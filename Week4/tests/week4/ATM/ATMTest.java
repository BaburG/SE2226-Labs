package week4.ATM;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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

    @DisplayName("Negative Transfer Test")
    @Test
    void negativeTransferToCustomer() {            ///NOT working
        assertEquals(INITIAL, aaa.getBalance());
        assertEquals(INITIAL, bbb.getBalance());
        atm.transferToCustomer(aaa,bbb,-2000);
        assertEquals(INITIAL, aaa.getBalance());
        assertEquals(INITIAL, bbb.getBalance());
    }

    @DisplayName("Zero Transfer Test")
    @Test
    void zeroTransferToCustomer() {            ///NOT working
        assertEquals(INITIAL, aaa.getBalance());
        assertEquals(INITIAL, bbb.getBalance());
        atm.transferToCustomer(aaa,bbb,0);
        assertEquals(INITIAL, aaa.getBalance());
        assertEquals(INITIAL, bbb.getBalance());
    }

    @DisplayName("Change password Test")
    @Test
    void changePassword() {
        assertEquals("4512", aaa.getPassword());
        atm.changePassword(aaa,"7945");
        assertTrue("7945".equals(aaa.getPassword()));
    }

    @DisplayName("3 digit password Test")
    @Test
    void shortPasswordTest(){
        int previousPasswordsSize = aaa.getPasswords().size();
        //Test short password
        assertEquals("4512",aaa.getPassword());
        atm.changePassword(aaa,"159");
        assertEquals("4512",aaa.getPassword());
        //check it is not added to previous passwords
        assertEquals(previousPasswordsSize, aaa.getPasswords().size());
    }

    @DisplayName("Long Password Test")
    @Test
    void longPasswordTest(){
        int previousPasswordsSize = aaa.getPasswords().size();
        //Test long password
        assertEquals("4512",aaa.getPassword());
        atm.changePassword(aaa,"15928");
        assertEquals("4512",aaa.getPassword());
        //check it is not added to previous passwords
        assertEquals(previousPasswordsSize, aaa.getPasswords().size());
    }


    @DisplayName("Test passwords between 1900 and 2000")
    @Test
    void yearPasswordTest(){
        for (int i = 0; i <= 100; i++){
            int previousPasswordsSize = aaa.getPasswords().size();
            //Test password
            assertEquals("4512",aaa.getPassword());
            atm.changePassword(aaa,String.valueOf(1900+i));
            assertEquals("4512",aaa.getPassword());
            //check it is not added to previous passwords
            assertEquals(previousPasswordsSize, aaa.getPasswords().size());
        }
    }

    @DisplayName("Test repeating number passwords")
    @Test
    void reaptingNumberPasswordTest(){
        for (int i = 0; i < 10; i++){
            int previousPasswordsSize = aaa.getPasswords().size();
            //Test password
            assertEquals("4512",aaa.getPassword());
            atm.changePassword(aaa,String.valueOf(i+i*10+i*100+i*1000));
            assertEquals("4512",aaa.getPassword());
            //check it is not added to previous passwords
            assertEquals(previousPasswordsSize, aaa.getPasswords().size());
        }
    }

    @DisplayName("Test 2 digits repeating passwords")
    @Test
    void twoDigitRepeatingPasswordTest(){ //Pass
        for (int i = 10; i < 100; i++){
            int previousPasswordsSize = aaa.getPasswords().size();
            //Test password
            assertEquals("4512",aaa.getPassword());
            atm.changePassword(aaa,String.valueOf(i+i*100));
            assertEquals("4512",aaa.getPassword());
            //check it is not added to previous passwords
            assertEquals(previousPasswordsSize, aaa.getPasswords().size());
        }
    }


    @DisplayName("Non Numeric Password Test")
    @ParameterizedTest
    @ValueSource(strings  = {"akgk", "@$*@", "1h* "})
    void nonNumericPasswordTest(String password){
        int previousPasswordsSize = aaa.getPasswords().size();
        //Test long password
        assertEquals("4512",aaa.getPassword());
        atm.changePassword(aaa,password);
        assertEquals("4512",aaa.getPassword());
        //check it is not added to previous passwords
        assertEquals(previousPasswordsSize, aaa.getPasswords().size());
    }

    @DisplayName("Repeating Password Test")
    @Test
    void reapeatedPasswordTest(){
        int previousPasswordsSize = aaa.getPasswords().size();
        String passwords[]  = {"9021", "8493", "4512"};
        for (String password : passwords) {
            atm.changePassword(aaa, password);
            if (!password.equals("4512")) {
                assertTrue(password.equals(aaa.getPassword()));
                assertEquals(previousPasswordsSize == 3 ? 3 : previousPasswordsSize + 1, aaa.getPasswords().size());
            } else { //fail if in last three
                assertTrue(aaa.getPassword().compareTo("8493") == 0);
                assertEquals(3, aaa.getPasswords().size());
            }
        }
    }
}