
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         FileResource fr = new FileResource(filename);
         WebLogParser logParser = new WebLogParser();
         for (String line : fr.lines()) {
             LogEntry logRec = logParser.parseEntry(line);
             records.add(logRec);
         }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public int countUniqueIPs() {
         ArrayList<String> uniqueIP = new ArrayList<String>();
         for (LogEntry entry : records) {
             String ipAddr = entry.getIpAddress();
             if (!uniqueIP.contains(ipAddr))
                uniqueIP.add(ipAddr);
         }
         return uniqueIP.size();
     }
     
     public void printAllHigherThanNum(int num) {
         for (LogEntry entry : records) {
             int status = entry.getStatusCode();
             if (status > num)
                System.out.println(entry);
         }
     }
     
     public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
         ArrayList<String> uniqueIP = new ArrayList<String>();
         for (LogEntry entry : records) {
             String[] timeList = entry.getAccessTime().toString().split(" ");
             String mmmDD = timeList[1] +" " + timeList[2];
             String ipAddr = entry.getIpAddress();
             if (mmmDD.equals(someday) && (!uniqueIP.contains(ipAddr)))
                uniqueIP.add(ipAddr);
         }
         return uniqueIP;
     }
     
     public int countUniqueIPsInRange(int low, int high) {
         ArrayList<String> uniqueIP = new ArrayList<String>();
         for (LogEntry entry : records) {
             int status = entry.getStatusCode();
             String ipAddr = entry.getIpAddress();
             if ((status >= low) && (status <= high) && 
                    (!uniqueIP.contains(ipAddr)))
                 uniqueIP.add(ipAddr);
         }
         return uniqueIP.size();
     }
}
