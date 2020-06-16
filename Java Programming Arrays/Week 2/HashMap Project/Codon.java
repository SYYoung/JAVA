
/**
 * Write a description of Codon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

public class Codon {
    private HashMap<String, Integer> codonMap;
    
    public Codon() {
        codonMap = new HashMap<String, Integer>();
    }
    
    public void buildCodonMap(int start, String dna) {
        int curIndex = start;
        dna = dna.trim();
        while (curIndex + 3 <= dna.length()) {
            String code = dna.substring(curIndex, curIndex+3);
            if (codonMap.containsKey(code)) {
                codonMap.put(code, codonMap.get(code)+1);
            }
            else {
                codonMap.put(code, 1);
            }
            curIndex += 3;
        }
    }
    
    public String getMostCommonCodon() {
        String most = "";
        int maxCount = 0;
        for (String code : codonMap.keySet()) {
            if (codonMap.get(code) > maxCount) {
                most = code;
                maxCount = codonMap.get(code);
            }
        }
        return most;
    }
    
    public void printCodonCounts(int start, int end) {
        System.out.println("Counts of codons between " +start + " and " +
                    end +" inclusive are:");
        for (String code : codonMap.keySet()) {
            int count = codonMap.get(code);
            if ((count >= start) && (count <= end))
                System.out.println(code +" : " + count);
        }
    }
    
    public void testCodon() {
        FileResource fr = new FileResource();
        String dna = fr.asString().toUpperCase();
        int start = 1, end = 10;
        for (int k=0; k<3; k++) {
            codonMap.clear();
            buildCodonMap(k, dna);
            System.out.println("Reading frame starting with " + k +
                            " results in " +codonMap.size() +" unique codons");
            String mostCommon = getMostCommonCodon();
            System.out.println("and most common codon is: " + mostCommon +
                    " with count " +codonMap.get(mostCommon));
            printCodonCounts(start, end);
        }
    }
}
