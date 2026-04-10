package com.absoloop.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.absoloop.pageObject.AccountRegistrationPage;
import com.absoloop.pageObject.HomePage;
import com.absoloop.testBase.BaseClass;
import com.absolooplab.Utility.TestDataUtil;

public class TC_001_AccountRegistrationTest extends BaseClass {

	@Test(groups = { "Regression", "Master" })
	public void test_account_Registration() {

		HomePage hp = new HomePage();
		hp.header.clickMyAccount();   // ← was hp.clickMyAccount()
		hp.header.clickRegister();    // ← was hp.clickRegister()
		hp.header.clickLogin();       // ← was hp.clickLogin()

		AccountRegistrationPage regpage = new AccountRegistrationPage();

		regpage.setFirstName(TestDataUtil.randomString().toUpperCase());
		regpage.setLastName(TestDataUtil.randomString().toUpperCase());
		regpage.setEmail(TestDataUtil.randomString() + "@gmail.com");
		regpage.setTelephone(TestDataUtil.randomNumber());

		regpage.setPassword("test@123");
		regpage.setConfirmPassword("test@123");
		regpage.setPrivacyPolicy();
		regpage.clickContinue();

		String confmsg = regpage.getConfirmationMsg();

		Assert.assertEquals(
		        regpage.getConfirmationMsg(),
		        "Your Account Has Been Created!",
		        "Registration failed!"
		    );
	}
}