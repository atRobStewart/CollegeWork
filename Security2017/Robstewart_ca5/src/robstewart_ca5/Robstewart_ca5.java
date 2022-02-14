/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robstewart_ca5;
/**
 *
 * @author rob
 */
import java.io.IOException;
import java.util.Scanner;
import java.util.Random;

public class Robstewart_ca5 {

    /**
     * @param args the command line arguments
     */
    
    
      static final int pin = 2468;
      static int input = 0;
      static int attempt = 0;
      static int attemptNum = 0;
      static Scanner k = new Scanner(System.in);
      
      public static void main(String[] args) throws IOException {
        menu();
    }
      
      
      public static void menu() throws IOException{
          
          int select;
          System.out.println("Please enter either 1 or 2");
          select = k.nextInt();
          
          if (select == 1 || select ==2){
              
              switch (select){
                    case 1:
                        System.out.println("1. Pin unlimited guesses");
                        pin();
                    break;
                    case 2:
			System.out.println("2. Pin only three attempts");
			pinLimit();
          }
          
          
      }
      
  }   
      public static void pin(){
         
        while (input != 2468){
            attemptNum++;
            
            System.out.println("Please enter a 4 digit pin");
        input = k.nextInt();
            
            if (input == 2468){
            System.out.println("Congratulations you have guessed correctly");
        }else{
            System.out.println("Incorrect Please Try Again");
            } 
        }        
         System.out.println("Number of attempts before correct guess was: " + attemptNum);
         System.exit(0);
   }  
    
      public static void pinLimit(){
            
        System.out.println("Please enter a 4 digit pin  three attempts remain");
        input = k.nextInt();
        
        
        
        while (attempt < 3){
            
            if (input == 2468){
            System.out.println("Congratulations you have guessed correctly");
            System.exit(0);
        }
            
            System.out.println("Incorrect pin number");
            
            attempt++;
            if (attempt == 1){
                System.out.println("Two attempts remaining");
                input = k.nextInt();
            }else if (attempt == 2){
                System.out.println("Last attempt remaining");
                input = k.nextInt();
            }else if (attempt == 3){
                System.out.println("Failed");
                System.exit(0);   
            }
        } 
    }

}
