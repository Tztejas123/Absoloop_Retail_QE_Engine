package com.absoloop.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.absoloop.flowsBusines.RegistrationFlows;
import com.absoloop.pageObject.AccountRegistrationPage;
import com.absoloop.testBase.BaseClass;

public class TC_001_AccountRegistrationTest extends BaseClass {

    @Test(groups = {"Regression", "Master"})
    public void test_account_Registration() {

        // ONE LINE — all logic lives in RegistrationFlows
        AccountRegistrationPage reg = RegistrationFlows.registerNewUser();

        Assert.assertEquals(
            reg.getConfirmationMsg(),
            "Your Account Has Been Created!",
            "Registration confirmation message mismatch"
        );
    }
}
