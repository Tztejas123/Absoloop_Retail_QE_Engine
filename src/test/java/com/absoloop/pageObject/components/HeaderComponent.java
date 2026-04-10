package com.absoloop.pageObject.components;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.absoloop.core.DriverManager;
import com.absolooplab.Utility.WaitUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HeaderComponent {

    private static final Logger log = LogManager.getLogger(HeaderComponent.class);

    @FindBy(xpath = "//span[normalize-space()='My Account']")
    private WebElement lnkMyAccount;

    @FindBy(xpath = "//a[normalize-space()='Register']")
    private WebElement lnkRegister;

    @FindBy(linkText = "Login")
    private WebElement lnkLogin;

    @FindBy(name = "search")
    private WebElement txtSearch;

    @FindBy(xpath = "//button[@class='btn btn-default btn-lg']")
    private WebElement btnSearch;

    public HeaderComponent() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    public void clickMyAccount() {
        log.info("Clicking My Account dropdown");
        WaitUtil.waitForClickable(lnkMyAccount);
        lnkMyAccount.click();
    }

    public void clickRegister() {
        log.info("Clicking Register link");
        WaitUtil.waitForClickable(lnkRegister);
        lnkRegister.click();
    }

    public void clickLogin() {
        log.info("Clicking Login link");
        WaitUtil.waitForClickable(lnkLogin);
        lnkLogin.click();
    }

    public void searchProduct(String productName) {
        log.info("Searching for product: {}", productName);
        WaitUtil.waitForVisibility(txtSearch);
        txtSearch.clear();
        txtSearch.sendKeys(productName);
        btnSearch.click();
    }
}