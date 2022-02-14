#include "StockItem.h"

using namespace std;

StockItem::StockItem() {

	this->vat = 0.0;
}

StockItem::StockItem(string t, string col, itemSize s, unsigned int quan, double c) : Item(t, col, s, quan, c){
	
	this->vat = (c / 100) * 21;

}

double StockItem::getVat()const { return this->vat; }

bool StockItem::setCost(double c) {

	if (Item::setCost(c)){

		this->vat = (c / 100) * 21;
		return true;
	}
	return false;
}


double StockItem::calculateCost() {

	return (this->getCost() + this->vat) * this->getQuantity();
}

void StockItem::print() {
	
	Item::print();
	cout << fixed << showpoint << setprecision(2) << setw(10)<<left<< "Plus Vat 21% " << this->vat << endl;
}


StockItem::~StockItem(){}