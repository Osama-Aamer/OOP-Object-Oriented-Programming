package org.example.dao;

import org.example.datasource.MariaDbConnection;
import org.example.entity.Currency;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Note for myself:
 * Data Access Object (DAO) class for Currency entities.
 * Handles all database operations related to currencies.
 */
public class CurrencyDao {

    /**
     * Retrieves the exchange rate of a currency from the database.
     * returns the exchange rate to USD, or -1 if not found or error occurred
     */
    public double getExchangeRate(String abbreviation) {
        Connection conn = MariaDbConnection.getConnection();
        if (conn == null) {
            return -1; // Database connection failed
        }

        String sql = "SELECT rate_to_usd FROM currency WHERE abbreviation = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, abbreviation);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getDouble("rate_to_usd");
            }
        } catch (SQLException e) {
            System.out.println("Error fetching exchange rate: " + e.getMessage());
            e.printStackTrace();
        }

        return -1; // Currency not found
    }

    /**
     * Retrieves all currencies from the database, or empty list if error occurred
     */
    public List<Currency> getAllCurrencies() {
        List<Currency> currencies = new ArrayList<>();
        Connection conn = MariaDbConnection.getConnection();

        if (conn == null) {
            return currencies;
        }

        String sql = "SELECT id, abbreviation, name, rate_to_usd FROM currency ORDER BY abbreviation";

        try {
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String abbreviation = rs.getString("abbreviation");
                String name = rs.getString("name");
                double rateToUsd = rs.getDouble("rate_to_usd");

                Currency currency = new Currency(id, abbreviation, name, rateToUsd);
                currencies.add(currency);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching currencies: " + e.getMessage());
            e.printStackTrace();
        }

        return currencies;
    }

    /**
     * Retrieves all currency abbreviations from the database, or an empty list if error occurred
     */
    public List<String> getAllCurrencyAbbreviations() {
        List<String> abbreviations = new ArrayList<>();
        Connection conn = MariaDbConnection.getConnection();

        if (conn == null) {
            return abbreviations;
        }

        String sql = "SELECT abbreviation FROM currency ORDER BY abbreviation";

        try {
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                abbreviations.add(rs.getString("abbreviation"));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching currency abbreviations: " + e.getMessage());
            e.printStackTrace();
        }

        return abbreviations;
    }

    /**
     * Retrieves a currency by its abbreviation, or null if not found
     */
    public Currency getCurrencyByAbbreviation(String abbreviation) {
        Connection conn = MariaDbConnection.getConnection();
        if (conn == null) {
            return null;
        }

        String sql = "SELECT id, abbreviation, name, rate_to_usd FROM currency WHERE abbreviation = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, abbreviation);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String abbr = rs.getString("abbreviation");
                String name = rs.getString("name");
                double rateToUsd = rs.getDouble("rate_to_usd");

                return new Currency(id, abbr, name, rateToUsd);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching currency: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Checks if the database is available.
     * returns true if the database is available, false otherwise
     */
    public boolean isDatabaseAvailable() {
        Connection conn = MariaDbConnection.getConnection();
        return conn != null;
    }
}

