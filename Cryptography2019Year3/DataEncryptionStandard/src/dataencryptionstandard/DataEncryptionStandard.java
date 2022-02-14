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
public class DataEncryptionStandard {

    //The Cipher class provides the functionality of a cryptographic cipher for encryption and decryption
    private static Cipher encrypt;
    private static Cipher decrypt;
    // vector of values for the IvParameter Spec, these values are needed for DES to function
    private static final byte[] IV = {11, 22, 33, 44, 99, 88, 77, 66};
    
    public static void main(String[] args) {
        
        String clearTextFile = "D:\\_college\\_DkIT_Level_8\\_Year 3\\Semester 5\\crypto\\Kerberos\\ClearText.txt";
        String cipherTextFile = "D:\\_college\\_DkIT_Level_8\\_Year 3\\Semester 5\\crypto\\Kerberos\\CipherText.txt";
        String clearTextNewFile = "D:\\_college\\_DkIT_Level_8\\_Year 3\\Semester 5\\crypto\\Kerberos\\NewClearText.txt";
        
        try{
            // A secret key is generated using the KeyGenerator Class, this class provides the functionality of a
            // secret symmetric key generator. Key generators are constructed using one of the getInstance class
            // functions of the class, this supports AES, DES, DESede, HmacSHA1 and HmacSHA256
            SecretKey key = KeyGenerator.getInstance("DES").generateKey();
            // IvparameterSpec implements AlgorithmParameterSpec, this class specifies an initialisation vector
            // ciphers in feedback mode use initialisation vectors DES in Cipher Block Chaining Mode(CBC) mode does this.
            // These parameters are used to initialise the Cipher instances
            AlgorithmParameterSpec paramSpec = new IvParameterSpec(IV);
            
            // encrypt stores an instance of DES with CBC using the padding scheme described in RSA Laboratories
            // PKCS #5 password based encryption standard.
            encrypt = Cipher.getInstance("DES/CBC/PKCS5Padding");
            // encrypt is initalised with a constant ENCRYPT_MODE used to initalise the cipher to encryption mode
            // the secret key is passed in too along with the specified parameters
            encrypt.init(Cipher.ENCRYPT_MODE, key, paramSpec);
            
            // decrypt gets the same instances and passes in the same arguments as its encrypt counterpart
            // the only exeption is the constant DECRYPT_MODE for initalising decryption
            decrypt = Cipher.getInstance("DES/CBC/PKCS5Padding");
            decrypt.init(Cipher.DECRYPT_MODE, key, paramSpec);
            
            // Function encrypts data written in the clear text file and writes it to the cipher text file in encrypted forme
            encrypt(new FileInputStream(clearTextFile), new FileOutputStream(cipherTextFile));
            // Function decrypts data written in the cipher text file and writes it to the new clear text file to prove it does 
            // successfully decrypt the cipher text.
            decrypt(new FileInputStream(cipherTextFile), new FileOutputStream(clearTextNewFile));
            // A simple message to confirm to the user that the DES has run sucessfully
            System.out.println("Done");
                
        }catch(NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | IOException e){
            e.printStackTrace();
        }
    }
    
    // This function encrypts the data and writes it to a file, the CipherOutputStream
    // will attempt to encrypt data before writing out the encrypted data
    private static void encrypt(InputStream is, OutputStream os) throws IOException{
        
        os = new CipherOutputStream(os, encrypt);
        writeData(is, os);
    }
    
    // This function decrypts the data and writes it to a file, the CipherIntputStream
    // will attempt to read in data and decrypt it, before returning the decrypted data.
    private static void decrypt(InputStream is, OutputStream os) throws IOException{
        
        is = new CipherInputStream(is, decrypt);
        writeData(is, os);
    }
    
    // writes data from an input source to an output source
    // input source: CipherInputStream (from file) which has encrypted or decrypted data
    // output source: file
    private static void writeData(InputStream is, OutputStream os) throws IOException{
        
        // buffer to hold up to 1024 bytes of data (encrypted or decrypted)
        byte[] buf = new byte[1024];
        // size of chunk that was read
        int numRead = 0;
        
        // read bytes from inputstream (ie clearText or cipherText) as long as they exist
        // numRead is number of bytes read
        while ((numRead = is.read(buf)) >= 0){
            // write bytes to outputstream (ie cipherText or clearTextNew) 
            os.write(buf, 0, numRead);
        }
        // close files
        os.close();
        is.close();
    }
    
}
