package week4.ATM.rules.shared;

import week4.ATM.Customer;
import week4.ATM.rules.Rule;

public class NotEmpty implements Rule {
    @Override
    public boolean validate(Customer customer, Object input) {
        return (customer != null) && (input != null);
    }

    @Override
    public String message() {
        return "Parameter is null";
    }
}
