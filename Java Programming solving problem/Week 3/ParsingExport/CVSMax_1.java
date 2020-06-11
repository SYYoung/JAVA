
/**
 * Write a description of CVSMax_1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;

/**
 * Write a description of CVSMax here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CVSMax_1 {
    String invalidTmpStr = "-9999";
    String invalidHumidStr = "N/A";
    int invalidTemp = Integer.parseInt(invalidTmpStr);
    
    public boolean invalidField(CSVRecord current, String field) {
        String curField = current.get(field);
        boolean invalid = true;
        if (field.equals("TemperatureF"))
            invalid =  curField.equals(invalidTmpStr);
        else if (field.equals("Humidity"))
            invalid =  curField.equals(invalidHumidStr);
            
        return invalid;
    }
    
    public CSVRecord getLowestRec(CSVRecord current, CSVRecord lowest, String field) {
        if (invalidField(current, field))
            return lowest;
        if (lowest == null)
            lowest = current;
        else {
            double curField = Double.parseDouble(current.get(field));
            double lowestField = Double.parseDouble(lowest.get(field));
            if ((curField < lowestField))
                lowest = current;
        }
        return lowest;
    }
    
    public CSVRecord lowestHourInFile(CSVParser parser, String field) {
        CSVRecord lowestRec = null;
        double lowestVal;
        for (CSVRecord curRec : parser) {
            lowestRec = getLowestRec(curRec, lowestRec, field);
        }
        return lowestRec;
    }
    
    public String fileWithLowest(String field) {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord lowestRec = null, curRec = null;
        String lowestFName = null;
        
        for (File f: dr.selectedFiles()) {
            if (lowestFName == null)
                lowestFName = f.getName();
            FileResource fr = new FileResource(f);
            curRec = lowestHourInFile(fr.getCSVParser(), field);
            lowestRec = getLowestRec(curRec, lowestRec, field);
            if (lowestRec == curRec) { // implies that current is the coldest
                lowestFName = f.getPath();
            }
        } 
        return lowestFName;
    }
    
    public String fileWithColdestTemperature() {
        String field = "TemperatureF";
        
        String lowestFName = fileWithLowest(field);
        return lowestFName;
    }
    
    public void printAllTemperatures(CSVParser parser) {
        for (CSVRecord rec: parser)
            System.out.println(rec.get("DateUTC") +":  " + rec.get("TemperatureF"));
    }
    
    public double averageTemperatureInFile(CSVParser parser) {
        double total = 0;
        int numOfDay = 0;
        String field = "TemperatureF";
        
        for (CSVRecord rec : parser) {
            if (!invalidField(rec, field)) {
                total += Double.parseDouble(rec.get(field));
                numOfDay++;
            }
        }
        return total/numOfDay;
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        double total = 0;
        int numOfDay = 0;
        String field = "TemperatureF";
        
        for (CSVRecord rec : parser) {
            if (!invalidField(rec, "TemperatureF") && !invalidField(rec, "Humidity")) {
                if (Double.parseDouble(rec.get("Humidity")) >= 80) {
                    total += Double.parseDouble(rec.get("TemperatureF"));
                    numOfDay++;
                }
            }
        }
        return total/numOfDay;
    }
    
    public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        double avgTemp = averageTemperatureInFile(fr.getCSVParser());
        System.out.println("Average temperature in file is " + avgTemp);
    }
    
    public void testAverageTemperatureWithHighHumidityInFile() {
        int humidVal = 80;
        
        FileResource fr = new FileResource();
        double avgTemp = averageTemperatureWithHighHumidityInFile(fr.getCSVParser(), humidVal);
        System.out.println("Average temperature when high Humidity is " + avgTemp);
    }
    
    public void testColdestHourInFile() {
        FileResource fr = new FileResource();
        CSVRecord coldestRec = lowestHourInFile(fr.getCSVParser(), "TemperatureF");
        System.out.println("The coldest temperature is: " +coldestRec.get("TemperatureF")
                            +" at: " +coldestRec.get("DateUTC"));
    }
    
    public void testLowestHumidityInFile() {
        String field = "Humidity";
        FileResource fr = new FileResource();
        CSVRecord lowestRec = lowestHourInFile(fr.getCSVParser(), field);
        System.out.println("Lowest Humidity was " +lowestRec.get(field)
                            +" at " +lowestRec.get("DateUTC"));   
                            
    }
    
    public void testLowestHumidityInManyFiles() {
        String field = "Humidity";
        String fPath = fileWithLowest(field);
        FileResource fr = new FileResource(fPath);
        
        CSVRecord lowestRec = lowestHourInFile(fr.getCSVParser(), field);
        System.out.println("Lowest Humidity was " + lowestRec.get(field) + " at "
                            + lowestRec.get("DateUTC"));
    }
    
    public void testFileWithColdestTemperature() {
        String field = "TemperatureF";
        String fPath = fileWithLowest(field);
        FileResource fr = new FileResource(fPath);
        String fName = fPath.substring(fPath.lastIndexOf("/")+1);
        CSVRecord coldestRec = lowestHourInFile(fr.getCSVParser(), field);
        System.out.println("Coldest day was in file " +fName);
        System.out.println("Coldest temperature on that day was " 
                        + coldestRec.get("TemperatureF"));
        System.out.println("All the temperatures on the coldest day were:");
        printAllTemperatures(fr.getCSVParser());
    }
}

