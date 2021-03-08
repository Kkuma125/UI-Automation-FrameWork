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

	private By header = By.cssSelector("div#logo a");
	private By accountSectionsHeaders = By.cssSelector("div#content h2");
	private By searchText = By.cssSelector("div#search input[name='search']");
	private By searchButton = By.cssSelector("div#search button[type='button']");
	private By searchItemResult = By.cssSelector("div.product-layout div.product-thumb");
	private By resultsItems = By.cssSelector("div.product-thumb h4 a");

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		Util = new ElementUtils(this.driver);
	}

	public String getAccountsPageTitle() {
		return Util.waitForPageTitlePresent(Constants.ACCOUNTS_PAGE_TITLE, 5);
	}
	
	public String getHeaderValue() {
		if(Util.doIsDisplayed(header)) {
			return Util.doGetText(header);
		}
		return null;
	}

	public int getAccontSectionsCount() {
		return Util.getElements(accountSectionsHeaders).size();
	}

	public List<String> getAccountSectionsList() {
		List<String> accountsList = new ArrayList<>();
		List<WebElement> accSectionList = Util.getElements(accountSectionsHeaders);

		for (WebElement e : accSectionList) {
			String section = e.getText();
			System.out.println(section);
			accountsList.add(section);
		}

		return accountsList;
	}
	
	//Search features Page Actions:
	public boolean doSearch(String productName) {
		Util.doSendKeys(searchText, productName);
		Util.doClick(searchButton);
		if(Util.getElements(searchItemResult).size() > 0) {
			return true;
		}
		return false;
	}
	
	public ProductInfoPage selectProductFromResults(String productName) {
		List<WebElement> resultItemsList = Util.getElements(resultsItems);
		System.out.println("total number of items displayed : " + resultItemsList.size());
		
		for(WebElement e : resultItemsList) {
			if(e.getText().equals(productName)) {
				e.click();
				break;
			}
		}
		
		return new ProductInfoPage(driver);
	}
	
}
