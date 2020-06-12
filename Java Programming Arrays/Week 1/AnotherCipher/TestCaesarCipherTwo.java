
/**
 * Write a description of TestCaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class TestCaesarCipherTwo {
    final int CYCLE = 26;
    
    private void countLetters(String str, int[] counts) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int k=0; k<str.length(); k++) {
            char ch = Character.toUpperCase(str.charAt(k));
            int pos = alphabet.indexOf(ch);
            if (pos != -1) {
                counts[pos]++;
            }
        }
    }
    
    private int maxIndex(int[] values) {
        int index = -1, maxVal = 0;
        for (int k=0; k<values.length; k++) {
            if (values[k] > maxVal) {
                index = k;
                maxVal = values[k];
            }
        }
        return index;
    }    
    
    private String halfOfString(String message, int start) {
        StringBuilder sb = new StringBuilder();
        for (int k=start; k<message.length(); k=k+2) {
            sb = sb.append(message.charAt(k));
        }
        return sb.toString();
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
            key = CYCLE - (posE - index);
        return key;
    }
    
    public String breakCaesarCipher(String input) {
        String firstStr = halfOfString(input, 0);
        String secondStr = halfOfString(input, 1);
        int firstKey = getKey(firstStr);
        int secondKey = getKey(secondStr);
        
        CaesarCipherTwo cc = new CaesarCipherTwo(firstKey, secondKey);
        String decryptMsg = cc.decrypt(input);
        
        System.out.println("first guessed key = " + firstKey);
        System.out.println("second guessed key = " + secondKey);
        
        return decryptMsg;
    } 
    
    public void simpleTests() {
        int key1= 17, key2 = 3;
        CaesarCipherTwo cc = new CaesarCipherTwo(key1, key2);
        FileResource fr = new FileResource();
        String input = fr.asString();
        System.out.println("The input message: " +input);
        String encryptMsg = cc.encrypt(input);
        System.out.println("The encrypted message: " +encryptMsg);
        //String decryptMsg = cc.decrypt(encryptMsg);
        //System.out.println("The decrypted message: " +decryptMsg);
        
        // now with guessed key
        String decryptMsg = breakCaesarCipher(encryptMsg);
        System.out.println("The decrypted message by breaking: " +decryptMsg);
    }
}
