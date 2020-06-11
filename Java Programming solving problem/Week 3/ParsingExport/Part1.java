import edu.duke.*;
import org.apache.commons.csv.*;

/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public String countryInfo(CSVParser parser, String country) {
        String allInfo = new String("NOT FOUND");
        
        for (CSVRecord record : parser) {
            if (country.equals(record.get("Country"))) {
                // return all info about this country
                allInfo = new String(record.get("Country") + ": "
                                    +record.get("Exports") + ": "
                                    +record.get("Value (dollars)"));
                break;
            }
        }
        return allInfo;
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1,
                    String exportItem2) {
        for (CSVRecord record: parser) {
            String exportItem = record.get("Exports");
            if (exportItem.contains(exportItem1) && exportItem.contains(exportItem2)) {
                System.out.println(record.get("Country"));
            }
        }
    }
    
    public int numberOfExporters(CSVParser parser, String exportitem) {
        int total = 0;
        for (CSVRecord record: parser) {
            if (record.get("Exports").contains(exportitem)) 
                total++;
        }
        return total;
    }
    
    public void bigExporters(CSVParser parser, String dollar) {
        for (CSVRecord record : parser) {
            if (record.get("Value (dollars)").length() > dollar.length()) {
                System.out.println(record.get("Country") +" " +
                            record.get("Value (dollars)"));
            }
        }
    }
    public void tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        int testcase = 4;
        
        if (testcase == 1) {
            String countryName = "Nauru";
            String info = countryInfo(parser, countryName);
            System.out.println(info);
        }
        
        // test listExportersTwoProducts
        if (testcase == 2) {
            parser = fr.getCSVParser();
            String item1 = "cotton", item2 = "flowers";
            listExportersTwoProducts(parser, item1, item2);
        }
        
        if (testcase == 3) {
            parser = fr.getCSVParser();
            String item3 = "cocoa";
            int num = numberOfExporters(parser, item3);
            System.out.println("Total number of countries export " +item3 +
                            ": " +num);
        }
        
        if (testcase == 4) {
            parser = fr.getCSVParser();
            String item4 = "$999,999,999,999";
            
            bigExporters(parser, item4);
        }
    }
}
