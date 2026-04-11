package com.absoloop.pageObject;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.absoloop.pageObject.components.AlertComponent;
import com.absolooplab.Utility.WaitUtil;

public class ProductComparePage extends BasePage {

	public final AlertComponent alert;

	@FindBy(xpath = "//div[@id='content']//table//tbody//tr[1]//td[position()>1]//a")
	private List<WebElement> productNames;

	@FindBy(xpath = "//div[@id='content']//table//tbody//tr[contains(.,'Price')]//td[position()>1]")
	private List<WebElement> productPrices;

	@FindBy(xpath = "//div[@id='content']//table//tbody//tr[last()-1]//button")
	private List<WebElement> btnAddToCart;

	@FindBy(xpath = "//div[@id='content']//table//tbody//tr[last()]//a[contains(.,'Remove')]")
	private List<WebElement> btnRemove;

	@FindBy(xpath = "//div[@id='content']//p[contains(.,'You have not chosen')]")
	private WebElement msgEmptyCompare;

	public ProductComparePage() {
		super();
		this.alert = new AlertComponent();
	}

	public List<String> getProductNames() {
		return productNames.stream().map(WebElement::getText).toList();
	}

	public List<String> getProductPrices() {
		return productPrices.stream().map(WebElement::getText).toList();
	}

	public boolean isComparePageEmpty() {
		return isDisplayed(msgEmptyCompare, "Empty Compare");
	}

	public CartPage addToCartByIndex(int index) {
		click(btnAddToCart.get(index), "Add to Cart [compare index=" + index + "]");
		WaitUtil.waitForPageLoad();
		return new CartPage();
	}

	public void removeByIndex(int index) {
		click(btnRemove.get(index), "Remove from Compare [index=" + index + "]");
		WaitUtil.waitForPageLoad();
	}
}
