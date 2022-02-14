#pragma once
#include <string>
#include<algorithm>
#include "AdultItem.h"
using namespace std;
class NameFilter
{
	string nameToSearch;
public :
	NameFilter(string s) { nameToSearch = s; }

	bool operator()(Item* stock)
	{
		return strstr(stock->getTitle().c_str(), nameToSearch.c_str());
		//return stock->getTitle() == nameToSearch;
	}

};
