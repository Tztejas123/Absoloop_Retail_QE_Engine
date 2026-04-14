package com.absoloop.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.absoloop.core.ConfigManager;
import com.absoloop.flowsBusines.AuthFlows;
import com.absoloop.flowsBusines.CartFlows;
import com.absoloop.flowsBusines.CheckoutFlows;
import com.absoloop.pageObject.CartPage;
import com.absoloop.pageObject.OrderConfirmationPage;
import com.absoloop.testBase.BaseClass;

public class TC_005_FullCheckoutFlow extends BaseClass {

    @Test(groups = {"Regression", "Master"})
    public void test_fullCheckoutFlow() {

        // Step 1: Login first (checkout requires login)
        AuthFlows.login();

        // Step 2: Add product → returns REAL CartPage (fixed in ProductDetailPage)
        CartPage cart = CartFlows.addProductToCart(
            ConfigManager.get("searchProductName")
        );

        // Step 3: Checkout using the real cart object — NOT null
        // ✅ FIXED: was CheckoutFlows.checkout(null, ...) → NPE
        OrderConfirmationPage confirm = CheckoutFlows.checkout(
            cart,                // ✅ real CartPage object
            "Tejas", "Zombade",
            "123 Test Street",
            "Pune",
            "411027"
        );

        // Step 4: Assert
        Assert.assertTrue(
            confirm.isOrderConfirmed(),
            "Order confirmation page not shown — checkout failed."
        );
    }
}