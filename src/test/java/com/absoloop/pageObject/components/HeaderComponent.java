package com.absoloop.pageObject.components;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.absoloop.core.DriverManager;
import com.absoloop.pageObject.AccountRegistrationPage;
import com.absoloop.pageObject.LoginPage;
import com.absoloop.pageObject.SearchResultsPage;
import com.absolooplab.Utility.WaitUtil;

/**
 * HeaderComponent — single source of truth for ALL header navigation.
 * Every navigation action returns the next PageObject.
 * Tests should NEVER navigate directly — always use header methods.
 */
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

    // ── My Account dropdown ────────────────────────────────────────
    public void clickMyAccount() {
        log.info("Opening My Account dropdown");
        WaitUtil.waitForClickable(lnkMyAccount);
        lnkMyAccount.click();
    }

    /**
     * Open My Account dropdown then click Login.
     * Returns LoginPage — enables chaining.
     */
    public LoginPage goToLogin() {
        clickMyAccount();
        WaitUtil.waitForClickable(lnkLogin);
        lnkLogin.click();
        return new LoginPage();
    }

    /**
     * Open My Account dropdown then click Register.
     * Returns AccountRegistrationPage — enables chaining.
     */
    public AccountRegistrationPage goToRegister() {
        clickMyAccount();
        WaitUtil.waitForClickable(lnkRegister);
        lnkRegister.click();
        return new AccountRegistrationPage();
    }

    /**
     * Search for a product by keyword.
     * Returns SearchResultsPage — enables chaining.
     */
    public SearchResultsPage search(String productName) {
        log.info("Searching for product: {}", productName);
        WaitUtil.waitForVisibility(txtSearch);
        txtSearch.clear();
        txtSearch.sendKeys(productName);
        btnSearch.click();
        WaitUtil.waitForPageLoad();
        return new SearchResultsPage();
    }
}
