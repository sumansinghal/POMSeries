
package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * 
 * @author Suman Agarwal
 *
 */
public class DriverFactory {
	
	public WebDriver driver; // creating references of WebDriver
	public Properties prop; // creating properties references
	public OptionsManager optionsManager;
	public static String highlight;//we are using static as unnecessary to create object otherwise
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();//thread local driver
	/**
	 * This method is used to initilize the webdriver on the basis of given browser name...
	 * @param browser
	 * @return this method will return driver
	 */
	
	public WebDriver initDriver(Properties prop) {
		optionsManager = new OptionsManager(prop);
		highlight = prop.getProperty("highlight");
				
		String browser = prop.getProperty("browser").trim();	
		System.out.println("browser name is :" + browser);
		if(browser.equalsIgnoreCase("chrome")) {
//			WebDriverManager.chromedriver().setup();
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
//			driver = new ChromeDriver(optionsManager.getChromeOptions());
		}
		else if(browser.equalsIgnoreCase("firefox")){
			WebDriverManager.firefoxdriver().setup();
//			driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			
		}
		else if(browser.equalsIgnoreCase("safari")){
			driver = new SafariDriver();
		
	}
		else {
			System.out.println("please pass the correct browser name:" +browser);
		}
	
	getDriver().manage().window().maximize();
	getDriver().manage().deleteAllCookies();
	getDriver().get(prop.getProperty("url"));
	
	return getDriver();
	
	}
	
	public synchronized WebDriver getDriver() {
		return tlDriver.get();
	}
	/**
	 * This method is used to initialize properties on the basis of given environment
	 * @return this method return prop
	 * @throws FileNotFoundException 
	 */
	public Properties initProp() {
		prop = new Properties();
		FileInputStream ip = null;

		String env = System.getProperty("env");
		if (env == null) {
			try {
				ip = new FileInputStream("./src/test/resources/config/config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Running on Environment: " + env);

			try {
				switch (env.toLowerCase()) {
				case "qa":
					ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
					break;
				case "stage":
					ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
					break;
				case "dev":
					ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
					break;

				default:
					System.out.println(".....Please pass the right environment....." + env);
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}
	
	/*
	 * take screenshot
	 */
	
	public String getScreenshot() {
		File srcFile = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+"/screenshot/"+System.currentTimeMillis()+".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
		
		}
			
}
