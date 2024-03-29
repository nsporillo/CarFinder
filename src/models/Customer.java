package models;

/**
 * Class containing Customer details: CustomerID, First Name, Last Name, Street,
 * City, State, Zip, Phone, Gender, and Income
 */
public class Customer {

    private int id;
    private String firstName;
    private String lastName;
    private String street;
    private String city;
    private String state;
    private int zip;
    private String phone;
    private int gender;
    private int annualIncome;

    /**
     * Constructor to create a new customer object
     *
     * @param customerID   Unique ID
     * @param firstName    Name of Customer
     * @param lastName     LastName of Customer
     * @param street       Street of Customer
     * @param city         City of Customer
     * @param state        State of Customer
     * @param zip          Zip of Customer
     * @param phone        Phone of Customer
     * @param gender       Gender of Customer
     * @param annualIncome Annual Income of Customer
     */
    public Customer(int customerID, String firstName, String lastName, String street, String city, String state,
                    int zip, String phone, int gender, int annualIncome) {
        this.id = customerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phone = phone;
        this.gender = gender;
        this.annualIncome = annualIncome;
    }

    public int getID() {
        return this.id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getAddress() {
        return street + ", " + city + ", " + state + ", " + zip;
    }

    public String getStreet() {
        return street;
    }


    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public int getZip() {
        return zip;
    }


    public String getPhone() {
        return phone;
    }

    public int getGender() {
        return gender;
    }

    public int getAnnualIncome() {
        return annualIncome;
    }
}
