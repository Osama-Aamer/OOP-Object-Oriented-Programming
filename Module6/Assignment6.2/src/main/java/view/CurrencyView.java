package view;

import controller.CurrencyController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.regex.Pattern;

public class CurrencyView {
    private final CurrencyController controller;
    private TextField amountField;
    private ComboBox<String> fromCombo;
    private ComboBox<String> toCombo;
    private Label resultLabel;

    public CurrencyView(CurrencyController controller) {
        this.controller = controller;
    }

    public void start(Stage stage) {
        stage.setTitle("Currency Converter");
        stage.setWidth(500);
        stage.setHeight(400);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(30));
        grid.setVgap(15);
        grid.setHgap(15);
        grid.setStyle("-fx-font-family: 'Segoe UI', Arial, sans-serif; -fx-font-size: 14px;");

        Label title = new Label("Currency Converter");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        GridPane.setColumnSpan(title, 2);
        grid.add(title, 0, 0);

        Label instruction = new Label("Enter amount and select currencies to convert");
        instruction.setStyle("-fx-text-fill: #555;");
        GridPane.setColumnSpan(instruction, 2);
        grid.add(instruction, 0, 1);

        grid.add(new Label("Amount:"), 0, 2);
        amountField = new TextField();
        amountField.setPromptText("e.g. 100.00");
        // Allow only numbers and one decimal point
        amountField.textProperty().addListener((obs, old, newVal) -> {
            if (!Pattern.matches("\\d*\\.?\\d*", newVal)) {
                amountField.setText(old);
            }
        });
        grid.add(amountField, 1, 2);

        grid.add(new Label("From:"), 0, 3);
        fromCombo = new ComboBox<>();
        fromCombo.getItems().addAll(controller.getModel().getCurrencies().keySet());
        fromCombo.setValue("USD");
        grid.add(fromCombo, 1, 3);

        grid.add(new Label("To:"), 0, 4);
        toCombo = new ComboBox<>();
        toCombo.getItems().addAll(controller.getModel().getCurrencies().keySet());
        toCombo.setValue("EUR");
        grid.add(toCombo, 1, 4);

        Button convertBtn = new Button("Convert");
        convertBtn.setStyle("-fx-background-color: #0066cc; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10px 20px;");
        convertBtn.setOnAction(e -> performConversion());
        GridPane.setColumnSpan(convertBtn, 2);
        grid.add(convertBtn, 0, 5);

        resultLabel = new Label("Result will appear here");
        resultLabel.setStyle("-fx-font-size: 18px; -fx-padding: 15px; -fx-background-color: #f0f0f0; -fx-border-color: #ccc;");
        GridPane.setColumnSpan(resultLabel, 2);
        grid.add(resultLabel, 0, 6);

        Scene scene = new Scene(grid);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    private void performConversion() {
        String result = controller.convert(amountField.getText(), fromCombo.getValue(), toCombo.getValue());
        resultLabel.setText(result);
        if (result.contains("Please") || result.contains("Invalid") || result.contains("negative")) {
            resultLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold; -fx-background-color: #ffe6e6;");
        } else {
            resultLabel.setStyle("-fx-text-fill: green; -fx-font-weight: bold; -fx-background-color: #e6ffe6;");
        }
    }
}