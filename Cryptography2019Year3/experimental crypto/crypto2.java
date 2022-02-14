import java.math.*;
import java.util.Arrays;

public class crypto2{
    
    public static void main (String[] args){
        
        BigInteger a = new BigInteger("5");
        BigInteger b = new BigInteger("36");
        BigInteger n = new BigInteger("5");
        BigInteger exp = new BigInteger("5");
        
        if (a.compareTo(b) == - 1){
                System.out.println(gcd(b, a));
                System.out.println(Arrays.toString(eea(b, a)));
                System.out.println(modInv(n, a) + " mod " + n);
                System.out.println(modExp(n, exp, a));
            }else{
                System.out.println(gcd(a, b));
                System.out.println(Arrays.toString(eea(a, b)));
                System.out.println(modInv(a, n) + " mod " + n);
                System.out.println(modExp(a, exp, n));
            }
        
        // System.out.println(gcd(a, n));
        // System.out.println(Arrays.toString(eea(a, n)));
        // System.out.println(modInv(a, n) + " mod " + n);
        // System.out.println(modExp(a, exp, n));
    }
    
    public static BigInteger modulo(BigInteger a, BigInteger b){
        
        BigInteger quotent = new BigInteger("0");
        BigInteger rem = new BigInteger("0");
        
        quotent = a.divide(b);
        rem = a.subtract(quotent.multiply(b));
        
        return rem;
    }
    
    public static BigInteger gcd(BigInteger a, BigInteger b){
        
        BigInteger rem = new BigInteger("0");
        
        if (b.equals(BigInteger.ZERO)){
            return b;
        }
        
        do {
            if (a.compareTo(b) == 1 || a.compareTo(b) == -1){
                
                rem = modulo(a, b);
                a = b;
                b = rem;
            }
            if (rem.equals(BigInteger.ZERO)){
                return a;
            }
        }
        while (!a.equals(BigInteger.ZERO) || !b.equals(BigInteger.ZERO));
        
        return BigInteger.ZERO;
    }
    
    public static BigInteger[] eea(BigInteger a, BigInteger b){
        
        BigInteger x = new BigInteger("0");
        BigInteger y = new BigInteger("0");
        BigInteger d = new BigInteger("0");
        
        if (b.equals(BigInteger.ZERO)){
            d = a;
            x = BigInteger.ONE;
            BigInteger[] arr = {d, x, y}; 
            
            return arr;  
        }else{
        
        d = gcd(a, b);
        
        BigInteger x1 = new BigInteger("0");
        BigInteger x2 = new BigInteger("1");
        BigInteger y1 = new BigInteger("1");
        BigInteger y2 = new BigInteger("0");
        BigInteger rem = new BigInteger("0");
        BigInteger quotent = new BigInteger("0"); 
        
        while (b.compareTo(BigInteger.ZERO) > 0){ 

            quotent = a.divide(b);
            rem = a.subtract(quotent.multiply(b));
            //rem = modulo(a, b);
            x = x2.subtract(quotent.multiply(x1));
            y = y2.subtract(quotent.multiply(y1)); 
            
            a = b;
            b = rem;
            x2 = x1;
            x1 = x;
            y2 = y1;
            y1 = y;    
        }
            d = a;
            x = x2;
            y = y2;
        
            BigInteger[] empArr = {x, y, d};
        
        return empArr;
        }
    }
    
    public static BigInteger modInv(BigInteger a, BigInteger n){
        
        BigInteger x = new BigInteger("0");
        BigInteger y = new BigInteger("0");
        BigInteger d = new BigInteger("0");
        BigInteger dgcd = new BigInteger("0");
        
        x = eea(a, n)[0];
        y = eea(a, n)[1];

        d = a.multiply(x).add(n.multiply(y));
        dgcd = gcd(a, n);
        
        if (!d.equals(BigInteger.ONE) && (!dgcd.equals(BigInteger.ONE))){
            System.out.println("Inverse doesn't exist");
            return BigInteger.ZERO;
        }
        if (x.compareTo(BigInteger.ZERO) < 0){
            return x.add(n);
        }
        return x;
    }
    
    public static BigInteger modExp(BigInteger a, BigInteger k, BigInteger n){
        
        BigInteger b = new BigInteger("1");
        BigInteger A = new BigInteger("0");
        String s = k.toString(2);
           
        if (k.equals(BigInteger.ZERO)){
            return b;
        }
        
        A = a;
        
        if (s.endsWith("1")){
            b = a;
        }
        
        for (int i = s.length() - 2; i >= 0; i--){
            A = modulo(A.multiply(A),n);
            
            if (s.charAt(i) == '1'){
                b = modulo(A.multiply(b),n);
            }
        }
           
        return b;

        //return a.modPow(k, n);
    }
}