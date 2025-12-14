package org.example.util;

import org.example.dao.CurrencyDao;
import org.example.entity.Currency;

import java.util.List;

public class DataInitializer {

    private final CurrencyDao currencyDao;

    public DataInitializer() {
        this.currencyDao = new CurrencyDao();
    }

    public void initializeIfEmpty() {
        List<Currency> existing = currencyDao.findAll();
        if (existing.isEmpty()) {
            System.out.println("Database is empty. Initializing with default currencies...");
            initializeDefaultCurrencies();
        } else {
            System.out.println("Database already contains " + existing.size() + " currencies.");
        }
    }

    private void initializeDefaultCurrencies() {
        currencyDao.persist(new Currency("USD", "US Dollar", 1.000000));
        currencyDao.persist(new Currency("EUR", "Euro", 0.940000));
        currencyDao.persist(new Currency("GBP", "British Pound", 0.820000));
        currencyDao.persist(new Currency("JPY", "Japanese Yen", 148.500000));
        currencyDao.persist(new Currency("CAD", "Canadian Dollar", 1.380000));
        currencyDao.persist(new Currency("AUD", "Australian Dollar", 1.550000));
        currencyDao.persist(new Currency("INR", "Indian Rupee", 83.900000));
        currencyDao.persist(new Currency("CHF", "Swiss Franc", 0.880000));
        currencyDao.persist(new Currency("CNY", "Chinese Yuan", 7.240000));
        currencyDao.persist(new Currency("SEK", "Swedish Krona", 10.450000));
        System.out.println("Default currencies initialized successfully.");
    }
}
