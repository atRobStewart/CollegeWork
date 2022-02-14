#include "pch.h"
#include "CppUnitTest.h"
#include "..//MyPOS/MyPOSHeader.h"
#include "..//MyPOS/Item.h"
#include "..//MyPOS/StockItem.h"
#include "..//MyPOS/Sales.h"

#include <functional>

using namespace Microsoft::VisualStudio::CppUnitTestFramework;

namespace StockTest
{
	TEST_CLASS(StockTest)
	{
	public:

		list<StockItem> stock;
		TEST_METHOD(TestSetSalesAssistant)
		{
			/*StockItem s1("Denims Jeans", "Blue", itemSize::XL, 1, 59.99);
			StockItem s2("Levi Jeans", "Black", itemSize::XS, 1, 159.99);
			stock.push_back(s1);
			stock.push_back(s2);
			string name = "Bob";
			Sales s(name, stock);
			string newName = "Aisling";
			s.setSalesAssistant("Aisling");
			Assert::AreEqual(newName, s.getSalesAssistant());*/

			
		}
		TEST_METHOD(getTitleTest)
		{
			StockItem s("Denims", "Blue", itemSize::XL, 4, 59.99);
			string actual = s.getTitle();
			string name = "Denims";
			Assert::AreEqual(name, actual);
		}
		TEST_METHOD(setTitleTest)
		{
			StockItem s("Denims", "Blue", itemSize::XL, 4, 59.99);
			string name = "Diesel";
			s.setTitle(name);
			Assert::AreEqual(name, s.getTitle());
		}
		TEST_METHOD(getColourTest)
		{
			StockItem s("Denims", "Blue", itemSize::XL, 4, 59.99);
			string actual = s.getColour();
			string col = "Blue";
			Assert::AreEqual(col, actual);
		}
		TEST_METHOD(setColourTest)
		{
			StockItem s("Denims", "Blue", itemSize::XL, 4, 59.99);
			string col = "Red";
			s.setColour(col);
			Assert::AreEqual(col, s.getColour());
		}
		TEST_METHOD(exceptionSetColourTest)
		{
			StockItem s("Denims", "Blue", itemSize::XL, 5, 59.99);
			function<void(void)> f1 = [&s] {return s.setColour("R"); };
			Assert::ExpectException<domain_error>(f1);
		}
		TEST_METHOD(getQuantityTest)
		{
			StockItem s("Denims", "Blue", itemSize::XL, 4, 59.99);
			int actual = s.getQuantity();
			Assert::AreEqual(4, actual);
		}
		TEST_METHOD(exceptionSetQuantityTest)
		{
			StockItem s("Denims", "Blue", itemSize::XL, 5, 59.99);
			function<void(void)> f1 = [&s] {return s.setQuantity(-1); };
			Assert::ExpectException<domain_error>(f1);
		}


		TEST_METHOD(getCostTest)
		{
			StockItem s("Denims", "Blue", itemSize::XL, 4, 59.99);
			double actual = s.getCost();
			Assert::AreEqual(59.99, actual);
		}

		TEST_METHOD(setCostTest)
		{
			StockItem s("Denims", "Blue", itemSize::XL, 4, 59.99);
			s.setCost(99.99);
			Assert::AreEqual(99.99, s.getCost());
		}
		TEST_METHOD(exceptionSetCostTest)
		{
			StockItem s("Denims", "Blue", itemSize::XL, 4, 59.99);
			function<void(void)> f1 = [&s] {return s.setCost(-1); };
			Assert::ExpectException<domain_error>(f1);
		}


		TEST_METHOD(OperatorEqualTrueTest)
		{

			StockItem s1("Denims Jeans", "Blue", itemSize::XL, 4, 59.99);
			StockItem s2("Denims Jeans", "Blue", itemSize::XL, 4, 59.99);
			Assert::AreEqual(true, s1 == s2);
		}
		TEST_METHOD(OperatorEqualFalseTest)
		{

			StockItem s1("Denims Jeans", "Blue", itemSize::XL, 4, 59.99);
			StockItem s2("Denims Jeans", "Black", itemSize::XL, 4, 59.99);
			Assert::AreEqual(false, s1 == s2);
		}

		TEST_METHOD(OperatorNotEqualTrueTest)
		{

			StockItem s1("Denims Jeans", "Blue", itemSize::XL, 4, 59.99);
			StockItem s2("Denims Jeans", "Black", itemSize::XL, 4, 59.99);
			Assert::AreEqual(true, s1 != s2);
		}
		TEST_METHOD(OperatorNotEqualFalseTest)
		{

			StockItem s1("Denims Jeans", "Blue", itemSize::XL, 4, 59.99);
			StockItem s2("Denims Jeans", "Blue", itemSize::XL, 4, 59.99);
			Assert::AreEqual(false, s1 != s2);
		}




	};
}