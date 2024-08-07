/**
Name: Harrison Tseng  
USC NetID: tsenghar
CS 455 PA4
Spring 2024
*/

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map;

/**
Enables the user to create a list of all legal words formed from a comprised Scrabble rack. Produced list of legal words shall be listed in decreasing order and in alphabetical order. There will be an optional command-line arguement for a dictionary file name, if left blank, the original dictionary file will be used. 
*/

public class WordFinder {

   public static void main(String[] args) { 
      String dictionaryFile;
      if (args.length > 0){
         dictionaryFile = args[0];  
      } else {
         dictionaryFile = "./sowpods.txt";
      }
      
      try {
         AnagramDictionary anagramDict = new AnagramDictionary(dictionaryFile);

         Rack rack = new Rack();
         Scanner in = new Scanner(System.in);
         System.out.println("Type . to quit.");
         // input not "."
         while (true){
            System.out.print("Rack? ");   
            rack = new Rack(in.nextLine());
            if (rack.toString().equals(".")){
               break;   
            }
            outputAll(rack, anagramDict);
         }
      } catch (FileNotFoundException e) {
         System.out.println("ERROR: Dictionary file \"" + dictionaryFile + "\" does not exist.");
         System.out.println("Exiting program.");
      } catch (IllegalDictionaryException e) {
         System.out.println(e.getMessage());
         System.out.println("Exiting program.");
      }
   }   
   
   /**
      outputAll formulates all possible words that can be produced with our rack. By comparing the dictionary for valid words with all subsets we can produce, we create a unique paired list of scores and words.
      @param rack Represented by our Rack object will hold the characters that we currently have.
      @param anagramDictionary Represented by our AnagramDictionary object is all words configured in canonical form so that we can search up possible works quicker.
   */
   private static void outputAll(Rack rack, AnagramDictionary anagramDictionary) {
      // Used to represent how final output is stored with all possible words and score.
      TreeMap<Integer, TreeSet<String>> scoreMap = new TreeMap<>();
      int totalWords = 0;
      for (String subset : rack.getAllSubset()) {
         ArrayList<String> anagramWords = anagramDictionary.getAnagramsOf(subset);  
         
         if (anagramWords != null) {
            for (String word : anagramWords) {
               // Multiply score by -1 for the natural order of TreeMap to be in the order we want to print the possible words
               int score = ScoreTable.computeScore(word) * -1;
               if (!scoreMap.containsKey(score)) {
                  scoreMap.put(score, new TreeSet<String>());
               }
               scoreMap.get(score).add(word);
               totalWords += 1;
            }
         }
      }
      System.out.println("We can make " + totalWords + " words from \"" + rack.toString() + "\"");
      if (totalWords > 0) {
         System.out.println("All of the words with their scores (sorted by score):");
         for (Map.Entry<Integer, TreeSet<String>> entry : scoreMap.entrySet()) {
            for (String word : entry.getValue()) {
               // Recalculating the correct score.
               int wordScore = entry.getKey() * -1;
               System.out.println(wordScore + ": " + word);
            }
         }
      }
   }
}