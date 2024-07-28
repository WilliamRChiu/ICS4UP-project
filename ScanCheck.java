package Carnival;

public class ScanCheck {//Was not able to implement this code into my game but made the method to show proof of understandingf
    public static boolean IntegerCheck(String s){
        String copy = s;
        s = s.replaceAll("0", "");
        s = s.replaceAll("1", "");
        s = s.replaceAll("2", "");
        s = s.replaceAll("3", "");
        s = s.replaceAll("4", "");
        s = s.replaceAll("5", "");
        s = s.replaceAll("6", "");
        s = s.replaceAll("7", "");
        s = s.replaceAll("8", "");
        s = s.replaceAll("9", "");
        return (s.length()==0);
    }
    
}
