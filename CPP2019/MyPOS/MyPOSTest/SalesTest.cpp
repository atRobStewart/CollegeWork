#include "pch.h"
#include "CppUnitTest.h"
#include "..//MyPOS/MyPOSHeader.h"
#include "..//MyPOS/Item.h"
#include "..//MyPOS/AdultItem.h"
#include "..//MyPOS/ChildItem.h"
#include "..//MyPOS/Sales.h"

#include <functional>

using namespace Microsoft::VisualStudio::CppUnitTestFramework;

namespace SalesTest
{
	TEST_CLASS(SalesTest)
	{
	public:

		//Testing Constuctor is valid
		TEST_METHOD(ConstructorSalesTest)
		{
			list<Item*> stock;
			list<Sales*> salesList;
			int listSize1 = salesList.size();
			Assert::AreEqual(0, listSize1);

			AdultItem s1("Denims Jeans", "Blue", itemSize::XL, 4, 59.99);
			stock.push_back(&s1);
			string salesAssis = "Aisling";
			Sales sale1(salesAssis, stock);
			salesList.push_back(&sale1);
			int listSize2 = salesList.size();
			Assert::AreEqual(1, listSize2);

			Sales sale2;
			salesList.push_back(&sale2);
			int listSize3 = salesList.size();
			Assert::AreEqual(2, listSize3);

			stock.clear();
			salesList.clear();
		}
		//Testing get method for sales Assistant
		TEST_METHOD(GetAssistantTest)
		{

			list<Item*> stock;
			AdultItem s1("Denims Jeans", "Blue", itemSize::XL, 4, 59.99);
			stock.push_back(&s1);
			AdultItem s2("Levi Jeans", "Black", itemSize::XL, 4, 59.99);
			stock.push_back(&s2);
			string sa = "Aisling";
			Sales s(sa, stock);
			Assert::AreEqual(sa, s.getSalesAssistant());
			stock.clear();
		}
		//Testing set method for sales Assistant
		TEST_METHOD(SetAssistantTest)
		{

			list<Item*> stock;
			AdultItem s1("Denims Jeans", "Blue", itemSize::XL, 4, 59.99);
			stock.push_back(&s1);
			string sa = "Aisling";
			Sales s(sa, stock);
			string sa2 = "PJ";
			s.setSalesAssistant(sa2);
			Assert::AreEqual(sa2, s.getSalesAssistant());

			stock.clear();
		}
		//Testing exception set method for sales Assistant
		TEST_METHOD(exceptionSetSalesAssistantTest)
		{
			list<Item*> stock;
			AdultItem s1("Denims Jeans", "Blue", itemSize::XL, 4, 59.99);
			stock.push_back(&s1);
			string sa = "Aisling";
			Sales s(sa, stock);
			function<void(void)> f1 = [&s] {return s.setSalesAssistant(""); };
			Assert::ExpectException<domain_error>(f1);
		}

		//Testing get method for stockItems
		TEST_METHOD(GetStockItemsTest)
		{

			list<Item*> stock;
			AdultItem s1("Denims Jeans", "Blue", itemSize::XL, 4, 59.99);
			stock.push_back(&s1);
			AdultItem s2("Levi Jeans", "Black", itemSize::XL, 4, 59.99);
			stock.push_back(&s2);
			string salesAssis = "Bob";
			Sales s(salesAssis, stock);
			list<Item*> checkStock = s.getStockItems();
			Assert::AreEqual(true, stock == checkStock);
			stock.clear();
		}
		//Testing set method for sales Assistant
		TEST_METHOD(SetStockItemsTest)
		{
			list<Item*> stock;
			AdultItem s1("Denims Jeans", "Blue", itemSize::XL, 4, 59.99);
			stock.push_back(&s1);
			AdultItem s2("Levi Jeans", "Black", itemSize::S, 4, 59.99);
			stock.push_back(&s2);
			string salesAssis = "Bob";
			Sales s(salesAssis, stock);

			list<Item*> stock2;
			AdultItem stock21("Levi Jeans", "Blue", itemSize::XL, 1, 56.78);
			stock2.push_back(&stock21);
			AdultItem stock22("Levi Jumper", "Black", itemSize::XS, 1, 139.99);
			stock2.push_back(&stock22);
			AdultItem stock23("Levi Jeans", "Black", itemSize::XL, 1, 1.99);
			stock.push_back(&stock23);

			s.setStockItems(stock2);

			list<Item*> checkStock = s.getStockItems();

			Assert::AreEqual(true, stock2 == checkStock);
			Assert::AreEqual(false, stock == checkStock);

			stock.clear();
			stock2.clear();
		}

		//Testing exception set method for stockItems
		TEST_METHOD(exceptionSetStockItemsTest)
		{
			list<Item*> stock;
			Sales s;
			function<void(void)> f1 = [&] {return s.setStockItems(stock); };
			Assert::ExpectException<domain_error>(f1);
		}


		//Operator overloading test methods
		//Testing operator == True Case
		TEST_METHOD(EqualTrueTest)
		{

			list<Item*> stock;
			AdultItem s1("Denims Jeans", "Blue", itemSize::XL, 4, 59.99);
			stock.push_back(&s1);
			AdultItem s2("Levi Jeans", "Black", itemSize::XL, 4, 59.99);
			stock.push_back(&s2);
			string sa = "Aisling";
			Sales sale1(sa, stock);
			Sales sale2(sa, stock);
			Assert::AreEqual(true, sale1 == sale2);
			stock.clear();
		}
		//Testing operator == False Case
		TEST_METHOD(EqualFalseTest)
		{

			list<Item*> stock;
			AdultItem s1("Denims Jeans", "Blue", itemSize::XL, 4, 59.99);
			stock.push_back(&s1);
			AdultItem s2("Levi Jeans", "Black", itemSize::XL, 4, 59.99);
			stock.push_back(&s2);
			string sa = "Aisling";
			Sales sale1(sa, stock);
			string sa2 = "Rob";
			Sales sale2(sa2, stock);
			Assert::AreEqual(false, sale1 == sale2);
			stock.clear();
		}

		//Testing operator != True Case
		TEST_METHOD(NotEqualTrueTest)
		{

			list<Item*> stock;
			AdultItem s1("Denims Jeans", "Blue", itemSize::XL, 4, 59.99);
			stock.push_back(&s1);
			AdultItem s2("Levi Jeans", "Black", itemSize::XL, 4, 59.99);
			stock.push_back(&s2);
			string sa = "Aisling";
			Sales sale1(sa, stock);
			string sa2 = "Robert";
			Sales sale2(sa2, stock);
			Assert::AreEqual(true, sale1 != sale2);
			stock.clear();
		}

		//Testing operator != False Case
		TEST_METHOD(NotEqualFalseTest)
		{

			list<Item*> stock;
			AdultItem s1("Denims Jeans", "Blue", itemSize::XL, 4, 59.99);
			stock.push_back(&s1);
			AdultItem s2("Levi Jeans", "Black", itemSize::XL, 4, 59.99);
			stock.push_back(&s2);
			string sa = "Aisling";
			Sales sale1(sa, stock);
			Sales sale2(sa, stock);
			Assert::AreEqual(false, sale1 != sale2);
			stock.clear();
		}

		//Testing copy constructor

		//Testing assignment operator 
		//operator=

		TEST_METHOD(OperatorAssertTest)
		{

			list<Item*> stock;
			AdultItem s1("Denims Jeans", "Blue", itemSize::XL, 4, 59.99);
			stock.push_back(&s1);
			AdultItem s2("Levi Jeans", "Black", itemSize::XL, 4, 59.99);
			stock.push_back(&s2);
			Sales sale1("Aisling", stock);
			Sales sale2("Bob", stock);
			Sales sale3 = sale2;

			Assert::AreEqual(true, sale2 == sale3);
			Assert::AreEqual(false, sale1 == sale3);
			Assert::AreEqual(false, sale1 == sale2);

		}
	};
}
