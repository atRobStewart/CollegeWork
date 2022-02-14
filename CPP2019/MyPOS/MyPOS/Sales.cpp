#include "Sales.h"
#include <iostream>
using std::cout;
using std::endl;

/**********************************
// Sales default, non-default and
// copy constructors
**********************************/
Sales::Sales() {

	this->saleId = idSalesCount;
	idSalesCount++;
	this->salesAssistant = "";
}


Sales::Sales(string sa, list<Item*> si) {

	this->saleId = idSalesCount;
	idSalesCount++;
	this->salesAssistant = sa;
	this->stockItems = si;

}

Sales::Sales(const Sales* s)
{
	this->setSaleId(s->getSaleId());
	this->setSalesAssistant(s->getSalesAssistant());
	this->setStockItems(s->getStockItems());


}
/*************************************
// Getters and Setters for sales
*************************************/
unsigned int Sales::getSaleId()const {return this->saleId;}
string Sales::getSalesAssistant()const {return this->salesAssistant;}
list<Item*> Sales::getStockItems()const {return this->stockItems;}

void Sales::setSaleId(int i) { saleId = i; }
void Sales::setSalesAssistant(string sa) {

	if (sa.length() >= 2){

		salesAssistant = sa;
	}
	else
	{

		throw domain_error("Please enter a valid name, 4 character minimum");
	}
}

void Sales::setStockItems(list<Item*> si) {

	if (si.size() > 1) {

		this->stockItems = si;
	}
	else {
		throw domain_error("List must have at least one item");
	}
	
}

/**************************************
// Prints sales assistant and all of 
// items
**************************************/
void Sales::print() {

	cout << "Sales Assistant: " << this->salesAssistant << endl;

	for (Item* s : stockItems) {
		s->print();
	}
	cout << endl;
}

/*************************************
// Prints a receipt of all sold items
*************************************/
void Sales::printReceipt() {

	cout << "Sales Assistant: " << this->salesAssistant << endl;
	double total = 0;
	cout << "-------------------------------------------" << endl;
	for (Item* s : stockItems) {

		double singleItemTotal = s->calculateCost();

		cout << "\nTitle: " << s->getTitle() << "\nColour: " << s->getColour() << "\nSize:" << s->getSizeString() << "\nCost: " << singleItemTotal << endl;
		total = singleItemTotal + total;
		cout << "-------------------------------------------" << endl;
	}
	cout << "*****************************************" << endl;
	cout << "* " << "Total (including VAT): " << total << " *" << endl;
	cout << "*****************************************" << endl;
}

/***************************************
// Overloaded operators for == and !=
***************************************/
bool operator==(Sales& s1, Sales& s2)
{
	return (
		s1.getSalesAssistant() == s2.getSalesAssistant() &&
		s1.getStockItems() == s2.getStockItems()
		);
		
}

bool operator!=(Sales& s1, Sales& s2)
{
	return !(s1 == s2);
}


Sales::~Sales() {

	//for (list<Item*>::iterator iter = stockItems.begin(); iter != stockItems.end(); iter++)
	//{
	//	delete(*iter);  // *iter points to the sstockItem -> delete frees up memory
	//}
}