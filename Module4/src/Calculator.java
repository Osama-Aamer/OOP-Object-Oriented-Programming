// Calculator.java
public class Calculator {
    private int value;

    public Calculator() {
        this.value = 0;
    }

    /**
     * Resets the calculator to zero
     */
    public void reset() {
        this.value = 0;
    }

    /**
     * Adds a positive integer to the current value.
     * Throws IllegalArgumentException if the number is negative.
     */
    public void add(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Cannot add negative number: " + number);
        }
        this.value += number;
    }

    /**
     * Returns the current value of the calculator
     */
    public int getValue() {
        return this.value;
    }

    // Temporary main method for testing
    public static void main(String[] args) {
        Calculator calc = new Calculator();

        calc.add(10);
        calc.add(20);
        calc.add(30);

        System.out.println("Current value: " + calc.getValue()); // 60

        try {
            calc.add(-5);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        calc.reset();
        System.out.println("After reset: " + calc.getValue()); // 0
    }
}