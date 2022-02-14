package logiclab2;
import java.util.Scanner;
/**
 *
 * @author rob
 */
public class LogicLab2 {
    // global variables used for the if statment Fibonacci sequence 
    static int num1 = 0;
    static int num2 = 1;
    static int num3;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        
            System.out.println("Press 1 for user entered Fibonacci sequence (if statement): ");
            System.out.println("Press 2 for Automatic Fibonacci sequence to ten places (switch statement): ");
            System.out.println("Press 3 for adding fractions: ");
            System.out.println("Press 4 for adding fractions (Loop & Array): ");
            System.out.println("Press 0 to Exit");
            
            boolean quit = false;
            int menuChoice;
            
                do{
                    System.out.print("Choose a Proposition: ");
                    menuChoice = s.nextInt();
                    
                    switch(menuChoice){
                        case 1:
                            System.out.println("Fibonacci Series, enter any whole postive integer: ");
                            System.out.println("(Eg: Entering 20 will out put the first 20 Fibonacci numbers)");
                            int count = s.nextInt();

                            System.out.print(num1 + " " + num2); // num1 and num2 initialised values are printed
                            Fibonacci(count - 2); // 
                            System.out.println();
                        break;
                        
                        case 2:
                            System.out.println("Fibonacci Series, predetrmined number : ");
                                for (int i = 0; i < 10; i++){ //
                                    System.out.println(Fibonacci2(i) + " ");
                                }
                        break;
                        
                        case 3: // case takes user input and uses the addFractions method to add them
                            System.out.println("Add two Fractions");
                            
                            System.out.println();
                            System.out.println("Enter first Denominator Value: ");
                            int numerator1 = s.nextInt(); 
                            
                            System.out.println();
                            System.out.println("Enter first Numerator Value: ");
                            int denominator1 = s.nextInt();
                            
                            System.out.println();
                            System.out.println("Enter second Denominator Value: ");
                            int numerator2 = s.nextInt();
                            
                            System.out.println();
                            System.out.println("Enter first Numerator Value: ");
                            int denominator2 = s.nextInt();
                            
                            System.out.println();
                            System.out.println("The total of the fractions is: ");
                            addFractions(numerator1, denominator1, numerator2, denominator2); 
                            
                        break;
                        
                        case 4: // case adds fractions using an Array and loop to cut back on code redundancy seen in case 3
                            System.out.println("Add two Fractions");
                            int i;
                            int[] fractionArray = new int[4];
                            System.out.println();
                            System.out.println("Enter fraction values(x/y x/y): ");
                            System.out.println("First Value is the first Denominator: ");
                            System.out.println("Second Value is the first Numerator: ");
                            System.out.println("Third Value is the Second Denominator: ");
                            System.out.println("Fourth Value is the Second Denominator: ");
                                for (i = 0; i < 4; i++){ // loop runs through four elements
                                    fractionArray[i] = s.nextInt(); // user inputs a value at each element
                                    System.out.println("/");
                                }// the input is then sent through the method to give back a result
                                addFractions(fractionArray[0], fractionArray[1], fractionArray[2], fractionArray[3]);
                            
                        break;
                        case 0:
                            quit = true;
                        break;
                        
                        default:
                            System.out.println("Invalid Selection Please enter 1, 2, 3 or 4 for functions or enter 0 to quit: ");
                    }
                }   while (!quit);
                System.out.println("Farewell!");
                System.exit(0);    
    }
    // Recurison if statment method (utilises the HEAP for systems with limited memory)
    static void Fibonacci(int count){
        
            if (count > 0){ // method will stop when count reaches zero 
                num3 = num1 + num2; // the first and second numbers of the sequence are added
                num1 = num2; // num1 takes the num2 value
                num2 = num3; // num2 takes the num3 value
                System.out.print(" " + num3); // the new num3 value is printed with each recursion
                Fibonacci(count - 1); // the Fibonnaci method is calling itself by the user entered amount
            }
    }
    // Recursion switch statment method (utilises the STACK for systems with an abundance of memory)
    static int Fibonacci2(int num){
        
        switch (num){ 
            case 0:
                return 0; // this case returns the first value of the sequence, i.e fibonacci(0)
            case 1:
                return 1; // this case returns the second value of the sequence
            default:
                return (Fibonacci2(num - 1) + Fibonacci2(num - 2)); // this adds the two values making a third place in the
                                                                    // sequence, the recursive methods move to the last two
                                                                    // places in the seguence and adds them too
                                                                    // (0 + 1 = 1, so 1 + 1 = 2, so 1 + 2 = 3 etc)
                                                                    // This continues until the determined end is reached.
        }
    }
    // Adding Fractions Method
    static void addFractions(int numerator1, int denominator1, int numerator2, int denominator2){
        
        int sumOfFractions = numerator1 + numerator2; // fractions are summed up by adding both numerators
        int greatestCommonDiviser = denominator1;  // the gcd to one denominator is only needed if both are the same
        if (denominator1 != denominator2){ // sets a condition if the denominators aren't equal
            greatestCommonDiviser = denominator1 * denominator2; // to get the gcd both denominators are multiplied
            sumOfFractions = numerator1 * denominator2 + numerator2 * denominator1; // this equation works out the numerator value to match the gcd
        }
        System.out.println(numerator1 + "/" + denominator1 + " + " + numerator2 + "/" + denominator2 + " = " 
                + sumOfFractions + "/" + greatestCommonDiviser); // prints out the entered values as well as the sum of the fractions
    }
}
