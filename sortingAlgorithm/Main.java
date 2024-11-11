import java.io.BufferedReader; // Import for reading text from character-input stream
import java.io.FileReader; // Import for reading from files
import java.io.IOException; // Import for handling input-output exceptions
import java.util.ArrayList; // Import for using ArrayList to store collections of objects
import java.util.List; // Import for using List interface
import java.util.Scanner; // Import for obtaining user input from the console

public class Main {
    public static void main(String[] args) {
        // Read orders and books data from CSV files
        List<Order> orders = readOrdersFromCSV("completed_order.csv"); // Loads orders from completed_order.csv
        List<Book> allBooks = readBooksFromCSV("books.csv"); // Loads books from books.csv

        // Scanner for reading user input
        Scanner scanner = new Scanner(System.in);

        // Prompt user to enter the order ID for sorting books
        System.out.print("Enter the order ID to sort books for: ");
        String orderId = scanner.nextLine(); // Get order ID from user input

        // Prompt user to enter the field to sort by
        System.out.print("Enter the field to sort by (id, title, author, price): ");
        String field = scanner.nextLine().toLowerCase(); // Get field to sort by and convert to lowercase

        // Prompt user to choose sort order (ascending or descending)
        System.out.print("Sort in ascending order? (y/n): ");
        boolean ascending = scanner.nextLine().equalsIgnoreCase("y"); // Determine sort order based on user input

        // Sort books based on the specified order ID, field, and sort order
        List<Book> sortedBooks = BookSorter.sortBooksByOrder(orders, allBooks, orderId, field, ascending);

        // Write sorted books to a CSV file
        BookSorter.writeToCSV(sortedBooks, "sorted_books.csv");

        // Confirm the completion of sorting and saving
        System.out.println("Books in order ID " + orderId + " sorted by " + field + " in "
                + (ascending ? "ascending" : "descending") + " order and saved to sorted_books.csv.");

        // Close the scanner
        scanner.close();
    }

    // Method to read books from a CSV file and return a list of Book objects
    private static List<Book> readBooksFromCSV(String fileName) {
        List<Book> books = new ArrayList<>(); // List to store Book objects
        String line; // Variable to store each line read from the file
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            br.readLine(); // Skip the header line in the CSV file
            while ((line = br.readLine()) != null) { // Loop through each line in the file
                String[] values = line.split(","); // Split line by commas to extract book attributes
                int id = Integer.parseInt(values[0].trim()); // Parse book ID
                String title = values[1].trim(); // Get book title
                String author = values[2].trim(); // Get author name
                double price = Double.parseDouble(values[3].trim()); // Parse price of the book
                String isbn = values[4].trim(); // Get ISBN
                int stock = Integer.parseInt(values[5].trim()); // Parse stock quantity
                books.add(new Book(id, title, author, price, isbn, stock)); // Add new Book object to the list
            }
        } catch (IOException e) {
            // Handle IOException if file reading fails
            System.out.println("Error reading CSV file: " + e.getMessage());
        }
        return books; // Return the list of Book objects
    }

    // Method to read orders from a CSV file and return a list of Order objects
    private static List<Order> readOrdersFromCSV(String fileName) {
        List<Order> orders = new ArrayList<>(); // List to store Order objects
        String line; // Variable to store each line read from the file
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            br.readLine(); // Skip the header line in the CSV file
            while ((line = br.readLine()) != null) { // Loop through each line in the file
                String[] values = line.split(","); // Split line by commas to extract order attributes
                String orderId = values[0].trim(); // Get order ID
                String customerId = values[1].trim(); // Get customer ID
                String customerName = values[2].trim(); // Get customer name
                String orderDate = values[3].trim(); // Get order date

                // Parsing book titles, assuming they are separated by semicolons within quotes
                String[] bookTitles = values[4].replaceAll("\"", "").split("; ");
                List<String> books = List.of(bookTitles); // Convert array of titles to a list

                double totalPrice = Double.parseDouble(values[5].trim()); // Parse total price of the order

                // Create a new Order object with parsed fields and add it to the list
                orders.add(new Order(orderId, customerId, customerName, orderDate, books, totalPrice));
            }
        } catch (IOException e) {
            // Handle IOException if file reading fails
            System.out.println("Error reading CSV file: " + e.getMessage());
        }
        return orders; // Return the list of Order objects
    }
}
