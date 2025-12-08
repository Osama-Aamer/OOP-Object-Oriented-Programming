
-- 1. basically the idea is to drop the previous version of the database if it exists
DROP DATABASE IF EXISTS currency_converter;

CREATE DATABASE currency_converter;

USE currency_converter;

-- Create a table for storing the Currency objects
CREATE TABLE currency (
    id INT NOT NULL AUTO_INCREMENT,
    abbreviation VARCHAR(3) NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL,
    rate_to_usd DECIMAL(15, 6) NOT NULL,
    PRIMARY KEY (id)
);

--
-- Exchange rates are relative to USD (matching Assignment 6.2's CurrencyModel)
INSERT INTO currency (abbreviation, name, rate_to_usd) VALUES
    ('USD', 'US Dollar', 1.000000),
    ('EUR', 'Euro', 0.940000),
    ('GBP', 'British Pound', 0.820000),
    ('JPY', 'Japanese Yen', 148.500000),
    ('CAD', 'Canadian Dollar', 1.380000),
    ('AUD', 'Australian Dollar', 1.550000),
    ('INR', 'Indian Rupee', 83.900000),
    ('CHF', 'Swiss Franc', 0.880000),
    ('CNY', 'Chinese Yuan', 7.240000),
    ('SEK', 'Swedish Krona', 10.450000);

-- Drop the user account appuser, if it exists
DROP USER IF EXISTS 'appuser'@'localhost';

-- Create the user account appuser
CREATE USER 'appuser'@'localhost' IDENTIFIED BY 'apppassword';

-- Grant privileges to the user account appuser
-- The application needs to: read currencies (SELECT), add new currencies (INSERT),
-- update exchange rates (UPDATE), and remove currencies (DELETE)
GRANT SELECT, INSERT, UPDATE, DELETE ON currency_converter.* TO 'appuser'@'localhost';

-- Apply the privilege changes
FLUSH PRIVILEGES;

-- Verify the setup by showing the currencies
SELECT * FROM currency;
