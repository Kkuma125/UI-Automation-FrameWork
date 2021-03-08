package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtils;

	public class RegisterPage {

	private WebDriver driver;
	private ElementUtils Util;
	
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By emailID = By.id("input-email");
	private By telePhone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmPassword = By.id("input-confirm");
	private By subscirbeYes = By.xpath("//label[@class='radio-inline'][position()=1]/input");
	private By subscribeNo = By.xpath("//label[@class='radio-inline'][position()=2]/input");
	private By checkBox = By.name("agree");
	private By continueBtn = By.cssSelector("input[type='submit']");
	private By accoutnSuccessmsg = By.cssSelector("#content h1");
	private By logout = By.linkText("Logout");
	private By RegisterLink = By.linkText("Register");
	
	
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		Util = new ElementUtils(this.driver);
	}
	
	public boolean accountsRegistration(String firstName, String lastname, String email,String tele , String passwd, String subscribe){
			
		Util.doSendKeys(this.firstName, firstName);
		Util.doSendKeys(this.lastName, lastname);
		Util.doSendKeys(this.emailID, email);
		Util.doSendKeys(this.telePhone, tele);
		Util.doSendKeys(this.password, passwd);
		Util.doSendKeys(this.confirmPassword, passwd);
		
		if(subscribe.equals("yes")){
			Util.doClick(subscirbeYes);
		}
		else {
			Util.doClick(subscribeNo);
		}
		
		Util.doClick(checkBox);
		Util.doClick(continueBtn);
		
		String text = Util.doGetText(accoutnSuccessmsg);
		if(text.contains(Constants.ACCOUNT_SUCCESS_MESSG)) {
			System.out.println("Accounts Created : " + text);
			Util.doClick(logout);
			Util.doClick(RegisterLink);
			
			return true;
		}
			return false;
			
	}	
	
	
		
	}
