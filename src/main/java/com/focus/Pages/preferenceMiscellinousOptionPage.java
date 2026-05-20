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
import java.util.NoSuchElementException;
import java.util.Set;

import org.openqa.selenium.support.ui.Select;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.eclipse.jetty.util.security.Password;
//import org.h2.security.auth.impl.StaticUserCredentialsValidator;
//import org.omg.CORBA.StringHolder;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.server.handler.SendKeys;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.focus.Pages.LoginPage;
import com.focus.base.BaseEngine;
import com.focus.supporters.ExcelReader;
import com.focus.utilities.DriverUtility;
import com.focus.utilities.POJOUtility;

public class preferenceMiscellinousOptionPage extends BaseEngine {

	private static String url;
	private static String xlfile;
	private static String resPass = "Pass";
	private static String resFail = "Fail";
	private static ExcelReader excelReader;

	@FindBy(xpath = "//span[contains(text(),'Configure Transactions')]")
	private static WebElement ConfigureTransactionBtn;

	@FindBy(xpath = "//div[@id='docCustomization']")
	private static WebElement DocumentCustomizationBtn;

	@FindBy(xpath = "//a[@id='0']")
	private static WebElement TagsBtn;

	@FindBy(xpath = "//ul[@id='preferenceUL']//a[@id='1']")
	private static WebElement AccountsBtn;

	@FindBy(xpath = "//ul[@id='preferenceUL']//ul//li//a[@id='2']")
	private static WebElement BudgetBtn;

	@FindBy(xpath = "//ul[@id='preferenceUL']//ul//li//a[@id='3']")
	private static WebElement ARAPBtn;

	@FindBy(xpath = "//a[@id='4']")
	private static WebElement MiselleanousBtn;

	@FindBy(xpath = "//a[@id='5']")
	private static WebElement PDCBtn;

	@FindBy(xpath = "//ul[@id='preferenceUL']//a[@id='6']")
	private static WebElement InventoryBtn;

	@FindBy(xpath = "//ul[@id='preferenceUL']//ul//li//a[@id='7']")
	private static WebElement BatchBtn;

	@FindBy(xpath = "//ul[@id='preferenceUL']//ul//li//a[@id='30']")
	private static WebElement RMABtn;

	@FindBy(xpath = "//ul[@id='preferenceUL']//ul//li//a[@id='8']")
	private static WebElement BinsBtn;

	@FindBy(xpath = "//ul[@id='preferenceUL']//ul//li//a[@id='9']")
	private static WebElement HirePurchaseBtn;

	@FindBy(xpath = "//ul[@id='preferenceUL']//ul//li//a[@id='10']")
	private static WebElement QuotationAnalysisBtn;

	@FindBy(xpath = "//ul[@id='preferenceUL']//ul//li//a[@id='34']")
	private static WebElement RFIDBtn;

	@FindBy(xpath = "//ul[@id='preferenceUL']//ul//li//a[@id='29']")
	private static WebElement AutoIndentBtn;

	@FindBy(xpath = "//ul[@id='preferenceUL']//ul//li//a[@id='14']")
	private static WebElement ABCAnalysisBtn;

	@FindBy(xpath = "//ul[@id='preferenceUL']//a[@id='11']")
	private static WebElement ReportBtn;

	@FindBy(xpath = "//ul[@id='preferenceUL']//a[@id='12']")
	private static WebElement InternetBtn;

	@FindBy(xpath = "//ul[@id='preferenceUL']//ul//li//a[@id='13']")
	private static WebElement MailSettingsBtn;

	@FindBy(xpath = "//ul[@id='preferenceUL']//a[@id='16']")
	private static WebElement MastersBtn;

	@FindBy(xpath = "//ul[@id='preferenceUL']//a[@id='17']")
	private static WebElement ExternalModulesBtn;

	@FindBy(xpath = "//ul[@id='preferenceUL']//a[@id='18']")
	private static WebElement InfoBtn;

	@FindBy(xpath = "//ul[@id='preferenceUL']//a[@id='19']")
	private static WebElement ProductionBtn;

	@FindBy(xpath = "//ul[@id='preferenceUL']//a[@id='20']")
	private static WebElement MrpBtn;

	@FindBy(xpath = "//ul[@id='preferenceUL']//a[@id='21']")
	private static WebElement QualityControlBtn;

	@FindBy(xpath = "//ul[@id='preferenceUL']//a[@id='22']")
	private static WebElement FixedAssetsBtn;

	@FindBy(xpath = "//ul[@id='preferenceUL']//a[@id='25']")
	private static WebElement VatBtn;

	@FindBy(xpath = "//a[@id='28']")
	private static WebElement PosBtn;

	@FindBy(xpath = "//ul[@id='preferenceUL']//a[@id='35']")
	private static WebElement WareHouseManagementBtn;

	@FindBy(xpath = "//a[@id='36']")
	private static WebElement LetterForCreditBtn;

	@FindBy(xpath = "//ul[@id='preferenceUL']//a[@id='37']")
	private static WebElement PronghornBnt;

	@FindBy(xpath = "//input[@id='txtVoucherWizard']")
	private static WebElement SearchTxt;

	//@FindBy(xpath = "//*[@class='icon-update hiconright2']")
	@FindBy(xpath="//*[@id='updateButton']")
	private static WebElement UpdateBtn;

	@FindBy(xpath = "//i[@class='icon-close hiconright2']")
	private static WebElement CloseBtn;

	@FindBy(xpath = "//select[@id='Accounts']")
	private static WebElement AccountingDropdown;

	@FindBy(xpath = "//select[@id='Inventory']")
	private static WebElement InventoryDropdown;

	@FindBy(xpath = "//select[@id='Payroll']")
	private static WebElement PayRollCostCenterDropdown;

	@FindBy(xpath = "//select[@id='SellingRate']")
	private static WebElement SellingsRateDropdown;

	@FindBy(xpath = "//select[@id='BuyingRates']")
	private static WebElement BuyingRatesdropdown;

	@FindBy(xpath = "//select[@id='PayrolDept']")
	private static WebElement PayRollDepartmentDropdown;

	@FindBy(xpath = "//select[@id='PayrollSite']")
	private static WebElement PayRollSiteDropdown;

	@FindBy(xpath = "//select[@id='DeptwiseAppropriation']")
	private static WebElement FinancialAppropriationByDropdown;

	@FindBy(xpath = "//select[@id='VAT']")
	private static WebElement VatDropdown;

	@FindBy(xpath = "//select[@id='Schemes']")
	private static WebElement SchemesDropdown;

	@FindBy(xpath = "//select[@id='Inventoryallocationtag']")
	private static WebElement InventoryAloocationTagDropdown;

	public static void checkPopUpWindow()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		// Thread.sleep(5000);

		try {
			getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(doNotShowCheckbox));
			doNotShowCheckbox.click();

			// Thread.sleep(2000);

			getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(closeBtnInDemoPopupScreen));
			closeBtnInDemoPopupScreen.click();

			System.err.println("POP UP DISPLAYED AND CLOSED SUCCESSFULLY");

		} catch (Exception e) {
			System.err.println("NO POP UP DISPLAYED");
		}

		// Thread.sleep(4000);
	}

	@FindBy(xpath = "//button[contains(text(),'Ok')]")
	private static WebElement loginRefreshOkBtn;

	public static void checkRefershPopOnlogin()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {

		try {
			getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(loginRefreshOkBtn));
			loginRefreshOkBtn.click();

		} catch (Exception e) {
			System.err.println("NO ALERT POP UP DISPLAYED");
		}

		// Thread.sleep(4000);
	}

	private static boolean methodReturnStatus;
	private static String xlSheetName = "PreferenceMiscellaneous";

	@FindBy(xpath = "//span[contains(text(),'Configure Transactions')]")
	private static WebElement settingsConfigureTransactions;

	@FindBy(xpath = "//*[@id='68']/span")
	private static WebElement settingsTransactionAuthorization;

	@FindBy(xpath = "//*[@id='235']/span")
	private static WebElement settingsPricebookAuthorization;

	@FindBy(xpath = "//*[@id='19']/span")
	private static WebElement settingsConfigureMasters;

	@FindBy(xpath = "//*[@id='67']/span")
	private static WebElement settingsMasterAuthorization;

	@FindBy(xpath = "//*[@id='69']/span")
	private static WebElement settingsCreditLimitAuthorization;

	@FindBy(xpath = "//*[@id='74']/span")
	private static WebElement settingsDesignWorkflow;

	@FindBy(xpath = "//*[@id='105']/span")
	private static WebElement settingsSettingsWizard;

	@FindBy(xpath = "//*[@id='232']/span")
	private static WebElement settingsBatchCodeGeneration;

	@FindBy(xpath = "//*[@id='34']/span")
	private static WebElement settingsMapCreditLimit;

	@FindBy(xpath = "//*[@id='257']/span")
	private static WebElement settingsVATTaxCode;

	@FindBy(xpath = "//*[@id='258']/span")
	private static WebElement settingsCustomerPortalAdmin;

	@FindBy(xpath = "//*[@id='3308']/span")
	private static WebElement settingsBudgetAuthorization;

	@FindBy(xpath = "//*[@id='3320']/span")
	private static WebElement settingsPaymentGatewayIntegration;

	@FindBy(xpath = "//*[@id='3327']/span")
	private static WebElement settingsFixedAssetsAuthorization;

	@FindBy(xpath = "//*[@id='navigation_menu']/li[8]/ul/li")
	private static List<WebElement> settingMenuList;

	@FindBy(xpath = "//div[@id='idGlobalError']")
	public static WebElement validationConfirmationMessage;

	@FindBy(xpath = "//div[@class='theme_color font-6']")
	public static WebElement validationUpdatingConfirmationMessage;

	@FindBy(xpath = "//*[@id='idGlobalError']/div/table/tbody/tr/td[3]/span")
	public static WebElement closeValidationConfirmationMessage;

	@FindBy(xpath = "//span[@class='icon-reject2 theme_color']")
	public static WebElement newErrorMessageCloseBtn;

	// Accounts Creation Page

	// Header tab Fields
	@FindBy(xpath = "//*[@id='divBtnGroup0']/div/a")
	public static WebElement headerGeneralTab;

	@FindBy(xpath = "//*[@id='divBtnGroup1']/div/a")
	public static WebElement headerAccountSettingTab;

	@FindBy(xpath = "//*[@id='divBtnGroup2']/div/a")
	public static WebElement headerDetailsTab;

	@FindBy(xpath = "//*[@id='divBtnGroup3']/div/a")
	public static WebElement headerPrintLayoutTab;

	@FindBy(xpath = "//*[@id='divBtnGroup1']/div/a")
	public static WebElement headerUnitsTab;

	@FindBy(xpath = "//*[@id='divBtnGroup2']/div/a")
	public static WebElement headerItemSettingsTab;

	@FindBy(xpath = "//*[@id='divBtnGroup3']/div/a")
	public static WebElement headerClassificationTab;

	@FindBy(xpath = "//*[@id='divBtnGroup4']/div/a")
	public static WebElement headerOtherDetailsTab;

	@FindBy(xpath = "//*[@id='divBtnGroup5']/div/a")
	public static WebElement headerReplenishmentTab;

	@FindBy(xpath = "//*[@id='divBtnGroup6']/div/a")
	public static WebElement headerOutletTab;

	// Header Section Fields
	@FindBy(xpath = "//*[@id='btnMasterSaveClick']")
	public static WebElement saveBtn;

	@FindBy(xpath = "//*[@id='GenerateNewMasterModel']/div/div[1]/div[2]/div/ul/li[2]/button[5]")
	public static WebElement closeBtn;

	@FindBy(xpath = "//i[@class='icon-close icon-font6']")
	private static WebElement closeBtnInDocumentCustmization;

	// Edit option Fields
	@FindBy(xpath = "//*[@id='GenerateNewMasterModel']/div/div[1]/div[2]/div/ul/li[2]/button[6]")
	public static WebElement editcloseBtn;

	@FindBy(xpath = "//i[@class='icon-font6 icon-paste-clipboard']")
	public static WebElement pasteBtn;

	@FindBy(xpath = "//button[@id='btnMasterClone']")
	public static WebElement editcloneBtn;

	@FindBy(xpath = "//*[@id='btnMasterCopy']")
	public static WebElement copyBtn;

	// New General Elements
	// Name
	@FindBy(xpath = "//input[@id='sName']")
	public static WebElement nameTxt;

	// Code
	@FindBy(xpath = "//input[@id='sCode']")
	public static WebElement codeTxt;

	// AccountType
	@FindBy(xpath = "//select[@id='iAccountType']")
	public static WebElement accountTypeDropdown;

	// CreditLimit
	@FindBy(xpath = "//input[@id='fCreditLimit']")
	public static WebElement creditLimitTxt;

	// CreditDays
	@FindBy(xpath = "//input[@id='iCreditDays']")
	public static WebElement creditdaysTxt;

	// ChequeDiscountLimit
	@FindBy(xpath = "//input[@id='fChequeDiscountLimit']")
	public static WebElement chequeDiscountLimitTxt;

	// Rate Of Interest
	@FindBy(xpath = "//input[@id='fRateofinterest']")
	public static WebElement rateofinterestTxt;

	// BankAccount
	@FindBy(xpath = "//input[@id='iBankAc']")
	public static WebElement bankAccountTxt;

	@FindBy(xpath = "//*[@id='iBankAc_input_image']/span")
	public static WebElement bankAccount_ExpansionBtn;

	@FindBy(xpath = "//*[@id='iBankAc_input_settings']/span")
	public static WebElement bankAccount_SettingBtn;

	// Setting Elements

	// DebitCreditProposal
	@FindBy(xpath = "//select[@id='iDebitCreditProposal']")
	public static WebElement debitCreditProposalDropdown;

	// DebitCreditRequired
	@FindBy(xpath = "//select[@id='iDebitCreditRequired']")
	public static WebElement debitCreditRequiredDropdown;

	// ExchangeAdjustmentGainAC
	@FindBy(xpath = "//input[@id='iExchangeAdjustmentGainAC']")
	public static WebElement exchangeAdjustmentGainACTxt;

	@FindBy(xpath = "//*[@id='iExchangeAdjustmentGainAC_input_image']/span")
	public static WebElement exchangeAdjustmentGainAC_ExpansionBtn;

	@FindBy(xpath = "//*[@id='iExchangeAdjustmentGainAC_input_settings']/span")
	public static WebElement exchangeAdjustmentGainAC_SettingBtn;

	// ExchangeAdjustmentLossAC
	@FindBy(xpath = "//input[@id='iExchangeAdjustmentLossAC']")
	public static WebElement exchangeAdjustmentLossACTxt;

	@FindBy(xpath = "//*[@id='iExchangeAdjustmentLossAC_input_image']/span")
	public static WebElement exchangeAdjustmentLossAC_ExpansionBtn;

	@FindBy(xpath = "//*[@id='iExchangeAdjustmentLossAC_input_settings']/span")
	public static WebElement exchangeAdjustmentLossAC_SettingBtn;

	// PrimaryAccount
	@FindBy(xpath = "//input[@id='iPrimaryAccount']")
	public static WebElement primaryAccountTxt;

	@FindBy(xpath = "//*[@id='iPrimaryAccount_input_image']/span")
	public static WebElement primaryAccount_ExpansionBtn;

	@FindBy(xpath = "//*[@id='iPrimaryAccount_input_settings']/span")
	public static WebElement primaryAccount_SettingBtn;

	// DefaultCurrency
	@FindBy(xpath = "//input[@id='optCurrency']")
	public static WebElement defaultCurrencyTxt;

	@FindBy(xpath = "//*[@id='iDefaultCurrency_input_image']/span")
	public static WebElement defaultCurrency_ExpansionBtn;

	@FindBy(xpath = "//*[@id='iDefaultCurrency_input_settings']/span")
	public static WebElement defaultCurrency_SettingBtn;

	// ConsolidationMethod
	@FindBy(xpath = "//select[@id='iConsolidationMethod']")
	public static WebElement consolidationMethodDropdown;

	// PaymentTerms
	@FindBy(xpath = "//input[@id='iPaymentTerms']")
	public static WebElement paymentTermstxt;

	@FindBy(xpath = "//*[@id='iPaymentTerms_input_image']/span")
	public static WebElement paymentTerms_ExpansionBtn;

	@FindBy(xpath = "//*[@id='iPaymentTerms_input_settings']/span")
	public static WebElement paymentTerms_SettingBtn;

	// ReminderTerms
	@FindBy(xpath = "//*[@id='iReminderTerms']")
	public static WebElement reminderTermsTxt;

	@FindBy(xpath = "//*[@id='iReminderTerms_input_image']/span")
	public static WebElement reminderTerms_ExpansionBtn;

	@FindBy(xpath = "//*[@id='iReminderTerms_input_settings']/span")
	public static WebElement reminderTerms_SettingBtn;

	// FinanceChargeTerms
	@FindBy(xpath = "//*[@id='iFinanceChargeTerms']")
	public static WebElement financeChargeTermsTxt;

	@FindBy(xpath = "//*[@id='iFinanceChargeTerms_input_image']/span")
	public static WebElement financeChargeTerms_Expansion;

	@FindBy(xpath = "//*[@id='iFinanceChargeTerms_input_settings']/span")
	public static WebElement financeChargeTerms_SettingBtn;

	// Details Elements

	// Address
	@FindBy(xpath = "//textarea[@id='sAddress']")
	public static WebElement addressTxt;

	// City
	@FindBy(xpath = "//input[@id='iCity']")
	public static WebElement cityTxt;

	@FindBy(xpath = "//*[@id='iCity_input_image']/span")
	public static WebElement city_ExpansionBtn;

	@FindBy(xpath = "//*[@id='iCity_input_settings']/span")
	public static WebElement city_SettingBtn;

	// DeliveryAddress
	@FindBy(xpath = "//textarea[@id='sDeliveryAddress']")
	public static WebElement deliveryAddressTxt;

	// Pin
	@FindBy(xpath = "//input[@id='sPin']")
	public static WebElement pinTxt;

	// City2 DeliveryCity (City As Second TextBox)
	@FindBy(xpath = "//input[@id='iDeliveryCity']")
	public static WebElement city2Text;

	@FindBy(xpath = "//*[@id='iDeliveryCity_input_image']/span")
	public static WebElement city2_ExpansionBtn;

	@FindBy(xpath = "//*[@id='iDeliveryCity_input_settings']/span")
	public static WebElement city2_SettingBtn;

	//// Pin Delivery pin Second Pin TxtBox
	@FindBy(xpath = "//input[@id='sDeliveryPin']")
	public static WebElement pin2Txt;

	// Send Email
	@FindBy(xpath = "//input[@id='bSendEmailtocustomer']")
	public static WebElement sendEmailCheckBox;

	// AllowCustomerPortal
	@FindBy(xpath = "//input[@id='bAllowCustomerPortal']")
	public static WebElement allowCustomerPortalCheckBox;

	// SendEmail
	@FindBy(xpath = "//input[@id='sEMail']")
	public static WebElement sendEmailTxt;

	// Password
	@FindBy(xpath = "//input[@id='sPassword']")
	public static WebElement passwordTxt;

	// TelphoneNumber
	@FindBy(xpath = "//input[@id='sTelNo']")
	public static WebElement telphoneNumberTxt;

	// FaxNo
	@FindBy(xpath = "//input[@id='sFaxNo']")
	public static WebElement faxNoTxt;

	// PortalEmail
	@FindBy(xpath = "//input[@id='sPortalEmail']")
	public static WebElement portalEmailTxt;

	// Print Layout Elements
	// Voucher Type
	@FindBy(xpath = "//label[contains(text(),'Voucher Type')]")
	public static WebElement gridVoucherTypeTxt;

	@FindBy(xpath = "//*[@id='iVoucherType_input_image']/span")
	public static WebElement gridVoucher_ExpansionBtn;

	@FindBy(xpath = "//*[@id='iVoucherType_input_settings']/span")
	public static WebElement gridVoucher_SettingBtn;

	// Print layout
	@FindBy(xpath = "//label[contains(text(),'Print Layout')]")
	public static WebElement gridPrintLayoutTxt;

	@FindBy(xpath = "//*[@id='iPrintLayout_input_image']/span")
	public static WebElement gridPrintLayout_ExpansionBtn;

	@FindBy(xpath = "//*[@id='iPrintLayout_input_settings']/span")
	public static WebElement gridPrintLayout_SettingBtn;

	// Item
	@FindBy(xpath = "//*[@id='spnHeaderText']")
	private static WebElement itemLabel;

	@FindBy(xpath = "//*[@id='btnNew']")
	private static WebElement itemNewBtn;

	// Item General Tab
	@FindBy(xpath = "//*[@id='divBtnGroup0']/div/a")
	private static WebElement ItemNewnewGeneralBtn;

	@FindBy(xpath = "//input[@id='sName']")
	private static WebElement newGeneralName;

	@FindBy(xpath = "//input[@id='sCode']")
	private static WebElement newGeneralCode;

	@FindBy(xpath = "//select[@id='iProductType']")
	private static WebElement newGeneralItemTypeDropDown;

	@FindBy(xpath = "//input[@id='fReorderLevel']")
	private static WebElement newGeneralReorderLevel;

	@FindBy(xpath = "//input[@id='iBinCapacity']")
	private static WebElement newGeneralBinCapacity;

	@FindBy(xpath = "//select[@id='iValuationMethod']")
	private static WebElement newGeneralValuationMethodDropDown;

	@FindBy(xpath = "//input[@id='iCategory']")
	private static WebElement newGeneralCategoryDropDown;

	@FindBy(xpath = "//td[@id='iCategory_input_settings']//span[@class='icon-settings optioncontrol_settings_margin']")
	private static WebElement newGeneralCategorySettingBtn;

	@FindBy(xpath = "//select[@id='iProductMake']")
	private static WebElement newGeneralItemMakeDropDown;

	@FindBy(xpath = "//input[@id='Printer']")
	private static WebElement newGeneralPrint;

	@FindBy(xpath = "//input[@id='iAlternateCategory']")
	private static WebElement newGeneralAlternativeCategoryDropdown;

	@FindBy(xpath = "//td[@id='iAlternateCategory_input_settings']//span[@class='icon-settings optioncontrol_settings_margin']")
	private static WebElement newGeneralAlternativeCategoryDropdownSettingBtn;

	@FindBy(xpath = "//textarea[@id='sDescription']")
	private static WebElement newGeneralDescription;

	@FindBy(xpath = "//input[@id='bPerishableItem']")
	private static WebElement newGeneralPerishableItemCheckBox;

	@FindBy(xpath = "//input[@id='iBin']")
	private static WebElement newGeneralBinDropdown;

	@FindBy(xpath = "//td[@id='iBin_input_settings']//span[@class='icon-settings optioncontrol_settings_margin']")
	private static WebElement newGeneralBinDropdownSettingBtn;

	@FindBy(xpath = "//input[@id='pImage']")
	private static WebElement newGeneralUploadImageOrFileBtn;

	@FindBy(xpath = "//*[@id='btnMasterSaveClick']/i")
	private static WebElement newGeneralSaveBtn;

	@FindBy(xpath = "//*[@id='GenerateNewMasterModel']/div/div[1]/div[2]/div/ul/li[2]/button[5]")
	private static WebElement newGeneralCLoseBtn;

	// Item Units Tab
	@FindBy(xpath = "//*[@id='divBtnGroup1']/div/a")
	private static WebElement ItemNewUnitsBtn;

	@FindBy(xpath = "//input[@id='iDefaultBaseUnit']")
	private static WebElement itemUnitsDefaultBaseUnitDropdown;

	@FindBy(xpath = "//*[@id='iDefaultBaseUnit_table_data_body']/tr/td")
	private static List<WebElement> itemUnitsDefaultBaseUnitDropdownList;

	@FindBy(xpath = "//td[@id='iDefaultBaseUnit_input_settings']//span[@class='icon-settings optioncontrol_settings_margin']")
	private static WebElement itemUnitsDefaultBaseUnitSetting;

	@FindBy(xpath = "//input[@id='iDefaultSalesUnit']")
	private static WebElement itemUnitsDefaultSalesUnitDropdown;

	@FindBy(xpath = "//*[@id='iDefaultSalesUnit_table_data_body']/tr/td")
	private static List<WebElement> itemUnitsDefaultSalesUnitDropdownList;

	@FindBy(xpath = "//td[@id='iDefaultSalesUnit_input_settings']//span[@class='icon-settings optioncontrol_settings_margin']")
	private static WebElement itemUnitsDefaultSalesUnitSettingsBtn;

	@FindBy(xpath = "//input[@id='iDefaultPurchaseUnit']")
	private static WebElement itemUnitsDefaultPurchaseUnitDropDown;

	@FindBy(xpath = "//*[@id='iDefaultPurchaseUnit_table_data_body']/tr/td")
	private static List<WebElement> itemUnitsDefaultPurchaseUnitDropDownList;

	@FindBy(xpath = "//td[@id='iDefaultPurchaseUnit_input_settings']//span[@class='icon-settings optioncontrol_settings_margin']")
	private static WebElement itemUnitsDefaultPurchaseUnitSettingsBtn;

	@FindBy(xpath = "//input[@id='fLength']")
	private static WebElement itemUnitsLength;

	@FindBy(xpath = "//input[@id='fWidth']")
	private static WebElement itemUnitswidth;

	@FindBy(xpath = "//input[@id='fHeight']")
	private static WebElement itemUnitsHeight;

	@FindBy(xpath = "//*[@id='fCBM']")
	private static WebElement itemUnitsCBM;

	@FindBy(xpath = "//input[@id='fWeight']")
	private static WebElement itemUnitsWeight;

	@FindBy(xpath = "//input[@id='iPalletUnit']")
	private static WebElement itemUnitsPalletUnitTxt;

	@FindBy(xpath = "//input[@id='fPalletQty']")
	private static WebElement itemUnitsPalletQtyTxt;

	@FindBy(xpath = "//input[@id='fPalletLength']")
	private static WebElement itemUnitsPalletLengthTXt;

	@FindBy(xpath = "//input[@id='fPalletWidth']")
	private static WebElement itemUnitsPalletWidthTxt;

	@FindBy(xpath = "//input[@id='fPalletHeight']")
	private static WebElement itemUnitsPalletHeightTxt;

	// Master Item Settings
	@FindBy(xpath = "//*[@id='divBtnGroup2']/div/a")
	private static WebElement ItemNewSettingsBtn;

	@FindBy(xpath = "//*[@id='fStandardCost']")
	private static WebElement NewSettingsStandardCost;

	@FindBy(xpath = "//*[@id='fOverheadCost']")
	private static WebElement NewSettingsOverheadCost;

	@FindBy(xpath = "//*[@id='fIndirectCost']")
	private static WebElement NewSettingsIndirectCost;

	@FindBy(xpath = "//*[@id='fProfit']")
	private static WebElement NewSettingsProfit;

	@FindBy(xpath = "//*[@id='iDontshowproductexpireddays']")
	private static WebElement NewSettingsDontshowproductexpireddays;

	@FindBy(xpath = "//*[@id='iTaxCode']")
	private static WebElement NewSettingsTaxCode;

	@FindBy(xpath = "//*[@id='iTaxCode_input_settings']/span")
	private static WebElement NewSettingsTaxCodeSttingsBtn;

	@FindBy(xpath = "//*[@id='btnMasterSaveClick']")
	private static WebElement NewSettingsSaveBtn;

	@FindBy(xpath = "//*[@id='GenerateNewMasterModel']/div/div[1]/div[2]/div/ul/li[2]/button[5]")
	private static WebElement NewSettingsCloseBtn;

	// Master Item Classification
	@FindBy(xpath = "//*[@id='divBtnGroup3']/div/a")
	private static WebElement ItemNewClassificationBtn;

	@FindBy(xpath = "//*[@id='iABCCodeValue']")
	private static WebElement newItemClassificationABC_CodeValueDropdown;

	@FindBy(xpath = "//*[@id='iABCCodeMargin']")
	private static WebElement newItemClassificationABC_CodeMarginDropdOwn;

	@FindBy(xpath = "//*[@id='iABCCodeRevenue']")
	private static WebElement newItemClassificationABCCodeRevenueDropdown;

	@FindBy(xpath = "//*[@id='iABCCodeCarryingCost']")
	private static WebElement newItemClassificationABCCodeCarryingCostDropdown;

	@FindBy(xpath = "//*[@id='sBarcode1']")
	private static WebElement newItemClassificationBarcode1;

	@FindBy(xpath = "//*[@id='sBarcode2']")
	private static WebElement newItemClassificationBarcode2;

	@FindBy(xpath = "//*[@id='sBarcode3']")
	private static WebElement newItemClassificationBarcode3;

	@FindBy(xpath = "//*[@id='iUnit1']")
	private static WebElement newItemgridFirstrowUnit;

	@FindBy(xpath = "//*[@id='sBarcode1']")
	private static WebElement newItemgridFirstrowBarcode;

	// Grid element
	@FindBy(xpath = "//*[@id='sBatch1']")
	private static WebElement newItemClassificationgridFirstrowBatch;

	@FindBy(xpath = "//*[@id='iUnit2']")
	private static WebElement newItemClassificationgridSecondrowUnit;

	@FindBy(xpath = "//*[@id='sBarcode2']")
	private static WebElement newItemClassificationgridSecondrowBarcode;

	@FindBy(xpath = "//*[@id='sBatch2']")
	private static WebElement newItemClassificationgridSecondrowBatch;

	@FindBy(xpath = "//*[@id='iUnit3']")
	private static WebElement newItemClassificationgridThridrowUnit;

	@FindBy(xpath = "//*[@id='sBarcode3']")
	private static WebElement newItemClassificationgridThridrowBarcode;

	@FindBy(xpath = "//*[@id='sBatch3']")
	private static WebElement newItemClassificationgridThridrowBatch;

	@FindBy(xpath = "//*[@id='iUnit_input_settings']")
	private static WebElement newItemClassificationgridUnitSettings;

	@FindBy(xpath = "//div[@id='divTreeMenu']")
	private static WebElement treeUnitsDisplay;

	// Master Item Other Details
	@FindBy(xpath = "//*[@id='divBtnGroup4']/div/a")
	private static WebElement ItemNewOtherDetailsBtn;

	@FindBy(xpath = "//input[@id='iCostOfIssueAccount']")
	private static WebElement newOtherDetailsCostOfIssueitem;

	@FindBy(xpath = "//*[@id='iCostOfIssueAccount_table_data_body']/tr/td")
	private static List<WebElement> newOtherDetailsCostOfIssueitemList;

	@FindBy(xpath = "//td[@id='iCostOfIssueAccount_input_settings']//span[@class='icon-settings optioncontrol_settings_margin']")
	private static WebElement newOtherDetailsCostOfIssueitemsettingsBtn;

	@FindBy(xpath = "//input[@id='iStocksAccount']")
	private static WebElement newOtherDetailsStocksitem;

	@FindBy(xpath = "//*[@id='iStocksAccount_table_data_body']/tr/td")
	private static List<WebElement> newOtherDetailsStocksitemList;

	@FindBy(xpath = "//td[@id='iStocksAccount_input_settings']//span[@class='icon-settings optioncontrol_settings_margin']")
	private static WebElement newOtherDetailsiStocksitemsettingsBtn;

	@FindBy(xpath = "//input[@id='iSalesAccount']")
	private static WebElement newOtherDetailsSalesAccount;

	@FindBy(xpath = "//*[@id='iSalesAccount_table_data_body']/tr/td")
	private static List<WebElement> newOtherDetailsSalesAccountList;

	@FindBy(xpath = "//td[@id='iSalesAccount_input_settings']//span[@class='icon-settings optioncontrol_settings_margin']")
	private static WebElement newOtherDetailsSalesitemsettingsBtn;

	@FindBy(xpath = "//input[@id='iWIPAccount']")
	private static WebElement newOtherDetailsWIPitem;

	@FindBy(xpath = "//td[@id='iWIPAccount_input_settings']//span[contains(@class,'icon-settings optioncontrol_settings_margin')]")
	private static WebElement newOtherDetailsWIPitemsettingsBtn;

	@FindBy(xpath = "//input[@id='iCostofShortageStockAC']")
	private static WebElement newOtherDetailsCostofShortageStockAC;

	@FindBy(xpath = "//*[@id='iCostofShortageStockAC_table_data_body']/tr/td")
	private static List<WebElement> newOtherDetailsCostofShortageStockACList;

	@FindBy(xpath = "//td[@id='iCostofShortageStockAC_input_settings']//span[contains(@class,'icon-settings optioncontrol_settings_margin')]")
	private static WebElement newOtherDetailsCostofShortageStockACsettingsBtn;

	@FindBy(xpath = "//input[@id='iCostofExcessStockAC']")
	private static WebElement newOtherDetailsCostofExcessStockAC;

	@FindBy(xpath = "//*[@id='iCostofExcessStockAC_table_data_body']/tr/td")
	private static List<WebElement> newOtherDetailsCostofExcessStockACList;

	@FindBy(xpath = "//td[@id='iCostofExcessStockAC_input_settings']//span[contains(@class,'icon-settings optioncontrol_settings_margin')]")
	private static WebElement OtherDetailsCostofExcessStockACsettingsBtn;

	@FindBy(xpath = "//input[@id='iCostofSaleReturnAC']")
	private static WebElement OtherDetailsCostofSaleReturnAC;

	@FindBy(xpath = "//*[@id='iCostofSaleReturnAC_table_data_body']/tr/td")
	private static List<WebElement> OtherDetailsCostofSaleReturnACList;

	@FindBy(xpath = "//td[@id='iCostofSaleReturnAC_input_settings']//span[contains(@class,'icon-settings optioncontrol_settings_margin')]")
	private static WebElement OtherDetailsCostofSaleReturnACsettingsBtn;

	@FindBy(xpath = "//input[@id='iPurchaseVarianceAC']")
	private static WebElement OtherDetailsPurchaseVarianceAC;

	@FindBy(xpath = "//td[@id='iPurchaseVarianceAC_input_settings']//span[contains(@class,'icon-settings optioncontrol_settings_margin')]")
	private static WebElement OtherDetailsPurchaseVarianceACsettingsBtn;

	@FindBy(xpath = "//*[@id='btnMasterSaveClick']")
	private static WebElement newOtherDetailsSaveBtn;

	@FindBy(xpath = "//*[@id='GenerateNewMasterModel']/div/div[1]/div[2]/div/ul/li[2]/button[5]")
	private static WebElement newOtherDetailsCloseBtn;

	// Grid Elements
	@FindBy(xpath = "//*[@id='ioWarehouse1']")
	private static WebElement newOtherDetailsgridFirstrowWareHouse;

	@FindBy(xpath = "//*[@id='ioWarehouse_input_settings']/span")
	private static WebElement newGridWarehouseSettingBtn;

	@FindBy(xpath = "//*[@id='1']")
	private static WebElement newOtherDetailsgridFirstrowSupplier;

	@FindBy(xpath = "//*[@id='iSupplier_input_settings']/span")
	private static WebElement newGridSupplierSettingBtn;

	@FindBy(xpath = "//*[@id='sSupCode1']")
	private static WebElement newOtherDetailsgridFirstrowCode;

	@FindBy(xpath = "//*[@id='iStartDate1']")
	private static WebElement newOtherDetailsgridFirstrowStartDate;

	@FindBy(xpath = "//*[@id='iEndDate1']")
	private static WebElement newOtherDetailsgridFirstrowEnddate;

	@FindBy(xpath = "//*[@id='fRate1']")
	private static WebElement newOtherDetailsgridFirstrowRate;

	@FindBy(xpath = "//*[@id='iTolerance1']")
	private static WebElement newOtherDetailsgridFirstTolerance;

	@FindBy(xpath = "//*[@id='iLeadTime1']")
	private static WebElement newOtherDetailsgridFirstrowLeadTime;

	@FindBy(xpath = "//*[@id='iCreditDays1']")
	private static WebElement newOtherDetailsgridFirstrowUnitCreditDays;

	@FindBy(xpath = "//*[@id='iCreditLimit1']")
	private static WebElement newOtherDetailsgridFirstrowCreditLimit;

	@FindBy(xpath = "//*[@id='LeastDeliveryDate1']")
	private static WebElement newOtherDetailsgridFirstrowLastDeliveryDate;

	// Master Item Replenishment
	@FindBy(xpath = "//*[@id='divBtnGroup5']/div/a")
	private static WebElement ItemNewReplenishmentBtn;

	@FindBy(xpath = "//*[@id='iDefaultReplenishment']")
	private static WebElement newreplenishmentDefaultReplenishment;

	@FindBy(xpath = "//*[@id='iManufacturePolicy']")
	private static WebElement newreplenishmentManufacturePolicy;

	@FindBy(xpath = "//*[@id='iPackingBOM']")
	private static WebElement newreplenishmentPackingBOM;

	@FindBy(xpath = "//*[@id='iPackingBOM_input_settings']/span")
	private static WebElement newreplenishmentPackingBOMsettingsBtn;

	@FindBy(xpath = "//*[@id='iBOM']")
	private static WebElement newreplenishmentBOM;

	@FindBy(xpath = "//*[@id='iFlushing']")
	private static WebElement newreplenishmentFlushing;

	@FindBy(xpath = "//*[@id='btnMasterSaveClick']")
	private static WebElement newreplenishmentSaveBtn;

	@FindBy(xpath = "//*[@id='GenerateNewMasterModel']/div/div[1]/div[2]/div/ul/li[2]/button[5]")
	private static WebElement newreplenishmentCloseBtn;

	// Grid
	@FindBy(xpath = "//*[@id='irWarehouse1']")
	private static WebElement newreplenishmentGridFirstRowWarehouse;

	@FindBy(xpath = "//*[@id='irStartdate1']")
	private static WebElement newreplenishmentGridFirstRowStartDate;

	@FindBy(xpath = "//*[@id='irEnddate1']")
	private static WebElement newreplenishmentGridFirstRowEndDate;

	@FindBy(xpath = "//*[@id='iReplenishqty1']")
	private static WebElement newreplenishmentGridFirstRowReplenishqty;

	@FindBy(xpath = "//*[@id='iReorderPolicy1']")
	private static WebElement newreplenishmentGridFirstRowReorderPolicy;

	@FindBy(xpath = "//*[@id='fSafetyStock1']")
	private static WebElement newreplenishmentGridFirstRowSafetyStock;

	@FindBy(xpath = "//*[@id='iSafetyLeadTime1']")
	private static WebElement newreplenishmentFirstRowSafetyLeadTime;

	@FindBy(xpath = "//*[@id='fReordercycle1']")
	private static WebElement newreplenishmentFirstRowReordercycle;

	@FindBy(xpath = "//*[@id='fReorderPoint1']")
	private static WebElement newreplenishmentFirstRowReorderPoint;

	@FindBy(xpath = "//*[@id='fReorderQuantity1']")
	private static WebElement newreplenishmentFirstRowReorderQuantity;

	@FindBy(xpath = "//*[@id='fMaxInventoryLevel1']")
	private static WebElement newreplenishmentFirstRowMaxInventoryLevel;

	@FindBy(xpath = "//*[@id='iDefaultVendor1']")
	private static WebElement newreplenishmentFirstRowDefaultVendor;

	@FindBy(xpath = "//*[@id='fMinOrderQuantity1']")
	private static WebElement newreplenishmentFirstRowMinOrderQuantity;

	@FindBy(xpath = "//*[@id='fMaxOrderQuantity1']")
	private static WebElement newreplenishmentFirstRowMaxOrderQuantity1;

	@FindBy(xpath = "//*[@id='fOrderMultiple1']")
	private static WebElement newreplenishmentFirstRowOrderMultiple;

	// OutLet Elements
	@FindBy(xpath = "//*[@id='divBtnGroup7']/div/a")
	private static WebElement ItemNewOutletBtn;

	@FindBy(xpath = "//*[@id='txtsrch-term0']")
	private static WebElement newOutletSearchOutlet;

	@FindBy(xpath = "//*[@id='cmbUserTypeMaster']")
	private static WebElement newOutletAdvanceSearch;

	@FindBy(xpath = "//*[@id='chkResizeGrid0']/span")
	private static WebElement newOutletResizeBtn;

	@FindBy(xpath = "//*[@id='chkRetainSelection']")
	private static WebElement newOutletRetainSelctionCheckBox;

	@FindBy(xpath = "//a[contains(text(),'Tariff')]")
	private static WebElement tariffTab;

	@FindBy(xpath = "//input[@id='iTariffCode1']")
	private static WebElement tariffGridTariffCode;

	@FindBy(xpath = "//input[@id='iAffectedDate1']")
	private static WebElement tariffGridAffecteddate;

	@FindBy(xpath = "//*[@id='btnMasterSaveClick']/i")
	private static WebElement itemCreationScreenSaveBtn;

	@FindBy(xpath = "//*[@id='GenerateNewMasterModel']/div/div[1]/div[2]/div/ul/li[2]/button[5]")
	private static WebElement itemCreationScreenCloseBtn;

	@FindBy(xpath = "//*[@id='doc_Searchbox']")
	public static WebElement searchBoxInDocumentCustomization;

	@FindBy(xpath = "//*[@id='liSearchOn']/a")
	public static WebElement search_SearchOnBtn;

	@FindBy(xpath = "//*[@id='liAdvanceSearch']/a")
	public static WebElement search_AdvanceSearch;

	@FindBy(xpath = "//input[@id='cmbUserTypeMaster']")
	public static WebElement cmbSearchTxt;

	@FindBy(xpath = "//*[@id='cmbUserTypeMaster_input_image']/span")
	public static WebElement cmbSearchExpansionBtn;

	@FindBy(xpath = "//*[@id='cmbUserTypeMaster_input_settings']/span")
	public static WebElement cmbSearchSettingBtn;

	@FindBy(xpath = "//a[@id='chkResizeGrid0']")
	public static WebElement resizeGridBtn;

	@FindBy(xpath = "//input[@id='chkRetainSelection']")
	public static WebElement retainSelectionCheckbox;

	@FindBy(xpath = "//label[@id='oncheckaccorderan0']")
	public static WebElement checkAccorderanBtn;

	@FindBy(xpath = "//*[@id='iTreeId0']")
	public static WebElement treeIdDropdown;

	@FindBy(xpath = "//*[@id='iTreeViewId0']")
	public static WebElement treeViewIdDropdown;

	// WareHouse Master

	@FindBy(xpath = "//input[@id='iBins']")
	public static WebElement binsText;

	@FindBy(xpath = "//*[@id='iBins_input_image']/span")
	public static WebElement bins_ExpansionBtn;

	@FindBy(xpath = "//*[@id='iBins_input_settings']/span")
	public static WebElement bins_SettingBtn;

	@FindBy(xpath = "//input[@id='iPhysInventoryCountingFrequency']")
	public static WebElement physInventoryCountingFrequencyTxt;

	@FindBy(xpath = "//select[@id='iWarehouseType']")
	public static WebElement warehouseTypeDropdown;

	@FindBy(xpath = "//input[@id='bDontMaintainBin']")
	public static WebElement dontMaintainBinCheckBox;

	// State Master

	@FindBy(xpath = "//input[@id='iCountry']")
	public static WebElement countryTxt;

	@FindBy(xpath = "//*[@id='iCountry_input_image']/span")
	public static WebElement country_ExpansionBtn;

	@FindBy(xpath = "//*[@id='iCountry_input_settings']/span")
	public static WebElement country_SettingBtn;

	// City Master

	@FindBy(xpath = "//input[@id='iState']")
	public static WebElement stateTxt;

	@FindBy(xpath = "//*[@id='iState_input_image']/span")
	public static WebElement state_ExpansionBtn;

	@FindBy(xpath = "//*[@id='iState_input_settings']/span")
	public static WebElement state_SettingBtn;

	// Master Delete Field

	@FindBy(xpath = "//*[@id='LandingGridBody']/tr[1]/td[11]")
	public static WebElement accountNewCreationNameOfLane1;

	@FindBy(xpath = "/html[1]/body[1]/section[1]/div[2]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[2]/div[1]/table[1]/tbody[1]/tr[1]/td[1]/div[1]/table[1]/tbody[1]/tr[2]/td[11]")
	public static WebElement accountNewCreationNameOfLane2;

	@FindBy(xpath = "//*[@id='LandingGridBody']/tr[1]/td[11]")
	public static WebElement masterCreationNameOfLane1;

	@FindBy(xpath = "//*[@id='LandingGridBody']/tr[2]/td[11]")
	public static WebElement masterCreationNameOfLane2;

	@FindBy(xpath = "//*[@id='LandingGridBody']/tr[1]/td[12]")
	public static WebElement masterCreationCodeOfLane1;

	@FindBy(xpath = "//*[@id='LandingGridBody']/tr[2]/td[12]")
	public static WebElement masterCreationCodeOfLane2;

	@FindBy(xpath = "//*[@id='LandingGridBody']/tr[1]/td[13]")
	public static WebElement masterCreationTypeOfLane1;

	@FindBy(xpath = "//*[@id='LandingGridBody']/tr[2]/td[13]")
	public static WebElement masterCreationTypeOfLane2;

	@FindBy(xpath = "/html[1]/body[1]/section[1]/div[2]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[2]/div[1]/table[1]/tbody[1]/tr[1]/td[1]/div[1]/table[1]/tbody[1]/tr[1]/td[8]/div[1]/label[1]/input[1]")
	public static WebElement accountFirstCheckBoxToSelection;

	@FindBy(xpath = "/html[1]/body[1]/section[1]/div[2]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/ol[1]/li[5]/span[1]")
	public static WebElement accountGroupTitleDisplay;

	@FindBy(xpath = "/html[1]/body[1]/section[1]/div[2]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/ol[1]/li[2]/span[1]")
	public static WebElement productGroupTitleDisplay;

	// Master Customization

	@FindBy(xpath = "//i[@class='icon-expand']")
	private static WebElement plusBtn;

	@FindBy(xpath = "//i[@class='icon-collepse']")
	private static WebElement minusBtn;

	@FindBy(xpath = "//span[contains(text(),'Master Fields')]")
	private static WebElement masterFieldsOption;

	@FindBy(xpath = "//*[@id='masterFields_list']/li[1]/span")
	private static WebElement generalTab;

	@FindBy(xpath = "//span[@class='font-5'][contains(text(),'Main')]")
	private static WebElement generalMainTab;

	@FindBy(xpath = "//div[@id='mainTab_0']//tbody//tr[1]//td[4]")
	private static WebElement accountCustomizeName;

	@FindBy(xpath = "//div[@id='mainTab_0']//tr[2]//td[4]")
	private static WebElement accountCustomizeCode;

	@FindBy(xpath = "//div[@id='mainTab_0']//tr[3]//td[4]")
	private static WebElement accountCustomizeAccountType;

	@FindBy(xpath = "//div[@id='mainTab_0']//tr[4]//td[4]")
	private static WebElement accountCustomizeCreditLimit;

	@FindBy(xpath = "//div[@id='mainTab_0']//tr[5]//td[4]")
	private static WebElement accountCustomizeCreditDays;

	@FindBy(xpath = "//div[@id='tabId_1']//span[@class='font-5'][contains(text(),'Header Details')]")
	private static WebElement generalHeaderDetailsTab;

	@FindBy(xpath = "//div[@id='tabId_1']//tbody[contains(@class,'ui-sortable')]//tr[1]//td[4]")
	private static WebElement accountCustomizeChequeDiscountLimit;

	@FindBy(xpath = "//div[@id='HeaderDetails_0']//tr[2]//td[4]")
	private static WebElement accountCustomizeRateOfInterest;

	@FindBy(xpath = "//*[@id='HeaderDetails_0']/div/div/table/tbody/tr[3]/td[4]")
	private static WebElement accountCustomizeBankAc;

	@FindBy(xpath = "//*[@id='HeaderDetails_0']/div/div/table/tbody/tr[4]/td[4]")
	private static WebElement accountCustomizePDCDiscountedAccount;

	@FindBy(xpath = "//*[@id='btnMove_Field_MasterCust']/div[1]/span")
	private static WebElement accountHeaderMoveFiledBtn;

	@FindBy(xpath = "//div[contains(text(),'Preview')]")
	private static WebElement accountHeaderPreview;

	@FindBy(xpath = "//div[@class='clsFieldButtons_MCust_div']//div[@class='col-sm-12 btnheader_txt font-7'][contains(text(),'Save')]")
	private static WebElement accountSaveBtn;

	@FindBy(xpath = "//*[@id='btnAdd_Field_MasterCust']/div[1]/span")
	private static WebElement accountAddBtn;

	@FindBy(xpath = "//*[@id='btnCloseMasterCutomizationFromMasterScreen']/div[1]/span")
	private static WebElement accountCloseBtn;

	@FindBy(xpath = "//span[@class='Flabel theme_icon-color'][contains(text(),'Settings')]")
	private static WebElement settingOption;

	@FindBy(xpath = "//div[@id='tabId_68']//span[@class='font-5'][contains(text(),'Header Details')]")
	private static WebElement settingsHeaderDetailsTab;

	@FindBy(xpath = "//*[@id='HeaderDetails_1']/div/div/table/tbody/tr[1]/td[1]/i[1]")
	private static WebElement accountSettingsHeaderEditFirstRowBtn;

	@FindBy(xpath = "//*[@id='HeaderDetails_1']/div/div/table/tbody/tr[2]/td[1]/i[1]")
	private static WebElement accountSettingsHeaderEditSecondRowBtn;

	@FindBy(xpath = "//*[@id='HeaderDetails_1']/div/div/table/tbody/tr[3]/td[1]/i[1]")
	private static WebElement accountSettingsHeaderEditThirdRowBtn;

	@FindBy(xpath = "//*[@id='HeaderDetails_1']/div/div/table/tbody/tr[4]/td[1]/i[1]")
	private static WebElement accountSettingsHeaderEditFourthRowBtn;

	@FindBy(xpath = "//*[@id='HeaderDetails_1']/div/div/table/tbody/tr[5]/td[1]/i[1]")
	private static WebElement accountSettingsHeaderEditFifthRowBtn;

	@FindBy(xpath = "//*[@id='HeaderDetails_1']/div/div/table/tbody/tr[6]/td[1]/i[1]")
	private static WebElement accountSettingsHeaderEditSixthRowBtn;

	@FindBy(xpath = "//*[@id='HeaderDetails_1']/div/div/table/tbody/tr[7]/td[1]/i[1]")
	private static WebElement accountSettingsHeaderEditSeventhRowBtn;

	@FindBy(xpath = "//*[@id='HeaderDetails_1']/div/div/table/tbody/tr[8]/td[1]/i[1]")
	private static WebElement accountSettingsHeaderEditEighthRowBtn;

	@FindBy(xpath = "//div[contains(text(),'Move Field')]")
	private static WebElement accountSettingsHeaderMoveFiledBtn;

	@FindBy(xpath = "//div[contains(text(),'Preview')]")
	private static WebElement accountSettingsHeaderPreview;

	@FindBy(xpath = "//div[@class='clsFieldButtons_MCust_div']//div[@class='col-sm-12 btnheader_txt font-7'][contains(text(),'Save')]")
	private static WebElement accountSettingsSaveBtn;

	@FindBy(xpath = "//div[@id='btnAdd_Field_MasterCust']//div[@class='col-sm-12 btnheader_txt font-7'][contains(text(),'Add')]")
	private static WebElement accountSettingsAddBtn;

	@FindBy(xpath = "//div[@class='clsFieldButtons_MCust_div']//div[@class='col-sm-12 btnheader_txt font-7'][contains(text(),'Close')]")
	private static WebElement accountSettingsCloseBtn;

	@FindBy(xpath = "//div[@id='tabId_68']//span[@class='font-5'][contains(text(),'Body Details')]")
	private static WebElement settingsBodyDetailsTab;

	@FindBy(xpath = "//div[@id='BodyDetails_1']//div[@class='scrollable tbl-emptyfields-mastercust']")
	private static WebElement settingsBodyDetailsEmptyTab;

	@FindBy(xpath = "//span[@class='Flabel theme_icon-color'][contains(text(),'Details')]")
	private static WebElement accountMasterFieldDetailsOption;

	@FindBy(xpath = "//div[@id='tabId_69']//span[@class='font-5'][contains(text(),'Header Details')]")
	private static WebElement detailsHeaderDetailsTab;

	@FindBy(xpath = "//*[@id='HeaderDetails_2']/div/div/table/tbody/tr[1]/td[4]")
	private static WebElement detailsHeaderAddress;

	@FindBy(xpath = "//*[@id='HeaderDetails_2']/div/div/table/tbody/tr[2]/td[4]")
	private static WebElement detailsHeaderCity;

	@FindBy(xpath = "//*[@id='HeaderDetails_2']/div/div/table/tbody/tr[3]/td[4]")
	private static WebElement detailsHeaderPin;

	@FindBy(xpath = "//*[@id='HeaderDetails_2']/div/div/table/tbody/tr[4]/td[4]")
	private static WebElement detailsHeaderDeliveryAddress;

	@FindBy(xpath = "//*[@id='HeaderDetails_2']/div/div/table/tbody/tr[5]/td[4]")
	private static WebElement detailsHeaderCityOne;

	@FindBy(xpath = "//*[@id='HeaderDetails_2']/div/div/table/tbody/tr[6]/td[4]")
	private static WebElement detailsHeaderPinOne;

	@FindBy(xpath = "//*[@id='HeaderDetails_2']/div/div/table/tbody/tr[7]/td[4]")
	private static WebElement detailsHeaderSendEmailToCustomer;

	@FindBy(xpath = "//*[@id='HeaderDetails_2']/div/div/table/tbody/tr[8]/td[4]")
	private static WebElement detailsHeaderAllowCustomerPortal;

	@FindBy(xpath = "//*[@id='HeaderDetails_2']/div/div/table/tbody/tr[9]/td[4]")
	private static WebElement detailsHeaderEmail;

	@FindBy(xpath = "//*[@id='HeaderDetails_2']/div/div/table/tbody/tr[10]/td[4]")
	private static WebElement detailsHeaderPassword;

	@FindBy(xpath = "//*[@id='HeaderDetails_2']/div/div/table/tbody/tr[11]/td[4]")
	private static WebElement detailsHeaderTelNo;

	@FindBy(xpath = "//*[@id='HeaderDetails_2']/div/div/table/tbody/tr[12]/td[4]")
	private static WebElement detailsHeaderFaxNo;

	@FindBy(xpath = "//*[@id='HeaderDetails_2']/div/div/table/tbody/tr[13]/td[4]")
	private static WebElement detailsHeaderBankAccountName;

	@FindBy(xpath = "//*[@id='HeaderDetails_2']/div/div/table/tbody/tr[14]/td[4]")
	private static WebElement detailsHeaderBankAccountNumber;

	@FindBy(xpath = "//*[@id='HeaderDetails_2']/div/div/table/tbody/tr[15]/td[4]")
	private static WebElement detailsHeaderIFSCCode;

	@FindBy(xpath = "//*[@id='HeaderDetails_2']/div/div/table/tbody/tr[16]/td[4]")
	private static WebElement detailsHeaderPaymentType;

	@FindBy(xpath = "//*[@id='HeaderDetails_2']/div/div/table/tbody/tr[17]/td[4]")
	private static WebElement detailsHeaderFinanceEmail;

	@FindBy(xpath = "//*[@id='HeaderDetails_2']/div/div/table/tbody/tr[18]/td[4]")
	private static WebElement detailsHeaderPortalEmail;

	@FindBy(xpath = "//div[@class='clsFieldButtons_MCust_div']//div[@class='col-sm-12 btnheader_txt font-7'][contains(text(),'Save')]")
	private static WebElement detailsHeaderBankSaveBtn;

	@FindBy(xpath = "//div[contains(text(),'Move Field')]")
	private static WebElement detailsHeaderMoveFieldBtn;

	@FindBy(xpath = "//div[@id='btnAdd_Field_MasterCust']//div[@class='col-sm-12 btnheader_txt font-7'][contains(text(),'Add')]")
	private static WebElement detailsHeaderAddBtn;

	@FindBy(xpath = "//div[contains(text(),'Preview')]")
	private static WebElement detailsHeaderPreviewBtn;

	@FindBy(xpath = "//div[@class='clsFieldButtons_MCust_div']//div[@class='col-sm-12 btnheader_txt font-7'][contains(text(),'Close')]")
	private static WebElement detailsHeaderCloseBtn;

	@FindBy(xpath = "//div[@id='tabId_69']//span[@class='font-5'][contains(text(),'Body Details')]")
	private static WebElement detailsBodyDetailsTab;

	@FindBy(xpath = "//div[@id='BodyDetails_2']//div[@class='scrollable tbl-emptyfields-mastercust']")
	private static WebElement detailsBodyDetailsEmptyTab;

	@FindBy(xpath = "//*[@id='masterFields_list']/li[4]/span")
	private static WebElement accountMasterFieldPrintLayoutOption;

	@FindBy(xpath = "//div[@id='tabId_70']//span[@class='font-5'][contains(text(),'Header Details')]")
	private static WebElement printlayoutHeaderDetailsTab;

	@FindBy(xpath = "//div[@id='HeaderDetails_3']//div[@class='scrollable tbl-emptyfields-mastercust']")
	private static WebElement printLayoutHeaderDetailsEmpty;

	@FindBy(xpath = "//div[@id='tabId_70']//li[2]//a[1]")
	private static WebElement printLayouBodyDetailsTab;

	@FindBy(xpath = "//*[@id='BodyDetails_3']/div/div/table/tbody/tr[1]/td[4]")
	private static WebElement printlayoutBodyVoucherType;

	@FindBy(xpath = "//*[@id='BodyDetails_3']/div/div/table/tbody/tr[2]/td[4]")
	private static WebElement printLayoutBodyPrintlayout;

	@FindBy(xpath = "//div[@class='clsFieldButtons_MCust_div']//div[@class='col-sm-12 btnheader_txt font-7'][contains(text(),'Save')]")
	private static WebElement printLayoutSaveBtn;

	@FindBy(xpath = "//div[@id='btnAdd_Field_MasterCust']//div[@class='col-sm-12 btnheader_txt font-7'][contains(text(),'Add')]")
	private static WebElement printLayoutAddBtn;

	@FindBy(xpath = "//div[contains(text(),'Preview')]")
	private static WebElement printLayoutPreviewBtn;

	@FindBy(xpath = "//div[@class='clsFieldButtons_MCust_div']//div[@class='col-sm-12 btnheader_txt font-7'][contains(text(),'Close')]")
	private static WebElement printLayoutCloseBtn;

	@FindBy(xpath = "//span[contains(text(),'Unique Constraints')]")
	private static WebElement accountMasterFieldsUniqueConstraintsOption;

	@FindBy(xpath = "//div[@id='tabContent_UConstraints_MasterCust']//div[@id='btnAddOrEdit_UniqueConstraints']")
	private static WebElement UniqueConstraintsAddBtn;

	@FindBy(xpath = "//div[@id='tabContent_UConstraints_MasterCust']//div[@class='col-sm-12 btnheader_txt font-7'][contains(text(),'Delete')]")
	private static WebElement UniqueConstraintsDeleteBtn;

	@FindBy(xpath = "//div[@id='tabContent_UConstraints_MasterCust']//div[@class='col-sm-12 btnheader_txt font-7'][contains(text(),'Close')]")
	private static WebElement UniqueConstraintsCloseBtn;

	@FindBy(xpath = "//input[@id='txtConstraintName']")
	private static WebElement ConstraintNameTxt;

	@FindBy(xpath = "//select[@id='ddlConstraintTabList']")
	private static WebElement UniqueConstraints_TabsDropdown;

	@FindBy(xpath = "//*[@id='tab_Main_UcFields']/i")
	private static WebElement UniqueConstraints_MainExpansionBtn;

	@FindBy(xpath = "//*[@id='chkList_MainFields_1']/div[1]")
	private static WebElement accountUniqueConstraints_Main_NameChekbox;

	@FindBy(xpath = "//div[@id='chkList_MainFields_1']//div[2]")
	private static WebElement accountUniqueConstraints_Main_CodeChekbox;

	@FindBy(xpath = "//div[@id='chkList_MainFields_1']//div[3]")
	private static WebElement accountUniqueConstraints_Main_AccountTypeCheckbox;

	@FindBy(xpath = "//div[@id='chkList_MainFields_1']//div[4]")
	private static WebElement accountUniqueConstraints_Main_CreditLimitCheckBox;

	@FindBy(xpath = "//div[@id='chkList_MainFields_1']//div[5]")
	private static WebElement accountUniqueConstraints_Main_CreditDays;

	@FindBy(xpath = "//div[@id='tab_Header_UcFields']//i[@class='pull-right icon-expand theme_icon-color']")
	private static WebElement UniqueConstraints_HeaderExpansionBtn;

	@FindBy(xpath = "//*[@id='chkList_HeaderFields_1']/div[1]")
	private static WebElement UniqueConstraints_Header_ChequeDiscountLimitChekbox;

	@FindBy(xpath = "//*[@id='chkList_HeaderFields_1']/div[2]")
	private static WebElement UniqueConstraints_Header_RateOfInterestChekbox;

	@FindBy(xpath = "//*[@id='chkList_HeaderFields_1']/div[3]")
	private static WebElement UniqueConstraints_Header_BankAcChekbox;

	@FindBy(xpath = "//*[@id='chkList_HeaderFields_1']/div[4]")
	private static WebElement UniqueConstraints_Header_PDCDiscountedChekbox;

	@FindBy(xpath = "//*[@id='tab_Body_UcFields']/i")
	private static WebElement UniqueConstraints_BodyExpansionBtn;

	@FindBy(xpath = "//span[contains(text(),'Rules')]")
	private static WebElement RulesOption;

	@FindBy(xpath = "//*[@id='newRuleTab_div_MasterRules']/div[1]/div/div[1]/div[1]")
	private static WebElement accountRules_SaveBtn;

	@FindBy(xpath = "//div[contains(text(),'New')]")
	private static WebElement accountRules_NewBtn;

	@FindBy(xpath = "/html[1]/body[1]/section[1]/div[2]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div[3]/div[1]/div[1]/div[1]/div[1]/div[3]/div[2]")
	private static WebElement accountRules_DeleteBtn;

	@FindBy(xpath = "/html[1]/body[1]/section[1]/div[2]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div[3]/div[1]/div[1]/div[1]/div[1]/div[4]/div[2]")
	private static WebElement accountRules_CloseBtn;

	@FindBy(xpath = "//*[@id='txtMasterRuleName_MasterRules']")
	private static WebElement Rules_RuleNameText;

	@FindBy(xpath = "//*[@id='chkCreatingGroup_Rule_MasterRules']")
	private static WebElement Rules_CreatingGroupChekbox;

	@FindBy(xpath = "//*[@id='chkNewRecord_Rule_MasterRules']")
	private static WebElement Rules_NewRecordChekbox;

	@FindBy(xpath = "//*[@id='chkEdit_Rule_MasterRules']")
	private static WebElement Rules_EditChekbox;

	@FindBy(xpath = "//*[@id='chkLoad_Rule_MasterRules']")
	private static WebElement Rules_LoadChekbox;

	@FindBy(xpath = "//*[@id='chkBeforeSave_Rule_MasterRules']")
	private static WebElement Rules_BeforeSaveChekbox;

	@FindBy(xpath = "//*[@id='chkOnLeave_Rule_MasterRules']")
	private static WebElement Rules_OnLeaveChekbox;

	@FindBy(xpath = "//*[@id='chkBeforeDelete_Rule_MasterRules']")
	private static WebElement Rules_BeforeDeleteChekbox;

	@FindBy(xpath = "//*[@id='chkOnEnter_Rule_MasterRules']")
	private static WebElement Rules_OnEnterChekbox;

	@FindBy(xpath = "//label[contains(text(),'Active')]")
	private static WebElement Rules_ActiveChekbox;

	@FindBy(xpath = "//*[@id='Conditions-Context_Menu']/li[3]")
	private static WebElement Rules_NoConditionChekbox;

	@FindBy(xpath = "//*[@id='allMasterCustTabs']/li[4]/span")
	private static WebElement rules_ExternalModules;

	@FindBy(xpath = "//select[@id='ddlOnEvent_MasterExternalModule']")
	private static WebElement rules_ExternalModulesOnEventDropdown;

	@FindBy(xpath = "//input[@id='txtbuttonCaption_MasterExternalModule']")
	private static WebElement rules_ExternalModulesButtonCaption;

	@FindBy(xpath = "//select[@id='ddlModuleType_MasterExternalModule']")
	private static WebElement rules_ExternalModules_ModuleTypeBtn;

	@FindBy(xpath = "//select[@id='ddlAllDllFiles_MasterExternalModule']")
	private static WebElement rules_ExternalModules_ModuleNameDropdown;

	@FindBy(xpath = "//select[@id='ddlClassNames_MasterExternalModule']")
	private static WebElement rules_ExternalModulesClassNameDropdown;

	@FindBy(xpath = "//select[@id='ddlClassMethods_MasterExternalModule']")
	private static WebElement rules_ExternalModulesFuctionalNameDropdown;

	@FindBy(xpath = "//div[contains(text(),'Update')]")
	private static WebElement rules_ExternalModulesUpdateBtn;

	@FindBy(xpath = "//div[@id='ExternalModulesDiv_MasterExternalModule']//div[@class='col-sm-12 btnheader_txt font-7'][contains(text(),'Save')]")
	private static WebElement rules_ExternalModulesSaveBtn;

	@FindBy(xpath = "//div[contains(text(),'Edit')]")
	private static WebElement rules_ExternalModulesEditBtn;

	@FindBy(xpath = "//div[@id='ExternalModulesDiv_MasterExternalModule']//div[@class='col-sm-12 btnheader_txt font-7'][contains(text(),'Delete')]")
	private static WebElement rules_ExternalModulesDeleteBtn;

	@FindBy(xpath = "//div[@id='ExternalModulesDiv_MasterExternalModule']//div[@class='col-sm-12 btnheader_txt font-7'][contains(text(),'Close')]")
	private static WebElement rules_ExternalModulesCloseBtn;

	@FindBy(xpath = "//span[contains(text(),'Info Panel Customization')]")
	private static WebElement itemInfoPanelCustomizationTab;

	@FindBy(xpath = "//body[@class='custom_scrollbar']/section[@id='mainDiv']/div[@id='id_focus8_wrapper_default']/div[@class='content-wrapper']/section[@id='page_Content']/div[@class='container']/div[@id='mainLandingDiv']/div[@id='masterFirstDiv']/div[@class='tab-content']/div[@id='divLanding']/div[@id='divSecondsub']/div/div[@class='col-xs-12 col-sm-8 col-md-9 col-lg-9 padding-left-right5 mainDivCustomWidth']/div[@id='tabContent_IPCust_MasterCust']/div[@class='col-xs-12 col-sm-12 padding0']/div[@class='pull-right']/div[1]")
	private static WebElement iteminfoPanelCust_AddBtn;

	@FindBy(xpath = "//div[@id='tabContent_IPCust_MasterCust']//div[@class='col-sm-12 btnheader_txt font-7'][contains(text(),'Delete')]")
	private static WebElement infoPanelCust_DeleteBtn;

	@FindBy(xpath = "//div[@id='tabContent_IPCust_MasterCust']//div[@class='col-sm-12 btnheader_txt font-7'][contains(text(),'Close')]")
	private static WebElement infoPanelCust_CloseBtn;

	@FindBy(xpath = "//strong[contains(text(),'Credit History')]")
	private static WebElement infoPanelCustCreditHistory;

	@FindBy(xpath = "//div[@id='divPanel_2']//strong[contains(text(),'Ageing Analysis')]")
	private static WebElement infoPanelCustAgeingAnalysis;

	@FindBy(xpath = "//strong[contains(text(),'Ageing Analysis Base')]")
	private static WebElement infoPanelCustAgeingAnalysisBase;

	@FindBy(xpath = "//strong[contains(text(),'Ageing Analysis Local')]")
	private static WebElement infoPanelCustAgeingAnalysisLocal;

	@FindBy(xpath = "//strong[contains(text(),'Authorization')]")
	private static WebElement infoPanelAuthorization;

	@FindBy(xpath = "//span[@class='Flabel'][contains(text(),'Reports')]")
	private static WebElement accountReportsTab;

	@FindBy(xpath = "//div[@id='tabContent_Reports_MasterCust']//div[@class='col-sm-12 btnheader_txt font-7'][contains(text(),'Save')]")
	private static WebElement accountReports_SaveBtn;

	@FindBy(xpath = "//div[@id='tabContent_Reports_MasterCust']//div[@class='col-sm-12 btnheader_txt font-7'][contains(text(),'Close')]")
	private static WebElement accountReports_CloseBtn;

	@FindBy(xpath = "//*[@id='AllReportsDiv_MasterCust']/div[1]/label/input")
	private static WebElement reports_AdvancedbudgetReportChkBox;

	@FindBy(xpath = "//div[@id='AllReportsDiv_MasterCust']//div[1]//label[1]")
	private static WebElement reports_MasterInfoChkBox;

	@FindBy(xpath = "//div[@class='col-xs-12 col-sm-8 col-md-8 col-lg-9 padding0']//div[2]//label[1]")
	private static WebElement reports_ABCAnalysisAmountChkBox;

	@FindBy(xpath = "//div[@id='tabContent_Reports_MasterCust']//div[3]//label[1]")
	private static WebElement reports_BankBookChkBox;

	@FindBy(xpath = "//div[@id='tabContent_Reports_MasterCust']//div[4]//label[1]")
	private static WebElement reports_BankreconciliationstatementChkBox;

	@FindBy(xpath = "//div[@id='tabContent_Reports_MasterCust']//div[5]//label[1]")
	private static WebElement reports_CashBookChkBox;

	@FindBy(xpath = "//div[@id='tabContent_Reports_MasterCust']//div[6]//label[1]")
	private static WebElement reports_ComparativeAnalysisChkBox;

	@FindBy(xpath = "//div[@id='tabContent_Reports_MasterCust']//div[7]//label[1]")
	private static WebElement reports_CustomerAgeingDetailsAnalysisChkBox;

	@FindBy(xpath = "//div[@id='tabContent_Reports_MasterCust']//div[8]//label[1]")
	private static WebElement reports_CustomerageingdetailbyduedateChkBox;

	@FindBy(xpath = "//div[@id='tabContent_Reports_MasterCust']//div[9]//label[1]")
	private static WebElement reports_CustomerageingsummaryanalysisChkBox;

	@FindBy(xpath = "//div[@id='tabContent_Reports_MasterCust']//div[10]//label[1]")
	private static WebElement reports_CustomerbillwisesummaryChkBox;

	@FindBy(xpath = "//div[@id='tabContent_Reports_MasterCust']//div[11]//label[1]")
	private static WebElement reports_CustomerduedateanalysisChkBox;

	@FindBy(xpath = "//div[@id='tabContent_Reports_MasterCust']//div[12]//label[1]")
	private static WebElement reports_CustomerlistingofoutstandingbillsChkBox;

	@FindBy(xpath = "//div[@id='tabContent_Reports_MasterCust']//div[13]//label[1]")
	private static WebElement reports_CustomeroverdueanalysisChkBox;

	@FindBy(xpath = "//div[@id='tabContent_Reports_MasterCust']//div[14]//label[1]")
	private static WebElement reports_CustomeroverduesummaryChkBox;

	@FindBy(xpath = "//div[@id='tabContent_Reports_MasterCust']//div[15]//label[1]")
	private static WebElement reports_CustomerstatementsChkBox;

	@FindBy(xpath = "//div[@id='tabContent_Reports_MasterCust']//div[16]//label[1]")
	private static WebElement reports_CustomersummaryageingbyduedateChkBox;

	@FindBy(xpath = "//div[@id='tabContent_Reports_MasterCust']//div[17]//label[1]")
	private static WebElement reports_IncomeExpenseTrendChkBox;

	@FindBy(xpath = "//div[@id='tabContent_Reports_MasterCust']//div[18]//label[1]")
	private static WebElement reports_InterestcalculationChkBox;

	@FindBy(xpath = "//div[@id='tabContent_Reports_MasterCust']//div[19]//label[1]")
	private static WebElement reports_LedgerChkBox;

	@FindBy(xpath = "//div[@id='tabContent_Reports_MasterCust']//div[20]//label[1]")
	private static WebElement reports_LedgerdetailChkBox;

	@FindBy(xpath = "//div[@id='tabContent_Reports_MasterCust']//div[21]//label[1]")
	private static WebElement reports_MonthlySalesChkBox;

	@FindBy(xpath = "//div[@id='tabContent_Reports_MasterCust']//div[22]//label[1]")
	private static WebElement reports_PeakandlowbalanceamountChkBox;

	@FindBy(xpath = "//div[@id='tabContent_Reports_MasterCust']//div[23]//label[1]")
	private static WebElement reports_PettycashbookChkBox;

	@FindBy(xpath = "//div[@id='tabContent_Reports_MasterCust']//div[24]//label[1]")
	private static WebElement reports_PurchaseregisterChkBox;

	@FindBy(xpath = "//div[@id='tabContent_Reports_MasterCust']//div[25]//label[1]")
	private static WebElement reports_PurchasereturnregisterChkBox;

	@FindBy(xpath = "//div[@id='tabContent_Reports_MasterCust']//div[26]//label[1]")
	private static WebElement reports_PurchasesgroupedbydepartmentChkBox;

	@FindBy(xpath = "//div[@id='tabContent_Reports_MasterCust']//div[27]//label[1]")
	private static WebElement reports_PurchasesgroupedbyItemChkBox;

	@FindBy(xpath = "//div[@id='tabContent_Reports_MasterCust']//div[28]//label[1]")
	private static WebElement reports_PurchasesgroupedbyvendorChkBox;

	@FindBy(xpath = "//div[@id='tabContent_Reports_MasterCust']//div[29]//label[1]")
	private static WebElement reports_SalesdaybookChkBox;

	@FindBy(xpath = "//div[@id='tabContent_Reports_MasterCust']//div[30]//label[1]")
	private static WebElement reports_SalesgroupedbycustomerChkBox;

	@FindBy(xpath = "//div[@id='tabContent_Reports_MasterCust']//div[31]//label[1]")
	private static WebElement reports_salesgroupedbydepartmentrChkBox;

	@FindBy(xpath = "//div[@id='tabContent_Reports_MasterCust']//div[32]//label[1]")
	private static WebElement reports_SalesgroupedbyItemChkBox;

	@FindBy(xpath = "//div[@id='tabContent_Reports_MasterCust']//div[33]//label[1]")
	private static WebElement reports_SalesreturnregisterChkBox;

	@FindBy(xpath = "//div[@id='tabContent_Reports_MasterCust']//div[34]//label[1]")
	private static WebElement reports_SchedulesChkBox;

	@FindBy(xpath = "//div[@id='tabContent_Reports_MasterCust']//div[35]//label[1]")
	private static WebElement reports_SubledgerChkBox;

	@FindBy(xpath = "//div[@id='tabContent_Reports_MasterCust']//div[36]//label[1]")
	private static WebElement reports_SummarypurchaseregisterChkBox;

	@FindBy(xpath = "//div[@id='tabContent_Reports_MasterCust']//div[37]//label[1]")
	private static WebElement reports_SummarysalesregisterChkBox;

	@FindBy(xpath = "//div[@id='tabContent_Reports_MasterCust']//div[38]//label[1]")
	private static WebElement reports_TopCustomersChkBox;

	@FindBy(xpath = "//div[@id='tabContent_Reports_MasterCust']//div[39]//label[1]")
	private static WebElement reports_TradingaccountrChkBox;

	@FindBy(xpath = "//div[40]//label[1]")
	private static WebElement reports_TransactionstypeanalysisChkBox;

	@FindBy(xpath = "//div[@id='tabContent_Reports_MasterCust']//div[41]//label[1]")
	private static WebElement reports_VendorageingdetailanalysisChkBox;

	@FindBy(xpath = "//div[@id='tabContent_Reports_MasterCust']//div[42]//label[1]")
	private static WebElement reports_VendorageingdetailbyduedateChkBox;

	@FindBy(xpath = "//div[@id='tabContent_Reports_MasterCust']//div[43]//label[1]")
	private static WebElement reports_VendorageingsummaryanalysisChkBox;

	@FindBy(xpath = "//div[@id='tabContent_Reports_MasterCust']//div[44]//label[1]")
	private static WebElement reports_VendorbillwisesummaryChkBox;

	@FindBy(xpath = "//div[@id='tabContent_Reports_MasterCust']//div[45]//label[1]")
	private static WebElement reports_VendorduedateanalysisChkBox;

	@FindBy(xpath = "//div[@id='tabContent_Reports_MasterCust']//div[46]//label[1]")
	private static WebElement reports_VendorlistingofoutstandingbillsChkBox;

	@FindBy(xpath = "//div[@id='tabContent_Reports_MasterCust']//div[47]//label[1]")
	private static WebElement reports_VendoroverdueanalysisChkBox;

	@FindBy(xpath = "//div[@id='tabContent_Reports_MasterCust']//div[48]//label[1]")
	private static WebElement reports_VendoroverduesummaryChkBox;

	@FindBy(xpath = "//div[@id='tabContent_Reports_MasterCust']//div[49]//label[1]")
	private static WebElement reports_VendorstatementsChkBox;

	@FindBy(xpath = "//div[@id='tabContent_Reports_MasterCust']//div[50]//label[1]")
	private static WebElement reports_VendorsummaryageingbyduedateChkBox;

	// Create Tabs
	@FindBy(xpath = "//button[@id='btnCreateTab_MCust']")
	private static WebElement createTab;

	@FindBy(xpath = "//a[contains(text(),'Tree Tab')]")
	private static WebElement createTabTreeTab;

	@FindBy(xpath = "//input[@id='txtNewTreeTabCaption']")
	private static WebElement treeTabCaptionTxt;

	@FindBy(xpath = "//input[@id='txtNewTreeTabName']")
	private static WebElement treeTabTabNameTxt;

	@FindBy(xpath = "//input[@id='fOption_tab_masterName']")
	private static WebElement treeTabNewMastertxt;

	@FindBy(xpath = "//*[@id='fOption_tab_masterName_input_image']/span")
	private static WebElement treeTabNewMasterdropdown;

	@FindBy(xpath = "//*[@id='fOption_tab_masterName_input_settings']/span")
	private static WebElement treeTab_NewMasterSettingsBtn;

	@FindBy(xpath = "//input[@id='txtNewTreeTabFieldCaption']")
	private static WebElement treeTab_FieldCaptiontxt;

	@FindBy(xpath = "//input[@id='txtNewTreeTabFieldName']")
	private static WebElement treeTab_FieldNameTxt;

	@FindBy(xpath = "//input[@id='bIsTreeHiddenTab']")
	private static WebElement treeTab_HiddenChkBox;

	@FindBy(xpath = "//div[@id='fOption_tab_masterName_customize_popup_container']")
	private static WebElement treeTab_NewMaster_ContainerTxt;

	@FindBy(xpath = "//div[@id='fOption_tab_masterName_customize_popup_footer']//input[1]")
	private static WebElement treeTab_NewMaster_StanadrdfieldsBtn;

	@FindBy(xpath = "//select[@id='fOption_tab_masterName_customize_popup_standardfields_list']")
	private static WebElement treeTab_NewMaster_Stanadrdfields_Fielddropdown;

	@FindBy(xpath = "//input[@id='fOption_tab_masterName_customize_popup_standardfields_header']")
	private static WebElement treeTab_NewMaster_Stanadrdfields_headerTxt;

	@FindBy(xpath = "//select[@id='fOption_tab_masterName_customize_popup_standardfields_alignment']")
	private static WebElement treeTab_NewMaster_Stanadrdfields_Allignmentdropdown;

	@FindBy(xpath = "//input[@id='fOption_tab_masterName_customize_popup_standardfields_width']")
	private static WebElement treeTab_NewMaster_Stanadrdfields_WidthTxt;

	@FindBy(xpath = "//div[@id='divSecondsub']//input[3]")
	private static WebElement treeTab_NewMaster_Stanadrdfields_OkBtn;

	@FindBy(xpath = "//div[@id='divSecondsub']//input[4]")
	private static WebElement treeTab_NewMaster_Stanadrdfields_CancelBtn;

	@FindBy(xpath = "//div[@id='fOption_tab_masterName_search_container']//input[2]")
	private static WebElement treeTab_NewMaster_deleteColumnBtn;

	@FindBy(xpath = "//div[@id='divSecondsub']//input[3]")
	private static WebElement treeTab_NewMaster_OkBtn;

	@FindBy(xpath = "//div[@id='divSecondsub']//input[4]")
	private static WebElement treeTab_NewMaster_CancelBtn;

	@FindBy(xpath = "//*[@id='MCustomize_CreateTreeTab_Modal']/div/div/div[3]/button[1]")
	private static WebElement treeTab_OkBtn;

	@FindBy(xpath = "//div[@id='MCustomize_EditTab_Modal']//button[@class='Fbutton font-5'][contains(text(),'OK')]")
	private static WebElement treeTab_UpdateOkBtn;

	@FindBy(xpath = "//*[@id='MCustomize_CreateTreeTab_Modal']/div/div/div[3]/button[2]")
	private static WebElement treeTab_CancelBtn;

	@FindBy(xpath = "//a[contains(text(),'Document Tab')]")
	private static WebElement createTab_DocumentTab;

	@FindBy(xpath = "//input[@id='txtNewDocTabCaption']")
	private static WebElement documentTab_TabCaption;

	@FindBy(xpath = "//input[@id='txtNewDocTabName']")
	private static WebElement documentTab_TabName;

	@FindBy(xpath = "//input[@id='bIsDocHiddenTab']")
	private static WebElement documentTab_HiddenChkbox;

	@FindBy(xpath = "//div[@id='MCustomize_CreateDocTab_Modal']//button[@class='Fbutton font-5'][contains(text(),'OK')]")
	private static WebElement documentTab_OkBtn;

	@FindBy(xpath = "//div[@id='MCustomize_CreateDocTab_Modal']//button[@class='Fbutton font-5'][contains(text(),'Cancel')]")
	private static WebElement documentTab_CancelBtn;

	@FindBy(xpath = "//label[@id='lblNameError_DocTab']")
	private static WebElement customizeMasterCreateTabDocumentTabMessage;

	@FindBy(xpath = "//span[contains(text(),'Document Tab')]")
	private static WebElement customizeMasterCreateDocumentTabNewlyCreate;

	@FindBy(xpath = "/html[1]/body[1]/section[1]/div[2]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div[1]/div[1]/div[6]/ul[1]/li[1]/a[1]/span[1]")
	private static WebElement customizeMasterCreateDocumentTabNewlyHeaderDetails;

	@FindBy(xpath = "//div[@id='HeaderDetails_5']//div[@class='scrollable tbl-emptyfields-mastercust']")
	private static WebElement customizeMasterCreateDocumentTabNewlyBlankInHeaderDetails;

	@FindBy(xpath = "/html[1]/body[1]/section[1]/div[2]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div[1]/div[1]/div[6]/ul[1]/li[2]/a[1]/span[1]")
	private static WebElement customizeMasterCreateDocumentTabNewlyBodyDetails;

	@FindBy(xpath = "/html[1]/body[1]/section[1]/div[2]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div[1]/div[1]/div[6]/div[1]/div[2]/div[1]/div[1]/table[1]/tbody[1]/tr[1]/td[4]")
	private static WebElement customizeMasterCreateDocumentTabFieldName;

	@FindBy(xpath = "/html[1]/body[1]/section[1]/div[2]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div[1]/div[1]/div[6]/div[1]/div[2]/div[1]/div[1]/table[1]/tbody[1]/tr[2]/td[4]")
	private static WebElement customizeMasterCreateDocumentTabDocument;

	@FindBy(xpath = "/html[1]/body[1]/section[1]/div[2]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div[1]/div[1]/div[6]/div[1]/div[2]/div[1]/div[1]/table[1]/tbody[1]/tr[3]/td[4]")
	private static WebElement customizeMasterCreateDocumentTabCreatedDate;

	@FindBy(xpath = "/html[1]/body[1]/section[1]/div[2]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div[1]/div[1]/div[6]/div[1]/div[2]/div[1]/div[1]/table[1]/tbody[1]/tr[4]/td[4]")
	private static WebElement customizeMasterCreateDocumentTabLastModified;

	@FindBy(xpath = "/html[1]/body[1]/section[1]/div[2]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div[1]/div[1]/div[6]/div[1]/div[2]/div[1]/div[1]/table[1]/tbody[1]/tr[5]/td[4]")
	private static WebElement customizeMasterCreateDocumentFileSize;

	@FindBy(xpath = "//a[contains(text(),'Create Tab')]")
	private static WebElement createTab_CreateTab;

	@FindBy(xpath = "//div[@id='MCustomize_CreateDocTab_Modal']//h4[@class='modal-title'][contains(text(),'Tab Details')]")
	private static WebElement createTab_TabDetailsLabel;

	@FindBy(xpath = "//input[@id='txtNewTabCaption']")
	private static WebElement createTab_TabCaptionTxt;

	@FindBy(xpath = "//input[@id='txtNewTabName']")
	private static WebElement createTab_TabNameTxt;

	@FindBy(xpath = "//input[@id='bIsHiddenTab']")
	private static WebElement createTab_PopHiddenChkbox;

	@FindBy(xpath = "//*[@id='MCustomize_CreateTab_Modal']/div/div/div[3]/button[1]")
	private static WebElement createTab_PopOkBtn;

	@FindBy(xpath = "//*[@id='MCustomize_CreateTab_Modal']/div/div/div[3]/button[2]")
	private static WebElement createTab_PopCancelBtn;

	@FindBy(xpath = "//label[@id='lblNameError_NewTab']")
	private static WebElement customizeMasterCreateTabCreateTabMessage;

	// Newly Created Create Tab
	@FindBy(xpath = "//span[contains(text(),'Create Tab')]")
	private static WebElement customizeMasterCreateCreateTabNewlyCreateTabCaption;

	@FindBy(xpath = "/html[1]/body[1]/section[1]/div[2]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div[1]/div[1]/div[7]/ul[1]/li[1]/a[1]/span[1]")
	private static WebElement customizeMasterCreateCreateTabHeaderDetails;

	@FindBy(xpath = "/html[1]/body[1]/section[1]/div[2]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div[1]/div[1]/div[7]/div[1]/div[1]/div[1]/div[1]")
	private static WebElement customizeMasterCreateCreateTabBlankHeaderDetails;

	@FindBy(xpath = "/html[1]/body[1]/section[1]/div[2]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div[1]/div[1]/div[7]/ul[1]/li[2]/a[1]/span[1]")
	private static WebElement customizeMasterCreateCreateTabBodyDetails;

	@FindBy(xpath = "/html[1]/body[1]/section[1]/div[2]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div[1]/div[1]/div[7]/div[1]/div[2]/div[1]/div[1]")
	private static WebElement customizeMasterCreateCreateTabBlankBodyDetails;

	@FindBy(xpath = "/html[1]/body[1]/section[1]/div[2]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div[1]/div[1]/div[8]/div[1]/div[2]")
	private static WebElement customizeMasterCreateCreateTabNewlyCreateTabSaveButton;

	@FindBy(xpath = "/html[1]/body[1]/section[1]/div[2]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div[1]/div[1]/div[8]/div[3]/div[2]")
	private static WebElement customizeMasterCreateCreateTabAddButton;

	@FindBy(xpath = "/html[1]/body[1]/section[1]/div[2]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div[1]/div[1]/div[8]/div[4]/div[2]")
	private static WebElement customizeMasterCreateCreateTabPreviewButton;

	@FindBy(xpath = "/html[1]/body[1]/section[1]/div[2]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div[1]/div[1]/div[8]/div[5]/div[2]")
	private static WebElement customizeMasterCreateCreateTabCloseButton;

	@FindBy(xpath = "//button[@id='btnDeleteTab_MCust']")
	private static WebElement tabs_DeleteBtn;

	@FindBy(xpath = "//label[@id='lblNameError_TreeTab']")
	private static WebElement customizeMasterCreateTabsMessage;

	@FindBy(xpath = "//label[@id='lblMasterNameError_TreeTab']")
	private static WebElement customizeMasterCreateTabsMasterNameMessage;

	@FindBy(xpath = "//span[contains(text(),'Tree Tab')]")
	private static WebElement customizeMasterCreateTreeTabNewlyCreate;

	@FindBy(xpath = "//input[@id='txtEditTabCaption']")
	private static WebElement customizeMasterCreateTreeTabNewlyCreateTabCaption;

	@FindBy(xpath = "//div[@id='MCustomize_EditTab_Modal']//label[@class='col-sm-12 col-xs-12 col-md-5 col-lg-5 form-control-static Flabel'][contains(text(),'Hidden')]")
	private static WebElement customizeMasterCreateTreeTabNewlyCreateHidden;

	@FindBy(xpath = "//div[@id='MCustomize_EditTab_Modal']//button[@class='Fbutton font-5'][contains(text(),'OK')]")
	private static WebElement customizeMasterCreateTreeTabNewlyCreateOkButton;

	@FindBy(xpath = "//div[@id='MCustomize_EditTab_Modal']//button[@class='Fbutton font-5'][contains(text(),'Cancel')]")
	private static WebElement customizeMasterCreateTreeTabNewlyCreateCancelButton;

	@FindBy(xpath = "/html[1]/body[1]/section[1]/div[2]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div[1]/div[1]/div[5]/ul[1]/li[1]/a[1]/span[1]")
	private static WebElement customizeMasterCreateTreeTabNewlyHeaderDetails;

	@FindBy(xpath = "//div[@id='HeaderDetails_4']//div[@class='scrollable tbl-emptyfields-mastercust']")
	private static WebElement customizeMasterCreateTreeTabNewlyBlankInHeaderDetails;

	@FindBy(xpath = "/html[1]/body[1]/section[1]/div[2]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div[1]/div[1]/div[5]/ul[1]/li[2]/a[1]/span[1]")
	private static WebElement customizeMasterCreateTreeTabNewlyBodyDetails;

	@FindBy(xpath = "/html[1]/body[1]/section[1]/div[2]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div[1]/div[1]/div[5]/div[1]/div[2]/div[1]/div[1]/table[1]/tbody[1]/tr[1]/td[4]")
	private static WebElement customizeMasterCreateTreeTabNewlyFieldInBodyDetails;

	@FindBy(xpath = "//span[contains(text(),'Tree Tab Delete')]")
	private static WebElement CustomizeCreateTreeTab;

	@FindBy(xpath = "//*[@id='lblWarnMessage_TabModal']")
	private static WebElement CustomizeDeleteTabMessage;

	@FindBy(xpath = "//div[@id='MCustomize_DeleteTab_Modal']//button[@class='Fbutton font-5'][contains(text(),'Yes')]")
	private static WebElement CustomizeDeleteTabMessageYesBtn;

	@FindBy(xpath = "//div[@id='MCustomize_DeleteTab_Modal']//button[@class='Fbutton font-5'][contains(text(),'No')]")
	private static WebElement CustomizeDeleteTabMessageNoBtn;

	// Master Customization Extra Fields Creation On Click Add button
	@FindBy(xpath = "//input[@id='ExtraField_FieldCaption']")
	private static WebElement extraFields_FieldDetailsTab;

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

	@FindBy(xpath = "//span[contains(text(),'Field Details')]")
	private static WebElement extraFields_ClickOnFieldDetailsTab;

	@FindBy(xpath = "//*[@id='ExtraField_FieldCaption']")
	private static WebElement extraFields_FieldDetailsCaption;

	@FindBy(xpath = "//div[@id='tab_FieldDetails_ExtraField']//div[1]//div[2]//div[1]//input[1]")
	private static WebElement extraFields_FieldDetailsName;

	@FindBy(xpath = "//*[@id='ddlDatatype_ExtraField']")
	private static WebElement extraFields_FieldDetailsDatTypeDropdown;

	@FindBy(xpath = "//*[@id='ExtraField_DefaultValue']")
	private static WebElement extraFields_FieldDetailsDefaultValue;

	@FindBy(xpath = "//*[@id='ExtraField_BannerText']")
	private static WebElement extraFields_FieldDetailsBannerText;

	@FindBy(xpath = "//*[@id='ExtraField_MaxSize']")
	private static WebElement extraFields_FieldDetails_MaxSize;

	@FindBy(xpath = "//*[@id='ExtraField_ControlType']")
	private static WebElement extraFields_FieldDetailsControlTypeDropdown;

	@FindBy(xpath = "//*[@id='ExtraField_ToolTipText']")
	private static WebElement extraFields_FieldDetailsToolTipText;

	@FindBy(xpath = "//*[@id='ExtraField_RegularExpression']")
	private static WebElement extraFields_FieldDetailsRegularExpressionText;

	@FindBy(xpath = " //*[@id='btnLoad_RExp']")
	private static WebElement extraFields_RegularExperssionLoadExpansionBtn;

	@FindBy(xpath = "//*[@id='extraFieldContainer_div']/ul/li[2]/a/span")
	private static WebElement extraFields_PropertiesTab;

	@FindBy(xpath = "//*[@id='tab_Properties_ExtraField']/div[1]/div[1]")
	private static WebElement extraFields_PropertiesTabPartOfDeliveryAddressChekbox;

	@FindBy(xpath = "//*[@id='FieldsDetails_PartOfBillingAddress']")
	private static WebElement extraFields_PropertiesPartOfBillingAddressChekbox;

	@FindBy(xpath = "//input[@id='FieldsDetails_IsMandatory']")
	private static WebElement extraFields_PropertiesMandatoryChekbox;

	@FindBy(xpath = "//*[@id='FieldsDetails_AllowAsParameter']")
	private static WebElement extraFields_PropertiesAllowAsParameterChekbox;

	@FindBy(xpath = "//*[@id='FieldsDetails_IsHidden']")
	private static WebElement extraFields_PropertiesHiddenChekbox;

	@FindBy(xpath = "//*[@id='FieldsDetails_HiddenInGroup']")
	private static WebElement extraFields_PropertiesHiddenInGroupChekbox;

	@FindBy(xpath = "//*[@id='FieldsDetails_SpellCheck']")
	private static WebElement extraFields_PropertiesSpellCheckChekbox;

	@FindBy(xpath = "//*[@id='FieldsDetails_MandatoryInGroup']")
	private static WebElement extraFields_PropertiesMandatoryInGroupChekbox;

	@FindBy(xpath = "//*[@id='FieldsDetails_AuditTrial']")
	private static WebElement extraFields_PropertiesAuditTrialChekbox;

	@FindBy(xpath = "//*[@id='FieldsDetails_NotAvailableForReports']")
	private static WebElement extraFields_PropertiesNotAvailableForReportsChekbox;

	@FindBy(xpath = "//*[@id='FieldsDetails_MergeField']")
	private static WebElement extraFields_PropertiesMergeFieldChekbox;

	@FindBy(xpath = "//*[@id='FieldsDetails_MassUpdate']")
	private static WebElement extraFields_PropertiesMassUpdateChekbox;

	@FindBy(xpath = "//*[@id='FieldsDetails_IncludeInQuickCreate']")
	private static WebElement extraFields_PropertiesIncludeInQuickCreateChekbox;

	@FindBy(xpath = "//*[@id='FieldsDetails_CannotBeExported']")
	private static WebElement extraFields_PropertiesCannotBeExportedChekbox;

	@FindBy(xpath = "//*[@id='FieldsDetails_CannotBeImported']")
	private static WebElement extraFields_PropertiesCannotBeImportedChekbox;

	@FindBy(xpath = "//*[@id='FieldsDetails_CopyFromParent']")
	private static WebElement extraFields_PropertiesCopyFromParentChekbox;

	@FindBy(xpath = "//*[@id='FieldsDetails_ReadOnly']")
	private static WebElement extraFields_PropertiesReadOnlyChekbox;

	@FindBy(xpath = "//*[@id='FieldsDetails_InformationField']")
	private static WebElement extraFields_PropertiesInformationFieldChekbox;

	@FindBy(xpath = "//*[@id='FieldsDetails_IsMandatoryInRevision']")
	private static WebElement extraFields_PropertiesIsMandatoryInRevisionChekbox;

	@FindBy(xpath = "//*[@id='FieldsDetails_AvailableinCustomerPortal']")
	private static WebElement extraFields_PropertiesAvailableinCustomerPortalChekbox;

	@FindBy(xpath = "//*[@id='FieldsDetails_EditableinCustomerPortal']")
	private static WebElement extraFields_PropertiesEditableinCustomerPortalChekbox;

	@FindBy(xpath = "//*[@id='Behaviour']")
	private static WebElement extraFields_PropertiesBehaviourDropdown;

	@FindBy(xpath = "//*[@id='extraFieldContainer_div']/ul/li[3]/a/span")
	private static WebElement extraFields_FormattingTab;

	@FindBy(xpath = "//*[@id='FieldsDetails_ColumnSpan']")
	private static WebElement extraFields_FormattingColumnSpan;

	@FindBy(xpath = "//*[@id='FieldsDetails_RowSpan']")
	private static WebElement extraFields_FormattingRowSpan;

	@FindBy(xpath = "//*[@id='CharacterCasing']")
	private static WebElement extraFields_FormattingCharacterCasingDropdown;

	@FindBy(xpath = "//*[@id='TextAlign']")
	private static WebElement extraFields_FormattingTextAlignDropdown;

	@FindBy(xpath = "//*[@id='FieldsDetails_Font']")
	private static WebElement extraFields_FormattingFieldsDetailsFont;

	@FindBy(xpath = "//*[@id='FieldsDetails_BackColor']")
	private static WebElement extraFields_FormattingBackColor;

	@FindBy(xpath = "//*[@id='FieldsDetails_Font_Button']")
	private static WebElement extraFields_FormattingFieldsDetailsFont_Btn;

	// Elements are used for font edit option pop in field details and rules
	@FindBy(xpath = "//*[@id='fontFamily_ExtraField_FontCtrl']")
	private static WebElement FontAreialDropdown;

	@FindBy(xpath = "//*[@id='fontStyle_ExtraField_FontCtrl']")
	private static WebElement fontStyleDropdown;

	@FindBy(xpath = "//*[@id='fontWeight_ExtraField_FontCtrl']")
	private static WebElement fontWeightDropdown;

	@FindBy(xpath = "//*[@id='fontSizes_ExtraField_FontCtrl']")
	private static WebElement fontSizeDropdown;

	@FindBy(xpath = "//*[@id='fontForeColor_ExtraField_FontCtrl']")
	private static WebElement foreColourDropdown;

	@FindBy(xpath = "//*[@id='fontBackColor_ExtraField_FontCtrl']")
	private static WebElement backColorDropdown;

	@FindBy(xpath = "//*[@id='chkBaseline_ExtraField_FontCtrl']")
	private static WebElement baselineChekbox;

	@FindBy(xpath = "//*[@id='chkOverLine_ExtraField_FontCtrl']")
	private static WebElement overLineChekbox;

	@FindBy(xpath = "//*[@id='chkStrikeThrough_ExtraField_FontCtrl']")
	private static WebElement strikeThroughChekbox;

	@FindBy(xpath = "//*[@id='chkUnderline_ExtraField_FontCtrl']")
	private static WebElement underlineChekbox;

	@FindBy(xpath = "//*[@id='chkEnableFont_ExtraField_FontCtrl']")
	private static WebElement enableFontChekbox;

	@FindBy(xpath = "//*[@id='previewfont_ExtraField_FontCtrl']")
	private static WebElement frontPriveiw;

	@FindBy(xpath = "//*[@id='myFontControl_IF_Save']/i")
	private static WebElement frontSaveBtn;

	@FindBy(xpath = "//*[@id='myFontControl_IF_Close']")
	private static WebElement frontCloseBtn;

	@FindBy(xpath = "//*[@id='extraFieldContainer_div']/ul/li[4]/a/span")
	private static WebElement extraFields_RulesTab;

	@FindBy(xpath = "//*[@id='newRuleTab_div_FieldRules']/div[1]/div/div[1]/div[2]")
	private static WebElement extraFields_RulesSaveBtn;

	@FindBy(xpath = "//*[@id='newRuleTab_div_FieldRules']/div[1]/div/div[2]/div[2]")
	private static WebElement extraFields_RulesNewBtn;

	@FindBy(xpath = "//*[@id='newRuleTab_div_FieldRules']/div[1]/div/div[3]/div[2]")
	private static WebElement extraFields_RulesDeleteBtn;

	@FindBy(xpath = "//div[@id='newRuleTab_div_FieldRules']//div[@class='col-sm-12 btnheader_txt font-7'][contains(text(),'Close')]")
	private static WebElement extraFields_RulesCloseBtn;

	@FindBy(xpath = "//*[@id='lnkRule_div_FieldRules']/input")
	private static WebElement extraFields_RulesClickHereAddRuleButtonBtn;

	@FindBy(xpath = "//*[@id='txtMasterRuleName_FieldRules']")
	private static WebElement extraFields_RuleNameDropdown;

	@FindBy(xpath = "//*[@id='txtMasterRuleName_FieldRules_input_settings']/span")
	private static WebElement extraFields_RuleNameSetingBtn;

	@FindBy(xpath = "//*[@id='extraFieldContainer_div']/ul/li[5]/a/span")
	private static WebElement extraFields_ExternalModulesTab;

	@FindBy(xpath = "//*[@id='ddlOnEvent_mExtraFieldExternalModule']")
	private static WebElement extraFields_ExternalModulesOnEventDropdown;

	@FindBy(xpath = "//*[@id='txtbuttonCaption_mExtraFieldExternalModule']")
	private static WebElement extraFields_ExternalModulesButtonCaption;

	@FindBy(xpath = "//*[@id='ddlModuleType_mExtraFieldExternalModule']")
	private static WebElement extraFields_ExternalModules_ModuleTypeBtn;

	@FindBy(xpath = "//*[@id='ddlAllDllFiles_mExtraFieldExternalModule']")
	private static WebElement extraFields_ExternalModules_ModuleNameDropdown;

	@FindBy(xpath = "//*[@id='ddlClassNames_mExtraFieldExternalModule']")
	private static WebElement extraFields_ExternalModulesClassNameDropdown;

	@FindBy(xpath = "//*[@id='ddlClassMethods_mExtraFieldExternalModule']")
	private static WebElement extraFields_ExternalModulesFuctionalNameDropdown;

	@FindBy(xpath = "//*[@id='ddlAvailability_mExtraFieldExternalModule']")
	private static WebElement extraFields_ExternalModulesAvabileInDropdown;

	@FindBy(xpath = "//*[@id='MCustomize_FontDialog_Modal']/div/div/div[3]/button[1]")
	private static WebElement extraFields_ExternalModulesForMatFieldDeatailsOkBtn;

	@FindBy(xpath = "//*[@id='MCustomize_FontDialog_Modal']/div/div/div[3]/button[2]")
	private static WebElement extraFields_externalModulesForMatFieldDeatailsCanceBtn;

	@FindBy(xpath = "/html[1]/body[1]/section[1]/div[2]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]")
	private static WebElement extraFields_OkBtn;

	@FindBy(xpath = "/html[1]/body[1]/section[1]/div[2]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[2]")
	private static WebElement extraFields_CloseBtn;

	@FindBy(xpath = "/html[1]/body[1]/section[1]/div[2]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div[1]/div[1]/div[7]/div[1]/div[1]/div[1]/div[1]/table[1]/tbody[1]/tr[1]/td[4]")
	private static WebElement extraFields_CreatedByUserInHeaderDetailsTab;

	@FindBy(xpath = "/html[1]/body[1]/section[1]/div[2]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div[1]/div[1]/div[7]/div[1]/div[1]/div[1]/div[1]/table[1]/tbody[1]/tr[1]/td[1]/i[1]")
	private static WebElement extraFields_EditOptionInHeaderDetailsTab;

	@FindBy(xpath = "/html[1]/body[1]/section[1]/div[2]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div[1]/div[1]/div[7]/div[1]/div[1]/div[1]/div[1]/table[1]/tbody[1]/tr[1]/td[1]/i[2]")
	private static WebElement extraFields_DeleteOptionInHeaderDetailsTab;

	@FindBy(xpath = "/html[1]/body[1]/section[1]/div[2]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div[1]/div[1]/div[7]/div[1]/div[1]/div[1]/div[1]/table[1]/tbody[1]/tr[2]/td[4]")
	private static WebElement extraFields_SecondFieldInHeaderDetailsTab;

	@FindBy(xpath = "/html[1]/body[1]/section[1]/div[2]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div[1]/div[1]/div[8]/div[2]/div[2]")
	private static WebElement extraFields_MoveFieldOption;

	@FindBy(xpath = "/html[1]/body[1]/section[1]/div[2]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div[1]/div[8]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/select[1]")
	private static WebElement extraFields_MoveFieldTabName;

	@FindBy(xpath = "//div[@id='tableType_div']//div[1]//label[1]")
	private static WebElement extraFields_MoveFieldHeader;

	@FindBy(xpath = "//div[@id='rdbBodyDiv_MoveField_Modal']//label[1]")
	private static WebElement extraFields_MoveFieldBody;

	@FindBy(xpath = "/html[1]/body[1]/section[1]/div[2]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div[1]/div[8]/div[1]/div[1]/div[3]/button[1]")
	private static WebElement extraFields_MoveFieldOkButton;

	@FindBy(xpath = "/html[1]/body[1]/section[1]/div[2]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div[1]/div[8]/div[1]/div[1]/div[3]/button[2]")
	private static WebElement extraFields_MoveFieldCancelButton;

	// Settings Tab Move Field
	@FindBy(xpath = "/html[1]/body[1]/section[1]/div[2]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/table[1]/tbody[1]/tr[3]/td[1]/i[1]")
	private static WebElement extraFields_SettingsTab_MoveField;

	@FindBy(xpath = "/html[1]/body[1]/section[1]/div[2]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/table[1]/tbody[1]/tr[1]/td[4]")
	private static WebElement extraFields_SettingsTab_First;

	@FindBy(xpath = "/html[1]/body[1]/section[1]/div[2]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/table[1]/tbody[1]/tr[1]/td[1]/i[2]")
	private static WebElement extraFields_SettingsTab_FirstDelete;

	@FindBy(xpath = "/html[1]/body[1]/section[1]/div[2]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/table[1]/tbody[1]/tr[3]/td[1]/i[2]")
	private static WebElement extraFields_SettingsTab_MoveFieldOptsDelete;

	@FindBy(xpath = "/html[1]/body[1]/section[1]/div[2]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div[1]/div[9]/div[1]/div[1]/div[2]/div[1]/div[1]/label[1]")
	private static WebElement extraFields_SettingsTab_DeleteMessage;

	@FindBy(xpath = "/html[1]/body[1]/section[1]/div[2]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div[1]/div[9]/div[1]/div[1]/div[3]/button[2]")
	private static WebElement extraFields_SettingsTab_DeleteMessageNoOption;

	@FindBy(xpath = "/html[1]/body[1]/section[1]/div[2]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div[1]/div[9]/div[1]/div[1]/div[3]/button[1]")
	private static WebElement extraFields_SettingsTab_DeleteMessageYesOption;

	@FindBy(xpath = "//a[@class='icon-font7 theme_color-inverse']")
	private static WebElement extraFields_GeneralTab;

	@FindBy(xpath = "//a[contains(text(),'Settings')]")
	private static WebElement extraFields_SettingsTab;

	@FindBy(xpath = "//a[contains(text(),'Details')]")
	private static WebElement extraFields_DetailsTab;

	@FindBy(xpath = "//a[contains(text(),'Print Layout')]")
	private static WebElement extraFields_PrintLayout;

	@FindBy(xpath = "//li[5]/div/div/a")
	private static WebElement extraFields_TreeTabUpdate;

	@FindBy(xpath = "//a[@class='icon-font7 theme_color'][contains(text(),'Document Tab')]")
	private static WebElement extraFields_DocumentTab;

	@FindBy(xpath = "//a[@class='icon-font7 theme_color'][contains(text(),'Create Tab')]")
	private static WebElement extraFields_CreateTab;

	@FindBy(xpath = "/html[1]/body[1]/section[1]/div[2]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div[1]/div[1]/div[8]/div[4]/div[2]")
	private static WebElement extraFields_PreviewTab;

	@FindBy(xpath = "//input[@id='sFileName1']")
	private static WebElement extraFields_PreviewTab_DocumentTab_FileName;

	@FindBy(xpath = "//input[@id='biDocument1']")
	private static WebElement extraFields_PreviewTab_DocumentTab_Document;

	@FindBy(xpath = "/html[1]/body[1]/section[1]/div[2]/div[1]/section[1]/div[23]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[6]/div[1]/div[1]/div[1]/table[1]/tbody[1]/tr[1]/td[5]/div[1]/div[1]/div[1]")
	private static WebElement extraFields_PreviewTab_DocumentTab_UploadDocument;

	@FindBy(xpath = "/html[1]/body[1]/section[1]/div[2]/div[1]/section[1]/div[23]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[6]/div[1]/div[1]/div[1]/table[1]/tbody[1]/tr[1]/td[5]/div[1]/div[1]/div[2]")
	private static WebElement extraFields_PreviewTab_DocumentTab_SaveDocument;

	@FindBy(xpath = "/html[1]/body[1]/section[1]/div[2]/div[1]/section[1]/div[23]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[6]/div[1]/div[1]/div[1]/table[1]/tbody[1]/tr[1]/td[5]/div[1]/div[1]/div[3]")
	private static WebElement extraFields_PreviewTab_DocumentTab_DeleteDocument;

	@FindBy(xpath = "/html[1]/body[1]/section[1]/div[2]/div[1]/section[1]/div[23]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[6]/div[1]/div[1]/div[1]/table[1]/tbody[1]/tr[1]/td[7]/table[1]/tbody[1]/tr[1]/td[2]/input[2]")
	private static WebElement extraFields_PreviewTab_DocumentTab_CreateDate;

	@FindBy(xpath = "/html[1]/body[1]/section[1]/div[2]/div[1]/section[1]/div[23]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[6]/div[1]/div[1]/div[1]/table[1]/tbody[1]/tr[1]/td[9]/div[1]/input[1]")
	private static WebElement extraFields_PreviewTab_DocumentTab_FileSize;

	@FindBy(xpath = "/html[1]/body[1]/section[1]/div[2]/div[1]/section[1]/div[23]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[7]/div[1]/div[2]/div[2]/div[1]/div[1]/input[1]")
	private static WebElement extraFields_PreviewTab_CreateCreateTab_ExtraFieldUpdate;

	@FindBy(xpath = "/html[1]/body[1]/section[1]/div[2]/div[1]/section[1]/div[23]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/div[2]/div[1]/ul[1]/li[2]/button[2]/i[1]")
	private static WebElement extraFields_PreviewTab_SaveBtn;

	@FindBy(xpath = "/html[1]/body[1]/section[1]/div[2]/div[1]/section[1]/div[23]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/div[2]/div[1]/ul[1]/li[2]/button[5]")
	private static WebElement extraFields_PreviewTab_CloseBtn;

	@FindBy(xpath = "/html/body/section/div[2]/div/section[1]/div[1]/div/div[1]/div[2]/div[2]/div[2]/div[2]/div[1]/table/tbody/tr[1]/td/div/table/tbody/tr[2]/td[8]/div[1]/label/input")
	private static WebElement selectVendorB;

	@FindBy(xpath = "//input[@id='iPDCDiscountedAC']")
	private static WebElement pdcDicountAccount;

	@FindBy(xpath = "//input[@id='uploadBtnbiDocument1']")
	private static WebElement addGroupDocumentTab_UploadDocument;

	@FindBy(xpath = "//tr[1]//td[5]//div[1]//div[1]//div[2]")
	private static WebElement addGroupDocumentTab_SaveDocument;

	@FindBy(xpath = "/html[1]/body[1]/section[1]/div[2]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/div[2]/form[1]/div[1]/div[6]/div[1]/div[1]/div[1]/table[1]/tbody[1]/tr[1]/td[5]/div[1]/div[1]/div[3]/i[1]")
	private static WebElement addGroupDocumentTab_DeleteDocument;

	@FindBy(xpath = "/html[1]/body[1]/section[1]/div[2]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/div[2]/form[1]/div[1]/div[7]/div[1]/div[2]/div[2]/div[1]/div[1]/input[1]")
	private static WebElement addGroupCreateCreateTab_ExtraFieldUpdate;

	@FindBy(xpath = "//td[contains(@class,'tdName padding0')][contains(text(),'Vendor Group')]")
	public static WebElement accountGroupCreation;

	@FindBy(xpath = "/html[1]/body[1]/section[1]/div[2]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[2]/div[1]/table[1]/tbody[1]/tr[1]/td[1]/div[1]/table[1]/tbody[1]/tr[1]/td[8]/div[1]/label[1]/input[1]")
	private static WebElement accSelect;

	@FindBy(xpath = "/html[1]/body[1]/section[1]/div[2]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[2]/div[1]/table[1]/tbody[1]/tr[1]/td[1]/div[1]/table[1]/tbody[1]/tr[1]/td[11]")
	public static WebElement accSelectBySingleClick;

	@FindBy(xpath = "//span[contains(text(),'Vendor Group')]")
	public static WebElement clickOnAccountGroupToDisplayTitle;

	@FindBy(xpath = "//span[contains(text(),'VG')]")
	public static WebElement checkAccountGroupTitle;

	@FindBy(xpath = "/html[1]/body[1]/section[1]/div[2]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/ol[1]/li[4]/span[1]")
	public static WebElement clickOnAccountGroup;

	@FindBy(xpath = "/html[1]/body[1]/section[1]/div[2]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/input[1]")
	public static WebElement getCpationOfGroup;

	@FindBy(xpath = "//label[@class='msgInfo']")
	public static WebElement clickOnEmptyGroup;

	@FindBy(xpath = "/html[1]/body[1]/section[1]/div[2]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[3]/ul[1]/li[1]/ul[6]/li[1]/ul[3]/li[1]/ul[1]/li[1]/a[2]")
	public static WebElement selectVendorGroupCheckbox;

	@FindBy(xpath = "/html/body/section/div[2]/div/section[1]/div[1]/div/div[1]/div[2]/div[2]/div[2]/div[2]/div[1]/table/tbody/tr[1]/td/div/table/tbody/tr[1]/td[8]/div[1]/label/input")
	public static WebElement selectVendorGroup;

	@FindBy(xpath = "/html[1]/body[1]/section[1]/div[2]/div[1]/section[1]/div[9]/div[1]/div[1]/div[1]/div[2]/div[1]/label[1]")
	public static WebElement getMsgOnDelete;

	@FindBy(xpath = "//button[@id='btnOkForDelete']")
	public static WebElement clickOnOkInDelete;

	@FindBy(xpath = "//button[@id='btnCancelForDelete']")
	public static WebElement clickOnCancelInDelete;

	@FindBy(xpath = "/html[1]/body[1]/section[1]/div[2]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/ol[1]/li[4]/span[1]")
	public static WebElement clickOnVendorGroup;

	@FindBy(xpath = "/html[1]/body[1]/section[1]/div[2]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/input[1]")
	public static WebElement getVendorGroupName;

	@FindBy(xpath = "//button[@class='btn-link theme_icon-color btnGenerateMasterclose closeMasterPartial clsMasterSaveClose']//i[@class='icon-font6 icon-close']")
	public static WebElement closeAccountCreationScreen;

	@FindBy(xpath = "//input[@id='chkRetainSelection']")
	public static WebElement accountMasterRetain;

	@FindBy(xpath = "//input[@id='liSelectAllMasters']")
	public static WebElement accountMasterSelect;

	@FindBy(xpath = "/html[1]/body[1]/section[1]/div[2]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[2]/div[1]/table[1]/tbody[1]/tr[1]/td[1]/div[1]/table[1]/tbody[1]/tr[1]/td[8]/div[1]/label[1]/input[1]")
	public static WebElement accountFirstCheckBox;

	@FindBy(xpath = "/html[1]/body[1]/section[1]/div[2]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[2]/div[1]/table[1]/tbody[1]/tr[1]/td[1]/div[1]/table[1]/tbody[1]/tr[2]/td[8]/div[1]/label[1]/input[1]")
	public static WebElement accountSecondCheckBox;

	@FindBy(xpath = "//button[@class='btn-link theme_icon-color btnGenerateMasterclose closeMasterPartial clsMasterSaveClose']")
	public static WebElement closeAccMasterCreationScreen;

	@FindBy(xpath = "/html[1]/body[1]/section[1]/div[2]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/ul[1]/li[2]/button[5]/i[1]")
	public static WebElement closeMaster;

	// Dash board Page
	@FindBy(xpath = "//*[@id='dashName']")
	private static WebElement labelDashboard;

	@FindBy(xpath = "//*[@id='Select_dash']")
	private static WebElement selectDashboard;

	@FindBy(xpath = "//*[@id='Dashboard_AddDash']")
	private static WebElement newAddDashBoard;

	@FindBy(xpath = "//*[@id='Dashboard_Dash_Config']")
	private static WebElement dashboardCustomizationSettings;

	// Properties Page
	@FindBy(xpath = "//span[contains(text(),'Account Properties')]")
	public static WebElement accountPropertiesLabel;

	@FindBy(xpath = "//input[@id='rbnNormal']")
	public static WebElement normalRadioBtn;

	@FindBy(xpath = "//input[@id='rbnDisplayDebitCreditTotalForEachAccountUnderGroup']")
	private static WebElement displayDebitCreditTotalForEachAccountUnderGroup;

	@FindBy(xpath = "//input[@id='rbnClubTranOfAllAcctsUnderGrpWhenDispInLedger']")
	private static WebElement ClubTheTransactionsofAllTheAccountsUnderGroup;

	@FindBy(xpath = "//input[@id='rbnShowSummaryOfAccount']")
	private static WebElement showSummaryOfTheAccountRadioBtn;

	@FindBy(xpath = "//input[@id='chkSuspendCreditLimit']")
	private static WebElement suspendCreditLimitChkbox;

	@FindBy(xpath = "//input[@id='rbnDisplayDebitCreditTotalForEachMonth']")
	private static WebElement displayDebitCreditTotalForEachMonthRadioBtn;

	@FindBy(xpath = "//input[@id='rbnrbnDisplayDebitCreditTotalForEachDay']")
	private static WebElement displayDebitCreditTotalForEachDayRadioBtn;

	@FindBy(xpath = "//input[@id='rbnNone']")
	public static WebElement noneRadioBtn;

	@FindBy(xpath = "//input[@id='rbnConsolidateWhenBothAccountsAreSame']")
	public static WebElement consolidateWhenBothAccountsAreSameRadioBtn;

	@FindBy(xpath = "//input[@id='rbnConsolidateAlways']")
	public static WebElement consolidateAlwaysRadioBtn;

	@FindBy(xpath = "//select[@id='ddlStatus']")
	public static WebElement statusDropdown;

	@FindBy(xpath = "//input[@id='chkDoNotRestrictSelectionEvenIfRightsNotAllotted']")
	public static WebElement doNotRestrictSelectionEvenIfRightsNotAllottedCheckbox;

	@FindBy(xpath = "//input[@id='chkAllowOtherCompaniesToViewRecords']")
	public static WebElement allowOtherCompaniesToViewRecordsCheckbox;

	@FindBy(xpath = "//input[@id='chkGenerateLedgerByCurrency']")
	public static WebElement generateLedgerByCurrencyCheckbox;

	@FindBy(xpath = "//input[@id='chkSendSmsEmailWhereverAcDebitedCredited']")
	public static WebElement sendSmsEmailWhereverAcDebitedCreditedCheckbox;

	@FindBy(xpath = "//span[@id='btnPropOk']")
	public static WebElement properties_okBtn;

	@FindBy(xpath = "//*[@id='divMasterProperty']/ul/li/span[2]")
	public static WebElement properties_cancelBtn;

	@FindBy(xpath = "/html[1]/body[1]/section[1]/div[2]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/div[1]/div[2]/ul[1]/li[1]/ul[1]/li[5]/span[1]")
	public static WebElement customizeTabWithTreeTabUpdate;

	@FindBy(xpath = "/html[1]/body[1]/section[1]/div[2]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/div[1]/div[2]/ul[1]/li[1]/ul[1]/li[6]/span[1]")
	public static WebElement customizeTabWithDocumentTab;

	@FindBy(xpath = "/html[1]/body[1]/section[1]/div[2]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/div[1]/div[2]/ul[1]/li[1]/ul[1]/li[7]/span[1]")
	public static WebElement customizeTabWithCreateTreeTab;

	@FindBy(xpath = "//div[@id='Layout_Popup']//input[2]")
	private static WebElement noIn;

	@FindBy(xpath = "//*[@id='dashName']")
	private static WebElement dashboard;

	@FindBy(xpath = "//div[@id='chartdiv0']")
	public static WebElement dashboardGraph;

	@FindBy(xpath = "//span[@id='btnPropOk']")
	public static WebElement dashboardLedger;

	@FindBy(xpath = "//span[@id='btnPropOk']")
	public static WebElement dashboardInfoPanel;

	// General tab
	@FindBy(xpath = "//tbody[@id='iBankAc_table_data_body']/tr/td")
	private static List<WebElement> bankAccountListCount;

	@FindBy(xpath = "//tbody[@id='iPDCDiscountedAC_table_data_body']/tr/td")
	private static List<WebElement> pdcAccountListCount;

	// Settings Tab
	@FindBy(xpath = "//tbody[@id='iExchangeAdjustmentGainAC_table_data_body']/tr/td")
	private static List<WebElement> exgGainListCount;

	@FindBy(xpath = "//tbody[@id='iExchangeAdjustmentLossAC_table_data_body']/tr/td")
	private static List<WebElement> exgLossListCount;

	@FindBy(xpath = "//tbody[@id='iPrimaryAccount_table_data_body']/tr/td")
	private static List<WebElement> primaryAccountListCount;

	@FindBy(xpath = "//tbody[@id='iReminderTerms_table_data_body']")
	private static WebElement reminderTermsEmptyList;

	@FindBy(xpath = "//tbody[@id='iDefaultCurrency_table_data_body']/tr/td")
	private static List<WebElement> defaultCurrencyListCount;

	@FindBy(xpath = "//tbody[@id='iFinanceChargeTerms_table_data_body']")
	private static WebElement financeChargeTermsEmptyList;

	@FindBy(xpath = "//tbody[@id='iPaymentTerms_table_data_body']")
	private static WebElement paymentTermsEmptyList;

	// Detail tab
	@FindBy(xpath = "//tbody[@id='iCity_table_data_body']")
	private static WebElement city1EmptyList;

	@FindBy(xpath = "//tbody[@id='iDeliveryCity_table_data_body']")
	private static WebElement city2EmptyList;

	// Tree tab
	@FindBy(xpath = "//tbody[@id='cmbUserTypeMaster_table_data_body']/tr/td")
	private static List<WebElement> mastercmbMasterListCount;

	// Currency Page
	@FindBy(xpath = "//span[@class='navText']//span[contains(text(),'Currency Master')]")
	private static WebElement currencyMasterlabel;

	@FindBy(xpath = "//*[@id='ISOCurrencyCode']")
	private static WebElement ISOCurrencyCodeTxt;

	@FindBy(xpath = "//td[@id='ISOCurrencyCode_input_settings']//span[@class='icon-settings optioncontrol_settings_margin']")
	private static WebElement ISOCurrencyCodeSettingsBtn;

	@FindBy(xpath = "//h4[@id='ISOCurrencyCode_customize_popup_heading']")
	private static WebElement ISOCurrencyCode_CustomizeDisplayColumnsLabel;

	@FindBy(xpath = "//div[@id='ISOCurrencyCode_customize_popup_container']")
	private static WebElement ISOCurrencysCodeContainerTxt;

	@FindBy(xpath = "//div[@id='ISOCurrencyCode_customize_popup_footer']//input[1]")
	private static WebElement ISOCurrencyCodeStanadrdfieldsBtn;

	@FindBy(xpath = "//select[@id='ISOCurrencyCode_customize_popup_standardfields_list']")
	private static WebElement ISOCurrencyCodeStanadrdfields_Fielddropdown;

	@FindBy(xpath = "//input[@id='ISOCurrencyCode_customize_popup_standardfields_header']")
	private static WebElement ISOCurrencyCodeStanadrdfields_headerTxt;

	@FindBy(xpath = "//select[@id='ISOCurrencyCode_customize_popup_standardfields_alignment']")
	private static WebElement ISOCurrencyCodeStanadrdfields_Allignmentdropdown;

	@FindBy(xpath = "//input[@id='ISOCurrencyCode_customize_popup_standardfields_width']")
	private static WebElement ISOCurrencyCodeStanadrdfields_WidthTxt;

	@FindBy(xpath = "//div[@id='currencyMasterWidth']//input[3]")
	private static WebElement ISOCurrencyCodeStanadrdfields_OkBtn;

	@FindBy(xpath = "//section[@id='page_Content']//input[4]")
	private static WebElement ISOCurrencyCodeStanadrdfields_CancelBtn;

	@FindBy(xpath = "//div[@id='ISOCurrencyCode_search_container']//input[2]")
	private static WebElement ISOCurrencyCodedeleteColumnBtn;

	@FindBy(xpath = "//div[@id='currencyMasterWidth']//input[3]")
	private static WebElement ISOCurrencyCodeOkBtn;

	@FindBy(xpath = "//section[@id='page_Content']//input[4]")
	private static WebElement ISOCurrencyCodeCancelBtn;

	@FindBy(xpath = "/html[1]/body[1]/section[1]/div[2]/div[1]/section[1]/div[1]/div[2]/fieldset[1]/div[1]/div[2]/div[2]/input[1]")
	private static WebElement coinsNameTxt;

	@FindBy(xpath = "//input[@id='currencyName']")
	private static WebElement currencyNameTxt;

	@FindBy(xpath = "//input[@id='GeneralRoundOff']")
	private static WebElement generalRoundOffTxt;

	@FindBy(xpath = "//select[@id='RoundingType']")
	private static WebElement roundingTypeDropdown;

	@FindBy(xpath = "//input[@id='CurrencyUnit']")
	private static WebElement currencyUnitTxt;

	@FindBy(xpath = "//input[@id='CurrencySubUnit']")
	private static WebElement currencySubUnitTxt;

	@FindBy(xpath = "//input[@id='connector']")
	private static WebElement connectorTxt;

	@FindBy(xpath = "//input[@id='controlSymbol']")
	private static WebElement controlSymbolDropdown;

	// Control SymbolPop Up
	@FindBy(xpath = "//td[@id='controlSymbol_input_settings']//span[@class='icon-settings optioncontrol_settings_margin']")
	private static WebElement symbolSettingsBtn;

	@FindBy(xpath = "//h4[@id='controlSymbol_customize_popup_heading']")
	private static WebElement symbol_CustomizeDisplayColumnsLabel;

	@FindBy(xpath = "//div[@id='controlSymbol_customize_popup_container']")
	private static WebElement ISOCurrencysSymbolContainerTxt;

	@FindBy(xpath = "//div[@id='controlSymbol_customize_popup_footer']//input[1]")
	private static WebElement symbolStanadrdfieldsBtn;

	@FindBy(xpath = "//select[@id='controlSymbol_customize_popup_standardfields_list']")
	private static WebElement symbolStanadrdfields_Fielddropdown;

	@FindBy(xpath = "//input[@id='controlSymbol_customize_popup_standardfields_header']")
	private static WebElement symbolStanadrdfields_headerTxt;

	@FindBy(xpath = "//select[@id='controlSymbol_customize_popup_standardfields_alignment']")
	private static WebElement symbolStanadrdfields_Allignmentdropdown;

	@FindBy(xpath = "//input[@id='controlSymbol_customize_popup_standardfields_width']")
	private static WebElement symbolStanadrdfields_WidthTxt;

	@FindBy(xpath = "//div[@id='currencyMasterWidth']//input[3]")
	private static WebElement symbolStanadrdfields_OkBtn;

	@FindBy(xpath = "//section[@id='page_Content']//input[4]")
	private static WebElement symbolStanadrdfields_CancelBtn;

	@FindBy(xpath = "//div[@id='controlSymbol_search_container']//input[2]")
	private static WebElement symboldeleteColumnBtn;

	@FindBy(xpath = "//div[@id='currencyMasterWidth']//input[3]")
	private static WebElement symbolOkBtn;

	@FindBy(xpath = "//section[@id='page_Content']//input[4]")
	private static WebElement symbolCancelBtn;

	@FindBy(xpath = "//input[@id='iNoOfDecimals']")
	private static WebElement noOfDecimalsTxt;

	@FindBy(xpath = "//input[@id='NoOfDesimals']")
	private static WebElement noOfDecimalsTxtInCurrency;

	@FindBy(xpath = "/html[1]/body[1]/section[1]/div[2]/div[1]/section[1]/div[1]/div[2]/fieldset[1]/div[1]/div[2]/div[4]/table[1]/tbody[1]/tr[2]")
	private static WebElement tableRow;

	@FindBy(xpath = "/html[1]/body[1]/section[1]/div[2]/div[1]/section[1]/div[1]/div[2]/fieldset[1]/div[1]/div[2]/div[4]/table[1]/tbody[1]/tr[2]/td[1]")
	private static WebElement tableCol;

	// Grid Fields in Denomination Details
	@FindBy(xpath = "//td[@id='CurrencyMasterGrid_col_1-1']")
	private static WebElement denominationCode1;

	@FindBy(xpath = "//input[@id='CurrencyMasterGrid_control_heading_ctrl_2']")
	private static WebElement denominationValue1;

	@FindBy(xpath = "//td[@id='CurrencyMasterGrid_col_2-1']")
	private static WebElement denominationCode2;

	@FindBy(xpath = "//td[@id='CurrencyMasterGrid_col_2-2']")
	private static WebElement denominationValue2;

	// Pop Up Field
	@FindBy(xpath = "//span[contains(text(),'Exchange Rate Definition')]")
	private static WebElement exchangeRateDefinitionLabel;

	@FindBy(xpath = "//i[@class='icon-font7 icon-import-from-excel']")
	private static WebElement importFromExcelIcon;

	@FindBy(xpath = "//i[@class='icon-font7 icon-clear']")
	private static WebElement exchangeRateDefinitionClearIcon;

	@FindBy(xpath = "//i[@class='icon-font7 icon-save']")
	private static WebElement exchangeRateDefinitionSaveIcon;

	@FindBy(xpath = "//span[@id='btnCurrencyMasterCancel']//i[@class='icon-font7 icon-close']")
	private static WebElement exchangeRateDefinitionPopCloseIcon;

	@FindBy(xpath = "//span[@id='closeExchangeRate']")
	private static WebElement exchangeRateDefinitionPageCloseIcon;

	@FindBy(xpath = "//input[@id='id_option']")
	private static WebElement baseCurrencyTxt;

	@FindBy(xpath = "//td[@id='id_option_input_settings']//span[@class='icon-settings optioncontrol_settings_margin']")
	private static WebElement baseCurrencySettingsBtn;

	@FindBy(xpath = "//h4[@id='id_option_customize_popup_heading']")
	private static WebElement baseCurrency_CustomizeDisplayColumnsLabel;

	@FindBy(xpath = "//div[@id='id_option_customize_popup_container']")
	private static WebElement baseCurrencyscodeContainerTxt;

	@FindBy(xpath = "//div[@id='id_option_customize_popup_footer']//input[1]")
	private static WebElement baseCurrencyStanadrdfieldsBtn;

	@FindBy(xpath = "//select[@id='id_option_customize_popup_standardfields_list']")
	private static WebElement baseCurrencyStanadrdfields_Fielddropdown;

	@FindBy(xpath = "//input[@id='id_option_customize_popup_standardfields_header']")
	private static WebElement baseCurrencyStanadrdfields_headerTxt;

	@FindBy(xpath = "//select[@id='id_option_customize_popup_standardfields_alignment']")
	private static WebElement baseCurrencyStanadrdfields_Allignmentdropdown;

	@FindBy(xpath = "//input[@id='id_option_customize_popup_standardfields_width']")
	private static WebElement baseCurrencyStanadrdfields_WidthTxt;

	@FindBy(xpath = "//div[@class='row']//input[3]")
	private static WebElement baseCurrencyStanadrdfields_OkBtn;

	@FindBy(xpath = "//section[@id='page_Content']//input[4]")
	private static WebElement baseCurrencyStanadrdfields_CancelBtn;

	@FindBy(xpath = "//div[@id='id_option_search_container']//input[2]")
	private static WebElement baseCurrencydeleteColumnBtn;

	@FindBy(xpath = "//div[@class='row']//input[3]")
	private static WebElement baseCurrencyOkBtn;

	@FindBy(xpath = "//section[@id='page_Content']//input[4]")
	private static WebElement baseCurrencyCancelBtn;

	// Grid Fields
	@FindBy(xpath = "//input[@id='ExchangeTable_CurrencyName']")
	private static WebElement gridcurrencyName1Txt;

	@FindBy(xpath = "//td[@id='ExchangeTable_CurrencyName_input_settings']//span[@class='icon-settings optioncontrol_settings_margin']")
	private static WebElement currencyNameSettingsBtn;

	// Pop Up
	@FindBy(xpath = "//h4[@id='ExchangeTable_CurrencyName_customize_popup_heading']")
	private static WebElement currencyName_CustomizeDisplayColumnsLabel;

	@FindBy(xpath = "//div[@id='ExchangeTable_CurrencyName_customize_popup_container']")
	private static WebElement currencyNameContainerTxt;

	@FindBy(xpath = "//div[@id='ExchangeTable_CurrencyName_customize_popup_footer']//input[1]")
	private static WebElement currencyNameStanadrdfieldsBtn;

	@FindBy(xpath = "//select[@id='ExchangeTable_CurrencyName_customize_popup_standardfields_list']")
	private static WebElement currencyNameStanadrdfields_Fielddropdown;

	@FindBy(xpath = "//input[@id='ExchangeTable_CurrencyName_customize_popup_standardfields_header']")
	private static WebElement currencyNameStanadrdfields_headerTxt;

	@FindBy(xpath = "//select[@id='ExchangeTable_CurrencyName_customize_popup_standardfields_alignment']")
	private static WebElement currencyNameStanadrdfields_Allignmentdropdown;

	@FindBy(xpath = "//input[@id='ExchangeTable_CurrencyName_customize_popup_standardfields_width']")
	private static WebElement currencyNameStanadrdfields_WidthTxt;

	@FindBy(xpath = "//table[@id='ExchangeTable']//input[3]")
	private static WebElement currencyNameStanadrdfields_OkBtn;

	@FindBy(xpath = "//section[@id='page_Content']//input[4]")
	private static WebElement currencyNameStanadrdfields_CancelBtn;

	@FindBy(xpath = "//div[@id='ExchangeTable_CurrencyName_search_container']//input[2]")
	private static WebElement currencyNamedeleteColumnBtn;

	@FindBy(xpath = "//table[@id='ExchangeTable']//input[3]")
	private static WebElement currencyNameOkBtn;

	@FindBy(xpath = "//section[@id='page_Content']//input[4]")
	private static WebElement currencyNameCancelBtn;

	@FindBy(xpath = "//input[@id='EffectiveDate']")
	private static WebElement withEffectiveDateTxt;

	@FindBy(xpath = "//*[@id='EffectiveDate_input_image']/span")
	private static WebElement dOne;

	@FindBy(xpath = "//*[@id='EffectiveDate_day_today']/td/span[1]")
	private static WebElement dTwo;

	@FindBy(xpath = "//input[@id='ExchangeTable_DefinedAs']")
	private static WebElement gridDefineAsTxt;

	@FindBy(xpath = "//input[@id='ExchangeTable_Rate']")
	private static WebElement gridRateTxt;

	@FindBy(xpath = "//td[@id='ExchangeTable_col_1-2']")
	private static WebElement gridDefineAs1Txt;

	@FindBy(xpath = "//td[@id='ExchangeTable_col_1-3']")
	private static WebElement gridRate1Txt;

	@FindBy(xpath = "//td[@id='ExchangeTable_col_1-4']")
	private static WebElement gridDescription1Txt;

	@FindBy(xpath = "//td[@id='ExchangeTable_col_2-1']")
	private static WebElement gridcurrencyName2Txt;

	@FindBy(xpath = "//td[@id='ExchangeTable_col_2-2']")
	private static WebElement gridDefineAs2Txt;

	@FindBy(xpath = "//td[@id='ExchangeTable_col_2-3']")
	private static WebElement gridRate2Txt;

	@FindBy(xpath = "//td[@id='ExchangeTable_col_2-4']")
	private static WebElement gridDescription2Txt;

	@FindBy(xpath = "//td[@id='ExchangeTable_col_3-1']")
	private static WebElement gridcurrencyName3Txt;

	@FindBy(xpath = "//td[@id='ExchangeTable_col_3-2']")
	private static WebElement gridDefineAs3Txt;

	@FindBy(xpath = "//td[@id='ExchangeTable_col_3-3']")
	private static WebElement gridRate3Txt;

	@FindBy(xpath = "//td[@id='ExchangeTable_col_3-4']")
	private static WebElement gridDescription3Txt;

	@FindBy(xpath = "//span[@id='exchange_currency']")
	private static WebElement exchangeRateIcon;

	@FindBy(xpath = "//div[contains(text(),'Clear')]")
	private static WebElement clearIcon;

	@FindBy(xpath = "//span[@id='btnSave']")
	private static WebElement saveIcon;

	@FindBy(xpath = "//span[@id='btnClose']")
	private static WebElement cancelIcon;

	// Exchange Rate Page

	// Header Fields
	@FindBy(xpath = "//i[@class='icon-font7 icon-delete']")
	private static WebElement exchangeRateDefinitionDeleteIcon;

	@FindBy(xpath = " //span[@id='closeExchangeRate']//i[@class='icon-font7 icon-close']")
	private static WebElement exchangeRateDefinitionCloseIcon;

	// Exchange Rate History
	@FindBy(xpath = "//div[@class='navText']//span[contains(text(),'Exchange Rate History')]")
	private static WebElement exchangeRateHistoryLabel;

	@FindBy(xpath = "//input[@id='id_option']")
	private static WebElement baseCurrencyInExchangeRateHistory;

	@FindBy(xpath = "//*[@id='DateOption']")
	private static WebElement dateOptionDropdown;

	@FindBy(xpath = "//*[@id='FromDate']")
	private static WebElement fromDateTxt;

	@FindBy(xpath = "//*[@id='ToDate']")
	private static WebElement toDateTxt;

	@FindBy(xpath = "//i[@class='icon-font7 icon-refresh']")
	private static WebElement loadIcon;

	@FindBy(xpath = "//i[@class='icon-font7 icon-clear']")
	private static WebElement exchangeRateHistoryclearIcon;

	// Grid
	@FindBy(xpath = "//input[@id='chkSelectAll']")
	private static WebElement toSelectAllChkBox;

	@FindBy(xpath = "//input[@id='ExchangeTable_chk0']")
	private static WebElement row1Chkbox;

	@FindBy(xpath = "//input[@id='//td[@id='ExchangeTable_col_1-2']")
	private static WebElement selectCurrency1;

	// PopUp
	@FindBy(xpath = "//td[@id='ExchangeTable_CurrencyName_input_settings']//span[@class='icon-settings optioncontrol_settings_margin']")
	private static WebElement selectCurrencySettingsBtn;

	@FindBy(xpath = "//h4[@id='ExchangeTable_CurrencyName_customize_popup_heading']")
	private static WebElement selectCurrency_CustomizeDisplayColumnsLabel;

	@FindBy(xpath = "//div[@id='ExchangeTable_CurrencyName_customize_popup_container']")
	private static WebElement isoCurrencysselectCurrencysCodeContainerTxt;

	@FindBy(xpath = "//div[@id='ExchangeTable_CurrencyName_customize_popup_footer']//input[1]")
	private static WebElement selectCurrencyStanadrdfieldsBtn;

	@FindBy(xpath = "//select[@id='ExchangeTable_CurrencyName_customize_popup_standardfields_list']")
	private static WebElement selectCurrencyStanadrdfields_Fielddropdown;

	@FindBy(xpath = "//input[@id='ExchangeTable_CurrencyName_customize_popup_standardfields_header']")
	private static WebElement selectCurrencyStanadrdfields_headerTxt;

	@FindBy(xpath = "//select[@id='ExchangeTable_CurrencyName_customize_popup_standardfields_alignment']")
	private static WebElement selectCurrencyStanadrdfields_Allignmentdropdown;

	@FindBy(xpath = "//input[@id='ExchangeTable_CurrencyName_customize_popup_standardfields_width']")
	private static WebElement selectCurrencyStanadrdfields_WidthTxt;

	@FindBy(xpath = "//section[@id='page_Content']//input[3]")
	private static WebElement selectCurrencyStanadrdfields_OkBtn;

	@FindBy(xpath = "//section[@id='page_Content']//input[4]")
	private static WebElement selectCurrencyStanadrdfields_CancelBtn;

	@FindBy(xpath = "//div[@id='ExchangeTable_CurrencyName_search_container']//input[2]")
	private static WebElement selectCurrencydeleteColumnBtn;

	@FindBy(xpath = "//section[@id='page_Content']//input[3]")
	private static WebElement selectCurrencyOkBtn;

	@FindBy(xpath = "//section[@id='page_Content']//input[4]")
	private static WebElement selectCurrencyCancelBtn;

	@FindBy(xpath = "//td[@id='ExchangeTable_col_1-3']")
	private static WebElement DefineAs1Txt;

	@FindBy(xpath = "/html[1]/body[1]/section[1]/div[2]/div[1]/section[1]/div[1]/div[2]/div[2]/div[1]/div[1]/table[1]/tbody[1]/tr[2]/td[3]")
	private static WebElement valueUSD;

	@FindBy(xpath = "/html[1]/body[1]/section[1]/div[2]/div[1]/section[1]/div[1]/div[2]/div[2]/div[1]/div[1]/table[1]/tbody[1]/tr[2]/td[4]")
	private static WebElement valueAED;

	@FindBy(xpath = "//td[@id='ExchangeTable_col_1-2']")
	private static WebElement selCurrencyOne;

	@FindBy(xpath = "//td[@id='ExchangeTable_col_2-2']")
	private static WebElement selCurrecnyTwo;

	@FindBy(xpath = "//td[@id='ExchangeTable_col_1-3']")
	private static WebElement defCurrecnyOne;

	@FindBy(xpath = "//td[@id='ExchangeTable_col_2-3']")
	private static WebElement defCurrecnyTwo;

	// Grid Second Row
	@FindBy(xpath = "//input[@id='ExchangeTable_chk1']")
	private static WebElement Row2Chkbox;

	@FindBy(xpath = "//td[@id='ExchangeTable_col_2-2']")
	private static WebElement SelectCurrency2;

	@FindBy(xpath = "//td[@id='ExchangeTable_col_1-3']")
	private static WebElement DefineAs2Txt;

	@FindBy(xpath = "//i[@class='icon-font6 icon-close']")
	private static WebElement exchangeRateHistoryCloseIcon;

	@FindBy(xpath = "//span[contains(text(),'Graph')]")
	private static WebElement graphTab;

	@FindBy(xpath = "//span[@class='font-5'][contains(text(),'Table')]")
	private static WebElement tableTab;

	@FindBy(xpath = "//td[@id='ExchangeTable_col_1-0']")
	private static WebElement selectFirstRowIndex;

	@FindBy(xpath = "//td[@id='ExchangeTable_col_2-0']")
	private static WebElement selectSecondRowIndex;

	@FindBy(xpath = "//div[contains(text(),'Delete Row')]")
	private static WebElement selectDeleteOption;

	@FindBy(xpath = "//span[@id='DeleteData']")
	private static WebElement exchnageRateDeleteOption;

	// Vouchers Home Page Ribbon Control

	@FindBy(xpath = "//span[@class='icon-authorize icon-font6']")
	private static WebElement authorizeBtn;

	@FindBy(xpath = "//span[@class='icon-suspend icon-font6']")
	private static WebElement suspendBtn;

	@FindBy(xpath = "//span[@class='icon-reject2 icon-font6']")
	private static WebElement rejectBtn;

	@FindBy(xpath = "//span[@class='icon-cheque icon-font6']")
	private static WebElement chequeReturnBtn;

	@FindBy(xpath = "//span[@class='icon-printbarcode icon-font6']")
	private static WebElement printBarCodeBtn;

	@FindBy(xpath = "//span[@class='icon-convert icon-font6']")
	private static WebElement convertBtn;

	@FindBy(xpath = "//span[@class='icon-export icon-font6']")
	private static WebElement exportToXMLBtn;

	// Vouchers Home Page Options
	@FindBy(xpath = "//a[@class='transaction_viewname_anchor theme_color-inverse font-5']")
	private static WebElement allVouchersOption;

	@FindBy(xpath = "//a[@class='transaction_viewname_anchor font-5']")
	private static WebElement pendingBillsOption;

	@FindBy(xpath = "//input[@id='SelectDefaultView']")
	private static WebElement selectDefaultViewChkBox;

	@FindBy(xpath = "//span[@class='icon-sorting icon-font6 toolbar_button_image theme_button_color dropdown-toggle']")
	private static WebElement sortingBtn;

	@FindBy(xpath = "//div[@id='btnCreateView']//span[@class='icon-new icon-font6']")
	private static WebElement createViewBtn;

	@FindBy(xpath = "//div[@id='btnCustomize']//span[@class='icon-settings icon-font6']")
	private static WebElement customizeBtn;

	@FindBy(xpath = "//span[@class='icon-filter icon-font6']")
	private static WebElement filterBtn;

	@FindBy(xpath = "//span[@id='reportRefresh']")
	private static WebElement refreshBtn;

	@FindBy(xpath = "//i[@class='icon-font6 icon-export']")
	private static WebElement exportBtn;

	// Options Button (Display Option)
	@FindBy(xpath = "//span[@id='transOptions']")
	private static WebElement optionsBtn;

	@FindBy(xpath = "//a[contains(text(),'Auto Adjust Width')]")
	private static WebElement autoAdjustWidthBtn;

	@FindBy(xpath = "//a[contains(text(),'Fit To Screen')]")
	private static WebElement fitToScreenBtn;

	@FindBy(xpath = "//a[contains(text(),'Normal')]")
	private static WebElement normalBtn;

	// Voucher Home Page Grid Header Columns
	@FindBy(xpath = "//input[@id='HeaderChkBox']")
	private static WebElement grid_HeaderChkBox;

	@FindBy(xpath = "//*[@id='tblHeadTransRenderRow']/th[3]")
	private static WebElement grid_Header_Date;

	@FindBy(xpath = "//*[@id='tblHeadTransRenderRow']/th[4]")
	private static WebElement grid_Header_VoucherNumber;

	@FindBy(xpath = "//*[@id='tblHeadTransRenderRow']/th[5]")
	private static WebElement grid_Header_CreatedBy;

	@FindBy(xpath = "//*[@id='tblHeadTransRenderRow']/th[6]")
	private static WebElement grid_Header_ModifiedBy;

	@FindBy(xpath = "//*[@id='tblHeadTransRenderRow']/th[7]")
	private static WebElement grid_Header_CreatedDate;

	@FindBy(xpath = "//*[@id='tblHeadTransRenderRow']/th[8]")
	private static WebElement grid_Header_ModifiedDate;

	@FindBy(xpath = "//*[@id='tblHeadTransRenderRow']/th[9]")
	private static WebElement grid_Header_CreatedTime;

	@FindBy(xpath = "//*[@id='tblHeadTransRenderRow']/th[10]")
	private static WebElement grid_Header_ModifiedTime;

	@FindBy(xpath = "//*[@id='tblHeadTransRenderRow']/th[11]")
	private static WebElement grid_Header_Suspended;

	@FindBy(xpath = "//*[@id='tblHeadTransRenderRow']/th[12]")
	private static WebElement grid_Header_Cancelled;

	@FindBy(xpath = "//*[@id='tblHeadTransRenderRow']/th[13]")
	private static WebElement grid_Header_AuthorizationStatus;

	// Grid Voucher DetailsBtn
	@FindBy(xpath = "//tr[@id='trRender_1']//i[@class='icon-info icon-font7']")
	private static WebElement grid_VoucherDetailsBtn1;

	@FindBy(xpath = "//tr[@id='trRender_2']//i[@class='icon-info icon-font7']")
	private static WebElement grid_VoucherDetailsBtn2;

	// Grid CheckBox 1 And 2

	@FindBy(xpath = "//tr[@id='trRender_1']//td//input")
	private static WebElement grid_ChkBox1;

	@FindBy(xpath = "//tr[@id='trRender_2']//td//input")
	private static WebElement grid_ChkBox2;

	@FindBy(xpath = "//tr[@id='trRender_3']//td//input")
	private static WebElement grid_ChkBox3;

	@FindBy(xpath = "//tr[@id='trRender_4']//td//input")
	private static WebElement grid_ChkBox4;

	// Vouchers Home Page Footer
	@FindBy(xpath = "//div[@id='tblFooterReportRender']//input[@id='txtSearch']")
	private static WebElement searchField;

	@FindBy(xpath = "//button[@id='frstPage']")
	private static WebElement firstPageBtn;

	@FindBy(xpath = "//button[@id='id_previousPage']")
	private static WebElement previousPageBtn;

	@FindBy(xpath = "//button[@id='id_pagenumber']")
	private static WebElement currentPageNo;

	@FindBy(xpath = "//button[@id='id_nextPage']")
	private static WebElement nextPageBtn;

	@FindBy(xpath = "//button[@id='lstPage']")
	private static WebElement lastPageBtn;

	@FindBy(xpath = "//*[@id='id_transactionentry_raisechequereturn']")
	private static WebElement raiseCheckReturnOption;

	@FindBy(xpath = "//*[@id='id_transactionentry_addtostock']")
	private static WebElement addToStockOption;

	@FindBy(xpath = "//*[@id='id_transactionentry_markconvert']")
	private static WebElement markConvertedOption;

	@FindBy(xpath = "//*[@id='id_transactionentry_bominput']")
	private static WebElement bOMinputOption;

	@FindBy(xpath = "//*[@id='id_transactionentry_autoallocate']")
	private static WebElement autoAllocateOption;

	@FindBy(xpath = "//*[@id='id_transactionentry_reverseentry']")
	private static WebElement reverseEntryOption;

	@FindBy(xpath = "//*[@id='id_transactionentry_printbarcode']")
	private static WebElement printBarCodeOption;

	@FindBy(xpath = "//*[@id='id_transactionentry_postingdetails']")
	private static WebElement postingDetailsOption;

	@FindBy(xpath = "//*[@id='id_transactionentry_exporttoxml']")
	private static WebElement exportToXMLOption;

	@FindBy(xpath = "//*[@id='id_transactionentry_settings']")
	private static WebElement settingsOption;

	// Voucher Entry Page Header Fields

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

	@FindBy(xpath = "//*[@id='id_header_4_input_image']/span")
	private static WebElement caskBankAccount_ExpansionBtn;

	@FindBy(xpath = "//*[@id='id_header_4_input_settings']/span")
	private static WebElement cashBankAccount_SettingsBtn;

	@FindBy(xpath = "//div[@id='id_header_4_customize_popup_container']")
	private static WebElement cashBankAccount_Settings_Container;

	@FindBy(xpath = "//div[@id='id_header_4_customize_popup_footer']//input[1]")
	private static WebElement cashBankAccount_Settings_StandardFieldsBtn;

	@FindBy(xpath = "//select[@id='id_header_4_customize_popup_standardfields_list']")
	private static WebElement cashBankAccount_Settings_StandardFieldsBtn_Fielddropdown;

	@FindBy(xpath = "//input[@id='id_header_4_customize_popup_standardfields_header']")
	private static WebElement cashBankAccount_Settings_StandardFieldsBtn_HeaderTxt;

	@FindBy(xpath = "//select[@id='id_header_4_customize_popup_standardfields_alignment']")
	private static WebElement cashBankAccount_Settings_StandardFieldsBtn_Alignmentdropdown;

	@FindBy(xpath = "//input[@id='id_header_4_customize_popup_standardfields_width']")
	private static WebElement cashBankAccount_Settings_StandardFieldsBtn_WidthTxt;

	@FindBy(xpath = "//div[@class='panel']//input[3]")
	private static WebElement cashBankAccount_Settings_StandardFieldsBtn_OkBtn;

	@FindBy(xpath = "//div[@class='panel']//input[4]")
	private static WebElement cashBankAccount_Settings_StandardFieldsBtn_CancelBtn;

	@FindBy(xpath = "//div[@id='id_header_4_search_container']//input[2]")
	private static WebElement cashBankAccount_Settings_DeleteColumnBtn;

	@FindBy(xpath = "//div[@class='panel']//input[3]")
	private static WebElement cashBankAccount_Settings_OkBtn;

	@FindBy(xpath = "//div[@class='panel']//input[4]")
	private static WebElement cashBankAccount_Settings_CancelBtn;

	@FindBy(xpath = "//input[@id='id_header_268435459']")
	private static WebElement departmentTxt;

	@FindBy(xpath = "//*[@id='id_header_268435459_input_image']/span")
	private static WebElement department_ExpansionBtn;

	@FindBy(xpath = "//*[@id='id_header_268435459_input_settings']/span")
	private static WebElement department_SettingsBtn;

	@FindBy(xpath = "//div[@id='id_header_268435459_customize_popup_container']")
	private static WebElement department_Settings_Container;

	@FindBy(xpath = "//div[@id='id_header_268435459_customize_popup_footer']//input[1]")
	private static WebElement department_Settings_StandardFieldsBtn;

	@FindBy(xpath = "//select[@id='id_header_268435459_customize_popup_standardfields_list']")
	private static WebElement department_Settings_StandardFieldsBtn_Fielddropdown;

	@FindBy(xpath = "//input[@id='id_header_268435459_customize_popup_standardfields_header']")
	private static WebElement department_Settings_StandardFieldsBtn_HeaderTxt;

	@FindBy(xpath = "//select[@id='id_header_268435459_customize_popup_standardfields_alignment']")
	private static WebElement department_Settings_StandardFieldsBtn_Alignmentdropdown;

	@FindBy(xpath = "//input[@id='id_header_268435459_customize_popup_standardfields_width']")
	private static WebElement department_Settings_StandardFieldsBtn_WidthTxt;

	@FindBy(xpath = "//div[@class='panel']//input[3]")
	private static WebElement department_Settings_StandardFieldsBtn_OkBtn;

	@FindBy(xpath = "//div[@class='panel']//input[4]")
	private static WebElement department_Settings_StandardFieldsBtn_CancelBtn;

	@FindBy(xpath = "//div[@id='id_header_268435459_search_container']//input[2]")
	private static WebElement department_Settings_DeleteColumnBtn;

	@FindBy(xpath = "//div[@class='panel']//input[3]")
	private static WebElement department_Settings_OkBtn;

	@FindBy(xpath = "//div[@class='panel']//input[4]")
	private static WebElement department_Settings_CancelBtn;

	@FindBy(xpath = "//*[@id='id_header_6']")
	private static WebElement maturityDateTxt;

	@FindBy(xpath = "//*[@id='id_header_6_input_image']/span']")
	private static WebElement maturityDateTxt_CalenderBtn;

	@FindBy(xpath = "//input[@id='id_header_3']")
	private static WebElement purchaseAccountTxt;

	@FindBy(xpath = "//*[@id='id_header_3_input_image']/span")
	private static WebElement purchaseAccount_ExpansionBtn;

	@FindBy(xpath = "//*[@id='id_header_3_input_settings']/span")
	private static WebElement purchaseAccount_SettingsBtn;

	@FindBy(xpath = "//div[@id='id_header_3_customize_popup_container']")
	private static WebElement purchaseAccount_Settings_Container;

	@FindBy(xpath = "//div[@id='id_header_3_customize_popup_footer']//input[1]")
	private static WebElement purchaseAccount_Settings_StandardFieldsBtn;

	@FindBy(xpath = "//select[@id='id_header_3_customize_popup_standardfields_list']")
	private static WebElement purchaseAccount_Settings_StandardFieldsBtn_Fielddropdown;

	@FindBy(xpath = "//input[@id='id_header_3_customize_popup_standardfields_header']")
	private static WebElement purchaseAccount_Settings_StandardFieldsBtn_HeaderTxt;

	@FindBy(xpath = "//select[@id='id_header_3_customize_popup_standardfields_alignment']")
	private static WebElement purchaseAccount_Settings_StandardFieldsBtn_Alignmentdropdown;

	@FindBy(xpath = "//input[@id='id_header_3_customize_popup_standardfields_width']")
	private static WebElement purchaseAccount_Settings_StandardFieldsBtn_WidthTxt;

	@FindBy(xpath = "//div[@class='panel']//input[3]")
	private static WebElement purchaseAccount_Settings_StandardFieldsBtn_OkBtn;

	@FindBy(xpath = "//div[@class='panel']//input[4]")
	private static WebElement purchaseAccount_Settings_StandardFieldsBtn_CancelBtn;

	@FindBy(xpath = "//div[@id='id_header_3_search_container']//input[2]")
	private static WebElement purchaseAccount_Settings_DeleteColumnBtn;

	@FindBy(xpath = "//div[@class='panel']//input[3]")
	private static WebElement purchaseAccount_Settings_OkBtn;

	@FindBy(xpath = "//div[@class='panel']//input[4]")
	private static WebElement purchaseAccount_Settings_CancelBtn;

	@FindBy(xpath = "//input[@id='id_header_4']")
	private static WebElement vendorAccountTxt;

	@FindBy(xpath = "//*[@id='id_header_4_input_image']/span")
	private static WebElement vendorAccount_ExpansionBtn;

	@FindBy(xpath = "//*[@id='id_header_4_input_settings']/span")
	private static WebElement vendorAccount_SettingsBtn;

	@FindBy(xpath = "//div[@id='id_header_4_customize_popup_container']")
	private static WebElement vendorAccount_Settings_Container;

	@FindBy(xpath = "//div[@id='id_header_4_customize_popup_footer']//input[1]")
	private static WebElement vendorAccount_Settings_StandardFieldsBtn;

	@FindBy(xpath = "//select[@id='id_header_4_customize_popup_standardfields_list']")
	private static WebElement vendorAccount_Settings_StandardFieldsBtn_Fielddropdown;

	@FindBy(xpath = "//input[@id='id_header_4_customize_popup_standardfields_header']")
	private static WebElement vendorAccount_Settings_StandardFieldsBtn_HeaderTxt;

	@FindBy(xpath = "//select[@id='id_header_4_customize_popup_standardfields_alignment']")
	private static WebElement vendorAccount_Settings_StandardFieldsBtn_Alignmentdropdown;

	@FindBy(xpath = "//input[@id='id_header_4_customize_popup_standardfields_width']")
	private static WebElement vendorAccount_Settings_StandardFieldsBtn_WidthTxt;

	@FindBy(xpath = "//div[@class='panel']//input[3]")
	private static WebElement vendorAccount_Settings_StandardFieldsBtn_OkBtn;

	@FindBy(xpath = "//div[@class='panel']//input[4]")
	private static WebElement vendorAccount_Settings_StandardFieldsBtn_CancelBtn;

	@FindBy(xpath = "//div[@id='id_header_4_search_container']//input[2]")
	private static WebElement vendorAccount_Settings_DeleteColumnBtn;

	@FindBy(xpath = "//div[@class='panel']//input[3]")
	private static WebElement vendorAccount_Settings_OkBtn;

	@FindBy(xpath = "//div[@class='panel']//input[4]")
	private static WebElement vendorAccount_Settings_CancelBtn;

	@FindBy(xpath = "//input[@id='id_header_3']")
	private static WebElement salesAccountTxt;

	@FindBy(xpath = "//*[@id='id_header_3_input_image']/span")
	private static WebElement salesAccountExpansionBtn;

	@FindBy(xpath = "//*[@id='id_header_3_input_settings']/span")
	private static WebElement salesAccount_SettingsBtn;

	@FindBy(xpath = "//div[@id='id_header_3_customize_popup_container']")
	private static WebElement salesAccount_Settings_Container;

	@FindBy(xpath = "//div[@id='id_header_3_customize_popup_footer']//input[1]")
	private static WebElement salesAccount_Settings_StandardFieldsBtn;

	@FindBy(xpath = "//select[@id='id_header_3_customize_popup_standardfields_list']")
	private static WebElement salesAccount_Settings_StandardFieldsBtn_Fielddropdown;

	@FindBy(xpath = "//input[@id='id_header_3_customize_popup_standardfields_header']")
	private static WebElement salesAccount_Settings_StandardFieldsBtn_HeaderTxt;

	@FindBy(xpath = "//select[@id='id_header_3_customize_popup_standardfields_alignment']")
	private static WebElement salesAccount_Settings_StandardFieldsBtn_Alignmentdropdown;

	@FindBy(xpath = "//input[@id='id_header_3_customize_popup_standardfields_width']")
	private static WebElement salesAccount_Settings_StandardFieldsBtn_WidthTxt;

	@FindBy(xpath = "//div[@class='panel']//input[3]")
	private static WebElement salesAccount_Settings_StandardFieldsBtn_OkBtn;

	@FindBy(xpath = "//div[@class='panel']//input[4]")
	private static WebElement salesAccount_Settings_StandardFieldsBtn_CancelBtn;

	@FindBy(xpath = "//div[@id='id_header_3_search_container']//input[2]")
	private static WebElement salesAccount_Settings_DeleteColumnBtn;

	@FindBy(xpath = "//div[@class='panel']//input[3]")
	private static WebElement salesAccount_Settings_OkBtn;

	@FindBy(xpath = "//div[@class='panel']//input[4]")
	private static WebElement salesAccount_Settings_CancelBtn;

	@FindBy(xpath = "//input[@id='id_header_4']")
	private static WebElement customerAccountTxt;

	@FindBy(xpath = "//*[@id='id_header_4_input_image']/span")
	private static WebElement customerAccount_ExpansionBtn;

	@FindBy(xpath = "//*[@id='id_header_4_input_settings']/span")
	private static WebElement customerAccount_SettingsBtn;

	@FindBy(xpath = "//div[@id='id_header_4_customize_popup_container']")
	private static WebElement customerAccount_Settings_Container;

	@FindBy(xpath = "//div[@id='id_header_4_customize_popup_footer']//input[1]")
	private static WebElement customerAccount_Settings_StandardFieldsBtn;

	@FindBy(xpath = "//select[@id='id_header_4_customize_popup_standardfields_list']")
	private static WebElement customerAccount_Settings_StandardFieldsBtn_Fielddropdown;

	@FindBy(xpath = "//input[@id='id_header_4_customize_popup_standardfields_header']")
	private static WebElement customerAccount_Settings_StandardFieldsBtn_HeaderTxt;

	@FindBy(xpath = "//select[@id='id_header_4_customize_popup_standardfields_alignment']")
	private static WebElement customerAccount_Settings_StandardFieldsBtn_Alignmentdropdown;

	@FindBy(xpath = "//input[@id='id_header_4_customize_popup_standardfields_width']")
	private static WebElement customerAccount_Settings_StandardFieldsBtn_WidthTxt;

	@FindBy(xpath = "//div[@class='panel']//input[3]")
	private static WebElement customerAccount_Settings_StandardFieldsBtn_OkBtn;

	@FindBy(xpath = "//div[@class='panel']//input[4]")
	private static WebElement customerAccount_Settings_StandardFieldsBtn_CancelBtn;

	@FindBy(xpath = "//div[@id='id_header_4_search_container']//input[2]")
	private static WebElement customerAccount_Settings_DeleteColumnBtn;

	@FindBy(xpath = "//div[@class='panel']//input[3]")
	private static WebElement customerAccount_Settings_OkBtn;

	@FindBy(xpath = "//div[@class='panel']//input[4]")
	private static WebElement customerAccount_Settings_CancelBtn;

	@FindBy(xpath = "//input[@id='id_header_12']")
	private static WebElement accountTxt;

	@FindBy(xpath = "//*[@id='id_header_12_input_image']/span")
	private static WebElement account_ExpansionBtn;

	@FindBy(xpath = "//*[@id='id_header_4_input_settings']/span")
	private static WebElement account_SettingsBtn;

	@FindBy(xpath = "//div[@id='id_header_4_customize_popup_container']")
	private static WebElement account_Settings_Container;

	@FindBy(xpath = "//div[@id='id_header_4_customize_popup_footer']//input[1]")
	private static WebElement account_Settings_StandardFieldsBtn;

	@FindBy(xpath = "//select[@id='id_header_4_customize_popup_standardfields_list']")
	private static WebElement account_Settings_StandardFieldsBtn_Fielddropdown;

	@FindBy(xpath = "//input[@id='id_header_4_customize_popup_standardfields_header']")
	private static WebElement account_Settings_StandardFieldsBtn_HeaderTxt;

	@FindBy(xpath = "//select[@id='id_header_4_customize_popup_standardfields_alignment']")
	private static WebElement account_Settings_StandardFieldsBtn_Alignmentdropdown;

	@FindBy(xpath = "//input[@id='id_header_4_customize_popup_standardfields_width']")
	private static WebElement account_Settings_StandardFieldsBtn_WidthTxt;

	@FindBy(xpath = "//div[@class='panel']//input[3]")
	private static WebElement account_Settings_StandardFieldsBtn_OkBtn;

	@FindBy(xpath = "//div[@class='panel']//input[4]")
	private static WebElement account_Settings_StandardFieldsBtn_CancelBtn;

	@FindBy(xpath = "//div[@id='id_header_4_search_container']//input[2]")
	private static WebElement account_Settings_DeleteColumnBtn;

	@FindBy(xpath = "//div[@class='panel']//input[3]")
	private static WebElement account_Settings_OkBtn;

	@FindBy(xpath = "//div[@class='panel']//input[4]")
	private static WebElement account_Settings_CancelBtn;

	@FindBy(xpath = "//input[@id='id_header_31']")
	private static WebElement issuesReceiptsTxt;

	@FindBy(xpath = "//input[@id='id_header_268435460']")
	private static WebElement wareHouseTxt;

	@FindBy(xpath = "//*[@id='id_header_268435460_input_image']/span")
	private static WebElement wareHouse_ExpansionBtn;

	@FindBy(xpath = "//*[@id='id_header_268435460_input_settings']/span")
	private static WebElement wareHouse_SettingsBtn;

	@FindBy(xpath = "//div[@id='id_header_268435460_customize_popup_container']")
	private static WebElement wareHouse_Settings_Container;

	@FindBy(xpath = "//div[@id='id_header_268435460_customize_popup_footer']//input[1]")
	private static WebElement wareHouse_Settings_StandardFieldsBtn;

	@FindBy(xpath = "//select[@id='id_header_268435460_customize_popup_standardfields_list']")
	private static WebElement wareHouse_Settings_StandardFieldsBtn_Fielddropdown;

	@FindBy(xpath = "//input[@id='id_header_268435460_customize_popup_standardfields_header']")
	private static WebElement wareHouse_Settings_StandardFieldsBtn_HeaderTxt;

	@FindBy(xpath = "//select[@id='id_header_268435460_customize_popup_standardfields_alignment']")
	private static WebElement wareHouse_Settings_StandardFieldsBtn_Alignmentdropdown;

	@FindBy(xpath = "//input[@id='id_header_268435460_customize_popup_standardfields_width']")
	private static WebElement wareHouse_Settings_StandardFieldsBtn_WidthTxt;

	@FindBy(xpath = "//div[@class='panel']//input[3]")
	private static WebElement wareHouse_Settings_StandardFieldsBtn_OkBtn;

	@FindBy(xpath = "//div[@class='panel']//input[4]")
	private static WebElement wareHouse_Settings_StandardFieldsBtn_CancelBtn;

	@FindBy(xpath = "//div[@id='id_header_268435460_search_container']//input[2]")
	private static WebElement wareHouse_Settings_DeleteColumnBtn;

	@FindBy(xpath = "//div[@class='panel']//input[3]")
	private static WebElement wareHouse_Settings_OkBtn;

	@FindBy(xpath = "//div[@class='panel']//input[4]")
	private static WebElement wareHouse_Settings_CancelBtn;

	@FindBy(xpath = "//span[contains(text(),'sName')]")
	private static WebElement customize_sName;

	@FindBy(xpath = "//span[@class='vcenter'][contains(text(),'Code')]")
	private static WebElement customize_sCode;

	@FindBy(xpath = "//span[contains(text(),'Alias')]")
	private static WebElement customize_Alias;

	@FindBy(xpath = "//span[@class='vcenter'][contains(text(),'Group')]")
	private static WebElement customize_bGroup;

	@FindBy(xpath = "//span[contains(@class,'vcenter')][contains(text(),'Balance')]")
	private static WebElement customize_Balance;

	@FindBy(xpath = "//input[@id='id_header_67108920']")
	private static WebElement appropriateBasedOndropdown;

	@FindBy(xpath = "//input[@id='id_header_67108921']")
	private static WebElement additionalValueTxt;

	@FindBy(xpath = "//input[@id='id_header_21']")
	private static WebElement raiseReceiptsChkBox;

	@FindBy(xpath = "//input[@id='id_header_67108865']")
	private static WebElement receipts_NarrationTxt;

	@FindBy(xpath = "//input[@id='id_header_67108868']")
	private static WebElement payments_NarrationTxt;

	@FindBy(xpath = "//input[@id='id_header_67108871']")
	private static WebElement pettyCash_NarrationTxt;

	@FindBy(xpath = "//input[@id='id_header_67108874']")
	private static WebElement postDatedReceipts_NarrationTxt;

	@FindBy(xpath = "//input[@id='id_header_67108865']")
	private static WebElement postDatedPayments_NarrationTxt;

	@FindBy(xpath = "//input[@id='id_header_67108880']")
	private static WebElement purchaseVouchers_NarrationTxt;

	@FindBy(xpath = "//input[@id='id_header_67108881']")
	private static WebElement purchaseReturns_NarrationTxt;

	@FindBy(xpath = "//input[@id='id_header_67108884']")
	private static WebElement salesInvoice_NarrationTxt;

	@FindBy(xpath = "//input[@id='id_header_67108886']")
	private static WebElement salesOrder_NarrationTxt;

	@FindBy(xpath = "//input[@id='id_header_67108928']")
	private static WebElement salesInvoiceNewNarrationTxt;

	@FindBy(xpath = "//input[@id='id_header_67108885']")
	private static WebElement salesReturns_NarrationTxt;

	@FindBy(xpath = "//input[@id='id_header_67108913']")
	private static WebElement cashSales_NarrationTxt;

	@FindBy(xpath = "//input[@id='id_header_67108914']")
	private static WebElement hirePurchaseSales_NarrationTxt;

	@FindBy(xpath = "//input[@id='id_header_67108890']")
	private static WebElement journalEntries_NarrationTxt;

	@FindBy(xpath = "//input[@id='id_header_67108916']")
	private static WebElement forexJV_NarrationTxt;

	@FindBy(xpath = "//input[@id='id_header_67108910']")
	private static WebElement interDepartmentalJV_NarrationTxt;

	@FindBy(xpath = "//input[@id='id_header_67108888']")
	private static WebElement nonStandardJournalEntries_NarrationTxt;

	@FindBy(xpath = "//input[@id='id_header_67108892']")
	private static WebElement debitNotes_NarrationTxt;

	@FindBy(xpath = "//input[@id='id_header_67108911']")
	private static WebElement debitNotesLineWise_NarrationTxt;

	@FindBy(xpath = "//input[@id='id_header_67108893']")
	private static WebElement creditNotes_NarrationTxt;

	@FindBy(xpath = "//input[@id='id_header_67108912']")
	private static WebElement creditNotesLineWisePayroll_NarrationTxt;

	@FindBy(xpath = "//input[@id='id_header_67108894']")
	private static WebElement openingBalance_NarrationTxt;

	@FindBy(xpath = "//input[@id='id_header_67108891']")
	private static WebElement fixedAssetDepreciationVoucher_NarrationTxt;

	@FindBy(xpath = "//input[@id='id_header_67108909']")
	private static WebElement requestForQuote_NarrationTxt;

	@FindBy(xpath = "//input[@id='id_header_67108903']")
	private static WebElement purchasesQuotations_NarrationTxt;

	@FindBy(xpath = "//input[@id='id_header_67108882']")
	private static WebElement purchasesOrders_NarrationTxt;

	@FindBy(xpath = "//input[@id='id_header_67108882']")
	private static WebElement materialReceiptsNotes_NarrationTxt;

	@FindBy(xpath = "//input[@id='id_header_67108904']")
	private static WebElement salesQuotationss_NarrationTxt;

	@FindBy(xpath = "//input[@id='id_header_67108887']")
	private static WebElement deliveryNotes_NarrationTxt;

	@FindBy(xpath = "//input[@id='id_header_67108917']")
	private static WebElement posSales_NarrationTxt;

	@FindBy(xpath = "//input[@id='id_header_67108918']")
	private static WebElement productionOrder_NarrationTxt;

	@FindBy(xpath = "//input[@id='id_header_67108886']")
	private static WebElement salesOrders_NarrationTxt;

	@FindBy(xpath = "//input[@id='id_header_67108895']")
	private static WebElement shortagesinStock_NarrationTxt;

	@FindBy(xpath = "//input[@id='id_header_67108896']")
	private static WebElement excessesinStocks_NarrationTxt;

	@FindBy(xpath = "//input[@id='id_header_67108897']")
	private static WebElement stockTransfers_NarrationTxt;

	@FindBy(xpath = "//input[@id='id_header_67108898']")
	private static WebElement openingStocks_NarrationTxt;

	@FindBy(xpath = "//input[@id='id_header_67108923']")
	private static WebElement openingStocksNEW_NarrationTxt;

	@FindBy(xpath = "//input[@id='id_header_67108908']")
	private static WebElement materialRequisition_NarrationTxt;

	@FindBy(xpath = "//input[@id='id_header_67108866']")
	private static WebElement receipts_ChequeNoTxt;

	@FindBy(xpath = "//input[@id='id_header_67108869']")
	private static WebElement payments_ChequeNoTxt;

	@FindBy(xpath = "//input[@id='id_header_67108872']")
	private static WebElement pettyCash_ChequeNoTxt;

	@FindBy(xpath = "//input[@id='id_header_67108875']")
	private static WebElement postDatedReceipts_ChequeNoTxt;

	@FindBy(xpath = "//input[@id='id_header_67108878']")
	private static WebElement postDatedPayments_ChequeNoTxt;

	// Voucher Entry Page Body Fields

	@FindBy(xpath = "//input[@id='id_body_536870916']")
	private static WebElement pvWareHouseTxt;

	@FindBy(xpath = "//td[@id='id_body_536870916_input_image']//span[@class='icon-down-arrow optioncontrol_arrow_margin']")
	private static WebElement pvWareHouse_ExpansionBtn;

	@FindBy(xpath = "//td[@id='id_body_536870916_input_settings']//span[@class='icon-settings optioncontrol_settings_margin']")
	private static WebElement pvWareHouse_SettingsBtn;

	@FindBy(xpath = "//div[@id='id_body_536870916_customize_popup_container']")
	private static WebElement pvWareHouse_Settings_Container;

	@FindBy(xpath = "//div[@id='id_body_536870916_customize_popup_footer']//input[1]")
	private static WebElement pvWareHouse_Settings_StandardFieldsBtn;

	@FindBy(xpath = "//select[@id='id_body_536870916_customize_popup_standardfields_list']")
	private static WebElement pvWareHouse_Settings_StandardFieldsBtn_Fielddropdown;

	@FindBy(xpath = "//input[@id='id_body_536870916_customize_popup_standardfields_header']")
	private static WebElement pvWareHouse_Settings_StandardFieldsBtn_HeaderTxt;

	@FindBy(xpath = "//select[@id='id_body_536870916_customize_popup_standardfields_alignment']")
	private static WebElement pvWareHouse_Settings_StandardFieldsBtn_Alignmentdropdown;

	@FindBy(xpath = "//input[@id='id_body_536870916_customize_popup_standardfields_width']")
	private static WebElement pvWareHouse_Settings_StandardFieldsBtn_WidthTxt;

	@FindBy(xpath = "//div[@class='panel']//input[3]")
	private static WebElement pvWareHouse_Settings_StandardFieldsBtn_OkBtn;

	@FindBy(xpath = "//div[@class='panel']//input[4]")
	private static WebElement pvWareHouse_Settings_StandardFieldsBtn_CancelBtn;

	@FindBy(xpath = "//div[@id='id_body_536870916_search_container']//input[2]")
	private static WebElement pvWareHouse_Settings_DeleteColumnBtn;

	@FindBy(xpath = "//div[@class='panel']//input[3]")
	private static WebElement pvWareHouse_Settings_OkBtn;

	@FindBy(xpath = "//div[@class='panel']//input[4]")
	private static WebElement pvWareHouse_Settings_CancelBtn;

	// Grid Elements
	@FindBy(xpath = "//th[@id='id_transaction_entry_detail_table_control_heading_1']")
	private static WebElement grid_Header_Account;

	@FindBy(xpath = "//th[@id='id_transaction_entry_detail_table_control_heading_1']")
	private static WebElement grid_Header_Item;

	@FindBy(xpath = "//th[@id='id_transaction_entry_detail_table_control_heading_1']")
	private static WebElement grid_Header_DebitAC;

	@FindBy(xpath = "//th[@id='id_transaction_entry_detail_table_control_heading_2']")
	private static WebElement grid_Header_CreditAC;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[1]/td[1]")
	private static WebElement firstRowIndex;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[2]/td[1]")
	private static WebElement secondRowIndex;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[3]/td[1]")
	private static WebElement thirdRowIndex;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[4]/td[1]")
	private static WebElement fourthRowIndex;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[5]/td[1]")
	private static WebElement fifthRowIndex;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[6]/td[1]")
	private static WebElement sixthRowIndex;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[7]/td[1]")
	private static WebElement seventhRowIndex;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[8]/td[1]")
	private static WebElement eigthRowIndex;

	@FindBy(xpath = "//span[@class='icon-delete icon-font8']")
	private static WebElement deleteRowBtn;

	@FindBy(xpath = "//span[@class='icon-insertrow icon-font8']")
	private static WebElement insertRowBtn;

	@FindBy(xpath = "//span[@class='icon-selectall icon-font8']")
	private static WebElement selectRowBtn;

	@FindBy(xpath = "//span[@class='icon-unselectall1 icon-font8']")
	private static WebElement unSelectRowBtn;

	@FindBy(xpath = "//span[@class='icon-clone icon-font8']")
	private static WebElement duplicateRowBtn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[1]/td[2]")
	private static WebElement select1stRow_1stColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[1]/td[3]")
	private static WebElement select1stRow_2ndColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[1]/td[4]")
	private static WebElement select1stRow_3rdColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[1]/td[5]")
	private static WebElement select1stRow_4thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[1]/td[6]")
	private static WebElement select1stRow_5thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[1]/td[7]")
	private static WebElement select1stRow_6thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[1]/td[8]")
	private static WebElement select1stRow_7thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[1]/td[9]")
	private static WebElement select1stRow_8thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[1]/td[10]")
	private static WebElement select1stRow_9thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[1]/td[11]")
	private static WebElement select1stRow_10thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[1]/td[12]")
	private static WebElement select1stRow_11thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[1]/td[13]")
	private static WebElement select1stRow_12thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[1]/td[14]")
	private static WebElement select1stRow_13thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[1]/td[15]")
	private static WebElement select1stRow_14thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[1]/td[16]")
	private static WebElement select1stRow_15thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[1]/td[17]")
	private static WebElement select1stRow_16thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[2]/td[2]")
	private static WebElement select2ndRow_1stColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[2]/td[3]")
	private static WebElement select2ndRow_2ndColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[2]/td[4]")
	private static WebElement select2ndRow_3rdColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[2]/td[5]")
	private static WebElement select2ndRow_4thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[2]/td[6]")
	private static WebElement select2ndRow_5thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[2]/td[7]")
	private static WebElement select2ndRow_6thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[2]/td[8]")
	private static WebElement select2ndRow_7thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[2]/td[9]")
	private static WebElement select2ndRow_8thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[2]/td[10]")
	private static WebElement select2ndRow_9thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[2]/td[12]")
	private static WebElement select2ndRow_11thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[2]/td[13]")
	private static WebElement select2ndRow_12thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[2]/td[11]")
	private static WebElement select2ndRow_10thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[2]/td[15]")
	private static WebElement select2ndRow_14thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[2]/td[16]")
	private static WebElement select2ndRow_15thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[2]/td[17]")
	private static WebElement select2ndRow_16thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[3]/td[2]")
	private static WebElement select3rdRow_1stColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[3]/td[3]")
	private static WebElement select3rdRow_2ndColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[3]/td[4]")
	private static WebElement select3rdRow_3rdColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[3]/td[5]")
	private static WebElement select3rdRow_4thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[3]/td[6]")
	private static WebElement select3rdRow_5thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[3]/td[7]")
	private static WebElement select3rdRow_6thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[3]/td[8]")
	private static WebElement select3rdRow_7thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[3]/td[9]")
	private static WebElement select3rdRow_8thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[3]/td[10]")
	private static WebElement select3rdRow_9thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[3]/td[11]")
	private static WebElement select3rdRow_10thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[3]/td[12]")
	private static WebElement select3rdRow_11thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[3]/td[13]")
	private static WebElement select3rdRow_12thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[3]/td[14]")
	private static WebElement select3rdRow_13thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[3]/td[15]")
	private static WebElement select3rdRow_14thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[3]/td[16]")
	private static WebElement select3rdRow_15thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[3]/td[17]")
	private static WebElement select3rdRow_16thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[4]/td[2]")
	private static WebElement select4thRow_1stColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[4]/td[3]")
	private static WebElement select4thRow_2ndColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[4]/td[4]")
	private static WebElement select4thRow_3rdColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[4]/td[5]")
	private static WebElement select4thRow_4thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[4]/td[6]")
	private static WebElement select4thRow_5thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[4]/td[7]")
	private static WebElement select4thRow_6thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[4]/td[8]")
	private static WebElement select4thRow_7thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[4]/td[9]")
	private static WebElement select4thRow_8thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[4]/td[10]")
	private static WebElement select4thRow_9thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[4]/td[11]")
	private static WebElement select4thRow_10thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[4]/td[12]")
	private static WebElement select4thRow_11thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[4]/td[13]")
	private static WebElement select4thRow_12thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[4]/td[14]")
	private static WebElement select4thRow_13thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[4]/td[15]")
	private static WebElement select4thRow_14thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[4]/td[16]")
	private static WebElement select4thRow_15thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[4]/td[17]")
	private static WebElement select4thRow_16thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[5]/td[2]")
	private static WebElement select5thRow_1stColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[5]/td[3]")
	private static WebElement select5thRow_2ndColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[5]/td[4]")
	private static WebElement select5thRow_3rdColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[5]/td[5]")
	private static WebElement select5thRow_4thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[5]/td[6]")
	private static WebElement select5thRow_5thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[5]/td[7]")
	private static WebElement select5thRow_6thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[5]/td[8]")
	private static WebElement select5thRow_7thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[5]/td[9]")
	private static WebElement select5thRow_8thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[5]/td[10]")
	private static WebElement select5thRow_9thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[5]/td[11]")
	private static WebElement select5thRow_10thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[5]/td[12]")
	private static WebElement select5thRow_11thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[5]/td[13]")
	private static WebElement select5thRow_12thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[5]/td[14]")
	private static WebElement select5thRow_13thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[5]/td[15]")
	private static WebElement select5thRow_14thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[5]/td[16]")
	private static WebElement select5thRow_15thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[5]/td[17]")
	private static WebElement select5thRow_16thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[6]/td[2]")
	private static WebElement select6thRow_1stColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[6]/td[3]")
	private static WebElement select6thRow_2ndColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[6]/td[4]")
	private static WebElement select6thRow_3rdColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[6]/td[5]")
	private static WebElement select6thRow_4thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[6]/td[6]")
	private static WebElement select6thRow_5thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[6]/td[7]")
	private static WebElement select6thRow_6thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[6]/td[8]")
	private static WebElement select6thRow_7thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[6]/td[9]")
	private static WebElement select6thRow_8thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[6]/td[10]")
	private static WebElement select6thRow_9thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[6]/td[11]")
	private static WebElement select6thRow_10thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[6]/td[12]")
	private static WebElement select6thRow_11thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[6]/td[13]")
	private static WebElement select6thRow_12thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[6]/td[14]")
	private static WebElement select6thRow_13thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[6]/td[15]")
	private static WebElement select6thRow_14thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[6]/td[16]")
	private static WebElement select6thRow_15thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[6]/td[17]")
	private static WebElement select6thRow_16thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[7]/td[2]")
	private static WebElement select7thRow_1stColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[7]/td[3]")
	private static WebElement select7thRow_2ndColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[7]/td[4]")
	private static WebElement select7thRow_3rdColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[7]/td[5]")
	private static WebElement select7thRow_4thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[7]/td[6]")
	private static WebElement select7thRow_5thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[7]/td[7]")
	private static WebElement select7thRow_6thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[7]/td[8]")
	private static WebElement select7thRow_7thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[7]/td[9]")
	private static WebElement select7thRow_8thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[7]/td[10]")
	private static WebElement select7thRow_9thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[8]/td[2]")
	private static WebElement select8thRow_1stColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[8]/td[3]")
	private static WebElement select8thRow_2ndColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[8]/td[4]")
	private static WebElement select8thRow_3rdColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[8]/td[5]")
	private static WebElement select8thRow_4thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[8]/td[6]")
	private static WebElement select8thRow_5thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[8]/td[7]")
	private static WebElement select8thRow_6thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[8]/td[8]")
	private static WebElement select8thRow_7thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[8]/td[9]")
	private static WebElement select8thRow_8thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[8]/td[10]")
	private static WebElement select8thRow_9thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[9]/td[2]")
	private static WebElement select9thRow_1stColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[9]/td[3]")
	private static WebElement select9thRow_2ndColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[9]/td[4]")
	private static WebElement select9thRow_3rdColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[9]/td[5]")
	private static WebElement select9thRow_4thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[9]/td[6]")
	private static WebElement select9thRow_5thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[9]/td[7]")
	private static WebElement select9thRow_6thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[9]/td[8]")
	private static WebElement select9thRow_7thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[9]/td[9]")
	private static WebElement select9thRow_8thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[9]/td[10]")
	private static WebElement select9thRow_9thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[10]/td[2]")
	private static WebElement select10thRow_1stColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[10]/td[3]")
	private static WebElement select10thRow_2ndColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[10]/td[4]")
	private static WebElement select10thRow_3rdColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[10]/td[5]")
	private static WebElement select10thRow_4thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[10]/td[6]")
	private static WebElement select10thRow_5thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[10]/td[7]")
	private static WebElement select10thRow_6thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[10]/td[8]")
	private static WebElement select10thRow_7thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[10]/td[9]")
	private static WebElement select10thRow_8thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[10]/td[10]")
	private static WebElement select10thRow_9thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[11]/td[2]")
	private static WebElement select11thRow_1stColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[11]/td[3]")
	private static WebElement select11thRow_2ndColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[11]/td[4]")
	private static WebElement select11thRow_3rdColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[11]/td[5]")
	private static WebElement select11thRow_4thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[11]/td[6]")
	private static WebElement select11thRow_5thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[11]/td[7]")
	private static WebElement select11thRow_6thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[11]/td[8]")
	private static WebElement select11thRow_7thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[11]/td[9]")
	private static WebElement select11thRow_8thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[11]/td[10]")
	private static WebElement select11thRow_9thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[12]/td[2]")
	private static WebElement select12thRow_1stColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[12]/td[3]")
	private static WebElement select12thRow_2ndColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[12]/td[4]")
	private static WebElement select12thRow_3rdColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[12]/td[5]")
	private static WebElement select12thRow_4thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[12]/td[6]")
	private static WebElement select12thRow_5thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[12]/td[7]")
	private static WebElement select12thRow_6thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[12]/td[8]")
	private static WebElement select12thRow_7thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[12]/td[9]")
	private static WebElement select12thRow_8thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[12]/td[10]")
	private static WebElement select12thRow_9thColumn;

	@FindBy(xpath = "//input[@id='id_body_12']")
	private static WebElement enter_AccountTxt;

	@FindBy(xpath = "//*[@id='id_body_12_input_image']/span")
	private static WebElement enter_Account_ExpansionBtn;

	@FindBy(xpath = "//*[@id='id_body_12_input_settings']/span")
	private static WebElement enter_Account_SettingsBtn;

	@FindBy(xpath = "//div[@id='id_header_12_customize_popup_container']")
	private static WebElement enter_Account_Settings_Container;

	@FindBy(xpath = "//div[@id='id_header_12_customize_popup_footer']//input[1]")
	private static WebElement enter_Account_Settings_StandardFieldsBtn;

	@FindBy(xpath = "//select[@id='id_header_12_customize_popup_standardfields_list']")
	private static WebElement enter_Account_Settings_StandardFieldsBtn_Fielddropdown;

	@FindBy(xpath = "//input[@id='id_header_12_customize_popup_standardfields_header']")
	private static WebElement enter_Account_Settings_StandardFieldsBtn_HeaderTxt;

	@FindBy(xpath = "//select[@id='id_header_12_customize_popup_standardfields_alignment']")
	private static WebElement enter_Account_Settings_StandardFieldsBtn_Alignmentdropdown;

	@FindBy(xpath = "//input[@id='id_header_12_customize_popup_standardfields_width']")
	private static WebElement enter_Account_Settings_StandardFieldsBtn_WidthTxt;

	@FindBy(xpath = "//div[contains(@class,'panel')]//input[3]")
	private static WebElement enter_Account_Settings_StandardFieldsBtn_OkBtn;

	@FindBy(xpath = "//div[contains(@class,'panel')]//input[4]")
	private static WebElement enter_Account_Settings_StandardFieldsBtn_CancelBtn;

	@FindBy(xpath = "//div[@id='id_header_12_search_container']//input[2]")
	private static WebElement enter_Account_Settings_DeleteColumnBtn;

	@FindBy(xpath = "//div[contains(@class,'panel')]//input[3]")
	private static WebElement enter_Account_Settings_OkBtn;

	@FindBy(xpath = "//div[contains(@class,'panel')]//input[4]")
	private static WebElement enter_Account_Settings_CancelBtn;

	@FindBy(xpath = "//input[@id='id_body_16']")
	private static WebElement enter_Amount;

	@FindBy(xpath = "//textarea[@id='id_body_16777219']")
	private static WebElement enter_Receipts_Remarks;

	@FindBy(xpath = "//textarea[@id='id_body_16777222']")
	private static WebElement enter_Payments_Remarks;

	@FindBy(xpath = "//textarea[@id='id_body_16777225']")
	private static WebElement enter_PettyCash_Remarks;

	@FindBy(xpath = "//textarea[@id='id_body_16777228']")
	private static WebElement enter_PostDatedReceipts_Remarks;

	@FindBy(xpath = "//textarea[@id='id_body_16777231']")
	private static WebElement enter_PostDatedPaymentss_Remarks;

	@FindBy(xpath = "//input[@id='id_body_23']")
	private static WebElement enter_ItemTxt;

	@FindBy(xpath = "//textarea[@id='id_body_16777360']")
	private static WebElement enterDescrptionTxt;

	@FindBy(xpath = "//*[@id='id_body_23_input_image']/span")
	private static WebElement item_ExpansionBtn;

	@FindBy(xpath = "//*[@id='id_body_23_input_settings']/span")
	private static WebElement item_SettingsBtn;

	@FindBy(xpath = "//div[@id='id_body_23_customize_popup_container']")
	private static WebElement item_Settings_Container;

	@FindBy(xpath = "//div[@id='id_body_23_customize_popup_footer']//input[1]")
	private static WebElement item_Settings_StandardFieldsBtn;

	@FindBy(xpath = "//select[@id='id_body_23_customize_popup_standardfields_list']")
	private static WebElement item_Settings_StandardFieldsBtn_Fielddropdown;

	@FindBy(xpath = "//input[@id='id_body_23_customize_popup_standardfields_header']")
	private static WebElement item_Settings_StandardFieldsBtn_HeaderTxt;

	@FindBy(xpath = "//select[@id='id_body_23_customize_popup_standardfields_alignment']")
	private static WebElement item_Settings_StandardFieldsBtn_Alignmentdropdown;

	@FindBy(xpath = "//input[@id='id_body_23_customize_popup_standardfields_width']")
	private static WebElement item_Settings_StandardFieldsBtn_WidthTxt;

	@FindBy(xpath = "//div[contains(@class,'panel')]//input[3]")
	private static WebElement item_Settings_StandardFieldsBtn_OkBtn;

	@FindBy(xpath = "//div[contains(@class,'panel')]//input[4]")
	private static WebElement item_Settings_StandardFieldsBtn_CancelBtn;

	@FindBy(xpath = "//div[@id='id_body_23_search_container']//input[2]")
	private static WebElement item_Settings_DeleteColumnBtn;

	@FindBy(xpath = "//div[contains(@class,'panel')]//input[3]")
	private static WebElement item_Settings_OkBtn;

	@FindBy(xpath = "//div[contains(@class,'panel')]//input[4]")
	private static WebElement item_Settings_CancelBtn;

	@FindBy(xpath = "//input[@id='id_body_24']")
	private static WebElement enter_UnitTxt;

	@FindBy(xpath = "//*[@id='id_body_24_input_image']/span")
	private static WebElement unit_ExpansionBtn;

	@FindBy(xpath = "//*[@id='id_body_24_input_settings']/span")
	private static WebElement unit_SettingsBtn;

	@FindBy(xpath = "//div[@id='id_body_24_customize_popup_container']")
	private static WebElement unit_Settings_Container;

	@FindBy(xpath = "//div[@id='id_body_24_customize_popup_footer']//input[1]")
	private static WebElement unit_Settings_StandardFieldsBtn;

	@FindBy(xpath = "//select[@id='id_body_24_customize_popup_standardfields_list']")
	private static WebElement unit_Settings_StandardFieldsBtn_Fielddropdown;

	@FindBy(xpath = "//input[@id='id_body_24_customize_popup_standardfields_header']")
	private static WebElement unit_Settings_StandardFieldsBtn_HeaderTxt;

	@FindBy(xpath = "//select[@id='id_body_24_customize_popup_standardfields_alignment']")
	private static WebElement unit_Settings_StandardFieldsBtn_Alignmentdropdown;

	@FindBy(xpath = "//input[@id='id_body_24_customize_popup_standardfields_width']")
	private static WebElement unit_Settings_StandardFieldsBtn_WidthTxt;

	@FindBy(xpath = "//div[contains(@class,'panel')]//input[3]")
	private static WebElement unit_Settings_StandardFieldsBtn_OkBtn;

	@FindBy(xpath = "//div[contains(@class,'panel')]//input[4]")
	private static WebElement unit_Settings_StandardFieldsBtn_CancelBtn;

	@FindBy(xpath = "//div[@id='id_body_24_search_container']//input[2]")
	private static WebElement unit_Settings_DeleteColumnBtn;

	@FindBy(xpath = "//div[contains(@class,'panel')]//input[3]")
	private static WebElement unit_Settings_OkBtn;

	@FindBy(xpath = "//div[contains(@class,'panel')]//input[4]")
	private static WebElement unit_Settings_CancelBtn;

	@FindBy(xpath = "//input[@id='id_body_26']")
	private static WebElement enter_Quantity;

	@FindBy(xpath = "//input[@id='id_body_27']")
	private static WebElement enter_Rate;

	@FindBy(xpath = "//input[@id='id_body_28']")
	private static WebElement enter_Gross;

	@FindBy(xpath = "//input[@id='id_body_13']")
	private static WebElement enter_Batch;

	@FindBy(xpath = "//input[@id='id_body_12']")
	private static WebElement enter_DebitACTxt;

	@FindBy(xpath = "//*[@id='id_body_12_input_image']/span")
	private static WebElement enter_DebitAC_ExpansionBtn;

	@FindBy(xpath = "//*[@id='id_body_12_input_settings']/span")
	private static WebElement enter_DebitAC_SettingsBtn;

	@FindBy(xpath = "//div[@id='id_header_12_customize_popup_container']")
	private static WebElement enter_DebitAC_Settings_Container;

	@FindBy(xpath = "//div[@id='id_header_12_customize_popup_footer']//input[1]")
	private static WebElement enter_DebitAC_Settings_StandardFieldsBtn;

	@FindBy(xpath = "//select[@id='id_header_12_customize_popup_standardfields_list']")
	private static WebElement enter_DebitAC_Settings_StandardFieldsBtn_Fielddropdown;

	@FindBy(xpath = "//input[@id='id_header_12_customize_popup_standardfields_header']")
	private static WebElement enter_DebitAC_Settings_StandardFieldsBtn_HeaderTxt;

	@FindBy(xpath = "//select[@id='id_header_12_customize_popup_standardfields_alignment']")
	private static WebElement enter_DebitAC_Settings_StandardFieldsBtn_Alignmentdropdown;

	@FindBy(xpath = "//input[@id='id_header_12_customize_popup_standardfields_width']")
	private static WebElement enter_DebitAC_Settings_StandardFieldsBtn_WidthTxt;

	@FindBy(xpath = "//div[contains(@class,'panel')]//input[3]")
	private static WebElement enter_DebitAC_Settings_StandardFieldsBtn_OkBtn;

	@FindBy(xpath = "//div[contains(@class,'panel')]//input[4]")
	private static WebElement enter_DebitAC_Settings_StandardFieldsBtn_CancelBtn;

	@FindBy(xpath = "//div[@id='id_header_12_search_container']//input[2]")
	private static WebElement enter_DebitAC_Settings_DeleteColumnBtn;

	@FindBy(xpath = "//div[contains(@class,'panel')]//input[3]")
	private static WebElement enter_DebitAC_Settings_OkBtn;

	@FindBy(xpath = "//div[contains(@class,'panel')]//input[4]")
	private static WebElement enter_DebitAC_Settings_CancelBtn;

	@FindBy(xpath = "//input[@id='id_body_39']")
	private static WebElement enter_CreditACTxt;

	@FindBy(xpath = "//*[@id='id_body_39_input_image']/span")
	private static WebElement enter_CreditAC_ExpansionBtn;

	@FindBy(xpath = "//*[@id='id_body_39_input_settings']/span")
	private static WebElement enter_CreditAC_SettingsBtn;

	@FindBy(xpath = "//div[@id='id_header_39_customize_popup_container']")
	private static WebElement enter_CreditAC_Settings_Container;

	@FindBy(xpath = "//div[@id='id_header_39_customize_popup_footer']//input[1]")
	private static WebElement enter_CreditAC_Settings_StandardFieldsBtn;

	@FindBy(xpath = "//select[@id='id_header_39_customize_popup_standardfields_list']")
	private static WebElement enter_CreditAC_Settings_StandardFieldsBtn_Fielddropdown;

	@FindBy(xpath = "//input[@id='id_header_39_customize_popup_standardfields_header']")
	private static WebElement enter_CreditAC_Settings_StandardFieldsBtn_HeaderTxt;

	@FindBy(xpath = "//select[@id='id_header_39_customize_popup_standardfields_alignment']")
	private static WebElement enter_CreditAC_Settings_StandardFieldsBtn_Alignmentdropdown;

	@FindBy(xpath = "//input[@id='id_header_39_customize_popup_standardfields_width']")
	private static WebElement enter_CreditAC_Settings_StandardFieldsBtn_WidthTxt;

	@FindBy(xpath = "//div[contains(@class,'panel')]//input[3]")
	private static WebElement enter_CreditAC_Settings_StandardFieldsBtn_OkBtn;

	@FindBy(xpath = "//div[contains(@class,'panel')]//input[4]")
	private static WebElement enter_CreditAC_Settings_StandardFieldsBtn_CancelBtn;

	@FindBy(xpath = "//div[@id='id_header_39_search_container']//input[2]")
	private static WebElement enter_CreditAC_Settings_DeleteColumnBtn;

	@FindBy(xpath = "//div[contains(@class,'panel')]//input[3]")
	private static WebElement enter_CreditAC_Settings_OkBtn;

	@FindBy(xpath = "//div[contains(@class,'panel')]//input[4]")
	private static WebElement enter_CreditAC_Settings_CancelBtn;

	@FindBy(xpath = "//input[@id='id_body_18']")
	private static WebElement enter_DebitTxt;

	@FindBy(xpath = "//input[@id='id_body_19']")
	private static WebElement enter_CreditTxt;

	@FindBy(xpath = "//textarea[@id='id_body_16777241']")
	private static WebElement enter_NonStandardJournalEntries_RemarksTxt;

	@FindBy(xpath = "//input[@id='id_body_536870916']")
	private static WebElement enter_Warehouse2Txt;

	@FindBy(xpath = "//*[@id='id_body_87_input_image']/span")
	private static WebElement enter_Warehouse2_ExpansionBtn;

	@FindBy(xpath = "//*[@id='id_body_87_input_settings']/span")
	private static WebElement enter_Warehouse2_SettingsBtn;

	@FindBy(xpath = "//div[@id='id_header_87_customize_popup_container']")
	private static WebElement enter_WareHouse2_Settings_Container;

	@FindBy(xpath = "//div[@id='id_header_87_customize_popup_footer']//input[1]")
	private static WebElement enter_WareHouse2_Settings_StandardFieldsBtn;

	@FindBy(xpath = "//select[@id='id_header_87_customize_popup_standardfields_list']")
	private static WebElement enter_WareHouse2_Settings_StandardFieldsBtn_Fielddropdown;

	@FindBy(xpath = "//input[@id='id_header_87_customize_popup_standardfields_header']")
	private static WebElement enter_WareHouse2_Settings_StandardFieldsBtn_HeaderTxt;

	@FindBy(xpath = "//select[@id='id_header_87_customize_popup_standardfields_alignment']")
	private static WebElement enter_WareHouse2_Settings_StandardFieldsBtn_Alignmentdropdown;

	@FindBy(xpath = "//input[@id='id_header_87_customize_popup_standardfields_width']")
	private static WebElement enter_WareHouse2_Settings_StandardFieldsBtn_WidthTxt;

	@FindBy(xpath = "//div[contains(@class,'panel')]//input[3]")
	private static WebElement enter_WareHouse2_Settings_StandardFieldsBtn_OkBtn;

	@FindBy(xpath = "//div[contains(@class,'panel')]//input[4]")
	private static WebElement enter_WareHouse2_Settings_StandardFieldsBtn_CancelBtn;

	@FindBy(xpath = "//div[@id='id_header_87_search_container']//input[2]")
	private static WebElement enter_WareHouse2_Settings_DeleteColumnBtn;

	@FindBy(xpath = "//div[contains(@class,'panel')]//input[3]")
	private static WebElement enter_WareHouse2_Settings_OkBtn;

	@FindBy(xpath = "//div[contains(@class,'panel')]//input[4]")
	private static WebElement enter_WareHouse2_Settings_CancelBtn;

	@FindBy(xpath = "//*[@id='ISOCurrencyCode_input_image']/span")
	private static WebElement isoCurrencyCodeComboboxButton;

	@FindBy(xpath = "//input[@id='sFileName1']")
	private static WebElement extraFields_DocumentTab_FileName;

	@FindBy(xpath = "//input[@id='biDocument1']")
	private static WebElement extraFields_DocumentTab_Document;

	@FindBy(xpath = "//tr[1]//td[5]//div[1]//div[1]//div[1]")
	private static WebElement extraFields_DocumentTab_UploadDocument;

	@FindBy(xpath = "//a[@id='SaveImg']")
	private static WebElement extraFields_DocumentTab_SaveDocument;

	@FindBy(xpath = "//i[@class='fa fa-eraser RemoveImg']")
	private static WebElement extraFields_DocumentTab_DeleteDocument;

	@FindBy(xpath = "//input[@id='iFileSize1']")
	private static WebElement extraFields_DocumentTab_FileSize;

	// Right Panel InfoSide Bar
	@FindBy(xpath = "//*[@id='id_transactionentry_infopanel_container']/div[1]/div[2]/span")
	private static WebElement infoSideBarCustomizeBtn;

	@FindBy(xpath = "//*[@id='id_transactionentry_infopanel_container']/div[1]/div[2]/span")
	private static WebElement customizeCloseBtn;

	@FindBy(xpath = "//div[@id='id_transactionentry_infopanel_customize_container']//li[@id='Dash_Save']")
	private static WebElement customizeSaveBtn;

	@FindBy(xpath = "//div[@id='id_transactionentry_infopanel_customize_container']//li[@id='Dash_Close']")
	private static WebElement customizeCancelBtn;

	@FindBy(xpath = "//input[@id='searchBoxTrans']")
	private static WebElement customizeSearchTxt;

	@FindBy(xpath = "//div[@id='Dashboard_Graph_panelID_Trans']//i[@class='icon-search searchicon']")
	private static WebElement customizeSearchBtn;

	@FindBy(xpath = "//div[@id='Dashboard_Graph_panelID_Trans']//i[@id='g']")
	private static WebElement graphBtn;

	@FindBy(xpath = "//div[@id='Dashboard_Graph_panelID_Trans']//li[@id='Newgraph']")
	private static WebElement newGraphOption;

	@FindBy(xpath = "//div[@id='Dashboard_Graph_panelID_Trans']//i[@id='r']")
	private static WebElement reportBtn;

	@FindBy(xpath = "//div[@id='Dashboard_Graph_panelID_Trans']//li[@id='NewReport']")
	private static WebElement newReportOption;

	@FindBy(xpath = "//div[@id='Dashboard_Graph_panelID_Trans']//i[@id='i']")
	private static WebElement infoPanelBtn;

	@FindBy(xpath = "//div[@id='Dashboard_Graph_panelID_Trans']//li[@id='NewInfopanel']")
	private static WebElement newInfoPanelOption;

	@FindBy(xpath = "//div[@id='Dashboard_Graph_panelID_Trans']//i[@id='w']")
	private static WebElement workFlowBtn;

	@FindBy(xpath = "//div[@id='Dashboard_Graph_panelID_Trans']//li[@id='NewWorkflow']")
	private static WebElement newWorkFlowOption;

	@FindBy(xpath = "//a[@id='DocumentInfoOption']")
	private static WebElement otherDashlets;

	@FindBy(xpath = "//*[@id='id_Dashlet0']")
	private static WebElement documentInfo;

	@FindBy(xpath = "//span[contains(@class,'icon-left-and-right-panel-icon icon-font6 no_padding_left_right')]")
	private static WebElement infoSideBarMinimizeExpandBtn;

	@FindBy(xpath = "//span[@class='icon-left-and-right-panel-icon icon-font6 no_padding_left_right']")
	private static WebElement infoSideBarExpandBtn;

	// Footer Section
	@FindBy(xpath = "//label[@id='id_transactionentry_footer_panel_summary_value_net']")
	private static WebElement netLabel;

	@FindBy(xpath = "//*[@id='id_transactionentry_footer_panel_summary_value_net']/span[2]")
	private static WebElement netAmount;

	// Login Page
	@FindBy(xpath = "//*[@id='txtUsername']")
	private static WebElement username;

	@FindBy(id = "txtPassword")
	private static WebElement password;

	@FindBy(id = "btnSignin")
	private static WebElement signIn;

	@FindBy(id = "ddlCompany")
	private static WebElement companyDropDownList;

	@FindBy(xpath = "//*[@id='companyLogo']")
	private static WebElement companyLogo;

	@FindBy(xpath = "//*[@id='ulCompanyDetails_HomePage']/li[1]")
	private static WebElement companyName;

	@FindBy(xpath = "//input[@id='donotshow']")
	private static WebElement doNotShowCheckbox;

	@FindBy(xpath = "//span[@class='pull-right']")
	private static WebElement closeBtnInDemoPopupScreen;

	// Server Error Elements
	@FindBy(xpath = "//div[@id='id_focus_msgbox_main']")
	private static WebElement serverErrorPopup;

	@FindBy(xpath = "//div[@id='id_focus_msgbox_main']/div[2]/button")
	private static WebElement serverErrorClose;

	public static boolean checkErrorMessageIsDisplayingOrNotDisplaying()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		System.out.println(
				"************************   checkErrorMessageIsDisplayingOrNotDisplaying Method Executes.............    ******************************");

		try {
			boolean actValidationMessageIsEmptyDisplay = validationConfirmationMessage.getText().isEmpty();
			boolean expValidationMessageIsEmptyDisplay = true;

			System.out.println("Error Message Is Displaying Or Not Displaying Value Actual : "
					+ actValidationMessageIsEmptyDisplay + " Value Expected : " + expValidationMessageIsEmptyDisplay);

			if (actValidationMessageIsEmptyDisplay == expValidationMessageIsEmptyDisplay) {
				// System.out.println("No Error Message Is Displaying On Opening Page,Click On
				// Add, Edit, Delete Etc...... In Method : " +
				// validationConfirmationMessage.getText());
				return true;
			} else {
				// System.err.println("Error Message Is Displaying On Opening Page, Click, Add,
				// Edit, Delete Etc..... In Method : " +
				// validationConfirmationMessage.getText());
				newErrorMessageCloseBtn.click();
				return false;
			}

		} catch (NoSuchElementException nse) {
			System.err.println("CATCH BLOCK : NO SUCH ELEMENT MESSAGE IS DISPLAY  :   " + nse.getMessage());
			return false;
		}
	}

	// Customize Master In Accounts Master Page
	@FindBy(xpath = "//a[@href='#HeaderDetails_0']")
	private static WebElement accountsCustomizeGeneralHeaderDetailsTab;

	@FindBy(xpath = "//a[@href='#HeaderDetails_1']")
	private static WebElement accountsCustomizeSettingsHeaderDetailsTab;

	@FindBy(xpath = "//a[@href='#BodyDetails_1']")
	private static WebElement accountsCustomizeSettingsBodyDetailsTab;

	@FindBy(xpath = "//a[@href='#HeaderDetails_2']")
	private static WebElement accountsCustomizeDetailsHeaderDetailsTab;

	@FindBy(xpath = "//a[@href='#BodyDetails_2']")
	private static WebElement accountsCustomizeDetailsBodyDetailsTab;

	@FindBy(xpath = "//a[@href='#HeaderDetails_3']")
	private static WebElement accountsCustomizePrintLayoutHeaderDetailsTab;

	@FindBy(xpath = "//a[@href='#BodyDetails_3']")
	private static WebElement accountsCustomizePrintLayoutBodyDetailsTab;

	@FindBy(xpath = "//a[@href='#HeaderDetails_4']")
	private static WebElement accountsCustomizeTreeTabHeaderDetailsTab;

	@FindBy(xpath = "//a[@href='#BodyDetails_4']")
	private static WebElement accountsCustomizeTreeTabBodyDetailsTab;

	@FindBy(xpath = "//a[@href='#HeaderDetails_5']")
	private static WebElement accountCustomizeDocumetTabHeaderDetailsTab;

	@FindBy(xpath = "//a[@href='#BodyDetails_5']")
	private static WebElement accountCustomizeDocumetTabBodyDetailsTab;

	@FindBy(xpath = "//a[@href='#HeaderDetails_6']")
	private static WebElement accountCustomizeCreateTabHeaderDetailsTab;

	@FindBy(xpath = "//a[@href='#BodyDetails_6']")
	private static WebElement accountCustomizeCreateTabBodyDetailsTab;

	@FindBy(xpath = "//*[@id='HeaderDetails_1']/div/div/table/tbody/tr[9]/td[1]/i[1]")
	private static WebElement accountSettingsHeaderEditNinethRowBtn;

	@FindBy(xpath = "//*[@id='HeaderDetails_1']/div/div/table/tbody/tr[10]/td[1]/i[1]")
	private static WebElement accountSettingsHeaderEditTenthRowBtn;

	@FindBy(xpath = "//*[@id='navigation_menu']/li/a/div/div")
	private static List<WebElement> menusList;

	@FindBy(xpath = "//*[@id='navigation_menu']/li[1]/ul/li/a/span")
	private static List<WebElement> homeMenuList;

	@FindBy(xpath = "//*[@id='navigation_menu']/li[1]/ul/li[3]/ul/li/a/span")
	private static List<WebElement> mastersMenuList;

	@FindBy(xpath = "//*[@id='ulCommonlyUsedRibbon']/li/span")
	private static List<WebElement> mastersRibbonControlList;

	@FindBy(xpath = "//*[@id='ulRibbonControl']/li/span")
	private static List<WebElement> ribbonControlExpandList;

	@FindBy(xpath = "//span[@id='updateButton']")
	private static WebElement updateBtn;

	// Home Menu
	@FindBy(xpath = "//*[@id='1']")
	public static WebElement homeMenu;

	// Inventory Menu
	/*
	 * @FindBy (xpath="//span[@class='icon-inventory icon-font1']") private static
	 * WebElement inventoryMenu;
	 */
	// Fixed Assets Menu
	@FindBy(xpath = "//span[@class='icon-assets icon-font1']")
	private static WebElement fixedAssetsMenu;

	// Production Menu
	@FindBy(xpath = "//span[@class='icon-production icon-font1']")
	private static WebElement productionMenu;

	// Point Of Sale Menu
	@FindBy(xpath = "//div[contains(text(),'Point of Sale')]")
	private static WebElement pointOfSaleMenu;

	// Quality Of Control Menu
	@FindBy(xpath = "//span[@class='icon-quotationanalysis icon-font1']")
	private static WebElement qualityControlMenu;

	// Settings Menu
	@FindBy(xpath = "//span[@class='icon-settings icon-font1']")
	private static WebElement SettingsMenu;

	// Company Menu
	@FindBy(xpath = "//*[@id='2']/span")
	private static WebElement companyMenu;

	// Security Menu
	@FindBy(xpath = "//*[@id='6']/span")
	private static WebElement securityMenu;

	// Masters Menu
	@FindBy(xpath = "//*[@id='1000']/span")
	private static WebElement mastersMenu;

	// DataManagement menu
	@FindBy(xpath = "//*[@id='25']/span")
	private static WebElement dataMangementMenu;

	// Utilities Menu
	@FindBy(xpath = "//*[@id='20']/span")
	private static WebElement utilities;

	@FindBy(xpath = "//*[@id='114']/span")
	private static WebElement repostVoucher;

	// In Masters Menu

	// Accounts
	@FindBy(xpath = "//a[@id='1104']//span[contains(text(),'Account')]")
	public static WebElement accounts;

	@FindBy(xpath = "//*[@id='220']/span")
	private static WebElement currency;

	@FindBy(xpath = "//*[@id='221']/span")
	private static WebElement products;

	@FindBy(xpath = "//*[@id='224']/span")
	private static WebElement mrp;

	@FindBy(xpath = "//a[@id='225']//span[contains(text(),'QC')]")
	private static WebElement qc;

	@FindBy(xpath = "//*[@id='251']/span")
	private static WebElement maintainance;

	@FindBy(xpath = "//*[@id='8001']/span")
	private static WebElement pointOfSale;

	@FindBy(xpath = "//*[@id='1106']/span")
	private static WebElement departments;

	@FindBy(xpath = "//*[@id='1107']/span")
	private static WebElement warehouse;

	@FindBy(xpath = "//*[@id='1108']/span")
	private static WebElement costcenter;

	@FindBy(xpath = "//*[@id='1109']/span")
	private static WebElement location;

	@FindBy(xpath = "//*[@id='1110']/span")
	private static WebElement region;

	@FindBy(xpath = "//*[@id='1111']/span")
	private static WebElement country;

	@FindBy(xpath = "//*[@id='1112']/span")
	private static WebElement state;

	@FindBy(xpath = "//*[@id='1113']/span")
	private static WebElement city;

	@FindBy(xpath = "//*[@id='1115']/span")
	private static WebElement bins;

	@FindBy(xpath = "//*[@id='1126']/span")
	private static WebElement insurance;

	@FindBy(xpath = "//*[@id='1127']/span")
	private static WebElement fixedassets;

	@FindBy(xpath = "//*[@id='1161']/span")
	private static WebElement taxCode;

	@FindBy(xpath = "//*[@id='1103']/span")
	private static WebElement payroll;

	@FindBy(xpath = "//*[@id='8002']/span")
	private static WebElement schemes;

	@FindBy(xpath = "//ul[@id='preferenceUL']//a[@id='37']")
	private static WebElement PronghornBtn;

	@FindBy(xpath = "//*[@id='preferenceUL']/li/a")
	private static List<WebElement> preferenceList;

	@FindBy(xpath = "//input[@id='chkARTag']")
	private static WebElement arTagChkBox;

	@FindBy(xpath = "//input[@id='optARTag']")
	private static WebElement arTagTxt;

	@FindBy(xpath = "//input[@id='chkARSalesAC']")
	private static WebElement salesAccountChkBox;

	@FindBy(xpath = "//input[@id='chkARProduct']")
	private static WebElement arItemChkBox;

	@FindBy(xpath = "//input[@id='chkEnableCreditLimitCheck']")
	private static WebElement enableCreditLimitCheckChkBox;

	@FindBy(xpath = "//input[@id='chkIncludePendingSalesOrderValueInCreditLimitCheck']")
	private static WebElement includePendingSalesOrderChkBox;

	@FindBy(xpath = "//input[@id='chkCheckcreditlimitinorders']")
	private static WebElement checkCreditLimitInOrdersChkBox;

	@FindBy(xpath = "//input[@id='chkDefineCreditLimitByDepartment']")
	private static WebElement defineCreditLimitByDepartmentChkBox;

	@FindBy(xpath = "//input[@id='chkAllowCreditLimitAuthorizationMappingToCustomer Group']")
	private static WebElement allowCreditLimitAuthorizationmappingChkBox;

	@FindBy(xpath = "//input[@id='dvExceed_0']")
	private static WebElement whenCreditLimitexceeded_WarnAndAllowRadio;

	@FindBy(xpath = "//input[@id='dvExceed_1']")
	private static WebElement whenCreditLimitexceeded_StopRadio;

	@FindBy(xpath = "//input[@id='dvExceed_2']")
	private static WebElement RequestCreditLimitIncreaseRadio;

	@FindBy(xpath = "//input[@id='chkPickCreditDayFromSalesAC']")
	private static WebElement pickCreditDayFromChkBox;

	@FindBy(xpath = "//input[@id='dvPickCreditDayFromSalesAC_0']")
	private static WebElement customerVendorACRadio;

	@FindBy(xpath = "//input[@id='dvPickCreditDayFromSalesAC_1']")
	private static WebElement paymentsTermsRadio;

	@FindBy(xpath = "//input[@id='dvPickCreditDayFromSalesAC_2']")
	private static WebElement salesACRadio;

	@FindBy(xpath = "//input[@id='chkAPTag']")
	private static WebElement apTagChkBox;

	@FindBy(xpath = "//input[@id='optAPTag']")
	private static WebElement apTagTxt;

	@FindBy(xpath = "//input[@id='chkAPPurchaseAC']")
	private static WebElement purchaseAccountChkBox;

	@FindBy(xpath = "//input[@id='chkAPProduct']")
	private static WebElement apItemChkBox;

	@FindBy(xpath = "//input[@id='chkDependsonmaintainbillwiseforAR_AP']")
	private static WebElement dependsOnMaintainBillWiseForARAPChkBox;

	@FindBy(xpath = "//input[@id='chkMaintainARTransactionCurrencies']")
	private static WebElement maintainARTransactionCurrenciesChkBox;

	@FindBy(xpath = "//input[@id='chkMaintainAPTransactionCurrencies']")
	private static WebElement maintainAPTransactionCurrenciesChkBox;

	@FindBy(xpath = "//input[@id='chkInputNarrationInReferences']")
	private static WebElement inputNarrationInReferenceChkBox;

	@FindBy(xpath = "//input[@id='chkEnableOverDueCheck']")
	private static WebElement enableOverDueCheckChkBox;

	@FindBy(xpath = "//input[@id='chkCreditDaysInsteadOfDueDateForLimitCheck']")
	private static WebElement useCreditDaysInsteadOfDueDateChkBox;

	@FindBy(xpath = "//input[@id='dvOverdueExceed_0']")
	private static WebElement whenCreditDaysLimitexceeded_WarnAndAllowRadio;

	@FindBy(xpath = "//input[@id='dvOverdueExceed_1']")
	private static WebElement whenCreditDaysLimitexceeded_StopRadio;

	@FindBy(xpath = "//input[@id='chkAddFreeItems']")
	private static WebElement addfreeItemsonaNewLineChkBoxTOSel;

	@FindBy(xpath = "//input[@id='chkAddFreeItems']/following-sibling::span")
	private static WebElement addfreeItemsonaNewLineChkBox;

	@FindBy(xpath = "//input[@id='chkCalDueDtLRDt']")
	private static WebElement calculatedueDateFromLRDateChkBoxTOSel;

	@FindBy(xpath = "//input[@id='chkCreateCustomerProfilefields']")
	private static WebElement createCustomerProfileFieldsChkBoxTOSel;

	@FindBy(xpath = "//input[@id='chkDialogbasedentryinvouchers']")
	private static WebElement dialogbasedentryinvouchersChkBoxTOSel;

	@FindBy(xpath = "//input[@id='chkDontRefreshDescACProdDoc']")
	private static WebElement dontRefreshDescACProdDocChkBoxTOSel;

	@FindBy(xpath = "//input[@id='chkIncUnCommitTransRpts']")
	private static WebElement includeUnCommitedTransactionChkBoxTOSel;

	@FindBy(xpath = "//input[@id='chkMntnLnksStkTransfer']")
	private static WebElement maintainLinksforOnlyOneSideChkBoxTOSel;

	@FindBy(xpath = "//input[@id='chkShowStatus']")
	private static WebElement showStatusMessageInPopUpTOSel;;

	@FindBy(xpath = "//input[@id='chkPrefixLocationCode']")
	private static WebElement prefixLocationCodeWhileImportingChkBoxTOSel;

	@FindBy(xpath = "//input[@id='chkShowTransactionInFifo']")
	private static WebElement showTransactionDateInFifoTOSel;;

	@FindBy(xpath = "//input[@id='chkEnableLocalCurrency']")
	private static WebElement enableLocalCurrencyChkBoxTOSel;

	@FindBy(xpath = "//input[@id='optLocalCurrency']")
	private static WebElement localCurrencyTxtTOSel;;

	@FindBy(xpath = "//input[@id='chkDontShowOpeningBalLedger']")
	private static WebElement dontShowOpeningBalLedgerChkBoxTOSel;

	@FindBy(xpath = "//input[@id='chkDontStoreDatesEntries']")
	private static WebElement dontStoreDatesEntriesChkBoxTOSel;

	@FindBy(xpath = "//input[@id='chkShowexchangeratedifferenceinledger']")
	private static WebElement showexchangeratedifferenceinledgerChkBoxTOSel;

	@FindBy(xpath = "//input[@id='chkEnableHijriDate']")
	private static WebElement enableHijriDateChkBoxTOSel;

	@FindBy(xpath = "//input[@id='chkEnableprofitabilitycheckbyproduct']")
	private static WebElement enableprofitabilitycheckbyproductChkBoxTOSel;

	@FindBy(xpath = "//input[@id='chkDonotLoadDocInExlusivemode']")
	private static WebElement donotLoadDocInExlusivemodeChkBoxTOSel;

	@FindBy(xpath = "//input[@id='chkCreateMasterinTransactionEntry']")
	private static WebElement createMasterinTransactionEntryChkBoxTOSel;

	@FindBy(xpath = "//input[@id='chkNextVoucherNoAftSaveVoucher']")
	private static WebElement stayOnSameVoucherNumberAfterDeleteingChkBoxTOSel;

	@FindBy(xpath = "//input[@id='chkDonotModifyDoc']")
	private static WebElement doNotModifiedDocumentIfTriggerIsRaisedTOSel;;

	@FindBy(xpath = "//input[@id='chkOpenSearchIfMasterNotFound']")
	private static WebElement openSearchIfMasterNotFoundChkBoxTOSel;

	@FindBy(xpath = "//input[@id='chkloadDateBasesonLastSavedVoucher']")
	private static WebElement loadDateBasesonLastSavedVoucherChkBoxTOSel;

	@FindBy(xpath = "//input[@id='idfiltercurrencyBasedondepartment']")
	private static WebElement filtercurrencyBasedondepartmentChkBoxTOSel;

	/////////////////////////

	@FindBy(xpath = "//input[@id='chkCalDueDtLRDt']/following-sibling::span")
	private static WebElement calculatedueDateFromLRDateChkBox;

	@FindBy(xpath = "//input[@id='chkCreateCustomerProfilefields']/following-sibling::span")
	private static WebElement createCustomerProfileFieldsChkBox;

	@FindBy(xpath = "//input[@id='chkDialogbasedentryinvouchers']/following-sibling::span")
	private static WebElement dialogbasedentryinvouchersChkBox;

	@FindBy(xpath = "//input[@id='chkDontRefreshDescACProdDoc']/following-sibling::span")
	private static WebElement dontRefreshDescACProdDocChkBox;

	@FindBy(xpath = "//input[@id='chkIncUnCommitTransRpts']/following-sibling::span")
	private static WebElement includeUnCommitedTransactionChkBox;

	@FindBy(xpath = "//input[@id='chkMntnLnksStkTransfer']/following-sibling::span")
	private static WebElement maintainLinksforOnlyOneSideChkBox;

	@FindBy(xpath = "//input[@id='chkShowStatus']/following-sibling::span")
	private static WebElement showStatusMessageInPopUpChkox;

	@FindBy(xpath = "//input[@id='chkShowStatus']")
	private static WebElement showStatusMessageInPopUpChkoxTosel;

	@FindBy(xpath = "//input[@id='chkPrefixLocationCode']/following-sibling::span")
	private static WebElement prefixLocationCodeWhileImportingChkBox;

	@FindBy(xpath = "//input[@id='chkShowTransactionInFifo']/following-sibling::span")
	private static WebElement showTransactionDateInFifo;

	@FindBy(xpath = "//input[@id='chkEnableLocalCurrency']/following-sibling::span")
	private static WebElement enableLocalCurrencyChkBox;

	@FindBy(xpath = "//input[@id='optLocalCurrency']/following-sibling::span")
	private static WebElement localCurrencyTxt;

	@FindBy(xpath = "//input[@id='chkDontShowOpeningBalLedger']/following-sibling::span")
	private static WebElement dontShowOpeningBalLedgerChkBox;

	@FindBy(xpath = "//input[@id='chkDontStoreDatesEntries']/following-sibling::span")
	private static WebElement dontStoreDatesEntriesChkBox;

	@FindBy(xpath = "//input[@id='chkShowexchangeratedifferenceinledger']/following-sibling::span")
	private static WebElement showexchangeratedifferenceinledgerChkBox;

	@FindBy(xpath = "//input[@id='chkEnableHijriDate']/following-sibling::span")
	private static WebElement enableHijriDateChkBox;

	@FindBy(xpath = "//input[@id='chkEnableprofitabilitycheckbyproduct']/following-sibling::span")
	private static WebElement enableprofitabilitycheckbyproductChkBox;

	@FindBy(xpath = "//input[@id='chkDonotLoadDocInExlusivemode']/following-sibling::span")
	private static WebElement donotLoadDocInExlusivemodeChkBox;

	@FindBy(xpath = "//input[@id='chkCreateMasterinTransactionEntry']/following-sibling::span")
	private static WebElement createMasterinTransactionEntryChkBox;

	@FindBy(xpath = "//input[@id='chkNextVoucherNoAftSaveVoucher']/following-sibling::span")
	private static WebElement stayOnSameVoucherNumberAfterDeleteingChkBox;

	@FindBy(xpath = "//input[@id='chkDonotModifyDoc']/following-sibling::span")
	private static WebElement doNotModifiedDocumentIfTriggerIsRaised;

	@FindBy(xpath = "//input[@id='chkOpenSearchIfMasterNotFound']/following-sibling::span")
	private static WebElement openSearchIfMasterNotFoundChkBox;

	@FindBy(xpath = "//input[@id='chkloadDateBasesonLastSavedVoucher']/following-sibling::span")
	private static WebElement loadDateBasesonLastSavedVoucherChkBox;

	@FindBy(xpath = "//input[@id='idfiltercurrencyBasedondepartment']/following-sibling::span")
	private static WebElement filtercurrencyBasedondepartmentChkBox;
	////////////

	@FindBy(xpath = "//input[@id='optCalendar']")
	private static WebElement defaultCalanderTxt;

	@FindBy(xpath = "//select[@id='cmbImageformat']")
	private static WebElement imageFormatDropdown;

	@FindBy(xpath = "//select[@id='cmbNumericSep']")
	private static WebElement numericSeperatorDropdown1;

	@FindBy(xpath = "//select[@id='cmbFormula']")
	private static WebElement numericSeperatorDropdown2;

	@FindBy(xpath = "//input[@id='rdoDocTypeOrClass_0']")
	private static WebElement docTypeRadio;

	@FindBy(xpath = "//input[@id='rdoDocTypeOrClass_1']")
	private static WebElement docClassRadio;

	@FindBy(xpath = "//td[@id='gPrefMisExcludeVoucher_col_1-0']")
	private static WebElement gridFirstRowIndexBtn;

	@FindBy(xpath = "//td[@id='gPrefMisExcludeVoucher_col_2-0']")
	private static WebElement gridSecondRowIndexBtn;

	@FindBy(xpath = "//td[@id='gPrefMisExcludeVoucher_col_1-1']")
	private static WebElement gridVoucherFirstRow;

	@FindBy(xpath = "//td[@id='gPrefMisExcludeVoucher_col_2-1']")
	private static WebElement gridVoucherSecondRow;

	@FindBy(xpath = "//input[@id='rdoSendMailClass_0']")
	private static WebElement sendAsAttachmentRadio;

	@FindBy(xpath = "//input[@id='rdoSendMailClass_1']")
	private static WebElement sendASBodyRadio;

	@FindBy(xpath = "//input[@id='optMisExcludeVoucher']")
	private static WebElement VoucherTxt;

	@FindBy(xpath = "//*[@id='26']/span")
	private static WebElement backup;

	@FindBy(xpath = "//*[@id='111']/span")
	private static WebElement reindex;

	@FindBy(xpath = "//*[@id='115']/span")
	private static WebElement eraseAll;

	@FindBy(xpath = "//*[@id='216']/span")
	private static WebElement lockAndUnlock;

	public static boolean checkLoginForPerferenceOptions()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		//re_LunchBrowser();

		//Thread.sleep(3000);

		checkLoginToSelectedCompany("Automation Options Company", "su", "su");

		return true;

	}
	

	public static void relogin() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		getDriver().navigate().refresh();
		
		Thread.sleep(2569);
		
		logout();
		
		Thread.sleep(2569);
		
		restart();
		
		Thread.sleep(2356);
		
		//checkLoginForPerferenceOptions();
		
		//checkLoginToSelectedCompany("Automation Options Company", "su", "su");
		
		checkLogin();
		Thread.sleep(4000);
		
		
		
	}

	public static void checkRestoreOptionsCompanyAndLogin()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {

		checkRestoreOptionsCompanyAndLogin("Automation Options Company", "Automation Options Company");

		System.err.println(" Company Restored And Logined");

	}

	public static void loginAutomationCompany()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		getDriver().navigate().refresh();

		Thread.sleep(2000);

		getDriver().navigate().refresh();

		Thread.sleep(2000);

		LoginPage lp = new LoginPage(getDriver());

		String unamelt = "su";

		String pawslt = "su";

		lp.enterUserName(unamelt);

		lp.enterPassword(pawslt);

		String compname = "Automation Options Company";

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

		lp.clickOnSignInBtn();
	}

	public boolean checkPreferencesOption()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		excelReader = new ExcelReader(POJOUtility.getExcelPath());
		xlfile = getBaseDir() + "\\src\\main\\resources\\testdata\\FocusTestData.xlsx";

		Thread.sleep(3000);

		ClickUsingJs(settingsMenu);

		click(Setting_PerferenceMenu);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(QuotationAnalysisBtn));

		boolean actTags = TagsBtn.isDisplayed();
		boolean actAccounts = AccountsBtn.isDisplayed();
		boolean actBudget = BudgetBtn.isDisplayed();
		boolean actARAP = ARAPBtn.isDisplayed();
		boolean actMiscelleanous = MiselleanousBtn.isDisplayed();
		boolean actPDC = PDCBtn.isDisplayed();
		boolean actInventory = InventoryBtn.isDisplayed();
		boolean actBatch = BatchBtn.isDisplayed();
		boolean actRMA = RMABtn.isDisplayed();
		boolean actBins = BinsBtn.isDisplayed();
		boolean actPurchase = HirePurchaseBtn.isDisplayed();
		boolean actQuotationAnalysis = QuotationAnalysisBtn.isDisplayed();
		boolean actRFID = RFIDBtn.isDisplayed();
		boolean actAutoIndent = AutoIndentBtn.isDisplayed();

		boolean actABCAnalysis = ABCAnalysisBtn.isDisplayed();
		boolean actReport = ReportBtn.isDisplayed();
		boolean actInternet = InternetBtn.isDisplayed();
		boolean actMiscellaneous = MailSettingsBtn.isDisplayed();
		boolean actMasters = MastersBtn.isDisplayed();
		boolean actExternalModules = ExternalModulesBtn.isDisplayed();
		boolean actInfo = InfoBtn.isDisplayed();
		boolean actProduction = ProductionBtn.isDisplayed();

		boolean actMRP = MrpBtn.isDisplayed();
		boolean actFixedAssets = FixedAssetsBtn.isDisplayed();

		boolean actVAT = VatBtn.isDisplayed();
		// boolean actPOS = PosBtn.isDisplayed();
		boolean actWarehouseManagement = WareHouseManagementBtn.isDisplayed();
		boolean actLetterForCredit = LetterForCreditBtn.isDisplayed();

		boolean actPronghorn = PronghornBtn.isDisplayed();

		boolean expTags = true;
		boolean expAccounts = true;
		boolean expBudget = true;
		boolean expARAP = true;
		boolean expMiscelleanous = true;
		boolean expPDC = true;
		boolean expInventory = true;
		boolean expBatch = true;
		boolean expRMA = true;
		boolean expBins = true;
		boolean expPurchase = true;
		boolean expQuotationAnalysis = true;
		boolean expRFID = true;
		boolean expAutoIndent = true;

		boolean expABCAnalysis = true;
		boolean expReport = true;
		boolean expInternet = true;
		boolean expMiscellaneous = true;
		boolean expMasters = true;
		boolean expExternalModules = true;
		boolean expInfo = true;
		boolean expProduction = true;

		boolean expMRP = true;
		boolean expFixedAssets = true;
		boolean expVAT = true;
		boolean expPOS = true;
		boolean expWarehouseManagement = true;
		boolean expLetterForCredit = true;
		boolean expPronghorn = true;

		ArrayList<String> actpreferenceList = new ArrayList<String>();

		int preferenceListCount = preferenceList.size();

		System.out.println(" preferenceListCount  :  " + preferenceListCount);

		for (int i = 0; i < preferenceListCount; i++) {
			String data = preferenceList.get(i).getText();
			actpreferenceList.add(data);
		}

		String actpreferenceOptions = actpreferenceList.toString();
		String exppreferenceOptions = /* excelReader.getCellData(xlSheetName, 10, 7) */"[Tags, Accounts, Item Search Wizard, Inventory, Report, Internet, Masters, External Modules, Info, Production, MRP, Quality Control, Fixed Assets, VAT, POS, Warehouse Management, Letter of Credit, Pronghorn, AI Setting, WhatsApp, Banking Integration, Document Storage, E-Signing]";

		System.err.println("  Actual preferenceList :    " + actpreferenceOptions);
		System.err.println("  Expected  preferenceList : " + exppreferenceOptions);

		excelReader.setCellData(xlfile, xlSheetName, 10, 8, actpreferenceOptions);

		System.out.println("Tags Value Actual                : " + actTags + " Value Expected : " + expTags);
		System.out.println("Accounts Value Actual            : " + actAccounts + " Value Expected : " + expAccounts);
		System.out.println("Budget Value Actual              : " + actBudget + " Value Expected : " + expBudget);
		System.out.println("AR AP Value Actual               : " + actARAP + " Value Expected : " + expARAP);
		System.out.println(
				"Miscellaneous Value Actual       : " + actMiscelleanous + " Value Expected : " + expMiscelleanous);
		System.out.println("PDC Value Actual                 : " + actPDC + " Value Expected : " + expPDC);
		System.out.println("Inventory Value Actual           : " + actInventory + " Value Expected : " + expInventory);
		System.out.println("Batch Value Actual               : " + actBatch + " Value Expected : " + expBatch);
		System.out.println("RMA Value Actual                 : " + actRMA + " Value Expected : " + expRMA);
		System.out.println("Bins Value Actual                : " + actBins + " Value Expected : " + expBins);
		System.out.println("Purchase Value Actual            : " + actPurchase + " Value Expected : " + expPurchase);
		System.out.println("Quotation Analysis Value Actual  : " + actQuotationAnalysis + " Value Expected : "
				+ expQuotationAnalysis);
		System.out.println("RFID Menu Value Actual           : " + actRFID + " Value Expected : " + expRFID);
		System.out
				.println("Auto Indent Value Actual         : " + actAutoIndent + " Value Expected : " + expAutoIndent);

		System.out.println(
				"ABC Analysis Value Actual        : " + actABCAnalysis + " Value Expected : " + expABCAnalysis);
		System.out.println("Report Value Actual              : " + actReport + " Value Expected : " + expReport);
		System.out.println("Internet Value Actual            : " + actInternet + " Value Expected : " + expInternet);
		System.out.println(
				"Miscellaneous Value Actual       : " + actMiscellaneous + " Value Expected : " + expMiscellaneous);
		System.out.println("Masters Value Actual             : " + actMasters + " Value Expected : " + expMasters);
		System.out.println(
				"External Modules Value Actual    : " + actExternalModules + " Value Expected : " + expExternalModules);
		System.out.println("Info Value Actual                : " + actInfo + " Value Expected : " + expInfo);
		System.out
				.println("Production Value Actual          : " + actProduction + " Value Expected : " + expProduction);

		System.out.println("MRP Value Actual                 : " + actMRP + " Value Expected : " + expMRP);
		System.out.println(
				"Fixed Assets Value Actual        : " + actFixedAssets + " Value Expected : " + expFixedAssets);
		System.out.println("VAT Value Actual                 : " + actVAT + " Value Expected : " + expVAT);
		// System.out.println("POS Value Actual : " + actPOS + " Value Expected : " +
		// expPOS);
		System.out.println("Warehouse Mangement Value Actual : " + actWarehouseManagement + " Value Expected : "
				+ expWarehouseManagement);
		System.out.println(
				"Letter For Credit Value Actual   : " + actLetterForCredit + " Value Expected : " + expLetterForCredit);
		System.out.println("Pronghorn Value Actual           : " + actPronghorn + " Value Expected : " + expPronghorn);

		boolean displayResult = actTags == expTags && actAccounts == expAccounts && actBudget == expBudget
				&& actARAP == expARAP && actMiscelleanous == expMiscelleanous && actPDC == expPDC
				&& actInventory == expInventory && actBatch == expBatch && actRMA == expRMA && actBins == expBins
				&& actPurchase == expPurchase && actQuotationAnalysis == expQuotationAnalysis && actRFID == actRFID
				&& actAutoIndent == actAutoIndent && actABCAnalysis == expABCAnalysis && actReport == expReport
				&& actInternet == expInternet && actInternet == expInternet && actMiscellaneous == expMiscellaneous
				&& actMasters == expMasters && actExternalModules == expExternalModules && actInfo == expInfo
				&& actProduction == expProduction && actMRP == expMRP && actFixedAssets == expFixedAssets
				&& actVAT == expVAT && actWarehouseManagement == expWarehouseManagement
				&& actLetterForCredit == expLetterForCredit && actPronghorn == expPronghorn;

		String actDisplayResult = Boolean.toString(displayResult);
		String expDisplayResult = excelReader.getCellData(xlSheetName, 11, 7);

		excelReader.setCellData(xlfile, xlSheetName, 11, 8, actDisplayResult);
		System.out.println("actDisplayResult  : " + actDisplayResult + " Value Expected : " + expDisplayResult);
		if (/* actDisplayResult.equalsIgnoreCase(expDisplayResult)&& */
		actpreferenceOptions.equalsIgnoreCase(exppreferenceOptions)) {
			System.out.println("Test Pass : VerifyPreferencesOptions Elements Are Displayed As Expected");
			excelReader.setCellData(xlfile, xlSheetName, 9, 9, resPass);
			return true;
		} else {
			System.out.println("Test Fail :VerifyPreferencesOptions Elements Are Not Displayed As Expected");
			excelReader.setCellData(xlfile, xlSheetName, 9, 9, resFail);
			return false;
		}
	}

	public boolean checkMiscellaneousInPreferences()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		excelReader = new ExcelReader(POJOUtility.getExcelPath());
		xlfile = getBaseDir() + "\\src\\main\\resources\\testdata\\FocusTestData.xlsx";

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MiselleanousBtn));

		boolean Miselleanous = MiselleanousBtn.isDisplayed();

		String actMiselleanous = Boolean.toString(Miselleanous);
		String expMiselleanousBtn = excelReader.getCellData(xlSheetName, 13, 7);

		excelReader.setCellData(xlfile, xlSheetName, 13, 8, actMiselleanous);

		System.out.println("MiselleanousBtn  : " + actMiselleanous + " Value Expected : " + expMiselleanousBtn);

		if (actMiselleanous.equalsIgnoreCase(expMiselleanousBtn)) {
			System.out.println("Test Pass : Miscellaneous is Displayed In Preferences");
			excelReader.setCellData(xlfile, xlSheetName, 12, 9, resPass);
			return true;
		} else {
			System.out.println("Test Fail : Miscellaneous is NOT Displayed In Preferences");
			excelReader.setCellData(xlfile, xlSheetName, 12, 9, resFail);
			return false;
		}
	}

	public boolean checkMiscellaneousOptions()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		excelReader = new ExcelReader(POJOUtility.getExcelPath());
		xlfile = getBaseDir() + "\\src\\main\\resources\\testdata\\FocusTestData.xlsx";

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MiselleanousBtn));
		MiselleanousBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(addfreeItemsonaNewLineChkBox));

		boolean actaddfreeItemsonaNewLineChkBox = addfreeItemsonaNewLineChkBox.isDisplayed();
		boolean actcalculatedueDateFromLRDateChkBox = calculatedueDateFromLRDateChkBox.isDisplayed();
		boolean actcreateCustomerProfileFieldsChkBox = createCustomerProfileFieldsChkBox.isDisplayed();
		boolean actdialogbasedentryinvouchersChkBox = dialogbasedentryinvouchersChkBox.isDisplayed();
		boolean actdontRefreshDescACProdDocChkBox = dontRefreshDescACProdDocChkBox.isDisplayed();
		boolean actincludeUnCommitedTransactionChkBox = includeUnCommitedTransactionChkBox.isDisplayed();
		boolean actmaintainLinksforOnlyOneSideChkBox = maintainLinksforOnlyOneSideChkBox.isDisplayed();
		boolean actshowStatusMessageInPopUpChkox = showStatusMessageInPopUpChkox.isDisplayed();
		boolean actprefixLocationCodeWhileImportingChkBox = prefixLocationCodeWhileImportingChkBox.isDisplayed();
		boolean actshowTransactionDateInFifo = showTransactionDateInFifo.isDisplayed();
		boolean actenableLocalCurrencyChkBox = enableLocalCurrencyChkBox.isDisplayed();
		boolean actdontShowOpeningBalLedgerChkBox = dontShowOpeningBalLedgerChkBox.isDisplayed();
		boolean actdontStoreDatesEntriesChkBox = dontStoreDatesEntriesChkBox.isDisplayed();
		boolean actshowexchangeratedifferenceinledgerChkBox = showexchangeratedifferenceinledgerChkBox.isDisplayed();
		boolean actenableHijriDateChkBox = enableHijriDateChkBox.isDisplayed();
		boolean actenableprofitabilitycheckbyproductChkBox = enableprofitabilitycheckbyproductChkBox.isDisplayed();
		boolean actdonotLoadDocInExlusivemodeChkBox = donotLoadDocInExlusivemodeChkBox.isDisplayed();
		boolean actcreateMasterinTransactionEntryChkBox = createMasterinTransactionEntryChkBox.isDisplayed();
		boolean actstayOnSameVoucherNumberAfterDeleteingChkBox = stayOnSameVoucherNumberAfterDeleteingChkBox
				.isDisplayed();
		boolean actopenSearchIfMasterNotFoundChkBox = openSearchIfMasterNotFoundChkBox.isDisplayed();
		boolean actloadDateBasesonLastSavedVoucherChkBox = loadDateBasesonLastSavedVoucherChkBox.isDisplayed();
		boolean actfiltercurrencyBasedondepartmentChkBox = filtercurrencyBasedondepartmentChkBox.isDisplayed();
		boolean actdefaultCalanderTxt = defaultCalanderTxt.isDisplayed();
		boolean actdefaultCurrencyTxt = defaultCurrencyTxt.isDisplayed();
		boolean actimageFormatDropdown = imageFormatDropdown.isDisplayed();
		boolean actnumericSeperatorDropdown1 = numericSeperatorDropdown1.isDisplayed();
		boolean actnumericSeperatorDropdown2 = numericSeperatorDropdown2.isDisplayed();
		boolean actdocTypeRadio = docTypeRadio.isDisplayed();
		boolean actdocClassRadio = docClassRadio.isDisplayed();
		boolean actgridFirstRowIndexBtn = gridFirstRowIndexBtn.isDisplayed();
		boolean actgridSecondRowIndexBtn = gridSecondRowIndexBtn.isDisplayed();
		boolean actgridVoucherFirstRow = gridVoucherFirstRow.isDisplayed();
		boolean actgridVoucherSecondRow = gridVoucherSecondRow.isDisplayed();
		boolean actsendAsAttachmentRadio = sendAsAttachmentRadio.isDisplayed();
		boolean actsendASBodyRadio = sendASBodyRadio.isDisplayed();

		boolean expaddfreeItemsonaNewLineChkBox = true;
		boolean expcalculatedueDateFromLRDateChkBox = true;
		boolean expcreateCustomerProfileFieldsChkBox = true;
		boolean expdialogbasedentryinvouchersChkBox = true;
		boolean expdontRefreshDescACProdDocChkBox = true;
		boolean expincludeUnCommitedTransactionChkBox = true;
		boolean expmaintainLinksforOnlyOneSideChkBox = true;
		boolean expshowStatusMessageInPopUpChkox = true;
		boolean expprefixLocationCodeWhileImportingChkBox = true;
		boolean expshowTransactionDateInFifo = true;
		boolean expenableLocalCurrencyChkBox = true;
		boolean expdontShowOpeningBalLedgerChkBox = true;
		boolean expdontStoreDatesEntriesChkBox = true;
		boolean expshowexchangeratedifferenceinledgerChkBox = true;
		boolean expenableHijriDateChkBox = true;
		boolean expenableprofitabilitycheckbyproductChkBox = true;
		boolean expdonotLoadDocInExlusivemodeChkBox = true;
		boolean expcreateMasterinTransactionEntryChkBox = true;
		boolean expstayOnSameVoucherNumberAfterDeleteingChkBox = true;
		boolean expopenSearchIfMasterNotFoundChkBox = true;
		boolean exploadDateBasesonLastSavedVoucherChkBox = true;
		boolean expfiltercurrencyBasedondepartmentChkBox = true;
		boolean expdefaultCalanderTxt = true;
		boolean expdefaultCurrencyTxt = true;
		boolean expimageFormatDropdown = true;
		boolean expnumericSeperatorDropdown1 = true;
		boolean expnumericSeperatorDropdown2 = true;
		boolean expdocTypeRadio = true;
		boolean expdocClassRadio = true;
		boolean expgridFirstRowIndexBtn = true;
		boolean expgridSecondRowIndexBtn = true;
		boolean expgridVoucherFirstRow = true;
		boolean expgridVoucherSecondRow = true;
		boolean expsendAsAttachmentRadio = true;
		boolean expsendASBodyRadio = true;

		System.out.println("addfreeItemsonaNewLineChkBox                  : " + actaddfreeItemsonaNewLineChkBox
				+ " Value Expected : " + expaddfreeItemsonaNewLineChkBox);
		System.out.println("calculatedueDateFromLRDateChkBox              : " + actcalculatedueDateFromLRDateChkBox
				+ " Value Expected : " + expcalculatedueDateFromLRDateChkBox);
		System.out.println("createCustomerProfileFieldsChkBox             : " + actcreateCustomerProfileFieldsChkBox
				+ " Value Expected : " + expcreateCustomerProfileFieldsChkBox);
		System.out.println("dialogbasedentryinvouchersChkBox              : " + actdialogbasedentryinvouchersChkBox
				+ " Value Expected : " + expdialogbasedentryinvouchersChkBox);
		System.out.println("dontRefreshDescACProdDocChkBox                : " + actdontRefreshDescACProdDocChkBox
				+ " Value Expected : " + expdontRefreshDescACProdDocChkBox);
		System.out.println("includeUnCommitedTransactionChkBox            : " + actincludeUnCommitedTransactionChkBox
				+ " Value Expected : " + expincludeUnCommitedTransactionChkBox);
		System.out.println("maintainLinksforOnlyOneSideChkBox             : " + actmaintainLinksforOnlyOneSideChkBox
				+ " Value Expected : " + expmaintainLinksforOnlyOneSideChkBox);
		System.out.println("showStatusMessageInPopUpChkox                  : " + actshowStatusMessageInPopUpChkox
				+ " Value Expected : " + expshowStatusMessageInPopUpChkox);
		System.out
				.println("prefixLocationCodeWhileImportingChkBox        : " + actprefixLocationCodeWhileImportingChkBox
						+ " Value Expected : " + expprefixLocationCodeWhileImportingChkBox);
		System.out.println("showTransactionDateInFifo                     : " + actshowTransactionDateInFifo
				+ " Value Expected : " + expshowTransactionDateInFifo);
		System.out.println("enableLocalCurrencyChkBox                     : " + actenableLocalCurrencyChkBox
				+ " Value Expected : " + expenableLocalCurrencyChkBox);
		System.out.println("dontShowOpeningBalLedgerChkBox                : " + actdontShowOpeningBalLedgerChkBox
				+ " Value Expected : " + expdontShowOpeningBalLedgerChkBox);
		System.out.println("dontStoreDatesEntriesChkBox                   : " + actdontStoreDatesEntriesChkBox
				+ " Value Expected : " + expdontStoreDatesEntriesChkBox);
		System.out.println(
				"showexchangeratedifferenceinledgerChkBox      : " + actshowexchangeratedifferenceinledgerChkBox
						+ " Value Expected : " + expshowexchangeratedifferenceinledgerChkBox);
		System.out.println("enableHijriDateChkBox                         : " + actenableHijriDateChkBox
				+ " Value Expected : " + expenableHijriDateChkBox);
		System.out
				.println("enableprofitabilitycheckbyproductChkBox       : " + actenableprofitabilitycheckbyproductChkBox
						+ " Value Expected : " + expenableprofitabilitycheckbyproductChkBox);
		System.out.println("donotLoadDocInExlusivemodeChkBox              : " + actdonotLoadDocInExlusivemodeChkBox
				+ " Value Expected : " + expdonotLoadDocInExlusivemodeChkBox);
		System.out.println("createMasterinTransactionEntryChkBox          : " + actcreateMasterinTransactionEntryChkBox
				+ " Value Expected : " + expcreateMasterinTransactionEntryChkBox);
		System.out.println(
				"stayOnSameVoucherNumberAfterDeleteingChkBox   : " + actstayOnSameVoucherNumberAfterDeleteingChkBox
						+ " Value Expected : " + expstayOnSameVoucherNumberAfterDeleteingChkBox);
		System.out.println("openSearchIfMasterNotFoundChkBox              : " + actopenSearchIfMasterNotFoundChkBox
				+ " Value Expected : " + expopenSearchIfMasterNotFoundChkBox);
		System.out.println("loadDateBasesonLastSavedVoucherChkBox         : " + actloadDateBasesonLastSavedVoucherChkBox
				+ " Value Expected : " + exploadDateBasesonLastSavedVoucherChkBox);
		System.out.println("filtercurrencyBasedondepartmentChkBox         : " + actfiltercurrencyBasedondepartmentChkBox
				+ " Value Expected : " + expfiltercurrencyBasedondepartmentChkBox);
		System.out.println("defaultCalanderTxt                            : " + actdefaultCalanderTxt
				+ " Value Expected : " + expdefaultCalanderTxt);
		System.out.println("defaultCurrencyTxt                            : " + actdefaultCurrencyTxt
				+ " Value Expected : " + expdefaultCurrencyTxt);
		System.out.println("imageFormatDropdown                           : " + actimageFormatDropdown
				+ " Value Expected : " + expimageFormatDropdown);
		System.out.println("numericSeperatorDropdown1                     : " + actnumericSeperatorDropdown1
				+ " Value Expected : " + expnumericSeperatorDropdown1);
		System.out.println("numericSeperatorDropdown2                     : " + actnumericSeperatorDropdown2
				+ " Value Expected : " + expnumericSeperatorDropdown2);
		System.out.println("docTypeRadio                                  : " + actdocTypeRadio + " Value Expected : "
				+ expdocTypeRadio);
		System.out.println("docClassRadio                                 : " + actdocClassRadio + " Value Expected : "
				+ expdocClassRadio);
		System.out.println("gridFirstRowIndexBtn                          : " + actgridFirstRowIndexBtn
				+ " Value Expected : " + expgridFirstRowIndexBtn);
		System.out.println("gridSecondRowIndexBtn                         : " + actgridSecondRowIndexBtn
				+ " Value Expected : " + expgridSecondRowIndexBtn);
		System.out.println("gridVoucherFirstRow                           : " + actgridVoucherFirstRow
				+ " Value Expected : " + expgridVoucherFirstRow);
		System.out.println("gridVoucherSecondRow                          : " + actgridVoucherSecondRow
				+ " Value Expected : " + expgridVoucherSecondRow);
		System.out.println("sendAsAttachmentRadio                         : " + actsendAsAttachmentRadio
				+ " Value Expected : " + expsendAsAttachmentRadio);
		System.out.println("sendASBodyRadio                               : " + actsendASBodyRadio
				+ " Value Expected : " + expsendASBodyRadio);

		boolean actMethod = actaddfreeItemsonaNewLineChkBox == expaddfreeItemsonaNewLineChkBox
				&& actcalculatedueDateFromLRDateChkBox == expcalculatedueDateFromLRDateChkBox
				&& actcreateCustomerProfileFieldsChkBox == expcreateCustomerProfileFieldsChkBox
				&& actdialogbasedentryinvouchersChkBox == expdialogbasedentryinvouchersChkBox
				&& actdontRefreshDescACProdDocChkBox == expdontRefreshDescACProdDocChkBox
				&& actincludeUnCommitedTransactionChkBox == expincludeUnCommitedTransactionChkBox
				&& actmaintainLinksforOnlyOneSideChkBox == expmaintainLinksforOnlyOneSideChkBox
				&& actshowStatusMessageInPopUpChkox == expshowStatusMessageInPopUpChkox
				&& actprefixLocationCodeWhileImportingChkBox == expprefixLocationCodeWhileImportingChkBox
				&& actshowTransactionDateInFifo == expshowTransactionDateInFifo
				&& actenableLocalCurrencyChkBox == expenableLocalCurrencyChkBox
				&& actdontShowOpeningBalLedgerChkBox == expdontShowOpeningBalLedgerChkBox
				&& actdontStoreDatesEntriesChkBox == expdontStoreDatesEntriesChkBox
				&& actshowexchangeratedifferenceinledgerChkBox == expshowexchangeratedifferenceinledgerChkBox
				&& actenableHijriDateChkBox == expenableHijriDateChkBox
				&& actenableprofitabilitycheckbyproductChkBox == expenableprofitabilitycheckbyproductChkBox
				&& actdonotLoadDocInExlusivemodeChkBox == expdonotLoadDocInExlusivemodeChkBox
				&& actcreateMasterinTransactionEntryChkBox == expcreateMasterinTransactionEntryChkBox
				&& actstayOnSameVoucherNumberAfterDeleteingChkBox == expstayOnSameVoucherNumberAfterDeleteingChkBox
				&& actopenSearchIfMasterNotFoundChkBox == expopenSearchIfMasterNotFoundChkBox
				&& actloadDateBasesonLastSavedVoucherChkBox == exploadDateBasesonLastSavedVoucherChkBox
				&& actfiltercurrencyBasedondepartmentChkBox == expfiltercurrencyBasedondepartmentChkBox
				&& actdefaultCalanderTxt == expdefaultCalanderTxt && actdefaultCurrencyTxt == expdefaultCurrencyTxt
				&& actimageFormatDropdown == expimageFormatDropdown
				&& actnumericSeperatorDropdown1 == expnumericSeperatorDropdown1
				&& actnumericSeperatorDropdown2 == expnumericSeperatorDropdown2 && actdocTypeRadio == expdocTypeRadio
				&& actdocClassRadio == expdocClassRadio && actgridFirstRowIndexBtn == expgridFirstRowIndexBtn
				&& actgridSecondRowIndexBtn == expgridSecondRowIndexBtn
				&& actgridVoucherFirstRow == expgridVoucherFirstRow
				&& actgridVoucherSecondRow == expgridVoucherSecondRow
				&& actsendAsAttachmentRadio == expsendAsAttachmentRadio && actsendASBodyRadio == expsendASBodyRadio;

		String actResult = Boolean.toString(actMethod);
		String expResult = excelReader.getCellData(xlSheetName, 15, 7);

		excelReader.setCellData(xlfile, xlSheetName, 15, 8, actResult);

		if (actResult.equalsIgnoreCase(expResult)) {
			System.out.println("Test Pass : Miscellaneous Options Are As Expected");
			excelReader.setCellData(xlfile, xlSheetName, 14, 9, resPass);
			return true;

		} else {
			System.out.println("Test Fail : Miscellaneous Options Are NOT As Expected");
			excelReader.setCellData(xlfile, xlSheetName, 14, 9, resFail);
			return false;
		}
	}

	public boolean checkdontRefreshDescACProdDocChkBoxOptions()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {

		excelReader = new ExcelReader(POJOUtility.getExcelPath());
		xlfile = getBaseDir() + "\\src\\main\\resources\\testdata\\FocusTestData.xlsx";

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dontRefreshDescACProdDocChkBox));
		if (dontRefreshDescACProdDocChkBoxTOSel.isSelected() == false) {

			getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dontRefreshDescACProdDocChkBox));
			dontRefreshDescACProdDocChkBox.click();

		}

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(UpdateBtn));
		ClickUsingJs(UpdateBtn);

		getWaitForAlert();

		String actAlertMgs = getAlert().getText();
		String expAlertMgs = excelReader.getCellData(xlSheetName, 17, 7);

		excelReader.setCellData(xlfile, xlSheetName, 17, 8, actAlertMgs);

		System.out.println("AlertMessage     : " + actAlertMgs + " Value Expected : " + expAlertMgs);

		getAlert().accept();

		String expMessage = excelReader.getCellData(xlSheetName, 18, 7);

		String actMessage = checkValidationMessage(expMessage);

		excelReader.setCellData(xlfile, xlSheetName, 18, 8, actMessage);

		System.out.println(
				"********************************checkdontRefreshDescACProdDocChkBoxOptions***************************");

		System.out.println("Message     : " + actMessage + " Value Expected : " + expMessage);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(CloseBtn));
		CloseBtn.click();

	/*	logout();

		Thread.sleep(3000);
		prongHornStartAtAdminLevel();
		Thread.sleep(3000);

	//	checkLoginForPerferenceOptions();
		
		//checkLoginToSelectedCompany("Automation Options Company", "su", "su");
		checkLogin();*/
		Thread.sleep(4000);
		
		if (actMessage.equalsIgnoreCase(expMessage) && actAlertMgs.equalsIgnoreCase(expAlertMgs)) {

			System.out.println("Test Pass : Clicking On Ok Btn And Error Message Is as Expected");
			excelReader.setCellData(xlfile, xlSheetName, 16, 9, resPass);
			return true;
		} else {
			System.out.println("Test Pass : Clicking On Ok Btn And Error Message Is Not as Expected");
			excelReader.setCellData(xlfile, xlSheetName, 16, 9, resFail);
			return false;
		}

	}

	@FindBy(xpath = "//div[contains(text(),'Description')]")
	private static WebElement gridBodydescriptionTxt;

	@FindBy(xpath = "//*[@id='2010']")
	private static WebElement requestForQuoteVoucher;

	@FindBy(xpath = "//a[contains(text(),'Body')]")
	private static WebElement editLayoutBodyTab;

	public boolean checkSavingExtraFieldinVoucherBody()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		excelReader = new ExcelReader(POJOUtility.getExcelPath());
		xlfile = getBaseDir() + "\\src\\main\\resources\\testdata\\FocusTestData.xlsx";

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(inventoryMenu));
		inventoryMenu.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(invTransactionsMenu));
		invTransactionsMenu.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(invTransPurchasesMenu));
		invTransPurchasesMenu.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(requestForQuoteVoucher));
		clickOn(requestForQuoteVoucher);

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(settingsBtn));
		settingsBtn.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(editLayoutTab));
		editLayoutTab.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(editLayoutBodyTab));
		editLayoutBodyTab.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(editLayoutAddFieldsBtn));
		editLayoutAddFieldsBtn.click();

		Thread.sleep(5000);

		try {
			if (getIsAlertPresent()) {
				getAlert().accept();
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(editLayoutCaptionTxt));
		editLayoutCaptionTxt.click();

		Thread.sleep(2000);
		editLayoutCaptionTxt.sendKeys(excelReader.getCellData(xlSheetName, 20, 6));

		Thread.sleep(2000);
		editLayoutCaptionTxt.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(editLayoutApplyBtn));
		editLayoutApplyBtn.click();

		Thread.sleep(3000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(UpdateBtn));
		ClickUsingJs(UpdateBtn);

		String expMessage = excelReader.getCellData(xlSheetName, 21, 7);

		String actMessage = checkValidationMessage(expMessage);

		excelReader.setCellData(xlfile, xlSheetName, 21, 8, actMessage);

		System.out.println("Message     : " + actMessage + " Value Expected : " + expMessage);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(CloseBtn));
		CloseBtn.click();

		if (actMessage.equalsIgnoreCase(expMessage)) {

			System.out.println("Test Pass : Clicking On Ok Btn And Error Message Is as Expected");
			excelReader.setCellData(xlfile, xlSheetName, 19, 9, resPass);
			return true;

		} else {

			System.out.println("Test Pass : Clicking On Ok Btn And Error Message Is Not as Expected");
			excelReader.setCellData(xlfile, xlSheetName, 19, 9, resFail);
			return false;
		}

	}

	@FindBy(xpath = "//*[@id='221']/span")
	private static WebElement homeMasterItemMenu;

	@FindBy(xpath = "//*[@id='1105']/span")
	private static WebElement homeMasterItem_ItemMenu;

	@FindBy(xpath = "//*[@id='LandingGridBody']/tr/td[12]")
	private static List<WebElement> itemList;

	@FindBy(xpath = "//*[@id='LandingGridBody']/tr/td[8]/div/label/input")
	private static List<WebElement> itemListChkBox;

	@FindBy(xpath = "//*[@id='btnMasterEdit']")
	private static WebElement itemEditBtn;

	public boolean checkSavingMasterItemWithDescrption()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		excelReader = new ExcelReader(POJOUtility.getExcelPath());
		xlfile = getBaseDir() + "\\src\\main\\resources\\testdata\\FocusTestData.xlsx";

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(homeMenu));
		homeMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(mastersMenu));
		mastersMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(homeMasterItemMenu));
		homeMasterItemMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(homeMasterItem_ItemMenu));
		homeMasterItem_ItemMenu.click();

		Thread.sleep(2000);

		int itemListCount = itemList.size();

		for (int i = 0; i < itemListCount; i++) {
			String data = itemList.get(i).getText();

			if (data.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 22, 6))) {
				itemList.get(i).click();
				itemListChkBox.get(i).click();

				break;
			}
		}

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemEditBtn));
		itemEditBtn.click();

		Thread.sleep(5000);
		
		getAction().moveToElement(newGeneralDescription).build().perform();
		Thread.sleep(1500);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(newGeneralDescription));
		newGeneralDescription.click();

		Thread.sleep(2000);
		newGeneralDescription.sendKeys(excelReader.getCellData(xlSheetName, 23, 6));

		Thread.sleep(2000);

		newGeneralDescription.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(newGeneralSaveBtn));
		newGeneralSaveBtn.click();

		String expMessage = excelReader.getCellData(xlSheetName, 24, 7);

		String actMessage = checkValidationMessage(expMessage);

		excelReader.setCellData(xlfile, xlSheetName, 24, 8, actMessage);

		System.out.println("Message     : " + actMessage + " Value Expected : " + expMessage);

		if (actMessage.equalsIgnoreCase(expMessage)) {

			System.out.println("Test Pass : Clicking On Ok Btn And Error Message Is as Expected");
			excelReader.setCellData(xlfile, xlSheetName, 22, 9, resPass);
			return true;

		} else {

			System.out.println("Test Pass : Clicking On Ok Btn And Error Message Is Not as Expected");
			excelReader.setCellData(xlfile, xlSheetName, 22, 9, resFail);
			return false;
		}
	}

	public boolean checkDescrpitionFieldAddedinVoucherBody()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		excelReader = new ExcelReader(POJOUtility.getExcelPath());
		xlfile = getBaseDir() + "\\src\\main\\resources\\testdata\\FocusTestData.xlsx";

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(inventoryMenu));
		inventoryMenu.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(invTransactionsMenu));
		invTransactionsMenu.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(invTransPurchasesMenu));
		invTransPurchasesMenu.click();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(requestForQuoteVoucher));
		requestForQuoteVoucher.click();

		Thread.sleep(2000);

		click(newBtn);
		Thread.sleep(2000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(gridBodydescriptionTxt));
		boolean GridBodydescriptionTxt = gridBodydescriptionTxt.isDisplayed();

		String actgridBodydescriptionTxt = Boolean.toString(GridBodydescriptionTxt);

		String expgridBodydescriptionTxt = excelReader.getCellData(xlSheetName, 25, 7);
		excelReader.setCellData(xlfile, xlSheetName, 25, 8, actgridBodydescriptionTxt);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_1stColumn));
		select1stRow_1stColumn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_ItemTxt));
		enter_ItemTxt.click();
		Thread.sleep(2000);
		
		enter_ItemTxt.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME);

		Thread.sleep(2000);

		enter_ItemTxt.sendKeys(excelReader.getCellData(xlSheetName, 26, 6));
		Thread.sleep(2000);

		enter_ItemTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_3rdColumn));
		select1stRow_3rdColumn.click();

		String actDescrption = select1stRow_2ndColumn.getText();
		String expDescrption = excelReader.getCellData(xlSheetName, 26, 7);
		excelReader.setCellData(xlfile, xlSheetName, 26, 8, actDescrption);

		System.err.println("actDescrption   : " + actDescrption);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_1stColumn));
		select1stRow_1stColumn.click();
		Thread.sleep(2000);

		enter_ItemTxt.sendKeys(Keys.SPACE);

		Thread.sleep(2000);

		enter_ItemTxt.click();
		Thread.sleep(2000);
		enter_ItemTxt.sendKeys(excelReader.getCellData(xlSheetName, 27, 6));
		Thread.sleep(2000);

		enter_ItemTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_3rdColumn));
		select1stRow_3rdColumn.click();

		String actDescrption1 = select1stRow_2ndColumn.getText();
		String expDescrption1 = excelReader.getCellData(xlSheetName, 27, 7);
		excelReader.setCellData(xlfile, xlSheetName, 27, 8, actDescrption1);

		System.err.println("actual Descrption After Reenter  : " + actDescrption1);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(new_CloseBtn));
		new_CloseBtn.click();

		/*
		 * getWaitForAlert();
		 * 
		 * getAlert().accept();
		 */

		clickOn(popUpOKBtn);

		if (actDescrption.equalsIgnoreCase(expDescrption) && actDescrption1.equalsIgnoreCase(expDescrption1)
				&& actgridBodydescriptionTxt.equalsIgnoreCase(expgridBodydescriptionTxt)) {

			System.out.println("Test Pass : Descrpition Column Remain Unchanged ");
			excelReader.setCellData(xlfile, xlSheetName, 25, 9, resPass);
			return true;

		} else {

			System.out.println("Test Fail : Descrpition Column Remain changed ");
			excelReader.setCellData(xlfile, xlSheetName, 25, 9, resFail);
			return false;
		}

	}

	public boolean checkSavingVoucherWithItemDescrption()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		
		
		Thread.sleep(4000);
		
		
		click(newBtn);

		Thread.sleep(2000);

		click(vendorAccountTxt);
		vendorAccountTxt.sendKeys(Keys.SPACE);

		selectionElementFromList(vendorAccountListCount, "Vendor A");
		Thread.sleep(2000);
		vendorAccountTxt.sendKeys(Keys.TAB);

		click(select1stRow_1stColumn);
		enter_ItemTxt.sendKeys(Keys.SPACE);
		selectionElementFromList(itemListCount, "BATCH BR ITEM");
		Thread.sleep(2000);
		enter_ItemTxt.sendKeys(Keys.TAB);

		click(select1stRow_3rdColumn);
		Thread.sleep(2000);

		click(select1stRow_4thColumn);
		Thread.sleep(2000);

		enter_Quantity.sendKeys("10");
		Thread.sleep(2000);
		enter_Quantity.sendKeys(Keys.TAB);

		enter_Rate.sendKeys("5.5");
		Thread.sleep(2000);
		enter_Rate.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		click(select2ndRow_1stColumn);
		enter_ItemTxt.sendKeys(Keys.SPACE);
		selectionElementFromList(itemListCount, "BATCH BIN FINISHED GOODS ITEM");
		Thread.sleep(2000);
		enter_ItemTxt.sendKeys(Keys.TAB);

		click(select2ndRow_3rdColumn);
		Thread.sleep(2000);

		click(select2ndRow_4thColumn);
		Thread.sleep(2000);

		enter_Quantity.sendKeys("10");
		Thread.sleep(2000);
		enter_Quantity.sendKeys(Keys.TAB);

		enter_Rate.sendKeys("5.5");
		Thread.sleep(2000);
		enter_Rate.sendKeys(Keys.TAB);

		Thread.sleep(2345);

		String docno = documentNumberTxt.getAttribute("value");

		click(voucherSaveBtn);

		boolean expMessage = true;
		boolean actMessage = checkVoucherSavingMessage(docno);

		if (actMessage == expMessage) {

			return true;
		} else {
			return false;

		}

	}

	public boolean checkCopyAndPasteOptionWithItemDescrption()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		click(previousBtn);

		Thread.sleep(3500);

		waitToClick(toggleBtn);

		//clickOn(toggleBtn);

		click(copytoClipboardBtn);

		Thread.sleep(1500);

		click(nextBtn);

		Thread.sleep(1500);

		click(toggleBtn);

		click(pastefromClipboardBtn);

		checkValidationMessage("");
		
		Thread.sleep(2569);

		String actList = listOfElements(entryPageRow1List);
		String expList = "[1, BATCH BR ITEM, BATCH Description, Dozs, 10.00, 5.50, 55.00, 0.00]";

		System.err.println(" Act List: " + actList);
		System.err.println(" Exp List: " + expList);

		Thread.sleep(1500);

		String actList2 = listOfElements(entryPageRow2List);
		String expList2 = "[2, BATCH BIN FINISHED GOODS ITEM, BATCH BIN FINISHED GOODS ITEM, Dozs, 10.00, 5.50, 55.00, 0.00]";

		System.err.println(" Act List2: " + actList2);
		System.err.println(" Exp List2: " + expList2);

		Thread.sleep(1500);

		String docno = documentNumberTxt.getAttribute("value");
		click(voucherSaveBtn);

		boolean expMessage = true;
		boolean actMessage = checkVoucherSavingMessage(docno);

		Thread.sleep(1500);

		click(new_CloseBtn);

		click(entryPageHeaderChkbox);

		Thread.sleep(1500);

		click(deleteBtn);

		Thread.sleep(1500);

		getWaitForAlert();

		getAlert().accept();

		checkValidationMessage("");

		if (actMessage == expMessage && actList.equalsIgnoreCase(expList) && actList2.equalsIgnoreCase(expList2)) {

			System.out.println(" Test pass: Voucher Saved With Valid data With Copy and Paste Option ");
			return true;
		} else {
			System.out.println(" Test FAIL: Voucher Saved With Valid data With Copy and Paste Option ");
			return false;

		}

	}

	public boolean checkshowStatusMessageInPopUpChkoxOptions()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {

		excelReader = new ExcelReader(POJOUtility.getExcelPath());
		xlfile = getBaseDir() + "\\src\\main\\resources\\testdata\\FocusTestData.xlsx";

		Thread.sleep(3000);
		//prongHornStopAtAdminLevel();
		
		//relogin();

		//Thread.sleep(3000);

		getDriver().navigate().refresh();
		Thread.sleep(4000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(settingsMenu));
		ClickUsingJs(settingsMenu);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(Setting_PerferenceMenu));
		Setting_PerferenceMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MiselleanousBtn));
		MiselleanousBtn.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dontRefreshDescACProdDocChkBox));
		if (dontRefreshDescACProdDocChkBoxTOSel.isSelected() == true) {
			getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dontRefreshDescACProdDocChkBox));
			dontRefreshDescACProdDocChkBox.click();
		}

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(showStatusMessageInPopUpChkox));
		if (showStatusMessageInPopUpChkoxTosel.isSelected() == false) {
			getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(showStatusMessageInPopUpChkox));
			showStatusMessageInPopUpChkox.click();

		}

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(showStatusMessageInPopUpChkox));
		boolean actShowStatusMessageInPopUpChkox = showStatusMessageInPopUpChkoxTosel.isSelected();

		String actshowStatusMessageInPopUpChkox = Boolean.toString(actShowStatusMessageInPopUpChkox);
		String expshowStatusMessageInPopUpChkox = excelReader.getCellData(xlSheetName, 29, 7);
		excelReader.setCellData(xlfile, xlSheetName, 29, 8, actshowStatusMessageInPopUpChkox);

		System.out.println(" Act Chkbox:" + actshowStatusMessageInPopUpChkox);
		System.out.println(" Exp Chkbox:" + expshowStatusMessageInPopUpChkox);

		getAction().moveToElement(UpdateBtn).build().perform();
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(UpdateBtn));
		ClickUsingJs(UpdateBtn);

		getWaitForAlert();

		String actAlertMgs = getAlert().getText();
		String expAlertMgs = excelReader.getCellData(xlSheetName, 30, 7);

		excelReader.setCellData(xlfile, xlSheetName, 30, 8, actAlertMgs);

		System.out.println("AlertMessage     : " + actAlertMgs + " Value Expected : " + expAlertMgs);

		getAlert().accept();

		String expMessage = excelReader.getCellData(xlSheetName, 31, 7);

		String actMessage = checkValidationMessage(expMessage);

		excelReader.setCellData(xlfile, xlSheetName, 31, 8, actMessage);

		System.out.println(
				"********************************checkshowStatusMessageInPopUpChkoxOptions***************************");

		System.out.println("Message     : " + actMessage + " Value Expected : " + expMessage);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(CloseBtn));
		CloseBtn.click();

	/*	logout();

		Thread.sleep(3000);
		prongHornStartAtAdminLevel();
		Thread.sleep(3000);

		//checkLoginForPerferenceOptions();
		checkLogin();
	//	checkLoginToSelectedCompany("Automation Options Company", "su", "su");
	//	checkLogin();
		Thread.sleep(4000);*/

		if (actMessage.equalsIgnoreCase(expMessage) && actAlertMgs.equalsIgnoreCase(expAlertMgs)) {

			System.out.println("Test Pass : Clicking On Ok Btn And Error Message Is as Expected");
			excelReader.setCellData(xlfile, xlSheetName, 28, 9, resPass);
			return true;

		} else {

			System.out.println("Test Pass : Clicking On Ok Btn And Error Message Is Not as Expected");
			excelReader.setCellData(xlfile, xlSheetName, 28, 9, resFail);
			return false;
		}

	}

	@FindBy(xpath = "//*[@id='61']/span")
	private static WebElement financialsTransactionMenu;

	@FindBy(xpath = "//*[@id='2007']/span")
	private static WebElement financialsTransactionsPurchaseMenu;

	@FindBy(xpath = "//*[@id='2008']/span")
	private static WebElement purchaseVoucher;

	public boolean checkshowStatusMessageInPopUpOption()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		excelReader = new ExcelReader(POJOUtility.getExcelPath());
		xlfile = getBaseDir() + "\\src\\main\\resources\\testdata\\FocusTestData.xlsx";

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(financialsMenu));
		financialsMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(financialsTransactionMenu));
		financialsTransactionMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(financialsTransactionsPurchaseMenu));
		financialsTransactionsPurchaseMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(purchaseVoucher));
		purchaseVoucher.click();

		String expMessage = excelReader.getCellData(xlSheetName, 33, 7);

		String actMessage = checkValidationMessage(expMessage);

		excelReader.setCellData(xlfile, xlSheetName, 33, 8, actMessage);

		System.out.println("Message     : " + actMessage + " Value Expected : " + expMessage);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(homeCloseBtn));
		homeCloseBtn.click();

		if (actMessage.equalsIgnoreCase(expMessage)) {

			System.out.println("Test Pass : Error Message Is as Expected");
			excelReader.setCellData(xlfile, xlSheetName, 32, 9, resPass);
			return true;

		} else {

			System.out.println("Test Fail :  Error Message Is Not as Expected");
			excelReader.setCellData(xlfile, xlSheetName, 32, 9, resFail);
			return false;
		}

	}

	public boolean checkdonotLoadDocInExlusivemodeChkBoxOptions()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {

		excelReader = new ExcelReader(POJOUtility.getExcelPath());
		xlfile = getBaseDir() + "\\src\\main\\resources\\testdata\\FocusTestData.xlsx";

		Thread.sleep(3000);
		//prongHornStopAtAdminLevel();
		//Thread.sleep(3000);
		
		//relogin();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(settingsMenu));
		ClickUsingJs(settingsMenu);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(Setting_PerferenceMenu));
		Setting_PerferenceMenu.click();

		Thread.sleep(4000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MiselleanousBtn));
		MiselleanousBtn.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(showStatusMessageInPopUpChkox));
		if (showStatusMessageInPopUpChkoxTosel.isSelected() == true) {
			getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(showStatusMessageInPopUpChkox));
			showStatusMessageInPopUpChkox.click();
		}

		Thread.sleep(3000);

		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript("arguments[0].scrollIntoView();", donotLoadDocInExlusivemodeChkBox);

		Thread.sleep(3000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(donotLoadDocInExlusivemodeChkBox));
		if (donotLoadDocInExlusivemodeChkBoxTOSel.isSelected() == false) {
			Thread.sleep(3000);
			getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(donotLoadDocInExlusivemodeChkBox));
			donotLoadDocInExlusivemodeChkBox.click();

		}

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(donotLoadDocInExlusivemodeChkBox));
		boolean actShowStatusMessageInPopUpChkox = donotLoadDocInExlusivemodeChkBoxTOSel.isSelected();

		String actshowStatusMessageInPopUpChkox = Boolean.toString(actShowStatusMessageInPopUpChkox);
		String expshowStatusMessageInPopUpChkox = excelReader.getCellData(xlSheetName, 35, 7);
		excelReader.setCellData(xlfile, xlSheetName, 35, 8, actshowStatusMessageInPopUpChkox);

		System.out.println(" Act Chkbox:" + actshowStatusMessageInPopUpChkox);
		System.out.println(" Exp Chkbox:" + expshowStatusMessageInPopUpChkox);

		ClickUsingJs(UpdateBtn);

		getWaitForAlert();

		String actAlertMgs = getAlert().getText();
		String expAlertMgs = excelReader.getCellData(xlSheetName, 36, 7);

		excelReader.setCellData(xlfile, xlSheetName, 36, 8, actAlertMgs);

		System.out.println("AlertMessage     : " + actAlertMgs + " Value Expected : " + expAlertMgs);

		getAlert().accept();

		String expMessage = excelReader.getCellData(xlSheetName, 37, 7);

		String actMessage = checkValidationMessage(expMessage);

		excelReader.setCellData(xlfile, xlSheetName, 37, 8, actMessage);

		System.out.println("Message     : " + actMessage + " Value Expected : " + expMessage);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(CloseBtn));
		CloseBtn.click();

	/*	logout();

		Thread.sleep(3000);
		prongHornStartAtAdminLevel();
		Thread.sleep(3000);

	//	checkLoginForPerferenceOptions();
	//	checkLoginToSelectedCompany("Automation Options Company", "su", "su");
		checkLogin();*/
		Thread.sleep(4000);
		if (actMessage.equalsIgnoreCase(expMessage) && actAlertMgs.equalsIgnoreCase(expAlertMgs)) {

			System.out.println("Test Pass : Clicking On Ok Btn And Error Message Is as Expected");
			excelReader.setCellData(xlfile, xlSheetName, 34, 9, resPass);
			return true;

		} else {

			System.out.println("Test Pass : Clicking On Ok Btn And Error Message Is Not as Expected");
			excelReader.setCellData(xlfile, xlSheetName, 34, 9, resFail);
			return false;
		}

	}

	@FindBy(xpath = "//a[@id='2049']//span[contains(text(),'Opening Stocks New')]")
	private static WebElement openingStocksNewVoucher;

	@FindBy(xpath = "//a[@id='2033']//span[contains(text(),'Stocks')]")
	private static WebElement invTransStocksMenu;

	public boolean checkdonotLoadDocInExlusivemodeInVoucher()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException 
	{

		excelReader = new ExcelReader(POJOUtility.getExcelPath());
		xlfile = getBaseDir() + "\\src\\main\\resources\\testdata\\FocusTestData.xlsx";

		Thread.sleep(2000);

	/*	try {
			if (username.isDisplayed()) {
				//checkLoginForPerferenceOptions();
				
				checkLoginToSelectedCompany("Automation Options Company", "su", "su");
			}
		} catch (Exception e) {
			
		}*/

	//	Thread.sleep(2000);
		
		getDriver().navigate().refresh();
		Thread.sleep(4500);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(inventoryMenu));
		inventoryMenu.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(invTransactionsMenu));
		invTransactionsMenu.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(invTransStocksMenu));
		invTransStocksMenu.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(openingStocksNewVoucher));
		openingStocksNewVoucher.click();

		Thread.sleep(8000);

		click(newBtn);
		Thread.sleep(4000);
		//checkUserFriendlyMessage();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(wareHouseTxt));
		wareHouseTxt.sendKeys(Keys.SPACE);

		int warehousecount = wareHouseListCount.size();

		System.err.println(warehousecount);

		for (int i = 0; i < warehousecount; i++) {
			String data = wareHouseListCount.get(i).getText();

			if (data.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 39, 6))) {
				wareHouseListCount.get(i).click();
				break;
			}
		}

		wareHouseTxt.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_1stColumn));
		select1stRow_1stColumn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_ItemTxt));
		enter_ItemTxt.click();

		removetTxt(enter_ItemTxt);

		enter_ItemTxt.sendKeys(Keys.SPACE);

		enter_ItemTxt.sendKeys(excelReader.getCellData(xlSheetName, 40, 6));
		Thread.sleep(2000);

		enter_ItemTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_UnitTxt));
		enter_UnitTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_Quantity));
		enter_Quantity.click();
		enter_Quantity.clear();
		enter_Quantity.sendKeys(excelReader.getCellData(xlSheetName, 41, 6));
		enter_Quantity.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_Rate));
		enter_Rate.click();
		enter_Rate.clear();
		enter_Rate.sendKeys(excelReader.getCellData(xlSheetName, 42, 6));
		enter_Rate.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_Gross));
		enter_Gross.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(voucherSaveBtn));
		voucherSaveBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNumberTxt));

		String docno = documentNumberTxt.getAttribute("value");

		System.err.println("docno :  " + docno);

		boolean savingVoucher = checkVoucherSavingMessage(docno);

		String actSavingVoucher = Boolean.toString(savingVoucher);
		String expSavingVoucher = excelReader.getCellData(xlSheetName, 43, 7);
		excelReader.setCellData(xlfile, xlSheetName, 43, 8, actSavingVoucher);

		System.out.println("Entry Page Save With Input Of Batch Value Actual  : " + savingVoucher
				+ "  Value Expected   : " + "TRUE");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(previousBtn));
		previousBtn.click();

		checkUserFriendlyMessage();

		Thread.sleep(4000);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_T);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_T);

		System.err.println("Completed");

		Thread.sleep(2000);

		ArrayList<String> openTabs = new ArrayList<String>(getDriver().getWindowHandles());

		System.out.println("openTabs" + openTabs);

		// getDriver().switchTo().window(openTabs.get(0));

		getDriver().switchTo().window(openTabs.get(1));

		Thread.sleep(2000);

		LoginPage lp = new LoginPage(getDriver());

		lp.checkLoginPageTitleByURLInputInBrowser(DriverUtility.FINUrl);

		Thread.sleep(4000);

	//	checkLoginToSelectedCompany("Automation Options Company", "su", "su");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(inventoryMenu));
		inventoryMenu.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(invTransactionsMenu));
		invTransactionsMenu.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(invTransStocksMenu));
		invTransStocksMenu.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(openingStocksNewVoucher));
		openingStocksNewVoucher.click();

		getWebDriverWait().until(ExpectedConditions.elementToBeClickable(grid_ChkBox1));

		getWebDriverWait().until(ExpectedConditions.elementToBeClickable(grid_ChkBox1));
		getAction().doubleClick(grid_ChkBox1).build().perform();
		
		
		String expMsg="This document is already loaded by 'SU'";
		String actMsg= checkValidationMessage(expMsg);
	
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_W);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_W);
		Thread.sleep(2000);
		
		getDriver().switchTo().window(openTabs.get(0));
		Thread.sleep(2000);
		
/*
		if (errorMessage.getText().equalsIgnoreCase("This document is already loaded by 'SU'")) {
			click(errorMessageCloseBtn);
		}

		boolean loading = checkLoadingMessage();

		Thread.sleep(3000);

		
		  robot.keyPress(KeyEvent.VK_CONTROL); robot.keyPress(KeyEvent.VK_W);
		  robot.keyRelease(KeyEvent.VK_CONTROL); robot.keyRelease(KeyEvent.VK_W);
		 

		boolean actvalidationConfirmationMessage = validationConfirmationMessage.getText().isEmpty();
		boolean expvalidationConfirmationMessage = true;

		if (actvalidationConfirmationMessage == expvalidationConfirmationMessage) {

			System.out.println("Test Pass : Clicking On Ok Btn And Error Message Is as Expected");
			excelReader.setCellData(xlfile, xlSheetName, 38, 9, resPass);

			Robot robot1 = new Robot();

			robot1.keyPress(KeyEvent.VK_CONTROL);
			robot1.keyPress(KeyEvent.VK_W);
			robot1.keyRelease(KeyEvent.VK_CONTROL);
			robot1.keyRelease(KeyEvent.VK_W);
			Thread.sleep(2000);
			
			getDriver().navigate().refresh();
			Thread.sleep(3000);

		//	getDriver().switchTo().window(openTabs.get(0));

			System.err.println("completed TAB Closing");
			return true;

		} else {

			System.out.println("Test Pass : Clicking On Ok Btn And Error Message Is Not as Expected");
			excelReader.setCellData(xlfile, xlSheetName, 38, 9, resFail);

			Robot robot1 = new Robot();

			robot1.keyPress(KeyEvent.VK_CONTROL);
			robot1.keyPress(KeyEvent.VK_W);
			robot1.keyRelease(KeyEvent.VK_CONTROL);
			robot1.keyRelease(KeyEvent.VK_W);

			//getDriver().switchTo().window(openTabs.get(0));

			System.err.println("completed W");
			
			getDriver().navigate().refresh();
			Thread.sleep(3000);
			return false;
		}
*/
		
		if(actMsg.equalsIgnoreCase(expMsg))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public boolean checkDoNoModifiedDocumentIfTriggerIsRaisedOptions()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {

		excelReader = new ExcelReader(POJOUtility.getExcelPath());
		xlfile = getBaseDir() + "\\src\\main\\resources\\testdata\\FocusTestData.xlsx";

		
		//prongHornStopAtAdminLevel();
		//Thread.sleep(3000);
		//relogin();
		//Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(settingsMenu));
		ClickUsingJs(settingsMenu);

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(Setting_PerferenceMenu));
		Setting_PerferenceMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MiselleanousBtn));
		MiselleanousBtn.click();

		Thread.sleep(3000);

		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript("arguments[0].scrollIntoView();", donotLoadDocInExlusivemodeChkBox);

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(donotLoadDocInExlusivemodeChkBox));
		if (donotLoadDocInExlusivemodeChkBoxTOSel.isSelected() == true) {
			getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(donotLoadDocInExlusivemodeChkBox));
			donotLoadDocInExlusivemodeChkBox.click();
		}

		Thread.sleep(3000);

		JavascriptExecutor js1 = (JavascriptExecutor) getDriver();
		js1.executeScript("arguments[0].scrollIntoView();", showexchangeratedifferenceinledgerChkBox);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(doNotModifiedDocumentIfTriggerIsRaised));
		if (doNotModifiedDocumentIfTriggerIsRaisedTOSel.isSelected() == false) {
			Thread.sleep(3000);
			getFluentWebDriverWait()
					.until(ExpectedConditions.elementToBeClickable(doNotModifiedDocumentIfTriggerIsRaised));
			doNotModifiedDocumentIfTriggerIsRaised.click();

		}

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(doNotModifiedDocumentIfTriggerIsRaised));
		boolean actDoNotModifiedDocumentIfTriggerIsRaised = doNotModifiedDocumentIfTriggerIsRaisedTOSel.isSelected();

		String actdoNotModifiedDocumentIfTriggerIsRaised = Boolean.toString(actDoNotModifiedDocumentIfTriggerIsRaised);
		String expdoNotModifiedDocumentIfTriggerIsRaised = excelReader.getCellData(xlSheetName, 45, 7);
		excelReader.setCellData(xlfile, xlSheetName, 45, 8, actdoNotModifiedDocumentIfTriggerIsRaised);

		System.out.println(
				" ACT doNotModifiedDocumentIfTriggerIsRaisedTOSel: " + actdoNotModifiedDocumentIfTriggerIsRaised);
		System.out.println(
				" EXP doNotModifiedDocumentIfTriggerIsRaisedTOSel: " + expdoNotModifiedDocumentIfTriggerIsRaised);

		ClickUsingJs(UpdateBtn);

		getWaitForAlert();

		String actAlertMgs = getAlert().getText();
		String expAlertMgs = excelReader.getCellData(xlSheetName, 46, 7);

		excelReader.setCellData(xlfile, xlSheetName, 46, 8, actAlertMgs);

		System.out.println("AlertMessage     : " + actAlertMgs + " Value Expected : " + expAlertMgs);

		getAlert().accept();

		String expMessage = excelReader.getCellData(xlSheetName, 47, 7);

		String actMessage = checkValidationMessage(expMessage);

		excelReader.setCellData(xlfile, xlSheetName, 47, 8, actMessage);

		System.out.println("Message     : " + actMessage + " Value Expected : " + expMessage);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(CloseBtn));
		CloseBtn.click();

	/*	logout();
		Thread.sleep(2500);		checkLogin();
		Thread.sleep(4000);*/
		if (actMessage.equalsIgnoreCase(expMessage) && actAlertMgs.equalsIgnoreCase(expAlertMgs)
				&& actdoNotModifiedDocumentIfTriggerIsRaised
						.equalsIgnoreCase(expdoNotModifiedDocumentIfTriggerIsRaised)) {

			System.out.println("Test Pass : Option Enabled ");
			excelReader.setCellData(xlfile, xlSheetName, 44, 9, resPass);
			return true;

		} else {

			System.out.println("Test Fail : Option Not Enabled ");
			excelReader.setCellData(xlfile, xlSheetName, 44, 9, resFail);
			return false;
		}

	}

	@FindBy(xpath = "//input[@id='id_header_10']")
	private static WebElement voucherHeaderCurrency;

	@FindBy(xpath = "//input[@id='id_header_145']")
	private static WebElement voucherHeaderLocalExchangeRate;

	@FindBy(xpath = "//input[@id='id_header_11']")
	private static WebElement voucherHeaderExchangeRate;

	@FindBy(xpath = "//*[@id='2058']")
	private static WebElement pvvatViewMenu;

	public boolean checkSavingVoucherInPVVATView()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		excelReader = new ExcelReader(POJOUtility.getExcelPath());
		xlfile = getBaseDir() + "\\src\\main\\resources\\testdata\\FocusTestData.xlsx";

		Thread.sleep(4000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(financialsMenu));
		financialsMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(financialsTransactionMenu));
		financialsTransactionMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(financialsTransactionsPurchaseMenu));
		financialsTransactionsPurchaseMenu.click();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(pvvatViewMenu));
		pvvatViewMenu.click();

		Thread.sleep(15000);

		click(newBtn);
		
		Thread.sleep(4000);

		//checkUserFriendlyMessage();

		//Thread.sleep(4000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(vendorAccountTxt));
		vendorAccountTxt.click();
		vendorAccountTxt.sendKeys(Keys.SPACE);

		int vendorcount = vendorAccountListCount.size();

		System.err.println(vendorcount);

		for (int i = 0; i < vendorcount; i++) {
			String data = vendorAccountListCount.get(i).getText();

			if (data.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 50, 6))) {
				vendorAccountListCount.get(i).click();

				break;
			}
		}

		vendorAccountTxt.sendKeys(Keys.TAB);

		try {
			if (errorMessage.isDisplayed()) {
				click(errorMessageCloseBtn);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		Thread.sleep(2000);

		voucherHeaderCurrency.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(wareHouseTxt));
		wareHouseTxt.sendKeys(Keys.SPACE);

		int warehousecount = wareHouseListCount.size();

		System.err.println(warehousecount);

		for (int i = 0; i < warehousecount; i++) {
			String data = wareHouseListCount.get(i).getText();

			if (data.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 53, 6))) {
				wareHouseListCount.get(i).click();
				break;
			}
		}

		wareHouseTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		voucherHeaderExchangeRate.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(departmentTxt));

		departmentTxt.sendKeys(Keys.SPACE);

		int departmentcount = departmentListCount.size();

		System.err.println(departmentcount);

		for (int i = 0; i < departmentcount; i++) {
			String data = departmentListCount.get(i).getText();

			if (data.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 52, 6))) {
				departmentListCount.get(i).click();

				break;
			}
		}

		departmentTxt.sendKeys(Keys.TAB);
		Thread.sleep(2000);

		voucherHeaderLocalExchangeRate.click();
		voucherHeaderLocalExchangeRate.sendKeys(Keys.TAB);

		Thread.sleep(4000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_1stColumn));
		select1stRow_1stColumn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_ItemTxt));
		enter_ItemTxt.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_ItemTxt));
		enter_ItemTxt.click();

		removetTxt(enter_ItemTxt);

		enter_ItemTxt.sendKeys(Keys.SPACE);
		enter_ItemTxt.sendKeys(excelReader.getCellData(xlSheetName, 54, 6));

		Thread.sleep(2000);

		enter_ItemTxt.sendKeys(Keys.TAB);

		enter_AccountTxt.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_UnitTxt));
		enter_UnitTxt.sendKeys(Keys.TAB);

		pvVATAvgRate0.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_Quantity));
		enter_Quantity.click();
		enter_Quantity.clear();
		enter_Quantity.sendKeys(excelReader.getCellData(xlSheetName, 55, 6));
		enter_Quantity.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_Rate));
		enter_Rate.click();
		enter_Rate.clear();
		enter_Rate.sendKeys(excelReader.getCellData(xlSheetName, 56, 6));
		enter_Rate.sendKeys(Keys.TAB);

		enter_Gross.sendKeys(Keys.TAB);

		String docno = documentNumberTxt.getAttribute("value");

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(voucherSaveBtn));
		voucherSaveBtn.click();

		try {
			if (errorMessage.isDisplayed()) {
				System.err.println(errorMessage.getText());
				errorMessageCloseBtn.click();
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		Thread.sleep(2000);

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(billRefNewReferenceTxt));
		billRefNewReferenceTxt.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(billRefPickIcon));
		billRefPickIcon.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(billRefOkBtn));
		billRefOkBtn.click();

		String expMessage = "Voucher saved successfully";
		String act = checkValidationMessage(expMessage);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(new_CloseBtn));
		new_CloseBtn.click();

		if (act.startsWith(expMessage)) {
			return true;
		} else {
			return false;
		}

	}

	public boolean checkPurchaseOrderHomePageAfterVoucherTriggeredAfterSavingPVVAT()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		excelReader = new ExcelReader(POJOUtility.getExcelPath());
		xlfile = getBaseDir() + "\\src\\main\\resources\\testdata\\FocusTestData.xlsx";

		Thread.sleep(2000);
		click(focusMainSearch);

		focusMainSearch.sendKeys("Purchases Orders");
		Thread.sleep(2000);

		focusMainSearch.sendKeys(Keys.ENTER);

		Thread.sleep(2000);

		int count = homePageVoucherNumList.size();
		ArrayList<String> homePageVoucherNumListArray = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			String data = homePageVoucherNumList.get(i).getText();
			homePageVoucherNumListArray.add(data);
		}

		String actual = homePageVoucherNumListArray.toString();
		String expected = "[1, ]";

		System.out.println(" ACTUAL  " + actual);
		System.out.println(" EXP     " + expected);

		voucherHomePageVoucherSelect("1");

		Thread.sleep(5600);

		click(documentNumberTxt);

		String actAccount = vendorAccountTxt.getAttribute("value");
		String expAccount = "Vendor A";

		System.err.println(" ACt Account Dispalyed: " + actAccount);
		System.err.println(" Exp Account Dispalyed: " + expAccount);

		String actdateTxt = dateTxt.getAttribute("value");
		String expdateTxt = currentDate();

		System.err.println(" ACt dateTxt Dispalyed: " + actdateTxt);
		System.err.println(" Exp dateTxt Dispalyed: " + expdateTxt);

		String actfooterAmount = recepitsFooterAmt.getText();
		String expfooterAmount = "1.00";

		System.err.println(" ACt footerAmount Dispalyed: " + actfooterAmount);
		System.err.println(" Exp footerAmount Dispalyed: " + expfooterAmount);

		Thread.sleep(2000);
		String actList = listOfElements(entryPageRow1List);
		String expList = "[1, HYDERABAD, STD RATE COGS ITEM, Dozs, 1.00, 1.00, 1.00, 0.00]";

		System.err.println(" ACt Row1 Dispalyed: " + actList);
		System.err.println(" Exp Row1 Dispalyed: " + expList);

		Thread.sleep(2000);

		click(new_CloseBtn);

		if (actual.equalsIgnoreCase(expected) && actAccount.equalsIgnoreCase(expAccount)
				&& actList.equalsIgnoreCase(expList) && actfooterAmount.equalsIgnoreCase(expfooterAmount)
				&& actdateTxt.equalsIgnoreCase(expdateTxt)) {
			System.err.println("Test pass:  Entry Page Data Displayed As Exptected");
			return true;
		}

		else {
			System.err.println(" Voucher is NOTTT raised In Purchase Order");
			return false;
		}

	}

	public boolean checkEditSavedVoucherInPVVATAfterVoucherIsRaised()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {

		excelReader = new ExcelReader(POJOUtility.getExcelPath());
		xlfile = getBaseDir() + "\\src\\main\\resources\\testdata\\FocusTestData.xlsx";

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(financialsMenu));
		financialsMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(financialsTransactionMenu));
		financialsTransactionMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(financialsTransactionsPurchaseMenu));
		financialsTransactionsPurchaseMenu.click();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(pvvatViewMenu));
		pvvatViewMenu.click();

		Thread.sleep(4000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(grid_ChkBox1));
		grid_ChkBox1.click();

		Thread.sleep(9999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(editBtn));
		editBtn.click();

		String expValidationMessage = excelReader.getCellData(xlSheetName, 58, 7);

		String actValidationMessage = checkValidationMessage(expValidationMessage);
		excelReader.setCellData(xlfile, xlSheetName, 58, 8, actValidationMessage);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(new_CloseBtn));
		new_CloseBtn.click();

		if (actValidationMessage.equalsIgnoreCase(expValidationMessage)) {
			excelReader.setCellData(xlfile, xlSheetName, 48, 9, resPass);
			return true;
		} else {
			excelReader.setCellData(xlfile, xlSheetName, 48, 9, resFail);
			return false;
		}
	}

	@FindBy(xpath = "//input[@id='id_body_33554534']")
	private static WebElement pvVATAvgRate0;

	@FindBy(xpath = "//input[@id='id_body_536870916']")
	private static WebElement enter_WarehouseTxt;

	@FindBy(xpath = "//*[@id='id_body_536870916_table_data_body']/tr")
	private static List<WebElement> pvvGridWarehouseList;

	@FindBy(xpath = "//*[@id='id_body_23_table_data_body']/tr")
	private static List<WebElement> pvvGridItemList;

	@FindBy(xpath = "//input[@id='id_body_33554504']")
	private static WebElement enter_PvTaxable;

	@FindBy(xpath = "//*[@id='id_body_16777333_table_data_body']/tr")
	private static List<WebElement> pvvGridTaxCodeList;

	@FindBy(xpath = "//*[@id='id_body_16777333']")
	private static WebElement enter_PvTaxCode;

	@FindBy(xpath = "//input[@id='chkNextVoucherNoAftSaveVoucher']")
	private static WebElement StayOnSameVoucherNumberAfterDeletingTosel;

	@FindBy(xpath = "//input[@id='chkNextVoucherNoAftSaveVoucher']//following-sibling::span")
	private static WebElement StayOnSameVoucherNumberAfterDeleting;

	public boolean checkEnableStayOnSameVoucherNumberAfterDeletingOptions()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {

		excelReader = new ExcelReader(POJOUtility.getExcelPath());
		xlfile = getBaseDir() + "\\src\\main\\resources\\testdata\\FocusTestData.xlsx";

		Thread.sleep(2000);

	/*	Thread.sleep(3000);
		prongHornStopAtAdminLevel();
		
		relogin();
		
		Thread.sleep(3000);*/

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(settingsMenu));
		ClickUsingJs(settingsMenu);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(Setting_PerferenceMenu));
		Setting_PerferenceMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MiselleanousBtn));
		MiselleanousBtn.click();

		Thread.sleep(3000);

	//	JavascriptExecutor js = (JavascriptExecutor) getDriver();
		//js.executeScript("arguments[0].scrollIntoView();", doNotModifiedDocumentIfTriggerIsRaised);
		getAction().moveToElement(doNotModifiedDocumentIfTriggerIsRaised).build().perform();
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(doNotModifiedDocumentIfTriggerIsRaised));
		if (doNotModifiedDocumentIfTriggerIsRaisedTOSel.isSelected() == true) {
			getFluentWebDriverWait()
					.until(ExpectedConditions.elementToBeClickable(doNotModifiedDocumentIfTriggerIsRaised));
			doNotModifiedDocumentIfTriggerIsRaised.click();
		}

		Thread.sleep(3000);

		//JavascriptExecutor js1 = (JavascriptExecutor) getDriver();
		//js1.executeScript("arguments[0].scrollIntoView();", StayOnSameVoucherNumberAfterDeleting);

		getAction().moveToElement(StayOnSameVoucherNumberAfterDeleting).build().perform();
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(StayOnSameVoucherNumberAfterDeleting));
		if (StayOnSameVoucherNumberAfterDeletingTosel.isSelected() == false) {
			Thread.sleep(3000);
			getFluentWebDriverWait()
					.until(ExpectedConditions.elementToBeClickable(StayOnSameVoucherNumberAfterDeleting));
			StayOnSameVoucherNumberAfterDeleting.click();

		}

		boolean actstayOnSameVoucherNumberAfterDeleting = StayOnSameVoucherNumberAfterDeletingTosel.isSelected();

		String actStayOnSameVoucherNumberAfterDeleting = Boolean.toString(actstayOnSameVoucherNumberAfterDeleting);
		String expStayOnSameVoucherNumberAfterDeleting = excelReader.getCellData(xlSheetName, 60, 7);
		excelReader.setCellData(xlfile, xlSheetName, 60, 8, actStayOnSameVoucherNumberAfterDeleting);

		System.out.println(" Act Chkbox:" + actStayOnSameVoucherNumberAfterDeleting);
		System.out.println(" Exp Chkbox:" + expStayOnSameVoucherNumberAfterDeleting);

		//ClickUsingJs(UpdateBtn);

		getAction().moveToElement(UpdateBtn).build().perform();
		UpdateBtn.click();
		getWaitForAlert();

		String actAlertMgs = getAlert().getText();
		String expAlertMgs = excelReader.getCellData(xlSheetName, 61, 7);

		excelReader.setCellData(xlfile, xlSheetName, 61, 8, actAlertMgs);

		System.out.println("AlertMessage     : " + actAlertMgs + " Value Expected : " + expAlertMgs);

		getAlert().accept();

		String expMessage = excelReader.getCellData(xlSheetName, 62, 7);

		String actMessage = checkValidationMessage(expMessage);

		excelReader.setCellData(xlfile, xlSheetName, 62, 8, actMessage);

		System.out.println("Message     : " + actMessage + " Value Expected : " + expMessage);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(CloseBtn));
		CloseBtn.click();

		Thread.sleep(3000);
/*
		logout();
		
		Thread.sleep(3000);
		
		prongHornStartAtAdminLevel();
		
		Thread.sleep(3000);
		
		checkLogin();
		Thread.sleep(4000);
	//	checkLoginForPerferenceOptions();
	//	checkLoginToSelectedCompany("Automation Options Company", "su", "su");
		
		Thread.sleep(3000);*/
		
		if (actMessage.equalsIgnoreCase(expMessage) && actAlertMgs.equalsIgnoreCase(expAlertMgs)
				&& actStayOnSameVoucherNumberAfterDeleting.equalsIgnoreCase(expStayOnSameVoucherNumberAfterDeleting)) {

			System.out.println("Test Pass : Option Enabled ");
			excelReader.setCellData(xlfile, xlSheetName, 59, 9, resPass);
			return true;

		} else {

			System.out.println("Test Fail : Option Not Enabled ");
			excelReader.setCellData(xlfile, xlSheetName, 59, 9, resFail);
			return false;
		}

	}

	public boolean checkStayOnSameVoucherNumberAfterDeletingFunctionality()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {

		excelReader = new ExcelReader(POJOUtility.getExcelPath());
		xlfile = getBaseDir() + "\\src\\main\\resources\\testdata\\FocusTestData.xlsx";

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(inventoryMenu));
		inventoryMenu.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(invTransactionsMenu));
		invTransactionsMenu.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(invTransStocksMenu));
		invTransStocksMenu.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(openingStocksNewVoucher));
		openingStocksNewVoucher.click();

		Thread.sleep(8000);

		click(newBtn);

		//checkUserFriendlyMessage();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(previousBtn));
		previousBtn.click();

		boolean loading = checkLoadingMessage();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(toggleBtn));
		toggleBtn.click();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(copyToClipBoardOption));
		copyToClipBoardOption.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(nextBtn));
		nextBtn.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(toggleBtn));
		toggleBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(pasteFromClipBoardOption));
		pasteFromClipBoardOption.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(errorMessageCloseBtn));
		errorMessageCloseBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(voucherSaveBtn));
		voucherSaveBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(errorMessageCloseBtn));
		errorMessageCloseBtn.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(previousBtn));
		previousBtn.click();

		boolean loading1 = checkLoadingMessage();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(toggleBtn));
		toggleBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(copyToClipBoardOption));
		copyToClipBoardOption.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(nextBtn));
		nextBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(toggleBtn));
		toggleBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(pasteFromClipBoardOption));
		pasteFromClipBoardOption.click();

		try {
			if (errorMessage.isDisplayed()) {
				System.err.println(errorMessage.getText());
				click(errorMessageCloseBtn);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(voucherSaveBtn));
		voucherSaveBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(errorMessageCloseBtn));
		errorMessageCloseBtn.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(previousBtn));
		previousBtn.click();

		boolean loading2 = checkLoadingMessage();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(previousBtn));
		previousBtn.click();

		boolean loading3 = checkLoadingMessage();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNumberTxt));
		String actDocNumberBeforeDelete = documentNumberTxt.getAttribute("value");

		String expDocNumberBeforeDelete = "2";

		System.out.println("actDocNumberBeforeDelete   : " + actDocNumberBeforeDelete + " Value Expected : "
				+ expDocNumberBeforeDelete);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(new_DeleteBtn));
		new_DeleteBtn.click();

		/*
		 * getWaitForAlert();
		 * 
		 * getAlert().accept();
		 */

		clickOn(popUpOKBtn);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNumberTxt));
		String actDocNumberAfterDelete = documentNumberTxt.getAttribute("value");

		String expDocNumberAfterDelete = "2";

		System.out.println("actDocNumberAfterDelete   : " + actDocNumberAfterDelete + " Value Expected : "
				+ expDocNumberAfterDelete);

		if (actDocNumberBeforeDelete.equalsIgnoreCase(expDocNumberBeforeDelete)
				&& actDocNumberAfterDelete.equalsIgnoreCase(expDocNumberAfterDelete)) {
			excelReader.setCellData(xlfile, xlSheetName, 59, 9, resPass);
			return true;
		} else {
			excelReader.setCellData(xlfile, xlSheetName, 59, 9, resFail);
			return false;
		}
	}

	public boolean checkEnableCalculatedueDateFromLRDateChkBoxOptions()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {

		excelReader = new ExcelReader(POJOUtility.getExcelPath());
		xlfile = getBaseDir() + "\\src\\main\\resources\\testdata\\FocusTestData.xlsx";

		Thread.sleep(2000);
		getDriver().navigate().refresh();

		Thread.sleep(2500);

		Thread.sleep(3000);
		prongHornStopAtAdminLevel();
		
		//relogin();
		//Thread.sleep(3000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(settingsMenu));
		ClickUsingJs(settingsMenu);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(Setting_PerferenceMenu));
		Setting_PerferenceMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MiselleanousBtn));
		MiselleanousBtn.click();

		Thread.sleep(3000);

	//	JavascriptExecutor js = (JavascriptExecutor) getDriver();
	//	js.executeScript("arguments[0].scrollIntoView();", StayOnSameVoucherNumberAfterDeleting);
		getAction().moveToElement(StayOnSameVoucherNumberAfterDeleting).build().perform();
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(StayOnSameVoucherNumberAfterDeleting));
		if (StayOnSameVoucherNumberAfterDeletingTosel.isSelected() == true) {
			getFluentWebDriverWait()
					.until(ExpectedConditions.elementToBeClickable(StayOnSameVoucherNumberAfterDeleting));
			StayOnSameVoucherNumberAfterDeleting.click();
		}

		Thread.sleep(3000);

		//JavascriptExecutor js1 = (JavascriptExecutor) getDriver();
		//js1.executeScript("arguments[0].scrollIntoView();", calculatedueDateFromLRDateChkBox);

		getAction().moveToElement(calculatedueDateFromLRDateChkBox).build().perform();
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(calculatedueDateFromLRDateChkBox));
		if (calculatedueDateFromLRDateChkBoxTOSel.isSelected() == false) {
			Thread.sleep(3000);
			getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(calculatedueDateFromLRDateChkBox));
			calculatedueDateFromLRDateChkBox.click();

		}

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(calculatedueDateFromLRDateChkBox));
		boolean actCalculatedueDateFromLRDateChkBox = calculatedueDateFromLRDateChkBoxTOSel.isSelected();

		String actcalculatedueDateFromLRDateChkBox = Boolean.toString(actCalculatedueDateFromLRDateChkBox);
		String expcalculatedueDateFromLRDateChkBox = excelReader.getCellData(xlSheetName, 67, 7);
		excelReader.setCellData(xlfile, xlSheetName, 67, 8, actcalculatedueDateFromLRDateChkBox);

		System.out.println(" Act Chkbox:" + actcalculatedueDateFromLRDateChkBox);
		System.out.println(" Exp Chkbox:" + actcalculatedueDateFromLRDateChkBox);

		getAction().moveToElement(UpdateBtn).build().perform();
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(UpdateBtn));
		//ClickUsingJs(UpdateBtn);
		UpdateBtn.click();
		getWaitForAlert();

		String actAlertMgs = getAlert().getText();
		String expAlertMgs = excelReader.getCellData(xlSheetName, 68, 7);

		excelReader.setCellData(xlfile, xlSheetName, 68, 8, actAlertMgs);

		System.out.println("AlertMessage     : " + actAlertMgs + " Value Expected : " + expAlertMgs);

		getAlert().accept();

		String expMessage = excelReader.getCellData(xlSheetName, 69, 7);

		String actMessage = checkValidationMessage(expMessage);

		excelReader.setCellData(xlfile, xlSheetName, 69, 8, actMessage);

		System.out.println("Message     : " + actMessage + " Value Expected : " + expMessage);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(CloseBtn));
		CloseBtn.click();

		Thread.sleep(3000);
/*
		logout();

		Thread.sleep(3000);
		
		prongHornStartAtAdminLevel();
		
		Thread.sleep(3000);
		
		checkLogin();
		

		//checkLoginForPerferenceOptions();
		Thread.sleep(4000);
		
		//checkLoginToSelectedCompany("Automation Options Company", "su", "su");*/

		if (actMessage.equalsIgnoreCase(expMessage) && actAlertMgs.equalsIgnoreCase(expAlertMgs)
				&& actcalculatedueDateFromLRDateChkBox.equalsIgnoreCase(expcalculatedueDateFromLRDateChkBox)) {

			System.out.println("Test Pass : Option Enabled ");
			excelReader.setCellData(xlfile, xlSheetName, 66, 9, resPass);
			return true;

		} else {

			System.out.println("Test Fail : Option Not Enabled ");
			excelReader.setCellData(xlfile, xlSheetName, 66, 9, resFail);
			return false;
		}

	}

	@FindBy(xpath = "//input[@id='iCreditDays']")
	private static WebElement creditDaysTxt;

	@FindBy(xpath = "(//*[@id='btnMasterSaveClick'])[2]")
	public static WebElement account_saveBtn;

	@FindBy(xpath = "//select[@id='EditLayout_FieldsCustomization_FieldDetails_DataType']")
	public static WebElement accountTypeDrpdwn;

	public boolean checkCreatingLRExtraFiledAndCheckItsFunctionality()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {

		excelReader = new ExcelReader(POJOUtility.getExcelPath());
		xlfile = getBaseDir() + "\\src\\main\\resources\\testdata\\FocusTestData.xlsx";

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(financialsMenu));
		financialsMenu.click();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(financialsTransactionMenu));
		financialsTransactionMenu.click();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(financialsTransactionsPurchaseMenu));
		financialsTransactionsPurchaseMenu.click();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(purchaseVouchersVat));
		purchaseVouchersVat.click();

		Thread.sleep(6000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(settingsBtn));
		settingsBtn.click();

		Thread.sleep(2000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(editLayoutTab));
		editLayoutTab.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(editLayoutAddFieldsBtn));
		editLayoutAddFieldsBtn.click();

		getWaitForAlert();

		String actAlertMgs = getAlert().getText();
		String expAlertMgs = excelReader.getCellData(xlSheetName, 71, 7);

		excelReader.setCellData(xlfile, xlSheetName, 71, 8, actAlertMgs);

		System.out.println("AlertMessage     : " + actAlertMgs + " Value Expected : " + expAlertMgs);

		getAlert().accept();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(editLayoutCaptionTxt));
		editLayoutCaptionTxt.click();

		Thread.sleep(2000);
		editLayoutCaptionTxt.sendKeys(excelReader.getCellData(xlSheetName, 72, 6));

		Thread.sleep(2000);
		editLayoutCaptionTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		Select s = new Select(accountTypeDrpdwn);

		s.selectByVisibleText("Date");

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(editLayoutApplyBtn));
		editLayoutApplyBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(errorMessageCloseBtn));
		errorMessageCloseBtn.click();

		Thread.sleep(3000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(UpdateBtn));
		ClickUsingJs(UpdateBtn);

		String expMessage = excelReader.getCellData(xlSheetName, 73, 7);

		String actMessage = checkValidationMessage(expMessage);

		excelReader.setCellData(xlfile, xlSheetName, 73, 8, actMessage);

		Thread.sleep(8000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(settingCloseIcon));
		settingCloseIcon.click();

		Thread.sleep(4000);

		click(newBtn);

		//checkUserFriendlyMessage();

		Thread.sleep(2000);
		vendorAccountTxt.click();

		vendorAccountTxt.sendKeys(excelReader.getCellData(xlSheetName, 74, 6));

		Thread.sleep(2000);
		vendorAccountTxt.sendKeys(Keys.TAB);
		checkValidationMessage("");
		
		Thread.sleep(8000);

		creditDaysTxt.click();

		Thread.sleep(2000);
		removetTxt(creditDaysTxt);
		creditDaysTxt.sendKeys(excelReader.getCellData(xlSheetName, 75, 6));

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(account_saveBtn));
		account_saveBtn.click();

		Thread.sleep(5000);
		vendorAccountTxt.click();
		vendorAccountTxt.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME);
		vendorAccountTxt.sendKeys(Keys.SPACE);
		vendorAccountTxt.sendKeys(excelReader.getCellData(xlSheetName, 74, 6));

		Thread.sleep(2000);

		vendorAccountTxt.sendKeys(Keys.TAB);
		
		
		Thread.sleep(2000);

		clickOn(voucherHeaderDueDate);

		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();

		System.out.println(df.format(date));

		String docdate = df.format(date);

		Calendar c = Calendar.getInstance();

		c.add(Calendar.DAY_OF_MONTH, 5);

		Calendar c1 = Calendar.getInstance();

		c1.add(Calendar.DAY_OF_MONTH, 10);

		Calendar c3 = Calendar.getInstance();

		c3.add(Calendar.DAY_OF_MONTH, 15);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(voucherHeaderDueDate));
		String actVoucherHeaderDueDate = voucherHeaderDueDate.getAttribute("value");
		String expVoucherHeaderDueDate = df.format(c.getTime());

		excelReader.setCellData(xlfile, xlSheetName, 76, 8, actVoucherHeaderDueDate);
		excelReader.setCellData(xlfile, xlSheetName, 76, 7, expVoucherHeaderDueDate);

		System.out.println("Due Date   : " + actVoucherHeaderDueDate + " Value Expected : " + expVoucherHeaderDueDate);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(LRDateTxt));
		LRDateTxt.click();

		LRDateTxt.sendKeys(Keys.HOME);

		LRDateTxt.sendKeys(df.format(c1.getTime()));

		LRDateTxt.sendKeys(Keys.TAB);

		String actLRDateTxt = LRDateTxt.getAttribute("value");
		String expLRDateTxt = df.format(c1.getTime());

		excelReader.setCellData(xlfile, xlSheetName, 77, 8, actLRDateTxt);
		excelReader.setCellData(xlfile, xlSheetName, 77, 7, expLRDateTxt);

		System.out.println("LRDateTxt   : " + actLRDateTxt + " Value Expected : " + expLRDateTxt);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(voucherHeaderDueDate));
		String actVoucherHeaderDueDateWithLRDate = voucherHeaderDueDate.getAttribute("value");
		String expVoucherHeaderDueDateWithLRDate = df.format(c3.getTime());

		excelReader.setCellData(xlfile, xlSheetName, 78, 8, actVoucherHeaderDueDateWithLRDate);
		excelReader.setCellData(xlfile, xlSheetName, 78, 7, expVoucherHeaderDueDateWithLRDate);

		System.out.println("Due Date With LRDate   : " + actVoucherHeaderDueDateWithLRDate + " Value Expected : "
				+ expVoucherHeaderDueDateWithLRDate);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(new_CloseBtn));
		new_CloseBtn.click();

		/*
		 * getWaitForAlert();
		 * 
		 * getAlert().accept();
		 */

		clickOn(popUpOKBtn);

		if (actVoucherHeaderDueDateWithLRDate.equalsIgnoreCase(expVoucherHeaderDueDateWithLRDate)
				&& actLRDateTxt.equalsIgnoreCase(expLRDateTxt)
				&& actVoucherHeaderDueDate.equalsIgnoreCase(expVoucherHeaderDueDate)) {

			System.out.println("Test Pass : Due Date Is Updated   With  LR Date ");
			excelReader.setCellData(xlfile, xlSheetName, 66, 9, resPass);
			return true;

		} else {

			System.out.println("Test Fail : Due Date IS Not Updated With LR Date  ");
			excelReader.setCellData(xlfile, xlSheetName, 66, 9, resFail);
			return false;
		}

	}

	@FindBy(xpath = "//select[@id='id_header_2_month']")
	private static WebElement monthDropDwn;

	@FindBy(xpath = "//select[@id='id_header_2_year']")
	private static WebElement yearDropDwn;

	@FindBy(xpath = "//tr[@id='id_header_2_day_grid']")
	private static WebElement calDates;

	@FindBy(xpath = "//input[@id='id_header_6']")
	private static WebElement voucherHeaderDueDate;

	//@FindBy(xpath = "//input[@id='id_header_67109002']")
	@FindBy(xpath="((//label[text()='LR Date']//..//table)[1]//input)[2]")
	private static WebElement LRDateTxt;

	@FindBy(xpath = "//input[@id='iCurrencyId']")
	private static WebElement currecyDrpDwn;

	public boolean checkEnablefiltercurrencyBasedondepartmentChkBoxOptions()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {

		excelReader = new ExcelReader(POJOUtility.getExcelPath());
		xlfile = getBaseDir() + "\\src\\main\\resources\\testdata\\FocusTestData.xlsx";

		getDriver().navigate().refresh();
		Thread.sleep(4000);
		
		Thread.sleep(3000);
		prongHornStopAtAdminLevel();
		
		//relogin();
		//Thread.sleep(3000);

	//	Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(settingsMenu));
		ClickUsingJs(settingsMenu);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(Setting_PerferenceMenu));
		Setting_PerferenceMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MiselleanousBtn));
		MiselleanousBtn.click();

		Thread.sleep(3000);

		//JavascriptExecutor js = (JavascriptExecutor) getDriver();
		//js.executeScript("arguments[0].scrollIntoView();", calculatedueDateFromLRDateChkBox);
		getAction().moveToElement(calculatedueDateFromLRDateChkBox).build().perform();
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(calculatedueDateFromLRDateChkBox));
		if (calculatedueDateFromLRDateChkBoxTOSel.isSelected() == true) {
			getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(calculatedueDateFromLRDateChkBox));
			calculatedueDateFromLRDateChkBox.click();
		}

		Thread.sleep(3000);

		//JavascriptExecutor js1 = (JavascriptExecutor) getDriver();
		//js1.executeScript("arguments[0].scrollIntoView();", filtercurrencyBasedondepartmentChkBox);
		
		getAction().moveToElement(filtercurrencyBasedondepartmentChkBox).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(filtercurrencyBasedondepartmentChkBox));
		if (filtercurrencyBasedondepartmentChkBoxTOSel.isSelected() == false) {
			Thread.sleep(3000);
			getFluentWebDriverWait()
					.until(ExpectedConditions.elementToBeClickable(filtercurrencyBasedondepartmentChkBox));
			filtercurrencyBasedondepartmentChkBox.click();

		}

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(filtercurrencyBasedondepartmentChkBox));
		boolean actFiltercurrencyBasedondepartmentChkBox = filtercurrencyBasedondepartmentChkBoxTOSel.isSelected();

		String actfiltercurrencyBasedondepartmentChkBox = Boolean.toString(actFiltercurrencyBasedondepartmentChkBox);
		String expfiltercurrencyBasedondepartmentChkBox = excelReader.getCellData(xlSheetName, 80, 7);
		excelReader.setCellData(xlfile, xlSheetName, 80, 8, actfiltercurrencyBasedondepartmentChkBox);

		System.out.println(" Act Chkbox:" + actfiltercurrencyBasedondepartmentChkBox);
		System.out.println(" Exp Chkbox:" + expfiltercurrencyBasedondepartmentChkBox);
		getAction().moveToElement(UpdateBtn).build().perform();
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(UpdateBtn));
	//	ClickUsingJs(UpdateBtn);
		UpdateBtn.click();
		getWaitForAlert();

		String actAlertMgs = getAlert().getText();
		String expAlertMgs = excelReader.getCellData(xlSheetName, 81, 7);

		excelReader.setCellData(xlfile, xlSheetName, 81, 8, actAlertMgs);

		System.out.println("AlertMessage     : " + actAlertMgs + " Value Expected : " + expAlertMgs);

		getAlert().accept();

		String expMessage = excelReader.getCellData(xlSheetName, 82, 7);

		String actMessage = checkValidationMessage(expMessage);

		excelReader.setCellData(xlfile, xlSheetName, 82, 8, actMessage);

		System.out.println("Message     : " + actMessage + " Value Expected : " + expMessage);

		ClickUsingJs(CloseBtn);

		Thread.sleep(3000);
/*
		logout();

		Thread.sleep(3000);
		prongHornStartAtAdminLevel();
		Thread.sleep(3000);

	//	checkLoginForPerferenceOptions();
	//	checkLoginToSelectedCompany("Automation Options Company", "su", "su");
		Thread.sleep(3000);
		checkLogin();
		Thread.sleep(4000);*/
		
		if (actMessage.equalsIgnoreCase(expMessage) && actAlertMgs.equalsIgnoreCase(expAlertMgs)
				&& actfiltercurrencyBasedondepartmentChkBox
						.equalsIgnoreCase(expfiltercurrencyBasedondepartmentChkBox)) {

			System.out.println("Test Pass : Option Enabled ");
			excelReader.setCellData(xlfile, xlSheetName, 79, 9, resPass);
			return true;

		} else {

			System.out.println("Test Fail : Option Not Enabled ");
			excelReader.setCellData(xlfile, xlSheetName, 79, 9, resFail);
			return false;
		}
	}

	public boolean checkfiltercurrencyBasedondepartmentChkBoxFunctionality()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {

		excelReader = new ExcelReader(POJOUtility.getExcelPath());
		xlfile = getBaseDir() + "\\src\\main\\resources\\testdata\\FocusTestData.xlsx";

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(financialsMenu));
		financialsMenu.click();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(financialsTransactionMenu));
		financialsTransactionMenu.click();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(financialsTransactionsPurchaseMenu));
		financialsTransactionsPurchaseMenu.click();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(purchaseVouchersVat));
		purchaseVouchersVat.click();

		Thread.sleep(2000);

		click(newBtn);

		//checkUserFriendlyMessage();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(voucherHeaderCurrency));
		voucherHeaderCurrency.click();
		voucherHeaderCurrency.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME);
		voucherHeaderCurrency.sendKeys(Keys.SPACE);

		int currencyListCountCount = currencyListCount.size();

		ArrayList<String> actCurrencyList = new ArrayList<String>();

		for (int i = 0; i < currencyListCountCount; i++) {
			String data = currencyListCount.get(i).getText();

			System.err.println("IN FOR LOOP : " + data);
			actCurrencyList.add(data);
		}

		voucherHeaderCurrency.sendKeys(Keys.TAB);

		System.err.println("actCurrencyList    :" + actCurrencyList);

		String actCurrencyCount = actCurrencyList.toString();
		String expCurrencyCount = excelReader.getCellData(xlSheetName, 83, 7);

		excelReader.setCellData(xlfile, xlSheetName, 83, 8, actCurrencyCount);

		Thread.sleep(2000);

		departmentTxt.click();
		departmentTxt.sendKeys(excelReader.getCellData(xlSheetName, 84, 6));

		Thread.sleep(2000);
		departmentTxt.sendKeys(Keys.TAB);

		Thread.sleep(3000);

		voucherHeaderCurrency.click();
		voucherHeaderCurrency.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
		voucherHeaderCurrency.sendKeys(excelReader.getCellData(xlSheetName, 85, 6));

		Thread.sleep(3000);

		voucherHeaderCurrency.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(account_saveBtn));
		account_saveBtn.click();

		Thread.sleep(2000);
		departmentTxt.click();
		departmentTxt.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME);
		departmentTxt.sendKeys(Keys.SPACE);
		departmentTxt.sendKeys(excelReader.getCellData(xlSheetName, 84, 6));

		Thread.sleep(2000);

		departmentTxt.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(voucherHeaderCurrency));
		voucherHeaderCurrency.click();
		voucherHeaderCurrency.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME);
		voucherHeaderCurrency.sendKeys(Keys.SPACE);

		int currencyListCountCount1 = currencyListCount.size();

		ArrayList<String> actCurrencyList1 = new ArrayList<String>();

		for (int i = 0; i < currencyListCountCount1; i++) {
			String data = currencyListCount.get(i).getText();

			System.err.println("IN FOR LOOP : " + data);
			actCurrencyList1.add(data);
		}

		voucherHeaderCurrency.sendKeys(Keys.TAB);

		System.err.println("actCurrencyList1    :" + actCurrencyList1);

		String actCurrencyCountAfter = actCurrencyList1.toString();
		String expCurrencyCountAfter = excelReader.getCellData(xlSheetName, 86, 7);

		excelReader.setCellData(xlfile, xlSheetName, 86, 8, actCurrencyCountAfter);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(new_CloseBtn));
		new_CloseBtn.click();

		/*
		 * getWaitForAlert();
		 * 
		 * getAlert().accept();
		 */

		clickOn(popUpOKBtn);

		System.out.println(
				"actCurrencyCountAfter  : " + actCurrencyCountAfter + " Value Expected : " + expCurrencyCountAfter);

		System.out.println("actCurrencyCount  : " + actCurrencyCount + " Value Expected : " + expCurrencyCount);

		if (actCurrencyCountAfter.equalsIgnoreCase(expCurrencyCountAfter)
				&& actCurrencyCount.equalsIgnoreCase(expCurrencyCount)) {

			System.out.println("Test Pass : Display Currency as Expected  ");
			excelReader.setCellData(xlfile, xlSheetName, 83, 9, resPass);
			return true;

		} else {

			System.out.println("Test Fail :  Not Display Currency as Expected  ");
			excelReader.setCellData(xlfile, xlSheetName, 83, 9, resFail);
			return false;
		}

	}

	public boolean checkEnableloadDateBasesonLastSavedVoucherChkBoxOptions()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {

		excelReader = new ExcelReader(POJOUtility.getExcelPath());
		xlfile = getBaseDir() + "\\src\\main\\resources\\testdata\\FocusTestData.xlsx";

		Thread.sleep(3000);
		
		getDriver().navigate().refresh();
		Thread.sleep(3500);
	/*	prongHornStopAtAdminLevel();
		
		relogin();
		Thread.sleep(3000);

		//Thread.sleep(2000);*/

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(settingsMenu));
		ClickUsingJs(settingsMenu);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(Setting_PerferenceMenu));
		Setting_PerferenceMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MiselleanousBtn));
		MiselleanousBtn.click();

		Thread.sleep(3000);

		//JavascriptExecutor js = (JavascriptExecutor) getDriver();
		//js.executeScript("arguments[0].scrollIntoView();", filtercurrencyBasedondepartmentChkBox);
		getAction().moveToElement(filtercurrencyBasedondepartmentChkBox).build().perform();
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(filtercurrencyBasedondepartmentChkBox));
		if (filtercurrencyBasedondepartmentChkBoxTOSel.isSelected() == true) {
			getFluentWebDriverWait()
					.until(ExpectedConditions.elementToBeClickable(filtercurrencyBasedondepartmentChkBox));
			filtercurrencyBasedondepartmentChkBox.click();
		}

		Thread.sleep(3000);

		//JavascriptExecutor js1 = (JavascriptExecutor) getDriver();
		//js1.executeScript("arguments[0].scrollIntoView();", loadDateBasesonLastSavedVoucherChkBox);
		getAction().moveToElement(loadDateBasesonLastSavedVoucherChkBox).build().perform();
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(loadDateBasesonLastSavedVoucherChkBox));
		if (loadDateBasesonLastSavedVoucherChkBoxTOSel.isSelected() == false) {

			getFluentWebDriverWait()
					.until(ExpectedConditions.elementToBeClickable(loadDateBasesonLastSavedVoucherChkBox));
			loadDateBasesonLastSavedVoucherChkBox.click();

		}

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(loadDateBasesonLastSavedVoucherChkBox));
		boolean actLoadDateBasesonLastSavedVoucherChkBox = loadDateBasesonLastSavedVoucherChkBoxTOSel.isSelected();

		String actloadDateBasesonLastSavedVoucherChkBox = Boolean.toString(actLoadDateBasesonLastSavedVoucherChkBox);
		String exploadDateBasesonLastSavedVoucherChkBox = excelReader.getCellData(xlSheetName, 88, 7);
		excelReader.setCellData(xlfile, xlSheetName, 88, 8, actloadDateBasesonLastSavedVoucherChkBox);

		System.out.println(" Act Chkbox:" + actloadDateBasesonLastSavedVoucherChkBox);
		System.out.println(" Exp Chkbox:" + exploadDateBasesonLastSavedVoucherChkBox);

		getAction().moveToElement(UpdateBtn).build().perform();
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(UpdateBtn));
		//ClickUsingJs(UpdateBtn);
		UpdateBtn.click();
		getWaitForAlert();

		String actAlertMgs = getAlert().getText();
		String expAlertMgs = excelReader.getCellData(xlSheetName, 89, 7);

		excelReader.setCellData(xlfile, xlSheetName, 89, 8, actAlertMgs);

		System.out.println("AlertMessage     : " + actAlertMgs + " Value Expected : " + expAlertMgs);

		getAlert().accept();

		String expMessage = excelReader.getCellData(xlSheetName, 90, 7);

		String actMessage = checkValidationMessage(expMessage);

		excelReader.setCellData(xlfile, xlSheetName, 90, 8, actMessage);

		System.out.println("Message     : " + actMessage + " Value Expected : " + expMessage);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(CloseBtn));
		CloseBtn.click();

		Thread.sleep(3000);
/*
		logout();

		Thread.sleep(3000);
		prongHornStartAtAdminLevel();
		Thread.sleep(3000);

		//checkLoginForPerferenceOptions();
		//checkLoginToSelectedCompany("Automation Options Company", "su", "su");
		//Thread.sleep(3000);
		checkLogin();
		Thread.sleep(4000);*/
		if (actMessage.equalsIgnoreCase(expMessage) && actAlertMgs.equalsIgnoreCase(expAlertMgs)
				&& actloadDateBasesonLastSavedVoucherChkBox
						.equalsIgnoreCase(exploadDateBasesonLastSavedVoucherChkBox)) {

			System.out.println("Test Pass : Option Enabled ");
			excelReader.setCellData(xlfile, xlSheetName, 87, 9, resPass);
			return true;

		} else {

			System.out.println("Test Fail : Option Not Enabled ");
			excelReader.setCellData(xlfile, xlSheetName, 87, 9, resFail);
			return false;
		}
	}

	public boolean checkloadDateBasesonLastSavedVoucherChkBoxFunctionality()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {

		excelReader = new ExcelReader(POJOUtility.getExcelPath());
		xlfile = getBaseDir() + "\\src\\main\\resources\\testdata\\FocusTestData.xlsx";

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(inventoryMenu));
		inventoryMenu.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(invTransactionsMenu));
		invTransactionsMenu.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(invTransPurchasesMenu));
		invTransPurchasesMenu.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(requestForQuoteVoucher));
		requestForQuoteVoucher.click();

		Thread.sleep(2000);

		click(newBtn);

		//checkUserFriendlyMessage();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dateTxt));
		dateTxt.click();

		dateTxt.sendKeys(Keys.HOME);

		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();

		System.out.println(df.format(date));

		String docdate = df.format(date);

		Calendar c = Calendar.getInstance();

		c.add(Calendar.DAY_OF_MONTH, -5);

		dateTxt.sendKeys(df.format(c.getTime()));

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dateTxt));
		String actVoucherHeaderDueDate = dateTxt.getAttribute("value");
		String expVoucherHeaderDueDate = df.format(c.getTime());

		Thread.sleep(4000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(vendorAccountTxt));
		vendorAccountTxt.click();
		vendorAccountTxt.sendKeys(Keys.SPACE);

		int vendorcount = vendorAccountListCount.size();

		System.err.println(vendorcount);

		for (int i = 0; i < vendorcount; i++) {
			String data = vendorAccountListCount.get(i).getText();

			if (data.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 92, 6))) {
				vendorAccountListCount.get(i).click();

				break;
			}
		}

		vendorAccountTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_1stColumn));
		select1stRow_1stColumn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_ItemTxt));
		enter_ItemTxt.click();
		Thread.sleep(2000);
		enter_ItemTxt.sendKeys(excelReader.getCellData(xlSheetName, 93, 6));
		Thread.sleep(2000);

		enter_ItemTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_3rdColumn));
		select1stRow_3rdColumn.click();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_4thColumn));
		select1stRow_4thColumn.click();

		System.out.println("Date Txt of Saved Voucher : " + actVoucherHeaderDueDate + " Value Expected: "
				+ expVoucherHeaderDueDate);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(voucherSaveBtn));
		voucherSaveBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(errorMessageCloseBtn));
		errorMessageCloseBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dateTxt));
		String actVoucherHeaderDueDate1 = dateTxt.getAttribute("value");
		String expVoucherHeaderDueDate1 = df.format(c.getTime());

		excelReader.setCellData(xlfile, xlSheetName, 94, 7, actVoucherHeaderDueDate);
		excelReader.setCellData(xlfile, xlSheetName, 94, 8, expVoucherHeaderDueDate);

		excelReader.setCellData(xlfile, xlSheetName, 95, 7, actVoucherHeaderDueDate1);
		excelReader.setCellData(xlfile, xlSheetName, 95, 8, expVoucherHeaderDueDate1);

		System.out.println(
				"Date Txt in New Voucher : " + actVoucherHeaderDueDate + " Value Expected: " + expVoucherHeaderDueDate);

		if (actVoucherHeaderDueDate1.equalsIgnoreCase(expVoucherHeaderDueDate1)
				&& actVoucherHeaderDueDate.equalsIgnoreCase(expVoucherHeaderDueDate)) {

			System.out.println("Test Pass : Display Currency as Expected  ");
			excelReader.setCellData(xlfile, xlSheetName, 91, 9, resPass);
			return true;

		} else {

			System.out.println("Test Fail :  Not Display Currency as Expected  ");
			excelReader.setCellData(xlfile, xlSheetName, 91, 9, resFail);
			return false;
		}

	}

	public boolean checkEnableopenSearchIfMasterNotFoundChkBoxOptions()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {

		excelReader = new ExcelReader(POJOUtility.getExcelPath());
		xlfile = getBaseDir() + "\\src\\main\\resources\\testdata\\FocusTestData.xlsx";

		Thread.sleep(3000);

		getDriver().navigate().refresh();

		Thread.sleep(3000);
	/*	prongHornStopAtAdminLevel();
		
		//relogin();
		Thread.sleep(3000);

		Thread.sleep(2000);*/

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(settingsMenu));
		ClickUsingJs(settingsMenu);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(Setting_PerferenceMenu));
		Setting_PerferenceMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MiselleanousBtn));
		MiselleanousBtn.click();

		Thread.sleep(3000);

		//JavascriptExecutor js = (JavascriptExecutor) getDriver();
		//js.executeScript("arguments[0].scrollIntoView();", loadDateBasesonLastSavedVoucherChkBox);
		getAction().moveToElement(loadDateBasesonLastSavedVoucherChkBox).build().perform();
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(loadDateBasesonLastSavedVoucherChkBox));
		if (loadDateBasesonLastSavedVoucherChkBoxTOSel.isSelected() == true) {
			getFluentWebDriverWait()
					.until(ExpectedConditions.elementToBeClickable(loadDateBasesonLastSavedVoucherChkBox));
			loadDateBasesonLastSavedVoucherChkBox.click();
		}

		Thread.sleep(3000);

		//JavascriptExecutor js1 = (JavascriptExecutor) getDriver();
		//js1.executeScript("arguments[0].scrollIntoView();", openSearchIfMasterNotFoundChkBox);
		getAction().moveToElement(openSearchIfMasterNotFoundChkBox).build().perform();
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(openSearchIfMasterNotFoundChkBox));
		if (openSearchIfMasterNotFoundChkBoxTOSel.isSelected() == false) {

			getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(openSearchIfMasterNotFoundChkBox));
			openSearchIfMasterNotFoundChkBox.click();

		}

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(openSearchIfMasterNotFoundChkBox));
		boolean actOpenSearchIfMasterNotFoundChkBox = openSearchIfMasterNotFoundChkBoxTOSel.isSelected();

		String actopenSearchIfMasterNotFoundChkBox = Boolean.toString(actOpenSearchIfMasterNotFoundChkBox);
		String expopenSearchIfMasterNotFoundChkBox = excelReader.getCellData(xlSheetName, 97, 7);
		excelReader.setCellData(xlfile, xlSheetName, 97, 8, actopenSearchIfMasterNotFoundChkBox);

		System.out.println(" Act Chkbox:" + actopenSearchIfMasterNotFoundChkBox);
		System.out.println(" Exp Chkbox:" + expopenSearchIfMasterNotFoundChkBox);

		getAction().moveToElement(UpdateBtn).build().perform();
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(UpdateBtn));
		//ClickUsingJs(UpdateBtn);
		UpdateBtn.click();
		getWaitForAlert();

		String actAlertMgs = getAlert().getText();
		String expAlertMgs = excelReader.getCellData(xlSheetName, 98, 7);

		excelReader.setCellData(xlfile, xlSheetName, 98, 8, actAlertMgs);

		System.out.println("AlertMessage     : " + actAlertMgs + " Value Expected : " + expAlertMgs);

		getAlert().accept();

		String expMessage = excelReader.getCellData(xlSheetName, 99, 7);

		String actMessage = checkValidationMessage(expMessage);

		excelReader.setCellData(xlfile, xlSheetName, 99, 8, actMessage);

		System.out.println("Message     : " + actMessage + " Value Expected : " + expMessage);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(CloseBtn));
		CloseBtn.click();

		Thread.sleep(3000);
/*
		logout();

		Thread.sleep(3000);
		prongHornStartAtAdminLevel();
		Thread.sleep(3000);

	//	checkLoginForPerferenceOptions();
	//	checkLoginToSelectedCompany("Automation Options Company", "su", "su");
		
		checkLogin();
		Thread.sleep(3000);*/

		if (actMessage.equalsIgnoreCase(expMessage) && actAlertMgs.equalsIgnoreCase(expAlertMgs)
				&& actopenSearchIfMasterNotFoundChkBox.equalsIgnoreCase(expopenSearchIfMasterNotFoundChkBox)) {

			System.out.println("Test Pass : Option Enabled ");
			excelReader.setCellData(xlfile, xlSheetName, 96, 9, resPass);
			return true;

		} else {

			System.out.println("Test Fail : Option Not Enabled ");
			excelReader.setCellData(xlfile, xlSheetName, 96, 9, resFail);
			return false;
		}
	}

	public boolean checkopenSearchIfMasterNotFoundChkBoxFunctionality()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {

		excelReader = new ExcelReader(POJOUtility.getExcelPath());
		xlfile = getBaseDir() + "\\src\\main\\resources\\testdata\\FocusTestData.xlsx";

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(financialsMenu));
		financialsMenu.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(financialsTransactionMenu));
		financialsTransactionMenu.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(financialsTransactionsPurchaseMenu));
		financialsTransactionsPurchaseMenu.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(purchaseVoucher));
		purchaseVoucher.click();

		Thread.sleep(2000);

		click(newBtn);

		//checkUserFriendlyMessage();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_1stColumn));
		select1stRow_1stColumn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_Warehouse2Txt));
		enter_Warehouse2Txt.click();
		Thread.sleep(2000);
		enter_Warehouse2Txt.sendKeys(excelReader.getCellData(xlSheetName, 101, 6));
		Thread.sleep(2000);

		enter_Warehouse2Txt.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		enter_ItemTxt.click();
		enter_ItemTxt.sendKeys(excelReader.getCellData(xlSheetName, 102, 6));

		Thread.sleep(3000);
		enter_ItemTxt.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
		boolean actSearchTxt = searchTxt.isDisplayed();

		String actsearchTxt = Boolean.toString(actSearchTxt);
		String expsearchTxt = excelReader.getCellData(xlSheetName, 103, 7);

		excelReader.setCellData(xlfile, xlSheetName, 103, 8, actsearchTxt);

		System.out.println("Search Display   : " + actsearchTxt + " Value Expected : " + expsearchTxt);

		clickOn(searchCancelTxt);

		Thread.sleep(2000);

		

		if (actsearchTxt.equalsIgnoreCase(expsearchTxt)) {
			excelReader.setCellData(xlfile, xlSheetName, 100, 9, resPass);
			return true;

		} else {
			excelReader.setCellData(xlfile, xlSheetName, 100, 9, resFail);
			return false;
		}

	}

	@FindBy(xpath = "//input[@id='id_body_23_search_popup_input']")
	private static WebElement searchTxt;

	@FindBy(xpath = "//input[@id='id_body_23_search_popup_category']")
	private static WebElement searchCatgeory;

	@FindBy(xpath = "//*[@id='id_body_23_search_popup']/div/div/div[3]/div/div[2]/input[3]")
	private static WebElement searchCancelTxt;

	public boolean checkEnabledontStoreDatesEntriesChkBoxOptions()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {

		excelReader = new ExcelReader(POJOUtility.getExcelPath());
		xlfile = getBaseDir() + "\\src\\main\\resources\\testdata\\FocusTestData.xlsx";

		Thread.sleep(3000);
		/*prongHornStopAtAdminLevel();
		
		//relogin();
		Thread.sleep(3000);

		Thread.sleep(2000);
*/
		
		getDriver().navigate().refresh();

		Thread.sleep(2000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(settingsMenu));
		ClickUsingJs(settingsMenu);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(Setting_PerferenceMenu));
		Setting_PerferenceMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MiselleanousBtn));
		MiselleanousBtn.click();

		Thread.sleep(3000);

		//JavascriptExecutor js = (JavascriptExecutor) getDriver();
		//js.executeScript("arguments[0].scrollIntoView();", openSearchIfMasterNotFoundChkBox);
		getAction().moveToElement(openSearchIfMasterNotFoundChkBox).build().perform();
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(openSearchIfMasterNotFoundChkBox));
		if (openSearchIfMasterNotFoundChkBoxTOSel.isSelected() == true) {
			getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(openSearchIfMasterNotFoundChkBox));
			openSearchIfMasterNotFoundChkBox.click();
		}

		Thread.sleep(3000);

		//JavascriptExecutor js1 = (JavascriptExecutor) getDriver();
		//js1.executeScript("arguments[0].scrollIntoView();", dontStoreDatesEntriesChkBox);
		getAction().moveToElement(dontStoreDatesEntriesChkBox).build().perform();
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dontStoreDatesEntriesChkBox));
		if (dontStoreDatesEntriesChkBoxTOSel.isSelected() == false) {

			getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dontStoreDatesEntriesChkBox));
			dontStoreDatesEntriesChkBox.click();

		}

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dontStoreDatesEntriesChkBox));
		boolean actDontStoreDatesEntriesChkBox = dontStoreDatesEntriesChkBoxTOSel.isSelected();

		String actdontStoreDatesEntriesChkBox = Boolean.toString(actDontStoreDatesEntriesChkBox);
		String expdontStoreDatesEntriesChkBox = excelReader.getCellData(xlSheetName, 105, 7);
		excelReader.setCellData(xlfile, xlSheetName, 105, 8, actdontStoreDatesEntriesChkBox);

		System.out.println(" Act Chkbox:" + actdontStoreDatesEntriesChkBox);
		System.out.println(" Exp Chkbox:" + expdontStoreDatesEntriesChkBox);

		getAction().moveToElement(UpdateBtn).build().perform();
		Thread.sleep(2000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(UpdateBtn));
		//ClickUsingJs(UpdateBtn);
		UpdateBtn.click();
		getWaitForAlert();

		String actAlertMgs = getAlert().getText();
		String expAlertMgs = excelReader.getCellData(xlSheetName, 106, 7);

		excelReader.setCellData(xlfile, xlSheetName, 106, 8, actAlertMgs);

		System.out.println("AlertMessage     : " + actAlertMgs + " Value Expected : " + expAlertMgs);

		getAlert().accept();

		String expMessage = excelReader.getCellData(xlSheetName, 107, 7);

		String actMessage = checkValidationMessage(expMessage);

		excelReader.setCellData(xlfile, xlSheetName, 107, 8, actMessage);

		System.out.println("Message     : " + actMessage + " Value Expected : " + expMessage);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(CloseBtn));
		CloseBtn.click();

		Thread.sleep(3000);
/*
		logout();

		Thread.sleep(3000);
		prongHornStartAtAdminLevel();
		Thread.sleep(3000);

	//	checkLoginForPerferenceOptions();
	//	checkLoginToSelectedCompany("Automation Options Company", "su", "su");
		Thread.sleep(3000);
		checkLogin();
		Thread.sleep(4000);*/
		if (actMessage.equalsIgnoreCase(expMessage) && actAlertMgs.equalsIgnoreCase(expAlertMgs)
				&& actdontStoreDatesEntriesChkBox.equalsIgnoreCase(expdontStoreDatesEntriesChkBox)) {

			System.out.println("Test Pass : Option Enabled ");
			excelReader.setCellData(xlfile, xlSheetName, 104, 9, resPass);
			return true;

		} else {

			System.out.println("Test Fail : Option Not Enabled ");
			excelReader.setCellData(xlfile, xlSheetName, 104, 9, resFail);
			return false;
		}
	}

	public boolean checkdontStoreDatesEntriesChkBoxInVoucher()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {

		excelReader = new ExcelReader(POJOUtility.getExcelPath());
		xlfile = getBaseDir() + "\\src\\main\\resources\\testdata\\FocusTestData.xlsx";

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(inventoryMenu));
		inventoryMenu.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(invTransactionsMenu));
		invTransactionsMenu.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(invTransStocksMenu));
		invTransStocksMenu.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(openingStocksNewVoucher));
		openingStocksNewVoucher.click();

		Thread.sleep(2000);

		click(newBtn);

		//checkUserFriendlyMessage();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(previousBtn));
		previousBtn.click();

		boolean loading = checkLoadingMessage();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(toggleBtn));
		clickOn(toggleBtn);

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(copyToClipBoardOption));
		copyToClipBoardOption.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(nextBtn));
		nextBtn.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(toggleBtn));
		toggleBtn.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(pasteFromClipBoardOption));
		pasteFromClipBoardOption.click();

		checkUserFriendlyMessage();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(voucherSaveBtn));
		voucherSaveBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(errorMessageCloseBtn));
		errorMessageCloseBtn.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(new_CloseBtn));
		new_CloseBtn.click();

		Thread.sleep(3000);

		boolean actModifiedDate = false;

		int VoucherBodyGridVouRowCount = voucherBodyGridVouRowCount.size();
		for (int i = 0; i < VoucherBodyGridVouRowCount; i++) {
			String docNo = voucherBodyGridVouRowCount.get(i).getText();

			if (docNo.equalsIgnoreCase("4") || docNo.equalsIgnoreCase("3")) {
				actModifiedDate = modifiedDateCol.get(i).getText().isEmpty();

				System.err.println("Loop data Entered : " + actModifiedDate);
				break;
			}
		}

		String actmodifiedDateCol = Boolean.toString(actModifiedDate);
		String expmodifiedDateCol = excelReader.getCellData(xlSheetName, 109, 7);

		excelReader.setCellData(xlfile, xlSheetName, 109, 8, actmodifiedDateCol);

		System.out.println("actmodifiedDateCol   : " + actmodifiedDateCol + " Value Expected " + expmodifiedDateCol);

		Thread.sleep(3000);
		//prongHornStopAtAdminLevel();
		//Thread.sleep(3000);

		if (actmodifiedDateCol.equalsIgnoreCase(expmodifiedDateCol)) {
			System.out.println("Date Is Displayed As Empty Data ");
			excelReader.setCellData(xlfile, xlSheetName, 108, 9, resPass);
			logout();
			return true;
		} else {

			System.out.println("Date Col Is Displayed With Data");
			excelReader.setCellData(xlfile, xlSheetName, 108, 9, resFail);
			logout();
			return false;
		}

	}

	@FindBy(xpath = "//*[@id='tblBodyTransRender']/tr/td[3]")
	private static List<WebElement> voucherBodyGridVouRowCount;

	@FindBy(xpath = "//*[@id='tblBodyTransRender']/tr/td[8]")
	private static List<WebElement> voucherBodyGridVouModifiedDate;

	@FindBy(xpath = "//*[@id='tblBodyTransRender']/tr/td[9]")
	private static List<WebElement> modifiedDateCol;

	@FindBy(xpath = "//*[@id='optCurrency_table_body']/tr")
	private static List<WebElement> defaultCurrecyList;

	@FindBy(xpath = "//*[@id='optMisExcludeVoucher_table_body']/tr")
	private static List<WebElement> excludeVoucherlist;

	public boolean checkMiscelloiniousDefaultCurrencyOptions()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {

		excelReader = new ExcelReader(POJOUtility.getExcelPath());
		xlfile = getBaseDir() + "\\src\\main\\resources\\testdata\\FocusTestData.xlsx";

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(settingsMenu));
		ClickUsingJs(settingsMenu);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(Setting_PerferenceMenu));
		Setting_PerferenceMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MiselleanousBtn));
		MiselleanousBtn.click();

		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript("arguments[0].scrollIntoView();", addfreeItemsonaNewLineChkBox);

		defaultCurrencyTxt.click();

		defaultCurrencyTxt.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME, Keys.SPACE);

		int defaultCurrecyListCount = defaultCurrecyList.size();

		ArrayList<String> defaultCurrecyListArray = new ArrayList<String>();

		for (int i = 1; i < defaultCurrecyListCount; i++) {
			String data = defaultCurrecyList.get(i).getText();
			defaultCurrecyListArray.add(data);
		}

		String actdefaultCurrecyList = defaultCurrecyListArray.toString();

		String expdefaultCurrecyList = excelReader.getCellData(xlSheetName, 111, 7);

		excelReader.setCellData(xlfile, xlSheetName, 111, 8, actdefaultCurrecyList);

		System.out.println("actdefaultCurrecyList   : " + actdefaultCurrecyList);
		System.out.println("ExpdefaultCurrecyList   : " + expdefaultCurrecyList);

		try {
			if (errorMessage.isDisplayed()) {
				errorMessageCloseBtn.click();
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		//logout();

		if (actdefaultCurrecyList.equalsIgnoreCase(expdefaultCurrecyList)) {
			System.out.println("Test Pass : Option Enabled ");
			excelReader.setCellData(xlfile, xlSheetName, 110, 9, resPass);
			return true;
		} else {

			System.out.println("Test Fail : Option Not Enabled ");
			excelReader.setCellData(xlfile, xlSheetName, 110, 9, resFail);
			return false;
		}
	}

	@FindBy(xpath = "//input[@id='id_EmailFromCust']")
	private static WebElement printScreenPickEmailChkbox;

	@FindBy(xpath = "//input[@id='id_EmailTo']")
	private static WebElement printScreenSentEmailTxt;

	@FindBy(xpath = "//input[@id='id_EmailCC']")
	private static WebElement printScreenCCTxt;

	@FindBy(xpath = "//input[@id='id_EmailSubject']")
	private static WebElement printSCreenSubjectTxt;

	@FindBy(xpath = "//select[@id='cmd_LayoutOptions']")
	private static WebElement printSCreenLayoutOption;

	@FindBy(xpath = "//button[@id='btnPrint']")
	private static WebElement emailSendBtn;

	@FindBy(xpath = "//div[@class='modal-content']//div[1]//div[2]//span[1]//i[1]")
	private static WebElement printAddBtn;

	@FindBy(xpath = "//span[contains(text(),'Header')]")
	private static WebElement headerExpandBtn;

	@FindBy(xpath = "//i[@class='icon-font6 icon-open']")
	private static WebElement Opendropdown;

	@FindBy(xpath = "//*[@id='forHf']/span[2]/ul/li/a[1]")
	private static WebElement OpenBtn;

	@FindBy(xpath = "//*[@id='3']")
	private static WebElement OpenBtn_First;

	@FindBy(xpath = "//*[@id='7']")
	private static WebElement OpenBtn_Second;

	@FindBy(xpath = "//button[@id='btn_PopUpOk']")
	private static WebElement OpenBtn_OkBtn;

	@FindBy(xpath = "//*[@id='id_PopUp']/div/div/div[3]/button[2]")
	private static WebElement OpenBtn_CancelBtn;

	@FindBy(xpath = "//*[@id='forHf']/span[2]/ul/li/a[2]")
	private static WebElement FromXMLBtn;

	@FindBy(xpath = "//i[@class='icon-font6 icon-save']")
	private static WebElement Savedropdown;

	@FindBy(xpath = "//*[@id='forHf']/span[3]/ul/li/a[1]")
	private static WebElement SaveBtn;

	@FindBy(xpath = "//input[@id='id_PopuplayoutName']")
	private static WebElement SaveTxtField;

	@FindBy(xpath = "//button[@id='btn_PopUpOk']")
	private static WebElement Save_OkBtn;

	@FindBy(xpath = "//*[@id='id_PopUp']/div/div/div[3]/button[2]")
	private static WebElement Save_CancekBtn;

	@FindBy(xpath = "//span[@id='id_InvoiceDesingCancel']")
	private static WebElement printCLoseBtn;

	@FindBy(xpath = "//input[@id='opt_LayoutID']")
	private static WebElement printlayoutTxt;

	@FindBy(xpath = "//input[@id='OutgoingPortNo']")
	private static WebElement outgoingPortTxt;

	@FindBy(xpath = "//input[@id='SSLSocketPort']")
	private static WebElement sslPortTXt;

	@FindBy(xpath = "//input[@id='EmailId']")
	private static WebElement MailSettingEmailTxt;

	@FindBy(xpath = "//input[@id='Password']")
	private static WebElement MailSettingPasswordTxt;

	public boolean checkMiscelloiniousInvoiceEmailSettingSendAsAttachementWithVoucherLevel()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {

		excelReader = new ExcelReader(POJOUtility.getExcelPath());
		xlfile = getBaseDir() + "\\src\\main\\resources\\testdata\\FocusTestData.xlsx";

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(settingsMenu));
		ClickUsingJs(settingsMenu);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(ConfigureTransactionBtn));
		ConfigureTransactionBtn.click();

		Thread.sleep(2000);

		JavascriptExecutor js1 = (JavascriptExecutor) getDriver();
		js1.executeScript("arguments[0].scrollIntoView();", MailSettingsBtn);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MailSettingsBtn));
		MailSettingsBtn.click();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(outgoingPortTxt));
		outgoingPortTxt.click();
		Thread.sleep(2000);
		outgoingPortTxt.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME);
		Thread.sleep(2000);
		outgoingPortTxt.sendKeys(excelReader.getCellData(xlSheetName, 112, 6));

		Thread.sleep(2000);
		outgoingPortTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sslPortTXt));
		sslPortTXt.click();

		sslPortTXt.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME);

		sslPortTXt.sendKeys(excelReader.getCellData(xlSheetName, 112, 6));

		Thread.sleep(2000);
		sslPortTXt.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		MailSettingEmailTxt.click();

		MailSettingEmailTxt.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME);

		MailSettingEmailTxt.sendKeys(excelReader.getCellData(xlSheetName, 114, 6));

		Thread.sleep(2000);
		MailSettingEmailTxt.sendKeys(Keys.TAB);

		MailSettingPasswordTxt.click();

		MailSettingPasswordTxt.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME);

		MailSettingPasswordTxt.sendKeys(excelReader.getCellData(xlSheetName, 113, 6));

		scrollPageUp(getDriver());

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(UpdateBtn));
		ClickUsingJs(UpdateBtn);

		getWaitForAlert();

		getAlert().accept();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(errorMessageCloseBtn));
		errorMessageCloseBtn.click();

		Thread.sleep(2000);

		Setting_PerferenceMenu.click();

		Thread.sleep(2000);

		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript("arguments[0].scrollIntoView();", sendAsAttachmentRadio);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sendAsAttachmentRadio));
		if (sendAsAttachmentRadio.isSelected() == false) {
			getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sendAsAttachmentRadio));
			sendAsAttachmentRadio.click();
		}

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(UpdateBtn));
		ClickUsingJs(UpdateBtn);

		getWaitForAlert();

		getAlert().accept();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(errorMessageCloseBtn));
		errorMessageCloseBtn.click();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(inventoryMenu));
		inventoryMenu.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(invTransactionsMenu));
		invTransactionsMenu.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(invTransStocksMenu));
		invTransStocksMenu.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(openingStocksNewVoucher));
		openingStocksNewVoucher.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(printBtn));
		printBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(printAddBtn));

		printAddBtn.click();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(Opendropdown));
		Opendropdown.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(FromXMLBtn));
		FromXMLBtn.click();

		Thread.sleep(5000);

		Runtime.getRuntime().exec(getBaseDir() + "\\autoIt\\scripts\\voucherprint.exe");

		Thread.sleep(3000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(Savedropdown));
		Savedropdown.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(SaveBtn));
		SaveBtn.click();

		SaveTxtField.click();
		SaveTxtField.sendKeys("Voucher");

		Thread.sleep(2000);
		SaveTxtField.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(Save_OkBtn));
		Save_OkBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(errorMessageCloseBtn));
		errorMessageCloseBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(printCLoseBtn));
		printCLoseBtn.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(printBtn));
		printBtn.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(printlayoutTxt));
		printlayoutTxt.click();

		printlayoutTxt.sendKeys("v");

		Thread.sleep(2000);
		printlayoutTxt.sendKeys(Keys.TAB);

		boolean Layout = printSCreenLayoutOption.isDisplayed();

		String actLayout = Boolean.toString(Layout);

		String expLayout = excelReader.getCellData(xlSheetName, 113, 7);

		excelReader.setCellData(xlfile, xlSheetName, 113, 8, actLayout);

		printSCreenLayoutOption.click();

		Thread.sleep(2000);

		Select s = new Select(printSCreenLayoutOption);

		s.selectByVisibleText("Email");

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(printScreenPickEmailChkbox));
		if (printScreenPickEmailChkbox.isSelected() == true) {
			getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(printScreenPickEmailChkbox));
			printScreenPickEmailChkbox.click();
		}

		printScreenSentEmailTxt.click();

		printScreenSentEmailTxt.sendKeys(excelReader.getCellData(xlSheetName, 114, 6));

		Thread.sleep(2000);

		printScreenSentEmailTxt.sendKeys(Keys.TAB);

		printSCreenSubjectTxt.click();

		printSCreenSubjectTxt.sendKeys(excelReader.getCellData(xlSheetName, 115, 6));

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(emailSendBtn));
		emailSendBtn.click();

		String expMessage = excelReader.getCellData(xlSheetName, 116, 7);

		String actMessage = checkValidationMessage(expMessage);

		excelReader.setCellData(xlfile, xlSheetName, 116, 8, actMessage);

		System.out.println("actMessage  : " + actMessage + "Value Expected :" + expMessage);

		if (actMessage.equalsIgnoreCase(expMessage)) {

			excelReader.setCellData(xlfile, xlSheetName, 112, 9, resPass);
			return true;
		} else {

			excelReader.setCellData(xlfile, xlSheetName, 112, 9, resFail);
			return false;
		}
	}

	@FindBy(xpath = "//input[@id='identifierId']")
	private static WebElement userNameTxt;

	@FindBy(xpath = "(//div[@class='VfPpkd-RLmnJb'])[2]")
	private static WebElement NextBtn;

	@FindBy(xpath = "//input[@name='password']")
	private static WebElement PasswordTxt;

	@FindBy(xpath = "//div[@id=':2i']")
	private static WebElement row1;

	@FindBy(xpath = "//span[@class='y2']")
	private static WebElement mailIDBody;

	@FindBy(xpath = "//*[@id=':2f']")
	private static WebElement mailIDBodyASBody;

	@FindBy(xpath = "//img[@class='CToWUd a6T']")
	private static WebElement mailBodyTxt;

	public boolean checkSentMailAsAttachement()
			throws EncryptedDocumentException, InvalidFormatException, IOException, AWTException, InterruptedException {

		excelReader = new ExcelReader(POJOUtility.getExcelPath());
		xlfile = getBaseDir() + "\\src\\main\\resources\\testdata\\FocusTestData.xlsx";

		Thread.sleep(5000);

		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_T);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_T);

		System.err.println("Completed");

		Thread.sleep(2000);

		ArrayList<String> openTabs = new ArrayList<String>(getDriver().getWindowHandles());

		System.out.println("openTabs" + openTabs);

		getDriver().switchTo().window(openTabs.get(0));

		getDriver().switchTo().window(openTabs.get(1));

		Thread.sleep(2000);
		getDriver().get(
				"https://accounts.google.com/ServiceLogin/identifier?service=mail&passive=true&rm=false&continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&ss=1&scc=1&ltmpl=default&ltmplcache=2&emr=1&osid=1&flowName=GlifWebSignIn&flowEntry=AddSession");

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(userNameTxt));
		userNameTxt.click();

		userNameTxt.sendKeys(excelReader.getCellData(xlSheetName, 114, 6));

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(NextBtn));
		NextBtn.click();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(PasswordTxt));
		PasswordTxt.click();

		PasswordTxt.sendKeys(excelReader.getCellData(xlSheetName, 113, 6));
		Thread.sleep(2000);

		NextBtn.click();

		Thread.sleep(8000);

		/*
		 * int mailIDListCount = mailIDList.size();
		 * 
		 * ArrayList<String> mailIDListArray = new ArrayList<String>();
		 * 
		 * for(int i=1;i<mailIDListCount;i++) { String data =
		 * mailIDList.get(i).getText(); mailIDListArray.add(data); }
		 */

		String actmailIDBody = mailIDBody.getText();

		String expmailIDBody = excelReader.getCellData(xlSheetName, 118, 7);

		excelReader.setCellData(xlfile, xlSheetName, 118, 8, actmailIDBody);

		System.out.println("mailIDBody  : " + actmailIDBody + " Value Expected  : " + expmailIDBody);

		Robot robot1 = new Robot();

		robot1.keyPress(KeyEvent.VK_CONTROL);
		robot1.keyPress(KeyEvent.VK_W);
		robot1.keyRelease(KeyEvent.VK_CONTROL);
		robot1.keyRelease(KeyEvent.VK_W);

		getDriver().switchTo().window(openTabs.get(0));

		if (actmailIDBody.contains(expmailIDBody)) {
			System.out.println(" Mail Displayed ");
			excelReader.setCellData(xlfile, xlSheetName, 117, 9, resPass);
			return true;
		} else {
			System.out.println(" Mail Not Displayed ");
			excelReader.setCellData(xlfile, xlSheetName, 117, 9, resFail);
			return false;
		}
	}

	public boolean checkMiscelloiniousInvoiceEmailSettingSendAsBodyWithVoucherLevel()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {

		excelReader = new ExcelReader(POJOUtility.getExcelPath());
		xlfile = getBaseDir() + "\\src\\main\\resources\\testdata\\FocusTestData.xlsx";

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(settingsMenu));
		ClickUsingJs(settingsMenu);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(ConfigureTransactionBtn));
		ConfigureTransactionBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MiselleanousBtn));
		MiselleanousBtn.click();

		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript("arguments[0].scrollIntoView();", sendASBodyRadio);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sendASBodyRadio));
		if (sendASBodyRadio.isSelected() == false) {
			getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sendASBodyRadio));
			sendASBodyRadio.click();
		}

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(UpdateBtn));
		ClickUsingJs(UpdateBtn);

		getWaitForAlert();

		getAlert().accept();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(errorMessageCloseBtn));
		errorMessageCloseBtn.click();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(inventoryMenu));
		inventoryMenu.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(invTransactionsMenu));
		invTransactionsMenu.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(invTransStocksMenu));
		invTransStocksMenu.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(openingStocksNewVoucher));
		openingStocksNewVoucher.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(printBtn));
		printBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(printAddBtn));

		printAddBtn.click();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(Opendropdown));
		Opendropdown.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(FromXMLBtn));
		FromXMLBtn.click();

		Thread.sleep(5000);

		Runtime.getRuntime().exec(getBaseDir() + "\\autoIt\\scripts\\voucherprint.exe");

		Thread.sleep(3000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(Savedropdown));
		Savedropdown.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(SaveBtn));
		SaveBtn.click();

		SaveTxtField.click();
		SaveTxtField.sendKeys("Voucher");

		Thread.sleep(2000);
		SaveTxtField.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(Save_OkBtn));
		Save_OkBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(errorMessageCloseBtn));
		errorMessageCloseBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(printCLoseBtn));
		printCLoseBtn.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(printBtn));
		printBtn.click();

		Thread.sleep(3000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(printlayoutTxt));
		printlayoutTxt.click();
		printlayoutTxt.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME);
		printlayoutTxt.sendKeys(Keys.SPACE);
		Thread.sleep(2000);

		printlayoutTxt.sendKeys("v");

		Thread.sleep(2000);
		printlayoutTxt.sendKeys(Keys.TAB);

		boolean Layout = printSCreenLayoutOption.isDisplayed();

		String actLayout = Boolean.toString(Layout);

		String expLayout = excelReader.getCellData(xlSheetName, 113, 7);

		excelReader.setCellData(xlfile, xlSheetName, 113, 8, actLayout);

		printSCreenLayoutOption.click();

		Thread.sleep(2000);

		Select s = new Select(printSCreenLayoutOption);

		s.selectByVisibleText("Email");

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(printScreenPickEmailChkbox));
		if (printScreenPickEmailChkbox.isSelected() == true) {
			getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(printScreenPickEmailChkbox));
			printScreenPickEmailChkbox.click();
		}

		printScreenSentEmailTxt.click();

		printScreenSentEmailTxt.sendKeys(excelReader.getCellData(xlSheetName, 121, 6));

		Thread.sleep(2000);

		printScreenSentEmailTxt.sendKeys(Keys.TAB);

		printSCreenSubjectTxt.click();

		printSCreenSubjectTxt.sendKeys(excelReader.getCellData(xlSheetName, 122, 6));

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(emailSendBtn));
		emailSendBtn.click();

		String expMessage = excelReader.getCellData(xlSheetName, 123, 7);

		String actMessage = checkValidationMessage(expMessage);

		excelReader.setCellData(xlfile, xlSheetName, 123, 8, actMessage);

		System.out.println("actMessage  : " + actMessage + "Value Expected :" + expMessage);

		if (actMessage.equalsIgnoreCase(expMessage)) {

			excelReader.setCellData(xlfile, xlSheetName, 119, 9, resPass);
			return true;
		} else {

			excelReader.setCellData(xlfile, xlSheetName, 119, 9, resFail);
			return false;
		}
	}

	public boolean checkSentMailAsBody()
			throws EncryptedDocumentException, InvalidFormatException, IOException, AWTException, InterruptedException {

		excelReader = new ExcelReader(POJOUtility.getExcelPath());
		xlfile = getBaseDir() + "\\src\\main\\resources\\testdata\\FocusTestData.xlsx";

		Thread.sleep(3000);

		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_T);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_T);

		System.err.println("Completed");

		Thread.sleep(2000);

		ArrayList<String> openTabs = new ArrayList<String>(getDriver().getWindowHandles());

		System.out.println("openTabs" + openTabs);

		getDriver().switchTo().window(openTabs.get(0));

		getDriver().switchTo().window(openTabs.get(1));

		getDriver().get(
				"https://accounts.google.com/ServiceLogin/identifier?service=mail&passive=true&rm=false&continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&ss=1&scc=1&ltmpl=default&ltmplcache=2&emr=1&osid=1&flowName=GlifWebSignIn&flowEntry=AddSession");

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(userNameTxt));
		userNameTxt.click();

		userNameTxt.sendKeys(excelReader.getCellData(xlSheetName, 114, 6));

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(NextBtn));
		NextBtn.click();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(PasswordTxt));
		PasswordTxt.click();

		PasswordTxt.sendKeys(excelReader.getCellData(xlSheetName, 113, 6));
		Thread.sleep(2000);

		NextBtn.click();

		Thread.sleep(8000);

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(mailIDBodyASBody));

		getAction().doubleClick(mailIDBodyASBody).build().perform();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(mailBodyTxt));

		boolean MailIDBody = mailBodyTxt.isDisplayed();

		String actmailIDBody = Boolean.toString(MailIDBody);
		String expmailIDBody = excelReader.getCellData(xlSheetName, 125, 7);

		excelReader.setCellData(xlfile, xlSheetName, 125, 8, actmailIDBody);

		System.out.println("mailIDBody  :" + actmailIDBody + " Value Expected  : " + expmailIDBody);

		Robot robot1 = new Robot();

		robot1.keyPress(KeyEvent.VK_CONTROL);
		robot1.keyPress(KeyEvent.VK_W);
		robot1.keyRelease(KeyEvent.VK_CONTROL);
		robot1.keyRelease(KeyEvent.VK_W);

		getDriver().switchTo().window(openTabs.get(0));

		if (actmailIDBody.contains(expmailIDBody)) {
			System.out.println(" Mail Displayed ");
			excelReader.setCellData(xlfile, xlSheetName, 124, 9, resPass);
			return true;
		} else {
			System.out.println(" Mail Not Displayed ");
			excelReader.setCellData(xlfile, xlSheetName, 124, 9, resFail);
			return false;
		}
	}

	@FindBy(xpath = "//td[@id='gPrefMisExcludeVoucher_col_1-1']")
	private static WebElement excludeVoucherRow1;

	@FindBy(xpath = "//td[@id='gPrefMisExcludeVoucher_col_2-1']")
	private static WebElement excludeVoucherRow2;

	@FindBy(xpath = "//input[@id='optMisExcludeVoucher']")
	private static WebElement enterExcluduVoucher;

	public boolean checkMiscelloiniousRepostOptions()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {

		excelReader = new ExcelReader(POJOUtility.getExcelPath());
		xlfile = getBaseDir() + "\\src\\main\\resources\\testdata\\FocusTestData.xlsx";

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(settingsMenu));
		ClickUsingJs(settingsMenu);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(ConfigureTransactionBtn));
		ConfigureTransactionBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MiselleanousBtn));
		MiselleanousBtn.click();

		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript("arguments[0].scrollIntoView();", excludeVoucherRow1);

		Thread.sleep(2000);
		excludeVoucherRow1.click();
		Thread.sleep(2000);

		enterExcluduVoucher.click();
		enterExcluduVoucher.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME);
		enterExcluduVoucher.sendKeys(Keys.SPACE);

		Thread.sleep(2000);
		int excludeVoucherlistCount = excludeVoucherlist.size();

		ArrayList<String> excludeVoucherlistArray = new ArrayList<String>();

		for (int i = 1; i < excludeVoucherlistCount; i++) {
			String data = excludeVoucherlist.get(i).getText();

			excludeVoucherlistArray.add(data);

			if (data.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 128, 6))) {
				excludeVoucherlist.get(i).click();

				break;
			}

		}

		enterExcluduVoucher.sendKeys(Keys.TAB);

		String actexcludeVoucherlist = excludeVoucherlistArray.toString();

		String expexcludeVoucherlist = excelReader.getCellData(xlSheetName, 127, 7);

		excelReader.setCellData(xlfile, xlSheetName, 127, 8, actexcludeVoucherlist);

		System.out.println("actexcludeVoucherlist   : " + actexcludeVoucherlist);
		System.out.println("expexcludeVoucherlist   : " + expexcludeVoucherlist);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(UpdateBtn));
		ClickUsingJs(UpdateBtn);

		getWaitForAlert();

		getAlert().accept();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(errorMessageCloseBtn));
		errorMessageCloseBtn.click();

		if (actexcludeVoucherlist.equalsIgnoreCase(expexcludeVoucherlist)) {
			System.out.println("Test Pass : Displayed Exclude Option Enabled ");
			excelReader.setCellData(xlfile, xlSheetName, 126, 9, resPass);
			return true;
		} else {

			System.out.println("Test Fail : Displayed Exclude Option Enabled ");
			excelReader.setCellData(xlfile, xlSheetName, 126, 9, resFail);
			return false;
		}
	}

	@FindBy(xpath = "//input[@id='startDate']")
	private static WebElement repostStartDateTxt;

	@FindBy(xpath = "//input[@id='endDate']")
	private static WebElement repostEndDateTxt;

	@FindBy(xpath = "//input[@id='endDate_checkbox']")
	private static WebElement repostEndDateChkbox;

	@FindBy(xpath = "//input[@id='stocktransfer']")
	private static WebElement reportIgoneStocktransferForRateChkbox;

	@FindBy(xpath = "//input[@id='stocktransfer2']")
	private static WebElement repostComprehensiveRePostChkbox;

	@FindBy(xpath = "//input[@id='stocktransfer3']")
	private static WebElement repostOnlyFinanceStockDocuments;

	@FindBy(xpath = "//input[@id='overwritedocorders']")
	private static WebElement repostOverwriteDocumentOrders;

	@FindBy(xpath = "//td[@id='id_RepostVoucher_col_1-1']")
	private static WebElement repostGridRow1;

	@FindBy(xpath = "//td[@id='id_RepostVoucher_col_2-1']")
	private static WebElement repostGridRow2;

	@FindBy(xpath = "//input[@id='optRepostVoucherType']")
	private static WebElement enterRepostGrid;

	@FindBy(xpath = "//*[@id='optRepostVoucherType_table_body']/tr")
	private static List<WebElement> repostGridVoucherList;

	@FindBy(xpath = "//span[contains(text(),'Ok')]")
	private static WebElement repostOkBtn;

	@FindBy(xpath = "//span[@class='theme_button_color'][contains(text(),'Close')]")
	private static WebElement repostCloseBtn;

	public boolean checkRepostVOucherScreen()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {

		excelReader = new ExcelReader(POJOUtility.getExcelPath());
		xlfile = getBaseDir() + "\\src\\main\\resources\\testdata\\FocusTestData.xlsx";

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(homeMenu));
		homeMenu.click();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(utilities));
		utilities.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(repostVoucher));
		repostVoucher.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(repostGridRow1));
		repostGridRow1.click();
		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enterRepostGrid));
		enterRepostGrid.click();
		enterRepostGrid.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME);
		enterRepostGrid.sendKeys(Keys.SPACE);

		Thread.sleep(2000);
		int repostGridVoucherListCount = repostGridVoucherList.size();

		System.out.println("repostGridVoucherListCount  : " + repostGridVoucherListCount);

		ArrayList<String> repostGridVoucherListArray = new ArrayList<String>();

		for (int i = 1; i < repostGridVoucherListCount; i++) {
			String data = repostGridVoucherList.get(i).getText();

			repostGridVoucherListArray.add(data);

			if (data.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 131, 6))) {
				repostGridVoucherList.get(i).click();

				break;
			}

		}

		enterRepostGrid.sendKeys(Keys.TAB);

		String actrepostGridVoucherList = repostGridVoucherListArray.toString();

		String exprepostGridVoucherList = excelReader.getCellData(xlSheetName, 130, 7);

		excelReader.setCellData(xlfile, xlSheetName, 130, 8, actrepostGridVoucherList);

		System.out.println("actrepostGridVoucherList   : " + actrepostGridVoucherList);
		System.out.println("exprepostGridVoucherList   : " + exprepostGridVoucherList);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(repostOkBtn));
		repostOkBtn.click();

		String expMessage = excelReader.getCellData(xlSheetName, 132, 7);

		String actMessage = checkValidationMessage(expMessage);

		excelReader.setCellData(xlfile, xlSheetName, 132, 8, actMessage);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(repostCloseBtn));
		repostCloseBtn.click();

		if (actrepostGridVoucherList.equalsIgnoreCase(exprepostGridVoucherList)) {
			System.out.println("Test Pass : Displayed Exclude Option Enabled ");
			excelReader.setCellData(xlfile, xlSheetName, 129, 9, resPass);
			return true;
		} else {

			System.out.println("Test Fail : Displayed Exclude Option Enabled ");
			excelReader.setCellData(xlfile, xlSheetName, 129, 9, resFail);
			return false;
		}
	}

	public boolean checkRepostAllVoucherFromREpostScreen()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {

		excelReader = new ExcelReader(POJOUtility.getExcelPath());
		xlfile = getBaseDir() + "\\src\\main\\resources\\testdata\\FocusTestData.xlsx";

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(homeMenu));
		homeMenu.click();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(utilities));
		utilities.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(repostVoucher));
		repostVoucher.click();

		Thread.sleep(2000);

		scrollPageUp(getDriver());

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(repostOkBtn));
		repostOkBtn.click();

		if (getIsAlertPresent()) {
			getWaitForAlert();

			String actAlertMgs = getAlert().getText();
			String expAlertMgs = excelReader.getCellData(xlSheetName, 134, 7);

			excelReader.setCellData(xlfile, xlSheetName, 134, 8, actAlertMgs);

			System.out.println("AlertMessage     : " + actAlertMgs + " Value Expected : " + expAlertMgs);

			getAlert().accept();
		}

		String expMessage = excelReader.getCellData(xlSheetName, 135, 7);

		String actMessage = checkValidationMessage(expMessage);

		excelReader.setCellData(xlfile, xlSheetName, 135, 8, actMessage);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(repostCloseBtn));
		repostCloseBtn.click();

		if (actMessage.equalsIgnoreCase(expMessage)) {
			System.out.println("Test Pass : Alert Message As Expected  ");
			excelReader.setCellData(xlfile, xlSheetName, 133, 9, resPass);
			return true;
		} else {

			System.out.println("Test Fail : Alert Message As nOt  Expected  ");
			excelReader.setCellData(xlfile, xlSheetName, 133, 9, resFail);
			return false;
		}
	}

	public static boolean checkLogin()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		excelReader = new ExcelReader(POJOUtility.getExcelPath());

		Thread.sleep(1999);
		getDriver().navigate().refresh();
		Thread.sleep(1999);
		
		/*
		 * try { if (userNameDisplayLogo.isDisplayed()) { logout(); }
		 * 
		 * } catch (Exception e) {
		 * 
		 * }
		 *
		 
		getDriver().navigate().refresh();
		Thread.sleep(1999);*/

		LoginPage lp = new LoginPage(getDriver());

		lp.checkLoginPageTitleByURLInputInBrowser(DriverUtility.FINUrl);

		re_LunchBrowser();

		Thread.sleep(3000);

		String unamelt = /*excelReader.getCellData(xlSheetName, 7, 5)*/"su";

		String pawslt = /*excelReader.getCellData(xlSheetName, 8, 5)*/"su";

		lp.enterUserName(unamelt);

		Thread.sleep(2000);

		lp.enterPassword(pawslt);

		String compname = "Automation Options Company";

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

		Thread.sleep(9999);

		//waitOn(financialsMenu);

		String expFinancialsMenuDisplayed = excelReader.getCellData(xlSheetName, 10, 6);
		String actFinancialsMenuDisplayed = Boolean.toString(financialsMenu.isDisplayed());
		excelReader.setCellData(xlfile, xlSheetName, 10, 7, actFinancialsMenuDisplayed);

		if (actFinancialsMenuDisplayed.equalsIgnoreCase(expFinancialsMenuDisplayed)) {
			excelReader.setCellData(xlfile, xlSheetName, 6, 8, resPass);
			return true;

		} else {
			excelReader.setCellData(xlfile, xlSheetName, 6, 8, resFail);
			return false;

		}
		  

	}
	
	
	public preferenceMiscellinousOptionPage(WebDriver driver) {

		PageFactory.initElements(driver, this);

	}

}