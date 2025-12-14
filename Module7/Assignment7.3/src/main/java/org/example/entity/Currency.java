package org.example.entity;

import jakarta.persistence.*;

/**Note for myself:
 * Entity class representing a Currency in the database.
 * Maps to the 'currency' table in the currency_converter database.
 * Uses JPA annotations for object-relational mapping.
 */
@Entity
@Table(name = "currency")
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "abbreviation", nullable = false, length = 3)
    private String abbreviation;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "rate_to_usd", nullable = false)
    private double rateToUsd;

    /**
     * Default constructor required by JPA.
     */
    public Currency() {
    }

    /**
     * Constructor for creating a Currency with all fields (used when retrieving from DB).
     */
    public Currency(int id, String abbreviation, String name, double rateToUsd) {
        this.id = id;
        this.abbreviation = abbreviation;
        this.name = name;
        this.rateToUsd = rateToUsd;
    }

    /**
     * Constructor for creating a new Currency (without id - for new currencies).
     */
    public Currency(String abbreviation, String name, double rateToUsd) {
        this.abbreviation = abbreviation;
        this.name = name;
        this.rateToUsd = rateToUsd;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRateToUsd() {
        return rateToUsd;
    }

    public void setRateToUsd(double rateToUsd) {
        this.rateToUsd = rateToUsd;
    }

    @Override
    public String toString() {
        return abbreviation + " - " + name;
    }
}

