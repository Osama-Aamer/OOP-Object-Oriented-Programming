import java.util.Scanner;

public class Task3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the size of the array: ");
        int n = scanner.nextInt();

        int[] original = new int[n];
        System.out.println("Enter the integers into the array:");
        for (int i = 0; i < n; i++) {
            System.out.print("Enter integer " + (i + 1) + ": ");
            original[i] = scanner.nextInt();
        }


        boolean[] seen = new boolean[10001];
        int[] unique = new int[n];
        int uniqueCount = 0;

        for (int num : original) {
            int index = num + 5000;
            if (!seen[index]) {
                seen[index] = true;
                unique[uniqueCount++] = num;
            }
        }

        System.out.println("The array without duplicates:");
        for (int i = 0; i < uniqueCount; i++) {
            System.out.print(unique[i]);
            if (i < uniqueCount - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();

    }
}