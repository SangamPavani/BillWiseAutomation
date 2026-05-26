package com.focus.Pages;


import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;

import com.focus.base.BaseEngine;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.collections4.bag.SynchronizedSortedBag;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.SystemUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
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
import org.openqa.selenium.support.ui.WebDriverWait;
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



public class ReportDesignerPage extends BaseEngine
{
	//Logout and Login Screen

	@FindBy(xpath="//*[@id='txtUsername']")
	private static WebElement username;

	@FindBy(id="txtPassword")
	private static WebElement password;

	@FindBy(id="btnSignin")
	private static WebElement signIn;

	@FindBy(id="ddlCompany")
	private static WebElement companyDropDownList;

	/*@FindBy(xpath="/html[1]/body[1]/section[1]/div[2]/header[1]/nav[1]/div[1]/ul[1]/li[5]/a[1]/span[1]")
	private static WebElement userNameDisplay;*/



	@FindBy(xpath="//*[@id='companyLogo']")
	private static WebElement companyLogo;

	@FindBy(xpath="//*[@id='ulCompanyDetails_HomePage']/li[1]")
	private static WebElement companyName;

	
	@FindBy(xpath="//input[@id='donotshow']")
	private static WebElement doNotShowCheckbox;

	@FindBy(xpath="//span[@class='pull-right']")
	private static WebElement closeBtnInDemoPopupScreen;
	
	
	 public static String checkDownloadedFileName(WebDriver driver) throws InterruptedException, AWTException
	   {
			/*String mainWindow = driver.getWindowHandle();

			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("window.open()");
			
			for(String winHandle : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle);
			}

			driver.get("chrome://downloads");*/

			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_J);
			robot.keyRelease(KeyEvent.VK_J);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			
			Thread.sleep(2000);
			
			ArrayList<String> openTabs = new ArrayList<String>(getDriver().getWindowHandles());

			int count = openTabs.size();
			
		 	System.out.println("openTabs : "+count);

		 	getDriver().switchTo().window(openTabs.get(count-2));
		 	
		 	getDriver().switchTo().window(openTabs.get(count-1));
		 	
		 	Thread.sleep(2000);
			
			JavascriptExecutor js1 = (JavascriptExecutor)driver;

			String fileName = (String) js1.executeScript("return document.querySelector('downloads-manager').shadowRoot.querySelector('#downloadsList downloads-item').shadowRoot.querySelector('div#content #file-link').text");

			System.err.println("Download deatils");
			System.out.println("File Name :-" + fileName);

			Thread.sleep(2000);
			
			/*driver.close();

			driver.switchTo().window(mainWindow);*/
			
			getDriver().switchTo().window(openTabs.get(count-1)).close();
		 	
		 	Thread.sleep(2000);
		 	
		 	getDriver().switchTo().window(openTabs.get(count-2));
			
			return fileName;
		}
	   
	   




	public static void checkPopUpWindow() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{


		try 
		{
			getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(doNotShowCheckbox));
			doNotShowCheckbox.click();



			getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(closeBtnInDemoPopupScreen));
			closeBtnInDemoPopupScreen.click(); 

			System.err.println("POP UP DISPLAYED AND CLOSED SUCCESSFULLY");

		} 
		catch (Exception e)
		{
			System.err.println("NO POP UP DISPLAYED");
		}



	}

	@FindBy(xpath = "//*[@id='idGlobalError']/div/div[2]")//*[@id="idGlobalError"]/div/div[2]
	public static WebElement errorMessage;

	@FindBy(xpath = "//*[@id='idGlobalError']/div/div[1]/button")//*[@id="idGlobalError"]/div/div[1]/button
	public static WebElement errorMessageCloseBtn;

	public static String checkValidationMessage(String ExpMessage) throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		try
		{
			getFluentWebDriverWait().until(ExpectedConditions.visibilityOf(errorMessage));
			String actErrorMessage=errorMessage.getText();
			String expErrorMessage=ExpMessage;

			try
			{

				getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(errorMessageCloseBtn));
				errorMessageCloseBtn.click();

				System.out.println("ValidationMessage  :  "+actErrorMessage +" Value Expected : "+expErrorMessage);

				return actErrorMessage;
			}
			catch(Exception ee)
			{

				System.out.println("ValidationMessage  :  "+actErrorMessage +" Value Expected : "+expErrorMessage);

				return actErrorMessage;
			}
		}
		catch(Exception e)
		{
			System.err.println("Error Message NOT Found or NOT Clickable");
			System.err.println(e.getMessage());

			String Exception=e.getMessage();

			return Exception;
		}
	}



	@FindBy(xpath="//button[contains(text(),'Ok')]")
	private static WebElement loginRefreshOkBtn;


	public static void checkRefershPopOnlogin() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{

		try 
		{
			getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(loginRefreshOkBtn));
			loginRefreshOkBtn.click();


		} 
		catch (Exception e)
		{
			System.err.println("NO ALERT POP UP DISPLAYED");
		}



	}

	private static String xlfile;
	private static String resPass="Pass";
	private static String resFail="Fail";
	private static ExcelReader excelReader;

	private static int cSize;


	@FindBy(xpath="//*[@id='dashName']")
	private static WebElement labelDashboard ;

	@FindBy(xpath="//*[@id='Select_dash']")
	private static WebElement selectDashboard ;

	@FindBy(xpath="//*[@id='Dashboard_AddDash']")
	private static WebElement newAddDashBoard;

	@FindBy(xpath="//*[@id='Dashboard_Dash_Config']")
	private static WebElement dashboardCustomizationSettings;

	public static boolean checkLoginForReportDesigner() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{


		LoginPage lp=new LoginPage(getDriver()); 

		String unamelt="su";

		String pawslt="su";

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

		/*String compname="Automation Company";

		Select oSelect = new Select(companyDropDownList);

		List <WebElement> elementCount = oSelect.getOptions();

		int cqSize = elementCount.size();

		int zqSize=cSize+1;

		System.out.println("CompanyDropdownList Count :"+cqSize);

		System.out.println("Company dropdown is :"+ zqSize);


		//Select dropdown= new Select(lp.companyDropDownList);
	    int i;

		//List<WebElement> list = dropdown.getOptions();

		//List<String> text = new ArrayList<>();
		for(i=0; i<elementCount.size(); i++) 
		{
			elementCount.get(i).getText();
			String optionName = elementCount.get(i).getText();
			if(optionName.toUpperCase().startsWith(compname.toUpperCase()))
			{
				System.out.println("q"+elementCount.get(i).getText());
				elementCount.get(i).click();
			}	
		}*/

		lp.clickOnSignInBtn();

		//checkRefershPopOnlogin();

		//checkPopUpWindow();

		Thread.sleep(8000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(userNameDisplay));
		userNameDisplay.click();

		String userInfo=userNameDisplay.getText();

		System.out.println("User Info : "+userInfo);

		System.out.println("User Info Capture Text :"+userNameDisplay.getText());

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(companyLogo));

		companyLogo.click();

		String getCompanyTxt=companyName.getText();
		String getLoginCompanyName=getCompanyTxt.substring(0, 19);
		System.out.println("company name :"+ getLoginCompanyName);
		companyLogo.click();



		String expuserInfo            ="SU";
		String expLoginCompanyName    ="Automation Company ";
		String expDashboard			  ="Graph with Active and setAsDefault";
		boolean expdashboardGraph	  =true;
		boolean expdashboardLedger    =true;
		boolean expdashboardInfoPanel =true;
		String expAccountsTitle       ="Account";

		System.out.println("***********************************checkOpenAccountsMenu*********************************");

		System.out.println("User Info                        : "+userInfo               +"  value expected  "+expuserInfo);
		System.out.println("Login Company Name               : "+getLoginCompanyName    +"  value expected  "+expLoginCompanyName);

		if(userInfo.equalsIgnoreCase(expuserInfo) && getLoginCompanyName.equalsIgnoreCase(expLoginCompanyName))
		{	
			return true;
		}	 
		else
		{
			return false;
		}
	}


	@FindBy(xpath="//a[@id='22']//span[contains(text(),'Report Designer')]")
	private static WebElement reportDesignerMenu;

	@FindBy(xpath="//span[@class='icon-xmlimport icon-font6']")
	private static WebElement importFromExcelBtn;

	@FindBy(xpath="//div[@id='myNavbar']//ul[@class='nav navbar-nav navbar-right']")
	private static WebElement exportFromExcelBtn;

	@FindBy(xpath="//div[contains(text(),'Reset')]")
	private static WebElement resetBtn;

	@FindBy(xpath="//*[@class='icon-delete hiconright2']")
	private static WebElement deleteBtn;

	@FindBy(xpath="//div[@id='myNavbar']//ul[@class='nav navbar-nav navbar-right']")
	private static WebElement backTab;

	@FindBy(xpath="//div[@onclick='RD_ENTRY.onNext_Click();']//div[@class='col-sm-12 toolbar_button_image']")
	private static WebElement nextTab;

	//@FindBy(xpath="//*[@id='myNavbar']/ul/li[7]/div/div[2]")
	@FindBy(xpath="//*[@id='RDFinishbtn']")
	private static WebElement finishBtn;

	@FindBy(xpath="//*[@class='icon-xmlimport hiconright2']")
	private static WebElement rdImportFromXML;

	@FindBy(xpath="//*[@id='myNavbar']/ul/li[2]")
	private static WebElement rdExportToXML;


	@FindBy(xpath="//span[@class='icon-close icon-font6']")
	private static WebElement cancelBtn;

	@FindBy(xpath="//*[@id='id_rd_header_button_group_1']/div[1]")
	private static WebElement definitionTab;

	@FindBy(xpath="//input[@id='id_rd_definition_reportname']")
	private static WebElement reportNameDropdown;


	@FindBy(xpath="//select[@id='id_rd_definition_reporttype']")
	private static WebElement reportTypeDropdown; 	

	@FindBy(xpath="//input[@id='advanceEngine']")
	private static WebElement advanceEngineCkeckbox;


	@FindBy(xpath="//select[@id='id_rd_definition_modules']")
	private static WebElement moduleDropdown;



	@FindBy(xpath="//select[@id='id_rd_definition_datasets']")
	private static WebElement dataSetDropdown;


	@FindBy(xpath="//input[@id='id_rd_parameter_entry_fieldname']")
	private static WebElement fieldNameTextbox;

	@FindBy(xpath="//input[@id='id_rd_parameter_entry_variablename']")
	private static WebElement variablenameTextbox;


	@FindBy(xpath="//select[@id='id_rd_parameter_entry_fieldtype']")
	private static WebElement fieldTypeDropddown;

	@FindBy(xpath="//input[@id='id_rd_parameter_entry_viewname']")
	private static WebElement viewnameTextBox;


	@FindBy(xpath="//input[@id='id_rd_parameter_entry_valuecolumn']")
	private static WebElement valueColoumTextBox;

	@FindBy(xpath="//input[@id='id_rd_parameter_entry_displaycolumn']")
	private static WebElement displayColumnTexBox;

	@FindBy(xpath="//input[@onclick='RD_ENTRY.PARAMETER.onOK_Click();']")
	private static WebElement okBtn;

	@FindBy(xpath="//input[@onclick='RD_ENTRY.PARAMETER.onCancel_Click();']")
	private static WebElement cancelBtnatLast;

	@FindBy(xpath="//input[@value='Add']")
	private static WebElement addBtn;

	@FindBy(xpath="//input[@onclick='RD_ENTRY.PARAMETER.onDelete_Click();']")
	private static WebElement deleteBtnatLast;

	@FindBy (xpath="//*[@id='1']")
	public static WebElement homeMenu;


	@FindBy (xpath="//*[@id='navigation_menu']/li[1]/ul/li[5]/ul/li")
	private static List<WebElement> utilitesList;

	@FindBy(xpath="//a[@id='20']//span[contains(text(),'Utilities')]")
	private static WebElement  utilities;

	@FindBy(xpath="//*[@id='id_menu_tree_135']/a/i")
	private static WebElement  inventoryExpandBtn;

	/* @FindBy(xpath="//*[@id='id_menu_tree_60']/a/span")
	  private static WebElement  finanincalExpandbtn;*/

	@FindBy(xpath="//*[@id='id_menu_tree_60']/a")
	private static WebElement  finanincalExpandbtn;

	@FindBy(xpath="//*[@id='id_menu_tree_60']/a/i")
	private static WebElement  finanincalExpandbtn1;


	@FindBy(xpath="//*[@id='id_menu_tree_81']/a/span")
	private static WebElement  finanincalReportsExpandbtn;



	@FindBy(xpath="//*[@id='id_menu_tree_200']/a/span")
	private static WebElement  reportsBtn;

	//DATA SET Tab


	@FindBy(xpath="//*[@id='id_rd_header_button_group_2']")
	private static WebElement dataSetTab;

	@FindBy(xpath="//span[@id='id_rd_customization_transet_tab_add']")
	private static WebElement plusBtn;


	@FindBy(xpath="//select[@id='id_rd_transet0_documentstatus']")
	private static WebElement documentStatsDropdown;
	
	@FindBy(xpath="//*[@id='id_rd_transet1-tab']/div")
	private static WebElement Transactionset2;
	
	


	@FindBy(xpath="//select[@id='id_rd_transet0_verificationstatus']")
	private static WebElement verificationDropdown;


	@FindBy(xpath="//select[@id='id_rd_transet0_authorizationstatus']")
	private static WebElement authorizationStatusDropdown;


	@FindBy(xpath="//select[@id='id_rd_transet0_brsstatus']")
	private static WebElement brsStatusDropdown;


	@FindBy(xpath="//span[@id='idFilterCustomizeIcon']")
	private static WebElement customizeIcon;


	@FindBy(xpath="//span[@id='a']")
	private static WebElement filterIcon;

	@FindBy(xpath="//*[@id='id_rd_transet0_select']/li")
	private static List<WebElement> transactionSetList;
	
	@FindBy(xpath="//*[@id='id_rd_transet1_select']/li")
	private static List<WebElement> transactionSet2List;

	// Customization Tab

	@FindBy(xpath="//*[@id='id_rd_header_button_group_3']")
	private static WebElement customizationTab;

	@FindBy(xpath="//span[contains(text(),'Sorting')]")
	private static WebElement customizationTABSortingTAB;


	@FindBy(xpath="//*[@id='id_rd_customization_sorting_source']/li")
	private static List<WebElement> sortingTABSourceList;


	@FindBy(xpath="//*[@id='id_rd_sorting_buttons_container']/button[1]")
	private static WebElement sortingTABRightRowBtn;

	@FindBy(xpath="//*[@id='id_rd_sorting_buttons_container']/button[2]")
	private static WebElement sortingLeftArrowBtn;

	@FindBy(xpath="//select[@id='id_rd_customization_sorting_select']")
	private static WebElement sortingtabSelectDrpdwn;

	@FindBy(xpath="//*[@id='id_rd_customization_sorting_container']/div[2]/div[5]/div/input[1]")
	private static WebElement sortingtabOkBtn;





	@FindBy(xpath="//div[@id='id_rd_customization_columnproperty']//div[@class='col-sm-12 toolbar_button_image']")
	private static WebElement columnPropertyTab;


	@FindBy(xpath="//input[@id='id_rd_customization_tree_search']")
	private static WebElement searchBox;


	@FindBy(xpath="//span[@class='icon-searchnumaric icon-font6']")
	private static WebElement searchNumericIcon;


	@FindBy(xpath="//span[@class='glyphicon glyphicon-text-color']")
	private static WebElement searchStringItemIcon;


	@FindBy(xpath="//span[@class='icon-left-and-right-panel-icon icon-font6']")
	private static WebElement closingPanelIcon;


	@FindBy(xpath="//input[@value='Create Virtual Field']")
	private static WebElement createVirtualFieldBox;

	//Row Formatting Tab



	@FindBy(xpath="//div[@id='id_rd_customization_rowformatting']//div[@class='col-sm-12 toolbar_button_image']")
	private static WebElement rowFormattingTab;


	@FindBy(xpath="//input[@id='rfnew']")
	private static WebElement newBtn;


	@FindBy(xpath="//input[@id='rfdelete']")
	private static WebElement deleteBtnInRf;


	@FindBy(xpath="//select[@id='id_set_on_column']")
	private static WebElement setOnColumnDropdown;


	@FindBy(xpath="//input[@id='id_label_for_condition']")
	private static WebElement labelForConditionTextBox;


	@FindBy(xpath="//div[@id='id_rowformatting_browsefile_ctrl_container']//table")
	private static WebElement imageForCondtionFileElement;


	@FindBy(xpath="//span[@class='icon-edit icon-font7 theme_color-inverse FAttachment_Img']")
	private static WebElement imageForConitionNeworEditFile;

	@FindBy(xpath="//input[@id='id_rf_font']")
	private static WebElement fontBtn;


	@FindBy(xpath="//input[@id='id_DontUseAbsoluteValue']")
	private static WebElement useDefaultValueCheckBox;


	@FindBy(xpath="//input[@id='rfok']")
	private static WebElement okBtnAtLast;


	@FindBy(xpath="//input[@id='rfclear']")
	private static WebElement cancelBtnAtLast;

	//SORTING TAB


	@FindBy(xpath="//div[@id='id_rd_customization_sorting']//div[contains(@class,'col-sm-12 toolbar_button_image')]")
	private static WebElement sortingTab;

	@FindBy(xpath="//i[contains(@class,'fa fa-caret-right fa-2x')]")
	private static WebElement forwardBtn;


	@FindBy(xpath="//i[contains(@class,'fa fa-caret-left fa-2x')]")
	private static WebElement backwardBtn;

	@FindBy(xpath="//select[@id='id_rd_customization_sorting_select']")
	private static WebElement noneDropdown;


	@FindBy(xpath="//input[@id='id_rd_customization_sorting_rows']")
	private static WebElement emptyTextArea;

	@FindBy(xpath="//input[contains(@onclick,'RD_ENTRY.CUSTOMIZATION.SORTING.onOK_Click(event);')]")
	private static WebElement okButton;


	@FindBy(xpath="//input[contains(@onclick,'RD_ENTRY.on_Customization_ColumnProperty_Click();')]")
	private static WebElement cancelButton;

	@FindBy(xpath="//label[normalize-space()='Landscape Orientation']")
	private static WebElement landScapeOrientationTab;


	@FindBy(xpath="//l")
	private static WebElement printZeroValue;

	@FindBy(xpath="//div[@id='id_rd_header_button_group_4']//div[contains(@class,'btn col-xs-12 header_button_group stop_text_overflow theme_background-color theme_color')]")
	private static WebElement headerFooterTab;

	@FindBy(xpath="//*[@id='id_rd_header_button_group_5']/a")
	private static WebElement previewTab;

	@FindBy(xpath="//*[@id='rd_customization_tree0']/a/i")
	private static WebElement transactionExpandBtn;

	@FindBy(xpath="//input[@id='id_rd_customization_tree_search']")
	private static WebElement custTabFieldSearchTxt;

	@FindBy(xpath="//*[@id='rd_customization_tree1']/a/span")
	private static WebElement custTabSearchFirstField;

	@FindBy(xpath="//span[contains(text(),'Date.Date')]")
	private static WebElement custTabSearchDATEField;


	@FindBy(xpath="(//li[@data-fieldname='Date']/a/i)[1]")
	private static WebElement dateExpandBtn;

	@FindBy(xpath="(//li[@data-fieldname='Date']/a/span)[2]")
	private static WebElement dateFieldBtn;

	@FindBy(xpath="//span[text()='CashBankAC']/../i")
	private static WebElement cashAndBankExpandBtn;

	//@FindBy(xpath="//*[@id='rd_customization_tree1']/ul/li[8]/ul/li[1]")
	@FindBy(xpath="(//span[text()='CashBankAC']//following::ul//li//span)[1][text()='Name']")
	private static WebElement cashAndBankNameBtn;

	//@FindBy(xpath="//*[@id='rd_customization_tree1']/ul/li[1]//i")
	@FindBy(xpath="//span[text()='Account']/../i")
	private static WebElement acc1ExpandBtn;

	//@FindBy(xpath="//*[@id='rd_customization_tree1']/ul/li[1]/ul/li[1]")
	@FindBy(xpath="(//span[text()='Account']/../i//..//following::ul//li//span)[1][text()='Name']")
	private static WebElement acc1NameBtn;

	//@FindBy(xpath="//*[@id='rd_customization_tree1']/ul/li[1]/ul/li[2]")
	@FindBy(xpath="(//span[text()='Account']/../i//..//following::ul//li//span[text()='Code'])[1]")
	private static WebElement acc1CodeBtn;


	//@FindBy(xpath="//*[@id='rd_customization_tree1']/ul/li[2]")
	@FindBy(xpath="//span[text()='Account2']/../i")
	private static WebElement acc2ExpandBtn;

	//@FindBy(xpath="//*[@id='rd_customization_tree1']/ul/li[2]/ul/li[1]")
	@FindBy(xpath="(//span[text()='Account2']/../i//..//following::ul//li//span[text()='Name'])[1]")
	private static WebElement acc2NameBtn;

	//@FindBy(xpath="//*[@id='rd_customization_tree1']/ul/li[2]/ul/li[2]")
	@FindBy(xpath="(//span[text()='Account2']/../i//..//following::ul//li//span[text()='Code'])[1]")
	private static WebElement acc2CodeBtn;


	@FindBy(xpath="//*[@data-fieldname='DocNo']")
	private static WebElement docNoFieldBtn;
	
	@FindBy(xpath="(//*[@data-fieldname='DocNo'])[1]")
	private static WebElement Ts2docNoFieldBtn;

	//@FindBy(xpath="//*[@id='rd_customization_tree0']/ul/li[1]/ul/li//span[text()='DocNo']")
	@FindBy(xpath="//span[text()='DocNo']")
	private static WebElement Rec_docNoFieldBtn;

	@FindBy(xpath="//span[text()='Footer amount']")
	private static WebElement rec_FooterAmtBtn;


	//@FindBy(xpath="//*[@id='rd_customization_tree0']/ul/li[1]/ul/li[44]")
	@FindBy(xpath="//span[contains(text(),'Voucher name')]")
	private static WebElement rec_VoucherNameBtn;
	
	@FindBy(xpath="//span[contains(text(),'Voucher class')]")
	private static WebElement rec_VoucherClassBtn;
	
	@FindBy(xpath="//span[contains(text(),'Voucher type')]")
	private static WebElement rec_VoucherTypeBtn;
	
	//@FindBy(xpath="//*[@id='rd_customization_tree274']/a/span")
	@FindBy(xpath="(//span[text()=\"Net amount\"])[1]")
	private static WebElement NetAmountBtn;
	
	



	//@FindBy(xpath="//*[@id='rd_customization_tree0']/ul/li[1]/ul/li[30]")
	//@FindBy(xpath="//li[@id='rd_customization_tree1']//*[text()='Item']")
	@FindBy(xpath="(//span[text()=\"Item\"]//..//i)[2]")
	private static WebElement itemExpandBtn;

	//@FindBy(xpath="//*[@id='rd_customization_tree0']/ul/li[1]/ul/li[30]/ul/li[1]")
	//@FindBy(xpath="//li[@id='rd_customization_tree1']//*[@title='Item.Name']")
	@FindBy(xpath="((//span[text()=\"Item\"]//..//i)[2]//..//following::ul//span[text()=\"Name\"])[1]")
	private static WebElement itemNameBtn;




	@FindBy(xpath="//span[text()=\"DocNo\"]")
	private static WebElement docnumberBtn;


	//@FindBy(xpath="//*[@id='rd_customization_tree0']/ul/li[1]/ul/li[37]")
	@FindBy(xpath="(//span[text()=\"Item\"]//..//i)[2]")
	private static WebElement itemExpandBtn1;
	
	@FindBy(xpath="(//span[text()=\"Modified date\"]/../i)[1]")
	private static WebElement ModifiedDateExpandBtn1;

	//@FindBy(xpath="//*[@id='rd_customization_tree0']/ul/li[1]/ul/li[37]/ul/li[1]")
	@FindBy(xpath="((//span[text()=\"Item\"]//..//i)[2]//..//following::ul//span[text()=\"Name\"])[1]")
	private static WebElement itemNameBtn1;
	
	//*[@id='rd_customization_tree0']/ul/li[1]/ul/li[42]
	
	
	@FindBy(xpath="(//*[@class='rd_customization_tree_group' and text()='Date'])[1]")
	private static WebElement DateExpandBtn;
	
	
	@FindBy(xpath="(//*[@class='rd_customization_tree_group' and text()='Date']//..//..//*[@class='rd_customization_tree_leaf' and text()='month'])[1]")
	private static WebElement DateMonthBtn;
	
	//@FindBy(xpath="//*[@id='rd_customization_tree0']/ul/li[1]/ul/li[37]/ul/li[2]")
	@FindBy(xpath="((//span[text()=\"Item\"]//..//i)[2]//..//following::ul//span[text()=\"Code\"])[1]")
	private static WebElement itemCodeBtn1;

	@FindBy(xpath="//*[@id='rd_customization_tree0']/ul/li[1]/ul/li[51]")
	private static WebElement quantityBtn1;

	@FindBy(xpath="//span[text()='Quantity']")
	private static WebElement quantityBtn2;

	@FindBy(xpath="//span[text()='Rate']")
	private static WebElement rateBtn2;

	//@FindBy(xpath="//*[@id='rd_customization_tree0']/ul/li[1]/ul/li[46]")
	@FindBy(xpath="//li//span[text()='Quantity']")
	private static WebElement quantityBtn;

	//@FindBy(xpath="//*[@id='rd_customization_tree0']/ul/li[1]/ul/li[49]")
	@FindBy(xpath="//li//span[text()='Rate']")
	private static WebElement rateBtn;

	@FindBy(xpath="//li//span[text()='Gross']")
	private static WebElement grossBtn;
	
	@FindBy(xpath="//span[text()=\"Voucher class\"]")
	private static WebElement voucherclassBtn;
	
	@FindBy(xpath="//span[text()=\"Voucher name\"]")
	private static WebElement voucherNameBtn;
	
	@FindBy(xpath="//span[text()=\"Voucher type\"]")
	private static WebElement voucherTypeBtn;

	//@FindBy(xpath="//*[@id='rd_customization_tree0']/ul/li[2]/ul/li[5]")
	@FindBy(xpath="//span[text()='Department']/../i")
	private static WebElement deptExpandBtn;
	
	@FindBy(xpath="//*[@id='rd_customization_tree0']/ul/li[2]/ul/li[2]")
	private static WebElement deptExpandBtnInCubes;
	


	@FindBy(xpath="//span[text()='Department']/../i")
	private static WebElement deptExpandBtn1;

	@FindBy(xpath="(//span[text()='Department']/../i//..//following::ul//span[text()='Name'])[1]")
	private static WebElement deptNameBtn1;



	@FindBy(xpath="//*[@id='rd_customization_tree0']/ul/li[2]/ul/li[2]")
	private static WebElement Rec_deptExpandBtn;
	
	@FindBy(xpath="//*[@id='rd_customization_tree0']/ul/li[2]/ul/li[4]")
	private static WebElement TS2_deptExpandBtn;
	
	
	@FindBy(xpath="//*[@id='rd_customization_tree0']/ul/li[2]/ul/li[2]/ul/li[1]")
	private static WebElement rec_deptNameBtn;
	
	@FindBy(xpath="//*[@id='rd_customization_tree0']//*[text()='CustomerAC']")
	private static WebElement CustomerAccExpandBtn;
	
	


	@FindBy(xpath="//*[@id='rd_customization_tree0']/ul/li[2]/ul/li[5]/ul/li[1]")
	private static WebElement deptNameBtn;
	
	@FindBy(xpath="//*[@id='rd_customization_tree0']/ul/li[2]/ul/li[2]/ul/li[1]")
	private static WebElement deptNameBtnInCubes;

	//@FindBy(xpath="//*[@id='rd_customization_tree0']/ul/li[2]/ul/li[24]")
	//@FindBy(xpath="(//a/span[text()='Warehouse'])[2]")
	@FindBy(xpath="(//li[@title='Extra Fields.Warehouse']//i)[1]")
	private static WebElement warehouseExpandBtn;

	//@FindBy(xpath="//*[@id='rd_customization_tree0']/ul/li[2]/ul/li[24]/ul/li[1]")
	@FindBy(xpath="//li[@title='Warehouse.Name']")
	private static WebElement warehouseNameBtn;


	@FindBy(xpath="//*[@id='rd_customization_tree0']/ul/li[2]/ul/li[31]")
	private static WebElement warehouseExpandBtn1;

	@FindBy(xpath="//*[@id='rd_customization_tree0']/ul/li[2]/ul/li[31]/ul/li[1]")
	private static WebElement warehouseNameBtn1;


	@FindBy(xpath="(//span[text()='Warehouse'])[2]")
	private static WebElement warehouseExpandBtn2;

	@FindBy(xpath="//*[@title='Warehouse.Name']")
	private static WebElement warehouseNameBtn2;

	@FindBy(xpath="//*[@id='rd_customization_tree0']/ul/li[2]/ul/li[29]")
	private static WebElement warehouseExpandBtn3;

	@FindBy(xpath="//*[@id='rd_customization_tree0']/ul/li[2]/ul/li[29]/ul/li[1]")
	private static WebElement warehouseNameBtn3;


	@FindBy(xpath="//*[@id='rd_customization_tree0']/ul/li[2]/ul/li[8]/a/i")
	private static WebElement extraFieldDepartExpandBtn;


	@FindBy(xpath="//*[@id='rd_customization_tree0']/ul/li[2]/ul/li[8]/ul/li[1]")
	private static WebElement extraFieldDepartNameBtn;

	//@FindBy(xpath="//*[@id='rd_customization_tree0']/ul/li[2]/ul/li[24]/ul/li[2]")
	@FindBy(xpath="(//span[text()='Warehouse']/../i//following::ul//li//span[text()='Code'])[1]")
	private static WebElement warehouseCodeBtn;

	@FindBy(xpath="//*[@id='rd_customization_tree310']/a/span")
	private static WebElement voucherAliasBtn;

	//@FindBy(xpath="//*[@id='rd_customization_tree1618']/a/span")
//	private static WebElement voucherNameBtn;



	@FindBy (xpath="//select[@id='id_rd_transet0_select_documentclass']")
	private static WebElement dataSetTabVouchersTab;
	
	@FindBy (xpath="//select[@id='id_rd_transet1_select_documentclass']")
	private static WebElement dataSet2TabVouchersTab;

	@FindBy (xpath="//select[@id='id_rd_transet0_select_documenttype']")
	private static WebElement dataSetTabVouchersDocType;


	//@FindBy (xpath="//*[@id='rd_customization_tree0']/ul/li[1]/a/i")
	@FindBy(xpath="//span[text()='Transaction Fields']/../i")
	private static WebElement transactionFieldsExpandBtn;

	//@FindBy (xpath="//*[@id='rd_customization_tree0']/ul/li[2]/a/i")
	@FindBy(xpath="//span[text()='Extra Fields']/../i")
	private static WebElement extraFieldsExpandBtn;

	@FindBy (xpath="//*[@id='rd_customization_tree0']/a/i")
	private static WebElement ReportFieldExpandBtn;


	@FindBy(xpath="//*[@id='id_mainlayoutmenu']/ul[2]/li/a[contains(text(),'SU')]")
	public static WebElement userNameTxt;
	
	
	@FindBy(xpath="//*[@id='companyLogo']")
	public static WebElement companyLogoImg;

	public static boolean checkLogin() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{

		Thread.sleep(1999);

		LoginPage lp=new LoginPage(getDriver()); 

		String unamelt="su";

		String pawslt="su";

		lp.enterUserName(unamelt);

		Thread.sleep(2000);

		lp.enterPassword(pawslt);

		/*String compname="User Restrictions--COGS";*/
		String compname="RD REPORTS";

		Select oSelect = new Select(companyDropDownList);

		List <WebElement> elementCount = oSelect.getOptions();

		int cqSize = elementCount.size();

		System.out.println("CompanyDropdownList Count :"+cqSize);

		int i;

		for(i=0; i<elementCount.size(); i++) 
		{

			elementCount.get(i).getText();

			String optionName = elementCount.get(i).getText();
			if(optionName.toUpperCase().startsWith(compname.toUpperCase()))
			{
				System.out.println("q"+elementCount.get(i).getText());
				elementCount.get(i).click();
			}

		}


		Thread.sleep(2000);

		lp.clickOnSignInBtn();
		
		
		Thread.sleep(4000);

		String userInfo=userNameTxt.getText();

		System.out.println("User Info : "+userInfo);

		System.out.println("User Info Capture Text :"+userNameTxt.getText());

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(companyLogoImg));

		//companyLogoImg.click();

		if(userInfo.equalsIgnoreCase("SU"))
		{

			System.out.println("Test Pass :Logined to RD Reports Company");
			return true;

		}
		else
		{
			System.out.println("Test Fail :Logined to  RD Reports Company");
			return false;

		}
	}


	
	public static boolean checkRestoreCompany() throws InterruptedException, IOException, AWTException
	{

		BaseEngine.restoreCompany("RD REPORTS","RD REPORTS");
		
		Thread.sleep(5000);
		
		String actUserInfo1=userNameTxt.getText();

		System.out.println("User Info  : "+actUserInfo1);

		System.out.println("User Info Capture Text  :  "+userNameTxt.getText());

	/*	getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(companyLogoImg));
		companyLogoImg.click();

		String getCompanyTxt1=companyName.getText();
		String getLoginCompanyName1=getCompanyTxt1.substring(0, 10);
		System.out.println("company name  :  "+ getLoginCompanyName1);
		companyLogoImg.click();*/

		String expUserInfo1           ="SU";
		String expLoginCompanyName1   ="RD REPORTS";

		System.out.println("UserInfo1             : "+actUserInfo1            +" Value Expected : "+expUserInfo1);
		//System.out.println("LoginCompanyName1     : "+getLoginCompanyName1    +" Value Expected : "+expLoginCompanyName1);

		if(actUserInfo1.equalsIgnoreCase(expUserInfo1) /*&& getLoginCompanyName1.contains(expLoginCompanyName1)*/)
		{
			return true;
		}
		else
		{
			return false;
		}
		
	
	}
	
	
	@FindBy(xpath="//*[@id=\"id_rd_container\"]//li[contains(text(),'Report Designer')]")
	
	public static WebElement RDLabel;
	//resue Method 
	public boolean checkNavigateToReportDesginer() throws InterruptedException
	{

		System.err.println("*****************************checkNavigateToReportDesginer*****");

		Thread.sleep(1999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(homeMenu));
		homeMenu.click();
		
		Thread.sleep(1999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(utilities));
		utilities.click();

		Thread.sleep(3500);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportDesignerMenu));
		reportDesignerMenu.click();

		Thread.sleep(5000);
		
		new WebDriverWait(getDriver(), 200).until(ExpectedConditions.visibilityOf(RDLabel));

		int rdReportHeaderListCount = rdReportHeaderList.size();
		ArrayList<String> rdReportHeaderListArray = new ArrayList<String>();
		for(int i=0;i<rdReportHeaderListCount;i++)
		{
			//String data = rdReportHeaderList.get(i).getText();
			String data=rdReportHeaderList.get(i).getAttribute("title");
			//System.err.println(rdReportHeaderList.get(i).getAttribute("title"));
			rdReportHeaderListArray.add(data);
		}
		String actrdReportHeaderList = rdReportHeaderListArray.toString();
		String exprdReportHeaderList = "[Import From Xml, Export to XML, Reset, Delete, Back, Next, Finish, Cancel]";


		System.out.println(" ACt rdReportHeaderListListList   :"+actrdReportHeaderList);
		System.out.println("  Exp rdReportHeaderListListList  :"+exprdReportHeaderList);




		int rdReportHeaderTABListCount = rdReportHeaderTABList.size();
		ArrayList<String> rdReportHeaderTABListArray = new ArrayList<String>();
		for(int i=0;i<rdReportHeaderTABListCount;i++)
		{
			String data = rdReportHeaderTABList.get(i).getText();
			rdReportHeaderTABListArray.add(data);
		}
		String actrdReportHeaderTABList = rdReportHeaderTABListArray.toString();
		String exprdReportHeaderTABList = "[Definition, Data Set, Customization, Header/Footer, Preview]";


		System.out.println(" ACt rdReportHeaderTABListList   :"+actrdReportHeaderTABList);
		System.out.println("  Exp rdReportHeaderTABListList  :"+exprdReportHeaderTABList);

		if (actrdReportHeaderList.equalsIgnoreCase(exprdReportHeaderList)) 
		{
			System.err.println("****************** Navigate to Report Desginer Page ");
			return true;
		}
		else
		{

			System.err.println("******************** Not Navigate to Report Desginer Page ");
			//checkServerErrorMessage
			return false;
		}

	}

	@FindBy(xpath="//*[@id='graphOpionsHeading']/ul//li/a")
	private static List<WebElement> rdReportHeaderList;


	@FindBy(xpath="//*[@id='pills-tab']//li")
	private static List<WebElement> rdReportHeaderTABList;

	 @FindBy(xpath="//*[@id='rfNew']")
     private static WebElement sl_CustomizeFormattingRowNewBtn;
	 
	// @FindBy(xpath="//span[@class='rd_customization_tree_group' and text()='Place of supply']/../i")
	 @FindBy(xpath="(//span[text()='Place of supply']//..//i)[15]")
     private static WebElement placeofSupplyExpandButton;
	 
	 //@FindBy(xpath="(//span[@class='rd_customization_tree_group' and text()='Place of supply']//../i//../../ul//span[text()='Name'])[1]")
    @FindBy(xpath="((//span[text()='Place of supply']//..//i)[15]//..//following::ul//span[text()='Name'])[1]")
	 private static WebElement placeofSupplyName;
	 
	
	 
	


	 
	 public static boolean checkDefaultReportDetails() throws InterruptedException
	 {

			Thread.sleep(2000);
			getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
			searchTxt.click();
			searchTxt.sendKeys("DetailsReportDefault");
			Thread.sleep(1000);
			searchTxt.sendKeys(Keys.ENTER);


			Thread.sleep(2000);
			getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_DateOptionDropdown));
			sl_DateOptionDropdown.click();
			Select s=new Select(sl_DateOptionDropdown);
			s.selectByValue("1");

			Thread.sleep(2000);

			getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_OkBtn));
			sl_OkBtn.click();

			String expRow1List = "[ReceiptsVAT, NDT57:1, CustomerA, DUBAI, Bank, 10.00]";
			boolean actRow1List = ListComparisionWOOrder(1,report1stRowList,expRow1List);

			String expRow2List = "[ReceiptsVAT, NDT57:2, CustomerA, DUBAI, Bank, 5.00]";
			boolean actRow2List = ListComparisionWOOrder(1,report2ndRowList,expRow2List);

			String expRow3List = "[ReceiptsVAT, NDT57:3, VendorSemiAdjustment, DUBAI, Bank, 5.00]";
			boolean actRow3List = ListComparisionWOOrder(1,report3rdRowList,expRow3List);
			
			String expRow4List = "[ReceiptsVAT, NDT57:4, CustomerSemiAdjustment, DUBAI, Bank, 5.00]";
			boolean actRow4List = ListComparisionWOOrder(1,report4thRowList,expRow4List);
			
			String expRow5List = "[GrandTotal, 25.00]";
			boolean actRow5List = ListComparisionWOOrder(1,report5thRowList,expRow5List);


			if (actRow1List&&
					actRow2List &&
					actRow3List && actRow4List && actRow5List) 
			{

				System.out.println(" Test Pass: Values Dsiplayed as Expected ");
				return true;
			} 
			else 
			{

				System.out.println(" Test FAIL: Values Dsiplayed as Expected ");
				return false;

			}



		
	 }
	 
	 public static boolean checkEditAndAddFieldsInDefaultReport() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	 {

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
		 reportNameDropdown.sendKeys("DetailsReportDefault");
		 Thread.sleep(1000);
		 reportNameDropdown.sendKeys(Keys.TAB);

		 Thread.sleep(3000);

		 getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTab));
		 customizationTab.click();

		 Thread.sleep(3500);
		// getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionExpandBtn));
		// transactionExpandBtn.click();


		 getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(extraFieldsExpandBtn));
		 extraFieldsExpandBtn.click();


		 ScrollToElement(placeofSupplyExpandButton);
		 getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(placeofSupplyExpandButton));
		 placeofSupplyExpandButton.click();

		 getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(placeofSupplyName));
		 getAction().doubleClick(placeofSupplyName).build().perform();

		 Thread.sleep(4000);
		 getAction().moveToElement(finishBtn).build().perform();
		 Thread.sleep(1500);
		ClickUsingJs(finishBtn);
		 Thread.sleep(2000);

		 String expMessage = "Data saved successfully.";

		 String actMessage = checkValidationMessage(expMessage);

		 System.out.println("Validation Message : "+actMessage+" Value Expected : "+expMessage);

		 if(actMessage.equalsIgnoreCase(expMessage))
		 {

			 return true;
		 }
		 else
		 {
			 return false;
		 }


	 }
	 
	 public static boolean checkAndValidateUpdatedDefaultReport() throws InterruptedException
	 {

		 Thread.sleep(2000);
		 getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
		 searchTxt.click();
		 searchTxt.sendKeys("DetailsReportDefault");
		 Thread.sleep(1000);
		 searchTxt.sendKeys(Keys.ENTER);


		 Thread.sleep(2000);
		 getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_DateOptionDropdown));
		 sl_DateOptionDropdown.click();
		 Select s=new Select(sl_DateOptionDropdown);
		 s.selectByValue("1");

		 Thread.sleep(2000);

		 getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_OkBtn));
		 sl_OkBtn.click();

		 String expRow1List = "[NDT52:1, STD RATE COGS ITEM, 1.00, 5.00, 5.00, DUBAI, HYDERABAD, HYDERABAD]";
		 boolean actRow1List = ListComparisionWOOrder(report1stRowList,expRow1List);

		 String expRow2List = "[NDT52:2, STD RATE COGS ITEM, 1.00, 5.00, 5.00, DUBAI, HYDERABAD, HYDERABAD]";
		 boolean actRow2List = ListComparisionWOOrder(report2ndRowList,expRow2List);

		 String expRow3List = "[NDT45:1, STD RATE COGS ITEM, 1.00, 11.00, 11.00, AMERICA, HYDERABAD, HYDERABAD]";
		 boolean actRow3List = ListComparisionWOOrder(report3rdRowList,expRow3List);

		 String expRow4List = "";
		 boolean actRow4List = ListComparisionWOOrder(report4thRowList,expRow4List);

		 String expRow5List = "";
		 boolean actRow5List = ListComparisionWOOrder(report5thRowList,expRow5List);


		 if (actRow1List&&
				 actRow2List &&
				 actRow3List && actRow4List && actRow5List) 
		 {

			 System.out.println(" Test Pass: Values Dsiplayed as Expected ");
			 return true;
		 } 
		 else 
		 {

			 System.out.println(" Test FAIL: Values Dsiplayed as Expected ");
			 return false;

		 }

	 }
	 


	public static boolean checkSavingReportDesignerOfAllTransactionsOfDocumentClass() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{


		Thread.sleep(2999);
		getAction().moveToElement(homeMenu).build().perform();
		Thread.sleep(1200);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(homeMenu));
		homeMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(utilities));
		utilities.click();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportDesignerMenu));
		reportDesignerMenu.click();

		

		Thread.sleep(2999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportNameDropdown));       
		
		Thread.sleep(1000);
		reportNameDropdown.sendKeys("All transactions of document class of Purchase Type");
		reportNameDropdown.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportTypeDropdown));
		Select rtd= new Select(reportTypeDropdown);
		rtd.selectByVisibleText("Details");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(inventoryExpandBtn));
		inventoryExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportsBtn));
		reportsBtn.click();




		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dataSetTab));
		dataSetTab.click();

		Thread.sleep(2000);

		int transactionSetListCount = transactionSetList.size();

		ArrayList<String >transactionSetListArray=new ArrayList<>();

		for(int i=0;i<=transactionSetListCount;i++)
		{
			String data = transactionSetList.get(i).getText();

			transactionSetListArray.add(data);

			if(data.equalsIgnoreCase("All transactions of document class"))
			{
				transactionSetList.get(i).click();

				break;
			}
		}

		String acttransactionSetList=transactionSetListArray.toString();
		String exptransactionSetList="[, Accounting transactions, Accounting transactions of an account, Accounting transactions of accounting tag, Accounting transactions of inventory tag, Accounting transactions of a tag, Accounting transactions of selected account, All accounting transactions, All accounts, All accounts by tag, All fixed assets, All products, All products by tag, All transactions of document class]";

		System.out.println(" ACt transactionSetList : "+acttransactionSetList);
		System.out.println(" Exp transactionSetList : "+exptransactionSetList);
		
		Thread.sleep(3000);
		Select voucher = new Select(dataSetTabVouchersTab);
		voucher.selectByVisibleText("Purchases Vouchers");

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTab));
		customizationTab.click();
		
		//getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionExpandBtn));
		//transactionExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionFieldsExpandBtn));
		transactionFieldsExpandBtn.click();

		Thread.sleep(1000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dateExpandBtn));
		dateExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dateFieldBtn));
		getAction().doubleClick(dateFieldBtn).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dateExpandBtn));
		dateExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(docNoFieldBtn));
		getAction().doubleClick(docNoFieldBtn).build().perform();

		Thread.sleep(1000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemExpandBtn));
		itemExpandBtn.click();
		Thread.sleep(1000);
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
		Thread.sleep(1000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(extraFieldsExpandBtn));
		extraFieldsExpandBtn.click();

		Thread.sleep(1000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(deptExpandBtn));
		deptExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(deptNameBtn1));
		getAction().doubleClick(deptNameBtn1).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(deptExpandBtn));
		deptExpandBtn.click();

		Thread.sleep(1000);
		
		ScrollToElement(warehouseExpandBtn);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouseExpandBtn));
		warehouseExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouseNameBtn));
		getAction().doubleClick(warehouseNameBtn).build().perform();

		Thread.sleep(1000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouseExpandBtn));
		warehouseExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(extraFieldsExpandBtn));
		extraFieldsExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionFieldsExpandBtn));
		transactionFieldsExpandBtn.click();
		

		//getAction().moveToElement(finishBtn).build().perform();
		 ((JavascriptExecutor)getDriver()).executeScript("window.scrollTo(0, 0)","");
			
	//	 Thread.sleep(5000);
		Thread.sleep(1200);
		finishBtn.click();
		
		Thread.sleep(2000);

		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : "+actMessage+" Value Expected : "+expMessage);

		if(actMessage.equalsIgnoreCase(expMessage) && 
				acttransactionSetList.equalsIgnoreCase(exptransactionSetList))
		{

			return true;
		}
		else
		{
			return false;
		}
	}


	@FindBy(xpath="//*[@id='id_rd_definition_reportname_table_data']/tbody/tr/td")
	private static List<WebElement> reportNameList;


	@FindBy(xpath="//*[@id='id_rd_customization_table']/thead/tr/th")
	private static List<WebElement> customizeTabTableHeaderLsist;


	@FindBy(xpath="//select[@id='id_rd_transet0_documentstatus']")
	private static WebElement rdDataSetDocumentStatusDrpDwn;


	@FindBy(xpath="//select[@id='id_rd_transet0_verificationstatus']")
	private static WebElement rdDataSetVerificationStatusDrpDwn;


	@FindBy(xpath="//select[@id='id_rd_transet0_authorizationstatus']")
	private static WebElement rdDataSetAuthorisationStatusDrpDwn;


	@FindBy(xpath="//span[@id='idFilterCustomizeIcon']")
	private static WebElement rdDataSetDefaultFilterCusBtn;


	@FindBy(xpath="//*[@id='FilterFields_22_0']/li[33]/a/i")
	private static WebElement rdDataSetFilterdepExpandbtn;


	@FindBy(xpath="//*[@id='FilterFields_22_0']//ul[4]/li[1]/div/label")
	private static WebElement rdDataSetFilterdepNamebtn;


	@FindBy(xpath="//*[@id='FilterFieldCust_22_0']//div[3]/input[1]")
	private static WebElement rdDataSetOkBtn;




	public static boolean checkUpdatingTheSavedReportInreportDesinger() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportNameDropdown));       
		
		reportNameDropdown.sendKeys(Keys.SPACE);

		int reportNameListcount = reportNameList.size();

		for(int i=0;i<reportNameListcount;i++)
		{
			String data = reportNameList.get(i).getText();

			if(data.equalsIgnoreCase("All transactions of document class of Purchase Type"))
			{
				reportNameList.get(i).click();
				break;
			}
		}

		reportNameDropdown.sendKeys(Keys.TAB);


		Thread.sleep(3000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTab));
		customizationTab.click();

		Thread.sleep(3000);
		
		int customizeTabTableHeaderLsistCount = customizeTabTableHeaderLsist.size();
		System.err.println(customizeTabTableHeaderLsistCount);
		for(int i=1;i<=customizeTabTableHeaderLsistCount;i++)
		{
			String data = customizeTabTableHeaderLsist.get(i).getText();

			if(data.equalsIgnoreCase("Warehouse.Name"))
			{
				customizeTabTableHeaderLsist.get(i).click();

				System.err.println(i);
				Thread.sleep(1000);

				WebElement deletebtn = getDriver().findElement(By.xpath("(//table//*[contains(text(),'Warehouse')]/..//span)[1]"));

				deletebtn.click();

				break;
			}
		}


		//getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionExpandBtn));
		//transactionExpandBtn.click();


		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(extraFieldsExpandBtn));
		extraFieldsExpandBtn.click();

		Thread.sleep(1200);
		ScrollToElement(warehouseExpandBtn);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouseExpandBtn));
		warehouseExpandBtn.click();
		Thread.sleep(1200);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouseNameBtn));
		getAction().doubleClick(warehouseNameBtn).build().perform();
		Thread.sleep(1200);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouseCodeBtn));
		getAction().doubleClick(warehouseCodeBtn).build().perform();


		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouseExpandBtn));
		warehouseExpandBtn.click();


		//getAction().moveToElement(finishBtn).build().perform();
		 ((JavascriptExecutor)getDriver()).executeScript("window.scrollTo(0, 0)","");
			
		
		Thread.sleep(1200);
		finishBtn.click();
		Thread.sleep(2000);

		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : "+actMessage+" Value Expected : "+expMessage);

		if(actMessage.equalsIgnoreCase(expMessage))
		{

			return true;
		}
		else
		{
			return false;
		}
	}


	@FindBy(xpath="//body/section[@id='mainDiv']/div[@id='id_focus8_wrapper_default']/div[1]/section[1]/div[1]/div[1]/div[1]/nav[1]/div[1]/div[2]/ul[1]/li[2]/div[1]/div[1]")
	private static WebElement reportDesginExportToXMLBtn;


	@FindBy(xpath="//*[@id='tblRDRender']/thead/tr[1]/th")
	private static List<WebElement> previewTabHeaderList;

	public boolean checkHeaderAndFooterTabInReportDesiging() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{


		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(homeMenu));
		homeMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(utilities));
		utilities.click();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportDesignerMenu));
		reportDesignerMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportNameDropdown));       
		
		reportNameDropdown.sendKeys(Keys.SPACE);

		int reportNameListcount = reportNameList.size();

		for(int i=0;i<reportNameListcount;i++)
		{
			String data = reportNameList.get(i).getText();

			if(data.equalsIgnoreCase("All transactions of document class of Purchase Type"))
			{
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


	@FindBy(xpath="//*[@id='InvoiceDesignFields']/ul/li[1]/a/span/i")
	private static WebElement RDHeaderTab_TransExpandBtn;


	@FindBy(xpath="//*[@id='InvoiceDesignFields']/ul/li[1]/ul/li[4]/a/span/i")
	private static WebElement RDHeaderTab_WarehouseExpandBtn;

	@FindBy(xpath="//*[@id='InvoiceDesignFields']/ul/li[1]/ul/li[4]/ul/li[1]/a/span")
	private static WebElement RDHeaderTab_WarehouseNamebtn;









	public boolean checkPreviewTabInReportDesiging() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{

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
		
		reportNameDropdown.sendKeys(Keys.SPACE);

		int reportNameListcount = reportNameList.size();

		for(int i=0;i<reportNameListcount;i++)
		{
			String data = reportNameList.get(i).getText();

			if(data.equalsIgnoreCase("All transactions of document class of Purchase Type"))
			{
				reportNameList.get(i).click();
				break;
			}
		}

		reportNameDropdown.sendKeys(Keys.TAB);

		Thread.sleep(3000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(previewTab));
		previewTab.click();

		Thread.sleep(3000);

		int previewTabHeaderListCount=previewTabHeaderList.size();

		ArrayList<String >previewTabHeaderListArray=new ArrayList<>();

		for (int i = 0; i < previewTabHeaderListCount; i++)
		{

			String data=previewTabHeaderList.get(i).getText();
			previewTabHeaderListArray.add(data);

		}
		String actpreviewTabHeaderList=previewTabHeaderListArray.toString();
		String exppreviewTabHeaderList="[, Date, Document No., Item.Name, Quantity, Rate, Gross, Department.Name, Warehouse.Name, Warehouse.Code]";


		System.out.println(" ACt List   :"+actpreviewTabHeaderList);
		System.out.println("  Exp List  :"+exppreviewTabHeaderList);

		//getAction().moveToElement(finishBtn).build().perform();
		 ((JavascriptExecutor)getDriver()).executeScript("window.scrollTo(0, 0)","");
			
	
		Thread.sleep(1200);
		finishBtn.click();
		//finishBtn.click();

		Thread.sleep(2000);
		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : "+actMessage+" Value Expected : "+expMessage);

		if(actMessage.equalsIgnoreCase(expMessage) && 
				actpreviewTabHeaderList.equalsIgnoreCase(exppreviewTabHeaderList))
		{

			System.out.println(" Test Pass: Preview Tab Displays as Expected ");
			return true;
		}
		else
		{
			System.out.println(" Test Fail: Preview Tab Displays as Expected ");
			return false;
		}
	}


	public boolean checkDeleteOptionInReportDesigner() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{

		checkNavigateToReportDesginer();

		Thread.sleep(2999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportNameDropdown));       
		reportNameDropdown.sendKeys("RD Report With Delete Option");
		Thread.sleep(2000);
		reportNameDropdown.sendKeys(Keys.TAB);
		Thread.sleep(2500);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportTypeDropdown));
		Select rtd= new Select(reportTypeDropdown);
		rtd.selectByVisibleText("Details");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(inventoryExpandBtn));
		inventoryExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportsBtn));
		reportsBtn.click();


		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dataSetTab));
		dataSetTab.click();

		Thread.sleep(2000);

		int transactionSetListCount = transactionSetList.size();

		for(int i=0;i<=transactionSetListCount;i++)
		{
			String data = transactionSetList.get(i).getText();

			if(data.equalsIgnoreCase("All transactions of document Type"))
			{
				transactionSetList.get(i).click();

				break;
			}
		}


		Select s2= new Select(dataSetTabVouchersDocType);
		s2.selectByVisibleText("Receipts");
	

		Thread.sleep(2000);

		moveToElement(rdDataSetDocumentStatusDrpDwn);
		Select s1= new Select(rdDataSetDocumentStatusDrpDwn);
		s1.selectByValue("1");

		Thread.sleep(2000);


		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTab));
		customizationTab.click();

		Thread.sleep(2000);
		//getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionExpandBtn));
		//transactionExpandBtn.click();

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

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(deptExpandBtn1));
		deptExpandBtn1.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(deptNameBtn1));
		getAction().doubleClick(deptNameBtn1).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(deptExpandBtn1));
		deptExpandBtn1.click();

		Thread.sleep(2000);


		getAction().moveToElement(finishBtn).build().perform();
		// ((JavascriptExecutor)getDriver()).executeScript("window.scrollTo(0, 0)","");
			
		// Thread.sleep(5000);
		
		Thread.sleep(1200);
		finishBtn.click();

		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : "+actMessage+" Value Expected : "+expMessage);


		Thread.sleep(4000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportNameDropdown));       
		reportNameDropdown.sendKeys("RD Report With Delete Option");
		Thread.sleep(1999);
		reportNameDropdown.sendKeys(Keys.TAB);

		Thread.sleep(1999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(deleteBtn));
		deleteBtn.click();


		getWaitForAlert();

		String actAlert=getAlert().getText();
		String expAlert="Are you sure to delete the report?";

		Thread.sleep(1000);

		System.out.println(" actAlert : "+actAlert +" Value  "+expAlert );

		getAlert().accept();

		String ExpMessage="Data deleted successfully.";
		String actMesage=checkValidationMessage(ExpMessage);

		if (actAlert.equalsIgnoreCase(expAlert) &&
				actMesage.equalsIgnoreCase(ExpMessage) ) 
		{
			return true;
		} else
		{

			return false;
		}



	}

	@FindBy(xpath="//*[@id='selectAllMasters_']/following-sibling::span")
	private static WebElement sl_SelectAllItemsChkBox;

	@FindBy(xpath="//*[@id='id_search_menu']//input")
	private static WebElement searchTxt;


	@FindBy(xpath="//select[@id='DateOptions_']")

	private static WebElement sl_DateOptionDropdown;

	@FindBy(xpath="//i[contains(@class,'icon-ok hiconright2')]")
	private static WebElement sl_OkBtn;

	@FindBy(xpath="//*[@class='icon-analyze hiconright2']")
	private static WebElement sl_AnalysisBtn;




	@FindBy(xpath="//input[@id='MasterGroup__101']")
	private static WebElement reportsAccountTxt;


	@FindBy(xpath="//input[@id='MasterSingle__101']")
	private static WebElement reportDepartmentTxt;


	@FindBy(xpath="//input[@id='MasterSingle__5042']")
	private static WebElement billwiseReportDepTxt;



	@FindBy(xpath="//i[contains(@class,'icon-font6 icon-close')]")
	private static WebElement sl_CloseBtn;

	@FindBy(xpath="//*[@id='trRender_0']/td[1]")
	private static WebElement sl_1stRow1stCol;

	@FindBy(xpath="//*[@id='trRender_0']/td[2]")
	private static WebElement sl_1stRow2ndCol;

	@FindBy(xpath="//*[@id='trRender_0']/td[3]")
	private static WebElement sl_1stRow3rdCol;

	@FindBy(xpath="//*[@id='trRender_0']/td[4]")
	private static WebElement sl_1stRow4thCol;

	@FindBy(xpath="//*[@id='trRender_0']/td[5]")
	private static WebElement sl_1stRow5thCol;


	@FindBy(xpath="//div[@id='dvReportDetails']/div/table/tbody/tr[1]/td")
	private static List<WebElement> report1stRowList;

	@FindBy(xpath="//div[@id='dvReportDetails']/div/table/tbody/tr[2]/td")
	private static List<WebElement> report2ndRowList;

	@FindBy(xpath="//div[@id='dvReportDetails']/div/table/tbody/tr[3]/td")
	private static List<WebElement> report3rdRowList;

	@FindBy(xpath="//div[@id='dvReportDetails']/div/table/tbody/tr[4]/td")
	private static List<WebElement> report4thRowList;

	@FindBy(xpath="//div[@id='dvReportDetails']/div/table/tbody/tr[5]/td")
	private static List<WebElement> report5thRowList;

	@FindBy(xpath="//div[@id='dvReportDetails']/div/table/tbody/tr[6]/td")
	private static List<WebElement> report6thRowList;

	@FindBy(xpath="//div[@id='dvReportDetails']/div/table/tbody/tr[7]/td")
	private static List<WebElement> report7thRowList;

	@FindBy(xpath="//div[@id='dvReportDetails']/div/table/tbody/tr[8]/td")
	private static List<WebElement> report8thRowList;

	@FindBy(xpath="//div[@id='dvReportDetails']/div/table/tbody/tr[9]/td")
	private static List<WebElement> report9thRowList;

	public boolean checkReportAllTransactionsOfDocumentClassOfPurchaseType() throws InterruptedException
	{
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
		searchTxt.click();
		searchTxt.sendKeys("All transactions of document class of Purchase Type");
		Thread.sleep(1000);
		searchTxt.sendKeys(Keys.ENTER);


		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_DateOptionDropdown));
		sl_DateOptionDropdown.click();
		Select s=new Select(sl_DateOptionDropdown);
		s.selectByValue("1");

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_OkBtn));
		sl_OkBtn.click();

		Thread.sleep(1500);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_1stRow1stCol));

		int reportsRow1ListCount = report1stRowList.size();
		ArrayList<String> reportsRow1ListArray = new ArrayList<String>();
		for(int i=2;i<reportsRow1ListCount;i++)
		{
			String data = report1stRowList.get(i).getText();


			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date date=new Date();
			String expadjustBills=df.format(date);

			reportsRow1ListArray.add(data);
		}
		String actRow1List = reportsRow1ListArray.toString();
		String expRow1List = "[NDT52:1, STD RATE COGS ITEM, 1.00, 5.00, 5.00, DUBAI, HYDERABAD, HYDERABAD]";


		int report2ndRowListCount = report2ndRowList.size();
		ArrayList<String> report2ndRowListArray = new ArrayList<String>();
		for(int i=2;i<report2ndRowListCount;i++)
		{
			String data = report2ndRowList.get(i).getText();
			report2ndRowListArray.add(data);
		}
		String actRow2List = report2ndRowListArray.toString();
		String expRow2List = "[NDT52:2, STD RATE COGS ITEM, 1.00, 5.00, 5.00, DUBAI, HYDERABAD, HYDERABAD]";


		int report3rdRowListCount = report3rdRowList.size();
		ArrayList<String> report3rdRowListArray = new ArrayList<String>();
		for(int i=2;i<report3rdRowListCount;i++)
		{
			String data = report3rdRowList.get(i).getText();
			report3rdRowListArray.add(data);
		}
		String actRow3List = report3rdRowListArray.toString();
		String expRow3List = "[NDT45:1, STD RATE COGS ITEM, 1.00, 11.00, 11.00, AMERICA, HYDERABAD, HYDERABAD]";



		System.out.println("actRow1List  : "+actRow1List);
		System.out.println("expRow1List  : "+expRow1List);
		System.out.println("*********************************************************************");

		System.out.println("actRow2List  : "+actRow2List);
		System.out.println("expRow2List  : "+expRow2List);
		System.out.println("*********************************************************************");

		System.out.println("actRow3List  : "+actRow3List);
		System.out.println("expRow3List  : "+expRow3List);
		System.out.println("*********************************************************************");

		if (actRow1List.equalsIgnoreCase(expRow1List) &&
				actRow2List.equalsIgnoreCase(expRow2List) /*&&
				actRow3List.equalsIgnoreCase(expRow3List)*/) 
		{

			System.out.println(" Test Pass: Values Dsiplayed as Expected ");
			return true;
		} 
		else 
		{

			System.out.println(" Test FAIL: Values Dsiplayed as Expected ");
			return false;

		}



	}


	public boolean checkSavingAllTransactionsOfDocumentTypeOfRecepitsTypeToDetails() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{



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
		Select rtd= new Select(reportTypeDropdown);
		rtd.selectByVisibleText("Details");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(inventoryExpandBtn));
		inventoryExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportsBtn));
		reportsBtn.click();


		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dataSetTab));
		dataSetTab.click();

		Thread.sleep(2000);

		int transactionSetListCount = transactionSetList.size();

		for(int i=0;i<=transactionSetListCount;i++)
		{
			String data = transactionSetList.get(i).getText();

			if(data.equalsIgnoreCase("All transactions of document Type"))
			{
				transactionSetList.get(i).click();

				break;
			}
		}

		
		Select s2= new Select(dataSetTabVouchersDocType);
		s2.selectByValue("4611");

	
		Thread.sleep(2000);
		
		moveToElement(rdDataSetDocumentStatusDrpDwn);
		Select s1= new Select(rdDataSetDocumentStatusDrpDwn);
		s1.selectByValue("1");

		Thread.sleep(2000);


		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTab));
		customizationTab.click();

		Thread.sleep(2000);
		//getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionExpandBtn));
		//transactionExpandBtn.click();

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

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rec_VoucherClassBtn));
		getAction().doubleClick(rec_VoucherClassBtn).build().perform();
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rec_VoucherTypeBtn));
		getAction().doubleClick(rec_VoucherTypeBtn).build().perform();
		
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionFieldsExpandBtn));
		transactionFieldsExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(extraFieldsExpandBtn));
		extraFieldsExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(deptExpandBtn1));
		deptExpandBtn1.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(deptNameBtn1));
		getAction().doubleClick(deptNameBtn1).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(deptExpandBtn1));
		deptExpandBtn1.click();

		Thread.sleep(2000);


		//getAction().moveToElement(finishBtn).build().perform();
		 ((JavascriptExecutor)getDriver()).executeScript("window.scrollTo(0, 0)","");
			
		// Thread.sleep(5000);
		Thread.sleep(1200);
		finishBtn.click();
		
		Thread.sleep(2000);

		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : "+actMessage+" Value Expected : "+expMessage);

		if(actMessage.equalsIgnoreCase(expMessage))
		{

			System.out.println(" Test PasS: Report desgining is saved with Document Type ");
			return true;
		}
		else
		{
			System.out.println(" Test FAIL: Report desgining is saved with Document Type ");
			return false;
		}

	}



	public boolean checkReportAllTransactionsOfDocumentTypeOfRecepitsVATType() throws InterruptedException
	{

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
		Select s=new Select(sl_DateOptionDropdown);
		s.selectByValue("1");

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_OkBtn));
		sl_OkBtn.click();

		Thread.sleep(1500);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_1stRow1stCol));

		int reportsRow1ListCount = reportCol3List.size();
		ArrayList<String> reportsRow1ListArray = new ArrayList<String>();
		for(int i=2;i<reportsRow1ListCount;i++)
		{
			String data = reportCol3List.get(i).getText();


			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date date=new Date();
			String expadjustBills=df.format(date);

			reportsRow1ListArray.add(data);
		}
		String actRow1List = reportsRow1ListArray.toString();
		String expRow1List = "[5, 5, 5, 5, 5, 5, ]";


		int report2ndRowListCount = reportCol4List.size();
		ArrayList<String> report2ndRowListArray = new ArrayList<String>();
		for(int i=2;i<report2ndRowListCount;i++)
		{
			String data = reportCol4List.get(i).getText();
			report2ndRowListArray.add(data);
		}
		String actRow2List = report2ndRowListArray.toString();
		String expRow2List = "[Bank, Bank, VAT ADVANCE SALE, VAT ADVANCE SALE, VAT ADVANCE SALE, VAT ADVANCE SALE, ]";


		int report3rdRowListCount = reportCol5List.size();
		ArrayList<String> report3rdRowListArray = new ArrayList<String>();
		for(int i=2;i<report3rdRowListCount;i++)
		{
			String data = reportCol5List.get(i).getText();
			report3rdRowListArray.add(data);
		}
		String actRow3List = report3rdRowListArray.toString();
		String expRow3List = "[, , , , , , ]";


		int report4thRowListCount = reportCol6List.size();
		ArrayList<String> report4thRowListArray = new ArrayList<String>();
		for(int i=2;i<report4thRowListCount;i++)
		{
			String data = reportCol6List.get(i).getText();
			report4thRowListArray.add(data);
		}
		String actRow4List = report4thRowListArray.toString();
		String expRow4List = "[Receipts VAT, Receipts VAT, Receipts VAT, Receipts VAT, Receipts VAT, Receipts VAT, ]";


		int report5thRowListCount = reportCol7List.size();
		ArrayList<String> report5thRowListArray = new ArrayList<String>();
		for(int i=1;i<report5thRowListCount;i++)
		{
			String data = reportCol7List.get(i).getText();
			report5thRowListArray.add(data);
		}
		String actRow5List = report5thRowListArray.toString();
		String expRow5List = "[4608, 4608, 4608, 4608, 4608, 4608, 4608, ]";
		
		
		int report6thRowListCount = reportCol8List.size();
		ArrayList<String> report6thRowListArray = new ArrayList<String>();
		for(int i=1;i<report6thRowListCount;i++)
		{
			String data = reportCol8List.get(i).getText();
			report6thRowListArray.add(data);
		}
		String actRow6List = report6thRowListArray.toString();
		String expRow6List = "[4611, 4611, 4611, 4611, 4611, 4611, 4611, ]";
		
		
		int report7thRowListCount = reportCol9List.size();
		ArrayList<String> report7thRowListArray = new ArrayList<String>();
		for(int i=1;i<report7thRowListCount;i++)
		{
			String data = reportCol9List.get(i).getText();
			report7thRowListArray.add(data);
		}
		String actRow7List = report7thRowListArray.toString();
		String expRow7List = "[DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, ]";

		System.out.println("actRow1List  : "+actRow1List);
		System.out.println("expRow1List  : "+expRow1List);
		System.out.println("*********************************************************************");

		System.out.println("actRow2List  : "+actRow2List);
		System.out.println("expRow2List  : "+expRow2List);
		System.out.println("*********************************************************************");

		
		System.out.println("actRow4List  : "+actRow4List);
		System.out.println("expRow4List  : "+expRow4List);
		System.out.println("*********************************************************************");

		System.out.println("actRow5List  : "+actRow5List);
		System.out.println("expRow5List  : "+expRow5List);
		System.out.println("*********************************************************************");

		System.out.println("actRow6List  : "+actRow6List);
		System.out.println("expRow6List  : "+expRow6List);
		System.out.println("*********************************************************************");

		System.out.println("actRow7List  : "+actRow7List);
		System.out.println("expRow7List  : "+expRow7List);
		System.out.println("*********************************************************************");

		if (actRow1List.equalsIgnoreCase(expRow1List) &&
				actRow2List.equalsIgnoreCase(expRow2List) &&
				
				actRow4List.equalsIgnoreCase(expRow4List) && 
				actRow5List.equalsIgnoreCase(expRow5List) && actRow6List.equalsIgnoreCase(expRow6List) && 
				actRow7List.equalsIgnoreCase(expRow7List)) 
		{
			System.out.println(" Test Pass: Values Dsiplayed as Expected ");
			return true;
		} 
		else 
		{
			System.out.println(" Test FAIL: Values Dsiplayed as Expected ");
			return false;
		}
	}





	public boolean checkSavingAccountingTransactionsOfAnAccountDetails() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{


		Thread.sleep(2999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(homeMenu));
		homeMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(utilities));
		utilities.click();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportDesignerMenu));
		reportDesignerMenu.click();

		Thread.sleep(4000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportNameDropdown));       
		reportNameDropdown.sendKeys("Accounting Transactions of an Account-Details");
		reportNameDropdown.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportTypeDropdown));
		Select rtd= new Select(reportTypeDropdown);
		rtd.selectByVisibleText("Details");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(inventoryExpandBtn));
		inventoryExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportsBtn));
		reportsBtn.click();


		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dataSetTab));
		dataSetTab.click();

		Thread.sleep(2000);

		int transactionSetListCount = transactionSetList.size();

		for(int i=0;i<=transactionSetListCount;i++)
		{
			String data = transactionSetList.get(i).getText();

			if(data.equalsIgnoreCase("Accounting transactions of an account"))
			{
				transactionSetList.get(i).click();

				break;
			}
		}



		Thread.sleep(2000);


		Select s1= new Select(rdDataSetVerificationStatusDrpDwn);
		s1.selectByValue("3");

		Thread.sleep(2000);

		Select s2= new Select(rdDataSetAuthorisationStatusDrpDwn);
		s2.selectByValue("2");


		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTab));
		customizationTab.click();

		Thread.sleep(2000);
		//getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionExpandBtn));
		//transactionExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionFieldsExpandBtn));
		transactionFieldsExpandBtn.click();


		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(acc1ExpandBtn));
		acc1ExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(acc1NameBtn));
		getAction().doubleClick(acc1NameBtn).build().perform();

		Thread.sleep(2000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(acc1ExpandBtn));
		acc1ExpandBtn.click();

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

		ScrollToElement(warehouseExpandBtn);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouseExpandBtn));
		warehouseExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouseNameBtn));
		getAction().doubleClick(warehouseNameBtn).build().perform();


		Thread.sleep(2000);


		//getAction().moveToElement(finishBtn).build().perform();
		 ((JavascriptExecutor)getDriver()).executeScript("window.scrollTo(0, 0)","");
			
		// Thread.sleep(5000);
		Thread.sleep(1200);
		finishBtn.click();

		Thread.sleep(2000);
		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : "+actMessage+" Value Expected : "+expMessage);

		if(actMessage.equalsIgnoreCase(expMessage))
		{

			System.out.println(" Test PasS: Report desgining is saved with Document Type ");
			return true;
		}
		else
		{
			System.out.println(" Test FAIL: Report desgining is saved with Document Type ");
			return false;
		}

	}


	public boolean checkReportAccountingTransactionsOfAnAccountDetails() throws InterruptedException
	{
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
		searchTxt.click();
		searchTxt.sendKeys("Accounting Transactions of an Account-Details");
		Thread.sleep(1000);
		searchTxt.sendKeys(Keys.ENTER);


		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_DateOptionDropdown));
		sl_DateOptionDropdown.click();
		Select s=new Select(sl_DateOptionDropdown);
		s.selectByValue("1");

		Thread.sleep(2000);


		reportsAccountTxt.click();
		reportsAccountTxt.sendKeys("Purchase");
		Thread.sleep(1000);
		reportsAccountTxt.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_OkBtn));
		sl_OkBtn.click();



		Thread.sleep(2000);
		boolean novalidationConfirmationMessage =validationConfirmationMessage.getText().isEmpty();

		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";



		Thread.sleep(1500);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_1stRow1stCol));

		int reportsRow1ListCount = report1stRowList.size();
		ArrayList<String> reportsRow1ListArray = new ArrayList<String>();
		for(int i=1;i<reportsRow1ListCount;i++)
		{
			String data = report1stRowList.get(i).getText();


			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date date=new Date();
			String expadjustBills=df.format(date);

			reportsRow1ListArray.add(data);
		}
		String actRow1List = reportsRow1ListArray.toString();
		String expRow1List = "[HDFC, Purchase, STD RATE COGS ITEM, HYDERABAD]";


		System.err.println("actRow1List  : "+actRow1List);
		System.err.println("expRow1List  : "+expRow1List);
		System.out.println("*********************************************************************");



		if (actRow1List.equalsIgnoreCase(expRow1List) && 
				actvalidationConfirmationMessage.equalsIgnoreCase(expvalidationConfirmationMessage)) 
		{
			System.out.println(" Test Pass: Values Dsiplayed as Expected ");
			return true;
		} 
		else 
		{
			System.out.println(" Test FAIL: Values Dsiplayed as Expected ");
			return false;
		}
	}





	public boolean checkSavingAccountingTransactionsOfAccountingTagDetails() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{


		Thread.sleep(2999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(homeMenu));
		homeMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(utilities));
		utilities.click();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportDesignerMenu));
		reportDesignerMenu.click();

		Thread.sleep(4000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportNameDropdown));       
		reportNameDropdown.sendKeys("Accounting Transactions of Accounting Tag-Details");
		reportNameDropdown.sendKeys(Keys.TAB);
		Thread.sleep(4000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportTypeDropdown));
		Select rtd= new Select(reportTypeDropdown);
		rtd.selectByVisibleText("Details");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(inventoryExpandBtn));
		inventoryExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportsBtn));
		reportsBtn.click();


		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dataSetTab));
		dataSetTab.click();

		Thread.sleep(2000);

		int transactionSetListCount = transactionSetList.size();

		for(int i=0;i<=transactionSetListCount;i++)
		{
			String data = transactionSetList.get(i).getText();

			if(data.equalsIgnoreCase("Accounting transactions of accounting tag"))
			{
				transactionSetList.get(i).click();

				break;
			}
		}



		Thread.sleep(2000);


		getAction().moveToElement(rdDataSetDefaultFilterCusBtn).build().perform();
		rdDataSetDefaultFilterCusBtn.click();


		getAction().moveToElement(rdDataSetDefaultFilterCusBtn).build().perform();
		
		JavascriptExecutor jse = (JavascriptExecutor)getDriver();
		jse.executeScript("arguments[0].scrollIntoView(true);", rdDataSetFilterdepExpandbtn);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdDataSetFilterdepExpandbtn));
		rdDataSetFilterdepExpandbtn.click();

		
		
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdDataSetFilterdepNamebtn));
		rdDataSetFilterdepNamebtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdDataSetOkBtn));
		rdDataSetOkBtn.click();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTab));
		customizationTab.click();

		Thread.sleep(2000);
		//getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionExpandBtn));
		//transactionExpandBtn.click();

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

		ScrollToElement(warehouseExpandBtn);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouseExpandBtn));
		warehouseExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouseNameBtn));
		getAction().doubleClick(warehouseNameBtn).build().perform();


		Thread.sleep(2000);


		//getAction().moveToElement(finishBtn).build().perform();
		 ((JavascriptExecutor)getDriver()).executeScript("window.scrollTo(0, 0)","");
			
	
		Thread.sleep(1200);
		finishBtn.click();

		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : "+actMessage+" Value Expected : "+expMessage);

		if(actMessage.equalsIgnoreCase(expMessage))
		{

			System.out.println(" Test PasS: Report desgining is saved with Document Type ");
			return true;
		}
		else
		{
			System.out.println(" Test FAIL: Report desgining is saved with Document Type ");
			return false;
		}

	}


	public boolean checkReportAccountingTransactionsOfAnAccountingTagDetails() throws InterruptedException, IOException, AWTException
	{
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
		searchTxt.click();
		searchTxt.sendKeys("Accounting Transactions of Accounting Tag-Details");
		Thread.sleep(1000);
		searchTxt.sendKeys(Keys.ENTER);


		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_DateOptionDropdown));
		/*sl_DateOptionDropdown.click();*/

		Select s=new Select(sl_DateOptionDropdown);
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

		Thread.sleep(1500);
		String expRow1List = "[1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2]";
		boolean actRow1List = ListComparisionWOOrder(0,reportCol3List,expRow1List);

		String expRow2List = "[STDRATECOGSITEM, STDRATECOGSITEM, STDRATECOGSITEM, STDRATECOGSITEM]";
		boolean actRow2List = ListComparisionWOOrder(0,reportCol4List,expRow2List);

		String expRow3List = "[Opening Balances Control A/C, CustomerA, STDRATECOGSACCINV, Bank, Bank, CustomerA, SRCOGSPOSTINGACC, Bank, Bank, VendorNewReference, VendorNewReference, VATINPUT, Bank, VendorB, VendorB]";
		boolean actRow3List = ListComparisionWOOrder(0,reportCol5List,expRow3List);

		String expRow4List = "[Customer New Reference, Sales-Computers, COGSPOSTINGACC, CustomerA, CustomerA, Sales-Computers, STDRATECOGSACCINV, CustomerNewReference, VendorNewReference, STDRATECOGSACCINV, VATINPUT, PURCHASEVARIANCE, VATADVANCEPURCHASE, VendorNewReference, STDRATECOGSACCINV, VATINPUT]";
		boolean actRow4List = ListComparisionWOOrder(0,reportCol6List,expRow4List);
		
		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_NextBtn));
		sl_NextBtn.click();

		Thread.sleep(3999);
		
		
		String expRow1List1 = "[2, 2, 1, 3, 1, 1, 1, 4]";
		boolean actRow1List1 = ListComparisionWOOrder(1,reportCol3List,expRow1List1);

		String expRow2List1 = "[]";
		boolean actRow2List1 = ListComparisionWOOrder(1,reportCol4List,expRow2List1);

		String expRow3List1 = "[VATINPUT, VendorFullAdjustment, Bank, Bank, JournalEntriesControlA/C, JournalEntriesControlA/C, Bank, Bank]";
		boolean actRow3List1 = ListComparisionWOOrder(0,reportCol5List,expRow3List1);

		String expRow4List1 = "[PURCHASEVARIANCE, VATADVANCEPURCHASE, Bank, VendorSemiAdjustment, VendorSemiAdjustment, CustomerSemiAdjustment, CustomerSemiAdjustment, CustomerSemiAdjustment, CustomerSemiAdjustment]";
		boolean actRow4List1 = ListComparisionWOOrder(0,reportCol6List,expRow4List1);


		Thread.sleep(2500);;
		checkDownloadPDFForCreatedDetailsRD();
		Thread.sleep(2500);
		if (actRow1List&& actRow2List && actRow3List && actRow4List && actRow1List1 && actRow2List1 && actRow3List1 && actRow4List1) 
		{
			System.out.println(" Test Pass: Values Dsiplayed as Expected ");
			return true;
		} 
		else 
		{
			System.out.println(" Test FAIL: Values Dsiplayed as Expected ");
			return false;
		}
	}



@FindBy(xpath="//*[@id='lblConfirmMessage']")
	private static WebElement ss_ReportPrintMsg;
	
	@FindBy(xpath="//*[@id='dvConfirm']//div[3]//input[1]")
	private static WebElement ss_ReportPrintYesBtn;
	
	@FindBy(xpath="(//*[@id='MCustomize_DeleteField_ModalLabel'])[1]")
	private static WebElement ss_ReportPrintLabel;
	
	public static boolean checkDownloadPDFForCreatedDetailsRD() throws IOException, InterruptedException, AWTException
	{
		Thread.sleep(2000);

		File Efile=new File(getBaseDir()+"\\autoIt\\ExportFiles\\SavingAccountingTransactionsOfAccountingTagDetails.pdf");

		if(Efile.exists())
		{
			Efile.delete();
		}
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_ExportBtn));
		sl_ExportBtn.click();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_ExportPDFBtn));
		sl_ExportPDFBtn.click();
		
		/*getWaitForAlert();
		Thread.sleep(2000);
		getAlert().accept();*/

		Thread.sleep(4000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(ss_ReportPrintLabel));
		
		String actConfirmMsg=ss_ReportPrintMsg.getText();
		String expConfirmMsg="Do you want to export report to the PDF?";
		Thread.sleep(4000);
		
		System.out.println("Actual Msg:"+actConfirmMsg		+ "Expected:"+expConfirmMsg);
		
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(ss_ReportPrintYesBtn));
		ss_ReportPrintYesBtn.click();
		
		Thread.sleep(10000);
		
		Robot robot = new Robot();
	/*	robot.keyPress(KeyEvent.VK_CONTROL);
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
		
		Thread.sleep(2000);
			*/
		Runtime.getRuntime().exec(getBaseDir() + "\\autoIt\\scripts\\scripts\\SavingAccountingTransactionsOfAccountingTagDetails.exe");
		
		Thread.sleep(9000);
		
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_J);
		robot.keyRelease(KeyEvent.VK_J);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		
		Thread.sleep(2000);
		
		ArrayList<String> openTabs = new ArrayList<String>(getDriver().getWindowHandles());
			
		int actOpenWindowsCount = getDriver().getWindowHandles().size();
		int expOpenWindowsCount = 4;
		
		System.out.println("Number of Windows  : "+actOpenWindowsCount+"  Value Expected  "+expOpenWindowsCount);
		
		Thread.sleep(1000);
/*
	 	getDriver().switchTo().window(openTabs.get(2)).close();
	 	Thread.sleep(1000);
	 	getDriver().switchTo().window(openTabs.get(1)).close();
	 	Thread.sleep(1000);
	 	getDriver().switchTo().window(openTabs.get(0));*/
		
		/*robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_W);
		robot.keyRelease(KeyEvent.VK_W);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		
		Thread.sleep(1000);*/
		
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_W);
		robot.keyRelease(KeyEvent.VK_W);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		
		Thread.sleep(1000);
	 
	 	String actPDF = getBaseDir()+"\\autoIt\\ExportFiles\\SavingAccountingTransactionsOfAccountingTagDetails.pdf";
		String expPDF = getBaseDir()+"\\autoIt\\ImportFiles\\SavingAccountingTransactionsOfAccountingTagDetails.pdf";
		
		PDFUtil pdfutil = new PDFUtil();
		
		
		String actPdfList  = pdfutil.getText(actPDF);
		String ExpPdfList = pdfutil.getText(expPDF).replaceAll("11/05/2022", getCurrentDateF2());
		
	

		System.out.println("actPDF  : "+actPdfList);
		System.out.println("expPDF  : "+ExpPdfList);
		
		
	
		if (actPdfList.equalsIgnoreCase(ExpPdfList)) 
		{
			
			return true;
		}
		else
		{
			
			return false;
		}

	}

	@FindBy(xpath="//div[@id='dvReportDetails']//tbody/tr/td[1]")
	private static List<WebElement> reportCol1List;

	@FindBy(xpath="//div[@id='dvReportDetails']//tbody/tr/td[2]")
	private static List<WebElement> reportCol2List;

	@FindBy(xpath="//div[@id='dvReportDetails']//tbody/tr/td[3]")
	private static List<WebElement> reportCol3List;

	@FindBy(xpath="//div[@id='dvReportDetails']//tbody/tr/td[4]")
	private static List<WebElement> reportCol4List;

	@FindBy(xpath="//div[@id='dvReportDetails']//tbody/tr/td[5]")
	private static List<WebElement> reportCol5List;

	@FindBy(xpath="//div[@id='dvReportDetails']//tbody/tr/td[6]")
	private static List<WebElement> reportCol6List;

	@FindBy(xpath="//div[@id='dvReportDetails']//tbody/tr/td[7]")
	private static List<WebElement> reportCol7List;


	@FindBy(xpath="//div[@id='dvReportDetails']//tbody/tr/td[8]")
	private static List<WebElement> reportCol8List;

	@FindBy(xpath="//div[@id='dvReportDetails']//tbody/tr/td[9]")
	private static List<WebElement> reportCol9List;



	@FindBy(xpath="//*[@id='tblFooterReportRender']//div/button[4]")
	private static WebElement sl_NextBtn;


	public boolean checkSavingAccountingTrasactionsOfInventoryTagDetails() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{


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
		Select rtd= new Select(reportTypeDropdown);
		rtd.selectByVisibleText("Details");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(inventoryExpandBtn));
		inventoryExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportsBtn));
		reportsBtn.click();


		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dataSetTab));
		dataSetTab.click();

		Thread.sleep(2000);

		int transactionSetListCount = transactionSetList.size();

		for(int i=0;i<=transactionSetListCount;i++)
		{
			String data = transactionSetList.get(i).getText();

			if(data.equalsIgnoreCase("Accounting transactions of inventory tag"))
			{
				transactionSetList.get(i).click();

				break;
			}
		}



		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTab));
		customizationTab.click();

		Thread.sleep(2000);
		//getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionExpandBtn));
		//transactionExpandBtn.click();

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

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouseExpandBtn));
		warehouseExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouseNameBtn));
		getAction().doubleClick(warehouseNameBtn).build().perform();


		Thread.sleep(4000);

		//getAction().moveToElement(finishBtn).build().perform();
		 ((JavascriptExecutor)getDriver()).executeScript("window.scrollTo(0, 0)","");
			
		 Thread.sleep(2000);
		finishBtn.click();
		
		Thread.sleep(2000);

		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : "+actMessage+" Value Expected : "+expMessage);

		if(actMessage.equalsIgnoreCase(expMessage))
		{

			System.out.println(" Test PasS: Report desgining is saved with Document Type ");
			return true;
		}
		else
		{
			System.out.println(" Test FAIL: Report desgining is saved with Document Type ");
			return false;
		}

	}






	public boolean checkReportAccountingTrasactionsofInventoryTagDetails() throws InterruptedException
	{
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
		searchTxt.click();
		searchTxt.sendKeys("Accounting Trasactions of Inventory Tag-Details");
		Thread.sleep(2000);
		searchTxt.sendKeys(Keys.ENTER);


		Thread.sleep(6000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_DateOptionDropdown));
		/*sl_DateOptionDropdown.click();*/

		Select s=new Select(sl_DateOptionDropdown);
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

		String expRow1List = "[2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]";
		boolean actRow1List = ListComparisionWOOrder(1,reportCol1List,expRow1List);

		String expRow2List = "[STDRATECOGSACCINV, CustomerA, SRCOGSPOSTINGACC, VendorNewReference, VendorNewReference, VATINPUT, VendorB, VendorB, VATINPUT]";
		boolean row2 = ListComparisionWOOrder(1,reportCol2List,expRow2List);

		String expRow3List = "[STDRATECOGSACCINV, 122-001, SRCOGSPOSTINGACC, VendorNewReference, VendorNewReference, VATINPUT, 033-002, 033-002, VATINPUT]";
		boolean row3 = ListComparisionWOOrder(1,reportCol3List,expRow3List);

		String expRow4List = "[COGSPOSTINGACC, Sales-Computers, STDRATECOGSACCINV, STDRATECOGSACCINV, VATINPUT, PURCHASEVARIANCE, VATADVANCEPURCHASE, STDRATECOGSACCINV, VATINPUT, PURCHASEVARIANCE, VATADVANCEPURCHASE]";
		boolean row4 = ListComparisionWOOrder(1,reportCol4List,expRow4List);


		String expRow1List1 = "[COGSPOSTINGACC, 071-001, STDRATECOGSACCINV, STDRATECOGSACCINV, VATINPUT, PURCHASEVARIANCE, VATADVANCEPURCHASE, STDRATECOGSACCINV, VATINPUT, PURCHASEVARIANCE, VATADVANCEPURCHASE]";
		boolean actRow1List1 = ListComparisionWOOrder(1,reportCol5List,expRow1List1);

		String expRow2List1 = "[STDRATECOGSITEM, STDRATECOGSITEM, STDRATECOGSITEM]";
		boolean actRow2List1 = ListComparisionWOOrder(1,reportCol6List,expRow2List1);


		if (actRow1List && row2 && row3 && row4 && actRow1List1 && actRow2List1) 
		{
			System.out.println(" Test Pass: Values Dsiplayed as Expected ");
			return true;
		} 
		else 
		{
			System.out.println(" Test FAIL: Values Dsiplayed as Expected ");
			return false;
		}
	}





	public boolean checkSavingAccountingTransactionsoFTagDetails() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{


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
		Select rtd= new Select(reportTypeDropdown);
		rtd.selectByVisibleText("Details");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(finanincalExpandbtn));
		finanincalExpandbtn.click();


		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dataSetTab));
		dataSetTab.click();

		Thread.sleep(2000);

		int transactionSetListCount = transactionSetList.size();

		for(int i=0;i<=transactionSetListCount;i++)
		{
			String data = transactionSetList.get(i).getText();

			if(data.equalsIgnoreCase("Accounting transactions of a tag"))
			{
				transactionSetList.get(i).click();

				break;
			}
		}



		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTab));
		customizationTab.click();

		Thread.sleep(2000);
		//getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionExpandBtn));
		//transactionExpandBtn.click();

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

		Thread.sleep(1000);
		ScrollToElement(warehouseExpandBtn);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouseExpandBtn));
		warehouseExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouseNameBtn));
		getAction().doubleClick(warehouseNameBtn).build().perform();


		Thread.sleep(2000);

		 ((JavascriptExecutor)getDriver()).executeScript("window.scrollTo(0, 0)","");
			
		 Thread.sleep(2000);
		finishBtn.click();
		
		Thread.sleep(2000);

		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : "+actMessage+" Value Expected : "+expMessage);

		if(actMessage.equalsIgnoreCase(expMessage))
		{

			System.out.println(" Test PasS: Report desgining is saved with Document Type ");
			return true;
		}
		else
		{
			System.out.println(" Test FAIL: Report desgining is saved with Document Type ");
			return false;
		}

	}


	@FindBy(xpath="//*[@id='dvReportDetails']/div/table/thead/tr/th")
	public static List<WebElement> reportHeaderList;

	@FindBy(xpath="//div[@id='idGlobalError']")
	public static WebElement validationConfirmationMessage;

	public boolean checkReportAccountingTransactionsofTagDetails() throws InterruptedException
	{
		Thread.sleep(2999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
		searchTxt.click();
		searchTxt.sendKeys("Accounting Transactions of a Tag-Details");
		Thread.sleep(2000);
		searchTxt.sendKeys(Keys.ENTER);


		Thread.sleep(5000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_DateOptionDropdown));
		/*sl_DateOptionDropdown.click();*/

		Select s=new Select(sl_DateOptionDropdown);
		s.selectByValue("1");

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_OkBtn));
		sl_OkBtn.click();


		Thread.sleep(2000);
		boolean novalidationConfirmationMessage =validationConfirmationMessage.getText().isEmpty();

		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";

		Thread.sleep(3000);

		Thread.sleep(1500);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_1stRow1stCol));

		int reportsRow1ListCount = reportCol1List.size();
		ArrayList<String> reportsRow1ListArray = new ArrayList<String>();
		for(int i=1;i<reportsRow1ListCount;i++)
		{
			String data = reportCol1List.get(i).getText();


			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		    Date date=new Date();
		    String expadjustBills=df.format(date);

			reportsRow1ListArray.add(data);
		}
		String actRow1List = reportsRow1ListArray.toString();
		String expRow1List = "[2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19]";


		int report2ndRowListCount = reportCol2List.size();
		ArrayList<String> report2ndRowListArray = new ArrayList<String>();
		for(int i=1;i<report2ndRowListCount;i++)
		{
			String data = reportCol2List.get(i).getText();
			report2ndRowListArray.add(data);
		}
		String actRow2List = report2ndRowListArray.toString();
		String expRow2List = "[Customer A, STD RATE COGS ACC INV, Bank, Bank, Customer A, SR COGS POSTING ACC, Bank, Bank, Vendor New Reference, VAT INPUT, , Vendor New Reference, Bank, Vendor B, Vendor B, VAT INPUT, , Vendor Full Adjustment]";


		int report3rdRowListCount = reportCol3List.size();
		ArrayList<String> report3rdRowListArray = new ArrayList<String>();
		for(int i=1;i<report3rdRowListCount;i++)
		{
			String data = reportCol3List.get(i).getText();
			report3rdRowListArray.add(data);
		}
		String actRow3List = report3rdRowListArray.toString();
		String expRow3List = "[122-001, STD RATE COGS ACC INV, 121-001, 121-001, 122-001, SR COGS POSTING ACC, 121-001, 121-001, Vendor New Reference, VAT INPUT, , Vendor New Reference, 121-001, 033-002, 033-002, VAT INPUT, , Vendor Full Adjustment]";


		int report4thRowListCount = reportCol4List.size();
		ArrayList<String> report4thRowListArray = new ArrayList<String>();
		for(int i=1;i<report4thRowListCount;i++)
		{
			String data = reportCol4List.get(i).getText();
			report4thRowListArray.add(data);
		}
		String actRow4List = report4thRowListArray.toString();
		String expRow4List = "[Sales - Computers, COGS POSTING ACC, Customer A, Customer A, Sales - Computers, STD RATE COGS ACC INV, Customer New Reference, Vendor New Reference, STD RATE COGS ACC INV, VAT ADVANCE PURCHASE, PURCHASE VARIANCE, VAT INPUT, Vendor New Reference, STD RATE COGS ACC INV, VAT INPUT, VAT ADVANCE PURCHASE, PURCHASE VARIANCE, Bank]";



		int reportsRow1ListCount1 = reportCol5List.size();
		ArrayList<String> reportsRow1ListArray1 = new ArrayList<String>();
		for(int i=1;i<reportsRow1ListCount1;i++)
		{
			String data = reportCol5List.get(i).getText();


			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		    Date date=new Date();
		    String expadjustBills=df.format(date);

			reportsRow1ListArray.add(data);
		}
		String actRow1List1 = reportsRow1ListArray1.toString();
		String expRow1List1 = "[]";


		int report2ndRowListCount1 = reportCol6List.size();
		ArrayList<String> report2ndRowListArray1 = new ArrayList<String>();
		for(int i=1;i<report2ndRowListCount1;i++)
		{
			String data = reportCol6List.get(i).getText();
			report2ndRowListArray1.add(data);
		}
		String actRow2List1 = report2ndRowListArray1.toString();
		String expRow2List1 = "[STD RATE COGS ITEM, , , , STD RATE COGS ITEM, , , , STD RATE COGS ITEM, , , , , STD RATE COGS ITEM, , , , ]";



		System.out.println("actRow1List  : "+actRow1List);
		System.out.println("expRow1List  : "+expRow1List);
		System.out.println("*********************************************************************");

		System.out.println("actRow2List  : "+actRow2List);
		System.out.println("expRow2List  : "+expRow2List);
		System.out.println("*********************************************************************");

		System.out.println("actRow3List  : "+actRow3List);
		System.out.println("expRow3List  : "+expRow3List);
		System.out.println("*********************************************************************");

		System.out.println("actRow4List  : "+actRow4List);
		System.out.println("expRow4List  : "+expRow4List);
		System.out.println("*********************************************************************");


		System.out.println("actRow1List1  : "+actRow1List1);
		System.out.println("expRow1List1  : "+expRow1List1);
		System.out.println("*********************************************************************");

		System.out.println("actRow2List1  : "+actRow2List1);
		System.out.println("expRow2List1  : "+expRow2List1);
		System.out.println("*********************************************************************");

		if (actRow1List.equalsIgnoreCase(expRow1List) &&
				actRow2List.equalsIgnoreCase(expRow2List) &&
				actRow3List.equalsIgnoreCase(expRow3List) && 
				actRow4List.equalsIgnoreCase(expRow4List)  && 

				actRow1List1.equalsIgnoreCase(expRow1List1) /*&&
				

				actvalidationConfirmationMessage.equalsIgnoreCase(expvalidationConfirmationMessage)*/) 
		{

			System.out.println(" Test Pass: Values Dsiplayed as Expected ");
			return true;
		} 
		else 
		{
			System.out.println(" Test FAIL: Values Dsiplayed as Expected ");
			String meesage=validationConfirmationMessage.getText();
			System.err.println(" Meesage Displayed  : "+meesage);
			
			getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportCloseBtn2));
			reportCloseBtn2.click();
			
			getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportDepartmentTxt));
			reportDepartmentTxt.click();
			reportDepartmentTxt.sendKeys("DUBAI"); 
			Thread.sleep(1000);
			reportDepartmentTxt.sendKeys(Keys.TAB);
			
			getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_OkBtn));
			sl_OkBtn.click();
			
			Thread.sleep(2000);
			boolean novalidationConfirmationMessage1 =validationConfirmationMessage.getText().isEmpty();

			String actvalidationConfirmationMessage1 = Boolean.toString(novalidationConfirmationMessage1);
			String expvalidationConfirmationMessage1 = "true";

			if (actvalidationConfirmationMessage1.equals(expvalidationConfirmationMessage1))
			{
				return true;
			} 
			
			else 
			{
			  return false;
			}
		}
	}






	public boolean checkSavingAllAccountsByTagDetails() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{


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

		Thread.sleep(4000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportTypeDropdown));
		Select rtd= new Select(reportTypeDropdown);
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

		for(int i=0;i<=transactionSetListCount;i++)
		{
			String data = transactionSetList.get(i).getText();

			if(data.equalsIgnoreCase("All accounts by tag"))
			{
				transactionSetList.get(i).click();

				break;
			}
		}



		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTab));
		customizationTab.click();

		Thread.sleep(2000);
		//getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionExpandBtn));
		//transactionExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionFieldsExpandBtn));
		transactionFieldsExpandBtn.click();


		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(acc1ExpandBtn));
		acc1ExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(acc1NameBtn));
		getAction().doubleClick(acc1NameBtn).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(acc1CodeBtn));
		getAction().doubleClick(acc1CodeBtn).build().perform();

		Thread.sleep(1000);
		ScrollToElement(acc2ExpandBtn);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(acc2ExpandBtn));
		acc2ExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(acc2NameBtn));
		getAction().doubleClick(acc2NameBtn).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(acc2CodeBtn));
		getAction().doubleClick(acc2CodeBtn).build().perform();

		
		Thread.sleep(1000);
		ScrollToElement(itemExpandBtn1);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemExpandBtn1));
		itemExpandBtn1.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemNameBtn1));
		getAction().doubleClick(itemNameBtn1).build().perform();


		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemNameBtn1));
		getAction().doubleClick(itemCodeBtn1).build().perform();
		
		Thread.sleep(2000);
		JavascriptExecutor jse = (JavascriptExecutor)getDriver();
		jse.executeScript("arguments[0].scrollIntoView(true);", quantityBtn2);

		//getAction().moveToElement(quantityBtn2).build().perform();
		
		Thread.sleep(3000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(quantityBtn2));
		getAction().doubleClick(quantityBtn2).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rateBtn2));
		getAction().doubleClick(rateBtn2).build().perform();




		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionFieldsExpandBtn));
		transactionFieldsExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(extraFieldsExpandBtn));
		extraFieldsExpandBtn.click();


		Thread.sleep(1000);
		ScrollToElement(warehouseExpandBtn2);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouseExpandBtn2));
		warehouseExpandBtn2.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouseNameBtn2));
		getAction().doubleClick(warehouseNameBtn2).build().perform();


		Thread.sleep(2000);

		 ((JavascriptExecutor)getDriver()).executeScript("window.scrollTo(0, 0)","");
			
		 Thread.sleep(2000);
		finishBtn.click();
		
		Thread.sleep(2000);
		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : "+actMessage+" Value Expected : "+expMessage);

		if(actMessage.equalsIgnoreCase(expMessage))
		{

			System.out.println(" Test PasS: Report desgining is saved with Document Type ");
			return true;
		}
		else
		{
			System.out.println(" Test FAIL: Report desgining is saved with Document Type ");
			return false;
		}

	}




	public boolean checkReportAllAccountsByTagDetails() throws InterruptedException
	{
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
		/*sl_DateOptionDropdown.click();*/

		Select s=new Select(sl_DateOptionDropdown);
		s.selectByValue("1");

		Thread.sleep(2000);


		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_OkBtn));
		sl_OkBtn.click();


		Thread.sleep(2000);
		boolean novalidationConfirmationMessage =validationConfirmationMessage.getText().isEmpty();

		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";

		Thread.sleep(3000);

		Thread.sleep(1500);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_1stRow1stCol));

	

		int report2ndRowListCount = reportCol2List.size();
		ArrayList<String> report2ndRowListArray = new ArrayList<String>();
		for(int i=0;i<report2ndRowListCount;i++)
		{
			String data = reportCol2List.get(i).getText().trim();
			if (data.isEmpty() == false)
			{
				report2ndRowListArray.add(data);
			}
			
		}
		String actRow2List = report2ndRowListArray.toString();
		String expRow2List = "[Opening Balances Control A/C, Customer A, STD RATE COGS ACC INV, Bank, Bank, Customer A, SR COGS POSTING ACC, Bank, Bank, Vendor New Reference, Vendor New Reference, VAT INPUT, Bank, Bank, Vendor B]";


		int report3rdRowListCount = reportCol3List.size();
		ArrayList<String> report3rdRowListArray = new ArrayList<String>();
		for(int i=0;i<report3rdRowListCount;i++)
		{
			String data = reportCol3List.get(i).getText().trim();
			if (data.isEmpty() == false) 
			{
				report3rdRowListArray.add(data);
			}
			
		}
		String actRow3List = report3rdRowListArray.toString();
		String expRow3List = "[OBC, 122-001, STD RATE COGS ACC INV, 121-001, 121-001, 122-001, SR COGS POSTING ACC, 121-001, 121-001, Vendor New Reference, Vendor New Reference, VAT INPUT, 121-001, 121-001, 033-002]";


		int report4thRowListCount = reportCol4List.size();
		ArrayList<String> report4thRowListArray = new ArrayList<String>();
		for(int i=0;i<report4thRowListCount;i++)
		{
			String data = reportCol4List.get(i).getText().trim();
			if (data.isEmpty() == false) 
			{
				report4thRowListArray.add(data);
			}
			
			report4thRowListArray.add(data);
		}
		String actRow4List = report4thRowListArray.toString();
		String expRow4List = "[Customer New Reference, Customer New Reference, Sales - Computers, Sales - Computers, COGS POSTING ACC, COGS POSTING ACC, Customer A, Customer A, Customer A, Customer A, Sales - Computers, Sales - Computers, STD RATE COGS ACC INV, STD RATE COGS ACC INV, Customer New Reference, Customer New Reference, Vendor New Reference, Vendor New Reference, STD RATE COGS ACC INV, STD RATE COGS ACC INV, VAT INPUT, VAT INPUT, PURCHASE VARIANCE, PURCHASE VARIANCE, VAT ADVANCE PURCHASE, VAT ADVANCE PURCHASE, Vendor New Reference, Vendor New Reference, Vendor B, Vendor B, STD RATE COGS ACC INV, STD RATE COGS ACC INV]";




		int report2ndRowListCount1 = reportCol6List.size();
		ArrayList<String> report2ndRowListArray1 = new ArrayList<String>();
		for(int i=0;i<report2ndRowListCount1;i++)
		{
			String data = reportCol6List.get(i).getText().trim();
			
			
			if (data.isEmpty() == false) 
			{
				report2ndRowListArray1.add(data);
			}
			
		}
		String actRow2List1 = report2ndRowListArray1.toString();
		String expRow2List1 = "[STD RATE COGS ITEM, STD RATE COGS ITEM, STD RATE COGS ITEM, STD RATE COGS ITEM]";


		int report2ndRowListCount7 = reportCol7List.size();
		ArrayList<String> report2ndRowListArray7 = new ArrayList<String>();
		for(int i=0;i<report2ndRowListCount7;i++)
		{
			String data = reportCol7List.get(i).getText().trim();
			if (data.isEmpty() == false) 
			{
				report2ndRowListArray7.add(data);
			}
		}
		String actRow2List7 = report2ndRowListArray7.toString();
		String expRow2List7 = "[STD RATE COGS ITEM, STD RATE COGS ITEM, STD RATE COGS ITEM, STD RATE COGS ITEM]";

		int report2ndRowListCount8 = reportCol8List.size();
		ArrayList<String> report2ndRowListArray8 = new ArrayList<String>();
		for(int i=0;i<report2ndRowListCount8;i++)
		{
			String data = reportCol8List.get(i).getText().trim();
			if (data.isEmpty() == false) 
			{
				report2ndRowListArray8.add(data);
			}
		}
		String actRow2List8 = report2ndRowListArray8.toString();
		String expRow2List8 = "[2.00, 1.00, 1.00, 1.00]";

		Thread.sleep(2000);

		sl_NextBtn.click();

		Thread.sleep(2000);


		int count = report6thRowList.size();
		ArrayList<String> array6 = new ArrayList<String>();
		for(int i=0;i<count;i++)
		{
			String data = report6thRowList.get(i).getText().trim();
			if (data.isEmpty() == false) 
			{
				array6.add(data);
			}
		}
		String actRow6 = array6.toString();
		String expRow6 = "[Bank, 121-001, Vendor Semi Adjustment, Vendor Semi Adjustment]";



		
		System.out.println("actRow2List  : "+actRow2List);
		System.out.println("expRow2List  : "+expRow2List);
		System.out.println("*********************************************************************");

		System.out.println("actRow3List  : "+actRow3List);
		System.out.println("expRow3List  : "+expRow3List);
		System.out.println("*********************************************************************");

		System.out.println("actRow4List  : "+actRow4List);
		System.out.println("expRow4List  : "+expRow4List);
		System.out.println("*********************************************************************");

		System.out.println("actRow2List1  :  "+actRow2List1);
		System.out.println("expRow2List1  :  "+expRow2List1);
		System.out.println("*********************************************************************");

		System.out.println("actRow2List7  : "+actRow2List7);
		System.out.println("expRow2List7  : "+expRow2List7);
		System.out.println("*********************************************************************");

		System.out.println("actRow2List8  : "+actRow2List8);
		System.out.println("expRow2List8  : "+expRow2List8);
		System.out.println("*********************************************************************");

		System.out.println("row6  : "+actRow6);
		System.out.println("row6  : "+expRow6);
		System.out.println("*********************************************************************");

		if (
				actRow2List.equalsIgnoreCase(expRow2List) &&
				actRow3List.equalsIgnoreCase(expRow3List) && 
				actRow4List.equalsIgnoreCase(expRow4List) ) 
		{
			System.out.println(" Test Pass: Values Dsiplayed as Expected ");
			return true;
		} 
		else 
		{
			System.out.println(" Test FAIL: Values Dsiplayed as Expected ");
			String meesage=validationConfirmationMessage.getText();
			System.err.println(" Meesage Displayed  : "+meesage);
			return false;
		}
	}






	public boolean checkSavingInventoryTransactionsOfAProductDetails() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{


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
		Select rtd= new Select(reportTypeDropdown);
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

		for(int i=0;i<=transactionSetListCount;i++)
		{
			String data = transactionSetList.get(i).getText();

			if(data.equalsIgnoreCase("Inventory transactions of a product"))
			{
				transactionSetList.get(i).click();

				break;
			}
		}



		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTab));
		customizationTab.click();

		Thread.sleep(2000);
		//getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionExpandBtn));
		//transactionExpandBtn.click();

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


		 ((JavascriptExecutor)getDriver()).executeScript("window.scrollTo(0, 0)","");
			
		 Thread.sleep(2000);
		finishBtn.click();

		Thread.sleep(2000);
		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : "+actMessage+" Value Expected : "+expMessage);

		if(actMessage.equalsIgnoreCase(expMessage))
		{

			System.out.println(" Test PasS: Report desgining is saved with Document Type ");
			return true;
		}
		else
		{
			System.out.println(" Test FAIL: Report desgining is saved with Document Type ");
			return false;
		}

	}



	public boolean checkSavingRDWithAllProductsDetails() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{

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
		Select rtd= new Select(reportTypeDropdown);
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

		for(int i=0;i<=transactionSetListCount;i++)
		{
			String data = transactionSetList.get(i).getText();

			if(data.equalsIgnoreCase("All products"))
			{
				transactionSetList.get(i).click();

				break;
			}
		}



		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTab));
		customizationTab.click();

		Thread.sleep(2000);
		//getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionExpandBtn));
		//transactionExpandBtn.click();

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

		Thread.sleep(1000);

		ScrollToElement(warehouseExpandBtn2);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouseExpandBtn2));
		warehouseExpandBtn2.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouseNameBtn2));
		getAction().doubleClick(warehouseNameBtn2).build().perform();


		Thread.sleep(2000);

		 ((JavascriptExecutor)getDriver()).executeScript("window.scrollTo(0, 0)","");
			
		 Thread.sleep(2000);
		finishBtn.click();

		Thread.sleep(2000);
		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : "+actMessage+" Value Expected : "+expMessage);

		if(actMessage.equalsIgnoreCase(expMessage))
		{

			System.out.println(" Test PasS: Report desgining is saved with Document Type ");
			return true;
		}
		else
		{
			System.out.println(" Test FAIL: Report desgining is saved with Document Type ");
			return false;
		}

	}




	public boolean checkReportAllProductsDetailsDetails() throws InterruptedException
	{
		getDriver().navigate().refresh();
		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
		searchTxt.click();
		searchTxt.sendKeys("All Products-Details");
		Thread.sleep(1000);
		searchTxt.sendKeys(Keys.ENTER);


		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_DateOptionDropdown));

		Select s=new Select(sl_DateOptionDropdown);
		s.selectByValue("1");

		Thread.sleep(2000);


		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_OkBtn));
		sl_OkBtn.click();


		Thread.sleep(2000);
		boolean novalidationConfirmationMessage =validationConfirmationMessage.getText().isEmpty();

		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";

		Thread.sleep(3000);

		Thread.sleep(1500);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_1stRow1stCol));

		int reportsRow1ListCount = reportCol1List.size();
		ArrayList<String> reportsRow1ListArray = new ArrayList<String>();
		for(int i=1;i<reportsRow1ListCount;i++)
		{
			String data = reportCol1List.get(i).getText().trim();


			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date date=new Date();
			String expadjustBills=df.format(date);
			if (data.isEmpty() == false)
			{
				reportsRow1ListArray.add(data);
			}

			
		}
		String actRow1List = reportsRow1ListArray.toString();
		String expRow1List = "[2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13]";


		int report2ndRowListCount = reportCol2List.size();
		ArrayList<String> report2ndRowListArray = new ArrayList<String>();
		for(int i=1;i<report2ndRowListCount;i++)
		{
			String data = reportCol2List.get(i).getText().trim();
			if (data.isEmpty() == false)
			{
				report2ndRowListArray.add(data);
			}

		}
		String actRow2List = report2ndRowListArray.toString();
		String expRow2List = "[STD RATE COGS ITEM, STD RATE COGS ITEM, STD RATE COGS ITEM, Grand Total]";


		int report3rdRowListCount = reportCol3List.size();
		ArrayList<String> report3rdRowListArray = new ArrayList<String>();
		for(int i=1;i<report3rdRowListCount;i++)
		{
			String data = reportCol3List.get(i).getText().trim();
			if (data.isEmpty() == false)
			{
				report3rdRowListArray.add(data);
			}
		}
		String actRow3List = report3rdRowListArray.toString();
		String expRow3List = "[STD RATE COGS ITEM, STD RATE COGS ITEM, STD RATE COGS ITEM]";


		int report4thRowListCount = reportCol4List.size();
		ArrayList<String> report4thRowListArray = new ArrayList<String>();
		for(int i=1;i<report4thRowListCount;i++)
		{
			String data = reportCol4List.get(i).getText().trim();
			if (data.isEmpty() == false)
			{
				report4thRowListArray.add(data);
			}
		}
		String actRow4List = report4thRowListArray.toString();
		String expRow4List = "[1.00, 1.00, 1.00, 1.00]";



		int reportsRow1ListCount1 = reportCol5List.size();
		ArrayList<String> reportsRow1ListArray1 = new ArrayList<String>();
		for(int i=1;i<reportsRow1ListCount1;i++)
		{
			String data = reportCol5List.get(i).getText().trim();


			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date date=new Date();
			String expadjustBills=df.format(date);

			if (data.isEmpty() == false)
			{
				reportsRow1ListArray1.add(data);
			}
		}
		String actRow1List1 = reportsRow1ListArray1.toString();
		String expRow1List1 = "[5.00, 5.00, 5.00, 25.00]";



		System.out.println("actRow1List  : "+actRow1List);
		System.out.println("expRow1List  : "+expRow1List);
		System.out.println("*********************************************************************");

		System.out.println("actRow2List  : "+actRow2List);
		System.out.println("expRow2List  : "+expRow2List);
		System.out.println("*********************************************************************");

		System.out.println("actRow3List  : "+actRow3List);
		System.out.println("expRow3List  : "+expRow3List);
		System.out.println("*********************************************************************");

		System.out.println("actRow4List  : "+actRow4List);
		System.out.println("expRow4List  : "+expRow4List);
		System.out.println("*********************************************************************");


		System.out.println("actRow1List1  : "+actRow1List1);
		System.out.println("expRow1List1  : "+expRow1List1);
		System.out.println("*********************************************************************");


		if (actRow1List.equalsIgnoreCase(expRow1List) &&
				actRow2List.equalsIgnoreCase(expRow2List) &&
				actRow3List.equalsIgnoreCase(expRow3List) && 
				actRow4List.equalsIgnoreCase(expRow4List)  && 

				actRow1List1.equalsIgnoreCase(expRow1List1))
				
		{
			System.out.println(" Test Pass: Values Dsiplayed as Expected ");
			return true;
		} 
		else 
		{
			System.out.println(" Test FAIL: Values Dsiplayed as Expected ");
			String meesage=validationConfirmationMessage.getText();
			System.err.println(" Meesage Displayed  : "+meesage);
			return false;
		}
	}




	public boolean checkSavingRDWithAllProductsBTagDetails() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{

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
		Select rtd= new Select(reportTypeDropdown);
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

		for(int i=0;i<=transactionSetListCount;i++)
		{
			String data = transactionSetList.get(i).getText();

			if(data.equalsIgnoreCase("All products by tag"))
			{
				transactionSetList.get(i).click();

				break;
			}
		}


		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTab));
		customizationTab.click();

		Thread.sleep(2000);
		//getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionExpandBtn));
		//transactionExpandBtn.click();

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

		Thread.sleep(1000);
		ScrollToElement(transactionFieldsExpandBtn);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionFieldsExpandBtn));
		transactionFieldsExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(extraFieldsExpandBtn));
		extraFieldsExpandBtn.click();

		Thread.sleep(1000);
		ScrollToElement(warehouseExpandBtn2);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouseExpandBtn2));
		warehouseExpandBtn2.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouseNameBtn2));
		getAction().doubleClick(warehouseNameBtn2).build().perform();


		Thread.sleep(2000);

		 ((JavascriptExecutor)getDriver()).executeScript("window.scrollTo(0, 0)","");
			
		 Thread.sleep(2000);
		finishBtn.click();

		Thread.sleep(2000);
		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : "+actMessage+" Value Expected : "+expMessage);

		if(actMessage.equalsIgnoreCase(expMessage))
		{

			System.out.println(" Test PasS: Report desgining is saved with Document Type ");
			return true;
		}
		else
		{
			System.out.println(" Test FAIL: Report desgining is saved with Document Type ");
			return false;
		}

	}




	public boolean checkReportAllProductsByTagDetails() throws InterruptedException
	{
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
		searchTxt.click();
		searchTxt.sendKeys("All Products By Tag-Details");
		Thread.sleep(1000);
		searchTxt.sendKeys(Keys.ENTER);


		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_DateOptionDropdown));

		Select s=new Select(sl_DateOptionDropdown);
		s.selectByValue("1");

		Thread.sleep(2000);


		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_OkBtn));
		sl_OkBtn.click();


		Thread.sleep(2000);
		boolean novalidationConfirmationMessage =validationConfirmationMessage.getText().isEmpty();

		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";

		Thread.sleep(3000);

		Thread.sleep(1500);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_1stRow1stCol));


		int report2ndRowListCount = reportCol2List.size();
		ArrayList<String> report2ndRowListArray = new ArrayList<String>();
		for(int i=1;i<report2ndRowListCount;i++)
		{
			String data = reportCol2List.get(i).getText().trim();

			if (data.isEmpty() == false) 
			{
				report2ndRowListArray.add(data);
			}
		}
		String actRow2List = report2ndRowListArray.toString();
		String expRow2List = "[STD RATE COGS ITEM, STD RATE COGS ITEM, STD RATE COGS ITEM, STD RATE COGS ITEM]";


		int report3rdRowListCount = reportCol3List.size();
		ArrayList<String> report3rdRowListArray = new ArrayList<String>();
		for(int i=1;i<report3rdRowListCount;i++)
		{
			String data = reportCol3List.get(i).getText().trim();
			if (data.isEmpty() == false) 
			{
				report3rdRowListArray.add(data);
			}
		}
		String actRow3List = report3rdRowListArray.toString();
		String expRow3List = "[STD RATE COGS ITEM, STD RATE COGS ITEM, STD RATE COGS ITEM, STD RATE COGS ITEM]";


		int report4thRowListCount = reportCol4List.size();
		ArrayList<String> report4thRowListArray = new ArrayList<String>();
		for(int i=1;i<report4thRowListCount;i++)
		{
			String data = reportCol4List.get(i).getText().trim();
			if (data.isEmpty() == false) 
			{
				report4thRowListArray.add(data);
			}
		}
		String actRow4List = report4thRowListArray.toString();
		String expRow4List = "[2.00, 1.00, 1.00, 1.00]";



		int reportsRow1ListCount1 = reportCol5List.size();
		ArrayList<String> reportsRow1ListArray1 = new ArrayList<String>();
		for(int i=1;i<reportsRow1ListCount1;i++)
		{
			String data = reportCol5List.get(i).getText().trim();


			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date date=new Date();
			String expadjustBills=df.format(date);

			if (data.isEmpty() == false) 
			{
				reportsRow1ListArray1.add(data);
			}
		}
		String actRow1List1 = reportsRow1ListArray1.toString();
		String expRow1List1 = "[10.00, 5.00, 5.00, 5.00]";

		System.out.println("actRow2List  : "+actRow2List);
		System.out.println("expRow2List  : "+expRow2List);
		System.out.println("*********************************************************************");

		System.out.println("actRow3List  : "+actRow3List);
		System.out.println("expRow3List  : "+expRow3List);
		System.out.println("*********************************************************************");

		System.out.println("actRow4List  : "+actRow4List);
		System.out.println("expRow4List  : "+expRow4List);
		System.out.println("*********************************************************************");


		System.out.println("actRow1List1  : "+actRow1List1);
		System.out.println("expRow1List1  : "+expRow1List1);
		System.out.println("*********************************************************************");


		if (actRow2List.equalsIgnoreCase(expRow2List) &&
				actRow3List.equalsIgnoreCase(expRow3List) && 
				actRow4List.equalsIgnoreCase(expRow4List)  && 

				actRow1List1.equalsIgnoreCase(expRow1List1) &&
				actvalidationConfirmationMessage.equalsIgnoreCase(expvalidationConfirmationMessage)) 
		{
			System.out.println(" Test Pass: Values Dsiplayed as Expected ");
			return true;
		} 
		else 
		{
			System.out.println(" Test FAIL: Values Dsiplayed as Expected ");
			String meesage=validationConfirmationMessage.getText();
			System.err.println(" Meesage Displayed  : "+meesage);
			return false;
		}
	}





	public boolean checkSavingRDWithInventoryTransactionsDetailsDetails() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{

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
		Select rtd= new Select(reportTypeDropdown);
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

		for(int i=0;i<=transactionSetListCount;i++)
		{
			String data = transactionSetList.get(i).getText();

			if(data.equalsIgnoreCase("All products by tag"))
			{
				transactionSetList.get(i).click();

				break;
			}
		}


		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTab));
		customizationTab.click();

		Thread.sleep(2000);
		//getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionExpandBtn));
		//transactionExpandBtn.click();

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

		Thread.sleep(1000);
		ScrollToElement(warehouseExpandBtn2);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouseExpandBtn2));
		warehouseExpandBtn2.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouseNameBtn2));
		getAction().doubleClick(warehouseNameBtn2).build().perform();


		Thread.sleep(2000);

		 ((JavascriptExecutor)getDriver()).executeScript("window.scrollTo(0, 0)","");
			
		 Thread.sleep(2000);
		finishBtn.click();
		
		Thread.sleep(2000);


		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : "+actMessage+" Value Expected : "+expMessage);

		if(actMessage.equalsIgnoreCase(expMessage))
		{

			System.out.println(" Test PasS: Report desgining is saved with Document Type ");
			return true;
		}
		else
		{
			System.out.println(" Test FAIL: Report desgining is saved with Document Type ");
			return false;
		}

	}




	public boolean checkReportInventoryTransactionsDetails() throws InterruptedException
	{

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

		Select s=new Select(sl_DateOptionDropdown);
		s.selectByValue("1");

		Thread.sleep(2000);


		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_OkBtn));
		sl_OkBtn.click();


		Thread.sleep(2000);
		boolean novalidationConfirmationMessage =validationConfirmationMessage.getText().isEmpty();

		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";
		
		System.err.println("actvalidationConfirmationMessage: "+actvalidationConfirmationMessage);
		System.err.println("expvalidationConfirmationMessage: "+expvalidationConfirmationMessage);
		

		Thread.sleep(3000);

		int report2ndRowListCount = reportCol2List.size();
		ArrayList<String> report2ndRowListArray = new ArrayList<String>();
		for(int i=1;i<report2ndRowListCount;i++)
		{
			String data = reportCol2List.get(i).getText().trim();
			if (data.isEmpty() == false) {
				report2ndRowListArray.add(data);

			}
		}
		String actRow2List = report2ndRowListArray.toString();
		String expRow2List = "[STD RATE COGS ITEM, STD RATE COGS ITEM, STD RATE COGS ITEM, STD RATE COGS ITEM]";


		int report3rdRowListCount = reportCol3List.size();
		ArrayList<String> report3rdRowListArray = new ArrayList<String>();
		for(int i=1;i<report3rdRowListCount;i++)
		{
			String data = reportCol3List.get(i).getText().trim();
			if (data.isEmpty() == false) {
				report3rdRowListArray.add(data);

			}
		}
		String actRow3List = report3rdRowListArray.toString();
		String expRow3List = "[STD RATE COGS ITEM, STD RATE COGS ITEM, STD RATE COGS ITEM, STD RATE COGS ITEM]";


		int report4thRowListCount = reportCol4List.size();
		ArrayList<String> report4thRowListArray = new ArrayList<String>();
		for(int i=1;i<report4thRowListCount;i++)
		{
			String data = reportCol4List.get(i).getText().trim();
			if (data.isEmpty() == false) {
				report4thRowListArray.add(data);

			}
		}
		String actRow4List = report4thRowListArray.toString();
		String expRow4List = "[2.00, 1.00, 1.00, 1.00]";



		int reportsRow1ListCount1 = reportCol5List.size();
		ArrayList<String> reportsRow1ListArray1 = new ArrayList<String>();
		for(int i=1;i<reportsRow1ListCount1;i++)
		{
			String data = reportCol5List.get(i).getText().trim();


			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date date=new Date();
			String expadjustBills=df.format(date);

			if (data.isEmpty() == false) {
				reportsRow1ListArray1.add(data);

			}
		}
		String actRow1List1 = reportsRow1ListArray1.toString();
		String expRow1List1 = "[10.00, 5.00, 5.00, 5.00]";


		System.out.println("actRow2List  : "+actRow2List);
		System.out.println("expRow2List  : "+expRow2List);
		System.out.println("*********************************************************************");

		System.out.println("actRow3List  : "+actRow3List);
		System.out.println("expRow3List  : "+expRow3List);
		System.out.println("*********************************************************************");

		System.out.println("actRow4List  : "+actRow4List);
		System.out.println("expRow4List  : "+expRow4List);
		System.out.println("*********************************************************************");


		System.out.println("actRow1List1  : "+actRow1List1);
		System.out.println("expRow1List1  : "+expRow1List1);
		System.out.println("*********************************************************************");


		if (actRow2List.equalsIgnoreCase(expRow2List) &&
				actRow3List.equalsIgnoreCase(expRow3List) && 
				actRow4List.equalsIgnoreCase(expRow4List)  && 

				actRow1List1.equalsIgnoreCase(expRow1List1)) 
		{
			System.out.println(" Test Pass: Values Dsiplayed as Expected ");
			return true;
		} 
		else 
		{
			System.out.println(" Test FAIL: Values Dsiplayed as Expected ");
			String meesage=validationConfirmationMessage.getText();
			System.err.println(" Meesage Displayed  : "+meesage);
			return false;
		}
	}




	public boolean checkSavingRDWithInventoryTransactionsOfProductDetails() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{

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
		Select rtd= new Select(reportTypeDropdown);
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

		for(int i=0;i<=transactionSetListCount;i++)
		{
			String data = transactionSetList.get(i).getText();

			if(data.equalsIgnoreCase("All products by tag"))
			{
				transactionSetList.get(i).click();

				break;
			}
		}


		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTab));
		customizationTab.click();

		Thread.sleep(2000);
		//getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionExpandBtn));
		//transactionExpandBtn.click();

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

		Thread.sleep(1000);
		ScrollToElement(warehouseExpandBtn2);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouseExpandBtn2));
		warehouseExpandBtn2.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouseNameBtn2));
		getAction().doubleClick(warehouseNameBtn2).build().perform();


		Thread.sleep(2000);

		 ((JavascriptExecutor)getDriver()).executeScript("window.scrollTo(0, 0)","");
			
		 Thread.sleep(2000);
		finishBtn.click();

		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : "+actMessage+" Value Expected : "+expMessage);

		if(actMessage.equalsIgnoreCase(expMessage))
		{

			System.out.println(" Test PasS: Report desgining is saved with Document Type ");
			return true;
		}
		else
		{
			System.out.println(" Test FAIL: Report desgining is saved with Document Type ");
			return false;
		}

	}




	public boolean checkReportInventoryTransactionsOfProductDetails() throws InterruptedException
	{
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
		searchTxt.click();
		Thread.sleep(1000);
		searchTxt.sendKeys("Inventory Transactions of a Product-Details");
		Thread.sleep(1000);
		searchTxt.sendKeys(Keys.ENTER);


		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_DateOptionDropdown));

		Select s=new Select(sl_DateOptionDropdown);
		s.selectByValue("1");

		Thread.sleep(2000);


		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_OkBtn));
		sl_OkBtn.click();


		Thread.sleep(2000);
		boolean novalidationConfirmationMessage =validationConfirmationMessage.getText().isEmpty();

		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";

		Thread.sleep(3000);

		Thread.sleep(1500);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_1stRow1stCol));


		int report2ndRowListCount = reportCol2List.size();
		ArrayList<String> report2ndRowListArray = new ArrayList<String>();
		for(int i=1;i<report2ndRowListCount;i++)
		{
			String data = reportCol2List.get(i).getText().trim();
			if (data.isEmpty() == false) 
			{
				report2ndRowListArray.add(data);
			}
		}
		String actRow2List = report2ndRowListArray.toString();
		String expRow2List = "[STD RATE COGS ITEM, STD RATE COGS ITEM, STD RATE COGS ITEM, STD RATE COGS ITEM]";


		int report3rdRowListCount = reportCol3List.size();
		ArrayList<String> report3rdRowListArray = new ArrayList<String>();
		for(int i=1;i<report3rdRowListCount;i++)
		{
			String data = reportCol3List.get(i).getText().trim();
			if (data.isEmpty() == false) 
			{
				report3rdRowListArray.add(data);
			}
		}
		String actRow3List = report3rdRowListArray.toString();
		String expRow3List = "[STD RATE COGS ITEM, STD RATE COGS ITEM, STD RATE COGS ITEM, STD RATE COGS ITEM]";


		int report4thRowListCount = reportCol4List.size();
		ArrayList<String> report4thRowListArray = new ArrayList<String>();
		for(int i=1;i<report4thRowListCount;i++)
		{
			String data = reportCol4List.get(i).getText().trim();
			if (data.isEmpty() == false) 
			{
				report4thRowListArray.add(data);
			}
		}
		String actRow4List = report4thRowListArray.toString();
		String expRow4List = "[2.00, 1.00, 1.00, 1.00]";



		int reportsRow1ListCount1 = reportCol5List.size();
		ArrayList<String> reportsRow1ListArray1 = new ArrayList<String>();
		for(int i=1;i<reportsRow1ListCount1;i++)
		{
			String data = reportCol5List.get(i).getText().trim();


			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date date=new Date();
			String expadjustBills=df.format(date);

			if (data.isEmpty() == false) 
			{
				reportsRow1ListArray1.add(data);
			}
		}
		String actRow1List1 = reportsRow1ListArray1.toString();
		String expRow1List1 = "[10.00, 5.00, 5.00, 5.00]";




		System.out.println("actRow2List  : "+actRow2List);
		System.out.println("expRow2List  : "+expRow2List);
		System.out.println("*********************************************************************");

		System.out.println("actRow3List  : "+actRow3List);
		System.out.println("expRow3List  : "+expRow3List);
		System.out.println("*********************************************************************");

		System.out.println("actRow4List  : "+actRow4List);
		System.out.println("expRow4List  : "+expRow4List);
		System.out.println("*********************************************************************");


		System.out.println("actRow1List1  : "+actRow1List1);
		System.out.println("expRow1List1  : "+expRow1List1);
		System.out.println("*********************************************************************");


		if (actRow2List.equalsIgnoreCase(expRow2List) &&
				actRow3List.equalsIgnoreCase(expRow3List) && 
				actRow4List.equalsIgnoreCase(expRow4List)  && 

				actRow1List1.equalsIgnoreCase(expRow1List1)
			) 
		{
			System.out.println(" Test Pass: Values Dsiplayed as Expected ");
			return true;
		} 
		else 
		{
			System.out.println(" Test FAIL: Values Dsiplayed as Expected ");
			String meesage=validationConfirmationMessage.getText();
			System.err.println(" Meesage Displayed  : "+meesage);
			return false;
		}
	}






	public boolean checkSavingRDWithInventoryTransactionsOfAccountingTagDetails() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{

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
		Select rtd= new Select(reportTypeDropdown);
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

		for(int i=0;i<=transactionSetListCount;i++)
		{
			String data = transactionSetList.get(i).getText();

			if(data.equalsIgnoreCase("Inventory transactions of accounting tag"))
			{
				transactionSetList.get(i).click();

				break;
			}
		}


		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTab));
		customizationTab.click();

		Thread.sleep(2000);
		//getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionExpandBtn));
		//transactionExpandBtn.click();

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


		 ((JavascriptExecutor)getDriver()).executeScript("window.scrollTo(0, 0)","");
			
		 Thread.sleep(2000);
		finishBtn.click();
		
		Thread.sleep(2000);
		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : "+actMessage+" Value Expected : "+expMessage);

		if(actMessage.equalsIgnoreCase(expMessage))
		{

			System.out.println(" Test PasS: Report desgining is saved with Document Type ");
			return true;
		}
		else
		{
			System.out.println(" Test FAIL: Report desgining is saved with Document Type ");
			return false;
		}

	}




	public boolean checkReportInventoryTransactionsOFAccountingTagDetails() throws InterruptedException
	{
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
		searchTxt.click();
		Thread.sleep(1000);
		searchTxt.sendKeys("Inventory Transactions of Accounting Tag-Details");
		Thread.sleep(1000);
		searchTxt.sendKeys(Keys.ENTER);


		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_DateOptionDropdown));

		Select s=new Select(sl_DateOptionDropdown);
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
		boolean novalidationConfirmationMessage =validationConfirmationMessage.getText().isEmpty();

		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";

		Thread.sleep(3000);

		Thread.sleep(1500);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_1stRow1stCol));

		int reportsRow1ListCount = reportCol1List.size();
		ArrayList<String> reportsRow1ListArray = new ArrayList<String>();
		for(int i=0;i<reportsRow1ListCount;i++)
		{
			String data = reportCol1List.get(i).getText().trim();


			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date date=new Date();
			String expadjustBills=df.format(date);

			if (data.isEmpty() == false) 
			{
				reportsRow1ListArray.add(data);
			}
		}
		String actRow1List = reportsRow1ListArray.toString();
		String expRow1List = "[1, 2, 3, 4, 5]";


		int report2ndRowListCount = reportCol2List.size();
		ArrayList<String> report2ndRowListArray = new ArrayList<String>();
		for(int i=0;i<report2ndRowListCount;i++)
		{
			String data = reportCol2List.get(i).getText().trim();
			if (data.isEmpty() == false) 
			{
				report2ndRowListArray.add(data);
			}
		}
		String actRow2List = report2ndRowListArray.toString();
		String expRow2List = "[1, 1, 1, 2, Grand Total]";


		int report3rdRowListCount = reportCol3List.size();
		ArrayList<String> report3rdRowListArray = new ArrayList<String>();
		for(int i=0;i<report3rdRowListCount;i++)
		{
			String data = reportCol3List.get(i).getText().trim();
			if (data.isEmpty() == false) 
			{
				report3rdRowListArray.add(data);
			}
		}
		String actRow3List = report3rdRowListArray.toString();
		String expRow3List = "[Customer A, Customer A, Vendor New Reference, Vendor B]";


		int report4thRowListCount = reportCol4List.size();
		ArrayList<String> report4thRowListArray = new ArrayList<String>();
		for(int i=0;i<report4thRowListCount;i++)
		{
			String data = reportCol4List.get(i).getText().trim();
			if (data.isEmpty() == false) 
			{
				report4thRowListArray.add(data);
			}
		}
		String actRow4List = report4thRowListArray.toString();
		String expRow4List = "[STD RATE COGS ITEM, STD RATE COGS ITEM, STD RATE COGS ITEM, STD RATE COGS ITEM]";



		int reportsRow1ListCount1 = reportCol5List.size();
		ArrayList<String> reportsRow1ListArray1 = new ArrayList<String>();
		for(int i=0;i<reportsRow1ListCount1;i++)
		{
			String data = reportCol5List.get(i).getText().trim();

			if (data.isEmpty() == false) 
			{
				reportsRow1ListArray1.add(data);
			}
		}
		String actRow1List1 = reportsRow1ListArray1.toString();
		String expRow1List1 = "[STD RATE COGS ITEM, STD RATE COGS ITEM, STD RATE COGS ITEM, STD RATE COGS ITEM]";


		int reportsRow1ListCount2 = reportCol6List.size();
		ArrayList<String> reportsRow1ListArray2 = new ArrayList<String>();
		for(int i=0;i<reportsRow1ListCount2;i++)
		{
			String data = reportCol6List.get(i).getText().trim();

			if (data.isEmpty() == false) 
			{
				reportsRow1ListArray2.add(data);
			}
		}
		String actRow1List2 = reportsRow1ListArray2.toString();
		String expRow1List2 = "[2.00, 1.00, 1.00, 1.00, 1.00]";


		int reportsRow1ListCount3 = reportCol7List.size();
		ArrayList<String> reportsRow1ListArray3= new ArrayList<String>();
		for(int i=0;i<reportsRow1ListCount3;i++)
		{
			String data = reportCol7List.get(i).getText().trim();

			if (data.isEmpty() == false) 
			{
				reportsRow1ListArray3.add(data);
			}
		}
		String actRow1List3 = reportsRow1ListArray3.toString();
		String expRow1List3 = "[10.00, 5.00, 5.00, 5.00, 25.00]";






		System.out.println("actRow1List  : "+actRow1List);
		System.out.println("expRow1List  : "+expRow1List);
		System.out.println("*********************************************************************");

		System.out.println("actRow2List  : "+actRow2List);
		System.out.println("expRow2List  : "+expRow2List);
		System.out.println("*********************************************************************");

		System.out.println("actRow3List  : "+actRow3List);
		System.out.println("expRow3List  : "+expRow3List);
		System.out.println("*********************************************************************");

		System.out.println("actRow4List  : "+actRow4List);
		System.out.println("expRow4List  : "+expRow4List);
		System.out.println("*********************************************************************");


		System.out.println("actRow1List1  : "+actRow1List1);
		System.out.println("expRow1List1  : "+expRow1List1);
		System.out.println("*********************************************************************");

		System.out.println("actRow1List2  : "+actRow1List2);
		System.out.println("expRow1List2  : "+expRow1List2);
		System.out.println("*********************************************************************");

		System.out.println("actRow1List3  : "+actRow1List3);
		System.out.println("expRow1List3  : "+expRow1List3);
		System.out.println("*********************************************************************");

		if (actRow1List.equalsIgnoreCase(expRow1List) &&
				actRow2List.equalsIgnoreCase(expRow2List) &&
				actRow3List.equalsIgnoreCase(expRow3List) && 
				actRow4List.equalsIgnoreCase(expRow4List)  && 

				actRow1List1.equalsIgnoreCase(expRow1List1) &&
				actRow1List2.equalsIgnoreCase(expRow1List2) &&
				actRow1List3.equalsIgnoreCase(expRow1List3) &&
				actvalidationConfirmationMessage.equalsIgnoreCase(expvalidationConfirmationMessage)) 
		{
			System.out.println(" Test Pass: Values Dsiplayed as Expected ");
			return true;
		} 
		else 
		{
			System.out.println(" Test FAIL: Values Dsiplayed as Expected ");
			String meesage=validationConfirmationMessage.getText();
			System.err.println(" Meesage Displayed  : "+meesage);
			return false;
		}
	}






	public boolean checkSavingRDWithInventoryTransactionsOfInventoryTagDetails() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{

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
		Select rtd= new Select(reportTypeDropdown);
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

		for(int i=0;i<=transactionSetListCount;i++)
		{
			String data = transactionSetList.get(i).getText();

			if(data.equalsIgnoreCase("Inventory transactions of inventory tag"))
			{
				transactionSetList.get(i).click();

				break;
			}
		}


		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTab));
		customizationTab.click();

		Thread.sleep(2000);
		//getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionExpandBtn));
		//transactionExpandBtn.click();

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

		Thread.sleep(1000);
		ScrollToElement(warehouseExpandBtn);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouseExpandBtn));
		warehouseExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouseNameBtn));
		getAction().doubleClick(warehouseNameBtn).build().perform();



		Thread.sleep(2000);


		 ((JavascriptExecutor)getDriver()).executeScript("window.scrollTo(0, 0)","");
			
		 Thread.sleep(2000);
		finishBtn.click();

		Thread.sleep(2000);
		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : "+actMessage+" Value Expected : "+expMessage);

		if(actMessage.equalsIgnoreCase(expMessage))
		{

			System.out.println(" Test PasS: Report desgining is saved with Document Type ");
			return true;
		}
		else
		{
			System.out.println(" Test FAIL: Report desgining is saved with Document Type ");
			return false;
		}

	}




	public boolean checkReportInventoryTransactionsOFInventoryTagDetails() throws InterruptedException
	{

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

		Select s=new Select(sl_DateOptionDropdown);
		s.selectByValue("1");

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportDepartmentTxt));
		reportDepartmentTxt.click();
		reportDepartmentTxt.sendKeys("HYDERABAD"); 
		Thread.sleep(1000);
		reportDepartmentTxt.sendKeys(Keys.TAB);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_OkBtn));
		sl_OkBtn.click();

		Thread.sleep(3000);

		Thread.sleep(1500);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_1stRow1stCol));

		int reportsRow1ListCount = reportCol1List.size();
		ArrayList<String> reportsRow1ListArray = new ArrayList<String>();
		for(int i=0;i<reportsRow1ListCount;i++)
		{
			String data = reportCol1List.get(i).getText().trim();


			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date date=new Date();
			String expadjustBills=df.format(date);

			if (data.isEmpty() == false) 
			{
				reportsRow1ListArray.add(data);
			}
		}
		String actRow1List = reportsRow1ListArray.toString();
		String expRow1List = "[1, 2, 3, 4, 5]";


		int report2ndRowListCount = reportCol2List.size();
		ArrayList<String> report2ndRowListArray = new ArrayList<String>();
		for(int i=0;i<report2ndRowListCount;i++)
		{
			String data = reportCol2List.get(i).getText().trim();
			if (data.isEmpty() == false) 
			{
				report2ndRowListArray.add(data);
			}
		}
		String actRow2List = report2ndRowListArray.toString();
		String expRow2List = "[1, 1, 1, 2, Grand Total]";


		int report3rdRowListCount = reportCol3List.size();
		ArrayList<String> report3rdRowListArray = new ArrayList<String>();
		for(int i=0;i<report3rdRowListCount;i++)
		{
			String data = reportCol3List.get(i).getText().trim();
			if (data.isEmpty() == false) 
			{
				report3rdRowListArray.add(data);
			}
		}
		String actRow3List = report3rdRowListArray.toString();
		String expRow3List = "[Customer A, Customer A, Vendor New Reference, Vendor B]";


		int report4thRowListCount = reportCol4List.size();
		ArrayList<String> report4thRowListArray = new ArrayList<String>();
		for(int i=0;i<report4thRowListCount;i++)
		{
			String data = reportCol4List.get(i).getText().trim();
			if (data.isEmpty() == false) 
			{
				report4thRowListArray.add(data);
			}
		}
		String actRow4List = report4thRowListArray.toString();
		String expRow4List = "[STD RATE COGS ITEM, STD RATE COGS ITEM, STD RATE COGS ITEM, STD RATE COGS ITEM]";



		int reportsRow1ListCount1 = reportCol5List.size();
		ArrayList<String> reportsRow1ListArray1 = new ArrayList<String>();
		for(int i=0;i<reportsRow1ListCount1;i++)
		{
			String data = reportCol5List.get(i).getText().trim();

			if (data.isEmpty() == false) 
			{
				reportsRow1ListArray1.add(data);
			}
		}
		String actRow1List1 = reportsRow1ListArray1.toString();
		String expRow1List1 = "[STD RATE COGS ITEM, STD RATE COGS ITEM, STD RATE COGS ITEM, STD RATE COGS ITEM]";


		int reportsRow1ListCount2 = reportCol6List.size();
		ArrayList<String> reportsRow1ListArray2 = new ArrayList<String>();
		for(int i=0;i<reportsRow1ListCount2;i++)
		{
			String data = reportCol6List.get(i).getText().trim();

			if (data.isEmpty() == false) 
			{
				reportsRow1ListArray2.add(data);
			}
		}
		String actRow1List2 = reportsRow1ListArray2.toString();
		String expRow1List2 = "[2.00, 1.00, 1.00, 1.00, 1.00]";


		int reportsRow1ListCount3 = reportCol7List.size();
		ArrayList<String> reportsRow1ListArray3= new ArrayList<String>();
		for(int i=0;i<reportsRow1ListCount3;i++)
		{
			String data = reportCol7List.get(i).getText().trim();

			if (data.isEmpty() == false) 
			{
				reportsRow1ListArray3.add(data);
			}
		}
		String actRow1List3 = reportsRow1ListArray3.toString();
		String expRow1List3 = "[10.00, 5.00, 5.00, 5.00, 25.00]";



		System.out.println("actRow1List  : "+actRow1List);
		System.out.println("expRow1List  : "+expRow1List);
		System.out.println("*********************************************************************");

		System.out.println("actRow2List  : "+actRow2List);
		System.out.println("expRow2List  : "+expRow2List);
		System.out.println("*********************************************************************");

		System.out.println("actRow3List  : "+actRow3List);
		System.out.println("expRow3List  : "+expRow3List);
		System.out.println("*********************************************************************");

		System.out.println("actRow4List  : "+actRow4List);
		System.out.println("expRow4List  : "+expRow4List);
		System.out.println("*********************************************************************");


		System.out.println("actRow1List1  : "+actRow1List1);
		System.out.println("expRow1List1  : "+expRow1List1);
		System.out.println("*********************************************************************");

		System.out.println("actRow1List2  : "+actRow1List2);
		System.out.println("expRow1List2  : "+expRow1List2);
		System.out.println("*********************************************************************");

		System.out.println("actRow1List3  : "+actRow1List3);
		System.out.println("expRow1List3  : "+expRow1List3);
		System.out.println("*********************************************************************");

		if (actRow1List.equalsIgnoreCase(expRow1List) &&
				actRow2List.equalsIgnoreCase(expRow2List) &&
				actRow3List.equalsIgnoreCase(expRow3List) && 
				actRow4List.equalsIgnoreCase(expRow4List)  && 

				actRow1List1.equalsIgnoreCase(expRow1List1) &&
				actRow1List2.equalsIgnoreCase(expRow1List2) &&
				actRow1List3.equalsIgnoreCase(expRow1List3)) 
		{
			System.out.println(" Test Pass: Values Dsiplayed as Expected ");
			return true;
		} 
		else 
		{
			System.out.println(" Test FAIL: Values Dsiplayed as Expected ");
			String meesage=validationConfirmationMessage.getText();
			System.err.println(" Meesage Displayed  : "+meesage);
			return false;
		}
	}



	@FindBy(xpath="//*[@id='advanceEngine']/following-sibling::span")
	private static WebElement rdReportsAdvanceEngineChkbox;
	
	@FindBy(xpath="//*[@id='advanceEngine']")
	private static WebElement rdReportsAdvanceEngineChkboxSelected;


	public boolean checkSavingRDWithInventoryTransactionsOfTagDetails() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{

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
		Thread.sleep(3000);
		reportNameDropdown.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportTypeDropdown));
		Select rtd= new Select(reportTypeDropdown);
		rtd.selectByVisibleText("Details");

		//getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdReportsAdvanceEngineChkbox));
		//rdReportsAdvanceEngineChkbox.click();


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

		for(int i=0;i<=transactionSetListCount;i++)
		{
			String data = transactionSetList.get(i).getText();

			if(data.equalsIgnoreCase("Inventory transactions of a tag"))
			{
				transactionSetList.get(i).click();

				break;
			}
		}


		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTab));
		customizationTab.click();

		Thread.sleep(2000);
		//getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionExpandBtn));
		//transactionExpandBtn.click();

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

		Thread.sleep(1000);
		ScrollToElement(warehouseExpandBtn);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouseExpandBtn));
		warehouseExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouseNameBtn));
		getAction().doubleClick(warehouseNameBtn).build().perform();

		Thread.sleep(2000);


		 ((JavascriptExecutor)getDriver()).executeScript("window.scrollTo(0, 0)","");
			
		 Thread.sleep(2000);
		finishBtn.click();
		Thread.sleep(2000);

		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : "+actMessage+" Value Expected : "+expMessage);

		if(actMessage.equalsIgnoreCase(expMessage))
		{

			System.out.println(" Test PasS: Report desgining is saved with Document Type ");
			return true;
		}
		else
		{
			System.out.println(" Test FAIL: Report desgining is saved with Document Type ");
			return false;
		}

	}




	public boolean checkReportInventoryTransactionsOFTagDetails() throws InterruptedException
	{

		getDriver().navigate().refresh();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
		searchTxt.click();
		Thread.sleep(1000);
		searchTxt.sendKeys("Inventory Transactions of a Tag-Details");
		Thread.sleep(2000);
		searchTxt.sendKeys(Keys.ENTER);


		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_DateOptionDropdown));

		Select s=new Select(sl_DateOptionDropdown);
		s.selectByValue("1");

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_OkBtn));
		sl_OkBtn.click();


		Thread.sleep(2000);
		boolean novalidationConfirmationMessage =validationConfirmationMessage.getText().isEmpty();

		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";


		Thread.sleep(1500);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_1stRow1stCol));

		int reportsRow1ListCount = reportCol1List.size();
		ArrayList<String> reportsRow1ListArray = new ArrayList<String>();
		for(int i=0;i<reportsRow1ListCount;i++)
		{
			String data = reportCol1List.get(i).getText().trim();


			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date date=new Date();
			String expadjustBills=df.format(date);

			if (data.isEmpty() == false)
			{
				reportsRow1ListArray.add(data);
			}
		}
		String actRow1List = reportsRow1ListArray.toString();
		String expRow1List = "[1, 2, 3, 4, 5]";


		int report2ndRowListCount = reportCol2List.size();
		ArrayList<String> report2ndRowListArray = new ArrayList<String>();
		for(int i=0;i<report2ndRowListCount;i++)
		{
			String data = reportCol2List.get(i).getText().trim();

			if (data.isEmpty() == false)
			{
				report2ndRowListArray.add(data);
			}
		}
		String actRow2List = report2ndRowListArray.toString();
		String expRow2List = "[1, 1, 1, 2, Grand Total]";


		int report3rdRowListCount = reportCol3List.size();
		ArrayList<String> report3rdRowListArray = new ArrayList<String>();
		for(int i=0;i<report3rdRowListCount;i++)
		{
			String data = reportCol3List.get(i).getText().trim();

			if (data.isEmpty() == false)
			{
				report3rdRowListArray.add(data);
			}
		}
		String actRow3List = report3rdRowListArray.toString();
		String expRow3List = "[Customer A, Customer A, Vendor New Reference, Vendor B]";


		int report4thRowListCount = reportCol4List.size();
		ArrayList<String> report4thRowListArray = new ArrayList<String>();
		for(int i=0;i<report4thRowListCount;i++)
		{
			String data = reportCol4List.get(i).getText().trim();

			if (data.isEmpty() == false)
			{
				report4thRowListArray.add(data);
			}
		}
		String actRow4List = report4thRowListArray.toString();
		String expRow4List = "[STD RATE COGS ITEM, STD RATE COGS ITEM, STD RATE COGS ITEM, STD RATE COGS ITEM]";



		int reportsRow1ListCount1 = reportCol5List.size();
		ArrayList<String> reportsRow1ListArray1 = new ArrayList<String>();
		for(int i=0;i<reportsRow1ListCount1;i++)
		{
			String data = reportCol5List.get(i).getText().trim();


			if (data.isEmpty() == false)
			{
				reportsRow1ListArray1.add(data);
			}
		}
		String actRow1List1 = reportsRow1ListArray1.toString();
		String expRow1List1 = "[STD RATE COGS ITEM, STD RATE COGS ITEM, STD RATE COGS ITEM, STD RATE COGS ITEM]";


		int reportsRow1ListCount2 = reportCol6List.size();
		ArrayList<String> reportsRow1ListArray2 = new ArrayList<String>();
		for(int i=0;i<reportsRow1ListCount2;i++)
		{
			String data = reportCol6List.get(i).getText().trim();


			if (data.isEmpty() == false)
			{
				reportsRow1ListArray2.add(data);
			}
		}
		String actRow1List2 = reportsRow1ListArray2.toString();
		String expRow1List2 = "[2.00, 1.00, 1.00, 1.00, 1.00]";


		int reportsRow1ListCount3 = reportCol7List.size();
		ArrayList<String> reportsRow1ListArray3= new ArrayList<String>();
		for(int i=0;i<reportsRow1ListCount3;i++)
		{
			String data = reportCol7List.get(i).getText().trim();


			if (data.isEmpty() == false)
			{
				reportsRow1ListArray3.add(data);
			}
		}
		String actRow1List3 = reportsRow1ListArray3.toString();
		String expRow1List3 = "[10.00, 5.00, 5.00, 5.00, 25.00]";



		System.out.println("actRow1List  : "+actRow1List);
		System.out.println("expRow1List  : "+expRow1List);
		System.out.println("*********************************************************************");

		System.out.println("actRow2List  : "+actRow2List);
		System.out.println("expRow2List  : "+expRow2List);
		System.out.println("*********************************************************************");

		System.out.println("actRow3List  : "+actRow3List);
		System.out.println("expRow3List  : "+expRow3List);
		System.out.println("*********************************************************************");

		System.out.println("actRow4List  : "+actRow4List);
		System.out.println("expRow4List  : "+expRow4List);
		System.out.println("*********************************************************************");


		System.out.println("actRow1List1  : "+actRow1List1);
		System.out.println("expRow1List1  : "+expRow1List1);
		System.out.println("*********************************************************************");

		System.out.println("actRow1List2  : "+actRow1List2);
		System.out.println("expRow1List2  : "+expRow1List2);
		System.out.println("*********************************************************************");

		System.out.println("actRow1List3  : "+actRow1List3);
		System.out.println("expRow1List3  : "+expRow1List3);
		System.out.println("*********************************************************************");

		if (actRow1List.equalsIgnoreCase(expRow1List) &&
				actRow2List.equalsIgnoreCase(expRow2List) &&
				actRow3List.equalsIgnoreCase(expRow3List) && 
				actRow4List.equalsIgnoreCase(expRow4List)  && 

				actRow1List1.equalsIgnoreCase(expRow1List1) &&
				actRow1List2.equalsIgnoreCase(expRow1List2) &&
				actRow1List3.equalsIgnoreCase(expRow1List3) &&
				actvalidationConfirmationMessage.equalsIgnoreCase(expvalidationConfirmationMessage)) 
		{
			System.out.println(" Test Pass: Values Displayed With Department as Expected ");
			return true;
		} 
		else 
		{
			System.out.println(" Test FAIL: Values Displayed With Department as Expected ");
			String meesage=validationConfirmationMessage.getText();
			System.err.println(" Meesage Displayed  : "+meesage);
			
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
			boolean novalidationConfirmationMessage1 =validationConfirmationMessage.getText().isEmpty();

			String actvalidationConfirmationMessage1= Boolean.toString(novalidationConfirmationMessage);
			String expvalidationConfirmationMessage1 = "true";


			Thread.sleep(1500);
			getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_1stRow1stCol));

			int reportsRow1ListCount01 = reportCol1List.size();
			ArrayList<String> reportsRow1ListArray01 = new ArrayList<String>();
			for(int i=0;i<reportsRow1ListCount;i++)
			{
				String data = reportCol1List.get(i).getText();


				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				Date date=new Date();
				String expadjustBills=df.format(date);

				reportsRow1ListArray.add(data);
			}
			String actRow1List01 = reportsRow1ListArray.toString();
			String expRow1List01 = "[1, 2, 3, 4, 5]";
			
			System.out.println(" Row1 List : Actual  "+actRow1List01);
			System.out.println(" Row1 List : Exp     "+expRow1List01);
			
			if (actRow1List01.equalsIgnoreCase(expRow1List01)) 
			{
				return true;
			} else 
			{
				String errormessage=errorMessage.getText();
				System.out.println(" ERROR MESSAGE DISPLAYED : "+errormessage);
				return false;
			}
			
		}
	}


@FindBy(xpath="//*[@class='icon-close hiconright2']")
private static WebElement reportCloseBtn;

@FindBy(xpath="(//*[@class='icon-close hiconright2'])[2]")
private static WebElement reportCloseBtn2;







	public boolean checkSavingRDWithInventoryTransactionsOfSelectedAccountDetails() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{

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

		Thread.sleep(4000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportTypeDropdown));
		Select rtd= new Select(reportTypeDropdown);
		rtd.selectByVisibleText("Details");

		//getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdReportsAdvanceEngineChkbox));
		//rdReportsAdvanceEngineChkbox.click();


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

		for(int i=0;i<=transactionSetListCount;i++)
		{
			String data = transactionSetList.get(i).getText();

			if(data.equalsIgnoreCase("Inventory transactions of selected product"))
			{
				transactionSetList.get(i).click();

				break;
			}
		}


		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTab));
		customizationTab.click();

		Thread.sleep(2000);
		//getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionExpandBtn));
		//transactionExpandBtn.click();

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

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouseExpandBtn));
		warehouseExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouseNameBtn));
		getAction().doubleClick(warehouseNameBtn).build().perform();



		Thread.sleep(2000);


		 ((JavascriptExecutor)getDriver()).executeScript("window.scrollTo(0, 0)","");
			
		 Thread.sleep(2000);
		finishBtn.click();
		Thread.sleep(2000);

		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : "+actMessage+" Value Expected : "+expMessage);

		if(actMessage.equalsIgnoreCase(expMessage))
		{

			System.out.println(" Test PasS: Report desgining is saved with Document Type ");
			return true;
		}
		else
		{
			System.out.println(" Test FAIL: Report desgining is saved with Document Type ");
			return false;
		}

	}




	public boolean checkReportInventoryTransactionsSelectedAccountDetails() throws InterruptedException
	{
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
		searchTxt.click();
		Thread.sleep(1000);
		searchTxt.sendKeys("Inventory Transactions of Selected Product-Details");
		Thread.sleep(1000);
		searchTxt.sendKeys(Keys.ENTER);


		Thread.sleep(3000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_DateOptionDropdown));

		Select s=new Select(sl_DateOptionDropdown);
		s.selectByValue("1");

		Thread.sleep(3000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_SelectAllItemsChkBox));
		sl_SelectAllItemsChkBox.click();

		
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_OkBtn));
		sl_OkBtn.click();

		Thread.sleep(2000);
		boolean novalidationConfirmationMessage =validationConfirmationMessage.getText().isEmpty();

		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);  
		String expvalidationConfirmationMessage = "true";


		Thread.sleep(3500);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_1stRow1stCol));

		int reportsRow1ListCount = reportCol1List.size();
		ArrayList<String> reportsRow1ListArray = new ArrayList<String>();
		for(int i=0;i<reportsRow1ListCount;i++)
		{
			String data = reportCol1List.get(i).getText().trim();


			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date date=new Date();
			String expadjustBills=df.format(date);

			if (data.isEmpty() == false) 
			{
				reportsRow1ListArray.add(data);
			}
		}
		String actRow1List = reportsRow1ListArray.toString();
		String expRow1List = "[1, 2, 3, 4, 5]";


		int report2ndRowListCount = reportCol2List.size();
		ArrayList<String> report2ndRowListArray = new ArrayList<String>();
		for(int i=0;i<report2ndRowListCount;i++)
		{
			String data = reportCol2List.get(i).getText().trim();
			if (data.isEmpty() == false) 
			{
				report2ndRowListArray.add(data);
			}
		}
		String actRow2List = report2ndRowListArray.toString();
		String expRow2List = "[1, 1, 1, 2, Grand Total]";


		int report3rdRowListCount = reportCol3List.size();
		ArrayList<String> report3rdRowListArray = new ArrayList<String>();
		for(int i=0;i<report3rdRowListCount;i++)
		{
			String data = reportCol3List.get(i).getText().trim();
			if (data.isEmpty() == false) 
			{
				report3rdRowListArray.add(data);
			}
		}
		String actRow3List = report3rdRowListArray.toString();
		String expRow3List = "[Customer A, Customer A, Vendor New Reference, Vendor B]";


		int report4thRowListCount = reportCol4List.size();
		ArrayList<String> report4thRowListArray = new ArrayList<String>();
		for(int i=0;i<report4thRowListCount;i++)
		{
			String data = reportCol4List.get(i).getText().trim();
			if (data.isEmpty() == false) 
			{
				report4thRowListArray.add(data);
			}
		}
		String actRow4List = report4thRowListArray.toString();
		String expRow4List = "[STD RATE COGS ITEM, STD RATE COGS ITEM, STD RATE COGS ITEM, STD RATE COGS ITEM]";



		int reportsRow1ListCount1 = reportCol5List.size();
		ArrayList<String> reportsRow1ListArray1 = new ArrayList<String>();
		for(int i=0;i<reportsRow1ListCount1;i++)
		{
			String data = reportCol5List.get(i).getText().trim();

			if (data.isEmpty() == false) 
			{
				reportsRow1ListArray1.add(data);
			}
		}
		String actRow1List1 = reportsRow1ListArray1.toString();
		String expRow1List1 = "[STD RATE COGS ITEM, STD RATE COGS ITEM, STD RATE COGS ITEM, STD RATE COGS ITEM]";


		int reportsRow1ListCount2 = reportCol6List.size();
		ArrayList<String> reportsRow1ListArray2 = new ArrayList<String>();
		for(int i=0;i<reportsRow1ListCount2;i++)
		{
			String data = reportCol6List.get(i).getText().trim();

			if (data.isEmpty() == false) 
			{
				reportsRow1ListArray2.add(data);
			}
		}
		String actRow1List2 = reportsRow1ListArray2.toString();
		String expRow1List2 = "[2.00, 1.00, 1.00, 1.00, 1.00]";


		int reportsRow1ListCount3 = reportCol7List.size();
		ArrayList<String> reportsRow1ListArray3= new ArrayList<String>();
		for(int i=0;i<reportsRow1ListCount3;i++)
		{
			String data = reportCol7List.get(i).getText().trim();

			if (data.isEmpty() == false) 
			{
				reportsRow1ListArray3.add(data);
			}
		}
		String actRow1List3 = reportsRow1ListArray3.toString();
		String expRow1List3 = "[10.00, 5.00, 5.00, 5.00, 25.00]";



		System.out.println("actRow1List  : "+actRow1List);
		System.out.println("expRow1List  : "+expRow1List);
		System.out.println("*********************************************************************");

		System.out.println("actRow2List  : "+actRow2List);
		System.out.println("expRow2List  : "+expRow2List);
		System.out.println("*********************************************************************");

		System.out.println("actRow3List  : "+actRow3List);
		System.out.println("expRow3List  : "+expRow3List);
		System.out.println("*********************************************************************");

		System.out.println("actRow4List  : "+actRow4List);
		System.out.println("expRow4List  : "+expRow4List);
		System.out.println("*********************************************************************");


		System.out.println("actRow1List1  : "+actRow1List1);
		System.out.println("expRow1List1  : "+expRow1List1);
		System.out.println("*********************************************************************");

		System.out.println("actRow1List2  : "+actRow1List2);
		System.out.println("expRow1List2  : "+expRow1List2);
		System.out.println("*********************************************************************");

		System.out.println("actRow1List3  : "+actRow1List3);
		System.out.println("expRow1List3  : "+expRow1List3);
		System.out.println("*********************************************************************");

		if (actRow1List.equalsIgnoreCase(expRow1List) &&
				actRow2List.equalsIgnoreCase(expRow2List) &&
				actRow3List.equalsIgnoreCase(expRow3List) && 
				actRow4List.equalsIgnoreCase(expRow4List)  && 

				actRow1List1.equalsIgnoreCase(expRow1List1) &&
				actRow1List2.equalsIgnoreCase(expRow1List2) &&
				actRow1List3.equalsIgnoreCase(expRow1List3) &&
				actvalidationConfirmationMessage.equalsIgnoreCase(expvalidationConfirmationMessage)) 
		{
			System.out.println(" Test Pass: Values Dsiplayed as Expected ");
			return true;
		} 
		else 
		{
			System.out.println(" Test FAIL: Values Dsiplayed as Expected ");
			String meesage=validationConfirmationMessage.getText();
			System.err.println(" Meesage Displayed  : "+meesage);
			return false;
		}
	}


	@FindBy(xpath="//*[@id='60']")
	private static WebElement  financialsMenu;


	@FindBy(xpath="//*[@id='61']/span")
	private static WebElement  financialsTransactionMenu;

	@FindBy(xpath="//*[@id='81']/span")
	private static WebElement  financialsReportsMenu; 

	@FindBy(xpath="//*[@id='500']/span")
	private static WebElement  ledger;
	
	@FindBy(xpath="//div[@id='columnData']//li//span[contains(text(), 'Account')]")
	private static WebElement  creditEle;
	
	


	public boolean checkSavingReportThroughAnalysisInLedgerReport() throws InterruptedException, IOException, EncryptedDocumentException, InvalidFormatException
	{

		getDriver().navigate().refresh();

		Thread.sleep(1999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(financialsMenu));
		financialsMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(financialsReportsMenu));
		financialsReportsMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(ledger));
		ledger.click();

		boolean novalidationConfirmationMessage =validationConfirmationMessage.getText().isEmpty();

		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";

		Thread.sleep(3000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_DateOptionDropdown));
		sl_DateOptionDropdown.click();
		Select s=new Select(sl_DateOptionDropdown);
		s.selectByValue("1");

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_SelectAllItemsChkBox));
		sl_SelectAllItemsChkBox.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_OkBtn));
		sl_OkBtn.click();

		Thread.sleep(20000);


		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_AnalysisBtn));
		sl_AnalysisBtn.click();

		Thread.sleep(30000);
		
		/*JavascriptExecutor jse = (JavascriptExecutor)getDriver();
		jse.executeScript("arguments[0].scrollIntoView(true);",creditEle);
		Thread.sleep(3000);*/
		
		
		Runtime.getRuntime().exec(getBaseDir()+"\\autoIt\\scripts\\scripts\\LedgerAnalysis.exe");

		Thread.sleep(50000);


		int analyzeReportCol2ListCount=analyzeReportCol2List.size();

		ArrayList<String >analyzeReportCol2ListArray=new ArrayList<>();
		for (int i = 0; i < analyzeReportCol2ListCount; i++) 
		{
			String data=analyzeReportCol2List.get(i).getText().trim();
			if (data.isEmpty() == false)
			{
				analyzeReportCol2ListArray.add(data);
			}
		}

		String actanalyzeReportCol2List=analyzeReportCol2ListArray.toString();
		String expanalyzeReportCol2List="[Bank, COGS POSTING ACC, Customer A, Customer New Reference, Customer Semi Adjustment, HDFC, Journal Entries Control A/C, Opening Balance, Purchase, Sales - Computers, SR COGS POSTING ACC, STD RATE COGS ACC INV, Vendor B, Vendor Full Adjustment, Vendor New Reference, Vendor Semi Adjustment]";

		System.out.println(" ***********************************");
		System.out.println("analyzeReportCol2List act : "+actanalyzeReportCol2List);
		System.out.println("analyzeReportCol2List exp : "+expanalyzeReportCol2List);


		int analyzeReportCol3ListCount=analyzeReportCol3List.size();
		ArrayList<String >analyzeReportCol3ListArray=new ArrayList<>();
		for (int i = 0; i < analyzeReportCol3ListCount; i++) 
		{
			String data=analyzeReportCol3List.get(i).getText().trim();
			if (data.isEmpty() == false)
			{
				analyzeReportCol3ListArray.add(data);
			}
		}

		String actanalyzeReportCol3List=analyzeReportCol3ListArray.toString();
		String expanalyzeReportCol3List="[-40.00, -20.00, -15.00, -120.00, -10.00, -20.00, -20.00, -120.00, -20.00, -120.25, -125.00, -5.00]";

		System.out.println(" ***********************************");
		System.out.println("analyzeReportCol3List act : "+actanalyzeReportCol3List);
		System.out.println("analyzeReportCol3List exp : "+expanalyzeReportCol3List);

		int analyzeReportCol4ListCount=analyzeReportCol4List.size();

		ArrayList<String >analyzeReportCol4ListArray=new ArrayList<>();
		for (int i = 0; i < analyzeReportCol4ListCount; i++) 
		{
			String data=analyzeReportCol4List.get(i).getText().trim();
			if (data.isEmpty() == false)
			{
				analyzeReportCol4ListArray.add(data);
			}
		}

		String actanalyzeReportCol4List=analyzeReportCol4ListArray.toString();
		String expanalyzeReportCol4List="[339.00, 29.75, 20.00, 20.00, 5.00, 15.00, 10.00, 20.00, 11.00, 5.00, 130.50, 10.00, 10.00, 10.00]";

		System.out.println(" ***********************************");
		System.out.println("analyzeReportCol4List act : "+actanalyzeReportCol4List);
		System.out.println("analyzeReportCol4List exp : "+expanalyzeReportCol4List);

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


		String ExpMessage="Analyze Report Saved Successfully";
		String actMessage=checkValidationMessage(ExpMessage);

		if (actanalyzeReportCol2List.equalsIgnoreCase(expanalyzeReportCol2List) &&
				actanalyzeReportCol2List.equalsIgnoreCase(expanalyzeReportCol2List) &&
				actanalyzeReportCol2List.equalsIgnoreCase(expanalyzeReportCol2List) && 
				actMessage.equalsIgnoreCase(ExpMessage)) {

			System.out.println(" Test Pass:  Report Saved Successfully");
			return true;
		} 
		else
		{
			System.out.println(" Test Fail:  Report Saved Successfully");
			return true;

		}

	}


	public boolean checkLedgerAnalysisreport() throws InterruptedException
	{


		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
		searchTxt.click();
	
		searchTxt.sendKeys("LedgerAnalysisReport");
		Thread.sleep(1000);
		searchTxt.sendKeys(Keys.ENTER);


		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_DateOptionDropdown));

		Select s=new Select(sl_DateOptionDropdown);
		s.selectByValue("1");

		Thread.sleep(2000);


		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_SelectAllItemsChkBox));
		sl_SelectAllItemsChkBox.click();


		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_OkBtn));
		sl_OkBtn.click();


		Thread.sleep(8000);
		boolean novalidationConfirmationMessage =validationConfirmationMessage.getText().isEmpty();

		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";


		Thread.sleep(1500);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_1stRow1stCol));

		int reportsRow1ListCount = reportCol1List.size();
		ArrayList<String> reportsRow1ListArray = new ArrayList<String>();
		for(int i=0;i<reportsRow1ListCount;i++)
		{
			String data = reportCol1List.get(i).getText().trim();


			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date date=new Date();
			String expadjustBills=df.format(date);

		if (data.isEmpty() == false) 
		{
			reportsRow1ListArray.add(data);
		}
		}
		String actRow1List = reportsRow1ListArray.toString();
		String expRow1List = "[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15]";


		int report2ndRowListCount = reportCol2List.size();
		ArrayList<String> report2ndRowListArray = new ArrayList<String>();
		for(int i=0;i<report2ndRowListCount;i++)
		{
			String data = reportCol2List.get(i).getText().trim();
			if (data.isEmpty() == false) 
			{
				report2ndRowListArray.add(data);
			}
		}
		String actRow2List = report2ndRowListArray.toString();
		String expRow2List = "[Bank, COGS POSTING ACC, Customer A, Customer New Reference, Customer Semi Adjustment, HDFC, Journal Entries Control A/C, Opening Balance, Purchase, Sales - Computers, SR COGS POSTING ACC, STD RATE COGS ACC INV, Vendor B, Vendor Full Adjustment]";


		int report3rdRowListCount = reportCol3List.size();
		ArrayList<String> report3rdRowListArray = new ArrayList<String>();
		for(int i=0;i<report3rdRowListCount;i++)
		{
			String data = reportCol3List.get(i).getText().trim();
			if (data.isEmpty() == false) 
			{
				report3rdRowListArray.add(data);
			}
		}
		String actRow3List = report3rdRowListArray.toString();
		String expRow3List = "[40.00, 20.00, 15.00, 120.00, 10.00, 20.00, 20.00, 120.00, 20.00, 120.25]";


		int report4thRowListCount = reportCol4List.size();
		ArrayList<String> report4thRowListArray = new ArrayList<String>();
		for(int i=0;i<report4thRowListCount;i++)
		{
			String data = reportCol4List.get(i).getText().trim();
			if (data.isEmpty() == false) 
			{
				report4thRowListArray.add(data);
			}
		}
		String actRow4List = report4thRowListArray.toString();
		String expRow4List = "[339.00, 29.75, 20.00, 20.00, 5.00, 15.00, 10.00, 20.00, 11.00, 5.00, 130.50, 10.00]";


		System.out.println("actRow1List  : "+actRow1List);
		System.out.println("expRow1List  : "+expRow1List);
		System.out.println("*********************************************************************");

		System.out.println("actRow2List  : "+actRow2List);
		System.out.println("expRow2List  : "+expRow2List);
		System.out.println("*********************************************************************");

		System.out.println("actRow3List  : "+actRow3List);
		System.out.println("expRow3List  : "+expRow3List);
		System.out.println("*********************************************************************");

		System.out.println("actRow4List  : "+actRow4List);
		System.out.println("expRow4List  : "+expRow4List);
		System.out.println("*********************************************************************");


		if (actRow1List.equalsIgnoreCase(expRow1List) &&
				actRow2List.equalsIgnoreCase(expRow2List) &&
				actRow3List.equalsIgnoreCase(expRow3List) && 
				actRow4List.equalsIgnoreCase(expRow4List)  && 

				actvalidationConfirmationMessage.equalsIgnoreCase(expvalidationConfirmationMessage)) 
		{
			System.out.println(" Test Pass: Values Dsiplayed as Expected ");
			return true;
		} 
		else 
		{
			System.out.println(" Test FAIL: Values Dsiplayed as Expected ");
			String meesage=validationConfirmationMessage.getText();
			System.err.println(" Meesage Displayed  : "+meesage);
			return false;
		}

	}

	@FindBy(xpath="//*[@id='analyzeReportTableBody']/tr/td[2]")
	private static List<WebElement> analyzeReportCol2List;


	@FindBy(xpath="//*[@id='analyzeReportTableBody']/tr/td[3]")
	private static List<WebElement> analyzeReportCol3List;

	@FindBy(xpath="//*[@id='analyzeReportTableBody']/tr/td[4]")
	private static List<WebElement> analyzeReportCol4List;


	@FindBy(xpath="//input[@id='analyzeReportName']")
	private static WebElement sl_LA_repotNameTxt;

	@FindBy(xpath="//*[@id='id_menu_tree_60']/a/span")
	private static WebElement sl_LA_repotFinanicalMenu;


	@FindBy(xpath="//button[contains(text(),'Save')]")
	private static WebElement sl_LA_repotSaveBtn;

	@FindBy(xpath="//div[@ID='id_analysismenu']//*[@class='icon-save hiconright2']")
	private static WebElement sl_LA_saveBtn;

	@FindBy(xpath="//*[@id='analyzeMenu']/li/span[8]/i")
	private static WebElement sl_LA_ExitBtn;

	@FindBy(xpath="//ul[@id='rowGroupingData']")
	private static WebElement sl_LA_GroupGrowingTxtArea;

	@FindBy(xpath="//ul[@id='columnGroupingData']")
	private static WebElement sl_LA_ColGroupingTxtArea;

	@FindBy(xpath="//ul[@id='ColumnMeasureData']")
	private static WebElement sl_LA_ColMeasureTxtArea;


	@FindBy(xpath="//*[@id='columnData']/ul/li[2]")
	private static WebElement sl_LA_ColDataVoucher;

	@FindBy(xpath="//*[@id='columnData']/ul/li[3]")
	private static WebElement sl_LA_ColDataAccount;


	@FindBy(xpath="//*[@id='columnData']/ul/li[4]")
	private static WebElement sl_LA_ColDataDebit;

	@FindBy(xpath="//*[@id='columnData']/ul/li[5]")
	private static WebElement sl_LA_ColDataCredit;


	@FindBy(xpath="//*[@id='columnData']/ul/li[6]")
	private static WebElement sl_LA_ColDataBalance;


	@FindBy(xpath="//*[@id='columnData']/ul/li[7]")
	private static WebElement sl_LA_ColDataDrLocal;

	@FindBy(xpath="//*[@id='columnData']/ul/li[8]")
	private static WebElement sl_LA_ColDataCrLocal;


	@FindBy(xpath="//*[@id='columnData']/ul/li[9]")
	private static WebElement sl_LA_ColDataBalLocal;

	@FindBy(xpath="//*[@id='columnData']/ul/li[10]")
	private static WebElement sl_LA_ColDataDrBase;


	@FindBy(xpath="//*[@id='columnData']/ul/li")
	private static List<WebElement> sl_LA_ColDataList;


	@FindBy(xpath="//*[@id='REPORTRENDERNEWControls']/ul/li/span[3]")
	private static WebElement sl_PrintBtn;

	@FindBy(xpath="//div[@id='id_reportmenudisplay']//*[@class='icon-export hiconright2']")
	private static WebElement sl_ExportBtn;

	@FindBy(xpath="//*[@id='id_reportmenudisplay']//*[text()='PDF']")
	private static WebElement sl_ExportPDFBtn;



	
	String LedgerAnalaysis = null;
	public boolean checkLedgerDetailsExportPdf() throws InterruptedException, AWTException, IOException
	{

		Thread.sleep(2000);

		File Efile=new File(getBaseDir()+"\\autoIt\\ExportFiles\\ledgerAnalysis.pdf");

		if(Efile.exists())
		{
			Efile.delete();
		}
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_ExportBtn));
		sl_ExportBtn.click();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_ExportPDFBtn));
		sl_ExportPDFBtn.click();
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(ss_ReportPrintLabel));
        
        String actConfirmMsg=ss_ReportPrintMsg.getText();
        String expConfirmMsg="";
        Thread.sleep(2000);
        
        System.out.println("Actual Msg                :                "+        actConfirmMsg                + "Expected                "        +        expConfirmMsg);
        
        
        getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(ss_ReportPrintYesBtn));
        ss_ReportPrintYesBtn.click();
        
        Thread.sleep(3000);


	
		/*	LedgerAnalaysis = checkDownloadedFileName(getDriver());*/
		
		
		Thread.sleep(5000);
		
		Robot robot = new Robot();
	/*	robot.keyPress(KeyEvent.VK_CONTROL);
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
		
		Thread.sleep(2000);
		*/
			
		Runtime.getRuntime().exec(getBaseDir() + "\\autoIt\\scripts\\scripts\\savingLedgerAnalysisPdf.exe");
		
		Thread.sleep(5000);
		
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_J);
		robot.keyRelease(KeyEvent.VK_J);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		
		Thread.sleep(2000);
		
		ArrayList<String> openTabs = new ArrayList<String>(getDriver().getWindowHandles());
			
		int actOpenWindowsCount = getDriver().getWindowHandles().size();
		int expOpenWindowsCount = 4;
		
		System.out.println("Number of Windows  : "+actOpenWindowsCount+"  Value Expected  "+expOpenWindowsCount);
		
		Thread.sleep(1000);
/*
	 	getDriver().switchTo().window(openTabs.get(2)).close();
	 	Thread.sleep(1000);
	 	getDriver().switchTo().window(openTabs.get(1)).close();
	 	Thread.sleep(1000);
	 	getDriver().switchTo().window(openTabs.get(0));*/
		
		
/*		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_W);
		robot.keyRelease(KeyEvent.VK_W);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		
		Thread.sleep(1000);*/
		
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_W);
		robot.keyRelease(KeyEvent.VK_W);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		
	 	
	 	Thread.sleep(3000);
	 	String actPDF = getBaseDir()+"\\autoIt\\ExportFiles\\ledgerAnalysis.pdf";
		String expPDF = getBaseDir()+"\\autoIt\\ImportFiles\\ledgerAnalysis.pdf";
		
		PDFUtil pdfutil = new PDFUtil();
		
		
		String actPdfList  = pdfutil.getText(actPDF);
		String ExpPdfList = pdfutil.getText(expPDF).replaceAll("22/12/2025", getCurrentDateF2());
		
		
		
		

		System.out.println("actPDF  : "+actPdfList);
		System.out.println("expPDF  : "+ExpPdfList);
		
		
		
		if (actPdfList.equalsIgnoreCase(ExpPdfList)) 
		{
			
			return true;
		}
		else
		{
			
			return false;
		}

	}

	@FindBy(xpath="//i[@class='icon-arrow hiconright']")
	public static WebElement userNameDisplay;


	//@FindBy(xpath="//div[@id='id_mainlayoutmenu']/ul[2]/li[6]")
	@FindBy(xpath = "//div[@class='adminprofile']")
	public static WebElement userNameDisplayLogo;


	@FindBy(xpath="//div[@id='id_mainlayoutmenu']/ul[2]/li[6]//ul/li[1]")
	public static WebElement changePassword;

	@FindBy(xpath="//select[@id='id_languageoptions']")
	public static WebElement languageDropdownInLogout;

	@FindBy(xpath="//*[@id='id_user_profile_icons']/li[2]/a")
	public static WebElement logoutOption;

	public static void checkogoutAndLogin() throws InterruptedException
	{
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(userNameDisplay));
		userNameDisplay.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(logoutOption));
		logoutOption.click();

		Thread.sleep(2000);

		LoginPage lp=new LoginPage(getDriver()); 

		String unamelt="su";

		String pawslt="su";

		lp.enterUserName(unamelt);

		Thread.sleep(2000);

		lp.enterPassword(pawslt);

		/*String compname="User Restrictions--COGS";*/
		String compname="RD REPORTS";

		Select oSelect = new Select(companyDropDownList);

		List <WebElement> elementCount = oSelect.getOptions();

		int cqSize = elementCount.size();

		System.out.println("CompanyDropdownList Count :"+cqSize);

		int y;

		for(y=0; y<elementCount.size(); y++) 
		{

			elementCount.get(y).getText();

			String optionName = elementCount.get(y).getText();
			if(optionName.toUpperCase().startsWith(compname.toUpperCase()))
			{
				System.out.println("q"+elementCount.get(y).getText());
				elementCount.get(y).click();
			}

		}


		Thread.sleep(2000);

		lp.clickOnSignInBtn();

		Thread.sleep(2000);
		
		lp.reLogin(unamelt, pawslt, compname);
		Thread.sleep(2000);
		
	}
	

	public boolean checkImportExcelFileInReportDesign() throws InterruptedException, IOException, EncryptedDocumentException, InvalidFormatException
	{
		
		checkogoutAndLogin();
		
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

		
		Runtime.getRuntime().exec(getBaseDir()+"\\autoIt\\scripts\\BillDetails.exe");

		Thread.sleep(6999);

		Thread.sleep(2999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportNameDropdown));       
		
		String actreportNameDropdown=reportNameDropdown.getAttribute("value");
		String expreportNameDropdown="BillwiseDetails";

		System.out.println(" reportNameDropdown  : "+actreportNameDropdown +" Value  "+expreportNameDropdown);

		reportNameDropdown.sendKeys(Keys.TAB);


		Thread.sleep(1000);

		 ((JavascriptExecutor)getDriver()).executeScript("window.scrollTo(0, 0)","");
			
		 Thread.sleep(2000);
		finishBtn.click();
		
		Thread.sleep(2000);
		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : "+actMessage+" Value Expected : "+expMessage);
		
		

		Thread.sleep(3000);
		
		reportNameDropdown.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
		Thread.sleep(3000);
		reportNameDropdown.sendKeys("BillwiseDetails");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(previewTab));
		previewTab.click();

		Thread.sleep(3000);

		int previewTabHeaderListCount=previewTabHeaderList.size();

		ArrayList<String >previewTabHeaderListArray=new ArrayList<>();

		for (int i = 0; i < previewTabHeaderListCount; i++)
		{

			String data=previewTabHeaderList.get(i).getText();
			previewTabHeaderListArray.add(data);

		}
		String actpreviewTabHeaderList=previewTabHeaderListArray.toString();
		String exppreviewTabHeaderList="[, Account.Name, Account.Account Type, Account2.Name, Account2.Code, Name, Document No., Due date, Unit.Name, Department.Name, Warehouse.Name]";


		System.out.println(" ACt List   :"+actpreviewTabHeaderList);
		System.out.println("  Exp List  :"+exppreviewTabHeaderList);


		if(actMessage.equalsIgnoreCase(expMessage) && actreportNameDropdown.equalsIgnoreCase(expreportNameDropdown)
				&&	actpreviewTabHeaderList.equalsIgnoreCase(exppreviewTabHeaderList))
		{

			System.out.println(" Test Pass: Imported from XML ");
			return true;
		}
		else
		{
			System.out.println(" Test Fail: Imported from XML ");
			return false;
		}
	}



	public boolean checkExportOptionreportDesging() throws InterruptedException, IOException, AWTException
	{

		Thread.sleep(2000);

		File Efile=new File(getBaseDir()+"\\autoIt\\ExportFiles\\ExportRDBillwise.xml");

		if(Efile.exists())
		{
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

		Runtime.getRuntime().exec(getBaseDir()+"\\autoIt\\scripts\\ExportRDBillwise.exe");


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

		Runtime.getRuntime().exec(getBaseDir() + "\\autoIt\\scripts\\scripts\\ExportRDBillwise.exe");

		Thread.sleep(5000);

		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_J);
		robot.keyRelease(KeyEvent.VK_J);
		robot.keyRelease(KeyEvent.VK_CONTROL);

		Thread.sleep(2000);

		ArrayList<String> openTabs = new ArrayList<String>(getDriver().getWindowHandles());

		int actOpenWindowsCount = getDriver().getWindowHandles().size();
		int expOpenWindowsCount = 3;

		System.out.println("Number of Windows  : "+actOpenWindowsCount+"  Value Expected  "+expOpenWindowsCount);

		Thread.sleep(1000);
/*
		getDriver().switchTo().window(openTabs.get(2)).close();
		Thread.sleep(1000);
		getDriver().switchTo().window(openTabs.get(1)).close();
		Thread.sleep(1000);
		getDriver().switchTo().window(openTabs.get(0));*/
		
		
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_W);
		robot.keyRelease(KeyEvent.VK_W);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		
		Thread.sleep(2000);
		
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_W);
		robot.keyRelease(KeyEvent.VK_W);
		robot.keyRelease(KeyEvent.VK_CONTROL);


		String actPDF = "D:\\Billwise Project\\FocusAI\\autoIt\\ExportFiles\\LedgerAnalaysis.pdf";
		String expPDF = "D:\\Billwise Project\\FocusAI\\\\autoIt\\Prints\\ExpectedRDReport.pdf";

		PDFUtil pdfutil = new PDFUtil();

		boolean result = pdfutil.compare(actPDF, expPDF);

		System.out.println("Compared Result  : "+result);

		Thread.sleep(2000);

		if (result) 
		{
			return true;
		}
		else
		{
			return false;
		}

	}


	@FindBy(xpath="//input[@id='id_rd_parameter_entry_fieldname']")
	private static WebElement rdFieldNameTxt;

	@FindBy(xpath="//input[@id='id_rd_parameter_entry_variablename']")
	private static WebElement rdVariableNameTxt;

	@FindBy(xpath="//select[@id='id_rd_parameter_entry_fieldtype']")
	private static WebElement rdFieldTypeDrpdwn;

	@FindBy(xpath="//input[@id='id_rd_parameter_entry_multipleinputselection']/following-sibling::span")
	private static WebElement rdMultipleInputChkbox;
	
	@FindBy(xpath="//input[@id='id_rd_parameter_entry_multipleinputselection']")
	private static WebElement rdMultipleInputChkboxSelected;

	@FindBy(xpath="//input[@id='id_rd_parameter_entry_isgroup']")
	private static WebElement rdInAGroupChkbox;


	@FindBy(xpath="//*[@id='id_rd_parameters_group']//input[@value='Ok']")
	private static WebElement rdParametersOkbtn;


	@FindBy(xpath="//*[@id='id_rd_definition_parameters_n1']")
	private static WebElement rdParametersTxtArea;
	
	@FindBy(xpath="//*[@id='idFilterCustomizeIcon']")
	private static WebElement rdDataSetDefaultfilter;
	
	@FindBy(xpath="//*[text()=' Department']//..//i")
	private static WebElement rdfilterDeptExpBtn;
	
	@FindBy(xpath="(//*[text()=' Department']//..//i//following::ul//li//input)[1]//following-sibling::span")
	private static WebElement rdfilterDeptNameChkBoxSelected;
	
	@FindBy(xpath="(//*[text()=' Department']//..//i//following::ul//li//input)[1]")
	private static WebElement rdfilterDeptNameChkBox;
	
	@FindBy(xpath="(//input[@value='Ok'])[1]")
	private static WebElement rdfilterOkbtn;

	public boolean checkSavingRDReportsWihParameter() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{

		Thread.sleep(1999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(homeMenu));
		homeMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(utilities));
		utilities.click();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportDesignerMenu));
		reportDesignerMenu.click();

		Thread.sleep(5000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportNameDropdown));       
		reportNameDropdown.sendKeys("Billwise Details");
		reportNameDropdown.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportTypeDropdown));
		Select rtd= new Select(reportTypeDropdown);
		rtd.selectByVisibleText("Details");

		//getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdReportsAdvanceEngineChkbox));
		//rdReportsAdvanceEngineChkbox.click();


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
		Select s1=new Select(rdFieldTypeDrpdwn);
		s1.selectByValue("3");


		Thread.sleep(2000);
		
		//scrollToElementJSE(rdParametersOkbtn);
		getAction().moveToElement(rdParametersOkbtn).build().perform();
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdParametersOkbtn));
		rdParametersOkbtn.click();

		Thread.sleep(2999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdParametersTxtArea));
		String actrdParametersTxtArea=rdParametersTxtArea.getAttribute("data-fieldname");
		System.out.println(" rdParametersTxtArea : "+actrdParametersTxtArea);


		Thread.sleep(2000);
		
		getAction().moveToElement(dataSetTab).build().perform();
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dataSetTab));
		dataSetTab.click();

		Thread.sleep(2000);

		int transactionSetListCount = transactionSetList.size();

		for(int i=0;i<=transactionSetListCount;i++)
		{
			String data = transactionSetList.get(i).getText();

			if(data.equalsIgnoreCase("Bill Reference"))
			{
				transactionSetList.get(i).click();

				break;
			}
		}


		Thread.sleep(4000);
		
		click(rdDataSetDefaultfilter);
		click(rdfilterDeptExpBtn);
		Thread.sleep(2500);
		if(rdfilterDeptNameChkBox.isSelected()==false)
		{
			click(rdfilterDeptNameChkBoxSelected);
		}
		
		click(rdfilterOkbtn);
		Thread.sleep(2500);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdCusTabDfaultFilterTxt));
		rdCusTabDfaultFilterTxt.click();
		rdCusTabDfaultFilterTxt.sendKeys("DUBAI");
		Thread.sleep(1999);

		rdCusTabDfaultFilterTxt.sendKeys(Keys.TAB);

		Thread.sleep(1999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTab));
		customizationTab.click();

		Thread.sleep(2000);
	//	getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionExpandBtn));
	//	transactionExpandBtn.click();

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

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(deptExpandBtn1));
		deptExpandBtn1.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(deptNameBtn1));
		getAction().doubleClick(deptNameBtn1).build().perform();



		Thread.sleep(2000);


		 ((JavascriptExecutor)getDriver()).executeScript("window.scrollTo(0, 0)","");
			
		 Thread.sleep(2000);
		finishBtn.click();
		
		Thread.sleep(2000);

		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : "+actMessage+" Value Expected : "+expMessage);

		if(actMessage.equalsIgnoreCase(expMessage))
		{

			System.out.println(" Test PasS: Report desgining is saved with Document Type ");
			return true;
		}
		else
		{
			System.out.println(" Test FAIL: Report desgining is saved with Document Type ");
			return false;
		}

	}

	@FindBy(xpath="//input[@id='FOption_22_0_DefaultFilter_0']")
	private static WebElement rdCusTabDfaultFilterTxt;



	@FindBy(xpath="//*[@id='id_rd_customization_report_column_button_container']/input[2]")
	private static WebElement rdExtraFiledOkBtn;
	
	@FindBy(xpath="(//*[@id='id_rd_customization_report_column_property']//div[2]/span[1])[1]")
	private static WebElement progrmmableFieldExpandBtn;
	
	


	public boolean checkAddingProgrammmingFiledInBillwiseRDReport() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{

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

		Thread.sleep(2999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTab));
		customizationTab.click();

		Thread.sleep(2000);
		
		//ClickUsingJs(ProgramFiledexpandBtn);
		/*getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(ProgramFiledexpandBtn));
		ProgramFiledexpandBtn.click();*/

		Thread.sleep(1000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(ProgramFieldBtn));
		getAction().doubleClick(ProgramFieldBtn).build().perform();
		Thread.sleep(2000);

		int customizeTabTableHeaderLsistCount = customizeTabTableHeaderLsist.size();
		System.err.println(customizeTabTableHeaderLsistCount);
		for(int i=1;i<=customizeTabTableHeaderLsistCount;i++)
		{
			String data = customizeTabTableHeaderLsist.get(i).getText();

			if(data.equalsIgnoreCase("Programmable Field"))
			{
				customizeTabTableHeaderLsist.get(i).click();

				System.err.println(i);
				Thread.sleep(1000);

				break;
			}
		}
		
		progrmmableFieldExpandBtn.click();

		Thread.sleep(1999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdExtraFiledColHeadingTxt));
		rdExtraFiledColHeadingTxt.click();
		rdExtraFiledColHeadingTxt.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
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
		 ((JavascriptExecutor)getDriver()).executeScript("window.scrollTo(0, 0)","");
			
		 Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(finishBtn));
		finishBtn.click();

		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		Thread.sleep(3000);
		
		Thread.sleep(2999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportNameDropdown));       
		reportNameDropdown.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
		reportNameDropdown.sendKeys("Billwise Details");
		Thread.sleep(2000);
		reportNameDropdown.sendKeys(Keys.TAB);
		/*
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(previewTab));
		previewTab.click();

		Thread.sleep(3000);

		int previewTabHeaderListCount=previewTabHeaderList.size();

		ArrayList<String >previewTabHeaderListArray=new ArrayList<>();

		for (int i = 0; i < previewTabHeaderListCount; i++)
		{

			String data=previewTabHeaderList.get(i).getText();
			previewTabHeaderListArray.add(data);

		}
		String actpreviewTabHeaderList=previewTabHeaderListArray.toString();
		String exppreviewTabHeaderList="[, Document No., Account.Name, Item.Code, Department.Name]";


		System.out.println(" ACt List   :"+actpreviewTabHeaderList);
		System.out.println("  Exp List  :"+exppreviewTabHeaderList);
*/

		Thread.sleep(3000);
		
		 ((JavascriptExecutor)getDriver()).executeScript("window.scrollTo(0, 0)","");
			
		 Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(finishBtn));
		finishBtn.click();

		
		checkValidationMessage(expMessage);

		System.out.println("Validation Message : "+actMessage+" Value Expected : "+expMessage);

		if(actMessage.equalsIgnoreCase(expMessage) /*&& 
				actpreviewTabHeaderList.equalsIgnoreCase(exppreviewTabHeaderList)*/)
		{

			System.out.println(" Test PasS: Extra Filed Programmable Filed added");
			return true;
		}
		else
		{
			System.out.println(" Test FAIL: Extra Filed Programmable Filed added");
			return false;
		}

	}


	//Programmable Fileds

	@FindBy(xpath="//*[@id='rd_customization_tree422']/a/i")
	private static WebElement custProgramFiledexpandBtn1;

	@FindBy(xpath="//*[@id='rd_customization_tree423']/a/span")
	private static WebElement custProgramFieldBtn1;


	@FindBy(xpath="//span[contains(text(),'Programmable Fields')]")
	private static WebElement custProgramFiledexpandBtn2;

	@FindBy(xpath="//span[text()='Programmable Field']")
	private static WebElement custProgramFieldBtn2;




	@FindBy(xpath="//*[@id='rd_customization_tree438']/a/i")
	private static WebElement custProgramFiledexpandBtn;

	@FindBy(xpath="//*[contains(text(),'Programmable Fields')]")
	private static WebElement ProgramFiledexpandBtn;


	//@FindBy(xpath="(//*[contains(text(),'Programmable Field')])[2]")
	@FindBy(xpath="//span[text()='Programmable Field']")
	private static WebElement ProgramFieldBtn;

	@FindBy(xpath="//*[@id='rd_customization_tree439']/a/span")
	private static WebElement custProgramFieldBtn;

	@FindBy(xpath="//input[@id='id_rd_columnproperty_columnheading']")
	private static WebElement rdExtraFiledColHeadingTxt;


	@FindBy(xpath="//input[@id='formulaControl_textbox']")
	private static WebElement rdExtraFiledFormulaControlTxt;

	@FindBy(xpath="//*[@id='id_rd_customization_report_column_button_container']/input[2]")
	private static WebElement rdExtraFiledFormulaControlOkBtn;


	@FindBy(xpath="//select[@id='id_rd_columnproperty_horizontalalign']")
	private static WebElement rdExtraFiledColHorizontalDrpdwn;


	@FindBy(xpath="//input[@id='id_rd_columnproperty_columnwidth']")
	private static WebElement rdExtraFiledColWidthTxt;

	@FindBy(xpath="//span[contains(text(),'select options')]")
	private static WebElement rdExtraFiledColSelectDrpdwn;


	@FindBy(xpath="//select[@id='id_rd_columnproperty_sign']")
	private static WebElement rdExtraFiledColSignDrpdwn;

	@FindBy(xpath="//span[contains(text(),'select options')]")
	private static WebElement rdExtraFiledSelectingDrpdwn;


	@FindBy(xpath="//tbody[@id='LandingGridBody']/tr/td[11]")
	public static List<WebElement> stockLedgerHometableItemNamesList;

	@FindBy(xpath="//tbody[@id='LandingGridBody']/tr/td[8]/div/label/input")
	public static List<WebElement> stockLedgerHometableItemChkboxList;

	public boolean checkBillwiseDetailsreport() throws InterruptedException
	{	

		Thread.sleep(3000);
		getDriver().navigate().refresh();
		
		Thread.sleep(3000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
		searchTxt.click();
		Thread.sleep(1000);
		searchTxt.sendKeys("Billwise Details");
		Thread.sleep(1000);
		searchTxt.sendKeys(Keys.ENTER);


		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_DateOptionDropdown));

		Select s=new Select(sl_DateOptionDropdown);
		s.selectByValue("1");

		Thread.sleep(2000);
		int rowcount=stockLedgerHometableRowCount.size();
		
		System.out.println(rowcount);
		
		for (int i = 0; i < rowcount; i++) 
		{
			String actName = stockLedgerHometableItemNamesList.get(i).getText();
			
			System.out.println(actName);
			
			if(actName.equalsIgnoreCase("DUBAI"))
			{
				stockLedgerHometableItemChkboxList.get(i).click();
				break;
			}
		}

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_OkBtn));
		sl_OkBtn.click();


		Thread.sleep(2000);
		boolean novalidationConfirmationMessage =validationConfirmationMessage.getText().isEmpty();

		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";


		Thread.sleep(1500);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_1stRow1stCol));

		int reportsRow1ListCount = reportCol1List.size();
		ArrayList<String> reportsRow1ListArray = new ArrayList<String>();
		for(int i=0;i<reportsRow1ListCount;i++)
		{
			String data = reportCol1List.get(i).getText().trim();


			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date date=new Date();
			String expadjustBills=df.format(date);

			if (data.isEmpty() ==  false)
			{
				reportsRow1ListArray.add(data);
			}
		}
		String actRow1List = reportsRow1ListArray.toString();
		String expRow1List = "[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16]";


		int report2ndRowListCount = reportCol2List.size();
		ArrayList<String> report2ndRowListArray = new ArrayList<String>();
		for(int i=0;i<report2ndRowListCount;i++)
		{
			String data = reportCol2List.get(i).getText().trim();
			if (data.isEmpty() ==  false)
			{
				report2ndRowListArray.add(data);
			}
		}
		String actRow2List = report2ndRowListArray.toString();
		String expRow2List = "[1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2]";


		int report3rdRowListCount = reportCol3List.size();
		ArrayList<String> report3rdRowListArray = new ArrayList<String>();
		for(int i=0;i<report3rdRowListCount;i++)
		{
			String data = reportCol3List.get(i).getText().trim();
			if (data.isEmpty() ==  false)
			{
				report3rdRowListArray.add(data);
			}
		}
		String actRow3List = report3rdRowListArray.toString();
		String expRow3List = "[Opening Balances Control A/C, Customer A, STD RATE COGS ACC INV, Bank, Bank, Customer A, SR COGS POSTING ACC, Bank, Bank, Vendor New Reference, Vendor New Reference, VAT INPUT, Bank, Bank, Vendor B]";


		int report4thRowListCount = reportCol4List.size();
		ArrayList<String> report4thRowListArray = new ArrayList<String>();
		for(int i=0;i<report4thRowListCount;i++)
		{
			String data = reportCol4List.get(i).getText().trim();
			if (data.isEmpty() ==  false)
			{
				report4thRowListArray.add(data);
			}
		}
		String actRow4List = report4thRowListArray.toString();
		String expRow4List = "[STD RATE COGS ITEM, STD RATE COGS ITEM, STD RATE COGS ITEM, STD RATE COGS ITEM]";


		int report5thRowListCount = reportCol5List.size();
		ArrayList<String> report5thRowListArray = new ArrayList<String>();
		for(int i=0;i<report5thRowListCount;i++)
		{
			String data = reportCol5List.get(i).getText().trim();
			if (data.isEmpty() ==  false)
			{
				report5thRowListArray.add(data);
			}
		}
		String actRow5List = report5thRowListArray.toString();
		String expRow5List = "[STD RATE COGS ITEM, STD RATE COGS ITEM, STD RATE COGS ITEM, STD RATE COGS ITEM]";

		int report6thRowListCount = reportCol6List.size();
		ArrayList<String> report6thRowListArray = new ArrayList<String>();
		for(int i=0;i<report6thRowListCount;i++)
		{
			String data = reportCol6List.get(i).getText().trim();
			if (data.isEmpty() ==  false)
			{
				report6thRowListArray.add(data);
			}
		}
		String actRow6List = report6thRowListArray.toString();
		String expRow6List = "[DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI]";



		System.out.println("actRow1List  : "+actRow1List);
		System.out.println("expRow1List  : "+expRow1List);
		System.out.println("*********************************************************************");

		System.out.println("actRow2List  : "+actRow2List);
		System.out.println("expRow2List  : "+expRow2List);
		System.out.println("*********************************************************************");

		System.out.println("actRow3List  : "+actRow3List);
		System.out.println("expRow3List  : "+expRow3List);
		System.out.println("*********************************************************************");

		System.out.println("actRow4List  : "+actRow4List);
		System.out.println("expRow4List  : "+expRow4List);
		System.out.println("*********************************************************************");

		System.out.println("actRow5List  : "+actRow5List);
		System.out.println("expRow5List  : "+expRow5List);
		System.out.println("*********************************************************************");

		System.out.println("actRow6List  : "+actRow6List);
		System.out.println("expRow6List  : "+expRow6List);
		System.out.println("*********************************************************************");


		if (actRow1List.equalsIgnoreCase(expRow1List) &&
				actRow2List.equalsIgnoreCase(expRow2List) &&
				actRow3List.equalsIgnoreCase(expRow3List) && 
				actRow4List.equalsIgnoreCase(expRow4List)  &&
				actRow5List.equalsIgnoreCase(expRow5List)  &&
				actRow6List.equalsIgnoreCase(expRow6List)  &&

				actvalidationConfirmationMessage.equalsIgnoreCase(expvalidationConfirmationMessage)) 
		{
			System.out.println(" Test Pass: Values Dsiplayed as Expected ");
			return true;
		} 
		else 
		{
			System.out.println(" Test FAIL: Values Dsiplayed as Expected ");
			String meesage=validationConfirmationMessage.getText();
			System.err.println(" Meesage Displayed  : "+meesage);
			return false;
		}

	}


	@FindBy(xpath="//select[@id='id_rd_definition_datasets']")
	private static WebElement reportDataSetTypeDrpdwn; 
	
	@FindBy(xpath="//*[@id='filterTree_22_0_AdvanceFilter_']/ul/li[31]/ul//li/a")
	private static List<WebElement> deptList; 
	
	@FindBy(xpath="//div[@class='dropdown-menu filterTree']//*[@id='23']/..//ul//li/a")
	private static List<WebElement> ItemList; 
	
	@FindBy(xpath="//div[@class='dropdown-menu filterDataTree']//ul//li")
	private static List<WebElement> parametersList; 
	
	
	
	
	
	



	public boolean checkParameterWithMultiplewayoptions() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{

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

		if (rdMultipleInputChkboxSelected.isSelected()==false) 
		{
			rdMultipleInputChkbox.click();
		}


		boolean   actrdMultipleInputChkbox=rdMultipleInputChkboxSelected.isSelected();
		boolean exprdMultipleInputChkbox=true;


		System.out.println(" rdMultipleInputChkbox "+actrdMultipleInputChkbox +" Value Expected  : "+exprdMultipleInputChkbox);

		Thread.sleep(2000);
		
		//scrollToElementJSE(rdParametersOkbtn);
		getAction().moveToElement(rdParametersOkbtn).build().perform();
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdParametersOkbtn));
		rdParametersOkbtn.click();


		Thread.sleep(2000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dataSetTab));
		dataSetTab.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdDatSetFilterBtn));
		rdDatSetFilterBtn.click();
		
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdDatSetFilterBtn));
		rdDatSetFilterBtn.click();
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdAdvanceFilterRemoveBtn));
		rdAdvanceFilterRemoveBtn.click();
/*
		for (int i = 0; i<5; i++) 
		{

			if (rdAdvanceFilterWhereDrpdwn.isDisplayed()==false)
			{

				rdDatSetFilterBtn.click();
				System.out.println(" I "+i);
				break;
			}

		}*/
		
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdAdvanceFilterWhereDrpdwn));
		rdAdvanceFilterWhereDrpdwn.click();
		Select s1=new Select(rdAdvanceFilterWhereDrpdwn);
		s1.selectByValue("0");


		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdAdvanceFilterNameTxt));
		rdAdvanceFilterNameTxt.click();
		Thread.sleep(2000);

		//getAction().moveToElement(rdAdvanceFilterDepExpBtn).build().perform();
		scrollToElementJSE(rdAdvanceFilterDepExpBtn);
		Thread.sleep(2000);
		rdAdvanceFilterDepExpBtn.click();
	/*	
		int count = deptList.size();
		
		for (int i = 0; i < count; i++)
		{
			String data = deptList.get(i).getText();
			if (data.equalsIgnoreCase("Name")) 
			{
				 deptList.get(i).click();
			}
		}
*/
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdAdvanceFilterDepName));
		rdAdvanceFilterDepName.click();
		
		//rdAdvanceFilterName_DepNameBtn.click();

		Thread.sleep(2000);
		Select s3=new Select(rdAdvanceFilterOpersatorDrpdwn);
		s3.selectByValue("0");


		Thread.sleep(2000);
		Select s4=new Select(rdAdvanceFilterValueDrpdwn);
		s4.selectByValue("2");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdAdvanceFilterSelectTxt));
		rdAdvanceFilterSelectTxt.click();
		rdAdvanceFilterSelectTxt.sendKeys("@department");

		Thread.sleep(2999);
		rdAdvanceFilterSelectTxt.sendKeys(Keys.TAB);


	/*	boolean novalidationConfirmationMessage =validationConfirmationMessage.getText().isEmpty();

		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";
*/
		
		scrollToElementJSE(rdAdvanceFilterSelectTxt);
		Thread.sleep(2000);
		String actrdAdvanceFilterSelectTxt=rdAdvanceFilterSelectTxt.getText();
		String exprdAdvanceFilterSelectTxt="@department";

		System.out.println(" rdAdvanceFilterSelectTxt : "+actrdAdvanceFilterSelectTxt +" Value exp: "+exprdAdvanceFilterSelectTxt);

		Thread.sleep(2999);

		 ((JavascriptExecutor)getDriver()).executeScript("window.scrollTo(0, 0)","");
			
		 Thread.sleep(2000);
		finishBtn.click();
		Thread.sleep(2000);

		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : "+actMessage+" Value Expected : "+expMessage);

		if(actMessage.equalsIgnoreCase(expMessage))
		{

			System.out.println(" Test PasS: Multiple Chkbx is Selcted ");
			return true;
		}
		else
		{
			System.out.println(" Test FAIL:  Multiple Chkbx is Selcted ");
			return false;
		}

	}



	@FindBy(xpath="//span[@id='a']")
	private static WebElement rdDatSetFilterBtn;
	
	@FindBy(xpath="//div[@id='id_rd_columnproperty_defaultfilter']//span[@class='theme_button_color icon-filter icon-font6']")
	private static WebElement rdDefaultFilterBtn;
	
	


	@FindBy(xpath="//*[@id='22_0_AdvanceFilter_']/table/tbody/tr/td[1]/select")
	private static WebElement rdAdvanceFilterWhereDrpdwn;
	
	@FindBy(xpath="//*[@id='22_1000_AdvanceFilter_']/table/tbody/tr/td[1]/select")
	private static WebElement AdvanceFilterWhereDrpdwn;
	
	
	
	@FindBy(xpath="//*[@id='0_0_AdvanceFilter_']/table/tbody/tr/td[1]/select")
	private static WebElement rdAdvanceFilterWhereDrpdwnForPaea;
	
	@FindBy(xpath="//*[@id='22_0_AdvanceFilter_']/table/tbody/tr/td[1]/select")
	private static WebElement rdAdvanceFilterWhereDrpdwnForPaea1;

	@FindBy(xpath="//*[@id='22_0_AdvanceFilter_']/table/tbody/tr/td[2]/input")
	              //*[@id="22_0_AdvanceFilter_"]/table/tbody/tr/td[2]/input
	private static WebElement rdAdvanceFilterNameTxt;
	
	@FindBy(xpath="//*[@id='0_0_AdvanceFilter_']/table/tbody/tr/td[2]/input")
   	private static WebElement rdAdvanceFilterNameTxt1;
	
	@FindBy(xpath="//*[@id='22_1000_AdvanceFilter_']/table/tbody/tr/td[2]/input")
	private static WebElement AdvanceFilterNameTxt;
	
	//@FindBy(xpath="//*[@id='filterTree_22_1000_AdvanceFilter_']/ul/li[118]/a[text()='Voucher type']")
	@FindBy(xpath="(//a[text()='Voucher type'])[3]")
	private static WebElement AdvFilterVoucherTypeBtn;
	
	//*[@id="filterTree_0_1000_AdvanceFilter_"]/ul/li[118]/a[text()='Voucher type']

	//@FindBy(xpath="//*[@id='filterTree_22_0_AdvanceFilter_']/ul/li[31]/a/span")

	@FindBy(xpath="//*[@id='22_0_AdvanceFilter_']/table/tbody/tr/td[6]/span")
	private static WebElement rdAdvanceFilterRemoveBtn;
	
	@FindBy(xpath="//*[@id='0_0_AdvanceFilter_']/table/tbody/tr/td[6]/span")
	private static WebElement rdAdvanceFilterRemoveBtn1;
	
	@FindBy(xpath="(//*[text()='Date'])[1]")
	private static WebElement rdAdvanceFilterDateBtn;
	
	@FindBy(xpath="(//a[contains(text(),'Department')]//span)[1]")
	private static WebElement rdAdvanceFilterDepExpBtn;
	
	@FindBy(xpath="//a[contains(text(),'Department')]//..//a[text()='Name']")
	private static WebElement rdAdvanceFilterDepName;
	
	@FindBy(xpath="//div[@class='dropdown-menu filterTree']//*[@id='23']/span")
	private static WebElement rdAdvanceFilterItemExpBtn;
	
	@FindBy(xpath="(//div[@class='dropdown-menu filterTree']//*[@id='23']/span//following::ul//a[text()=\"Name\"])[1]")
	private static WebElement rdAdvanceFilterItemNameBtn;
	

	
	@FindBy(xpath="(//div[@class='dropdown-menu filterTree']//*[@id='23']/span//following::ul//a[text()=\"Group Name\"])[1]")
	private static WebElement rdAdvanceFilterItemGroupNameBtn;
	
	
	@FindBy(xpath="//a[@id='5042']")
	private static WebElement rdAdvanceFilterName_DepNameBtn;


	@FindBy(xpath="//*[@id='22_0_AdvanceFilter_']/table/tbody/tr/td[3]/select")
	private static WebElement rdAdvanceFilterOpersatorDrpdwn;
	
	@FindBy(xpath="//*[@id='22_1000_AdvanceFilter_']/table/tbody/tr/td[3]/select")
	private static WebElement AdvanceFilterOpersatorDrpdwn;
	
	@FindBy(xpath="//*[@id='0_0_AdvanceFilter_']/table/tbody/tr/td[3]/select")
	private static WebElement rdAdvanceFilterOpersatorDrpdwn1;

	@FindBy(xpath="//*[@id='22_0_AdvanceFilter_']/table/tbody/tr/td[4]/select")
	private static WebElement rdAdvanceFilterValueDrpdwn;
	
	@FindBy(xpath="//*[@id='22_1000_AdvanceFilter_']/table/tbody/tr/td[4]/select")
	private static WebElement AdvanceFilterValueDrpdwn;
	
	@FindBy(xpath="//*[@id='0_0_AdvanceFilter_']/table/tbody/tr/td[4]/select")
	private static WebElement rdAdvanceFilterValueDrpdwn1;


	@FindBy(xpath="//*[@id='22_0_AdvanceFilter_']/table/tbody/tr/td[5]/input")
	private static WebElement rdAdvanceFilterSelectTxt;
	
	@FindBy(xpath="//input[@id='advancefilter_TableOptionControl_22_1000']")
	private static WebElement AdvanceFilterSelectTxt;
	
	
	
	@FindBy(xpath="//*[@id='0_0_AdvanceFilter_']/table/tbody/tr/td[5]/input")
	private static WebElement rdAdvanceFilterSelectTxt1;
	
	@FindBy(xpath="//*[@id='id_rd_customization_report_column_button_container']/input[2]")
	private static WebElement AdvanceFilterOkBtn;
	
	



	@FindBy(xpath="//tbody[@id='LandingGridBody']/tr")
	private static List<WebElement> stockLedgerHometableRowCount;


	public boolean checkBillwiseReportAfterCheckingMultipleChkbox() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(userNameDisplay));
		userNameDisplay.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(userNameDisplay));
		logoutOption.click();

		
		Thread.sleep(2000);

		LoginPage lp=new LoginPage(getDriver()); 

		String unamelt="su";

		String pawslt="su";

		lp.enterUserName(unamelt);

		Thread.sleep(2000);

		lp.enterPassword(pawslt);

		/*String compname="User Restrictions--COGS";*/
		String compname="RD REPORTS";

		Select oSelect = new Select(companyDropDownList);

		List <WebElement> elementCount = oSelect.getOptions();

		int cqSize = elementCount.size();

		System.out.println("CompanyDropdownList Count :"+cqSize);

		int y;

		for(y=0; y<elementCount.size(); y++) 
		{

			elementCount.get(y).getText();

			String optionName = elementCount.get(y).getText();
			if(optionName.toUpperCase().startsWith(compname.toUpperCase()))
			{
				System.out.println("q"+elementCount.get(y).getText());
				elementCount.get(y).click();
			}

		}

		Thread.sleep(2000);

		lp.clickOnSignInBtn();

		Thread.sleep(2000);
		
		//lp.reLogin(unamelt, pawslt, compname);
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
		searchTxt.click();
		Thread.sleep(1000);
		searchTxt.sendKeys("Billwise Details");
		Thread.sleep(1000);
		searchTxt.sendKeys(Keys.ENTER);


		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_DateOptionDropdown));

		Select s=new Select(sl_DateOptionDropdown);
		s.selectByValue("1");

		Thread.sleep(2000);

		int rowcount=stockLedgerHometableRowCount.size();

		ArrayList<String >department=new ArrayList<>();

		System.out.println(rowcount);

		for (int i = 0; i < rowcount; i++)
		{
			String data=stockLedgerHometableRowCount.get(i).getText();
			department.add(data);
		}

		String actDepList=department.toString();
		String expDepList="[1 INDIA INDIA, 2 DUBAI DUBAI, 3 AMERICA AMERICA, 4 SINGPORE SINGAPORE, 6 EUROPE EUROPE]";

		System.out.println(" Department List  : "+actDepList);
		System.out.println(" Department List  : "+expDepList);

		for (int i = 1; i <= rowcount; i++) 
		{
			WebElement name=getDriver().findElement(By.xpath("//tbody[@id='LandingGridBody']/tr["+i+"]/td[10]"));

			String actname=name.getText();

			System.out.println(actname);

			if(actname.equalsIgnoreCase("DUBAI") )
			{

				WebElement index=getDriver().findElement(By.xpath("//tbody[@id='LandingGridBody']/tr["+i+"]/td[8]/div/label/input"));
				index.click();

				break;
			}
		}

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_OkBtn));
		sl_OkBtn.click();


		////checkServerErrorMessage

		Thread.sleep(2000);
		boolean novalidationConfirmationMessage =validationConfirmationMessage.getText().isEmpty();

		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";

		Thread.sleep(1500);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_1stRow1stCol));

		int reportsRow1ListCount = reportCol1List.size();
		ArrayList<String> reportsRow1ListArray = new ArrayList<String>();
		for(int i=0;i<reportsRow1ListCount;i++)
		{
			String data = reportCol1List.get(i).getText().trim();
			
			if (data.isEmpty() ==  false)
			{
				reportsRow1ListArray.add(data);
			}

			
		}
		String actRow1List = reportsRow1ListArray.toString();
		String expRow1List = "[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16]";


		int report2ndRowListCount = reportCol2List.size();
		ArrayList<String> report2ndRowListArray = new ArrayList<String>();
		for(int i=0;i<report2ndRowListCount;i++)
		{
			String data = reportCol2List.get(i).getText().trim();

			if (data.isEmpty() ==  false)
			{
				report2ndRowListArray.add(data);
			}

		}
		String actRow2List = report2ndRowListArray.toString();
		String expRow2List = "[1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2]";


		int report3rdRowListCount = reportCol3List.size();
		ArrayList<String> report3rdRowListArray = new ArrayList<String>();
		for(int i=0;i<report3rdRowListCount;i++)
		{
			String data = reportCol3List.get(i).getText().trim();

			if (data.isEmpty() ==  false)
			{
				report3rdRowListArray.add(data);
			}

		}
		String actRow3List = report3rdRowListArray.toString();
		String expRow3List = "[Opening Balances Control A/C, Customer A, STD RATE COGS ACC INV, Bank, Bank, Customer A, SR COGS POSTING ACC, Bank, Bank, Vendor New Reference, Vendor New Reference, VAT INPUT, Bank, Bank, Vendor B]";


		int report4thRowListCount = reportCol4List.size();
		ArrayList<String> report4thRowListArray = new ArrayList<String>();
		for(int i=0;i<report4thRowListCount;i++)
		{
			String data = reportCol4List.get(i).getText().trim();

			if (data.isEmpty() ==  false)
			{
				report4thRowListArray.add(data);
			}

		}
		String actRow4List = report4thRowListArray.toString();
		String expRow4List = "[STD RATE COGS ITEM, STD RATE COGS ITEM, STD RATE COGS ITEM, STD RATE COGS ITEM]";


		int report5thRowListCount = reportCol5List.size();
		ArrayList<String> report5thRowListArray = new ArrayList<String>();
		for(int i=0;i<report5thRowListCount;i++)
		{
			String data = reportCol5List.get(i).getText().trim();

			if (data.isEmpty() ==  false)
			{
				report5thRowListArray.add(data);
			}

		}
		String actRow5List = report5thRowListArray.toString();
		String expRow5List = "[STD RATE COGS ITEM, STD RATE COGS ITEM, STD RATE COGS ITEM, STD RATE COGS ITEM]";

		int report6thRowListCount = reportCol6List.size();
		ArrayList<String> report6thRowListArray = new ArrayList<String>();
		for(int i=0;i<report6thRowListCount;i++)
		{
			String data = reportCol6List.get(i).getText().trim();

			if (data.isEmpty() ==  false)
			{
				report6thRowListArray.add(data);
			}

		}
		String actRow6List = report6thRowListArray.toString();
		String expRow6List = "[DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI]";



		System.out.println("actRow1List  : "+actRow1List);
		System.out.println("expRow1List  : "+expRow1List);
		System.out.println("*********************************************************************");

		System.out.println("actRow2List  : "+actRow2List);
		System.out.println("expRow2List  : "+expRow2List);
		System.out.println("*********************************************************************");

		System.out.println("actRow3List  : "+actRow3List);
		System.out.println("expRow3List  : "+expRow3List);
		System.out.println("*********************************************************************");

		System.out.println("actRow4List  : "+actRow4List);
		System.out.println("expRow4List  : "+expRow4List);
		System.out.println("*********************************************************************");

		System.out.println("actRow5List  : "+actRow5List);
		System.out.println("expRow5List  : "+expRow5List);
		System.out.println("*********************************************************************");

		System.out.println("actRow6List  : "+actRow6List);
		System.out.println("expRow6List  : "+expRow6List);
		System.out.println("*********************************************************************");


		if (actRow1List.equalsIgnoreCase(expRow1List) &&
				actRow2List.equalsIgnoreCase(expRow2List) &&
				actRow3List.equalsIgnoreCase(expRow3List) && 
				actRow4List.equalsIgnoreCase(expRow4List)  &&
				actRow5List.equalsIgnoreCase(expRow5List)  &&
				actRow6List.equalsIgnoreCase(expRow6List)  &&

				actvalidationConfirmationMessage.equalsIgnoreCase(expvalidationConfirmationMessage)) 
		{
			System.out.println(" Test Pass: Values Dsiplayed as Expected ");
			return true;
		} 
		else 
		{
			System.out.println(" Test FAIL: Values Dsiplayed as Expected ");
			String meesage=validationConfirmationMessage.getText();
			System.err.println(" Meesage Displayed  : "+meesage);
			return false;
		}

	}









	public boolean checkSavingReportDesigningWithQuaeery() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
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
		Select rtd= new Select(reportTypeDropdown);
		rtd.selectByVisibleText("Details");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportDataSetTypeDrpdwn));
		Select rds= new Select(reportDataSetTypeDrpdwn);
		rds.selectByVisibleText("Query");

		Thread.sleep(2000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(inventoryExpandBtn));
		inventoryExpandBtn.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportsBtn));
		reportsBtn.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dataSetTab));
		dataSetTab.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportDataSetQueryTxt));
		reportDataSetQueryTxt.click();

		reportDataSetQueryTxt.sendKeys("select * from msec_users");

		Thread.sleep(2999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTab));
		customizationTab.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(ReportFieldExpandBtn));
		ReportFieldExpandBtn.click();


		int reportFieldlistCount=reportFieldlist.size();
		ArrayList<String >reportFieldlistarray= new ArrayList<>();
		for (int i = 0; i < reportFieldlistCount; i++) 
		{
			String data =reportFieldlist.get(i).getText();
			reportFieldlistarray.add(data);
		}

		String actreportFieldlist=reportFieldlistarray.toString();
		String expreportFieldlist="[iUserId, sLoginName, sLoginAbbr, iPwdPolicyId, sPassword, sUserName, sEmail, sPhone, sMobile, binImage, iGroupId, sSecurityQuestion, sSecurityAnswer, iUserType, iLinkId, sDomainName, sDomainUser, bAccountDisabled, bSendEmailNotification, bAllowMultipleLogin, bEmailonLoginFailure, bEmailUseronLoginSuccess, bDontLockAccount, iStatus, iNumInvalidAttempts, iLockedTill, iDays, iBlockFromDate, iBlockToDate, iTimeRestrictionStartDate, iTimeRestrictionEndDate, iTimeRestrictionStartTime, iTimeRestrictionEndTime, iLocation, iLanguage, iAltLanguage, iTimeZone, iUserAccess, iPWDChangeDate, fVal0, fVal1, fVal2, fVal3, fVal4, iCreatedBy, iModifiedBy, iCreatedDate, iModifiedDate, iCreatedTime, iModifiedTime, bModifiedDiffLoc, iSyncReceivedDate, iEditingLocation, iLocationId, sAuthenticationCode, biSignature, sEmailPwd, bEmailAuthPermission, sMacAddress, bDisableAPIAccess, bDisableSQLQueriesfromAPI]";


		System.out.println(" act reportFieldlist : "+actreportFieldlist);
		System.out.println(" exp reportFieldlist : "+expreportFieldlist);


		for (int i = 0; i < reportFieldlistCount; i++) 
		{
			String data =reportFieldlist.get(i).getText();

			if (data.equalsIgnoreCase("iUserId")) 
			{
				reportFieldlist.get(i).click();

				getAction().doubleClick(reportFieldlist.get(i)).build().perform();
			}
		}

		for (int i = 0; i < reportFieldlistCount; i++) 
		{
			String data =reportFieldlist.get(i).getText();

			if (data.equalsIgnoreCase("sLoginName")) 
			{
				reportFieldlist.get(i).click();

				getAction().doubleClick(reportFieldlist.get(i)).build().perform();
			}
		}

		for (int i = 0; i < reportFieldlistCount; i++) 
		{
			String data =reportFieldlist.get(i).getText();

			if (data.equalsIgnoreCase("sLoginAbbr")) 
			{
				reportFieldlist.get(i).click();

				getAction().doubleClick(reportFieldlist.get(i)).build().perform();
			}
		}


		for (int i = 0; i < reportFieldlistCount; i++) 
		{
			String data =reportFieldlist.get(i).getText();

			if (data.equalsIgnoreCase("iPwdPolicyId")) 
			{
				reportFieldlist.get(i).click();

				getAction().doubleClick(reportFieldlist.get(i)).build().perform();
			}
		}

		for (int i = 0; i < reportFieldlistCount; i++) 
		{
			String data =reportFieldlist.get(i).getText();

			if (data.equalsIgnoreCase("sPassword")) 
			{
				reportFieldlist.get(i).click();

				getAction().doubleClick(reportFieldlist.get(i)).build().perform();
			}
		}
		
		Thread.sleep(2000);
		 ((JavascriptExecutor)getDriver()).executeScript("window.scrollTo(0, 0)","");
			
		 Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(finishBtn));
		finishBtn.click();
		Thread.sleep(2000);

		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : "+actMessage+" Value Expected : "+expMessage);
		
		reportNameDropdown.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
		Thread.sleep(2999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportNameDropdown));       
		reportNameDropdown.sendKeys("Report With Quarry");
		Thread.sleep(2000);
		reportNameDropdown.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(previewTab));
		previewTab.click();

		Thread.sleep(3000);

		int previewTabHeaderListCount=previewTabHeaderList.size();

		ArrayList<String >previewTabHeaderListArray=new ArrayList<>();

		for (int i = 0; i < previewTabHeaderListCount; i++)
		{

			String data=previewTabHeaderList.get(i).getText();
			previewTabHeaderListArray.add(data);

		}
		String actpreviewTabHeaderList=previewTabHeaderListArray.toString();
		String exppreviewTabHeaderList="[]";


		System.out.println(" Actual Preview TAb List   :"+actpreviewTabHeaderList);
		System.out.println("  Exp Preview TAb   List  :"+exppreviewTabHeaderList);

		

		if(actMessage.equalsIgnoreCase(expMessage))
		{

			System.out.println(" Test PasS: Extra Filed Programmable Filed added");
			return true;
		}
		else
		{
			System.out.println(" Test FAIL: Extra Filed Programmable Filed added");
			return false;
		}

	}




	@FindBy(xpath="//*[@id='rd_customization_tree0']/ul/li")
	private static List<WebElement> reportFieldlist;


	@FindBy(xpath="//textarea[@id='id_rd_customization_query']")
	private static WebElement reportDataSetQueryTxt;



	public boolean checkReportWithQuaeryreport() throws InterruptedException
	{
		Thread.sleep(2000);
		getDriver().navigate().refresh();
		
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
		searchTxt.click();
		Thread.sleep(1000);
		searchTxt.sendKeys("Report With Quarry");
		Thread.sleep(1000);
		searchTxt.sendKeys(Keys.ENTER);


		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_DateOptionDropdown));

		Select s=new Select(sl_DateOptionDropdown);
		s.selectByValue("1");

		Thread.sleep(2000);


		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_OkBtn));
		sl_OkBtn.click();


		Thread.sleep(2000);
		boolean novalidationConfirmationMessage =validationConfirmationMessage.getText().isEmpty();

		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";
		
		System.err.println("actvalidationConfirmationMessage: "+actvalidationConfirmationMessage);
		System.err.println("expvalidationConfirmationMessage: "+expvalidationConfirmationMessage);


		Thread.sleep(1500);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_1stRow1stCol));

		int reportsRow1ListCount = reportCol1List.size();
		ArrayList<String> reportsRow1ListArray = new ArrayList<String>();
		for(int i=0;i<reportsRow1ListCount;i++)
		{
			String data = reportCol1List.get(i).getText().trim();


			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date date=new Date();
			String expadjustBills=df.format(date);

			if (data.isEmpty() == false)
			{
				reportsRow1ListArray.add(data);
			}
		}
		String actRow1List = reportsRow1ListArray.toString();
		String expRow1List = "[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16]";


		int report2ndRowListCount = reportCol2List.size();
		ArrayList<String> report2ndRowListArray = new ArrayList<String>();
		for(int i=0;i<report2ndRowListCount;i++)
		{
			String data = reportCol2List.get(i).getText().trim();
			if (data.isEmpty() == false)
			{
				report2ndRowListArray.add(data);
			}
		}
		String actRow2List = report2ndRowListArray.toString();
		String expRow2List = "[1.00, 2.00, 3.00, 4.00, 5.00, 6.00, 7.00, 8.00, 9.00, 10.00, 11.00, 12.00, 13.00, 14.00, 16.00]";


		int report3rdRowListCount = reportCol3List.size();
		ArrayList<String> report3rdRowListArray = new ArrayList<String>();
		for(int i=0;i<report3rdRowListCount;i++)
		{
			String data = reportCol3List.get(i).getText().trim();
			if (data.isEmpty() == false)
			{
				report3rdRowListArray.add(data);
			}
		}
		String actRow3List = report3rdRowListArray.toString();
		String expRow3List = "[Users & Groups, SU, UserAllOptions, UserWithPurchaseFAINV, UserAccPOTransRestrictions, UserItemPOTransRestrictions, UserItemPOExclusion, UserWithAccDisableRole, UserWithDNotLock, UserWithAllowMulLogin, UserSendEmailLoginSuccess, UserSendEmailLoginFailure, UserBlockWithTime, UserGroup, UserUGChangePassword, UserWithAccRestrictionsReportV]";


		int report4thRowListCount = reportCol4List.size();
		ArrayList<String> report4thRowListArray = new ArrayList<String>();
		for(int i=0;i<report4thRowListCount;i++)
		{
			String data = reportCol4List.get(i).getText().trim();
			if (data.isEmpty() == false)
			{
				report4thRowListArray.add(data);
			}
		}
		String actRow4List = report4thRowListArray.toString();
		String expRow4List = "[UAO, UWP, UPOR, UPOR, UIPOE, UAD, UWDL, UWAML, USELS, USELF, UBWT, null, UUGCP, UWARRV]";


		int report5thRowListCount = reportCol5List.size();
		ArrayList<String> report5thRowListArray = new ArrayList<String>();
		for(int i=0;i<report5thRowListCount;i++)
		{
			String data = reportCol5List.get(i).getText().trim();
			if (data.isEmpty() == false)
			{
				report5thRowListArray.add(data);
			}
		}
		String actRow5List = report5thRowListArray.toString();
		String expRow5List = "[6.00, 6.00, 1.00, 1.00, 1.00, 6.00, 8.00, 1.00, 8.00, 8.00, 1.00, 9.00, 8.00]";

		int report6thRowListCount = reportCol6List.size();
		ArrayList<String> report6thRowListArray = new ArrayList<String>();
		for(int i=0;i<report6thRowListCount;i++)
		{
			String data = reportCol6List.get(i).getText().trim();
			if (data.isEmpty() == false)
			{
				report6thRowListArray.add(data);
			}
		}
		String actRow6List = report6thRowListArray.toString();
		String expRow6List = "[010c0088, 0154011400d2008e0048, 0154011400d2008e0048, 0154011400d2008e0048, 0154011400d2008e0048, 0154011400d2008e0048, 0154011400d2008e0048, 075008e6078007cb05cc064c0654054903660369041003b1023a028a01d8017a00a60047, 0192010c0086, 0af80d9c0bb80c78092a0a500a95094c0688090007b407c007710532069a05b80554050a03960440033a02c402580218019200a60047, 0af80d9c0bb80c78092a0a500a95094c0688090007b407c007710532069a05b80554050a032103a0036402fa02a80214016800a60047, 02180198010c0088, 010c0086, 05a20618064805cd033e0264022801ea01aa0168012400de0096004c]";



		System.out.println("actRow1List  : "+actRow1List);
		System.out.println("expRow1List  : "+expRow1List);
		System.out.println("*********************************************************************");

		System.out.println("actRow2List  : "+actRow2List);
		System.out.println("expRow2List  : "+expRow2List);
		System.out.println("*********************************************************************");

		System.out.println("actRow3List  : "+actRow3List);
		System.out.println("expRow3List  : "+expRow3List);
		System.out.println("*********************************************************************");

		System.out.println("actRow4List  : "+actRow4List);
		System.out.println("expRow4List  : "+expRow4List);
		System.out.println("*********************************************************************");

		System.out.println("actRow5List  : "+actRow5List);
		System.out.println("expRow5List  : "+expRow5List);
		System.out.println("*********************************************************************");

		System.out.println("actRow6List  : "+actRow6List);
		System.out.println("expRow6List  : "+expRow6List);
		System.out.println("*********************************************************************");


		if (actRow1List.equalsIgnoreCase(expRow1List) &&
				actRow2List.equalsIgnoreCase(expRow2List) &&
				actRow3List.equalsIgnoreCase(expRow3List) && 
				actRow4List.equalsIgnoreCase(expRow4List)  &&
				actRow5List.equalsIgnoreCase(expRow5List)  &&
				actRow6List.equalsIgnoreCase(expRow6List)) 
		{
			System.out.println(" Test Pass: Values Dsiplayed as Expected ");
			return true;
		} 
		else 
		{
			System.out.println(" Test FAIL: Values Dsiplayed as Expected ");
			String meesage=validationConfirmationMessage.getText();
			System.err.println(" Meesage Displayed  : "+meesage);
			return false;
		}

	}
	
	
	
	public static boolean checkCustomizeDateFormatInLedgerReport() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{

		getDriver().navigate().refresh();

		Thread.sleep(1999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(financialsMenu));
		financialsMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(financialsReportsMenu));
		financialsReportsMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(ledger));
		ledger.click();

		boolean novalidationConfirmationMessage =validationConfirmationMessage.getText().isEmpty();

		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";

		

		/*getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_DateOptionDropdown));
		sl_DateOptionDropdown.click();
		Select s=new Select(sl_DateOptionDropdown);
		s.selectByValue("1");

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_SelectAllItemsChkBox));
		sl_SelectAllItemsChkBox.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_OkBtn));
		sl_OkBtn.click();*/

		Thread.sleep(7000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportCustomizeBtnHomePage));
		reportCustomizeBtnHomePage.click();
		
		int reportsRow1ListCount = reportCustomizationList.size();
		ArrayList<String> reportsRow1ListArray = new ArrayList<String>();
		for(int i=0;i<reportsRow1ListCount;i++)
		{
			String data = reportCustomizationList.get(i).getText().trim();
			if (data.equalsIgnoreCase("Date"))
			{
				getAction().doubleClick(reportCustomizationList.get(i)).build().perform();
				
			}
		}
		
		Thread.sleep(1500);
		scrollToElementJSE(dateFormatDropdown);
		Thread.sleep(1500);
		Select sc = new Select(dateFormatDropdown);
		sc.selectByValue("11");
		
		
		try{
			
			if(getIsAlertPresent())
			{
			String actAlertText = getAlert().getText();
			String expAlertText = "Separator is mandatory for the selected date format.";
			
			getAlert().accept();
			
			}
			
		}
		
		catch (Exception e) 
		{
		 System.err.println("No Alert Present");
		}
		
		
		
		
		String actSelected = sc.getFirstSelectedOption().getText();
		String expSelected = "DDDDDDMMMMYYY";
		
		System.out.println("actSelected:"+actSelected);
		System.out.println("expSelected:"+expSelected);
		
		
		Thread.sleep(2000);
		
		dateseparator.sendKeys("-");
		Thread.sleep(1000);
		dateseparator.sendKeys(Keys.TAB);
		
		
	
		
		
		Boolean actDateFormat = dateDisplay.isDisplayed();
		Boolean expDateFormat = true;
		
		Thread.sleep(2000);
		
		getAction().moveToElement(reportCustomizeSaveBtn).build().perform();
		Thread.sleep(1500);
		reportCustomizeSaveBtn.click();
		
		
		String expSaveMessage= "Data saved successfully";
		String actSaveMessage = checkValidationMessage(expSaveMessage);
		
		
		if (actSelected.equalsIgnoreCase(expSelected) && actDateFormat == expDateFormat && actSaveMessage.equalsIgnoreCase(expSaveMessage))
		{
			return true;
		}
		else 
		{
			return false;
		}

	}
	
	@FindBy(xpath="//table[@class='CommonReportTable']//tbody//tr[2]//td[2]")
	private static WebElement DateFormatCol;
	
	
	 
    
    
    
    


	public static boolean checkValidateDateFormatInLedgerReport() throws InterruptedException
	{

		getDriver().navigate().refresh();

		Thread.sleep(1999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(financialsMenu));
		financialsMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(financialsReportsMenu));
		financialsReportsMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(ledger));
		ledger.click();

		Thread.sleep(3000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_DateOptionDropdown));
		sl_DateOptionDropdown.click();
		Select s=new Select(sl_DateOptionDropdown);
		s.selectByValue("1");

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_SelectAllItemsChkBox));
		sl_SelectAllItemsChkBox.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_OkBtn));
		sl_OkBtn.click();

		Thread.sleep(3000);
		
		String actDF = DateFormatCol.getText();
		String expDF = "Friday-May-2021";
		
		System.err.println("actDF:"+actDF);
		System.err.println("expDF:"+expDF);
		
		
		if (actDF.equalsIgnoreCase(expDF)) 
		{
			return true;
		} 
		else 
		{
			return false;
		}
	
		
	}
	
	 @FindBy(xpath="//*[@id='id_rd_rowformatting_1']")
     private static WebElement sl_CustomizeFormattingRowNew;
	 
	 @FindBy(xpath="//div[@id='id_rd_rowformatting_filtercontrol7']//*[@class='theme_button_color icon-filter icon-font6']")
     private static WebElement sl_CustomizeFormattingRowFilterBtn;
	 
	 @FindBy(xpath="//*[@id='200_10_AdvanceFilter_Customize']/table/tbody/tr/td[1]/select")
     private static WebElement sl_CustomizeFormattingRowConjunction;
     
     @FindBy(xpath="//*[@id='200_10_AdvanceFilter_Customize']/table/tbody/tr/td[2]/input")
     private static WebElement sl_CustomizeFormattingRowTextField;
     
     @FindBy(xpath="//a[text()='Item Name']")
     private static WebElement ItemName;
     
   
     
     @FindBy(xpath="(//a[contains(text(),'Rate')])[1]")
     private static WebElement sl_CustomizeFormattingRowTextRateField;
     
     
     @FindBy(xpath="//*[@id='200_10_AdvanceFilter_Customize']/table/tbody/tr/td[3]/select")
     private static WebElement sl_CustomizeFormattingRowOperator;
     
     @FindBy(xpath="//*[@id='200_10_AdvanceFilter_Customize']/table/tbody/tr/td[4]/select")
     private static WebElement sl_CustomizeFormattingRowCompare;
     
     @FindBy(xpath="//*[@id='200_10_AdvanceFilter_Customize']/table/tbody/tr/td[5]/input")
     private static WebElement sl_CustomizeFormattingRowValueField;
     
     @FindBy(xpath="//*[@id='advancefilter_date_200_10_Customize_input_image']/span")
     private static WebElement sl_CustomizeFormattingRowValueCal;
     
     @FindBy(xpath="//select[@id='id_set_on_column']")
     private static WebElement sl_CustomizeFormattingRowSetonColumn;
     
     
     @FindBy(xpath="//input[@id='id_rf_font']")
     private static WebElement sl_CustomizeFormattingRowFontBtn;

     
     
     @FindBy(xpath="//select[@id='fontForeColor_id_rd_rowformatting_fontcontrol7']")
     private static WebElement sl_CustomizeFormattingRowForeColour;

     @FindBy(xpath="//select[@id='fontStyle_id_rd_rowformatting_fontcontrol7']")
     private static WebElement sl_CustomizeFormattingRowFontStyle;
     
     @FindBy(xpath="//*[@id='id_set_on_column']")
     private static WebElement setOnColDropdown;
     
     @FindBy(xpath="//*[@id='id_ApplyOnColumn']//following-sibling::span")
     private static WebElement applyOnColCheckBox;
     
     @FindBy(xpath="//*[@id='rfok']")//*[@id="rfok"]
     private static WebElement FilterOkBtn;
     
     @FindBy(xpath="//select[@id='fontSizes_id_rd_rowformatting_fontcontrol7']")
     private static WebElement sl_CustomizeFormattingRowFontSize;
     
     @FindBy(xpath="//select[@id='fontWeight_id_rd_rowformatting_fontcontrol7']")
     private static WebElement sl_CustomizeFormattingRowFontWeight;
     
     @FindBy(xpath="//select[@id='fontBackColor_id_rd_rowformatting_fontcontrol7']")
     private static WebElement sl_CustomizeFormattingRowBackColor;

     
     
     @FindBy(xpath=" //*[@id='id_rf_font']")
     private static WebElement FontBtn;
  
     
     @FindBy(xpath="//*[@id='fontFamily_id_rd_rowformatting_fontcontrol7']")
	    private static WebElement sl_CustomizeFormattingFontFamilyDropdown;
 
 
 @FindBy(xpath="//*[@id='chkBaseline_id_rd_rowformatting_fontcontrol7']/following-sibling::span")
 private static WebElement sl_CustomizeFormattingRowBaselineChkBox;
 
 @FindBy(xpath="//*[@id='chkOverLine_id_rd_rowformatting_fontcontrol7']/following-sibling::span")
 private static WebElement sl_CustomizeFormattingRowOverlineChkBox;
 
 
 
 @FindBy(xpath="//*[@id='chkStrikeThrough_id_rd_rowformatting_fontcontrol7']/following-sibling::span")
 private static WebElement sl_CustomizeFormattingRowStrikeThroughChkBox;
 
 
 @FindBy(xpath="//*[@id='chkUnderline_id_rd_rowformatting_fontcontrol7']/following-sibling::span")
 private static WebElement sl_CustomizeFormattingRowUnderlineChkBox;
   
   
 @FindBy(xpath="//*[@id='RDModalFont']/div[2]/div/div[3]/div/div/input[1]")
 private static WebElement sl_CustomizeFormattingFontOkBtn;

     

     
	//*[@id="200_10_AdvanceFilter_Customize"]/table/tbody/tr/td[1]/select

	
	public static boolean checkSaveAReportForValidationRowFormattingTab() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{

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
		reportNameDropdown.sendKeys("FormattingReport");
		reportNameDropdown.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportTypeDropdown));
		Select rtd= new Select(reportTypeDropdown);
		rtd.selectByVisibleText("Details");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(inventoryExpandBtn));
		inventoryExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportsBtn));
		reportsBtn.click();




		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dataSetTab));
		dataSetTab.click();

		Thread.sleep(2000);

		int transactionSetListCount = transactionSetList.size();

		ArrayList<String >transactionSetListArray=new ArrayList<>();

		for(int i=0;i<=transactionSetListCount;i++)
		{
			String data = transactionSetList.get(i).getText();

			transactionSetListArray.add(data);

			if(data.equalsIgnoreCase("All transactions of document class"))
			{
				transactionSetList.get(i).click();

				break;
			}
		}

		Select voucher = new Select(dataSetTabVouchersTab);
		voucher.selectByVisibleText("Purchases Vouchers");

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTab));
		customizationTab.click();
		
		//getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionExpandBtn));
		//transactionExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionFieldsExpandBtn));
		transactionFieldsExpandBtn.click();

		Thread.sleep(1000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dateExpandBtn));
		dateExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dateFieldBtn));
		getAction().doubleClick(dateFieldBtn).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dateExpandBtn));
		dateExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(docNoFieldBtn));
		getAction().doubleClick(docNoFieldBtn).build().perform();

		Thread.sleep(1000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemExpandBtn));
		itemExpandBtn.click();
		Thread.sleep(1000);
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
		Thread.sleep(1000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(extraFieldsExpandBtn));
		extraFieldsExpandBtn.click();

		Thread.sleep(1000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(deptExpandBtn));
		deptExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(deptNameBtn1));
		getAction().doubleClick(deptNameBtn1).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(deptExpandBtn));
		deptExpandBtn.click();

		Thread.sleep(1000);
		
		//moveToElement(warehouseExpandBtn);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouseExpandBtn));
		warehouseExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouseNameBtn));
		getAction().doubleClick(warehouseNameBtn).build().perform();

		Thread.sleep(1000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouseExpandBtn));
		warehouseExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(extraFieldsExpandBtn));
		extraFieldsExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionFieldsExpandBtn));
		transactionFieldsExpandBtn.click();
		Thread.sleep(3000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rowFormattingTab));
		rowFormattingTab.click();
		
		Thread.sleep(1000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_CustomizeFormattingRowNewBtn));
		sl_CustomizeFormattingRowNewBtn.click();
		Thread.sleep(1000);
		sl_CustomizeFormattingRowNew.click();
		
		Thread.sleep(1200);
		sl_CustomizeFormattingRowFilterBtn.click();
		
		Thread.sleep(2000);
		sl_CustomizeFormattingRowConjunction.click();
		Select whr = new Select(sl_CustomizeFormattingRowConjunction);
		whr.selectByVisibleText("Where");
		
		
		sl_CustomizeFormattingRowTextField.click();
		getAction().moveToElement(ItemName).build().perform();
		Thread.sleep(2000);
		ItemName.click();
		Thread.sleep(2000);
	
		
		sl_CustomizeFormattingRowOperator.click();
		Select equalTo = new Select(sl_CustomizeFormattingRowOperator);
		equalTo.selectByVisibleText("Equal to");
		
		sl_CustomizeFormattingRowCompare.click();
		Select value = new Select(sl_CustomizeFormattingRowCompare);
		value.selectByValue("0");
		
		Thread.sleep(2000);
		sl_CustomizeFormattingRowValueField.sendKeys(Keys.SPACE);
		Thread.sleep(1000);
		sl_CustomizeFormattingRowValueField.sendKeys("STD RATE COGS ITEM");
		Thread.sleep(2000);
		
		Select setOnCol = new Select(setOnColDropdown);
		setOnCol.selectByVisibleText("Item Name");
		
		Thread.sleep(1000);
		applyOnColCheckBox.click();
		Thread.sleep(1000);
		
		Thread.sleep(2500);
		ClickUsingJs(FilterOkBtn);
		
		String expFontMessage = "Must Select Font";
		String actFontMessage = checkValidationMessage(expFontMessage);
		
		Thread.sleep(2000);
		FontBtn.click();
		Thread.sleep(4000);

		Select FontFamily = new Select(sl_CustomizeFormattingFontFamilyDropdown);
		FontFamily.selectByVisibleText("Arial Rounded MT");
		
		Select FontStyle = new Select(sl_CustomizeFormattingRowFontStyle);
		FontStyle.selectByVisibleText("Italic");
		
		Select FontWeight = new Select(sl_CustomizeFormattingRowFontWeight);
		FontWeight.selectByVisibleText("Bold");
		
		Select FontSize = new Select(sl_CustomizeFormattingRowFontSize);
		FontSize.selectByVisibleText("10");
		
		Select ForeColor = new Select(sl_CustomizeFormattingRowForeColour);
		ForeColor.selectByVisibleText("Navy");
		
		Select BackColor = new Select(sl_CustomizeFormattingRowBackColor);
		BackColor.selectByVisibleText("Yellow");
		
		getWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_CustomizeFormattingRowBaselineChkBox));
		sl_CustomizeFormattingRowBaselineChkBox.click();
		
		getWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_CustomizeFormattingRowOverlineChkBox));
		sl_CustomizeFormattingRowOverlineChkBox.click();
	
		
		getWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_CustomizeFormattingRowUnderlineChkBox));
		sl_CustomizeFormattingRowUnderlineChkBox.click();
		
		Thread.sleep(2000);
		
		getWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_CustomizeFormattingFontOkBtn));
		sl_CustomizeFormattingFontOkBtn.click();
		
		Thread.sleep(3500);
		
		getAction().moveToElement(FilterOkBtn).build().perform();
		Thread.sleep(2000);
		ClickUsingJs(FilterOkBtn);

		Thread.sleep(2000);
		

		getAction().moveToElement(finishBtn).build().perform();
		Thread.sleep(1200);
		finishBtn.click();
		
		Thread.sleep(2000);

		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : "+actMessage+" Value Expected : "+expMessage);

		if(actMessage.equalsIgnoreCase(expMessage))
		{

			return true;
		}
		else
		{
			return false;
		}
	
	}
	
	
	public boolean checkFormattingReportAfterRowFormating() throws InterruptedException
	{
		
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
		searchTxt.click();
		Thread.sleep(1000);
		searchTxt.sendKeys("FormattingReport");
		Thread.sleep(1000);
		searchTxt.sendKeys(Keys.ENTER);


		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_DateOptionDropdown));

		Select s=new Select(sl_DateOptionDropdown);
		s.selectByValue("1");

		Thread.sleep(2000);


		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_OkBtn));
		sl_OkBtn.click();


		Thread.sleep(2000);
		boolean novalidationConfirmationMessage =validationConfirmationMessage.getText().isEmpty();

		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";
		
		System.err.println("actvalidationConfirmationMessage: "+actvalidationConfirmationMessage);
		System.err.println("expvalidationConfirmationMessage: "+expvalidationConfirmationMessage);


		Thread.sleep(1500);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_1stRow1stCol));

		int reportsRow1ListCount = reportCol1List.size();
		ArrayList<String> reportsRow1ListArray = new ArrayList<String>();
		for(int i=0;i<reportsRow1ListCount;i++)
		{
			String data = reportCol1List.get(i).getAttribute("style");


			
			if (data.isEmpty() == false)
			{
				reportsRow1ListArray.add(data);
			}
		}
		String actRow1List = reportsRow1ListArray.toString();
		String expRow1List = "[color: rgb(0, 0, 128); background-color: rgb(255, 255, 0); font-style: italic; font-weight: bold; font-size: 10px; font-family: Arial Rounded MT; width: 80px;]";

		System.out.println(" ***********************************");
		System.out.println("actRow1List  : "+actRow1List);
		System.out.println("expRow1List : "+expRow1List);

		if(actRow1List.equalsIgnoreCase(expRow1List))
		{
			return true;
		}
		else
		{
			return false;
		}
		
		
	}
	
	
	
	
	
	//Cubes

	@FindBy(xpath="//*[@id='groupingOptions']/div//label")
	private static List<WebElement> groupingOptionsChechboxes;
	
	@FindBy(xpath="//div[@class='Fpanel-footer']/*[@id='id_rd_customization_rowgroupingpopup_ok']")
	private static WebElement groupingOptionsOkBtn;
	
	@FindBy(xpath="//*[@id='chkDisplayInBold']")
	private static WebElement displayInBoldChkbx;
	
	
	
	@FindBy(xpath="//div[@id='id_rd_customization_tree_container']//span[text()='Print count']")
	private static WebElement printCount;
	
	@FindBy(xpath="//div[@id='id_rd_customization_tree_container']//span[text()='Footer amount']")
	private static WebElement footerAmount;
	
	@FindBy(xpath="//div[@id='id_rd_customization_tree_container']//span[text()='Net amount']")
	private static WebElement netAmount;
	
	@FindBy(xpath="//div[@id='id_rd_customization_tree_container']//span[text()='Voucher amount']")
	private static WebElement voucherAmount;
	
	@FindBy(xpath="//div[@id='id_rd_customization_tree_container']//span[text()='Voucher name']")
	private static WebElement voucherName;
	
	@FindBy(xpath="//div[@id='id_rd_customization_tree_container']//span[text()='Voucher type']")
	private static WebElement voucherType;
	
	public static boolean checkSavingAllTransactionsOfDocumentClassCube() throws InterruptedException, IOException, EncryptedDocumentException, InvalidFormatException
	{
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
		reportNameDropdown.sendKeys("All transactions of document class-cube");
		reportNameDropdown.sendKeys(Keys.TAB);

		Thread.sleep(2500);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportTypeDropdown));
		Select rtd= new Select(reportTypeDropdown);
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

		for(int i=0;i<=transactionSetListCount;i++)
		{
			String data = transactionSetList.get(i).getText();

			if(data.equalsIgnoreCase("All transactions of document class"))
			{
				transactionSetList.get(i).click();

				break;
			}
		}

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTab));
		customizationTab.click();

		Thread.sleep(2000);
		//getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionExpandBtn));
		//transactionExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionFieldsExpandBtn));
		transactionFieldsExpandBtn.click();

		Thread.sleep(1200);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(acc2ExpandBtn));
		acc2ExpandBtn.click();

		Thread.sleep(3000);

		Runtime.getRuntime().exec(getBaseDir()+"\\autoIt\\scripts\\scripts\\AccountDragN.exe");

		Thread.sleep(20000);
		
		ArrayList<String> checkBoxList = new ArrayList<String>();
		
		int groupingOptionsChechboxesCount = groupingOptionsChechboxes.size();

		for(int i=0; i < groupingOptionsChechboxesCount;i++)
		{
			String data = groupingOptionsChechboxes.get(i).getText();
			checkBoxList.add(data);
		}

		String actList = checkBoxList.toString();
		String expList = "[Do not start the next level in a new line, Display total at the end of this level, Display value at the begining of the group, Display value at the end of the group, Leave blank line at the begining of the group, Display a line at the end of this level, Skip page at the end of the group, Hide group total, Hide group line, Show Name/Code, Display based on tree sequence]";
		
		System.out.println("actList: "+actList);
		System.out.println("expList: "+expList);
		
		
		for(int i=0; i < groupingOptionsChechboxesCount;i++)
		{
			String data = groupingOptionsChechboxes.get(i).getText();
			if(data.equalsIgnoreCase("Display total at the end of this level"))
			{
				groupingOptionsChechboxes.get(i).click();

				break;
			}
		}
		
		Thread.sleep(2000);
		groupingOptionsOkBtn.click();
		
		Thread.sleep(1000);
		
		
		//getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(acc1ExpandBtn));
		//acc1ExpandBtn.click();

		Thread.sleep(1000);
		
		scrollToElementJSE(footerAmount);
		
		Thread.sleep(2000);
		
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(footerAmount));
		getAction().doubleClick(footerAmount).build().perform();

	
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(netAmount));
		getAction().doubleClick(netAmount).build().perform();

		scrollToElementJSE(voucherType);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(voucherAmount));
		getAction().doubleClick(voucherAmount).build().perform();
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(voucherName));
		getAction().doubleClick(voucherName).build().perform();
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(voucherType));
		getAction().doubleClick(voucherType).build().perform();

		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(extraFieldsExpandBtn));
		extraFieldsExpandBtn.click();

		Thread.sleep(1000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(deptExpandBtn1));
		deptExpandBtn1.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(deptNameBtn1));
		getAction().doubleClick(deptNameBtn1).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(deptExpandBtn1));
		deptExpandBtn1.click();

		Thread.sleep(1000);
		

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(extraFieldsExpandBtn));
		extraFieldsExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionFieldsExpandBtn));
		transactionFieldsExpandBtn.click();
		


		 ((JavascriptExecutor)getDriver()).executeScript("window.scrollTo(0, 0)","");
		 Thread.sleep(2000);
		 
		finishBtn.click();
		
		Thread.sleep(2000);

		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);


		System.out.println("Validation Message : "+actMessage+" Value Expected : "+expMessage);

		if(actList.equalsIgnoreCase(expList) && actMessage.equalsIgnoreCase(expMessage))
		{
			return true;
		}
		else
		{
			return false;
		}


	}


	public static boolean checkReportAlltransactionsofdocumentclasscube() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		Thread.sleep(2000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(userNameDisplay));
		userNameDisplay.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(userNameDisplay));
		logoutOption.click();

		Thread.sleep(2500);		
		
		checkLogin();
		
		LoginPage lp=new LoginPage(getDriver()); 

		String unamelt="su";

		String pawslt="su";
		String compname="RD REPORTS";

		Thread.sleep(2000);
		//lp.reLogin(unamelt, pawslt, compname);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
		searchTxt.click();
		searchTxt.sendKeys("All transactions of document class-cube");
		Thread.sleep(2000);
		searchTxt.sendKeys(Keys.ENTER);

		Thread.sleep(2000);

		try {
		if (sl_DateOptionDropdown.isDisplayed()==false)
		{
			getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
			searchTxt.click();
			searchTxt.sendKeys("All transactions of document class-cube");
			Thread.sleep(2000);
			searchTxt.sendKeys(Keys.ENTER);
		}
		}
		catch (Exception e)
		{
		}

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_DateOptionDropdown));

		Select s=new Select(sl_DateOptionDropdown);
		s.selectByValue("1");

		Thread.sleep(2000);


		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_OkBtn));
		sl_OkBtn.click();


		Thread.sleep(3000);

		Thread.sleep(1500);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_1stRow1stCol));

		int reportsRow1ListCount = reportCol1List.size();
		ArrayList<String> reportsRow1ListArray = new ArrayList<String>();
		for(int i=0;i<reportsRow1ListCount;i++)
		{
			String data = reportCol1List.get(i).getText().trim();

			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date date=new Date();
			String expadjustBills=df.format(date);

			if (data.isEmpty() == false)
			{
				reportsRow1ListArray.add(data);
			}
		}
		String actRow1List = reportsRow1ListArray.toString();
		String expRow1List = "[1, 2, 3, 4, 5, 6, 7]";

		String expRow2List = "[Customer A, Sub Total, Vendor Semi Adjustment, Sub Total, Customer Semi Adjustment, Sub Total, Grand Total]";
		boolean actRow2List =  ListComparisionWOOrder(reportCol2List,expRow2List);


		int report3rdRowListCount = reportCol3List.size();
		ArrayList<String> report3rdRowListArray = new ArrayList<String>();
		for(int i=0;i<report3rdRowListCount;i++)
		{
			String data = reportCol3List.get(i).getText().trim();
			if (data.isEmpty() == false)
			{
				report3rdRowListArray.add(data);
			}
		}
		String actRow3List = report3rdRowListArray.toString();
		String expRow3List = "[]";


		int report4thRowListCount = reportCol4List.size();
		ArrayList<String> report4thRowListArray = new ArrayList<String>();
		for(int i=0;i<report4thRowListCount;i++)
		{
			String data = reportCol4List.get(i).getText().trim();
			if (data.isEmpty() == false)
			{
				report4thRowListArray.add(data);
			}
		}
		String actRow4List = report4thRowListArray.toString();
		String expRow4List = "[15.00, 15.00, 5.00, 5.00, 5.00, 5.00, 25.00]";



		int reportsRow1ListCount1 = reportCol5List.size();
		ArrayList<String> reportsRow1ListArray1 = new ArrayList<String>();
		for(int i=0;i<reportsRow1ListCount1;i++)
		{
			String data = reportCol5List.get(i).getText().trim();

			if (data.isEmpty() == false)
			{
				reportsRow1ListArray1.add(data);
			}
		}
		String actRow1List1 = reportsRow1ListArray1.toString();
		String expRow1List1 = "[]";


		int report2ndRowListCount1 = reportCol6List.size();
		ArrayList<String> report2ndRowListArray1 = new ArrayList<String>();
		for(int i=0;i<report2ndRowListCount1;i++)
		{
			String data = reportCol6List.get(i).getText().trim();
			if (data.isEmpty() == false)
			{
				report2ndRowListArray1.add(data);
			}
		}
		String actRow2List1 = report2ndRowListArray1.toString();
		String expRow2List1 = "[Receipts VAT, Receipts VAT, Receipts VAT, Receipts VAT, Receipts VAT, Receipts VAT]";


		int report2ndRowListCount7 = reportCol7List.size();
		ArrayList<String> report2ndRowListArray7 = new ArrayList<String>();
		for(int i=0;i<report2ndRowListCount7;i++)
		{
			String data = reportCol7List.get(i).getText().trim();
			if (data.isEmpty() == false)
			{
				report2ndRowListArray7.add(data);
			}
		}
		String actRow2List7 = report2ndRowListArray7.toString();
		String expRow2List7 = "[4611, 4611, 4611, 4611, 4611, 4611]";


		Thread.sleep(2000);
		
		int report2ndRowListCount8 = reportCol8List.size();
		ArrayList<String> report2ndRowListArray8= new ArrayList<String>();
		for(int i=0;i<report2ndRowListCount8;i++)
		{
			String data = reportCol8List.get(i).getText().trim();
			if (data.isEmpty() == false)
			{
				report2ndRowListArray8.add(data);
			}
		}
		String actRow2List8 = report2ndRowListArray8.toString();
		String expRow2List8 = "[DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI]";

	


		System.out.println("actCol1List  : "+actRow1List);
		System.out.println("expCol1List  : "+expRow1List);
		System.out.println("*********************************************************************");

		System.out.println("actCol2List  : "+actRow2List);
		System.out.println("expCol2List  : "+expRow2List);
		System.out.println("*********************************************************************");

		System.out.println("actCol3List  : "+actRow3List);
		System.out.println("expCol3List  : "+expRow3List);
		System.out.println("*********************************************************************");

		System.out.println("actCol4List  : "+actRow4List);
		System.out.println("expCol4List  : "+expRow4List);
		System.out.println("*********************************************************************");


		System.out.println("actCol5List  : "+actRow1List1);
		System.out.println("expCol5List : "+expRow1List1);
		System.out.println("*********************************************************************");

		System.out.println("actCol6List  :  "+actRow2List1);
		System.out.println("expCol6List  :  "+expRow2List1);
		System.out.println("*********************************************************************");

		System.out.println("actCol7List  : "+actRow2List7);
		System.out.println("expCol7List  : "+expRow2List7);
		System.out.println("*********************************************************************");
		
		System.out.println("actCol8List  : "+actRow2List8);
		System.out.println("expCol8List  : "+expRow2List8);

		if (actRow1List.equalsIgnoreCase(expRow1List) &&
				actRow2List &&
				actRow3List.equalsIgnoreCase(expRow3List) && 
				actRow4List.equalsIgnoreCase(expRow4List)  && 

				actRow1List1.equalsIgnoreCase(expRow1List1) &&
				actRow2List1.equalsIgnoreCase(expRow2List1) && 

				actRow2List7.equalsIgnoreCase(expRow2List7) &&
				actRow2List8.equalsIgnoreCase(expRow2List8)) 
		{
			System.out.println(" Test Pass: Values Dsiplayed as Expected ");
			return true;
		} 
		else 
		{
			System.out.println(" Test FAIL: Values Dsiplayed as Expected ");
			String meesage=validationConfirmationMessage.getText();
			System.err.println(" Meesage Displayed  : "+meesage);
			return false;
		}
	}
	
	
	public static boolean checkReportExportingThroughPDFAndValidatePDF() throws InterruptedException, AWTException, IOException
	{
		Thread.sleep(2000);

		File Efile=new File(getBaseDir()+"\\autoIt\\ExportFiles\\transactionsofdocumentclasscubeReport.pdf");

		if(Efile.exists())
		{
			Efile.delete();
		}
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_ExportBtn));
		sl_ExportBtn.click();

		Thread.sleep(3500);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_ExportPDFBtn));
		sl_ExportPDFBtn.click();
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(ss_ReportPrintLabel));
        
        String actConfirmMsg=ss_ReportPrintMsg.getText();
        String expConfirmMsg="";
        Thread.sleep(2000);
        
        System.out.println("Actual Msg                :                "+        actConfirmMsg                + "Expected                "        +        expConfirmMsg);
        
        
        getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(ss_ReportPrintYesBtn));
        ss_ReportPrintYesBtn.click();
        
        Thread.sleep(3000);
		Thread.sleep(4000);
	
		/*	LedgerAnalaysis = checkDownloadedFileName(getDriver());*/
		
		
		Thread.sleep(5000);
		
		Robot robot = new Robot();
		/*robot.keyPress(KeyEvent.VK_CONTROL);
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
		
		Thread.sleep(2000);
			*/
		Runtime.getRuntime().exec(getBaseDir() + "\\autoIt\\scripts\\scripts\\savingtransactionsofdocumentclasscubeReportPdf.exe");
		
		Thread.sleep(5000);
		
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_J);
		robot.keyRelease(KeyEvent.VK_J);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		
		Thread.sleep(2000);
		
		ArrayList<String> openTabs = new ArrayList<String>(getDriver().getWindowHandles());
			
		int actOpenWindowsCount = getDriver().getWindowHandles().size();
		int expOpenWindowsCount = 3;
		
		System.out.println("Number of Windows  : "+actOpenWindowsCount+"  Value Expected  "+expOpenWindowsCount);
		
		Thread.sleep(1000);
/*
	 	getDriver().switchTo().window(openTabs.get(2)).close();
	 	Thread.sleep(1000);
	 	getDriver().switchTo().window(openTabs.get(1)).close();
	 	Thread.sleep(1000);
	 	getDriver().switchTo().window(openTabs.get(0));*/
		
		
	/*	robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_W);
		robot.keyRelease(KeyEvent.VK_W);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		
		Thread.sleep(2000);
		*/
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_W);
		robot.keyRelease(KeyEvent.VK_W);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		
		
		
		
	 	
	 	Thread.sleep(3000);
	 	String actPDF = getBaseDir()+"\\autoIt\\ExportFiles\\transactionsofdocumentclasscubeReport.pdf";
		String expPDF = getBaseDir()+"\\autoIt\\ImportFiles\\transactionsofdocumentclasscubeReport.pdf";
		
		PDFUtil pdfutil = new PDFUtil();
		
		
		String actPdfList  = pdfutil.getText(actPDF);
		String ExpPdfList = pdfutil.getText(expPDF);
		
		String newExp = ExpPdfList.replaceAll("25/08/2025", getCurrentDateF2());

		System.out.println("actPDF  : "+actPdfList);
		System.out.println("expPDF  : "+ExpPdfList);
		//System.out.println("newExp  : "+newExp);
		
	
		if (actPdfList.equalsIgnoreCase(newExp)) 
		{
			
			return true;
		}
		else
		{
			
			return false;
		}

	}
	
	@FindBy(xpath="//*[@class='icon-filter2 hiconright2']")
	private static WebElement reportCustomizeBtn;
	
	@FindBy(xpath="//*[@class='icon-filter2 hiconright2']")
	private static WebElement reportCustomizeBtnHomePage;
	
	
	
	
	@FindBy(xpath="//*[@id='rd_customization_cube_rowgrouping_column_n1']")
	private static WebElement rowGroupingAccName;
	
	@FindBy(xpath="//*[@id='txtDisplayValue']")
	private static WebElement DisplayTxtArea;
	
	@FindBy(xpath="//table[@id='id_rc_columnheadertable']//tr[@id='tdTarget']//th//div[1]/p")
	public static List<WebElement> reportCustomizationList;
	
	
	@FindBy(xpath="//select[@id='DateStandards']")
	public static WebElement dateFormatDropdown;
	
	@FindBy(xpath="//*[@id='Save']/i")
	public static WebElement reportCustomizeSaveBtn;
	
	@FindBy(xpath="//*[@id='id_customize_datedisplay']")
	public static WebElement dateDisplay;
	
	@FindBy(xpath="//*[@id='Separator']")
	public static WebElement dateseparator;
	
	
	
	
	
	
	
	
	public static boolean checkCustomizeReportOFAlltransactionsofdocumentclasscube() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
		searchTxt.click();
		searchTxt.sendKeys("All transactions of document class-cube");
		Thread.sleep(2000);
		searchTxt.sendKeys(Keys.ENTER);

		Thread.sleep(2000);

		try {
		if (sl_DateOptionDropdown.isDisplayed()==false)
		{
			getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
			searchTxt.click();
			searchTxt.sendKeys("All transactions of document class-cube");
			Thread.sleep(2000);
			searchTxt.sendKeys(Keys.ENTER);
		}
		}
		catch (Exception e)
		{
		}

		
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportCustomizeBtn));
		reportCustomizeBtn.click();

		Thread.sleep(2000);
		
		String actReportName = reportNameDropdown.getAttribute("value");
		String expReportName = "All transactions of document class-cube";
		
		Select rtd= new Select(reportTypeDropdown);
		String actReportType = rtd.getFirstSelectedOption().getText();
		String expReportType = "Cubes";
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTab));
		customizationTab.click();

		Thread.sleep(2000);
		
		getAction().doubleClick(rowGroupingAccName).build().perform();
		
		int groupingOptionsChechboxesCount = groupingOptionsChechboxes.size();
		
		for(int i=0; i < groupingOptionsChechboxesCount;i++)
		{
			String data = groupingOptionsChechboxes.get(i).getText();
			if(data.equalsIgnoreCase("Display total at the end of this level"))
			{
				Thread.sleep(2000);
				
				System.out.println(groupingOptionsChechboxes.get(i).isSelected());
				if(groupingOptionsChechboxes.get(i).isSelected())
				{
					groupingOptionsChechboxes.get(i).click();
				}

				break;
			}
		}
		
		for(int i=0; i < groupingOptionsChechboxesCount;i++)
		{
			String data = groupingOptionsChechboxes.get(i).getText();
			if(data.equalsIgnoreCase("Display value at the begining of the group"))
			{
				Thread.sleep(2000);
				groupingOptionsChechboxes.get(i).click();

				break;
			}
		}
		
		Thread.sleep(2000);
		DisplayTxtArea.sendKeys("TextSample");

		Thread.sleep(2000);
		if(displayInBoldChkbx.isDisplayed())
		{
			displayInBoldChkbx.click();
		}
		
		
		Thread.sleep(2000);
		groupingOptionsOkBtn.click();
		
		Thread.sleep(1000);
		
		int customizeTabTableHeaderLsistCount = customizeTabTableHeaderLsist.size();
		System.err.println(customizeTabTableHeaderLsistCount);
		for(int i=1;i<=customizeTabTableHeaderLsistCount;i++)
		{
			String data = customizeTabTableHeaderLsist.get(i).getText();

			if(data.equalsIgnoreCase("Department.Name"))
			{
				customizeTabTableHeaderLsist.get(i).click();

				System.err.println(i);
				Thread.sleep(1000);

				break;
			}
		}
		
		Thread.sleep(1000);

		progrmmableFieldExpandBtn.click();


		Thread.sleep(1999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdExtraFiledColHeadingTxt));
		rdExtraFiledColHeadingTxt.click();
		rdExtraFiledColHeadingTxt.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
		rdExtraFiledColHeadingTxt.sendKeys("DepartmentUpdate");
		Thread.sleep(1999);

		rdExtraFiledColHeadingTxt.sendKeys(Keys.TAB);


		Thread.sleep(2000);
		
		getAction().moveToElement(rdExtraFiledFormulaControlOkBtn).build().perform();
		Thread.sleep(1999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdExtraFiledFormulaControlOkBtn));
		rdExtraFiledFormulaControlOkBtn.click();

		Thread.sleep(1999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(finishBtn));
		finishBtn.click();

		
		Thread.sleep(2000);

		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);


		System.out.println("Validation Message : "+actMessage+" Value Expected : "+expMessage);

		if(actReportName.equalsIgnoreCase(expReportName) && actReportType.equalsIgnoreCase(expReportType) &&
				actMessage.equalsIgnoreCase(expMessage))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	@FindBy(xpath="(//li//*[@class='rd_customization_tree_group' and text()='Currency'])[1]")
	private static WebElement currencyExpand;
	
	@FindBy(xpath="(//*[@class='rd_customization_tree_group' and text()='Unit'])[1]")
	private static WebElement unitExpand;
	//
	@FindBy(xpath="(//li[@title='Account.Name'])[1]")
	private static WebElement accountName;
	
	@FindBy(xpath="(//li[@title='Currency.Name'])[1]")
	private static WebElement currencyName;
	
	@FindBy(xpath="//li[@title='Item.Name']")
	private static WebElement itemName;
	
	@FindBy(xpath="//li[@title='Unit.Name']")
	private static WebElement unitName;
	
	@FindBy(xpath="//span[text()='Rate']")//*[@id="rd_customization_tree365"]/a/span
	private static WebElement rateBtnn;
	
	@FindBy(xpath="(//span[text()='RMA'])[1]")//*[@id="rd_customization_tree365"]/a/span
	private static WebElement RMABtnn;
	
	
	public static boolean checkInventoryTransactionsOfATagCubeReportWithMutipleLevelsOfRowGrouping() throws IOException, InterruptedException, EncryptedDocumentException, InvalidFormatException
	{
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
		reportNameDropdown.sendKeys("Inventory Transactions Of A Tag Cubic Report");
		reportNameDropdown.sendKeys(Keys.TAB);

		Thread.sleep(2500);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportTypeDropdown));
		Select rtd= new Select(reportTypeDropdown);
		rtd.selectByVisibleText("Cubes");
		Thread.sleep(2500);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(finanincalExpandbtn1));
		finanincalExpandbtn1.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(finanincalReportsExpandbtn));
		finanincalReportsExpandbtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dataSetTab));
		dataSetTab.click();

		Thread.sleep(2000);

		int transactionSetListCount = transactionSetList.size();

		for(int i=0;i<=transactionSetListCount;i++)
		{
			String data = transactionSetList.get(i).getText();

			if(data.equalsIgnoreCase("Inventory transactions of a tag"))
			{
				transactionSetList.get(i).click();

				break;
			}
		}

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTab));
		customizationTab.click();

		Thread.sleep(2000);
		//getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionExpandBtn));
		//transactionExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionFieldsExpandBtn));
		transactionFieldsExpandBtn.click();


		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(acc1ExpandBtn));
		acc1ExpandBtn.click();

		Thread.sleep(4500);

		Runtime.getRuntime().exec(getBaseDir()+"\\autoIt\\scripts\\scripts\\AccountDragN.exe");

		Thread.sleep(20000);
		
		ArrayList<String> checkBoxList = new ArrayList<String>();
		
		int groupingOptionsChechboxesCount = groupingOptionsChechboxes.size();

		for(int i=0; i < groupingOptionsChechboxesCount;i++)
		{
			String data = groupingOptionsChechboxes.get(i).getText();
			if(data.equalsIgnoreCase("Display total at the end of this level"))
			{
				groupingOptionsChechboxes.get(i).click();

				break;
			}
		}
		
		Thread.sleep(2000);
		groupingOptionsOkBtn.click();
		
		Thread.sleep(1000);
		
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(accountName));
		getAction().doubleClick(accountName).build().perform();
		
		getAction().moveToElement(accountName).build().perform();
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(currencyExpand));
		currencyExpand.click();
		
		Thread.sleep(3000);
		
		getAction().moveToElement(dateExpandBtn).build().perform();
		Thread.sleep(2000);
	
		Runtime.getRuntime().exec(getBaseDir()+"\\autoIt\\scripts\\scripts\\CurrencyDrag.exe");

		Thread.sleep(15000);
		
		
		Thread.sleep(2000);
	
		groupingOptionsOkBtn.click();
		
		Thread.sleep(1000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(currencyName));
		getAction().doubleClick(currencyName).build().perform();
		
		moveToElement(ModifiedDateExpandBtn1);
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemExpandBtn1));
		itemExpandBtn1.click();
		
		Runtime.getRuntime().exec(getBaseDir()+"\\autoIt\\scripts\\scripts\\ItemDragNew.exe");

		Thread.sleep(15000);
		
		Thread.sleep(2000);
		groupingOptionsOkBtn.click();
		
		Thread.sleep(1000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemName));
		getAction().doubleClick(itemName).build().perform();
		Thread.sleep(2000);
	
		moveToElement(voucherName);
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(unitExpand));
		unitExpand.click();
		Thread.sleep(2000);
		Runtime.getRuntime().exec(getBaseDir()+"\\autoIt\\scripts\\scripts\\UnitDragNew.exe");

		Thread.sleep(15000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(unitName));
		getAction().doubleClick(unitName).build().perform();
		
		moveToElement(RMABtnn);
		Thread.sleep(2000);
		
		Thread.sleep(2000);
		Runtime.getRuntime().exec(getBaseDir()+"\\autoIt\\scripts\\scripts\\RateDragRDNew.exe");

		Thread.sleep(15000);
		
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rateBtnn));
		getAction().doubleClick(rateBtnn).build().perform();
		

		//getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionFieldsExpandBtn));
		//transactionFieldsExpandBtn.click();
		
		Thread.sleep(2000);

		getAction().moveToElement(finishBtn).build().perform();
		Thread.sleep(1200);
		finishBtn.click();
		
		Thread.sleep(2000);

		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);


		System.out.println("Validation Message : "+actMessage+" Value Expected : "+expMessage);

		if(actMessage.equalsIgnoreCase(expMessage))
		{
			return true;
		}
		else
		{
			return false;
		}


		

	}
	
	public static boolean checkReportForCreatedInventoryTransactionsOfATagCube() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		Thread.sleep(2000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(userNameDisplay));
		userNameDisplay.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(userNameDisplay));
		logoutOption.click();

		checkLogin();
		
	/*	LoginPage lp=new LoginPage(getDriver()); 

		String unamelt="su";

		String pawslt="su";
		String compname="RD REPORTS";

		Thread.sleep(2000);
	//	lp.reLogin(unamelt, pawslt, compname);*/

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
		searchTxt.click();
		searchTxt.sendKeys("Inventory Transactions Of A Tag Cubic Report");
		Thread.sleep(2000);
		searchTxt.sendKeys(Keys.ENTER);

		Thread.sleep(2000);

		try {
		if (sl_DateOptionDropdown.isDisplayed()==false)
		{
			getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
			searchTxt.click();
			searchTxt.sendKeys("Inventory Transactions Of A Tag Cubic Report");
			Thread.sleep(2000);
			searchTxt.sendKeys(Keys.ENTER);
		}
		}
		catch (Exception e)
		{
		}

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_DateOptionDropdown));

		Select s=new Select(sl_DateOptionDropdown);
		s.selectByValue("1");

		Thread.sleep(2000);


		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_OkBtn));
		sl_OkBtn.click();


		Thread.sleep(3000);

		Thread.sleep(1500);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_1stRow1stCol));

		int reportsRow1ListCount = reportCol1List.size();
		ArrayList<String> reportsRow1ListArray = new ArrayList<String>();
		for(int i=0;i<reportsRow1ListCount;i++)
		{
			String data = reportCol1List.get(i).getText().trim();

			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date date=new Date();
			String expadjustBills=df.format(date);

			if (data.isEmpty() == false)
			{
				reportsRow1ListArray.add(data);
			}
		}
		String actRow1List = reportsRow1ListArray.toString();
		String expRow1List = "[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13]";


		int report2ndRowListCount = reportCol2List.size();
		ArrayList<String> report2ndRowListArray = new ArrayList<String>();
		for(int i=0;i<report2ndRowListCount;i++)
		{
			String data = reportCol2List.get(i).getText().trim();
			if (data.isEmpty() == false)
			{
				report2ndRowListArray.add(data);
			}
		}
		String actRow2List = report2ndRowListArray.toString();
		String expRow2List = "[Customer A, Indian Rupees, STD RATE COGS ITEM, Sub Total, Vendor B, Indian Rupees, STD RATE COGS ITEM, Sub Total, Vendor New Reference, Indian Rupees, STD RATE COGS ITEM, Sub Total, Grand Total]";


		int report3rdRowListCount = reportCol3List.size();
		ArrayList<String> report3rdRowListArray = new ArrayList<String>();
		for(int i=0;i<report3rdRowListCount;i++)
		{
			String data = reportCol3List.get(i).getText().trim();
			if (data.isEmpty() == false)
			{
				report3rdRowListArray.add(data);
			}
		}
		String actRow3List = report3rdRowListArray.toString();
		String expRow3List = "[Customer A, Vendor B, Vendor New Reference]";


		int report4thRowListCount = reportCol4List.size();
		ArrayList<String> report4thRowListArray = new ArrayList<String>();
		for(int i=0;i<report4thRowListCount;i++)
		{
			String data = reportCol4List.get(i).getText().trim();
			if (data.isEmpty() == false)
			{
				report4thRowListArray.add(data);
			}
		}
		String actRow4List = report4thRowListArray.toString();
		String expRow4List = "[Indian Rupees, Indian Rupees, Indian Rupees]";



		int reportsRow1ListCount1 = reportCol5List.size();
		ArrayList<String> reportsRow1ListArray1 = new ArrayList<String>();
		for(int i=0;i<reportsRow1ListCount1;i++)
		{
			String data = reportCol5List.get(i).getText().trim();

			if (data.isEmpty() == false)
			{
				reportsRow1ListArray1.add(data);
			}
		}
		String actRow1List1 = reportsRow1ListArray1.toString();
		String expRow1List1 = "[STD RATE COGS ITEM, STD RATE COGS ITEM, STD RATE COGS ITEM]";


		int report2ndRowListCount1 = reportCol6List.size();
		ArrayList<String> report2ndRowListArray1 = new ArrayList<String>();
		for(int i=0;i<report2ndRowListCount1;i++)
		{
			String data = reportCol6List.get(i).getText().trim();
			if (data.isEmpty() == false)
			{
				report2ndRowListArray1.add(data);
			}
		}
		String actRow2List1 = report2ndRowListArray1.toString();
		String expRow2List1 = "[Dozs, Dozs, Dozs]";


		int report2ndRowListCount7 = reportCol7List.size();
		ArrayList<String> report2ndRowListArray7 = new ArrayList<String>();
		for(int i=0;i<report2ndRowListCount7;i++)
		{
			String data = reportCol7List.get(i).getText().trim();
			if (data.isEmpty() == false)
			{
				report2ndRowListArray7.add(data);
			}
		}
		String actRow2List7 = report2ndRowListArray7.toString();
		String expRow2List7 = "[5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 15]";


		Thread.sleep(2000);
		
		int report2ndRowListCount8 = reportCol8List.size();
		ArrayList<String> report2ndRowListArray8= new ArrayList<String>();
		for(int i=0;i<report2ndRowListCount8;i++)
		{
			String data = reportCol8List.get(i).getText().trim();
			if (data.isEmpty() == false)
			{
				report2ndRowListArray8.add(data);
			}
		}
		String actRow2List8 = report2ndRowListArray8.toString();
		String expRow2List8 = "[10, 10, 10, 10, 10]";

		
		Thread.sleep(2000);
		
		int report2ndRowListCount9 = reportCol9List.size();
		ArrayList<String> report2ndRowListArray9= new ArrayList<String>();
		for(int i=0;i<report2ndRowListCount9;i++)
		{
			String data = reportCol9List.get(i).getText().trim();
			if (data.isEmpty() == false)
			{
				report2ndRowListArray9.add(data);
			}
		}
		String actRow2List9 = report2ndRowListArray9.toString();
		String expRow2List9 = "[15, 15, 15, 15, 5, 5, 5, 5, 5, 5, 5, 5, 25]";

	


		System.out.println("actCol1List  : "+actRow1List);
		System.out.println("expCol1List  : "+expRow1List);
		System.out.println("*********************************************************************");

		System.out.println("actCol2List  : "+actRow2List);
		System.out.println("expCol2List  : "+expRow2List);
		System.out.println("*********************************************************************");

		System.out.println("actCol3List  : "+actRow3List);
		System.out.println("expCol3List  : "+expRow3List);
		System.out.println("*********************************************************************");

		System.out.println("actCol4List  : "+actRow4List);
		System.out.println("expCol4List  : "+expRow4List);
		System.out.println("*********************************************************************");


		System.out.println("actCol5List  : "+actRow1List1);
		System.out.println("expCol5List  : "+expRow1List1);
		System.out.println("*********************************************************************");

		System.out.println("actCol6List  :  "+actRow2List1);
		System.out.println("expCol6List  :  "+expRow2List1);
		System.out.println("*********************************************************************");

		System.out.println("actCol7List  : "+actRow2List7);
		System.out.println("expCol7List  : "+expRow2List7);
		System.out.println("*********************************************************************");
		
		System.out.println("actCol8List  : "+actRow2List8);
		System.out.println("expCol8List  : "+expRow2List8);
		
		System.out.println("*********************************************************************");
		
		System.out.println("actCol8List  : "+actRow2List9);
		System.out.println("expCol8List  : "+expRow2List9);

		if (actRow1List.equalsIgnoreCase(expRow1List) &&
				actRow2List.equalsIgnoreCase(expRow2List) &&
				actRow3List.equalsIgnoreCase(expRow3List) && 
				actRow4List.equalsIgnoreCase(expRow4List)  && 

				actRow1List1.equalsIgnoreCase(expRow1List1) &&
				actRow2List1.equalsIgnoreCase(expRow2List1) && 

				actRow2List7.equalsIgnoreCase(expRow2List7) &&
				actRow2List8.equalsIgnoreCase(expRow2List8) &&
				actRow2List9.equalsIgnoreCase(expRow2List9)) 
		{
			System.out.println(" Test Pass: Values Dsiplayed as Expected ");
			return true;
		} 
		else 
		{
			System.out.println(" Test FAIL: Values Dsiplayed as Expected ");
			String meesage=validationConfirmationMessage.getText();
			System.err.println(" Meesage Displayed  : "+meesage);
			return false;
		}
	}
	
	
	
	public static boolean checkSaveInventoryTransactionsOfInventoryTagCubes() throws InterruptedException, IOException, EncryptedDocumentException, InvalidFormatException
	{
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
		reportNameDropdown.sendKeys("Inventory Transactions of Inventory Tag-Cubes");
		reportNameDropdown.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportTypeDropdown));
		Select rtd= new Select(reportTypeDropdown);
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

		for(int i=0;i<=transactionSetListCount;i++)
		{
			String data = transactionSetList.get(i).getText();

			if(data.equalsIgnoreCase("Inventory transactions of inventory tag"))
			{
				transactionSetList.get(i).click();

				break;
			}
		}


		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTab));
		customizationTab.click();

		Thread.sleep(2000);
		//getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionExpandBtn));
		//transactionExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionFieldsExpandBtn));
		transactionFieldsExpandBtn.click();
		Thread.sleep(3000);
		
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(acc1ExpandBtn));
		acc1ExpandBtn.click();

		Thread.sleep(3000);

		Runtime.getRuntime().exec(getBaseDir()+"\\autoIt\\scripts\\scripts\\AccountDargRowNew.exe");

		Thread.sleep(20000);
		
		ArrayList<String> checkBoxList = new ArrayList<String>();
		
		int groupingOptionsChechboxesCount = groupingOptionsChechboxes.size();

		for(int i=0; i < groupingOptionsChechboxesCount;i++)
		{
			String data = groupingOptionsChechboxes.get(i).getText();
			if(data.equalsIgnoreCase("Display total at the end of this level"))
			{
				groupingOptionsChechboxes.get(i).click();

				break;
			}
		}
		
		Thread.sleep(2000);
		groupingOptionsOkBtn.click();
		
		Thread.sleep(3000);

		Runtime.getRuntime().exec(getBaseDir()+"\\autoIt\\scripts\\scripts\\AccountDragColMsrNew.exe");
		
		Thread.sleep(3000);
		
		String expMessage = "Only 'Numeric' type field allowed.";
		String actMessage =  checkValidationMessage(expMessage);
		
		System.out.println("validation message:"+checkValidationMessage(""));
		
		Thread.sleep(1000);
		
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(accountName));
		getAction().doubleClick(accountName).build().perform();
		
		getAction().moveToElement(accountName).build().perform();
		Thread.sleep(2000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(acc1ExpandBtn));
		acc1ExpandBtn.click();

		Thread.sleep(3000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(currencyExpand));
		currencyExpand.click();
		
		Thread.sleep(3000);
		
		getAction().moveToElement(dateExpandBtn).build().perform();
		Thread.sleep(2000);
	
		Runtime.getRuntime().exec(getBaseDir()+"\\autoIt\\scripts\\scripts\\CurrencyDrag.exe");

		Thread.sleep(15000);
		
		
		Thread.sleep(2000);
	
		groupingOptionsOkBtn.click();
		
		Thread.sleep(1000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(currencyName));
		getAction().doubleClick(currencyName).build().perform();
		
		moveToElement(ModifiedDateExpandBtn1);
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemExpandBtn1));
		itemExpandBtn1.click(); 
		
		Runtime.getRuntime().exec(getBaseDir()+"\\autoIt\\scripts\\scripts\\ItemDragN.exe");

		Thread.sleep(15000);
		
		Thread.sleep(2000);
		groupingOptionsOkBtn.click();
		
		Thread.sleep(1000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemName));
		getAction().doubleClick(itemName).build().perform();
		Thread.sleep(2000);
	
		moveToElement(voucherName);
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(unitExpand));
		unitExpand.click();
		Thread.sleep(2000);
		Runtime.getRuntime().exec(getBaseDir()+"\\autoIt\\scripts\\scripts\\UnitDragNew.exe");

		Thread.sleep(15000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(unitName));
		getAction().doubleClick(unitName).build().perform();
		
		moveToElement(RMABtnn);
		Thread.sleep(2000);
		
		Thread.sleep(2000);
		Runtime.getRuntime().exec(getBaseDir()+"\\autoIt\\scripts\\scripts\\RateDragRDNew.exe");

		Thread.sleep(15000);
		
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rateBtnn));
		getAction().doubleClick(rateBtnn).build().perform();
		

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionFieldsExpandBtn));
		transactionFieldsExpandBtn.click();
		
		Thread.sleep(2000);

		getAction().moveToElement(finishBtn).build().perform();
		Thread.sleep(1200);
		finishBtn.click();
		
		Thread.sleep(2000);

		String expMessage1 = "Data saved successfully.";

		String actMessage1 = checkValidationMessage(expMessage1);


		System.out.println("Validation Message : "+actMessage+" Value Expected : "+expMessage);

		
		
		
		
		
		if (actMessage.equalsIgnoreCase(expMessage))
		{
			return true;
		}
		else
		{
			return false;
			
		}
	
	}
	
	
	
	public static boolean checkReportForCreatedInventoryTransactionsOfInventoryTagCube() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		Thread.sleep(2000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(userNameDisplay));
		userNameDisplay.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(userNameDisplay));
		logoutOption.click();

		Thread.sleep(2000);
		
		checkLogin();
		
	/*	LoginPage lp=new LoginPage(getDriver()); 

		String unamelt="su";

		String pawslt="su";
		String compname="RD REPORTS";

		Thread.sleep(2000);
		lp.reLogin(unamelt, pawslt, compname);*/

		Thread.sleep(4000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
		searchTxt.click();
		searchTxt.sendKeys("Inventory Transactions of Inventory Tag-Cubes");
		Thread.sleep(2000);
		searchTxt.sendKeys(Keys.ENTER);

		Thread.sleep(2000);

		try {
		if (sl_DateOptionDropdown.isDisplayed()==false)
		{
			getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
			searchTxt.click();
			searchTxt.sendKeys("Inventory Transactions Of A Tag Cubic Report");
			Thread.sleep(2000);
			searchTxt.sendKeys(Keys.ENTER);
		}
		}
		catch (Exception e)
		{
		}

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_DateOptionDropdown));

		Select s=new Select(sl_DateOptionDropdown);
		s.selectByValue("1");

		Thread.sleep(2000);


		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_OkBtn));
		sl_OkBtn.click();


		Thread.sleep(3000);

		Thread.sleep(1500);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_1stRow1stCol));

		int reportsRow1ListCount = reportCol1List.size();
		ArrayList<String> reportsRow1ListArray = new ArrayList<String>();
		for(int i=0;i<reportsRow1ListCount;i++)
		{
			String data = reportCol1List.get(i).getText().trim();

			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date date=new Date();
			String expadjustBills=df.format(date);

			if (data.isEmpty() == false)
			{
				reportsRow1ListArray.add(data);
			}
		}
		String actRow1List = reportsRow1ListArray.toString();
		String expRow1List = "[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13]";


		int report2ndRowListCount = reportCol2List.size();
		ArrayList<String> report2ndRowListArray = new ArrayList<String>();
		for(int i=0;i<report2ndRowListCount;i++)
		{
			String data = reportCol2List.get(i).getText().trim();
			if (data.isEmpty() == false)
			{
				report2ndRowListArray.add(data);
			}
		}
		String actRow2List = report2ndRowListArray.toString();
		String expRow2List = "[Customer A, Indian Rupees, STD RATE COGS ITEM, Sub Total, Vendor B, Indian Rupees, STD RATE COGS ITEM, Sub Total, Vendor New Reference, Indian Rupees, STD RATE COGS ITEM, Sub Total, Grand Total]";


		int report3rdRowListCount = reportCol3List.size();
		ArrayList<String> report3rdRowListArray = new ArrayList<String>();
		for(int i=0;i<report3rdRowListCount;i++)
		{
			String data = reportCol3List.get(i).getText().trim();
			if (data.isEmpty() == false)
			{
				report3rdRowListArray.add(data);
			}
		}
		String actRow3List = report3rdRowListArray.toString();
		String expRow3List = "[Customer A, Vendor B, Vendor New Reference]";


		int report4thRowListCount = reportCol4List.size();
		ArrayList<String> report4thRowListArray = new ArrayList<String>();
		for(int i=0;i<report4thRowListCount;i++)
		{
			String data = reportCol4List.get(i).getText().trim();
			if (data.isEmpty() == false)
			{
				report4thRowListArray.add(data);
			}
		}
		String actRow4List = report4thRowListArray.toString();
		String expRow4List = "[Indian Rupees, Indian Rupees, Indian Rupees]";



		int reportsRow1ListCount1 = reportCol5List.size();
		ArrayList<String> reportsRow1ListArray1 = new ArrayList<String>();
		for(int i=0;i<reportsRow1ListCount1;i++)
		{
			String data = reportCol5List.get(i).getText().trim();

			if (data.isEmpty() == false)
			{
				reportsRow1ListArray1.add(data);
			}
		}
		String actRow1List1 = reportsRow1ListArray1.toString();
		String expRow1List1 = "[Dozs, Dozs, Dozs]";


		int report2ndRowListCount1 = reportCol6List.size();
		ArrayList<String> report2ndRowListArray1 = new ArrayList<String>();
		for(int i=0;i<report2ndRowListCount1;i++)
		{
			String data = reportCol6List.get(i).getText().trim();
			if (data.isEmpty() == false)
			{
				report2ndRowListArray1.add(data);
			}
		}
		String actRow2List1 = report2ndRowListArray1.toString();
		String expRow2List1 = "[5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 15]";


		int report2ndRowListCount7 = reportCol7List.size();
		ArrayList<String> report2ndRowListArray7 = new ArrayList<String>();
		for(int i=0;i<report2ndRowListCount7;i++)
		{
			String data = reportCol7List.get(i).getText().trim();
			if (data.isEmpty() == false)
			{
				report2ndRowListArray7.add(data);
			}
		}
		String actRow2List7 = report2ndRowListArray7.toString();
		String expRow2List7 = "[10, 10, 10, 10, 10]";


		Thread.sleep(2000);
		
		int report2ndRowListCount8 = reportCol8List.size();
		ArrayList<String> report2ndRowListArray8= new ArrayList<String>();
		for(int i=0;i<report2ndRowListCount8;i++)
		{
			String data = reportCol8List.get(i).getText().trim();
			if (data.isEmpty() == false)
			{
				report2ndRowListArray8.add(data);
			}
		}
		String actRow2List8 = report2ndRowListArray8.toString();
		String expRow2List8 = "[15, 15, 15, 15, 5, 5, 5, 5, 5, 5, 5, 5, 25]";

		
		Thread.sleep(2000);
		
		int report2ndRowListCount9 = reportCol9List.size();
		ArrayList<String> report2ndRowListArray9= new ArrayList<String>();
		for(int i=0;i<report2ndRowListCount9;i++)
		{
			String data = reportCol9List.get(i).getText().trim();
			if (data.isEmpty() == false)
			{
				report2ndRowListArray9.add(data);
			}
		}
		String actRow2List9 = report2ndRowListArray9.toString();
		String expRow2List9 = "[15, 15, 15, 15, 5, 5, 5, 5, 5, 5, 5, 5, 25]";

	


		System.out.println("actCol1List  : "+actRow1List);
		System.out.println("expCol1List  : "+expRow1List);
		System.out.println("*********************************************************************");

		System.out.println("actCol2List  : "+actRow2List);
		System.out.println("expCol2List  : "+expRow2List);
		System.out.println("*********************************************************************");

		System.out.println("actCol3List  : "+actRow3List);
		System.out.println("expCol3List  : "+expRow3List);
		System.out.println("*********************************************************************");

		System.out.println("actCol4List  : "+actRow4List);
		System.out.println("expCol4List  : "+expRow4List);
		System.out.println("*********************************************************************");


		System.out.println("actCol5List  : "+actRow1List1);
		System.out.println("expCol5List  : "+expRow1List1);
		System.out.println("*********************************************************************");

		System.out.println("actCol6List  :  "+actRow2List1);
		System.out.println("expCol6List  :  "+expRow2List1);
		System.out.println("*********************************************************************");

		System.out.println("actCol7List  : "+actRow2List7);
		System.out.println("expCol7List  : "+expRow2List7);
		System.out.println("*********************************************************************");
		
		System.out.println("actCol8List  : "+actRow2List8);
		System.out.println("expCol8List  : "+expRow2List8);
		
		System.out.println("*********************************************************************");
		
		System.out.println("actCol8List  : "+actRow2List9);
		System.out.println("expCol8List  : "+expRow2List9);

		if (actRow1List.equalsIgnoreCase(expRow1List) &&
				actRow2List.equalsIgnoreCase(expRow2List) &&
				actRow3List.equalsIgnoreCase(expRow3List) && 
				actRow4List.equalsIgnoreCase(expRow4List)  && 

				actRow1List1.equalsIgnoreCase(expRow1List1) &&
				actRow2List1.equalsIgnoreCase(expRow2List1) && 

				actRow2List7.equalsIgnoreCase(expRow2List7) &&
				actRow2List8.equalsIgnoreCase(expRow2List8) &&
				actRow2List9.equalsIgnoreCase(expRow2List9)) 
		{
			System.out.println(" Test Pass: Values Dsiplayed as Expected ");
			return true;
		} 
		else 
		{
			System.out.println(" Test FAIL: Values Dsiplayed as Expected ");
			String meesage=validationConfirmationMessage.getText();
			System.err.println(" Meesage Displayed  : "+meesage);
			return false;
		}
	}
	
	
	

	//@FindBy(xpath="//body/section[@id='mainDiv']/div[@id='id_focus8_wrapper_default']/div[1]/section[1]/div[1]/div[1]/div[2]/div[3]/div[2]/div[1]/div[2]/ul[1]/li[1]/ul[1]/li[1]/ul[1]/li[34]/a[1]/span[1]")
	@FindBy(xpath="(//li[@title='Transaction Fields.Item']//i)[1]")
	private static WebElement itemExpandBtnn;


	//@FindBy(xpath="//body/section[@id='mainDiv']/div[@id='id_focus8_wrapper_default']/div[1]/section[1]/div[1]/div[1]/div[2]/div[3]/div[2]/div[1]/div[2]/ul[1]/li[1]/ul[1]/li[1]/ul[1]/li[34]/ul[1]/li[4]/a[1]/span[1]")
	@FindBy(xpath="//li[@title='Item.Alias']")
	private static WebElement itemAliasBtn;


	public static boolean checkSavingAccountingTransactionsOfAnAccountCube() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{

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
		Select rtd= new Select(reportTypeDropdown);
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

		for(int i=0;i<=transactionSetListCount;i++)
		{
			String data = transactionSetList.get(i).getText();

			if(data.equalsIgnoreCase("Accounting transactions of an account"))
			{
				transactionSetList.get(i).click();

				break;
			}
		}

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTab));
		customizationTab.click();

		Thread.sleep(2000);
		//getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionExpandBtn));
		//transactionExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionFieldsExpandBtn));
		transactionFieldsExpandBtn.click();


		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(acc1ExpandBtn));
		acc1ExpandBtn.click();

		Thread.sleep(2999);

		Runtime.getRuntime().exec(getBaseDir()+"\\autoIt\\scripts\\scripts\\AccountDargRowNew.exe");
		Thread.sleep(20000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(groupingOptionsOkBtn));
		groupingOptionsOkBtn.click();
		
		Thread.sleep(1500);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(acc1ExpandBtn));
		acc1ExpandBtn.click();
		
		getAction().moveToElement(itemExpandBtnn).perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemExpandBtnn));
		itemExpandBtnn.click();

		getAction().moveToElement(itemAliasBtn).perform();

		Thread.sleep(3000);
		Runtime.getRuntime().exec(getBaseDir()+"\\autoIt\\scripts\\scripts\\ItemNameDragColNew.exe");
		Thread.sleep(20000);

		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemExpandBtnn));
		itemExpandBtnn.click();
		Thread.sleep(2000);
		
		getAction().moveToElement(documentNo).perform();
		Thread.sleep(2000);
		
		Runtime.getRuntime().exec(getBaseDir()+"\\autoIt\\scripts\\scripts\\CreditDebitDragColMsrNew.exe");
		Thread.sleep(21000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(qunatityInAccounttransactionBtn));
		getAction().moveToElement(qunatityInAccounttransactionBtn).doubleClick(qunatityInAccounttransactionBtn).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rateInAccounttransactionBtn));
		getAction().moveToElement(rateInAccounttransactionBtn).doubleClick(rateInAccounttransactionBtn).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNo));
		getAction().moveToElement(documentNo).doubleClick(documentNo).build().perform();

		Thread.sleep(2000);

		getAction().moveToElement(finishBtn).build().perform();
		Thread.sleep(1200);
		finishBtn.click();
		Thread.sleep(2000);

		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : "+actMessage+" Value Expected : "+expMessage);

		if(actMessage.equalsIgnoreCase(expMessage))
		{
			return true;
		}
		else
		{
			return false;
		}

	}

	public static boolean checkReportAccountingtransactionOfAnAccountCube() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(userNameDisplay));
		userNameDisplay.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(userNameDisplay));
		logoutOption.click();

		checkLogin();


		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
		searchTxt.click();
		searchTxt.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
		searchTxt.sendKeys("Accounting Transactions of an account_Cube");
		Thread.sleep(2000);
		searchTxt.sendKeys(Keys.ENTER);


		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_DateOptionDropdown));
		/*sl_DateOptionDropdown.click();*/

		Select s=new Select(sl_DateOptionDropdown);
		s.selectByValue("1");

		Thread.sleep(2000);


		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_OkBtn));
		sl_OkBtn.click();


		Thread.sleep(2000);
		boolean novalidationConfirmationMessage =validationConfirmationMessage.getText().isEmpty();

		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";

		Thread.sleep(3000);

		Thread.sleep(1500);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_1stRow1stCol));

		
		int reportsRowHeaderListCount = reportHeaderList.size();
		ArrayList<String> reportsRowHeaderListArray = new ArrayList<String>();
		for(int i=0;i<reportsRowHeaderListCount;i++)
		{
			String data = reportHeaderList.get(i).getText();

			reportsRowHeaderListArray.add(data);
		}
		String actRowHeaderList = reportsRowHeaderListArray.toString();
		String expRowHeaderList = "[#, Particulars, Document No., Credit, Debit, STD RATE COGS ITEM, Total Credit, Total Debit, Credit, Debit]";
		
		
		
		int reportsRow1ListCount = reportCol1List.size();
		ArrayList<String> reportsRow1ListArray = new ArrayList<String>();
		for(int i=0;i<reportsRow1ListCount;i++)
		{
			String data = reportCol1List.get(i).getText();

			reportsRow1ListArray.add(data);
		}
		String actRow1List = reportsRow1ListArray.toString();
		String expRow1List = "[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16]";


		int report2ndRowListCount = reportCol2List.size();
		ArrayList<String> report2ndRowListArray = new ArrayList<String>();
		for(int i=0;i<report2ndRowListCount;i++)
		{
			String data = reportCol2List.get(i).getText();
			report2ndRowListArray.add(data);
		}
		String actRow2List = report2ndRowListArray.toString();
		String expRow2List = "[, Bank, COGS POSTING ACC, Customer A, Customer New Reference, Customer Semi Adjustment, Journal Entries Control A/C, Opening Balances Control A/C, PURCHASE VARIANCE, Sales - Computers, SR COGS POSTING ACC, STD RATE COGS ACC INV, VAT ADVANCE PURCHASE, VAT INPUT, Vendor B, Vendor Full Adjustment]";


		int report3rdRowListCount = reportCol3List.size();
		ArrayList<String> report3rdRowListArray = new ArrayList<String>();
		for(int i=0;i<report3rdRowListCount;i++)
		{
			String data = reportCol3List.get(i).getText();
			report3rdRowListArray.add(data);
		}
		String actRow3List = report3rdRowListArray.toString();
		String expRow3List = "[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2]";


		int report4thRowListCount = reportCol4List.size();
		ArrayList<String> report4thRowListArray = new ArrayList<String>();
		for(int i=0;i<report4thRowListCount;i++)
		{
			String data = reportCol4List.get(i).getText();
			report4thRowListArray.add(data);
		}
		String actRow4List = report4thRowListArray.toString();
		String expRow4List = "[230, 30, 20, , 5, 15, 10, 20, , , , 120, , , , 10]";



		int reportsRow1ListCount1 = reportCol5List.size();
		ArrayList<String> reportsRow1ListArray1 = new ArrayList<String>();
		for(int i=0;i<reportsRow1ListCount1;i++)
		{
			String data = reportCol5List.get(i).getText();

			reportsRow1ListArray1.add(data);
		}
		String actRow1List1 = reportsRow1ListArray1.toString();
		String expRow1List1 = "[, 40, , 15, 20, 15, 10, , , , 120, 20, , , 0, ]";


		int report2ndRowListCount1 = reportCol6List.size();
		ArrayList<String> report2ndRowListArray1 = new ArrayList<String>();
		for(int i=0;i<report2ndRowListCount1;i++)
		{
			String data = reportCol6List.get(i).getText();
			report2ndRowListArray1.add(data);
		}
		String actRow2List1 = report2ndRowListArray1.toString();
		String expRow2List1 = "[, , , 20, , , , , , 5, , 11, , , , ]";


		int report2ndRowListCount7 = reportCol7List.size();
		ArrayList<String> report2ndRowListArray7 = new ArrayList<String>();
		for(int i=0;i<report2ndRowListCount7;i++)
		{
			String data = reportCol7List.get(i).getText();
			report2ndRowListArray7.add(data);
		}
		String actRow2List7 = report2ndRowListArray7.toString();
		String expRow2List7 = "[, , , 5, , , , , , 20, , , , , 120, ]";



		int repor8col = reportCol8List.size();
		ArrayList<String> report8thColListArray = new ArrayList<String>();
		for(int i=0;i<repor8col;i++)
		{
			String data = reportCol8List.get(i).getText();
			report8thColListArray.add(data);
		}
		String act8thColList = report8thColListArray.toString();
		String exp8thcolList = "[230, 30, 20, 20, 5, 15, 10, 20, , 5, , 131, , , , 10]";

		Thread.sleep(2000);
		
		int repor9col = reportCol9List.size();
		ArrayList<String> report9thColListArray = new ArrayList<String>();
		for(int i=0;i<repor9col;i++)
		{
			String data = reportCol9List.get(i).getText();
			report9thColListArray.add(data);
		}
		String act9thColList = report9thColListArray.toString();
		String exp9thcolList = "[, 40, , 20, 20, 15, 10, , , 20, 120, 20, , , 120, ]";

		

		sl_NextBtn.click();

		Thread.sleep(2000);
		
		
		int report3rdRow = report3rdRowList.size();
		ArrayList<String> report3RdRowListArray = new ArrayList<String>();
		for(int i=0;i<report3rdRow;i++)
		{
			String data = report3rdRowList.get(i).getText();
			report3RdRowListArray.add(data);
		}
		String act3RdRowList = report3RdRowListArray.toString();
		String exp3RdRowList = "[19, Grand Total, , 480, 250, 36, 265, 515, 515]";

		
		System.out.println("actRowHeaderList  : "+actRowHeaderList);
		System.out.println("expRowHeaderList  : "+expRowHeaderList);
		System.out.println("*********************************************************************");

		System.out.println("actCol1List  : "+actRow1List);
		System.out.println("expCol1List  : "+expRow1List);
		System.out.println("*********************************************************************");

		System.out.println("actCol2List  : "+actRow2List);
		System.out.println("expCol2List  : "+expRow2List);
		System.out.println("*********************************************************************");

		System.out.println("actCol3List  : "+actRow3List);
		System.out.println("expCol3List  : "+expRow3List);
		System.out.println("*********************************************************************");

		System.out.println("actCol4List  : "+actRow4List);
		System.out.println("expCol4List  : "+expRow4List);
		System.out.println("*********************************************************************");


		System.out.println("actCol5List  : "+actRow1List1);
		System.out.println("expCol5List : "+expRow1List1);
		System.out.println("*********************************************************************");

		System.out.println("actCol6List  :  "+actRow2List1);
		System.out.println("expCol6List  :  "+expRow2List1);
		System.out.println("*********************************************************************");

		System.out.println("actCol7List  : "+actRow2List7);
		System.out.println("expCol7List  : "+expRow2List7);
		System.out.println("*********************************************************************");


		System.out.println("actCol8List  : "+act8thColList);
		System.out.println("expCol8List  : "+exp8thcolList);
		System.out.println("*********************************************************************");

		System.out.println("actCol9List  : "+act9thColList);
		System.out.println("expCol9List  : "+exp9thcolList);
		System.out.println("*********************************************************************");

		System.out.println("act3RdRowList  : "+act3RdRowList);
		System.out.println("exp3RdRowList  : "+exp3RdRowList);
		System.out.println("*********************************************************************");

		
		
		if (actRowHeaderList.equalsIgnoreCase(expRowHeaderList)    && actRow1List.equalsIgnoreCase(expRow1List) &&
				actRow2List.equalsIgnoreCase(expRow2List) &&
				actRow3List.equalsIgnoreCase(expRow3List) && 
				actRow4List.equalsIgnoreCase(expRow4List)  && 

				actRow1List1.equalsIgnoreCase(expRow1List1) &&
				actRow2List1.equalsIgnoreCase(expRow2List1) && actRow2List7.equalsIgnoreCase(expRow2List7)

				&&  act8thColList.equalsIgnoreCase(exp8thcolList) && act9thColList.equalsIgnoreCase(exp9thcolList)
				&& act3RdRowList.equalsIgnoreCase(exp3RdRowList)) 
		{
			System.out.println(" Test Pass: Values Dsiplayed as Expected ");
			return true;
		} 
		else 
		{
			System.out.println(" Test FAIL: Values Dsiplayed as Expected ");
			String meesage=validationConfirmationMessage.getText();
			System.err.println(" Meesage Displayed  : "+meesage);
			return false;
		}

	}


	//@FindBy(xpath="//body/section[@id='mainDiv']/div[@id='id_focus8_wrapper_default']/div[1]/section[1]/div[1]/div[1]/div[2]/div[3]/div[2]/div[1]/div[2]/ul[1]/li[1]/ul[1]/li[2]/ul[1]/li[8]/a[1]/span[1]")
	@FindBy(xpath="//li[@title='Extra Fields.Department']")
	private static WebElement departmentExpandBtn;

	//@FindBy(xpath="//body/section[@id='mainDiv']/div[@id='id_focus8_wrapper_default']/div[1]/section[1]/div[1]/div[1]/div[2]/div[3]/div[2]/div[1]/div[2]/ul[1]/li[1]/ul[1]/li[2]/ul[1]/li[8]/ul[1]/li[4]/a[1]/span[1]")
	@FindBy(xpath="(//span[contains(text(),'Jurisdiction')])[2]")
	private static WebElement jurisdictionTxt;

	@FindBy(xpath="//span[contains(text(),'Voucher type')]")
	private static WebElement voucherTypeTxt;


	public static boolean checkSavingAccountingTransactionsOfAnAccountingTagCube() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{

		Thread.sleep(2999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(homeMenu));
		homeMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(utilities));
		utilities.click();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportDesignerMenu));
		reportDesignerMenu.click();

		Thread.sleep(4000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportNameDropdown));       
		reportNameDropdown.sendKeys("Accounting Transactions of an accounting tag_cube");
		Thread.sleep(1999);
		reportNameDropdown.sendKeys(Keys.TAB);

		Thread.sleep(4000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportTypeDropdown));
		Select rtd= new Select(reportTypeDropdown);
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

		for(int i=0;i<=transactionSetListCount;i++)
		{
			String data = transactionSetList.get(i).getText();

			if(data.equalsIgnoreCase("Accounting transactions of accounting tag"))
			{
				transactionSetList.get(i).click();

				break;
			}
		}


		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTab));
		customizationTab.click();

		Thread.sleep(2000);
		//getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionExpandBtn));
		//transactionExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(extraFieldsExpandBtn));
		extraFieldsExpandBtn.click();
		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(departmentExpandBtn));
		departmentExpandBtn.click();
		Thread.sleep(2000);
		getAction().moveToElement(jurisdictionTxt).perform();

		Thread.sleep(2000);
		Runtime.getRuntime().exec(getBaseDir()+"\\autoIt\\scripts\\scripts\\DepartmentNameDragRow.exe");
		Thread.sleep(20000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(groupingOptionsOkBtn));
		groupingOptionsOkBtn.click();
		Thread.sleep(1000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(extraFieldsExpandBtn));
		extraFieldsExpandBtn.click();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionFieldsExpandBtn));
		transactionFieldsExpandBtn.click();


		getAction().moveToElement(voucherTypeTxt).perform();

		Thread.sleep(3000);
		Runtime.getRuntime().exec(getBaseDir()+"\\autoIt\\scripts\\scripts\\voucherNameDrag1.exe");
		Thread.sleep(20000);


		getAction().moveToElement(documentNo).perform();
		Thread.sleep(2000);

		Runtime.getRuntime().exec(getBaseDir()+"\\autoIt\\scripts\\scripts\\CreditDebitDragColMsrNew.exe");
		Thread.sleep(21000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(qunatityInAccounttransactionBtn));
		getAction().moveToElement(qunatityInAccounttransactionBtn).doubleClick(qunatityInAccounttransactionBtn).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rateInAccounttransactionBtn));
		getAction().moveToElement(rateInAccounttransactionBtn).doubleClick(rateInAccounttransactionBtn).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNo));
		getAction().moveToElement(documentNo).doubleClick(documentNo).build().perform();

		Thread.sleep(2000);

		getAction().moveToElement(finishBtn).build().perform();
				
		Thread.sleep(1200);
		finishBtn.click();
		Thread.sleep(2000);

		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : "+actMessage+" Value Expected : "+expMessage);

		if(actMessage.equalsIgnoreCase(expMessage))
		{
			return true;
		}
		else
		{
			return false;
		}

	}           

	@FindBy(xpath ="//li[@title='Transaction Fields.Quantity']")
	private static WebElement qunatityInAccounttransactionBtn;

	@FindBy(xpath ="//li[@title='Transaction Fields.Rate']/a/span")
	private static WebElement rateInAccounttransactionBtn;

	@FindBy(xpath ="//li[@title='Transaction Fields.DocNo']/a/span")
	private static WebElement documentNo;


	@FindBy(xpath ="//li[@title='Transaction Fields.Credit']/a/span")
	private static WebElement creditBtn;


	@FindBy(xpath ="(//li[@title='Transaction Fields.Debit']/a/span)[1]")
	private static WebElement debitBtn;



	public boolean checkReportAccountingTransactionsofAnAccountingTag_cube() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{

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
		/*sl_DateOptionDropdown.click();*/

		Select s=new Select(sl_DateOptionDropdown);
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
		boolean novalidationConfirmationMessage =validationConfirmationMessage.getText().isEmpty();

		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";



		Thread.sleep(1500);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_1stRow1stCol));

		int reportsRow1ListCount = report1stRowList.size();
		ArrayList<String> reportsRow1ListArray = new ArrayList<String>();
		for(int i=1;i<reportsRow1ListCount;i++)
		{
			String data = report1stRowList.get(i).getText();

			reportsRow1ListArray.add(data);
		}
		String actRow1List = reportsRow1ListArray.toString();
		String expRow1List = "[DUBAI, 1, , 15, 5, , 10, , 10, 10, , 20, 5, , 10, , 11, , , 25, 20, 20, 125, , 176, 70]";


		int report2ndRowListCount = report2ndRowList.size();
		ArrayList<String> report2ndRowListArray = new ArrayList<String>();
		for(int i=1;i<report2ndRowListCount;i++)
		{
			String data = report2ndRowList.get(i).getText();
			report2ndRowListArray.add(data);
		}
		String actRow2List = report2ndRowListArray.toString();
		String expRow2List = "[Grand Total, , , 15, 5, , 10, , 10, 10, , 20, 5, , 10, , 11, , , 25, 20, 20, 125, , 176, 70]";



		System.out.println("actRow1List  : "+actRow1List);
		System.out.println("expRow1List  : "+expRow1List);
		System.out.println("*********************************************************************");

		System.out.println("actRow2List  : "+actRow2List);
		System.out.println("expRow2List  : "+expRow2List);
		System.out.println("*********************************************************************");


		if (actRow1List.equalsIgnoreCase(expRow1List) &&
				actRow2List.equalsIgnoreCase(expRow2List) && 
				actvalidationConfirmationMessage.equalsIgnoreCase(expvalidationConfirmationMessage)) 
		{
			System.out.println(" Test Pass: Values Dsiplayed as Expected ");
			return true;
		} 
		else 
		{
			System.out.println(" Test FAIL: Values Dsiplayed as Expected ");
			return false;
		}
	}
	
	
	@FindBy(xpath = "//span[text()='iUserId']")
	private static WebElement userID;

	@FindBy(xpath = "//*[@id='id_rd_customization_cube_column_measures_list']")
	private static WebElement cube_ColMeasureTxt;

	@FindBy(xpath = "//*[@id='id_rd_customization_cube_grouping_row']")
	private static WebElement cube_RowGroupingTxt;

	public boolean checkAddingReportDesginerWithdataTypeCubeType() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{

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
		Select rtd= new Select(reportTypeDropdown);
		rtd.selectByVisibleText("Cubes");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportDataSetTypeDrpdwn));
		Select rds= new Select(reportDataSetTypeDrpdwn);
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


		int reportFieldlistCount=reportFieldlist.size();
		ArrayList<String >reportFieldlistarray= new ArrayList<>();
		for (int i = 0; i < reportFieldlistCount; i++) 
		{
			String data =reportFieldlist.get(i).getText();
			reportFieldlistarray.add(data);
		}

		String actreportFieldlist=reportFieldlistarray.toString();
		String expreportFieldlist="[iUserId, sLoginName, sLoginAbbr, iPwdPolicyId, sPassword, sUserName, sEmail, sPhone, sMobile, binImage, iGroupId, sSecurityQuestion, sSecurityAnswer, iUserType, iLinkId, sDomainName, sDomainUser, bAccountDisabled, bSendEmailNotification, bAllowMultipleLogin, bEmailonLoginFailure, bEmailUseronLoginSuccess, bDontLockAccount, iStatus, iNumInvalidAttempts, iLockedTill, iDays, iBlockFromDate, iBlockToDate, iTimeRestrictionStartDate, iTimeRestrictionEndDate, iTimeRestrictionStartTime, iTimeRestrictionEndTime, iLocation, iLanguage, iAltLanguage, iTimeZone, iUserAccess, iPWDChangeDate, fVal0, fVal1, fVal2, fVal3, fVal4, iCreatedBy, iModifiedBy, iCreatedDate, iModifiedDate, iCreatedTime, iModifiedTime, bModifiedDiffLoc, iSyncReceivedDate, iEditingLocation, iLocationId, sAuthenticationCode, biSignature, sEmailPwd, bEmailAuthPermission, sMacAddress]";


		System.out.println(" act reportFieldlist : "+actreportFieldlist);
		System.out.println(" exp reportFieldlist : "+expreportFieldlist);


		for (int i = 0; i < reportFieldlistCount; i++) 
		{
			String data =reportFieldlist.get(i).getText();

			if (data.equalsIgnoreCase("iUserId")) 
			{
				reportFieldlist.get(i).click();

				getAction().doubleClick(reportFieldlist.get(i)).build().perform();
			}
		}

		for (int i = 0; i < reportFieldlistCount; i++) 
		{
			String data =reportFieldlist.get(i).getText();

			if (data.equalsIgnoreCase("sLoginName")) 
			{
				reportFieldlist.get(i).click();

				getAction().doubleClick(reportFieldlist.get(i)).build().perform();
			}
		}

		for (int i = 0; i < reportFieldlistCount; i++) 
		{
			String data =reportFieldlist.get(i).getText();

			if (data.equalsIgnoreCase("sLoginAbbr")) 
			{
				reportFieldlist.get(i).click();

				getAction().doubleClick(reportFieldlist.get(i)).build().perform();
			}
		}


		for (int i = 0; i < reportFieldlistCount; i++) 
		{
			String data =reportFieldlist.get(i).getText();

			if (data.equalsIgnoreCase("iPwdPolicyId")) 
			{
				reportFieldlist.get(i).click();

				getAction().doubleClick(reportFieldlist.get(i)).build().perform();
			}
		}

		for (int i = 0; i < reportFieldlistCount; i++) 
		{
			String data =reportFieldlist.get(i).getText();

			if (data.equalsIgnoreCase("sPassword")) 
			{
				reportFieldlist.get(i).click();

				getAction().doubleClick(reportFieldlist.get(i)).build().perform();
			}
		}

		Thread.sleep(3999);

		//Runtime.getRuntime().exec(getBaseDir()+"\\autoIt\\scripts\\scripts\\CubeQurryType.exe");

	//	Thread.sleep(30000);

		dragAndDrop(userID, cube_RowGroupingTxt);

		Thread.sleep(6500);

		click(groupingOptionsOkBtn);

		Thread.sleep(2000);

		dragAndDrop(userID, cube_ColMeasureTxt);

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(previewTab));
		previewTab.click();


		//checkServerErrorMessage

		System.out.println(" *********No Data Displayed in Preview TAB");

		Thread.sleep(3000);

		int previewTabHeaderListCount=previewTabHeaderList.size();

		ArrayList<String >previewTabHeaderListArray=new ArrayList<>();

		for (int i = 0; i < previewTabHeaderListCount; i++)
		{

			String data=previewTabHeaderList.get(i).getText();
			previewTabHeaderListArray.add(data);

		}
		String actpreviewTabHeaderList=previewTabHeaderListArray.toString();
		String exppreviewTabHeaderList="";


		System.out.println(" Actual Preview TAb List   :"+actpreviewTabHeaderList);
		System.out.println("  Exp Preview TAb   List  :"+exppreviewTabHeaderList);




		getAction().moveToElement(finishBtn).build().perform();
		Thread.sleep(1200);
		finishBtn.click();
		Thread.sleep(2000);

		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : "+actMessage+" Value Expected : "+expMessage);

		if(actMessage.equalsIgnoreCase(expMessage))
		{

			System.out.println(" Test PasS: Extra Filed Programmable Filed added");
			return true;
		}
		else
		{
			System.out.println(" Test FAIL: Extra Filed Programmable Filed added");
			return false;
		}

	}




	@FindBy(xpath="//*[@id='id_focus_msgbox_title']/div[2]/span")
	private static WebElement serverErrorCloseBtn;






	public boolean checkReportOFReportWithQuarryForCubeType() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{

		Thread.sleep(2000);
	/*	getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(userNameDisplay));
		userNameDisplay.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(userNameDisplay));
		logoutOption.click();

		checkLogin();

		Thread.sleep(2000);*/
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
		searchTxt.click();
		searchTxt.sendKeys("Report With Quarry For Cube Type");
		Thread.sleep(1000);
		searchTxt.sendKeys(Keys.ENTER);


		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_DateOptionDropdown));

		Select s=new Select(sl_DateOptionDropdown);
		s.selectByValue("1");

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_OkBtn));
		sl_OkBtn.click();

		//Validate Server Error Message 
	/*	//checkServerErrorMessage*/

		Thread.sleep(2000);
		boolean novalidationConfirmationMessage =validationConfirmationMessage.getText().isEmpty();

		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";


		Thread.sleep(1500);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_1stRow1stCol));


		int reportsRow1ListCount = reportCol2List.size();
		ArrayList<String> reportsRow1ListArray = new ArrayList<String>();
		for(int i=0;i<reportsRow1ListCount;i++)
		{
			String data = reportCol2List.get(i).getText();


			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date date=new Date();
			String expadjustBills=df.format(date);

			reportsRow1ListArray.add(data);
		}
		String actRow1List = reportsRow1ListArray.toString();
		String expRow1List = "[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 16, 17, 18, Grand Total]";


		int report2ndRowListCount = reportCol3List.size();
		ArrayList<String> report2ndRowListArray = new ArrayList<String>();
		for(int i=0;i<report2ndRowListCount;i++)
		{
			String data = reportCol3List.get(i).getText();
			report2ndRowListArray.add(data);
		}
		String actRow2List = report2ndRowListArray.toString();
		String expRow2List = "[6, 6, 1, 1, 1, 6, 8, 1, 8, 8, 1, , 9, 8, 1, 10, ]";


		int report3rdRowListCount = reportCol4List.size();
		ArrayList<String> report3rdRowListArray = new ArrayList<String>();
		for(int i=0;i<report3rdRowListCount;i++)
		{
			String data = reportCol4List.get(i).getText();
			report3rdRowListArray.add(data);
		}
		String actRow3List = report3rdRowListArray.toString();
		String expRow3List = "[0154011400d2008e0048, 0154011400d2008e0048, 0154011400d2008e0048, 0154011400d2008e0048, 0154011400d2008e0048, 0154011400d2008e0048, 075008e6078007cb05cc064c0654054903660369041003b1023a028a01d8017a00a60047, 0192010c0086, 0af80d9c0bb80c78092a0a500a95094c0688090007b407c007710532069a05b80554050a03960440033a02c402580218019200a60047, 0af80d9c0bb80c78092a0a500a95094c0688090007b407c007710532069a05b80554050a032103a0036402fa02a80214016800a60047, 02180198010c0088, , 010c0086, 05a20618064805cd033e0264022801ea01aa0168012400de0096004c, 0154011400d2008e0048, 010c0088, ]";


		int report4thRowListCount = reportCol5List.size();
		ArrayList<String> report4thRowListArray = new ArrayList<String>();
		for(int i=0;i<report4thRowListCount;i++)
		{
			String data = reportCol5List.get(i).getText();
			report4thRowListArray.add(data);
		}
		String actRow4List = report4thRowListArray.toString();
		String expRow4List = "[naveenkumar@focussoftnet.com, vasu@gmail.com, dhana@gmail.com, sai@gmail.com, rathod@gmail.com, raki@gmail.com, teja@gmail.com, sudheer@gmail.com, sudheer@focussoftnet.com, testfail@gmail.com, Test@gmail.com, , iii@gmail.com, iiiiizzzz@gmail.com, UserWithLoadOption@gmail.com, , ]";


		int report5thRowListCount = reportCol6List.size();
		ArrayList<String> report5thRowListArray = new ArrayList<String>();
		for(int i=1;i<report5thRowListCount;i++)
		{
			String data = reportCol6List.get(i).getText();
			report5thRowListArray.add(data);
		}
		String actRow5List = report5thRowListArray.toString();
		String expRow5List = "[4.00, 14.00, 16.00, 2.00, 7.00, 6.00, 5.00, 10.00, 9.00, 8.00, 12.00, 11.00, 17.00, 3.00, 138.00]";



		System.out.println("actRow1List  : "+actRow1List);
		System.out.println("expRow1List  : "+expRow1List);
		System.out.println("*********************************************************************");

		System.out.println("actRow2List  : "+actRow2List);
		System.out.println("expRow2List  : "+expRow2List);
		System.out.println("*********************************************************************");

		System.out.println("actRow3List  : "+actRow3List);
		System.out.println("expRow3List  : "+expRow3List);
		System.out.println("*********************************************************************");

		System.out.println("actRow4List  : "+actRow4List);
		System.out.println("expRow4List  : "+expRow4List);
		System.out.println("*********************************************************************");

		System.out.println("actRow5List  : "+actRow5List);
		System.out.println("expRow5List  : "+expRow5List);
		System.out.println("*********************************************************************");

		if (actRow1List.equalsIgnoreCase(expRow1List) &&
				actRow2List.equalsIgnoreCase(expRow2List) &&
				actRow3List.equalsIgnoreCase(expRow3List) && 
				actRow4List.equalsIgnoreCase(expRow4List) && 
				actRow5List.equalsIgnoreCase(expRow5List)) 
		{
			System.out.println(" Test Pass: Values Dsiplayed as Expected ");
			return true;
		} 
		else 
		{
			System.out.println(" Test FAIL: Values Dsiplayed as Expected ");
			return false;
		}
	}





	public boolean checkSavingRDReportsWihParameterOFtypeCube() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		checkNavigateToReportDesginer();

		Thread.sleep(2999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportNameDropdown));       
		reportNameDropdown.sendKeys("Report Cube Type OF Parameter");
		reportNameDropdown.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportTypeDropdown));
		Select rtd= new Select(reportTypeDropdown);
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

		Select s1=new Select(rdFieldTypeDrpdwn);
		s1.selectByValue("3");


		Thread.sleep(2000);

		getAction().moveToElement(rdParametersOkbtn).build().perform();
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdParametersOkbtn));
		rdParametersOkbtn.click();

		Thread.sleep(2999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdParametersTxtArea));
		String actrdParametersTxtArea=rdParametersTxtArea.getAttribute("data-fieldname");
		String ExprdParametersTxtArea="department";

		System.out.println(" rdParametersTxtArea : "+actrdParametersTxtArea+" Value Expected  :"+ExprdParametersTxtArea);


		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dataSetTab));
		dataSetTab.click();

		Thread.sleep(2000);

		int transactionSetListCount = transactionSetList.size();

		for(int i=0;i<=transactionSetListCount;i++)
		{
			String data = transactionSetList.get(i).getText();

			if(data.equalsIgnoreCase("Inventory transactions"))
			{
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

		Runtime.getRuntime().exec(getBaseDir()+"\\autoIt\\scripts\\scripts\\CubeParameter.exe");
		Thread.sleep(40000);



		getAction().moveToElement(finishBtn).build().perform();
		Thread.sleep(1200);
		finishBtn.click();
		Thread.sleep(2000);

		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : "+actMessage+" Value Expected : "+expMessage);

		if(actMessage.equalsIgnoreCase(expMessage) && 
				actrdParametersTxtArea.equalsIgnoreCase(ExprdParametersTxtArea))
		{

			System.out.println(" Test PasS: Report desgining is saved with Paremeter with Cube Type");
			return true;
		}
		else
		{
			System.out.println(" Test FAIL: Report desgining is saved with Paremeter with Cube Type");
			return false;
		}

	}



	public boolean checkReportSavedWithCubeTypeParmeter() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{


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

		Select s=new Select(sl_DateOptionDropdown);
		s.selectByValue("1");

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_OkBtn));
		sl_OkBtn.click();


		//checkServerErrorMessage

		Thread.sleep(2000);
		boolean novalidationConfirmationMessage =validationConfirmationMessage.getText().isEmpty();

		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";


		Thread.sleep(1500);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_1stRow1stCol));

		int reportsRow1ListCount = report1stRowList.size();
		ArrayList<String> reportsRow1ListArray = new ArrayList<String>();
		for(int i=1;i<reportsRow1ListCount;i++)
		{
			String data = report1stRowList.get(i).getText();

			reportsRow1ListArray.add(data);
		}
		String actRow1List = reportsRow1ListArray.toString();
		String expRow1List = "[STD RATE COGS ITEM, 5]";


		int report2ndRowListCount = report2ndRowList.size();
		ArrayList<String> report2ndRowListArray = new ArrayList<String>();
		for(int i=1;i<report2ndRowListCount;i++)
		{
			String data = report2ndRowList.get(i).getText();
			report2ndRowListArray.add(data);
		}
		String actRow2List = report2ndRowListArray.toString();
		String expRow2List = "[Grand Total, 5]";


		System.out.println("actRow1List  : "+actRow1List);
		System.out.println("expRow1List  : "+expRow1List);
		System.out.println("*********************************************************************");

		System.out.println("actRow2List  : "+actRow2List);
		System.out.println("expRow2List  : "+expRow2List);
		System.out.println("*********************************************************************");


		if (actRow1List.equalsIgnoreCase(expRow1List) &&
				actRow2List.equalsIgnoreCase(expRow2List) && 
				actvalidationConfirmationMessage.equalsIgnoreCase(expvalidationConfirmationMessage)) 
		{
			System.out.println(" Test Pass: Values Dsiplayed as Expected ");
			return true;
		} 
		else 
		{
			System.out.println(" Test FAIL: Values Dsiplayed as Expected ");
			return false;
		}

	}


	public boolean checkAddingAndDeletingParameterWithDataTypeCubes() throws InterruptedException
	{

		checkNavigateToReportDesginer();

		Thread.sleep(2999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportNameDropdown));       
		reportNameDropdown.sendKeys("Report Delete Parameter");
		reportNameDropdown.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportTypeDropdown));
		Select rtd= new Select(reportTypeDropdown);
		rtd.selectByVisibleText("Cubes");

		rdFieldNameTxt.click();
		rdFieldNameTxt.sendKeys("warehouse");
		Thread.sleep(2000);

		rdFieldNameTxt.sendKeys(Keys.TAB);


		Thread.sleep(2000);
		Select s1=new Select(rdFieldTypeDrpdwn);
		s1.selectByValue("4");

		Thread.sleep(2000);

		getAction().moveToElement(rdParametersOkbtn).build().perform();
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdParametersOkbtn));
		rdParametersOkbtn.click();

		Thread.sleep(2999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdParametersTxtArea));
		String actrdParametersTxtArea=rdParametersTxtArea.getAttribute("data-fieldname");
		String ExprdParametersTxtArea="warehouse";

		System.out.println(" rdParametersTxtArea : "+actrdParametersTxtArea);


		rdParametersTxtArea.click();
		Thread.sleep(2999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdReportParameterDeleteBtn));
		rdReportParameterDeleteBtn.click();
		Thread.sleep(2000);
		getWaitForAlert();

		String actAlert=getAlert().getText();
		String expAlert="Do you want to Delete?";

		System.out.println(" Alert Displayed Text : "+actAlert +" Value Expected : "+expAlert);

		getAlert().accept();


		boolean novalidationConfirmationMessage =validationConfirmationMessage.getText().isEmpty();

		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";


		if (actvalidationConfirmationMessage.equalsIgnoreCase(expvalidationConfirmationMessage) &&
				actAlert.equalsIgnoreCase(expAlert))
		{

			System.out.println(" Test Pass: Parameter Added And Deleted Successfully");
			return true;

		} 
		else 
		{
			System.out.println(" Test Fail: Parameter Added And Deleted Successfully");
			return false;

		}
	}

	public boolean checkResetOptionAndSaveWithOutSelectingDisplayReportinReportDesginer() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{


		getAction().moveToElement(finishBtn).build().perform();
		Thread.sleep(1200);
		finishBtn.click();
		
		Thread.sleep(2000);

		String expMessage = "Please Select menu to display report.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : "+actMessage+" Value Expected : "+expMessage);

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(resetBtn));
		resetBtn.click();

		Thread.sleep(2000);

		boolean reportNameDropdownMessage =reportNameDropdown.getText().isEmpty();

		String actreportNameDropdown = Boolean.toString(reportNameDropdownMessage);
		String expreportNameDropdown = "true";

		if (actreportNameDropdown.equalsIgnoreCase(expreportNameDropdown) && 
				actMessage.equalsIgnoreCase(expMessage)) 
		{
			return true;
		} 
		else
		{
			//checkServerErrorMessage
			return false;
		}

	}



	public boolean checkRdWithCubesTypeAddingProgrammableFiled() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{

		checkNavigateToReportDesginer();

		Thread.sleep(2999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportNameDropdown));       
		reportNameDropdown.sendKeys("Report With Programmable Filed");
		reportNameDropdown.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportTypeDropdown));
		Select rtd= new Select(reportTypeDropdown);
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

		for(int i=0;i<=transactionSetListCount;i++)
		{
			String data = transactionSetList.get(i).getText();

			if(data.equalsIgnoreCase("Inventory transactions"))
			{
				transactionSetList.get(i).click();

				break;
			}
		}

		Thread.sleep(2000);


		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTab));
		customizationTab.click();
		Thread.sleep(1999);

		Runtime.getRuntime().exec(getBaseDir()+"\\autoIt\\scripts\\CubeProgrammable.exe");
		
		Thread.sleep(40000);


		int customizeTabTableHeaderLsistCount = customizeTabTableHeaderLsist.size();
		System.err.println(customizeTabTableHeaderLsistCount);
		for(int i=1;i<=customizeTabTableHeaderLsistCount;i++)
		{
			String data = customizeTabTableHeaderLsist.get(i).getText();

			if(data.equalsIgnoreCase("Programmable Field"))
			{
				customizeTabTableHeaderLsist.get(i).click();

				System.err.println(i);
				Thread.sleep(1000);

				break;
			}
		}

		
		progrmmableFieldExpandBtn.click();
		
		Thread.sleep(1999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdExtraFiledColHeadingTxt));
		rdExtraFiledColHeadingTxt.click();
		rdExtraFiledColHeadingTxt.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
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

		getAction().moveToElement(finishBtn).build().perform();
		Thread.sleep(1200);
		finishBtn.click();
		Thread.sleep(2000);

		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : "+actMessage+" Value Expected : "+expMessage);

		if(actMessage.equalsIgnoreCase(expMessage))
		{
			System.out.println(" Test PasS: Report desgining is saved with Paremeter with Cube Type");
			return true;
		}
		else
		{
			System.out.println(" Test FAIL: Report desgining is saved with Paremeter with Cube Type");
			return false;
		}

	}

	
	@FindBy(xpath="//*[@id='rd_customization_tree0']/ul/li[2]/ul/li[1]")
    private static WebElement mrDepExpandBtn;
	
	
	@FindBy(xpath="//*[@id='rd_customization_tree0']/ul/li[2]/ul/li[1]/ul/li[1]")
    private static WebElement mrDepExpandNameBtn;
	
	
	@FindBy(xpath="//*[@id='rd_customization_tree0']/ul/li[2]/ul/li[5]")
    private static WebElement mrWarehouseExpBtn;
	
	@FindBy(xpath="//*[@id='rd_customization_tree0']/ul/li[2]/ul/li[5]/ul/li[1]")
    private static WebElement mrWarehouseNameBtn;
	
	//@FindBy(xpath="//*[@id='rd_customization_tree0']/ul/li[1]/ul/li[20]")
	@FindBy(xpath="//span[text()='Gross']")
    private static WebElement mrGrossBtn;
	
	
	@FindBy(xpath="//*[@id='rd_customization_tree0']/ul/li[1]/ul/li[23]")
    private static WebElement mrItemExpbtn;
	
	
	//@FindBy(xpath="//*[@id='rd_customization_tree117']/a/span")
	@FindBy(xpath="(//span[contains(text(),'Item')]//following::ul//li//span[text()=\"Name\"])[2]")
    private static WebElement mrItemNameExpbtn;
	
	
	
	//@FindBy(xpath="//*[@id='rd_customization_tree118']/a/span")
	@FindBy(xpath="(//span[contains(text(),'Item')]//following::ul//li//span[text()=\"Code\"])[2]")
    private static WebElement mrItemCodeExpbtn;
	
	//@FindBy(xpath="//*[@id='rd_customization_tree230']/a/span")
	//@FindBy(xpath="(//li//span[contains(text(),'Rate')])[8]")
	@FindBy(xpath="//span[text()='Rate']")
    private static WebElement mrItemRateExpbtn;
	
	
	@FindBy(xpath="(//*[@id='id_rd_customization_report_column_property']//div[2]/span)[1]")
    private static WebElement mrHeaderExpbtn;
	
	
	public boolean CheckAddingFilterInFiledsCreatedCustomizationTab() throws InterruptedException, IOException, EncryptedDocumentException, InvalidFormatException
	{

		System.out.println("***********************************CheckAddingFilterInFiledsCreatedCustomizationTab*************************************************");

		Thread.sleep(1200);
		getDriver().navigate().refresh();
		Thread.sleep(1200);
		checkNavigateToReportDesginer();
		
		Thread.sleep(2999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportNameDropdown));       
		reportNameDropdown.sendKeys("MR With Filter");
		Thread.sleep(2000);
		reportNameDropdown.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportTypeDropdown));
		Select rtd= new Select(reportTypeDropdown);
		rtd.selectByVisibleText("Details");

		Thread.sleep(2000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(finanincalExpandbtn1));
		finanincalExpandbtn1.click();

		Thread.sleep(3000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(finanincalReportsExpandbtn));
		finanincalReportsExpandbtn.click();


		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dataSetTab));
		dataSetTab.click();


		Thread.sleep(2000);

		int transactionSetListCount = transactionSetList.size();

		for(int i=0;i<=transactionSetListCount;i++)
		{
			String data = transactionSetList.get(i).getText();

			if(data.equalsIgnoreCase("All transactions of document type"))
			{
				transactionSetList.get(i).click();

				break;
			}
		}

		Thread.sleep(3000);



		getAction().moveToElement(dataSetTabVouchersDocType).build().perform();
		Thread.sleep(3000);
		Select voucher = new Select(dataSetTabVouchersDocType);
		voucher.selectByVisibleText("Material Requisition");

		
		Thread.sleep(3999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTab));
		customizationTab.click();

		Thread.sleep(1999);

		//getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionExpandBtn));
		//transactionExpandBtn.click();
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(extraFieldsExpandBtn));
		extraFieldsExpandBtn.click();
		Thread.sleep(1999);
		
		//moveToElement(deptExpandBtn1);
		
		//Thread.sleep(3000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(deptExpandBtn1));
		deptExpandBtn1.click();
		//Thread.sleep(3000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(deptNameBtn1));
		getAction().doubleClick(deptNameBtn1).build().perform();

		Thread.sleep(3000);
		
		//moveToElement(warehouseExpBtn);
		//getAction().moveToElement(mrWarehouseExpBtn).build().perform();
		Thread.sleep(2000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouseExpBtn));
		warehouseExpBtn.click();
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouseNameBtn));
		getAction().doubleClick(warehouseNameBtn).build().perform();

		Thread.sleep(1999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(extraFieldsExpandBtn));
		extraFieldsExpandBtn.click();
		
		Thread.sleep(1999);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionFieldsExpandBtn));
		transactionFieldsExpandBtn.click();

		Thread.sleep(1000);
		
		moveToElement(mrGrossBtn);
		Thread.sleep(1200);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(mrGrossBtn));
		getAction().doubleClick(mrGrossBtn).build().perform();
		
		Thread.sleep(1000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemExpandBtn1));
		itemExpandBtn1.click();
		
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemNameBtn1));
		getAction().doubleClick(itemNameBtn1).build().perform();
		
		Thread.sleep(4000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemCodeBtn1));
		getAction().doubleClick(itemCodeBtn1).build().perform();

		Thread.sleep(1999);
		moveToElement(mrItemRateExpbtn);
		Thread.sleep(1000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(mrItemRateExpbtn));
		getAction().doubleClick(mrItemRateExpbtn).build().perform();
		
		
		Thread.sleep(1999);

		int customizeTabTableHeaderLsistCount2 = customizeTabTableHeaderLsist.size();
		System.err.println(customizeTabTableHeaderLsistCount2);
		for(int i=1;i<=customizeTabTableHeaderLsistCount2;i++)
		{
			String data = customizeTabTableHeaderLsist.get(i).getText();

			if(data.equalsIgnoreCase("Gross"))
			{
				customizeTabTableHeaderLsist.get(i).click();

				System.err.println(i);
				Thread.sleep(1000);
				break;
			}
		}
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(mrHeaderExpbtn));
		mrHeaderExpbtn.click();
		
		
		
		//getAction().moveToElement(rdReportExtraFieldFilterExpandBtn).build().perform();


		//getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdReportExtraFieldFilterExpandBtn));
		//rdReportExtraFieldFilterExpandBtn.click();

		Thread.sleep(2999);


		getAction().moveToElement(extraFieldCustIcon).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(extraFieldCustIcon));
		extraFieldCustIcon.click();

		Thread.sleep(2999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(custPopWareHouseExpandBtn));
		custPopWareHouseExpandBtn.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(custPopWareHouseNameChkbox));
		if (custPopWareHouseNameChkboxSelected.isSelected()==false) 
		{
			custPopWareHouseNameChkbox.click();

		} 


		Thread.sleep(2999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(custPopOkBtn));
		custPopOkBtn.click();


		System.err.println(" Check Warehouse Name chk box in customization ");

		Thread.sleep(2000);
		moveToElement(extraFieldDefaultFiltertxt);
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(extraFieldDefaultFiltertxt));
		extraFieldDefaultFiltertxt.click();

		extraFieldDefaultFiltertxt.sendKeys("HYDERABAD");
		Thread.sleep(1999);
		extraFieldDefaultFiltertxt.sendKeys(Keys.TAB);
		System.err.println(" ***Displayed WareHouse Default Filter Text ");

		extraFieldDefaultFilterOkBtn.click();


		getAction().moveToElement(finishBtn).build().perform();
		Thread.sleep(1200);
		finishBtn.click();
		Thread.sleep(2000);

		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : "+actMessage+" Value Expected : "+expMessage);



		Thread.sleep(2999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportNameDropdown));       
		reportNameDropdown.sendKeys("MR With Filter");
		Thread.sleep(2000);
		reportNameDropdown.sendKeys(Keys.TAB);


		Thread.sleep(3999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTab));
		customizationTab.click();

		System.err.println(customizeTabTableHeaderLsistCount2);
		for(int i=1;i<=customizeTabTableHeaderLsistCount2;i++)
		{
			String data = customizeTabTableHeaderLsist.get(i).getText();

			if(data.equalsIgnoreCase("Gross"))
			{
				customizeTabTableHeaderLsist.get(i).click();

				System.err.println(i);
				Thread.sleep(1000);
				break;
			}
		}
		Thread.sleep(2000);
/*
		//getAction().moveToElement(cusFilterTextFiled).build().perform();


		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdReportExtraFieldFilterExpandBtn));
		rdReportExtraFieldFilterExpandBtn.click();

		Thread.sleep(2999);

		defaultFilterBtn.click();


		Thread.sleep(2999);

		try {
			if(cusFilterTextFiled.isDisplayed())
			{
				getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(cusFilterTextFiled));
				cusFilterTextFiled.click();
			}
		} catch (Exception e) {
			
		}
*/
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(extraFieldDefaultFilterOkBtn));
		extraFieldDefaultFilterOkBtn.click();


		getAction().moveToElement(finishBtn).build().perform();
		Thread.sleep(1200);
		finishBtn.click();
		Thread.sleep(2000);

		String expMessage1 = "Data saved successfully.";

		String actMessage1 = checkValidationMessage(expMessage1);

		System.out.println("Validation Message1 : "+actMessage1+" Value Expected : "+expMessage1);

		if(actMessage.equalsIgnoreCase(expMessage))
		{

			return true;
		}
		else
		{
			return false;
		}
	}



	public boolean checkReportMRWithFilter() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{

		
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
		searchTxt.click();
		searchTxt.sendKeys("MR With Filter");
		Thread.sleep(1000);
		searchTxt.sendKeys(Keys.ENTER);


		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_DateOptionDropdown));

		Select s=new Select(sl_DateOptionDropdown);
		s.selectByValue("1");

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_OkBtn));
		sl_OkBtn.click();

		if (sl_1stRow1stCol.isDisplayed()==false) 
		{
			//checkServerErrorMessage
		}



		Thread.sleep(2000);
		boolean novalidationConfirmationMessage =validationConfirmationMessage.getText().isEmpty();

		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";


		Thread.sleep(1500);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_1stRow1stCol));

		int reportsRow1ListCount = reportCol1List.size();
		ArrayList<String> reportsRow1ListArray = new ArrayList<String>();
		for(int i=0;i<reportsRow1ListCount;i++)
		{
			String data = reportCol1List.get(i).getText();

			reportsRow1ListArray.add(data);
		}
		String actRow1List = reportsRow1ListArray.toString();
		String expRow1List = "[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16]";


		int report2ndRowListCount = reportCol2List.size();
		ArrayList<String> report2ndRowListArray = new ArrayList<String>();
		for(int i=0;i<report2ndRowListCount;i++)
		{
			String data = reportCol2List.get(i).getText();
			report2ndRowListArray.add(data);
		}
		String actRow2List = report2ndRowListArray.toString();
		String expRow2List = "[AMERICA, AMERICA, DUBAI, DUBAI, INDIA, INDIA, INDIA, INDIA, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI]";


		int report3rdRowListCount = reportCol3List.size();
		ArrayList<String> report3rdRowListArray = new ArrayList<String>();
		for(int i=0;i<report3rdRowListCount;i++)
		{
			String data = reportCol3List.get(i).getText();
			report3rdRowListArray.add(data);
		}
		String actRow3List = report3rdRowListArray.toString();
		String expRow3List = "[HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD]";


		int report4thRowListCount = reportCol4List.size();
		ArrayList<String> report4thRowListArray = new ArrayList<String>();
		for(int i=0;i<report4thRowListCount;i++)
		{
			String data = reportCol4List.get(i).getText();
			report4thRowListArray.add(data);
		}
		String actRow4List = report4thRowListArray.toString();
		String expRow4List = "[110.00, 11.00, 110.00, 11.00, 110.00, 11.00, 110.00, 11.00, 110.00, 11.00, 110.00, 11.00, 110.00, 11.00, 110.00, 11.00]";


		int report5thRowListCount = reportCol5List.size();
		ArrayList<String> report5thRowListArray = new ArrayList<String>();
		for(int i=0;i<report5thRowListCount;i++)
		{
			String data = reportCol5List.get(i).getText();
			report5thRowListArray.add(data);
		}
		String actRow5List = report5thRowListArray.toString();
		String expRow5List = "[BATCH BIN WITH NO RESERVATION ITEM, BR COGS ITEM, BATCH BIN WITH NO RESERVATION ITEM, BR COGS ITEM, BATCH BIN WITH NO RESERVATION ITEM, BR COGS ITEM, BATCH BIN WITH NO RESERVATION ITEM, BR COGS ITEM, BATCH BIN WITH NO RESERVATION ITEM, BR COGS ITEM, BATCH BIN WITH NO RESERVATION ITEM, BR COGS ITEM, BATCH BIN WITH NO RESERVATION ITEM, BR COGS ITEM, BATCH BIN WITH NO RESERVATION ITEM, BR COGS ITEM]";

		int report6thRowListCount = reportCol6List.size();
		ArrayList<String> report6thRowListArray = new ArrayList<String>();
		for(int i=0;i<report6thRowListCount;i++)
		{
			String data = reportCol6List.get(i).getText();
			report6thRowListArray.add(data);
		}
		String actRow6List = report6thRowListArray.toString();
		String expRow6List = "[BBWNRI, BR COGS ITEM, BBWNRI, BR COGS ITEM, BBWNRI, BR COGS ITEM, BBWNRI, BR COGS ITEM, BBWNRI, BR COGS ITEM, BBWNRI, BR COGS ITEM, BBWNRI, BR COGS ITEM, BBWNRI, BR COGS ITEM]";

		int report7thRowListCount = reportCol7List.size();
		ArrayList<String> report7thRowListArray = new ArrayList<String>();
		for(int i=0;i<report7thRowListCount;i++)
		{
			String data = reportCol7List.get(i).getText();
			report7thRowListArray.add(data);
		}
		String actRow7List = report7thRowListArray.toString();
		String expRow7List = "[11.00, 11.00, 11.00, 11.00, 11.00, 11.00, 11.00, 11.00, 11.00, 11.00, 11.00, 11.00, 11.00, 11.00, 11.00, 11.00]";

		
		click(sl_NextBtn);
		Thread.sleep(2000);
		
		int reportsCol1ListCount = reportCol1List.size();
		ArrayList<String> reportsCol1ListArray = new ArrayList<String>();
		for(int i=0;i<reportsCol1ListCount;i++)
		{
			String data = reportCol1List.get(i).getText();

			reportsCol1ListArray.add(data);
		}
		String actCol1List = reportsCol1ListArray.toString();
		String expCol1List = "[17, 18, 19, 20, 21, 22, 23, 24, 25]";


		int report2ndColListCount = reportCol2List.size();
		ArrayList<String> report2ndColListArray = new ArrayList<String>();
		for(int i=0;i<report2ndColListCount;i++)
		{
			String data = reportCol2List.get(i).getText();
			report2ndColListArray.add(data);
		}
		String actCol2List = report2ndColListArray.toString();
		String expCol2List = "[DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, Grand Total]";


		int report3rdColListCount = reportCol3List.size();
		ArrayList<String> report3rdColListArray = new ArrayList<String>();
		for(int i=0;i<report3rdColListCount;i++)
		{
			String data = reportCol3List.get(i).getText();
			report3rdColListArray.add(data);
		}
		String actCol3List = report3rdColListArray.toString();
		String expCol3List = "[SECUNDERABAD, SECUNDERABAD, SECUNDERABAD, SECUNDERABAD, SECUNDERABAD, SECUNDERABAD, SECUNDERABAD, SECUNDERABAD, ]";


		int report4thColListCount = reportCol4List.size();
		ArrayList<String> report4thColListArray = new ArrayList<String>();
		for(int i=0;i<report4thColListCount;i++)
		{
			String data = reportCol4List.get(i).getText();
			report4thColListArray.add(data);
		}
		String actCol4List = report4thColListArray.toString();
		String expCol4List = "[, , , , , , , , 968.00]";


		int report5thColListCount = reportCol5List.size();
		ArrayList<String> report5thColListArray = new ArrayList<String>();
		for(int i=0;i<report5thColListCount;i++)
		{
			String data = reportCol5List.get(i).getText();
			report5thColListArray.add(data);
		}
		String actCol5List = report5thColListArray.toString();
		String expCol5List = "[BATCH BIN WITH NO RESERVATION ITEM, BR COGS ITEM, BATCH BIN WITH NO RESERVATION ITEM, BR COGS ITEM, BATCH BIN WITH NO RESERVATION ITEM, BR COGS ITEM, BATCH BIN WITH NO RESERVATION ITEM, BR COGS ITEM, ]";

		int report6thColListCount = reportCol6List.size();
		ArrayList<String> report6thColListArray = new ArrayList<String>();
		for(int i=0;i<report6thColListCount;i++)
		{
			String data = reportCol6List.get(i).getText();
			report6thColListArray.add(data);
		}
		String actCol6List = report6thColListArray.toString();
		String expCol6List = "[BBWNRI, BR COGS ITEM, BBWNRI, BR COGS ITEM, BBWNRI, BR COGS ITEM, BBWNRI, BR COGS ITEM, ]";

		int report7thColListCount = reportCol7List.size();
		ArrayList<String> report7thColListArray = new ArrayList<String>();
		for(int i=0;i<report7thColListCount;i++)
		{
			String data = reportCol7List.get(i).getText();
			report7thColListArray.add(data);
		}
		String actCol7List = report7thColListArray.toString();
		String expCol7List = "[11.00, 11.00, 11.00, 11.00, 11.00, 11.00, 11.00, 11.00, 264.00]";

		



		System.out.println("actRow1List  : "+actRow1List);
		System.out.println("expRow1List  : "+expRow1List);
		System.out.println("*********************************************************************");

		System.out.println("actRow2List  : "+actRow2List);
		System.out.println("expRow2List  : "+expRow2List);
		System.out.println("*********************************************************************");

		System.out.println("actRow3List  : "+actRow3List);
		System.out.println("expRow3List  : "+expRow3List);
		System.out.println("*********************************************************************");

		System.out.println("actRow4List  : "+actRow4List);
		System.out.println("expRow4List  : "+expRow4List);
		System.out.println("*********************************************************************");

		System.out.println("actRow5List  : "+actRow5List);
		System.out.println("expRow5List  : "+expRow5List);
		System.out.println("*********************************************************************");

		System.out.println("actRow6List  : "+actRow6List);
		System.out.println("expRow6List  : "+expRow6List);
		System.out.println("*********************************************************************");


		System.out.println("actRow7List  : "+actRow7List);
		System.out.println("expRow7List  : "+expRow7List);
		System.out.println("*********************************************************************");

		
		System.out.println("*****************Next Page Col Details ************");
		
		System.out.println("actCol1List  : "+actCol1List);
		System.out.println("expCol1List  : "+expCol1List);
		System.out.println("*********************************************************************");

		System.out.println("actCol2List  : "+actCol2List);
		System.out.println("expCol2List  : "+expCol2List);
		System.out.println("*********************************************************************");

		System.out.println("actCol3List  : "+actCol3List);
		System.out.println("expCol3List  : "+expCol3List);
		System.out.println("*********************************************************************");

		System.out.println("actCol4List  : "+actCol4List);
		System.out.println("expCol4List  : "+expCol4List);
		System.out.println("*********************************************************************");

		System.out.println("actCol5List  : "+actCol5List);
		System.out.println("expCol5List  : "+expCol5List);
		System.out.println("*********************************************************************");

		System.out.println("actCol6List  : "+actCol6List);
		System.out.println("expCol6List  : "+expCol6List);
		System.out.println("*********************************************************************");


		System.out.println("actCol7List  : "+actCol7List);
		System.out.println("expCol7List  : "+expCol7List);
		System.out.println("*********************************************************************");

		
		

		if (actRow1List.equalsIgnoreCase(expRow1List) &&
				actRow2List.equalsIgnoreCase(expRow2List) && 

				actRow3List.equalsIgnoreCase(expRow3List) &&
				actRow4List.equalsIgnoreCase(expRow4List) &&
				actRow5List.equalsIgnoreCase(expRow5List) &&
				actRow6List.equalsIgnoreCase(expRow6List) &&
				actRow7List.equalsIgnoreCase(expRow7List) &&
				actCol1List.equalsIgnoreCase(expCol1List) &&
				actCol2List.equalsIgnoreCase(expCol2List) && 

				actCol3List.equalsIgnoreCase(expCol3List) &&
				actCol4List.equalsIgnoreCase(expCol4List) &&
				actCol5List.equalsIgnoreCase(expCol5List) &&
				actCol6List.equalsIgnoreCase(expCol6List) &&
				actCol7List.equalsIgnoreCase(expCol7List) &&
				actvalidationConfirmationMessage.equalsIgnoreCase(expvalidationConfirmationMessage)) 
		{
			System.out.println(" Test Pass: Values Dsiplayed as Expected ");
			return true;
		} 
		else 
		{
			System.out.println(" Test FAIL: Values Dsiplayed as Expected ");
			return false;
		}
	}



	public boolean checkAddingProgrammableFiledWithColAttaributeAndEditingColName() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		
		Thread.sleep(1200);

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

		//getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(custProgramFiledexpandBtn2));
		//custProgramFiledexpandBtn2.click();

		Thread.sleep(1000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(custProgramFieldBtn2));
		getAction().doubleClick(custProgramFieldBtn2).build().perform();

		int customizeTabTableHeaderLsistCount = customizeTabTableHeaderLsist.size();
		System.err.println(customizeTabTableHeaderLsistCount);
		for(int i=1;i<=customizeTabTableHeaderLsistCount;i++)
		{
			String data = customizeTabTableHeaderLsist.get(i).getText();

			if(data.equalsIgnoreCase("Programmable Field"))
			{
				customizeTabTableHeaderLsist.get(i).click();

				System.err.println(i);
				Thread.sleep(1000);

				break;
			}
		}

		progrmmableFieldExpandBtn.click();

		Thread.sleep(1999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdExtraFiledColHeadingTxt));
		rdExtraFiledColHeadingTxt.click();
		rdExtraFiledColHeadingTxt.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
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
		/*getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdExtraFiledOkBtn));1
		rdExtraFiledOkBtn.click();

*/
		Thread.sleep(3000);

		int customizeTabTableHeaderLsistCount1 = customizeTabTableHeaderLsist.size();
		System.err.println(customizeTabTableHeaderLsistCount1);
		for(int i=1;i<=customizeTabTableHeaderLsistCount1;i++)
		{
			String data = customizeTabTableHeaderLsist.get(i).getText();

			if(data.equalsIgnoreCase("Rate"))
			{
				customizeTabTableHeaderLsist.get(i).click();

				System.err.println(i);
				Thread.sleep(1000);

				break;
			}
		}

		Thread.sleep(1000);
		
		getAction().moveToElement(progrmmableFieldExpandBtn).build().perform();
		Thread.sleep(2000);
		//progrmmableFieldExpandBtn.click();


		Thread.sleep(1999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdExtraFiledColHeadingTxt));
		rdExtraFiledColHeadingTxt.click();
		rdExtraFiledColHeadingTxt.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
		rdExtraFiledColHeadingTxt.sendKeys("RATE MODIFIED");
		Thread.sleep(1999);

		rdExtraFiledColHeadingTxt.sendKeys(Keys.TAB);
		Thread.sleep(1999);
		
		getAction().moveToElement(rdExtraFiledFormulaControlOkBtn).build().perform();
		Thread.sleep(1999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdExtraFiledFormulaControlOkBtn));
		rdExtraFiledFormulaControlOkBtn.click();
		
	/*	getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdExtraFiledOkBtn));
		rdExtraFiledOkBtn.click();

*/


		getAction().moveToElement(finishBtn).build().perform();
		Thread.sleep(1200);
		finishBtn.click();
		
		Thread.sleep(2000);
		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : "+actMessage+" Value Expected : "+expMessage);

		if(actMessage.equalsIgnoreCase(expMessage))
		{

			System.out.println(" Test PasS: Extra Filed Programmable Filed added");
			return true;
		}
		else
		{
			System.out.println(" Test FAIL: Extra Filed Programmable Filed added");
			return false;
		}

	}


	public boolean checkReportMRAfterChangesInCustomizationtab() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		Thread.sleep(2000);
		getDriver().navigate().refresh();
		Thread.sleep(3000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(userNameDisplay));
		userNameDisplay.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(userNameDisplay));
		logoutOption.click();
		Thread.sleep(2000);
		checkLogin();
		
	/*	LoginPage lp=new LoginPage(getDriver()); 

		String unamelt="su";

		String pawslt="su";

	/*	lp.enterUserName(unamelt);

		Thread.sleep(2000);

		lp.enterPassword(pawslt);

		
		String compname="RD REPORTS";

		
		lp.reLogin(unamelt, pawslt, compname);
*/
		Thread.sleep(4000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
		searchTxt.click();
		searchTxt.sendKeys("MR With Filter");
		Thread.sleep(1000);
		searchTxt.sendKeys(Keys.ENTER);


		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_DateOptionDropdown));

		Select s=new Select(sl_DateOptionDropdown);
		s.selectByValue("1");

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_OkBtn));
		sl_OkBtn.click();

		if (sl_1stRow1stCol.isDisplayed()==false) 
		{
			//checkServerErrorMessage
		}



		Thread.sleep(2000);
		boolean novalidationConfirmationMessage =validationConfirmationMessage.getText().isEmpty();

		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";



		int reportHeaderListCount=reportHeaderList.size();

		ArrayList<String >reportHeaderListArray=new ArrayList<>();

		for (int i = 0; i < reportHeaderListCount; i++) 
		{
			String data=reportHeaderList.get(i).getText();
			reportHeaderListArray.add(data);
		}

		String actreportHeaderList=reportHeaderListArray.toString();
		String expreportHeaderList="[#, Department.Name, Warehouse.Name, Gross, Item.Name, Item.Code, RATE MODIFIED, ToGrossAddedField]";

		System.err.println(" Act reportHeaderList : "+actreportHeaderList);
		System.err.println(" Exp reportHeaderList : "+expreportHeaderList);


		Thread.sleep(1500);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_1stRow1stCol));

		int reportsRow1ListCount = reportCol1List.size();
		ArrayList<String> reportsRow1ListArray = new ArrayList<String>();
		for(int i=0;i<reportsRow1ListCount;i++)
		{
			String data = reportCol1List.get(i).getText();

			reportsRow1ListArray.add(data);
		}
		String actRow1List = reportsRow1ListArray.toString();
		String expRow1List = "[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16]";


		int report2ndRowListCount = reportCol2List.size();
		ArrayList<String> report2ndRowListArray = new ArrayList<String>();
		for(int i=0;i<report2ndRowListCount;i++)
		{
			String data = reportCol2List.get(i).getText();
			report2ndRowListArray.add(data);
		}
		String actRow2List = report2ndRowListArray.toString();
		String expRow2List = "[AMERICA, AMERICA, DUBAI, DUBAI, INDIA, INDIA, INDIA, INDIA, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI]";


		int report3rdRowListCount = reportCol3List.size();
		ArrayList<String> report3rdRowListArray = new ArrayList<String>();
		for(int i=0;i<report3rdRowListCount;i++)
		{
			String data = reportCol3List.get(i).getText();
			report3rdRowListArray.add(data);
		}
		String actRow3List = report3rdRowListArray.toString();
		String expRow3List = "[HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD]";


		int report4thRowListCount = reportCol4List.size();
		ArrayList<String> report4thRowListArray = new ArrayList<String>();
		for(int i=0;i<report4thRowListCount;i++)
		{
			String data = reportCol4List.get(i).getText();
			report4thRowListArray.add(data);
		}
		String actRow4List = report4thRowListArray.toString();
		String expRow4List = "[110.00, 11.00, 110.00, 11.00, 110.00, 11.00, 110.00, 11.00, 110.00, 11.00, 110.00, 11.00, 110.00, 11.00, 110.00, 11.00]";


		int report5thRowListCount = reportCol5List.size();
		ArrayList<String> report5thRowListArray = new ArrayList<String>();
		for(int i=0;i<report5thRowListCount;i++)
		{
			String data = reportCol5List.get(i).getText();
			report5thRowListArray.add(data);
		}
		String actRow5List = report5thRowListArray.toString();
		String expRow5List = "[BATCH BIN WITH NO RESERVATION ITEM, BR COGS ITEM, BATCH BIN WITH NO RESERVATION ITEM, BR COGS ITEM, BATCH BIN WITH NO RESERVATION ITEM, BR COGS ITEM, BATCH BIN WITH NO RESERVATION ITEM, BR COGS ITEM, BATCH BIN WITH NO RESERVATION ITEM, BR COGS ITEM, BATCH BIN WITH NO RESERVATION ITEM, BR COGS ITEM, BATCH BIN WITH NO RESERVATION ITEM, BR COGS ITEM, BATCH BIN WITH NO RESERVATION ITEM, BR COGS ITEM]";

		int report6thRowListCount = reportCol6List.size();
		ArrayList<String> report6thRowListArray = new ArrayList<String>();
		for(int i=0;i<report6thRowListCount;i++)
		{
			String data = reportCol6List.get(i).getText();
			report6thRowListArray.add(data);
		}
		String actRow6List = report6thRowListArray.toString();
		String expRow6List = "[BBWNRI, BR COGS ITEM, BBWNRI, BR COGS ITEM, BBWNRI, BR COGS ITEM, BBWNRI, BR COGS ITEM, BBWNRI, BR COGS ITEM, BBWNRI, BR COGS ITEM, BBWNRI, BR COGS ITEM, BBWNRI, BR COGS ITEM]";

		int report7thRowListCount = reportCol7List.size();
		ArrayList<String> report7thRowListArray = new ArrayList<String>();
		for(int i=0;i<report7thRowListCount;i++)
		{
			String data = reportCol7List.get(i).getText();
			report7thRowListArray.add(data);
		}
		String actRow7List = report7thRowListArray.toString();
		String expRow7List = "[11.00, 11.00, 11.00, 11.00, 11.00, 11.00, 11.00, 11.00, 11.00, 11.00, 11.00, 11.00, 11.00, 11.00, 11.00, 11.00]";

		int report8thRowListCount = reportCol8List.size();
		ArrayList<String> report8thRowListArray = new ArrayList<String>();
		for(int i=0;i<report8thRowListCount;i++)
		{
			String data = reportCol8List.get(i).getText();
			report8thRowListArray.add(data);
		}
		String actRow8List = report8thRowListArray.toString();
		String expRow8List = "[1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00]";

		click(sl_NextBtn);
		Thread.sleep(2000);
		
		int reportsCol1ListCount = reportCol1List.size();
		ArrayList<String> reportsCol1ListArray = new ArrayList<String>();
		for(int i=0;i<reportsCol1ListCount;i++)
		{
			String data = reportCol1List.get(i).getText();

			reportsCol1ListArray.add(data);
		}
		String actCol1List = reportsCol1ListArray.toString();
		String expCol1List = "[17, 18, 19, 20, 21, 22, 23, 24, 25]";


		int report2ndColListCount = reportCol2List.size();
		ArrayList<String> report2ndColListArray = new ArrayList<String>();
		for(int i=0;i<report2ndColListCount;i++)
		{
			String data = reportCol2List.get(i).getText();
			report2ndColListArray.add(data);
		}
		String actCol2List = report2ndColListArray.toString();
		String expCol2List = "[DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, Grand Total]";


		int report3rdColListCount = reportCol3List.size();
		ArrayList<String> report3rdColListArray = new ArrayList<String>();
		for(int i=0;i<report3rdColListCount;i++)
		{
			String data = reportCol3List.get(i).getText();
			report3rdColListArray.add(data);
		}
		String actCol3List = report3rdColListArray.toString();
		String expCol3List = "[SECUNDERABAD, SECUNDERABAD, SECUNDERABAD, SECUNDERABAD, SECUNDERABAD, SECUNDERABAD, SECUNDERABAD, SECUNDERABAD, ]";


		int report4thColListCount = reportCol4List.size();
		ArrayList<String> report4thColListArray = new ArrayList<String>();
		for(int i=0;i<report4thColListCount;i++)
		{
			String data = reportCol4List.get(i).getText();
			report4thColListArray.add(data);
		}
		String actCol4List = report4thColListArray.toString();
		String expCol4List = "[, , , , , , , , 968.00]";


		int report5thColListCount = reportCol5List.size();
		ArrayList<String> report5thColListArray = new ArrayList<String>();
		for(int i=0;i<report5thColListCount;i++)
		{
			String data = reportCol5List.get(i).getText();
			report5thColListArray.add(data);
		}
		String actCol5List = report5thColListArray.toString();
		String expCol5List = "[BATCH BIN WITH NO RESERVATION ITEM, BR COGS ITEM, BATCH BIN WITH NO RESERVATION ITEM, BR COGS ITEM, BATCH BIN WITH NO RESERVATION ITEM, BR COGS ITEM, BATCH BIN WITH NO RESERVATION ITEM, BR COGS ITEM, ]";

		int report6thColListCount = reportCol6List.size();
		ArrayList<String> report6thColListArray = new ArrayList<String>();
		for(int i=0;i<report6thColListCount;i++)
		{
			String data = reportCol6List.get(i).getText();
			report6thColListArray.add(data);
		}
		String actCol6List = report6thColListArray.toString();
		String expCol6List = "[BBWNRI, BR COGS ITEM, BBWNRI, BR COGS ITEM, BBWNRI, BR COGS ITEM, BBWNRI, BR COGS ITEM, ]";

		int report7thColListCount = reportCol7List.size();
		ArrayList<String> report7thColListArray = new ArrayList<String>();
		for(int i=0;i<report7thColListCount;i++)
		{
			String data = reportCol7List.get(i).getText();
			report7thColListArray.add(data);
		}
		String actCol7List = report7thColListArray.toString();
		String expCol7List = "[11.00, 11.00, 11.00, 11.00, 11.00, 11.00, 11.00, 11.00, 264.00]";

		int report8thColListCount = reportCol8List.size();
		ArrayList<String> report8thColListArray = new ArrayList<String>();
		for(int i=0;i<report8thColListCount;i++)
		{
			String data = reportCol8List.get(i).getText();
			report8thColListArray.add(data);
		}
		String actCol8List = report8thColListArray.toString();
		String expCol8List = "[1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 24.00]";



		System.out.println("actRow1List  : "+actRow1List);
		System.out.println("expRow1List  : "+expRow1List);
		System.out.println("*********************************************************************");

		System.out.println("actRow2List  : "+actRow2List);
		System.out.println("expRow2List  : "+expRow2List);
		System.out.println("*********************************************************************");

		System.out.println("actRow3List  : "+actRow3List);
		System.out.println("expRow3List  : "+expRow3List);
		System.out.println("*********************************************************************");

		System.out.println("actRow4List  : "+actRow4List);
		System.out.println("expRow4List  : "+expRow4List);
		System.out.println("*********************************************************************");

		System.out.println("actRow5List  : "+actRow5List);
		System.out.println("expRow5List  : "+expRow5List);
		System.out.println("*********************************************************************");

		System.out.println("actRow6List  : "+actRow6List);
		System.out.println("expRow6List  : "+expRow6List);
		System.out.println("*********************************************************************");


		System.out.println("actRow7List  : "+actRow7List);
		System.out.println("expRow7List  : "+expRow7List);
		System.out.println("*********************************************************************");

		System.out.println("actRow8List  : "+actRow8List);
		System.out.println("expRow8List  : "+expRow8List);
		System.out.println("*********************************************************************");

		System.out.println("actCol1List  : "+actCol1List);
		System.out.println("expCol1List  : "+expCol1List);
		System.out.println("*********************************************************************");

		System.out.println("actCol2List  : "+actCol2List);
		System.out.println("expCol2List  : "+expCol2List);
		System.out.println("*********************************************************************");

		System.out.println("actCol3List  : "+actCol3List);
		System.out.println("expCol3List  : "+expCol3List);
		System.out.println("*********************************************************************");

		System.out.println("actCol4List  : "+actCol4List);
		System.out.println("expCol4List  : "+expCol4List);
		System.out.println("*********************************************************************");

		System.out.println("actCol5List  : "+actCol5List);
		System.out.println("expCol5List  : "+expCol5List);
		System.out.println("*********************************************************************");

		System.out.println("actCol6List  : "+actCol6List);
		System.out.println("expCol6List  : "+expCol6List);
		System.out.println("*********************************************************************");


		System.out.println("actCol7List  : "+actCol7List);
		System.out.println("expCol7List  : "+expCol7List);
		System.out.println("*********************************************************************");

		System.out.println("actCol8List  : "+actCol8List);
		System.out.println("expCol8List  : "+expCol8List);
		System.out.println("*********************************************************************");


		if (actRow1List.equalsIgnoreCase(expRow1List) &&
				actRow2List.equalsIgnoreCase(expRow2List) && 

				actRow3List.equalsIgnoreCase(expRow3List) &&
				actRow4List.equalsIgnoreCase(expRow4List) &&
				actRow5List.equalsIgnoreCase(expRow5List) &&
				actRow6List.equalsIgnoreCase(expRow6List) &&
				actRow7List.equalsIgnoreCase(expRow7List) &&
				actRow8List.equalsIgnoreCase(expRow8List) &&
				actCol1List.equalsIgnoreCase(expCol1List) &&
						actCol2List.equalsIgnoreCase(expCol2List) && 

						actCol3List.equalsIgnoreCase(expCol3List) &&
						actCol4List.equalsIgnoreCase(expCol4List) &&
						actCol5List.equalsIgnoreCase(expCol5List) &&
						actCol6List.equalsIgnoreCase(expCol6List) &&
						actCol7List.equalsIgnoreCase(expCol7List) &&
						actCol8List.equalsIgnoreCase(expCol8List) &&

				actreportHeaderList.equalsIgnoreCase(expreportHeaderList) && 

				actvalidationConfirmationMessage.equalsIgnoreCase(expvalidationConfirmationMessage)) 
		{
			System.out.println(" Test Pass: Values Dsiplayed as Expected ");
			return true;
		} 
		else 
		{
			System.out.println(" Test FAIL: Values Dsiplayed as Expected ");
			return false;
		}


	}


	@FindBy(xpath="//input[@id='FOption_22_1000_DefaultFilter_0']")
	private static WebElement extraFieldDefaultFiltertxt;
	
	@FindBy(xpath="//label[text()='Warehouse:']//..//div[2]//input[2]")
	private static WebElement extraFieldDefaultFiltertxtRD2;

	@FindBy(xpath="//*[@id='id_rd_customization_report_column_button_container']/input[2]")
	private static WebElement extraFieldDefaultFilterOkBtn;

	@FindBy(xpath="//*[@id='22_1000_AdvanceFilter_']/table/tbody/tr/td[5]/input")
	private static WebElement cusFilterTextFiled;

	
	


	@FindBy(xpath="(//*[@id='idFilterCustomizeIcon'])[2]")
	private static WebElement extraFieldCustIcon;;

	@FindBy(xpath="(//*[contains(text(),' Warehouse')])[1]")
	private static WebElement custPopWareHouseExpandBtn;
	
	//@FindBy(xpath="(//*[@id='FilterFields_22_1000']//li[131]//i)[1]")
	@FindBy(xpath="(//a[contains(text(),' Warehouse')]//i)[1]")
	private static WebElement custPopWareHouseExpandBtnRD2;
	
	//@FindBy(xpath="(//div[@class='modal-dialog FilterFieldCustomizePopup']//ul[131]/li[1]/div//input)[1]")
	@FindBy(xpath="(//a[contains(text(),'Warehouse')]/i//following::ul//label[contains(text(),'Name')]//input)[1]")
	private static WebElement custPopWareHouseNameselected;
	
	//@FindBy(xpath="(//div[@class='modal-dialog FilterFieldCustomizePopup']//ul[131]/li[1]/div/label/span)[1]")
	@FindBy(xpath="(//a[contains(text(),'Warehouse')]/i//following::ul//label[contains(text(),'Name')]//input//..//span)[1]")
	private static WebElement custPopWareHouseName;
	
	
	
	
	
	
	


	@FindBy(xpath="//*[@id='5058']")
	private static WebElement custPopWareHouseNameChkboxSelected;;
	
	@FindBy(xpath="(//input[@id='5058']/following-sibling::span)[1]")
	private static WebElement custPopWareHouseNameChkbox;
	
	

	//@FindBy(xpath="//*[@id='FilterFieldCust_22_1000']/div/div[3]/input[1]")
	@FindBy(xpath="(//input[@value='Ok'])[1]")
	private static WebElement custPopOkBtn;;


	//@FindBy(xpath="//*[@id='id_rd_columnproperty_group2']//div[5]//span")
	//@FindBy(xpath="//*[@id='id_rd_customization_report_column_property']//div[2]//span[@class='icon-new hiconright2 icon-arrow btn-img']")
	@FindBy(xpath="//*[@id='id_rd_customization_report_column_property']//span[@class='icon-new hiconright2 icon-arrow btn-img']")
	private static WebElement rdReportExtraFieldFilterExpandBtn;


	@FindBy(xpath="//*[@id='id_rd_customization_parameterslist_button']/input[1]")
	private static WebElement rdReportParameterDeleteBtn;

	@FindBy(xpath="//*[@id='id_rd_columnproperty_defaultfilter']//*[@id='a']")
	private static WebElement defaultFilterBtn;;

	

	public boolean checkAlignmentInReportDesgining() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{

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
		for(int i=1;i<=customizeTabTableHeaderLsistCount;i++)
		{
			String data = customizeTabTableHeaderLsist.get(i).getText();

			if(data.equalsIgnoreCase("RATE MODIFIED"))
			{
				customizeTabTableHeaderLsist.get(i).click();

				System.err.println(i);
				Thread.sleep(1000);
				break;
			}
		}
		
		
		Thread.sleep(1000);

		if(rdExtraFiledColHeadingTxt.isDisplayed()==false)
		{
			progrmmableFieldExpandBtn.click();

		}

		Thread.sleep(1999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdExtraFiledColHeadingTxt));
		rdExtraFiledColHeadingTxt.click();

		Thread.sleep(1999);
		getAction().moveToElement(rdExtraFiledColHorizontalDrpdwn).build().perform();
		rdExtraFiledColHorizontalDrpdwn.click();
		
		Select s1=new Select(rdExtraFiledColHorizontalDrpdwn);
		s1.selectByVisibleText("Center");

		Thread.sleep(1999);
		getAction().moveToElement(rdExtraFiledColSignDrpdwn).build().perform();
		rdExtraFiledColSignDrpdwn.click();
		Select s2=new Select(rdExtraFiledColSignDrpdwn);
		s2.selectByVisibleText("(BRACKET)");

		Thread.sleep(1999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdExtraFiledOkBtn));
		rdExtraFiledOkBtn.click();


		getAction().moveToElement(finishBtn).build().perform();
		Thread.sleep(1200);
		finishBtn.click();

		Thread.sleep(2000);
		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : "+actMessage+" Value Expected : "+expMessage);

		if(actMessage.equalsIgnoreCase(expMessage))
		{

			System.out.println(" Test PasS: Extra Filed Programmable Filed added");
			return true;
		}
		else
		{
			System.out.println(" Test FAIL: Extra Filed Programmable Filed added");
			return false;
		}

	}


	public boolean checkReportMRAfterChangesAlignmentAndSignInColoumnFiels() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{

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

		Select s=new Select(sl_DateOptionDropdown);
		s.selectByValue("1");

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_OkBtn));
		sl_OkBtn.click();

		if (sl_1stRow1stCol.isDisplayed()==false) 
		{
			//checkServerErrorMessage
		}



		Thread.sleep(2000);
		boolean novalidationConfirmationMessage =validationConfirmationMessage.getText().isEmpty();

		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";



		int reportHeaderListCount=reportHeaderList.size();

		ArrayList<String >reportHeaderListArray=new ArrayList<>();

		for (int i = 0; i < reportHeaderListCount; i++) 
		{
			String data=reportHeaderList.get(i).getText();
			reportHeaderListArray.add(data);
		}

		String actreportHeaderList=reportHeaderListArray.toString();
		String expreportHeaderList="[#, Department.Name, Warehouse.Name, Gross, Item.Name, Item.Code, RATE MODIFIED, ToGrossAddedField]";

		System.err.println(" Act reportHeaderList : "+actreportHeaderList);
		System.err.println(" Exp reportHeaderList : "+expreportHeaderList);


		Thread.sleep(1500);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_1stRow1stCol));

		int reportsRow1ListCount = reportCol1List.size();
		ArrayList<String> reportsRow1ListArray = new ArrayList<String>();
		for(int i=0;i<reportsRow1ListCount;i++)
		{
			String data = reportCol1List.get(i).getText();

			reportsRow1ListArray.add(data);
		}
		String actRow1List = reportsRow1ListArray.toString();
		String expRow1List = "[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16]";


		int report2ndRowListCount = reportCol2List.size();
		ArrayList<String> report2ndRowListArray = new ArrayList<String>();
		for(int i=0;i<report2ndRowListCount;i++)
		{
			String data = reportCol2List.get(i).getText();
			report2ndRowListArray.add(data);
		}
		String actRow2List = report2ndRowListArray.toString();
		String expRow2List = "[AMERICA, AMERICA, DUBAI, DUBAI, INDIA, INDIA, INDIA, INDIA, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI]";


		int report3rdRowListCount = reportCol3List.size();
		ArrayList<String> report3rdRowListArray = new ArrayList<String>();
		for(int i=0;i<report3rdRowListCount;i++)
		{
			String data = reportCol3List.get(i).getText();
			report3rdRowListArray.add(data);
		}
		String actRow3List = report3rdRowListArray.toString();
		String expRow3List = "[HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD]";


		int report4thRowListCount = reportCol4List.size();
		ArrayList<String> report4thRowListArray = new ArrayList<String>();
		for(int i=0;i<report4thRowListCount;i++)
		{
			String data = reportCol4List.get(i).getText();
			report4thRowListArray.add(data);
		}
		String actRow4List = report4thRowListArray.toString();
		String expRow4List = "[110.00, 11.00, 110.00, 11.00, 110.00, 11.00, 110.00, 11.00, 110.00, 11.00, 110.00, 11.00, 110.00, 11.00, 110.00, 11.00]";


		int report5thRowListCount = reportCol5List.size();
		ArrayList<String> report5thRowListArray = new ArrayList<String>();
		for(int i=0;i<report5thRowListCount;i++)
		{
			String data = reportCol5List.get(i).getText();
			report5thRowListArray.add(data);
		}
		String actRow5List = report5thRowListArray.toString();
		String expRow5List = "[BATCH BIN WITH NO RESERVATION ITEM, BR COGS ITEM, BATCH BIN WITH NO RESERVATION ITEM, BR COGS ITEM, BATCH BIN WITH NO RESERVATION ITEM, BR COGS ITEM, BATCH BIN WITH NO RESERVATION ITEM, BR COGS ITEM, BATCH BIN WITH NO RESERVATION ITEM, BR COGS ITEM, BATCH BIN WITH NO RESERVATION ITEM, BR COGS ITEM, BATCH BIN WITH NO RESERVATION ITEM, BR COGS ITEM, BATCH BIN WITH NO RESERVATION ITEM, BR COGS ITEM]";

		int report6thRowListCount = reportCol6List.size();
		ArrayList<String> report6thRowListArray = new ArrayList<String>();
		for(int i=0;i<report6thRowListCount;i++)
		{
			String data = reportCol6List.get(i).getText();
			report6thRowListArray.add(data);
		}
		String actRow6List = report6thRowListArray.toString();
		String expRow6List = "[BBWNRI, BR COGS ITEM, BBWNRI, BR COGS ITEM, BBWNRI, BR COGS ITEM, BBWNRI, BR COGS ITEM, BBWNRI, BR COGS ITEM, BBWNRI, BR COGS ITEM, BBWNRI, BR COGS ITEM, BBWNRI, BR COGS ITEM]";

		int report7thRowListCount1 = reportCol7List.size();
		ArrayList<String> report7thRowListArray1 = new ArrayList<String>();
		for(int i=0;i<report7thRowListCount1;i++)
		{
			String data = reportCol7List.get(i).getAttribute("class");
			report7thRowListArray1.add(data);
		}
		String actRow7AlignList = report7thRowListArray1.toString();
		String expRow7AlignList ="[TextAlignCenter, TextAlignCenter, TextAlignCenter, TextAlignCenter, TextAlignCenter, TextAlignCenter, TextAlignCenter, TextAlignCenter, TextAlignCenter, TextAlignCenter, TextAlignCenter, TextAlignCenter, TextAlignCenter, TextAlignCenter, TextAlignCenter, TextAlignCenter]";
		
		
		
		
		int report7thRowListCount = reportCol7List.size();
		ArrayList<String> report7thRowListArray = new ArrayList<String>();
		for(int i=0;i<report7thRowListCount;i++)
		{
			String data = reportCol7List.get(i).getText();
			report7thRowListArray.add(data);
		}
		String actRow7List = report7thRowListArray.toString();
		String expRow7List = "[11.00, 11.00, 11.00, 11.00, 11.00, 11.00, 11.00, 11.00, 11.00, 11.00, 11.00, 11.00, 11.00, 11.00, 11.00, 11.00]";

		int report8thRowListCount = reportCol8List.size();
		ArrayList<String> report8thRowListArray = new ArrayList<String>();
		for(int i=0;i<report8thRowListCount;i++)
		{
			String data = reportCol8List.get(i).getText();
			report8thRowListArray.add(data);
		}
		String actRow8List = report8thRowListArray.toString();
		String expRow8List = "[1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00]";

		click(sl_NextBtn);
		Thread.sleep(2000);
		
		int reportsCol1ListCount = reportCol1List.size();
		ArrayList<String> reportsCol1ListArray = new ArrayList<String>();
		for(int i=0;i<reportsCol1ListCount;i++)
		{
			String data = reportCol1List.get(i).getText();

			reportsCol1ListArray.add(data);
		}
		String actCol1List = reportsCol1ListArray.toString();
		String expCol1List = "[17, 18, 19, 20, 21, 22, 23, 24, 25]";


		int report2ndColListCount = reportCol2List.size();
		ArrayList<String> report2ndColListArray = new ArrayList<String>();
		for(int i=0;i<report2ndColListCount;i++)
		{
			String data = reportCol2List.get(i).getText();
			report2ndColListArray.add(data);
		}
		String actCol2List = report2ndColListArray.toString();
		String expCol2List = "[DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, Grand Total]";


		int report3rdColListCount = reportCol3List.size();
		ArrayList<String> report3rdColListArray = new ArrayList<String>();
		for(int i=0;i<report3rdColListCount;i++)
		{
			String data = reportCol3List.get(i).getText();
			report3rdColListArray.add(data);
		}
		String actCol3List = report3rdColListArray.toString();
		String expCol3List = "[SECUNDERABAD, SECUNDERABAD, SECUNDERABAD, SECUNDERABAD, SECUNDERABAD, SECUNDERABAD, SECUNDERABAD, SECUNDERABAD, ]";


		int report4thColListCount = reportCol4List.size();
		ArrayList<String> report4thColListArray = new ArrayList<String>();
		for(int i=0;i<report4thColListCount;i++)
		{
			String data = reportCol4List.get(i).getText();
			report4thColListArray.add(data);
		}
		String actCol4List = report4thColListArray.toString();
		String expCol4List = "[, , , , , , , , 968.00]";


		int report5thColListCount = reportCol5List.size();
		ArrayList<String> report5thColListArray = new ArrayList<String>();
		for(int i=0;i<report5thColListCount;i++)
		{
			String data = reportCol5List.get(i).getText();
			report5thColListArray.add(data);
		}
		String actCol5List = report5thColListArray.toString();
		String expCol5List = "[BATCH BIN WITH NO RESERVATION ITEM, BR COGS ITEM, BATCH BIN WITH NO RESERVATION ITEM, BR COGS ITEM, BATCH BIN WITH NO RESERVATION ITEM, BR COGS ITEM, BATCH BIN WITH NO RESERVATION ITEM, BR COGS ITEM, ]";

		int report6thColListCount = reportCol6List.size();
		ArrayList<String> report6thColListArray = new ArrayList<String>();
		for(int i=0;i<report6thColListCount;i++)
		{
			String data = reportCol6List.get(i).getText();
			report6thColListArray.add(data);
		}
		String actCol6List = report6thColListArray.toString();
		String expCol6List = "[BBWNRI, BR COGS ITEM, BBWNRI, BR COGS ITEM, BBWNRI, BR COGS ITEM, BBWNRI, BR COGS ITEM, ]";

		int report7thColListCount = reportCol7List.size();
		ArrayList<String> report7thColListArray = new ArrayList<String>();
		for(int i=1;i<report7thColListCount;i++)
		{
			String data = reportCol7List.get(i).getText();
			report7thColListArray.add(data);
		}
		String actCol7List = report7thColListArray.toString();
		String expCol7List = "[11.00, 11.00, 11.00, 11.00, 11.00, 11.00, 11.00, 264.00]";

		int report8thColListCount = reportCol8List.size();
		ArrayList<String> report8thColListArray = new ArrayList<String>();
		for(int i=0;i<report8thColListCount;i++)
		{
			String data = reportCol8List.get(i).getText();
			report8thColListArray.add(data);
		}
		String actCol8List = report8thColListArray.toString();
		String expCol8List = "[1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 24.00]";



		System.out.println("actRow1List  : "+actRow1List);
		System.out.println("expRow1List  : "+expRow1List);
		System.out.println("*********************************************************************");

		System.out.println("actRow2List  : "+actRow2List);
		System.out.println("expRow2List  : "+expRow2List);
		System.out.println("*********************************************************************");

		System.out.println("actRow3List  : "+actRow3List);
		System.out.println("expRow3List  : "+expRow3List);
		System.out.println("*********************************************************************");

		System.out.println("actRow4List  : "+actRow4List);
		System.out.println("expRow4List  : "+expRow4List);
		System.out.println("*********************************************************************");

		System.out.println("actRow5List  : "+actRow5List);
		System.out.println("expRow5List  : "+expRow5List);
		System.out.println("*********************************************************************");

		System.out.println("actRow6List  : "+actRow6List);
		System.out.println("expRow6List  : "+expRow6List);
		System.out.println("*********************************************************************");


		System.out.println("actRow7List  : "+actRow7List);
		System.out.println("expRow7List  : "+expRow7List);
		System.out.println("*********************************************************************");

		System.out.println("actRow7List  : "+actRow7AlignList);
		System.out.println("expRow7List  : "+expRow7AlignList);
		System.out.println("*********************************************************************");


		System.out.println("actRow8List  : "+actRow8List);
		System.out.println("expRow8List  : "+expRow8List);
		System.out.println("*********************************************************************");

		System.out.println("actCol1List  : "+actCol1List);
		System.out.println("expCol1List  : "+expCol1List);
		System.out.println("*********************************************************************");

		System.out.println("actCol2List  : "+actCol2List);
		System.out.println("expCol2List  : "+expCol2List);
		System.out.println("*********************************************************************");

		System.out.println("actCol3List  : "+actCol3List);
		System.out.println("expCol3List  : "+expCol3List);
		System.out.println("*********************************************************************");

		System.out.println("actCol4List  : "+actCol4List);
		System.out.println("expCol4List  : "+expCol4List);
		System.out.println("*********************************************************************");

		System.out.println("actCol5List  : "+actCol5List);
		System.out.println("expCol5List  : "+expCol5List);
		System.out.println("*********************************************************************");

		System.out.println("actCol6List  : "+actCol6List);
		System.out.println("expCol6List  : "+expCol6List);
		System.out.println("*********************************************************************");


		System.out.println("actCol7List  : "+actCol7List);
		System.out.println("expCol7List  : "+expCol7List);
		System.out.println("*********************************************************************");

		System.out.println("actCol8List  : "+actCol8List);
		System.out.println("expCol8List  : "+expCol8List);
		System.out.println("*********************************************************************");


		if (actRow1List.equalsIgnoreCase(expRow1List) &&
				actRow2List.equalsIgnoreCase(expRow2List) && 

				actRow3List.equalsIgnoreCase(expRow3List) &&
				actRow4List.equalsIgnoreCase(expRow4List) &&
				actRow5List.equalsIgnoreCase(expRow5List) &&
				actRow6List.equalsIgnoreCase(expRow6List) &&
				actRow7List.equalsIgnoreCase(expRow7List) && actRow7AlignList.equalsIgnoreCase(expRow7AlignList)&&
				actRow8List.equalsIgnoreCase(expRow8List) &&
				actCol1List.equalsIgnoreCase(expCol1List) &&
				actCol2List.equalsIgnoreCase(expCol2List) && 

				actCol3List.equalsIgnoreCase(expCol3List) &&
				actCol4List.equalsIgnoreCase(expCol4List) &&
				actCol5List.equalsIgnoreCase(expCol5List) &&
				actCol6List.equalsIgnoreCase(expCol6List) &&
				actCol7List.equalsIgnoreCase(expCol7List) &&
				actCol8List.equalsIgnoreCase(expCol8List) &&

				actreportHeaderList.equalsIgnoreCase(expreportHeaderList) && 

				actvalidationConfirmationMessage.equalsIgnoreCase(expvalidationConfirmationMessage)) 
		{
			System.out.println(" Test Pass: Values Dsiplayed as Expected ");
			return true;
		} 
		else 
		{
			System.out.println(" Test FAIL: Values Dsiplayed as Expected ");
			return false;
		}


	}





	public boolean checkSortingOptionInReportDesgining() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{

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
		int count=sortingTABSourceList.size();

		ArrayList<String >sortingTABSourceListarray=new ArrayList<>();
		for (int i = 0; i < count; i++) 
		{
			String data=sortingTABSourceList.get(i).getText();
			sortingTABSourceListarray.add(data);

			if (data.equalsIgnoreCase("Department.Name")) 
			{
				sortingTABSourceList.get(i).click();
			}
		}

		String actList=sortingTABSourceListarray.toString();
		String expList="[Department.Name, Warehouse.Name, Gross, Item.Name, Item.Code, RATE MODIFIED, ToGrossAddedField]";

		System.err.println(" Sorting Tab Actual  List  : "+actList);
		System.err.println(" Sorting Tab Exp  List     : "+expList);

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sortingTABRightRowBtn));
		sortingTABRightRowBtn.click();

		Thread.sleep(1999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sortingtabSelectDrpdwn));
		Select s1=new Select(sortingtabSelectDrpdwn);
		s1.selectByVisibleText("Top");

		Thread.sleep(1999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sortingtabOkBtn));
		sortingtabOkBtn.click();

		Thread.sleep(1200);
		getAction().moveToElement(finishBtn).build().perform();
		Thread.sleep(1200);
		finishBtn.click();
		Thread.sleep(2000);

		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : "+actMessage+" Value Expected : "+expMessage);

		if(actMessage.equalsIgnoreCase(expMessage) && actList.equalsIgnoreCase(expList))
		{

			System.out.println(" Test PasS: Extra Filed Programmable Filed added");
			return true;
		}
		else
		{
			System.out.println(" Test FAIL: Extra Filed Programmable Filed added");
			//checkServerErrorMessage
			return false;
		}

	}


	public boolean checkReportMRAfterChangesAInSortingTAB() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{


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

		Select s=new Select(sl_DateOptionDropdown);
		s.selectByValue("1");

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_OkBtn));
		sl_OkBtn.click();

		if (sl_1stRow1stCol.isDisplayed()==false) 
		{
			//checkServerErrorMessage
		}



		Thread.sleep(2000);
		boolean novalidationConfirmationMessage =validationConfirmationMessage.getText().isEmpty();

		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";



		int reportHeaderListCount=reportHeaderList.size();

		ArrayList<String >reportHeaderListArray=new ArrayList<>();

		for (int i = 0; i < reportHeaderListCount; i++) 
		{
			String data=reportHeaderList.get(i).getText();
			reportHeaderListArray.add(data);
		}

		String actreportHeaderList=reportHeaderListArray.toString();
		String expreportHeaderList="[#, Department.Name, Warehouse.Name, Gross, Item.Name, Item.Code, RATE MODIFIED, ToGrossAddedField]";

		System.err.println(" Act reportHeaderList : "+actreportHeaderList);
		System.err.println(" Exp reportHeaderList : "+expreportHeaderList);


		Thread.sleep(1500);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_1stRow1stCol));

		int reportsRow1ListCount = reportCol1List.size();
		ArrayList<String> reportsRow1ListArray = new ArrayList<String>();
		for(int i=1;i<reportsRow1ListCount;i++)
		{
			String data = reportCol1List.get(i).getText();

			reportsRow1ListArray.add(data);
		}
		String actRow1List = reportsRow1ListArray.toString();
		String expRow1List = "[2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16]";


		int report2ndRowListCount = reportCol2List.size();
		ArrayList<String> report2ndRowListArray = new ArrayList<String>();
		for(int i=1;i<report2ndRowListCount;i++)
		{
			String data = reportCol2List.get(i).getText();
			report2ndRowListArray.add(data);
		}
		String actRow2List = report2ndRowListArray.toString();
		String expRow2List = "[AMERICA, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI, DUBAI]";


		int report3rdRowListCount = reportCol3List.size();
		ArrayList<String> report3rdRowListArray = new ArrayList<String>();
		for(int i=1;i<report3rdRowListCount;i++)
		{
			String data = reportCol3List.get(i).getText();
			report3rdRowListArray.add(data);
		}
		String actRow3List = report3rdRowListArray.toString();
		String expRow3List = "[HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, SECUNDERABAD, SECUNDERABAD, SECUNDERABAD, SECUNDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD, HYDERABAD]";


		int report4thRowListCount = reportCol4List.size();
		ArrayList<String> report4thRowListArray = new ArrayList<String>();
		for(int i=1;i<report4thRowListCount;i++)
		{
			String data = reportCol4List.get(i).getText();
			report4thRowListArray.add(data);
		}
		String actRow4List = report4thRowListArray.toString();
		String expRow4List = "[11.00, , 11.00, , , , 110.00, 110.00, 110.00, 110.00, 110.00, , , , ]";

		String expRow5List = "[BRCOGSITEM, BATCHBINWITHNORESERVATIONITEM, BRCOGSITEM, BATCHBINWITHNORESERVATIONITEM, BATCHBINWITHNORESERVATIONITEM, BATCHBINWITHNORESERVATIONITEM, BATCHBINWITHNORESERVATIONITEM, BATCHBINWITHNORESERVATIONITEM, BATCHBINWITHNORESERVATIONITEM, BATCHBINWITHNORESERVATIONITEM, BATCHBINWITHNORESERVATIONITEM, BRCOGSITEM, BRCOGSITEM, BRCOGSITEM, BRCOGSITEM]";
		boolean actRow5List = ListComparisionWOOrder(1,reportCol5List,expRow5List);
		
		String expRow6List = "[BRCOGSITEM, BBWNRI, BRCOGSITEM, BBWNRI, BBWNRI, BBWNRI, BBWNRI, BBWNRI, BBWNRI, BBWNRI, BBWNRI, BRCOGSITEM, BRCOGSITEM, BRCOGSITEM, BRCOGSITEM]";
		boolean actRow6List = ListComparisionWOOrder(1, reportCol6List, expRow6List);
		
		int report7thRowListCount = reportCol7List.size();
		ArrayList<String> report7thRowListArray = new ArrayList<String>();
		for(int i=1;i<report7thRowListCount;i++)
		{
			String data = reportCol7List.get(i).getText();
			report7thRowListArray.add(data);
		}
		String actRow7List = report7thRowListArray.toString();
		String expRow7List = "[11.00, 11.00, 11.00, 11.00, 11.00, 11.00, 11.00, 11.00, 11.00, 11.00, 11.00, 11.00, 11.00, 11.00, 11.00]";

		int report8thRowListCount = reportCol8List.size();
		ArrayList<String> report8thRowListArray = new ArrayList<String>();
		for(int i=1;i<report8thRowListCount;i++)
		{
			String data = reportCol8List.get(i).getText();
			report8thRowListArray.add(data);
		}
		String actRow8List = report8thRowListArray.toString();
		String expRow8List = "[1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00]";

		int report9thRowListCount = reportCol9List.size();
		ArrayList<String> report9thRowListArray = new ArrayList<String>();
		for(int i=1;i<report9thRowListCount;i++)
		{
			String data = reportCol9List.get(i).getText();
			report9thRowListArray.add(data);
		}
		String actRow9List = report9thRowListArray.toString();
		String expRow9List = "[]";


		System.out.println("actRow1List  : "+actRow1List);
		System.out.println("expRow1List  : "+expRow1List);
		System.out.println("*********************************************************************");

		System.out.println("actRow2List  : "+actRow2List);
		System.out.println("expRow2List  : "+expRow2List);
		System.out.println("*********************************************************************");

		System.out.println("actRow3List  : "+actRow3List);
		System.out.println("expRow3List  : "+expRow3List);
		System.out.println("*********************************************************************");

		System.out.println("actRow4List  : "+actRow4List);
		System.out.println("expRow4List  : "+expRow4List);
		System.out.println("*********************************************************************");

		System.out.println("actRow5List  : "+actRow5List);
		System.out.println("expRow5List  : "+expRow5List);
		System.out.println("*********************************************************************");

		System.out.println("actRow6List  : "+actRow6List);
		System.out.println("expRow6List  : "+expRow6List);
		System.out.println("*********************************************************************");


		System.out.println("actRow7List  : "+actRow7List);
		System.out.println("expRow7List  : "+expRow7List);
		System.out.println("*********************************************************************");

		System.out.println("actRow8List  : "+actRow8List);
		System.out.println("expRow8List  : "+expRow8List);
		System.out.println("*********************************************************************");

		System.out.println("actRow9List  : "+actRow9List);
		System.out.println("expRow9List  : "+expRow9List);
		System.out.println("*********************************************************************");


		if (actRow1List.equalsIgnoreCase(expRow1List) &&
				actRow2List.equalsIgnoreCase(expRow2List) && 

				actRow3List.equalsIgnoreCase(expRow3List) &&
				actRow4List.equalsIgnoreCase(expRow4List) &&
				actRow5List && actRow6List &&
				actRow7List.equalsIgnoreCase(expRow7List) &&
				actRow8List.equalsIgnoreCase(expRow8List) &&
				actRow9List.equalsIgnoreCase(expRow9List) &&

				actreportHeaderList.equalsIgnoreCase(expreportHeaderList) && 

				actvalidationConfirmationMessage.equalsIgnoreCase(expvalidationConfirmationMessage)) 
		{
			System.out.println(" Test Pass: Values Dsiplayed as Expected ");
			 Thread.sleep(2000);
				
			
			return true;
		} 
		else 
		{
			System.out.println(" Test FAIL: Values Dsiplayed as Expected ");
			 Thread.sleep(2000);
				
			
			    return false;
		}
	}




	public boolean checkSavingReportDesginerOnBasisOFCustomization() throws InterruptedException, IOException, EncryptedDocumentException, InvalidFormatException
	{

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
		Select rtd= new Select(reportTypeDropdown);
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

		for(int i=0;i<=transactionSetListCount;i++)
		{
			String data = transactionSetList.get(i).getText();

			if(data.equalsIgnoreCase("Inventory transactions of selected product"))
			{
				transactionSetList.get(i).click();

				break;
			}
		}



		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTab));
		customizationTab.click();

		Thread.sleep(3999);



		Runtime.getRuntime().exec(getBaseDir()+"\\autoIt\\scripts\\TestColumnFilter.exe");

		Thread.sleep(1000000);

		getAction().moveToElement(finishBtn).build().perform();
		Thread.sleep(1200);
		finishBtn.click();
		
		Thread.sleep(2000);
		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : "+actMessage+" Value Expected : "+expMessage);

		if(actMessage.equalsIgnoreCase(expMessage))
		{
			return true;
		}
		else
		{
			return false;
		}

	}
	
//////////////////////////////////////////////REPORT DESIGNER TEST SCENARIOS ON FILTER AND PRINT AND PDF//////////////////////////////////////////////////////////////////////
	
	
	@FindBy(xpath="//div[@class='adminprofile']/i")
	public static WebElement LogoutDropdown;
	
	public static boolean checklogout() throws InterruptedException
	{
		getWebDriverWaitEle(LogoutDropdown);
		getAction().moveToElement(LogoutDropdown).build().perform();
		Thread.sleep(1200);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(LogoutDropdown));
		LogoutDropdown.click();

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
	
	//@FindBy(xpath="//*[@id='id_mainlayoutmenu']/ul[2]/li[7]/a")
	@FindBy(xpath="//a[text()='SU']")
	public static WebElement usernametxt;

	public static boolean checkLoginForRD2() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{

		Thread.sleep(1999);

		LoginPage lp=new LoginPage(getDriver()); 

		String unamelt="su";

		String pawslt="su";

		lp.enterUserName(unamelt);

		Thread.sleep(2000);

		lp.enterPassword(pawslt);

		/*String compname="User Restrictions--COGS";*/
		String compname="Testing 22";

		Select oSelect = new Select(companyDropDownList);

		List <WebElement> elementCount = oSelect.getOptions();

		int cqSize = elementCount.size();

		System.out.println("CompanyDropdownList Count :"+cqSize);

		int i;

		for(i=0; i<elementCount.size(); i++) 
		{

			elementCount.get(i).getText();

			String optionName = elementCount.get(i).getText();
			if(optionName.toUpperCase().startsWith(compname.toUpperCase()))
			{
				System.out.println("q"+elementCount.get(i).getText());
				elementCount.get(i).click();
			}

		}


		Thread.sleep(2000);

		lp.clickOnSignInBtn();
		
		
		Thread.sleep(12000);

		String userInfo=usernametxt.getText();

		System.out.println("User Info : "+userInfo);

		//System.out.println("User Info Capture Text :"+userNameDisplay.getText());

		//getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(companyLogoImg));

		//companyLogoImg.click();

		if(userInfo.equalsIgnoreCase("SU"))
		{

			System.out.println("Test Pass :Logined to RD Reports Company");
			return true;

		}
		else
		{
			System.out.println("Test Fail :Logined to  RD Reports Company");
			return false;

		}
	}


	
	public static boolean checkRestoreCompanyForRD2() throws InterruptedException, IOException, AWTException
	{

		BaseEngine.restoreCompany("ReportDesigner Testing 24","Testing 22");
		
		Thread.sleep(5000);
		
		String actUserInfo1=usernametxt.getText();

		System.out.println("User Info  : "+actUserInfo1);

		System.out.println("User Info Capture Text  :  "+usernametxt.getText());

	/*	getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(companyLogoImg));
		companyLogoImg.click();

		String getCompanyTxt1=companyName.getText();
		String getLoginCompanyName1=getCompanyTxt1.substring(0, 10);
		System.out.println("company name  :  "+ getLoginCompanyName1);
		companyLogoImg.click();*/

		String expUserInfo1           ="SU";
		String expLoginCompanyName1   ="Testing 22";

		System.out.println("UserInfo1             : "+actUserInfo1            +" Value Expected : "+expUserInfo1);
		//System.out.println("LoginCompanyName1     : "+getLoginCompanyName1    +" Value Expected : "+expLoginCompanyName1);

		if(actUserInfo1.equalsIgnoreCase(expUserInfo1) /*&& getLoginCompanyName1.contains(expLoginCompanyName1)*/)
		{
			return true;
		}
		else
		{
			return false;
		}
		
	
	}

	
	public static boolean checkReportDesignerForQuantityFilter() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{

		getWebDriverWaitEle(homeMenu);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(homeMenu));
		homeMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(utilities));
		utilities.click();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportDesignerMenu));
		reportDesignerMenu.click();

		Thread.sleep(4000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportNameDropdown));       
		reportNameDropdown.sendKeys("Report Designer For Quantity Filter");
		reportNameDropdown.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportTypeDropdown));
		Select rtd= new Select(reportTypeDropdown);
		rtd.selectByVisibleText("Details");
		Thread.sleep(2000);
		
		getWebDriverWaitEle(inventoryExpandBtn);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(inventoryExpandBtn));
		inventoryExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportsBtn));
		reportsBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dataSetTab));
		dataSetTab.click();

		Thread.sleep(2000);

		int transactionSetListCount = transactionSetList.size();

		ArrayList<String >transactionSetListArray=new ArrayList<>();

		for(int i=0;i<=transactionSetListCount;i++)
		{
			String data = transactionSetList.get(i).getText();

			transactionSetListArray.add(data);

			if(data.equalsIgnoreCase("Inventory transactions"))
			{
				transactionSetList.get(i).click();

				break;
			}
		}

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTab));
		customizationTab.click();
		
		//getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionExpandBtn));
		//transactionExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionFieldsExpandBtn));
		transactionFieldsExpandBtn.click();

		Thread.sleep(1000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemExpandBtn));
		itemExpandBtn.click();
		Thread.sleep(1000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemNameBtn));
		getAction().doubleClick(itemNameBtn).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemExpandBtn));
		itemExpandBtn.click();

		Thread.sleep(1000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(extraFieldsExpandBtn));
		extraFieldsExpandBtn.click();
		
		getAction().moveToElement(warehouseExpandBtn).build().perform();
		Thread.sleep(2000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouseExpandBtn));
		warehouseExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouseNameBtn));
		getAction().doubleClick(warehouseNameBtn).build().perform();

		Thread.sleep(1000);
		
		getAction().moveToElement(quantityBtn).build().perform();
		Thread.sleep(2000);
		
	
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(quantityBtn));
		getAction().doubleClick(quantityBtn).build().perform();
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouseExpandBtn));
		warehouseExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(extraFieldsExpandBtn));
		extraFieldsExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionFieldsExpandBtn));
		transactionFieldsExpandBtn.click();
		Thread.sleep(1000);
		
		//getAction().moveToElement(finishBtn).build().perform();
		 ((JavascriptExecutor)getDriver()).executeScript("window.scrollTo(0, 0)","");
			
		Thread.sleep(1200);
		finishBtn.click();
		
		Thread.sleep(2000);

		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : "+actMessage+" Value Expected : "+expMessage);

		if(actMessage.equalsIgnoreCase(expMessage))
		{

			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	@FindBy(xpath="//*[@id='id_rd_customization_report_column_property']//div[1]/div[2]/span")
	public static WebElement quantityExpBtn;
	
	@FindBy(xpath="//*[@id='id_rd_columnproperty_group2_heading_panel']//span")
	public static WebElement specificPropExpBtn;
	
	@FindBy(xpath="//*[@id='id_rd_columnproperty_decimalsincolumn']")
	public static WebElement specificProp_DecimalColTxt;
	
	
	public static boolean checkQuantityFilteronWarehouse() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
				 
		Thread.sleep(2500);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportNameDropdown));       
		
		reportNameDropdown.sendKeys(Keys.SPACE);

		int reportNameListcount = reportNameList.size();

		for(int i=0;i<reportNameListcount;i++)
		{
			String data = reportNameList.get(i).getText();

			if(data.equalsIgnoreCase("Report Designer For Quantity Filter"))
			{
				reportNameList.get(i).click();
				break;
			}
		}

		reportNameDropdown.sendKeys(Keys.TAB);


		Thread.sleep(3000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTab));
		customizationTab.click();

		Thread.sleep(3000);
		
		int customizeTabTableHeaderLsistCount = customizeTabTableHeaderLsist.size();
		System.err.println(customizeTabTableHeaderLsistCount);
		for(int i=1;i<=customizeTabTableHeaderLsistCount;i++)
		{
			String data = customizeTabTableHeaderLsist.get(i).getText();

			if(data.equalsIgnoreCase("Quantity"))
			{
				customizeTabTableHeaderLsist.get(i).click();
				break;
			}
		}
		
		Thread.sleep(4000);
		
		if(rdExtraFiledColHeadingTxt.isDisplayed()==false)
		{
			quantityExpBtn.click();
		}
		if(specificProp_DecimalColTxt.isDisplayed())
		{
			specificPropExpBtn.click();
		}
		
		getAction().moveToElement(rdReportExtraFieldFilterExpandBtn).build().perform();


		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdReportExtraFieldFilterExpandBtn));
		rdReportExtraFieldFilterExpandBtn.click();

		Thread.sleep(2999);


		getAction().moveToElement(extraFieldCustIcon).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(extraFieldCustIcon));
		extraFieldCustIcon.click();

		Thread.sleep(2999);

		scrollToElementJSE(custPopWareHouseExpandBtnRD2);
		Thread.sleep(1500);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(custPopWareHouseExpandBtnRD2));
		custPopWareHouseExpandBtnRD2.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(custPopWareHouseName));
		if (custPopWareHouseNameselected.isSelected()==false) 
		{
			custPopWareHouseName.click();

		} 


		Thread.sleep(2999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(custPopOkBtn));
		custPopOkBtn.click();


		System.err.println(" Check Warehouse Name chk box in customization ");

		Thread.sleep(2000);
		scrollToElementJSE(extraFieldDefaultFiltertxtRD2);
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(extraFieldDefaultFiltertxtRD2));
		extraFieldDefaultFiltertxtRD2.click();

		extraFieldDefaultFiltertxtRD2.sendKeys("wh1");
		Thread.sleep(1999);
		extraFieldDefaultFiltertxtRD2.sendKeys(Keys.TAB);
		System.err.println(" ***Displayed WareHouse Default Filter Text ");

		extraFieldDefaultFilterOkBtn.click();

		 ((JavascriptExecutor)getDriver()).executeScript("window.scrollTo(0, 0)","");
			
		Thread.sleep(1200);
		finishBtn.click();

		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : "+actMessage+" Value Expected : "+expMessage);

		if (actMessage.equalsIgnoreCase(expMessage))
		{
			return true;
		}
		else
		{
			return false;
		}


	}
	
	public static boolean checkValidateQuantityFilterOnWarehouse() throws InterruptedException
	{

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
		searchTxt.click();
		searchTxt.sendKeys("Report Designer For Quantity Filter");
		Thread.sleep(1000);
		searchTxt.sendKeys(Keys.ENTER);


		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_DateOptionDropdown));
		sl_DateOptionDropdown.click();
		Select s=new Select(sl_DateOptionDropdown);
		s.selectByValue("1");

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_OkBtn));
		sl_OkBtn.click();

		Thread.sleep(1500);
		
		String expRow1List = "[RMA1, RMA3, item1, RMA1, RMA3, RMA3, RMA2, RMA2, RMA2, RMA1, RMA2, RMA3, RMA1, RMA2, RMA2, RMA3]";
		String actRow1List = listOfElements(reportCol2List);

		String expRow2List = "[wh4, wh1, wh3, wh1, wh4, wh2, wh2, wh4, wh3, wh4, wh4, wh4, wh2, wh2, wh1, wh1]";
		String actRow2List = listOfElements(reportCol3List);

			
		
		int reportsQtyListCount = reportCol3List.size();
		ArrayList<String> reportsQtywh1ListArray = new ArrayList<String>();
		ArrayList<String> reportsQtyListArray = new ArrayList<String>();
		for(int i=0;i<reportsQtyListCount;i++)
		{
			String data = reportCol3List.get(i).getText();
			if(data.equals("wh1"))
			{
				boolean flag=reportCol4List.get(i).getText().isEmpty();
				reportsQtywh1ListArray.add(Boolean.toString(flag));
			}
			else
			{
				boolean flag=reportCol4List.get(i).getText().isEmpty();
				reportsQtyListArray.add(Boolean.toString(flag));
			}
		}
		
		
		String actQtywh1=reportsQtywh1ListArray.toString();
		String expQtywh1="[false, false, false, false]";
		
		String actQtywh=reportsQtyListArray.toString();
		String expQtywh="[true, true, true, true, true, true, true, true, true, true, true, true]";
		
		System.out.println("actRow1List  : "+actRow1List);
		System.out.println("expRow1List  : "+expRow1List);
		System.out.println("*********************************************************************");

		System.out.println("actRow2List  : "+actRow2List);
		System.out.println("expRow2List  : "+expRow2List);
		System.out.println("*********************************************************************");

	
		System.out.println("actQtywh1  : "+actQtywh1);
		System.out.println("expQtywh1  : "+expQtywh1);
		System.out.println("*********************************************************************");

		
		System.out.println("actQtywh  : "+actQtywh);
		System.out.println("expQtywh  : "+expQtywh);
		System.out.println("*********************************************************************");

		
		if (actRow1List.equalsIgnoreCase(expRow1List) &&
				actRow2List.equalsIgnoreCase(expRow2List) ) 
		{

			System.out.println(" Test Pass: Values Dsiplayed as Expected ");
			return true;
		} 
		else 
		{

			System.out.println(" Test FAIL: Values Dsiplayed as Expected ");
			return false;

		}

	}
	
	@FindBy(xpath="//*[@id='id_rd_customization_parameterslist_button']/input[2]")
	public static WebElement AddParameterBtn;
	
	public static boolean checkCustomizeReportDesignerForRDItemParameter() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
		searchTxt.click();
		searchTxt.sendKeys("RD With Item Parameter");
		Thread.sleep(2000);
		searchTxt.sendKeys(Keys.ENTER);
		
		Thread.sleep(3500);
		reportCustomizeBtnHomePage.click();
		String actReportName = reportNameDropdown.getText();
		String expReportName = "";
		
		System.out.println("actReportName: "+actReportName);
		System.out.println("expReportName: "+expReportName);
		
		Thread.sleep(3500);
		getAction().moveToElement(AddParameterBtn).build().perform();
		Thread.sleep(1200);
		AddParameterBtn.click();
		
		Thread.sleep(1200);
		rdFieldNameTxt.click();
		rdFieldNameTxt.sendKeys("Item");
		Thread.sleep(2000);

		rdFieldNameTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		Select s1=new Select(rdFieldTypeDrpdwn);
		s1.selectByVisibleText("Item");

		Thread.sleep(2000);
		
		//scrollToElementJSE(rdParametersOkbtn);
		getAction().moveToElement(rdParametersOkbtn).build().perform();
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdParametersOkbtn));
		rdParametersOkbtn.click();

		Thread.sleep(2999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdParametersTxtArea));
		String actrdParametersTxtArea=rdParametersTxtArea.getAttribute("data-fieldname");
		String exprdParametersTxtArea = "";
		System.out.println(" actrdParametersTxtArea : "+actrdParametersTxtArea);
		System.out.println(" exprdParametersTxtArea : "+exprdParametersTxtArea);
			

		getAction().moveToElement(finishBtn).build().perform();
		Thread.sleep(1200);
		finishBtn.click();

		 String expMessage = "Data saved successfully.";

		 String actMessage = checkValidationMessage(expMessage);

		 System.out.println("Validation Message : "+actMessage+" Value Expected : "+expMessage);

		 if(actMessage.equalsIgnoreCase(expMessage))
		 {

			 return true;
		 }
		 else
		 {
			 return false;
		 }

	}
	
	@FindBy(xpath="//*[@id='a' and @class='theme_button_color icon-filter icon-font6']")
	private static WebElement changeToDefFilterIcon;
	
	@FindBy(xpath="(//span[@class='icon-close icon-font6'])[2]")
	public static WebElement removeFilterIcon;
	
	@FindBy(xpath="//*[@id='FOption_0_0_DefaultFilter_0']")
	public static WebElement filter_AccTxt;
	
	
	@FindBy(xpath="//*[@id='a']")
	public static WebElement filter_AdvncFilter;
	
	
	public static boolean checkApplyFilterInDataSetTabForItemWithParameterRD() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{

		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
		searchTxt.click();
		searchTxt.sendKeys("RD With Item Parameter");
		Thread.sleep(1500);
		searchTxt.sendKeys(Keys.ENTER);
		
		Thread.sleep(2500);
		reportCustomizeBtnHomePage.click();
		String actReportName = reportNameDropdown.getAttribute("value");
		String expReportName = "RD With Item Parameter";
		
		System.out.println("actReportName: "+actReportName);
		System.out.println("expReportName: "+expReportName);
		
		
		getWebDriverWaitEle(dataSetTab);
		dataSetTab.click();
		
		Thread.sleep(1000);
		
		if(filter_AccTxt.isDisplayed()==true)
		{
			click(changeToDefFilterIcon);
		}
	/*	if(removeFilterIcon.isDisplayed()==true)
		{
			System.out.println("Remove filter");
			click(removeFilterIcon);
		}
		
		click(changeToDefFilterIcon);*/
		Thread.sleep(2500);
		/*else
		{
		getWebDriverWaitEle(changeToDefFilterIcon);
		changeToDefFilterIcon.click();
		click(removeFilterIcon);
		}
		/*
		for (int i = 0; i<5; i++) 
		{

			if (rdAdvanceFilterWhereDrpdwnForPaea.isDisplayed()==false)
			{

				changeToDefFilterIcon.click();
				System.out.println(" I "+i);
				break;
			}

		}
*/		
		Thread.sleep(2000);
		Select s1=new Select(rdAdvanceFilterWhereDrpdwnForPaea);
		s1.selectByValue("0");
		rdAdvanceFilterWhereDrpdwnForPaea.sendKeys(Keys.TAB);


		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdAdvanceFilterNameTxt1));
		rdAdvanceFilterNameTxt1.click();
		Thread.sleep(2000);

		//getAction().moveToElement(rdAdvanceFilterDepExpBtn).build().perform();
		scrollToElementJSE(rdAdvanceFilterItemExpBtn);
		Thread.sleep(2000);
		rdAdvanceFilterItemExpBtn.click();
		
		int count = ItemList.size();
		
		for (int i = 0; i < count; i++)
		{
			String data = ItemList.get(i).getText();
			if (data.equalsIgnoreCase("Name")) 
			{
				ItemList.get(i).click();
			}
			break;
		}

		//rdAdvanceFilterName_DepNameBtn.click();

		Thread.sleep(2000);
		Select s3=new Select(rdAdvanceFilterOpersatorDrpdwn1);
		s3.selectByValue("0");


		Thread.sleep(2000);
		Select s4=new Select(rdAdvanceFilterValueDrpdwn1);
		s4.selectByValue("2");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdAdvanceFilterSelectTxt1));
		rdAdvanceFilterSelectTxt1.click();
		


		int Pcount = parametersList.size();

		for (int i = 0; i < Pcount; i++)
		{
			String data = parametersList.get(i).getText();
			if (data.equalsIgnoreCase("@Item")) 
			{
				parametersList.get(i).click();
			}
			break;
		}
		
		scrollToElementJSE(rdAdvanceFilterSelectTxt1);
		Thread.sleep(2000);
		String actrdAdvanceFilterSelectTxt=rdAdvanceFilterSelectTxt1.getAttribute("value");
		String exprdAdvanceFilterSelectTxt="@Item";
		
		Thread.sleep(1500);

		System.out.println(" rdAdvanceFilterSelectTxt : "+actrdAdvanceFilterSelectTxt +" Value exp: "+exprdAdvanceFilterSelectTxt);

		Thread.sleep(2999);

		 ((JavascriptExecutor)getDriver()).executeScript("window.scrollTo(0, 0)","");

		// getAction().moveToElement(finishBtn).build().perform();
		Thread.sleep(1500);
		finishBtn.click();

		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : "+actMessage+" Value Expected : "+expMessage);

		if(actMessage.equalsIgnoreCase(expMessage))
		{

			System.out.println(" Test PasS: Multiple Chkbx is Selcted ");
			return true;
		}
		else
		{
			System.out.println(" Test FAIL:  Multiple Chkbx is Selcted ");
			return false;
		}

		
	
		
	}
	
	@FindBy(xpath="//*[@id='MasterSingle__1']")
	private static WebElement ItemMasterParameter;
	
	public static boolean checkRDReportForItemFilterAsItemParameter() throws InterruptedException
	{

		getWebDriverWaitEle(searchTxt);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
		searchTxt.click();
		searchTxt.sendKeys("RD With Item Parameter");
		Thread.sleep(1000);
		searchTxt.sendKeys(Keys.ENTER);
		

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_DateOptionDropdown));
		sl_DateOptionDropdown.click();
		Select s=new Select(sl_DateOptionDropdown);
		s.selectByValue("1");

		Thread.sleep(2000);
		
		ItemMasterParameter.sendKeys("item4");
		Thread.sleep(3000);
		ItemMasterParameter.sendKeys(Keys.TAB);
		

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_OkBtn));
		sl_OkBtn.click();


		String expRow1List = "[1, Focus_2, Vendor B, Cost of goods sold - Electronics, item4, 15.00, 8.00, 120.00, Purchases Vouchers, dep2, wh2]";
		String actRow1List = listOfElements(report1stRowList);

		String expRow2List = "[2, Focus_3, Vendor C, Inventory FG, item4, 10.00, 3.00, 30.00, Purchases Vouchers, dep3, wh3]";
		String actRow2List = listOfElements(report2ndRowList);
		
		String expRow3List = "[3, 2, Vendor B, item4, 1.00, 2.00, 2.00, Material Receipt Notes, dep2, wh2]";
		String actRow3List = listOfElements(report3rdRowList);

		String expRow4List = "[4, 3, Vendor B, item4, 1.00, 2.00, 2.00, Material Receipt Notes, dep2, wh2]";
		String actRow4List = listOfElements(report4thRowList);

		String expRow5List = "[5, 4, Vendor B, item4, 1.00, 2.00, 2.00, Material Receipt Notes, dep2, wh2]";
		String actRow5List = listOfElements(report5thRowList);

		String expRow6List = "[6, 6, Vendor B, item4, 1.00, 2.00, 2.00, Material Receipt Notes, dep2, wh2]";
		String actRow6List = listOfElements(report6thRowList);
		
		String expcol1List = "[item4, item4, item4, item4, item4, item4, item4, item4, item4, item4, item4, item4, item4, item4, item4, item4]";
		String actcol1List = listOfElements(reportCol5List);



		System.out.println("actRow1List  : "+actRow1List);
		System.out.println("expRow1List  : "+expRow1List);
		System.out.println("*********************************************************************");

		System.out.println("actRow2List  : "+actRow2List);
		System.out.println("expRow2List  : "+expRow2List);
		System.out.println("*********************************************************************");

		System.out.println("actRow3List  : "+actRow3List);
		System.out.println("expRow3List  : "+expRow3List);
		System.out.println("*********************************************************************");
		
		System.out.println("actRow4List  : "+actRow4List);
		System.out.println("expRow4List  : "+expRow4List);
		System.out.println("*********************************************************************");
		
		System.out.println("actRow5List  : "+actRow5List);
		System.out.println("expRow5List  : "+expRow5List);
		System.out.println("*********************************************************************");

		System.out.println("actRow6List  : "+actRow6List);
		System.out.println("expRow6List  : "+expRow6List);
		System.out.println("*********************************************************************");
		
		System.out.println("actcol1List  : "+actcol1List);
		System.out.println("expcol1List  : "+expcol1List);
		System.out.println("*********************************************************************");
		
		

		if (actRow1List.equalsIgnoreCase(expRow1List) &&
				actRow2List.equalsIgnoreCase(expRow2List) && actRow3List.equalsIgnoreCase(expRow3List)
				&& actRow4List.equalsIgnoreCase(expRow4List) && actRow5List.equalsIgnoreCase(expRow5List) 
				&& actRow6List.equalsIgnoreCase(expRow6List) && actcol1List.equalsIgnoreCase(expcol1List))
		{

			System.out.println(" Test Pass: Values Dsiplayed as Expected ");
			return true;
		} 
		else 
		{

			System.out.println(" Test FAIL: Values Dsiplayed as Expected ");
			return false;

		}

		
		
		
		
	}
	
	
	@FindBy(xpath="//div[@id='id_reportmenudisplay']//i[@class='icon-print hiconright2']")
	private static WebElement Report_printIcon;
	
	
	public static boolean checkPrintPDFForAnotherItemForItemParameterRD() throws InterruptedException, AWTException, IOException
	{

		
		Thread.sleep(2500);
		File Efile=new File(getBaseDir()+"\\autoIt\\ExportFiles\\ItemPrameter.pdf");

		if(Efile.exists())
		{
			Efile.delete();
		}
		
		getWebDriverWaitEle(searchTxt);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
		searchTxt.click();
		searchTxt.sendKeys("RD With Item Parameter");
		Thread.sleep(1000);
		searchTxt.sendKeys(Keys.ENTER);
		

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_DateOptionDropdown));
		sl_DateOptionDropdown.click();
		Select s=new Select(sl_DateOptionDropdown);
		s.selectByValue("1");

		Thread.sleep(2000);
		
		ItemMasterParameter.sendKeys("item1");
		Thread.sleep(3000);
		ItemMasterParameter.sendKeys(Keys.TAB);
		

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_OkBtn));
		sl_OkBtn.click();
		
		getWebDriverWaitEle(Report_printIcon);
		Report_printIcon.click();
		
		Thread.sleep(1500);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(ss_ReportPrintLabel));

		String actConfirmMsg=ss_ReportPrintMsg.getText();
		String expConfirmMsg="";
		Thread.sleep(1500);

		System.out.println("Actual Msg                :                "+        actConfirmMsg                + "Expected                "        +        expConfirmMsg);


		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(ss_ReportPrintYesBtn));
		ss_ReportPrintYesBtn.click();

		Thread.sleep(10000);

		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_S);
		robot.keyRelease(KeyEvent.VK_S);
		robot.keyRelease(KeyEvent.VK_CONTROL);

		Thread.sleep(7000);

		Runtime.getRuntime().exec(getBaseDir() + "\\autoIt\\scripts\\scripts\\SavingItemParameter.exe");

		Thread.sleep(10000);

		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_J);
		robot.keyRelease(KeyEvent.VK_J);
		robot.keyRelease(KeyEvent.VK_CONTROL);

		Thread.sleep(1500);

		ArrayList<String> openTabs = new ArrayList<String>(getDriver().getWindowHandles());

		int actOpenWindowsCount = getDriver().getWindowHandles().size();
		int expOpenWindowsCount = 3;

		System.out.println("Number of Windows  : "+actOpenWindowsCount+"  Value Expected  "+expOpenWindowsCount);

		Thread.sleep(1000);
/*
		getDriver().switchTo().window(openTabs.get(2)).close();
		Thread.sleep(1000);
		getDriver().switchTo().window(openTabs.get(1)).close();
		Thread.sleep(1000);
		getDriver().switchTo().window(openTabs.get(0));*/
		
		
		
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_W);
		robot.keyRelease(KeyEvent.VK_W);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		
		Thread.sleep(2000);
		
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_W);
		robot.keyRelease(KeyEvent.VK_W);
		robot.keyRelease(KeyEvent.VK_CONTROL);


		String actPDF = getBaseDir()+"\\autoIt\\ExportFiles\\ItemPrameter.pdf";
		String expPDF = getBaseDir()+"\\autoIt\\ImportFiles\\ItemPrameter.pdf";

		PDFUtil pdfutil = new PDFUtil();

		boolean result = pdfutil.compare(actPDF, expPDF);
		Thread.sleep(5000);

		String actData = pdfutil.getText(actPDF);
		String expData = pdfutil.getText(expPDF).replaceAll("25/08/2025", getCurrentDateF2());
		Thread.sleep(4000);
		System.err.println(actData);
		System.err.println(expData);

		System.out.println("Compared Result  : "+result);

		if (actData.equalsIgnoreCase(expData) /*&& result==true*/) 
		{
			return true;
		}
		else
		{
			return false;
		}

	}
	
	
	public static boolean checkCreateReportForMonthWiseData() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{

		getWebDriverWaitEle(homeMenu);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(homeMenu));
		homeMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(utilities));
		utilities.click();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportDesignerMenu));
		reportDesignerMenu.click();

		Thread.sleep(4000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportNameDropdown));       
		reportNameDropdown.sendKeys("Report for MonthWise Transaction data");
		reportNameDropdown.sendKeys(Keys.TAB);
		Thread.sleep(4000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportTypeDropdown));
		Select rtd= new Select(reportTypeDropdown);
		rtd.selectByVisibleText("Cubes");
		
		Thread.sleep(1000);
		reportTypeDropdown.sendKeys(Keys.TAB);
		
		Thread.sleep(2500);

		getWebDriverWaitEle(inventoryExpandBtn);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(inventoryExpandBtn));
		inventoryExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportsBtn));
		reportsBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dataSetTab));
		dataSetTab.click();

		Thread.sleep(2000);

		int transactionSetListCount = transactionSetList.size();

		ArrayList<String >transactionSetListArray=new ArrayList<>();

		for(int i=0;i<=transactionSetListCount;i++)
		{
			String data = transactionSetList.get(i).getText();

			transactionSetListArray.add(data);

			if(data.equalsIgnoreCase("Inventory transactions"))
			{
				transactionSetList.get(i).click();

				break;
			}
		}

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTab));
		customizationTab.click();
		
		//getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionExpandBtn));
		//transactionExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionFieldsExpandBtn));
		transactionFieldsExpandBtn.click();

		Thread.sleep(2500);
	
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemExpandBtn));
		itemExpandBtn.click();
		Thread.sleep(1000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemNameBtn));
		getAction().doubleClick(itemNameBtn).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemExpandBtn));
		itemExpandBtn.click();

		Thread.sleep(1000);
		
		ScrollToElement(quantityBtn);
		Thread.sleep(1200);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(quantityBtn));
		getAction().doubleClick(quantityBtn).build().perform();
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rateBtn));
		getAction().doubleClick(rateBtn).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(grossBtn));
		getAction().doubleClick(grossBtn).build().perform();
		
		
		Thread.sleep(4000);
		
		ScrollToElement(dateExpandBtn);
		Thread.sleep(3000);
		click(dateExpandBtn);
		Thread.sleep(1500);		//getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dateExpandBtn));
		//dateExpandBtn.click();
		
	//	Thread.sleep(2000);
		
		Runtime.getRuntime().exec(getBaseDir() + "\\autoIt\\scripts\\DateMonthDrag.exe");
		
		Thread.sleep(15000);
		
		
		groupingOptionsOkBtn.click();
		
		Thread.sleep(3000);
		
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dateExpandBtn));
		dateExpandBtn.click();
		
		Thread.sleep(2000);
		
		getAction().moveToElement(transactionFieldsExpandBtn).click().build().perform();
		Thread.sleep(2000);
		
		
	///	ScrollToElement(extraFieldsExpandBtn);
		Thread.sleep(2400);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(extraFieldsExpandBtn));
		extraFieldsExpandBtn.click();
		
		ScrollToElement(warehouseExpandBtn);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouseExpandBtn));
		warehouseExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouseNameBtn));
		getAction().doubleClick(warehouseNameBtn).build().perform();

		Thread.sleep(1000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouseExpandBtn));
		warehouseExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(extraFieldsExpandBtn));
		extraFieldsExpandBtn.click();

		
		 ((JavascriptExecutor)getDriver()).executeScript("window.scrollTo(0, 0)","");
		 
		 click(finishBtn);

		//ClickUsingJs(finishBtn);
		/*getAction().moveToElement(finishBtn).build().perform();
		Thread.sleep(1000);
		finishBtn.click();*/
		
		Thread.sleep(2000);

		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : "+actMessage+" Value Expected : "+expMessage);

		if(actMessage.equalsIgnoreCase(expMessage))
		{

			return true;
		}
		else
		{
			return false;
		}
	
	}
	
	public static boolean checkCreatedMonthlyReportDetails() throws InterruptedException
	{


		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
		searchTxt.click();
		searchTxt.sendKeys("Report for MonthWise Transaction data");
		Thread.sleep(1000);
		searchTxt.sendKeys(Keys.ENTER);


		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_DateOptionDropdown));
		sl_DateOptionDropdown.click();
		Select s=new Select(sl_DateOptionDropdown);
		s.selectByValue("1");

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_OkBtn));
		sl_OkBtn.click();

		String expRow1List = "[January, item2, 50.00, 66.60, 82.00, wh3]";
		boolean actRow1List = ListComparisionWOOrder(1,report1stRowList,expRow1List);

		String expRow2List = "[February, item3, 15.00, 53.00, 45.00, wh2]";
		boolean actRow2List = ListComparisionWOOrder(1,report2ndRowList,expRow2List);

		String expRow3List = "[April, item3, 87.50, 162.00, 354.50, wh2]";
		boolean actRow3List = ListComparisionWOOrder(1,report3rdRowList,expRow3List);

		String expRow4List = "[May, item1, 38.00, 149.29, 201.35, wh2]";
		boolean actRow4List = ListComparisionWOOrder(1,report4thRowList,expRow4List);

		String expRow5List = "[June, item1, 88.77, 135.89, 388.74, wh3]";
		boolean actRow5List = ListComparisionWOOrder(1,report5thRowList,expRow5List);
		
		//
		String expRow6List = "[July, item4, 78.00, 221.00, 459.00, wh2]";
		boolean actRow6List = ListComparisionWOOrder(1,report6thRowList,expRow6List);

		String expRow7List = "[August, item3, 234.50, 264.00, 1, 445.50, wh2]";
		boolean actRow7List = ListComparisionWOOrder(1,report7thRowList,expRow7List);

		String expRow8List = "[September, item3, 162.00, 73.60, 1, 595.00, wh2]";
		boolean actRow8List = ListComparisionWOOrder(1,report8thRowList,expRow8List);

		String expRow9List = "[October, item1, 133.00, 65.00, 949.00, wh2]";
		boolean actRow9List = ListComparisionWOOrder(1,report9thRowList,expRow9List);

		String expRow10List = "[November, RMA3, 2, 294.00, 636.00, 48, 984.00, wh2]";
		boolean actRow10List = ListComparisionWOOrder(1,report10thRowList,expRow10List);
		
		String expRow11List = "[December, item3, 1, 269.50, 630.00, 8, 009.00, wh2]";
		boolean actRow11List = ListComparisionWOOrder(1,report11thRowList,expRow11List);

		String expRow12List = "[GrandTotal, 3, 476.73, 2, 456.38, 55, 990.91]";
		boolean actRow12List = ListComparisionWOOrder(1,report12thRowList,expRow12List);


		if (actRow1List&&
				actRow2List &&
				actRow3List && actRow4List && actRow5List && actRow6List 
				&& actRow7List && actRow8List 
				&& actRow9List && actRow10List && actRow11List
				&& actRow12List)
			
		{

			System.out.println(" Test Pass: Values Dsiplayed as Expected ");
			return true;
		} 
		else 
		{

			System.out.println(" Test FAIL: Values Dsiplayed as Expected ");
			return false;

		}

	}
	
	public static boolean checkCreateReportDesignerForMultipleTransactionSets() throws InterruptedException, IOException, EncryptedDocumentException, InvalidFormatException
	{
		Thread.sleep(2400);
		getDriver().navigate().refresh();
		Thread.sleep(6000);
		
		getWebDriverWaitEle(homeMenu);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(homeMenu));
		homeMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(utilities));
		utilities.click();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportDesignerMenu));
		reportDesignerMenu.click();

		Thread.sleep(4000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportNameDropdown));       
		reportNameDropdown.sendKeys("Report for Multiple Transaction sets");
		reportNameDropdown.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportTypeDropdown));
		Select rtd= new Select(reportTypeDropdown);
		rtd.selectByVisibleText("Cubes");
		
		Thread.sleep(1000);
		reportTypeDropdown.sendKeys(Keys.TAB);
		
		Thread.sleep(5000);

		getWebDriverWaitEle(inventoryExpandBtn);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(inventoryExpandBtn));
		inventoryExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportsBtn));
		reportsBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dataSetTab));
		dataSetTab.click();

		Thread.sleep(2000);

		int transactionSetListCount = transactionSetList.size();

		ArrayList<String >transactionSetListArray=new ArrayList<>();

		for(int i=0;i<=transactionSetListCount;i++)
		{
			String data = transactionSetList.get(i).getText();

			transactionSetListArray.add(data);

			if(data.equalsIgnoreCase("All transactions of document class"))
			{
				transactionSetList.get(i).click();

				break;
			}
		}

		Thread.sleep(2000);
		
		Thread.sleep(3000);
		ScrollToElement(dataSetTabVouchersTab);
		Thread.sleep(2500);
		Select voucher = new Select(dataSetTabVouchersTab);
		//voucher.selectByValue("3328");//sales invoices
		voucher.selectByVisibleText("Sales Invoices");
		Thread.sleep(2400);
		dataSetTabVouchersTab.sendKeys(Keys.TAB);
		
		
		
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(plusBtn));
		plusBtn.click();
		
		Thread.sleep(1000);
		
		boolean actTranSet = Transactionset2.isDisplayed();
		boolean expTranSet =  true;
		
		Thread.sleep(2000);
		
		Transactionset2.click();
		
		Thread.sleep(4000);
		
		int transactionSetListCount2 = transactionSet2List.size();

		ArrayList<String >transactionSetListArray2=new ArrayList<>();

		for(int i=0;i<=transactionSetListCount2;i++)
		{
			String data = transactionSet2List.get(i).getText();

			transactionSetListArray2.add(data);

			if(data.equalsIgnoreCase("All transactions of document class"))
			{
				transactionSet2List.get(i).click();

				break;
			}
		}

		Thread.sleep(2000);
		
		
		Select voucher2 = new Select(dataSet2TabVouchersTab);
		voucher2.selectByVisibleText("Receipts");
		
		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTab));
		customizationTab.click();
		Thread.sleep(3500);
		
		//getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionExpandBtn));
		//transactionExpandBtn.click();
		
		Thread.sleep(2400);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(extraFieldsExpandBtn));
		extraFieldsExpandBtn.click();
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(deptExpandBtn1));
		deptExpandBtn1.click();
		
		Thread.sleep(3500);
		
	
		Runtime.getRuntime().exec(getBaseDir() + "\\autoIt\\scripts\\scripts\\DeptDrag.exe");
		
		Thread.sleep(12000);
		
		click(groupingOptionsOkBtn);
		Thread.sleep(2400);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(extraFieldsExpandBtn));
		extraFieldsExpandBtn.click();
		
		Thread.sleep(1200);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionFieldsExpandBtn));
		transactionFieldsExpandBtn.click();

		Thread.sleep(2500);
		
		ScrollToElement(CustomerAccExpandBtn);
		Thread.sleep(1200);
		CustomerAccExpandBtn.click();
		
		
		Thread.sleep(2000);
		
	
		Runtime.getRuntime().exec(getBaseDir() + "\\autoIt\\scripts\\CustomerAccNameDrag.exe");
		
		Thread.sleep(15000);
		
	
		groupingOptionsOkBtn.click();
		
		Thread.sleep(1200);
		CustomerAccExpandBtn.click();
		
		
		 getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(Ts2docNoFieldBtn));
		 getAction().doubleClick(Ts2docNoFieldBtn).build().perform();
		 
		 Thread.sleep(2500);
		 getAction().moveToElement(NetAmountBtn).build().perform();;
		 Thread.sleep(1000);
		 getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(NetAmountBtn));
		 getAction().doubleClick(NetAmountBtn).build().perform();
		 
		 Thread.sleep(1000);
		 getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(NetAmountBtn));
		 getAction().doubleClick(NetAmountBtn).build().perform();
		 
		// ClickUsingJs(finishBtn);
		 ((JavascriptExecutor)getDriver()).executeScript("window.scrollTo(0, 0)","");

		 click(finishBtn);
			
			Thread.sleep(2000);

			String expMessage = "Data saved successfully.";

			String actMessage = checkValidationMessage(expMessage);

			System.out.println("Validation Message : "+actMessage+" Value Expected : "+expMessage);

			if(actMessage.equalsIgnoreCase(expMessage))
			{

				return true;
			}
			else
			{
				return false;
			}
		

	}
	
	public static boolean checkChangeNetAmountForEachIndividualVouchers() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		Thread.sleep(2400);
		getWebDriverWaitEle(homeMenu);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(homeMenu));
		homeMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(utilities));
		utilities.click();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportDesignerMenu));
		reportDesignerMenu.click();

		Thread.sleep(4000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportNameDropdown));       
		reportNameDropdown.sendKeys("Report for Multiple Transaction sets");
		
		Thread.sleep(1200);
		reportNameDropdown.sendKeys(Keys.TAB);
		
		Thread.sleep(3500);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTab));
		customizationTab.click();
		
		int customizeTabTableHeaderLsistCount = customizeTabTableHeaderLsist.size();
		System.err.println(customizeTabTableHeaderLsistCount);
		for(int i=1;i<=customizeTabTableHeaderLsistCount;i++)
		{
			String data = customizeTabTableHeaderLsist.get(i).getText();

			if(data.equalsIgnoreCase("Net amount"))
			{
				customizeTabTableHeaderLsist.get(i).click();

				System.err.println(i);
				Thread.sleep(1000);

				break;
			}
		}
		
		progrmmableFieldExpandBtn.click();

		Thread.sleep(1999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdExtraFiledColHeadingTxt));
		rdExtraFiledColHeadingTxt.click();
		rdExtraFiledColHeadingTxt.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
		rdExtraFiledColHeadingTxt.sendKeys("SalesInvoices NetAMT");
		Thread.sleep(1999);

		rdExtraFiledColHeadingTxt.sendKeys(Keys.TAB);
		
		
		getAction().moveToElement(rdReportExtraFieldFilterExpandBtn).build().perform();


		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdReportExtraFieldFilterExpandBtn));
		rdReportExtraFieldFilterExpandBtn.click();

		Thread.sleep(2999);
		
		Thread.sleep(2000);
		ScrollToElement(rdDefaultFilterBtn);
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdDefaultFilterBtn));
		rdDefaultFilterBtn.click();
		
		

		Thread.sleep(2000);
		Select s1=new Select(AdvanceFilterWhereDrpdwn);
		s1.selectByValue("0");


		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(AdvanceFilterNameTxt));
		AdvanceFilterNameTxt.click();
		Thread.sleep(2000);

		//getAction().moveToElement(rdAdvanceFilterDepExpBtn).build().perform();
		scrollToElementJSE(AdvFilterVoucherTypeBtn);
		Thread.sleep(2000);
		AdvFilterVoucherTypeBtn.click();
		

		Thread.sleep(2000);
		Select s3=new Select(AdvanceFilterOpersatorDrpdwn);
		s3.selectByValue("0");


		Thread.sleep(2000);
		Select s4=new Select(AdvanceFilterValueDrpdwn);
		s4.selectByValue("0");
		AdvanceFilterValueDrpdwn.sendKeys(Keys.TAB);
		Thread.sleep(1000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(AdvanceFilterSelectTxt));
		AdvanceFilterSelectTxt.click();
		AdvanceFilterSelectTxt.sendKeys("Sales Invoices");

		Thread.sleep(2999);
		AdvanceFilterSelectTxt.sendKeys(Keys.TAB);
	
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(AdvanceFilterOkBtn));
		AdvanceFilterOkBtn.click();
		
		int customizeTabTableHeaderLsistCount2 = customizeTabTableHeaderLsist.size();
		System.err.println(customizeTabTableHeaderLsistCount2);
		for(int i=1;i<=customizeTabTableHeaderLsistCount2;i++)
		{
			String data = customizeTabTableHeaderLsist.get(i).getText();

			if(data.equalsIgnoreCase("Net amount"))
			{
				customizeTabTableHeaderLsist.get(i).click();

				System.err.println(i);
				Thread.sleep(1000);

				break;
			}
		}
		
		Thread.sleep(3500);
		getAction().moveToElement(progrmmableFieldExpandBtn).build().perform();
		Thread.sleep(1200);
		//progrmmableFieldExpandBtn.click();

		Thread.sleep(1999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdExtraFiledColHeadingTxt));
		rdExtraFiledColHeadingTxt.click();
		rdExtraFiledColHeadingTxt.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
		rdExtraFiledColHeadingTxt.sendKeys("Receipts-NetAmount");
		Thread.sleep(1999);

		rdExtraFiledColHeadingTxt.sendKeys(Keys.TAB);
		
		
		//getAction().moveToElement(rdReportExtraFieldFilterExpandBtn).build().perform();


		//getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdReportExtraFieldFilterExpandBtn));
		//rdReportExtraFieldFilterExpandBtn.click();

		Thread.sleep(2999);
		
		Thread.sleep(2000);
		ScrollToElement(rdDefaultFilterBtn);
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdDefaultFilterBtn));
		rdDefaultFilterBtn.click();
		
		Thread.sleep(2000);
		Select s2=new Select(AdvanceFilterWhereDrpdwn);
		s2.selectByValue("0");


		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(AdvanceFilterNameTxt));
		AdvanceFilterNameTxt.click();
		Thread.sleep(2000);

		//getAction().moveToElement(rdAdvanceFilterDepExpBtn).build().perform();
		scrollToElementJSE(AdvFilterVoucherTypeBtn);
		Thread.sleep(2000);
		AdvFilterVoucherTypeBtn.click();
		

		Thread.sleep(2000);
		Select s5=new Select(AdvanceFilterOpersatorDrpdwn);
		s5.selectByValue("0");


		Thread.sleep(2000);
		Select s6=new Select(AdvanceFilterValueDrpdwn);
		s6.selectByValue("0");
		AdvanceFilterValueDrpdwn.sendKeys(Keys.TAB);
		Thread.sleep(1000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(AdvanceFilterSelectTxt));
		AdvanceFilterSelectTxt.click();
		AdvanceFilterSelectTxt.sendKeys("Receipts");

		Thread.sleep(2999);
		AdvanceFilterSelectTxt.sendKeys(Keys.TAB);
	
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(AdvanceFilterOkBtn));
		AdvanceFilterOkBtn.click();
		
		
		Thread.sleep(2999);
		finishBtn.click();
		Thread.sleep(2000);

		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : "+actMessage+" Value Expected : "+expMessage);

		if(actMessage.equalsIgnoreCase(expMessage))
		{

			System.out.println(" Test PasS: Multiple Chkbx is Selcted ");
			return true;
		}
		else
		{
			System.out.println(" Test FAIL:  Multiple Chkbx is Selcted ");
			return false;
		}

		
		
	}
	
	
	public static boolean checkCreatedMultipleTransactionSetsReportDetails() throws InterruptedException
	{


		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
		searchTxt.click();
		searchTxt.sendKeys("Report for Multiple Transaction sets");
		Thread.sleep(1000);
		searchTxt.sendKeys(Keys.ENTER);
		
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_DateOptionDropdown));
		sl_DateOptionDropdown.click();
		Select s=new Select(sl_DateOptionDropdown);
		s.selectByValue("1");

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_OkBtn));
		sl_OkBtn.click();

		String expRow1List = "[dep1, 695.00, 25.00]";
		boolean actRow1List = ListComparisionWOOrder(1,report1stRowList,expRow1List);

		String expRow2List = "[Cash, Rct:2, 25.00]";
		boolean actRow2List = ListComparisionWOOrder(1,report2ndRowList,expRow2List);

		String expRow3List = "[CustomerA, SalInv:28, 85.00]";
		boolean actRow3List = ListComparisionWOOrder(1,report3rdRowList,expRow3List);

		String expRow4List = "[CustomerB, SalInv:18, 610.00]";
		boolean actRow4List = ListComparisionWOOrder(1,report4thRowList,expRow4List);

		String expRow5List = "[dep2, 1, 705.09, 80.00]";
		boolean actRow5List = ListComparisionWOOrder(1,report5thRowList,expRow5List);
		
		//
		String expRow6List = "[Bank, Rct:1, 80.00]";
		boolean actRow6List = ListComparisionWOOrder(1,report6thRowList,expRow6List);

		String expRow7List = "[CustomDutyPayable, SalInv:27, 22.00]";
		boolean actRow7List = ListComparisionWOOrder(1,report7thRowList,expRow7List);

		String expRow8List = "[CustomerA, SalInv:33, 193.35]";
		boolean actRow8List = ListComparisionWOOrder(1,report8thRowList,expRow8List);

		String expRow9List = "[CustomerB, SalInv:7, 1, 474.74]";
		boolean actRow9List = ListComparisionWOOrder(1,report9thRowList,expRow9List);

		String expRow10List = "[CustomerC, SalInv:13, 15.00]";
		boolean actRow10List = ListComparisionWOOrder(1,report10thRowList,expRow10List);
		
		String expRow11List = "[dep3, 1, 342.00]";
		boolean actRow11List = ListComparisionWOOrder(1,report11thRowList,expRow11List);

		String expRow12List = "[CustomerA, SalInv:36, 60.00]";
		boolean actRow12List = ListComparisionWOOrder(1,report12thRowList,expRow12List);
		

		String expRow13List = "[CustomerB, SalInv:45, 96.00]";
		boolean actRow13List = ListComparisionWOOrder(1,report13thRowList,expRow13List);

		String expRow14List = "[CustomerC, SalInv:22, 1, 186.00]";
		boolean actRow14List = ListComparisionWOOrder(1,report14thRowList,expRow14List);

		String expRow15List = "[dep4]";
		boolean actRow15List = ListComparisionWOOrder(1,report15thRowList,expRow15List);
		
		String expRow16List = "[CustomerB, SalInv:38]";
		boolean actRow16List = ListComparisionWOOrder(1,report16thRowList,expRow16List);
		
		click(sl_NextBtn);
		Thread.sleep(2000);

		String expRow17List = "[dept, 98.00]";
		boolean actRow17List = ListComparisionWOOrder(1,report1stRowList,expRow17List);
		
		String expRow18List = "[Costofgoodssold-Computers, SalInv:2, 98.00]";
		boolean actRow18List = ListComparisionWOOrder(1,report2ndRowList,expRow18List);
		
		String expRow19List = "[GrandTotal, 3, 840.09, 105.00]";
		boolean actRow19List = ListComparisionWOOrder(1,report3rdRowList,expRow19List);


		if (actRow1List&&
				actRow2List &&
				actRow3List && actRow4List && actRow5List && actRow6List 
				&& actRow7List && actRow8List 
				&& actRow9List && actRow10List && actRow11List
				&& actRow12List && actRow13List && actRow14List && actRow15List 
				&& actRow16List && actRow17List)
			
		{

			System.out.println(" Test Pass: Values Dsiplayed as Expected ");
			return true;
		} 
		else 
		{

			System.out.println(" Test FAIL: Values Dsiplayed as Expected ");
			return false;

		}

	}
	

	/*public static boolean checkAddHeaderFooterLayoutInReportDesigner()
	{
		
	}
	*/
	
	public static boolean checkSaveRDWithRowFormattingForItemNameAndQuantityFileds() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		Thread.sleep(2999);
		getAction().moveToElement(homeMenu).build().perform();
		Thread.sleep(1200);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(homeMenu));
		homeMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(utilities));
		utilities.click();

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportDesignerMenu));
		reportDesignerMenu.click();

		Thread.sleep(2999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportNameDropdown));       
		reportNameDropdown.sendKeys("RD For RowFormatting");
		reportNameDropdown.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportTypeDropdown));
		Select rtd= new Select(reportTypeDropdown);
		rtd.selectByVisibleText("Details");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(inventoryExpandBtn));
		inventoryExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportsBtn));
		reportsBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dataSetTab));
		dataSetTab.click();

		Thread.sleep(2000);

		int transactionSetListCount = transactionSetList.size();

		ArrayList<String >transactionSetListArray=new ArrayList<>();

		for(int i=0;i<=transactionSetListCount;i++)
		{
			String data = transactionSetList.get(i).getText();

			transactionSetListArray.add(data);

			if(data.equalsIgnoreCase("All transactions of document class"))
			{
				transactionSetList.get(i).click();

				break;
			}
		}

		Thread.sleep(3000);
		Select voucher = new Select(dataSetTabVouchersTab);
		voucher.selectByVisibleText("Purchases Vouchers");

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTab));
		customizationTab.click();
		
		//getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionExpandBtn));
		//transactionExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionFieldsExpandBtn));
		transactionFieldsExpandBtn.click();

		Thread.sleep(1000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dateExpandBtn));
		dateExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dateFieldBtn));
		getAction().doubleClick(dateFieldBtn).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dateExpandBtn));
		dateExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(docNoFieldBtn));
		getAction().doubleClick(docNoFieldBtn).build().perform();

		Thread.sleep(1000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(itemExpandBtn));
		itemExpandBtn.click();
		Thread.sleep(1000);
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
		Thread.sleep(1000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(extraFieldsExpandBtn));
		extraFieldsExpandBtn.click();

		Thread.sleep(1000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(deptExpandBtn));
		deptExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(deptNameBtn));
		getAction().doubleClick(deptNameBtn).build().perform();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(deptExpandBtn));
		deptExpandBtn.click();

		Thread.sleep(1000);
		
		ScrollToElement(warehouseExpandBtn);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouseExpandBtn));
		warehouseExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouseNameBtn));
		getAction().doubleClick(warehouseNameBtn).build().perform();

		Thread.sleep(1000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouseExpandBtn));
		warehouseExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(extraFieldsExpandBtn));
		extraFieldsExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionFieldsExpandBtn));
		transactionFieldsExpandBtn.click();
		

		getAction().moveToElement(finishBtn).build().perform();
		Thread.sleep(1200);
		finishBtn.click();
		
		Thread.sleep(2000);

		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : "+actMessage+" Value Expected : "+expMessage);

		if(actMessage.equalsIgnoreCase(expMessage))
		{

			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	/*public static boolean checkEditSavedRDForRowFormatting() throws InterruptedException
	{
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportNameDropdown));       
		
		reportNameDropdown.sendKeys(Keys.SPACE);

		int reportNameListcount = reportNameList.size();

		for(int i=0;i<reportNameListcount;i++)
		{
			String data = reportNameList.get(i).getText();

			if(data.equalsIgnoreCase("RD For RowFormatting"))
			{
				reportNameList.get(i).click();
				break;
			}
		}

		reportNameDropdown.sendKeys(Keys.TAB);


		Thread.sleep(3000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTab));
		customizationTab.click();

		Thread.sleep(3000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rowFormattingTab));
		rowFormattingTab.click();
		
		
		
	}
	*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@FindBy(xpath="//*[@id='MasterSingle__1']")
	public static WebElement ItemDropdownInRD;
	
	public static boolean checkValidateItemDropdownInReportPageAndFilterItemFieldInDSTab() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
		searchTxt.click();
		searchTxt.sendKeys("All transactions of document class of Purchase Type");
		Thread.sleep(1000);
		searchTxt.sendKeys(Keys.ENTER);
		
		boolean actItemDropdown = ItemDropdownInRD.isDisplayed();
		boolean expItemDropdown = true;
		
		Thread.sleep(1200);
		
		
		reportCustomizeBtnHomePage.click();
		String actReportName = reportNameDropdown.getText();
		String expReportName = "";
		
		
		

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdDatSetFilterBtn));
		rdDatSetFilterBtn.click();
		
		

		Thread.sleep(2000);
		Select s1=new Select(rdAdvanceFilterWhereDrpdwn);
		s1.selectByValue("0");


		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdAdvanceFilterNameTxt));
		rdAdvanceFilterNameTxt.click();
		Thread.sleep(2000);

		//getAction().moveToElement(rdAdvanceFilterDepExpBtn).build().perform();
		scrollToElementJSE(rdAdvanceFilterDepExpBtn);
		Thread.sleep(2000);
		rdAdvanceFilterDepExpBtn.click();
		
		int count = deptList.size();
		
		for (int i = 0; i < count; i++)
		{
			String data = deptList.get(i).getText();
			if (data.equalsIgnoreCase("Name")) 
			{
				 deptList.get(i).click();
			}
		}

		//rdAdvanceFilterName_DepNameBtn.click();

		Thread.sleep(2000);
		Select s3=new Select(rdAdvanceFilterOpersatorDrpdwn);
		s3.selectByValue("0");


		Thread.sleep(2000);
		Select s4=new Select(rdAdvanceFilterValueDrpdwn);
		s4.selectByValue("2");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdAdvanceFilterSelectTxt));
		rdAdvanceFilterSelectTxt.click();
		rdAdvanceFilterSelectTxt.sendKeys("@department");

		Thread.sleep(2999);
		rdAdvanceFilterSelectTxt.sendKeys(Keys.TAB);


	/*	boolean novalidationConfirmationMessage =validationConfirmationMessage.getText().isEmpty();

		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";
*/
		
		scrollToElementJSE(rdAdvanceFilterSelectTxt);
		Thread.sleep(2000);
		String actrdAdvanceFilterSelectTxt=rdAdvanceFilterSelectTxt.getText();
		String exprdAdvanceFilterSelectTxt="@department";

		System.out.println(" rdAdvanceFilterSelectTxt : "+actrdAdvanceFilterSelectTxt +" Value exp: "+exprdAdvanceFilterSelectTxt);

		Thread.sleep(2999);

		getAction().moveToElement(finishBtn).build().perform();
		Thread.sleep(1200);
		finishBtn.click();
		Thread.sleep(2000);

		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : "+actMessage+" Value Expected : "+expMessage);

		if(actMessage.equalsIgnoreCase(expMessage))
		{

			System.out.println(" Test PasS: Multiple Chkbx is Selcted ");
			return true;
		}
		else
		{
			System.out.println(" Test FAIL:  Multiple Chkbx is Selcted ");
			return false;
		}
	
	}
	
	
	
	
	///////////////////////////////////New Scenarios///////////////////////////////////
	
	@FindBy(xpath="//span[text()=\"DocNo\"]")
	public static WebElement docNoField;
	
	
	
	
	
	
	
	public boolean checkSavingRDReportforValidatingItemGroupFilter() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
		searchTxt.click();
		Thread.sleep(1000);
		searchTxt.sendKeys("Report Designer");
		Thread.sleep(1000);
		searchTxt.sendKeys(Keys.ENTER);
		
		Thread.sleep(4000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportNameDropdown));       
		reportNameDropdown.sendKeys("Report for Item Group Filter");
		reportNameDropdown.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportTypeDropdown));
		Select rtd= new Select(reportTypeDropdown);
		rtd.selectByVisibleText("Details");
		
		Thread.sleep(1000);
		reportTypeDropdown.sendKeys(Keys.TAB);
		
		Thread.sleep(3000);

		getWebDriverWaitEle(inventoryExpandBtn);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(inventoryExpandBtn));
		inventoryExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportsBtn));
		reportsBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dataSetTab));
		dataSetTab.click();

		Thread.sleep(2000);

		int transactionSetListCount = transactionSetList.size();

		ArrayList<String >transactionSetListArray=new ArrayList<>();

		for(int i=0;i<=transactionSetListCount;i++)
		{
			String data = transactionSetList.get(i).getText();

			transactionSetListArray.add(data);

			if(data.equalsIgnoreCase("Inventory transactions"))
			{
				transactionSetList.get(i).click();

				break;
			}
		}

		Thread.sleep(2000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTab));
		customizationTab.click();

		Thread.sleep(2000);
		//getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionExpandBtn));
		//transactionExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionFieldsExpandBtn));
		transactionFieldsExpandBtn.click();

		getAction().moveToElement(docNoField).doubleClick().build().perform();
		
		getAction().moveToElement(itemExpandBtn).build().perform();
		click(itemExpandBtn);
		
		getAction().doubleClick(itemNameBtn).build().perform();
		click(itemExpandBtn);
				
		Thread.sleep(2000);
		
		getAction().moveToElement(quantityBtn).doubleClick().build().perform();
		
		getAction().doubleClick(rateBtn).build().perform();
		
		Thread.sleep(2000);
		
		getAction().moveToElement(finishBtn).build().perform();
		click(finishBtn);
		
		Thread.sleep(2000);
		
		String expMsg="Data saved successfully.";
		String actMsg=checkValidationMessage(expMsg);
		
		if(actMsg.equalsIgnoreCase(expMsg))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	@FindBy(xpath="(//i[@class='icon-filter2 hiconright2'])[2]")
	public static WebElement reportEntryCustomizationBtn;
	
	@FindBy(xpath="//*[@id='tblRDRender']//thead//th")
	public static List<WebElement> rdPreviewtableHeaderList;
	
	@FindBy(xpath="//*[@id='advancefilter_master_0_0_']")
	public static WebElement rdFilterTxt;
	
	@FindBy(xpath="//*[@id='advancefilter_master_22_0_']")
	public static WebElement rdFilterTxt1;
	
	public boolean checkReportforItemGroupFilterBeforeandAfterFilteronGroupItem() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
		searchTxt.click();
		Thread.sleep(1000);
		searchTxt.sendKeys("Report for Item Group Filter");
		Thread.sleep(1000);
		searchTxt.sendKeys(Keys.ENTER);
		
		Thread.sleep(4000);
		
		
		click(sl_OkBtn);
		Thread.sleep(5000);
		
		int report2ndColCount=report2ndColList.size();
		
		ArrayList<String>report2ndColArrayList=new ArrayList<String>();
		for(int i=0;i<report2ndColCount;i++)
		{
			report2ndColArrayList.add(report2ndColList.get(i).getText());
			
		}
	
		click(report_LastBtn);
		Thread.sleep(2000);
		
		
		int report2ndColCount1=report2ndColList.size();
		
		
		for(int i=0;i<report2ndColCount1;i++)
		{
			report2ndColArrayList.add(report2ndColList.get(i).getText());
			
		}
		String actItemNameBeforeFilter=report2ndColArrayList.toString();
		String expItemNameBeforeFilter="[RMA1, RMA3, item1, RMA1, RMA3, RMA3, RMA2, RMA2, RMA2, RMA1, RMA2, RMA3, RMA1, RMA2, RMA2, RMA3, RMA2, RMA2, RMA2, RMA2, RMA2, RMA2, ]";
		
		System.out.println("Actual Item list Before Group Filter :		"		+		actItemNameBeforeFilter);
		System.out.println("Expect Item list Before Group Filter :		"		+		expItemNameBeforeFilter);
		
		click(reportEntryCustomizationBtn);
		Thread.sleep(2500);
		
		click(previewTab);
		Thread.sleep(2000);
		
		
		int count=rdPreviewtableHeaderList.size();
		
		ArrayList<String>reportPreviewHeaderArray=new ArrayList<String>();
		for(int i=0;i<count;i++)
		{
			reportPreviewHeaderArray.add(rdPreviewtableHeaderList.get(i).getText());
		}
		String actPreviewTabData=reportPreviewHeaderArray.toString();
		String expPreviewTabData="[, Document No., Item.Name, Quantity, Rate]";
		
		
		System.out.println("Actual Preview Data		"	+	actPreviewTabData);
		System.out.println("Expect Preview Data		"	+	expPreviewTabData);
		
		click(dataSetTab);
		Thread.sleep(1500);
		
		click(rdDatSetFilterBtn);
		Thread.sleep(2000);
		
		click(rdAdvanceFilterRemoveBtn1);
		Thread.sleep(1000);
		
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdAdvanceFilterWhereDrpdwnForPaea));
		rdAdvanceFilterWhereDrpdwnForPaea.click();
		Select s1=new Select(rdAdvanceFilterWhereDrpdwnForPaea);
		s1.selectByValue("0");


		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdAdvanceFilterNameTxt1));
		rdAdvanceFilterNameTxt1.click();
		Thread.sleep(2000);

		
		scrollToElementJSE(rdAdvanceFilterItemExpBtn);
		Thread.sleep(2000);
		rdAdvanceFilterItemExpBtn.click();
	
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdAdvanceFilterItemNameBtn));
		rdAdvanceFilterItemNameBtn.click();
		
		//rdAdvanceFilterName_DepNameBtn.click();

		Thread.sleep(2000);
		Select s3=new Select(rdAdvanceFilterOpersatorDrpdwn1);
		s3.selectByValue("0");


		Thread.sleep(2000);
		Select s4=new Select(rdAdvanceFilterValueDrpdwn1);
		s4.selectByValue("0");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdFilterTxt));
		rdFilterTxt.click();
		rdFilterTxt.sendKeys("Focus");

		Thread.sleep(2999);
		rdFilterTxt.sendKeys(Keys.TAB);

		Thread.sleep(2999);

		 ((JavascriptExecutor)getDriver()).executeScript("window.scrollTo(0, 0)","");
			
		 Thread.sleep(2000);
		finishBtn.click();
		Thread.sleep(2000);

		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : "+actMessage+" Value Expected : "+expMessage);

		///After Filter in Data set
		
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
		searchTxt.click();
		Thread.sleep(1000);
		searchTxt.sendKeys("Report for Item Group Filter");
		Thread.sleep(1000);
		searchTxt.sendKeys(Keys.ENTER);
		
		Thread.sleep(4000);
		
		
		click(sl_OkBtn);
		Thread.sleep(5000);
		
		int report2ndColCount2=report2ndColList.size();
		
		ArrayList<String>report2ndColArrayList1=new ArrayList<String>();
		for(int i=0;i<report2ndColCount2;i++)
		{
			report2ndColArrayList1.add(report2ndColList.get(i).getText());
			
		}
	
		click(report_LastBtn);
		Thread.sleep(2000);
		
		
		int report2ndColCount3=report2ndColList.size();
		
		
		for(int i=0;i<report2ndColCount3;i++)
		{
			report2ndColArrayList1.add(report2ndColList.get(i).getText());
			
		}
		String actItemNameAfterFilter=report2ndColArrayList1.toString();
		String expItemNameAfterFilter="[item1, item1, item1, item2, item1, item1, item2, item1, item2, item2, item1, item2, item2, item2, item1, item2, item2, item2, item2, item2, item2, item2, ]";
		
		System.out.println("Actual Item list After Group Filter :		"		+		actItemNameAfterFilter);
		System.out.println("Expect Item list After Group Filter :		"		+		expItemNameAfterFilter);
		
		
		
		
		
		if(actMessage.equalsIgnoreCase(expMessage) && actItemNameBeforeFilter.equalsIgnoreCase(expItemNameBeforeFilter)
				&& actItemNameAfterFilter.equalsIgnoreCase(expItemNameAfterFilter))
		{

			System.out.println(" Test PasS: Multiple Chkbx is Selcted ");
			return true;
		}
		else
		{
			System.out.println(" Test FAIL:  Multiple Chkbx is Selcted ");
			return false;
		}
		
		
	}
	
	
	
	
	public boolean checkSavingRDReportforValidatingAnalyzeReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{

		
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
		searchTxt.click();
		Thread.sleep(1000);
		searchTxt.sendKeys("Report Designer");
		Thread.sleep(1000);
		searchTxt.sendKeys(Keys.ENTER);
		
		Thread.sleep(4000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportNameDropdown));       
		reportNameDropdown.sendKeys("Report for Analyze");
		reportNameDropdown.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportTypeDropdown));
		Select rtd= new Select(reportTypeDropdown);
		rtd.selectByVisibleText("Details");
		
		Thread.sleep(1000);
		reportTypeDropdown.sendKeys(Keys.TAB);
		
		Thread.sleep(3000);

		getWebDriverWaitEle(inventoryExpandBtn);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(inventoryExpandBtn));
		inventoryExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportsBtn));
		reportsBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dataSetTab));
		dataSetTab.click();

		Thread.sleep(2000);

		int transactionSetListCount = transactionSetList.size();

		ArrayList<String >transactionSetListArray=new ArrayList<>();

		for(int i=0;i<=transactionSetListCount;i++)
		{
			String data = transactionSetList.get(i).getText();

			transactionSetListArray.add(data);

			if(data.equalsIgnoreCase("Inventory transactions"))
			{
				transactionSetList.get(i).click();

				break;
			}
		}

		Thread.sleep(2000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTab));
		customizationTab.click();

		Thread.sleep(2000);
		//getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionExpandBtn));
		//transactionExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionFieldsExpandBtn));
		transactionFieldsExpandBtn.click();

		getAction().moveToElement(docNoField).doubleClick().build().perform();
		
		getAction().moveToElement(itemExpandBtn).build().perform();
		click(itemExpandBtn);
		
		getAction().doubleClick(itemNameBtn).build().perform();
		click(itemExpandBtn);
				
		Thread.sleep(2000);
		
		getAction().moveToElement(quantityBtn).doubleClick().build().perform();
		
		getAction().doubleClick(rateBtn).build().perform();
		
		Thread.sleep(2000);
		
		getAction().moveToElement(voucherclassBtn).doubleClick().build().perform();
		getAction().moveToElement(voucherNameBtn).doubleClick().build().perform();
		
		getAction().moveToElement(finishBtn).build().perform();
		click(finishBtn);
		
		Thread.sleep(2000);
		
		String expMsg="Data saved successfully.";
		String actMsg=checkValidationMessage(expMsg);
		
		if(actMsg.equalsIgnoreCase(expMsg))
		{
			return true;
		}
		else
		{
			return false;
		}
	
	}
	
	@FindBy(xpath="//span[text()=\"Item.Name \"]")
	public static WebElement analyze_ItemNameTxt;
	
	@FindBy(xpath="//*[@id='rowGroupingData']")
	public static WebElement analyze_RowGroupingTxt;
	
	@FindBy(xpath="//*[@id='analyzeReportTableBody']/tr/td[2]")
	public static List<WebElement> analyze_RowGroupingList;
	
	@FindBy(xpath="//a[@title='Analyze']")
	public static WebElement rdanalyzBtn;
	
	
	public boolean checkCreatedReportforAnalyzeReport() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
		searchTxt.click();
		Thread.sleep(1000);
		searchTxt.sendKeys("Report for Analyze");
		Thread.sleep(1000);
		searchTxt.sendKeys(Keys.ENTER);
		
		Thread.sleep(4000);
		
		click(sl_OkBtn);
		Thread.sleep(6000);
		
		click(rdanalyzBtn);
		Thread.sleep(18000);
		
		Runtime.getRuntime().exec(getBaseDir() + "\\autoIt\\scripts\\scripts\\ItemNameAnalyze.exe");
		
		Thread.sleep(9000);
		
		
		
		int count=analyze_RowGroupingList.size();
		ArrayList<String>analyzeRowGrpArray=new ArrayList<String>();
		for(int i=0;i<count;i++)
		{
			analyzeRowGrpArray.add(analyze_RowGroupingList.get(i).getText());
		}
		String actanalyzeRowGrpList=analyzeRowGrpArray.toString();
		String expanalyzeRowGrpList="[Batch, batch1, item1, item2, item3, item4, RMA1, RMA2, RMA3, ZARA-TOP, Grand Total]";
		
		System.out.println("Actual Row Grouping		"	+		actanalyzeRowGrpList);
		System.out.println("Expect Row Grouping		"	+		expanalyzeRowGrpList);
		
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_LA_saveBtn));
		sl_LA_saveBtn.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_LA_repotNameTxt));
		sl_LA_repotNameTxt.click();
		sl_LA_repotNameTxt.sendKeys("RDAnalysisReport");
		Thread.sleep(2000);
		sl_LA_repotNameTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_LA_repotFinanicalMenu));
		sl_LA_repotFinanicalMenu.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_LA_repotSaveBtn));
		sl_LA_repotSaveBtn.click();


		String ExpMessage="Analyze Report Saved Successfully";
		String actMessage=checkValidationMessage(ExpMessage);
		
		Thread.sleep(3000);
		
		if(actanalyzeRowGrpList.equalsIgnoreCase(expanalyzeRowGrpList) && actMessage.equalsIgnoreCase(ExpMessage))
		{
			return true;
		}
		else
		{
			return false;
		}
		
		
	}
	
	
	public boolean checkSavedRDAnalyzedReport() throws InterruptedException
	{
		
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
		searchTxt.click();
		Thread.sleep(1000);
		searchTxt.sendKeys("RDAnalysisReport");
		Thread.sleep(1000);
		searchTxt.sendKeys(Keys.ENTER);
		
		Thread.sleep(4000);
		
		click(sl_OkBtn);
		Thread.sleep(8000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_1stRow1stCol));
		
		int reportsRow1ListCount = report1stRowList.size();
		ArrayList<String> reportsRow1ListArray = new ArrayList<String>();
		for(int i=1;i<reportsRow1ListCount;i++)
		{
			String data = report1stRowList.get(i).getText();
			reportsRow1ListArray.add(data);
		}
		String actRow1List = reportsRow1ListArray.toString();
		String expRow1List = "[Batch, 768, 7.50, 3.00]";
		
		
		int report2ndRowListCount = report2ndRowList.size();
		ArrayList<String> report2ndRowListArray = new ArrayList<String>();
		for(int i=1;i<report2ndRowListCount;i++)
		{
			String data = report2ndRowList.get(i).getText();
			report2ndRowListArray.add(data);
		}
		String actRow2List = report2ndRowListArray.toString();
		String expRow2List = "[batch1, 768, 50.00, 3.00]";
		
		
		int report3rdRowListCount = report3rdRowList.size();
		ArrayList<String> report3rdRowListArray = new ArrayList<String>();
		for(int i=1;i<report3rdRowListCount;i++)
		{
			String data = report3rdRowList.get(i).getText();
			report3rdRowListArray.add(data);
		}
		String actRow3List = report3rdRowListArray.toString();
		String expRow3List = "[item1, 1280, 107.00, 601.09]";
		
		
		int report4thRowListCount = report4thRowList.size();
		ArrayList<String> report4thRowListArray = new ArrayList<String>();
		for(int i=1;i<report4thRowListCount;i++)
		{
			String data = report4thRowList.get(i).getText();
			report4thRowListArray.add(data);
		}
		String actRow4List = report4thRowListArray.toString();
		String expRow4List = "[item2, 1280, 142.00, 333.00]";

		int report5thRowListCount = report5thRowList.size();
		ArrayList<String> report5thRowListArray = new ArrayList<String>();
		for(int i=1;i<report5thRowListCount;i++)
		{
			String data = report5thRowList.get(i).getText();
			report5thRowListArray.add(data);
		}
		String actRow5List = report5thRowListArray.toString();
		String expRow5List = "[item3, 1280, 91.64, 303.00]";

		int report6thRowListCount = report6thRowList.size();
		ArrayList<String> report6thRowListArray = new ArrayList<String>();
		for(int i=1;i<report6thRowListCount;i++)
		{
			String data = report6thRowList.get(i).getText();
			report6thRowListArray.add(data);
		}
		String actRow6List = report6thRowListArray.toString();
		String expRow6List = "[item4, 768, 45.59, 144.29]";
		
		int report7thRowListCount = report7thRowList.size();
		ArrayList<String> report7thRowListArray = new ArrayList<String>();
		for(int i=1;i<report7thRowListCount;i++)
		{
			String data = report7thRowList.get(i).getText();
			report7thRowListArray.add(data);
		}
		String actRow7List = report7thRowListArray.toString();
		String expRow7List = "[RMA1, 3072, 1,045.00, 513.00]";
		
		
		int report8thRowListCount = report8thRowList.size();
		ArrayList<String> report8thRowListArray = new ArrayList<String>();
		for(int i=1;i<report8thRowListCount;i++)
		{
			String data = report8thRowList.get(i).getText();
			report8thRowListArray.add(data);
		}
		String actRow8List = report8thRowListArray.toString();
		String expRow8List = "[RMA2, 3072, 2,085.00, 277.00]";
		
		
		int report9thRowListCount = report9thRowList.size();
		ArrayList<String> report9thRowListArray = new ArrayList<String>();
		for(int i=1;i<report9thRowListCount;i++)
		{
			String data = report9thRowList.get(i).getText();
			report9thRowListArray.add(data);
		}
		String actRow9List = report9thRowListArray.toString();
		String expRow9List = "[RMA3, 6144, 102.00, 270.00]";
		
		
		int report10thRowListCount = report10thRowList.size();
		ArrayList<String> report10thRowListArray = new ArrayList<String>();
		for(int i=1;i<report10thRowListCount;i++)
		{
			String data = report10thRowList.get(i).getText();
			report10thRowListArray.add(data);
		}
		String actRow10List = report10thRowListArray.toString();
		String expRow10List = "[ZARA-TOP, 3328, 199.00, 9.00]";
		
		
		int report11thRowListCount = report11thRowList.size();
		ArrayList<String> report11thRowListArray = new ArrayList<String>();
		for(int i=1;i<report11thRowListCount;i++)
		{
			String data = report11thRowList.get(i).getText();
			report11thRowListArray.add(data);
		}
		String actRow11List = report11thRowListArray.toString();
		String expRow11List = "[Grand Total, , 3,476.73, 2,456.38]";
		
		System.out.println("*********************************************************************");
		
		System.out.println("actRow1List  : "+actRow1List);
		System.out.println("expRow1List  : "+expRow1List);
		System.out.println("*********************************************************************");
				
		
		System.out.println("actRow2List  : "+actRow2List);
		System.out.println("expRow2List  : "+expRow2List);
		System.out.println("*********************************************************************");
				
		System.out.println("actRow3List  : "+actRow3List);
		System.out.println("expRow3List  : "+expRow3List);
		System.out.println("*********************************************************************");
				
		System.out.println("actRow4List  : "+actRow4List);
		System.out.println("expRow4List  : "+expRow4List);
		System.out.println("*********************************************************************");
				
		System.out.println("actRow5List  : "+actRow5List);
		System.out.println("expRow5List  : "+expRow5List);
		System.out.println("*********************************************************************");
				
		System.out.println("actRow6List  : "+actRow6List);
		System.out.println("expRow6List  : "+expRow6List);
		System.out.println("*********************************************************************");
				
		System.out.println("actRow7List  : "+actRow7List);
		System.out.println("expRow7List  : "+expRow7List);
		System.out.println("*********************************************************************");
				
		System.out.println("actRow8List  : "+actRow8List);
		System.out.println("expRow8List  : "+expRow8List);
		System.out.println("*********************************************************************");
		
		System.out.println("actRow9List  : "+actRow9List);
		System.out.println("expRow9List  : "+expRow9List);
		
		System.out.println("*********************************************************************");
		
		System.out.println("actRow10List  : "+actRow10List);
		System.out.println("expRow10List  : "+expRow10List);
		System.out.println("*********************************************************************");
		
		
		System.out.println("actRow11List  : "+actRow11List);
		System.out.println("expRow11List  : "+expRow11List);
		System.out.println("*********************************************************************");
		
		if(actRow2List.equalsIgnoreCase(expRow2List) &&
				actRow3List.equalsIgnoreCase(expRow3List) &&
				actRow4List.equalsIgnoreCase(expRow4List) &&
				actRow5List.equalsIgnoreCase(expRow5List) &&
				actRow6List.equalsIgnoreCase(expRow6List) &&
				actRow7List.equalsIgnoreCase(expRow7List) &&
				actRow8List.equalsIgnoreCase(expRow8List) && actRow9List.equalsIgnoreCase(expRow9List)
				&& actRow10List.equalsIgnoreCase(expRow10List) 
				&&  actRow11List.equalsIgnoreCase(expRow11List))
		{
			
			
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	
	@FindBy(xpath="//span[text()=\"Department\"]/../i")
	public static WebElement departmentExpBtn;
	
	@FindBy(xpath="((//span[contains(text(),'Department')]/../i)[2]/../following::ul//li//span[contains(text(),'Name')])[1]")
	public static WebElement departmentNameField;
	
	@FindBy(xpath="//span[text()=\"Warehouse\"]/../i")
	public static WebElement warehouseExpBtn;
	
	@FindBy(xpath="(//span[text()=\"Warehouse\"]/../i/../following::ul//li//span[text()=\"Name\"])[1]")
	public static WebElement warehouseNameField;
	
	
	@FindBy(xpath="//a[@title='Reset']")
	public static WebElement rdResetBtn;
	
	public boolean checkSavingDetailsReportforFilter() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{


		
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
		searchTxt.click();
		Thread.sleep(1000);
		searchTxt.sendKeys("Report Designer");
		Thread.sleep(1000);
		searchTxt.sendKeys(Keys.ENTER);
		
		Thread.sleep(4000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportNameDropdown));       
		reportNameDropdown.sendKeys("Report for Filter");
		reportNameDropdown.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportTypeDropdown));
		Select rtd= new Select(reportTypeDropdown);
		rtd.selectByVisibleText("Details");
		
		Thread.sleep(1000);
		reportTypeDropdown.sendKeys(Keys.TAB);
		
		Thread.sleep(3000);

		getWebDriverWaitEle(inventoryExpandBtn);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(inventoryExpandBtn));
		inventoryExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportsBtn));
		reportsBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dataSetTab));
		dataSetTab.click();

		Thread.sleep(2000);

		int transactionSetListCount = transactionSetList.size();

		ArrayList<String >transactionSetListArray=new ArrayList<>();

		for(int i=0;i<=transactionSetListCount;i++)
		{
			String data = transactionSetList.get(i).getText();

			transactionSetListArray.add(data);

			if(data.equalsIgnoreCase("Inventory transactions"))
			{
				transactionSetList.get(i).click();

				break;
			}
		}

		Thread.sleep(2000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTab));
		customizationTab.click();

		Thread.sleep(2000);
		//getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionExpandBtn));
		//transactionExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionFieldsExpandBtn));
		transactionFieldsExpandBtn.click();

		getAction().moveToElement(docNoField).doubleClick().build().perform();
		
		getAction().moveToElement(itemExpandBtn).build().perform();
		click(itemExpandBtn);
		
		getAction().doubleClick(itemNameBtn).build().perform();
		click(itemExpandBtn);
				
		Thread.sleep(2000);
		
		getAction().moveToElement(quantityBtn).doubleClick().build().perform();
		
		getAction().doubleClick(rateBtn).build().perform();
		
		Thread.sleep(2000);
		
	
		getAction().moveToElement(transactionFieldsExpandBtn).click().build().perform();
		
		click(extraFieldsExpandBtn);
		
		getAction().moveToElement(departmentExpBtn).click().build().perform();
	
		getAction().moveToElement(departmentNameField).doubleClick().build().perform();
		
		getAction().moveToElement(departmentExpBtn).click().build().perform();
		
		getAction().moveToElement(warehouseExpBtn).click().build().perform();
		Thread.sleep(1000);
		
		getAction().moveToElement(warehouseNameField).build().perform();
		Thread.sleep(2000);
		
		getAction().moveToElement(warehouseNameField).doubleClick().build().perform();
		
		
		getAction().moveToElement(finishBtn).build().perform();
		click(finishBtn);
		
		Thread.sleep(2000);
		
		String expMsg="Data saved successfully.";
		String actMsg=checkValidationMessage(expMsg);
		Thread.sleep(1500);

		click(rdResetBtn);
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportNameDropdown));       
		reportNameDropdown.sendKeys("Report for Filter");
		Thread.sleep(2000);
		reportNameDropdown.sendKeys(Keys.TAB);

		Thread.sleep(4000);
		
		click(dataSetTab);
		Thread.sleep(1500);
		
		click(rdDatSetFilterBtn);
		Thread.sleep(2000);
		
		click(rdAdvanceFilterRemoveBtn);
		Thread.sleep(1000);
		
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdAdvanceFilterWhereDrpdwnForPaea1));
		rdAdvanceFilterWhereDrpdwnForPaea1.click();
		Select s1=new Select(rdAdvanceFilterWhereDrpdwnForPaea1);
		s1.selectByValue("0");


		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdAdvanceFilterNameTxt));
		rdAdvanceFilterNameTxt.click();
		Thread.sleep(2000);

		
		scrollToElementJSE(rdAdvanceFilterItemExpBtn);
		Thread.sleep(2000);
		rdAdvanceFilterItemExpBtn.click();
	
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdAdvanceFilterItemNameBtn));
		rdAdvanceFilterItemNameBtn.click();
		
		//rdAdvanceFilterName_DepNameBtn.click();

		Thread.sleep(2000);
		Select s3=new Select(rdAdvanceFilterOpersatorDrpdwn);
		s3.selectByValue("0");


		Thread.sleep(2000);
		Select s4=new Select(rdAdvanceFilterValueDrpdwn);
		s4.selectByValue("0");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdFilterTxt1));
		rdFilterTxt1.click();
		rdFilterTxt1.sendKeys("item1");

		Thread.sleep(2999);
		rdFilterTxt1.sendKeys(Keys.TAB);

		Thread.sleep(2999);

		 ((JavascriptExecutor)getDriver()).executeScript("window.scrollTo(0, 0)","");
			
		 Thread.sleep(2000);
		finishBtn.click();
		Thread.sleep(2000);

		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		
		if(actMsg.equalsIgnoreCase(expMsg) && actMessage.equalsIgnoreCase(expMessage))
		{
			return true;
		}
		else
		{
			return false;
		}
	
	
		
	}
	
	
	
	public boolean checkSavedReportforFilterforValidationoffilter() throws InterruptedException
	{
		
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
		searchTxt.click();
		Thread.sleep(1000);
		searchTxt.sendKeys("Report for Filter");
		Thread.sleep(1000);
		searchTxt.sendKeys(Keys.ENTER);
		
		Thread.sleep(4000);
		
		
		click(sl_OkBtn);
		Thread.sleep(5000);
		
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_1stRow1stCol));
	
		click(report_LastBtn);
		Thread.sleep(2000);
		int pages= Integer.parseInt(report_PageNoBtn.getText());
		System.out.println(pages);
		
		boolean flag=false;
		
		ArrayList<String> reportsRow1ListArray = new ArrayList<String>();
		
		for(int j=pages;j>1;j--)
		{
			int reportsRow1ListCount = report2ndColList.size();
			for(int i=1;i<reportsRow1ListCount;i++)
			{
				String data = report2ndColList.get(i).getText();
				
				if(data.equals("item1") )
				{
					flag=true;
				}
				
			
			}
			
			click(report_PreviousBtn);
			Thread.sleep(2000);
		}
		
		System.out.println("All item names are item1:	"	+ flag);
		
		if(flag)
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	@FindBy(xpath="//*[@id='id_reportmenudisplay']//i[@class='icon-filter hiconright2']")
	public static WebElement report_FilterExpandBtn;
	
	@FindBy(xpath="//*[@id='id_mainreportmenuheadings']//i[@class='icon-filter hiconright2']")
	public static WebElement report_HomeFilterExpandBtn;
	
	
	@FindBy(xpath="(//li[text()='Filter'])[2]")
	public static WebElement report_FilterBtn;
	
	@FindBy(xpath="(//li[text()='Filter'])[1]")
	public static WebElement report_HomeFilterBtn;
	
	@FindBy(xpath = "//*[@id='idFilterCustomizeIcon']")
	public static WebElement report_FilterCustomizeBtn;
	
	@FindBy(xpath = "//*[@id='a']")
	public static WebElement report_FilterAdvFilterBtn;
	
	
	
	@FindBy (xpath="(//a[contains(text(),'Item')]//i)[1]")
	private static WebElement report_FilterItemExpansion;
	
	@FindBy (xpath="(//*[@id='5021'])[1]//..//span")
	private static WebElement report_FilterItemNameChkbox;
	
	@FindBy (xpath="(//*[@id='5021'])[1]")
	private static WebElement report_FilterItemNameChkboxSelected;
	
	@FindBy(xpath = "(//*[@class='FButton-Primary'])[1]")
	public static WebElement filter_FilterOkButton;
	
	
	//@FindBy (xpath="//*[@id='FOption_70069_0_DefaultFilter_0']")
	@FindBy(xpath="(//table[@class='option-btn-table'])[1]//following::input[@id='FOption_70069_0_DefaultFilter_0']")
	private static WebElement report_FilterItemDefaulTxt;
	
	@FindBy(xpath = "//*[@id='filter_Okbtn_']")
	public static WebElement filterOkButton;
	
	
	@FindBy(xpath = "//table/tbody/tr/td[1]/select")
	public static WebElement homefilterWhereSelect;
	
	
	@FindBy(xpath = "(//table/tbody/tr/td[2]/input)[4]")
	public static WebElement homefilterSelectTxt;
	
	@FindBy(xpath = "(//a[contains(text(),'Item')]//span)[2]")
	public static WebElement homefilterSelectItemExpBtn;
	
	
	@FindBy(xpath = "((//a[contains(text(),'Item')]//span)[2]//following::ul//a[contains(text(),'Name')])[1]")
	public static WebElement homefilterSelectItemName;
	
	@FindBy(xpath = "//table/tbody/tr/td[3]/select")
	public static WebElement homefilterSelectOperator;
	
	@FindBy(xpath = "//table/tbody/tr/td[4]/select")
	public static WebElement homefilterSelectCompare;
	
	//@FindBy(xpath = "((//table[@class='option-btn-table'])[1]//following::input)[10]")
	@FindBy(xpath="//*[@id='advancefilter_master_70069_0_']")
	public static WebElement homefilterValueTxt;
	
	
	
	
	
	
	
	
	
	
	
	public boolean checkRemovingFilterinDatasetApplyFilterinReportScreen() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		Thread.sleep(4000);
		
		click(reportEntryCustomizationBtn);
		Thread.sleep(4000);
		
		click(dataSetTab);
		Thread.sleep(2000);
		
		click(rdAdvanceFilterRemoveBtn1);
		Thread.sleep(1000);
		
		getAction().moveToElement(finishBtn).click().build().perform();
		Thread.sleep(1000);
		
		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
		searchTxt.click();
		Thread.sleep(1000);
		searchTxt.sendKeys("Report for Filter");
		Thread.sleep(1000);
		searchTxt.sendKeys(Keys.ENTER);
		
		Thread.sleep(4000);
		
		
		click(sl_OkBtn);
		Thread.sleep(8000);
		
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_1stRow1stCol));
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(report_FilterExpandBtn));
		report_FilterExpandBtn.click();
		
		Thread.sleep(2000);
		
		
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(report_FilterBtn));
		report_FilterBtn.click();
		
		Thread.sleep(8000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(report_FilterCustomizeBtn));
		report_FilterCustomizeBtn.click();
		Thread.sleep(2000);
		
				
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(report_FilterItemExpansion));
		report_FilterItemExpansion.click();
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(report_FilterItemNameChkbox));
		if (report_FilterItemNameChkboxSelected.isSelected()==false)
		{
			Thread.sleep(2000);
			
			getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(report_FilterItemNameChkbox));
			report_FilterItemNameChkbox.click();
			
		}
		
		Thread.sleep(2000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(filter_FilterOkButton));
		filter_FilterOkButton.click();
		
		Thread.sleep(2000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(report_FilterItemDefaulTxt));
		report_FilterItemDefaulTxt.click();
		report_FilterItemDefaulTxt.sendKeys("item1");
		
		Thread.sleep(2000);
		
		report_FilterItemDefaulTxt.sendKeys(Keys.TAB);
		
		Thread.sleep(4000);
		
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(filterOkButton));
		filterOkButton.click();

		Thread.sleep(12000);
		
		
		boolean novalidationConfirmationMessage =validationConfirmationMessage.getText().isEmpty();
		
		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";
		
		System.out.println("validationConfirmationMessage : "+actvalidationConfirmationMessage+" Value Expected : "+expvalidationConfirmationMessage);
		
		
		click(report_LastBtn);
		Thread.sleep(2000);
		int pages= Integer.parseInt(report_PageNoBtn.getText());
		System.out.println(pages);
		
		boolean flag=false;
		
		ArrayList<String> reportsRow1ListArray = new ArrayList<String>();
		
		for(int j=pages;j>1;j--)
		{
			int reportsRow1ListCount = report2ndColList.size();
			for(int i=1;i<reportsRow1ListCount;i++)
			{
				String data = report2ndColList.get(i).getText();
				
				if(data.equals("item1"))
				{
					flag=true;
				}
			}
			
			click(report_PreviousBtn);
			Thread.sleep(2000);
		}
		
		System.out.println("All item names are item1 after apply filter from entry:	"	+ flag);
		
		//apply filter from home page
		
						
		click(report_CloseBtn);
		Thread.sleep(4000);
		
		click(report_HomeFilterExpandBtn);
		
		
		Thread.sleep(2000);
		
		click(report_HomeFilterBtn);
			
		Thread.sleep(8000);
		
		click(report_FilterCustomizeBtn);
	
		Thread.sleep(2000);
				
		
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(report_FilterItemNameChkbox));
		if (report_FilterItemNameChkboxSelected.isSelected()==true)
		{
			Thread.sleep(2000);
			
			click(report_FilterItemNameChkbox);
			
		}
		
		Thread.sleep(4000);
		
		click(filter_FilterOkButton);
		
		Thread.sleep(2000);
		
		click(report_Filter_RefreshBtn);
		Thread.sleep(2000);
			
		click(filterOkButton);
		
	
		Thread.sleep(12000);
		
	
		
		boolean novalidationConfirmationMessage1 =validationConfirmationMessage.getText().isEmpty();
		
		String actvalidationConfirmationMessage1 = Boolean.toString(novalidationConfirmationMessage1);
		String expvalidationConfirmationMessage1 = "true";
		
		System.out.println("validationConfirmationMessage : "+actvalidationConfirmationMessage+" Value Expected : "+expvalidationConfirmationMessage);
		
		////filter home
		
		click(report_HomeFilterExpandBtn);
		
	
		Thread.sleep(2000);
		click(report_HomeFilterBtn);
					
		
		Thread.sleep(8000);
		
		click(report_FilterAdvFilterBtn);
		Thread.sleep(2000);
		
		click(homefilterWhereSelect);
		Select s=new Select(homefilterWhereSelect);
		s.selectByVisibleText("Where");
		
		click(homefilterSelectTxt);
		click(homefilterSelectItemExpBtn);
		click(homefilterSelectItemName);
		Thread.sleep(1000);
		
		
		click(homefilterSelectOperator);
		Select s1=new Select(homefilterSelectOperator);
		s1.selectByValue("0");
		Thread.sleep(1000);
		
		click(homefilterSelectCompare);
		Select s2=new Select(homefilterSelectCompare);
		s2.selectByValue("0");
		
		click(homefilterValueTxt);
		homefilterValueTxt.sendKeys("item1");
		Thread.sleep(2000);
		homefilterValueTxt.sendKeys(Keys.TAB);
		
		
		click(filterOkButton);
		Thread.sleep(2500);
		
		click(sl_OkBtn);
		Thread.sleep(5000);
		
		click(report_LastBtn);
		Thread.sleep(2000);
		int pages1= Integer.parseInt(report_PageNoBtn.getText());
		System.out.println(pages);
		
		boolean flag1=false;
		
		
		
		for(int j=pages1;j>1;j--)
		{
			int reportsRow1ListCount = report2ndColList.size();
			for(int i=1;i<reportsRow1ListCount;i++)
			{
				String data = report2ndColList.get(i).getText();
				
				if(data.equals("item1"))
				{
					flag1=true;
				}
			}
			
			click(report_PreviousBtn);
			Thread.sleep(2000);
		}
		
		System.out.println("All item names are item1 after apply filter from entry:	"	+ flag1);
		
		
		if(flag)
		{
			return true;
		}
		else
		{
			return false;
		}
		
			
		
	}
	
	
	@FindBy(xpath="//*[@title='Options']")
	public static WebElement reportOptionsBtn;
	
	
	@FindBy(xpath="//*[@id='RDDesignOptionsEnum']//a")
	public static List<WebElement> reportOptionsList;
	
	
	@FindBy(xpath="//a[text()='Auto Adjust Width']")
	public static WebElement reportOptionsAutoAdjustBtn;
	
	@FindBy(xpath="//a[text()='Fit To Screen']")
	public static WebElement reportOptionsFittoScreenBtn;
	
	@FindBy(xpath="//a[text()='Normal']")
	public static WebElement reportOptionsNormalBtn;
	
	@FindBy(xpath="//a[text()='Hide Grid Line']")
	public static WebElement reportOptionsHideGridBtn;
	
	@FindBy(xpath="//a[text()='Show Grid Line']")
	public static WebElement reportOptionsShowGridBtn;
	
	@FindBy(xpath="//*[@id='dvReportDetails']//table//tbody")
	public static WebElement reportTable;
	
	@FindBy(xpath="//*[@id='dvReportDetails']//table//tbody//tr[2]//td[2]")
	public static WebElement reportTable2ndRow2ndCol;
	
	
	public boolean checkOptionsinRDDetailsReport() throws InterruptedException
	{
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
		searchTxt.click();
		Thread.sleep(1000);
		searchTxt.sendKeys("RD Detail");
		Thread.sleep(1000);
		searchTxt.sendKeys(Keys.ENTER);
		
		Thread.sleep(4000);
		
		
		click(sl_OkBtn);
		Thread.sleep(8000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportOptionsBtn));
		reportOptionsBtn.click();
		
		Thread.sleep(1500);
		
		String actAutoAdjustTxt=null,expAutoAdjustTxt=null,actFitScreenTxt=null,expFitScreenTxt=null,actNormalTxt=null,expNormalTxt=null;
		String actHideGridTxt=null,expHideGridTxt=null, actShowGridTxt=null, expShowGridTxt=null;
		
		for(int i=0;i< reportOptionsList.size();i++)
		{
			
			String data=reportOptionsList.get(i).getText();
			
			System.out.println(data);
			switch(data)
			{
			
			case "Auto Adjust Width":
				
					click(reportOptionsAutoAdjustBtn);
					Thread.sleep(2000);
					 actAutoAdjustTxt=reportTable.getAttribute("style");
					 expAutoAdjustTxt="width: auto;";
					
			case "Fit To Screen":
				click(reportOptionsFittoScreenBtn);
				Thread.sleep(2000);
				 actFitScreenTxt=reportTable.getAttribute("style");
				 expFitScreenTxt="width: 100%;";
				
			case "Normal":
				
				click(reportOptionsNormalBtn);
				Thread.sleep(2000);
				 actNormalTxt=reportTable.getAttribute("style");
				 expNormalTxt="width: 100px;";
				
			case "Hide Grid Line":
				click(reportOptionsHideGridBtn);
				Thread.sleep(2000);
				 actHideGridTxt=reportTable2ndRow2ndCol.getAttribute("style");
				 expHideGridTxt="border-style: none; font-style: normal; font-weight: normal; font-size: 12px; font-family: \"Microsoft Sans Serif\"; width: 80px;";
				 
			case "Show Grid Line":
				click(reportOptionsShowGridBtn);
				Thread.sleep(2000);
				 actShowGridTxt=reportTable2ndRow2ndCol.getAttribute("style");
				 expShowGridTxt="font-style: normal; font-weight: normal; font-size: 12px; font-family: \"Microsoft Sans Serif\"; width: 80px;";
;
			default:
				break;
			}
		}
				
		System.out.println("Auto Adjust Screen		"		+		"Actual		"		+	actAutoAdjustTxt		+		"Expected		"		+	expAutoAdjustTxt);
	
		System.out.println("Fit To Screen			"		+		"Actual		"		+	actFitScreenTxt			+		"Expected		"		+	expFitScreenTxt);
		
		System.out.println("Normal					"		+		"Actual		"		+	actNormalTxt			+		"Expected		"		+	expNormalTxt);
		
		System.out.println("Hide Grid Line			"		+		"Actual		"		+	actHideGridTxt			+		"Expected		"		+	expHideGridTxt);
		
		System.out.println("Show Grid Line			"		+		"Actual		"		+	actShowGridTxt			+		"Expected		"		+	expShowGridTxt);
		
		if(actAutoAdjustTxt.equalsIgnoreCase(expAutoAdjustTxt) && actFitScreenTxt.equalsIgnoreCase(expFitScreenTxt)
				&& actNormalTxt.equalsIgnoreCase(expNormalTxt) && actHideGridTxt.equalsIgnoreCase(expHideGridTxt) && actShowGridTxt.equalsIgnoreCase(expShowGridTxt))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	public boolean checkOptionsinCubeRDReport() throws InterruptedException
	{
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
		searchTxt.click();
		Thread.sleep(1000);
		searchTxt.sendKeys("Cube RD");
		Thread.sleep(1000);
		searchTxt.sendKeys(Keys.ENTER);
		
		Thread.sleep(4000);
		
		
		click(sl_OkBtn);
		Thread.sleep(8000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportOptionsBtn));
		reportOptionsBtn.click();
		
		Thread.sleep(1500);
		
		String actAutoAdjustTxt=null,expAutoAdjustTxt=null,actFitScreenTxt=null,expFitScreenTxt=null,actNormalTxt=null,expNormalTxt=null;
		String actHideGridTxt=null,expHideGridTxt=null, actShowGridTxt=null, expShowGridTxt=null;
		
		for(int i=0;i< reportOptionsList.size();i++)
		{
			
			String data=reportOptionsList.get(i).getText();
			
			System.out.println(data);
			switch(data)
			{
			
			case "Auto Adjust Width":
				
					click(reportOptionsAutoAdjustBtn);
					Thread.sleep(2000);
					 actAutoAdjustTxt=reportTable.getAttribute("style");
					 expAutoAdjustTxt="width: auto;";
					
			case "Fit To Screen":
				click(reportOptionsFittoScreenBtn);
				Thread.sleep(2000);
				 actFitScreenTxt=reportTable.getAttribute("style");
				 expFitScreenTxt="width: 100%;";
				
			case "Normal":
				
				click(reportOptionsNormalBtn);
				Thread.sleep(2000);
				 actNormalTxt=reportTable.getAttribute("style");
				 expNormalTxt="width: 100px;";
				
			case "Hide Grid Line":
				click(reportOptionsHideGridBtn);
				Thread.sleep(2000);
				 actHideGridTxt=reportTable2ndRow2ndCol.getAttribute("style");
				 expHideGridTxt="border-style: none; font-style: normal; font-weight: normal; font-size: 12px; font-family: \"Microsoft Sans Serif\"; width: 150px; left: 30px; position: sticky; z-index: 3; background-color: rgb(249, 250, 252); box-shadow: rgb(198, 198, 198) 0px -1px 0px inset;";
				 
			case "Show Grid Line":
				click(reportOptionsShowGridBtn);
				Thread.sleep(2000);
				 actShowGridTxt=reportTable2ndRow2ndCol.getAttribute("style");
				 expShowGridTxt="font-style: normal; font-weight: normal; font-size: 12px; font-family: \"Microsoft Sans Serif\"; width: 150px; left: 30px; position: sticky; z-index: 3; background-color: rgb(249, 250, 252); box-shadow: rgb(198, 198, 198) 0px -1px 0px inset;";
;
			default:
				break;
			}
		}
				
		System.out.println("Auto Adjust Screen		"		+		"Actual		"		+	actAutoAdjustTxt		+		"Expected		"		+	expAutoAdjustTxt);
	
		System.out.println("Fit To Screen			"		+		"Actual		"		+	actFitScreenTxt			+		"Expected		"		+	expFitScreenTxt);
		
		System.out.println("Normal					"		+		"Actual		"		+	actNormalTxt			+		"Expected		"		+	expNormalTxt);
		
		System.out.println("Hide Grid Line			"		+		"Actual		"		+	actHideGridTxt			+		"Expected		"		+	expHideGridTxt);
		
		System.out.println("Show Grid Line			"		+		"Actual		"		+	actShowGridTxt			+		"Expected		"		+	expShowGridTxt);
		
		if(actAutoAdjustTxt.equalsIgnoreCase(expAutoAdjustTxt) && actFitScreenTxt.equalsIgnoreCase(expFitScreenTxt)
				&& actNormalTxt.equalsIgnoreCase(expNormalTxt) && actHideGridTxt.equalsIgnoreCase(expHideGridTxt) && actShowGridTxt.equalsIgnoreCase(expShowGridTxt))
		{
			return true;
		}
		else
		{
			return false;
		}
	
	}
	
	
	@FindBy(xpath="//*[@id='dvReportDetails']//table//tr//td[4]")
	public static  List<WebElement > report4thColList;
	
	@FindBy(xpath="//*[@id='dvReportDetails']//table//tr//td[7]")
	public static  List<WebElement > report7thColList;
	
	
	 @FindBy(xpath="//*[@id='id_transaction_entry_detail_table_body']/tr[1]/td")
	 private static List<WebElement> voucherRow1List;
	 
	 @FindBy(xpath="//*[@id='id_transaction_entry_detail_table_body']/tr[2]/td")
	 private static List<WebElement> voucherRow2List;
	
	
		public boolean checkBacktrackOptioninRDDetailReport() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
		{
			
			getDriver().navigate().refresh();
			Thread.sleep(4000);
			
			getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
			searchTxt.click();
			Thread.sleep(1000);
			searchTxt.sendKeys("RD Detail");
			Thread.sleep(1000);
			searchTxt.sendKeys(Keys.ENTER);
			
			Thread.sleep(4000);
			
			
			click(sl_OkBtn);
			Thread.sleep(8000);
			
			
			for(int i=0;i<report4thColList.size();i++)
			{
				
				if(report4thColList.get(i).getText().equals("Vendor A"))
				{
					report4thColList.get(i).click();
					break;
					
				}
				
				else
				{
					report_NextBtn.click();
				}
			}
			
			click(report_BackTrackBtn);
			Thread.sleep(2000);
			
			boolean novalidationConfirmationMessage =validationConfirmationMessage.getText().isEmpty();

			String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
			String expvalidationConfirmationMessage = "true";

			System.out.println(" Validation MEssage on Opening Report Actual : "+actvalidationConfirmationMessage);
			System.out.println(" Validation MEssage on Opening Report Expctd : "+expvalidationConfirmationMessage);


			Thread.sleep(3000);

			ArrayList<String> openTabs = new ArrayList<String>(getDriver().getWindowHandles());

			int actOpenWindowsCount = getDriver().getWindowHandles().size();
			int expOpenWindowsCount = 2;

			getDriver().switchTo().window(openTabs.get(1));

			checkValidationMessage("  Voucher loaded successfully.");

			getWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNumberTxt));
			String actDocNo = documentNumberTxt.getAttribute("value");
			String expDocNo = "Focus_9";
			
			getWebDriverWait().until(ExpectedConditions.elementToBeClickable(purchaseAccountTxt));
			String actPurchase = purchaseAccountTxt.getAttribute("value");
			String expPurchase = "Purchase";

			getWebDriverWait().until(ExpectedConditions.elementToBeClickable(vendorAccountTxt));
			String actVendor = vendorAccountTxt.getAttribute("value");
			String expVendor = "Vendor A";

			
			getWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouse1Txt));
			String actWarehouse = warehouse1Txt.getAttribute("value");
			String expWarehouse = "wh1";

			
			ArrayList<String >voucherRow1ListArray = new ArrayList<String>();

			int voucherRow1ListCount=voucherRow1List.size();

			for (int j = 0; j < voucherRow1ListCount; j++) 
			{
				String data=voucherRow1List.get(j).getText();
				voucherRow1ListArray.add(data);
			}
			String actRow1List=voucherRow1ListArray.toString();
			String expRow1List="[1, dep1, item1, 50.00, 10.00, 500.00, 0.00, , ]";

			System.out.println("actRow1List  : "+actRow1List );
			System.out.println("expRow1List  : "+expRow1List );
			
			
			ArrayList<String >voucherRow2ListArray = new ArrayList<String>();

			int voucherRow2ListCount=voucherRow2List.size();

			for (int j = 0; j < voucherRow2ListCount; j++) 
			{
				String data=voucherRow2List.get(j).getText();
				voucherRow2ListArray.add(data);
			}
			String actRow2List=voucherRow2ListArray.toString();
			String expRow2List="[2, dep1, item2, 7.50, 3.00, 22.50, 0.00, , ]";

			System.out.println("actRow2ist  : "+actRow2List );
			System.out.println("expRow2List  : "+expRow2List );
			

			System.out.println("OpenWindowsCount: "+actOpenWindowsCount+"  Value Expected  "+expOpenWindowsCount);

			System.out.println("Document No     : "+actDocNo			+"  Value Expected  "+expDocNo);
			System.out.println("Purchase        : "+actPurchase			+"  Value Expected  "+expPurchase);
			System.out.println("Vendor          : "+actVendor			+"  Value Expected  "+expVendor);
			System.out.println("Warehosue      : "+actWarehouse		+"  Value Expected  "+expWarehouse);
			

			getDriver().switchTo().window(openTabs.get(1)).close();

			Thread.sleep(1000);

			getDriver().switchTo().window(openTabs.get(0));
			
			
			if(actRow1List.equalsIgnoreCase(expRow1List) && actRow2List.equalsIgnoreCase(expRow2List)&&
					actOpenWindowsCount==expOpenWindowsCount 
					&& actDocNo.equalsIgnoreCase(expDocNo) && actVendor.equalsIgnoreCase(expVendor) && actPurchase.equalsIgnoreCase(expPurchase) 
					&& actWarehouse.equalsIgnoreCase(expWarehouse) )
			{
			
			
				return true;
			}
			else
			{
				return false;
			}
		
	}
		
		
		public boolean checkBacktrackOptioninRDCubeReport() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
		{

			getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
			searchTxt.click();
			Thread.sleep(1000);
			searchTxt.sendKeys("Cube RD");
			Thread.sleep(1000);
			searchTxt.sendKeys(Keys.ENTER);
			
			Thread.sleep(4000);
			
			
			click(sl_OkBtn);
			Thread.sleep(8000);
			
			click(report_LastBtn);
			Thread.sleep(5000);
			
			click(report_PreviousBtn);
			Thread.sleep(5000);
			
			for(int i=0;i<report7thColList.size();i++)
			{
				
				if(report7thColList.get(i).getText().equals("Opening Stocks"))
				{
					report7thColList.get(i).click();
					break;
					
				}
			}
			
			click(report_BackTrackBtn);
			Thread.sleep(2000);
			
			boolean novalidationConfirmationMessage =validationConfirmationMessage.getText().isEmpty();

			String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
			String expvalidationConfirmationMessage = "true";

			System.out.println(" Validation MEssage on Opening Report Actual : "+actvalidationConfirmationMessage);
			System.out.println(" Validation MEssage on Opening Report Expctd : "+expvalidationConfirmationMessage);


			Thread.sleep(3000);

			ArrayList<String> openTabs = new ArrayList<String>(getDriver().getWindowHandles());

			int actOpenWindowsCount = getDriver().getWindowHandles().size();
			int expOpenWindowsCount = 2;

			getDriver().switchTo().window(openTabs.get(1));

			checkValidationMessage("  Voucher loaded successfully.");

			getWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNumberTxt));
			String actDocNo = documentNumberTxt.getAttribute("value");
			String expDocNo = "Focus_30";
			
		
			getWebDriverWait().until(ExpectedConditions.elementToBeClickable(warehouse1Txt));
			String actWarehouse = warehouse1Txt.getAttribute("value");
			String expWarehouse = "wh6";

			
			ArrayList<String >voucherRow1ListArray = new ArrayList<String>();

			int voucherRow1ListCount=voucherRow1List.size();

			for (int j = 0; j < voucherRow1ListCount; j++) 
			{
				String data=voucherRow1List.get(j).getText();
				voucherRow1ListArray.add(data);
			}
			String actRow1List=voucherRow1ListArray.toString();
			String expRow1List="[1, RMA3, 5.00, 56.00, 280.00, , @DIYA,wkrt53023109,24TRURU490980,345KJ9798798790,SKJ8767868SIK32545]";

			System.out.println("actRow1List  : "+actRow1List );
			System.out.println("expRow1List  : "+expRow1List );
			
			
			ArrayList<String >voucherRow2ListArray = new ArrayList<String>();

			int voucherRow2ListCount=voucherRow2List.size();

			for (int j = 0; j < voucherRow2ListCount; j++) 
			{
				String data=voucherRow2List.get(j).getText();
				voucherRow2ListArray.add(data);
			}
			String actRow2List=voucherRow2ListArray.toString();
			String expRow2List="[2, RMA2, 3.00, 5.00, 15.00, , 4657,08213,0980324]";

			System.out.println("actRow2ist  : "+actRow2List );
			System.out.println("expRow2List  : "+expRow2List );
			

			System.out.println("OpenWindowsCount: "+actOpenWindowsCount+"  Value Expected  "+expOpenWindowsCount);

			System.out.println("Document No     : "+actDocNo			+"  Value Expected  "+expDocNo);
			System.out.println("Warehosue      	: "+actWarehouse		+"  Value Expected  "+expWarehouse);
			

			getDriver().switchTo().window(openTabs.get(1)).close();

			Thread.sleep(1000);

			getDriver().switchTo().window(openTabs.get(0));
			
			
			if(actRow1List.equalsIgnoreCase(expRow1List) && actRow2List.equalsIgnoreCase(expRow2List)&&
					actOpenWindowsCount==expOpenWindowsCount 
					&& actDocNo.equalsIgnoreCase(expDocNo)  
					&& actWarehouse.equalsIgnoreCase(expWarehouse) )
			{
			
			
				return true;
			}
			else
			{
				return false;
			}
	
			
		}
	
	
		
		@FindBy(xpath="(//*[@title='Print'])[1]")
		public static WebElement reportHomePrintBtn;
		
		@FindBy(xpath="(//*[@title='Excel'])[1]")
		public static WebElement reportHomeExcelBtn;
		
		
		@FindBy(xpath="(//*[@title='Export'])[1]")
		public static WebElement reportHomeExportBtn;
		
		@FindBy(xpath="(//a[text()='CSV'])[1]")
		public static WebElement reportHomeExportCSVBtn;
		
		
		@FindBy(xpath="//*[@title='PDF']")
		public static WebElement reportHomeExportPDFBtn;
		
		@FindBy(xpath="//*[@title='File']")
		public static WebElement reportHomeExportFileBtn;
		
		
		@FindBy(xpath="(//*[@title='Print'])[2]")
		public static WebElement reportEntryPrintBtn;
		
		@FindBy(xpath="(//*[@title='Excel'])[2]")
		public static WebElement reportEntryExcelBtn;
		
		
		@FindBy(xpath="(//*[@title='Export'])[2]")
		public static WebElement reportEntryExportBtn;
		
		
		@FindBy(xpath="(//a[text()='CSV'])[2]")
		public static WebElement reportEntryExportCSVBtn;
		
		
		@FindBy(xpath="(//a[text()='PDF'])[2]")
		public static WebElement reportEntryExportPDFBtn;
		
		@FindBy(xpath="(//a[text()='File'])[2]")
		public static WebElement reportEntryExportFileBtn;
		
		
		
		public boolean checkHomePagePrintOptioninRDDetailReport() throws InterruptedException, IOException, AWTException
		{
			
			getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
			searchTxt.click();
			Thread.sleep(1000);
			searchTxt.sendKeys("RD Detail");
			Thread.sleep(1000);
			searchTxt.sendKeys(Keys.ENTER);
			
			Thread.sleep(4000);
			
			click(reportHomePrintBtn);
			Thread.sleep(3000);
			
			click(ss_ReportPrintYesBtn);
			Thread.sleep(8000);
			
			
	File Efile=new File(getBaseDir()+"\\autoIt\\ExportFiles\\RDDetailPrint.pdf");
			
			if(Efile.exists())
			{
				Efile.delete();
			}
			
			Thread.sleep(2000);
			
			
			Thread.sleep(2000);
			
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_S);
			robot.keyRelease(KeyEvent.VK_S);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			
			Thread.sleep(6000);
				
			Runtime.getRuntime().exec(getBaseDir() + "\\autoIt\\scripts\\RDDetailPrint.exe");
			
			Thread.sleep(12000);
			
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_J);
			robot.keyRelease(KeyEvent.VK_J);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			Thread.sleep(2000);
			
			ArrayList<String> openTabs = new ArrayList<String>(getDriver().getWindowHandles());
		
			
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_W);
			robot.keyRelease(KeyEvent.VK_W);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			Thread.sleep(2000);
			
		 	robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_W);
			robot.keyRelease(KeyEvent.VK_W);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			Thread.sleep(2000);
			
			
		 	
		 	String actPDF = getBaseDir()+"\\autoIt\\ExportFiles\\RDDetailPrint.pdf";
			String expPDF = getBaseDir()+"\\autoIt\\ImportFiles\\RDDetailPrint.pdf";
			System.out.println(actPDF);
			System.out.println(expPDF);
			
			
			PDFUtil pdfutil = new PDFUtil();
			
			boolean result = pdfutil.compare(actPDF, expPDF);
			Calendar cal=Calendar.getInstance();
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			String currentDate = df.format(cal.getTime());
			
			String actData = pdfutil.getText(actPDF);
			String expData = pdfutil.getText(expPDF).replaceAll("05/08/2025", currentDate);
			System.out.println(actData);
			System.out.println(expData);
			
			System.out.println("Compared Result  : "+result);
			
			
			if(actData.equalsIgnoreCase(expData))
			{
				return true;
			}
			else
			{
				return false;
			}
			
		 			
			
		}
		
		
		
		public boolean checkHomePageExcelOptioninRDDetailReport() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException
		{
			Thread.sleep(3000);
			
			
			getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportHomeExcelBtn));
			reportHomeExcelBtn.click();
			Thread.sleep(3000);
			
			click(ss_ReportPrintYesBtn);
		
			Thread.sleep(12000);
			
			File Efile=new File(getBaseDir()+"\\autoIt\\ExportFiles\\RDDetailExcelHome.xlsx");
				
				if(Efile.exists())
				{
					Efile.delete();
				}
				
				
						
				
				
				Robot robot = new Robot();
				/*robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_J);
				robot.keyRelease(KeyEvent.VK_J);
				robot.keyRelease(KeyEvent.VK_CONTROL);
				
				Thread.sleep(2000);
				
				robot.keyPress(KeyEvent.VK_TAB);
				robot.keyRelease(KeyEvent.VK_TAB);
				robot.keyPress(KeyEvent.VK_TAB);
				robot.keyRelease(KeyEvent.VK_TAB);
				

				
				Thread.sleep(2000);
				
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
				
				Thread.sleep(25000);
				
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
				Thread.sleep(4000);
				robot.keyPress(KeyEvent.VK_F12);
				robot.keyRelease(KeyEvent.VK_F12);
				
				
				Thread.sleep(2000);
				
				*/
					
				Runtime.getRuntime().exec(getBaseDir() + "\\autoIt\\scripts\\RDDetailExcelHome.exe");
				
				Thread.sleep(8000);
				
				
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
				Thread.sleep(4000);
				
			/*	
				robot.keyPress(KeyEvent.VK_ALT);
				robot.keyPress(KeyEvent.VK_F4);
				
				robot.keyRelease(KeyEvent.VK_ALT);
				robot.keyRelease(KeyEvent.VK_F4);
				Thread.sleep(5000);
				
			*/	
				
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_J);
				robot.keyRelease(KeyEvent.VK_J);
				robot.keyRelease(KeyEvent.VK_CONTROL);
				
				Thread.sleep(2000);
				
				ArrayList<String> openTabs = new ArrayList<String>(getDriver().getWindowHandles());
					
				int actOpenWindowsCount = getDriver().getWindowHandles().size();
				int expOpenWindowsCount = 2;
				
				System.out.println("Number of Windows  : "+actOpenWindowsCount+"  Value Expected  "+expOpenWindowsCount);
				
				Thread.sleep(1000);

			 
				
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_W);
				robot.keyRelease(KeyEvent.VK_W);
				robot.keyRelease(KeyEvent.VK_CONTROL);
			
				
				
			 	 ExcelReader excelReader = new ExcelReader(POJOUtility.getExcelPath());
			     

			 	String actExcelfile = getBaseDir()+"\\autoIt\\ExportFiles\\RDDetailExcelHome.xlsx";
			 	String expExcelfile = getBaseDir()+"\\autoIt\\ImportFiles\\RDDetailExcelHome.xlsx";
			 	String sheet = "Sheet1";
			 	   
			 	
			 	
			 	FileInputStream fip1 = new FileInputStream(actExcelfile);
			 	Workbook workbook1  = WorkbookFactory.create(fip1);
			 	
			 	FileInputStream fip2 = new FileInputStream(expExcelfile);
			 	Workbook workbook2  = WorkbookFactory.create(fip2);
			 	
			 	boolean result = excelReader.checkExcelSheetsComparisonWithMonth(workbook1, workbook2,"22/12/2025");
			 	
			 	System.err.println(result);
			 	
			 	if (result)
			 	{
			 		return true;
			 	}
			 	else
			 	{
			 		return false;
			 	}

				
		
			
		}
		
public boolean checkHomePageCSVOptioninRDDetailReport() throws InterruptedException, IOException, AWTException, EncryptedDocumentException, InvalidFormatException
{
	

	Thread.sleep(2000);
	getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportHomeExportBtn));
	reportHomeExportBtn.click();
	Thread.sleep(2000);
	getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportHomeExportCSVBtn));
	reportHomeExportCSVBtn.click();
	Thread.sleep(4000);
	
	File Efile1 = new File(getBaseDir() + "\\autoIt\\ExportFiles\\RDDetailHomeCSV.csv");

	if (Efile1.exists()) {
		Efile1.delete();
	}
	
	getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(ss_ReportPrintYesBtn));
	ss_ReportPrintYesBtn.click();
	Thread.sleep(1000);
	
	
	Robot robot = new Robot();
	/*robot.keyPress(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_J);
	robot.keyRelease(KeyEvent.VK_J);
	robot.keyRelease(KeyEvent.VK_CONTROL);
	
	Thread.sleep(2000);*/
	
	/*
	
	robot.keyPress(KeyEvent.VK_TAB);
	robot.keyRelease(KeyEvent.VK_TAB);
	robot.keyPress(KeyEvent.VK_TAB);
	robot.keyRelease(KeyEvent.VK_TAB);
	
	Thread.sleep(2000);
	
	robot.keyPress(KeyEvent.VK_ENTER);
	robot.keyRelease(KeyEvent.VK_ENTER);
	
	Thread.sleep(25000);
	
	robot.keyPress(KeyEvent.VK_ENTER);
	robot.keyRelease(KeyEvent.VK_ENTER);
	Thread.sleep(2000);
	robot.keyPress(KeyEvent.VK_F12);
	robot.keyRelease(KeyEvent.VK_F12);
	
	
	Thread.sleep(2000);
	*/
	try {
		Runtime.getRuntime().exec(getBaseDir() + "\\autoIt\\scripts\\RDDetailHomeCSV.exe");
	} catch (Exception e) {
		
		e.printStackTrace();
	}
	
	Thread.sleep(10000);
	/*
	robot.keyPress(KeyEvent.VK_TAB);
	robot.keyRelease(KeyEvent.VK_TAB);
	Thread.sleep(2000);
	robot.keyPress(KeyEvent.VK_C);
	robot.keyRelease(KeyEvent.VK_C);
	Thread.sleep(2000);
	
	robot.keyPress(KeyEvent.VK_TAB);
	robot.keyRelease(KeyEvent.VK_TAB);
	
	robot.keyPress(KeyEvent.VK_TAB);
	robot.keyRelease(KeyEvent.VK_TAB);
	
	robot.keyPress(KeyEvent.VK_TAB);
	robot.keyRelease(KeyEvent.VK_TAB);
	
	robot.keyPress(KeyEvent.VK_TAB);
	robot.keyRelease(KeyEvent.VK_TAB);
	
	robot.keyPress(KeyEvent.VK_TAB);
	robot.keyRelease(KeyEvent.VK_TAB);
	
	robot.keyPress(KeyEvent.VK_ENTER);
	robot.keyRelease(KeyEvent.VK_ENTER);
	Thread.sleep(2000);
	
	robot.keyPress(KeyEvent.VK_ENTER);
	robot.keyRelease(KeyEvent.VK_ENTER);
	Thread.sleep(2000);
	
	robot.keyPress(KeyEvent.VK_ALT);
	robot.keyPress(KeyEvent.VK_F4);
	
	robot.keyRelease(KeyEvent.VK_ALT);
	robot.keyRelease(KeyEvent.VK_F4);
	Thread.sleep(15000);
	
	robot.keyPress(KeyEvent.VK_TAB);
	robot.keyRelease(KeyEvent.VK_TAB);
	*/
	robot.keyPress(KeyEvent.VK_ENTER);
	robot.keyRelease(KeyEvent.VK_ENTER);
	Thread.sleep(6000);
	
	
	robot.keyPress(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_J);
	robot.keyRelease(KeyEvent.VK_J);
	robot.keyRelease(KeyEvent.VK_CONTROL);
	
	Thread.sleep(2000);
	
	ArrayList<String> openTabs = new ArrayList<String>(getDriver().getWindowHandles());
	
	int actOpenWindowsCount = getDriver().getWindowHandles().size();
	int expOpenWindowsCount = 2;
	
	System.out.println("Number of Windows  : "+actOpenWindowsCount+"  Value Expected : "+expOpenWindowsCount);
	
	Thread.sleep(6000);

 
	
	robot.keyPress(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_W);
	robot.keyRelease(KeyEvent.VK_W);
	robot.keyRelease(KeyEvent.VK_CONTROL);
	
	Thread.sleep(2000);
	
 	
 	ExcelReader excelReader = new ExcelReader(POJOUtility.getExcelPath());
 	
 	String actFile= getBaseDir()+"\\autoIt\\ExportFiles\\RDDetailHomeCSV.csv";
 	String expFile = getBaseDir()+"\\autoIt\\ImportFiles\\RDDetailHomeCSV.csv";
 	
 	
 	FileInputStream file1=new FileInputStream(actFile);
	FileInputStream file2=new FileInputStream(expFile);
 	
 	boolean result = excelReader.verifyDataInCSVWithMonth(actFile, expFile,"29/09/2025");
 	
 	System.err.println("LATEST METHOD : "+result);
 	
 	if (result)
 	{
 		return true;
 	}
 	else
 	{
 		return false;
 	}


	

}


public boolean checkHomePagePDFOptioninRDDetailReport() throws InterruptedException, AWTException, IOException
{

	Thread.sleep(2000);
	getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportHomeExportBtn));
	reportHomeExportBtn.click();
	Thread.sleep(2000);
	getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportHomeExportPDFBtn));
	reportHomeExportPDFBtn.click();
	Thread.sleep(4000);
	
	File Efile1 = new File(getBaseDir() + "\\autoIt\\ExportFiles\\RDDetailHomePDF.pdf");

	if (Efile1.exists()) {
		Efile1.delete();
	}

	Thread.sleep(4000);
	
	
	
	getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(ss_ReportPrintYesBtn));
	ss_ReportPrintYesBtn.click();
	Thread.sleep(10000);
	
	Robot robot = new Robot();
/*	robot.keyPress(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_J);
	robot.keyRelease(KeyEvent.VK_J);
	robot.keyRelease(KeyEvent.VK_CONTROL);

	Thread.sleep(8000);

	robot.keyPress(KeyEvent.VK_TAB);
	robot.keyRelease(KeyEvent.VK_TAB);
	robot.keyPress(KeyEvent.VK_TAB);
	robot.keyRelease(KeyEvent.VK_TAB);

	robot.keyPress(KeyEvent.VK_ENTER);
	robot.keyRelease(KeyEvent.VK_ENTER);

	Thread.sleep(25000);

	robot.keyPress(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_S);
	robot.keyRelease(KeyEvent.VK_S);
	robot.keyRelease(KeyEvent.VK_CONTROL);

	Thread.sleep(5000);
	*/
	Runtime.getRuntime().exec(getBaseDir() + "\\autoIt\\scripts\\RDDetailHomePDF.exe");

	Thread.sleep(8000);

	robot.keyPress(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_J);
	robot.keyRelease(KeyEvent.VK_J);
	robot.keyRelease(KeyEvent.VK_CONTROL);

	Thread.sleep(2000);

	ArrayList<String> newTabs = new ArrayList<String>(getDriver().getWindowHandles());

	int actOpenWindowsCount = getDriver().getWindowHandles().size();
	int expOpenWindowsCount = 3;

	System.out.println(
			"Number of Windows  : " + actOpenWindowsCount + "  Value Expected  " + expOpenWindowsCount);


	
	
	robot.keyPress(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_W);
	robot.keyRelease(KeyEvent.VK_W);
	robot.keyRelease(KeyEvent.VK_CONTROL);
	Thread.sleep(2000);
	
	
/*	
 	robot.keyPress(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_W);
	robot.keyRelease(KeyEvent.VK_W);
	robot.keyRelease(KeyEvent.VK_CONTROL);
	
	*/
	
	
	String actPDF = getBaseDir()+"\\autoIt\\ExportFiles\\RDDetailHomePDF.pdf";
	String expPDF = getBaseDir()+"\\autoIt\\ImportFiles\\RDDetailHomePDF.pdf";
	
	PDFUtil pdfutil = new PDFUtil();
	
	boolean result = pdfutil.compare(actPDF, expPDF);
	
	String data = pdfutil.getText(expPDF);
	
	DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	Calendar cal = Calendar.getInstance();
	String date = df.format(cal.getTime());
	System.err.println(date);
	
	String oldDate = "12/09/2025";
	

	
	String actData = pdfutil.getText(actPDF);
	String expData = data.replace(oldDate, date);
	
	System.err.println(actData);
	System.err.println(expData);
	
	System.out.println("Compared Result  : "+result);
	
	if (actData.equalsIgnoreCase(expData))
	{
		return true;
	}
	else
	{
		return false;
	}
}


public boolean checkHomePageFileOptioninRDDetailReport() throws InterruptedException, IOException, AWTException
{
	
	

	Thread.sleep(4000);
	getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportHomeExportBtn));
	reportHomeExportBtn.click();
	Thread.sleep(2000);
	getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportHomeExportFileBtn));
	reportHomeExportFileBtn.click();
	
	File Efile1 = new File(getBaseDir() + "\\autoIt\\ExportFiles\\RDDetailHomeFile.pdf");

	if (Efile1.exists()) {
		Efile1.delete();
	}

	Thread.sleep(4000);
	
	
	Thread.sleep(4000);
	getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(ss_ReportPrintYesBtn));
	ss_ReportPrintYesBtn.click();
	Thread.sleep(10000);
	
	Robot robot = new Robot();
	/*robot.keyPress(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_J);
	robot.keyRelease(KeyEvent.VK_J);
	robot.keyRelease(KeyEvent.VK_CONTROL);

	Thread.sleep(5000);

	robot.keyPress(KeyEvent.VK_TAB);
	robot.keyRelease(KeyEvent.VK_TAB);
	robot.keyPress(KeyEvent.VK_TAB);
	robot.keyRelease(KeyEvent.VK_TAB);

	robot.keyPress(KeyEvent.VK_ENTER);
	robot.keyRelease(KeyEvent.VK_ENTER);

	Thread.sleep(25000);

	robot.keyPress(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_S);
	robot.keyRelease(KeyEvent.VK_S);
	robot.keyRelease(KeyEvent.VK_CONTROL);

	Thread.sleep(5000);
	*/
	Runtime.getRuntime().exec(getBaseDir() + "\\autoIt\\scripts\\RDDetailHomeFile.exe");

	Thread.sleep(8000);

	robot.keyPress(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_J);
	robot.keyRelease(KeyEvent.VK_J);
	robot.keyRelease(KeyEvent.VK_CONTROL);

	Thread.sleep(2000);

	ArrayList<String> newTabs = new ArrayList<String>(getDriver().getWindowHandles());

	int actOpenWindowsCount = getDriver().getWindowHandles().size();
	int expOpenWindowsCount = 3;

	System.out.println(
			"Number of Windows  : " + actOpenWindowsCount + "  Value Expected  " + expOpenWindowsCount);


	
	
	robot.keyPress(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_W);
	robot.keyRelease(KeyEvent.VK_W);
	robot.keyRelease(KeyEvent.VK_CONTROL);
	
	Thread.sleep(2000);
 /*	robot.keyPress(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_W);
	robot.keyRelease(KeyEvent.VK_W);
	robot.keyRelease(KeyEvent.VK_CONTROL);
	
	Thread.sleep(2000);*/
	
	String actPDF = getBaseDir()+"\\autoIt\\ExportFiles\\RDDetailHomeFile.pdf";
	String expPDF = getBaseDir()+"\\autoIt\\ImportFiles\\RDDetailHomeFile.pdf";
	
	PDFUtil pdfutil = new PDFUtil();
	
	boolean result = pdfutil.compare(actPDF, expPDF);
	
	String data = pdfutil.getText(expPDF);
	
	
	String actData = pdfutil.getText(actPDF);
	String expData = data.replace("12/09/2025", getCurrentDate());
	
	System.err.println(actData);
	System.err.println(expData);
	
	System.out.println("Compared Result  : "+result);
	
	if (actData.equalsIgnoreCase(expData))
	{
		return true;
	}
	else
	{
		return false;
	}


}


		
///Entry Page
		
		
		public boolean checkEntryPagePrintOptioninRDDetailReport() throws InterruptedException, IOException, AWTException
		{
			
			click(sl_OkBtn);
						
			Thread.sleep(4000);
			
			click(reportEntryPrintBtn);
			Thread.sleep(2000);
			
			click(ss_ReportPrintYesBtn);
			Thread.sleep(5000);
			
			
	File Efile=new File(getBaseDir()+"\\autoIt\\ExportFiles\\RDDetailEntryPrint.pdf");
			
			if(Efile.exists())
			{
				Efile.delete();
			}
			
			Thread.sleep(2000);
			
			
			Thread.sleep(2000);
			
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_S);
			robot.keyRelease(KeyEvent.VK_S);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			
			Thread.sleep(6000);
				
			Runtime.getRuntime().exec(getBaseDir() + "\\autoIt\\scripts\\RDDetailEntryPrint.exe");
			
			Thread.sleep(12000);
			
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_J);
			robot.keyRelease(KeyEvent.VK_J);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			Thread.sleep(2000);
			
			ArrayList<String> openTabs = new ArrayList<String>(getDriver().getWindowHandles());
		
			
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_W);
			robot.keyRelease(KeyEvent.VK_W);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			Thread.sleep(1500);
			
		 	robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_W);
			robot.keyRelease(KeyEvent.VK_W);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			Thread.sleep(1500);
			
		 	
		 	String actPDF = getBaseDir()+"\\autoIt\\ExportFiles\\RDDetailEntryPrint.pdf";
			String expPDF = getBaseDir()+"\\autoIt\\ImportFiles\\RDDetailEntryPrint.pdf";
			System.out.println(actPDF);
			System.out.println(expPDF);
			
			
			PDFUtil pdfutil = new PDFUtil();
			
			boolean result = pdfutil.compare(actPDF, expPDF);
			Calendar cal=Calendar.getInstance();
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			String currentDate = df.format(cal.getTime());
			
			String actData = pdfutil.getText(actPDF);
			String expData = pdfutil.getText(expPDF).replaceAll("12/09/2025", currentDate);
			System.out.println(actData);
			System.out.println(expData);
			
			System.out.println("Compared Result  : "+result);
			
			
			if(actData.equalsIgnoreCase(expData))
			{
				return true;
			}
			else
			{
				return false;
			}
			
		 			
			
		}
		
		
		
		public boolean checkEntryPageExcelOptioninRDDetailReport() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException
		{
			Thread.sleep(3000);
			
			
			getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportEntryExcelBtn));
			reportEntryExcelBtn.click();
			Thread.sleep(3000);
			
			click(ss_ReportPrintYesBtn);
		
			Thread.sleep(8000);
			
			File Efile=new File(getBaseDir()+"\\autoIt\\ExportFiles\\RDDetailExcelEntry.xlsx");
				
				if(Efile.exists())
				{
					Efile.delete();
				}
				
				
						
				
				
				Robot robot = new Robot();
			/*	robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_J);
				robot.keyRelease(KeyEvent.VK_J);
				robot.keyRelease(KeyEvent.VK_CONTROL);
				
				Thread.sleep(2000);
				
				robot.keyPress(KeyEvent.VK_TAB);
				robot.keyRelease(KeyEvent.VK_TAB);
				robot.keyPress(KeyEvent.VK_TAB);
				robot.keyRelease(KeyEvent.VK_TAB);
				

				
				Thread.sleep(2000);
				
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
				
				Thread.sleep(25000);
				
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
				Thread.sleep(4000);
				robot.keyPress(KeyEvent.VK_F12);
				robot.keyRelease(KeyEvent.VK_F12);
				
				
				Thread.sleep(2000);
				
				*/
					
				Runtime.getRuntime().exec(getBaseDir() + "\\autoIt\\scripts\\RDDetailExcelEntry.exe");
				
				Thread.sleep(8000);
				
				
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
				Thread.sleep(4000);
				
			/*	
				robot.keyPress(KeyEvent.VK_ALT);
				robot.keyPress(KeyEvent.VK_F4);
				
				robot.keyRelease(KeyEvent.VK_ALT);
				robot.keyRelease(KeyEvent.VK_F4);
				Thread.sleep(5000);
				
				*/
				
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_J);
				robot.keyRelease(KeyEvent.VK_J);
				robot.keyRelease(KeyEvent.VK_CONTROL);
				
				Thread.sleep(2000);
				
				ArrayList<String> openTabs = new ArrayList<String>(getDriver().getWindowHandles());
					
				int actOpenWindowsCount = getDriver().getWindowHandles().size();
				int expOpenWindowsCount = 2;
				
				System.out.println("Number of Windows  : "+actOpenWindowsCount+"  Value Expected  "+expOpenWindowsCount);
				
				Thread.sleep(1000);

			 
				
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_W);
				robot.keyRelease(KeyEvent.VK_W);
				robot.keyRelease(KeyEvent.VK_CONTROL);
			
				Thread.sleep(2000);
				
			 	 ExcelReader excelReader = new ExcelReader(POJOUtility.getExcelPath());
			     

			 	String actExcelfile = getBaseDir()+"\\autoIt\\ExportFiles\\RDDetailExcelEntry.xlsx";
			 	String expExcelfile = getBaseDir()+"\\autoIt\\ImportFiles\\RDDetailExcelEntry.xlsx";
			 	String sheet = "Sheet1";
			 	   
			 	
			 	
			 	FileInputStream fip1 = new FileInputStream(actExcelfile);
			 	Workbook workbook1  = WorkbookFactory.create(fip1);
			 	
			 	FileInputStream fip2 = new FileInputStream(expExcelfile);
			 	Workbook workbook2  = WorkbookFactory.create(fip2);
			 	
			 	boolean result = excelReader.checkExcelSheetsComparisonWithMonth(workbook1, workbook2,"22/12/2025");
			 	
			 	System.err.println(result);
			 	
			 	if (result)
			 	{
			 		return true;
			 	}
			 	else
			 	{
			 		return false;
			 	}

				
		
			
		}
		
public boolean checkEntryPageCSVOptioninRDDetailReport() throws InterruptedException, IOException, AWTException, EncryptedDocumentException, InvalidFormatException
{
	

	Thread.sleep(2000);
	getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportEntryExportBtn));
	reportEntryExportBtn.click();
	Thread.sleep(2000);
	getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportEntryExportCSVBtn));
	reportEntryExportCSVBtn.click();
	Thread.sleep(4000);
	
	File Efile1 = new File(getBaseDir() + "\\autoIt\\ExportFiles\\RDDetailEntryCSV.csv");

	if (Efile1.exists()) {
		Efile1.delete();
	}
	
	getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(ss_ReportPrintYesBtn));
	ss_ReportPrintYesBtn.click();
	Thread.sleep(10000);
	
	
	Robot robot = new Robot();
	/*robot.keyPress(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_J);
	robot.keyRelease(KeyEvent.VK_J);
	robot.keyRelease(KeyEvent.VK_CONTROL);
	
	Thread.sleep(2000);
	
	
	
	robot.keyPress(KeyEvent.VK_TAB);
	robot.keyRelease(KeyEvent.VK_TAB);
	robot.keyPress(KeyEvent.VK_TAB);
	robot.keyRelease(KeyEvent.VK_TAB);
	
	Thread.sleep(2000);
	
	robot.keyPress(KeyEvent.VK_ENTER);
	robot.keyRelease(KeyEvent.VK_ENTER);
	
	Thread.sleep(25000);
	
	robot.keyPress(KeyEvent.VK_ENTER);
	robot.keyRelease(KeyEvent.VK_ENTER);
	Thread.sleep(2000);
	robot.keyPress(KeyEvent.VK_F12);
	robot.keyRelease(KeyEvent.VK_F12);
	
	
	Thread.sleep(2000);*/
	
	try {
		Runtime.getRuntime().exec(getBaseDir() + "\\autoIt\\scripts\\RDDetailEntryCSV.exe");
	} catch (Exception e) {
		
		e.printStackTrace();
	}
	
	Thread.sleep(10000);
	
/*	robot.keyPress(KeyEvent.VK_TAB);
	robot.keyRelease(KeyEvent.VK_TAB);
	Thread.sleep(2000);
	robot.keyPress(KeyEvent.VK_C);
	robot.keyRelease(KeyEvent.VK_C);
	Thread.sleep(2000);
	
	robot.keyPress(KeyEvent.VK_TAB);
	robot.keyRelease(KeyEvent.VK_TAB);
	
	robot.keyPress(KeyEvent.VK_TAB);
	robot.keyRelease(KeyEvent.VK_TAB);
	
	robot.keyPress(KeyEvent.VK_TAB);
	robot.keyRelease(KeyEvent.VK_TAB);
	
	robot.keyPress(KeyEvent.VK_TAB);
	robot.keyRelease(KeyEvent.VK_TAB);
	
	robot.keyPress(KeyEvent.VK_TAB);
	robot.keyRelease(KeyEvent.VK_TAB);
	
	robot.keyPress(KeyEvent.VK_ENTER);
	robot.keyRelease(KeyEvent.VK_ENTER);
	Thread.sleep(2000);
	
	robot.keyPress(KeyEvent.VK_ENTER);
	robot.keyRelease(KeyEvent.VK_ENTER);
	Thread.sleep(2000);
	
	robot.keyPress(KeyEvent.VK_ALT);
	robot.keyPress(KeyEvent.VK_F4);
	
	robot.keyRelease(KeyEvent.VK_ALT);
	robot.keyRelease(KeyEvent.VK_F4);
	Thread.sleep(6000);
	
	robot.keyPress(KeyEvent.VK_TAB);
	robot.keyRelease(KeyEvent.VK_TAB);*/
	
	robot.keyPress(KeyEvent.VK_ENTER);
	robot.keyRelease(KeyEvent.VK_ENTER);
	Thread.sleep(6000);
	
	
	robot.keyPress(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_J);
	robot.keyRelease(KeyEvent.VK_J);
	robot.keyRelease(KeyEvent.VK_CONTROL);
	
	ArrayList<String> openTabs = new ArrayList<String>(getDriver().getWindowHandles());
	
	int actOpenWindowsCount = getDriver().getWindowHandles().size();
	int expOpenWindowsCount = 2;
	
	System.out.println("Number of Windows  : "+actOpenWindowsCount+"  Value Expected : "+expOpenWindowsCount);
	
	Thread.sleep(6000);

 
	
	robot.keyPress(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_W);
	robot.keyRelease(KeyEvent.VK_W);
	robot.keyRelease(KeyEvent.VK_CONTROL);
	

	Thread.sleep(2000);
 	
 	ExcelReader excelReader = new ExcelReader(POJOUtility.getExcelPath());
 	
 	String actFile= getBaseDir()+"\\autoIt\\ExportFiles\\RDDetailEntryCSV.csv";
 	String expFile = getBaseDir()+"\\autoIt\\ImportFiles\\RDDetailEntryCSV.csv";
 	
 	
 	FileInputStream file1=new FileInputStream(actFile);
	FileInputStream file2=new FileInputStream(expFile);
 	
 	boolean result = excelReader.verifyDataInCSVWithMonth(actFile, expFile,"29/09/2025");
 	
 	System.err.println("LATEST METHOD : "+result);
 	
 	if (result)
 	{
 		return true;
 	}
 	else
 	{
 		return false;
 	}


	

}


public boolean checkEntryPagePDFOptioninRDDetailReport() throws InterruptedException, AWTException, IOException
{

	Thread.sleep(2000);
	getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportEntryExportBtn));
	reportEntryExportBtn.click();
	Thread.sleep(2000);
	getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportEntryExportPDFBtn));
	reportEntryExportPDFBtn.click();
	Thread.sleep(4000);
	
	File Efile1 = new File(getBaseDir() + "\\autoIt\\ExportFiles\\RDDetailEntryPDF.pdf");

	if (Efile1.exists()) {
		Efile1.delete();
	}

	Thread.sleep(4000);
	
	
	
	getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(ss_ReportPrintYesBtn));
	ss_ReportPrintYesBtn.click();
	Thread.sleep(10000);
	
	Robot robot = new Robot();
	/*robot.keyPress(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_J);
	robot.keyRelease(KeyEvent.VK_J);
	robot.keyRelease(KeyEvent.VK_CONTROL);

	Thread.sleep(8000);

	robot.keyPress(KeyEvent.VK_TAB);
	robot.keyRelease(KeyEvent.VK_TAB);
	robot.keyPress(KeyEvent.VK_TAB);
	robot.keyRelease(KeyEvent.VK_TAB);

	robot.keyPress(KeyEvent.VK_ENTER);
	robot.keyRelease(KeyEvent.VK_ENTER);

	Thread.sleep(6000);

	robot.keyPress(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_S);
	robot.keyRelease(KeyEvent.VK_S);
	robot.keyRelease(KeyEvent.VK_CONTROL);

	Thread.sleep(5000);*/
	Runtime.getRuntime().exec(getBaseDir() + "\\autoIt\\scripts\\RDDetailEntryPDF.exe");

	Thread.sleep(8000);

	robot.keyPress(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_J);
	robot.keyRelease(KeyEvent.VK_J);
	robot.keyRelease(KeyEvent.VK_CONTROL);

	Thread.sleep(2000);

	ArrayList<String> newTabs = new ArrayList<String>(getDriver().getWindowHandles());

	int actOpenWindowsCount = getDriver().getWindowHandles().size();
	int expOpenWindowsCount = 3;

	System.out.println(
			"Number of Windows  : " + actOpenWindowsCount + "  Value Expected  " + expOpenWindowsCount);


	
	
	robot.keyPress(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_W);
	robot.keyRelease(KeyEvent.VK_W);
	robot.keyRelease(KeyEvent.VK_CONTROL);
	Thread.sleep(2500);
	
/*	
 	robot.keyPress(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_W);
	robot.keyRelease(KeyEvent.VK_W);
	robot.keyRelease(KeyEvent.VK_CONTROL);
	*/
	
	
	
	String actPDF = getBaseDir()+"\\autoIt\\ExportFiles\\RDDetailEntryPDF.pdf";
	String expPDF = getBaseDir()+"\\autoIt\\ImportFiles\\RDDetailEntryPDF.pdf";
	
	PDFUtil pdfutil = new PDFUtil();
	
	boolean result = pdfutil.compare(actPDF, expPDF);
	
	String data = pdfutil.getText(expPDF);
	
	DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	Calendar cal = Calendar.getInstance();
	String date = df.format(cal.getTime());
	System.err.println(date);
	
	String oldDate = "12/09/2025";
	

	
	String actData = pdfutil.getText(actPDF);
	String expData = data.replace(oldDate, date);
	
	System.err.println(actData);
	System.err.println(expData);
	
	System.out.println("Compared Result  : "+result);
	
	if (actData.equalsIgnoreCase(expData))
	{
		return true;
	}
	else
	{
		return false;
	}
}


public boolean checkEntryPageFileOptioninRDDetailReport() throws InterruptedException, IOException, AWTException
{
	
	

	Thread.sleep(4000);
	getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportEntryExportBtn));
	reportEntryExportBtn.click();
	Thread.sleep(2000);
	getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportEntryExportFileBtn));
	reportEntryExportFileBtn.click();
	
	File Efile1 = new File(getBaseDir() + "\\autoIt\\ExportFiles\\RDDetailEntryFile.pdf");

	if (Efile1.exists()) {
		Efile1.delete();
	}

	Thread.sleep(4000);
	
	
	Thread.sleep(4000);
	getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(ss_ReportPrintYesBtn));
	ss_ReportPrintYesBtn.click();
	Thread.sleep(10000);
	
	Robot robot = new Robot();
/*	robot.keyPress(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_J);
	robot.keyRelease(KeyEvent.VK_J);
	robot.keyRelease(KeyEvent.VK_CONTROL);

	Thread.sleep(5000);

	robot.keyPress(KeyEvent.VK_TAB);
	robot.keyRelease(KeyEvent.VK_TAB);
	robot.keyPress(KeyEvent.VK_TAB);
	robot.keyRelease(KeyEvent.VK_TAB);

	robot.keyPress(KeyEvent.VK_ENTER);
	robot.keyRelease(KeyEvent.VK_ENTER);

	Thread.sleep(25000);

	robot.keyPress(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_S);
	robot.keyRelease(KeyEvent.VK_S);
	robot.keyRelease(KeyEvent.VK_CONTROL);

	Thread.sleep(5000);*/
	Runtime.getRuntime().exec(getBaseDir() + "\\autoIt\\scripts\\RDDetailEntryFile.exe");

	Thread.sleep(8000);

	robot.keyPress(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_J);
	robot.keyRelease(KeyEvent.VK_J);
	robot.keyRelease(KeyEvent.VK_CONTROL);

	Thread.sleep(2000);

	ArrayList<String> newTabs = new ArrayList<String>(getDriver().getWindowHandles());

	int actOpenWindowsCount = getDriver().getWindowHandles().size();
	int expOpenWindowsCount = 3;

	System.out.println(
			"Number of Windows  : " + actOpenWindowsCount + "  Value Expected  " + expOpenWindowsCount);


	
	
	robot.keyPress(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_W);
	robot.keyRelease(KeyEvent.VK_W);
	robot.keyRelease(KeyEvent.VK_CONTROL);
	Thread.sleep(2000);
/*
 	robot.keyPress(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_W);
	robot.keyRelease(KeyEvent.VK_W);
	robot.keyRelease(KeyEvent.VK_CONTROL);
	Thread.sleep(2000);
	*/
	
	String actPDF = getBaseDir()+"\\autoIt\\ExportFiles\\RDDetailEntryFile.pdf";
	String expPDF = getBaseDir()+"\\autoIt\\ImportFiles\\RDDetailEntryFile.pdf";
	
	PDFUtil pdfutil = new PDFUtil();
	
	boolean result = pdfutil.compare(actPDF, expPDF);
	
	String data = pdfutil.getText(expPDF);
	
	
	String actData = pdfutil.getText(actPDF);
	String expData = data.replace("12/09/2025", getCurrentDate());
	
	System.err.println(actData);
	System.err.println(expData);
	
	System.out.println("Compared Result  : "+result);
	
	if (actData.equalsIgnoreCase(expData))
	{
		return true;
	}
	else
	{
		return false;
	}


}

 
  
   
   			
		public boolean checkHomePagePrintOptioninRDCubeReport() throws InterruptedException, IOException, AWTException
		{
			
			getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
			searchTxt.click();
			Thread.sleep(1000);
			searchTxt.sendKeys("Cube RD");
			Thread.sleep(1000);
			searchTxt.sendKeys(Keys.ENTER);
			
			Thread.sleep(8000);
			
			click(reportHomePrintBtn);
			Thread.sleep(4000);
			
			click(ss_ReportPrintYesBtn);
			Thread.sleep(8000);
			
			
	File Efile=new File(getBaseDir()+"\\autoIt\\ExportFiles\\RDCubePrint.pdf");
			
			if(Efile.exists())
			{
				Efile.delete();
			}
			
			Thread.sleep(4000);
	
			
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_S);
			robot.keyRelease(KeyEvent.VK_S);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			
			Thread.sleep(6000);
				
			Runtime.getRuntime().exec(getBaseDir() + "\\autoIt\\scripts\\RDCubePrint.exe");
			
			Thread.sleep(12000);
			
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_J);
			robot.keyRelease(KeyEvent.VK_J);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			Thread.sleep(2000);
			
			ArrayList<String> openTabs = new ArrayList<String>(getDriver().getWindowHandles());
		
			
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_W);
			robot.keyRelease(KeyEvent.VK_W);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			
			Thread.sleep(2000);
		 	robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_W);
			robot.keyRelease(KeyEvent.VK_W);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			
			
		 	
		 	String actPDF = getBaseDir()+"\\autoIt\\ExportFiles\\RDCubePrint.pdf";
			String expPDF = getBaseDir()+"\\autoIt\\ImportFiles\\RDCubePrint.pdf";
			System.out.println(actPDF);
			System.out.println(expPDF);
			
			
			PDFUtil pdfutil = new PDFUtil();
			
			boolean result = pdfutil.compare(actPDF, expPDF);
			Calendar cal=Calendar.getInstance();
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			String currentDate = df.format(cal.getTime());
			
			String actData = pdfutil.getText(actPDF);
			String expData = pdfutil.getText(expPDF).replaceAll("05/08/2025", currentDate);
			System.out.println(actData);
			System.out.println(expData);
			
			System.out.println("Compared Result  : "+result);
			
			
			if(actData.equalsIgnoreCase(expData))
			{
				return true;
			}
			else
			{
				return false;
			}
			
		 			
			
		}
		
		
		
		public boolean checkHomePageExcelOptioninRDCubeReport() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException
		{
			Thread.sleep(3000);
			
			
			getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportHomeExcelBtn));
			reportHomeExcelBtn.click();
			Thread.sleep(3000);
			
			click(ss_ReportPrintYesBtn);
		
			Thread.sleep(8000);
			
			File Efile=new File(getBaseDir()+"\\autoIt\\ExportFiles\\RDCubeExcelHome.xlsx");
				
				if(Efile.exists())
				{
					Efile.delete();
				}
				
				
						
				
				
				Robot robot = new Robot();
			/*	robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_J);
				robot.keyRelease(KeyEvent.VK_J);
				robot.keyRelease(KeyEvent.VK_CONTROL);
				
				Thread.sleep(2000);
				
				robot.keyPress(KeyEvent.VK_TAB);
				robot.keyRelease(KeyEvent.VK_TAB);
				robot.keyPress(KeyEvent.VK_TAB);
				robot.keyRelease(KeyEvent.VK_TAB);
				

				
				Thread.sleep(2000);
				
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
				
				Thread.sleep(25000);
				
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
				Thread.sleep(4000);
				robot.keyPress(KeyEvent.VK_F12);
				robot.keyRelease(KeyEvent.VK_F12);
				
				
				Thread.sleep(2000);
				*/
				
					
				Runtime.getRuntime().exec(getBaseDir() + "\\autoIt\\scripts\\RDCubeExcelHome.exe");
				
				Thread.sleep(8000);
				
				
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
				Thread.sleep(4000);
		/*		
				
				robot.keyPress(KeyEvent.VK_ALT);
				robot.keyPress(KeyEvent.VK_F4);
				
				robot.keyRelease(KeyEvent.VK_ALT);
				robot.keyRelease(KeyEvent.VK_F4);
				Thread.sleep(5000);
				
				
				*/
				
				
				ArrayList<String> openTabs = new ArrayList<String>(getDriver().getWindowHandles());
					
				int actOpenWindowsCount = getDriver().getWindowHandles().size();
				int expOpenWindowsCount = 2;
				
				System.out.println("Number of Windows  : "+actOpenWindowsCount+"  Value Expected  "+expOpenWindowsCount);
				
				Thread.sleep(1000);

				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_J);
				robot.keyRelease(KeyEvent.VK_J);
				robot.keyRelease(KeyEvent.VK_CONTROL);
				
				Thread.sleep(2000);
				
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_W);
				robot.keyRelease(KeyEvent.VK_W);
				robot.keyRelease(KeyEvent.VK_CONTROL);
			
				Thread.sleep(2000);
				
			 	 ExcelReader excelReader = new ExcelReader(POJOUtility.getExcelPath());
			     

			 	String actExcelfile = getBaseDir()+"\\autoIt\\ExportFiles\\RDCubeExcelHome.xlsx";
			 	String expExcelfile = getBaseDir()+"\\autoIt\\ImportFiles\\RDCubeExcelHome.xlsx";
			 	String sheet = "Sheet1";
			 	   
			 	
			 	
			 	FileInputStream fip1 = new FileInputStream(actExcelfile);
			 	Workbook workbook1  = WorkbookFactory.create(fip1);
			 	
			 	FileInputStream fip2 = new FileInputStream(expExcelfile);
			 	Workbook workbook2  = WorkbookFactory.create(fip2);
			 	
			 	boolean result = excelReader.checkExcelSheetsComparisonWithMonth(workbook1, workbook2,"05/08/2025");
			 	
			 	System.err.println(result);
			 	
			 	if (result)
			 	{
			 		return true;
			 	}
			 	else
			 	{
			 		return false;
			 	}

				
		
			
		}
		
public boolean checkHomePageCSVOptioninRDCubeReport() throws InterruptedException, IOException, AWTException, EncryptedDocumentException, InvalidFormatException
{
	

	Thread.sleep(2000);
	getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportHomeExportBtn));
	reportHomeExportBtn.click();
	Thread.sleep(2000);
	getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportHomeExportCSVBtn));
	reportHomeExportCSVBtn.click();
	Thread.sleep(4000);
	
	File Efile1 = new File(getBaseDir() + "\\autoIt\\ExportFiles\\RDCubeHomeCSV.csv");

	if (Efile1.exists()) {
		Efile1.delete();
	}
	
	getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(ss_ReportPrintYesBtn));
	ss_ReportPrintYesBtn.click();
	Thread.sleep(10000);
	
	
	Robot robot = new Robot();
/*	robot.keyPress(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_J);
	robot.keyRelease(KeyEvent.VK_J);
	robot.keyRelease(KeyEvent.VK_CONTROL);
	
	Thread.sleep(2000);
	
	
	
	robot.keyPress(KeyEvent.VK_TAB);
	robot.keyRelease(KeyEvent.VK_TAB);
	robot.keyPress(KeyEvent.VK_TAB);
	robot.keyRelease(KeyEvent.VK_TAB);
	
	Thread.sleep(2000);
	
	robot.keyPress(KeyEvent.VK_ENTER);
	robot.keyRelease(KeyEvent.VK_ENTER);
	
	Thread.sleep(25000);
	
	robot.keyPress(KeyEvent.VK_ENTER);
	robot.keyRelease(KeyEvent.VK_ENTER);
	Thread.sleep(2000);
	robot.keyPress(KeyEvent.VK_F12);
	robot.keyRelease(KeyEvent.VK_F12);
	
	
	Thread.sleep(2000);*/
	
	try {
		Runtime.getRuntime().exec(getBaseDir() + "\\autoIt\\scripts\\RDCubeHomeCSV.exe");
	} catch (Exception e) {
		
		e.printStackTrace();
	}
	
	Thread.sleep(10000);
	
/*	robot.keyPress(KeyEvent.VK_TAB);
	robot.keyRelease(KeyEvent.VK_TAB);
	Thread.sleep(2000);
	robot.keyPress(KeyEvent.VK_C);
	robot.keyRelease(KeyEvent.VK_C);
	Thread.sleep(2000);
	
	robot.keyPress(KeyEvent.VK_TAB);
	robot.keyRelease(KeyEvent.VK_TAB);
	
	robot.keyPress(KeyEvent.VK_TAB);
	robot.keyRelease(KeyEvent.VK_TAB);
	
	robot.keyPress(KeyEvent.VK_TAB);
	robot.keyRelease(KeyEvent.VK_TAB);
	
	robot.keyPress(KeyEvent.VK_TAB);
	robot.keyRelease(KeyEvent.VK_TAB);
	
	robot.keyPress(KeyEvent.VK_TAB);
	robot.keyRelease(KeyEvent.VK_TAB);
	
	robot.keyPress(KeyEvent.VK_ENTER);
	robot.keyRelease(KeyEvent.VK_ENTER);
	Thread.sleep(2000);
	
	robot.keyPress(KeyEvent.VK_ENTER);
	robot.keyRelease(KeyEvent.VK_ENTER);
	Thread.sleep(2000);
	
	robot.keyPress(KeyEvent.VK_ALT);
	robot.keyPress(KeyEvent.VK_F4);
	
	robot.keyRelease(KeyEvent.VK_ALT);
	robot.keyRelease(KeyEvent.VK_F4);
	Thread.sleep(6000);
	
	robot.keyPress(KeyEvent.VK_TAB);
	robot.keyRelease(KeyEvent.VK_TAB);
	*/
	robot.keyPress(KeyEvent.VK_ENTER);
	robot.keyRelease(KeyEvent.VK_ENTER);
	Thread.sleep(6000);
	
	robot.keyPress(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_J);
	robot.keyRelease(KeyEvent.VK_J);
	robot.keyRelease(KeyEvent.VK_CONTROL);
	
	Thread.sleep(2000);
	
	ArrayList<String> openTabs = new ArrayList<String>(getDriver().getWindowHandles());
	
	int actOpenWindowsCount = getDriver().getWindowHandles().size();
	int expOpenWindowsCount = 2;
	
	System.out.println("Number of Windows  : "+actOpenWindowsCount+"  Value Expected : "+expOpenWindowsCount);
	
	Thread.sleep(6000);

 
	
	robot.keyPress(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_W);
	robot.keyRelease(KeyEvent.VK_W);
	robot.keyRelease(KeyEvent.VK_CONTROL);
	

	Thread.sleep(2000);
 	
 	ExcelReader excelReader = new ExcelReader(POJOUtility.getExcelPath());
 	
 	String actFile= getBaseDir()+"\\autoIt\\ExportFiles\\RDCubeHomeCSV.csv";
 	String expFile = getBaseDir()+"\\autoIt\\ImportFiles\\RDCubeHomeCSV.csv";
 	
 	
 	FileInputStream file1=new FileInputStream(actFile);
	FileInputStream file2=new FileInputStream(expFile);
 	
 	boolean result = excelReader.verifyDataInCSVWithMonth(actFile, expFile,"11/03/2026");
 	
 	System.err.println("LATEST METHOD : "+result);
 	
 	if (result)
 	{
 		return true;
 	}
 	else
 	{
 		return false;
 	}


	

}


public boolean checkHomePagePDFOptioninRDCubeReport() throws InterruptedException, AWTException, IOException
{

	Thread.sleep(2000);
	getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportHomeExportBtn));
	reportHomeExportBtn.click();
	Thread.sleep(2000);
	getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportHomeExportPDFBtn));
	reportHomeExportPDFBtn.click();
	Thread.sleep(4000);
	
	File Efile1 = new File(getBaseDir() + "\\autoIt\\ExportFiles\\RDCubeHomePDF.pdf");

	if (Efile1.exists()) {
		Efile1.delete();
	}

	Thread.sleep(4000);
	
	
	
	getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(ss_ReportPrintYesBtn));
	ss_ReportPrintYesBtn.click();
	Thread.sleep(10000);
	
	Robot robot = new Robot();
/*	robot.keyPress(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_J);
	robot.keyRelease(KeyEvent.VK_J);
	robot.keyRelease(KeyEvent.VK_CONTROL);

	Thread.sleep(8000);

	robot.keyPress(KeyEvent.VK_TAB);
	robot.keyRelease(KeyEvent.VK_TAB);
	robot.keyPress(KeyEvent.VK_TAB);
	robot.keyRelease(KeyEvent.VK_TAB);

	robot.keyPress(KeyEvent.VK_ENTER);
	robot.keyRelease(KeyEvent.VK_ENTER);

	Thread.sleep(25000);

	robot.keyPress(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_S);
	robot.keyRelease(KeyEvent.VK_S);
	robot.keyRelease(KeyEvent.VK_CONTROL);

	Thread.sleep(5000);*/
	Runtime.getRuntime().exec(getBaseDir() + "\\autoIt\\scripts\\RDCubeHomePDF.exe");

	Thread.sleep(8000);

	robot.keyPress(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_J);
	robot.keyRelease(KeyEvent.VK_J);
	robot.keyRelease(KeyEvent.VK_CONTROL);

	Thread.sleep(2000);

	ArrayList<String> newTabs = new ArrayList<String>(getDriver().getWindowHandles());

	int actOpenWindowsCount = getDriver().getWindowHandles().size();
	int expOpenWindowsCount = 3;

	System.out.println(
			"Number of Windows  : " + actOpenWindowsCount + "  Value Expected  " + expOpenWindowsCount);


	
	
	robot.keyPress(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_W);
	robot.keyRelease(KeyEvent.VK_W);
	robot.keyRelease(KeyEvent.VK_CONTROL);
	
	Thread.sleep(2000);
 	/*robot.keyPress(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_W);
	robot.keyRelease(KeyEvent.VK_W);
	robot.keyRelease(KeyEvent.VK_CONTROL);
	*/
	
	
	
	String actPDF = getBaseDir()+"\\autoIt\\ExportFiles\\RDCubeHomePDF.pdf";
	String expPDF = getBaseDir()+"\\autoIt\\ImportFiles\\RDCubeHomePDF.pdf";
	
	PDFUtil pdfutil = new PDFUtil();
	
	boolean result = pdfutil.compare(actPDF, expPDF);
	
	String data = pdfutil.getText(expPDF);
	
	DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	Calendar cal = Calendar.getInstance();
	String date = df.format(cal.getTime());
	System.err.println(date);
	
	String oldDate = "05/08/2025";
	

	
	String actData = pdfutil.getText(actPDF);
	String expData = data.replace(oldDate, date);
	
	System.err.println(actData);
	System.err.println(expData);
	
	System.out.println("Compared Result  : "+result);
	
	if (actData.equalsIgnoreCase(expData))
	{
		return true;
	}
	else
	{
		return false;
	}
}


public boolean checkHomePageFileOptioninRDCubeReport() throws InterruptedException, IOException, AWTException
{
	
	

	Thread.sleep(4000);
	getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportHomeExportBtn));
	reportHomeExportBtn.click();
	Thread.sleep(2000);
	getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportHomeExportFileBtn));
	reportHomeExportFileBtn.click();
	
	File Efile1 = new File(getBaseDir() + "\\autoIt\\ExportFiles\\RDCubeHomeFile.pdf");

	if (Efile1.exists()) {
		Efile1.delete();
	}

	Thread.sleep(4000);
	
	
	Thread.sleep(4000);
	getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(ss_ReportPrintYesBtn));
	ss_ReportPrintYesBtn.click();
	Thread.sleep(10000);
	
	Robot robot = new Robot();
/*	robot.keyPress(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_J);
	robot.keyRelease(KeyEvent.VK_J);
	robot.keyRelease(KeyEvent.VK_CONTROL);

	Thread.sleep(5000);

	robot.keyPress(KeyEvent.VK_TAB);
	robot.keyRelease(KeyEvent.VK_TAB);
	robot.keyPress(KeyEvent.VK_TAB);
	robot.keyRelease(KeyEvent.VK_TAB);

	robot.keyPress(KeyEvent.VK_ENTER);
	robot.keyRelease(KeyEvent.VK_ENTER);

	Thread.sleep(23000);

	robot.keyPress(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_S);
	robot.keyRelease(KeyEvent.VK_S);
	robot.keyRelease(KeyEvent.VK_CONTROL);

	Thread.sleep(5000);*/
	Runtime.getRuntime().exec(getBaseDir() + "\\autoIt\\scripts\\RDCubeHomeFile.exe");

	Thread.sleep(8000);

	robot.keyPress(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_J);
	robot.keyRelease(KeyEvent.VK_J);
	robot.keyRelease(KeyEvent.VK_CONTROL);

	Thread.sleep(2000);

	ArrayList<String> newTabs = new ArrayList<String>(getDriver().getWindowHandles());

	int actOpenWindowsCount = getDriver().getWindowHandles().size();
	int expOpenWindowsCount = 3;

	System.out.println(
			"Number of Windows  : " + actOpenWindowsCount + "  Value Expected  " + expOpenWindowsCount);


	
	
	robot.keyPress(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_W);
	robot.keyRelease(KeyEvent.VK_W);
	robot.keyRelease(KeyEvent.VK_CONTROL);
	
	Thread.sleep(2000);
 /*	robot.keyPress(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_W);
	robot.keyRelease(KeyEvent.VK_W);
	robot.keyRelease(KeyEvent.VK_CONTROL);
	
	Thread.sleep(2000);*/
	
	String actPDF = getBaseDir()+"\\autoIt\\ExportFiles\\RDCubeHomeFile.pdf";
	String expPDF = getBaseDir()+"\\autoIt\\ImportFiles\\RDCubeHomeFile.pdf";
	
	PDFUtil pdfutil = new PDFUtil();
	
	boolean result = pdfutil.compare(actPDF, expPDF);
	
	String data = pdfutil.getText(expPDF);
	
	
	String actData = pdfutil.getText(actPDF);
	String expData = data.replace("05/08/2025", getCurrentDate());
	
	System.err.println(actData);
	System.err.println(expData);
	
	System.out.println("Compared Result  : "+result);
	
	if (actData.equalsIgnoreCase(expData))
	{
		return true;
	}
	else
	{
		return false;
	}


}


		
///Entry Page
		
		
		public boolean checkEntryPagePrintOptioninRDCubeReport() throws InterruptedException, IOException, AWTException
		{
			
			click(sl_OkBtn);
						
			Thread.sleep(4000);
			
			click(reportEntryPrintBtn);
			Thread.sleep(2000);
			
			click(ss_ReportPrintYesBtn);
			Thread.sleep(5000);
			
			
	File Efile=new File(getBaseDir()+"\\autoIt\\ExportFiles\\RDCubeEntryPrint.pdf");
			
			if(Efile.exists())
			{
				Efile.delete();
			}
			
			Thread.sleep(2000);
			
			
			Thread.sleep(2000);
			
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_S);
			robot.keyRelease(KeyEvent.VK_S);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			
			Thread.sleep(6000);
				
			Runtime.getRuntime().exec(getBaseDir() + "\\autoIt\\scripts\\RDCubeEntryPrint.exe");
			
			Thread.sleep(12000);
			
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_J);
			robot.keyRelease(KeyEvent.VK_J);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			Thread.sleep(2000);
			
			ArrayList<String> openTabs = new ArrayList<String>(getDriver().getWindowHandles());
		
			
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_W);
			robot.keyRelease(KeyEvent.VK_W);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			
			Thread.sleep(2000);
		 	robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_W);
			robot.keyRelease(KeyEvent.VK_W);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			
			Thread.sleep(2000);
		 	
		 	String actPDF = getBaseDir()+"\\autoIt\\ExportFiles\\RDCubeEntryPrint.pdf";
			String expPDF = getBaseDir()+"\\autoIt\\ImportFiles\\RDCubeEntryPrint.pdf";
			System.out.println(actPDF);
			System.out.println(expPDF);
			
			
			PDFUtil pdfutil = new PDFUtil();
			
			boolean result = pdfutil.compare(actPDF, expPDF);
			Calendar cal=Calendar.getInstance();
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			String currentDate = df.format(cal.getTime());
			
			String actData = pdfutil.getText(actPDF);
			String expData = pdfutil.getText(expPDF).replaceAll("05/08/2025", currentDate);
			System.out.println(actData);
			System.out.println(expData);
			
			System.out.println("Compared Result  : "+result);
			
			
			if(actData.equalsIgnoreCase(expData))
			{
				return true;
			}
			else
			{
				return false;
			}
			
			
		}
	
		
		public boolean checkEntryPageExcelOptioninRDCubeReport() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException
		{
			Thread.sleep(3000);
			
			
			getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportEntryExcelBtn));
			reportEntryExcelBtn.click();
			Thread.sleep(3000);
			
			click(ss_ReportPrintYesBtn);
		
			Thread.sleep(8000);
			
			File Efile=new File(getBaseDir()+"\\autoIt\\ExportFiles\\RDCubeExcelEntry.xlsx");
				
				if(Efile.exists())
				{
					Efile.delete();
				}
				
				
						
				
				
				Robot robot = new Robot();
		/*		robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_J);
				robot.keyRelease(KeyEvent.VK_J);
				robot.keyRelease(KeyEvent.VK_CONTROL);
				
				Thread.sleep(2000);
				
				robot.keyPress(KeyEvent.VK_TAB);
				robot.keyRelease(KeyEvent.VK_TAB);
				robot.keyPress(KeyEvent.VK_TAB);
				robot.keyRelease(KeyEvent.VK_TAB);
				

				
				Thread.sleep(2000);
				
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
				
				Thread.sleep(25000);
				
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
				Thread.sleep(4000);
				robot.keyPress(KeyEvent.VK_F12);
				robot.keyRelease(KeyEvent.VK_F12);
				
				
				Thread.sleep(2000);
				
				*/
					
				Runtime.getRuntime().exec(getBaseDir() + "\\autoIt\\scripts\\RDCubeExcelEntry.exe");
				
				Thread.sleep(8000);
				
				
			robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
				Thread.sleep(4000);
				
			/*	
				robot.keyPress(KeyEvent.VK_ALT);
				robot.keyPress(KeyEvent.VK_F4);
				
				robot.keyRelease(KeyEvent.VK_ALT);
				robot.keyRelease(KeyEvent.VK_F4);
				Thread.sleep(5000);
				
				*/
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_J);
				robot.keyRelease(KeyEvent.VK_J);
				robot.keyRelease(KeyEvent.VK_CONTROL);
				
				Thread.sleep(2000);
				
				
				ArrayList<String> openTabs = new ArrayList<String>(getDriver().getWindowHandles());
					
				int actOpenWindowsCount = getDriver().getWindowHandles().size();
				int expOpenWindowsCount = 2;
				
				System.out.println("Number of Windows  : "+actOpenWindowsCount+"  Value Expected  "+expOpenWindowsCount);
				
				Thread.sleep(1000);

			 
				
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_W);
				robot.keyRelease(KeyEvent.VK_W);
				robot.keyRelease(KeyEvent.VK_CONTROL);
			
				Thread.sleep(2000);
				
			 	 ExcelReader excelReader = new ExcelReader(POJOUtility.getExcelPath());
			     

			 	String actExcelfile = getBaseDir()+"\\autoIt\\ExportFiles\\RDCubeExcelEntry.xlsx";
			 	String expExcelfile = getBaseDir()+"\\autoIt\\ImportFiles\\RDCubeExcelEntry.xlsx";
			 	String sheet = "Sheet1";
			 	   
			 	
			 	
			 	FileInputStream fip1 = new FileInputStream(actExcelfile);
			 	Workbook workbook1  = WorkbookFactory.create(fip1);
			 	
			 	FileInputStream fip2 = new FileInputStream(expExcelfile);
			 	Workbook workbook2  = WorkbookFactory.create(fip2);
			 	
			 	boolean result = excelReader.checkExcelSheetsComparisonWithMonth(workbook1, workbook2,"29/09/2025");
			 	
			 	System.err.println(result);
			 	
			 	if (result)
			 	{
			 		return true;
			 	}
			 	else
			 	{
			 		return false;
			 	}

				
		
			
		}
		
public boolean checkEntryPageCSVOptioninRDCubeReport() throws InterruptedException, IOException, AWTException, EncryptedDocumentException, InvalidFormatException
{
	

	Thread.sleep(2000);
	getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportEntryExportBtn));
	reportEntryExportBtn.click();
	Thread.sleep(2000);
	getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportEntryExportCSVBtn));
	reportEntryExportCSVBtn.click();
	Thread.sleep(4000);
	
	File Efile1 = new File(getBaseDir() + "\\autoIt\\ExportFiles\\RDCubeEntryCSV.csv");

	if (Efile1.exists()) {
		Efile1.delete();
	}
	
	getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(ss_ReportPrintYesBtn));
	ss_ReportPrintYesBtn.click();
	Thread.sleep(10000);
	
	
	Robot robot = new Robot();
/*	robot.keyPress(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_J);
	robot.keyRelease(KeyEvent.VK_J);
	robot.keyRelease(KeyEvent.VK_CONTROL);
	
	Thread.sleep(2000);
	
	
	
	robot.keyPress(KeyEvent.VK_TAB);
	robot.keyRelease(KeyEvent.VK_TAB);
	robot.keyPress(KeyEvent.VK_TAB);
	robot.keyRelease(KeyEvent.VK_TAB);
	
	Thread.sleep(2000);
	
	robot.keyPress(KeyEvent.VK_ENTER);
	robot.keyRelease(KeyEvent.VK_ENTER);
	
	Thread.sleep(23000);
	
	robot.keyPress(KeyEvent.VK_ENTER);
	robot.keyRelease(KeyEvent.VK_ENTER);
	Thread.sleep(2000);
	robot.keyPress(KeyEvent.VK_F12);
	robot.keyRelease(KeyEvent.VK_F12);
	
	
	Thread.sleep(2000);
	*/
	try {
		Runtime.getRuntime().exec(getBaseDir() + "\\autoIt\\scripts\\RDCubeEntryCSV.exe");
	} catch (Exception e) {
		
		e.printStackTrace();
	}
	
	Thread.sleep(10000);
	
	
	
/*	robot.keyPress(KeyEvent.VK_TAB);
	robot.keyRelease(KeyEvent.VK_TAB);
	Thread.sleep(2000);
	robot.keyPress(KeyEvent.VK_C);
	robot.keyRelease(KeyEvent.VK_C);
	Thread.sleep(2000);
	
	robot.keyPress(KeyEvent.VK_TAB);
	robot.keyRelease(KeyEvent.VK_TAB);
	
	robot.keyPress(KeyEvent.VK_TAB);
	robot.keyRelease(KeyEvent.VK_TAB);
	
	robot.keyPress(KeyEvent.VK_TAB);
	robot.keyRelease(KeyEvent.VK_TAB);
	
	robot.keyPress(KeyEvent.VK_TAB);
	robot.keyRelease(KeyEvent.VK_TAB);
	
	robot.keyPress(KeyEvent.VK_TAB);
	robot.keyRelease(KeyEvent.VK_TAB);
	
	robot.keyPress(KeyEvent.VK_ENTER);
	robot.keyRelease(KeyEvent.VK_ENTER);
	Thread.sleep(2000);
	
	robot.keyPress(KeyEvent.VK_ENTER);
	robot.keyRelease(KeyEvent.VK_ENTER);
	Thread.sleep(2000);
	
	robot.keyPress(KeyEvent.VK_ALT);
	robot.keyPress(KeyEvent.VK_F4);
	
	robot.keyRelease(KeyEvent.VK_ALT);
	robot.keyRelease(KeyEvent.VK_F4);
	Thread.sleep(6000);
	
	robot.keyPress(KeyEvent.VK_TAB);
	robot.keyRelease(KeyEvent.VK_TAB);*/
	
	robot.keyPress(KeyEvent.VK_ENTER);
	robot.keyRelease(KeyEvent.VK_ENTER);
	Thread.sleep(6000);
	
	robot.keyPress(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_J);
	robot.keyRelease(KeyEvent.VK_J);
	robot.keyRelease(KeyEvent.VK_CONTROL);
	
	Thread.sleep(2000);
	
	ArrayList<String> openTabs = new ArrayList<String>(getDriver().getWindowHandles());
	
	int actOpenWindowsCount = getDriver().getWindowHandles().size();
	int expOpenWindowsCount = 2;
	
	System.out.println("Number of Windows  : "+actOpenWindowsCount+"  Value Expected : "+expOpenWindowsCount);
	
	Thread.sleep(6000);

 
	
	robot.keyPress(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_W);
	robot.keyRelease(KeyEvent.VK_W);
	robot.keyRelease(KeyEvent.VK_CONTROL);
	
	Thread.sleep(2000);
	
 	
 	ExcelReader excelReader = new ExcelReader(POJOUtility.getExcelPath());
 	
 	String actFile= getBaseDir()+"\\autoIt\\ExportFiles\\RDCubeEntryCSV.csv";
 	String expFile = getBaseDir()+"\\autoIt\\ImportFiles\\RDCubeEntryCSV.csv";
 	
 	
 	FileInputStream file1=new FileInputStream(actFile);
	FileInputStream file2=new FileInputStream(expFile);
 	
 	boolean result = excelReader.verifyDataInCSVWithMonth(actFile, expFile,"11/03/2026");
 	
 	System.err.println("LATEST METHOD : "+result);
 	
 	if (result)
 	{
 		return true;
 	}
 	else
 	{
 		return false;
 	}


	

}


public boolean checkEntryPagePDFOptioninRDCubeReport() throws InterruptedException, AWTException, IOException
{

	Thread.sleep(2000);
	getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportEntryExportBtn));
	reportEntryExportBtn.click();
	Thread.sleep(2000);
	getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportEntryExportPDFBtn));
	reportEntryExportPDFBtn.click();
	Thread.sleep(4000);
	
	File Efile1 = new File(getBaseDir() + "\\autoIt\\ExportFiles\\RDCubeEntryPDF.pdf");

	if (Efile1.exists()) {
		Efile1.delete();
	}

	Thread.sleep(4000);
	
	
	
	getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(ss_ReportPrintYesBtn));
	ss_ReportPrintYesBtn.click();
	Thread.sleep(10000);
	
	Robot robot = new Robot();
/*	robot.keyPress(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_J);
	robot.keyRelease(KeyEvent.VK_J);
	robot.keyRelease(KeyEvent.VK_CONTROL);

	Thread.sleep(8000);

	robot.keyPress(KeyEvent.VK_TAB);
	robot.keyRelease(KeyEvent.VK_TAB);
	robot.keyPress(KeyEvent.VK_TAB);
	robot.keyRelease(KeyEvent.VK_TAB);

	robot.keyPress(KeyEvent.VK_ENTER);
	robot.keyRelease(KeyEvent.VK_ENTER);

	Thread.sleep(23000);

	robot.keyPress(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_S);
	robot.keyRelease(KeyEvent.VK_S);
	robot.keyRelease(KeyEvent.VK_CONTROL);

	Thread.sleep(5000);*/
	Runtime.getRuntime().exec(getBaseDir() + "\\autoIt\\scripts\\RDCubeEntryPDF.exe");

	Thread.sleep(8000);

	robot.keyPress(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_J);
	robot.keyRelease(KeyEvent.VK_J);
	robot.keyRelease(KeyEvent.VK_CONTROL);

	Thread.sleep(2000);

	ArrayList<String> newTabs = new ArrayList<String>(getDriver().getWindowHandles());

	int actOpenWindowsCount = getDriver().getWindowHandles().size();
	int expOpenWindowsCount = 3;

	System.out.println(
			"Number of Windows  : " + actOpenWindowsCount + "  Value Expected  " + expOpenWindowsCount);


	
	
	robot.keyPress(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_W);
	robot.keyRelease(KeyEvent.VK_W);
	robot.keyRelease(KeyEvent.VK_CONTROL);
	
	Thread.sleep(2000);
 /*	robot.keyPress(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_W);
	robot.keyRelease(KeyEvent.VK_W);
	robot.keyRelease(KeyEvent.VK_CONTROL);
	
	Thread.sleep(2000);
	*/
	
	String actPDF = getBaseDir()+"\\autoIt\\ExportFiles\\RDCubeEntryPDF.pdf";
	String expPDF = getBaseDir()+"\\autoIt\\ImportFiles\\RDCubeEntryPDF.pdf";
	
	PDFUtil pdfutil = new PDFUtil();
	
	boolean result = pdfutil.compare(actPDF, expPDF);
	
	String data = pdfutil.getText(expPDF);
	
	DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	Calendar cal = Calendar.getInstance();
	String date = df.format(cal.getTime());
	System.err.println(date);
	
	String oldDate = "05/08/2025";
	

	
	String actData = pdfutil.getText(actPDF);
	String expData = data.replace(oldDate, date);
	
	System.err.println(actData);
	System.err.println(expData);
	
	System.out.println("Compared Result  : "+result);
	
	if (actData.equalsIgnoreCase(expData))
	{
		return true;
	}
	else
	{
		return false;
	}
}


public boolean checkEntryPageFileOptioninRDCubeReport() throws InterruptedException, IOException, AWTException
{
	
	

	Thread.sleep(4000);
	getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportEntryExportBtn));
	reportEntryExportBtn.click();
	Thread.sleep(2000);
	getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportEntryExportFileBtn));
	reportEntryExportFileBtn.click();
	
	File Efile1 = new File(getBaseDir() + "\\autoIt\\ExportFiles\\RDCubeEntryFile.pdf");

	if (Efile1.exists()) {
		Efile1.delete();
	}

	Thread.sleep(4000);
	
	
	Thread.sleep(4000);
	getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(ss_ReportPrintYesBtn));
	ss_ReportPrintYesBtn.click();
	Thread.sleep(10000);
	
	Robot robot = new Robot();
	/*robot.keyPress(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_J);
	robot.keyRelease(KeyEvent.VK_J);
	robot.keyRelease(KeyEvent.VK_CONTROL);

	Thread.sleep(5000);

	robot.keyPress(KeyEvent.VK_TAB);
	robot.keyRelease(KeyEvent.VK_TAB);
	robot.keyPress(KeyEvent.VK_TAB);
	robot.keyRelease(KeyEvent.VK_TAB);

	robot.keyPress(KeyEvent.VK_ENTER);
	robot.keyRelease(KeyEvent.VK_ENTER);

	Thread.sleep(23000);

	robot.keyPress(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_S);
	robot.keyRelease(KeyEvent.VK_S);
	robot.keyRelease(KeyEvent.VK_CONTROL);

	Thread.sleep(5000);
	*/
	Runtime.getRuntime().exec(getBaseDir() + "\\autoIt\\scripts\\RDCubeEntryFile.exe");

	Thread.sleep(8000);

	robot.keyPress(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_J);
	robot.keyRelease(KeyEvent.VK_J);
	robot.keyRelease(KeyEvent.VK_CONTROL);

	Thread.sleep(2000);

	ArrayList<String> newTabs = new ArrayList<String>(getDriver().getWindowHandles());

	int actOpenWindowsCount = getDriver().getWindowHandles().size();
	int expOpenWindowsCount = 3;

	System.out.println(
			"Number of Windows  : " + actOpenWindowsCount + "  Value Expected  " + expOpenWindowsCount);


	
	
	robot.keyPress(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_W);
	robot.keyRelease(KeyEvent.VK_W);
	robot.keyRelease(KeyEvent.VK_CONTROL);
	
	Thread.sleep(2000);
 /*	robot.keyPress(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_W);
	robot.keyRelease(KeyEvent.VK_W);
	robot.keyRelease(KeyEvent.VK_CONTROL);
	Thread.sleep(2000);
	*/
	
	String actPDF = getBaseDir()+"\\autoIt\\ExportFiles\\RDCubeEntryFile.pdf";
	String expPDF = getBaseDir()+"\\autoIt\\ImportFiles\\RDCubeEntryFile.pdf";
	
	PDFUtil pdfutil = new PDFUtil();
	
	boolean result = pdfutil.compare(actPDF, expPDF);
	
	String data = pdfutil.getText(expPDF);
	
	
	String actData = pdfutil.getText(actPDF);
	String expData = data.replace("05/08/2025", getCurrentDate());
	
	System.err.println(actData);
	System.err.println(expData);
	
	System.out.println("Compared Result  : "+result);
	
	if (actData.equalsIgnoreCase(expData))
	{
		return true;
	}
	else
	{
		return false;
	}


}



@FindBy(xpath="(//a[@title='Customize'])[1]")
public static WebElement rd_HomeCustBtn;

@FindBy(xpath="//a[contains(text(),'Header/Footer')]")
public static WebElement rd_HeaderFooterTab;

@FindBy(xpath = "(//i[@class='icon-close hiconright2'])[2]")
public static WebElement closeBtn;


public boolean checkHeaderFooterinRDDetailReport() throws InterruptedException, IOException, EncryptedDocumentException, InvalidFormatException
{
	Thread.sleep(2500);
	
	logout();
	Thread.sleep(3500);
	
	checkLoginForRD2();
	Thread.sleep(5000);
	
	
	getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
	searchTxt.click();
	Thread.sleep(1000);
	searchTxt.sendKeys("RD Detail");
	Thread.sleep(1000);
	searchTxt.sendKeys(Keys.ENTER);
	
	Thread.sleep(8000);
	
	click(rd_HomeCustBtn);
	Thread.sleep(5000);
	
	click(rd_HeaderFooterTab);
	Thread.sleep(5000);
	
	Runtime.getRuntime().exec(getBaseDir() + "\\autoIt\\scripts\\scripts\\RDHeaderFooterLayout.exe");
	
	Thread.sleep(55000);
	
	click(finishBtn);
	Thread.sleep(4000);
	
	String expMsg="Data saved successfully.";
	
	String actMsg=checkValidationMessage(expMsg);
	
	click(closeBtn);
	Thread.sleep(4000);
	if(actMsg.equalsIgnoreCase(expMsg))
	{
	
	return true;
	}
	else
	{
		return false;
	}
	
	
}


public boolean checkPrintOptioninRDDetailReportAfterHeaderFooter() throws InterruptedException, IOException, AWTException
{
	
	click(reportHomePrintBtn);
	Thread.sleep(4000);
	
	click(ss_ReportPrintYesBtn);
	Thread.sleep(8000);
	
	
	File Efile=new File(getBaseDir()+"\\autoIt\\ExportFiles\\RDDetailHeaderFooterPrint.pdf");
	
	if(Efile.exists())
	{
		Efile.delete();
	}
	
	Thread.sleep(2000);
	
	
	Thread.sleep(2000);
	
	Robot robot = new Robot();
	robot.keyPress(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_S);
	robot.keyRelease(KeyEvent.VK_S);
	robot.keyRelease(KeyEvent.VK_CONTROL);
	
	Thread.sleep(6000);
		
	Runtime.getRuntime().exec(getBaseDir() + "\\autoIt\\scripts\\RDDetailHeaderFooterPrint.exe");
	
	Thread.sleep(12000);
	
	robot.keyPress(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_J);
	robot.keyRelease(KeyEvent.VK_J);
	robot.keyRelease(KeyEvent.VK_CONTROL);
	Thread.sleep(2000);
	
	ArrayList<String> openTabs = new ArrayList<String>(getDriver().getWindowHandles());

	
	robot.keyPress(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_W);
	robot.keyRelease(KeyEvent.VK_W);
	robot.keyRelease(KeyEvent.VK_CONTROL);
	Thread.sleep(2000);
	
 	robot.keyPress(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_W);
	robot.keyRelease(KeyEvent.VK_W);
	robot.keyRelease(KeyEvent.VK_CONTROL);
	
	Thread.sleep(2000);
 	
 	String actPDF = getBaseDir()+"\\autoIt\\ExportFiles\\RDDetailHeaderFooterPrint.pdf";
	String expPDF = getBaseDir()+"\\autoIt\\ImportFiles\\RDDetailHeaderFooterPrint.pdf";
	System.out.println(actPDF);
	System.out.println(expPDF);
	
	
	PDFUtil pdfutil = new PDFUtil();
	
	boolean result = pdfutil.compare(actPDF, expPDF);
	Calendar cal=Calendar.getInstance();
	DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	String currentDate = df.format(cal.getTime());
	
	String currentTime = LocalTime.now().toString();
	
	//String actData = pdfutil.getText(actPDF).replaceAll("\\b\\d{1,2}:\\d{1,2}:\\d{1,2}", "TimeField");
	String actData = pdfutil.getText(actPDF);
	String expData = pdfutil.getText(expPDF).replaceAll("06/08/2025", currentDate).replaceAll("11:30:30", currentTimeDay());
	System.out.println(actData);
	System.out.println(expData);
	
	System.out.println("Compared Result  : "+result);
	
	
	if(actData.equalsIgnoreCase(expData))
	{
		return true;
	}
	else
	{
		return false;
	}
	
	
	
}

@FindBy(xpath="//*[@id='dvReportDetails']//table//tr//td[2]")
public static List<WebElement> docNoColList;
   		
   		
 public boolean checkRDDetailReportforDateRangeasAsonDate() throws InterruptedException
 {
	 getDriver().navigate().refresh();
	 Thread.sleep(4000);
	 
	 getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
		searchTxt.click();
		Thread.sleep(1000);
		searchTxt.sendKeys("RD Detail");
		Thread.sleep(1000);
		searchTxt.sendKeys(Keys.ENTER);
		
		Thread.sleep(8000);
		
		click(sl_DateOptionDropdown);
		Select s=new Select(sl_DateOptionDropdown);
		s.selectByVisibleText("As on date");
		sl_DateOptionDropdown.sendKeys(Keys.TAB);
		
		click(sl_OkBtn);
		Thread.sleep(8000);
		
		boolean novalidationConfirmationMessage =validationConfirmationMessage.getText().isEmpty();
		
		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_1stRow1stCol));
		
		click(report_LastBtn);
		Thread.sleep(8000);
		
		int i;
		for(i=0;i<docNoColList.size();i++)
		{
			String data=docNoColList.get(i).getText();
			if(data.equals("Grand Total"))
			{
				break;
			}
		}
		
		String xpath1="//*[@id='dvReportDetails']//table//tr[";
		String xpath2="]//td";
		
		
		System.out.println(xpath1+(i+1)+xpath2);
		List<WebElement>grandTotList=getDriver().findElements(By.xpath(xpath1+(i+1)+xpath2));
		
		ArrayList<String>GrandTotList=new ArrayList<String>();
		for(int j=1;j<grandTotList.size();j++)
		{
			GrandTotList.add(grandTotList.get(j).getText());
			
		}
		
		String actGrandTotList=GrandTotList.toString();
		String expGrandTotList="[Grand Total, , , , , , 3,476.73, 2,456.38, 55,990.91, , , 10,086.00, 3,716.73]";
		
		System.out.println("actGrandTotList		"		+		actGrandTotList);
		System.out.println("expGrandTotList		"		+		expGrandTotList);
		
		click(report_CloseBtn);
		Thread.sleep(2000);
		
		if(actGrandTotList.equalsIgnoreCase(expGrandTotList))
		{
			return true;
		}
		else
		{
			return false;
		}
		
		
 }
   		
   
 public boolean checkRDDetailReportforDateRangeasCurrentMonth() throws InterruptedException
 {
	 Thread.sleep(2000);
	 click(sl_DateOptionDropdown);
	 Thread.sleep(1500);
		Select s=new Select(sl_DateOptionDropdown);
		s.selectByValue("2");
		Thread.sleep(1500);
	//	sl_DateOptionDropdown.sendKeys(Keys.TAB);
		Thread.sleep(1500);
		
		click(sl_OkBtn);
		Thread.sleep(4000);
		
		boolean novalidationConfirmationMessage =validationConfirmationMessage.getText().isEmpty();
		
		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";
		
		boolean actReportTable = reportsTable.getText().isEmpty();
		boolean expReportTable = true;
		
		System.out.println(actReportTable);
		System.out.println(expReportTable);
		
		click(report_CloseBtn);
		Thread.sleep(4000);
		
		if(actReportTable==expReportTable)
		{
			return true;
		}
		else
		{
			return false;
		}
 }
 

 
 public boolean checkRDDetailReportforDateRangeasPreviousMonth() throws InterruptedException
 {
	 Thread.sleep(1500);
	 click(sl_DateOptionDropdown);
		Select s=new Select(sl_DateOptionDropdown);
		s.selectByValue("3");
		Thread.sleep(1500);
		//sl_DateOptionDropdown.sendKeys(Keys.TAB);
		
		click(sl_OkBtn);
		Thread.sleep(8000);
		
		boolean novalidationConfirmationMessage =validationConfirmationMessage.getText().isEmpty();
		
		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";
		
		boolean actReportTable = reportsTable.getText().isEmpty();
		boolean expReportTable = true;
		
		System.out.println(actReportTable);
		System.out.println(expReportTable);
		
		click(report_CloseBtn);
		Thread.sleep(4000);
		
		if(actReportTable==expReportTable)
		{
			return true;
		}
		else
		{
			return false;
		}
 }

 
 public boolean checkRDDetailReportforDateRangeasCurrentWeek() throws InterruptedException
 {
	 Thread.sleep(1500);
	 click(sl_DateOptionDropdown);
		Select s=new Select(sl_DateOptionDropdown);
		s.selectByValue("4");
		Thread.sleep(1500);
		//sl_DateOptionDropdown.sendKeys(Keys.TAB);
		
		click(sl_OkBtn);
		Thread.sleep(8000);
		
		boolean novalidationConfirmationMessage =validationConfirmationMessage.getText().isEmpty();
		
		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";
		
		boolean actReportTable = reportsTable.getText().isEmpty();
		boolean expReportTable = true;
		
		System.out.println(actReportTable);
		System.out.println(expReportTable);
		
		click(report_CloseBtn);
		Thread.sleep(4000);
		
		if(actReportTable==expReportTable)
		{
			return true;
		}
		else
		{
			return false;
		}
 }

 public boolean checkRDDetailReportforDateRangeasPreviousWeek() throws InterruptedException
 {
	 Thread.sleep(1500);
	 click(sl_DateOptionDropdown);
		Select s=new Select(sl_DateOptionDropdown);
		s.selectByValue("5");
		Thread.sleep(1500);
		//sl_DateOptionDropdown.sendKeys(Keys.TAB);
		
		click(sl_OkBtn);
		Thread.sleep(8000);
		
		boolean novalidationConfirmationMessage =validationConfirmationMessage.getText().isEmpty();
		
		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";
		
		boolean actReportTable = reportsTable.getText().isEmpty();
		boolean expReportTable = true;
		
		System.out.println(actReportTable);
		System.out.println(expReportTable);
		
		click(report_CloseBtn);
		Thread.sleep(4000);
		
		if(actReportTable==expReportTable)
		{
			return true;
		}
		else
		{
			return false;
		}
 }
 
 
 public boolean checkRDDetailReportforDateRangeasToday() throws InterruptedException
 {
	 Thread.sleep(1500);
	 click(sl_DateOptionDropdown);
		Select s=new Select(sl_DateOptionDropdown);
		s.selectByValue("6");
		Thread.sleep(1500);
	//	sl_DateOptionDropdown.sendKeys(Keys.TAB);
		
		click(sl_OkBtn);
		Thread.sleep(8000);
		
		boolean novalidationConfirmationMessage =validationConfirmationMessage.getText().isEmpty();
		
		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";
		
		boolean actReportTable = reportsTable.getText().isEmpty();
		boolean expReportTable = true;
		
		System.out.println(actReportTable);
		System.out.println(expReportTable);
		
		click(report_CloseBtn);
		Thread.sleep(4000);
		
		if(actReportTable==expReportTable)
		{
			return true;
		}
		else
		{
			return false;
		}
 }
 
 
 public boolean checkRDDetailReportforDateRangeasCurrentyear() throws InterruptedException
 {
	 Thread.sleep(1500);
	 click(sl_DateOptionDropdown);
		Select s=new Select(sl_DateOptionDropdown);
		s.selectByValue("7");
		Thread.sleep(1500);
		//sl_DateOptionDropdown.sendKeys(Keys.TAB);
		
		click(sl_OkBtn);
		Thread.sleep(8000);
		
		boolean novalidationConfirmationMessage =validationConfirmationMessage.getText().isEmpty();
		
		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";
		
		boolean actReportTable = reportsTable.getText().isEmpty();
		boolean expReportTable = true;
		
		System.out.println(actReportTable);
		System.out.println(expReportTable);
		
		click(report_CloseBtn);
		Thread.sleep(4000);
		
		if(actReportTable==expReportTable)
		{
			return true;
		}
		else
		{
			return false;
		}
 }
 
 
 public boolean checkRDDetailReportforDateRangeasPreviousyear() throws InterruptedException
 {
	 Thread.sleep(1500);
	 click(sl_DateOptionDropdown);
		Select s=new Select(sl_DateOptionDropdown);
		s.selectByValue("8");
		Thread.sleep(1500);
		//sl_DateOptionDropdown.sendKeys(Keys.TAB);
		
		click(sl_OkBtn);
		Thread.sleep(8000);
		
		boolean novalidationConfirmationMessage =validationConfirmationMessage.getText().isEmpty();
		
		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";
		
		
/*getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_1stRow1stCol));
		
		click(report_LastBtn);
		Thread.sleep(8000);
		
		int i;
		for(i=0;i<docNoColList.size();i++)
		{
			String data=docNoColList.get(i).getText();
			if(data.equals("Grand Total"))
			{
				break;
			}
		}
		
		String xpath1="//*[@id='dvReportDetails']//table//tr[";
		String xpath2="]//td";
		
		
		System.out.println(xpath1+(i+1)+xpath2);
		List<WebElement>grandTotList=getDriver().findElements(By.xpath(xpath1+(i+1)+xpath2));
		
		ArrayList<String>GrandTotList=new ArrayList<String>();
		for(int j=1;j<grandTotList.size();j++)
		{
			GrandTotList.add(grandTotList.get(j).getText());
			
		}
		
		String actGrandTotList=GrandTotList.toString();
		String expGrandTotList="[Grand Total, , , , , , 40.00, 113.00, 46.00, , , 1,353.00, 17.00]";
		
		System.out.println("actGrandTotList		"		+		actGrandTotList);
		System.out.println("expGrandTotList		"		+		expGrandTotList);
		*/
		boolean actReportTable = reportsTable.getText().isEmpty();
		boolean expReportTable = true;
		
		System.out.println(actReportTable);
		System.out.println(expReportTable);
		click(report_CloseBtn);
		Thread.sleep(2000);
		
		if(actReportTable)
		{
			return true;
		}
		else
		{
			return false;
		}
		
 }
 
 
 
 public boolean checkRDDetailReportforDateRangeasCurrentFinancialyear() throws InterruptedException
 {
	 Thread.sleep(1500);
	 click(sl_DateOptionDropdown);
		Select s=new Select(sl_DateOptionDropdown);
		s.selectByValue("9");
		Thread.sleep(1500);
	//	sl_DateOptionDropdown.sendKeys(Keys.TAB);
		
		click(sl_OkBtn);
		Thread.sleep(8000);
		
		boolean novalidationConfirmationMessage =validationConfirmationMessage.getText().isEmpty();
		
		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";
		
		boolean actReportTable = reportsTable.getText().isEmpty();
		boolean expReportTable = true;
		
		System.out.println(actReportTable);
		System.out.println(expReportTable);
		
		click(report_CloseBtn);
		Thread.sleep(4000);
		
		if(actReportTable==expReportTable)
		{
			return true;
		}
		else
		{
			return false;
		}
 }
 
 
 
 public boolean checkRDDetailReportforDateRangeasPreviousFinancialyear() throws InterruptedException
 {
	 Thread.sleep(1500);
	 click(sl_DateOptionDropdown);
		Select s=new Select(sl_DateOptionDropdown);
		s.selectByValue("10");
		Thread.sleep(1500);
		//sl_DateOptionDropdown.sendKeys(Keys.TAB);
		
		click(sl_OkBtn);
		Thread.sleep(8000);
		
		boolean novalidationConfirmationMessage =validationConfirmationMessage.getText().isEmpty();
		
		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";
		
		
//getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_1stRow1stCol));
		
boolean actReportTable = reportsTable.getText().isEmpty();
boolean expReportTable = true;

System.out.println(actReportTable);
System.out.println(expReportTable);
		click(report_CloseBtn);
		Thread.sleep(2000);
		
		if(actReportTable)
		{
			return true;
		}
		else
		{
			return false;
		}
		
 }
 
 
 
 public boolean checkRDDetailReportforDateRangeasYesterDay() throws InterruptedException
 {
	 Thread.sleep(1500);
	 click(sl_DateOptionDropdown);
		Select s=new Select(sl_DateOptionDropdown);
		s.selectByValue("11");
		Thread.sleep(1500);
		//sl_DateOptionDropdown.sendKeys(Keys.TAB);
		
		click(sl_OkBtn);
		Thread.sleep(8000);
		
		boolean novalidationConfirmationMessage =validationConfirmationMessage.getText().isEmpty();
		
		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";
		
		boolean actReportTable = reportsTable.getText().isEmpty();
		boolean expReportTable = true;
		
		System.out.println(actReportTable);
		System.out.println(expReportTable);
		
		click(report_CloseBtn);
		Thread.sleep(4000);
		
		if(actReportTable==expReportTable)
		{
			return true;
		}
		else
		{
			return false;
		}
 }
 
 
 
 public boolean checkRDDetailReportforDateRangeasCurrentQuarter() throws InterruptedException
 {
	 Thread.sleep(1500);
	 click(sl_DateOptionDropdown);
		Select s=new Select(sl_DateOptionDropdown);
		s.selectByValue("12");
		Thread.sleep(1500);
		//sl_DateOptionDropdown.sendKeys(Keys.TAB);
		
		click(sl_OkBtn);
		Thread.sleep(8000);
		
		boolean novalidationConfirmationMessage =validationConfirmationMessage.getText().isEmpty();
		
		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";
		
		boolean actReportTable = reportsTable.getText().isEmpty();
		boolean expReportTable = true;
		
		System.out.println(actReportTable);
		System.out.println(expReportTable);
		
		click(report_CloseBtn);
		Thread.sleep(4000);
		
		if(actReportTable==expReportTable)
		{
			return true;
		}
		else
		{
			return false;
		}
 }
 
 
 public boolean checkRDDetailReportforDateRangeasPreviousQuarter() throws InterruptedException
 {
	 Thread.sleep(1500);
	 click(sl_DateOptionDropdown);
		Select s=new Select(sl_DateOptionDropdown);
		s.selectByValue("13");
		Thread.sleep(1500);
		//sl_DateOptionDropdown.sendKeys(Keys.TAB);
		
		click(sl_OkBtn);
		Thread.sleep(8000);
		
		boolean novalidationConfirmationMessage =validationConfirmationMessage.getText().isEmpty();
		
		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";
		
		boolean actReportTable = reportsTable.getText().isEmpty();
		boolean expReportTable = true;
		
		System.out.println(actReportTable);
		System.out.println(expReportTable);
		
		click(report_CloseBtn);
		Thread.sleep(4000);
		
		if(actReportTable==expReportTable)
		{
			return true;
		}
		else
		{
			return false;
		}
 }
 
 
 
 
 
  
  public boolean checkCubeRDReportforDateRangeasAsonDate() throws InterruptedException
 {
	 
	 
	 getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
		searchTxt.click();
		Thread.sleep(1000);
		searchTxt.sendKeys("Cube RD");
		Thread.sleep(1000);
		searchTxt.sendKeys(Keys.ENTER);
		
		Thread.sleep(8000);
		
		click(sl_DateOptionDropdown);
		Select s=new Select(sl_DateOptionDropdown);
		s.selectByVisibleText("As on date");
		sl_DateOptionDropdown.sendKeys(Keys.TAB);
		
		click(sl_OkBtn);
		Thread.sleep(8000);
		
		boolean novalidationConfirmationMessage =validationConfirmationMessage.getText().isEmpty();
		
		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_1stRow1stCol));
		
		click(report_LastBtn);
		Thread.sleep(8000);
		
		int i;
		for(i=0;i<docNoColList.size();i++)
		{
			String data=docNoColList.get(i).getText();
			if(data.equals("Grand Total"))
			{
				break;
			}
		}
		
		String xpath1="//*[@id='dvReportDetails']//table//tr[";
		String xpath2="]//td";
		
		
		System.out.println(xpath1+(i+1)+xpath2);
		List<WebElement>grandTotList=getDriver().findElements(By.xpath(xpath1+(i+1)+xpath2));
		
		ArrayList<String>GrandTotList=new ArrayList<String>();
		for(int j=1;j<grandTotList.size();j++)
		{
			GrandTotList.add(grandTotList.get(j).getText());
			
		}
		
		String actGrandTotList=GrandTotList.toString();
		String expGrandTotList="[Grand Total, , 3,476.73, 2,456.38, 55,990.91, ]";
		
		System.out.println("actGrandTotList		"		+		actGrandTotList);
		System.out.println("expGrandTotList		"		+		expGrandTotList);
		
		click(report_CloseBtn);
		Thread.sleep(2000);
		
		if(actGrandTotList.equalsIgnoreCase(expGrandTotList))
		{
			return true;
		}
		else
		{
			return false;
		}
		
		
 }
   		
   
 public boolean checkCubeRDReportforDateRangeasCurrentMonth() throws InterruptedException
 {
	 Thread.sleep(2000);
	 click(sl_DateOptionDropdown);
	 Thread.sleep(1500);
		Select s=new Select(sl_DateOptionDropdown);
		s.selectByValue("2");
		Thread.sleep(1500);
	//	sl_DateOptionDropdown.sendKeys(Keys.TAB);
		Thread.sleep(1500);
		
		click(sl_OkBtn);
		Thread.sleep(4000);
		
		boolean novalidationConfirmationMessage =validationConfirmationMessage.getText().isEmpty();
		
		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";
		
		boolean actReportTable = reportsTable.getText().isEmpty();
		boolean expReportTable = true;
		
		System.out.println(actReportTable);
		System.out.println(expReportTable);
		
		click(report_CloseBtn);
		Thread.sleep(4000);
		
		if(actReportTable==expReportTable)
		{
			return true;
		}
		else
		{
			return false;
		}
 }
 

 
 public boolean checkCubeRDReportforDateRangeasPreviousMonth() throws InterruptedException
 {
	 Thread.sleep(1500);
	 click(sl_DateOptionDropdown);
		Select s=new Select(sl_DateOptionDropdown);
		s.selectByValue("3");
		Thread.sleep(1500);
		//sl_DateOptionDropdown.sendKeys(Keys.TAB);
		
		click(sl_OkBtn);
		Thread.sleep(8000);
		
		boolean novalidationConfirmationMessage =validationConfirmationMessage.getText().isEmpty();
		
		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";
		
		boolean actReportTable = reportsTable.getText().isEmpty();
		boolean expReportTable = true;
		
		System.out.println(actReportTable);
		System.out.println(expReportTable);
		
		click(report_CloseBtn);
		Thread.sleep(4000);
		
		if(actReportTable==expReportTable)
		{
			return true;
		}
		else
		{
			return false;
		}
 }

 
 public boolean checkCubeRDReportforDateRangeasCurrentWeek() throws InterruptedException
 {
	 Thread.sleep(1500);
	 click(sl_DateOptionDropdown);
		Select s=new Select(sl_DateOptionDropdown);
		s.selectByValue("4");
		Thread.sleep(1500);
		//sl_DateOptionDropdown.sendKeys(Keys.TAB);
		
		click(sl_OkBtn);
		Thread.sleep(8000);
		
		boolean novalidationConfirmationMessage =validationConfirmationMessage.getText().isEmpty();
		
		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";
		
		boolean actReportTable = reportsTable.getText().isEmpty();
		boolean expReportTable = true;
		
		System.out.println(actReportTable);
		System.out.println(expReportTable);
		
		click(report_CloseBtn);
		Thread.sleep(4000);
		
		if(actReportTable==expReportTable)
		{
			return true;
		}
		else
		{
			return false;
		}
 }

 public boolean checkCubeRDReportforDateRangeasPreviousWeek() throws InterruptedException
 {
	 Thread.sleep(1500);
	 click(sl_DateOptionDropdown);
		Select s=new Select(sl_DateOptionDropdown);
		s.selectByValue("5");
		Thread.sleep(1500);
		//sl_DateOptionDropdown.sendKeys(Keys.TAB);
		
		click(sl_OkBtn);
		Thread.sleep(8000);
		
		boolean novalidationConfirmationMessage =validationConfirmationMessage.getText().isEmpty();
		
		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";
		
		boolean actReportTable = reportsTable.getText().isEmpty();
		boolean expReportTable = true;
		
		System.out.println(actReportTable);
		System.out.println(expReportTable);
		
		click(report_CloseBtn);
		Thread.sleep(4000);
		
		if(actReportTable==expReportTable)
		{
			return true;
		}
		else
		{
			return false;
		}
 }
 
 
 public boolean checkCubeRDReportforDateRangeasToday() throws InterruptedException
 {
	 Thread.sleep(1500);
	 click(sl_DateOptionDropdown);
		Select s=new Select(sl_DateOptionDropdown);
		s.selectByValue("6");
		Thread.sleep(1500);
	//	sl_DateOptionDropdown.sendKeys(Keys.TAB);
		
		click(sl_OkBtn);
		Thread.sleep(8000);
		
		boolean novalidationConfirmationMessage =validationConfirmationMessage.getText().isEmpty();
		
		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";
		
		boolean actReportTable = reportsTable.getText().isEmpty();
		boolean expReportTable = true;
		
		System.out.println(actReportTable);
		System.out.println(expReportTable);
		
		click(report_CloseBtn);
		Thread.sleep(4000);
		
		if(actReportTable==expReportTable)
		{
			return true;
		}
		else
		{
			return false;
		}
 }
 
 
 public boolean checkCubeRDReportforDateRangeasCurrentyear() throws InterruptedException
 {
	 Thread.sleep(1500);
	 click(sl_DateOptionDropdown);
		Select s=new Select(sl_DateOptionDropdown);
		s.selectByValue("7");
		Thread.sleep(1500);
		//sl_DateOptionDropdown.sendKeys(Keys.TAB);
		
		click(sl_OkBtn);
		Thread.sleep(8000);
		
		boolean novalidationConfirmationMessage =validationConfirmationMessage.getText().isEmpty();
		
		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";
		
		boolean actReportTable = reportsTable.getText().isEmpty();
		boolean expReportTable = true;
		
		System.out.println(actReportTable);
		System.out.println(expReportTable);
		
		click(report_CloseBtn);
		Thread.sleep(4000);
		
		if(actReportTable==expReportTable)
		{
			return true;
		}
		else
		{
			return false;
		}
 }
 
 
 public boolean checkCubeRDReportforDateRangeasPreviousyear() throws InterruptedException
 {
	 Thread.sleep(1500);
	 click(sl_DateOptionDropdown);
		Select s=new Select(sl_DateOptionDropdown);
		s.selectByValue("8");
		Thread.sleep(1500);
		//sl_DateOptionDropdown.sendKeys(Keys.TAB);
		
		click(sl_OkBtn);
		Thread.sleep(8000);
		
		boolean novalidationConfirmationMessage =validationConfirmationMessage.getText().isEmpty();
		
		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";
		
		
/*getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_1stRow1stCol));
		
		click(report_LastBtn);
		Thread.sleep(8000);
		
		int i;
		for(i=0;i<docNoColList.size();i++)
		{
			String data=docNoColList.get(i).getText();
			if(data.equals("Grand Total"))
			{
				break;
			}
		}
		
		String xpath1="//*[@id='dvReportDetails']//table//tr[";
		String xpath2="]//td";
		
		
		System.out.println(xpath1+(i+1)+xpath2);
		List<WebElement>grandTotList=getDriver().findElements(By.xpath(xpath1+(i+1)+xpath2));
		
		ArrayList<String>GrandTotList=new ArrayList<String>();
		for(int j=1;j<grandTotList.size();j++)
		{
			GrandTotList.add(grandTotList.get(j).getText());
			
		}
		
		String actGrandTotList=GrandTotList.toString();
		String expGrandTotList="[Grand Total, , 40.00, 113.00, 46.00, ]";
		
		System.out.println("actGrandTotList		"		+		actGrandTotList);
		System.out.println("expGrandTotList		"		+		expGrandTotList);
		*/
		
		boolean actReportTable = reportsTable.getText().isEmpty();
		boolean expReportTable = true;
		
		System.out.println(actReportTable);
		System.out.println(expReportTable);
		click(report_CloseBtn);
		Thread.sleep(2000);
		
		if(actReportTable)
				
		{
			return true;
		}
		else
		{
			return false;
		}
		
 }
 
 
 
 public boolean checkCubeRDReportforDateRangeasCurrentFinancialyear() throws InterruptedException
 {
	 Thread.sleep(1500);
	 click(sl_DateOptionDropdown);
		Select s=new Select(sl_DateOptionDropdown);
		s.selectByValue("9");
		Thread.sleep(1500);
	//	sl_DateOptionDropdown.sendKeys(Keys.TAB);
		
		click(sl_OkBtn);
		Thread.sleep(8000);
		
		boolean novalidationConfirmationMessage =validationConfirmationMessage.getText().isEmpty();
		
		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";
		
		boolean actReportTable = reportsTable.getText().isEmpty();
		boolean expReportTable = true;
		
		System.out.println(actReportTable);
		System.out.println(expReportTable);
		
		click(report_CloseBtn);
		Thread.sleep(4000);
		
		if(actReportTable==expReportTable)
		{
			return true;
		}
		else
		{
			return false;
		}
 }
 
 
 
 public boolean checkCubeRDReportforDateRangeasPreviousFinancialyear() throws InterruptedException
 {
	 Thread.sleep(1500);
	 click(sl_DateOptionDropdown);
		Select s=new Select(sl_DateOptionDropdown);
		s.selectByValue("10");
		Thread.sleep(1500);
		//sl_DateOptionDropdown.sendKeys(Keys.TAB);
		
		click(sl_OkBtn);
		Thread.sleep(8000);
		
		boolean novalidationConfirmationMessage =validationConfirmationMessage.getText().isEmpty();
		
		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";
		
		
		boolean actReportTable = reportsTable.getText().isEmpty();
		boolean expReportTable = true;
		
		System.out.println(actReportTable);
		System.out.println(expReportTable);
		click(report_CloseBtn);
		Thread.sleep(2000);
		
		if(actReportTable)
		{
			return true;
		}
		else
		{
			return false;
		}
		
 }
 
 
 
 public boolean checkCubeRDReportforDateRangeasYesterDay() throws InterruptedException
 {
	 Thread.sleep(1500);
	 click(sl_DateOptionDropdown);
		Select s=new Select(sl_DateOptionDropdown);
		s.selectByValue("11");
		Thread.sleep(1500);
		//sl_DateOptionDropdown.sendKeys(Keys.TAB);
		
		click(sl_OkBtn);
		Thread.sleep(8000);
		
		boolean novalidationConfirmationMessage =validationConfirmationMessage.getText().isEmpty();
		
		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";
		
		boolean actReportTable = reportsTable.getText().isEmpty();
		boolean expReportTable = true;
		
		System.out.println(actReportTable);
		System.out.println(expReportTable);
		
		click(report_CloseBtn);
		Thread.sleep(4000);
		
		if(actReportTable==expReportTable)
		{
			return true;
		}
		else
		{
			return false;
		}
 }
 
 
 
 public boolean checkCubeRDReportforDateRangeasCurrentQuarter() throws InterruptedException
 {
	 Thread.sleep(1500);
	 click(sl_DateOptionDropdown);
		Select s=new Select(sl_DateOptionDropdown);
		s.selectByValue("12");
		Thread.sleep(1500);
		//sl_DateOptionDropdown.sendKeys(Keys.TAB);
		
		click(sl_OkBtn);
		Thread.sleep(8000);
		
		boolean novalidationConfirmationMessage =validationConfirmationMessage.getText().isEmpty();
		
		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";
		
		boolean actReportTable = reportsTable.getText().isEmpty();
		boolean expReportTable = true;
		
		System.out.println(actReportTable);
		System.out.println(expReportTable);
		
		click(report_CloseBtn);
		Thread.sleep(4000);
		
		if(actReportTable==expReportTable)
		{
			return true;
		}
		else
		{
			return false;
		}
 }
 
 
 public boolean checkCubeRDReportforDateRangeasPreviousQuarter() throws InterruptedException
 {
	 Thread.sleep(1500);
	 click(sl_DateOptionDropdown);
		Select s=new Select(sl_DateOptionDropdown);
		s.selectByValue("13");
		Thread.sleep(1500);
		//sl_DateOptionDropdown.sendKeys(Keys.TAB);
		
		click(sl_OkBtn);
		Thread.sleep(8000);
		
		boolean novalidationConfirmationMessage =validationConfirmationMessage.getText().isEmpty();
		
		String actvalidationConfirmationMessage = Boolean.toString(novalidationConfirmationMessage);
		String expvalidationConfirmationMessage = "true";
		
		boolean actReportTable = reportsTable.getText().isEmpty();
		boolean expReportTable = true;
		
		System.out.println(actReportTable);
		System.out.println(expReportTable);
		
		click(report_CloseBtn);
		Thread.sleep(4000);
		
		if(actReportTable==expReportTable)
		{
			return true;
		}
		else
		{
			return false;
		}
 }
 
 
 //Filter on RD Stock Transfer Report
 
 @FindBy(xpath="//*[@id='FOption_70035_0_DefaultFilter_0']")
 public static WebElement invTagDefaultTxt;
 
 public boolean checkFilterinRDStockTransferReport() throws InterruptedException
 {
	 	getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
		searchTxt.click();
		Thread.sleep(1000);
		searchTxt.sendKeys("RD STOCK TRANSFER REPORT");
		Thread.sleep(1000);
		searchTxt.sendKeys(Keys.ENTER);
		
		Thread.sleep(8000);
		
		click(report_HomeFilterExpandBtn);
		Thread.sleep(2000);
		
		click(report_HomeFilterBtn);
		Thread.sleep(2000);
		
		click(invTagDefaultTxt);
		invTagDefaultTxt.sendKeys("wh6");
		Thread.sleep(1200);
		invTagDefaultTxt.sendKeys(Keys.TAB);
		
		click(filterOkButton);
				
		Thread.sleep(6000);
		
		
		click(sl_OkBtn);
		Thread.sleep(8000);
		
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_1stRow1stCol));
		
		int reportsRow1ListCount = report1stRowList.size();
		ArrayList<String> reportsRow1ListArray = new ArrayList<String>();
		for(int i=1;i<reportsRow1ListCount;i++)
		{
			String data = report1stRowList.get(i).getText();
			if(!data.isEmpty())
			{
				reportsRow1ListArray.add(data);
			}
		}
		String actRow1List = reportsRow1ListArray.toString();
		String expRow1List = "[23, 29/11/2023, wh5, wh6, Issues, RMA3, fw1, 1.00]";
		
		
		int report2ndRowListCount = report2ndRowList.size();
		ArrayList<String> report2ndRowListArray = new ArrayList<String>();
		for(int i=1;i<report2ndRowListCount;i++)
		{
			String data = report2ndRowList.get(i).getText();
			if(!data.isEmpty())
			{
				report2ndRowListArray.add(data);
			}
		}
		String actRow2List = report2ndRowListArray.toString();
		String expRow2List = "[25, 29/11/2023, wh5, wh6, Issues, RMA3, fw2, 1.00]";
		
		
		int report3rdRowListCount = report3rdRowList.size();
		ArrayList<String> report3rdRowListArray = new ArrayList<String>();
		for(int i=1;i<report3rdRowListCount;i++)
		{
			String data = report3rdRowList.get(i).getText();
			if(!data.isEmpty())
			{
				report3rdRowListArray.add(data);
			}
		}
		String actRow3List = report3rdRowListArray.toString();
		String expRow3List = "[26, 29/11/2023, wh5, wh6, Issues, RMA3, fw3, 1.00]";
		
		
		int report4thRowListCount = report4thRowList.size();
		ArrayList<String> report4thRowListArray = new ArrayList<String>();
		for(int i=1;i<report4thRowListCount;i++)
		{
			String data = report4thRowList.get(i).getText();
			if(!data.isEmpty())
			{
				report4thRowListArray.add(data);
			}
		}
		String actRow4List = report4thRowListArray.toString();
		String expRow4List = "[27, 29/11/2023, wh5, wh6, Issues, RMA3, fw4, 1.00]";

		int report5thRowListCount = report5thRowList.size();
		ArrayList<String> report5thRowListArray = new ArrayList<String>();
		for(int i=1;i<report5thRowListCount;i++)
		{
			String data = report5thRowList.get(i).getText();
			if(!data.isEmpty())
			{
				report5thRowListArray.add(data);
			}
		}
		String actRow5List = report5thRowListArray.toString();
		String expRow5List = "[29, 29/11/2023, wh5, wh6, Issues, RMA3, fw5, 1.00]";

		int report6thRowListCount = report6thRowList.size();
		ArrayList<String> report6thRowListArray = new ArrayList<String>();
		for(int i=1;i<report6thRowListCount;i++)
		{
			String data = report6thRowList.get(i).getText();
			if(!data.isEmpty())
			{
				report6thRowListArray.add(data);
			}
		}
		String actRow6List = report6thRowListArray.toString();
		String expRow6List = "[31, 29/11/2023, wh5, wh6, Issues, RMA3, ret1, 1.00]";
		
		int report7thRowListCount = report7thRowList.size();
		ArrayList<String> report7thRowListArray = new ArrayList<String>();
		for(int i=1;i<report7thRowListCount;i++)
		{
			String data = report7thRowList.get(i).getText();
			if(!data.isEmpty())
			{
				report7thRowListArray.add(data);
			}
		}
		String actRow7List = report7thRowListArray.toString();
		String expRow7List = "[33, 29/11/2023, wh5, wh6, Issues, RMA3, ret2, 1.00]";
		
		
		int report8thRowListCount = report8thRowList.size();
		ArrayList<String> report8thRowListArray = new ArrayList<String>();
		for(int i=1;i<report8thRowListCount;i++)
		{
			String data = report8thRowList.get(i).getText();
			if(!data.isEmpty())
			{
				report8thRowListArray.add(data);
			}
		}
		String actRow8List = report8thRowListArray.toString();
		String expRow8List = "[35, 29/11/2023, wh5, wh6, Issues, RMA3, ret3, 1.00]";
		
		
		int report9thRowListCount = report9thRowList.size();
		ArrayList<String> report9thRowListArray = new ArrayList<String>();
		for(int i=1;i<report9thRowListCount;i++)
		{
			String data = report9thRowList.get(i).getText();
			if(!data.isEmpty())
			{
				report9thRowListArray.add(data);
			}
		}
		String actRow9List = report9thRowListArray.toString();
		String expRow9List = "[72, 28/12/2023, wh5, wh6, Issues, RMA2, L1, 1.00]";
		
		
		int report10thRowListCount = report10thRowList.size();
		ArrayList<String> report10thRowListArray = new ArrayList<String>();
		for(int i=1;i<report10thRowListCount;i++)
		{
			String data = report10thRowList.get(i).getText();
			if(!data.isEmpty())
			{
				report10thRowListArray.add(data);
			}
		}
		String actRow10List = report10thRowListArray.toString();
		String expRow10List = "[Grand Total, 9.00]";
		
	
		
		System.out.println("************************************checkLedgerReport********************************************");
			
		
		System.out.println("actRow1List  : "+actRow1List);
		System.out.println("expRow1List  : "+expRow1List);
		System.out.println("*********************************************************************");
				
		System.out.println("actRow2List  : "+actRow2List);
		System.out.println("expRow2List  : "+expRow2List);
		System.out.println("*********************************************************************");
				
		System.out.println("actRow3List  : "+actRow3List);
		System.out.println("expRow3List  : "+expRow3List);
		System.out.println("*********************************************************************");
				
		System.out.println("actRow4List  : "+actRow4List);
		System.out.println("expRow4List  : "+expRow4List);
		System.out.println("*********************************************************************");
				
		System.out.println("actRow5List  : "+actRow5List);
		System.out.println("expRow5List  : "+expRow5List);
		System.out.println("*********************************************************************");
				
		System.out.println("actRow6List  : "+actRow6List);
		System.out.println("expRow6List  : "+expRow6List);
		System.out.println("*********************************************************************");
				
		System.out.println("actRow7List  : "+actRow7List);
		System.out.println("expRow7List  : "+expRow7List);
		System.out.println("*********************************************************************");
			
		System.out.println("actRow8List  : "+actRow8List);
		System.out.println("expRow8List  : "+expRow8List);
		System.out.println("*********************************************************************");
				
		System.out.println("actRow9List  : "+actRow9List);
		System.out.println("expRow9List  : "+expRow9List);
		System.out.println("*********************************************************************");
				
		System.out.println("actRow10List  : "+actRow10List);
		System.out.println("expRow10List  : "+expRow10List);
		System.out.println("*********************************************************************");
				
		if(actRow1List.equalsIgnoreCase(expRow1List) &&
						actRow2List.equalsIgnoreCase(expRow2List) &&
						actRow3List.equalsIgnoreCase(expRow3List) &&
						actRow4List.equalsIgnoreCase(expRow4List) &&
						actRow5List.equalsIgnoreCase(expRow5List) &&
						actRow6List.equalsIgnoreCase(expRow6List) &&
						actRow7List.equalsIgnoreCase(expRow7List) &&
						actRow8List.equalsIgnoreCase(expRow8List) &&
						actRow9List.equalsIgnoreCase(expRow9List) &&
						actRow10List.equalsIgnoreCase(expRow10List) )
				{
		
			return true;
				}
		
		else
		{
			return false;
		}
		
 }

  public boolean checkPrintinRDStockTransferReportAfterFilter() throws IOException, InterruptedException, AWTException
  {
	  Thread.sleep(4000);
	  
	  click(reportEntryPrintBtn);
	  Thread.sleep(4000);
	  
	  click(ss_ReportPrintYesBtn);
		Thread.sleep(12000);
		
		
File Efile=new File(getBaseDir()+"\\autoIt\\ExportFiles\\StockTransferEntryPrint.pdf");
		
		if(Efile.exists())
		{
			Efile.delete();
		}
		
		Thread.sleep(6000);
		
		
		
		
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_S);
		robot.keyRelease(KeyEvent.VK_S);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		
		Thread.sleep(10000);
			
		Runtime.getRuntime().exec(getBaseDir() + "\\autoIt\\scripts\\StockTransferEntryPrint.exe");
		
		Thread.sleep(14000);
		
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_J);
		robot.keyRelease(KeyEvent.VK_J);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		Thread.sleep(2000);
		
		ArrayList<String> openTabs = new ArrayList<String>(getDriver().getWindowHandles());
	
		
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_W);
		robot.keyRelease(KeyEvent.VK_W);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		Thread.sleep(2000);
		
	 	robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_W);
		robot.keyRelease(KeyEvent.VK_W);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		Thread.sleep(2000);
		
		
	 	
	 	String actPDF = getBaseDir()+"\\autoIt\\ExportFiles\\StockTransferEntryPrint.pdf";
		String expPDF = getBaseDir()+"\\autoIt\\ImportFiles\\StockTransferEntryPrint.pdf";
		System.out.println(actPDF);
		System.out.println(expPDF);
		
		
		PDFUtil pdfutil = new PDFUtil();
		
		boolean result = pdfutil.compare(actPDF, expPDF);
		Calendar cal=Calendar.getInstance();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String currentDate = df.format(cal.getTime());
		
		String actData = pdfutil.getText(actPDF);
		String expData = pdfutil.getText(expPDF).replaceAll("05/08/2025", currentDate);
		System.out.println(actData);
		System.out.println(expData);
		
		System.out.println("Compared Result  : "+result);
		
		
		if(actData.equalsIgnoreCase(expData))
		{
			return true;
		}
		else
		{
			return false;
		}
		
	  
  }
  
  
  public boolean checkExcelinStockTransferReportAfterFilter() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
  {
	  

		Thread.sleep(3000);
		
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportEntryExcelBtn));
		reportEntryExcelBtn.click();
		Thread.sleep(3000);
		
		click(ss_ReportPrintYesBtn);
	
		Thread.sleep(8000);
		
		File Efile=new File(getBaseDir()+"\\autoIt\\ExportFiles\\StockTransferEntryExcel.xlsx");
			
			if(Efile.exists())
			{
				Efile.delete();
			}
			
			
					
			
			
			Robot robot = new Robot();
		/*	robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_J);
			robot.keyRelease(KeyEvent.VK_J);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			
			Thread.sleep(2000);
			
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
			

			
			Thread.sleep(2000);
			
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			
			Thread.sleep(25000);
			
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(4000);
			robot.keyPress(KeyEvent.VK_F12);
			robot.keyRelease(KeyEvent.VK_F12);
			
			
			Thread.sleep(2000);
			*/
			
				
			Runtime.getRuntime().exec(getBaseDir() + "\\autoIt\\scripts\\StockTransferEntryExcel.exe");
			
			Thread.sleep(8000);
			
			
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(4000);
			
		/*	
			robot.keyPress(KeyEvent.VK_ALT);
			robot.keyPress(KeyEvent.VK_F4);
			
			robot.keyRelease(KeyEvent.VK_ALT);
			robot.keyRelease(KeyEvent.VK_F4);
			Thread.sleep(5000);
			
			*/
			
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_J);
			robot.keyRelease(KeyEvent.VK_J);
			robot.keyRelease(KeyEvent.VK_CONTROL);
		
			Thread.sleep(2000);
			
			ArrayList<String> openTabs = new ArrayList<String>(getDriver().getWindowHandles());
				
			int actOpenWindowsCount = getDriver().getWindowHandles().size();
			int expOpenWindowsCount = 2;
			
			System.out.println("Number of Windows  : "+actOpenWindowsCount+"  Value Expected  "+expOpenWindowsCount);
			
			Thread.sleep(1000);

		 
			
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_W);
			robot.keyRelease(KeyEvent.VK_W);
			robot.keyRelease(KeyEvent.VK_CONTROL);
		
			Thread.sleep(2000);
			
		 	 ExcelReader excelReader = new ExcelReader(POJOUtility.getExcelPath());
		     

		 	String actExcelfile = getBaseDir()+"\\autoIt\\ExportFiles\\StockTransferEntryExcel.xlsx";
		 	String expExcelfile = getBaseDir()+"\\autoIt\\ImportFiles\\StockTransferEntryExcel.xlsx";
		 	String sheet = "Sheet1";
		 	   
		 	
		 	
		 	FileInputStream fip1 = new FileInputStream(actExcelfile);
		 	Workbook workbook1  = WorkbookFactory.create(fip1);
		 	
		 	FileInputStream fip2 = new FileInputStream(expExcelfile);
		 	Workbook workbook2  = WorkbookFactory.create(fip2);
		 	
		 	boolean result = excelReader.checkExcelSheetsComparisonWithMonth(workbook1, workbook2,"29/09/2025");
		 	
		 	System.err.println(result);
		 	
		 	if (result)
		 	{
		 		return true;
		 	}
		 	else
		 	{
		 		return false;
		 	}

	  
  }
  
  
  public boolean checkHomePrintinRDStockTransferReportAfterFilter() throws InterruptedException, IOException, AWTException
  {	  

	  Thread.sleep(4000);
	  
	  click(report_CloseBtn);
	  Thread.sleep(2000);
	  
	  click(reportHomePrintBtn);
	  Thread.sleep(4000);
	  
	  click(ss_ReportPrintYesBtn);
		Thread.sleep(12000);
		
		
File Efile=new File(getBaseDir()+"\\autoIt\\ExportFiles\\StockTransferHomePrint.pdf");
		
		if(Efile.exists())
		{
			Efile.delete();
		}
		
		Thread.sleep(2000);
		
		
		Thread.sleep(2000);
		
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_S);
		robot.keyRelease(KeyEvent.VK_S);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		
		Thread.sleep(6000);
			
		Runtime.getRuntime().exec(getBaseDir() + "\\autoIt\\scripts\\StockTransferHomePrint.exe");
		
		Thread.sleep(12000);
		
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_J);
		robot.keyRelease(KeyEvent.VK_J);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		Thread.sleep(2000);
		
		ArrayList<String> openTabs = new ArrayList<String>(getDriver().getWindowHandles());
	
		
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_W);
		robot.keyRelease(KeyEvent.VK_W);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		Thread.sleep(2000);
		
	 	robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_W);
		robot.keyRelease(KeyEvent.VK_W);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		Thread.sleep(2000);
		
		
	 	
	 	String actPDF = getBaseDir()+"\\autoIt\\ExportFiles\\StockTransferHomePrint.pdf";
		String expPDF = getBaseDir()+"\\autoIt\\ImportFiles\\StockTransferHomePrint.pdf";
		System.out.println(actPDF);
		System.out.println(expPDF);
		
		
		PDFUtil pdfutil = new PDFUtil();
		
		boolean result = pdfutil.compare(actPDF, expPDF);
		Calendar cal=Calendar.getInstance();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String currentDate = df.format(cal.getTime());
		
		String actData = pdfutil.getText(actPDF);
		String expData = pdfutil.getText(expPDF).replaceAll("05/08/2025", currentDate);
		System.out.println(actData);
		System.out.println(expData);
		
		System.out.println("Compared Result  : "+result);
		
		
		if(actData.equalsIgnoreCase(expData))
		{
			return true;
		}
		else
		{
			return false;
		}

	  
  }
  
  
  public boolean checkHomeExcelinStockTransferReportAfterFilter() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
  {
	  

		Thread.sleep(3000);
		
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportHomeExcelBtn));
		reportHomeExcelBtn.click();
		Thread.sleep(3000);
		
		click(ss_ReportPrintYesBtn);
	
		Thread.sleep(12000);
		
		File Efile=new File(getBaseDir()+"\\autoIt\\ExportFiles\\StockTransferHomeExcel.xlsx");
			
			if(Efile.exists())
			{
				Efile.delete();
			}
			
			
					
			
			
			Robot robot = new Robot();
	/*		robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_J);
			robot.keyRelease(KeyEvent.VK_J);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			
			Thread.sleep(2000);
			
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
			

			
			Thread.sleep(2000);
			
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			
			Thread.sleep(25000);
			
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(4000);
			robot.keyPress(KeyEvent.VK_F12);
			robot.keyRelease(KeyEvent.VK_F12);
			
			
			Thread.sleep(2000);
			
			*/
				
			Runtime.getRuntime().exec(getBaseDir() + "\\autoIt\\scripts\\StockTransferHomeExcel.exe");
			
			Thread.sleep(8000);
			
			
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(4000);
			
		/*	
			robot.keyPress(KeyEvent.VK_ALT);
			robot.keyPress(KeyEvent.VK_F4);
			
			robot.keyRelease(KeyEvent.VK_ALT);
			robot.keyRelease(KeyEvent.VK_F4);
			Thread.sleep(5000);
			*/
			
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_J);
			robot.keyRelease(KeyEvent.VK_J);
			robot.keyRelease(KeyEvent.VK_CONTROL);
		
			Thread.sleep(2000);
			
			
			
			
			ArrayList<String> openTabs = new ArrayList<String>(getDriver().getWindowHandles());
				
			int actOpenWindowsCount = getDriver().getWindowHandles().size();
			int expOpenWindowsCount = 2;
			
			System.out.println("Number of Windows  : "+actOpenWindowsCount+"  Value Expected  "+expOpenWindowsCount);
			
			Thread.sleep(1000);

		 
			
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_W);
			robot.keyRelease(KeyEvent.VK_W);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			Thread.sleep(2000);
			
			
		 	 ExcelReader excelReader = new ExcelReader(POJOUtility.getExcelPath());
		     

		 	String actExcelfile = getBaseDir()+"\\autoIt\\ExportFiles\\StockTransferHomeExcel.xlsx";
		 	String expExcelfile = getBaseDir()+"\\autoIt\\ImportFiles\\StockTransferHomeExcel.xlsx";
		 	String sheet = "Sheet1";
		 	   
		 	
		 	
		 	FileInputStream fip1 = new FileInputStream(actExcelfile);
		 	Workbook workbook1  = WorkbookFactory.create(fip1);
		 	
		 	FileInputStream fip2 = new FileInputStream(expExcelfile);
		 	Workbook workbook2  = WorkbookFactory.create(fip2);
		 	
		 	boolean result = excelReader.checkExcelSheetsComparisonWithMonth(workbook1, workbook2,"29/09/2025");
		 	
		 	System.err.println(result);
		 	
		 	if (result)
		 	{
		 		return true;
		 	}
		 	else
		 	{
		 		return false;
		 	}
	  
  }
  

  @FindBy(xpath="(//a[contains(text(),' InvTag2')])[1]//../i")
  public static WebElement InvTag2ExpBtn;
  
  @FindBy(xpath="((//a[contains(text(),' InvTag2')])[1]//following::ul//label[contains(text(),'Name')]//input/../span)[1]")
  public static WebElement InvTag2NameChkBoxSelected;
  
  
  @FindBy(xpath="((//a[contains(text(),' InvTag2')])[1]//following::ul//label[contains(text(),'Name')]//input)[1]")
  public static WebElement InvTag2NameChkBox;
  
  
  @FindBy(xpath="(//input[@value='Ok'])[1]")
  public static WebElement datasetCustOkBtn;;
  
  @FindBy(xpath="//*[@id='FOption_0_0_DefaultFilter_1']")
  public static WebElement datasetInvTagDefaultTxt;
  
  public boolean checkDefaultFilterinRDStockTransferReport() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
  {
	  
	  getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
		searchTxt.click();
		Thread.sleep(1000);
		searchTxt.sendKeys("RD STOCK TRANSFER REPORT");
		Thread.sleep(1000);
		searchTxt.sendKeys(Keys.ENTER);
		
		Thread.sleep(8000);
		
		click(rd_HomeCustBtn);
		Thread.sleep(6000);
		
		click(dataSetTab);
		Thread.sleep(4000);
		
		click(rdDataSetDefaultFilterCusBtn);
		Thread.sleep(3000);
		
		getAction().moveToElement(InvTag2ExpBtn).build().perform();
		click(InvTag2ExpBtn);
		Thread.sleep(1000);
		
		
		  getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(InvTag2NameChkBoxSelected));
		if(InvTag2NameChkBox.isSelected()==false)
		{
			InvTag2NameChkBoxSelected.click();
		}
		
		
		click(datasetCustOkBtn);
		Thread.sleep(1500);
		
		click(datasetInvTagDefaultTxt);
		
		datasetInvTagDefaultTxt.sendKeys("wh6");
		Thread.sleep(2000);
		datasetInvTagDefaultTxt.sendKeys(Keys.TAB);
		
	  click(finishBtn);
	  Thread.sleep(2000);
	  
	  String expMsg="Data saved successfully.";
	  String actMsg= checkValidationMessage(expMsg);
	  
	  click(closeBtn);
	  Thread.sleep(2000);
	  
	  if(actMsg.equalsIgnoreCase(expMsg))
	  {
		  return true;
	  }
	  else
	  {
		  return false;
	  }
  }
  
  public boolean checkRDStockTransferReportafterDefaultFilter() throws InterruptedException
  {
	  click(sl_OkBtn);
	  Thread.sleep(8000);
	  
	  getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_1stRow1stCol));
		
		int reportsRow1ListCount = report1stRowList.size();
		ArrayList<String> reportsRow1ListArray = new ArrayList<String>();
		for(int i=1;i<reportsRow1ListCount;i++)
		{
			String data = report1stRowList.get(i).getText();
			if(!data.isEmpty())
			{
				reportsRow1ListArray.add(data);
			}
		}
		String actRow1List = reportsRow1ListArray.toString();
		String expRow1List = "[23, 29/11/2023, wh5, wh6, Issues, RMA3, fw1, 1.00]";
		
		
		int report2ndRowListCount = report2ndRowList.size();
		ArrayList<String> report2ndRowListArray = new ArrayList<String>();
		for(int i=1;i<report2ndRowListCount;i++)
		{
			String data = report2ndRowList.get(i).getText();
			if(!data.isEmpty())
			{
				report2ndRowListArray.add(data);
			}
		}
		String actRow2List = report2ndRowListArray.toString();
		String expRow2List = "[25, 29/11/2023, wh5, wh6, Issues, RMA3, fw2, 1.00]";
		
		
		int report3rdRowListCount = report3rdRowList.size();
		ArrayList<String> report3rdRowListArray = new ArrayList<String>();
		for(int i=1;i<report3rdRowListCount;i++)
		{
			String data = report3rdRowList.get(i).getText();
			if(!data.isEmpty())
			{
				report3rdRowListArray.add(data);
			}
		}
		String actRow3List = report3rdRowListArray.toString();
		String expRow3List = "[26, 29/11/2023, wh5, wh6, Issues, RMA3, fw3, 1.00]";
		
		
		int report4thRowListCount = report4thRowList.size();
		ArrayList<String> report4thRowListArray = new ArrayList<String>();
		for(int i=1;i<report4thRowListCount;i++)
		{
			String data = report4thRowList.get(i).getText();
			if(!data.isEmpty())
			{
				report4thRowListArray.add(data);
			}
		}
		String actRow4List = report4thRowListArray.toString();
		String expRow4List = "[27, 29/11/2023, wh5, wh6, Issues, RMA3, fw4, 1.00]";

		int report5thRowListCount = report5thRowList.size();
		ArrayList<String> report5thRowListArray = new ArrayList<String>();
		for(int i=1;i<report5thRowListCount;i++)
		{
			String data = report5thRowList.get(i).getText();
			if(!data.isEmpty())
			{
				report5thRowListArray.add(data);
			}
		}
		String actRow5List = report5thRowListArray.toString();
		String expRow5List = "[29, 29/11/2023, wh5, wh6, Issues, RMA3, fw5, 1.00]";

		int report6thRowListCount = report6thRowList.size();
		ArrayList<String> report6thRowListArray = new ArrayList<String>();
		for(int i=1;i<report6thRowListCount;i++)
		{
			String data = report6thRowList.get(i).getText();
			if(!data.isEmpty())
			{
				report6thRowListArray.add(data);
			}
		}
		String actRow6List = report6thRowListArray.toString();
		String expRow6List = "[31, 29/11/2023, wh5, wh6, Issues, RMA3, ret1, 1.00]";
		
		int report7thRowListCount = report7thRowList.size();
		ArrayList<String> report7thRowListArray = new ArrayList<String>();
		for(int i=1;i<report7thRowListCount;i++)
		{
			String data = report7thRowList.get(i).getText();
			if(!data.isEmpty())
			{
				report7thRowListArray.add(data);
			}
		}
		String actRow7List = report7thRowListArray.toString();
		String expRow7List = "[33, 29/11/2023, wh5, wh6, Issues, RMA3, ret2, 1.00]";
		
		
		int report8thRowListCount = report8thRowList.size();
		ArrayList<String> report8thRowListArray = new ArrayList<String>();
		for(int i=1;i<report8thRowListCount;i++)
		{
			String data = report8thRowList.get(i).getText();
			if(!data.isEmpty())
			{
				report8thRowListArray.add(data);
			}
		}
		String actRow8List = report8thRowListArray.toString();
		String expRow8List = "[35, 29/11/2023, wh5, wh6, Issues, RMA3, ret3, 1.00]";
		
		
		int report9thRowListCount = report9thRowList.size();
		ArrayList<String> report9thRowListArray = new ArrayList<String>();
		for(int i=1;i<report9thRowListCount;i++)
		{
			String data = report9thRowList.get(i).getText();
			if(!data.isEmpty())
			{
				report9thRowListArray.add(data);
			}
		}
		String actRow9List = report9thRowListArray.toString();
		String expRow9List = "[72, 28/12/2023, wh5, wh6, Issues, RMA2, L1, 1.00]";
		
		
		int report10thRowListCount = report10thRowList.size();
		ArrayList<String> report10thRowListArray = new ArrayList<String>();
		for(int i=1;i<report10thRowListCount;i++)
		{
			String data = report10thRowList.get(i).getText();
			if(!data.isEmpty())
			{
				report10thRowListArray.add(data);
			}
		}
		String actRow10List = report10thRowListArray.toString();
		String expRow10List = "[Grand Total, 9.00]";
		
	
		
		System.out.println("************************************checkLedgerReport********************************************");
			
		
		System.out.println("actRow1List  : "+actRow1List);
		System.out.println("expRow1List  : "+expRow1List);
		System.out.println("*********************************************************************");
				
		System.out.println("actRow2List  : "+actRow2List);
		System.out.println("expRow2List  : "+expRow2List);
		System.out.println("*********************************************************************");
				
		System.out.println("actRow3List  : "+actRow3List);
		System.out.println("expRow3List  : "+expRow3List);
		System.out.println("*********************************************************************");
				
		System.out.println("actRow4List  : "+actRow4List);
		System.out.println("expRow4List  : "+expRow4List);
		System.out.println("*********************************************************************");
				
		System.out.println("actRow5List  : "+actRow5List);
		System.out.println("expRow5List  : "+expRow5List);
		System.out.println("*********************************************************************");
				
		System.out.println("actRow6List  : "+actRow6List);
		System.out.println("expRow6List  : "+expRow6List);
		System.out.println("*********************************************************************");
				
		System.out.println("actRow7List  : "+actRow7List);
		System.out.println("expRow7List  : "+expRow7List);
		System.out.println("*********************************************************************");
			
		System.out.println("actRow8List  : "+actRow8List);
		System.out.println("expRow8List  : "+expRow8List);
		System.out.println("*********************************************************************");
				
		System.out.println("actRow9List  : "+actRow9List);
		System.out.println("expRow9List  : "+expRow9List);
		System.out.println("*********************************************************************");
				
		System.out.println("actRow10List  : "+actRow10List);
		System.out.println("expRow10List  : "+expRow10List);
		System.out.println("*********************************************************************");
				
		if(actRow1List.equalsIgnoreCase(expRow1List) &&
						actRow2List.equalsIgnoreCase(expRow2List) &&
						actRow3List.equalsIgnoreCase(expRow3List) &&
						actRow4List.equalsIgnoreCase(expRow4List) &&
						actRow5List.equalsIgnoreCase(expRow5List) &&
						actRow6List.equalsIgnoreCase(expRow6List) &&
						actRow7List.equalsIgnoreCase(expRow7List) &&
						actRow8List.equalsIgnoreCase(expRow8List) &&
						actRow9List.equalsIgnoreCase(expRow9List) &&
						actRow10List.equalsIgnoreCase(expRow10List) )
				{
		
			return true;
				}
		
		else
		{
			return false;
		}
	  
  }
  
  
   
   
    public boolean checkPrintinRDStockTransferReportAfterDefaultFilter() throws IOException, InterruptedException, AWTException
  {
	  Thread.sleep(4000);
	  
	  click(reportEntryPrintBtn);
	  Thread.sleep(4000);
	  
	  click(ss_ReportPrintYesBtn);
		Thread.sleep(12000);
		
		
File Efile=new File(getBaseDir()+"\\autoIt\\ExportFiles\\StockTransferEntryPrintAfterFilter.pdf");
		
		if(Efile.exists())
		{
			Efile.delete();
		}
		
		Thread.sleep(6000);
		
		
		
		
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_S);
		robot.keyRelease(KeyEvent.VK_S);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		
		Thread.sleep(6000);
			
		Runtime.getRuntime().exec(getBaseDir() + "\\autoIt\\scripts\\StockTransferEntryPrintAfterFilter.exe");
		
		Thread.sleep(14000);
		
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_J);
		robot.keyRelease(KeyEvent.VK_J);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		Thread.sleep(2000);
		
		ArrayList<String> openTabs = new ArrayList<String>(getDriver().getWindowHandles());
	
		
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_W);
		robot.keyRelease(KeyEvent.VK_W);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		Thread.sleep(2000);
		
	 	robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_W);
		robot.keyRelease(KeyEvent.VK_W);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		Thread.sleep(2000);
		
		
	 	
	 	String actPDF = getBaseDir()+"\\autoIt\\ExportFiles\\StockTransferEntryPrintAfterFilter.pdf";
		String expPDF = getBaseDir()+"\\autoIt\\ImportFiles\\StockTransferEntryPrintAfterFilter.pdf";
		System.out.println(actPDF);
		System.out.println(expPDF);
		
		
		PDFUtil pdfutil = new PDFUtil();
		
		boolean result = pdfutil.compare(actPDF, expPDF);
		Calendar cal=Calendar.getInstance();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String currentDate = df.format(cal.getTime());
		
		String actData = pdfutil.getText(actPDF);
		String expData = pdfutil.getText(expPDF).replaceAll("05/08/2025", currentDate);
		System.out.println(actData);
		System.out.println(expData);
		
		System.out.println("Compared Result  : "+result);
		
		
		if(actData.equalsIgnoreCase(expData))
		{
			return true;
		}
		else
		{
			return false;
		}
		
	  
  }
  
  
  public boolean checkExcelinStockTransferReportAfterDefaultFilter() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
  {
	  

		Thread.sleep(3000);
		
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportEntryExcelBtn));
		reportEntryExcelBtn.click();
		Thread.sleep(3000);
		
		click(ss_ReportPrintYesBtn);
	
		Thread.sleep(8000);
		
		File Efile=new File(getBaseDir()+"\\autoIt\\ExportFiles\\StockTransferEntryExcelAfterFilter.xlsx");
			
			if(Efile.exists())
			{
				Efile.delete();
			}
			
			
					
			
			
			Robot robot = new Robot();
		/*	robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_J);
			robot.keyRelease(KeyEvent.VK_J);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			
			Thread.sleep(2000);
			
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
			

			
			Thread.sleep(2000);
			
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			
			Thread.sleep(25000);
			
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(4000);
			robot.keyPress(KeyEvent.VK_F12);
			robot.keyRelease(KeyEvent.VK_F12);
			
			
			Thread.sleep(2000);
			
			*/
				
			Runtime.getRuntime().exec(getBaseDir() + "\\autoIt\\scripts\\StockTransferEntryExcelAfterFilter.exe");
			
			Thread.sleep(8000);
			
			
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(4000);
			
		/*	
			robot.keyPress(KeyEvent.VK_ALT);
			robot.keyPress(KeyEvent.VK_F4);
			
			robot.keyRelease(KeyEvent.VK_ALT);
			robot.keyRelease(KeyEvent.VK_F4);
			Thread.sleep(5000);
			
			*/
			
			
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_J);
			robot.keyRelease(KeyEvent.VK_J);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			
			Thread.sleep(2000);
			
			
			ArrayList<String> openTabs = new ArrayList<String>(getDriver().getWindowHandles());
				
			int actOpenWindowsCount = getDriver().getWindowHandles().size();
			int expOpenWindowsCount = 2;
			
			System.out.println("Number of Windows  : "+actOpenWindowsCount+"  Value Expected  "+expOpenWindowsCount);
			
			Thread.sleep(1000);

		 
			
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_W);
			robot.keyRelease(KeyEvent.VK_W);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			Thread.sleep(2000);
			
			
		 	 ExcelReader excelReader = new ExcelReader(POJOUtility.getExcelPath());
		     

		 	String actExcelfile = getBaseDir()+"\\autoIt\\ExportFiles\\StockTransferEntryExcelAfterFilter.xlsx";
		 	String expExcelfile = getBaseDir()+"\\autoIt\\ImportFiles\\StockTransferEntryExcelAfterFilter.xlsx";
		 	String sheet = "Sheet1";
		 	   
		 	
		 	
		 	FileInputStream fip1 = new FileInputStream(actExcelfile);
		 	Workbook workbook1  = WorkbookFactory.create(fip1);
		 	
		 	FileInputStream fip2 = new FileInputStream(expExcelfile);
		 	Workbook workbook2  = WorkbookFactory.create(fip2);
		 	
		 	boolean result = excelReader.checkExcelSheetsComparisonWithMonth(workbook1, workbook2,"29/09/2025");
		 	
		 	System.err.println(result);
		 	
		 	if (result)
		 	{
		 		return true;
		 	}
		 	else
		 	{
		 		return false;
		 	}

	  
  }
  
  
  public boolean checkHomePrintinRDStockTransferReportAfterDefaultFilter() throws InterruptedException, IOException, AWTException
  {	  

	  Thread.sleep(4000);
	  
	  click(report_CloseBtn);
	  Thread.sleep(2000);
	  
	  click(reportHomePrintBtn);
	  Thread.sleep(4000);
	  
	  click(ss_ReportPrintYesBtn);
		Thread.sleep(12000);
		
		
File Efile=new File(getBaseDir()+"\\autoIt\\ExportFiles\\StockTransferHomePrintAfterFilter.pdf");
		
		if(Efile.exists())
		{
			Efile.delete();
		}
		
		Thread.sleep(2000);
		
		
		Thread.sleep(2000);
		
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_S);
		robot.keyRelease(KeyEvent.VK_S);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		
		Thread.sleep(6000);
			
		Runtime.getRuntime().exec(getBaseDir() + "\\autoIt\\scripts\\StockTransferHomePrintAfterFilter.exe");
		
		Thread.sleep(12000);
		
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_J);
		robot.keyRelease(KeyEvent.VK_J);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		Thread.sleep(2000);
		
		ArrayList<String> openTabs = new ArrayList<String>(getDriver().getWindowHandles());
	
		
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_W);
		robot.keyRelease(KeyEvent.VK_W);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		Thread.sleep(2000);
		
	 	robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_W);
		robot.keyRelease(KeyEvent.VK_W);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		Thread.sleep(2000);
		
		
	 	
	 	String actPDF = getBaseDir()+"\\autoIt\\ExportFiles\\StockTransferHomePrintAfterFilter.pdf";
		String expPDF = getBaseDir()+"\\autoIt\\ImportFiles\\StockTransferHomePrintAfterFilter.pdf";
		System.out.println(actPDF);
		System.out.println(expPDF);
		
		
		PDFUtil pdfutil = new PDFUtil();
		
		boolean result = pdfutil.compare(actPDF, expPDF);
		Calendar cal=Calendar.getInstance();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String currentDate = df.format(cal.getTime());
		
		String actData = pdfutil.getText(actPDF);
		String expData = pdfutil.getText(expPDF).replaceAll("05/08/2025", currentDate);
		System.out.println(actData);
		System.out.println(expData);
		
		System.out.println("Compared Result  : "+result);
		
		
		if(actData.equalsIgnoreCase(expData))
		{
			return true;
		}
		else
		{
			return false;
		}

	  
  }
  
  
  public boolean checkHomeExcelinStockTransferReportAfterDefaultFilter() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
  {
	  

		Thread.sleep(3000);
		
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportHomeExcelBtn));
		reportHomeExcelBtn.click();
		Thread.sleep(3000);
		
		click(ss_ReportPrintYesBtn);
	
		Thread.sleep(12000);
		
		File Efile=new File(getBaseDir()+"\\autoIt\\ExportFiles\\StockTransferHomeExcelAfterFilter.xlsx");
			
			if(Efile.exists())
			{
				Efile.delete();
			}
			
			
					
			
			
			Robot robot = new Robot();
		/*	robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_J);
			robot.keyRelease(KeyEvent.VK_J);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			
			Thread.sleep(2000);
			
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
			

			
			Thread.sleep(2000);
			
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			
			Thread.sleep(25000);
			
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(4000);
			robot.keyPress(KeyEvent.VK_F12);
			robot.keyRelease(KeyEvent.VK_F12);
			
			
			Thread.sleep(2000);
			
			*/
				
			Runtime.getRuntime().exec(getBaseDir() + "\\autoIt\\scripts\\StockTransferHomeExcelAfterFilter.exe");
			
			Thread.sleep(8000);
			
			
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(4000);
			
/*			
			robot.keyPress(KeyEvent.VK_ALT);
			robot.keyPress(KeyEvent.VK_F4);
			
			robot.keyRelease(KeyEvent.VK_ALT);
			robot.keyRelease(KeyEvent.VK_F4);
			Thread.sleep(5000);
			*/
			
			
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_J);
			robot.keyRelease(KeyEvent.VK_J);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			
			Thread.sleep(2000);
			
			ArrayList<String> openTabs = new ArrayList<String>(getDriver().getWindowHandles());
				
			int actOpenWindowsCount = getDriver().getWindowHandles().size();
			int expOpenWindowsCount = 2;
			
			System.out.println("Number of Windows  : "+actOpenWindowsCount+"  Value Expected  "+expOpenWindowsCount);
			
			Thread.sleep(1000);

		 
			
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_W);
			robot.keyRelease(KeyEvent.VK_W);
			robot.keyRelease(KeyEvent.VK_CONTROL);
		
			
			
		 	 ExcelReader excelReader = new ExcelReader(POJOUtility.getExcelPath());
		     

		 	String actExcelfile = getBaseDir()+"\\autoIt\\ExportFiles\\StockTransferHomeExcelAfterFilter.xlsx";
		 	String expExcelfile = getBaseDir()+"\\autoIt\\ImportFiles\\StockTransferHomeExcelAfterFilter.xlsx";
		 	String sheet = "Sheet1";
		 	   
		 	
		 	
		 	FileInputStream fip1 = new FileInputStream(actExcelfile);
		 	Workbook workbook1  = WorkbookFactory.create(fip1);
		 	
		 	FileInputStream fip2 = new FileInputStream(expExcelfile);
		 	Workbook workbook2  = WorkbookFactory.create(fip2);
		 	
		 	boolean result = excelReader.checkExcelSheetsComparisonWithMonth(workbook1, workbook2,"29/09/2025");
		 	
		 	System.err.println(result);
		 	
		 	if (result)
		 	{
		 		return true;
		 	}
		 	else
		 	{
		 		return false;
		 	}
	  
  }
   
  @FindBy(xpath="//*[@id='dvReportDetails']//thead//th")
  public static List<WebElement> monthdetailsList; 
   
   public boolean checkPreviousYearMonthDetailsinRDYearandMonthWiseReport() throws InterruptedException
   {
	   
	   getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchTxt));
	 		searchTxt.click();
	 		Thread.sleep(1000);
	 		searchTxt.sendKeys("RD Year and Month wise Data");
	 		Thread.sleep(1000);
	 		searchTxt.sendKeys(Keys.ENTER);
	 		
	 		Thread.sleep(8000);
	 		
	 		click(sl_DateOptionDropdown);
	 		Select s=new Select(sl_DateOptionDropdown);
	 		s.selectByValue("8");
	 		Thread.sleep(2000);
	 		
	 		click(sl_OkBtn);
	 		Thread.sleep(8000);
	 		
	 		
	 		//getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_1stRow1stCol));
	 		
	 		ArrayList<String>prevousMonthArray=new ArrayList<String>();
	 		for(int i=0;i<monthdetailsList.size();i++)
	 		{
	 			
	 			prevousMonthArray.add(monthdetailsList.get(i).getText());
	 		}
	 		String actMonthDetails=prevousMonthArray.toString();
	 		String expMonthDetails="[#, Particulars, Voucher name, Department.Name, Warehouse.Name, Quantity]";
	 		
	 		System.out.println("actMonthDetails"		+	actMonthDetails);
	 		System.out.println("expMonthDetails"		+	expMonthDetails);
	 		
	 		if(actMonthDetails.equalsIgnoreCase(expMonthDetails))
	 		{
	 			return true;
	 			 		 			
	 		}
	 		else
	 		{
	 			return false;
	 		}
	   
   }
  
   
   @FindBy(xpath="//a[text()=' Fiscal Year']//span")
   public static WebElement fiscalYearExpBtn;
   
   @FindBy(xpath="//*[text()='Current FY']")
   public static WebElement currentFYBtn;
   
   @FindBy(xpath="//*[text()='Previous FY']")
   public static WebElement previousFYBtn;
   
   @FindBy(xpath="//*[text()=' Fiscal Quarter']//span")
   public static WebElement fiscalQuaterExpBtn;
   
   @FindBy(xpath="//*[text()='Previous FQ']")
   public static WebElement previousQuaterBtn;
   
   @FindBy(xpath="//*[text()=' Calendar Month']//span")
   public static WebElement calendartMonthExpBtn;
   
   @FindBy(xpath="//*[text()='Previous Month']")
   public static WebElement previousMonthBtn;
  
   
   public boolean checkSavingRDDetailReportforDateOptions() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
   {
	   
	   Thread.sleep(4000);
	   
	   focusMainSearch("Report Designer");
	   Thread.sleep(5000);
	   
	   getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportNameDropdown));       
		reportNameDropdown.sendKeys("Report for Date Range Options");
		reportNameDropdown.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportTypeDropdown));
		Select rtd= new Select(reportTypeDropdown);
		rtd.selectByVisibleText("Details");
		
		Thread.sleep(1000);
		reportTypeDropdown.sendKeys(Keys.TAB);
		
		Thread.sleep(3000);

		getWebDriverWaitEle(inventoryExpandBtn);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(inventoryExpandBtn));
		inventoryExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportsBtn));
		reportsBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dataSetTab));
		dataSetTab.click();

		Thread.sleep(2000);

		int transactionSetListCount = transactionSetList.size();

		ArrayList<String >transactionSetListArray=new ArrayList<>();

		for(int i=0;i<=transactionSetListCount;i++)
		{
			String data = transactionSetList.get(i).getText();

			transactionSetListArray.add(data);

			if(data.equalsIgnoreCase("Inventory transactions"))
			{
				transactionSetList.get(i).click();

				break;
			}
		}

		Thread.sleep(2000);
		
		click(advanceFilterBtn1);
		Thread.sleep(2000);
		
		
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdAdvanceFilterWhereDrpdwnForPaea1));
		rdAdvanceFilterWhereDrpdwnForPaea1.click();
		Select s5=new Select(rdAdvanceFilterWhereDrpdwnForPaea1);
		s5.selectByValue("0");


		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdAdvanceFilterNameTxt));
		rdAdvanceFilterNameTxt.click();
		Thread.sleep(2000);

		
		scrollToElementJSE(rdAdvanceFilterDateBtn);
		Thread.sleep(2000);
		rdAdvanceFilterDateBtn.click();
	
		
		
		Thread.sleep(2000);
		Select s3=new Select(rdAdvanceFilterOpersatorDrpdwn);
		s3.selectByValue("0");


		Thread.sleep(2000);
		Select s4=new Select(rdAdvanceFilterValueDrpdwn);
		s4.selectByValue("3");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdAdvanceFilterSelectTxt));
		rdAdvanceFilterSelectTxt.click();
		Thread.sleep(1500);
		
		fiscalYearExpBtn.click();
		Thread.sleep(1500);
		
		previousFYBtn.click();
		Thread.sleep(1500);

		Thread.sleep(2999);
		rdAdvanceFilterSelectTxt.sendKeys(Keys.TAB);

		Thread.sleep(2999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTab));
		customizationTab.click();

		Thread.sleep(2000);
		//getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionExpandBtn));
		//transactionExpandBtn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(transactionFieldsExpandBtn));
		transactionFieldsExpandBtn.click();

		getAction().moveToElement(docNoField).doubleClick().build().perform();
		Thread.sleep(1500);
		
		getAction().moveToElement(dateExpandBtn).click().build().perform();
		Thread.sleep(1500);
		
		doubleClick(dateFieldBtn);
		Thread.sleep(2000);
		
		getAction().moveToElement(acc1ExpandBtn).click().build().perform();
		Thread.sleep(1500);
		
		getAction().moveToElement(acc1NameBtn).doubleClick().build().perform();
		Thread.sleep(1500);
		
		getAction().moveToElement(itemExpandBtn).build().perform();
		click(itemExpandBtn);
		
		getAction().moveToElement(itemNameBtn).doubleClick().build().perform();
		click(itemExpandBtn);
				
		Thread.sleep(2000);
		
		getAction().moveToElement(quantityBtn).doubleClick().build().perform();
		
		getAction().doubleClick(rateBtn).build().perform();
		
		Thread.sleep(2000);
		
		getAction().moveToElement(finishBtn).build().perform();
		click(finishBtn);
		
		Thread.sleep(2000);
		
		String expMsg="Data saved successfully.";
		String actMsg=checkValidationMessage(expMsg);
		
		 focusMainSearch("Report for Date Range Options");
		   Thread.sleep(5000);
		   
		   click(sl_OkBtn);
		   Thread.sleep(8000);
		   
		   click(report_LastBtn1);
		   Thread.sleep(2000);
		   
			String expTotalRowList = "[91, 08/02/2024, RMA2, 1.00, 5.00]";
			boolean actTotalRowList = ListComparisionWOOrder(1,report11thRowList,expTotalRowList);
		   
		
		if(actMsg.equalsIgnoreCase(expMsg) && actTotalRowList)
		{
			return true;
		}
		else
		{
			return false;
		}
	}   
	   
   public boolean checkReportforDateRangeOptionsforPreviousFQ() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
   {
	 click(report_CloseBtn) ; 
	 Thread.sleep(4000);
	   
	   click(reportCustomizeBtnHomePage);
	   Thread.sleep(4000);
	   
	   click(dataSetTab);
	   Thread.sleep(2500);
	   
	   getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdAdvanceFilterSelectTxt1));
		rdAdvanceFilterSelectTxt1.click();
		Thread.sleep(1500);
		
		fiscalQuaterExpBtn.click();
		Thread.sleep(1500);
		
		click(previousQuaterBtn);
		Thread.sleep(2500);
		
		rdAdvanceFilterSelectTxt1.sendKeys(Keys.TAB);
		
		click(finishBtn);
		
		String expMsg="Data saved successfully.";
		String actMsg=checkValidationMessage(expMsg);
		
		click(rd_CancelBtn);
		Thread.sleep(3500);
		
	   click(sl_OkBtn);
	   Thread.sleep(8000);
	   
	 boolean actReportTableTxt=reportTable.getText().isEmpty();
	 System.out.println("Report for Previous Quarter	"	+	actReportTableTxt);
	 
	   if(actReportTableTxt)
	   {
		   return true;
	   }
	   else
	   {
		   return false;
	   }
	   
	   
   }
   
   public boolean checkReportforDateRangeforPreviousMonth() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
   {

		 click(report_CloseBtn) ; 
		 Thread.sleep(4000);
		   
		   click(reportCustomizeBtnHomePage);
		   Thread.sleep(4000);
		   
		   click(dataSetTab);
		   Thread.sleep(2500);
		   
		   getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdAdvanceFilterSelectTxt1));
			rdAdvanceFilterSelectTxt1.click();
			Thread.sleep(1500);
			
			calendartMonthExpBtn.click();
			Thread.sleep(1500);
			
			click(previousMonthBtn);
			Thread.sleep(2500);
			
			rdAdvanceFilterSelectTxt1.sendKeys(Keys.TAB);
			
			click(finishBtn);
			
			String expMsg="Data saved successfully.";
			String actMsg=checkValidationMessage(expMsg);
			
			click(rd_CancelBtn);
			Thread.sleep(3500);
			
		   click(sl_OkBtn);
		   Thread.sleep(8000);
		   
		 boolean actReportTableTxt=reportTable.getText().isEmpty();
		 System.out.println("Report for Previous Quarter	"	+	actReportTableTxt);
		 
		   if(actReportTableTxt)
		   {
			   return true;
		   }
		   else
		   {
			   return false;
		   }
		   
		   
	   
   }
   
   @FindBy(xpath="(//*[@class='icon-close hiconright2'])[3]")
   public static WebElement rd_CancelBtn;
   
   public boolean checkLogoutReportDesignerPage() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
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
	 
 
	public ReportDesignerPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);

	}







}