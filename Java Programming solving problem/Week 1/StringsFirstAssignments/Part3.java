
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public boolean twoOccurrences(String stringa, String stringb) {
        int firstInd = -1, secondInd =-1;
        
        firstInd = stringb.indexOf(stringa);
        if (firstInd == -1)
            return false;
        secondInd = stringb.indexOf(stringa, firstInd+stringa.length());
        if (secondInd == -1)
            return false;
        else
            return true;
    }
    
    public String lastPart(String stringa, String stringb) {
        int startInd;
        startInd = stringb.indexOf(stringa);
        if (startInd == -1) 
            return stringb;
        else {
            return stringb.substring(startInd+stringa.length());
        }
    }
    
    public void testing() {
        String test1b = new String("A story by Abby Long");
        String test2b = new String("banana");
        String test3b = new String("ctgtatgta");
        
        String test1a = "by";
        String test2a = "a";
        String test3a = "atg";
        
        String strA = test3a, strB = test3b;
        boolean result = twoOccurrences(strA, strB);
        if (result)
            System.out.println(strB +" has at least 2 occurrences of " + strA);
        else
            System.out.println(strB + "does not have at least 2 occurrences of " + strA);
            
        String test4a = "an", test4b = "banana";
        String test5a = "zoo", test5b = "forest";
        strA = test5a; strB = test5b;
        System.out.println("strA: " + strA + ", strB: " + strB + ",after last part: " +
            lastPart(strA, strB));
    }
}
