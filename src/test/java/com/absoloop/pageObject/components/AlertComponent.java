package com.absoloop.pageObject.components;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.absoloop.core.DriverManager;
import com.absolooplab.Utility.WaitUtil;

public class AlertComponent {

    private static final Logger log = LogManager.getLogger(AlertComponent.class);

    @FindBy(xpath = "//div[contains(@class,'alert-success')]")
    private WebElement alertSuccess;

    @FindBy(xpath = "//div[contains(@class,'alert-danger')]")
    private WebElement alertDanger;

    public AlertComponent() { PageFactory.initElements(DriverManager.getDriver(), this); }

    public String getSuccessMessage() {
        WaitUtil.waitForVisibility(alertSuccess);
        String msg = alertSuccess.getText();
        log.info("Success alert: {}", msg);
        return msg;
    }

    public String getDangerMessage() {
        WaitUtil.waitForVisibility(alertDanger);
        String msg = alertDanger.getText();
        log.warn("Danger alert: {}", msg);
        return msg;
    }

    public boolean isSuccessAlertPresent() {
        try { WaitUtil.waitForVisibilityShort(alertSuccess); return true; }
        catch (Exception e) { return false; }
    }

    public boolean isDangerAlertPresent() {
        try { WaitUtil.waitForVisibilityShort(alertDanger); return true; }
        catch (Exception e) { return false; }
    }
}
