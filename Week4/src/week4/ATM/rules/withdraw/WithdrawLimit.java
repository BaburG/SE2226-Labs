package week4.ATM.rules.withdraw;

import week4.ATM.Customer;
import week4.ATM.rules.Rule;

public class WithdrawLimit implements Rule {
    private static final double withdrawLowerLimit = 0.0001;

    @Override
    public boolean validate(Customer customer, Object input) {
        assert input instanceof Double : "The value must be double";
        double amount = (double) input;
        double withdrawUpperLimit = customer.getBalance();
        return ((amount >= withdrawLowerLimit) && (amount <= withdrawUpperLimit));
    }

    @Override
    public String message() {
        return "Can not withdraw money.Check the entered amount";
    }
}
