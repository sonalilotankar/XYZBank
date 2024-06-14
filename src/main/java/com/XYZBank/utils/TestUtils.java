package com.XYZBank.utils;

import com.XYZBank.base.TestBase;
import org.apache.log4j.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyEvent;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestUtils extends TestBase {

    public static SoftAssert softAssert = new SoftAssert();

    public TestUtils() {
        PageFactory.initElements(getDriver(), this);
    }

    //logger
    public static void configure() {
        Logger root = Logger.getRootLogger();
        root.addAppender(new ConsoleAppender(new PatternLayout("%r [%t] %p %c %x - %m%n")));
    }

    // Enter the text
    public static void enterText(WebElement element, String textToEnter) {
        try {
            // Use sendKeys to enter text
            element.sendKeys(textToEnter);

        } catch (Exception sendKeysException) {
            // If sendKeys fails, use JavascriptExecutor as a backup
            JavascriptExecutor js = (JavascriptExecutor) getDriver();
            js.executeScript("arguments[0].value='" + textToEnter + "';", element);

        }
    }

    //Method for Assertion - assertEquals, assertTrue, assertFalse, handleAssertionFailure
    public static void assertEquals(String actual, String expected, String message) {
        BasicConfigurator.configure();
        try {

            softAssert.assertEquals(actual, expected, message);

        } catch (AssertionError e) {

            handleAssertionFailure(e);
        }
        softAssert.assertAll();
    }

    public static void assertTrue(boolean condition, String message) {
        BasicConfigurator.configure();
        try {
            softAssert.assertTrue(condition, message);

        } catch (AssertionError e) {
            handleAssertionFailure(e);
        }
        softAssert.assertAll();

    }

    private static void handleAssertionFailure(AssertionError e) {
        BasicConfigurator.configure();
        e.printStackTrace();
    }

    // Select value from the dropdown - Select tag
    public static void selectDropdownOptionByText(WebElement element, String optionText) {
        wait.waitforElementToBeClickable(element, 30);
        Select dropdown = new Select(element);
        List<WebElement> options = dropdown.getOptions();
        if (options.isEmpty()) {
            Assert.assertTrue(options.isEmpty());
        } else {

            dropdown.selectByVisibleText(optionText);
        }
    }

    public static boolean isAlertPresent(int timedout) {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timedout));
            wait.until(ExpectedConditions.alertIsPresent());
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    // Method to verify Table headers
    public static void verifyTableData(WebElement table, String expectedHeaders) {
        // Verify table headers
        verifyTableHeaders(table, expectedHeaders);


    }

    //Verify the table headers - table->thead->tr->td
    public static void verifyTableHeaders(WebElement table, String expectedHeaders) {

        WebElement headerRow = table.findElement(By.tagName("thead")).findElement(By.tagName("tr"));
        String[] expectedHeadersArray = expectedHeaders.split(",");
        for (int i = 0; i < expectedHeadersArray.length; i++) {
            WebElement headerCell = headerRow.findElements(By.tagName("td")).get(i);
            String actualHeader = headerCell.getText().trim();
            String expectedHeader = expectedHeadersArray[i].trim();

            // Perform the verification
            assert actualHeader.equalsIgnoreCase(expectedHeader) : "Header verification failed for column: " + expectedHeader;
        }
    }

    //Verify value in column
    public static boolean verifyValueInColumn(WebElement table, int targetColumn, String targetValue) {

        List<WebElement> rows = table.findElements(By.tagName("tr"));


        for (WebElement row : rows) {

            List<WebElement> cells = row.findElements(By.tagName("td"));


            if (cells.size() >= targetColumn) {

                String cellText = cells.get(targetColumn - 1).getText();


                if (cellText.equals(targetValue)) {

                    return true;
                }
            }
        }


        return false;
    }

    public static void deleteRowBySearchField(WebElement table, String searchField) {

        List<WebElement> rows = table.findElements(By.tagName("tr"));


        for (WebElement row : rows) {

            List<WebElement> cells = row.findElements(By.tagName("td"));


            if (cells.size() >= 5) {

                String cellText = cells.get(0).getText();


                if (cellText.equals(searchField)) {

                    cells.get(4).findElement(By.tagName("button")).click();
                    break;
                }
            }
        }
    }

    public static boolean verifySearchFieldNotPresent(WebElement table, String searchField) {

        List<WebElement> rows = table.findElements(By.tagName("tr"));


        for (WebElement row : rows) {

            List<WebElement> cells = row.findElements(By.tagName("td"));


            for (WebElement cell : cells) {

                if (cell.getText().contains(searchField)) {

                    return false;
                }
            }
        }

        // If search field not found in any cell, return true
        return true;
    }

    public static void removeTempFolders() {
        String userDir = System.getProperty("user.dir");
        String allureResults = userDir + File.separator + "allure-results";

        deleteFolder(allureResults);

    }

    private static void deleteFolder(String folderPath) {
        File folder = new File(folderPath);

        if (!folder.exists()) {
            return;
        }

        if (!folder.isDirectory()) {
            System.out.println("Given path is not a folder: " + folderPath);
            return;
        }

        deleteFolderContents(folder);
    }

    private static void deleteFolderContents(File folder) {
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteFolderContents(file);
                } else {
                    file.delete();
                }
            }
        }
        folder.delete();
        System.out.println("Folder deleted: " + folder.getAbsolutePath());
    }


}

