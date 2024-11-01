import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        // Initialize the stack for completed orders
        StackCompletedOrder stackCompletedOrder = new StackCompletedOrder();

        // Load orders from output.csv into the stack
        stackCompletedOrder.loadFromCSV("completedOrder.csv");

        // Display all orders in the stack (from the most recent to the oldest)
        System.out.println("Completed Orders in Stack (LIFO Order):");
        stackCompletedOrder.displayStack();

        // Example usage of peek and pop operations
        System.out.println("\nPeeking at the top of the stack:");
        StackCompletedOrder.OrderNode topOrder = stackCompletedOrder.peek();
        if (topOrder != null) {
            System.out.println(topOrder);
        }

        System.out.println("\nPopping the top order from the stack:");
        StackCompletedOrder.OrderNode poppedOrder = stackCompletedOrder.pop();
        if (poppedOrder != null) {
            System.out.println("Popped Order: " + poppedOrder);
        }

        // Display the stack after a pop operation to show the updated stack contents
        System.out.println("\nStack after one pop operation:");
        stackCompletedOrder.displayStack();
    }
}
