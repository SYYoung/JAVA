import edu.duke.*;

/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public int howMany(String stringa, String stringb) {
        int count = 0, startInd = 0, curInd = 0;
        while (true) {
            curInd = stringb.indexOf(stringa, startInd);
            if (curInd == -1)
                break;
            count++;
            startInd = curInd + stringa.length();
        }
        return count;
    }
    
    public void testHowMany() {
        String str1a = "GAA", str1b = "ATGAACGAATTGAATC";
        String str2a = "AA", str2b = "ATAAAA";
        String str3a = "AA", str3b = "ATABCD";
        int counter = 0;
        String stra, strb;
       
        stra = str3a; strb = str3b;
        counter = howMany(stra, strb);
        System.out.println("stra: " + stra + ", strb: " + strb + ", # of occur: " + counter);
    }
}
