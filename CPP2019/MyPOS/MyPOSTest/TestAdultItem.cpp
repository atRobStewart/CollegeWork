#include "pch.h"
#include "CppUnitTest.h"
#include "..//MyPOS/MyPOSHeader.h"
#include "..//MyPOS/Item.h"
#include "..//MyPOS/AdultItem.h"
#include "..//MyPOS/ChildItem.h"
#include "..//MyPOS/Sales.h"


#include <functional>

using namespace Microsoft::VisualStudio::CppUnitTestFramework;

namespace TestAdultItem
{
	TEST_CLASS(TestAdultItem)
	{
	public:

		TEST_METHOD(ConstructorTest)
		{
			list<Item*> items;
			int size1 = items.size();
			int guess = 0;
			Assert::AreEqual(guess, size1);

			AdultItem s1("Denims Jeans", "Blue", itemSize::XL, 4, 59.99);
			items.push_back(&s1);
			int size2 = items.size();
			int guess2 = 1;
			Assert::AreEqual(guess2, size2);

			AdultItem s2;
			items.push_back(&s2);


		}
		TEST_METHOD(TestGetVat)
		{
			AdultItem s1("Denims Jeans", "Blue", itemSize::XL, 4, 59.99);
			double guessVat =  12.5979;
			Assert::AreEqual(guessVat, s1.getVat());
		}
		TEST_METHOD(TestCalculateCost)
		{
			AdultItem s1("Denims Jeans", "Blue", itemSize::XL, 4, 59.99);
			double total = (s1.getCost() + s1.getVat()) * s1.getQuantity();
			Assert::AreEqual(total, s1.calculateCost());
		}

	};




}

