package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.example.datasource.MariaDbJpaConnection;
import org.example.entity.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TransactionDao {

    public boolean persist(Transaction transaction) {
        try {
            EntityManager em = MariaDbJpaConnection.getInstance();
            if (em == null) {
                return false;
            }
            em.getTransaction().begin();
            em.persist(transaction);
            em.getTransaction().commit();
            System.out.println("Transaction persisted with ID: " + transaction.getId());
            return true;
        } catch (Exception e) {
            System.out.println("Error persisting transaction: " + e.getMessage());
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

    public Transaction find(int id) {
        try {
            EntityManager em = MariaDbJpaConnection.getInstance();
            if (em == null) {
                return null;
            }
            return em.find(Transaction.class, id);
        } catch (Exception e) {
            System.out.println("Error finding transaction: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public List<Transaction> findAll() {
        List<Transaction> transactions = new ArrayList<>();
        try {
            EntityManager em = MariaDbJpaConnection.getInstance();
            if (em == null) {
                return transactions;
            }
            TypedQuery<Transaction> query = em.createQuery(
                    "SELECT t FROM Transaction t ORDER BY t.transactionDate DESC", Transaction.class);
            transactions = query.getResultList();
        } catch (Exception e) {
            System.out.println("Error fetching transactions: " + e.getMessage());
            e.printStackTrace();
        }
        return transactions;
    }
}

