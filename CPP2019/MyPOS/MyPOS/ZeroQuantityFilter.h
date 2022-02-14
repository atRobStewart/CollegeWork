#pragma once
#include "AdultItem.h"
using namespace std;
class ZeroQuantityFilter{

public:

	bool operator()(Item* stock){

		return stock->getQuantity() == 0;
	}
};

