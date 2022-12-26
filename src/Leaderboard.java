import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

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
        if (scores[scores.length - ProjectConstants.ONE] != null) {
            // If the last element of the array is full,
            int newScore = Integer.parseInt(score.split(":")[ProjectConstants.ONE]
                    .trim());
            int lowestScore = Integer.parseInt(scores[scores.length
                    - ProjectConstants.ONE].split(":")[ProjectConstants.ONE]
                    .trim());
            if (newScore > lowestScore) {
                // If the new score is greater than the lowest score, it replaces the lowest score.
                scores[scores.length - ProjectConstants.ONE] = score;
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
        final int SCORE_INDEX = 1;

        // We use an algorithm to sort the scores
        for (int i = 0; i < scores.length - 1; i++) {
            for (int j = 0; j < scores.length - i - 1; j++) {
                // We convert the string values of the scores to integer
                int score1 = Integer.parseInt(scores[j].split(":")[SCORE_INDEX].trim());
                int score2 = Integer.parseInt(scores[j + 1].split(":")[SCORE_INDEX].trim());

                // We compare scores
                if (score2 > score1) {
                    // We change the scores
                    String temp = scores[j];
                    scores[j] = scores[j + 1];
                    scores[j + 1] = temp;
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

}