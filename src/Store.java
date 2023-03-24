public class Store {
    
    private Item[] store_items; // to hold all items in the store
    private int current;

    private static final int max_items = 500;

    public Store() {
        store_items = new Item[max_items];
        current = 0;
    }

    public void add(Item item) {
        for(int i = 0;i < store_items.length;i++) {
            if(store_items[i] == null) {
                store_items[i] = item;
                break;
            }
        }
    }

    public Item getItem(String description) {
        // the description is recieved from the system so it should always return an item

        for(int i = 0;i < store_items.length;i++) {
            if(store_items[i].getDescription() == description) {
                return store_items[i];
            }
        }
        return null;
    }

    public int getCurrent() {
        return current;
    }

    // Iterator methods

    public void reset() {
        current = 0;
    }

    public boolean hasNext() {
        return store_items[current] != null;
    }

    public Item getNext() {
        current++;
        return store_items[current - 1];
    }
}
