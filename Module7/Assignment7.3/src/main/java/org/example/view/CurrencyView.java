package org.example.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
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
        stage.setTitle("Currency Converter - JPA Edition");
        stage.setWidth(600);
        stage.setHeight(500);

        VBox root = new VBox(20);
        root.setPadding(new Insets(30));
        root.setAlignment(Pos.TOP_CENTER);
        root.setStyle("-fx-background-color: #f5f5f5;");

        // Title
        Label title = new Label("Currency Converter");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        // Subtitle
        Label subtitle = new Label("Exchange rates loaded from database using JPA/Hibernate");
        subtitle.setStyle("-fx-font-size: 12px; -fx-text-fill: #7f8c8d;");

        // Status label for database connection
        statusLabel = new Label();
        statusLabel.setStyle("-fx-font-size: 11px; -fx-padding: 5px 10px;");

        // Check database connection and populate currencies
        List<String> currencies = controller.getCurrencyAbbreviations();
        updateStatusLabel(currencies);

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
        Button swapButton = new Button("Swap");
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

        // Add Currency button
        Button addCurrencyBtn = new Button("+ Add Currency");
        addCurrencyBtn.setStyle("-fx-background-color: #27ae60; -fx-text-fill: white; -fx-font-size: 12px; -fx-padding: 5px 15px;");
        addCurrencyBtn.setOnAction(e -> openAddCurrencyWindow());

        HBox buttonBox = new HBox(15, swapButton, convertBtn, addCurrencyBtn);
        buttonBox.setAlignment(Pos.CENTER);

        // Result label
        resultLabel = new Label("Enter an amount and click Convert");
        resultLabel.setStyle("-fx-font-size: 18px; -fx-padding: 20px; -fx-background-color: white; " +
                "-fx-border-color: #bdc3c7; -fx-border-radius: 5px; -fx-background-radius: 5px;");
        resultLabel.setMinWidth(450);
        resultLabel.setAlignment(Pos.CENTER);

        // Add all components to root
        root.getChildren().addAll(title, subtitle, statusLabel, grid, buttonBox, resultLabel);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setOnCloseRequest(e -> controller.shutdown());
        stage.show();
    }

    /**
     * Updates the status label based on database connection status.
     */
    private void updateStatusLabel(List<String> currencies) {
        if (currencies.isEmpty()) {
            statusLabel.setText("Database connection failed: " +
                    (controller.getLastError() != null ? controller.getLastError() : "Unknown error"));
            statusLabel.setStyle("-fx-font-size: 11px; -fx-text-fill: white; -fx-background-color: #e74c3c; -fx-padding: 5px 10px;");
        } else {
            statusLabel.setText("Connected to database via JPA (" + currencies.size() + " currencies loaded)");
            statusLabel.setStyle("-fx-font-size: 11px; -fx-text-fill: white; -fx-background-color: #27ae60; -fx-padding: 5px 10px;");
        }
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

    /**
     * Opens a new window for adding a new currency.
     */
    private void openAddCurrencyWindow() {
        // Create a new stage (window)
        Stage newStage = new Stage();
        newStage.setTitle("Add New Currency");
        newStage.initModality(Modality.APPLICATION_MODAL); // Block input to other windows

        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #f5f5f5;");

        // Title
        Label windowTitle = new Label("Add New Currency");
        windowTitle.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        // Form grid
        GridPane formGrid = new GridPane();
        formGrid.setHgap(10);
        formGrid.setVgap(10);
        formGrid.setAlignment(Pos.CENTER);

        // Abbreviation field
        Label abbrLabel = new Label("Abbreviation:");
        abbrLabel.setStyle("-fx-font-weight: bold;");
        TextField abbrField = new TextField();
        abbrField.setPromptText("e.g., GBP");
        abbrField.setPrefWidth(150);
        // Limit to 3 uppercase letters
        abbrField.textProperty().addListener((obs, old, newVal) -> {
            if (newVal.length() > 3) {
                abbrField.setText(old);
            } else {
                abbrField.setText(newVal.toUpperCase());
            }
        });
        formGrid.add(abbrLabel, 0, 0);
        formGrid.add(abbrField, 1, 0);

        // Name field
        Label nameLabel = new Label("Currency Name:");
        nameLabel.setStyle("-fx-font-weight: bold;");
        TextField nameField = new TextField();
        nameField.setPromptText("e.g., British Pound");
        nameField.setPrefWidth(150);
        formGrid.add(nameLabel, 0, 1);
        formGrid.add(nameField, 1, 1);

        // Rate field
        Label rateLabel = new Label("Rate to USD:");
        rateLabel.setStyle("-fx-font-weight: bold;");
        TextField rateField = new TextField();
        rateField.setPromptText("e.g., 0.79");
        rateField.setPrefWidth(150);
        // Allow only numbers and decimal point
        rateField.textProperty().addListener((obs, old, newVal) -> {
            if (!Pattern.matches("\\d*\\.?\\d*", newVal)) {
                rateField.setText(old);
            }
        });
        formGrid.add(rateLabel, 0, 2);
        formGrid.add(rateField, 1, 2);

        // Status/Error label
        Label addStatusLabel = new Label();
        addStatusLabel.setStyle("-fx-font-size: 12px;");

        // Buttons
        Button saveBtn = new Button("Save");
        saveBtn.setStyle("-fx-background-color: #27ae60; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 8px 20px;");

        Button cancelBtn = new Button("Cancel");
        cancelBtn.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-padding: 8px 20px;");
        cancelBtn.setOnAction(e -> newStage.close());

        saveBtn.setOnAction(e -> {
            String abbr = abbrField.getText().trim();
            String name = nameField.getText().trim();
            String rateText = rateField.getText().trim();

            // Validate
            if (abbr.isEmpty() || name.isEmpty() || rateText.isEmpty()) {
                addStatusLabel.setText("Please fill in all fields");
                addStatusLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #e74c3c;");
                return;
            }

            double rate;
            try {
                rate = Double.parseDouble(rateText);
                if (rate <= 0) {
                    addStatusLabel.setText("Rate must be a positive number");
                    addStatusLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #e74c3c;");
                    return;
                }
            } catch (NumberFormatException ex) {
                addStatusLabel.setText("Invalid rate format");
                addStatusLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #e74c3c;");
                return;
            }

            // Add currency via controller
            boolean success = controller.addCurrency(abbr, name, rate);
            if (success) {
                addStatusLabel.setText("Currency added successfully!");
                addStatusLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #27ae60;");
                // Close after short delay
                newStage.close();
            } else {
                addStatusLabel.setText(controller.getLastError());
                addStatusLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #e74c3c;");
            }
        });

        HBox buttonBox = new HBox(10, saveBtn, cancelBtn);
        buttonBox.setAlignment(Pos.CENTER);

        layout.getChildren().addAll(windowTitle, formGrid, addStatusLabel, buttonBox);

        Scene scene = new Scene(layout, 350, 250);
        newStage.setScene(scene);

        // Use showAndWait so main window waits for this to close
        newStage.showAndWait();

        // After the add window closes, refresh the currency list
        refreshCurrencyList();
    }

    /**
     * Refreshes the currency lists in the combo boxes.
     */
    private void refreshCurrencyList() {
        List<String> currencies = controller.getCurrencyAbbreviations();

        // Save current selections
        String selectedFrom = fromCombo.getValue();
        String selectedTo = toCombo.getValue();

        // Update combo boxes
        fromCombo.getItems().clear();
        toCombo.getItems().clear();

        if (!currencies.isEmpty()) {
            fromCombo.getItems().addAll(currencies);
            toCombo.getItems().addAll(currencies);
            fromCombo.setDisable(false);
            toCombo.setDisable(false);

            // Restore selections if they still exist
            if (currencies.contains(selectedFrom)) {
                fromCombo.setValue(selectedFrom);
            } else {
                fromCombo.setValue(currencies.get(0));
            }

            if (currencies.contains(selectedTo)) {
                toCombo.setValue(selectedTo);
            } else {
                toCombo.setValue(currencies.size() > 1 ? currencies.get(1) : currencies.get(0));
            }
        }

        // Update status label
        updateStatusLabel(currencies);
    }

    @Override
    public void stop() {
        controller.shutdown();
    }
}

