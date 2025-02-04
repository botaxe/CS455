// Name: Harrison Tseng
// USC NetID: tsenghar
// CS 455 PA4
// Spring 2024

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Map;

/**
   Class Rack
   Implements the idea of arranging our scrabble rack as a String. The rack itself is immutable. We use our rack to get all possible subsets that can be made with the letters in our rack.
 */

public class Rack {
   /** 
   Representation Invariant: the content of our rack object must be immutable and no methods can change our representation.
   */
   
   private String rack;
   
   /**
    * Creates an empty Rack object.
    */
   public Rack(){
      rack = "";
   }
   
   /**
    * Creates an Rack object with the following String rack.
    * PRE: the input will always be a valid input.
    */
   public Rack(String rack) {
      this.rack = rack;
   }
   
   /**
    * Converts our Rack object to produce a string
    */
   public String toString() {
      return rack;   
   }
      /**
   Creates variables for a given rack so that we can call allSubsets(). For example the rack "aabbbd" will produce Object["abd",{2,3,1}] which we can then use to call allSubsets.
   */
   public ArrayList<String> getAllSubset() {
      TreeMap<Character, Integer> charCount = new TreeMap<>();
      for (int i = 0; i < rack.length(); i++) {
         char c = rack.charAt(i);
         if (charCount.containsKey(c)){
            charCount.put(c, charCount.get(c) + 1);
         } else {
            charCount.put(c, 1);
         }
      }
      char[] unique = new char[charCount.size()];
      int[] mult = new int[charCount.size()];
      int index = 0;
      
      for (Map.Entry<Character, Integer> entry : charCount.entrySet()) {
         unique[index] = entry.getKey();
         mult[index] = entry.getValue();
         index++;
      }
      return allSubsets(new String(unique), mult, 0);
   }
   

   /**
      Finds all subsets of the multiset starting at position k in unique and mult.
      unique and mult describe a multiset such that mult[i] is the multiplicity of the char
           unique.charAt(i).
      PRE: mult.length must be at least as big as unique.length()
           0 <= k <= unique.length()
      @param unique a string of unique letters
      @param mult the multiplicity of each letter from unique.  
      @param k the smallest index of unique and mult to consider.
      @return all subsets of the indicated multiset.  Unlike the multiset in the parameters,
      each subset is represented as a String that can have repeated characters in it.
      @author Claire Bono
    */
   private static ArrayList<String> allSubsets(String unique, int[] mult, int k) {
      ArrayList<String> allCombos = new ArrayList<>();
      
      if (k == unique.length()) {  // multiset is empty
         allCombos.add("");
         return allCombos;
      }
      
      // get all subsets of the multiset without the first unique char
      ArrayList<String> restCombos = allSubsets(unique, mult, k+1);
      
      // prepend all possible numbers of the first char (i.e., the one at position k) 
      // to the front of each string in restCombos.  Suppose that char is 'a'...
      
      String firstPart = "";          // in outer loop firstPart takes on the values: "", "a", "aa", ...
      for (int n = 0; n <= mult[k]; n++) {   
         for (int i = 0; i < restCombos.size(); i++) {  // for each of the subsets 
                                                        // we found in the recursive call
            // create and add a new string with n 'a's in front of that subset
            allCombos.add(firstPart + restCombos.get(i));  
         }
         firstPart += unique.charAt(k);  // append another instance of 'a' to the first part
      }
      
      return allCombos;
   }

   
}
