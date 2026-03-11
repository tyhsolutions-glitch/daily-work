package day2;

public class Dog implements Pet{
	private String breed;
	
	public Dog(String breed) {
		this.breed = breed;
	}
	public void sound() {
		System.out.print("dog makes sound");
	}
	@Override
	public void play() {
		System.out.println("playing with Dog");
	}
	
	public void bark() {
		System.out.println("Dog is barking");
	}	
}


