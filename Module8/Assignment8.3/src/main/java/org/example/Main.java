package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;/**

 * Assignment 8.3: Lambda Expressions
 *
 * Task 1: Sorting and Filtering using Lambda
 * Task 2: Collection Operations with Lambdas
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Assignment 8.3: Lambda Expressions ===\n");

        // Run Task 1
        task1SortingAndFiltering();

        // Run Task 2
        task2CollectionOperations();
    }

    /**
     * Task 1: Sorting and Filtering using Lambda
     */
    public static void task1SortingAndFiltering() {
        System.out.println("--- Task 1: Sorting and Filtering using Lambda ---\n");

        // Step 1: Create a list of Person objects
        List<Person> people = new ArrayList<>();
        people.add(new Person("Alice", 30, "New York"));
        people.add(new Person("Bob", 25, "Los Angeles"));
        people.add(new Person("Charlie", 35, "New York"));
        people.add(new Person("Diana", 28, "Chicago"));
        people.add(new Person("Eve", 22, "New York"));
        people.add(new Person("Frank", 40, "Los Angeles"));
        people.add(new Person("Grace", 33, "New York"));

        System.out.println("Original list:");
        people.forEach(p -> System.out.println("  " + p));

        // Step 2: Sort by age using lambda expression and Comparator
        System.out.println("\nSorting by age (ascending) using lambda...");
        people.sort((p1, p2) -> p1.getAge() - p2.getAge());

        System.out.println("Sorted list:");
        people.forEach(p -> System.out.println("  " + p));

        // Step 3: Filter by city using removeIf() with lambda
        String filterCity = "New York";
        System.out.println("\nFiltering to keep only people from '" + filterCity + "'...");
        people.removeIf(p -> !p.getCity().equals(filterCity));

        System.out.println("Filtered list (only " + filterCity + "):");
        people.forEach(p -> System.out.println("  " + p));

        // Sort by name example
        System.out.println("\n--- Additional: Sort by name ---");
        List<Person> morePeople = new ArrayList<>();
        morePeople.add(new Person("Zara", 28, "Boston"));
        morePeople.add(new Person("Adam", 35, "Boston"));
        morePeople.add(new Person("Mike", 28, "Boston"));

        morePeople.sort((p1, p2) -> p1.getName().compareTo(p2.getName()));
        morePeople.forEach(p -> System.out.println("  " + p));

        System.out.println();
    }

    /**
     * Task 2: Collection Operations with Lambdas
     */
    public static void task2CollectionOperations() {
        System.out.println("--- Task 2: Collection Operations with Lambdas ---\n");

        // Step 1: Create a list of integers
        List<Integer> numbers = new ArrayList<>(Arrays.asList(10, 5, 8, 20, 15, 3, 12));
        System.out.println("Original list: " + numbers);

        // Step 2: Filter out even numbers (keep only odd numbers)
        System.out.println("\nFiltering out even numbers...");
        numbers.removeIf(n -> n % 2 == 0);
        System.out.println("After removing evens: " + numbers);

        // Step 3: Double the odd numbers using replaceAll()
        System.out.println("\nDoubling the remaining odd numbers...");
        numbers.replaceAll(n -> n * 2);
        System.out.println("After doubling: " + numbers);

        // Step 4: Sum the numbers using forEach()
        System.out.println("\nCalculating sum...");
        AtomicInteger sum = new AtomicInteger(0);
        numbers.forEach(n -> sum.addAndGet(n));
        System.out.println("Sum of all numbers: " + sum.get());

        // Alternative sum with array
        final int[] sumArray = {0};
        numbers.forEach(n -> sumArray[0] += n);
        System.out.println("Sum (alternative): " + sumArray[0]);

        System.out.println();
    }
}