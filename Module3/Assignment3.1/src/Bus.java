public class Bus extends Car {
    private int passengerCount;
    private int maxPassengers;

    public Bus(double gasolineTankCapacity, int maxPassengers) {
        super(gasolineTankCapacity);
        this.maxPassengers = maxPassengers;
        this.passengerCount = 0;
    }

    public void passengerEnter(int count) {
        if (count < 0) return;
        int newTotal = passengerCount + count;
        if (newTotal > maxPassengers) {
            System.out.println("Cannot board " + count + " passengers. Bus is full!");
            passengerCount = maxPassengers;
        } else {
            passengerCount = newTotal;
            System.out.println(count + " passenger(s) entered. Total: " + passengerCount);
        }
    }

    public void passengerExit(int count) {
        if (count < 0) return;
        int newTotal = passengerCount - count;
        if (newTotal < 0) {
            System.out.println("Cannot exit " + count + " passengers. Only " + passengerCount + " on board.");
            passengerCount = 0;
        } else {
            passengerCount = newTotal;
            System.out.println(count + " passenger(s) exited. Total: " + passengerCount);
        }
    }

    public int getPassengerCount() { return passengerCount; }

    @Override
    public String toString() {
        return String.format("Bus{passengers=%d/%d, %s", passengerCount, maxPassengers, super.toString().substring(4));
    }
}