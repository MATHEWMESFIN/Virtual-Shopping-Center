public class Account {

    private String username;
    private String password;
    private String credit_card;
    private boolean credit_card_onfile;
    private boolean premium_member;
    private Transactions account_transactions;
    private Cart account_cart;
    private SavedItems account_saved;

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
        this.credit_card = "";
        credit_card_onfile = false;
        premium_member = false;
        account_transactions = new Transactions();
        account_cart = new Cart();
        account_saved = new SavedItems();
    }

    // Getters
    public String getUsername() {
        // username is immutable
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getCreditCard() {
        return credit_card;
    }

    public boolean getPremiumMember() {
        return premium_member;
    }

    public Transactions getTransactions() {
        return account_transactions;
    }

    public Cart getCart() {
        return account_cart;
    }

    public SavedItems getSavedItems() {
        return account_saved;
    }

    // Setters
    public void setPassword(String password) {
        this.password = password;
    }

    public void setCreditCard(String credit_card) {
        this.credit_card = credit_card;
        credit_card_onfile = true;
    }

    public void setPremiumMember(boolean premium_member) {
        this.premium_member = premium_member;
    }

    // Test Methods

    public boolean hasCreditCardOnfile() {
        return credit_card_onfile;
    }
}