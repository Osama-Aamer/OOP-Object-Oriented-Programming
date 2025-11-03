//task3
public class ElectricMotorcycle extends AbstractVehicle {
    public ElectricMotorcycle() {
        super("Electric Motorcycle", "Electricity", "Green", 8.0);  // 8 km/kWh
    }

    @Override
    public void charge() {
        System.out.println("Electric Motorcycle is charging... Fast charge enabled");
    }

    @Override
    public String getInfo() {
        return super.getInfo() + "\nRange: 150 km";
    }
}