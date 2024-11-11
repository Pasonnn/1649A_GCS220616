public class Customer {

    // Fields representing the attributes of the Customer class
    private String customerId; // Unique identifier for the customer
    private String name; // Customer's name
    private int age; // Customer's age
    private String email; // Customer's email address
    private String phoneNumber; // Customer's phone number
    private String shipAddress; // Shipping address for the customer

    // Constructor to initialize all the fields of the Customer class
    public Customer(String customerId, String name, int age, String email, String phoneNumber, String shipAddress) {
        this.customerId = customerId; // Assigning the customerId parameter to the customerId field
        this.name = name; // Assigning the name parameter to the name field
        this.age = age; // Assigning the age parameter to the age field
        this.email = email; // Assigning the email parameter to the email field
        this.phoneNumber = phoneNumber; // Assigning the phoneNumber parameter to the phoneNumber field
        this.shipAddress = shipAddress; // Assigning the shipAddress parameter to the shipAddress field
    }

    // Getter methods to access the private fields of the class

    // Returns the unique id of the customer
    public String getCustomerId() {
        return customerId;
    }

    // Returns the name of the customer
    public String getName() {
        return name;
    }

    // Returns the age of the customer
    public int getAge() {
        return age;
    }

    // Returns the email address of the customer
    public String getEmail() {
        return email;
    }

    // Returns the phone number of the customer
    public String getPhoneNumber() {
        return phoneNumber;
    }

    // Returns the shipping address of the customer
    public String getShipAddress() {
        return shipAddress;
    }

    // Overridden toString method to provide a readable representation of the
    // Customer object

    // Returns a string representation of the Customer object in the specified
    // format
    @Override
    public String toString() {
        return "Customer{" +
                "id='" + customerId + '\'' + // String representation of customerId field
                ", name='" + name + '\'' + // String representation of name field
                ", age=" + age + // String representation of age field
                ", email='" + email + '\'' + // String representation of email field
                ", phoneNumber='" + phoneNumber + '\'' + // String representation of phoneNumber field
                ", shipAddress='" + shipAddress + '\'' + // String representation of shipAddress field
                '}';
    }
}
