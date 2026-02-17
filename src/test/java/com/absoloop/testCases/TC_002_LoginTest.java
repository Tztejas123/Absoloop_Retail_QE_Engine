package com.absoloop.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.absoloop.pageObject.HomePage;
import com.absoloop.pageObject.LoginPage;
import com.absoloop.pageObject.MyAccountPage;
import com.absoloop.testBase.BaseClass;

public class TC_002_LoginTest extends BaseClass {
    
    @Test(groups= {"Sanity","Master"})
    public void test_Login() {
        logger.info("**** Starting TC_002_LoginTest ****");
        
        try {               
            // Step 1: Navigate to Login
            HomePage hp = new HomePage();
            hp.clickMyAccount();
            hp.clickLogin();
            
            // Step 2: Perform Login
            LoginPage lp = new LoginPage();
            logger.info("Entering login credentials...");
            
            // rb is inherited from BaseClass
            lp.setEmail(rb.getString("email")); 
            lp.setPassword(rb.getString("password")); 
            lp.clickLogin();
            
            // Step 3: Validation
            MyAccountPage macc = new MyAccountPage();
            boolean targetpage = macc.isMyAccountPageExists();
            
            logger.info("Checking if My Account Page exists...");
            Assert.assertTrue(targetpage, "Login failed: My Account page not displayed.");
            
        } catch(Exception e) {
            logger.error("Test Failed: ", e); 
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }
        
        logger.info("**** Finished TC_002_LoginTest ****");
    }
}