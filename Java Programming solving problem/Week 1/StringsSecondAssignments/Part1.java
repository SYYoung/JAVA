import edu.duke.*;

/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
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
    
    public void testFindGene() {
        // 5 testcases: no ATG, dna with one valid stop, with multiple valid stop, 
        // with no valid stops
        int stopTAA, stopTAG, stopTGA, startInd;
        FileResource fr = new FileResource("./test2.txt");
        for (String dna : fr.words()){
            System.out.println("the input gene: \n" + dna);
            startInd = dna.indexOf("ATG");
            String result = findGene(dna, startInd);
            System.out.println("result: " + result);
        }
    }
    
    public void printAllGenes(String dna) {
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
    }
    
    public void testPrintAllGenes() {
        FileResource fr = new FileResource("./test1.txt");
        for (String dna : fr.words()) {
            System.out.println("input gene: \n" +dna);
            System.out.println("finding genes are:");
            printAllGenes(dna);
        }
    }
    
    public void testFindStopCodon() {
        int stopTAA, stopTAG, stopTGA, startInd;
        FileResource fr = new FileResource("./tes2.txt");
        for (String dna : fr.words()){
            System.out.println(dna);
            startInd = dna.indexOf("ATG");
            stopTAA = findStopCodon(dna, startInd, "TAA");
            stopTAG = findStopCodon(dna, startInd, "TAG");
            stopTGA = findStopCodon(dna, startInd, "TGA");
            System.out.println("stopTAA = " + stopTAA + ",stopTAG = " + stopTAG +
                ", stopTGA = " +stopTGA);
        }
    }
}
