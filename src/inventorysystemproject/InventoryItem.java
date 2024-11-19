package inventorysystemproject;

public class InventoryItem {
    private String itemID; // Unique identifier for the item
    private String itemName; // Name of the item
    private String category; // Category of the item (e.g., Electronics, Clothing)
    private int quantity; // Stock quantity (number of units available)
    private double price; // Price per unit
    private AList<String> suppliers; // Supplier names and contact details

    // Constructor
    public InventoryItem(String itemID, String itemName, String category, int quantity, double price) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
        this.suppliers = new AList<>(); // Initialize the supplier list
    }

    // Getters and Setters
    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public AList<String> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(AList<String> suppliers) {
        this.suppliers = suppliers;
    }

    // Add a supplier to the supplier list
    public void addSupplier(String supplierInfo) {
        this.suppliers.add(supplierInfo);
    }

    // Update quantity (add or subtract)
    public void updateQuantity(int amount) {
        this.quantity += amount;
    }

    // String representation for easy printing
    @Override
    public String toString() {
        return "ItemID: " + itemID + ", ItemName: " + itemName + ", Category: " + category +
                ", Quantity: " + quantity + ", Price: " + price + ", Suppliers: " + suppliers.toString();
    }

    // Equality check for comparing items based on ID
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        InventoryItem that = (InventoryItem) obj;
        return itemID.equals(that.itemID);
    }

    @Override
    public int hashCode() {
        return itemID.hashCode();
    }
}
