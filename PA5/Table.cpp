// Name: Harrison Tseng
// USC NetID: Tsenghar
// CSCI 455 PA5
// Spring 2024

// Table.cpp  Table class implementation


#include "Table.h"

#include <iostream>
#include <string>
#include <cassert>

// for hash function called in private hashCode method defined below
#include <functional>

using namespace std;


// listFuncs.h has the definition of Node and its methods.  -- when
// you complete it it will also have the function prototypes for your
// list functions.  With this #include, you can use Node type (and
// Node*, and ListType), and call those list functions from inside
// your Table methods, below.

#include "listFuncs.h"


//*************************************************************************


Table::Table() {
   hashTable = new ListType[HASH_SIZE];
   hashSize = HASH_SIZE;
   for (int i = 0; i < hashSize; i++) {
      hashTable[i] = NULL;
   }

}


Table::Table(unsigned int hSize) {
   hashSize = hSize;
   hashTable = new ListType[hSize];
   for (int i = 0; i < hashSize; i++) {
      hashTable[i] = NULL;
   }
}


int * Table::lookup(const string &key) {
   int currHashCode = hashCode(key);
   return lookUpList(hashTable[currHashCode], key);
}


bool Table::remove(const string &key) {
   int currHashCode = hashCode(key);
   return removeList(hashTable[currHashCode], key);
}


bool Table::insert(const string &key, int value) {
   int currHashCode = hashCode(key);
   return appendList(hashTable[currHashCode], key, value);
}


int Table::numEntries() const {
   int totalEntries = 0;
   for (int i = 0; i < hashSize; i++) {
      totalEntries += getSize(hashTable[i]);
   }
   return totalEntries;
}


void Table::printAll() const {
   for (int i = 0; i < hashSize; i++) {
      if (hashTable[i] != NULL) {
         printList(hashTable[i]);
      }
   }
}


void Table::hashStats(ostream &out) const {
   int numNonEmptyBuckets = 0;
   int longestChain = 0;
   out << "number of buckets: " << hashSize << endl;
   out << "number of entries: " << numEntries() << endl;
   
   for (int i = 0; i < hashSize; i++) {
      int currSize = getSize(hashTable[i]);
      if (longestChain < currSize) {
         longestChain = currSize;
      } if (hashTable[i] != NULL) {
         numNonEmptyBuckets++;
      }
   }
   
   out << "number of non-empty buckets: " << numNonEmptyBuckets << endl;
   out << "longest chain: " << longestChain << endl;
}


// hash function for a string
// (we defined it for you)
// returns a value in the range [0, hashSize)
unsigned int Table::hashCode(const string &word) const {

   // Note: calls a std library hash function for string (it uses the good hash
   //   algorithm for strings that we discussed in lecture).
   return hash<string>()(word) % hashSize;

}


// add definitions for your private methods here

