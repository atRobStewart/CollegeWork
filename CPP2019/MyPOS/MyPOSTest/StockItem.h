#pragma once
#include "Item.h"

class StockItem : public Item{

	double vat;

public:

	StockItem();

	StockItem(string t, string col, itemSize s, unsigned int quan, double c);

	double getVat()const;

	bool setCost(double c);

	double calculateCost();

	void print();



	~StockItem();

};

