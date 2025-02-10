import java.util.ArrayList;
import java.util.Scanner;

public class HumanPlayer extends Player {
    private static final Scanner scanner = new Scanner(System.in);

    public HumanPlayer(int playerID, Deck sharedDeck, Game sharedGame) {
        super(playerID, sharedDeck, sharedGame);
    }

    @Override
    public void takeTurn() {
        ArrayList<Card> playableCards = createHandToPlay();
        if (playableCards.isEmpty()) {
            System.out.println("No playable cards. Drawing a card...");
            drawACard();
        } else {
        	showHand();
            System.out.println("Your playable cards are:");
            for (int i = 0; i < playableCards.size(); i++) {
                System.out.println((i + 1) + ": " + playableCards.get(i));
            }
            int choice;
            do {
                System.out.print("Choose a card to play (1-" + playableCards.size() + "): ");
                choice = scanner.nextInt();
            } while (choice < 1 || choice > playableCards.size());
            playCard(playableCards.get(choice - 1));
        }
    }
}
