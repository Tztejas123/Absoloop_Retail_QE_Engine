package com.absoloop.pageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.absolooplab.Utility.WaitUtil;

public class CheckoutPage extends BasePage {

	// Step 1 — Billing Details
	@FindBy(id = "input-payment-firstname")
	private WebElement txtFirstname;
	@FindBy(id = "input-payment-lastname")
	private WebElement txtLastname;
	@FindBy(id = "input-payment-address-1")
	private WebElement txtAddress;
	@FindBy(id = "input-payment-city")
	private WebElement txtCity;
	@FindBy(id = "input-payment-postcode")
	private WebElement txtPostcode;

	// Step 5 — Confirm
	@FindBy(xpath = "//input[@id='button-confirm']")
	private WebElement btnConfirmOrder;

	@FindBy(xpath = "//div[@class='panel-heading']//a[contains(.,'Billing')]")
	private WebElement stepBilling;

	public CheckoutPage() {
		super();
	}

	public CheckoutPage enterBillingFirstname(String v) {
		type(txtFirstname, v, "Billing Firstname");
		return this;
	}

	public CheckoutPage enterBillingLastname(String v) {
		type(txtLastname, v, "Billing Lastname");
		return this;
	}

	public CheckoutPage enterAddress(String v) {
		type(txtAddress, v, "Address");
		return this;
	}

	public CheckoutPage enterCity(String v) {
		type(txtCity, v, "City");
		return this;
	}

	public CheckoutPage enterPostcode(String v) {
		type(txtPostcode, v, "Postcode");
		return this;
	}

	public OrderConfirmationPage confirmOrder() {
		click(btnConfirmOrder, "Confirm Order");
		WaitUtil.waitForPageLoad();
		return new OrderConfirmationPage(); // 🔥 MUST RETURN
	}
}