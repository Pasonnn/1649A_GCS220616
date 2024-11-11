import java.io.BufferedReader; // Import for reading text from character-input stream
import java.io.FileReader; // Import for reading from files using BufferedReader
import java.io.IOException; // Import for handling input-output exceptions
import java.util.ArrayList; // Import for using ArrayList to store lists of objects
import java.util.List; // Import for using List interface
import java.util.Scanner; // Import for getting user input from console

public class Main {

    public static void main(String[] args) {
        // Load all orders from the specified CSV file
        List<Order> orders = loadOrdersFromCSV("completed_order.csv");

        // Create a Scanner object for reading user input from the console
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter the field they want to search by
        System.out.print("Enter search field (orderId, customerId, customerName, bookName, date): ");
        String searchField = scanner.nextLine(); // Get the search field from user input

        // Prompt the user to enter the term they want to search for
        System.out.print("Enter search term: ");
        String searchTerm = scanner.nextLine(); // Get the search term from user input

        // Use OrderSearcher class to search orders based on the given field and term
        List<Order> searchResults = OrderSearcher.searchOrders(orders, searchField, searchTerm);

        // Display the results
        if (searchResults.isEmpty()) {
            System.out.println("No matching orders found.");
        } else {
            System.out.println("Matching orders:");
            for (Order order : searchResults) {
                System.out.println(order); // Print each matching order
            }
        }

        // Close the scanner to avoid resource leaks
        scanner.close();
    }

    // Method to load order data from a CSV file
    public static List<Order> loadOrdersFromCSV(String fileName) {
        List<Order> orders = new ArrayList<>(); // Create an empty list to store Order objects

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            br.readLine(); // Skip the header line of the CSV file

            // Read each line from the CSV file until the end
            while ((line = br.readLine()) != null) {
                String[] values = line.split(","); // Split the line by commas to get order attributes

                // Extract fields from the CSV row
                String orderId = values[0].trim(); // Extract and trim the orderId field
                String customerId = values[1].trim(); // Extract and trim the customerId field
                String customerName = values[2].trim(); // Extract and trim the customerName field
                String orderDate = values[3].trim(); // Extract and trim the orderDate field

                // Parsing the book titles (assumes books are separated by semicolons within
                // quotes)
                String booksString = values[4].replaceAll("\"", "").trim(); // Remove quotes and trim the book titles
                                                                            // string
                String[] bookTitles = booksString.split("; "); // Split book titles by semicolon followed by space
                List<String> books = new ArrayList<>(); // Create a list to store individual book titles
                for (String title : bookTitles) {
                    books.add(title); // Add each book title to the list
                }

                double totalPrice = Double.parseDouble(values[5].trim()); // Parse and trim the totalPrice field

                // Create a new Order object with the parsed fields and add it to the list of
                // orders
                Order order = new Order(orderId, customerId, customerName, orderDate, books, totalPrice);
                orders.add(order); // Add the new Order object to the orders list
            }
        } catch (IOException e) {
            // Handle any IOException that occurs and print an error message
            System.out.println("Error reading file: " + e.getMessage());
        } catch (NumberFormatException e) {
            // Handle NumberFormatException when parsing a number fails
            System.out.println("Error parsing number in file: " + e.getMessage());
        }

        return orders; // Return the list of orders
    }
}
