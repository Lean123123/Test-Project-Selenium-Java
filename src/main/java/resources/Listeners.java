package resources;

import base.BasePage;

import java.io.IOException;

import base.ExtentManager;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners extends BasePage implements ITestListener {
    public Listeners() throws IOException {
    super();
    }

    //public sync void on start()
    //usart

    public synchronized void onStart(ITestContext context){
        ExtentManager.getReport();
        ExtentManager.createTest(context.getName(),context.getName());

    }

    public synchronized void onTestFailure(ITestResult result) {
        ExtentManager.getTest().fail(result.getThrowable());
        try {
            System.out.println("Test failed:" + result.getName());
            takeSnapShot(result.getMethod().getMethodName());
            ExtentManager.attachImage();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public  synchronized  void onFinish(ITestContext context){
        ExtentManager.flushReport();
    }
}
