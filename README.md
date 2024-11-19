# Inventory Management System

A simple Java-based **Inventory Management System** designed to manage inventory data efficiently. This console-based application supports functionalities such as adding, removing, updating, and searching for items, as well as categorizing and saving inventory data persistently.

---

## Features
1. **Search and Display Items**: Search for items by ID or name and view their details.
2. **List by Category**: Display all items belonging to a specific category.
3. **Add New Item**: Add new items to the inventory by entering their details.
4. **Remove Item**: Delete items from the inventory by their ID.
5. **Update Item**: Modify details of existing items.
6. **Display All Items**: View all items in a structured format.
7. **Save and Exit**: Save changes to the inventory file and exit the program.

---

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 8 or later.
- An IDE or text editor for Java development (e.g., IntelliJ IDEA, Eclipse, or VS Code).

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/AtharvShinde2004/inventory-management-system.git
   ```
2. Open the project in your preferred IDE.
3. Ensure the file structure includes the `item_inventory.csv` file for loading/saving inventory data.

---

## Usage

1. Navigate to the project directory.
2. Compile the program:
   ```bash
   javac -d bin src/inventorysystemproject/*.java
   ```
3. Run the program:
   ```bash
   java -cp bin inventorysystemproject.DSA_project_InventorySystem
   ```

4. Use the text-based menu to perform inventory operations:
   ```
   Inventory Management System
   1. Search and Display Items by ID or Name
   2. List All Items of a Certain Category
   3. Add New Item to the Inventory
   4. Remove Item from the Inventory
   5. Update an Item in the Inventory
   6. Display All Items
   7. Exit (and Save)
   ```

5. Follow prompts to perform desired actions.

---

## File Structure

```
project-directory/
├── src/
│   ├── inventorysystemproject/
│   │   ├── DSA_project_InventorySystem.java
│   ├── item_inventory.csv
├── README.md
```

### Inventory File (`item_inventory.csv`)
This CSV file stores inventory data with the following format:
```
ID,Name,Category,Quantity,Price,Suppliers
101,Keyboard,Electronics,10,500,ABC Suppliers;XYZ Suppliers
```

---

## Implementation Details

### Abstract Data Types (ADTs)
- **ArrayList**: Used for storing inventory items dynamically in memory.
- **String Array**: Each inventory item is represented as an array of attributes (ID, name, category, quantity, price, suppliers).
- **File Handling**: Used for persistent storage of inventory data.

### Time Complexity
| **Operation**           | **Time Complexity** |
|--------------------------|---------------------|
| Add Item                | O(1)               |
| Remove Item             | O(n)               |
| Update Item             | O(n)               |
| Search for Item         | O(n)               |
| Display All Items       | O(n)               |
| Save Inventory          | O(n)               |
| List by Category        | O(n)               |

---

## Contributions
Contributions are welcome! Feel free to fork this repository and submit a pull request.

---

## License
This project is licensed under the MIT License. See the `LICENSE` file for more details.
