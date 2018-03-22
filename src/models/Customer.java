package models;

public class Customer {

	private int id;
	private String firstName;
	private String lastName;
	private String street, city, state;
	private int zip;
	private String phone;
	private int gender;
	private int annualIncome;

	public Customer(int customerID, String firstName, String lastName, String street, String state, int zip, String phone, int gender, int annualIncome) {
		this.id = customerID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.street = street;
		this.city = city;
		this.zip = zip;
		this.state = state;
		this.phone = phone;
		this.gender = gender;
		this.annualIncome = annualIncome;
	}

	public int getID() { return this.id; }

	public void setID(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String name) {
		this.firstName = name;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String name) {
		this.lastName = name;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	public String getState() {return state;}

	public void setState(String state) {this.state = state;}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public int getAnnualIncome() {
		return annualIncome;
	}

	public void setAnnualIncome(int annualIncome) {
		this.annualIncome = annualIncome;
	}
}
