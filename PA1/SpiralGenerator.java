// Name: Harrison Tseng
// USC NetID: Tsenghar
// CS 455 PA1
// Spring 2024


import java.awt.geom.Line2D;
import java.awt.Point;



/**
   Class SpiralGenerator
   
   Generates a "rectangular" spiral, using Java display coordinate system, moving 
   outward from the center of the spiral in a counter-clockwise direction.
   That is, it will head rightward on screen, then, upward, then left, then down, etc.
 
   To use this class SpiralGenerator, we must create an instance of the class with a starting position (Point) and an unit length (Int). We have two functions tied to this class. A constructor that initializes the SpiralGenerator object and a method nextSegment. By calling this method with our SpiralGenerator object, it returns a Line2D object representing the next line segment of our spiral.
 */
public class SpiralGenerator {
   /**
   unitLength and numSegments should both be greater than zero or they are undefined values and a spiral cannot be drawn. originalLength is a private variable that helps store the padding that is used when the spiral makes ach direction change and this will always be equal to a valid unitLength. Private variables x1, y1, x2, y2 are used to calculate our next segment and are instantiated to the starting position of ur spiral.
   */
   private int unitLength;
   private int segmentNumber;
   private int originalLength;
   
   private int x1;
   private int y1;
   private int x2;
   private int y2;

   /**
      Creates a SpiralGenerator starting at startPosition, with length of first segnment and 
      distance between "layers" both given as unitLength.  Both values are assuming the Java 
      graphics coordinate system.
      @param startPosition starting point of the first segment in the spiral
      @param unitLength in pixels, must be > 0
   */
   public SpiralGenerator(Point startPosition, int unitLength) {
      this.unitLength = 0;
      this.segmentNumber = 0;
      this.originalLength = unitLength;

      this.x1 = startPosition.x;
      this.y1 = startPosition.y;
      this.x2 = startPosition.x;
      this.y2 = startPosition.y;   
   }

   /**
      Return the coordinates of the next line segment in the spiral.
      Pattern of spiral is maintain length for two turns and add paddings on directions (left to right) and (right to left)
    */
   
   public Line2D nextSegment() {
      x1 = x2;
      y1 = y2;
      switch (segmentNumber % 4){
         // Case direction % 4 == 1 (going down to up)
         case 1:
            y2 = y2 - unitLength;
            break;
         // Case direction % 4 == 2 (going right to left)
         case 2:
            unitLength = unitLength + originalLength;
            x2 = x2 - unitLength;
            break;
         // Case direction % 4 == 3 (going up to down)
         case 3:
            y2 = y2 + unitLength;
            break;
         // Case direction % 4 == 0 (going left to right)
         default:
            unitLength = unitLength + originalLength;
            x2 = x2 + unitLength;
            break;
      }      
      segmentNumber++;
      return new Line2D.Double(x1, y1, x2, y2);  
   }

}
