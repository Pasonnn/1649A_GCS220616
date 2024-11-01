import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StackCompletedOrder {
    private OrderNode top;

    // Public static inner class for OrderNode
    public static class OrderNode {
        String orderId;
        String customerId;
        String customerName;
        String orderDate;
        List<String> books;
        double totalPrice;
        OrderNode next;

        public OrderNode(String orderId, String customerId, String customerName, String orderDate, List<String> books,
                double totalPrice) {
            this.orderId = orderId;
            this.customerId = customerId;
            this.customerName = customerName;
            this.orderDate = orderDate;
            this.books = books;
            this.totalPrice = totalPrice;
            this.next = null;
        }

        @Override
        public String toString() {
            return "OrderNode{" +
                    "orderId='" + orderId + '\'' +
                    ", customerId='" + customerId + '\'' +
                    ", customerName='" + customerName + '\'' +
                    ", orderDate='" + orderDate + '\'' +
                    ", books=" + books +
                    ", totalPrice=" + totalPrice +
                    '}';
        }
    }

    public StackCompletedOrder() {
        this.top = null;
    }

    // Push operation to add an order node to the stack
    public void push(OrderNode node) {
        node.next = top;
        top = node;
    }

    // Pop operation to remove the top order node from the stack
    public OrderNode pop() {
        if (top == null) {
            System.out.println("Stack is empty. No orders to pop.");
            return null;
        }
        OrderNode poppedOrder = top;
        top = top.next;
        return poppedOrder;
    }

    // Peek operation to view the top order node without removing it
    public OrderNode peek() {
        if (top == null) {
            System.out.println("Stack is empty. No orders to view.");
            return null;
        }
        return top;
    }

    // Method to load completed orders from a CSV file and push them onto the stack
    public void loadFromCSV(String filename) {
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            br.readLine(); // Skip header line
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                // Check if the line has the correct number of columns (6 in this case)
                if (values.length < 6) {
                    continue; // Skip to the next line if the format is incorrect
                }

                // Parsing order details
                String orderId = values[0].trim();
                String customerId = values[1].trim();
                String customerName = values[2].trim();
                String orderDate = values[3].trim();

                // Parsing book titles (assuming they are separated by semicolons within the
                // quotes)
                String booksString = values[4].replaceAll("\"", "").trim();
                List<String> books = List.of(booksString.split("; "));

                double totalPrice = Double.parseDouble(values[5].trim());

                // Create an OrderNode and push it onto the stack
                OrderNode orderNode = new OrderNode(orderId, customerId, customerName, orderDate, books, totalPrice);
                push(orderNode);
            }
            System.out.println("Completed orders loaded from CSV and stored in the stack.");
        } catch (IOException e) {
            System.out.println("Error reading CSV file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error parsing number in CSV file: " + e.getMessage());
        }
    }

    // Method to display all orders in the stack (for testing purposes)
    public void displayStack() {
        OrderNode current = top;
        while (current != null) {
            System.out.println(current);
            current = current.next;
        }
    }
}
