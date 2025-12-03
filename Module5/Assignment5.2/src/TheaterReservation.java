// Task1
public class TheaterReservation {
    private static final int TOTAL_SEATS = 10;
    private int availableSeats = TOTAL_SEATS;

    public synchronized boolean reserve(int customerId, int ticketsWanted) {
        System.out.printf("Customer %d is trying to reserve %d ticket(s)...%n",
                customerId, ticketsWanted);

        if (availableSeats >= ticketsWanted) {
            // simulating booking delay
            try { Thread.sleep(100); } catch (InterruptedException e) {}

            availableSeats -= ticketsWanted;
            System.out.printf("Customer %d reserved %d ticket(s). (Seats left: %d)%n",
                    customerId, ticketsWanted, availableSeats);
            return true;
        } else {
            System.out.printf("Customer %d couldn't reserve %d ticket(s). Only %d left.%n",
                    customerId, ticketsWanted, availableSeats);
            return false;
        }
    }

    public static void main(String[] args) {
        TheaterReservation theater = new TheaterReservation();

        // we make 15 customers who try to book 1–4 tickets each
        for (int i = 1; i <= 15; i++) {
            final int customerId = i;
            final int ticketsToBook = (i % 4) + 1;

            new Thread(() -> {
                theater.reserve(customerId, ticketsToBook);
            }, "Customer-" + i).start();
        }
    }
}