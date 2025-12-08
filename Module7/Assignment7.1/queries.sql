
USE currency_converter;

-- Query 1: Retrieve all the currencies from the database
SELECT * FROM currency;

-- Query 2: Retrieve the currency with the abbreviation EUR
SELECT * FROM currency WHERE abbreviation = 'EUR';

-- Query 3: Retrieve the number of currencies in the database
SELECT COUNT(*) AS total_currencies FROM currency;

-- Query 4: Retrieve the currency with the highest exchange rate (to USD)
SELECT * FROM currency WHERE rate_to_usd = (SELECT MAX(rate_to_usd) FROM currency);

