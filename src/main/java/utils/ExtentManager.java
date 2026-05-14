package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    private static ExtentReports reports;

    public static ExtentReports getInstance(){
        if (reports==null){
            ExtentSparkReporter sparkReporter=new ExtentSparkReporter("reports/extent-reports.html");

            sparkReporter.config().setDocumentTitle("Test execution report");
            sparkReporter.config().setReportName("Test execution summary");

            reports=new ExtentReports();
            reports.attachReporter(sparkReporter);

            reports.setSystemInfo("Framework","Playwright+TestNG");
            reports.setSystemInfo("Tester","Steve Muturi");
        }
        return reports;
    }
}
