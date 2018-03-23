package models;

public class Dealer {

	private String name, street, state, phone;
	private int zip, id;

	public Dealer(String name, int id, String Street, String State, int zip, String phone) {
		super();
		this.name = name;
		this.id = id;
		this.street = Street;
		this.state = State;
		this.zip = zip;
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
