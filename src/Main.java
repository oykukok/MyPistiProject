import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        boolean isPlaying = true;
        Leaderboard leaderboard = new Leaderboard();
        while (isPlaying) {
            System.out.println("Welcome to Pişti, the game begins!");

            String playerName = playerName();



            Deck deck = new Deck();
            deck.shuffle();

            int cutNumber = getNumber();
            deck.cutAndReverse(cutNumber);

            Hand player = new Hand();
            Hand computer = new Hand();
            Hand floor = new Hand();

            Game game = new Game(player, computer, floor, deck);

            game.startGame();


            int score = game.results();

            leaderboard.addScore(playerName + ":" + score);
            leaderboard.writeScoresToFile();

            isPlaying = restartGame();
        }
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
    public static int getNumber() {

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter a number between 1 and 52 to cut the cards: ");
            int number = scanner.nextInt();
            if (number < 1 || number > 52) {
                System.out.println("You have entered an invalid value, please enter a value between 1 and 52: ");
                return getNumber();
            }
            return number;

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
        System.out.println("Type 'yes' if you want to replay the game, 'no' if you don't want to: ");

        final Scanner restartGame = new Scanner(System.in);
        // Reads the user's input and assigns it to the answer variable
        String answer = restartGame.nextLine();

        // Loops if answer variable is not "Yes" or "No"
        while (!Objects.equals(answer, "yes")
                && !Objects.equals(answer, "no")) {
            System.out.println("Type 'yes' if you want to replay the game, 'no' if you don't want to: ");
            answer = (restartGame.nextLine());
        }

        // Returns true if answer variable is "Yes", false otherwise
        return Objects.equals(answer, "yes");
    }
}