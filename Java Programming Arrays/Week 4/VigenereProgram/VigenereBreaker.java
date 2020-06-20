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
    
    public void breakVigenereOld2 () {
        char mostCommon = 'e';
        FileResource fr = new FileResource();
        String encryptMsg = fr.asString();
        
        FileResource frDict = new FileResource();
        HashSet<String> dictionary = readDictionary(frDict);
        String decryptMsg = breakForLanguage(encryptMsg, dictionary);
       
        System.out.println("The decrypted message : \n" +decryptMsg.substring(0,200));
    }
    
    public void breakVigenere () {
        FileResource fr = new FileResource();
        String encryptMsg = fr.asString();
        HashMap<String,HashSet<String>> allDict = readAllDictionaries();
        String decryptMsg = breakForAllLangs(encryptMsg, allDict);
       
        System.out.println("The decrypted message : \n" +decryptMsg.substring(0,200));
    }
    
    public HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> dict = new HashSet<String>();
        for (String line : fr.lines()) 
            dict.add(line.toLowerCase());
        return dict;
    }
    
    public HashMap<String, HashSet<String>> readAllDictionaries() {
        String[] langList = {"Danish", "Dutch", "English", "French", "German",
                                "Italian", "Portuguese", "Spanish"};
        HashMap<String, HashSet<String>> allDict = new HashMap<String,HashSet<String>>();
        String fPrefix = "./dictionaries/";
        for (String lang : langList) {
            String fname = fPrefix + lang;
            allDict.put(lang, readDictionary(new FileResource(fname)));
            System.out.println("building dictionary: " + lang);
        }
        return allDict;
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
        //char mostCommon = 'e';
        char mostCommon = mostCommonCharIn(dictionary);
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
    
    private HashMap<Character, Integer> buildCharList(HashSet<String> dictionary) {
        HashMap<Character, Integer> countChar = new HashMap<Character, Integer>();
        for (String word : dictionary) {
            for (int k=0; k<word.length(); k++) {
                char ch = word.charAt(k);
                if (!countChar.containsKey(ch))
                    countChar.put(ch, 1);
                else
                    countChar.put(ch, countChar.get(ch)+1);
            }
        } 
        return countChar;
    }
    
    private char getCharWithValue(HashMap<Character, Integer> charList, int whichVal) {
        for (char ch : charList.keySet())
            if (charList.get(ch) == whichVal)
                return ch;
        return ' ';
    }
    
    public char mostCommonCharIn(HashSet<String> dictionary) {
        char mostCommon = ' ';
        HashMap<Character, Integer> charList = buildCharList(dictionary);
        ArrayList<Integer> val = new ArrayList<>(charList.values());
        Collections.sort(val, Collections.reverseOrder());
        int maxCount = val.get(0);
        mostCommon = getCharWithValue(charList, maxCount);
        return mostCommon;
    }
    
    public String breakForAllLangs(String encrypted, 
                        HashMap<String, HashSet<String>>languages) {
        String bestLang = "";
        String bestDecryptMsg = "";
        int maxCount = 0;
        for (String whichLang : languages.keySet()) {
            HashSet<String> dict = languages.get(whichLang);
            System.out.println("Break language : " + whichLang);
            String decryptMsg = breakForLanguage(encrypted, dict);
            int numValidWord = countWords(decryptMsg, dict);
            System.out.println("In " +whichLang +" , numValidWord = " +numValidWord);
            if (numValidWord > maxCount) {
                bestLang = whichLang;
                bestDecryptMsg = decryptMsg;
                maxCount = numValidWord;
            }
        }
        return bestDecryptMsg;
    }
}
