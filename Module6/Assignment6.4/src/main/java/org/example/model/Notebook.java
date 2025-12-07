package org.example.model;

import java.util.ArrayList;
import java.util.List;


public class Notebook {
    private List<Note> notes;

    /**
     * Creates a new empty Notebook.
     */
    public Notebook() {
        this.notes = new ArrayList<>();
    }


    public void addNote(Note note) {
        notes.add(note);
    }

    public void removeNote(Note note) {
        notes.remove(note);
    }

    /**
     * Gets all notes in the notebook.
     */
    public List<Note> getNotes() {
        return notes;
    }

    /**
     * Gets a note at a specific index.
     * index = the index of the note
     */
    public Note getNote(int index) {
        if (index >= 0 && index < notes.size()) {
            return notes.get(index);
        }
        return null;
    }

    /**
     * Gets the number of notes in the notebook.
     */
    public int getSize() {
        return notes.size();
    }
}

