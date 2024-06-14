package com.XYZBank.dataproviders;

import Utils.ExcelUtils;
import com.XYZBank.utils.PropertiesUtils;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class DataProviders {

    PropertiesUtils prop = new PropertiesUtils();

    @DataProvider(name = "XYZBankJourney")
    public Iterator<Object[]> XYZBankJourney(Method method) throws IOException {
        ArrayList<HashMap<String, String>> testDataList = new ExcelUtils().getBankDetails(prop.getProperties("data.excel"), method.getName(), prop.getProperties("dataprovider.bankDetails"));
        List<Object[]> testDataArray = new ArrayList<>();
        for (HashMap<String, String> testData : testDataList) {
            testDataArray.add(new Object[]{testData});
        }
        return testDataArray.iterator();
    }

}

