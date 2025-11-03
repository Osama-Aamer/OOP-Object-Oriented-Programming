public class SportsCar extends Car {
    public SportsCar(double gasolineTankCapacity) {
        super(gasolineTankCapacity);
    }

    @Override
    public void accelerate(double amount) {
        // 2x faster acceleration
        super.accelerate(amount * 2);
        // 3x higher gas consumption
        consumeGas(amount * 0.3);
    }

    @Override
    public void brake(double amount) {
        // 2x faster deceleration
        super.brake(amount * 2);
    }

    @Override
    public String toString() {
        return "SportsCar" + super.toString().substring(3);
    }
}