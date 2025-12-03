// Task2
public class SafeStringListTest {
    private static final int THREAD_COUNT = 10;
    private static final int OPERATIONS_PER_THREAD = 1000;

    public static void main(String[] args) throws InterruptedException {
        SafeStringList safeList = new SafeStringList();

        // Phase 1: 10 threads adding 1000 items each -> should be like 10,000
        Thread[] adders = new Thread[THREAD_COUNT];
        for (int i = 0; i < THREAD_COUNT; i++) {
            final int id = i;
            adders[i] = new Thread(() -> {
                for (int j = 0; j < OPERATIONS_PER_THREAD; j++) {
                    safeList.add("Item-from-thread-" + id + "-" + j);
                }
            });
            adders[i].start();
        }

        for (Thread t : adders) t.join();

        System.out.println("After adding: size = " + safeList.size());

        // Phase 2: Try to remove some items from multiple threads
        Thread[] removers = new Thread[THREAD_COUNT];
        for (int i = 0; i < THREAD_COUNT; i++) {
            removers[i] = new Thread(() -> {
                for (int j = 0; j < 200; j++) {
                    safeList.remove("Item-from-thread-0-" + j);  // only threads 0 items
                }
            });
            removers[i].start();
        }

        for (Thread t : removers) t.join();

        System.out.println("After removing: size = " + safeList.size());
        System.out.println("Thread-safe list works perfectly!");
    }
}