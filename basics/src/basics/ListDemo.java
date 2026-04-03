package basics;
import java.util.ArrayList;
import java.util.ListDemo;
public class ListDemo {
	public static void main(String[] args) {
//		manageBooks();
		stringSurprise();
	} 
    private static void stringSurprise() {
    	String s1 = new String("Book1");
    	String s3 = new String("Book1");
    	String s2 = new String("Book1");
    	System.out.println(s1 == s2);	  
    } 
}
