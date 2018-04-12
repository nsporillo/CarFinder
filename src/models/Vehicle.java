package models;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

/**
 * Class containing Vehicle details: VIN, ModelID, OptionID, SaleID, Year, Price
 */
public class Vehicle {

    private String vin;
    private Model model;
    private Option option;
    private int year;
    private int price;

    // Optional field, populated by ResultSet to denote a dealer name or customer name
    private String owner;

    public Vehicle(){}

    public Vehicle(String vin, Model model, Option option, int year, int price) {
        this.vin = vin;
        this.model = model;
        this.option = option;
        this.year = year;
        this.price = price;
    }

    public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Integer getModelID() {
        return model.getId();
    }

    public Option getOption() { return option; }

    public void setOption(Option option){ this.option = option; }

    public Integer getOptionID() {
        return option.getId();
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

    @Override
    public String toString() {
        return "Vehicle{" +
                "vin=" + vin +
                ", model=" + model +
                ", option=" + option +
                ", year=" + year +
                ", price=" + price +
                '}';
    }

	private String getSearchColumn(String column) {
		switch (column) {
			case "Dealer":
				return padText(owner, 16);
			case "Year":
				return padText(year > 0 ? String.valueOf(year) : "YEAR", 6);
			case "Color":
				return padText(option.getColor(), 7);
			case "Make":
				return padText(model.getBrandName(), 10);
			case "Model":
				return padText(model.getBodyStyle(), 20);
			case "Price":
				return padText(price(), 14);
			case "Engine":
				return padText(option.getEngine(), 6);
			case "Transmission":
				return padText(option.getTransmission(), 12);
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

    public String price() {
    	if (price < 0) {
    		return "PRICE";
    	}

        return NumberFormat.getCurrencyInstance(new Locale("en", "US")).format(price);
    }

    private static String padText(String color, int size) {
        int padSize = size - color.length();
        int padStart = color.length() + padSize / 2;

        color = String.format("%" + padStart + "s", color);
        color = String.format("%-" + size  + "s", color);

        return color;
    }

    public static final Vehicle label() {
    	Vehicle label = new Vehicle("VIN",
				new Model(-1, "MAKE", "MODEL"),
				new Option(-1, "COLOR", "ENGINE", "TRANSMISSION"),
				-1, -1);
    	label.setOwner("DEALER");
    	return label;
    }
}
