//task 1 , 2
public class Bus extends AbstractVehicle {
    private int capacity;

    public Bus() {
        super("Bus", "Diesel", "Yellow", 4.5);  // 4.5 km/L
        this.capacity = 40;
    }

    @Override
    public String getInfo() {
        return super.getInfo() + "\nCapacity: " + capacity + " passengers";
    }
}
