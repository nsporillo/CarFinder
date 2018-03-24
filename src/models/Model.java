package models;

/**
 * Class containing Model: ModelID, Brand Name and Body Style
 */
public class Model {

    private int id;
    private String brandName;
    private String bodyStyle;

    public Model(int modelID, String brandName, String bodystyle) {
        this.id = modelID;
        this.brandName = brandName;
        this.bodyStyle = bodystyle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBodyStyle() {
        return bodyStyle;
    }

    public void setBodyStyle(String bodyStyle) {
        this.bodyStyle = bodyStyle;
    }


}
