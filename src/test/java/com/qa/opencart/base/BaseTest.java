package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterationPage;
import com.qa.opencart.pages.ResultsPage;

public class BaseTest {
	
	public WebDriver driver;
	public Properties prop;// it is public in nature because all child can access this references
	public DriverFactory df;//create reference of driverfactory to initialize driver
	public LoginPage loginPage;
	
	public AccountsPage accPage;
	public ResultsPage resultPage;
	public ProductInfoPage productInfoPage;
	public RegisterationPage registerationPage;
	public SoftAssert softAssert;
	
	@BeforeTest
	public void setUp() {
		
		df= new DriverFactory();
		prop = df.initProp();// return properties
		driver = df.initDriver(prop);// driver is holding parameter
		//before launching driver we have to initialize prop		
		loginPage = new LoginPage(driver);
		softAssert= new SoftAssert();
		
			
		
		
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
	

}
