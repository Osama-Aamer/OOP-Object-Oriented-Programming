import java.util.Scanner;

public class Task3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter two positive integers (start and end, where start < end): ");
        int start = scanner.nextInt();
        int end = scanner.nextInt();

        if (start >= end || start < 0 || end < 0) {
            System.out.println("Invalid input: start must be less than end, and both must be non-negative.");
            scanner.close();
            return;
        }

        System.out.println("Prime numbers between " + start + " and " + end + ":");
        for (int num = start; num <= end; num++) {
            if (isPrime(num)) {
                System.out.print(num + " ");
            }
        }
        System.out.println();
    }

    // to check if a number is prime
    private static boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}