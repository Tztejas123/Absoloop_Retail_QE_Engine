package com.absoloop.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.absoloop.flowsBusines.AuthFlows;
import com.absoloop.pageObject.MyAccountPage;
import com.absoloop.testBase.BaseClass;

public class TC_002_LoginTest extends BaseClass {

    @Test(groups = {"Sanity", "Master"})
    public void test_Login() {

        // ONE LINE — all login logic lives in AuthFlows
        MyAccountPage macc = AuthFlows.login();

        Assert.assertTrue(
            macc.isMyAccountPageExists(),
            "Login failed: My Account page not displayed."
        );
    }
}
