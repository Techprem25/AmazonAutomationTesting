package testListeners;

import com.amazon.PageObjectModel.ReusableMethods;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners extends ReusableMethods implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);
        System.out.println("onTestStart");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ReusableMethods.captureScreen(driver, result.getMethod().getMethodName() + ".png");
        ITestListener.super.onTestSuccess(result);
        System.out.println("onTestSuccess");
    }

    @Override
    public void onTestFailure(ITestResult result) {

//        String filename = result.getMethod().getMethodName() + "__" + result.getThrowable().toString().split(":")[0].replaceAll("org.openqa.selenium", "") + ".png";
//        Reusable.captureScreen(driver, filename);
        ITestListener.super.onTestFailure(result);
        System.out.println("onTestFailure");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
        System.out.println("onTestSkipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
        System.out.println("onTestFailedButWithinSuccessPercentage");
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
        System.out.println("onTestFailedWithTimeout");
    }

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
        System.out.println("onStart");
    }

    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
        System.out.println("onFinish");
    }
}
