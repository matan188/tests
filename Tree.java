package tests;

import java.util.List;
import java.util.Queue;
import java.util.Stack;

import org.apache.log4j.chainsaw.Main;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;

public class Tree<E extends Integer/*Comparable<E>*/> {
	
	private class Node<T>{
		private Node<T> left;
		private Node<T> right;
		private Node<T> parent;
		private T val;
		
		public Node(T val) {
			this.val = val;
			this.left = null;
			this.right = null;
		}
		
		public T getVal() {
			return this.val;
		}
	}
	
	/* Class members */
	private Node<E> root;
	
	public Tree() {
		this.root = null;
	}
	
	public boolean insert(E val) {
		Node<E> newNode = new Node<E>(val);
		if (this.root == null) {
			this.root = newNode;
			return true;
		}
		
		Node<E> currNode = this.root;
		Node<E> parent = null;
		int comp = 0;
		while(currNode != null) {
			parent = currNode;
			comp = val.compareTo(currNode.getVal());
			if (comp <= 0) {
				currNode = currNode.left;
			} else if (comp > 0) {
				currNode = currNode.right;
			}
		}
		
		if (comp <= 0) {
			parent.left = newNode;
		} else {
			parent.right = newNode;
		}
		newNode.parent = parent;
		
		return true;
	}
	
	public Node<E> search(int val) {
		Node<E> curr = this.root;
		
		while (curr != null) {
			if (val < curr.val) {
				curr = curr.left;
			} else if ( val > curr.val){
				curr = curr.right;
			} else {
				return curr;
			}
		}
		return null;
	}
	
	public Node<E> getNextInOrder(int val) {
		Node<E> node = this.search(val);
		
		if (node == null) return null;
		
		if (node.right != null) {
			Node<E> curr = node.right;
			while (curr.left != null) {
				curr = curr.left;
			}
			return curr;
		}
		
		Node<E> parent = node; 
		while (parent.parent.left != parent && parent.parent == null) {
			parent = parent.parent;
		}
		
		if (parent.parent == null) return null;
		
		return parent.parent;
	}
	
	/**
	 * Print the tree as level by level traversal
	 */
	public void toPrint() {
		List<Node<E>> queue = new LinkedList<Node<E>>();
		queue.add(root);
		List<E> level = new ArrayList<E>();
		level.add(root.getVal());
		while (!queue.isEmpty()) {
			System.out.println(level);
			level.clear();
			int size = queue.size();
			while (size > 0) {
				Node<E> currNode = queue.remove(0);
				if (currNode.left != null) {
					queue.add(currNode.left);
					level.add(currNode.left.getVal());
				}
				if (currNode.right!= null) {
					queue.add(currNode.right);
					level.add(currNode.right.getVal());
				}
				size--;
			}
		}
	}
	
	public Node<E> getParent(E val) {
		if (val == null) {
			return null;
		}
		Node<E> currNode = this.root;
		Node<E> parent = null;
		while (currNode != null) {
			int comp = val.compareTo(currNode.getVal());
			Node<E> tempParent = currNode;
			if (comp < 0) {
				currNode = currNode.left;
			} else if (comp > 0) {
				currNode = currNode.right;
			} else {
				return parent;
			}
			parent = tempParent;
		}
		
		return null;
				
	}
	
	public Node<E> getSuccessor(E val) {
		if (val == null) {
			return null;
		}
		Node<E> currNode = this.root;
		Node<E> lastLeft = null; 
		while (currNode != null) {
			int comp = val.compareTo(currNode.getVal());
			if (comp < 0) { // val < currNode
				lastLeft = currNode;
				currNode = currNode.left;
			} else { 		// val >= currNode
				currNode = currNode.right;
			}
		}
		return lastLeft;	
	}
	
	public Node<E> remove(E val) {
		if (val == null) {
			return null;
		}
		Node<E> currNode = this.root;
		Node<E> parent = null;
		while (currNode != null) {
			Node<E> tempPar = currNode;
			int comp = val.compareTo(currNode.getVal());
			if (comp < 0) {
				currNode = currNode.left;
			} else if (comp > 0) {
				currNode = currNode.right;
			} else {
				break;
			}
			parent = tempPar;
		}
		
		if (currNode.left != null && currNode.right != null) {
			Node<E> replaceNode = this.remove(this.getSuccessor(currNode.getVal()).getVal());
			replaceNode.left = currNode.left;
			replaceNode.right = currNode.right;
			if (parent == null) {
				this.root = replaceNode;
			} else {
				if (parent.left == currNode) {
					parent.left = replaceNode;
				} else {
					parent.right = replaceNode; 
				}
				
			}
		} else if (currNode.left == null) {
			if (parent.left == currNode) {
				parent.left = currNode.right;
			} else {
				parent.right = currNode.right;
			}
		} else if (currNode.right == null) {
			if (parent.left == currNode) {
				parent.left = currNode.left;
			} else {
				parent.right = currNode.left;
			}
		}
		return currNode;
	}
	
	public int twoSum(int k) {
		if (this.root == null) {
            return 0;
        }
        
        Stack<Node<E>> leftPath = this.pathToMin();
        Stack<Node<E>> rightPath = this.pathToMax();
        Node<E> low = leftPath.pop();
        Node<E> high = rightPath.pop();
        
        while (low != high) {
            int sum = low.val + high.val;
            if (sum == k) {
                return 1;
            } else if (sum < k) {
                //low = leftPath.pop();
                if (low.right != null){
                    low = low.right;
                    while (low != null) {
                        leftPath.push(low);
                        low = low.left;
                    }
                }
                low = leftPath.pop();
            } else {
                //high = rightPath.pop();
                if (high.left != null){
                    high = high.left;
                    while (high != null) {
                        rightPath.push(high);
                        high = high.right;
                    }
                }
                high = rightPath.pop();
            }
        }
        
        return 0;
	}
	
	private Stack<Node<E>> pathToMin(){
        Stack<Node<E>> path = new Stack<Node<E>>();
        Node<E>curr = this.root; 
        while(curr != null) {
            path.push(curr);
            curr = curr.left;
        }
        return path;
    }
    
    private Stack<Node<E>> pathToMax(){
        Stack<Node<E>> path = new Stack<Node<E>>();
        Node<E>curr = this.root; 
        while(curr != null) {
            path.push(curr);
            curr = curr.right;
        }
        return path;
    }
	
	public void inOrderwithStack() {
		Node<E> curr = this.root;
		Stack<Node<E>> st = new Stack<Node<E>>();
		
		while (curr != null) {
			st.push(curr);
			curr = curr.left;
		}
		
		while (!st.isEmpty()) {
			curr = st.pop();
			System.out.println(curr.getVal());
			if (curr.right != null) {
				curr = curr.right;
				while(curr != null) {
					st.push(curr);
					curr = curr.left;
				}
			}
		}
	}
	
	
	
	public void levelOrderTraversalSpiral() {
		ArrayDeque<Node<E>> queue = new ArrayDeque<Node<E>>();
		if (root == null) return;
		queue.addFirst(root);
		int levelCount = 1;
		int currentCount = 0;
		Node<E> curr = null;
		boolean ltr = true;
		while (!queue.isEmpty()) {
			if (levelCount == 0) {
				System.out.println("");
				levelCount = currentCount;
				currentCount = 0;
				if (ltr) ltr = false;
				else ltr = true;
			}
			if (ltr) {
				curr = queue.remove();
				System.out.print(curr.getVal() + " ");
				if (curr.left != null) {
					queue.addLast(curr.left);
					currentCount++;
				}
				if (curr.right != null) {
					queue.addLast(curr.right);
					currentCount++;
				}
				levelCount--;
			} else {
				curr = queue.removeLast();
				System.out.print(curr.getVal() + " ");
				if (curr.right != null) {
					queue.addFirst(curr.right);
					currentCount++;
				}
				if (curr.left != null) {
					queue.addFirst(curr.left);
					currentCount++;
				}
				levelCount--;
			}
		}
		System.out.println("");
	}
	
	public boolean isBalanced() {
		return Math.abs(this.maxDepth(this.root) - this.minDepth(root)) <= 1;
	}
	
	private int maxDepth(Node<E> node) {
		if (node == null) return -1;
		return Math.max(this.maxDepth(node.left), this.maxDepth(node.right)) + 1;
	}
	
	private int minDepth(Node<E> node) {
		if (node == null) return -1;
		return Math.min(this.minDepth(node.left), this.minDepth(node.right)) + 1;
	}
	
	public static Tree<Integer> sortedArrToBalTree(Integer[] arr) {
		Tree<Integer> tree = new Tree<Integer>();
		aTTHelper(arr, 0, arr.length - 1, tree);
		return tree;
	}
	
	private static void aTTHelper(Integer[] arr, int start, int end, Tree<Integer> tree) {
		if (start > end) return;
		
		int mid = (end - start)/2 + start;
		tree.insert(arr[mid]);
		aTTHelper(arr, start, mid-1, tree);
		aTTHelper(arr, mid+1, end, tree);
	}
	
	public List<LinkedList<Integer>> treeToLL() {
		LinkedList<Node<Integer>> queue = new LinkedList<Node<Integer>>();
		List<LinkedList<Integer>> outList = new ArrayList<LinkedList<Integer>>();
		Node<Integer> curr = (Tree<E>.Node<Integer>) this.root;
		queue.addLast(curr);
		LinkedList<Integer> currLL = new LinkedList<Integer>();
		currLL.add(curr.getVal());
		while (!queue.isEmpty()) {
			int size = queue.size();
			outList.add(currLL);
			currLL = new LinkedList<Integer>();
			while (size > 0) {
				curr = queue.removeFirst();
				size--;
				if (curr.left != null) {
					queue.addLast(curr.left);
					currLL.addLast(curr.left.getVal());
				}
				if (curr.right != null) {
					queue.addLast(curr.right);
					currLL.addLast(curr.right.getVal());
				}
			}
		}
		
		return outList;
	}
	
	public Node<E> getPredecessor(int val) {
		Node<E> curr = this.root;
		Node<E> pred = null;
		while (curr != null) {
			if (val <= curr.getVal()) {
				curr = curr.left;
			} else {
				pred = curr;
				curr = curr.right;
			}
		}
		return pred;
	}
	
	/**
	 * Morris Traversal. In-order with no recursion and no stack.
	 */
	public void morrisTraversal() {
		Node<E> curr = this.root;
		Node<E> pred;
		
		while (curr != null) {
			if (curr.left == null) {
				System.out.println(curr.getVal());
				curr = curr.right;
			} else {
				pred = curr.left;
				while (pred.right != null && pred.right != curr) {
					pred = pred.right;
				}
				if (pred.right == null) {
					pred.right = curr;
					curr = curr.left;
				} else {
					pred.right = null;
					System.out.println(curr.getVal());
					curr = curr.right;
				}
			}
		}
	}
	
	public String treeToString() {
		if (this.root == null) return "";
		StringBuilder out = new StringBuilder();
		this.tTSHelper(root, out);
		return new String(out);
	}
	
	private void tTSHelper(Node<E> node, StringBuilder out) {
		if (node != null) {
			out.append(node.getVal().toString() + ",");
			this.tTSHelper(node.left, out);
			this.tTSHelper(node.right, out);
		}
	}
	
	public static Tree<Integer> stringToTree(String str) {
		String[] arr = str.split(",");
//		for (String s : arr) System.out.print(s + " ");
		Tree<Integer> tree = new Tree<Integer>();
		for (String val : arr) {
			tree.insert(Integer.parseInt(val));
		}
		return tree;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Tree) {
			return equalsHelper(this.root, ((Tree<E>) obj).root);
		}
		return false;
	}
	
	private boolean equalsHelper(Node<E> a, Node<E> b) {
		if (a == null && b == null) return true;
		if (a != null && b != null) {
			boolean left = this.equalsHelper(a.left, b.left);
			boolean right = this.equalsHelper(a.right, b.right);
			return (a.getVal() == b.getVal()) && right && left;
		}
		return false;
		
	}
	
	public void stackTraversal() {
		Stack<Node<E>> stack = new Stack<Node<E>>();
		Node<E> curr = this.root;
		while (!stack.isEmpty() || curr != null) {
			if (curr != null) {
				stack.push(curr);
				curr = curr.left;
			} else {
				curr = stack.pop();
				System.out.println(curr.val);
				curr = curr.right;
			}
		}
	}
	
	public boolean twoSumTest(int k) {
		Node<E> low = this.root;
		Stack<Node<E>> leftPath = new Stack<Node<E>>();
		while (low.left != null) {
			leftPath.push(low);
			low = low.left;
		}
		
		Node<E> high = this.root;
		Stack<Node<E>> rightPath = new Stack<Node<E>>();
		while (high.right != null) {
			rightPath.push(high);
			high = high.right;
		}
		
		while (low != high) {
			int sum = low.getVal() + high.getVal();  
			if (sum == k) {
				return true;
			} else if (sum < k) {
				if (low.right != null) {
					low = low.right;
					while(low.left != null) {
						leftPath.push(low);
						low = low.left;
					}
				} else {
					low = leftPath.pop();
				}
			} else {
				if (high.left != null) {
					high = high.left;
					while (high.right != null) {
						rightPath.push(high);
						high = high.right;
					}
				} else {
					high = rightPath.pop();
				}
			}
		}
		return false;
	}
	
	public void findSumPath(int sum) {
		ArrayList<Integer> buffer = new ArrayList<Integer>();
		this.findSumPathHelp(this.root, 5, buffer, 0);
	}
	
	private void findSumPathHelp(Node<E> head, int sum, ArrayList<Integer> buffer, int level) {
		if (head == null) return;
		int tmp = sum; 
		buffer.add(head.getVal());
		for (int i = level; i >=1 ; i--) {
			tmp -= buffer.get(i);
			if (tmp == 0) this.toPrint();
		}
		ArrayList<Integer> c1 = (ArrayList<Integer>) buffer.clone();
		ArrayList<Integer> c2 = (ArrayList<Integer>) buffer.clone();
		this.findSumPathHelp(head.left, sum, c1, level+1);
		this.findSumPathHelp(head.left, sum, c2, level+1);
	}
	
	private void print(ArrayList<Integer> buffer, int level, int i2) {
		for (int i = level; i < i2; i++) {
			System.out.println(buffer.get(i) + " ");
		}
		System.out.println();
	}
	
	public void levelSpiralTest() {
		LinkedList<Node<E>> LL = new LinkedList<Node<E>>();
		LL.add(this.root);
		boolean ltr = true;
		while (!LL.isEmpty()) {
			int size = LL.size();
			while (size > 0) {
				size--;
				Node<E> curr = null;
				if (ltr) {
					curr = LL.removeLast();
					if (curr.left != null) {
						LL.addFirst(curr.left);
					}
					if (curr.right != null) {
						LL.addFirst(curr.right);
					}
				} else {
					curr= LL.removeFirst();
					if (curr.right != null) {
						LL.addLast(curr.right);
					}
					if (curr.left != null) {
						LL.addLast(curr.left);
					}
				}
				System.out.print(curr.val + " ");
			}
			System.out.println();
			if (ltr) ltr = false;
			else ltr = true;
		}
	}
	
	public void insertBinaryTree(E val) {
		if (this.root == null) {
			this.root = new Node<E>(val);
			return;
		}
		Queue<Node<E>> queue = new LinkedList<Node<E>>();
		queue.add(this.root);
		while (!queue.isEmpty()) {
			Node<E> curr = queue.poll();
			if (curr.left == null) {
				curr.left = new Node<E>(val);
				return;
			}
			if (curr.right == null) {
				curr.right = new Node<E>(val);
				return;
			}
			queue.add(curr.left);
			queue.add(curr.right);
		}
		
	}
	
	public void levelOrder() {
		if (this.root == null) return;
		Queue<Node<E>> queue = new LinkedList<Node<E>>();
		queue.add(this.root);
		while (!queue.isEmpty()) {
			int size = queue.size();
			while (size > 0) {
				Node<E> curr = queue.poll();
				size--;
				System.out.print(curr.val + " ");
				if (curr.left != null) {
					queue.add(curr.left);
				}
				if (curr.right != null) {
					queue.add(curr.right);
				}
			}
			System.out.println();
		}
	}
		
	public static void main(String[] args) {
		Tree<Integer> t = new Tree<Integer>();
		t.insert(10);
		t.insert(9);
		t.insert(20);
		t.insert(25);
		t.insert(15);
		t.insert(5);
		t.insert(2);
		t.insert(7);
		
		
		t.levelOrder();
//		t.levelSpiralTest();
//		t.levelOrderTraversalSpiral();
				
	}

}
