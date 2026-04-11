package com.absoloop.pageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.absoloop.pageObject.components.AlertComponent;
import com.absolooplab.Utility.WaitUtil;

public class ForgotPasswordPage extends BasePage {

	public final AlertComponent alert;

	@FindBy(id = "input-email")
	private WebElement txtEmail;
	@FindBy(xpath = "//input[@value='Continue']")
	private WebElement btnContinue;
	@FindBy(xpath = "//div[contains(@class,'alert-success')]")
	private WebElement msgSuccess;
	@FindBy(xpath = "//div[contains(@class,'alert-danger')]")
	private WebElement msgError;

	public ForgotPasswordPage() {
		super();
		this.alert = new AlertComponent();
	}

	public ForgotPasswordPage submitPasswordReset(String email) {
		type(txtEmail, email, "Email");
		click(btnContinue, "Continue (Reset Password)");
		WaitUtil.waitForPageLoad();
		return this;
	}

	public boolean isSuccessMessageDisplayed() {
		return isDisplayed(msgSuccess, "Reset Success");
	}

	public boolean isErrorMessageDisplayed() {
		return isDisplayed(msgError, "Reset Error");
	}
}
