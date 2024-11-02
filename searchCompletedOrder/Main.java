import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        List<Order> orders = loadOrdersFromCSV("completed_order.csv");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter search field (orderId, customerId, customerName, bookName, date): ");
        String searchField = scanner.nextLine();
        System.out.print("Enter search term: ");
        String searchTerm = scanner.nextLine();

        List<Order> searchResults = OrderSearcher.searchOrders(orders, searchField, searchTerm);

        if (searchResults.isEmpty()) {
            System.out.println("No matching orders found.");
        } else {
            System.out.println("Matching orders:");
            for (Order order : searchResults) {
                System.out.println(order);
            }
        }

        scanner.close();
    }

    public static List<Order> loadOrdersFromCSV(String fileName) {
        List<Order> orders = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            br.readLine(); // Skip the header line

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                // Extract fields from the CSV row
                String orderId = values[0].trim();
                String customerId = values[1].trim();
                String customerName = values[2].trim();
                String orderDate = values[3].trim();

                // Parsing the book titles (assumes books are separated by semicolons within
                // quotes)
                String booksString = values[4].replaceAll("\"", "").trim();
                String[] bookTitles = booksString.split("; ");
                List<String> books = new ArrayList<>();
                for (String title : bookTitles) {
                    books.add(title);
                }

                double totalPrice = Double.parseDouble(values[5].trim());

                // Create a new Order object and add it to the list
                Order order = new Order(orderId, customerId, customerName, orderDate, books, totalPrice);
                orders.add(order);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error parsing number in file: " + e.getMessage());
        }

        return orders;
    }
}
