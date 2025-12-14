package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

class StringManipulatorTest {

    private StringManipulator manipulator;

    @BeforeEach
    void setUp() {
        manipulator = new StringManipulator();
    }

    @Test
    @DisplayName("concatenate: should concatenate two strings")
    void testConcatenateTwoStrings() {
        assertEquals("HelloWorld", manipulator.concatenate("Hello", "World"));
    }

    @Test
    @DisplayName("concatenate: should handle empty strings")
    void testConcatenateEmptyStrings() {
        assertEquals("Hello", manipulator.concatenate("Hello", ""));
        assertEquals("World", manipulator.concatenate("", "World"));
        assertEquals("", manipulator.concatenate("", ""));
    }

    @Test
    @DisplayName("findLength: should return correct length")
    void testFindLength() {
        assertEquals(5, manipulator.findLength("Hello"));
        assertEquals(11, manipulator.findLength("Hello World"));
    }

    @Test
    @DisplayName("findLength: should return 0 for empty string")
    void testFindLengthEmptyString() {
        assertEquals(0, manipulator.findLength(""));
    }

    @Test
    @DisplayName("convertToUpperCase: should convert to uppercase")
    void testConvertToUpperCase() {
        assertEquals("HELLO", manipulator.convertToUpperCase("hello"));
        assertEquals("HELLO WORLD", manipulator.convertToUpperCase("Hello World"));
    }

    @Test
    @DisplayName("convertToUpperCase: should handle empty string")
    void testConvertToUpperCaseEmptyString() {
        assertEquals("", manipulator.convertToUpperCase(""));
    }

    @Test
    @DisplayName("convertToLowerCase: should convert to lowercase")
    void testConvertToLowerCase() {
        assertEquals("hello", manipulator.convertToLowerCase("HELLO"));
        assertEquals("hello world", manipulator.convertToLowerCase("Hello World"));
    }

    @Test
    @DisplayName("convertToLowerCase: should handle empty string")
    void testConvertToLowerCaseEmptyString() {
        assertEquals("", manipulator.convertToLowerCase(""));
    }

    @Test
    @DisplayName("containsSubstring: should return true when substring exists")
    void testContainsSubstringTrue() {
        assertTrue(manipulator.containsSubstring("Hello World", "World"));
        assertTrue(manipulator.containsSubstring("Hello World", "Hello"));
    }

    @Test
    @DisplayName("containsSubstring: should return false when substring does not exist")
    void testContainsSubstringFalse() {
        assertFalse(manipulator.containsSubstring("Hello World", "world"));
        assertFalse(manipulator.containsSubstring("Hello World", "xyz"));
    }

    @Test
    @DisplayName("containsSubstring: should return true for empty substring")
    void testContainsSubstringEmpty() {
        assertTrue(manipulator.containsSubstring("Hello", ""));
    }
}

