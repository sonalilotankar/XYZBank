package com.XYZBank.pageObjects;

import com.XYZBank.base.TestBase;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminDashboard extends TestBase {

    //Admin Dashboard
    @FindBy(xpath = "//div[@class=\"box mainhdr\"]//button[@class=\"btn home\"]")
    private WebElement homeBtn;
    @Step("Home button")
    public WebElement getHomeBtn() {
        return homeBtn;
    }

    //customer login button
    @FindBy(xpath = "//button[text()=\"Customer Login\"]")
    private WebElement custLoginBtn;
    @Step("Customer Login Button")
    public WebElement getCustLoginBtn() {

        return custLoginBtn;
    }

    //Bank Manager Login Button
    @FindBy(xpath = "//button[text()=\"Bank Manager Login\"]")
    private WebElement bankmanagerLoginbtn;
    @Step("Bank Manager Login Button")
    public WebElement getBankmanagerLoginbtn() {

        return bankmanagerLoginbtn;
    }

    //Bank Name Header
    @FindBy(xpath = "//div[@class=\"box mainhdr\"]//strong[@class=\"mainHeading\"]")
    private WebElement banknameHeader;
    @Step("Bank Name Header")
    public WebElement getBanknameHeader() {

        return banknameHeader;
    }

    //Logout button
    @FindBy(xpath = "//button[text()=\"Logout\"]")
    private WebElement logoutBtn;
    @Step("Bank Name Header")
    public WebElement getLogoutBtn() {

        return logoutBtn;
    }


    public AdminDashboard() {
        PageFactory.initElements(getDriver(), this);
    }



}
