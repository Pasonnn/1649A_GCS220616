import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

// Node class for storing order details
class Node {
    String orderId;
    Customer customer;
    Book[] books;
    Node next;

    public Node(String orderId, Customer customer, Book[] books) {
        this.orderId = orderId;
        this.customer = customer;
        this.books = books;
        this.next = null;
    }
}

// Queue class for managing customer orders
public class OrderQueue {
    private Node front, rear;

    public OrderQueue() {
        this.front = this.rear = null;
    }

    // Enqueue operation: add an order to the rear of the queue
    public boolean enqueue(String orderId, Customer customer, Book[] books) {
        // Check stock for each book in the order
        for (Book book : books) {
            if (!book.isInStock()) {
                System.out.println("Order " + orderId + " failed: Book '" + book.getTitle() + "' is out of stock.");
                return false; // Fail the enqueue if any book is out of stock
            }
        }

        // If all books are in stock, proceed with enqueue
        Node newNode = new Node(orderId, customer, books);
        if (rear == null) { // if the queue is empty
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        System.out.println("Order " + orderId + " added to the queue.");
        return true; // Enqueue was successful
    }

    // Dequeue operation: remove an order from the front of the queue
    public Node dequeue() {
        if (front == null) {
            System.out.println("Queue is empty. No orders to process.");
            return null;
        }
        Node removedOrder = front;
        front = front.next;
        if (front == null) { // if the queue is now empty
            rear = null;
        }
        System.out.println("\n\nProcessing Order ID: " + removedOrder.orderId);
        System.out.println(removedOrder.customer.toString());
        System.out.println("Books Ordered:");
        double totalPrice = 0;
        for (Book book : removedOrder.books) {
            System.out.println("+ " + book.toString());
            totalPrice += book.getPrice();
            book.decrementStock(); // Decrement stock when processing the order
        }
        System.out.println("Total Price: $" + totalPrice);

        // Save to output.csv
        saveOrderToCSV(removedOrder, totalPrice);

        return removedOrder;
    }

    // Method to save completed order details to a CSV file
    private void saveOrderToCSV(Node order, double totalPrice) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("output.csv", true))) {
            StringBuilder sb = new StringBuilder();
            sb.append(order.orderId).append(",");
            sb.append(order.customer.getCustomerId()).append(",");
            sb.append(order.customer.getName()).append(",");
            sb.append(java.time.LocalDateTime.now()).append(",");

            // Adding the list of book titles to the CSV
            List<String> bookTitles = new ArrayList<>();
            for (Book book : order.books) {
                bookTitles.add(book.getTitle());
            }
            sb.append("\"").append(String.join("; ", bookTitles)).append("\",");
            sb.append(totalPrice);

            pw.println(sb.toString());
            System.out.println("Order " + order.orderId + " saved to output.csv.");
        } catch (IOException e) {
            System.out.println("Error writing to CSV file: " + e.getMessage());
        }
    }

    // Check if the queue is empty
    public boolean isEmpty() {
        return front == null;
    }
}
