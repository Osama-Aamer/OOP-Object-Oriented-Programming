//task 2 & 3 & 4
public abstract class AbstractVehicle implements Vehicle, ElectricVehicle {
    protected String type;
    protected String fuel;
    protected String color;
    protected double fuelEfficiency;  // in km/L or km/kWh

    public AbstractVehicle(String type, String fuel, String color, double fuelEfficiency) {
        this.type = type;
        this.fuel = fuel;
        this.color = color;
        this.fuelEfficiency = fuelEfficiency;
    }

    @Override
    public void start() {
        System.out.println(type + " is starting...");
    }

    @Override
    public void stop() {
        System.out.println(type + " is stopping...");
    }

    @Override
    public double calculateFuelEfficiency() {
        return fuelEfficiency;
    }

    // set so default: non-electric vehicles can't charge
    @Override
    public void charge() {
        System.out.println("Not possible to charge. This is a " + fuel + " vehicle.");
    }

    @Override
    public String getInfo() {
        return "Type: " + type + "\n" +
                "Fuel: " + fuel + "\n" +
                "Color: " + color;
    }
}