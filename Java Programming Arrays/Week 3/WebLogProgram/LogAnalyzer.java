
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
     private HashMap<String, Integer> uniqueIPCount;
     private HashMap<String, ArrayList<String>> ipVisitOnDay;
     
     public LogAnalyzer() {
         records = new ArrayList<LogEntry>();
         uniqueIPCount = new HashMap<String, Integer>();
         ipVisitOnDay = new HashMap<String, ArrayList<String>>();
     }
        
     public void readFile(String filename) {
         FileResource fr = new FileResource(filename);
         WebLogParser logParser = new WebLogParser();
         uniqueIPCount.clear();
         ipVisitOnDay.clear();
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
     
     public HashMap<String, Integer> countVisitsPerIP() {
         for (LogEntry entry : records) {
             String ipAddr = entry.getIpAddress();
             if (!uniqueIPCount.containsKey(ipAddr))
                uniqueIPCount.put(ipAddr, 1);
             else
                uniqueIPCount.put(ipAddr, uniqueIPCount.get(ipAddr)+1);
         }  
         return uniqueIPCount;
     }
        
     public int countUniqueIPs() {
         if (uniqueIPCount.isEmpty())
            uniqueIPCount = countVisitsPerIP();
         return uniqueIPCount.size();
     }
     
     public void printAllHigherThanNum(int num) {
         for (LogEntry entry : records) {
             int status = entry.getStatusCode();
             if (status > num)
                System.out.println(entry);
         }
     }
     
     private void getIPVisitOnDay() {
         for (LogEntry entry : records) {
             String[] timeList = entry.getAccessTime().toString().split(" ");
             String mmmDD = timeList[1] +" " + timeList[2];
             String ipAddr = entry.getIpAddress();
             if (!ipVisitOnDay.containsKey(mmmDD)) {
                ArrayList<String> ip = new ArrayList<String>();
                ip.add(ipAddr);
                ipVisitOnDay.put(mmmDD, ip);
            }
            else {
                ArrayList<String> ip = ipVisitOnDay.get(mmmDD);
                ip.add(ipAddr);
                ipVisitOnDay.put(mmmDD, ip);
            }
         }    
     }
        
     public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
         ArrayList<String> uniqueIPAddr = new ArrayList<String>();
         if (ipVisitOnDay.isEmpty())
            getIPVisitOnDay();
         if (!ipVisitOnDay.containsKey(someday))
            return uniqueIPAddr;
         for (String ip : ipVisitOnDay.get(someday)) {
             if (!uniqueIPAddr.contains(ip))
                uniqueIPAddr.add(ip);
         }
         return uniqueIPAddr;
     }
     
     public HashMap<String, ArrayList<String>> iPsForDays() {
         if (ipVisitOnDay.isEmpty())
            getIPVisitOnDay();
         return ipVisitOnDay;
     }
     
     public int countUniqueIPsInRange(int low, int high) {
         ArrayList<String> uniqueIPAddr = new ArrayList<String>();
         for (LogEntry entry : records) {
             int status = entry.getStatusCode();
             String ipAddr = entry.getIpAddress();
             if ((status >= low) && (status <= high) && 
                    (!uniqueIPAddr.contains(ipAddr)))
                 uniqueIPAddr.add(ipAddr);
         }
         return uniqueIPAddr.size();
     }
     
     public void testDayWithMostIPVisits() {
         if (ipVisitOnDay.isEmpty()) {
             getIPVisitOnDay();
         }
         for (String day : ipVisitOnDay.keySet())
            System.out.println("Day : " +day +" , # of IP visited "
                            +ipVisitOnDay.get(day).size());
     }
        
     public String dayWithMostIPVisits() {
         if (ipVisitOnDay.isEmpty())
            getIPVisitOnDay();
         int maxVisit = 0;
         String whichDate = "";
         for (String day : ipVisitOnDay.keySet()) {
             if (ipVisitOnDay.get(day).size() > maxVisit) {
                whichDate = day;
                maxVisit = ipVisitOnDay.get(day).size();
            }
         }
         return whichDate;
     }
     
     public ArrayList<String> iPsWithMostVisitsOnDay(String time) {
         if (ipVisitOnDay.isEmpty())
            getIPVisitOnDay();
         ArrayList<String> ipAddr = new ArrayList<String>();
         HashMap<String, Integer> ipCount = 
                    new HashMap<String, Integer>();
         if (ipVisitOnDay.containsKey(time)) {
             ArrayList<String> ipList = ipVisitOnDay.get(time);
             for (String ip : ipList)
                if (!ipCount.containsKey(ip))
                    ipCount.put(ip, 1);
                else
                    ipCount.put(ip, ipCount.get(ip)+1);
             ArrayList<Integer> count = new ArrayList<>(ipCount.values());
             Collections.sort(count, Collections.reverseOrder());
             int maxCount = count.get(0);
             for (String ip : ipCount.keySet())
                if (ipCount.get(ip) == maxCount)
                    ipAddr.add(ip);
         }
         return ipAddr;
     }
     
     public int mostNumberVisitsByIP() {
         if (uniqueIPCount.isEmpty())
            uniqueIPCount = countVisitsPerIP(); 
         
         ArrayList<Integer> ipCount = new ArrayList<>(uniqueIPCount.values());
         Collections.sort(ipCount, Collections.reverseOrder());
         return ipCount.get(0);
     }
     
     public ArrayList<String> iPsMostVisits() {
         int maxNum = mostNumberVisitsByIP();
         ArrayList<String> ipAddr = new ArrayList<String>();
         for (String addr : uniqueIPCount.keySet()) {
             if (uniqueIPCount.get(addr) == maxNum)
                ipAddr.add(addr);
         }
         return ipAddr;
     }
}
