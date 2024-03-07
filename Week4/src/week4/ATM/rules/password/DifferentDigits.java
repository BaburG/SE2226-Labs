package week4.ATM.rules.password;

import week4.ATM.Customer;
import week4.ATM.rules.Rule;

public class DifferentDigits implements Rule {
    @Override
    public boolean validate(Customer customer, Object input) {
        assert input instanceof String : "Input must be convertible to String";
        String password = (String) input;
        char firstDigit = password.charAt(0);
        char secondDigit = password.charAt(1);
        char thirdDigit = password.charAt(2);
        char fourthDigit = password.charAt(3);
        return ((firstDigit != secondDigit) && (secondDigit != thirdDigit) && (thirdDigit != fourthDigit));
    }

    @Override
    public String message() {
        return "All digits of password can not be the same";
    }
}
