package org.execution;

import org.library.SeleniumBaseClass;
import org.openqa.selenium.WebElement;
import org.page.LoginPage;
import org.pageManager.PageManager;
import org.testng.annotations.Test;

public class TC01_ADactinHotelLoginPageValidation extends SeleniumBaseClass {

	public static SeleniumBaseClass seleniumBaseClass = new SeleniumBaseClass();
	public static PageManager pageManager = new PageManager();

	@Test
	public void LoginSuccessFulValidation() {

		try {

			// LoginPage loginPage = pageManager.getLoginPage();

			if (pageManager.getLoginPage().getWelcomeText().isDisplayed()) {

				System.out.println("Login Succesfully Happend");

			}

		} catch (Exception e) {

		}

	}

}
