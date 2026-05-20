package com.focus.Pages;

import com.focus.base.BaseEngine;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.text.ParseException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.xerces.impl.dv.ValidatedInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.server.handler.SendKeys;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
//import org.testng.remote.strprotocol.AbstractRemoteTestRunnerClient;

import com.focus.base.BaseEngine;
import com.focus.supporters.ExcelReader;
import com.focus.utilities.POJOUtility;

public class BRSPage extends BaseEngine {

	// private static String xlfile;
	private static String resPass = "Pass";
	private static String resFail = "Fail";
	private static ExcelReader excelReader;

	private static String xlfile = getBaseDir() + "\\src\\main\\resources\\testdata\\FocusTestData.xlsx";

	@FindBy(id = "ddlCompany")
	private static WebElement companyDropDownList;

	public static String xlSheetName = "BRS";

	public boolean checkLogin()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		getDriver().navigate().refresh();

		excelReader = new ExcelReader(POJOUtility.getExcelPath());
		Thread.sleep(1999);

		getDriver().navigate().refresh();

		Thread.sleep(1999);
		
		  
		  Thread.sleep(3500);
		  
		  prongHornStartAtAdminLevel();
		  
		  Thread.sleep(8000);
		 
		LoginPage lp = new LoginPage(getDriver());

		Thread.sleep(3000);

		String unamelt = "su";

		String pawslt = "su";

		lp.enterUserName(unamelt);

		Thread.sleep(2000);

		lp.enterPassword(pawslt);

		String compname = excelReader.getCellData(xlSheetName, 11, 5);

		Select oSelect = new Select(companyDropDownList);

		List<WebElement> elementCount = oSelect.getOptions();

		int cqSize = elementCount.size();

		System.out.println("CompanyDropdownList Count :" + cqSize);

		int i;

		for (i = 0; i < elementCount.size(); i++) {

			elementCount.get(i).getText();

			String optionName = elementCount.get(i).getText();
			if (optionName.toUpperCase().startsWith(compname.toUpperCase())) {
				System.out.println("Company List:" + elementCount.get(i).getText());
				elementCount.get(i).click();
			}

		}

		Thread.sleep(2000);

		lp.clickOnSignInBtn();

		Thread.sleep(8000);
		
	/*	if(password.isDisplayed())
		{
			lp.enterPassword(pawslt);
			lp.clickOnSignInBtn();
			Thread.sleep(4000);
		}
*/
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(companyLogo));
		companyLogo.click();

		if (homeMenu.isDisplayed()) {
			System.out.println("Test Pass :Logined to BRS Company");
			excelReader.setCellData(xlfile, xlSheetName, 8, 8, resPass);
			return true;

		} else {
			System.out.println("Test Fail :Logined to BRS Company");
			excelReader.setCellData(xlfile, xlSheetName, 8, 8, resFail);
			return false;

		}

	}

	@FindBy(xpath = "//*[@id='83']")
	private static WebElement bankReconciliationReport;

	public static void checkUserFriendlyMessage()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		try {
			getFluentWebDriverWait().until(ExpectedConditions.visibilityOf(errorMessage));
			String actErrorMessage = errorMessage.getText();

			System.out.println("Open Page then Message Display  :  " + actErrorMessage);
			getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(errorMessageCloseBtn));
			errorMessageCloseBtn.click();

			/*
			 * try { System.out.println("In Try Block Validation Message  :  " +
			 * actErrorMessage);
			 * getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(
			 * errorMessageCloseBtn)); errorMessageCloseBtn.click(); } catch(Exception ee) {
			 * System.out.println("In Catch Block Validation Message  :  " +
			 * actErrorMessage); }
			 */
		} catch (Exception e) {
			System.err.println("Error Message NOT Found or NOT Clickable");
			System.err.println(e.getMessage());

			String Exception = e.getMessage();
		}
	}

	public static String checkValidationMessage(String ExpMessage)
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		try {
			getFluentWebDriverWait().until(ExpectedConditions.visibilityOf(errorMessage));
			String actErrorMessage = errorMessage.getText();
			String expErrorMessage = ExpMessage;

			try {
				getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(errorMessageCloseBtn));
				errorMessageCloseBtn.click();

				System.out.println("ValidationMessage  :  " + actErrorMessage + " Value Expected : " + expErrorMessage);

				return actErrorMessage;
			} catch (Exception ee) {

				System.out.println("ValidationMessage  :  " + actErrorMessage + " Value Expected : " + expErrorMessage);

				return actErrorMessage;
			}
		} catch (Exception e) {
			System.err.println("Error Message NOT Found or NOT Clickable");
			System.err.println(e.getMessage());

			String Exception = e.getMessage();

			return Exception;
		}
	}

	public static void restoreCompany()
			throws InterruptedException, IOException, AWTException, EncryptedDocumentException, InvalidFormatException {

		checkRestoreOptionsCompanyAndLogin("BRS", "BRS");

		/*
		 * Thread.sleep(1000);
		 * 
		 * getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(
		 * homeMenu)); homeMenu.click();
		 * 
		 * getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(
		 * dataMangementMenu)); dataMangementMenu.click();
		 * 
		 * getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(
		 * restore)); restore.click();
		 * 
		 * getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(
		 * folderpathExpandBtn)); folderpathExpandBtn.click();
		 * 
		 * Thread.sleep(3000);
		 * 
		 * Robot rb = new Robot(); StringSelection str = new
		 * StringSelection(getBaseDir() + "\\requiredBackUps\\BRS.fbak");
		 * Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);
		 * 
		 * rb.keyPress(KeyEvent.VK_CONTROL); rb.keyPress(KeyEvent.VK_V);
		 * 
		 * rb.keyRelease(KeyEvent.VK_CONTROL); rb.keyRelease(KeyEvent.VK_V);
		 * 
		 * rb.keyPress(KeyEvent.VK_ENTER); rb.keyRelease(KeyEvent.VK_ENTER);
		 * 
		 * Thread.sleep(3000);
		 * 
		 * getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(
		 * restoreCompanyBtn)); restoreCompanyBtn.click();
		 * 
		 * Thread.sleep(3000);
		 * 
		 * try { getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(
		 * overRideYesBtn)); overRideYesBtn.click();
		 * 
		 * System.err.println("COMPANY EXISTS");
		 * 
		 * Thread.sleep(130000);
		 * 
		 * } catch (Exception e) { System.err.println("NO OLDER COMPANY EXISTS"); }
		 * 
		 * if (getIsAlertPresent()) { System.err.println("Alert Displayed");
		 * System.err.println(getAlert().getText()); getWaitForAlert();
		 * 
		 * getAlert().accept(); }
		 * 
		 * 
		 * Thread.sleep(3000);
		 * 
		 * logout();
		 * 
		 * Thread.sleep(3000);
		 * 
		 * checkLoginToSelectedCompany("BRS", "su", "su");
		 * 
		 * Thread.sleep(2500);
		 * 
		 * reindexClear();
		 * 
		 */
	}

	public boolean checkBankReconciliationReport()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		excelReader = new ExcelReader(POJOUtility.getExcelPath());

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(financialsMenu));
		financialsMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(financialsReportsMenu));
		financialsReportsMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(cashAndBankBooksMenu));
		cashAndBankBooksMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(bankReconciliationReport));
		bankReconciliationReport.click();

		boolean novalidationConfirmationMessage = validationConfirmationMessage.getText().isEmpty();

		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = excelReader.getCellData(xlSheetName, 14, 6);

		excelReader.setCellData(xlfile, xlSheetName, 14, 7, actvalidationConfirmationMessage);

		System.out.println("validationConfirmationMessage : " + actvalidationConfirmationMessage + " Value Expected : "
				+ expvalidationConfirmationMessage);

		Thread.sleep(9000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportbankTxt));
		reportbankTxt.click();
		reportbankTxt.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME);
		reportbankTxt.sendKeys(Keys.SPACE);

		int bankListCount = bankList.size();
		for (int i = 0; i < bankListCount; i++) {

			String data = bankList.get(i).getText();
			if (data.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 15, 5))) {
				bankList.get(i).click();
			}
		}
		Thread.sleep(2000);

		reportbankTxt.sendKeys(Keys.TAB);

		Thread.sleep(1500);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(LoadBtn));
		ClickUsingJs(LoadBtn);

		Thread.sleep(3999);

		int bankRecDocumentNoListCount = bankRecDocumentNoList.size();

		ArrayList<String> bankRecDocumentNoListArray = new ArrayList<>();

		for (int i = 0; i < bankRecDocumentNoListCount; i++) {

			String data = bankRecDocumentNoList.get(i).getText();
			bankRecDocumentNoListArray.add(data);
		}

		String actbankRecDocumentNoList = bankRecDocumentNoListArray.toString();
		String expbankRecDocumentNoList = excelReader.getCellData(xlSheetName, 15, 6);

		excelReader.setCellData(xlfile, xlSheetName, 15, 7, actbankRecDocumentNoList);

		System.out.println(" Act bankRecDocumentNoList : " + actbankRecDocumentNoList);
		System.out.println(" Exp bankRecDocumentNoList : " + expbankRecDocumentNoList);

		int bankRecDocumentTYPEListCount = bankRecDocumentTYPEList.size();

		ArrayList<String> bankRecDocumentTYPEListArray = new ArrayList<>();

		for (int i = 0; i < bankRecDocumentTYPEListCount; i++) {

			String data = bankRecDocumentTYPEList.get(i).getText();
			bankRecDocumentTYPEListArray.add(data);
		}

		String actbankRecDocumentTYPEList = bankRecDocumentTYPEListArray.toString();
		String expbankRecDocumentTYPEList = excelReader.getCellData(xlSheetName, 16, 6);

		excelReader.setCellData(xlfile, xlSheetName, 16, 7, actbankRecDocumentTYPEList);

		System.out.println(" Act bankRecDocumentTYPEList : " + actbankRecDocumentTYPEList);
		System.out.println(" Exp bankRecDocumentTYPEList : " + expbankRecDocumentTYPEList);

		if (actbankRecDocumentNoList.equalsIgnoreCase(expbankRecDocumentNoList)
				&& actbankRecDocumentTYPEList.equalsIgnoreCase(expbankRecDocumentTYPEList)) {
			System.out.println("Test Pass: Displayed all the vouchers in Bank reconcliation Report");
			excelReader.setCellData(xlfile, xlSheetName, 14, 8, resPass);
			return true;
		} else {
			System.out.println("Test Fail: Displayed all the vouchers in Bank reconcliation Report");
			excelReader.setCellData(xlfile, xlSheetName, 14, 8, resFail);
			return false;
		}
	}

	@FindBy(xpath = "//*[@id='BRTable_body']/tr/td[1]")
	private static List<WebElement> BRSBodyGirdRowNoList;

	@FindBy(xpath = "//*[@id='BRTable_body']/tr/td[2]")
	private static List<WebElement> bankRecDocumentlinkStatusList;

	@FindBy(xpath = "//*[@id='BRTable_body']/tr/td[5]")
	private static List<WebElement> bankRecDocumentNoList;

	@FindBy(xpath = "//*[@id='BRTable_body']/tr/td[9]")
	private static List<WebElement> bankRecDocumentTYPEList;

	@FindBy(xpath = "//*[@id='BRTable_body']/tr/td[5]")
	private static List<WebElement> bankRecDocumentNuberList;

	@FindBy(xpath = "//*[@id='BRTable_body']/tr")
	private static List<WebElement> bankRecGridRowList;

	@FindBy(xpath = "//td[text()='Payments VAT']//..//td")
	private static List<WebElement> paymentrow;

	@FindBy(xpath = "//input[@id='OptCtrlBank']")
	private static WebElement reportbankTxt;

	@FindBy(xpath = "//td[text()='Payments VAT']")
	private static WebElement paymentTxt;

	@FindBy(xpath = "//*[@id='OptCtrlBank_table_body']/tr/td[2]")
	private static List<WebElement> reportbankList;

	@FindBy(xpath = "//*[@id='OptCtrlBank_table_body']/tr/td[2]")
	private static List<WebElement> bankList;

	@FindBy(xpath = "//Select[@id='sortOrder']")
	private static WebElement BRSortDrpdwn;

	public boolean checkBankReportOnBasisOFSortOrderWithDateAndDocnumberCombination()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		excelReader = new ExcelReader(POJOUtility.getExcelPath());
		Thread.sleep(2000);

		Select s1 = new Select(BRSortDrpdwn);
		s1.selectByValue("2");

		Thread.sleep(3500);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(LoadBtn));
		ClickUsingJs(LoadBtn);

		Thread.sleep(9899);

		int bankRecDocumentNoListCount = bankRecDocumentNoList.size();

		ArrayList<String> bankRecDocumentNoListArray = new ArrayList<>();

		for (int i = 0; i < bankRecDocumentNoListCount; i++) {

			String data = bankRecDocumentNoList.get(i).getText();
			bankRecDocumentNoListArray.add(data);
		}

		String actbankRecDocumentNoList = bankRecDocumentNoListArray.toString();
		String expbankRecDocumentNoList = excelReader.getCellData(xlSheetName, 21, 6);
		excelReader.setCellData(xlfile, xlSheetName, 21, 7, actbankRecDocumentNoList);

		System.out.println(" Act bankRecDocumentNoList : " + actbankRecDocumentNoList);
		System.out.println(" Exp bankRecDocumentNoList : " + expbankRecDocumentNoList);

		int bankRecDocumentTYPEListCount = bankRecDocumentTYPEList.size();
		ArrayList<String> bankRecDocumentTYPEListArray = new ArrayList<>();

		for (int i = 0; i < bankRecDocumentTYPEListCount; i++) {

			String data = bankRecDocumentTYPEList.get(i).getText();
			bankRecDocumentTYPEListArray.add(data);
		}

		String actbankRecDocumentTYPEList = bankRecDocumentTYPEListArray.toString();
		String expbankRecDocumentTYPEList = excelReader.getCellData(xlSheetName, 22, 6);
		excelReader.setCellData(xlfile, xlSheetName, 22, 7, actbankRecDocumentTYPEList);

		System.out.println(" Act bankRecDocumentTYPEList : " + actbankRecDocumentTYPEList);
		System.out.println(" Exp bankRecDocumentTYPEList : " + expbankRecDocumentTYPEList);

		if (actbankRecDocumentNoList.equalsIgnoreCase(expbankRecDocumentNoList)
				&& actbankRecDocumentTYPEList.equalsIgnoreCase(expbankRecDocumentTYPEList)) {
			System.out.println("Test Pass: Displayed all the vouchers in Bank reconcliation Report");
			excelReader.setCellData(xlfile, xlSheetName, 20, 8, resPass);
			return true;
		} else {
			System.out.println("Test Fail: Displayed all the vouchers in Bank reconcliation Report");
			excelReader.setCellData(xlfile, xlSheetName, 20, 8, resFail);
			return false;
		}
	}

	@FindBy(xpath = "//select[@id='sortByColumn']")
	private static WebElement BRSSortByOrderDrpdwn;

	public boolean checkBRSReportWithSortByColoumnDebitAmount()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		excelReader = new ExcelReader(POJOUtility.getExcelPath());
		Thread.sleep(2000);

		Select s1 = new Select(BRSSortByOrderDrpdwn);
		s1.selectByValue("6");

		Thread.sleep(8999);

		int bankRecDocumentNoListCount = bankRecDocumentNoList.size();

		ArrayList<String> bankRecDocumentNoListArray = new ArrayList<>();

		for (int i = 0; i < bankRecDocumentNoListCount; i++) {

			String data = bankRecDocumentNoList.get(i).getText();
			bankRecDocumentNoListArray.add(data);
		}

		String actbankRecDocumentNoList = bankRecDocumentNoListArray.toString();
		String expbankRecDocumentNoList = excelReader.getCellData(xlSheetName, 25, 6);
		excelReader.setCellData(xlfile, xlSheetName, 25, 7, actbankRecDocumentNoList);

		System.out.println(" Act bankRecDocumentNoList : " + actbankRecDocumentNoList);
		System.out.println(" Exp bankRecDocumentNoList : " + expbankRecDocumentNoList);

		int bankRecDocumentTYPEListCount = bankRecDocumentTYPEList.size();
		ArrayList<String> bankRecDocumentTYPEListArray = new ArrayList<>();

		for (int i = 0; i < bankRecDocumentTYPEListCount; i++) {

			String data = bankRecDocumentTYPEList.get(i).getText();
			bankRecDocumentTYPEListArray.add(data);
		}

		String actbankRecDocumentTYPEList = bankRecDocumentTYPEListArray.toString();
		String expbankRecDocumentTYPEList = excelReader.getCellData(xlSheetName, 26, 6);
		excelReader.setCellData(xlfile, xlSheetName, 26, 7, actbankRecDocumentTYPEList);

		System.out.println(" Act bankRecDocumentTYPEList : " + actbankRecDocumentTYPEList);
		System.out.println(" Exp bankRecDocumentTYPEList : " + expbankRecDocumentTYPEList);

		if (actbankRecDocumentNoList.equalsIgnoreCase(expbankRecDocumentNoList)
				&& actbankRecDocumentTYPEList.equalsIgnoreCase(expbankRecDocumentTYPEList)) {
			System.out.println("Test Pass: Displayed all the vouchers in Bank reconcliation Report With Debit Amount");
			excelReader.setCellData(xlfile, xlSheetName, 24, 8, resPass);
			return true;
		} else {
			System.out.println("Test Fail: Displayed all the vouchers in Bank reconcliation Report With Debit Amount");
			excelReader.setCellData(xlfile, xlSheetName, 24, 8, resFail);
			return false;
		}

	}

	public boolean checkBRSReportWithSortByColoumnCreditAmount()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		excelReader = new ExcelReader(POJOUtility.getExcelPath());
		Thread.sleep(2000);

		Select s1 = new Select(BRSSortByOrderDrpdwn);
		s1.selectByValue("7");

		Thread.sleep(4000);

		ClickUsingJs(LoadBtn);

		Thread.sleep(8999);

		int bankRecDocumentNoListCount = bankRecDocumentNoList.size();

		ArrayList<String> bankRecDocumentNoListArray = new ArrayList<>();

		for (int i = 0; i < bankRecDocumentNoListCount; i++) {

			String data = bankRecDocumentNoList.get(i).getText();
			bankRecDocumentNoListArray.add(data);
		}

		String actbankRecDocumentNoList = bankRecDocumentNoListArray.toString();
		String expbankRecDocumentNoList = excelReader.getCellData(xlSheetName, 33, 6);
		excelReader.setCellData(xlfile, xlSheetName, 33, 7, actbankRecDocumentNoList);

		System.out.println(" Act bankRecDocumentNoList : " + actbankRecDocumentNoList);
		System.out.println(" Exp bankRecDocumentNoList : " + expbankRecDocumentNoList);

		int bankRecDocumentTYPEListCount = bankRecDocumentTYPEList.size();
		ArrayList<String> bankRecDocumentTYPEListArray = new ArrayList<>();

		for (int i = 0; i < bankRecDocumentTYPEListCount; i++) {

			String data = bankRecDocumentTYPEList.get(i).getText();
			bankRecDocumentTYPEListArray.add(data);
		}

		String actbankRecDocumentTYPEList = bankRecDocumentTYPEListArray.toString();
		String expbankRecDocumentTYPEList = excelReader.getCellData(xlSheetName, 34, 6);
		excelReader.setCellData(xlfile, xlSheetName, 34, 7, actbankRecDocumentNoList);

		System.out.println(" Act bankRecDocumentTYPEList : " + actbankRecDocumentTYPEList);
		System.out.println(" Exp bankRecDocumentTYPEList : " + expbankRecDocumentTYPEList);

		if (actbankRecDocumentNoList.equalsIgnoreCase(expbankRecDocumentNoList)
				&& actbankRecDocumentTYPEList.equalsIgnoreCase(expbankRecDocumentTYPEList)) {
			System.out.println("Test Pass: Displayed all the vouchers in Bank reconcliation Report With Credit Amount");
			excelReader.setCellData(xlfile, xlSheetName, 32, 8, resPass);
			return true;
		} else {
			System.out.println("Test Fail: Displayed all the vouchers in Bank reconcliation Report With Credit Amount");
			excelReader.setCellData(xlfile, xlSheetName, 32, 8, resFail);
			return false;
		}
	}

	@FindBy(xpath = "//select[@id='SelectDebit']")
	private static WebElement BRSSelectCRDRDrpdwn;

	public boolean checkBRSReportWithSelectDropdownOnCredits()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		excelReader = new ExcelReader(POJOUtility.getExcelPath());
		Thread.sleep(2000);

		Select s1 = new Select(BRSSelectCRDRDrpdwn);
		s1.selectByValue("1");

		Thread.sleep(6500);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(LoadBtn));
		ClickUsingJs(LoadBtn);

		Thread.sleep(8999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(LoadBtn));
		ClickUsingJs(LoadBtn);

		Thread.sleep(8999);

		int bankRecDocumentNoListCount = bankRecDocumentNoList.size();

		ArrayList<String> bankRecDocumentNoListArray = new ArrayList<>();

		for (int i = 0; i < bankRecDocumentNoListCount; i++) {

			String data = bankRecDocumentNoList.get(i).getText();
			bankRecDocumentNoListArray.add(data);
		}

		String actbankRecDocumentNoList = bankRecDocumentNoListArray.toString();
		String expbankRecDocumentNoList = excelReader.getCellData(xlSheetName, 41, 6);
		excelReader.setCellData(xlfile, xlSheetName, 41, 7, actbankRecDocumentNoList);

		System.out.println(" Act bankRecDocumentNoList : " + actbankRecDocumentNoList);
		System.out.println(" Exp bankRecDocumentNoList : " + expbankRecDocumentNoList);

		int bankRecDocumentTYPEListCount = bankRecDocumentTYPEList.size();
		ArrayList<String> bankRecDocumentTYPEListArray = new ArrayList<>();

		for (int i = 0; i < bankRecDocumentTYPEListCount; i++) {

			String data = bankRecDocumentTYPEList.get(i).getText();
			bankRecDocumentTYPEListArray.add(data);
		}

		String actbankRecDocumentTYPEList = bankRecDocumentTYPEListArray.toString();
		String expbankRecDocumentTYPEList = excelReader.getCellData(xlSheetName, 42, 6);
		excelReader.setCellData(xlfile, xlSheetName, 42, 7, actbankRecDocumentTYPEList);

		System.out.println(" Act bankRecDocumentTYPEList : " + actbankRecDocumentTYPEList);
		System.out.println(" Exp bankRecDocumentTYPEList : " + expbankRecDocumentTYPEList);

		if (actbankRecDocumentNoList.equalsIgnoreCase(expbankRecDocumentNoList)
				&& actbankRecDocumentTYPEList.equalsIgnoreCase(expbankRecDocumentTYPEList)) {
			System.out.println("Test Pass: Displayed all the vouchers in Bank reconcliation Report With Credit Amount");
			excelReader.setCellData(xlfile, xlSheetName, 40, 8, resPass);
			return true;
		} else {
			System.out.println("Test Fail: Displayed all the vouchers in Bank reconcliation Report With Credit Amount");
			excelReader.setCellData(xlfile, xlSheetName, 40, 8, resFail);
			return false;
		}
	}

	@FindBy(xpath = "//*[@id='BRTable_body']/tr[1]/td")
	private static List<WebElement> BRSBodyGridRow1List;

	@FindBy(xpath = "//*[@id='BRTable_body']/tr[1]/td[2]")
	private static WebElement BRSBodyGridRow1Col1;

	@FindBy(xpath = "//*[@id='BRTable_body']/tr[1]/td[3]")
	private static WebElement BRSBodyGridRow1Col2;

	@FindBy(xpath = "//select[@id='selectSaveOption']")
	private static WebElement BRSReportSelectSaveOptionDrpdwn;

	public boolean checkClearenaceVoucherOptionInBRSReport()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		excelReader = new ExcelReader(POJOUtility.getExcelPath());
		Thread.sleep(2000);

		Select s1 = new Select(BRSReportSelectSaveOptionDrpdwn);
		s1.selectByValue("0");

		Thread.sleep(2000);

		int BRSBodyGridRow1ListCount = BRSBodyGridRow1List.size();

		ArrayList<String> BRSBodyGridRow1ListArray = new ArrayList<String>();

		for (int i = 0; i < BRSBodyGridRow1ListCount; i++) {

			String data = BRSBodyGridRow1List.get(i).getText();
		
			BRSBodyGridRow1ListArray.add(data);
		}

		String actListBeforeClick = BRSBodyGridRow1ListArray.toString();
		String expListBeforeClick = excelReader.getCellData(xlSheetName, 48, 6);
		excelReader.setCellData(xlfile, xlSheetName, 48, 7, actListBeforeClick);

		System.out.println("Actual List : " + actListBeforeClick);
		System.out.println("Exp    List : " + expListBeforeClick);

		Thread.sleep(1999);

		getAction().moveToElement(BRSBodyGridRow1Col1).build().perform();;

		Thread.sleep(1000);

		getAction().doubleClick(BRSBodyGridRow1Col1).click().build().perform();

		Thread.sleep(1999);

		ArrayList<String> BRSBodyGridRow1ListArray1 = new ArrayList<String>();

		for (int i = 0; i < BRSBodyGridRow1ListCount; i++) {

			String data = BRSBodyGridRow1List.get(i).getText();

			BRSBodyGridRow1ListArray1.add(data);
		}

		String actListAfterClick = BRSBodyGridRow1ListArray1.toString();
		String expListAfterClick = excelReader.getCellData(xlSheetName, 49, 6);
		excelReader.setCellData(xlfile, xlSheetName, 49, 7, actListAfterClick);

		System.out.println("Actual List After: " + actListAfterClick);
		System.out.println("Exp    List After: " + expListAfterClick);

		String ExpMessage = excelReader.getCellData(xlSheetName, 50, 6);

		String actMessage = checkValidationMessage(ExpMessage);

		excelReader.setCellData(xlfile, xlSheetName, 50, 7, actMessage);

		if (actListBeforeClick.equalsIgnoreCase(expListBeforeClick)
				&& actListAfterClick.equalsIgnoreCase(expListAfterClick) && actMessage.equalsIgnoreCase(ExpMessage)) {
			System.out.println(" Test Pass: Displayed Expected values ");
			excelReader.setCellData(xlfile, xlSheetName, 47, 8, resPass);
			return true;

		} else {
			System.out.println(" Test Fail: Displayed Expected values ");
			excelReader.setCellData(xlfile, xlSheetName, 47, 8, resFail);
			return true;
		}

	}

	@FindBy(xpath = "//select[@id='selectStatus']")
	private static WebElement BRSSelectStatusDrpdwn;

	public boolean checkBRSReportWithSelectStatusAsCleared()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		excelReader = new ExcelReader(POJOUtility.getExcelPath());
		Thread.sleep(2000);

		Select s1 = new Select(BRSSelectStatusDrpdwn);
		s1.selectByValue("1");

		Thread.sleep(3500);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(LoadBtn));
		ClickUsingJs(LoadBtn);

		Thread.sleep(4999);

		int bankRecDocumentNoListCount = bankRecDocumentlinkStatusList.size();

		ArrayList<String> bankRecDocumentNoListArray = new ArrayList<>();

		for (int i = 0; i < bankRecDocumentNoListCount; i++) {

			String data = bankRecDocumentlinkStatusList.get(i).getText();
			bankRecDocumentNoListArray.add(data);
		}

		String actbankRecDocumentNoList = bankRecDocumentNoListArray.toString();
		String expbankRecDocumentNoList = excelReader.getCellData(xlSheetName, 55, 6);
		excelReader.setCellData(xlfile, xlSheetName, 55, 7, actbankRecDocumentNoList);

		System.out.println(" Act bankRecDocumentNoList : " + actbankRecDocumentNoList);
		System.out.println(" Exp bankRecDocumentNoList : " + expbankRecDocumentNoList);

		if (actbankRecDocumentNoList.equalsIgnoreCase(expbankRecDocumentNoList)) {
			System.out.println("Test Pass: Displayed all the vouchers in Bank reconcliation Report With Cleared Status ");
			excelReader.setCellData(xlfile, xlSheetName, 54, 8, resPass);
			return true;
		} else {
			System.out.println("Test Fail: Displayed all the vouchers in Bank reconcliation With Cleared Status ");
			excelReader.setCellData(xlfile, xlSheetName, 54, 8, resFail);
			return false;
		}

	}

	public boolean checkSelctStatusWithPedingInBRSReport()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		excelReader = new ExcelReader(POJOUtility.getExcelPath());
		Thread.sleep(2000);

		// Seelcting Status as Pending
		Select s1 = new Select(BRSSelectStatusDrpdwn);
		s1.selectByValue("0");

		Thread.sleep(3500);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(LoadBtn));
		ClickUsingJs(LoadBtn);

		Thread.sleep(4999);

		int bankRecDocumentNoListCount = bankRecDocumentlinkStatusList.size();

		ArrayList<String> bankRecDocumentNoListArray = new ArrayList<>();

		for (int i = 0; i < bankRecDocumentNoListCount; i++) {

			String data = bankRecDocumentlinkStatusList.get(i).getText();
			bankRecDocumentNoListArray.add(data);
		}

		String actbankRecDocumentlinkStatusList = bankRecDocumentNoListArray.toString();
		String expbankRecDocumentlinkStatusList = excelReader.getCellData(xlSheetName, 62, 6);
		excelReader.setCellData(xlfile, xlSheetName, 62, 7, actbankRecDocumentlinkStatusList);

		System.out.println(" Act bankRecDocumentlinkStatusList : " + actbankRecDocumentlinkStatusList);
		System.out.println(" Exp bankRecDocumentlinkStatusList : " + expbankRecDocumentlinkStatusList);

		int bankRecDocumentNoListCount1 = bankRecDocumentNoList.size();

		ArrayList<String> bankRecDocumentNoListArray1 = new ArrayList<>();

		for (int i = 0; i < bankRecDocumentNoListCount1; i++) {

			String data = bankRecDocumentNoList.get(i).getText();
			bankRecDocumentNoListArray1.add(data);
		}

		String actbankRecDocumentNoList = bankRecDocumentNoListArray1.toString();
		String expbankRecDocumentNoList = excelReader.getCellData(xlSheetName, 63, 6);
		excelReader.setCellData(xlfile, xlSheetName, 63, 7, actbankRecDocumentNoList);

		System.out.println(" Act bankRecDocumentNoList : " + actbankRecDocumentNoList);
		System.out.println(" Exp bankRecDocumentNoList : " + expbankRecDocumentNoList);

		if (actbankRecDocumentNoList.equalsIgnoreCase(expbankRecDocumentNoList)
				&& actbankRecDocumentlinkStatusList.equalsIgnoreCase(expbankRecDocumentlinkStatusList)) {
			System.out
					.println("Test Pass: Displayed all the vouchers in Bank reconcliation Report With Pending Status ");
			excelReader.setCellData(xlfile, xlSheetName, 61, 8, resPass);
			return true;
		} else {
			System.out.println("Test Fail: Displayed all the vouchers in Bank reconcliation With Pending Status ");
			excelReader.setCellData(xlfile, xlSheetName, 61, 8, resFail);
			return false;
		}
	}

	@FindBy(xpath = "//*[@id='btnSaveDisableImmediate']")
	private static WebElement BRSSaveBtn;

	@FindBy(xpath = "(//*[@title='Save'])[2]")
	private static WebElement BRSSaveBtn2;

	public boolean checkCleareanceVoucherInBRSByUsingBatchMode()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		Thread.sleep(2000);
		excelReader = new ExcelReader(POJOUtility.getExcelPath());

		Select s1 = new Select(BRSReportSelectSaveOptionDrpdwn);
		s1.selectByValue("1");

		Thread.sleep(1999);

		getAction().moveToElement(BRSBodyGridRow1Col1).doubleClick(BRSBodyGridRow1Col1).click().build().perform();

		Thread.sleep(1999);

		reportbankTxt.click();

		ClickUsingJs(BRSSaveBtn2);

		String ExpMessage = excelReader.getCellData(xlSheetName, 69, 6);

		String actMessage = checkValidationMessage(ExpMessage);

		excelReader.setCellData(xlfile, xlSheetName, 69, 7, actMessage);

		Thread.sleep(4999);

		int bankRecDocumentNoListCount = bankRecDocumentlinkStatusList.size();

		ArrayList<String> bankRecDocumentNoListArray = new ArrayList<>();

		for (int i = 0; i < bankRecDocumentNoListCount; i++) {

			String data = bankRecDocumentlinkStatusList.get(i).getText();
			bankRecDocumentNoListArray.add(data);
		}

		String actbankRecDocumentNoList = bankRecDocumentNoListArray.toString();
		String expbankRecDocumentNoList = excelReader.getCellData(xlSheetName, 70, 6);

		excelReader.setCellData(xlfile, xlSheetName, 70, 7, actbankRecDocumentNoList);

		System.out.println(" Act bankRecDocumentNoList : " + actbankRecDocumentNoList);
		System.out.println(" Exp bankRecDocumentNoList : " + expbankRecDocumentNoList);

		if (actbankRecDocumentNoList.equalsIgnoreCase(expbankRecDocumentNoList)) {
			System.out
					.println("Test Pass: Displayed all the vouchers in Bank reconcliation Report With Cleared Status ");
			excelReader.setCellData(xlfile, xlSheetName, 68, 8, resPass);
			return true;
		} else {
			System.out.println("Test Fail: Displayed all the vouchers in Bank reconcliation With Cleared Status ");
			excelReader.setCellData(xlfile, xlSheetName, 68, 8, resFail);
			return false;
		}

	}

	@FindBy(xpath = "//*[@class='icon-backtrack hiconright2']")
	private static WebElement BRSBackTrackBtn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[1]/td[2]")
	private static WebElement select1stRow_1stColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[1]/td[3]")
	private static WebElement select1stRow_2ndColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[1]/td[4]")
	private static WebElement select1stRow_3rdColumn;

	// Voucher Entry Page Header Fields
	@FindBy(xpath = "//input[@id='id_header_1']")
	private static WebElement documentNumberTxt;

	@FindBy(xpath = "//*[@id='id_header_1_input_image']/span")
	private static WebElement documentNumberdropdown_ExpansionBtn;

	@FindBy(xpath = "//input[@id='id_header_2']")
	private static WebElement dateTxt;

	@FindBy(xpath = "//td[@id='id_header_2_input_image']//span[@class='icon-calender theme_color-inverse datecontrol_arrow_margin datecontrol_arrow']")
	private static WebElement dateTxt_CalenderBtn;

	@FindBy(xpath = "//tr[@id='id_header_2_day_today']//span[@class='theme_color-inverse'][contains(text(),'Today')]")
	private static WebElement calender_TodayBtn;

	@FindBy(xpath = "//input[@id='id_header_4']")
	private static WebElement caskBankAccountTxt;

	@FindBy(xpath = "//*[@id='id_header_268435459_table_body']/tr/td[2]")
	private static List<WebElement> openingBalDepartmentList;

	@FindBy(xpath = "//*[@id='id_transactionentry_save']")
	private static WebElement openingBalancesSaveBtn;

	@FindBy(xpath = "//input[@id='id_header_268435459']")
	private static WebElement departmentTxt;

	@FindBy(xpath = "//input[@id='id_header_4']")
	private static WebElement newCashBankAccountTxt;

	@FindBy(xpath = "//input[@id='id_header_10']")
	private static WebElement voucherHeaderCurrency;

	public boolean checkbacktrackoptionInBRSReport()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		Thread.sleep(2999);

		excelReader = new ExcelReader(POJOUtility.getExcelPath());

		int count = bankRecDocumentNuberList.size();

		for (int i = 0; i < count; i++) {
			String data = bankRecDocumentNuberList.get(i).getText();

			System.out.println(" DATA : " + data);

			Thread.sleep(2000);
			if (data.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 76, 5))) {
				Thread.sleep(2000);

				BRSBodyGirdRowNoList.get(i).click();

				getAction().doubleClick().click().build().perform();

				break;
			}
		}

		Thread.sleep(1999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(BRSBackTrackBtn));
		BRSBackTrackBtn.click();

		Thread.sleep(3999);

		ArrayList<String> openTabs = new ArrayList<String>(getDriver().getWindowHandles());

		int actOpenWindowsCount = getDriver().getWindowHandles().size();
		int expOpenWindowsCount = 2;

		Thread.sleep(1000);
		getDriver().switchTo().window(openTabs.get(1));

		Thread.sleep(3000);

		String actDocno = documentNumberTxt.getAttribute("value");
		String actVouDate = dateTxt.getAttribute("value");
		String actDepartment = departmentTxt.getAttribute("value");
		String actCurrency = voucherHeaderCurrency.getAttribute("value");
		String actCashAndBankAccount = newCashBankAccountTxt.getAttribute("value");

		DateFormat df = new SimpleDateFormat("dd MMM yyyy");
		Date date = new Date();
		String ExpDate = df.format(date);

		System.out.println("ExpDate   :" + ExpDate);

		String expDocno = /*
							 * excelReader.getCellData(xlSheetName, 77, 6); excelReader.setCellData(xlfile,
							 * xlSheetName, 77, 7, actDocno)
							 */"1";

		String expDepartment = excelReader.getCellData(xlSheetName, 78, 6);
		excelReader.setCellData(xlfile, xlSheetName, 78, 7, actDepartment);

		String expCurrency = excelReader.getCellData(xlSheetName, 79, 6);
		excelReader.setCellData(xlfile, xlSheetName, 79, 7, actCurrency);

		String expCashAndBankAccount = excelReader.getCellData(xlSheetName, 80, 6);
		excelReader.setCellData(xlfile, xlSheetName, 80, 7, actCashAndBankAccount);

		String expDate = excelReader.getCellData(xlSheetName, 81, 6);
		excelReader.setCellData(xlfile, xlSheetName, 81, 7, actVouDate);
		// AS BACK UP TAKEN ON THE DATE

		String actAccountR1 = select1stRow_1stColumn.getText();
		String actAmountR1 = select1stRow_2ndColumn.getText();
		String actrefR1 = select1stRow_3rdColumn.getText();

		String expAccountR1 = excelReader.getCellData(xlSheetName, 82, 6);
		excelReader.setCellData(xlfile, xlSheetName, 82, 7, actAccountR1);

		String expAmountR1 = excelReader.getCellData(xlSheetName, 83, 6);
		excelReader.setCellData(xlfile, xlSheetName, 83, 7, actAmountR1);

		String exprefR1 = excelReader.getCellData(xlSheetName, 84, 6);
		excelReader.setCellData(xlfile, xlSheetName, 84, 7, actrefR1);

		System.out.println("Entry Page Document Number    " + actDocno + "  value Expected  " + expDocno);
		System.out.println("Entry Page Voucher Date       " + actVouDate + "  value Expected  " + expDate);
		System.out.println("Entry Page Currency        " + actCurrency + "  value Expected  " + expCurrency);
		System.out.println("Entry Page Department         " + actDepartment + "  value Expected  " + expDepartment);
		System.out.println("Entry Page CashAndBankAccount " + actCashAndBankAccount + "  value Expected  "
				+ expCashAndBankAccount);

		System.out.println("Entry Page Account            " + actAccountR1 + "  value Expected  " + expAccountR1);
		System.out.println("Entry Page Amount             " + actAmountR1 + "  value Expected  " + expAmountR1);
		System.out.println("Entry Page Reference          " + actrefR1 + "  value Expected  " + exprefR1);

		Thread.sleep(2000);

		getDriver().switchTo().window(openTabs.get(1)).close();
		Thread.sleep(1000);

		getDriver().switchTo().window(openTabs.get(0));

		if (actDocno.equalsIgnoreCase(expDocno) && actVouDate.equalsIgnoreCase(expDate)
				&& actDepartment.equalsIgnoreCase(expDepartment) && actCurrency.equalsIgnoreCase(expCurrency)
				&& actCashAndBankAccount.equalsIgnoreCase(expCashAndBankAccount) &&

				actAccountR1.equalsIgnoreCase(expAccountR1) && actAmountR1.equalsIgnoreCase(expAmountR1)
				&& actrefR1.startsWith(exprefR1))

		{
			System.out.println(" Test Pass: Data Displayed As Exepcted  ");
			excelReader.setCellData(xlfile, xlSheetName, 75, 8, resPass);
			return true;
		} else {
			System.err.println(" Test Fail: Data Displayed As Exepcted ");
			excelReader.setCellData(xlfile, xlSheetName, 75, 8, resFail);
			return false;
		}
	}

	@FindBy(xpath = "//*[@id='id_transactionentry_close']/div[1]")
	private static WebElement entrypageClosebtn;

	@FindBy(xpath = "//button[@id='btnPendingBills']")
	private static WebElement BRSPendingBillsBtn;

	@FindBy(xpath = "//*[@id='btnRaisePayment']")
	private static WebElement BRSRaisingPayments;

	@FindBy(xpath = "//li[@id='liPendingBills3']")
	private static WebElement BRSPendingBillsReceiptsVATBtn;

	@FindBy(xpath = "//*[@id='liRaisePayment1']")
	private static WebElement BRS_RaisePay_PayVAT;

	@FindBy(xpath = "//input[@id='id_body_12']")
	private static WebElement enter_AccountTxt;

	@FindBy(xpath = "//input[@id='Clearancedate1']")
	private static WebElement BRSBodyGridEnter_ClearanceDate;

	@FindBy(xpath = "//*[@id='selectSaveOption']")
	private static WebElement BRSSaveOptionsDrpdwn;

	@FindBy(xpath = "//input[@id='id_body_18']")
	private static WebElement enter_DebitTxt;

	@FindBy(xpath = "//input[@id='id_body_19']")
	private static WebElement enter_CreditTxt;

	@FindBy(xpath = "//*[@id='id_body_12_table_body']/tr")
	private static List<WebElement> openingBalAccountListInGrid;

	@FindBy(xpath = "//input[@id='id_body_19']")
	private static WebElement enter_OBCreditACTxt;

	public boolean checkChangingClearanceDateWithSaveOptionsAsbatchMode()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		// Selecting Save Option as Save Batch mode
		Select s1 = new Select(BRSReportSelectSaveOptionDrpdwn);
		s1.selectByValue("1");

		Thread.sleep(2000);

		getAction().moveToElement(BRSBodyGridRow1Col2).build().perform();
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(BRSBodyGridRow1Col2));
		BRSBodyGridRow1Col2.click();
		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(BRSBodyGridEnter_ClearanceDate));
		BRSBodyGridEnter_ClearanceDate.click();
		BRSBodyGridEnter_ClearanceDate.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME);
		Thread.sleep(2000);

		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		String currentdate = df.format(date);
		System.out.println(" Curent date  : " + currentdate);

		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, 1);

		String FilterDate = df.format(c.getTime());

		System.out.println("FilterDate  : " + FilterDate);

		Thread.sleep(2000);
		BRSBodyGridEnter_ClearanceDate.sendKeys(FilterDate);

		Thread.sleep(2000);

		BRSBodyGridEnter_ClearanceDate.sendKeys(Keys.TAB);

		Thread.sleep(1999);

		Select drp = new Select(BRSSaveOptionsDrpdwn);

		drp.selectByValue("1");

		Thread.sleep(2000);

		String actSelected = drp.getFirstSelectedOption().getText();
		System.err.println("actSelected*************************************" + actSelected);

		Thread.sleep(2569);

		ClickUsingJs(BRSSaveBtn);

		String ExpMessage = "Clearance date cannot be future date.";
		String actMessage = checkValidationMessage(ExpMessage);

		if (actMessage.equalsIgnoreCase(ExpMessage)) {
			System.out.println("Test Pass: Clearance date cannot be future date.");
			return true;
		} else {
			System.out.println("Test Fail: Clearance date cannot be future date.");
			return false;
		}
	}

	public boolean checkSavingOpeningBalaceWithBank()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {

		Thread.sleep(2999);
		getDriver().navigate().refresh();

		Thread.sleep(2999);
		System.err.println(" Entered   ************************");

		excelReader = new ExcelReader(POJOUtility.getExcelPath());

		getWebDriverWait().until(ExpectedConditions.elementToBeClickable(financialsMenu));
		financialsMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(financialsTransactionMenu));
		financialsTransactionMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(financialsTransactionsJournalsMenu));
		financialsTransactionsJournalsMenu.click();

		ClickUsingJs(openingBalancesVoucher);

		Thread.sleep(2000);

		waitToClick(newBtn);

		checkValidationMessage("Screen opened");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNumberTxt));

		String docno = documentNumberTxt.getAttribute("value");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(voucherHeaderCurrency));
		voucherHeaderCurrency.click();

		voucherHeaderCurrency.sendKeys(Keys.SHIFT, Keys.HOME);

		voucherHeaderCurrency.sendKeys(Keys.SPACE);

		int currencycount = currencyListCount.size();

		System.err.println(currencycount);

		for (int i = 0; i < currencycount; i++) {
			String data = currencyListCount.get(i).getText();

			if (data.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 96, 5))) {
				currencyListCount.get(i).click();

				break;
			}
		}

		voucherHeaderCurrency.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(departmentTxt));
		departmentTxt.click();
		departmentTxt.sendKeys(Keys.SPACE);

		int OpeningBalDepartmentListCount = openingBalDepartmentList.size();

		for (int i = 0; i < OpeningBalDepartmentListCount; i++) {
			String data = openingBalDepartmentList.get(i).getText();

			if (data.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 97, 5))) {
				openingBalDepartmentList.get(i).click();

				break;
			}
		}

		departmentTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_1stColumn));
		select1stRow_1stColumn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_AccountTxt));
		enter_AccountTxt.click();
		enter_AccountTxt.sendKeys(excelReader.getCellData(xlSheetName, 98, 5));

		int accountCount = openingBalAccountListInGrid.size();

		System.err.println(accountCount);

		for (int i = 0; i < accountCount; i++) {
			String data = openingBalAccountListInGrid.get(i).getText();

			if (data.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 99, 5))) {
				openingBalAccountListInGrid.get(i).click();

				break;
			}
		}

		enter_AccountTxt.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_DebitTxt));
		enter_DebitTxt.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_CreditTxt));
		enter_CreditTxt.sendKeys(excelReader.getCellData(xlSheetName, 100, 5));
		enter_CreditTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(openingBalancesSaveBtn));
		openingBalancesSaveBtn.click();

		boolean savingVoucher = checkVoucherSavingMessage(docno);

		if (savingVoucher == true) {
			System.out.println(" Test Pass: Voucher Saved With all Credit Amounts  In Opening Bal ");
			excelReader.setCellData(xlfile, xlSheetName, 95, 8, resPass);
			return true;

		} else {
			System.out.println(" Test Fail: Voucher Saved With all In Opening Bal ");
			excelReader.setCellData(xlfile, xlSheetName, 95, 8, resFail);
			return false;
		}

	}

	public boolean checkChangingClearanceDateFromAccountingDateToCurrentdate()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		excelReader = new ExcelReader(POJOUtility.getExcelPath());
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(financialsMenu));
		financialsMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(financialsReportsMenu));
		financialsReportsMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(cashAndBankBooksMenu));
		cashAndBankBooksMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(bankReconciliationReport));
		bankReconciliationReport.click();

		boolean novalidationConfirmationMessage = validationConfirmationMessage.getText().isEmpty();

		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = excelReader.getCellData(xlSheetName, 106, 6);
		excelReader.setCellData(xlfile, xlSheetName, 106, 7, actvalidationConfirmationMessage);

		System.out.println("validationConfirmationMessage : " + actvalidationConfirmationMessage + " Value Expected : "
				+ expvalidationConfirmationMessage);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportbankTxt));
		reportbankTxt.click();
		reportbankTxt.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME);
		reportbankTxt.sendKeys(Keys.SPACE);

		int bankListCount = bankList.size();
		for (int i = 0; i < bankListCount; i++) {

			String data = bankList.get(i).getText();
			if (data.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 107, 5))) {
				bankList.get(i).click();
			}
		}
		Thread.sleep(2000);
		reportbankTxt.sendKeys(Keys.TAB);

		Thread.sleep(1500);

		Select s1 = new Select(BRSSelectStatusDrpdwn);
		s1.selectByValue("2");

		Thread.sleep(1500);

		Select s2 = new Select(BRSSelectCRDRDrpdwn);
		s2.selectByValue("2");

		Thread.sleep(1500);

		Select s3 = new Select(BRSDatePeriodDrpdwn);
		s3.selectByValue("1");

		Thread.sleep(2999);

		Select s4 = new Select(BRSReportSelectSaveOptionDrpdwn);
		s4.selectByValue("0");

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(LoadBtn));
		ClickUsingJs(LoadBtn);

		try {
			if (getIsAlertPresent()) {
				getAlert().accept();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		Thread.sleep(4999);

		int BRSBodyGridRow1ListCount = BRSBodyGridRow1List.size();

		ArrayList<String> BRSBodyGridRow1ListArray = new ArrayList<String>();

		for (int i = 0; i < BRSBodyGridRow1ListCount; i++) {

			String data = BRSBodyGridRow1List.get(i).getText();

			BRSBodyGridRow1ListArray.add(data);
		}

		String actListBeforeClick = BRSBodyGridRow1ListArray.toString();
		String expListBeforeClick = "";
		excelReader.setCellData(xlfile, xlSheetName, 108, 7, actListBeforeClick);

		System.out.println("Actual List : " + actListBeforeClick);
		System.out.println("Exp    List : " + expListBeforeClick);

		if (actListBeforeClick.equalsIgnoreCase(expListBeforeClick)) {
			System.err.println(" Test PasS: Opening Bal Displayed in BRS Report ");

			getAction().moveToElement(BRSBodyGridRow1Col2).build().perform();
			Thread.sleep(2000);

			BRSBodyGridRow1Col2.click();

			Thread.sleep(2000);

			
				if (dueDateCalenderIcon.isDisplayed()) {
					getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dueDateCalenderIcon));
					dueDateCalenderIcon.click();

					Thread.sleep(10000);
					getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(todaysDatePicker));
					todaysDatePicker.click();
				}

			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();

			String docdate = df.format(date);

			System.out.println(" Today date  : " + docdate);

			enterCleancedate.sendKeys(Keys.END);
			Thread.sleep(1000);

			enterCleancedate.sendKeys(Keys.SHIFT, Keys.HOME);
			Thread.sleep(1000);
			enterCleancedate.sendKeys(Keys.HOME);
			Thread.sleep(1000);
			enterCleancedate.sendKeys(docdate);

			Thread.sleep(1999);
			enterCleancedate.sendKeys(Keys.TAB);

			getAction().doubleClick(BRSBodyGridRow1Col1).click().build().perform();

			String ExpMessage = excelReader.getCellData(xlSheetName, 110, 6);
			String actMessage = checkValidationMessage(ExpMessage);
			excelReader.setCellData(xlfile, xlSheetName, 110, 7, actMessage);

			System.out.println(" Act Message : " + actMessage);
			System.out.println(" Exp Message : " + ExpMessage);

			Thread.sleep(4999);

			String currentdate = df.format(date);

			String actDate = BRSBodyGridRow1Col2.getText();
			String expdate = currentDate();

			System.out.println(" Act Date  : " + actDate);
			System.out.println(" Exp Date  : " + expdate);

			if (actDate.equalsIgnoreCase(expdate)) {
				System.err.println(" Test Pass: Clearance Date Displayed as Present Date in BRS  ");
				excelReader.setCellData(xlfile, xlSheetName, 105, 8, resPass);
				return true;
			} else {
				excelReader.setCellData(xlfile, xlSheetName, 105, 8, resFail);

				return false;
			}

		} else {
			System.err.println(" Test Fail: Opening Bal Displayed in BRS Report ");
			excelReader.setCellData(xlfile, xlSheetName, 105, 8, resFail);

			return false;
		}

	}

	@FindBy(xpath = "//*[@id='Clearancedate1']")
	private static WebElement enterCleancedate;

	@FindBy(xpath = "//*[@id='Clearancedate1_input_container']/div[1]/i[2]")
	private static WebElement dueDateCalenderIcon;

	@FindBy(xpath = "//tbody/tr[@id='Clearancedate1_day_today']/td[1]/span[1]")
	private static WebElement todaysDatePicker;

	@FindBy(xpath = "//select[@id='DatePeriod']")
	private static WebElement BRSDatePeriodDrpdwn;

	@FindBy(xpath = "//span[contains(text(),'Receipts VAT')]")
	private static WebElement recepitsVATVoucher;

	// Financial Menu and Sub Menus

	@FindBy(xpath = "//*[@id='id_header_67108947']")
	private static WebElement recepitsVATNarrationText;

	@FindBy(xpath = "//*[@id='id_body_12_table_body']/tr")
	private static List<WebElement> bodyAccountListInGrid;

	@FindBy(xpath = "//*[@id='id_body_39_table_body']/tr")
	private static List<WebElement> bodyCreditAccountListInGrid;

	public boolean checkSavingRecepitsVATVoucherFromBRS()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		excelReader = new ExcelReader(POJOUtility.getExcelPath());

		Thread.sleep(1999);

		ClickUsingJs(BRSPendingBillsBtn);

		Thread.sleep(1999);

		ClickUsingJs(BRSPendingBillsReceiptsVATBtn);

		Thread.sleep(4000);

		ArrayList<String> openTabs = new ArrayList<String>(getDriver().getWindowHandles());

		int actOpenWindowsCount = getDriver().getWindowHandles().size();
		int expOpenWindowsCount = 2;

		System.out.println("Number of Windows  : " + actOpenWindowsCount + "  Value Expected  " + expOpenWindowsCount);

		Thread.sleep(1000);

		getDriver().switchTo().window(openTabs.get(1));

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(newCashBankAccountTxt));

		if (newCashBankAccountTxt.getAttribute("value")
				.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 123, 5)) == false) {
			removetTxt(newCashBankAccountTxt);
			newCashBankAccountTxt.sendKeys(excelReader.getCellData(xlSheetName, 123, 5));
			Thread.sleep(2000);
			newCashBankAccountTxt.sendKeys(Keys.TAB);
		}

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(departmentTxt));
		departmentTxt.click();
		departmentTxt.sendKeys(excelReader.getCellData(xlSheetName, 124, 5));
		Thread.sleep(2000);
		departmentTxt.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(recepitsVATNarrationText));
		recepitsVATNarrationText.click();
		recepitsVATNarrationText.sendKeys(excelReader.getCellData(xlSheetName, 125, 5));
		Thread.sleep(2000);
		recepitsVATNarrationText.sendKeys(Keys.TAB);

		Thread.sleep(2999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_1stColumn));
		select1stRow_1stColumn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_AccountTxt));
		enter_AccountTxt.click();
		enter_AccountTxt.sendKeys(excelReader.getCellData(xlSheetName, 126, 5));

		getFluentWebDriverWait().until(ExpectedConditions.visibilityOfAllElements(bodyAccountListInGrid));
		int accountCount = bodyAccountListInGrid.size();

		System.err.println(accountCount);

		for (int i = 0; i < accountCount; i++) {
			String data = bodyAccountListInGrid.get(i).getText();

			if (data.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 127, 5))) {
				getFluentWebDriverWait().until(ExpectedConditions.visibilityOfAllElements(bodyAccountListInGrid));
				bodyAccountListInGrid.get(i).click();

				break;
			}
		}

		enter_AccountTxt.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(recVATTaxCode));
		recVATTaxCode.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME);
		recVATTaxCode.sendKeys(excelReader.getCellData(xlSheetName, 128, 5));
		Thread.sleep(1999);
		recVATTaxCode.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_Amount));
		enter_Amount.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME);
		enter_Amount.sendKeys(excelReader.getCellData(xlSheetName, 129, 5));
		Thread.sleep(1999);
		enter_Amount.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		String docno = documentNumberTxt.getAttribute("value");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(billRefNewReferenceTxt));
		billRefNewReferenceTxt.click();

		Thread.sleep(1000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(billRefPickIcon));
		billRefPickIcon.click();

		Thread.sleep(1000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(billRefOkBtn));
		billRefOkBtn.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(saveBtn));
		saveBtn.click();

		boolean savingVoucher = checkVoucherSavingMessage(docno);

		Thread.sleep(2000);
		getDriver().switchTo().window(openTabs.get(1)).close();
		Thread.sleep(2000);

		getDriver().switchTo().window(openTabs.get(0));

		if (savingVoucher == true) {
			System.out.println(" Test Pass: Voucher Saved With Narration ");
			excelReader.setCellData(xlfile, xlSheetName, 122, 8, resPass);
			return true;
		} else {
			System.out.println(" Test Fail: Voucher Saved With Narration ");
			excelReader.setCellData(xlfile, xlSheetName, 122, 8, resFail);
			return false;
		}
	}

	@FindBy(xpath = "//input[@id='id_body_16']")
	private static WebElement enter_Amount;

	@FindBy(xpath = "//input[@id='id_body_16777330']")
	private static WebElement recVATTaxCode;

	@FindBy(xpath = "//input[@id='id_body_16777332']")
	private static WebElement payVATTaxCode;

	public boolean checkBRSReportWithAdvanceFilterWithAccount()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		excelReader = new ExcelReader(POJOUtility.getExcelPath());
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(financialsMenu));
		financialsMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(financialsReportsMenu));
		financialsReportsMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(cashAndBankBooksMenu));
		cashAndBankBooksMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(bankReconciliationReport));
		bankReconciliationReport.click();

		Thread.sleep(3500);

		click(reportbankTxt);
		reportbankTxt.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME);
		reportbankTxt.sendKeys(Keys.SPACE);

		int bankListCount = bankList.size();
		for (int i = 0; i < bankListCount; i++) {

			String data = bankList.get(i).getText();
			if (data.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 136, 5))) {
				bankList.get(i).click();
			}
		}

		Thread.sleep(2000);

		reportbankTxt.sendKeys(Keys.TAB);

		Thread.sleep(1500);

		Thread.sleep(1500);

		Select s1 = new Select(BRSSelectStatusDrpdwn);
		s1.selectByValue("2");

		Thread.sleep(1500);

		Select s2 = new Select(BRSSelectCRDRDrpdwn);
		s2.selectByValue("2");

		Thread.sleep(1500);

		Select s3 = new Select(BRSDatePeriodDrpdwn);
		s3.selectByValue("1");

		Thread.sleep(2999);

		Select s4 = new Select(BRSReportSelectSaveOptionDrpdwn);
		s4.selectByValue("0");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(LoadBtn));
		ClickUsingJs(LoadBtn);

		Thread.sleep(5999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(BRSAdvaceFiletrBtn));
		BRSAdvaceFiletrBtn.click();

		Select s5 = new Select(BRSAdvaceFiletrConjuctionDrpdwn);
		s5.selectByValue("0");

		Thread.sleep(1999);
		BRSAdvaceFiletrSelectTxt.click();

		Thread.sleep(1999);
		BRSAdvaceFiletrSelectTxt.sendKeys(Keys.SPACE);

		Thread.sleep(1999);
		BRSAdvaceFiletrSelectAccBtn.click();

		Thread.sleep(1999);
		BRSAdvaceFiletrOperatorDrpdwn.click();

		Thread.sleep(1999);
		Select s6 = new Select(BRSAdvaceFiletrOperatorDrpdwn);
		s6.selectByValue("0");

		Thread.sleep(1999);
		Select s7 = new Select(BRSAdvaceFiletrCompareDrpdwn);
		s7.selectByValue("0");

		Thread.sleep(1999);

		BRSAdvaceFiletrEnterValueTXt.click();
		BRSAdvaceFiletrEnterValueTXt.sendKeys(excelReader.getCellData(xlSheetName, 137, 5));

		Thread.sleep(2999);

		BRSAdvaceFiletrEnterValueTXt.sendKeys(Keys.TAB);

		if (getIsAlertPresent()) {
			String alert = getAlert().getText();
			System.out.println("Alert Displayed: " + alert);
			getAlert().accept();
		}

		BRSAdvaceFiletrOKBtn.click();

		Thread.sleep(3999);

		int BRSBodyGridRow1ListCount = BRSBodyGridRow1List.size();

		ArrayList<String> BRSBodyGridRow1ListArray = new ArrayList<String>();

		for (int i = 0; i < BRSBodyGridRow1ListCount; i++) {

			String data = BRSBodyGridRow1List.get(i).getText();

			

			BRSBodyGridRow1ListArray.add(data);
		}

		String actListBeforeClick = BRSBodyGridRow1ListArray.toString();
		String expListBeforeClick = "";

		excelReader.setCellData(xlfile, xlSheetName, 138, 7, actListBeforeClick);

		System.out.println("Actual List : " + actListBeforeClick);
		System.out.println("Exp    List : " + expListBeforeClick);

		if (actListBeforeClick.startsWith(expListBeforeClick)) {
			System.out.println("Test Pass: Advance Filter ");
			excelReader.setCellData(xlfile, xlSheetName, 135, 8, resPass);
			return true;

		} else {
			System.out.println("Test Fail: Advance Filter ");
			excelReader.setCellData(xlfile, xlSheetName, 135, 8, resFail);
			return false;
		}
	}

	@FindBy(xpath = "//*[@id='btnAdvFilter']")
	private static WebElement BRSAdvaceFiletrBtn;

	@FindBy(xpath = "//*[@id='83_0_AdvanceFilter_']/table/tbody/tr/td[1]/select")
	private static WebElement BRSAdvaceFiletrConjuctionDrpdwn;

	@FindBy(xpath = "//*[@id='83_0_AdvanceFilter_']/table/tbody/tr/td[2]/input")
	private static WebElement BRSAdvaceFiletrSelectTxt;

	@FindBy(xpath = "(//*[@id='9'][contains(text(),'Account')])[1]")
	private static WebElement BRSAdvaceFiletrSelectAccBtn;

	@FindBy(xpath = "//*[@id='83_0_AdvanceFilter_']/table/tbody/tr/td[3]/select")
	private static WebElement BRSAdvaceFiletrOperatorDrpdwn;

	@FindBy(xpath = "//*[@id='83_0_AdvanceFilter_']/table/tbody/tr/td[4]/select")
	private static WebElement BRSAdvaceFiletrCompareDrpdwn;

	@FindBy(xpath = "//*[@id='83_0_AdvanceFilter_']/table/tbody/tr/td[5]/input")
	private static WebElement BRSAdvaceFiletrValueTXt;

	@FindBy(xpath = "//*[@id='advancefilter_master_83_0_']")
	private static WebElement BRSAdvaceFiletrEnterValueTXt;

	@FindBy(xpath = "//*[@id='btnOk']")
	private static WebElement BRSAdvaceFiletrOKBtn;

	// BRS Import

	@FindBy(xpath = "//*[@id='84']/span")
	private static WebElement bankReconciliationImport;

	@FindBy(xpath = "//*[@id='BRSFocusDataTable_body']/tr[1]")
	private static List<WebElement> BRSFocusDataRow1;

	@FindBy(xpath = "//*[@id='BRSFocusDataTable_body']/tr[2]")
	private static List<WebElement> BRSFocusDataRow2;

	@FindBy(xpath = "//*[@id='BRSFocusDataTable_body']/tr[3]")
	private static List<WebElement> BRSFocusDataRow3;

	@FindBy(xpath = "//*[@id='BRSFocusDataTable_body']/tr[4]")
	private static List<WebElement> BRSFocusDataRow4;

	@FindBy(xpath = "//*[@id='BRSFocusDataTable_body']/tr[5]")
	private static List<WebElement> BRSFocusDataRow5;

	@FindBy(xpath = "//*[@id='BRSFocusDataTable_body']/tr[6]")
	private static List<WebElement> BRSFocusDataRow6;

	@FindBy(xpath = "//*[@id='BRSBankDataTable_body']/tr[15]")
	private static List<WebElement> BRSBankDataRow1;

	@FindBy(xpath = "//*[@id='BRSBankDataTable_body']/tr[16]")
	private static List<WebElement> BRSBankDataRow2;

	@FindBy(xpath = "//*[@id='BRSBankDataTable_body']/tr[17]")
	private static List<WebElement> BRSBankDataRow3;

	@FindBy(xpath = "//*[@id='BRSBankDataTable_body']/tr[18]")
	private static List<WebElement> BRSBankDataRow4;

	@FindBy(xpath = "//*[@id='BRSBankDataTable_body']/tr[19]")
	private static List<WebElement> BRSBankDataRow5;

	@FindBy(xpath = "//*[@id='BRSBankDataTable_body']/tr[20]")
	private static List<WebElement> BRSBankDataRow6;

	public boolean checkBankReconciliationImportReport()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {

		Thread.sleep(2999);

		getDriver().navigate().refresh();

		Thread.sleep(2000);
		excelReader = new ExcelReader(POJOUtility.getExcelPath());
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(financialsMenu));
		financialsMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(financialsReportsMenu));
		financialsReportsMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(cashAndBankBooksMenu));
		cashAndBankBooksMenu.click();

		getAction().moveToElement(bankReconciliationImport).build().perform();
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(bankReconciliationReport));
		bankReconciliationImport.click();

		boolean novalidationConfirmationMessage = validationConfirmationMessage.getText().isEmpty();

		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = excelReader.getCellData(xlSheetName, 145, 5);

		excelReader.setCellData(xlfile, xlSheetName, 145, 7, actvalidationConfirmationMessage);

		System.out.println("validationConfirmationMessage : " + actvalidationConfirmationMessage + " Value Expected : "
				+ expvalidationConfirmationMessage);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(brsImport_ImportBtn));
		brsImport_ImportBtn.click();

		Thread.sleep(9500);

		Robot rb = new Robot();

		StringSelection str = new StringSelection(getBaseDir() + "\\autoIt\\ImportFiles\\BRS import.xlsx");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);

		rb.keyPress(KeyEvent.VK_CONTROL);
		rb.keyPress(KeyEvent.VK_V);

		rb.keyRelease(KeyEvent.VK_CONTROL);
		rb.keyRelease(KeyEvent.VK_V);

		rb.keyPress(KeyEvent.VK_ENTER);
		rb.keyRelease(KeyEvent.VK_ENTER);

		Thread.sleep(5000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(SmokeMasters));
		SmokeMasters.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sheetOkBtn));
		sheetOkBtn.click();
		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(brsImportBankDrpdwn));
		brsImportBankDrpdwn.click();
		brsImportBankDrpdwn.sendKeys(excelReader.getCellData(xlSheetName, 146, 5));
		Thread.sleep(2000);

		brsImportBankDrpdwn.sendKeys(Keys.TAB);

		Thread.sleep(1999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(fieldMapRow1));
		fieldMapRow1.click();
		Thread.sleep(1999);
		Select s1 = new Select(FieldDpdwn);
		s1.selectByVisibleText("chequeno.");

		Thread.sleep(1999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(fieldMapRow2));
		fieldMapRow2.click();

		Select s2 = new Select(FieldDpdwn1);
		s2.selectByVisibleText("clearancedate");

		Thread.sleep(1999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(fieldMapRow3));
		fieldMapRow3.click();

		Select s3 = new Select(FieldDpdwn2);
		s3.selectByVisibleText("creditamount");

		Thread.sleep(1999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(fieldMapRow4));
		fieldMapRow4.click();

		Select s4 = new Select(FieldDpdwn3);
		s4.selectByVisibleText("d.no");

		Thread.sleep(1999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(fieldMapRow5));
		fieldMapRow5.click();

		Select s5 = new Select(FieldDpdwn4);
		s5.selectByVisibleText("type");

		Thread.sleep(1999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(BRSImportCustBtn));
		BRSImportCustBtn.click();

		Thread.sleep(5999);

		int count = BRSCustCollist.size();
		ArrayList<String> BRSCustCollistArray = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			String data = BRSCustCollist.get(i).getText();
			BRSCustCollistArray.add(data);

			if (data.equalsIgnoreCase("Base Currency")) {
				BRSCustCollist.get(i).click();
			}
		}

		String actBRSCustCollist = BRSCustCollistArray.toString();
		String expBRSCustCollist = "[BRS Status, ChequeNo, Clearance Date, Document No, Document Date, Debit Amount, Credit Amount, Document Type, , Base Currency, Fx Currency, Trans Debit Amount, Trans Credit Amount, Local Currency, Local Debit Amount, Local Credit Amount, Department, Cumulative Amount, Account, Narration, Remarks, Tax Code, VAT, PDCNO, RD, AQ, FQ, Avg Rate, Avg Rate(O), Discount]";

		System.out.println("ACT: *****" + actBRSCustCollist);
		System.out.println("Exp: *****" + expBRSCustCollist);

		Thread.sleep(1999);

		click(enterWidthOfSelectedColTxt);
		enterWidthOfSelectedColTxt.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME);

		enterWidthOfSelectedColTxt.sendKeys("100");

		Thread.sleep(1000);

		enterWidthOfSelectedColTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(BRSCustOkBtn));
		BRSCustOkBtn.click();

		Thread.sleep(5999);

		if (actBRSCustCollist.contains(expBRSCustCollist)) {
			return true;
		} else {

			return false;
		}
	}

	@FindBy(xpath = "(//*[@id='WidthOfS'])[1]")
	private static WebElement enterWidthOfSelectedColTxt;

	public boolean checkBRSImportAfterEnableOptionInCustomization() throws InterruptedException {

		Thread.sleep(1999);

		// Focus data Table Header List

		String actBRSFocusDataColList = listOfElements(BRSFocusDataColList);
		String expBRSFocusDataColList = "[BRS Status, Clearance Date, Document No, Document Date]";

		System.out.println("ACT: *****" + actBRSFocusDataColList);
		System.out.println("Exp: *****" + expBRSFocusDataColList);

		Thread.sleep(5000);

		int focus1 = BRSFocusDataRow1.size();
		ArrayList<String> row1 = new ArrayList<>();
		for (int i = 0; i < focus1; i++) {
			String data = BRSFocusDataRow1.get(i).getText();
			row1.add(data);
		}
		String actBRSFocusDataRow1 = row1.toString();
		String expBRSFocusDataRow1 = "[1 Pending 08/04/2021 DebNts:1 08/04/2021 4.00 0 Debit Notes]";
		System.out.println("BRSFocusDataRow1 ACt : " + actBRSFocusDataRow1);
		System.out.println("BRSFocusDataRow1 EXP : " + expBRSFocusDataRow1);

		int focus2 = BRSFocusDataRow2.size();
		ArrayList<String> row2 = new ArrayList<>();
		for (int i = 0; i < focus2; i++) {
			String data = BRSFocusDataRow2.get(i).getText();
			row2.add(data);
		}
		String actBRSFocusDataRow2 = row2.toString();
		String expBRSFocusDataRow2 = "[2 Pending 08/04/2021 NDT62:1 08/04/2021 0.00 2 Debit Notes VAT]";
		System.out.println("BRSFocusDataRow2 ACt : " + actBRSFocusDataRow2);
		System.out.println("BRSFocusDataRow2 EXP : " + expBRSFocusDataRow2);

		int focus3 = BRSFocusDataRow3.size();
		ArrayList<String> row3 = new ArrayList<>();
		for (int i = 0; i < focus3; i++) {
			String data = BRSFocusDataRow3.get(i).getText();
			row3.add(data);
		}
		String actBRSFocusDataRow3 = row3.toString();
		String expBRSFocusDataRow3 = "[3 Pending 08/04/2021 Rct:1 08/04/2021 6.00 0 Receipts]";
		System.out.println("BRSFocusDataRow3 ACt : " + actBRSFocusDataRow3);
		System.out.println("BRSFocusDataRow3 EXP : " + expBRSFocusDataRow3);

		int focus4 = BRSFocusDataRow4.size();
		ArrayList<String> row4 = new ArrayList<>();
		for (int i = 0; i < focus4; i++) {
			String data = BRSFocusDataRow4.get(i).getText();
			row4.add(data);
		}
		String actBRSFocusDataRow4 = row4.toString();
		String expBRSFocusDataRow4 = "[4 Pending 08/04/2021 Rct:2 08/04/2021 6.00 0 Receipts]";
		System.out.println("BRSFocusDataRow4 ACt : " + actBRSFocusDataRow4);
		System.out.println("BRSFocusDataRow4 EXP : " + expBRSFocusDataRow4);

		int bank1 = BRSBankDataRow1.size();
		ArrayList<String> BR1 = new ArrayList<>();
		for (int i = 0; i < bank1; i++) {
			String data = BRSBankDataRow1.get(i).getText();
			BR1.add(data);
		}
		String actBRSBankDataRow1 = BR1.toString();
		String expBRSBankDataRow1 = "[15]";
		System.out.println("BRSFocusDataRow1 ACt : " + actBRSBankDataRow1);
		System.out.println("BRSFocusDataRow1 EXP : " + expBRSBankDataRow1);

		int bank2 = BRSBankDataRow2.size();
		ArrayList<String> BR2 = new ArrayList<>();
		for (int i = 0; i < bank2; i++) {
			String data = BRSBankDataRow2.get(i).getText();
			BR2.add(data);
		}
		String actBRSBankDataRow2 = BR2.toString();
		String expBRSBankDataRow2 = "[16]";
		System.out.println("BRSFocusDataRow2 ACt : " + actBRSBankDataRow2);
		System.out.println("BRSFocusDataRow2 EXP : " + expBRSBankDataRow2);

		int bank3 = BRSBankDataRow3.size();
		ArrayList<String> BR3 = new ArrayList<>();
		for (int i = 0; i < bank3; i++) {
			String data = BRSBankDataRow3.get(i).getText();
			BR3.add(data);
		}
		String actBRSBankDataRow3 = BR3.toString();
		String expBRSBankDataRow3 = "[17]";
		System.out.println("BRSFocusDataRow3 ACt : " + actBRSBankDataRow3);
		System.out.println("BRSFocusDataRow3 EXP : " + expBRSBankDataRow3);

		int bank4 = BRSBankDataRow4.size();
		ArrayList<String> BR4 = new ArrayList<>();
		for (int i = 0; i < bank4; i++) {
			String data = BRSBankDataRow4.get(i).getText();
			BR4.add(data);
		}
		String actBRSBankDataRow4 = BR4.toString();
		String expBRSBankDataRow4 = "[18 04/01/2020 1 10.00 PDRpts]";
		System.out.println("BRSFocusDataRow4 ACt : " + actBRSBankDataRow4);
		System.out.println("BRSFocusDataRow4 EXP : " + expBRSBankDataRow4);

		int bank5 = BRSBankDataRow5.size();
		ArrayList<String> BR5 = new ArrayList<>();
		for (int i = 0; i < bank5; i++) {
			String data = BRSBankDataRow5.get(i).getText();
			BR5.add(data);
		}
		String actBRSBankDataRow5 = BR5.toString();
		String expBRSBankDataRow5 = "[19 05/01/2020 2 20.00 PDRpts]";
		System.out.println("BRSFocusDataRow5 ACt : " + actBRSBankDataRow5);
		System.out.println("BRSFocusDataRow5 EXP : " + expBRSBankDataRow5);

		int bank6 = BRSBankDataRow6.size();
		ArrayList<String> BR6 = new ArrayList<>();
		for (int i = 0; i < bank6; i++) {
			String data = BRSBankDataRow6.get(i).getText();
			BR6.add(data);
		}
		String actBRSBankDataRow6 = BR6.toString();
		String expBRSBankDataRow6 = "[20 06/01/2020 3 30.00 Pmt]";
		System.out.println("BRSFocusDataRow6 ACt : " + actBRSBankDataRow6);
		System.out.println("BRSFocusDataRow6 EXP : " + expBRSBankDataRow6);

		String actBRSTotalDebitAmount = BRSTotalDebitAmount.getText();
		String expBRSTotalDebitAmount = "167.00";

		System.out.println(" BRSTotalDebitAmount : " + actBRSTotalDebitAmount + " Value  : " + expBRSTotalDebitAmount);

		String actBRSTotalCreditAmount = BRSTotalCreditAmount.getText();
		String expBRSTotalCreditAmount = "183.50";

		System.out
				.println(" BRSTotalCreditAmount : " + actBRSTotalCreditAmount + " Value  : " + expBRSTotalCreditAmount);

		String actBRSTotalAmount = BRSTotalAmount.getText();
		String expBRSTotalAmount = "60.00";

		System.out.println(" BRSTotalAmount : " + actBRSTotalAmount + " Value  : " + expBRSTotalAmount);

		if (actBRSFocusDataRow1.equalsIgnoreCase(expBRSFocusDataRow1)
				&& actBRSFocusDataRow2.equalsIgnoreCase(expBRSFocusDataRow2)
				&& actBRSFocusDataRow3.equalsIgnoreCase(expBRSFocusDataRow3)
				&& actBRSFocusDataRow4.equalsIgnoreCase(expBRSFocusDataRow4) &&

				actBRSBankDataRow1.equalsIgnoreCase(expBRSBankDataRow1)
				&& actBRSBankDataRow2.equalsIgnoreCase(expBRSBankDataRow2)
				&& actBRSBankDataRow3.equalsIgnoreCase(expBRSBankDataRow3) &&

				actBRSTotalDebitAmount.equalsIgnoreCase(expBRSTotalDebitAmount)
				&& actBRSTotalCreditAmount.equalsIgnoreCase(expBRSTotalCreditAmount)
				&& actBRSTotalAmount.equalsIgnoreCase(expBRSTotalAmount)) {

			System.err.println(" DATA DISPLAYED AS EXPECETED");
			return true;

		} else if (actBRSTotalDebitAmount.equalsIgnoreCase(expBRSTotalDebitAmount)
				&& actBRSTotalCreditAmount.equalsIgnoreCase(expBRSTotalCreditAmount)
				&& actBRSTotalAmount.equalsIgnoreCase(expBRSTotalAmount)) {
			System.err.println(" Bottom Fields are matched :-------------------");
			return true;
		} else {
			{
				return false;
			}
		}
	}

	@FindBy(xpath = "//*[@id='btnClear']")
	private static WebElement BRSClearBtn;

	@FindBy(xpath = "//*[@id='lblTotalDebitAmt']")
	private static WebElement BRSTotalDebitAmount;

	@FindBy(xpath = "//*[@id='lblTotalCreditAmt']")
	private static WebElement BRSTotalCreditAmount;

	@FindBy(xpath = "//*[@id='lblTotalAmt']")
	private static WebElement BRSTotalAmount;

	@FindBy(xpath = "//*[@class='icon-custamize hiconright2']")
	private static WebElement BRSImportCustBtn;

	@FindBy(xpath = "(//input[@value='Ok'])[2]")
	private static WebElement BRSCustOkBtn;

	@FindBy(xpath = "//*[@id='popUpdata']/div/label")
	private static List<WebElement> BRSCustCollist;

	@FindBy(xpath = "//*[@id='BRSFocusDataTable_row_heading']/th/div")
	private static List<WebElement> BRSFocusDataColList;

	@FindBy(xpath = "//*[@id='btnLoad']")
	private static WebElement brsLoadBtn;

	@FindBy(xpath = "//*[@id='SheetNameId']/li")
	private static WebElement SmokeMasters;

	@FindBy(xpath = "(//*[@id='btnOkSheetName'])[1]")
	private static WebElement sheetOkBtn;

	@FindBy(xpath = "//button[@id='btnSheetClose']")
	private static WebElement sheetCloseBtn;

	@FindBy(xpath = "//input[@id='Bank']")
	private static WebElement brsImportBankDrpdwn;

	@FindBy(xpath = "//*[@id='formLoadDataExcel']/div/span/span")
	private static WebElement brsImport_ImportBtn;

	@FindBy(xpath = "//*[@id='tblBodyImportFromExcel']/tr[1]/td[2]")
	private static WebElement fieldMapRow1;

	@FindBy(xpath = "(//*[@id='ddlImportFromExcelItemList0'])[1]")
	private static WebElement FieldDpdwn;

	@FindBy(xpath = "(//*[@id='ddlImportFromExcelItemList1'])[1]")
	private static WebElement FieldDpdwn1;

	@FindBy(xpath = "(//*[@id='ddlImportFromExcelItemList2'])[1]")
	private static WebElement FieldDpdwn2;

	@FindBy(xpath = "(//*[@id='ddlImportFromExcelItemList3'])[1]")
	private static WebElement FieldDpdwn3;

	@FindBy(xpath = "(//*[@id='ddlImportFromExcelItemList4'])[1]")
	private static WebElement FieldDpdwn4;

	@FindBy(xpath = "//*[@id='tblBodyImportFromExcel']/tr[2]/td[2]")
	private static WebElement fieldMapRow2;

	@FindBy(xpath = "//*[@id='tblBodyImportFromExcel']/tr[3]/td[2]")
	private static WebElement fieldMapRow3;

	@FindBy(xpath = "//*[@id='tblBodyImportFromExcel']/tr[4]/td[2]")
	private static WebElement fieldMapRow4;

	@FindBy(xpath = "//*[@id='tblBodyImportFromExcel']/tr[5]/td[2]")
	private static WebElement fieldMapRow5;

	public boolean checkSavingRaisingPaymentsFIFOVoucherFromBRSScreen()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {

		excelReader = new ExcelReader(POJOUtility.getExcelPath());

		click(financialsMenu);

		click(financialsReportsMenu);

		click(cashAndBankBooksMenu);

		click(bankReconciliationReport);

		Thread.sleep(5500);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportbankTxt));
		reportbankTxt.click();
		reportbankTxt.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME);
		reportbankTxt.sendKeys(Keys.SPACE);

		int bankListCount = bankList.size();
		for (int i = 0; i < bankListCount; i++) {

			String data = bankList.get(i).getText();
			if (data.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 15, 5))) {
				bankList.get(i).click();
			}
		}
		Thread.sleep(2000);

		reportbankTxt.sendKeys(Keys.TAB);

		Thread.sleep(1500);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(LoadBtn));
		ClickUsingJs(LoadBtn);

		Thread.sleep(3999);

		ClickUsingJs(BRSRaisingPayments);

		Thread.sleep(1999);

		ClickUsingJs(BRS_RaisePay_PayVAT);

		Thread.sleep(4000);

		Thread.sleep(2000);

		ArrayList<String> openTabs = new ArrayList<String>(getDriver().getWindowHandles());

		int actOpenWindowsCount = getDriver().getWindowHandles().size();
		int expOpenWindowsCount = 2;

		System.out.println("Number of Windows  : " + actOpenWindowsCount + "  Value Expected  " + expOpenWindowsCount);

		Thread.sleep(1000);

		getDriver().switchTo().window(openTabs.get(1));

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(newCashBankAccountTxt));

		if (newCashBankAccountTxt.getAttribute("value")
				.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 123, 5)) == false) {
			removetTxt(newCashBankAccountTxt);
			newCashBankAccountTxt.sendKeys(excelReader.getCellData(xlSheetName, 123, 5));
			Thread.sleep(2000);
			newCashBankAccountTxt.sendKeys(Keys.TAB);
		}

		// As Due Date And Currency Values Must be Pre-loaded as In Raise From BRS

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(departmentTxt));
		departmentTxt.click();
		departmentTxt.sendKeys(excelReader.getCellData(xlSheetName, 124, 5));
		Thread.sleep(2000);
		departmentTxt.sendKeys(Keys.TAB);

		Thread.sleep(2999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_1stColumn));
		select1stRow_1stColumn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_AccountTxt));
		enter_AccountTxt.click();
		enter_AccountTxt.sendKeys(excelReader.getCellData(xlSheetName, 126, 5));

		getFluentWebDriverWait().until(ExpectedConditions.visibilityOfAllElements(bodyAccountListInGrid));
		int accountCount = bodyAccountListInGrid.size();

		System.err.println(accountCount);

		for (int i = 0; i < accountCount; i++) {
			String data = bodyAccountListInGrid.get(i).getText();

			if (data.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 127, 5))) {
				getFluentWebDriverWait().until(ExpectedConditions.visibilityOfAllElements(bodyAccountListInGrid));
				bodyAccountListInGrid.get(i).click();

				break;
			}
		}

		enter_AccountTxt.sendKeys(Keys.TAB);

		removetTxt(payVATTaxCode);
		payVATTaxCode.sendKeys(excelReader.getCellData(xlSheetName, 128, 5));
		Thread.sleep(1999);
		payVATTaxCode.sendKeys(Keys.TAB);

		removetTxt(enter_Amount);
		enter_Amount.sendKeys(excelReader.getCellData(xlSheetName, 129, 5));
		Thread.sleep(1999);
		enter_Amount.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		String docno = documentNumberTxt.getAttribute("value");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(billRefNewReferenceTxt));
		billRefNewReferenceTxt.click();

		Thread.sleep(1000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(billRefPickIcon));
		billRefPickIcon.click();

		Thread.sleep(1000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(billRefOkBtn));
		billRefOkBtn.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(saveBtn));
		saveBtn.click();

		boolean savingVoucher = checkVoucherSavingMessage(docno);

		Thread.sleep(2000);
		getDriver().switchTo().window(openTabs.get(1)).close();
		Thread.sleep(2000);

		getDriver().switchTo().window(openTabs.get(0));

		if (savingVoucher == true) {
			System.out.println(" Test Pass: Voucher Saved With Narration ");
			excelReader.setCellData(xlfile, xlSheetName, 122, 8, resPass);
			return true;
		} else {
			System.out.println(" Test Fail: Voucher Saved With Narration ");
			excelReader.setCellData(xlfile, xlSheetName, 122, 8, resFail);
			return false;
		}
	}

	public boolean checkRaisedVoucherPaymentsVATInBRS() throws InterruptedException {

		Thread.sleep(2000);

		click(financialsMenu);

		click(financialsReportsMenu);

		click(cashAndBankBooksMenu);

		click(bankReconciliationReport);

		Thread.sleep(5500);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportbankTxt));
		reportbankTxt.click();
		reportbankTxt.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME);
		reportbankTxt.sendKeys(Keys.SPACE);

		int bankListCount = bankList.size();
		for (int i = 0; i < bankListCount; i++) {

			String data = bankList.get(i).getText();
			if (data.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 15, 5))) {
				bankList.get(i).click();
			}
		}
		Thread.sleep(2000);

		reportbankTxt.sendKeys(Keys.TAB);

		Thread.sleep(1500);

		Select s1 = new Select(BRSSelectCRDRDrpdwn);
		s1.selectByValue("1");

		Thread.sleep(1500);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(LoadBtn));
		ClickUsingJs(LoadBtn);

		Thread.sleep(3999);

		String act1 = paymentTxt.getText();

		int c1 = paymentrow.size();

		ArrayList<String> a1 = new ArrayList<String>();

		if (act1.equalsIgnoreCase("Payments VAT"))

		{
			for (int i = 1; i < c1; i++) {
				String data5 = paymentrow.get(i).getText();
				a1.add(data5);
			}

		}

		String actbankRecRow = a1.toString();
		String expbankRecRow = "[Pending, " + currentDate() + ", NDT58:1, " + currentDate()
				+ ", 0.00, 20.00, , Payments VAT, , , , ]";

		System.err.println("ACT bankRecRow :" + actbankRecRow);
		System.err.println("EXP bankRecRow :" + expbankRecRow);

		if (actbankRecRow.equalsIgnoreCase(expbankRecRow)) {
			return true;
		} else {

			return false;
		}

	}

	public boolean checkSavingPDRVATWithBeforeAccountingDate()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {

		getDriver().navigate().refresh();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(financialsMenu));
		financialsMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(financialsTransactionMenu));
		financialsTransactionMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(cashAndBankMenu));
		cashAndBankMenu.click();
		
		getAction().moveToElement(PDRVAT).build().perform();
		Thread.sleep(1000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(PDRVAT));
		PDRVAT.click();

		Thread.sleep(2000);

		waitToClick(newBtn);

		checkUserFriendlyMessage();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNumberTxt));
		documentNumberTxt.click();

		// Less than Accounting date we considering --------should not get saved
		dateTxt.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME);
		Thread.sleep(500);
		dateTxt.sendKeys("28/02/2021");
		Thread.sleep(1500);
		dateTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		String actmaturitydate = maturityDateTxt.getAttribute("value");
		String expmaturitydate = "28/02/2021";

		System.out.println(" Act Maturuity: " + actmaturitydate);
		System.out.println(" Exp Maturuity: " + expmaturitydate);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(newCashBankAccountTxt));
		newCashBankAccountTxt.click();

		newCashBankAccountTxt.sendKeys(Keys.SPACE);

		int cashAndBAnkAccountListCount = cashAndBAnkAccountList.size();

		for (int i = 0; i < cashAndBAnkAccountListCount; i++) {
			String data = cashAndBAnkAccountList.get(i).getText();

			if (data.equalsIgnoreCase("Bank")) {
				cashAndBAnkAccountList.get(i).click();

				break;
			}
		}

		newCashBankAccountTxt.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(departmentTxt));

		departmentTxt.sendKeys(Keys.SPACE);

		int departmentcount = departmentListCount.size();

		System.err.println(departmentcount);

		for (int i = 0; i < departmentcount; i++) {
			String data = departmentListCount.get(i).getText();

			if (data.equalsIgnoreCase("DUBAI")) {
				departmentListCount.get(i).click();

				break;
			}
		}

		departmentTxt.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(PDRVATPlaceOfSupplyTXt));
		PDRVATPlaceOfSupplyTXt.click();
		PDRVATPlaceOfSupplyTXt.sendKeys(Keys.END);
		PDRVATPlaceOfSupplyTXt.sendKeys(Keys.SHIFT, Keys.HOME);
		PDRVATPlaceOfSupplyTXt.sendKeys(Keys.SPACE);

		int placeOfSupplyListCount = placeOfSupplyList.size();

		for (int j = 0; j < placeOfSupplyListCount; j++) {
			String data = placeOfSupplyList.get(j).getText();

			if (data.equalsIgnoreCase("Abu Dhabi")) {
				placeOfSupplyList.get(j).click();

			}

		}

		PDRVATPlaceOfSupplyTXt.sendKeys(Keys.TAB);

		PDRVAT_JuridictionTxt.sendKeys(Keys.END);
		PDRVAT_JuridictionTxt.sendKeys(Keys.SHIFT, Keys.HOME);

		PDRVAT_JuridictionTxt.sendKeys(Keys.SPACE);

		int jurdictionListCount = jurdictionList.size();

		for (int k = 0; k < jurdictionListCount; k++) {
			String data = jurdictionList.get(k).getText();

			if (data.equalsIgnoreCase("Abu Dhabi")) {
				jurdictionList.get(k).click();

			}

		}
		PDRVAT_JuridictionTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_1stColumn));
		select1stRow_1stColumn.click();
		enter_AccountTxt.click();
		enter_AccountTxt.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME);
		enter_AccountTxt.sendKeys(Keys.SPACE);
		enter_AccountTxt.sendKeys("Customer New");
		Thread.sleep(2000);

		enter_AccountTxt.sendKeys(Keys.TAB);

		enterTaxcode.click();
		enterTaxcode.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME);
		enterTaxcode.sendKeys("STD");
		Thread.sleep(2000);

		enterTaxcode.sendKeys(Keys.TAB);

		enter_Amount.click();
		enter_Amount.clear();
		enter_Amount.sendKeys("50");
		enter_Amount.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNumberTxt));
		String docno = documentNumberTxt.getAttribute("value");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(billRefPartyName));

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(billRefNewReferenceTxt));
		billRefNewReferenceTxt.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(billRefOkBtn));
		billRefPickIcon.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(billRefOkBtn));
		billRefOkBtn.click();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(saveBtn));
		saveBtn.click();

		boolean ExpMessage = true;
		boolean actSaving = checkBackgroundSavingMessage(docno);

		if (actSaving == ExpMessage) {
			System.err.println("Test Pass: Error Message displayed As Expected  ");
			return true;
		} else {
			System.err.println("Test Fail: Error Message displayed As ");

			Thread.sleep(1999);
			documentNumberTxt.click();
			documentNumberTxt.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME);
			documentNumberTxt.sendKeys("1");

			Thread.sleep(1000);

			documentNumberTxt.sendKeys(Keys.TAB);

			Thread.sleep(1999);
			if (new_DeleteBtn.isDisplayed()) {
				new_DeleteBtn.click();

				Thread.sleep(2000);

				/*
				 * getWaitForAlert();
				 * 
				 * getAlert().accept();
				 */

				clickOn(popUpOKBtn);

				Thread.sleep(2000);

				errorMessageCloseBtn.click();

			}
			return false;
		}

	}

	public boolean checkValidationInPDRVATWithCurrencyWhichIsnotDefinedInRange() throws InterruptedException {

		// Less than Accounting date we considering --------should not get saved
		dateTxt.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME);
		Thread.sleep(500);
		dateTxt.sendKeys("28/02/2008");
		Thread.sleep(1500);
		dateTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		String actmaturitydate = maturityDateTxt.getAttribute("value");
		String expmaturitydate = "28/02/2008";

		System.out.println(" Act Maturuity: " + actmaturitydate);
		System.out.println(" Exp Maturuity: " + expmaturitydate);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(voucherHeaderCurrency));
		voucherHeaderCurrency.click();

		voucherHeaderCurrency.sendKeys(Keys.SHIFT, Keys.HOME);

		voucherHeaderCurrency.sendKeys(Keys.SPACE);

		int currencycount = currencyListCount.size();

		int expCount = 0;

		Thread.sleep(2000);
		voucherHeaderCurrency.sendKeys(Keys.TAB);

		if (currencycount == expCount) {

			Thread.sleep(2000);
			new_newBtn.click();

			Thread.sleep(2000);
			/*
			 * getWaitForAlert();
			 * 
			 * Thread.sleep(2000); getAlert().accept();
			 */

			clickOn(popUpOKBtn);

			return true;
		}

		else {
			Thread.sleep(2000);
			new_newBtn.click();

			/*
			 * Thread.sleep(2000); getWaitForAlert();
			 * 
			 * Thread.sleep(2000); getAlert().accept();
			 */

			clickOn(popUpOKBtn);

			return false;

		}

	}

	public boolean checkSavingVoucherInPostDatedRecepits()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		checkEraseAllTransaction();

		Thread.sleep(2000);

		getDriver().navigate().refresh();

		Thread.sleep(2000);

		click(financialsMenu);

		click(financialsTransactionMenu);

		click(cashAndBankMenu);

		click(postDatedRecepitsMenu);

		Thread.sleep(2000);

		waitToClick(newBtn);

		checkValidationMessage("Screen opened");

		click(documentNumberTxt);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(newCashBankAccountTxt));
		newCashBankAccountTxt.click();

		newCashBankAccountTxt.sendKeys(Keys.SPACE);

		int cashAndBAnkAccountListCount = cashAndBAnkAccountList.size();

		for (int i = 0; i < cashAndBAnkAccountListCount; i++) {
			String data = cashAndBAnkAccountList.get(i).getText();

			if (data.equalsIgnoreCase("HDFC")) {
				cashAndBAnkAccountList.get(i).click();

				break;
			}
		}

		newCashBankAccountTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		click(maturityDateTxt);
		removetTxt(maturityDateTxt);
		Thread.sleep(2000);

		maturityDateTxt.sendKeys(FilterCurrentDate(5));

		Thread.sleep(2000);
		maturityDateTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(departmentTxt));

		departmentTxt.sendKeys(Keys.SPACE);

		int departmentcount = departmentListCount.size();

		System.err.println(departmentcount);

		for (int i = 0; i < departmentcount; i++) {
			String data = departmentListCount.get(i).getText();

			if (data.equalsIgnoreCase("DUBAI")) {
				departmentListCount.get(i).click();

				break;
			}
		}

		departmentTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		click(postDatedReceipts_ChequeNoTxt);
		postDatedReceipts_ChequeNoTxt.sendKeys("PDC_123");
		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNumberTxt));
		String docno = documentNumberTxt.getAttribute("value");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_1stColumn));
		select1stRow_1stColumn.click();

		enter_AccountTxt.click();
		removetTxt(enter_AccountTxt);
		enter_AccountTxt.sendKeys(Keys.SPACE);
		enter_AccountTxt.sendKeys("Customer A");
		Thread.sleep(2000);

		enter_AccountTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		enter_Amount.click();
		enter_Amount.clear();
		enter_Amount.sendKeys("5000");

		enter_Amount.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(billRefPartyName));

		billwisePick();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(saveBtn));
		saveBtn.click();

		boolean actSaving = checkBackgroundSavingMessage(docno);
		boolean expSaving = true;

		if (actSaving == expSaving) {
			System.err.println("Test Pass: Voucher Saved POST DATED RECEPITS ");
			return true;
		} else {
			System.err.println("Test FAIl: Voucher Saved POST DATED RECEPITS ");
			return false;
		}

	}

	@FindBy(xpath = "//*[@id='optionBankName']")
	private static WebElement AssPDC_BackNameTxt;

	@FindBy(xpath = "//*[@id='APdcContainer']/div[1]/div[2]/div/div[2]/div")
	private static WebElement AssPDC_LimitTxt;

	@FindBy(xpath = "//*[@id='btnSave']")
	private static WebElement AssPDC_SaveBtn;

	@FindBy(xpath = "//*[@id='tableAssignPdcLimit_col_1-1']")
	private static WebElement AssPDC_Row1Col1;

	@FindBy(xpath = "//*[@id='id_customer_account']")
	private static WebElement AssPDC_EnterAcc;

	@FindBy(xpath = "//*[@id='id_limit']")
	private static WebElement AssPDC_Enterlimit;

	@FindBy(xpath = "//*[@id='tableAssignPdcLimit_body']/tr/td")
	private static List<WebElement> AssPDC_RowList;

	public boolean checkAssignPDCDiscountLimitScreen()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		click(focusMainSearch);
		focusMainSearch.sendKeys("Assign PDC Limit");
		Thread.sleep(2000);

		focusMainSearch.sendKeys(Keys.ENTER);

		Thread.sleep(3500);

		click(AssPDC_BackNameTxt);
		AssPDC_BackNameTxt.sendKeys("HDFC");
		Thread.sleep(2000);
		AssPDC_BackNameTxt.sendKeys(Keys.TAB);

		Thread.sleep(3500);

		String actAssPDC_LimitTxt = AssPDC_LimitTxt.getText();
		String expAssPDC_LimitTxt = "10000.000";

		System.err.println(" ACT AssPDC_LimitTxt: " + actAssPDC_LimitTxt);
		System.err.println(" Exp AssPDC_LimitTxt: " + expAssPDC_LimitTxt);

		click(AssPDC_Row1Col1);
		removetTxt(AssPDC_EnterAcc);
		AssPDC_EnterAcc.sendKeys("Customer A");

		Thread.sleep(2000);
		AssPDC_EnterAcc.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		removetTxt(AssPDC_Enterlimit);
		AssPDC_Enterlimit.sendKeys("500000");

		Thread.sleep(2000);
		AssPDC_Enterlimit.sendKeys(Keys.TAB);

		String expMessage = "Customers Limit can not Exceed the Bank Limit";
		String actMessage = checkValidationMessage(expMessage);

		Thread.sleep(2000);
		click(AssPDC_Row1Col1);
		AssPDC_EnterAcc.sendKeys(Keys.TAB);
		removetTxt(AssPDC_Enterlimit);
		AssPDC_Enterlimit.sendKeys("500");

		Thread.sleep(2000);

		AssPDC_Enterlimit.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		removetTxt(AssPDC_EnterAcc);
		AssPDC_EnterAcc.sendKeys("Customer B");
		Thread.sleep(2000);
		AssPDC_EnterAcc.sendKeys(Keys.TAB);
		Thread.sleep(2000);
		removetTxt(AssPDC_Enterlimit);
		AssPDC_Enterlimit.sendKeys("450");

		Thread.sleep(2000);

		AssPDC_Enterlimit.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		removetTxt(AssPDC_EnterAcc);
		AssPDC_EnterAcc.sendKeys("Customer C");
		Thread.sleep(2000);
		AssPDC_EnterAcc.sendKeys(Keys.TAB);
		Thread.sleep(2000);
		removetTxt(AssPDC_Enterlimit);
		AssPDC_Enterlimit.sendKeys("350");

		Thread.sleep(2000);

		AssPDC_Enterlimit.sendKeys(Keys.TAB);

		Thread.sleep(4500);

		click(AssPDC_SaveBtn);

		String expSaveMessage = "Records Saved Successfully.";
		String actSaveMessage = checkValidationMessage(expSaveMessage);

		System.err.println("Save Message ACT : " + actSaveMessage);
		System.err.println("Save Message EXP: " + expSaveMessage);

		if (actAssPDC_LimitTxt.equalsIgnoreCase(expAssPDC_LimitTxt) && actSaveMessage.equalsIgnoreCase(expSaveMessage)
				&& actMessage.equalsIgnoreCase(expMessage)) {
			return true;
		}

		else if (actSaveMessage.equalsIgnoreCase(expSaveMessage) && actMessage.equalsIgnoreCase(expMessage)) {
			System.err.println(" Test Pass: Data Displayed as expected in GRID COL");
			return true;
		} else {

			return false;
		}

	}

	public boolean checkSavedAssignPDCScrrenWithHDFC() throws InterruptedException {

		click(focusMainSearch);
		focusMainSearch.sendKeys("Assign PDC Limit");
		Thread.sleep(2000);

		focusMainSearch.sendKeys(Keys.ENTER);

		Thread.sleep(3500);

		click(AssPDC_BackNameTxt);
		AssPDC_BackNameTxt.sendKeys("HDFC");
		Thread.sleep(2000);
		AssPDC_BackNameTxt.sendKeys(Keys.TAB);

		Thread.sleep(3500);

		String actList = listOfElements(AssPDC_RowList);
		String expList = "[1, 500.00, 2, Customer B, 450.00, 3, Customer C, 350.00, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14]";

		System.err.println("List ACT : " + actList);
		System.err.println("List EXP : " + expList);

		Thread.sleep(2563);
		logout();

		if (actList.equalsIgnoreCase(expList)) {
			return true;
		} else {

			return false;
		}

	}

	@FindBy(xpath = "//*[@id='optnCtrlCheqDisBank']")
	private static WebElement CD_BankAccountTxt;

	@FindBy(xpath = "//*[@id='optCtrlStartDate']")
	private static WebElement CD_StartDate;

	@FindBy(xpath = "//*[@id='optCtrlEndDate']")
	private static WebElement CD_endDate;

	@FindBy(xpath = "//*[@value='Load']")
	private static WebElement CD_LoadBtn;

	@FindBy(xpath = "(//*[@class='icon-save hiconright2'])[1]")
	private static WebElement CD_SaveBtn;

	@FindBy(xpath = "//*[@id='btnDepositingBank']")
	private static WebElement CD_ApplyBtn;

	@FindBy(xpath = "//*[@id='ChequeDiscountingTable_body']/tr[1]/td")
	private static List<WebElement> CD_Row1List;

	@FindBy(xpath = "//*[@id='chkColumn']")
	private static WebElement CD_SelectAllBtn;

	@FindBy(xpath = "//*[@id='ChequeDiscountingTable_col_1-8']")
	private static WebElement CD_Row1DisCountCol;

	@FindBy(xpath = "//*[@id='Discount Amount_First']")
	private static WebElement CD_EnterDiscount;

	public boolean checkPostingChequeDiscountingScreen()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		click(focusMainSearch);
		focusMainSearch.sendKeys("Cheque Discounting");
		Thread.sleep(2000);

		focusMainSearch.sendKeys(Keys.ENTER);

		Thread.sleep(3500);

		Thread.sleep(3500);

		String actList = listOfElements(CD_Row1List);
		String expList = "[1, Customer A, 1, " + filterDateBydays(5) + ", PDC_123, 5,000.00, 500.00, 0.00, HDFC, 0, "
				+ filterDateBydays(0) + "]";

		System.err.println("List ACT : " + actList);
		System.err.println("List EXP : " + expList);

		Thread.sleep(2000);

		click(CD_Row1DisCountCol);
		click(CD_EnterDiscount);
		removetTxt(CD_EnterDiscount);
		CD_EnterDiscount.sendKeys("15000");
		Thread.sleep(2000);
		CD_EnterDiscount.sendKeys(Keys.TAB);

		String expMessage = "Discount amount cannot be greater than Discount Limit";
		String actMessage = checkValidationMessage(expMessage);

		Thread.sleep(2000);

		click(CD_Row1DisCountCol);
		click(CD_EnterDiscount);
		removetTxt(CD_EnterDiscount);
		CD_EnterDiscount.sendKeys("250");
		Thread.sleep(2000);
		CD_EnterDiscount.sendKeys(Keys.TAB);
		Thread.sleep(2000);

		click(CD_SelectAllBtn);

		Thread.sleep(2000);

		click(CD_SaveBtn);

		String expSaveMessage = "Saved Successfully.";
		String actSaveMessage = checkValidationMessage(expSaveMessage);

		if (actSaveMessage.equalsIgnoreCase(expSaveMessage) && actMessage.equalsIgnoreCase(expMessage)
				&& actList.equalsIgnoreCase(expList)) {
			return true;
		} else {

			return false;
		}

	}

	public boolean checkPostedVoucherInrtecepitsVAT()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		click(financialsMenu);

		click(financialsTransactionMenu);

		click(cashAndBankMenu);

		click(recepitsVATVoucher);

		Thread.sleep(2000);

		voucherHomePageVoucherSelect("1");

		checkValidationMessage("Voucher loaded successfully");

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNumberTxt));
		String actDocno = documentNumberTxt.getAttribute("value");
		String actVouDate = dateTxt.getAttribute("value");
		String actDepartment = departmentTxt.getAttribute("value");
		String actvoucherHeaderCurrency = voucherHeaderCurrency.getAttribute("value");

		String actCashAndBankAccount = newCashBankAccountTxt.getAttribute("value");

		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		String expDate = df.format(date);

		System.out.println("expDate   :" + expDate);

		String expDocno = "1";
		String expDepartment = "DUBAI";
		String expvoucherHeaderCurrency = "INR";
		String expCashAndBankAccount = "HDFC";

		String actAccountR1 = listOfElements(entryPageRow1List);
		String expAccountR1 = "[1, Cheque Discounting, 250.00, 0.00]";

		System.out.println("Entry Page Document Number    " + actDocno + "  value Expected  " + expDocno);
		System.out.println("Entry Page Voucher Date       " + actVouDate + "  value Expected  " + expDate);
		System.out.println("Entry Page voucherHeaderCurrency        " + actvoucherHeaderCurrency + "  value Expected  "
				+ expvoucherHeaderCurrency);
		System.out.println("Entry Page Department         " + actDepartment + "  value Expected  " + expDepartment);
		System.out.println("Entry Page CashAndBankAccount " + actCashAndBankAccount + "  value Expected  "
				+ expCashAndBankAccount);

		System.out.println("Entry Page Account            " + actAccountR1 + "  value Expected  " + expAccountR1);

		click(new_CloseBtn);

		Thread.sleep(1999);

		click(voucherhomeCloseBtn);

		if (actDocno.equalsIgnoreCase(expDocno) && actVouDate.equalsIgnoreCase(expDate)
				&& actDepartment.equalsIgnoreCase(expDepartment)
				&& actvoucherHeaderCurrency.equalsIgnoreCase(expvoucherHeaderCurrency)
				&& actCashAndBankAccount.equalsIgnoreCase(expCashAndBankAccount) &&

				actAccountR1.equalsIgnoreCase(expAccountR1))

		{
			System.out.println(" Test Pass: Data Displayed As Exepcted  ");
			return true;
		} else {
			System.err.println(" Test Fail: Data Displayed As Exepcted ");
			return false;
		}

	}

	@FindBy(xpath = "(//*[text()='Post-Dated Receipts']//..//input)[1]")
	public static WebElement PDRChkbox;

	public boolean checkPostIngVoucherinCOnvertedMaturedPDCScreen()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		click(focusMainSearch);
		focusMainSearch.sendKeys("Convert Matured PDC");
		Thread.sleep(2000);
		focusMainSearch.sendKeys(Keys.ENTER);

		Thread.sleep(3500);

		click(PDRChkbox);

		Thread.sleep(2000);

		click(PDCStartDate);
		removetTxt(PDCStartDate);
		Thread.sleep(2000);
		PDCStartDate.sendKeys(FilterCurrentDate(20));

		Thread.sleep(2000);
		click(postOnDateChkbox);

		click(postOnDateTxt);
		removetTxt(postOnDateTxt);
		postOnDateTxt.sendKeys(FilterCurrentDate(20));

		convertMaturedPDCsOkIcon.click();

		Thread.sleep(10000);

		click(pdcGridRow1Chkbox);

		Thread.sleep(2000);

		String actAccountR1 = listOfElements(PDC_Row1List);
		String expAccountR1 = "[1, 1, " + filterDateBydays(5) + ", HDFC, 5,000.00, PDC_123]";

		System.err.println(" Row1 ACT: " + actAccountR1);
		System.err.println(" Row1 EXP: " + expAccountR1);

		Thread.sleep(2000);

		click(pdcVoucherOkIcon);

		String expValidationMessage = "Voucher converted successfully";

		String actValidationMessage = checkValidationMessage(expValidationMessage);

		if (actValidationMessage.equalsIgnoreCase(expValidationMessage)
				&& actAccountR1.equalsIgnoreCase(expAccountR1)) {
			return true;
		} else {

			return false;
		}

	}

	public boolean checkRecepictsVATVoucherAfterPostedByPDCConverted()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {

		click(financialsMenu);

		click(financialsTransactionMenu);

		click(cashAndBankMenu);

		click(recepitsVATVoucher);

		Thread.sleep(2000);

		voucherHomePageVoucherSelect("2");

		checkValidationMessage("Voucher loaded successfully");

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNumberTxt));
		String actDocno = documentNumberTxt.getAttribute("value");
		String actVouDate = dateTxt.getAttribute("value");
		String actDepartment = departmentTxt.getAttribute("value");
		String actvoucherHeaderCurrency = voucherHeaderCurrency.getAttribute("value");

		String actCashAndBankAccount = newCashBankAccountTxt.getAttribute("value");

		String expDate = filterDateBydays(20);

		System.out.println("expDate   :" + expDate);

		String expDocno = "2";
		String expDepartment = "Dubai";
		String expvoucherHeaderCurrency = "INR";
		String expCashAndBankAccount = "Cheque Discounting";

		String actAccountR1 = listOfElements(entryPageRow1List);
		String expAccountR1 = "[1, HDFC, 250.00, 0.00]";

		System.out.println("Entry Page Document Number    " + actDocno + "  value Expected  " + expDocno);
		System.out.println("Entry Page Voucher Date       " + actVouDate + "  value Expected  " + expDate);
		System.out.println("Entry Page voucherHeaderCurrency        " + actvoucherHeaderCurrency + "  value Expected  "
				+ expvoucherHeaderCurrency);
		System.out.println("Entry Page Department         " + actDepartment + "  value Expected  " + expDepartment);
		System.out.println("Entry Page CashAndBankAccount " + actCashAndBankAccount + "  value Expected  "
				+ expCashAndBankAccount);

		System.out.println("Entry Page Account            " + actAccountR1 + "  value Expected  " + expAccountR1);

		click(new_CloseBtn);

		Thread.sleep(1999);

		click(voucherhomeCloseBtn);

		if (actDocno.equalsIgnoreCase(expDocno) && actVouDate.equalsIgnoreCase(expDate)
				&& actDepartment.equalsIgnoreCase(expDepartment)
				&& actvoucherHeaderCurrency.equalsIgnoreCase(expvoucherHeaderCurrency)
				&& actCashAndBankAccount.equalsIgnoreCase(expCashAndBankAccount)
				&& actAccountR1.equalsIgnoreCase(expAccountR1))

		{
			System.out.println(" Test Pass: Data Displayed As Exepcted  ");
			return true;
		} else {
			System.err.println(" Test Fail: Data Displayed As Exepcted ");
			return false;
		}

	}

	public boolean checkPostedVoucherInRecepitsPDCConverted()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {

		click(financialsMenu);

		click(financialsTransactionMenu);

		click(cashAndBankMenu);

		click(receiptsVoucher);

		Thread.sleep(2000);

		voucherHomePageVoucherSelect("1");

		checkValidationMessage("Voucher loaded successfully");

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNumberTxt));
		String actDocno = documentNumberTxt.getAttribute("value");
		String actVouDate = dateTxt.getAttribute("value");
		String actDepartment = departmentTxt.getAttribute("value");
		String actvoucherHeaderCurrency = voucherHeaderCurrency.getAttribute("value");

		String actCashAndBankAccount = newCashBankAccountTxt.getAttribute("value");

		String expDate = filterDateBydays(20);

		System.out.println("expDate   :" + expDate);

		String expDocno = "1";
		String expDepartment = "Dubai";
		String expvoucherHeaderCurrency = "INR";
		String expCashAndBankAccount = "HDFC";

		String actAccountR1 = listOfElements(entryPageRow1List);
		String expAccountR1 = "[1, Customer A, 5,000.00, New Reference]";

		System.out.println("Entry Page Document Number    " + actDocno + "  value Expected  " + expDocno);
		System.out.println("Entry Page Voucher Date       " + actVouDate + "  value Expected  " + expDate);
		System.out.println("Entry Page vouHeaderCurrency  " + actvoucherHeaderCurrency + "  value Expected  "
				+ expvoucherHeaderCurrency);
		System.out.println("Entry Page Department         " + actDepartment + "  value Expected  " + expDepartment);
		System.out.println("Entry Page CashAndBankAccount " + actCashAndBankAccount + "  value Expected  "
				+ expCashAndBankAccount);

		System.out.println("Entry Page Account            " + actAccountR1 + "  value Expected  " + expAccountR1);

		click(new_CloseBtn);

		Thread.sleep(1999);

		click(voucherhomeCloseBtn);

		Thread.sleep(2000);

		// logout();

		if (actDocno.equalsIgnoreCase(expDocno) && actVouDate.equalsIgnoreCase(expDate)
				&& actDepartment.equalsIgnoreCase(expDepartment)
				&& actvoucherHeaderCurrency.equalsIgnoreCase(expvoucherHeaderCurrency)
				&& actCashAndBankAccount.equalsIgnoreCase(expCashAndBankAccount)
				&& actAccountR1.equalsIgnoreCase(expAccountR1))

		{
			System.out.println(" Test Pass: Data Displayed As Exepcted  ");
			return true;
		} else {
			System.err.println(" Test Fail: Data Displayed As Exepcted ");
			return false;
		}

	}

	public boolean checkBankReconciliationStatementReport() throws InterruptedException, AWTException, IOException {

		focusMainSearch("Bank Reconciliation Statement");

		Thread.sleep(2345);

		waitOn(sl_DateOptionDropdown);

		Select s1 = new Select(sl_DateOptionDropdown);

		s1.selectByValue("2");

		clickOn(reportaccountTxt);
		reportaccountTxt.sendKeys("HDFC");
		Thread.sleep(1456);
		reportaccountTxt.sendKeys(Keys.TAB);
		Thread.sleep(1456);

		clickOn(sl_OkBtn);

		waitOn(report_CloseBtn);

		String actRow1 = listOfElements(report1stRowList);
		String expRow1 = "[1, Balance as per Books, 5,000.00, 5,000.00, 350.00]";

		System.err.println(" ACT ROw1 : " + actRow1);
		System.err.println(" EXP ROw1 : " + expRow1);

		String actRow2 = listOfElements(report2ndRowList);
		String expRow2 = "[2, " + currentDate()
				+ ", NDT57 : 1, Cheque Discounting, 250.00, 4,750.00, 250.00, 250.00, 4,750.00, 250.00, 17.50, 332.50, 17.50]";

		System.err.println(" ACT ROw2 : " + actRow2);
		System.err.println(" EXP ROw2 : " + expRow2);

		String actRow3 = listOfElements(report3rdRowList);
		String expRow3 = "[3, " + FilterCurrentDate(10)
				+ ", Rct : 1, Customer A, 5,000.00, 250.00, 5,250.00, 5,000.00, 250.00, 5,250.00, 350.00, 17.50, 367.50]";

		System.err.println(" ACT ROw3 : " + actRow3);
		System.err.println(" EXP ROw3 : " + expRow3);

		String actRow4 = listOfElements(report4thRowList);
		String expRow4 = "[4, " + FilterCurrentDate(10)
				+ ", NDT57 : 2, Cheque Discounting, 250.00, 5,000.00, 250.00, 5,000.00, 17.50, 350.00]";

		System.err.println(" ACT ROw4 : " + actRow4);
		System.err.println(" EXP ROw4 : " + expRow4);

		String actRow5 = listOfElements(report5thRowList);
		String expRow5 = "[5, Balance as per Bank]";

		System.err.println(" ACT ROw5 : " + actRow5);
		System.err.println(" EXP ROw5 : " + expRow5);

		String actRow6 = listOfElements(report6thRowList);
		String expRow6 = "[6, Grand Total, 5,250.00, 250.00, 4,500.00, 10,500.00, 5,250.00, 250.00, 4,500.00, 10,500.00, 367.50, 17.50, 315.00, 735.00]";

		System.err.println(" ACT ROw6 : " + actRow6);
		System.err.println(" EXP ROw6 : " + expRow6);

		Thread.sleep(1568);

		clickOn(report_CloseBtn);

		logout();

		Thread.sleep(2000);

		prongHornStopAtAdminLevel();

		Thread.sleep(2000);

		if (actRow1.equalsIgnoreCase(expRow1) && actRow2.equalsIgnoreCase(expRow2) && actRow3.equalsIgnoreCase(expRow3)
				&& actRow4.equalsIgnoreCase(expRow4) && actRow5.equalsIgnoreCase(expRow5)
				&& actRow6.equalsIgnoreCase(expRow6)) {
			System.err.println(" ******************Test pass: Data Displayed as per Expected");
			return true;

		}

		else if (actRow1.startsWith(expRow1) && actRow2.startsWith(expRow2) && actRow3.startsWith(expRow3)
				&& actRow4.startsWith(expRow4) && actRow5.startsWith(expRow5) && actRow6.startsWith(expRow6)) {
			System.err.println(" *********ELSE IF*********Test pass: Data Displayed as per Expected");
			return true;
		}

		else {
			System.err.println(" *****************Test Fail: Data Displayed as per Expected");
			return false;

		}

	}

	public BRSPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

}
