import java.util.Scanner;

public class Classwork {
    public static void main(String[]args) {
       Scanner scanner =  new Scanner(System.in);

        System.out.println("Give a number:");
        /*int number = Integer.parseInt(scanner.nextLine());

        if (number > 0) {
            System.out.print("The number is positive");
 */
        int number = Integer.parseInt(scanner.nextLine());

        if (number == 5) {
            System.out.println("value is 5");
        } else if (number <0) {
            System.out.println("<0");
        } else {
            System.out.println("value is 0");
        }
        if ((number >0 && number <10) || number==-1) {
            System.out.println("The values is between 1 and 9, or it is -1.");
        }
    }
}
