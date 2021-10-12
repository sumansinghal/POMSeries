package com.qa.opencart.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class RegistrationPageTest extends BaseTest {

	@BeforeClass
	public void regPageSetup() {
		registerationPage = loginPage.navigateToRegisterPage();

	}
	
	@DataProvider
	public void getRegTestData() {
		
		
	}
	
	@Test
	public void registerationTest() {
		Assert.assertTrue(registerationPage.registration("jam", "kevin", "jam@gmail.com", "98765431", "jam@123", "yes"));
	}
	
	

}
