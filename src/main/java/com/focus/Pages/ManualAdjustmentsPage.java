package com.focus.Pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.stringtemplate.v4.compiler.STParser.ifstat_return;

import com.focus.base.BaseEngine;
import com.focus.utilities.DriverUtility;

public class ManualAdjustmentsPage extends BaseEngine {

	public void checkLoginToManualAdjsuments()
			throws InterruptedException, IOException, AWTException, EncryptedDocumentException, InvalidFormatException {

		Thread.sleep(2678);

		LoginPage lp = new LoginPage(getDriver());

		LoginPage.checkLoginPageTitleByURLInputInBrowser(DriverUtility.FINUrl);

		checkLoginToSelectedCompany("ManualAdjustment", "useralloptions", "12345");
		//checkLoginToSelectedCompany("ManualAdjustment", "su", "su");

		Thread.sleep(3500);

	}
	
	
	public static boolean checkLogin()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		

		Thread.sleep(1999);
		getDriver().navigate().refresh();
		Thread.sleep(1999);
		
		

		LoginPage lp = new LoginPage(getDriver());

		lp.checkLoginPageTitleByURLInputInBrowser(DriverUtility.FINUrl);

		re_LunchBrowser();

		Thread.sleep(3000);

		String unamelt = "useralloptions";

		String pawslt = "12345";

		lp.enterUserName(unamelt);

		Thread.sleep(2000);

		lp.enterPassword(pawslt);
		
		companyDropDownList.click();

		String compname = "ManualAdjustment";

		Select oSelect = new Select(companyDropDownList);

		List<WebElement> elementCount = oSelect.getOptions();

		int cqSize = elementCount.size();

		System.out.println("CompanyDropdownList Count :" + cqSize);

		int i;

		for (i = 0; i < elementCount.size(); i++) {

			elementCount.get(i).getText();

			String optionName = elementCount.get(i).getText();
			if (optionName.toUpperCase().startsWith(compname.toUpperCase())) {
				System.out.println("q" + elementCount.get(i).getText());
				elementCount.get(i).click();
			}

		}

		Thread.sleep(2000);

		lp.clickOnSignInBtn();

		Thread.sleep(18000);

		

		boolean expHomeMenuDisplayed = true;
		boolean actHomeMenuDisplayed = homeMenu.isDisplayed();

		if (actHomeMenuDisplayed==expHomeMenuDisplayed) {
			
			return true;

		} else {
			
			return false;

		}
		  

	}

	public static void reindexClear() throws InterruptedException {

		try {

			fluentWaitWith250Sec().until(ExpectedConditions.elementToBeClickable(reindexCancelBtn));

			if (reindexCancelBtn.isDisplayed()) {

				System.err.println("Reindexing Log is Displaying while Restore Company");

				click(reindexCancelBtn);

				Thread.sleep(2000);

				click(signIn);
			}

		} catch (Exception e) {
			System.err.println("Catch Block Executed-----------------------------");

		}

	}

	public static void restoreCompany(String companyName) throws InterruptedException, IOException, AWTException {

		Thread.sleep(1000);

		
		click(homeMenu);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dataMangementMenu));
		dataMangementMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(restore));
		restore.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(folderpathExpandBtn));
		folderpathExpandBtn.click();

		Thread.sleep(9999);

		Robot rb = new Robot();
		StringSelection str = new StringSelection(getBaseDir() + "\\backup\\" + companyName + ".fbak");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);

		rb.keyPress(KeyEvent.VK_CONTROL);
		rb.keyPress(KeyEvent.VK_V);

		rb.keyRelease(KeyEvent.VK_CONTROL);
		rb.keyRelease(KeyEvent.VK_V);

		rb.keyPress(KeyEvent.VK_ENTER);
		rb.keyRelease(KeyEvent.VK_ENTER);

		Thread.sleep(9999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(restoreCompanyBtn));
		restoreCompanyBtn.click();

		Thread.sleep(3000);
		
		
		try {
			getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(overRideYesBtn));
			overRideYesBtn.click();

			System.err.println("COMPANY EXISTS");

			//Thread.sleep(130000);

		} catch (Exception e) {
			System.err.println("NO OLDER COMPANY EXISTS");
		}
		
		new WebDriverWait(getDriver(), 500).until(ExpectedConditions.alertIsPresent());

		if (getIsAlertPresent()) {
			System.err.println(" Alert Text : *************************" + getAlert().getText());
			System.err.println("Alert Displayed");
			getWaitForAlert();

			getAlert().accept();
		}

		Thread.sleep(3000);

		ScrollToElement(userNameDisplayLogo);

		userNameDisplayLogo.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(logoutOption));
		logoutOption.click();

		Thread.sleep(3000);

		checkLoginToSelectedCompany(companyName, "useralloptions", "12345");
		//checkLoginToSelectedCompany(companyName, "su", "su");
		Thread.sleep(3000);

		//reindexClear();

	}

	public void checkRestoreOptionInManualAdjustment() throws InterruptedException, AWTException, IOException {

		Thread.sleep(2000);
		 restoreCompany("ManualAdjustment");

		Thread.sleep(2000);

	/*	logout();

		Thread.sleep(2000);

		prongHornStartAtAdminLevel();

		Thread.sleep(2000);

		checkLoginToSelectedCompany("ManualAdjustment", "useralloptions", "12345");

		Thread.sleep(2000);

		System.err.println(" Company Login and Restore Done ");
*/
	}

	public void date(int dt) throws InterruptedException {
		Thread.sleep(2000);
		click(dateTxt);
		removetTxt(dateTxt);
		Thread.sleep(2000);
		dateTxt.sendKeys(FilterCurrentDate(dt));
		Thread.sleep(2000);
		dateTxt.sendKeys(Keys.TAB);
	}

	public boolean CheckSavingSalesInvoiceVATWithDATE(int dt, String customer, String dep, String war, String qty,
			String rate) throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		Thread.sleep(3000);

	/*	click(financialsMenu);

		click(financialsTransactionMenu);

		click(financialTransactionSalesMenu);

		click(salesInvoiceVATVoucher);

		Thread.sleep(1500);

		click(newBtn);

		Thread.sleep(2000);*/
		click(documentNumberTxt);

		Thread.sleep(2000);

		date(dt);

		Thread.sleep(1999);
		customerAccountTxt.click();
		customerAccountTxt.sendKeys(customer);
		Thread.sleep(2000);
		customerAccountTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		departmentTxt.click();
		departmentTxt.sendKeys(dep);
		Thread.sleep(2000);
		departmentTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		click(select1stRow_1stColumn);
		pvWareHouseTxt.click();
		pvWareHouseTxt.sendKeys(Keys.SPACE);

		int warehousecount = pvwareHouseListCount.size();

		System.err.println(warehousecount);

		for (int i = 0; i < warehousecount; i++) {
			String data = pvwareHouseListCount.get(i).getText();

			if (data.equalsIgnoreCase(war)) {
				pvwareHouseListCount.get(i).click();

				break;
			}
		}

		pvWareHouseTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_ItemTxt));
		removetTxt(enter_ItemTxt);
		enter_ItemTxt.sendKeys("std");

		Thread.sleep(1999);

		enter_ItemTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		removetTxt(enterSalesTaxcode);
		enterSalesTaxcode.sendKeys("STD");

		Thread.sleep(2500);

		enterSalesTaxcode.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		click(select1stRow_5thColumn);

		Thread.sleep(2000);
		click(select1stRow_8thColumn);
		enter_AQTxt.sendKeys(qty);
		enter_AQTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		enter_FQTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		click(select1stRow_11thColumn);
		Thread.sleep(2000);
		click(select1stRow_14thColumn);

		Thread.sleep(2000);
		enter_Rate.click();
		enter_Rate.clear();
		enter_Rate.sendKeys(rate);
		enter_Rate.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		enter_Gross.click();
		enter_Gross.sendKeys(Keys.TAB);

		String docno = documentNumberTxt.getAttribute("value");

		Thread.sleep(1999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(voucherSaveBtn));
		voucherSaveBtn.click();

		Thread.sleep(1999);
		billwisePick();

		//String exp = "This Transaction will make the Stock Negative";
		//String act = checkValidationMessage(exp);

		String expMessage="Voucher saved successfully";
		String actMessage= checkValidationMessage(expMessage);
		
		String expMessage1= docno;
		//boolean actMessage = checkVoucherSavingMessage2(docno);
		//boolean expMessage = true;

		/*
		 * boolean actMessage = checkBackgroundSavingNegativeMessage(docno); boolean
		 * expMessage = true;
		 */

		System.err.println(
				" Saving+" + docno + "voucher Completed**********:" + actMessage + " Value Exp: " + expMessage);

		if (actMessage.startsWith(expMessage) && actMessage.endsWith(expMessage1)) {
			System.err.println(" Test Pass: Sales Invoice VAT Voucher Saved ");
			return true;
		}

		else {
			System.err.println(" Test FAIl: Sales Invoice VAT Voucher Saved ");
			return false;
		}
	}

	@FindBy(xpath = "(//*[@id='idGlobalError']/div/div[1]/button)[1]")
	public static WebElement errorMessageCloseBtn12;

	public boolean CheckSavingSalesInvoiceVAT(String customer, String dep, String war, String qty, String rate)
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		/*click(financialsMenu);

		click(financialsTransactionMenu);

		click(financialTransactionSalesMenu);

		click(salesInvoiceVATVoucher);

		Thread.sleep(8000);
		click(newBtn);
		*/

		//waitForElement(documentNumberTxt);
		//click(documentNumberTxt);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNumberTxt));
		customerAccountTxt.click();
		customerAccountTxt.sendKeys(customer);
		Thread.sleep(2000);
		customerAccountTxt.sendKeys(Keys.TAB);

		departmentTxt.click();
		departmentTxt.sendKeys(dep);
		Thread.sleep(2000);
		departmentTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		click(select1stRow_1stColumn);
		pvWareHouseTxt.click();
		pvWareHouseTxt.sendKeys(Keys.SPACE);

		int warehousecount = pvwareHouseListCount.size();

	//	System.err.println(warehousecount);

		for (int i = 0; i < warehousecount; i++) {
			String data = pvwareHouseListCount.get(i).getText();

			if (data.equalsIgnoreCase(war)) {
				pvwareHouseListCount.get(i).click();

				break;
			}
		}

		pvWareHouseTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_ItemTxt));
		removetTxt(enter_ItemTxt);
		enter_ItemTxt.sendKeys("std");

		Thread.sleep(1999);

		enter_ItemTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		removetTxt(enterSalesTaxcode);
		enterSalesTaxcode.sendKeys("STD");

		Thread.sleep(2500);

		enterSalesTaxcode.sendKeys(Keys.TAB);

		click(select1stRow_5thColumn);

		click(select1stRow_8thColumn);
		enter_AQTxt.sendKeys(qty);
		enter_AQTxt.sendKeys(Keys.TAB);

		enter_FQTxt.sendKeys(Keys.TAB);

		click(select1stRow_11thColumn);
		click(select1stRow_14thColumn);
		enter_Rate.click();
		enter_Rate.clear();
		enter_Rate.sendKeys(rate);
		enter_Rate.sendKeys(Keys.TAB);

		enter_Gross.click();
		enter_Gross.sendKeys(Keys.TAB);

		String docno = documentNumberTxt.getAttribute("value");

		Thread.sleep(1999);
		click(voucherSaveBtn);

		Thread.sleep(1999);

		click(billRefNewReferenceTxt);

		click(billRefPickIcon);

		Thread.sleep(1999);

		click(billRefOkBtn);

		//checkValidationMessage("This Transaction will make the Stock Negative");

		boolean actMessage = checkVoucherSavingMessage(docno);
		boolean expMessage = true;

		/*
		 * boolean actMessage = checkBackgroundSavingNegativeMessage(docno); boolean
		 * expMessage = true;
		 */

		System.err.println(
				" Saving+" + docno + "voucher Completed**********:" + actMessage + " Value Exp: " + expMessage);

		if (actMessage == expMessage) {
			System.err.println(" Test Pass: Sales Invoice VAT Voucher Saved ");
			return true;
		} else {
			System.err.println(" Test FAIl: Sales Invoice VAT Voucher Saved ");
			return false;
		}

	}

	public boolean checkSavingVoucherInRecepitsVATWithdate(int dt, String dep, String acc, String amt)
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		Thread.sleep(3000);

		getDriver().navigate().refresh();

		Thread.sleep(3000);

		ClickUsingJs(financialsMenu);

		click(financialsTransactionMenu);

		click(cashAndBankMenu);

		click(recepitsVATVoucher);
		Thread.sleep(4000);

		click(newBtn);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(newCashBankAccountTxt));
		Thread.sleep(2000);

		date(dt);

		Thread.sleep(2000);

		newCashBankAccountTxt.click();

		newCashBankAccountTxt.sendKeys(Keys.SPACE);

		int cashAndBAnkAccountListCount = cashAndBAnkAccountList.size();

		System.err.println("cashAndBAnkAccountListCount   : " + cashAndBAnkAccountListCount);

		for (int i = 0; i < cashAndBAnkAccountListCount; i++) {
			String data = cashAndBAnkAccountList.get(i).getText();

			if (data.equalsIgnoreCase("Bank")) {
				cashAndBAnkAccountList.get(i).click();

				break;
			}
		}

		newCashBankAccountTxt.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(departmentTxt));

		departmentTxt.click();
		departmentTxt.sendKeys(Keys.SHIFT, Keys.HOME, Keys.BACK_SPACE);
		departmentTxt.sendKeys(Keys.SPACE);
		Thread.sleep(2000);
		int departmentcount = departmentListCount.size();

		System.err.println(departmentcount);

		for (int i = 0; i < departmentcount; i++) {
			String data = departmentListCount.get(i).getText();

			if (data.equalsIgnoreCase(dep)) {
				departmentListCount.get(i).click();
				break;
			}
		}

		Thread.sleep(1000);

		departmentTxt.sendKeys(Keys.TAB);

		Thread.sleep(2500);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(placeofSupplyTxt));
		placeofSupplyTxt.click();
		placeofSupplyTxt.sendKeys(Keys.SPACE);

		int placeOfSupplyListCount = placeofSupplyList.size();

		System.err.println(placeOfSupplyListCount);

		for (int i = 0; i < placeOfSupplyListCount; i++) {
			String data = placeofSupplyList.get(i).getText();

			if (data.equalsIgnoreCase("Abu Dhabi")) {
				placeofSupplyList.get(i).click();

				break;
			}
		}

		placeofSupplyTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_1stColumn));
		select1stRow_1stColumn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_AccountTxt));
		enter_AccountTxt.click();
		enter_AccountTxt.sendKeys(acc);

		Thread.sleep(2000);

		enter_AccountTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enterReceiptsVATTaxCode));
		removetTxt(enterReceiptsVATTaxCode);
		enterReceiptsVATTaxCode.sendKeys("STD");
		Thread.sleep(2000);
		enterReceiptsVATTaxCode.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		enter_Amount.sendKeys(amt);
		enter_Amount.sendKeys(Keys.TAB);

		Thread.sleep(3000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(billRefPartyName));

		billwisePick();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNumberTxt));
		String docno = documentNumberTxt.getAttribute("value");

		click(voucherSaveBtn);

		boolean actSaving = checkVoucherSavingMessage(docno);
		boolean expSaving = true;

		System.err
				.println(" Saving+" + docno + "voucher Completed**********:" + actSaving + " Value Exp: " + expSaving);

		if (actSaving == expSaving)

		{
			System.err.println("Test Pass: Recepits VAT Voucher Saved  ");
			return true;
		} else {
			System.err.println("Test FAIL: Recepits VAT Voucher Saved  ");
			return false;
		}
	}

	public boolean checkSavingVoucherInRecepitsVAT(String dep, String acc, String amt)
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException 
	{

		getDriver().navigate().refresh();

		Thread.sleep(3000);

		ClickUsingJs(financialsMenu);

		click(financialsTransactionMenu);

		click(cashAndBankMenu);

		click(recepitsVATVoucher);

		waitToClick(newBtn);

		waitForElement(newCashBankAccountTxt);
		newCashBankAccountTxt.click();

		newCashBankAccountTxt.sendKeys(Keys.SPACE);

		int cashAndBAnkAccountListCount = cashAndBAnkAccountList.size();

		System.err.println("cashAndBAnkAccountListCount   : " + cashAndBAnkAccountListCount);

		for (int i = 0; i < cashAndBAnkAccountListCount; i++) {
			String data = cashAndBAnkAccountList.get(i).getText();

			if (data.equalsIgnoreCase("Bank")) {
				cashAndBAnkAccountList.get(i).click();

				break;
			}
		}

		newCashBankAccountTxt.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(departmentTxt));

		departmentTxt.click();
		departmentTxt.sendKeys(Keys.SHIFT, Keys.HOME, Keys.BACK_SPACE);
		departmentTxt.sendKeys(Keys.SPACE);
		Thread.sleep(2000);
		int departmentcount = departmentListCount.size();

		System.err.println(departmentcount);

		for (int i = 0; i < departmentcount; i++) {
			String data = departmentListCount.get(i).getText();

			if (data.equalsIgnoreCase(dep)) {
				departmentListCount.get(i).click();
				break;
			}
		}

		Thread.sleep(1000);

		departmentTxt.sendKeys(Keys.TAB);

		Thread.sleep(2500);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(placeofSupplyTxt));
		placeofSupplyTxt.click();
		placeofSupplyTxt.sendKeys(Keys.SPACE);

		int placeOfSupplyListCount = placeofSupplyList.size();

		System.err.println(placeOfSupplyListCount);

		for (int i = 0; i < placeOfSupplyListCount; i++) {
			String data = placeofSupplyList.get(i).getText();

			if (data.equalsIgnoreCase("Abu Dhabi")) {
				placeofSupplyList.get(i).click();

				break;
			}
		}

		placeofSupplyTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_1stColumn));
		select1stRow_1stColumn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_AccountTxt));
		enter_AccountTxt.click();
		enter_AccountTxt.sendKeys(acc);

		Thread.sleep(2000);

		enter_AccountTxt.sendKeys(Keys.TAB);

		Thread.sleep(800);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enterReceiptsVATTaxCode));
		removetTxt(enterReceiptsVATTaxCode);
		enterReceiptsVATTaxCode.sendKeys("STD");
		Thread.sleep(2000);
		enterReceiptsVATTaxCode.sendKeys(Keys.TAB);

		Thread.sleep(800);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_Amount));
		enter_Amount.sendKeys(amt);
		enter_Amount.sendKeys(Keys.TAB);

		Thread.sleep(1500);

		billwisePick();

		Thread.sleep(1500);

		String docno = documentNumberTxt.getAttribute("value");

		Thread.sleep(1500);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(voucherSaveBtn));
		voucherSaveBtn.click();

		boolean actSaving = checkVoucherSavingMessage(docno);
		boolean expSaving = true;

		System.err.println(" Saving voucher: " + actSaving + " Value Exp: " + expSaving);

		if (actSaving == expSaving)

		{
			System.err.println("Test Pass: Recepits VAT Voucher Saved  ");
			return true;
		} else {
			System.err.println("Test FAIL: Recepits VAT Voucher Saved  ");
			return false;
		}
	}

	// Manual Adjustmnet Screen starts from here

	@FindBy(xpath = "//select[@id='id_SelectARAP']")
	private static WebElement MA_ARAPDrpdwn;

	@FindBy(xpath = "//input[@id='optTag']")
	private static WebElement MA_DepDrpDwn;

	@FindBy(xpath = "//select[@id='id_SelectType']")
	private static WebElement MA_SelectTypeDrpdwn;

	@FindBy(xpath = "//input[@id='optAccount']")
	private static WebElement MA_AccountDrpdwn;

	@FindBy(xpath = "//input[@id='optCurrency']")
	private static WebElement MA_CurrencyDrpDwn;

	//@FindBy(xpath = "//*[@id='btnLoad1']/a/span")
	@FindBy(xpath="//span[@class='icon-load-default']")
	private static WebElement MA_LoadBtn;

	@FindBy(xpath = "//*[@id='btnOk']")
	private static WebElement MA_OkBtn;

	@FindBy(xpath = "(//*[@id='btnCancel'])[1]")
	private static WebElement MA_CanelBtn;

	@FindBy(xpath = "//*[@id='id_DrAdj_Grid_body']/tr")
	private static WebElement MA_DebitRowList;

	@FindBy(xpath = "//*[@id='id_CrAdj_Grid_body']/tr")
	private static WebElement MA_CreditRowList;

	@FindBy(xpath = "//label[@id='id_lblLeftGridTotal']")
	private static WebElement MA_DebitTotalSum;

	@FindBy(xpath = "//label[@id='id_lblRightGridTotal']")
	private static WebElement MA_CreditTotalSum;

	@FindBy(xpath = "//*[@id='20']/span")
	private static WebElement utilitesMenu;

	@FindBy(xpath = "//*[@id='3316']/span")
	private static WebElement ManualAdjustemntMenu;

	@FindBy(xpath = "//*[@id='dtLFFromDate']")
	private static WebElement ManAdj_DebitFilterStartdateTxt;

	@FindBy(xpath = "//*[@id='id_DrAdj_Grid_col_1-1']/input")
	private static WebElement debitRow1Chkbox;

	@FindBy(xpath = "//*[@id='id_DrAdj_Grid_body']/tr/td[2]/input")
	private static List<WebElement> debitRowChkboxList;

	@FindBy(xpath = "//*[@id='id_CrAdj_Grid_body']/tr/td[2]/input")
	private static List<WebElement> creditRowChkboxList;

	@FindBy(xpath = "//*[@id='id_CrAdj_Grid_body']/tr/td[9]")
	private static List<WebElement> creditDocNOList;

	@FindBy(xpath = "//*[@id='id_CrAdj_Grid_col_1-1']/input")
	private static WebElement CreditRow1Chkbox;

	@FindBy(xpath = "//*[@id='id_CrAdj_Grid_col_2-1']/input")
	private static WebElement CreditRow2Chkbox;

	public void eraseAllTransactions()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException {

		Thread.sleep(1999);

		ClickUsingJs(homeMenu);

		click(dataMangementMenu);

		click(eraseAll);
		Thread.sleep(4000);
		
		click(eraseTranscationsRadio);

		click(eraseAllOkBtn);

		if (getIsAlertPresent()) {
			getWaitForAlert();

			getAlert().accept();
		}

		checkValidationMessage("Data deleted successfully.");

		Thread.sleep(2000);

	/*	logout();
		Thread.sleep(2000);

		checkLoginToSelectedCompany("ManualAdjustment", "useralloptions", "12345");*/

	}

	public boolean checkSavingVoucherInSalesInvoiceVATANDRecepicts()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException 
	{

		Thread.sleep(2000);

		eraseAllTransactions();

/*		logout();

		Thread.sleep(2567);

		prongHornStartAtAdminLevel();

		Thread.sleep(2567);

		checkLoginToManualAdjsuments();

		waitOn(homeMenu);
*/
		Thread.sleep(4000);
		
		getDriver().navigate().refresh();
		Thread.sleep(4000);
		
		click(financialsMenu);

		click(financialsTransactionMenu);

		click(financialTransactionSalesMenu);

		click(salesInvoiceVATVoucher);

		Thread.sleep(8000);
		click(newBtn);
		Thread.sleep(4000);

		boolean actSales = CheckSavingSalesInvoiceVAT("Customer A", "AMERICA", "HYDERABAD", "10", "10");
		boolean expSales = true;

		System.err.println(" Saving Sales Invoice VAT : " + actSales + " Value Exp: " + expSales);

		boolean actRec = checkSavingVoucherInRecepitsVAT("AMERICA", "Customer A", "100");
		boolean expRec = true;

		System.err.println(" Saving Recepicts VAT : " + actRec + " Value Exp: " + expRec);

		if (actSales == expSales && actRec == expRec) {
			return true;
		} else {

			return false;
		}

	}

	public boolean checkManualAdjustMentHomeScreen()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		
		click(homeMenu);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(utilitesMenu));
		utilitesMenu.click();

		ClickUsingJs(ManualAdjustemntMenu);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_ARAPDrpdwn));
	//	waitForElement(MA_ARAPDrpdwn);

		boolean actMA_ARAPDrpdwn = MA_ARAPDrpdwn.isDisplayed();
		boolean actMA_AccountDrpdwn = MA_AccountDrpdwn.isDisplayed();
		boolean actMA_DepDrpDwn = MA_DepDrpDwn.isDisplayed();
		boolean actMA_CurrencyDrpDwn = MA_CurrencyDrpDwn.isDisplayed();
		boolean actMA_SelectTypeDrpdwn = MA_SelectTypeDrpdwn.isDisplayed();
		boolean actMA_OkBtn = MA_OkBtn.isDisplayed();
		boolean actMA_CanelBtn = MA_CanelBtn.isDisplayed();

		boolean expMA_ARAPDrpdwn = true;
		boolean expMA_AccountDrpdwn = true;
		boolean expMA_DepDrpDwn = true;
		boolean expMA_CurrencyDrpDwn = true;
		boolean expMA_SelectTypeDrpdwn = true;
		boolean expMA_OkBtn = true;
		boolean expMA_CanelBtn = true;

		System.out.println("MA_ARAPDrpdwn            : " + actMA_ARAPDrpdwn + " Value Expected : " + expMA_ARAPDrpdwn);
		System.out.println(
				"MA_AccountDrpdwn         : " + actMA_AccountDrpdwn + " Value Expected : " + expMA_AccountDrpdwn);
		System.out.println("MA_DepDrpDwn             : " + actMA_DepDrpDwn + " Value Expected : " + expMA_DepDrpDwn);
		System.out.println(
				"MA_CurrencyDrpDwn        : " + actMA_CurrencyDrpDwn + " Value Expected : " + expMA_CurrencyDrpDwn);
		System.out.println(
				"MA_SelectTypeDrpdwn      : " + actMA_SelectTypeDrpdwn + " Value Expected : " + expMA_SelectTypeDrpdwn);
		System.out.println("MA_OkBtn                 : " + actMA_OkBtn + " Value Expected : " + expMA_OkBtn);
		System.out.println("MA_CanelBtn              : " + actMA_CanelBtn + " Value Expected : " + expMA_CanelBtn);

		if (actMA_ARAPDrpdwn == expMA_ARAPDrpdwn && actMA_AccountDrpdwn == expMA_AccountDrpdwn
				&& actMA_DepDrpDwn == expMA_DepDrpDwn && actMA_OkBtn == expMA_OkBtn
				&& actMA_SelectTypeDrpdwn == expMA_SelectTypeDrpdwn && actMA_CurrencyDrpDwn == expMA_CurrencyDrpDwn
				&& actMA_CanelBtn == expMA_CanelBtn) {
			System.out.println("Test Pass: Displayed all the filed in scrren");
			return true;
		} else {
			System.out.println(" Test Fail: Not Displayed Manual Adjustment Screen ");
			return false;
		}
	}

	@FindBy(xpath = "//*[@id='id_CrAdj_Grid_col_1-1']/input")
	private static WebElement MA_CreditSide1stRowChkBox;

	@FindBy(xpath = "//*[@id='id_DrAdj_Grid_col_1-1']/input")
	private static WebElement MA_DebitSide1stRowChkBox;

	@FindBy(xpath = "//*[@id='id_DrAdj_Grid_body']/tr[1]/td")
	private static List<WebElement> MA_DebitSideRow1list;

	@FindBy(xpath = "//*[@id='id_DrAdj_Grid_body']/tr[2]/td")
	private static List<WebElement> MA_DebitSideRow2list;

	@FindBy(xpath = "//*[@id='id_DrAdj_Grid_body']/tr[3]/td")
	private static List<WebElement> MA_DebitSideRow3list;

	@FindBy(xpath = "//*[@id='id_DrAdj_Grid_body']/tr[4]/td")
	private static List<WebElement> MA_DebitSideRow4list;

	@FindBy(xpath = "//*[@id='id_DrAdj_Grid_body']/tr[5]/td")
	private static List<WebElement> MA_DebitSideRow5list;

	@FindBy(xpath = "//*[@id='id_CrAdj_Grid_body']/tr[1]/td")
	private static List<WebElement> MA_CreditSideRow1list;

	@FindBy(xpath = "//*[@id='id_CrAdj_Grid_body']/tr[2]/td")
	private static List<WebElement> MA_CreditSideRow2ist;

	@FindBy(xpath = "//*[@id='id_CrAdj_Grid_body']/tr[3]/td")
	private static List<WebElement> MA_CreditSideRow3list;

	@FindBy(xpath = "//*[@id='id_CrAdj_Grid_body']/tr[4]/td")
	private static List<WebElement> MA_CreditSideRow4list;

	@FindBy(xpath = "//*[@id='id_CrAdj_Grid_body']/tr[5]/td")
	private static List<WebElement> MA_CreditSideRow5list;

	@FindBy(xpath = "//*[@id='id_lblLeftGridTotal']")
	private static WebElement debitTotal;

	@FindBy(xpath = "//*[@id='id_lblRightGridTotal']")
	private static WebElement creditTotal;
	
	@FindBy(xpath="//*[@id='id_fcollapse']")
	public static WebElement filterCollapseBtn;
	
	@FindBy(xpath="//*[@id='id_fExpand']")
	public static WebElement filterExpandBtn;
	
	public boolean checkManualAdjutmentScreenWithCustomerTyepandAdjustmentWithAR()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException 
	{

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_ARAPDrpdwn));
		Select ARAP = new Select(MA_ARAPDrpdwn);
		ARAP.selectByValue("0");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_AccountDrpdwn));
		MA_AccountDrpdwn.sendKeys("Customer A");
		Thread.sleep(1999);
		MA_AccountDrpdwn.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_DepDrpDwn));
		MA_DepDrpDwn.sendKeys("AMERICA");
		Thread.sleep(1999);
		MA_DepDrpDwn.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_CurrencyDrpDwn));
		MA_CurrencyDrpDwn.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME);
		MA_CurrencyDrpDwn.sendKeys("INR");
		Thread.sleep(1999);
		MA_CurrencyDrpDwn.sendKeys(Keys.TAB);

		Thread.sleep(1000);
		
		click(filterCollapseBtn);
		Thread.sleep(1500);
		MA_LoadBtn.click();
		Thread.sleep(8000);
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();

		String docdate = df.format(date);

		int count = MA_DebitSideRow1list.size();
		ArrayList<String> Debit = new ArrayList<String>();

		for (int i = 0; i < count; i++) {
			String data = MA_DebitSideRow1list.get(i).getText();

			if (data.isEmpty() == false) {
				Debit.add(data);
			}

		}

		String actDebitSideList = Debit.toString();
		String expDebitSideList = "[1, NDT55:1, " + dateF9() + ", " + dateF9()
				+ ", ₹, 100.00, 100.00, 0.00, 0.00, 0.00, 0.00, 0.00]";

		System.err.println(" Actual Debit List  : " + actDebitSideList);
		System.err.println(" Exp    Debit List  : " + expDebitSideList);

		int count1 = MA_CreditSideRow1list.size();
		ArrayList<String> Credit = new ArrayList<String>();

		for (int i = 0; i < count1; i++) {
			String data = MA_CreditSideRow1list.get(i).getText();

			if (data.isEmpty() == false) {
				Credit.add(data);
			}
		}

		String actCreditSideList = Credit.toString();
		String expCreditSideList = "[1, NDT57:1, " + dateF9() + ", " + dateF9()
				+ ", ₹, 100.00, 100.00, 0.00, 0.00, 0.00, 0.00, 0.00]";

		System.err.println(" Actual Credit List  : " + actCreditSideList);
		System.err.println(" Exp    Credit List  : " + expCreditSideList);

		Thread.sleep(2000);

		click(debitRow1Chkbox);

		Thread.sleep(2000);

		click(CreditRow1Chkbox);

		Thread.sleep(2000);

		String actdebitTotal = debitTotal.getText();
		String expdebitTotal = "100.00";

		System.err.println(" debitTotal : " + actdebitTotal + " Value  Exp: " + expdebitTotal);

		String actcreditTotal = creditTotal.getText();
		String expcreditTotal = "100.00";

		System.err.println(" creditTotal : " + actcreditTotal + " Value  Exp: " + expcreditTotal);

		Thread.sleep(2000);

		click(MA_OkBtn);

		String expMessage = "Record Saved Succesfully";
		String actMessage = checkValidationMessage(expMessage);

		System.err.println(" Message ACT: " + actMessage);
		System.err.println(" Message EXP: " + expMessage);

		if (actCreditSideList.equalsIgnoreCase(expCreditSideList) && actDebitSideList.equalsIgnoreCase(expDebitSideList)
				&& actdebitTotal.equalsIgnoreCase(expdebitTotal) && actcreditTotal.equalsIgnoreCase(expcreditTotal)
				&& actMessage.equalsIgnoreCase(expMessage)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkSavedVoucherInrecepictsVATAfterManulAdjustments() throws InterruptedException {

		click(financialsMenu);

		click(financialsTransactionMenu);

		click(cashAndBankMenu);

		click(recepitsVATVoucher);

		Thread.sleep(2000);

		voucherHomePageVoucherSelect("1");

		Thread.sleep(4500);

		String actRow1 = listOfElements(entryPageRow1List);
		String expRow1 = "[1, Customer A, Std Rate, 100.00, NDT55:1 : " + getCurrentdateDayFormat() + ", 4.76]";

		System.err.println("  ACT Row1 List: " + actRow1);
		System.err.println("  EXP Row1 List: " + expRow1);

		if (actRow1.equalsIgnoreCase(expRow1)) {
			return true;
		} else {

			return false;
		}

	}

	@FindBy(xpath = "//*[@id='RITTable__0']")
	private static WebElement reportAccountTxt;

	@FindBy(xpath = "//*[@id='tblHeaderReportRender']/th")
	private static List<WebElement> reportHeaderList;

	public boolean checkAuditTrailTransactionsreport() throws InterruptedException {

		Thread.sleep(2000);

		click(focusMainSearch);
		focusMainSearch.sendKeys("Audit trail transactions");

		Thread.sleep(2000);

		focusMainSearch.sendKeys(Keys.ENTER);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_DateOptionDropdown));
		sl_DateOptionDropdown.click();
		Select s = new Select(sl_DateOptionDropdown);
		s.selectByValue("1");

		click(reportAccountTxt);
		reportAccountTxt.sendKeys("Receipts VAT");
		Thread.sleep(2000);

		reportAccountTxt.sendKeys(Keys.TAB);

		click(sl_OkBtn);

		Thread.sleep(2000);

		Thread.sleep(2000);
		boolean novalidationConfirmationMessage = validationConfirmationMessage.getText().isEmpty();

		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";

		System.out.println("validationConfirmationMessage ACT : " + actvalidationConfirmationMessage);
		System.out.println("validationConfirmationMessage EXP: " + expvalidationConfirmationMessage);

		waitForElement(sl_1stRow1stCol);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_1stRow1stCol));
		String actreportHeaderList = listOfElements(reportHeaderList);
		String expreportHeaderList = "[#, Version, Voucher no, Created date, Created time, Created by, Modified date, Modified Time, Modified user, Action, Source]";

		System.err.println(" act reportHeaderList: " + actreportHeaderList);
		System.err.println(" exp reportHeaderList: " + expreportHeaderList);

		String actreport1stRowList = listOfElements(report1stRowList);
		String expreport1stRowList = "[1, Receipts VAT [NDT57]]";

		System.err.println(" act report1stRowList: " + actreport1stRowList);
		System.err.println(" exp report1stRowList: " + expreport1stRowList);

		Thread.sleep(2000);

		int count = report2ndRowList.size();

		ArrayList<String> list2 = new ArrayList<>();

		for (int i = 2; i < count; i++) {
			String data = report2ndRowList.get(i).getText();

			if (i == 4) {
				data = "TimeFiled";
			}

			if (i == 7) {
				data = "TimeFiled";
			}

			if (data.isEmpty() == false) {
				list2.add(data);
			}

		}

		String actreport2ndRowList = list2.toString();

		String expreport2ndRowList = "[1, " + currentDate() + ", TimeFiled, UserAllOptions, " + currentDate()
				+ ", TimeFiled, UserAllOptions, Modified, API]";

		System.err.println(" act report2ndRowList: " + actreport2ndRowList);
		System.err.println(" exp report2ndRowList: " + expreport2ndRowList);

		int count1 = report3rdRowList.size();

		ArrayList<String> list3 = new ArrayList<>();

		for (int j = 2; j < count1; j++) {
			String data1 = report3rdRowList.get(j).getText();

			if (j == 4) {
				data1 = "TimeFiled";
			}

			if (j == 7) {
				data1 = "TimeFiled";
			}

			if (data1.isEmpty() == false) {
				list3.add(data1);
			}

		}

		String actreport3rdRowList = list3.toString();

		String expreport3rdRowList = "[2, " + currentDate() + ", TimeFiled, UserAllOptions, " + currentDate()
				+ ", TimeFiled, UserAllOptions, Modified, API]";

		System.err.println(" act report3rdRowList: " + actreport3rdRowList);
		System.err.println(" exp report3rdRowList: " + expreport3rdRowList);

		if (actreport1stRowList.equalsIgnoreCase(expreport1stRowList)
				&& actreport2ndRowList.equalsIgnoreCase(expreport2ndRowList)
				&& actreport3rdRowList.equalsIgnoreCase(expreport3rdRowList)) {
			return true;

		}

		else {

			return false;
		}

	}

	@FindBy(xpath = "//*[@title='Show modifications']")
	private static WebElement showModificationsbtnReport;

	@FindBy(xpath = "//*[@id='dvReportDetails']//tbody/tr/td[1]")
	private static List<WebElement> reportVouNumberList;

	@FindBy(xpath = "//*[@id='tBodyAuditLogDetails']/tr")
	private static List<WebElement> reportLogList;

	// @FindBy(xpath="//button[text()='Close']")

	@FindBy(xpath = "//*[@value='Close']")
	private static WebElement reportCloseBtn;

	public void reportPageSelect(String doc) {
		int count = reportVouNumberList.size();

		for (int i = 0; i < count; i++) {
			String data = reportVouNumberList.get(i).getText();

			System.err.println(data);
			if (data.equalsIgnoreCase(doc)) {
				reportVouNumberList.get(i).click();
			}

		}
	}

	public boolean checkAuditTrailReportShowModification() throws InterruptedException 
	{

		Thread.sleep(2000);
		reportPageSelect("2");

		Thread.sleep(2000);
		click(showModificationsbtnReport);

		Thread.sleep(2000);

		String actList = listOfElements(reportLogList);
		String expList = "[Header, Narration, Cheque No, Row 1 modified, Reference NDT55:1 : "
				+ getCurrentdateDayFormat() + " New Reference]";

		System.err.println(" Act List: " + actList);
		System.err.println(" Exp List: " + expList);

		Thread.sleep(2000);
		click(reportCloseBtn);

		Thread.sleep(2000);

		Thread.sleep(2000);
		reportPageSelect("3");

		Thread.sleep(2000);
		reportPageSelect("3");

		Thread.sleep(2000);
		click(showModificationsbtnReport);

		Thread.sleep(2000);

		String actList1 = listOfElements(reportLogList);
		String expList1 = "[Row 1 modified, Amount 100.00 10.00]";

		System.err.println(" Act List Row3: " + actList1);
		System.err.println(" Exp List Row3: " + expList1);

		Thread.sleep(2000);

		ClickUsingJs(reportCloseBtn);

		Thread.sleep(2000);

		browserRefresh();

		if (actList.equalsIgnoreCase(expList) && actList1.equalsIgnoreCase(expList1)) {
			System.err.println(" Audit Log report Displayed AS Expected");
			return true;
		} 
		else {

			System.err.println(" Audit Log report Displayed AS  NOTExpected******************************************");
			return false;
		}

	}

	public boolean checkSavingVoucherInSalesInvoiceVATWIthMultipleAmount()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException 
	{

		Thread.sleep(2000);

		// re_LunchBrowser();

	//	Thread.sleep(2000);

	//	Thread.sleep(2000);

		eraseAllTransactions();

		Thread.sleep(4000);

	/*	logout();

		Thread.sleep(2567);

		checkLoginToManualAdjsuments();*/

		//waitOn(homeMenu);
		
		
		click(financialsMenu);

		click(financialsTransactionMenu);

		click(financialTransactionSalesMenu);

		click(salesInvoiceVATVoucher);

		Thread.sleep(8000);
		click(newBtn);
		Thread.sleep(4000);

		boolean actVoucherSaving1 = CheckSavingSalesInvoiceVAT("Customer A", "DUBAI", "HYDERABAD", "10", "10");
		boolean actVoucherSaving2 = CheckSavingSalesInvoiceVAT("Customer A", "DUBAI", "HYDERABAD", "10", "10");
		boolean actVoucherSaving3 = CheckSavingSalesInvoiceVAT("Customer A", "DUBAI", "HYDERABAD", "10", "20");
		boolean actVoucherSaving4 = CheckSavingSalesInvoiceVAT("Customer A", "DUBAI", "HYDERABAD", "10", "30");
		boolean actVoucherSaving5 = CheckSavingSalesInvoiceVAT("Customer A", "DUBAI", "HYDERABAD", "10", "40");

		boolean expVoucherSaving1 = true;
		boolean expVoucherSaving2 = true;
		boolean expVoucherSaving3 = true;
		boolean expVoucherSaving4 = true;
		boolean expVoucherSaving5 = true;

		System.err.println(" Saving Voucher 1:" + actVoucherSaving1 + " Value Exp: " + expVoucherSaving1);
		System.err.println(" Saving Voucher 2:" + actVoucherSaving2 + " Value Exp: " + expVoucherSaving2);
		System.err.println(" Saving Voucher 3:" + actVoucherSaving3 + " Value Exp: " + expVoucherSaving3);
		System.err.println(" Saving Voucher 4:" + actVoucherSaving4 + " Value Exp: " + expVoucherSaving4);
		System.err.println(" Saving Voucher 5:" + actVoucherSaving5 + " Value Exp: " + expVoucherSaving5);

		Thread.sleep(2999);

		if (actVoucherSaving1 == expVoucherSaving1 && actVoucherSaving2 == expVoucherSaving2
				&& actVoucherSaving3 == expVoucherSaving3 && actVoucherSaving4 == expVoucherSaving4
				&& actVoucherSaving5 == expVoucherSaving5) {
			return true;
		} else {

			return false;
		}
	}

	public boolean checkSavingReceiptsVATWithQty1500()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {

		boolean act = checkSavingVoucherInRecepitsVAT("Dubai", "Customer A", "1500");
		boolean exp = true;

		System.err.println(" Saving Voucher 1:" + act + " Value Exp: " + exp);

		if (act == exp) {
			return true;
		} else {

			return false;
		}
	}

	public boolean checkManualAdjustmentWithMultipleDebitAndSingelcreditWithAR()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {

		Thread.sleep(2000);

	/*	logout();

		Thread.sleep(2000);

		checkLoginToSelectedCompany("ManualAdjustment", "su", "su");

		Thread.sleep(2000);
*/
		Thread.sleep(2000);

		
		click(homeMenu);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(utilitesMenu));
		utilitesMenu.click();

		Thread.sleep(1999);
		getAction().moveToElement(ManualAdjustemntMenu).build().perform();

		Thread.sleep(1999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(ManualAdjustemntMenu));
		ManualAdjustemntMenu.click();

		Thread.sleep(1999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_ARAPDrpdwn));

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_ARAPDrpdwn));
		Select ARAP = new Select(MA_ARAPDrpdwn);
		ARAP.selectByValue("0");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_AccountDrpdwn));
		MA_AccountDrpdwn.sendKeys("Customer A");
		Thread.sleep(1999);
		MA_AccountDrpdwn.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_DepDrpDwn));
		MA_DepDrpDwn.sendKeys("Dubai");
		Thread.sleep(1999);
		MA_DepDrpDwn.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_CurrencyDrpDwn));
		MA_CurrencyDrpDwn.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME);
		MA_CurrencyDrpDwn.sendKeys("INR");
		Thread.sleep(1999);
		MA_CurrencyDrpDwn.sendKeys(Keys.TAB);

		Thread.sleep(2500);

		
		click(filterCollapseBtn);
		Thread.sleep(1500);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_LoadBtn));
		ClickUsingJs(MA_LoadBtn);

		Thread.sleep(4500);

		int count = MA_DebitSideRow1list.size();
		ArrayList<String> Debit = new ArrayList<String>();

		for (int i = 0; i < count; i++) {
			String data = MA_DebitSideRow1list.get(i).getText();

			if (data.isEmpty() == false) {
				Debit.add(data);
			}

		}

		String actDebitSideList = Debit.toString();
		String expDebitSideList = "[1, NDT55:1, " + dateF9() + ", " + dateF9()
				+ ", ₹, 100.00, 100.00, 0.00, 0.00, 0.00, 0.00, 0.00]";

		System.err.println(" Actual Debit List  : " + actDebitSideList);
		System.err.println(" Exp    Debit List  : " + expDebitSideList);

		String actDebitSideRow2List = listOfElements(MA_DebitSideRow2list);
		String expDebitSideRow2List = "[2, NDT55:2, " + dateF9() + ", " + dateF9()
				+ ", ₹, 100.00, 100.00, 0.00, 0.00, 0.00, 0.00, 0.00]";

		System.err.println(" Actual Debit List  : " + actDebitSideRow2List);
		System.err.println(" Exp    Debit List  : " + expDebitSideRow2List);

		String actDebitSideRow3List = listOfElements(MA_DebitSideRow3list);
		String expDebitSideRow3List = "[3, NDT55:3, " + dateF9() + ", " + dateF9()
				+ ", ₹, 200.00, 200.00, 0.00, 0.00, 0.00, 0.00, 0.00]";

		System.err.println(" Actual Debit List  : " + actDebitSideRow3List);
		System.err.println(" Exp    Debit List  : " + expDebitSideRow3List);

		String actDebitSideRow4List = listOfElements(MA_DebitSideRow4list);
		String expDebitSideRow4List = "[4, NDT55:4, " + dateF9() + ", " + dateF9()
				+ ", ₹, 300.00, 300.00, 0.00, 0.00, 0.00, 0.00, 0.00]";

		System.err.println(" Actual Debit List  : " + actDebitSideRow4List);
		System.err.println(" Exp    Debit List  : " + expDebitSideRow4List);

		String actDebitSideRow5List = listOfElements(MA_DebitSideRow5list);
		String expDebitSideRow5List = "[5, NDT55:5, " + dateF9() + ", " + dateF9()
				+ ", ₹, 400.00, 400.00, 0.00, 0.00, 0.00, 0.00, 0.00]";

		System.err.println(" Actual Debit List  : " + actDebitSideRow5List);
		System.err.println(" Exp    Debit List  : " + expDebitSideRow5List);

		int count1 = MA_CreditSideRow1list.size();
		ArrayList<String> Credit = new ArrayList<String>();

		for (int i = 0; i < count1; i++) {
			String data = MA_CreditSideRow1list.get(i).getText();

			if (data.isEmpty() == false) {
				Credit.add(data);
			}

		}

		String actCreditSideList = Credit.toString();
		String expCreditSideList = "[1, NDT57:1, " + dateF9() + ", " + dateF9()
				+ ", ₹, 1500.00, 1500.00, 0.00, 0.00, 0.00, 0.00, 0.00]";

		System.err.println(" Actual Credit List  : " + actCreditSideList);
		System.err.println(" Exp    Credit List  : " + expCreditSideList);

		Thread.sleep(2000);

		List<WebElement> a = debitRowChkboxList;

		for (WebElement string : a) {
			string.click();
		}

		Thread.sleep(2000);

		click(CreditRow1Chkbox);

		Thread.sleep(2000);

		String actdebitTotal = debitTotal.getText();
		String expdebitTotal = "1100.00";

		System.err.println(" debitTotal : " + actdebitTotal + " Value  Exp: " + expdebitTotal);

		Thread.sleep(2000);

		String actcreditTotal = creditTotal.getText();
		String expcreditTotal = "1500.00";

		System.err.println(" creditTotal : " + actcreditTotal + " Value  Exp: " + expcreditTotal);

		Thread.sleep(2000);

		click(MA_OkBtn);

		String expMessage = "Record Saved Succesfully";
		String actMessage = checkValidationMessage(expMessage);

		System.err.println(" Message ACT: " + actMessage);
		System.err.println(" Message EXP: " + expMessage);

		if (actCreditSideList.equalsIgnoreCase(expCreditSideList) && actDebitSideList.equalsIgnoreCase(expDebitSideList)
				&& actdebitTotal.equalsIgnoreCase(expdebitTotal) && actcreditTotal.equalsIgnoreCase(expcreditTotal)
				&& actMessage.equalsIgnoreCase(expMessage)) {
			return true;
		} else {
			return false;
		}

	}

	public boolean checkBalanceAmountOnCreditSideInManulaAdjustmentScreen()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		Thread.sleep(2000);

		
		click(homeMenu);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(utilitesMenu));
		utilitesMenu.click();

		Thread.sleep(1999);
		getAction().moveToElement(ManualAdjustemntMenu).build().perform();

		Thread.sleep(1999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(ManualAdjustemntMenu));
		ManualAdjustemntMenu.click();

		Thread.sleep(1999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_ARAPDrpdwn));

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_ARAPDrpdwn));
		Select ARAP = new Select(MA_ARAPDrpdwn);
		ARAP.selectByValue("0");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_AccountDrpdwn));
		MA_AccountDrpdwn.sendKeys("Customer A");
		Thread.sleep(1999);
		MA_AccountDrpdwn.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_DepDrpDwn));
		MA_DepDrpDwn.sendKeys("Dubai");
		Thread.sleep(1999);
		MA_DepDrpDwn.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_CurrencyDrpDwn));
		MA_CurrencyDrpDwn.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME);
		MA_CurrencyDrpDwn.sendKeys("INR");
		Thread.sleep(1999);
		MA_CurrencyDrpDwn.sendKeys(Keys.TAB);

		Thread.sleep(1000);
		
		click(filterCollapseBtn);
		Thread.sleep(1500);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_LoadBtn));
		ClickUsingJs(MA_LoadBtn);

		Thread.sleep(4500);

		String actCreditSideList = listOfElements(MA_CreditSideRow1list);
		String expCreditSideList = "[1, NDT57:1, " + dateF9() + ", " + dateF9()
				+ ", ₹, 400.00, 400.00, 0.00, 0.00, 0.00, 0.00, 0.00]";

		System.err.println(" Actual Credit List  : " + actCreditSideList);
		System.err.println(" Exp    Credit List  : " + expCreditSideList);

		Thread.sleep(2000);

		click(CreditRow1Chkbox);

		Thread.sleep(2000);

		String expMessage = "Please select debit type of voucher for adjustment";
		String actMessage = checkValidationMessage(expMessage);

		String actdebitTotal = debitTotal.getText();
		String expdebitTotal = "0.00";

		System.err.println(" debitTotal : " + actdebitTotal + " Value  Exp: " + expdebitTotal);

		Thread.sleep(2000);

		String actcreditTotal = creditTotal.getText();
		String expcreditTotal = "0.00";

		System.err.println(" creditTotal : " + actcreditTotal + " Value  Exp: " + expcreditTotal);

		if (actdebitTotal.equalsIgnoreCase(expdebitTotal) && actcreditTotal.equalsIgnoreCase(actcreditTotal)
				&& actCreditSideList.equalsIgnoreCase(expCreditSideList) && actMessage.equalsIgnoreCase(expMessage)) {
			return true;
		} else {

			return false;
		}

	}

	public boolean checkAuditTrailTransactionsreportWithMultipleDebitAndSingleCredit() throws InterruptedException {

		Thread.sleep(2000);

		click(focusMainSearch);

		focusMainSearch.sendKeys("Audit trail transactions");

		Thread.sleep(2000);

		focusMainSearch.sendKeys(Keys.ENTER);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_DateOptionDropdown));
		sl_DateOptionDropdown.click();
		Select s = new Select(sl_DateOptionDropdown);
		s.selectByValue("1");

		click(reportAccountTxt);
		reportAccountTxt.sendKeys("Receipts VAT");
		Thread.sleep(2000);

		reportAccountTxt.sendKeys(Keys.TAB);

		click(sl_OkBtn);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_1stRow1stCol));

		String actreportHeaderList = listOfElements(reportHeaderList);
		String expreportHeaderList = "[#, Version, Voucher no, Created date, Created time, Created by, Modified date, Modified Time, Modified user, Action, Source]";

		System.err.println(" act reportHeaderList: " + actreportHeaderList);
		System.err.println(" exp reportHeaderList: " + expreportHeaderList);

		String actreport1stRowList = listOfElements(report1stRowList);
		String expreport1stRowList = "[1, Receipts VAT [NDT57]]";

		System.err.println(" act report1stRowList: " + actreport1stRowList);
		System.err.println(" exp report1stRowList: " + expreport1stRowList);

		Thread.sleep(2000);

		int count = report2ndRowList.size();

		ArrayList<String> list2 = new ArrayList<>();

		for (int i = 2; i < count; i++) {
			String data = report2ndRowList.get(i).getText();

			if (i == 4) {
				data = "TimeFiled";
			}

			if (i == 7) {
				data = "TimeFiled";
			}

			if (data.isEmpty() == false) {
				list2.add(data);
			}

		}

		String actreport2ndRowList = list2.toString();

		String expreport2ndRowList = "[1, " + currentDate() + ", TimeFiled, UserAllOptions, " + currentDate()
				+ ", TimeFiled, UserAllOptions, Modified, API]";

		System.err.println(" act report2ndRowList: " + actreport2ndRowList);
		System.err.println(" exp report2ndRowList: " + expreport2ndRowList);

		int count1 = report3rdRowList.size();

		ArrayList<String> list3 = new ArrayList<>();

		for (int j = 2; j < count1; j++) {
			String data1 = report3rdRowList.get(j).getText();

			if (j == 4) {
				data1 = "TimeFiled";
			}

			if (j == 7) {
				data1 = "TimeFiled";
			}

			if (data1.isEmpty() == false) {
				list3.add(data1);
			}

		}

		String actreport3rdRowList = list3.toString();

		String expreport3rdRowList = "[1, " + currentDate() + ", TimeFiled, UserAllOptions, " + currentDate()
				+ ", TimeFiled, UserAllOptions, Modified, API]";

		System.err.println(" act report3rdRowList: " + actreport3rdRowList);
		System.err.println(" exp report3rdRowList: " + expreport3rdRowList);

		int count2 = report4thRowList.size();

		ArrayList<String> list4 = new ArrayList<>();

		for (int k = 2; k < count2; k++) {
			String data2 = report4thRowList.get(k).getText();

			if (k == 4) {
				data2 = "TimeFiled";
			}

			if (k == 7) {
				data2 = "TimeFiled";
			}

			if (data2.isEmpty() == false) {
				list4.add(data2);
			}

		}

		String actreport4thRowList = list4.toString();

		String expreport4thRowList = "[2, " + currentDate() + ", TimeFiled, UserAllOptions, " + currentDate()
				+ ", TimeFiled, UserAllOptions, Modified, API]";

		System.err.println(" act report4rdRowList: " + actreport4thRowList);
		System.err.println(" exp report4rdRowList: " + expreport4thRowList);

		if (actreport1stRowList.equalsIgnoreCase(expreport1stRowList)
				&& actreport2ndRowList.equalsIgnoreCase(expreport2ndRowList)
				&& actreport3rdRowList.equalsIgnoreCase(expreport3rdRowList)) {
			return true;

		} else {

			return false;
		}

	}

	public boolean checkAuditTrailReportShowModificationWithMultipleDebitAndSingleCredit() throws InterruptedException {

		Thread.sleep(2000);
		reportPageSelect("4");

		Thread.sleep(2000);
		click(showModificationsbtnReport);

		Thread.sleep(2000);

		String actList = listOfElements(reportLogList);
		String expList = "[Header, Narration, Cheque No, Row 1 modified, Reference NDT55:2 : "
				+ getCurrentdateDayFormat() + ";NDT55:3 : " + getCurrentdateDayFormat() + ";NDT55:4 : "
				+ getCurrentdateDayFormat() + ";NDT55:5 : " + getCurrentdateDayFormat() + ";NDT55:6 : "
				+ getCurrentdateDayFormat() + ";New Reference New Reference]";

		System.err.println(" Act List: " + actList);
		System.err.println(" Exp List: " + expList);

		Thread.sleep(2000);
		click(reportCloseBtn);

		if (actList.equalsIgnoreCase(expList)) {
			System.err.println(" Audit Log report Displayed AS Expected");
			return true;
		} else {

			System.err.println(" Audit Log report Displayed AS  NOTExpected******************************************");
			return false;
		}

	}

	public boolean checkEnableNarrationDrpDownAsRemarksInSalesInvoiceVAT()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException {
		Thread.sleep(3000);

		click(financialsMenu);

		click(financialsTransactionMenu);

		click(financialTransactionSalesMenu);

		click(salesInvoiceVATVoucher);

		Thread.sleep(1500);

		click(homePageSettingbtn);

		Thread.sleep(2500);

		click(miscellaneousTabInDC);

		Thread.sleep(2500);

		click(mis_ARAPExpandBtn);

		Thread.sleep(2500);

		Select s1 = new Select(misNarraitionDrpdwn);
		s1.selectByVisibleText("Remarks");

		Thread.sleep(2000);

		ClickUsingJs(settingUpdateIcon);

		String expMessage = "Data saved successfully";
		String actMessage = checkValidationMessage(expMessage);

		Thread.sleep(2000);

		//Thread.sleep(2000);

		//logout();

		//Thread.sleep(2000);
		//checkLogin();
		//checkLoginToManualAdjsuments();

		//checkLoginToSelectedCompany("ManualAdjustment", "useralloptions", "12345");
		//checkLoginToSelectedCompany("ManualAdjustment", "su", "su");

		Thread.sleep(4000);

		if (actMessage.equalsIgnoreCase(expMessage)) {
			return true;
		} else {

			return false;
		}

	}

	public boolean checkSavingSalesInvoiceVATWith2000()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException {

		Thread.sleep(2000);

		// re_LunchBrowser();

		

		eraseAllTransactions();
		Thread.sleep(4000);

	/*	logout();

		Thread.sleep(2567);

		checkLoginToManualAdjsuments();
*/
		waitOn(homeMenu);

		Thread.sleep(2000);
		
		click(financialsMenu);

		click(financialsTransactionMenu);

		click(financialTransactionSalesMenu);

		click(salesInvoiceVATVoucher);

		Thread.sleep(4000);

		click(newBtn);

		Thread.sleep(2000);

		boolean actVoucherSaving1 = CheckSavingSalesInvoiceVATWithDATE(2, "Customer A", "DUBAI", "HYDERABAD", "1",
				"2000");

		boolean expVoucherSaving1 = true;

		System.err.println(" Saving Voucher 1:" + actVoucherSaving1 + " Value Exp: " + expVoucherSaving1);

		Thread.sleep(2000);

		Thread.sleep(3000);

		click(financialsMenu);

		click(financialsTransactionMenu);

		click(financialTransactionSalesMenu);

		click(salesInvoiceVATVoucher);

		Thread.sleep(4000);

		voucherHomePageVoucherSelect("1");

		Thread.sleep(4000);

		click(select1stRow_1stColumn);

		Thread.sleep(1500);

		ClickUsingJs(select1stRow_22ndColumn);

		Thread.sleep(1500);

		click(enterRemarksTxt);
		enterRemarksTxt.sendKeys("SI001");
		Thread.sleep(1500);
		enterRemarksTxt.sendKeys(Keys.TAB);

		Thread.sleep(1500);

		String docno = documentNumberTxt.getAttribute("value");

		Thread.sleep(1999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(voucherSaveBtn));
		voucherSaveBtn.click();

		Thread.sleep(1999);
		billwisePick();

		String exp = "This Transaction will make the Stock Negative";
		String act = checkValidationMessage(exp);

		Thread.sleep(250);

		boolean actMessage = checkVoucherSavingMessage2(docno);
		boolean expMessage = true;

		/*
		 * boolean actMessage = checkBackgroundSavingNegativeMessage(docno); boolean
		 * expMessage = true;
		 */
		System.out.println("SavingMessage  :  " + actMessage + " Value Expected : " + expMessage);

		if (actVoucherSaving1 == expVoucherSaving1 && actMessage == expMessage) {
			return true;
		} else if (act.equalsIgnoreCase(exp)) {
			System.err.println(" Negative Message Displayed");
			return true;
		} else {

			return false;
		}
	}

	public static void re_LunchBrowser()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException

	{

		Set<Cookie> cookies = null;
		try {
			cookies = driver.manage().getCookies();
		} catch (Throwable e) {
			System.err.println("Error While getting Cookies: " + e.getMessage());
		}

		Thread.sleep(1999);

		getDriver().close();

		System.err.println("--------------------Browser Closed");

		Thread.sleep(1999);

		browserOpen();

		System.err.println("--------------------Browser OPened");

		Thread.sleep(1999);

		try {
			for (Cookie cookie : cookies) {
				driver.manage().addCookie(cookie);
			}
		} catch (Throwable e) {
			System.err.println("Error While setting Cookies: " + e.getMessage());
		}

		getDriver().navigate().refresh();

	}

	public boolean checkSavingRecepitsVATWithMultipleSaving()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {

		Thread.sleep(2000);

		boolean actVoucherSaving1 = checkSavingVoucherInRecepitsVATWithdate(1, "Dubai", "Customer A", "100");
		boolean actVoucherSaving2 = checkSavingVoucherInRecepitsVATWithdate(2, "Dubai", "Customer A", "200");
		boolean actVoucherSaving3 = checkSavingVoucherInRecepitsVATWithdate(3, "Dubai", "Customer A", "300");
		boolean actVoucherSaving4 = checkSavingVoucherInRecepitsVATWithdate(4, "Dubai", "Customer A", "400");
		boolean actVoucherSaving5 = checkSavingVoucherInRecepitsVATWithdate(5, "Dubai", "Customer A", "500");

		boolean expVoucherSaving1 = true;
		boolean expVoucherSaving2 = true;
		boolean expVoucherSaving3 = true;
		boolean expVoucherSaving4 = true;
		boolean expVoucherSaving5 = true;

		System.err.println(" Saving Voucher 1:" + actVoucherSaving1 + " Value Exp: " + expVoucherSaving1);
		System.err.println(" Saving Voucher 2:" + actVoucherSaving2 + " Value Exp: " + expVoucherSaving2);
		System.err.println(" Saving Voucher 3:" + actVoucherSaving3 + " Value Exp: " + expVoucherSaving3);
		System.err.println(" Saving Voucher 4:" + actVoucherSaving4 + " Value Exp: " + expVoucherSaving4);
		System.err.println(" Saving Voucher 5:" + actVoucherSaving5 + " Value Exp: " + expVoucherSaving5);

		if (actVoucherSaving1 == expVoucherSaving1 && actVoucherSaving2 == expVoucherSaving2
				&& actVoucherSaving3 == expVoucherSaving3 && actVoucherSaving4 == expVoucherSaving4
				&& actVoucherSaving5 == expVoucherSaving5) {
			return true;
		} else {

			return false;
		}
	}

	@FindBy(xpath = "//*[@id='dtLFFromDate_input_container']/div[1]/i[2]")
	private static WebElement filter_Debit_StartDt;

	@FindBy(xpath = "//*[@id='dtLFToDate_input_container']/div[1]/i[2]")
	private static WebElement filter_Debit_EndDt;

	@FindBy(xpath = "//*[@id='txtLFBillNo']")
	private static WebElement filter_Debit_BillNo;

	@FindBy(xpath = "//*[@id='optLFVoucherType']	")
	private static WebElement filter_Debit_VoucherType;

	@FindBy(xpath = "//*[@id='txtLFVNoFrom']")
	private static WebElement filter_Debit_VouFrom;

	@FindBy(xpath = "//*[@id='txtLFVNoTo']")
	private static WebElement filter_Debit_VouTo;

	@FindBy(xpath = "//*[@id='dtLFFromDate_day_today']/td/span[text()='Today']")
	private static WebElement deb_StartDt_todayDate;

	@FindBy(xpath = "//*[@id='dtLFToDate_day_today']/td/span[text()='Today']")
	private static WebElement deb_EndDt_todayDate;

	@FindBy(xpath = "//*[@id='dtRFFromDate_day_today']/td/span[text()='Today']")
	private static WebElement cred_StartDt_todayDate;

	@FindBy(xpath = "//*[@id='dtRFToDate_day_today']/td/span[text()='Today']")
	private static WebElement cred_EndDt_todayDate;

	@FindBy(xpath = "//*[@id='dtRFFromDate_input_container']/div[1]/i[2]")
	private static WebElement filter_credit_StartDt;

	@FindBy(xpath = "//*[@id='dtRFToDate_input_container']/div[1]/i[2]")
	private static WebElement filter_credit_ENDDate;

	@FindBy(xpath = "//*[@id='txtRFBillNo']")
	private static WebElement filter_credit_Billno;

	@FindBy(xpath = "//*[@id='optRFVoucherType']")
	private static WebElement filter_credit_VouType;

	@FindBy(xpath = "//*[@id='txtRFVNoFrom']")
	private static WebElement filter_credit_VouFrom;

	@FindBy(xpath = "//*[@id='txtRFVNoTo']")
	private static WebElement filter_credit_VouTO;

	@FindBy(xpath = "//*[@id='dtLFToDate']")
	private static WebElement debitEndDtTxt;

	@FindBy(xpath = "//*[@id='dtRFToDate']")
	private static WebElement creditEndDtTxt;
	
	@FindBy(xpath="//*[@id='dtRFToDate_input_container']/div[1]/i[1]")
	public static WebElement creditEndClose;

	public boolean checkFIlterOptionInManualAdjustmentScreen()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {

		Thread.sleep(2000);

		click(homeMenu);

		click(utilitesMenu);

		Thread.sleep(1999);

		ClickUsingJs(ManualAdjustemntMenu);

		Thread.sleep(1999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_ARAPDrpdwn));
		Select ARAP = new Select(MA_ARAPDrpdwn);
		ARAP.selectByValue("0");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_AccountDrpdwn));
		MA_AccountDrpdwn.sendKeys("Customer A");
		Thread.sleep(1999);
		MA_AccountDrpdwn.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_DepDrpDwn));
		MA_DepDrpDwn.sendKeys("Dubai");
		Thread.sleep(1999);
		MA_DepDrpDwn.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_CurrencyDrpDwn));
		MA_CurrencyDrpDwn.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME);
		MA_CurrencyDrpDwn.sendKeys("INR");
		Thread.sleep(1999);
		MA_CurrencyDrpDwn.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		click(filter_Debit_StartDt);
		click(deb_StartDt_todayDate);
		Thread.sleep(2000);

		click(filter_Debit_EndDt);
		click(deb_EndDt_todayDate);
		Thread.sleep(2000);

		click(filter_credit_StartDt);
		click(cred_StartDt_todayDate);
		Thread.sleep(2000);

		click(filter_credit_ENDDate);
		click(cred_EndDt_todayDate);

		Thread.sleep(2000);

		click(filterCollapseBtn);
		Thread.sleep(1500);
		
		click(MA_LoadBtn);

		Thread.sleep(2000);

		String actCurrentDateDebit = listOfElements(debitRowChkboxList);
		String expCurrentDateDebit = "[]";

		System.err.println(" Actual Debit Table on Current Date : " + actCurrentDateDebit);
		System.err.println(" EXP    Debit Table on Current Date : " + expCurrentDateDebit);

		String actCurrentDatecredit = listOfElements(creditRowChkboxList);
		String expCurrentDatecredit = "[]";

		System.err.println(" Actual Credit Table on Current Date : " + actCurrentDatecredit);
		System.err.println(" EXP    Credit Table on Current Date : " + expCurrentDatecredit);

		Thread.sleep(2000);

		// Changing date on Debit Side

		click(filterExpandBtn);
		Thread.sleep(1500);
		click(debitEndDtTxt);
		Thread.sleep(2000);
		removetTxt(debitEndDtTxt);
		debitEndDtTxt.sendKeys(FilterCurrentDate(2));
		Thread.sleep(2000);
		debitEndDtTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		click(filterCollapseBtn);
		Thread.sleep(1500);
		
		click(MA_LoadBtn);

		Thread.sleep(2000);

		String actDebitSideListWith2days = listOfElements(MA_DebitSideRow1list);
		String expDebitSideListWith2days = "[1, NDT55:1, SI001, " + FilterdateF9(2) + ", " + FilterdateF9(2)
				+ ", ₹, 2000.00, 2000.00, 0.00, 0.00, 0.00, 0.00, 0.00]";

		System.err.println(" Actual Debit List  : " + actDebitSideListWith2days);
		System.err.println(" Exp    Debit List  : " + expDebitSideListWith2days);

		String actCurrentDatecredit2Date = listOfElements(creditRowChkboxList);
		String expCurrentDatecredit2Date = "[]";

		System.err.println(" Actual Credit Table on 2 Days : " + actCurrentDatecredit2Date);
		System.err.println(" EXP    Credit Table on 2 Days : " + expCurrentDatecredit2Date);

		Thread.sleep(2000);

		// Now Date Changing On Credit Side
		
		click(filterExpandBtn);
		Thread.sleep(2000);
		
		click(filter_credit_StartDt);
		click(cred_StartDt_todayDate);
		Thread.sleep(2000);

		click(creditEndDtTxt);
		Thread.sleep(2000);
	
		//removetTxt(creditEndDtTxt);
		click(creditEndClose);
	
		creditEndDtTxt.sendKeys(FilterCurrentDate(10));
		Thread.sleep(2000);
		creditEndDtTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		
		click(filterCollapseBtn);
		Thread.sleep(1500);
		
		click(MA_LoadBtn);

		Thread.sleep(2500);

		String actDebitSideListWith10days = listOfElements(MA_DebitSideRow1list);
		String expDebitSideListWith10days = "[1, NDT55:1, SI001, " + FilterdateF9(2) + ", " + FilterdateF9(2)
				+ ", ₹, 2000.00, 2000.00, 0.00, 0.00, 0.00, 0.00, 0.00]";

		System.err.println(" Actual Debit List  : " + actDebitSideListWith10days);
		System.err.println(" Exp    Debit List  : " + expDebitSideListWith10days);

		boolean actCurrentDatecredit10Date = ListComparisionWOOrder(creditDocNOList,
				"[NDT57:1, NDT57:2, NDT57:3, NDT57:4, NDT57:5]");
		boolean expCurrentDatecredit10Date = true;

		System.err.println(" Actual Credit Table on 10 Days : " + actCurrentDatecredit10Date);
		System.err.println(" EXP    Credit Table on 10 Days : " + expCurrentDatecredit10Date);

		if (actCurrentDateDebit.equalsIgnoreCase(expCurrentDateDebit)
				&& actDebitSideListWith2days.equalsIgnoreCase(expDebitSideListWith2days)
				&& actCurrentDatecredit.equalsIgnoreCase(expCurrentDatecredit)
				&& actCurrentDatecredit2Date.equalsIgnoreCase(actCurrentDatecredit2Date)
				&& actDebitSideListWith10days.equalsIgnoreCase(expDebitSideListWith10days)
				&& actCurrentDatecredit10Date == expCurrentDatecredit10Date) {
			return true;
		} else {
			return false;

		}

	}
	
	

	public boolean checkSavingSalesInvoiceVATWithCopyDOcument()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException 
	{

		 Thread.sleep(2000);
		/*  
		  String batCommand =
		  "cmd /c start C:\\Users\\Rakesh\\Desktop\\PronghornStop.lnk";
		  Thread.sleep(2000); Runtime.getRuntime().exec(batCommand);
		  
		  Thread.sleep(10000);
		  
		  System.err.println("Pronghorn stopped");
		
		Thread.sleep(2000);
		//logout();

		Thread.sleep(2000);

		 
		  Thread.sleep(2000);
		  
		  String batCommand2 =
		  "cmd /c start C:\\Users\\Rakesh\\Desktop\\IISRESET.lnk";
		  Thread.sleep(2000); Runtime.getRuntime().exec(batCommand2);
		  
		  Thread.sleep(15000);
		  
		  System.err.println("InetManagerRestart");
		  
		  Thread.sleep(6000);
		  
		  String batCommand1 =
		  "cmd /c start C:\\Users\\Rakesh\\Desktop\\PronghornStart.lnk";
		  Thread.sleep(2000); Runtime.getRuntime().exec(batCommand1);
		  
		  Thread.sleep(10000);
		  
		  System.err.println("Pronghorn Started");
		  
		  Thread.sleep(8965);
		  */
		getDriver().navigate().refresh();
		Thread.sleep(15000);
		
		
		NavigationTosalesInvoiceVATVoucher();

		Thread.sleep(2000);

		voucherHomePageVoucherSelect("1");

		Thread.sleep(4000);

		click(toggleBtn);
		Thread.sleep(1500);
		
		click(copytoClipboardBtn);

		Thread.sleep(2000);

		click(toggleBtn);

		click(pastefromClipboardBtn);

		Thread.sleep(2000);

		click(select1stRow_22ndColumn);

		Thread.sleep(1500);

		click(enterRemarksTxt);
		removetTxt(enterRemarksTxt);
		enterRemarksTxt.sendKeys("SI002");
		Thread.sleep(1500);
		enterRemarksTxt.sendKeys(Keys.TAB);

		Thread.sleep(1500);

		String docno = documentNumberTxt.getAttribute("value");

		Thread.sleep(1999);

		click(voucherSaveBtn);

		Thread.sleep(2500);

		billwisePick();

		checkValidationMessage("This Transaction will make the Stock Negative");

		Thread.sleep(250);

		boolean actMessage = checkVoucherSavingMessage2(docno);
		boolean expMessage = true;

		/*
		 * boolean actMessage = checkBackgroundSavingNegativeMessage(docno); boolean
		 * expMessage = true;
		 */

		System.out.println("SavingMessage  :  " + actMessage + " Value Expected : " + expMessage);

		if (actMessage == expMessage) {
			return true;
		} else {

			return false;
		}

	}

	@FindBy(xpath = "//*[@id='dtRFFromDate_input_container']/div[1]/i[1]")
	private static WebElement creditFromDateClearbtn;

	@FindBy(xpath = "//*[@id='dtRFToDate_input_container']/div[1]/i[1]")
	private static WebElement creditToDateClearbtn;

	public boolean checkFilterOptionInManualEntryWithVoucherNumberANDBillNumber() throws InterruptedException 
	{

		Thread.sleep(2000);

		click(homeMenu);

		click(utilitesMenu);

		Thread.sleep(1999);

		ClickUsingJs(ManualAdjustemntMenu);

		Thread.sleep(1999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_ARAPDrpdwn));
		Select ARAP = new Select(MA_ARAPDrpdwn);
		ARAP.selectByValue("0");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_AccountDrpdwn));
		MA_AccountDrpdwn.sendKeys("Customer A");
		Thread.sleep(1999);
		MA_AccountDrpdwn.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_DepDrpDwn));
		MA_DepDrpDwn.sendKeys("Dubai");
		Thread.sleep(1999);
		MA_DepDrpDwn.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_CurrencyDrpDwn));
		MA_CurrencyDrpDwn.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME);
		MA_CurrencyDrpDwn.sendKeys("INR");
		Thread.sleep(1999);
		MA_CurrencyDrpDwn.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		click(filter_Debit_StartDt);
		click(deb_StartDt_todayDate);
		Thread.sleep(2000);

		click(filter_Debit_EndDt);
		click(deb_EndDt_todayDate);
		Thread.sleep(2000);

		click(filter_credit_StartDt);
		click(cred_StartDt_todayDate);
		Thread.sleep(2000);

		click(filter_credit_ENDDate);
		click(cred_EndDt_todayDate);

		Thread.sleep(2000);

		click(debitEndDtTxt);
		Thread.sleep(2000);
		removetTxt(debitEndDtTxt);
		debitEndDtTxt.sendKeys(FilterCurrentDate(2));
		Thread.sleep(2000);
		debitEndDtTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		
		click(filterCollapseBtn);
		Thread.sleep(1500);
		

		click(MA_LoadBtn);

		Thread.sleep(2000);

		String actDebitSideListWith2days = listOfElements(MA_DebitSideRow1list);
		String expDebitSideListWith2days = "[1, NDT55:1, SI001, " + FilterdateF9(2) + ", " + FilterdateF9(2)
				+ ", ₹, 2000.00, 2000.00, 0.00, 0.00, 0.00, 0.00, 0.00]";

		System.err.println(" Actual Debit List  : " + actDebitSideListWith2days);
		System.err.println(" Exp    Debit List  : " + expDebitSideListWith2days);

		String actDebitSideListWith2daysRow2 = listOfElements(MA_DebitSideRow2list);
		String expDebitSideListWith2daysRow2 = "[2, NDT55:2, SI002, " + FilterdateF9(2) + ", " + FilterdateF9(2)
				+ ", ₹, 2000.00, 2000.00, 0.00, 0.00, 0.00, 0.00, 0.00]";

		System.err.println(" Actual Debit List  : " + actDebitSideListWith2daysRow2);
		System.err.println(" Exp    Debit List  : " + expDebitSideListWith2daysRow2);

		String actCurrentDatecredit2Date = listOfElements(creditRowChkboxList);
		String expCurrentDatecredit2Date = "[]";

		System.err.println(" Actual Credit Table on 2 Days : " + actCurrentDatecredit2Date);
		System.err.println(" EXP    Credit Table on 2 Days : " + expCurrentDatecredit2Date);

		Thread.sleep(2000);

		// Now Date Changing On Credit Side

		click(filterExpandBtn);
		
		Thread.sleep(2000);

		click(filter_Debit_BillNo);
		filter_Debit_BillNo.sendKeys("SI002");
		Thread.sleep(2000);
		filter_Debit_BillNo.sendKeys(Keys.TAB);

		
		click(filterCollapseBtn);
		Thread.sleep(1500);
		
		click(MA_LoadBtn);

		Thread.sleep(2000);

		String act = listOfElements(MA_DebitSideRow1list);
		String exp = "[1, NDT55:2, SI002, " + FilterdateF9(2) + ", " + FilterdateF9(2)
				+ ", ₹, 2000.00, 2000.00, 0.00, 0.00, 0.00, 0.00, 0.00]";

		System.err.println(" Actual Debit List  : " + act);
		System.err.println(" Exp    Debit List  : " + exp);

		Thread.sleep(2000);
		click(filterExpandBtn);
		Thread.sleep(2000);
		click(creditFromDateClearbtn);

		Thread.sleep(2000);

		click(creditToDateClearbtn);
		Thread.sleep(2000);

		filter_credit_VouType.sendKeys("Receipts VAT");
		Thread.sleep(2000);
		filter_credit_VouType.sendKeys(Keys.TAB);

		filter_credit_VouFrom.sendKeys("1");

		Thread.sleep(2000);
		filter_credit_VouFrom.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		filter_credit_VouTO.sendKeys("3");

		Thread.sleep(2000);
		filter_credit_VouTO.sendKeys(Keys.TAB);

		Thread.sleep(2500);
		
		click(filterCollapseBtn);
		Thread.sleep(1500);
		

		click(MA_LoadBtn);

		Thread.sleep(2500);

		boolean actDebitSideListWith10days = ListComparisionWOOrder(creditDocNOList, "[NDT57:1, NDT57:2, NDT57:3]");
		boolean expDebitSideListWith10days = true;

		System.err.println(" Actual Debit List  : " + actDebitSideListWith10days);
		System.err.println(" Exp    Debit List  : " + expDebitSideListWith10days);

		if (act.equalsIgnoreCase(exp) && actDebitSideListWith2days.equalsIgnoreCase(expDebitSideListWith2days)
				&& actDebitSideListWith2daysRow2.equalsIgnoreCase(expDebitSideListWith2daysRow2)
				&& actCurrentDatecredit2Date.equalsIgnoreCase(actCurrentDatecredit2Date)
				&& actDebitSideListWith10days == expDebitSideListWith10days) {
			return true;
		} else {
			return false;

		}

	}

	public boolean checkDeletingVoucherInsalesInvoiceVAT()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		NavigationTosalesInvoiceVATVoucher();

		Thread.sleep(2000);

		voucherHomePageVoucherSelect("2");

		Thread.sleep(2000);

		checkValidationMessage("Voucher loaded successfully");

		Thread.sleep(2000);

		click(new_DeleteBtn);

		Thread.sleep(2000);

		click(popUpOKBtn);

		String expMess = "Voucher deleted Successfully";
		String actMessage = checkValidationMessage(expMess);

		if (actMessage.equalsIgnoreCase(expMess)) {
			return true;
		} else {

			return false;
		}

	}

	public boolean checkManualAdjustmentWithMultipleCreditAndSingelDebitWithAR()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {

		Thread.sleep(2000);

		click(homeMenu);

		click(utilitesMenu);

		Thread.sleep(1999);
		ClickUsingJs(ManualAdjustemntMenu);

		Thread.sleep(1999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_ARAPDrpdwn));
		Select ARAP = new Select(MA_ARAPDrpdwn);
		ARAP.selectByValue("0");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_AccountDrpdwn));
		MA_AccountDrpdwn.sendKeys("Customer A");
		Thread.sleep(1999);
		MA_AccountDrpdwn.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_DepDrpDwn));
		MA_DepDrpDwn.sendKeys("Dubai");
		Thread.sleep(1999);
		MA_DepDrpDwn.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_CurrencyDrpDwn));
		MA_CurrencyDrpDwn.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME);
		MA_CurrencyDrpDwn.sendKeys("INR");
		Thread.sleep(1999);
		MA_CurrencyDrpDwn.sendKeys(Keys.TAB);

		Thread.sleep(1000);
		
		click(filterCollapseBtn);
		Thread.sleep(1500);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_LoadBtn));
		ClickUsingJs(MA_LoadBtn);

		Thread.sleep(5999);

		click(debitRow1Chkbox);

		Thread.sleep(2000);

		List<WebElement> a = creditRowChkboxList;

		for (WebElement string : a) {
			string.click();
		}

		// Credit Side

		String actDebitSideList = listOfElements(MA_CreditSideRow1list);
		String expDebitSideList = "[1, NDT57:1, " + FilterdateF9(1) + ", " + FilterdateF9(1)
				+ ", ₹, 100.00, 100.00, 100.00, 0.00, 0.00, 0.00, 0.00]";

		System.err.println(" Actual Debit List  : " + actDebitSideList);
		System.err.println(" Exp    Debit List  : " + expDebitSideList);

		String actDebitSideRow2List = listOfElements(MA_CreditSideRow2ist);
		String expDebitSideRow2List = "[2, NDT57:2, " + FilterdateF9(2) + ", " + FilterdateF9(2)
				+ ", ₹, 200.00, 200.00, 200.00, 0.00, 0.00, 0.00, 0.00]";

		System.err.println(" Actual Debit List  : " + actDebitSideRow2List);
		System.err.println(" Exp    Debit List  : " + expDebitSideRow2List);

		String actDebitSideRow3List = listOfElements(MA_CreditSideRow3list);
		String expDebitSideRow3List = "[3, NDT57:3, " + FilterdateF9(3) + ", " + FilterdateF9(3)
				+ ", ₹, 300.00, 300.00, 300.00, 0.00, 0.00, 0.00, 0.00]";

		System.err.println(" Actual Debit List  : " + actDebitSideRow3List);
		System.err.println(" Exp    Debit List  : " + expDebitSideRow3List);

		String actDebitSideRow4List = listOfElements(MA_CreditSideRow4list);
		String expDebitSideRow4List = "[4, NDT57:4, " + FilterdateF9(4) + ", " + FilterdateF9(4)
				+ ", ₹, 400.00, 400.00, 400.00, 0.00, 0.00, 0.00, 0.00]";

		System.err.println(" Actual Debit List  : " + actDebitSideRow4List);
		System.err.println(" Exp    Debit List  : " + expDebitSideRow4List);

		String actDebitSideRow5List = listOfElements(MA_CreditSideRow5list);
		String expDebitSideRow5List = "[5, NDT57:5, " + FilterdateF9(5) + ", " + FilterdateF9(5)
				+ ", ₹, 500.00, 500.00, 500.00, 0.00, 0.00, 0.00, 0.00]";

		System.err.println(" Actual Debit List  : " + actDebitSideRow5List);
		System.err.println(" Exp    Debit List  : " + expDebitSideRow5List);

		String actCreditSideList = listOfElements(MA_DebitSideRow1list);
		String expCreditSideList = "[1, NDT55:1, SI001, " + FilterdateF9(2) + ", " + FilterdateF9(2)
				+ ", ₹, 2000.00, 2000.00, 0.00, 0.00, 0.00, 0.00, 0.00]";

		System.err.println(" Actual Credit List  : " + actCreditSideList);
		System.err.println(" Exp    Credit List  : " + expCreditSideList);

		Thread.sleep(2000);

		String actdebitTotal = debitTotal.getText();
		String expdebitTotal = "2000.00";

		System.err.println(" debitTotal : " + actdebitTotal + " Value  Exp: " + expdebitTotal);

		Thread.sleep(2000);

		String actcreditTotal = creditTotal.getText();
		String expcreditTotal = "1500.00";

		System.err.println(" creditTotal : " + actcreditTotal + " Value  Exp: " + expcreditTotal);

		Thread.sleep(2000);

		click(MA_OkBtn);

		String expMessage = "Record Saved Succesfully";
		String actMessage = checkValidationMessage(expMessage);

		System.err.println(" Message ACT: " + actMessage);
		System.err.println(" Message EXP: " + expMessage);

		if (actCreditSideList.equalsIgnoreCase(expCreditSideList) && actDebitSideList.equalsIgnoreCase(expDebitSideList)
				&& actdebitTotal.equalsIgnoreCase(expdebitTotal) && actcreditTotal.equalsIgnoreCase(expcreditTotal)
				&& actMessage.equalsIgnoreCase(expMessage)) {
			return true;
		} else if (actdebitTotal.equalsIgnoreCase(expdebitTotal) && actcreditTotal.equalsIgnoreCase(expcreditTotal)) {
			return true;
		}

		else {
			return false;
		}

	}

	public boolean checkBalanceAmountOnDebitSideInManulaAdjustmentScreen()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		Thread.sleep(2000);

		
		click(homeMenu);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(utilitesMenu));
		utilitesMenu.click();

		Thread.sleep(1999);
		ClickUsingJs(ManualAdjustemntMenu);

		Thread.sleep(1999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_ARAPDrpdwn));

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_ARAPDrpdwn));
		Select ARAP = new Select(MA_ARAPDrpdwn);
		ARAP.selectByValue("0");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_AccountDrpdwn));
		MA_AccountDrpdwn.sendKeys("Customer A");
		Thread.sleep(1999);
		MA_AccountDrpdwn.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_DepDrpDwn));
		MA_DepDrpDwn.sendKeys("Dubai");
		Thread.sleep(1999);
		MA_DepDrpDwn.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_CurrencyDrpDwn));
		MA_CurrencyDrpDwn.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME);
		MA_CurrencyDrpDwn.sendKeys("INR");
		Thread.sleep(1999);
		MA_CurrencyDrpDwn.sendKeys(Keys.TAB);

		Thread.sleep(1000);
		
		click(filterCollapseBtn);
		Thread.sleep(1500);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_LoadBtn));
		ClickUsingJs(MA_LoadBtn);

		Thread.sleep(6000);

		click(MA_DebitSide1stRowChkBox);

		String actCreditSideList = listOfElements(MA_DebitSideRow1list);
		String expCreditSideList = "[1, NDT55:1, SI001, " + FilterdateF9(2) + ", " + FilterdateF9(3)
				+ ", ₹, 2000.00, 500.00, 0.00, 0.00, 0.00, 0.00, 1500.00]";

		System.err.println(" Actual Credit List  : " + actCreditSideList);
		System.err.println(" Exp    Credit List  : " + expCreditSideList);

		Thread.sleep(2000);

		String actdebitTotal = debitTotal.getText();
		String expdebitTotal = "500.00";

		System.err.println(" debitTotal : " + actdebitTotal + " Value  Exp: " + expdebitTotal);

		Thread.sleep(2000);

		String actcreditTotal = creditTotal.getText();
		String expcreditTotal = "0.00";

		System.err.println(" creditTotal : " + actcreditTotal + " Value  Exp: " + expcreditTotal);

		if (actdebitTotal.equalsIgnoreCase(expdebitTotal) && actcreditTotal.equalsIgnoreCase(actcreditTotal)
				&& actCreditSideList.equalsIgnoreCase(expCreditSideList)) {
			return true;
		} else if (actdebitTotal.equalsIgnoreCase(expdebitTotal) && actcreditTotal.equalsIgnoreCase(actcreditTotal)) {
			return true;
		}

		else {

			return false;
		}

	}

	public boolean chekSavingSalesInvoiceVATWithMultiple()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException {

		Thread.sleep(2000);

		// re_LunchBrowser();

	/*	Thread.sleep(2000);

		eraseAllTransactions();

		logout();

		Thread.sleep(2567);

		checkLoginToManualAdjsuments();*/
		Thread.sleep(2500);

		waitOn(homeMenu);

		Thread.sleep(2000);
		
		
		click(financialsMenu);

		click(financialsTransactionMenu);

		click(financialTransactionSalesMenu);

		click(salesInvoiceVATVoucher);

		Thread.sleep(8000);
		click(newBtn);
		Thread.sleep(4000);

		boolean actVoucherSaving1 = CheckSavingSalesInvoiceVAT("Customer A", "DUBAI", "HYDERABAD", "10", "10");
		boolean actVoucherSaving2 = CheckSavingSalesInvoiceVAT("Customer A", "DUBAI", "HYDERABAD", "10", "10");
		boolean actVoucherSaving3 = CheckSavingSalesInvoiceVAT("Customer A", "DUBAI", "HYDERABAD", "10", "20");
		boolean actVoucherSaving4 = CheckSavingSalesInvoiceVAT("Customer A", "DUBAI", "HYDERABAD", "10", "30");
		boolean actVoucherSaving5 = CheckSavingSalesInvoiceVAT("Customer A", "DUBAI", "HYDERABAD", "10", "40");

		boolean expVoucherSaving1 = true;
		boolean expVoucherSaving2 = true;
		boolean expVoucherSaving3 = true;
		boolean expVoucherSaving4 = true;
		boolean expVoucherSaving5 = true;

		System.err.println(" Saving Voucher 1:" + actVoucherSaving1 + " Value Exp: " + expVoucherSaving1);
		System.err.println(" Saving Voucher 2:" + actVoucherSaving2 + " Value Exp: " + expVoucherSaving2);
		System.err.println(" Saving Voucher 3:" + actVoucherSaving3 + " Value Exp: " + expVoucherSaving3);
		System.err.println(" Saving Voucher 4:" + actVoucherSaving4 + " Value Exp: " + expVoucherSaving4);
		System.err.println(" Saving Voucher 5:" + actVoucherSaving5 + " Value Exp: " + expVoucherSaving5);

		if (actVoucherSaving1 == expVoucherSaving1 && actVoucherSaving2 == expVoucherSaving2
				&& actVoucherSaving3 == expVoucherSaving3 && actVoucherSaving4 == expVoucherSaving4
				&& actVoucherSaving5 == expVoucherSaving5) {
			return true;
		} else {

			return false;
		}
	}

	public boolean chekSavingReceiptsVATWithMultiple()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {

		boolean actVoucherSaving1 = checkSavingVoucherInRecepitsVAT("Dubai", "Customer A", "100");
		boolean actVoucherSaving2 = checkSavingVoucherInRecepitsVAT("Dubai", "Customer A", "200");
		boolean actVoucherSaving3 = checkSavingVoucherInRecepitsVAT("Dubai", "Customer A", "300");
		boolean actVoucherSaving4 = checkSavingVoucherInRecepitsVAT("Dubai", "Customer A", "400");
		boolean actVoucherSaving5 = checkSavingVoucherInRecepitsVAT("Dubai", "Customer A", "500");

		boolean expVoucherSaving1 = true;
		boolean expVoucherSaving2 = true;
		boolean expVoucherSaving3 = true;
		boolean expVoucherSaving4 = true;
		boolean expVoucherSaving5 = true;

		System.err.println(" Saving Voucher 1:" + actVoucherSaving1 + " Value Exp: " + expVoucherSaving1);
		System.err.println(" Saving Voucher 2:" + actVoucherSaving2 + " Value Exp: " + expVoucherSaving2);
		System.err.println(" Saving Voucher 3:" + actVoucherSaving3 + " Value Exp: " + expVoucherSaving3);
		System.err.println(" Saving Voucher 4:" + actVoucherSaving4 + " Value Exp: " + expVoucherSaving4);
		System.err.println(" Saving Voucher 5:" + actVoucherSaving5 + " Value Exp: " + expVoucherSaving5);

		if (actVoucherSaving1 == expVoucherSaving1 && actVoucherSaving2 == expVoucherSaving2
				&& actVoucherSaving3 == expVoucherSaving3 && actVoucherSaving4 == expVoucherSaving4
				&& actVoucherSaving5 == expVoucherSaving5) {
			return true;
		} else {

			return false;
		}
	}

	public boolean checkManualAdjustmentWithMultipleCreditAndMultipleDebitWithAR()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {

		Thread.sleep(2000);

		
		click(homeMenu);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(utilitesMenu));
		utilitesMenu.click();

		Thread.sleep(1999);
		getAction().moveToElement(ManualAdjustemntMenu).build().perform();

		Thread.sleep(1999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(ManualAdjustemntMenu));
		ManualAdjustemntMenu.click();

		Thread.sleep(1999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_ARAPDrpdwn));

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_ARAPDrpdwn));
		Select ARAP = new Select(MA_ARAPDrpdwn);
		ARAP.selectByValue("0");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_AccountDrpdwn));
		MA_AccountDrpdwn.sendKeys("Customer A");
		Thread.sleep(1999);
		MA_AccountDrpdwn.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_DepDrpDwn));
		MA_DepDrpDwn.sendKeys("Dubai");
		Thread.sleep(1999);
		MA_DepDrpDwn.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_CurrencyDrpDwn));
		MA_CurrencyDrpDwn.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME);
		MA_CurrencyDrpDwn.sendKeys("INR");
		Thread.sleep(1999);
		MA_CurrencyDrpDwn.sendKeys(Keys.TAB);

		Thread.sleep(1000);
		
		click(filterCollapseBtn);
		Thread.sleep(1500);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_LoadBtn));
		ClickUsingJs(MA_LoadBtn);

		Thread.sleep(4999);

		List<WebElement> a = debitRowChkboxList;

		for (WebElement string : a) {
			string.click();
		}

		Thread.sleep(2000);

		click(CreditRow1Chkbox);

		Thread.sleep(2000);

		click(CreditRow2Chkbox);

		boolean actCreditRow2Chkbox = CreditRow2Chkbox.isSelected();
		boolean expCreditRow2Chkbox = false;

		System.err.println(
				" CreditRow2Chkbox  Enable Status : " + actCreditRow2Chkbox + " Value Exp: " + expCreditRow2Chkbox);

		Thread.sleep(2000);

		List<WebElement> a1 = debitRowChkboxList;

		for (WebElement string : a1) {
			string.click();
		}

		Thread.sleep(2000);

		String exp = "First Uncheck Right side grid";
		String act = checkValidationMessage(exp);

		if (actCreditRow2Chkbox == expCreditRow2Chkbox && act.equalsIgnoreCase(exp)) {
			return true;
		} else {
			return false;
		}

	}

	public boolean checkSavingPurchaseVOucherVAT(String vendor, String curr, String dep, String qty, String rate)
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {

		Thread.sleep(3000);

		click(financialsMenu);

		click(financialsTransactionMenu);

		click(financialsTransactionsPurchaseMenu);

		Thread.sleep(3000);
		click(purchaseVouchersVat);

		Thread.sleep(5000);

		waitToClick(newBtn);

		Thread.sleep(2000);
		click(documentNumberTxt);

		Thread.sleep(2000);
		click(vendorAccountTxt);
		vendorAccountTxt.sendKeys(vendor);
		Thread.sleep(2000);

		vendorAccountTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		removetTxt(voucherHeaderCurrency);
		voucherHeaderCurrency.sendKeys(curr);

		Thread.sleep(2000);

		voucherHeaderCurrency.sendKeys(Keys.TAB);

		voucherHeaderExchangeRate.click();

		Thread.sleep(2000);

		click(departmentTxt);
		removetTxt(departmentTxt);
		departmentTxt.sendKeys(dep);
		Thread.sleep(2000);
		departmentTxt.sendKeys(Keys.TAB);

		Thread.sleep(1000);

		click(select1stRow_1stColumn);

		enter_WarehouseTxt.click();

		enter_WarehouseTxt.sendKeys(Keys.SPACE);

		int warehousecount = warehouseBodyComboList.size();

		for (int i = 0; i < warehousecount; i++) {
			String data = warehouseBodyComboList.get(i).getText();

			if (data.equalsIgnoreCase("HYDERABAD")) {
				warehouseBodyComboList.get(i).click();
				break;
			}
		}

		enter_WarehouseTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_ItemTxt));
		enter_ItemTxt.click();
		enter_ItemTxt.sendKeys(Keys.SPACE);
		int pvvGridItemListCount = pvvGridItemList.size();
		for (int i = 0; i < pvvGridItemListCount; i++) {
			String Item = pvvGridItemList.get(i).getText();
			if (Item.equalsIgnoreCase("STD RATE COGS ITEM")) {
				pvvGridItemList.get(i).click();
				break;
			}
		}
		enter_ItemTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		removetTxt(enter_TaxCode1);
		enter_TaxCode1.sendKeys("STD");
		Thread.sleep(2000);
		enter_TaxCode1.sendKeys(Keys.TAB);
		Thread.sleep(2000);

		enter_PurchaseAccountTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		click(select1stRow_9thColumn);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_Quantity));
		enter_Quantity.click();
		enter_Quantity.clear();
		enter_Quantity.sendKeys(qty);

		Thread.sleep(2000);
		click(select1stRow_11thColumn);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_Rate));
		enter_Rate.click();
		enter_Rate.clear();
		enter_Rate.sendKeys(rate);
		enter_Rate.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		click(enter_Gross);
		enter_Gross.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		click(select1stRow_14thColumn);

		enter_PvVat.click();

		Thread.sleep(2000);
		enter_PvVat.sendKeys(Keys.TAB);

		enter_PvTaxable.click();
		enter_PvTaxable.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		String docno = documentNumberTxt.getAttribute("value");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(voucherSaveBtn));
		voucherSaveBtn.click();

		Thread.sleep(2000);

		billwisePick();

		boolean actSaving = checkVoucherSavingMessage(docno);
		boolean expSaving = true;

		if (actSaving == expSaving)

		{
			System.err.println(" Purchase VAT Saved With New Reference ");
			return true;
		} else {
			System.err.println("Purchase VAT Saved With New Reference ");
			return false;
		}

	}

	public boolean checkSavingPaymentsVAT(String curr, String dep, String acc, String amt)
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {

		Thread.sleep(2000);

		click(financialsMenu);

		click(financialsTransactionMenu);

		click(cashAndBankMenu);

		click(paymentsVATVoucher);

		Thread.sleep(4000);
		click(newBtn);

		Thread.sleep(2000);
		click(documentNumberTxt);

		click(newCashBankAccountTxt);

		newCashBankAccountTxt.sendKeys(Keys.SPACE);

		int cashAndBAnkAccountListCount = cashAndBAnkAccountList.size();

		System.err.println("cashAndBAnkAccountListCount   : " + cashAndBAnkAccountListCount);

		for (int i = 0; i < cashAndBAnkAccountListCount; i++) {
			String data = cashAndBAnkAccountList.get(i).getText();

			if (data.equalsIgnoreCase("bank")) {
				cashAndBAnkAccountList.get(i).click();

				break;
			}
		}

		newCashBankAccountTxt.sendKeys(Keys.TAB);
		Thread.sleep(2000);

		click(voucherHeaderCurrency);

		Thread.sleep(2000);

		voucherHeaderCurrency.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME);

		voucherHeaderCurrency.sendKeys(Keys.SPACE);

		int currencycount = currencyListCount.size();

		System.err.println(currencycount);

		for (int i = 0; i < currencycount; i++) {
			String data = currencyListCount.get(i).getText();

			if (data.equalsIgnoreCase(curr)) {
				currencyListCount.get(i).click();

				break;
			}
		}

		voucherHeaderCurrency.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		click(PDRVATPlaceOfSupplyTXt);

		PDRVATPlaceOfSupplyTXt.sendKeys("Dubai");

		Thread.sleep(2000);
		PDRVATPlaceOfSupplyTXt.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		click(departmentTxt);
		departmentTxt.sendKeys(Keys.SHIFT, Keys.HOME);
		departmentTxt.sendKeys(Keys.SPACE);

		WebElement options = departmentTxt;

		int departmentListCountCount = departmentListCount.size();

		for (int i = 0; i < departmentListCountCount; i++) {
			String data = departmentListCount.get(i).getText();

			if (data.equalsIgnoreCase(dep)) {
				departmentListCount.get(i).click();
				break;
			}
		}

		departmentTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		// First Row

		click(select1stRow_1stColumn);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_AccountTxt));
		enter_AccountTxt.click();
		enter_AccountTxt.sendKeys(acc);

		Thread.sleep(2000);

		enter_AccountTxt.sendKeys(Keys.TAB);

		Thread.sleep(1999);
		removetTxt(enterpayVATTaxCode);
		enterpayVATTaxCode.sendKeys("std");
		Thread.sleep(1999);
		enterpayVATTaxCode.sendKeys(Keys.TAB);

		removetTxt(enter_Amount);
		enter_Amount.sendKeys(amt);
		Thread.sleep(1999);
		enter_Amount.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		billwisePick();

		String docno = documentNumberTxt.getAttribute("value");

		Thread.sleep(2000);

		click(voucherSaveBtn);

		boolean actSaving = checkVoucherSavingMessage(docno);
		boolean expSaving = true;

		if (actSaving == expSaving)

		{
			System.err.println(" Payemnst VAT Saved With New Reference ");
			return true;
		} else {
			System.err.println("Payemnst VAT Saved With New Reference ");
			return false;
		}
	}

	@FindBy(xpath = "//*[@id='id_transactionentry_save']/div[2]")
	private static WebElement saveBtn;

	public boolean checkSavingVoucherInPurchaseVoucherVAT()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException {

		Thread.sleep(2000);

		// re_LunchBrowser();

		

		eraseAllTransactions();

	/*	logout();

		Thread.sleep(2567);

		checkLoginToManualAdjsuments();*/
		
		Thread.sleep(2000);
		waitOn(homeMenu);

		Thread.sleep(2000);

		boolean act = checkSavingPurchaseVOucherVAT("Vendor A", "USD", "DUBAI", "10", "10");
		boolean exp = true;

		System.err.println(" VOucher Saving: " + act + " Value Exp: " + exp);

		if (act == exp) {
			return true;
		} else {

			return false;
		}

	}

	public boolean checkSavingVoucherInPaymentsVAT()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {

		boolean act = checkSavingPaymentsVAT("USD", "DUBAI", "Vendor A", "105");
		boolean exp = true;

		System.err.println(" VOucher Saving: " + act + " Value Exp: " + exp);

		if (act == exp) {
			return true;
		} else {

			return false;
		}

	}

	public boolean checkManualAdjustmentWithMultipleDebitAndSingelcreditWithAP()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {

		Thread.sleep(2000);

		
		click(homeMenu);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(utilitesMenu));
		utilitesMenu.click();

		Thread.sleep(1999);
		getAction().moveToElement(ManualAdjustemntMenu).build().perform();

		Thread.sleep(1999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(ManualAdjustemntMenu));
		ManualAdjustemntMenu.click();

		Thread.sleep(1999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_ARAPDrpdwn));

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_ARAPDrpdwn));
		Select ARAP = new Select(MA_ARAPDrpdwn);
		ARAP.selectByValue("1");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_AccountDrpdwn));
		MA_AccountDrpdwn.sendKeys("Vendor A");
		Thread.sleep(1999);
		MA_AccountDrpdwn.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_DepDrpDwn));
		MA_DepDrpDwn.sendKeys("Dubai");
		Thread.sleep(1999);
		MA_DepDrpDwn.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_CurrencyDrpDwn));
		MA_CurrencyDrpDwn.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME);
		MA_CurrencyDrpDwn.sendKeys("USD");
		Thread.sleep(1999);
		MA_CurrencyDrpDwn.sendKeys(Keys.TAB);

		Thread.sleep(1000);
		
		click(filterCollapseBtn);
		Thread.sleep(1500);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_LoadBtn));
		ClickUsingJs(MA_LoadBtn);

		Thread.sleep(4500);

		click(debitRow1Chkbox);
		Thread.sleep(2000);

		String actDebitSideList = listOfElements(MA_DebitSideRow1list);
		String expDebitSideList = "[1, NDT52:1, " + dateF9() + ", " + dateF9()
				+ ", $, 105.00, 105.00, 0.00, 0.00, 0.00, 0.00, 0.00]";

		System.err.println(" Actual Debit List  : " + actDebitSideList);
		System.err.println(" Exp    Debit List  : " + expDebitSideList);

		Thread.sleep(2000);

		click(CreditRow1Chkbox);

		Thread.sleep(2000);

		String actCreditSideList = listOfElements(MA_CreditSideRow1list);
		String expCreditSideList = "[1, NDT58:1, " + dateF9() + ", " + dateF9()
				+ ", $, 105.00, 105.00, 105.00, 0.00, 0.00, 0.00, 0.00]";

		System.err.println(" Actual Credit List  : " + actCreditSideList);
		System.err.println(" Exp    Credit List  : " + expCreditSideList);

		Thread.sleep(2000);

		String actdebitTotal = debitTotal.getText();
		String expdebitTotal = "105.00";

		System.err.println(" debitTotal : " + actdebitTotal + " Value  Exp: " + expdebitTotal);

		Thread.sleep(2000);

		String actcreditTotal = creditTotal.getText();
		String expcreditTotal = "105.00";

		System.err.println(" creditTotal : " + actcreditTotal + " Value  Exp: " + expcreditTotal);

		Thread.sleep(2000);

		click(MA_OkBtn);

		String expMessage = "Record Saved Succesfully";
		String actMessage = checkValidationMessage(expMessage);

		System.err.println(" Message ACT: " + actMessage);
		System.err.println(" Message EXP: " + expMessage);

		if (actCreditSideList.equalsIgnoreCase(expCreditSideList) && actDebitSideList.equalsIgnoreCase(expDebitSideList)
				&& actdebitTotal.equalsIgnoreCase(expdebitTotal) && actcreditTotal.equalsIgnoreCase(expcreditTotal)
				&& actMessage.equalsIgnoreCase(expMessage)) {
			System.err.println(" Test Pass: AP Adjsuted ");
			return true;
		} else {
			System.err.println(" Test FAIl: AP Adjsuted ");
			return false;
		}

	}

	public boolean checkSavedVoucherInPaymentsVATAfterManulAdjustments() throws InterruptedException {
		Thread.sleep(3000);

		click(financialsMenu);

		click(financialsTransactionMenu);

		click(cashAndBankMenu);

		click(paymentsVATVoucher);

		Thread.sleep(2000);

		voucherHomePageVoucherSelect("1");

		Thread.sleep(2000);

		String actRow1 = listOfElements(entryPageRow1List);
		String expRow1 = "[1, Vendor A, Std Rate, 105.00, NDT52:1 : " + getCurrentdateDayFormat() + ", 5.00]";

		System.err.println("  ACT Row1 List: " + actRow1);
		System.err.println("  EXP Row1 List: " + expRow1);

		if (actRow1.equalsIgnoreCase(expRow1)) {
			return true;
		} else {

			return false;
		}

	}

	public boolean checkSavingVoucherInPVVATWIthMultipleAmount()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException {

		Thread.sleep(2000);

		// re_LunchBrowser();

		

		eraseAllTransactions();
		Thread.sleep(2000);

	/*	logout();

		Thread.sleep(2567);

		checkLoginToManualAdjsuments();
*/
		waitOn(homeMenu);

		Thread.sleep(2000);

		boolean actVoucherSaving1 = checkSavingPurchaseVOucherVAT("Vendor A", "USD", "DUBAI", "10", "10");
		boolean actVoucherSaving2 = checkSavingPurchaseVOucherVAT("Vendor A", "USD", "DUBAI", "10", "10");
		boolean actVoucherSaving3 = checkSavingPurchaseVOucherVAT("Vendor A", "USD", "DUBAI", "10", "20");
		boolean actVoucherSaving4 = checkSavingPurchaseVOucherVAT("Vendor A", "USD", "DUBAI", "10", "30");
		boolean actVoucherSaving5 = checkSavingPurchaseVOucherVAT("Vendor A", "USD", "DUBAI", "10", "40");

		boolean expVoucherSaving1 = true;
		boolean expVoucherSaving2 = true;
		boolean expVoucherSaving3 = true;
		boolean expVoucherSaving4 = true;
		boolean expVoucherSaving5 = true;

		System.err.println(" Saving Voucher 1:" + actVoucherSaving1 + " Value Exp: " + expVoucherSaving1);
		System.err.println(" Saving Voucher 2:" + actVoucherSaving2 + " Value Exp: " + expVoucherSaving2);
		System.err.println(" Saving Voucher 3:" + actVoucherSaving3 + " Value Exp: " + expVoucherSaving3);
		System.err.println(" Saving Voucher 4:" + actVoucherSaving4 + " Value Exp: " + expVoucherSaving4);
		System.err.println(" Saving Voucher 5:" + actVoucherSaving5 + " Value Exp: " + expVoucherSaving5);

		if (actVoucherSaving1 == expVoucherSaving1 && actVoucherSaving2 == expVoucherSaving2
				&& actVoucherSaving3 == expVoucherSaving3 && actVoucherSaving4 == expVoucherSaving4
				&& actVoucherSaving5 == expVoucherSaving5) {
			return true;
		} else {

			return false;
		}
	}

	public boolean checkSavingPaymentsVATWithQty1055()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {

		boolean act = checkSavingPaymentsVAT("USD", "DUBAI", "Vendor A", "1055");
		boolean exp = true;

		System.err.println(" Saving Voucher 1:" + act + " Value Exp: " + exp);

		if (act == exp) {
			return true;
		} else {

			return false;
		}
	}

	public boolean checkManualAdjustmentWithMultipleDebitAndSinglecreditWithAP()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException 
	{

		Thread.sleep(2000);

		
		click(homeMenu);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(utilitesMenu));
		utilitesMenu.click();

		Thread.sleep(1999);
		getAction().moveToElement(ManualAdjustemntMenu).build().perform();

		Thread.sleep(1999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(ManualAdjustemntMenu));
		ManualAdjustemntMenu.click();

		Thread.sleep(1999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_ARAPDrpdwn));

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_ARAPDrpdwn));
		Select ARAP = new Select(MA_ARAPDrpdwn);
		ARAP.selectByValue("1");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_AccountDrpdwn));
		MA_AccountDrpdwn.sendKeys("Vendor A");
		Thread.sleep(1999);
		MA_AccountDrpdwn.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_DepDrpDwn));
		MA_DepDrpDwn.sendKeys("Dubai");
		Thread.sleep(1999);
		MA_DepDrpDwn.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_CurrencyDrpDwn));
		MA_CurrencyDrpDwn.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME);
		MA_CurrencyDrpDwn.sendKeys("USD");
		Thread.sleep(1999);
		MA_CurrencyDrpDwn.sendKeys(Keys.TAB);

		Thread.sleep(1000);
		
		click(filterCollapseBtn);
		Thread.sleep(1500);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_LoadBtn));
		ClickUsingJs(MA_LoadBtn);

		Thread.sleep(5999);

		int count = MA_DebitSideRow1list.size();
		ArrayList<String> Debit = new ArrayList<String>();

		for (int i = 0; i < count; i++) {
			String data = MA_DebitSideRow1list.get(i).getText();

			if (data.isEmpty() == false) {
				Debit.add(data);
			}

		}

		String actDebitSideList = Debit.toString();
		String expDebitSideList = "[1, NDT52:1, " + dateF9() + ", " + dateF9()
				+ ", $, 105.00, 105.00, 0.00, 0.00, 0.00, 0.00, 0.00]";

		System.err.println(" Actual Debit List  : " + actDebitSideList);
		System.err.println(" Exp    Debit List  : " + expDebitSideList);

		String actDebitSideRow2List = listOfElements(MA_DebitSideRow2list);
		String expDebitSideRow2List = "[2, NDT52:2, " + dateF9() + ", " + dateF9()
				+ ", $, 105.00, 105.00, 0.00, 0.00, 0.00, 0.00, 0.00]";

		System.err.println(" Actual Debit List  : " + actDebitSideRow2List);
		System.err.println(" Exp    Debit List  : " + expDebitSideRow2List);

		String actDebitSideRow3List = listOfElements(MA_DebitSideRow3list);
		String expDebitSideRow3List = "[3, NDT52:3, " + dateF9() + ", " + dateF9()
				+ ", $, 210.00, 210.00, 0.00, 0.00, 0.00, 0.00, 0.00]";

		System.err.println(" Actual Debit List  : " + actDebitSideRow3List);
		System.err.println(" Exp    Debit List  : " + expDebitSideRow3List);

		String actDebitSideRow4List = listOfElements(MA_DebitSideRow4list);
		String expDebitSideRow4List = "[4, NDT52:4, " + dateF9() + ", " + dateF9()
				+ ", $, 315.00, 315.00, 0.00, 0.00, 0.00, 0.00, 0.00]";

		System.err.println(" Actual Debit List  : " + actDebitSideRow4List);
		System.err.println(" Exp    Debit List  : " + expDebitSideRow4List);

		String actDebitSideRow5List = listOfElements(MA_DebitSideRow5list);
		String expDebitSideRow5List = "[5, NDT52:5, " + dateF9() + ", " + dateF9()
				+ ", $, 420.00, 420.00, 0.00, 0.00, 0.00, 0.00, 0.00]";

		System.err.println(" Actual Debit List  : " + actDebitSideRow5List);
		System.err.println(" Exp    Debit List  : " + expDebitSideRow5List);

		int count1 = MA_CreditSideRow1list.size();
		ArrayList<String> Credit = new ArrayList<String>();

		for (int i = 0; i < count1; i++) {
			String data = MA_CreditSideRow1list.get(i).getText();

			if (data.isEmpty() == false) {
				Credit.add(data);
			}

		}

		String actCreditSideList = Credit.toString();
		String expCreditSideList = "[1, NDT58:1, " + dateF9() + ", " + dateF9()
				+ ", $, 1055.00, 1055.00, 0.00, 0.00, 0.00, 0.00, 0.00]";

		System.err.println(" Actual Credit List  : " + actCreditSideList);
		System.err.println(" Exp    Credit List  : " + expCreditSideList);

		Thread.sleep(2000);

		List<WebElement> a = debitRowChkboxList;

		for (WebElement string : a) {
			string.click();
		}

		Thread.sleep(2000);

		click(CreditRow1Chkbox);

		Thread.sleep(2000);

		String actdebitTotal = debitTotal.getText();
		String expdebitTotal = "1155.00";

		System.err.println(" debitTotal : " + actdebitTotal + " Value  Exp: " + expdebitTotal);

		Thread.sleep(2000);

		String actcreditTotal = creditTotal.getText();
		String expcreditTotal = "1055.00";

		System.err.println(" creditTotal : " + actcreditTotal + " Value  Exp: " + expcreditTotal);

		Thread.sleep(2000);

		ClickUsingJs(MA_OkBtn);

		String expMessage = "Record Saved Succesfully";
		String actMessage = checkValidationMessage(expMessage);

		System.err.println(" Message ACT: " + actMessage);
		System.err.println(" Message EXP: " + expMessage);

		if (actCreditSideList.equalsIgnoreCase(expCreditSideList) && actDebitSideList.equalsIgnoreCase(expDebitSideList)
				&& actdebitTotal.equalsIgnoreCase(expdebitTotal) && actcreditTotal.equalsIgnoreCase(expcreditTotal)
				&& actMessage.equalsIgnoreCase(expMessage)) {
			return true;
		} else {
			return false;
		}

	}

	public boolean checkBalanceAmountOnCreditSideInManulaAdjustmentScreenWithAP()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException 
	{
		Thread.sleep(2000);

		
		click(homeMenu);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(utilitesMenu));
		utilitesMenu.click();

		Thread.sleep(1999);
		getAction().moveToElement(ManualAdjustemntMenu).build().perform();

		Thread.sleep(1999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(ManualAdjustemntMenu));
		ManualAdjustemntMenu.click();

		Thread.sleep(1999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_ARAPDrpdwn));

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_ARAPDrpdwn));
		Select ARAP = new Select(MA_ARAPDrpdwn);
		ARAP.selectByValue("1");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_AccountDrpdwn));
		MA_AccountDrpdwn.sendKeys("Vendor A");
		Thread.sleep(1999);
		MA_AccountDrpdwn.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_DepDrpDwn));
		MA_DepDrpDwn.sendKeys("Dubai");
		Thread.sleep(1999);
		MA_DepDrpDwn.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_CurrencyDrpDwn));
		MA_CurrencyDrpDwn.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME);
		MA_CurrencyDrpDwn.sendKeys("USD");
		Thread.sleep(1999);
		MA_CurrencyDrpDwn.sendKeys(Keys.TAB);

		Thread.sleep(1000);
		
		click(filterCollapseBtn);
		Thread.sleep(1500);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_LoadBtn));
		ClickUsingJs(MA_LoadBtn);

		Thread.sleep(5999);

		String actCreditSideList = listOfElements(MA_DebitSideRow1list);
		String expCreditSideList = "[1, NDT52:5, " + dateF9() + ", " + dateF9()
				+ ", $, 420.00, 100.00, 0.00, 0.00, 0.00, 0.00, 320.00]";

		System.err.println(" Actual Credit List  : " + actCreditSideList);
		System.err.println(" Exp    Credit List  : " + expCreditSideList);

		Thread.sleep(2000);

		if (actCreditSideList.equalsIgnoreCase(expCreditSideList)) 
		{
			return true;
		} else {

			return false;
		}

	}

	public boolean checkSavingPurchaseVoucherVATWith5000()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException

	{

		Thread.sleep(2000);

		// re_LunchBrowser();

		

		eraseAllTransactions();
		Thread.sleep(2000);
		
	/*	logout();

		Thread.sleep(2567);

		checkLoginToManualAdjsuments();*/
		

		waitOn(homeMenu);

		Thread.sleep(2000);

		boolean actVoucherSaving1 = checkSavingPurchaseVOucherVAT("Vendor A", "USD", "DUBAI", "10", "500");

		boolean expVoucherSaving1 = true;

		System.err.println(" Saving Voucher 1:" + actVoucherSaving1 + " Value Exp: " + expVoucherSaving1);

		if (actVoucherSaving1 == expVoucherSaving1) 
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public boolean checkSavingPaymentsVATWithMultipleSaving()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException 
	{

		boolean actVoucherSaving1 = checkSavingPaymentsVAT("USD", "DUBAI", "Vendor A", "100");
		boolean actVoucherSaving2 = checkSavingPaymentsVAT("USD", "DUBAI", "Vendor A", "200");
		boolean actVoucherSaving3 = checkSavingPaymentsVAT("USD", "DUBAI", "Vendor A", "400");
		boolean actVoucherSaving4 = checkSavingPaymentsVAT("USD", "DUBAI", "Vendor A", "500");
		boolean actVoucherSaving5 = checkSavingPaymentsVAT("USD", "DUBAI", "Vendor A", "200");

		boolean expVoucherSaving1 = true;
		boolean expVoucherSaving2 = true;
		boolean expVoucherSaving3 = true;
		boolean expVoucherSaving4 = true;
		boolean expVoucherSaving5 = true;

		System.err.println(" Saving Voucher 1:" + actVoucherSaving1 + " Value Exp: " + expVoucherSaving1);
		System.err.println(" Saving Voucher 2:" + actVoucherSaving2 + " Value Exp: " + expVoucherSaving2);
		System.err.println(" Saving Voucher 3:" + actVoucherSaving3 + " Value Exp: " + expVoucherSaving3);
		System.err.println(" Saving Voucher 4:" + actVoucherSaving4 + " Value Exp: " + expVoucherSaving4);
		System.err.println(" Saving Voucher 5:" + actVoucherSaving5 + " Value Exp: " + expVoucherSaving5);

		if (actVoucherSaving1 == expVoucherSaving1 && actVoucherSaving2 == expVoucherSaving2
				&& actVoucherSaving3 == expVoucherSaving3 && actVoucherSaving4 == expVoucherSaving4
				&& actVoucherSaving5 == expVoucherSaving5) {
			return true;
		} else {

			return false;
		}
	}

	public boolean checkManualAdjustmentWithMultipleCreditAndSingelDebitWithAP()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {

		Thread.sleep(2000);

		
		click(homeMenu);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(utilitesMenu));
		utilitesMenu.click();

		Thread.sleep(1999);
		getAction().moveToElement(ManualAdjustemntMenu).build().perform();

		Thread.sleep(1999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(ManualAdjustemntMenu));
		ManualAdjustemntMenu.click();

		Thread.sleep(1999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_ARAPDrpdwn));

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_ARAPDrpdwn));
		Select ARAP = new Select(MA_ARAPDrpdwn);
		ARAP.selectByValue("1");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_AccountDrpdwn));
		MA_AccountDrpdwn.sendKeys("vendor A");
		Thread.sleep(1999);
		MA_AccountDrpdwn.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_DepDrpDwn));
		MA_DepDrpDwn.sendKeys("Dubai");
		Thread.sleep(1999);
		MA_DepDrpDwn.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_CurrencyDrpDwn));
		MA_CurrencyDrpDwn.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME);
		MA_CurrencyDrpDwn.sendKeys("USD");
		Thread.sleep(1999);
		MA_CurrencyDrpDwn.sendKeys(Keys.TAB);

		Thread.sleep(1000);
		
		click(filterCollapseBtn);
		Thread.sleep(1500);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_LoadBtn));
		ClickUsingJs(MA_LoadBtn);

		Thread.sleep(6899);

		click(debitRow1Chkbox);

		Thread.sleep(2000);

		List<WebElement> a = creditRowChkboxList;

		for (WebElement string : a) {
			string.click();
		}

		// Credit Side

		String actDebitSideList = listOfElements(MA_CreditSideRow1list);
		String expDebitSideList = "[1, NDT58:1, " + dateF9() + ", " + dateF9()
				+ ", $, 100.00, 100.00, 100.00, 0.00, 0.00, 0.00, 0.00]";

		System.err.println(" Actual Debit List  : " + actDebitSideList);
		System.err.println(" Exp    Debit List  : " + expDebitSideList);

		String actDebitSideRow2List = listOfElements(MA_CreditSideRow2ist);
		String expDebitSideRow2List = "[2, NDT58:2, " + dateF9() + ", " + dateF9()
				+ ", $, 200.00, 200.00, 200.00, 0.00, 0.00, 0.00, 0.00]";

		System.err.println(" Actual Debit List  : " + actDebitSideRow2List);
		System.err.println(" Exp    Debit List  : " + expDebitSideRow2List);

		String actDebitSideRow3List = listOfElements(MA_CreditSideRow3list);
		String expDebitSideRow3List = "[3, NDT58:3, " + dateF9() + ", " + dateF9()
				+ ", $, 400.00, 400.00, 400.00, 0.00, 0.00, 0.00, 0.00]";

		System.err.println(" Actual Debit List  : " + actDebitSideRow3List);
		System.err.println(" Exp    Debit List  : " + expDebitSideRow3List);

		String actDebitSideRow4List = listOfElements(MA_CreditSideRow4list);
		String expDebitSideRow4List = "[4, NDT58:4, " + dateF9() + ", " + dateF9()
				+ ", $, 500.00, 500.00, 500.00, 0.00, 0.00, 0.00, 0.00]";

		System.err.println(" Actual Debit List  : " + actDebitSideRow4List);
		System.err.println(" Exp    Debit List  : " + expDebitSideRow4List);

		String actDebitSideRow5List = listOfElements(MA_CreditSideRow5list);
		String expDebitSideRow5List = "[5, NDT58:5, " + dateF9() + ", " + dateF9()
				+ ", $, 200.00, 200.00, 200.00, 0.00, 0.00, 0.00, 0.00]";

		System.err.println(" Actual Debit List  : " + actDebitSideRow5List);
		System.err.println(" Exp    Debit List  : " + expDebitSideRow5List);

		String actCreditSideList = listOfElements(MA_DebitSideRow1list);
		String expCreditSideList = "[1, NDT52:1, " + dateF9() + ", " + dateF9()
				+ ", $, 5250.00, 5250.00, 0.00, 0.00, 0.00, 0.00, 0.00]";

		System.err.println(" Actual Credit List  : " + actCreditSideList);
		System.err.println(" Exp    Credit List  : " + expCreditSideList);

		Thread.sleep(2000);

		String actdebitTotal = debitTotal.getText();
		String expdebitTotal = "5250.00";

		System.err.println(" debitTotal : " + actdebitTotal + " Value  Exp: " + expdebitTotal);

		Thread.sleep(2000);

		String actcreditTotal = creditTotal.getText();
		String expcreditTotal = "1400.00";

		System.err.println(" creditTotal : " + actcreditTotal + " Value  Exp: " + expcreditTotal);

		Thread.sleep(2000);

		click(MA_OkBtn);

		String expMessage = "Record Saved Succesfully";
		String actMessage = checkValidationMessage(expMessage);

		System.err.println(" Message ACT: " + actMessage);
		System.err.println(" Message EXP: " + expMessage);

		if (actCreditSideList.equalsIgnoreCase(expCreditSideList) && actDebitSideList.equalsIgnoreCase(expDebitSideList)
				&& actdebitTotal.equalsIgnoreCase(expdebitTotal) && actcreditTotal.equalsIgnoreCase(expcreditTotal)
				&& actMessage.equalsIgnoreCase(expMessage)) {
			return true;
		} else {
			return false;
		}

	}

	public boolean checkBalanceAmountOnDebitSideInManulaAdjustmentScreenAfterApAdjustment()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		Thread.sleep(2000);

		
		click(homeMenu);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(utilitesMenu));
		utilitesMenu.click();

		Thread.sleep(1999);
		getAction().moveToElement(ManualAdjustemntMenu).build().perform();

		Thread.sleep(1999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(ManualAdjustemntMenu));
		ManualAdjustemntMenu.click();

		Thread.sleep(1999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_ARAPDrpdwn));

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_ARAPDrpdwn));
		Select ARAP = new Select(MA_ARAPDrpdwn);
		ARAP.selectByValue("1");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_AccountDrpdwn));
		MA_AccountDrpdwn.sendKeys("vendor A");
		Thread.sleep(1999);
		MA_AccountDrpdwn.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_DepDrpDwn));
		MA_DepDrpDwn.sendKeys("Dubai");
		Thread.sleep(1999);
		MA_DepDrpDwn.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_CurrencyDrpDwn));
		MA_CurrencyDrpDwn.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME);
		MA_CurrencyDrpDwn.sendKeys("USD");
		Thread.sleep(1999);
		MA_CurrencyDrpDwn.sendKeys(Keys.TAB);

		Thread.sleep(1000);
		
		click(filterCollapseBtn);
		Thread.sleep(1500);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_LoadBtn));
		ClickUsingJs(MA_LoadBtn);

		Thread.sleep(5999);

		click(MA_DebitSide1stRowChkBox);

		String actCreditSideList = listOfElements(MA_DebitSideRow1list);
		String expCreditSideList = "[1, NDT52:1, " + dateF9() + ", " + dateF9()
				+ ", $, 5250.00, 3850.00, 0.00, 0.00, 0.00, 0.00, 1400.00]";

		System.err.println(" Actual Credit List  : " + actCreditSideList);
		System.err.println(" Exp    Credit List  : " + expCreditSideList);

		Thread.sleep(2000);

		String actdebitTotal = debitTotal.getText();
		String expdebitTotal = "3850.00";

		System.err.println(" debitTotal : " + actdebitTotal + " Value  Exp: " + expdebitTotal);

		Thread.sleep(2000);

		String actcreditTotal = creditTotal.getText();
		String expcreditTotal = "0.00";

		System.err.println(" creditTotal : " + actcreditTotal + " Value  Exp: " + expcreditTotal);

		if (actdebitTotal.equalsIgnoreCase(expdebitTotal) && actcreditTotal.equalsIgnoreCase(actcreditTotal)
				&& actCreditSideList.equalsIgnoreCase(expCreditSideList)) {
			return true;
		} else {

			return false;
		}

	}

	public boolean chekSavingPVVATWithMultiple()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException {

		Thread.sleep(2000);

		// re_LunchBrowser();

		Thread.sleep(2000);

		eraseAllTransactions();

	/*	logout();

		Thread.sleep(2567);

		checkLoginToManualAdjsuments();

		waitOn(homeMenu);

		Thread.sleep(2000);

		logout();
		Thread.sleep(2000);

		checkLoginToManualAdjsuments();*/

		Thread.sleep(2000);

		waitOn(homeMenu);

		boolean actVoucherSaving1 = checkSavingPurchaseVOucherVAT("Vendor A", "USD", "DUBAI", "10", "10");
		boolean actVoucherSaving2 = checkSavingPurchaseVOucherVAT("Vendor A", "USD", "DUBAI", "10", "10");
		boolean actVoucherSaving3 = checkSavingPurchaseVOucherVAT("Vendor A", "USD", "DUBAI", "10", "20");
		boolean actVoucherSaving4 = checkSavingPurchaseVOucherVAT("Vendor A", "USD", "DUBAI", "10", "30");
		boolean actVoucherSaving5 = checkSavingPurchaseVOucherVAT("Vendor A", "USD", "DUBAI", "10", "40");

		boolean expVoucherSaving1 = true;
		boolean expVoucherSaving2 = true;
		boolean expVoucherSaving3 = true;
		boolean expVoucherSaving4 = true;
		boolean expVoucherSaving5 = true;

		System.err.println(" Saving Voucher 1:" + actVoucherSaving1 + " Value Exp: " + expVoucherSaving1);
		System.err.println(" Saving Voucher 2:" + actVoucherSaving2 + " Value Exp: " + expVoucherSaving2);
		System.err.println(" Saving Voucher 3:" + actVoucherSaving3 + " Value Exp: " + expVoucherSaving3);
		System.err.println(" Saving Voucher 4:" + actVoucherSaving4 + " Value Exp: " + expVoucherSaving4);
		System.err.println(" Saving Voucher 5:" + actVoucherSaving5 + " Value Exp: " + expVoucherSaving5);

		if (actVoucherSaving1 == expVoucherSaving1 && actVoucherSaving2 == expVoucherSaving2
				&& actVoucherSaving3 == expVoucherSaving3 && actVoucherSaving4 == expVoucherSaving4
				&& actVoucherSaving5 == expVoucherSaving5) {
			return true;
		} else {

			return false;
		}
	}

	public boolean chekSavingPaymentsVATWithMultiple()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {

		boolean actVoucherSaving1 = checkSavingPaymentsVAT("USD", "DUBAI", "Vendor A", "100");
		boolean actVoucherSaving2 = checkSavingPaymentsVAT("USD", "DUBAI", "Vendor A", "200");
		boolean actVoucherSaving3 = checkSavingPaymentsVAT("USD", "DUBAI", "Vendor A", "400");
		boolean actVoucherSaving4 = checkSavingPaymentsVAT("USD", "DUBAI", "Vendor A", "500");
		boolean actVoucherSaving5 = checkSavingPaymentsVAT("USD", "DUBAI", "Vendor A", "200");

		boolean expVoucherSaving1 = true;
		boolean expVoucherSaving2 = true;
		boolean expVoucherSaving3 = true;
		boolean expVoucherSaving4 = true;
		boolean expVoucherSaving5 = true;

		System.err.println(" Saving Voucher 1:" + actVoucherSaving1 + " Value Exp: " + expVoucherSaving1);
		System.err.println(" Saving Voucher 2:" + actVoucherSaving2 + " Value Exp: " + expVoucherSaving2);
		System.err.println(" Saving Voucher 3:" + actVoucherSaving3 + " Value Exp: " + expVoucherSaving3);
		System.err.println(" Saving Voucher 4:" + actVoucherSaving4 + " Value Exp: " + expVoucherSaving4);
		System.err.println(" Saving Voucher 5:" + actVoucherSaving5 + " Value Exp: " + expVoucherSaving5);

		if (actVoucherSaving1 == expVoucherSaving1 && actVoucherSaving2 == expVoucherSaving2
				&& actVoucherSaving3 == expVoucherSaving3 && actVoucherSaving4 == expVoucherSaving4
				&& actVoucherSaving5 == expVoucherSaving5) {
			return true;
		} else {

			return false;
		}
	}

	public boolean checkManualAdjustmentWithMultipleCreditAndMultipleDebitWithAP()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException 
	{

		Thread.sleep(2000);

		click(homeMenu);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(utilitesMenu));
		utilitesMenu.click();

		Thread.sleep(1999);
		getAction().moveToElement(ManualAdjustemntMenu).build().perform();

		Thread.sleep(1999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(ManualAdjustemntMenu));
		ManualAdjustemntMenu.click();

		Thread.sleep(1999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_ARAPDrpdwn));

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_ARAPDrpdwn));
		Select ARAP = new Select(MA_ARAPDrpdwn);
		ARAP.selectByValue("1");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_AccountDrpdwn));
		MA_AccountDrpdwn.sendKeys("vendor A");
		Thread.sleep(1999);
		MA_AccountDrpdwn.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_DepDrpDwn));
		MA_DepDrpDwn.sendKeys("Dubai");
		Thread.sleep(1999);
		MA_DepDrpDwn.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_CurrencyDrpDwn));
		MA_CurrencyDrpDwn.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME);
		MA_CurrencyDrpDwn.sendKeys("USD");
		Thread.sleep(1999);
		MA_CurrencyDrpDwn.sendKeys(Keys.TAB);

		Thread.sleep(1000);
		
		click(filterCollapseBtn);
		Thread.sleep(1500);
		
		ClickUsingJs(MA_LoadBtn);

		Thread.sleep(5999);

		List<WebElement> a = debitRowChkboxList;

		for (WebElement string : a) {
			string.click();
		}

		Thread.sleep(2000);

		click(CreditRow1Chkbox);

		Thread.sleep(2000);

		click(CreditRow2Chkbox);

		boolean actCreditRow2Chkbox = CreditRow2Chkbox.isSelected();
		boolean expCreditRow2Chkbox = false;

		System.err.println(
				" CreditRow2Chkbox  Enable Status : " + actCreditRow2Chkbox + " Value Exp: " + expCreditRow2Chkbox);

		Thread.sleep(2000);

		List<WebElement> a1 = debitRowChkboxList;

		for (WebElement string : a1) {
			string.click();
		}

		Thread.sleep(2000);

		String exp = "First Uncheck Right side grid";
		String act = checkValidationMessage(exp);

		if (actCreditRow2Chkbox == expCreditRow2Chkbox && act.equalsIgnoreCase(exp)) {
			return true;
		} else {
			return false;
		}

	}

	// Auto Adjust Screen

	@FindBy(xpath = "//*[@id='id_SelectARAP']")
	private static WebElement autoAdju_TypeDrpdwn;

	@FindBy(xpath = "//*[@id='id_SelectAdjust']")
	private static WebElement autoAdju_adjustmentDrpdwn;

	@FindBy(xpath = "//*[@id='txtsrch-term']")
	private static WebElement autoAdju_ItemSearch;

	@FindBy(xpath = "//*[@id='liSelectAllMasters']")
	private static WebElement autoAdju_SelectALlMasters;

	@FindBy(xpath = "//*[@id='id_Pick']")
	private static WebElement autoAdju_OKBtn;

	@FindBy(xpath = "//*[@id='id_Cancel']")
	private static WebElement autoAdju_Cancelbtn;

	public boolean checkSavingSalesInvoiceVATAndRecepitsVAT()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException {

		Thread.sleep(2000);

		// re_LunchBrowser();

		//Thread.sleep(2000);

		//Thread.sleep(3000);

		eraseAllTransactions();

	/*	logout();

		Thread.sleep(2567);

		checkLoginToManualAdjsuments();*/
		
		Thread.sleep(4000);

		//waitOn(homeMenu);
		
		click(financialsMenu);

		click(financialsTransactionMenu);

		click(financialTransactionSalesMenu);

		click(salesInvoiceVATVoucher);

		Thread.sleep(8000);
		click(newBtn);

		Thread.sleep(4000);

		boolean actSales = CheckSavingSalesInvoiceVAT("Customer A", "AMERICA", "HYDERABAD", "10", "10");
		boolean expSales = true;

		System.err.println(" Saving Sales Invoice VAT : " + actSales + " Value Exp: " + expSales);

		boolean actRec = checkSavingVoucherInRecepitsVAT("AMERICA", "Customer A", "100");
		boolean expRec = true;

		System.err.println(" Saving Recepicts VAT : " + actRec + " Value Exp: " + expRec);

		if (actSales == expSales && actRec == expRec) {
			return true;
		} else {

			return false;
		}

	}

	public boolean checkSavingPVVATAndPaymentsVAT()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException 
	{

		Thread.sleep(2000);

		boolean act = checkSavingPurchaseVOucherVAT("Vendor A", "USD", "DUBAI", "10", "10");
		boolean exp = true;

		System.err.println(" VOucher Saving: " + act + " Value Exp: " + exp);

		Thread.sleep(2000);

		boolean actPayments = checkSavingPaymentsVAT("USD", "DUBAI", "Vendor A", "105");
		boolean expPayments = true;

		System.err.println(" VOucher Saving: " + actPayments + " Value Exp: " + expPayments);

		if (actPayments == expPayments && act == exp) {
			return true;
		} else {

			return false;
		}

	}

	public boolean checkSavingVoucher2INRecepitsVAT()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {

		boolean actRec = checkSavingVoucherInRecepitsVAT("AMERICA", "Customer A", "100");
		boolean expRec = true;

		System.err.println(" Saving Recepicts VAT : " + actRec + " Value Exp: " + expRec);

		if (actRec == expRec) {
			return true;
		} else {

			return false;
		}
	}

	public boolean checkAutoAdjustScreenWithCustomerType()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		click(focusMainSearch);
		focusMainSearch.sendKeys("Auto Adjust");
		Thread.sleep(500);
		focusMainSearch.sendKeys(Keys.ENTER);

		Thread.sleep(2500);

		Select s1 = new Select(autoAdju_TypeDrpdwn);
		s1.selectByValue("0");

		Thread.sleep(2500);

		String actType = s1.getFirstSelectedOption().getText();
		String expType = "Customer";

		System.err.println("autoAdju_TypeDrpdwn : " + actType + " Value Exp: " + expType);

		Thread.sleep(500);
		Select s2 = new Select(autoAdju_adjustmentDrpdwn);

		String actautoAdju_adjustmentDrpdwn = s2.getFirstSelectedOption().getText();
		String expautoAdju_adjustmentDrpdwn = "Auto Adjust On Fifo";

		System.err.println("autoAdju_adjustmentDrpdwn : " + actautoAdju_adjustmentDrpdwn + " Value Exp: "
				+ expautoAdju_adjustmentDrpdwn);

		click(autoAdju_ItemSearch);
		removetTxt(autoAdju_ItemSearch);
		autoAdju_ItemSearch.sendKeys("Customer A");
		Thread.sleep(2000);
		autoAdju_ItemSearch.sendKeys(Keys.ENTER);

		Thread.sleep(2000);

		click(autoAdju_SelectALlMasters);
		Thread.sleep(2000);

		click(autoAdju_OKBtn);

		String expMessage = "auto adjust finished.";
		String actMessage = checkValidationMessage(expMessage);

		Thread.sleep(2000);

		if (actMessage.equalsIgnoreCase(expMessage) && actType.equalsIgnoreCase(expType)
				&& actautoAdju_adjustmentDrpdwn.equalsIgnoreCase(expautoAdju_adjustmentDrpdwn)) 
		{
			return true;
		} else {

			return false;
		}

	}

	public boolean checkSavingAutoAdjustWithVendor()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		click(focusMainSearch);
		focusMainSearch.sendKeys("Auto Adjust");
		Thread.sleep(500);
		focusMainSearch.sendKeys(Keys.ENTER);

		Thread.sleep(2500);

		Select s1 = new Select(autoAdju_TypeDrpdwn);
		s1.selectByValue("1");

		Thread.sleep(2500);

		String actType = s1.getFirstSelectedOption().getText();
		String expType = "Vendor";

		System.err.println("autoAdju_TypeDrpdwn : " + actType + " Value Exp: " + expType);

		Thread.sleep(500);
		Select s2 = new Select(autoAdju_adjustmentDrpdwn);

		String actautoAdju_adjustmentDrpdwn = s2.getFirstSelectedOption().getText();
		String expautoAdju_adjustmentDrpdwn = "Auto Adjust On Fifo";

		System.err.println("autoAdju_adjustmentDrpdwn : " + actautoAdju_adjustmentDrpdwn + " Value Exp: "
				+ expautoAdju_adjustmentDrpdwn);

		click(autoAdju_ItemSearch);
		removetTxt(autoAdju_ItemSearch);
		autoAdju_ItemSearch.sendKeys("vendor A");
		Thread.sleep(2000);
		autoAdju_ItemSearch.sendKeys(Keys.ENTER);

		Thread.sleep(2000);

		click(autoAdju_SelectALlMasters);
		Thread.sleep(2000);

		click(autoAdju_OKBtn);

		String expMessage = "auto adjust finished.";
		String actMessage = checkValidationMessage(expMessage);

		Thread.sleep(2000);

		if (actMessage.equalsIgnoreCase(expMessage) && actType.equalsIgnoreCase(expType)
				&& actautoAdju_adjustmentDrpdwn.equalsIgnoreCase(expautoAdju_adjustmentDrpdwn)) {
			return true;
		} else {

			return false;
		}

	}

	public boolean checkAdjustedVoucherInrecepitsVAT() throws InterruptedException 
	{
		Thread.sleep(3000);

		click(financialsMenu);

		click(financialsTransactionMenu);

		click(cashAndBankMenu);

		click(recepitsVATVoucher);

		Thread.sleep(2000);

		voucherHomePageVoucherSelect("2");

		Thread.sleep(2000);

		String actRow1 = listOfElements(entryPageRow1List);
		String expRow1 = "[1, Customer A, Std Rate, 100.00, NDT55:1 : " + getCurrentdateDayFormat() + ", 4.76]";

		System.err.println("  ACT Row1 List: " + actRow1);
		System.err.println("  EXP Row1 List: " + expRow1);

		if (actRow1.equalsIgnoreCase(expRow1)) {
			return true;
		} else {

			return false;
		}

	}

	public boolean checkAdjustedVoucherINPaymentsVAT() throws InterruptedException 
	{
		Thread.sleep(3000);

		click(financialsMenu);

		click(financialsTransactionMenu);

		click(cashAndBankMenu);

		click(paymentsVATVoucher);

		Thread.sleep(2000);

		voucherHomePageVoucherSelect("1");

		Thread.sleep(2000);

		String actRow1 = listOfElements(entryPageRow1List);
		String expRow1 = "[1, Vendor A, Std Rate, 105.00, NDT52:1 : " + getCurrentdateDayFormat() + ", 5.00]";

		System.err.println("  ACT Row1 List: " + actRow1);
		System.err.println("  EXP Row1 List: " + expRow1);

		if (actRow1.equalsIgnoreCase(expRow1)) {
			return true;
		} else {

			return false;
		}

	}

	public boolean checkNonAdjsutedVoucherINReceiptsVAT() throws InterruptedException 
	{

		Thread.sleep(3000);

		click(financialsMenu);

		click(financialsTransactionMenu);

		click(cashAndBankMenu);

		click(recepitsVATVoucher);

		Thread.sleep(2000);

		voucherHomePageVoucherSelect("2");

		Thread.sleep(2000);

		String actRow1 = listOfElements(entryPageRow1List);
		String expRow1 = "[1, Customer A, Std Rate, 100.00, New Reference, 4.76]";

		System.err.println("  ACT Row1 List: " + actRow1);
		System.err.println("  EXP Row1 List: " + expRow1);

		if (actRow1.equalsIgnoreCase(expRow1)) {
			return true;
		} else {

			return false;
		}

	}

	public boolean chekSavingSalesAndrecepitsVATWithHigherAnLowerAmount()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException {

		Thread.sleep(2000);

		// re_LunchBrowser();

		Thread.sleep(2000);

		eraseAllTransactions();

	/*	logout();

		Thread.sleep(2567);

		checkLoginToManualAdjsuments();*/
		
		Thread.sleep(4000);

		//waitOn(homeMenu);
		
		click(financialsMenu);

		click(financialsTransactionMenu);

		click(financialTransactionSalesMenu);

		click(salesInvoiceVATVoucher);

		Thread.sleep(8000);
		click(newBtn);

		Thread.sleep(4000);

		boolean actSales = CheckSavingSalesInvoiceVAT("Customer A", "AMERICA", "HYDERABAD", "10", "10");
		boolean expSales = true;

		System.err.println(" Saving Sales Invoice VAT : " + actSales + " Value Exp: " + expSales);

		boolean actRec = checkSavingVoucherInRecepitsVAT("AMERICA", "Customer A", "1000");
		boolean expRec = true;

		System.err.println(" Saving Recepicts VAT : " + actRec + " Value Exp: " + expRec);

		boolean actRec2 = checkSavingVoucherInRecepitsVAT("AMERICA", "Customer A", "100");
		boolean expRec2 = true;

		System.err.println(" Saving Recepicts VAT 2 : " + actRec2 + " Value Exp: " + expRec2);

		Thread.sleep(2000);

		boolean actMethod = checkAutoAdjustScreenWithCustomerType();
		boolean expMethod = true;

		System.err.println(" ACT Method Running Status: " + actMethod);
		System.err.println(" EXP Method Running Status: " + expMethod);

		Thread.sleep(2000);

		if (actSales == expSales && actRec == expRec && actRec2 == expRec2 && actMethod == expMethod) {
			return true;
		}

		else if (actMethod == expMethod) {
			return true;
		}

		else {

			return false;
		}

	}

	public boolean checkSavedVoucherInRecepictsVAT() throws InterruptedException 
	{
		Thread.sleep(3000);

		click(financialsMenu);

		click(financialsTransactionMenu);

		click(cashAndBankMenu);

		click(recepitsVATVoucher);

		Thread.sleep(2000);

		voucherHomePageVoucherSelect("1");

		Thread.sleep(2000);

		String actRow1 = listOfElements(entryPageRow1List);
		String expRow1 = "[1, Customer A, Std Rate, 1000.00, NDT55:1 : " + getCurrentdateDayFormat()+", 47.62]";

		System.err.println("  ACT Row1 List: " + actRow1);
		System.err.println("  EXP Row1 List: " + expRow1);

		Thread.sleep(2000);

		click(nextBtn);

		Thread.sleep(2000);

		String actRow1Vou2 = listOfElements(entryPageRow1List);
		String expRow1Vou2 = "[1, Customer A, Std Rate, 100.00, New Reference, 4.76]";

		System.err.println("  ACT Row1 List: " + actRow1Vou2);
		System.err.println("  EXP Row1 List: " + expRow1Vou2);

		Thread.sleep(2000);

		if (actRow1.equalsIgnoreCase(expRow1) && actRow1Vou2.equalsIgnoreCase(expRow1Vou2)) {
			System.err.println(" Test pasS: receipcts VAT Displayed As Expected");
			return true;
		} else {

			System.err.println(" Test FAIL: receipcts VAT Displayed As Expected");
			return false;
		}

	}

	public boolean checkSavingSalesInvoiceVATWithCustomers()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException {

		Thread.sleep(2000);

		// re_LunchBrowser();

		Thread.sleep(2000);

		eraseAllTransactions();

/*		logout();

		Thread.sleep(2567);

		checkLoginToManualAdjsuments();*/
		Thread.sleep(4000);

		//waitOn(homeMenu);
		
		
		click(financialsMenu);

		click(financialsTransactionMenu);

		click(financialTransactionSalesMenu);

		click(salesInvoiceVATVoucher);

		Thread.sleep(4000);

		click(newBtn);

		Thread.sleep(2000);

		boolean actSavingVoucher1 = CheckSavingSalesInvoiceVATWithDATE(0, "Customer A", "DUBAI", "HYDERABAD", "1",
				"100");
		System.err.println(" Saving Voucher 1:" + actSavingVoucher1);
		Thread.sleep(2000);

		boolean actSavingVoucher2 = CheckSavingSalesInvoiceVATWithDATE(0, "Customer A", "DUBAI", "HYDERABAD", "1",
				"200");
		System.err.println(" Saving Voucher 2:" + actSavingVoucher2);
		Thread.sleep(2000);

		boolean actSavingVoucher3 = CheckSavingSalesInvoiceVATWithDATE(0, "Customer A", "DUBAI", "HYDERABAD", "1",
				"300");
		System.err.println(" Saving Voucher 3:" + actSavingVoucher3);
		Thread.sleep(2000);

		boolean actSavingVoucher4 = CheckSavingSalesInvoiceVATWithDATE(0, "Customer b", "DUBAI", "HYDERABAD", "1",
				"100");
		System.err.println(" Saving Voucher 4:" + actSavingVoucher4);
		Thread.sleep(2000);

		boolean actSavingVoucher5 = CheckSavingSalesInvoiceVATWithDATE(0, "Customer b", "DUBAI", "HYDERABAD", "1",
				"200");
		System.err.println(" Saving Voucher 5:" + actSavingVoucher5);
		Thread.sleep(2000);

		boolean actSavingVoucher6 = CheckSavingSalesInvoiceVATWithDATE(0, "Customer b", "DUBAI", "HYDERABAD", "1",
				"300");
		System.err.println(" Saving Voucher 6:" + actSavingVoucher6);
		Thread.sleep(2000);

		boolean actSavingVoucher7 = CheckSavingSalesInvoiceVATWithDATE(0, "Customer c", "DUBAI", "HYDERABAD", "1",
				"100");
		System.err.println(" Saving Voucher 7:" + actSavingVoucher7);
		Thread.sleep(2000);

		boolean actSavingVoucher8 = CheckSavingSalesInvoiceVATWithDATE(0, "Customer c", "DUBAI", "HYDERABAD", "1",
				"200");
		System.err.println(" Saving Voucher 8:" + actSavingVoucher8);
		Thread.sleep(2000);

		boolean actSavingVoucher9 = CheckSavingSalesInvoiceVATWithDATE(0, "Customer c", "DUBAI", "HYDERABAD", "1",
				"300");
		System.err.println(" Saving Voucher 9:" + actSavingVoucher9);
		Thread.sleep(2000);

		boolean actSavingVoucher10 = CheckSavingSalesInvoiceVATWithDATE(0, "Customer New Reference", "DUBAI",
				"HYDERABAD", "1", "100");
		System.err.println(" Saving Voucher 10:" + actSavingVoucher10);
		Thread.sleep(2000);

		boolean actSavingVoucher11 = CheckSavingSalesInvoiceVATWithDATE(0, "Customer New Reference", "DUBAI",
				"HYDERABAD", "1", "200");
		System.err.println(" Saving Voucher 11:" + actSavingVoucher11);
		Thread.sleep(2000);

		boolean actSavingVoucher12 = CheckSavingSalesInvoiceVATWithDATE(0, "Customer New Reference", "DUBAI",
				"HYDERABAD", "1", "300");
		System.err.println(" Saving Voucher 12:" + actSavingVoucher12);
		Thread.sleep(2000);

		if ((actSavingVoucher12 && actSavingVoucher1 && actSavingVoucher2 && actSavingVoucher3 && actSavingVoucher4
				&& actSavingVoucher5 && actSavingVoucher6 && actSavingVoucher7 && actSavingVoucher8 && actSavingVoucher9
				&& actSavingVoucher10 && actSavingVoucher11) == true) {
			System.out.println(" *******************************Test Pass: 12 Vouchers Saved in sales Invoice VAT");
			return true;
		} else {
			System.err.println(" ********************************Test FAIL: 12 Vouchers Saved in sales Invoice VAT");
			return false;

		}

	}

	public boolean checkSavingRecepictsVAtWithCustomerA()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException 
	{

		Thread.sleep(2000);
		click(financialsMenu);

		click(financialsTransactionMenu);

		click(cashAndBankMenu);

		click(recepitsVATVoucher);

		Thread.sleep(4000);

		click(newBtn);

		Thread.sleep(2000);

		waitForElement(newCashBankAccountTxt);
		newCashBankAccountTxt.click();

		newCashBankAccountTxt.sendKeys(Keys.SPACE);

		int cashAndBAnkAccountListCount = cashAndBAnkAccountList.size();

		System.err.println("cashAndBAnkAccountListCount   : " + cashAndBAnkAccountListCount);

		for (int i = 0; i < cashAndBAnkAccountListCount; i++) {
			String data = cashAndBAnkAccountList.get(i).getText();

			if (data.equalsIgnoreCase("Bank")) {
				cashAndBAnkAccountList.get(i).click();

				break;
			}
		}

		newCashBankAccountTxt.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(departmentTxt));

		departmentTxt.click();
		departmentTxt.sendKeys(Keys.SHIFT, Keys.HOME, Keys.BACK_SPACE);
		departmentTxt.sendKeys(Keys.SPACE);
		Thread.sleep(2000);
		int departmentcount = departmentListCount.size();

		System.err.println(departmentcount);

		for (int i = 0; i < departmentcount; i++) {
			String data = departmentListCount.get(i).getText();

			if (data.equalsIgnoreCase("DUBAI")) {
				departmentListCount.get(i).click();
				break;
			}
		}

		Thread.sleep(1000);

		departmentTxt.sendKeys(Keys.TAB);

		Thread.sleep(2500);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(placeofSupplyTxt));
		placeofSupplyTxt.click();
		placeofSupplyTxt.sendKeys(Keys.SPACE);

		int placeOfSupplyListCount = placeofSupplyList.size();

		System.err.println(placeOfSupplyListCount);

		for (int i = 0; i < placeOfSupplyListCount; i++) {
			String data = placeofSupplyList.get(i).getText();

			if (data.equalsIgnoreCase("Abu Dhabi")) {
				placeofSupplyList.get(i).click();

				break;
			}
		}

		placeofSupplyTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_1stColumn));
		select1stRow_1stColumn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_AccountTxt));
		enter_AccountTxt.click();
		enter_AccountTxt.sendKeys("Customer A");

		Thread.sleep(2000);

		enter_AccountTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enterReceiptsVATTaxCode));
		removetTxt(enterReceiptsVATTaxCode);
		enterReceiptsVATTaxCode.sendKeys("STD");
		Thread.sleep(2000);
		enterReceiptsVATTaxCode.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_Amount));
		enter_Amount.sendKeys("150");
		enter_Amount.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		waitForElement(billRefPartyName);

		int Adjustbills = billRefAdjustBillsGrid.size();

		String actAdjustbills = Integer.toString(Adjustbills);

		String expAdjustbills = "3";

		System.err.println("actAdjustbills : " + actAdjustbills + " Value Expected  : " + expAdjustbills);

		Thread.sleep(2000);

		String actBillsDisplayed = listOfElements(billwiseAdjustBillsDocList);
		String expBillsDisplayed = "[NDT55:1, NDT55:2, NDT55:3]";

		System.err.println(" Act BillsAdjust Displayed: " + actBillsDisplayed);
		System.err.println(" Exp BillsAdjust Displayed: " + expBillsDisplayed);

		Thread.sleep(2000);
		click(billrefAdjuBills1stChkbox);

		Thread.sleep(2000);
		click(billrefAdjuBills2ndChkbox);

		click(billRefOkBtn);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNumberTxt));
		String docno = documentNumberTxt.getAttribute("value");

		click(voucherSaveBtn);

		boolean actSaving = checkVoucherSavingMessage(docno);
		boolean expSaving = true;

		System.err.println(" Saving voucher: " + actSaving + " Value Exp: " + expSaving);

		if (actSaving == expSaving && actAdjustbills.equalsIgnoreCase(expAdjustbills)
				&& actBillsDisplayed.equalsIgnoreCase(expBillsDisplayed))

		{
			System.err.println("Test Pass: Recepits VAT Voucher Saved  ");
			return true;
		} else {
			System.err.println("Test FAIL: Recepits VAT Voucher Saved  ");
			return false;
		}
	}

	@FindBy(xpath = "//*[@id='id_transactionentry_previous']")
	public static WebElement previousPageBtn;

	public boolean checkEditingVoucherInRecepictsVAT()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException 
	{

		Thread.sleep(2000);

		click(previousBtn);

		Thread.sleep(2000);
		waitForElement(select1stRow_3rdColumn);
		click(select1stRow_3rdColumn);
		removetTxt(enter_Amount);
		enter_Amount.sendKeys("600");
		Thread.sleep(2000);

		enter_Amount.sendKeys(Keys.TAB);

		if (billrefAdjuBills1stChkbox.isSelected()) {

			// Uncheck
			Thread.sleep(2000);
			click(billrefAdjuBills1stChkbox);

			Thread.sleep(2000);
			click(billrefAdjuBills2ndChkbox);
		}

		// check
		Thread.sleep(2000);
		click(billrefAdjuBills1stChkbox);

		Thread.sleep(2000);
		click(billrefAdjuBills2ndChkbox);

		Thread.sleep(2000);
		click(billrefAdjuBills3rdChkbox);

		Thread.sleep(2000);
		String actRow1 = listOfElements(billRefRow1List);
		String expRow1 = "[1, NDT55:1, " + FilterCurrentDate(0) + ", " + FilterCurrentDate(0)
				+ ", ₹, 100.00, 100.00, 100.00, 100.00, 0.00]";

		System.err.println(" ACT Row1: " + actRow1);
		System.err.println(" EXP Row1: " + expRow1);

		String actRow2 = listOfElements(billRefRow2List);
		String expRow2 = "[2, NDT55:2, " + FilterCurrentDate(0) + ", " + FilterCurrentDate(0)
				+ ", ₹, 200.00, 200.00, 200.00, 200.00, 0.00]";

		System.err.println(" ACT Row2: " + actRow2);
		System.err.println(" EXP Row2: " + expRow2);

		String actRow3 = listOfElements(billRefRow3List);
		String expRow3 = "[3, NDT55:3, " + FilterCurrentDate(0) + ", " + FilterCurrentDate(0)
				+ ", ₹, 300.00, 300.00, 300.00, 300.00, 0.00]";

		System.err.println(" ACT Row3: " + actRow3);
		System.err.println(" EXP Row3: " + expRow3);

		click(billRefOkBtn);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNumberTxt));
		String docno = documentNumberTxt.getAttribute("value");

		click(voucherSaveBtn);

		boolean actSaving = checkVoucherSavingMessage(docno);
		boolean expSaving = true;

		System.err.println(" Saving voucher: " + actSaving + " Value Exp: " + expSaving);

		if (actRow1.equalsIgnoreCase(expRow1) && actRow2.equalsIgnoreCase(expRow2)
				&& actRow3.equalsIgnoreCase(expRow3)
				&& actSaving == expSaving) 
		{

			return true;
		} else {

			return false;
		}

	}

	public boolean checkAdjustingCustomerBInRecepictsVAT()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException 
	{

		Thread.sleep(2000);
		click(financialsMenu);

		click(financialsTransactionMenu);

		click(cashAndBankMenu);

		click(recepitsVATVoucher);

		Thread.sleep(2000);

		waitToClick(newBtn);

		Thread.sleep(2000);

		waitForElement(newCashBankAccountTxt);
		newCashBankAccountTxt.click();

		newCashBankAccountTxt.sendKeys(Keys.SPACE);

		int cashAndBAnkAccountListCount = cashAndBAnkAccountList.size();

		System.err.println("cashAndBAnkAccountListCount   : " + cashAndBAnkAccountListCount);

		for (int i = 0; i < cashAndBAnkAccountListCount; i++) {
			String data = cashAndBAnkAccountList.get(i).getText();

			if (data.equalsIgnoreCase("Bank")) {
				cashAndBAnkAccountList.get(i).click();

				break;
			}
		}

		newCashBankAccountTxt.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(departmentTxt));

		departmentTxt.click();
		departmentTxt.sendKeys(Keys.SHIFT, Keys.HOME, Keys.BACK_SPACE);
		departmentTxt.sendKeys(Keys.SPACE);
		Thread.sleep(2000);
		int departmentcount = departmentListCount.size();

		System.err.println(departmentcount);

		for (int i = 0; i < departmentcount; i++) {
			String data = departmentListCount.get(i).getText();

			if (data.equalsIgnoreCase("DUBAI")) 
			{
				departmentListCount.get(i).click();
				break;
			}
		}

		Thread.sleep(1000);

		departmentTxt.sendKeys(Keys.TAB);

		Thread.sleep(2500);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(placeofSupplyTxt));
		placeofSupplyTxt.click();
		placeofSupplyTxt.sendKeys(Keys.SPACE);

		int placeOfSupplyListCount = placeofSupplyList.size();

		System.err.println(placeOfSupplyListCount);

		for (int i = 0; i < placeOfSupplyListCount; i++) {
			String data = placeofSupplyList.get(i).getText();

			if (data.equalsIgnoreCase("Abu Dhabi")) 
			{
				placeofSupplyList.get(i).click();

				break;
			}
		}

		placeofSupplyTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_1stColumn));
		select1stRow_1stColumn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_AccountTxt));
		enter_AccountTxt.click();
		enter_AccountTxt.sendKeys("Customer B");

		Thread.sleep(2000);

		enter_AccountTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enterReceiptsVATTaxCode));
		removetTxt(enterReceiptsVATTaxCode);
		enterReceiptsVATTaxCode.sendKeys("STD");
		Thread.sleep(2000);
		enterReceiptsVATTaxCode.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		removetTxt(enter_Amount);
		Thread.sleep(800);
		enter_Amount.sendKeys("600");
		Thread.sleep(800);
		enter_Amount.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		waitForElement(billRefPartyName);

		int Adjustbills = billRefAdjustBillsGrid.size();

		String actAdjustbills = Integer.toString(Adjustbills);

		String expAdjustbills = "3";

		System.err.println("actAdjustbills : " + actAdjustbills + " Value Expected  : " + expAdjustbills);

		Thread.sleep(2000);

		String actBillsDisplayed = listOfElements(billwiseAdjustBillsDocList);
		String expBillsDisplayed = "[NDT55:4, NDT55:5, NDT55:6]";

		System.err.println(" Act BillsAdjust Displayed: " + actBillsDisplayed);
		System.err.println(" Exp BillsAdjust Displayed: " + expBillsDisplayed);

		Thread.sleep(2000);
		click(billrefAdjuBills1stChkbox);

		Thread.sleep(2000);
		click(billrefAdjuBills2ndChkbox);

		Thread.sleep(2000);
		click(billrefAdjuBills3rdChkbox);

		click(billRefOkBtn);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNumberTxt));
		String docno = documentNumberTxt.getAttribute("value");

		click(voucherSaveBtn);

		boolean actSaving = checkVoucherSavingMessage(docno);
		boolean expSaving = true;

		System.err.println(" Saving voucher: " + actSaving + " Value Exp: " + expSaving);

		Thread.sleep(3563);

		click(previousPageBtn);

		Thread.sleep(2000);

		waitForElement(select1stRow_1stColumn);

		String actRow1 = listOfElements(entryPageRow1List);
		String expRow1 = "[1, Customer B, Std Rate, 600.00, NDT55:4 : " + getCurrentdateDayFormat() + ";NDT55:5 : "
				+ getCurrentdateDayFormat() + ";NDT55:6 : " + getCurrentdateDayFormat() + ", 0.00]";

		System.err.println(" ACT Row3: " + actRow1);
		System.err.println(" EXP Row3: " + expRow1);

		Thread.sleep(2000);

		click(new_CloseBtn);

		if (actSaving == expSaving && actAdjustbills.equalsIgnoreCase(expAdjustbills)
				&& actBillsDisplayed.equalsIgnoreCase(expBillsDisplayed) && actRow1.equalsIgnoreCase(expRow1))

		{
			System.err.println("Test Pass: Recepits VAT Voucher Saved  ");
			return true;
		} else {
			System.err.println("Test FAIL: Recepits VAT Voucher Saved  ");
			return false;
		}
	}

	public boolean checkSavingVoucherInRecepictsVATWithCustomerCReference()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {

		Thread.sleep(2000);
		click(financialsMenu);

		click(financialsTransactionMenu);

		click(cashAndBankMenu);

		click(recepitsVATVoucher);

		Thread.sleep(2000);

		waitToClick(newBtn);

		Thread.sleep(2000);

		waitForElement(newCashBankAccountTxt);
		newCashBankAccountTxt.click();

		newCashBankAccountTxt.sendKeys(Keys.SPACE);

		int cashAndBAnkAccountListCount = cashAndBAnkAccountList.size();

		System.err.println("cashAndBAnkAccountListCount   : " + cashAndBAnkAccountListCount);

		for (int i = 0; i < cashAndBAnkAccountListCount; i++) {
			String data = cashAndBAnkAccountList.get(i).getText();

			if (data.equalsIgnoreCase("Bank")) {
				cashAndBAnkAccountList.get(i).click();

				break;
			}
		}

		newCashBankAccountTxt.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(departmentTxt));

		departmentTxt.click();
		departmentTxt.sendKeys(Keys.SHIFT, Keys.HOME, Keys.BACK_SPACE);
		departmentTxt.sendKeys(Keys.SPACE);
		Thread.sleep(2000);
		int departmentcount = departmentListCount.size();

		System.err.println(departmentcount);

		for (int i = 0; i < departmentcount; i++) {
			String data = departmentListCount.get(i).getText();

			if (data.equalsIgnoreCase("DUBAI")) {
				departmentListCount.get(i).click();
				break;
			}
		}

		Thread.sleep(1000);

		departmentTxt.sendKeys(Keys.TAB);

		Thread.sleep(2500);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(placeofSupplyTxt));
		placeofSupplyTxt.click();
		placeofSupplyTxt.sendKeys(Keys.SPACE);

		int placeOfSupplyListCount = placeofSupplyList.size();

		System.err.println(placeOfSupplyListCount);

		for (int i = 0; i < placeOfSupplyListCount; i++) {
			String data = placeofSupplyList.get(i).getText();

			if (data.equalsIgnoreCase("Abu Dhabi")) {
				placeofSupplyList.get(i).click();

				break;
			}
		}

		placeofSupplyTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_1stColumn));
		select1stRow_1stColumn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_AccountTxt));
		enter_AccountTxt.click();
		enter_AccountTxt.sendKeys("Customer C");

		Thread.sleep(2000);

		enter_AccountTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enterReceiptsVATTaxCode));
		removetTxt(enterReceiptsVATTaxCode);
		enterReceiptsVATTaxCode.sendKeys("STD");
		Thread.sleep(2000);
		enterReceiptsVATTaxCode.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_Amount));
		enter_Amount.sendKeys("600");
		enter_Amount.sendKeys(Keys.TAB);

		billwisePick();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNumberTxt));
		String docno = documentNumberTxt.getAttribute("value");

		click(voucherSaveBtn);

		boolean actSaving = checkVoucherSavingMessage(docno);
		boolean expSaving = true;

		System.err.println(" Saving voucher: " + actSaving + " Value Exp: " + expSaving);

		click(previousPageBtn);

		Thread.sleep(2000);

		waitForElement(select1stRow_1stColumn);

		String actRow1 = listOfElements(entryPageRow1List);
		String expRow1 = "[1, Customer C, Std Rate, 600.00, New Reference, 28.57]";

		System.err.println(" ACT Row3: " + actRow1);
		System.err.println(" EXP Row3: " + expRow1);

		Thread.sleep(2000);

		click(new_CloseBtn);

		if (actSaving == expSaving && actRow1.equalsIgnoreCase(expRow1))

		{
			System.err.println("Test Pass: Recepits VAT Voucher Saved  ");
			return true;
		} else {
			System.err.println("Test FAIL: Recepits VAT Voucher Saved  ");
			return false;
		}
	}

	@FindBy(xpath = "//*[@id='id_DrAdj_Grid_col_2-1']/input")
	private static WebElement debitRow2Chkbox;

	@FindBy(xpath = "//*[@id='id_DrAdj_Grid_col_3-1']/input")
	private static WebElement debitRow3Chkbox;

	public boolean checkManaulAdjsutmentWithCustomerC()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		Thread.sleep(2000);

		click(homeMenu);

		click(utilitesMenu);

		Thread.sleep(1999);

		ClickUsingJs(ManualAdjustemntMenu);

		Thread.sleep(1999);

		waitForElement(MA_ARAPDrpdwn);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_ARAPDrpdwn));
		Select ARAP = new Select(MA_ARAPDrpdwn);
		ARAP.selectByValue("0");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_AccountDrpdwn));
		MA_AccountDrpdwn.sendKeys("Customer C");
		Thread.sleep(1999);
		MA_AccountDrpdwn.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_DepDrpDwn));
		MA_DepDrpDwn.sendKeys("DUBAI");
		Thread.sleep(1999);
		MA_DepDrpDwn.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_CurrencyDrpDwn));
		MA_CurrencyDrpDwn.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME);
		MA_CurrencyDrpDwn.sendKeys("INR");
		Thread.sleep(1999);
		MA_CurrencyDrpDwn.sendKeys(Keys.TAB);

		Thread.sleep(1000);
		
		click(filterCollapseBtn);
		Thread.sleep(1500);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_LoadBtn));
		ClickUsingJs(MA_LoadBtn);

		Thread.sleep(5999);// dateF9

		String actDebitSideList = listOfElements(MA_DebitSideRow1list);
		String expDebitSideList = "[1, NDT55:7, " + dateF9() + ", " + dateF9()
				+ ", ₹, 100.00, 100.00, 0.00, 0.00, 0.00, 0.00, 0.00]";

		System.err.println(" Actual Debit List  : " + actDebitSideList);
		System.err.println(" Exp    Debit List  : " + expDebitSideList);

		String actDebitSideList2 = listOfElements(MA_DebitSideRow2list);
		String expDebitSideList2 = "[2, NDT55:8, " + dateF9() + ", " + dateF9()
				+ ", ₹, 200.00, 200.00, 0.00, 0.00, 0.00, 0.00, 0.00]";

		System.err.println(" Actual Debit List2  : " + actDebitSideList2);
		System.err.println(" Exp    Debit List2  : " + expDebitSideList2);

		String actDebitSideList3 = listOfElements(MA_DebitSideRow3list);
		String expDebitSideList3 = "[3, NDT55:9, " + dateF9() + ", " + dateF9()
				+ ", ₹, 300.00, 300.00, 0.00, 0.00, 0.00, 0.00, 0.00]";

		System.err.println(" Actual Debit List 3 : " + actDebitSideList3);
		System.err.println(" Exp    Debit List 3: " + expDebitSideList3);

		Thread.sleep(1999);

		String actCreditSideList = listOfElements(MA_CreditSideRow1list);
		String expCreditSideList = "[1, NDT57:3, " + dateF9() + ", " + dateF9()
				+ ", ₹, 600.00, 600.00, 0.00, 0.00, 0.00, 0.00, 0.00]";

		System.err.println(" Actual Credit List  : " + actCreditSideList);
		System.err.println(" Exp    Credit List  : " + expCreditSideList);

		Thread.sleep(2000);

		click(debitRow1Chkbox);
		click(debitRow2Chkbox);
		click(debitRow3Chkbox);

		Thread.sleep(2000);

		click(CreditRow1Chkbox);

		Thread.sleep(2000);

		String actdebitTotal = debitTotal.getText();
		String expdebitTotal = "600.00";

		System.err.println(" debitTotal : " + actdebitTotal + " Value  Exp: " + expdebitTotal);

		Thread.sleep(2000);

		String actcreditTotal = creditTotal.getText();
		String expcreditTotal = "600.00";

		System.err.println(" creditTotal : " + actcreditTotal + " Value  Exp: " + expcreditTotal);

		Thread.sleep(2000);

		click(MA_OkBtn);

		String expMessage = "Record Saved Succesfully";
		String actMessage = checkValidationMessage(expMessage);

		System.err.println(" Message ACT: " + actMessage);
		System.err.println(" Message EXP: " + expMessage);

		if (actCreditSideList.equalsIgnoreCase(expCreditSideList) && actDebitSideList.equalsIgnoreCase(expDebitSideList)
				&& actdebitTotal.equalsIgnoreCase(expdebitTotal) && actcreditTotal.equalsIgnoreCase(expcreditTotal)
				&& actMessage.equalsIgnoreCase(expMessage)) {
			return true;
		} else {
			return false;
		}

	}

	public boolean checkRececpictsVATVoucherAfterAdjsutmentInManualScreen()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {

		Thread.sleep(2000);
		click(financialsMenu);

		click(financialsTransactionMenu);

		click(cashAndBankMenu);

		click(recepitsVATVoucher);

		Thread.sleep(4000);

		//click(newBtn);

		voucherHomePageVoucherSelect("3");

		Thread.sleep(2000);

		waitForElement(select1stRow_1stColumn);

		String actRow1 = listOfElements(entryPageRow1List);
		String expRow1 = "[1, Customer C, Std Rate, 600.00, NDT55:7 : " + getCurrentdateDayFormat() + ";NDT55:8 : "
				+ getCurrentdateDayFormat() + ";NDT55:9 : " + getCurrentdateDayFormat() + ", 28.57]";

		System.err.println(" ACT Row3: " + actRow1);
		System.err.println(" EXP Row3: " + expRow1);

		Thread.sleep(2000);

		click(previousPageBtn);

		Thread.sleep(2000);

		waitForElement(select1stRow_1stColumn);

		String actVoucher2Row1 = listOfElements(entryPageRow1List);
		String expVoucher2Row1 = "[1, Customer B, Std Rate, 600.00, NDT55:4 : " + getCurrentdateDayFormat()
				+ ";NDT55:5 : " + getCurrentdateDayFormat() + ";NDT55:6 : " + getCurrentdateDayFormat() + ", 0.00]";

		System.err.println(" ACT Row3: " + actVoucher2Row1);
		System.err.println(" EXP Row3: " + expVoucher2Row1);

		click(new_CloseBtn);

		if (actRow1.equalsIgnoreCase(expRow1) && actVoucher2Row1.equalsIgnoreCase(expVoucher2Row1))

		{
			System.err.println("Test Pass: Recepits VAT Voucher Saved  ");
			return true;
		} else {
			System.err.println("Test FAIL: Recepits VAT Voucher Saved  ");
			return false;
		}

	}

	public boolean checkSavingRecipctsVATWithCustomerNewreference()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {

		Thread.sleep(2000);

		click(financialsMenu);

		click(financialsTransactionMenu);

		click(cashAndBankMenu);

		click(recepitsVATVoucher);

		Thread.sleep(2000);

		waitToClick(newBtn);

		Thread.sleep(2000);

		waitForElement(newCashBankAccountTxt);
		newCashBankAccountTxt.click();

		newCashBankAccountTxt.sendKeys(Keys.SPACE);

		int cashAndBAnkAccountListCount = cashAndBAnkAccountList.size();

		System.err.println("cashAndBAnkAccountListCount   : " + cashAndBAnkAccountListCount);

		for (int i = 0; i < cashAndBAnkAccountListCount; i++) {
			String data = cashAndBAnkAccountList.get(i).getText();

			if (data.equalsIgnoreCase("Bank")) {
				cashAndBAnkAccountList.get(i).click();

				break;
			}
		}

		newCashBankAccountTxt.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(departmentTxt));

		departmentTxt.click();
		departmentTxt.sendKeys(Keys.SHIFT, Keys.HOME, Keys.BACK_SPACE);
		departmentTxt.sendKeys(Keys.SPACE);
		Thread.sleep(2000);
		int departmentcount = departmentListCount.size();

		System.err.println(departmentcount);

		for (int i = 0; i < departmentcount; i++) {
			String data = departmentListCount.get(i).getText();

			if (data.equalsIgnoreCase("DUBAI")) {
				departmentListCount.get(i).click();
				break;
			}
		}

		Thread.sleep(1000);

		departmentTxt.sendKeys(Keys.TAB);

		Thread.sleep(2500);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(placeofSupplyTxt));
		placeofSupplyTxt.click();
		placeofSupplyTxt.sendKeys(Keys.SPACE);

		int placeOfSupplyListCount = placeofSupplyList.size();

		System.err.println(placeOfSupplyListCount);

		for (int i = 0; i < placeOfSupplyListCount; i++) {
			String data = placeofSupplyList.get(i).getText();

			if (data.equalsIgnoreCase("Abu Dhabi")) {
				placeofSupplyList.get(i).click();

				break;
			}
		}

		placeofSupplyTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_1stColumn));
		select1stRow_1stColumn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_AccountTxt));
		enter_AccountTxt.click();
		enter_AccountTxt.sendKeys("Customer New Reference");

		Thread.sleep(2000);
		enter_AccountTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enterReceiptsVATTaxCode));
		removetTxt(enterReceiptsVATTaxCode);
		enterReceiptsVATTaxCode.sendKeys("STD");
		Thread.sleep(2000);
		enterReceiptsVATTaxCode.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_Amount));
		enter_Amount.sendKeys("150");
		enter_Amount.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		click(billrefAdjuBills1stChkbox);

		click(billRefPickIcon);

		Thread.sleep(2000);

		click(billRefNewReferenceTxt);

		click(billRefPickIcon);

		Thread.sleep(2000);

		click(billRefOkBtn);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNumberTxt));
		String docno = documentNumberTxt.getAttribute("value");

		click(voucherSaveBtn);

		boolean actSaving = checkVoucherSavingMessage(docno);
		boolean expSaving = true;

		System.err.println(" Saving voucher: " + actSaving + " Value Exp: " + expSaving);

		click(previousPageBtn);

		Thread.sleep(2000);

		waitForElement(select1stRow_1stColumn);

		String actRow1 = listOfElements(entryPageRow1List);
		String expRow1 = "[1, Customer New Reference, Std Rate, 50.00, New Reference, 2.38]";

		System.err.println(" ACT Row3: " + actRow1);
		System.err.println(" EXP Row3: " + expRow1);

		String actRow2 = listOfElements(entryPageRow2List);
		String expRow2 = "[2, Customer New Reference, Std Rate, 100.00, NDT55:10 : " + getCurrentdateDayFormat()
				+ ", 0.00]";

		System.err.println(" ACT Row3: " + actRow2);
		System.err.println(" EXP Row3: " + expRow2);

		Thread.sleep(2000);

		click(new_CloseBtn);

		if (actSaving == expSaving && actRow2.equalsIgnoreCase(expRow2) && actRow1.equalsIgnoreCase(expRow1))

		{
			System.err.println("Test Pass: Recepits VAT Voucher Saved  ");
			return true;
		} else {
			System.err.println("Test FAIL: Recepits VAT Voucher Saved  ");
			return false;
		}
	}

	public boolean checkManualAdjsutmentWithCustomerNewreferenceAcc()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {

		Thread.sleep(2000);

		click(homeMenu);

		click(utilitesMenu);

		Thread.sleep(1999);

		ClickUsingJs(ManualAdjustemntMenu);

		Thread.sleep(1999);

		waitForElement(MA_ARAPDrpdwn);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_ARAPDrpdwn));
		Select ARAP = new Select(MA_ARAPDrpdwn);
		ARAP.selectByValue("0");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_AccountDrpdwn));
		MA_AccountDrpdwn.sendKeys("Customer New Reference");
		Thread.sleep(1999);
		MA_AccountDrpdwn.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_DepDrpDwn));
		MA_DepDrpDwn.sendKeys("DUBAI");
		Thread.sleep(1999);
		MA_DepDrpDwn.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_CurrencyDrpDwn));
		MA_CurrencyDrpDwn.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME);
		MA_CurrencyDrpDwn.sendKeys("INR");
		Thread.sleep(1999);
		MA_CurrencyDrpDwn.sendKeys(Keys.TAB);

		Thread.sleep(1000);
		
		click(filterCollapseBtn);
		Thread.sleep(1500);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_LoadBtn));
		ClickUsingJs(MA_LoadBtn);

		Thread.sleep(5999);// dateF9

		String actDebitSideList = listOfElements(MA_DebitSideRow1list);
		String expDebitSideList = "[1, NDT55:11, " + dateF9() + ", " + dateF9()
				+ ", ₹, 200.00, 200.00, 0.00, 0.00, 0.00, 0.00, 0.00]";

		System.err.println(" Actual Debit List  : " + actDebitSideList);
		System.err.println(" Exp    Debit List  : " + expDebitSideList);

		String actDebitSideList2 = listOfElements(MA_DebitSideRow2list);
		String expDebitSideList2 = "[2, NDT55:12, " + dateF9() + ", " + dateF9()
				+ ", ₹, 300.00, 300.00, 0.00, 0.00, 0.00, 0.00, 0.00]";

		System.err.println(" Actual Debit List2  : " + actDebitSideList2);
		System.err.println(" Exp    Debit List2  : " + expDebitSideList2);

		Thread.sleep(1999);

		String actCreditSideList = listOfElements(MA_CreditSideRow1list);
		String expCreditSideList = "[1, NDT57:4, " + dateF9() + ", " + dateF9()
				+ ", ₹, 50.00, 50.00, 0.00, 0.00, 0.00, 0.00, 0.00]";

		System.err.println(" Actual Credit List  : " + actCreditSideList);
		System.err.println(" Exp    Credit List  : " + expCreditSideList);

		Thread.sleep(2000);

		click(debitRow2Chkbox);

		Thread.sleep(2000);

		click(CreditRow1Chkbox);

		Thread.sleep(2000);

		String actdebitTotal = debitTotal.getText();
		String expdebitTotal = "300.00";

		System.err.println(" debitTotal : " + actdebitTotal + " Value  Exp: " + expdebitTotal);

		Thread.sleep(2000);

		String actcreditTotal = creditTotal.getText();
		String expcreditTotal = "50.00";

		System.err.println(" creditTotal : " + actcreditTotal + " Value  Exp: " + expcreditTotal);

		Thread.sleep(2000);

		click(MA_OkBtn);

		String expMessage = "Record Saved Succesfully";
		String actMessage = checkValidationMessage(expMessage);

		System.err.println(" Message ACT: " + actMessage);
		System.err.println(" Message EXP: " + expMessage);

		if (actCreditSideList.equalsIgnoreCase(expCreditSideList) && actDebitSideList.equalsIgnoreCase(expDebitSideList)
				&& actdebitTotal.equalsIgnoreCase(expdebitTotal) && actcreditTotal.equalsIgnoreCase(expcreditTotal)
				&& actMessage.equalsIgnoreCase(expMessage)) {
			return true;
		} else {
			return false;
		}

	}

	public boolean checkAdjustedVoucherInReceipctsVAT() throws InterruptedException {

		Thread.sleep(2000);
		click(financialsMenu);

		click(financialsTransactionMenu);

		click(cashAndBankMenu);

		click(recepitsVATVoucher);

		Thread.sleep(4000);

		//waitToClick(newBtn);

		voucherHomePageVoucherSelect("4");

		Thread.sleep(2000);

		waitForElement(select1stRow_1stColumn);

		String actRow1 = listOfElements(entryPageRow1List);
		String expRow1 = "[1, Customer New Reference, Std Rate, 50.00, NDT55:12 : " + getCurrentdateDayFormat()
				+ ", 2.38]";

		System.err.println(" ACT Row1: " + actRow1);
		System.err.println(" EXP Row1: " + expRow1);

		String actVoucher2Row1 = listOfElements(entryPageRow2List);
		String expVoucher2Row1 = "[2, Customer New Reference, Std Rate, 100.00, NDT55:10 : " + getCurrentdateDayFormat()
				+ ", 0.00]";

		System.err.println(" ACT Row2: " + actVoucher2Row1);
		System.err.println(" EXP Row2: " + expVoucher2Row1);

		click(new_CloseBtn);

		if (actRow1.equalsIgnoreCase(expRow1) && actVoucher2Row1.equalsIgnoreCase(expVoucher2Row1))

		{
			System.err.println("Test Pass: Recepits VAT Voucher Saved  ");
			return true;
		} else {
			System.err.println("Test FAIL: Recepits VAT Voucher Saved  ");
			return false;
		}

	}

	public boolean checkSavingRecepictsVATWithCustomerNewref()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		Thread.sleep(2000);

		click(financialsMenu);

		click(financialsTransactionMenu);

		click(cashAndBankMenu);

		click(recepitsVATVoucher);

		Thread.sleep(2000);

		waitToClick(newBtn);
		Thread.sleep(2000);

		waitForElement(newCashBankAccountTxt);
		newCashBankAccountTxt.click();

		newCashBankAccountTxt.sendKeys(Keys.SPACE);

		int cashAndBAnkAccountListCount = cashAndBAnkAccountList.size();

		System.err.println("cashAndBAnkAccountListCount   : " + cashAndBAnkAccountListCount);

		for (int i = 0; i < cashAndBAnkAccountListCount; i++) {
			String data = cashAndBAnkAccountList.get(i).getText();

			if (data.equalsIgnoreCase("Bank")) {
				cashAndBAnkAccountList.get(i).click();

				break;
			}
		}

		newCashBankAccountTxt.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(departmentTxt));

		departmentTxt.click();
		departmentTxt.sendKeys(Keys.SHIFT, Keys.HOME, Keys.BACK_SPACE);
		departmentTxt.sendKeys(Keys.SPACE);
		Thread.sleep(2000);
		int departmentcount = departmentListCount.size();

		System.err.println(departmentcount);

		for (int i = 0; i < departmentcount; i++) {
			String data = departmentListCount.get(i).getText();

			if (data.equalsIgnoreCase("DUBAI")) {
				departmentListCount.get(i).click();
				break;
			}
		}

		Thread.sleep(1000);

		departmentTxt.sendKeys(Keys.TAB);

		Thread.sleep(2500);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(placeofSupplyTxt));
		placeofSupplyTxt.click();
		placeofSupplyTxt.sendKeys(Keys.SPACE);

		int placeOfSupplyListCount = placeofSupplyList.size();

		System.err.println(placeOfSupplyListCount);

		for (int i = 0; i < placeOfSupplyListCount; i++) {
			String data = placeofSupplyList.get(i).getText();

			if (data.equalsIgnoreCase("Abu Dhabi")) {
				placeofSupplyList.get(i).click();

				break;
			}
		}

		placeofSupplyTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_1stColumn));
		select1stRow_1stColumn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_AccountTxt));
		enter_AccountTxt.click();
		enter_AccountTxt.sendKeys("Customer New Reference");

		Thread.sleep(2000);
		enter_AccountTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enterReceiptsVATTaxCode));
		removetTxt(enterReceiptsVATTaxCode);
		enterReceiptsVATTaxCode.sendKeys("STD");
		Thread.sleep(2000);
		enterReceiptsVATTaxCode.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_Amount));
		enter_Amount.sendKeys("500");
		enter_Amount.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		waitForElement(billRefNewReferenceTxt);
		click(billRefNewReferenceTxt);

		click(billRefPickIcon);

		Thread.sleep(2000);

		click(billRefOkBtn);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNumberTxt));
		String docno = documentNumberTxt.getAttribute("value");

		click(voucherSaveBtn);

		boolean actSaving = checkVoucherSavingMessage(docno);
		boolean expSaving = true;

		System.err.println(" Saving voucher: " + actSaving + " Value Exp: " + expSaving);

		click(previousPageBtn);

		Thread.sleep(2000);

		waitForElement(select1stRow_1stColumn);

		String actRow1 = listOfElements(entryPageRow1List);
		String expRow1 = "[1, Customer New Reference, Std Rate, 500.00, New Reference, 23.81]";

		System.err.println(" ACT Row3: " + actRow1);
		System.err.println(" EXP Row3: " + expRow1);

		Thread.sleep(2000);

		click(new_CloseBtn);

		if (actSaving == expSaving && actRow1.equalsIgnoreCase(expRow1))

		{
			System.err.println("Test Pass: Recepits VAT Voucher Saved  ");
			return true;
		} else {
			System.err.println("Test FAIL: Recepits VAT Voucher Saved  ");
			return false;
		}
	}

	public boolean checkAutoAdjustScreenWithCustomerNewRef()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {

		click(focusMainSearch);
		focusMainSearch.sendKeys("Auto Adjust");
		Thread.sleep(500);
		focusMainSearch.sendKeys(Keys.ENTER);

		Thread.sleep(2500);

		Select s1 = new Select(autoAdju_TypeDrpdwn);
		s1.selectByValue("0");

		Thread.sleep(2500);

		String actType = s1.getFirstSelectedOption().getText();
		String expType = "Customer";

		System.err.println("autoAdju_TypeDrpdwn : " + actType + " Value Exp: " + expType);

		Thread.sleep(500);
		Select s2 = new Select(autoAdju_adjustmentDrpdwn);

		String actautoAdju_adjustmentDrpdwn = s2.getFirstSelectedOption().getText();
		String expautoAdju_adjustmentDrpdwn = "Auto Adjust On Fifo";

		System.err.println("autoAdju_adjustmentDrpdwn : " + actautoAdju_adjustmentDrpdwn + " Value Exp: "
				+ expautoAdju_adjustmentDrpdwn);

		click(autoAdju_ItemSearch);
		removetTxt(autoAdju_ItemSearch);
		autoAdju_ItemSearch.sendKeys("customer New reference");
		Thread.sleep(2000);
		autoAdju_ItemSearch.sendKeys(Keys.ENTER);

		Thread.sleep(2000);

		click(autoAdju_SelectALlMasters);
		Thread.sleep(2000);

		click(autoAdju_OKBtn);

		String expMessage = "auto adjust finished.";
		String actMessage = checkValidationMessage(expMessage);

		Thread.sleep(2000);

		if (actMessage.equalsIgnoreCase(expMessage) && actType.equalsIgnoreCase(expType)
				&& actautoAdju_adjustmentDrpdwn.equalsIgnoreCase(expautoAdju_adjustmentDrpdwn)) {
			return true;
		} else {

			return false;
		}

	}

	public boolean checkAdjsutedVoucherInReceiptsVAT()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		Thread.sleep(2000);

		click(financialsMenu);

		click(financialsTransactionMenu);

		click(cashAndBankMenu);

		click(recepitsVATVoucher);

		Thread.sleep(2000);

		waitToClick(newBtn);

		//checkValidationMessage("screen Opened");

		click(previousPageBtn);

		checkValidationMessage("Voucher loaded successfully");

		Thread.sleep(2000);

		String actRow1 = listOfElements(entryPageRow1List);
		String expRow1 = "[1, Customer New Reference, Std Rate, 500.00, NDT55:11 : " + getCurrentdateDayFormat()
				+ ";NDT55:12 : " + getCurrentdateDayFormat() + ";New Reference, 23.81]";

		System.err.println(" ACt Row1: " + actRow1);
		System.err.println(" EXp Row1: " + expRow1);

		Thread.sleep(2000);

		//logout();

		if (actRow1.equalsIgnoreCase(expRow1)) {
			return true;
		} else {

			return false;
		}
	}

	@FindBy(xpath = "(//span[text()='Preferences'])[2]")
	public static WebElement Setting_PerferenceMenu;

	public boolean checkChangingTahsInARAP()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException {

		Thread.sleep(2000);

		// re_LunchBrowser();

	/*	Thread.sleep(2000);

		// prongHornStopAtAdminLevel();

		Thread.sleep(2000);

		Thread.sleep(2000);

		checkLoginToSelectedCompany("ManualAdjustment", "su", "su");

		Thread.sleep(2000);*/

		eraseAllTransactions();

		Thread.sleep(2000);
/*
		logout();

		Thread.sleep(2000);
		checkLoginToSelectedCompany("ManualAdjustment", "su", "su");

		Thread.sleep(2000);*/

		getDriver().navigate().refresh();
		Thread.sleep(4500);
		
		ClickUsingJs(SettingsMenu);
		Thread.sleep(2000);
		click(Setting_PerferenceMenu);

		Thread.sleep(2000);
		click(ARAPBtn);

		Thread.sleep(2000);

		click(arTagTxt);
		removetTxt(arTagTxt);
		arTagTxt.sendKeys("Warehouse");
		Thread.sleep(2000);
		arTagTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		click(apTagTxt);
		removetTxt(apTagTxt);
		apTagTxt.sendKeys("Warehouse");
		Thread.sleep(2000);
		apTagTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		ClickUsingJs(settingUpdateIcon);

		getWaitForAlert();

		getAlert().accept();
		
		Thread.sleep(2000);

		String expMessage = "Data saved Successfully";
		String actMessage = checkValidationMessage(expMessage);

		click(settingscloseBtn);

		Thread.sleep(2000);

		// prongHornStartAtAdminLevel();

		Thread.sleep(2000);

		if (actMessage.equalsIgnoreCase(expMessage)) {
			return true;
		} else {

			return false;
		}

	}

	@FindBy(xpath = "//*[@id='btnCustomizeClose']/i")
	public static WebElement settingscloseBtn;

	public boolean checkSavingVouchersInSalesInvoiceVAT()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException {

		Thread.sleep(2000);
	/*	logout();

		Thread.sleep(2000);

		checkLoginToSelectedCompany("ManualAdjustment", "useralloptions", "12345");

		Thread.sleep(2000);*/
		
		click(financialsMenu);

		click(financialsTransactionMenu);

		click(financialTransactionSalesMenu);

		click(salesInvoiceVATVoucher);

		Thread.sleep(8000);
		click(newBtn);
		Thread.sleep(4000);
		boolean actVoucherSaving1 = CheckSavingSalesInvoiceVAT("Customer A", "AMERICA", "HYDERABAD", "1", "1500");

		boolean expVoucherSaving1 = true;

		System.err.println(" Saving Voucher 1:" + actVoucherSaving1 + " Value Exp: " + expVoucherSaving1);

		Thread.sleep(2000);

		boolean actVoucherSaving2 = CheckSavingSalesInvoiceVAT("Customer A", "DUBAI", "HYDERABAD", "1", "1500");

		boolean expVoucherSaving2 = true;

		System.err.println(" Saving Voucher 2:" + actVoucherSaving2 + " Value Exp: " + expVoucherSaving2);

		Thread.sleep(2000);

		if (actVoucherSaving1 == expVoucherSaving1 && actVoucherSaving2 == expVoucherSaving2) {
			return true;
		} else {

			return false;
		}

	}

	public boolean checkSavingReceiptsVATVOuchers()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {

		Thread.sleep(3000);

		ClickUsingJs(financialsMenu);

		click(financialsTransactionMenu);

		click(cashAndBankMenu);

		click(recepitsVATVoucher);

		Thread.sleep(2000);

		waitToClick(newBtn);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(newCashBankAccountTxt));
		newCashBankAccountTxt.click();

		newCashBankAccountTxt.sendKeys(Keys.SPACE);

		int cashAndBAnkAccountListCount = cashAndBAnkAccountList.size();

		System.err.println("cashAndBAnkAccountListCount   : " + cashAndBAnkAccountListCount);

		for (int i = 0; i < cashAndBAnkAccountListCount; i++) {
			String data = cashAndBAnkAccountList.get(i).getText();

			if (data.equalsIgnoreCase("Bank")) {
				cashAndBAnkAccountList.get(i).click();

				break;
			}
		}

		newCashBankAccountTxt.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(departmentTxt));

		departmentTxt.click();
		departmentTxt.sendKeys(Keys.SHIFT, Keys.HOME, Keys.BACK_SPACE);
		departmentTxt.sendKeys(Keys.SPACE);
		Thread.sleep(2000);
		int departmentcount = departmentListCount.size();

		System.err.println(departmentcount);

		for (int i = 0; i < departmentcount; i++) {
			String data = departmentListCount.get(i).getText();

			if (data.equalsIgnoreCase("AMERICA")) {
				departmentListCount.get(i).click();
				break;
			}
		}

		Thread.sleep(1000);

		departmentTxt.sendKeys(Keys.TAB);

		Thread.sleep(1500);

		removetTxt(warehouseTxt);
		Thread.sleep(2000);
		warehouseTxt.sendKeys("HYDERABAD");

		Thread.sleep(2000);
		warehouseTxt.sendKeys(Keys.TAB);

		Thread.sleep(1235);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(placeofSupplyTxt));
		placeofSupplyTxt.click();
		placeofSupplyTxt.sendKeys(Keys.SPACE);

		int placeOfSupplyListCount = placeofSupplyList.size();

		System.err.println(placeOfSupplyListCount);

		for (int i = 0; i < placeOfSupplyListCount; i++) {
			String data = placeofSupplyList.get(i).getText();

			if (data.equalsIgnoreCase("Abu Dhabi")) {
				placeofSupplyList.get(i).click();

				break;
			}
		}

		placeofSupplyTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_1stColumn));
		select1stRow_1stColumn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_AccountTxt));
		enter_AccountTxt.click();
		enter_AccountTxt.sendKeys("Customer A");

		Thread.sleep(2000);

		enter_AccountTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enterReceiptsVATTaxCode));
		removetTxt(enterReceiptsVATTaxCode);
		enterReceiptsVATTaxCode.sendKeys("STD");
		Thread.sleep(2000);
		enterReceiptsVATTaxCode.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_Amount));
		enter_Amount.sendKeys("3000");
		enter_Amount.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(billRefPartyName));

		int Adjustbills = billRefAdjustBillsGridList.size();

		String actAdjustbills = Integer.toString(Adjustbills);

		String expAdjustbills = "2";

		System.err.println("actAdjustbills : " + actAdjustbills + " Value Expected  : " + expAdjustbills);

		String actRow1List = listOfElements(billRefRow1List);
		String expRow1List = "[1, NDT55:1, " + currentDate() + ", " + currentDate()
				+ ", ₹, 1,500.00, 1,500.00, 0.00, 0.00, 0.00]";

		System.err.println(" ACt ROw1 List: " + actRow1List);
		System.err.println(" EXP ROw1 List: " + expRow1List);

		String actRow2List = listOfElements(billRefRow2List);
		String expRow2List = "[2, NDT55:2, " + currentDate() + ", " + currentDate()
				+ ", ₹, 1,500.00, 1,500.00, 0.00, 0.00, 0.00]";

		System.err.println(" ACt ROw2 List: " + actRow2List);
		System.err.println(" EXP ROw2 List: " + expRow2List);

		billwisePick();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNumberTxt));
		String docno = documentNumberTxt.getAttribute("value");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(voucherSaveBtn));
		voucherSaveBtn.click();

		boolean actSaving = checkBackgroundSavingMessage(docno);
		boolean expSaving = true;

		System.err.println(" Saving voucher: " + actSaving + " Value Exp: " + expSaving);

		if (actSaving == expSaving && actRow1List.equalsIgnoreCase(expRow1List)
				&& actRow2List.equalsIgnoreCase(expRow2List))

		{
			System.err.println("Test Pass: Recepits VAT Voucher Saved and Adjustment Bills are Displayed ");
			return true;
		} else {
			System.err.println("Test FAIL: Recepits VAT Voucher Saved and Adjustment Bills are Displayed ");
			return false;
		}

	}

	public boolean checkManulAdjsumentScreenWithCustomerA() throws InterruptedException {

		Thread.sleep(2000);
		focusMainSearch("Manual Adjustment");
		Thread.sleep(4500);

		waitForElement(MA_ARAPDrpdwn);
		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_ARAPDrpdwn));
		Select ARAP = new Select(MA_ARAPDrpdwn);
		ARAP.selectByValue("0");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_AccountDrpdwn));
		MA_AccountDrpdwn.sendKeys("Customer A");
		Thread.sleep(1999);
		MA_AccountDrpdwn.sendKeys(Keys.TAB);

		click(MA_DepDrpDwn);
		MA_DepDrpDwn.sendKeys("HYDERABAD");
		Thread.sleep(1999);
		MA_DepDrpDwn.sendKeys(Keys.TAB);

		/*click(MA_FADrpDwn);
		MA_FADrpDwn.sendKeys("AMERICA");
		Thread.sleep(1999);
		MA_FADrpDwn.sendKeys(Keys.TAB);*/

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_CurrencyDrpDwn));
		MA_CurrencyDrpDwn.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME);
		MA_CurrencyDrpDwn.sendKeys("INR");
		Thread.sleep(1999);
		MA_CurrencyDrpDwn.sendKeys(Keys.TAB);

		Thread.sleep(1000);
		
		click(filterCollapseBtn);
		Thread.sleep(1500);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_LoadBtn));
		ClickUsingJs(MA_LoadBtn);

		Thread.sleep(5999);

		int count = MA_DebitSideRow1list.size();
		ArrayList<String> Debit = new ArrayList<String>();

		for (int i = 0; i < count; i++) {
			String data = MA_DebitSideRow1list.get(i).getText();

			if (data.isEmpty() == false) {
				Debit.add(data);
			}
		}

		String actDebitSideList = Debit.toString();
		String expDebitSideList = "[1, NDT55:1, " + dateF9() + ", " + dateF9()
				+ ", ₹, 1500.00, 1500.00, 0.00, 0.00, 0.00, 0.00, 0.00]";

		System.err.println(" Actual Debit List  : " + actDebitSideList);
		System.err.println(" Exp    Debit List  : " + expDebitSideList);

		Thread.sleep(2000);
		int count1 = MA_CreditSideRow1list.size();
		ArrayList<String> Credit = new ArrayList<String>();

		for (int i = 0; i < count1; i++) {
			String data = MA_CreditSideRow1list.get(i).getText();

			if (data.isEmpty() == false) {
				Credit.add(data);
			}

		}

		String actCreditSideList = Credit.toString();
		String expCreditSideList = "[1, NDT57:1, " + dateF9() + ", " + dateF9()
				+ ", ₹, 3000.00, 3000.00, 0.00, 0.00, 0.00, 0.00, 0.00]";

		System.err.println(" Actual Credit List  : " + actCreditSideList);
		System.err.println(" Exp    Credit List  : " + expCreditSideList);

		if (actCreditSideList.endsWith(expCreditSideList) && actDebitSideList.equalsIgnoreCase(expDebitSideList)) {

			return true;
		} else {

			return false;
		}

	}

	@FindBy(xpath = "//*[@id='optFATag']")
	private static WebElement MA_FADrpDwn;

	public boolean checkWithFATAGDifferent() throws InterruptedException {

		//removetTxt(MA_FADrpDwn);
		MA_FADrpDwn.click();
		MA_FADrpDwn.sendKeys(Keys.END, Keys.SHIFT,Keys.HOME);
		MA_FADrpDwn.sendKeys("DUBAI");
		Thread.sleep(1999);
		MA_FADrpDwn.sendKeys(Keys.TAB);

		Thread.sleep(2500);
		
		click(filterCollapseBtn);
		Thread.sleep(1500);
		

		click(MA_LoadBtn);

		Thread.sleep(2999);

		int count = MA_DebitSideRow1list.size();
		ArrayList<String> Debit = new ArrayList<String>();

		for (int i = 0; i < count; i++) {
			String data = MA_DebitSideRow1list.get(i).getText();

			if (data.isEmpty() == false) {
				Debit.add(data);
			}

		}

		String actDebitSideList = Debit.toString();
		String expDebitSideList = "[1, NDT55:2, " + dateF9() + ", " + dateF9()
				+ ", ₹, 1500.00, 1500.00, 0.00, 0.00, 0.00, 0.00, 0.00]";

		System.err.println(" Actual Debit List  : " + actDebitSideList);
		System.err.println(" Exp    Debit List  : " + expDebitSideList);

		if (actDebitSideList.equalsIgnoreCase(expDebitSideList)) {

			return true;
		} else {

			return false;
		}

	}

	public boolean checkAdjustingInAutoAdjustScreenWithOutDep()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		click(focusMainSearch);
		focusMainSearch.sendKeys("Auto Adjust");
		Thread.sleep(500);
		focusMainSearch.sendKeys(Keys.ENTER);

		Thread.sleep(2500);

		Select s1 = new Select(autoAdju_TypeDrpdwn);
		s1.selectByValue("0");

		Thread.sleep(2500);

		String actType = s1.getFirstSelectedOption().getText();
		String expType = "Customer";

		System.err.println("autoAdju_TypeDrpdwn : " + actType + " Value Exp: " + expType);

		Thread.sleep(500);
		Select s2 = new Select(autoAdju_adjustmentDrpdwn);

		String actautoAdju_adjustmentDrpdwn = s2.getFirstSelectedOption().getText();
		String expautoAdju_adjustmentDrpdwn = "Auto Adjust On Fifo";

		System.err.println("autoAdju_adjustmentDrpdwn : " + actautoAdju_adjustmentDrpdwn + " Value Exp: "
				+ expautoAdju_adjustmentDrpdwn);

		click(autoAdju_ItemSearch);
		removetTxt(autoAdju_ItemSearch);
		autoAdju_ItemSearch.sendKeys("Customer A");
		Thread.sleep(2000);
		autoAdju_ItemSearch.sendKeys(Keys.ENTER);

		Thread.sleep(2000);

		click(autoAdju_SelectALlMasters);
		Thread.sleep(2000);

		click(autoAdju_OKBtn);

		String expMessage = "auto adjust finished.";
		String actMessage = checkValidationMessage(expMessage);

		Thread.sleep(2000);

		if (actMessage.equalsIgnoreCase(expMessage) && actType.equalsIgnoreCase(expType)
				&& actautoAdju_adjustmentDrpdwn.equalsIgnoreCase(expautoAdju_adjustmentDrpdwn)) {
			return true;
		} else {

			return false;
		}

	}

	public boolean checkAdjustmentVoucherInrecepictsVATFromAutoAdjustScreen() throws InterruptedException {

		Thread.sleep(3000);

		ClickUsingJs(financialsMenu);

		click(financialsTransactionMenu);

		click(cashAndBankMenu);

		click(recepitsVATVoucher);

		Thread.sleep(2000);

		click(homePageRow1Chkbox);

		click(editBtn);

		Thread.sleep(3500);

		String actEntryPage1 = listOfElements(entryPageRow1List);
		String expEntryPage1 = "[1, Customer A, Std Rate, 3,000.00, NDT55:1 : " + getCurrentdateDayFormat()
				+ ";NDT55:2 : " + getCurrentdateDayFormat() + ", 142.86]";

		System.err.println(" Row1 ACT :" + actEntryPage1);
		System.err.println(" Row1 EXP :" + expEntryPage1);

		if (actEntryPage1.equalsIgnoreCase(expEntryPage1)) {
			return true;
		} else {
			return false;

		}

	}

	@FindBy(xpath = "//*[@id='id_Adjustment_Grid_body']/tr[1]/td[2]/input")
	private static WebElement billrefAdjuBills1stChkbox;

	@FindBy(xpath = "//*[@id='id_Adjustment_Grid_body']/tr[2]/td[2]/input")
	private static WebElement billrefAdjuBills2ndChkbox;

	public boolean checkResavingReceipctsVoucherWithNewReferenceAgain()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		click(select1stRow_3rdColumn);
		enter_Amount.sendKeys(Keys.TAB);

		Thread.sleep(3500);

		click(billrefAdjuBills1stChkbox);

		click(billrefAdjuBills2ndChkbox);

		Thread.sleep(2000);

		billwisePick();

		String actEntryPage1 = listOfElements(entryPageRow1List);
		String expEntryPage1 = "[1, Customer A, Std Rate, 3,000.00, New Reference, 142.86]";

		System.err.println(" Row1 ACT :" + actEntryPage1);
		System.err.println(" Row1 EXP :" + expEntryPage1);

		String docno = documentNumberTxt.getAttribute("value");

		click(voucherSaveBtn);

		boolean actSaving = checkBackgroundSavingMessage(docno);
		boolean expSaving = true;

		System.err.println(" Saving voucher: " + actSaving + " Value Exp: " + expSaving);

		if (actSaving == expSaving && actEntryPage1.equalsIgnoreCase(expEntryPage1))

		{
			System.err.println("Test Pass: Recepits VAT Voucher Saved and Adjustment Bills are Displayed ");
			return true;
		} else {
			System.err.println("Test FAIL: Recepits VAT Voucher Saved and Adjustment Bills are Displayed ");
			return false;
		}

	}

	public boolean checkAutoAdjustWithDepartment()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {

		click(focusMainSearch);
		focusMainSearch.sendKeys("Auto Adjust");
		Thread.sleep(500);
		focusMainSearch.sendKeys(Keys.ENTER);

		Thread.sleep(2500);

		Select s1 = new Select(autoAdju_TypeDrpdwn);
		s1.selectByValue("0");

		Thread.sleep(2500);

		String actType = s1.getFirstSelectedOption().getText();
		String expType = "Customer";

		System.err.println("autoAdju_TypeDrpdwn : " + actType + " Value Exp: " + expType);

		Thread.sleep(500);
		Select s2 = new Select(autoAdju_adjustmentDrpdwn);

		String actautoAdju_adjustmentDrpdwn = s2.getFirstSelectedOption().getText();
		String expautoAdju_adjustmentDrpdwn = "Auto Adjust On Fifo";

		System.err.println("autoAdju_adjustmentDrpdwn : " + actautoAdju_adjustmentDrpdwn + " Value Exp: "
				+ expautoAdju_adjustmentDrpdwn);

		Thread.sleep(2000);

		removetTxt(MA_FADrpDwn);
		MA_FADrpDwn.sendKeys("AMERICA");
		Thread.sleep(2000);
		MA_FADrpDwn.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		click(autoAdju_ItemSearch);
		removetTxt(autoAdju_ItemSearch);
		autoAdju_ItemSearch.sendKeys("Customer A");
		Thread.sleep(2000);
		autoAdju_ItemSearch.sendKeys(Keys.ENTER);

		Thread.sleep(2000);

		click(autoAdju_SelectALlMasters);
		Thread.sleep(2000);

		click(autoAdju_OKBtn);

		String expMessage = "auto adjust finished.";
		String actMessage = checkValidationMessage(expMessage);

		Thread.sleep(2000);

		if (actMessage.equalsIgnoreCase(expMessage) && actType.equalsIgnoreCase(expType)
				&& actautoAdju_adjustmentDrpdwn.equalsIgnoreCase(expautoAdju_adjustmentDrpdwn)) {
			return true;
		} else {

			return false;
		}

	}

	public boolean checkAdjustmentVoucherInrecepictsVATFromAutoAdjustScreenWithDepFilter()
			throws InterruptedException, AWTException, IOException {

		Thread.sleep(3000);

		ClickUsingJs(financialsMenu);

		click(financialsTransactionMenu);

		click(cashAndBankMenu);

		click(recepitsVATVoucher);

		Thread.sleep(2000);

		click(homePageRow1Chkbox);

		click(editBtn);

		Thread.sleep(3500);

		String actEntryPage1 = listOfElements(entryPageRow1List);
		String expEntryPage1 = "[1, Customer A, Std Rate, 3,000.00, NDT55:1 : " + getCurrentdateDayFormat()
				+ ";New Reference, 142.86]";

		System.err.println(" Row1 ACT :" + actEntryPage1);
		System.err.println(" Row1 EXP :" + expEntryPage1);

		Thread.sleep(2000);
		click(new_CloseBtn);

		Thread.sleep(2000);

		// prongHornStopAtAdminLevel();

		Thread.sleep(2000);

		if (actEntryPage1.equalsIgnoreCase(expEntryPage1)) {
			return true;
		} else {
			return false;

		}

	}

	public void checkRestoreExchangeBackUp() throws InterruptedException, AWTException, IOException {

		// prongHornStopAtAdminLevel();

		Thread.sleep(3569);

		restoreCompany("Exchange New Scnarios", "ManualAdjustment");

		Thread.sleep(2000);

		logout();
		System.err.println(" ********************************************Method Logout");

		Thread.sleep(2000);

		checkLoginToSelectedCompany("ManualAdjustment", "useralloptions", "12345");

		Thread.sleep(2000);

		System.err.println(" Company Login and Restore Done ");

		Thread.sleep(6589);

		// prongHornStartAtAdminLevel();

		Thread.sleep(6589);

	}
	
	
	@FindBy(xpath="//*[@id='id_body_536870915']")
	public static WebElement body_DeptTxt;

	public boolean checkSavingSalesInvoiceVATWithPastDate()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {

		Thread.sleep(2000);

		Thread.sleep(2000);

		// prongHornStartAtAdminLevel();

		Thread.sleep(2000);

		eraseAllTransactions();

		Thread.sleep(2000);

	/*	logout();

		Thread.sleep(2567);

		checkLoginToManualAdjsuments();

		waitOn(homeMenu);*/

		Thread.sleep(3000);

		ClickUsingJs(financialsMenu);

		click(financialsTransactionMenu);

		click(financialTransactionSalesMenu);

		click(salesInvoiceVATVoucher);

		Thread.sleep(4000);

		click(newBtn);

		Thread.sleep(2000);
		click(documentNumberTxt);

		Thread.sleep(1999);

		Thread.sleep(2000);
		click(dateTxt);
		removetTxt(dateTxt);
		Thread.sleep(2000);
		dateTxt.sendKeys("01/01/2021");
		Thread.sleep(2000);
		dateTxt.sendKeys(Keys.TAB);

		customerAccountTxt.click();
		customerAccountTxt.sendKeys("Customer A");
		Thread.sleep(2000);
		customerAccountTxt.sendKeys(Keys.TAB);

		Thread.sleep(1999);

		/*departmentTxt.click();
		departmentTxt.sendKeys("AMERICA");
		Thread.sleep(2000);
		departmentTxt.sendKeys(Keys.TAB);
*/
		selectVoucherHeaderCurrency("AED");

		Thread.sleep(2000);

		Thread.sleep(2000);

		click(select1stRow_1stColumn);
		pvWareHouseTxt.click();
		pvWareHouseTxt.sendKeys(Keys.SPACE);

		int warehousecount = pvwareHouseListCount.size();

		System.err.println(warehousecount);

		for (int i = 0; i < warehousecount; i++) {
			String data = pvwareHouseListCount.get(i).getText();

			if (data.equalsIgnoreCase("HYDERABAD")) {
				pvwareHouseListCount.get(i).click();

				break;
			}
		}

		pvWareHouseTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		
		removetTxt(body_DeptTxt);
		body_DeptTxt.sendKeys("AMERICA");
		Thread.sleep(2000);
		body_DeptTxt.sendKeys(Keys.TAB);
		

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_ItemTxt));
		removetTxt(enter_ItemTxt);
		enter_ItemTxt.sendKeys("std");

		Thread.sleep(1999);

		enter_ItemTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		
		

		removetTxt(enterSalesTaxcode);
		enterSalesTaxcode.sendKeys("STD");

		Thread.sleep(2500);

		enterSalesTaxcode.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		click(select1stRow_5thColumn);

		Thread.sleep(2000);
		click(select1stRow_9thColumn);
		enter_AQTxt.sendKeys("10");
		enter_AQTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		enter_FQTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		click(select1stRow_12thColumn);
		Thread.sleep(2000);
		click(select1stRow_15thColumn);

		Thread.sleep(2000);
		enter_Rate.click();
		enter_Rate.clear();
		enter_Rate.sendKeys("10");
		enter_Rate.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		enter_Gross.click();
		enter_Gross.sendKeys(Keys.TAB);

		String docno = documentNumberTxt.getAttribute("value");

		Thread.sleep(1999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(voucherSaveBtn));
		voucherSaveBtn.click();

		Thread.sleep(1999);
		billwisePick();

		String exp = "This Transaction will make the Stock Negative";
		String act = checkValidationMessage(exp);

		//boolean actMessage = checkVoucherSavingMessage(docno);
		//boolean expMessage = true;

		/*
		 * boolean actMessage = checkBackgroundSavingNegativeMessage(docno); boolean
		 * expMessage = true;
		 */

		//System.out.println("SavingMessage  :  " + actMessage + " Value Expected : " + expMessage);

		if (act.equalsIgnoreCase(exp))

		{
			System.err.println(" Test Pass: Sales Invoice VAT Voucher Saved ");
			return true;
		}

		else if (act.equalsIgnoreCase(exp)) {
			System.err.println(" Negative Message Displayed*****************************");
			return false;
		}

		else {
			System.err.println(" Test FAIl: Sales Invoice VAT Voucher Saved ");
			return false;
		}

	}
	
	
	@FindBy(xpath="//*[@id='id_body_536870915']")
	public static WebElement receiptsBody_DeptTxt;

	
	@FindBy(xpath="//a[@title='Setting']")
	public static WebElement receiptsVATSettingsBtn;
	
	@FindBy(xpath="//*[@id='doc_TagsTable_col_1-2']")
	public static WebElement receiptsVAT_Position1_2;
	
	@FindBy(xpath="//*[@id='doc_TagsPositionDropDown']")
	public static WebElement receiptsVAT_Position1_2Dropdown;
	
	@FindBy(xpath="//*[@id='updateButton']")
	public static WebElement receiptsVATUpdateBtn;
	
	public boolean checkSavingReceipcsVATAndAdjustingSalesInvoiceVAT()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {

		Thread.sleep(3000);

		ClickUsingJs(financialsMenu);

		click(financialsTransactionMenu);

		click(cashAndBankMenu);

		click(recepitsVATVoucher);

		Thread.sleep(4000);

		click(newBtn);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(newCashBankAccountTxt));
		newCashBankAccountTxt.click();

		newCashBankAccountTxt.sendKeys(Keys.SPACE);

		int cashAndBAnkAccountListCount = cashAndBAnkAccountList.size();

		System.err.println("cashAndBAnkAccountListCount   : " + cashAndBAnkAccountListCount);

		for (int i = 0; i < cashAndBAnkAccountListCount; i++) {
			String data = cashAndBAnkAccountList.get(i).getText();

			if (data.equalsIgnoreCase("Bank")) {
				cashAndBAnkAccountList.get(i).click();

				break;
			}
		}

		newCashBankAccountTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		selectVoucherHeaderCurrency("AED");
		Thread.sleep(2000);

		/*getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(departmentTxt));

		departmentTxt.click();
		departmentTxt.sendKeys(Keys.SHIFT, Keys.HOME, Keys.BACK_SPACE);
		departmentTxt.sendKeys(Keys.SPACE);
		Thread.sleep(2000);
		int departmentcount = departmentListCount.size();

		System.err.println(departmentcount);

		for (int i = 0; i < departmentcount; i++) {
			String data = departmentListCount.get(i).getText();

			if (data.equalsIgnoreCase("AMERICA")) {
				departmentListCount.get(i).click();
				break;
			}
		}

		Thread.sleep(1000);

		departmentTxt.sendKeys(Keys.TAB);

		Thread.sleep(2500);*/

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(placeofSupplyTxt));
		placeofSupplyTxt.click();
		placeofSupplyTxt.sendKeys(Keys.SPACE);

		int placeOfSupplyListCount = placeofSupplyList.size();

		System.err.println(placeOfSupplyListCount);

		for (int i = 0; i < placeOfSupplyListCount; i++) {
			String data = placeofSupplyList.get(i).getText();

			if (data.equalsIgnoreCase("Abu Dhabi")) {
				placeofSupplyList.get(i).click();

				break;
			}
		}

		placeofSupplyTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_1stColumn));
		select1stRow_1stColumn.click();

		receiptsBody_DeptTxt.click();
		receiptsBody_DeptTxt.sendKeys("AMERICA");
		Thread.sleep(2000);
		receiptsBody_DeptTxt.sendKeys(Keys.TAB);
		
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_AccountTxt));
		enter_AccountTxt.click();
		enter_AccountTxt.sendKeys("Customer A");

		Thread.sleep(2000);

		enter_AccountTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		
		

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enterReceiptsVATTaxCode));
		removetTxt(enterReceiptsVATTaxCode);
		enterReceiptsVATTaxCode.sendKeys("STD");
		Thread.sleep(2000);
		enterReceiptsVATTaxCode.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_Amount));
		enter_Amount.sendKeys("100");
		enter_Amount.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(billRefPartyName));

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(billRefGridFirstRowAdjustmentAmtTxt));
		click(billRefGridFirstRowAdjustmentAmtTxt);

		click(billRefPickIcon);

		Thread.sleep(2000);

		click(billRefGridFirstRowAdjustmentAmtTxt);

		click(billRefPickIcon);

		String actList = listOfElements(billRefRow1List);
		String expList = "[1, NDT55:1, 01/01/2021, 01/01/2021, Dhs, 100.00, 100.00, 100.00, 100.00, 0.00]";

		System.err.println(" ACT List: " + actList);
		System.err.println(" EXP List: " + expList);

		click(billRefOkBtn);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNumberTxt));
		String docno = documentNumberTxt.getAttribute("value");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(voucherSaveBtn));
		voucherSaveBtn.click();

		boolean actSaving = checkBackgroundSavingMessage(docno);
		boolean expSaving = true;

		System.err.println(" Saving voucher: " + actSaving + " Value Exp: " + expSaving);
		
		click(new_CloseBtn);
		
		click(receiptsVATSettingsBtn);
		Thread.sleep(4000);
		
		click(receiptsVAT_Position1_2);
		Select s=new Select(receiptsVAT_Position1_2Dropdown);
		s.selectByVisibleText("Body");
		
		click(receiptsVATUpdateBtn);
		Thread.sleep(2000);
		

		if (actList.equalsIgnoreCase(expList) && actSaving == expSaving) {

			return true;
		} else {

			return false;
		}

	}

	@FindBy(xpath = "//*[@id='DocToPost']")
	private static WebElement docToPostDrpDwn;

	@FindBy(xpath = "//*[@id='chkPostBy']/following-sibling::span")
	private static WebElement postByDepChkbox;

	@FindBy(xpath = "(//a[@title='Ok'])[1]")
	private static WebElement forScreenOKBtn;

	@FindBy(xpath = "//*[@id='cmbUserTypeMaster']")
	public static WebElement reportEntrySearchTxt;

	public boolean checkPostingInAdjustForeignExchange()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		focusMainSearch("Adjust Foreign Exchange");

		Thread.sleep(3500);

		click(reportEntrySearchTxt);
		reportEntrySearchTxt.sendKeys("Customer A");
		Thread.sleep(2000);
		reportEntrySearchTxt.sendKeys(Keys.TAB);

		Thread.sleep(3500);

		click(docToPostDrpDwn);
		docToPostDrpDwn.sendKeys("Non-Standard Journal Entries");
		Thread.sleep(3500);
		docToPostDrpDwn.sendKeys(Keys.TAB);

		Thread.sleep(1500);

		click(postByDepChkbox);

		Thread.sleep(1500);

		click(forScreenOKBtn);

		String expMessage = "Voucher(s) posted with following document numbers : 1";
		String actMessage = checkValidationMessage(expMessage);

		if (actMessage.equalsIgnoreCase(expMessage)) {

			return true;
		} else {
			return false;

		}

	}

	public boolean checPostedVoucherINNonStandardJournales() throws InterruptedException {

		focusMainSearch("Non-Standard Journal Entries");

		Thread.sleep(3569);

		voucherHomePageVoucherSelect("1");

		Thread.sleep(3569);

		String actDep = departmentTxt.getAttribute("data-focustext");
		String expDep = "AMERICA";

		System.err.println(" Dep : " + actDep + "*************" + expDep);

		String actList = listOfElements(entryPageRow1List);
		String expList = "[1, Customer A, 600.00]";

		System.err.println(" ACt List1: " + actList);
		System.err.println(" EXP List1: " + expList);

		String actList1 = listOfElements(entryPageRow2List);
		String expList1 = "[2, Exchange Gain, 600.00]";

		System.err.println(" ACt List2: " + actList1);
		System.err.println(" EXP List2: " + expList1);

		Thread.sleep(3569);

		if (actList.equalsIgnoreCase(expList) && actList1.equalsIgnoreCase(expList1)
				&& actDep.equalsIgnoreCase(expDep)) {
			System.err.println(" Test PasS:  Voucher posted as Expected");
			return true;

		} else {
			System.err.println(" Test FAIl:  Voucher posted as Expected");
			return false;

		}

	}

	public boolean checkSavingVoucherInRecepitsVATWithCurrency(String curr, String dep, String acc, String amt)
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		Thread.sleep(3000);

		ClickUsingJs(financialsMenu);

		click(financialsTransactionMenu);

		click(cashAndBankMenu);

		click(recepitsVATVoucher);

		Thread.sleep(2000);

		waitToClick(newBtn);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(newCashBankAccountTxt));
		newCashBankAccountTxt.click();

		newCashBankAccountTxt.sendKeys(Keys.SPACE);

		int cashAndBAnkAccountListCount = cashAndBAnkAccountList.size();

		System.err.println("cashAndBAnkAccountListCount   : " + cashAndBAnkAccountListCount);

		for (int i = 0; i < cashAndBAnkAccountListCount; i++) {
			String data = cashAndBAnkAccountList.get(i).getText();

			if (data.equalsIgnoreCase("Bank")) {
				cashAndBAnkAccountList.get(i).click();

				break;
			}
		}

		newCashBankAccountTxt.sendKeys(Keys.TAB);

		Thread.sleep(2500);

		selectVoucherHeaderCurrency(curr);
		Thread.sleep(2500);

		/*getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(departmentTxt));

		departmentTxt.click();
		departmentTxt.sendKeys(Keys.SHIFT, Keys.HOME, Keys.BACK_SPACE);
		departmentTxt.sendKeys(Keys.SPACE);
		Thread.sleep(2000);
		int departmentcount = departmentListCount.size();

		System.err.println(departmentcount);

		for (int i = 0; i < departmentcount; i++) {
			String data = departmentListCount.get(i).getText();

			if (data.equalsIgnoreCase(dep)) {
				departmentListCount.get(i).click();
				break;
			}
		}

		Thread.sleep(1000);

		departmentTxt.sendKeys(Keys.TAB);

		Thread.sleep(2500);*/

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(placeofSupplyTxt));
		placeofSupplyTxt.click();
		placeofSupplyTxt.sendKeys(Keys.SPACE);

		int placeOfSupplyListCount = placeofSupplyList.size();

		System.err.println(placeOfSupplyListCount);

		for (int i = 0; i < placeOfSupplyListCount; i++) {
			String data = placeofSupplyList.get(i).getText();

			if (data.equalsIgnoreCase("Abu Dhabi")) {
				placeofSupplyList.get(i).click();

				break;
			}
		}

		placeofSupplyTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_1stColumn));
		select1stRow_1stColumn.click();
		
		receiptsBody_DeptTxt.click();
		receiptsBody_DeptTxt.sendKeys(dep);
		Thread.sleep(2000);
		receiptsBody_DeptTxt.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_AccountTxt));
		enter_AccountTxt.click();
		enter_AccountTxt.sendKeys(acc);

		Thread.sleep(2000);

		enter_AccountTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enterReceiptsVATTaxCode));
		removetTxt(enterReceiptsVATTaxCode);
		enterReceiptsVATTaxCode.sendKeys("STD");
		Thread.sleep(2000);
		enterReceiptsVATTaxCode.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_Amount));
		enter_Amount.sendKeys(amt);
		enter_Amount.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(billRefPartyName));

		billwisePick();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNumberTxt));
		String docno = documentNumberTxt.getAttribute("value");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(voucherSaveBtn));
		voucherSaveBtn.click();

		boolean actSaving = checkBackgroundSavingMessage(docno);
		boolean expSaving = true;

		System.err.println(" Saving voucher: " + actSaving + " Value Exp: " + expSaving);

		if (actSaving == expSaving)

		{
			System.err.println("Test Pass: Recepits VAT Voucher Saved  ");
			return true;
		} else {
			System.err.println("Test FAIL: Recepits VAT Voucher Saved  ");
			return false;
		}
	}

	public boolean checkPostingThorughtManulAdjustmentScreen()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException {

		boolean actSalesSaving = checkSavingSalesInvoiceVATWithPastDate();

		System.err.println(" Saving Sales Invoice VAT : " + actSalesSaving);

		boolean actReceipctsVATSaving = checkSavingVoucherInRecepitsVATWithCurrency("AED", "AMERICA", "Customer A",
				"100");
		


		Thread.sleep(3000);

		
		
		
		// MAnual Adjustmnet

		focusMainSearch("Manual Adjustment");
		Thread.sleep(2500);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_ARAPDrpdwn));
		Select ARAP = new Select(MA_ARAPDrpdwn);
		ARAP.selectByValue("0");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_AccountDrpdwn));
		MA_AccountDrpdwn.sendKeys("Customer A");
		Thread.sleep(1999);
		MA_AccountDrpdwn.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_DepDrpDwn));
		MA_DepDrpDwn.sendKeys("AMERICA");
		Thread.sleep(1999);
		MA_DepDrpDwn.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_CurrencyDrpDwn));
		MA_CurrencyDrpDwn.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME);
		MA_CurrencyDrpDwn.sendKeys("AED");
		Thread.sleep(1999);
		MA_CurrencyDrpDwn.sendKeys(Keys.TAB);

		Thread.sleep(1000);
		
		click(filterCollapseBtn);
		Thread.sleep(1500);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_LoadBtn));
		MA_LoadBtn.click();

		Thread.sleep(1999);

		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();

		String docdate = df.format(date);

		int count = MA_DebitSideRow1list.size();
		ArrayList<String> Debit = new ArrayList<String>();

		for (int i = 0; i < count; i++) {
			String data = MA_DebitSideRow1list.get(i).getText();

			if (data.isEmpty() == false) {
				Debit.add(data);
			}

		}

		String actDebitSideList = Debit.toString();
		String expDebitSideList = "[1, NDT55:1, 1/1/2021, 1/1/2021, Dhs, 100.00, 100.00, 0.00, 0.00, 0.00, 0.00, 0.00]";

		System.err.println(" Actual Debit List  : " + actDebitSideList);
		System.err.println(" Exp    Debit List  : " + expDebitSideList);

		int count1 = MA_CreditSideRow1list.size();
		ArrayList<String> Credit = new ArrayList<String>();

		for (int i = 0; i < count1; i++) {
			String data = MA_CreditSideRow1list.get(i).getText();

			if (data.isEmpty() == false) {
				Credit.add(data);
			}

		}

		String actCreditSideList = Credit.toString();
		String expCreditSideList = "[1, NDT57:1, " + dateF9() + ", " + dateF9()
				+ ", Dhs, 100.00, 100.00, 0.00, 0.00, 0.00, 0.00, 0.00]";

		System.err.println(" Actual Credit List  : " + actCreditSideList);
		System.err.println(" Exp    Credit List  : " + expCreditSideList);

		Thread.sleep(2000);

		click(debitRow1Chkbox);

		Thread.sleep(2000);

		click(CreditRow1Chkbox);

		Thread.sleep(2000);

		String actdebitTotal = debitTotal.getText();
		String expdebitTotal = "100.00";

		System.err.println(" debitTotal : " + actdebitTotal + " Value  Exp: " + expdebitTotal);

		Thread.sleep(2000);

		String actcreditTotal = creditTotal.getText();
		String expcreditTotal = "100.00";

		System.err.println(" creditTotal : " + actcreditTotal + " Value  Exp: " + expcreditTotal);

		Thread.sleep(2000);

		click(MA_OkBtn);

		String expMessage = "Record Saved Succesfully";
		String actMessage = checkValidationMessage(expMessage);

		System.err.println(" Message ACT: " + actMessage);
		System.err.println(" Message EXP: " + expMessage);

		// Forgein Screen

		boolean Posting = checkPostingInAdjustForeignExchange();

		System.err.println(" POsting in Forgein Exchange Screen : " + Posting);

		// POsted Vouchers

		boolean Posted = checPostedVoucherINNonStandardJournales();

		System.err.println(" Posted Voucher Screen : " + Posted);

		if (actCreditSideList.equalsIgnoreCase(expCreditSideList) && actDebitSideList.equalsIgnoreCase(expDebitSideList)
				&& actdebitTotal.equalsIgnoreCase(expdebitTotal) && actcreditTotal.equalsIgnoreCase(expcreditTotal)
				&& actMessage.equalsIgnoreCase(expMessage) && Posted && Posting && actSalesSaving
				&& actReceipctsVATSaving) {
			return true;
		}

		else if (actCreditSideList.equalsIgnoreCase(expCreditSideList)
				&& actDebitSideList.equalsIgnoreCase(expDebitSideList) && actdebitTotal.equalsIgnoreCase(expdebitTotal)
				&& actcreditTotal.equalsIgnoreCase(expcreditTotal) && actMessage.equalsIgnoreCase(expMessage) && Posted
				&& Posting) {
			System.err.println(" Test pass : Posting Details Saved As Per Expected");
			return true;
		}

		else {
			return false;
		}

	}

	public boolean checkSavingWithAutoAdjustScreen()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {

		boolean actSalesSaving = checkSavingSalesInvoiceVATWithPastDate();

		System.err.println(" Saving Sales Invoice VAT : " + actSalesSaving);

		boolean actReceipctsVATSaving = checkSavingVoucherInRecepitsVATWithCurrency("AED", "AMERICA", "Customer A",
				"100");

		System.err.println(" Saving Receicts VAT:" + actReceipctsVATSaving);

		// Auto Adjustmnet

		click(focusMainSearch);
		focusMainSearch.sendKeys("Auto Adjust");
		Thread.sleep(500);
		focusMainSearch.sendKeys(Keys.ENTER);

		Thread.sleep(2500);

		Select s1 = new Select(autoAdju_TypeDrpdwn);
		s1.selectByValue("0");

		Thread.sleep(2500);

		/*
		 * String actType = s1.getFirstSelectedOption().getText(); String expType =
		 * "Customer";
		 * 
		 * System.err.println("autoAdju_TypeDrpdwn : " + actType + " Value Exp: " +
		 * expType);
		 */

		Thread.sleep(500);
		Select s2 = new Select(autoAdju_adjustmentDrpdwn);

		String actautoAdju_adjustmentDrpdwn = s2.getFirstSelectedOption().getText();
		String expautoAdju_adjustmentDrpdwn = "Auto Adjust On Fifo";

		System.err.println("autoAdju_adjustmentDrpdwn : " + actautoAdju_adjustmentDrpdwn + " Value Exp: "
				+ expautoAdju_adjustmentDrpdwn);

		click(autoAdju_ItemSearch);
		removetTxt(autoAdju_ItemSearch);
		autoAdju_ItemSearch.sendKeys("Customer A");
		Thread.sleep(2000);
		autoAdju_ItemSearch.sendKeys(Keys.ENTER);

		Thread.sleep(2000);

		click(autoAdju_SelectALlMasters);
		Thread.sleep(2000);

		click(autoAdju_OKBtn);

		String expMessage = "auto adjust finished.";
		String actMessage = checkValidationMessage(expMessage);

		Thread.sleep(2000);

		// Forgein Screen

		boolean Posting = checkPostingInAdjustForeignExchange();

		System.err.println(" POsting in Forgein Exchange Screen : " + Posting);

		// POsted Vouchers

		boolean Posted = checPostedVoucherINNonStandardJournales();

		System.err.println(" Posted Voucher Screen : " + Posted);

		Thread.sleep(2500);

		if (actMessage.equalsIgnoreCase(expMessage) && actMessage.equalsIgnoreCase(expMessage) && Posted && Posting
				&& actSalesSaving && actReceipctsVATSaving) {
			return true;
		}

		else if (actMessage.equalsIgnoreCase(expMessage) && actMessage.equalsIgnoreCase(expMessage) && Posted
				&& Posting) {
			System.err.println("**********************POSTING DETAILS************");
			return true;
		} else {
			return false;
		}

	}

	public boolean checkLedgerReport() throws InterruptedException {

		focusMainSearch("Ledger");

		Thread.sleep(2899);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_DateOptionDropdown));
		sl_DateOptionDropdown.click();
		Select s = new Select(sl_DateOptionDropdown);
		s.selectByValue("1");

		Thread.sleep(2000);

		click(mastercmbMasterTxt);
		mastercmbMasterTxt.sendKeys("Customer A");
		Thread.sleep(2090);
		mastercmbMasterTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_OkBtn));
		sl_OkBtn.click();

		Thread.sleep(9999);

		String actList1 = listOfElements(reportRow1List);
		String actList2 = listOfElements(reportRow2List);
		String actList3 = listOfElements(reportRow3List);
		String actList4 = listOfElements(reportRow4List);
		String actList5 = listOfElements(reportRow5List);

		String expList1 = "[1, Customer A [122-001]]";
		String expList2 = "[2, 01/01/2021, NDT55 : 1, Sales - Computers, 100.00, 100.00, 100.00, 100.00, 1,400.00, 1,400.00, United Arab Emirates Dirham]";
		String expList3 = "[3, " + currentDate()
				+ ", NDT57 : 1, Bank, 100.00, 100.00, 2,000.00, 600.00, United Arab Emirates Dirham]";
		String expList4 = "[4, " + currentDate()
				+ ", JouEnt : 1, Journal Entries Control A/C, 600.00, United Arab Emirates Dirham]";
		String expList5 = "[5, Total, 100.00, 100.00, 100.00, 100.00, 2,000.00, 2,000.00]";

		System.err.println("ACT  ROW 1 List : " + actList1);
		System.err.println("EXP  ROW 1 List : " + expList1);

		System.err.println("ACT  ROW 2 List : " + actList2);
		System.err.println("EXP  ROW 2 List : " + expList2);

		System.err.println("ACT  ROW 3 List : " + actList3);
		System.err.println("EXP  ROW 3 List : " + expList3);

		System.err.println("ACT  ROW 4 List : " + actList4);
		System.err.println("EXP  ROW 4 List : " + expList4);

		System.err.println("ACT  ROW 5 List : " + actList5);
		System.err.println("EXP  ROW 5 List : " + expList5);

		if (actList1.equalsIgnoreCase(expList1) && actList2.equalsIgnoreCase(expList2)
				&& actList3.equalsIgnoreCase(expList3) && actList4.equalsIgnoreCase(expList4)
				&& actList5.equalsIgnoreCase(expList5)) {

			return true;
		} else {

			return false;
		}

	}

	public boolean checkCustomerStatementsReport() throws InterruptedException {
		focusMainSearch("Customer Statements");

		Thread.sleep(2899);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_DateOptionDropdown));
		sl_DateOptionDropdown.click();
		Select s = new Select(sl_DateOptionDropdown);
		s.selectByValue("1");

		Thread.sleep(2000);

		click(mastercmbMasterTxt);
		mastercmbMasterTxt.sendKeys("Customer A");
		Thread.sleep(2090);
		mastercmbMasterTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_OkBtn));
		sl_OkBtn.click();

		Thread.sleep(9999);

		String actList1 = listOfElements(reportRow1List);
		String actList2 = listOfElements(reportRow2List);
		String actList3 = listOfElements(reportRow3List);
		String actList4 = listOfElements(reportRow4List);
		String actList5 = listOfElements(reportRow5List);

		String expList1 = "[1, Customer A [122-001]]";
		String expList2 = "[2, NDT55:1, 01/01/2021, Customer A, 1,400.00, 100.00, 1326, United Arab Emirates Dirham, 01/01/2021, 122-001, AMERICA, AMERICA]";
		String expList3 = "[3, NDT57:1, " + currentDate()
				+ ", Customer A, 1,400.00, 100.00, 1326, United Arab Emirates Dirham, 19/08/2024, 122-001, AMERICA, AMERICA]";
		String expList4 = "[4, Forex adjustments amount, 600.00, 600.00, 600.00]";
		String expList5 = "[5, Total, 2,000.00, 1,400.00, 600.00, 600.00, 100.00, 100.00, 2652]";

		System.err.println("ACT  ROW 1 List : " + actList1);
		System.err.println("EXP  ROW 1 List : " + expList1);

		System.err.println("ACT  ROW 2 List : " + actList2);
		System.err.println("EXP  ROW 2 List : " + expList2);

		System.err.println("ACT  ROW 3 List : " + actList3);
		System.err.println("EXP  ROW 3 List : " + expList3);

		System.err.println("ACT  ROW 4 List : " + actList4);
		System.err.println("EXP  ROW 4 List : " + expList4);

		System.err.println("ACT  ROW 5 List : " + actList5);
		System.err.println("EXP  ROW 5 List : " + expList5);

		if (actList1.equalsIgnoreCase(expList1) && actList2.equalsIgnoreCase(expList2)
				&& actList3.equalsIgnoreCase(expList3) && actList4.equalsIgnoreCase(expList4)
				&& actList5.equalsIgnoreCase(expList5)) {

			return true;
		} else {

			return false;
		}

	}

	// New Scenario
	public boolean checkSavingPaymentsVAT()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {

		Thread.sleep(2000);
		checkEraseAllTransaction();
		Thread.sleep(2000);

		Thread.sleep(2000);

		click(financialsMenu);

		click(financialsTransactionMenu);

		click(cashAndBankMenu);

		click(paymentsVATVoucher);

		Thread.sleep(2000);
		waitToClick(newBtn);

		Thread.sleep(2000);
		click(documentNumberTxt);

		Thread.sleep(2000);
		click(dateTxt);
		removetTxt(dateTxt);
		dateTxt.sendKeys("01012021");
		Thread.sleep(2000);
		dateTxt.sendKeys(Keys.TAB);

		click(newCashBankAccountTxt);

		newCashBankAccountTxt.sendKeys(Keys.SPACE);

		int cashAndBAnkAccountListCount = cashAndBAnkAccountList.size();

		System.err.println("cashAndBAnkAccountListCount   : " + cashAndBAnkAccountListCount);

		for (int i = 0; i < cashAndBAnkAccountListCount; i++) {
			String data = cashAndBAnkAccountList.get(i).getText();

			if (data.equalsIgnoreCase("bank")) {
				cashAndBAnkAccountList.get(i).click();

				break;
			}
		}

		newCashBankAccountTxt.sendKeys(Keys.TAB);
		Thread.sleep(2000);

		click(voucherHeaderCurrency);

		Thread.sleep(2000);

		voucherHeaderCurrency.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME);

		voucherHeaderCurrency.sendKeys(Keys.SPACE);

		int currencycount = currencyListCount.size();

		System.err.println(currencycount);

		for (int i = 0; i < currencycount; i++) {
			String data = currencyListCount.get(i).getText();

			if (data.equalsIgnoreCase("AED")) {
				currencyListCount.get(i).click();

				break;
			}
		}

		voucherHeaderCurrency.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		click(PDRVATPlaceOfSupplyTXt);

		PDRVATPlaceOfSupplyTXt.sendKeys("Dubai");

		Thread.sleep(2000);
		PDRVATPlaceOfSupplyTXt.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		click(departmentTxt);
		departmentTxt.sendKeys(Keys.SHIFT, Keys.HOME);
		departmentTxt.sendKeys(Keys.SPACE);

		WebElement options = departmentTxt;

		int departmentListCountCount = departmentListCount.size();

		for (int i = 0; i < departmentListCountCount; i++) {
			String data = departmentListCount.get(i).getText();

			if (data.equalsIgnoreCase("DUBAI")) {
				departmentListCount.get(i).click();
				break;
			}
		}

		departmentTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		// First Row

		click(select1stRow_1stColumn);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_AccountTxt));
		enter_AccountTxt.click();
		enter_AccountTxt.sendKeys("Vendor A");

		Thread.sleep(2000);

		enter_AccountTxt.sendKeys(Keys.TAB);

		Thread.sleep(1999);
		removetTxt(enterpayVATTaxCode);
		enterpayVATTaxCode.sendKeys("std");
		Thread.sleep(1999);
		enterpayVATTaxCode.sendKeys(Keys.TAB);

		removetTxt(enter_Amount);
		enter_Amount.sendKeys("105.65");
		Thread.sleep(1999);
		enter_Amount.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		billwisePick();

		String docno = documentNumberTxt.getAttribute("value");

		Thread.sleep(2000);

		click(voucherSaveBtn);

		boolean actSaving = checkBackgroundSavingMessage(docno);
		boolean expSaving = true;

		if (actSaving == expSaving)

		{
			System.err.println("Test pass: Payment VAT Saved With New Reference ");
			return true;
		} else {
			System.err.println("Test fail: Payment VAT Saved With New Reference ");
			return false;
		}

	}

	public boolean checkSavingMRNVOucherWithUpdateFA()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		Thread.sleep(2000);
		focusMainSearch("Material Receipt Notes");
		Thread.sleep(2000);

		waitToClick(newBtn);

		Thread.sleep(2000);
		click(documentNumberTxt);

		Thread.sleep(2000);
		selectVoucherHeaderPurchaseAccount("Purchase");
		Thread.sleep(2000);

		selectVoucherHeaderAccount("Vendor A");
		Thread.sleep(2000);

		Thread.sleep(2000);

		selectVoucherHeaderCurrency("AED");
		Thread.sleep(2000);

		click(departmentTxt);
		departmentTxt.sendKeys(Keys.SHIFT, Keys.HOME);
		departmentTxt.sendKeys(Keys.SPACE);

		WebElement options = departmentTxt;

		int departmentListCountCount = departmentListCount.size();

		for (int i = 0; i < departmentListCountCount; i++) {
			String data = departmentListCount.get(i).getText();

			if (data.equalsIgnoreCase("DUBAI")) {
				departmentListCount.get(i).click();
				break;
			}
		}

		departmentTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		String docno = documentNumberTxt.getAttribute("value");

		// First Row

		click(select1stRow_1stColumn);
		pvWareHouseTxt.sendKeys(Keys.SPACE);
		selectionElementFromList(pvvGridWarehouseList, "HYDERABAD");
		Thread.sleep(2000);
		pvWareHouseTxt.sendKeys(Keys.TAB);

		enterItemttxt.sendKeys(Keys.SPACE);
		selectionElementFromList(itemtxt123, "STD RATE COGS ITEM");
		Thread.sleep(2000);
		enterItemttxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		click(select1stRow_5thColumn);

		enterQuantitytxt.sendKeys("10");

		click(select1stRow_7thColumn);
		removetTxt(enterRatetxt);
		enterRatetxt.sendKeys("10");
		Thread.sleep(2000);
		enterRatetxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		click(voucherSaveBtn);

		Thread.sleep(2000);

		Thread.sleep(2000);

		click(billrefAdjuBills1stChkbox);

		click(billRefPickIcon);

		Thread.sleep(2000);

		click(billrefAdjuBills1stChkbox);

		click(billRefPickIcon);

		int count1 = billWiseGridRow1List.size();
		ArrayList<String> one = new ArrayList<String>();
		for (int i = 0; i < count1; i++) {
			String data1 = billWiseGridRow1List.get(i).getText();

			if (data1.isEmpty() == false) {
				if (i == 6) {
					data1 = "currency";
				}

				one.add(data1);
			}

		}
		String actbillWiseGridRow1List = one.toString();
		String expbillWiseGridRow1List = "[1, NDT58:1, 01/01/2021, 01/01/2021, currency, 105.65, 105.65, 100.00, 100.00, 0.00]";

		System.out.println("ActbillWiseGridRow1List : " + actbillWiseGridRow1List);
		System.out.println("ExpbillWiseGridRow1List : " + expbillWiseGridRow1List);
		Thread.sleep(2000);

		click(billRefOkBtn);

		boolean actSaving = checkBackgroundSavingMessage(docno);
		boolean expSaving = true;

		if (actSaving == expSaving && actbillWiseGridRow1List.equalsIgnoreCase(expbillWiseGridRow1List)) {
			System.err.println("Test pass: MRN SAVED");
			return true;
		} else {
			System.err.println("Test fail: MRN SAVED");
			return false;
		}

	}

	public boolean checkPostingDetailsInMRNVOucher() throws InterruptedException {

		Thread.sleep(2000);
		focusMainSearch("Material Receipt Notes");
		Thread.sleep(2000);

		voucherHomePageVoucherSelect("1");

		Thread.sleep(2000);

		click(toggleBtn);

		click(postingDetailsBtn);

		Thread.sleep(5500);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(postingDetailsDebitSum));

		String actpostingDetailsDebitList = listOfElements(postingDetailsDebitList);
		String exppostingDetailsDebitList = "[Purchase, 2,000.00]";

		System.err.println("postingDetailsDebitList ACT : " + actpostingDetailsDebitList);
		System.err.println("postingDetailsDebitList EXP : " + exppostingDetailsDebitList);

		String actpostingDetailsCreditList = listOfElements(postingDetailsCreditList);
		String exppostingDetailsCreditList = "[Vendor A, 1,400.00, Exchange Gain, 600.00]";

		System.err.println("postingDetailsCreditList ACT : " + actpostingDetailsCreditList);
		System.err.println("postingDetailsCreditList EXP : " + exppostingDetailsCreditList);

		String actpostingDetailsDebitSum = postingDetailsDebitSum.getText();
		String exppostingDetailsDebitSum = "2,000.00";

		System.err.println("postingDetailsDebitSum ACT : " + actpostingDetailsDebitSum);
		System.err.println("postingDetailsDebitSum EXP : " + exppostingDetailsDebitSum);

		String actpostingDetailsCreditSum = postingDetailsCreditSum.getText();
		String exppostingDetailsCreditSum = "2,000.00";

		System.err.println("postingDetailsCreditSum ACT : " + actpostingDetailsCreditSum);
		System.err.println("postingDetailsCreditSum EXP : " + exppostingDetailsCreditSum);

		Thread.sleep(2000);
		getDriver().navigate().refresh();

		if (actpostingDetailsDebitList.equalsIgnoreCase(exppostingDetailsDebitList)
				&& actpostingDetailsCreditList.equalsIgnoreCase(exppostingDetailsCreditList)
				&& actpostingDetailsDebitSum.equalsIgnoreCase(exppostingDetailsDebitSum)
				&& actpostingDetailsCreditSum.equalsIgnoreCase(exppostingDetailsCreditSum))

		{
			System.err.println(" Cheque Return As Execpted");
			return true;
		} else {
			System.err.println(" Cheque Return As NOT  Execpted");
			return false;
		}

	}

	public boolean checkSavingVoucherInPurchaseVOucherVAT()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		focusMainSearch("Purchases Voucher VAT");

		Thread.sleep(4589);

		waitToClick(newBtn);

		Thread.sleep(2000);
		click(documentNumberTxt);

		Thread.sleep(2000);
		click(vendorAccountTxt);
		vendorAccountTxt.sendKeys("Vendor A");
		Thread.sleep(2000);

		vendorAccountTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		removetTxt(voucherHeaderCurrency);
		voucherHeaderCurrency.sendKeys("AED");

		Thread.sleep(2000);

		voucherHeaderCurrency.sendKeys(Keys.TAB);

		voucherHeaderExchangeRate.click();

		Thread.sleep(2000);

		click(departmentTxt);
		removetTxt(departmentTxt);
		departmentTxt.sendKeys("DUBAI");
		Thread.sleep(2000);
		departmentTxt.sendKeys(Keys.TAB);

		Thread.sleep(1000);

		click(select1stRow_1stColumn);

		enter_WarehouseTxt.click();

		enter_WarehouseTxt.sendKeys(Keys.SPACE);

		int warehousecount = warehouseBodyComboList.size();

		for (int i = 0; i < warehousecount; i++) {
			String data = warehouseBodyComboList.get(i).getText();

			if (data.equalsIgnoreCase("HYDERABAD")) {
				warehouseBodyComboList.get(i).click();
				break;
			}
		}

		enter_WarehouseTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_ItemTxt));
		enter_ItemTxt.click();
		enter_ItemTxt.sendKeys(Keys.SPACE);
		int pvvGridItemListCount = pvvGridItemList.size();
		for (int i = 0; i < pvvGridItemListCount; i++) {
			String Item = pvvGridItemList.get(i).getText();
			if (Item.equalsIgnoreCase("STD RATE COGS ITEM")) {
				pvvGridItemList.get(i).click();
				break;
			}
		}
		enter_ItemTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		removetTxt(enter_TaxCode1);
		enter_TaxCode1.sendKeys("STD");
		Thread.sleep(2000);
		enter_TaxCode1.sendKeys(Keys.TAB);
		Thread.sleep(2000);

		enter_PurchaseAccountTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		click(select1stRow_9thColumn);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_Quantity));
		enter_Quantity.click();
		enter_Quantity.clear();
		enter_Quantity.sendKeys("1");

		Thread.sleep(2000);
		click(select1stRow_11thColumn);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_Rate));
		enter_Rate.click();
		enter_Rate.clear();
		enter_Rate.sendKeys("5");
		enter_Rate.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		click(enter_Gross);
		enter_Gross.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		click(select1stRow_14thColumn);

		enter_PvVat.click();

		Thread.sleep(2000);
		enter_PvVat.sendKeys(Keys.TAB);

		enter_PvTaxable.click();
		enter_PvTaxable.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		String docno = documentNumberTxt.getAttribute("value");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(voucherSaveBtn));
		voucherSaveBtn.click();

		Thread.sleep(2000);
		Thread.sleep(2000);

		click(billrefAdjuBills1stChkbox);

		click(billRefPickIcon);

		Thread.sleep(2000);

		click(billrefAdjuBills1stChkbox);

		click(billRefPickIcon);

		int count1 = billWiseGridRow1List.size();
		ArrayList<String> one = new ArrayList<String>();
		for (int i = 0; i < count1; i++) {
			String data1 = billWiseGridRow1List.get(i).getText();

			if (data1.isEmpty() == false) {
				if (i == 6) {
					data1 = "currency";
				}

				one.add(data1);
			}

		}
		String actbillWiseGridRow1List = one.toString();
		String expbillWiseGridRow1List = "[1, NDT58:1, 01/01/2021, 01/01/2021, currency, 105.65, 5.65, 5.25, 5.25, 100.00]";

		System.out.println("ActbillWiseGridRow1List : " + actbillWiseGridRow1List);
		System.out.println("ExpbillWiseGridRow1List : " + expbillWiseGridRow1List);
		Thread.sleep(2000);

		click(billRefOkBtn);

		boolean actSaving = checkBackgroundSavingMessage(docno);
		boolean expSaving = true;

		if (actSaving == expSaving)

		{
			System.err.println(" Purchase VAT Saved With Adjsutment ");
			return true;
		} else {
			System.err.println("Purchase VAT Saved With Adjsutment ");
			return false;
		}

	}

	public boolean checkPostingDetailsInPurchaseVoucherVAT() throws InterruptedException, AWTException, IOException {

		focusMainSearch("Purchases Voucher VAT");

		Thread.sleep(2500);

		voucherHomePageVoucherSelect("1");

		Thread.sleep(8965);

		click(toggleBtn);

		click(postingDetailsBtn);

		Thread.sleep(5500);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(postingDetailsDebitSum));

		String actpostingDetailsDebitList = listOfElements(postingDetailsDebitList);
		String exppostingDetailsDebitList = "[STD RATE COGS ACC INV, 2,400.00, VAT ADVANCE PURCHASE, 5.00]";

		System.err.println("postingDetailsDebitList ACT : " + actpostingDetailsDebitList);
		System.err.println("postingDetailsDebitList EXP : " + exppostingDetailsDebitList);

		String actpostingDetailsCreditList = listOfElements(postingDetailsCreditList);
		String exppostingDetailsCreditList = "[Vendor A, 73.50, PURCHASE VARIANCE, 2,300.00, Exchange Gain, 31.50]";

		System.err.println("postingDetailsCreditList ACT : " + actpostingDetailsCreditList);
		System.err.println("postingDetailsCreditList EXP : " + exppostingDetailsCreditList);

		String actpostingDetailsDebitSum = postingDetailsDebitSum.getText();
		String exppostingDetailsDebitSum = "2,405.00";

		System.err.println("postingDetailsDebitSum ACT : " + actpostingDetailsDebitSum);
		System.err.println("postingDetailsDebitSum EXP : " + exppostingDetailsDebitSum);

		String actpostingDetailsCreditSum = postingDetailsCreditSum.getText();
		String exppostingDetailsCreditSum = "2,405.00";

		System.err.println("postingDetailsCreditSum ACT : " + actpostingDetailsCreditSum);
		System.err.println("postingDetailsCreditSum EXP : " + exppostingDetailsCreditSum);

		Thread.sleep(2000);
		getDriver().navigate().refresh();

		Thread.sleep(2500);

		// prongHornStopAtAdminLevel();

		//logout();

		if (actpostingDetailsDebitList.equalsIgnoreCase(exppostingDetailsDebitList)
				&& actpostingDetailsCreditList.equalsIgnoreCase(exppostingDetailsCreditList)
				&& actpostingDetailsDebitSum.equalsIgnoreCase(exppostingDetailsDebitSum)
				&& actpostingDetailsCreditSum.equalsIgnoreCase(exppostingDetailsCreditSum))

		{
			System.err.println(" Cheque Return As Execpted");
			return true;
		} else {
			System.err.println(" Cheque Return As NOT  Execpted");
			return false;
		}

	}

	
	@FindBy(xpath="//label[text()='Customer Account']")
	public static WebElement custAccLabel;
	
	public boolean checkSavingMultipleVouchersofSalesInvoiceVATVoucher() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		


		Thread.sleep(2000);

		
		eraseAllTransactions();

		Thread.sleep(2000);
	

		
		ClickUsingJs(financialsMenu);

		click(financialsTransactionMenu);

		click(financialTransactionSalesMenu);

		click(salesInvoiceVATVoucher);

		Thread.sleep(4000);

		click(newBtn);

		Thread.sleep(2000);
		click(documentNumberTxt);

		Thread.sleep(1999);

		

		customerAccountTxt.click();
		customerAccountTxt.sendKeys("Customer B");
		Thread.sleep(2000);
		customerAccountTxt.sendKeys(Keys.TAB);

		Thread.sleep(1999);
		
		selectVoucherHeaderCurrency("USD");

		Thread.sleep(2000);

		Thread.sleep(2000);

		click(select1stRow_1stColumn);
		pvWareHouseTxt.click();
		pvWareHouseTxt.sendKeys(Keys.SPACE);

		int warehousecount = pvwareHouseListCount.size();

		System.err.println(warehousecount);

		for (int i = 0; i < warehousecount; i++) {
			String data = pvwareHouseListCount.get(i).getText();

			if (data.equalsIgnoreCase("HYDERABAD")) {
				pvwareHouseListCount.get(i).click();

				break;
			}
		}

		pvWareHouseTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		
		removetTxt(body_DeptTxt);
		body_DeptTxt.sendKeys("AMERICA");
		Thread.sleep(2000);
		body_DeptTxt.sendKeys(Keys.TAB);
		

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_ItemTxt));
		removetTxt(enter_ItemTxt);
		enter_ItemTxt.sendKeys("std");

		Thread.sleep(1999);

		enter_ItemTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		
		

		removetTxt(enterSalesTaxcode);
		enterSalesTaxcode.sendKeys("STD");

		Thread.sleep(2500);

		enterSalesTaxcode.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		click(select1stRow_5thColumn);

		Thread.sleep(2000);
		click(select1stRow_9thColumn);
		enter_AQTxt.sendKeys("12");
		enter_AQTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		enter_FQTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		click(select1stRow_12thColumn);
		Thread.sleep(2000);
		click(select1stRow_15thColumn);

		Thread.sleep(2000);
		enter_Rate.click();
		enter_Rate.clear();
		enter_Rate.sendKeys("18.45");
		enter_Rate.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		enter_Gross.click();
		enter_Gross.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(voucherSaveBtn));
		voucherSaveBtn.click();

		Thread.sleep(1999);
		billwisePick();

		String exp = "This Transaction will make the Stock Negative";
		String act = checkValidationMessage(exp);

		
		Thread.sleep(4000);
		//second Vocuher
		
		click(new_newBtn);
		Thread.sleep(1500);
		
		getAction().moveToElement(custAccLabel).build().perform();
		Thread.sleep(1500);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customerAccountTxt));
		customerAccountTxt.click();
		customerAccountTxt.sendKeys("Customer B");
		Thread.sleep(2000);
		customerAccountTxt.sendKeys(Keys.TAB);

		Thread.sleep(1999);
		
		selectVoucherHeaderCurrency("USD");

		Thread.sleep(2000);

		Thread.sleep(2000);

		click(select1stRow_1stColumn);
		pvWareHouseTxt.click();
		pvWareHouseTxt.sendKeys(Keys.SPACE);

		//int warehousecount = pvwareHouseListCount.size();

		System.err.println(warehousecount);

		for (int i = 0; i < warehousecount; i++) {
			String data = pvwareHouseListCount.get(i).getText();

			if (data.equalsIgnoreCase("HYDERABAD")) {
				pvwareHouseListCount.get(i).click();

				break;
			}
		}

		pvWareHouseTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		
		removetTxt(body_DeptTxt);
		body_DeptTxt.sendKeys("AMERICA");
		Thread.sleep(2000);
		body_DeptTxt.sendKeys(Keys.TAB);
		

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_ItemTxt));
		removetTxt(enter_ItemTxt);
		enter_ItemTxt.sendKeys("std");

		Thread.sleep(1999);

		enter_ItemTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		
		

		removetTxt(enterSalesTaxcode);
		enterSalesTaxcode.sendKeys("STD");

		Thread.sleep(2500);

		enterSalesTaxcode.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		click(select1stRow_5thColumn);

		Thread.sleep(2000);
		click(select1stRow_9thColumn);
		enter_AQTxt.sendKeys("15");
		enter_AQTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		enter_FQTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		click(select1stRow_12thColumn);
		Thread.sleep(2000);
		click(select1stRow_15thColumn);

		Thread.sleep(2000);
		enter_Rate.click();
		enter_Rate.clear();
		enter_Rate.sendKeys("22.58");
		enter_Rate.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		enter_Gross.click();
		enter_Gross.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(voucherSaveBtn));
		voucherSaveBtn.click();

		Thread.sleep(1999);
		billwisePick();

		String exp1 = "This Transaction will make the Stock Negative";
		String act1 = checkValidationMessage(exp1);
		
		
		//Third Voucher
		
		//click(documentNumberTxt);

		Thread.sleep(1999);

		getAction().moveToElement(custAccLabel).build().perform();
		Thread.sleep(1500);

		customerAccountTxt.click();
		customerAccountTxt.sendKeys("Customer B");
		Thread.sleep(2000);
		customerAccountTxt.sendKeys(Keys.TAB);

		Thread.sleep(1999);
		
		selectVoucherHeaderCurrency("USD");

		Thread.sleep(2000);

		Thread.sleep(2000);

		click(select1stRow_1stColumn);
		pvWareHouseTxt.click();
		pvWareHouseTxt.sendKeys(Keys.SPACE);

	

		System.err.println(warehousecount);

		for (int i = 0; i < warehousecount; i++) {
			String data = pvwareHouseListCount.get(i).getText();

			if (data.equalsIgnoreCase("HYDERABAD")) {
				pvwareHouseListCount.get(i).click();

				break;
			}
		}

		pvWareHouseTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		
		removetTxt(body_DeptTxt);
		body_DeptTxt.sendKeys("AMERICA");
		Thread.sleep(2000);
		body_DeptTxt.sendKeys(Keys.TAB);
		

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_ItemTxt));
		removetTxt(enter_ItemTxt);
		enter_ItemTxt.sendKeys("std");

		Thread.sleep(1999);

		enter_ItemTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		
		

		removetTxt(enterSalesTaxcode);
		enterSalesTaxcode.sendKeys("STD");

		Thread.sleep(2500);

		enterSalesTaxcode.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		click(select1stRow_5thColumn);

		Thread.sleep(2000);
		click(select1stRow_9thColumn);
		enter_AQTxt.sendKeys("25");
		enter_AQTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		enter_FQTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		click(select1stRow_12thColumn);
		Thread.sleep(2000);
		click(select1stRow_15thColumn);

		Thread.sleep(2000);
		enter_Rate.click();
		enter_Rate.clear();
		enter_Rate.sendKeys("13.05");
		enter_Rate.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		enter_Gross.click();
		enter_Gross.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(voucherSaveBtn));
		voucherSaveBtn.click();

		Thread.sleep(1999);
		billwisePick();

		String exp2 = "This Transaction will make the Stock Negative";
		String act2 = checkValidationMessage(exp2);

		
		
		
		

		
		 if (act.equalsIgnoreCase(exp)) {
			System.err.println(" Negative Message Displayed*****************************");
			return false;
		}

		else {
			System.err.println(" Test FAIl: Sales Invoice VAT Voucher Saved ");
			return false;
		}

	
		
	}

	
	@FindBy(xpath="//*[@id='id_Adjustment_Grid_body']/tr[2]/td[13]")
	public static WebElement adjust2ndRow12thCol;
	
	@FindBy(xpath="//*[@id='id_limit']")
	public static WebElement adjustmentTxt;
	
	public boolean checkSavingReceiptsVATVoucher() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		
		Thread.sleep(3000);

		ClickUsingJs(financialsMenu);

		click(financialsTransactionMenu);

		click(cashAndBankMenu);

		click(recepitsVATVoucher);

		Thread.sleep(4000);

		click(newBtn);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(newCashBankAccountTxt));
		newCashBankAccountTxt.click();

		newCashBankAccountTxt.sendKeys(Keys.SPACE);

		int cashAndBAnkAccountListCount = cashAndBAnkAccountList.size();

		System.err.println("cashAndBAnkAccountListCount   : " + cashAndBAnkAccountListCount);

		for (int i = 0; i < cashAndBAnkAccountListCount; i++) {
			String data = cashAndBAnkAccountList.get(i).getText();

			if (data.equalsIgnoreCase("Bank")) {
				cashAndBAnkAccountList.get(i).click();

				break;
			}
		}

		newCashBankAccountTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		selectVoucherHeaderCurrency("USD");
		Thread.sleep(2000);

	/*	getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(departmentTxt));

		departmentTxt.click();
		departmentTxt.sendKeys(Keys.SHIFT, Keys.HOME, Keys.BACK_SPACE);
		departmentTxt.sendKeys(Keys.SPACE);
		Thread.sleep(2000);
		int departmentcount = departmentListCount.size();

		System.err.println(departmentcount);

		for (int i = 0; i < departmentcount; i++) {
			String data = departmentListCount.get(i).getText();

			if (data.equalsIgnoreCase("AMERICA")) {
				departmentListCount.get(i).click();
				break;
			}
		}

		Thread.sleep(1000);

		departmentTxt.sendKeys(Keys.TAB);

		Thread.sleep(2500);
		Thread.sleep(2500);*/

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(placeofSupplyTxt));
		placeofSupplyTxt.click();
		placeofSupplyTxt.sendKeys(Keys.SPACE);

		int placeOfSupplyListCount = placeofSupplyList.size();

		System.err.println(placeOfSupplyListCount);

		for (int i = 0; i < placeOfSupplyListCount; i++) {
			String data = placeofSupplyList.get(i).getText();

			if (data.equalsIgnoreCase("Abu Dhabi")) {
				placeofSupplyList.get(i).click();

				break;
			}
		}

		placeofSupplyTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_1stColumn));
		select1stRow_1stColumn.click();

		receiptsBody_DeptTxt.click();
		receiptsBody_DeptTxt.sendKeys("AMERICA");
		Thread.sleep(2000);
		receiptsBody_DeptTxt.sendKeys(Keys.TAB);
		
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_AccountTxt));
		enter_AccountTxt.click();
		enter_AccountTxt.sendKeys("Customer B");

		Thread.sleep(2000);

		enter_AccountTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		
		

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enterReceiptsVATTaxCode));
		removetTxt(enterReceiptsVATTaxCode);
		enterReceiptsVATTaxCode.sendKeys("STD");
		Thread.sleep(2000);
		enterReceiptsVATTaxCode.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_Amount));
		enter_Amount.sendKeys("150");
		enter_Amount.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(billRefPartyName));

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(billRefGridSecondRowAdjustmentAmtTxt));
		click(billRefGridSecondRowAdjustmentAmtTxt);

		click(billRefPickIcon);

		Thread.sleep(2000);

		click(billRefGridSecondRowAdjustmentAmtTxt);
		
		click(adjust2ndRow12thCol);
		
		click(adjustmentTxt);
		adjustmentTxt.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
		
		adjustmentTxt.sendKeys("75");
		
		click(newReferenceTxt);

		click(billRefPickIcon);

		String actList = listOfElements(billRefRow1List);
		String expList = "[1, NDT55:1, "+currentDate()+", "+currentDate()+", $, 221.40, 221.40, 0.00, 0.00, 0.00]";
		
		String actList2 = listOfElements(billRefRow2List);
		String expList2 = "[2, NDT55:2, "+currentDate()+", "+currentDate()+", $, 338.70, 338.70, 75.00, 75.00, 0.00]";
		
		String actList3 = listOfElements(billRefRow3List);
		String expList3 = "[3, NDT55:3, "+currentDate()+", "+currentDate()+", $, 326.25, 326.25, 0.00, 0.00, 0.00]";

		System.err.println(" ACT List: " + actList);
		System.err.println(" EXP List: " + expList);
		
		System.err.println(" ACT List: " + actList2);
		System.err.println(" EXP List: " + expList2);
		
		System.err.println(" ACT List: " + actList3);
		System.err.println(" EXP List: " + expList3);

		click(billRefOkBtn);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNumberTxt));
		String docno = documentNumberTxt.getAttribute("value");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(voucherSaveBtn));
		voucherSaveBtn.click();

		boolean actSaving = checkBackgroundSavingMessage(docno);
		boolean expSaving = true;

		System.err.println(" Saving voucher: " + actSaving + " Value Exp: " + expSaving);

		if (actList.equalsIgnoreCase(expList)  && actList2.equalsIgnoreCase(expList2) && actList3.equalsIgnoreCase(expList3)&& actSaving == expSaving) {

			return true;
		} else {

			return false;
		}

	
	}
	
	
	public boolean checkPostinginManualAdjustmentforMultipleDebitVocuhers() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		
		focusMainSearch("Manual Adjustment");
		Thread.sleep(2500);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_ARAPDrpdwn));
		Select ARAP = new Select(MA_ARAPDrpdwn);
		ARAP.selectByValue("0");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_AccountDrpdwn));
		MA_AccountDrpdwn.sendKeys("Customer B");
		Thread.sleep(1999);
		MA_AccountDrpdwn.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_DepDrpDwn));
		MA_DepDrpDwn.sendKeys("AMERICA");
		Thread.sleep(1999);
		MA_DepDrpDwn.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_CurrencyDrpDwn));
		MA_CurrencyDrpDwn.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME);
		MA_CurrencyDrpDwn.sendKeys("USD");
		Thread.sleep(1999);
		MA_CurrencyDrpDwn.sendKeys(Keys.TAB);

		Thread.sleep(1000);
		
		click(filterCollapseBtn);
		Thread.sleep(1500);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MA_LoadBtn));
		MA_LoadBtn.click();

		Thread.sleep(1999);

		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();

		String docdate = df.format(date);

		int count = MA_DebitSideRow1list.size();
		ArrayList<String> Debit = new ArrayList<String>();

		for (int i = 0; i < count; i++) {
			String data = MA_DebitSideRow1list.get(i).getText();

			if (data.isEmpty() == false) {
				Debit.add(data);
			}

		}

		String actDebitSideList = Debit.toString();
		String expDebitSideList = "[1, NDT55:1, "+getCurrentDate1()+", "+getCurrentDate1()+", $, 221.40, 221.40, 0.00, 0.00, 0.00, 0.00, 0.00]";

		System.err.println(" Actual Debit List  : " + actDebitSideList);
		System.err.println(" Exp    Debit List  : " + expDebitSideList);
		
		
		int count2 = MA_DebitSideRow2list.size();
		ArrayList<String> Debit2 = new ArrayList<String>();

		for (int i = 0; i < count; i++) {
			String data = MA_DebitSideRow2list.get(i).getText();

			if (data.isEmpty() == false) {
				Debit2.add(data);
			}

		}

		String actDebitSide2List = Debit2.toString();
		String expDebitSide2List = "[2, NDT55:2, "+getCurrentDate1()+", "+getCurrentDate1()+", $, 338.70, 263.70, 0.00, 0.00, 0.00, 0.00, 75.00]";

		System.err.println(" Actual Debit List  : " + actDebitSide2List);
		System.err.println(" Exp    Debit List  : " + expDebitSide2List);
		
		
		int count3 = MA_DebitSideRow1list.size();
		ArrayList<String> Debit3 = new ArrayList<String>();

		for (int i = 0; i < count; i++) {
			String data = MA_DebitSideRow3list.get(i).getText();

			if (data.isEmpty() == false) {
				Debit3.add(data);
			}

		}

		String actDebitSide3List = Debit3.toString();
		String expDebitSide3List = "[3, NDT55:3, "+getCurrentDate1()+", "+getCurrentDate1()+", $, 326.25, 326.25, 0.00, 0.00, 0.00, 0.00, 0.00]";

		System.err.println(" Actual Debit List  : " + actDebitSide3List);
		System.err.println(" Exp    Debit List  : " + expDebitSide3List);
		

		int count1 = MA_CreditSideRow1list.size();
		ArrayList<String> Credit = new ArrayList<String>();

		for (int i = 0; i < count1; i++) {
			String data = MA_CreditSideRow1list.get(i).getText();

			if (data.isEmpty() == false) {
				Credit.add(data);
			}

		}

		String actCreditSideList = Credit.toString();
		String expCreditSideList = "[1, NDT57:1, "+getCurrentDate1()+", "+getCurrentDate1()+", $, 75.00, 75.00, 0.00, 0.00, 0.00, 0.00, 0.00]";

		System.err.println(" Actual Credit List  : " + actCreditSideList);
		System.err.println(" Exp    Credit List  : " + expCreditSideList);

		Thread.sleep(2000);

		click(debitRow3Chkbox);

		Thread.sleep(2000);

		click(CreditRow1Chkbox);

		Thread.sleep(2000);
		
		int count4 = MA_CreditSideRow1list.size();
		ArrayList<String> CreditAfterChkBox = new ArrayList<String>();

		for (int i = 0; i < count1; i++) {
			String data = MA_CreditSideRow1list.get(i).getText();

			if (data.isEmpty() == false) {
				CreditAfterChkBox.add(data);
			}

		}

		String actCreditSideAfterList = CreditAfterChkBox.toString();
		String expCreditSideAfterList = "[1, NDT57:1, "+getCurrentDate1()+", "+getCurrentDate1()+", $, 75.00, 75.00, 75.00, 0.00, 0.00, 0.00, 0.00]";

		System.err.println(" Actual Credit List  : " + actCreditSideAfterList);
		System.err.println(" Exp    Credit List  : " + expCreditSideAfterList);
		

		String actdebitTotal = debitTotal.getText();
		String expdebitTotal = "326.25";

		System.err.println(" debitTotal : " + actdebitTotal + " Value  Exp: " + expdebitTotal);

		Thread.sleep(2000);

		String actcreditTotal = creditTotal.getText();
		String expcreditTotal = "75.00";

		System.err.println(" creditTotal : " + actcreditTotal + " Value  Exp: " + expcreditTotal);

		Thread.sleep(2000);

		click(MA_OkBtn);

		String expMessage = "Record Saved Succesfully";
		String actMessage = checkValidationMessage(expMessage);

		System.err.println(" Message ACT: " + actMessage);
		System.err.println(" Message EXP: " + expMessage);
		
		
		if(actDebitSideList.equalsIgnoreCase(expDebitSideList) && actDebitSide2List.equalsIgnoreCase(expDebitSide2List)
				&& actDebitSide3List.equalsIgnoreCase(expDebitSide3List) && actCreditSideList.equalsIgnoreCase(expCreditSideList)
				&& actCreditSideAfterList.equalsIgnoreCase(expCreditSideAfterList)  && actdebitTotal.equalsIgnoreCase(expdebitTotal)
				&& actcreditTotal.equalsIgnoreCase(expcreditTotal) && actMessage.equalsIgnoreCase(expMessage))
		{
			return true;

		}
		else
		{
			return false;
		}
	}
	
	
	public boolean checkAdjustmentVoucherinReceiptsVATAfterManualAdjustment() throws InterruptedException
	{
		Thread.sleep(3000);

		ClickUsingJs(financialsMenu);

		click(financialsTransactionMenu);

		click(cashAndBankMenu);

		click(recepitsVATVoucher);

		Thread.sleep(2000);

		click(homePageRow1Chkbox);

		click(editBtn);

		Thread.sleep(3500);
		
		

		String actEntryPage1 = listOfElements(entryPageRow1List);
		String expEntryPage1 = "[1, AMERICA, Customer B, Std Rate, 75.00, NDT55:3 : "+currentDateMonth()+", 3.57]";

		System.err.println(" Row1 ACT :" + actEntryPage1);
		System.err.println(" Row1 EXP :" + expEntryPage1);
		
		logout();
		Thread.sleep(5000);

		if (actEntryPage1.equalsIgnoreCase(expEntryPage1)) {
			return true;
		} else {
			return false;

		}

	
	}
	
	
	public ManualAdjustmentsPage(WebDriver driver) {

		PageFactory.initElements(driver, this);
	}

}
