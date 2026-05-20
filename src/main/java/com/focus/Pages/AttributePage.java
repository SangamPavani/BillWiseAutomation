package com.focus.Pages;

import java.awt.AWTException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.focus.base.BaseEngine;
import com.focus.supporters.ExcelReader;
import com.focus.utilities.POJOUtility;

public class AttributePage extends BaseEngine {

	// SettingsMenu
	@FindBy(xpath = "//*[@id='16']/span")
	public static WebElement settings;

	// config transactions
	// *[@id="17"]/span

	@FindBy(xpath = "//*[@id='17']/span")
	public static WebElement Setting_PerferenceMenu;

	@FindBy(xpath = "(//span[contains(text(),'Preferences')])[2]")
	public static WebElement Setting_Perference;

	// *[@id="174"]

	// config transactions preferences
	// *[@id='salesnav']/li[6]/a
	@FindBy(xpath = "(//*[@id='16'])[2]")
	public static WebElement Setting_PerferenceMenuPreferencesMaster;

	// Label of Masters tab
	@FindBy(xpath = "//a[contains(text(),'Masters')]")
	public static WebElement LblMaster;

	@FindBy(xpath = "//*[@id='ProductAttributesValues']")
	public static WebElement attributes;

	@FindBy(xpath = "//*[@id='grdProductAttributesValues_body']/tr[1]/td[3]")
	public static WebElement attribute0;

	@FindBy(xpath = "//*[@id='txtProductName']")
	public static WebElement attributeAfterclick;

	@FindBy(xpath = "//*[@id='grdProductAttributesValues_body']/tr[2]/td[3]")
	public static WebElement attribute1;

	// update
	@FindBy(xpath = "//*[@id='updateButton']/i")
	public static WebElement updtBtn;

	// close
	@FindBy(xpath = "//*[@id='btnCustomizeClose']")
	public static WebElement closeBtn;

	/*
	 * @FindBy(xpath="//*[@id='navbarSupportedContent2']/ul/li[3]") public static
	 * WebElement closeBtn;
	 */

	@FindBy(xpath = "//*[@id='Dashboard_ddlList']/li[2]/a")
	public static WebElement dashboard;

	@FindBy(xpath = "//*[@id='1']")
	public static WebElement homeMenu;

	// Masters Menu
	@FindBy(xpath = "//a[@id='1000']//span[contains(text(),'Masters')]")
	public static WebElement mastersMenu;

	// Items Menu
	@FindBy(xpath = "//*[@id='221']")
	public static WebElement itemMenu;

	@FindBy(xpath = "//*[@id='1105']")
	public static WebElement item;

	@FindBy(xpath = "//*[@title='Fruits']//..//input")
	public static WebElement fruitsItemChkbox;

	@FindBy(xpath = "//*[@id='masterFirstDiv']/nav/div/nav/ol/li[3]/a")
	public static WebElement LblItem;

	// *[@id='ulCommonlyUsedRibbon']/li[1]/a

	@FindBy(xpath = "//*[@id='btnNew']")
	public static WebElement newItemBtn;

	@FindBy(xpath = "//*[@data-bs-toggle='tab'][contains(text(),'Attribute-0')]")
	public static WebElement attribute0Tab;

	@FindBy(xpath = "//*[@data-bs-toggle='tab'][contains(text(),'Attribute-1')]")
	public static WebElement attribute1Tab;

	@FindBy(xpath = "//*[@id='tabMega']/span")
	public static WebElement itemLasttab;

	@FindBy(xpath = "//*[@id='GenerateNewMasterModel']/div/div[2]/div[1]/div/ul/li")
	public static List<WebElement> itemMasterTabList;

	public static String checkValidationMessage(String ExpMessage)
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		try {
			getFluentWebDriverWait().until(ExpectedConditions.visibilityOf(errorMessage));
			String actErrorMessage = errorMessage.getText();
			String expErrorMessage = ExpMessage;

			try {

				getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(errorMessageCloseBtn));
				errorMessageCloseBtn.click();

				System.out.println("ValidationMessage  ACT:  " + actErrorMessage);
				System.out.println("ValidationMessage  EXP:  " + expErrorMessage);

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

	private static String resPass = "Pass";
	private static String resFail = "Fail";
	private static ExcelReader excelReader;

	private static String xlfile = getBaseDir() + "\\src\\main\\resources\\testdata\\FocusTestData.xlsx";

	public static String xlSheetName = "AttributePage";

	public boolean checkLoginTOBRS()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		excelReader = new ExcelReader(POJOUtility.getExcelPath());

		getDriver().navigate().refresh();

		excelReader = new ExcelReader(POJOUtility.getExcelPath());
		Thread.sleep(1999);

		getDriver().navigate().refresh();

		Thread.sleep(1999);

		LoginPage lp = new LoginPage(getDriver());

		String unamelt = excelReader.getCellData(xlSheetName, 9, 5);

		String pawslt = excelReader.getCellData(xlSheetName, 10, 5);

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
				System.out.println("q" + elementCount.get(i).getText());
				elementCount.get(i).click();
			}

		}

		Thread.sleep(2000);

		lp.clickOnSignInBtn();

		Thread.sleep(4000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(companyLogo));
		//companyLogo.click();

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

	public boolean checkSavingVoucherInRecepictsVAT()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException {

		Thread.sleep(2000);
		checkEraseAllTrans();

		Thread.sleep(2000);
	/*	
		logout();
		
		Thread.sleep(2323);
		
		
		prongHornStopAtAdminLevel();
		
		Thread.sleep(2323);
		
		prongHornStartAtAdminLevel();
		
		
		Thread.sleep(2323);
		
		checkLoginToSelectedCompany("BRS", "su", "su");
		
		Thread.sleep(2323);
		
		System.err.println(" Company Logined after Successful Prong Retart");
		
		Thread.sleep(2323);*/
		
		getDriver().navigate().refresh();
		Thread.sleep(4000);
		
		clickOn(financialsMenu);

		clickOn(financialsTransactionMenu);

		clickOn(cashAndBankMenu);

		clickOn(recepitsVATVoucher);

		Thread.sleep(6000);

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

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_1stColumn));
		select1stRow_1stColumn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_AccountTxt));
		enter_AccountTxt.click();
		enter_AccountTxt.sendKeys("Customer New");

		getFluentWebDriverWait().until(ExpectedConditions.visibilityOfAllElements(bodyAccountListInGrid));
		int accountCount = bodyAccountListInGrid.size();

		System.err.println(accountCount);

		for (int i = 0; i < accountCount; i++) {
			String data = bodyAccountListInGrid.get(i).getText();

			if (data.equalsIgnoreCase("Customer New Reference")) {
				getFluentWebDriverWait().until(ExpectedConditions.visibilityOfAllElements(bodyAccountListInGrid));
				bodyAccountListInGrid.get(i).click();

				break;
			}
		}

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_AccountTxt));
		enter_AccountTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enterReceiptsVATTaxCode));
		enterReceiptsVATTaxCode.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_Amount));
		enter_Amount.sendKeys("100");
		enter_Amount.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		billwisePick();
		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNumberTxt));
		String docno = documentNumberTxt.getAttribute("value");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(voucherSaveBtn));
		voucherSaveBtn.click();

		boolean actSaving = checkBackgroundSavingMessage(docno);
		boolean expSaving = true;

		if (actSaving == expSaving)

		{
			System.err.println("Recepits VAT Voucher Saved With New Reference  ");
			return true;
		} else {
			System.err.println("Recepits VAT Voucher Saved With New Reference  ");
			return false;
		}

	}

	public boolean checkSavingVocuherSalesInoiveVATAdjustingRecepictsVAT()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {

		Thread.sleep(3000);

		clickOn(financialsMenu);
		clickOn(financialsTransactionMenu);
		clickOn(financialTransactionSalesMenu);
		clickOn(salesInvoiceVATVoucher);

		Thread.sleep(6000);

	click(newBtn);

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNumberTxt));
		documentNumberTxt.click();

		Thread.sleep(1999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customerAccountTxt));
		customerAccountTxt.click();
		customerAccountTxt.sendKeys(Keys.END);
		customerAccountTxt.sendKeys(Keys.SHIFT, Keys.HOME);
		customerAccountTxt.sendKeys("customer new");
		customerAccountTxt.sendKeys(Keys.SPACE);

		int customercount = customerAccountListCount.size();

		System.err.println(customercount);

		for (int i = 0; i < customercount; i++) {
			String data = customerAccountListCount.get(i).getText();

			if (data.equalsIgnoreCase("Customer New Reference")) {
				customerAccountListCount.get(i).click();

				break;
			}
		}

		customerAccountTxt.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(departmentTxt));
		departmentTxt.click();
		departmentTxt.sendKeys(Keys.END);
		departmentTxt.sendKeys(Keys.SHIFT, Keys.HOME);
		departmentTxt.sendKeys(Keys.SPACE);

		int departmentcount = departmentListCount.size();

		System.err.println(departmentcount);

		for (int i = 0; i < departmentcount; i++) {
			String data = departmentListCount.get(i).getText();

			if (data.equalsIgnoreCase("DUBAI")) {
				departmentListCount.get(i).click();

				Thread.sleep(1000);

				break;
			}
		}

		departmentTxt.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(salesInvoiceVATPlaceOFSupply));
		salesInvoiceVATPlaceOFSupply.click();
		salesInvoiceVATPlaceOFSupply.sendKeys(Keys.END);
		salesInvoiceVATPlaceOFSupply.sendKeys(Keys.SHIFT, Keys.HOME);
		salesInvoiceVATPlaceOFSupply.sendKeys(Keys.SPACE);

		int placeOFSupplyListCount = placeOFSupplyList.size();

		System.err.println("placeOFSupplyListCount   : " + placeOFSupplyListCount);

		for (int i = 0; i < placeOFSupplyListCount; i++) {
			String data = placeOFSupplyList.get(i).getText();

			if (data.equalsIgnoreCase("DUBAI")) {
				placeOFSupplyList.get(i).click();

				break;
			}
		}

		Thread.sleep(2000);

		salesInvoiceVATPlaceOFSupply.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_1stColumn));
		select1stRow_1stColumn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(pvWareHouseTxt));
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

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_ItemTxt));
		enter_ItemTxt.sendKeys(Keys.END);
		enter_ItemTxt.sendKeys(Keys.SHIFT, Keys.HOME);
		enter_ItemTxt.sendKeys(Keys.BACK_SPACE);
		enter_ItemTxt.sendKeys(Keys.SPACE);
		enter_ItemTxt.sendKeys("std");

		Thread.sleep(1999);

		enter_ItemTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_TaxCode));
		enter_TaxCode.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
		enter_TaxCode.sendKeys("STD Rate");
		Thread.sleep(2000);
		enter_TaxCode.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_5thColumn));
		select1stRow_5thColumn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_8thColumn));
		select1stRow_8thColumn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_AQTxt));
		enter_AQTxt.sendKeys("10");
		enter_AQTxt.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_FQTxt));
		enter_FQTxt.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_11thColumn));
		select1stRow_11thColumn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_14thColumn));
		select1stRow_14thColumn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_Rate));
		enter_Rate.click();
		enter_Rate.clear();
		enter_Rate.sendKeys("10");
		enter_Rate.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_Gross));
		enter_Gross.click();
		enter_Gross.sendKeys(Keys.TAB);

		String docno = documentNumberTxt.getAttribute("value");

		Thread.sleep(1999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(voucherSaveBtn));
		voucherSaveBtn.click();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(billRefPartyName));
		String actPartyName = billRefPartyName.getText();
		String expPartyName = "Customer New Reference (Customer New Reference)";

		System.out.println("Bill wise Screen Cutomer Name " + actPartyName + "  Value Expected  " + expPartyName);

		int Adjustbills = billRefAdjustBillsGrid.size();

		String actAdjustbills = Integer.toString(Adjustbills);

		String expAdjustbills = "1";

		System.err.println("actAdjustbills : " + actAdjustbills + " Value Expected  : " + expAdjustbills);

		String expBillNewReference = "0.00";
		String expBillTransactionCurrency = "100.00";
		String expBillBaseCurrency = "100.00";
		String expBillLocalCurrency = "7.00";
		String expBillBalanceNewRefAmount = "0.00";

		String expbillRefAdjustAmountInTransCurency = "0.00";
		String expbillRefBalanceAmountAdjustInTrnasCurrency = "100.00";

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(billRefNewReferenceTxt));
		String actBillNewReference = billRefNewReferenceTxt.getAttribute("value");
		String actBillTransactionCurrency = billRefTransactionCurency.getText();
		String actBillBaseCurrency = billRefBaseCurrency.getText();
		String actBillLocalCurrency = localCurrencyDhs.getText();
		String actBillBalanceNewRefAmount = balanceNewReferenceAmt.getText();
		String actbillRefAdjustAmountInTransCurency = billRefAdjustAmountInTransCurency.getText();
		String actbillRefBalanceAmountAdjustInTrnasCurrency = billRefBalanceAmountAdjustInTrnasCurrency.getText();

		String actconversationRateBaseCurrencyRatePick = conversationRateBaseCurrencyRate2.getText();
		String actconversationRateLocalCurrencyRatePick = conversationRateLocalCurrencyRate.getText();

		String expconversationRateBaseCurrencyRatePick = "1";
		String expconversationRateLocalCurrencyRatePick = "0.07";

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(gridOrginalAmtRow1));
		String actgridOrginalAmtRow1 = gridOrginalAmtRow1.getText();
		String actgridBalanceAmtRow1 = gridBalanceAmtRow1.getText();
		String actgridAdjustmentAmtRow1 = gridAdjustmentAmtRow1.getText();
		String actgridAdjustmentBillsRow1DocNo = billRefAdjustBillsRow1DocNo.getText();

		String expgridOrginalAmtRow1 = "100.00";
		String expgridBalanceAmtRow1 = "100.00";
		String expgridAdjustmentAmtRow1 = "0.00";
		String expgridAdjustmentBillsRow1DocNo = "NDT57:1";

		System.out.println("actgridOrginalAmtRow1    :" + actgridOrginalAmtRow1 + "       " + "expgridOrginalAmtRow1 :"
				+ expgridOrginalAmtRow1);
		System.out.println("actgridBalanceAmtRow1    :" + actgridBalanceAmtRow1 + "       " + "expgridBalanceAmtRow1 :"
				+ expgridBalanceAmtRow1);
		System.out.println("actgridAdjustmentAmtRow1 :" + actgridAdjustmentAmtRow1 + "    "
				+ "expgridAdjustmentAmtRow1:" + expgridAdjustmentAmtRow1);
		System.out.println("actgridAdjustmentBillsRow1DocNo    :" + actgridAdjustmentBillsRow1DocNo + "       "
				+ "expgridOrginalAmtRow1 :" + expgridAdjustmentBillsRow1DocNo);

		Thread.sleep(2000);
		clickOn(billRefGridFirstRowAdjustmentAmtTxt);

		clickOn(billRefPickIcon);

		String expBillNewReferencePick = "0.00";
		String expBillTransactionCurrencyPick = "100.00";
		String expBillBaseCurrencyPick = "100.00";
		String expBillLocalCurrencyPick = "7.00";
		String expBillBalanceNewRefAmountPick = "0.00";
		String expbillRefAdjustAmountInTransCurencyPick = "100.00";
		String expbillRefBalanceAmountAdjustInTrnasCurrencyPick = "0.00";

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(billRefNewReferenceTxt));

		String actBillNewReferencePick = billRefNewReferenceTxt.getAttribute("value");
		String actBillTransactionCurrencyPick = billRefTransactionCurency.getText();
		String actBillBaseCurrencyPick = billRefBaseCurrency.getText();
		String actBillLocalCurrencyPick = localCurrencyDhs.getText();
		String actBillBalanceNewRefAmountPick = balanceNewReferenceAmt.getText();
		String actbillRefAdjustAmountInTransCurencyPick = billRefAdjustAmountInTransCurency.getText();
		String actbillRefBalanceAmountAdjustInTrnasCurrencyPick = billRefBalanceAmountAdjustInTrnasCurrency.getText();

		System.out.println(
				"*********************************************************************************************************");

		System.out.println("Bill reference Adjustment Bills  :" + actAdjustbills + "                          "
				+ "expadjustBills :" + expAdjustbills);
		System.out.println("actBillNewReference :             " + actBillNewReference + "                    "
				+ "expBillNewReference :" + expBillNewReference);
		System.out.println("actBillTransactionCurrency       :" + actBillTransactionCurrency + "            "
				+ "expBillTransactionCurrency :" + expBillTransactionCurrency);
		System.out.println("actBillBaseCurrency :             " + actBillBaseCurrency + "                   "
				+ "expBillBaseCurrency :" + expBillBaseCurrency);
		System.out.println("actBillLocalCurrency :            " + actBillLocalCurrency + "                   "
				+ "expBillLocalCurrency :" + expBillLocalCurrency);
		System.out.println("actBillBalanceNewRefAmount :      " + actBillBalanceNewRefAmount + "            "
				+ "expBillBalanceNewRefAmount :" + expBillBalanceNewRefAmount);

		System.out.println("actbillRefAdjustAmountInTransCurency         :" + actbillRefAdjustAmountInTransCurency
				+ "       " + "expbillRefAdjustAmountInTransCurency :" + expbillRefAdjustAmountInTransCurency);
		System.out.println("actbillRefBalanceAmountAdjustInTrnasCurrency :"
				+ actbillRefBalanceAmountAdjustInTrnasCurrency + "       "
				+ "expbillRefBalanceAmountAdjustInTrnasCurrency :" + expbillRefBalanceAmountAdjustInTrnasCurrency);

		////// Pick

		System.out.println("actBillNewReferencePick :              " + actBillNewReferencePick + "              "
				+ "expBillNewReferencePick :" + expBillNewReferencePick);
		System.out.println("actBillTransactionCurrencyPick :       " + actBillTransactionCurrencyPick + "     "
				+ "expBillTransactionCurrencyPick :" + expBillTransactionCurrencyPick);
		System.out.println("actBillBaseCurrencyPick :              " + actBillBaseCurrencyPick + "            "
				+ "expBillBaseCurrencyPick :" + expBillBaseCurrencyPick);
		System.out.println("actBillLocalCurrencyPick :             " + actBillLocalCurrency + "                "
				+ "expBillLocalCurrencyPick :" + expBillLocalCurrency);
		System.out.println("actBillBalanceNewRefAmountPick :       " + actBillBalanceNewRefAmountPick + " "
				+ "expBillBalanceNewRefAmountPick :" + expBillBalanceNewRefAmountPick);
		System.out.println("actconversationRateBaseCurrRatePick:   " + actconversationRateBaseCurrencyRatePick + "  "
				+ "expconversationRateBaseCurrencyRatePick :" + expconversationRateBaseCurrencyRatePick);
		System.out.println("actconversationRateLocalCurRatePick :  " + actconversationRateLocalCurrencyRatePick + " "
				+ "expconversationRateLocalCurrencyRatePick :" + expconversationRateLocalCurrencyRatePick);

		System.out.println("actbillRefAdjustAmountInTransCurencyPick :       "
				+ actbillRefAdjustAmountInTransCurencyPick + "       " + "expbillRefAdjustAmountInTransCurencyPick :"
				+ expbillRefAdjustAmountInTransCurencyPick);
		System.out.println(
				"actbillRefBalanceAmountAdjustInTrnasCurrencyPick :" + actbillRefBalanceAmountAdjustInTrnasCurrencyPick
						+ "       " + "expbillRefBalanceAmountAdjustInTrnasCurrencyPick :"
						+ expbillRefBalanceAmountAdjustInTrnasCurrencyPick);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(billRefOkBtn));
		billRefOkBtn.click();

		if (errorMessage.getText().equalsIgnoreCase("This Transaction will make the Stock Negative")) {
			clickOn(errorMessageCloseBtn);
		}

		Thread.sleep(250);

		boolean actSaving = checkVoucherSavingMessage2(docno);
		boolean expSaving = true;

		if (actPartyName.equalsIgnoreCase(expPartyName) && actAdjustbills.equalsIgnoreCase(expAdjustbills)
				&& actBillNewReference.equalsIgnoreCase(expBillNewReference)
				&& actBillTransactionCurrency.equalsIgnoreCase(expBillTransactionCurrency)
				&& actBillBaseCurrency.equalsIgnoreCase(expBillBaseCurrency)
				&& actBillLocalCurrency.equalsIgnoreCase(expBillLocalCurrency)
				&& actBillBalanceNewRefAmount.equalsIgnoreCase(expBillBalanceNewRefAmount)
				&& actbillRefAdjustAmountInTransCurency.equalsIgnoreCase(expbillRefAdjustAmountInTransCurency)
				&& actbillRefBalanceAmountAdjustInTrnasCurrency
						.equalsIgnoreCase(expbillRefBalanceAmountAdjustInTrnasCurrency)
				&&

				actBillNewReferencePick.equalsIgnoreCase(expBillNewReferencePick)
				&& actBillTransactionCurrencyPick.equalsIgnoreCase(expBillTransactionCurrencyPick)
				&& actBillBaseCurrencyPick.equalsIgnoreCase(expBillBaseCurrencyPick)
				&& actBillLocalCurrencyPick.equalsIgnoreCase(expBillLocalCurrencyPick)
				&& actBillBalanceNewRefAmountPick.equalsIgnoreCase(expBillBalanceNewRefAmountPick)
				&& actconversationRateBaseCurrencyRatePick.equalsIgnoreCase(expconversationRateBaseCurrencyRatePick)
				&& actconversationRateLocalCurrencyRatePick.equalsIgnoreCase(expconversationRateLocalCurrencyRatePick)
				&& actbillRefAdjustAmountInTransCurencyPick.equalsIgnoreCase(expbillRefAdjustAmountInTransCurencyPick)
				&& actbillRefBalanceAmountAdjustInTrnasCurrencyPick
						.equalsIgnoreCase(expbillRefBalanceAmountAdjustInTrnasCurrencyPick))

		{
			System.err.println(" Test Pass:Sales Voucher With adjustMernt Rececipts VAT");
			return true;
		}

		else if (actSaving == expSaving) {
			return true;
		}

		else {
			System.err.println(" Test FaIL : Sales Voucher with adjustMernt Rececipts VAT");
			return false;
		}

	}

	public boolean checkDecreaseQuantityInReciptsVATAfterConsumedInSalesInvoiceVAT()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		Thread.sleep(2000);

		clickOn(financialsMenu);

		clickOn(financialsTransactionMenu);

		clickOn(cashAndBankMenu);

		clickOn(recepitsVATVoucher);

		Thread.sleep(2000);

		voucherHomePageVoucherSelect("1");

		Thread.sleep(2000);
		clickOn(select1stRow_1stColumn);
		enter_AccountTxt.click();
		enter_AccountTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enterReceiptsVATTaxCode));
		enterReceiptsVATTaxCode.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_Amount));
		enter_Amount.sendKeys("90");
		enter_Amount.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		billwisePick();
		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNumberTxt));
		String docno = documentNumberTxt.getAttribute("value");

		String actRow1 = listOfElements(entryPageRow1List);
		String expRow1 = "[1, Customer New Reference, Std Rate, 90.00, New Reference, 4.29]";
		System.err.println(" ACT Row1: " + actRow1);
		System.err.println(" EXP Row1: " + expRow1);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(voucherSaveBtn));
		voucherSaveBtn.click();

		String expSaving = "Voucher cannot be modified as the bills are already adjusted against this voucher.";
		String actSaving = checkValidationMessage(expSaving);

		if (actSaving.equalsIgnoreCase(expSaving) && actRow1.equalsIgnoreCase(expRow1)) {
			return true;

		} else {
			return false;

		}

	}

	public boolean checkInputAmountWithNegativeInReceiptsVAT()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {

		Thread.sleep(2000);

		getDriver().navigate().refresh();

		Thread.sleep(2000);

		clickOn(financialsMenu);
		Thread.sleep(2000);

		clickOn(financialsTransactionMenu);
		Thread.sleep(2000);

		clickOn(cashAndBankMenu);

		Thread.sleep(2000);

		clickOn(recepitsVATVoucher);

		Thread.sleep(5000);

		voucherHomePageVoucherSelect("1");

		Thread.sleep(4500);

		clickOn(select1stRow_1stColumn);
		enter_AccountTxt.click();
		enter_AccountTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enterReceiptsVATTaxCode));
		enterReceiptsVATTaxCode.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_Amount));
		enter_Amount.sendKeys("-100");
		enter_Amount.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		billwisePick();
		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNumberTxt));
		String docno = documentNumberTxt.getAttribute("value");

		String actRow1 = listOfElements(entryPageRow1List);
		String expRow1 = "[1, Customer New Reference, Std Rate, -100.00, New Reference, 4.76]";

		System.err.println(" ACT Row1: " + actRow1);
		System.err.println(" EXP Row1: " + expRow1);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(voucherSaveBtn));
		voucherSaveBtn.click();

		String expSaving = "BillwiseCannotChangeSign";
		String actSaving = checkValidationMessage(expSaving);
		
		Thread.sleep(2567);
		getDriver().navigate().refresh();

		Thread.sleep(2567);
		
		if (actSaving.equalsIgnoreCase(expSaving) && actRow1.equalsIgnoreCase(expRow1)) {
			return true;

		} else {
			return false;

		}

	}

	public boolean checkAdjustmentScreenInsalesInvoiceVATAfterChangingAmountInRecepictsVAT()
			throws InterruptedException {
		Thread.sleep(3000);

		clickOn(financialsMenu);
		clickOn(financialsTransactionMenu);
		clickOn(financialTransactionSalesMenu);
		clickOn(salesInvoiceVATVoucher);

		Thread.sleep(4000);

		voucherHomePageVoucherSelect("1");

		Thread.sleep(3000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNumberTxt));
		documentNumberTxt.click();

		Thread.sleep(1999);

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_1stColumn));
		select1stRow_1stColumn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(pvWareHouseTxt));
		pvWareHouseTxt.click();
		pvWareHouseTxt.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_ItemTxt));

		enter_ItemTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);

	/*	getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_5thColumn));
		select1stRow_5thColumn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_8thColumn));
		select1stRow_8thColumn.click();

		Thread.sleep(1999);*/
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(voucherSaveBtn));
		voucherSaveBtn.click();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(billRefPartyName));
		String actPartyName = billRefPartyName.getText();
		String expPartyName = "Customer New Reference (Customer New Reference)";

		System.out.println("Bill wise Screen Cutomer Name " + actPartyName + "  Value Expected  " + expPartyName);

		int Adjustbills = billRefAdjustBillsGrid.size();

		String actAdjustbills = Integer.toString(Adjustbills);

		String expAdjustbills = "1";

		System.err.println("actAdjustbills : " + actAdjustbills + " Value Expected  : " + expAdjustbills);

		String actRow1 = listOfElements(billRefRow1List);
		String expRow1 = "[1, NDT57:1, " + currentDate() + ", " + currentDate()
				+ ", ₹, 100.00, 100.00, 100.00, 100.00, 0.00]";

		System.err.println(" ACT Row1: " + actRow1);
		System.err.println(" EXP Row1: " + expRow1);

		if (actRow1.equalsIgnoreCase(expRow1)) {
			getDriver().navigate().refresh();
			return true;
		} else {

			getDriver().navigate().refresh();
			return false;
		}

	}

	@FindBy(xpath = "//*[@id='id_transactionentry_save']/div[2]")
	private static WebElement voucherSaveBtnBtn;

	public boolean checkSavingVoucherPaymentsVAT()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {

		System.err.println(" Entered   ************************");

		Thread.sleep(2000);

		clickOn(financialsMenu);

		clickOn(financialsTransactionMenu);

		clickOn(cashAndBankMenu);

		clickOn(paymentsVATVoucher);

		Thread.sleep(2000);

		waitToClick(newBtn);

		Thread.sleep(2000);

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNumberTxt));
		documentNumberTxt.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(newCashBankAccountTxt));
		newCashBankAccountTxt.click();

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

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(voucherHeaderCurrency));
		voucherHeaderCurrency.click();
		;
		voucherHeaderCurrency.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME);

		voucherHeaderCurrency.sendKeys(Keys.SPACE);

		int currencycount = currencyListCount.size();

		System.err.println(currencycount);

		for (int i = 0; i < currencycount; i++) {
			String data = currencyListCount.get(i).getText();

			if (data.equalsIgnoreCase("INR")) {
				currencyListCount.get(i).click();

				break;
			}
		}

		voucherHeaderCurrency.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(PDRVATPlaceOfSupplyTXt));
		PDRVATPlaceOfSupplyTXt.click();

		PDRVATPlaceOfSupplyTXt.sendKeys("Dubai");

		Thread.sleep(2000);
		PDRVATPlaceOfSupplyTXt.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(departmentTxt));
		departmentTxt.click();
		departmentTxt.sendKeys(Keys.SHIFT, Keys.HOME);
		departmentTxt.sendKeys(Keys.SPACE);

		WebElement options = departmentTxt;

		int departmentListCountCount = departmentListCount.size();

		for (int i = 0; i < departmentListCountCount; i++) {
			String data = departmentListCount.get(i).getText();

			if (data.equalsIgnoreCase("Dubai")) {
				departmentListCount.get(i).click();
				break;
			}
		}

		departmentTxt.sendKeys(Keys.TAB);

		// First Row

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_1stColumn));
		select1stRow_1stColumn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_AccountTxt));
		enter_AccountTxt.click();
		enter_AccountTxt.sendKeys("Vendor A");

		getFluentWebDriverWait().until(ExpectedConditions.visibilityOfAllElements(bodyAccountListInGrid));
		int accountCount = bodyAccountListInGrid.size();

		System.err.println(accountCount);

		for (int i = 0; i < accountCount; i++) {
			String data = bodyAccountListInGrid.get(i).getText();

			if (data.equalsIgnoreCase("Vendor A")) {
				getFluentWebDriverWait().until(ExpectedConditions.visibilityOfAllElements(bodyAccountListInGrid));
				bodyAccountListInGrid.get(i).click();

				break;
			}
		}

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_AccountTxt));
		enter_AccountTxt.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enterpayVATTaxCode));
		Thread.sleep(1999);
		enterpayVATTaxCode.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_Amount));
		enter_Amount.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME);
		enter_Amount.sendKeys("105");
		Thread.sleep(1999);
		enter_Amount.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		billwisePick();

		Thread.sleep(2000);

		String docno = documentNumberTxt.getAttribute("value");

		clickOn(voucherSaveBtn);

		boolean actSaving = checkBackgroundSavingMessage(docno);
		boolean expSaving = true;

		if (actSaving == expSaving)

		{
			System.err.println(" Test Pass: Payemnst VAT Saved With New reference");
			return true;
		} else {
			System.err.println("Test FAIl: Payemnst VAT Saved With New reference");
			return false;
		}

	}

	public boolean checkSavingpurchaseVoucherVATWithpaymentAdjustments()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {

		clickOn(financialsMenu);

		clickOn(financialsTransactionMenu);

		clickOn(purchasesExpandBtn);

		clickOn(purchaseVouchersVat);

		Thread.sleep(1999);

	waitToClick(newBtn);

		Thread.sleep(2500);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(vendorAccountTxt));
		vendorAccountTxt.sendKeys("Vendor A");
		Thread.sleep(3000);
		vendorAccountTxt.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(departmentTxt));
		departmentTxt.click();
		departmentTxt.sendKeys(Keys.END);
		departmentTxt.sendKeys(Keys.SHIFT, Keys.HOME);
		departmentTxt.sendKeys(Keys.SPACE);

		int departmentcount = departmentListCount.size();

		System.err.println(departmentcount);

		for (int i = 0; i < departmentcount; i++) {
			String data = departmentListCount.get(i).getText();

			if (data.equalsIgnoreCase("Dubai")) {
				departmentListCount.get(i).click();

				Thread.sleep(1000);

				if (getIsAlertPresent()) {
					getAlert().accept();
				}

				break;
			}
		}

		departmentTxt.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(purchaseVoucherVATPlaceOFSupply));
		purchaseVoucherVATPlaceOFSupply.click();
		purchaseVoucherVATPlaceOFSupply.sendKeys(Keys.END);
		purchaseVoucherVATPlaceOFSupply.sendKeys(Keys.SHIFT, Keys.HOME);
		purchaseVoucherVATPlaceOFSupply.sendKeys(Keys.SPACE);

		int placeOFSupplyListCount = placeOFSupplyList.size();

		System.err.println("placeOFSupplyListCount   : " + placeOFSupplyListCount);

		for (int i = 0; i < placeOFSupplyListCount; i++) {
			String data = placeOFSupplyList.get(i).getText();

			if (data.equalsIgnoreCase("Abu Dhabi")) {
				placeOFSupplyList.get(i).click();

				break;
			}
		}

		Thread.sleep(2000);

		purchaseVoucherVATPlaceOFSupply.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(jurisdictionTxt));
		jurisdictionTxt.click();
		jurisdictionTxt.sendKeys(Keys.END);
		jurisdictionTxt.sendKeys(Keys.SHIFT, Keys.HOME);
		jurisdictionTxt.sendKeys("DUBAI");
		Thread.sleep(2000);
		jurisdictionTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		clickOn(select1stRow_1stColumn);
		pvWareHouseTxt.sendKeys("Hyderabad");
		Thread.sleep(3000);
		pvWareHouseTxt.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_ItemTxt));
		enter_ItemTxt.sendKeys("STD RATE COGS ITEM");
		Thread.sleep(3000);
		enter_ItemTxt.sendKeys(Keys.TAB);

		Thread.sleep(1500);
		enter_TaxCode1.sendKeys(Keys.TAB);

		Thread.sleep(1500);

		enter_PurchaseAccountTxt.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_9thColumn));
		select1stRow_9thColumn.click();
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_Quantity));
		enter_Quantity.click();
		enter_Quantity.clear();
		enter_Quantity.sendKeys("100");

		Thread.sleep(1500);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_11thColumn));
		select1stRow_11thColumn.click();

		enter_Rate.click();
		enter_Rate.clear();
		enter_Rate.sendKeys("1");
		enter_Rate.sendKeys(Keys.TAB);

		Thread.sleep(1500);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_Gross));
		enter_Gross.click();
		enter_Gross.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_14thColumn));
		select1stRow_14thColumn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_PvVat));
		enter_PvVat.click();

		enter_PvVat.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_PvTaxable));
		enter_PvTaxable.click();
		enter_PvTaxable.sendKeys(Keys.TAB);

		String docno = documentNumberTxt.getAttribute("value");

		Thread.sleep(1500);

		clickOn(voucherSaveBtn);
		Thread.sleep(1500);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(billRefPartyName));

		int Adjustbills = billRefAdjustBillsGrid.size();

		String actAdjustbills = Integer.toString(Adjustbills);

		String expAdjustbills = "1";

		System.err.println("actAdjustbills : " + actAdjustbills + " Value Expected  : " + expAdjustbills);

		String actRow1 = listOfElements(billRefRow1List);
		String expRow1 = "[1, NDT58:1, " + FilterCurrentDate(0) + ", " + FilterCurrentDate(0)
				+ ", ₹, 105.00, 105.00, 0.00, 0.00, 0.00]";

		System.err.println(" ACT Row1: " + actRow1);
		System.err.println(" EXP Row1: " + expRow1);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(billRefGridFirstRowAdjustmentAmtTxt));
		billRefGridFirstRowAdjustmentAmtTxt.click();

		Thread.sleep(1500);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(billRefPickIcon));
		billRefPickIcon.click();

		Thread.sleep(1500);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(billRefOkBtn));
		billRefOkBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNumberTxt));

		boolean actSaving = checkBackgroundSavingMessage(docno);
		boolean expSaving = true;

		if (actSaving == expSaving && actRow1.equalsIgnoreCase(expRow1))

		{
			System.err.println(" Purchase VAT Saved With Adjustment Amount With payments ");
			return true;
		} else {
			System.err.println("Purchase VAT Saved With Adjustment Amount With payments");
			return false;
		}

	}

	public boolean checkDecreaseQuantityInPaymentsVATAfterConsumedInSalesInvoiceVAT()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		Thread.sleep(2000);

		clickOn(financialsMenu);

		clickOn(financialsTransactionMenu);

		clickOn(cashAndBankMenu);

		clickOn(paymentsVATVoucher);

		Thread.sleep(5000);

		voucherHomePageVoucherSelect("1");

		Thread.sleep(4000);
		clickOn(select1stRow_1stColumn);
		enter_AccountTxt.click();
		enter_AccountTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		enterpayVATTaxCode.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_Amount));
		enter_Amount.sendKeys("90");
		enter_Amount.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		billwisePick();
		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNumberTxt));
		String docno = documentNumberTxt.getAttribute("value");

		String actRow1 = listOfElements(entryPageRow1List);
		String expRow1 = "[1, Vendor A, Standard Rated Purchase - Recoverable, 90.00, New Reference, 4.29]";
		System.err.println(" ACT Row1: " + actRow1);
		System.err.println(" EXP Row1: " + expRow1);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(voucherSaveBtn));
		voucherSaveBtn.click();

		String expSaving = "Voucher cannot be modified as the bills are already adjusted against this voucher.";
		String actSaving = checkValidationMessage(expSaving);

		if (actSaving.equalsIgnoreCase(expSaving) && actRow1.equalsIgnoreCase(expRow1)) {
			return true;

		} else {
			return false;

		}

	}

	public boolean checkInputAmountWithNegativeInPaymentsVAT()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {

		Thread.sleep(2000);

		getDriver().navigate().refresh();

		Thread.sleep(2000);

		clickOn(financialsMenu);

		clickOn(financialsTransactionMenu);

		Thread.sleep(2000);
		clickOn(cashAndBankMenu);
		Thread.sleep(2000);

		clickOn(paymentsVATVoucher);

		Thread.sleep(2000);

		voucherHomePageVoucherSelect("1");

		Thread.sleep(5500);

		clickOn(select1stRow_1stColumn);
		enter_AccountTxt.click();
		enter_AccountTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		enterpayVATTaxCode.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_Amount));
		enter_Amount.sendKeys("-100");
		enter_Amount.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		billwisePick();
		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNumberTxt));
		String docno = documentNumberTxt.getAttribute("value");

		String actRow1 = listOfElements(entryPageRow1List);
		String expRow1 = "[1, Vendor A, Standard Rated Purchase - Recoverable, -100.00, New Reference, 4.76]";
		System.err.println(" ACT Row1: " + actRow1);
		System.err.println(" EXP Row1: " + expRow1);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(voucherSaveBtn));
		voucherSaveBtn.click();

		String expSaving = "BillwiseCannotChangeSign";
		String expSaving1 = "Voucher cannot be modified as the bills are already adjusted against this voucher.";
		String actSaving = checkValidationMessage(expSaving);

		
		Thread.sleep(2567);
		getDriver().navigate().refresh();
		Thread.sleep(2567);
		
		if ((actSaving.equalsIgnoreCase(expSaving) || actSaving.equalsIgnoreCase(expSaving1))
				&& actRow1.equalsIgnoreCase(expRow1)) {
			return true;

		} else {
			return false;

		}

	}

	public boolean checkAdjustmentScreenInPVVATVATAfterChangingAmountInPaymentsVAT() throws InterruptedException {

		clickOn(financialsMenu);

		clickOn(financialsTransactionMenu);

		clickOn(purchasesExpandBtn);

		clickOn(purchaseVouchersVat);

		Thread.sleep(5000);

		voucherHomePageVoucherSelect("1");

		Thread.sleep(4000);

		clickOn(documentNumberTxt);

		Thread.sleep(1999);

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_1stColumn));
		select1stRow_1stColumn.click();

		Thread.sleep(1999);

		ClickUsingJs(voucherSaveBtn);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(billRefPartyName));
		String actPartyName = billRefPartyName.getText();
		String expPartyName = "[Vendor A(Vendor A)]";

		System.out.println("Bill wise Screen Cutomer Name " + actPartyName + "  Value Expected  " + expPartyName);

		int Adjustbills = billRefAdjustBillsGrid.size();

		String actAdjustbills = Integer.toString(Adjustbills);

		String expAdjustbills = "1";

		System.err.println("actAdjustbills : " + actAdjustbills + " Value Expected  : " + expAdjustbills);

		String actRow1 = listOfElements(billRefRow1List);
		String expRow1 = "[1, NDT58:1, " + FilterCurrentDate(0) + ", " + FilterCurrentDate(0)
				+ ", ₹, 105.00, 105.00, 105.00, 105.00, 0.00]";

		System.err.println(" ACT Row1: " + actRow1);
		System.err.println(" EXP Row1: " + expRow1);

		if (actRow1.equalsIgnoreCase(expRow1)) {
			return true;
		} else {

			return false;
		}

	}

	public boolean checkSavingRecepictsVATWithVendor()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {

		Thread.sleep(2000);
		checkEraseAllTransaction();

		Thread.sleep(2000);

		clickOn(financialsMenu);

		clickOn(financialsTransactionMenu);

		clickOn(cashAndBankMenu);

		clickOn(recepitsVATVoucher);

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

			if (data.equalsIgnoreCase("DUBAI")) {
				departmentListCount.get(i).click();
				break;
			}
		}

		Thread.sleep(1000);

		departmentTxt.sendKeys(Keys.TAB);

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

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_1stColumn));
		select1stRow_1stColumn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_AccountTxt));
		enter_AccountTxt.click();
		enter_AccountTxt.sendKeys("Vendor A");

		getFluentWebDriverWait().until(ExpectedConditions.visibilityOfAllElements(bodyAccountListInGrid));
		int accountCount = bodyAccountListInGrid.size();

		System.err.println(accountCount);

		for (int i = 0; i < accountCount; i++) {
			String data = bodyAccountListInGrid.get(i).getText();

			if (data.equalsIgnoreCase("Vendor A")) {
				getFluentWebDriverWait().until(ExpectedConditions.visibilityOfAllElements(bodyAccountListInGrid));
				bodyAccountListInGrid.get(i).click();

				break;
			}
		}

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_AccountTxt));
		enter_AccountTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enterReceiptsVATTaxCode));
		enterReceiptsVATTaxCode.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_Amount));
		enter_Amount.sendKeys("100");
		enter_Amount.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		billwisePick();
		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNumberTxt));
		String docno = documentNumberTxt.getAttribute("value");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(voucherSaveBtn));
		voucherSaveBtn.click();

		boolean actSaving = checkBackgroundSavingMessage(docno);
		boolean expSaving = true;

		if (actSaving == expSaving)

		{
			System.err.println("Recepits VAT Voucher Saved With New Reference  ");
			return true;
		} else {
			System.err.println("Recepits VAT Voucher Saved With New Reference  ");
			return false;
		}

	}

	public boolean checkSavingPaymentsAdjsutingRecepicts()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {

		System.err.println(" Entered   ************************");

		// checkEraseAllTransaction();

		Thread.sleep(2000);

		clickOn(financialsMenu);

		clickOn(financialsTransactionMenu);

		clickOn(cashAndBankMenu);

		clickOn(paymentsVATVoucher);

		Thread.sleep(4000);

		
		click(newBtn);

		Thread.sleep(2000);

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNumberTxt));
		documentNumberTxt.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(newCashBankAccountTxt));
		newCashBankAccountTxt.click();

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

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(voucherHeaderCurrency));
		voucherHeaderCurrency.click();
		;
		voucherHeaderCurrency.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME);

		voucherHeaderCurrency.sendKeys(Keys.SPACE);

		int currencycount = currencyListCount.size();

		System.err.println(currencycount);

		for (int i = 0; i < currencycount; i++) {
			String data = currencyListCount.get(i).getText();

			if (data.equalsIgnoreCase("INR")) {
				currencyListCount.get(i).click();

				break;
			}
		}

		voucherHeaderCurrency.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(PDRVATPlaceOfSupplyTXt));
		PDRVATPlaceOfSupplyTXt.click();

		PDRVATPlaceOfSupplyTXt.sendKeys("Dubai");

		Thread.sleep(2000);
		PDRVATPlaceOfSupplyTXt.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(departmentTxt));
		departmentTxt.click();
		departmentTxt.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME);
		departmentTxt.sendKeys(Keys.SPACE);

		WebElement options = departmentTxt;

		int departmentListCountCount = departmentListCount.size();

		for (int i = 0; i < departmentListCountCount; i++) {
			String data = departmentListCount.get(i).getText();

			if (data.equalsIgnoreCase("Dubai")) {
				departmentListCount.get(i).click();
				break;
			}
		}

		departmentTxt.sendKeys(Keys.TAB);

		// First Row

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_1stColumn));
		select1stRow_1stColumn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_AccountTxt));
		enter_AccountTxt.click();
		enter_AccountTxt.sendKeys("Vendor A");

		getFluentWebDriverWait().until(ExpectedConditions.visibilityOfAllElements(bodyAccountListInGrid));
		int accountCount = bodyAccountListInGrid.size();

		System.err.println(accountCount);

		for (int i = 0; i < accountCount; i++) {
			String data = bodyAccountListInGrid.get(i).getText();

			if (data.equalsIgnoreCase("Vendor A")) {
				getFluentWebDriverWait().until(ExpectedConditions.visibilityOfAllElements(bodyAccountListInGrid));
				bodyAccountListInGrid.get(i).click();

				break;
			}
		}

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_AccountTxt));
		enter_AccountTxt.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enterpayVATTaxCode));
		Thread.sleep(1999);
		enterpayVATTaxCode.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_Amount));
		enter_Amount.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME);
		enter_Amount.sendKeys("100");
		Thread.sleep(1999);
		enter_Amount.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(billRefPartyName));

		int Adjustbills = billRefAdjustBillsGrid.size();

		String actAdjustbills = Integer.toString(Adjustbills);

		String expAdjustbills = "1";

		System.err.println("actAdjustbills : " + actAdjustbills + " Value Expected  : " + expAdjustbills);

		String actRow1 = listOfElements(billRefRow1List);
		String expRow1 = "[1, NDT57:1, " + filterDateBydays(0) + ", " + FilterCurrentDate(0)
				+ ", ₹, 100.00, 100.00, 0.00, 0.00, 0.00]";

		System.err.println(" ACT Row1: " + actRow1);
		System.err.println(" EXP Row1: " + expRow1);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(billRefGridFirstRowAdjustmentAmtTxt));
		billRefGridFirstRowAdjustmentAmtTxt.click();

		Thread.sleep(1500);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(billRefPickIcon));
		billRefPickIcon.click();

		Thread.sleep(1500);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(billRefOkBtn));
		billRefOkBtn.click();

		Thread.sleep(2000);

		String docno = documentNumberTxt.getAttribute("value");

		clickOn(voucherSaveBtn);

		boolean actSaving = checkBackgroundSavingMessage(docno);
		boolean expSaving = true;

		if (actSaving == expSaving)

		{
			System.err.println(" Test Pass: Payemnst VAT Saved With New reference");
			return true;
		} else {
			System.err.println("Test FAIl: Payemnst VAT Saved With New reference");
			return false;
		}

	}

	public boolean checkChangingAmountInReceiptsVAT()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {
		Thread.sleep(2000);

		clickOn(financialsMenu);

		clickOn(financialsTransactionMenu);

		clickOn(cashAndBankMenu);

		clickOn(recepitsVATVoucher);

		Thread.sleep(2000);

		voucherHomePageVoucherSelect("1");

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(newCashBankAccountTxt));
		newCashBankAccountTxt.click();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_1stColumn));
		select1stRow_1stColumn.click();

		enter_AccountTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enterReceiptsVATTaxCode));
		enterReceiptsVATTaxCode.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_Amount));
		enter_Amount.sendKeys("-100");
		enter_Amount.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		billwisePick();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNumberTxt));
		String docno = documentNumberTxt.getAttribute("value");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(voucherSaveBtn));
		voucherSaveBtn.click();

		String expSaving = "BillwiseCannotChangeSign";
		String actSaving = checkValidationMessage(expSaving);
		
		
		Thread.sleep(2567);
		getDriver().navigate().refresh();
		Thread.sleep(2567);

		if (actSaving.equalsIgnoreCase(expSaving)) {
			System.err.println("Recepits VAT Voucher Saved With New Reference  ");
			return true;
		} else {
			System.err.println("Recepits VAT Voucher Saved With New Reference  ");
			return false;
		}
	}

	@FindBy(xpath = "//span[@class='hidden-xs']")
	private static WebElement userNameDisplay;

	@FindBy(xpath = "//*[@id='companyLogo']")
	private static WebElement companyLogo;

	public boolean checkConfigureTransactionMastersUnderSettings()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		excelReader = new ExcelReader(POJOUtility.getExcelPath());

		/*
		 * getAction().moveToElement(settings).build().perform(); Thread.sleep(1000);
		 */
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(settings));
		ClickUsingJs(settings);

		Thread.sleep(1000);

		ClickUsingJs(Setting_Perference);
		Thread.sleep(2000);

		Thread.sleep(2000);

		ClickUsingJs(LblMaster);

		String actLabelMaster = LblMaster.getText();
		String expLabelMaster = "Masters";/* excelReader.getCellData(xlSheetName, 13, 6); */
		excelReader.setCellData(xlfile, xlSheetName, 13, 7, actLabelMaster);

		System.out.println("Label: " + actLabelMaster + " Value Expected " + expLabelMaster);

		if (actLabelMaster.equalsIgnoreCase(expLabelMaster)) {
			excelReader.setCellData(xlfile, xlSheetName, 12, 8, resPass);
			return true;
		} else {
			excelReader.setCellData(xlfile, xlSheetName, 12, 8, resFail);
			return false;
		}
	}

	public boolean checkItemAttributeValuesinMastersUnderSettingsConfigureTransactions()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		excelReader = new ExcelReader(POJOUtility.getExcelPath());

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(attributes));
		Select select = new Select(attributes);
		select.selectByVisibleText(excelReader.getCellData(xlSheetName, 15, 5));

		// Entering first attribute

		getAction().moveToElement(attribute0).build().perform();
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(attribute0));

		attribute0.click();
		// attribute0.clear();
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(attributeAfterclick));
		attributeAfterclick.clear();
		attributeAfterclick.sendKeys(excelReader.getCellData(xlSheetName, 16, 5));
		attributeAfterclick.sendKeys(Keys.TAB);

		// Entering second attribute
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(attribute1));
		attribute1.click();
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(attributeAfterclick));
		attributeAfterclick.clear();
		attributeAfterclick.sendKeys(excelReader.getCellData(xlSheetName, 17, 5));

		Thread.sleep(2000);

		// clicking update button

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(updtBtn));
		updtBtn.click();

		getWaitForAlert();

		System.out.println("Alert message " + getDriver().switchTo().alert().getText());

		String actAlert = getAlert().getText();
		String expAlrert = excelReader.getCellData(xlSheetName, 18, 6);
		excelReader.setCellData(xlfile, xlSheetName, 18, 7, actAlert);

		getAlert().accept();

		String expMessage = excelReader.getCellData(xlSheetName, 19, 6);
		String actMessage = checkValidationMessage(expMessage);
		excelReader.setCellData(xlfile, xlSheetName, 19, 7, actMessage);

		System.err.println(actMessage);

		if (actMessage.equalsIgnoreCase(expMessage)) {
			excelReader.setCellData(xlfile, xlSheetName, 14, 8, resPass);
			return true;
		} else {
			excelReader.setCellData(xlfile, xlSheetName, 14, 8, resFail);
			return false;
		}

	}

	public boolean checkCloseOptionInMastersScreenUnderSettingsMenu()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		excelReader = new ExcelReader(POJOUtility.getExcelPath());
		// clicking on close

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(closeBtn));
		closeBtn.click();

		Thread.sleep(1000);

		/*
		 * String expMessage=excelReader.getCellData(xlSheetName, 21, 6);
		 * 
		 * String actMessage=dashboard.getText(); excelReader.setCellData(xlfile,
		 * xlSheetName, 21, 7, actMessage);
		 */

		if (getIsAlertPresent()) {
			System.out.println(getAlert().getText());

			getAlert().accept();
		}

		Thread.sleep(1999);
		if (homeMenu.isDisplayed()) {
			excelReader.setCellData(xlfile, xlSheetName, 20, 8, resPass);
			return true;
		} else {
			excelReader.setCellData(xlfile, xlSheetName, 20, 8, resFail);
			return false;
		}
	}

	/*
	 * @FindBy(xpath="//*[@id='lastTab']/div/div/a/span") public static WebElement
	 * itemLasttab;
	 */

	public boolean CheckAttributesTabinItem()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {

		excelReader = new ExcelReader(POJOUtility.getExcelPath());
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(homeMenu));
		homeMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(mastersMenu));
		mastersMenu.click();
		Thread.sleep(1000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemMenu));
		itemMenu.click();
		Thread.sleep(1000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(item));
		item.click();

		Thread.sleep(2999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(newItemBtn));
		newItemBtn.click();

		Thread.sleep(1000);

		try {
			if (itemLasttab.isDisplayed()) {
				getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemLasttab));
				itemLasttab.click();
			}

		} catch (Exception e) {
			int count = itemMasterTabList.size();
			ArrayList<String> List = new ArrayList<>();
			for (int i = 0; i < count; i++) {
				String data = itemMasterTabList.get(i).getText();
				System.err.println(" DATA : " + data);
				if (data.equalsIgnoreCase("Attribute-0")) {
					itemMasterTabList.get(i).click();
				}
			}

		}

		Thread.sleep(1000);

		String actAttribute0 = attribute0Tab.getText();
		String expAttribute0 = excelReader.getCellData(xlSheetName, 23, 6);
		excelReader.setCellData(xlfile, xlSheetName, 23, 7, actAttribute0);

		System.err.println("Attribute-0 Tab : " + actAttribute0 + "  Value Expected  " + expAttribute0);

		Thread.sleep(1000);

		String actAttribute1 = attribute1Tab.getText();
		String expAttribute1 = excelReader.getCellData(xlSheetName, 24, 6);
		excelReader.setCellData(xlfile, xlSheetName, 24, 7, actAttribute1);

		System.err.println("Attribute-1 Tab : " + actAttribute1 + "  Value Expected  " + expAttribute1);

		if (actAttribute0.equalsIgnoreCase(expAttribute0) && actAttribute1.equalsIgnoreCase(expAttribute1)) {
			excelReader.setCellData(xlfile, xlSheetName, 22, 8, resPass);
			return true;
		} else {

			excelReader.setCellData(xlfile, xlSheetName, 22, 8, resFail);
			return false;
		}
	}

	// clicking on item menu
	@FindBy(xpath = "//*[@id='sName']")
	public static WebElement itemName;

	@FindBy(xpath = "//*[@id='sCode']")
	public static WebElement itemCode;

	// units tab
	@FindBy(xpath = "//*[@id='divBtnGroup1']")
	public static WebElement unitsTab;

	@FindBy(xpath = "//*[@id='iDefaultBaseUnit']")
	public static WebElement baseUnit;

	@FindBy(xpath = "//*[@id='iDefaultSalesUnit']")
	public static WebElement salesUnit;

	@FindBy(xpath = "//*[@id='iDefaultPurchaseUnit']")
	public static WebElement purchaseUnit;

	@FindBy(xpath = "//*[@id='chkProductAttribute0']")
	public static WebElement checkBox0;
	// *[@id="chkProductAttribute0"]

	// *[@id="txtProdAttributeName0"]
	@FindBy(xpath = "//*[@id='id_AttributeName0']")
	public static WebElement attributeName;

	@FindBy(xpath = "//*[@id='txtProdAttributeName0']")
	public static WebElement Valueattribute1;

	@FindBy(xpath = "//*[@id='txtProdAttributeCode0']")
	public static WebElement Codeattribute1;

	// *[@id="txtProdAttributeName1"]

	@FindBy(xpath = "//*[@id='txtProdAttributeName1']")
	public static WebElement Valueattribute2;

	@FindBy(xpath = "//*[@id='txtProdAttributeCode1']")
	public static WebElement Codeattribute2;

	@FindBy(xpath = "//*[@id='txtProdAttributeName3']")
	public static WebElement Valueattribute3;

	@FindBy(xpath = "//*[@id='txtProdAttributeCode3']")
	public static WebElement Codeattribute3;

	@FindBy(xpath = "//*[@id='txtProdAttributeName4']")
	public static WebElement Valueattribute4;

	@FindBy(xpath = "//*[@id='txtProdAttributeCode4']")
	public static WebElement Codeattribute4;

	@FindBy(xpath = "//*[@id='btnMasterSaveClick']/i")
	public static WebElement saveBtn;

	@FindBy(xpath = "//*[@id='divBtnGroup9']")
	public static WebElement valid;

	@FindBy(xpath = "//*[@id='GenerateNewMasterModel']/div/div[1]/div[2]/div/ul/li[2]/button[5]/i")
	public static WebElement closeBtnforAttributes;

	public boolean checkItemsAdddinginAttribute0Tab()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		excelReader = new ExcelReader(POJOUtility.getExcelPath());

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(homeMenu));
		homeMenu.click();
		Thread.sleep(1000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(mastersMenu));
		mastersMenu.click();
		Thread.sleep(1000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemMenu));
		itemMenu.click();
		Thread.sleep(1000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(item));
		item.click();

		Thread.sleep(1000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(newItemBtn));
		newItemBtn.click();
		Thread.sleep(1000);

		itemName.click();
		itemName.clear();
		itemName.sendKeys(excelReader.getCellData(xlSheetName, 26, 5));
		itemName.sendKeys(Keys.TAB);

		itemCode.click();
		itemCode.clear();
		itemCode.sendKeys(excelReader.getCellData(xlSheetName, 27, 5));
		itemCode.sendKeys(Keys.TAB);

		// units addding
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(unitsTab));
		unitsTab.click();

		Thread.sleep(1000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(baseUnit));
		baseUnit.click();
		baseUnit.clear();
		baseUnit.sendKeys(excelReader.getCellData(xlSheetName, 28, 5));
		Thread.sleep(1000);
		baseUnit.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(salesUnit));
		salesUnit.click();
		salesUnit.clear();
		salesUnit.sendKeys(excelReader.getCellData(xlSheetName, 29, 5));
		Thread.sleep(1000);
		salesUnit.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(purchaseUnit));
		purchaseUnit.click();
		purchaseUnit.clear();
		purchaseUnit.sendKeys(excelReader.getCellData(xlSheetName, 30, 5));
		Thread.sleep(1000);
		purchaseUnit.sendKeys(Keys.TAB);

		try {

			if (itemLasttab.isDisplayed()) {
				getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemLasttab));
				itemLasttab.click();
			}
		} catch (Exception e) {

		}
		Thread.sleep(1000);

		attribute0Tab.click();
		Thread.sleep(1000);
		checkBox0.click();
		Thread.sleep(1000);

		String AttributeName = attributeName.getText();
		// System.err.println("Attribute name" +attribute0Tab.getText());

		Valueattribute1.sendKeys(excelReader.getCellData(xlSheetName, 31, 5));
		Valueattribute1.sendKeys(Keys.TAB);
		Codeattribute1.sendKeys(excelReader.getCellData(xlSheetName, 32, 5));
		Codeattribute1.sendKeys(Keys.TAB);
		Thread.sleep(1000);

		Valueattribute2.sendKeys(excelReader.getCellData(xlSheetName, 33, 5));
		Valueattribute2.sendKeys(Keys.TAB);
		Codeattribute2.sendKeys(excelReader.getCellData(xlSheetName, 34, 5));
		Codeattribute2.sendKeys(Keys.TAB);
		Thread.sleep(1000);

		Valueattribute3.sendKeys(excelReader.getCellData(xlSheetName, 35, 5));
		Valueattribute3.sendKeys(Keys.TAB);
		Codeattribute3.sendKeys(excelReader.getCellData(xlSheetName, 36, 5));
		Codeattribute3.sendKeys(Keys.TAB);
		Thread.sleep(1000);

		Valueattribute4.sendKeys(excelReader.getCellData(xlSheetName, 37, 5));
		Valueattribute4.sendKeys(Keys.TAB);
		Codeattribute4.sendKeys(excelReader.getCellData(xlSheetName, 38, 5));
		// Codeattribute4.sendKeys(Keys.TAB);
		Thread.sleep(1000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(saveBtn));
		saveBtn.click();

		Thread.sleep(1000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(closeBtnforAttributes));
		closeBtnforAttributes.click();
		Thread.sleep(1000);
		itemCloseBtn.click();
		Thread.sleep(1000);

		String expMessage = excelReader.getCellData(xlSheetName, 39, 6);
		String actMessage = checkValidationMessage(expMessage);
		excelReader.setCellData(xlfile, xlSheetName, 39, 7, actMessage);

		if (actMessage.equalsIgnoreCase(expMessage)) {

			excelReader.setCellData(xlfile, xlSheetName, 25, 8, resPass);
			return true;
		} else {
			excelReader.setCellData(xlfile, xlSheetName, 25, 8, resFail);
			return false;
		}

	}

	@FindBy(xpath = "//*[@id='chkProductAttribute1']")
	public static WebElement checkBox1;

	// *[@id="txtProdAttributeName0"]
	@FindBy(xpath = "(//tbody[@id='tblProductAttributesBody'])[2]/tr[1]/td[4]/input")
	public static WebElement ValueforAttribute1;
	@FindBy(xpath = "(//tbody[@id='tblProductAttributesBody'])[2]/tr[1]/td[5]/input")
	public static WebElement CodeforAttribute1;

	@FindBy(xpath = "(//tbody[@id='tblProductAttributesBody'])[2]/tr[2]/td[4]/input")
	public static WebElement ValueforAttribute2;
	@FindBy(xpath = "(//tbody[@id='tblProductAttributesBody'])[2]/tr[2]/td[5]/input")
	public static WebElement CodeforAttribute2;

	@FindBy(xpath = "(//tbody[@id='tblProductAttributesBody'])[2]/tr[3]/td[4]/input")
	public static WebElement ValueforAttribute3;
	@FindBy(xpath = "(//tbody[@id='tblProductAttributesBody'])[2]/tr[3]/td[5]/input")
	public static WebElement CodeforAttribute3;

	@FindBy(xpath = "(//tbody[@id='tblProductAttributesBody'])[2]/tr[4]/td[4]/input")
	public static WebElement ValueforAttribute4;
	@FindBy(xpath = "(//tbody[@id='tblProductAttributesBody'])[2]/tr[4]/td[5]/input")
	public static WebElement CodeforAttribute4;

	@FindBy(xpath = "//*[@id='btnClose']")
	public static WebElement itemCloseBtn;

	public boolean checkItemsAdddinginAttribute1Tab()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(homeMenu));
		homeMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(mastersMenu));
		mastersMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemMenu));
		itemMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(item));
		item.click();

		Thread.sleep(1000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(newItemBtn));
		newItemBtn.click();
		Thread.sleep(1000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemName));
		itemName.click();
		itemName.clear();
		itemName.sendKeys(excelReader.getCellData(xlSheetName, 41, 5));
		itemName.sendKeys(Keys.TAB);

		itemCode.click();
		itemCode.clear();
		itemCode.sendKeys(excelReader.getCellData(xlSheetName, 42, 5));
		itemCode.sendKeys(Keys.TAB);

		// units addding
		unitsTab.click();

		Thread.sleep(1000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(baseUnit));
		baseUnit.click();
		baseUnit.clear();
		baseUnit.sendKeys(excelReader.getCellData(xlSheetName, 43, 5));
		Thread.sleep(1000);
		baseUnit.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(salesUnit));
		salesUnit.click();
		salesUnit.clear();
		salesUnit.sendKeys(excelReader.getCellData(xlSheetName, 44, 5));
		Thread.sleep(1000);
		salesUnit.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(purchaseUnit));
		purchaseUnit.click();
		purchaseUnit.clear();
		purchaseUnit.sendKeys(excelReader.getCellData(xlSheetName, 45, 5));
		Thread.sleep(1000);
		purchaseUnit.sendKeys(Keys.TAB);

		try {

			if (itemLasttab.isDisplayed()) {
				getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemLasttab));
				itemLasttab.click();
			}
		} catch (Exception e) {

		}
		Thread.sleep(1000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(attribute1Tab));
		attribute1Tab.click();
		Thread.sleep(1000);
		checkBox1.click();
		Thread.sleep(1000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(ValueforAttribute1));
		ValueforAttribute1.sendKeys(excelReader.getCellData(xlSheetName, 46, 5));
		ValueforAttribute1.sendKeys(Keys.TAB);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(CodeforAttribute1));
		CodeforAttribute1.sendKeys(excelReader.getCellData(xlSheetName, 47, 5));
		CodeforAttribute1.sendKeys(Keys.TAB);
		Thread.sleep(1000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(ValueforAttribute2));
		ValueforAttribute2.sendKeys(excelReader.getCellData(xlSheetName, 48, 5));
		ValueforAttribute2.sendKeys(Keys.TAB);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(CodeforAttribute2));
		CodeforAttribute2.sendKeys(excelReader.getCellData(xlSheetName, 49, 5));
		CodeforAttribute2.sendKeys(Keys.TAB);
		Thread.sleep(1000);

		ValueforAttribute3.sendKeys(excelReader.getCellData(xlSheetName, 50, 5));
		ValueforAttribute3.sendKeys(Keys.TAB);
		CodeforAttribute3.sendKeys(excelReader.getCellData(xlSheetName, 51, 5));
		CodeforAttribute3.sendKeys(Keys.TAB);
		Thread.sleep(1000);

		ValueforAttribute4.sendKeys(excelReader.getCellData(xlSheetName, 52, 5));
		ValueforAttribute4.sendKeys(Keys.TAB);
		Codeattribute4.sendKeys(excelReader.getCellData(xlSheetName, 53, 5));
		CodeforAttribute4.sendKeys(Keys.TAB);
		Thread.sleep(1000);

		saveBtn.click();
		Thread.sleep(1000);
		closeBtnforAttributes.click();
		Thread.sleep(1000);
		itemCloseBtn.click();
		Thread.sleep(1000);

		String expMessage = excelReader.getCellData(xlSheetName, 54, 6);
		String actMessage = checkValidationMessage(expMessage);
		excelReader.setCellData(xlfile, xlSheetName, 54, 7, actMessage);

		if (actMessage.equalsIgnoreCase(expMessage)) {
			excelReader.setCellData(xlfile, xlSheetName, 40, 8, resPass);
			return true;
		} else {
			excelReader.setCellData(xlfile, xlSheetName, 40, 8, resFail);
			return false;
		}
	}

	@FindBy(xpath = "//*[@id='LandingGridBody']/tr/td[8]//input")
	private static List<WebElement> masterGridBodyChkbox;

	@FindBy(xpath = "//*[@id='LandingGridBody']/tr/td[12]")
	private static List<WebElement> masterGridBodyName;

	public boolean checkSubItemUnderFruitsItemMaster()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		excelReader = new ExcelReader(POJOUtility.getExcelPath());

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(homeMenu));
		homeMenu.click();
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(mastersMenu));
		clickOn(mastersMenu);
		Thread.sleep(2000);

		clickOn(itemMenu);
		Thread.sleep(2000);

		clickOn(item);

		Thread.sleep(2000);

		clickOn(fruitsItemChkbox);

		getAction().doubleClick(fruitsItemChkbox).build().perform();

		/*
		 * int count = masterGridBodyName.size();
		 * 
		 * for (int i = 0; i < count; i++) { String data =
		 * masterGridBodyName.get(i).getText(); Thread.sleep(1000); if
		 * (data.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 56, 5))) {
		 * getAction().doubleClick(masterGridBodyChkbox.get(i)).build().perform();
		 * Thread.sleep(2000); break; } } Thread.sleep(2000);
		 * 
		 */

		Thread.sleep(2000);

		boolean actItemsList = ListComparisionWOOrder(masterGridBodyName, excelReader.getCellData(xlSheetName, 57, 6));
		boolean expItemsList = true;

		System.err.println("DATA Displayed : " + actItemsList);
		System.err.println("DATA Displayed : " + expItemsList);

		Thread.sleep(2000);

		itemCloseBtn.click();

		Thread.sleep(3000);

		if (actItemsList == expItemsList) {
			excelReader.setCellData(xlfile, xlSheetName, 55, 8, resPass);
			return true;
		} else {
			excelReader.setCellData(xlfile, xlSheetName, 55, 8, resFail);
			return false;
		}
	}

	public boolean checkItemsSavedinMasterItemforElectronicsandSubItems()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		excelReader = new ExcelReader(POJOUtility.getExcelPath());

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(homeMenu));
		homeMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(mastersMenu));
		mastersMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemMenu));
		itemMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(item));
		item.click();

		Thread.sleep(2000);

		int count = masterGridBodyName.size();

		for (int i = 0; i < count; i++) {
			String data = masterGridBodyName.get(i).getText();
			Thread.sleep(1000);
			if (data.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 59, 5))) {
				getAction().doubleClick(masterGridBodyChkbox.get(i)).build().perform();
				Thread.sleep(2000);
				break;
			}
		}
		Thread.sleep(2000);

		int count1 = masterGridBodyName.size();

		ArrayList<String> actList = new ArrayList<>();

		for (int i = 0; i < count1; i++) {
			String data1 = masterGridBodyName.get(i).getText();
			actList.add(data1);
		}

		String actItemsList = actList.toString();
		String expItemsList = excelReader.getCellData(xlSheetName, 60, 6);
		excelReader.setCellData(xlfile, xlSheetName, 60, 7, actItemsList);

		System.err.println("DATA Displayed : " + actItemsList);
		System.err.println("DATA Displayed : " + expItemsList);

		Thread.sleep(2000);

		itemCloseBtn.click();

		Thread.sleep(3000);

		if (actItemsList.equalsIgnoreCase(expItemsList)) {
			excelReader.setCellData(xlfile, xlSheetName, 58, 8, resPass);
			return true;
		} else {
			excelReader.setCellData(xlfile, xlSheetName, 58, 8, resFail);
			return false;
		}

	}

	public boolean checktheNewBtninSubItemsofanItemFruits()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		excelReader = new ExcelReader(POJOUtility.getExcelPath());

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(homeMenu));
		homeMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(mastersMenu));
		mastersMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemMenu));
		itemMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(item));
		item.click();

		int count = masterGridBodyName.size();

		for (int i = 0; i < count; i++)

		{
			Thread.sleep(1000);
			String data = masterGridBodyName.get(i).getText();

			if (data.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 62, 5))) {
				Thread.sleep(1000);
				getAction().doubleClick(masterGridBodyChkbox.get(i)).build().perform();

				break;
			}
		}
		Thread.sleep(5000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(newItemBtn));
		newItemBtn.click();
		Thread.sleep(2000);

		String expMessage = excelReader.getCellData(xlSheetName, 63, 6);
		String actMessage = checkValidationMessage(expMessage);

		excelReader.setCellData(xlfile, xlSheetName, 63, 7, actMessage);

		if (actMessage.equalsIgnoreCase(expMessage)) {
			excelReader.setCellData(xlfile, xlSheetName, 61, 8, resPass);
			return true;
		} else {
			excelReader.setCellData(xlfile, xlSheetName, 61, 8, resFail);
			return false;
		}
	}

	public boolean checkFruits1ItemProperties() throws InterruptedException {

		getDriver().navigate().refresh();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(homeMenu));
		homeMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(mastersMenu));
		mastersMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemMenu));
		itemMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(item));
		item.click();

		int count = masterGridBodyName.size();

		for (int i = 0; i < count; i++) {
			String data = masterGridBodyName.get(i).getText();
			Thread.sleep(1000);
			if (data.equalsIgnoreCase("Fruits")) {
				getAction().click(masterGridBodyChkbox.get(i)).build().perform();

				Thread.sleep(2000);
				break;
			}
		}
		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemPropertiesBtn));
		itemPropertiesBtn.click();

		Thread.sleep(8965);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dontMaintainStockByBatchChkbox));
		if (dontMaintainStockByBatchChkboxIsSelected.isSelected() == false) {
			dontMaintainStockByBatchChkbox.click();
		}

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dontInputBinChkbox));
		if (dontInputBinChkboxIsSelected.isSelected() == false) {
			dontInputBinChkbox.click();
		}

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dontMaintainStockByRMAChkbox));
		if (dontMaintainStockByRMAChkboxIsSelected.isSelected() == false) {
			dontMaintainStockByRMAChkbox.click();
		}

		Thread.sleep(2000);

		boolean actDontMaintainBatch = dontMaintainStockByBatchChkboxIsSelected.isSelected();
		boolean expDontMaintainBatch = true;

		boolean actDontInputBins = dontInputBinChkboxIsSelected.isSelected();
		boolean expDontInputBins = true;

		boolean actDontMaintainRMA = dontMaintainStockByRMAChkboxIsSelected.isSelected();
		boolean expDontMaintainRMA = true;

		System.out
				.println("Dont Maintain Batch : " + actDontMaintainBatch + "  Value Expected  " + expDontMaintainBatch);
		System.out.println("Dont Input Bins     : " + actDontInputBins + "  Value Expected  " + expDontInputBins);
		System.out.println("Dont Maintain RMA   : " + actDontMaintainRMA + "  Value Expected  " + expDontMaintainRMA);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemPropertiesOkBtn));
		itemPropertiesOkBtn.click();

		Thread.sleep(2000);

		if (actDontMaintainBatch == expDontMaintainBatch && actDontInputBins == expDontInputBins
				&& actDontMaintainRMA == expDontMaintainRMA)

		{
			return true;
		} else {

			return false;
		}

	}

	public boolean checkElectronicsItemProperties() throws InterruptedException {

		getDriver().navigate().refresh();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(homeMenu));
		homeMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(mastersMenu));
		mastersMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemMenu));
		itemMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(item));
		item.click();

		int count = masterGridBodyName.size();

		for (int i = 0; i < count; i++) {
			String data = masterGridBodyName.get(i).getText();
			Thread.sleep(1000);
			if (data.equalsIgnoreCase("Electronics")) {
				getAction().click(masterGridBodyChkbox.get(i)).build().perform();
				Thread.sleep(2000);
				break;
			}
		}
		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemPropertiesBtn));
		itemPropertiesBtn.click();
		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dontMaintainStockByBatchChkbox));
		if (dontMaintainStockByBatchChkboxIsSelected.isSelected() == false) {
			dontMaintainStockByBatchChkbox.click();
		}

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dontInputBinChkbox));
		if (dontInputBinChkboxIsSelected.isSelected() == false) {
			dontInputBinChkbox.click();
		}

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dontMaintainStockByRMAChkbox));
		if (dontMaintainStockByRMAChkboxIsSelected.isSelected() == false) {
			dontMaintainStockByRMAChkbox.click();
		}

		Thread.sleep(2000);

		boolean actDontMaintainBatch = dontMaintainStockByBatchChkboxIsSelected.isSelected();
		boolean expDontMaintainBatch = true;

		boolean actDontInputBins = dontInputBinChkboxIsSelected.isSelected();
		boolean expDontInputBins = true;

		boolean actDontMaintainRMA = dontMaintainStockByRMAChkboxIsSelected.isSelected();
		boolean expDontMaintainRMA = true;

		System.out
				.println("Dont Maintain Batch : " + actDontMaintainBatch + "  Value Expected  " + expDontMaintainBatch);
		System.out.println("Dont Input Bins     : " + actDontInputBins + "  Value Expected  " + expDontInputBins);
		System.out.println("Dont Maintain RMA   : " + actDontMaintainRMA + "  Value Expected  " + expDontMaintainRMA);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemPropertiesOkBtn));
		itemPropertiesOkBtn.click();

		Thread.sleep(2000);

		if (actDontMaintainBatch == expDontMaintainBatch && actDontInputBins == expDontInputBins
				&& actDontMaintainRMA == expDontMaintainRMA)

		{

			return true;
		} else {

			return false;
		}

	}

	@FindBy(xpath = "//*[@id='ulCommonlyUsedRibbon']//li[7]")
	private static WebElement propetiesIcon;

	@FindBy(xpath = "//*[@id='chkDontMaintainStockbyBatch']")
	private static WebElement DontMaintainStockbyBatchChkbox;

	@FindBy(xpath = "//*[@id='chkDontinputBin']")
	private static WebElement DontinputBinChkbox;

	@FindBy(xpath = "//*[@id='chkDontMaintainStocksByRMA']")
	private static WebElement DontMaintainStocksByRMAChkbox;

	@FindBy(xpath = "//*[@id='divMasterProperty']/ul//li/span[1]")
	private static WebElement propertiesOkBtn;

	@FindBy(xpath = "//*[@id='divMasterProperty']/ul//li/span[2]")
	private static WebElement propertiesCancelBtn;

	@FindBy(xpath = "//*[@id='mainHeader_MainLayout']/nav/div/ul/li[6]/a/span")
	private static WebElement userName;

	@FindBy(xpath = "//*[@id='userprofile']/li/span[2]")
	private static WebElement logoutBtn;

	public boolean checkLogoutAndLoginAgain()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		//prongHornStopAtAdminLevel();

		 Thread.sleep(2000);
		  
		  String batCommand =
		  "cmd /c start C:\\Users\\Rakesh\\Desktop\\PronghornStop.lnk";
		  Thread.sleep(2000); Runtime.getRuntime().exec(batCommand);
		  
		  Thread.sleep(10000);
		  
		  System.err.println("Pronghorn stopped");
		
		Thread.sleep(2000);
		logout();

		Thread.sleep(2000);

		 
		  Thread.sleep(2000);
		  
		  String batCommand2 =
		  "cmd /c start C:\\Users\\Rakesh\\Desktop\\IISRESET.lnk";
		  Thread.sleep(2000); Runtime.getRuntime().exec(batCommand2);
		  
		  Thread.sleep(15000);
		  
		  System.err.println("InetManagerRestart");
		  
		  Thread.sleep(4503);
		  
		  String batCommand1 =
		  "cmd /c start C:\\Users\\Rakesh\\Desktop\\PronghornStart.lnk";
		  Thread.sleep(2000); Runtime.getRuntime().exec(batCommand1);
		  
		  Thread.sleep(10000);
		  
		  System.err.println("Pronghorn Started");
		  
		  Thread.sleep(8965);
		  

		checkLoginTOBRS();

		return true;

	}

	// opening PurchaseVocher VAT
	@FindBy(xpath = "//*[@id='60']")
	public static WebElement financeMenu;

	@FindBy(xpath = "//*[@id='61']")
	public static WebElement transactionsMenu;

	@FindBy(xpath = "//*[@id='2007']")
	public static WebElement purchasesMenu;

	@FindBy(xpath = "//*[@id='2057']")
	public static WebElement purchaseVocherVAT;

	@FindBy(xpath = "//*[@id='id_transaction_homescreen_new']")
	public static WebElement purchaseVochernewBtn;

	@FindBy(xpath = "//*[@id='id_header_1']")
	public static WebElement documentNumberTxt;

	@FindBy(xpath = "//*[@id='id_header_2']")
	public static WebElement dateTxt;

	@FindBy(xpath = "//*[@id='id_header_4']")
	public static WebElement vendorAcct;

	@FindBy(xpath = "//*[@id='id_header_21']")
	public static WebElement raiseReceipt;

	@FindBy(xpath = "//*[@id='id_header_6']")
	public static WebElement dueDate;

	@FindBy(xpath = "//*[@id='id_header_10']")
	public static WebElement vocherHeaderCurrency;

	@FindBy(xpath = "//*[@id='id_header_268435459']")
	public static WebElement departmentTxt;

	@FindBy(xpath = "//*[@id='id_header_268435470']")
	public static WebElement placeOfsupply;

	@FindBy(xpath = "//*[@id='id_header_268435471']")
	public static WebElement jurisdictionTxt;

	@FindBy(xpath = "//*[@id='id_header_67108938']")
	public static WebElement narration;

	@FindBy(xpath = "//*[@id='id_header_67108972']")
	public static WebElement permitNo;

	@FindBy(xpath = "//*[@id='id_header_67109036']")
	public static WebElement billNum;

	@FindBy(xpath = "//*[@id='id_body_536870916']")
	public static WebElement warehouseTxt;

	@FindBy(xpath = "//*[@id='id_body_16777323']")
	public static WebElement taxCode;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[1]/td[5]")
	public static WebElement purchaseAccount;

	@FindBy(xpath = "//*[@id='id_body_13']")
	public static WebElement batchTxt;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[1]/td[19]")
	public static WebElement RMATxt;

	@FindBy(xpath = "//*[@id='id_transactionentry_save']/a/span")
	public static WebElement voucherSaveBtn;

	@FindBy(xpath = "//*[@id='txtNewReference']")
	public static WebElement newBillReferenceTxt;

	@FindBy(xpath = "//*[@id='id_Pick']/a/span")
	public static WebElement pickBtn;

	@FindBy(xpath = "//*[@id='id_Ok']/a/span")
	public static WebElement okBtn;

	@FindBy(xpath = "//*[@id='id_transactionentry_close']/a")
	public static WebElement purchaseCloseBtnAfterSave;

	
	public boolean checkPurchaseVocherVATtoAddAttributeItemFruitsasItem()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException {
		
		
		excelReader = new ExcelReader(POJOUtility.getExcelPath());

		
		Thread.sleep(2569);
	/*	logout();
		
		Thread.sleep(2569);
		prongHornStopAtAdminLevel();
		Thread.sleep(2569);
		
		prongHornStartAtAdminLevel();
		Thread.sleep(2569);
		
		
		checkLoginTOBRS();
		
		Thread.sleep(2569);
		*/
		
		clickOn(financeMenu);
		Thread.sleep(1000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionsMenu));
		transactionsMenu.click();
		Thread.sleep(1000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(purchasesMenu));
		purchasesMenu.click();
		Thread.sleep(1000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(purchaseVocherVAT));
		purchaseVocherVAT.click();

		Thread.sleep(8000);

		click(newBtn);

		Thread.sleep(1000);

		//checkValidationMessage("Screen opened");
		// Thread.sleep(1000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNumberTxt));
		// documentNumberTxt.click();
		documentNumberTxt.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dateTxt));
		dateTxt.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(vendorAccountTxt));
		vendorAccountTxt.click();

		vendorAccountTxt.sendKeys(Keys.SPACE);

		ArrayList<String> actVendorAccountList = new ArrayList<String>();

		int vendorcount = vendorAccountListCount.size();

		System.err.println("Vendor Accounts count is:   " + vendorcount);

		for (int i = 0; i < vendorcount; i++) {
			String data = vendorAccountListCount.get(i).getText();

			if (data.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 65, 5))) {
				vendorAccountListCount.get(i).click();
				break;
			}
		}

		vendorAccountTxt.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(voucherHeaderDueDate));
		voucherHeaderDueDate.click();
		voucherHeaderDueDate.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(voucherHeaderCurrency));
		voucherHeaderCurrency.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME);
		voucherHeaderCurrency.sendKeys(Keys.SPACE);

		int currencycount = currencyListCount.size();

		System.err.println("Currency:" + currencycount);

		for (int i = 0; i < currencycount; i++) {
			String data = currencyListCount.get(i).getText();

			if (data.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 66, 5))) {
				currencyListCount.get(i).click();
				break;
			}
		}

		voucherHeaderCurrency.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(departmentTxt));
		departmentTxt.click();
		departmentTxt.sendKeys(Keys.SPACE);

		ArrayList<String> actDepartmentList = new ArrayList<String>();

		int departmentCount = departmentListCount.size();

		System.err.println("Departments Count is:    " + departmentCount);

		for (int i = 0; i < departmentCount; i++) {
			String data = departmentListCount.get(i).getText();

			if (data.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 67, 5))) {
				departmentListCount.get(i).click();
				break;
			}
		}

		departmentTxt.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(placeOfsupply));
		placeOfsupply.sendKeys(excelReader.getCellData(xlSheetName, 68, 5));
		placeOfsupply.sendKeys(Keys.TAB);
		Thread.sleep(1000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(jurisdictionTxt));
		jurisdictionTxt.click();
		jurisdictionTxt.sendKeys(excelReader.getCellData(xlSheetName, 69, 5));
		Thread.sleep(2000);
		jurisdictionTxt.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_1stColumn));
		select1stRow_1stColumn.click();
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_WarehouseTxt));
		enter_WarehouseTxt.click();

		enter_WarehouseTxt.sendKeys(Keys.SPACE);

		int warehousecount = warehouseBodyComboList.size();

		for (int i = 0; i < warehousecount; i++) {
			String data = warehouseBodyComboList.get(i).getText();

			if (data.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 70, 5))) {
				warehouseBodyComboList.get(i).click();
				break;
			}
		}

		enter_WarehouseTxt.sendKeys(Keys.TAB);

		Thread.sleep(1000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_ItemTxt));
		enter_ItemTxt.click();
		enter_ItemTxt.sendKeys(excelReader.getCellData(xlSheetName, 71, 5));
		enter_ItemTxt.sendKeys(Keys.ARROW_DOWN);
		Thread.sleep(2000);

		// int size=pvvGridItemList.size();
		// System.err.println("Items under Fruits Item are: "+size);
		int pvvGridItemListCount = pvvGridItemList.size();

		for (int i = 0; i < pvvGridItemListCount; i++) {
			String Item = pvvGridItemList.get(i).getText();

			if (Item.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 72, 5))) {
				pvvGridItemList.get(i).click();
				break;
			}
		}
		enter_ItemTxt.sendKeys(Keys.TAB);
		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(taxCode));
		taxCode.sendKeys(Keys.TAB);

		Thread.sleep(1000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_4thColumn));
		select1stRow_4thColumn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_PurchaseAccountTxt));
		enter_PurchaseAccountTxt.sendKeys(excelReader.getCellData(xlSheetName, 73, 5));
		enter_PurchaseAccountTxt.sendKeys(Keys.TAB);

		Thread.sleep(1000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_9thColumn));
		select1stRow_9thColumn.click();
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_Quantity));
		enter_Quantity.click();
		enter_Quantity.clear();
		enter_Quantity.sendKeys(excelReader.getCellData(xlSheetName, 74, 5));

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_11thColumn));
		select1stRow_11thColumn.click();
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_Rate));
		enter_Rate.click();
		enter_Rate.clear();
		enter_Rate.sendKeys(excelReader.getCellData(xlSheetName, 75, 5));
		enter_Rate.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_Gross));
		enter_Gross.click();
		enter_Gross.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_14thColumn));
		select1stRow_14thColumn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_PvVat));
		enter_PvVat.click();

		enter_PvVat.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_PvTaxable));
		enter_PvTaxable.click();
		enter_PvTaxable.sendKeys(Keys.TAB);

		// entering second item
		getWebDriverWait().until(ExpectedConditions.elementToBeClickable(select2ndRow_1stColumn));
		select2ndRow_1stColumn.click();
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_WarehouseTxt));
		enter_WarehouseTxt.click();
		enter_WarehouseTxt.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME);
		enter_WarehouseTxt.sendKeys(Keys.SPACE);

		int warehousecount1 = warehouseBodyComboList.size();

		for (int i = 0; i < warehousecount1; i++) {
			String data = warehouseBodyComboList.get(i).getText();

			if (data.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 76, 5))) {
				warehouseBodyComboList.get(i).click();
				break;
			}
		}

		enter_WarehouseTxt.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select2ndRow_2ndColumn));
		select2ndRow_2ndColumn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_ItemTxt));
		enter_ItemTxt.click();
		enter_ItemTxt.sendKeys(excelReader.getCellData(xlSheetName, 77, 5));
		enter_ItemTxt.sendKeys(Keys.ARROW_DOWN);
		Thread.sleep(2000);

		// int size=pvvGridItemList.size();
		// enter_ItemTxt.sendKeys(Keys.SPACE);
		int pvvGridItemListCount1 = pvvGridItemList.size();
		for (int i = 0; i < pvvGridItemListCount1; i++) {
			String Item = pvvGridItemList.get(i).getText();
			if (Item.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 78, 5))) {
				pvvGridItemList.get(i).click();
				break;
			}
		}
		enter_ItemTxt.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(taxCode));
		taxCode.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select2ndRow_4thColumn));
		select2ndRow_4thColumn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_PurchaseAccountTxt));
		enter_PurchaseAccountTxt.sendKeys(excelReader.getCellData(xlSheetName, 79, 5));
		enter_PurchaseAccountTxt.sendKeys(Keys.TAB);

		Thread.sleep(1000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select2ndRow_9thColumn));
		select2ndRow_9thColumn.click();
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_Quantity));
		enter_Quantity.click();
		enter_Quantity.clear();
		enter_Quantity.sendKeys(excelReader.getCellData(xlSheetName, 80, 5));

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select2ndRow_11thColumn));
		select2ndRow_11thColumn.click();
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_Rate));
		enter_Rate.click();
		enter_Rate.clear();
		enter_Rate.sendKeys(excelReader.getCellData(xlSheetName, 81, 5));
		enter_Rate.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_Gross));
		enter_Gross.click();
		enter_Gross.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select2ndRow_14thColumn));
		select2ndRow_14thColumn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_PvVat));
		enter_PvVat.click();

		enter_PvVat.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_PvTaxable));
		enter_PvTaxable.click();
		enter_PvTaxable.sendKeys(Keys.TAB);

		Thread.sleep(3000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(voucherSaveBtn));
		voucherSaveBtn.click();

		Thread.sleep(1000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(newBillReferenceTxt));

		newBillReferenceTxt.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(pickBtn));
		pickBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(okBtn));

		okBtn.click();

		Thread.sleep(1000);

		//clickOn(purchaseCloseBtnAfterSave);
		//Thread.sleep(1000);

		// purchaseVATCloseBtn.click();
		// Thread.sleep(1000);

		String expMessage = excelReader.getCellData(xlSheetName, 82, 6);
		String actMessage = checkValidationMessage(expMessage);
		excelReader.setCellData(xlfile, xlSheetName, 82, 7, actMessage);

		if (actMessage.startsWith(expMessage)) {
			excelReader.setCellData(xlfile, xlSheetName, 64, 8, resPass);
			return true;
		} else {
			excelReader.setCellData(xlfile, xlSheetName, 64, 8, resFail);
			return false;
		}
	}

	public boolean checkPurchaseVocherVATtoAddAttributeItemElectronicsasItem()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {
		
		getDriver().navigate().refresh();
		Thread.sleep(4000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(financeMenu));
		financeMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionsMenu));
		transactionsMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(purchasesMenu));
		purchasesMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(purchaseVocherVAT));
		purchaseVocherVAT.click();
		Thread.sleep(1000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(purchaseVochernewBtn));
		purchaseVochernewBtn.click();

		Thread.sleep(1000);

		//checkValidationMessage("Screen opened");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNumberTxt));
		// documentNumberTxt.click();
		documentNumberTxt.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dateTxt));
		dateTxt.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(vendorAccountTxt));
		vendorAccountTxt.click();

		vendorAccountTxt.sendKeys(Keys.SPACE);

		ArrayList<String> actVendorAccountList = new ArrayList<String>();

		int vendorcount = vendorAccountListCount.size();

		System.err.println("Vendor accounts count:  " + vendorcount);

		for (int i = 0; i < vendorcount; i++) {
			String data = vendorAccountListCount.get(i).getText();

			if (data.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 84, 5))) {
				vendorAccountListCount.get(i).click();
				break;
			}
		}

		vendorAccountTxt.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(voucherHeaderDueDate));
		voucherHeaderDueDate.click();
		voucherHeaderDueDate.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(voucherHeaderCurrency));
		voucherHeaderCurrency.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME);
		voucherHeaderCurrency.sendKeys(Keys.SPACE);

		int currencycount = currencyListCount.size();

		System.err.println("Currency count:  " + currencycount);

		for (int i = 0; i < currencycount; i++) {
			String data = currencyListCount.get(i).getText();

			if (data.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 85, 5))) {
				currencyListCount.get(i).click();
				break;
			}
		}

		voucherHeaderCurrency.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(departmentTxt));
		departmentTxt.click();
		departmentTxt.sendKeys(Keys.SPACE);

		ArrayList<String> actDepartmentList = new ArrayList<String>();

		int departmentCount = departmentListCount.size();

		System.err.println("Departments Count:   " + departmentCount);

		for (int i = 0; i < departmentCount; i++) {
			String data = departmentListCount.get(i).getText();

			if (data.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 86, 5))) {
				departmentListCount.get(i).click();
				break;
			}
		}

		departmentTxt.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(placeOfsupply));
		placeOfsupply.sendKeys(excelReader.getCellData(xlSheetName, 87, 5));
		placeOfsupply.sendKeys(Keys.TAB);
		Thread.sleep(1000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(jurisdictionTxt));
		jurisdictionTxt.click();
		jurisdictionTxt.sendKeys(excelReader.getCellData(xlSheetName, 88, 5));
		Thread.sleep(2000);
		jurisdictionTxt.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_1stColumn));
		select1stRow_1stColumn.click();
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_WarehouseTxt));
		enter_WarehouseTxt.click();

		enter_WarehouseTxt.sendKeys(Keys.SPACE);

		int warehousecount = warehouseBodyComboList.size();

		for (int i = 0; i < warehousecount; i++) {
			String data = warehouseBodyComboList.get(i).getText();

			if (data.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 89, 5))) {
				warehouseBodyComboList.get(i).click();
				break;
			}
		}

		enter_WarehouseTxt.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_ItemTxt));
		enter_ItemTxt.click();
		enter_ItemTxt.sendKeys(excelReader.getCellData(xlSheetName, 90, 5));
		enter_ItemTxt.sendKeys(Keys.ARROW_DOWN);
		Thread.sleep(2000);

		int pvvGridItemListCount = pvvGridItemList.size();
		for (int i = 0; i < pvvGridItemListCount; i++) {
			String Item = pvvGridItemList.get(i).getText();

			if (Item.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 91, 5))) {
				pvvGridItemList.get(i).click();
				break;
			}
		}
		enter_ItemTxt.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(taxCode));
		taxCode.sendKeys(Keys.TAB);

		Thread.sleep(1000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_4thColumn));
		select1stRow_4thColumn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_PurchaseAccountTxt));
		enter_PurchaseAccountTxt.sendKeys(excelReader.getCellData(xlSheetName, 92, 5));
		enter_PurchaseAccountTxt.sendKeys(Keys.TAB);

		Thread.sleep(1000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_9thColumn));
		select1stRow_9thColumn.click();
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_Quantity));
		enter_Quantity.click();
		enter_Quantity.clear();
		enter_Quantity.sendKeys(excelReader.getCellData(xlSheetName, 93, 5));

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_11thColumn));
		select1stRow_11thColumn.click();
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_Rate));
		enter_Rate.click();
		enter_Rate.clear();
		enter_Rate.sendKeys(excelReader.getCellData(xlSheetName, 94, 5));
		enter_Rate.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_Gross));
		enter_Gross.click();
		enter_Gross.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_14thColumn));
		select1stRow_14thColumn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_PvVat));
		enter_PvVat.click();

		enter_PvVat.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_PvTaxable));
		enter_PvTaxable.click();
		enter_PvTaxable.sendKeys(Keys.TAB);

		Thread.sleep(3000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(voucherSaveBtn));
		voucherSaveBtn.click();

		Thread.sleep(1000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(newBillReferenceTxt));

		newBillReferenceTxt.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(pickBtn));
		pickBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(okBtn));

		okBtn.click();
		Thread.sleep(2000);

		purchaseCloseBtnAfterSave.click();

		// Thread.sleep(3000);
		// purchaseVATCloseBtn.click();

		String expMessage = excelReader.getCellData(xlSheetName, 95, 6);
		String actMessage = checkValidationMessage(expMessage);
		excelReader.setCellData(xlfile, xlSheetName, 95, 7, actMessage);

		if (actMessage.contains(expMessage)) {
			excelReader.setCellData(xlfile, xlSheetName, 83, 8, resPass);
			return true;
		} else {
			excelReader.setCellData(xlfile, xlSheetName, 83, 8, resFail);
			return false;
		}
	}

	@FindBy(xpath = "//*[@id='navigationtab14']")
	public static WebElement InventoryOptions;

	@FindBy(xpath = "//div[6]/div/div/div/div/h2[1]/button")
	public static WebElement InventorySettings;

	// *[@id="inventoryOpt_chkInputQntyBreakup"]

	@FindBy(xpath = "//*[contains(text(),'Input Item by Attribute')]//span")
	public static WebElement InputitembyattributeCheckBoxToSelect;

	@FindBy(xpath = "//*[contains(text(),'Input Item by Attribute')]//input")
	public static WebElement InputitembyattributeCheckBox;

	@FindBy(xpath = "//*[@id='updateButton']/i")
	public static WebElement updateBtn;

	@FindBy(xpath = "//*[@id='btnCustomizeClose']/i")
	public static WebElement settingscloseBtn;

	/*
	 * @FindBy(xpath="//*[@id='dvHomeTransClose']/div[1]/span") public static
	 * WebElement purchaseVATcloseBtn;
	 */

	public boolean checkSettingsInventoryOptionsinPurchaseVATforInputItemByAttributeCheckBox()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException

	{
		excelReader = new ExcelReader(POJOUtility.getExcelPath());

		clickOn(financeMenu);

		clickOn(transactionsMenu);

		clickOn(purchasesMenu);

		clickOn(purchaseVocherVAT);
		Thread.sleep(1000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(settingsBtn));
		settingsBtn.click();
		Thread.sleep(1000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(InventoryOptions));
		InventoryOptions.click();

		Thread.sleep(2000);

		boolean act = InputitembyattributeCheckBoxToSelect.isDisplayed();

		System.out.println("************" + act);

		if (InputitembyattributeCheckBoxToSelect.isDisplayed() == false) {
			getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(InventorySettings));
			InventorySettings.click();

			System.err.println("Inventory Tab --->inv Setting is Maximised");

		}

		Thread.sleep(1000);

		getAction().moveToElement(InputitembyattributeCheckBoxToSelect).build().perform();

		Thread.sleep(1000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(InputitembyattributeCheckBoxToSelect));
		InputitembyattributeCheckBoxToSelect.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(updateBtn));
		updateBtn.click();

		String expMessage = excelReader.getCellData(xlSheetName, 97, 6);
		String actMessage = checkValidationMessage(expMessage);
		excelReader.setCellData(xlfile, xlSheetName, 97, 7, actMessage);

		
		Thread.sleep(2569);
		
		clickOn(settingscloseBtn);
		

		if (actMessage.equalsIgnoreCase(actMessage)) {
			excelReader.setCellData(xlfile, xlSheetName, 96, 8, resPass);
			return true;
		} else {
			excelReader.setCellData(xlfile, xlSheetName, 96, 8, resFail);
			return false;
		}
	}

	@FindBy(xpath = "//*[@id='id_body_productattribute_attributeEntryOptinCtrl']")
	public static WebElement attributeEntryOption;

	@FindBy(xpath = "//*[@id='id_body_productattribute']/div/div/div[1]")
	public static WebElement window;

	@FindBy(xpath = "//*[@id='id_body_productattribute_0_']")
	public static WebElement selectAttributeItem;

	@FindBy(xpath = "//*[@id='id_body_productattribute']/div[2]/div/div[3]/div/div[3]/input[1]")
	public static WebElement OkBtn;

	@FindBy(xpath = "//*[@id='id_transactionentry_close']/div[1]/span")
	public static WebElement purchaseVATAfterSavecloseBtn;

	@FindBy(xpath = "//*[@id='dvHomeTransClose']/div[1]/span")
	public static WebElement purchaseVochercloseBtn;

	public boolean checkDisplayingofAttribute0WindowinItemsTabinPurchaseVocherVAT()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		excelReader = new ExcelReader(POJOUtility.getExcelPath());

		Thread.sleep(3000);

		
		
		focusMainSearch("Purchases Voucher VAT");

		Thread.sleep(6000);

		clickOn(purchaseVochernewBtn);

		//checkValidationMessage("Screen opened");

		Thread.sleep(4000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNumberTxt));
		documentNumberTxt.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dateTxt));
		dateTxt.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(vendorAccountTxt));
		vendorAccountTxt.click();

		vendorAccountTxt.sendKeys(Keys.SPACE);

		ArrayList<String> actVendorAccountList = new ArrayList<String>();

		int vendorcount = vendorAccountListCount.size();

		System.err.println(vendorcount);

		for (int i = 0; i < vendorcount; i++) {
			String data = vendorAccountListCount.get(i).getText();

			if (data.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 99, 5))) {
				vendorAccountListCount.get(i).click();
				break;
			}
		}

		vendorAccountTxt.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(voucherHeaderDueDate));
		voucherHeaderDueDate.click();
		voucherHeaderDueDate.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(voucherHeaderCurrency));
		voucherHeaderCurrency.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME);
		voucherHeaderCurrency.sendKeys(Keys.SPACE);

		int currencycount = currencyListCount.size();

		System.err.println(currencycount);

		for (int i = 0; i < currencycount; i++) {
			String data = currencyListCount.get(i).getText();

			if (data.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 100, 5))) {
				currencyListCount.get(i).click();
				break;
			}
		}

		voucherHeaderCurrency.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(departmentTxt));
		departmentTxt.click();
		departmentTxt.sendKeys(Keys.SPACE);

		ArrayList<String> actDepartmentList = new ArrayList<String>();

		int departmentCount = departmentListCount.size();

		System.err.println(departmentCount);

		for (int i = 0; i < departmentCount; i++) {
			String data = departmentListCount.get(i).getText();

			if (data.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 101, 5))) {
				departmentListCount.get(i).click();
				break;
			}
		}

		departmentTxt.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(placeOfsupply));
		placeOfsupply.sendKeys(excelReader.getCellData(xlSheetName, 102, 5));
		placeOfsupply.sendKeys(Keys.TAB);
		Thread.sleep(1000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(jurisdictionTxt));
		jurisdictionTxt.click();
		jurisdictionTxt.sendKeys(excelReader.getCellData(xlSheetName, 103, 5));
		Thread.sleep(2000);
		jurisdictionTxt.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_1stColumn));
		select1stRow_1stColumn.click();
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_WarehouseTxt));
		enter_WarehouseTxt.click();

		enter_WarehouseTxt.sendKeys(Keys.SPACE);

		int warehousecount = warehouseBodyComboList.size();

		for (int i = 0; i < warehousecount; i++) {
			String data = warehouseBodyComboList.get(i).getText();

			if (data.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 104, 5))) {
				warehouseBodyComboList.get(i).click();
				break;
			}
		}

		enter_WarehouseTxt.sendKeys(Keys.TAB);
		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(window));
		window.click();

		getAction().moveToElement(attributeEntryOption);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(attributeEntryOption));
		attributeEntryOption.click();
		attributeEntryOption.sendKeys(excelReader.getCellData(xlSheetName, 105, 5));
		Thread.sleep(2500);
		attributeEntryOption.sendKeys(Keys.TAB);

		Thread.sleep(4000);

		String ExpsubAttributeItem = "Apple";

		Select select = new Select(selectAttributeItem);
		List<WebElement> data = select.getOptions();
		ArrayList<String> subAttrItems = new ArrayList<String>();
		for (WebElement e : data) {
			String Attrdata = e.getText();

			subAttrItems.add(Attrdata);

			System.err.println("SubAttrributes---" + subAttrItems);

		}

		// System.err.println(subAttrItems);

		String ActsubAttributeItem = subAttrItems.get(0);

		if (ActsubAttributeItem.equals(ExpsubAttributeItem)) {
			System.err.println("true");
		} else {
			System.err.println("AttributeItems not matched");
		}

		select.selectByVisibleText("Banana");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(OkBtn));
		OkBtn.click();

		Thread.sleep(1000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(taxCode));
		taxCode.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_4thColumn));
		select1stRow_4thColumn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_PurchaseAccountTxt));
		enter_PurchaseAccountTxt.sendKeys(excelReader.getCellData(xlSheetName, 106, 5));
		Thread.sleep(1000);
		enter_PurchaseAccountTxt.sendKeys(Keys.TAB);

		Thread.sleep(1000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_9thColumn));
		select1stRow_9thColumn.click();
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_Quantity));
		enter_Quantity.click();
		enter_Quantity.clear();
		enter_Quantity.sendKeys(excelReader.getCellData(xlSheetName, 107, 5));

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_11thColumn));
		select1stRow_11thColumn.click();
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_Rate));
		enter_Rate.click();
		enter_Rate.clear();
		enter_Rate.sendKeys(excelReader.getCellData(xlSheetName, 108, 5));
		enter_Rate.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_Gross));
		enter_Gross.click();
		enter_Gross.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_14thColumn));
		select1stRow_14thColumn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_PvVat));
		enter_PvVat.click();

		enter_PvVat.sendKeys(Keys.TAB);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_15thColumn));
		select1stRow_15thColumn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(voucherSaveBtn));
		voucherSaveBtn.click();
		Thread.sleep(1000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(newBillReferenceTxt));

		newBillReferenceTxt.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(pickBtn));
		pickBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(okBtn));

		okBtn.click();

		String expMessage = excelReader.getCellData(xlSheetName, 109, 6);
		String actMessage = checkValidationMessage(expMessage);
		excelReader.setCellData(xlfile, xlSheetName, 109, 7, actMessage);

		if (actMessage.contains(expMessage)) {
			excelReader.setCellData(xlfile, xlSheetName, 98, 8, resPass);
			return true;
		} else {
			excelReader.setCellData(xlfile, xlSheetName, 98, 8, resFail);
			return false;
		}

	}

	public boolean checkDisplayingofAttribute1WindowinItemsTabinPurchaseVocherVAT()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		excelReader = new ExcelReader(POJOUtility.getExcelPath());

		/*
		 * getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(
		 * financeMenu)); financeMenu.click(); Thread.sleep(1000);
		 * 
		 * getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(
		 * transactionsMenu)); transactionsMenu.click(); Thread.sleep(1000);
		 * 
		 * getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(
		 * purchasesMenu)); purchasesMenu.click(); Thread.sleep(1000);
		 * 
		 * getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(
		 * purchaseVocherVAT)); purchaseVocherVAT.click();
		 */
		
		focusMainSearch("Purchases Voucher VAT");
		

		Thread.sleep(1000);
		
		clickOn(purchaseVochernewBtn);
		
		Thread.sleep(1000);

		//checkValidationMessage("screen Opened");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNumberTxt));
		documentNumberTxt.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dateTxt));
		dateTxt.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(vendorAccountTxt));
		vendorAccountTxt.click();

		vendorAccountTxt.sendKeys(Keys.SPACE);

		ArrayList<String> actVendorAccountList = new ArrayList<String>();

		int vendorcount = vendorAccountListCount.size();

		System.err.println(vendorcount);

		for (int i = 0; i < vendorcount; i++) {
			String data = vendorAccountListCount.get(i).getText();

			if (data.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 111, 5))) {
				vendorAccountListCount.get(i).click();
				break;
			}
		}

		vendorAccountTxt.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(voucherHeaderDueDate));
		voucherHeaderDueDate.click();
		voucherHeaderDueDate.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(voucherHeaderCurrency));
		voucherHeaderCurrency.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME);
		voucherHeaderCurrency.sendKeys(Keys.SPACE);

		int currencycount = currencyListCount.size();

		System.err.println(currencycount);

		for (int i = 0; i < currencycount; i++) {
			String data = currencyListCount.get(i).getText();

			if (data.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 112, 5))) {
				currencyListCount.get(i).click();
				break;
			}
		}

		voucherHeaderCurrency.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(departmentTxt));
		departmentTxt.click();
		departmentTxt.sendKeys(Keys.SPACE);

		ArrayList<String> actDepartmentList = new ArrayList<String>();

		int departmentCount = departmentListCount.size();

		System.err.println(departmentCount);

		for (int i = 0; i < departmentCount; i++) {
			String data = departmentListCount.get(i).getText();

			if (data.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 113, 5))) {
				departmentListCount.get(i).click();
				break;
			}
		}

		departmentTxt.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(placeOfsupply));
		placeOfsupply.sendKeys(excelReader.getCellData(xlSheetName, 114, 5));
		placeOfsupply.sendKeys(Keys.TAB);
		Thread.sleep(1000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(jurisdictionTxt));
		jurisdictionTxt.click();
		jurisdictionTxt.sendKeys(excelReader.getCellData(xlSheetName, 115, 5));
		Thread.sleep(2000);
		jurisdictionTxt.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_1stColumn));
		select1stRow_1stColumn.click();
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_WarehouseTxt));
		enter_WarehouseTxt.click();

		enter_WarehouseTxt.sendKeys(Keys.SPACE);

		int warehousecount = warehouseBodyComboList.size();

		for (int i = 0; i < warehousecount; i++) {
			String data = warehouseBodyComboList.get(i).getText();

			if (data.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 116, 5))) {
				warehouseBodyComboList.get(i).click();
				break;
			}
		}

		enter_WarehouseTxt.sendKeys(Keys.TAB);
		Thread.sleep(1000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(window));
		window.click();

		getAction().moveToElement(attributeEntryOption);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(attributeEntryOption));
		attributeEntryOption.click();
		attributeEntryOption.sendKeys(excelReader.getCellData(xlSheetName, 117, 5));
		Thread.sleep(1000);

		attributeEntryOption.sendKeys(Keys.TAB);

		Thread.sleep(3500);

		String ExpsubAttributeItem = "Laptop";

		Select select = new Select(selectAttributeItem);
		List<WebElement> data = select.getOptions();
		ArrayList<String> subAttrItems = new ArrayList<String>();
		for (WebElement e : data) {
			String Attrdata = e.getText();

			subAttrItems.add(Attrdata);

			System.err.println("SubAttrributes of Electronics---" + subAttrItems);

		}

		// System.err.println(subAttrItems);

		String ActsubAttributeItem = subAttrItems.get(1);

		if (ActsubAttributeItem.equals(ExpsubAttributeItem)) {
			System.err.println("true");
		} else {
			System.err.println("AttributeItems not matched");
			System.err.println("Actual subattribute Item" + ActsubAttributeItem + "Expected subattribute Item   "
					+ ExpsubAttributeItem);
		}
		/*
		 * select.selectByVisibleText("Laptop");
		 * 
		 * Thread.sleep(2000); selectAttributeItem.sendKeys(Keys.TAB);
		 */

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(OkBtn));
		OkBtn.click();

		Thread.sleep(1000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(taxCode));
		taxCode.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_4thColumn));
		select1stRow_4thColumn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_PurchaseAccountTxt));
		enter_PurchaseAccountTxt.sendKeys(excelReader.getCellData(xlSheetName, 118, 5));
		Thread.sleep(1000);
		enter_PurchaseAccountTxt.sendKeys(Keys.TAB);

		Thread.sleep(1000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_9thColumn));
		select1stRow_9thColumn.click();
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_Quantity));
		enter_Quantity.click();
		enter_Quantity.clear();
		enter_Quantity.sendKeys(excelReader.getCellData(xlSheetName, 119, 5));

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_11thColumn));
		select1stRow_11thColumn.click();
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_Rate));
		enter_Rate.click();
		enter_Rate.clear();
		enter_Rate.sendKeys(excelReader.getCellData(xlSheetName, 120, 5));
		enter_Rate.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_Gross));
		enter_Gross.click();
		enter_Gross.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_14thColumn));
		select1stRow_14thColumn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_PvVat));
		enter_PvVat.click();

		enter_PvVat.sendKeys(Keys.TAB);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_15thColumn));
		select1stRow_15thColumn.click();

		String docno = documentNumberTxt.getAttribute("value");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(voucherSaveBtn));
		voucherSaveBtn.click();
		Thread.sleep(1000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(newBillReferenceTxt));

		newBillReferenceTxt.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(pickBtn));
		pickBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(okBtn));

		okBtn.click();

		boolean expMessage = true;
		boolean actMessage = checkBackgroundSavingMessage(docno);

		Thread.sleep(3000);

		if (actMessage == expMessage) {
			excelReader.setCellData(xlfile, xlSheetName, 110, 8, resPass);
			return true;
		} else {

			excelReader.setCellData(xlfile, xlSheetName, 110, 8, resFail);
			return false;
		}
	}

	public boolean checkAddingItemSTDCOGSRateinItemWindowinPurchaseVocherVAT()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		excelReader = new ExcelReader(POJOUtility.getExcelPath());

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(financeMenu));
		financeMenu.click();
		Thread.sleep(1000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionsMenu));
		transactionsMenu.click();
		Thread.sleep(1000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(purchasesMenu));
		purchasesMenu.click();
		Thread.sleep(1000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(purchaseVocherVAT));
		purchaseVocherVAT.click();

		Thread.sleep(1000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(purchaseVochernewBtn));
		purchaseVochernewBtn.click();

		checkValidationMessage("Screen opened(00:710)");
		Thread.sleep(1000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNumberTxt));
		documentNumberTxt.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dateTxt));
		dateTxt.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(vendorAccountTxt));
		vendorAccountTxt.click();

		vendorAccountTxt.sendKeys(Keys.SPACE);

		ArrayList<String> actVendorAccountList = new ArrayList<String>();

		int vendorcount = vendorAccountListCount.size();

		System.err.println(vendorcount);

		for (int i = 0; i < vendorcount; i++) {
			String data = vendorAccountListCount.get(i).getText();

			if (data.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 123, 5))) {
				vendorAccountListCount.get(i).click();
				break;
			}
		}

		vendorAccountTxt.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(voucherHeaderDueDate));
		voucherHeaderDueDate.click();
		voucherHeaderDueDate.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(voucherHeaderCurrency));
		voucherHeaderCurrency.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME);
		voucherHeaderCurrency.sendKeys(Keys.SPACE);

		int currencycount = currencyListCount.size();

		System.err.println(currencycount);

		for (int i = 0; i < currencycount; i++) {
			String data = currencyListCount.get(i).getText();

			if (data.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 124, 5))) {
				currencyListCount.get(i).click();
				break;
			}
		}

		voucherHeaderCurrency.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(departmentTxt));
		departmentTxt.click();
		departmentTxt.sendKeys(Keys.SPACE);

		ArrayList<String> actDepartmentList = new ArrayList<String>();

		int departmentCount = departmentListCount.size();

		System.err.println(departmentCount);

		for (int i = 0; i < departmentCount; i++) {
			String data = departmentListCount.get(i).getText();

			if (data.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 125, 5))) {
				departmentListCount.get(i).click();
				break;
			}
		}

		departmentTxt.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(placeOfsupply));
		placeOfsupply.sendKeys(excelReader.getCellData(xlSheetName, 126, 5));
		placeOfsupply.sendKeys(Keys.TAB);
		Thread.sleep(1000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(jurisdictionTxt));
		jurisdictionTxt.click();
		jurisdictionTxt.sendKeys(excelReader.getCellData(xlSheetName, 127, 5));
		Thread.sleep(2000);
		jurisdictionTxt.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_1stColumn));
		select1stRow_1stColumn.click();
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_WarehouseTxt));
		enter_WarehouseTxt.click();

		enter_WarehouseTxt.sendKeys(Keys.SPACE);

		int warehousecount = warehouseBodyComboList.size();

		for (int i = 0; i < warehousecount; i++) {
			String data = warehouseBodyComboList.get(i).getText();

			if (data.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 128, 5))) {
				warehouseBodyComboList.get(i).click();
				break;
			}
		}

		enter_WarehouseTxt.sendKeys(Keys.TAB);
		Thread.sleep(2569);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(window));
		window.click();
		
		Thread.sleep(2569);

		getAction().moveToElement(attributeEntryOption);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(attributeEntryOption));
		attributeEntryOption.click();

		Thread.sleep(2569);
		attributeEntryOption.sendKeys(excelReader.getCellData(xlSheetName, 129, 5));
		Thread.sleep(2000);
		attributeEntryOption.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(OkBtn));
		OkBtn.click();
		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(taxCode));
		taxCode.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_4thColumn));
		select1stRow_4thColumn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_PurchaseAccountTxt));
		// enter_PurchaseAccountTxt.sendKeys("Purchase");
		// Thread.sleep(1000);
		enter_PurchaseAccountTxt.sendKeys(Keys.TAB);

		Thread.sleep(1000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_9thColumn));
		select1stRow_9thColumn.click();
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_Quantity));
		enter_Quantity.click();
		enter_Quantity.clear();
		enter_Quantity.sendKeys(excelReader.getCellData(xlSheetName, 130, 5));

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_11thColumn));
		select1stRow_11thColumn.click();
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_Rate));
		enter_Rate.click();
		enter_Rate.clear();
		enter_Rate.sendKeys(excelReader.getCellData(xlSheetName, 131, 5));
		enter_Rate.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_Gross));
		enter_Gross.click();
		enter_Gross.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_14thColumn));
		select1stRow_14thColumn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_PvVat));
		enter_PvVat.click();

		enter_PvVat.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_15thColumn));
		select1stRow_15thColumn.click();

		String docno = documentNumberTxt.getAttribute("value");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(voucherSaveBtn));
		voucherSaveBtn.click();
		Thread.sleep(1000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(newBillReferenceTxt));

		newBillReferenceTxt.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(pickBtn));
		pickBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(okBtn));

		okBtn.click();

		Thread.sleep(3000);

		boolean expMessage = true;
		boolean actMessage = checkBackgroundSavingMessage(docno);

		if (actMessage == expMessage) {
			excelReader.setCellData(xlfile, xlSheetName, 122, 8, resPass);
			return true;
		} else {
			excelReader.setCellData(xlfile, xlSheetName, 122, 8, resFail);
			return false;
		}
	}

	public boolean checkDateFilterOptionInVoucherEntryPage() throws InterruptedException {

		Thread.sleep(2000);

		NavigationToPurchaseVouchersVat();

		Thread.sleep(2000);

		String actCountBefore = listOfElements(paymnetsPendingBillsSlNoList);
		String expCountBefore = "[1, 2, 3, 4, 5,  ]";

		System.err.println(" ACT CountBefore: " + actCountBefore);
		System.err.println(" EXP CountBefore: " + expCountBefore);

		Thread.sleep(2000);

		clickOn(homepagePannelOpenBtn);

		Thread.sleep(2000);
		clickOn(homePage_filterBtn);

		Thread.sleep(2000);
		clickOn(homePage_filterBtn_CusBtn);

		Thread.sleep(4500);
		clickOn(homePage_filterBtn_SelectFiledTxt);

		Thread.sleep(2000);

		clickOn(dateField);

		Thread.sleep(2000);

		Select s1 = new Select(selectFieldDrpdwn);
		s1.selectByValue("0");

		Thread.sleep(2000);

		Select s2 = new Select(selectField_CompareDrpdwn);
		s2.selectByValue("0");

		Thread.sleep(2000);

		clickOn(filter_DateTxt);

		removetTxt(filter_DateTxt);
		filter_DateTxt.sendKeys(currentDate());

		Thread.sleep(2000);

		filter_DateTxt.sendKeys(Keys.TAB);

		if (getIsAlertPresent()) {
			System.err.println(" Alert Is DIsplayed and Issue Exists********************");

			System.err.println(getAlert().getText());

			Thread.sleep(2000);
			getAlert().accept();
		}

		clickOn(filter_OkBtn);

		Thread.sleep(2500);

		String actCount = listOfElements(paymnetsPendingBillsSlNoList);
		String expCount = "[1, 2, 3, 4, 5,  ]";

		System.err.println(" ACT Count: " + actCount);
		System.err.println(" EXP Count: " + expCount);

		if (actCount.equalsIgnoreCase(expCount) && actCountBefore.equalsIgnoreCase(expCountBefore)) {

			System.err.println(" Test Pass: Filter Working Fine");
			return true;
		} else {
			System.err.println(" Test FAIl: Filter Working Fine");
			return false;
		}

	}

	public boolean checkLoadingSavedAttributesInMasterLevel()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		getDriver().navigate().refresh();

		clickOn(homeMenu);

		Thread.sleep(2000);
		clickOn(mastersMenu);

		Thread.sleep(2000);

		clickOn(itemMenu);
		Thread.sleep(2000);

		clickOn(item);

		Thread.sleep(4569);

		clickOn(itemNewBtn);
		itemName.click();
		itemName.clear();
		itemName.sendKeys("A");
		itemName.sendKeys(Keys.TAB);

		itemCode.click();
		itemCode.clear();
		itemCode.sendKeys("A");
		itemCode.sendKeys(Keys.TAB);

		// units addding
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(unitsTab));
		unitsTab.click();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(baseUnit));
		baseUnit.click();
		baseUnit.clear();
		baseUnit.sendKeys("Dozs");
		Thread.sleep(1000);
		baseUnit.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(salesUnit));
		salesUnit.click();
		salesUnit.clear();
		salesUnit.sendKeys("Pcs");
		Thread.sleep(1000);
		salesUnit.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(purchaseUnit));
		purchaseUnit.click();
		purchaseUnit.clear();
		purchaseUnit.sendKeys("Dozs");
		Thread.sleep(1000);
		purchaseUnit.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemLasttab));
		itemLasttab.click();
		Thread.sleep(1000);

		attribute0Tab.click();
		Thread.sleep(1000);
		checkBox0.click();
		Thread.sleep(1000);

		attributesDrpDwn.click();

		removetTxt(attributesDrpDwn);

		Thread.sleep(2500);

		attributesDrpDwn.sendKeys(Keys.TAB);

		Thread.sleep(2500);
		clickOn(attributesPopUp_OkBtn);

		String expMessage = "You have not selected any attribute from the List. Please Select attributes to Display";
		String actmessage = checkValidationMessage(expMessage);

		Thread.sleep(2500);

		String actList = listOfElements(attributesPopUpList);
		String expList = "[Apple, Banana, Grapes, Orange]";

		System.err.println(" ACT :" + actList);
		System.err.println(" exp :" + expList);

		clickOn(attributesPopUpSelectAllChkbox);

		Thread.sleep(2500);
		
		clickOn(attributesPopUp_OkBtn);

		Thread.sleep(10000);

		
		
		String actList1 = listOfElements(itemMasterAttributesList);

		String expList1 = "[Apple, Banana, Grapes, Orange]";

		System.err.println(" ACT 1:" + actList1);
		System.err.println(" exp 1:" + expList1);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(saveBtn));
		saveBtn.click();

		String expSaveMessage = "Saved Successfully";
		String actSaveMessage = checkValidationMessage(expSaveMessage);

		Thread.sleep(2000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(closeBtnforAttributes));
		closeBtnforAttributes.click();

		Thread.sleep(1000);

		getDriver().navigate().refresh();
		
		Thread.sleep(1000);

		if (actmessage.equalsIgnoreCase(expMessage) && actList.equalsIgnoreCase(expList)
		 && actList1.equalsIgnoreCase(expList1)  && actSaveMessage.equalsIgnoreCase(expSaveMessage)) {
			System.err.println(" Test Fail: Saved With Popp up Attribues ");
			return true;
		} else {

			System.err.println(" Test Fail: Saved With Popp up Attribues ");
			return false;
		}

	}

	@FindBy(xpath = "//*[@id='id_AttributeName0']")
	public static WebElement attributesDrpDwn;

	@FindBy(xpath = "//*[@id='prodAttrModalBody']/div/table/tbody/tr/td/label")
	public static List<WebElement> attributesPopUpList;
	
	
	
	
	//

	@FindBy(xpath = "(//*[@id='tblProductAttributesBody'])[1]//tr//td[4]")
	public static List<WebElement> itemMasterAttributesList;
	
	
	

	@FindBy(xpath = "//*[@id='selectProdAttr']/following-sibling::span")
	public static WebElement attributesPopUpSelectAllChkbox;

	@FindBy(xpath = "//*[@id='divProductAttributesModal']/div[2]/div/div[3]/div/button[1]")
	public static WebElement attributesPopUp_OkBtn;

	// External Modules

	public boolean checkAddingExternalmoduleScreen()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {

		logout();

		Thread.sleep(2000);
		checkLoginTOBRS();

		System.err.println("Completed LOGIN");

		Thread.sleep(2000);

		ClickUsingJs(settingsMenu);

		Thread.sleep(2000);
		clickOn(Setting_Perference);

		Thread.sleep(2000);
		ClickUsingJs(extMODMenu);

		Thread.sleep(3500);

		Select s1 = new Select(extMOD_OnEvntMthdDrpDwn);

		List<WebElement> elementCount = s1.getOptions();
		ArrayList<String> list1 = new ArrayList<>();

		int i;

		for (i = 0; i < elementCount.size(); i++) {

			elementCount.get(i).getText();

			String optionName = elementCount.get(i).getText();
			list1.add(optionName);
			if (optionName.equalsIgnoreCase("On Menu")) {
				System.out.println("optionName" + elementCount.get(i).getText());
				elementCount.get(i).click();
			}

		}

		String actEventDrpdwn = list1.toString();
		String expEventDrpdwn = "[, On Menu, Year End process, Initialize Company, Delete Company, Backup, Restore, Login, Logout, Create Company, Save Masters, External Variables, Alt+F1, Alt+F2, Alt+F3, Alt+F4, Alt+F5, Alt+F6, Alt+F7, Alt+F8, Alt+F9, Alt+F10, On Add Tab, On Authorize, On Reject, Sample Quantity]";

		System.err.println(" ACT EventDrpdwn List:" + actEventDrpdwn);
		System.err.println(" Exp EventDrpdwn List:" + expEventDrpdwn);

		int count3 = extMOD_MenuPopUpList.size();

		ArrayList<String> list123 = new ArrayList<>();

		for (int j = 0; j < count3; j++) {
			String data = extMOD_MenuPopUpList.get(j).getText();

			if (data.isEmpty() == false) {
				list123.add(data);
			}

			if (data.equalsIgnoreCase("HOME")) {
				extMOD_MenuPopUpList.get(j).click();

			}
		}

		String actPopup = list123.toString();
		String expPopup = "[Home, Financials, Inventory, Fixed Asset, Production, Point of Sale, Quality Control, Settings]";

		System.err.println("actPopup : " + actPopup);
		System.err.println("expPopup : " + expPopup);

		Thread.sleep(2000);

		clickOn(extMOD_PopupOkbtn);

		Thread.sleep(3500);

		Select s2 = new Select(extMOD_ModuleTypeDepDwn);

		List<WebElement> elementCount1 = s2.getOptions();
		ArrayList<String> list2 = new ArrayList<>();

		int j;

		for (j = 0; j < elementCount1.size(); j++) {

			elementCount1.get(j).getText();

			String optionName = elementCount1.get(j).getText();
			list2.add(optionName);
			if (optionName.equalsIgnoreCase("URL")) {
				System.out.println("ModuleTypeDepDwn" + elementCount1.get(j).getText());
				elementCount1.get(j).click();
			}

		}

		String actModuleTypeDepDwn = list2.toString();
		String expModuleTypeDepDwn = "[, DLL, Web Service, URL, Forms]";

		System.err.println(" ACT ModuleTypeDepDwn List:" + actModuleTypeDepDwn);
		System.err.println(" Exp ModuleTypeDepDwn List:" + expModuleTypeDepDwn);

		Thread.sleep(2000);

		clickOn(extMOD_ModuleNametxt);
		extMOD_ModuleNametxt.sendKeys("http://localhost/focusx#");
		Thread.sleep(2000);
		extMOD_ModuleNametxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		clickOn(extMOD_FunctionNameTxt);
		extMOD_FunctionNameTxt.sendKeys("HomeFunction");
		Thread.sleep(2000);
		extMOD_FunctionNameTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		clickOn(extMOD_HomeMenu);
		extMOD_HomeMenu.sendKeys("HomeMenu");
		Thread.sleep(2000);
		extMOD_HomeMenu.sendKeys(Keys.TAB);

		clickOn(extMOD_InsertBtn);

		Thread.sleep(2000);

		int sum = extMOD_GridRow1List.size();

		ArrayList<String> RowList = new ArrayList<>();

		for (int k = 0; k < sum; k++) {
			String data = extMOD_GridRow1List.get(k).getText();

			if (data.isEmpty() == false) {
				RowList.add(data);
			}

		}

		String actRowList = RowList.toString();
		String expRowList = "[On Menu, URL, http://localhost/focusx#, HomeFunction, Home, HomeMenu]";

		System.err.println(" ACT RowList1 : " + actRowList);
		System.err.println(" EXP RowList1 : " + expRowList);

		Thread.sleep(2000);
		clickOn(settingUpdateIcon);

		getWaitForAlert();

		String actAlert = getAlert().getText();
		String expAlert = "Do you want to save the changes?";

		System.err.println(" Act Alert: " + actAlert);
		System.err.println(" exp Alert: " + expAlert);

		Thread.sleep(2000);
		getAlert().accept();

		String expMessage = "Data saved Successfully";
		String actMessage = checkValidationMessage(expMessage);

		clickOn(closeBtn);

		if (actEventDrpdwn.equalsIgnoreCase(expEventDrpdwn) && actModuleTypeDepDwn.equalsIgnoreCase(expModuleTypeDepDwn)
				&& actRowList.equalsIgnoreCase(expRowList) && actAlert.equalsIgnoreCase(expAlert)
				&& actMessage.equalsIgnoreCase(expMessage)) {
			return true;
		} else {
			return false;

		}
	}

	public boolean checkEditingInExternalModules() throws InterruptedException {

		getDriver().navigate().refresh();

		Thread.sleep(2000);

		ClickUsingJs(settingsMenu);

		clickOn(Setting_Perference);

		Thread.sleep(2000);
		ClickUsingJs(extMODMenu);

		Thread.sleep(2000);
		clickOn(extMOD_GridRow1Chkbox);

		Select s1 = new Select(extMOD_OnEvntMthdDrpDwn);
		String actEvent = s1.getFirstSelectedOption().getText();
		String expEvent = "On Menu";

		Select s2 = new Select(extMOD_ModuleTypeDepDwn);
		String actModuleType = s2.getFirstSelectedOption().getText();
		String expModuleType = "URL";

		System.err.println(" ModuleType Drpdwn Displayed: " + actModuleType + " Value Exp: " + expModuleType);

		String actextMOD_ModuleNametxt = extMOD_ModuleNametxt.getAttribute("value");
		String expextMOD_ModuleNametxt = "http://localhost/focusx#";

		Thread.sleep(2000);

		String actextMOD_FunctionNameTxt = extMOD_FunctionNameTxt.getAttribute("value");
		String expextMOD_FunctionNameTxt = "HomeFunction";

		Thread.sleep(2000);

		String actextMOD_CaptionTxt = extMOD_CaptionTxt.getAttribute("value");
		String expextMOD_CaptionTxt = "Home";

		Thread.sleep(2000);

		String actextMOD_HomeMenu = extMOD_HomeMenu.getAttribute("value");
		String expextMOD_HomeMenu = "HomeMenu";

		System.err.println(" EVENT Drpdwn Displayed           : " + actEvent + " Value Exp: " + expEvent);
		System.err.println(" extMOD_ModuleNametxt Displayed   : " + actextMOD_ModuleNametxt + " Value Exp: "
				+ expextMOD_ModuleNametxt);
		System.err.println(" extMOD_FunctionNameTxt Displayed : " + actextMOD_FunctionNameTxt + " Value Exp: "
				+ expextMOD_FunctionNameTxt);
		System.err.println(
				" extMOD_CaptionTxt Displayed      : " + actextMOD_CaptionTxt + " Value Exp: " + expextMOD_CaptionTxt);
		System.err.println(
				" extMOD_HomeMenu Displayed        : " + actextMOD_HomeMenu + " Value Exp: " + expextMOD_HomeMenu);

		Thread.sleep(2000);

		int sum = extMOD_GridRow1List.size();

		ArrayList<String> RowList = new ArrayList<>();

		for (int k = 0; k < sum; k++) {
			String data = extMOD_GridRow1List.get(k).getText();

			if (data.isEmpty() == false) {
				RowList.add(data);
			}
		}

		Thread.sleep(2000);

		String actRowList = RowList.toString();
		String expRowList = "[On Menu, URL, http://localhost/focusx#, HomeFunction, Home, HomeMenu]";

		System.err.println(" ACT RowList1 : " + actRowList);
		System.err.println(" EXP RowList1 : " + expRowList);

		if (actRowList.equalsIgnoreCase(expRowList) && actEvent.equalsIgnoreCase(expEvent)
				&& actModuleType.equalsIgnoreCase(expModuleType)
				&& actextMOD_FunctionNameTxt.equalsIgnoreCase(expextMOD_FunctionNameTxt)
				&& actextMOD_ModuleNametxt.equalsIgnoreCase(expextMOD_ModuleNametxt)
				&& actextMOD_HomeMenu.equalsIgnoreCase(expextMOD_HomeMenu)
				&& actextMOD_CaptionTxt.equalsIgnoreCase(expextMOD_CaptionTxt)) {

			return true;
		} else {

			return false;
		}

	}

	public boolean checkInsertingTwoRowsInExternalmodules()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		ClickUsingJs(settingsMenu);

		Thread.sleep(2000);
		clickOn(Setting_Perference);

		Thread.sleep(2000);
		ClickUsingJs(extMODMenu);

		Thread.sleep(2000);

		Select s1 = new Select(extMOD_OnEvntMthdDrpDwn);

		List<WebElement> elementCount = s1.getOptions();

		int i;

		for (i = 0; i < elementCount.size(); i++) {

			String optionName = elementCount.get(i).getText();

			if (optionName.equalsIgnoreCase("On Menu")) {
				elementCount.get(i).click();

			}

		}

		int count3 = extMOD_MenuPopUpList.size();

		for (int j = 0; j < count3; j++) {
			String data = extMOD_MenuPopUpList.get(j).getText();

			if (data.equalsIgnoreCase("Inventory")) {
				extMOD_MenuPopUpList.get(j).click();
				Thread.sleep(2000);

				extMOD_MenuPopUpExpBtnList.get(j).click();

			}
		}

		clickOn(extMOD_PopupInvTransMenu);

		Thread.sleep(2000);

		clickOn(extMOD_PopupOkbtn);

		Thread.sleep(3500);

		Select s2 = new Select(extMOD_ModuleTypeDepDwn);

		List<WebElement> elementCount1 = s2.getOptions();
		int j;

		for (j = 0; j < elementCount1.size(); j++) {

			String optionName = elementCount1.get(j).getText();

			if (optionName.equalsIgnoreCase("URL")) {
				elementCount1.get(j).click();
			}

		}

		Thread.sleep(2000);
		clickOn(extMOD_ModuleNametxt);
		extMOD_ModuleNametxt.sendKeys("http://localhost/focusx#");
		Thread.sleep(2000);
		extMOD_ModuleNametxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		clickOn(extMOD_FunctionNameTxt);
		extMOD_FunctionNameTxt.sendKeys("InvTransFun");
		Thread.sleep(2000);
		extMOD_FunctionNameTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		clickOn(extMOD_HomeMenu);
		extMOD_HomeMenu.sendKeys("TransFunction");
		Thread.sleep(2000);
		extMOD_HomeMenu.sendKeys(Keys.TAB);

		clickOn(extMOD_InsertBtn);

		Thread.sleep(2300);

		Select s22 = new Select(extMOD_OnEvntMthdDrpDwn);

		List<WebElement> elementCount123 = s22.getOptions();

		int a;

		for (a = 0; a < elementCount123.size(); a++) {

			String optionName = elementCount123.get(a).getText();

			if (optionName.equalsIgnoreCase("On Menu")) {
				elementCount123.get(a).click();

			}

		}

		int count323 = extMOD_MenuPopUpList.size();

		for (int b = 0; b < count323; b++) {
			String data = extMOD_MenuPopUpList.get(b).getText();

			if (data.equalsIgnoreCase("Fixed Asset")) {
				extMOD_MenuPopUpList.get(b).click();
			}
		}

		clickOn(extMOD_PopupOkbtn);

		Thread.sleep(3500);

		Select s223 = new Select(extMOD_ModuleTypeDepDwn);

		List<WebElement> elementCount456 = s223.getOptions();
		int c;

		for (c = 0; c < elementCount456.size(); c++) {

			String optionName = elementCount456.get(c).getText();

			if (optionName.equalsIgnoreCase("URL")) {
				elementCount456.get(c).click();
			}

		}

		Thread.sleep(2000);

		clickOn(extMOD_ModuleNametxt);
		extMOD_ModuleNametxt.sendKeys("http://localhost/focusx#");
		Thread.sleep(2000);
		extMOD_ModuleNametxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		clickOn(extMOD_FunctionNameTxt);
		extMOD_FunctionNameTxt.sendKeys("FixedAssetsFun");
		Thread.sleep(2000);
		extMOD_FunctionNameTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		clickOn(extMOD_HomeMenu);
		extMOD_HomeMenu.sendKeys("FixedAssestMenu");
		Thread.sleep(2000);
		extMOD_HomeMenu.sendKeys(Keys.TAB);

		clickOn(extMOD_InsertBtn);

		Thread.sleep(2000);

		int sum = extMOD_GridRow2List.size();

		ArrayList<String> RowList = new ArrayList<>();

		for (int k = 0; k < sum; k++) {
			String data = extMOD_GridRow2List.get(k).getText();

			if (data.isEmpty() == false) {
				RowList.add(data);
			}

		}

		String actRowList = RowList.toString();
		String expRowList = "[On Menu, URL, http://localhost/focusx#, InvTransFun, Transactions, TransFunction]";

		System.err.println(" ACT RowList2 : " + actRowList);
		System.err.println(" EXP RowList2 : " + expRowList);

		int sum1 = extMOD_GridRow3List.size();

		ArrayList<String> RowList1 = new ArrayList<>();

		for (int k = 0; k < sum1; k++) {
			String data1 = extMOD_GridRow3List.get(k).getText();

			if (data1.isEmpty() == false) {
				RowList1.add(data1);
			}

		}

		String actRowList1 = RowList1.toString();
		String expRowList1 = "[On Menu, URL, http://localhost/focusx#, FixedAssetsFun, Fixed Asset, FixedAssestMenu]";

		System.err.println(" ACT RowList3 : " + actRowList1);
		System.err.println(" EXP RowList3 : " + expRowList1);

		Thread.sleep(2000);
		clickOn(settingUpdateIcon);

		getWaitForAlert();

		String actAlert = getAlert().getText();
		String expAlert = "Do you want to save the changes?";

		System.err.println(" Act Alert: " + actAlert);
		System.err.println(" exp Alert: " + expAlert);

		Thread.sleep(2000);
		getAlert().accept();

		String expMessage = "Data saved Successfully";
		String actMessage = checkValidationMessage(expMessage);

		clickOn(closeBtn);

		if (actRowList.equalsIgnoreCase(expRowList) && actAlert.equalsIgnoreCase(expAlert)
				&& actMessage.equalsIgnoreCase(expMessage)) {
			return true;
		} else {
			return false;

		}

	}

	public boolean checkSavedExternalModuleWithThreeRowsAndRemove()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		getDriver().navigate().refresh();

		Thread.sleep(2000);

		ClickUsingJs(settingsMenu);

		Thread.sleep(2000);
		clickOn(Setting_Perference);

		Thread.sleep(2000);
		ClickUsingJs(extMODMenu);

		Thread.sleep(2000);

		int sum = extMOD_GridRow1List.size();

		ArrayList<String> RowList = new ArrayList<>();

		for (int k = 0; k < sum; k++) {
			String data = extMOD_GridRow1List.get(k).getText();

			if (data.isEmpty() == false) {
				RowList.add(data);
			}
		}

		Thread.sleep(2000);

		String actRow1List = RowList.toString();
		String expRow1List = "[On Menu, URL, http://localhost/focusx#, HomeFunction, Home, HomeMenu]";

		System.err.println(" ACT RowList1 : " + actRow1List);
		System.err.println(" EXP RowList1 : " + expRow1List);

		Thread.sleep(2000);

		int sum1 = extMOD_GridRow2List.size();

		ArrayList<String> Row2List = new ArrayList<>();

		for (int k = 0; k < sum1; k++) {
			String data = extMOD_GridRow2List.get(k).getText();

			if (data.isEmpty() == false) {
				Row2List.add(data);
			}

		}

		String actRow2List = Row2List.toString();
		String expRow2List = "[On Menu, URL, http://localhost/focusx#, InvTransFun, Transactions, TransFunction]";

		System.err.println(" ACT RowList2 : " + actRow2List);
		System.err.println(" EXP RowList2 : " + expRow2List);

		int sum3 = extMOD_GridRow3List.size();

		ArrayList<String> Row3List = new ArrayList<>();

		for (int k = 0; k < sum3; k++) {
			String data1 = extMOD_GridRow3List.get(k).getText();

			if (data1.isEmpty() == false) {
				Row3List.add(data1);
			}

		}

		String actRow3List = Row3List.toString();
		String expRow3List = "[On Menu, URL, http://localhost/focusx#, FixedAssetsFun, Fixed Asset, FixedAssestMenu]";

		System.err.println(" ACT RowList3 : " + actRow3List);
		System.err.println(" EXP RowList3 : " + expRow3List);

		clickOn(extMOD_GridRow1Chkbox);

		clickOn(extMOD_RemoveBtn);

		getWaitForAlert();

		String actAlert = getAlert().getText();
		String expAlert = "Do you want to delete the selected module?";

		System.err.println(" Act Alert: " + actAlert);
		System.err.println(" exp Alert: " + expAlert);

		Thread.sleep(2000);
		getAlert().accept();

		Thread.sleep(2000);
		clickOn(settingUpdateIcon);

		getWaitForAlert();

		String actAlert1 = getAlert().getText();
		String expAlert1 = "Do you want to save the changes?";

		System.err.println(" Act Alert1: " + actAlert1);
		System.err.println(" exp Alert1: " + expAlert1);

		Thread.sleep(2000);
		getAlert().accept();
		Thread.sleep(2000);
		String expMessage1 = "Data saved Successfully";
		String actMessage1 = checkValidationMessage(expMessage1);

		clickOn(closeBtn);

		Thread.sleep(2000);

		ClickUsingJs(settingsMenu);

		Thread.sleep(2000);
		clickOn(Setting_Perference);

		Thread.sleep(2000);
		ClickUsingJs(extMODMenu);

		Thread.sleep(2000);

		Thread.sleep(2000);

		int actcountAfterDeletion = extMOD_GridRowCount.size();
		int expcountAfterDeletion = 0;

		System.err.println("countAfterDeletion IMP: " + actcountAfterDeletion + " Value EXP: " + expcountAfterDeletion);

		Thread.sleep(2000);
		getDriver().navigate().refresh();
		Thread.sleep(2000);

		logout();

		if (actRow1List.equalsIgnoreCase(expRow1List) && actAlert.equalsIgnoreCase(expAlert)
				&& actRow2List.equalsIgnoreCase(expRow2List) && actRow3List.equalsIgnoreCase(expRow3List)
				&& actcountAfterDeletion == expcountAfterDeletion && actAlert1.equalsIgnoreCase(expAlert1)
				&& actMessage1.equalsIgnoreCase(expMessage1)) {

			System.err.println(" Test Pass: External Module Deletion Process ");
			return true;
		} else {
			System.err.println(" Test FAIL: External Module Deletion Process ");
			return false;

		}

	}
	
	
	
	
	@FindBy(xpath = "//tbody[@id='id_header_4_table_body']/tr/td[2]")
	public static List<WebElement> customerAccountComboList;

	
	

	public boolean checkEnableOptionWithSalesinvoiceReorderWarnAndAllow() throws InterruptedException, IOException, AWTException, EncryptedDocumentException, InvalidFormatException
	{

		checkLoginTOBRS();

		//checkRestoreOptionsCompanyAndLogin("BRS reorderLevel", "BRS");

		Thread.sleep(4569);

		waitOn(homeMenu);

		Thread.sleep(2000);

		clickOn(financialsMenu);

		clickOn(financialsTransactionMenu);

		clickOn(financialTransactionSalesMenu);

		clickOn(salesInvoiceVATVoucher);
		Thread.sleep(4000);
		
		click(newBtn);

		// //checkUserFriendlyMessage();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customerAccountTxt));
		clickOn(customerAccountTxt);
		customerAccountTxt.sendKeys(Keys.END);
		customerAccountTxt.sendKeys(Keys.SHIFT, Keys.HOME);
		customerAccountTxt.sendKeys(Keys.SPACE);

		int customerAccountCount = customerAccountComboList.size();

		System.out.println("Customer Account List Size: " + customerAccountCount);

		for (int i = 0; i < customerAccountCount; i++) {
			String customerAccount = customerAccountComboList.get(i).getText();

			if (customerAccount.equalsIgnoreCase("Customer A")) {
				

				customerAccountComboList.get(i).click();

				customerAccountTxt.sendKeys(Keys.TAB);

				break;
			}
		}
		customerAccountTxt.sendKeys(Keys.TAB);

		clickOn(voucherHeaderCurrency);

		removetTxt(voucherHeaderCurrency);
		voucherHeaderCurrency.sendKeys("INR");
		Thread.sleep(2345);
		voucherHeaderCurrency.sendKeys(Keys.TAB);

		Thread.sleep(2345);
		selectVoucherHeaderDepartmentTxt("INDIA");
		Thread.sleep(2345);

		clickOn(select1stRow_1stColumn);

		pvWarehouseTxt.sendKeys(Keys.END);
		pvWarehouseTxt.sendKeys(Keys.SHIFT, Keys.HOME);
		pvWarehouseTxt.sendKeys(Keys.SPACE);

		int warehouselist = warehouseBodyComboList.size();

		ArrayList<String> actWarehouseOpt = new ArrayList<String>();

		System.out.println("******* Warehouse List: " + warehouselist);

		for (int i = 0; i < warehouselist; i++) {
			String warehouse = warehouseBodyComboList.get(i).getText();

			if (warehouse.equalsIgnoreCase("HYDERABAD")) {
				

				warehouseBodyComboList.get(i).click();

				pvWarehouseTxt.sendKeys(Keys.TAB);

				break;
			}
		}

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_ItemTxt));
		enter_ItemTxt.sendKeys(Keys.END);
		enter_ItemTxt.sendKeys(Keys.SHIFT, Keys.HOME);
		enter_ItemTxt.sendKeys(Keys.SPACE);

		int itemsCount = itemComboList.size();

		for (int i = 0; i < itemsCount; i++) 
		{
			String item = itemComboList.get(i).getText();

			if (item.equalsIgnoreCase("NewItem")) {
				
				itemComboList.get(i).click();

				enter_ItemTxt.sendKeys(Keys.TAB);

				break;
			}
		}

		Thread.sleep(2345);
		clickOn(enterSalesTaxcode);
		enterSalesTaxcode.sendKeys("STD");
		Thread.sleep(2345);
		enterSalesTaxcode.sendKeys(Keys.TAB);

		Thread.sleep(2345);
		removetTxt(enter_AccountTxt);
		enter_AccountTxt.sendKeys("Sales - Electronics");

		enter_AccountTxt.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_5thColumn));
		clickOn(select1stRow_5thColumn);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_8thColumn));
		select1stRow_8thColumn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_AQTxt));
		enter_AQTxt.clear();
		enter_AQTxt.sendKeys(Keys.END);
		enter_AQTxt.sendKeys(Keys.SHIFT, Keys.HOME);
		enter_AQTxt.sendKeys("12");
		enter_AQTxt.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_FQTxt));
		enter_FQTxt.clear();
		enter_FQTxt.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_14thColumn));
		select1stRow_14thColumn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_Rate));
		enter_Rate.sendKeys("10");
		enter_Rate.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_Gross));
		enter_Gross.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNumberTxt));
		String docno = documentNumberTxt.getAttribute("value");

		clickOn(voucherSaveBtn);

		Thread.sleep(2000);

		billwisePick();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNumberTxt));
		
		
		
		String expMessage="This Transaction will make the Stock Negative,This Transaction will make the Stock fall below Re-order level";
		String actMessage=checkValidationMessage(expMessage);
		

		boolean actSaving = checkBackgroundSavingMessage(docno);
		boolean expSaving = true;

		System.out.println(" Final Mesage Displayed  : " + actSaving + " Value Exp : " + expSaving);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNumberTxt));
		String actNewDocNo = documentNumberTxt.getAttribute("value");

		if (actMessage.equalsIgnoreCase(expMessage)) 
		{
			System.out.println(" Test Pass: Message Displayed AS WARN AND ALLOW");
			return true;
		} else {
			System.out.println("Test FAIl: Message Displayed As WARN AND ALLOW");

			return false;
		}

	}
	
	
	
	@FindBy(xpath="//*[@id='ReorderLevelGroup_1']")
	private static WebElement reorder_StopRadioBtn;
	
	
	
	
	public boolean checkEnableStopOptionInReorderUnderPerfernce() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{

		focusMainSearch("Sales invoice VAT");

		Thread.sleep(2345);

		clickOn(entryPageHeaderChkbox);

		clickOn(homepageDeleteBtn);

		getWaitForAlert();

		getAlert().accept();

		checkValidationMessage("");

		Thread.sleep(2345);

		getAction().moveToElement(settingsMenu).build().perform();
		ClickUsingJs(settingsMenu);

		Thread.sleep(2000);
		clickOn(Setting_Perference);

		Thread.sleep(2000);

		ClickUsingJs(InventoryBtn);

		Thread.sleep(2000);
		
		getAction().moveToElement(checkReorderlevelChkBoxSelected).build().perform();
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(checkReorderlevelChkBoxSelected));
		if(checkReorderlevelChkBox.isSelected()==false)
		{
			checkReorderlevelChkBoxSelected.click();
		}

		click(reorder_StopRadioBtn);

		boolean actreorder_StopRadioBtn = reorder_StopRadioBtn.isSelected();
		boolean expreorder_StopRadioBtn = true;

		System.err.println(
				"reorder_StopRadioBtn Status : " + actreorder_StopRadioBtn + "----------" + expreorder_StopRadioBtn);

		Thread.sleep(2000);
		click(settingUpdateIcon);

		getWaitForAlert();

		String actAlert = getAlert().getText();
		String expAlert = "Do you want to save the changes?";

		System.err.println(" Act Alert: " + actAlert);
		System.err.println(" exp Alert: " + expAlert);

		Thread.sleep(2000);
		getAlert().accept();

		String expMessage = "Data saved Successfully";
		String actMessage = checkValidationMessage(expMessage);

		clickOn(closeBtn);

		if (actreorder_StopRadioBtn == expreorder_StopRadioBtn && actAlert.equalsIgnoreCase(expAlert)
				&& actMessage.equalsIgnoreCase(expMessage)) {
			return true;
		} else {

			return false;
		}

	}
	
	
	
	
	public boolean checkSavingSalesInvoiceVARWithEnableOption() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		
		  logout();
		  
		  Thread.sleep(2345);
		 
		checkLoginTOBRS();

		Thread.sleep(2345);

		waitOn(homeMenu);

		Thread.sleep(2000);

		click(financialsMenu);

		click(financialsTransactionMenu);

		click(financialTransactionSalesMenu);

		click(salesInvoiceVATVoucher);
		Thread.sleep(5000);
		click(newBtn);

		// //checkUserFriendlyMessage();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customerAccountTxt));
		click(customerAccountTxt);
		customerAccountTxt.sendKeys(Keys.END);
		customerAccountTxt.sendKeys(Keys.SHIFT, Keys.HOME);
		customerAccountTxt.sendKeys(Keys.SPACE);

		int customerAccountCount = customerAccountComboList.size();

		System.out.println("Customer Account List Size: " + customerAccountCount);

		for (int i = 0; i < customerAccountCount; i++) {
			String customerAccount = customerAccountComboList.get(i).getText();

			if (customerAccount.equalsIgnoreCase("Customer A")) {
				System.out.println("Entered If Loop");

				customerAccountComboList.get(i).click();

				customerAccountTxt.sendKeys(Keys.TAB);

				break;
			}
		}
		customerAccountTxt.sendKeys(Keys.TAB);

		click(voucherHeaderCurrency);

		removetTxt(voucherHeaderCurrency);
		voucherHeaderCurrency.sendKeys("INR");
		Thread.sleep(2345);
		voucherHeaderCurrency.sendKeys(Keys.TAB);

		Thread.sleep(2345);
		selectVoucherHeaderDepartmentTxt("INDIA");
		Thread.sleep(2345);

		click(select1stRow_1stColumn);

		pvWarehouseTxt.sendKeys(Keys.END);
		pvWarehouseTxt.sendKeys(Keys.SHIFT, Keys.HOME);
		pvWarehouseTxt.sendKeys(Keys.SPACE);

		int warehouselist = warehouseBodyComboList.size();

		ArrayList<String> actWarehouseOpt = new ArrayList<String>();

		System.out.println("******* Warehouse List: " + warehouselist);

		for (int i = 0; i < warehouselist; i++) {
			String warehouse = warehouseBodyComboList.get(i).getText();

			if (warehouse.equalsIgnoreCase("HYDERABAD")) {
				System.out.println("Entered If Loop");

				warehouseBodyComboList.get(i).click();

				pvWarehouseTxt.sendKeys(Keys.TAB);

				break;
			}
		}

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_ItemTxt));
		enter_ItemTxt.sendKeys(Keys.END);
		enter_ItemTxt.sendKeys(Keys.SHIFT, Keys.HOME);
		enter_ItemTxt.sendKeys(Keys.SPACE);

		int itemsCount = itemComboList.size();

		for (int i = 0; i < itemsCount; i++) {
			String item = itemComboList.get(i).getText();

			if (item.equalsIgnoreCase("NewItem")) {
				System.out.println("Entered If Loop");

				itemComboList.get(i).click();

				enter_ItemTxt.sendKeys(Keys.TAB);

				break;
			}
		}

		Thread.sleep(2345);
		click(enterSalesTaxcode);
		enterSalesTaxcode.sendKeys("STD");
		Thread.sleep(2345);
		enterSalesTaxcode.sendKeys(Keys.TAB);

		Thread.sleep(2345);
		removetTxt(enter_AccountTxt);
		enter_AccountTxt.sendKeys("Sales - Electronics");

		enter_AccountTxt.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_5thColumn));
		click(select1stRow_5thColumn);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_8thColumn));
		select1stRow_8thColumn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_AQTxt));
		enter_AQTxt.clear();
		enter_AQTxt.sendKeys(Keys.END);
		enter_AQTxt.sendKeys(Keys.SHIFT, Keys.HOME);
		enter_AQTxt.sendKeys("12");
		enter_AQTxt.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_FQTxt));
		enter_FQTxt.clear();
		enter_FQTxt.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_14thColumn));
		select1stRow_14thColumn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_Rate));
		enter_Rate.sendKeys("10");
		enter_Rate.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_Gross));
		enter_Gross.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNumberTxt));
		String docno = documentNumberTxt.getAttribute("value");

		click(voucherSaveBtn);

		Thread.sleep(2000);

		billwisePick();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNumberTxt));

		String expMessage = "This Transaction will make the Stock Negative,This Transaction will make the Stock fall below Re-order level. It cannot be saved";
		
		String actMessage = checkValidationMessage(expMessage);
		String expMessage1="This Transaction will make the Stock fall below Re-order level";
		
		String actMessage1 = checkValidationMessage(expMessage1);

		String actList = listOfElements(entryPageRow1List);
		String expList = "[1, HYDERABAD, NewItem, Std Rate, Sales - Electronics, 150.00, 150.00, 12.00, 0.00, 0, 12.00, 0.00, 10.00, 120.00, 0.00, 0.00, 120.00]";

		System.err.println(" Row 1List Act: " + actList);
		System.err.println(" Row 1List EXP: " + expList);

		Thread.sleep(2345);
		getDriver().navigate().refresh();

		Thread.sleep(2345);
		
		logout();

		if ((actMessage.equalsIgnoreCase(expMessage) && actMessage1.equalsIgnoreCase(expMessage1))
				&& actList.equalsIgnoreCase(expList))
		{
			System.out.println(" Test Pass: Message Displayed AS stop");
			return true;
		} else {
			System.out.println("Test FAIl: Message Displayed As STOP");

			return false;
		}

	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public AttributePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

}
