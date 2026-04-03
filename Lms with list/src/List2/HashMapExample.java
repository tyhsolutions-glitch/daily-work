package List2;

import java.util.HashMap;

public class HashMapExample { 
    public static void main(String[] args) {
        HashMap<Integer, String> map = new HashMap<>(); 
        
        map.put(1, "Java");
        map.put(2, "Python");
        map.put(3, "Go");
        
        System.out.println(map);
        System.out.println(map.get(2));
    }
}
