package week4.ATM.rules.deposit;

import week4.ATM.Customer;
import week4.ATM.rules.Rule;

public class DepositLimit implements Rule {
    private static final double depositLowerLimit = 0.0001;
    private static final double depositUpperLimit = 50000;//Some arbitrary number

    @Override
    public boolean validate(Customer customer, Object input) {
        assert input instanceof Double : "The value must be double";
        double amount = (double) input;
        return ((amount >= depositLowerLimit) && (amount <= depositUpperLimit));
    }

    @Override
    public String message() {
        return "The deposit value is not in the range between  " + depositLowerLimit + " - " + depositUpperLimit;
    }
}
