package com.absoloop.flowsBusines;

import com.absoloop.pageObject.CartPage;
import com.absoloop.pageObject.HomePage;

/**
 * CartFlows — product search and add-to-cart business flows.
 *
 * Usage in tests: CartPage cart = CartFlows.addProductToCart("iPhone");
 * CartPage cart = CartFlows.addProductToCart("iPhone", "2");
 */
public class CartFlows {

	private CartFlows() {
	}

	/**
	 * Search for product, open its detail page, add qty=1 to cart.
	 */
	public static CartPage addProductToCart(String productName) {
		return addProductToCart(productName, "1");
	}

	/**
	 * Search for product, open its detail page, set specific quantity, add to cart.
	 */
	public static CartPage addProductToCart(String productName, String quantity) {
		return new HomePage().header.search(productName).selectProduct(productName).setQuantity(quantity).addToCart();
	}
}
