package com.absoloop.pageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.absolooplab.Utility.WaitUtil;

public class AccountRegistrationPage extends BasePage {

    @FindBy(name = "firstname")  private WebElement txtFirstname;
    @FindBy(name = "lastname")   private WebElement txtLastname;
    @FindBy(name = "email")      private WebElement txtEmail;
    @FindBy(name = "telephone")  private WebElement txtTelephone;
    @FindBy(name = "password")   private WebElement txtPassword;
    @FindBy(name = "confirm")    private WebElement txtConfirmPassword;
    @FindBy(name = "agree")      private WebElement chkPolicy;

    @FindBy(xpath = "//input[@value='Continue']")
    private WebElement btnContinue;

    @FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
    private WebElement msgConfirmation;

    public AccountRegistrationPage() { super(); }

    // ✅ All using BasePage type() — wait + log built in
    public void setFirstName(String fname)     { type(txtFirstname, fname, "First Name"); }
    public void setLastName(String lname)      { type(txtLastname, lname, "Last Name"); }
    public void setEmail(String email)         { type(txtEmail, email, "Email"); }
    public void setTelephone(String tel)       { type(txtTelephone, tel, "Telephone"); }
    public void setPassword(String pwd)        { type(txtPassword, pwd, "Password"); }
    public void setConfirmPassword(String pwd) { type(txtConfirmPassword, pwd, "Confirm Password"); }

    public void setPrivacyPolicy() {
        click(chkPolicy, "Privacy Policy Checkbox");
    }

    public void clickContinue() {
        click(btnContinue, "Continue Button");
        // ✅ After form submit, wait for next page to load
        WaitUtil.waitForPageLoad();
    }

    public String getConfirmationMsg() {
        return getText(msgConfirmation, "Confirmation Message");
    }
}