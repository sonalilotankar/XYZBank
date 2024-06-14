package com.XYZBank.testcases;

import com.XYZBank.TestFactory.BankManagerLoginTestFactory;
import com.XYZBank.TestFactory.CustLoginTestFactory;
import com.XYZBank.base.TestBase;
import com.XYZBank.dataproviders.DataProviders;
import io.qameta.allure.Allure;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;

public class CustomerLoginTest extends TestBase {

    public CustomerLoginTest() {

        super();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 1, description = "Customer Login Functionality", dataProvider = "XYZBankJourney", dataProviderClass = DataProviders.class)
    public void customerLogin(HashMap<String, String> testData) throws InterruptedException, IOException {
        adminDashboard.getBankmanagerLoginbtn().click();
        new BankManagerLoginTestFactory().addNewCustomer(testData);
        new BankManagerLoginTestFactory().openCustomerAccount(testData);
        adminDashboard.getHomeBtn().click();
        new CustLoginTestFactory().loginUser();


    }
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 2, description = "verified Customer details")
    public void verifyCustomerDetails() throws InterruptedException, IOException {

        new CustLoginTestFactory().verifyloggedUser();
        new CustLoginTestFactory().selectAccNum();

    }
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 3, description = "Deposit Tab functionality", dataProvider = "XYZBankJourney", dataProviderClass = DataProviders.class)
    public void depositTabverification(HashMap<String, String> testData) throws InterruptedException, IOException {

        new CustLoginTestFactory().enterDepositAmount(testData);

    }
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 4, description = "Withdrawl Tab functionality", dataProvider = "XYZBankJourney", dataProviderClass = DataProviders.class)
    public void withDrawlSuccessMsgverification(HashMap<String, String> testData) throws InterruptedException, IOException {

        new CustLoginTestFactory().enterWithdrawlAmount(testData);

    }
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 6, description = "Withdrawl Tab functionality", dataProvider = "XYZBankJourney", dataProviderClass = DataProviders.class)
    public void withDrawlFailureMsgverification(HashMap<String, String> testData) throws InterruptedException, IOException {
        new CustLoginTestFactory().clickonBackBtn();
        new CustLoginTestFactory().enterWithdrawlAmount(testData);

    }
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 5, description = "Verify Transaction table functionality")
    public void verifyTransactionTable() throws InterruptedException, IOException {

        new CustLoginTestFactory().verifytransactionTableHeaders();
        new CustLoginTestFactory().verifytransactionTableData();

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 8, description = "Verify Customers table data sorting functionality")
    public void verifybuttonFunctionality() throws InterruptedException, IOException {
        customerLogin.getTransactionButton().click();
        new CustLoginTestFactory().clickOnResetBtn();
        new CustLoginTestFactory().clickonBackBtn();
        adminDashboard.getLogoutBtn().click();
        Allure.step("Clicked on the Logout button successfully");


    }



}
