// Task2
import java.util.Random;

public class ParallelSum {
    private static final int ARRAY_SIZE = 100_000_000;  // 100 million elements
    private static final int[] numbers = new int[ARRAY_SIZE];

    static class SumTask extends Thread {
        private final int start;
        private final int end;
        private long partialSum = 0;

        public SumTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public void run() {
            for (int i = start; i < end; i++) {
                partialSum += numbers[i];
            }
        }

        public long getPartialSum() {
            return partialSum;
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Random rand = new Random(42);
        for (int i = 0; i < ARRAY_SIZE; i++) {
            numbers[i] = rand.nextInt(100);  //from 0 to 99
        }

        int cores = Runtime.getRuntime().availableProcessors();
        System.out.println("Available CPU cores: " + cores);

        // === SINGLE THREAD (baseline) ===
        timeIt (() -> {
            long sum = 0;
            for (int n : numbers) sum += n;
            return sum;
        }, "Single thread");

        // === MULTI THREAD (using all cores) ===
        long multiThreadSum = sumWithThreads(cores);
        System.out.println("Multi-thread sum: " + multiThreadSum);
    }

    private static long sumWithThreads(int threadCount) throws InterruptedException {
        SumTask[] tasks = new SumTask[threadCount];
        int chunkSize = ARRAY_SIZE / threadCount;

        // to create threads
        for (int i = 0; i < threadCount; i++) {
            int start = i * chunkSize;
            int end = (i == threadCount - 1) ? ARRAY_SIZE : start + chunkSize;
            tasks[i] = new SumTask(start, end);
            tasks[i].start();
        }

        // basically idea is to wait for all threads to finish and collect results
        long totalSum = 0;
        for (SumTask task : tasks) {
            task.join();
            totalSum += task.getPartialSum();
        }

        return totalSum;
    }

    private static void timeIt(Runnable task, String label) {
        long start = System.nanoTime();
        task.run();
        long end = System.nanoTime();
        System.out.printf("%s took: %.2f ms%n", label, (end - start) / 1_000_000.0);
    }

    // overloaded version that returns a value(basically its a helper to measure time and print resutls)
    private static long timeIt(LongSupplier task, String label) {
        long start = System.nanoTime();
        long result = task.getAsLong();
        long end = System.nanoTime();
        System.out.printf("%s sum: %d → took %.2f ms%n", label, result, (end - start) / 1_000_000.0);
        return result;
    }

    @FunctionalInterface
    interface LongSupplier {
        long getAsLong();
    }
}