package tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class ArraysTraining {
	
	public static void mergeSort(int[] arr) {
		int n = arr.length;
		if (n <= 1) {
			return;
		}
		
		int mid = n/2;
		int[] left = Arrays.copyOfRange(arr, 0, mid);
		int[] right= Arrays.copyOfRange(arr, mid, n);
		mergeSort(left);
		mergeSort(right);
		merge(arr, left, right);
	}
	
	private static void merge(int[] arr, int[] left, int[] right) {
		int k, l, r;
		k = l = r = 0;
		
		while (l < left.length && r < right.length) {
			if (right[r] < left[l]) {
				arr[k] = right[r];
				r++;
			} else {
				arr[k] = left[l];
				l++;
			}
			k++;
		}
		
		while (l < left.length) {
			arr[k] = left[l];
			l++;
			k++;
		}
		while (r < right.length) {
			arr[k] = right[r];
			r++;
			k++;
		}
		
	}
	
	public static void quickSort(int[] arr) {
		quickSortHelper(arr, 0, arr.length);
	}
	
	private static void quickSortHelper(int[] arr, int start, int end) {
		if (start >= end) {
			return;
		}
		
		int pivotIndex = partition(arr, start, end);
		quickSortHelper(arr, start, pivotIndex);
		quickSortHelper(arr, pivotIndex + 1, end);
	}
	
	private static int partition(int[] arr, int start, int end) {
		Random random = new Random();
		int pivotValIndex = random.nextInt(end - start) + start;
		int pivotVal = arr[pivotValIndex];
		swap(arr, pivotValIndex, end-1);
		
		int pivotIndex = start;
		for (int runInd = start; runInd < end - 1; runInd++) {
			if (arr[runInd] <= pivotVal) {
				swap(arr, pivotIndex, runInd);
				pivotIndex++;
			}
		}
		swap(arr, end-1, pivotIndex);
		return pivotIndex;
	}
	
	private static void swap (int[] arr, int indA, int indB) {
		int temp = arr[indA];
		arr[indA] = arr[indB];
		arr[indB] = temp;
	}
	
	public static boolean binarySearch(int[] arr, int val) {
		return bsHelper(arr, val, 0, arr.length);
	}
	
	private static boolean bsHelper(int[] arr, int val, int start, int end) {
		if (start >= end) {
			return false;
		}
		
		int mid = (end - start)/2 + start;
		
		if (val < arr[mid]) {
			return bsHelper(arr, val, start, mid);
		} else if (val > arr[mid]) {
			return bsHelper(arr, val, mid + 1, end);
		} else { 
			return true;
		}
	}
	
	public static void drawParentheses(int n) {
		paraHelper("", n, 0, 0);
	}
	
	private static void paraHelper(String s, int n, int l, int r) {
		
		if (r >= n) {
			System.out.print(s + ", ");
			return;
		}
		
		if (l < n) {
			paraHelper(s + "(", n, l+1, r);
		}
		
		if (r < l) {
			paraHelper(s + ")", n, l, r+1);
		}
	}
	
	public static boolean rotatedSearch(int[] arr, int val) {
		
		return rotatedHelp(arr, val, 0, arr.length);
	}
	
	private static boolean rotatedHelp(int[] arr, int val, int start, int end) {
		int mid = (end-start)/2 + start;
		if (start >= end) {
			return false;
		}
		
		if (arr[mid] == val) {
			return true;
		}
		if (arr[mid] <= arr[end-1]) {
			if (val > arr[mid] && val <= arr[end-1]) {
				return rotatedHelp(arr, val, mid+1, end);
			} else {
				return rotatedHelp(arr, val, start, mid);
			}
		} else {
			if (val >= arr[start] && val < arr[mid]) {
				return rotatedHelp(arr, val, start, mid); 
			} else {
				return rotatedHelp(arr, val, mid+1, end);
			}
		}

	}
	
	public static void main(String[] args) {
		int[] arr = {4,5,6,1,2};
//		quickSort(arr);
//		System.out.println("sorted");
//		for (int num : arr) {
//			System.out.println(num);
//		}
		
		System.out.println(rotatedSearch(arr, 1));
		System.out.println(rotatedSearch(arr, 2));
		System.out.println(rotatedSearch(arr, 3));
		System.out.println(rotatedSearch(arr, 4));
		System.out.println(rotatedSearch(arr, 5));
		System.out.println(rotatedSearch(arr, 6));
		
		
	}
}