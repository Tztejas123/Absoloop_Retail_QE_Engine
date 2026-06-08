package com.absolooplab.Utility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private ThreadLocal<Integer> count = ThreadLocal.withInitial(() -> 0);
    private static final int maxRetry = 1;

    @Override
    public boolean retry(ITestResult result) {
        if (count.get() < maxRetry) {
            count.set(count.get() + 1);
            return true;
        }
        count.set(0); // reset for next test
        return false;
    }
}