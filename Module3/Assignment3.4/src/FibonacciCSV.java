// Task2
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FibonacciCSV {
    public static void main(String[] args) {
        int n = 60;
        long[] fib = new long[n];
        fib[0] = 0;
        fib[1] = 1;

        for (int i = 2; i < n; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter("fibonacci.csv"))) {
            writer.println("Index,Fibonacci Number");

            for (int i = 0; i < n; i++) {
                writer.printf("%d,%d%n", i, fib[i]);
            }

            System.out.println("Fibonacci sequence written to fibonacci.csv");
            System.out.println("F60: " + fib[59]);

        } catch (IOException e) {
            System.err.println("Error writing CSV: " + e.getMessage());
        }
    }
}