import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        OrderQueue orderQueue = new OrderQueue();

        // Load books and customers from CSV files
        ArrayList<Book> books = readBooksFromCSV("books.csv");
        HashMap<String, Customer> customers = readCustomersFromCSV("customers.csv");

        // Split books into orders (for example, 3 books per order)
        orderQueue.enqueue("001", customers.get("C123"), new Book[] { books.get(0), books.get(1), books.get(2) });
        orderQueue.enqueue("002", customers.get("C124"), new Book[] { books.get(3), books.get(4), books.get(5) });
        orderQueue.enqueue("003", customers.get("C125"),
                new Book[] { books.get(6), books.get(7), books.get(8), books.get(9) });
        orderQueue.enqueue("004", customers.get("C126"), new Book[] { books.get(2) });

        // Process all orders in the queue
        while (!orderQueue.isEmpty()) {
            orderQueue.dequeue();
        }
    }

    // Method to read books from books.csv and return a list of Book objects
    private static ArrayList<Book> readBooksFromCSV(String fileName) {
        ArrayList<Book> books = new ArrayList<>();
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            br.readLine(); // Skip header line
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                int id = Integer.parseInt(values[0].trim());
                String title = values[1].trim();
                String author = values[2].trim();
                double price = Double.parseDouble(values[3].trim());
                String isbn = values[4].trim();
                int stock = Integer.parseInt(values[5].trim());
                books.add(new Book(id, title, author, price, isbn, stock));
            }
        } catch (IOException e) {
            System.out.println("Error reading books CSV file: " + e.getMessage());
        }
        return books;
    }

    // Method to read customers from customers.csv and return a map of Customer
    // objects by customerId
    private static HashMap<String, Customer> readCustomersFromCSV(String fileName) {
        HashMap<String, Customer> customers = new HashMap<>();
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            br.readLine(); // Skip header line
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String customerId = values[0].trim();
                String name = values[1].trim();
                int age = Integer.parseInt(values[2].trim());
                String email = values[3].trim();
                String phoneNumber = values[4].trim();
                String shipAddress = values[5].trim();
                customers.put(customerId, new Customer(customerId, name, age, email, phoneNumber, shipAddress));
            }
        } catch (IOException e) {
            System.out.println("Error reading customers CSV file: " + e.getMessage());
        }
        return customers;
    }
}
