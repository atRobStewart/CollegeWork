#include "pch.h"
#include "CppUnitTest.h"
#include "..//MyPOS/MyPOSHeader.h"
#include "..//MyPOS/Item.h"
#include "..//MyPOS/AdultItem.h"
#include "..//MyPOS/ChildItem.h"
#include "..//MyPOS/Sales.h"


#include <functional>

using namespace Microsoft::VisualStudio::CppUnitTestFramework;

namespace AMainPOSFunctionsTest
{
	TEST_CLASS(AMainPOSFunctionsTest)
	{
	public:


		TEST_METHOD(RemoveItemTest)
		{

			list<Item*> stock;
			int s = stock.size();
			Assert::AreEqual(0, s);

			AdultItem s1("Denims Jeans", "Blue", itemSize::XL, 4, 59.99);
			stock.push_back(&s1);

			AdultItem s2("Denims Jeans", "Black", itemSize::XL, 4, 59.99);
			stock.push_back(&s2);

			AdultItem s3("Denims Jeans", "Grey", itemSize::XS, 4, 59.99);
			stock.push_back(&s3);

			int listSize = stock.size();
			Assert::AreEqual(3, listSize);

			int id = 6;

			removeItem(stock, id);
			int listSize2 = stock.size();
			Assert::AreEqual(2, listSize2);

			stock.clear();

		}

		TEST_METHOD(SearchByNameTrueTest)
		{

			list<Item*> stock;
			AdultItem s1("Denims Jeans", "Blue", itemSize::XL, 4, 59.99);
			stock.push_back(&s1);

			AdultItem s2("Denims Jeans", "Black", itemSize::M, 4, 59.99);
			stock.push_back(&s2);

			AdultItem s3("Levi Jeans", "Grey", itemSize::XS, 4, 59.99);
			stock.push_back(&s3);
			
			bool found = searchByName(stock, "Levi");
			Assert::AreEqual(true, found );

			stock.clear();

		}
		TEST_METHOD(SearchByNameFalseTest)
		{

			list<Item*> stock;
			AdultItem s1("Denims Jeans", "Blue", itemSize::XL, 4, 59.99);
			stock.push_back(&s1);

			AdultItem s2("Denims Jeans", "Black", itemSize::M, 4, 59.99);
			stock.push_back(&s2);

			AdultItem s3("Levi Jeans", "Grey", itemSize::XS, 4, 59.99);
			stock.push_back(&s3);

			bool found = searchByName(stock, "Gucci");
			Assert::AreEqual(false, found);

			stock.clear();

		}

		TEST_METHOD(FindByIDTest)
		{

			list<Item*> stock;
			Item *s1 = new AdultItem("Denims Jeans", "Blue", itemSize::XL, 4, 59.99);
			stock.push_back(s1);

			Item* s2 = new AdultItem("Denims Jeans", "Black", itemSize::M, 4, 59.99);
			stock.push_back(s2);

			Item* s3 = new AdultItem("Levi Jeans", "Grey", itemSize::XS, 4, 59.99);
			stock.push_back(s3);

			Item* result = findById(stock, 4);
			Assert::AreEqual(true, s3 == result);

			stock.clear();

		}

		

		TEST_METHOD(findExistingStockItemTest)
		{

			list<Item*> stock;
			Item* s1 = new AdultItem("Denims Jeans", "Blue", itemSize::XL, 4, 59.99);
			stock.push_back(s1);

			Item* s2 = new AdultItem("Denims Jeans", "Black", itemSize::M, 4, 59.99);
			stock.push_back(s2);

			Item* s3 = new AdultItem("Levi Jeans", "Grey", itemSize::XS, 4, 59.99);
			stock.push_back(s3);

			Item* result = findExistingStockItem(stock, "Levi Jeans" ,"Grey","XS",59.99 );
			Assert::AreEqual(true, s3 == result);

			stock.clear();

		}
		TEST_METHOD(addStockItemTest)
		{

			list<Item*> stock;
			int listSize = stock.size();
			Assert::AreEqual(0, listSize);

			Item* s1 = new AdultItem("Guess Jacket", "Purple", itemSize::M, 10, 89.90);
			addStock(stock, s1);

			int listSize2 = stock.size();
			Assert::AreEqual(1, listSize2);


			stock.clear();

		}
		/*TEST_METHOD(notaddStockItemTest)
		{

			list<Item*> stock;
			int listSize = stock.size();
			Assert::AreEqual(0, listSize);
			
			Item* s1 = new AdultItem("Guess Jacket", "Purple", itemSize::M, -1, 89.90);
			
			function<void(void)> f1 = [&] {return addStock(stock, s1); };
			Assert::ExpectException<domain_error>(f1);

		}*/

		TEST_METHOD(StoreStockTest)
		{

			list<Item*> stock;
			Item* s1 = new AdultItem("Denims Jeans", "Blue", itemSize::XL, 4, 59.99);
			stock.push_back(s1);

			Item* s2 = new AdultItem("Denims Jeans", "Black", itemSize::M, 4, 59.99);
			stock.push_back(s2);

			Item* s3 = new AdultItem("Levi Jeans", "Grey", itemSize::XS, 4, 59.99);
			stock.push_back(s3);

			bool stored = storeStock(stock);

			Assert::AreEqual(true, stored);

		}

		

		TEST_METHOD(loadStockTest)
		{

			list<Item*> stock;
			bool loaded = loadStock(stock);

			Assert::AreEqual(true, loaded);

		}

		

	};




}
