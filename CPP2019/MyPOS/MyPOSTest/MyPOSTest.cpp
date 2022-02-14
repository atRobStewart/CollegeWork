//#include "pch.h"
//#include "CppUnitTest.h"
//#include "..//MyPOS/MyPOSHeader.h"
//#include "..//MyPOS/Item.h"
//#include "..//MyPOS/AdultItem.h"
//#include "..//MyPOS/Sales.h"
//#include <functional>
//
//using namespace Microsoft::VisualStudio::CppUnitTestFramework;
//
//namespace MyPOSTest
//{
//	TEST_CLASS(MyPOSTest)
//	{
//	public:
//
//		TEST_METHOD(getCostTest)
//		{
//			AdultItem s("Denims", "Blue", itemSize::XL, 4, 59.99);
//			double actual = s.getCost();
//			Assert::AreEqual(59.99, actual);
//		}
//
//		TEST_METHOD(setCostTest)
//		{
//			AdultItem s("Denims", "Blue", itemSize::XL, 4, 59.99);
//			s.setCost(99.99);
//			Assert::AreEqual(99.99, s.getCost());
//		}
//		TEST_METHOD(exceptionSetCostTest)
//		{
//			AdultItem s("Denims", "Blue", itemSize::XL, 4, 59.99);
//			function<void(void)> f1 = [&s] {return s.setCost(-1); };
//			Assert::ExpectException<domain_error>(f1);
//		}
//
//		TEST_METHOD(addStockTest) {

//			AdultItem s("Test", "Test", itemSize::NA, 1, 0.01);
//		}
//
//	};
//}
