public class Book {
    private int id;
    private String title;
    private String author;
    private double price;
    private String ISBN;
    private int stock;

    // Constructor
    public Book(int id, String title, String author, double price, String ISBN, int stock) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
        this.ISBN = ISBN;
        this.stock = stock;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }

    public String getISBN() {
        return ISBN;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public boolean isInStock() {
        return getStock() > 0;
    }

    // Method to decrement stock by 1
    public void decrementStock() {
        if (stock > 0) {
            stock--;
        }
    }

    // Overridden toString method for displaying book information in the specified
    // format
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
