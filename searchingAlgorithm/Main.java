import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Books are loaded from CSV or added manually here
        List<Book> books = readBooksFromCSV("books.csv");

        // Get search term from the user
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a search term (ID, Title, or Author): ");
        String searchTerm = scanner.nextLine();

        // Perform search
        List<Book> results = BookSearcher.searchBooks(books, searchTerm);

        // Display results
        if (results.isEmpty()) {
            System.out.println("No books found matching the search term: " + searchTerm);
        } else {
            System.out.println("Books found matching the search term '" + searchTerm + "':");
            for (Book book : results) {
                System.out.println(book);
            }
        }

        scanner.close();
    }

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
