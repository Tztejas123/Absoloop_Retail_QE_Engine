package com.absoloop.pageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

    @FindBy(xpath = "//h2[text()='My Account']")
    private WebElement msgHeading;

    @FindBy(xpath = "//div[@class='list-group']//a[text()='Logout']")
    private WebElement lnkLogout;

    public MyAccountPage() { super(); }

    /** FIXED: uses BasePage.isDisplayed() with proper wait instead of raw isDisplayed() */
    public boolean isMyAccountPageExists() {
        return isDisplayed(msgHeading, "My Account Heading");
    }

    /**
     * Logout and return to HomePage.
     */
    public HomePage clickLogout() {
        click(lnkLogout, "Logout");
        return new HomePage();
    }
}
