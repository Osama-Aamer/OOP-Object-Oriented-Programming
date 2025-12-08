package org.example.datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MariaDbConnection {

    private static Connection conn = null;
    private static String lastError = null;

    // Database connection parameters
    private static final String DB_URL = "jdbc:mariadb://localhost:3306/currency_converter";
    private static final String DB_USER = "appuser";
    private static final String DB_PASSWORD = "apppassword";

    /**
     * Gets the database connection. Creates a new connection if one doesn't exist.
     * returns the Connection object, or null if connection failed
     */
    public static Connection getConnection() {
        if (conn == null) {
            try {
                conn = DriverManager.getConnection(
                        DB_URL + "?user=" + DB_USER + "&password=" + DB_PASSWORD);
                lastError = null;
                System.out.println("Database connection established successfully.");
            } catch (SQLException e) {
                lastError = "Database connection failed: " + e.getMessage();
                System.out.println(lastError);
                e.printStackTrace();
                return null;
            }
        }
        return conn;
    }

    /**
     * Checks if the database connection is available, true if connected or false otherwise
     */
    public static boolean isConnected() {
        try {
            return conn != null && !conn.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }

    /**
     * Gets the last error message, or null if no error
     */
    public static String getLastError() {
        return lastError;
    }

    /**
     * Terminates the database connection.
     */
    public static void terminate() {
        if (conn != null) {
            try {
                conn.close();
                conn = null;
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                System.out.println("Error closing database connection: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public static void resetConnection() {
        terminate();
        conn = null;
        lastError = null;
    }
}

