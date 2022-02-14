#include "Sales.h"
#include <iostream>
using std::cout;
using std::endl;

Sales::Sales() {

	this->saleId = idSalesCount;
	idSalesCount++;
	this->salesAssistant = "";
}


Sales::Sales(string sa, list<StockItem*> si) {

	this->saleId = idSalesCount;
	idSalesCount++;
	this->salesAssistant = sa;
	this->stockItems = si;

}

unsigned int Sales::getSaleId()const {return this->saleId;}
string Sales::getSalesAssistant()const {return this->salesAssistant;}
list<StockItem*> Sales::getStockItems()const {return this->stockItems;}


void Sales::setSalesAssistant(string sa) {

	if (sa.length() >= 4){

		salesAssistant = sa;
	}else{

		cout << "Please enter a valid name, 4 character minimum" << endl;
	}
}

void Sales::setStockItems(list<StockItem*> si) {

	
}

void Sales::print() {

	cout << "Sales Assistant: " << this->salesAssistant << endl;

	for (StockItem* s : stockItems) {
		s->print();
	}
	cout << endl;
}

void Sales::printReceipt() {

	cout << "Sales Assistant: " << this->salesAssistant << endl;
	double total = 0;

	for (StockItem* s : stockItems) {

		double singleItemTotal = s->getCost() * s->getQuantity();

		cout << "\n\nTitle: " << s->getTitle() << "\nColour: " << s->getColour() << "\nSize:" << s->getSizeString() << "\nCost: " << singleItemTotal << endl;
		total = singleItemTotal + total;
	}
	cout << "*******************" << endl;
	cout << "* " << "Total: " << total << " *" << endl;
	cout << "*******************" << endl;
}

Sales::~Sales() {

	for (list<StockItem*>::iterator iter = stockItems.begin(); iter != stockItems.end(); iter++)
	{
		delete(*iter);  // *iter points to the sstockItem -> delete frees up memory
	}
}