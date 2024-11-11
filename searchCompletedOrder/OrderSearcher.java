import java.util.ArrayList; // Import for using ArrayList to store collections of objects
import java.util.List; // Import for using List interface

public class OrderSearcher {

    // Function to search orders based on a specific field
    public static List<Order> searchOrders(List<Order> orders, String searchField, String searchTerm) {
        List<Order> results = new ArrayList<>(); // Create an empty list to store matching orders

        // Iterate through each order in the provided list of orders
        for (Order order : orders) {
            boolean matches = false; // Flag to determine if the current order matches the search criteria

            // Check the search field to determine which attribute to search by
            switch (searchField.toLowerCase()) {
                case "orderid":
                    // Check if the order ID contains the search term
                    if (order.getOrderId().contains(searchTerm)) {
                        matches = true; // Set matches to true if the condition is met
                    }
                    break;

                case "customerid":
                    // Check if the customer ID contains the search term
                    if (order.getCustomerId().contains(searchTerm)) {
                        matches = true; // Set matches to true if the condition is met
                    }
                    break;

                case "customername":
                    // Check if the customer name contains the search term (case-insensitive)
                    if (order.getCustomerName().toLowerCase().contains(searchTerm.toLowerCase())) {
                        matches = true; // Set matches to true if the condition is met
                    }
                    break;

                case "bookname":
                    // Iterate over the list of book titles to see if any of them match the search
                    // term
                    for (String bookTitle : order.getBooks()) {
                        // Check if the book title contains the search term (case-insensitive)
                        if (bookTitle.toLowerCase().contains(searchTerm.toLowerCase())) {
                            matches = true; // Set matches to true if the condition is met
                            break; // Break the loop once a matching book is found
                        }
                    }
                    break;

                case "date":
                    // Check if the order date starts with the search term (ignoring time)
                    // Assuming searchTerm is in "yyyy-MM-dd" format to match the date part
                    if (order.getOrderDate().startsWith(searchTerm)) {
                        matches = true; // Set matches to true if the condition is met
                    }
                    break;

                default:
                    // Handle invalid search fields
                    System.out.println("Invalid search field: " + searchField);
                    return results; // Return an empty list if the search field is invalid
            }

            // If the order matches the search criteria, add it to the results list
            if (matches) {
                results.add(order);
            }
        }

        // Return the list of orders that match the search criteria
        return results;
    }
}
