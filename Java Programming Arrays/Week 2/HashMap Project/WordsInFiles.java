
/**
 * Write a description of WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
import java.io.File;

public class WordsInFiles {
    private HashMap<String, ArrayList<String>> wordMap;
    
    public WordsInFiles() {
        wordMap = new HashMap<String, ArrayList<String>>();
    }
    
    private void addWordsFromFile(File f) {
        String fname = f.getName();
        FileResource fr = new FileResource(f);
        for (String word : fr.words()) {
            if (wordMap.containsKey(word)) {
                if (!wordMap.get(word).contains(fname))
                    wordMap.get(word).add(fname);
            }
            else {
                ArrayList<String> list = new ArrayList<String>();
                list.add(fname);
                wordMap.put(word, list);
            }
        }
    }
    
    public void buildWordFileMap() {
        wordMap.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()) {
            addWordsFromFile(f);
        }
    }
    
    public int maxNumber() {
        int maxCount = 0;
        for (String word : wordMap.keySet()) {
            int count = wordMap.get(word).size();
            if (count > maxCount)    
                maxCount = count;
        }
        return maxCount;
    }
    
    public ArrayList<String> wordsInNumFiles(int number) {
        ArrayList<String> wordList = new ArrayList<String>();
        
        for (String word: wordMap.keySet()) {
            if (wordMap.get(word).size() == number)
                wordList.add(word);
        }
        return wordList;
    }
    
    public void printFilesIn(String word) {
        if (wordMap.containsKey(word)) {
            System.out.print("\"" +word + "\" appears in the files: ");
            for (String fname : wordMap.get(word))
                System.out.print(fname + ", ");
        }
        System.out.println();
    }
    
    public void tester() {
        buildWordFileMap();
        int maxNum = maxNumber();
        int number = maxNum;
        ArrayList<String> wordList = wordsInNumFiles(maxNum);
        wordList = wordsInNumFiles(4);
        number = 4;
        System.out.print("The greatest number of files a word appears in is " +
                    number +", and there are  " +wordList.size() +
                    " such words: ");
        for (String word : wordList)
            System.out.print("\"" +word + "\", ");
        System.out.println();
        
        //for (String word : wordList)
        //    printFilesIn(word);
        printFilesIn("tree");
    }
}
