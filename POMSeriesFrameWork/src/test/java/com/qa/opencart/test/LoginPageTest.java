package com.qa.opencart.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.utils.Constants;

public class LoginPageTest extends BaseTest{

	@Test
	public void loginPageTitleTest(){
		String title= loginPage.getLoginPageTitle();
		System.out.println(title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
		
	} 
	@Test
	public void forgotPwdLinkTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}
	
	@Test	
	public void loginTest() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
		Assert.assertEquals(accountsPage.getAccountsPageTitle(),Constants.ACCOUNTS_PAGE_TITLE);
	}
}
	