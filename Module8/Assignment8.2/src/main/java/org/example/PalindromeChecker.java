package org.example;

/**explainition of the class:
 * A class that checks if a given string is a palindrome.
 * A palindrome reads the same forward and backward,
 * ignoring spaces, punctuation, and capitalization.
 */
public class PalindromeChecker {

    /**
     * Checks if the given string is a palindrome.
     * Ignores spaces, punctuation, and capitalization.
     *
     * str= the string to check
     * returns true if the string is a palindrome, false otherwise
     */
    public boolean isPalindrome(String str) {
        if (str == null) {
            return false;
        }

        // Remove all non-alphanumeric characters and convert to lowercase
        String cleaned = str.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        // Check if the cleaned string reads the same forward and backward
        int left = 0;
        int right = cleaned.length() - 1;

        while (left < right) {
            if (cleaned.charAt(left) != cleaned.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }
}

