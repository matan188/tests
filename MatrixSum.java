package tests;

public class MatrixSum {
	private int[][] matrix; 
	private int[][] sumMatrix;
	private int row_num;
	private int col_num;
	
	public MatrixSum(int row_num, int col_num) {
		// TODO Auto-generated constructor stub
		this.matrix = new int[row_num][col_num];
		this.sumMatrix = new int[row_num][col_num];
		this.row_num = row_num;
		this.col_num = col_num;
	}
	
	public void setVal(int row, int col, int val) {
		if (row >= this.row_num || col >= this.col_num) throw new IndexOutOfBoundsException();
		this.matrix[row][col] = val;
		
		int diff = 0;
		if (row == 0 && col == 0) diff = val - this.sumMatrix[row][col];
		else if (row == 0) diff = val - (this.sumMatrix[row][col] - this.sumMatrix[row][col - 1]);
		else if (col == 0) diff = val - (this.sumMatrix[row][col] - this.sumMatrix[row-1][col]);
		else{
			int prevVal = this.sumMatrix[row][col] - this.sumMatrix[row-1][col] - this.sumMatrix[row][col-1] + this.sumMatrix[row-1][col-1];
			diff = val - prevVal;
		}
		for (int i = row; i < this.row_num; i++) {
			for (int j = col; j < this.col_num; j++) {
				this.sumMatrix[i][j] += diff;
			}
		}
	}
	
	public int getSum(int row, int col) {
		if (row >= this.row_num || col >= this.col_num) throw new IndexOutOfBoundsException();
		int sum = 0;
		for (int rowInd=0; rowInd <= row; rowInd++) {
			for (int colInd=0; colInd <= col;colInd++) {
				sum += this.matrix[rowInd][colInd];
			}
		}
		return sum;
	}
	
	public int getSumFromSum(int row, int col) {
		if (row >= this.row_num || col >= this.col_num) throw new IndexOutOfBoundsException();
		return this.sumMatrix[row][col];
	}
	
	public void toPrint() {
		for (int i=0; i < this.row_num; i++) {
			System.out.print("[");
			for (int j=0; j < this.col_num; j++) {
				System.out.print(this.matrix[i][j] + " ");
			}
			System.out.println("]");
		}
		System.out.println("###");
		for (int i=0; i < this.row_num; i++) {
			System.out.print("[");
			for (int j=0; j < this.col_num; j++) {
				System.out.print(this.sumMatrix[i][j] + " ");
			}
			System.out.println("]");
		}		
	}
	
	public static void main(String[] args) {
		MatrixSum m = new MatrixSum(3, 4);
		m.setVal(2, 2, 4);
		m.setVal(0, 1, 2);
		m.setVal(1, 1, 3);
		m.setVal(0, 0, 1);
		m.setVal(1, 0, 2);
		m.setVal(2, 3, 1);
		m.toPrint();
		System.out.println(m.getSum(1, 1));
		m.setVal(1, 1, 1);
		System.out.println();
		m.toPrint();
	}

}
