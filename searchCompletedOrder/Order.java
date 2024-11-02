import java.util.List;

public class Order {
    private String orderId;
    private String customerId;
    private String customerName;
    private String orderDate;
    private List<String> books;
    private double totalPrice;

    public Order(String orderId, String customerId, String customerName, String orderDate, List<String> books,
            double totalPrice) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.customerName = customerName;
        this.orderDate = orderDate;
        this.books = books;
        this.totalPrice = totalPrice;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public List<String> getBooks() {
        return books;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
        return "Order ID: " + orderId + ", Customer: " + customerName + " (" + customerId + "), Date: " + orderDate +
                ", Books: " + books + ", Total Price: $" + totalPrice;
    }
}
