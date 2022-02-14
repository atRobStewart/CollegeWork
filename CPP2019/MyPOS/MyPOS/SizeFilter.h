#pragma once
#include <string>
#include<algorithm>
#include "AdultItem.h"
using namespace std;
class SizeFilter
{
	itemSize size;

public:
	SizeFilter(itemSize s) { size = s; }

	bool operator()(Item* stock)
	{
		return stock->getSize() == size;
	}
};

