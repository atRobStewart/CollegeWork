#pragma once
#include "Item.h"

class ChildItem : public Item {

	double vat;

public:

	ChildItem();

	ChildItem(string t, string col, itemSize s, unsigned int quan, double c);

	bool setCost(double c);

	double calculateCost();

	void print();

	~ChildItem();


};
