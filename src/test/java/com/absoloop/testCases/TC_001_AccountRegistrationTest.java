package com.absoloop.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.absoloop.pageObject.AccountRegistrationPage;
import com.absoloop.pageObject.HomePage;
import com.absoloop.testBase.BaseClass;

public class TC_001_AccountRegistrationTest extends BaseClass {
    
    @Test(groups = { "Regression", "Master" }) 
    public void test_account_Registration() {
        logger.info("**** Starting TC_001_AccountRegistrationTest ****");
        
        try {
            // RUTHLESS FIX: No more passing getDriver(). The Page Object handles it.
            HomePage hp = new HomePage();
            hp.clickMyAccount();
            logger.info("Clicked on My Account");

            hp.clickRegister();
            logger.info("Clicked on Register");

            AccountRegistrationPage regpage = new AccountRegistrationPage();

            regpage.setFirstName(randomString().toUpperCase());
            regpage.setLastName(randomString().toUpperCase());
            regpage.setEmail(randomString() + "@gmail.com");
            regpage.setTelephone(randomNumber()); // Fixed typo from randomeNumber

            regpage.setPassword("test@123");
            regpage.setConfirmPassword("test@123");
            regpage.setPrivacyPolicy();
            
            logger.info("Submitting registration form...");
            regpage.clickContinue();

            String confmsg = regpage.getConfirmationMsg();
            logger.info("Validating expected message...");
            
            Assert.assertEquals(confmsg, "Your Account Has Been Created!", "Registration message mismatch!");
            
        } catch (Exception e) {
            logger.error("Test Failed: ", e); 
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }

        logger.info("**** Finished TC_001_AccountRegistrationTest ****");
    }
}