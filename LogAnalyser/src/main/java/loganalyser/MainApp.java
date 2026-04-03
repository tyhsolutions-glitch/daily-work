package loganalyser;
import java.util.*;

public class MainApp {

    public static void main(String[] args) {

        LogAnalyzer analyzer = new LogAnalyzer();
        Map<String, Integer> result = analyzer.analyze("system.log");
        for (Map.Entry<String, Integer> entry : result.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
