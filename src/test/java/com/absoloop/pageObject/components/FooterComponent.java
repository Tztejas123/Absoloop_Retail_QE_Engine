package com.absoloop.pageObject.components;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.absoloop.core.DriverManager;
import com.absolooplab.Utility.WaitUtil;

public class FooterComponent {

	private static final Logger log = LogManager.getLogger(FooterComponent.class);

	@FindBy(xpath = "//footer//a[normalize-space()='About Us']")
	private WebElement lnkAboutUs;
	@FindBy(xpath = "//footer//a[normalize-space()='Delivery Information']")
	private WebElement lnkDeliveryInfo;
	@FindBy(xpath = "//footer//a[normalize-space()='Privacy Policy']")
	private WebElement lnkPrivacyPolicy;
	@FindBy(xpath = "//footer//a[normalize-space()='Terms & Conditions']")
	private WebElement lnkTerms;
	@FindBy(xpath = "//footer//a[normalize-space()='Contact Us']")
	private WebElement lnkContactUs;
	@FindBy(xpath = "//footer//a[normalize-space()='Returns']")
	private WebElement lnkReturns;
	@FindBy(xpath = "//footer//a[normalize-space()='Site Map']")
	private WebElement lnkSiteMap;
	@FindBy(xpath = "//footer//a[normalize-space()='Brands']")
	private WebElement lnkBrands;
	@FindBy(xpath = "//footer//a[normalize-space()='Gift Certificates']")
	private WebElement lnkGiftCertificates;
	@FindBy(xpath = "//footer//a[normalize-space()='Affiliate']")
	private WebElement lnkAffiliate;
	@FindBy(xpath = "//footer//a[normalize-space()='Specials']")
	private WebElement lnkSpecials;
	@FindBy(xpath = "//footer//a[normalize-space()='My Account']")
	private WebElement lnkMyAccount;
	@FindBy(xpath = "//footer//a[normalize-space()='Order History']")
	private WebElement lnkOrderHistory;
	@FindBy(xpath = "//footer//a[normalize-space()='Wish List']")
	private WebElement lnkWishList;
	@FindBy(xpath = "//footer//a[normalize-space()='Newsletter']")
	private WebElement lnkNewsletter;

	public FooterComponent() {
		PageFactory.initElements(DriverManager.getDriver(), this);
	}

	public void clickAboutUs() {
		click(lnkAboutUs, "About Us");
	}

	public void clickDeliveryInfo() {
		click(lnkDeliveryInfo, "Delivery Information");
	}

	public void clickPrivacyPolicy() {
		click(lnkPrivacyPolicy, "Privacy Policy");
	}

	public void clickTerms() {
		click(lnkTerms, "Terms & Conditions");
	}

	public void clickContactUs() {
		click(lnkContactUs, "Contact Us");
	}

	public void clickReturns() {
		click(lnkReturns, "Returns");
	}

	public void clickSiteMap() {
		click(lnkSiteMap, "Site Map");
	}

	public void clickBrands() {
		click(lnkBrands, "Brands");
	}

	public void clickGiftCertificates() {
		click(lnkGiftCertificates, "Gift Certificates");
	}

	public void clickAffiliate() {
		click(lnkAffiliate, "Affiliate");
	}

	public void clickSpecials() {
		click(lnkSpecials, "Specials");
	}

	public void clickMyAccount() {
		click(lnkMyAccount, "My Account (footer)");
	}

	public void clickOrderHistory() {
		click(lnkOrderHistory, "Order History (footer)");
	}

	public void clickWishList() {
		click(lnkWishList, "Wish List (footer)");
	}

	public void clickNewsletter() {
		click(lnkNewsletter, "Newsletter (footer)");
	}

	private void click(WebElement el, String name) {
		WaitUtil.waitForClickable(el);
		log.info("FOOTER CLICK | {}", name);
		el.click();
		WaitUtil.waitForPageLoad();
	}
}
