
/**
 * Write a description of JustTest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;

public class JustTest {
    public JustTest() {
        FileResource fr = new FileResource();
        for (String line : fr.lines()) {
            String[] output = line.split(" ");
            System.out.println("The splitted string is: ");
            for (int k=0; k<output.length; k++)
                System.out.println(output[k]);
        }
    }
}
