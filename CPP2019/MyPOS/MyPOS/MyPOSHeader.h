/*************************************************
// This header file holds all of the includes
// and function protoypes necessary to run all
// the functionality in MyPOSFunctions.cpp and
// the MyPOS.cpp main, separating all of this
// into three separate files prevents one file
// holding all the code making reading and
// maintainability easier. 
**************************************************/
#pragma once
#include "AdultItem.h"
#include "ChildItem.h"
#include "Sales.h"
#include "AddExistingStockFilter.h"
#include "NameFilter.h"
#include "IdFilter.h"
#include "SizeFilter.h"
#include "ZeroQuantityFilter.h"
#include <algorithm>
#include <iostream>
#include <fstream>
#include <sstream>
#include <map>
#include <functional>

using namespace std;
typedef function<void(list<Item*>&, map<unsigned int, Item*>&)> funct;


void question1(list<Item*> &stock, map<unsigned int, Item*> &salesAnalysis);
void question2(list<Item*> &stock, map<unsigned int, Item*> &salesAnalysis);
void question3(list<Item*> &stock, map<unsigned int, Item*> &salesAnalysis);
void question4(list<Item*> &stock, map<unsigned int, Item*> &salesAnalysis);
void question5(list<Item*> &stock, map<unsigned int, Item*> &salesAnalysis);
void question6(list<Item*> &stock, map<unsigned int, Item*> &salesAnalysis);
void question7(list<Item*>& stock, map<unsigned int, Item*> &salesAnalysis);

void searchById(list<Item*>& stock, int id);
bool searchByName(list<Item*>& stock, string name);
void searchBySize(list<Item*>& stock, string size);
void addStock(list<Item*>& stock, Item* aStock);
bool storeStock(list<Item*>& stock);
bool loadStock(list<Item*>& stock);
void addSale(list<Item*>& stock, map<unsigned int, Item*> &salesAnalysis);

Item* findExistingStockItem(list<Item*>& stock, string title, string colour, string size, double cost);
Item* findById(list<Item*>& stock, unsigned int stockId);
void analyseSales(map<unsigned int, Item*> &salesAnalysis);
void addToSalesMap(map<unsigned int, Item*> &salesAnalysis, Item* &stock, unsigned int id);
void removeItem(list<Item*>& stock,int id);