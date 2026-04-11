package com.absoloop.pageObject;

import com.absoloop.pageObject.components.FooterComponent;
import com.absoloop.pageObject.components.HeaderComponent;

/**
 * HomePage — entry point for all test flows. Navigation is ONLY via
 * header/footer components. Direct clickMyAccount/clickLogin methods REMOVED —
 * use header.goToLogin() instead.
 */
public class HomePage extends BasePage {

	public final HeaderComponent header;
	public final FooterComponent footer;

	public HomePage() {
		super();
		this.header = new HeaderComponent();
		this.footer = new FooterComponent();
	}
}
