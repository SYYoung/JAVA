import edu.duke.*;

/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int curIndex = startIndex;
        while (curIndex != -1) {
            curIndex = dna.indexOf(stopCodon, curIndex);
            if (curIndex == -1) {
                break;
            }
            if ((curIndex - startIndex) %3 == 0) {
                break;
            }
            curIndex++;
        } 
        return curIndex;
    }
    
    public String findGene(String dna, int whereToStart) {
        int stopTAA, stopTAG, stopTGA, minInd, startInd;
        
        String dnaToUpperCase = new String(dna.toUpperCase());
        startInd = dnaToUpperCase.indexOf("ATG", whereToStart);
        if (startInd == -1)
            return "";
        stopTAA = findStopCodon(dnaToUpperCase, startInd, "TAA");
        stopTAG = findStopCodon(dnaToUpperCase, startInd, "TAG");
        stopTGA = findStopCodon(dnaToUpperCase, startInd, "TGA");
        System.out.println("stopTAA = "+(stopTAA-startInd) 
                    + ",stopTAG = "+(stopTAG-startInd) 
                    + "stopTGA = "+(stopTGA-startInd));
        if ((stopTAA == -1) || 
            ((stopTGA != -1) && (stopTGA < stopTAA))) {
            minInd = stopTGA;
        }
        else {
            minInd = stopTAA;
        }
        if ((minInd == -1) ||
            ((stopTAG != -1) && (stopTAG < minInd))) {
            minInd = stopTAG;
        }
        if (minInd == -1)
            return "";
        return dna.substring(startInd, minInd+3);
    }
    
    public int countGenes(String dna) {
        int curInd = 0;
        int counter = 0;
        while (true) {
            String gene = findGene(dna, curInd);
            if (gene.isEmpty())
                break;
            System.out.println(gene);
            curInd = dna.indexOf(gene, curInd) + gene.length();
            counter++;
        }
        return counter;
    }
    
    public void testCountGenes() {
        FileResource fr = new FileResource("./test_quiz.txt");
        for (String dna : fr.words()) {
            System.out.println("input gene: \n" +dna);
            System.out.println("finding genes are:");
            int totalCount = countGenes(dna);
            System.out.println("There are total: " + totalCount +" genes in this dna.");
        }
    }
    
}
