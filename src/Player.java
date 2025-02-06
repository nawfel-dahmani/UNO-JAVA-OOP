import java.util.ArrayList;

import org.fusesource.jansi.ConsoleColors;

public abstract class Player {
    protected int playerID;
    protected Deck thisDeck;
    protected Game thisGame;
    protected ArrayList<Card> hand;

    public Player(int playerID, Deck sharedDeck, Game sharedGame) {
        this.playerID = playerID;
        thisDeck = sharedDeck;
        thisGame = sharedGame;
        this.hand = new ArrayList<>();
    }
    

    public void dealHand(int numberOfCards) {
        ArrayList<Card> deck = thisDeck.initialistion();
        for (int i = 0; i < numberOfCards; i++) {
            if (!thisDeck.chek_empty_Deck(deck) && thisDeck.chekposible_draw(deck, 1)) {
                ArrayList<Card> drawnCards = thisDeck.Draw(deck, 1);
                hand.add(drawnCards.get(0));
            } else {
                break;
            }
        }
    }

    public ArrayList<Card> createHandToPlay() {
        ArrayList<Card> playableCards = new ArrayList<>();
        Card currentCard = thisGame.getCurrentCard(); // Get the current card on the table
        for (Card card : hand) {
            if (canPlayCard(card, currentCard)) {
                playableCards.add(card);
            }
        }
        return playableCards;
    }

    public boolean canPlayCard(Card card, Card topCard) {
        return card.getColor() == topCard.getColor() ||
               card.getNumber() == topCard.getNumber() ||
               card.getColor() == Card.Color.wild;
    }

    public abstract void takeTurn(); // Abstract method for taking a turn

    public void playCard(Card card) {
        if (hand.remove(card)) {
            thisGame.setCurrentCard(card);
            applySpecialCardEffect(card);
            sayUno();
        }
    }

    public void drawACard() {
        ArrayList<Card> deck = thisDeck.initialistion();
        if (!deck.isEmpty()) {
            Card drawnCard = deck.remove(0);
            hand.add(drawnCard);
        }
    }

    public void applySpecialCardEffect(Card card) {
        switch (card.getNumber()) {
            case skip:
                thisGame.skipNextPlayer(); // Skip the next player's turn
                break;
            case Reveres:
                thisGame.reverseTurnOrder(); // Reverse the turn order
                break;
            case DrawTwo:
                thisGame.drawCardsForNextPlayer(2); // Force the next player to draw 2 card
                thisGame.skipNextPlayer(); // Skip the next player's turn
                break;
            case wild:
                thisGame.changeColor(); // Allow the player to change the color
                break;
            case wild_four:
                thisGame.changeColor(); // Allow the player to change the color
                thisGame.drawCardsForNextPlayer(4); // Force the next player to draw 4 card
                thisGame.skipNextPlayer(); // Skip the next player's turn
                break;
            default:
                // Do nothing for other cards
                break;
        }
    }

    public boolean hasCardsLeft() {
        return !hand.isEmpty();
    }

    public void sayUno() {
        if (hand.size() == 1) {
            System.out.println("Player " + playerID + " says Uno!");
        }
    }

    public void showHand() {
        System.out.println("Your hand:");
        for (Card card : hand) {
            String colorCode = getColorCode(card.getColor());
            System.out.println(colorCode + card + ConsoleColors.RESET);
        }
    }
    
    public int getPlayerID() {
        return playerID;
    }
    
    public ArrayList<Card> getHand() {
        return hand;
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
}
