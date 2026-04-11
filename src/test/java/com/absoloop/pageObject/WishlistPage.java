package com.absoloop.pageObject;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.absoloop.pageObject.components.AlertComponent;
import com.absolooplab.Utility.WaitUtil;

public class WishlistPage extends BasePage {

	public final AlertComponent alert;

	@FindBy(xpath = "//div[@id='content']//table//tbody//tr")
	private List<WebElement> wishlistItems;

	@FindBy(xpath = "//td[@class='text-center']")
	private WebElement msgEmptyWishlist;

	public WishlistPage() {
		super();
		this.alert = new AlertComponent();
	}

	public int getWishlistCount() {
		return wishlistItems.size();
	}

	public boolean isWishlistEmpty() {
		return isDisplayed(msgEmptyWishlist, "Empty Wishlist");
	}

	public CartPage addToCartByIndex(int index) {
		WebElement btnCart = wishlistItems.get(index)
				.findElement(org.openqa.selenium.By.xpath(".//button[@data-original-title='Add to Cart']"));
		click(btnCart, "Add to Cart from Wishlist [index=" + index + "]");
		WaitUtil.waitForPageLoad();
		return new CartPage();
	}
}
