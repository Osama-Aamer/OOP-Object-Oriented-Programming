package org.example.controller;

import org.example.dao.CurrencyDao;
import org.example.datasource.MariaDbJpaConnection;
import org.example.entity.Currency;

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
            lastError = MariaDbJpaConnection.getLastError();
        }
        return available;
    }


    public String getLastError() {
        return lastError;
    }

    /**
     * Gets all currency abbreviations from the database.
     * @return list of currency abbreviations
     */
    public List<String> getCurrencyAbbreviations() {
        return currencyDao.getAllCurrencyAbbreviations();
    }

    /**
     * Gets all currencies from the database.
     * @return list of all currencies
     */
    public List<Currency> getAllCurrencies() {
        return currencyDao.findAll();
    }

    /**
     * Converts an amount from one currency to another.
     * Fetches exchange rates from the database using JPA.
     *
     * amountText = the amount to convert as a string
     *  fromCurrency = the source currency abbreviation
     *  toCurrency = the target currency abbreviation
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

        // Fetch exchange rates from database using JPA
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
     * Adds a new currency to the database.
     * @param abbreviation the currency abbreviation (e.g., "GBP")
     * @param name =    the currency name (e.g., "British Pound")
     * @param rateToUsd =  the exchange rate to USD
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

