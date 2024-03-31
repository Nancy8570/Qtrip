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
import org.testng.asserts.SoftAssert;

import qtriptest.*;

public class testCase_01 {

    static RemoteWebDriver driver;
    static String lastgeneratedUsername="";
    static ExtentReports report;
    static ExtentTest test;

    @BeforeSuite(alwaysRun = true)
    public static void createDriver() throws MalformedURLException {
        DriverSingleton sbc1 = DriverSingleton.getInstanceOfSingletonBrowserClass();
        driver = sbc1.getDriver();
        ReportSingleton rs1 = ReportSingleton.getInstanceOfSingletonReportClass();
        report = rs1.getReport();
        test = report.startTest("ExtentDemo");
    }

    @Test(description="Verify user logged in", dataProvider = "data-provider", dataProviderClass = DP.class, priority = 1, groups = {"Login Flow"})
    @Parameters({ "TC1_userMail", "TC1_userPassword" })
    public void TestCase01(@Optional("testUser@gmail.com") String TC1_userMail, @Optional("abc@12345") String TC1_userPassword) throws InterruptedException {
        HomePage home = new HomePage(driver);
        home.gotoHomePage();
        home.clickRegister();
        SoftAssert sa = new SoftAssert();
        RegisterPage registration = new RegisterPage(driver);
        sa.assertTrue(registration.registerUser(TC1_userMail, TC1_userPassword, true),"Failed to create new user");
        lastgeneratedUsername=registration.lastGeneratedUsername;
        LoginPage login = new LoginPage(driver);
        login.navigateToLoginPage();
        assertTrue(login.performLogin(lastgeneratedUsername, TC1_userPassword),"Failed to login");
        assertTrue(home.isUserLoggedIn(),"Failure to Login using registered user");
        home.logOutUser();
        assertFalse(home.isUserLoggedIn(),"Failure in verification of user logout");
        test.log(LogStatus.INFO, test.addScreenCapture(SeleniumWrapper.takeScreenshot(driver, "PASS", "Login")));
        home.gotoHomePage();
    }

    @AfterSuite
    public static void quitDriver() {
        System.out.println("quit()");
        report.endTest(test);
        driver.quit();
        report.flush();
    }
}
