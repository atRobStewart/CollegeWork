#pragma once
#include <string>
#include <sstream>
#include <iostream>
#include <iomanip>

using namespace std;

static unsigned int idCount = 2;
enum itemSize { XS, S, M, L, XL, XXL, XXXL, NA };

class Item {

	unsigned int id;
	string title;
	string colour;
	itemSize size;
	unsigned int quantity;
	double cost;

public:

	Item();

	Item(string t, string col, itemSize s, unsigned int quan, double c);

	unsigned int getId() const;
	string getTitle() const;
	string getColour() const;
	unsigned int getQuantity() const;
	double getCost() const;
	itemSize getSize();
	string getSizeString();

	void setID(int i);
	void setTitle(string t);
	void setColour(string c);
	void setSize(string s);
	void setQuantity(int q);
	virtual bool setCost(double c) = 0;

	bool decreaseQuantity(unsigned int quan);

	friend ostream& operator<<(ostream& o, itemSize& s);
	friend ostream& operator<<(ostream& o, Item& s);
	friend istream& operator>>(istream& in, Item& s);
	friend istream& operator>>(istream& in, itemSize& s);
	bool friend operator==(Item& s1, Item& s2);
	bool friend operator!=(Item& s1, Item& s2);



	

	virtual double calculateCost() = 0;
	virtual void print() = 0;



	virtual ~Item();
};