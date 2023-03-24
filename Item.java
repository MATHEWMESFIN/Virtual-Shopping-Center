public abstract class Item {
    
    private String description;
    private double price;
    private boolean premium_discount;

    public Item(String description, double price, boolean premium_discount) {
        this.description = description;
        this.price = price;
        this.premium_discount = premium_discount;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public double getPremiumPrice() {
        return (Math.round((price-(price*.10))*100)/100.00);
    }
    
    public boolean getPremiumDiscount() {
        return premium_discount;
    }

    public String toString() {
        // Example: Iphone 13     $1000.00     ($900.00 with premium)
        String with_premium = (premium_discount)? ("($" + getPremiumPrice() + " with premium)") : "";
        // (Math.round((price-(price*.1))*100)/100.00) discounts the original price by 10% and rounds to the nearest hundreth
        return (description + "\t $" + price + "\t" + with_premium );
    }
}
