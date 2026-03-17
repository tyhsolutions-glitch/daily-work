package dsafundamentals;

import java.util.ArrayList;

public class ReverseStack {
    public static void main(String[] args) {

        String str = "HELLO";
        ArrayList<Character> stack = new ArrayList<>();

        for (int i = 0; i < str.length(); i++) {
            stack.add(str.charAt(i));
        }
        for (int i = stack.size() - 1; i >= 0; i--) {
            System.out.print(stack.get(i));
        }
    }
}
