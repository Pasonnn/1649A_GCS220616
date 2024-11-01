public class Customer {
    private String customerId;
    private String name;
    private int age;
    private String email;
    private String phoneNumber;
    private String shipAddress;

    // Constructor
    public Customer(String customerId, String name, int age, String email, String phoneNumber, String shipAddress) {
        this.customerId = customerId;
        this.name = name;
        this.age = age;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.shipAddress = shipAddress;
    }

    // Getters and Setters
    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getShipAddress() {
        return shipAddress;
    }

    // Overridden toString method for displaying customer information in the
    // specified format
    @Override
    public String toString() {
        return "Customer{" +
                "id='" + customerId + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", shipAddress='" + shipAddress + '\'' +
                '}';
    }
}
