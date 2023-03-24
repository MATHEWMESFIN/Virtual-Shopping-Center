import java.util.ArrayList;

public class SavedItems {
    
    private ArrayList<Item> saved_items; // to hold all the items saved for an account
    private int current;

    public SavedItems() {
        saved_items = new ArrayList<Item>();
        current = 0;
    }

    public void add(Item item) {
        saved_items.add(item);
    }

    public void delete(int index) {
        saved_items.remove(index);
    }

    public Item getSavedItem(int index) {
        return saved_items.get(index);
    }
    public int getSize() {
        return saved_items.size();
    }

    public int getCurrent() {
        return current;
    }
    // Iterator methods

    public void reset() {
        current = 0;
    }

    public boolean hasNext() {
        return current != saved_items.size();
    }

    public Item getNext() {
        current++;
        return saved_items.get(current - 1);
    }

}
