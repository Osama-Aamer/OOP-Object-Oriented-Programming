import java.util.Scanner;

public class Task3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Weight (g): ");
        double grams = scanner.nextDouble();

        final double LUOTI_TO_GRAMS = 13.28;
        final int NAULA_TO_LUOTI = 32;
        final int LEIVISKA_TO_NAULA = 20;

        double totalLuoti = grams / LUOTI_TO_GRAMS;

        int leiviska = (int) (totalLuoti / (LEIVISKA_TO_NAULA * NAULA_TO_LUOTI));

        double remainingLuoti = totalLuoti % (LEIVISKA_TO_NAULA * NAULA_TO_LUOTI);

        int naula = (int) (remainingLuoti / NAULA_TO_LUOTI);

        double luoti = remainingLuoti % NAULA_TO_LUOTI;

        System.out.printf("%.0f grams is %d leiviskä, %d naula, and %.2f luoti.\n",
                grams, leiviska, naula, luoti);

    }
}