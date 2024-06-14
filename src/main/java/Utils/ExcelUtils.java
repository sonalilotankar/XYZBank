package Utils;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtils {


    public static String getPlatformNameFromExcel(String testExcelSheet, String sheetName) {
        String platform = null;

        Xls_reader reader = null;
        try {
            reader = new Xls_reader(System.getProperty("user.dir") + testExcelSheet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int rowCount = 2; rowCount <= reader.getRowCount(sheetName); rowCount++) {
            String enableFlag = reader.getCellData(sheetName, "execute", rowCount);
            if (enableFlag.equalsIgnoreCase("YES")) {
                platform = reader.getCellData(sheetName, "platform", rowCount);
                break;
            }
        }
        return platform;
    }
    public static String getURLFromExcel(String testExcelSheet, String sheetName) {

        String urlType = null;
        Xls_reader reader = null;
        try {
            reader = new Xls_reader(System.getProperty("user.dir") + testExcelSheet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int rowCount = 2; rowCount <= reader.getRowCount(sheetName); rowCount++) {
            String enableFlag = reader.getCellData(sheetName, "execute", rowCount);
            if (enableFlag.equalsIgnoreCase("YES")) {
                urlType = reader.getCellData(sheetName, "URL", rowCount);
                break;
            }
        }
        return urlType;
    }
    private static String formatCellValue(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            default:
                return "";
        }
    }

    public ArrayList<HashMap<String, String>> getBankDetails(String testExcelSheet, String testName, String SheetName) {
        ArrayList<HashMap<String, String>> myData = new ArrayList<>();
        ArrayList<String> myColumnData = new ArrayList<String>();
        Xls_reader reader = null;

        try {
            reader = new Xls_reader(System.getProperty("user.dir") + testExcelSheet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("dashboard Sheet row count ---------" + reader.getRowCount(SheetName));
        System.out.println(" ******* current test running method  *********" + testName);
        System.out.println("**** column count  ********" + reader.getColumnCount(SheetName));

        String[] columnHeaders = {"firstname", "lastname", "postcode","custname","currency","depositAmt","withdrawAmt"};
        for (int rowCount = 3; rowCount <= reader.getRowCount(SheetName); rowCount++) {
            String currentTestMethod = reader.getCellData(SheetName, "testCaseName", rowCount);
            if (currentTestMethod.equalsIgnoreCase(testName)) {
                HashMap<String, String> testDataMap = new HashMap<>();
                for (String header : columnHeaders) {
                    String cellValue = reader.getCellData(SheetName, header, rowCount);
                    testDataMap.put(header, cellValue.trim());
                }
                myData.add(testDataMap);
            }
        }
        System.out.println("mydata rows value **********" + myData);
        return myData;
    }


}



