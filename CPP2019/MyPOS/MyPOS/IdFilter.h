#include <string>
#include<algorithm>
#include "AdultItem.h"
#include "ChildItem.h"
using namespace std;

class IdFilter
{
	int idToSearch;

public:
	IdFilter(int id) { idToSearch = id; }

	bool operator()(Item* stock)
	{
		return stock->getId() == idToSearch;
	}
};
