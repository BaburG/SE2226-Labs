package week4.ATM;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import week4.ATM.Customer;
import week4.ATM.rules.deposit.DepositLimit;

import static org.junit.jupiter.api.Assertions.*;

public class DepositLimitTest {

    static Customer customer;
    static DepositLimit depositLimit;

    @BeforeAll
    static void init() {
        customer = new Customer("a", "1234", 1000);
        depositLimit = new DepositLimit();
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.0001, 0.0002, 0.001, 1, 100, 150, 49999.9999, 50000})
    void validValues(double amount) {
        assertTrue(depositLimit.validate(customer, amount));
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.00009, -0.0001, 50000.00001, 50001})
    void invalidDoubles(double amount) {
        assertFalse(depositLimit.validate(customer, amount));
    }


    // It fails the program with the assert statement in validate function
    @ParameterizedTest
    @CsvSource(value = {
            "''",
            "NULL",
            "45555"

    },nullValues = "NULL")
    void invalidInputs(Object input) {
        assertThrows(AssertionError.class, () ->
                depositLimit.validate(customer, input)
    );
    }
}