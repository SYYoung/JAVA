
/**
 * Write a description of GrayImage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;

public class GrayImage {
    
    public ImageResource grayConversion(ImageResource inImage) {
        ImageResource outImage = new ImageResource(inImage.getWidth(), 
                                inImage.getHeight());
        for (Pixel p : outImage.pixels()) {
            Pixel inPixel = inImage.getPixel(p.getX(), p.getY());
            int avg = (inPixel.getRed() + inPixel.getGreen() + inPixel.getBlue())/3;
            p.setRed(avg); p.setGreen(avg); p.setBlue(avg);
        }
        return outImage;
    }
    
    public void saveNewFile(File f, ImageResource img, String prefix) {
        String fname = f.getName();
        img.setFileName(prefix+fname);
        img.save();
    }
    
    public ImageResource makeInversion(ImageResource inImage) {
        ImageResource outImage = new ImageResource(inImage.getWidth(), 
                                inImage.getHeight());
        for (Pixel p : outImage.pixels()) {
            Pixel inPixel = inImage.getPixel(p.getX(), p.getY());
            p.setRed(255 - inPixel.getRed()); 
            p.setGreen(255 - inPixel.getGreen()); 
            p.setBlue(255 - inPixel.getBlue());
        }
        return outImage;    
    }
    
    public void testImageConversion() {
        String testGray = "testGray", testInversion = "testInversion";
        String testMethod = testGray;
        String prefix = "";
        ImageResource outImage = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()) {
            ImageResource inImage = new ImageResource(f);
            if (testMethod.equals(testGray)) {
                outImage = grayConversion(inImage);
                prefix = "gray-";
            }
            else if (testMethod.equals(testInversion)) {
                outImage = makeInversion(inImage);
                prefix = "inverted-";
            }
            inImage.draw();
            outImage.draw();
            saveNewFile(f, outImage, prefix);
        }
    }
}
