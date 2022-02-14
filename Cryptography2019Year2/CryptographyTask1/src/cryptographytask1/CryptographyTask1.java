package cryptographytask1;
/**
 *
 * @author Rob
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CryptographyTask1{

    // only works with ascii files, when £ or € are used, the file becomes unreadible
    // other than this the modulo operations will be carried out on lines containing two numbers and ignore most other characters
    // the catch errors are not needed with most other characters considered
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
                String[] data = line.split("\\s+");// ignores multiple whitespaces and will perform modulo so long as there are two numbers
                Pattern p = Pattern.compile("[a-zA-Z,.!?@#^&%£€_'~¬`\\=|+*/()<>{}]"); // uses regular expressions to ignore letters and special characters
                Matcher m = p.matcher(line); // finds illegal characters on the lines
                    if (line.trim().length() == 0)continue; // skips empty lines
                    if (m.find())continue; // skips lines that have illegal characters entered
                    if (data.length != 2)continue; // skips lines that do not have two values entered
                    if (data.length == 2){
                        try{
                            long a = Long.parseLong(data[0]); // changed int to long to deal with larger numbers
                            long b = Long.parseLong(data[1]);
                            System.out.println(getModulo(a, b)); //prints method result along with the numbers that modulo was performed on

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
    public static long getModulo(long a, long b){ 
        long remainder = a - ((a / b)* b); // finds the remainder of the two given numbers by performing the mathematical operation
        //remainder = Math.abs(remainder1);
        remainder = (remainder < 0 ? -remainder : remainder);// gets the absolute value of a number changing negative numbers to positive
        System.out.print(a + " mod " + b + " = ");
        return remainder;
    }
//    public static boolean skipText(String[] data){
//        for (int i = 0; i < data.length; i++){
//            String check = data[i];
//            for (int j = 0; j < check.length(); j++){
//                if (check.charAt(i) == 'a') return true;
//            }
//        }
//        return false;
//   }
}
