
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
        String fname = "../Testcase/weblog1_log";
        analyzer.readFile(fname);
        switch (testcase) {
            case 1 : 
                analyzer.printAll();
                break;
            case 2:
                System.out.println("Case 2: Number of unique IPs : " 
                        +analyzer.countUniqueIPs());
                break;
            case 3:
                int num = 400;
                analyzer.printAllHigherThanNum(num);
                break;
            case 4:
                String someday = "Sep 30";
                ArrayList<String> uniqueIP = 
                            analyzer.uniqueIPVisitsOnDay(someday);
                System.out.println("Case 4: The unique IPs visit on " +someday +":");
                System.out.println("Total IPs is : " +uniqueIP.size());
                for (String ip : uniqueIP)
                    System.out.println(ip);
                break;
            case 5:
                int low = 200, high = 299;
                System.out.println("Case 5: The number of unique IPs which status "
                        +"code between " +low +"and " +high
                        +" : " + analyzer.countUniqueIPsInRange(low, high));
                break;
            case 6: // test countVisitsPerIP
                HashMap<String, Integer> countPerIP = analyzer.countVisitsPerIP();
                System.out.println("Case 6: " +countPerIP);
                break;
            case 7: // test mostNu,berVisitsByIP
                int mostNumber = analyzer.mostNumberVisitsByIP();
                System.out.println("Case 7: The most number visited by single IP is: "
                        +mostNumber);
                break;
            case 8: // test iPsMostVisits
                ArrayList<String> ipAddr = analyzer.iPsMostVisits();
                System.out.println("Case 8: " +ipAddr);
                break;
            case 9: // test iPsForDays
                HashMap<String, ArrayList<String>> ipForDay = 
                                analyzer.iPsForDays();
                for (String time : ipForDay.keySet()) {
                    System.out.println("Case 9:  on : " +time +"IPs visited : ");
                    System.out.println(ipForDay.get(time));
                }
                break;
            case 10: // test dayWithMostIPVisits
                System.out.println("Case 10: THe most visited date is: "
                            +analyzer.dayWithMostIPVisits());
                break;
            case 11: // test iPsWithMostVisitsOnDay
                String time = "Mar 17";
                ArrayList<String> ipList = analyzer.iPsWithMostVisitsOnDay(time);
                System.out.println(ipList);
                break;
            case 12: // output dayWIthMostIPVisits
                analyzer.testDayWithMostIPVisits();
                break;
        }
        
    }
    
}
