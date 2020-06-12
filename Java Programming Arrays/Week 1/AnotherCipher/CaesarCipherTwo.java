
/**
 * Write a description of CaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipherTwo {
    int mainKey1, mainKey2;
    CaesarCipher c1, c2;
    
    public CaesarCipherTwo(int key1, int key2) {
        this.c1 = new CaesarCipher(key1);
        this.c2 = new CaesarCipher(key2);
        this.mainKey1 = key1;
        this.mainKey2 = key2;
    }
    
    public String encrypt(String input) {
        String firstStr = halfOfString(input, 0);
        String secondStr = halfOfString(input, 1);
        String firstEncrypt = this.c1.encrypt(firstStr);
        String secondEncrypt = this.c2.encrypt(secondStr);
        String joinMsg = combineTwoStr(firstEncrypt, secondEncrypt);
        return joinMsg;
    }
    
    private String halfOfString(String message, int start) {
        StringBuilder sb = new StringBuilder();
        for (int k=start; k<message.length(); k=k+2) {
            sb = sb.append(message.charAt(k));
        }
        return sb.toString();
    }
    
    private String combineTwoStr(String firstStr, String secondStr) {
        StringBuilder totalMsg = new StringBuilder();
        for (int k=0; k<firstStr.length(); k++) {
            totalMsg = totalMsg.append(firstStr.charAt(k));
            if (k < secondStr.length())
                totalMsg = totalMsg.append(secondStr.charAt(k));
        }  
        return totalMsg.toString();
    } 
    
    public String decrypt(String input) {
        String firstStr = halfOfString(input, 0);
        String secondStr = halfOfString(input, 1);
       
        String firstDecrypt = this.c1.decrypt(firstStr);
        String secondDecrypt = this.c2.decrypt(secondStr);
        // combine both messages
        String decryptMsg = combineTwoStr(firstDecrypt, secondDecrypt);
        
        System.out.println("the first half encryped message: " + firstStr);
        System.out.println("first decrypted message: " + firstDecrypt);
        System.out.println("the second half encryped message: " + secondStr);
        System.out.println("second decrypted message: " + secondDecrypt);
        
        return decryptMsg;
    }
    
}
