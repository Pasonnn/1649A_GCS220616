import java.io.BufferedReader; // Import for reading text from a character-input stream
import java.io.FileReader; // Import for reading from files using a BufferedReader
import java.io.IOException; // Import for handling input-output exceptions
import java.util.ArrayList; // Import for using ArrayList to store lists of objects
import java.util.HashMap; // Import for using HashMap to store key-value pairs

public class Main {
    public static void main(String[] args) {
        // Create an instance of OrderQueue to manage orders
        OrderQueue orderQueue = new OrderQueue();

        // Load books and customers from CSV files
        ArrayList<Book> books = readBooksFromCSV("books.csv"); // Reads book data from books.csv into an ArrayList of
                                                               // Book objects
        HashMap<String, Customer> customers = readCustomersFromCSV("customers.csv"); // Reads customer data from
                                                                                     // customers.csv into a HashMap of
                                                                                     // Customer objects

        // Enqueue orders with unique order IDs, customers, and selected books
        orderQueue.enqueue("001", customers.get("C123"), new Book[] { books.get(91), books.get(32), books.get(23) });
        orderQueue.enqueue("002", customers.get("C124"), new Book[] { books.get(14), books.get(25), books.get(26) });
        orderQueue.enqueue("003", customers.get("C125"),
                new Book[] { books.get(6), books.get(27), books.get(42), books.get(29) });
        orderQueue.enqueue("004", customers.get("C126"), new Book[] { books.get(51) });

        // Process all orders in the queue until it is empty
        while (!orderQueue.isEmpty()) {
            orderQueue.dequeue(); // Dequeues (removes and processes) an order from the order queue
        }
    }

    // Method to read book data from a CSV file and return an ArrayList of Book
    // objects
    private static ArrayList<Book> readBooksFromCSV(String fileName) {
        ArrayList<Book> books = new ArrayList<>(); // Create an empty list to store Book objects
        String line; // Variable to store each line read from the CSV file
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            br.readLine(); // Skip the header line in the CSV file
            while ((line = br.readLine()) != null) { // Loop through each line until EOF is reached
                String[] values = line.split(","); // Split the line by commas to extract book attributes
                int id = Integer.parseInt(values[0].trim()); // Parse and trim the book ID
                String title = values[1].trim(); // Trim the title of the book
                String author = values[2].trim(); // Trim the author's name
                double price = Double.parseDouble(values[3].trim()); // Parse and trim the price of the book
                String isbn = values[4].trim(); // Trim the ISBN value
                int stock = Integer.parseInt(values[5].trim()); // Parse and trim the stock quantity
                books.add(new Book(id, title, author, price, isbn, stock)); // Create a Book object and add it to the
                                                                            // list
            }
        } catch (IOException e) {
            // Handle any IOException that occurs and print an error message
            System.out.println("Error reading books CSV file: " + e.getMessage());
        }
        return books; // Return the list of books
    }

    // Method to read customer data from a CSV file and return a HashMap of Customer
    // objects
    private static HashMap<String, Customer> readCustomersFromCSV(String fileName) {
        HashMap<String, Customer> customers = new HashMap<>(); // Create an empty map to store Customer objects by
                                                               // customerId
        String line; // Variable to store each line read from the CSV file
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            br.readLine(); // Skip the header line in the CSV file
            while ((line = br.readLine()) != null) { // Loop through each line until EOF is reached
                String[] values = line.split(","); // Split the line by commas to extract customer attributes
                String customerId = values[0].trim(); // Trim the customer ID
                String name = values[1].trim(); // Trim the customer's name
                int age = Integer.parseInt(values[2].trim()); // Parse and trim the customer's age
                String email = values[3].trim(); // Trim the customer's email address
                String phoneNumber = values[4].trim(); // Trim the customer's phone number
                String shipAddress = values[5].trim(); // Trim the customer's shipping address
                customers.put(customerId, new Customer(customerId, name, age, email, phoneNumber, shipAddress));
            }
        } catch (IOException e) {
            // Handle any IOException that occurs and print an error message
            System.out.println("Error reading customers CSV file: " + e.getMessage());
        }
        return customers; // Return the map of customers
    }
}
