
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

public class CharactersInPlay {
    private ArrayList<String> name;
    private ArrayList<Integer> count;
    
    public CharactersInPlay() {
        name = new ArrayList<String>();
        count = new ArrayList<Integer>();
    }
    
    private void update(String person) {
        int pos = name.indexOf(person);
        if (pos == -1) {
            name.add(person);
            count.add(1);
        }
        else {
            int val = count.get(pos);
            count.set(pos, val+1);
        }
    }
    
    private void findAllCharacters() {
        FileResource fr = new FileResource();
        for (String line : fr.lines()) {
            int pos = line.indexOf(".");
            if (pos != -1) {
                String person = line.substring(0, pos);
                update(person);
            }
        }
    }
    
    private void printMedium(int medium) {
        Integer[] copyCount = new Integer[count.size()];
        copyCount = count.toArray(copyCount);
        //copyCount.sort();
        //Arrays.sort(copyCount);
        //int medium = copyCount[copyCount.length/2];
        for (int k=0; k<name.size(); k++) {
            if (count.get(k) >= medium)
                System.out.println(name.get(k) + ":      " + count.get(k));
        }
    }
    
    public void charactersWithNumParts(int num1, int num2) {
        System.out.println("Here is the list of characters whose speaking parts " 
                    +"are between " +num1 +" and " +num2);
        for (int k=0; k<name.size(); k++) {
            if ((count.get(k) >= num1) && (count.get(k) <= num2))
                System.out.println(name.get(k) +":      " + count.get(k));
        }
    }
    
    public void tester() {
        findAllCharacters();
        if (name.size() < 20)
            for (int k=0; k<name.size(); k++) {
                System.out.println(name.get(k) + ":\t" + count.get(k));
            }
        charactersWithNumParts(10,15);
    }
}
