/*****************************************************************
//
*****************************************************************/
//#ifndef BINT_H
//#define BINT_H
#include <iostream>

using namespace std;


int main()
{
   int x, y, gcd;

   cout << "Enter two integer values:" << endl;
   cin >> x >> y;

   for (int i = 1; i <= x && i <= y; i++)
   {
      if (x % i == 0 && y % i == 0)
         gcd = i;
   }

   cout << "\nGCD of " << x << " and " << y << " is: " << gcd << endl;

   return 0;
}