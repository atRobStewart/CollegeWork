#include "MyPOSHeader.h"

list<StockItem*> stock;

void storeStock(list<StockItem*>& stock)
{
	ofstream out("stockOut.txt");
	if (out)
	{
		for (StockItem* s : stock)
		{
			out << *s << endl;

		}
		string stars(30, '*');
		cout << "\n\t" << stars << endl;
		cout << "\t  Stock stored successfully" << endl;
		cout << "\t" << stars << "\n" << endl;
		out.close();
	}
	else
	{
		cout << "Error opening file" << endl;
	}
}

void loadStock(list<StockItem*>& stock)
{
	ifstream in("stockOut.txt");
	if (in)
	{
		while (!in.eof())
		{
			StockItem* s = new StockItem();
			in >> *s;
			if (s->getTitle() != "")
			{
				stock.push_back(s);
			}
		}
		


	}
	else
	{
		cout << "Error opening file." << endl;
	}
	/*ifstream in("stockOut.txt");
	
	if (in)
		{

			StockItem* s;
			string line;
			string title, colour, idString, size, qtyString, costString;
			int id, quantity;
			double cost;
			while (getline(in, line))
			{
				stringstream ss(line);
				getline(ss, idString, '~');
				id = stoi(idString);
				getline(ss, title, '~');
				getline(ss, colour, '~');
				getline(ss, size, '~');
				getline(ss, qtyString, '~');
				quantity = stoi(qtyString);
				getline(ss, costString, '~');
				cost = stod(costString);

				s = new StockItem();
				s->setID(id);
				s->setTitle(title);
				s->setColour(colour);
				s->setSize(size);
				s->setQuantity(quantity);
				s->setCost(cost);
				stock.push_back(s);
			}


		}
		else
		{
			cout << "Error opening file." << endl;
		}*/
	

	
}

void question1(list<StockItem*> &stock, map<unsigned int, StockItem*> salesAnalysis)
{
	addStock(stock);
	//storeStock(stock);
}

void question2(list<StockItem*> &stock, map<unsigned int, StockItem*> salesAnalysis) {

	addSale(stock, salesAnalysis);
}

void question3(list<StockItem*> &stock, map<unsigned int, StockItem*> salesAnalysis) {

	analyseSales(salesAnalysis);
}

void question4(list<StockItem*> &stock, map<unsigned int, StockItem*> salesAnalysis){
	int option;
	cout << "Options:\n1.Search by ID\n2.Search by name\n3.Search by size" << endl;
	cin >> option;
	cin.ignore(1000, '\n');
	int itemId = 0;
	string name;
	string size;

	switch(option)
	{
		case 1:
			cout << "Please enter ID you wish to search: ";
			cin >> itemId;
			searchById(stock, itemId);
			break;
		case 2:
			cout << "Please enter Name you wish to search: ";
			getline(cin, name);
			searchByName(stock, name);
			break;
		case 3:
			cout << "Please enter size you wish to search: ";
			getline(cin, size);
			searchBySize(stock, size);
			break;
		
		default: cout << "Invalid Option";
			break;

	}
		
		
		
}

void question5(list<StockItem*> &stock, map<unsigned int, StockItem*> salesAnalysis) {

	removeItem(stock);
}

void question6(list<StockItem*> &stock, map<unsigned int, StockItem*> salesAnalysis)
{
	//writing to file
	storeStock(stock);
}

void question7(list<StockItem*> &stock, map<unsigned int, StockItem*> salesAnalysis)
{
	//reading from file
	cout << "\nBrands Recorded in file:\n";
	for (list<StockItem*>::iterator iter = stock.begin(); iter != stock.end(); iter++)
	{
		if (iter != stock.begin())
		{
			cout << ", " << (*iter)->getTitle();

		}
		else
		{
			cout << (*iter)->getTitle();
		}

	}
	cout << endl << endl;



}

void searchById(list<StockItem*> &stock,int id)
{
	
	
	list<StockItem*> results;
	IdFilter filter(id);
	auto it = copy_if(stock.begin(), stock.end(), back_inserter(results), filter);
	if (results.size() > 0)
	{
		cout << "\n";
		for (StockItem* ptr : results)
		{
			ptr->print();
			cout << "\n";
		}
	}
	else
	{
		cout << "No items found." << endl;
	}
	cout << endl << endl;
}

void searchByName(list<StockItem*> &stock, string name)
{
	list<StockItem*> results;
	NameFilter filter(name);
	auto it = copy_if(stock.begin(), stock.end(), back_inserter(results), filter);
	if (results.size() > 0)
	{
		cout << "\n";
		for (StockItem* ptr : results)
		{
			ptr->print();
			cout << "\n";
		}
	}
	else
	{
		cout << "No items found." << endl;
	}


}

void searchBySize(list<StockItem*> &stock, string size)
{
	StockItem s1;
	s1.setSize(size);
	list<StockItem*> results;
	SizeFilter filter(s1.getSize());
	auto it = copy_if(stock.begin(), stock.end(), back_inserter(results), filter);
	if (results.size() > 0)
	{
		cout << "\n";
		for (StockItem* ptr : results)
		{
			ptr->print();
			cout << "\n";
		}
	}
	else
	{
		cout << "No items found." << endl;
	}

}

/******************************************************************
// addStock takes user input for each item attribute, if the item
// does not exist an new Stock item will get added to the passed 
// in Stock list, if the Stock item already exists which is 
// determined through the findExistingStockItem() function, the
// items current quantity gets added to the user entered quantity.

// This Function uses the findExistingStockItem() function
// within this file.
*******************************************************************/
void addStock(list<StockItem*> &stock) {

	unsigned int itemId;
	string itemTitle;
	string itemColour;
	string itemSizing;
	unsigned int itemQuantity;
	double itemCost;

	cout << "Enter item title: " << endl;
	getline(cin, itemTitle);
	cout << endl;

	cout << "Enter item colour: " << endl;
	getline(cin, itemColour);
	cout << endl;

	cout << "Enter item size: " << endl;
	cin >> itemSizing;
	cout << endl;

	cout << "Enter item quantity: " << endl;
	cin >> itemQuantity;
	cout << endl;

	cout << "Enter item Cost: " << endl;
	cin >> itemCost;
	cout << endl;

	StockItem* existingItem = findExistingStockItem(stock, itemTitle, itemColour, itemSizing, itemCost);

	if (existingItem == NULL) {

		StockItem* newItem = new StockItem(itemTitle, itemColour, itemSize::NA, itemQuantity, itemCost);
		newItem->setSize(itemSizing);
		stock.push_back(newItem);

		cout << "Item successfully added." << endl;
	}
	else {
		existingItem->setQuantity(existingItem->getQuantity() + itemQuantity);

		cout << "Item successfully updated." << endl;
	}

}

/*******************************************************
// Function checks if an added item already exists,
// through using the addExistingStockFilter.
// Returns the first existing item if one is found, 
// otherwise it returns null.

// This function is used in the addStock() function
// within this file.
// This function uses the AddExistingStockFilter.h
// file.
********************************************************/
StockItem* findExistingStockItem(list<StockItem*>& stock, string title, string colour, string size, double cost){

	list<StockItem*> results;
	AddExistingStockFilter filter(title, colour, size, cost);
	auto it = copy_if(stock.begin(), stock.end(), back_inserter(results), filter);
	if (results.size() > 0){

		return results.front();
	}else{

		return NULL;
	}
}
void addToSalesMap(map<unsigned int, StockItem*> salesAnalysis, StockItem*& stock, unsigned int id) {

	map<unsigned int, StockItem*>::iterator iter;
	iter = salesAnalysis.find(id);

	if (iter != salesAnalysis.end()) {

		iter->second->setQuantity(iter->second->getQuantity() + stock->getQuantity());
	}
	else {

		salesAnalysis.insert(pair<unsigned int, StockItem*>(id, stock));
	}
}

void addSale(list<StockItem*> &stock, map<unsigned int, StockItem*> salesAnalysis) {

	list<StockItem*> salesList;
	string salesAssistant;
	cout << "Enter sales assistant name:" << endl;
	getline(cin, salesAssistant);
	cout << endl;

	int choice = 0;

	do {

		string findByName = "";
		cout << "Enter brand name:" << endl;
		getline(cin, findByName);
		cout << endl;
		searchByName(stock, findByName);

		unsigned int itemId;
		unsigned int quantity;

		cout << "Enter item id:" << endl;
		cin >> itemId;
		cout << endl;

		cout << "Enter item quantity:" << endl;
		cin >> quantity;
		cout << endl;

		StockItem* existingItem = findById(stock, itemId);

		if (existingItem != NULL) {

			if (existingItem->decreaseQuantity(quantity)) {

				StockItem* newItem = new StockItem(existingItem->getTitle(), existingItem->getColour(), existingItem->getSize(), quantity, existingItem->getCost());
				salesList.push_back(newItem);
				addToSalesMap(salesAnalysis, newItem, itemId);
			}
		}

		cout << "Enter 1 to continue, otherwise enter -1 to end Sale:" << endl;
		cin >> choice;

	} while (choice != -1);

	Sales* newSale = new Sales(salesAssistant, salesList);
	newSale->printReceipt();
	delete newSale;
}

StockItem* findById(list<StockItem*>& stock, unsigned int stockId) {

	list<StockItem*> results;
	IdFilter filter(stockId);
	auto it = copy_if(stock.begin(), stock.end(), back_inserter(results), filter);
	if (results.size() > 0){

		return results.front();

	}else{
		return NULL;
	}
}

void analyseSales(map<unsigned int, StockItem*> salesAnalysis) {

	map<unsigned int, StockItem*>::iterator iter;

	for (iter = salesAnalysis.begin(); iter != salesAnalysis.end(); iter++){

		iter->second->print();
	}
	salesAnalysis.clear();
}


void removeItem(list<StockItem*>& stock) {

	ZeroQuantityFilter filter;
	stock.remove_if(filter);
}