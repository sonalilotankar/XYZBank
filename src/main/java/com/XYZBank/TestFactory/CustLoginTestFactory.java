package com.XYZBank.TestFactory;
import com.XYZBank.base.TestBase;
import com.XYZBank.pageObjects.AdminDashboard;
import com.XYZBank.pageObjects.CustomerLogin;
import io.qameta.allure.Allure;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static com.XYZBank.utils.PropertiesUtils.updateConfig;


public class CustLoginTestFactory extends TestBase {
    private static final Logger logger = LogManager.getLogger(CustLoginTestFactory.class);
    AdminDashboard login = new AdminDashboard();

    public CustLoginTestFactory() {

        PageFactory.initElements(getDriver(), this);
    }
    public void loginUser() throws IOException {

        adminDashboard.getCustLoginBtn().click();
        utils.enterText(customerLogin.getCustNameDropdown(), prop.getOutputProperties("app.username"));
        customerLogin.getLoginBtn().click();
        Allure.step("user Logged in successfully from the customer login portal");

    }
    public void verifyloggedUser() throws IOException {

        String actualUsername= customerLogin.getLoggedUserName().getText().trim();
        utils.assertEquals(actualUsername, prop.getOutputProperties("app.username"), "Verified logged username successfully");
        Allure.step("Verified username successfully"+prop.getOutputProperties("app.username"));
        String actualCurrency = customerLogin.getCurrency().getText().trim();
        utils.assertEquals(actualCurrency, prop.getOutputProperties("addCust.currency"), "Verified logged username successfully");
        Allure.step("Verified currency successfully"+prop.getOutputProperties("addCust.currency"));

    }
    public void selectAccNum() throws IOException {

        utils.selectDropdownOptionByText(customerLogin.getAccNoDropdown(), prop.getOutputProperties("openAcc.accountNo"));
        String actualAccNo= customerLogin.getcustomerAccNumber().getText().trim();
        utils.assertEquals(actualAccNo, prop.getOutputProperties("openAcc.accountNo"), "Verified Account number successfully");
        Allure.step("Selected account number from the dropdown"+actualAccNo);
    }
    public void enterWithdrawlAmount(HashMap<String, String> testData) throws IOException {

        customerLogin.getwithdrawButton().click();
        utils.enterText(customerLogin.getAmount(), testData.get("withdrawAmt"));
        Allure.step("Entered Withdrawl amount successfully"+testData.get("withdrawAmt"));
        updateConfig("withdrawl.payment", testData.get("withdrawAmt"));
        customerLogin.getAmount().sendKeys(Keys.ENTER);
        String actualMsg =customerLogin.getErrorMsg().getText().trim();
        if(Integer.valueOf(prop.getOutputProperties("deposit.payment")) >= Integer.valueOf(testData.get("withdrawAmt")) ) {
                utils.assertEquals(actualMsg, prop.getProperties("deposit.successMsg"), "Verified transaction msg successfully");
        } else {
                utils.assertEquals(actualMsg, prop.getProperties("withdraw.errorMsg"), "Verified transaction msg successfully");
        }
    }
    public void enterDepositAmount(HashMap<String, String> testData) throws IOException {

        customerLogin.getDepositButton().click();
        utils.enterText(customerLogin.getAmount(), testData.get("depositAmt"));
        updateConfig("deposit.payment", testData.get("depositAmt"));
        Allure.step("Entered deposit amount successfully"+testData.get("depositAmt"));
        customerLogin.getDepositBtn().click();
        Allure.step("Clicked on the deposit button successfully");
        String actualSuccessmsg =customerLogin.getErrorMsg().getText();
        utils.assertEquals(actualSuccessmsg, prop.getProperties("deposit.successMsg"), "Verified Account number successfully");
        String verifybalance = customerLogin.getbalance().getText().trim();
        if(Integer.valueOf(verifybalance)>=Integer.valueOf(testData.get("depositAmt")) && Integer.valueOf(verifybalance)!=0){
            logger.info("The balance amount is successfully reflected in the balance field");
        }else{
            Assert.fail("The balance amount is zero");
        }

    }

    public void verifytransactionTableHeaders() throws IOException {

        customerLogin.getTransactionButton().click();
        utils.verifyTableData(bankManagerLogin.getTable(), prop.getProperties("transactions.expectedheaders"));
        Allure.step("verified transaction headers successfully"+prop.getProperties("transactions.expectedheaders"));
    }
    public void verifytransactionTableData() throws IOException {
        wait.waitForElementToBeVisibleweb( customerLogin.getTransactionTable(), 10, "Table value populated");
        boolean isDepositAmtPresent = utils.verifyValueInColumn(customerLogin.getTransactionTable(), 2, prop.getOutputProperties("withdrawl.payment"));
        utils.assertTrue(isDepositAmtPresent, "The" + prop.getOutputProperties("withdrawl.payment") + " value is present in the specified column.");
        Allure.step("verified deposit amount from the transactions table");
        boolean isWithdrawAmtPresent = utils.verifyValueInColumn(customerLogin.getTransactionTable(), 2, prop.getOutputProperties("deposit.payment"));
        utils.assertTrue(isWithdrawAmtPresent, "The" + prop.getOutputProperties("deposit.payment") + " value is present in the specified column.");
        Allure.step("verified withdrawl amount from the transactions table");

    }
    public void clickonBackBtn() throws IOException {
        customerLogin.getBackbtn().click();
        Allure.step("Clicked on the back button successfully");

    }
    public boolean clickOnResetBtn() throws IOException {
        customerLogin.getResetBtn().click();
        Allure.step("Clicked on the Reset button successfully");
        List<WebElement> rows = getDriver().findElements(By.xpath("//table[@class=\"table table-bordered table-striped\"]//tbody"));
        // If there are no rows, the table is empty
        return rows.size() == 0;
    }




}
