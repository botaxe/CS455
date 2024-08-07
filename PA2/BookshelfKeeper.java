// Name: Harrison Tseng 
// USC NetID: tsenghar
// CS 455 PA2
// Spring 2024

import java.util.ArrayList;

/**
 * Class BookshelfKeeper 
 *
 * Enables users to perform efficient putPos or pickHeight operation on a bookshelf of books kept in 
 * non-decreasing order by height, with the restriction that single books can only be added 
 * or removed from one of the two *ends* of the bookshelf to complete a higher level pick or put 
 * operation.  Pick or put operations are performed with minimum number of such adds or removes.
 */
public class BookshelfKeeper {

   /**
      Representation invariant:
      BookshelfKeeper must always be in a non decreasing order.
      BookshelfKeeper should consist of heights that are greater than zero
      
      bookshelf is an object we are using to represent our BookshelfKeeper. lastCall represents the number of calls to mutators on the bookshelf the last command executed while totalCall represents the total calls to mutators on the bookshelf executed as a whole. Both values should be integers and greater or equal to zero.
   */
   private Bookshelf bookshelf;
   private int lastCall;
   private int totalCall;

   /**
    * Creates a BookShelfKeeper object with an empty bookshelf
    */
   public BookshelfKeeper() {
      bookshelf = new Bookshelf();
      lastCall = 0;
      totalCall = 0;
      assert isValidBookshelfKeeper();
   }

   /**
    * Creates a BookshelfKeeper object initialized with the given sorted bookshelf.
    * Note: method does not make a defensive copy of the bookshelf.
    *
    * PRE: sortedBookshelf.isSorted() is true.
    */
   public BookshelfKeeper(Bookshelf sortedBookshelf) {
      bookshelf = sortedBookshelf;
      lastCall = 0;
      totalCall = 0;
      assert isValidBookshelfKeeper();
   }

   /**
    * Removes a book from the specified position in the bookshelf and keeps bookshelf sorted 
    * after picking up the book.
    * 
    * Returns the number of calls to mutators on the contained bookshelf used to complete this
    * operation. This must be the minimum number to complete the operation.
    * 
    * PRE: 0 <= position < getNumBooks()
    */
   public int pickPos(int position) {
      // Conditional logic to determine which side to remove books from.
      if (position < bookshelf.size() / 2){
         lastCall = pickFront(position);
      } else {
         lastCall = pickBack(position);
      }
      totalCall += lastCall;  
      assert isValidBookshelfKeeper();
      return lastCall;   
   }

   /**
    * Inserts book with specified height into the shelf.  Keeps the contained bookshelf sorted 
    * after the insertion.
    * 
    * Returns the number of calls to mutators on the contained bookshelf used to complete this
    * operation. This must be the minimum number to complete the operation.
    * 
    * PRE: height > 0
    */
   public int putHeight(int height) {
      int spotsFromFront = 0;
      int spotsFromBack = 0;
      // Logic to determine which side of the bookshelf would result in minimal calls made to mutators on the contained bookshelf.
      for (int i = 0; i < bookshelf.size() && bookshelf.getHeight(i) < height; i++) {
         spotsFromFront += 1;
      }
      for (int i = bookshelf.size() - 1; i >= 0 && bookshelf.getHeight(i) > height ; i--){
         spotsFromBack += 1;
      }
      if (spotsFromBack >= spotsFromFront){
         lastCall = putFront(height, spotsFromFront);
      } else {
         lastCall = putBack(height, spotsFromBack);
      }
      totalCall += lastCall;
      assert isValidBookshelfKeeper();
      return lastCall; 
   }

   /**
    * Returns the total number of calls made to mutators on the contained bookshelf
    * so far, i.e., all the ones done to perform all of the pick and put operations
    * that have been requested up to now.
    */
   public int getTotalOperations() {
      assert isValidBookshelfKeeper();
      return totalCall;   
   }

   /**
    * Returns the number of books on the contained bookshelf.
    */
   public int getNumBooks() {
      assert isValidBookshelfKeeper();
      return bookshelf.size();   
   }

   /**
    * Returns string representation of this BookshelfKeeper. Returns a String containing height
    * of all books present in the bookshelf in the order they are on the bookshelf, followed 
    * by the number of bookshelf mutator calls made to perform the last pick or put operation, 
    * followed by the total number of such calls made since we created this BookshelfKeeper.
    * 
    * Example return string showing required format: “[1, 3, 5, 7, 33] 4 10”
    * 
    */
   public String toString() {
      assert isValidBookshelfKeeper();
      return bookshelf.toString() + ' ' + lastCall + ' ' + totalCall;
   }

   /**
    * Returns true iff the BookshelfKeeper data is in a valid state.
    * (See representation invariant comment for details.)
    */
   private boolean isValidBookshelfKeeper() {
      if (bookshelf.size() == 1 && bookshelf.getHeight(0) <= 0){
         return false;
      }
      int previousHeight = 1;
      for (int i = 1; i < bookshelf.size(); i++){
         int currentHeight = bookshelf.getHeight(i);
         if (currentHeight < previousHeight){
            return false;
         }
         previousHeight = currentHeight;
      }
      return true;  

   }
   
   /**
   Remove all the books from the front and store them in a temp arraylist to execute our pickPos method while keeping our bookshelf in order. Returns the amount of calls made to mutators that were made to execute the method.
   @param index The index is which index we should be picking out our book.
   
   PRE: index >= 0 and index <= bookshelf.size() / 2
   */
   private int pickFront(int index){
      ArrayList<Integer> removed = new ArrayList<>();
      int currCall = 0;
      for (int i = 0; i < index; i++){
         removed.add(bookshelf.removeFront());
         currCall += 1;
      }
      bookshelf.removeFront();
      currCall += 1;
      for (int j = removed.size() - 1; j >= 0; j--){
         bookshelf.addFront(removed.get(j));
         currCall += 1;
      }
      return currCall;
   }
   
   /**
   Remove all the books from the back and store them in a temp arraylist to execute our pickPos method while keeping our bookshelf in order. Returns the amount of calls made to mutators that were made to execute the method.
   @param index The index is which index we should be picking out our book.
   
   PRE: index < bookshelf.size() and index >= bookshelf.size() / 2
   */
   private int pickBack(int index){
      ArrayList<Integer> removed = new ArrayList<>();
      int currCall = 0;
      for (int i = bookshelf.size() - 1; i > index; i--){
         removed.add(bookshelf.removeLast());
         currCall += 1;
      }
      bookshelf.removeLast();
      currCall += 1;
      for (int j = removed.size() - 1; j >= 0; j--){
         bookshelf.addLast(removed.get(j));
         currCall += 1;
      }
      return currCall;  
   }
   
   /**
   Remove all the books from the front and store them in a temp arraylist to execute our putHeight method while keeping our bookshelf in order. Returns the amount of calls made to mutators that were made to execute the method.
   @param height The height is the book height that we should be adding to our contained book shelf.
   @param numSpots is the number of books that we must remove from the front to then add our book in the right position.
   
   PRE: height > 0 and numSpots >= 0 and numSpots <= bookshelf.size() / 2
   */
   private int putFront(int height, int numSpots){
      ArrayList<Integer> removed = new ArrayList<>();
      int currCall = 0;
      for (int i = 0; i < numSpots; i++){
         removed.add(bookshelf.removeFront());
         currCall += 1;
      }
      bookshelf.addFront(height);
      currCall += 1;
      for (int j = removed.size() - 1; j >= 0; j--){
         bookshelf.addFront(removed.get(j));
         currCall += 1;
      }
      return currCall;
   }
   
   /**
   Remove all the books from the back and store them in a temp arraylist to execute our putHeight method while keeping our bookshelf in order. Returns the amount of calls made to mutators that were made to execute the method.
   @param height The height is the book height that we should be adding to our contained book shelf.
   @param numSpots is the number of books that we must remove from the back to then add our book in the right position.
   
   PRE: height > 0 and numSpots >= 0 and numSpots <= bookshelf.size() / 2
   */
   private int putBack(int height, int numSpots){
      ArrayList<Integer> removed = new ArrayList<>();
      int currCall = 0;
      for (int i = 0; i < numSpots; i++){
         removed.add(bookshelf.removeLast());
         currCall += 1;
      }
      bookshelf.addLast(height);
      currCall += 1;
      for (int j = removed.size() - 1; j >= 0; j--){
         bookshelf.addLast(removed.get(j));
         currCall += 1;
      }
      return currCall;  
   }

   
}
