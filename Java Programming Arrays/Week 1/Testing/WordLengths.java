
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class WordLengths {
    public void countWordLengths(FileResource resource, int[] counts) {
        for (String word : resource.words()) {
            System.out.println("word: " + word);
            int len = word.length();
            if (!Character.isLetter(word.charAt(0)))
                len--;
            if (!Character.isLetter(word.charAt(word.length()-1)))
                len--;
            if (len > counts.length-1)
                counts[counts.length-1]++;
            else if (len > 0)
                counts[len]++;
            System.out.println(word +" : length : " + len);
        }
    }
    
    public int indexOfMax(int[] values) {
        int index = -1, maxVal = 0;
        for (int k=0; k<values.length; k++) {
            if (values[k] > maxVal) {
                index = k;
                maxVal = values[k];
            }
        }
        return index;
    }
    
    public void testCountWordLengths() {
        FileResource fr = new FileResource();
        int[] counts = new int[15];
        countWordLengths(fr, counts);
        for (int i=1; i<counts.length; i++)
            System.out.println(counts[i] +"words of length " + i );
        System.out.println("The index of most freq words is: " +indexOfMax(counts));
    }
}
