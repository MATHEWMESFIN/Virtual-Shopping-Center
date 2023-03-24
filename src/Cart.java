import java.util.ArrayList;

public class Cart {
    
    private ArrayList<Item> cart_items; // to hold all the items in an account cart
    private int current;

    public Cart() {
        cart_items = new ArrayList<Item>();
        current = 0;
    }

    public void add(Item item) {
        cart_items.add(item);
    }

    public ArrayList<Item> getCart() {
        return cart_items;
    }

    public Item getCartItem(int index) {
        return cart_items.get(index);
    }

    public int getSize() {
        return cart_items.size();
    }

    public int getCurrent() {
        return current;
    }

    public void deleteCartItem(int index) {
        cart_items.remove(index);
    }

    public void emptyCart() {
        cart_items.clear();
    }

    // Iterator methods

    public void reset() {
        current = 0;
    }

    public boolean hasNext() {
        return current != cart_items.size();
    }

    public Item getNext() {
        current++;
        return cart_items.get(current - 1);
    }
    
}
