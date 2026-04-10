package com.absoloop.flows;

import com.absoloop.pageObject.CartPage;
import com.absoloop.pageObject.CheckoutPage;
import com.absoloop.pageObject.OrderConfirmationPage;

/**
 * CheckoutFlows — checkout and order placement business flows.
 */
public class CheckoutFlows {

	private CheckoutFlows() {
	}

	/**
	 * Complete checkout from an existing cart. Assumes user is already on CartPage.
	 */
	public static OrderConfirmationPage checkout(CartPage cart, String firstname, String lastname, String address,
			String city, String postcode) {

		return cart.proceedToCheckout().enterBillingFirstname(firstname).enterBillingLastname(lastname)
				.enterAddress(address).enterCity(city).enterPostcode(postcode).confirmOrder();
	}

	/**
	 * Full end-to-end flow: login + add product + checkout
	 */
	public static OrderConfirmationPage loginAddAndCheckout(String productName, String firstname, String lastname,
			String address, String city, String postcode) {

		// Step 1: Login
		AuthFlows.login();

		// Step 2: Add product → RETURNS CartPage (IMPORTANT)
		CartPage cart = CartFlows.addProductToCart(productName);

		// Step 3: Checkout using returned cart
		return checkout(cart, firstname, lastname, address, city, postcode);
	}
}