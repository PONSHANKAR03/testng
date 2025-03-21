package org.execution;


import org.library.SeleniumBaseClass;
import org.openqa.selenium.WebElement;
import org.page.LoginPage;
import org.page.SearchHotelPage;
import org.pageManager.PageManager;
import org.testng.annotations.Test;

public class TC02_ADactinHotelSearchHotelPageValidation extends SeleniumBaseClass {

	public static SeleniumBaseClass seleniumBaseClass = new SeleniumBaseClass();
	public static PageManager pageManager = new PageManager();    
	@Test
	public  void TC02_ADactinHotelSearchHotelPageValidation() {
		

		try {
			
			 
			 // Validate User is in Search Page or not
			 if (pageManager.getSearchHotelPage().getSearchHotelText().isDisplayed()) {
				 System.out.println("User is in Search Hotel Page");
				
			}

			WebElement location = pageManager.getSearchHotelPage().getLocation();
			seleniumBaseClass.selectBy(location, getDataFromWorkbook("input", 1, 4), "value");

			//------
			
			// To Validate and click search button
			if (pageManager.getSearchHotelPage().getSearchButton().isDisplayed()) {
				
				if (pageManager.getSearchHotelPage().getSearchButton().isEnabled()) {
					
					seleniumBaseClass.clickByJSE(pageManager.getSearchHotelPage().getSearchButton());
					
				}
				
			}
			
		} catch (Exception e) {

		
		
		}
	
	
	}

}
