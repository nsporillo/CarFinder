package models;

/**
 * Class containing Vehicle details: VIN, ModelID, OptionID, SaleID, Year, Price
 */
public class Vehicle {

    private int vin;
    private Model model;
    private Option option;
    private int year;
    private int price;

    public Vehicle(){}

    public Vehicle(int vin, Model model, Option option, int year, int price) {
        this.vin = vin;
        this.model = model;
        this.year = year;
        this.price = price;
    }

    public int getVin() {
        return vin;
    }

    public void setVin(int vin) {
        this.vin = vin;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public int getModelID() {
        return model.getId();
    }

    public Option getOption() { return option; }

    public void setOption(Option option){ this.option = option; }

    public int getOptionID() {
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
}
