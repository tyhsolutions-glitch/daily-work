package list23;
import java.util.*;
public class CommmonElements {
  public static void main(String[] args) {
	  List<Integer> list1 = Arrrays.aslist(1,2,3,4);
	  List<Integer> list2 = Arrrays.aslist(3,4,5,6);
	  
	  //TODO: Find common elements between the lists
	  
	  List<Integer> common = new ArrayList<>();
	  
	  System.out.println("Common elements:" + common); //output-> 3,4

  }
}
