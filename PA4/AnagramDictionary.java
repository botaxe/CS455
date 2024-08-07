// Name: Harrison Tseng
// USC NetID: tsenghar
// CS 455 PA4
// Spring 2024

import java.io.FileNotFoundException;
import java.util.ArrayList;

import java.io.*;
import java.util.*;


/**
   A dictionary of all anagram sets. 
   Note: the processing is case-sensitive; so if the dictionary has all lower
   case words, you will likely want any string you test to have all lower case
   letters too, and likewise if the dictionary words are all upper case.

 */
public class AnagramDictionary {
   /** Map key is in canonical form and the hashset has all of the words that can be created by our anagram.
   
   Representation Invariant: Each key in the anagramDict should not have any duplicate words in it's value and that each value should not be null.
   */
   
   private Map<String, HashSet<String>> anagramDict;

   /**
      Create an anagram dictionary from the list of words given in the file
      indicated by fileName.  
      @param fileName  the name of the file to read from
      @throws FileNotFoundException  if the file is not found
      @throws IllegalDictionaryException  if the dictionary has any duplicate words
    */
   public AnagramDictionary(String fileName) throws FileNotFoundException,
                                                    IllegalDictionaryException {
      anagramDict = new HashMap<>();
      Scanner scanner = new Scanner(new File(fileName));
      
      while (scanner.hasNext()) {
         String word = scanner.next();
         char[] wordInChar = word.toCharArray();
         Arrays.sort(wordInChar);
         String key = new String(wordInChar);
         
         if (anagramDict.containsKey(key)) {
            if (anagramDict.get(key).contains(word)) {
               throw new IllegalDictionaryException("ERROR: Illegal dictionary: dictionary file has a duplicate word: " + word);
            }
         } else {
            anagramDict.put(key, new HashSet<String>());   
         }
         anagramDict.get(key).add(word);
      }
   }
   

   /**
      Get all anagrams of the given string. This method is case-sensitive.
      E.g. "CARE" and "race" would not be recognized as anagrams.
      @param s string to process
      @return a list of the anagrams of s
    */
   public ArrayList<String> getAnagramsOf(String string) {
      ArrayList<String> res = new ArrayList<>();
      char[] stringInChar = string.toCharArray();
      Arrays.sort(stringInChar);
      String key = new String(stringInChar);
      
      if (anagramDict.containsKey(key)) {
          HashSet<String> uniqueWords = anagramDict.get(key);
         for (String s : uniqueWords) {
            res.add(s);
         }
      }
      return res;
   }
}
