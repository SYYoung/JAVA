
/**
 * Write a description of CaesarBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class CaesarBreaker {
    public void countLetters(String str, int[] counts) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int k=0; k<str.length(); k++) {
            char ch = Character.toUpperCase(str.charAt(k));
            int pos = alphabet.indexOf(ch);
            if (pos != -1) {
                counts[pos]++;
            }
        }
    }
    
    public int indexOfMax(int[] values) {
        int index = -1, maxVal = 0;
        for (int k=0; k<values.length; k++) {
            if (values[k] > maxVal) {
                index = k;
                maxVal = values[k];
            }
        }
        return index;
    }    
    
    public String decrypt(String encrypted, int key) {
        CaesarCipher cc = new CaesarCipher();
        String message = cc.encrypt(encrypted, 26-key);
        return message;
    }
    
    public void testDecrypt() {
        // 1. get the original message
        FileResource fr = new FileResource();
        String origMsg = fr.asString();
        System.out.println("Original message: \n" + origMsg);
        // 2. Encrypt the message with single key
        int key = 15;
        CaesarCipher c1 = new CaesarCipher();
        String encryptMsg = c1.encrypt(origMsg, key);
        System.out.println("Encrypted message: \n" + encryptMsg);
        // 3. Decrypt the message
        String decryptMsg = decrypt(encryptMsg, key);
        System.out.println("Decrypted message: \n" + decryptMsg);
    }
}
