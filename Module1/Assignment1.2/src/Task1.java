import java.util.Scanner;
public class Task1 {

    public static void main(String[] args) {

        Scanner scanner =new Scanner(System.in);

        System.out.println("Enter the temperature in Fahrenheit");
        double fahrenheit = scanner.nextDouble();

        double celsius = (fahrenheit - 32) * 5 / 9;

        System.out.printf("The temperature in Celsius is %.1f\n", celsius);
    }

}
