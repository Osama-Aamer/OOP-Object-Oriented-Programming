//task3
public class ElectricCar extends AbstractVehicle {
    public ElectricCar() {
        super("Electric Car", "Electricity", "Blue", 5.5);  // 5.5 km/kWh
    }

    @Override
    public void charge() {
        System.out.println("Electric Car is charging... Battery at 80%");
    }

    @Override
    public String getInfo() {
        return super.getInfo() + "\nBattery: Lithium-ion";
    }
}