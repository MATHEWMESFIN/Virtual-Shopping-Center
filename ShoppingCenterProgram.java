import java.util.Scanner;

public class ShoppingCenterProgram {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        Store store = new Store(); // max items in a store = 500
        populate(store);     // initialize the store

        Accounts accs = new Accounts(); // declare the array of accounts

        System.out.println(String.format("%50s","Welcome to My Shopping Center"));

        boolean quit = false;
        while(!quit) {
            // Main Menu
            System.out.println("\n0 - Quit\n\n1 - Create an Account \n2 - Login");
            int min = 0; int max = 2;
            int selection = getSelection(input, min, max);

            switch(selection) {
                case 1: createNewAccount(input, accs); break;
                case 2: login(input, accs, store); break;
                case 0: quit = true; break;
                default: System.out.println("System Error, please try again.");
            }
        }
        
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

    public static void login(Scanner input, Accounts accs, Store store) {
        // Need to check the system for a matching username and password

        System.out.println("__________Login__________");
        System.out.println("Username: ");
        String username = input.next();
        System.out.println("Password: ");
        String password = input.next();

        Account account = accs.getAccount(username, password); 
        // throws AccountNotFoundException if username and password dont match

        if(account != null) {
            // initialize the system with the store
            SystemInterface.init(store);

            // declare and start the user interface with the logged in account
            UserInterface ui = new UserInterface(account);
            ui.start(input);
        }
        else {
            System.out.println("Incorrect username or password, please try again or create an account.");
        }
        
    }

    public static void createNewAccount(Scanner input, Accounts accs) {
        // Check if the username already exists
        // Require entered username to have a length of 6 - 16 characters
        // Require entered passwords to have a minimum length of 8 characters

        String exit = "0"; // used to return back to the main menu

        System.out.println("__________Create New Account__________");

        String username = "";
        String password = "";

        boolean valid_username = false;
        
        while(!valid_username) {
            System.out.println("Username(6 - 16 characters): ");
            username = input.next();
            if(username.equals(exit)) {
                System.out.println("** Returning to main menu... **");
                return;
            } else if(accs.usernameExists(username)) {
                System.out.println("** Username already exists, please try again or enter \"0\" to quit. **");
            } else if(username.length() < 6) {
                System.out.println("** Username is too short, please try again or enter \"0\" to quit. **");
            } else if(username.length() > 16) {
                System.out.println("** Username is too long, please try again or enter \"0\" to quit. **");
            } else {
                valid_username = true;
            }
        }

        boolean valid_password = false;

        while(!valid_password) {
            System.out.println("Password(8 characters minimum): ");
            password = input.next();
            if(password.equals(exit)) {
                System.out.println("** Returning to main menu... **");
                return;
            } else if(password.length() < 8) {
                System.out.println("** Password is too short, please try again or enter \"0\" to quit. **");
            } else {
                valid_password = true;
            }
        }

        Account account = new Account(username, password);
        accs.addAccount(account);
        System.out.println("** Returning to main menu... **");
    }

    public static void populate(Store store) {
        // populating the store

        store.add(new Electronics("Apple iPhone 13", 699.99, true));
        store.add(new Electronics("Apple iPhone 13 Pro", 999.99, false));
        store.add(new Electronics("Apple iPad", 329.99, true));
        store.add(new Electronics("Apple iPad Pro", 799.99, false));
        store.add(new Electronics("Apple MacBook Air", 999.99, true));
        store.add(new Electronics("Apple Macbook Pro", 1299.99, false));

        store.add(new Automotive("Mobile 1 Advanced Full Synthetic Motor Oil 5W-30 5-Quart", 24.47, true));
        store.add(new Automotive("Pennzoil Synthetic 5W-30 Motor Oil 5-Quart", 17.68, false));
        store.add(new Automotive("FOXWELL OBD2 Scanner", 59.49, false));
        store.add(new Automotive("FIXD OBD2 Professional Bluetooth Scan Tool", 56.00, false));
        store.add(new Automotive("NOCO Boost Plus Car Battery Booster", 99.95, false));
        store.add(new Automotive("DEWALT Digital Portable Power Station Jump Starter", 157.43, true));

        store.add(new Fashion("Casio G-Shock", 112.00, true));
        store.add(new Fashion("Rolex Submariner Date", 17995.00, false));
        store.add(new Fashion("Adidas Originals Men's", 65.00, true));
        store.add(new Fashion("Unisex-Adult Classic Clog", 49.95, false));
        store.add(new Fashion("Mens Air Jordan Mid", 229.95, true));

        store.add(new Furniture("Queen Size Bed Frame", 164.99, false));
        store.add(new Furniture("Convertible Sofa Bed", 249.99, true));
        store.add(new Furniture("Harrington Sofa in Black", 819.99, true));
        store.add(new Furniture("Modway Oracle 69-inch Rectangle Dining Table", 328.88, false));
        store.add(new Furniture("PADMA HOUSE 48-inch Round Glass Top Dining Table", 489.00, false));

        store.add(new Office("Ergonomic Office Chair", 199.99, true));
        store.add(new Office("Ergo Lux Leather Office Chair", 269.95, true));
        store.add(new Office("HP Wireless Color All-in-One Printer", 84.89, false));
        store.add(new Office("File Box With Lock", 37.39, false));
        store.add(new Office("Simple Mesh Paper Organizer", 23.97, false));

        store.add(new PersonalCare("Gillette Fusion5 Power Mens Razor Blade, 8 Count", 27.99, false));
        store.add(new PersonalCare("Philips Norelco Multigroomer All-in-One Trimmer", 59.96, true));
        store.add(new PersonalCare("Degree Men Antiperspirant Deodorant, 6 pack", 10.78, true));
        store.add(new PersonalCare("Crest + Scope Complete Whitening Toothpaste, 3 pack", 10.47, false));
        store.add(new PersonalCare("SheaMoisture Curl and Shine Coconut Shampoo", 8.69, false));

        store.add(new Pets("Merrick Dry Dog Food, 22lb. Bag", 64.98, true));
        store.add(new Pets("Furhaven Pet Bed for Dogs and Cats", 59.99, true));
        store.add(new Pets("Meow Mix Original Dry Cat Food, 12lb Bag", 39.98, false));
        store.add(new Pets("BEWISHOME Cat Tree with Sisal Scratching Posts", 94.90, true));
        store.add(new Pets("Tetra Aquarium 20 Gallon Fish Tank Kit", 127.80, false));
    }

}
