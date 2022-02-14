package logiclab4;
import java.util.Scanner;
/**
 *
 * @author Rob
 */
public class LogicLab4 {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        
        Scanner s = new Scanner(System.in); // instanciation of the scanner class
        // options for different converters
        System.out.println("Press 1 for Decimal to Binary conversion");
        System.out.println("Press 2 for Binary to Decimal conversion");
        System.out.println("Press 3 for Decimal to Hexadecimal conversion");
        System.out.println("Press 4 for Hexadecimal to Decimal conversion");
        System.out.println("Press 0 to Exit");
        
            boolean quit = false; // quit initalises to false 
            int menuChoice; // variable for use in the switch for the number choice regarding the converters
            
                do{
                    System.out.println("Choose a Base number converter: ");
                    menuChoice = s.nextInt(); // stores the user input in the variable
                    
                    switch (menuChoice){// will switch to the appropriate case number related to the user input 
                        case 1:
                            int decimalNumber; // variable for storing a whole number
                            String bin;        // variable for storing a string
                            System.out.println("Enter any Whole number");
                            decimalNumber = s.nextInt(); // takes user input for the whole number
                            bin = decimalToBinary(decimalNumber); // uses the method to convert the decimal number to a binary number and stores it in the string variable
                            System.out.println("Binary Number is: " + bin); 
                        break;
                        
                        case 2:
                            System.out.println("Enter Binary number: ");
                            String binaries = s.next(); // takes in a user entered binary number
                            int dec = binaryToDecimal(binaries); // uses the binary to decimal converter method and stores the result in the variable
                            System.out.println("Decimal Number is: " + dec);     
                        break;
                        
                        case 3:
                            System.out.println("Enter Decimal Number: ");
                            int decimalValue = s.nextInt(); // takes in a user entered decimal value
                            System.out.println("Hexadecimal number is: ");
                            System.out.println(decimalToHexadecimal(decimalValue));// uses the deciaml to hexadecimal method to convert the user entered number
                        break;
                        
                        case 4:
                            System.out.println("Enter Hexadecimal Number: ");
                            String hexdec = s.next(); // stores the user inputted hexadecimal value
                            hexdec = hexdec.toUpperCase(); // converts letters to uppercase
                            int decimal = HexadecimalToDecimal(hexdec); // uses the hexadecimal to decimal converter method to change the value and store in the variable
                            System.out.println("Decimal number is: " + decimal);
                        break;
                        
                        case 0:
                            quit = true; // menu choice entered as zero will turn quit to true, stopping the program
                        break;
                        
                        default:
                            System.out.println("Invalid number selection, choose a given option or 0 to quit");
                    }
                }while (!quit);
                        System.out.println("Goodbye");
                        System.exit(0);
        System.out.println();
    }
    /**
     * Takes in the user entered decimal integer and converts it to a String Binary number.
     * Modulo is used to find the remainder of 0 or 1 on the integer.
     * The integer is then divided by two and the process is repeated until 0 is reached.
     * The function uses recursion to call itself to achieve this. 
     * @param y
     * @return 
     */
    static String decimalToBinary(int y){ 
        int a;
        if (y > 0){
            a = y % 2;
            return (decimalToBinary(y / 2)+ "" + a);
        }
        return "";
    }
    /**
     * Takes in a String Binary number and returns an integer in decimal form.
     * Each binary value staring from the left is applied an exponent value
     * respective to its position, the number receiving the exponent is 2 
     * because its in base 2, where each value in the binary string is 1,
     * the resulting integers are added to each other, where the values are
     * 0 the resulting integer is always 0. Math.pow returns a double and is
     * as a result of this, is cast to an int.
     * @param binaryNumber
     * @return 
     */
    static int binaryToDecimal(String binaryNumber){
        int dec = 0;
        int length = binaryNumber.length();
            if (length > 0){
                String substring = binaryNumber.substring(1);
                int digit = Character.getNumericValue(binaryNumber.charAt(0));
                dec = digit * (int) Math.pow(2, length - 1) + binaryToDecimal(substring);
            }
        return dec;
    }
    /**
     * The decimal value entered has modulo applied to it, whatever the 
     * value is between and including 1 - 16, is then used to identify 
     * the Hex value in the char array for the indexes 0 - 15.
     * This is achieved using a while loop until zero is reached, the entered value
     * is divided by 16 with each iteration.
     * @param decimalValue
     * @return 
     */
    static String decimalToHexadecimal(int decimalValue){
        char[] hex = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        int rem;
        String hexadecimal = "";
            while (decimalValue != 0){
                   rem = decimalValue % 16;
                   hexadecimal = hex[rem] + hexadecimal;
                   decimalValue = decimalValue / 16;
            }
        return hexadecimal;
    }
    /**
     * The String hex1 stores all the possible hex notations, the function
     * identifies each hex character by its index in the string, each index
     * value is multiplied by 16 and the value stored in decimal, the index
     * value is then added to it. The very first decimal value will be what
     * is stored in the currentIndex variable. 
     * @param hexdec
     * @return 
     */
    static int HexadecimalToDecimal(String hexdec){
        int decimal = 0;
        String hex1 = "0123456789ABCDEF";
            for (int i = 0; i < hexdec.length(); i++){
                 char ch = hexdec.charAt(i);
                 int currentIndex = hex1.indexOf(ch);
                 decimal = 16 * decimal + currentIndex;
            }
        return decimal;
    }   
}