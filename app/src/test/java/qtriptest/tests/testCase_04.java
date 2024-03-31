package qtriptest.tests;

import java.net.MalformedURLException;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.ReportSingleton;
import qtriptest.SeleniumWrapper;
import qtriptest.pages.*;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class testCase_04 {

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

    @Test(description = "Verify that Booking history can be viewed", dataProvider = "data-provider", dataProviderClass = DP.class, priority = 4, groups = {"Reliability Flow"})
    public static void TestCase04(String NewUserName, String Password, String dataset1, String dataset2, String dataset3) throws Exception {

        String[] DS1 = dataset1.split(";");String[] DS2 = dataset2.split(";");String[] DS3 = dataset3.split(";");
        HomePage home = new HomePage(driver);
        home.gotoHomePage();
        home.clickRegister();
        RegisterPage register = new RegisterPage(driver);
        register.registerUser(NewUserName, Password, true);String username = register.lastGeneratedUsername;
        LoginPage Login = new LoginPage(driver);
        Login.performLogin(username, Password);
        Thread.sleep(3000);

        home.searchCity(DS1[0]);
        home.selectCity(DS1[0]);Thread.sleep(2000);
        AdventurePage adventures = new AdventurePage(driver);
        adventures.selectAdventure(DS1[1]);
        Thread.sleep(3000);
        AdventureDetailsPage adp = new AdventureDetailsPage(driver);
        adp.bookAdventure(DS1[2], DS1[3], Integer.parseInt(DS1[4]));
        Thread.sleep(3000);

        home.gotoHomePage();
        Thread.sleep(3000);
        home.searchCity(DS2[0]);
        home.selectCity(DS2[0]);
        Thread.sleep(2000);
        adventures.selectAdventure(DS2[1]);
        Thread.sleep(3000);
        adp.bookAdventure(DS2[2], DS2[3], Integer.parseInt(DS2[4]));
        Thread.sleep(3000);

        home.gotoHomePage();
        Thread.sleep(3000);
        home.searchCity(DS3[0]);
        
        
        home.selectCity(DS3[0]);
        Thread.sleep(2000);
        adventures.selectAdventure(DS3[1]);
        Thread.sleep(3000);
        adp.bookAdventure(DS3[2], DS3[3], Integer.parseInt(DS3[4]));
        Thread.sleep(3000);

        HistoryPage history = new HistoryPage(driver);
        history.gotoHistoryPage();
        Thread.sleep(3000);
        var reservations = history.getReservations();
        Assert.assertTrue((reservations.size() == 3),"Failure while verifying the number of reservations");
        test.log(LogStatus.INFO, test.addScreenCapture(SeleniumWrapper.takeScreenshot(driver, "PASS", "Login")));
        home.logOutUser();
    }

    @AfterSuite
    public static void quitDriver() {
        System.out.println("quit()");
        driver.quit();
    }
}
