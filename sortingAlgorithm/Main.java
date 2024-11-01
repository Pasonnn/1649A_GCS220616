import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Book> books = readBooksFromCSV("books.csv");

        // Get sorting field and order from the user
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the field to sort by (id, title, author, price): ");
        String field = scanner.nextLine().toLowerCase();

        System.out.print("Sort in ascending order? (y/n): ");
        boolean ascending = scanner.nextLine().equalsIgnoreCase("y");

        // Sort the books based on user input
        BookSorter.mergeSort(books, field, ascending);

        // Write the sorted list back to a CSV file
        BookSorter.writeToCSV(books, "sorted_books.csv");

        System.out.println("Books sorted by " + field + " in " + (ascending ? "ascending" : "descending")
                + " order and saved to sorted_books.csv.");

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
}
