package com.qa.opencart.utils;



import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.factory.DriverFactory;

	public class ElementUtils {
	private WebDriver driver;
	private JavaScriptUtil jsUtil;
	
	public ElementUtils(WebDriver driver) {
		this.driver=driver;
		jsUtil= new JavaScriptUtil(this.driver);
		
	}
	
	public  WebElement getElement(By Locator) {
		WebElement element= driver.findElement(Locator);
		if(DriverFactory.highlight.equals("true")) {
			jsUtil.flash(element);
		}
		return element;
		
	}	
	
	public List<WebElement> getElements(By Locator) {
		return driver.findElements(Locator);
		
	}
	public  void doSendKeys(By Locator,String value) {
			getElement(Locator).sendKeys(value);
		
	}
		
	public  void doClick(By Locator) {
			getElement(Locator).click();
	
	}
	
	public boolean doIsDisplayed(By Locator) {
		return getElement(Locator).isDisplayed();
	}
	
	public String doGetText(By Locator) {
		return getElement(Locator).getText();
		
		
	}
	/**
	 * Clicks in the middle of the given element. 
	 * Equivalent to: Actions.moveToElement(onElement).click()
	 * @param Locator
	 */
	
	public  void doActionsClick(By Locator) {
		Actions action= new Actions(driver);
		action.click(getElement(Locator)).perform();
		//action.moveToElement(getElement(Locator)).click().build().perform();
	}
	
	public  void doActionsSendKeys(By Locator,String value) {
		Actions action= new Actions(driver);
		action.sendKeys(getElement(Locator), value).perform(); 
	}
	
	
	//**********************************Drop Down Utils********************************//
	
	public  void doSelectDropDownByVisibleText(By Locator ,String value) {
		Select select = new Select(getElement(Locator));
		select.selectByVisibleText(value);
	}

	public  void doSelectDropDownByIndex(By Locator ,int index) {
		Select select = new Select(getElement(Locator));
		select.selectByIndex(index);
					
	}
	public  void doSelectDropDownByValue(By Locator ,String value) {
		Select select = new Select(getElement(Locator));
		select.selectByValue(value);
			
	}

	
	
	
	public  void selectDropDownValue(By Locator ,String value) {
		List<WebElement> optionslist = getElements(Locator);
		
		System.out.println(optionslist.size());
		
		for (WebElement e : optionslist) {
			String text = e.getText();
			System.out.println(text); 
			
		if(text.equals(value)) {
			e.click();
			break;
		}	
		 
		}
		
	}
	
	
	public  void selectFromSuggetionList(By suggestion_list, String value)  {
		
		List<WebElement> resultlist =getElements(suggestion_list);
		System.out.println(resultlist.size());
			
		for(WebElement e:resultlist ) {
			String text=e.getText();
			System.out.println(text);
			
			if(text.equals(value)) {
				e.click();	
				break;
			}
			
		
		}
		
		
	}

//*************************************************Actions Utils**********************************//
public  List<String> getRightClickList(By Locator, By List) {
		
		Actions action= new Actions(driver);
		action.contextClick(getElement(Locator)).perform();
		List<String> Menu= new ArrayList<String>();
		List<WebElement> rightMenu =getElements(Locator);
		System.out.println(rightMenu.size());
		
		for(WebElement e: rightMenu) {
			String text=e.getText();
			Menu.add(text);	
			
		}
			return Menu;
		
	} 
	
	 	
	
	
	public  void ClickOption(By Locator,By List,String value) {
		Actions action= new Actions(driver);
		action.contextClick(getElement(Locator)).perform();
		List<WebElement> rightMenu =getElements(Locator);
		System.out.println(rightMenu.size());
		
		for(WebElement e: rightMenu) {
			String text=e.getText();
			if(text.equals(value)) {
				e.click();
				break;	
			}
			
		}
			
		
	}
		
	public  void DragAndDrop(WebElement Locator,WebElement List) {
		Actions action = new Actions(driver);
		action.clickAndHold((WebElement) Locator).moveToElement((WebElement) List).release().build().perform();
		
	}	
	//**************************************ChoiceList***************
	public  void selectChoiceFromDropDown(By Choicelist,String...value) {
		
		List<WebElement> ChoiceList= getElements(Choicelist);
		if(!value[0].equalsIgnoreCase("All")) {
			
		for(int i=0;i<ChoiceList.size();i++) {
			String text=ChoiceList.get(i).getText();
			System.out.println(text);
			
				for(int j=0;j<value.length;j++) {
					if(text.equals(value[j])) {
						ChoiceList.get(i).click();
						break;
					}
				}
			
			}
		
		}
	
		//select all values
		else {
			try {
			for(WebElement e: ChoiceList) {
				e.click();
			}
			
			}
			catch(Exception e){
					
			}
		}	
	}
	//*************************************Dynamic WebTable************
	public  String getWicketTakerName(String Playername) {
		return getElement(By.xpath("//a[text()='"+Playername+"']//parent::td//following-sibling::td/span")).getText();
	}
	
	public  List<String> getScoreList(String Playername) {
		System.out.println("getting score card for: "+Playername);
		List<String> scorecard = new ArrayList<String>();
		List<WebElement>list =getElements(By.xpath("//a[text()='"+Playername+"']//parent::td//following-sibling::td"));
		System.out.println(list.size());
		
		for(int i=1;i<list.size();i++) {
			String text = list.get(i).getText();
			if(!text.isBlank()) {
				scorecard.add(text);
			
				 
			} 
			
		}
			return scorecard;
	}
 
	
	//*************************************Alert Util*******************************************

	public  Alert isAlertPresent(int TimeOut) {
		
	WebDriverWait wait = new WebDriverWait(driver,TimeOut);
	return wait.until(ExpectedConditions.alertIsPresent()); 
		
	}
	
	public  String getAlertText(int TimeOut) {
		return isAlertPresent(TimeOut).getText();
		
	}
	public  void doAcceptAlert(int TimeOut) {
		isAlertPresent(TimeOut).accept();
	}
	
	public  void doDismissAlert(int TimeOut) {
		isAlertPresent(TimeOut).dismiss();
	
	}	
	/**
	 * An expectation for checking that an element, known to be present on the
	 *  DOM of a page, isvisible. Visibility means that the element is not only displayed but also has a height andwidth that is greater than 0.
	 * @param Locator
	 * @param TimeOut
	 * @return
	 */
	public  WebElement waitForElementVisible(By Locator ,int TimeOut) {
		WebDriverWait wait= new WebDriverWait(driver,TimeOut);
		return wait.until(ExpectedConditions.visibilityOf(getElement(Locator)));
	
}
	public  WebElement waitForElementPresent(By Locator ,int TimeOut, int PollingTime) {
		WebDriverWait wait= new WebDriverWait(driver,TimeOut,PollingTime);
		return wait.until(ExpectedConditions.presenceOfElementLocated(Locator));
		
	}
	
	public String waitForPageTitlePresent(String titleValue, int TimeOut) {
		WebDriverWait wait= new WebDriverWait(driver, TimeOut);
		wait.until(ExpectedConditions.titleContains(titleValue));
		return driver.getTitle();
	}
	/**
	 * An expectation for checking an element is visible and enabled such that you can click it.
	 * @param Locator
	 * @param TimeOut
	 */
	public  void ActionsclickWhenReady(By Locator ,int TimeOut) {
		WebDriverWait wait= new WebDriverWait(driver,TimeOut);
		WebElement ele=wait.until(ExpectedConditions.elementToBeClickable(Locator));
		Actions action = new Actions(driver);
		action.moveToElement(ele).click().build().perform();
		
	}
	public  void clickWhenReady(By Locator ,int TimeOut) {
		WebDriverWait wait= new WebDriverWait(driver,TimeOut);
		WebElement ele=wait.until(ExpectedConditions.elementToBeClickable(Locator));
		ele.click();
		
	}
	public List<WebElement> visibilityOfAllElements(By Locator, int Timeout) {
		WebDriverWait wait= new WebDriverWait(driver,Timeout);
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Locator));
	}
		
	
	public void getPageElementText(By Locator, int Timeout) {
		visibilityOfAllElements(Locator, Timeout).stream().forEach(ele-> System.out.println(ele.getText()));
	}
	
	public int getPageElementsText(By Locator, int Timeout) {
		return visibilityOfAllElements(Locator, Timeout).size();
	}
	
	
	/**
	 * This is Custom method to provide the dynamic wait to find the webelement
	 * @return 
	 * @return 
	 * @throws InterruptedException 
	 */
	
	public   WebElement retryingElement(By Locator)  {
		WebElement ele = null;
		int attempts= 0;
		while (attempts<30) {
			try {
			ele= driver.findElement(Locator);
			break;
		} 
		catch (NoSuchElementException e) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e1) {
			}
		}	
		catch (StaleElementReferenceException e) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e1) {	
			}
		}
			System.out.println("element is not found: "+ (attempts+1));
		attempts++;
	 }
		return ele;		 
		
		}
	
	
	public WebElement waitForElementWithFluentWait(By Locator, int Timeout, int pollingTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(Timeout))
				.pollingEvery(Duration.ofSeconds(pollingTime))
				.ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class);
			
		return wait.until(ExpectedConditions.presenceOfElementLocated(Locator));
		
		}
			
	}


