// Name: Harrison Tseng
// USC NetID: Tsenghar
// CSCI 455 PA5
// Spring 2024


#include <iostream>

#include <cassert>

#include "listFuncs.h"

using namespace std;

Node::Node(const string &theKey, int theValue) {
   key = theKey;
   value = theValue;
   next = NULL;
}

Node::Node(const string &theKey, int theValue, Node *n) {
   key = theKey;
   value = theValue;
   next = n;
}


//*************************************************************************
// put the function definitions for your list functions below
int getSize(const ListType& list) {
   int size = 0;
   if (list == NULL) {
      return size;
   }
   Node * p = list;
   while (p != NULL) {
      p = p -> next;
      size++;
   }
   return size;
}

bool appendList(ListType & list, string targetKey, int targetValue) {
   if (contains(list, targetKey)) {
      return false;
   }
   Node * p = list;
   // If it is an empty list
   if (p == NULL) {
      list = new Node(targetKey, targetValue);
      return true;
   }
   while (p -> next != NULL) {
      p = p -> next;
   }
   p -> next = new Node(targetKey, targetValue);
   return true;
}

void printList(const ListType& list){
   Node * p = list;
   while (p != NULL) {
      cout << p -> key << " " << p -> value << endl;
      p = p -> next;
   }
}

bool contains(const ListType & list, string targetKey) {
   Node * p = list;
   while (p != NULL) {
      if (p -> key == targetKey) {
         return true;
      }
      p = p -> next;
   }
   return false;
}

bool removeList(ListType & list, string targetKey) {
   if (list == NULL || !contains(list, targetKey)) {
      return false;
   }
   // First node is the node that needs to be removed.
   if (list -> key == targetKey) {
      Node * temp = list;
      list = list -> next;
      delete temp;
      return true;
   }
   Node * prev = list;
   Node * curr = list -> next;
   while (curr != NULL) {
      if (curr -> key == targetKey) {
         prev -> next = curr -> next;
         delete curr;
         return true;
      }
      prev = curr;
      curr = curr -> next;
   }
   return true;
}

bool updateList(ListType & list, string targetKey, int targetValue) {
   if (!contains(list, targetKey)) {
      return false;
   }
   // No need to check for empty list since we know it contains the targetkey
   Node * p = list;
   while (p != NULL) {
      if (p -> key == targetKey) {
         p -> value = targetValue;
         return true;
      }
      p = p -> next;
   }
   return true;
}

int * lookUpList(ListType list, string targetKey) {
   if (!contains(list, targetKey)) {
      return NULL;
   }
   Node * p = list;
   while (p != NULL) {
      if (p -> key == targetKey) {
         break;
      }
      p = p -> next;
   }
   return (& (p -> value));
}