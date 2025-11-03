public class TestBus {
    public static void main(String[] args) {
        Bus cityBus = new Bus(100, 40);
        cityBus.passengerEnter(25);
        cityBus.passengerEnter(20); // Over capacity
        cityBus.passengerExit(10);
        System.out.println(cityBus);
    }
}