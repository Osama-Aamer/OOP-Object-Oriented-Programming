package model;

import java.util.LinkedHashMap;
import java.util.Map;

public class CurrencyModel {
    private final Map<String, Currency> currencies = new LinkedHashMap<>();

    public CurrencyModel() {
        // Hardcoded rates to USD (as allowed in the assignment)
        addCurrency("USD", "US Dollar", 1.0);
        addCurrency("EUR", "Euro", 0.94);
        addCurrency("GBP", "British Pound", 0.82);
        addCurrency("JPY", "Japanese Yen", 148.5);
        addCurrency("CAD", "Canadian Dollar", 1.38);
        addCurrency("AUD", "Australian Dollar", 1.55);
        addCurrency("INR", "Indian Rupee", 83.9);
    }

    private void addCurrency(String code, String name, double rateToUSD) {
        currencies.put(code, new Currency(code, name, rateToUSD));
    }

    public Map<String, Currency> getCurrencies() {
        return currencies;
    }

    public double convert(double amount, String fromCode, String toCode) {
        if (fromCode.equals(toCode)) return amount;
        double fromRate = currencies.get(fromCode).rateToUSD;
        double toRate = currencies.get(toCode).rateToUSD;
        return amount * (toRate / fromRate);
    }

    public record Currency(String code, String name, double rateToUSD) {}
}