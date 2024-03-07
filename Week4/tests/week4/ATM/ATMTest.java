package week4.ATM;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ATMTest {
    static ATM atm;
    static Customer aaa;
    static Customer bbb;
    static final int INITIAL = 5000;

    @BeforeAll
    static void generateATM(){
        atm = new ATM();

        aaa = new Customer("aaaa", "4512", INITIAL);
        bbb = new Customer("bbbb", "4512", INITIAL);

    }

    @AfterEach
    void aftereach(){
        aaa = new Customer("aaaa", "4512", INITIAL);
        bbb = new Customer("bbbb", "4512", INITIAL);
    }

    @DisplayName("Test Deposit")
    @Test
    void deposit() {
        assertEquals(INITIAL, aaa.getBalance());
        atm.deposit(aaa, 200);
        assertEquals(INITIAL + 200, aaa.getBalance());
    }
    @DisplayName("Test Zero Deposit")
    @Test
    void depositZero() {
        assertEquals(INITIAL, aaa.getBalance());
        atm.deposit(aaa, 0);
        assertEquals(INITIAL, aaa.getBalance());
    }
    @DisplayName("Test Negative Deposit")
    @Test
    void negativeDeposit() {
        assertEquals(INITIAL, aaa.getBalance());
        atm.deposit(aaa, -200);
        assertEquals(INITIAL, aaa.getBalance());
    }
    @DisplayName("Test limit Edge Deposit")
    @Test
    void edgeDeposit() {
        assertEquals(INITIAL, aaa.getBalance());
        atm.deposit(aaa, 0.00001);
        atm.deposit(aaa, 50001);
        assertEquals(INITIAL, aaa.getBalance());
    }


    @DisplayName("Withdraw test")
    @Test
    void withdraw() {
        assertEquals(INITIAL, aaa.getBalance());
        atm.withdraw(aaa, 200);
        assertEquals(INITIAL - 200, aaa.getBalance());
    }
    @DisplayName("Withdraw Zero")
    @Test
    void withdrawZero(){
        assertEquals(INITIAL, aaa.getBalance());
        atm.withdraw(aaa,0);
        assertEquals(INITIAL, aaa.getBalance());
    }
    @DisplayName("Negative Withdraw")
    @Test
    void negativeWithdraw(){
        assertEquals(INITIAL, aaa.getBalance());
        atm.withdraw(aaa, -200);
        assertEquals(INITIAL, aaa.getBalance());
    }
    @DisplayName("Edge Withdraw")
    @Test
    void edgeWithdraw(){
        assertEquals(INITIAL, aaa.getBalance());
        atm.withdraw(aaa, 0.000001);
        assertEquals(INITIAL, aaa.getBalance());
    }


    @DisplayName("Transfer test")
    @Test
    void transferToCustomer() {            ///NOT working
        assertEquals(INITIAL, aaa.getBalance());
        assertEquals(INITIAL, bbb.getBalance());
        atm.transferToCustomer(aaa,bbb,2000);
        assertEquals(INITIAL-2000, aaa.getBalance());
        assertEquals(INITIAL+2000, bbb.getBalance());
    }

    @Test
    void changePassword() {
        assertEquals("4512", aaa.getPassword());
        System.out.println(aaa.getPassword());
        atm.changePassword(aaa,"7945");
        System.out.println(aaa.getPassword());
        assertTrue("7945".equals(aaa.getPassword())); ///NULL pointer????????
    }
}