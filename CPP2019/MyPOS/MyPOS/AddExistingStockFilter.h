/************************************************************
// This class determines whether a Stock item already exists
// or not, the user entered data is compared to the Stock
// lists current data, if all entered data is matching it
// will return true, otherwise it will return false at the
// first non-matching data point.

// This file is implemented in the findExistingStockItem()
// function in the MyPOSFunctions.cpp
*************************************************************/

#pragma once
#include <string>
#include<algorithm>
#include "AdultItem.h"
using namespace std;

class AddExistingStockFilter{

	string title;
	string colour;
	string size;
	double cost;

public:
	AddExistingStockFilter(string t, string col, string s, double c) { 
		
		this->title = t; 
		this->colour = col;
		this->size = s;
		this->cost = c;
	}

	bool operator()(Item* stock){

		if (stock->getTitle() != this->title) {

			return false;
		}
		if (stock->getColour() != this->colour) {

			return false;
		}
		if (stock->getSizeString() != this->size) {

			return false;
		}
		if (stock->getCost() != this->cost) {

			return false;
		}

		return true;
	}
};
