package practice.sorting;

public class MergeSort {

	public static void mergeSort(int arr[]) {
		if (arr.length < 2)
			return;
		int mid = arr.length / 2;
		int left[] = new int[mid];
		int right[] = new int[arr.length - mid];
		for (int i = 0; i < left.length; i++) {
			left[i] = arr[i];
		}
		for (int i = 0; i < right.length; i++) {
			right[i] = arr[mid + i];
		}
		mergeSort(left);
		mergeSort(right);
		merge(arr, left, right);
	}

	private static void merge(int arr[], int left[], int right[]) {
		int nl = 0;
		int nr = 0;
		int i = 0;
		while (nl < left.length && nr < right.length) {
			if (left[nl] < right[nr]) {
				arr[i] = left[nl];
				nl++;
			} else {
				arr[i] = right[nr];
				nr++;
			}
			i++;
		}
		while (nl < left.length) {
			arr[i] = left[nl];
			i++;
			nl++;
		}
		while (nr < right.length) {
			arr[i] = right[nr];
			i++;
			nr++;
		}
	}

	private static void printArray(int[] x) {
		for (int i = 0; i < x.length - 1; i++) {
			System.out.print(x[i] + ", ");
		}
		System.out.println(x[x.length - 1]);
		System.out.println();

	}

	public static void main(String[] args) {
		int x[] = { 64, 34, 25, 99, 12,6, 22, 11, 90 };
		printArray(x);
		mergeSort(x);
		printArray(x);
	}

}
