import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("-----Welcome to UNO!-----");

        // Create a Scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to select the number of players
        int numberOfPlayers = selectNumberOfPlayers(scanner);
        

        // Initialize the game with the list of players
        Game game = new Game(numberOfPlayers);

        // Start the game
        game.startGame();

        // Close the scanner
        scanner.close();
    }

    // Method to select the number of players
    private static int selectNumberOfPlayers(Scanner scanner) {
        int numberOfPlayers = 0;
        while (numberOfPlayers < 2 || numberOfPlayers > 4) { // Ensure the number of players is valid
            System.out.print("Enter the number of players (2-4): ");
            if (scanner.hasNextInt()) {
                numberOfPlayers = scanner.nextInt();
                if (numberOfPlayers < 2 || numberOfPlayers > 4) {
                    System.out.println("Invalid number of players. Please enter a number between 2 and 4.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Clear the invalid input
            }
        }
        return numberOfPlayers;
    }
    
    
}