import java.util.List; // Import for using List interface to store collections of book titles

public class Order {
    // Fields representing the attributes of the Order class
    private String orderId; // Unique identifier for the order
    private String customerId; // ID of the customer who placed the order
    private String customerName; // Name of the customer who placed the order
    private String orderDate; // Date when the order was placed
    private List<String> books; // List of book titles included in the order
    private double totalPrice; // Total price of the books in the order

    // Constructor to initialize all the fields of the Order class
    public Order(String orderId, String customerId, String customerName, String orderDate, List<String> books,
            double totalPrice) {
        this.orderId = orderId; // Assigning the orderId parameter to the orderId field
        this.customerId = customerId; // Assigning the customerId parameter to the customerId field
        this.customerName = customerName; // Assigning the customerName parameter to the customerName field
        this.orderDate = orderDate; // Assigning the orderDate parameter to the orderDate field
        this.books = books; // Assigning the list of books parameter to the books field
        this.totalPrice = totalPrice; // Assigning the totalPrice parameter to the totalPrice field
    }

    // Getter methods to access the private fields of the class

    // Returns the unique ID of the order
    public String getOrderId() {
        return orderId;
    }

    // Returns the ID of the customer who placed the order
    public String getCustomerId() {
        return customerId;
    }

    // Returns the name of the customer who placed the order
    public String getCustomerName() {
        return customerName;
    }

    // Returns the date when the order was placed
    public String getOrderDate() {
        return orderDate;
    }

    // Returns the list of book titles in the order
    public List<String> getBooks() {
        return books;
    }

    // Returns the total price of the books in the order
    public double getTotalPrice() {
        return totalPrice;
    }

    // Overridden toString method to provide a readable representation of the Order
    // object
    @Override
    public String toString() {
        // Returns a formatted string representation of the Order object with all its
        // details
        return "Order ID: " + orderId + ", Customer: " + customerName + " (" + customerId + "), Date: " + orderDate +
                ", Books: " + books + ", Total Price: $" + totalPrice;
    }
}
