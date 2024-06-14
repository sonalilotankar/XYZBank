package com.XYZBank.TestFactory;

import com.XYZBank.base.TestBase;
import com.XYZBank.pageObjects.AdminDashboard;

import com.XYZBank.utils.TestUtils;
import io.qameta.allure.Allure;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.io.IOException;
import java.util.HashMap;


import static com.XYZBank.utils.PropertiesUtils.updateConfig;
import static com.XYZBank.utils.TestUtils.enterText;

public class BankManagerLoginTestFactory extends TestBase {
    private static final Logger logger = LogManager.getLogger(BankManagerLoginTestFactory.class);
    AdminDashboard login = new AdminDashboard();

    public BankManagerLoginTestFactory() {

        PageFactory.initElements(getDriver(), this);
    }
    public void addNewCustomer(HashMap<String, String> testData) throws IOException {

        bankManagerLogin.getaddCustBtn().click();
        Allure.step("Clicked on the Add customer button");
        enterText(bankManagerLogin.getInputFName(), testData.get("firstname"));
        Allure.step("Entered first name "+testData.get("firstname"));
        enterText(bankManagerLogin.getInputLName(), testData.get("lastname"));
        Allure.step("Entered last name "+testData.get("lastname"));
        String fullname = testData.get("firstname")+" "+testData.get("lastname");
        updateConfig("app.username", fullname);
        enterText(bankManagerLogin.getInputPostCode(), testData.get("postcode"));
        Allure.step("Entered Post code "+testData.get("postcode"));
        bankManagerLogin.getaddCustSubmitBtn().click();
        Allure.step("Clicked on the Add customer button to submit the form ");
        utils.isAlertPresent(10);
        String alertMsg =getDriver().switchTo().alert().getText();
        String[] arrOfStr = alertMsg.split(":");
        updateConfig("addCust.customerId", arrOfStr[1]);
        getDriver().switchTo().alert().accept();
        Allure.step("Alert message verified successfully!!");
    }
    public void openCustomerAccount(HashMap<String, String> testData) throws IOException {

        bankManagerLogin.getopenAccBtn().click();
        utils.selectDropdownOptionByText(bankManagerLogin.getCustDropdown(), prop.getOutputProperties("app.username"));
        Allure.step("Username is selected from the dropdown "+prop.getOutputProperties("app.username"));
        utils.selectDropdownOptionByText(bankManagerLogin.getCurrencyDropdown(), testData.get("currency"));
        Allure.step("Currency is selected from the dropdown "+testData.get("currency"));
        updateConfig("addCust.currency", testData.get("currency"));
        bankManagerLogin.getprocessBtn().click();
        Allure.step("Clicked on the success button successfully!");
        utils.isAlertPresent(10);
        String alertMsg =getDriver().switchTo().alert().getText();
        String[] arrOfStr = alertMsg.split(":");
        updateConfig("openAcc.accountNo", arrOfStr[1]);
        getDriver().switchTo().alert().accept();
        Allure.step("Alert message verified successfully!!");
    }

    public void verifyCustomersheader() throws IOException {

        bankManagerLogin.getcustomersBtn().click();
        utils.verifyTableData(bankManagerLogin.getTable(), prop.getProperties("customers.expectedheaders"));
        Allure.step("Customers header successfully verified!!"+prop.getProperties("customers.expectedheaders"));

    }
    public void searchCustomer(HashMap<String, String> testData) throws IOException {

        bankManagerLogin.getcustomersBtn().click();
        enterText(bankManagerLogin.getSearchCustBtn(),testData.get("firstname"));
        Allure.step("Entered first name "+testData.get("firstname"));
        boolean isFnamePresent = utils.verifyValueInColumn(bankManagerLogin.getTable(), 1, testData.get("firstname"));
        if (isFnamePresent) {
            utils.assertTrue(isFnamePresent, "The"+ testData.get("firstname") +" value is present in the specified column.");
        } else {
            Assert.fail("The value is not present in the specified column." +testData.get("firstname"));
        }
        bankManagerLogin.getSearchCustBtn().clear();
        enterText(bankManagerLogin.getSearchCustBtn(),testData.get("lastname"));
        Allure.step("Entered last name "+testData.get("lastname"));
        boolean isLnamePresent = utils.verifyValueInColumn(bankManagerLogin.getTable(), 2, testData.get("lastname"));
        if (isLnamePresent) {
            utils.assertTrue(isLnamePresent, "The"+ testData.get("lastname") +" value is present in the specified column.");
        } else {
            Assert.fail("The value is not present in the specified column." +testData.get("lastname"));
        }
        bankManagerLogin.getSearchCustBtn().clear();
        enterText(bankManagerLogin.getSearchCustBtn(),testData.get("postcode"));
        Allure.step("Entered Post code "+testData.get("postcode"));
        boolean isPostCodePresent = utils.verifyValueInColumn(bankManagerLogin.getTable(), 3, testData.get("postcode"));
        if (isPostCodePresent) {
            utils.assertTrue(isPostCodePresent, "The"+ testData.get("postcode") +" value is present in the specified column.");
        } else {
            Assert.fail("The value is not present in the specified column." +testData.get("postcode"));
        }
        bankManagerLogin.getSearchCustBtn().clear();
        enterText(bankManagerLogin.getSearchCustBtn(), prop.getOutputProperties("openAcc.accountNo"));
        Allure.step("Entered Account Number "+prop.getOutputProperties("openAcc.accountNo"));
        boolean isAccNoPresent = utils.verifyValueInColumn(bankManagerLogin.getTable(), 4, prop.getOutputProperties("openAcc.accountNo"));
        if (isAccNoPresent) {
            utils.assertTrue(isAccNoPresent, "The"+ testData.get("openAcc.accountNo") +" value is present in the specified column.");
        } else {
            Assert.fail("The value is not present in the specified column." +prop.getOutputProperties("openAcc.accountNo"));
        }

    }

    public void deleteCustomer(HashMap<String, String> testData) throws IOException {

        bankManagerLogin.getcustomersBtn().click();
        enterText(bankManagerLogin.getSearchCustBtn(),testData.get("firstname"));
        utils.deleteRowBySearchField(bankManagerLogin.getTable(),testData.get("firstname"));
        Allure.step("User deleted successfully from the table");

        boolean isSearchFieldNotPresent = utils.verifySearchFieldNotPresent(bankManagerLogin.getTable(), testData.get("firstname"));
        logger.info("Is search field not present in any column? " + isSearchFieldNotPresent);


    }

    public void verifySortingFirstnameFrmTable() throws IOException {
        bankManagerLogin.getcustomersBtn().click();
        WebElement firstunsortedrow = getDriver().findElement(By.xpath("(//table[@class='table table-bordered table-striped']//tbody//tr//td)[1]"));
        String firstTwoCharsFirstString =firstunsortedrow.getText().substring(0, 2);
        WebElement firstheader = getDriver().findElement(By.xpath("(//table[@class=\"table table-bordered table-striped\"]//thead//tr//td//a)[1]"));
        firstheader.click();
        WebElement firstsortedrow = getDriver().findElement(By.xpath("(//table[@class='table table-bordered table-striped']//tbody//tr//td)[1]"));
        String firstTwoCharsSecondString = firstsortedrow.getText().substring(0, 2);
        int comparisonResult = firstTwoCharsFirstString.compareTo(firstTwoCharsSecondString);
        if (comparisonResult > 0) {
            Assert.assertTrue(Boolean.parseBoolean("Second string is smaller based on the first two characters."));
        } else if (comparisonResult < 0) {
            Assert.fail("First string is smaller based on the first two characters.");
        }else{
            Assert.assertEquals(comparisonResult, 0, "Both strings are equal based on the first two characters.");
        }
        Allure.step("Column First name sorted successfully");


    }
    public void verifySortingLastnameFrmTable() throws IOException {
        bankManagerLogin.getcustomersBtn().click();
        WebElement lastunsortedrow = getDriver().findElement(By.xpath("(//table[@class='table table-bordered table-striped']//tbody//tr//td)[2]"));
        String lastTwoCharsFirstString =lastunsortedrow.getText().substring(0, 2);
        WebElement lastheader = getDriver().findElement(By.xpath("(//table[@class=\"table table-bordered table-striped\"]//thead//tr//td//a)[2]"));
        lastheader.click();
        WebElement lastsortedrow = getDriver().findElement(By.xpath("(//table[@class='table table-bordered table-striped']//tbody//tr//td)[2]"));
        String lastTwoCharsSecondString = lastsortedrow.getText().substring(0, 2);
        int comparisonResult = lastTwoCharsFirstString.compareTo(lastTwoCharsSecondString);
        if (comparisonResult > 0) {
            Assert.assertTrue(Boolean.parseBoolean("Second string is smaller based on the first two characters."));
        } else if (comparisonResult < 0) {
            Assert.fail("First string is smaller based on the first two characters.");
        }else{
            Assert.assertEquals(comparisonResult, 0, "Both strings are equal based on the first two characters.");
        }
        Allure.step("Column last name sorted successfully");


    }




    }
