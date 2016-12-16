package tests;

import java.util.Stack;

public class LinkedList {
	
	private ListNode head;
	private int size;
	
	/* LisNode class */
	public static class ListNode{
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
	
	public LinkedList(ListNode head) {
		this.head = head;
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
	
	public boolean addLast(Integer val) {
		return this.add(val, this.size);
	}
	
	public boolean addLast(ListNode node) {
		return this.add(node, this.size);
	}
	
	public boolean add(ListNode node, int index) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		ListNode newNode = node;
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
//			newNode.next = nextNode;
		}
		this.size++;
		return true;
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
	
	public boolean deleteNode(ListNode node) {
		ListNode curr = node;
		if(curr == null || curr.next == null) return false;
		ListNode prev = null;
		while (curr.next != null) {
			prev = curr;
			curr.val = curr.next.val;
			curr = curr.next;
		}
		prev.next = null;
		return true;
	}
	
	public static LinkedList additionBackward(LinkedList first, LinkedList second) {
		ListNode curr1 = first.head;
		ListNode curr2 = second.head;
		LinkedList out = new LinkedList();
		int rem = 0;
		while (curr1 != null && curr2 != null) {
			int sum = curr1.val + curr2.val + rem;
			int nodeVal = sum % 10;
			rem = sum / 10;
			out.addLast(nodeVal);
			curr1 = curr1.next;
			curr2 = curr2.next;
		}
		
		while (curr1 != null) {
			out.addLast(curr1.val + rem);
			rem = 0;
			curr1 = curr1.next;
		}
		
		while (curr2 != null) {
			out.addLast(curr2.val + rem);
			rem = 0;
			curr2 = curr2.next;
		}
		
		if (rem == 1) out.addLast(rem);
		
		return out;
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
		if (this.head == null) return;
		
		ListNode prev = this.head;
		ListNode curr = prev.next;
		while (curr != null) {
			ListNode runner = this.head;
			while (runner != curr) {
				if (runner.getVal() == curr.getVal()) {
					prev.next = curr.next;
					curr.next = null;
					curr = prev;
					break;
				}
				runner = runner.next;
			}
			prev = curr;
			curr = curr.next;
		}
	}
	
	public ListNode findCircleHead() {
		ListNode slow = this.head;
		ListNode fast = this.head;
		
		if (fast.next == null || fast.next.next == null) return null;
		slow = slow.next;
		fast = fast.next.next;
		while (fast != slow && fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		System.out.println(slow.val);
		
		if (fast.next == null || fast.next.next == null) return null;
		
		slow = this.head;
		while (slow != fast) {
			slow = slow.next;
			fast = fast.next;
		}
		return slow;
	}
	
	/**
	 * Removes duplicate nodes with no buffer in O(n^2)
	 * @param node
	 * @return
	 */
	public boolean remove(ListNode node) {
		ListNode curr = this.head;
		ListNode prev = null;
		while (curr != null && curr != node) {
			prev = curr; 
			curr = curr.next;
		}
		
		if (curr == null) return false;
		
		if (prev == null){
			this.head = curr.next;
		} else {
			prev.next = curr.next;
		}
		
		curr.next = null;
		return true;
	}
	
	public ListNode getNode(int val) {
		ListNode curr = this.head;
		
		while (curr != null && curr.val != val) {
			curr = curr.next;
		}
		return curr;
	}
	
	public void reverseKAgain(int k) {
		this.head = this.helpReverseKAgain(this.head, k);
	}
	private ListNode helpReverseKAgain(ListNode head, int k){
		ListNode curr = head;
		ListNode prev = null;
		ListNode next = null;
		int counter = 0;
		while (curr != null && counter < k) {
			counter++;
			next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}
		
		if (curr != null) {
			head.next = this.helpReverseKAgain(curr, k);
		}
		return prev;
	}
	
	// TESTS
	public static void main(String[] args) {
		LinkedList a = new LinkedList();
		a.addLast(1);
		a.addLast(2);
		a.addLast(3);
		a.addLast(4);
		a.addLast(5);
		a.addLast(6);
		a.addLast(7);
		System.out.println(a);
		a.reverseKAgain(3);
		System.out.println(a);
		
	}
}
