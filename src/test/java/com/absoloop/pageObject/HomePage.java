package com.absoloop.pageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.absoloop.pageObject.components.HeaderComponent;
import com.absolooplab.Utility.WaitUtil;

public class HomePage extends BasePage {
	public HeaderComponent header;   // ← access header actions via this

    public HomePage() {
        super();
        this.header = new HeaderComponent();
    }

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