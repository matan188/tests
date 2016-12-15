package tests;

import java.util.EmptyStackException;
import java.util.Stack;

public class MyQueue {
	private Stack<Integer> outStack;
	private Stack<Integer> inStack;
	
	public MyQueue() {
		this.inStack = new Stack<Integer>();
		this.outStack = new Stack<Integer>();
	}
	
	public void enqueue(int val) {
		this.inStack.push(val);
	}
	
	public int dequeue() {
		if (this.outStack.isEmpty()) {
			if (this.inStack.isEmpty()) {
				throw new EmptyStackException();
			} else {
				while (!this.inStack.isEmpty()) {
					this.outStack.push(this.inStack.pop());
				}
			}
		}
		return this.outStack.pop();
	}
	
	public void print() {
		System.out.println(this.outStack);
		System.out.println(this.inStack);
	}
	
	public int size() {
		return this.inStack.size() + this.outStack.size();
	}
	
	public static void main(String[] args) {
		MyQueue q = new MyQueue();
		q.enqueue(5);
		q.enqueue(3);
		System.out.println(q.size());
		q.dequeue();
		q.enqueue(10);
		q.enqueue(7);
		System.out.println(q.size());
		System.out.println(q.dequeue());
		q.dequeue();
		q.dequeue();
	}

}
