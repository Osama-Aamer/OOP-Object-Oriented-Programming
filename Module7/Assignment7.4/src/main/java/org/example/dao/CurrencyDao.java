package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.example.datasource.MariaDbJpaConnection;
import org.example.entity.Currency;

import java.util.ArrayList;
import java.util.List;

public class CurrencyDao {

    public List<Currency> findAll() {
        List<Currency> currencies = new ArrayList<>();
        try {
            EntityManager em = MariaDbJpaConnection.getInstance();
            if (em == null) {
                return currencies;
            }
            TypedQuery<Currency> query = em.createQuery(
                    "SELECT c FROM Currency c ORDER BY c.abbreviation", Currency.class);
            currencies = query.getResultList();
        } catch (Exception e) {
            System.out.println("Error fetching currencies: " + e.getMessage());
            e.printStackTrace();
        }
        return currencies;
    }

    public Currency findByAbbreviation(String abbreviation) {
        try {
            EntityManager em = MariaDbJpaConnection.getInstance();
            if (em == null) {
                return null;
            }
            TypedQuery<Currency> query = em.createQuery(
                    "SELECT c FROM Currency c WHERE c.abbreviation = :abbr", Currency.class);
            query.setParameter("abbr", abbreviation);
            List<Currency> results = query.getResultList();
            if (!results.isEmpty()) {
                return results.get(0);
            }
        } catch (Exception e) {
            System.out.println("Error finding currency: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public double getExchangeRate(String abbreviation) {
        Currency currency = findByAbbreviation(abbreviation);
        if (currency != null) {
            return currency.getRateToUsd();
        }
        return -1;
    }

    public List<String> getAllCurrencyAbbreviations() {
        List<String> abbreviations = new ArrayList<>();
        try {
            EntityManager em = MariaDbJpaConnection.getInstance();
            if (em == null) {
                return abbreviations;
            }
            TypedQuery<String> query = em.createQuery(
                    "SELECT c.abbreviation FROM Currency c ORDER BY c.abbreviation", String.class);
            abbreviations = query.getResultList();
        } catch (Exception e) {
            System.out.println("Error fetching currency abbreviations: " + e.getMessage());
            e.printStackTrace();
        }
        return abbreviations;
    }

    public boolean persist(Currency currency) {
        try {
            EntityManager em = MariaDbJpaConnection.getInstance();
            if (em == null) {
                return false;
            }
            em.getTransaction().begin();
            em.persist(currency);
            em.getTransaction().commit();
            System.out.println("Currency persisted: " + currency.getAbbreviation());
            return true;
        } catch (Exception e) {
            System.out.println("Error persisting currency: " + e.getMessage());
            e.printStackTrace();
            try {
                EntityManager em = MariaDbJpaConnection.getInstance();
                if (em != null && em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
            } catch (Exception ex) {
                // Ignore rollback errors
            }
            return false;
        }
    }

    public boolean isDatabaseAvailable() {
        return MariaDbJpaConnection.isConnected();
    }
}

