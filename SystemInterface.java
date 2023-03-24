import java.util.Scanner;

public class SystemInterface {
    
    private static Store store;

    public static void init(Store s) {
        store = s;
    }

    public static int getSelection(Scanner input, int min, int max) {
        int selection = 0;
        boolean validSelection = false;
        while(!validSelection) {
            try{
                String temp = input.next();
                selection = Integer.valueOf(temp);
                validSelection = (selection >= min && selection <= max);
                if(!validSelection) {
                    System.out.println("** Invalid input, please try again. **");
                }
            } catch(NumberFormatException e) {
                System.out.println("** Invalid input, please try again. **");
            }
        }
        return selection;
    }
    
    /*
    *
    * Shop related methods
    *
    */

    public static void shopCatagory(Scanner input, Account user, Item[] category) {
        // used by the shopAll******* methods to reduce repetivity

        boolean back = false;
        int min, max;
        int selection;

        while(!back) {
                // print all the items of the category as a list with the index
            for(int i = 0;i < category.length;i++) {
                // add 1 to i so the output starts at 1 instead of 0
                System.out.println((i + 1) + " - " + category[i] + "\n");
            }

            System.out.println("Enter the number next to the item you want to add to your cart (or enter \"0\" to go back): ");
            min = 0; max = (category.length);
            selection = getSelection(input, min, max);

            if(selection == 0) {
                back = true;
            }
            else {
                user.getCart()
                    .add(category[selection - 1]);
                System.out.println(category[selection - 1] + " added to your cart");
            }
        }
        

    }

    public static void shopAllElectronics(Scanner input, Account user) {

        Item[] electronics;
        int counter = 0;

        // get the amount of electronics in the store
        store.reset();
        while(store.hasNext()) {
            if(store.getNext() instanceof Electronics) {
                counter++;
            }
        }

        // store all the electronics in its own list
        electronics = new Item[counter];

        int i = 0;
        store.reset();
        while(store.hasNext()) {
            Item item = store.getNext();
            if(item instanceof Electronics) {
                electronics[i++] = item;
            }
        }

        shopCatagory(input, user, electronics);
        
    }

    public static void shopAllAutomotive(Scanner input, Account user) {

        Item[] automotive;
        int counter = 0;

        // get the amount of automotives in the store
        store.reset();
        while(store.hasNext()) {
            if(store.getNext() instanceof Automotive) {
                counter++;
            }
        }

        // store all the automotives in its own list
        automotive = new Item[counter];

        int i = 0;
        store.reset();
        while(store.hasNext()) {
            Item item = store.getNext();
            if(item instanceof Automotive) {
                automotive[i++] = item;
            }
        }

        shopCatagory(input, user, automotive);
    }

    public static void shopAllFashion(Scanner input, Account user) {

        Item[] fashion;
        int counter = 0;

        // get the amount of fashion items in the store
        store.reset();
        while(store.hasNext()) {
            if(store.getNext() instanceof Fashion) {
                counter++;
            }
        }

        // store all the fashion items in its own list
        fashion = new Item[counter];

        int i = 0;
        store.reset();
        while(store.hasNext()) {
            Item item = store.getNext();
            if(item instanceof Fashion) {
                fashion[i++] = item;
            }
        }

        shopCatagory(input, user, fashion);
    }

    public static void shopAllFurniture(Scanner input, Account user) {

        Item[] furniture;
        int counter = 0;

        // get the amount of furniture in the store
        store.reset();
        while(store.hasNext()) {
            if(store.getNext() instanceof Furniture) {
                counter++;
            }
        }

        // store all the furniture in its own list
        furniture = new Item[counter];

        int i = 0;
        store.reset();
        while(store.hasNext()) {
            Item item = store.getNext();
            if(item instanceof Furniture) {
                furniture[i++] = item;
            }
        }

        shopCatagory(input, user, furniture);
    }

    public static void shopAllOffice(Scanner input, Account user) {

        Item[] office;
        int counter = 0;

        // get the amount of office items in the store
        store.reset();
        while(store.hasNext()) {
            if(store.getNext() instanceof Office) {
                counter++;
            }
        }

        // store all the office items in its own list
        office = new Item[counter];

        int i = 0;
        store.reset();
        while(store.hasNext()) {
            Item item = store.getNext();
            if(item instanceof Office) {
                office[i++] = item;
            }
        }

        shopCatagory(input, user, office);
    }

    public static void shopAllPersonalCare(Scanner input, Account user) {

        Item[] personal_care;
        int counter = 0;

        // get the amount of personal care items in the store
        store.reset();
        while(store.hasNext()) {
            if(store.getNext() instanceof PersonalCare) {
                counter++;
            }
        }

        // store all the personal care items in its own list
        personal_care = new Item[counter];

        int i = 0;
        store.reset();
        while(store.hasNext()) {
            Item item = store.getNext();
            if(item instanceof PersonalCare) {
                personal_care[i++] = item;
            }
        }
        
        shopCatagory(input, user, personal_care);
    }

    public static void shopAllPets(Scanner input, Account user) {

        Item[] pets;
        int counter = 0;

        // get the amount of pet items in the store
        store.reset();
        while(store.hasNext()) {
            if(store.getNext() instanceof Pets) {
                counter++;
            }
        }

        // store all the pet items in its own list
        pets = new Item[counter];

        int i = 0;
        store.reset();
        while(store.hasNext()) {
            Item item = store.getNext();
            if(item instanceof Pets) {
                pets[i++] = item;
            }
        }

        shopCatagory(input, user, pets);
    }

    /*
    *
    * Cart and saved items related methods
    *
    */

    public static void displayCartItems(Cart cart) {

        cart.reset();
        while(cart.hasNext()) {
            System.out.println((cart.getCurrent() + 1) + " - " + cart.getNext() + "\n");
        }
    }

    public static void displaySavedItems(SavedItems saved) {
        
        saved.reset();
        while(saved.hasNext()) {
            System.out.println((saved.getCurrent() + 1) + " - " + saved.getNext() + "\n");
        }
    }

    public static double calculateTotalCost(Cart cart, boolean premium_member) {

        // used by the checkout method to calculate the total cost of a transaction
        // is the user is a premium member it will calculate the cost including discounts

        double totalCost = 0;

        cart.reset();
        while(cart.hasNext()) {
            Item item = cart.getNext();
            if(item.getPremiumDiscount() && premium_member) { 
                totalCost += item.getPremiumPrice();
            } else {
                totalCost += item.getPrice();
            }
        }
        // the total cost is taxed by 6%
        totalCost = (Math.round((totalCost + (totalCost * .06))*100)/100.0);

        return totalCost;
    }

    public static void checkout(Scanner input, Account user) {

        int min, max;
        int selection;

        Cart cart = user.getCart();
        boolean premium_member = user.getPremiumMember();

        // Get and output the total cost
        double totalCost = SystemInterface.calculateTotalCost(cart, premium_member); 
        System.out.println("The total cost will be: $" + totalCost);

        // get the credit card
        if(user.hasCreditCardOnfile()) {
            int showLastFour = 12; // the beginning index to show the last 4 digits of a 16 digit credit card number
            System.out.println("Would you like to use your credit card ending with: " + user.getCreditCard().substring(showLastFour) + "? 1 - Yes\t2 - No");
            min = 1; max = 2;
            selection = getSelection(input, min, max);
            if(selection  == 2) {
                user.setCreditCard(getValidCreditCard(input)); // adds a valid credit card under the users account
            }
        }
        else {
            user.setCreditCard(getValidCreditCard(input)); // adds a valid credit card under the users account
        }


        System.out.println("Successfully purchased the following items: ");

        cart.reset();
        while(cart.hasNext()) {
            System.out.println(cart.getNext() + "\n");
        }

        System.out.println("With a total cost of: $" + totalCost);

        // inform premium users of their savings
        if(user.getPremiumMember()) {
            double noDiscountTotal = 0; // the total cost if the user had not been a premium member
            cart.reset();
            while(cart.hasNext()) {
                noDiscountTotal += cart.getNext().getPrice();
            }
            double money_saved = (Math.round((noDiscountTotal - totalCost) * 100) / 100); // money saved as a premium member rounded to the nearest hundreth
            System.out.println("You saved: $" + money_saved + " as a premium member!");
        }

        // Create and add the transaction to the account
        Transaction newTransaction = new Transaction(cart.getCart(), cart.getSize(), totalCost);
        user.getTransactions().add(newTransaction);

        // Empty the cart
        cart.emptyCart();
        
    }


    public static void moveCartItemIntoSaved(Scanner input, Account user) {

        int min, max;
        int selection;

        Cart cart = user.getCart();
        SavedItems saved = user.getSavedItems();

        // get the index of the cart item
        System.out.println("Enter the number next to the cart item to save it for later (or enter \"0\" to go back): ");
        min = 0; max = cart.getSize();
        selection = getSelection(input, min, max);

        if(selection == 0 ) {
            return;
        }

        selection = selection - 1; // selection for array items substracted by one because the index starts at 0

        String item = cart.getCartItem(selection).toString();

        // add that cart item into the saved and remove it from the cart
        saved.add(cart.getCartItem(selection));
        cart.deleteCartItem(selection);

        System.out.println("Successfully saved: " + item + " from your cart!");
    }

    public static void removeCartItem(Scanner input, Account user) {

        int min, max;
        int selection;

        Cart cart = user.getCart();

        // get the index of the cart item
        System.out.println("Enter the number next to the cart item to remove it (or enter \"0\" to go back): ");
        min = 0; max = cart.getSize();
        selection = getSelection(input, min, max);

        if(selection == 0) {
            return;
        }

        selection = selection - 1; // selection for array items substracted by one because the index starts at 0

        String item = cart.getCartItem(selection).toString();

        // remove that cart item
        cart.deleteCartItem(selection);

        System.out.println("Successfully removed: " + item + " from your cart!");
    }

    public static void moveSavedItemIntoCart(Scanner input, Account user) {

        int min, max;
        int selection;

        SavedItems saved = user.getSavedItems();
        Cart cart = user.getCart();

        // get the index of the saved item
        System.out.println("Enter the number next to the saved item to move it to your cart (or enter \"0\" to go back):");
        min  = 0; max = saved.getSize();
        selection = getSelection(input, min, max);

        if(selection == 0) {
            return;
        }

        selection = selection - 1; // selection for array items substracted by one because the index starts at 0

        String item = saved.getSavedItem(selection).toString();

        // add that saved item into the cart and remove it from saved
        cart.add(saved.getSavedItem(selection));
        saved.delete(selection);

        System.out.println("Successfully moved saved item: " + item + " into your cart!");
    }

    public static void removeSavedItem(Scanner input, Account user) {

        int min, max;
        int selection;

        SavedItems saved = user.getSavedItems();

        // get the index of the saved item
        System.out.println("Enter the number next to the saved item to remove it (or enter \"0\" to go back): ");
        min = 0; max = saved.getSize();
        selection = getSelection(input, min, max);

        if(selection == 0) {
            return;
        }

        selection = selection - 1; // selection for array items substracted by one because the index starts at 0

        String item = saved.getSavedItem(selection).toString();

        //remove that saved item
        saved.delete(selection);

        System.out.println("Successfully removed: " + item + " from your saved!");
    }

    /*
    * 
    * Account related methods
    * 
    */

    public static void dashboard(Account user) {
        // provides a quick summary of the account

        System.out.println("Hello, " + user.getUsername() + "\n");

        String member_type = (user.getPremiumMember())? " * PREMIUM MEMBER * ": " free member ";
        System.out.println(member_type);

        if(user.hasCreditCardOnfile()) {
            int showLastFour = 12; // the beginning index to show the last 4 digits of a 16 digit credit card number
            System.out.println("Card on file: ..." + user.getCreditCard().substring(showLastFour));
        }
        else {
            System.out.println("No card on file");
        }

        System.out.println(user.getCart().getSize() + " items in your cart");
        System.out.println(user.getSavedItems().getSize() + " items saved");

        System.out.println(user.getTransactions().getSize() + " transactions made");
    }

    public static void becomeAPremiumMember(Scanner input, Account user) {

        int min, max;
        int selection;

        System.out.println("To become a premium member there is a one-time fee of $200.00.\n" 
                            + "After paying the fee, you will enjoy a 10% discount on certain items.");

        System.out.println("Would you like to continue? 1 - YES\t2 - NO");
        min = 1; max = 2;
        selection = getSelection(input, min, max);

        switch(selection) {
            // become a premimum member
            case 1: 
                // get the credit card
                if(user.hasCreditCardOnfile()) {
                    int showLastFour = 12; // the beginning index to show the last 4 digits of a 16 digit credit card number
                    System.out.println("Would you like to use your credit card ending with: " + user.getCreditCard().substring(showLastFour) + "? 1 - Yes\t2 - No");
                    min = 1; max = 2;
                    int option = getSelection(input, min, max);
                    if(option  == 2) {
                        user.setCreditCard(getValidCreditCard(input)); // adds a valid credit card under the users account
                    }
                }
                else {
                    user.setCreditCard(getValidCreditCard(input)); // adds a valid credit card under the users account
                }
                
                user.setPremiumMember(true);
                System.out.println("Congratulations on becoming a premium member!");
                break;
        }

    }

    public static void displayTransactions(Account user) {
        
        Transactions t = user.getTransactions();

        t.reset();
        while(t.hasNext()) {
            System.out.println(t.getNext());
            System.out.println("________________________________________");
        }
    }

    public static void changePassword(Scanner input, Account user) {

        // prompt user to enter their current password before entering a new password
        // the new password must be of minimum length 8
        
        String exit = "0";

        boolean valid_password = false;
        String current_pass = "";
        String password = "";

        System.out.println("Enter your current password: ");
        while(!user.getPassword().equals(current_pass)) {
            current_pass = input.next();
            if(current_pass.equals(exit)) {
                return;
            }
            else if(!user.getPassword().equals(current_pass)) {
                System.out.println("** Entered password is incorrect, please try again or enter \"0\" to quit. **");
            }
        }
        

        while(!valid_password) {
            System.out.println("Enter your new password(8 characters minimum): ");
            password = input.next();
            if(password.equals(exit)) {
                return;
            } else if(password.length() < 8) {
                System.out.println("** Password is too short, please try again or enter \"0\" to quit. **");
            } else {
                valid_password = true;
                user.setPassword(password);
                System.out.println("Successfully changed your password!");
            }
        }
    }

    public static String getValidCreditCard(Scanner input) {

        boolean valid = false;
        String creditcard = "";

        System.out.println("Enter your 16-digit credit card number: ");

        while(!valid) {
            creditcard = input.next();
            // check if the entered credit card is of length 16
            if(creditcard.length() == 16) {
                int i = 0;
                // check if the entered credit card is all digits
                while(i < creditcard.length()) {
                    char c = creditcard.charAt(i);
                    if(!(c >= '0' && c <= '9')) {
                        System.out.println("** Invalid credit card number **\n** Hint: the number must 16-digits long **");
                        break;
                    }
                    i++;
                }
                if(i == 16) {
                    valid = true;
                }
            }
            else {
                System.out.println("** Invalid credit card number **\n** Hint: the number must 16-digits long **");
            }
        }

        int showLastFour = 12; // the beginning index to show the last 4 digits of a 16 digit credit card number
        System.out.println("Successfully added the credit card ending with: " + creditcard.substring(showLastFour));

        return creditcard;
    }
}
