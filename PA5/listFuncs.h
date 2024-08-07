// Name: Harrison Tseng
// USC NetID: Tsenghar
// CSCI 455 PA5
// Spring 2024


//*************************************************************************
// Node class definition 
// and declarations for functions on ListType

// Note: we don't need Node in Table.h
// because it's used by the Table class; not by any Table client code.

// Note2: it's good practice to *not* put "using" statement in *header* files.  Thus
// here, things from std libary appear as, for example, std::string

#ifndef LIST_FUNCS_H
#define LIST_FUNCS_H

#include <string>
  

struct Node {
   std::string key;
   int value;

   Node *next;

   Node(const std::string &theKey, int theValue);

   Node(const std::string &theKey, int theValue, Node *n);
};


typedef Node * ListType;

//*************************************************************************
//add function headers (aka, function prototypes) for your functions
//that operate on a list here (i.e., each includes a parameter of type
//ListType or ListType&).  No function definitions go in this file.

/**
Gets the size of the list argument
PRE: size of list is no more than max integer
**/
int getSize(const ListType & list);

/**
Append a new key value pair to the end of our list if key is not found and return true. If key is found, do nothing and return false.
**/
bool appendList(ListType & list, std::string key, int value);

/**
Print our given list in key1:value1 key2:value2 .... format
**/
void printList(const ListType & list);

/**
Returns true if Key is already in list else return False if key is not in List
**/
bool contains(const ListType & list, std::string key);

/**
If the key doesn't exist, do nothing and return false. If key does exist, remove the key value pair and return true
**/
bool removeList(ListType & list, std::string key);

/**
If key is in list, update and return true. If the key is not in the list return false and do nothing.
**/
bool updateList(ListType & list, std::string key, int value);

/**
If key not in list, return NULL. If value is there, return address of Node with key of targetKey.
**/
int * lookUpList(ListType list, std::string targetKey);



// keep the following line at the end of the file
#endif
