package exceptionhandling;
import java.io.*;
        
public class CheckedExample {
	   public static void main(String[] args) {
		try {
			readfile();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	  
}
	   private static void readfile() throws FileNotFoundException {// deferred
		   FileReader file = new FileReader("data.txt");
	   }
}	   