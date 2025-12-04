package model;

import java.util.HashMap;
import java.util.Map;

public class Dictionary {
    private final Map<String, String> words = new HashMap<>();

    public Dictionary() {
        addWord("java", "A popular programming language and platform.");
        addWord("thread", "A lightweight process that allows concurrent execution.");
        addWord("mvc", "Model-View-Controller – a design pattern.");
        addWord("dictionary", "A collection of words and their meanings.");
        addWord("hello", "A common greeting.");
    }

    public void addWord(String word, String meaning) {
        words.put(word.toLowerCase(), meaning);
    }

    public String search(String word) {
        if (word == null || word.trim().isEmpty()) {
            throw new IllegalArgumentException("Word cannot be empty");
        }
        String meaning = words.get(word.toLowerCase().trim());
        if (meaning == null) {
            throw new IllegalArgumentException("Word not found: " + word);
        }
        return meaning;
    }
}