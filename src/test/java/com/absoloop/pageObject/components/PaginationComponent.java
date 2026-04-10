package com.absoloop.pageObject.components;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.absoloop.core.DriverManager;
import com.absolooplab.Utility.WaitUtil;

public class PaginationComponent {

	private static final Logger log = LogManager.getLogger(PaginationComponent.class);

	@FindBy(xpath = "//ul[@class='pagination']//a[contains(.,'Next')]")
	private WebElement btnNext;

	@FindBy(xpath = "//ul[@class='pagination']//a[contains(.,'Previous')]")
	private WebElement btnPrev;

	@FindBy(xpath = "//div[@id='content']//p[contains(.,'Showing')]")
	private WebElement txtResultsCount;

	public PaginationComponent() {
		PageFactory.initElements(DriverManager.getDriver(), this);
	}

	public void clickNext() {
		WaitUtil.waitForClickable(btnNext);
		log.info("Pagination: Next");
		btnNext.click();
	}

	public void clickPrevious() {
		WaitUtil.waitForClickable(btnPrev);
		log.info("Pagination: Previous");
		btnPrev.click();
	}

	public String getResultsText() {
		return txtResultsCount.getText();
	}
}
