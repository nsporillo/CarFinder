package models;

public class Vehicle {

	private String vin;
	private String brand;
	private String model;
	private int year;
	private int price;
	private String color;
	private String engine, transmission;
	
	public Vehicle(String vin, String brand, String model, int year, int price, String color,
				   String engine, String transmission) {
		this.vin = vin;
		this.brand = brand;
		this.model = model;
		this.year = year;
		this.price = price;
		this.color = color;
		this.engine = engine;
		this.transmission = transmission;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getEngine() {
		return engine;
	}

	public void setEngine(String engine) {
		this.engine = engine;
	}

	public String getTransmission() {
		return transmission;
	}

	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}
}
