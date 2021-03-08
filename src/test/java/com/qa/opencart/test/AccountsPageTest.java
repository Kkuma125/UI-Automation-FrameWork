package com.qa.opencart.test;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.Errors;

import io.qameta.allure.Epic;
import io.qameta.allure.Story;

public class AccountsPageTest extends BaseTest{
	
	@Epic("Epic - 200: Feature Name : Accounts Page for Demo Shop Application")
	@Story("User Story - 301: Desgin Accounts Page for application with different test cases")
	
	@BeforeClass
	public void accountsPageSetup(){
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
			
	}
	@Test(priority= 1)
	public void accountsPageTitleTest() { 
		String title1= accountsPage.getAccountsPageTitle();
		System.out.println("Accounts Page title is :" + title1);	
		Assert.assertEquals(title1, Constants.ACCOUNTS_PAGE_TITLE,Errors.TITLE_NOT_MATCHED);
	}
	
	@Test(priority= 2)
	public void verifyAccountsPageHeaderTest() {
		String header= accountsPage.getHeaderValue();
		System.out.println(header);
		Assert.assertEquals(header, Constants.ACCOUNTS_PAGE_HEADER,Errors.HEADER_NOT_MATCHED);
		
	}
	
	@Test(priority= 3)
	public void verifyAccountsPageSectionsCountTest() {
		Assert.assertTrue(accountsPage.getAccontSectionsCount() == Constants.ACCOUNTS_PAGE_SECTION_COUNT);
	}
	
	@Test(priority= 4)
	public void verifySectionsListTest() {
		List<String> text= accountsPage.getAccountSectionsList();
		System.out.println("this is Account section list is :" + text);
		Assert.assertEquals(text, Constants.getAccSectionsList());
		
	}
	
	@DataProvider
	public Object[][] searchData() {
		return new Object[][] {{"iMac"}, 
							   {"iPhone"}, 
							   {"Macbook"}};
	}
	@Test(priority= 5,dataProvider = "searchData")
	public void searchTest(String productName) {
		Assert.assertTrue(accountsPage.doSearch(productName));
	}
	
//	@Test(priority= 6)
//	public void searchTest_MacBook(String productName) {
//		Assert.assertTrue(accountsPage.doSearch(productName));
//	}
	
	
	@Test(priority= 6)
	public void verifyProductResultsTest() {
		accountsPage.doSearch("iMac");
		accountsPage.selectProductFromResults("iMac");
		
		
	}
}
 