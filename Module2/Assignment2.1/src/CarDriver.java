public class CarDriver {
    public static void main(String[] args) {
        Car car = new Car(50.0, 40.0);

        System.out.println("Initial: " + car);

        car.setCruiseSpeed(60.0);
        car.turnOnCruiseControl();

        // to simulate driving
        for (int i = 0; i < 10; i++) {
            car.accelerate(0); // Just trigger cruise adjustment
        }

        car.turnOffCruiseControl();

        // Try invalid cruise
        car.brake(50);
        car.setCruiseSpeed(60.0);
        car.turnOnCruiseControl(); // should fail

        car.accelerate(50);
        car.turnOnCruiseControl(); // should succeed
    }
}