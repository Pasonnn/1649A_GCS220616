import java.io.FileWriter; // Import for writing text to files
import java.io.IOException; // Import for handling input-output exceptions
import java.util.ArrayList; // Import for using ArrayList to store collections of objects
import java.util.Comparator; // Import for defining a comparator to sort objects
import java.util.List; // Import for using List interface

public class BookSorter {

    // Method to sort books within a specific order by a specified field and order
    // (ascending/descending)
    public static List<Book> sortBooksByOrder(List<Order> orders, List<Book> allBooks, String orderId, String field,
            boolean ascending) {
        List<Book> booksToSort = new ArrayList<>(); // List to store books that need to be sorted

        // Find the books in the specified orderId and match with full book details
        for (Order order : orders) {
            if (order.getOrderId().equals(orderId)) { // Check if current order matches the orderId
                for (String bookTitle : order.getBooks()) {
                    // Find the book in allBooks by title
                    for (Book book : allBooks) {
                        if (book.getTitle().equals(bookTitle)) {
                            booksToSort.add(book); // Add book to list if titles match
                        }
                    }
                }
                break; // Exit the loop once the specified order is found and processed
            }
        }

        if (booksToSort.isEmpty()) { // Check if no books were found for the specified orderId
            System.out.println("No books found for order ID: " + orderId);
            return booksToSort;
        }

        // Sort books using merge sort based on the specified field and order
        mergeSort(booksToSort, field, ascending);
        return booksToSort; // Return the sorted list of books
    }

    // Method to perform merge sort on the list of books based on the specified
    // field and order
    private static void mergeSort(List<Book> books, String field, boolean ascending) {
        if (books.size() < 2) {
            return; // No need to sort if the list is empty or has only one element
        }
        List<Book> sortedBooks = mergeSortHelper(books, getComparator(field, ascending)); // Sort using helper method
        books.clear();
        books.addAll(sortedBooks); // Copy sorted books back to the original list
    }

    // Recursive helper method for merge sort
    private static List<Book> mergeSortHelper(List<Book> books, Comparator<Book> comparator) {
        if (books.size() < 2) {
            return books; // Base case: return the list if it has fewer than two elements
        }
        int mid = books.size() / 2; // Find the midpoint for splitting the list
        List<Book> left = mergeSortHelper(new ArrayList<>(books.subList(0, mid)), comparator); // Sort left half
        List<Book> right = mergeSortHelper(new ArrayList<>(books.subList(mid, books.size())), comparator); // Sort right
                                                                                                           // half
        return merge(left, right, comparator); // Merge the sorted halves
    }

    // Method to merge two sorted lists into a single sorted list based on the
    // comparator
    private static List<Book> merge(List<Book> left, List<Book> right, Comparator<Book> comparator) {
        List<Book> merged = new ArrayList<>(); // List to store merged result
        int leftIndex = 0, rightIndex = 0;

        // Merge elements from both lists based on the comparator
        while (leftIndex < left.size() && rightIndex < right.size()) {
            if (comparator.compare(left.get(leftIndex), right.get(rightIndex)) <= 0) {
                merged.add(left.get(leftIndex++)); // Add from left if it compares lower or equal
            } else {
                merged.add(right.get(rightIndex++)); // Add from right otherwise
            }
        }
        // Add remaining elements from left and right lists
        while (leftIndex < left.size()) {
            merged.add(left.get(leftIndex++));
        }
        while (rightIndex < right.size()) {
            merged.add(right.get(rightIndex++));
        }
        return merged; // Return merged list
    }

    // Method to return a comparator for the specified field and order
    // (ascending/descending)
    private static Comparator<Book> getComparator(String field, boolean ascending) {
        Comparator<Book> comparator;

        // Select comparator based on the field specified
        switch (field.toLowerCase()) {
            case "id":
                comparator = Comparator.comparingInt(Book::getId); // Compare by book ID
                break;
            case "title":
                comparator = Comparator.comparing(Book::getTitle); // Compare by book title
                break;
            case "author":
                comparator = Comparator.comparing(Book::getAuthor); // Compare by author name
                break;
            case "price":
                comparator = Comparator.comparingDouble(Book::getPrice); // Compare by book price
                break;
            default:
                throw new IllegalArgumentException("Unknown field: " + field); // Handle invalid field
        }

        return ascending ? comparator : comparator.reversed(); // Return reversed comparator if descending order
    }

    // Method to write sorted books to a CSV file
    public static void writeToCSV(List<Book> books, String filename) {
        try (FileWriter writer = new FileWriter(filename)) { // Initialize FileWriter in try-with-resources
            writer.write("id,title,author,price,isbn\n"); // Write header row to CSV file
            for (Book book : books) {
                // Write book details in CSV format
                writer.write(book.getId() + "," +
                        book.getTitle() + "," +
                        book.getAuthor() + "," +
                        book.getPrice() + "," +
                        book.getISBN() + "\n");
            }
            System.out.println("Sorted books written to CSV file: " + filename);
        } catch (IOException e) {
            // Handle any IOException that occurs and print an error message
            System.out.println("Error writing to CSV file: " + e.getMessage());
        }
    }
}
