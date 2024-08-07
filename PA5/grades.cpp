// Name: Harrison Tseng
// USC NetID: tsenghar
// CSCI 455 PA5
// Spring 2024

#include "Table.h"

// cstdlib needed for call to atoi
#include <cstdlib>

using namespace std;
// Helper function that executes out console code.
void run(Table * & grades);

// Helper function to test insert method in class Table
void insertHelper(Table * grades);

// Helper function to test lookup method in class Table and change a pointer value
void changeHelper(Table * grades);

// Helper function to test lookup method in class Table
void lookupHelper(Table * grades);

// Helper function to test remove method in class Table
void removeHelper(Table * grades);

// Helper function to test printAll method in class Table
void printHelper(Table * grades);

// Helper function to test numEntries method in class Table
void sizeHelper(Table * grades);

// Helper function to test hashStats method in class Table
void statsHelper(Table * grades);

// Helper function to print all commands and give a brief summary.
void help();

/*
 * grades.cpp
 * A program to test the Table class.
 * How to run it:
 *      grades [hashSize]
 * 
 * the optional argument hashSize is the size of hash table to use.
 * if it's not given, the program uses default size (Table::HASH_SIZE)
 *
 */

int main(int argc, char * argv[]) {
   // Default hashsize
   int hashSize = Table::HASH_SIZE;

   Table * grades;  // Table is dynamically allocated below, so we can call
                     // different constructors depending on input from the user.
   
   // optionally gets the hash table size from the command line
   if (argc > 1) {
      hashSize = atoi(argv[1]);  // atoi converts c-string to int
      if (hashSize < 1) {
         cout << "Command line argument (hashSize) must be a positive number" 
              << endl;
         return 1;
      }

      grades = new Table(hashSize);

   }
   else {   // no command line args given -- use default table size
      grades = new Table();
   }


   grades->hashStats(cout);

   // add more code here
   // Reminder: use -> when calling Table methods, since grades is type Table*
   run(grades);
   return 0;
}
// Method comments up above.

void help(){
   cout << "insert name score : Insert this name and score in the grade table. If this name was already present, print a message to that effect, and don't do the insert." << endl;
   cout << "change name newscore : Change the score for name. Print an appropriate message if this name isn't present." << endl;
   cout << "lookup name : Lookup the name, and print out his or her score, or a message indicating that student is not in the table." << endl;
   cout << "remove name : Remove this student. If this student wasn't in the grade table, print a message to that effect." << endl;
   cout << "print : Prints out all names and scores in the table." << endl;
   cout << "size : Prints out the number of entries in the table." << endl;
   cout << "stats : Prints out statistics about the hash table at this point. (Calls hashStats() method)" << endl;
   cout << "help : Prints out a brief command summary." << endl;
   cout << "quit : Exits the program." << endl;
}

void run(Table * & grades) {
   string command = "";
   while (true) {
      cout << "cmd> ";
      cin >> command;
      if (command == "insert") {
         insertHelper(grades);
      } else if (command == "change") {
         changeHelper(grades);
      } else if (command == "lookup") {
         lookupHelper(grades);
      } else if (command == "remove") {
         removeHelper(grades);
      } else if (command == "print") {
         printHelper(grades);
      } else if (command == "size") {
         sizeHelper(grades);
      } else if (command == "stats") {
         statsHelper(grades);
      } else if (command == "help") {
         help();
      } else if (command == "quit") {
         break;
      } else {
         cout << "ERROR: invalid command" << endl;
         help();
      }
   }
}
   
void insertHelper(Table * grades) {
   string name;
   int score;
   cin >> name >> score; 
   if (!(grades -> insert(name, score))) {
      cout << "Unsuccessful, name already exist." << endl;
   } 
}

void changeHelper(Table * grades) {
   string name;
   int score;
   cin >> name >> score;
   int * targetPointer = grades -> lookup(name);
   if (targetPointer == NULL) {
      cout << "Unsuccessfull, " << name << " does not exist." << endl;   
   } else {
      * targetPointer = score;
      cout << "Successfully changed score" << endl;
   }
}

void lookupHelper(Table * grades) {
   string name;
   cin >> name;
   int * targetPointer = grades -> lookup(name);
   if (targetPointer == NULL) {
      cout << "Unsuccessfull, " << name << " does not exist." << endl;
   } else {
      cout << name << " has a score of " << * targetPointer << endl;
   }
}

void removeHelper(Table * grades) {
   string name;
   cin >> name;
   if (!remove(name.c_str())) {
      cout << "Unsuccessful " << name << " does not exist." << endl;
   } else {
      cout << "Successfully removed student" << endl;   
   }
}

void printHelper(Table * grades) {
   grades -> printAll();
}

void sizeHelper(Table * grades) {
   cout << "Their are " << grades -> numEntries() << " entries." << endl;
}

void statsHelper(Table * grades) {
   grades -> hashStats(cout);
}