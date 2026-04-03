package com.tek.logging;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriteExample {

    public static void main(String[] args) throws IOException {

        FileWriter writer = new FileWriter("test2.txt");
        writer.write("Hello JAVA file IO");
        writer.close();

    }
}
