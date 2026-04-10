package com.absoloop.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.absoloop.flows.AuthFlows;
import com.absoloop.flows.CartFlows;
import com.absoloop.flows.CheckoutFlows;
import com.absoloop.core.ConfigManager;
import com.absoloop.pageObject.OrderConfirmationPage;
import com.absoloop.testBase.BaseClass;

public class TC_005_FullCheckoutFlow extends BaseClass {

    @Test(groups = {"Regression", "Master"})
    public void test_fullCheckoutFlow() {

        // Step 1: Login (pre-condition for checkout)
        AuthFlows.login();

        // Step 2: Add product to cart
        CartFlows.addProductToCart(ConfigManager.get("searchProductName"));

        // Step 3: Checkout via CheckoutFlows
        OrderConfirmationPage confirm = CheckoutFlows.checkout(
            null, "Tejas", "Zombade",
            "123 Test Street", "Pune", "411027"
        );

        // Step 4: Assert order confirmed
        Assert.assertTrue(
            confirm.isOrderConfirmed(),
            "Order confirmation page not shown — checkout may have failed."
        );
    }
}
