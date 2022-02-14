package logiclab1;

import java.util.Scanner;
/**
 *
 * @author rob
 */
public class LogicLab1 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        
            System.out.println("Press 1 for Prime Number proposition: ");
            System.out.println("Press 2 for Divisible by Three proposition: ");
            System.out.println("Press 3 for IMPROVED Prime Number proposition: ");
            System.out.println("Press 4 for ALTERNATE Divisible by Three proposition: ");
            System.out.println("Press 0 to Exit");
            
            boolean quit = false;
            int menuChoice;
            
                do{
                    System.out.print("Choose a Proposition: ");
                    menuChoice = s.nextInt();
                    
                    switch(menuChoice){
                        case 1:
                            System.out.println("You have selected Proposition 1: ");
                            System.out.print("For every nonnegative integer, n, the value of n2 + n + 41 is prime.\n" +
                                             "Is this statement true?");
                            System.out.println();
                            int m; // will store the user entered value
                            System.out.println("Please Enter any positive whole number: ");
                            m = s.nextInt(); // takes the keyboard input for integers
                            int x = m * m + m + 41; // stores the answer to the equation in a single variable
        
                                if(isPrime(x)){ // the method takes the value and checks it
                                    System.out.println(x + " = Prime"); // prints out if the return is true
                                }else{
                                    System.out.println(x + " = Not Prime"); // prints out if the return is false
                            }
                        break;
                        
                        case 2:
                            System.out.println("You have selected Proposition 2: ");
                            System.out.print("For every integer n, if the sum of all the digits is divisible by 3, then it follows\n" +
                                             "that the number, n is divisible by 3.\n" +
                                             "Is this statement true?");
                            System.out.println();
                            System.out.println("Please Enter any positive whole number: ");
                            int n = s.nextInt(); // stores the user entered value
                                if (ifDivisiblebyThree(n)){ // the stored value is run through the method
                                    System.out.println("True the sum of each element of the number entered is divisible by 3, "
                                                       + "therefore the entered number is divisible by 3"); // prints if true
                                }else{
                                System.out.println("False the sum of each element of the number entered is not divisible by 3, "
                                                   + "therefore the entered number is not divisible by 3"); // prints if false
                            }
                        break;
                        
                        case 3:
                            System.out.println("This proposition runs automatically, no user input required");
                            for (int i = 0; i <= 100; i++){ // puts all values between 0 and 100 into the equation and checks them in the method
                                 int y = i * i + i + 41;
                                 if (!isPrime(y)){ // only runs if a value that is not prime is returned
                                     System.out.println(i + " is the first value where the equations result " + y + " is not a prime number");
                                     break;
                                 }
                            }
                        
                        break;
                        
                        case 4:
                            System.out.println("Will automatically check all values from 1 to 1000");
                            System.out.println("Type 'go' to start:");
                            String w;
                            w = s.next();
                            for (int i = 1; i <= 1000; i++){
                                System.out.println(i + "  " + ifDivisiblebyThree(i));
                            }
                            
                        break;
                        case 0:
                            quit = true;
                        break;
                        
                        default:
                            System.out.println("Invalid Selection Please enter 1, 2, 3 or 4 for propostions or enter 0 to quit: ");
                        }
                }   while (!quit);
                System.out.println("Farewell!");
                System.exit(0);
        
    }
    // Proposition 1 method, only works for numbers bigger than 9 the smallest number produced is 43
    public static boolean isPrime(int n){ //checks if the entered value sumed up in the proposed equation is a prime number
        if (n <= 1){ // error checks the value if its less than or equal to 1
            return false; // smallest prime is 2
        }
        for (int i = 2; i < n; i++){ // the sum of the equation is checked if its divisible by any value between 2 and the number before, all numbers are divisibe against themselves
            if (n % i == 0){ // if the value n is divisible by any value in i equal to zero, then it cannot be prime
                return false; // primes aren't divisible by anything other than 1 or itself
            }
        }
        return true; // if the value is divisible by itself and 1 only, then its prime and returns true
    }
    // Proposition 2 method
    public static boolean ifDivisiblebyThree(int n){ // checks if any entered values elements summed up is divisible by 3
        String str = Integer.toString(n); // takes the int parameter and converts to string
        int num = str.length(); // number is converted to a string in the parameter and num stores the length value so 1854 would be 4
        int sum = 0; // initalised variable sum
        for (int i = 0; i < num; i++){ // using askii the loop recognises each numbers askii table position
            sum += (str.charAt(i) - '0'); // the respective position number is subtracted by zeros position number and added to the sum variable as an int
            System.out.println(sum);
        }
        return (sum % 3 == 0 && n % 3 == 0); // the intial number and the result is then divided by 3 and returned
    }
}

/** 
 Proposition 1
 Proof by Contradiction:
 For every nonnegative integer, n, the value of n^2 + n + 41 is prime.
 
  Statement:                                                           Reason:     
 1.  Assume n^2 + n + 41 is prime                                      Assumption
 2.  n = N (N = set of positive whole numbers)                         Definition 
 3.  n = 1                                                             Algebra
 4.  1^2 + 1 + 41 = prime                                              Assumption
 5.  1^2 + 1 + 41 = 43                                                 Algebra
 6.  43 / 43                                                           Proof
 7.  43 / N < 43                                                       Algebra
 8.  43 / 1                                                            Proof
 9.  43 !/ 2-42                                                        Proof
 10. 43 is prime                                                       Proof 
 11. n = {2,3,4,5,6,7......}                                           Algebra
 12. n = {2,3,4,5,6,7......39} = prime                                 Proof
 13. n = 40                                                            Algebra
 14. 40^2 + 40 + 41 = prime                                            Assumption
 15. 40^2 + 40 + 41 = 1681                                             Algebra
 16. 1681 / 1681                                                       Proof
 17. 1681 / 1                                                          Proof
 18. 1681 / 41                                                         Proof
 19. Contradiction 1681 is not prime
  
  This proposition is a contradiction and is therefore False, because there is a condition where the sum of the equation 
  does not produce a prime number, specifically the equation 40^2 + 40 + 41 = 1681
 
 
 Proposition 2
 Direct Proof:
 For every integer n, if the sum of all the digits is divisible by 3, then it follows
 that the number, n is divisible by 3.
 
 
  Statement:                                                          Reason:     
 1.  Assume n / 3 = Z                                                Assumption
 2.  n = Z (Z = whole integer)                                       Definition
 3.  336 / 3 = Z                                                     Algebra
 4.  336 / 3 = 112                                                   Proof
 5.  3 + 3 + 6 = Z                                                   Algebra
 6.  3 + 3 + 6 = 12                                                  Algebra 
 7.  12 / 3 = Z                                                      Algebra
 8.  12 / 3 = 4                                                      Proof
 9.  Direct proof True integer & summed elements are / 3
 10. 115 / 3 = Z                                                     Assumption
 11. 115 / 3 = 38.33333...                                           Proof
 12. 115 / 3 != Z                                                    Proof 
 13. 1 + 1 + 5 = Z                                                   Algebra
 14. 1 + 1 + 5 = 7                                                   Algebra
 15. 7 / 3 = Z                                                       Assumption
 16. 7 / 3 = 2.33333...                                              Proof
 17. Direct proof true if the integer is not / 3 then the integers
     elements summed is not / 3
 
  
  This proposition is True, if the sum of any integers elements is divisible by 3 then the integer itself is divisible 
  by 3 no matter what the size is, every third integer in a list of numbers going from one to infinity is indefinitely
  divisible by 3, as a whole and with each element summed up, all others are not.
  
  
  
 
 **/