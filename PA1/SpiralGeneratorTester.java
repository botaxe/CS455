// Name: Harrison Tseng
// USC NetID: Tsenghar
// CS 455 PA1
// Spring 2024

/**
   A class to test the SpiralGenerator class.
*/

import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Line2D;

/**
A class used for tesing each method of the SpiralGenerator class for boundary cases and inputs that may cause our class to fail. The main function runs a series of Tests on predetermined parameters and outputs on the console the expected points of our SpiralGenerator. The class has a total of 5 private functions. spiralTester can be called with 3 parameters to test whether our SpiralGenerator class is working properly. The other 4 functions all return a boolean and is used to accompany our spiralTester function to further detect if our current segment is horizontal, perpendicular, connected, or vertical.
*/
public class SpiralGeneratorTester
{
   /**
      Test the methods of the SpiralGenerator class
   */
   public static void main(String[] args)
   { 
      spiralTester(new Point (500,500), 10, 70);
      spiralTester(new Point (200,200), 1, 10);
      spiralTester(new Point (200,200), 10, 1000);
      spiralTester(new Point (200,200), -8, 8);
      spiralTester(new Point (200,200), 8, -8); 
   }
   /**
   Helper function to test our SpiralGenerator class multiple times. Preconditions are that unitLength and numSegment should be greater or equal to 1.
   @param unitLength initial segment length and padding3
   @param numSegments Number of segments the spiral generator should compute
   */
   
   private static void spiralTester(Point startPoint, int unitLength, int numSegments){
      int twoValidLines = 0;
      Point2D previousPoint = startPoint;
      Line2D previousLine = null;
      SpiralGenerator testSpiral = new SpiralGenerator(startPoint, unitLength);
      Point2D firstPoint;
      Point2D secondPoint;
   
      System.out.println("Making a spiral starting from " + startPoint);
      System.out.println(", with a unit length of " + unitLength + ", and made up of " + numSegments + " segments:");
      
      // Checking for precondition cases before computing next segments
      if (unitLength < 0 || numSegments < 0) {
         System.out.println("Error: unitLength value and numSegments value must be > 0 ");
         return;
      } 
      
      for (int i = 0; i < numSegments; i++) {
         Line2D currentLine = testSpiral.nextSegment();
         firstPoint = currentLine.getP1();
         secondPoint = currentLine.getP2(); 
         System.out.println("Segment #" + (i + 1) + ": " + firstPoint + " to " + secondPoint);
         if (twoValidLines > 0 && !connected(previousPoint, firstPoint)) {
            twoValidLines = -1;
            System.out.println("FAILED: last two segments are not connected");
         }
         if (!isHorizontal(firstPoint, secondPoint) && !isVertical(firstPoint, secondPoint)) {
            System.out.println("FAILED: segment is not horizontal or vertical.   Skipping pair tests.");
            twoValidLines = -1;
         } else if (twoValidLines >= 1 && !arePerpendicular(previousLine, currentLine)){
            System.out.println("FAILED: last two segments are not perpendicular");
         }
         twoValidLines++;
         previousPoint = secondPoint;
         previousLine = currentLine;
      }
      
   }
   
   
   /**
   Check if end points of two lines are connected
   @param previousPoint Previous end point of previous line
   @param currentPoint Currend start point of current line
   */
   private static boolean connected(Point2D previousPoint, Point2D currentPoint){
      return (currentPoint.equals(previousPoint));
   }
   
   /**
   Check if the last two lines are perpendicular by checking if one was horizontal and the other was vertical
   @param lineOne Previous line drawn
   @param lineTwo Current line drawn
   */
   private static boolean arePerpendicular(Line2D lineOne, Line2D lineTwo){
      return ((isVertical(lineOne.getP1(), lineOne.getP2()) && isHorizontal(lineTwo.getP1(), lineTwo.getP2())) || (isHorizontal(lineOne.getP1(), lineOne.getP2()) && isVertical(lineTwo.getP1(), lineTwo.getP2())));
   }
   
   /**
   Check if current line is horizontal
   @param firstPoint First point of current line segment
   @param secondPoint Second point of current line segment
   */
   private static boolean isHorizontal(Point2D firstPoint, Point2D secondPoint){
      return (firstPoint.getY() == secondPoint.getY());
   }
   
   /**
   Check if current line is vertical
   @param firstPoint First point of current line segment
   @param secondPoint Second point of current line segment
   */
   private static boolean isVertical(Point2D firstPoint, Point2D secondPoint){
      return (firstPoint.getX() == secondPoint.getX());
   }
   
}