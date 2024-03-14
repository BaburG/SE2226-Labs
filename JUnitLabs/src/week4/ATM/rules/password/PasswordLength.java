package week4.ATM.rules.password;

import week4.ATM.Customer;
import week4.ATM.rules.Rule;

public class PasswordLength implements Rule {
    private int length;

    public PasswordLength(int length) {
        this.length = length;
    }

    @Override
    public boolean validate(Customer customer, Object input) {
        assert input instanceof String : "Input must be a string";
        String password = (String) input;

        return (password.length() == length);
    }

    @Override
    public String message() {
        return "Password length must be " + length;
    }
}
