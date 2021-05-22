
/**
 *Main class– create a Linked List of Contacts that is populated with “contacts.txt”.
 *Allow the user to choose from the following functions: 
 *1.Add -prompt the user for the Contact’s data, then add the Contact to the list.
 *2.Remove
 *a.byfull name - if found, then remove the Contact from the list.
 *b.by index-display the enumerated list, and remove the Contact.
 *3.Search
 *c.by last name - display all Contacts with the same last name.
 *d.by zip code - display all Contacts with the same zip code.
 *4.Update-prompt the user to enter Contact’s full name, 
 *find the Contact using the search by full name.  
 *If it exists, ask the user to choose which field they want to update, 
 *along with the new information, then update the Contact.
 *5.Display -print an enumerated list of the sorted Contacts.
 *6.Quit - write the list of Contacts back to the file using the toFile function
 * @author George Vargas
 * 11/4/2019
 */
//import all the classes we will be using
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String args[]) {
		// Create one scanner that will be used for the whole program
		Scanner input = new Scanner(System.in);
		// the file could be overwritten later
		String filename = "contacts.txt";
		// create a linked list from the contents of the file
		LinkedList contacts = readFromFile(filename);
		// declare the integers for storing options and suboptions (menu and submenus)
		int option, suboption;
		do {
			// print the menu and read the user input
			printMenu();
			option = CheckInput.getIntRange(1, 6);
			switch (option) {
			case 1:
				add(contacts, input); // send the linked list and the scanner
				break;
			case 2:
				System.out.println("1. by full name");
				System.out.println("2. by index");
				// ask for the sub menu option
				suboption = CheckInput.getIntRange(1, 2);
				switch (suboption) {
				case 1:
					removeByFullName(contacts, input); // send the linked list and the scanner
					break;
				case 2:
					removeByIndex(contacts, input); // send the linked list and the scanner
					break;
				default:
					System.out.println("That is not a valid option.");
					break;
				}
				break;

			case 3:
				System.out.println("1. by last name");
				System.out.println("2. by zip code");
				// ask for the submenu option
				suboption = CheckInput.getIntRange(1, 2);
				switch (suboption) {
				case 1:
					searchByLastName(contacts, input); // send the linked list and the scanner
					break;
				case 2:
					searchByZipCode(contacts, input); // send the linked list and the scanner
					break;
				default:
					System.out.println("That is not a valid option.");
					break;
				}
				break;
			case 4:
				update(contacts, input); // send the linked list and the scanner
				break;
			case 5:
				display(contacts); // send the linked list
				break;
			case 6:
				quit(contacts, filename); // send the linked list and the filename for saving
				break;
			default:
				System.out.println("That is not a valid option.");
				break;
			}
		} while (option != 6); // repeat while the user doesn't chose to quit
		// close the scanner
		input.close();
	}

	/**
	 * method to add a contact. This static method can be called from the main
	 * 
	 * @param contacts variable to add a new contact
	 * @param sc       read input from the user
	 */
	public static void add(LinkedList contacts, Scanner sc) {
		System.out.println("---- Add Contact ----");
		// ask the user to input the data
		System.out.println("Last Name: ");
		String last = sc.nextLine();
		System.out.println("First Name: ");
		String first = sc.nextLine();
		System.out.println("Phone Number: ");
		String phone = sc.nextLine();
		System.out.println("Address: ");
		String address = sc.nextLine();
		System.out.println("City: ");
		String city = sc.nextLine();
		System.out.println("Zip Code: ");
		String zipCode = sc.nextLine();

		// create a contact
		Contact newContact = new Contact(last, first, phone, address, city, zipCode);
		// add it to the linked list
		contacts.add(newContact);
	}

	/**
	 * method to remove a contact given the fullname. This static method can be
	 * called from the main
	 * 
	 * @param contacts variable to remove a contact
	 * @param sc       read input from the user
	 */
	public static void removeByFullName(LinkedList contacts, Scanner sc) {
		System.out.println("---- Remove by Full Name ----");
		// Ask the user to input the data
		System.out.println("Last Name: ");
		String last = sc.nextLine();
		System.out.println("First Name: ");
		String first = sc.nextLine();

		// remove the contact from the linked list
		contacts.remove(first, last);
	}

	/**
	 * method to remove a contact given the index. This static method can be called
	 * from the main
	 * 
	 * @param contacts variable to remove a contact by index
	 * @param sc       read input from the user
	 */
	public static void removeByIndex(LinkedList contacts, Scanner sc) {
		System.out.println("---- Remove by Index ----");
		System.out.println(contacts.toString());
		// Ask the user to input the data
		System.out.println("Which index do you want to remove? ");
		int index = sc.nextInt();
		// remove the contact from the linked list
		contacts.remove(index);
	}

	/**
	 * method to retrieve a list of contacts with the same last name. This static
	 * method can be called from the main
	 * 
	 * @param contacts variable to search a contact by lastname
	 * @param sc       read input from the user
	 */
	public static void searchByLastName(LinkedList contacts, Scanner sc) {
		System.out.println("---- Search by Last Name ----");
		// Ask the user to input the data
		System.out.println("Last Name: ");
		String last = sc.nextLine();
		// get the list of contacts with the same last name
		ArrayList<Contact> results = contacts.searchName(last);
		// go through the whole list
		for (Contact contact : results) {
			// print each contact
			System.out.println(contact.toString());
		}
	}

	/**
	 * method to retrieve a list of contacts with the same zipcode. This static
	 * method can be called from the main
	 * 
	 * @param contacts variable to search a contact by zipcode
	 * @param sc       read input from the user
	 */
	public static void searchByZipCode(LinkedList contacts, Scanner sc) {
		System.out.println("---- Search by Zip Code ----");
		// Ask the user to input the data
		System.out.println("Zip Code: ");
		String zipCode = sc.nextLine();
		// get the list of contacts with the same zip code
		ArrayList<Contact> results = contacts.searchZip(zipCode);
		// go through the whole list
		for (Contact contact : results) {
			// print each contact
			System.out.println(contact.toString());
		}
	}

	/**
	 * method to update a contact given the full name. This static method can be
	 * called from the main
	 * 
	 * @param contacts variable to update a contact
	 * @param sc       read input from the user
	 */
	public static void update(LinkedList contacts, Scanner sc) {
		System.out.println("---- Update ----");
		// ask the user to input the data
		System.out.println("Last Name: ");
		String last = sc.nextLine();
		System.out.println("First Name: ");
		String first = sc.nextLine();

		// search for the contact
		Contact updateContact = contacts.searchName(first, last);
		// if the contact was found
		if (updateContact != null) {
			// ask the user to input the data
			System.out.println("What field do you want to update?");
			System.out.println("1. Last Name");
			System.out.println("2. First Name");
			System.out.println("3. Phone Number");
			System.out.println("4. Address");
			System.out.println("5. City");
			System.out.println("6. Zip Code");
			int field = CheckInput.getIntRange(1, 6);
			switch (field) {
			case 1:
				System.out.println("Last Name: ");
				last = sc.nextLine(); // Ask the user to input the data
				updateContact.setLastName(last); // change the field
				break;
			case 2:
				System.out.println("First Name: ");
				first = sc.nextLine(); // Ask the user to input the data
				updateContact.setFirstName(first); // change the field
				break;
			case 3:
				System.out.println("Phone Number: ");
				String phone = sc.nextLine(); // Ask the user to input the data
				updateContact.setPhoneNumber(phone); // change the field
				break;
			case 4:
				System.out.println("Address: ");
				String address = sc.nextLine(); // Ask the user to input the data
				updateContact.setAddress(address); // change the field
				break;
			case 5:
				System.out.println("City: ");
				String city = sc.nextLine(); // Ask the user to input the data
				updateContact.setCity(city); // change the field
				break;
			case 6:
				System.out.println("Zip Code: ");
				String zip = sc.nextLine(); // Ask the user to input the data
				updateContact.setZipCode(zip); // change the field
				break;
			default:
				System.out.println("That is not a valid option.");
				break;
			}
		}
	}

	/**
	 * method to display all contacts. This static method can be called from the
	 * main
	 * 
	 * @param contacts variable to display the list of contacts
	 */
	public static void display(LinkedList contacts) {
		System.out.println("---- Display ----");
		// print the contacts using the toString method of the linked list
		System.out.println(contacts.toString());
	}

	/**
	 * method to save the contacts to a file. This static method can be called from
	 * the main
	 * 
	 * @param contacts variable for the list of contacts
	 * @param newContants variable for the newly created list of contacts
	 */
	public static void quit(LinkedList contacts, String newContants) {
		System.out.println("---- Quit ----");
		// create a file
		File newList = new File(newContants);
		try {
			// create a print writer
			PrintWriter writer = new PrintWriter(newList);
			// print the whole string returned by the toFile method???
			writer.print(contacts.toFile());
			// flush to add all the contents of the string to the file???
			writer.flush();
			// close the writer
			writer.close();
		} catch (FileNotFoundException fnfe) {
			// print a message error of the file wasn't find
			// this is to identify where to put the file
			System.out.println("File not found: " + newList.getAbsolutePath());
		}
		System.out.println("Good bye");
	}

	/**
	 * method to retrieve the contacts from a file and save them into a linked list.
	 * This static method can be called from the main
	 * 
	 * @param fileName variable for reading the file of contacts
	 * @return the linked list
	 */
	public static LinkedList readFromFile(String fileName) {
		// create an empty linked list
		LinkedList contacts = new LinkedList();
		// create a file
		File file = new File(fileName);
		try {
			// create a Scanner from the file
			Scanner reader = new Scanner(file);
			// while there are more lines
			while (reader.hasNextLine()) {
				// get a line
				String line = reader.nextLine();
				// split it using a comma as a separator
				String[] data = line.split(",");
				// create a new contact with the split data
				// make sure each line is in the right format otherwise this will
				// give an index out of bounds exception
				Contact newPerson = new Contact(data[0], data[1], data[2], data[3], data[4], data[5]);
				// add the contact at the end of the linked list
				contacts.add(newPerson);
			}
			// close the reader
			reader.close();
		} catch (FileNotFoundException fnfe) {
			// print a message error of the file wasn't find
			// this is to identify where to put the file
			System.out.println("File not found: " + file.getAbsolutePath());
		}
		// return the linked list
		return contacts;
	}

	/**
	 * method to print the menu. This static method can be called from the main
	 */
	public static void printMenu() {
		System.out.println();
		System.out.println("Please choose one of the following functions: ");
		System.out.println("1. Add");
		System.out.println("2. Remove");
		System.out.println("3. Search");
		System.out.println("4. Update");
		System.out.println("5. Display");
		System.out.println("6. Quit");
		System.out.println();
	}
}