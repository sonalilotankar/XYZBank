package com.XYZBank.base;

import com.XYZBank.pageObjects.BankManagerLogin;
import com.XYZBank.pageObjects.CustomerLogin;
import com.XYZBank.pageObjects.AdminDashboard;
import com.XYZBank.utils.TestUtils;
import com.XYZBank.utils.PropertiesUtils;
import com.XYZBank.utils.WaitUtils;
import org.testng.annotations.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

import static com.XYZBank.utils.TestUtils.removeTempFolders;


public class TestBase extends BasePage {

    public static PropertiesUtils prop;
    public static TestUtils utils;
    public static AdminDashboard adminDashboard;
    public static CustomerLogin customerLogin;
    public static BankManagerLogin bankManagerLogin;
    public static WaitUtils wait;



    public TestBase() {
        try {

            prop = new PropertiesUtils();
            prop.getProperties("data.excel");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void initialization() throws IOException, InterruptedException {

        BasePage.setDriver();
        System.out.println("Browser setup by Thread " + Thread.currentThread().getId() + " and Driver reference is : " + getDriver());
        getDriver().get(prop.getProperties("base.url"));
        Thread.sleep(1500);
        Thread.sleep(3000);
        System.out.println("Title printed by Thread " + Thread.currentThread().getId() + " - " + getDriver().getTitle() + " on driver reference " + getDriver());
        getDriver().manage().deleteAllCookies();
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        Thread.sleep(3000);

    }

    @BeforeSuite
    public void removeTempData() {
        removeTempFolders();
        System.out.println("Temprory folders for previous run removed");
    }

    @BeforeClass
    public void setUp() {

        utils = new TestUtils();
        adminDashboard = new AdminDashboard();
        customerLogin = new CustomerLogin();
        bankManagerLogin = new BankManagerLogin();
        wait = new WaitUtils();

    }

    @BeforeClass
    public void preSetup() throws IOException, InterruptedException {
        initialization();
    }


    @AfterClass
    public void tearDown() {

        BasePage.getDriver().quit();
    }


}