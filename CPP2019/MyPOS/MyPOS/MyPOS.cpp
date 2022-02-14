/******************************************************
// This is the main. all function prototypes and bodies
// are stored in the MyPOSFunctions and MyPOSHeader.h
// to ease maintainability and readiblity.
*******************************************************/
#include "MyPOSHeader.h"

int main()
{
	map<unsigned int, Item*> salesMap;
	list<Item*> stock;
	loadStock(stock);

	map<int, funct> questions;
	questions[1] = question1;
	questions[2] = question2;
	questions[3] = question3;
	questions[4] = question4;
	questions[5] = question5;
	questions[6] = question6;
	questions[7] = question7;

	int choice = 0;
	do
	{
		cout << "1.Add Stock\n2.Add Sale\n3.Analyse Sales\n4.Search Stock\n5.Remove Item\n6.Store Stock\n7.Load Stock\nPlease enter question number, -1 to exit: ";
		cin >> choice;
		system("cls");
		cin.ignore(1000, '\n');
		if (questions.find(choice) != questions.end())
		{
			questions[choice](stock, salesMap);
		}
		else
		{
			cout << "Questions not found" << endl;
		}
	} while (choice != -1);
	storeStock(stock);
}




