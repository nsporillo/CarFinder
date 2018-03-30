package models;

/**
 * Class containing Vehicle Options: OptionID, Color, Engine, and Transmission
 */
public class Option {

    private int id;
    private String color;
    private String engine;
    private String transmission;

    public Option(int OptionID, String color, String engine, String transmission) {
        this.id = OptionID;
        this.color = color;
        this.engine = engine;
        this.transmission = transmission;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColor() {
        return color;
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

    @Override
    public String toString() {
        return "Option{" +
                "id=" + id +
                ", color='" + color + '\'' +
                ", engine='" + engine + '\'' +
                ", transmission='" + transmission + '\'' +
                '}';
    }
}
