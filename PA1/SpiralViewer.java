// Name: Harrison Tseng
// USC NetID: Tsenghar
// CS 455 PA1
// Spring 2024

import javax.swing.JFrame;
import java.util.Scanner;
/**
This class is used to run our spiralgenerator class on a JFrame. To run this program, we would compile it and then execute the compiled class. The user would then be prompted to enter a valid length of segment and number of segments to display.
*/

public class SpiralViewer
{
   /**
      Main method prompts the user to enter length of initial segment and the number of segments to display to construct a frame and a SpiralComponent.
      @param args Command line arguments
   */
   public static void main(String[] args)
   {
      int unitLength = 0;
      int numSegments = 0;
      
      Scanner checkParams = new Scanner(System.in);
      
      // Prompts for length of the initial segment and error check
      while (true) {
         System.out.print("Enter length of initial segment: ");
         unitLength = checkParams.nextInt();
         if (unitLength > 0) {
            break;
         }
         System.out.println("Error: value must be > 0");
      }
      
      // Prompts for number of segments to display and error check
      while (true) {
         System.out.print("Enter number of segments to display: ");
         numSegments = checkParams.nextInt();
         if (numSegments > 0) {
            break;
         }
         System.out.println("Error: value must be > 0");
      }
      
      JFrame frame = new JFrame();
      int JFRAMEWIDTH = 800;
      int JFRAMEHEIGHT = 500;
      frame.setSize(JFRAMEWIDTH, JFRAMEHEIGHT);
      frame.setTitle("Spiral");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      // Passing numSegments and unitLength through SpiralComponent
      SpiralComponent component = new SpiralComponent(numSegments, unitLength);
      frame.add(component);

      frame.setVisible(true);
   }
}
