package com.focus.Pages;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.focus.base.BaseEngine;
import com.focus.elements.WebElements;
import com.focus.supporters.ExcelReader;
import com.focus.utilities.DriverUtility;
import com.focus.utilities.POJOUtility;

public class ReportDesignerNew extends BaseEngine{
	
		
	
	
	
	@FindBy(xpath="//*[@id='ddlCompany']")
	public static WebElement companyDropDownList;
	
	
	@FindBy(xpath = "//*[@id='1' and @title='Home']")
	public static WebElement homeMenu;
	
	
	@FindBy(xpath="//*[@id='txtUsername']")
	public static WebElement username;

	@FindBy(id="txtPassword")
	public static WebElement password;
	
   

	@FindBy(id="btnSignin")
	public static WebElement signIn;
	

	
	public static boolean checkLogin()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		

		Thread.sleep(1999);
		getDriver().navigate().refresh();
		Thread.sleep(1999);
		
		

		LoginPage lp = new LoginPage(getDriver());

		lp.checkLoginPageTitleByURLInputInBrowser(DriverUtility.FINUrl);

		re_LunchBrowser();

		Thread.sleep(3000);

		String unamelt = "su";

		String pawslt = "su";

		lp.enterUserName(unamelt);

		Thread.sleep(2000);

		lp.enterPassword(pawslt);
		
		companyDropDownList.click();

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

		Thread.sleep(18000);

		

		boolean expHomeMenuDisplayed = true;
		boolean actHomeMenuDisplayed = homeMenu.isDisplayed();

		if (actHomeMenuDisplayed==expHomeMenuDisplayed) {
			
			return true;

		} else {
			
			return false;

		}
		  

	}
	
	public static void checkRestoreOptionsCompanyAndLogin()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {

		checkRestoreOptionsCompanyAndLogin("RD REPORTSNew", "RD REPORTS");

		

	}
	
	
	
	@FindBy(xpath="//*[@id='lblMasterSingle']")
	public static WebElement RDDetail_BrandLabel;
	
	@FindBy(xpath="//*[@id='MasterSingle__1']")
	public static WebElement RDDetail_BrandTxt;
	
	@FindBy(xpath="//*[@id='MasterSingle__2']")
	public static WebElement RDDetail_AccTxt;
	
	@FindBy(xpath="//*[@id='dvReportDetails']//table//th")
	public static List<WebElement> RDDetail_HeaderList;
	
	@FindBy(xpath="//*[@id='dvReportDetails']//table//tr//td[3]")
	public static List<WebElement> RDDetail_DateColList;
	
	@FindBy(xpath="//*[@id='dvReportDetails']//table//tr//td[4]")
	public static List<WebElement> RDDetail_Acc1ColList;
	
	@FindBy(xpath="//*[@id='dvReportDetails']//table//tr//td[8]")
	public static List<WebElement> RDDetail_QtyColList;
	
	@FindBy(xpath="//*[@id='dvReportDetails']//table//tr[6]//td[9]")
	public static WebElement RDDetail_RateColTotal;
	
	@FindBy(xpath="//*[@id='dvReportDetails']//table//tr//td[7]")
	public static List<WebElement> RDDetail_ItemColList;
	
	@FindBy(xpath="//*[@id='dvReportDetails']//table//tr//td[10]")
	public static List<WebElement> RDDetail_GrossColList;
	
	@FindBy(xpath="//*[@id='dvReportDetails']//table//tr//td[11]")
	public static List<WebElement> RDDetail_VoucherNameColList;
	
	@FindBy(xpath="//*[@id='dvReportDetails']//table//tr//td[12]")
	public static List<WebElement> RDDetail_DeptNameColList;
	
	@FindBy(xpath="//*[@id='dvReportDetails']//table//tr//td[15]")
	public static List<WebElement> RDDetail_Prog1ColList;
	
	@FindBy(xpath="//*[@id='dvReportDetails']//table//tr//td[16]")
	public static List<WebElement> RDDetail_Prog2ColList;
	
	@FindBy(xpath="//*[@id='dvReportDetails']//table//tr//td[13]")
	public static List<WebElement> RDDetail_WarehouseColList;
	
			@FindBy(xpath="(//tr[@id='trRender_0'])[1]/td")
			private static List<WebElement> reportsRow1List;
		  	
			@FindBy(xpath="(//tr[@id='trRender_1'])[1]/td")
			private static List<WebElement> reportsRow2List;
			
			@FindBy(xpath="(//tr[@id='trRender_2'])[1]/td")
			private static List<WebElement> reportsRow3List;
			
			@FindBy(xpath="(//tr[@id='trRender_3'])[1]/td")
			private static List<WebElement> reportsRow4List;
			
			@FindBy(xpath="(//tr[@id='trRender_4'])[1]/td")
			private static List<WebElement> reportsRow5List;
			
			@FindBy(xpath="(//tr[@id='trRender_5'])[1]/td")
			private static List<WebElement> reportsRow6List;
			
			@FindBy(xpath="(//tr[@id='trRender_6'])[1]/td")
			private static List<WebElement> reportsRow7List;
			
	
	
	public boolean checkRDDetailReportCustomization() throws InterruptedException
	{
		focusMainSearch("RD Detail");
		Thread.sleep(4000);
		
		getFluentWebDriverWait().until(ExpectedConditions.visibilityOf(RDDetail_BrandLabel));
		boolean actBrandLabel=RDDetail_BrandLabel.isDisplayed();
		boolean expBrandLabel=true;
		
		System.out.println("Brand Parameter is Displayed	"		+		"Actual		"		+	actBrandLabel		+	"Expected		"		+		expBrandLabel);
		
		click(sl_OkBtn);
		Thread.sleep(12000);
		
		getFluentWebDriverWait().until(ExpectedConditions.visibilityOf(sl_1stRow1stCol));
		
		String expRow1List = "[1, 1, 21-05-07, CustomerA, Sales-Computers, 071-001, STDRATECOGSITEM, 1.00, 5.00, 0.00, SalesReturnsVAT, HYDERABAD, 0.00, 5.00]";
		boolean actRow1List=ListComparisionWOOrder(reportsRow1List,expRow1List);

		
		
		String expRow2List = "[2, 1, 21-05-07, CustomerA, Sales-Computers, 071-001, STDRATECOGSITEM, -2.00, 10.00, 0.00, SalesNewReference, HYDERABAD, 0.00, 20.00]";
		boolean actRow2List=ListComparisionWOOrder(reportsRow2List,expRow2List);

		
		
		String expRow3List = "[3, 2, 21-05-07, VendorB, STDRATECOGSACCINV, STDRATECOGSACCINV, STDRATECOGSITEM, 1.00, 5.00, 5.00, PurchasesVoucherVAT, HYDERABAD, 0.00, 5.00]";
		boolean actRow3List=ListComparisionWOOrder(reportsRow3List,expRow3List);

		
		
		String expRow4List = "[4, 1, 21-05-07, VendorNewReference, STDRATECOGSACCINV, STDRATECOGSACCINV, STDRATECOGSITEM, 1.00, 5.00, 5.00, PurchasesVoucherVAT, HYDERABAD, 0.00, 5.00]";
		boolean actRow4List=ListComparisionWOOrder(reportsRow4List,expRow4List);

		
		String expRow5List = "[5, 1, 25-05-26, VendorA, Purchase, 091-002, STOCKITEM, 100.00, 51.00, 5, 100.00, PurchasesVouchers, AMERICA, HYDERABAD, STYLEUNION, 0.00, 5, 100.00]";
		boolean actRow5List=ListComparisionWOOrder(reportsRow5List,expRow5List);

		String expRow6List = "[6, GrandTotal, 101.00, 5, 110.00, 0.00, 5, 095.00]";
		boolean actRow6List=ListComparisionWOOrder(reportsRow6List,expRow6List);

		
		//Date Format
		ArrayList<String>DateColArray=new ArrayList<String>();
		for(int i=0; i<RDDetail_DateColList.size();i++)
		{
			DateColArray.add(RDDetail_DateColList.get(i).getText());
			
		}
		
		String actDateColList=DateColArray.toString();
		String expDateColList="[21-05-07, 21-05-07, 21-05-07, 21-05-07, 25-05-26, ]";
		
		System.out.println("Actual Date Col List	"	+	actDateColList);
		System.out.println("Expect Date Col List	"	+	expDateColList);
		
		//Account1 Alignment
		
		ArrayList<String>Acc1ColArray=new ArrayList<String>();
		for(int i=0; i<RDDetail_Acc1ColList.size();i++)
		{
			Acc1ColArray.add(RDDetail_Acc1ColList.get(i).getAttribute("class"));
			
		}
		
		String actAcc1ColList=Acc1ColArray.toString();
		String expAcc1ColList="[TextAlignCenter, TextAlignCenter, TextAlignCenter, TextAlignCenter, TextAlignCenter, TextAlignCenter]";
		
		System.out.println("Actual Acc1 Col Alignment List	"	+	actAcc1ColList);
		System.out.println("Expect Acc1 Col Alignment List	"	+	expAcc1ColList);
		
		//Hide Acc2 Code
		
		ArrayList<String>HeaderColArray=new ArrayList<String>();
		for(int i=0; i<RDDetail_HeaderList.size();i++)
		{
			String data=RDDetail_HeaderList.get(i).getText();
			if(!data.equalsIgnoreCase("Account.Code"))
			{
				HeaderColArray.add(RDDetail_HeaderList.get(i).getText());
			}
			
		}
		
		String actHeaderColList=HeaderColArray.toString();
		String expHeaderColList="[#, Document No., Date, Account.Name, Account2.Name, Account2.Code, Item.Name, Quantity, Rate, Gross, Voucher name, Department.Name, Warehouse.Name, BRAND.Name, P1, P2]";
		
		System.out.println("Actual Acc2 Col Hide List	"	+	actHeaderColList);
		System.out.println("Expect Acc2 Col Hide List	"	+	expHeaderColList);
		
		
		///Item Name for Warehouse
		
		ArrayList<String>ItemColArray=new ArrayList<String>();
		for(int i=0; i<RDDetail_ItemColList.size();i++)
		{
			String data=RDDetail_WarehouseColList.get(i).getText();
			if(data.equalsIgnoreCase("HYDERABAD"))
			{
				ItemColArray.add(RDDetail_ItemColList.get(i).getText());
			}
			
		}
		
		String actItemColList=ItemColArray.toString();
		String expItemColList="[STD RATE COGS ITEM, STD RATE COGS ITEM, STD RATE COGS ITEM, STD RATE COGS ITEM, STOCK ITEM]";
		
		System.out.println("Actual Item Col  List	"	+	actItemColList);
		System.out.println("Expect Item Col  List	"	+	expItemColList);
		
		//Quantity will display (+/-)
		ArrayList<String>QuantityColArray=new ArrayList<String>();
		for(int i=0; i<RDDetail_QtyColList.size();i++)
		{
			
				QuantityColArray.add(RDDetail_QtyColList.get(i).getText());
					
		}
		
		String actQtyColList=QuantityColArray.toString();
		String expQtyColList="[1.00, -2.00, 1.00, 1.00, 100.00, 101.00]";
		
		System.out.println("Actual Qty Col  List	"	+	actQtyColList);
		System.out.println("Expect Qty Col  List	"	+	expQtyColList);
		
		//Rate col total should not display
		boolean actRateColTotalnotDisplay=RDDetail_RateColTotal.getText().isEmpty();
		boolean expRateColTotalnotDisplay=true;
		
		System.out.println("Actual Rate Col Total Not Displayed		"		+		actRateColTotalnotDisplay);
		System.out.println("Actual Rate Col Total Not Displayed		"		+		expRateColTotalnotDisplay);
		
		
		//Gross value for Purchase Vouchers
		
		ArrayList<String>GrossColArray=new ArrayList<String>();
		for(int i=0; i<RDDetail_GrossColList.size();i++)
		{
			String data=RDDetail_VoucherNameColList.get(i).getText();
			if(data.contains("Purchases"))
			{
				GrossColArray.add(RDDetail_GrossColList.get(i).getText());
			}
			
		}
		
		String actGrossColList=GrossColArray.toString();
		String expGrossColList="[5.00, 5.00, 5,100.00]";
		
		System.out.println("Actual Gross Col  List	"	+	actGrossColList);
		System.out.println("Expect Gross Col  List	"	+	expGrossColList);
		
	//Dept name for qty>5
		ArrayList<String>DeptColArray=new ArrayList<String>();
		for(int i=0; i<RDDetail_QtyColList.size();i++)
		{
			double data=Double.parseDouble(RDDetail_QtyColList.get(i).getText());
			if(data>5.00)
			{
				DeptColArray.add(RDDetail_DeptNameColList.get(i).getText());
			}
			
		}
		
		String actDeptColList=DeptColArray.toString();
		String expDeptColList="[AMERICA, ]";
		
		System.out.println("Actual Dept Col  List	"	+	actDeptColList);
		System.out.println("Expect Dept Col  List	"	+	expDeptColList);
		
		
		ArrayList<String>Prog1ColArray=new ArrayList<String>();
		for(int i=0; i<RDDetail_Prog1ColList.size();i++)
		{
			
			Prog1ColArray.add(RDDetail_Prog1ColList.get(i).getText());
			
		}
		
		String actProg1ColList=Prog1ColArray.toString();
		String expProg1ColList="[0.00, 0.00, 0.00, 0.00, 0.00, 0.00]";
		
		System.out.println("Actual Prog1 Col  List	"	+	actProg1ColList);
		System.out.println("Expect Prog1 Col  List	"	+	expProg1ColList);
		
		
		ArrayList<String>Prog2ColArray=new ArrayList<String>();
		for(int i=0; i<RDDetail_Prog2ColList.size();i++)
		{
			
			Prog2ColArray.add(RDDetail_Prog2ColList.get(i).getText());
			
		}
		
		String actProg2ColList=Prog2ColArray.toString();
		String expProg2ColList="[5.00, 20.00, 5.00, 5.00, 5,100.00, 5,095.00]";
		
		System.out.println("Actual Prog2 Col  List	"	+	actProg2ColList);
		System.out.println("Expect Prog2 Col  List	"	+	expProg2ColList);
		
		if(actBrandLabel==expBrandLabel && actDateColList.equalsIgnoreCase(expDateColList) && actAcc1ColList.equalsIgnoreCase(expAcc1ColList) && actHeaderColList.equalsIgnoreCase(expHeaderColList)
				&& actItemColList.equalsIgnoreCase(expItemColList) && actRateColTotalnotDisplay==expRateColTotalnotDisplay && actGrossColList.equalsIgnoreCase(expGrossColList)
				&& actDeptColList.equalsIgnoreCase(expDeptColList) && actProg1ColList.equalsIgnoreCase(expProg1ColList) && actProg2ColList.equalsIgnoreCase(expProg2ColList))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	@FindBy(xpath="(//a[@title='Excel'])[2]")
	public static WebElement report_ExportExcelfromEntryPage;
	
	@FindBy(xpath="(//input[@value='Yes'])[1]")
	private static WebElement sl_ExportPDFYesBtn;
	
	public boolean checkExporttoExcelinRDDetailReportofBrand() throws InterruptedException, AWTException, IOException, EncryptedDocumentException, InvalidFormatException
	{
		click(report_CloseBtn);
		Thread.sleep(2500);
		
		click(RDDetail_BrandTxt);
		RDDetail_BrandTxt.sendKeys("STYLE UNION");
		Thread.sleep(2000);
		RDDetail_BrandTxt.sendKeys(Keys.TAB);
		
		click(sl_OkBtn);
		Thread.sleep(10000);
		
		getFluentWebDriverWait().until(ExpectedConditions.visibilityOf(sl_1stRow1stCol));
		
		String expRow1List = "[1, 1, 25-05-26, VendorA, Purchase, 091-002, STOCKITEM, 100.00, 51.00, 5, 100.00, PurchasesVouchers, AMERICA, HYDERABAD, STYLEUNION, 0.00, 5, 100.00]";
		boolean actRow1List=ListComparisionWOOrder(reportsRow1List,expRow1List);
				
		String expRow2List = "[2, GrandTotal, 100.00, 5, 100.00, 0.00, 5, 100.00]";
		boolean actRow2List=ListComparisionWOOrder(reportsRow2List,expRow2List);

		click(report_ExportExcelfromEntryPage);
		Thread.sleep(2500);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_ExportPDFYesBtn));
		sl_ExportPDFYesBtn.click();
		Thread.sleep(8000);
		
		File Efile=new File(getBaseDir()+"\\autoIt\\ExportFiles\\RDDetailExcelEntryNew.xlsx");
			
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
			
			Thread.sleep(7000);
			
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(4000);
			robot.keyPress(KeyEvent.VK_F12);
			robot.keyRelease(KeyEvent.VK_F12);
			
			
			Thread.sleep(2000);
			*/
			
				
			Runtime.getRuntime().exec(getBaseDir() + "\\autoIt\\scripts\\RDDetailExcelEntryNew.exe");
			
			Thread.sleep(8000);
			
			
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(4000);
			
			
		/*	robot.keyPress(KeyEvent.VK_ALT);
			robot.keyPress(KeyEvent.VK_F4);
			
			robot.keyRelease(KeyEvent.VK_ALT);
			robot.keyRelease(KeyEvent.VK_F4);
			Thread.sleep(3000);
			
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

		 	/*getDriver().switchTo().window(openTabs.get(2)).close();
		 	Thread.sleep(1000);*/
		 /*	getDriver().switchTo().window(openTabs.get(1)).close();
		 	Thread.sleep(1000);
		 	getDriver().switchTo().window(openTabs.get(0));*/
			
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_W);
			robot.keyRelease(KeyEvent.VK_W);
			robot.keyRelease(KeyEvent.VK_CONTROL);
		
			
			
		 	 ExcelReader excelReader = new ExcelReader(POJOUtility.getExcelPath());
		     

		 	String actExcelfile = getBaseDir()+"\\autoIt\\ExportFiles\\RDDetailExcelEntryNew.xlsx";
		 	String expExcelfile = getBaseDir()+"\\autoIt\\ImportFiles\\RDDetailExcelEntryNew.xlsx";
		 	String sheet = "Sheet1";
		 	   
		 	
		 	
		 	FileInputStream fip1 = new FileInputStream(actExcelfile);
		 	Workbook workbook1  = WorkbookFactory.create(fip1);
		 	
		 	FileInputStream fip2 = new FileInputStream(expExcelfile);
		 	Workbook workbook2  = WorkbookFactory.create(fip2);
		 	
		 	boolean result = excelReader.checkExcelSheetsComparison(workbook1, workbook2,"29/09/2025");
		 	
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
	
	
	public boolean checkRowFormattinginRDDetailReport() throws InterruptedException
	{
		
		click(report_CloseBtn);
		Thread.sleep(2500);
		
		
		click(RDDetail_BrandTxt);
		RDDetail_BrandTxt.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
		RDDetail_BrandTxt.sendKeys(Keys.ESCAPE);
		
		click(sl_OkBtn);
		Thread.sleep(12000);
		
		ArrayList<String>QtyColArray=new ArrayList<String>();
		for(int i=0; i<RDDetail_QtyColList.size()-1;i++)
		{
			double data=Double.parseDouble(RDDetail_QtyColList.get(i).getText());
			if(data>10.00)
			{
				QtyColArray.add(RDDetail_QtyColList.get(i).getCssValue("background-color"));
			}
			
		}
		
		String actQtyColList=QtyColArray.toString();
		String expQtyColList="[rgba(255, 255, 0, 1)]";
		
		System.out.println("Actual Qty Col  List	"	+	actQtyColList);
		System.out.println("Expect Qty Col  List	"	+	expQtyColList);
		
		
		if(actQtyColList.equalsIgnoreCase(expQtyColList))
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	
	public boolean checkSortingOrderofGrossinRDDetilsReport()
	{
		ArrayList<String>GrossColArray=new ArrayList<String>();
		for(int i=0; i<RDDetail_GrossColList.size();i++)
		{
			
			GrossColArray.add(RDDetail_GrossColList.get(i).getText());
			
			
		}
		
		String actGrossColList=GrossColArray.toString();
		String expGrossColList="[0.00, 0.00, 5.00, 5.00, 5,100.00, 5,110.00]";
		
		System.out.println("Actual Gross Col  List	"	+	actGrossColList);
		System.out.println("Expect Gross Col  List	"	+	expGrossColList);
		
		
		if(actGrossColList.equalsIgnoreCase(expGrossColList))
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	public boolean checkColumnHeadinginCenterinRDDetailReport()
	{
		

		ArrayList<String>HeaderColArray=new ArrayList<String>();
		for(int i=0; i<RDDetail_HeaderList.size();i++)
		{
			
			HeaderColArray.add(RDDetail_HeaderList.get(i).getCssValue("text-align"));
			
			
		}
		
		String actHeaderColList=HeaderColArray.toString();
		String expHeaderColList="[center, center, center, center, center, center, center, center, center, center, center, center, center, center, center, center]";
		
		System.out.println("Actual Qty Col  List	"	+	actHeaderColList);
		System.out.println("Expect Qty Col  List	"	+	expHeaderColList);
		
		
		if(actHeaderColList.equalsIgnoreCase(expHeaderColList))
		{
			return true;
		}
		else
		{
			return false;
		}
		
	
	}
	
	
	@FindBy(xpath="//*[@id='id_rd_parameters_group']//input[@value='Ok']")
	private static WebElement rdParametersOkbtn;
	@FindBy(xpath="//a[@id='20']//span[contains(text(),'Utilities')]")
	private static WebElement  utilities;

	@FindBy(xpath="//a[@id='22']//span[contains(text(),'Report Designer')]")
	private static WebElement reportDesignerMenu;
	
	@FindBy(xpath="//input[@id='id_rd_definition_reportname']")
	private static WebElement reportNameDropdown;
	
	@FindBy(xpath="//select[@id='id_rd_definition_reporttype']")
	private static WebElement reportTypeDropdown; 	

	@FindBy(xpath="//input[@id='advanceEngine']")
	private static WebElement advanceEngineCkeckbox;
	
	@FindBy(xpath="//*[@id='advanceEngine']/following-sibling::span")
	private static WebElement rdReportsAdvanceEngineChkbox;
	
	@FindBy(xpath="//*[@id='advanceEngine']")
	private static WebElement rdReportsAdvanceEngineChkboxSelected;
	
	@FindBy(xpath="//*[@id='id_menu_tree_135']/a/i")
	private static WebElement  inventoryExpandBtn;
	
	

	@FindBy(xpath="//*[@id='id_menu_tree_200']/a/span")
	private static WebElement  reportsBtn;
	
	@FindBy(xpath="//input[@id='id_rd_parameter_entry_fieldname']")
	private static WebElement rdFieldNameTxt;
	
	@FindBy(xpath="//select[@id='id_rd_parameter_entry_fieldtype']")
	private static WebElement rdFieldTypeDrpdwn;
	
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
		
		
		@FindBy(xpath="//*[@id='RDFinishbtn']")
		private static WebElement finishBtn;
		
		@FindBy(xpath="//*[@id='id_rd_header_button_group_3']")
		private static WebElement customizationTab;
		
		
		@FindBy(xpath="//*[@id='rd_customization_tree0']//*[text()='Transaction']")
		private static WebElement TransactionExpBtn;
		
		
		@FindBy(xpath="//*[@id='rd_customization_tree0']//*[text()='Transaction Fields']")
		private static WebElement TransactionFieldsExpBtn;
		
		@FindBy(xpath="//*[text()='DocNo']")
		private static WebElement DocNoBtn;
		
		
		@FindBy(xpath="(//span[text()='Item'])[3]/../i")
		private static WebElement itemExpandBtn;

	
		@FindBy(xpath="((//span[text()='Item'])[3]//following::ul//li//span[text()='Name'])[1]")
		private static WebElement itemNameBtn;
		
		@FindBy(xpath="(//*[text()='Quantity'])[2]")
		private static WebElement quantityBtn;
		
		@FindBy(xpath="//..//span[text()='Rate']")
		private static WebElement rateBtn;
		
		@FindBy(xpath="//..//span[text()='Gross']")
		private static WebElement grossBtn;

		@FindBy(xpath="//input[@id='id_rd_parameter_entry_multipleinputselection']/following-sibling::span")
		private static WebElement rdMultipleInputChkbox;
		
		@FindBy(xpath="//input[@id='id_rd_parameter_entry_multipleinputselection']")
		private static WebElement rdMultipleInputChkboxSelected;

		@FindBy(xpath="//div[@class='Fpanel-footer']/*[@id='id_rd_customization_rowgroupingpopup_ok']")
		private static WebElement groupingOptionsOkBtn;
		
		@FindBy(xpath="(//*[text()='Extra Fields'])[1]")
		private static WebElement ExtraFieldExpBtn;
		
		@FindBy(xpath="(//*[text()='Department'])[4]/../i")
		private static WebElement DeptExpBtn;
		
		//@FindBy(xpath="((//..//span[contains(text(),'Department')])[7]//following::ul//li//*[text()='Name'])[1]")
		@FindBy(xpath="((//span[text()='Department'])[2]//following::ul//li//span[text()='Name'])[1]")
		private static WebElement DeptNameBt;
		
		@FindBy(xpath="(//..//span[contains(text(),'Warehouse')])[4]")
		private static WebElement WarehouseExpBtn;
		
		@FindBy(xpath="(//..//span[contains(text(),'Date')])[13]")
		private static WebElement DateExpBtn;
		
		@FindBy(xpath="(//..//span[contains(text(),'Date')])[13]//following::ul//li//*[text()='Date']")
		private static WebElement DateBtn;
		
		@FindBy(xpath="(//..//span[contains(text(),'Account')])[11]")
		private static WebElement AccountExpBtn;
		
		@FindBy(xpath="((//..//span[contains(text(),'Account')])[11]//following::ul//li//*[text()='Name'])[1]")
		private static WebElement AccountNameBtn;
		
		@FindBy(xpath="//..//span[contains(text(),'Account2')]")
		private static WebElement Account2ExpBtn;
		
		@FindBy(xpath="(//..//span[contains(text(),'Account2')]//following::ul//li//*[text()='Name'])[1]")
		private static WebElement Account2NameBtn;
		
				
		@FindBy(xpath="((//..//span[contains(text(),'Warehouse')])[4]//following::ul//li//*[text()='Name'])[1]")
		private static WebElement WarehouseNameBtn;
		
	public boolean checkCreatingRDCubeReportforDepartmentParameter() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
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
		reportNameDropdown.sendKeys("RD Cube Department Tag");
		reportNameDropdown.sendKeys(Keys.TAB);
		Thread.sleep(2500);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportTypeDropdown));
		Select rtd= new Select(reportTypeDropdown);
		rtd.selectByVisibleText("Cubes");

		//getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdReportsAdvanceEngineChkbox));
		//rdReportsAdvanceEngineChkbox.click();


		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(inventoryExpandBtn));
		inventoryExpandBtn.click();

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(reportsBtn));
		reportsBtn.click();


		rdFieldNameTxt.click();
		rdFieldNameTxt.sendKeys("dept");
		Thread.sleep(2000);

		rdFieldNameTxt.sendKeys(Keys.TAB);


		Thread.sleep(2000);
		Select s1=new Select(rdFieldTypeDrpdwn);
		s1.selectByValue("3");


		Thread.sleep(2000);
		
		if(rdMultipleInputChkboxSelected.isSelected()==false)
		{
			rdMultipleInputChkbox.click();
		}
		
		//scrollToElementJSE(rdParametersOkbtn);
		getAction().moveToElement(rdParametersOkbtn).build().perform();
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdParametersOkbtn));
		rdParametersOkbtn.click();

		Thread.sleep(2999);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dataSetTab));
		dataSetTab.click();

		Thread.sleep(2000);

		int transactionSetListCount = transactionSetList.size();

		for(int i=0;i<=transactionSetListCount;i++)
		{
			String data = transactionSetList.get(i).getText();

			if(data.equalsIgnoreCase("Inventory Transactions"))
			{
				transactionSetList.get(i).click();

				break;
			}
		}


		Thread.sleep(4000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTab));
		customizationTab.click();

		Thread.sleep(4000);
		
		
		//click(TransactionExpBtn);
		//Thread.sleep(1500);
		
		click(TransactionFieldsExpBtn);
		Thread.sleep(1500);
		
		getAction().moveToElement(DocNoBtn).build().perform();
		Thread.sleep(1500);

		Runtime.getRuntime().exec(getBaseDir()+"\\autoIt\\scripts\\DocNoDrag.exe");
		Thread.sleep(25000);
		
		click(groupingOptionsOkBtn);
		Thread.sleep(1500);

		getAction().moveToElement(itemExpandBtn).build().perform();
		Thread.sleep(2000);
		
		click(itemExpandBtn);
		Thread.sleep(1500);
		
		Runtime.getRuntime().exec(getBaseDir()+"\\autoIt\\scripts\\ItemNameDrag.exe");
		Thread.sleep(20000);
		
		click(groupingOptionsOkBtn);
		Thread.sleep(1500);
		
		click(itemExpandBtn);
		Thread.sleep(1500);
		
		getAction().moveToElement(quantityBtn).build().perform();
		Thread.sleep(2000);

		Runtime.getRuntime().exec(getBaseDir()+"\\autoIt\\scripts\\QtyRateDragNew.exe");
		Thread.sleep(40000);
		
		getAction().moveToElement(TransactionFieldsExpBtn).build().perform();
		Thread.sleep(1500);
		
		TransactionFieldsExpBtn.click();
		
		click(ExtraFieldExpBtn);
		Thread.sleep(2000);
		
		getAction().moveToElement(DeptExpBtn).build().perform();
		Thread.sleep(2000);
		
		click(DeptExpBtn);
		Thread.sleep(2000);
		
		getAction().doubleClick(DeptNameBt).build().perform();
		Thread.sleep(2000);
		
		click(DeptExpBtn);
		Thread.sleep(2000);
		
		getAction().moveToElement(WarehouseExpBtn).build().perform();
		Thread.sleep(2000);
		
		click(WarehouseExpBtn);
		Thread.sleep(2000);
		getAction().doubleClick(WarehouseNameBtn).build().perform();
		Thread.sleep(2000);
		
		
		getAction().moveToElement(finishBtn).build().perform();
		Thread.sleep(1200);
		finishBtn.click();
		Thread.sleep(2000);

		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : "+actMessage+" Value Expected : "+expMessage);

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(userNameDisplay));
		userNameDisplay.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(userNameDisplay));
		logoutOption.click();

		checkLogin();
		Thread.sleep(8000);
		
		focusMainSearch("RD Cube Department Tag");
		Thread.sleep(15000);
		
		click(homeCustomizationBtn);
		Thread.sleep(2500);
		
		click(dataSetTab);
		Thread.sleep(2000);
		
		click(rdDatSetFilterBtn);
		Thread.sleep(2000);
		
		click(rdAdvanceFilterRemoveBtn1);
		Thread.sleep(1000);
		
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdAdvanceFilterWhereDrpdwnForPaea));
		rdAdvanceFilterWhereDrpdwnForPaea.click();
		Select s5=new Select(rdAdvanceFilterWhereDrpdwnForPaea);
		s5.selectByValue("0");


		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdAdvanceFilterNameTxt1));
		rdAdvanceFilterNameTxt1.click();
		Thread.sleep(2000);

		
		scrollToElementJSE(rdAdvanceFilterDepExpBtn);
		Thread.sleep(2000);
		rdAdvanceFilterDepExpBtn.click();
	
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdAdvanceFilterDepName));
		rdAdvanceFilterDepName.click();
		
		//rdAdvanceFilterName_DepNameBtn.click();

		Thread.sleep(2000);
		Select s3=new Select(rdAdvanceFilterOpersatorDrpdwn1);
		s3.selectByValue("0");


		Thread.sleep(2000);
		Select s4=new Select(rdAdvanceFilterValueDrpdwn1);
		s4.selectByValue("2");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdAdvanceFilterSelectTxt1));
		rdAdvanceFilterSelectTxt1.click();
		rdAdvanceFilterSelectTxt1.sendKeys("@dept");

		Thread.sleep(2999);
		rdAdvanceFilterSelectTxt1.sendKeys(Keys.TAB);

		Thread.sleep(2999);
		
		 ((JavascriptExecutor)getDriver()).executeScript("window.scrollTo(0, 0)","");
			
		 Thread.sleep(2000);
		finishBtn.click();
		Thread.sleep(2000);

		String expMessage1 = "Data saved successfully.";

		String actMessage1 = checkValidationMessage(expMessage1);

		System.out.println("Validation Message : "+actMessage1+" Value Expected : "+expMessage1);

		
		click(rdCancelBtn);
		Thread.sleep(3500);
		
		ArrayList<String>deptListArray=new ArrayList<String>();
		
		for(int i=0;i<deptNameList.size();i++)
		{
			deptListArray.add(deptNameList.get(i).getText());
		}
		
		String actDeptList=deptListArray.toString();
		String expDeptList="[INDIA, DUBAI, AMERICA, SINGPORE, EUROPE]";
		
		System.out.println("Actaul Dept Lsit 	"		+	actDeptList);
		System.out.println("Actaul Dept Lsit 	"		+	expDeptList);
		
		
		if(actMessage.equalsIgnoreCase(expMessage))
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	
	@FindBy(xpath="//*[@id='LandingGridBody']//td[10]")
	public static List<WebElement> deptNameList;
	
	@FindBy(xpath="//*[@id='LandingGridBody']//td[8]")
	public static List<WebElement> deptNameChkBoxList;
	
	public boolean checkRDCubeReporttoDisplayDepartments() throws InterruptedException
	{
		
		for(int i=0;i<deptNameList.size();i++)
		{
			String data=deptNameList.get(i).getText();
			if(data.equalsIgnoreCase("India") || data.equalsIgnoreCase("Dubai"))
			{
				deptNameChkBoxList.get(i).click();
			}
		}
		
		Thread.sleep(2000);
		
		click(sl_OkBtn);
		Thread.sleep(10000);
		
		getFluentWebDriverWait().until(ExpectedConditions.visibilityOf(sl_1stRow1stCol));
		
		String expRow1List = "[1, 1, 20, 10]";
		boolean actRow1List=ListComparisionWOOrder(reportsRow1List,expRow1List);

		
		
		String expRow2List = "[2, STDRATECOGSITEM, DUBAI, HYDERABAD, 20, 10]";
		boolean actRow2List=ListComparisionWOOrder(reportsRow2List,expRow2List);

		
		
		String expRow3List = "[3, 2, 1, 5, 5]";
		boolean actRow3List=ListComparisionWOOrder(reportsRow3List,expRow3List);

		
		
		String expRow4List = "[4, STDRATECOGSITEM, DUBAI, HYDERABAD, 1, 5, 5]";
		boolean actRow4List=ListComparisionWOOrder(reportsRow4List,expRow4List);

		
		String expRow5List = "[5, GrandTotal, 1, 25, 5]";
		boolean actRow5List=ListComparisionWOOrder(reportsRow5List,expRow5List);

		
		
		if( actRow1List && actRow2List && actRow3List 
				&& actRow4List && actRow5List )
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	@FindBy(xpath="(//*[@title='Excel'])[1]")
	public static WebElement homePageExcel;
	
	public boolean checkExporttoExcelinRDCubeReport() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException
	{
		
		click(report_CloseBtn);
		Thread.sleep(4000);
		
		for(int i=0;i<deptNameList.size();i++)
		{
			String data=deptNameList.get(i).getText();
			if(data.equalsIgnoreCase("India") || data.equalsIgnoreCase("America"))
			{
				deptNameChkBoxList.get(i).click();
			}
		}
		
		Thread.sleep(2000);
		
		
		click(homePageExcel);
		Thread.sleep(2500);
		
		click(sl_ExportPDFYesBtn);
		Thread.sleep(6000);
		
		File Efile=new File(getBaseDir()+"\\autoIt\\ExportFiles\\RDCubeExcelEntryNew.xlsx");
		
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
		
		Thread.sleep(7000);
		
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(4000);
		robot.keyPress(KeyEvent.VK_F12);
		robot.keyRelease(KeyEvent.VK_F12);
		
		
		Thread.sleep(2000);
		
		*/
			
		Runtime.getRuntime().exec(getBaseDir() + "\\autoIt\\scripts\\RDCubeExcelEntryNew.exe");
		
		Thread.sleep(8000);
		
		
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(4000);
		
	/*	
		robot.keyPress(KeyEvent.VK_ALT);
		robot.keyPress(KeyEvent.VK_F4);
		
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyRelease(KeyEvent.VK_F4);
		Thread.sleep(3000);
		
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

	 	/*getDriver().switchTo().window(openTabs.get(2)).close();
	 	Thread.sleep(1000);*/
	 /*	getDriver().switchTo().window(openTabs.get(1)).close();
	 	Thread.sleep(1000);
	 	getDriver().switchTo().window(openTabs.get(0));*/
		
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_W);
		robot.keyRelease(KeyEvent.VK_W);
		robot.keyRelease(KeyEvent.VK_CONTROL);
	
		
		
	 	 ExcelReader excelReader = new ExcelReader(POJOUtility.getExcelPath());
	     

	 	String actExcelfile = getBaseDir()+"\\autoIt\\ExportFiles\\RDCubeExcelEntryNew.xlsx";
	 	String expExcelfile = getBaseDir()+"\\autoIt\\ImportFiles\\RDCubeExcelEntryNew.xlsx";
	 	String sheet = "Sheet1";
	 	   
	 	
	 	
	 	FileInputStream fip1 = new FileInputStream(actExcelfile);
	 	Workbook workbook1  = WorkbookFactory.create(fip1);
	 	
	 	FileInputStream fip2 = new FileInputStream(expExcelfile);
	 	Workbook workbook2  = WorkbookFactory.create(fip2);
	 	
	 	boolean result = excelReader.checkExcelSheetsComparison(workbook1, workbook2,"22/12/2025");
	 	
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
	
	
	public boolean checkSavingRDDetailReportforMultipleParameter() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException 
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
		reportNameDropdown.sendKeys("RD Detail Report for Multiple Parameter");
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
		rdFieldNameTxt.sendKeys("dept");
		Thread.sleep(2000);

		rdFieldNameTxt.sendKeys(Keys.TAB);


		Thread.sleep(2000);
		Select s1=new Select(rdFieldTypeDrpdwn);
		s1.selectByValue("3");


		Thread.sleep(2000);
						
		scrollToElementJSE(rdParametersOkbtn);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdParametersOkbtn));
		rdParametersOkbtn.click();

		Thread.sleep(2999);
		
		rdFieldNameTxt.click();
		rdFieldNameTxt.sendKeys("Acc");
		Thread.sleep(2000);

		rdFieldNameTxt.sendKeys(Keys.TAB);


		Thread.sleep(2000);
		Select s2=new Select(rdFieldTypeDrpdwn);
		s2.selectByValue("1");


		Thread.sleep(2000);
						
		scrollToElementJSE(rdParametersOkbtn);
		
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdParametersOkbtn));
		rdParametersOkbtn.click();

		Thread.sleep(2999);
		
		
		getAction().moveToElement(dataSetTab).build().perform();
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(dataSetTab));
		dataSetTab.click();

		Thread.sleep(2000);

		int transactionSetListCount = transactionSetList.size();

		for(int i=0;i<=transactionSetListCount;i++)
		{
			String data = transactionSetList.get(i).getText();

			if(data.equalsIgnoreCase("Inventory Transactions"))
			{
				transactionSetList.get(i).click();

				break;
			}
		}


		Thread.sleep(4000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customizationTab));
		customizationTab.click();

		Thread.sleep(2999);
		
		
		//click(TransactionExpBtn);
		//Thread.sleep(1500);
		
		click(TransactionFieldsExpBtn);
		Thread.sleep(1500);
		
		getAction().moveToElement(DocNoBtn).doubleClick().build().perform();
		Thread.sleep(1500);

		getAction().moveToElement(DateExpBtn).click().build().perform();
		Thread.sleep(1500);
		
		doubleClick(DateBtn);
		Thread.sleep(1500);
		
		click(DateExpBtn);
		Thread.sleep(1500);
		
		getAction().moveToElement(AccountExpBtn).click().build().perform();
		Thread.sleep(1500);
		
		doubleClick(AccountNameBtn);
		Thread.sleep(1500);
		
		click(AccountExpBtn);
		Thread.sleep(1500);
		
		click(Account2ExpBtn);
		Thread.sleep(1500);
		
		doubleClick(Account2NameBtn);
		Thread.sleep(1500);
		
		getAction().moveToElement(itemExpandBtn).build().perform();
		Thread.sleep(1500);
		
		click(itemExpandBtn);
		Thread.sleep(1500);
		
		doubleClick(itemNameBtn);
		Thread.sleep(1500);
		
		click(itemExpandBtn);
		Thread.sleep(1500);
		
		getAction().moveToElement(quantityBtn).doubleClick().build().perform();
		Thread.sleep(1500);
		
		getAction().moveToElement(rateBtn).doubleClick().build().perform();
		Thread.sleep(1500);
		
		getAction().moveToElement(grossBtn).doubleClick().build().perform();
		Thread.sleep(1500);
		
		getAction().moveToElement(TransactionFieldsExpBtn).click().build().perform();
		Thread.sleep(1500);
		
		click(ExtraFieldExpBtn);
		Thread.sleep(1500);
		
		getAction().moveToElement(DeptExpBtn).click().build().perform();
		Thread.sleep(1500);
		
		doubleClick(DeptNameBt);
		Thread.sleep(1500);
		
		click(DeptExpBtn);
		Thread.sleep(1500);
		
		getAction().moveToElement(WarehouseExpBtn).click().build().perform();
		Thread.sleep(1500);
		
		doubleClick(WarehouseNameBtn);
		Thread.sleep(1500);
		
		getAction().moveToElement(finishBtn).click().build().perform();
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
	
	
	@FindBy(xpath="//*[@id='advancefilter_master_0_0_']")
	public static WebElement rdFilterTxt;
	
	
	@FindBy(xpath="(//a[contains(text(),'Department')]//span)[1]")
	private static WebElement rdAdvanceFilterDepExpBtn;
	
	@FindBy(xpath="//a[contains(text(),'Department')]//..//a[text()='Name']")
	private static WebElement rdAdvanceFilterDepName;
	
	@FindBy(xpath="//div[@class='dropdown-menu filterTree']//*[@id='23']/span")
	private static WebElement rdAdvanceFilterItemExpBtn;
	
	@FindBy(xpath="(//div[@class='dropdown-menu filterTree']//*[@id='23']/span//following::ul//a[text()='Name'])[1]")
	private static WebElement rdAdvanceFilterItemNameBtn;
	

	
	@FindBy(xpath="(//div[@class='dropdown-menu filterTree']//*[@id='23']/span//following::ul//a[text()='Group Name'])[1]")
	private static WebElement rdAdvanceFilterItemGroupNameBtn;
	
	
	@FindBy(xpath="//div[@class='dropdown-menu filterTree']//*[@id='4']/span")
	private static WebElement rdAdvanceFilterAccountExpBtn;
	
	
	@FindBy(xpath="(//div[@class='dropdown-menu filterTree']//*[@id='4']/span//following::ul//a[text()='Name'])[1]")
	private static WebElement rdAdvanceFilterAccountNameBtn;
	
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
	
	@FindBy(xpath="//span[@id='a']")
	private static WebElement rdDatSetFilterBtn;
	
	
	@FindBy(xpath="//*[@id='0_0_AdvanceFilter_']/table/tbody/tr/td[6]/span")
	private static WebElement rdAdvanceFilterRemoveBtn1;
	
	@FindBy(xpath="//*[@id='0_0_AdvanceFilter_']/table/tbody/tr/td[1]/select")
	private static WebElement rdAdvanceFilterWhereDrpdwnForPaea;
	
	@FindBy(xpath="//*[@id='22_0_AdvanceFilter_']/table/tbody/tr/td[1]/select")
	private static WebElement rdAdvanceFilterWhereDrpdwnForPaea1;
	
	@FindBy(xpath="//*[@id='0_0_AdvanceFilter_']/table/tbody/tr/td[2]/input")
   	private static WebElement rdAdvanceFilterNameTxt1;
	

	@FindBy(xpath="(//span[@class='icon-pluse icon-font6'])[2]")
   	private static WebElement rdAdvanceFilterAddBtn;
	
//2nd row filter
	
	@FindBy(xpath="//*[@id='0_0_AdvanceFilter_']/table/tbody/tr[2]/td[1]/select")
	private static WebElement rdAdvanceFilterWhereDrpdwn;
	
	@FindBy(xpath="//*[@id='0_0_AdvanceFilter_']/table/tbody/tr[2]/td[2]/input")
   	private static WebElement rdAdvanceFilterNameTxt2;
	
	@FindBy(xpath="//*[@id='0_0_AdvanceFilter_']/table/tbody/tr[2]/td[3]/select")
	private static WebElement rdAdvanceFilterOpersatorDrpdwn2;
	
	@FindBy(xpath="//*[@id='0_0_AdvanceFilter_']/table/tbody/tr[2]/td[4]/select")
	private static WebElement rdAdvanceFilterValueDrpdwn2;
	
	@FindBy(xpath="//*[@id='0_0_AdvanceFilter_']/table/tbody/tr[2]/td[5]/input")
	private static WebElement rdAdvanceFilterSelectTxt2;
	
	public boolean checkFilterinRDMultipleParameterReport() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		
		focusMainSearch("RD Detail Report for Multiple Parameter");
		Thread.sleep(4000);
		
		click(homeCustomizationBtn);
		Thread.sleep(2500);
		
		click(dataSetTab);
		Thread.sleep(2000);
		
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

		
		//scrollToElementJSE(rdAdvanceFilterAccountExpBtn);
		//Thread.sleep(2000);
		rdAdvanceFilterAccountExpBtn.click();
	
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdAdvanceFilterAccountNameBtn));
		rdAdvanceFilterAccountNameBtn.click();
		
		//rdAdvanceFilterName_DepNameBtn.click();

		Thread.sleep(2000);
		Select s3=new Select(rdAdvanceFilterOpersatorDrpdwn1);
		s3.selectByValue("0");


		Thread.sleep(2000);
		Select s4=new Select(rdAdvanceFilterValueDrpdwn1);
		s4.selectByValue("2");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdAdvanceFilterSelectTxt1));
		rdAdvanceFilterSelectTxt1.click();
		rdAdvanceFilterSelectTxt1.sendKeys("@Acc");

		Thread.sleep(2999);
		rdAdvanceFilterSelectTxt1.sendKeys(Keys.TAB);

		Thread.sleep(2999);
		
		click(rdAdvanceFilterAddBtn);
		Thread.sleep(1500);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdAdvanceFilterWhereDrpdwn));
		rdAdvanceFilterWhereDrpdwn.click();
		Select s5=new Select(rdAdvanceFilterWhereDrpdwn);
		s5.selectByValue("6");


		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdAdvanceFilterNameTxt2));
		rdAdvanceFilterNameTxt2.click();
		Thread.sleep(2000);
		
		scrollToElementJSE(rdAdvanceFilterDepExpBtn);
		Thread.sleep(5000);
		click(rdAdvanceFilterDepExpBtn);
		Thread.sleep(1500);
		
		click(rdAdvanceFilterDepName);
		Thread.sleep(1500);
		
		Thread.sleep(2000);
		Select s6=new Select(rdAdvanceFilterOpersatorDrpdwn2);
		s6.selectByValue("0");


		Thread.sleep(2000);
		Select s7=new Select(rdAdvanceFilterValueDrpdwn2);
		s7.selectByValue("2");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rdAdvanceFilterSelectTxt2));
		rdAdvanceFilterSelectTxt2.click();
		rdAdvanceFilterSelectTxt2.sendKeys("@dept");

		Thread.sleep(2999);
		rdAdvanceFilterSelectTxt2.sendKeys(Keys.TAB);

		Thread.sleep(2999);

		 ((JavascriptExecutor)getDriver()).executeScript("window.scrollTo(0, 0)","");
			
		 Thread.sleep(2000);
		finishBtn.click();
		Thread.sleep(2000);

		String expMessage = "Data saved successfully.";

		String actMessage = checkValidationMessage(expMessage);

		System.out.println("Validation Message : "+actMessage+" Value Expected : "+expMessage);

		
		click(rdCancelBtn);
		Thread.sleep(3500);
		
		click(RDDetail_BrandTxt);
		RDDetail_BrandTxt.sendKeys("AMERICA");
		Thread.sleep(2000);
		RDDetail_BrandTxt.sendKeys(Keys.TAB);
	
		
		click(RDDetail_AccTxt);
		RDDetail_AccTxt.sendKeys("Vendor A");
		Thread.sleep(2000);
		RDDetail_AccTxt.sendKeys(Keys.TAB);
		
		click(sl_OkBtn);
		Thread.sleep(8000);
		
		getFluentWebDriverWait().until(ExpectedConditions.visibilityOf(sl_1stRow1stCol));
		
		String expRow1List = "[1, 1, 26/05/2025, VendorA, Purchase, STOCKITEM, 100.00, 51.00, 5, 100.00, AMERICA, HYDERABAD]";
		boolean actRow1List=ListComparisionWOOrder(reportsRow1List,expRow1List);

		
		
		String expRow2List = "[2, GrandTotal, 100.00, 51.00, 5, 100.00]";
		boolean actRow2List=ListComparisionWOOrder(reportsRow2List,expRow2List);

		
		
		if(actMessage.equalsIgnoreCase(expMessage) && actRow1List && actRow2List)
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	
	
	
	public boolean checkYearAndMonthWiseDatainRDCubeReport() throws InterruptedException
	{
		
		Thread.sleep(2000);
		
		focusMainSearch("RD Year and Month wise Data");
		Thread.sleep(4000);
		
		click(sl_OkBtn);
		Thread.sleep(8000);
		
getFluentWebDriverWait().until(ExpectedConditions.visibilityOf(sl_1stRow1stCol));
		
		String expRow1List = "[1, 1, 20, 10, 100, 51, 5, 100, 100, 71, 5, 090]";
		boolean actRow1List=ListComparisionWOOrder(reportsRow1List,expRow1List);

		
		
		String expRow2List = "[2, STDRATECOGSITEM, 20, 10, 20, 10]";
		boolean actRow2List=ListComparisionWOOrder(reportsRow2List,expRow2List);

		
		
		String expRow3List = "[3, STOCKITEM, 100, 51, 5, 100, 100, 51, 5, 100]";
		boolean actRow3List=ListComparisionWOOrder(reportsRow3List,expRow3List);

		
		
		String expRow4List = "[4, 2, 1, 5, 5, 1, 5, 5]";
		boolean actRow4List=ListComparisionWOOrder(reportsRow4List,expRow4List);

		
		String expRow5List = "[5, STDRATECOGSITEM, 1, 5, 5, 1, 5, 5]";
		boolean actRow5List=ListComparisionWOOrder(reportsRow5List,expRow5List);

		String expRow6List = "[6, GrandTotal, 1, 25, 5, 100, 51, 5, 100, 101, 76, 5, 095]";
		boolean actRow6List=ListComparisionWOOrder(reportsRow6List,expRow6List);
		
		if(actRow1List && actRow2List && actRow3List && actRow4List && actRow5List && actRow6List)
		{
			return true;
		}
		else
		{
			return false;
		}

				
	}
	
	
	@FindBy(xpath="(//i[@class='icon-filter2 hiconright2'])[1]")
	public static WebElement homeCustomizationBtn;
	
	@FindBy(xpath="//*[contains(text(),'Rate[SUM]')]")
	public static WebElement custRateSumField; 
	
	@FindBy(xpath="(//*[@id='id_rd_columnproperty_function'])[2]")
	public static WebElement rdColPropertyFunctionSelect; 
	
	@FindBy(xpath="//*[@id='id_rd_customization_columnproperty_popup_ok']")
	public static WebElement rdCustFilterOkBtn; 
	
	
	public boolean checkRDReportAfterChangingRatetoAverage() throws InterruptedException
	{
		
		click(report_CloseBtn);
		Thread.sleep(4000);
		
		click(homeCustomizationBtn);
		Thread.sleep(1500);
		
		click(customizationTab);
		Thread.sleep(1500);
		
		doubleClick(custRateSumField);
		Thread.sleep(2000);
		
		click(rdColPropertyFunctionSelect);
		Select s= new Select(rdColPropertyFunctionSelect);
		s.selectByVisibleText("Average");
		Thread.sleep(1500);
		
		click(rdCustFilterOkBtn);
		Thread.sleep(1500);
		
		click(finishBtn);
		Thread.sleep(1500);
		
		click(rdCancelBtn);
		Thread.sleep(2000);
		
		click(sl_OkBtn);
		Thread.sleep(8000);
		
		String expRow1List = "[1, 1, 7, 10, 100, 51, 5, 100, 100, 18, 5, 090]";
		boolean actRow1List=ListComparisionWOOrder(reportsRow1List,expRow1List);

		
		
		String expRow2List = "[2, STDRATECOGSITEM, 7, 10, 7, 10]";
		boolean actRow2List=ListComparisionWOOrder(reportsRow2List,expRow2List);

		
		
		String expRow3List = "[3, STOCKITEM, 100, 51, 5, 100, 100, 51, 5, 100]";
		boolean actRow3List=ListComparisionWOOrder(reportsRow3List,expRow3List);

		
		
		String expRow4List = "[4, 2, 1, 5, 5, 1, 5, 5]";
		boolean actRow4List=ListComparisionWOOrder(reportsRow4List,expRow4List);

		
		String expRow5List = "[5, STDRATECOGSITEM, 1, 5, 5, 1, 5, 5]";
		boolean actRow5List=ListComparisionWOOrder(reportsRow5List,expRow5List);

		String expRow6List = "[6, GrandTotal, 1, 12, 5, 100, 51, 5, 100, 101, 23, 5, 095]";
		boolean actRow6List=ListComparisionWOOrder(reportsRow6List,expRow6List);
		
		if(actRow1List && actRow2List && actRow3List && actRow4List && actRow5List && actRow6List)
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	@FindBy(xpath="(//a[@title='Cancel'])[1]")
	public static WebElement rdCancelBtn;
	
	@FindBy(xpath = "//input[@id='id_body_33554460']")
	public static WebElement so_enter_AQTxt;

	@FindBy(xpath = "//input[@id='id_body_33554461']")
	public static WebElement so_enter_FQTxt;

	
	public boolean checkSavingVoucherwithTwoRowsinSalesOrder() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(inventoryMenu));
		inventoryMenu.click();
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(inventoryTransactionsMenu));
		inventoryTransactionsMenu.click();
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(inventoryTransactionsSalesMenu));
		inventoryTransactionsSalesMenu.click();
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(salesOrdersVoucher));
		salesOrdersVoucher.click();

		Thread.sleep(25000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(newBtn));
		newBtn.click();

	//	checkValidationMessage("Screen opened");
		Thread.sleep(6000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNumberTxt));

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(customerAccountTxt));
		customerAccountTxt.sendKeys("Customer A");
		Thread.sleep(2000);
		customerAccountTxt.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_1stColumn));
		select1stRow_1stColumn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(pvWareHouseTxt));
		pvWareHouseTxt.sendKeys("HYDERABAD");
		Thread.sleep(2000);
		pvWareHouseTxt.sendKeys(Keys.TAB);


		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_ItemTxt));
		enter_ItemTxt.sendKeys("STD RATE COGS");
		Thread.sleep(2000);
		enter_ItemTxt.sendKeys(Keys.TAB);	

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_UnitTxt));
		enter_UnitTxt.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_5thColumn));
		select1stRow_5thColumn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(so_enter_AQTxt));
		so_enter_AQTxt.sendKeys("3");
		so_enter_AQTxt.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(so_enter_FQTxt));
		so_enter_FQTxt.sendKeys("2");
		so_enter_FQTxt.sendKeys(Keys.TAB);
		Thread.sleep(4000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_Quantity));
		enter_Quantity.sendKeys(Keys.TAB);
		
		Thread.sleep(2000);
		click(enterStdReservationCol);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_QtyToReserve));
		enter_QtyToReserve.sendKeys("5");
		enter_QtyToReserve.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(res_OkBtn));
		res_OkBtn.click();

		Thread.sleep(4000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_Rate));
		enter_Rate.click();
		enter_Rate.clear();
		enter_Rate.sendKeys("10");
		enter_Rate.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_Gross));
		enter_Gross.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_8thColumn));

		String actReserveColumn	=select1stRow_8thColumn.getText();
		String expReserveColumn	="Reserved";

		System.out.println("Reservation Column  : "+actReserveColumn+"  Value Expected  "+expReserveColumn);

		
		//2nd Row
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select2ndRow_1stColumn));
		select2ndRow_1stColumn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(pvWareHouseTxt));
		//pvWareHouseTxt.sendKeys("HYDERABAD");
		//Thread.sleep(2000);
		pvWareHouseTxt.sendKeys(Keys.TAB);


		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_ItemTxt));
		enter_ItemTxt.sendKeys("STD RATE COGS");
		Thread.sleep(2000);
		enter_ItemTxt.sendKeys(Keys.TAB);	

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_UnitTxt));
		enter_UnitTxt.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select2ndRow_5thColumn));
		select2ndRow_5thColumn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(so_enter_AQTxt));
		so_enter_AQTxt.sendKeys("3");
		so_enter_AQTxt.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(so_enter_FQTxt));
		so_enter_FQTxt.sendKeys("8");
		so_enter_FQTxt.sendKeys(Keys.TAB);
		Thread.sleep(4000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_Quantity));
		enter_Quantity.sendKeys(Keys.TAB);
		
		Thread.sleep(2000);
		
		click(enterStdReservationCol);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_QtyToReserve));
		enter_QtyToReserve.sendKeys("11");
		enter_QtyToReserve.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(res_OkBtn));
		res_OkBtn.click();

		Thread.sleep(4000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_Rate));
		enter_Rate.click();
		enter_Rate.clear();
		enter_Rate.sendKeys("15.58");
		enter_Rate.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_Gross));
		enter_Gross.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select2ndRow_8thColumn));

		String actReserveColumn1	=select2ndRow_8thColumn.getText();
		String expReserveColumn1	="Reserved";

		System.out.println("Reservation Column  : "+actReserveColumn1+"  Value Expected  "+expReserveColumn1);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(MRsaveBtn));
		MRsaveBtn.click();
		Thread.sleep(2000);
		
		String expMessage1="Voucher saved successfully";
		String actMessage = checkValidationMessage(expMessage1);
		
		
		String expMessage2 =": 1";
		
	
	
		System.out.println("Actual Message    : "+actMessage);
		System.out.println("Expected Message  : "+expMessage1);

		
		if (actReserveColumn.equalsIgnoreCase(expReserveColumn) && actMessage.startsWith(expMessage1)&& actMessage.endsWith(expMessage2)) 
		{
			
			return true;
		} 
		else 
		{
			
			return false;
		}
	
	}
	
	
	
	public boolean checkSavingVoucherConsumingoneRowinSalesInvoiceVAT() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException
	{
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(financialsMenu));
		financialsMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(financialsTransactionMenu));
		financialsTransactionMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(financialTransactionSalesMenu));
		financialTransactionSalesMenu.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(salesInvoiceVATVoucher));
		salesInvoiceVATVoucher.click();

		Thread.sleep(12000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(newBtn));
		newBtn.click();

		//checkValidationMessage("Screen opened");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNumberTxt));
		documentNumberTxt.click();
		Thread.sleep(2000);
		Robot robot=new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_L);
		robot.keyRelease(KeyEvent.VK_L);
		robot.keyRelease(KeyEvent.VK_CONTROL);

		Thread.sleep(8000);

		click(linksalesOrder1stRow2ndCol);
		
		click(linksalesOrderOkBtn);
		Thread.sleep(2000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(departmentTxt));
		departmentTxt.sendKeys("INDIA");
		Thread.sleep(2000);
		departmentTxt.sendKeys(Keys.TAB);	

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(salesInvoiceVATPlaceOFSupply));
		salesInvoiceVATPlaceOFSupply.sendKeys("Abu Dhabi");
		Thread.sleep(2000);
		salesInvoiceVATPlaceOFSupply.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(jurisdictionTxt));
		jurisdictionTxt.sendKeys("Dubai");
		Thread.sleep(2000);
		jurisdictionTxt.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_3rdColumn));
		select1stRow_3rdColumn.click();
		Thread.sleep(2000);
		enter_TaxCode.sendKeys(Keys.TAB);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(voucherSaveBtn));
		voucherSaveBtn.click();
		
		Thread.sleep(6000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(newReferenceTxt));
		newReferenceTxt.click();
		

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(billRefPickIcon));
		billRefPickIcon.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(billRefOkBtn));
		billRefOkBtn.click();
		Thread.sleep(2000);
		
		
		String expMessage1 = "Voucher saved successfully";
		
		String actMessage = checkValidationMessage(expMessage1);
		String expMessage2 = ": 1";
	
		System.out.println("Actual Message    : "+actMessage);
		System.out.println("Expected Message  : "+expMessage1);

		if(actMessage.startsWith(expMessage1)&& actMessage.endsWith(expMessage2))
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	@FindBy(xpath="//*[@id='id_transaction_entry_detail_workflow_popup_body']//tr[1]//td[2]")
	public static WebElement linksalesOrder1stRow2ndCol;
	
	@FindBy(xpath="(//input[@value='Ok'])[3]")
	public static WebElement linksalesOrderOkBtn;
	
	
	public boolean checkRDPendingLinksReport() throws InterruptedException
	{
		
		Thread.sleep(4000);
		focusMainSearch("RD Pending links");
		Thread.sleep(4000);
		
		click(sl_OkBtn);
		Thread.sleep(8000);
		
		getFluentWebDriverWait().until(ExpectedConditions.visibilityOf(sl_1stRow1stCol));
		
		String expRow1List = "[1, 1, 11.00, 15.58, 171.38]";
		boolean actRow1List=ListComparisionWOOrder(reportsRow1List,expRow1List);

		
		
		String expRow2List = "[2, STDRATECOGSITEM, 11.00, 15.58, 171.38, Pending]";
		boolean actRow2List=ListComparisionWOOrder(reportsRow2List,expRow2List);

		
		
		String expRow3List = "[3, GrandTotal, 11.00, 15.58, 171.38]";
		boolean actRow3List=ListComparisionWOOrder(reportsRow3List,expRow3List);

		Thread.sleep(1500);
		
		if(actRow1List && actRow2List && actRow3List)
		{
			return true;
		}
		else
		{
			return false;
		}
		
		
	}
	
	
	
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
		 
	
	
	public ReportDesignerNew(WebDriver driver)
	{
		  PageFactory.initElements(driver, this);
	}
	
	
}