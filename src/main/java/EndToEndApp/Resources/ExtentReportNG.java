package EndToEndApp.Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {

	public static ExtentReports getReportObject() {
		String path = System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Results"); //Updating Report Name
		reporter.config().setDocumentTitle("Initial Demo of Extent Reports"); //Update the Report document Name
		
		ExtentReports report = new ExtentReports(); 
		report.attachReporter(reporter);
		report.setSystemInfo("Tester", "Ananya");
		return report;
	}
}
