import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BookSorter {

    // Method to sort books by specified field and order within an orderId
    public static List<Book> sortBooksByOrder(List<Order> orders, List<Book> allBooks, String orderId, String field,
            boolean ascending) {
        List<Book> booksToSort = new ArrayList<>();

        // Find the books in the specified orderId and match with full book details
        for (Order order : orders) {
            if (order.getOrderId().equals(orderId)) {
                for (String bookTitle : order.getBooks()) {
                    // Find the book in allBooks by title
                    for (Book book : allBooks) {
                        if (book.getTitle().equals(bookTitle)) {
                            booksToSort.add(book);
                        }
                    }
                }
                break;
            }
        }

        if (booksToSort.isEmpty()) {
            System.out.println("No books found for order ID: " + orderId);
            return booksToSort;
        }

        // Sort books using merge sort based on the specified field and order
        mergeSort(booksToSort, field, ascending);
        return booksToSort;
    }

    private static void mergeSort(List<Book> books, String field, boolean ascending) {
        if (books.size() < 2) {
            return; // No need to sort if list is empty or has only one element
        }
        List<Book> sortedBooks = mergeSortHelper(books, getComparator(field, ascending));
        books.clear();
        books.addAll(sortedBooks);
    }

    private static List<Book> mergeSortHelper(List<Book> books, Comparator<Book> comparator) {
        if (books.size() < 2) {
            return books;
        }
        int mid = books.size() / 2;
        List<Book> left = mergeSortHelper(new ArrayList<>(books.subList(0, mid)), comparator);
        List<Book> right = mergeSortHelper(new ArrayList<>(books.subList(mid, books.size())), comparator);
        return merge(left, right, comparator);
    }

    private static List<Book> merge(List<Book> left, List<Book> right, Comparator<Book> comparator) {
        List<Book> merged = new ArrayList<>();
        int leftIndex = 0, rightIndex = 0;

        while (leftIndex < left.size() && rightIndex < right.size()) {
            if (comparator.compare(left.get(leftIndex), right.get(rightIndex)) <= 0) {
                merged.add(left.get(leftIndex++));
            } else {
                merged.add(right.get(rightIndex++));
            }
        }
        while (leftIndex < left.size()) {
            merged.add(left.get(leftIndex++));
        }
        while (rightIndex < right.size()) {
            merged.add(right.get(rightIndex++));
        }
        return merged;
    }

    private static Comparator<Book> getComparator(String field, boolean ascending) {
        Comparator<Book> comparator;

        switch (field.toLowerCase()) {
            case "id":
                comparator = Comparator.comparingInt(Book::getId);
                break;
            case "title":
                comparator = Comparator.comparing(Book::getTitle);
                break;
            case "author":
                comparator = Comparator.comparing(Book::getAuthor);
                break;
            case "price":
                comparator = Comparator.comparingDouble(Book::getPrice);
                break;
            default:
                throw new IllegalArgumentException("Unknown field: " + field);
        }

        return ascending ? comparator : comparator.reversed();
    }

    // Method to write sorted books to a CSV file
    public static void writeToCSV(List<Book> books, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("id,title,author,price,isbn\n");
            for (Book book : books) {
                writer.write(book.getId() + "," +
                        book.getTitle() + "," +
                        book.getAuthor() + "," +
                        book.getPrice() + "," +
                        book.getISBN() + "\n");
            }
            System.out.println("Sorted books written to CSV file: " + filename);
        } catch (IOException e) {
            System.out.println("Error writing to CSV file: " + e.getMessage());
        }
    }
}
