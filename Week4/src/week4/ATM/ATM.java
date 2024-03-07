package week4.ATM;

import week4.ATM.rules.Rule;
import week4.ATM.rules.RuleGenerator;
import week4.ATM.rules.RuleName;

import java.util.ArrayList;

public class ATM {
    private ArrayList<Rule> depositRules;
    private ArrayList<Rule> withdrawRules;
    private ArrayList<Rule> passwordRules;
    private ArrayList<Rule> transferRules;

    //Better to use enums rather than array indexes
    private String[] messages = {
            "Deposit operation successful. Your new balance : ",
            "Withdraw operation successful. Your new balance : ",
            "Money transferred to the customer ",
            "Your password successfully changed",
    };

    public ATM() {
        depositRules = RuleGenerator.getRules(new RuleName[]{
                RuleName.NotEmpty,
                RuleName.DepositLimit,
        });
        withdrawRules = RuleGenerator.getRules(new RuleName[]{
                RuleName.NotEmpty,
                RuleName.WithdrawLimit,
        });
        transferRules = RuleGenerator.getRules(new RuleName[]{
                RuleName.NotEmpty,
                RuleName.WithdrawLimit,
        });
        passwordRules = RuleGenerator.getRules(new RuleName[]{
                RuleName.NotEmpty,
                RuleName.Numeric,
                RuleName.PasswordLength,
                RuleName.YearCheck,
                RuleName.DifferentDigits,
                RuleName.RepeatingDigits,
                RuleName.Last3Password,
        });
    }

    //All functions have rule check first, maybe it can be carried to a shared function

    public void deposit(Customer customer, double amount) {
        boolean success = true;
        for (Rule rule : depositRules) {
            if (!rule.validate(customer, amount)) {
                System.out.println(rule.message());
                success = false;
                break;
            }
        }
        if (success) {
            double balance = customer.getBalance();
            customer.setBalance(balance + amount);
            System.out.println(messages[0] + customer.getBalance());
        }
    }

    public void withdraw(Customer customer, double amount) {
        boolean success = true;
        for (Rule rule : withdrawRules) {
            if (!rule.validate(customer, amount)) {
                System.out.println(rule.message());
                success = false;
                break;
            }
        }
        if (success) {
            double balance = customer.getBalance();
            customer.setBalance(balance - amount);
            System.out.println(messages[1] + customer.getBalance());
        }
    }

    //This function must be rewritten to perform desired operation
    public void transferToCustomer(Customer sender, Customer receiver, double amount) {
        boolean success = true;
        for (Rule rule : transferRules) {
            if (!rule.validate(sender, amount)) {
                System.out.println(rule.message());
                success = false;
                break;
            }
        }
        if (success) {
            double balance = receiver.getBalance();
            receiver.setBalance(balance + amount);
            sender.setBalance(balance-amount); //Fixed
            System.out.println(messages[2] + receiver.getName());
        }
    }

    public void changePassword(Customer customer, String password) {
        boolean success = true;
        for (Rule rule : passwordRules) {
            if (!rule.validate(customer, password)) {
                System.out.println(rule.message());
                return;
            }
        }
        customer.setPassword(password);
        System.out.println(messages[3]);
    }
}
