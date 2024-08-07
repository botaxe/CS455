// Name: Harrison Tseng
// USC NetID: tsenghar
// CS 455 PA2
// Spring 2024


import java.util.ArrayList;
import java.util.Scanner;

/**
A class used for testing each method in our BookshelfKeeper class. Our main function takes in multiple arguments that are then broken done and tested on a BookshelfKeeper object. To use our main method we should use the -ea flag as well as input and output redirection to document our tested results. 
*/
public class BookshelfKeeperProg
{
   /**
   Parsing our input from test files to test our BookshelfKeeper class.
   */
   public static void main(String[] args)
   { 
      
      Scanner in = new Scanner(System.in);
      BookshelfKeeper validBookshelf = construct(in);
      if (validBookshelf != null ){
         System.out.println("Type pick <index> or put <height> followed by newline. Type end to exit.");
         while (in.hasNextLine()){
            nextCommand(in, validBookshelf);
         }
      }
      System.out.println("Exiting Program."); 
   }
   
   /**
   nextCommand intakes our scanner object and BookshelfKeeper object so that we can parse the next command given from our input testfile. We check if any of our input breaks our precondition by giving an error if they do or executing our command if it doesn't. 
   @param in The scanner object should be set up so that the nextLine given will always be formatted with the command first and an integer that correlates to the command.
   @param bookshelf The BookshelfKeeper object is our class object that we execute the commands on. It should always be expected that all books are valid and in the right order.
   */
   private static void nextCommand(Scanner in, BookshelfKeeper bookshelf){
      String commandLine = in.nextLine();
      Scanner scanCommand = new Scanner(commandLine);
      String command = scanCommand.next();
      if (command.equals("end")){
         return;   
      } 
      int value = scanCommand.nextInt();
      if (command.equals("put")){
         if (value <= 0){
            System.out.println("ERROR: Height of a book must be positive.");
            return;
         }
         bookshelf.putHeight(value);
         System.out.println(bookshelf.toString());
      } else if (command.equals("pick")){
         if (value < 0 || value >= bookshelf.getNumBooks()){
            System.out.println("ERROR: Entered pick operation is invalid on this shelf.");
            return;
         }
         bookshelf.pickPos(value);
         System.out.println(bookshelf.toString());
      } else {
         System.out.println("ERROR: Invalid command. Valid commands are pick, put, or end.");
      }
      
   }
   
   /**
   construct helps us construct a valid BookshelfKeeper object as long as the given string read from our scanner is valid. Returns either null if the given input was invalid or a valid BookshelfKeeper object.
   @param in The scanner object should be set up so that the nextLine given will always be formatted with a string of integers.
   */
   private static BookshelfKeeper construct(Scanner in){
      System.out.println("Please enter initial arrangement of books followed by newline:");
      String input = in.nextLine();
      ArrayList<Integer> validArray = convertToArray(input);
      if (validArray == null){
         return null;
      }
      Bookshelf books = new Bookshelf(validArray);
      BookshelfKeeper bookshelf = new BookshelfKeeper(books);
      System.out.println(bookshelf.toString());  
      return bookshelf;
   }
            
   /**
   Private method to convert string given from user input to an ArrayList if the check for valid book height and valid book order are valid. Returns null if a valid representation of a bookshelf is invalid or an array of valid book heights.
   @param inputString is the given input from the scanner object.
   */
   private static ArrayList<Integer> convertToArray(String inputString){
      ArrayList<Integer> validBookshelf = new ArrayList<>();
      Scanner scanArrayString = new Scanner(inputString);
      int previousBook = 1;
      while (scanArrayString.hasNextInt()){
         int nextInt = scanArrayString.nextInt();
         if (nextInt <= 0){
            System.out.println("ERROR: Height of a book must be positive.");
            return null;
         }
         if (nextInt < previousBook){
            System.out.println("ERROR: Heights must be specified in non-decreasing order."); 
            return null;
         }
         previousBook = nextInt;
         validBookshelf.add(nextInt);  
      }
      return validBookshelf;
   }
   
                           
}