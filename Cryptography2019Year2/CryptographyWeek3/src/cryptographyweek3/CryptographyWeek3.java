package cryptographyweek3;

import java.util.Scanner;
/**
 *
 * @author Rob
 */
public class CryptographyWeek3 {

    public static void main(String[] args) {
       // Version (i) using the % modulo java function
       Scanner s = new Scanner(System.in);
       System.out.println("Enter First number");
       int num1 = s.nextInt();
       
       System.out.println("Enter Second number");
       int num2 = s.nextInt();
        
       int remainder = num1 % num2;
        System.out.println(num1 + " mod " + num2 + " = " + remainder);
        
        // Version (ii) first number is divided by the second then the result is multiplied by the second
       System.out.println("Enter First number"); // and that result is subtracted from the first number
       int num3 = s.nextInt();
       
       System.out.println("Enter Second number");
       int num4 = s.nextInt();
       int remainder1 = num3 - ((num3 / num4)* num4);
        System.out.println(num3 + " mod " + num4 + " = " + remainder1);
        
        //version (iii) 
        System.out.println("Enter First Number");
        int num5 = s.nextInt();
        
        System.out.println("Enter Second Number");
        int num6 = s.nextInt();
        
        int intermediate = 0; // adds up the num6 variable into itself until it is larger than the num5 variable
            while(intermediate < num5){
                intermediate += num6; // value is then stored in the intermediate variable
            }
            intermediate -= num6; // decrements the intermediate variable by num6 to make it smaller than num5
        int remainder3 = num5 - intermediate; // remainder is num5 subtract the intermediate 
        System.out.println(num5 + " mod " + num6 + " = " + remainder3);
        
    }
    
    /**
    public static int mod(int num1, int num2){
        int remainder = num1 % num2;
            if(remainder < 0){
                remainder += num2;
            }
            return remainder;
    } **/
    
}
