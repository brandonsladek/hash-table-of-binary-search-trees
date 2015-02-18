package csci232.lab2;

import java.util.Scanner;

import csci232.lab2.BinaryTree.Node;

public class HashTable {

	static Node[] hashTable;
	static int sizeHashTable;
	static BinaryTree bt = new BinaryTree();
	
	// Calculate the index where the element should be inserted into the hash table
	public static int hashFunction(int numToInsert) {
		int index;
		index = numToInsert % sizeHashTable;
		return index;
	} // End of hashFunction method
	
	// Insert an element into the hash table
	public static void insertElement(int numToInsert) {
		int index = hashFunction(numToInsert);
		Node newNode = bt.new Node(numToInsert, null, null, null);
		
		// If there is no collision
		if (hashTable[index] == null) {
		hashTable[index] = newNode;
		} 
		// If there is a collision, insert into binary tree at collision index
			else {
			
		}
	} // End of insertElement method
	
	// Main method
	public static void main(String[] args) {
		
		int numToInsert;
		String quit = "continue";
		
		Scanner in = new Scanner(System.in);
		
		System.out.println("Welcome to the HashBinary program.");
		System.out.println();
		System.out.print("Enter the size of the hash table you would like to work with >>> ");
		
		sizeHashTable = in.nextInt();
		System.out.println();
		
		hashTable = new Node[sizeHashTable];
		
		System.out.println("A hash table of size " + sizeHashTable + " has been created.");
		
		// Repeatedly ask the user to enter an integer into the hash table, quit if user enters q
		while(!quit.equals("q")) {
		
			System.out.println();
			System.out.println("To insert an integer into the hash table, enter an integer.");
			System.out.println("To quit, enter q");
		
			// Get user input
			numToInsert = in.nextInt();
			quit = in.nextLine();
			System.out.println();
		
			insertElement(numToInsert);
			System.out.println("The number " + numToInsert + " has been added to the hash table.");
			System.out.println();
		}
		
	} // End of main method
	
} // End of HashTable class
