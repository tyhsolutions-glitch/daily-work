package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MathTest {
	Math math;
	
	@BeforeEach
	void setup() {//Called before every test method
		math = new Math();//A- arrange
	}
	@Test
	void testAddWithArray() {
		int result = math.addWithArray(new Integer[] {2,3,4,5});//A- Act
		assertEquals(14,result);//A-> Assert
	}
	@Test
	void testDivide() {
		int result = math.divide(25,5);//A- Act
		assertEquals(5,result);//A-> Assert
	}
	@Test
	void testDivideByZzero() {
		assertThrows(ArithmeticException.class,()->{
			math.divide(25, 0);
		});
	}
	@Test
	void testDivideWithNegativeDevisor() {
		int result = math.divide(25,-5);//A- Act
		assertEquals(-5,result);//A-> Assert
	}
	@Test
	void testAdd() {
		int result = math.add(2, 5);//A- Act
		assertEquals(7,result);//A-> Assert
	}
	@Test
	void testAddNegativeNumbers() {
		int result = math.add(-2, -5);//A- Act
		assertEquals(-7,result);//A-> Assert
	}
	@Test
	void testAddNegativeAndPositiveNumbers() {
		int result = math.add(2, -5);//A- Act
		assertEquals(-3,result);//A-> Assert
	}
}
