package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

class PalindromeCheckerTest {

    private PalindromeChecker checker;

    @BeforeEach
    void setUp() {
        checker = new PalindromeChecker();
    }

    // ==================== Simple Palindrome Tests ====================

    @Test
    @DisplayName("isPalindrome: should return true for simple palindrome 'radar'")
    void testSimplePalindromeRadar() {
        assertTrue(checker.isPalindrome("radar"));
    }

    @Test
    @DisplayName("isPalindrome: should return true for simple palindrome 'level'")
    void testSimplePalindromeLevel() {
        assertTrue(checker.isPalindrome("level"));
    }

    @Test
    @DisplayName("isPalindrome: should return true for single character")
    void testSingleCharacter() {
        assertTrue(checker.isPalindrome("a"));
    }

    @Test
    @DisplayName("isPalindrome: should return true for empty string")
    void testEmptyString() {
        assertTrue(checker.isPalindrome(""));
    }

    // ==================== Non-Palindrome Tests ====================

    @Test
    @DisplayName("isPalindrome: should return false for 'hello'")
    void testNonPalindromeHello() {
        assertFalse(checker.isPalindrome("hello"));
    }

    @Test
    @DisplayName("isPalindrome: should return false for 'openai'")
    void testNonPalindromeOpenai() {
        assertFalse(checker.isPalindrome("openai"));
    }

    @Test
    @DisplayName("isPalindrome: should return false for 'world'")
    void testNonPalindromeWorld() {
        assertFalse(checker.isPalindrome("world"));
    }

    // ==================== Complex Palindrome Tests (with spaces and punctuation) ====================

    @Test
    @DisplayName("isPalindrome: should return true for 'A man, a plan, a canal, Panama'")
    void testComplexPalindromeWithPunctuation() {
        assertTrue(checker.isPalindrome("A man, a plan, a canal, Panama"));
    }

    @Test
    @DisplayName("isPalindrome: should return true for 'Was it a car or a cat I saw?'")
    void testComplexPalindromeQuestion() {
        assertTrue(checker.isPalindrome("Was it a car or a cat I saw?"));
    }

    @Test
    @DisplayName("isPalindrome: should return true for 'No lemon, no melon'")
    void testComplexPalindromeNoLemon() {
        assertTrue(checker.isPalindrome("No lemon, no melon"));
    }

    // ==================== Case Insensitivity Tests ====================

    @Test
    @DisplayName("isPalindrome: should be case insensitive")
    void testCaseInsensitive() {
        assertTrue(checker.isPalindrome("Radar"));
        assertTrue(checker.isPalindrome("RADAR"));
        assertTrue(checker.isPalindrome("RaDaR"));
    }

    // ==================== Edge Cases ====================

    @Test
    @DisplayName("isPalindrome: should return false for null")
    void testNullInput() {
        assertFalse(checker.isPalindrome(null));
    }

    @Test
    @DisplayName("isPalindrome: should return true for two same characters")
    void testTwoSameCharacters() {
        assertTrue(checker.isPalindrome("aa"));
    }

    @Test
    @DisplayName("isPalindrome: should return false for two different characters")
    void testTwoDifferentCharacters() {
        assertFalse(checker.isPalindrome("ab"));
    }

    // ==================== Numbers in Palindrome ====================

    @Test
    @DisplayName("isPalindrome: should return true for numeric palindrome")
    void testNumericPalindrome() {
        assertTrue(checker.isPalindrome("12321"));
    }

    @Test
    @DisplayName("isPalindrome: should return true for alphanumeric palindrome")
    void testAlphanumericPalindrome() {
        assertTrue(checker.isPalindrome("A1B2B1A"));
    }
}

