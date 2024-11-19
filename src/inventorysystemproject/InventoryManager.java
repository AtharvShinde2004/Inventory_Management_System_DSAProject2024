package inventorysystemproject;

import java.io.*;
import java.util.Iterator;

public class InventoryManager {

    private ListHashTable<String, InventoryItem> items; // Hash table to store inventory items
    private String filePath; // File path for saving and loading inventory items

    // Constructor initializes inventory items and loads data from file
    public InventoryManager(String filePath) {
        this.filePath = filePath;
        this.items = new ListHashTable<>();
        loadItems(); // Load items on initialization
    }

    // Load items from file into memory
    @SuppressWarnings("unchecked")
    public void loadItems() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            // Checking if the object read is of the correct type
            Object obj = ois.readObject();
            if (obj instanceof ListHashTable<?, ?>) {
                // Type-safe cast
                this.items = (ListHashTable<String, InventoryItem>) obj;
            } else {
                System.out.println("Error: The loaded object is not of the expected type.");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading items: " + e.getMessage());
        }
    }

    // Save items from memory to file
    public void saveItems() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(items); // Save inventory items to file
        } catch (IOException e) {
            System.out.println("Error saving items: " + e.getMessage());
        }
    }

    // Add a new item to the inventory
    public void addItem(InventoryItem item) {
        items.add(item.getItemID(), item); // Add item to the hashtable
        System.out.println("Item added: " + item.getItemName());
    }

    // Remove an existing item from the inventory by ID
    public void removeItem(String id) {
        InventoryItem removedItem = items.remove(id); // Remove item by ID
        if (removedItem != null) {
            System.out.println("Item removed: " + removedItem.getItemName());
        } else {
            System.out.println("Item not found with ID: " + id);
        }
    }

    // Find and retrieve an item by its ID
    public InventoryItem findItemById(String id) {
        return items.getValue(id); // Get item by ID
    }

    // Update the stock quantity of an item
    public void updateStockQuantity(String id, int newQuantity) {
        InventoryItem item = findItemById(id); // Find item by ID
        if (item != null) {
            item.setQuantity(newQuantity); // Update stock quantity
            System.out.println("Stock updated for item: " + item.getItemName() + " to " + newQuantity);
        } else {
            System.out.println("Item not found with ID: " + id);
        }
    }

    // Display all items in the inventory
    public void displayAllItems() {
        Iterator<InventoryItem> iterator = items.getValueIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next()); // Print each item
        }
    }

    // Find and display all items in a specific category
    public AList<InventoryItem> findItemsByCategory(String category) {
        AList<InventoryItem> filteredItems = new AList<>();
        Iterator<InventoryItem> iterator = items.getValueIterator();
        while (iterator.hasNext()) {
            InventoryItem item = iterator.next();
            if (item.getCategory().equalsIgnoreCase(category)) {
                filteredItems.add(item); // Add item to filtered list if category matches
            }
        }
        return filteredItems; // Return filtered items
    }
}
