#include "Item.h"

/***********************************
// Default constructor
************************************/
Item::Item() {

	this->id = idCount;
	idCount++;
	this->title = "";
	this->colour = "";
	this->size = XS;
	this->quantity = 0;
	this->cost = 0.0;
}

/***********************************
// Constructor with entered values.
************************************/
Item::Item(string t, string col, itemSize s, unsigned int quan, double c) {

	this->id = idCount;
	idCount++;
	this->title = t;
	this->colour = col;
	this->size = s;
	this->quantity = quan;
	this->cost = c;
}

/**********************************
// Copy constructor makes memory
// space for an already existing 
// item with identical attributes.
**********************************/
Item::Item(const Item* i)
{
	this->setID(i->getId());
	this->setTitle(i->getTitle());
	this->setColour(i->getColour());
	this->setQuantity(i->getQuantity());
	this->setSize("XS");
	this->setCost(i->getCost());


}


/***********************************
// Getters for each item attribute.
************************************/
unsigned int Item::getId()const { return id; }
string Item::getTitle()const { return title; }
string Item::getColour()const { return colour; }
unsigned int Item::getQuantity()const { return quantity; }
double Item::getCost()const { return cost; }
itemSize Item::getSize() { return size; }

/**************************************************************************
// returns size as a string, it's needed for the AddExistingStockFilter.h
***************************************************************************/
string Item::getSizeString() {

	string sizeString;

	switch (this->size) {

	case XS:
		sizeString = "XS";
		break;
	case S:
		sizeString = "S";
		break;
	case M:
		sizeString = "M";
		break;
	case L:
		sizeString = "L";
		break;
	case XL:
		sizeString = "XL";
		break;
	case XXL:
		sizeString = "XXL";
		break;
	case XXXL:
		sizeString = "XXXL";
		break;
	default:
		sizeString = "NA";
		break;
	}
	return sizeString;
}

/****************************************************
// Setters for Item with required validation
*****************************************************/
void Item::setID(int i) { id = i; }

void Item::setTitle(string t)
{
	if (t.length() >= 4)
	{
		title = t;
	}
	else
	{
		throw domain_error("Please enter a valid title");

	}
}

void Item::setColour(string c)
{
	if (c.length() >= 3)
	{
		colour = c;
	}
	else
	{
		throw domain_error("Please enter a valid colour");
	}

}

void Item::setSize(string s) {

	//Check input input
	if (s == "XS")
	{
		this->size = itemSize::XS;
	}
	else if (s == "S")
	{
		this->size = itemSize::S;
	}
	else if (s == "M")
	{
		this->size = itemSize::M;
	}
	else if (s == "L")
	{
		this->size = itemSize::L;
	}
	else if (s == "XL")
	{
		this->size = itemSize::XL;
	}
	else if (s == "XXL")
	{
		this->size = itemSize::XXL;
	}
	else if (s == "XXXL")
	{
		this->size = itemSize::XXXL;
	}
	else
	{
		throw domain_error("Please enter a valid size (XS,S,M,L,XL,XXL,XXXL)");
	}
}

void Item::setQuantity(int q) {
	if (q >= 1)
	{
		quantity = q;
	}
	else
	{
		throw domain_error("Please enter a quantity greater than 0");
	}

}

bool Item::setCost(double c) {
	if (c >= 0.01)
	{
		cost = c;
		return true;
	}
	else
	{
		throw domain_error("Price must be a positive double value");
		return false;
	}
	
}

/****************************************************
// Reduces quantity but only if the quantity is
// greater than zero.
****************************************************/
bool Item::decreaseQuantity(unsigned int quan) {

	if ((this->quantity - quan) >= 0) {

		this->quantity = this->quantity - quan;
		return true;
	}
	return false;
}

/****************************************************
// Operator overloads override the:
// '<<' '>>' '==' '!='
// operators.
****************************************************/
ostream& operator << (ostream& os, itemSize& s)
{
	switch (s)
	{
	case XS: os << "XS"; break;
	case S: os << "S"; break;
	case M: os << "M"; break;
	case L: os << "L"; break;
	case XL: os << "XL"; break;
	case XXL: os << "XXL"; break;
	case XXXL: os << "XXXL"; break;
	}
	return os;
}

ostream& operator<<(ostream& o, Item& s) {
	o << s.id << "~" << s.title << "~" << s.colour << "~" << s.size << "~" << s.quantity << "~" << s.cost;
	return o;
}

istream& operator>>(istream& in, itemSize& s)
{
	string sizeType;
	in >> sizeType;

	if (sizeType == "XS") {
		s = XS;
	}
	else if (sizeType == "S") {
		s = S;
	}
	else if (sizeType == "M") {
		s = M;
	}
	else if (sizeType == "L") {
		s = L;
	}
	else if (sizeType == "XL") {
		s = XL;
	}
	else if (sizeType == "XXL") {
		s = XXL;
	}
	else if (sizeType == "XXXL") {
		s = XXXL;
	}
	else {
		s = NA;
	}

	return in;
}

/*************************************************
// This overload had to be commented out due to
// the inheritence hierarchy in this program.
// It can't use both adult and child items.
*************************************************/
/*istream& operator>>(istream& in, Item& s)
{
	s = new AdultItem();
	string line;
	string title, colour, idString, size, qtyString, costString;
	int id, quantity;
	double cost;
	(getline(in, line));
	if (line != "")
	{
		stringstream ss(line);
		getline(ss, idString, '~');
		id = stoi(idString);
		s.setID(id);
		getline(ss, title, '~');
		s.setTitle(title);
		getline(ss, colour, '~');
		s.setColour(colour);
		getline(ss, size, '~');
		s.setSize(size);
		getline(ss, qtyString, '~');
		quantity = stoi(qtyString);
		s.setQuantity(quantity);
		getline(ss, costString, '~');
		cost = stod(costString);
		s.setCost(cost);
	}
	//cout << s << endl;
	return in;

}*/

bool operator==(Item& s1, Item& s2)
{
	return (
		s1.getTitle() == s2.getTitle() && 
		s1.getColour() == s2.getColour() && 
		s1.getSize() == s2.getSize() && 
		s1.getCost() == s2.getCost());
}

bool operator!=(Item& s1, Item& s2)
{
	return !(s1==s2);
}

/****************************************
// Calculates cost by multiplying the
// cost by the quantity of the item.
****************************************/
double Item::calculateCost() {

	return cost * quantity;
}

/*****************************************
// Prints out each item attribute.
*****************************************/
void Item::print() {

	cout <<setw(5) << left << this->id<< setw(5) << left << this->title << setw(10) << left << this->colour << setw(10) << left << this->size << setw(4) << left << this->quantity << setw(10) << left << this->cost;
}
