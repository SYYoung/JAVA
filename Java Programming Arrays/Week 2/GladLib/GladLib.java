import edu.duke.*;
import java.util.*;

public class GladLib {
    private ArrayList<String> adjectiveList;
    private ArrayList<String> nounList;
    private ArrayList<String> colorList;
    private ArrayList<String> countryList;
    private ArrayList<String> nameList;
    private ArrayList<String> animalList;
    private ArrayList<String> timeList;
    private ArrayList<String> verbList;
    private ArrayList<String> fruitList;
    private ArrayList<String> usedList;
    
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    public GladLib(){
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }
    
    public GladLib(String source){
        initializeFromSource(source);
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {
        adjectiveList= readIt(source+"/adjective.txt"); 
        nounList = readIt(source+"/noun.txt");
        colorList = readIt(source+"/color.txt");
        countryList = readIt(source+"/country.txt");
        nameList = readIt(source+"/name.txt");      
        animalList = readIt(source+"/animal.txt");
        timeList = readIt(source+"/timeframe.txt");     
        verbList = readIt(source+"/verb.txt");
        fruitList = readIt(source+"/fruit.txt");
        
        usedList = new ArrayList<String>();
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        String subString = "";
        switch (label) {
            case "country":
                subString = randomFrom(countryList);
                break;
            case "color":
                subString = randomFrom(colorList);
                break;
            case "noun" : 
                subString = randomFrom(nounList);
                break;
            case "name" :
                subString = randomFrom(nameList);
                break;
            case "adjective" :
                subString = randomFrom(adjectiveList);
                break;
            case "animal" :
                subString = randomFrom(animalList);
                break;
            case "timeframe" :
                subString = randomFrom(timeList);
                break;
            case "number" :
                subString = ""+myRandom.nextInt(50)+5;
                break;
            case "verb" :
                subString = randomFrom(verbList);
                break;
            case "fruit" :
                subString = randomFrom(fruitList);
                break;
            default:
                subString = "**UNKNOWN**";
        }
        
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
        usedList.add(sub);
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
    
    private void ClearUsedList() {
        usedList.clear();
    }
    
    public void makeStory(){
        System.out.println("\n");
        ClearUsedList();
        String story = fromTemplate("datalong/madtemplate2.txt");
        printOut(story, 60);
    }
    


}
