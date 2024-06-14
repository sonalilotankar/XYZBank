package com.XYZBank.utils;

import io.qameta.allure.Allure;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.NoSuchElementException;


import static com.XYZBank.base.BasePage.getDriver;

public class WaitUtils {


    public static void waitforElementToBeClickable(WebElement element, int timeout) {

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.elementToBeClickable(element));

    }
    public static void waitForElementToBeVisibleweb( WebElement element, int timeOut, String message) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeOut));
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (NoSuchElementException e) {
            Allure.addAttachment(message, "is having no such Element Exception");
        } catch (TimeoutException e) {
            Allure.addAttachment(message, "is having no Timeout Exception");
        }
    }

    //Fluent wait
    public static WebElement fluentWaitForElement( WebElement element, int timeoutInSeconds, int pollingIntervalInSeconds) {
        Wait<WebDriver> wait = new FluentWait<>(getDriver())
                .withTimeout(Duration.ofSeconds(timeoutInSeconds))
                .pollingEvery(Duration.ofSeconds(pollingIntervalInSeconds))
                .ignoring(org.openqa.selenium.NoSuchElementException.class);
        return wait.until((ExpectedCondition<WebElement>) webDriver -> element.isDisplayed() ? element : null);
    }

}
