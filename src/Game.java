import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.fusesource.jansi.ConsoleColors;

public class Game {
    private Deck deck;
    private List<Player> players;
    private int currentPlayerIndex;
    private boolean reverseOrder;
    private Card currentCard;

    public Game(int numberOfPlayers) {
        this.deck = new Deck(null, null); // Initialize the deck
        this.players = new ArrayList<>();
        this.currentPlayerIndex = 0;
        this.reverseOrder = false;
        initializePlayers(numberOfPlayers);

        // Initialize the deck and draw the first card
        ArrayList<Card> deckCards = deck.initialistion(); // Initialize and shuffle the deck
        if (!deckCards.isEmpty()) {
            // Ensure the starting card is a non-wild card
            for (Card card : deckCards) {
                if (card.getColor() != Card.Color.wild) {
                    this.currentCard = card; // Set the first non-wild card as the starting card
                    deckCards.remove(card); // Remove the card from the deck
                    break;
                }
            }
        } else {
            throw new IllegalStateException("The deck is empty. Cannot start the game.");
        }
    }
    
    public void showCurrentCard() {
        String colorCode = getColorCode(currentCard.getColor());
        System.out.println("The current top card is: " + colorCode + currentCard + ConsoleColors.RESET);
    }

    private String getColorCode(Card.Color color) {
        switch (color) {
            case blue:
                return ConsoleColors.BLUE;
            case red:
                return ConsoleColors.RED;
            case green:
                return ConsoleColors.GREEN;
            case yellow:
                return ConsoleColors.YELLOW;
            case wild:
                return ConsoleColors.MAGENTA;
            default:
                return ConsoleColors.WHITE; // Default color
        }
    }

    private void initializePlayers(int numberOfPlayers) {
    	Scanner sc = new Scanner(System.in);
    	int choice ;
        for (int i = 0; i < numberOfPlayers; i++) {
        	System.out.println("choose 0 human or 1 boot for Player "+(i+1)+":");
        	choice = sc.nextInt();
        	if (choice == 0) {
                players.add(new HumanPlayer(i + 1, deck, this));
            } else {
                players.add(new ComputerPlayer(i + 1, deck, this));
            }
        }
    }

    public void startGame() {
        // Deal 7 cards to each player
        for (Player player : players) {
            player.dealHand(7);
        }

        // Start the game loop
        while (true) {
            Player currentPlayer = players.get(currentPlayerIndex);
            System.out.println("------Player " + currentPlayer.getPlayerID() + "'s turn.-------");
            showCurrentCard(); // Display the current card
            currentPlayer.takeTurn();

            if (!currentPlayer.hasCardsLeft()) {
                System.out.println("**Player " + currentPlayer.getPlayerID() + " wins!**");
                break;
            }

            nextPlayer(); // Move to the next player
        }
    }

    public void nextPlayer() {
        if (reverseOrder) {
            currentPlayerIndex = (currentPlayerIndex - 1 + players.size()) % players.size();
        } else {
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        }
    }

    public void skipNextPlayer() {
        nextPlayer(); // Move to the next player
    }

    public void reverseTurnOrder() {
        reverseOrder = !reverseOrder;
    }
    
    public Player getNextPlayer() {
        int nextIndex;
        if (reverseOrder) {
            // Move to the previous player in reverse order
            nextIndex = (currentPlayerIndex - 1 + players.size()) % players.size();
        } else {
            // Move to the next player in forward order
            nextIndex = (currentPlayerIndex + 1) % players.size();
        }
        return players.get(nextIndex);
    }

    public void drawCardsForNextPlayer(int numberOfCards) {
        Player nextPlayer = getNextPlayer(); // Get the next player
        for (int i = 0; i < numberOfCards; i++) {
            nextPlayer.drawACard(); // Force the next player to draw a card
        }
        System.out.println("Player " + nextPlayer.getPlayerID() + " draws " + numberOfCards + " cards.");
    }

    public void changeColor() {
        Player currentPlayer = players.get(currentPlayerIndex);
        if (currentPlayer instanceof HumanPlayer) {
            System.out.println("Choose a color:");
            Card.Color[] colors = Card.Color.values();
            for (int i = 0; i < colors.length; i++) {
                if (colors[i] != Card.Color.wild) {
                    System.out.println((i + 1) + ". " + colors[i]);
                }
            }

            Scanner scanner = new Scanner(System.in);
            int choice;
            do {
                System.out.print("Enter your choice (1-" + (colors.length - 1) + "): ");
                choice = scanner.nextInt();
            } while (choice < 1 || choice > colors.length - 1);

            Card.Color selectedColor = colors[choice - 1];
            currentCard = new Card(selectedColor, currentCard.getNumber());
            System.out.println("Color changed to " + selectedColor + ".");
        } else {
            Card.Color[] colors = {Card.Color.blue, Card.Color.red, Card.Color.green, Card.Color.yellow};
            Card.Color selectedColor = colors[(int) (Math.random() * colors.length)];
            currentCard = new Card(selectedColor, currentCard.getNumber());
            System.out.println("Computer chose " + selectedColor + " as the new color.");
        }
    }

    public Card getCurrentCard() {
        return currentCard;
    }

    public void setCurrentCard(Card card) {
        this.currentCard = card;
    }

}