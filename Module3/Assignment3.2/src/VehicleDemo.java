// Task 1 to 4
public class VehicleDemo {
    public static void main(String[] args) {
        System.out.println("Vehicle Demonstration\n");

        // Array of Vehicle (polymorphism)
        Vehicle[] vehicles = {
                new Car(),
                new Motorcycle(),
                new Bus(),
                new ElectricCar(),
                new ElectricMotorcycle()
        };

        for (Vehicle v : vehicles) {
            v.start();
            v.stop();

            System.out.println(v.getInfo());
            System.out.printf("Fuel Efficiency: %.2f km/unit%n", v.calculateFuelEfficiency());

            // Only electric vehicles can charge meaningfully
            if (v instanceof ElectricVehicle ev) {
                ev.charge();
            } else {
                System.out.println("Not possible to charge. This is a " + v.getInfo().split("\n")[1] + " vehicle.");
            }

            System.out.println();  // will print an empty line
        }
    }
}