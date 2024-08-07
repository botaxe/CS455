// Name: Harrison Tseng
// USC NetID: Tsenghar
// CS 455 Lab 5
// Spring 2024
//


import java.util.ArrayList;


public class BookshelfTester
{

   public static void main(String[] args)
   { 
      System.out.println("Excersize Two:");
      // Constructor 1
      Bookshelf constructorOne = new Bookshelf();
      System.out.println("ConstructorOne = " + constructorOne);
      // Constructor 2
      ArrayList<Integer> test = new ArrayList<Integer>();
      test.add(3);
      test.add(9);
      test.add(10);
      Bookshelf constructorTwo = new Bookshelf(test);
      System.out.println("ConstructorTwo = " + constructorTwo);
      // To String
      System.out.println("Converting to string:...." + constructorTwo.toString());
   }
}