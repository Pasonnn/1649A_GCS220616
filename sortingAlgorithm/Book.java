public class Book {
    // Fields representing the attributes of the Book class
    private int id; // Unique identifier for the book
    private String title; // Title of the book
    private String author; // Author of the book
    private double price; // Price of the book
    private String ISBN; // ISBN number, uniquely identifying the book
    private int stock; // Number of copies available in stock

    // Constructor to initialize all the fields of the Book class
    public Book(int id, String title, String author, double price, String ISBN, int stock) {
        this.id = id; // Assigning the id parameter to the id field
        this.title = title; // Assigning the title parameter to the title field
        this.author = author; // Assigning the author parameter to the author field
        this.price = price; // Assigning the price parameter to the price field
        this.ISBN = ISBN; // Assigning the ISBN parameter to the ISBN field
        this.stock = stock; // Assigning the stock parameter to the stock field
    }

    // Getter methods to access the private fields of the class

    // Returns the unique id of the book
    public int getId() {
        return id;
    }

    // Returns the title of the book
    public String getTitle() {
        return title;
    }

    // Returns the author of the book
    public String getAuthor() {
        return author;
    }

    // Returns the price of the book
    public double getPrice() {
        return price;
    }

    // Returns the ISBN of the book
    public String getISBN() {
        return ISBN;
    }

    // Returns the current stock of the book
    public int getStock() {
        return stock;
    }

    // Setter method to modify the stock of the book

    // Sets the stock of the book to the provided value
    public void setStock(int stock) {
        this.stock = stock;
    }

    // Method to check if the book is currently in stock

    // Returns true if there is at least one copy of the book in stock
    public boolean isInStock() {
        return getStock() > 0; // Calls getStock() and checks if stock is greater than zero
    }

    // Method to decrement the stock by 1 if stock is available
    public void decrementStock() {
        if (stock > 0) {
            stock--; // Decrements stock by 1
        }
    }

    // Overridden toString method to provide a readable representation of the Book
    // object

    // Returns a string representation of the Book object in the specified format
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", ISBN='" + ISBN + '\'' +
                ", stock=" + stock +
                '}';
    }
}
