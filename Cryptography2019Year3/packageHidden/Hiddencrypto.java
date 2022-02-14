package packageHidden;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;

/***********************************************
* This class contains cryptographic functions
* that utilise BigInteger. This was done as a
* college based exercise, it is not recommended
* that these functions are ever used over their
* BigInteger class library counterparts.
* The primary reason for doing this was to gain
* detailed understanding on how these functions
* work, and how challenging it is to bring the
* mathematics involved from paper to coded
* implementation. 
***********************************************/

public class Hiddencrypto{
    /*******************************************
    * These global variables are here to make
    * the code easier to read, cutting down 
    * the amount of times BigInteger was typed.
    *******************************************/
    final static BigInteger ZERO = BigInteger.ZERO;
    final static BigInteger ONE = BigInteger.ONE;
    
    /*******************************************
    * Function takes in two BigIntegers and
    * and calculates the mod value.
    *******************************************/
    public static BigInteger modulo(BigInteger a, BigInteger b){
        
        BigInteger quotent = new BigInteger("0");
        BigInteger rem = new BigInteger("0");
        
        // calculate quotent by getting a / b
        // get remainder by calculating a - (quotent * b)
        quotent = a.divide(b);
        rem = a.subtract(quotent.multiply(b));
        
        return rem;
    }
    
    /*******************************************
    * Function for checking the Greatest 
    * Common Divisor of two integers.
    * Two BigInteger values are passed in
    * to achieve this.
    *******************************************/
    public static BigInteger gcd(BigInteger a, BigInteger b){
        
        // checks if a is less than b,
        // if true a and b are swapped around
        if (a.compareTo(b) < 0){
            BigInteger temp = a;
            a = b;
            b = temp;
        }
        
        BigInteger rem = ZERO;
        
        // loop continously iterates until the remainder = 0
        // when this happens 'a' will contain the gcd of the
        // two passed in integers.
        while (!b.equals(ZERO)) {
            if (a.compareTo(b) == 1 || a.compareTo(b) == -1){
                
                rem = modulo(a, b);
                a = b;
                b = rem;
            }
            if (rem.equals(ZERO)){
                return a;
            }
        }        
        return a;
    }
    
    /*******************************************
    * This Extended Euclidean Algorithm Function
    * takes in two arguments and calculates the 
    * gcd. Along with this it also calculates
    * integers x and y satisfying the equation
    * ax + by = d (d is the gcd)
    *******************************************/
    public static BigInteger[] extendedEA(BigInteger a, BigInteger b){
        
        BigInteger x = ZERO;
        BigInteger y = ZERO;
        BigInteger d = ZERO;
        
        // if b = 0 then d gets assigned the value in a
        // x gets assigned 1 and y remains as 0,
        // they are then returned in an array
        if (b.equals(ZERO)){
            d = a;
            x = ONE;
            BigInteger[] bZeroArr = {x, y, d}; 
            
            return bZeroArr;  
        }else{
        
        // d gets assigned the gcd using the gcd function
        d = gcd(a, b);
        
        BigInteger x1 = ZERO;
        BigInteger x2 = ONE;
        BigInteger y1 = ONE;
        BigInteger y2 = ZERO;
        BigInteger rem = ZERO;
        BigInteger quotent = ZERO; 
        
        // Loop will keep repeating until b is no longer
        // greater than zero.
        while (b.compareTo(ZERO) > 0){ 

            quotent = a.divide(b);
            rem = a.subtract(quotent.multiply(b));
            x = x2.subtract(quotent.multiply(x1));
            y = y2.subtract(quotent.multiply(y1)); 
            
            a = b;
            b = rem;
            x2 = x1;
            x1 = x;
            y2 = y1;
            y1 = y;    
        }
        // Once b is less than zero what the values in a, x2,
        // and y2 are, get assigned to d, x, and y respectively.
            d = a;
            x = x2;
            y = y2;
        
            BigInteger[] arr = {x, y, d};
        
        return arr;
        }
    }
    
    /*******************************************
    * The Modular Inverse is computed using
    * the Extended Euclidean Algorithm.
    *******************************************/
    public static BigInteger modInv(BigInteger a, BigInteger n){
        
        // In the eea function x is always at index 0, 
        // y at index 1 and the gcd at index 2 of the array
        BigInteger[] arr = extendedEA(a, n);
        BigInteger x = arr[0];
        BigInteger y = arr[1];
        BigInteger d = arr[2];
        
        // Checks if the gcd is greater than 1, the modular
        // inverse does not exist if the gcd > 1.
        if (d.compareTo(ONE) > 0){
            System.out.println("Inverse doesn't exist");
            return ZERO;
        }
        
        // Checks if the x value is negative, if it is it adds
        // the mod value thats passed in to it.
        if (x.compareTo(ZERO) < 0){
            return x.add(n);
        }
        return x;
    }
    
    /*******************************************
    * Modular exponentiation is performed here
    * using the repeated square-and-multiply 
    * algorithm.
    *******************************************/
    public static BigInteger modExp(BigInteger a, BigInteger exp, BigInteger n){
        
        BigInteger b = ONE;
        BigInteger A = ZERO;
        
        // the exponent value is set to a string in number 
        // base 2 format (binary), representing the 
        // exponent in binary reduces the number of
        // operations required.
        String binary = exp.toString(2);
        
        // if the exponent is set to zero b which has been
        // assigned the value one is returned, this is 
        // because any integer to the power of zero is one. 
        if (exp.equals(ZERO)){
            return b;
        }
        
        A = a;
        // if the binary ends with one, b gets updated with
        // the value in a, the end value will be the starting
        // value in this.
        if (binary.endsWith("1")){
            b = a;
        }
        // Every binary index value is checked starting from 
        // right to left, what we would consider going from 
        // the last index to the first.
        for (int i = binary.length() - 2; i >= 0; i--){
            
            // big A gets assigned the result of A^2 mod n
            A = modulo(A.multiply(A),n);
            
            // If a binary character is one then b gets assigned
            // big A^b mod n.
            if (binary.charAt(i) == '1'){
                b = modulo(A.multiply(b),n);
            }
        }
        // once all indexes in the binary are checked b is 
        // returned containing the modular exponentiation value
        return b;
    }
    
    /*******************************************
    * Probable Prime function checks the 
    * probability on a number being prime.
    * the modExp function is used within to
    * reduce the number of operations required
    * particulary regarding large primes and
    * number of witnesses that are desired.
    *******************************************/
    public static String probPrime(BigInteger n, BigInteger t){
        
        final BigInteger TWO = new BigInteger("2");
        final BigInteger THREE = new BigInteger("3");
        BigInteger r = ZERO;
        BigInteger s = ZERO;
        String isPrime = "Prime";
        String composite = "Composite";
        String invalidInput = "Invalid Input, must greater than or equal to 3 and not even.";
        
        // Validates the input of n and t to ensure n is odd 
        // and is greater than or equal to three. additionally
        // t is checked to ensure it's greater than or equal 
        // to one to ensure at least on check on n is performed.
        if (modulo(n, TWO).equals(ZERO) || (n.compareTo(THREE) <= -1) || t.compareTo(ONE) <= -1){
            return invalidInput;
        }
        // This will find a value for r such that r is odd.
        // n - 1 is assigned 2^s * r it is broken up and r
        // is divided down until it is odd, s is also 
        // simultaneously incremented with it.
        r = n.subtract(ONE);
        while (modulo(r, TWO).equals(ZERO)){
            s = s.add(ONE);
            r = r.divide(TWO);
            //System.out.println("r = " + r);
        }
        // This will repeat an amount of times dictated by the
        // passed in t argument.
        for (BigInteger i = ONE; i.compareTo(t) <= 0; i = i.add(ONE)){
            
            BigInteger a;
            Random rng = new Random();

            // Randomly picks values for a until it gets the
            // amount to satisfy the passed in t value and 
            // to satisfy the do while condition.
                do {
                    a = new BigInteger(n.bitLength(), rng);
                    //System.out.println("a = " + a);
                }while (a.compareTo(TWO) < 0 || a.compareTo(n.subtract(TWO)) > 0);
                        
            BigInteger y = ZERO;
            y = modExp(a, r, n);
            //System.out.println("y = " + y);
            // Each of the calculated y values must satisfy
            // the if condition before the inner while and 
            // nested if will execute.
            if (!y.equals(ONE) && !y.equals(n.subtract(ONE))){
                BigInteger j = ONE;
                
                // while j <= s -1 and y != n - 1 then 
                // y = y^2 mod n is computed using the
                // modExp function. j is also incremented
                // by one. this is continued until one of 
                // while conditions is not satisfied.
                while (j.compareTo(s.subtract(ONE)) < 1 && !y.equals(n.subtract(ONE))){
                        
                    y = modExp(y, TWO, n);
                    //System.out.println("sec y = " + y);
                    j = j.add(ONE);
                }
                // if the resulting y from the while loop is
                // not equal to n - 1 then the passed in n value
                // is composite. otherwise isPrime is returned.
                if (!y.equals(n.subtract(ONE))){
                    return composite;
                }
            }          
        }     
    return isPrime;
    }
    
    public static BigInteger RSA(BigInteger M){
        
        BigInteger e = new BigInteger("104477");
        BigInteger n = new BigInteger("1037");
        BigInteger d = new BigInteger("700");
        
        BigInteger Encrypt = new BigInteger("0");
        BigInteger Decrypt = new BigInteger("0");
        
        Encrypt = modExp(M, e, n);
        System.out.println("Encrypt in function = " + Encrypt);
        Decrypt = modExp(Encrypt, d, n);
        System.out.println("Decrypt in function = " + Decrypt);
        
        return Decrypt;
    }
}