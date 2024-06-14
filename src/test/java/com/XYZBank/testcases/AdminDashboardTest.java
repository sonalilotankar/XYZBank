package com.XYZBank.testcases;

import com.XYZBank.base.TestBase;
import com.XYZBank.utils.TestUtils;
import io.qameta.allure.Allure;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import java.io.IOException;

public class AdminDashboardTest extends TestBase {

    public AdminDashboardTest() {

        super();
    }
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 1, description = "Verify the bank name")
    public void verifybankName() {
        String actualBankName = adminDashboard.getBanknameHeader().getText();
        utils.assertEquals(actualBankName, "XYZ Bank", "Verified bank name successfully");
        Allure.step("verified bank name" +actualBankName);


    }

}
