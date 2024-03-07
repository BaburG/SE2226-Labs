package week4.ATM.rules.password;

import week4.ATM.CircularQueue;
import week4.ATM.Customer;
import week4.ATM.rules.Rule;

public class Last3Password implements Rule {
    @Override
    public boolean validate(Customer customer, Object input) {
        assert input instanceof String : "Input must be a string";
        String password = (String) input; //Clean up
        String customerPassword;
        CircularQueue<String> passwords = customer.getPasswords();
        for (int i = 0; i < passwords.size(); i++) {
            customerPassword = passwords.get(i);
            if (customerPassword != null && customerPassword.equals(password)) { // Fix
                return false;
            }
        }
        return true;
    }

    @Override
    public String message() {
        return "New password must be different from the last 3 password";
    }
}
