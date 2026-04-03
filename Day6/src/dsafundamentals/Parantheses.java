package dsafundamentals;

import java.util.ArrayList;

public class Parantheses {
    public static void main(String[] args) {

        String str = "(())";
        ArrayList<Character> stack = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            if (ch == '(') {
                stack.add(ch); 
            } else if (ch == ')') {
                if (stack.size() == 0) {
                    System.out.println("Not Balanced");
                    return;
                }
                stack.remove(stack.size() - 1); 
            }
        }
        if (stack.size() == 0)
            System.out.println("Balanced");
        else
            System.out.println("Not Balanced");
    }
}

