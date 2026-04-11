package com.absoloop.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.absoloop.flowsBusines.AuthFlows;
import com.absoloop.pageObject.MyAccountPage;
import com.absoloop.testBase.BaseClass;
import com.absolooplab.Utility.DataProviders;

public class TC_003_LoginDDT extends BaseClass {

    @Test(dataProvider = "LoginData",
          dataProviderClass = DataProviders.class,
          groups = "Datadriven")
    public void verify_loginDDT(String email, String password, String exp) {

        // Uses AuthFlows with explicit credentials from Excel
        MyAccountPage macc = AuthFlows.loginWith(email, password);
        boolean targetPage  = macc.isMyAccountPageExists();

        if (exp.equalsIgnoreCase("Valid")) {
            Assert.assertTrue(targetPage, "Expected login success but failed for: " + email);
            macc.clickLogout();
        } else {
            Assert.assertFalse(targetPage, "Expected login failure but succeeded for: " + email);
        }
    }
}
