package com.absoloop.flows;

import com.absoloop.core.ConfigManager;
import com.absoloop.pageObject.HomePage;
import com.absoloop.pageObject.MyAccountPage;

/**
 * AuthFlows — all authentication-related business flows.
 *
 * Usage in tests: MyAccountPage account = AuthFlows.login(); MyAccountPage
 * account = AuthFlows.loginWith("user@test.com", "pass");
 */
public class AuthFlows {

	private AuthFlows() {
	}

	/**
	 * Login using credentials from config.properties (email + password).
	 */
	public static MyAccountPage login() {
		return new HomePage().header.goToLogin().login(ConfigManager.get("email"), ConfigManager.get("password"));
	}

	/**
	 * Login using explicit credentials. Use in DDT tests where credentials come
	 * from Excel.
	 */
	public static MyAccountPage loginWith(String email, String password) {
		return new HomePage().header.goToLogin().login(email, password);
	}
}
