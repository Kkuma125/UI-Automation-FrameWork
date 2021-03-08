package com.qa.opencart.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtils;

import io.qameta.allure.Step;

public class LoginPage {
	
    private static final Logger LOGGER = Logger.getLogger(String.valueOf(LoginPage.class));
	
	private WebDriver driver;
	private ElementUtils Util;
	//1.By Locator /
	private By user = By.name("email");
	private By pwd= By.name("password");
	private By logibtn= By.cssSelector("input[type='submit'] ");
	private By forgotPwdlink= By.cssSelector("div.form-group a");
	private By RegisterLink = By.linkText("Register");
	
	//2.Constructor of Page Class
	
	public LoginPage (WebDriver driver) {
		this.driver= driver;
		Util= new ElementUtils(this.driver);
		
	}	
	
	//3.page actions /methods
	@Step("get Login Page Title")
	public String getLoginPageTitle() {
		return Util.waitForPageTitlePresent(Constants.LOGIN_PAGE_TITLE, 10);
	}
	
	@Step("checking is Forgot Pwd Link Exist")
	public boolean isForgotPwdLinkExist() {
		return Util.doIsDisplayed(forgotPwdlink);
	}
	
	@Step("Login with username : {0} and password : {1}")
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
	@Step("navigate To Register Page")
	public RegisterPage navigateToRegisterPage() {
		LOGGER.info("Navigate to Register page....");
		Util.doClick(RegisterLink);
		return new RegisterPage(driver);
	}
}
