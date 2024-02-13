package com.solvd.api_tests.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static com.solvd.api_tests.util.Printers.PRINT2LN;
import static com.solvd.api_tests.util.Printers.PRINTLN;

public class TestNGListener implements ITestListener {
    public static final Logger LOGGER = LogManager.getLogger(TestNGListener.class);

    @Override
    public void onTestStart(ITestResult result) {
        PRINT2LN.info(String.format("Test '%s' from '%s' test group started!", result.getName(), result.getTestContext().getName()));
        PRINTLN.info(String.format("Test is from '%s' test suite:", result.getTestContext().getSuite().getName()));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        PRINTLN.info(String.format("Test '%s' from '%s' test group completed successfully!", result.getName(), result.getTestContext().getName()));
        PRINTLN.info(String.format("Test is from '%s' test suite.", result.getTestContext().getSuite().getName()));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LOGGER.error(String.format("Test '%s' from '%s' test group failed!", result.getName(), result.getTestContext().getName()));
        PRINTLN.info(String.format("Test is from '%s' test suite.", result.getTestContext().getSuite().getName()));
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        PRINT2LN.info(String.format("Test '%s' from '%s' test group skipped!", result.getName(), result.getTestContext().getName()));
        PRINTLN.info(String.format("Test is from '%s' test suite.", result.getTestContext().getSuite().getName()));
        PRINTLN.info("");
    }
}
