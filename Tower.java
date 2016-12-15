package tests;
import java.util.Stack;

public class Tower {
	
	private Stack<Integer> disks;
	private int index;
		
	public Tower(int i) {
		this.disks = new Stack<Integer>();
		this.index = i;
	}
	
	public int index() {
		return this.index;
	}
	
	public void add(int d) {
		if (!disks.isEmpty() && disks.peek() <= d) {
			System.out.println("Error adding disk");
		} else {
			disks.push(d);
		}			
	}
	
	public void moveTopTo(Tower t) {
		int top = this.disks.pop();
		t.add(top);
//		System.out.println();
	}
	
	public void print() {
		System.out.println("Content of tower " + this.index());
		for (int i = disks.size()-1; i >= 0; i--) System.out.println(" " + disks.get(i));
	}
	
	public void moveDisks(int n, Tower destination, Tower buffer) {
		if (n > 0) {
			this.moveDisks(n-1, buffer, destination);
			this.moveTopTo(destination);
			buffer.moveDisks(n-1, destination, this);
		}
	}
	
	
	public static void main(String[] args) {
		int n = 5;
		Tower[] towers = new Tower[n];
		for (int i=0; i<3; i++) towers[i] = new Tower(i);
		for (int i=n-1; i>=0; i--) towers[0].add(i);
		towers[0].moveDisks(n, towers[2], towers[1]);
		towers[0].print();
		towers[2].print();
	}
}
