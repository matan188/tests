package tests;

import java.util.Stack;

public class LinkedList {
	
	private ListNode head;
	private int size;
	
	/* LisNode class */
	public class ListNode{
		private ListNode next;
		private Integer val;
		
		public ListNode(Integer val) {
			this.val = val;
			this.next = null;
		}
		
		@Override
		public String toString() {
			return this.val.toString();
		}
		
		public Integer getVal() {
			return this.val;
		}
	}
	
	/**
	 * Constructs the LinkedList with 0 elements.
	 */
	public LinkedList() {
		this.head = null;
		this.size = 0;
	}
	
	/**
	 * Adds a new ListNode with the given value at the index location
	 * @param val
	 * @param index
	 * @return
	 */
	public boolean add(Integer val, int index) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		
		ListNode newNode = new ListNode(val);
		
		if (index == 0) {
			if (this.head == null) {
				this.head = newNode;
			} else {
				newNode.next = this.head;
				this.head = newNode;
			}
		} else {
			int currIndex = 1;
			
			ListNode currNode = this.head;
			while (currIndex != index) {
				currNode = currNode.next;
				currIndex++;
			}
			ListNode nextNode = currNode.next;
			currNode.next = newNode;
			newNode.next = nextNode;
		}
		this.size++;
		return true;
	}
	
	public ListNode getRoot() {
		return this.head;
	}
	
	public ListNode getNthElement (int n) {
		if (this.head == null) return null;
		ListNode curr = head;
		int counter = 0;
		while (curr != null) {
			if(counter++ == n) return curr;
			curr = curr.next;
		}
		return curr;
	}
	
	/**
	 * Automatically adds a new ListNode at the first index
	 * @param val
	 * @return
	 */
	public boolean add(Integer val) {
		return this.add(val, 0);
	}
	
	public boolean addToLast(Integer val) {
		return this.add(val, this.size);
	}
	
	/**
	 * Returns a string representation of the list.
	 */
	@Override
	public String toString() {
		String out = "";
		ListNode currNode = this.head;
		if (this.head == null) {
			return "->";
		}
		while (currNode != null) {
			out += "->" + currNode.getVal().toString();
			currNode = currNode.next;
		}
		
		return out;
	}
	
	/**
	 * Remove the first element found with the given val.
	 * 
	 * @param val
	 * @return true if element's been removed. false if no element with given val was found.
	 */
	public boolean removeByVal(Integer val) {
		ListNode currNode = this.head;
		ListNode prev = null;
		while (currNode != null) {
			if (currNode.getVal().equals(val)) {
				if(currNode == this.head) {
					this.removeFirst();
				} else {
					prev.next = currNode.next;
					currNode.next = null;
				}
				return true;
			}
			prev = currNode;
			currNode = currNode.next;
		}
		
		return false;
	}
	
	/**
	 * Remove the item at the given index.
	 * @param index
	 * @return
	 */
	public boolean removeByInd(int index) {
		if (index < 0 || index >= this.size) {
			return false;
		}
		ListNode currNode = this.head;
		if (index == 0) {
			this.head = this.head.next;
			currNode.next = null;
		} else {
			int currInd = 1;
			// currNode is always the one before the index
			while(currInd != index) {
				currNode = currNode.next;
				currInd++;
			}
			ListNode toRemove = currNode.next;
			currNode.next = toRemove.next;
			toRemove.next = null;
		}
		
		this.size--;
		return true;
	}
	
	/**
	 * @return true if the list is empty, false otherwise 
	 */
	public boolean isEmpty() {
		if (this.size == 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * Removes the first element of the LL. If the List is empty, returns False.
	 * @return
	 */
	public boolean removeFirst() {
		if (this.isEmpty()) {
			return false;
		}
		return this.removeByInd(0);
	}
	
	/**
	 * Reverses the list with one traversal
	 */
	public void reverseListIter() {
		ListNode curr = this.head;
		ListNode prev = null;
		ListNode next = null;
		
		while (curr != null) {
			next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}
		this.head = prev;
	}
	
	public void reverseListWithStack() {
		Stack<ListNode> stack = new Stack<ListNode>();
		ListNode curr = this.head;
		while (curr != null) {
			stack.push(curr);
			curr = curr.next;
		}
		this.head = stack.pop();
		curr = this.head;
		while (!stack.isEmpty()) {
			curr.next = stack.pop();
			curr = curr.next;
		}
		curr.next = null;
	}
	
	/**
	 * Reverses the list recursively in one traversal.
	 * @return
	 */
	public void reverseListRecursive() {
		
		this.reverseHelper(this.head);
	}
	
	/**
	 * 
	 * @param node
	 * @return the next of the node in the current method instance
	 */
	private void reverseHelper(ListNode node) {
		if (node == null) return;
		if (node.next == null) {
			this.head = node;
		} else {
			this.reverseHelper(node.next);
			ListNode prevNode = node.next;
			prevNode.next = node; 
			node.next = null;
		}
	}
	
	
	public void reverseRecTrain() {
		this.tempHelp(this.head);
	}
	
	private ListNode tempHelp(ListNode node) {
		if (node == null) return null;
		if (node.next == null) {
			this.head.next = null;
			this.head = node;
		} else {
			ListNode newPrev = this.tempHelp(node.next);
			newPrev.next = node;
		}
		return node;
	}

public ListNode reverseK(ListNode head, int k) {
		if (head == null) return null;
	    ListNode curr = head;
	    ListNode prev = null;
	    ListNode next = null;	    

	    int counter = 0;
        while (counter < k && curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
            counter++;
        }
        
        if (next != null) {
        	head.next = this.reverseK(next, k);
        }
        this.head = prev;
        return prev;
	}

	public void removeDup() {
		
	}
	
	public ListNode nThElement(int n) {
		if (this.head == null) return null;
		ListNode curr = this.head;
		ListNode nth = this.head;
		int counter = 0;
		while (curr != null) {
			if (++counter > n) nth = nth.next;
			curr = curr.next;
		}
		return nth;
	}
	
	public void deleteNode(ListNode node) {
		ListNode curr = node;
		if(curr == null) return;
		ListNode prev = null;
		while (curr.next != null) {
			prev = curr;
			curr.val = curr.next.val;
			curr = curr.next;
		}
		prev.next = null;
	}
	
	public static LinkedList additionForward(LinkedList first, LinkedList second) {
		LinkedList result = new LinkedList();
		return null;
	}
	
	private static ListNode addForHelper(ListNode node1, ListNode node2) {
		return null;
	}
	
	// TESTS
	public static void main(String[] args) {
		LinkedList ll = new LinkedList();
		ll.addToLast(1);
		ll.addToLast(2);
		ll.addToLast(3);
		ll.addToLast(4);
		ll.addToLast(5);
		ll.addToLast(6);
		ll.addToLast(7);
		ll.addToLast(8);
		
		System.out.println(ll);
		ll.reverseListIter();
		System.out.println(ll);
		ll.reverseListRecursive();
		System.out.println(ll);
		ll.reverseRecTrain();
		System.out.println(ll);
		ll.reverseListWithStack();
		System.out.println(ll);
		
	}
}
