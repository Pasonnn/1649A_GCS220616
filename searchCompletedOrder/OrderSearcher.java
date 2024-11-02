import java.util.ArrayList;
import java.util.List;

public class OrderSearcher {

    // Function to search orders based on a specific field
    public static List<Order> searchOrders(List<Order> orders, String searchField, String searchTerm) {
        List<Order> results = new ArrayList<>();

        for (Order order : orders) {
            boolean matches = false;

            // Check based on search field
            switch (searchField.toLowerCase()) {
                case "orderid":
                    if (order.getOrderId().contains(searchTerm)) {
                        matches = true;
                    }
                    break;

                case "customerid":
                    if (order.getCustomerId().contains(searchTerm)) {
                        matches = true;
                    }
                    break;

                case "customername":
                    if (order.getCustomerName().toLowerCase().contains(searchTerm.toLowerCase())) {
                        matches = true;
                    }
                    break;

                case "bookname":
                    // Now iterating over `List<String>` for book titles
                    for (String bookTitle : order.getBooks()) {
                        if (bookTitle.toLowerCase().contains(searchTerm.toLowerCase())) {
                            matches = true;
                            break;
                        }
                    }
                    break;

                case "date":
                    // Check if the order date (ignoring time) matches the search date
                    if (order.getOrderDate().startsWith(searchTerm)) { // Assuming searchTerm is in "yyyy-MM-dd" format
                        matches = true;
                    }
                    break;

                default:
                    System.out.println("Invalid search field: " + searchField);
                    return results;
            }

            // If order matches the search criteria, add it to results
            if (matches) {
                results.add(order);
            }
        }

        return results;
    }
}
