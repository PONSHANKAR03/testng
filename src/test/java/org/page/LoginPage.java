package org.page;

import org.library.SeleniumBaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends SeleniumBaseClass {

	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath="//td[text()='Existing User Login - Build 1']")
	private WebElement loginText;
	
	public WebElement getLoginText() {
		return loginText;
	}

	@CacheLookup
	@FindBys({@FindBy(how=How.XPATH,using="//input[@id='username']") , @FindBy(xpath="//input[@name='username']") })
	private WebElement username;

	public WebElement getUserName() {

		return username;
	}
	
	
	@FindAll({@FindBy(how=How.ID,using="password") , @FindBy(how=How.NAME,using="passwoiughrd")})
	private WebElement password;
	
	public WebElement getPassword() {
		return password;
	}
	
	
	@FindBy(id="login")
	private WebElement loginButton;
	
	public WebElement getLoginButton() {
		return loginButton;
	}
	

	@FindBy(xpath="//td[text()='Welcome to Adactin Group of Hotels']")
	private WebElement WelcomeText;
	
	public WebElement getWelcomeText() {
		return WelcomeText;
	}
	
	
	
}
