package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartTest {

    private ShoppingCart cart;

    @BeforeEach
    void setUp() {
        cart = new ShoppingCart();
    }

    // ==================== Add Item Tests ====================

    @Test
    @DisplayName("addItem: should add items to cart and increase count")
    void testAddItem() {
        cart.addItem("Apple", 1.0);
        cart.addItem("Banana", 0.5);

        assertEquals(2, cart.getItemCount());
    }

    @Test
    @DisplayName("addItem: should add single item")
    void testAddSingleItem() {
        cart.addItem("Apple", 1.0);

        assertEquals(1, cart.getItemCount());
        assertTrue(cart.containsItem("Apple"));
    }

    @Test
    @DisplayName("addItem: should update price if item already exists")
    void testAddItemUpdatesPrice() {
        cart.addItem("Apple", 1.0);
        cart.addItem("Apple", 1.5);

        assertEquals(1, cart.getItemCount());
        assertEquals(1.5, cart.getItemPrice("Apple"), 0.01);
    }

    // ==================== Remove Item Tests ====================

    @Test
    @DisplayName("removeItem: should remove item from cart")
    void testRemoveItem() {
        cart.addItem("Apple", 1.0);
        cart.addItem("Banana", 0.5);
        cart.removeItem("Apple");

        assertEquals(1, cart.getItemCount());
        assertFalse(cart.containsItem("Apple"));
        assertTrue(cart.containsItem("Banana"));
    }

    @Test
    @DisplayName("removeItem: should handle removing non-existent item")
    void testRemoveNonExistentItem() {
        cart.addItem("Apple", 1.0);
        cart.removeItem("Orange");

        assertEquals(1, cart.getItemCount());
    }

    @Test
    @DisplayName("removeItem: should make cart empty when last item removed")
    void testRemoveLastItem() {
        cart.addItem("Apple", 1.0);
        cart.removeItem("Apple");

        assertEquals(0, cart.getItemCount());
        assertTrue(cart.isEmpty());
    }

    // ==================== Calculate Total Tests ====================

    @Test
    @DisplayName("calculateTotal: should calculate total cost correctly")
    void testCalculateTotal() {
        cart.addItem("Apple", 1.0);
        cart.addItem("Banana", 0.5);
        cart.addItem("Orange", 0.75);

        assertEquals(2.25, cart.calculateTotal(), 0.01);
    }

    @Test
    @DisplayName("calculateTotal: should return 0 for empty cart")
    void testCalculateTotalEmptyCart() {
        assertEquals(0.0, cart.calculateTotal(), 0.01);
    }

    @Test
    @DisplayName("calculateTotal: should return correct total for single item")
    void testCalculateTotalSingleItem() {
        cart.addItem("Apple", 1.99);

        assertEquals(1.99, cart.calculateTotal(), 0.01);
    }

    @Test
    @DisplayName("calculateTotal: should update after removing item")
    void testCalculateTotalAfterRemove() {
        cart.addItem("Apple", 1.0);
        cart.addItem("Banana", 0.5);
        cart.removeItem("Apple");

        assertEquals(0.5, cart.calculateTotal(), 0.01);
    }

    // ==================== Get Item Count Tests ====================

    @Test
    @DisplayName("getItemCount: should return 0 for new cart")
    void testGetItemCountEmpty() {
        assertEquals(0, cart.getItemCount());
    }

    @Test
    @DisplayName("getItemCount: should return correct count")
    void testGetItemCount() {
        cart.addItem("Apple", 1.0);
        cart.addItem("Banana", 0.5);
        cart.addItem("Orange", 0.75);

        assertEquals(3, cart.getItemCount());
    }

    // ==================== Contains Item Tests ====================

    @Test
    @DisplayName("containsItem: should return true for existing item")
    void testContainsItemTrue() {
        cart.addItem("Apple", 1.0);

        assertTrue(cart.containsItem("Apple"));
    }

    @Test
    @DisplayName("containsItem: should return false for non-existing item")
    void testContainsItemFalse() {
        cart.addItem("Apple", 1.0);

        assertFalse(cart.containsItem("Banana"));
    }

    // ==================== Clear Cart Tests ====================

    @Test
    @DisplayName("clear: should remove all items from cart")
    void testClear() {
        cart.addItem("Apple", 1.0);
        cart.addItem("Banana", 0.5);
        cart.clear();

        assertEquals(0, cart.getItemCount());
        assertTrue(cart.isEmpty());
        assertEquals(0.0, cart.calculateTotal(), 0.01);
    }

    // ==================== Is Empty Tests ====================

    @Test
    @DisplayName("isEmpty: should return true for new cart")
    void testIsEmptyTrue() {
        assertTrue(cart.isEmpty());
    }

    @Test
    @DisplayName("isEmpty: should return false after adding item")
    void testIsEmptyFalse() {
        cart.addItem("Apple", 1.0);

        assertFalse(cart.isEmpty());
    }

    // ==================== Get Item Price Tests ====================

    @Test
    @DisplayName("getItemPrice: should return correct price")
    void testGetItemPrice() {
        cart.addItem("Apple", 1.99);

        assertEquals(1.99, cart.getItemPrice("Apple"), 0.01);
    }

    @Test
    @DisplayName("getItemPrice: should return -1 for non-existing item")
    void testGetItemPriceNotFound() {
        assertEquals(-1, cart.getItemPrice("Apple"), 0.01);
    }
}

