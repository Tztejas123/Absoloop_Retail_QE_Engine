package com.absoloop.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.absoloop.pageObject.HomePage;
import com.absoloop.pageObject.LoginPage;
import com.absoloop.pageObject.MyAccountPage;
import com.absoloop.testBase.BaseClass;
import com.absolooplab.Utility.DataProviders;

public class TC_003_LoginDDT extends BaseClass {

    @Test(dataProvider = "LoginData",
          dataProviderClass = DataProviders.class,
          groups = "Datadriven")
    public void verify_loginDDT(String email,
                                String password,
                                String exp) {

        // Step 1: Navigation
        HomePage hp = new HomePage();
        hp.clickMyAccount();
        hp.clickLogin();

        // Step 2: Login
        LoginPage lp = new LoginPage();
        lp.setEmail(email);
        lp.setPassword(password);
        lp.clickLogin();

        // Step 3: Validation
        MyAccountPage macc = new MyAccountPage();
        boolean targetPage = macc.isMyAccountPageExists();

        if (exp.equalsIgnoreCase("Valid")) {

            Assert.assertTrue(
                    targetPage,
                    "Expected login success but failed."
            );

            macc.clickLogout();

        } else if (exp.equalsIgnoreCase("Invalid")) {

            Assert.assertFalse(
                    targetPage,
                    "Expected login failure but succeeded."
            );
        }
    }
}