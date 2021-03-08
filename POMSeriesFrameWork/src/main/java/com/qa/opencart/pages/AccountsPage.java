package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtils;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtils Util;
	
	private By Header= By.cssSelector("div#logo a");	
	private By AccountSectionHeaders= By.cssSelector("div#content h2");
	
	
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getAccountsPageTitle() {
		return Util.waitForPageTitlePresent(Constants.ACCOUNTS_PAGE_TITLE, 5);
	}
	
	public String getHeaderValue() {
		if(Util.doIsDisplayed(Header)) {
			return Util.doGetText(Header); 
				
	}
		return null;
		 
	}
	
	public int getAccountsSectionCount() {
		return Util.getElements(AccountSectionHeaders).size();
		
	}
	public List<String> getAccountSectionList() {
		List<String> AccountsList= new ArrayList<>();
		List<WebElement> accountSection= Util.getElements(AccountSectionHeaders);
		
			for(WebElement e : accountSection) {
				String text= e.getText();
				System.out.println(text);
				AccountsList.add(text);
					
	}
			
			return AccountsList;
		
	}
	
	
	
	
	
	
}
