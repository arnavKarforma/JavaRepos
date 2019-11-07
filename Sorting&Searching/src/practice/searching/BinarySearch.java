package practice.searching;

public class BinarySearch {

	private static int search(int arr[], int x, int l, int r) {
		if (r >= l) {
			int mid = l + (r - l) / 2;
			if (x == arr[mid])
				return mid;
			if (x < arr[mid]) {
				return search(arr, x, 0, mid);
			} else {
				return search(arr, x, mid + 1, arr.length);
			}
		}
		return -1;

	}

	public static void main(String[] args) {
		int x[] = { 6, 11, 12, 22, 25, 34, 64, 90, 99 };
		int index = search(x, 64, 0, x.length);
		System.out.println(index);
		if (index >= 0) {
			System.out.println("Found at location " + index);
		}
		System.out.println("Was not found");
	}

}
