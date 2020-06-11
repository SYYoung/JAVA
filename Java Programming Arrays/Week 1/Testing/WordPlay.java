
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordPlay {
    public boolean isVowel(char ch) {
        Character chLower = Character.toLowerCase(ch);
        if (chLower.equals('a') || chLower.equals('e') || chLower.equals('i')
                || chLower.equals('o') || chLower.equals('u'))
            return true;
        else
            return false;
    }
    
    public String replaceVowels(String phrase, char ch) {
        StringBuilder sb = new StringBuilder(phrase);
        for (int i=0; i<phrase.length(); i++) {
            if (isVowel(phrase.charAt(i))) {
                sb.setCharAt(i, ch);
            }
        }
        return sb.toString();
    }
    
    public String emphasize(String phrase, char ch) {
        StringBuilder sb = new StringBuilder(phrase);
        for (int i=0; i<phrase.length(); i++) {
            Character chLower = Character.toLowerCase(phrase.charAt(i));
            if (chLower.equals(ch)) {
                if (i%2==0)
                    sb.setCharAt(i, '*');
                else
                    sb.setCharAt(i, '+');
            }
        }
        return sb.toString();
    }
    
    public void testEmphasize() {
        String str1 = "dna ctgaaactga", str2 = "Mary Bella Abracadabra";
        String testStr = str2;
        String afterEmphasize = emphasize(testStr, 'a');
        System.out.println("Before emphasize: phrase is: " + testStr);
        System.out.println("After emphasize: phrase is: " +afterEmphasize);
    }
    
    public void testReplaceVowels() {
        String  testStr = "Hello World";
        String afterReplace = replaceVowels(testStr, '*');
        System.out.println("Before replace, phrase is: " + testStr);
        System.out.println("After replace, phrase is: " + afterReplace);
    }
}
