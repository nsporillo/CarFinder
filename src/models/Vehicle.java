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
        String template = "%d %s %s %s";
        return String.format(template, year, model.getBrandName(), model.getBodyStyle(), price());
    }

    private String price() {
        return NumberFormat.getCurrencyInstance(new Locale("en", "US")).format(price);
    }
}
