package week4.ATM.rules.password;

import week4.ATM.Customer;
import week4.ATM.rules.Rule;

public class Numeric implements Rule {
    @Override
    public boolean validate(Customer customer, Object input) {
        assert input instanceof String : "Input must be a string";
        String password = (String) input;
        boolean nonNumeric = false;
        for (int index = 0; index < password.length(); index++) {
            char character = password.charAt(index);
            if (!Character.isDigit(character)) {
                nonNumeric = true;
                break;
            }
        }
        return !nonNumeric;
    }

    @Override
    public String message() {
        return "The input contains non-numeric characters";
    }
}
