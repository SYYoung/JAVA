import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Put code here
        int totalSide = 0;
        for (Point pt : s.getPoints()) {
          totalSide ++;  
        }
        return totalSide;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        // 1. get number of points
        int totalSide = getNumPoints(s);
        double totalLen = 0;
        Point prevPt = s.getLastPoint();
        for (Point curPt : s.getPoints()) {
            totalLen +=  prevPt.distance(curPt);  
            prevPt = curPt;
        }
        return totalLen/totalSide;
    }

    public double getLargestSide(Shape s) {
        // Put code here
        
        int totalSide = getNumPoints(s);
        double longestSide = 0;
        double curSide = 0;
        Point prevPt = s.getLastPoint();
        for (Point curPt : s.getPoints()) {
            curSide = prevPt.distance(curPt);
            if (curSide > longestSide) {
                longestSide = curSide;
            }
            
            prevPt = curPt;
        }
        return longestSide;
    }

    public double getLargestX(Shape s) {
        // Put code here
        int totalSide = getNumPoints(s);
        double curX, largestX = -10000000;
        Point prevPt = s.getLastPoint();
        for (Point curPt : s.getPoints()) {
            curX = curPt.getX();
            if (curX > largestX) {
                largestX = curX;
            }
        }
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        DirectoryResource dr = new DirectoryResource();
        double curPeri, largestPeri = 0;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            System.out.println(f);
            Shape sh = new Shape(fr);
            curPeri = getPerimeter(sh);
            if (curPeri > largestPeri) {
                largestPeri = curPeri;   
            }
        }
        return largestPeri;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        DirectoryResource dr = new DirectoryResource();
        double curPeri, largestPeri = 0;
        File whichFile = null;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            System.out.println(f);
            Shape sh = new Shape(fr);
            curPeri = getPerimeter(sh);
            if (curPeri > largestPeri) {
                largestPeri = curPeri;  
                whichFile = f;
            }
        }
        return whichFile.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double largestX = getLargestX(s);
        System.out.println("Test: getLargestX(), longest X = " + largestX);
        double avgLen = getAverageLength(s);
        System.out.println("Test: getAverageLength(), avg len = " +  avgLen);
        double largestSide = getLargestSide(s);
        System.out.println("Test: getLargestSide(), largest side = " +  largestSide);
        //double length = getPerimeter(s);
        //System.out.println("perimeter = " + length);
    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
        //printFileNames();
        double largestPeri = getLargestPerimeterMultipleFiles();
        System.out.println("Test: getLargestPerimeterMultipleFiles(), largestPeri = " + 
            largestPeri);
        String fileName = new String();
        fileName = getFileWithLargestPerimeter();
        System.out.println("Test: getFileWithLargestPerimeter(), file name is: " + fileName);
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
        pr.testPerimeterMultipleFiles();
    }
}
