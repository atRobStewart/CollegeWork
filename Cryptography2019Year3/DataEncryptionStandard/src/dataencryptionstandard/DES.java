//Reference https://www.journaldev.com/1309/java-des-algorithm-program
//Reference https://docs.oracle.com/en/java/javase/13/docs/api/java.base/javax/crypto/Cipher.html 
//Reference https://docs.oracle.com/javase/7/docs/api/javax/crypto/Cipher.html 

package dataencryptionstandard;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
/**
 *
 * @author Rob
 */
public class DES {
    
    private static Cipher encrypt;
    private static Cipher decrypt;
    private static final byte[] IV = {11, 22, 33, 44, 99, 88, 77, 66};
    
    public static void main(String[] args) {
        String clearTextFile = "D:\\_college\\_DkIT_Level_8\\_Year 3\\Semester 5\\crypto\\Kerberos\\ClearText.txt";
        String cipherTextFile = "D:\\_college\\_DkIT_Level_8\\_Year 3\\Semester 5\\crypto\\Kerberos\\CipherText.txt";
        String clearTextNewFile = "D:\\_college\\_DkIT_Level_8\\_Year 3\\Semester 5\\crypto\\Kerberos\\NewClearText.txt";
        
        try{
            SecretKey key = KeyGenerator.getInstance("DES").generateKey();
            AlgorithmParameterSpec paramSpec = new IvParameterSpec(IV);
            
            encrypt = Cipher.getInstance("DES/CBC/PKCS5Padding");
            encrypt.init(Cipher.ENCRYPT_MODE, key, paramSpec);
            
            decrypt = Cipher.getInstance("DES/CBC/PKCS5Padding");
            decrypt.init(Cipher.DECRYPT_MODE, key, paramSpec);
            
            encrypt(new FileInputStream(clearTextFile), new FileOutputStream(cipherTextFile));
            
            decrypt(new FileInputStream(cipherTextFile), new FileOutputStream(clearTextNewFile));
            System.out.println("Done");
                
        }catch(NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | IOException e){
            e.printStackTrace();
        }
    }
    
    private static void encrypt(InputStream is, OutputStream os) throws IOException{
        
        os = new CipherOutputStream(os, encrypt);
        writeData(is, os);
    }
    
    private static void decrypt(InputStream is, OutputStream os) throws IOException{
        
        is = new CipherInputStream(is, decrypt);
        writeData(is, os);
    }
    
    private static void writeData(InputStream is, OutputStream os) throws IOException{
        
        byte[] buf = new byte[1024];
        int numRead = 0;
        
        while ((numRead = is.read(buf)) >= 0){
            os.write(buf, 0, numRead);
        }
        os.close();
        is.close();
    }
}


