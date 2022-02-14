#pragma once
#include <list>
#include "AdultItem.h"

static unsigned int idSalesCount = 2;

class Sales{

	unsigned int saleId;
	string salesAssistant;
    list<Item*> stockItems;

public:
	
	Sales();
	Sales(string sa, list<Item*> si);
	Sales(const Sales* s);

	unsigned int getSaleId()const;
	string getSalesAssistant()const;
	list<Item*> getStockItems()const;

	void setSaleId(int i);
	void setSalesAssistant(string sa);
	void setStockItems(list<Item*> si);

	friend ostream& operator<<(ostream& o, Sales& s);
	friend istream& operator>>(istream& in, Sales& s);
	bool friend operator==(Sales& s1, Sales& s2);
	bool friend operator!=(Sales& s1, Sales& s2);

	void print();
	void printReceipt();

	~Sales();

};