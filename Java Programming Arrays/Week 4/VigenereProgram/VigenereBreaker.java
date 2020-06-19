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

    public void breakVigenere () {
        int klength = 5;
        char mostCommon = 'e';
        int[] key = new int[klength];
        FileResource fr = new FileResource();
        String encryptMsg = fr.asString();
        key = tryKeyLength(encryptMsg, klength, mostCommon);
        VigenereCipher vc = new VigenereCipher(key);
        String decryptMsg = vc.decrypt(encryptMsg);
        System.out.println("The decrypted message : \n" +decryptMsg);
    }
    
}
