package practice.sorting;

import java.util.Random;

public class QuickSort {
	public static int getRandom(int start, int end) {
		return new Random().nextInt((end-start)+1)+start;
	} 

	private static int partition(int arr[], int start, int end) {
		int pivotIndex =getRandom(start,end); 
		int pivot = arr[pivotIndex];
		int pindex = start;
		swap(arr,pivotIndex,end);
		for (int i = start; i < end; i++) {
			if (arr[i] < pivot) {
				System.out.println(arr[i] +"--"+arr[pindex] );
				swap(arr, i, pindex);
				pindex++;
			}

		}
		swap(arr, pindex, end);
		return pindex;
	}

	private static void quicksort(int arr[], int start, int end) {
		if (start >= end)
			return;
		System.out.println("Start-> "+start +"end-> "+end );
		int p = partition(arr, start, end);
		quicksort(arr, start, p - 1);
		quicksort(arr, p + 1, end);

	}

	private static void swap(int arr[], int x, int y) {
		int temp = arr[x];
		arr[x] = arr[y];
		arr[y] = temp;

	}

	private static void printArray(int[] x) {
		for (int i = 0; i < x.length - 1; i++) {
			System.out.print(x[i] + ", ");
		}
		System.out.println(x[x.length - 1]);
		System.out.println();

	}

	public static void main(String[] args) {
		int x[] = { 64, 34, 25, 99, 12, 22, 11, 90 };
		printArray(x);
		quicksort(x, 0, x.length - 1);
		printArray(x);
	}
}
