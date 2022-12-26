import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        boolean isPlaying = true;
        Leaderboard leaderboard = new Leaderboard();
        while (isPlaying) {
            System.out.println(ProjectConstants.STARTGAMETEXT);

            String playerName = playerName();

            boolean isPlayerDealer = tossCoin();

            Deck deck = new Deck();
            deck.shuffle();

            int cutNumber = getNumber(isPlayerDealer);
            deck.cut(cutNumber);

            Hand player = new Hand();
            Hand computer = new Hand();
            Hand floor = new Hand();

            Game game = new Game(player, computer, floor, deck, isPlayerDealer);

            game.startGame();


            int score = game.results();

            leaderboard.addScore(playerName + ":" + score);
            leaderboard.writeScoresToFile();

            isPlaying = restartGame();
        }
    }

    /*
      Tosses a coin and sees if the user's input matches the result
      Returns a boolean value indicating .

      This method takes input "Y" (tails) or "T" (heads) from the user and
      then tosses a virtual coin using a random number generator.
      If the coin falls on the text (0) and the user enters "Y" or the coin
      If heads landed (1) and the user entered "T", the method returns true.
      Otherwise, it returns false.
      return  true if the user's input is the result of a coin toss
    matches ,  false otherwise
     */
    public static boolean tossCoin() {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println(ProjectConstants.HEADSTAILSTEXT);
        String input = scanner.nextLine();

        // generates a random number between 0 or 1
        // If coin is 0 and user entered "H" or coin is 1
        // and returns true if the user entered "T"
        int coin = random.nextInt(ProjectConstants.TWO);

        if (coin == ProjectConstants.ZERO && input.equalsIgnoreCase("H")) {
            return true;
        } else return coin == ProjectConstants.ONE && input.equalsIgnoreCase("T");
    }

    /*
      Prompts the user to enter a name and returns what he entered.
      return User entered name
     */
    public static String playerName() {
        Scanner scanner = new Scanner(System.in);
        // gets the name of the player
        System.out.println("Please enter your name : ");
        // returns the name of the player
        return scanner.nextLine();
    }

    /*
      Returns an integer between 1 and 52.
      If the inputFromUser parameter is true, this method gives the user 1 to 52
      to enter an integer between
      and repeats this process until it returns a valid input. if
      If the inputFromUser parameter is false, the method randomly ranges from 1 to 52
      returns an integer.
      parameter inputFromUser is a boolean value, the integer's value
      indicates whether to enter
      return An integer from 1 to 52
     */
    public static int getNumber(boolean inputFromUser) {
        if (inputFromUser) {
            Scanner scanner = new Scanner(System.in);
            System.out.println(ProjectConstants.CUTNUMBERTEXT);
            int number = scanner.nextInt();
            if (number < ProjectConstants.ONE || number > ProjectConstants.DECKSIZE) {
                System.out.println(ProjectConstants.ERRORGETNUMBERTEXT);
                return getNumber(inputFromUser);
            }
            return number;
        } else {
            Random random = new Random();
            int randomNumber = random.nextInt(ProjectConstants.DECKSIZE) + ProjectConstants.ONE;
            System.out.println(ProjectConstants.COMPUTERCUTTEXT);
            System.out.println(randomNumber);
            return randomNumber;
        }
    }

    /*
      Prompts the user for "Yes" or "No" input and restarts the game.
      returns a boolean value indicating that it will not be initialized.
      This method prompts the user for "Yes" or "No" input and returns a valid
      repeats this process until it responds.
      If the user inputs "Yes", the method returns true.
      If the user inputs "No", the method returns false.

      return  true if the user wants to restart the game,
      If it is false
     */
    private static boolean restartGame() {
        System.out.println(ProjectConstants.STARTAGAINTEXT);

        final Scanner restartGame = new Scanner(System.in);
        // Reads the user's input and assigns it to the answer variable
        String answer = restartGame.nextLine();

        // Loops if answer variable is not "Yes" or "No"
        while (!Objects.equals(answer, ProjectConstants.YESTEXT)
                && !Objects.equals(answer, ProjectConstants.NOTEXT)) {
            System.out.println(ProjectConstants.STARTAGAINTEXT);
            answer = (restartGame.nextLine());
        }

        // Returns true if answer variable is "Yes", false otherwise
        return Objects.equals(answer, ProjectConstants.YESTEXT);
    }
}
