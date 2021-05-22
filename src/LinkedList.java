
/**
 * Node and Linked List class (similar to the lecture notes, modified to use Contacts):
 * Methods to add:
 * 1.remove – pass in the first and last name, use the Contact’s equals method to find the Contact to remove.
 * 2.searchName -pass in the full name, compare using the Contact’s equals method, return the first matching Contact.
 * 3.searchName -pass in a last name, returns an ArrayList of matching Contacts.
 * 4.searchZip - pass in a zip code, returns an ArrayList of matching Contacts.
 * 5.sort -use a Bubble Sort that uses theContact’s compareTo()function to sort the list.  Do not pass in the list to the function and don’t call get().
 * 6.toString-return the list of Contacts sorted and enumerated as a String.
 * 7.toFile-return the list of Contacts as a string suitable for writing the contents of the linked list to the file.
 */
import java.util.ArrayList;

public class LinkedList {
	/**
	 * data members as private Nodes
	 */
	private Node first;
	private Node last;

	/**
	 * Constructs an empty linked list
	 */
	public LinkedList() {
		first = null;
		last = null;
	}

	/**
	 * 
	 * @return the first element in the linked list empty
	 */
	public boolean isEmpty() {
		return first == null;//no root node got an empty list
	}

	/**
	 * Modified to add a contact, now receives a Contact instead of a String
	 * 
	 * @param c is the contact to add to the linked list
	 */
	public void add(Contact c) {
		if (first == null) {
			first = new Node(c);
			last = first;//the only node in line lol
		} else {
			Node n = new Node(c);	//one node
			last.next = n;       	//node is next to last
			last = n;           	//node is new last 
		}
	}

	/**
	 * Modified to add a contact, now receives a Contact instead of a String
	 * 
	 * @param i is the index method to add a contact
	 * @param c the contact to add to the linked list
	 */
	public void add(int i, Contact c) {
		if (i < 0) {
			throw new IndexOutOfBoundsException();
		}
		if (i == 0) {
			first = new Node(c, first);//first node
			if (last == null) {
				last = first;
			}
		} else {
			Node n = first;
			if (n == null) {
				throw new IndexOutOfBoundsException();
			}
			for (int j = 1; j < i; j++) {
				n = n.next;
				if (n == null) {
					throw new IndexOutOfBoundsException();
				}
			}
			n.next = new Node(c, n.next);//????
			if (n.next.next == null) {
				last = n.next;
			}
		}
	}

	/**
	 * Modified to remove a contact, now returns a Contact instead of a String
	 * checks if the linked lists exists
	 * 
	 * @param i the index method to remove a contact given the index
	 * @return rem variable for the removed contact
	 */
	public Contact remove(int i) {
		Contact rem;
		if (first == null || i < 0) {
			throw new IndexOutOfBoundsException();
		}
		if (i == 0) {
			rem = first.data;
			first = first.next;
			if (first == null) {
				last = null;
			}
		} else {
			Node n = first;
			for (int j = 1; j < i; j++) {
				n = n.next;
				if (n == null) {
					throw new IndexOutOfBoundsException();
				}
			}
			if (n.next == null) {
				throw new IndexOutOfBoundsException();
			}
			rem = n.next.data;
			n.next = n.next.next;
			if (n.next == null) {
				last = n;
			}
		}
		return rem;
	}

	/**
	 * Modified to remove by full name
	 * 
	 * @param firstName the variable contact to remove
	 * @param lastName  the variable contact to remove
	 * @return true because we found it or false if not
	 */
	public boolean remove(String firstName, String lastName) {
		// Create a new contact using the first and last name
		Contact c = new Contact(lastName, firstName);
		if (first != null) {
			// compare the contact with the first node's data
			if (c.equals(first.data)) {
				// if it is the first node we update the links
				first = first.next;
				if (first == null) {
					last = null;
				}
				// return true because we found it
				return true;
			} else {
				// otherwise, we start from the second node on (we will use next)
				Node n = first;
				// compare until we reach the end or the contact we are looking for
				while (n.next != null && !n.next.data.equals(c)) {
					n = n.next;
				}
				// if found
				if (n.next != null) {
					// update the links
					n.next = n.next.next;
					if (n.next == null) {
						last = n;
					}
					// return true because we found it
					return true;
				}
			}
		}
		// if we reach this line, the contact wasn't found
		return false;
	}

	/**
	 * method added to search by full name
	 * 
	 * @param firstName the contact being searched
	 * @param lastName  the contact being searched
	 * @return the contact data
	 */
	public Contact searchName(String firstName, String lastName) {
		// create a contact with the given first and last names
		Contact c = new Contact(lastName, firstName);
		// start the search from the first node
		if (first != null) {
			// compare the contact with the first node's data
			if (c.equals(first.data)) {
				// if the contact is in the first node, just return the data
				return first.data;
			} else {
				// otherwise, we start the search with the second node (we will use next)
				Node n = first;
				// compare until we reach the end or the contact we are looking for
				while (n.next != null && !n.next.data.equals(c)) {
					n = n.next;
				}
				// if we found it
				if (n.next != null) {
					// return the data
					return n.next.data;
				}
			}
		}
		// if we reach this line, the contact wasn't found and we return null
		return null;
	}

	/**
	 * Search all contacts that have the same last name
	 * 
	 * @param lastName the contact being searched
	 * @return if it's the same last name, add it to the array list
	 */
	public ArrayList<Contact> searchName(String lastName) {
		// create an empty array list
		ArrayList<Contact> matching = new ArrayList<>();
		// if the linked list is not empty
		if (first != null) {
			// compare the contact with the first node's data
			if (first.data.getLastName().equals(lastName)) {
				// if it's the same last name, add it to the array list
				matching.add(first.data);
			} else {
				Node n = first;
				// compare until we reach the end or the contact we are looking for
				while (n.next != null) {
					if (n.next.data.getLastName().equals(lastName)) {
						// if it's the same last name, add it to the array list
						matching.add(n.next.data);
					}
					// move by one
					n = n.next;
				}
			}
		}
		// return the whole list empty
		// if no contact with that last name was found
		return matching;
	}

	/**
	 * Search all contacts that have the same zip code
	 * 
	 * @param zipCode the contact being searched
	 * @return if it's the same zip code, add it to the array list
	 */
	public ArrayList<Contact> searchZip(String zipCode) {
		// create an empty array list
		ArrayList<Contact> matching = new ArrayList<>();
		if (first != null) {
			// compare the contact with the first node's data
			if (first.data.getZipCode().equals(zipCode)) {
				// if it's the same zip code, add it to the array list
				matching.add(first.data);
			} else {
				Node n = first;
				// compare until we reach the end or the contact we are looking for
				while (n.next != null) {
					if (n.next.data.getZipCode().equals(zipCode)) {
						// if it's the same zip code, add it to the array list
						matching.add(n.next.data);
					}
					// move by one
					n = n.next;
				}
			}
		}
		// return the whole list empty
		// if no contact with that zip code was found
		return matching;
	}

	/**
	 * This method uses bubble sort (lecture 6) to sort the linked list
	 */
	public void sort() {
		{
			boolean swapped = false;
			do {
				Node n = first;//go back to first element in linked list
				swapped = false;//while nodes are not being swapped locations
				while (n.next != null) {
					if (n.data.compareTo(n.next.data) > 0) {//if comparison if less than it will equal -1 or equal is 0
						
						// Perform the swap of the data
						Contact temp = n.data;//temp contact equals the current node's data
						n.data = n.next.data;//current node's data now equals the next node's data
						n.next.data = temp;//the next node's data equals temp contact
						swapped = true;
						
					}
					n = n.next;//regardless of swap this will move down the list
				}
			} while (swapped);
		}
	}

	/**
	 * modified to return a Contact instead of a String
	 * 
	 * @param i is the index method to get a contact
	 * @return the data being retrieved
	 */
	public Contact get(int i) {
		if (first == null || i < 0) {
			throw new IndexOutOfBoundsException();
		}
		Node n = first;
		for (int j = 0; j < i; j++) {
			n = n.next;
			if (n == null) {
				throw new IndexOutOfBoundsException();
			}
		}
		return n.data;
	}

	/**
	 * Modified to receive a Contact instead of a String
	 * 
	 * @param i is the index method to set a contact
	 * @param c the contact information being replaced
	 */
	public void set(int i, Contact c) {
		if (first == null || i < 0) {
			throw new IndexOutOfBoundsException();
		}
		Node n = first;
		for (int j = 0; j < i; j++) {
			n = n.next;
			if (n == null) {
				throw new IndexOutOfBoundsException();
			}
		}
		n.data = c;
	}

	/**
	 * 
	 * @return the count for the size of the file
	 */
	public int size() {
		int count = 0;
		Node n = first;
		while (n != null) {
			count++;
			n = n.next;
		}
		return count;
	}

	@Override
	public String toString() {
		// start an index from 0
		int i = 0;
		// start with an empty String
		String str = "";
		// sort the linked list before creating the String
		sort();
		// start from the first node
		Node n = first;
		// while there are more nodes
		while (n != null) {
			// add the index and the data
			str = str + Integer.toString(i) + ": " + n.data + "\n";
			// move to the next position
			n = n.next;
			// increase the index
			i++;
		}
		// return the whole string
		return str;
	}

	/**
	 * method to create a string that will be saved into the file
	 * 
	 * @return the whole string
	 */
	public String toFile() {
		// start with an empty string
		String str = "";
		// start from the first node
		Node n = first;
		// while there are more nodes
		while (n != null) {
			// append the data to the end of the string
			str = str + n.data + "\n";
			// move one position forward
			n = n.next;
		}
		// return the whole string
		return str;
	}

	private static class Node {
		// modified from lecture 15 to contain a Contact instead of a String
		private Contact data;
		private Node next;

		public Node(Contact s) {
			data = s;
			next = null;
		}

		public Node(Contact s, Node n) {
			data = s;
			next = n;
		}

		@Override
		public String toString() {
			// Now using the Contact's to String method
			return data.toString();
		}
	}
}