package day5;
@FunctionalInterface //annotation
interface Animal{
	Object eat = null;

	void eat();
//	void run();
}
class Cat implements Animal{
	@Override
	public void eat() {
		System.out.println("Animal eat inside class");
	}
}
public class FunctionalInterfaceDemo{
	public static void main(String[] args) {
		oopWay();
		functional();
	}

	private static void oopWay() {
		// TODO Auto-generated method stub
		
	}

	public static void oopWay {
		Animal animal = new Cat();
		  Object eat = animal.eat;
	}
	private static void functional() {
		Animal animal = ()->{  //inline lambda expression or function
			System.out.println("Animal eats in lambda");
		};
		animal.eat();
	}
}