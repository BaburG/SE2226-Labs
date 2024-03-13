package week5;

import org.junit.platform.suite.api.*;
import week4.ATM.DepositLimitTest;

@Suite
@SuiteDisplayName("Week 4 Test Cases")
//@SelectPackages("week4")
//Make the test class "public" if you can not add its name
//@SelectClasses({DepositLimitTest.class, ATMTestMoodle.class})
@SelectClasses({DepositLimitTest.class})
public class MultiTestSuit {
}