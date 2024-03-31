package qtriptest;

import java.io.File;
import java.sql.Timestamp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.relevantcodes.extentreports.ExtentReports;

public class ReportSingleton {

    private static ReportSingleton instanceOfSingletonReport = null;

    private ExtentReports report;

    public static String getTimeStamp() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return String.valueOf(timestamp.getTime());
    }

    private ReportSingleton() {
        report = new ExtentReports(System.getProperty("user.dir") + "ExtentReportResults_" + getTimeStamp() + ".html"); 
        report.loadConfig(new File("extent_customization_configs.xml"));
    }

    public static ReportSingleton getInstanceOfSingletonReportClass() {
        if (instanceOfSingletonReport == null) {
            instanceOfSingletonReport = new ReportSingleton();
        }
        return instanceOfSingletonReport;
    }

    public ExtentReports getReport() {
        return report;
    }

}