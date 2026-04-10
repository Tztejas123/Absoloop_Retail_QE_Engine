package com.absoloop.pageObject.components;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.absoloop.core.DriverManager;
import com.absolooplab.Utility.WaitUtil;

public class BreadcrumbComponent {

	@FindBy(xpath = "//ul[@class='breadcrumb']//li[last()]")
	private WebElement lastCrumb;

	public BreadcrumbComponent() {
		PageFactory.initElements(DriverManager.getDriver(), this);
	}

	public String getCurrentPageBreadcrumb() {
		WaitUtil.waitForVisibility(lastCrumb);
		return lastCrumb.getText();
	}
}
