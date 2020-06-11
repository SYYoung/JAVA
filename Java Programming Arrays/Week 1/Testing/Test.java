
/**
 * Write a description of Test here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Test {
    final int CYCLE = 26;
    public void Test1() {
    StringBuilder sb = new StringBuilder("hello");    
    StringBuilder s2 = sb;
    sb = sb.append(" world");
    System.out.println("sb : " + sb);
    System.out.println("s2 : " + s2);
    boolean status;
    System.out.println("is sb same as s2?" + (s2==sb));
    
    StringBuilder s3 = sb.reverse();
    System.out.println("s3 : " + s3);
    
    String s1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    StringBuilder sb2 = new StringBuilder(s1);
    sb2 = sb2.append(s1);
    int step = 3;
    String s4 = sb2.substring(step,CYCLE+step);
    System.out.println("s4 : " + s4);
    System.out.println("s1 in lower case : " +s1.toLowerCase());
    }
    
    public void test2() {
        int step = 19;
        String inMsg = "A BAT";
        String s = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String orig = new String(s+s);
        String encKey = orig.substring(step, step+CYCLE+1);
        System.out.println("encKey : " + encKey);
        StringBuilder outMsg = new StringBuilder("");
        //char c = orig[2];
        for (int i=0; i<inMsg.length(); i++) {
            char c = inMsg.charAt(i);
            System.out.println("inMsg.charAt " + c);
            if (Character.isLetter(c)) {
                int pos = orig.indexOf(inMsg.charAt(i));
                System.out.println("pos : " + pos);
                outMsg.append(encKey.charAt(pos));
            } 
            else
                outMsg.append(c);
        }
        System.out.println("outMsg : " + outMsg);
    }
}
