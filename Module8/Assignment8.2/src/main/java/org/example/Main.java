package org.example;

/**
 * Main class for Assignment 8.2 - Test-Driven Development (TDD)
 *
 * Task 1: PalindromeChecker class
 * - Checks if a string is a palindrome
 * - Ignores spaces, punctuation, and capitalization
 * - Comprehensive JUnit tests
 *
 * Task 2: ShoppingCart class
 * - Add items with prices
 * - Remove items
 * - Calculate total cost
 * - Comprehensive JUnit tests
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Assignment 8.2: Test-Driven Development ===\n");

        // Demo Task 1: PalindromeChecker
        System.out.println("--- Task 1: PalindromeChecker Demo ---");
        PalindromeChecker checker = new PalindromeChecker();

        String[] testStrings = {
            "radar",
            "A man, a plan, a canal, Panama",
            "hello",
            "Was it a car or a cat I saw?"
        };

        for (String s : testStrings) {
            System.out.println("\"" + s + "\" is palindrome: " + checker.isPalindrome(s));
        }

        // Demo Task 2: ShoppingCart
        System.out.println("\n--- Task 2: ShoppingCart Demo ---");
        ShoppingCart cart = new ShoppingCart();

        cart.addItem("Apple", 1.0);
        cart.addItem("Banana", 0.5);
        cart.addItem("Orange", 0.75);

        System.out.println("Items in cart: " + cart.getItemCount());
        System.out.println("Total: $" + cart.calculateTotal());

        cart.removeItem("Banana");
        System.out.println("After removing Banana:");
        System.out.println("Items in cart: " + cart.getItemCount());
        System.out.println("Total: $" + cart.calculateTotal());

        System.out.println("\n=== Run JUnit tests to verify TDD implementation ===");
    }
}