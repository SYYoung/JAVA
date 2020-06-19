
/**
 * Write a description of TesterCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;

public class TesterCaesarCipher {
    public void testCaesarCipher() {
        int key = 3;
        CaesarCipher cc = new CaesarCipher(key);
        // encrypt a message
        FileResource fr = new FileResource();
        String origMsg = fr.asString();
        String encryptMsg = cc.encrypt(origMsg);
        String decryptMsg = cc.decrypt(encryptMsg);
        System.out.println("Original message : \n" +origMsg);
        System.out.println("The encrypted message : \n" +encryptMsg);
        System.out.println("The decrypted message : \n" + decryptMsg);
    }
    
    public void testCaesarCracker() {
        CaesarCracker cc = new CaesarCracker('a');
        FileResource fr = new FileResource();
        String encryptMsg = fr.asString();
        String decryptMsg = cc.decrypt(encryptMsg);
        System.out.println("The encrypted message : \n" + encryptMsg);
        System.out.println("The decrypted message : \n" + decryptMsg);
    }
    
    public void testVCipher() {
        int[] keys = {17, 14, 12, 4};
        VigenereCipher vc = new VigenereCipher(keys);
        FileResource fr = new FileResource();
        String origMsg = fr.asString();
        String encryptMsg = vc.encrypt(origMsg);
        String decryptMsg = vc.decrypt(encryptMsg);
        System.out.println("orig message : \n" +origMsg);
        System.out.println("Encrypted message : \n" +encryptMsg);
        System.out.println("Decrypted message : \n" +decryptMsg);
    }
    
    public void testVBreaker(int keyLen) {
        /*
        int[] keys = new int[keyLen];
        char most_common_char = 'e';
        VigenereBreaker vb = new VigenereBreaker();
        FileResource fr = new FileResource();
        String encryptMsg = fr.asString();
        keys = vb.tryKeyLength(encryptMsg, keyLen, most_common_char);
        System.out.println("The keys are : ");
        for (int k=0; k<keyLen; k++)
            System.out.print(keys[k] +" , ");
        */    
        // test: breakVigenere
        VigenereBreaker vb = new VigenereBreaker();
        vb.breakVigenere();
    }
}
