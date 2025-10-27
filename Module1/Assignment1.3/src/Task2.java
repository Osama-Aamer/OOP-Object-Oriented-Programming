import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a binary number: ");
        String binary = scanner.nextLine();

        int decimal = 0;
        for (int i = 0; i < binary.length(); i++) {
            char bit = binary.charAt(i);
            if (bit == '1') {
                decimal += Math.pow(2, binary.length() - 1 - i);
            } else if (bit != '0') {
                System.out.println("Invalid binary number. Please use only 0s and 1s.");
                scanner.close();
                return;
            }
        }

        System.out.println("The decimal equivalent is: " + decimal);
    }
}