package org.example;

import java.util.HashMap;
import java.util.Map;

/**
 * A class that manages items in a shopping cart.
 * Allows adding items, removing items, and calculating the total cost.
 */
public class ShoppingCart {

    private Map<String, Double> items;

    /**
     * Creates a new empty shopping cart.
     */
    public ShoppingCart() {
        this.items = new HashMap<>();
    }

    /**
     * Adds an item to the cart with the specified price.
     * If the item already exists, it will be replaced with the new price.
     *
     */
    public void addItem(String itemName, double price) {
        items.put(itemName, price);
    }

    /**
     * Removes an item from the cart.
     */
    public void removeItem(String itemName) {
        items.remove(itemName);
    }

    /**
     * Returns the number of items in the cart.
     *return the item count
     */
    public int getItemCount() {
        return items.size();
    }

    /**
     * Calculates and returns the total cost of all items in the cart.
     */
    public double calculateTotal() {
        double total = 0.0;
        for (double price : items.values()) {
            total += price;
        }
        return total;
    }

    /**
     * Checks if the cart contains a specific item.
     * returns true if the item is in the cart, false otherwise
     */
    public boolean containsItem(String itemName) {
        return items.containsKey(itemName);
    }

    /**
     * Gets the price of a specific item.
     *returns the price of the item, or -1 if not found
     */
    public double getItemPrice(String itemName) {
        if (items.containsKey(itemName)) {
            return items.get(itemName);
        }
        return -1;
    }

    /**
     * Clears all items from the cart.
     */
    public void clear() {
        items.clear();
    }

    /**
     * Checks if the cart is empty.
     *return true if the cart is empty, false otherwise
     */
    public boolean isEmpty() {
        return items.isEmpty();
    }
}

