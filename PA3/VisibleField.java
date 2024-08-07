// Name: Harrison Tseng
// USC NetID: tsenghar
// CS 455 PA3
// Spring 2024


/**
  VisibleField class
  This is the data that's being displayed at any one point in the game (i.e., visible field, because
  it's what the user can see about the minefield). Client can call getStatus(row, col) for any 
  square.  It actually has data about the whole current state of the game, including the underlying
  mine field (getMineField()).  Other accessors related to game status: numMinesLeft(), 
  isGameOver().  It also has mutators related to actions the player could do (resetGameDisplay(),
  cycleGuess(), uncover()), and changes the game state accordingly.
  
  It, along with the MineField (accessible in mineField instance variable), forms the Model for the
  game application, whereas GameBoardPanel is the View and Controller in the MVC design pattern.  It
  contains the MineField that it's partially displaying.  That MineField can be accessed
  (or modified) from outside this class via the getMineField accessor.  
  Representation Invariant: Our visiblefield object must only consist of values COVERED, MINE_GUESS, QUESTION, MINE, INCORRECT_GUESS, EXPLODED_MINE or our visiblefield object will not be vaid
 */
public class VisibleField {
   // ----------------------------------------------------------   
   // The following public constants (plus values [0,8] mentioned in comments below) are the
   // possible states of one location (a "square") in the visible field (all are values that can be
   // returned by public method getStatus(row, col)).
   
   // The following are the covered states (all negative values):
   public static final int COVERED = -1;   // initial value of all squares
   public static final int MINE_GUESS = -2;
   public static final int QUESTION = -3;

   // The following are the uncovered states (all non-negative values):
   
   // values in the range [0,8] corresponds to number of mines adjacent to this opened square
   
   public static final int MINE = 9;      // this loc is a mine that hasn't been guessed already
                                          // (end of losing game)
   public static final int INCORRECT_GUESS = 10;  // is displayed a specific way at the end of
                                                  // losing game
   public static final int EXPLODED_MINE = 11;   // the one you uncovered by mistake (that caused
                                                 // you to lose)
   // ----------------------------------------------------------   
  
   private MineField mineField; // MineField object that depicts are minefield
   private int[][] visibleField; // 2 array that helps keep track of whats being displayed at any point of the game
   private boolean gameOver; // Game is over when gameOver is true.
   

   /**
      Create a visible field that has the given underlying mineField.
      The initial state will have all the locations covered, no mines guessed, and the game not
      over.
      @param mineField  the minefield to use for for this VisibleField
    */
   public VisibleField(MineField mineField) {
      this.mineField = mineField;
      gameOver = false;
      visibleField = new int[mineField.numRows()][mineField.numCols()];
      for (int i = 0; i < visibleField.length; i++){
         for (int j = 0; j < visibleField[0].length; j++){
            visibleField[i][j] = COVERED;
         }
      }
   }
   
   
   /**
      Reset the object to its initial state (see constructor comments), using the same underlying
      MineField. 
   */     
   public void resetGameDisplay() {
      gameOver = false;
      for (int i = 0; i < visibleField.length; i++){
         for (int j = 0; j < visibleField[0].length; j++){
            visibleField[i][j] = COVERED;
         }
      }
   }
  
   
   /**
      Returns a reference to the mineField that this VisibleField "covers"
      @return the minefield
    */
   public MineField getMineField() {
      return mineField;       
   }
   
   
   /**
      Returns the visible status of the square indicated.
      @param row  row of the square
      @param col  col of the square
      @return the status of the square at location (row, col).  See the public constants at the
            beginning of the class for the possible values that may be returned, and their meanings.
      PRE: getMineField().inRange(row, col)
    */
   public int getStatus(int row, int col) {
      return visibleField[row][col];       
   }

   
   /**
      Returns the the number of mines left to guess.  This has nothing to do with whether the mines
      guessed are correct or not.  Just gives the user an indication of how many more mines the user
      might want to guess.  This value will be negative if they have guessed more than the number of
      mines in the minefield.     
      @return the number of mines left to guess.
    */
   public int numMinesLeft() {
      int numMines = getMineField().numMines();
      for (int i = 0; i < visibleField.length; i++){
         for (int j = 0; j < visibleField[0].length; j++){
            if (getStatus(i,j) == MINE_GUESS){
               numMines--;
            }
         }
      }
      return numMines;      

   }
 
   
   /**
      Cycles through covered states for a square, updating number of guesses as necessary.  Call on
      a COVERED square changes its status to MINE_GUESS; call on a MINE_GUESS square changes it to
      QUESTION;  call on a QUESTION square changes it to COVERED again; call on an uncovered square
      has no effect.  
      @param row  row of the square
      @param col  col of the square
      PRE: getMineField().inRange(row, col)
    */
   public void cycleGuess(int row, int col) {
      int currStatus = getStatus(row, col);
      if (currStatus == COVERED){
         visibleField[row][col] = MINE_GUESS;
      } else if (currStatus == MINE_GUESS){
         visibleField[row][col] = QUESTION;
      } else if (currStatus == QUESTION){
         visibleField[row][col] = COVERED;
      }
   }

   
   /**
      Uncovers this square and returns false iff you uncover a mine here.
      If the square wasn't a mine or adjacent to a mine it also uncovers all the squares in the
      neighboring area that are also not next to any mines, possibly uncovering a large region.
      Any mine-adjacent squares you reach will also be uncovered, and form (possibly along with
      parts of the edge of the whole field) the boundary of this region.
      Does not uncover, or keep searching through, squares that have the status MINE_GUESS. 
      Note: this action may cause the game to end: either in a win (opened all the non-mine squares)
      or a loss (opened a mine).
      @param row  of the square
      @param col  of the square
      @return false   iff you uncover a mine at (row, col)
      PRE: getMineField().inRange(row, col)
    */
   public boolean uncover(int row, int col) {
      if (getMineField().hasMine(row, col)) {
         visibleField[row][col] = EXPLODED_MINE;
         lostGame();
         gameOver = true;
         return false;
      } else {
         uncoverMineField(row, col);
         // Check remaining squares if game is over
         for (int i = 0; i < visibleField.length; i++){
            for (int j = 0; j < visibleField[0].length; j++){
               if (!(getMineField().hasMine(i, j)) && !isUncovered(i, j) && getStatus(i, j) != MINE_GUESS) {
                  return true;
               }
            }
         }
      }
      // If all squares are either a mine or uncovered, the game is over so we replace all none uncovered squares to MINE_GUESS
      gameOver = true;
      for (int i = 0; i < visibleField.length; i++){
         for (int j = 0; j < visibleField[0].length; j++){
            if ((getMineField().hasMine(i, j)) && getStatus(i, j) != MINE_GUESS) {
               visibleField[i][j] = MINE_GUESS;
            }
         }
      }
      return true;
   }

   /**
      Returns whether the game is over.
      (Note: This is not a mutator.)
      @return whether game has ended
    */
   public boolean isGameOver() {
      return gameOver;       
   }
 
   
   /**
      Returns whether this square has been uncovered.  (i.e., is in any one of the uncovered states, 
      vs. any one of the covered states).
      @param row of the square
      @param col of the square
      @return whether the square is uncovered
      PRE: getMineField().inRange(row, col)
    */
   public boolean isUncovered(int row, int col) {
      // Covered states are negative
      if (getStatus(row, col) < 0){
         return false;
      }
      return true;       
   }
   
 
   /**
      This method is a helper method that uncovers the square that the player picks. When the square happens to have no
      adjacent mines, it uses a flood fill DFS that will find all connecting squares till it either reaches not in range,
      a current square that has a mine guess, already uncovered square, or has a mine.
      @param row  of the square
      @param col  of the square
    */
   private void uncoverMineField(int row, int col){
      int adjMines = getMineField().numAdjacentMines(row, col);
      // Base case
      if (!getMineField().inRange(row, col) || getStatus(row, col) == MINE_GUESS || isUncovered(row, col) || getMineField().hasMine(row, col)){
         return;
      } 
      visibleField[row][col] = adjMines;
      if (adjMines == 0){
         int[][] directions = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1},  {1, 0},  {1, 1}};
      // Recursion call on all 8 surrounded squares
         for (int[] direction : directions) {
            uncoverMineField(row + direction[0], col + direction[1]);
         }
      } 
      return;
   }
   
   /**
      Method helps populate the correct visibleField in the case that the player presses on a mine and loses the game.
    */
   private void lostGame(){
      for (int i = 0; i < visibleField.length; i++){
         for (int j = 0; j < visibleField[0].length; j++){
            if (getMineField().hasMine(i, j) && !(getStatus(i, j) == EXPLODED_MINE || getStatus(i, j) == MINE_GUESS)){
               visibleField[i][j] = MINE;
            } else {
               if (!getMineField().hasMine(i, j) && getStatus(i, j) == MINE_GUESS){
                  visibleField[i][j] = INCORRECT_GUESS;
               }
            }
         }
      }
   }
   
}
