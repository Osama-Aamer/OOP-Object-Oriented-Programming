package view;

import controller.DictionaryController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DictionaryView {
    private final DictionaryController controller;
    private TextField wordField;
    private TextArea resultArea;

    public DictionaryView(DictionaryController controller) {
        this.controller = controller;
    }

    public void start(Stage stage) {
        stage.setTitle("Virtual Dictionary");

        Label lbl = new Label("Enter a word:");
        wordField = new TextField();
        wordField.setPromptText("e.g. java");

        Button searchBtn = new Button("Search");
        searchBtn.setDefaultButton(true);

        resultArea = new TextArea();
        resultArea.setEditable(false);
        resultArea.setWrapText(true);
        resultArea.setPrefRowCount(6);

        searchBtn.setOnAction(e -> handleSearch());

        VBox root = new VBox(15, lbl, wordField, searchBtn, resultArea);
        root.setPadding(new Insets(20));
        root.setPrefSize(500, 400);

        stage.setScene(new Scene(root));
        stage.show();
    }

    private void handleSearch() {
        String word = wordField.getText();
        String result = controller.searchWord(word);

        if (result.startsWith("Word cannot be empty") || result.startsWith("Word not found")) {
            resultArea.setStyle("-fx-text-fill: red;");
        } else {
            resultArea.setStyle("-fx-text-fill: green;");
        }
        resultArea.setText(result);
        wordField.clear();
    }
}