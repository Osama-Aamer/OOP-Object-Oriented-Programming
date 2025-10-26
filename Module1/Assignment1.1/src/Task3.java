import java.util.Scanner;

public class Task3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Give the first number:");
        int first = Integer.parseInt(scanner.nextLine());

        System.out.println("Give the second number:");
        int second = Integer.parseInt(scanner.nextLine());

        System.out.println("Give the third number:");
        int third = Integer.parseInt(scanner.nextLine());

       //iteration1 without decimal
        /*
        System.out.println("The sum of the numbers is " + (first + second + third));
        System.out.println("The Product of the number is " + (first * second * third));
        System.out.println("The Average of the numbers is " + (first + second + third)/3);
        */

        int sum =  first + second + third;
        int product = first * second * third;
        //double average = sum / 3; (iteration2 wasnt givning 4.333 but 4.0)
        double average = (double) sum / 3; //iteration3, by casting sum to double before division ((double) sum / 3), we basically make Java perform floating-point division, so that we can get result in 4.3333333333333.
        //so that we get the decimal average,
        // we need to cast the sum to double before dividing by 3
        System.out.println("The sum of the numbers is " + sum );
        System.out.println("The Product of the number is " + product);
        System.out.println("The Average of the number is " + average);



    }
}