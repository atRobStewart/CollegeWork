#pragma once
#include <list>
#include "StockItem.h"

static unsigned int idSalesCount = 2;

class Sales{

	unsigned int saleId;
	string salesAssistant;
    list<StockItem*> stockItems;

public:
	
	Sales();
	Sales(string sa, list<StockItem*> si);

	unsigned int getSaleId()const;
	string getSalesAssistant()const;
	list<StockItem*> getStockItems()const;

	void setSalesAssistant(string sa);
	void setStockItems(list<StockItem*> si);

	void print();
	void printReceipt();

	~Sales();

};