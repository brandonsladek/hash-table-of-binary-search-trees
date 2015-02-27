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
	
	// Main method
	public static void main(String[] args) {
		
		int numToInsert;
		String input = null;
		
		BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
		HashMap<Integer, Integer> insertedElements = new HashMap<Integer, Integer>();
		
		System.out.println("Welcome to the HashBST program.");
		System.out.println();
		System.out.print("Enter the integer size of the hash table you would like to work with: ");
		
		// Begin reading in user input
		try {
			input = buffer.readLine();
			
			// If input is an integer
			if (isInt(input)) {
				sizeHashTable = Integer.parseInt(input);
				
				// Create new hash table with Node array
				hashTable = new Node[sizeHashTable];
				System.out.println();
				
				// Notify user
				System.out.println("A hash table of size " + sizeHashTable + " has been created.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} // End of try-catch statement
		
		// Repeatedly ask the user to enter an integer into the hash table, quit if user enters q
		while(!input.equals("q")) {
			
			// Good formatting
			System.out.println("--------------------------------------------------------------------------------");
			
			// Prompt user
			System.out.print("Enter first letter of show, insert, find, quit: ");
			
			// Read in user input
			try {
				input = buffer.readLine();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			// Transfer control based on user input
			switch (input) {
			
				// Display contents of hash table
				case "s":
					displayHashTable();
				
				break; // End of case "s"
	
				// Insert an integer into the hash table
				case "i": 
					System.out.println();
					System.out.print("To insert an integer into the hash table, enter an integer: ");
				
					// Get user input
					try {
						input = buffer.readLine();
						System.out.println();
						
						if (isInt(input)) {
							numToInsert = Integer.parseInt(input);
							
							// Insert the integer into the hashTable if it hasn't already been entered
							if (!insertedElements.containsKey(numToInsert)) {
								
								insertElement(numToInsert);
								insertedElements.put(numToInsert, 1);
								numInsertedElements++;
								
								System.out.println("The number " + numToInsert + " has been added to the hash table.");
								System.out.println();
							} else {
								System.out.println(numToInsert + " has already been inserted into the hash table!");
								System.out.println();
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
					
				break; // End of case "i"
				
				// Check to see if a specific integer was inserted into the hash table
				case "f": 
					System.out.println();
					System.out.print("To check if an integer was inserted into the hash table, enter a number: ");
					
					// Read in user input
					try {
						input = buffer.readLine();
						System.out.println();
						
						if (isInt(input)) {
							
							int number = Integer.parseInt(input);
							
							if (searchHashTable(number)) {
								System.out.println(number + " is in the hash table!");
								System.out.println();
							} else {
								System.out.println(number + " is NOT in the hash table!");
								System.out.println();
							}
						} else {
							System.out.println("Invalid entry.");
							System.out.println();
						}
								
					} catch (IOException e1) {
						e1.printStackTrace();
					} // End of try-catch statement
					
				break; // End of case "f"
				
				// Quit
				case "q":
					input = "q";
					
				break; // End of case "q"
				
				// Default case
				default:
					System.out.println();
					System.out.println("Invalid entry. Please try again.");
					System.out.println();
					
					break; // End of default case
					
			} // End of switch statement
	
		} // End while loop
		
		// Good manners
		System.out.println();
		System.out.println("Goodbye.");
		
	} // End main method

	
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
		
		
	// Use regular expression to check whether the user input is an integer
	public static boolean isInt(String check) {
		return check.matches("\\d+");
	} // End of isInt method
	
	
	// Search hash table for a specific number
	public static boolean searchHashTable(int number) {
		
		boolean found = false;
		int index = hashFunction(number);
		
		// Search hashTable for argument number
		if (hashTable[index].getElement() == number) {
			found = true;
		} else if (bst.searchTree(hashTable[index], number)) {
			found = true;
		}
		
		return found;
	} // End of searchHashTable method
	
	
	// Display an inorder traversal of every value in the hash table
	public static void displayHashTable() {
		
		System.out.println("--------------------------------------------------------------------------------");
		
		// Traverse hash table and print out values by index
		for (int i = 0; i < sizeHashTable; i++) {
			System.out.println();
			System.out.print("Index " + i + "---> ");
			bst.inorderTraversalPrint(hashTable[i]);
			System.out.println();
		}
		System.out.println();
		System.out.print("Here is a list of all the elements, sorted by index and inorder traversal: ");

		// Print out all of the values in one line
		for (int i = 0; i < sizeHashTable; i++) {
			bst.inorderTraversalPrint(hashTable[i]);
		}
		System.out.println();
		System.out.println();
	} // End of displayHashTable method
	
	
} // End of HashTable class
