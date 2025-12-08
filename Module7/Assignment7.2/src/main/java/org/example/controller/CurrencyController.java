package org.example.controller;

import org.example.dao.CurrencyDao;
import org.example.datasource.MariaDbConnection;

import java.util.List;

public class CurrencyController {

    private final CurrencyDao currencyDao;
    private String lastError;

    public CurrencyController() {
        this.currencyDao = new CurrencyDao();
        this.lastError = null;
    }

    public boolean isDatabaseAvailable() {
        boolean available = currencyDao.isDatabaseAvailable();
        if (!available) {
            lastError = MariaDbConnection.getLastError();
        }
        return available;
    }

    public String getLastError() {
        return lastError;
    }

    /**
     * Gets all currency abbreviations from the database.
     */
    public List<String> getCurrencyAbbreviations() {
        return currencyDao.getAllCurrencyAbbreviations();
    }

    /**
     * Converts an amount from one currency to another.
     * Fetches exchange rates from the database.
     *
     * amountText = the amount to convert as a string
     * fromCurrency = the source currency abbreviation
     * toCurrency = the target currency abbreviation
     * returns the result message (either the converted amount or an error message)
     */
    public String convert(String amountText, String fromCurrency, String toCurrency) {
        // Validate input
        if (amountText == null || amountText.trim().isEmpty()) {
            lastError = "Please enter an amount";
            return lastError;
        }

        double amount;
        try {
            amount = Double.parseDouble(amountText.trim());
            if (amount < 0) {
                lastError = "Amount cannot be negative";
                return lastError;
            }
        } catch (NumberFormatException e) {
            lastError = "Invalid number format";
            return lastError;
        }

        // Check database availability
        if (!isDatabaseAvailable()) {
            lastError = "Database is not available. Please try again later.";
            return lastError;
        }

        // Fetch exchange rates from database
        double fromRate = currencyDao.getExchangeRate(fromCurrency);
        double toRate = currencyDao.getExchangeRate(toCurrency);

        if (fromRate < 0) {
            lastError = "Could not find exchange rate for " + fromCurrency;
            return lastError;
        }

        if (toRate < 0) {
            lastError = "Could not find exchange rate for " + toCurrency;
            return lastError;
        }

        // First convert to USD, then to target currency
        double amountInUsd = amount / fromRate;
        double result = amountInUsd * toRate;

        lastError = null;
        return String.format("%.2f %s = %.2f %s", amount, fromCurrency, result, toCurrency);
    }

    /**
     * Closes the database connection.
     */
    public void shutdown() {
        MariaDbConnection.terminate();
    }
}
