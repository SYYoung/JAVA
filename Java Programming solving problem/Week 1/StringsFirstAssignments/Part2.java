
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public String findSimpleGene(String dna, String startCodon, String stopCodon) {
        //String startCodon = "ATG", endCodon = "TAA";
        int invalidInd = -1;
        int startInd, stopInd;
        String startCode, stopCode;
        
        boolean isUpper = dna.equals(dna.toUpperCase());
        if (isUpper) {
            startCode = startCodon.toUpperCase();
            stopCode = stopCodon.toUpperCase();
        }
        else {
            startCode = startCodon.toLowerCase();
            stopCode = stopCodon.toLowerCase();
        }
        
        startInd = dna.indexOf(startCode);
        if (startInd == invalidInd) 
            return "";
        stopInd = dna.indexOf(stopCode, startInd+startCode.length());
        if (stopInd == invalidInd)
            return "";
        if ((stopInd - startInd)%3 == 0) {
            return dna.substring(startInd, stopInd+stopCode.length());
        }
        else
            return "";
    }
    
    public void testSimpleGene() {
        String test1 = "ABCDEFGTAA";
        String test2 = "ATGCTGTTT";
        String test3 = "ATGCTGTAGTAA";
        String test4 = "ATGCTGTAGGTAA";
        
        String test5 = "ATGGGTTAAGTC";
        String test6 = "gatgctataat";
        String test7 = "ATGTAA";
        
        String startCodon = "ATG", stopCodon = "TAA";
        
        String theTest = test4;
        System.out.println("The DNA string is: "+ theTest);
        System.out.println("The Gene is : "+ findSimpleGene(theTest, startCodon, stopCodon));
        
    }
}
