package models;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Class containing Vehicle details: VIN, ModelID, OptionID, SaleID, Year, Price
 */
public class Vehicle {

    private String vin;
    private Model model;
    private Option option;
    private int year;
    private int price;

    public Vehicle(){}

    public Vehicle(String vin, Model model, Option option, int year, int price) {
        this.vin = vin;
        this.model = model;
        this.option = option;
        this.year = year;
        this.price = price;
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

    public String getSearchView() {
    	// YEAR COLOR MAKE MODEL PRICE ENGINE TRANSMISSION
        String template = "| %s | %s | %s | %s | %s | %s | %s |";
        String sYear = year > 0 ? String.valueOf(year) : "YEAR";
        return String.format(template, 
        		sYear, 
        		option.getColor(), 
        		model.getBrandName(), 
        		model.getBodyStyle(), 
        		price(), 
        		option.getEngine(), 
        		option.getTransmission());
    }

    public String price() {
    	if (price < 0) {
    		return "PRICE";
    	}
    	
        return NumberFormat.getCurrencyInstance(new Locale("en", "US")).format(price);
    }
    
    public static final Vehicle label() {
    	return new Vehicle("VIN", 
				new Model(-1, "MAKE", "MODEL"), 
				new Option(-1, "COLOR", "ENGINE", "TRANSMISSION"), 
				-1, -1);
    }
}
