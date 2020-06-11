
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class CaesarCipher {
    public String encrypt(String input, int key) {
        String coverageUpper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String coverageLower = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder(input);
        String encKeyUpper = coverageUpper.substring(key) + coverageUpper.substring(0,key);
        String encKeyLower = encKeyUpper.toLowerCase();
        for (int i=0; i<input.length(); i++) {
            Character ch = input.charAt(i);
            if (Character.isUpperCase(input.charAt(i))) {
                int pos = coverageUpper.indexOf(input.charAt(i));
                sb.setCharAt(i, encKeyUpper.charAt(pos));
            }
            else if (Character.isLowerCase(input.charAt(i))) {
                int pos = coverageLower.indexOf(input.charAt(i));
                sb.setCharAt(i, encKeyLower.charAt(pos));
            }

        }
        return sb.toString();
    }
    
    public String encryptTwoKeys(String input, int key1, int key2) {
        String coverageUpper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String coverageLower = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder(input);
        String encKeyUpper1 = coverageUpper.substring(key1) + coverageUpper.substring(0,key1);
        String encKeyLower1 = encKeyUpper1.toLowerCase();
        String encKeyUpper2 = coverageUpper.substring(key2) + coverageUpper.substring(0,key2);
        String encKeyLower2 = encKeyUpper2.toLowerCase();
        for (int i=0; i<input.length(); i++) {
            Character ch = input.charAt(i);
            if (Character.isUpperCase(input.charAt(i))) {
                int pos = coverageUpper.indexOf(input.charAt(i));
                if (i%2 == 0)
                    sb.setCharAt(i, encKeyUpper1.charAt(pos));
                else
                    sb.setCharAt(i, encKeyUpper2.charAt(pos));
            }
            else if (Character.isLowerCase(input.charAt(i))) {
                int pos = coverageLower.indexOf(input.charAt(i));
                if (i%2 == 0)
                    sb.setCharAt(i, encKeyLower1.charAt(pos));
                else
                    sb.setCharAt(i, encKeyLower2.charAt(pos));
            }

        }
        return sb.toString();
    }
    
    public void testCaesar() {
        int key = 15;
        int key1 = 15, key2 = 21;
        int testcase = 1;
        FileResource fr = new FileResource();
        String encrypted = "";
        for (String line: fr.lines()) {
            if (testcase == 2)
                encrypted = encryptTwoKeys(line, key1, key2);
            else
                encrypted = encrypt(line, key1);
            System.out.println("The original message: " +line);
            System.out.println("with key=" +key1 +", new message");
            System.out.println(encrypted);
        }
    }
    
    
    public void stupid() {
        int[] gap = new int[26];
        String test = "apple";
        for (int i=0; i<test.length(); i++) {
            int diff = Character.compare(test.charAt(i), 'a');
            System.out.println("difference = " + gap);
            gap[diff]++;
        }
        for (int i=0; i<gap.length; i++)
            System.out.println(gap[i]);
    }
}
