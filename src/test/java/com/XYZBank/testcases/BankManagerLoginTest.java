package com.XYZBank.testcases;

import com.XYZBank.TestFactory.BankManagerLoginTestFactory;
import com.XYZBank.base.TestBase;
import com.XYZBank.dataproviders.DataProviders;
import com.XYZBank.utils.TestUtils;
import com.XYZBank.utils.WaitUtils;
import io.qameta.allure.Allure;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;

public class BankManagerLoginTest extends TestBase {

    public BankManagerLoginTest() {

        super();
    }
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 1, description = "Verify the bank name")
    public void verifyButton(){
        adminDashboard.getBankmanagerLoginbtn().click();

        String actualBtn1=bankManagerLogin.getaddCustBtn().getText().trim();
        utils.assertEquals(actualBtn1, "Add Customer", "Verified button name - Add customer");
        Allure.step("verified bank name" +actualBtn1);


        String actualBtn2=bankManagerLogin.getopenAccBtn().getText().trim();
        utils.assertEquals(actualBtn2, "Open Account", "Verified button name - Open Account");
        Allure.step("verified bank name" +actualBtn2);


        String actualBtn3=bankManagerLogin.getcustomersBtn().getText().trim();
        utils.assertEquals(actualBtn3, "Customers", "Verified button name - Customers");
        Allure.step("verified bank name" +actualBtn3);

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 2, description = "Add new customer", dataProvider = "XYZBankJourney", dataProviderClass = DataProviders.class)
    public void createNewCustomer(HashMap<String, String> testData) throws InterruptedException, IOException {

        new BankManagerLoginTestFactory().addNewCustomer(testData);
    }
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 3, description = "Open New account", dataProvider = "XYZBankJourney", dataProviderClass = DataProviders.class)
    public void openCustomerAcc(HashMap<String, String> testData) throws InterruptedException, IOException {

        new BankManagerLoginTestFactory().openCustomerAccount(testData);
    }
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 4, description = "Verify Customers table data", dataProvider = "XYZBankJourney", dataProviderClass = DataProviders.class)
    public void customerstableVerification(HashMap<String, String> testData) throws InterruptedException, IOException {

        new BankManagerLoginTestFactory().verifyCustomersheader();
        new BankManagerLoginTestFactory().searchCustomer(testData);

    }
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 5, description = "Verify Customers table data sorting functionality")
    public void verifyCustomerSorting() throws InterruptedException, IOException {

        new BankManagerLoginTestFactory().verifySortingFirstnameFrmTable();
        new BankManagerLoginTestFactory().verifySortingLastnameFrmTable();

    }
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 6, description = "Verify deleted Customer Functionality", dataProvider = "XYZBankJourney", dataProviderClass = DataProviders.class)
    public void verifyDeletedUser(HashMap<String, String> testData) throws InterruptedException, IOException {
        new BankManagerLoginTestFactory().deleteCustomer(testData);
    }


}
