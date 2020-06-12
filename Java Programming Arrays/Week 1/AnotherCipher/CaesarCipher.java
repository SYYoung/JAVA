
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipher {
    private String alphabet;
    private String shiftedAlphabet;
    int mainKey;
    
    public CaesarCipher(int key) {
        this.alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        this.shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0,key);  
        this.mainKey = key;
    }
    
    public String encrypt(String input) {
        StringBuilder sb = new StringBuilder(input);
        String lowerAlphabet = this.alphabet.toLowerCase();
        String shiftedLower = this.shiftedAlphabet.toLowerCase();
        for (int i=0; i<input.length(); i++) {
            Character ch = input.charAt(i);
            if (Character.isUpperCase(input.charAt(i))) {
                int pos = this.alphabet.indexOf(input.charAt(i));
                sb.setCharAt(i, this.shiftedAlphabet.charAt(pos));
            }
            else if (Character.isLowerCase(input.charAt(i))) {
                int pos = lowerAlphabet.indexOf(input.charAt(i));
                sb.setCharAt(i, shiftedLower.charAt(pos));
            } 
        }
        return sb.toString();
    }
    
    public String decrypt(String input) {
        CaesarCipher cc = new CaesarCipher(26 - this.mainKey);
        String decryptMsg = cc.encrypt(input);
        return decryptMsg;
    }
}
