package controller;

import model.Dictionary;

public class DictionaryController {
    private final Dictionary dictionary;

    public DictionaryController() {
        this.dictionary = new Dictionary();
    }

    public String searchWord(String word) {
        try {
            return dictionary.search(word);
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }
}