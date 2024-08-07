/**
Name: Harrison Tseng  
USC NetID: tsenghar
CS 455 PA4
Spring 2024
*/
import java.lang.Character;

/**
* ScoreTable class to get score of a given string based on the score of each character in the game of Scrabble.
*/

public class ScoreTable{
   
   /**
    * Representation Invariant: ScoreTable is a constent and must consist of only integers.
    */
   
   private static int[] SCORETABLE = {1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10};
   
   /**
   Method computes the score of a string input and returns the score.
   */
   public static int computeScore(String word) {
      int totalScore = 0;
      for (char letter : word.toCharArray()) {
         int letterIndex = Character.toLowerCase(letter) - 'a';
         totalScore += SCORETABLE[letterIndex];
      }
      return totalScore;
   }
}