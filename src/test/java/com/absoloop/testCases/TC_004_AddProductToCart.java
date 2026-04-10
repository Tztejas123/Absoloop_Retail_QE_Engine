package com.absoloop.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.absoloop.core.ConfigManager;
import com.absoloop.flows.CartFlows;
import com.absoloop.pageObject.CartPage;
import com.absoloop.testBase.BaseClass;

public class TC_004_AddProductToCart extends BaseClass {

    @Test(groups = {"Regression", "Master"})
    public void test_addProductToCart() {

        // ONE LINE — entire search+select+add flow in CartFlows
        CartPage cart = CartFlows.addProductToCart(
            ConfigManager.get("searchProductName"), "2"
        );

        Assert.assertTrue(
            cart.alert.isSuccessAlertPresent(),
            "Add to cart failed — success alert not shown."
        );

        Assert.assertFalse(
            cart.isCartEmpty(),
            "Cart is empty after adding product."
        );
    }
}
