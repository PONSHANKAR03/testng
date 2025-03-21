package org.execution;

import org.library.SeleniumBaseClass;
import org.openqa.selenium.WebElement;
import org.page.SearchHotelPage;
import org.page.SelectHotelPage;
import org.pageManager.PageManager;
import org.testng.annotations.Test;

public class TC03_ADactinHotelSelectPageValidation extends SeleniumBaseClass {

	public static SeleniumBaseClass seleniumBaseClass = new SeleniumBaseClass();
	public static PageManager pageManager = new PageManager();

	@Test
	public void Tc03_ADactinHotelSelectPageValidation() {

		// Search Hote Details
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

		// Select Hotel Details
		try {

			if (pageManager.getSelectHotelPage().getSelectHotelText().isDisplayed()) {

				System.out.println("User is in Select Page ");
			}

		} catch (Exception e) {

		}

	}

}
