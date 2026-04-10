package com.absoloop.pageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.absolooplab.Utility.WaitUtil;

public class ChangePasswordPage extends BasePage {

    @FindBy(id = "input-password") private WebElement txtPassword;
    @FindBy(id = "input-confirm")  private WebElement txtConfirm;
    @FindBy(xpath = "//input[@value='Continue']") private WebElement btnContinue;

    public ChangePasswordPage() { super(); }

    public ChangePasswordPage setNewPassword(String v)  { type(txtPassword, v, "New Password");     return this; }
    public ChangePasswordPage confirmPassword(String v) { type(txtConfirm,  v, "Confirm Password"); return this; }

    public MyAccountPage savePassword() {
        click(btnContinue, "Save Password");
        WaitUtil.waitForPageLoad();
        return new MyAccountPage();
    }
}
