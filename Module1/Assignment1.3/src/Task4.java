import java.util.Scanner;

public class Task4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            int score = 0;

            for (int i = 0; i < 10; i++) {
                int num1 = (int) (Math.random() * 10) + 1;
                int num2 = (int) (Math.random() * 10) + 1;

                System.out.printf("Question %d: What is %d * %d? ", (i + 1), num1, num2);
                int userAnswer = scanner.nextInt();

                int correctAnswer = num1 * num2;
                if (userAnswer == correctAnswer) {
                    System.out.println("Correct!");
                    score++;
                } else {
                    System.out.println("Incorrect. The correct answer is " + correctAnswer);
                }
            }

            System.out.println("Your score: " + score + "/10");

            if (score == 10) {
                System.out.println("Congratulations! You have mastered the multiplication tables!");
                break;
            } else {
                System.out.println("Try again with a new set of problems.");
            }
        }
    }
}