package dsafundamentals;

public class ReverseArrayTS {
	public static void main(String[] args) {

		int[] arr = { 5, 9, 2, 7, 1 };
//        int[] arr = {1, 6, 4, 3, 9};
//        int[] arr = {-5, -9, -2, -7, -1};

		long startTime = System.currentTimeMillis();
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
		long endTime = System.currentTimeMillis();
		System.out.println();
		System.out.println("Second Largest: " + secondLargest);
		System.out.println("Time taken: " + (endTime - startTime) + " ms");

		System.out.println("Space used (approx): " + arr.length * 4 + " bytes");
	}
}
