import edu.duke.*;

/**
 * Write a description of Quiz1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class Quiz1 {
   public void findAbc(String input) {
       int index = input.indexOf("abc");
       while (true) {
           if ((index == -1) || (index >= input.length()-3)) {
               break;
           }
           System.out.println("index = " + index);
           System.out.println((index+1) + ", " + (index+4));
           String found = input.substring(index+1, index+4);
           System.out.println(found);
           index = input.indexOf("abc", index+3);
           System.out.println("index after updating " + index);
       }
    }
    
   public void test() {
       //findAbc("abcd");
       FileResource fr = new FileResource("./test_q.txt");
       for (String words : fr.words()) {
           System.out.println("input word: " + words);
           findAbc(words);
       }
    }
}
