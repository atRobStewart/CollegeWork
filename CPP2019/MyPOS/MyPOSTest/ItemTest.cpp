#include "pch.h"
#include "CppUnitTest.h"
#include "..//MyPOS/MyPOSHeader.h"
#include "..//MyPOS/Item.h"
#include "..//MyPOS/AdultItem.h"
#include "..//MyPOS/ChildItem.h"
#include "..//MyPOS/Sales.h"

#include <functional>

using namespace Microsoft::VisualStudio::CppUnitTestFramework;

namespace ItemTest
{
	TEST_CLASS(ItemTest)
	{
	public:

		//Testing the get method for ID as ID variable is static
		TEST_METHOD(GetIDTest)
		{

			list<AdultItem*> stock;

			AdultItem s1("Denims Jeans", "Blue", itemSize::XL, 4, 59.99);
			stock.push_back(&s1);
			//Testing the static ID starts at 2
			int actualID1 = s1.getId();
			Assert::AreEqual(26, actualID1);

			AdultItem s2("Levi Jumper", "Pink", itemSize::L, 4, 123.45);
			stock.push_back(&s2);

			AdultItem s3;
			stock.push_back(&s3);

			AdultItem s4("Guicci T-Shirt", "Yellow", itemSize::M, 7, 67.89);
			stock.push_back(&s4);

			int actualID2 = s4.getId();
			//Ensuring the static ID adds one everytime an item is added
			Assert::AreEqual(29, actualID2);

			stock.clear();
		}
		//Testing Constuctor is valid
		TEST_METHOD(ConstructorTest)
		{ 

			list<AdultItem*> stock;
			int s = stock.size();
			Assert::AreEqual(0, s);

			AdultItem s1("Denims Jeans", "Blue", itemSize::XL, 4, 59.99);
			stock.push_back(&s1);
			int listSize2 = stock.size();
			Assert::AreEqual(1, listSize2);

			AdultItem s2;
			stock.push_back(&s2);
			int listSize3 = stock.size();
			Assert::AreEqual(2, listSize3);

			stock.clear();
		}
		

		//Testing get and set for Title 
		TEST_METHOD(getTitleTest)
		{
			AdultItem s("Denims", "Blue", itemSize::XL, 4, 59.99);
			string actual = s.getTitle();
			string name = "Denims";
			Assert::AreEqual(name, actual);
		}
		TEST_METHOD(setTitleTest)
		{
			AdultItem s("Denims", "Blue", itemSize::XL, 4, 59.99);
			string name = "Diesel";
			s.setTitle(name);
			Assert::AreEqual(name, s.getTitle());
		}
		TEST_METHOD(exceptionSetTitleTest)
		{
			AdultItem s("Denims", "Blue", itemSize::XL, 5, 59.99);
			function<void(void)> f1 = [&s] {return s.setTitle("Z"); };
			Assert::ExpectException<domain_error>(f1);
		}

		//Testing get and set methods for colour
		TEST_METHOD(getColourTest)
		{
			AdultItem s("Denims", "Blue", itemSize::XL, 4, 59.99);
			string actual = s.getColour();
			string col = "Blue";
			Assert::AreEqual(col, actual);
		}
		TEST_METHOD(setColourTest)
		{
			AdultItem s("Denims", "Blue", itemSize::XL, 4, 59.99);
			string col = "Red";
			s.setColour(col);
			Assert::AreEqual(col, s.getColour());
		}
		TEST_METHOD(exceptionSetColourTest)
		{
			AdultItem s("Denims", "Blue", itemSize::XL, 5, 59.99);
			function<void(void)> f1 = [&s] {return s.setColour("R"); };
			Assert::ExpectException<domain_error>(f1);
		}
		//Testing get and set methods for size
		TEST_METHOD(getSizeTest)
		{
			AdultItem s("Denims", "Blue", itemSize::XL, 4, 59.99);
			string actual = s.getSizeString();
			string expect = "XL";
			Assert::AreEqual(expect, actual);
		}
		TEST_METHOD(setSizeTest)
		{
			AdultItem s("Denims", "Blue", itemSize::XL, 4, 59.99);
			s.setSize("XS");
			string actual = s.getSizeString();
			string expect = "XS";
			Assert::AreEqual(expect, actual);
		}
		TEST_METHOD(exceptionSetSizeTest)
		{
			AdultItem s("Denims", "Blue", itemSize::XL, 5, 59.99);
			function<void(void)> f1 = [&s] {return s.setSize("1234"); };
			Assert::ExpectException<domain_error>(f1);
		}


		//Testing get and set methods for quantity
		TEST_METHOD(getQuantityTest)
		{
			AdultItem s("Denims", "Blue", itemSize::XL, 4, 59.99);
			int actual = s.getQuantity();
			Assert::AreEqual(4, actual);
		}
		TEST_METHOD(setQuantityTest)
		{
			AdultItem s("Denims", "Blue", itemSize::XL, 4, 59.99);
			s.setQuantity(100);
			int actual = s.getQuantity();
			Assert::AreEqual(100, actual);
		}
		TEST_METHOD(exceptionSetQuantityTest)
		{
			AdultItem s("Denims", "Blue", itemSize::XL, 5, 59.99);
			function<void(void)> f1 = [&s] {return s.setQuantity(-1); };
			Assert::ExpectException<domain_error>(f1);
		}

		//Testing get and set methods for cost 
		TEST_METHOD(getCostTest)
		{
			AdultItem s("Denims", "Blue", itemSize::XL, 4, 59.99);
			double actual = s.getCost();
			Assert::AreEqual(59.99, actual);
		}

		TEST_METHOD(setCostTest)
		{
			AdultItem s("Denims", "Blue", itemSize::XL, 4, 59.99);
			s.setCost(99.99);
			Assert::AreEqual(99.99, s.getCost());
		}
		TEST_METHOD(exceptionSetCostTest)
		{
			AdultItem s("Denims", "Blue", itemSize::XL, 4, 59.99);
			function<void(void)> f1 = [&s] {return s.setCost(-1); };
			Assert::ExpectException<domain_error>(f1);
		}

		//Operator overloaded methods
		//Testing operator == 
		TEST_METHOD(OperatorEqualTrueTest)
		{

			AdultItem s1("Denims Jeans", "Blue", itemSize::XL, 4, 59.99);
			AdultItem s2("Denims Jeans", "Blue", itemSize::XL, 4, 59.99);
			Assert::AreEqual(true, s1 == s2);
		}
		TEST_METHOD(OperatorEqualFalseTest)
		{
			
			AdultItem s1("Denims Jeans", "Blue", itemSize::XL, 4, 59.99);
			AdultItem s2("Denims Jeans", "Black", itemSize::XL, 4, 59.99);
			Assert::AreEqual(false, s1==s2);
		}

		//Testing operator !=
		TEST_METHOD(OperatorNotEqualTrueTest)
		{

			AdultItem s1("Denims Jeans", "Blue", itemSize::XL, 4, 59.99);
			AdultItem s2("Denims Jeans", "Black", itemSize::XL, 4, 59.99);
			Assert::AreEqual(true, s1 != s2);
		}
		TEST_METHOD(OperatorNotEqualFalseTest)
		{

			AdultItem s1("Denims Jeans", "Blue", itemSize::XL, 4, 59.99);
			AdultItem s2("Denims Jeans", "Blue", itemSize::XL, 4, 59.99);
			Assert::AreEqual(false, s1 != s2);
		}


		//Testing assignment operator 
		//operator=
		TEST_METHOD(OperatorAssertTrueTest)
		{

			AdultItem s1("Denims Jeans", "Blue", itemSize::XL, 4, 59.99);
			AdultItem s2 = s1;
			Assert::AreEqual(true, s1 == s2);
		}
		TEST_METHOD(OperatorAssertFalseTest)
		{

			AdultItem s1("Denims Jeans", "Blue", itemSize::XL, 4, 59.99);
			AdultItem s2("Denims Jeans", "Black", itemSize::XL, 4, 59.99);
			AdultItem s3 = s1;
			Assert::AreEqual(false, s3 == s2);

		}

		

		
	};
}