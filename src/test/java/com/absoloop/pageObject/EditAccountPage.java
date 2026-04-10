package com.absoloop.pageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.absolooplab.Utility.WaitUtil;

public class EditAccountPage extends BasePage {

	@FindBy(id = "input-firstname")
	private WebElement txtFirstname;
	@FindBy(id = "input-lastname")
	private WebElement txtLastname;
	@FindBy(id = "input-email")
	private WebElement txtEmail;
	@FindBy(id = "input-telephone")
	private WebElement txtTelephone;
	@FindBy(xpath = "//input[@value='Continue']")
	private WebElement btnContinue;

	public EditAccountPage() {
		super();
	}

	public EditAccountPage updateFirstname(String v) {
		type(txtFirstname, v, "Firstname");
		return this;
	}

	public EditAccountPage updateLastname(String v) {
		type(txtLastname, v, "Lastname");
		return this;
	}

	public EditAccountPage updateEmail(String v) {
		type(txtEmail, v, "Email");
		return this;
	}

	public EditAccountPage updateTelephone(String v) {
		type(txtTelephone, v, "Telephone");
		return this;
	}

	public MyAccountPage saveChanges() {
		click(btnContinue, "Save Changes");
		WaitUtil.waitForPageLoad();
		return new MyAccountPage();
	}
}
