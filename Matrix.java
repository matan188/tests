package tests;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Matrix<T> {
	
	private int rows; 
	private int cols; 
	private List<ArrayList<T>> adata;
	
	public Matrix(int rows, int cols, T[] data) {
		this.rows = rows;
		this.cols = cols;
		this.adata = new ArrayList<ArrayList<T>>();
		ArrayList<T> x = new ArrayList<T>();
		for(int i=0; i < data.length; i++) {
			int col = i%cols;
			x.add(data[i]);
			if(col == cols - 1) {
				this.adata.add(x);
				x = new ArrayList<T>();
			}
		}
	}
	
	public void printOut() {
		for(int ind=0; ind < rows; ind++) {
			System.out.println(this.adata.get(ind));
		}
	}
	
	
	public static void main(String[] args) {
		String data[] = {"ine", "two", "three", "four", "five", "six"};
		Matrix<String> m = new Matrix<String>(2,3, data);
		m.printOut();
		HashMap<ArrayList<String>, Integer> map = new HashMap<>();
		ArrayList<String> as = new ArrayList<String>();
		as.add("w1");
		as.add("w2");
		map.put(as, 2);
		System.out.println(map);
		as.add("w3");
		System.out.println(map);
		System.out.println(as.get(1));
	}

}
