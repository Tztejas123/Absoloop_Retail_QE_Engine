package com.absoloop.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.absoloop.pageObject.HomePage;
import com.absoloop.pageObject.LoginPage;
import com.absoloop.pageObject.MyAccountPage;
import com.absoloop.testBase.BaseClass;
import com.absolooplab.Utility.DataProviders;

public class TC_003_LoginDDT extends BaseClass {

    @Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups = "Datadriven")
    public void verify_loginDDT(String email, String password, String exp) {
        logger.info("**** Starting TC_003_LoginDDT for: " + email + " *****");

        try {
            // Step 1: Navigation
            HomePage hp = new HomePage(); // Using the new no-arg constructor
            hp.clickMyAccount();
            hp.clickLogin();

            // Step 2: Login Action
            LoginPage lp = new LoginPage();
            lp.setEmail(email);
            lp.setPassword(password);
            lp.clickLogin();

            // Step 3: Validation
            MyAccountPage macc = new MyAccountPage();
            boolean targetPage = macc.isMyAccountPageExists();

            if (exp.equalsIgnoreCase("Valid")) {
                if (targetPage == true) {
                    macc.clickLogout();
                    Assert.assertTrue(true);
                } else {
                    logger.error("Login failed for Valid data");
                    Assert.assertTrue(false);
                }
            }

            if (exp.equalsIgnoreCase("Invalid")) {
                if (targetPage == true) {
                    macc.clickLogout();
                    logger.error("Login succeeded for Invalid data");
                    Assert.assertTrue(false);
                } else {
                    // Logic: Login failed as expected for Invalid data
                    Assert.assertTrue(true);
                }
            }
            
        } catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage());
            Assert.fail("Test execution interrupted by exception.");
        }

        logger.info("**** Finished TC_003_LoginDDT *****");
    }
}