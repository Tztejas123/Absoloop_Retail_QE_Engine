package com.absoloop.pageObject;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.absoloop.pageObject.components.BreadcrumbComponent;
import com.absoloop.pageObject.components.PaginationComponent;
import com.absolooplab.Utility.WaitUtil;

public class CategoryPage extends BasePage {

    public final BreadcrumbComponent breadcrumb;
    public final PaginationComponent pagination;

    @FindBy(xpath = "//div[@class='product-thumb']//h4/a")
    private List<WebElement> productLinks;

    @FindBy(id = "button-list") private WebElement btnListView;
    @FindBy(id = "button-grid") private WebElement btnGridView;

    public CategoryPage() {
        super();
        this.breadcrumb = new BreadcrumbComponent();
        this.pagination = new PaginationComponent();
    }

    public ProductDetailPage selectProduct(String name) {
        productLinks.stream()
            .filter(e -> e.getText().equalsIgnoreCase(name))
            .findFirst()
            .ifPresent(e -> { click(e, "Product: " + name); WaitUtil.waitForPageLoad(); });
        return new ProductDetailPage();
    }

    public int getTotalProducts()  { return productLinks.size(); }
    public void switchToListView() { click(btnListView, "List View"); }
    public void switchToGridView() { click(btnGridView, "Grid View"); }
}
