package org.example.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.controller.CurrencyController;

import java.util.List;
import java.util.regex.Pattern;


public class CurrencyView extends Application {

    private CurrencyController controller;
    private TextField amountField;
    private ComboBox<String> fromCombo;
    private ComboBox<String> toCombo;
    private Label resultLabel;
    private Label statusLabel;

    @Override
    public void init() {
        controller = new CurrencyController();
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Currency Converter - Database Edition");
        stage.setWidth(550);
        stage.setHeight(450);

        VBox root = new VBox(20);
        root.setPadding(new Insets(30));
        root.setAlignment(Pos.TOP_CENTER);
        root.setStyle("-fx-background-color: #f5f5f5;");

        // Title
        Label title = new Label("💱 Currency Converter");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        // Subtitle
        Label subtitle = new Label("Exchange rates loaded from database");
        subtitle.setStyle("-fx-font-size: 12px; -fx-text-fill: #7f8c8d;");

        // Status label for database connection
        statusLabel = new Label();
        statusLabel.setStyle("-fx-font-size: 11px; -fx-padding: 5px 10px;");

        // Check database connection and populate currencies
        List<String> currencies = controller.getCurrencyAbbreviations();
        if (currencies.isEmpty()) {
            statusLabel.setText("⚠ Database connection failed: " +
                (controller.getLastError() != null ? controller.getLastError() : "Unknown error"));
            statusLabel.setStyle("-fx-font-size: 11px; -fx-text-fill: white; -fx-background-color: #e74c3c; -fx-padding: 5px 10px;");
        } else {
            statusLabel.setText("✓ Connected to database (" + currencies.size() + " currencies loaded)");
            statusLabel.setStyle("-fx-font-size: 11px; -fx-text-fill: white; -fx-background-color: #27ae60; -fx-padding: 5px 10px;");
        }

        // Grid for input fields
        GridPane grid = new GridPane();
        grid.setHgap(15);
        grid.setVgap(15);
        grid.setAlignment(Pos.CENTER);

        // Amount input
        Label amountLabel = new Label("Amount:");
        amountLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
        amountField = new TextField();
        amountField.setPromptText("Enter amount (e.g., 100.00)");
        amountField.setPrefWidth(200);
        amountField.setStyle("-fx-font-size: 14px; -fx-padding: 8px;");
        // Allows only numbers and one decimal point
        amountField.textProperty().addListener((obs, old, newVal) -> {
            if (!Pattern.matches("\\d*\\.?\\d*", newVal)) {
                amountField.setText(old);
            }
        });
        grid.add(amountLabel, 0, 0);
        grid.add(amountField, 1, 0);

        // From currency
        Label fromLabel = new Label("From:");
        fromLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
        fromCombo = new ComboBox<>();
        fromCombo.setPrefWidth(200);
        fromCombo.setStyle("-fx-font-size: 14px;");
        if (!currencies.isEmpty()) {
            fromCombo.getItems().addAll(currencies);
            fromCombo.setValue("USD");
        } else {
            fromCombo.setDisable(true);
        }
        grid.add(fromLabel, 0, 1);
        grid.add(fromCombo, 1, 1);

        // To currency
        Label toLabel = new Label("To:");
        toLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
        toCombo = new ComboBox<>();
        toCombo.setPrefWidth(200);
        toCombo.setStyle("-fx-font-size: 14px;");
        if (!currencies.isEmpty()) {
            toCombo.getItems().addAll(currencies);
            toCombo.setValue("EUR");
        } else {
            toCombo.setDisable(true);
        }
        grid.add(toLabel, 0, 2);
        grid.add(toCombo, 1, 2);

        // Swap button
        Button swapButton = new Button("⇄ Swap");
        swapButton.setStyle("-fx-background-color: #95a5a6; -fx-text-fill: white; -fx-font-size: 12px; -fx-padding: 5px 10px;");
        swapButton.setOnAction(e -> {
            String temp = fromCombo.getValue();
            fromCombo.setValue(toCombo.getValue());
            toCombo.setValue(temp);
        });
        if (currencies.isEmpty()) {
            swapButton.setDisable(true);
        }

        // Convert button
        Button convertBtn = new Button("Convert");
        convertBtn.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 16px; -fx-padding: 10px 30px;");
        convertBtn.setOnAction(e -> performConversion());
        if (currencies.isEmpty()) {
            convertBtn.setDisable(true);
        }

        HBox buttonBox = new HBox(15, swapButton, convertBtn);
        buttonBox.setAlignment(Pos.CENTER);

        // Result label
        resultLabel = new Label("Enter an amount and click Convert");
        resultLabel.setStyle("-fx-font-size: 18px; -fx-padding: 20px; -fx-background-color: white; " +
                "-fx-border-color: #bdc3c7; -fx-border-radius: 5px; -fx-background-radius: 5px;");
        resultLabel.setMinWidth(400);
        resultLabel.setAlignment(Pos.CENTER);

        // Add all components to root
        root.getChildren().addAll(title, subtitle, statusLabel, grid, buttonBox, resultLabel);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setOnCloseRequest(e -> controller.shutdown());
        stage.show();
    }

    /**
     * Performs the currency conversion and updates the result label.
     */
    private void performConversion() {
        String result = controller.convert(
                amountField.getText(),
                fromCombo.getValue(),
                toCombo.getValue()
        );

        resultLabel.setText(result);

        // Checks if result is an error message
        if (controller.getLastError() != null) {
            resultLabel.setStyle("-fx-font-size: 16px; -fx-padding: 20px; -fx-background-color: #ffe6e6; " +
                    "-fx-border-color: #e74c3c; -fx-border-radius: 5px; -fx-background-radius: 5px; " +
                    "-fx-text-fill: #c0392b; -fx-font-weight: bold;");
        } else {
            resultLabel.setStyle("-fx-font-size: 18px; -fx-padding: 20px; -fx-background-color: #e8f8f5; " +
                    "-fx-border-color: #27ae60; -fx-border-radius: 5px; -fx-background-radius: 5px; " +
                    "-fx-text-fill: #1e8449; -fx-font-weight: bold;");
        }
    }

    @Override
    public void stop() {
        controller.shutdown();
    }
}

