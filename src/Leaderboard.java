import java.io.*;

public class Leaderboard {
    // The array I created to keep the top 10 scores.
    private static final String[] scores = new String[10];

    /*
      Updates the array holding the scores to be displayed on the screen.

      This method will display the given score on the screen.
      adds to the array and scores the array in descending order
      sorts. If the last element of the array is not empty, the new score
      compares with the last element of the array and if the new score
      if greater than the last element of the array, the last element of the array
      updates. If the last element of the array is empty,
      adds the score to the first element.
      parameter score to add score
     */
    public void addScore(String score) {
        if (scores[scores.length - 1] != null) {
            // If the last element of the array is full,
            int newScore = Integer.parseInt(score.split(":")[1]
                    .trim());
            int lowestScore = Integer.parseInt(scores[scores.length
                    - 1].split(":")[1]
                    .trim());
            if (newScore > lowestScore) {
                // If the new score is greater than the lowest score, it replaces the lowest score.
                scores[scores.length - 1] = score;
            }
            else {
                // If the last element of the array is empty,
                for (int i = 0; i < scores.length; i++) {
                    if (scores[i] == null) {
                        // places the score in an empty space
                        scores[i] = score;
                        break;
                    }
                }
            }
        }

        boolean isEmpty = false;

        for (String singleScore : scores) {
            if (singleScore == null) {
                isEmpty = true;
                break;
            }
        }
        if (!isEmpty) {
            sortScores();
        }
    }

    /*
      Order the scores from highest to lowest.

      This method sorts the scores from highest to lowest.
      It loops through each element of the array one by one.
      compares with other elements. If one element is
      If is greater than the element, its elements are swapped.
     */
    public void sortScores() {
        // Indexes to use to parse username and score
        final int index = 1;

        // We use an algorithm to sort the scores
        for (int i = 0; i < scores.length - 1; i++) {
            for (int a = 0; a < scores.length - i - 1; a++) {
                // We convert the string values of the scores to integer
                int score1 = Integer.parseInt(scores[a].split(":")[index].trim());
                int score2 = Integer.parseInt(scores[a + 1].split(":")[index].trim());

                // We compare scores
                if (score2 > score1) {
                    // We change the scores
                    String temp = scores[a];
                    scores[a] = scores[a + 1];
                    scores[a + 1] = temp;
                }
            }
        }
    }

    /*
      Writes scores to scores.txt file.
      This method returns the scores in the scores array to be displayed on the screen.
      Writes to "scores.txt" file. If the file could not be created or
      If an error occurred while writing , the error message is printed on the screen.

     */
    public void writeScoresToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("scores.txt"))) {
            // Opens a BufferedWriter to write to the scores.txt file
            for (String score : scores) {
                if (score != null) {
                    // If the score is not null,
                    writer.write(score);
                    // Writes the score to the file
                    writer.newLine();
                    // goes to the bottom line of the file
                }
            }
        } catch (IOException e) {
            // If an error occurs while opening the file, print the error
            e.printStackTrace();
        }
    }
   /* public String showScores(File file) throws Exception{
        try {
            FileReader reader = new FileReader("scoresOfPlayers.txt");
        } catch (IOException e){
            e.printStackTrace();
        }


    }*/

}