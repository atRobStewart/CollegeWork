package cryptographytask3;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Rob
 */
public class CryptographyTask3 {

    // Achieves RSA Encryption using the Big Integer
    public static void main(String[] args){
        // 4096 was chosen as the bit length because it is the current standard of RSA Encryption, the lower bit lengths can be hacked quickly with many current systems
        int bitLength = 4096;
        // SecureRandom() provides a cryptographically strong random number generator
        Random rng = new SecureRandom();
        // probablePrime() returns a positive big integer that is probably prime, the chances of the number being composite are 2^-100, this is used in current systems
        BigInteger p = BigInteger.probablePrime(bitLength / 2, rng);
        BigInteger q = BigInteger.probablePrime(bitLength / 2, rng);
        
        // once p and q are acquired randomly, they are then multiplied to give a big integer n value
        BigInteger n = p.multiply(q);
        
        // phiOfN is determined by subtracting one from p and q, and then multiplying the results together
        BigInteger phiOfN = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        
        // printing out each value to show the size of each big integer, redundant practice in a real time application
        System.out.println("p: "+ p);
        System.out.println("q: "+ q);
        
        // fake big integer 32 bit prime values will replace the correct initial p and q values until grabage collection disposes them from memory
        p = new BigInteger("12234556546453454565675663454447");
        q = new BigInteger("42356876989775523434234456474561");
        
        // a big integer e must be selected that is coprime with phiOfN
        BigInteger e;
            do{
                // randomly picks a number for e with the same bit length as phiOfN
                e = new BigInteger(phiOfN.bitLength(), rng);
            }
            // will keep running if e is smaller or equal to one, is larger or equal to phiOfN or if the gcd of e and phiOfN is not equal to one
            while(e.compareTo(BigInteger.ONE) <= 0 || e.compareTo(phiOfN) >= 0 || !e.gcd(phiOfN).equals(BigInteger.ONE));
            BigInteger d = e.modInverse(phiOfN);
            
            // printing out each value to show the size of each big integer, redundant practice in a real time application
            System.out.println("n: "+ n);
            System.out.println("phiOfN: "+ phiOfN);
            System.out.println("e: "+ e);
            System.out.println("d: "+ d);
            
            // User enters data to be encrypted, all letters, numbers and majority of special characters will encrypt and decrypt as expected
            // Pound and Euro currency symbols won't decrypt because they are excluded from the ascii table
            Scanner s = new Scanner(System.in);
            System.out.println("\n Enter data to be encrypted: ");
            String text = s.nextLine().replaceAll("\r", " ");
            
            // prints out the user entered plain text
            System.out.println("\n" + text + "\n");
            
            // takes the ascii code for each number, letter and special character in the user entered text and places the values in a byte array
            byte[] bytes = text.getBytes();
            
            // prints out the values in the byte array, redundant in real world implementation
            System.out.println("Ascii values in the byte array:\n" + Arrays.toString(bytes) + "\n");
            
            // big integer msg takes the values from the byte array and stores them
            BigInteger msg = new BigInteger(bytes);
            // biginteger enc, encrypts msg changing it to a different value, msg^e is congruent to enc mod n
            BigInteger enc = msg.modPow(e, n);
            
            // prints out encypted message, redundant in real world implementation
            System.out.println("Encrypted message:\n" + enc + "\n");
            
            // big integer dec takes the enc and puts it to the power of its private key d which will be congruent to dec mod n, which returns the decrypted message
            BigInteger dec = enc.modPow(d, n);
            
            // prints out decrypted message, redundant in real world
            System.out.println("Decrypted message in Big Integer number format:\n" + dec + "\n");
            
            // the dec is then returned to a byte array which should have the same values as the inital byte array, holding the correct ascii values
            byte[] bytes2 = dec.toByteArray();
            
            // prints out values in the second byte array, redundant in real world
            System.out.println("Ascii values in the byte array:\n" +Arrays.toString(bytes2) + "\n");
            
            // String msg2 takes the byte array and changes the ascii values back to the original user entered plain text, typed in by user A
            String msg2 = new String(bytes2, StandardCharsets.UTF_8);
            
            // prints out the decrypted plain text message
            System.out.println(msg2);
            
    }

}
