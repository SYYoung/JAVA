import edu.duke.*;

/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    private StorageResource geneList = new StorageResource();
    
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
        //System.out.println("stopTAA = "+(stopTAA-startInd) 
        //            + ",stopTAG = "+(stopTAG-startInd) 
        //            + "stopTGA = "+(stopTGA-startInd));
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
    
    public StorageResource getAllGenes(String dna) {
        StorageResource sr = new StorageResource();
        int curInd = 0;
        int counter = 0;
        while (true) {
            String gene = findGene(dna, curInd);
            if (gene.isEmpty())
                break;
            sr.add(gene);
            curInd = dna.indexOf(gene, curInd) + gene.length();
            counter++;
        }
        return sr;
    }
    
    public void testGetAllGenes() {
        FileResource fr = new FileResource("./test4a.txt");
        for (String dna : fr.words()) {
            System.out.println("input gene: \n" +dna);
            System.out.println("finding genes are:");
            this.geneList = getAllGenes(dna);
            
            //for (String item : this.geneList.data()) {
            //    System.out.println(item);
            //}
        }
    }
    
    
    
    public int howMany(String stringa, String stringb) {
        String stringaUpper = new String(stringa.toUpperCase());
        String stringbUpper = new String(stringb.toUpperCase());
        int count = 0, startInd = 0, curInd = 0;
        while (true) {
            curInd = stringbUpper.indexOf(stringaUpper, startInd);
            if (curInd == -1)
                break;
            count++;
            startInd = curInd + stringaUpper.length();
        }
        return count;
    }
    
    public float cgRatio(String dna) {
        // check how many C exist in the dna
        int cCount = howMany("C", dna);
        int gCount = howMany("G", dna);
        System.out.println("cCount = " + cCount + ", gCount = " + gCount);
        return (float)(cCount+gCount)/dna.length();
    }
    
    public int countCTG(String dna) {
        int ctgCount = howMany("CTG", dna);
        return ctgCount;
    }
    
    public void processGenes(StorageResource sr) {
        int count9Char = 0, countCGHigh = 0, maxLen = 0;
        int numCTG = 0;
        String longestS = "";
        float cgRatio;
        
        System.out.println("Inside processGenes()");
        for (String item : this.geneList.data()) {
            // print all strings longer than 9 char
            if (item.length() > 60) {
                System.out.println("longer than 9 chars: " +item);
                count9Char++;
            }
            cgRatio = cgRatio(item);
            if (cgRatio > 0.35) {
                System.out.println("cgRatio > 0.35: " +item);
                countCGHigh++;
            }
            if (item.length() > maxLen) {
                maxLen = item.length();
                longestS = item;
            }
            numCTG = countCTG(item);
        }
        System.out.println("Number of Strings in sr that are longer 60 chars: " + count9Char);
        System.out.println("Number of strings in sr whose CG ratio > 0.35: " +countCGHigh);
        System.out.println("the longest gene is len = " + maxLen);
        System.out.println("The longest gene is: " +longestS);
        System.out.println("Number of CTG is: " + numCTG);
    }
    
    public void testGetAllGeneFromDNA() {
        //FileResource fr = new FileResource("../dna/brca1line.fa");
        //String dna = fr.asString()
        URLResource url = new URLResource("https://www.cs.duke.edu/~rodger/GRch38dnapart.fa");
        String dna = url.asString();
        
        this.geneList = getAllGenes(dna);
        //System.out.println("Total # of genes in this file: " + this.geneList.size());
        //processGenes(this.geneList);
    }
    
    public void testCGRatio() {
        testGetAllGenes();
        for (String item : this.geneList.data()) {
            System.out.println(item);
            float cgRatio = cgRatio(item);
            System.out.println("The CG ratio = " + cgRatio);
            int ctgCount = countCTG(item);
            System.out.println("Total CTG count = " + ctgCount);
        }
        
    }
    
    public void testProcessGenes() {
        //testGetAllGenes();
        testGetAllGeneFromDNA();
        System.out.println("# of genes : " +this.geneList.size());
        processGenes(this.geneList);
        
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
