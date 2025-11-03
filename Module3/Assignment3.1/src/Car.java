public class Car {
    protected double gasolineTankCapacity;
    protected double currentGasoline;
    protected double speed;

    public Car(double gasolineTankCapacity) {
        this.gasolineTankCapacity = gasolineTankCapacity;
        this.currentGasoline = gasolineTankCapacity; // Full tank
        this.speed = 0;
    }

    public void accelerate(double amount) {
        speed += amount;
        if (speed > 200) speed = 200;
        consumeGas(amount * 0.1); // Base consumption
    }

    public void brake(double amount) {
        speed -= amount;
        if (speed < 0) speed = 0;
    }

    protected void consumeGas(double liters) {
        currentGasoline = Math.max(0, currentGasoline - liters);
    }

    public double getSpeed() { return speed; }
    public double getGasoline() { return currentGasoline; }

    @Override
    public String toString() {
        return String.format("Car{speed=%.1f km/h, gas=%.1f/%.0fL}", speed, currentGasoline, gasolineTankCapacity);
    }
}