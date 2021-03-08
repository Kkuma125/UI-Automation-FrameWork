package com.qa.opencart.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterPageTest extends BaseTest{

	@BeforeClass
	public void accountsPageSetup(){
		registerPage = loginPage.navigateToRegisterPage();
	}
	
	
	@DataProvider
	public Object[][] getRegistrationData() {
		Object data [][]= ExcelUtil.getTestData(Constants.REGISTER_SHEET_NAME);
		return data;
	}
	@Test(dataProvider ="getRegistrationData")
	public void userRegistrationTest(String firstName, String lastname, String email,String tele , String passwd, String subscribe) {
	Assert.assertTrue(registerPage.accountsRegistration(firstName,lastname,email,tele,passwd,subscribe));
		
	}
	
	
	
	
}
