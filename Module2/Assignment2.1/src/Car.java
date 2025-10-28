public class Car {
    private double gasolineTankCapacity;
    private double currentGasoline;
    private double speed;

    // === TASK 2: New constructor with initial speed ===
    private double cruiseTargetSpeed;   // Task 4
    private boolean cruiseControlOn;    // Task 4
    private static final double MIN_CRUISE = 30.0;  //  Task 4
    private static final double MAX_CRUISE = 120.0; //  Task 4

    // Original constructor
    public Car(double gasolineTankCapacity) {
        this.gasolineTankCapacity = gasolineTankCapacity;
        this.currentGasoline = 0;
        this.speed = 0;
        initCruiseControl(); // initialize cruise fields
    }

    // TASK 2: New constructor with tank capacity and initial speed
    public Car(double gasolineTankCapacity, double initialSpeed) {
        this(gasolineTankCapacity);
        this.speed = initialSpeed;
    }

    // ADDED: Helper to initialize cruise control fields
    private void initCruiseControl() {
        this.cruiseControlOn = false;
        this.cruiseTargetSpeed = 0.0;
    }

    public void accelerate(double amount) {
        speed += amount;
        if (speed > 200) speed = 200;
        adjustToCruise();
    }

    public void brake(double amount) {
        speed -= amount;
        if (speed < 0) speed = 0;
        adjustToCruise();
    }

    // === TASK 4: CRUISE CONTROL METHODS ===

    //
    public void setCruiseSpeed(double target) {
        if (target < MIN_CRUISE || target > MAX_CRUISE) {
            System.out.println("Cruise speed must be between " + MIN_CRUISE + " and " + MAX_CRUISE);
            return;
        }
        this.cruiseTargetSpeed = target;
        System.out.println("Cruise target set to " + target + " km/h");
    }

    //
    public boolean turnOnCruiseControl() {
        if (speed < MIN_CRUISE || speed > MAX_CRUISE) {
            System.out.println("Cannot activate cruise control: speed must be between " + MIN_CRUISE + " and " + MAX_CRUISE);
            cruiseControlOn = false;
            return false;
        }
        cruiseControlOn = true;
        System.out.println("Cruise control ON at " + speed + " km/h");
        adjustToCruise();
        return true;
    }


    public void turnOffCruiseControl() {
        cruiseControlOn = false;
        System.out.println("Cruise control OFF");
    }

    private void adjustToCruise() {
        if (!cruiseControlOn) return;

        double diff = cruiseTargetSpeed - speed;
        if (Math.abs(diff) > 0.5) {
            if (diff > 0) {
                accelerate(1.0);
            } else {
                brake(1.0);
            }
            System.out.println("Adjusting speed to " + cruiseTargetSpeed + "... current: " + String.format("%.1f", speed));
        }
    }


    public double getSpeed() {
        return speed;
    }

    public double getCruiseSpeed() {
        return cruiseTargetSpeed;
    }

    public boolean isCruiseOn() {
        return cruiseControlOn;
    }

    public void fillGas(double liters) {
        currentGasoline = Math.min(currentGasoline + liters, gasolineTankCapacity);
    }

    public double getGasoline() {
        return currentGasoline;
    }

    @Override
    public String toString() {
        return "Car{speed=" + String.format("%.1f", speed) + " km/h, cruise=" +
                (cruiseControlOn ? cruiseTargetSpeed : "OFF") + "}";
    }
}