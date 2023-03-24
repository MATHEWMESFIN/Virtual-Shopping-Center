import java.util.ArrayList;

public class Transaction {
    
    private ArrayList<Item> transaction_items; // to hold all the items in one transaction
    private int totalItems;
    private double totalCost;

    public Transaction(ArrayList<Item> t, int totalItems, double totalCost) {
        transaction_items = t;
        this.totalItems = totalItems;
        this.totalCost = totalCost;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public String getAllItems() {

        String allItems = "";
        for(int i = 0;i < transaction_items.size();i++) {
            allItems += ("\n" + transaction_items.get(i));
        }
        
        return allItems;
    }

    public String toString() {
        // Example: Number of items: 3
        //          item1
        //          item2
        //          item3
        //          Total Cost: $100.00
        return ("Number of Items: " + totalItems + getAllItems() + "\nTotal Cost: $" + totalCost );
    }
}
