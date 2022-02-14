#include "AdultItem.h"

/*****************************
// Default constructor
*****************************/
AdultItem::AdultItem() {

	this->vat = 0.0;
}

/******************************
// Constructor calculates vat
// from cost and inherits
// attributes from Item class.
******************************/
AdultItem::AdultItem(string t, string col, itemSize s, unsigned int quan, double c) : Item(t, col, s, quan, c){
	
	this->vat = (c / 100) * 21;

}

/******************************
// Getter and setter for vat
// attribute.
******************************/
double AdultItem::getVat()const { return this->vat; }

bool AdultItem::setCost(double c) {

	if (Item::setCost(c)){

		this->vat = (c / 100) * 21;
		return true;
	}
	return false;
}

/******************************
// Calculates cost for an
// adult item with the vat
// added on.
******************************/
double AdultItem::calculateCost() {

	return (this->getCost() + this->vat) * this->getQuantity();
}

/******************************
// Prints item attributes along
// with the vat.
******************************/
void AdultItem::print() {
	
	Item::print();
	cout << fixed << showpoint << setprecision(2) << setw(10)<<left<< "Plus Vat 21% " << this->vat << endl;
}


AdultItem::~AdultItem(){}