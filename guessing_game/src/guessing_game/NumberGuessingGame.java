package guessing_game;
import java.util.Scanner;
import java.util.Random;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Random rand = new Random();
        int secretNumber = rand.nextInt(100) + 1; // random number between 1 and 100
        int guess;
        int attempts = 0;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("I have selected a number between 1 and 100.");

        while (true) {
            System.out.print("Enter your guess: ");
            
            // check if input is an integer
            if (!scanner.hasNextInt()) {
                System.out.println("Please enter a valid number.");
                scanner.next(); // discard invalid input
                continue;
            }

            guess = scanner.nextInt();
            attempts++;

            if (guess < secretNumber) {
                System.out.println("Too low! Try again.");
            } else if (guess > secretNumber) {
                System.out.println("Too high! Try again.");
            } else {
                System.out.println("Congratulations! You guessed the number in " + attempts + " attempts.");
                break;
            }
        }

        scanner.close();
    }
}
