

/**
 * Write a description of GladLibMap here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;

public class GladLib {
    private HashMap<String, ArrayList<String>> myMap;
    private ArrayList<String> usedList;
    private ArrayList<String> usedCategory;
    private String[] wordList = {"adjective", "noun", "color", "country", "name", "animal", 
                        "timeframe", "verb", "fruit"};

    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    public GladLib(){
        myMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
        usedCategory = new ArrayList<String>();
    }
    
    public GladLib(String source){
        myMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(source);
        myRandom = new Random();
        usedCategory = new ArrayList<String>();
    }
    
    private void initializeFromSource(String source) {
        for (int k=0; k<wordList.length; k++) {
            String fname = source +"/" +wordList[k] +".txt";
            ArrayList<String> theList = readIt(fname);
            //System.out.println("fname is: " +fname + ", array size: " + theList.size());
            myMap.put(wordList[k], theList);
        }
        
        usedList = new ArrayList<String>();
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        String subString = "";
        if (myMap.containsKey(label)) {
            subString = randomFrom(myMap.get(label));
            if (!usedCategory.contains(label))
                usedCategory.add(label);
        }
        else if (label.equals("number"))
            subString = ""+myRandom.nextInt(50)+5;
        else
            subString = "**UNKNOWN**";
        return subString;
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        while (usedList.contains(sub)) {
            System.out.println("word is used again: " +sub);
            sub = getSubstitute(w.substring(first+1,last));
        }
        //usedList.add(sub);
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    public int totalWordsInMap() {
        int totalWords = 0;
        for (int k=0; k<wordList.length; k++)
            totalWords += myMap.get(wordList[k]).size();
        return totalWords;
    }
    
    public int totalWordsConsidered() {
        int totalConsidered = 0;
        for (String word : usedCategory)
            totalConsidered += myMap.get(word).size();
        return totalConsidered;
    }
    
    private void ClearUsedList() {
        usedList.clear();
        usedCategory.clear();
    }
    
    public void makeStory(){
        System.out.println("\n");
        ClearUsedList();
        String story = fromTemplate("data/madtemplate.txt");
        printOut(story, 60);
        System.out.println("\nTotal words in the map is: " +totalWordsInMap());
        System.out.println("Total words considered is: " +totalWordsConsidered());
    }
    


}