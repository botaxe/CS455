// Name: Harrison Tseng
// USC NetID: Tsenghar
// CS 455 PA1
// Spring 2024

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import java.awt.Point;
import java.awt.geom.Line2D;

/**
This component draws the spiral. To use this class we must instantiate a new SpiralComponent and the function paintComponent will be    automatically called when our window is resized by the user.
*/

public class SpiralComponent extends JComponent
{     
   /**
   unitLength and numSegments should both be greater than zero or they are undefined values and a spiral cannot be drawn
   */
   private int unitLength;
   private int numSegments;
   
/**
   Constructor initializes the SpiralComponent with the specified number of segments and unit    length recieved from the user.
   @param numSegments Number of segments the spiral will display
   @param unitLength in pixels, must be > 0 is also the padding of the spiral
*/   

   public SpiralComponent(int numSegments, int unitLength) {
      this.numSegments = numSegments;
      this.unitLength = unitLength;
   }
   
/**
   This method draws the spiral on the graphical component. We will never explicitly call paintComponent but when the window is resized, the       method will be called and the spiral position is recomputed.
   @param g Graphics context 
*/   

   public void paintComponent(Graphics g)
   {  
      Graphics2D g2 = (Graphics2D) g;
      
      // Finding the middle of our window so that our spiral starts in the middle.
      int xCoord = getWidth() / 2;
      int yCoord = getHeight() / 2;
      Point startP = new Point(xCoord, yCoord);
      
      SpiralGenerator spiral = new SpiralGenerator(startP, unitLength);
      for (int i = 0; i < numSegments; i++) {
         Line2D line = spiral.nextSegment();
         g2.draw(line);
      }
    
   }
}
