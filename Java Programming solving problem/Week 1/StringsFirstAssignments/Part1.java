
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.File;

public class Part1 {
    public String findSimpleGene(String dna) {
        String startCodon = "ATG", endCodon = "TAA";
        int invalidInd = -1;
        int startInd, stopInd;
        
        startInd = dna.indexOf(startCodon);
        if (startInd == invalidInd) 
            return "";
        stopInd = dna.indexOf(endCodon, startInd+startCodon.length());
        if (stopInd == invalidInd)
            return "";
        if ((stopInd - startInd)%3 == 0) {
            return dna.substring(startInd, stopInd+endCodon.length());
        }
        else
            return "";
    }
    
    public void testSimpleGene() {
        String test1 = "ABCDEFGTAA";
        String test2 = "ATGCTGTTT";
        String test3 = "ATGCTGTAGTAA";
        String test4 = "ATGCTGTAGGTAA";
        String test5 = "AAATGCCCTAACTAGATTAAGAAACC";
        
        String theTest = test5;
        System.out.println("The DNA string is: "+ theTest);
        System.out.println("The Gene is : "+ findSimpleGene(theTest));
        
    }
}
