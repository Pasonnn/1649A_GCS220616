import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Order> orders = readOrdersFromCSV("completed_order.csv");
        List<Book> allBooks = readBooksFromCSV("books.csv");

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the order ID to sort books for: ");
        String orderId = scanner.nextLine();

        System.out.print("Enter the field to sort by (id, title, author, price): ");
        String field = scanner.nextLine().toLowerCase();

        System.out.print("Sort in ascending order? (y/n): ");
        boolean ascending = scanner.nextLine().equalsIgnoreCase("y");

        List<Book> sortedBooks = BookSorter.sortBooksByOrder(orders, allBooks, orderId, field, ascending);
        BookSorter.writeToCSV(sortedBooks, "sorted_books.csv");

        System.out.println("Books in order ID " + orderId + " sorted by " + field + " in "
                + (ascending ? "ascending" : "descending") + " order and saved to sorted_books.csv.");

        scanner.close();
    }

    // Method to read books from CSV and return a list of Book objects
    private static List<Book> readBooksFromCSV(String fileName) {
        List<Book> books = new ArrayList<>();
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
            System.out.println("Error reading CSV file: " + e.getMessage());
        }
        return books;
    }

    // Method to read orders from CSV and return a list of Order objects
    private static List<Order> readOrdersFromCSV(String fileName) {
        List<Order> orders = new ArrayList<>();
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String orderId = values[0].trim();
                String customerId = values[1].trim();
                String customerName = values[2].trim();
                String orderDate = values[3].trim();
                String[] bookTitles = values[4].replaceAll("\"", "").split("; ");
                List<String> books = List.of(bookTitles);
                double totalPrice = Double.parseDouble(values[5].trim());
                orders.add(new Order(orderId, customerId, customerName, orderDate, books, totalPrice));
            }
        } catch (IOException e) {
            System.out.println("Error reading CSV file: " + e.getMessage());
        }
        return orders;
    }
}
