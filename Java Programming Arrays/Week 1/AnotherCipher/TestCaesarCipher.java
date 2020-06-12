
/**
 * Write a description of TestCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class TestCaesarCipher {
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
    
    public void simpleTests() {
        FileResource fr = new FileResource();
        String origMsg = fr.asString();
        int key = 18;
        CaesarCipher c1 = new CaesarCipher(key);
        String encryptMsg = c1.encrypt(origMsg);
        String decryptMsg = c1.decrypt(encryptMsg);
        
        System.out.println("The original message: \n" + origMsg);
        System.out.println("The encrypted message: " +encryptMsg);
        System.out.println("The decrypted message: " +decryptMsg);
        
        // now we calculate the key
        decryptMsg = breakCaesarCipher(encryptMsg);
        System.out.println("From breakCaesarCipher(): The decrypted message: \n" + decryptMsg);
    }
    
    private int getKey(String s) {
        int[] counts = new int[30];
        int posE = 4; // index of 'e'
        int key;
        
        countLetters(s, counts);
        int index = maxIndex(counts);
        if (index >= posE) 
            key = index - posE;
        else
            key = 26 - (posE - index);
        return key;
    }
    
    public String breakCaesarCipher(String input) {
        int guessedKey = getKey(input);
        CaesarCipher c1 = new CaesarCipher(guessedKey);
        String decryptMsg = c1.decrypt(input);
        System.out.println("The encryption key : " + guessedKey);
        return decryptMsg;
    }
}
