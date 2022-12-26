import java.util.Arrays;

public class Hand {
    private String[] cards;
    private int score = 0;
    private int numberOfCards = 0;

    /*
      This method is used to create a new hand.
      The number of cards of the created hand will be 0.
     */
    public Hand() {
        cards = new String[0];
    }

    /*
      This method adds the given card to the end of the hand.
      parameter card the card to be added to the end of the hand
     */
    public void addCard(String card) {
        // creates a new array with increasing the length of the cards array by 1
        cards = Arrays.copyOf(cards, cards.length + ProjectConstants.ONE);
        // assigns the given card to the last element of the created array
        cards[cards.length - ProjectConstants.ONE] = card;
    }

    /*
      This method deletes the hand in the specified index and returns the deleted card.
      parameter index the index of the card to be deleted
      @return deleted card, returned null if given index is invalid
     */
    public String removeCard(int index) {
        // returns null if index value is less than 0 or greater than the length of cards array
        if (index < ProjectConstants.ZERO || index >= cards.length) {
            return null;
        }

        // assigns the element in the given index of the cards array to the variable card
        String card = cards[index];
        // creates an array 1 less than the length of the cards array
        String[] newCards = new String[cards.length - ProjectConstants.ONE];
        // copies the part of the cards array up to the given index into the newCards array
        System.arraycopy(cards, ProjectConstants.ZERO, newCards,
                ProjectConstants.ZERO, index);
        // Copies the remainder of the given index of the cards array from the next element to the newCards array
        System.arraycopy(cards, index + ProjectConstants.ONE,
                newCards, index, cards.length - index - ProjectConstants.ONE);
        // replaces array cards with array newCards
        cards = newCards;
        // returns the deleted element
        return card;
    }

    /*
      This method returns the card at the end of the hand.
      return card at the end of the hand, returned null if the hand is empty
     */
    public String getLastCard() {
        return cards[cards.length - ProjectConstants.ONE];
    }



    /*
      This method returns the number of cards.
      return card count
     */
    public int getCardCount() {
        return cards.length;
    }

    /*
      This method returns the array of cards found.
      Array of cards with return
     */
    public String[] getCards() {
        return cards;
    }


    /*
     This method returns the number of cards it contains.

     Number of cards with @return
     */
    public int getNumberOfCards() {
        return numberOfCards;
    }



}