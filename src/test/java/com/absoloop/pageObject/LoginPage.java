package com.absoloop.pageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	@FindBy(id = "input-email")
	private WebElement txtEmail;

	@FindBy(id = "input-password")
	private WebElement txtPassword;

	@FindBy(xpath = "//input[@value='Login']")
	private WebElement btnLogin;

	public LoginPage() {
		super();
	}

	public void setEmail(String email) {
		type(txtEmail, email, "Email");
	}

	public void setPassword(String pwd) {
		type(txtPassword, pwd, "Password");
	}

	/**
	 * Full login action — returns MyAccountPage. Use in flows: AuthFlows.login()
	 * calls this.
	 */
	public MyAccountPage login(String email, String password) {
		type(txtEmail, email, "Email");
		type(txtPassword, password, "Password");
		click(btnLogin, "Login Button");
		return new MyAccountPage();
	}

	/** Legacy: use login(email, password) in new code */
	public void clickLogin() {
		click(btnLogin, "Login Button");
	}
}
