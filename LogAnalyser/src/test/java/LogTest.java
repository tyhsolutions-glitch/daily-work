
import org.junit.jupiter.api.Test;

import loganalyser.LogAnalyzer;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class LogAnalyzerTest {

    @Test
    void testAnalyzeLogs() {
        LogAnalyzer analyzer = new LogAnalyzer();
        Map<String, Integer> result = analyzer.analyze("system.log");
        assertEquals(1, result.get("INFO"));
        assertEquals(1, result.get("WARNING"));
        assertEquals(2, result.get("ERROR"));
    }

    @Test
    void testFileNotFound() {
        LogAnalyzer analyzer = new LogAnalyzer();
        Map<String, Integer> result = analyzer.analyze("wrongfile.log");
        assertTrue(result.isEmpty());
    }

}

