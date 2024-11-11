import java.io.BufferedReader; // Import for reading text from character-input stream
import java.io.FileReader; // Import for reading from files
import java.io.IOException; // Import for handling input-output exceptions
import java.util.ArrayList; // Import for using ArrayList to store collections of objects
import java.util.HashMap; // Import for using HashMap to map keys to values

public class Main {
    public static void main(String[] args) {
        // Initialize the stack to manage completed orders
        StackCompletedOrder stackCompletedOrder = new StackCompletedOrder();

        // Load orders from completedOrder.csv file into the stack
        stackCompletedOrder.loadFromCSV("completedOrder.csv");

        // Display all orders currently in the stack, with most recent orders at the top
        // (LIFO order)
        System.out.println("Completed Orders in Stack (LIFO Order):");
        stackCompletedOrder.displayStack();

        // Demonstrate peek operation: view the most recent (top) order in the stack
        // without removing it
        System.out.println("\nPeeking at the top of the stack:");
        StackCompletedOrder.OrderNode topOrder = stackCompletedOrder.peek();
        if (topOrder != null) { // Check if stack is not empty
            System.out.println(topOrder); // Display the top order
        }

        // Demonstrate pop operation: remove and return the top order from the stack
        System.out.println("\nPopping the top order from the stack:");
        StackCompletedOrder.OrderNode poppedOrder = stackCompletedOrder.pop();
        if (poppedOrder != null) { // Check if stack is not empty
            System.out.println("Popped Order: " + poppedOrder); // Display the popped order
        }

        // Display the stack after a pop operation to show updated contents
        System.out.println("\nStack after one pop operation:");
        stackCompletedOrder.displayStack();
    }
}
