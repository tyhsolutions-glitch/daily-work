package dsafundamentals;

public class ReverseArray {
	public static void main(String[] args) {
		// int[] arr = {5, 9, 2, 7, 1};
		int[] arr = { 1, 6, 4, 3, 9 };
// int[] arr = {-5, -9, -2, 7, 1};
		int largest = arr[0];
		int secondLargest = arr[0];
		for (int i = arr.length - 1; i >= 0; i--) {
			System.out.print(arr[i] + " ");
			if (arr[i] > largest) {
				secondLargest = largest;
				largest = arr[i];
			} else if (arr[i] > secondLargest && arr[i] != largest) {
				secondLargest = arr[i];
			}
		}
		System.out.println();
		System.out.println("Second Largest: " + secondLargest);
	}
}