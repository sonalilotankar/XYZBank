package com.XYZBank.Listner;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;

import static com.XYZBank.base.BasePage.getDriver;

public class AllureListener implements ITestListener {

    private static void attachScreenshot(byte[] screenshot) {
        Allure.addAttachment("Screenshot", new ByteArrayInputStream(screenshot));
    }

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        System.out.println("Starting Test Suite '" + iTestContext.getName() + "'.......");
        iTestContext.setAttribute("WebDriver", getDriver());
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        System.out.println("Finished Test Suite '" + iTestContext.getName() + "'");
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("Starting Test Method '" + getTestMethodName(iTestResult) + "'");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("Test Method '" + getTestMethodName(iTestResult) + "' is Passed");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("Test Method '" + getTestMethodName(iTestResult) + "' is Failed");
        if (getDriver() != null) {
            System.out.println("Screenshot has been captured for the Test Method '" + getTestMethodName(iTestResult) + "'");
            byte[] screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
            attachScreenshot(screenshot);
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("Test Method '" + getTestMethodName(iTestResult) + "' is Skipped");
        if (getDriver() != null) {
            System.out.println("Screenshot has been captured for the Test Method '" + getTestMethodName(iTestResult) + "'");
            byte[] screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
            attachScreenshot(screenshot);
        }
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        if (getDriver() != null) {
            System.out.println("Screenshot has been captured for the Test Method '" + getTestMethodName(iTestResult) + "'");
            byte[] screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
            attachScreenshot(screenshot);
        }
    }
}