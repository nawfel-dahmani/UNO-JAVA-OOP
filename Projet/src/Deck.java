import java.util.ArrayList;
import java.util.Collections;


public class Deck extends Card  {


    public Deck(Color color, Number number) {
        super(color, number);
    }

    public  ArrayList<Card> initialistion(){
        ArrayList<Card> DECK = new ArrayList<>();
        for(Card.Number nmbers : Card.Number.values()){

            if(nmbers != Card.Number.wild_four && nmbers != Card.Number.wild){
                for(Card.Color color : Card.Color.values()){
                    if(color != Card.Color.wild ){
                        DECK.add(new Card(color, nmbers));
                        if( nmbers != Card.Number.zero){
                            DECK.add(new Card(color, nmbers));
                        }
                    }


                }
            }
        }

        {
            DECK.add(new Card(Card.Color.wild, Card.Number.wild));
            DECK.add(new Card(Card.Color.wild, Card.Number.wild));
            DECK.add(new Card(Card.Color.wild, Card.Number.wild));
            DECK.add(new Card(Card.Color.wild, Card.Number.wild));
            DECK.add(new Card(Card.Color.wild, Card.Number.wild_four));
            DECK.add(new Card(Card.Color.wild, Card.Number.wild_four));
            DECK.add(new Card(Card.Color.wild, Card.Number.wild_four));
            DECK.add(new Card(Card.Color.wild, Card.Number.wild_four));
        }

        Collections.shuffle(DECK);
        return DECK ;
    }
    //first function is to check if the Deck is empty that means ya can not draw a cards
    public boolean chek_empty_Deck(ArrayList<Card> DECK){
        return DECK.isEmpty();
    }
    // this function tell you if you can draw a number of cards in the deck basic on the number of cards left
    public boolean chekposible_draw(ArrayList<Card> DECK,int nb_card_draw){
        return DECK.size() >= nb_card_draw;

    }
    //that draw certain number cards from the deck, but it should call  the
// checkposible_draw first and the return is a set of drawn cards

    public ArrayList<Card> Draw(ArrayList<Card> DECK,int nb_card_draw){
        ArrayList<Card> DECK_DRAW = new ArrayList<>();

        for (int i =0 ; i <nb_card_draw; i++){
            DECK_DRAW.add(DECK.get(i));
            DECK.remove(i);
        }
        Collections.shuffle(DECK_DRAW);

        return DECK_DRAW ;
    }
    //it shows the deck .the cards in it for safety code .
// or for just verifying the reliability but we the cards after that .
    public void show_deck (ArrayList<Card> DECK){
        System.out.println("the Cards of the Deck are : ");
        for (Card card : DECK) {

            System.out.println("" + card.getNumber() + "  " + card.getColor());
        }
        Collections.shuffle(DECK);
    }


}
