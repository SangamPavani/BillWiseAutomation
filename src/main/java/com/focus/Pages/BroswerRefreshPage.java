package com.focus.Pages;

import java.awt.Desktop.Action;
import java.io.IOException;

import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.focus.base.BaseEngine;
import com.focus.supporters.ExcelReader;

public class BroswerRefreshPage extends BaseEngine {

	private static ExcelReader excelReader;
	private static WebElement loginTitle;
	private static String url;

	@FindBy(xpath = "//*[@id='userprofile']/li/span[2]")
	private static WebElement logoutOption;

	@FindBy(xpath = "//span[@class='hidden-xs']")
	private static WebElement userNameDisplay;

	@FindBy(xpath = "//*[@id='txtUsername']")
	private static WebElement username;

	@FindBy(id = "txtPassword")
	private static WebElement password;

	@FindBy(css = "* /deep/ #clearBrowsingDataConfirm")
	private static WebElement clearBrowserDataBtn;

	public boolean checkLogoutOption() throws InterruptedException, IOException {

		// browser takes place refresh
		getDriver().navigate().refresh();

		Thread.sleep(2000);

		try {

			getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(userNameDisplay));
			userNameDisplay.click();

			Thread.sleep(2000);
			getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(logoutOption));
			logoutOption.click();

		} catch (Exception e) {

			System.err.println(" Displayed Logout Screen ");
		}
		// delete all cookies and time out
		initActivities();

		Thread.sleep(1999);

		getDriver().get("chrome://settings/clearBrowserData");

		Thread.sleep(1999);

		Runtime.getRuntime().exec(getBaseDir() + "\\autoIt\\scripts\\clearingBrowswerHistory.exe");

		Thread.sleep(3999);

		for (int i = 0; i < 8; i++) {
			System.out.println(" Entered Loop : " + i);
			getAction().sendKeys(Keys.TAB);
		}

		Thread.sleep(2999);

		LoginPage lp = new LoginPage(getDriver());

		getDriver().get("http://localhost/focus8w");

		url = "http://localhost/focus8w";

		Thread.sleep(1999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(username));
		boolean actusername = username.isDisplayed();
		boolean expusername = true;

		System.out.println("username Display Status : " + actusername + " Value Exp : " + expusername);

		if (actusername == expusername) {
			return true;

		} else {
			return false;

		}
	}

	public BroswerRefreshPage(WebDriver driver)

	{
		PageFactory.initElements(driver, this);
	}

}
