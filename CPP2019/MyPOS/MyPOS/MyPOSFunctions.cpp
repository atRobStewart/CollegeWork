/********************************************************
// This file holds majority of the functionality of this
// application, it includes the MyPOSHeader.h as all the
// function prototypes and necessary #includes are within
// it to get read by the compiler before the function
// bodies are seen.
*********************************************************/
#include "MyPOSHeader.h"

list<Item*> stock;

/********************************************************
// Function stores all items in the stock list to an
// external text file with attributes that have been
// entered by the user using the addStock function.
// Dynamic cast will ensure items are  written to the 
// file as their respective adult and child items.
*********************************************************/
bool storeStock(list<Item*>& stock)
{
	bool stored = false;
	ofstream out("stockOut.txt");
	if (out)
	{
		for (Item* s : stock)
		{
			if (dynamic_cast<AdultItem*>(s) != nullptr) {

				out << "AdultItem~" << *s << endl;
			}
			else if(dynamic_cast<ChildItem*>(s) != nullptr){

				out << "ChildItem~" << *s << endl;
			}
		}
		string stars(30, '*');
		cout << "\n\t" << stars << endl;
		cout << "\t  Stock stored successfully" << endl;
		cout << "\t" << stars << "\n" << endl;
		stored = true;
		out.close();
	}
	else
	{
		cout << "Error opening file" << endl;
		stored = false;
	}

	return stored;
}

/********************************************************
// Function loads all stock from the file into a list
// of stock items. Getline retrieve all the information
// and the if statement uses type to define whether the
// items are child or adult items. Error will be thrown
// if the file is not detected.
*********************************************************/
bool loadStock(list<Item*>& stock)
{
	bool loaded = false;
	//leave commented out
	/*ifstream in("stockOut.txt");
	if (in)
	{
		while (!in.eof())
		{
			Item* s;// = new Item();
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
	}*/
	ifstream in("stockOut.txt");
	
	if (in)
		{

			Item* s;
			string line;
			string type, title, colour, idString, size, qtyString, costString;
			int id, quantity;
			double cost;
			while (getline(in, line))
			{
				stringstream ss(line);
				getline(ss, type, '~');
				getline(ss, idString, '~');
				id = stoi(idString);
				getline(ss, title, '~');
				getline(ss, colour, '~');
				getline(ss, size, '~');
				getline(ss, qtyString, '~');
				quantity = stoi(qtyString);
				getline(ss, costString, '~');
				cost = stod(costString);

				if (type == "AdultItem") {
					s = new AdultItem();
					s->setID(id);
					s->setTitle(title);
					s->setColour(colour);
					s->setSize(size);
					s->setQuantity(quantity);
					s->setCost(cost);
					stock.push_back(s);
				}
				else {
					s = new ChildItem();
					s->setID(id);
					s->setTitle(title);
					s->setColour(colour);
					s->setSize(size);
					s->setQuantity(quantity);
					s->setCost(cost);
					stock.push_back(s);
				}
			}

			loaded = true;
		}
		else
		{
			loaded = false;
			cout << "Error opening file." << endl;
		}

	return loaded;
		
}

/******************************************************************
// All question functions use typedef function and a map in MyPOS
// for use in the user interface.
//
// Note that question1 now has all of the user input functionality
// stored within it, this uses the addStock function once all input
// is correctly entered.
*******************************************************************/
void question1(list<Item*> &stock, map<unsigned int, Item*> &salesAnalysis)
{

	unsigned int itemId;
	string itemTitle;
	string itemColour;
	string itemSizing;
	int itemQuantity;
	double itemCost;
	int itemChoice;
	bool isValid = false;

	cout << "Adult item press 1:\nChild item press 2: " << endl;
	do {
		cin >> itemChoice;
		cin.ignore(100, '\n');
		cout << endl;
		isValid = true;

		if (cin.fail()) {

			cout << "Please enter '1' for Adult item or '2' for Child item: " << endl;
			isValid = false;
			cin.clear();
			cin.ignore(100, '\n');
		}
		else if (itemChoice <= 0 || itemChoice >= 3) {

			cout << "Please enter '1' for Adult item or '2' for Child item: " << endl;
			isValid = false;
		}
	} while (!isValid);

	cout << "Enter item title: " << endl;
	do {
		getline(cin, itemTitle);
		cout << endl;

		if (itemTitle.length() < 4) {

			cout << "Please enter a title with 4 or more characters: " << endl;
		}
	} while (itemTitle.length() < 4);

	cout << "Enter item colour: " << endl;
	do {
		getline(cin, itemColour);
		cout << endl;

		if (itemColour.length() < 3) {

			cout << "Please enter a Colour with 3 or more characters: " << endl;
		}
	} while (itemColour.length() < 3);

	cout << "Enter item size: " << endl;
	do {
		cin >> itemSizing;
		cout << endl;
		if (itemSizing != "XS" && itemSizing != "S" && itemSizing != "M" && itemSizing != "L" && itemSizing != "XL" && itemSizing != "XXL" && itemSizing != "XXXL") {

			cout << "Please enter 1 of the sizes 'XS', 'S', 'M', 'L', 'XL', 'XXL', 'XXXL': " << endl;
		}
	} while (itemSizing != "XS" && itemSizing != "S" && itemSizing != "M" && itemSizing != "L" && itemSizing != "XL" && itemSizing != "XXL" && itemSizing != "XXXL");

	cout << "Enter item quantity: " << endl;
	isValid = false;
	do {

		cin >> itemQuantity;
		cout << endl;
		isValid = true;
		if (cin.fail()) {

			cout << "Please enter a valid whole number: " << endl;
			isValid = false;
			cin.clear();
			cin.ignore(100, '\n');
		}
		else if (itemQuantity < 1) {

			isValid = false;
			cout << "Please enter a positive number: " << endl;
		}

	} while (!isValid);

	cout << "Enter item Cost: " << endl;
	isValid = false;
	do {

		cin >> itemCost;
		cout << endl;
		isValid = true;

		if (cin.fail()) {
			cout << "Please enter a valid price: " << endl;
			isValid = false;
			cin.clear();
			cin.ignore(100, '\n');
		}
		else if (itemCost < 0.01) {

			isValid = false;
			cout << "Please enter a positive price: " << endl;
		}

	} while (!isValid);

	
	Item* newItem;

	if (itemChoice == 1) {

		newItem = new AdultItem();
		newItem->setTitle(itemTitle);
		newItem->setColour(itemColour);
		newItem->setSize(itemSizing);
		newItem->setQuantity(itemQuantity);
		newItem->setCost(itemCost);
	}
	else {

		newItem = new ChildItem();

		newItem->setTitle(itemTitle);
		newItem->setColour(itemColour);
		newItem->setSize(itemSizing);
		newItem->setQuantity(itemQuantity);
		newItem->setCost(itemCost);
	}
	addStock(stock,newItem);
}

void question2(list<Item*> &stock, map<unsigned int, Item*> &salesAnalysis) {

	addSale(stock, salesAnalysis);
}

void question3(list<Item*> &stock, map<unsigned int, Item*> &salesAnalysis) {

	analyseSales(salesAnalysis);
}

void question4(list<Item*> &stock, map<unsigned int, Item*> &salesAnalysis){

	int option;
	cout << "Options:\n1.Search by ID\n2.Search by name\n3.Search by size" << endl;
	cin >> option;
	cin.ignore(1000, '\n');
	int itemId = 0;
	bool isValid;
	string name;
	string size;

	switch(option)
	{
		case 1:
			cout << "Please enter ID you wish to search: " << endl;
			isValid = false;
			do {

				cin >> itemId;
				cout << endl;
				isValid = true;
				if (cin.fail()) {

					cout << "Please enter a valid whole number: " << endl;
					isValid = false;
					cin.clear();
					cin.ignore(100, '\n');
				}
				else if (itemId < 1) {

					isValid = false;
					cout << "Please enter a positive number: " << endl;
				}

			} while (!isValid);
			
			searchById(stock, itemId);
			break;

		case 2:
			cout << "Please enter Name you wish to search: " << endl;
			do {
				getline(cin, name);
				cout << endl;

				if (name.length() < 4) {

					cout << "Please enter a title with 4 or more characters: " << endl;
				}
			} while (name.length() < 4);
			searchByName(stock, name);
			break;
		case 3:
			cout << "Please enter size you wish to search: " << endl;
			do {
				cin >> size;
				cout << endl;
				if (size != "XS" && size != "S" && size != "M" && size != "L" && size != "XL" && size != "XXL" && size != "XXXL") {

					cout << "Please enter 1 of the sizes 'XS', 'S', 'M', 'L', 'XL', 'XXL', 'XXXL': " << endl;
				}
			} while (size != "XS" && size != "S" && size != "M" && size != "L" && size != "XL" && size != "XXL" && size != "XXXL");

			searchBySize(stock, size);
			break;
		
		

	}
		
		
		
}

void question5(list<Item*> &stock, map<unsigned int, Item*> &salesAnalysis) {
	int id;
	cout << "Please enter ID of item to remove: " << endl;
	cin >> id;
	removeItem(stock,id);
}

void question6(list<Item*> &stock, map<unsigned int, Item*> &salesAnalysis)
{
	//writing to file
	storeStock(stock);
}

void question7(list<Item*> &stock, map<unsigned int, Item*> &salesAnalysis)
{
	//reading from file
	cout << "\nBrands Recorded in file:\n";
	for (list<Item*>::iterator iter = stock.begin(); iter != stock.end(); iter++)
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

void question8(list<Item*>& stock, map<unsigned int, Item*> &salesAnalysis)
{

	int id;
	cout << "Please enter ID of item to remove: " << endl;
	cin >> id;
	removeItem(stock,id);
}

/*******************************************************
// Function takes in an id and finds the items if it 
// exists and prints the information, this uses the 
// IdFilter predicate that is in the header files
// as IdFilter.h
********************************************************/
void searchById(list<Item*> &stock,int id)
{
	
	
	list<Item*> results;
	IdFilter filter(id);
	auto it = copy_if(stock.begin(), stock.end(), back_inserter(results), filter);
	if (results.size() > 0)
	{
		cout << "\n";
		for (Item* ptr : results)
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

/*******************************************************
// Function takes in a name and finds the item if it
// exists and prints the information, this uses the 
// NameFilter predicate that is in the header files
// as NameFilter.h
********************************************************/
bool searchByName(list<Item*> &stock, string name)
{
	list<Item*> results;
	NameFilter filter(name);
	auto it = copy_if(stock.begin(), stock.end(), back_inserter(results), filter);
	if (results.size() > 0)
	{
		cout << "\n";
		for (Item* ptr : results)
		{
			ptr->print();
			cout << "\n";
		}
		return true;
	}
	else
	{
		cout << "No items found." << endl;
	}
	return false;
}

/*******************************************************
// Function takes in a size and finds the item if it
// exists and prints the information, this uses the 
// SizeFilter predicate that is in the header files
//  as SizeFilter.h
********************************************************/
void searchBySize(list<Item*> &stock, string size)
{
	AdultItem s1;
	s1.setSize(size);
	list<Item*> results;
	SizeFilter filter(s1.getSize());
	auto it = copy_if(stock.begin(), stock.end(), back_inserter(results), filter);
	if (results.size() > 0)
	{
		cout << "\n";
		for (Item* ptr : results)
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
// If the item does not exist an new Item will get added to the  
// passed in Stock list, if the Item already exists which is 
// determined through the findExistingStockItem() function, the
// user entered quantity gets added to the items current quantity.

// This Function uses the findExistingStockItem() function
// within this file.
*******************************************************************/
void addStock(list<Item*> &stock, Item* aStock) {

	//unsigned int itemId = aStock->getId();
	string itemTitle = aStock->getTitle();
	string itemColour=aStock->getColour();
	string itemSizing = aStock->getSizeString();
	unsigned int itemQuantity = aStock->getQuantity();
	double itemCost=aStock->getCost();

	

	Item* existingItem = findExistingStockItem(stock, itemTitle, itemColour, itemSizing, itemCost);

	if (existingItem == NULL) {

		
		stock.push_back(aStock);

		cout << "Item successfully added." << endl;
	}else{

		existingItem->setQuantity(existingItem->getQuantity() + itemQuantity);

		cout << "Item successfully updated." << endl;

		delete aStock;
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
Item* findExistingStockItem(list<Item*>& stock, string title, string colour, string size, double cost){

	list<Item*> results;
	AddExistingStockFilter filter(title, colour, size, cost);
	auto it = copy_if(stock.begin(), stock.end(), back_inserter(results), filter);
	if (results.size() > 0){

		return results.front();
	}else{

		return NULL;
	}
}

/*************************************************************************
// Adds sales to the map by finding the id, the quantity of sales is set 
// by adding the stock quantity to the salesAnalysis quantity defined by 
// the iterator.
// The id and stock is then inserted into the salesAnalysis. If no entry
// is found it will insert a new one.
//
// This function is used in the body of the addSale function.
**************************************************************************/
void addToSalesMap(map<unsigned int, Item*> &salesAnalysis, Item*& stock, unsigned int id) {

	map<unsigned int, Item*>::iterator iter;
	iter = salesAnalysis.find(id);

	if (iter != salesAnalysis.end()) {

		iter->second->setQuantity(iter->second->getQuantity() + stock->getQuantity());
	}
	else {

		salesAnalysis.insert(pair<unsigned int, Item*>(id, stock));
	}
}

/************************************************************************
// This function allows the user to add sales, a sales assistant will
// enter their details and search for items by their brands, finding one
// will prodouce a list of items with their details. 
//
// The user will enter the id of the required item, next they will be
// asked for a quanitity in the event that more than one of the same 
// item is being purchased.
//
// The salesList will get populated by existing stock items from the
// the stock item list, this stock item will also have sold items 
// removed by their quantity. 
//
// Dynamic cast will take existing items and determine if they are
// adult or child items storing them in a newItem pointer which will
// see use in the addToSalesMap function in this file.
// 
*************************************************************************/
void addSale(list<Item*> &stock, map<unsigned int, Item*> &salesAnalysis) {

	list<Item*> salesList;
	string salesAssistant;
	cout << "Enter sales assistant name:" << endl;

	do {
		getline(cin, salesAssistant);
		cout << endl;

		if (salesAssistant.length() < 2) {

			cout << "Please enter a name with 2 or more characters: " << endl;
		}
	} while (salesAssistant.length() < 2);
	

	cout << endl;

	int choice = 0;

	do {

		bool hasDisplayedStock = false;
		do {
			string findByName = "";
			cout << "Enter brand name:" << endl;
			getline(cin, findByName);
			//for_each(findByName.begin(), findByName.end(), [](char & c) { c = ::tolower(c); });
			cout << endl;
			hasDisplayedStock = searchByName(stock, findByName);

		} while (!hasDisplayedStock);

		int itemId;
		int quantity;
		bool isValid = false;

		cout << "Enter sale item id:" << endl;
		isValid = false;
		do {
			cin >> itemId;
			cout << endl;
			isValid = true;
			if (cin.fail()) {

				cout << "Please enter a valid whole number: " << endl;
				isValid = false;
				cin.clear();
				cin.ignore(100, '\n');
			}
			else if (itemId <= 0) {

				isValid = false;
				cout << "Please enter a positive number: " << endl;
			}
		} while (!isValid);

		cout << "Enter sale item quantity: " << endl;
		isValid = false;
		do {

			cin >> quantity;
			cout << endl;
			isValid = true;
			if (cin.fail()) {

				cout << "Please enter a valid whole number: " << endl;
				isValid = false;
				cin.clear();
				cin.ignore(100, '\n');
			}
			else if (quantity < 1) {

				isValid = false;
				cout << "Please enter a positive number: " << endl;
			}

		} while (!isValid);

		Item* existingItem = findById(stock, itemId);

		if (existingItem != NULL) {

			if (existingItem->decreaseQuantity(quantity)) {
				Item* newItem;
				if (dynamic_cast<AdultItem*>(existingItem) != nullptr) {

					newItem = new AdultItem(existingItem->getTitle(), existingItem->getColour(), existingItem->getSize(), quantity, existingItem->getCost());
				}
				else if (dynamic_cast<ChildItem*>(existingItem) != nullptr) {

					newItem = new ChildItem(existingItem->getTitle(), existingItem->getColour(), existingItem->getSize(), quantity, existingItem->getCost());
				}
				salesList.push_back(newItem);
				addToSalesMap(salesAnalysis, newItem, itemId);
			}
			else {
				cout << "Not enough of requested item in stock or stock is empty. " << endl;
			}
		}

		cout << "Enter 1 to continue, otherwise enter -1 to end Sale: " << endl;
		do {
			cin >> choice;
			cin.ignore(100, '\n');
			if (cin.fail()) {

				cout << "Enter 1 to continue, otherwise enter -1 to end Sale: " << endl;
				isValid = false;
				cin.clear();
				cin.ignore(100, '\n');
			}
			else if (choice != -1 && choice != 1) {

				isValid = false;
				cout << "Enter 1 to continue, otherwise enter -1 to end Sale: " << endl;
			}

		} while (choice != -1 && choice != 1);

	} while (choice != -1);

	Sales* newSale = new Sales(salesAssistant, salesList);
	newSale->printReceipt();
	delete newSale;
}

Item* findById(list<Item*>& stock, unsigned int stockId) {


	list<Item*> results;
	IdFilter filter(stockId);
	auto it = copy_if(stock.begin(), stock.end(), back_inserter(results), filter);
	if (results.size() > 0){

		return results.front();

	}else{
		return NULL;
	}
}

/***********************************************************
// This function prints the sales analysis, the passed in  
// map keeps track of this and gets emptyed anytime a sales
// analysis is run by the user.
************************************************************/
void analyseSales(map<unsigned int, Item*> &salesAnalysis) {

	map<unsigned int, Item*>::iterator iter;

	for (iter = salesAnalysis.begin(); iter != salesAnalysis.end(); iter++){

		iter->second->print();
	}
	salesAnalysis.clear();
}

/***********************************************************
// First this function checks if any of the ids are in the 
// list it then iterates through the stock item, if the 
// iterator picks up an id equal to the user entered id the
// item will be deleted.
************************************************************/
void removeItem(list<Item*>& stock, int id) {

	IdFilter filter(id);
	
	if((any_of(stock.begin(), stock.end(), filter)))
	{
		for (list<Item*>::iterator iter = stock.begin(); iter != stock.end(); iter++)
		{
			if ((*iter)->getId() == id)
			{
				(*iter)->print();
				iter = stock.erase(iter);
				string stars(30, '*');
				cout << "\n\t" << stars << endl;
				cout << "\t  Item deleted successfully" << endl;
				cout << "\t" << stars << "\n" << endl;
			}
			
		}
			
	}
	else
	{
		throw domain_error("No items exist with that ID");
	}

	

}