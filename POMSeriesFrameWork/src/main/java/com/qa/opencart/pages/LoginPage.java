package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtils;

public class LoginPage {

	private WebDriver driver;
	private ElementUtils Util;
	//1.By Locator /
	private By user = By.name("email");
	private By pwd= By.name("password");
	private By logibtn= By.cssSelector("input[type='submit'] ");
	private By forgotPwdlink= By.cssSelector("div.form-group a");
	
	
	//2.Constructor of Page Class
	
	public LoginPage (WebDriver driver) {
		this.driver= driver;
		Util= new ElementUtils(this.driver);
		
	}
	
	//3.page actions /methods
	public String getLoginPageTitle() {
		return Util.waitForPageTitlePresent(Constants.LOGIN_PAGE_TITLE, 5);
	}
	
	public boolean isForgotPwdLinkExist() {
		return Util.doIsDisplayed(forgotPwdlink);
	}
	
	public AccountsPage doLogin(String un, String Pwd) {
		System.out.println("Login with :" + un + "" + Pwd);
		
//		driver.findElement(username).sendKeys(un);
//		driver.findElement(password).sendKeys(Pwd);
//		driver.findElement(logibtn).click();
		Util.doSendKeys(user, un);
		Util.doSendKeys(pwd, Pwd);
		Util.doClick(logibtn);
		return  new AccountsPage(driver);
		
		
		
		
	}
	
}
