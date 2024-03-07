package week4.ATM.rules;


import week4.ATM.rules.deposit.DepositLimit;
import week4.ATM.rules.password.*;
import week4.ATM.rules.shared.NotEmpty;
import week4.ATM.rules.withdraw.WithdrawLimit;

import java.util.ArrayList;

public class RuleGenerator {

    public static ArrayList<Rule> getRules(RuleName[] names) throws IllegalArgumentException {
        ArrayList<Rule> rules = new ArrayList<>();
        for (RuleName name : names) {
            if (name == RuleName.NotEmpty) {
                rules.add(new NotEmpty());
            } else if (name == RuleName.DepositLimit) {
                rules.add(new DepositLimit());
            } else if (name == RuleName.WithdrawLimit) {
                rules.add(new WithdrawLimit());
            } else if (name == RuleName.DifferentDigits) {
                rules.add(new DifferentDigits());
            } else if (name == RuleName.Last3Password) {
                rules.add(new Last3Password());
            } else if (name == RuleName.PasswordLength) {
                rules.add(new PasswordLength(4));
            } else if (name == RuleName.RepeatingDigits) {
                rules.add(new RepeatingDigits());
            } else if (name == RuleName.YearCheck) {
                rules.add(new YearCheck());
            } else if (name == RuleName.Numeric) {
                rules.add(new Numeric());
            } else {
                throw new IllegalArgumentException("The rule " + name + " is not exist");
            }
        }
        return rules;
    }
}
