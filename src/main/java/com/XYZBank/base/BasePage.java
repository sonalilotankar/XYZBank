package com.XYZBank.base;

import Utils.ExcelUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import java.io.IOException;


import static com.XYZBank.base.TestBase.prop;

public class BasePage {

    protected static final ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    public static String parameterName;
    public static String downloadFolder;

    static void setDriver() throws IOException {

        parameterName = ExcelUtils.getPlatformNameFromExcel(prop.getProperties("data.excel"), "controller");


        if (parameterName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver.set(new FirefoxDriver());

        } else if (parameterName.equalsIgnoreCase("chrome")) {
            if(prop.getProperties("headless.mode").equalsIgnoreCase("YES")){

                ChromeOptions options = new ChromeOptions();
                options.addArguments("headless");
                options.addArguments("window-size=1920,1080");
                driver.set(new ChromeDriver(options));
            }else {
                driver.set(new ChromeDriver());
            }



        } else if (parameterName.equalsIgnoreCase("Safari")) {

            WebDriverManager.safaridriver().setup();
            driver.set(new SafariDriver());
        } else {

            System.out.println("Invalid Browser Name ");


        }


    }

    public static WebDriver getDriver() {

        return driver.get();
    }


}