import java.util.ArrayList;


public class MineFieldTester
{

   public static void main(String[] args)
   { 
      boolean[][] mineData = {{true, false, false}, {false, true, false}, {false, true, false}};
      MineField mineField = new MineField(mineData);
      System.out.println("has mines: " + mineField.hasMine(0,0));
      System.out.println("num mines: " + mineField.numMines());
      System.out.println("num rows: " + mineField.numRows());
      System.out.println("num cols: " + mineField.numCols());
      System.out.println("in range?: " + mineField.inRange(1,1));
      System.out.println(mineField.numAdjacentMines(1,1));

      MineField mineField1 = new MineField(4, 4, 3);
      System.out.println("has mines: " + mineField1.hasMine(0,0));
      System.out.println("num mines: " + mineField1.numMines());
      System.out.println("num rows: " + mineField1.numRows());
      System.out.println("num cols: " + mineField1.numCols());
      System.out.println("in range?: " + mineField1.inRange(4,4));
      
      mineField.populateMineField(1, 1);
      System.out.println("has mines: " + mineField.hasMine(1,1));
      System.out.println("num mines: " + mineField.numMines());
      System.out.println("num rows: " + mineField.numRows());
      System.out.println("num cols: " + mineField.numCols());
      System.out.println("in range?: " + mineField.inRange(1,1));
   }
}