package qtriptest.tests;

import static org.testng.Assert.*;
import java.net.MalformedURLException;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import qtriptest.pages.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import org.testng.annotations.Test;

import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.ReportSingleton;
import qtriptest.SeleniumWrapper;

public class testCase_02 {

    static RemoteWebDriver driver;
    static ExtentReports report;
    static ExtentTest test;

    @BeforeSuite(alwaysRun = true)
    public static void createDriver() throws MalformedURLException {
        System.out.println("------------ running before suite --------------");
        DriverSingleton sbc1 = DriverSingleton.getInstanceOfSingletonBrowserClass();
        driver = sbc1.getDriver();
        ReportSingleton rs1 = ReportSingleton.getInstanceOfSingletonReportClass();
        report = rs1.getReport();
        test = report.startTest("ExtentDemo");
    }

    @Test(description = "Verify that Search and filters work fine", dataProvider = "data-provider", dataProviderClass = DP.class, priority = 2, groups = {"Search and Filter flow"})
    public static void TestCase02(String CityName, String Category_Filter, String DurationFilter, String ExpectedFilteredResults, String ExpectedUnFilteredResults) throws Exception {
        
        HomePage home = new HomePage(driver);
        home.gotoHomePage();
        Thread.sleep(1000);
        home.searchCity("nowhere");
        Thread.sleep(1000);
        assertTrue(home.isNoCityFound());
        home.searchCity(CityName);
        home.selectCity(CityName);
        AdventurePage adventures = new AdventurePage(driver);
        adventures.setCategoryValue(Category_Filter);
        adventures.setFilterValue(DurationFilter);
        assertTrue(adventures.getResultCount() == Integer.parseInt(ExpectedFilteredResults),"Mismatchg in result count Expected vs actual");
        adventures.clearFilters();
        assertTrue(adventures.getResultCount() == Integer.parseInt(ExpectedUnFilteredResults),"Mismatchg in result count Expected vs actual after clearing filters");
        test.log(LogStatus.INFO, test.addScreenCapture(SeleniumWrapper.takeScreenshot(driver, "PASS", "Login")));

    }

    @AfterSuite
    public static void quitDriver() {
        System.out.println("quit()");
        driver.quit();
    }
}
