package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchResultsPage;

public class BaseTest {
	
	DriverFactory df;
	
	public Properties prop;
	public WebDriver driver;
	
	public LoginPage loginPage;
	public AccountsPage accPage;
	public SearchResultsPage searchResultsPage;
	public ProductInfoPage productInfoPage;
	public RegisterPage registerPage;
	
	public SoftAssert softAssert;
	
	@Parameters({"browser", "browserversion", "testname"})
	@BeforeTest
	public void setup(String browser, String browserVersion, String testName) {
		df = new DriverFactory();
		prop = df.initProp();
		
			if(browser!=null) {
				prop.setProperty("browser", browser);
				prop.setProperty("browserversion", browserVersion);
				prop.setProperty("testname", testName);
			}
		
		driver = df.initDriver(prop);
		loginPage = new LoginPage(driver);
		
		softAssert = new SoftAssert();
	}
		
	
	@AfterTest
	public void tearDown() {
		driver.quit();
		
	}
	

}
