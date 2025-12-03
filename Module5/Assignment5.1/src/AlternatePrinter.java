// Task1
public class AlternatePrinter {
    private static final int MAX_NUMBER = 20;  // we can change/alter this for longer/shorter range
    private static final Object lock = new Object();
    private static int current = 1;
    private static boolean isOddTurn = true;

    static class OddPrinter extends Thread {
        public void run() {
            while (true) {
                synchronized (lock) {
                    while (!isOddTurn || current > MAX_NUMBER) {
                        if (current > MAX_NUMBER) return;
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    if (current > MAX_NUMBER) return;

                    System.out.println("Odd Thread: " + current);
                    current++;
                    isOddTurn = false;
                    lock.notifyAll();
                }
            }
        }
    }

    static class EvenPrinter extends Thread {
        public void run() {
            while (true) {
                synchronized (lock) {
                    while (isOddTurn || current > MAX_NUMBER) {
                        if (current > MAX_NUMBER) return;
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    if (current > MAX_NUMBER) return;

                    System.out.println("Even Thread: " + current);
                    current++;
                    isOddTurn = true;
                    lock.notifyAll();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread oddThread = new OddPrinter();
        Thread evenThread = new EvenPrinter();

        oddThread.start();
        evenThread.start();

        oddThread.join();
        evenThread.join();

        System.out.println("Printing complete.");
    }
}