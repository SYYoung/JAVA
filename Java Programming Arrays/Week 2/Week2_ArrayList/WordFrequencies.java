
/**
 * Write a description of WordFrequencies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public WordFrequencies() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    private String removePunct(String word) {
        String wordLower = word.toLowerCase();
        char ch = wordLower.charAt(wordLower.length()-1);
        if (!Character.isLetterOrDigit(ch))
            wordLower = wordLower.substring(0, wordLower.length()-1);
        return wordLower;
    }
    
    public void findUnique() {
        // first clear the arrays
        myWords.clear();
        myFreqs.clear();
        FileResource fr = new FileResource();
        for (String word: fr.words()) {
            //String newWord = removePunct(word);
            String newWord = word.toLowerCase();
            int pos = myWords.indexOf(newWord);
            if (pos == -1) {
                myWords.add(newWord);
                myFreqs.add(1);
            }
            else {
                int val = myFreqs.get(pos);
                myFreqs.set(pos, val+1);
            }
        }
    }
    
    public int findIndexOfMax() {
        int maxVal = 0, maxIndex=0;
        for (int k=0; k<myWords.size(); k++) {
            if (myFreqs.get(k) > maxVal) {
                maxVal = myFreqs.get(k);
                maxIndex = k;
            }
        }
        return maxIndex;
    }
    
    public void tester() {
        findUnique();
        System.out.println("Number of unique words: " + myWords.size());
        //for (int k=0; k<myWords.size(); k++) {
        //    System.out.println(myFreqs.get(k) +"\t : " + myWords.get(k));
        //}
        int maxIndex = findIndexOfMax();
        System.out.println("The word that occurs most often and its count are: " +
                        myWords.get(maxIndex) +"  " + myFreqs.get(maxIndex));
    }
}
