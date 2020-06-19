import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder sb = new StringBuilder();
        for (int k=whichSlice; k<message.length(); k+=totalSlices) {
            sb.append(message.charAt(k));
        }
        return sb.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cc = new CaesarCracker(mostCommon);
        for (int k=0; k<klength; k++) {
            // build the substrings
            String input = sliceString(encrypted, k, klength);
            key[k] = cc.getKey(input);
        }
        return key;
    }

    public void breakVigenereOld () {
        int klength = 4;
        char mostCommon = 'e';
        int[] key = new int[klength];
        FileResource fr = new FileResource();
        HashSet<String> dictionary = readDictionary(fr);
        String encryptMsg = fr.asString();
        key = tryKeyLength(encryptMsg, klength, mostCommon);
        VigenereCipher vc = new VigenereCipher(key);
        String decryptMsg = vc.decrypt(encryptMsg);
        System.out.println("The decrypted message : \n" +decryptMsg.substring(0,300));
    }
    
    public void breakVigenere () {
        char mostCommon = 'e';
        FileResource fr = new FileResource();
        String encryptMsg = fr.asString();
        
        FileResource frDict = new FileResource();
        HashSet<String> dictionary = readDictionary(frDict);
        String decryptMsg = breakForLanguage(encryptMsg, dictionary);
       
        System.out.println("The decrypted message : \n" +decryptMsg.substring(0,200));
    }
    
    public HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> dict = new HashSet<String>();
        for (String line : fr.lines()) 
            dict.add(line.toLowerCase());
        return dict;
    }
    
    public int countWords(String message, HashSet<String> dictionary) {
        String[] wordList = message.split("\\W+");
        int numValidWord = 0;
        for (String eachWord : wordList) {
            if (dictionary.contains(eachWord.toLowerCase()))
                numValidWord++;
        }
        return numValidWord;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String>dictionary) {
        int maxKeyLen = 100;
        char mostCommon = 'e';
        String bestDecryptMsg = "";
        int maxValidWord = 0;
        int[] bestKey = {};
        for (int keyLen = 1; keyLen <= maxKeyLen; keyLen++) {
            int[] key = tryKeyLength(encrypted, keyLen, mostCommon);
            VigenereCipher vc = new VigenereCipher(key);
            String decryptMsg = vc.decrypt(encrypted);
            int numValidWord = countWords(decryptMsg, dictionary);
            if (numValidWord > maxValidWord) {
                maxValidWord = numValidWord;
                bestDecryptMsg = decryptMsg;
                bestKey = key.clone();
            }
        }
        String[] wordList = bestDecryptMsg.split("\\W+");
        System.out.println("maxValidWord = " +maxValidWord + " out of " +wordList.length);
        System.out.println("key length = " + bestKey.length);
        System.out.println("key is : " + Arrays.toString(bestKey));
        return bestDecryptMsg;
    }
}
