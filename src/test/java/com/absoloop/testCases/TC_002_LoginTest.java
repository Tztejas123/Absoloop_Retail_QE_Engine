package com.absoloop.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.absoloop.pageObject.HomePage;
import com.absoloop.pageObject.LoginPage;
import com.absoloop.pageObject.MyAccountPage;
import com.absoloop.testBase.BaseClass;
import com.absoloop.core.ConfigManager;

public class TC_002_LoginTest extends BaseClass {

    @Test(groups = { "Sanity", "Master" })
    public void test_Login() {

        // Step 1: Navigate to Login
        HomePage hp = new HomePage();
        hp.clickMyAccount();
        hp.clickLogin();

        // Step 2: Perform Login
        LoginPage lp = new LoginPage();
        lp.setEmail(ConfigManager.get("email"));
        lp.setPassword(ConfigManager.get("password"));
        lp.clickLogin();

        // Step 3: Validation
        MyAccountPage macc = new MyAccountPage();
        boolean targetpage = macc.isMyAccountPageExists();

        Assert.assertTrue(
                targetpage,
                "Login failed: My Account page not displayed."
        );
    }
}