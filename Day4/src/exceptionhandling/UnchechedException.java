package exceptionhandling;

public class UnchechedException {
	public static void main(String[] args) {
//		String str = null;
//		System.out.println(str.length());
		try {
			validateAge(17);
		} catch (InvalidAgeException e ) {// don't catch unchecked exceptions
			e.printStackTrace();
		}
		System.out.println("Finished");
		
	}

	private static void validateAge(int age) {
		if(age<18){
			throw new InvalidAgeException("Age must be 18+");
		}
	}
}
