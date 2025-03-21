package org.pageManager;

import org.page.LoginPage;
import org.page.SearchHotelPage;
import org.page.SelectHotelPage;

public class PageManager {

	private LoginPage loginPage;
	private SearchHotelPage searchHotelPage;
	private SelectHotelPage selectHotelPage;

	public LoginPage getLoginPage() {
		return (loginPage == null) ? loginPage = new LoginPage() : loginPage;
	}

	public SearchHotelPage getSearchHotelPage() {
		return (searchHotelPage == null) ? searchHotelPage = new SearchHotelPage() : searchHotelPage;
	}

	public SelectHotelPage getSelectHotelPage() {
		return (selectHotelPage==null) ? selectHotelPage = new SelectHotelPage() : selectHotelPage;
	}

	// (Condition) ? true block value : false block value ;
}
