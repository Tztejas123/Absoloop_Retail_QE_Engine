package com.absoloop.pageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.absolooplab.Utility.WaitUtil;

public class OrderConfirmationPage extends BasePage {

    @FindBy(xpath = "//h1[contains(.,'Your order has been placed')]")
    private WebElement msgConfirmation;

    @FindBy(xpath = "//a[contains(.,'Continue')]")
    private WebElement btnContinue;

    public OrderConfirmationPage() { super(); }

    public boolean isOrderConfirmed() {
        return isDisplayed(msgConfirmation, "Order Confirmation Message");
    }

    public HomePage clickContinue() {
        click(btnContinue, "Continue After Order");
        WaitUtil.waitForPageLoad();
        return new HomePage();
    }
}