import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BookSearcher {

    public static List<Book> searchBooks(List<Book> books, String searchTerm) {
        List<Book> results = new ArrayList<>();

        for (Book book : books) {
            boolean matches = false;

            // Check if the search term is contained in the id (as a string), title, or
            // author
            if (String.valueOf(book.getId()).contains(searchTerm)) {
                matches = true;
            } else if (book.getTitle().toLowerCase().contains(searchTerm.toLowerCase())) {
                matches = true;
            } else if (book.getAuthor().toLowerCase().contains(searchTerm.toLowerCase())) {
                matches = true;
            }

            // If any field matches, add the book to the results
            if (matches) {
                results.add(book);
            }
        }

        return results;
    }

}
