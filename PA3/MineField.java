// Name: Harrison Tseng
// USC NetID: tsenghar
// CS 455 PA3
// Spring 2024

import java.util.Random;

/** 
   MineField
      Class with locations of mines for a minesweeper game.
      This class is mutable, because we sometimes need to change it once it's created.
      Mutators: populateMineField, resetEmpty
      Includes convenience method to tell the number of mines adjacent to a location.
      Representation Invariant: The values of all "spots" in our minefield will either be true or false and must not consist of null.
 */
public class MineField {
   
   private boolean mineData[][];
   private int totalMines;
   
   /**
      Create a minefield with same dimensions as the given array, and populate it with the mines in
      the array such that if mineData[row][col] is true, then hasMine(row,col) will be true and vice
      versa.  numMines() for this minefield will corresponds to the number of 'true' values in 
      mineData.
      @param mineData  the data for the mines; must have at least one row and one col,
                       and must be rectangular (i.e., every row is the same length)
    */
   public MineField(boolean[][] mineData) {
      this.mineData = new boolean[mineData.length][];
      for (int i = 0; i < mineData.length; i++){
         this.mineData[i] = new boolean[mineData[i].length];
         for (int j = 0; j < mineData[i].length; j++){
            this.mineData[i][j] = mineData[i][j];
         }
      }
      totalMines = 0;
      for(int i = 0; i < mineData.length; i++){
         for(int j = 0; j < mineData[i].length; j++){
            if(mineData[i][j] == true){
               totalMines++;
            }
         }
      }
   }
   
   
   /**
      Create an empty minefield (i.e. no mines anywhere), that may later have numMines mines (once 
      populateMineField is called on this object).  Until populateMineField is called on such a 
      MineField, numMines() will not correspond to the number of mines currently in the MineField.
      @param numRows  number of rows this minefield will have, must be positive
      @param numCols  number of columns this minefield will have, must be positive
      @param numMines   number of mines this minefield will have,  once we populate it.
      PRE: numRows > 0 and numCols > 0 and 0 <= numMines < (1/3 of total number of field locations). 
    */
   public MineField(int numRows, int numCols, int numMines) {
      this.totalMines = numMines;
      this.mineData = new boolean[numRows][numCols];
   }
   

   /**
      Removes any current mines on the minefield, and puts numMines() mines in random locations on
      the minefield, ensuring that no mine is placed at (row, col).
      @param row the row of the location to avoid placing a mine
      @param col the column of the location to avoid placing a mine
      PRE: inRange(row, col) and numMines() < (1/3 * numRows() * numCols())
    */
   public void populateMineField(int row, int col) {
      resetEmpty();
      
      int randRow = 0;
      int randCol = 0;
      int currMines = 0;
      Random random = new Random();
      
      while (currMines < totalMines){
         randRow = random.nextInt(mineData.length);   
         randCol = random.nextInt(mineData[0].length);
         // If not an invalid spot to populate mine, add mine
         if (!((randRow == row && randCol == col) || hasMine(randRow, randCol))){
            mineData[randRow][randCol] = true;
            currMines++;
         }
      }
   }
   
   
   /**
      Reset the minefield to all empty squares.  This does not affect numMines(), numRows() or
      numCols().  Thus, after this call, the actual number of mines in the minefield does not match
      numMines().  
      Note: This is the state a minefield created with the three-arg constructor is in at the 
      beginning of a game.
    */
   public void resetEmpty() {
      for (int i = 0; i < mineData.length; i++) {
         for (int j = 0; j < mineData[0].length; j++){
            mineData[i][j] = false;
         }
      }
   }

   
   /**
     Returns the number of mines adjacent to the specified location (not counting a possible 
     mine at (row, col) itself).
     Diagonals are also considered adjacent, so the return value will be in the range [0,8]
     @param row  row of the location to check
     @param col  column of the location to check
     @return  the number of mines adjacent to the square at (row, col)
     PRE: inRange(row, col)
   */
   public int numAdjacentMines(int row, int col) {
      int numAdj = 0;
      int[][] directions = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1},  {1, 0},  {1, 1}};
      // Test all 8 surround squares
      for (int[] direction : directions) {
         int newRow = row + direction[0];
         int newCol = col + direction[1];
         if (inRange(newRow, newCol) && hasMine(newRow, newCol)){
            numAdj++;   
         }
      }
      return numAdj;       
   }
   
   
   /**
      Returns true iff (row,col) is a valid field location.  Row numbers and column numbers
      start from 0.
      @param row  row of the location to consider
      @param col  column of the location to consider
      @return whether (row, col) is a valid field location
   */
   public boolean inRange(int row, int col) {
      return (row < mineData.length && row >= 0 && col >= 0 && col < mineData[0].length);      
   }
   
   
   /**
      Returns the number of rows in the field.
      @return number of rows in the field
   */  
   public int numRows() {
      return mineData.length;     
   }
   
   
   /**
      Returns the number of columns in the field.
      @return number of columns in the field
   */    
   public int numCols() {
      return mineData[0].length;     
   }
   
   
   /**
      Returns whether there is a mine in this square
      @param row  row of the location to check
      @param col  column of the location to check
      @return whether there is a mine in this square
      PRE: inRange(row, col)   
   */    
   public boolean hasMine(int row, int col) {
      if(mineData[row][col] == true){
         return true;
      }
      return false;       
   }
   
   
   /**
      Returns the number of mines you can have in this minefield.  For mines created with the 3-arg
      constructor, some of the time this value does not match the actual number of mines currently
      on the field.  See doc for that constructor, resetEmpty, and populateMineField for more
      details.
      @return number of mines
    */
   public int numMines() {
      return totalMines;      
   }
}

