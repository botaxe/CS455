// Name: Harrison Tseng
// USC NetID: tsenghar
// CS 455 PA5
// Spring 2024

// pa5list.cpp
// a program to test the linked list code necessary for a hash table chain

// You are not required to submit this program for pa5.

// We gave you this starter file for it so you don't have to figure
// out the #include stuff.  The code that's being tested will be in
// listFuncs.cpp, which uses the header file listFuncs.h

// The pa5 Makefile includes a rule that compiles these two modules
// into one executable.

#include <iostream>
#include <string>
#include <cassert>

using namespace std;

#include "listFuncs.h"


int main() {
   ListType testList = NULL;
   appendList(testList, "FirstValue", 1);
   appendList(testList, "SecValue", 2);
   appendList(testList, "pizza", 9);
   appendList(testList, "ThirdValue", 3);   
   printList(testList);
   cout << "size: " << getSize(testList) << endl;
   
   appendList(testList, "SecValue", 5);
   cout << "appending SecValue:5 which should not change list" << endl;
   printList(testList);
   cout << endl;
   
   updateList(testList, "SecValue", 5);
   cout << "updating with SecValue:5 (testing middle of list) which should change list" << endl;   
   printList(testList);
   cout << endl;
   
   updateList(testList, "ThirdValue", 4);
   cout << "updating with ThirdValue:4 (testing end of list) which should change list" << endl;   
   printList(testList);
   cout << endl;
   
   updateList(testList, "FirstValue", 83);
   cout << "updating with FirstValue:83 (testing beginning of list) which should change list" << endl;   
   printList(testList);
   cout << endl;
   
   removeList(testList, "SecValue");
   cout<< "testing remove SecValue (middle of list)" << endl;
   printList(testList);
   cout << endl;
   
   removeList(testList, "FirstValue");
   cout<< "testing remove FirstValue (beginning of list)" << endl;
   printList(testList);
   cout << endl;
   
   removeList(testList, "ThirdValue");
   cout<< "testing remove ThirdValue (end of list)" << endl;
   printList(testList);
   cout << endl;
   
   removeList(testList, "NotThere");
   cout<< "testing remove NotThere (not in list)" << endl;
   printList(testList);
   cout << "size: " << getSize(testList) << endl;
   


   return 0;
}
