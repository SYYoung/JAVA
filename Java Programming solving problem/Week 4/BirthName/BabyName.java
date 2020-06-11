import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;

/**
 * Write a description of BabyName here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BabyName {
    String filePrefix = "../us_babynames_by_year/yob";
    String fileSuffix = ".csv";
    //String filePrefix = "../us_babynames_test/yob" ;
    //String fileSuffix = "short.csv";
    
    public void totalBirths(CSVParser parser) {
        int totalBoy = 0, totalGirl = 0;
        for (CSVRecord rec : parser) {
            if (rec.get(1).equals("F"))
                totalGirl++;
            else
                totalBoy++;
        }
        System.out.println("Number of girls names : " + totalGirl);
        System.out.println("Number of boys names : " + totalBoy);
        System.out.println("Total names in the file : " + (totalGirl+totalBoy));
    }
    
    public int totalBirthsInYear(int year, int rank, String gender) {
        String fname = this.filePrefix + year + this.fileSuffix;
        int total = 0, sofar = 1;
        FileResource fr = new FileResource(fname);
        for (CSVRecord rec : fr.getCSVParser()) {
            if (rec.get(1).equals(gender)) {
                if (sofar < rank) {
                    total = total + Integer.parseInt(rec.get(2));
                    sofar++;
                }
                else
                    break;
            }
        }
        return total;
    }
    
    public int getRank(int year, String name, String gender) {
        //String fname = "../us_babynames_by_year/yob" + year +".csv";
        //String fname = "../us_babynames_test/yob" + year +"short.csv";
        String fname = this.filePrefix + year + this.fileSuffix;
        System.out.println(fname);
        FileResource fr = new FileResource(fname);
        int rank = 1;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if ((rec.get(0).equals(name)) && (rec.get(1).equals(gender))) {
                return rank;
            }
            else if (rec.get(1).equals(gender))
                rank++;
        }
        return -1;
    }
    
    public String getName(int year, int rank, String gender) {
        //String fname = "../us_babynames_by_year/yob" + year +".csv";
        //String fname = "../us_babynames_test/yob" + year +"short.csv";
        String fname = this.filePrefix + year + this.fileSuffix;
        System.out.println(fname);
        FileResource fr = new FileResource(fname);
        int curRank = 1;
        String name = "";
        
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {
                if (curRank == rank)
                    return rec.get(0);
                else
                    curRank++;
            }
            
        }
        return "NO NAME";
    }
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender) {
        int rank = getRank(year, name, gender);
        String pronoun = "she";
        if (rank == -1)
            System.out.println(name +" born in " +year +": no such rank.");
        else {
            if (gender.equals("M"))
                pronoun = "he";
            String newName = getName(newYear, rank, gender);
            System.out.println(name +" born in " +year +" would be "
                            +newName +" if " +pronoun +" was born in " +newYear);
        }
    }
    
    public int whichYear(String fname) {
        String filePrefix = "yob";
        int yearLen = 4;
        int cur = fname.indexOf(filePrefix) + filePrefix.length();
        String yearStr = fname.substring(cur, cur+yearLen);
        return Integer.parseInt(yearStr);
    }
    
    public int getHigherRank(int cur, int highest) {
        if (highest == -1)
            return cur;
        else if (cur == -1)
            return highest;
        else {
            return Math.min(cur, highest);
        }
    }
    
    public int yearOfHighestRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        int highestRank = -1, curRank;
        int highestRankYear = 0;
        for (File f: dr.selectedFiles()) {
            String fname = f.getName();
            int year = whichYear(fname);
            System.out.println("the extracted year = " + year);
            curRank = getRank(year, name, gender);
            highestRank = getHigherRank(curRank, highestRank);
            if (highestRank == curRank)
                highestRankYear = year;
        }
        return highestRankYear;
    }
    
    public double getAverageRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        int totalRank = 0, curRank, totalYear=0;
        
        for (File f: dr.selectedFiles()) {
            String fname = f.getName();
            int year = whichYear(fname);
            System.out.println("the extracted year = " + year);
            curRank = getRank(year, name, gender);
            if (curRank == -1)
                return -1;
            else {
                totalRank += curRank;
                totalYear++;
            }
        }
        return (double)totalRank/totalYear;    
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        int totalBirthsHigherRank = 0;
        int theRank = getRank(year, name, gender);
        System.out.println("The rank is: " +theRank);
        if (theRank == -1)
            return -1;
        else {
            totalBirthsHigherRank = totalBirthsInYear(year, theRank, gender);
        }
        return totalBirthsHigherRank;
    }
    
    public void testGetTotalBirthsRankedHigher() {
        int year = 1990;
        String name = "Drew", gender = "M";
        int ans = getTotalBirthsRankedHigher(year, name, gender);
        System.out.println("In year "+year +", there have " +
                            ans +" ranker than " +name);
    }
    
    public void testGetAverageRank() {
        String name = "Robert", gender = "M";
        double avgRank = getAverageRank(name, gender);
        System.out.println("The average ranking of :" +name + " was : " +avgRank);
    }
    
    public void testYearOfHighestRank() {
        String name = "Mich", gender = "M";
        int year = yearOfHighestRank(name, gender);
        System.out.println("The highest ranking of name: " +name +" : was in " + year);
    }
    
    public void testWhatIsNameInYear() {
        whatIsNameInYear("Susan", 1972, 2014, "F");
    }
   
    public void testGetRank() {
        int year = 1971;
        String name = "Frank", gender = "M";
        int rank = getRank(year, name, gender);
        System.out.println("In year " +year +", " +name +" rank: " +rank);
    }
    
    public void testGetName() {
        int year = 1982;
        String gender = "M";
        int rank = 450;
        System.out.println("In year " +year +", " +gender +" , rank = " +rank
                            +" is : " +getName(year, rank, gender));
    }
    
    public void testTotalBirths() {
        FileResource fr = new FileResource();
        totalBirths(fr.getCSVParser(false));
    }
}
