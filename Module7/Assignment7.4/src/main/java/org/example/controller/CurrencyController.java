package org.example.controller;

import org.example.dao.CurrencyDao;
import org.example.dao.TransactionDao;
import org.example.datasource.MariaDbJpaConnection;
import org.example.entity.Currency;
import org.example.entity.Transaction;

import java.util.List;


public class CurrencyController {

    private final CurrencyDao currencyDao;
    private final TransactionDao transactionDao;
    private String lastError;

    public CurrencyController() {
        this.currencyDao = new CurrencyDao();
        this.transactionDao = new TransactionDao();
        this.lastError = null;
    }

    /**
     * Checks if the database is available.
     * returns true if available, false otherwise
     */
    public boolean isDatabaseAvailable() {
        boolean available = currencyDao.isDatabaseAvailable();
        if (!available) {
            lastError = MariaDbJpaConnection.getLastError();
        }
        return available;
    }

    /**
     * Gets the last error message.
     * returns the last error message, or null if no error
     */
    public String getLastError() {
        return lastError;
    }

    /**
     * Gets all currency abbreviations from the database.
     * returns list of currency abbreviations
     */
    public List<String> getCurrencyAbbreviations() {
        return currencyDao.getAllCurrencyAbbreviations();
    }

    /**
     * Gets all currencies from the database.
     * return list of all currencies
     */
    public List<Currency> getAllCurrencies() {
        return currencyDao.findAll();
    }

    /**
     * Converts an amount from one currency to another.
     * Fetches exchange rates from the database using JPA.
     * STORES THE TRANSACTION IN THE DATABASE.
     *
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

        // Fetch Currency objects from database
        Currency sourceCurrencyObj = currencyDao.findByAbbreviation(fromCurrency);
        Currency targetCurrencyObj = currencyDao.findByAbbreviation(toCurrency);

        if (sourceCurrencyObj == null) {
            lastError = "Could not find currency: " + fromCurrency;
            return lastError;
        }

        if (targetCurrencyObj == null) {
            lastError = "Could not find currency: " + toCurrency;
            return lastError;
        }

        double fromRate = sourceCurrencyObj.getRateToUsd();
        double toRate = targetCurrencyObj.getRateToUsd();

        // First convert to USD, then to target currency
        double amountInUsd = amount / fromRate;
        double result = amountInUsd * toRate;

        // Store the transaction in the database
        Transaction transaction = new Transaction(sourceCurrencyObj, targetCurrencyObj, amount, result);
        boolean saved = transactionDao.persist(transaction);

        if (!saved) {
            System.out.println("Warning: Failed to save transaction to database");
        }

        lastError = null;
        return String.format("%.2f %s = %.2f %s", amount, fromCurrency, result, toCurrency);
    }

    /**
     * Adds a new currency to the database.
     *
     * returns true if successful, false otherwise
     */
    public boolean addCurrency(String abbreviation, String name, double rateToUsd) {
        // Validate input
        if (abbreviation == null || abbreviation.trim().isEmpty()) {
            lastError = "Abbreviation cannot be empty";
            return false;
        }
        if (name == null || name.trim().isEmpty()) {
            lastError = "Name cannot be empty";
            return false;
        }
        if (rateToUsd <= 0) {
            lastError = "Exchange rate must be positive";
            return false;
        }

        // Check if currency already exists
        Currency existing = currencyDao.findByAbbreviation(abbreviation.toUpperCase().trim());
        if (existing != null) {
            lastError = "Currency with abbreviation '" + abbreviation + "' already exists";
            return false;
        }

        // Create and persist the new currency
        Currency newCurrency = new Currency(
                abbreviation.toUpperCase().trim(),
                name.trim(),
                rateToUsd
        );

        boolean success = currencyDao.persist(newCurrency);
        if (!success) {
            lastError = "Failed to add currency to database";
        } else {
            lastError = null;
        }
        return success;
    }

    /**
     * Closes the database connection.
     */
    public void shutdown() {
        MariaDbJpaConnection.terminate();
    }
}

