package com.XYZBank.pageObjects;

import com.XYZBank.base.TestBase;
import io.qameta.allure.Step;
import org.apache.tools.ant.taskdefs.SQLExec;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CustomerLogin extends TestBase {
    public CustomerLogin() {
        PageFactory.initElements(getDriver(), this);
    }
    //Customer Login
    //Your name
    @FindBy(id = "userSelect")
    private WebElement custNameDropdown;
    @Step("Customer name dropdown")
    public WebElement getCustNameDropdown() {
        return custNameDropdown;
    }

    //Login button
    @FindBy(xpath = "//button[text()=\"Login\"]")
    private WebElement loginBtn;
    @Step("Login button")
    public WebElement getLoginBtn() {
        return loginBtn;
    }

    //User Name
    @FindBy(xpath = "//div//strong//span[@class=\"fontBig ng-binding\"]")
    private WebElement loggedUserName;
    @Step("Customer Username")
    public WebElement getLoggedUserName() {
        return loggedUserName;
    }

    //Account number dropdown
    @FindBy(xpath = "//select[@ng-hide=\"noAccount\"]")
    private WebElement accNoDropdown;
    @Step("Customer Account Number dropdown")
    public WebElement getAccNoDropdown() {
        return accNoDropdown;
    }

    //Account number
    @FindBy(xpath = "//div[@ng-hide=\"noAccount\"]//Strong[1]")
    private WebElement customerAccNumber;
    @Step("Customer Account number")
    public WebElement getcustomerAccNumber() {
        return customerAccNumber;
    }

    //balance
    @FindBy(xpath = "//div[@ng-hide=\"noAccount\"]//Strong[2]")
    private WebElement balance;
    @Step("Customer balance")
    public WebElement getbalance() {
        return balance;
    }

    //Currency
    @FindBy(xpath = "//div[@ng-hide=\"noAccount\"]//Strong[3]")
    private WebElement currency;
    @Step("Customer balance")
    public WebElement getCurrency() {
        return currency;
    }


    //Transaction button
    @FindBy(xpath = "//div[@ng-hide=\"noAccount\"]//button[@ng-class=\"btnClass1\"]")
    private WebElement transactionButton;
    @Step("Transaction Button")
    public WebElement getTransactionButton() {
        return transactionButton;
    }

    //Deposit button
    @FindBy(xpath = "//div[@ng-hide=\"noAccount\"]//button[@ng-class=\"btnClass2\"]")
    private WebElement depositButton;
    @Step("balance Button")
    public WebElement getDepositButton() {
        return depositButton;
    }

    //Withdraw Button
    @FindBy(xpath = "//div[@ng-hide=\"noAccount\"]//button[@ng-class=\"btnClass3\"]")
    private WebElement withdrawButton;
    @Step("withdraw button")
    public WebElement getwithdrawButton() {
        return withdrawButton;
    }


    //Transaction page operations
    @FindBy(xpath = "//table[@class=\"table table-bordered table-striped\"]")
    private WebElement transactionTable;
    @Step("Transaction table")
    public WebElement getTransactionTable() {
        return transactionTable;
    }

    @FindBy(xpath = "//button[text()=\"Reset\"]")
    private WebElement resetBtn;
    @Step("Reset button")
    public WebElement getResetBtn() {
        return resetBtn;
    }

    @FindBy(xpath = "//button[text()=\"Back\"]")
    private WebElement backbtn;
    @Step("withdraw button")
    public WebElement getBackbtn() {
        return backbtn;
    }


    //Deposit Button & Withdrawl button

    @FindBy(xpath = "//input[@ng-model=\"amount\"]")
    private WebElement amount;
    @Step("input amount")
    public WebElement getAmount() {
        return amount;
    }

    @FindBy(xpath = "//button[text()=\"Withdraw\"]")
    private WebElement withdrawbtn;
    @Step("withdraw button")
    public WebElement getWithdrawBtn() {
        return withdrawbtn;
    }

    @FindBy(xpath = "//button[text()=\"Deposit\"]")
    private WebElement depositbtn;
    @Step("withdraw button")
    public WebElement getDepositBtn() {
        return depositbtn;
    }

    @FindBy(xpath = "//div//span[@class=\"error ng-binding\"]")
    private WebElement errormsg;
    @Step("withdraw button")
    public WebElement getErrorMsg() {
        return errormsg;
    }













}
