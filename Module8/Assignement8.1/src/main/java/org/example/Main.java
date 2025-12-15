package org.example;

/**
 * Main class for Assignment 8.1 - JUnit Testing
 *
 * Task 1: Pen class (3 points)
 * - Implemented Pen class that passes all provided JUnit tests
 * - Pen has default color RED, cap on by default
 * - Can only draw when cap is off
 * - Can only change color when cap is on
 *
 * Task 2: StringManipulator class (3 points)
 * - Implemented StringManipulator with 5 methods
 * - Created comprehensive JUnit tests for all methods
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Assignment 8.1: JUnit Testing ===\n");

        // Demo Task 1: Pen class
        System.out.println("--- Task 1: Pen Demo ---");
        Pen pen = new Pen();
        System.out.println("Pen created with default color (RED)");
        System.out.println("Draw with cap on: \"" + pen.draw() + "\"");
        pen.capOff();
        System.out.println("Draw with cap off: \"" + pen.draw() + "\"");
        pen.capOn();
        pen.changeColor(Pen.Color.BLUE);
        pen.capOff();
        System.out.println("Draw after changing to BLUE: \"" + pen.draw() + "\"");

        // Demo Task 2: StringManipulator class
        System.out.println("\n--- Task 2: StringManipulator Demo ---");
        StringManipulator sm = new StringManipulator();
        System.out.println("concatenate(\"Hello \", \"World\"): " + sm.concatenate("Hello ", "World"));
        System.out.println("findLength(\"Hello\"): " + sm.findLength("Hello"));
        System.out.println("convertToUpperCase(\"hello\"): " + sm.convertToUpperCase("hello"));
        System.out.println("convertToLowerCase(\"HELLO\"): " + sm.convertToLowerCase("HELLO"));
        System.out.println("containsSubstring(\"Hello World\", \"World\"): " + sm.containsSubstring("Hello World", "World"));

        System.out.println("\n=== Run JUnit tests to verify implementation ===");
    }
}