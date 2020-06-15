
/**
 * Write a description of stupid here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class stupid {
    public void getSub(String label) {
        switch (label) {
            case "country" :
                System.out.println("country is selected.");
                break;
            case "color" :
                System.out.println("color is selected." );
                break;
            default:
                System.out.println("Neither country or color is selected.");
        }
    }
}
