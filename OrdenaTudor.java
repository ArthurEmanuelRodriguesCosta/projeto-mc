import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class OrdenaTudor<T extends Comparable<T>> {
	
	private void swap(T[] array, int i, int j) {
		T temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	
	public void insertionSort(T[] array, int leftIndex, int rightIndex) {
		for (int j = leftIndex; j <= rightIndex; j++) {
			T key = array[j];
			int i = j - 1;
			while (i >= leftIndex && array[i].compareTo(key) > 0) {
				array[i + 1] = array[i];
				i--;
			}
			array[i + 1] = key;
		}
	}
	
	public void selectionSort(T[] array, int leftIndex, int rightIndex) {
		for (int i = leftIndex; i <= rightIndex; i++) {
			int min = i;
			for (int j = i; j <= rightIndex; j++) {
				if (array[j].compareTo(array[min]) < 0) {
					min = j;
				}
			}
			swap(array, i, min);
		}
	}
	
	public void mergeSort(T[] array, int leftIndex, int rightIndex) {
		if (leftIndex >= rightIndex) 
			return;
		int mid = leftIndex + (rightIndex - leftIndex)/2 ;
		mergeSort(array, mid + 1, rightIndex);
		mergeSort(array, leftIndex, mid);
		mergeSortedArrays(array, leftIndex, rightIndex);
	}
	
	private void mergeSortedArrays(T[] array, int leftIndex, int rightIndex) {
		Comparable[] helper = new Comparable[rightIndex - leftIndex + 1];
		int mid = leftIndex + (rightIndex - leftIndex) / 2;
		int i = leftIndex;
		int j = mid + 1;
		int k = 0;
		
		while (i <= mid && j <= rightIndex) {
			if(array[i].compareTo(array[j]) <= 0) {
				helper[k] = array[i];
				i++;
			} else {
				helper[k] = array[j];
				j++;
			}
			k++;
		}
		
		while (i <= mid) {
			helper[k++] = array[i++]; 
		}
		
		while (j <= rightIndex) {
			helper[k++] = array[j++];
		}
		
		for (int l = 0; l < helper.length; l++) {
			array[leftIndex] = (T) helper[l];
			leftIndex++;
		}
	}
	
	public void quickSort(T[] array, int leftIndex, int rightIndex) {
		int pivot;
		if (leftIndex < rightIndex) {
			pivot = partition(array, leftIndex, rightIndex);
			quickSort(array, pivot + 1, rightIndex);
			quickSort(array, leftIndex, pivot - 1);
		}
	}
	
	private int partition(T[] array, int leftIndex, int rightIndex) {
		T pivot = array[leftIndex];
		int i = leftIndex;
		
		for (int j = i + 1; j <= rightIndex; j++) {
			if (array[j].compareTo(pivot) < 0) {
				i++;
				swap(array, i, j);
			}
		}
		swap(array, leftIndex, i);
		
		return i;
	}
	
	public static void main(String[] args) {			
	    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	    OrdenaTudor<Double> sorter = new OrdenaTudor<Double>();

	    System.out.println("list_id, algorithm, time");

	    String line = null;
	    try {
	        while ((line = in.readLine()) != null) {
	            String[] a = line.split(" ");
				
		    List<Double> l = new ArrayList<>();
		    for (String e : a)
		        l.add(Double.parseDouble(e));
				
		    Double[] unidadeExp = new Double[l.size()];
		    unidadeExp = l.toArray(unidadeExp);
				
		    long startTime = 0;
		    long estimatedTime = 0;
		
		    if (args[0].equals("QuickSort")) {
					startTime = System.nanoTime();
					sorter.quickSort(unidadeExp, 0, unidadeExp.length - 1);
					estimatedTime = System.nanoTime() - startTime;
				} else if (args[0].equals("MergeSort")) {
					startTime = System.nanoTime();
					sorter.mergeSort(unidadeExp, 0, unidadeExp.length - 1);
					estimatedTime = System.nanoTime() - startTime;
				} else if (args[0].equals("InsertionSort")) {
					startTime = System.nanoTime();
					sorter.insertionSort(unidadeExp, 0, unidadeExp.length - 1);
					estimatedTime = System.nanoTime() - startTime;
				} else if (args[0].equals("SelectionSort")) {
					startTime = System.nanoTime();
					sorter.selectionSort(unidadeExp, 0, unidadeExp.length - 1);
					estimatedTime = System.nanoTime() - startTime;
				}
				
			System.out.println(a[0] + "," + args[0] + "," + estimatedTime);
		}
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}
}

