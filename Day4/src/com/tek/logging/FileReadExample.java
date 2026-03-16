package com.tek.logging;
import java.io.*;

public class FileReadExample {
	public static void main(String[] args) throws IOException{
		FileReader reader = new FileReader("test2.txt");
		
		int character;
		while((character = reader.read()) != -1) {
			System.out.print((char) character);
		}
		reader.close();
	}

}
