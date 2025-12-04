package controller;

import model.CurrencyModel;

public class CurrencyController {
    private final CurrencyModel model;

    public CurrencyController() {
        this.model = new CurrencyModel();
    }

    public CurrencyModel getModel() {
        return model;
    }

    public String convert(String amountText, String from, String to) {
        if (amountText.isEmpty()) {
            return "Please enter an amount";
        }
        try {
            double amount = Double.parseDouble(amountText);
            if (amount < 0) return "Amount cannot be negative";
            double result = model.convert(amount, from, to);
            return String.format("%.2f", result);
        } catch (NumberFormatException e) {
            return "Invalid number format";
        }
    }
}