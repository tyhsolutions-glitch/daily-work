package dsafundamentals;

import java.util.ArrayList;

public class StackArray {

    ArrayList<Integer> stack = new ArrayList<>();
    int size = 5;

    void push(int x) {
        if (stack.size() == size) {
            System.out.println("Stack overflow");
        } else {
            stack.add(x);
        }
    }
    int pop() {
        if (stack.size() == 0) {
            System.out.println("Stack underflow");
            return -1;
        }
        return stack.remove(stack.size() - 1);
    }
    public static void main(String[] args) {
        StackArray s = new StackArray();
        s.push(10);
        s.push(20);
        s.push(30);
        System.out.println(s.pop());
        System.out.println(s.pop());
    }
}


