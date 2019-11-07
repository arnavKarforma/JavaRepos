package practice.sorting;

public class BubbleSort {

	private static void sort(int arr[]) {
		for (int i = 0; i < arr.length; i++) {
			boolean sorted = true;
			for (int j = 0; j < arr.length - i-1; j++) {
				if (arr[j] > arr[j + 1]) {
					int temp = arr[j + 1];
					arr[j + 1] = arr[j];
					arr[j] = temp;
					sorted = false;
				}
			}
			if (sorted == true)
				break;
		}
	}

	private static void printArray(int[] x) {
		for (int i = 0; i < x.length-1; i++) {
			System.out.print(x[i] + ", ");
		}
		System.out.println(x[x.length-1]);
		System.out.println();

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int x[] = { 64, 34, 25, 99, 12, 22, 11, 90 };
		printArray(x);
		sort(x);
		printArray(x);

	}

}
