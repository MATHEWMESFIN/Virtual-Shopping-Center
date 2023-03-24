import java.util.Scanner;

import javax.xml.namespace.QName;

public class UserInterface {

    private Account user;


    private final String sysError = "** System Error, please try again **";

    public UserInterface(Account user) {
        this.user = user;
    }

    public void start(Scanner input) {

        int min, max;
        int selection;
        boolean quit = false;

        while(!quit) {
            // Home page
            System.out.println("\n0 - LOGOUT\n\n1 - Shop\n2 - Cart and Saved Items\n3 - Account");
            min = 0; max = 3; //the minimum and maximum valid input
            selection = getSelection(input, min, max);
            switch(selection) {
                case 1: shop(input); break;
                case 2: cartAndSaved(input); break;
                case 3: account(input); break;
                case 0: quit = true; 
                System.out.println("** Logging out... **");
                break;
                default: System.out.println(sysError);
                break;
            }
        }

    }

    public int getSelection(Scanner input, int min, int max) {
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

    public void shop(Scanner input) {

        int min, max;
        int selection;
        boolean home = false;
        while(!home) {
            System.out.println("__________Shop__________");
            System.out.println("0 - Home\n\n"
                            +"1 - Electronics\n"
                            +"2 - Automotive\n"
                            +"3 - Fashion\n"
                            +"4 - Furniture\n"
                            +"5 - Office\n"
                            +"6 - Personal Care\n"
                            +"7 - Pets\n");
            min = 0; max = 7;
            selection = getSelection(input, min, max);
            switch(selection) {
                case 1: SystemInterface.shopAllElectronics(input, user); break;
                case 2: SystemInterface.shopAllAutomotive(input, user); break;
                case 3: SystemInterface.shopAllFashion(input, user); break;
                case 4: SystemInterface.shopAllFurniture(input, user); break;
                case 5: SystemInterface.shopAllOffice(input, user); break;
                case 6: SystemInterface.shopAllPersonalCare(input, user); break;
                case 7: SystemInterface.shopAllPets(input, user); break;
                case 0: home = true; 
                System.out.println("** Returning to Home Page... **");
                break;
                default: System.out.println(sysError);
                break;
                }
            }
    }

    public void cartAndSaved(Scanner input) {

        int min, max;
        int selection;
        boolean home = false;

        while(!home) {

            System.out.println("__________Cart and Saved Items__________");
            System.out.println("__________CART ITEMS__________");
            SystemInterface.displayCartItems(user.getCart());

            System.out.println("__________SAVED ITEMS__________");
            SystemInterface.displaySavedItems(user.getSavedItems());

            System.out.println("\n0 - Home\n\n"
                            +"1 - Checkout\n"
                            +"2 - Save an Item\n"
                            +"3 - Remove a Cart Item\n"
                            +"4 - Remove a Saved Item\n"
                            +"5 - Move a Saved Item to your Cart\n");
            min = 0; max = 5;
            selection = getSelection(input, min, max);

            switch(selection) {
                // To checkout, save an item, or Remove a cart item the user must have at least one item in their cart
                case 1, 2, 3: 
                    if(user.getCart().getSize() < 1) {
                        System.out.println("** You have no items in your cart **");
                    }
                    else {
                        switch(selection) {
                            // Checkout
                            case 1: System.out.println("__________Checkout__________");
                            SystemInterface.checkout(input, user);
                            break;
                            // Save an Item
                            case 2: System.out.println("__________Save an Item__________");
                            SystemInterface.moveCartItemIntoSaved(input, user);
                            break;
                            // Remove a Cart Item
                            case 3: System.out.println("__________Remove a Cart Item__________");
                            SystemInterface.removeCartItem(input, user);
                            break;
                            default: System.out.println(sysError);
                            break;
                        }
                    }
                    System.out.println("** Enter any key to return to cart and saved items **");
                    input.next();
                break;
                // To remove a saved item, or move a saved item to cart the user must have at lease one item saved
                case 4, 5:
                    if(user.getSavedItems().getSize() < 1) {
                        System.out.println("** You have no items saved **");
                    }
                    else {
                        switch(selection) {
                            // Remove a Saved Item
                            case 4: System.out.println("__________Remove a Saved Item__________");
                            SystemInterface.removeSavedItem(input, user);
                            break;
                            // Move Saved Item Back to Cart
                            case 5: System.out.println("__________Move Saved Item Into Cart__________");
                            SystemInterface.moveSavedItemIntoCart(input, user);
                            break;
                            default: System.out.println(sysError);
                            break;
                        }
                    }
                    System.out.println("** Enter any key to return to cart and saved items **");
                    input.next();
                break;
                case 0: home = true;
                System.out.println("** Returning to Home Page... **");
                break;
                default: System.out.println(sysError);
                break;
            }
        }
    }

    public void account(Scanner input) {

        int min, max;
        int selection;
        boolean home = false;

        while(!home) {

            System.out.println("__________Account__________");
            SystemInterface.dashboard(user);

            System.out.println("\n0 - Home\n\n"
                            +"1 - Become a Premium Member\n"
                            +"2 - View Transaction history\n"
                            +"3 - Change Password\n"
                            +"4 - Add/Change Credit Card\n");
            min = 0; max = 4;
            selection = getSelection(input, min, max);

            switch(selection) {
                // After cases 1, 2, 3, and 4 the user is prompted to return to account
                case 1,2,3,4: 
                    switch(selection) {
                        // become a preimum member
                        case 1: 
                            if(user.getPremiumMember()) {
                                System.out.println("** You are already a premium member **");
                            }
                            else {
                                System.out.println("__________Become a Premium Member__________");
                                SystemInterface.becomeAPremiumMember(input, user);
                            }
                            break;
                        // view transactions
                        case 2:
                            if(user.getTransactions().getSize() < 1) {
                                System.out.println("** You have yet to make any transactions **");
                            }
                            else {
                                System.out.println("__________Transactions__________");
                                SystemInterface.displayTransactions(user);
                            }
                            break;
                        // change password
                        case 3: System.out.println("__________Change Password__________");
                            SystemInterface.changePassword(input, user);
                            break;
                        // add/change credit card
                        case 4: 
                            if(user.hasCreditCardOnfile()) {
                                System.out.println("__________Change Credit Card__________");
                            }
                            else {
                                System.out.println("__________Add Credit Card__________");
                            }
                            user.setCreditCard(SystemInterface.getValidCreditCard(input));
                            break;
                        default: 
                            System.out.println(sysError);
                            break;
                    }
                    System.out.println("** Enter any key to return to account **");
                    input.next();
                break;
                case 0: home = true;
                System.out.println("** Returning to home page... **");
                break;
                default: System.out.println(sysError);
                break;
                
            }
        }
        
    }
}
