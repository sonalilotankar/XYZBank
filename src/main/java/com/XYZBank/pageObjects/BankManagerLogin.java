package com.XYZBank.pageObjects;
import com.XYZBank.base.TestBase;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BankManagerLogin extends TestBase {
    public BankManagerLogin() {
        PageFactory.initElements(getDriver(), this);
    }

    //Add customer Button
    @FindBy(xpath = "//div[@class=\"center\"]//button[@ng-class=\"btnClass1\"]")
    private WebElement addCustBtn;
    @Step("Add customer button")
    public WebElement getaddCustBtn() {
        return addCustBtn;
    }

    //Open Account

    @FindBy(xpath = "//div[@class=\"center\"]//button[@ng-class=\"btnClass2\"]")
    private WebElement openAccBtn;
    @Step("Add customer button")
    public WebElement getopenAccBtn() {
        return openAccBtn;
    }

    //Customers Button
    @FindBy(xpath = "//div[@class=\"center\"]//button[@ng-class=\"btnClass3\"]")
    private WebElement customersBtn;
    @Step("Add customer button")
    public WebElement getcustomersBtn() {
        return customersBtn;
    }

    //Add customer - form fillup process
    //First name
    @FindBy(xpath = "//form[@name=\"myForm\"]//div//input[@placeholder=\"First Name\"]")
    private WebElement inputFName;
    @Step("First name input field")
    public WebElement getInputFName() {
        return inputFName;
    }
    //Last name
    @FindBy(xpath = "//form[@name=\"myForm\"]//div//input[@placeholder=\"Last Name\"]")
    private WebElement inputLName;
    @Step("last name input field")
    public WebElement getInputLName() {
        return inputLName;
    }
    //Post code
    @FindBy(xpath = "//form[@name=\"myForm\"]//div//input[@ng-model=\"postCd\"]")
    private WebElement inputPostCode;
    @Step("post code input field")
    public WebElement getInputPostCode() {
        return inputPostCode;
    }
    //Add Customer - submit button
    @FindBy(xpath = "//form[@name=\"myForm\"]//button")
    private WebElement addCustSubmitBtn;
    @Step("Add customer submit button")
    public WebElement getaddCustSubmitBtn() {
        return addCustSubmitBtn;
    }

    // Open Account
    //Customer
    @FindBy(id = "userSelect")
    private WebElement custDropdown;
    @Step("Customer dropdown")
    public WebElement getCustDropdown() {
        return custDropdown;
    }

    //Currency
    @FindBy(id = "currency")
    private WebElement currencyDropdown;
    @Step("Currency Dropdown")
    public WebElement getCurrencyDropdown() {
        return currencyDropdown;
    }
    //Process button
    @FindBy(xpath = "//button[text()=\"Process\"]")
    private WebElement processBtn;
    @Step("Process button")
    public WebElement getprocessBtn() {
        return processBtn;
    }

    //Customers
    //Search Customer bar
    @FindBy(xpath = "//div//input[@placeholder=\"Search Customer\"]")
    private WebElement searchCustBtn;
    @Step("Process button")
    public WebElement getSearchCustBtn() {
        return searchCustBtn;
    }

    //table

    @FindBy(xpath = "//table[@class=\"table table-bordered table-striped\"]")
    private WebElement table;
    @Step("Process button")
    public WebElement getTable() {
        return table;
    }



}
