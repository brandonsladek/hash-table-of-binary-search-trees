package csci232.lab2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

import csci232.lab2.BinarySearchTree.Node;

public class HashTable {

	static Node nodeAtIndex;
	static Node[] hashTable;
	static int sizeHashTable;
	static int numInsertedElements;
	static String elementsOfHashTable = "";
	static BinarySearchTree bst = new BinarySearchTree();
	
	// Calculate the index where the element should be inserted into the hash table
	public static int hashFunction(int numToInsert) {
		int index;
		index = numToInsert % (sizeHashTable);
		return index;
	} // End of hashFunction method
	
	// Insert an element into the hash table
	public static void insertElement(int numToInsert) {
		
		// Use hash function to find index
		int index = hashFunction(numToInsert);
		nodeAtIndex = hashTable[index];
		
		Node newNode = bst.new Node(numToInsert, null, null, null);
		
		// If there is no collision
		if (hashTable[index] == null) {
			hashTable[index] = newNode;
		}  
		// If there is a collision, use the nodeAtIndex as root to insert the node into the binary search tree
		else {
			bst.insertNode(newNode, nodeAtIndex);
		}
	} // End of insertElement method
	
	// Main method
	public static void main(String[] args) {
		
		int numToInsert;
		String input = null;
		
		BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
		HashMap<Integer, Integer> insertedElements = new HashMap<Integer, Integer>();
		
		System.out.println("Welcome to the HashBST program.");
		System.out.println();
		System.out.print("Enter the integer size of the hash table you would like to work with >>> ");
		
		// Begin reading in user input
		try {
			input = buffer.readLine();
			
			// If input is an integer
			if (isInt(input)) {
				sizeHashTable = Integer.parseInt(input);
				hashTable = new Node[sizeHashTable];
				System.out.println();
				System.out.println("A hash table of size " + sizeHashTable + " has been created.");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Repeatedly ask the user to enter an integer into the hash table, quit if user enters q
		while(!input.equals("q")) {
		
			System.out.println();
			System.out.println("To insert an integer into the hash table, enter an integer.");
			System.out.println("To quit entering values into the hash table, enter q");
		
			// Get user input
			
			System.out.println();
			
			try {
				input = buffer.readLine();
				
				if (isInt(input)) {
					numToInsert = Integer.parseInt(input);
					
					// Insert the integer into the hashTable if it hasn't already been entered
					if (!insertedElements.containsKey(numToInsert)) {
						
						insertElement(numToInsert);
						insertedElements.put(numToInsert, 1);
						numInsertedElements++;
						
						System.out.println();
						System.out.println("The number " + numToInsert + " has been added to the hash table.");
						System.out.println();
					} else {
						System.out.println(numToInsert + " has already been inserted into the hash table!");
					}
			
				} else {
					if (!input.equals("q"))
						System.out.println("Value has already been entered or is invalid.");
					else
						input = "q";
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			} // End of try-catch statement
			
		} // End while loop
		
		System.out.println();
		System.out.println("Now you can search and display the hash table.");
		System.out.println("To quit, enter q");
		
		String quit = "continue";
		
		while(quit.equals("continue")) {
		
		System.out.println("If you would like to check if an integer was inserted into the hash table, enter a number.");
		
		// Read in user input
		try {
			input = buffer.readLine();
			
			if (isInt(input)) {
				
				int number = Integer.parseInt(input);
				
				if (searchHashTable(number)) {
					System.out.println();
					System.out.println(number + " is in the hash table!");
					System.out.println();
				} else {
					System.out.println(number + " is NOT in the hash table!");
				}
			} else {
				System.out.println("Inavlid entry.");
			}
					
		} catch (IOException e1) {
			e1.printStackTrace();
		} // End of try-catch statement
		
		System.out.println("Would you like to dislay an inorder traversal of every tree in the hash table? (yes/no)");
		
		try {
			input = buffer.readLine();
			
			if (input.equals("yes")) {
				displayHashTable();
			}
			System.out.println();
			
		} catch (IOException e1) {
			e1.printStackTrace();
		} // End of try-catch statement
		
		System.out.println("Would you like to display an inorder traversal of a tree at a specific index? (yes/no)");
		
		// Read in user input
		try {
			input = buffer.readLine();
			
			if (input.equals("yes")) {
				System.out.println();
				System.out.println("Enter the index value. NOTE: It must be less than " + sizeHashTable + ".");
				System.out.println("NOTE: The hash function being used is (x % sizeHashTable), so if ");
				System.out.println("x % 5 = 3, the value x will be placed in index 3, rather than 2.");
				input = buffer.readLine();
				int index = Integer.parseInt(input);
				
				// Print out an in order traversal of the tree at the entered index of hash table
				System.out.println();
				bst.inorderTraversalPrint(hashTable[index]);
				System.out.println();
			} // End if
			
		} catch (IOException e) {
			e.printStackTrace();
		} // End of try-catch statement
		
		System.out.println("Would you like to continue? If not, enter q");
		
		// Read in user input
		try {
			input = buffer.readLine();
			
			if (input.equals("q")) {
				quit = "q";
			}
			
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		} // End of while loop
		
		// Good manners
		System.out.println();
		System.out.println("Goodbye.");
		
	} // End main method

	// Use regular expression to check whether the user input is an integer
	public static boolean isInt(String check) {
		return check.matches("\\d+");
	} // End of isInt method
	
	public static boolean searchHashTable(int number) {
		
		boolean found = false;
		int index = hashFunction(number);
		
		// Search hashTable for argument number
		if (hashTable[index].getElement() == number) {
			found = true;
		} else if (bst.searchTree(hashTable[index])) {
			found = true;
		}
		
		return found;
	} // End of searchHashTable method
	
	
	
	// Display an inorder traversal of every value in the hash table
	public static void displayHashTable() {
		
		System.out.println();
		
		// Traverse hash table and print out values by index
		for (int i = 0; i < sizeHashTable; i++) {
			System.out.println();
			System.out.println("Index " + i + ":");
			System.out.println();
			bst.inorderTraversalPrint(hashTable[i]);
			System.out.println();
			System.out.println();
		}
		
		// Print out all of the values in one line
		for (int i = 0; i < sizeHashTable; i++) {
			bst.inorderTraversalPrint(hashTable[i]);
		}
		
		System.out.println();
		
	} // End of displayHashTable method
	
} // End of HashTable class
