package com.qa.opencart.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

public class LoginPageTest extends BaseTest{

	
	@Epic("Epic - 100: Feature Name : Login Page for Demo Shop Application")
	@Story("User Story - 300: Desgin Login Page for application with different test cases")
	
	@Test
	@Description("login page title test...")
	@Severity(SeverityLevel.MINOR)
	public void loginPageTitleTest(){
		String title= loginPage.getLoginPageTitle();
		System.out.println(title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
		
	} 
	@Test
	@Description("verify forgot pwd link test...")
	@Severity(SeverityLevel.CRITICAL)
	public void forgotPwdLinkTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}
	
	@Test
	@Description("login test ...")
	@Severity(SeverityLevel.BLOCKER)
	public void loginTest() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
		Assert.assertEquals(accountsPage.getAccountsPageTitle(),Constants.ACCOUNTS_PAGE_TITLE);
	}
}
	