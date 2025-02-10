import java.util.ArrayList;

public class ComputerPlayer extends Player {
    public ComputerPlayer(int playerID, Deck sharedDeck, Game sharedGame) {
        super(playerID, sharedDeck, sharedGame);
    }

    @Override
    public void takeTurn() {
        try {
            Thread.sleep(1000); // Simulate thinking time
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ArrayList<Card> playableCards = createHandToPlay();
        if (playableCards.isEmpty()) {
            System.out.println("Bot has no playable cards. Drawing a card...");
            drawACard();
        } else {
            Card selectedCard = playableCards.get(0); // Boot plays the first playable card
            System.out.println("Bot plays: " + selectedCard);
            playCard(selectedCard);
        }
    }
}
