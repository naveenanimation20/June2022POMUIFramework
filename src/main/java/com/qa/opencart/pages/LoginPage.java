package com.qa.opencart.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	// 1. By locator:
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By logoImage = By.cssSelector("img[title='naveenopencart']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By registerLink = By.linkText("Register");
	private By regLink = By.linkText("Register");

	private static final Logger LOG = Logger.getLogger(LoginPage.class);


	// 2. page const....
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	// 3. page actions:
	
	@Step("Waiting for login page title and fetching the title")
	public String getLoginPageTitle() {
		String title = eleUtil.waitForTitleIs(AppConstants.DEFAULT_TIME_OUT, AppConstants.LOGIN_PAGE_TITLE);
		System.out.println("login page title : " + title);
		LOG.info("login page title: " + title);
		return title;
	}

	@Step("Waiting for login page url and fetching the url")
	public boolean getLoginPageUrl() {
		String url = eleUtil.waitForUrlContains(AppConstants.DEFAULT_TIME_OUT, AppConstants.LOGIN_PAGE_URL_PARAM);
		System.out.println("login page url : " + url);
		if (url.contains(AppConstants.LOGIN_PAGE_URL_PARAM)) {
			return true;
		}
		return false;
	}

	@Step("checking forgot pwd link is displayed on login page")
	public boolean isForgotPwdLinkExist() {
		return eleUtil.doEleIsDisplayed(forgotPwdLink);
	}

	@Step("login with username : {0} and password: {1}")
	public AccountsPage doLogin(String username, String pwd) {
		System.out.println("user creds are : " + username + " : " + pwd);
		eleUtil.doSendKeysWithWait(emailId, AppConstants.DEFAULT_LARGE_TIME_OUT, username);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		return new AccountsPage(driver);
	}

	@Step("navigating to register page")
	public RegisterPage navigateToRegisterPage() {
		System.out.println("navigating to register page.....");
		eleUtil.doClick(registerLink);
		return new RegisterPage(driver);
	}

}
