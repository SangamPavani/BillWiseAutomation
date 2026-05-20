package com.focus.Pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.focus.base.BaseEngine;

public class VoucherOptionsPage extends BaseEngine {

	@FindBy(xpath = "//*[@id='txtbox_doc_Accountdepandency1']")
	private static WebElement doc_Accountdepandencytxt;

	@FindBy(xpath = "//*[@id='txtbox_doc_Productdepandency']")
	private static WebElement doc_ItemdepandencyTxt;

	@FindBy(xpath = "//*[@id='doc_Accountdepandency_Filter1']")
	private static WebElement doc_AccFilterDrpdwn;

	@FindBy(xpath = "//*[@id='doc_Itemdepandency_Filter']")
	private static WebElement doc_ItemFilterDrpdwn;

	@FindBy(xpath = "//*[@id='txtbox_doc_TagsDepedencyDropDown']")
	private static WebElement EntershowDependyRow;

	@FindBy(xpath = "//*[@id='doc_TagsTable_col_1-3']")
	private static WebElement showDependyRow1;

	@FindBy(xpath = "//*[@id='doc_TagsTable_col_1-4']")
	private static WebElement groupRow1;

	@FindBy(xpath = "//*[@id='doc_TagsGroupTxtbox']")
	private static WebElement enterGroupRow;

	@FindBy(xpath = "//*[@id='doc_TagsTable_col_1-5']")
	private static WebElement filterRow1;

	@FindBy(xpath = "//*[@id='docFilter']")
	private static WebElement enterFilterRow;

	@FindBy(xpath = "//*[@id='doc_TagsTable_col_1-6']")
	private static WebElement mandatoryRow1;

	@FindBy(xpath = "//*[@id='doc_MandatoryDropDown']")
	private static WebElement enterMandatoryDrpDwn;

	public boolean checkDocumentTABUnderVoucherWizard() throws InterruptedException, IOException, AWTException {
		Thread.sleep(2000);

		//waitOn(settingsMenu);
		ClickUsingJs(settingsMenu);
		

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(settingsConfigureTransactions));
		settingsConfigureTransactions.click();

		Thread.sleep(2000);

		highlightElement(settingCong_DocCustimeBtn);
		Thread.sleep(2000);
		click(settingCong_DocCustimeBtn);

		Thread.sleep(8989);

		ClickUsingJs(doccus_SalesInvoiceVATBtn);
		Thread.sleep(3000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(titleTxt));

		ScrollToElement(titleTxt);
		String actTitle = titleTxt.getAttribute("value");
		String expTitle = "Sales invoice VAT";

		System.out.println(" Title Displayed :" + actTitle + "Value Expec: " + expTitle);

		if (actTitle.equalsIgnoreCase(expTitle)) {
			System.out.println(" Landed in Sales Invoice  VAT In Voucher Wizard");
			return true;
		} else {
			System.err.println(" NOTTTT  Landed in Sales Invoice  VAT In Voucher Wizard");
			return false;
		}
	}

	public boolean checkDisplayStatusOFDocumentTABInVoucherWizard() throws InterruptedException {

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(doc_Accountdepandencytxt));
		boolean actdoc_Accountdepandencytxt = doc_Accountdepandencytxt.isDisplayed();
		boolean actdoc_ItemdepandencyTxt = doc_ItemdepandencyTxt.isDisplayed();
		boolean actdoc_AccFilterDrpdwn = doc_AccFilterDrpdwn.isDisplayed();
		boolean actdoc_ItemFilterDrpdwn = doc_ItemFilterDrpdwn.isDisplayed();

		boolean expdoc_Accountdepandencytxt = true;
		boolean expdoc_ItemdepandencyTxt = true;
		boolean expdoc_AccFilterDrpdwn = true;
		boolean expdoc_ItemFilterDrpdwn = true;

		System.out.println(" doc_Accountdepandencytxt :" + actdoc_Accountdepandencytxt + "*-*-*-*-*-*-*-"
				+ expdoc_Accountdepandencytxt);
		System.out.println(
				" doc_ItemdepandencyTxt    :" + actdoc_ItemdepandencyTxt + "*-*-*-*-*-*-*-" + expdoc_ItemdepandencyTxt);
		System.out.println(
				" doc_AccFilterDrpdwn      :" + expdoc_AccFilterDrpdwn + "*-*-*-*-*-*-*-" + expdoc_AccFilterDrpdwn);
		System.out.println(
				" doc_ItemFilterDrpdwn     :" + actdoc_ItemFilterDrpdwn + "*-*-*-*-*-*-*-" + expdoc_ItemFilterDrpdwn);

		if (actdoc_Accountdepandencytxt == expdoc_Accountdepandencytxt
				&& actdoc_ItemdepandencyTxt == expdoc_ItemdepandencyTxt
				&& actdoc_AccFilterDrpdwn == expdoc_AccFilterDrpdwn
				&& actdoc_ItemFilterDrpdwn == expdoc_ItemFilterDrpdwn) {
			return true;

		} else {
			return false;
		}
	}

	@FindBy(xpath = "//*[@id='ul_doc_Accountdepandency1']/li/div/label/span")
	private static List<WebElement> doc_accountDepList;

	@FindBy(xpath = "//*[@id='ul_doc_Accountdepandency1']/li/div/label/input")
	private static List<WebElement> doc_accountDepCheckBoxList;

	@FindBy(xpath = "//*[@id='ul_doc_Productdepandency']/li//label/span[1]")
	private static List<WebElement> doc_ItemDepList;

	// @FindBy(xpath="//*[@id='ul_doc_Productdepandency']/li/div/label/input")

	@FindBy(xpath = "//*[@id='ul_doc_Productdepandency']/li//label/span[2]")
	private static List<WebElement> doc_ItemDepCheckBoxList;

	public static boolean checkLogin() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		Thread.sleep(2333);
		getDriver().navigate().refresh();

		Thread.sleep(2333);

		LoginPage lp = new LoginPage(getDriver());

		lp.enterUserName("su");

		lp.enterPassword("su");

		String compname = "Voucher Options Company";

		Select oSelect = new Select(companyDropDownList);

		List<WebElement> elementCount = oSelect.getOptions();

		int cqSize = elementCount.size();

		System.out.println("CompanyDropdownList Count :" + cqSize);

		int i;

		for (i = 0; i < elementCount.size(); i++) {

			elementCount.get(i).getText();

			String optionName = elementCount.get(i).getText();
			if (optionName.toUpperCase().startsWith(compname.toUpperCase())) {
				System.out.println("Logined Company" + elementCount.get(i).getText());
				elementCount.get(i).click();
			}

		}

		Thread.sleep(2000);

		lp.clickOnSignInBtn();

		Thread.sleep(10000);

		if (homeMenu.isDisplayed()) 
		{
			System.out.println("Test Pass :Logined to Billwise Company");
			return true;

		} else {
			System.out.println("Test Fail :Logined to Billwise Company");
			return false;

		}
	}

	public void checkRestoreCompany() throws InterruptedException, AWTException, EncryptedDocumentException, InvalidFormatException, IOException 
	{

		checkRestoreOptionsCompanyAndLogin("Voucher Options Company", "Voucher Options Company");
		
		
		/*

		Thread.sleep(1000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(homeMenu));
		ClickUsingJs(homeMenu);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dataMangementMenu));
		dataMangementMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(restore));
		restore.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(folderpathExpandBtn));
		folderpathExpandBtn.click();

		Thread.sleep(5000);

		Robot rb = new Robot();
		StringSelection str = new StringSelection(getBaseDir() + "\\requiredBackUps\\BillWise.fbak");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);

		rb.keyPress(KeyEvent.VK_CONTROL);
		rb.keyPress(KeyEvent.VK_V);

		rb.keyRelease(KeyEvent.VK_CONTROL);
		rb.keyRelease(KeyEvent.VK_V);

		rb.keyPress(KeyEvent.VK_ENTER);
		rb.keyRelease(KeyEvent.VK_ENTER);

		Thread.sleep(15000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(restoreCompanyBtn));
		restoreCompanyBtn.click();

		Thread.sleep(3000);

		try {
			getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(overRideYesBtn));
			overRideYesBtn.click();

			System.err.println("COMPANY EXISTS");

			Thread.sleep(280000);

		} catch (Exception e) {
			System.err.println("NO OLDER COMPANY EXISTS");
		}

		if (getIsAlertPresent()) {
			System.err.println("Alert Displayed");
			getWaitForAlert();

			getAlert().accept();
		}

		Thread.sleep(3000);

		click(userNameDisplayLogo);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(logoutOption));
		logoutOption.click();

		Thread.sleep(3000);

		checkLoginToSelectedCompany("Billwise", "su", "su");

		Thread.sleep(2500);
		
		reindexClear();
		
		*/


	}

	public boolean checkAccountDependencyValues() throws InterruptedException {
		Thread.sleep(2000);

		click(doc_Accountdepandencytxt);

		Thread.sleep(2500);

		int count = doc_accountDepList.size();
		ArrayList<String> list = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			String data = doc_accountDepList.get(i).getText();
			list.add(data);
		}
		String actAccList = list.toString();
		String expAccList = "[TreeTab__, , PlaceOfSupply, , iAcceptanceac, , iExchangeAdjustmentGainAC, , iExchangeAdjustmentLossAC, , iPrimaryAccount, , iCity, , iDeliveryCity, , iBankAc, , iPDCDiscountedAC, , iPortalRights, ]";
		System.out.println(" ACT Account List :" + actAccList);
		System.out.println(" Exp Account List :" + expAccList);

		if (actAccList.equalsIgnoreCase(expAccList)) {
			return true;
		} else {

			return false;
		}
	}

	public boolean checkAccountDependencyAndClickOnUpdate()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException 
	{
		Thread.sleep(4500);

		waitForElement(titleTxt);
		
		ClickUsingJs(titleTxt);

		String actdoc_Accountdepandencytxt = doc_Accountdepandencytxt.getAttribute("value");
		String expdoc_Accountdepandencytxt = "PlaceOfSupply";

		System.out.println("doc_Accountdepandencytxt Text : " + actdoc_Accountdepandencytxt + "*-*-*-*-*-"
				+ expdoc_Accountdepandencytxt);

		Thread.sleep(2500);

		ClickUsingJs(settingUpdateIcon);

		String expMessage1 = "Data saved successfully";

		String actMessage = checkValidationMessage(expMessage1);
		
		
		clickOn(settings_closeBtn);
		
		
	/*	logout();
		

		Thread.sleep(3000);
		
		prongHornStartAtAdminLevel();
		
		Thread.sleep(3000);
		
		
		//checkLoginToSelectedCompany("BillWise", "su", "su");
		
		//Thread.sleep(3000);
		
		checkLogin();*/
		Thread.sleep(4000);
		if (actMessage.equalsIgnoreCase(expMessage1)
				&& actdoc_Accountdepandencytxt.equalsIgnoreCase(expdoc_Accountdepandencytxt)) {
			
			return true;
		} else {
			return false;
		}
	}

	public boolean checkAssgingPalceOfSupplyInAccountMaster()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		
		
	/*	
		prongHornStopAtAdminLevel();
		
		
		logout();
		
		Thread.sleep(2345);
		
		prongHornStartAtAdminLevel();
		Thread.sleep(2345);
		
		checkLogin();
		
		Thread.sleep(2345);*/

		Thread.sleep(2000);

		ClickUsingJs(homeMenu);

		Thread.sleep(2000);
		click(mastersMenu);

		Thread.sleep(2000);
		click(accounts);

		Thread.sleep(2000);

		click(cusNewRefMasterChkBOx);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(masterEditBtn));
		masterEditBtn.click();

		Thread.sleep(2000);

		click(AccMaster_VATSettingTAB);

		Thread.sleep(2000);

		click(AccMaster_VATSettingPlaceOfSupplyTxt);
		AccMaster_VATSettingPlaceOfSupplyTxt.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME);
		AccMaster_VATSettingPlaceOfSupplyTxt.sendKeys("Germany");

		Thread.sleep(2000);

		AccMaster_VATSettingPlaceOfSupplyTxt.sendKeys(Keys.TAB);

		String actText = AccMaster_VATSettingPlaceOfSupplyTxt.getAttribute("value");
		String expText = "Germany";

		System.out.println(" Text Displayed: " + actText + "*-*-*-*-*" + expText);

		click(mastersaveBtn);

		String expMessage1 = "Updated Successfully";

		String actMessage = checkValidationMessage(expMessage1);

		if (actMessage.equalsIgnoreCase(expMessage1) && actText.equalsIgnoreCase(expText)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkAccountDependencyAtSalesInvoiceVATAfterAssigningInAxccountMaster()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {

		click(financialsMenu);

		click(financialsTransactionMenu);

		click(financialTransactionSalesMenu);

		Thread.sleep(2000);
		click(salesInvoiceVATVoucher);

		Thread.sleep(8000);

		click(newBtn);

		Thread.sleep(2999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customerAccountTxt));
		customerAccountTxt.click();
		customerAccountTxt.sendKeys(Keys.SPACE);

		selectionElementFromList(customerAccountListCount, "Customer New Reference");

		Thread.sleep(1000);

		customerAccountTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		String actplaceOFSupplyTxt = placeOFSupplyTxt.getAttribute("value");
		String expplaceOFSupplyTxt = "Germany";

		System.err.println(" placeOFSupplyTxt : " + actplaceOFSupplyTxt + "******" + expplaceOFSupplyTxt);

		Thread.sleep(2999);

		click(customerAccountTxt);
		removetTxt(customerAccountTxt);
		customerAccountTxt.sendKeys(Keys.SPACE);

		selectionElementFromList(customerAccountListCount, "Customer A");

		Thread.sleep(1000);

		customerAccountTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		String actplaceOFSupplyTxtWithOut = placeOFSupplyTxt.getAttribute("value");
		String expplaceOFSupplyTxtWithOut = "";// Expected Displays as Empty

		System.err.println("PlaceOFSupplyTxt WithOut --Should display empty : " + actplaceOFSupplyTxtWithOut + "******"
				+ expplaceOFSupplyTxtWithOut);

		Thread.sleep(2345);
		getDriver().navigate().refresh();
		Thread.sleep(2345);
		
		if (actplaceOFSupplyTxt.equalsIgnoreCase(expplaceOFSupplyTxt)
				&& actplaceOFSupplyTxtWithOut.equalsIgnoreCase(expplaceOFSupplyTxtWithOut)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkItemDependencyListUnderDocumentTAB() throws InterruptedException {

		Thread.sleep(2000);

		ClickUsingJs(settingsMenu);

		click(settingsConfigureTransactions);

		Thread.sleep(2000);

		highlightElement(settingCong_DocCustimeBtn);
		Thread.sleep(2000);
		click(settingCong_DocCustimeBtn);

		Thread.sleep(2000);
		click(doccus_SalesInvoiceVATBtn);

		Thread.sleep(2000);
		click(doc_ItemdepandencyTxt);

		Thread.sleep(2000);

		String actAccList = listOfElements(doc_ItemDepList);
		String expAccList = "[Outlet__, iCostofShortageStockAC, iCostofExcessStockAC, iCostofSaleReturnAC, iPurchaseVarianceAC, VATExpenseAccount, iDefaultBaseUnit, iDefaultSalesUnit, iDefaultPurchaseUnit, iCostOfIssueAccount, iStocksAccount, iSalesAccount, OtherDetails__, Replenishment__, Reorder__, iCategory, iWIPAccount, Classification__, Replenishment__, OtherDetails__, iBin, iAlternateCategory, iTaxCode, ExtraFieldOne]";

		System.out.println(" ACT Account List :" + actAccList);
		System.out.println(" Exp Account List :" + expAccList);
		
		getDriver().navigate().refresh();

		if (actAccList.equalsIgnoreCase(expAccList))
		{
			return true;
		} else {
			return false;

		}
	}

	@FindBy(xpath = "//*[@class='icon-panel hiconright2']")
	public static WebElement masterRibbonToExpandOptions;

	@FindBy(xpath = "//a[@class='lSNext']")
	public static WebElement masterRibbonControlNextBtn;

	@FindBy(xpath = "//*[@id='btnCustomizeMaster']")
	public static WebElement masterCustamizemasterBtn;

	@FindBy(xpath = "//i[@class='icon-font6 icon-options']")
	public static WebElement masterCustamizeViewBtn;

	@FindBy(xpath = "//i[@class='icon-font6 icon-tree']")
	public static WebElement mastercustamizeTreeBtn;

	@FindBy(xpath = "//*[@id='masterFields_list']/li[1]")
	private static WebElement generalTab;

	@FindBy(xpath = "//div[@id='tabId_1']//span[@class='font-5'][contains(text(),'Header Details')]")
	private static WebElement generalHeaderAccDetailsTab;

	// @FindBy(xpath="//div[@id='tabId_2']//span[@class='font-5'][contains(text(),'Header
	// Details')]")
	@FindBy(xpath = "//*[@id='tab_HeaderDetails_0']")
	private static WebElement generalHeaderDetailsTab;

	@FindBy(xpath = "//*[@id='btnAdd_Field_MasterCust']/a/i")
	private static WebElement accountAddBtn;

	@FindBy(xpath = "//*[@id='btnClose']")
	private static WebElement accountCloseBtn;

	@FindBy(xpath = "//*[@id='ExtraField_FieldCaption']")
	private static WebElement extraFields_FieldDetailsCaption;

	@FindBy(xpath = "//select[@id='ddlDatatype_ExtraField']")
	private static WebElement extraFields_FieldDetailsDatTypeDropdown;

	@FindBy(xpath = "//div[@id='HeaderDetails_0']/div/div/table/tbody/tr/td[4]")
	private static List<WebElement> GeneralTabfieldCaptionTxtList;

	@FindBy(xpath = "//div[@id='HeaderDetails_0']/div/div/table/tbody/tr/td[1]/i[1]")
	private static List<WebElement> GeneralTabeditFieldList;

	@FindBy(xpath = "//div[@id='HeaderDetails_0']/div/div/table/tbody/tr/td[1]/i[2]")
	private static List<WebElement> GeneralTabDeleteFieldList;

	@FindBy(xpath = "//tbody[@id='ExtraField_MasterToLink_table_body']//tr//td[2]")
	private static List<WebElement> MastersList;

	@FindBy(xpath = "//*[@id='btnSaveRule']/a/i")
	private static WebElement extraFields_OkBtn;

	@FindBy(xpath = "//*[@id='ExtraField_MasterToLink']")
	private static WebElement extraField_FieldDetailsMasterToLinkDropDown;

	@FindBy(xpath = "//*[@id='ExtraField_MandatoryFields']")
	private static WebElement extraField_FieldDetailsMandatoryFields;

	@FindBy(xpath = "//*[@id='221']")
	private static WebElement ItemMenu;

	@FindBy(xpath = "//*[@id='1105']")
	private static WebElement ItemMasterMenu;

	public boolean checkSavingMastertypeFieldInItemMaster()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {

		getDriver().navigate().refresh();

		Thread.sleep(4500);
		
		waitForElement(homeMenu);

		Thread.sleep(2000);

		ClickUsingJs(homeMenu);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(mastersMenu));
		mastersMenu.click();

		Thread.sleep(2000);

		click(ItemMenu);

		click(ItemMasterMenu);

		Thread.sleep(6000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(masterRibbonToExpandOptions));
		masterRibbonToExpandOptions.click();

		Thread.sleep(2000);

		ClickUsingJs(masterCustamizemasterBtn);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(generalTab));
		generalTab.click();
		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(generalHeaderDetailsTab));
		generalHeaderDetailsTab.click();
		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(accountAddBtn));
		accountAddBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(extraFields_FieldDetailsCaption));
		extraFields_FieldDetailsCaption.click();
		extraFields_FieldDetailsCaption.sendKeys("DEPT");
		extraFields_FieldDetailsCaption.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		Select dataTypeDropdown = new Select(extraFields_FieldDetailsDatTypeDropdown);

		dataTypeDropdown.selectByValue("12");

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(extraField_FieldDetailsMasterToLinkDropDown));
		extraField_FieldDetailsMasterToLinkDropDown.click();
		extraField_FieldDetailsMasterToLinkDropDown.sendKeys("Department");
		Thread.sleep(2000);
		extraField_FieldDetailsMasterToLinkDropDown.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(extraFields_OkBtn));
		extraFields_OkBtn.click();

		String expMessage = "Field Added Successfully.";
		String actMessage = checkValidationMessage(expMessage);
		
		Thread.sleep(2000);
		
		getDriver().navigate().refresh();
		
		Thread.sleep(2000);

		if (actMessage.equalsIgnoreCase(expMessage)) {
			return true;
		} else {
			return false;
		}

	}

	public boolean checkAssigingItemDependecyUnderSalesInvoiceVAT()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		
		Thread.sleep(3000);
/*		
		prongHornStopAtAdminLevel();
		
		Thread.sleep(2356);
		
		logout();
		
		Thread.sleep(2356);
		
		restart();
		
		Thread.sleep(2356);
		
		checkLogin();
		
		Thread.sleep(2356);
		
		*/

		ClickUsingJs(settingsMenu);

		click(settingsConfigureTransactions);

		Thread.sleep(2000);

		highlightElement(settingCong_DocCustimeBtn);
		Thread.sleep(2000);
		click(settingCong_DocCustimeBtn);

		Thread.sleep(2000);
		click(doccus_SalesInvoiceVATBtn);

		Thread.sleep(2000);

		click(Postion2);

		Thread.sleep(2000);

		enter_Postion.sendKeys(Keys.ARROW_DOWN, Keys.TAB);

		Thread.sleep(2000);

		click(doc_ItemdepandencyTxt);

		Thread.sleep(2000);

		int count = doc_ItemDepList.size();
		ArrayList<String> list = new ArrayList<>();

		for (int i = 0; i < count; i++) {
			String data = doc_ItemDepList.get(i).getText();

			if (data.equalsIgnoreCase("DEPT")) {
				doc_ItemDepCheckBoxList.get(i).click();
			}
		}

		Thread.sleep(2000);

		String actList = doc_ItemdepandencyTxt.getAttribute("value");
		String expList = "DEPT";

		System.err.println(" ACT : " + actList);
		System.err.println(" EXP : " + expList);

		Thread.sleep(2000);
		click(settingUpdateIcon);

		String expMessage1 = "Data saved successfully";

		String actMessage = checkValidationMessage(expMessage1);

		if (actList.equalsIgnoreCase(expList) && actMessage.equalsIgnoreCase(expMessage1)) {
			return true;
		} else {
			return false;

		}
	}

	public boolean checkViewOptionAndMovingItemForwatdUnderViewTAB()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		
		
		Thread.sleep(2000);

		ClickUsingJs(settingsMenu);

		click(settingsConfigureTransactions);

		Thread.sleep(2000);

		highlightElement(settingCong_DocCustimeBtn);
		Thread.sleep(2000);
		click(settingCong_DocCustimeBtn);

		Thread.sleep(2000);
		click(doccus_SalesInvoiceVATBtn);

		Thread.sleep(1500);
		// click(config_Settings_ToggleButton);

		click(viewBtn);

		click(existingViewTxt);

		Thread.sleep(1999);
		existingViewTxt.sendKeys("Test");
		Thread.sleep(1999);
		existingViewTxt.sendKeys(Keys.TAB);

		Thread.sleep(1999);

		int count = viewFiledNameList.size();

		ArrayList<String> array = new ArrayList<>();

		for (int i = 0; i < count; i++) {
			String data = viewFiledNameList.get(i).getText();

			if (data.equalsIgnoreCase("Item")) {
				viewFiledNameList.get(i).click();
			}
		}
		Thread.sleep(2000);

		click(viewTABMoveUpButton);

		Thread.sleep(1000);
		if (viewTABSUUserChkbox.isDisplayed() == false) {

			click(viewTabLoginBtn);

			Thread.sleep(2000);
		}

		Thread.sleep(2000);

		click(viewTABSUUserChkboxSelected);

		click(viewTABSave);

		String ExpMessage = "Data saved successfully";
		String actMessage = checkValidationMessage(ExpMessage);
		
	/*	
		logout();

		Thread.sleep(3000);
		
		prongHornStartAtAdminLevel();
		
		
		Thread.sleep(3000);
		
		
		//checkLoginToSelectedCompany("BillWise", "su", "su");
		
		//Thread.sleep(3000);
		checkLogin();
		Thread.sleep(4000);*/

		if (actMessage.equalsIgnoreCase(ExpMessage)) {
			return true;

		} else {
			return false;

		}
	}

	@FindBy(xpath = "//*[@id='DEPT']")
	private static WebElement ItemMaster_DepTxt;

	@FindBy(xpath = "//*[@id='WARE']")
	private static WebElement ItemMaster_WarehouseTxt;

	public boolean checkItemSavingWithDepartmentInItemMaster()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{

		Thread.sleep(6500);
		waitForElement(homeMenu);
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(homeMenu));
		ClickUsingJs(homeMenu);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(mastersMenu));
		mastersMenu.click();

		Thread.sleep(2000);

		click(ItemMenu);

		click(ItemMasterMenu);

		Thread.sleep(6000);

		int count = masterItemFcaptionList.size();

		ArrayList<String> array = new ArrayList<>();

		for (int i = 0; i < count; i++) {
			String data = masterItemFcaptionList.get(i).getText();
			if (data.equalsIgnoreCase("STOCK ITEM")) {
				masterItemChkBoxList.get(i).click();
			}
		}

		Thread.sleep(1000);

		click(masterEditBtn);

		Thread.sleep(5000);
		getAction().moveToElement(ItemMaster_DepTxt).build().perform();
		Thread.sleep(1500);
		click(ItemMaster_DepTxt);

		removetTxt(ItemMaster_DepTxt);
		ItemMaster_DepTxt.sendKeys("AMERICA");

		Thread.sleep(2000);
		ItemMaster_DepTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		String actDep = ItemMaster_DepTxt.getAttribute("value");
		String expDep = "AMERICA";

		System.err.println(" DEP Displayed as " + actDep + " Value Exp : " + expDep);

		Thread.sleep(3000);
		
		moveToElement(mastersaveBtn);
		
		Thread.sleep(3000);

		String ExpMessage = "Updated Successfully";
		String actMessage = checkValidationMessage(ExpMessage);

		if (actMessage.equalsIgnoreCase(ExpMessage) && actDep.equalsIgnoreCase(expDep)) {
			return true;

		} else {
			return false;

		}
	}

	public boolean checkAssigningDepToFIFOItem()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{

		Thread.sleep(3000);
		
		waitForElement(homeMenu);
		Thread.sleep(2000);
		
		ClickUsingJs(homeMenu);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(mastersMenu));
		mastersMenu.click();

		Thread.sleep(2000);

		click(ItemMenu);

		click(ItemMasterMenu);

		Thread.sleep(3000);

		int count = masterItemFcaptionList.size();

		ArrayList<String> array = new ArrayList<>();

		for (int i = 0; i < count; i++) {
			String data = masterItemFcaptionList.get(i).getText();
			if (data.equalsIgnoreCase("BATCH FIFO ITEM")) {
				masterItemChkBoxList.get(i).click();
			}
		}

		Thread.sleep(1000);

		click(masterEditBtn);

		Thread.sleep(2000);

		click(ItemMaster_DepTxt);

		removetTxt(ItemMaster_DepTxt);
		ItemMaster_DepTxt.sendKeys("DUBAI");

		Thread.sleep(2000);
		ItemMaster_DepTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		String actDep = ItemMaster_DepTxt.getAttribute("value");
		String expDep = "DUBAI";

		System.err.println(" DEP Displayed as " + actDep + " Value Exp : " + expDep);

		Thread.sleep(3000);
		moveToElement(mastersaveBtn);
		

		String ExpMessage = "Updated Successfully";
		String actMessage = checkValidationMessage(ExpMessage);
		
		Thread.sleep(3000);

		if (actMessage.equalsIgnoreCase(ExpMessage) && actDep.equalsIgnoreCase(expDep)) {
			return true;

		} else {
			return false;

		}
	}

	@FindBy(xpath = "//*[@id='id_body_536870915']")
	private static WebElement enter_DepTxt;

	public boolean checkVouherLevelValidationAfterAddingItemDependency() throws InterruptedException {

		Thread.sleep(2000);
		click(financialsMenu);

		click(financialsTransactionMenu);

		click(financialTransactionSalesMenu);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(salesInvoiceVATVoucher));
		salesInvoiceVATVoucher.click();

		Thread.sleep(2000);

		waitToClick(newBtn);

		Thread.sleep(2999);

		click(select1stRow_2ndColumn);

		Thread.sleep(1000);
		enter_ItemTxt.sendKeys("BATCH FIFO ITEM");
		Thread.sleep(2000);
		enter_ItemTxt.sendKeys(Keys.TAB);

		click(select1stRow_3rdColumn);
		enter_DepTxt.click();

		String actDep = enter_DepTxt.getAttribute("value");
		String expDep = "DUBAI";

		System.out.println(" Dep Text Displayed With BATCH FIFO ITEM: " + actDep + " Value Exp : " + expDep);

		if (actDep.equalsIgnoreCase(expDep)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkSalesInvoiceVATValidationDepartmentAsStockItem() throws InterruptedException {

		Thread.sleep(2999);

		click(select1stRow_2ndColumn);

		removetTxt(enter_ItemTxt);
		Thread.sleep(1000);
		enter_ItemTxt.sendKeys("STOCK ITEM");
		Thread.sleep(2000);
		enter_ItemTxt.sendKeys(Keys.TAB);

		click(select1stRow_3rdColumn);
		enter_DepTxt.click();

		String actDep = enter_DepTxt.getAttribute("value");
		String expDep = "AMERICA";

		System.out.println(" Dep Text Displayed With BATCH FIFO ITEM: " + actDep + " Value Exp : " + expDep);

		if (actDep.equalsIgnoreCase(expDep)) {
			return true;
		} else {
			return false;
		}
	}

	// @FindBy(xpath="//span[@class='icon-delete icon-font8']")
	@FindBy(xpath = "//*[@id='id_transentry_body_menu']/a[1]")
	private static WebElement indexDeleteRowButton;

	public boolean checkSalesInvoiceVATValidationDepartmentAsRMAItemWhichIsNotAddedDepency()
			throws InterruptedException {

		Thread.sleep(2500);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(firstRowIndex));

		getAction().contextClick(firstRowIndex).build().perform();
		Thread.sleep(2000);

		click(indexDeleteRowButton);

		Thread.sleep(2999);

		click(select1stRow_2ndColumn);

		enter_ItemTxt.sendKeys("FIFO COGS ITEM");
		Thread.sleep(2000);
		enter_ItemTxt.sendKeys(Keys.TAB);

		click(select1stRow_3rdColumn);
		enter_DepTxt.click();

		String actDep = enter_DepTxt.getAttribute("value");
		String expDep = "";

		System.out.println(" Dep Text Displayed With RMA ITEM: " + actDep + " Value Exp : " + expDep);

		if (actDep.equalsIgnoreCase(expDep)) {
			System.out.println(" Test Pass: Display empty Data as Not assigned any dep Master ");
			return true;
		} else {
			System.out.println(" Test Fail : Department displayed ");
			return false;
		}
	}

	public boolean checkSavingWareExtraFieldUnderItemGeneralHeaderDetailsTAB()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {

		getDriver().navigate().refresh();

		Thread.sleep(4500);
		waitForElement(homeMenu);
		
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(homeMenu));
		ClickUsingJs(homeMenu);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(mastersMenu));
		mastersMenu.click();

		Thread.sleep(2000);

		click(ItemMenu);

		click(ItemMasterMenu);

		Thread.sleep(3000);

		click(masterRibbonToExpandOptions);

		Thread.sleep(300);

		ClickUsingJs(masterCustamizemasterBtn);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(generalTab));
		generalTab.click();
		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(generalHeaderDetailsTab));
		generalHeaderDetailsTab.click();
		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(accountAddBtn));
		accountAddBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(extraFields_FieldDetailsCaption));
		extraFields_FieldDetailsCaption.click();
		extraFields_FieldDetailsCaption.sendKeys("WARE");
		extraFields_FieldDetailsCaption.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		Select dataTypeDropdown = new Select(extraFields_FieldDetailsDatTypeDropdown);

		dataTypeDropdown.selectByValue("12");

		Thread.sleep(2000);

		getFluentWebDriverWait()
				.until(ExpectedConditions.elementToBeClickable(extraField_FieldDetailsMasterToLinkDropDown));
		extraField_FieldDetailsMasterToLinkDropDown.sendKeys("Warehouse");
		Thread.sleep(2000);
		extraField_FieldDetailsMasterToLinkDropDown.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(extraFields_OkBtn));
		extraFields_OkBtn.click();
		Thread.sleep(2000);
		String expMessage = "Field Added Successfully.";
		String actMessage = checkValidationMessage(expMessage);

		if (actMessage.equalsIgnoreCase(expMessage)) {
			return true;
		} else {
			return false;
		}
	}
	
	
	public static void relogin() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		
		
		Thread.sleep(2569);
		
		logout();
		
		Thread.sleep(2569);
		
	//	restart();
	//	
		Thread.sleep(2356);
		
		checkLogin();
		
		Thread.sleep(2356);
		
		
		
	}

	public boolean checkItemFilterDrpDwnInSalesInvoiceVATVoucherWizard()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {

		getDriver().navigate().refresh();

		Thread.sleep(2000);
	/*	
		

		Thread.sleep(3000);
		
		prongHornStopAtAdminLevel();
		
		Thread.sleep(3000);
		
		
		relogin();
		*/

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(settingsMenu));
		ClickUsingJs(settingsMenu);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(settingsConfigureTransactions));
		settingsConfigureTransactions.click();

		Thread.sleep(2000);

		highlightElement(settingCong_DocCustimeBtn);
		Thread.sleep(2000);

		click(settingCong_DocCustimeBtn);

		Thread.sleep(2000);
		ClickUsingJs(doccus_SalesInvoiceVATBtn);
		Thread.sleep(3000);

		Select s1 = new Select(doc_ItemFilterDrpdwn);
		s1.selectByVisibleText("WARE");

		Thread.sleep(2000);
		doc_ItemFilterDrpdwn.sendKeys(Keys.TAB);

		String actDisplay = s1.getFirstSelectedOption().getText();
		String expDisplay = "WARE";

		System.err.println(" Item Filter Options Selected :" + actDisplay + " Value Exp: " + expDisplay);

		click(dependency1);
		Thread.sleep(2000);
		dependencyTxt1.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		click(settingUpdateIcon);
		Thread.sleep(2000);
		String expMessage1 = "Data saved successfully";

		String actMessage = checkValidationMessage(expMessage1);

	/*	logout();
		
		Thread.sleep(3000);
		
		prongHornStartAtAdminLevel();
		
		Thread.sleep(3000);
		
		//checkLoginToSelectedCompany("BillWise", "su", "su");
		
		//Thread.sleep(3000);
		
		checkLogin();
		Thread.sleep(4000);*/

		if (actMessage.equalsIgnoreCase(expMessage1) && actDisplay.equalsIgnoreCase(expDisplay)) {
			
			return true;
		} else {
			return false;
		}
	}

	public boolean checkAssgingWarehouseToItem()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException 
	{

		Thread.sleep(3000);
		
		waitForElement(homeMenu);
		
		Thread.sleep(2000);
		ClickUsingJs(homeMenu);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(mastersMenu));
		mastersMenu.click();

		Thread.sleep(2000);

		click(ItemMenu);

		click(ItemMasterMenu);

		Thread.sleep(5000);

		int count = masterItemFcaptionList.size();

		ArrayList<String> array = new ArrayList<>();

		for (int i = 0; i < count; i++) {
			String data = masterItemFcaptionList.get(i).getText();
			if (data.equalsIgnoreCase("BATCH FIFO ITEM")) {
				masterItemChkBoxList.get(i).click();
			}
		}

		Thread.sleep(2569);

		click(masterEditBtn);

		Thread.sleep(4000);

		getAction().moveToElement(ItemMaster_WarehouseTxt).build().perform();
		Thread.sleep(1500);
		click(ItemMaster_WarehouseTxt);

		removetTxt(ItemMaster_WarehouseTxt);
		ItemMaster_WarehouseTxt.sendKeys("STWH");

		Thread.sleep(2000);
		ItemMaster_WarehouseTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		String actDep = ItemMaster_WarehouseTxt.getAttribute("value");
		String expDep = "STWH";

		System.err.println(" DEP Displayed as " + actDep + " Value Exp : " + expDep);

		ClickUsingJs(mastersaveBtn);

		String ExpMessage = "Updated Successfully";
		String actMessage = checkValidationMessage(ExpMessage);

		if (actMessage.equalsIgnoreCase(ExpMessage) && actDep.equalsIgnoreCase(expDep)) {
			return true;

		} else {
			return false;

		}
	}

	public boolean checkAlignmentOFWarehouseInViewTAbInSalesInvoiceVAT()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException 
	{
		
		Thread.sleep(3000);
	/*	
		prongHornStopAtAdminLevel();
		
		Thread.sleep(3000);
		
		
		relogin();
		
		
		Thread.sleep(3000);*/

		ClickUsingJs(settingsMenu);

		click(settingsConfigureTransactions);

		Thread.sleep(2000);

		highlightElement(settingCong_DocCustimeBtn);
		Thread.sleep(2000);
		click(settingCong_DocCustimeBtn);

		Thread.sleep(2000);
		click(doccus_SalesInvoiceVATBtn);

		Thread.sleep(1500);
		// click(config_Settings_ToggleButton);

		click(viewBtn);

		click(existingViewTxt);

		Thread.sleep(1999);
		removetTxt(existingViewTxt);
		Thread.sleep(1999);
		existingViewTxt.sendKeys("Test");
		Thread.sleep(1999);
		existingViewTxt.sendKeys(Keys.TAB);

		Thread.sleep(3500);

		int count = viewFiledNameList.size();

		ArrayList<String> array = new ArrayList<>();

		for (int i = 0; i < count; i++) {
			String data = viewFiledNameList.get(i).getText();

			if (data.equalsIgnoreCase("Department")) {
				viewFiledNameList.get(i).click();
			}
		}
		Thread.sleep(2000);

		click(viewTABMoveUpButton);

		Thread.sleep(2000);

		for (int i = 0; i < count; i++) {
			String data = viewFiledNameList.get(i).getText();

			if (data.equalsIgnoreCase("Item")) {
				viewFiledNameList.get(i).click();
			}
		}
		Thread.sleep(2000);

		click(viewTABMoveUpButton);

		Thread.sleep(2000);

		click(viewTABSave);

		Thread.sleep(4000);

		click(settingUpdateIcon);
		
		String ExpMessage = "Data saved successfully";
		String actMessage = checkValidationMessage(ExpMessage);
		
		Thread.sleep(3000);
		
	/*	logout();
		
		Thread.sleep(3000);
		
		prongHornStartAtAdminLevel();
		
		Thread.sleep(3000);
		
		//checkLoginToSelectedCompany("BillWise", "su", "su");
		checkLogin();
		Thread.sleep(4000);
*/
		

		if (actMessage.equalsIgnoreCase(ExpMessage)) {
			return true;

		} else {
			return false;
		}
	}

	public boolean checkValidationAtSalesInvoiceVATvoucher()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		Thread.sleep(2000);

		click(financialsMenu);

		click(financialsTransactionMenu);

		click(financialTransactionSalesMenu);

		Thread.sleep(2000);
		click(salesInvoiceVATVoucher);

		Thread.sleep(8000);

		click(newBtn);

		Thread.sleep(2000);

		//checkValidationMessage("screen Opened");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_1stColumn));
		select1stRow_1stColumn.click();

		pvWareHouseTxt.click();

		pvWareHouseTxt.sendKeys("STWH");

		Thread.sleep(2000);

		pvWareHouseTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		click(select1stRow_2ndColumn);
		Thread.sleep(1000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_ItemTxt));
		enter_ItemTxt.sendKeys(Keys.SPACE);

		int itemsCount = itemComboList.size();
		ArrayList<String> itemarray = new ArrayList<>();

		for (int i = 0; i < itemsCount; i++) {
			String item = itemComboList.get(i).getText();

			itemarray.add(item);

			if (item.equalsIgnoreCase("BATCH FIFO ITEM")) {
				itemComboList.get(i).click();

				break;
			}
		}
		Thread.sleep(2000);
		
		String act = itemarray.toString();
		String exp = "[BATCH FIFO ITEM]";

		System.out.println(" Item List Displayed After Selected warehouse aas STWH:" + act + "-------" + exp);

		enter_ItemTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		
		click(select1stRow_3rdColumn);

		String actbodyDep = enterBodyDepTxt.getAttribute("value");
		String expBodyDep = "DUBAI";

		System.err.println(" DEP List Displayed After Selected warehouse aas BATCH FIFO ITEM :" + actbodyDep + "-------"
				+ expBodyDep);

		if (act.equalsIgnoreCase(exp) && actbodyDep.equalsIgnoreCase(expBodyDep)) {
			System.out.println(" Displayed as Exp");

			return true;
		} else {
			return false;

		}

	}

	@FindBy(xpath = "//a[@id='1104']//span[contains(text(),'Account')]")
	public static WebElement accounts;

	public boolean checkSavingMasterTypeFiledInAccountMaster()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		getDriver().navigate().refresh();

		Thread.sleep(3000);
		waitForElement(homeMenu);
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(homeMenu));
		ClickUsingJs(homeMenu);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(mastersMenu));
		mastersMenu.click();

		Thread.sleep(2000);

		click(accounts);

		Thread.sleep(3000);
		
		checkValidationMessage("");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(masterRibbonToExpandOptions));
		masterRibbonToExpandOptions.click();

		Thread.sleep(300);

		ClickUsingJs(masterCustamizemasterBtn);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(generalTab));
		generalTab.click();
		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(generalHeaderDetailsTab));
		generalHeaderDetailsTab.click();
		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(accountAddBtn));
		accountAddBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(extraFields_FieldDetailsCaption));
		extraFields_FieldDetailsCaption.click();
		extraFields_FieldDetailsCaption.sendKeys("JURID");
		extraFields_FieldDetailsCaption.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		Select dataTypeDropdown = new Select(extraFields_FieldDetailsDatTypeDropdown);

		dataTypeDropdown.selectByValue("12");

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(extraField_FieldDetailsMasterToLinkDropDown));
		extraField_FieldDetailsMasterToLinkDropDown.click();
		extraField_FieldDetailsMasterToLinkDropDown.sendKeys("Jurisdiction");
		Thread.sleep(2000);
		extraField_FieldDetailsMasterToLinkDropDown.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(extraFields_OkBtn));
		extraFields_OkBtn.click();

		Thread.sleep(8000);
		
		String expMessage = "Field Added Successfully.";
		String actMessage = checkValidationMessage(expMessage);

		if (actMessage.equalsIgnoreCase(expMessage)) {
			return true;
		} else {
			return false;
		}

	}

	public boolean checkAssigningAccountFilterUnderSalesinvoiceVAT()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		
		
		
		
		getDriver().navigate().refresh();
		Thread.sleep(3000);
	/*	prongHornStopAtAdminLevel();
		
		
		Thread.sleep(3000);
		
		
		relogin();*/
		Thread.sleep(3000);

		ClickUsingJs(settingsMenu);
		Thread.sleep(1000);
		click(settingsConfigureTransactions);

		Thread.sleep(2000);

		highlightElement(settingCong_DocCustimeBtn);
		Thread.sleep(2000);
		click(settingCong_DocCustimeBtn);

		Thread.sleep(2000);
		click(doccus_SalesInvoiceVATBtn);

		Thread.sleep(6589);

		Select s2 = new Select(doc_AccFilterDrpdwn);
		s2.selectByVisibleText("JURID");

		Thread.sleep(2000);

		doc_AccFilterDrpdwn.sendKeys(Keys.TAB);

		String actList = s2.getFirstSelectedOption().getText();
		String expList = "JURID";

		System.err.println(" ACT : " + actList);
		System.err.println(" EXP : " + expList);

		Thread.sleep(2000);
		click(settingUpdateIcon);

		Thread.sleep(4000);
		String expMessage1 = "Data saved successfully";

		String actMessage = checkValidationMessage(expMessage1);
		
	/*	
		logout();
		Thread.sleep(3000);
		prongHornStartAtAdminLevel();
		Thread.sleep(3000);
		
		//checkLoginToSelectedCompany("BillWise", "su", "su");
		checkLogin();
		Thread.sleep(4000);*/

		if (actList.equalsIgnoreCase(expList) && actMessage.equalsIgnoreCase(expMessage1)) {
			return true;
		} else {
			return false;

		}

	}

	public boolean checkSavingAccountMasterWithJurdicationAndPlaceOFSupply()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {

		Thread.sleep(2000);

		click(homeMenu);

		click(mastersMenu);

		click(accounts);
		Thread.sleep(2000);
		checkValidationMessage("");
		
		System.out.println("*****");
		click(cusSemiAdjustMasterChkBOx);

		Thread.sleep(4000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(masterEditBtn));
		masterEditBtn.click();

		Thread.sleep(4000);
		
		//checkValidationMessage("");
		
		JavascriptExecutor js= (JavascriptExecutor)getDriver();
		js.executeScript("window.scrollBy(0, 350);");

		Thread.sleep(1500);
		getAction().moveToElement(AccMaster_JURIDTxt).build().perform();
		Thread.sleep(1500);
		click(AccMaster_JURIDTxt);

		Thread.sleep(2000);

		removetTxt(AccMaster_JURIDTxt);
		AccMaster_JURIDTxt.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME);
		AccMaster_JURIDTxt.sendKeys("Ajman");

		Thread.sleep(2000);

		AccMaster_JURIDTxt.sendKeys(Keys.TAB);

		String actText = AccMaster_JURIDTxt.getAttribute("value");
		String expText = "Ajman";

		System.out.println(" Text Displayed: " + actText + "*-*-*-*-*" + expText);

		Thread.sleep(2000);

		click(AccMaster_VATSettingTAB);

		Thread.sleep(2000);

		click(AccMaster_VATSettingPlaceOfSupplyTxt);
		removetTxt(AccMaster_VATSettingPlaceOfSupplyTxt);
		AccMaster_VATSettingPlaceOfSupplyTxt.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME);
		AccMaster_VATSettingPlaceOfSupplyTxt.sendKeys("Abu Dhabi Free Trade Zone of Khalifa Port");

		Thread.sleep(2000);

		AccMaster_VATSettingPlaceOfSupplyTxt.sendKeys(Keys.TAB);

		String actPlaceOfSupplyText = AccMaster_VATSettingPlaceOfSupplyTxt.getAttribute("value");
		String expPlaceOfSupplyText = "Abu Dhabi Free Trade Zone of Khalifa Port";

		System.out.println(" Text Displayed: " + actPlaceOfSupplyText + "*-*-*-*-*" + expPlaceOfSupplyText);

		click(mastersaveBtn);

		String expMessage1 = "Updated Successfully";

		String actMessage = checkValidationMessage(expMessage1);

		if (actMessage.equalsIgnoreCase(expMessage1) && actText.equalsIgnoreCase(expText)
				&& actPlaceOfSupplyText.equalsIgnoreCase(expPlaceOfSupplyText)) {
			return true;
		} else {
			return false;
		}
	}

	@FindBy(xpath = "//tbody[@id='id_header_4_table_body']/tr/td[2]")
	private static List<WebElement> customerAccountComboList;

	public boolean checkValidationInSalesInvoiceVAT()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		Thread.sleep(2000);

		click(financialsMenu);

		click(financialsTransactionMenu);

		click(financialTransactionSalesMenu);

		Thread.sleep(2000);
		click(salesInvoiceVATVoucher);

		Thread.sleep(6000);

		click(newBtn);

		Thread.sleep(2000);

		//checkValidationMessage("screen Opened");

		click(customerAccountTxt);
		customerAccountTxt.sendKeys(Keys.SPACE);

		String beforeSelectingJurdiction = listOfElements(customerAccountComboList);
		String expbeforeSelectingJurdiction = "[Bank, Cash, Customer A, Customer B, Customer C, Customer Display CD For Each Account One, Customer Display CD For Each Account Three, Customer Display CD For Each Account Two, Customer Full Adjustment, Customer New Reference, Customer Semi Adjustment, Customer Update, HDFC]";

		System.err.println(" beforeSelectingJurdiction  : " + beforeSelectingJurdiction);
		System.err.println(" expbeforeSelectingJurdiction:" + expbeforeSelectingJurdiction);

		Thread.sleep(2000);
		
		customerAccountTxt.sendKeys("Customer Semi Adjustment");
		
		Thread.sleep(2000);
		
		customerAccountTxt.sendKeys(Keys.TAB);
		
		Thread.sleep(2000);

		click(PDRVAT_JuridictionTxt);
		//removetTxt(PDRVAT_JuridictionTxt);
		PDRVAT_JuridictionTxt.sendKeys(Keys.SPACE);
		PDRVAT_JuridictionTxt.sendKeys("Ajman");

		Thread.sleep(2000);

		PDRVAT_JuridictionTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		click(customerAccountTxt);
		Thread.sleep(2000);
		//removetTxt(customerAccountTxt);
		//Thread.sleep(2000);

		customerAccountTxt.sendKeys(Keys.SPACE);

		Thread.sleep(2000);
		customerAccountTxt.sendKeys(Keys.BACK_SPACE);
		Thread.sleep(2000);
		

		String AfterSelectingJurdiction = listOfElements(customerAccountComboList);
		String expAfterSelectingJurdiction = "[Customer Semi Adjustment]";

		System.err.println(" AfterSelectingJurdiction  : " + AfterSelectingJurdiction);
		System.err.println(" expAfterSelectingJurdiction:" + expAfterSelectingJurdiction);

		Thread.sleep(2000);
		customerAccountTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		click(placeOFSupplyTxt);

		String actPlaceOfSupply = placeOFSupplyTxt.getAttribute("value");

		String expPlaceOfSupply = "Abu Dhabi Free Trade Zone of Khalifa Port";

		System.err.println(" Place Of Suply Displayed as : " + actPlaceOfSupply);
		System.err.println(" Place Of Suply Displayed as : " + expPlaceOfSupply);

		Thread.sleep(2000);

		getDriver().navigate().refresh();
		
		
		Thread.sleep(4569);

	//	logout();

		if (beforeSelectingJurdiction.equalsIgnoreCase(expbeforeSelectingJurdiction)
				&& AfterSelectingJurdiction.equalsIgnoreCase(expAfterSelectingJurdiction)
				&& actPlaceOfSupply.equalsIgnoreCase(expPlaceOfSupply)) {
			return true;
		} else {
			return false;

		}
	}
	
	@FindBy(xpath = "//*[@id='navigationtab14']")
	public static WebElement inventoryoptionsTabInDC;
	

	@FindBy(xpath = "//*[@id='inventoryOpt_chkShowvendorassignproducts']")
	public static WebElement showCustomerAssignedItemsChkbox;

	
	@FindBy(xpath = "//*[@id='inventoryOpt_chkShowvendorassignproducts']//following-sibling::span")
	public static WebElement showCustomerAssignedItemsChkboxToSelect;
	
	
	public boolean checkEnableShowCustomerAssignedItemsunderSalesinvoiceVAT()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		//checkLoginToSelectedCompany("BillWise", "su", "su");

		//Thread.sleep(2500);
		
	//	checkLogin();
	//	Thread.sleep(4000);
		
		NavigationTosalesInvoiceVATVoucher();

		Thread.sleep(2000);

		click(newBtn);

		Thread.sleep(2000);

		click(toggleBtn);
		Thread.sleep(2000);

		click(settingsOption);

		Thread.sleep(2000);

		click(inventoryoptionsTabInDC);
		Thread.sleep(2000);

		clickOn(showCustomerAssignedItemsChkboxToSelect);

		boolean actshowCustomerAssignedItemsChkbox = showCustomerAssignedItemsChkbox.isSelected();
		boolean expshowCustomerAssignedItemsChkbox = true;

		System.err.println("showCustomerAssignedItemsChkbox Enable Status : " + actshowCustomerAssignedItemsChkbox
				+ " Value EXp::" + expshowCustomerAssignedItemsChkbox);

		click(updateBtn);

		String expMessage = "Data saved successfully";
		String actMessage = checkValidationMessage(expMessage);

		click(settingCloseBtn);

		Thread.sleep(2000);

		if (actshowCustomerAssignedItemsChkbox == expshowCustomerAssignedItemsChkbox) {

			System.err.println(" Test pass: Option Enabled ");
			return true;

		} else {

			System.err.println(" Test Fail: Option Enabled ");
			return false;
		}

	}
	
	@FindBy(xpath = "//*[@id='btnMasterSaveClick']")
	public static WebElement masterSaveBtn;

	public boolean checkAssigingCustomersToItemInItemmaster()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		Thread.sleep(4000);
		click(homeMenu);
		Thread.sleep(2000);

		click(mastersMenu);

		Thread.sleep(2000);
		click(ItemMenu);

		click(ItemMasterMenu);

		Thread.sleep(3000);
		
		//checkValidationMessage("");

		click(stockItem_ItemChkbox);

		Thread.sleep(2000);
		click(masterEditBtn);

		//checkValidationMessage("");
		Thread.sleep(4000);

		
		click(ItemNewOtherDetailsBtn);

		Thread.sleep(2000);

		//click(supplierRow1);
		click(stockItem_ODBodyR1C2);
		click(supplierRow1);

		supplierRow1.sendKeys("customer A");
		Thread.sleep(2000);
		supplierRow1.sendKeys(Keys.TAB);

		//click(supplierRow2);
		click(stockItem_ODBodyR2C2);
		supplierRow2.sendKeys("customer B");
		Thread.sleep(2000);
		supplierRow2.sendKeys(Keys.TAB);

		click(masterSaveBtn);

		String expMessage = "Updated Successfully";
		String actMessage = checkValidationMessage(expMessage);

		if (actMessage.equalsIgnoreCase(expMessage)) {
			System.err.println(" Test Pass: Item Assgined With Customers");
			return true;
		} else {
			System.err.println(" Test Fail: Item Assgined With Customers");
			return false;
		}

	}

	public boolean checkValidationItemsInSalesinvoiceVAT() throws InterruptedException, AWTException, IOException {

		NavigationTosalesInvoiceVATVoucher();
		
		Thread.sleep(4000);

		click(newBtn);
		Thread.sleep(2500);

		selectVoucherHeaderAccount("Customer A");
		Thread.sleep(1500);

		//click(select1stRow_1stColumn);
	//	Thread.sleep(1500);
		//enter_WarehouseTxt.sendKeys(Keys.TAB);
		Thread.sleep(1500);
		click(select1stRow_2ndColumn);

		Thread.sleep(1500);
		enter_ItemTxt.sendKeys(Keys.SPACE);

		String actList = listOfElements(itemListCount);
		String expList = "[STOCK ITEM]";

		System.err.println(" Item Displayed With Customer A: " + actList + " Value Exp:  " + expList);

		Thread.sleep(4000);
		
		enter_ItemTxt.sendKeys(Keys.TAB);
		
		Thread.sleep(4000);
		
	click(new_newBtn);
	Thread.sleep(1500);
	
	click(popUpOKBtn);
	Thread.sleep(10000);
	
	getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNumberTxt));
	documentNumberTxt.click();
	documentNumberTxt.sendKeys(Keys.TAB);
	
	getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customerAccountTxt));
	customerAccountTxt.click();
		 customerAccountTxt.click();
		 customerAccountTxt.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
		 customerAccountTxt.sendKeys("Customer B");
		 
		Thread.sleep(1500);
		customerAccountTxt.sendKeys(Keys.TAB);

		/*click(select1stRow_1stColumn);
		Thread.sleep(1500);
		enter_WarehouseTxt.sendKeys(Keys.TAB);*/

		click(select1stRow_2ndColumn);

		removetTxt(enter_ItemTxt);
		enter_ItemTxt.sendKeys(Keys.SPACE);

		String actList1 = listOfElements(itemListCount);
		String expList1 = "[STOCK ITEM]";

		System.err.println(" Item Displayed With Customer B: " + actList1 + " Value Exp:  " + expList1);

		Thread.sleep(2000);
		click(new_newBtn);
		click(popUpOKBtn);
		selectVoucherHeaderAccount("Customer C");
		Thread.sleep(1500);

	/*	click(select1stRow_1stColumn);
		Thread.sleep(1500);
		enter_WarehouseTxt.sendKeys(Keys.TAB);*/

		click(select1stRow_2ndColumn);

		removetTxt(enter_ItemTxt);
		enter_ItemTxt.sendKeys(Keys.SPACE);

		String actList2 = listOfElements(itemListCount);
		String expList2 = "[]";

		System.err.println(" Item Displayed With Customer C: " + actList2 + " Value Exp:  " + expList2);

		Thread.sleep(1500);

	//	getDriver().navigate().refresh();
		
		
	//	Thread.sleep(3000);
	//	prongHornStopAtAdminLevel();
	//	Thread.sleep(3000);

	//	logout();

		Thread.sleep(1500);

		if (actList.equalsIgnoreCase(expList) && actList1.equalsIgnoreCase(expList1)
				&& actList2.equalsIgnoreCase(expList2)) {
			System.err.println(" Test Pass: Item Are displayed as Expeted");
			return true;

		} else {

			System.err.println(" Test Fail: Item Are displayed as Expeted");
			return false;
		}

	}


	public boolean checkLogoutVoucherOptionsPage() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		getDriver().navigate().refresh();
		Thread.sleep(2000);
		 
		 try
			{
			  getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(userNameDisplayLogo));
			  userNameDisplayLogo.click();
			  Thread.sleep(2000);
			 
			  getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(logoutOption));
			  logoutOption.click();
			  
			  Thread.sleep(2000);
			  
			  boolean actUserLoginPage              = username.isDisplayed() && username.isEnabled()
	                                               && password.isDisplayed() && password.isEnabled();
	                                      
			  boolean expUserLoginPage              = true;
			  
			  if(actUserLoginPage==expUserLoginPage)  
		      {
				System.out.println("***Test Pass: Login Successfull***");
				
				return true;
			  }
		      else
		      {
		  	 
				System.out.println("***Test Fail: Login Not Successfull***");
				
				return false;
			  }
			}
			catch (Exception e)
			{
			 	String exception = e.getMessage();
			 		
				return false;
			}
		}
	

	public VoucherOptionsPage(WebDriver driver)

	{
		PageFactory.initElements(driver, this);
	}

}
