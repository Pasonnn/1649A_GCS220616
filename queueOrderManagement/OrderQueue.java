import java.io.FileWriter; // Import for writing text to files
import java.io.IOException; // Import for handling input-output exceptions
import java.io.PrintWriter; // Import for printing formatted representations of objects to a text-output stream
import java.util.ArrayList; // Import for using ArrayList to store lists of objects
import java.util.List; // Import for using List interface

// Node class for storing order details
class Node {
    String orderId; // Unique identifier for the order
    Customer customer; // Customer associated with the order
    Book[] books; // Array of books included in the order
    Node next; // Reference to the next node in the queue

    // Constructor to initialize order details
    public Node(String orderId, Customer customer, Book[] books) {
        this.orderId = orderId; // Assigning orderId to the node
        this.customer = customer; // Assigning customer to the node
        this.books = books; // Assigning array of books to the node
        this.next = null; // Setting the next node reference to null
    }
}

// Queue class for managing customer orders
public class OrderQueue {
    private Node front, rear; // References to the front and rear of the queue

    // Constructor to initialize an empty queue
    public OrderQueue() {
        this.front = this.rear = null; // Setting both front and rear references to null initially
    }

    // Enqueue operation: add an order to the rear of the queue
    public boolean enqueue(String orderId, Customer customer, Book[] books) {
        // Check stock for each book in the order
        for (Book book : books) {
            if (!book.isInStock()) { // If any book is out of stock
                System.out.println("Order " + orderId + " failed: Book '" + book.getTitle() + "' is out of stock.");
                return false; // Fail the enqueue if any book is out of stock
            }
        }

        // If all books are in stock, proceed with enqueue
        Node newNode = new Node(orderId, customer, books); // Create a new node with order details
        if (rear == null) { // If the queue is empty
            front = rear = newNode; // Both front and rear point to the new node
        } else {
            rear.next = newNode; // Link the new node to the current rear
            rear = newNode; // Update the rear to point to the new node
        }
        System.out.println("Order " + orderId + " added to the queue.");
        return true; // Enqueue was successful
    }

    // Dequeue operation: remove an order from the front of the queue
    public Node dequeue() {
        if (front == null) { // If the queue is empty
            System.out.println("Queue is empty. No orders to process.");
            return null; // Return null as there is no order to process
        }

        Node removedOrder = front; // Store the current front order for processing
        front = front.next; // Move the front reference to the next node
        if (front == null) { // If the queue is now empty
            rear = null; // Update rear to null as well
        }

        // Process and print order details
        System.out.println("\n\nProcessing Order ID: " + removedOrder.orderId);
        System.out.println(removedOrder.customer.toString());
        System.out.println("Books Ordered:");
        double totalPrice = 0;

        // Print details of each book in the order and decrement stock
        for (Book book : removedOrder.books) {
            System.out.println("+ " + book.toString());
            totalPrice += book.getPrice(); // Add book price to total price
            book.decrementStock(); // Decrement stock when processing the order
        }
        System.out.println("Total Price: $" + totalPrice);

        // Save the completed order details to a CSV file
        saveOrderToCSV(removedOrder, totalPrice);

        return removedOrder; // Return the processed order node
    }

    // Method to save completed order details to a CSV file
    private void saveOrderToCSV(Node order, double totalPrice) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("output.csv", true))) {
            StringBuilder sb = new StringBuilder(); // Use StringBuilder for building CSV line
            sb.append(order.orderId).append(","); // Append order ID
            sb.append(order.customer.getCustomerId()).append(","); // Append customer ID
            sb.append(order.customer.getName()).append(","); // Append customer name
            sb.append(java.time.LocalDateTime.now()).append(","); // Append current date and time

            // Adding the list of book titles to the CSV
            List<String> bookTitles = new ArrayList<>(); // Create a list to store book titles
            for (Book book : order.books) {
                bookTitles.add(book.getTitle()); // Add each book title to the list
            }
            sb.append("\"").append(String.join("; ", bookTitles)).append("\","); // Join titles with semicolon delimiter
            sb.append(totalPrice); // Append the total price of the order

            pw.println(sb.toString()); // Write the CSV line to the file
            System.out.println("Order " + order.orderId + " saved to output.csv.");
        } catch (IOException e) {
            // Handle any IOException that occurs and print an error message
            System.out.println("Error writing to CSV file: " + e.getMessage());
        }
    }

    // Method to check if the queue is empty
    public boolean isEmpty() {
        return front == null; // Return true if the front of the queue is null, meaning no orders are in the
                              // queue
    }
}
