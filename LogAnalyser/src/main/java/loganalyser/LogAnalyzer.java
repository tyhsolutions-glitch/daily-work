package loganalyser;

import java.io.*;
import java.util.*;

public class LogAnalyzer {
    public Map<String, Integer> analyze(String fileName) {
        Map<String, Integer> map = new HashMap<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":");
                String type = parts[0];
                map.put(type, map.getOrDefault(type, 0) + 1);
            }
            br.close();
        } catch (Exception e) {
            System.out.println("File not found or error reading file");
        }
        return map;
    }
}
