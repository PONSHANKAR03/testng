package org.page;

import org.library.SeleniumBaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchHotelPage extends SeleniumBaseClass {

	public SearchHotelPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//td[text()='Search Hotel ']")
	private WebElement searchHotelText;
	
	public WebElement getSearchHotelText() {
		return searchHotelText;
	}
	
	
	@FindBy(id="location")
	private WebElement location;
	
	public WebElement getLocation() {
		return location;
	}
	
	@FindBy(xpath="//input[@id='Submit']")
	private WebElement searchButton;
	
	public WebElement getSearchButton() {
		return searchButton;
	}

}
