package com.absoloop.pageObject;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.absoloop.pageObject.components.PaginationComponent;

public class OrderHistoryPage extends BasePage {

	public final PaginationComponent pagination;

	@FindBy(xpath = "//div[@id='content']//table//tbody//tr")
	private List<WebElement> orderRows;

	public OrderHistoryPage() {
		super();
		this.pagination = new PaginationComponent();
	}

	public int getOrderCount() {
		return orderRows.size();
	}
}
