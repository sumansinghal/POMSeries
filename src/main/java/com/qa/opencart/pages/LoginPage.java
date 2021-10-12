package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil elementUtil;

	// By Locators
	// it is template
	private By emailID = By.id("input-email");
	private By password = By.id("input-password");
	private By loginButton = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By registerLink = By.linkText("Register");

	// 2 page constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);

	}

	// 3. Page Actions/ methods/features-- will be called by testng have to be
	// public in nature
	public String getLoginPageTitle() {
		return driver.getTitle();
	}

	public String getLoginPageUrl() {
		return driver.getCurrentUrl();
	}

	public boolean isForgotPwdLinkExist() {
		return elementUtil.doIsDisplayed(forgotPwdLink);

	}

	public boolean isRegisterLinkExist() {
		return elementUtil.doIsDisplayed(registerLink);

	}

	public AccountsPage doLogin(String un, String pwd) {
		elementUtil.doSendKeys(emailID, un);
		elementUtil.doSendKeys(password, pwd);
		elementUtil.doClick(loginButton);
		return new AccountsPage(driver); // returning the object of account page
	}

	public RegisterationPage navigateToRegisterPage() {
		elementUtil.doClick(registerLink);
		return new RegisterationPage(driver);

	}
}
