package org.example.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Currency")
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "abbreviation", nullable = false, length = 3, unique = true)
    private String abbreviation;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "rate_to_usd", nullable = false)
    private double rateToUsd;

    public Currency() {
    }

    public Currency(int id, String abbreviation, String name, double rateToUsd) {
        this.id = id;
        this.abbreviation = abbreviation;
        this.name = name;
        this.rateToUsd = rateToUsd;
    }

    public Currency(String abbreviation, String name, double rateToUsd) {
        this.abbreviation = abbreviation;
        this.name = name;
        this.rateToUsd = rateToUsd;
    }

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

