package inventorysystemproject;

import java.io.*;
import java.util.*;

public class DSA_project_InventorySystem {

    private static final String FILE_PATH = "src//inventorysystemproject//item_inventory.csv"; // Path to inventory file
    private static final List<String[]> inventory = new ArrayList<>(); // List to store inventory items

    public static void main(String[] args) {
        loadInventory(); // Load inventory data on startup

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            // Displaying the main menu options
            System.out.println("\nInventory Management System");
            System.out.println("1. Search and Display items by ID or Name");
            System.out.println("2. List all items of a certain category");
            System.out.println("3. Add new item to the inventory");
            System.out.println("4. Remove item from the inventory");
            System.out.println("5. Update an item in the inventory");
            System.out.println("6. Display all items");
            System.out.println("7. Exit (and save)");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Clear newline character after integer input

            // Processing user's choice
            switch (choice) {
                case 1:
                    searchItem(scanner); // Search for item by ID or Name
                    break;
                case 2:
                    listByCategory(scanner); // List all items of a specific category
                    break;
                case 3:
                    addItem(scanner); // Add new item to inventory
                    break;
                case 4:
                    removeItem(scanner); // Remove item from inventory
                    break;
                case 5:
                    updateItem(scanner); // Update an item's details
                    break;
                case 6:
                    displayInventory(); // Show all inventory items
                    break;
                case 7:
                    saveInventory(); // Save the current inventory to file and exit
                    System.out.println("Changes saved. Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 7);

        scanner.close(); // Close the scanner resource
    }

    // Load inventory items from the CSV file
    private static void loadInventory() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;

            // Skip the header line
            br.readLine();

            // Read all subsequent lines and add items to inventory list
            while ((line = br.readLine()) != null) {
                inventory.add(line.split(","));
            }
            System.out.println("Inventory loaded successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while loading the inventory.");
            e.printStackTrace();
        }
    }

    // Display all items in the inventory
    private static void displayInventory() {
        System.out.println("\nItem Inventory:");
        System.out.println("======================================================");
        System.out.println("ID | Name | Category | Quantity | Price | Suppliers");
        System.out.println("------------------------------------------------------");

        for (String[] item : inventory) {
            System.out.printf("%s | %s | %s | %s | %s | %s%n",
                    item[0], item[1], item[2], item[3], item[4], item[5]);
        }
    }

    // Add a new item to the inventory
    private static void addItem(Scanner scanner) {
        System.out.print("Enter Item ID: ");
        String id = scanner.nextLine();

        System.out.print("Enter Item Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Item Category: ");
        String category = scanner.nextLine();

        System.out.print("Enter Item Quantity: ");
        String quantity = scanner.nextLine();

        System.out.print("Enter Item Price: ");
        String price = scanner.nextLine();

        System.out.print("Enter Item Suppliers (separated by ';'): ");
        String suppliers = scanner.nextLine();

        inventory.add(new String[] { id, name, category, quantity, price, suppliers });
        System.out.println("Item added successfully.");
    }

    // Remove an item from the inventory by its ID
    private static void removeItem(Scanner scanner) {
        System.out.print("Enter Item ID to remove: ");
        String id = scanner.nextLine();

        boolean removed = inventory.removeIf(item -> item[0].equals(id));
        if (removed) {
            System.out.println("Item removed successfully.");
        } else {
            System.out.println("Item not found.");
        }
    }

    // Update an item's details
    private static void updateItem(Scanner scanner) {
        System.out.print("Enter Item ID to update: ");
        String id = scanner.nextLine();

        for (String[] item : inventory) {
            if (item[0].equals(id)) {
                System.out.print("Enter new Name (leave blank to keep current): ");
                String name = scanner.nextLine();
                if (!name.isEmpty()) item[1] = name;

                System.out.print("Enter new Category (leave blank to keep current): ");
                String category = scanner.nextLine();
                if (!category.isEmpty()) item[2] = category;

                System.out.print("Enter new Quantity (leave blank to keep current): ");
                String quantity = scanner.nextLine();
                if (!quantity.isEmpty()) item[3] = quantity;

                System.out.print("Enter new Price (leave blank to keep current): ");
                String price = scanner.nextLine();
                if (!price.isEmpty()) item[4] = price;

                System.out.print("Enter new Suppliers (leave blank to keep current): ");
                String suppliers = scanner.nextLine();
                if (!suppliers.isEmpty()) item[5] = suppliers;

                System.out.println("Item updated successfully.");
                return;
            }
        }
        System.out.println("Item not found.");
    }

    // Search for an item by ID or Name
    private static void searchItem(Scanner scanner) {
        System.out.print("Enter search term (ID or Name): ");
        String term = scanner.nextLine();

        System.out.println("\nSearch Results:");
        boolean found = false;
        for (String[] item : inventory) {
            if (item[0].equalsIgnoreCase(term) || item[1].equalsIgnoreCase(term)) {
                System.out.printf("ID: %s, Name: %s, Category: %s, Quantity: %s, Price: %s, Suppliers: %s%n",
                        item[0], item[1], item[2], item[3], item[4], item[5]);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Item not found.");
        }
    }

    // List all items of a certain category
    private static void listByCategory(Scanner scanner) {
        System.out.print("Enter category to list items: ");
        String category = scanner.nextLine();

        System.out.println("\nItems in Category: " + category);
        boolean found = false;
        for (String[] item : inventory) {
            if (item[2].equalsIgnoreCase(category)) {
                System.out.printf("ID: %s, Name: %s, Quantity: %s, Price: %s, Suppliers: %s%n",
                        item[0], item[1], item[3], item[4], item[5]);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No items found in this category.");
        }
    }

    // Save the current inventory to the CSV file
    private static void saveInventory() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            // Write the header line
            bw.write("ID,Name,Category,Quantity,Price,Suppliers");
            bw.newLine();

            // Write all inventory items to the file
            for (String[] item : inventory) {
                bw.write(String.join(",", item));
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving the inventory.");
            e.printStackTrace();
        }
    }
}
