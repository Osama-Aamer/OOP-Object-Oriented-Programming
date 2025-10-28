import java.util.Scanner;
import java.util.Random;

public class Task1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        String[] firstNames = {"Alice", "Bob", "Charlie", "Diana", "Eve", "Frank", "Grace", "Henry"};
        String[] lastNames = {"Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis"};

        System.out.println("How many random names should be generated?");
        int count = scanner.nextInt();

        System.out.println("Generated names:");
        for (int i = 0; i < count; i++) {

            int firstIndex = random.nextInt(firstNames.length);

            int lastIndex = random.nextInt(lastNames.length);

            String fullName = firstNames[firstIndex] + " " + lastNames[lastIndex];
            System.out.println(fullName);
        }
    }
}