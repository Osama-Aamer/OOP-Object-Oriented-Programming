package org.example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.example.model.Note;
import org.example.model.Notebook;


public class NoteController {

    @FXML
    private TextField titleField;

    @FXML
    private TextArea contentArea;

    @FXML
    private ListView<Note> notesListView;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button updateButton;

    private Notebook notebook;
    private ObservableList<Note> observableNotes;

    /**
     * Initializes the controller. Called automatically after FXML loading.
     */
    @FXML
    public void initialize() {
        notebook = new Notebook();
        observableNotes = FXCollections.observableArrayList();
        notesListView.setItems(observableNotes);

        // Add listener for ListView selection
        notesListView.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> {
                if (newValue != null) {
                    displayNote(newValue);
                }
            }
        );
    }

    @FXML
    private void handleAddNote() {
        String title = titleField.getText().trim();
        String content = contentArea.getText().trim();

        if (title.isEmpty()) {
            showAlert("Error", "Please enter a title for the note.");
            return;
        }

        Note note = new Note(title, content);
        notebook.addNote(note);
        observableNotes.add(note);

        // Clears input fields
        clearFields();

        System.out.println("Note added: " + title);
    }

    @FXML
    private void handleDeleteNote() {
        Note selectedNote = notesListView.getSelectionModel().getSelectedItem();

        if (selectedNote == null) {
            showAlert("Error", "Please select a note to delete.");
            return;
        }

        notebook.removeNote(selectedNote);
        observableNotes.remove(selectedNote);
        clearFields();

        System.out.println("Note deleted: " + selectedNote.getTitle());
    }

    @FXML
    private void handleUpdateNote() {
        Note selectedNote = notesListView.getSelectionModel().getSelectedItem();

        if (selectedNote == null) {
            showAlert("Error", "Please select a note to update.");
            return;
        }

        String newTitle = titleField.getText().trim();
        String newContent = contentArea.getText().trim();

        if (newTitle.isEmpty()) {
            showAlert("Error", "Please enter a title for the note.");
            return;
        }

        selectedNote.setTitle(newTitle);
        selectedNote.setContent(newContent);

        // Refresh the ListView to show updated title
        notesListView.refresh();

        System.out.println("Note updated: " + newTitle);
    }

    /**
     * Displays the selected note's details in the input fields.
     */
    private void displayNote(Note note) {
        titleField.setText(note.getTitle());
        contentArea.setText(note.getContent());
    }

    /**
     * Clears the input fields.
     */
    private void clearFields() {
        titleField.clear();
        contentArea.clear();
        notesListView.getSelectionModel().clearSelection();
    }

    /**
     * Shows an alert dialog with the specified title and message.
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
