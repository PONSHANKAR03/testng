package org.page;

import org.library.SeleniumBaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SelectHotelPage extends SeleniumBaseClass {

	public SelectHotelPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//td[text()='Select Hotel ']")
	private WebElement selectHotelText;

	public WebElement getSelectHotelText() {
		return selectHotelText;
	}
	
	
	
}
