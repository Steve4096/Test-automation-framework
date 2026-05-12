package listener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ExtentManager;
import utils.TestManager;

public class TestListener implements ITestListener {
    private static ExtentReports extentReports= ExtentManager.getInstance();

    @Override
    public void onTestStart(ITestResult result) {
        String classname=result.getTestClass().getName();
        String testname=result.getMethod().getMethodName();
        ExtentTest extentTest= extentReports.createTest(classname+" - "+testname);
       // test.set(extentTest);
        TestManager.setTest(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        TestManager.getTest().pass("Test passed successfully");
        //TestManager.get().info("Test passed successfully");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        //test.get().fail(result.getThrowable());
        TestManager.getTest().fail(result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        TestManager.getTest().skip("Test skipped");
        //test.get().info("Test skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        extentReports.flush();
    }
}
