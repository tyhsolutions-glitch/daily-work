package List2;
import java.util.*;


public class PriorityQueue {
	public static void main(String[] args) {
		
		PriorityQueue<Integer> pq = new PriorityQueue();
		pq.add(30);
		pq.add(20);
		pq.add(10);
		System.out.println(pq);
		
		System.out.println(pq.poll());
		System.out.println(pq.poll());
 	}

}
