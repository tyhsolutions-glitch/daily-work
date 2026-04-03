package com.tek.logging;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.logging.Logger;

public class FileLoggerExample {
    private static final Logger logger = Logger.getLogger(FileLoggerExample.class.getName());

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("test2.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                logger.info(line);
            }
        } catch (Exception e) {
            logger.severe("Error reading file");
        }
    }
}
