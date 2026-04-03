package exceptionhandling;

public class ExceptionExample {
    public static void main(String[] args) {
        try {
            int result = 10 / 0;
            System.out.println(result);
        } catch (ArithmeticException arithmeticException) {
            System.out.println("Arithmetic Exception occurred: " + arithmeticException.getMessage());
        } finally {
            System.out.println("Program finished");
        }
    }
}
