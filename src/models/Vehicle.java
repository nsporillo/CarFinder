package models;

public class Vehicle {

	private String vin;
	private String brand;
	private String model;
	private int year;
	private int price;
	private String interiorColor, exteriorColor;
	private String engine, transmission, drivetrain, trim;
	
	public Vehicle(String vin, String brand, String model, int year, int price, String interiorColor,
			String exteriorColor, String engine, String transmission, String drivetrain, String trim) {
		this.vin = vin;
		this.brand = brand;
		this.model = model;
		this.year = year;
		this.price = price;
		this.interiorColor = interiorColor;
		this.exteriorColor = exteriorColor;
		this.engine = engine;
		this.transmission = transmission;
		this.drivetrain = drivetrain;
		this.trim = trim;
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

	public String getInteriorColor() {
		return interiorColor;
	}

	public void setInteriorColor(String interiorColor) {
		this.interiorColor = interiorColor;
	}

	public String getExteriorColor() {
		return exteriorColor;
	}

	public void setExteriorColor(String exteriorColor) {
		this.exteriorColor = exteriorColor;
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

	public String getDrivetrain() {
		return drivetrain;
	}

	public void setDrivetrain(String drivetrain) {
		this.drivetrain = drivetrain;
	}

	public String getTrim() {
		return trim;
	}

	public void setTrim(String trim) {
		this.trim = trim;
	}
}
