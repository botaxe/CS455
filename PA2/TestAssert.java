// Name: Harrison Tseng
// USC NetID: Tsenghar
// CS 455 Lab 5
// Spring 2024
//

// Exercise 5

import java.util.ArrayList;


public class TestAssert
{

   public static void main(String[] args)
   { 
      System.out.println("Excersize Five:");
      // Constructor Pass
      ArrayList<Integer> test = new ArrayList<Integer>();
      test.add(3);
      test.add(8);
      test.add(9);
      Bookshelf constructorPass = new Bookshelf(test);
      System.out.println("ConstructorPass = " + constructorPass);

      // Constructor Fail
      ArrayList<Integer> test2 = new ArrayList<Integer>();
      test2.add(-3);
      test2.add(8);
      test2.add(-9);
      Bookshelf constructorFail = new Bookshelf(test2);
      System.out.println("ConstructorFail = " + constructorFail);
   }
}