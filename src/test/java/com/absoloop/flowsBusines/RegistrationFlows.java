package com.absoloop.flowsBusines;

import com.absoloop.pageObject.AccountRegistrationPage;
import com.absoloop.pageObject.HomePage;
import com.absolooplab.Utility.TestDataUtil;

/**
 * RegistrationFlows — new user registration business flows.
 *
 * Usage in tests: AccountRegistrationPage reg =
 * RegistrationFlows.registerNewUser(); AccountRegistrationPage reg =
 * RegistrationFlows.registerWith("test@mail.com");
 */
public class RegistrationFlows {

	private RegistrationFlows() {
	}

	/**
	 * Register a fully random user (all fields auto-generated).
	 */
	public static AccountRegistrationPage registerNewUser() {
		AccountRegistrationPage reg = new HomePage().header.goToRegister();

		reg.setFirstName(TestDataUtil.randomString().toUpperCase());
		reg.setLastName(TestDataUtil.randomString().toUpperCase());
		reg.setEmail(TestDataUtil.randomString() + "@qa.com");
		reg.setTelephone(TestDataUtil.randomNumber());
		reg.setPassword("Test@1234");
		reg.setConfirmPassword("Test@1234");
		reg.setPrivacyPolicy();
		reg.clickContinue();
		return reg;
	}

	/**
	 * Register with a specific email (other fields remain random).
	 */
	public static AccountRegistrationPage registerWith(String email) {
		AccountRegistrationPage reg = new HomePage().header.goToRegister();

		reg.setFirstName(TestDataUtil.randomString().toUpperCase());
		reg.setLastName(TestDataUtil.randomString().toUpperCase());
		reg.setEmail(email);
		reg.setTelephone(TestDataUtil.randomNumber());
		reg.setPassword("Test@1234");
		reg.setConfirmPassword("Test@1234");
		reg.setPrivacyPolicy();
		reg.clickContinue();
		return reg;
	}
}
