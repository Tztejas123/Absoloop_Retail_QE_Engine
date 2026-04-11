package com.absoloop.pageObject;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.absoloop.pageObject.components.AlertComponent;
import com.absolooplab.Utility.WaitUtil;

public class CartPage extends BasePage {

	public final AlertComponent alert;

	@FindBy(xpath = "//div[@id='content']//table//tbody//tr")
	private List<WebElement> cartRows;

	@FindBy(xpath = "//a[contains(.,'Checkout')]")
	private WebElement btnCheckout;

	@FindBy(xpath = "//a[contains(.,'Continue Shopping')]")
	private WebElement btnContinueShopping;

	@FindBy(xpath = "//div[@id='content']//table//tfoot//tr[last()]//td[2]")
	private WebElement txtOrderTotal;

	public CartPage() {
		super();
		this.alert = new AlertComponent();
	}

	public int getCartItemCount() {
		return cartRows.size();
	}

	public String getOrderTotal() {
		return getText(txtOrderTotal, "Order Total");
	}

	public boolean isCartEmpty() {
		return cartRows.isEmpty();
	}

	/**
	 * Proceed to checkout — returns CheckoutPage. Changed from void — enables
	 * method chaining.
	 */
	public CheckoutPage proceedToCheckout() {
		click(btnCheckout, "Proceed to Checkout");
		WaitUtil.waitForPageLoad();
		return new CheckoutPage(); // 🔥 MUST RETURN THIS
	}

	public HomePage continueShopping() {
		click(btnContinueShopping, "Continue Shopping");
		WaitUtil.waitForPageLoad();
		return new HomePage();
	}
}
