
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer(int testcase) {
        LogAnalyzer analyzer = new LogAnalyzer();
        String fname = "../Testcase/weblog-short_log";
        analyzer.readFile(fname);
        switch (testcase) {
            case 1 : 
                analyzer.printAll();
                break;
            case 2:
                System.out.println("Number of unique IPs : " 
                        +analyzer.countUniqueIPs());
                break;
            case 3:
                int num = 200;
                analyzer.printAllHigherThanNum(num);
                break;
            case 4:
                String someday = "Sep 30";
                ArrayList<String> uniqueIP = 
                            analyzer.uniqueIPVisitsOnDay(someday);
                System.out.println("The unique IPs visit on " +someday +":");
                for (String ip : uniqueIP)
                    System.out.println(ip);
                break;
        }
        
    }
    
}
