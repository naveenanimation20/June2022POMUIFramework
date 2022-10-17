package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class RegisterPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmpassword = By.id("input-confirm");

	private By agreeCheckBox = By.name("agree");
	private By continueButton = By.xpath("//input[@type='submit' and @value='Continue']");
	private By subscribeYes = By.xpath("//label[@class='radio-inline']/input[@value='1' and @type='radio']");
	private By subscribeNo = By.xpath("//label[@class='radio-inline']/input[@value='0' and @type='radio']");

	private By registerSuccessMesg = By.cssSelector("div#content h1");
	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	/**
	 * user registration
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param telephone
	 * @param password
	 * @param subscribe
	 */
	public String userRegister(String firstName, String lastName, 
					String email, String telephone, String password, String subscribe) {

		
		eleUtil.doSendKeysWithVisibleElement(this.firstName, AppConstants.DEFAULT_LARGE_TIME_OUT, firstName);
		eleUtil.doSendKeys(this.lastName, lastName);
		eleUtil.doSendKeys(this.email, email);
		eleUtil.doSendKeys(this.telephone, telephone);
		eleUtil.doSendKeys(this.password, password);
		eleUtil.doSendKeys(this.confirmpassword, password);

		
			if(subscribe.equalsIgnoreCase("yes")) {
				eleUtil.doClick(this.subscribeYes);
			}
			else {
				eleUtil.doClick(this.subscribeNo);
			}
		
		eleUtil.doClick(this.agreeCheckBox);
		eleUtil.doClick(this.continueButton);
		
		String succMesg = eleUtil.waitForElementVisible(registerSuccessMesg, AppConstants.DEFAULT_LARGE_TIME_OUT).getText();
		System.out.println("Success Messsggggg=====>" + succMesg);
		
		eleUtil.doClick(logoutLink);
		eleUtil.doClick(registerLink);
		
		
		return succMesg;
		
	}

}
