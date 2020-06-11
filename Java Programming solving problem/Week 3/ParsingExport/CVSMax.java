import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;

/**
 * Write a description of CVSMax here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CVSMax {
    
    public CSVRecord getColdestRec(CSVRecord current, CSVRecord coldest) {
        int invalidTemp = -9999;
        
        if (coldest == null)
            coldest = current;
        else {
            double curTemp = Double.parseDouble(current.get("TemperatureF"));
            double coldestTemp = Double.parseDouble(coldest.get("TemperatureF"));
            if ((curTemp < coldestTemp) && (curTemp != invalidTemp))
                coldest = current;
        }
        return coldest;
    }
    
    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord coldestRec = null;
        double coldestTemp;
        for (CSVRecord curRec : parser) {
            coldestRec = getColdestRec(curRec, coldestRec);
        }
        return coldestRec;
    }
    public String fileWithColdestTemperature() {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord coldestRec = null, curRec = null;
        String coldestFName = null;
        
        for (File f: dr.selectedFiles()) {
            if (coldestFName == null)
                coldestFName = f.getName();
            FileResource fr = new FileResource(f);
            curRec = coldestHourInFile(fr.getCSVParser());
            coldestRec = getColdestRec(curRec, coldestRec);
            if (coldestRec == curRec) { // implies that current is the coldest
                coldestFName = f.getPath();
            }
        } 
        return coldestFName;
    }
    
    public void printAllTemperatures(CSVParser parser) {
        for (CSVRecord rec: parser)
            System.out.println(rec.get("DateUTC") +":  " + rec.get("TemperatureF"));
    }
    
    public void testColdestHourInFile() {
        FileResource fr = new FileResource();
        CSVRecord coldestRec = coldestHourInFile(fr.getCSVParser());
        System.out.println("The coldest temperature is: " +coldestRec.get("TemperatureF")
                            +" at: " +coldestRec.get("TimeEST"));
    }
    
    public void testFileWithColdestTemperature() {
        String fPath = fileWithColdestTemperature();
        FileResource fr = new FileResource(fPath);
        String fName = fPath.substring(fPath.lastIndexOf("/")+1);
        CSVRecord coldestRec = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest day was in file " +fName);
        System.out.println("Coldest temperature on that day was " 
                        + coldestRec.get("TemperatureF"));
        System.out.println("All the temperatures on the coldest day were:");
        printAllTemperatures(fr.getCSVParser());
    }
}
