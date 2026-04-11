package com.absoloop.pageObject;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.absoloop.pageObject.components.AlertComponent;
import com.absolooplab.Utility.WaitUtil;

public class AddressBookPage extends BasePage {

	public final AlertComponent alert;

	@FindBy(xpath = "//div[@id='content']//table//tbody//tr")
	private List<WebElement> addressRows;
	@FindBy(xpath = "//a[normalize-space()='New Address']")
	private WebElement btnNewAddress;
	@FindBy(id = "input-firstname")
	private WebElement txtFirstname;
	@FindBy(id = "input-lastname")
	private WebElement txtLastname;
	@FindBy(id = "input-company")
	private WebElement txtCompany;
	@FindBy(id = "input-address-1")
	private WebElement txtAddress1;
	@FindBy(id = "input-address-2")
	private WebElement txtAddress2;
	@FindBy(id = "input-city")
	private WebElement txtCity;
	@FindBy(id = "input-postcode")
	private WebElement txtPostcode;
	@FindBy(id = "input-country")
	private WebElement drpCountry;
	@FindBy(id = "input-zone")
	private WebElement drpZone;
	@FindBy(xpath = "//input[@name='default']")
	private WebElement chkDefaultAddress;
	@FindBy(xpath = "//input[@value='Continue']")
	private WebElement btnContinue;
	@FindBy(xpath = "//div[@id='content']//p[contains(.,'no addresses')]")
	private WebElement msgNoAddresses;

	public AddressBookPage() {
		super();
		this.alert = new AlertComponent();
	}

	public int getAddressCount() {
		return addressRows.size();
	}

	public boolean isAddressBookEmpty() {
		return isDisplayed(msgNoAddresses, "No Addresses");
	}

	public AddressBookPage clickNewAddress() {
		click(btnNewAddress, "New Address");
		WaitUtil.waitForPageLoad();
		return this;
	}

	public AddressBookPage setFirstname(String v) {
		type(txtFirstname, v, "Firstname");
		return this;
	}

	public AddressBookPage setLastname(String v) {
		type(txtLastname, v, "Lastname");
		return this;
	}

	public AddressBookPage setAddress1(String v) {
		type(txtAddress1, v, "Address 1");
		return this;
	}

	public AddressBookPage setCity(String v) {
		type(txtCity, v, "City");
		return this;
	}

	public AddressBookPage setPostcode(String v) {
		type(txtPostcode, v, "Postcode");
		return this;
	}

	public AddressBookPage selectCountry(String country) {
		selectDropdown(drpCountry, country, "Country");
		WaitUtil.waitForVisibility(drpZone);
		return this;
	}

	public AddressBookPage selectZone(String zone) {
		selectDropdown(drpZone, zone, "Zone");
		return this;
	}

	public AddressBookPage setAsDefault() {
		if (!chkDefaultAddress.isSelected())
			click(chkDefaultAddress, "Set as Default");
		return this;
	}

	public MyAccountPage saveAddress() {
		click(btnContinue, "Save Address");
		WaitUtil.waitForPageLoad();
		return new MyAccountPage();
	}
}
