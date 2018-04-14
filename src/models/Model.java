package models;

/**
 * Class containing Model: ModelID, Brand Name and Body Style
 */
public class Model {

    private Integer id;
    private String brandName;
    private String bodyStyle;

    public Model(String brand, String body) {
        this(null, brand, body);
    }

    public Model(Integer modelID, String brandName, String bodystyle) {
        this.id = modelID;
        this.brandName = brandName;
        this.bodyStyle = bodystyle;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public String getBodyStyle() {
        return bodyStyle;
    }

    @Override
    public String toString() {
        return "Model{" +
                "id=" + id +
                ", brandName='" + brandName + '\'' +
                ", bodyStyle='" + bodyStyle + '\'' +
                '}';
    }
}
