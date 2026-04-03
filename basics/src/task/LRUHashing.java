package task;
import java.util.LinkedHashMap;
import java.util.Map;
class LRUHashing<K,V> extends LinkedHashMap<K,V> {
	private static final long serialVersionUID = 1L;
	private int capacity;
    LRUHashing(int capacity) {
        super(capacity,0.75f,true);
        this.capacity = capacity;
    }
    protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {
        return size() > capacity;
    }
}
