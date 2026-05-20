package com.focus.Pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import com.focus.base.BaseEngine;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.collections4.bag.SynchronizedSortedBag;
import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xdgf.usermodel.section.geometry.GeometryRowFactory;
//import org.omg.IOP.ExceptionDetailMessage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import com.focus.supporters.ExcelReader;
import com.focus.utilities.POJOUtility;
import com.testautomationguru.utility.PDFUtil;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import com.focus.base.BaseEngine;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.remote.server.handler.GetAllWindowHandles;

public class ReportDesignerPage1 extends BaseEngine {
	// Logout and Login Screen

	@FindBy(xpath = "//*[@id='txtUsername']")
	private static WebElement username;

	@FindBy(id = "txtPassword")
	private static WebElement password;

	@FindBy(id = "btnSignin")
	private static WebElement signIn;

	@FindBy(id = "ddlCompany")
	private static WebElement companyDropDownList;

	/*
	 * @FindBy(xpath=
	 * "/html[1]/body[1]/section[1]/div[2]/header[1]/nav[1]/div[1]/ul[1]/li[5]/a[1]/span[1]")
	 * private static WebElement userNameDisplay;
	 */

	@FindBy(xpath = "//span[@class='hidden-xs']")
	private static WebElement userNameDisplay;

	@FindBy(xpath = "//*[@id='companyLogo']")
	private static WebElement companyLogo;

	@FindBy(xpath = "//*[@id='ulCompanyDetails_HomePage']/li[1]")
	private static WebElement companyName;

	@FindBy(xpath = "//*[@id='userprofile']/li/span[2]")
	private static WebElement logoutOption;

	@FindBy(xpath = "//input[@id='donotshow']")
	private static WebElement doNotShowCheckbox;

	@FindBy(xpath = "//span[@class='pull-right']")
	private static WebElement closeBtnInDemoPopupScreen;

	public static void checkPopUpWindow()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {

		try {
			getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(doNotShowCheckbox));
			doNotShowCheckbox.click();

			getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(closeBtnInDemoPopupScreen));
			closeBtnInDemoPopupScreen.click();

			System.err.println("POP UP DISPLAYED AND CLOSED SUCCESSFULLY");

		} catch (Exception e) {
			System.err.println("NO POP UP DISPLAYED");
		}

	}

	@FindBy(xpath = "//div[@class='theme_color font-6']")
	public static WebElement errorMessage;

	@FindBy(xpath = "//span[@class='icon-reject2 theme_color']")
	public static WebElement errorMessageCloseBtn;

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

	}

	private static String xlfile;
	private static String resPass = "Pass";
	private static String resFail = "Fail";
	private static ExcelReader excelReader;

	private static int cSize;

	@FindBy(xpath = "//*[@id='dashName']")
	private static WebElement labelDashboard;

	@FindBy(xpath = "//*[@id='Select_dash']")
	private static WebElement selectDashboard;

	@FindBy(xpath = "//*[@id='Dashboard_AddDash']")
	private static WebElement newAddDashBoard;

	@FindBy(xpath = "//*[@id='Dashboard_Dash_Config']")
	private static WebElement dashboardCustomizationSettings;

	public static boolean checkLoginForReportDesigner()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {

		LoginPage lp = new LoginPage(getDriver());

		String unamelt = "su";

		String pawslt = "su";

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(username));
		username.click();
		Thread.sleep(2000);
		username.clear();
		Thread.sleep(2000);
		username.sendKeys(unamelt);
		getAction().moveToElement(username).sendKeys(Keys.TAB).perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(password));
		password.click();
		Thread.sleep(2000);
		password.clear();
		Thread.sleep(2000);
		password.sendKeys(pawslt);

		/*
		 * String compname="Automation Company";
		 * 
		 * Select oSelect = new Select(companyDropDownList);
		 * 
		 * List <WebElement> elementCount = oSelect.getOptions();
		 * 
		 * int cqSize = elementCount.size();
		 * 
		 * int zqSize=cSize+1;
		 * 
		 * System.out.println("CompanyDropdownList Count :"+cqSize);
		 * 
		 * System.out.println("Company dropdown is :"+ zqSize);
		 * 
		 * 
		 * //Select dropdown= new Select(lp.companyDropDownList); int i;
		 * 
		 * //List<WebElement> list = dropdown.getOptions();
		 * 
		 * //List<String> text = new ArrayList<>(); for(i=0; i<elementCount.size(); i++)
		 * { elementCount.get(i).getText(); String optionName =
		 * elementCount.get(i).getText();
		 * if(optionName.toUpperCase().startsWith(compname.toUpperCase())) {
		 * System.out.println("q"+elementCount.get(i).getText());
		 * elementCount.get(i).click(); } }
		 */

		lp.clickOnSignInBtn();

		// checkRefershPopOnlogin();

		// checkPopUpWindow();

		Thread.sleep(8000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(userNameDisplay));
		userNameDisplay.click();

		String userInfo = userNameDisplay.getText();

		System.out.println("User Info : " + userInfo);

		System.out.println("User Info Capture Text :" + userNameDisplay.getText());

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(companyLogo));

		companyLogo.click();

		String getCompanyTxt = companyName.getText();
		String getLoginCompanyName = getCompanyTxt.substring(0, 19);
		System.out.println("company name :" + getLoginCompanyName);
		companyLogo.click();

		String expuserInfo = "SU";
		String expLoginCompanyName = "Automation Company ";
		String expDashboard = "Graph with Active and setAsDefault";
		boolean expdashboardGraph = true;
		boolean expdashboardLedger = true;
		boolean expdashboardInfoPanel = true;
		String expAccountsTitle = "Account";

		System.out.println("***********************************checkOpenAccountsMenu*********************************");

		System.out.println("User Info                        : " + userInfo + "  value expected  " + expuserInfo);
		System.out.println("Login Company Name               : " + getLoginCompanyName + "  value expected  "
				+ expLoginCompanyName);

		if (userInfo.equalsIgnoreCase(expuserInfo) && getLoginCompanyName.equalsIgnoreCase(expLoginCompanyName)) {
			return true;
		} else {
			return false;
		}
	}

	@FindBy(xpath = "//a[@id='22']//span[contains(text(),'Report Designer')]")
	private static WebElement reportDesignerMenu;

	@FindBy(xpath = "//span[@class='icon-xmlimport icon-font6']")
	private static WebElement importFromExcelBtn;

	@FindBy(xpath = "//div[@id='myNavbar']//ul[@class='nav navbar-nav navbar-right']")
	private static WebElement exportFromExcelBtn;

	@FindBy(xpath = "//div[contains(text(),'Reset')]")
	private static WebElement resetBtn;

	@FindBy(xpath = "//div[contains(text(),'Delete')]")
	private static WebElement deleteBtn;

	@FindBy(xpath = "//div[@id='myNavbar']//ul[@class='nav navbar-nav navbar-right']")
	private static WebElement backTab;

	@FindBy(xpath = "//div[@onclick='RD_ENTRY.onNext_Click();']//div[@class='col-sm-12 toolbar_button_image']")
	private static WebElement nextTab;

	@FindBy(xpath = "//span[@class='icon-ok icon-font6']")
	private static WebElement finishBtn;

	@FindBy(xpath = "//*[@id='myNavbar']/ul/li[1]")
	private static WebElement rdImportFromXML;

	@FindBy(xpath = "//*[@id='myNavbar']/ul/li[2]")
	private static WebElement rdExportToXML;

	@FindBy(xpath = "//span[@class='icon-close icon-font6']")
	private static WebElement cancelBtn;

	@FindBy(xpath = "//*[@id='id_rd_header_button_group_1']/div[1]")
	private static WebElement definitionTab;

	@FindBy(xpath = "//input[@id='id_rd_definition_reportname']")
	private static WebElement reportNameDropdown;

	@FindBy(xpath = "//select[@id='id_rd_definition_reporttype']")
	private static WebElement reportTypeDropdown;

	@FindBy(xpath = "//input[@id='advanceEngine']")
	private static WebElement advanceEngineCkeckbox;

	@FindBy(xpath = "//select[@id='id_rd_definition_modules']")
	private static WebElement moduleDropdown;

	@FindBy(xpath = "//select[@id='id_rd_definition_datasets']")
	private static WebElement dataSetDropdown;

	@FindBy(xpath = "//input[@id='id_rd_parameter_entry_fieldname']")
	private static WebElement fieldNameTextbox;

	@FindBy(xpath = "//input[@id='id_rd_parameter_entry_variablename']")
	private static WebElement variablenameTextbox;

	@FindBy(xpath = "//select[@id='id_rd_parameter_entry_fieldtype']")
	private static WebElement fieldTypeDropddown;

	@FindBy(xpath = "//input[@id='id_rd_parameter_entry_viewname']")
	private static WebElement viewnameTextBox;

	@FindBy(xpath = "//input[@id='id_rd_parameter_entry_valuecolumn']")
	private static WebElement valueColoumTextBox;

	@FindBy(xpath = "//input[@id='id_rd_parameter_entry_displaycolumn']")
	private static WebElement displayColumnTexBox;

	@FindBy(xpath = "//input[@onclick='RD_ENTRY.PARAMETER.onOK_Click();']")
	private static WebElement okBtn;

	@FindBy(xpath = "//input[@onclick='RD_ENTRY.PARAMETER.onCancel_Click();']")
	private static WebElement cancelBtnatLast;

	@FindBy(xpath = "//input[@value='Add']")
	private static WebElement addBtn;

	@FindBy(xpath = "//input[@onclick='RD_ENTRY.PARAMETER.onDelete_Click();']")
	private static WebElement deleteBtnatLast;

	@FindBy(xpath = "//*[@id='1']/div/span")
	public static WebElement homeMenu;

	@FindBy(xpath = "//*[@id='navigation_menu']/li[1]/ul/li[5]/ul/li")
	private static List<WebElement> utilitesList;

	@FindBy(xpath = "//a[@id='20']//span[contains(text(),'Utilities')]")
	private static WebElement utilities;

	@FindBy(xpath = "//*[@id='id_menu_tree_135']/a/i")
	private static WebElement inventoryExpandBtn;

	/*
	 * @FindBy(xpath="//*[@id='id_menu_tree_60']/a/span") private static WebElement
	 * finanincalExpandbtn;
	 */

	@FindBy(xpath = "//*[@id='id_menu_tree_60']/a")
	private static WebElement finanincalExpandbtn;

	@FindBy(xpath = "//*[@id='id_menu_tree_60']/a/i")
	private static WebElement finanincalExpandbtn1;

	@FindBy(xpath = "//*[@id='id_menu_tree_81']/a/span")
	private static WebElement finanincalReportsExpandbtn;

	@FindBy(xpath = "//*[@id='id_menu_tree_200']/a/span")
	private static WebElement reportsBtn;

	// DATA SET Tab

	@FindBy(xpath = "//*[@id='id_rd_header_button_group_2']/div[1]")
	private static WebElement dataSetTab;

	@FindBy(xpath = "//span[@id='id_rd_customization_transet_tab_add']")
	private static WebElement plusBtn;

	@FindBy(xpath = "//select[@id='id_rd_transet0_documentstatus']")
	private static WebElement documentStatsDropdown;

	@FindBy(xpath = "//select[@id='id_rd_transet0_verificationstatus']")
	private static WebElement verificationDropdown;

	@FindBy(xpath = "//select[@id='id_rd_transet0_authorizationstatus']")
	private static WebElement authorizationStatusDropdown;

	@FindBy(xpath = "//select[@id='id_rd_transet0_brsstatus']")
	private static WebElement brsStatusDropdown;

	@FindBy(xpath = "//span[@id='idFilterCustomizeIcon']")
	private static WebElement customizeIcon;

	@FindBy(xpath = "//span[@id='a']")
	private static WebElement filterIcon;

	@FindBy(xpath = "//*[@id='id_rd_transet0_select']/li")
	private static List<WebElement> transactionSetList;

	// Customization Tab

	@FindBy(xpath = "//*[@id='id_rd_header_button_group_3']/div[1]/span[2]")
	private static WebElement customizationTab;

	@FindBy(xpath = "//span[contains(text(),'Sorting')]")
	private static WebElement customizationTABSortingTAB;

	@FindBy(xpath = "//*[@id='id_rd_customization_sorting_source']/li")
	private static List<WebElement> sortingTABSourceList;

	@FindBy(xpath = "//*[@id='id_rd_sorting_buttons_container']/button[1]")
	private static WebElement sortingTABRightRowBtn;

	@FindBy(xpath = "//*[@id='id_rd_sorting_buttons_container']/button[2]")
	private static WebElement sortingLeftArrowBtn;

	@FindBy(xpath = "//select[@id='id_rd_customization_sorting_select']")
	private static WebElement sortingtabSelectDrpdwn;

	@FindBy(xpath = "//*[@id='id_rd_customization_sorting_container']/div[2]/div[5]/div/input[1]")
	private static WebElement sortingtabOkBtn;

	@FindBy(xpath = "//div[@id='id_rd_customization_columnproperty']//div[@class='col-sm-12 toolbar_button_image']")
	private static WebElement columnPropertyTab;

	@FindBy(xpath = "//input[@id='id_rd_customization_tree_search']")
	private static WebElement searchBox;

	@FindBy(xpath = "//span[@class='icon-searchnumaric icon-font6']")
	private static WebElement searchNumericIcon;

	@FindBy(xpath = "//span[@class='glyphicon glyphicon-text-color']")
	private static WebElement searchStringItemIcon;

	@FindBy(xpath = "//span[@class='icon-left-and-right-panel-icon icon-font6']")
	private static WebElement closingPanelIcon;

	@FindBy(xpath = "//input[@value='Create Virtual Field']")
	private static WebElement createVirtualFieldBox;

	// Row Formatting Tab

	@FindBy(xpath = "//div[@id='id_rd_customization_rowformatting']//div[@class='col-sm-12 toolbar_button_image']")
	private static WebElement rowFormattingTab;

	@FindBy(xpath = "//input[@id='rfnew']")
	private static WebElement newBtn;

	@FindBy(xpath = "//input[@id='rfdelete']")
	private static WebElement deleteBtnInRf;

	@FindBy(xpath = "//select[@id='id_set_on_column']")
	private static WebElement setOnColumnDropdown;

	@FindBy(xpath = "//input[@id='id_label_for_condition']")
	private static WebElement labelForConditionTextBox;

	@FindBy(xpath = "//div[@id='id_rowformatting_browsefile_ctrl_container']//table")
	private static WebElement imageForCondtionFileElement;

	@FindBy(xpath = "//span[@class='icon-edit icon-font7 theme_color-inverse FAttachment_Img']")
	private static WebElement imageForConitionNeworEditFile;

	@FindBy(xpath = "//input[@id='id_rf_font']")
	private static WebElement fontBtn;

	@FindBy(xpath = "//input[@id='id_DontUseAbsoluteValue']")
	private static WebElement useDefaultValueCheckBox;

	@FindBy(xpath = "//input[@id='rfok']")
	private static WebElement okBtnAtLast;

	@FindBy(xpath = "//input[@id='rfclear']")
	private static WebElement cancelBtnAtLast;

	// SORTING TAB

	@FindBy(xpath = "//div[@id='id_rd_customization_sorting']//div[contains(@class,'col-sm-12 toolbar_button_image')]")
	private static WebElement sortingTab;

	@FindBy(xpath = "//i[contains(@class,'fa fa-caret-right fa-2x')]")
	private static WebElement forwardBtn;

	@FindBy(xpath = "//i[contains(@class,'fa fa-caret-left fa-2x')]")
	private static WebElement backwardBtn;

	@FindBy(xpath = "//select[@id='id_rd_customization_sorting_select']")
	private static WebElement noneDropdown;

	@FindBy(xpath = "//input[@id='id_rd_customization_sorting_rows']")
	private static WebElement emptyTextArea;

	@FindBy(xpath = "//input[contains(@onclick,'RD_ENTRY.CUSTOMIZATION.SORTING.onOK_Click(event);')]")
	private static WebElement okButton;

	@FindBy(xpath = "//input[contains(@onclick,'RD_ENTRY.on_Customization_ColumnProperty_Click();')]")
	private static WebElement cancelButton;

	@FindBy(xpath = "//label[normalize-space()='Landscape Orientation']")
	private static WebElement landScapeOrientationTab;

	@FindBy(xpath = "//l")
	private static WebElement printZeroValue;

	@FindBy(xpath = "//div[@id='id_rd_header_button_group_4']//div[contains(@class,'btn col-xs-12 header_button_group stop_text_overflow theme_background-color theme_color')]")
	private static WebElement headerFooterTab;

	@FindBy(xpath = "//div[@id='id_rd_header_button_group_5']//div[contains(@class,'btn col-xs-12 header_button_group stop_text_overflow theme_background-color theme_color')]")
	private static WebElement previewTab;

	@FindBy(xpath = "//*[@id='rd_customization_tree0']/a/i")
	private static WebElement transactionExpandBtn;

	@FindBy(xpath = "//input[@id='id_rd_customization_tree_search']")
	private static WebElement custTabFieldSearchTxt;

	@FindBy(xpath = "//*[@id='rd_customization_tree1']/a/span")
	private static WebElement custTabSearchFirstField;

	@FindBy(xpath = "//span[contains(text(),'Date.Date')]")
	private static WebElement custTabSearchDATEField;

	@FindBy(xpath = "(//li[@data-fieldname='Date']/a/i)[1]")
	private static WebElement dateExpandBtn;

	@FindBy(xpath = "(//li[@data-fieldname='Date']/a/span)[2]")
	private static WebElement dateFieldBtn;

	@FindBy(xpath = "//*[@id='rd_customization_tree1']/ul/li[7]")
	private static WebElement cashAndBankExpandBtn;

	@FindBy(xpath = "//*[@id='rd_customization_tree1']/ul/li[7]/ul/li[1]")
	private static WebElement cashAndBankNameBtn;

	@FindBy(xpath = "//*[@id='rd_customization_tree1']/ul/li[1]")
	private static WebElement acc1ExpandBtn;

	@FindBy(xpath = "//*[@id='rd_customization_tree1']/ul/li[1]/ul/li[1]")
	private static WebElement acc1NameBtn;

	@FindBy(xpath = "//*[@id='rd_customization_tree1']/ul/li[1]/ul/li[2]")
	private static WebElement acc1CodeBtn;

	@FindBy(xpath = "//*[@id='rd_customization_tree1']/ul/li[2]")
	private static WebElement acc2ExpandBtn;

	@FindBy(xpath = "//*[@id='rd_customization_tree1']/ul/li[2]/ul/li[1]")
	private static WebElement acc2NameBtn;

	@FindBy(xpath = "//*[@id='rd_customization_tree1']/ul/li[2]/ul/li[2]")
	private static WebElement acc2CodeBtn;

	@FindBy(xpath = "//*[@id='rd_customization_tree0']/ul/li[1]/ul/li[19]")
	private static WebElement docNoFieldBtn;

	@FindBy(xpath = "//*[@id='rd_customization_tree0']/ul/li[1]/ul/li[14]")
	private static WebElement Rec_docNoFieldBtn;

	@FindBy(xpath = "//*[@id='rd_customization_tree0']/ul/li[1]/ul/li[19]")
	private static WebElement rec_FooterAmtBtn;

	@FindBy(xpath = "//*[@id='rd_customization_tree0']/ul/li[1]/ul/li[40]")
	private static WebElement rec_VoucherNameBtn;

	@FindBy(xpath = "//*[@id='rd_customization_tree0']/ul/li[1]/ul/li[29]")
	private static WebElement itemExpandBtn;

	@FindBy(xpath = "//*[@id='rd_customization_tree0']/ul/li[1]/ul/li[29]/ul/li[1]")
	private static WebElement itemNameBtn;

	@FindBy(xpath = "//*[@id='rd_customization_tree0']/ul/li[1]/ul/li[24]")
	private static WebElement docnumberBtn;

	@FindBy(xpath = "//*[@id='rd_customization_tree0']/ul/li[1]/ul/li[34]")
	private static WebElement itemExpandBtn1;

	@FindBy(xpath = "//*[@id='rd_customization_tree0']/ul/li[1]/ul/li[34]/ul/li[1]")
	private static WebElement itemNameBtn1;

	@FindBy(xpath = "//*[@id='rd_customization_tree0']/ul/li[1]/ul/li[34]/ul/li[2]")
	private static WebElement itemCodeBtn1;

	@FindBy(xpath = "//*[@id='rd_customization_tree0']/ul/li[1]/ul/li[50]")
	private static WebElement quantityBtn1;

	@FindBy(xpath = "//*[@id='rd_customization_tree0']/ul/li[1]/ul/li[49]")
	private static WebElement quantityBtn2;

	@FindBy(xpath = "//*[@id='rd_customization_tree0']/ul/li[1]/ul/li[52]")
	private static WebElement rateBtn2;

	@FindBy(xpath = "//*[@id='rd_customization_tree0']/ul/li[1]/ul/li[45]")
	private static WebElement quantityBtn;

	@FindBy(xpath = "//*[@id='rd_customization_tree0']/ul/li[1]/ul/li[48]")
	private static WebElement rateBtn;

	@FindBy(xpath = "//*[@id='rd_customization_tree0']/ul/li[1]/ul/li[26]")
	private static WebElement grossBtn;

	@FindBy(xpath = "//*[@id='rd_customization_tree0']/ul/li[2]/ul/li[5]")
	private static WebElement deptExpandBtn;

	@FindBy(xpath = "//*[@id='rd_customization_tree0']/ul/li[2]/ul/li[7]")
	private static WebElement deptExpandBtn1;

	@FindBy(xpath = "//*[@id='rd_customization_tree0']/ul/li[2]/ul/li[7]/ul/li[1]")
	private static WebElement deptNameBtn1;

	@FindBy(xpath = "//*[@id='rd_customization_tree0']/ul/li[2]/ul/li[2]")
	private static WebElement Rec_deptExpandBtn;

	@FindBy(xpath = "//*[@id='rd_customization_tree0']/ul/li[2]/ul/li[2]/ul/li[1]")
	private static WebElement rec_deptNameBtn;

	@FindBy(xpath = "//*[@id='rd_customization_tree0']/ul/li[2]/ul/li[5]/ul/li[1]")
	private static WebElement deptNameBtn;

	@FindBy(xpath = "//*[@id='rd_customization_tree0']/ul/li[2]/ul/li[24]")
	private static WebElement warehouseExpandBtn;

	@FindBy(xpath = "//*[@id='rd_customization_tree0']/ul/li[2]/ul/li[24]/ul/li[1]")
	private static WebElement warehouseNameBtn;

	@FindBy(xpath = "//*[@id='rd_customization_tree0']/ul/li[2]/ul/li[31]")
	private static WebElement warehouseExpandBtn1;

	@FindBy(xpath = "//*[@id='rd_customization_tree0']/ul/li[2]/ul/li[31]/ul/li[1]")
	private static WebElement warehouseNameBtn1;

	@FindBy(xpath = "//*[@id='rd_customization_tree0']/ul/li[2]/ul/li[35]")
	private static WebElement warehouseExpandBtn2;

	@FindBy(xpath = "//*[@id='rd_customization_tree0']/ul/li[2]/ul/li[35]/ul/li[1]")
	private static WebElement warehouseNameBtn2;

	@FindBy(xpath = "//*[@id='rd_customization_tree0']/ul/li[2]/ul/li[29]")
	private static WebElement warehouseExpandBtn3;

	@FindBy(xpath = "//*[@id='rd_customization_tree0']/ul/li[2]/ul/li[29]/ul/li[1]")
	private static WebElement warehouseNameBtn3;

	@FindBy(xpath = "//*[@id='rd_customization_tree0']/ul/li[2]/ul/li[8]/a/i")
	private static WebElement extraFieldDepartExpandBtn;

	@FindBy(xpath = "//*[@id='rd_customization_tree0']/ul/li[2]/ul/li[8]/ul/li[1]")
	private static WebElement extraFieldDepartNameBtn;

	@FindBy(xpath = "//*[@id='rd_customization_tree0']/ul/li[2]/ul/li[24]/ul/li[2]")
	private static WebElement warehouseCodeBtn;

	@FindBy(xpath = "//*[@id='rd_customization_tree310']/a/span")
	private static WebElement voucherAliasBtn;

	@FindBy(xpath = "//*[@id='rd_customization_tree1618']/a/span")
	private static WebElement voucherNameBtn;

	@FindBy(xpath = "//select[@id='id_rd_transet0_select_documentclass']")
	private static WebElement dataSetTabVouchersTab;

	@FindBy(xpath = "//select[@id='id_rd_transet0_select_documenttype']")
	private static WebElement dataSetTabVouchersDocType;

	@FindBy(xpath = "//*[@id='rd_customization_tree0']/ul/li[1]/a/i")
	private static WebElement transactionFieldsExpandBtn;

	@FindBy(xpath = "//*[@id='rd_customization_tree0']/ul/li[2]/a/i")
	private static WebElement extraFieldsExpandBtn;

	@FindBy(xpath = "//*[@id='rd_customization_tree0']/a/i")
	private static WebElement ReportFieldExpandBtn;

	public static boolean checkLogin()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		Thread.sleep(1999);

		LoginPage lp = new LoginPage(getDriver());

		String unamelt = "su";

		String pawslt = "su";

		lp.enterUserName(unamelt);

		Thread.sleep(2000);

		lp.enterPassword(pawslt);

		/* String compname="User Restrictions--COGS"; */
		String compname = "RD REPORTS";

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

		// checkRefershPopOnlogin();

		// checkPopUpWindow();

		Thread.sleep(2000);

		String userInfo = userNameDisplay.getText();

		System.out.println("User Info : " + userInfo);

		System.out.println("User Info Capture Text :" + userNameDisplay.getText());

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(companyLogo));

		companyLogo.click();

		if (userInfo.equalsIgnoreCase("SU")) {

			System.out.println("Test Pass :Logined to RD Reports Company");
			return true;

		} else {
			System.out.println("Test Fail :Logined to  RD Reports Company");
			return false;

		}
	}

	// resue Method
	public boolean checkNavigateToReportDesginer() throws InterruptedException {

		System.err.println("*****************************checkNavigateToReportDesginer*****");

		Thread.sleep(1999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(homeMenu));
		homeMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(utilities));
		utilities.click();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportDesignerMenu));
		reportDesignerMenu.click();

		Thread.sleep(2000);

		int rdReportHeaderListCount = rdReportHeaderList.size();
		ArrayList<String> rdReportHeaderListArray = new ArrayList<String>();
		for (int i = 0; i < rdReportHeaderListCount; i++) {
			String data = rdReportHeaderList.get(i).getText();
			rdReportHeaderListArray.add(data);
		}
		String actrdReportHeaderList = rdReportHeaderListArray.toString();
		String exprdReportHeaderList = "[Import From Xml, Export to _XML, Reset, Delete, Back, Next, Finish, Cancel]";

		System.out.println(" ACt rdReportHeaderListListList   :" + actrdReportHeaderList);
		System.out.println("  Exp rdReportHeaderListListList  :" + exprdReportHeaderList);

		int rdReportHeaderTABListCount = rdReportHeaderTABList.size();
		ArrayList<String> rdReportHeaderTABListArray = new ArrayList<String>();
		for (int i = 0; i < rdReportHeaderTABListCount; i++) {
			String data = rdReportHeaderTABList.get(i).getText();
			rdReportHeaderTABListArray.add(data);
		}
		String actrdReportHeaderTABList = rdReportHeaderTABListArray.toString();
		String exprdReportHeaderTABList = "[  Definition,   Data Set,   Customization,   Header/Footer,   Preview]";

		System.out.println(" ACt rdReportHeaderTABListList   :" + actrdReportHeaderTABList);
		System.out.println("  Exp rdReportHeaderTABListList  :" + exprdReportHeaderTABList);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportNameDropdown));
		if (actrdReportHeaderList.equalsIgnoreCase(exprdReportHeaderList)) {
			System.err.println("****************** Navigate to Report Desginer Page ");
			return true;
		} else {

			System.err.println("******************** Not Navigate to Report Desginer Page ");
			checkServerErrorMessage();
			return false;
		}

	}

	@FindBy(xpath = "//*[@id='myNavbar']/ul/li")
	private static List<WebElement> rdReportHeaderList;

	@FindBy(xpath = "//*[@id='id_rd_container']/div/div[1]/div/div//div/span[2]")
	private static List<WebElement> rdReportHeaderTABList;

	public static boolean checkSavingReportDesignerOfAllTransactionsOfDocumentClass()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {

		Thread.sleep(2999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(homeMenu));
		homeMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(utilities));
		utilities.click();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportDesignerMenu));
		reportDesignerMenu.click();

		Thread.sleep(2999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportNameDropdown));
		reportNameDropdown.sendKeys("All transactions of document class of Purchase Type");
		reportNameDropdown.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportTypeDropdown));
		Select rtd = new Select(reportTypeDropdown);
		rtd.selectByVisibleText("Details");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(inventoryExpandBtn));
		inventoryExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportsBtn));
		reportsBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dataSetTab));
		dataSetTab.click();

		Thread.sleep(2000);

		int transactionSetListCount = transactionSetList.size();

		ArrayList<String> transactionSetListArray = new ArrayList<>();

		for (int i = 0; i <= transactionSetListCount; i++) {
			String data = transactionSetList.get(i).getText();

			transactionSetListArray.add(data);

			if (data.equalsIgnoreCase("All transactions of document class")) {
				transactionSetList.get(i).click();

				break;
			}
		}

		String acttransactionSetList = transactionSetListArray.toString();
		String exptransactionSetList = "[, Accounting transactions, Accounting transactions of an account, Accounting transactions of accounting tag, Accounting transactions of inventory tag, Accounting transactions of a tag, Accounting transactions of selected account, All accounting transactions, All accounts, All accounts by tag, All fixed assets, All products, All products by tag, All transactions of document class]";

		System.out.println(" ACt transactionSetList : " + acttransactionSetList);
		System.out.println(" Exp transactionSetList : " + exptransactionSetList);

		Select voucher = new Select(dataSetTabVouchersTab);
		voucher.selectByVisibleText("Purchases Vouchers");

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTab));
		customizationTab.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionExpandBtn));
		transactionExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionFieldsExpandBtn));
		transactionFieldsExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dateExpandBtn));
		dateExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dateFieldBtn));
		getAction().doubleClick(dateFieldBtn).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dateExpandBtn));
		dateExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(docNoFieldBtn));
		getAction().doubleClick(docNoFieldBtn).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemExpandBtn));
		itemExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemNameBtn));
		getAction().doubleClick(itemNameBtn).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemExpandBtn));
		itemExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(quantityBtn));
		getAction().doubleClick(quantityBtn).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rateBtn));
		getAction().doubleClick(rateBtn).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(grossBtn));
		getAction().doubleClick(grossBtn).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionFieldsExpandBtn));
		transactionFieldsExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(extraFieldsExpandBtn));
		extraFieldsExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(deptExpandBtn));
		deptExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(deptNameBtn));
		getAction().doubleClick(deptNameBtn).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(deptExpandBtn));
		deptExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouseExpandBtn));
		warehouseExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouseNameBtn));
		getAction().doubleClick(warehouseNameBtn).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouseExpandBtn));
		warehouseExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(extraFieldsExpandBtn));
		extraFieldsExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionFieldsExpandBtn));
		transactionFieldsExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(finishBtn));
		finishBtn.click();

		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : " + actMessage + " Value Expected : " + expMessage);

		if (actMessage.equalsIgnoreCase(expMessage) && acttransactionSetList.equalsIgnoreCase(exptransactionSetList)) {

			return true;
		} else {
			return false;
		}
	}

	@FindBy(xpath = "//*[@id='id_rd_definition_reportname_table_data']/tbody/tr/td")
	private static List<WebElement> reportNameList;

	@FindBy(xpath = "//*[@id='id_rd_customization_table']/thead/tr/th")
	private static List<WebElement> customizeTabTableHeaderLsist;

	@FindBy(xpath = "//select[@id='id_rd_transet0_documentstatus']")
	private static WebElement rdDataSetDocumentStatusDrpDwn;

	@FindBy(xpath = "//select[@id='id_rd_transet0_verificationstatus']")
	private static WebElement rdDataSetVerificationStatusDrpDwn;

	@FindBy(xpath = "//select[@id='id_rd_transet0_authorizationstatus']")
	private static WebElement rdDataSetAuthorisationStatusDrpDwn;

	@FindBy(xpath = "//span[@id='idFilterCustomizeIcon']")
	private static WebElement rdDataSetDefaultFilterCusBtn;

	@FindBy(xpath = "(//*[@id='FilterFields_22_0']/li[30]/a/i)[1]")
	private static WebElement rdDataSetFilterdepExpandbtn;

	@FindBy(xpath = "(//input[@id='5042'])[1]")
	private static WebElement rdDataSetFilterdepNamebtn;

	@FindBy(xpath = "(//*[@id='FilterFieldCust_22_0']/div/div[3]/button[1])[1]")
	private static WebElement rdDataSetOkBtn;

	public static boolean checkUpdatingTheSavedReportInreportDesinger()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportNameDropdown));
		reportNameDropdown.click();
		reportNameDropdown.sendKeys(Keys.SPACE);

		int reportNameListcount = reportNameList.size();

		for (int i = 0; i < reportNameListcount; i++) {
			String data = reportNameList.get(i).getText();

			if (data.equalsIgnoreCase("All transactions of document class of Purchase Type")) {
				reportNameList.get(i).click();
				break;
			}
		}

		reportNameDropdown.sendKeys(Keys.TAB);

		Thread.sleep(3000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTab));
		customizationTab.click();

		int customizeTabTableHeaderLsistCount = customizeTabTableHeaderLsist.size();
		System.err.println(customizeTabTableHeaderLsistCount);
		for (int i = 1; i <= customizeTabTableHeaderLsistCount; i++) {
			String data = customizeTabTableHeaderLsist.get(i).getText();

			if (data.equalsIgnoreCase("Warehouse.Name")) {
				customizeTabTableHeaderLsist.get(i).click();

				System.err.println(i);
				Thread.sleep(1000);

				WebElement deletebtn = getDriver()
						.findElement(By.xpath("//*[@id='rd_customization_table_column_7']/div[2]/span"));

				deletebtn.click();

				break;
			}
		}

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionExpandBtn));
		transactionExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(extraFieldsExpandBtn));
		extraFieldsExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouseExpandBtn));
		warehouseExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouseNameBtn));
		getAction().doubleClick(warehouseNameBtn).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouseCodeBtn));
		getAction().doubleClick(warehouseCodeBtn).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouseExpandBtn));
		warehouseExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(finishBtn));
		finishBtn.click();

		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : " + actMessage + " Value Expected : " + expMessage);

		if (actMessage.equalsIgnoreCase(expMessage)) {

			return true;
		} else {
			return false;
		}
	}

	@FindBy(xpath = "//body/section[@id='mainDiv']/div[@id='id_focus8_wrapper_default']/div[1]/section[1]/div[1]/div[1]/div[1]/nav[1]/div[1]/div[2]/ul[1]/li[2]/div[1]/div[1]")
	private static WebElement reportDesginExportToXMLBtn;

	@FindBy(xpath = "//*[@id='tblRDRender']/thead/tr[1]/th")
	private static List<WebElement> previewTabHeaderList;

	public boolean checkHeaderAndFooterTabInReportDesiging()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(homeMenu));
		homeMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(utilities));
		utilities.click();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportDesignerMenu));
		reportDesignerMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportNameDropdown));
		reportNameDropdown.click();
		reportNameDropdown.sendKeys(Keys.SPACE);

		int reportNameListcount = reportNameList.size();

		for (int i = 0; i < reportNameListcount; i++) {
			String data = reportNameList.get(i).getText();

			if (data.equalsIgnoreCase("All transactions of document class of Purchase Type")) {
				reportNameList.get(i).click();
				break;
			}
		}

		reportNameDropdown.sendKeys(Keys.TAB);

		Thread.sleep(3000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(headerFooterTab));
		headerFooterTab.click();

		Thread.sleep(3000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(RDHeaderTab_WarehouseExpandBtn));
		RDHeaderTab_WarehouseExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(RDHeaderTab_WarehouseNamebtn));
		RDHeaderTab_WarehouseNamebtn.click();

		return true;

	}

	@FindBy(xpath = "//*[@id='InvoiceDesignFields']/ul/li[1]/a/span/i")
	private static WebElement RDHeaderTab_TransExpandBtn;

	@FindBy(xpath = "//*[@id='InvoiceDesignFields']/ul/li[1]/ul/li[4]/a/span/i")
	private static WebElement RDHeaderTab_WarehouseExpandBtn;

	@FindBy(xpath = "//*[@id='InvoiceDesignFields']/ul/li[1]/ul/li[4]/ul/li[1]/a/span")
	private static WebElement RDHeaderTab_WarehouseNamebtn;

	public boolean checkPreviewTabInReportDesiging()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(homeMenu));
		homeMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(utilities));
		utilities.click();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportDesignerMenu));
		reportDesignerMenu.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportNameDropdown));
		reportNameDropdown.click();
		reportNameDropdown.sendKeys(Keys.SPACE);

		int reportNameListcount = reportNameList.size();

		for (int i = 0; i < reportNameListcount; i++) {
			String data = reportNameList.get(i).getText();

			if (data.equalsIgnoreCase("All transactions of document class of Purchase Type")) {
				reportNameList.get(i).click();
				break;
			}
		}

		reportNameDropdown.sendKeys(Keys.TAB);

		Thread.sleep(3000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(previewTab));
		previewTab.click();

		Thread.sleep(3000);

		int previewTabHeaderListCount = previewTabHeaderList.size();

		ArrayList<String> previewTabHeaderListArray = new ArrayList<>();

		for (int i = 0; i < previewTabHeaderListCount; i++) {

			String data = previewTabHeaderList.get(i).getText();
			previewTabHeaderListArray.add(data);

		}
		String actpreviewTabHeaderList = previewTabHeaderListArray.toString();
		String exppreviewTabHeaderList = "[, Date, Document No., Quantity, Rate, Gross, Department.Name, Warehouse.Name, Warehouse.Code]";

		System.out.println(" ACt List   :" + actpreviewTabHeaderList);
		System.out.println("  Exp List  :" + exppreviewTabHeaderList);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(finishBtn));
		finishBtn.click();

		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : " + actMessage + " Value Expected : " + expMessage);

		if (actMessage.equalsIgnoreCase(expMessage)
				&& actpreviewTabHeaderList.equalsIgnoreCase(exppreviewTabHeaderList)) {

			System.out.println(" Test Pass: Preview Tab Displays as Expected ");
			return true;
		} else {
			System.out.println(" Test Fail: Preview Tab Displays as Expected ");
			return false;
		}
	}

	public boolean checkDeleteOptionInReportDesigner()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		checkNavigateToReportDesginer();

		Thread.sleep(2999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportNameDropdown));
		reportNameDropdown.sendKeys("RD Report With Delete Option");
		Thread.sleep(2000);
		reportNameDropdown.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportTypeDropdown));
		Select rtd = new Select(reportTypeDropdown);
		rtd.selectByVisibleText("Details");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(inventoryExpandBtn));
		inventoryExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportsBtn));
		reportsBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dataSetTab));
		dataSetTab.click();

		Thread.sleep(2000);

		int transactionSetListCount = transactionSetList.size();

		for (int i = 0; i <= transactionSetListCount; i++) {
			String data = transactionSetList.get(i).getText();

			if (data.equalsIgnoreCase("All transactions of document Type")) {
				transactionSetList.get(i).click();

				break;
			}
		}

		dataSetTabVouchersDocType.click();

		dataSetTabVouchersDocType.sendKeys(Keys.ARROW_DOWN);
		dataSetTabVouchersDocType.sendKeys(Keys.ARROW_DOWN);
		dataSetTabVouchersDocType.sendKeys(Keys.ARROW_DOWN);
		dataSetTabVouchersDocType.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		Select s1 = new Select(rdDataSetDocumentStatusDrpDwn);
		s1.selectByValue("1");

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTab));
		customizationTab.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionExpandBtn));
		transactionExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionFieldsExpandBtn));
		transactionFieldsExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dateExpandBtn));
		dateExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dateFieldBtn));
		getAction().doubleClick(dateFieldBtn).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dateExpandBtn));
		dateExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(Rec_docNoFieldBtn));
		getAction().doubleClick(Rec_docNoFieldBtn).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(cashAndBankExpandBtn));
		cashAndBankExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(cashAndBankNameBtn));
		getAction().doubleClick(cashAndBankNameBtn).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rec_FooterAmtBtn));
		getAction().doubleClick(rec_FooterAmtBtn).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rec_VoucherNameBtn));
		getAction().doubleClick(rec_VoucherNameBtn).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionFieldsExpandBtn));
		transactionFieldsExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(extraFieldsExpandBtn));
		extraFieldsExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(Rec_deptExpandBtn));
		Rec_deptExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rec_deptNameBtn));
		getAction().doubleClick(rec_deptNameBtn).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(Rec_deptExpandBtn));
		Rec_deptExpandBtn.click();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(finishBtn));
		finishBtn.click();

		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : " + actMessage + " Value Expected : " + expMessage);

		Thread.sleep(2999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportNameDropdown));
		reportNameDropdown.sendKeys("RD Report With Delete Option");
		Thread.sleep(1999);
		reportNameDropdown.sendKeys(Keys.TAB);

		Thread.sleep(1999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(deleteBtn));
		deleteBtn.click();

		getWaitForAlert();

		String actAlert = getAlert().getText();
		String expAlert = "Are you sure to delete the report?";

		Thread.sleep(1000);

		System.out.println(" actAlert : " + actAlert + " Value  " + expAlert);

		getAlert().accept();

		String ExpMessage = "Data deleted successfully.";
		String actMesage = checkValidationMessage(ExpMessage);

		if (actAlert.equalsIgnoreCase(expAlert) && actMesage.equalsIgnoreCase(ExpMessage)) {
			return true;
		} else {

			return false;
		}

	}

	@FindBy(xpath = "//input[@id='selectAllMasters_']")
	private static WebElement sl_SelectAllItemsChkBox;

	@FindBy(xpath = "//input[@id='txtSearchMenu_MainLayout']")
	private static WebElement searchTxt;

	@FindBy(xpath = "//select[@id='DateOptions_']")

	private static WebElement sl_DateOptionDropdown;

	@FindBy(xpath = "//i[contains(@class,'icon-font6 icon-ok')]")
	private static WebElement sl_OkBtn;

	@FindBy(xpath = "//*[@id='REPORTRENDERNEWControls']/ul/li/span[9]")
	private static WebElement sl_AnalysisBtn;

	@FindBy(xpath = "//input[@id='MasterGroup__101']")
	private static WebElement reportsAccountTxt;

	@FindBy(xpath = "//input[@id='MasterSingle__101']")
	private static WebElement reportDepartmentTxt;

	@FindBy(xpath = "//input[@id='MasterSingle__5042']")
	private static WebElement billwiseReportDepTxt;

	@FindBy(xpath = "//i[contains(@class,'icon-font6 icon-close')]")
	private static WebElement sl_CloseBtn;

	@FindBy(xpath = "//*[@id='trRender_0']/td[1]")
	private static WebElement sl_1stRow1stCol;

	@FindBy(xpath = "//*[@id='trRender_0']/td[2]")
	private static WebElement sl_1stRow2ndCol;

	@FindBy(xpath = "//*[@id='trRender_0']/td[3]")
	private static WebElement sl_1stRow3rdCol;

	@FindBy(xpath = "//*[@id='trRender_0']/td[4]")
	private static WebElement sl_1stRow4thCol;

	@FindBy(xpath = "//*[@id='trRender_0']/td[5]")
	private static WebElement sl_1stRow5thCol;

	@FindBy(xpath = "//div[@id='dvReportDetails']/div/table/tbody/tr[1]/td")
	private static List<WebElement> report1stRowList;

	@FindBy(xpath = "//div[@id='dvReportDetails']/div/table/tbody/tr[2]/td")
	private static List<WebElement> report2ndRowList;

	@FindBy(xpath = "//div[@id='dvReportDetails']/div/table/tbody/tr[3]/td")
	private static List<WebElement> report3rdRowList;

	@FindBy(xpath = "//div[@id='dvReportDetails']/div/table/tbody/tr[4]/td")
	private static List<WebElement> report4thRowList;

	@FindBy(xpath = "//div[@id='dvReportDetails']/div/table/tbody/tr[5]/td")
	private static List<WebElement> report5thRowList;

	@FindBy(xpath = "//div[@id='dvReportDetails']/div/table/tbody/tr[6]/td")
	private static List<WebElement> report6thRowList;

	@FindBy(xpath = "//div[@id='dvReportDetails']/div/table/tbody/tr[7]/td")
	private static List<WebElement> report7thRowList;

	@FindBy(xpath = "//div[@id='dvReportDetails']/div/table/tbody/tr[8]/td")
	private static List<WebElement> report8thRowList;

	@FindBy(xpath = "//div[@id='dvReportDetails']/div/table/tbody/tr[9]/td")
	private static List<WebElement> report9thRowList;

	public boolean checkReportAllTransactionsOfDocumentClassOfPurchaseType() throws InterruptedException {
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
		searchTxt.click();
		searchTxt.sendKeys("All transactions of document class of Purchase Type");
		Thread.sleep(1000);
		searchTxt.sendKeys(Keys.ENTER);

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_DateOptionDropdown));
		sl_DateOptionDropdown.click();
		Select s = new Select(sl_DateOptionDropdown);
		s.selectByValue("1");

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_OkBtn));
		sl_OkBtn.click();

		Thread.sleep(1500);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_1stRow1stCol));

		int reportsRow1ListCount = report1stRowList.size();
		ArrayList<String> reportsRow1ListArray = new ArrayList<String>();
		for (int i = 2; i < reportsRow1ListCount; i++) {
			String data = report1stRowList.get(i).getText();

			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			String expadjustBills = df.format(date);

			reportsRow1ListArray.add(data);
		}
		String actRow1List = reportsRow1ListArray.toString();
		String expRow1List = "[NDT52:1, 1.00, 5.00, 5.00, DUBAI, HYDERABAD, HYDERABAD]";

		int report2ndRowListCount = report2ndRowList.size();
		ArrayList<String> report2ndRowListArray = new ArrayList<String>();
		for (int i = 2; i < report2ndRowListCount; i++) {
			String data = report2ndRowList.get(i).getText();
			report2ndRowListArray.add(data);
		}
		String actRow2List = report2ndRowListArray.toString();
		String expRow2List = "[NDT52:2, 1.00, 5.00, 5.00, DUBAI, HYDERABAD, HYDERABAD]";

		int report3rdRowListCount = report3rdRowList.size();
		ArrayList<String> report3rdRowListArray = new ArrayList<String>();
		for (int i = 2; i < report3rdRowListCount; i++) {
			String data = report3rdRowList.get(i).getText();
			report3rdRowListArray.add(data);
		}
		String actRow3List = report3rdRowListArray.toString();
		String expRow3List = "[, 2.00, 10.00, 10.00, , , ]";

		System.out.println("actRow1List  : " + actRow1List);
		System.out.println("expRow1List  : " + expRow1List);
		System.out.println("*********************************************************************");

		System.out.println("actRow2List  : " + actRow2List);
		System.out.println("expRow2List  : " + expRow2List);
		System.out.println("*********************************************************************");

		System.out.println("actRow3List  : " + actRow3List);
		System.out.println("expRow3List  : " + expRow3List);
		System.out.println("*********************************************************************");

		if (actRow1List.equalsIgnoreCase(expRow1List) && actRow2List.equalsIgnoreCase(expRow2List)
				&& actRow3List.equalsIgnoreCase(expRow3List)) {

			System.out.println(" Test Pass: Values Dsiplayed as Expected ");
			return true;
		} else {

			System.out.println(" Test FAIL: Values Dsiplayed as Expected ");
			return false;

		}

	}

	public boolean checkSavingAllTransactionsOfDocumentTypeOfRecepitsTypeToCubes()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {

		Thread.sleep(2999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(homeMenu));
		homeMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(utilities));
		utilities.click();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportDesignerMenu));
		reportDesignerMenu.click();

		Thread.sleep(2999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportNameDropdown));
		reportNameDropdown.sendKeys("All transactions of document Type of Recepits VAT Type");
		reportNameDropdown.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportTypeDropdown));
		Select rtd = new Select(reportTypeDropdown);
		rtd.selectByVisibleText("Details");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(inventoryExpandBtn));
		inventoryExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportsBtn));
		reportsBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dataSetTab));
		dataSetTab.click();

		Thread.sleep(2000);

		int transactionSetListCount = transactionSetList.size();

		for (int i = 0; i <= transactionSetListCount; i++) {
			String data = transactionSetList.get(i).getText();

			if (data.equalsIgnoreCase("All transactions of document Type")) {
				transactionSetList.get(i).click();

				break;
			}
		}

		dataSetTabVouchersDocType.click();

		dataSetTabVouchersDocType.sendKeys(Keys.ARROW_DOWN);
		dataSetTabVouchersDocType.sendKeys(Keys.ARROW_DOWN);
		dataSetTabVouchersDocType.sendKeys(Keys.ARROW_DOWN);
		dataSetTabVouchersDocType.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		Select s1 = new Select(rdDataSetDocumentStatusDrpDwn);
		s1.selectByValue("1");

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTab));
		customizationTab.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionExpandBtn));
		transactionExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionFieldsExpandBtn));
		transactionFieldsExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dateExpandBtn));
		dateExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dateFieldBtn));
		getAction().doubleClick(dateFieldBtn).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dateExpandBtn));
		dateExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(Rec_docNoFieldBtn));
		getAction().doubleClick(Rec_docNoFieldBtn).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(cashAndBankExpandBtn));
		cashAndBankExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(cashAndBankNameBtn));
		getAction().doubleClick(cashAndBankNameBtn).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rec_FooterAmtBtn));
		getAction().doubleClick(rec_FooterAmtBtn).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rec_VoucherNameBtn));
		getAction().doubleClick(rec_VoucherNameBtn).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionFieldsExpandBtn));
		transactionFieldsExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(extraFieldsExpandBtn));
		extraFieldsExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(Rec_deptExpandBtn));
		Rec_deptExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rec_deptNameBtn));
		getAction().doubleClick(rec_deptNameBtn).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(Rec_deptExpandBtn));
		Rec_deptExpandBtn.click();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(finishBtn));
		finishBtn.click();

		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : " + actMessage + " Value Expected : " + expMessage);

		if (actMessage.equalsIgnoreCase(expMessage)) {

			System.out.println(" Test PasS: Report desgining is saved with Document Type ");
			return true;
		} else {
			System.out.println(" Test FAIL: Report desgining is saved with Document Type ");
			return false;
		}

	}

	public boolean checkReportAllTransactionsOfDocumentTypeOfRecepitsVATType() throws InterruptedException {

		getDriver().navigate().refresh();
		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
		searchTxt.click();
		searchTxt.sendKeys("All transactions of document Type of Recepits VAT Type");
		Thread.sleep(1000);
		searchTxt.sendKeys(Keys.ENTER);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_DateOptionDropdown));
		sl_DateOptionDropdown.click();
		Select s = new Select(sl_DateOptionDropdown);
		s.selectByValue("1");

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_OkBtn));
		sl_OkBtn.click();

		Thread.sleep(1500);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_1stRow1stCol));

		int reportsRow1ListCount = reportCol3List.size();
		ArrayList<String> reportsRow1ListArray = new ArrayList<String>();
		for (int i = 2; i < reportsRow1ListCount; i++) {
			String data = reportCol3List.get(i).getText();

			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			String expadjustBills = df.format(date);

			reportsRow1ListArray.add(data);
		}
		String actRow1List = reportsRow1ListArray.toString();
		String expRow1List = "[5, 5, 5, 5, 5, 5, ]";

		int report2ndRowListCount = reportCol4List.size();
		ArrayList<String> report2ndRowListArray = new ArrayList<String>();
		for (int i = 2; i < report2ndRowListCount; i++) {
			String data = reportCol4List.get(i).getText();
			report2ndRowListArray.add(data);
		}
		String actRow2List = report2ndRowListArray.toString();
		String expRow2List = "[Bank, Bank, VAT ADVANCE SALE, VAT ADVANCE SALE, VAT ADVANCE SALE, VAT ADVANCE SALE, ]";

		int report3rdRowListCount = reportCol5List.size();
		ArrayList<String> report3rdRowListArray = new ArrayList<String>();
		for (int i = 2; i < report3rdRowListCount; i++) {
			String data = reportCol5List.get(i).getText();
			report3rdRowListArray.add(data);
		}
		String actRow3List = report3rdRowListArray.toString();
		String expRow3List = "[, , , , , , ]";

		int report4thRowListCount = reportCol6List.size();
		ArrayList<String> report4thRowListArray = new ArrayList<String>();
		for (int i = 2; i < report4thRowListCount; i++) {
			String data = reportCol6List.get(i).getText();
			report4thRowListArray.add(data);
		}
		String actRow4List = report4thRowListArray.toString();
		String expRow4List = "[Receipts VAT, Receipts VAT, Receipts VAT, Receipts VAT, Receipts VAT, Receipts VAT, ]";

		int report5thRowListCount = reportCol7List.size();
		ArrayList<String> report5thRowListArray = new ArrayList<String>();
		for (int i = 1; i < report5thRowListCount; i++) {
			String data = reportCol7List.get(i).getText();
			report5thRowListArray.add(data);
		}
		String actRow5List = report5thRowListArray.toString();
		String expRow5List = "[DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, ]";

		System.out.println("actRow1List  : " + actRow1List);
		System.out.println("expRow1List  : " + expRow1List);
		System.out.println("*********************************************************************");

		System.out.println("actRow2List  : " + actRow2List);
		System.out.println("expRow2List  : " + expRow2List);
		System.out.println("*********************************************************************");

		System.out.println("actRow3List  : " + actRow3List);
		System.out.println("expRow3List  : " + expRow3List);
		System.out.println("*********************************************************************");

		System.out.println("actRow4List  : " + actRow4List);
		System.out.println("expRow4List  : " + expRow4List);
		System.out.println("*********************************************************************");

		System.out.println("actRow5List  : " + actRow5List);
		System.out.println("expRow5List  : " + expRow5List);
		System.out.println("*********************************************************************");

		if (actRow1List.equalsIgnoreCase(expRow1List) && actRow2List.equalsIgnoreCase(expRow2List)
				&& actRow3List.equalsIgnoreCase(expRow3List) && actRow4List.equalsIgnoreCase(expRow4List)
				&& actRow5List.equalsIgnoreCase(expRow5List)) {
			System.out.println(" Test Pass: Values Dsiplayed as Expected ");
			return true;
		} else {
			System.out.println(" Test FAIL: Values Dsiplayed as Expected ");
			return false;
		}
	}

	public boolean checkSavingAccountingTransactionsOfAnAccountDetails()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {

		Thread.sleep(2999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(homeMenu));
		homeMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(utilities));
		utilities.click();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportDesignerMenu));
		reportDesignerMenu.click();

		Thread.sleep(2999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportNameDropdown));
		reportNameDropdown.sendKeys("Accounting Transactions of an Account-Details");
		reportNameDropdown.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportTypeDropdown));
		Select rtd = new Select(reportTypeDropdown);
		rtd.selectByVisibleText("Details");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(inventoryExpandBtn));
		inventoryExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportsBtn));
		reportsBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dataSetTab));
		dataSetTab.click();

		Thread.sleep(2000);

		int transactionSetListCount = transactionSetList.size();

		for (int i = 0; i <= transactionSetListCount; i++) {
			String data = transactionSetList.get(i).getText();

			if (data.equalsIgnoreCase("Accounting transactions of an account")) {
				transactionSetList.get(i).click();

				break;
			}
		}

		Thread.sleep(2000);

		Select s1 = new Select(rdDataSetVerificationStatusDrpDwn);
		s1.selectByValue("3");

		Thread.sleep(2000);

		Select s2 = new Select(rdDataSetAuthorisationStatusDrpDwn);
		s2.selectByValue("2");

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTab));
		customizationTab.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionExpandBtn));
		transactionExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionFieldsExpandBtn));
		transactionFieldsExpandBtn.click();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(acc1ExpandBtn));
		acc1ExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(acc1NameBtn));
		getAction().doubleClick(acc1NameBtn).build().perform();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(acc2ExpandBtn));
		acc2ExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(acc2NameBtn));
		getAction().doubleClick(acc2NameBtn).build().perform();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemExpandBtn1));
		itemExpandBtn1.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemNameBtn1));
		getAction().doubleClick(itemNameBtn1).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionFieldsExpandBtn));
		transactionFieldsExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(extraFieldsExpandBtn));
		extraFieldsExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouseExpandBtn1));
		warehouseExpandBtn1.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouseNameBtn1));
		getAction().doubleClick(warehouseNameBtn1).build().perform();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(finishBtn));
		finishBtn.click();

		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : " + actMessage + " Value Expected : " + expMessage);

		if (actMessage.equalsIgnoreCase(expMessage)) {

			System.out.println(" Test PasS: Report desgining is saved with Document Type ");
			return true;
		} else {
			System.out.println(" Test FAIL: Report desgining is saved with Document Type ");
			return false;
		}

	}

	public boolean checkReportAccountingTransactionsOfAnAccountDetails() throws InterruptedException {
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
		searchTxt.click();
		searchTxt.sendKeys("Accounting Transactions of an Account-Details");
		Thread.sleep(1000);
		searchTxt.sendKeys(Keys.ENTER);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_DateOptionDropdown));
		sl_DateOptionDropdown.click();
		Select s = new Select(sl_DateOptionDropdown);
		s.selectByValue("1");

		Thread.sleep(2000);

		reportsAccountTxt.click();
		reportsAccountTxt.sendKeys("Purchase");
		Thread.sleep(1000);
		reportsAccountTxt.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_OkBtn));
		sl_OkBtn.click();

		Thread.sleep(2000);
		boolean novalidationConfirmationMessage = validationConfirmationMessage.getText().isEmpty();

		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";

		Thread.sleep(1500);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_1stRow1stCol));

		int reportsRow1ListCount = report1stRowList.size();
		ArrayList<String> reportsRow1ListArray = new ArrayList<String>();
		for (int i = 1; i < reportsRow1ListCount; i++) {
			String data = report1stRowList.get(i).getText();

			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			String expadjustBills = df.format(date);

			reportsRow1ListArray.add(data);
		}
		String actRow1List = reportsRow1ListArray.toString();
		String expRow1List = "[HDFC, Purchase, HYDERABAD]";

		System.err.println("actRow1List  : " + actRow1List);
		System.err.println("expRow1List  : " + expRow1List);
		System.out.println("*********************************************************************");

		if (actRow1List.equalsIgnoreCase(expRow1List)
				&& actvalidationConfirmationMessage.equalsIgnoreCase(expvalidationConfirmationMessage)) {
			System.out.println(" Test Pass: Values Dsiplayed as Expected ");
			return true;
		} else {
			System.out.println(" Test FAIL: Values Dsiplayed as Expected ");
			return false;
		}
	}

	public boolean checkSavingAccountingTransactionsOfAccountingTagDetails()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {

		Thread.sleep(2999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(homeMenu));
		homeMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(utilities));
		utilities.click();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportDesignerMenu));
		reportDesignerMenu.click();

		Thread.sleep(2999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportNameDropdown));
		reportNameDropdown.sendKeys("Accounting Transactions of Accounting Tag-Details");
		reportNameDropdown.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportTypeDropdown));
		Select rtd = new Select(reportTypeDropdown);
		rtd.selectByVisibleText("Details");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(inventoryExpandBtn));
		inventoryExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportsBtn));
		reportsBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dataSetTab));
		dataSetTab.click();

		Thread.sleep(2000);

		int transactionSetListCount = transactionSetList.size();

		for (int i = 0; i <= transactionSetListCount; i++) {
			String data = transactionSetList.get(i).getText();

			if (data.equalsIgnoreCase("Accounting transactions of accounting tag")) {
				transactionSetList.get(i).click();

				break;
			}
		}

		Thread.sleep(2000);

		getAction().moveToElement(rdDataSetDefaultFilterCusBtn).build().perform();
		rdDataSetDefaultFilterCusBtn.click();

		getAction().moveToElement(rdDataSetDefaultFilterCusBtn).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdDataSetFilterdepExpandbtn));
		rdDataSetFilterdepExpandbtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdDataSetFilterdepNamebtn));
		rdDataSetFilterdepNamebtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdDataSetOkBtn));
		rdDataSetOkBtn.click();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTab));
		customizationTab.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionExpandBtn));
		transactionExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionFieldsExpandBtn));
		transactionFieldsExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dateExpandBtn));
		dateExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dateFieldBtn));
		getAction().doubleClick(dateFieldBtn).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dateExpandBtn));
		dateExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(docNoFieldBtn));
		getAction().doubleClick(docNoFieldBtn).build().perform();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemExpandBtn1));
		itemExpandBtn1.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemNameBtn1));
		getAction().doubleClick(itemNameBtn1).build().perform();

		Thread.sleep(2000);

		getAction().moveToElement(acc1ExpandBtn).build().perform();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(acc1ExpandBtn));
		acc1ExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(acc1NameBtn));
		getAction().doubleClick(acc1NameBtn).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(acc2ExpandBtn));
		acc2ExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(acc2NameBtn));
		getAction().doubleClick(acc2NameBtn).build().perform();

		quantityBtn1.click();
		getAction().doubleClick(quantityBtn1).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionFieldsExpandBtn));
		transactionFieldsExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(extraFieldsExpandBtn));
		extraFieldsExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouseExpandBtn1));
		warehouseExpandBtn1.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouseNameBtn1));
		getAction().doubleClick(warehouseNameBtn1).build().perform();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(finishBtn));
		finishBtn.click();

		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : " + actMessage + " Value Expected : " + expMessage);

		if (actMessage.equalsIgnoreCase(expMessage)) {

			System.out.println(" Test PasS: Report desgining is saved with Document Type ");
			return true;
		} else {
			System.out.println(" Test FAIL: Report desgining is saved with Document Type ");
			return false;
		}

	}

	public boolean checkReportAccountingTransactionsOfAnAccountingTagDetails() throws InterruptedException {
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
		searchTxt.click();
		searchTxt.sendKeys("Accounting Transactions of Accounting Tag-Details");
		Thread.sleep(1000);
		searchTxt.sendKeys(Keys.ENTER);

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_DateOptionDropdown));
		/* sl_DateOptionDropdown.click(); */

		Select s = new Select(sl_DateOptionDropdown);
		s.selectByValue("1");

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportDepartmentTxt));
		reportDepartmentTxt.click();
		reportDepartmentTxt.sendKeys("Duabi");
		Thread.sleep(1000);
		reportDepartmentTxt.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_OkBtn));
		sl_OkBtn.click();

		Thread.sleep(1500);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_1stRow1stCol));

		int reportsRow1ListCount = reportCol3List.size();
		ArrayList<String> reportsRow1ListArray = new ArrayList<String>();
		for (int i = 1; i < reportsRow1ListCount; i++) {
			String data = reportCol3List.get(i).getText();

			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			String expadjustBills = df.format(date);

			reportsRow1ListArray.add(data);
		}
		String actRow1List = reportsRow1ListArray.toString();
		String expRow1List = "[SU, SU, SU, SU, SU, SU, SU, SU, SU, SU, SU, SU, SU, SU, SU, SU, SU, SU]";

		int report2ndRowListCount = reportCol4List.size();
		ArrayList<String> report2ndRowListArray = new ArrayList<String>();
		for (int i = 1; i < report2ndRowListCount; i++) {
			String data = reportCol4List.get(i).getText();
			report2ndRowListArray.add(data);
		}
		String actRow2List = report2ndRowListArray.toString();
		String expRow2List = "[Customer A, STD RATE COGS ACC INV, Bank, Bank, Customer A, SR COGS POSTING ACC, Bank, Bank, Vendor New Reference, , VAT INPUT, Vendor New Reference, Bank, , Vendor B, VAT INPUT, Vendor B, Vendor Full Adjustment]";

		int report3rdRowListCount = reportCol5List.size();
		ArrayList<String> report3rdRowListArray = new ArrayList<String>();
		for (int i = 1; i < report3rdRowListCount; i++) {
			String data = reportCol5List.get(i).getText();
			report3rdRowListArray.add(data);
		}
		String actRow3List = report3rdRowListArray.toString();
		String expRow3List = "[2.00, , , , 1.00, , , , 1.00, , , , , , , , 1.00, ]";

		int report4thRowListCount = reportCol6List.size();
		ArrayList<String> report4thRowListArray = new ArrayList<String>();
		for (int i = 1; i < report4thRowListCount; i++) {
			String data = reportCol6List.get(i).getText();
			report4thRowListArray.add(data);
		}
		String actRow4List = report4thRowListArray.toString();
		String expRow4List = "[HYDERABAD, HYDERABAD, , , HYDERABAD, HYDERABAD, , , HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, , HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, ]";

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_NextBtn));
		sl_NextBtn.click();

		Thread.sleep(3999);

		int reportsRow1ListCount1 = reportCol3List.size();
		ArrayList<String> reportsRow1ListArray1 = new ArrayList<String>();
		for (int i = 1; i < reportsRow1ListCount1; i++) {
			String data = reportCol3List.get(i).getText();

			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			String expadjustBills = df.format(date);

			reportsRow1ListArray.add(data);
		}
		String actRow1List1 = reportsRow1ListArray1.toString();
		String expRow1List1 = "[]";

		int report2ndRowListCount1 = reportCol4List.size();
		ArrayList<String> report2ndRowListArray1 = new ArrayList<String>();
		for (int i = 1; i < report2ndRowListCount1; i++) {
			String data = reportCol4List.get(i).getText();
			report2ndRowListArray1.add(data);
		}
		String actRow2List1 = report2ndRowListArray1.toString();
		String expRow2List1 = "[Bank, Journal Entries Control A/C, Journal Entries Control A/C, Bank, Bank, ]";

		int report3rdRowListCount1 = reportCol5List.size();
		ArrayList<String> report3rdRowListArray1 = new ArrayList<String>();
		for (int i = 1; i < report3rdRowListCount1; i++) {
			String data = reportCol5List.get(i).getText();
			report3rdRowListArray1.add(data);
		}
		String actRow3List1 = report3rdRowListArray1.toString();
		String expRow3List1 = "[, , , , , 1.00]";

		int report4thRowListCount1 = reportCol6List.size();
		ArrayList<String> report4thRowListArray1 = new ArrayList<String>();
		for (int i = 1; i < report4thRowListCount1; i++) {
			String data = reportCol6List.get(i).getText();
			report4thRowListArray1.add(data);
		}
		String actRow4List1 = report4thRowListArray1.toString();
		String expRow4List1 = "[, , , , , ]";

		System.out.println("actRow1List  : " + actRow1List);
		System.out.println("expRow1List  : " + expRow1List);
		System.out.println("*********************************************************************");

		System.out.println("actRow2List  : " + actRow2List);
		System.out.println("expRow2List  : " + expRow2List);
		System.out.println("*********************************************************************");

		System.out.println("actRow3List  : " + actRow3List);
		System.out.println("expRow3List  : " + expRow3List);
		System.out.println("*********************************************************************");

		System.out.println("actRow4List  : " + actRow4List);
		System.out.println("expRow4List  : " + expRow4List);
		System.out.println("*********************************************************************");

		System.out.println("actRow1List1  : " + actRow1List1);
		System.out.println("expRow1List1  : " + expRow1List1);
		System.out.println("*********************************************************************");

		System.out.println("actRow2List1  : " + actRow2List1);
		System.out.println("expRow2List1  : " + expRow2List1);
		System.out.println("*********************************************************************");

		System.out.println("actRow3List1  : " + actRow3List1);
		System.out.println("expRow3List1  : " + expRow3List1);
		System.out.println("*********************************************************************");

		System.out.println("actRow4List1  : " + actRow4List1);
		System.out.println("expRow4List1  : " + expRow4List1);
		System.out.println("*********************************************************************");

		if (actRow1List.equalsIgnoreCase(expRow1List) && actRow2List.equalsIgnoreCase(expRow2List)
				&& actRow3List.equalsIgnoreCase(expRow3List) && actRow4List.equalsIgnoreCase(expRow4List) &&

				actRow1List1.equalsIgnoreCase(expRow1List1) && actRow2List1.equalsIgnoreCase(expRow2List1)
				&& actRow3List1.equalsIgnoreCase(expRow3List1) && actRow4List1.equalsIgnoreCase(expRow4List1)) {
			System.out.println(" Test Pass: Values Dsiplayed as Expected ");
			return true;
		} else {
			System.out.println(" Test FAIL: Values Dsiplayed as Expected ");
			return false;
		}
	}

	@FindBy(xpath = "//div[@id='dvReportDetails']//tbody/tr/td[1]")
	private static List<WebElement> reportCol1List;

	@FindBy(xpath = "//div[@id='dvReportDetails']//tbody/tr/td[2]")
	private static List<WebElement> reportCol2List;

	@FindBy(xpath = "//div[@id='dvReportDetails']//tbody/tr/td[3]")
	private static List<WebElement> reportCol3List;

	@FindBy(xpath = "//div[@id='dvReportDetails']//tbody/tr/td[4]")
	private static List<WebElement> reportCol4List;

	@FindBy(xpath = "//div[@id='dvReportDetails']//tbody/tr/td[5]")
	private static List<WebElement> reportCol5List;

	@FindBy(xpath = "//div[@id='dvReportDetails']//tbody/tr/td[6]")
	private static List<WebElement> reportCol6List;

	@FindBy(xpath = "//div[@id='dvReportDetails']//tbody/tr/td[7]")
	private static List<WebElement> reportCol7List;

	@FindBy(xpath = "//div[@id='dvReportDetails']//tbody/tr/td[8]")
	private static List<WebElement> reportCol8List;

	@FindBy(xpath = "//div[@id='dvReportDetails']//tbody/tr/td[9]")
	private static List<WebElement> reportCol9List;

	@FindBy(xpath = "//*[@id='tblFooterReportRender']//div/button[4]")
	private static WebElement sl_NextBtn;

	public boolean checkSavingAccountingTrasactionsOfInventoryTagDetails()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {

		Thread.sleep(2999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(homeMenu));
		homeMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(utilities));
		utilities.click();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportDesignerMenu));
		reportDesignerMenu.click();

		Thread.sleep(2999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportNameDropdown));
		reportNameDropdown.sendKeys("Accounting Trasactions of Inventory Tag-Details");
		reportNameDropdown.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportTypeDropdown));
		Select rtd = new Select(reportTypeDropdown);
		rtd.selectByVisibleText("Details");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(inventoryExpandBtn));
		inventoryExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportsBtn));
		reportsBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dataSetTab));
		dataSetTab.click();

		Thread.sleep(2000);

		int transactionSetListCount = transactionSetList.size();

		for (int i = 0; i <= transactionSetListCount; i++) {
			String data = transactionSetList.get(i).getText();

			if (data.equalsIgnoreCase("Accounting transactions of inventory tag")) {
				transactionSetList.get(i).click();

				break;
			}
		}

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTab));
		customizationTab.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionExpandBtn));
		transactionExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionFieldsExpandBtn));
		transactionFieldsExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(acc1ExpandBtn));
		acc1ExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(acc1NameBtn));
		getAction().doubleClick(acc1NameBtn).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(acc1CodeBtn));
		getAction().doubleClick(acc1CodeBtn).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(acc2ExpandBtn));
		acc2ExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(acc2NameBtn));
		getAction().doubleClick(acc2NameBtn).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(acc2CodeBtn));
		getAction().doubleClick(acc2CodeBtn).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemExpandBtn1));
		itemExpandBtn1.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemNameBtn1));
		getAction().doubleClick(itemNameBtn1).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemNameBtn1));
		getAction().doubleClick(itemCodeBtn1).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionFieldsExpandBtn));
		transactionFieldsExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(extraFieldsExpandBtn));
		extraFieldsExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouseExpandBtn1));
		warehouseExpandBtn1.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouseNameBtn1));
		getAction().doubleClick(warehouseNameBtn1).build().perform();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(finishBtn));
		finishBtn.click();

		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : " + actMessage + " Value Expected : " + expMessage);

		if (actMessage.equalsIgnoreCase(expMessage)) {

			System.out.println(" Test PasS: Report desgining is saved with Document Type ");
			return true;
		} else {
			System.out.println(" Test FAIL: Report desgining is saved with Document Type ");
			return false;
		}

	}

	public boolean checkReportAccountingTrasactionsofInventoryTagDetails() throws InterruptedException {
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
		searchTxt.click();
		searchTxt.sendKeys("Accounting Trasactions of Inventory Tag-Details");
		Thread.sleep(1000);
		searchTxt.sendKeys(Keys.ENTER);

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_DateOptionDropdown));
		/* sl_DateOptionDropdown.click(); */

		Select s = new Select(sl_DateOptionDropdown);
		s.selectByValue("1");

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportDepartmentTxt));
		reportDepartmentTxt.click();
		reportDepartmentTxt.sendKeys("HYDERABAD");
		Thread.sleep(1000);
		reportDepartmentTxt.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_OkBtn));
		sl_OkBtn.click();

		Thread.sleep(1500);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_1stRow1stCol));

		int reportsRow1ListCount = reportCol1List.size();
		ArrayList<String> reportsRow1ListArray = new ArrayList<String>();
		for (int i = 1; i < reportsRow1ListCount; i++) {
			String data = reportCol1List.get(i).getText();

			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			String expadjustBills = df.format(date);

			reportsRow1ListArray.add(data);
		}
		String actRow1List = reportsRow1ListArray.toString();
		String expRow1List = "[2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]";

		int report2ndRowListCount = reportCol2List.size();
		ArrayList<String> report2ndRowListArray = new ArrayList<String>();
		for (int i = 1; i < report2ndRowListCount; i++) {
			String data = reportCol2List.get(i).getText();
			report2ndRowListArray.add(data);
		}
		String actRow2List = report2ndRowListArray.toString();
		String expRow2List = "[STD RATE COGS ACC INV, Customer A, SR COGS POSTING ACC, Vendor New Reference, VAT INPUT, , Vendor New Reference, Vendor B, , Vendor B, VAT INPUT]";

		int report3rdRowListCount = reportCol3List.size();
		ArrayList<String> report3rdRowListArray = new ArrayList<String>();
		for (int i = 1; i < report3rdRowListCount; i++) {
			String data = reportCol3List.get(i).getText();
			report3rdRowListArray.add(data);
		}
		String actRow3List = report3rdRowListArray.toString();
		String expRow3List = "[STD RATE COGS ACC INV, 122-001, SR COGS POSTING ACC, Vendor New Reference, VAT INPUT, , Vendor New Reference, 033-002, , 033-002, VAT INPUT]";

		int report4thRowListCount = reportCol4List.size();
		ArrayList<String> report4thRowListArray = new ArrayList<String>();
		for (int i = 1; i < report4thRowListCount; i++) {
			String data = reportCol4List.get(i).getText();
			report4thRowListArray.add(data);
		}
		String actRow4List = report4thRowListArray.toString();
		String expRow4List = "[COGS POSTING ACC, Sales - Computers, STD RATE COGS ACC INV, STD RATE COGS ACC INV, VAT ADVANCE PURCHASE, PURCHASE VARIANCE, VAT INPUT, STD RATE COGS ACC INV, PURCHASE VARIANCE, VAT INPUT, VAT ADVANCE PURCHASE]";

		int reportsRow1ListCount1 = reportCol5List.size();
		ArrayList<String> reportsRow1ListArray1 = new ArrayList<String>();
		for (int i = 1; i < reportsRow1ListCount1; i++) {
			String data = reportCol5List.get(i).getText();

			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			String expadjustBills = df.format(date);

			reportsRow1ListArray.add(data);
		}
		String actRow1List1 = reportsRow1ListArray1.toString();
		String expRow1List1 = "[]";

		int report2ndRowListCount1 = reportCol6List.size();
		ArrayList<String> report2ndRowListArray1 = new ArrayList<String>();
		for (int i = 1; i < report2ndRowListCount1; i++) {
			String data = reportCol6List.get(i).getText();
			report2ndRowListArray1.add(data);
		}
		String actRow2List1 = report2ndRowListArray1.toString();
		String expRow2List1 = "[, STD RATE COGS ITEM, , STD RATE COGS ITEM, , , , STD RATE COGS ITEM, , , ]";

		System.out.println("actRow1List  : " + actRow1List);
		System.out.println("expRow1List  : " + expRow1List);
		System.out.println("*********************************************************************");

		System.out.println("actRow2List  : " + actRow2List);
		System.out.println("expRow2List  : " + expRow2List);
		System.out.println("*********************************************************************");

		System.out.println("actRow3List  : " + actRow3List);
		System.out.println("expRow3List  : " + expRow3List);
		System.out.println("*********************************************************************");

		System.out.println("actRow4List  : " + actRow4List);
		System.out.println("expRow4List  : " + expRow4List);
		System.out.println("*********************************************************************");

		System.out.println("actRow1List1  : " + actRow1List1);
		System.out.println("expRow1List1  : " + expRow1List1);
		System.out.println("*********************************************************************");

		System.out.println("actRow2List1  : " + actRow2List1);
		System.out.println("expRow2List1  : " + expRow2List1);
		System.out.println("*********************************************************************");

		if (actRow1List.equalsIgnoreCase(expRow1List) && actRow2List.equalsIgnoreCase(expRow2List)
				&& actRow3List.equalsIgnoreCase(expRow3List) && actRow4List.equalsIgnoreCase(expRow4List) &&

				actRow1List1.equalsIgnoreCase(expRow1List1) && actRow2List1.equalsIgnoreCase(expRow2List1)) {
			System.out.println(" Test Pass: Values Dsiplayed as Expected ");
			return true;
		} else {
			System.out.println(" Test FAIL: Values Dsiplayed as Expected ");
			return false;
		}
	}

	public boolean checkSavingAccountingTransactionsoFTagDetails()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {

		Thread.sleep(2999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(homeMenu));
		homeMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(utilities));
		utilities.click();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportDesignerMenu));
		reportDesignerMenu.click();

		Thread.sleep(2999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportNameDropdown));
		reportNameDropdown.sendKeys("Accounting Transactions of a Tag-Details");
		reportNameDropdown.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportTypeDropdown));
		Select rtd = new Select(reportTypeDropdown);
		rtd.selectByVisibleText("Details");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(finanincalExpandbtn));
		finanincalExpandbtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dataSetTab));
		dataSetTab.click();

		Thread.sleep(2000);

		int transactionSetListCount = transactionSetList.size();

		for (int i = 0; i <= transactionSetListCount; i++) {
			String data = transactionSetList.get(i).getText();

			if (data.equalsIgnoreCase("Accounting transactions of a tag")) {
				transactionSetList.get(i).click();

				break;
			}
		}

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTab));
		customizationTab.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionExpandBtn));
		transactionExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionFieldsExpandBtn));
		transactionFieldsExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(acc1ExpandBtn));
		acc1ExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(acc1NameBtn));
		getAction().doubleClick(acc1NameBtn).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(acc1CodeBtn));
		getAction().doubleClick(acc1CodeBtn).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(acc2ExpandBtn));
		acc2ExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(acc2NameBtn));
		getAction().doubleClick(acc2NameBtn).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(acc2CodeBtn));
		getAction().doubleClick(acc2CodeBtn).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemExpandBtn1));
		itemExpandBtn1.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemNameBtn1));
		getAction().doubleClick(itemNameBtn1).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemNameBtn1));
		getAction().doubleClick(itemCodeBtn1).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionFieldsExpandBtn));
		transactionFieldsExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(extraFieldsExpandBtn));
		extraFieldsExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouseExpandBtn1));
		warehouseExpandBtn1.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouseNameBtn1));
		getAction().doubleClick(warehouseNameBtn1).build().perform();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(finishBtn));
		finishBtn.click();

		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : " + actMessage + " Value Expected : " + expMessage);

		if (actMessage.equalsIgnoreCase(expMessage)) {

			System.out.println(" Test PasS: Report desgining is saved with Document Type ");
			return true;
		} else {
			System.out.println(" Test FAIL: Report desgining is saved with Document Type ");
			return false;
		}

	}

	@FindBy(xpath = "//*[@id='dvReportDetails']/div/table/thead/tr/th")
	public static List<WebElement> reportHeaderList;

	@FindBy(xpath = "//div[@id='idGlobalError']")
	public static WebElement validationConfirmationMessage;

	public boolean checkReportAccountingTransactionsofTagDetails() throws InterruptedException {
		Thread.sleep(2999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
		searchTxt.click();
		searchTxt.sendKeys("Accounting Transactions of a Tag-Details");
		Thread.sleep(1000);
		searchTxt.sendKeys(Keys.ENTER);

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_DateOptionDropdown));
		/* sl_DateOptionDropdown.click(); */

		Select s = new Select(sl_DateOptionDropdown);
		s.selectByValue("1");

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_OkBtn));
		sl_OkBtn.click();

		Thread.sleep(2000);
		boolean novalidationConfirmationMessage = validationConfirmationMessage.getText().isEmpty();

		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";

		Thread.sleep(3000);

		Thread.sleep(1500);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_1stRow1stCol));

		int reportsRow1ListCount = reportCol1List.size();
		ArrayList<String> reportsRow1ListArray = new ArrayList<String>();
		for (int i = 1; i < reportsRow1ListCount; i++) {
			String data = reportCol1List.get(i).getText();

			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			String expadjustBills = df.format(date);

			reportsRow1ListArray.add(data);
		}
		String actRow1List = reportsRow1ListArray.toString();
		String expRow1List = "[2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19]";

		int report2ndRowListCount = reportCol2List.size();
		ArrayList<String> report2ndRowListArray = new ArrayList<String>();
		for (int i = 1; i < report2ndRowListCount; i++) {
			String data = reportCol2List.get(i).getText();
			report2ndRowListArray.add(data);
		}
		String actRow2List = report2ndRowListArray.toString();
		String expRow2List = "[Customer A, STD RATE COGS ACC INV, Bank, Bank, Customer A, SR COGS POSTING ACC, Bank, Bank, Vendor New Reference, VAT INPUT, , Vendor New Reference, Bank, Vendor B, Vendor B, VAT INPUT, , Vendor Full Adjustment]";

		int report3rdRowListCount = reportCol3List.size();
		ArrayList<String> report3rdRowListArray = new ArrayList<String>();
		for (int i = 1; i < report3rdRowListCount; i++) {
			String data = reportCol3List.get(i).getText();
			report3rdRowListArray.add(data);
		}
		String actRow3List = report3rdRowListArray.toString();
		String expRow3List = "[122-001, STD RATE COGS ACC INV, 121-001, 121-001, 122-001, SR COGS POSTING ACC, 121-001, 121-001, Vendor New Reference, VAT INPUT, , Vendor New Reference, 121-001, 033-002, 033-002, VAT INPUT, , Vendor Full Adjustment]";

		int report4thRowListCount = reportCol4List.size();
		ArrayList<String> report4thRowListArray = new ArrayList<String>();
		for (int i = 1; i < report4thRowListCount; i++) {
			String data = reportCol4List.get(i).getText();
			report4thRowListArray.add(data);
		}
		String actRow4List = report4thRowListArray.toString();
		String expRow4List = "[Sales - Computers, COGS POSTING ACC, Customer A, Customer A, Sales - Computers, STD RATE COGS ACC INV, Customer New Reference, Vendor New Reference, STD RATE COGS ACC INV, VAT ADVANCE PURCHASE, PURCHASE VARIANCE, VAT INPUT, Vendor New Reference, STD RATE COGS ACC INV, VAT INPUT, VAT ADVANCE PURCHASE, PURCHASE VARIANCE, Bank]";

		int reportsRow1ListCount1 = reportCol5List.size();
		ArrayList<String> reportsRow1ListArray1 = new ArrayList<String>();
		for (int i = 1; i < reportsRow1ListCount1; i++) {
			String data = reportCol5List.get(i).getText();

			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			String expadjustBills = df.format(date);

			reportsRow1ListArray.add(data);
		}
		String actRow1List1 = reportsRow1ListArray1.toString();
		String expRow1List1 = "[]";

		int report2ndRowListCount1 = reportCol6List.size();
		ArrayList<String> report2ndRowListArray1 = new ArrayList<String>();
		for (int i = 1; i < report2ndRowListCount1; i++) {
			String data = reportCol6List.get(i).getText();
			report2ndRowListArray1.add(data);
		}
		String actRow2List1 = report2ndRowListArray1.toString();
		String expRow2List1 = "[STD RATE COGS ITEM, , , , STD RATE COGS ITEM, , , , STD RATE COGS ITEM, , , , , STD RATE COGS ITEM, , , , ]";

		System.out.println("actRow1List  : " + actRow1List);
		System.out.println("expRow1List  : " + expRow1List);
		System.out.println("*********************************************************************");

		System.out.println("actRow2List  : " + actRow2List);
		System.out.println("expRow2List  : " + expRow2List);
		System.out.println("*********************************************************************");

		System.out.println("actRow3List  : " + actRow3List);
		System.out.println("expRow3List  : " + expRow3List);
		System.out.println("*********************************************************************");

		System.out.println("actRow4List  : " + actRow4List);
		System.out.println("expRow4List  : " + expRow4List);
		System.out.println("*********************************************************************");

		System.out.println("actRow1List1  : " + actRow1List1);
		System.out.println("expRow1List1  : " + expRow1List1);
		System.out.println("*********************************************************************");

		System.out.println("actRow2List1  : " + actRow2List1);
		System.out.println("expRow2List1  : " + expRow2List1);
		System.out.println("*********************************************************************");

		if (actRow1List.equalsIgnoreCase(expRow1List) && actRow2List.equalsIgnoreCase(expRow2List)
				&& actRow3List.equalsIgnoreCase(expRow3List) && actRow4List.equalsIgnoreCase(expRow4List) &&

				actRow1List1.equalsIgnoreCase(expRow1List1) &&

				actvalidationConfirmationMessage.equalsIgnoreCase(expvalidationConfirmationMessage)) {

			System.out.println(" Test Pass: Values Dsiplayed as Expected ");
			return true;
		} else {
			System.out.println(" Test FAIL: Values Dsiplayed as Expected ");
			String meesage = validationConfirmationMessage.getText();
			System.err.println(" Meesage Displayed  : " + meesage);

			getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportCloseBtn));
			reportCloseBtn.click();

			getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportDepartmentTxt));
			reportDepartmentTxt.click();
			reportDepartmentTxt.sendKeys("DUBAI");
			Thread.sleep(1000);
			reportDepartmentTxt.sendKeys(Keys.TAB);

			getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_OkBtn));
			sl_OkBtn.click();

			Thread.sleep(2000);
			boolean novalidationConfirmationMessage1 = validationConfirmationMessage.getText().isEmpty();

			String actvalidationConfirmationMessage1 = Boolean.toString(novalidationConfirmationMessage1);
			String expvalidationConfirmationMessage1 = "true";

			if (actvalidationConfirmationMessage1.equals(expvalidationConfirmationMessage1)) {
				return true;
			}

			else {
				return false;
			}
		}
	}

	public boolean checkSavingAllAccountsByTagDetails()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {

		Thread.sleep(2999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(homeMenu));
		homeMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(utilities));
		utilities.click();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportDesignerMenu));
		reportDesignerMenu.click();

		Thread.sleep(2999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportNameDropdown));
		reportNameDropdown.sendKeys("All Accounts By Tag-Details");
		reportNameDropdown.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportTypeDropdown));
		Select rtd = new Select(reportTypeDropdown);
		rtd.selectByVisibleText("Details");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(finanincalExpandbtn1));
		finanincalExpandbtn1.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(finanincalReportsExpandbtn));
		finanincalReportsExpandbtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dataSetTab));
		dataSetTab.click();

		Thread.sleep(2000);

		int transactionSetListCount = transactionSetList.size();

		for (int i = 0; i <= transactionSetListCount; i++) {
			String data = transactionSetList.get(i).getText();

			if (data.equalsIgnoreCase("All accounts by tag")) {
				transactionSetList.get(i).click();

				break;
			}
		}

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTab));
		customizationTab.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionExpandBtn));
		transactionExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionFieldsExpandBtn));
		transactionFieldsExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(acc1ExpandBtn));
		acc1ExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(acc1NameBtn));
		getAction().doubleClick(acc1NameBtn).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(acc1CodeBtn));
		getAction().doubleClick(acc1CodeBtn).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(acc2ExpandBtn));
		acc2ExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(acc2NameBtn));
		getAction().doubleClick(acc2NameBtn).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(acc2CodeBtn));
		getAction().doubleClick(acc2CodeBtn).build().perform();

		Thread.sleep(2000);
		getAction().moveToElement(quantityBtn2).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(quantityBtn2));
		getAction().doubleClick(quantityBtn2).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rateBtn2));
		getAction().doubleClick(rateBtn2).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemExpandBtn1));
		itemExpandBtn1.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemNameBtn1));
		getAction().doubleClick(itemNameBtn1).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemNameBtn1));
		getAction().doubleClick(itemCodeBtn1).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionFieldsExpandBtn));
		transactionFieldsExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(extraFieldsExpandBtn));
		extraFieldsExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouseExpandBtn2));
		warehouseExpandBtn2.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouseNameBtn2));
		getAction().doubleClick(warehouseNameBtn2).build().perform();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(finishBtn));
		finishBtn.click();

		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : " + actMessage + " Value Expected : " + expMessage);

		if (actMessage.equalsIgnoreCase(expMessage)) {

			System.out.println(" Test PasS: Report desgining is saved with Document Type ");
			return true;
		} else {
			System.out.println(" Test FAIL: Report desgining is saved with Document Type ");
			return false;
		}

	}

	public boolean checkReportAllAccountsByTagDetails() throws InterruptedException {
		Thread.sleep(2000);
		getDriver().navigate().refresh();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
		searchTxt.click();
		searchTxt.sendKeys("All Accounts By Tag-Details");
		Thread.sleep(1000);
		searchTxt.sendKeys(Keys.ENTER);

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_DateOptionDropdown));
		/* sl_DateOptionDropdown.click(); */

		Select s = new Select(sl_DateOptionDropdown);
		s.selectByValue("1");

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_OkBtn));
		sl_OkBtn.click();

		Thread.sleep(2000);
		boolean novalidationConfirmationMessage = validationConfirmationMessage.getText().isEmpty();

		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";

		Thread.sleep(3000);

		Thread.sleep(1500);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_1stRow1stCol));

		int reportsRow1ListCount = reportCol1List.size();
		ArrayList<String> reportsRow1ListArray = new ArrayList<String>();
		for (int i = 1; i < reportsRow1ListCount; i++) {
			String data = reportCol1List.get(i).getText();

			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			String expadjustBills = df.format(date);

			reportsRow1ListArray.add(data);
		}
		String actRow1List = reportsRow1ListArray.toString();
		String expRow1List = "[2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19]";

		int report2ndRowListCount = reportCol2List.size();
		ArrayList<String> report2ndRowListArray = new ArrayList<String>();
		for (int i = 1; i < report2ndRowListCount; i++) {
			String data = reportCol2List.get(i).getText();
			report2ndRowListArray.add(data);
		}
		String actRow2List = report2ndRowListArray.toString();
		String expRow2List = "[STD RATE COGS ACC INV, Customer A, Bank, Bank, SR COGS POSTING ACC, Customer A, Bank, Bank, Vendor New Reference, VAT INPUT, Vendor New Reference, , Bank, Bank, , Vendor B, VAT INPUT, Vendor B]";

		int report3rdRowListCount = reportCol3List.size();
		ArrayList<String> report3rdRowListArray = new ArrayList<String>();
		for (int i = 1; i < report3rdRowListCount; i++) {
			String data = reportCol3List.get(i).getText();
			report3rdRowListArray.add(data);
		}
		String actRow3List = report3rdRowListArray.toString();
		String expRow3List = "[STD RATE COGS ACC INV, 122-001, 121-001, 121-001, SR COGS POSTING ACC, 122-001, 121-001, 121-001, Vendor New Reference, VAT INPUT, Vendor New Reference, , 121-001, 121-001, , 033-002, VAT INPUT, 033-002]";

		int report4thRowListCount = reportCol4List.size();
		ArrayList<String> report4thRowListArray = new ArrayList<String>();
		for (int i = 1; i < report4thRowListCount; i++) {
			String data = reportCol4List.get(i).getText();
			report4thRowListArray.add(data);
		}
		String actRow4List = report4thRowListArray.toString();
		String expRow4List = "[COGS POSTING ACC, Sales - Computers, Customer A, Customer A, STD RATE COGS ACC INV, Sales - Computers, Customer New Reference, Vendor New Reference, VAT INPUT, VAT ADVANCE PURCHASE, STD RATE COGS ACC INV, PURCHASE VARIANCE, Vendor New Reference, Vendor B, PURCHASE VARIANCE, VAT INPUT, VAT ADVANCE PURCHASE, STD RATE COGS ACC INV]";

		int reportsRow1ListCount1 = reportCol5List.size();
		ArrayList<String> reportsRow1ListArray1 = new ArrayList<String>();
		for (int i = 1; i < reportsRow1ListCount1; i++) {
			String data = reportCol5List.get(i).getText();

			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			String expadjustBills = df.format(date);

			reportsRow1ListArray.add(data);
		}
		String actRow1List1 = reportsRow1ListArray1.toString();
		String expRow1List1 = "[]";

		int report2ndRowListCount1 = reportCol6List.size();
		ArrayList<String> report2ndRowListArray1 = new ArrayList<String>();
		for (int i = 1; i < report2ndRowListCount1; i++) {
			String data = reportCol6List.get(i).getText();
			report2ndRowListArray1.add(data);
		}
		String actRow2List1 = report2ndRowListArray1.toString();
		String expRow2List1 = "[, 2.00, , , , 1.00, , , , , 1.00, , , , , , , 1.00]";

		int report2ndRowListCount7 = reportCol7List.size();
		ArrayList<String> report2ndRowListArray7 = new ArrayList<String>();
		for (int i = 1; i < report2ndRowListCount7; i++) {
			String data = reportCol7List.get(i).getText();
			report2ndRowListArray7.add(data);
		}
		String actRow2List7 = report2ndRowListArray7.toString();
		String expRow2List7 = "[, 10.00, , , , 5.00, , , , , 5.00, , , , , , , 5.00]";

		int report2ndRowListCount8 = reportCol8List.size();
		ArrayList<String> report2ndRowListArray8 = new ArrayList<String>();
		for (int i = 1; i < report2ndRowListCount8; i++) {
			String data = reportCol8List.get(i).getText();
			report2ndRowListArray8.add(data);
		}
		String actRow2List8 = report2ndRowListArray8.toString();
		String expRow2List8 = "[, STD RATE COGS ITEM, , , , STD RATE COGS ITEM, , , , , STD RATE COGS ITEM, , , , , , , STD RATE COGS ITEM]";

		Thread.sleep(2000);

		sl_NextBtn.click();

		Thread.sleep(2000);

		int count = report6thRowList.size();
		ArrayList<String> array6 = new ArrayList<String>();
		for (int i = 1; i < count; i++) {
			String data = report6thRowList.get(i).getText();
			array6.add(data);
		}
		String actRow6 = array6.toString();
		String expRow6 = "[Journal Entries Control A/C, JEC, Customer Semi Adjustment, Customer Semi Adjustment, , , , , ]";

		System.out.println("actRow1List  : " + actRow1List);
		System.out.println("expRow1List  : " + expRow1List);
		System.out.println("*********************************************************************");

		System.out.println("actRow2List  : " + actRow2List);
		System.out.println("expRow2List  : " + expRow2List);
		System.out.println("*********************************************************************");

		System.out.println("actRow3List  : " + actRow3List);
		System.out.println("expRow3List  : " + expRow3List);
		System.out.println("*********************************************************************");

		System.out.println("actRow4List  : " + actRow4List);
		System.out.println("expRow4List  : " + expRow4List);
		System.out.println("*********************************************************************");

		System.out.println("actRow1List1  : " + actRow1List1);
		System.out.println("expRow1List1  : " + expRow1List1);
		System.out.println("*********************************************************************");

		System.out.println("actRow2List1  :  " + actRow2List1);
		System.out.println("expRow2List1  :  " + expRow2List1);
		System.out.println("*********************************************************************");

		System.out.println("actRow2List7  : " + actRow2List7);
		System.out.println("expRow2List7  : " + expRow2List7);
		System.out.println("*********************************************************************");

		System.out.println("actRow2List8  : " + actRow2List8);
		System.out.println("expRow2List8  : " + expRow2List8);
		System.out.println("*********************************************************************");

		System.out.println("row6  : " + actRow6);
		System.out.println("row6  : " + expRow6);
		System.out.println("*********************************************************************");

		if (actRow1List.equalsIgnoreCase(expRow1List) && actRow2List.equalsIgnoreCase(expRow2List)
				&& actRow3List.equalsIgnoreCase(expRow3List) && actRow4List.equalsIgnoreCase(expRow4List) &&

				actRow1List1.equalsIgnoreCase(expRow1List1)/*
															 * && actRow2List1.equalsIgnoreCase(expRow2List1) /*&&
															 * 
															 * actRow2List7.equalsIgnoreCase(expRow2List7) &&
															 * actRow2List8.equalsIgnoreCase(expRow2List8) &&
															 * 
															 * actvalidationConfirmationMessage.equalsIgnoreCase(
															 * expvalidationConfirmationMessage)
															 */) {
			System.out.println(" Test Pass: Values Dsiplayed as Expected ");
			return true;
		} else {
			System.out.println(" Test FAIL: Values Dsiplayed as Expected ");
			String meesage = validationConfirmationMessage.getText();
			System.err.println(" Meesage Displayed  : " + meesage);
			return false;
		}
	}

	public boolean checkSavingInventoryTransactionsOfAProductDetails()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {

		Thread.sleep(2999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(homeMenu));
		homeMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(utilities));
		utilities.click();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportDesignerMenu));
		reportDesignerMenu.click();

		Thread.sleep(2999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportNameDropdown));
		reportNameDropdown.sendKeys("Inventory Transactions of a Product-Details");
		reportNameDropdown.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportTypeDropdown));
		Select rtd = new Select(reportTypeDropdown);
		rtd.selectByVisibleText("Details");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(inventoryExpandBtn));
		inventoryExpandBtn.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportsBtn));
		reportsBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dataSetTab));
		dataSetTab.click();

		Thread.sleep(2000);

		int transactionSetListCount = transactionSetList.size();

		for (int i = 0; i <= transactionSetListCount; i++) {
			String data = transactionSetList.get(i).getText();

			if (data.equalsIgnoreCase("Inventory transactions of a product")) {
				transactionSetList.get(i).click();

				break;
			}
		}

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTab));
		customizationTab.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionExpandBtn));
		transactionExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionFieldsExpandBtn));
		transactionFieldsExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(acc1ExpandBtn));
		acc1ExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(acc1NameBtn));
		getAction().doubleClick(acc1NameBtn).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(acc1CodeBtn));
		getAction().doubleClick(acc1CodeBtn).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(acc2ExpandBtn));
		acc2ExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(acc2NameBtn));
		getAction().doubleClick(acc2NameBtn).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(acc2CodeBtn));
		getAction().doubleClick(acc2CodeBtn).build().perform();

		Thread.sleep(2000);
		getAction().moveToElement(quantityBtn2).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(quantityBtn2));
		getAction().doubleClick(quantityBtn2).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rateBtn2));
		getAction().doubleClick(rateBtn2).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemExpandBtn1));
		itemExpandBtn1.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemNameBtn1));
		getAction().doubleClick(itemNameBtn1).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemNameBtn1));
		getAction().doubleClick(itemCodeBtn1).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionFieldsExpandBtn));
		transactionFieldsExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(extraFieldsExpandBtn));
		extraFieldsExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouseExpandBtn2));
		warehouseExpandBtn2.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouseNameBtn2));
		getAction().doubleClick(warehouseNameBtn2).build().perform();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(finishBtn));
		finishBtn.click();

		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : " + actMessage + " Value Expected : " + expMessage);

		if (actMessage.equalsIgnoreCase(expMessage)) {

			System.out.println(" Test PasS: Report desgining is saved with Document Type ");
			return true;
		} else {
			System.out.println(" Test FAIL: Report desgining is saved with Document Type ");
			return false;
		}

	}

	public boolean checkSavingRDWithAllProductsDetails()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {

		Thread.sleep(1999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(homeMenu));
		homeMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(utilities));
		utilities.click();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportDesignerMenu));
		reportDesignerMenu.click();

		Thread.sleep(2999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportNameDropdown));
		reportNameDropdown.sendKeys("All Products-Details");
		reportNameDropdown.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportTypeDropdown));
		Select rtd = new Select(reportTypeDropdown);
		rtd.selectByVisibleText("Details");

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(inventoryExpandBtn));
		inventoryExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportsBtn));
		reportsBtn.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dataSetTab));
		dataSetTab.click();

		Thread.sleep(2000);

		int transactionSetListCount = transactionSetList.size();

		for (int i = 0; i <= transactionSetListCount; i++) {
			String data = transactionSetList.get(i).getText();

			if (data.equalsIgnoreCase("All products")) {
				transactionSetList.get(i).click();

				break;
			}
		}

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTab));
		customizationTab.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionExpandBtn));
		transactionExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionFieldsExpandBtn));
		transactionFieldsExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemExpandBtn));
		itemExpandBtn1.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemNameBtn1));
		getAction().doubleClick(itemNameBtn1).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemCodeBtn1));
		getAction().doubleClick(itemCodeBtn1).build().perform();

		Thread.sleep(2000);
		getAction().moveToElement(quantityBtn2).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(quantityBtn2));
		getAction().doubleClick(quantityBtn2).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rateBtn2));
		getAction().doubleClick(rateBtn2).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionFieldsExpandBtn));
		transactionFieldsExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(extraFieldsExpandBtn));
		extraFieldsExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouseExpandBtn2));
		warehouseExpandBtn2.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouseNameBtn2));
		getAction().doubleClick(warehouseNameBtn2).build().perform();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(finishBtn));
		finishBtn.click();

		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : " + actMessage + " Value Expected : " + expMessage);

		if (actMessage.equalsIgnoreCase(expMessage)) {

			System.out.println(" Test PasS: Report desgining is saved with Document Type ");
			return true;
		} else {
			System.out.println(" Test FAIL: Report desgining is saved with Document Type ");
			return false;
		}

	}

	public boolean checkReportAllProductsDetailsDetails() throws InterruptedException {
		getDriver().navigate().refresh();
		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
		searchTxt.click();
		searchTxt.sendKeys("All Products-Details");
		Thread.sleep(1000);
		searchTxt.sendKeys(Keys.ENTER);

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_DateOptionDropdown));

		Select s = new Select(sl_DateOptionDropdown);
		s.selectByValue("1");

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_OkBtn));
		sl_OkBtn.click();

		Thread.sleep(2000);
		boolean novalidationConfirmationMessage = validationConfirmationMessage.getText().isEmpty();

		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";

		Thread.sleep(3000);

		Thread.sleep(1500);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_1stRow1stCol));

		int reportsRow1ListCount = reportCol1List.size();
		ArrayList<String> reportsRow1ListArray = new ArrayList<String>();
		for (int i = 1; i < reportsRow1ListCount; i++) {
			String data = reportCol1List.get(i).getText();

			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			String expadjustBills = df.format(date);

			reportsRow1ListArray.add(data);
		}
		String actRow1List = reportsRow1ListArray.toString();
		String expRow1List = "[2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13]";

		int report2ndRowListCount = reportCol2List.size();
		ArrayList<String> report2ndRowListArray = new ArrayList<String>();
		for (int i = 1; i < report2ndRowListCount; i++) {
			String data = reportCol2List.get(i).getText();
			report2ndRowListArray.add(data);
		}
		String actRow2List = report2ndRowListArray.toString();
		String expRow2List = "[, STD RATE COGS ITEM, , STD RATE COGS ITEM, , , , STD RATE COGS ITEM, , , , Grand Total]";

		int report3rdRowListCount = reportCol3List.size();
		ArrayList<String> report3rdRowListArray = new ArrayList<String>();
		for (int i = 1; i < report3rdRowListCount; i++) {
			String data = reportCol3List.get(i).getText();
			report3rdRowListArray.add(data);
		}
		String actRow3List = report3rdRowListArray.toString();
		String expRow3List = "[, 1.00, , 1.00, , , , 1.00, , , , 1.00]";

		int report4thRowListCount = reportCol4List.size();
		ArrayList<String> report4thRowListArray = new ArrayList<String>();
		for (int i = 1; i < report4thRowListCount; i++) {
			String data = reportCol4List.get(i).getText();
			report4thRowListArray.add(data);
		}
		String actRow4List = report4thRowListArray.toString();
		String expRow4List = "[, 5.00, , 5.00, , , , 5.00, , , , 25.00]";

		int reportsRow1ListCount1 = reportCol5List.size();
		ArrayList<String> reportsRow1ListArray1 = new ArrayList<String>();
		for (int i = 1; i < reportsRow1ListCount1; i++) {
			String data = reportCol5List.get(i).getText();

			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			String expadjustBills = df.format(date);

			reportsRow1ListArray1.add(data);
		}
		String actRow1List1 = reportsRow1ListArray1.toString();
		String expRow1List1 = "[HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, ]";

		System.out.println("actRow1List  : " + actRow1List);
		System.out.println("expRow1List  : " + expRow1List);
		System.out.println("*********************************************************************");

		System.out.println("actRow2List  : " + actRow2List);
		System.out.println("expRow2List  : " + expRow2List);
		System.out.println("*********************************************************************");

		System.out.println("actRow3List  : " + actRow3List);
		System.out.println("expRow3List  : " + expRow3List);
		System.out.println("*********************************************************************");

		System.out.println("actRow4List  : " + actRow4List);
		System.out.println("expRow4List  : " + expRow4List);
		System.out.println("*********************************************************************");

		System.out.println("actRow1List1  : " + actRow1List1);
		System.out.println("expRow1List1  : " + expRow1List1);
		System.out.println("*********************************************************************");

		if (actRow1List.equalsIgnoreCase(expRow1List) && actRow2List.equalsIgnoreCase(expRow2List)
				&& actRow3List.equalsIgnoreCase(expRow3List) && actRow4List.equalsIgnoreCase(expRow4List) &&

				actRow1List1.equalsIgnoreCase(expRow1List1)
				&& actvalidationConfirmationMessage.equalsIgnoreCase(expvalidationConfirmationMessage)) {
			System.out.println(" Test Pass: Values Dsiplayed as Expected ");
			return true;
		} else {
			System.out.println(" Test FAIL: Values Dsiplayed as Expected ");
			String meesage = validationConfirmationMessage.getText();
			System.err.println(" Meesage Displayed  : " + meesage);
			return false;
		}
	}

	public boolean checkSavingRDWithAllProductsBTagDetails()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {

		Thread.sleep(1999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(homeMenu));
		homeMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(utilities));
		utilities.click();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportDesignerMenu));
		reportDesignerMenu.click();

		Thread.sleep(2999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportNameDropdown));
		reportNameDropdown.sendKeys("All Products By Tag-Details");
		reportNameDropdown.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportTypeDropdown));
		Select rtd = new Select(reportTypeDropdown);
		rtd.selectByVisibleText("Details");

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(inventoryExpandBtn));
		inventoryExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportsBtn));
		reportsBtn.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dataSetTab));
		dataSetTab.click();

		Thread.sleep(2000);

		int transactionSetListCount = transactionSetList.size();

		for (int i = 0; i <= transactionSetListCount; i++) {
			String data = transactionSetList.get(i).getText();

			if (data.equalsIgnoreCase("All products by tag")) {
				transactionSetList.get(i).click();

				break;
			}
		}

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTab));
		customizationTab.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionExpandBtn));
		transactionExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionFieldsExpandBtn));
		transactionFieldsExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemExpandBtn));
		itemExpandBtn1.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemNameBtn1));
		getAction().doubleClick(itemNameBtn1).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemCodeBtn1));
		getAction().doubleClick(itemCodeBtn1).build().perform();

		Thread.sleep(2000);
		getAction().moveToElement(quantityBtn2).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(quantityBtn2));
		getAction().doubleClick(quantityBtn2).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rateBtn2));
		getAction().doubleClick(rateBtn2).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionFieldsExpandBtn));
		transactionFieldsExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(extraFieldsExpandBtn));
		extraFieldsExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouseExpandBtn2));
		warehouseExpandBtn2.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouseNameBtn2));
		getAction().doubleClick(warehouseNameBtn2).build().perform();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(finishBtn));
		finishBtn.click();

		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : " + actMessage + " Value Expected : " + expMessage);

		if (actMessage.equalsIgnoreCase(expMessage)) {

			System.out.println(" Test PasS: Report desgining is saved with Document Type ");
			return true;
		} else {
			System.out.println(" Test FAIL: Report desgining is saved with Document Type ");
			return false;
		}

	}

	public boolean checkReportAllProductsByTagDetails() throws InterruptedException {
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
		searchTxt.click();
		searchTxt.sendKeys("All Products By Tag-Details");
		Thread.sleep(1000);
		searchTxt.sendKeys(Keys.ENTER);

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_DateOptionDropdown));

		Select s = new Select(sl_DateOptionDropdown);
		s.selectByValue("1");

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_OkBtn));
		sl_OkBtn.click();

		Thread.sleep(2000);
		boolean novalidationConfirmationMessage = validationConfirmationMessage.getText().isEmpty();

		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";

		Thread.sleep(3000);

		Thread.sleep(1500);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_1stRow1stCol));

		int reportsRow1ListCount = reportCol1List.size();
		ArrayList<String> reportsRow1ListArray = new ArrayList<String>();
		for (int i = 1; i < reportsRow1ListCount; i++) {
			String data = reportCol1List.get(i).getText();

			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			String expadjustBills = df.format(date);

			reportsRow1ListArray.add(data);
		}
		String actRow1List = reportsRow1ListArray.toString();
		String expRow1List = "[2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19]";

		int report2ndRowListCount = reportCol2List.size();
		ArrayList<String> report2ndRowListArray = new ArrayList<String>();
		for (int i = 1; i < report2ndRowListCount; i++) {
			String data = reportCol2List.get(i).getText();
			report2ndRowListArray.add(data);
		}
		String actRow2List = report2ndRowListArray.toString();
		String expRow2List = "[STD RATE COGS ITEM, , , , STD RATE COGS ITEM, , , , , , STD RATE COGS ITEM, , , , STD RATE COGS ITEM, , , ]";

		int report3rdRowListCount = reportCol3List.size();
		ArrayList<String> report3rdRowListArray = new ArrayList<String>();
		for (int i = 1; i < report3rdRowListCount; i++) {
			String data = reportCol3List.get(i).getText();
			report3rdRowListArray.add(data);
		}
		String actRow3List = report3rdRowListArray.toString();
		String expRow3List = "[2.00, , , , 1.00, , , , , , 1.00, , , , 1.00, , , ]";

		int report4thRowListCount = reportCol4List.size();
		ArrayList<String> report4thRowListArray = new ArrayList<String>();
		for (int i = 1; i < report4thRowListCount; i++) {
			String data = reportCol4List.get(i).getText();
			report4thRowListArray.add(data);
		}
		String actRow4List = report4thRowListArray.toString();
		String expRow4List = "[10.00, , , , 5.00, , , , , , 5.00, , , , 5.00, , , ]";

		int reportsRow1ListCount1 = reportCol5List.size();
		ArrayList<String> reportsRow1ListArray1 = new ArrayList<String>();
		for (int i = 1; i < reportsRow1ListCount1; i++) {
			String data = reportCol5List.get(i).getText();

			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			String expadjustBills = df.format(date);

			reportsRow1ListArray1.add(data);
		}
		String actRow1List1 = reportsRow1ListArray1.toString();
		String expRow1List1 = "[HYDERABAD, HYDERABAD, , , HYDERABAD, HYDERABAD, , , HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, , , HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD]";

		System.out.println("actRow1List  : " + actRow1List);
		System.out.println("expRow1List  : " + expRow1List);
		System.out.println("*********************************************************************");

		System.out.println("actRow2List  : " + actRow2List);
		System.out.println("expRow2List  : " + expRow2List);
		System.out.println("*********************************************************************");

		System.out.println("actRow3List  : " + actRow3List);
		System.out.println("expRow3List  : " + expRow3List);
		System.out.println("*********************************************************************");

		System.out.println("actRow4List  : " + actRow4List);
		System.out.println("expRow4List  : " + expRow4List);
		System.out.println("*********************************************************************");

		System.out.println("actRow1List1  : " + actRow1List1);
		System.out.println("expRow1List1  : " + expRow1List1);
		System.out.println("*********************************************************************");

		if (actRow1List.equalsIgnoreCase(expRow1List) && actRow2List.equalsIgnoreCase(expRow2List)
				&& actRow3List.equalsIgnoreCase(expRow3List) && actRow4List.equalsIgnoreCase(expRow4List) &&

				actRow1List1.equalsIgnoreCase(expRow1List1)
				&& actvalidationConfirmationMessage.equalsIgnoreCase(expvalidationConfirmationMessage)) {
			System.out.println(" Test Pass: Values Dsiplayed as Expected ");
			return true;
		} else {
			System.out.println(" Test FAIL: Values Dsiplayed as Expected ");
			String meesage = validationConfirmationMessage.getText();
			System.err.println(" Meesage Displayed  : " + meesage);
			return false;
		}
	}

	public boolean checkSavingRDWithInventoryTransactionsDetailsDetails()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {

		Thread.sleep(1999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(homeMenu));
		homeMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(utilities));
		utilities.click();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportDesignerMenu));
		reportDesignerMenu.click();

		Thread.sleep(2999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportNameDropdown));
		reportNameDropdown.sendKeys("Inventory Transactions-Details");
		reportNameDropdown.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportTypeDropdown));
		Select rtd = new Select(reportTypeDropdown);
		rtd.selectByVisibleText("Details");

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(inventoryExpandBtn));
		inventoryExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportsBtn));
		reportsBtn.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dataSetTab));
		dataSetTab.click();

		Thread.sleep(2000);

		int transactionSetListCount = transactionSetList.size();

		for (int i = 0; i <= transactionSetListCount; i++) {
			String data = transactionSetList.get(i).getText();

			if (data.equalsIgnoreCase("All products by tag")) {
				transactionSetList.get(i).click();

				break;
			}
		}

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTab));
		customizationTab.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionExpandBtn));
		transactionExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionFieldsExpandBtn));
		transactionFieldsExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemExpandBtn));
		itemExpandBtn1.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemNameBtn1));
		getAction().doubleClick(itemNameBtn1).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemCodeBtn1));
		getAction().doubleClick(itemCodeBtn1).build().perform();

		Thread.sleep(2000);
		getAction().moveToElement(quantityBtn2).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(quantityBtn2));
		getAction().doubleClick(quantityBtn2).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rateBtn2));
		getAction().doubleClick(rateBtn2).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionFieldsExpandBtn));
		transactionFieldsExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(extraFieldsExpandBtn));
		extraFieldsExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouseExpandBtn2));
		warehouseExpandBtn2.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouseNameBtn2));
		getAction().doubleClick(warehouseNameBtn2).build().perform();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(finishBtn));
		finishBtn.click();

		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : " + actMessage + " Value Expected : " + expMessage);

		if (actMessage.equalsIgnoreCase(expMessage)) {

			System.out.println(" Test PasS: Report desgining is saved with Document Type ");
			return true;
		} else {
			System.out.println(" Test FAIL: Report desgining is saved with Document Type ");
			return false;
		}

	}

	public boolean checkReportInventoryTransactionsDetails() throws InterruptedException {
		Thread.sleep(2000);
		getDriver().navigate().refresh();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
		searchTxt.click();
		Thread.sleep(1000);
		searchTxt.sendKeys("Inventory Transactions-Details");
		Thread.sleep(1000);
		searchTxt.sendKeys(Keys.ENTER);

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_DateOptionDropdown));

		Select s = new Select(sl_DateOptionDropdown);
		s.selectByValue("1");

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_OkBtn));
		sl_OkBtn.click();

		Thread.sleep(2000);
		boolean novalidationConfirmationMessage = validationConfirmationMessage.getText().isEmpty();

		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";

		Thread.sleep(3000);

		Thread.sleep(1500);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_1stRow1stCol));

		int reportsRow1ListCount = reportCol1List.size();
		ArrayList<String> reportsRow1ListArray = new ArrayList<String>();
		for (int i = 1; i < reportsRow1ListCount; i++) {
			String data = reportCol1List.get(i).getText();

			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			String expadjustBills = df.format(date);

			reportsRow1ListArray.add(data);
		}
		String actRow1List = reportsRow1ListArray.toString();
		String expRow1List = "[2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19]";

		int report2ndRowListCount = reportCol2List.size();
		ArrayList<String> report2ndRowListArray = new ArrayList<String>();
		for (int i = 1; i < report2ndRowListCount; i++) {
			String data = reportCol2List.get(i).getText();
			report2ndRowListArray.add(data);
		}
		String actRow2List = report2ndRowListArray.toString();
		String expRow2List = "[STD RATE COGS ITEM, , , , STD RATE COGS ITEM, , , , , , STD RATE COGS ITEM, , , , STD RATE COGS ITEM, , , ]";

		int report3rdRowListCount = reportCol3List.size();
		ArrayList<String> report3rdRowListArray = new ArrayList<String>();
		for (int i = 1; i < report3rdRowListCount; i++) {
			String data = reportCol3List.get(i).getText();
			report3rdRowListArray.add(data);
		}
		String actRow3List = report3rdRowListArray.toString();
		String expRow3List = "[2.00, , , , 1.00, , , , , , 1.00, , , , 1.00, , , ]";

		int report4thRowListCount = reportCol4List.size();
		ArrayList<String> report4thRowListArray = new ArrayList<String>();
		for (int i = 1; i < report4thRowListCount; i++) {
			String data = reportCol4List.get(i).getText();
			report4thRowListArray.add(data);
		}
		String actRow4List = report4thRowListArray.toString();
		String expRow4List = "[10.00, , , , 5.00, , , , , , 5.00, , , , 5.00, , , ]";

		int reportsRow1ListCount1 = reportCol5List.size();
		ArrayList<String> reportsRow1ListArray1 = new ArrayList<String>();
		for (int i = 1; i < reportsRow1ListCount1; i++) {
			String data = reportCol5List.get(i).getText();

			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			String expadjustBills = df.format(date);

			reportsRow1ListArray1.add(data);
		}
		String actRow1List1 = reportsRow1ListArray1.toString();
		String expRow1List1 = "[HYDERABAD, HYDERABAD, , , HYDERABAD, HYDERABAD, , , HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, , , HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD]";

		System.out.println("actRow1List  : " + actRow1List);
		System.out.println("expRow1List  : " + expRow1List);
		System.out.println("*********************************************************************");

		System.out.println("actRow2List  : " + actRow2List);
		System.out.println("expRow2List  : " + expRow2List);
		System.out.println("*********************************************************************");

		System.out.println("actRow3List  : " + actRow3List);
		System.out.println("expRow3List  : " + expRow3List);
		System.out.println("*********************************************************************");

		System.out.println("actRow4List  : " + actRow4List);
		System.out.println("expRow4List  : " + expRow4List);
		System.out.println("*********************************************************************");

		System.out.println("actRow1List1  : " + actRow1List1);
		System.out.println("expRow1List1  : " + expRow1List1);
		System.out.println("*********************************************************************");

		if (actRow1List.equalsIgnoreCase(expRow1List) && actRow2List.equalsIgnoreCase(expRow2List)
				&& actRow3List.equalsIgnoreCase(expRow3List) && actRow4List.equalsIgnoreCase(expRow4List) &&

				actRow1List1.equalsIgnoreCase(expRow1List1)
				&& actvalidationConfirmationMessage.equalsIgnoreCase(expvalidationConfirmationMessage)) {
			System.out.println(" Test Pass: Values Dsiplayed as Expected ");
			return true;
		} else {
			System.out.println(" Test FAIL: Values Dsiplayed as Expected ");
			String meesage = validationConfirmationMessage.getText();
			System.err.println(" Meesage Displayed  : " + meesage);
			return false;
		}
	}

	public boolean checkSavingRDWithInventoryTransactionsOfProductDetails()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {

		Thread.sleep(1999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(homeMenu));
		homeMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(utilities));
		utilities.click();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportDesignerMenu));
		reportDesignerMenu.click();

		Thread.sleep(2999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportNameDropdown));
		reportNameDropdown.sendKeys("Inventory Transactions of a Product-Details");
		reportNameDropdown.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportTypeDropdown));
		Select rtd = new Select(reportTypeDropdown);
		rtd.selectByVisibleText("Details");

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(inventoryExpandBtn));
		inventoryExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportsBtn));
		reportsBtn.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dataSetTab));
		dataSetTab.click();

		Thread.sleep(2000);

		int transactionSetListCount = transactionSetList.size();

		for (int i = 0; i <= transactionSetListCount; i++) {
			String data = transactionSetList.get(i).getText();

			if (data.equalsIgnoreCase("All products by tag")) {
				transactionSetList.get(i).click();

				break;
			}
		}

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTab));
		customizationTab.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionExpandBtn));
		transactionExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionFieldsExpandBtn));
		transactionFieldsExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemExpandBtn));
		itemExpandBtn1.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemNameBtn1));
		getAction().doubleClick(itemNameBtn1).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemCodeBtn1));
		getAction().doubleClick(itemCodeBtn1).build().perform();

		Thread.sleep(2000);
		getAction().moveToElement(quantityBtn2).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(quantityBtn2));
		getAction().doubleClick(quantityBtn2).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rateBtn2));
		getAction().doubleClick(rateBtn2).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionFieldsExpandBtn));
		transactionFieldsExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(extraFieldsExpandBtn));
		extraFieldsExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouseExpandBtn2));
		warehouseExpandBtn2.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouseNameBtn2));
		getAction().doubleClick(warehouseNameBtn2).build().perform();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(finishBtn));
		finishBtn.click();

		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : " + actMessage + " Value Expected : " + expMessage);

		if (actMessage.equalsIgnoreCase(expMessage)) {

			System.out.println(" Test PasS: Report desgining is saved with Document Type ");
			return true;
		} else {
			System.out.println(" Test FAIL: Report desgining is saved with Document Type ");
			return false;
		}

	}

	public boolean checkReportInventoryTransactionsOfProductDetails() throws InterruptedException {
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
		searchTxt.click();
		Thread.sleep(1000);
		searchTxt.sendKeys("Inventory Transactions of a Product-Details");
		Thread.sleep(1000);
		searchTxt.sendKeys(Keys.ENTER);

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_DateOptionDropdown));

		Select s = new Select(sl_DateOptionDropdown);
		s.selectByValue("1");

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_OkBtn));
		sl_OkBtn.click();

		Thread.sleep(2000);
		boolean novalidationConfirmationMessage = validationConfirmationMessage.getText().isEmpty();

		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";

		Thread.sleep(3000);

		Thread.sleep(1500);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_1stRow1stCol));

		int reportsRow1ListCount = reportCol1List.size();
		ArrayList<String> reportsRow1ListArray = new ArrayList<String>();
		for (int i = 1; i < reportsRow1ListCount; i++) {
			String data = reportCol1List.get(i).getText();

			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			String expadjustBills = df.format(date);

			reportsRow1ListArray.add(data);
		}
		String actRow1List = reportsRow1ListArray.toString();
		String expRow1List = "[2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19]";

		int report2ndRowListCount = reportCol2List.size();
		ArrayList<String> report2ndRowListArray = new ArrayList<String>();
		for (int i = 1; i < report2ndRowListCount; i++) {
			String data = reportCol2List.get(i).getText();
			report2ndRowListArray.add(data);
		}
		String actRow2List = report2ndRowListArray.toString();
		String expRow2List = "[STD RATE COGS ITEM, , , , STD RATE COGS ITEM, , , , , , STD RATE COGS ITEM, , , , STD RATE COGS ITEM, , , ]";

		int report3rdRowListCount = reportCol3List.size();
		ArrayList<String> report3rdRowListArray = new ArrayList<String>();
		for (int i = 1; i < report3rdRowListCount; i++) {
			String data = reportCol3List.get(i).getText();
			report3rdRowListArray.add(data);
		}
		String actRow3List = report3rdRowListArray.toString();
		String expRow3List = "[2.00, , , , 1.00, , , , , , 1.00, , , , 1.00, , , ]";

		int report4thRowListCount = reportCol4List.size();
		ArrayList<String> report4thRowListArray = new ArrayList<String>();
		for (int i = 1; i < report4thRowListCount; i++) {
			String data = reportCol4List.get(i).getText();
			report4thRowListArray.add(data);
		}
		String actRow4List = report4thRowListArray.toString();
		String expRow4List = "[10.00, , , , 5.00, , , , , , 5.00, , , , 5.00, , , ]";

		int reportsRow1ListCount1 = reportCol5List.size();
		ArrayList<String> reportsRow1ListArray1 = new ArrayList<String>();
		for (int i = 1; i < reportsRow1ListCount1; i++) {
			String data = reportCol5List.get(i).getText();

			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			String expadjustBills = df.format(date);

			reportsRow1ListArray1.add(data);
		}
		String actRow1List1 = reportsRow1ListArray1.toString();
		String expRow1List1 = "[HYDERABAD, HYDERABAD, , , HYDERABAD, HYDERABAD, , , HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, , , HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD]";

		System.out.println("actRow1List  : " + actRow1List);
		System.out.println("expRow1List  : " + expRow1List);
		System.out.println("*********************************************************************");

		System.out.println("actRow2List  : " + actRow2List);
		System.out.println("expRow2List  : " + expRow2List);
		System.out.println("*********************************************************************");

		System.out.println("actRow3List  : " + actRow3List);
		System.out.println("expRow3List  : " + expRow3List);
		System.out.println("*********************************************************************");

		System.out.println("actRow4List  : " + actRow4List);
		System.out.println("expRow4List  : " + expRow4List);
		System.out.println("*********************************************************************");

		System.out.println("actRow1List1  : " + actRow1List1);
		System.out.println("expRow1List1  : " + expRow1List1);
		System.out.println("*********************************************************************");

		if (actRow1List.equalsIgnoreCase(expRow1List) && actRow2List.equalsIgnoreCase(expRow2List)
				&& actRow3List.equalsIgnoreCase(expRow3List) && actRow4List.equalsIgnoreCase(expRow4List) &&

				actRow1List1.equalsIgnoreCase(expRow1List1)
				&& actvalidationConfirmationMessage.equalsIgnoreCase(expvalidationConfirmationMessage)) {
			System.out.println(" Test Pass: Values Dsiplayed as Expected ");
			return true;
		} else {
			System.out.println(" Test FAIL: Values Dsiplayed as Expected ");
			String meesage = validationConfirmationMessage.getText();
			System.err.println(" Meesage Displayed  : " + meesage);
			return false;
		}
	}

	public boolean checkSavingRDWithInventoryTransactionsOfAccountingTagDetails()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {

		Thread.sleep(1999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(homeMenu));
		homeMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(utilities));
		utilities.click();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportDesignerMenu));
		reportDesignerMenu.click();

		Thread.sleep(2999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportNameDropdown));
		reportNameDropdown.sendKeys("Inventory Transactions of Accounting Tag-Details");
		reportNameDropdown.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportTypeDropdown));
		Select rtd = new Select(reportTypeDropdown);
		rtd.selectByVisibleText("Details");

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(inventoryExpandBtn));
		inventoryExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportsBtn));
		reportsBtn.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dataSetTab));
		dataSetTab.click();

		Thread.sleep(2000);

		int transactionSetListCount = transactionSetList.size();

		for (int i = 0; i <= transactionSetListCount; i++) {
			String data = transactionSetList.get(i).getText();

			if (data.equalsIgnoreCase("Inventory transactions of accounting tag")) {
				transactionSetList.get(i).click();

				break;
			}
		}

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTab));
		customizationTab.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionExpandBtn));
		transactionExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionFieldsExpandBtn));
		transactionFieldsExpandBtn.click();

		Thread.sleep(2000);
		getAction().moveToElement(docnumberBtn).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(docnumberBtn));
		getAction().doubleClick(docnumberBtn).build().perform();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(acc1ExpandBtn));
		acc1ExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(acc1NameBtn));
		getAction().doubleClick(acc1NameBtn).build().perform();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemExpandBtn));
		itemExpandBtn1.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemNameBtn1));
		getAction().doubleClick(itemNameBtn1).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemCodeBtn1));
		getAction().doubleClick(itemCodeBtn1).build().perform();

		Thread.sleep(2000);
		getAction().moveToElement(quantityBtn2).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(quantityBtn2));
		getAction().doubleClick(quantityBtn2).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rateBtn2));
		getAction().doubleClick(rateBtn2).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionFieldsExpandBtn));
		transactionFieldsExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(extraFieldsExpandBtn));
		extraFieldsExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(deptExpandBtn1));
		deptExpandBtn1.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(deptNameBtn1));
		getAction().doubleClick(deptNameBtn1).build().perform();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(finishBtn));
		finishBtn.click();

		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : " + actMessage + " Value Expected : " + expMessage);

		if (actMessage.equalsIgnoreCase(expMessage)) {

			System.out.println(" Test PasS: Report desgining is saved with Document Type ");
			return true;
		} else {
			System.out.println(" Test FAIL: Report desgining is saved with Document Type ");
			return false;
		}

	}

	public boolean checkReportInventoryTransactionsOFAccountingTagDetails() throws InterruptedException {
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
		searchTxt.click();
		Thread.sleep(1000);
		searchTxt.sendKeys("Inventory Transactions of Accounting Tag-Details");
		Thread.sleep(1000);
		searchTxt.sendKeys(Keys.ENTER);

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_DateOptionDropdown));

		Select s = new Select(sl_DateOptionDropdown);
		s.selectByValue("1");

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportDepartmentTxt));
		reportDepartmentTxt.click();
		reportDepartmentTxt.sendKeys("Duabi");
		Thread.sleep(1000);
		reportDepartmentTxt.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_OkBtn));
		sl_OkBtn.click();

		Thread.sleep(2000);
		boolean novalidationConfirmationMessage = validationConfirmationMessage.getText().isEmpty();

		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";

		Thread.sleep(3000);

		Thread.sleep(1500);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_1stRow1stCol));

		int reportsRow1ListCount = reportCol1List.size();
		ArrayList<String> reportsRow1ListArray = new ArrayList<String>();
		for (int i = 0; i < reportsRow1ListCount; i++) {
			String data = reportCol1List.get(i).getText();

			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			String expadjustBills = df.format(date);

			reportsRow1ListArray.add(data);
		}
		String actRow1List = reportsRow1ListArray.toString();
		String expRow1List = "[1, 2, 3, 4, 5]";

		int report2ndRowListCount = reportCol2List.size();
		ArrayList<String> report2ndRowListArray = new ArrayList<String>();
		for (int i = 0; i < report2ndRowListCount; i++) {
			String data = reportCol2List.get(i).getText();
			report2ndRowListArray.add(data);
		}
		String actRow2List = report2ndRowListArray.toString();
		String expRow2List = "[1, 1, 1, 2, Grand Total]";

		int report3rdRowListCount = reportCol3List.size();
		ArrayList<String> report3rdRowListArray = new ArrayList<String>();
		for (int i = 0; i < report3rdRowListCount; i++) {
			String data = reportCol3List.get(i).getText();
			report3rdRowListArray.add(data);
		}
		String actRow3List = report3rdRowListArray.toString();
		String expRow3List = "[Customer A, Customer A, Vendor New Reference, Vendor B, ]";

		int report4thRowListCount = reportCol4List.size();
		ArrayList<String> report4thRowListArray = new ArrayList<String>();
		for (int i = 0; i < report4thRowListCount; i++) {
			String data = reportCol4List.get(i).getText();
			report4thRowListArray.add(data);
		}
		String actRow4List = report4thRowListArray.toString();
		String expRow4List = "[STD RATE COGS ITEM, STD RATE COGS ITEM, STD RATE COGS ITEM, STD RATE COGS ITEM, ]";

		int reportsRow1ListCount1 = reportCol5List.size();
		ArrayList<String> reportsRow1ListArray1 = new ArrayList<String>();
		for (int i = 0; i < reportsRow1ListCount1; i++) {
			String data = reportCol5List.get(i).getText();

			reportsRow1ListArray1.add(data);
		}
		String actRow1List1 = reportsRow1ListArray1.toString();
		String expRow1List1 = "[2.00, 1.00, 1.00, 1.00, 1.00]";

		int reportsRow1ListCount2 = reportCol6List.size();
		ArrayList<String> reportsRow1ListArray2 = new ArrayList<String>();
		for (int i = 0; i < reportsRow1ListCount2; i++) {
			String data = reportCol6List.get(i).getText();

			reportsRow1ListArray2.add(data);
		}
		String actRow1List2 = reportsRow1ListArray2.toString();
		String expRow1List2 = "[10.00, 5.00, 5.00, 5.00, 25.00]";

		int reportsRow1ListCount3 = reportCol7List.size();
		ArrayList<String> reportsRow1ListArray3 = new ArrayList<String>();
		for (int i = 0; i < reportsRow1ListCount3; i++) {
			String data = reportCol7List.get(i).getText();

			reportsRow1ListArray3.add(data);
		}
		String actRow1List3 = reportsRow1ListArray3.toString();
		String expRow1List3 = "[DUBAI, DUBAI, DUBAI, DUBAI, ]";

		System.out.println("actRow1List  : " + actRow1List);
		System.out.println("expRow1List  : " + expRow1List);
		System.out.println("*********************************************************************");

		System.out.println("actRow2List  : " + actRow2List);
		System.out.println("expRow2List  : " + expRow2List);
		System.out.println("*********************************************************************");

		System.out.println("actRow3List  : " + actRow3List);
		System.out.println("expRow3List  : " + expRow3List);
		System.out.println("*********************************************************************");

		System.out.println("actRow4List  : " + actRow4List);
		System.out.println("expRow4List  : " + expRow4List);
		System.out.println("*********************************************************************");

		System.out.println("actRow1List1  : " + actRow1List1);
		System.out.println("expRow1List1  : " + expRow1List1);
		System.out.println("*********************************************************************");

		System.out.println("actRow1List2  : " + actRow1List2);
		System.out.println("expRow1List2  : " + expRow1List2);
		System.out.println("*********************************************************************");

		System.out.println("actRow1List3  : " + actRow1List3);
		System.out.println("expRow1List3  : " + expRow1List3);
		System.out.println("*********************************************************************");

		if (actRow1List.equalsIgnoreCase(expRow1List) && actRow2List.equalsIgnoreCase(expRow2List)
				&& actRow3List.equalsIgnoreCase(expRow3List) && actRow4List.equalsIgnoreCase(expRow4List) &&

				actRow1List1.equalsIgnoreCase(expRow1List1) && actRow1List2.equalsIgnoreCase(expRow1List2)
				&& actRow1List3.equalsIgnoreCase(expRow1List3)
				&& actvalidationConfirmationMessage.equalsIgnoreCase(expvalidationConfirmationMessage)) {
			System.out.println(" Test Pass: Values Dsiplayed as Expected ");
			return true;
		} else {
			System.out.println(" Test FAIL: Values Dsiplayed as Expected ");
			String meesage = validationConfirmationMessage.getText();
			System.err.println(" Meesage Displayed  : " + meesage);
			return false;
		}
	}

	public boolean checkSavingRDWithInventoryTransactionsOfInventoryTagDetails()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {

		Thread.sleep(1999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(homeMenu));
		homeMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(utilities));
		utilities.click();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportDesignerMenu));
		reportDesignerMenu.click();

		Thread.sleep(2999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportNameDropdown));
		reportNameDropdown.sendKeys("Inventory Transactions of Inventory Tag-Details");
		reportNameDropdown.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportTypeDropdown));
		Select rtd = new Select(reportTypeDropdown);
		rtd.selectByVisibleText("Details");

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(inventoryExpandBtn));
		inventoryExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportsBtn));
		reportsBtn.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dataSetTab));
		dataSetTab.click();

		Thread.sleep(2000);

		int transactionSetListCount = transactionSetList.size();

		for (int i = 0; i <= transactionSetListCount; i++) {
			String data = transactionSetList.get(i).getText();

			if (data.equalsIgnoreCase("Inventory transactions of inventory tag")) {
				transactionSetList.get(i).click();

				break;
			}
		}

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTab));
		customizationTab.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionExpandBtn));
		transactionExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionFieldsExpandBtn));
		transactionFieldsExpandBtn.click();

		Thread.sleep(2000);
		getAction().moveToElement(docnumberBtn).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(docnumberBtn));
		getAction().doubleClick(docnumberBtn).build().perform();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(acc1ExpandBtn));
		acc1ExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(acc1NameBtn));
		getAction().doubleClick(acc1NameBtn).build().perform();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemExpandBtn));
		itemExpandBtn1.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemNameBtn1));
		getAction().doubleClick(itemNameBtn1).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemCodeBtn1));
		getAction().doubleClick(itemCodeBtn1).build().perform();

		Thread.sleep(2000);
		getAction().moveToElement(quantityBtn2).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(quantityBtn2));
		getAction().doubleClick(quantityBtn2).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rateBtn2));
		getAction().doubleClick(rateBtn2).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionFieldsExpandBtn));
		transactionFieldsExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(extraFieldsExpandBtn));
		extraFieldsExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouseExpandBtn3));
		warehouseExpandBtn3.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouseNameBtn3));
		getAction().doubleClick(warehouseNameBtn3).build().perform();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(finishBtn));
		finishBtn.click();

		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : " + actMessage + " Value Expected : " + expMessage);

		if (actMessage.equalsIgnoreCase(expMessage)) {

			System.out.println(" Test PasS: Report desgining is saved with Document Type ");
			return true;
		} else {
			System.out.println(" Test FAIL: Report desgining is saved with Document Type ");
			return false;
		}

	}

	public boolean checkReportInventoryTransactionsOFInventoryTagDetails() throws InterruptedException {

		Thread.sleep(2000);
		getDriver().navigate().refresh();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
		searchTxt.click();
		Thread.sleep(1000);
		searchTxt.sendKeys("Inventory Transactions of Inventory Tag-Details");
		Thread.sleep(1000);
		searchTxt.sendKeys(Keys.ENTER);

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_DateOptionDropdown));

		Select s = new Select(sl_DateOptionDropdown);
		s.selectByValue("1");

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportDepartmentTxt));
		reportDepartmentTxt.click();
		reportDepartmentTxt.sendKeys("HYDERABAD");
		Thread.sleep(1000);
		reportDepartmentTxt.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_OkBtn));
		sl_OkBtn.click();

		Thread.sleep(2000);
		boolean novalidationConfirmationMessage = validationConfirmationMessage.getText().isEmpty();

		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";

		Thread.sleep(3000);

		Thread.sleep(1500);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_1stRow1stCol));

		int reportsRow1ListCount = reportCol1List.size();
		ArrayList<String> reportsRow1ListArray = new ArrayList<String>();
		for (int i = 0; i < reportsRow1ListCount; i++) {
			String data = reportCol1List.get(i).getText();

			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			String expadjustBills = df.format(date);

			reportsRow1ListArray.add(data);
		}
		String actRow1List = reportsRow1ListArray.toString();
		String expRow1List = "[1, 2, 3, 4, 5]";

		int report2ndRowListCount = reportCol2List.size();
		ArrayList<String> report2ndRowListArray = new ArrayList<String>();
		for (int i = 0; i < report2ndRowListCount; i++) {
			String data = reportCol2List.get(i).getText();
			report2ndRowListArray.add(data);
		}
		String actRow2List = report2ndRowListArray.toString();
		String expRow2List = "[1, 1, 1, 2, Grand Total]";

		int report3rdRowListCount = reportCol3List.size();
		ArrayList<String> report3rdRowListArray = new ArrayList<String>();
		for (int i = 0; i < report3rdRowListCount; i++) {
			String data = reportCol3List.get(i).getText();
			report3rdRowListArray.add(data);
		}
		String actRow3List = report3rdRowListArray.toString();
		String expRow3List = "[Customer A, Customer A, Vendor New Reference, Vendor B, ]";

		int report4thRowListCount = reportCol4List.size();
		ArrayList<String> report4thRowListArray = new ArrayList<String>();
		for (int i = 0; i < report4thRowListCount; i++) {
			String data = reportCol4List.get(i).getText();
			report4thRowListArray.add(data);
		}
		String actRow4List = report4thRowListArray.toString();
		String expRow4List = "[STD RATE COGS ITEM, STD RATE COGS ITEM, STD RATE COGS ITEM, STD RATE COGS ITEM, ]";

		int reportsRow1ListCount1 = reportCol5List.size();
		ArrayList<String> reportsRow1ListArray1 = new ArrayList<String>();
		for (int i = 0; i < reportsRow1ListCount1; i++) {
			String data = reportCol5List.get(i).getText();

			reportsRow1ListArray1.add(data);
		}
		String actRow1List1 = reportsRow1ListArray1.toString();
		String expRow1List1 = "[2.00, 1.00, 1.00, 1.00, 1.00]";

		int reportsRow1ListCount2 = reportCol6List.size();
		ArrayList<String> reportsRow1ListArray2 = new ArrayList<String>();
		for (int i = 0; i < reportsRow1ListCount2; i++) {
			String data = reportCol6List.get(i).getText();

			reportsRow1ListArray2.add(data);
		}
		String actRow1List2 = reportsRow1ListArray2.toString();
		String expRow1List2 = "[10.00, 5.00, 5.00, 5.00, 25.00]";

		int reportsRow1ListCount3 = reportCol7List.size();
		ArrayList<String> reportsRow1ListArray3 = new ArrayList<String>();
		for (int i = 0; i < reportsRow1ListCount3; i++) {
			String data = reportCol7List.get(i).getText();

			reportsRow1ListArray3.add(data);
		}
		String actRow1List3 = reportsRow1ListArray3.toString();
		String expRow1List3 = "[HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, ]";

		System.out.println("actRow1List  : " + actRow1List);
		System.out.println("expRow1List  : " + expRow1List);
		System.out.println("*********************************************************************");

		System.out.println("actRow2List  : " + actRow2List);
		System.out.println("expRow2List  : " + expRow2List);
		System.out.println("*********************************************************************");

		System.out.println("actRow3List  : " + actRow3List);
		System.out.println("expRow3List  : " + expRow3List);
		System.out.println("*********************************************************************");

		System.out.println("actRow4List  : " + actRow4List);
		System.out.println("expRow4List  : " + expRow4List);
		System.out.println("*********************************************************************");

		System.out.println("actRow1List1  : " + actRow1List1);
		System.out.println("expRow1List1  : " + expRow1List1);
		System.out.println("*********************************************************************");

		System.out.println("actRow1List2  : " + actRow1List2);
		System.out.println("expRow1List2  : " + expRow1List2);
		System.out.println("*********************************************************************");

		System.out.println("actRow1List3  : " + actRow1List3);
		System.out.println("expRow1List3  : " + expRow1List3);
		System.out.println("*********************************************************************");

		if (actRow1List.equalsIgnoreCase(expRow1List) && actRow2List.equalsIgnoreCase(expRow2List)
				&& actRow3List.equalsIgnoreCase(expRow3List) && actRow4List.equalsIgnoreCase(expRow4List) &&

				actRow1List1.equalsIgnoreCase(expRow1List1) && actRow1List2.equalsIgnoreCase(expRow1List2)
				&& actRow1List3.equalsIgnoreCase(expRow1List3)
				&& actvalidationConfirmationMessage.equalsIgnoreCase(expvalidationConfirmationMessage)) {
			System.out.println(" Test Pass: Values Dsiplayed as Expected ");
			return true;
		} else {
			System.out.println(" Test FAIL: Values Dsiplayed as Expected ");
			String meesage = validationConfirmationMessage.getText();
			System.err.println(" Meesage Displayed  : " + meesage);
			return false;
		}
	}

	@FindBy(xpath = "//*[@id='advanceEngine']")
	private static WebElement rdReportsAdvanceEngineChkbox;

	public boolean checkSavingRDWithInventoryTransactionsOfTagDetails()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {

		Thread.sleep(1999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(homeMenu));
		homeMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(utilities));
		utilities.click();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportDesignerMenu));
		reportDesignerMenu.click();

		Thread.sleep(2999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportNameDropdown));
		reportNameDropdown.sendKeys("Inventory Transactions of a Tag-Details");
		reportNameDropdown.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportTypeDropdown));
		Select rtd = new Select(reportTypeDropdown);
		rtd.selectByVisibleText("Details");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdReportsAdvanceEngineChkbox));
		rdReportsAdvanceEngineChkbox.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(inventoryExpandBtn));
		inventoryExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportsBtn));
		reportsBtn.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dataSetTab));
		dataSetTab.click();

		Thread.sleep(2000);

		int transactionSetListCount = transactionSetList.size();

		for (int i = 0; i <= transactionSetListCount; i++) {
			String data = transactionSetList.get(i).getText();

			if (data.equalsIgnoreCase("Inventory transactions of a tag")) {
				transactionSetList.get(i).click();

				break;
			}
		}

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTab));
		customizationTab.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionExpandBtn));
		transactionExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionFieldsExpandBtn));
		transactionFieldsExpandBtn.click();

		Thread.sleep(2000);
		getAction().moveToElement(docnumberBtn).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(docnumberBtn));
		getAction().doubleClick(docnumberBtn).build().perform();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(acc1ExpandBtn));
		acc1ExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(acc1NameBtn));
		getAction().doubleClick(acc1NameBtn).build().perform();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemExpandBtn));
		itemExpandBtn1.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemNameBtn1));
		getAction().doubleClick(itemNameBtn1).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemCodeBtn1));
		getAction().doubleClick(itemCodeBtn1).build().perform();

		Thread.sleep(2000);
		getAction().moveToElement(quantityBtn2).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(quantityBtn2));
		getAction().doubleClick(quantityBtn2).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rateBtn2));
		getAction().doubleClick(rateBtn2).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionFieldsExpandBtn));
		transactionFieldsExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(extraFieldsExpandBtn));
		extraFieldsExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouseExpandBtn3));
		warehouseExpandBtn3.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouseNameBtn3));
		getAction().doubleClick(warehouseNameBtn3).build().perform();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(finishBtn));
		finishBtn.click();

		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : " + actMessage + " Value Expected : " + expMessage);

		if (actMessage.equalsIgnoreCase(expMessage)) {

			System.out.println(" Test PasS: Report desgining is saved with Document Type ");
			return true;
		} else {
			System.out.println(" Test FAIL: Report desgining is saved with Document Type ");
			return false;
		}

	}

	public boolean checkReportInventoryTransactionsOFTagDetails() throws InterruptedException {

		getDriver().navigate().refresh();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
		searchTxt.click();
		Thread.sleep(1000);
		searchTxt.sendKeys("Inventory Transactions of a Tag-Details");
		Thread.sleep(1000);
		searchTxt.sendKeys(Keys.ENTER);

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_DateOptionDropdown));

		Select s = new Select(sl_DateOptionDropdown);
		s.selectByValue("1");

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_OkBtn));
		sl_OkBtn.click();

		Thread.sleep(2000);
		boolean novalidationConfirmationMessage = validationConfirmationMessage.getText().isEmpty();

		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";

		Thread.sleep(1500);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_1stRow1stCol));

		int reportsRow1ListCount = reportCol1List.size();
		ArrayList<String> reportsRow1ListArray = new ArrayList<String>();
		for (int i = 0; i < reportsRow1ListCount; i++) {
			String data = reportCol1List.get(i).getText();

			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			String expadjustBills = df.format(date);

			reportsRow1ListArray.add(data);
		}
		String actRow1List = reportsRow1ListArray.toString();
		String expRow1List = "[1, 2, 3, 4, 5]";

		int report2ndRowListCount = reportCol2List.size();
		ArrayList<String> report2ndRowListArray = new ArrayList<String>();
		for (int i = 0; i < report2ndRowListCount; i++) {
			String data = reportCol2List.get(i).getText();
			report2ndRowListArray.add(data);
		}
		String actRow2List = report2ndRowListArray.toString();
		String expRow2List = "[1, 1, 1, 2, Grand Total]";

		int report3rdRowListCount = reportCol3List.size();
		ArrayList<String> report3rdRowListArray = new ArrayList<String>();
		for (int i = 0; i < report3rdRowListCount; i++) {
			String data = reportCol3List.get(i).getText();
			report3rdRowListArray.add(data);
		}
		String actRow3List = report3rdRowListArray.toString();
		String expRow3List = "[Customer A, Customer A, Vendor New Reference, Vendor B, ]";

		int report4thRowListCount = reportCol4List.size();
		ArrayList<String> report4thRowListArray = new ArrayList<String>();
		for (int i = 0; i < report4thRowListCount; i++) {
			String data = reportCol4List.get(i).getText();
			report4thRowListArray.add(data);
		}
		String actRow4List = report4thRowListArray.toString();
		String expRow4List = "[STD RATE COGS ITEM, STD RATE COGS ITEM, STD RATE COGS ITEM, STD RATE COGS ITEM, ]";

		int reportsRow1ListCount1 = reportCol5List.size();
		ArrayList<String> reportsRow1ListArray1 = new ArrayList<String>();
		for (int i = 0; i < reportsRow1ListCount1; i++) {
			String data = reportCol5List.get(i).getText();

			reportsRow1ListArray1.add(data);
		}
		String actRow1List1 = reportsRow1ListArray1.toString();
		String expRow1List1 = "[2.00, 1.00, 1.00, 1.00, 1.00]";

		int reportsRow1ListCount2 = reportCol6List.size();
		ArrayList<String> reportsRow1ListArray2 = new ArrayList<String>();
		for (int i = 0; i < reportsRow1ListCount2; i++) {
			String data = reportCol6List.get(i).getText();

			reportsRow1ListArray2.add(data);
		}
		String actRow1List2 = reportsRow1ListArray2.toString();
		String expRow1List2 = "[10.00, 5.00, 5.00, 5.00, 25.00]";

		int reportsRow1ListCount3 = reportCol7List.size();
		ArrayList<String> reportsRow1ListArray3 = new ArrayList<String>();
		for (int i = 0; i < reportsRow1ListCount3; i++) {
			String data = reportCol7List.get(i).getText();

			reportsRow1ListArray3.add(data);
		}
		String actRow1List3 = reportsRow1ListArray3.toString();
		String expRow1List3 = "[HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, ]";

		System.out.println("actRow1List  : " + actRow1List);
		System.out.println("expRow1List  : " + expRow1List);
		System.out.println("*********************************************************************");

		System.out.println("actRow2List  : " + actRow2List);
		System.out.println("expRow2List  : " + expRow2List);
		System.out.println("*********************************************************************");

		System.out.println("actRow3List  : " + actRow3List);
		System.out.println("expRow3List  : " + expRow3List);
		System.out.println("*********************************************************************");

		System.out.println("actRow4List  : " + actRow4List);
		System.out.println("expRow4List  : " + expRow4List);
		System.out.println("*********************************************************************");

		System.out.println("actRow1List1  : " + actRow1List1);
		System.out.println("expRow1List1  : " + expRow1List1);
		System.out.println("*********************************************************************");

		System.out.println("actRow1List2  : " + actRow1List2);
		System.out.println("expRow1List2  : " + expRow1List2);
		System.out.println("*********************************************************************");

		System.out.println("actRow1List3  : " + actRow1List3);
		System.out.println("expRow1List3  : " + expRow1List3);
		System.out.println("*********************************************************************");

		if (actRow1List.equalsIgnoreCase(expRow1List) && actRow2List.equalsIgnoreCase(expRow2List)
				&& actRow3List.equalsIgnoreCase(expRow3List) && actRow4List.equalsIgnoreCase(expRow4List) &&

				actRow1List1.equalsIgnoreCase(expRow1List1) && actRow1List2.equalsIgnoreCase(expRow1List2)
				&& actRow1List3.equalsIgnoreCase(expRow1List3)
				&& actvalidationConfirmationMessage.equalsIgnoreCase(expvalidationConfirmationMessage)) {
			System.out.println(" Test Pass: Values Displayed With Department as Expected ");
			return true;
		} else {
			System.out.println(" Test FAIL: Values Displayed With Department as Expected ");
			String meesage = validationConfirmationMessage.getText();
			System.err.println(" Meesage Displayed  : " + meesage);

			getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportCloseBtn));
			reportCloseBtn.click();

			getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportDepartmentTxt));
			reportDepartmentTxt.click();
			reportDepartmentTxt.sendKeys("Dubai");
			Thread.sleep(1000);
			reportDepartmentTxt.sendKeys(Keys.TAB);

			getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_OkBtn));
			sl_OkBtn.click();

			Thread.sleep(2000);
			boolean novalidationConfirmationMessage1 = validationConfirmationMessage.getText().isEmpty();

			String actvalidationConfirmationMessage1 = Boolean.toString(novalidationConfirmationMessage);
			String expvalidationConfirmationMessage1 = "true";

			Thread.sleep(1500);
			getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_1stRow1stCol));

			int reportsRow1ListCount01 = reportCol1List.size();
			ArrayList<String> reportsRow1ListArray01 = new ArrayList<String>();
			for (int i = 0; i < reportsRow1ListCount; i++) {
				String data = reportCol1List.get(i).getText();

				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				Date date = new Date();
				String expadjustBills = df.format(date);

				reportsRow1ListArray.add(data);
			}
			String actRow1List01 = reportsRow1ListArray.toString();
			String expRow1List01 = "[1, 2, 3, 4, 5]";

			System.out.println(" Row1 List : Actual  " + actRow1List01);
			System.out.println(" Row1 List : Exp     " + expRow1List01);

			if (actRow1List01.equalsIgnoreCase(expRow1List01)) {
				return true;
			} else {
				String errormessage = errorMessage.getText();
				System.out.println(" ERROR MESSAGE DISPLAYED : " + errormessage);
				return false;
			}

		}
	}

	@FindBy(xpath = "//*[@id='REPORTRENDERNEWControls']/ul/li/span[12]")
	private static WebElement reportCloseBtn;

	public boolean checkSavingRDWithInventoryTransactionsOfSelectedAccountDetails()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {

		Thread.sleep(1999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(homeMenu));
		homeMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(utilities));
		utilities.click();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportDesignerMenu));
		reportDesignerMenu.click();

		Thread.sleep(2999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportNameDropdown));
		reportNameDropdown.sendKeys("Inventory Transactions of Selected Product-Details");
		reportNameDropdown.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportTypeDropdown));
		Select rtd = new Select(reportTypeDropdown);
		rtd.selectByVisibleText("Details");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdReportsAdvanceEngineChkbox));
		rdReportsAdvanceEngineChkbox.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(inventoryExpandBtn));
		inventoryExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportsBtn));
		reportsBtn.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dataSetTab));
		dataSetTab.click();

		Thread.sleep(2000);

		int transactionSetListCount = transactionSetList.size();

		for (int i = 0; i <= transactionSetListCount; i++) {
			String data = transactionSetList.get(i).getText();

			if (data.equalsIgnoreCase("Inventory transactions of selected product")) {
				transactionSetList.get(i).click();

				break;
			}
		}

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTab));
		customizationTab.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionExpandBtn));
		transactionExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionFieldsExpandBtn));
		transactionFieldsExpandBtn.click();

		Thread.sleep(2000);
		getAction().moveToElement(docnumberBtn).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(docnumberBtn));
		getAction().doubleClick(docnumberBtn).build().perform();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(acc1ExpandBtn));
		acc1ExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(acc1NameBtn));
		getAction().doubleClick(acc1NameBtn).build().perform();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemExpandBtn));
		itemExpandBtn1.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemNameBtn1));
		getAction().doubleClick(itemNameBtn1).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemCodeBtn1));
		getAction().doubleClick(itemCodeBtn1).build().perform();

		Thread.sleep(2000);
		getAction().moveToElement(quantityBtn2).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(quantityBtn2));
		getAction().doubleClick(quantityBtn2).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rateBtn2));
		getAction().doubleClick(rateBtn2).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionFieldsExpandBtn));
		transactionFieldsExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(extraFieldsExpandBtn));
		extraFieldsExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouseExpandBtn3));
		warehouseExpandBtn3.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouseNameBtn3));
		getAction().doubleClick(warehouseNameBtn3).build().perform();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(finishBtn));
		finishBtn.click();

		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : " + actMessage + " Value Expected : " + expMessage);

		if (actMessage.equalsIgnoreCase(expMessage)) {

			System.out.println(" Test PasS: Report desgining is saved with Document Type ");
			return true;
		} else {
			System.out.println(" Test FAIL: Report desgining is saved with Document Type ");
			return false;
		}

	}

	public boolean checkReportInventoryTransactionsSelectedAccountDetails() throws InterruptedException {
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
		searchTxt.click();
		Thread.sleep(1000);
		searchTxt.sendKeys("Inventory Transactions of Selected Product-Details");
		Thread.sleep(1000);
		searchTxt.sendKeys(Keys.ENTER);

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_DateOptionDropdown));

		Select s = new Select(sl_DateOptionDropdown);
		s.selectByValue("1");

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_SelectAllItemsChkBox));
		sl_SelectAllItemsChkBox.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_OkBtn));
		sl_OkBtn.click();

		Thread.sleep(2000);
		boolean novalidationConfirmationMessage = validationConfirmationMessage.getText().isEmpty();

		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";

		Thread.sleep(1500);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_1stRow1stCol));

		int reportsRow1ListCount = reportCol1List.size();
		ArrayList<String> reportsRow1ListArray = new ArrayList<String>();
		for (int i = 0; i < reportsRow1ListCount; i++) {
			String data = reportCol1List.get(i).getText();

			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			String expadjustBills = df.format(date);

			reportsRow1ListArray.add(data);
		}
		String actRow1List = reportsRow1ListArray.toString();
		String expRow1List = "[1, 2, 3, 4, 5]";

		int report2ndRowListCount = reportCol2List.size();
		ArrayList<String> report2ndRowListArray = new ArrayList<String>();
		for (int i = 0; i < report2ndRowListCount; i++) {
			String data = reportCol2List.get(i).getText();
			report2ndRowListArray.add(data);
		}
		String actRow2List = report2ndRowListArray.toString();
		String expRow2List = "[1, 1, 1, 2, Grand Total]";

		int report3rdRowListCount = reportCol3List.size();
		ArrayList<String> report3rdRowListArray = new ArrayList<String>();
		for (int i = 0; i < report3rdRowListCount; i++) {
			String data = reportCol3List.get(i).getText();
			report3rdRowListArray.add(data);
		}
		String actRow3List = report3rdRowListArray.toString();
		String expRow3List = "[Customer A, Customer A, Vendor New Reference, Vendor B, ]";

		int report4thRowListCount = reportCol4List.size();
		ArrayList<String> report4thRowListArray = new ArrayList<String>();
		for (int i = 0; i < report4thRowListCount; i++) {
			String data = reportCol4List.get(i).getText();
			report4thRowListArray.add(data);
		}
		String actRow4List = report4thRowListArray.toString();
		String expRow4List = "[STD RATE COGS ITEM, STD RATE COGS ITEM, STD RATE COGS ITEM, STD RATE COGS ITEM, ]";

		int reportsRow1ListCount1 = reportCol5List.size();
		ArrayList<String> reportsRow1ListArray1 = new ArrayList<String>();
		for (int i = 0; i < reportsRow1ListCount1; i++) {
			String data = reportCol5List.get(i).getText();

			reportsRow1ListArray1.add(data);
		}
		String actRow1List1 = reportsRow1ListArray1.toString();
		String expRow1List1 = "[2.00, 1.00, 1.00, 1.00, 1.00]";

		int reportsRow1ListCount2 = reportCol6List.size();
		ArrayList<String> reportsRow1ListArray2 = new ArrayList<String>();
		for (int i = 0; i < reportsRow1ListCount2; i++) {
			String data = reportCol6List.get(i).getText();

			reportsRow1ListArray2.add(data);
		}
		String actRow1List2 = reportsRow1ListArray2.toString();
		String expRow1List2 = "[10.00, 5.00, 5.00, 5.00, 25.00]";

		int reportsRow1ListCount3 = reportCol7List.size();
		ArrayList<String> reportsRow1ListArray3 = new ArrayList<String>();
		for (int i = 0; i < reportsRow1ListCount3; i++) {
			String data = reportCol7List.get(i).getText();

			reportsRow1ListArray3.add(data);
		}
		String actRow1List3 = reportsRow1ListArray3.toString();
		String expRow1List3 = "[HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, ]";

		System.out.println("actRow1List  : " + actRow1List);
		System.out.println("expRow1List  : " + expRow1List);
		System.out.println("*********************************************************************");

		System.out.println("actRow2List  : " + actRow2List);
		System.out.println("expRow2List  : " + expRow2List);
		System.out.println("*********************************************************************");

		System.out.println("actRow3List  : " + actRow3List);
		System.out.println("expRow3List  : " + expRow3List);
		System.out.println("*********************************************************************");

		System.out.println("actRow4List  : " + actRow4List);
		System.out.println("expRow4List  : " + expRow4List);
		System.out.println("*********************************************************************");

		System.out.println("actRow1List1  : " + actRow1List1);
		System.out.println("expRow1List1  : " + expRow1List1);
		System.out.println("*********************************************************************");

		System.out.println("actRow1List2  : " + actRow1List2);
		System.out.println("expRow1List2  : " + expRow1List2);
		System.out.println("*********************************************************************");

		System.out.println("actRow1List3  : " + actRow1List3);
		System.out.println("expRow1List3  : " + expRow1List3);
		System.out.println("*********************************************************************");

		if (actRow1List.equalsIgnoreCase(expRow1List) && actRow2List.equalsIgnoreCase(expRow2List)
				&& actRow3List.equalsIgnoreCase(expRow3List) && actRow4List.equalsIgnoreCase(expRow4List) &&

				actRow1List1.equalsIgnoreCase(expRow1List1) && actRow1List2.equalsIgnoreCase(expRow1List2)
				&& actRow1List3.equalsIgnoreCase(expRow1List3)
				&& actvalidationConfirmationMessage.equalsIgnoreCase(expvalidationConfirmationMessage)) {
			System.out.println(" Test Pass: Values Dsiplayed as Expected ");
			return true;
		} else {
			System.out.println(" Test FAIL: Values Dsiplayed as Expected ");
			String meesage = validationConfirmationMessage.getText();
			System.err.println(" Meesage Displayed  : " + meesage);
			return false;
		}
	}

	@FindBy(xpath = "//*[@id='60']/div")
	private static WebElement financialsMenu;

	@FindBy(xpath = "//*[@id='61']/span")
	private static WebElement financialsTransactionMenu;

	@FindBy(xpath = "//*[@id='81']/span")
	private static WebElement financialsReportsMenu;

	@FindBy(xpath = "//*[@id='500']/span")
	private static WebElement ledger;

	public boolean checkSavingReportThroughAnalysisInLedgerReport()
			throws InterruptedException, IOException, EncryptedDocumentException, InvalidFormatException {

		getDriver().navigate().refresh();

		Thread.sleep(1999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(financialsMenu));
		financialsMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(financialsReportsMenu));
		financialsReportsMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(ledger));
		ledger.click();

		boolean novalidationConfirmationMessage = validationConfirmationMessage.getText().isEmpty();

		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";

		Thread.sleep(3000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_DateOptionDropdown));
		sl_DateOptionDropdown.click();
		Select s = new Select(sl_DateOptionDropdown);
		s.selectByValue("1");

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_SelectAllItemsChkBox));
		sl_SelectAllItemsChkBox.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_OkBtn));
		sl_OkBtn.click();

		Thread.sleep(15000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_AnalysisBtn));
		sl_AnalysisBtn.click();

		Thread.sleep(9999);

		Runtime.getRuntime().exec(getBaseDir() + "\\autoIt\\scripts\\LedgerUpdated.exe");

		Thread.sleep(50000);

		int analyzeReportCol2ListCount = analyzeReportCol2List.size();

		ArrayList<String> analyzeReportCol2ListArray = new ArrayList<>();
		for (int i = 0; i < analyzeReportCol2ListCount; i++) {
			String data = analyzeReportCol2List.get(i).getText();
			analyzeReportCol2ListArray.add(data);
		}

		String actanalyzeReportCol2List = analyzeReportCol2ListArray.toString();
		String expanalyzeReportCol2List = "[, Bank, COGS POSTING ACC, Customer A, Customer New Reference, Customer Semi Adjustment, Journal Entries Control A/C, Opening Balance, Sales - Computers, SR COGS POSTING ACC, STD RATE COGS ACC INV, Vendor B, Vendor Full Adjustment, Vendor New Reference, Vendor Semi Adjustment, Grand Total]";

		System.out.println(" ***********************************");
		System.out.println("analyzeReportCol2List act : " + actanalyzeReportCol2List);
		System.out.println("analyzeReportCol2List exp : " + expanalyzeReportCol2List);

		int analyzeReportCol3ListCount = analyzeReportCol3List.size();
		ArrayList<String> analyzeReportCol3ListArray = new ArrayList<>();
		for (int i = 0; i < analyzeReportCol3ListCount; i++) {
			String data = analyzeReportCol3List.get(i).getText();
			analyzeReportCol3ListArray.add(data);
		}

		String actanalyzeReportCol3List = analyzeReportCol3ListArray.toString();
		String expanalyzeReportCol3List = "[, 40.00, , 20.00, , 15.00, 10.00, 20.00, 20.00, 120.00, 20.00, 120.25, , 125.00, 5.00, 515.25]";

		System.out.println(" ***********************************");
		System.out.println("analyzeReportCol3List act : " + actanalyzeReportCol3List);
		System.out.println("analyzeReportCol3List exp : " + expanalyzeReportCol3List);

		int analyzeReportCol4ListCount = analyzeReportCol4List.size();

		ArrayList<String> analyzeReportCol4ListArray = new ArrayList<>();
		for (int i = 0; i < analyzeReportCol4ListCount; i++) {
			String data = analyzeReportCol4List.get(i).getText();
			analyzeReportCol4ListArray.add(data);
		}

		String actanalyzeReportCol4List = analyzeReportCol4ListArray.toString();
		String expanalyzeReportCol4List = "[230.00, 29.75, 20.00, 20.00, 5.00, 15.00, 10.00, 20.00, 5.00, , 130.50, , 10.00, 10.00, 10.00, 515.25]";

		System.out.println(" ***********************************");
		System.out.println("analyzeReportCol4List act : " + actanalyzeReportCol4List);
		System.out.println("analyzeReportCol4List exp : " + expanalyzeReportCol4List);

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_LA_saveBtn));
		sl_LA_saveBtn.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_LA_repotNameTxt));
		sl_LA_repotNameTxt.click();
		sl_LA_repotNameTxt.sendKeys("LedgerAnalysisReport");
		Thread.sleep(2000);
		sl_LA_repotNameTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_LA_repotFinanicalMenu));
		sl_LA_repotFinanicalMenu.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_LA_repotSaveBtn));
		sl_LA_repotSaveBtn.click();

		String ExpMessage = "Analyze Report Saved Successfully";
		String actMessage = checkValidationMessage(ExpMessage);

		if (actanalyzeReportCol2List.equalsIgnoreCase(expanalyzeReportCol2List)
				&& actanalyzeReportCol2List.equalsIgnoreCase(expanalyzeReportCol2List)
				&& actanalyzeReportCol2List.equalsIgnoreCase(expanalyzeReportCol2List)
				&& actMessage.equalsIgnoreCase(ExpMessage)) {

			System.out.println(" Test Pass:  Report Saved Successfully");
			return true;
		} else {
			System.out.println(" Test Fail:  Report Saved Successfully");
			return true;

		}

	}

	public boolean checkLedgerAnalysisreport() throws InterruptedException {

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
		searchTxt.click();
		Thread.sleep(1000);
		searchTxt.sendKeys("LedgerAnalysisReport");
		Thread.sleep(1000);
		searchTxt.sendKeys(Keys.ENTER);

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_DateOptionDropdown));

		Select s = new Select(sl_DateOptionDropdown);
		s.selectByValue("1");

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_SelectAllItemsChkBox));
		sl_SelectAllItemsChkBox.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_OkBtn));
		sl_OkBtn.click();

		Thread.sleep(2000);
		boolean novalidationConfirmationMessage = validationConfirmationMessage.getText().isEmpty();

		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";

		Thread.sleep(1500);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_1stRow1stCol));

		int reportsRow1ListCount = reportCol1List.size();
		ArrayList<String> reportsRow1ListArray = new ArrayList<String>();
		for (int i = 0; i < reportsRow1ListCount; i++) {
			String data = reportCol1List.get(i).getText();

			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			String expadjustBills = df.format(date);

			reportsRow1ListArray.add(data);
		}
		String actRow1List = reportsRow1ListArray.toString();
		String expRow1List = "[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18]";

		int report2ndRowListCount = reportCol2List.size();
		ArrayList<String> report2ndRowListArray = new ArrayList<String>();
		for (int i = 0; i < report2ndRowListCount; i++) {
			String data = reportCol2List.get(i).getText();
			report2ndRowListArray.add(data);
		}
		String actRow2List = report2ndRowListArray.toString();
		String expRow2List = "[, Bank, COGS POSTING ACC, Customer A, Customer New Reference, Customer Semi Adjustment, HDFC, Journal Entries Control A/C, Opening Balance, Purchase, Sales - Computers, SR COGS POSTING ACC, STD RATE COGS ACC INV, Vendor B, Vendor Full Adjustment, Vendor New Reference, Vendor Semi Adjustment, Grand Total]";

		int report3rdRowListCount = reportCol3List.size();
		ArrayList<String> report3rdRowListArray = new ArrayList<String>();
		for (int i = 0; i < report3rdRowListCount; i++) {
			String data = reportCol3List.get(i).getText();
			report3rdRowListArray.add(data);
		}
		String actRow3List = report3rdRowListArray.toString();
		String expRow3List = "[, 40.00, , 20.00, , 15.00, 120.00, 10.00, 20.00, , 20.00, 120.00, 20.00, 120.25, , 125.00, 5.00, 635.25]";

		int report4thRowListCount = reportCol4List.size();
		ArrayList<String> report4thRowListArray = new ArrayList<String>();
		for (int i = 0; i < report4thRowListCount; i++) {
			String data = reportCol4List.get(i).getText();
			report4thRowListArray.add(data);
		}
		String actRow4List = report4thRowListArray.toString();
		String expRow4List = "[339.00, 29.75, 20.00, 20.00, 5.00, 15.00, , 10.00, 20.00, 11.00, 5.00, , 130.50, , 10.00, 10.00, 10.00, 635.25]";

		System.out.println("actRow1List  : " + actRow1List);
		System.out.println("expRow1List  : " + expRow1List);
		System.out.println("*********************************************************************");

		System.out.println("actRow2List  : " + actRow2List);
		System.out.println("expRow2List  : " + expRow2List);
		System.out.println("*********************************************************************");

		System.out.println("actRow3List  : " + actRow3List);
		System.out.println("expRow3List  : " + expRow3List);
		System.out.println("*********************************************************************");

		System.out.println("actRow4List  : " + actRow4List);
		System.out.println("expRow4List  : " + expRow4List);
		System.out.println("*********************************************************************");

		if (actRow1List.equalsIgnoreCase(expRow1List) && actRow2List.equalsIgnoreCase(expRow2List)
				&& actRow3List.equalsIgnoreCase(expRow3List) && actRow4List.equalsIgnoreCase(expRow4List) &&

				actvalidationConfirmationMessage.equalsIgnoreCase(expvalidationConfirmationMessage)) {
			System.out.println(" Test Pass: Values Dsiplayed as Expected ");
			return true;
		} else {
			System.out.println(" Test FAIL: Values Dsiplayed as Expected ");
			String meesage = validationConfirmationMessage.getText();
			System.err.println(" Meesage Displayed  : " + meesage);
			return false;
		}

	}

	@FindBy(xpath = "//*[@id='analyzeReportTableBody']/tr/td[2]")
	private static List<WebElement> analyzeReportCol2List;

	@FindBy(xpath = "//*[@id='analyzeReportTableBody']/tr/td[3]")
	private static List<WebElement> analyzeReportCol3List;

	@FindBy(xpath = "//*[@id='analyzeReportTableBody']/tr/td[4]")
	private static List<WebElement> analyzeReportCol4List;

	@FindBy(xpath = "//input[@id='analyzeReportName']")
	private static WebElement sl_LA_repotNameTxt;

	@FindBy(xpath = "//*[@id='id_menu_tree_60']/a/span")
	private static WebElement sl_LA_repotFinanicalMenu;

	@FindBy(xpath = "//button[contains(text(),'Save')]")
	private static WebElement sl_LA_repotSaveBtn;

	@FindBy(xpath = "//*[@id='idsaveAnlyze']/i")
	private static WebElement sl_LA_saveBtn;

	@FindBy(xpath = "//*[@id='analyzeMenu']/li/span[8]/i")
	private static WebElement sl_LA_ExitBtn;

	@FindBy(xpath = "//ul[@id='rowGroupingData']")
	private static WebElement sl_LA_GroupGrowingTxtArea;

	@FindBy(xpath = "//ul[@id='columnGroupingData']")
	private static WebElement sl_LA_ColGroupingTxtArea;

	@FindBy(xpath = "//ul[@id='ColumnMeasureData']")
	private static WebElement sl_LA_ColMeasureTxtArea;

	@FindBy(xpath = "//*[@id='columnData']/ul/li[2]")
	private static WebElement sl_LA_ColDataVoucher;

	@FindBy(xpath = "//*[@id='columnData']/ul/li[3]")
	private static WebElement sl_LA_ColDataAccount;

	@FindBy(xpath = "//*[@id='columnData']/ul/li[4]")
	private static WebElement sl_LA_ColDataDebit;

	@FindBy(xpath = "//*[@id='columnData']/ul/li[5]")
	private static WebElement sl_LA_ColDataCredit;

	@FindBy(xpath = "//*[@id='columnData']/ul/li[6]")
	private static WebElement sl_LA_ColDataBalance;

	@FindBy(xpath = "//*[@id='columnData']/ul/li[7]")
	private static WebElement sl_LA_ColDataDrLocal;

	@FindBy(xpath = "//*[@id='columnData']/ul/li[8]")
	private static WebElement sl_LA_ColDataCrLocal;

	@FindBy(xpath = "//*[@id='columnData']/ul/li[9]")
	private static WebElement sl_LA_ColDataBalLocal;

	@FindBy(xpath = "//*[@id='columnData']/ul/li[10]")
	private static WebElement sl_LA_ColDataDrBase;

	@FindBy(xpath = "//*[@id='columnData']/ul/li")
	private static List<WebElement> sl_LA_ColDataList;

	@FindBy(xpath = "//*[@id='REPORTRENDERNEWControls']/ul/li/span[3]")
	private static WebElement sl_PrintBtn;

	@FindBy(xpath = "//*[@id='REPORTRENDERNEWControls']/ul/li/span[4]")
	private static WebElement sl_ExportBtn;

	@FindBy(xpath = "//a[contains(text(),'PDF')]")
	private static WebElement sl_ExportPDFBtn;

	String LedgerAnalaysis = null;

	public boolean checkLedgerDetailsExportPdf() throws InterruptedException, AWTException, IOException {

		Thread.sleep(2000);

		File Efile = new File(getBaseDir() + "\\autoIt\\ExportFiles\\LedgerAnalaysis.pdf");

		if (Efile.exists()) {
			Efile.delete();
		}

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_ExportBtn));
		sl_ExportBtn.click();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_ExportPDFBtn));
		sl_ExportPDFBtn.click();

		Thread.sleep(5000);

		LedgerAnalaysis = checkDownloadedFileName(getDriver());

		/*
		 * Robot robot = new Robot(); robot.keyPress(KeyEvent.VK_CONTROL);
		 * robot.keyPress(KeyEvent.VK_J); robot.keyRelease(KeyEvent.VK_J);
		 * robot.keyRelease(KeyEvent.VK_CONTROL);
		 * 
		 * Thread.sleep(2000);
		 * 
		 * robot.keyPress(KeyEvent.VK_TAB); robot.keyRelease(KeyEvent.VK_TAB);
		 * robot.keyPress(KeyEvent.VK_TAB); robot.keyRelease(KeyEvent.VK_TAB);
		 * 
		 * robot.keyPress(KeyEvent.VK_ENTER); robot.keyRelease(KeyEvent.VK_ENTER);
		 * 
		 * Thread.sleep(2000);
		 * 
		 * robot.keyPress(KeyEvent.VK_CONTROL); robot.keyPress(KeyEvent.VK_S);
		 * robot.keyRelease(KeyEvent.VK_S); robot.keyRelease(KeyEvent.VK_CONTROL);
		 * 
		 * Thread.sleep(3000);
		 * 
		 * Runtime.getRuntime().exec(getBaseDir() +
		 * "\\autoIt\\scripts\\SavingPrintPDFInLedgerAnanlysis.exe");
		 * 
		 * Thread.sleep(5000);
		 * 
		 * robot.keyPress(KeyEvent.VK_CONTROL); robot.keyPress(KeyEvent.VK_J);
		 * robot.keyRelease(KeyEvent.VK_J); robot.keyRelease(KeyEvent.VK_CONTROL);
		 * 
		 * Thread.sleep(2000);
		 * 
		 * ArrayList<String> openTabs = new
		 * ArrayList<String>(getDriver().getWindowHandles());
		 * 
		 * int actOpenWindowsCount = getDriver().getWindowHandles().size(); int
		 * expOpenWindowsCount = 3;
		 * 
		 * System.out.println("Number of Windows  : "
		 * +actOpenWindowsCount+"  Value Expected  "+expOpenWindowsCount);
		 * 
		 * Thread.sleep(1000);
		 * 
		 * getDriver().switchTo().window(openTabs.get(2)).close(); Thread.sleep(1000);
		 * getDriver().switchTo().window(openTabs.get(1)).close(); Thread.sleep(1000);
		 * getDriver().switchTo().window(openTabs.get(0));
		 */

		String actPDF = "‪C:\\Users\\Dell\\Downloads\\" + LedgerAnalaysis;
		String expPDF = getBaseDir() + "\\autoIt\\Prints\\ExpectedRDReport.pdf";

		PDFUtil pdfutil = new PDFUtil();

		boolean result = pdfutil.compare(actPDF, expPDF);

		System.out.println("Compared Result  : " + result);

		Thread.sleep(2000);

		getDriver().navigate().refresh();

		if (result) {
			return true;
		} else {
			getDriver().navigate().refresh();
			return false;
		}

	}

	public boolean checkImportExcelFileInReportDesign()
			throws InterruptedException, IOException, EncryptedDocumentException, InvalidFormatException {
		Thread.sleep(1999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(homeMenu));
		homeMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(utilities));
		utilities.click();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportDesignerMenu));
		reportDesignerMenu.click();

		Thread.sleep(2999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdImportFromXML));
		rdImportFromXML.click();

		Thread.sleep(2999);
		Runtime.getRuntime().exec(getBaseDir() + "\\autoIt\\scripts\\Billwise-Details.exe");

		Thread.sleep(6999);

		Thread.sleep(2999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportNameDropdown));
		reportNameDropdown.click();
		String actreportNameDropdown = reportNameDropdown.getAttribute("value");
		String expreportNameDropdown = "Billwise-Details";

		System.out.println(" reportNameDropdown  : " + actreportNameDropdown + " Value  " + expreportNameDropdown);

		reportNameDropdown.sendKeys(Keys.TAB);

		Thread.sleep(3000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(previewTab));
		previewTab.click();

		Thread.sleep(3000);

		int previewTabHeaderListCount = previewTabHeaderList.size();

		ArrayList<String> previewTabHeaderListArray = new ArrayList<>();

		for (int i = 0; i < previewTabHeaderListCount; i++) {

			String data = previewTabHeaderList.get(i).getText();
			previewTabHeaderListArray.add(data);

		}
		String actpreviewTabHeaderList = previewTabHeaderListArray.toString();
		String exppreviewTabHeaderList = "[, Account.Name, Account.Account Type, Account2.Name, Account2.Code, Name, Document No., Due date, Unit.Name, Department.Name, Warehouse.Name]";

		System.out.println(" ACt List   :" + actpreviewTabHeaderList);
		System.out.println("  Exp List  :" + exppreviewTabHeaderList);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(finishBtn));
		finishBtn.click();

		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : " + actMessage + " Value Expected : " + expMessage);

		if (actMessage.equalsIgnoreCase(expMessage) && actreportNameDropdown.equalsIgnoreCase(expreportNameDropdown)
				&& actpreviewTabHeaderList.equalsIgnoreCase(exppreviewTabHeaderList)) {

			System.out.println(" Test Pass: Imported from XML ");
			return true;
		} else {
			System.out.println(" Test Fail: Imported from XML ");
			return false;
		}
	}

	public boolean checkExportOptionreportDesging() throws InterruptedException, IOException, AWTException {

		Thread.sleep(2000);

		File Efile = new File(getBaseDir() + "\\autoIt\\ExportFiles\\ExportRDBillwise.xml");

		if (Efile.exists()) {
			Efile.delete();
		}

		Thread.sleep(2999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportNameDropdown));
		reportNameDropdown.sendKeys("Billwise-Details");
		Thread.sleep(1999);
		reportNameDropdown.sendKeys(Keys.TAB);

		Thread.sleep(2999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdExportToXML));
		rdExportToXML.click();

		Thread.sleep(3999);

		Runtime.getRuntime().exec(getBaseDir() + "\\autoIt\\scripts\\ExportRDBillwise.exe");

		Thread.sleep(25000);

		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_J);
		robot.keyRelease(KeyEvent.VK_J);
		robot.keyRelease(KeyEvent.VK_CONTROL);

		Thread.sleep(2000);

		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);

		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

		Thread.sleep(2000);

		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_S);
		robot.keyRelease(KeyEvent.VK_S);
		robot.keyRelease(KeyEvent.VK_CONTROL);

		Thread.sleep(3000);

		Runtime.getRuntime().exec(getBaseDir() + "\\autoIt\\scripts\\ExportRDBillwise.exe");

		Thread.sleep(5000);

		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_J);
		robot.keyRelease(KeyEvent.VK_J);
		robot.keyRelease(KeyEvent.VK_CONTROL);

		Thread.sleep(2000);

		ArrayList<String> openTabs = new ArrayList<String>(getDriver().getWindowHandles());

		int actOpenWindowsCount = getDriver().getWindowHandles().size();
		int expOpenWindowsCount = 3;

		System.out.println("Number of Windows  : " + actOpenWindowsCount + "  Value Expected  " + expOpenWindowsCount);

		Thread.sleep(1000);

		getDriver().switchTo().window(openTabs.get(2)).close();
		Thread.sleep(1000);
		getDriver().switchTo().window(openTabs.get(1)).close();
		Thread.sleep(1000);
		getDriver().switchTo().window(openTabs.get(0));

		String actPDF = "D:\\Billwise Project\\FocusAI\\autoIt\\ExportFiles\\LedgerAnalaysis.pdf";
		String expPDF = "D:\\Billwise Project\\FocusAI\\\\autoIt\\Prints\\ExpectedRDReport.pdf";

		PDFUtil pdfutil = new PDFUtil();

		boolean result = pdfutil.compare(actPDF, expPDF);

		System.out.println("Compared Result  : " + result);

		Thread.sleep(2000);

		if (result) {
			return true;
		} else {
			return false;
		}

	}

	@FindBy(xpath = "//input[@id='id_rd_parameter_entry_fieldname']")
	private static WebElement rdFieldNameTxt;

	@FindBy(xpath = "//input[@id='id_rd_parameter_entry_variablename']")
	private static WebElement rdVariableNameTxt;

	@FindBy(xpath = "//select[@id='id_rd_parameter_entry_fieldtype']")
	private static WebElement rdFieldTypeDrpdwn;

	@FindBy(xpath = "//input[@id='id_rd_parameter_entry_multipleinputselection']")
	private static WebElement rdMultipleInputChkbox;

	@FindBy(xpath = "//input[@id='id_rd_parameter_entry_isgroup']")
	private static WebElement rdInAGroupChkbox;

	@FindBy(xpath = "//*[@id='id_rd_parameters_group']/div[2]/div[9]/div[2]/input[2]")
	private static WebElement rdParametersOkbtn;

	@FindBy(xpath = "//*[@id='id_rd_definition_parameters_n1']")
	private static WebElement rdParametersTxtArea;

	public boolean checkSavingRDReportsWihParameter()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		Thread.sleep(1999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(homeMenu));
		homeMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(utilities));
		utilities.click();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportDesignerMenu));
		reportDesignerMenu.click();

		Thread.sleep(2999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportNameDropdown));
		reportNameDropdown.sendKeys("Billwise Details");
		reportNameDropdown.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportTypeDropdown));
		Select rtd = new Select(reportTypeDropdown);
		rtd.selectByVisibleText("Details");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdReportsAdvanceEngineChkbox));
		rdReportsAdvanceEngineChkbox.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(inventoryExpandBtn));
		inventoryExpandBtn.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportsBtn));
		reportsBtn.click();

		rdFieldNameTxt.click();
		rdFieldNameTxt.sendKeys("department");
		Thread.sleep(2000);

		rdFieldNameTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		Select s1 = new Select(rdFieldTypeDrpdwn);
		s1.selectByValue("3");

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdParametersOkbtn));
		rdParametersOkbtn.click();

		Thread.sleep(2999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdParametersTxtArea));
		String actrdParametersTxtArea = rdParametersTxtArea.getAttribute("data-fieldname");
		System.out.println(" rdParametersTxtArea : " + actrdParametersTxtArea);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dataSetTab));
		dataSetTab.click();

		Thread.sleep(2000);

		int transactionSetListCount = transactionSetList.size();

		for (int i = 0; i <= transactionSetListCount; i++) {
			String data = transactionSetList.get(i).getText();

			if (data.equalsIgnoreCase("Bill Reference")) {
				transactionSetList.get(i).click();

				break;
			}
		}

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdCusTabDfaultFilterTxt));
		rdCusTabDfaultFilterTxt.click();
		rdCusTabDfaultFilterTxt.sendKeys("DUBAI");
		Thread.sleep(1999);

		rdCusTabDfaultFilterTxt.sendKeys(Keys.TAB);

		Thread.sleep(1999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTab));
		customizationTab.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionExpandBtn));
		transactionExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionFieldsExpandBtn));
		transactionFieldsExpandBtn.click();

		Thread.sleep(2000);
		getAction().moveToElement(docnumberBtn).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(docnumberBtn));
		getAction().doubleClick(docnumberBtn).build().perform();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(acc1ExpandBtn));
		acc1ExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(acc1NameBtn));
		getAction().doubleClick(acc1NameBtn).build().perform();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemExpandBtn));
		itemExpandBtn1.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemNameBtn1));
		getAction().doubleClick(itemNameBtn1).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemCodeBtn1));
		getAction().doubleClick(itemCodeBtn1).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionFieldsExpandBtn));
		transactionFieldsExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(extraFieldsExpandBtn));
		extraFieldsExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(extraFieldDepartExpandBtn));
		extraFieldDepartExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(extraFieldDepartNameBtn));
		getAction().doubleClick(extraFieldDepartNameBtn).build().perform();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(finishBtn));
		finishBtn.click();

		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : " + actMessage + " Value Expected : " + expMessage);

		if (actMessage.equalsIgnoreCase(expMessage)) {

			System.out.println(" Test PasS: Report desgining is saved with Document Type ");
			return true;
		} else {
			System.out.println(" Test FAIL: Report desgining is saved with Document Type ");
			return false;
		}

	}

	@FindBy(xpath = "//input[@id='FOption_22_0_DefaultFilter_0']")
	private static WebElement rdCusTabDfaultFilterTxt;

	@FindBy(xpath = "//*[@id='id_rd_customization_report_column_button_container']/input[2]")
	private static WebElement rdExtraFiledOkBtn;

	public boolean checkAddingProgrammmingFiledInBillwiseRDReport()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		Thread.sleep(1999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(homeMenu));
		homeMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(utilities));
		utilities.click();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportDesignerMenu));
		reportDesignerMenu.click();

		Thread.sleep(2999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportNameDropdown));
		reportNameDropdown.sendKeys("Billwise Details");
		Thread.sleep(1999);
		reportNameDropdown.sendKeys(Keys.TAB);

		Thread.sleep(3999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTab));
		customizationTab.click();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(ProgramFiledexpandBtn));
		ProgramFiledexpandBtn.click();

		Thread.sleep(1000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(ProgramFieldBtn));
		getAction().doubleClick(ProgramFieldBtn).build().perform();

		int customizeTabTableHeaderLsistCount = customizeTabTableHeaderLsist.size();
		System.err.println(customizeTabTableHeaderLsistCount);
		for (int i = 1; i <= customizeTabTableHeaderLsistCount; i++) {
			String data = customizeTabTableHeaderLsist.get(i).getText();

			if (data.equalsIgnoreCase("Programmable Field")) {
				customizeTabTableHeaderLsist.get(i).click();

				System.err.println(i);
				Thread.sleep(1000);

				break;
			}
		}

		Thread.sleep(1999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdExtraFiledColHeadingTxt));
		rdExtraFiledColHeadingTxt.click();
		rdExtraFiledColHeadingTxt.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME);
		rdExtraFiledColHeadingTxt.sendKeys("AddedField");
		Thread.sleep(1999);

		rdExtraFiledColHeadingTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		getAction().moveToElement(rdExtraFiledFormulaControlTxt).build().perform();
		Thread.sleep(1999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdExtraFiledFormulaControlTxt));
		rdExtraFiledFormulaControlTxt.click();

		rdExtraFiledFormulaControlTxt.sendKeys("c2+1");
		Thread.sleep(1000);

		getAction().moveToElement(rdExtraFiledFormulaControlOkBtn).build().perform();
		Thread.sleep(1999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdExtraFiledFormulaControlOkBtn));
		rdExtraFiledFormulaControlOkBtn.click();

		Thread.sleep(1999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdExtraFiledOkBtn));
		rdExtraFiledOkBtn.click();

		Thread.sleep(3000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(previewTab));
		previewTab.click();

		Thread.sleep(3000);

		int previewTabHeaderListCount = previewTabHeaderList.size();

		ArrayList<String> previewTabHeaderListArray = new ArrayList<>();

		for (int i = 0; i < previewTabHeaderListCount; i++) {

			String data = previewTabHeaderList.get(i).getText();
			previewTabHeaderListArray.add(data);

		}
		String actpreviewTabHeaderList = previewTabHeaderListArray.toString();
		String exppreviewTabHeaderList = "";

		System.out.println(" ACt List   :" + actpreviewTabHeaderList);
		System.out.println("  Exp List  :" + exppreviewTabHeaderList);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(finishBtn));
		finishBtn.click();

		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : " + actMessage + " Value Expected : " + expMessage);

		if (actMessage.equalsIgnoreCase(expMessage)) {

			System.out.println(" Test PasS: Extra Filed Programmable Filed added");
			return true;
		} else {
			System.out.println(" Test FAIL: Extra Filed Programmable Filed added");
			return false;
		}

	}

	// Programmable Fileds

	@FindBy(xpath = "//*[@id='rd_customization_tree422']/a/i")
	private static WebElement custProgramFiledexpandBtn1;

	@FindBy(xpath = "//*[@id='rd_customization_tree423']/a/span")
	private static WebElement custProgramFieldBtn1;

	@FindBy(xpath = "//span[contains(text(),'Programmable Fields')]")
	private static WebElement custProgramFiledexpandBtn2;

	@FindBy(xpath = "//body/section[@id='mainDiv']/div[@id='id_focus8_wrapper_default']/div[1]/section[1]/div[1]/div[1]/div[2]/div[3]/div[2]/div[1]/div[2]/ul[1]/li[2]/ul[1]/li[1]/a[1]/span[1]")
	private static WebElement custProgramFieldBtn2;

	@FindBy(xpath = "//*[@id='rd_customization_tree438']/a/i")
	private static WebElement custProgramFiledexpandBtn;

	@FindBy(xpath = "//*[@id='rd_customization_tree1729']/a/i")
	private static WebElement ProgramFiledexpandBtn;

	@FindBy(xpath = "//*[@id='rd_customization_tree1730']/a/span")
	private static WebElement ProgramFieldBtn;

	@FindBy(xpath = "//*[@id='rd_customization_tree439']/a/span")
	private static WebElement custProgramFieldBtn;

	@FindBy(xpath = "//input[@id='id_rd_columnproperty_columnheading']")
	private static WebElement rdExtraFiledColHeadingTxt;

	@FindBy(xpath = "//input[@id='id_rd_columnproperty_formula_textbox']")
	private static WebElement rdExtraFiledFormulaControlTxt;

	@FindBy(xpath = "//button[@id='id_rd_columnproperty_formula_Ok']")
	private static WebElement rdExtraFiledFormulaControlOkBtn;

	@FindBy(xpath = "//select[@id='id_rd_columnproperty_horizontalalign']")
	private static WebElement rdExtraFiledColHorizontalDrpdwn;

	@FindBy(xpath = "//input[@id='id_rd_columnproperty_columnwidth']")
	private static WebElement rdExtraFiledColWidthTxt;

	@FindBy(xpath = "//span[contains(text(),'select options')]")
	private static WebElement rdExtraFiledColSelectDrpdwn;

	@FindBy(xpath = "//select[@id='id_rd_columnproperty_sign']")
	private static WebElement rdExtraFiledColSignDrpdwn;

	@FindBy(xpath = "//span[contains(text(),'select options')]")
	private static WebElement rdExtraFiledSelectingDrpdwn;

	public boolean checkBillwiseDetailsreport() throws InterruptedException {

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
		searchTxt.click();
		Thread.sleep(1000);
		searchTxt.sendKeys("Billwise Details");
		Thread.sleep(1000);
		searchTxt.sendKeys(Keys.ENTER);

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_DateOptionDropdown));

		Select s = new Select(sl_DateOptionDropdown);
		s.selectByValue("1");

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(billwiseReportDepTxt));
		billwiseReportDepTxt.click();
		billwiseReportDepTxt.sendKeys("Duabi");
		Thread.sleep(2000);
		billwiseReportDepTxt.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_OkBtn));
		sl_OkBtn.click();

		Thread.sleep(2000);
		boolean novalidationConfirmationMessage = validationConfirmationMessage.getText().isEmpty();

		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";

		Thread.sleep(1500);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_1stRow1stCol));

		int reportsRow1ListCount = reportCol1List.size();
		ArrayList<String> reportsRow1ListArray = new ArrayList<String>();
		for (int i = 0; i < reportsRow1ListCount; i++) {
			String data = reportCol1List.get(i).getText();

			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			String expadjustBills = df.format(date);

			reportsRow1ListArray.add(data);
		}
		String actRow1List = reportsRow1ListArray.toString();
		String expRow1List = "[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19]";

		int report2ndRowListCount = reportCol2List.size();
		ArrayList<String> report2ndRowListArray = new ArrayList<String>();
		for (int i = 0; i < report2ndRowListCount; i++) {
			String data = reportCol2List.get(i).getText();
			report2ndRowListArray.add(data);
		}
		String actRow2List = report2ndRowListArray.toString();
		String expRow2List = "[1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2]";

		int report3rdRowListCount = reportCol3List.size();
		ArrayList<String> report3rdRowListArray = new ArrayList<String>();
		for (int i = 0; i < report3rdRowListCount; i++) {
			String data = reportCol3List.get(i).getText();
			report3rdRowListArray.add(data);
		}
		String actRow3List = report3rdRowListArray.toString();
		String expRow3List = "[Opening Balances Control A/C, STD RATE COGS ACC INV, Customer A, Bank, Bank, SR COGS POSTING ACC, Customer A, Bank, Bank, Vendor New Reference, VAT INPUT, Vendor New Reference, , Bank, Bank, Vendor B, , VAT INPUT, Vendor B]";

		int report4thRowListCount = reportCol4List.size();
		ArrayList<String> report4thRowListArray = new ArrayList<String>();
		for (int i = 0; i < report4thRowListCount; i++) {
			String data = reportCol4List.get(i).getText();
			report4thRowListArray.add(data);
		}
		String actRow4List = report4thRowListArray.toString();
		String expRow4List = "[, , STD RATE COGS ITEM, , , , STD RATE COGS ITEM, , , , , STD RATE COGS ITEM, , , , , , , STD RATE COGS ITEM]";

		int report5thRowListCount = reportCol5List.size();
		ArrayList<String> report5thRowListArray = new ArrayList<String>();
		for (int i = 0; i < report5thRowListCount; i++) {
			String data = reportCol5List.get(i).getText();
			report5thRowListArray.add(data);
		}
		String actRow5List = report5thRowListArray.toString();
		String expRow5List = "[DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI]";

		int report6thRowListCount = reportCol6List.size();
		ArrayList<String> report6thRowListArray = new ArrayList<String>();
		for (int i = 0; i < report6thRowListCount; i++) {
			String data = reportCol6List.get(i).getText();
			report6thRowListArray.add(data);
		}
		String actRow6List = report6thRowListArray.toString();
		String expRow6List = "[, , , , , , , , , , , , , , , , , , ]";

		System.out.println("actRow1List  : " + actRow1List);
		System.out.println("expRow1List  : " + expRow1List);
		System.out.println("*********************************************************************");

		System.out.println("actRow2List  : " + actRow2List);
		System.out.println("expRow2List  : " + expRow2List);
		System.out.println("*********************************************************************");

		System.out.println("actRow3List  : " + actRow3List);
		System.out.println("expRow3List  : " + expRow3List);
		System.out.println("*********************************************************************");

		System.out.println("actRow4List  : " + actRow4List);
		System.out.println("expRow4List  : " + expRow4List);
		System.out.println("*********************************************************************");

		System.out.println("actRow5List  : " + actRow5List);
		System.out.println("expRow5List  : " + expRow5List);
		System.out.println("*********************************************************************");

		System.out.println("actRow6List  : " + actRow6List);
		System.out.println("expRow6List  : " + expRow6List);
		System.out.println("*********************************************************************");

		if (actRow1List.equalsIgnoreCase(expRow1List) && actRow2List.equalsIgnoreCase(expRow2List)
				&& actRow3List.equalsIgnoreCase(expRow3List) && actRow4List.equalsIgnoreCase(expRow4List)
				&& actRow5List.equalsIgnoreCase(expRow5List) && actRow6List.equalsIgnoreCase(expRow6List) &&

				actvalidationConfirmationMessage.equalsIgnoreCase(expvalidationConfirmationMessage)) {
			System.out.println(" Test Pass: Values Dsiplayed as Expected ");
			return true;
		} else {
			System.out.println(" Test FAIL: Values Dsiplayed as Expected ");
			String meesage = validationConfirmationMessage.getText();
			System.err.println(" Meesage Displayed  : " + meesage);
			return false;
		}

	}

	@FindBy(xpath = "//select[@id='id_rd_definition_datasets']")
	private static WebElement reportDataSetTypeDrpdwn;

	public boolean checkParameterWithMultiplewayoptions()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		Thread.sleep(1999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(homeMenu));
		homeMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(utilities));
		utilities.click();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportDesignerMenu));
		reportDesignerMenu.click();

		Thread.sleep(2999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportNameDropdown));
		reportNameDropdown.sendKeys("Billwise Details");
		Thread.sleep(1999);
		reportNameDropdown.sendKeys(Keys.TAB);

		Thread.sleep(4999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdParametersTxtArea));
		rdParametersTxtArea.click();

		Thread.sleep(2999);
		getAction().doubleClick(rdParametersTxtArea).click().build().perform();
		Thread.sleep(2000);

		if (rdMultipleInputChkbox.isSelected() == false) {
			rdMultipleInputChkbox.click();
		}

		boolean actrdMultipleInputChkbox = rdMultipleInputChkbox.isSelected();
		boolean exprdMultipleInputChkbox = true;

		System.out.println(" rdMultipleInputChkbox " + actrdMultipleInputChkbox + " Value Expected  : "
				+ exprdMultipleInputChkbox);

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdParametersOkbtn));
		rdParametersOkbtn.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dataSetTab));
		dataSetTab.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdDatSetFilterBtn));
		rdDatSetFilterBtn.click();

		for (int i = 0; i < 5; i++) {

			if (rdAdvanceFilterWhereDrpdwn.isDisplayed() == false) {

				rdDatSetFilterBtn.click();
				System.out.println(" I " + i);
				break;
			}

		}
		Thread.sleep(2000);
		Select s1 = new Select(rdAdvanceFilterWhereDrpdwn);
		s1.selectByValue("0");

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdAdvanceFilterNameTxt));
		rdAdvanceFilterNameTxt.click();
		Thread.sleep(2000);

		getAction().moveToElement(rdAdvanceFilterDepExpBtn).build().perform();
		rdAdvanceFilterDepExpBtn.click();

		rdAdvanceFilterName_DepNameBtn.click();

		Thread.sleep(2000);
		Select s3 = new Select(rdAdvanceFilterOpersatorDrpdwn);
		s3.selectByValue("0");

		Thread.sleep(2000);
		Select s4 = new Select(rdAdvanceFilterValueDrpdwn);
		s4.selectByValue("2");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdAdvanceFilterSelectTxt));
		rdAdvanceFilterSelectTxt.click();
		rdAdvanceFilterSelectTxt.sendKeys("@department");

		Thread.sleep(2999);
		rdAdvanceFilterSelectTxt.sendKeys(Keys.TAB);

		boolean novalidationConfirmationMessage = validationConfirmationMessage.getText().isEmpty();

		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";

		String actrdAdvanceFilterSelectTxt = rdAdvanceFilterSelectTxt.getText();
		String exprdAdvanceFilterSelectTxt = "@department";

		System.out.println(" rdAdvanceFilterSelectTxt : " + actrdAdvanceFilterSelectTxt + " Value exp: "
				+ exprdAdvanceFilterSelectTxt);

		Thread.sleep(2999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(finishBtn));
		finishBtn.click();

		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : " + actMessage + " Value Expected : " + expMessage);

		if (actMessage.equalsIgnoreCase(expMessage)) {

			System.out.println(" Test PasS: Multiple Chkbx is Selcted ");
			return true;
		} else {
			System.out.println(" Test FAIL:  Multiple Chkbx is Selcted ");
			return false;
		}

	}

	@FindBy(xpath = "//span[@id='a']")
	private static WebElement rdDatSetFilterBtn;

	@FindBy(xpath = "//*[@id='22_0_AdvanceFilter_']/table/tbody/tr/td[1]/select")
	private static WebElement rdAdvanceFilterWhereDrpdwn;

	@FindBy(xpath = "//*[@id='22_0_AdvanceFilter_']/table/tbody/tr/td[2]/input")
	private static WebElement rdAdvanceFilterNameTxt;

	@FindBy(xpath = "//*[@id='536870915']")
	private static WebElement rdAdvanceFilterDepExpBtn;

	@FindBy(xpath = "//a[@id='5042']")
	private static WebElement rdAdvanceFilterName_DepNameBtn;

	@FindBy(xpath = "//*[@id='22_0_AdvanceFilter_']/table/tbody/tr/td[3]/select")
	private static WebElement rdAdvanceFilterOpersatorDrpdwn;

	@FindBy(xpath = "//*[@id='22_0_AdvanceFilter_']/table/tbody/tr/td[4]/select")
	private static WebElement rdAdvanceFilterValueDrpdwn;

	@FindBy(xpath = "//*[@id='22_0_AdvanceFilter_']/table/tbody/tr/td[5]/input")
	private static WebElement rdAdvanceFilterSelectTxt;

	@FindBy(xpath = "//tbody[@id='LandingGridBody']/tr")
	private static List<WebElement> stockLedgerHometableRowCount;

	public boolean checkBillwiseReportAfterCheckingMultipleChkbox()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(userNameDisplay));
		userNameDisplay.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(userNameDisplay));
		logoutOption.click();

		Thread.sleep(2000);

		LoginPage lp = new LoginPage(getDriver());

		String unamelt = "su";

		String pawslt = "su";

		lp.enterUserName(unamelt);

		Thread.sleep(2000);

		lp.enterPassword(pawslt);

		/* String compname="User Restrictions--COGS"; */
		String compname = "RD REPORTS";

		Select oSelect = new Select(companyDropDownList);

		List<WebElement> elementCount = oSelect.getOptions();

		int cqSize = elementCount.size();

		System.out.println("CompanyDropdownList Count :" + cqSize);

		int y;

		for (y = 0; y < elementCount.size(); y++) {

			elementCount.get(y).getText();

			String optionName = elementCount.get(y).getText();
			if (optionName.toUpperCase().startsWith(compname.toUpperCase())) {
				System.out.println("q" + elementCount.get(y).getText());
				elementCount.get(y).click();
			}

		}

		Thread.sleep(2000);

		lp.clickOnSignInBtn();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
		searchTxt.click();
		Thread.sleep(1000);
		searchTxt.sendKeys("Billwise Details");
		Thread.sleep(1000);
		searchTxt.sendKeys(Keys.ENTER);

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_DateOptionDropdown));

		Select s = new Select(sl_DateOptionDropdown);
		s.selectByValue("1");

		Thread.sleep(2000);

		int rowcount = stockLedgerHometableRowCount.size();

		ArrayList<String> department = new ArrayList<>();

		System.out.println(rowcount);

		for (int i = 0; i < rowcount; i++) {
			String data = stockLedgerHometableRowCount.get(i).getText();
			department.add(data);
		}

		String actDepList = department.toString();
		String expDepList = "[1 INDIA INDIA, 2 DUBAI DUBAI, 3 AMERICA AMERICA, 4 SINGPORE SINGAPORE, 6 EUROPE EUROPE]";

		System.out.println(" Department List  : " + actDepList);
		System.out.println(" Department List  : " + expDepList);

		for (int i = 1; i <= rowcount; i++) {
			WebElement name = getDriver().findElement(By.xpath("//tbody[@id='LandingGridBody']/tr[" + i + "]/td[10]"));

			String actname = name.getText();

			System.out.println(actname);

			if (actname.equalsIgnoreCase("DUBAI")) {

				WebElement index = getDriver()
						.findElement(By.xpath("//tbody[@id='LandingGridBody']/tr[" + i + "]/td[8]/div/label/input"));
				index.click();

				break;
			}
		}

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_OkBtn));
		sl_OkBtn.click();

		// checkServerErrorMessage();

		Thread.sleep(2000);
		boolean novalidationConfirmationMessage = validationConfirmationMessage.getText().isEmpty();

		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";

		Thread.sleep(1500);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_1stRow1stCol));

		int reportsRow1ListCount = reportCol1List.size();
		ArrayList<String> reportsRow1ListArray = new ArrayList<String>();
		for (int i = 0; i < reportsRow1ListCount; i++) {
			String data = reportCol1List.get(i).getText();

			reportsRow1ListArray.add(data);
		}
		String actRow1List = reportsRow1ListArray.toString();
		String expRow1List = "[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19]";

		int report2ndRowListCount = reportCol2List.size();
		ArrayList<String> report2ndRowListArray = new ArrayList<String>();
		for (int i = 0; i < report2ndRowListCount; i++) {
			String data = reportCol2List.get(i).getText();
			report2ndRowListArray.add(data);
		}
		String actRow2List = report2ndRowListArray.toString();
		String expRow2List = "[1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2]";

		int report3rdRowListCount = reportCol3List.size();
		ArrayList<String> report3rdRowListArray = new ArrayList<String>();
		for (int i = 0; i < report3rdRowListCount; i++) {
			String data = reportCol3List.get(i).getText();
			report3rdRowListArray.add(data);
		}
		String actRow3List = report3rdRowListArray.toString();
		String expRow3List = "[Opening Balances Control A/C, STD RATE COGS ACC INV, Customer A, Bank, Bank, SR COGS POSTING ACC, Customer A, Bank, Bank, , Vendor New Reference, Vendor New Reference, VAT INPUT, Bank, Bank, , VAT INPUT, Vendor B, Vendor B]";

		int report4thRowListCount = reportCol4List.size();
		ArrayList<String> report4thRowListArray = new ArrayList<String>();
		for (int i = 0; i < report4thRowListCount; i++) {
			String data = reportCol4List.get(i).getText();
			report4thRowListArray.add(data);
		}
		String actRow4List = report4thRowListArray.toString();
		String expRow4List = "[, , STD RATE COGS ITEM, , , , STD RATE COGS ITEM, , , , , STD RATE COGS ITEM, , , , , , , STD RATE COGS ITEM]";

		int report5thRowListCount = reportCol5List.size();
		ArrayList<String> report5thRowListArray = new ArrayList<String>();
		for (int i = 0; i < report5thRowListCount; i++) {
			String data = reportCol5List.get(i).getText();
			report5thRowListArray.add(data);
		}
		String actRow5List = report5thRowListArray.toString();
		String expRow5List = "[DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI]";

		int report6thRowListCount = reportCol6List.size();
		ArrayList<String> report6thRowListArray = new ArrayList<String>();
		for (int i = 0; i < report6thRowListCount; i++) {
			String data = reportCol6List.get(i).getText();
			report6thRowListArray.add(data);
		}
		String actRow6List = report6thRowListArray.toString();
		String expRow6List = "[, , , , , , , , , , , , , , , , , , ]";

		System.out.println("actRow1List  : " + actRow1List);
		System.out.println("expRow1List  : " + expRow1List);
		System.out.println("*********************************************************************");

		System.out.println("actRow2List  : " + actRow2List);
		System.out.println("expRow2List  : " + expRow2List);
		System.out.println("*********************************************************************");

		System.out.println("actRow3List  : " + actRow3List);
		System.out.println("expRow3List  : " + expRow3List);
		System.out.println("*********************************************************************");

		System.out.println("actRow4List  : " + actRow4List);
		System.out.println("expRow4List  : " + expRow4List);
		System.out.println("*********************************************************************");

		System.out.println("actRow5List  : " + actRow5List);
		System.out.println("expRow5List  : " + expRow5List);
		System.out.println("*********************************************************************");

		System.out.println("actRow6List  : " + actRow6List);
		System.out.println("expRow6List  : " + expRow6List);
		System.out.println("*********************************************************************");

		if (actRow1List.equalsIgnoreCase(expRow1List) && actRow2List.equalsIgnoreCase(expRow2List)
				&& actRow3List.equalsIgnoreCase(expRow3List) && actRow4List.equalsIgnoreCase(expRow4List)
				&& actRow5List.equalsIgnoreCase(expRow5List) && actRow6List.equalsIgnoreCase(expRow6List) &&

				actvalidationConfirmationMessage.equalsIgnoreCase(expvalidationConfirmationMessage)) {
			System.out.println(" Test Pass: Values Dsiplayed as Expected ");
			return true;
		} else {
			System.out.println(" Test FAIL: Values Dsiplayed as Expected ");
			String meesage = validationConfirmationMessage.getText();
			System.err.println(" Meesage Displayed  : " + meesage);
			return false;
		}

	}

	public boolean checkSavingReportDesigningWithQuaeery()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		Thread.sleep(1999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(homeMenu));
		homeMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(utilities));
		utilities.click();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportDesignerMenu));
		reportDesignerMenu.click();

		Thread.sleep(2999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportNameDropdown));
		reportNameDropdown.sendKeys("Report With Quarry");
		Thread.sleep(2000);
		reportNameDropdown.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportTypeDropdown));
		Select rtd = new Select(reportTypeDropdown);
		rtd.selectByVisibleText("Details");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportDataSetTypeDrpdwn));
		Select rds = new Select(reportDataSetTypeDrpdwn);
		rds.selectByVisibleText("Query");

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(inventoryExpandBtn));
		inventoryExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportsBtn));
		reportsBtn.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dataSetTab));
		dataSetTab.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportDataSetQueryTxt));
		reportDataSetQueryTxt.click();

		reportDataSetQueryTxt.sendKeys("select  * from msec_users");

		Thread.sleep(2999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTab));
		customizationTab.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(ReportFieldExpandBtn));
		ReportFieldExpandBtn.click();

		int reportFieldlistCount = reportFieldlist.size();
		ArrayList<String> reportFieldlistarray = new ArrayList<>();
		for (int i = 0; i < reportFieldlistCount; i++) {
			String data = reportFieldlist.get(i).getText();
			reportFieldlistarray.add(data);
		}

		String actreportFieldlist = reportFieldlistarray.toString();
		String expreportFieldlist = "[iUserId, sLoginName, sLoginAbbr, iPwdPolicyId, sPassword, sUserName, sEmail, sPhone, sMobile, binImage, iGroupId, sSecurityQuestion, sSecurityAnswer, iUserType, iLinkId, sDomainName, sDomainUser, bAccountDisabled, bSendEmailNotification, bAllowMultipleLogin, bEmailonLoginFailure, bEmailUseronLoginSuccess, bDontLockAccount, iStatus, iNumInvalidAttempts, iLockedTill, iDays, iBlockFromDate, iBlockToDate, iTimeRestrictionStartDate, iTimeRestrictionEndDate, iTimeRestrictionStartTime, iTimeRestrictionEndTime, iLocation, iLanguage, iAltLanguage, iTimeZone, iUserAccess, iPWDChangeDate, fVal0, fVal1, fVal2, fVal3, fVal4, iCreatedBy, iModifiedBy, iCreatedDate, iModifiedDate, iCreatedTime, iModifiedTime, bModifiedDiffLoc, iSyncReceivedDate, iEditingLocation, iLocationId, sAuthenticationCode, biSignature, sEmailPwd, bEmailAuthPermission, sMacAddress]";

		System.out.println(" act reportFieldlist : " + actreportFieldlist);
		System.out.println(" exp reportFieldlist : " + expreportFieldlist);

		for (int i = 0; i < reportFieldlistCount; i++) {
			String data = reportFieldlist.get(i).getText();

			if (data.equalsIgnoreCase("iUserId")) {
				reportFieldlist.get(i).click();

				getAction().doubleClick(reportFieldlist.get(i)).build().perform();
			}
		}

		for (int i = 0; i < reportFieldlistCount; i++) {
			String data = reportFieldlist.get(i).getText();

			if (data.equalsIgnoreCase("sLoginName")) {
				reportFieldlist.get(i).click();

				getAction().doubleClick(reportFieldlist.get(i)).build().perform();
			}
		}

		for (int i = 0; i < reportFieldlistCount; i++) {
			String data = reportFieldlist.get(i).getText();

			if (data.equalsIgnoreCase("sLoginAbbr")) {
				reportFieldlist.get(i).click();

				getAction().doubleClick(reportFieldlist.get(i)).build().perform();
			}
		}

		for (int i = 0; i < reportFieldlistCount; i++) {
			String data = reportFieldlist.get(i).getText();

			if (data.equalsIgnoreCase("iPwdPolicyId")) {
				reportFieldlist.get(i).click();

				getAction().doubleClick(reportFieldlist.get(i)).build().perform();
			}
		}

		for (int i = 0; i < reportFieldlistCount; i++) {
			String data = reportFieldlist.get(i).getText();

			if (data.equalsIgnoreCase("sPassword")) {
				reportFieldlist.get(i).click();

				getAction().doubleClick(reportFieldlist.get(i)).build().perform();
			}
		}

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(previewTab));
		previewTab.click();

		Thread.sleep(3000);

		int previewTabHeaderListCount = previewTabHeaderList.size();

		ArrayList<String> previewTabHeaderListArray = new ArrayList<>();

		for (int i = 0; i < previewTabHeaderListCount; i++) {

			String data = previewTabHeaderList.get(i).getText();
			previewTabHeaderListArray.add(data);

		}
		String actpreviewTabHeaderList = previewTabHeaderListArray.toString();
		String exppreviewTabHeaderList = "";

		System.out.println(" Actual Preview TAb List   :" + actpreviewTabHeaderList);
		System.out.println("  Exp Preview TAb   List  :" + exppreviewTabHeaderList);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(finishBtn));
		finishBtn.click();

		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : " + actMessage + " Value Expected : " + expMessage);

		if (actMessage.equalsIgnoreCase(expMessage)) {

			System.out.println(" Test PasS: Extra Filed Programmable Filed added");
			return true;
		} else {
			System.out.println(" Test FAIL: Extra Filed Programmable Filed added");
			return false;
		}

	}

	@FindBy(xpath = "//*[@id='rd_customization_tree0']/ul/li")
	private static List<WebElement> reportFieldlist;

	@FindBy(xpath = "//textarea[@id='id_rd_customization_query']")
	private static WebElement reportDataSetQueryTxt;

	public boolean checkReportWithQuaeryreport() throws InterruptedException {

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
		searchTxt.click();
		Thread.sleep(1000);
		searchTxt.sendKeys("Report With Quarry");
		Thread.sleep(1000);
		searchTxt.sendKeys(Keys.ENTER);

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_DateOptionDropdown));

		Select s = new Select(sl_DateOptionDropdown);
		s.selectByValue("1");

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_OkBtn));
		sl_OkBtn.click();

		Thread.sleep(2000);
		boolean novalidationConfirmationMessage = validationConfirmationMessage.getText().isEmpty();

		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";

		Thread.sleep(1500);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_1stRow1stCol));

		int reportsRow1ListCount = reportCol1List.size();
		ArrayList<String> reportsRow1ListArray = new ArrayList<String>();
		for (int i = 0; i < reportsRow1ListCount; i++) {
			String data = reportCol1List.get(i).getText();

			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			String expadjustBills = df.format(date);

			reportsRow1ListArray.add(data);
		}
		String actRow1List = reportsRow1ListArray.toString();
		String expRow1List = "[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18]";

		int report2ndRowListCount = reportCol2List.size();
		ArrayList<String> report2ndRowListArray = new ArrayList<String>();
		for (int i = 0; i < report2ndRowListCount; i++) {
			String data = reportCol2List.get(i).getText();
			report2ndRowListArray.add(data);
		}
		String actRow2List = report2ndRowListArray.toString();
		String expRow2List = "[, 1.00, 2.00, 3.00, 4.00, 5.00, 6.00, 7.00, 8.00, 9.00, 10.00, 11.00, 12.00, 13.00, 14.00, 16.00, 17.00, 138.00]";

		int report3rdRowListCount = reportCol3List.size();
		ArrayList<String> report3rdRowListArray = new ArrayList<String>();
		for (int i = 0; i < report3rdRowListCount; i++) {
			String data = reportCol3List.get(i).getText();
			report3rdRowListArray.add(data);
		}
		String actRow3List = report3rdRowListArray.toString();
		String expRow3List = "[Users & Groups, SU, UserAllOptions, UserWithPurchaseFAINV, UserAccPOTransRestrictions, UserItemPOTransRestrictions, UserItemPOExclusion, UserWithAccDisableRole, UserWithDNotLock, UserWithAllowMulLogin, UserSendEmailLoginSuccess, UserSendEmailLoginFailure, UserBlockWithTime, UserGroup, UserUGChangePassword, UserWithAccRestrictionsReportV, UserWithLoadOption, Grand Total]";

		int report4thRowListCount = reportCol4List.size();
		ArrayList<String> report4thRowListArray = new ArrayList<String>();
		for (int i = 0; i < report4thRowListCount; i++) {
			String data = reportCol4List.get(i).getText();
			report4thRowListArray.add(data);
		}
		String actRow4List = report4thRowListArray.toString();
		String expRow4List = "[, , UAO, UWP, UPOR, UPOR, UIPOE, UAD, UWDL, UWAML, USELS, USELF, UBWT, null, UUGCP, UWARRV, UPOR, ]";

		int report5thRowListCount = reportCol5List.size();
		ArrayList<String> report5thRowListArray = new ArrayList<String>();
		for (int i = 0; i < report5thRowListCount; i++) {
			String data = reportCol5List.get(i).getText();
			report5thRowListArray.add(data);
		}
		String actRow5List = report5thRowListArray.toString();
		String expRow5List = "[, , 6.00, 6.00, 1.00, 1.00, 1.00, 6.00, 8.00, 1.00, 8.00, 8.00, 1.00, , 9.00, 8.00, 1.00, 65.00]";

		int report6thRowListCount = reportCol6List.size();
		ArrayList<String> report6thRowListArray = new ArrayList<String>();
		for (int i = 0; i < report6thRowListCount; i++) {
			String data = reportCol6List.get(i).getText();
			report6thRowListArray.add(data);
		}
		String actRow6List = report6thRowListArray.toString();
		String expRow6List = "[, 010c0088, 0154011400d2008e0048, 0154011400d2008e0048, 0154011400d2008e0048, 0154011400d2008e0048, 0154011400d2008e0048, 0154011400d2008e0048, 075008e6078007cb05cc064c0654054903660369041003b1023a028a01d8017a00a60047, 0192010c0086, 0af80d9c0bb80c78092a0a500a95094c0688090007b407c007710532069a05b80554050a03960440033a02c402580218019200a60047, 0af80d9c0bb80c78092a0a500a95094c0688090007b407c007710532069a05b80554050a032103a0036402fa02a80214016800a60047, 02180198010c0088, , 010c0086, 05a20618064805cd033e0264022801ea01aa0168012400de0096004c, 0154011400d2008e0048, ]";

		System.out.println("actRow1List  : " + actRow1List);
		System.out.println("expRow1List  : " + expRow1List);
		System.out.println("*********************************************************************");

		System.out.println("actRow2List  : " + actRow2List);
		System.out.println("expRow2List  : " + expRow2List);
		System.out.println("*********************************************************************");

		System.out.println("actRow3List  : " + actRow3List);
		System.out.println("expRow3List  : " + expRow3List);
		System.out.println("*********************************************************************");

		System.out.println("actRow4List  : " + actRow4List);
		System.out.println("expRow4List  : " + expRow4List);
		System.out.println("*********************************************************************");

		System.out.println("actRow5List  : " + actRow5List);
		System.out.println("expRow5List  : " + expRow5List);
		System.out.println("*********************************************************************");

		System.out.println("actRow6List  : " + actRow6List);
		System.out.println("expRow6List  : " + expRow6List);
		System.out.println("*********************************************************************");

		if (actRow1List.equalsIgnoreCase(expRow1List) && actRow2List.equalsIgnoreCase(expRow2List)
				&& actRow3List.equalsIgnoreCase(expRow3List) && actRow4List.equalsIgnoreCase(expRow4List)
				&& actRow5List.equalsIgnoreCase(expRow5List) && actRow6List.equalsIgnoreCase(expRow6List) &&

				actvalidationConfirmationMessage.equalsIgnoreCase(expvalidationConfirmationMessage)) {
			System.out.println(" Test Pass: Values Dsiplayed as Expected ");
			return true;
		} else {
			System.out.println(" Test FAIL: Values Dsiplayed as Expected ");
			String meesage = validationConfirmationMessage.getText();
			System.err.println(" Meesage Displayed  : " + meesage);
			return false;
		}

	}

	// Cubes

	public static boolean checkSavingAccountingTransactionsCube()
			throws InterruptedException, IOException, EncryptedDocumentException, InvalidFormatException {
		Thread.sleep(2999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(homeMenu));
		homeMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(utilities));
		utilities.click();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportDesignerMenu));
		reportDesignerMenu.click();

		Thread.sleep(2999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportNameDropdown));
		reportNameDropdown.sendKeys("Accounting Transactions-Cube");
		reportNameDropdown.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportTypeDropdown));
		Select rtd = new Select(reportTypeDropdown);
		rtd.selectByVisibleText("Cubes");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(finanincalExpandbtn1));
		finanincalExpandbtn1.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(finanincalReportsExpandbtn));
		finanincalReportsExpandbtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dataSetTab));
		dataSetTab.click();

		Thread.sleep(2000);

		int transactionSetListCount = transactionSetList.size();

		for (int i = 0; i <= transactionSetListCount; i++) {
			String data = transactionSetList.get(i).getText();

			if (data.equalsIgnoreCase("Accounting transactions")) {
				transactionSetList.get(i).click();

				break;
			}
		}

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTab));
		customizationTab.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionExpandBtn));
		transactionExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionFieldsExpandBtn));
		transactionFieldsExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(acc1ExpandBtn));
		acc1ExpandBtn.click();

		Thread.sleep(3999);

		Runtime.getRuntime().exec(getBaseDir() + "\\autoIt\\scripts\\accountDrag.exe");

		Thread.sleep(20000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(debitBtn));
		getAction().moveToElement(debitBtn).doubleClick(debitBtn).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(creditBtn));
		getAction().moveToElement(creditBtn).doubleClick(creditBtn).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(qunatityInAccounttransactionBtn));
		getAction().moveToElement(qunatityInAccounttransactionBtn).doubleClick(qunatityInAccounttransactionBtn).build()
				.perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rateInAccounttransactionBtn));
		getAction().moveToElement(rateInAccounttransactionBtn).doubleClick(rateInAccounttransactionBtn).build()
				.perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNo));
		getAction().moveToElement(documentNo).doubleClick(documentNo).build().perform();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(finishBtn));
		finishBtn.click();

		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : " + actMessage + " Value Expected : " + expMessage);

		if (actMessage.equalsIgnoreCase(expMessage)) {
			return true;
		} else {
			return false;
		}

	}

	public static boolean checkReportAccountingTransactionsCube()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(userNameDisplay));
		userNameDisplay.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(userNameDisplay));
		logoutOption.click();

		checkLogin();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
		searchTxt.click();
		searchTxt.sendKeys("Accounting Transactions-Cube");
		Thread.sleep(2000);
		searchTxt.sendKeys(Keys.ENTER);

		Thread.sleep(2000);

		try {
			if (sl_DateOptionDropdown.isDisplayed() == false) {
				getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
				searchTxt.click();
				searchTxt.sendKeys("Accounting Transactions-Cube");
				Thread.sleep(2000);
				searchTxt.sendKeys(Keys.ENTER);
			}
		} catch (Exception e) {
		}

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_DateOptionDropdown));

		Select s = new Select(sl_DateOptionDropdown);
		s.selectByValue("1");

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_OkBtn));
		sl_OkBtn.click();

		Thread.sleep(2000);
		boolean novalidationConfirmationMessage = validationConfirmationMessage.getText().isEmpty();

		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";

		Thread.sleep(3000);

		Thread.sleep(1500);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_1stRow1stCol));

		int reportsRow1ListCount = reportCol1List.size();
		ArrayList<String> reportsRow1ListArray = new ArrayList<String>();
		for (int i = 1; i < reportsRow1ListCount; i++) {
			String data = reportCol1List.get(i).getText();

			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			String expadjustBills = df.format(date);

			reportsRow1ListArray.add(data);
		}
		String actRow1List = reportsRow1ListArray.toString();
		String expRow1List = "[2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]";

		int report2ndRowListCount = reportCol2List.size();
		ArrayList<String> report2ndRowListArray = new ArrayList<String>();
		for (int i = 1; i < report2ndRowListCount; i++) {
			String data = reportCol2List.get(i).getText();
			report2ndRowListArray.add(data);
		}
		String actRow2List = report2ndRowListArray.toString();
		String expRow2List = "[Bank, Customer A, Journal Entries Control A/C, Opening Balances Control A/C, SR COGS POSTING ACC, STD RATE COGS ACC INV, VAT INPUT, Vendor B, Vendor Full Adjustment, Vendor New Reference, Grand Total]";

		int report3rdRowListCount = reportCol3List.size();
		ArrayList<String> report3rdRowListArray = new ArrayList<String>();
		for (int i = 1; i < report3rdRowListCount; i++) {
			String data = reportCol3List.get(i).getText();
			report3rdRowListArray.add(data);
		}
		String actRow3List = report3rdRowListArray.toString();
		String expRow3List = "[29.75, 20.00, 10.00, 20.00, , , , , 10.00, , 69.75]";

		int report4thRowListCount = reportCol4List.size();
		ArrayList<String> report4thRowListArray = new ArrayList<String>();
		for (int i = 1; i < report4thRowListCount; i++) {
			String data = reportCol4List.get(i).getText();
			report4thRowListArray.add(data);
		}
		String actRow4List = report4thRowListArray.toString();
		String expRow4List = "[30.00, 5.00, 10.00, , 120.00, 20.00, , 5.25, , 5.25, 175.50]";

		int reportsRow1ListCount1 = reportCol5List.size();
		ArrayList<String> reportsRow1ListArray1 = new ArrayList<String>();
		for (int i = 1; i < reportsRow1ListCount1; i++) {
			String data = reportCol5List.get(i).getText();

			reportsRow1ListArray1.add(data);
		}
		String actRow1List1 = reportsRow1ListArray1.toString();
		String expRow1List1 = "[, 1.00, , , , , , 1.00, , 1.00, 1.00]";

		int report2ndRowListCount1 = reportCol6List.size();
		ArrayList<String> report2ndRowListArray1 = new ArrayList<String>();
		for (int i = 1; i < report2ndRowListCount1; i++) {
			String data = reportCol6List.get(i).getText();
			report2ndRowListArray1.add(data);
		}
		String actRow2List1 = report2ndRowListArray1.toString();
		String expRow2List1 = "[, 15.00, , , , , , 5.00, , 5.00, 25.00]";

		int report2ndRowListCount7 = reportCol7List.size();
		ArrayList<String> report2ndRowListArray7 = new ArrayList<String>();
		for (int i = 1; i < report2ndRowListCount7; i++) {
			String data = reportCol7List.get(i).getText();
			report2ndRowListArray7.add(data);
		}
		String actRow2List7 = report2ndRowListArray7.toString();
		String expRow2List7 = "[1, 1, 1, 1, 1, 1, 1, 2, 2, 1, ]";

		Thread.sleep(2000);

		sl_NextBtn.click();

		Thread.sleep(2000);

		System.out.println("actCol1List  : " + actRow1List);
		System.out.println("expCol1List  : " + expRow1List);
		System.out.println("*********************************************************************");

		System.out.println("actCol2List  : " + actRow2List);
		System.out.println("expCol2List  : " + expRow2List);
		System.out.println("*********************************************************************");

		System.out.println("actCol3List  : " + actRow3List);
		System.out.println("expCol3List  : " + expRow3List);
		System.out.println("*********************************************************************");

		System.out.println("actCol4List  : " + actRow4List);
		System.out.println("expCol4List  : " + expRow4List);
		System.out.println("*********************************************************************");

		System.out.println("actCol5List  : " + actRow1List1);
		System.out.println("expCol5List : " + expRow1List1);
		System.out.println("*********************************************************************");

		System.out.println("actCol6List  :  " + actRow2List1);
		System.out.println("expCol6List  :  " + expRow2List1);
		System.out.println("*********************************************************************");

		System.out.println("actCol7List  : " + actRow2List7);
		System.out.println("expCol7List  : " + expRow2List7);
		System.out.println("*********************************************************************");

		if (actRow1List.equalsIgnoreCase(expRow1List) && actRow2List.equalsIgnoreCase(expRow2List)
				&& actRow3List.equalsIgnoreCase(expRow3List) && actRow4List.equalsIgnoreCase(expRow4List) &&

				actRow1List1.equalsIgnoreCase(expRow1List1) && actRow2List1.equalsIgnoreCase(expRow2List1) &&

				actRow2List7.equalsIgnoreCase(expRow2List7)) {
			System.out.println(" Test Pass: Values Dsiplayed as Expected ");
			return true;
		} else {
			System.out.println(" Test FAIL: Values Dsiplayed as Expected ");
			String meesage = validationConfirmationMessage.getText();
			System.err.println(" Meesage Displayed  : " + meesage);
			return false;
		}
	}

	@FindBy(xpath = "//body/section[@id='mainDiv']/div[@id='id_focus8_wrapper_default']/div[1]/section[1]/div[1]/div[1]/div[2]/div[3]/div[2]/div[1]/div[2]/ul[1]/li[1]/ul[1]/li[1]/ul[1]/li[34]/a[1]/span[1]")
	private static WebElement itemExpandBtnn;

	@FindBy(xpath = "//body/section[@id='mainDiv']/div[@id='id_focus8_wrapper_default']/div[1]/section[1]/div[1]/div[1]/div[2]/div[3]/div[2]/div[1]/div[2]/ul[1]/li[1]/ul[1]/li[1]/ul[1]/li[34]/ul[1]/li[4]/a[1]/span[1]")
	private static WebElement itemAliasBtn;

	public static boolean checkSavingAccountingTransactionsOfAnAccountCube()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {

		Thread.sleep(2999);

		getDriver().navigate().refresh();

		Thread.sleep(2999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(homeMenu));
		homeMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(utilities));
		utilities.click();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportDesignerMenu));
		reportDesignerMenu.click();

		Thread.sleep(2999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportNameDropdown));
		reportNameDropdown.sendKeys("Accounting Transactions of an account_Cube");
		reportNameDropdown.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportTypeDropdown));
		Select rtd = new Select(reportTypeDropdown);
		rtd.selectByVisibleText("Cubes");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(finanincalExpandbtn1));
		finanincalExpandbtn1.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(finanincalReportsExpandbtn));
		finanincalReportsExpandbtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dataSetTab));
		dataSetTab.click();

		Thread.sleep(2000);

		int transactionSetListCount = transactionSetList.size();

		for (int i = 0; i <= transactionSetListCount; i++) {
			String data = transactionSetList.get(i).getText();

			if (data.equalsIgnoreCase("Accounting transactions of an account")) {
				transactionSetList.get(i).click();

				break;
			}
		}

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTab));
		customizationTab.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionExpandBtn));
		transactionExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionFieldsExpandBtn));
		transactionFieldsExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(acc1ExpandBtn));
		acc1ExpandBtn.click();

		Thread.sleep(2999);

		Runtime.getRuntime().exec(getBaseDir() + "\\autoIt\\scripts\\accountDrag.exe");
		Thread.sleep(20000);

		getAction().moveToElement(itemExpandBtnn).perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemExpandBtnn));
		itemExpandBtnn.click();

		getAction().moveToElement(itemAliasBtn).perform();

		Thread.sleep(3000);
		Runtime.getRuntime().exec(getBaseDir() + "\\autoIt\\scripts\\itemDrag.exe");
		Thread.sleep(20000);

		getAction().moveToElement(documentNo).perform();

		Runtime.getRuntime().exec(getBaseDir() + "\\autoIt\\scripts\\CreditDebitDrag.exe");
		Thread.sleep(21000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(qunatityInAccounttransactionBtn));
		getAction().moveToElement(qunatityInAccounttransactionBtn).doubleClick(qunatityInAccounttransactionBtn).build()
				.perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rateInAccounttransactionBtn));
		getAction().moveToElement(rateInAccounttransactionBtn).doubleClick(rateInAccounttransactionBtn).build()
				.perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNo));
		getAction().moveToElement(documentNo).doubleClick(documentNo).build().perform();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(finishBtn));
		finishBtn.click();

		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : " + actMessage + " Value Expected : " + expMessage);

		if (actMessage.equalsIgnoreCase(expMessage)) {
			return true;
		} else {
			return false;
		}

	}

	public static boolean checkReportAccountingtransactionOfAnAccountCube()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(userNameDisplay));
		userNameDisplay.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(userNameDisplay));
		logoutOption.click();

		checkLogin();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
		searchTxt.click();
		searchTxt.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME);
		searchTxt.sendKeys("Accounting Transactions of an account_Cube");
		Thread.sleep(2000);
		searchTxt.sendKeys(Keys.ENTER);

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_DateOptionDropdown));
		/* sl_DateOptionDropdown.click(); */

		Select s = new Select(sl_DateOptionDropdown);
		s.selectByValue("1");

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_OkBtn));
		sl_OkBtn.click();

		Thread.sleep(2000);
		boolean novalidationConfirmationMessage = validationConfirmationMessage.getText().isEmpty();

		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";

		Thread.sleep(3000);

		Thread.sleep(1500);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_1stRow1stCol));

		int reportsRow1ListCount = reportCol1List.size();
		ArrayList<String> reportsRow1ListArray = new ArrayList<String>();
		for (int i = 1; i < reportsRow1ListCount; i++) {
			String data = reportCol1List.get(i).getText();

			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			String expadjustBills = df.format(date);

			reportsRow1ListArray.add(data);
		}
		String actRow1List = reportsRow1ListArray.toString();
		String expRow1List = "[2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19]";

		int report2ndRowListCount = reportCol2List.size();
		ArrayList<String> report2ndRowListArray = new ArrayList<String>();
		for (int i = 1; i < report2ndRowListCount; i++) {
			String data = reportCol2List.get(i).getText();
			report2ndRowListArray.add(data);
		}
		String actRow2List = report2ndRowListArray.toString();
		String expRow2List = "[Bank, COGS POSTING ACC, Customer A, Customer New Reference, Customer Semi Adjustment, Journal Entries Control A/C, Opening Balances Control A/C, PURCHASE VARIANCE, Sales - Computers, SR COGS POSTING ACC, STD RATE COGS ACC INV, VAT ADVANCE PURCHASE, VAT INPUT, Vendor B, Vendor Full Adjustment, Vendor New Reference, Vendor Semi Adjustment, Grand Total]";

		int report3rdRowListCount = reportCol3List.size();
		ArrayList<String> report3rdRowListArray = new ArrayList<String>();
		for (int i = 1; i < report3rdRowListCount; i++) {
			String data = reportCol3List.get(i).getText();
			report3rdRowListArray.add(data);
		}
		String actRow3List = report3rdRowListArray.toString();
		String expRow3List = "[2, 1, 2, 1, 4, 1, 1, 2, 1, 1, 1, 2, 1, 2, 2, 1, 1, ]";

		int report4thRowListCount = reportCol4List.size();
		ArrayList<String> report4thRowListArray = new ArrayList<String>();
		for (int i = 1; i < report4thRowListCount; i++) {
			String data = reportCol4List.get(i).getText();
			report4thRowListArray.add(data);
		}
		String actRow4List = report4thRowListArray.toString();
		String expRow4List = "[30, 20, , 5, 15, 10, 20, , , , 120, , , , 10, 10, 10, 480]";

		int reportsRow1ListCount1 = reportCol5List.size();
		ArrayList<String> reportsRow1ListArray1 = new ArrayList<String>();
		for (int i = 1; i < reportsRow1ListCount1; i++) {
			String data = reportCol5List.get(i).getText();

			reportsRow1ListArray1.add(data);
		}
		String actRow1List1 = reportsRow1ListArray1.toString();
		String expRow1List1 = "[40, , 15, 20, 15, 10, , , , 120, 20, , , , , 5, 5, 250]";

		int report2ndRowListCount1 = reportCol6List.size();
		ArrayList<String> report2ndRowListArray1 = new ArrayList<String>();
		for (int i = 1; i < report2ndRowListCount1; i++) {
			String data = reportCol6List.get(i).getText();
			report2ndRowListArray1.add(data);
		}
		String actRow2List1 = report2ndRowListArray1.toString();
		String expRow2List1 = "[, , 20, , , , , , 5, , 11, , , , , , , 36]";

		int report2ndRowListCount7 = reportCol7List.size();
		ArrayList<String> report2ndRowListArray7 = new ArrayList<String>();
		for (int i = 1; i < report2ndRowListCount7; i++) {
			String data = reportCol7List.get(i).getText();
			report2ndRowListArray7.add(data);
		}
		String actRow2List7 = report2ndRowListArray7.toString();
		String expRow2List7 = "[, , 5, , , , , , 20, , , , , 120, , 120, , 265]";

		int repor8col = reportCol8List.size();
		ArrayList<String> report8thColListArray = new ArrayList<String>();
		for (int i = 1; i < repor8col; i++) {
			String data = reportCol8List.get(i).getText();
			report8thColListArray.add(data);
		}
		String act8thColList = report8thColListArray.toString();
		String exp8thcolList = "[30, 20, 20, 5, 15, 10, 20, , 5, , 131, , , , 10, 10, 10, 515]";

		Thread.sleep(2000);

		sl_NextBtn.click();

		Thread.sleep(2000);

		System.out.println("actCol1List  : " + actRow1List);
		System.out.println("expCol1List  : " + expRow1List);
		System.out.println("*********************************************************************");

		System.out.println("actCol2List  : " + actRow2List);
		System.out.println("expCol2List  : " + expRow2List);
		System.out.println("*********************************************************************");

		System.out.println("actCol3List  : " + actRow3List);
		System.out.println("expCol3List  : " + expRow3List);
		System.out.println("*********************************************************************");

		System.out.println("actCol4List  : " + actRow4List);
		System.out.println("expCol4List  : " + expRow4List);
		System.out.println("*********************************************************************");

		System.out.println("actCol5List  : " + actRow1List1);
		System.out.println("expCol5List : " + expRow1List1);
		System.out.println("*********************************************************************");

		System.out.println("actCol6List  :  " + actRow2List1);
		System.out.println("expCol6List  :  " + expRow2List1);
		System.out.println("*********************************************************************");

		System.out.println("actCol7List  : " + actRow2List7);
		System.out.println("expCol7List  : " + expRow2List7);
		System.out.println("*********************************************************************");

		System.out.println("actCol7List  : " + act8thColList);
		System.out.println("expCol7List  : " + exp8thcolList);
		System.out.println("*********************************************************************");

		if (actRow1List.equalsIgnoreCase(expRow1List) && actRow2List.equalsIgnoreCase(expRow2List)
				&& actRow3List.equalsIgnoreCase(expRow3List) && actRow4List.equalsIgnoreCase(expRow4List) &&

				actRow1List1.equalsIgnoreCase(expRow1List1) && actRow2List1.equalsIgnoreCase(expRow2List1) &&

				actRow2List7.equalsIgnoreCase(expRow2List7)) {
			System.out.println(" Test Pass: Values Dsiplayed as Expected ");
			return true;
		} else {
			System.out.println(" Test FAIL: Values Dsiplayed as Expected ");
			String meesage = validationConfirmationMessage.getText();
			System.err.println(" Meesage Displayed  : " + meesage);
			return false;
		}

	}

	@FindBy(xpath = "//body/section[@id='mainDiv']/div[@id='id_focus8_wrapper_default']/div[1]/section[1]/div[1]/div[1]/div[2]/div[3]/div[2]/div[1]/div[2]/ul[1]/li[1]/ul[1]/li[2]/ul[1]/li[8]/a[1]/span[1]")
	private static WebElement departmentExpandBtn;

	@FindBy(xpath = "//body/section[@id='mainDiv']/div[@id='id_focus8_wrapper_default']/div[1]/section[1]/div[1]/div[1]/div[2]/div[3]/div[2]/div[1]/div[2]/ul[1]/li[1]/ul[1]/li[2]/ul[1]/li[8]/ul[1]/li[4]/a[1]/span[1]")
	private static WebElement jurisdictionTxt;

	@FindBy(xpath = "//span[contains(text(),'Voucher type')]")
	private static WebElement voucherTypeTxt;

	public static boolean checkSavingAccountingTransactionsOfAnAccountingTagCube()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {

		Thread.sleep(2999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(homeMenu));
		homeMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(utilities));
		utilities.click();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportDesignerMenu));
		reportDesignerMenu.click();

		Thread.sleep(2999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportNameDropdown));
		reportNameDropdown.sendKeys("Accounting Transactions of an accounting tag_cube");
		Thread.sleep(1999);
		reportNameDropdown.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportTypeDropdown));
		Select rtd = new Select(reportTypeDropdown);
		rtd.selectByVisibleText("Cubes");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(finanincalExpandbtn1));
		finanincalExpandbtn1.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(finanincalReportsExpandbtn));
		finanincalReportsExpandbtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dataSetTab));
		dataSetTab.click();

		Thread.sleep(2000);

		int transactionSetListCount = transactionSetList.size();

		for (int i = 0; i <= transactionSetListCount; i++) {
			String data = transactionSetList.get(i).getText();

			if (data.equalsIgnoreCase("Accounting transactions of accounting tag")) {
				transactionSetList.get(i).click();

				break;
			}
		}

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTab));
		customizationTab.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionExpandBtn));
		transactionExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(extraFieldsExpandBtn));
		extraFieldsExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(departmentExpandBtn));
		departmentExpandBtn.click();

		getAction().moveToElement(jurisdictionTxt).perform();

		Thread.sleep(2000);
		Runtime.getRuntime().exec(getBaseDir() + "\\autoIt\\scripts\\departmentDrag.exe");
		Thread.sleep(20000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(extraFieldsExpandBtn));
		extraFieldsExpandBtn.click();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionFieldsExpandBtn));
		transactionFieldsExpandBtn.click();

		getAction().moveToElement(voucherTypeTxt).perform();

		Thread.sleep(3000);
		Runtime.getRuntime().exec(getBaseDir() + "\\autoIt\\scripts\\voucherNameDrag.exe");
		Thread.sleep(20000);

		getAction().moveToElement(documentNo).perform();
		Thread.sleep(2000);

		Runtime.getRuntime().exec(getBaseDir() + "\\autoIt\\scripts\\CreditDebitDrag.exe");
		Thread.sleep(21000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(qunatityInAccounttransactionBtn));
		getAction().moveToElement(qunatityInAccounttransactionBtn).doubleClick(qunatityInAccounttransactionBtn).build()
				.perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rateInAccounttransactionBtn));
		getAction().moveToElement(rateInAccounttransactionBtn).doubleClick(rateInAccounttransactionBtn).build()
				.perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNo));
		getAction().moveToElement(documentNo).doubleClick(documentNo).build().perform();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(finishBtn));
		finishBtn.click();

		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : " + actMessage + " Value Expected : " + expMessage);

		if (actMessage.equalsIgnoreCase(expMessage)) {
			return true;
		} else {
			return false;
		}

	}

	@FindBy(xpath = "//*[@id='rd_customization_tree1608']/a/span")
	private static WebElement qunatityInAccounttransactionBtn;

	@FindBy(xpath = "//*[@id='rd_customization_tree1611']/a/span")
	private static WebElement rateInAccounttransactionBtn;

	@FindBy(xpath = "//*[@id='rd_customization_tree885']/a/span")
	private static WebElement documentNo;

	@FindBy(xpath = "//*[@id='rd_customization_tree866']/a/span")
	private static WebElement creditBtn;

	@FindBy(xpath = "//*[@id='rd_customization_tree884']/a/span")
	private static WebElement debitBtn;

	public boolean checkReportAccountingTransactionsofAnAccountingTag_cube()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(userNameDisplay));
		userNameDisplay.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(userNameDisplay));
		logoutOption.click();

		checkLogin();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
		searchTxt.click();
		searchTxt.sendKeys("Accounting Transactions of an accounting tag_cube");
		Thread.sleep(1000);
		searchTxt.sendKeys(Keys.ENTER);

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_DateOptionDropdown));
		/* sl_DateOptionDropdown.click(); */

		Select s = new Select(sl_DateOptionDropdown);
		s.selectByValue("1");

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportDepartmentTxt));
		reportDepartmentTxt.click();
		reportDepartmentTxt.sendKeys("Duabi");
		Thread.sleep(1000);
		reportDepartmentTxt.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_OkBtn));
		sl_OkBtn.click();

		Thread.sleep(2000);
		boolean novalidationConfirmationMessage = validationConfirmationMessage.getText().isEmpty();

		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";

		Thread.sleep(1500);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_1stRow1stCol));

		int reportsRow1ListCount = report1stRowList.size();
		ArrayList<String> reportsRow1ListArray = new ArrayList<String>();
		for (int i = 1; i < reportsRow1ListCount; i++) {
			String data = report1stRowList.get(i).getText();

			reportsRow1ListArray.add(data);
		}
		String actRow1List = reportsRow1ListArray.toString();
		String expRow1List = "[DUBAI, 1, , 15, 5, , 10, , 10, 10, , 20, 5, , 10, , 11, , , 25, 20, 20, 125, , 176, 70]";

		int report2ndRowListCount = report2ndRowList.size();
		ArrayList<String> report2ndRowListArray = new ArrayList<String>();
		for (int i = 1; i < report2ndRowListCount; i++) {
			String data = report2ndRowList.get(i).getText();
			report2ndRowListArray.add(data);
		}
		String actRow2List = report2ndRowListArray.toString();
		String expRow2List = "[Grand Total, , , 15, 5, , 10, , 10, 10, , 20, 5, , 10, , 11, , , 25, 20, 20, 125, , 176, 70]";

		System.out.println("actRow1List  : " + actRow1List);
		System.out.println("expRow1List  : " + expRow1List);
		System.out.println("*********************************************************************");

		System.out.println("actRow2List  : " + actRow2List);
		System.out.println("expRow2List  : " + expRow2List);
		System.out.println("*********************************************************************");

		if (actRow1List.equalsIgnoreCase(expRow1List) && actRow2List.equalsIgnoreCase(expRow2List)
				&& actvalidationConfirmationMessage.equalsIgnoreCase(expvalidationConfirmationMessage)) {
			System.out.println(" Test Pass: Values Dsiplayed as Expected ");
			return true;
		} else {
			System.out.println(" Test FAIL: Values Dsiplayed as Expected ");
			return false;
		}
	}

	public boolean checkAddingReportDesginerWithdataTypeCubeType()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {

		Thread.sleep(1999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(homeMenu));
		homeMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(utilities));
		utilities.click();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportDesignerMenu));
		reportDesignerMenu.click();

		Thread.sleep(2999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportNameDropdown));
		reportNameDropdown.sendKeys("Report With Quarry For Cube Type");
		Thread.sleep(2000);
		reportNameDropdown.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportTypeDropdown));
		Select rtd = new Select(reportTypeDropdown);
		rtd.selectByVisibleText("Cubes");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportDataSetTypeDrpdwn));
		Select rds = new Select(reportDataSetTypeDrpdwn);
		rds.selectByVisibleText("Query");

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(inventoryExpandBtn));
		inventoryExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportsBtn));
		reportsBtn.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dataSetTab));
		dataSetTab.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportDataSetQueryTxt));
		reportDataSetQueryTxt.click();

		reportDataSetQueryTxt.sendKeys("select  * from msec_users");

		Thread.sleep(2999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTab));
		customizationTab.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(ReportFieldExpandBtn));
		ReportFieldExpandBtn.click();

		int reportFieldlistCount = reportFieldlist.size();
		ArrayList<String> reportFieldlistarray = new ArrayList<>();
		for (int i = 0; i < reportFieldlistCount; i++) {
			String data = reportFieldlist.get(i).getText();
			reportFieldlistarray.add(data);
		}

		String actreportFieldlist = reportFieldlistarray.toString();
		String expreportFieldlist = "[iUserId, sLoginName, sLoginAbbr, iPwdPolicyId, sPassword, sUserName, sEmail, sPhone, sMobile, binImage, iGroupId, sSecurityQuestion, sSecurityAnswer, iUserType, iLinkId, sDomainName, sDomainUser, bAccountDisabled, bSendEmailNotification, bAllowMultipleLogin, bEmailonLoginFailure, bEmailUseronLoginSuccess, bDontLockAccount, iStatus, iNumInvalidAttempts, iLockedTill, iDays, iBlockFromDate, iBlockToDate, iTimeRestrictionStartDate, iTimeRestrictionEndDate, iTimeRestrictionStartTime, iTimeRestrictionEndTime, iLocation, iLanguage, iAltLanguage, iTimeZone, iUserAccess, iPWDChangeDate, fVal0, fVal1, fVal2, fVal3, fVal4, iCreatedBy, iModifiedBy, iCreatedDate, iModifiedDate, iCreatedTime, iModifiedTime, bModifiedDiffLoc, iSyncReceivedDate, iEditingLocation, iLocationId, sAuthenticationCode, biSignature, sEmailPwd, bEmailAuthPermission, sMacAddress]";

		System.out.println(" act reportFieldlist : " + actreportFieldlist);
		System.out.println(" exp reportFieldlist : " + expreportFieldlist);

		for (int i = 0; i < reportFieldlistCount; i++) {
			String data = reportFieldlist.get(i).getText();

			if (data.equalsIgnoreCase("iUserId")) {
				reportFieldlist.get(i).click();

				getAction().doubleClick(reportFieldlist.get(i)).build().perform();
			}
		}

		for (int i = 0; i < reportFieldlistCount; i++) {
			String data = reportFieldlist.get(i).getText();

			if (data.equalsIgnoreCase("sLoginName")) {
				reportFieldlist.get(i).click();

				getAction().doubleClick(reportFieldlist.get(i)).build().perform();
			}
		}

		for (int i = 0; i < reportFieldlistCount; i++) {
			String data = reportFieldlist.get(i).getText();

			if (data.equalsIgnoreCase("sLoginAbbr")) {
				reportFieldlist.get(i).click();

				getAction().doubleClick(reportFieldlist.get(i)).build().perform();
			}
		}

		for (int i = 0; i < reportFieldlistCount; i++) {
			String data = reportFieldlist.get(i).getText();

			if (data.equalsIgnoreCase("iPwdPolicyId")) {
				reportFieldlist.get(i).click();

				getAction().doubleClick(reportFieldlist.get(i)).build().perform();
			}
		}

		for (int i = 0; i < reportFieldlistCount; i++) {
			String data = reportFieldlist.get(i).getText();

			if (data.equalsIgnoreCase("sPassword")) {
				reportFieldlist.get(i).click();

				getAction().doubleClick(reportFieldlist.get(i)).build().perform();
			}
		}

		Thread.sleep(3999);

		Runtime.getRuntime().exec(getBaseDir() + "\\autoIt\\scripts\\CubeQurryType.exe");

		Thread.sleep(30000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(previewTab));
		previewTab.click();

		checkServerErrorMessage();

		System.out.println(" *********No Data Displayed in Preview TAB");

		Thread.sleep(3000);

		int previewTabHeaderListCount = previewTabHeaderList.size();

		ArrayList<String> previewTabHeaderListArray = new ArrayList<>();

		for (int i = 0; i < previewTabHeaderListCount; i++) {

			String data = previewTabHeaderList.get(i).getText();
			previewTabHeaderListArray.add(data);

		}
		String actpreviewTabHeaderList = previewTabHeaderListArray.toString();
		String exppreviewTabHeaderList = "";

		System.out.println(" Actual Preview TAb List   :" + actpreviewTabHeaderList);
		System.out.println("  Exp Preview TAb   List  :" + exppreviewTabHeaderList);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(finishBtn));
		finishBtn.click();

		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : " + actMessage + " Value Expected : " + expMessage);

		if (actMessage.equalsIgnoreCase(expMessage)) {

			System.out.println(" Test PasS: Extra Filed Programmable Filed added");
			return true;
		} else {
			System.out.println(" Test FAIL: Extra Filed Programmable Filed added");
			return false;
		}

	}

	@FindBy(xpath = "//*[@id='id_focus_msgbox_title']/div[2]/span")
	private static WebElement serverErrorCloseBtn;

	public boolean checkReportOFReportWithQuarryForCubeType()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(userNameDisplay));
		userNameDisplay.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(userNameDisplay));
		logoutOption.click();

		checkLogin();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
		searchTxt.click();
		searchTxt.sendKeys("Report With Quarry For Cube Type");
		Thread.sleep(1000);
		searchTxt.sendKeys(Keys.ENTER);

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_DateOptionDropdown));

		Select s = new Select(sl_DateOptionDropdown);
		s.selectByValue("1");

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_OkBtn));
		sl_OkBtn.click();

		// Validate Server Error Message
		checkServerErrorMessage();

		Thread.sleep(2000);
		boolean novalidationConfirmationMessage = validationConfirmationMessage.getText().isEmpty();

		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";

		Thread.sleep(1500);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_1stRow1stCol));

		int reportsRow1ListCount = reportCol2List.size();
		ArrayList<String> reportsRow1ListArray = new ArrayList<String>();
		for (int i = 2; i < reportsRow1ListCount; i++) {
			String data = reportCol2List.get(i).getText();

			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			String expadjustBills = df.format(date);

			reportsRow1ListArray.add(data);
		}
		String actRow1List = reportsRow1ListArray.toString();
		String expRow1List = "[iii@gmail.com, iiiiizzzz@gmail.com, naveenkumar@focussoftnet.com, raki@gmail.com, rathod@gmail.com, sai@gmail.com, sudheer@focussoftnet.com, sudheer@gmail.com, teja@gmail.com, Test@gmail.com, testfail@gmail.com, UserWithLoadOption@gmail.com, vasu@gmail.com, Grand Total]";

		int report2ndRowListCount = reportCol3List.size();
		ArrayList<String> report2ndRowListArray = new ArrayList<String>();
		for (int i = 2; i < report2ndRowListCount; i++) {
			String data = reportCol3List.get(i).getText();
			report2ndRowListArray.add(data);
		}
		String actRow2List = report2ndRowListArray.toString();
		String expRow2List = "[UUGCP, UWARRV, UAO, UAD, UIPOE, UPOR, USELS, UWAML, UWDL, UBWT, USELF, UPOR, UWP, ]";

		int report3rdRowListCount = reportCol4List.size();
		ArrayList<String> report3rdRowListArray = new ArrayList<String>();
		for (int i = 2; i < report3rdRowListCount; i++) {
			String data = reportCol4List.get(i).getText();
			report3rdRowListArray.add(data);
		}
		String actRow3List = report3rdRowListArray.toString();
		String expRow3List = "[9, 8, 6, 6, 1, 1, 8, 1, 8, 1, 8, 1, 6, ]";

		int report4thRowListCount = reportCol5List.size();
		ArrayList<String> report4thRowListArray = new ArrayList<String>();
		for (int i = 2; i < report4thRowListCount; i++) {
			String data = reportCol5List.get(i).getText();
			report4thRowListArray.add(data);
		}
		String actRow4List = report4thRowListArray.toString();
		String expRow4List = "[UserUGChangePassword, UserWithAccRestrictionsReportView, UserAllOptions, UserWithAccDisableRole, UserItemPOExclusion, UserPOTransRestrictions, UserSendEmailLoginSuccess, UserWithAllowMulLogin, UserWithDNotLock, UserBlockWithTime, UserSendEmailLoginFailure, UserPOTransRestrictions, UserWithPurchaseFAINV, ]";

		int report5thRowListCount = reportCol6List.size();
		ArrayList<String> report5thRowListArray = new ArrayList<String>();
		for (int i = 1; i < report5thRowListCount; i++) {
			String data = reportCol6List.get(i).getText();
			report5thRowListArray.add(data);
		}
		String actRow5List = report5thRowListArray.toString();
		String expRow5List = "[4.00, 14.00, 16.00, 2.00, 7.00, 6.00, 5.00, 10.00, 9.00, 8.00, 12.00, 11.00, 17.00, 3.00, 138.00]";

		System.out.println("actRow1List  : " + actRow1List);
		System.out.println("expRow1List  : " + expRow1List);
		System.out.println("*********************************************************************");

		System.out.println("actRow2List  : " + actRow2List);
		System.out.println("expRow2List  : " + expRow2List);
		System.out.println("*********************************************************************");

		System.out.println("actRow3List  : " + actRow3List);
		System.out.println("expRow3List  : " + expRow3List);
		System.out.println("*********************************************************************");

		System.out.println("actRow4List  : " + actRow4List);
		System.out.println("expRow4List  : " + expRow4List);
		System.out.println("*********************************************************************");

		System.out.println("actRow5List  : " + actRow5List);
		System.out.println("expRow5List  : " + expRow5List);
		System.out.println("*********************************************************************");

		if (actRow1List.equalsIgnoreCase(expRow1List) && actRow2List.equalsIgnoreCase(expRow2List)
				&& actRow3List.equalsIgnoreCase(expRow3List) && actRow4List.equalsIgnoreCase(expRow4List)
				&& actRow5List.equalsIgnoreCase(expRow5List)) {
			System.out.println(" Test Pass: Values Dsiplayed as Expected ");
			return true;
		} else {
			System.out.println(" Test FAIL: Values Dsiplayed as Expected ");
			return false;
		}
	}

	public boolean checkSavingRDReportsWihParameterOFtypeCube()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		checkNavigateToReportDesginer();

		Thread.sleep(2999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportNameDropdown));
		reportNameDropdown.sendKeys("Report Cube Type OF Parameter");
		reportNameDropdown.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportTypeDropdown));
		Select rtd = new Select(reportTypeDropdown);
		rtd.selectByVisibleText("Cubes");

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(inventoryExpandBtn));
		inventoryExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportsBtn));
		reportsBtn.click();

		Thread.sleep(2000);
		rdFieldNameTxt.click();
		rdFieldNameTxt.sendKeys("department");
		Thread.sleep(2000);

		rdFieldNameTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		Select s1 = new Select(rdFieldTypeDrpdwn);
		s1.selectByValue("3");

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdParametersOkbtn));
		rdParametersOkbtn.click();

		Thread.sleep(2999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdParametersTxtArea));
		String actrdParametersTxtArea = rdParametersTxtArea.getAttribute("data-fieldname");
		String ExprdParametersTxtArea = "department";

		System.out.println(
				" rdParametersTxtArea : " + actrdParametersTxtArea + " Value Expected  :" + ExprdParametersTxtArea);

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dataSetTab));
		dataSetTab.click();

		Thread.sleep(2000);

		int transactionSetListCount = transactionSetList.size();

		for (int i = 0; i <= transactionSetListCount; i++) {
			String data = transactionSetList.get(i).getText();

			if (data.equalsIgnoreCase("Inventory transactions")) {
				transactionSetList.get(i).click();

				break;
			}
		}

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdCusTabDfaultFilterTxt));
		rdCusTabDfaultFilterTxt.click();
		rdCusTabDfaultFilterTxt.sendKeys("DUBAI");
		Thread.sleep(1999);

		rdCusTabDfaultFilterTxt.sendKeys(Keys.TAB);

		Thread.sleep(1999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTab));
		customizationTab.click();

		Thread.sleep(2999);

		Runtime.getRuntime().exec(getBaseDir() + "\\autoIt\\scripts\\CubeParameter.exe");
		Thread.sleep(40000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(finishBtn));
		finishBtn.click();

		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : " + actMessage + " Value Expected : " + expMessage);

		if (actMessage.equalsIgnoreCase(expMessage)
				&& actrdParametersTxtArea.equalsIgnoreCase(ExprdParametersTxtArea)) {

			System.out.println(" Test PasS: Report desgining is saved with Paremeter with Cube Type");
			return true;
		} else {
			System.out.println(" Test FAIL: Report desgining is saved with Paremeter with Cube Type");
			return false;
		}

	}

	public boolean checkReportSavedWithCubeTypeParmeter()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(userNameDisplay));
		userNameDisplay.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(userNameDisplay));
		logoutOption.click();

		checkLogin();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
		searchTxt.click();
		searchTxt.sendKeys("Report Cube Type OF Parameter");
		Thread.sleep(1000);
		searchTxt.sendKeys(Keys.ENTER);

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_DateOptionDropdown));

		Select s = new Select(sl_DateOptionDropdown);
		s.selectByValue("1");

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_OkBtn));
		sl_OkBtn.click();

		checkServerErrorMessage();

		Thread.sleep(2000);
		boolean novalidationConfirmationMessage = validationConfirmationMessage.getText().isEmpty();

		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";

		Thread.sleep(1500);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_1stRow1stCol));

		int reportsRow1ListCount = report1stRowList.size();
		ArrayList<String> reportsRow1ListArray = new ArrayList<String>();
		for (int i = 1; i < reportsRow1ListCount; i++) {
			String data = report1stRowList.get(i).getText();

			reportsRow1ListArray.add(data);
		}
		String actRow1List = reportsRow1ListArray.toString();
		String expRow1List = "[STD RATE COGS ITEM, 5]";

		int report2ndRowListCount = report2ndRowList.size();
		ArrayList<String> report2ndRowListArray = new ArrayList<String>();
		for (int i = 1; i < report2ndRowListCount; i++) {
			String data = report2ndRowList.get(i).getText();
			report2ndRowListArray.add(data);
		}
		String actRow2List = report2ndRowListArray.toString();
		String expRow2List = "[Grand Total, 5]";

		System.out.println("actRow1List  : " + actRow1List);
		System.out.println("expRow1List  : " + expRow1List);
		System.out.println("*********************************************************************");

		System.out.println("actRow2List  : " + actRow2List);
		System.out.println("expRow2List  : " + expRow2List);
		System.out.println("*********************************************************************");

		if (actRow1List.equalsIgnoreCase(expRow1List) && actRow2List.equalsIgnoreCase(expRow2List)
				&& actvalidationConfirmationMessage.equalsIgnoreCase(expvalidationConfirmationMessage)) {
			System.out.println(" Test Pass: Values Dsiplayed as Expected ");
			return true;
		} else {
			System.out.println(" Test FAIL: Values Dsiplayed as Expected ");
			return false;
		}

	}

	public boolean checkAddingAndDeletingParameterWithDataTypeCubes() throws InterruptedException {

		checkNavigateToReportDesginer();

		Thread.sleep(2999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportNameDropdown));
		reportNameDropdown.sendKeys("Report Delete Parameter");
		reportNameDropdown.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportTypeDropdown));
		Select rtd = new Select(reportTypeDropdown);
		rtd.selectByVisibleText("Cubes");

		rdFieldNameTxt.click();
		rdFieldNameTxt.sendKeys("warehouse");
		Thread.sleep(2000);

		rdFieldNameTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		Select s1 = new Select(rdFieldTypeDrpdwn);
		s1.selectByValue("4");

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdParametersOkbtn));
		rdParametersOkbtn.click();

		Thread.sleep(2999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdParametersTxtArea));
		String actrdParametersTxtArea = rdParametersTxtArea.getAttribute("data-fieldname");
		String ExprdParametersTxtArea = "warehouse";

		System.out.println(" rdParametersTxtArea : " + actrdParametersTxtArea);

		rdParametersTxtArea.click();
		Thread.sleep(2999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdReportParameterDeleteBtn));
		rdReportParameterDeleteBtn.click();
		Thread.sleep(2000);
		getWaitForAlert();

		String actAlert = getAlert().getText();
		String expAlert = "Do you want to Delete?";

		System.out.println(" Alert Displayed Text : " + actAlert + " Value Expected : " + expAlert);

		getAlert().accept();

		boolean novalidationConfirmationMessage = validationConfirmationMessage.getText().isEmpty();

		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";

		if (actvalidationConfirmationMessage.equalsIgnoreCase(expvalidationConfirmationMessage)
				&& actAlert.equalsIgnoreCase(expAlert)) {

			System.out.println(" Test Pass: Parameter Added And Deleted Successfully");
			return true;

		} else {
			System.out.println(" Test Fail: Parameter Added And Deleted Successfully");
			return false;

		}
	}

	public boolean checkResetOptionAndSaveWithOutSelectingDisplayReportinReportDesginer()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(finishBtn));
		finishBtn.click();

		String expMessage = "Please Select menu to display report.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : " + actMessage + " Value Expected : " + expMessage);

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(resetBtn));
		resetBtn.click();

		Thread.sleep(2000);

		boolean reportNameDropdownMessage = reportNameDropdown.getText().isEmpty();

		String actreportNameDropdown = Boolean.toString(reportNameDropdownMessage);
		String expreportNameDropdown = "true";

		if (actreportNameDropdown.equalsIgnoreCase(expreportNameDropdown) && actMessage.equalsIgnoreCase(expMessage)) {
			return true;
		} else {
			checkServerErrorMessage();
			return false;
		}

	}

	public boolean checkRdWithCubesTypeAddingProgrammableFiled()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		checkNavigateToReportDesginer();

		Thread.sleep(2999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportNameDropdown));
		reportNameDropdown.sendKeys("Report With Programmable Filed");
		reportNameDropdown.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportTypeDropdown));
		Select rtd = new Select(reportTypeDropdown);
		rtd.selectByVisibleText("Cubes");

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(inventoryExpandBtn));
		inventoryExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportsBtn));
		reportsBtn.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dataSetTab));
		dataSetTab.click();

		Thread.sleep(2000);

		int transactionSetListCount = transactionSetList.size();

		for (int i = 0; i <= transactionSetListCount; i++) {
			String data = transactionSetList.get(i).getText();

			if (data.equalsIgnoreCase("Inventory transactions")) {
				transactionSetList.get(i).click();

				break;
			}
		}

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTab));
		customizationTab.click();
		Thread.sleep(1999);

		Runtime.getRuntime().exec(getBaseDir() + "\\autoIt\\scripts\\CubeProgrammable.exe");
		Thread.sleep(40000);

		int customizeTabTableHeaderLsistCount = customizeTabTableHeaderLsist.size();
		System.err.println(customizeTabTableHeaderLsistCount);
		for (int i = 1; i <= customizeTabTableHeaderLsistCount; i++) {
			String data = customizeTabTableHeaderLsist.get(i).getText();

			if (data.equalsIgnoreCase("Programmable Field")) {
				customizeTabTableHeaderLsist.get(i).click();

				System.err.println(i);
				Thread.sleep(1000);

				break;
			}
		}

		Thread.sleep(1999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdExtraFiledColHeadingTxt));
		rdExtraFiledColHeadingTxt.click();
		rdExtraFiledColHeadingTxt.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME);
		rdExtraFiledColHeadingTxt.sendKeys("AddedRateField");
		Thread.sleep(1999);

		rdExtraFiledColHeadingTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		getAction().moveToElement(rdExtraFiledFormulaControlTxt).build().perform();
		Thread.sleep(1999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdExtraFiledFormulaControlTxt));
		rdExtraFiledFormulaControlTxt.click();

		rdExtraFiledFormulaControlTxt.sendKeys("c2+1");
		Thread.sleep(1000);

		getAction().moveToElement(rdExtraFiledFormulaControlOkBtn).build().perform();
		Thread.sleep(1999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdExtraFiledFormulaControlOkBtn));
		rdExtraFiledFormulaControlOkBtn.click();

		Thread.sleep(1999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdExtraFiledOkBtn));
		rdExtraFiledOkBtn.click();

		Thread.sleep(3000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(finishBtn));
		finishBtn.click();

		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : " + actMessage + " Value Expected : " + expMessage);

		if (actMessage.equalsIgnoreCase(expMessage)) {

			System.out.println(" Test PasS: Report desgining is saved with Paremeter with Cube Type");
			return true;
		} else {
			System.out.println(" Test FAIL: Report desgining is saved with Paremeter with Cube Type");
			return false;
		}

	}

	public boolean CheckAddingFilterInFiledsCreatedCustomizationTab()
			throws InterruptedException, IOException, EncryptedDocumentException, InvalidFormatException {

		System.out.println(
				"***********************************CheckAddingFilterInFiledsCreatedCustomizationTab*************************************************");

		checkNavigateToReportDesginer();

		Thread.sleep(2999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportNameDropdown));
		reportNameDropdown.sendKeys("MR With Filter");
		Thread.sleep(2000);
		reportNameDropdown.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportTypeDropdown));
		Select rtd = new Select(reportTypeDropdown);
		rtd.selectByVisibleText("Details");

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(finanincalExpandbtn1));
		finanincalExpandbtn1.click();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(finanincalReportsExpandbtn));
		finanincalReportsExpandbtn.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dataSetTab));
		dataSetTab.click();

		Thread.sleep(2000);

		int transactionSetListCount = transactionSetList.size();

		for (int i = 0; i <= transactionSetListCount; i++) {
			String data = transactionSetList.get(i).getText();

			if (data.equalsIgnoreCase("All transactions of document type")) {
				transactionSetList.get(i).click();

				break;
			}
		}

		Thread.sleep(2000);

		Select voucher = new Select(dataSetTabVouchersDocType);
		voucher.selectByVisibleText("Material Requisition");

		Thread.sleep(2000);

		Thread.sleep(3999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTab));
		customizationTab.click();

		Thread.sleep(1999);

		Runtime.getRuntime().exec(getBaseDir() + "\\autoIt\\scripts\\EditingProgrammable.exe");

		Thread.sleep(40000);

		int customizeTabTableHeaderLsistCount2 = customizeTabTableHeaderLsist.size();
		System.err.println(customizeTabTableHeaderLsistCount2);
		for (int i = 1; i <= customizeTabTableHeaderLsistCount2; i++) {
			String data = customizeTabTableHeaderLsist.get(i).getText();

			if (data.equalsIgnoreCase("Gross")) {
				customizeTabTableHeaderLsist.get(i).click();

				System.err.println(i);
				Thread.sleep(1000);
				break;
			}
		}

		getAction().moveToElement(rdReportExtraFieldFilterExpandBtn).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdReportExtraFieldFilterExpandBtn));
		rdReportExtraFieldFilterExpandBtn.click();

		Thread.sleep(2999);

		getAction().moveToElement(extraFieldCustIcon).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(extraFieldCustIcon));
		extraFieldCustIcon.click();

		Thread.sleep(2999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(custPopWareHouseExpandBtn));
		custPopWareHouseExpandBtn.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(custPopWareHouseNameChkbox));
		if (custPopWareHouseNameChkbox.isSelected() == false) {
			custPopWareHouseNameChkbox.click();

		}

		Thread.sleep(2999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(custPopOkBtn));
		custPopOkBtn.click();

		System.err.println(" Check Warehouse Nae chk box in customization ");

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(extraFieldDefaultFiltertxt));
		extraFieldDefaultFiltertxt.click();

		extraFieldDefaultFiltertxt.sendKeys("HYDERABAD");
		Thread.sleep(1999);
		extraFieldDefaultFiltertxt.sendKeys(Keys.TAB);
		System.err.println(" ***Displayed WareHouse Default Filter Text ");

		extraFieldDefaultFilterOkBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(finishBtn));
		finishBtn.click();

		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : " + actMessage + " Value Expected : " + expMessage);

		Thread.sleep(2999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportNameDropdown));
		reportNameDropdown.sendKeys("MR With Filter");
		Thread.sleep(2000);
		reportNameDropdown.sendKeys(Keys.TAB);

		Thread.sleep(3999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTab));
		customizationTab.click();

		System.err.println(customizeTabTableHeaderLsistCount2);
		for (int i = 1; i <= customizeTabTableHeaderLsistCount2; i++) {
			String data = customizeTabTableHeaderLsist.get(i).getText();

			if (data.equalsIgnoreCase("Gross")) {
				customizeTabTableHeaderLsist.get(i).click();

				System.err.println(i);
				Thread.sleep(1000);
				break;
			}
		}

		getAction().moveToElement(rdReportExtraFieldFilterExpandBtn).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdReportExtraFieldFilterExpandBtn));
		rdReportExtraFieldFilterExpandBtn.click();

		Thread.sleep(2999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(extraFieldDefaultFilterOkBtn));
		extraFieldDefaultFilterOkBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(finishBtn));
		finishBtn.click();

		String expMessage1 = "Data saved successfully.";

		String actMessage1 = checkValidationMessage(expMessage1);

		System.out.println("Validation Message1 : " + actMessage1 + " Value Expected : " + expMessage1);

		if (actMessage.equalsIgnoreCase(expMessage)) {

			return true;
		} else {
			return false;
		}
	}

	public boolean checkReportMRWithFilter()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		/*
		 * Thread.sleep(2000);
		 * getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(
		 * userNameDisplay)); userNameDisplay.click();
		 * 
		 * getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(
		 * userNameDisplay)); logoutOption.click();
		 * 
		 * checkLogin();
		 */
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
		searchTxt.click();
		searchTxt.sendKeys("MR With Filter");
		Thread.sleep(1000);
		searchTxt.sendKeys(Keys.ENTER);

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_DateOptionDropdown));

		Select s = new Select(sl_DateOptionDropdown);
		s.selectByValue("1");

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_OkBtn));
		sl_OkBtn.click();

		if (sl_1stRow1stCol.isDisplayed() == false) {
			checkServerErrorMessage();
		}

		Thread.sleep(2000);
		boolean novalidationConfirmationMessage = validationConfirmationMessage.getText().isEmpty();

		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";

		Thread.sleep(1500);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_1stRow1stCol));

		int reportsRow1ListCount = reportCol1List.size();
		ArrayList<String> reportsRow1ListArray = new ArrayList<String>();
		for (int i = 1; i < reportsRow1ListCount; i++) {
			String data = reportCol1List.get(i).getText();

			reportsRow1ListArray.add(data);
		}
		String actRow1List = reportsRow1ListArray.toString();
		String expRow1List = "[2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19]";

		int report2ndRowListCount = reportCol2List.size();
		ArrayList<String> report2ndRowListArray = new ArrayList<String>();
		for (int i = 1; i < report2ndRowListCount; i++) {
			String data = reportCol2List.get(i).getText();
			report2ndRowListArray.add(data);
		}
		String actRow2List = report2ndRowListArray.toString();
		String expRow2List = "[AMERICA, DUBAI, DUBAI, INDIA, INDIA, INDIA, INDIA, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI]";

		int report3rdRowListCount = reportCol3List.size();
		ArrayList<String> report3rdRowListArray = new ArrayList<String>();
		for (int i = 1; i < report3rdRowListCount; i++) {
			String data = reportCol3List.get(i).getText();
			report3rdRowListArray.add(data);
		}
		String actRow3List = report3rdRowListArray.toString();
		String expRow3List = "[HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, SECUNDERABAD, SECUNDERABAD, SECUNDERABAD]";

		int report4thRowListCount = reportCol4List.size();
		ArrayList<String> report4thRowListArray = new ArrayList<String>();
		for (int i = 1; i < report4thRowListCount; i++) {
			String data = reportCol4List.get(i).getText();
			report4thRowListArray.add(data);
		}
		String actRow4List = report4thRowListArray.toString();
		String expRow4List = "[11.00, 110.00, 11.00, 110.00, 11.00, 110.00, 11.00, 110.00, 11.00, 110.00, 11.00, 110.00, 11.00, 110.00, 11.00, , , ]";

		int report5thRowListCount = reportCol5List.size();
		ArrayList<String> report5thRowListArray = new ArrayList<String>();
		for (int i = 1; i < report5thRowListCount; i++) {
			String data = reportCol5List.get(i).getText();
			report5thRowListArray.add(data);
		}
		String actRow5List = report5thRowListArray.toString();
		String expRow5List = "[BR COGS ITEM, BATCH BIN WITH NO RESERVATION ITEM, BR COGS ITEM, BATCH BIN WITH NO RESERVATION ITEM, BR COGS ITEM, BATCH BIN WITH NO RESERVATION ITEM, BR COGS ITEM, BATCH BIN WITH NO RESERVATION ITEM, BR COGS ITEM, BATCH BIN WITH NO RESERVATION ITEM, BR COGS ITEM, BATCH BIN WITH NO RESERVATION ITEM, BR COGS ITEM, BATCH BIN WITH NO RESERVATION ITEM, BR COGS ITEM, BATCH BIN WITH NO RESERVATION ITEM, BR COGS ITEM, BATCH BIN WITH NO RESERVATION ITEM]";

		int report6thRowListCount = reportCol6List.size();
		ArrayList<String> report6thRowListArray = new ArrayList<String>();
		for (int i = 1; i < report6thRowListCount; i++) {
			String data = reportCol6List.get(i).getText();
			report6thRowListArray.add(data);
		}
		String actRow6List = report6thRowListArray.toString();
		String expRow6List = "[BR COGS ITEM, BBWNRI, BR COGS ITEM, BBWNRI, BR COGS ITEM, BBWNRI, BR COGS ITEM, BBWNRI, BR COGS ITEM, BBWNRI, BR COGS ITEM, BBWNRI, BR COGS ITEM, BBWNRI, BR COGS ITEM, BBWNRI, BR COGS ITEM, BBWNRI]";

		int report7thRowListCount = reportCol7List.size();
		ArrayList<String> report7thRowListArray = new ArrayList<String>();
		for (int i = 1; i < report7thRowListCount; i++) {
			String data = reportCol7List.get(i).getText();
			report7thRowListArray.add(data);
		}
		String actRow7List = report7thRowListArray.toString();
		String expRow7List = "[, 121.00, , 242.00, , , , 484.00, , , , , , , , 484.00, , ]";

		int report8thRowListCount = reportCol8List.size();
		ArrayList<String> report8thRowListArray = new ArrayList<String>();
		for (int i = 1; i < report8thRowListCount; i++) {
			String data = reportCol8List.get(i).getText();
			report8thRowListArray.add(data);
		}
		String actRow8List = report8thRowListArray.toString();
		String expRow8List = "[1.00, 10.00, 1.00, 10.00, 1.00, 10.00, 1.00, 10.00, 1.00, 10.00, 1.00, 10.00, 1.00, 10.00, 1.00, 10.00, 1.00, 10.00]";

		System.out.println("actRow1List  : " + actRow1List);
		System.out.println("expRow1List  : " + expRow1List);
		System.out.println("*********************************************************************");

		System.out.println("actRow2List  : " + actRow2List);
		System.out.println("expRow2List  : " + expRow2List);
		System.out.println("*********************************************************************");

		System.out.println("actRow3List  : " + actRow3List);
		System.out.println("expRow3List  : " + expRow3List);
		System.out.println("*********************************************************************");

		System.out.println("actRow4List  : " + actRow4List);
		System.out.println("expRow4List  : " + expRow4List);
		System.out.println("*********************************************************************");

		System.out.println("actRow5List  : " + actRow5List);
		System.out.println("expRow5List  : " + expRow5List);
		System.out.println("*********************************************************************");

		System.out.println("actRow6List  : " + actRow6List);
		System.out.println("expRow6List  : " + expRow6List);
		System.out.println("*********************************************************************");

		System.out.println("actRow7List  : " + actRow7List);
		System.out.println("expRow7List  : " + expRow7List);
		System.out.println("*********************************************************************");

		System.out.println("actRow8List  : " + actRow8List);
		System.out.println("expRow8List  : " + expRow8List);
		System.out.println("*********************************************************************");

		if (actRow1List.equalsIgnoreCase(expRow1List) && actRow2List.equalsIgnoreCase(expRow2List) &&

				actRow3List.equalsIgnoreCase(expRow3List) && actRow4List.equalsIgnoreCase(expRow4List)
				&& actRow5List.equalsIgnoreCase(expRow5List) && actRow6List.equalsIgnoreCase(expRow6List)
				&& actRow7List.equalsIgnoreCase(expRow7List) && actRow8List.equalsIgnoreCase(expRow8List)
				&& actvalidationConfirmationMessage.equalsIgnoreCase(expvalidationConfirmationMessage)) {
			System.out.println(" Test Pass: Values Dsiplayed as Expected ");
			return true;
		} else {
			System.out.println(" Test FAIL: Values Dsiplayed as Expected ");
			return false;
		}
	}

	public boolean checkAddingProgrammableFiledWithColAttaributeAndEditingColName()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		checkNavigateToReportDesginer();

		Thread.sleep(2999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportNameDropdown));
		reportNameDropdown.sendKeys("MR With Filter");
		Thread.sleep(2000);
		reportNameDropdown.sendKeys(Keys.TAB);

		Thread.sleep(2999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTab));
		customizationTab.click();

		Thread.sleep(1999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(custProgramFiledexpandBtn2));
		custProgramFiledexpandBtn2.click();

		Thread.sleep(1000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(custProgramFieldBtn2));
		getAction().doubleClick(custProgramFieldBtn2).build().perform();

		int customizeTabTableHeaderLsistCount = customizeTabTableHeaderLsist.size();
		System.err.println(customizeTabTableHeaderLsistCount);
		for (int i = 1; i <= customizeTabTableHeaderLsistCount; i++) {
			String data = customizeTabTableHeaderLsist.get(i).getText();

			if (data.equalsIgnoreCase("Programmable Field")) {
				customizeTabTableHeaderLsist.get(i).click();

				System.err.println(i);
				Thread.sleep(1000);

				break;
			}
		}

		Thread.sleep(1999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdExtraFiledColHeadingTxt));
		rdExtraFiledColHeadingTxt.click();
		rdExtraFiledColHeadingTxt.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME);
		rdExtraFiledColHeadingTxt.sendKeys("ToGrossAddedField");
		Thread.sleep(1999);

		rdExtraFiledColHeadingTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		getAction().moveToElement(rdExtraFiledFormulaControlTxt).build().perform();
		Thread.sleep(1999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdExtraFiledFormulaControlTxt));
		rdExtraFiledFormulaControlTxt.click();

		rdExtraFiledFormulaControlTxt.sendKeys("c7+1");
		Thread.sleep(1000);

		getAction().moveToElement(rdExtraFiledFormulaControlOkBtn).build().perform();
		Thread.sleep(1999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdExtraFiledFormulaControlOkBtn));
		rdExtraFiledFormulaControlOkBtn.click();

		Thread.sleep(1999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdExtraFiledOkBtn));
		rdExtraFiledOkBtn.click();

		Thread.sleep(3000);

		int customizeTabTableHeaderLsistCount1 = customizeTabTableHeaderLsist.size();
		System.err.println(customizeTabTableHeaderLsistCount1);
		for (int i = 1; i <= customizeTabTableHeaderLsistCount1; i++) {
			String data = customizeTabTableHeaderLsist.get(i).getText();

			if (data.equalsIgnoreCase("Quantity")) {
				customizeTabTableHeaderLsist.get(i).click();

				System.err.println(i);
				Thread.sleep(1000);

				break;
			}
		}

		Thread.sleep(1999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdExtraFiledColHeadingTxt));
		rdExtraFiledColHeadingTxt.click();
		rdExtraFiledColHeadingTxt.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME);
		rdExtraFiledColHeadingTxt.sendKeys("QUANTITY");
		Thread.sleep(1999);

		rdExtraFiledColHeadingTxt.sendKeys(Keys.TAB);
		Thread.sleep(1999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdExtraFiledOkBtn));
		rdExtraFiledOkBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(finishBtn));
		finishBtn.click();

		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : " + actMessage + " Value Expected : " + expMessage);

		if (actMessage.equalsIgnoreCase(expMessage)) {

			System.out.println(" Test PasS: Extra Filed Programmable Filed added");
			return true;
		} else {
			System.out.println(" Test FAIL: Extra Filed Programmable Filed added");
			return false;
		}

	}

	public boolean checkReportMRAfterChangesInCustomizationtab()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(userNameDisplay));
		userNameDisplay.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(userNameDisplay));
		logoutOption.click();

		checkLogin();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
		searchTxt.click();
		searchTxt.sendKeys("MR With Filter");
		Thread.sleep(1000);
		searchTxt.sendKeys(Keys.ENTER);

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_DateOptionDropdown));

		Select s = new Select(sl_DateOptionDropdown);
		s.selectByValue("1");

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_OkBtn));
		sl_OkBtn.click();

		if (sl_1stRow1stCol.isDisplayed() == false) {
			checkServerErrorMessage();
		}

		Thread.sleep(2000);
		boolean novalidationConfirmationMessage = validationConfirmationMessage.getText().isEmpty();

		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";

		int reportHeaderListCount = reportHeaderList.size();

		ArrayList<String> reportHeaderListArray = new ArrayList<>();

		for (int i = 0; i < reportHeaderListCount; i++) {
			String data = reportHeaderList.get(i).getText();
			reportHeaderListArray.add(data);
		}

		String actreportHeaderList = reportHeaderListArray.toString();
		String expreportHeaderList = "[#, Department.Name, Warehouse.Name, Gross, Item.Name, Item.Code, Net amount, QUANTITY, ToGrossAddedField]";

		System.err.println(" Act reportHeaderList : " + actreportHeaderList);
		System.err.println(" Exp reportHeaderList : " + expreportHeaderList);

		Thread.sleep(1500);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_1stRow1stCol));

		int reportsRow1ListCount = reportCol1List.size();
		ArrayList<String> reportsRow1ListArray = new ArrayList<String>();
		for (int i = 1; i < reportsRow1ListCount; i++) {
			String data = reportCol1List.get(i).getText();

			reportsRow1ListArray.add(data);
		}
		String actRow1List = reportsRow1ListArray.toString();
		String expRow1List = "[2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19]";

		int report2ndRowListCount = reportCol2List.size();
		ArrayList<String> report2ndRowListArray = new ArrayList<String>();
		for (int i = 1; i < report2ndRowListCount; i++) {
			String data = reportCol2List.get(i).getText();
			report2ndRowListArray.add(data);
		}
		String actRow2List = report2ndRowListArray.toString();
		String expRow2List = "[AMERICA, DUBAI, DUBAI, INDIA, INDIA, INDIA, INDIA, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI]";

		int report3rdRowListCount = reportCol3List.size();
		ArrayList<String> report3rdRowListArray = new ArrayList<String>();
		for (int i = 1; i < report3rdRowListCount; i++) {
			String data = reportCol3List.get(i).getText();
			report3rdRowListArray.add(data);
		}
		String actRow3List = report3rdRowListArray.toString();
		String expRow3List = "[HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, SECUNDERABAD, SECUNDERABAD, SECUNDERABAD]";

		int report4thRowListCount = reportCol4List.size();
		ArrayList<String> report4thRowListArray = new ArrayList<String>();
		for (int i = 1; i < report4thRowListCount; i++) {
			String data = reportCol4List.get(i).getText();
			report4thRowListArray.add(data);
		}
		String actRow4List = report4thRowListArray.toString();
		String expRow4List = "[11.00, 110.00, 11.00, 110.00, 11.00, 110.00, 11.00, 110.00, 11.00, 110.00, 11.00, 110.00, 11.00, 110.00, 11.00, , , ]";

		int report5thRowListCount = reportCol5List.size();
		ArrayList<String> report5thRowListArray = new ArrayList<String>();
		for (int i = 1; i < report5thRowListCount; i++) {
			String data = reportCol5List.get(i).getText();
			report5thRowListArray.add(data);
		}
		String actRow5List = report5thRowListArray.toString();
		String expRow5List = "[BR COGS ITEM, BATCH BIN WITH NO RESERVATION ITEM, BR COGS ITEM, BATCH BIN WITH NO RESERVATION ITEM, BR COGS ITEM, BATCH BIN WITH NO RESERVATION ITEM, BR COGS ITEM, BATCH BIN WITH NO RESERVATION ITEM, BR COGS ITEM, BATCH BIN WITH NO RESERVATION ITEM, BR COGS ITEM, BATCH BIN WITH NO RESERVATION ITEM, BR COGS ITEM, BATCH BIN WITH NO RESERVATION ITEM, BR COGS ITEM, BATCH BIN WITH NO RESERVATION ITEM, BR COGS ITEM, BATCH BIN WITH NO RESERVATION ITEM]";

		int report6thRowListCount = reportCol6List.size();
		ArrayList<String> report6thRowListArray = new ArrayList<String>();
		for (int i = 1; i < report6thRowListCount; i++) {
			String data = reportCol6List.get(i).getText();
			report6thRowListArray.add(data);
		}
		String actRow6List = report6thRowListArray.toString();
		String expRow6List = "[BR COGS ITEM, BBWNRI, BR COGS ITEM, BBWNRI, BR COGS ITEM, BBWNRI, BR COGS ITEM, BBWNRI, BR COGS ITEM, BBWNRI, BR COGS ITEM, BBWNRI, BR COGS ITEM, BBWNRI, BR COGS ITEM, BBWNRI, BR COGS ITEM, BBWNRI]";

		int report7thRowListCount = reportCol7List.size();
		ArrayList<String> report7thRowListArray = new ArrayList<String>();
		for (int i = 1; i < report7thRowListCount; i++) {
			String data = reportCol7List.get(i).getText();
			report7thRowListArray.add(data);
		}
		String actRow7List = report7thRowListArray.toString();
		String expRow7List = "[, 121.00, , 242.00, , , , 484.00, , , , , , , , 484.00, , ]";

		int report8thRowListCount = reportCol8List.size();
		ArrayList<String> report8thRowListArray = new ArrayList<String>();
		for (int i = 1; i < report8thRowListCount; i++) {
			String data = reportCol8List.get(i).getText();
			report8thRowListArray.add(data);
		}
		String actRow8List = report8thRowListArray.toString();
		String expRow8List = "[1.00, 10.00, 1.00, 10.00, 1.00, 10.00, 1.00, 10.00, 1.00, 10.00, 1.00, 10.00, 1.00, 10.00, 1.00, 10.00, 1.00, 10.00]";

		int report9thRowListCount = reportCol9List.size();
		ArrayList<String> report9thRowListArray = new ArrayList<String>();
		for (int i = 1; i < report9thRowListCount; i++) {
			String data = reportCol9List.get(i).getText();
			report9thRowListArray.add(data);
		}
		String actRow9List = report9thRowListArray.toString();
		String expRow9List = "[2.00, 11.00, 2.00, 11.00, 2.00, 11.00, 2.00, 11.00, 2.00, 11.00, 2.00, 11.00, 2.00, 11.00, 2.00, 11.00, 2.00, 11.00]";

		System.out.println("actRow1List  : " + actRow1List);
		System.out.println("expRow1List  : " + expRow1List);
		System.out.println("*********************************************************************");

		System.out.println("actRow2List  : " + actRow2List);
		System.out.println("expRow2List  : " + expRow2List);
		System.out.println("*********************************************************************");

		System.out.println("actRow3List  : " + actRow3List);
		System.out.println("expRow3List  : " + expRow3List);
		System.out.println("*********************************************************************");

		System.out.println("actRow4List  : " + actRow4List);
		System.out.println("expRow4List  : " + expRow4List);
		System.out.println("*********************************************************************");

		System.out.println("actRow5List  : " + actRow5List);
		System.out.println("expRow5List  : " + expRow5List);
		System.out.println("*********************************************************************");

		System.out.println("actRow6List  : " + actRow6List);
		System.out.println("expRow6List  : " + expRow6List);
		System.out.println("*********************************************************************");

		System.out.println("actRow7List  : " + actRow7List);
		System.out.println("expRow7List  : " + expRow7List);
		System.out.println("*********************************************************************");

		System.out.println("actRow8List  : " + actRow8List);
		System.out.println("expRow8List  : " + expRow8List);
		System.out.println("*********************************************************************");

		System.out.println("actRow9List  : " + actRow9List);
		System.out.println("expRow9List  : " + expRow9List);
		System.out.println("*********************************************************************");

		if (actRow1List.equalsIgnoreCase(expRow1List) && actRow2List.equalsIgnoreCase(expRow2List) &&

				actRow3List.equalsIgnoreCase(expRow3List) && actRow4List.equalsIgnoreCase(expRow4List)
				&& actRow5List.equalsIgnoreCase(expRow5List) && actRow6List.equalsIgnoreCase(expRow6List)
				&& actRow7List.equalsIgnoreCase(expRow7List) && actRow8List.equalsIgnoreCase(expRow8List)
				&& actRow9List.equalsIgnoreCase(expRow9List) &&

				actreportHeaderList.equalsIgnoreCase(expreportHeaderList) &&

				actvalidationConfirmationMessage.equalsIgnoreCase(expvalidationConfirmationMessage)) {
			System.out.println(" Test Pass: Values Dsiplayed as Expected ");
			return true;
		} else {
			System.out.println(" Test FAIL: Values Dsiplayed as Expected ");
			return false;
		}

	}

	@FindBy(xpath = "//input[@id='FOption_22_1000_DefaultFilter_0']")
	private static WebElement extraFieldDefaultFiltertxt;;;

	@FindBy(xpath = "//*[@id='id_rd_customization_report_column_button_container']/input[2]")
	private static WebElement extraFieldDefaultFilterOkBtn;

	@FindBy(xpath = "(//*[@id='idFilterCustomizeIcon'])[2]")
	private static WebElement extraFieldCustIcon;;

	@FindBy(xpath = "//*[@id='FilterFields_22_1000']/li[56]/a")
	private static WebElement custPopWareHouseExpandBtn;

	@FindBy(xpath = "//*[@id='5058']")
	private static WebElement custPopWareHouseNameChkbox;;

	@FindBy(xpath = "//*[@id='FilterFieldCust_22_1000']/div/div[3]/button[1]")
	private static WebElement custPopOkBtn;;

	@FindBy(xpath = "//*[@id='id_rd_customization_report_column_property']/div/div[5]/div/span")
	private static WebElement rdReportExtraFieldFilterExpandBtn;

	@FindBy(xpath = "//*[@id='id_rd_customization_parameterslist_button']/input[1]")
	private static WebElement rdReportParameterDeleteBtn;

	public boolean checkAlignmentInReportDesgining()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		checkNavigateToReportDesginer();

		Thread.sleep(2999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportNameDropdown));
		reportNameDropdown.sendKeys("MR With Filter");
		Thread.sleep(2000);
		reportNameDropdown.sendKeys(Keys.TAB);

		Thread.sleep(2999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTab));
		customizationTab.click();

		Thread.sleep(1999);

		int customizeTabTableHeaderLsistCount = customizeTabTableHeaderLsist.size();
		System.err.println(customizeTabTableHeaderLsistCount);
		for (int i = 1; i <= customizeTabTableHeaderLsistCount; i++) {
			String data = customizeTabTableHeaderLsist.get(i).getText();

			if (data.equalsIgnoreCase("QUANTITY")) {
				customizeTabTableHeaderLsist.get(i).click();

				System.err.println(i);
				Thread.sleep(1000);
				break;
			}
		}

		Thread.sleep(1999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdExtraFiledColHeadingTxt));
		rdExtraFiledColHeadingTxt.click();

		Thread.sleep(1999);
		getAction().moveToElement(rdExtraFiledColHorizontalDrpdwn).build().perform();

		Select s1 = new Select(rdExtraFiledColHorizontalDrpdwn);
		s1.selectByVisibleText("CENTER");

		Thread.sleep(1999);
		getAction().moveToElement(rdExtraFiledColSignDrpdwn).build().perform();
		Select s2 = new Select(rdExtraFiledColSignDrpdwn);
		s2.selectByVisibleText("(BRACKET)");

		Thread.sleep(1999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdExtraFiledOkBtn));
		rdExtraFiledOkBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(finishBtn));
		finishBtn.click();

		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : " + actMessage + " Value Expected : " + expMessage);

		if (actMessage.equalsIgnoreCase(expMessage)) {

			System.out.println(" Test PasS: Extra Filed Programmable Filed added");
			return true;
		} else {
			System.out.println(" Test FAIL: Extra Filed Programmable Filed added");
			return false;
		}

	}

	public boolean checkReportMRAfterChangesAlignmentAndSignInColoumnFiels()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(userNameDisplay));
		userNameDisplay.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(userNameDisplay));
		logoutOption.click();

		checkLogin();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
		searchTxt.click();
		searchTxt.sendKeys("MR With Filter");
		Thread.sleep(1000);
		searchTxt.sendKeys(Keys.ENTER);

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_DateOptionDropdown));

		Select s = new Select(sl_DateOptionDropdown);
		s.selectByValue("1");

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_OkBtn));
		sl_OkBtn.click();

		if (sl_1stRow1stCol.isDisplayed() == false) {
			checkServerErrorMessage();
		}

		Thread.sleep(2000);
		boolean novalidationConfirmationMessage = validationConfirmationMessage.getText().isEmpty();

		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";

		int reportHeaderListCount = reportHeaderList.size();

		ArrayList<String> reportHeaderListArray = new ArrayList<>();

		for (int i = 0; i < reportHeaderListCount; i++) {
			String data = reportHeaderList.get(i).getText();
			reportHeaderListArray.add(data);
		}

		String actreportHeaderList = reportHeaderListArray.toString();
		String expreportHeaderList = "[#, Department.Name, Warehouse.Name, Gross, Item.Name, Item.Code, Net amount, QUANTITY, ToGrossAddedField]";

		System.err.println(" Act reportHeaderList : " + actreportHeaderList);
		System.err.println(" Exp reportHeaderList : " + expreportHeaderList);

		Thread.sleep(1500);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_1stRow1stCol));

		int reportsRow1ListCount = reportCol1List.size();
		ArrayList<String> reportsRow1ListArray = new ArrayList<String>();
		for (int i = 1; i < reportsRow1ListCount; i++) {
			String data = reportCol1List.get(i).getText();

			reportsRow1ListArray.add(data);
		}
		String actRow1List = reportsRow1ListArray.toString();
		String expRow1List = "[2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19]";

		int report2ndRowListCount = reportCol2List.size();
		ArrayList<String> report2ndRowListArray = new ArrayList<String>();
		for (int i = 1; i < report2ndRowListCount; i++) {
			String data = reportCol2List.get(i).getText();
			report2ndRowListArray.add(data);
		}
		String actRow2List = report2ndRowListArray.toString();
		String expRow2List = "[AMERICA, DUBAI, DUBAI, INDIA, INDIA, INDIA, INDIA, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI]";

		int report3rdRowListCount = reportCol3List.size();
		ArrayList<String> report3rdRowListArray = new ArrayList<String>();
		for (int i = 1; i < report3rdRowListCount; i++) {
			String data = reportCol3List.get(i).getText();
			report3rdRowListArray.add(data);
		}
		String actRow3List = report3rdRowListArray.toString();
		String expRow3List = "[HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, SECUNDERABAD, SECUNDERABAD, SECUNDERABAD]";

		int report4thRowListCount = reportCol4List.size();
		ArrayList<String> report4thRowListArray = new ArrayList<String>();
		for (int i = 1; i < report4thRowListCount; i++) {
			String data = reportCol4List.get(i).getText();
			report4thRowListArray.add(data);
		}
		String actRow4List = report4thRowListArray.toString();
		String expRow4List = "[11.00, 110.00, 11.00, 110.00, 11.00, 110.00, 11.00, 110.00, 11.00, 110.00, 11.00, 110.00, 11.00, 110.00, 11.00, , , ]";

		int report5thRowListCount = reportCol5List.size();
		ArrayList<String> report5thRowListArray = new ArrayList<String>();
		for (int i = 1; i < report5thRowListCount; i++) {
			String data = reportCol5List.get(i).getText();
			report5thRowListArray.add(data);
		}
		String actRow5List = report5thRowListArray.toString();
		String expRow5List = "[BR COGS ITEM, BATCH BIN WITH NO RESERVATION ITEM, BR COGS ITEM, BATCH BIN WITH NO RESERVATION ITEM, BR COGS ITEM, BATCH BIN WITH NO RESERVATION ITEM, BR COGS ITEM, BATCH BIN WITH NO RESERVATION ITEM, BR COGS ITEM, BATCH BIN WITH NO RESERVATION ITEM, BR COGS ITEM, BATCH BIN WITH NO RESERVATION ITEM, BR COGS ITEM, BATCH BIN WITH NO RESERVATION ITEM, BR COGS ITEM, BATCH BIN WITH NO RESERVATION ITEM, BR COGS ITEM, BATCH BIN WITH NO RESERVATION ITEM]";

		int report6thRowListCount = reportCol6List.size();
		ArrayList<String> report6thRowListArray = new ArrayList<String>();
		for (int i = 1; i < report6thRowListCount; i++) {
			String data = reportCol6List.get(i).getText();
			report6thRowListArray.add(data);
		}
		String actRow6List = report6thRowListArray.toString();
		String expRow6List = "[BR COGS ITEM, BBWNRI, BR COGS ITEM, BBWNRI, BR COGS ITEM, BBWNRI, BR COGS ITEM, BBWNRI, BR COGS ITEM, BBWNRI, BR COGS ITEM, BBWNRI, BR COGS ITEM, BBWNRI, BR COGS ITEM, BBWNRI, BR COGS ITEM, BBWNRI]";

		int report7thRowListCount = reportCol7List.size();
		ArrayList<String> report7thRowListArray = new ArrayList<String>();
		for (int i = 1; i < report7thRowListCount; i++) {
			String data = reportCol7List.get(i).getText();
			report7thRowListArray.add(data);
		}
		String actRow7List = report7thRowListArray.toString();
		String expRow7List = "[, 121.00, , 242.00, , , , 484.00, , , , , , , , 484.00, , ]";

		int report8thRowListCount = reportCol8List.size();
		ArrayList<String> report8thRowListArray = new ArrayList<String>();
		for (int i = 1; i < report8thRowListCount; i++) {
			String data = reportCol8List.get(i).getText();
			report8thRowListArray.add(data);
		}
		String actRow8List = report8thRowListArray.toString();
		String expRow8List = "[(1.00), (10.00), (1.00), (10.00), (1.00), (10.00), (1.00), (10.00), (1.00), (10.00), (1.00), (10.00), (1.00), (10.00), (1.00), (10.00), (1.00), (10.00)]";

		int report9thRowListCount = reportCol9List.size();
		ArrayList<String> report9thRowListArray = new ArrayList<String>();
		for (int i = 1; i < report9thRowListCount; i++) {
			String data = reportCol9List.get(i).getText();
			report9thRowListArray.add(data);
		}
		String actRow9List = report9thRowListArray.toString();
		String expRow9List = "[2.00, 11.00, 2.00, 11.00, 2.00, 11.00, 2.00, 11.00, 2.00, 11.00, 2.00, 11.00, 2.00, 11.00, 2.00, 11.00, 2.00, 11.00]";

		System.out.println("actRow1List  : " + actRow1List);
		System.out.println("expRow1List  : " + expRow1List);
		System.out.println("*********************************************************************");

		System.out.println("actRow2List  : " + actRow2List);
		System.out.println("expRow2List  : " + expRow2List);
		System.out.println("*********************************************************************");

		System.out.println("actRow3List  : " + actRow3List);
		System.out.println("expRow3List  : " + expRow3List);
		System.out.println("*********************************************************************");

		System.out.println("actRow4List  : " + actRow4List);
		System.out.println("expRow4List  : " + expRow4List);
		System.out.println("*********************************************************************");

		System.out.println("actRow5List  : " + actRow5List);
		System.out.println("expRow5List  : " + expRow5List);
		System.out.println("*********************************************************************");

		System.out.println("actRow6List  : " + actRow6List);
		System.out.println("expRow6List  : " + expRow6List);
		System.out.println("*********************************************************************");

		System.out.println("actRow7List  : " + actRow7List);
		System.out.println("expRow7List  : " + expRow7List);
		System.out.println("*********************************************************************");

		System.out.println("actRow8List  : " + actRow8List);
		System.out.println("expRow8List  : " + expRow8List);
		System.out.println("*********************************************************************");

		System.out.println("actRow9List  : " + actRow9List);
		System.out.println("expRow9List  : " + expRow9List);
		System.out.println("*********************************************************************");

		if (actRow1List.equalsIgnoreCase(expRow1List) && actRow2List.equalsIgnoreCase(expRow2List) &&

				actRow3List.equalsIgnoreCase(expRow3List) && actRow4List.equalsIgnoreCase(expRow4List)
				&& actRow5List.equalsIgnoreCase(expRow5List) && actRow6List.equalsIgnoreCase(expRow6List)
				&& actRow7List.equalsIgnoreCase(expRow7List) && actRow8List.equalsIgnoreCase(expRow8List)
				&& actRow9List.equalsIgnoreCase(expRow9List) &&

				actreportHeaderList.equalsIgnoreCase(expreportHeaderList) &&

				actvalidationConfirmationMessage.equalsIgnoreCase(expvalidationConfirmationMessage)) {
			System.out.println(" Test Pass: Values Dsiplayed as Expected ");
			return true;
		} else {
			System.out.println(" Test FAIL: Values Dsiplayed as Expected ");
			return false;
		}

	}

	public boolean checkSortingOptionInReportDesgining()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		Thread.sleep(2000);
		getDriver().navigate().refresh();

		Thread.sleep(2000);

		checkNavigateToReportDesginer();

		Thread.sleep(2999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportNameDropdown));
		reportNameDropdown.sendKeys("MR With Filter");
		Thread.sleep(2000);
		reportNameDropdown.sendKeys(Keys.TAB);

		Thread.sleep(2999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTab));
		customizationTab.click();

		Thread.sleep(1999);

		Thread.sleep(2999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTABSortingTAB));
		customizationTABSortingTAB.click();

		Thread.sleep(2999);
		int count = sortingTABSourceList.size();

		ArrayList<String> sortingTABSourceListarray = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			String data = sortingTABSourceList.get(i).getText();
			sortingTABSourceListarray.add(data);

			if (data.equalsIgnoreCase("Department.Name")) {
				sortingTABSourceList.get(i).click();
			}
		}

		String actList = sortingTABSourceListarray.toString();
		String expList = "[Department.Name, Warehouse.Name, Gross, Item.Name, Item.Code, Net amount, QUANTITY, ToGrossAddedField]";

		System.err.println(" Sorting Tab Actual  List  : " + actList);
		System.err.println(" Sorting Tab Exp  List     : " + expList);

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sortingTABRightRowBtn));
		sortingTABRightRowBtn.click();

		Thread.sleep(1999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sortingtabSelectDrpdwn));
		Select s1 = new Select(sortingtabSelectDrpdwn);
		s1.selectByValue("2");

		Thread.sleep(1999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sortingtabOkBtn));
		sortingtabOkBtn.click();

		Thread.sleep(1999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(finishBtn));
		finishBtn.click();

		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : " + actMessage + " Value Expected : " + expMessage);

		if (actMessage.equalsIgnoreCase(expMessage) && actList.equalsIgnoreCase(expList)) {

			System.out.println(" Test PasS: Extra Filed Programmable Filed added");
			return true;
		} else {
			System.out.println(" Test FAIL: Extra Filed Programmable Filed added");
			checkServerErrorMessage();
			return false;
		}

	}

	public boolean checkReportMRAfterChangesAInSortingTAB()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(userNameDisplay));
		userNameDisplay.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(userNameDisplay));
		logoutOption.click();

		checkLogin();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
		searchTxt.click();
		searchTxt.sendKeys("MR With Filter");
		Thread.sleep(1000);
		searchTxt.sendKeys(Keys.ENTER);

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_DateOptionDropdown));

		Select s = new Select(sl_DateOptionDropdown);
		s.selectByValue("1");

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_OkBtn));
		sl_OkBtn.click();

		if (sl_1stRow1stCol.isDisplayed() == false) {
			checkServerErrorMessage();
		}

		Thread.sleep(2000);
		boolean novalidationConfirmationMessage = validationConfirmationMessage.getText().isEmpty();

		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";

		int reportHeaderListCount = reportHeaderList.size();

		ArrayList<String> reportHeaderListArray = new ArrayList<>();

		for (int i = 0; i < reportHeaderListCount; i++) {
			String data = reportHeaderList.get(i).getText();
			reportHeaderListArray.add(data);
		}

		String actreportHeaderList = reportHeaderListArray.toString();
		String expreportHeaderList = "[#, Department.Name, Warehouse.Name, Gross, Item.Name, Item.Code, Net amount, QUANTITY, ToGrossAddedField]";

		System.err.println(" Act reportHeaderList : " + actreportHeaderList);
		System.err.println(" Exp reportHeaderList : " + expreportHeaderList);

		Thread.sleep(1500);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_1stRow1stCol));

		int reportsRow1ListCount = reportCol1List.size();
		ArrayList<String> reportsRow1ListArray = new ArrayList<String>();
		for (int i = 1; i < reportsRow1ListCount; i++) {
			String data = reportCol1List.get(i).getText();

			reportsRow1ListArray.add(data);
		}
		String actRow1List = reportsRow1ListArray.toString();
		String expRow1List = "[2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19]";

		int report2ndRowListCount = reportCol2List.size();
		ArrayList<String> report2ndRowListArray = new ArrayList<String>();
		for (int i = 1; i < report2ndRowListCount; i++) {
			String data = reportCol2List.get(i).getText();
			report2ndRowListArray.add(data);
		}
		String actRow2List = report2ndRowListArray.toString();
		String expRow2List = "[INDIA, INDIA, INDIA, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI]";

		int report3rdRowListCount = reportCol3List.size();
		ArrayList<String> report3rdRowListArray = new ArrayList<String>();
		for (int i = 1; i < report3rdRowListCount; i++) {
			String data = reportCol3List.get(i).getText();
			report3rdRowListArray.add(data);
		}
		String actRow3List = report3rdRowListArray.toString();
		String expRow3List = "[HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, SECUNDERABAD, SECUNDERABAD, SECUNDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, SECUNDERABAD, SECUNDERABAD]";

		int report4thRowListCount = reportCol4List.size();
		ArrayList<String> report4thRowListArray = new ArrayList<String>();
		for (int i = 1; i < report4thRowListCount; i++) {
			String data = reportCol4List.get(i).getText();
			report4thRowListArray.add(data);
		}
		String actRow4List = report4thRowListArray.toString();
		String expRow4List = "[110.00, 11.00, 11.00, 110.00, 110.00, 110.00, 110.00, 110.00, , , , 11.00, 11.00, 11.00, 11.00, 11.00, , ]";

		int report5thRowListCount = reportCol5List.size();
		ArrayList<String> report5thRowListArray = new ArrayList<String>();
		for (int i = 1; i < report5thRowListCount; i++) {
			String data = reportCol5List.get(i).getText();
			report5thRowListArray.add(data);
		}
		String actRow5List = report5thRowListArray.toString();
		String expRow5List = "[BATCH BIN WITH NO RESERVATION ITEM, BR COGS ITEM, BR COGS ITEM, BATCH BIN WITH NO RESERVATION ITEM, BATCH BIN WITH NO RESERVATION ITEM, BATCH BIN WITH NO RESERVATION ITEM, BATCH BIN WITH NO RESERVATION ITEM, BATCH BIN WITH NO RESERVATION ITEM, BATCH BIN WITH NO RESERVATION ITEM, BATCH BIN WITH NO RESERVATION ITEM, BATCH BIN WITH NO RESERVATION ITEM, BR COGS ITEM, BR COGS ITEM, BR COGS ITEM, BR COGS ITEM, BR COGS ITEM, BR COGS ITEM, BR COGS ITEM]";

		int report6thRowListCount = reportCol6List.size();
		ArrayList<String> report6thRowListArray = new ArrayList<String>();
		for (int i = 1; i < report6thRowListCount; i++) {
			String data = reportCol6List.get(i).getText();
			report6thRowListArray.add(data);
		}
		String actRow6List = report6thRowListArray.toString();
		String expRow6List = "[BBWNRI, BR COGS ITEM, BR COGS ITEM, BBWNRI, BBWNRI, BBWNRI, BBWNRI, BBWNRI, BBWNRI, BBWNRI, BBWNRI, BR COGS ITEM, BR COGS ITEM, BR COGS ITEM, BR COGS ITEM, BR COGS ITEM, BR COGS ITEM, BR COGS ITEM]";

		int report7thRowListCount = reportCol7List.size();
		ArrayList<String> report7thRowListArray = new ArrayList<String>();
		for (int i = 1; i < report7thRowListCount; i++) {
			String data = reportCol7List.get(i).getText();
			report7thRowListArray.add(data);
		}
		String actRow7List = report7thRowListArray.toString();
		String expRow7List = "[, , , 121.00, 484.00, , , , 484.00, , , , , , , , , ]";

		int report8thRowListCount = reportCol8List.size();
		ArrayList<String> report8thRowListArray = new ArrayList<String>();
		for (int i = 1; i < report8thRowListCount; i++) {
			String data = reportCol8List.get(i).getText();
			report8thRowListArray.add(data);
		}
		String actRow8List = report8thRowListArray.toString();
		String expRow8List = "[(10.00), (1.00), (1.00), (10.00), (10.00), (10.00), (10.00), (10.00), (10.00), (10.00), (10.00), (1.00), (1.00), (1.00), (1.00), (1.00), (1.00), (1.00)]";

		int report9thRowListCount = reportCol9List.size();
		ArrayList<String> report9thRowListArray = new ArrayList<String>();
		for (int i = 1; i < report9thRowListCount; i++) {
			String data = reportCol9List.get(i).getText();
			report9thRowListArray.add(data);
		}
		String actRow9List = report9thRowListArray.toString();
		String expRow9List = "[11.00, 2.00, 2.00, 11.00, 11.00, 11.00, 11.00, 11.00, 11.00, 11.00, 11.00, 2.00, 2.00, 2.00, 2.00, 2.00, 2.00, 2.00]";

		System.out.println("actRow1List  : " + actRow1List);
		System.out.println("expRow1List  : " + expRow1List);
		System.out.println("*********************************************************************");

		System.out.println("actRow2List  : " + actRow2List);
		System.out.println("expRow2List  : " + expRow2List);
		System.out.println("*********************************************************************");

		System.out.println("actRow3List  : " + actRow3List);
		System.out.println("expRow3List  : " + expRow3List);
		System.out.println("*********************************************************************");

		System.out.println("actRow4List  : " + actRow4List);
		System.out.println("expRow4List  : " + expRow4List);
		System.out.println("*********************************************************************");

		System.out.println("actRow5List  : " + actRow5List);
		System.out.println("expRow5List  : " + expRow5List);
		System.out.println("*********************************************************************");

		System.out.println("actRow6List  : " + actRow6List);
		System.out.println("expRow6List  : " + expRow6List);
		System.out.println("*********************************************************************");

		System.out.println("actRow7List  : " + actRow7List);
		System.out.println("expRow7List  : " + expRow7List);
		System.out.println("*********************************************************************");

		System.out.println("actRow8List  : " + actRow8List);
		System.out.println("expRow8List  : " + expRow8List);
		System.out.println("*********************************************************************");

		System.out.println("actRow9List  : " + actRow9List);
		System.out.println("expRow9List  : " + expRow9List);
		System.out.println("*********************************************************************");

		if (actRow1List.equalsIgnoreCase(expRow1List) && actRow2List.equalsIgnoreCase(expRow2List) &&

				actRow3List.equalsIgnoreCase(expRow3List) && actRow4List.equalsIgnoreCase(expRow4List)
				&& actRow5List.equalsIgnoreCase(expRow5List) && actRow6List.equalsIgnoreCase(expRow6List)
				&& actRow7List.equalsIgnoreCase(expRow7List) && actRow8List.equalsIgnoreCase(expRow8List)
				&& actRow9List.equalsIgnoreCase(expRow9List) &&

				actreportHeaderList.equalsIgnoreCase(expreportHeaderList) &&

				actvalidationConfirmationMessage.equalsIgnoreCase(expvalidationConfirmationMessage)) {
			System.out.println(" Test Pass: Values Dsiplayed as Expected ");
			Thread.sleep(2000);

			getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(userNameDisplay));
			userNameDisplay.click();

			getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(logoutOption));
			logoutOption.click();
			return true;
		} else {
			System.out.println(" Test FAIL: Values Dsiplayed as Expected ");
			Thread.sleep(2000);

			getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(userNameDisplay));
			userNameDisplay.click();

			getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(logoutOption));
			logoutOption.click();
			return false;
		}
	}

	public boolean checkSavingReportDesginerOnBasisOFCustomization()
			throws InterruptedException, IOException, EncryptedDocumentException, InvalidFormatException {

		getDriver().navigate().refresh();

		Thread.sleep(1999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(homeMenu));
		homeMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(utilities));
		utilities.click();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportDesignerMenu));
		reportDesignerMenu.click();

		Thread.sleep(2999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportNameDropdown));
		reportNameDropdown.sendKeys("Test Column Filter");
		reportNameDropdown.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportTypeDropdown));
		Select rtd = new Select(reportTypeDropdown);
		rtd.selectByVisibleText("Cubes");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(inventoryExpandBtn));
		inventoryExpandBtn.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportsBtn));
		reportsBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dataSetTab));
		dataSetTab.click();

		Thread.sleep(2000);

		int transactionSetListCount = transactionSetList.size();

		for (int i = 0; i <= transactionSetListCount; i++) {
			String data = transactionSetList.get(i).getText();

			if (data.equalsIgnoreCase("Inventory transactions of selected product")) {
				transactionSetList.get(i).click();

				break;
			}
		}

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTab));
		customizationTab.click();

		Thread.sleep(3999);

		Runtime.getRuntime().exec(getBaseDir() + "\\autoIt\\scripts\\TestColumnFilter.exe");

		Thread.sleep(1000000);

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(finishBtn));
		finishBtn.click();

		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : " + actMessage + " Value Expected : " + expMessage);

		if (actMessage.equalsIgnoreCase(expMessage)) {
			return true;
		} else {
			return false;
		}

	}

	public ReportDesignerPage1(WebDriver driver) {
		PageFactory.initElements(driver, this);

	}

}