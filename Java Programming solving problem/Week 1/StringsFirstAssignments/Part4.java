import edu.duke.*;

/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part4 {
    public void findWebLink() {
        //String theLink = new String("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        String theLink = new String("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        URLResource myUrl = new URLResource("https://www.dukelearntoprogram.com/course2/data/manylinks.html");
        String s = new String("youtube.com");
        int startQ, endQ, stringInd;
        int totalWordCount = 0, youtubeCount = 0;
        for (String word: myUrl.words()) {
            // check if youtube.com is included in the string
            //System.out.println(word);
            String lowerWord = new String(word.toLowerCase());
            stringInd = lowerWord.indexOf(s);
            totalWordCount++;
            if (stringInd != -1) {
                startQ = word.lastIndexOf("\"", stringInd);
                endQ = word.indexOf("\"", stringInd);
                System.out.println(word.substring(startQ, endQ));
                youtubeCount++;
            }
        }
        System.out.println("Total word count: " + totalWordCount);
        System.out.println("Youtube count: " + youtubeCount);
    }
    
    public void testWebLink() {
        findWebLink();
    }
}
