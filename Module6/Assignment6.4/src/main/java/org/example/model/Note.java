package org.example.model;

/**
 * Represents a single note with a title and content.
 */
public class Note {
    private String title;
    private String content;

    /**
     * Creates a new Note with the specified title and content.

     */
    public Note(String title, String content) {
        this.title = title;
        this.content = content;
    }

    /**
     * Gets the title of the note.
     * returns the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the note.
     * basically sets up the new title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the content of the note.
     * returns the content of the note
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the content of the note.
     * basically sets up the new content in the note
     */
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return title;
    }
}
