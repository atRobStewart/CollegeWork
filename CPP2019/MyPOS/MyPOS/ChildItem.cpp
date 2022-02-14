#include "ChildItem.h"

/*****************************
// Default constructor
*****************************/
ChildItem::ChildItem() {

	this->vat = 0;
}

/******************************
// Constructor initalises vat
// and inherits attributes 
// from Item class.
******************************/
ChildItem::ChildItem(string t, string col, itemSize s, unsigned int quan, double c) : Item(t, col, s, quan, c) {

	this->vat = 0;

}

/******************************
// Setter returns bool to tell
// if the cost was changed.
******************************/
bool ChildItem::setCost(double c) {

	return Item::setCost(c);
}

/******************************
// Uses calculate cost from 
// the Item class
******************************/
double ChildItem::calculateCost() {

	return Item::calculateCost();
}

/*****************************
// Uses print from the Item
// class
*****************************/
void ChildItem::print() {

	Item::print();
}

ChildItem::~ChildItem(){}