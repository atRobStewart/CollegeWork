package cryptographytask2version2;

import java.io.File;
import java.io.FileNotFoundException;
//import java.math.BigInteger;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *
 * @author Rob
 */
public class CryptographyTask2Version2 {
        // only works with ascii files, when £ or € are used, the file becomes unreadible
     public static void main(String[] args){
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Performs modulo on numbers in a text file, each line may contain two numbers only, "
                            + "\nanything else typed on any line will cause sed line to be ignored! "
                            + "\n\nPlease enter file name below...");
        String filename = keyboard.nextLine();

        try{
            Scanner myFile = new Scanner(new File(filename));
            int lineCount = 0;
            while (myFile.hasNextLine()){
                lineCount++;
                String line = myFile.nextLine();
                String[] data = line.split("\\s+");// ignores multiple whitespaces and will perform exponentiation and modulo so long as there are three numbers
                Pattern p = Pattern.compile("[a-zA-Z,.!?@#^&%£€_'~¬`\\=|+*/()<>{}]"); // uses regular expressions to ignore letters and special characters
                Matcher m = p.matcher(line); // finds illegal characters on the lines
                    if (line.trim().length() == 0)continue; // skips empty lines
                    if (m.find())continue; // skips lines that have illegal characters entered
                    if (data.length != 3)continue; // skips lines that do not have three values entered
                    if (data[2].equalsIgnoreCase("0"))continue; // skips lines where the mod value is zero
                    if (data.length == 3){
                        try{
                            int a = Integer.parseInt(data[0]);
                            int b = Integer.parseInt(data[1]);
                            int c = Integer.parseInt(data[2]);
                            System.out.println("The " + a + "^" + b + " mod " + c + " is " + getModPower(a, b, c));
                            
                        }catch(NumberFormatException e){
                            System.out.println("An exception occurred while parsing numbers from line " + lineCount + " : " + line);
                        }
                    }else{
                        System.out.println("Line " + lineCount + " was not formatted properly: \"" + line + "\"");
                    }
                }
            }catch(FileNotFoundException fe){
                System.out.println("That file was not found in the system, sorry.");
            }
        }
    public static int getModulo(int a, int b){ 
        int remainder = a - ((a / b)* b); // finds the remainder of the two given numbers by performing the mathematical operation
        remainder = (remainder < 0 ? -remainder : remainder);// gets the absolute value of a number changing negative numbers to positive
        return remainder;
    }
    
    public static int getModPower(int base, int exp, int mod){
      if (exp == 0){
          return 1; // Any integer to the power of zero will always return 1
      }
      if (exp == 1){
          return base; // Any integer to the power of one will always return itself
      }
      int result = getModPower(base, exp / 2, mod); //method recursively calls itself and divides b by two until it gets 1 or 0
      if (getModulo(exp,  2) == 0){ // statement to satisfiy the condition where b divided by two equals zero
          return getModulo(result * result, mod); // if divides by two equals zero is satisfied, the result is multiplied to itself, it and the mod value are run through the get Modulo Method and returned
      }else{ // else is used as the only other achievable condition is b equals 1
          return getModulo((getModulo(result * result, mod) * base), mod); // the previous condition is first executed, it is then multiplied by the base value, that result along with the mod value is then run through the getModulo method and returned
      }
    }
}
