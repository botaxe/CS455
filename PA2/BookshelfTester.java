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
      test.add(8);
      test.add(9);
      Bookshelf constructorTwo = new Bookshelf(test);
      System.out.println("ConstructorTwo = " + constructorTwo);
      // To String
      System.out.println("Converting to string:...." + constructorTwo.toString());
      
      System.out.println("Excersize Three:");
      // add front
      constructorTwo.addFront(7);
      System.out.println("addFront(7) gives:" + constructorTwo);
      // add last
      constructorTwo.addLast(10);
      System.out.println("addLast(10) gives:" + constructorTwo);
      // remove front
      constructorTwo.removeFront();
      System.out.println("removeFront() gives:" + constructorTwo);
      // remove last
      constructorTwo.removeLast();
      System.out.println("removeLast() gives:" + constructorTwo);
      
      System.out.println("Excersize Four:");
      // getHeight()
      System.out.println("getHeight(1) gives:" + constructorTwo.getHeight(1));
      // size()
      System.out.println("size() gives how many books we have (Expected: 3):" + constructorTwo.size());
      // isSorted()
      System.out.println("isSorted() (Expected: True):" + constructorTwo.isSorted());

   }
}