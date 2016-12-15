package tests;

import java.util.EmptyStackException;
import java.util.Stack;

public class MyStack {
	
	private Stack<Integer> stack;
	private Stack<Integer> minsStack;
	
	public MyStack() {
		this.stack = new Stack<Integer>();
		this.minsStack = new Stack<Integer>();
	}
	
	public void add(int val) {
		this.stack.add(val);
		if (this.minsStack.isEmpty()) {
			this.minsStack.add(val);
		} else {
			if (val < this.minsStack.peek()) {
				this.minsStack.add(val);
			} else {
				this.minsStack.add(this.minsStack.peek());
			}
			
		}
	}
	
	public int getMin() {
		if (this.minsStack.isEmpty()) throw new EmptyStackException();
		return this.minsStack.peek();
	}
	
	public int pop() {
		this.minsStack.pop();
		return this.stack.pop();
	}
	
	public static Stack<Integer> sortStack(Stack<Integer> stack) {
		Stack<Integer> helper = new Stack<Integer>();
		
		while (!stack.isEmpty()) {
			int val = stack.pop();
			while (!helper.isEmpty() && helper.peek() > val) {
				stack.push(helper.pop());
			}
			helper.push(val);
		}
		return helper;
	}
	
	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(5);
		stack.push(3);
		stack.push(6);
		stack.push(1);
		System.out.println(sortStack(stack));
		
	}

}
