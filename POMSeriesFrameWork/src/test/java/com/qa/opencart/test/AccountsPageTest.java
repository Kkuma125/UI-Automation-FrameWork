package com.qa.opencart.test;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;

public class AccountsPageTest extends BaseTest{
	
	@BeforeClass
	public void accountsPageSetup(){
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
			
	}
	@Test(priority= 1)
	public void accountsPageTitleTest() { 
		String title1= accountsPage.getAccountsPageTitle();
		System.out.println("Accounts Page title is :" + title1);	
		Assert.assertEquals(title1, Constants.ACCOUNTS_PAGE_TITLE);
	}
	
	@Test(priority= 2)
	public void verifyAccountsPageHeaderTest() {
		String header= accountsPage.getHeaderValue();
		System.out.println(header);
		Assert.assertEquals(header, Constants.ACCOUNTS_PAGE_HEADER);
		
	}
	
	@Test(priority= 3)
	public void verifyAccountsPageSectionsCount() {
		Assert.assertTrue(accountsPage.getAccountsSectionCount() == Constants.ACCOUNTS_PAGE_SECTION_COUNT);
	}
	
	@Test(priority= 4)
	public void verifySectionsListTest() {
		List<String> text= accountsPage.getAccountSectionList();
		System.out.println("this is Account section list is :" + text);
		
		
	}
	
}
 