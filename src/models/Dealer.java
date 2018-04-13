package models;

import java.util.Map;

public class Dealer {

	private String name, street, city, state, phone;
	private int zip, id;

	public Dealer() {}
	
	public Dealer(String name, int id, String street, String city, String state, int zip, String phone) {
		this.name = name;
		this.id = id;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.phone = phone;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return street + ", " + state + ", " + zip;
	}

	@Override
	public String toString() {
		return "Dealer [name=" + name + ", street=" + street + ", city=" + city + ", state=" + state + ", phone="
				+ phone + ", zip=" + zip + ", id=" + id + "]";
	}


	private static String padText(String color, int size) {
		int padSize = size - color.length();
		int padStart = color.length() + padSize / 2;

		color = String.format("%" + padStart + "s", color);
		color = String.format("%-" + size  + "s", color);

		return color;
	}

	private String getSearchColumn(String column) {
		switch (column) {
			case "Name":
				return padText(name, 16);
			case "ID":
				return padText(String.valueOf(id), 6);
			case "Street":
				return padText(street, 7);
			case "City":
				return padText(city, 10);
			case "State":
				return padText(state, 20);
			case "Zip":
				return padText(String.valueOf(zip), 14);
			case "Phone":
				return padText(phone, 6);
		}

		return "NONE";
	}

	public String getSearchView(Map<String, Boolean> columnToggle) {
		// DEALER YEAR COLOR MAKE MODEL PRICE ENGINE TRANSMISSION
		StringBuilder searchView = new StringBuilder();

		for (Map.Entry<String, Boolean> column : columnToggle.entrySet()) {
			if (column.getValue()) {
				searchView.append("| ").append(getSearchColumn(column.getKey())).append(" ");
			}
		}
		searchView.append("|");

		return searchView.toString();
	}


	public static Dealer label() {
		return new Dealer("NAME", -1, "STREET", "CITY", "STATE", -1,  "PHONE");
	}
}
