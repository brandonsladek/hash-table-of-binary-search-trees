package csci232.lab2;

public class BinarySearchTree {

	// Nested class to create nodes for the tree
	public class Node {
		
		// Every Node consists of an integer element and most have links to their parent and children nodes
		int element;
		Node parent;
		Node leftChild;
		Node rightChild;
		
		// Get methods
		public int getElement() { return element; }
		public Node getParent() { return parent; }
		public Node getLeftChild() { return leftChild; }
		public Node getRightChild() { return rightChild; }
		
		// Set methods
		public void setElement(int num) { element = num; }
		public void setParent(Node p) { parent = p; }
		public void setLeftChild(Node l) { leftChild = l; }
		public void setRightChild(Node r) { rightChild = r; }
		
		// Constructor for Node 
		public Node(int e, Node p, Node l, Node r) {
			element = e;
			parent = p;
			leftChild = l;
			rightChild = r;
		}
		
	} // End of nested Node class
	
	// Insert the argument node into the tree using root
	public void insertNode(Node node, Node root) { 
		
		Node current = root;
        Node previous = null;
        
        // Node integer value
        int number = node.getElement();

        // Traverse tree to determine where node should be inserted
        while (current != null) {
            previous = current;
            if (number <= current.getElement()) {
                current = current.getLeftChild();
            }
            else {
                current = current.getRightChild();
            }
        }
        
        // Determine whether new node should be leftChild or rightChild
        if (number <= previous.getElement()) {
            previous.setLeftChild(node);
            node.setParent(previous);
        }
        else {
            previous.setRightChild(node);
            node.setParent(previous);
        }
    } // End of insertNode method 
	
	// Print the values in the tree in an order traversal
	public void inorderTraversalPrint(Node root) {
		if (root.getLeftChild() != null) {
			inorderTraversalPrint(root.getLeftChild());
		}
			System.out.print(root.getElement() + " ");
		if (root.getRightChild() != null) {
			inorderTraversalPrint(root.getRightChild());
		}	
	} // End of inorderTraversalPrint method
	
	// Traverse tree to determine if node is present
	public boolean searchTree(Node root) {
		
		boolean found = false;
        Node node = root;
        int number = node.getElement();
        
        while(!found && node != null) {
        	
            if(number == node.getElement()) {
                found = true;
            }
            else if(number < node.getElement()) {
                node = node.getLeftChild();
            }
            else {
                node = node.getRightChild();
            }
        }
        return found;
	} // End of searchTree method
	
} // End of BinarySearchTree class
