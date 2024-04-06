package week4.ATM;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


import static org.junit.jupiter.api.Assertions.*;

public class ATMTestMoodle {

    static ATM atm;
    static Customer customer;
    static double initialBalance = 1000;


    @BeforeAll
    static void init() {
        customer = new Customer("a", "1234", initialBalance);
        atm = new ATM();
    }


    @ParameterizedTest
    @ValueSource(doubles = {0.0001, 1, 49999, 50000})
    void validDepositValues(double amount) {
        double currentBalance = customer.getBalance();
        atm.deposit(customer, amount);
        double expectedBalance = currentBalance + amount;
        assertEquals(expectedBalance, customer.getBalance());
    }

    @ParameterizedTest
    @ValueSource(doubles = {-0.0001, 50000.001, -15})
    void invalidDepositValues(double amount) {
        double currentBalance = customer.getBalance();
        atm.deposit(customer, amount);
        assertEquals(currentBalance, customer.getBalance());
    }

    @ParameterizedTest
    @ValueSource(strings = {"1234","5678","9876"})
    void validPasswordChange(String newPassword) {
        atm.changePassword(customer,newPassword);
        assertEquals(newPassword,customer.getPassword());
    }

    @ParameterizedTest
    @ValueSource(strings = {"0012","1200","1002","0001","1000","0100","0010","0000","9999"})
    void invalidPasswordChange(String newPassword) {
        String currentPassword = customer.getPassword();
        atm.changePassword(customer,newPassword);
        assertEquals(currentPassword,customer.getPassword());
    }
}