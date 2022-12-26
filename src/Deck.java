import java.util.Arrays;
import java.util.Random;

public class Deck {
    private String[] cards;
    /*
      Deck class holds 52 decks of cards.
      Constructor cards spades > clubs > hearts > diamonds
      Assigns it to the array.
      The indexes of the series are Ace for 1, Jacks for 11, Queen for 12,
      Represents King emoticons for 13.
     */
    public Deck() {
        cards = new String[ProjectConstants.DECKSIZE];
        int index = 0;
        // Initializes the array to hold the cards and the index of the array
        for (int i = 1; i <= ProjectConstants.CARDSIZE; i++) {
            // generates a number between 1 and 13
            String rank = getRank(i);
            // Creates a card value based on the value of this number
            // (For example, "Ace" for 1)
            cards[index++] = "♠" + rank;
            // Adds this card value to the array when creating pairs of hearts and spades
            cards[index++] = "♣" + rank;
            cards[index++] = "♥" + rank;
            cards[index++] = "♦" + rank;
        }
    }
    /*
      The purpose of this method is to return the value of a card as a string.

     This method takes an integer representing the value of a card and
      returns the corresponding value as a string. If the value is 1,
      method returns "Ace". If the value is 11, 12 or 13, the method is "Jack",
      Returns "Queen" or "King". For all other values, the method value
      returns as a string.

      Value of @param rank card
      @return card value as a string
     */
    private static String getRank(int rank) {
        return switch (rank) {
            case 1 -> "Ace";
            case 11 -> "Jack";
            case 12 -> "Queen";
            case 13 -> "King";
            default -> String.valueOf(rank);
        };
    }
    /*
      Draws and rotates the last card.
      This method pulls the last element of the cards array
      and returns. If the array of cards is empty, it returns null.
     */
    public String draw() {
        // If the array of cards is empty
        if (cards.length == 0) {
            return null; // returns null value
        }
        String card = cards[cards.length - 1]; // Gets the last element of the cards array.
        // Removes the last element of the cards array (copies and reduces the array's height by 1)
        cards = Arrays.copyOf(cards, cards.length - 1);
        // Returns the first drawn card.
        return card;
    }
    public void shuffle() {
        // Shows the cards before they were shuffled
        System.out.println(ProjectConstants.BEFORESHUFFLETEXT);
        System.out.println(Arrays.toString(cards));

        // A rand object is created to generate a random number
        Random rand = new Random();
        // Loops the length of the array of cards
        for (int i = 0; i < cards.length; i++) {
            // generate a random number
            int j = rand.nextInt(cards.length);
            // A temporary variable is assigned the value of the card
            String temp = cards[i];
            // Replaces the card with a random number
            cards[i] = cards[j];
            cards[j] = temp;
        }
        // Shows the state of the cards after they were shuffled
        System.out.println(ProjectConstants.AFTERSHUFFLETEXT);
        System.out.println(Arrays.toString(cards));
    }
    /*
      This method is based on the element of the given array at the specified index.
      cuts and recombines the next part to the beginning of the series

      @param index the index from which the index will be truncated

      Note: If the given index is outside the length of the array,
       the method does nothing.
     */

    public void cut(int index) {
        // If the given index is invalid, it terminates the execution of the method.
        if (index < 0 || index >= cards.length) {
            return;
        }

        // Copies elements of cards array 0 to index
        String[] top = Arrays.copyOfRange(cards, 0, index);
        // Copies elements of cards array between index and last element
        String[] bottom = Arrays.copyOfRange(cards, index, cards.length);

        // Updates the cards array by combining the bottom and top arrays
        cards = concatenateArrays(bottom, top);

        System.out.println(ProjectConstants.AFTERCUTTEXT);
        System.out.println(Arrays.toString(cards));
    }
    /*
      This method concatenates the two given arrays and returns the concatenated array.
      param array1 is the first array to merge
      param array2 second array to join
      return concatenated array
     */
    private static String[] concatenateArrays(String[] array1, String[] array2) {
        // sets the length of the result array
        String[] result = new String[array1.length + array2.length];
        // Copies the elements of array1 into the result array
        System.arraycopy(array1, 0, result, 0, array1.length);
        // Adds the elements of array2 to the result array
        System.arraycopy(array2, 0, result, array1.length, array2.length);
        // returns the concatenated array
        return result;
    }
    /*
      The purpose of this method is to check if the array of cards is empty.

      return {code true} if array of cards is empty, {code false} is not.
     */
    public boolean isEmpty() {
        return cards.length == 0;
    }
}
