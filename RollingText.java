package Carnival;
import java.util.concurrent.TimeUnit;

public class RollingText {
    public static void print(String s){//this is used to roll through text and not hit enter after the text is written
        for(char c : s.toCharArray()){
            try{
                System.out.print(c);
                TimeUnit.MILLISECONDS.sleep(20);
            }
            catch(Exception e){}
        }
    }
    public static void println(String s){//this is used to roll through text and then hit enter after the text is complete
        print(s);//this recalls the previous method for rolling text
        System.out.println();
    }

    
}
