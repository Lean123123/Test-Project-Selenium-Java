package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentManager extends BasePage{

    public static ExtentReports extentReport;
    public static String extentReportPrefix;
    public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    public ExtentManager() throws IOException {
        super();
    }

    public static ExtentReports getReport(){
        if (extentReport == null){
            setupExtentReport("Live Project");
        }
        return extentReport;
    }

    public static ExtentReports setupExtentReport(String testName){
        extentReport = new ExtentReports();
        String path = System.getProperty("user.dir")
                      + "/report/"
                      + extentReportPrefix_Name(testName)
                      + ".html";

        ExtentSparkReporter spark = new ExtentSparkReporter(path);
        extentReport.attachReporter(spark);

        extentReport.setSystemInfo("Test","Juan Carlos");
        spark.config().setReportName("Regression Test");
        spark.config().setDocumentTitle("Test Result");
        spark.config().setTheme(Theme.STANDARD);

        return extentReport;
    }


    public static String extentReportPrefix_Name(String testName){
        String date = new SimpleDateFormat("yyyy-mm-dd_HH-mm-ss").format(new Date());
        extentReportPrefix = testName + "_" +date;
        return extentReportPrefix;
    }

    public static void flushReport(){
        extentReport.flush();
    }

    public  synchronized static ExtentTest getTest(){
        return extentTest.get();
    }

    public synchronized static ExtentTest createTest(String name, String description){
         ExtentTest test = extentReport.createTest(name,description);
         extentTest.set(test);
         return getTest();
    }

    public synchronized static void log(String message){
        getTest().info(message);
    }
    public synchronized static void pass(String message){
        getTest().pass(message);
    }
    public synchronized static void fail(String message){
        getTest().fail(message);
    }

    public synchronized static void attachImage(){
        getTest().addScreenCaptureFromPath(getScreenShotDestinationPath());
    }

}
