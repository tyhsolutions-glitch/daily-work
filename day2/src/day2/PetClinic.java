package day2;

import java.util.ArrayList;
import java.util.List;

public class PetClinic {
	public static void main(String[] args) {
//	demo1();
	methodOverriding();
	}
	private static void metodOverriding() {
		
		Pet pet = new Dog("Husky");
		Dog dog = new Dog("Husky");
		Pet dog1 = new Dog("Husky");
		Cat cat = new Cat();
//		dog1.back();
		dog.bark();
		List<Pet> pets = new ArrayList<Pet>();
		pets.add(dog);
		pets.add(dog1);
		pets.add(cat);
		pets.forEach((pet)->pet.play());
	}

	private static void methodOverriding() {
		// TODO Auto-generated method stub
		
	}

}
