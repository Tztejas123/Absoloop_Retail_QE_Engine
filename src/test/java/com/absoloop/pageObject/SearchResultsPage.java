package com.absoloop.pageObject;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.absoloop.pageObject.components.BreadcrumbComponent;
import com.absoloop.pageObject.components.PaginationComponent;
import com.absolooplab.Utility.WaitUtil;

public class SearchResultsPage extends BasePage {

    public final BreadcrumbComponent breadcrumb;
    public final PaginationComponent pagination;

    @FindBy(xpath = "//div[@class='product-thumb']") private List<WebElement> productCards;
    @FindBy(xpath = "//div[@class='product-thumb']//h4/a") private List<WebElement> productNames;
    @FindBy(id = "input-search") private WebElement txtSearch;
    @FindBy(id = "button-search") private WebElement btnSearch;
    @FindBy(xpath = "//h2[contains(.,'There is no product')]") private WebElement msgNoProduct;

    public SearchResultsPage() {
        super();
        this.breadcrumb = new BreadcrumbComponent();
        this.pagination  = new PaginationComponent();
    }

    public int getProductCount()        { return productCards.size(); }
    public boolean isNoResultMsgShown() { return isDisplayed(msgNoProduct, "No results message"); }

    /**
     * Click a product by name and return ProductDetailPage.
     * Replaces old void clickProductByName().
     */
    public ProductDetailPage selectProduct(String name) {
        productNames.stream()
            .filter(e -> e.getText().trim().equalsIgnoreCase(name))
            .findFirst()
            .ifPresent(e -> { click(e, "Product: " + name); WaitUtil.waitForPageLoad(); });
        return new ProductDetailPage();
    }

    public SearchResultsPage refineSearch(String keyword) {
        type(txtSearch, keyword, "Search Box");
        click(btnSearch, "Search Button");
        WaitUtil.waitForPageLoad();
        return new SearchResultsPage();
    }
}
