package org.example.datasource;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**Note for myself:
 * JPA Connection class for managing EntityManager instances.
 * Uses the singleton pattern to ensure only one EntityManagerFactory exists.
 */
public class MariaDbJpaConnection {

    private static EntityManagerFactory emf = null;
    private static EntityManager em = null;
    private static String lastError = null;

    /**
     * Gets the EntityManager instance. Creates a new one if it doesn't exist.
     * returns the EntityManager instance, or null if connection failed
     */
    public static EntityManager getInstance() {
        if (em == null || !em.isOpen()) {
            try {
                if (emf == null || !emf.isOpen()) {
                    emf = Persistence.createEntityManagerFactory("CurrencyConverterUnit");
                }
                em = emf.createEntityManager();
                lastError = null;
                System.out.println("JPA EntityManager created successfully.");
            } catch (Exception e) {
                lastError = "Failed to create EntityManager: " + e.getMessage();
                System.out.println(lastError);
                e.printStackTrace();
                return null;
            }
        }
        return em;
    }

    /**
     * Checks if the JPA connection is available.
     * returns true if connected, false otherwise
     */
    public static boolean isConnected() {
        try {
            EntityManager entityManager = getInstance();
            return entityManager != null && entityManager.isOpen();
        } catch (Exception e) {
            return false;
        }
    }

    public static String getLastError() {
        return lastError;
    }

    /**
     * Closes the EntityManager and EntityManagerFactory.
     */
    public static void terminate() {
        try {
            if (em != null && em.isOpen()) {
                em.close();
                System.out.println("EntityManager closed.");
            }
            if (emf != null && emf.isOpen()) {
                emf.close();
                System.out.println("EntityManagerFactory closed.");
            }
        } catch (Exception e) {
            System.out.println("Error closing JPA connection: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

