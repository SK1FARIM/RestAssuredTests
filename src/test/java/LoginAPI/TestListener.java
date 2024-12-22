package LoginAPI;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {  //ISuiteListener  IInvokedMethodListener IReporter

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Тест начался: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Тест прошел: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Тест не прошел: " + result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Тест пропущен: " + result.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Не обязательный метод
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Тестовый набор начал выполнение: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Тестовый набор завершил выполнение: " + context.getName());
    }
}
