import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class HighScore {
    private String playerName;
    public void scoreList(int score,String playerName){
        int newScore = score; // the score the player just got in the game

        // read the high score list from the file
        String[] highScores = new String[10]; // will hold the high scores and player names
        int numScores = 0; // number of scores in the list
        try (BufferedReader reader = new BufferedReader(new FileReader("highscores.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                highScores[numScores] = line;
                numScores++;
            }
        } catch (Exception e) {
            // file could not be read, just leave the list empty
        }

        // check if the new score is high enough to be added to the list
        if (numScores < 10 || newScore > Integer.parseInt(highScores[numScores - 1].split(" ")[0])) {
            // ask the player for their name


            // insert the new score and name into the list in the correct position
            int i;
            for (i = 0; i < numScores; i++) {
                if (newScore > Integer.parseInt(highScores[i].split(" ")[0])) {
                    break;
                }
            }
            for (int j = numScores; j > i; j--) {
                highScores[j] = highScores[j - 1];
            }
            highScores[i] = newScore + " " + playerName;
            if (numScores < 10) {
                numScores++;
            }
        }

        // write the updated high score list to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("highscores.txt"))) {
            for (int i = 0; i < numScores; i++) {
                writer.write(highScores[i] + "\n");
            }
        } catch (Exception e) {
            // file could not be written
        }
    }

}