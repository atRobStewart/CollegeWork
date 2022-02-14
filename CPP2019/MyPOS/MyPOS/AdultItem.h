#pragma once
#include "Item.h"

class AdultItem : public Item{

	double vat;

public:

	AdultItem();

	AdultItem(string t, string col, itemSize s, unsigned int quan, double c);

	double getVat()const;

	bool setCost(double c);

	double calculateCost();

	void print();

	~AdultItem();

};

