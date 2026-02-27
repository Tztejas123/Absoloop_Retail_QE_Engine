package com.absoloop.pageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.absolooplab.Utility.WaitUtil;

public class HomePage extends BasePage {

    @FindBy(xpath = "//span[normalize-space()='My Account']")
    private WebElement lnkMyaccount;

    @FindBy(xpath = "//a[normalize-space()='Register']")
    private WebElement lnkRegister;

    @FindBy(linkText = "Login")
    private WebElement linkLogin;

    public void clickMyAccount() {
        WaitUtil.waitForClickable(lnkMyaccount);
        lnkMyaccount.click();
    }

    public void clickRegister() {
        WaitUtil.waitForClickable(lnkRegister);
        lnkRegister.click();
    }

    public void clickLogin() {
        WaitUtil.waitForClickable(linkLogin);
        linkLogin.click();
    }
}