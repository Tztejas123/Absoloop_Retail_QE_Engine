package com.absoloop.pageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.absoloop.pageObject.components.AlertComponent;
import com.absoloop.pageObject.components.BreadcrumbComponent;
import com.absolooplab.Utility.WaitUtil;

public class ProductDetailPage extends BasePage {

	public final AlertComponent alert;
	public final BreadcrumbComponent breadcrumb;

	@FindBy(xpath = "//h1")
	private WebElement txtProductName;
	@FindBy(xpath = "//li[@class='price-new']")
	private WebElement txtPrice;
	@FindBy(id = "input-quantity")
	private WebElement txtQuantity;
	@FindBy(id = "button-cart")
	private WebElement btnAddToCart;
	@FindBy(xpath = "//button[@title='Add to Wish List']")
	private WebElement btnWishList;
	@FindBy(xpath = "//button[@title='Compare this Product']")
	private WebElement btnCompare;

	public ProductDetailPage() {
		super();
		this.alert = new AlertComponent();
		this.breadcrumb = new BreadcrumbComponent();
	}

	public String getProductName() {
		return getText(txtProductName, "Product Name");
	}

	public String getPrice() {
		return getText(txtPrice, "Product Price");
	}

	public ProductDetailPage setQuantity(String qty) {
		clearAndType(txtQuantity, qty, "Quantity");
		return this; // fluent
	}

	/**
	 * Add to cart and return CartPage. Changed from void — enables method chaining.
	 */
	public CartPage addToCart() {
		click(btnAddToCart, "Add to Cart");
		WaitUtil.waitForPageLoad();
		return new CartPage();
	}

	public void addToWishList() {
		click(btnWishList, "Add to Wishlist");
	}

	public void compareProduct() {
		click(btnCompare, "Compare Product");
	}

	// clearAndType exposed via BasePage for fluent chaining above
	protected void clearAndType(WebElement element, String value, String name) {
		WaitUtil.waitForVisibility(element);
		element.clear();
		WaitUtil.waitForVisibility(element);
		element.sendKeys(value);
	}
}
