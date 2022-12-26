import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Game {
    Hand player;
    Hand computer;
    Hand floor;
    Deck deck;
    boolean isPlayerDealer;

    public Game(Hand player, Hand computer, Hand floor, Deck deck, boolean isPlayerDealer) {
        this.player = player;
        this.computer = computer;
        this.floor = floor;
        this.deck = deck;
        this.isPlayerDealer = isPlayerDealer;
    }

    public void startGame() {
        for (int round = ProjectConstants.ONE; round <= ProjectConstants.SIX; round++) {
            System.out.println("------- ROUND " + round + " -------");
            // we will deal 4 cards for each round.
            for (int dealCard = ProjectConstants.ZERO; dealCard < ProjectConstants.FOUR; dealCard++) {
                // if the player is the dealer, we deal cards to the first computer.
                if (isPlayerDealer) {
                    computer.addCard(deck.draw());
                    // then we give ourselves cards.
                    player.addCard(deck.draw());
                    // if the computer is the dealer, we give a card to the first player.
                } else {
                    player.addCard(deck.draw());
                    computer.addCard(deck.draw());
                }
            }
            // if it's the first round, we show the top card on the floor.
            if (round == ProjectConstants.ONE) {
                // We put 4 cards on the floor, show the 4th card.
                for (int dealCard = ProjectConstants.ZERO; dealCard < ProjectConstants.FOUR; dealCard++) {
                    floor.addCard(deck.draw());
                }
                // we show the top card.
                if (!isPlayerDealer) {
                    System.out.println("Top card: " + floor.getLastCard());
                }
            }
            // then we show the player's cards.
            System.out.println(ProjectConstants.REMAININGCARDTEXT + Arrays.toString(player.getCards()));

            // then our game starts. This will continue until the cards we have are finished.
            for (int turn = ProjectConstants.ZERO; turn < ProjectConstants.FOUR; turn++) {
                // if the player is the dealer, the first computer deals cards.
                if (isPlayerDealer) {
                    play(false);
                    play(true);
                    // if the computer is the dealer, the first player deals.
                } else {
                    play(true);
                    play(false);
                }
                System.out.println(ProjectConstants.REMAININGCARDTEXT + Arrays.toString(player.getCards()));

            }
        }
    }

    private int search(String[] array, String targetValue) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals("JACK")) {
                return i;
            } else if (array[i].substring(1).equals(targetValue.substring(1))) {
                return i;
            }
        }

        Random random = new Random();
        return random.nextInt(array.length);
    }

    private void play(boolean usersTurn) {
        if (usersTurn) {
            if (!player.isEmpty()) {
                final Scanner getScanner = new Scanner(System.in);

                System.out.print(ProjectConstants.PLAYCARDTEXT);
                int choosedNumber = (getScanner.nextInt());

                while (player.getCardCount() < choosedNumber || 1 > choosedNumber) {
                    System.out.println("Please enter a number between 1 and " + player.getCardCount() + ".");
                    System.out.print(ProjectConstants.PLAYCARDTEXT);
                    choosedNumber = (getScanner.nextInt());
                }
                gameStatus(player.removeCard(choosedNumber - 1), player);
            }
        } else {
            if (!computer.isEmpty()) {
                String card = "";
                if (!floor.isEmpty()) {
                    int cardIndex = search(computer.getCards(), floor.getLastCard());
                    card = computer.removeCard(cardIndex);
                    gameStatus(card, computer);
                } else {
                    Random random = new Random();
                    card = computer.removeCard(random.nextInt(computer.getCardCount()));
                    gameStatus(card,computer);
                }
                if (floor.isEmpty()) {
                    System.out.println("There are no cards at the top.");
                } else {
                    System.out.println("Top card: " + card);
                }
            }
        }
    }

    private void gameStatus(String card, Hand player) {
        if (floor.isEmpty()) {
            floor.addCard(card);
        } else {
            if (floor.getLastCard().substring(1).equals(card.substring(1))) {
                player.setNumberOfCards(floor.getCardCount() + 1);
                if (floor.getCardCount() == 1) {
                    player.setScore(ProjectConstants.PISTISCORE);
                } else {
                    player.setScore(calculateScore(floor.getCards()) + cardScore(card));
                }
                floor.clear();
            } else if (card.substring(1).equals("Jack")) {
                player.setScore(calculateScore(floor.getCards()) + cardScore(card));
                player.setNumberOfCards(floor.getCardCount() + 1);
                floor.clear();
            } else {
                floor.addCard(card);
            }
        }
        if (deck.isEmpty() && player.isEmpty() && computer.isEmpty()) {
            player.setScore(calculateScore(floor.getCards()));
            player.setNumberOfCards(floor.getCardCount());
            floor.clear();
        }
    }

    private int cardScore(String card) {
        return switch (card) {
            case ProjectConstants.THREESCORE -> ProjectConstants.THREE;
            case ProjectConstants.TWOSCORE -> ProjectConstants.TWO;
            default -> ProjectConstants.ONE;
        };
    }

    private int calculateScore(String[] cards) {
        int score = 0;
        for (String card : cards) {
            score += cardScore(card);
        }
        return score;
    }

    /*
      Determines the outcome of the game and returns the player's score.

      This method compares the number of cards in the player's hand with the number of cards in the computer's hand
      and gives the player 3 points if the player has more cards and gives the computer 3 points if the computer has more cards.
      The method then prints the final scores of the player and the computer and prints a message showing who won the game.
      Finally, the method returns the player's score.

      return player's score
     */
    public int results() {
        if (player.getNumberOfCards() > computer.getNumberOfCards()) {
            player.setScore(ProjectConstants.THREE);
        } else {
            computer.setScore(ProjectConstants.THREE);
        }

        System.out.println("Game over. Scores: ");
        System.out.println("Player: " + player.getScore());
        System.out.println("Computer: " + computer.getScore());

        if (player.getScore() > computer.getScore()) {
            System.out.println("You won the game.");
        } else {
            System.out.println("You lost the game.");
        }

        return player.getScore();
    }
}
