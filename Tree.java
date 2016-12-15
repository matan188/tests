package tests;

import java.util.List;
import java.util.Stack;

import org.apache.log4j.chainsaw.Main;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;

public class Tree<E extends Integer/*Comparable<E>*/> {
	
	private class Node<T>{
		private Node<T> left;
		private Node<T> right;
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
		
		return true;
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
        System.out.println("low " + low.val + " high " + high.val);
        
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
	
	public static Tree<Integer> arrToTree(Integer[] arr) {
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
		Integer[] arr = {1,2,3,4,5,6,7};
		Tree<Integer> tree = Tree.arrToTree(arr);
		List<LinkedList<Integer>> ll = t.treeToLL();
		System.out.println(ll);
		
		
		
		
		
	}

}
