/**
 * Data Members (Strings): last name, first name, phone number, address, city,
 * zip code. Methods: Constructor - pass in first and last name (all other
 * fields are blank). Constructor - pass in all data and set them. get and sets
 * - for all data members. toString - all data on one line in the same format as
 * the file. equals - compares by first and last name. compareTo - compare by
 * last name, if the last names are the same then compare by first name).
 */

public class Contact implements Comparable<Contact> {
	/**
	 * data members are private strings
	 */
	private String lastName;//create {getters,setters}
	private String firstName;
	private String phoneNumber;
	private String address;
	private String city;
	private String zipCode;

	/**
	 * Constructor - pass in first and last name (all other fields are blank).
	 * 
	 * @param lastName  is the local variable and is differentiated by the instance
	 *                  variable this.lastname
	 * @param firstName is the local variable and is differentiated by the instance
	 *                  variable this.firstname
	 */
	public Contact(String lastName, String firstName) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.phoneNumber = "";
		this.address = "";
		this.city = "";
		this.zipCode = "";
	}

	/**
	 * Constructor - pass in all data and set them.
	 * 
	 * @param lastName    is the local variable and is differentiated by the
	 *                    instance variable this.lastname
	 * @param firstName   is the local variable and is differentiated by the
	 *                    instance variable this.firstname
	 * @param phoneNumber is the local variable and is differentiated by the
	 *                    instance variable this.phonenumber
	 * @param address     is the local variable and is differentiated by the
	 *                    instance variable this.address
	 * @param city        is the local variable and is differentiated by the
	 *                    instance variable this.city
	 * @param zipCode     is the local variable and is differentiated by the
	 *                    instance variable this.zipcode
	 */
	public Contact(String lastName, String firstName, String phoneNumber, String address, String city, String zipCode) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.city = city;
		this.zipCode = zipCode;
	}

	// The getters and setters methods allow for use of private data strings
	/**
	 * @return lastname
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the local variable and is differentiated by the instance
	 *                 variable this.lastname
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * 
	 * @return firstname
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * 
	 * @param firstName the local variable and is differentiated by the instance
	 *                  variable this.firstname
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * 
	 * @return phonenumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * 
	 * @param phoneNumber the local variable and is differentiated by the instance
	 *                    variable this.phonenumber
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * 
	 * @return address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * 
	 * @param address the local variable and is differentiated by the instance
	 *                variable this.address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 
	 * @return city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * 
	 * @param city the local variable and is differentiated by the instance variable
	 *             this.city
	 * 
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * 
	 * @return zicode
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * 
	 * @param zipCode the local variable and is differentiated by the instance
	 *                variable this.zipcode
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * override toString - all data on one line in the same format as the file.
	 */
	@Override
	public String toString() {
		return lastName + "," + firstName + "," + phoneNumber + "," + address + "," + city + "," + zipCode;
	}

	/**
	 * override equals - compares by first and last name.
	 */
	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (this == o)
			return true;
		if (getClass() != o.getClass())
			return false;

		Contact c = (Contact) o;
		return this.firstName.equals(c.firstName) && this.lastName.equals(c.lastName);
	}

	/**
	 * compareTo - compare by last name, if the last names are the same then compare
	 * by first name.
	 */
	@Override
	public int compareTo(Contact c) {
		// compare the last names
		int comparison = this.lastName.compareTo(c.lastName);
		// if they are the same
		if (comparison == 0) {
			// compare the first names
			return this.firstName.compareTo(c.firstName);
		}
		// otherwise, return the first comparison
		return comparison;
	}
}