
/**
 * Write a description of CaesarBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class CaesarBreaker {
    final int CYCLE = 26;
    
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
    
    public int maxIndex(int[] values) {
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
    
    public String halfOfString(String message, int start) {
        StringBuilder sb = new StringBuilder();
        for (int k=start; k<message.length(); k=k+2) {
            sb = sb.append(message.charAt(k));
        }
        return sb.toString();
    }
    
    public int getKey(String s) {
        int[] counts = new int[30];
        int posE = 4; // index of 'e'
        int key;
        
        countLetters(s, counts);
        int index = maxIndex(counts);
        if (index >= posE) 
            key = index - posE;
        else
            key = CYCLE - (posE - index);
        return key;
    }
    
    public String combineTwoStr(String firstStr, String secondStr) {
        StringBuilder decryptMsg = new StringBuilder();
        for (int k=0; k<firstStr.length(); k++) {
            decryptMsg = decryptMsg.append(firstStr.charAt(k));
            if (k < secondStr.length())
                decryptMsg = decryptMsg.append(secondStr.charAt(k));
        }  
        return decryptMsg.toString();
    }
    
    public String decryptTwoKeys(String encrypted) {
        String firstStr = halfOfString(encrypted, 0);
        String secondStr = halfOfString(encrypted, 1);
        int firstKey = getKey(firstStr);
        int secondKey = getKey(secondStr);
        // for testing only
        //int firstKey = 2, secondKey = 20;
        String firstDecrypt = decrypt(firstStr, firstKey);
        String secondDecrypt = decrypt(secondStr, secondKey);
        // combine both messages
        String decryptMsg = combineTwoStr(firstDecrypt, secondDecrypt);
        
        System.out.println("the first half encryped message: " + firstStr);
        System.out.println("first guessed key = " + firstKey);
        System.out.println("first decrypted message: " + firstDecrypt);
        System.out.println("the second half encryped message: " + secondStr);
        System.out.println("second guessed key = " + secondKey);
        System.out.println("second decrypted message: " + secondDecrypt);
        
        return decryptMsg;
    }
    
    public void testDecryptTwoKeys() {
        FileResource fr = new FileResource();
        String  encryptMsg = fr.asString();
        String decryptMsg = decryptTwoKeys(encryptMsg);
        System.out.println("The decrypt message is: " + decryptMsg);
    }
    
    public void testGetTwoKey() {
        String origMsg = "Just a test string with lots of eeeeeeeeeeeeees";
        System.out.println("Original message: \n" + origMsg);
        // 2. Encrypt the message with single key
        int key1 = 23, key2 = 2;
        // 3. break the original message into 2 parts:
        String firstOrig = halfOfString(origMsg, 0);
        String secondOrig = halfOfString(origMsg, 1);
        
        CaesarCipher c1 = new CaesarCipher();
        String firstEncrypt = c1.encrypt(firstOrig, key1);
        String secondEncrypt = c1.encrypt(secondOrig, key2);
        System.out.println("firstEncrypt msg: " + firstEncrypt);
        System.out.println("secondEncrypt msg: " + secondEncrypt);
        String encryptMsg = combineTwoStr(firstEncrypt, secondEncrypt);
        
        System.out.println("first Encrypted message: \n" + firstEncrypt);  
        System.out.println("second encrypted message: \n" + secondEncrypt);
        System.out.println("combined encrypted message: \n" + encryptMsg);
        // 3. get the key
        String decryptMsg = decryptTwoKeys(encryptMsg);
        System.out.println("the decrypted message: \n" + decryptMsg);
    }
    
    public void testGetKey() {
        String origMsg = "Just a test string with lots of eeeeeeeeeeeeees";
        System.out.println("Original message: \n" + origMsg);
        // 2. Encrypt the message with single key
        int key = 15;
        CaesarCipher c1 = new CaesarCipher();
        String encryptMsg = c1.encrypt(origMsg, key);
        System.out.println("Encrypted message: \n" + encryptMsg);  
        // 3. get the key
        int guessedKey = getKey(encryptMsg);
        System.out.println("the guessed key = " + guessedKey);
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
