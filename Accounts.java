import java.util.ArrayList;

public class Accounts {
    
    private ArrayList<Account> accs = new ArrayList<Account>();

    public Account getAccount(String username, String password) {
        // returns the account with the associated username and password
        // returns an AccountNotFoundException if the username and password dont match with an account in the database
        Account account = null;

        for(int i = 0;i < accs.size();i++) {
            if(accs.get(i) == null) {
                return null;
            }
            else if(accs.get(i).getUsername().equals(username) && accs.get(i).getPassword().equals(password)) {
                account = accs.get(i);
            }
        }
        return account;
    }

    public boolean usernameExists(String username) {
        // returns true if there is already a username in the database matching the username passed
        // returns false otherwise
        for(int i = 0;i < accs.size();i++) {
            if(accs.get(i) == null) {
                return false;
            }
            else if(accs.get(i).getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public void addAccount(Account account) {
        accs.add(account);
    }
}
