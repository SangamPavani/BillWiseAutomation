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
import org.openqa.selenium.support.ui.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.focus.base.BaseEngine;
import com.focus.utilities.DriverUtility;

public class ReportDesignerNew1Page  extends BaseEngine{
	
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

		String compname = "Testing 22";

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

		checkRestoreOptionsCompanyAndLogin("Testing 22 New1", "Testing 22");

	
	}


	public boolean checkLogoutandLoginWithUser() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		 getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(userNameDisplayLogo));
		  userNameDisplayLogo.click();
		  Thread.sleep(2000);
		 
		  getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(logoutOption));
		  logoutOption.click();
		  
			LoginPage lp = new LoginPage(getDriver());

			lp.checkLoginPageTitleByURLInputInBrowser(DriverUtility.FINUrl);

			Thread.sleep(3000);

			String unamelt = "suchi";

			String pawslt = "su";

			lp.enterUserName(unamelt);

			Thread.sleep(2000);

			lp.enterPassword(pawslt);
			
			companyDropDownList.click();

			String compname = "Testing 22";

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
	
	
	public boolean checkRDwarehousewiseDataReport() throws InterruptedException
	{
		
		focusMainSearch("RD warehouse wise Data");
		Thread.sleep(4000);
		
		click(sl_OkBtn);
		Thread.sleep(4000);
		
		String expRow1List = "[1, Rural, 3, 565.09]";
		boolean actRow1List=ListComparisionWOOrder(reportRow1List,expRow1List);

		String expRow2List = "[2, wh2, 2, 647.83]";
		boolean actRow2List=ListComparisionWOOrder(reportRow2List,expRow2List);
		
		String expRow3List = "[3, wh3, 917.26]";
		boolean actRow3List=ListComparisionWOOrder(reportRow3List,expRow3List);
		
		String expRow4List = "[4, GrandTotal, 3, 565.09]";
		boolean actRow4List=ListComparisionWOOrder(reportRow4List,expRow4List);
		
		click(report_CloseBtn);
		
		if(actRow1List && actRow2List && actRow3List && actRow4List)
		{
			return true;
		}
		else
		{
			return false;
		}

	}

	
	public boolean checkLogoutandLoginWithSU() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		 getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(userNameDisplayLogo));
		  userNameDisplayLogo.click();
		  Thread.sleep(4000);
		 
		  getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(logoutOption));
		  logoutOption.click();
		  
			LoginPage lp = new LoginPage(getDriver());

			lp.checkLoginPageTitleByURLInputInBrowser(DriverUtility.FINUrl);

			Thread.sleep(3000);

			String unamelt = "su";

			String pawslt = "su";

			lp.enterUserName(unamelt);

			Thread.sleep(2000);

			lp.enterPassword(pawslt);
			
			companyDropDownList.click();

			String compname = "Testing 22";

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
	
	@FindBy(xpath="//*[@id='id_reportmenudisplay']//i[@class='icon-filter hiconright2']")
	public static WebElement report_FilterExpandBtn;
	
	@FindBy(xpath="(//li[text()='Filter'])[2]")
	public static WebElement report_FilterBtn;
	
	@FindBy(xpath = "//*[@id='idFilterCustomizeIcon']")
	public static WebElement report_FilterCustomizeBtn;
	
	@FindBy (xpath="(//a[contains(text(),'Item')]//i)[1]")
	private static WebElement report_FilterItemExpansion;
	
	@FindBy (xpath="(//*[@id='5021'])[1]//..//span")
	private static WebElement report_FilterItemNameChkbox;
	
	@FindBy(xpath = "(//*[@class='FButton-Primary'])[1]")
	public static WebElement filter_FilterOkButton;
	
	
	@FindBy (xpath="//*[@id='FOption_70066_0_DefaultFilter_0']")
	//@FindBy(xpath="(//table[@class='option-btn-table'])[1]//following::input[@id='FOption_70069_0_DefaultFilter_0']")
	private static WebElement report_FilterItemDefaulTxt;
	
	@FindBy(xpath = "//*[@id='filter_Okbtn_']")
	public static WebElement filterOkButton;
	
	@FindBy (xpath="(//*[@id='5021'])[1]")
	private static WebElement report_FilterItemNameChkboxSelected;
	
	
	@FindBy(xpath="//*[@id='dvReportDetails']//table/tbody/tr[1]/td[10]")
	public static WebElement report1stRow9thCol;
	
	
	
	public boolean checkRDItemWiseDataReport() throws InterruptedException
	{
		focusMainSearch("RD item wise Data");
		Thread.sleep(4000);
		
		click(sl_OkBtn);
		Thread.sleep(4000);
		
		click(report_FilterExpandBtn);
		click(report_FilterBtn);
		click(report_FilterCustomizeBtn);
		click(report_FilterItemExpansion);
		
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
		report_FilterItemDefaulTxt.sendKeys("item4");
		
		Thread.sleep(2000);
		
		report_FilterItemDefaulTxt.sendKeys(Keys.TAB);
		
		Thread.sleep(4000);
		
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(filterOkButton));
		filterOkButton.click();

		Thread.sleep(12000);
		
	

		String expRow1List = "[1, item4, 263.48, 5.78, True, 58, 14/04/2025, 27/11/20255:11:15PM, 5.13, Chennai, MGIT, 1, 066, No, 12, i4, 37.70]";
		boolean actRow1List=ListComparisionWOOrder(reportRow1List,expRow1List);
		
		String expRow2List = "[2, GrandTotal, 5.78, 263.48, 5.78, 5.13, 1, 066, 37.70]";
		boolean actRow2List=ListComparisionWOOrder(reportRow2List,expRow2List);
		
		
		String actFractionDataforItem=report1stRow9thCol.getText();
		String expFractionDataforItem="5.13";
		
		System.out.println("Fraction value for Item 	"	+	actFractionDataforItem		+		"Expected"		+	expFractionDataforItem);
		
		
		if(actRow1List && actRow2List && actFractionDataforItem.equalsIgnoreCase(expFractionDataforItem))
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	
	@FindBy(xpath="//*[@id='70066_0_AdvanceFilter_']/table/tbody/tr/td[5]/input")
	public static WebElement filterValueField;
	
	@FindBy(xpath="//*[@id='advancefilter_master_70066_0_']")
	public static WebElement filterValueTxt;
	
	
	@FindBy(xpath="//*[@id='dvReportDetails']//table/tbody/tr[1]/td[3]")
	public static WebElement report1stRow3rdCol;
	
	@FindBy(xpath="//*[@id='dvReportDetails']//table/tbody/tr[1]/td[4]")
	public static WebElement report1stRow4thCol;
	
	@FindBy(xpath="//*[@id='dvReportDetails']//table/tbody/tr[1]/td[5]")
	public static WebElement report1stRow5thCol;
	
	@FindBy(xpath="//*[@id='dvReportDetails']//table/tbody/tr[1]/td[10]")
	public static WebElement report1stRow10thCol;
	
	@FindBy(xpath="//*[@id='dvReportDetails']//table/tbody/tr[1]/td[11]")
	public static WebElement report1stRow11thCol;
	
	
	@FindBy(xpath="//*[@id='LandingGridBody']/tr/td[12]")
	public static List<WebElement> reportItemNameList;
	
	@FindBy(xpath="//*[@id='LandingGridBody']/tr/td[8]")
	public static List<WebElement> reportItemChkBoxList;
	
	
	public boolean checkCompareValuesinRDItemWiseReporttoStockMovementReport() throws InterruptedException
	{
		
		click(report_FilterExpandBtn);
		click(report_FilterBtn);
		click(filterValueField);
		click(filterValueTxt);
		filterValueTxt.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
		filterValueTxt.sendKeys("RMA1");
		Thread.sleep(4000);
		filterValueTxt.sendKeys(Keys.TAB);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(filterOkButton));
		filterOkButton.click();

		Thread.sleep(12000);
		
		String actOpeningStockValueforItem=report1stRow3rdCol.getText();
		
		
		String actStockValueforItem=report1stRow4thCol.getText();
		
		
		
		String actStockRateforItem=report1stRow5thCol.getText();
		
		
		
		focusMainSearch("Stock Movement");
		Thread.sleep(4000);
		
		for(int i=0;i<reportItemNameList.size();i++)
		{
			
			if(reportItemNameList.get(i).getText().equalsIgnoreCase("RMA1"))
			{
				reportItemChkBoxList.get(i).click();
				break;
			}
		}
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_DateOptionDropdown));
		sl_DateOptionDropdown.click();
		Select s=new Select(sl_DateOptionDropdown);
		s.selectByValue("1");
		
		Thread.sleep(3000);
		
		click(sl_OkBtn);
		Thread.sleep(4000);
		
		
		String expOpeningStockValueforItem=report1stRow3rdCol.getText();
		String expStockValueforItem=report1stRow10thCol.getText();
		String expStockRateforItem=report1stRow11thCol.getText();
		
		
		System.out.println("Opening Stock value for Item 	"	+	actOpeningStockValueforItem		+		"Expected"		+	expOpeningStockValueforItem);
		System.out.println("Stock value for Item 			"	+	actStockValueforItem			+		"Expected"		+	expStockValueforItem);
		System.out.println("Stock rate for Item 			"	+	actStockRateforItem				+		"Expected"		+	expStockRateforItem);
		
		if(actOpeningStockValueforItem.equalsIgnoreCase(expOpeningStockValueforItem) && actStockValueforItem.equalsIgnoreCase(expStockValueforItem)
				&& actStockRateforItem.equalsIgnoreCase(expStockRateforItem))
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	
	public boolean checkRDAccountWiseDataReport() throws InterruptedException
	{
		focusMainSearch("RD Account wise data");
		Thread.sleep(4000);
		
		click(sl_OkBtn);
		Thread.sleep(8000);
		
		String expRow1List = "[1, False, FOCUS, Yes]";
		boolean actRow1List=ListComparisionWOOrder(reportRow1List,expRow1List);
		
		String expRow2List = "[2, Costofgoodssold-Computers, False, Mubai, FOCUS, Yes]";
		boolean actRow2List=ListComparisionWOOrder(reportRow2List,expRow2List);
		
		
		String expRow3List = "[3, CustomDutyPayable, False, FOCUS, Yes]";
		boolean actRow3List=ListComparisionWOOrder(reportRow3List,expRow3List);
		
		String expRow4List = "[4, CustomerA, False, 85, 16:24:37, 26/03/2025, 27/11/20254:24:37PM, 202.08, Mubai, GOOGLE, 54, No]";
		boolean actRow4List=ListComparisionWOOrder(reportRow4List,expRow4List);
		
		String expRow5List = "[5, CustomerB, True, 43, 16:51:26, 30/08/2025, 27/11/20254:51:26PM, 340.79, Hyd, Mubai, FOCUS, 25, Yes]";
		boolean actRow5List=ListComparisionWOOrder(reportRow5List,expRow5List);
		
		String expRow6List = "[6, CustomerC, False, FOCUS, Yes]";
		boolean actRow6List=ListComparisionWOOrder(reportRow6List,expRow6List);
		
		String expRow7List = "[7, VendorA, True, 99, 16:04:40, 18/03/2025, 27/11/20254:04:40PM, 192.24, Mubai, Chennai, IBM, 44, Yes]";
		boolean actRow7List=ListComparisionWOOrder(reportRow7List,expRow7List);
		
		String expRow8List = "[8, VendorB, False, 67, 16:06:36, 30/11/2025, 27/11/20254:06:36PM, 2, 420.51, Pune, Mubai, HCL, 23, No]";
		boolean actRow8List=ListComparisionWOOrder(reportRow8List,expRow8List);
		
		String expRow9List = "[9, VendorC, False, Pune, FOCUS, Yes]";
		boolean actRow9List=ListComparisionWOOrder(reportRow9List,expRow9List);
		
		String expRow10List = "[10, GrandTotal, 3, 155.62, 146]";
		boolean actRow10List=ListComparisionWOOrder(reportRow10List,expRow10List);
		
		if(actRow1List && actRow2List && actRow3List && actRow4List && actRow5List && actRow6List 
				&& actRow7List && actRow8List && actRow9List && actRow10List)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	@FindBy(xpath="(//a[@title='Graph'])[1]")
	public static WebElement reportHomeGraph;
	
	@FindBy(xpath="(//a[@title='Graph'])[2]")
	public static WebElement reportEntryGraph;
	
	@FindBy(xpath="//*[@id='GraphOptionscolumns']//li")
	public static List<WebElement> reportGraphColList;
	
	
	@FindBy(xpath="(//*[text()='1,090'])[2]")
	public static WebElement reportGraphRAM2Txt;
	
	@FindBy(xpath="//*[@id='rightArrow']")
	public static WebElement reportGraphRightArrow;
	
	@FindBy(xpath="//*[@id='graphOpionsHeading']/ul/li[1]")
	public static WebElement reportGraphOkBtn;
	
	@FindBy(xpath="//tr[@class='c3-tooltip-name-RMA2']//td[2]")
	public static WebElement graphRMA2Per;
	
	@FindBy(xpath="//*[text()='6,979']")
	public static WebElement reportGraph36Txt;
	
	@FindBy(xpath="//tr[@class='c3-tooltip-name-36']//td[2]")
	public static WebElement graph36Per;
	
	
	
	public boolean checkGraphinCubeReport() throws InterruptedException
	{
		
		focusMainSearch("Cube Report");
		Thread.sleep(4000);
		
		click(sl_OkBtn);
		Thread.sleep(8000);
		
		click(reportEntryGraph);
		Thread.sleep(8000);
		
		for(int i=0;i<reportGraphColList.size();i++)
		{
			
			if(reportGraphColList.get(i).getText().equalsIgnoreCase("Quantity November"))
			{
				reportGraphColList.get(i).click();
				break;
			}
		}
		
		
		click(reportGraphRightArrow);
		
		
		click(reportGraphOkBtn);
		Thread.sleep(5000);
		
		getAction().moveToElement(reportGraphRAM2Txt).perform();
		
	        String acttooltipText = graphRMA2Per.getText();
	        String exptooltipTxt="47.1%";
	        
	        System.out.println("Tooltip text: " + acttooltipText	+	"Expected		"		+		exptooltipTxt);

	        if(acttooltipText.equalsIgnoreCase(acttooltipText))
	        {
	        	return true;
	        }
	        else
	        {
	        	return false;
	        }
	}
	
	
	public boolean checkDetailRDReportforGraph() throws InterruptedException
	{
		
		focusMainSearch("Detail RD");
		Thread.sleep(4000);
		
		
		click(reportHomeGraph);
		Thread.sleep(8000);
		
		for(int i=0;i<reportGraphColList.size();i++)
		{
			
			if(reportGraphColList.get(i).getText().equalsIgnoreCase("Quantity") || reportGraphColList.get(i).getText().equalsIgnoreCase("Rate")
					|| reportGraphColList.get(i).getText().equalsIgnoreCase("Gross"))
			{
				reportGraphColList.get(i).click();
			
			}
		}
		
		
		click(reportGraphRightArrow);
		
		
		click(reportGraphOkBtn);
		Thread.sleep(5000);
		
		
		getAction().moveToElement(reportGraph36Txt).perform();
		
		 	String acttooltipText = graph36Per.getText();
	        String exptooltipTxt="11.3%";
	        
	        System.out.println("Tooltip text: " + acttooltipText	+	"Expected		"		+		exptooltipTxt);

	        if(acttooltipText.equalsIgnoreCase(acttooltipText))
	        {
	        	return true;
	        }
	        else
	        {
	        	return false;
	        }
	}
	
	
	@FindBy(xpath="//a[@title='Cross-reference']")
	public static WebElement reportCrossRef;
	
	@FindBy(xpath="//*[@id='CrossReferenceOptions']//li//a[contains(text(),'Account Info')]")
	public static WebElement reportCrossRefAccInf;
	
	
	@FindBy(xpath="//*[@id='CrossReferenceOptions']//li//a[contains(text(),'Account Query')]")
	public static WebElement reportCrossRefAccQuery;
	
	@FindBy(xpath="//*[@id='CrossReferenceOptions']//li//a[contains(text(),'Account Report')]")
	public static WebElement reportCrossRefAccReport;
	
	@FindBy(xpath="//*[@id='CrossReferenceOptions']//li//a[contains(text(),'Item Info')]")
	public static WebElement reportCrossRefItemInf;
	
	@FindBy(xpath="//*[@id='CrossReferenceOptions']//li//a[contains(text(),'Item Query')]")
	public static WebElement reportCrossRefItemQuery;
	
	@FindBy(xpath="//*[@id='CrossReferenceOptions']//li//a[contains(text(),'Item Report')]")
	public static WebElement reportCrossRefItemReport;
	
	@FindBy(xpath="//*[@id='dvReportDetails']//table//tr//td[2]")
	public static List<WebElement> reportTable2ndColList;
	
	
	@FindBy(xpath="//*[@id='tBodyShowModificationTable']//tr[1]//td")
	public static List<WebElement> reportCrossRefAccInfo1stRowList;
	
	@FindBy(xpath="//*[@id='tBodyShowModificationTable']//tr[2]//td")
	public static List<WebElement> reportCrossRefAccInfo2ndRowList;
	
	@FindBy(xpath="//*[@id='tBodyShowModificationTable']//tr[3]//td")
	public static List<WebElement> reportCrossRefAccInfo3rdRowList;
	
	
	@FindBy(xpath="(//input[@value='Close'])[1]")
	public static WebElement reportCrossRefAccInfoCloseBtn;
	
	
	public boolean checkCrossreferenceAccountInfoinRDDetailReport() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		
		focusMainSearch("RD Detail");
		Thread.sleep(4000);
		
		click(sl_OkBtn);
		Thread.sleep(8000);
		
		click(report_NextBtn);
		Thread.sleep(2000);

		click(report_NextBtn);
		Thread.sleep(2000);
		
		for(int i=0;i<reportTable2ndColList.size();i++)
		{
			if(reportTable2ndColList.get(i).getText().equalsIgnoreCase("Focus_21"))
			{
				reportTable2ndColList.get(i).click();
				break;
			}
		}
		
				
		click(reportCrossRef);
		Thread.sleep(2000);
		
		click(reportCrossRefAccInf);
		
		Thread.sleep(5000);
		
		String expRow1List = "[Purchase, 091-002, SU, 25/11/202110:51:45AM, SU, 27/11/20254:22:46PM]";
		boolean actRow1List=ListComparisionWOOrder(reportCrossRefAccInfo1stRowList,expRow1List);
		
		String expRow2List = "[VendorA, 033-001, SU, 25/11/202110:51:45AM, SU, 27/11/20254:50:29PM]";
		boolean actRow2List=ListComparisionWOOrder(reportCrossRefAccInfo2ndRowList,expRow2List);
		
		click(reportCrossRefAccInfoCloseBtn);
		
		if(actRow1List && actRow2List )
		{
			return true;
		}
		else
		{
			return false;
		}
		
		
	}
	
	
	@FindBy(xpath="(//h4[@class='modal-title'])[2]")
	public static WebElement reportCrossRefAccQurytitle;
	
	
	@FindBy(xpath="((//div[@class='modal-body'])[2]//div[@class='row'])[1]//label")
	public static List<WebElement> reportCrossRefAccQury1stRowList;
	
	@FindBy(xpath="((//div[@class='modal-body'])[2]//div[@class='row'])[2]//label")
	public static List<WebElement> reportCrossRefAccQury2ndRowList;
	
	@FindBy(xpath="((//div[@class='modal-body'])[2]//div[@class='row'])[3]//label")
	public static List<WebElement> reportCrossRefAccQury3rdRowList;
	
	@FindBy(xpath="((//div[@class='modal-body'])[2]//div[@class='row'])[4]//label")
	public static List<WebElement> reportCrossRefAccQury4thRowList;
	
	
	@FindBy(xpath="((//div[@class='modal-body'])[2]//div[@class='row'])[5]//label")
	public static List<WebElement> reportCrossRefAccQury5thRowList;
	
	@FindBy(xpath="((//div[@class='modal-body'])[2]//div[@class='row'])[6]//label")
	public static List<WebElement> reportCrossRefAccQury6thRowList;
	
	@FindBy(xpath="((//div[@class='modal-body'])[2]//div[@class='row'])[7]//label")
	public static List<WebElement> reportCrossRefAccQury7thRowList;
	
	@FindBy(xpath="((//div[@class='modal-body'])[2]//div[@class='row'])[8]//label")
	public static List<WebElement> reportCrossRefAccQury8thRowList;
	
	
	@FindBy(xpath="//*[@id='CrossAccount']")
	public static WebElement reportCrossAccTxt;
	
	@FindBy(xpath="//*[@id='CrossReport']")
	public static WebElement reportCrossReportTxt;
	
	
	@FindBy(xpath="(//*[@id='crossReference']//div[3]//input[1])[4]")
	public static WebElement reportCrossReportOkBtn;
	
	
	@FindBy(xpath="//*[@id='CrossProduct']")
	public static WebElement reportCrossItemTxt;
	
	public boolean checkAccountQueryandAccountReportofCrossRefinRDDetailReport() throws InterruptedException
	{
		
		Thread.sleep(4000);
		click(reportCrossRef);
		Thread.sleep(2000);
		
		click(reportCrossRefAccQuery);
		
		Thread.sleep(8000);
		
		
		System.out.println("**************************             Account Query Details                  **************************");
		
	
		
		String expRow1List = "[OpeningBalance, 0.00]";
		boolean actRow1List=ListComparisionWOOrder(reportCrossRefAccQury1stRowList,expRow1List);
		
		String expRow2List = "[Debit, 673.50]";
		boolean actRow2List=ListComparisionWOOrder(reportCrossRefAccQury2ndRowList,expRow2List);
		
		
		String expRow3List = "[Credit, 2, 965.18]";
		boolean actRow3List=ListComparisionWOOrder(reportCrossRefAccQury3rdRowList,expRow3List);
		
		String expRow4List = "[BalanceAmount, 2, 291.68Cr]";
		boolean actRow4List=ListComparisionWOOrder(reportCrossRefAccQury4thRowList,expRow4List);
		
		click(reportCrossRefAccInfoCloseBtn);
		
		Thread.sleep(4000);
		click(reportCrossRef);
		Thread.sleep(2000);
		
		click(reportCrossRefAccReport);
		
		Thread.sleep(5000);
		
		System.out.println("**************************             Account Report  Details                  **************************");
		
		Select s=new Select(reportCrossAccTxt);
		String actSelectedAccTxt=s.getFirstSelectedOption().getText();
		String expSelectedAccTxt="Vendor A [033-001]";
		
		System.out.println("Actual Account Txt		"		+	actSelectedAccTxt		+	"Expected	"		+	expSelectedAccTxt);
		
		click(reportCrossReportTxt);
		reportCrossReportTxt.sendKeys("Best Selling Item");
		Thread.sleep(4000);
		reportCrossReportTxt.sendKeys(Keys.TAB);
		Thread.sleep(2000);
		
		click(reportCrossReportOkBtn);
		Thread.sleep(2500);
		
		ArrayList<String> openTabs = new ArrayList<String>(getDriver().getWindowHandles());
				
		getDriver().switchTo().window(openTabs.get(1));
		Thread.sleep(3000);
		
		String expReportRow1List = "[1, item1, 303.00, 2, 021.00]";
		boolean actReportRow1List=ListComparisionWOOrder(report1stRowList,expReportRow1List);
		
		String expReportRow2List = "[2, ZARA-TOP, 199.00, 1, 791.00]";
		boolean actReportRow2List=ListComparisionWOOrder(report2ndRowList,expReportRow2List);
		
		
		String expReportRow3List = "[3, item3, 118.36, 597.84]";
		boolean actReportRow3List=ListComparisionWOOrder(report3rdRowList,expReportRow3List);
		
		String expReportRow4List = "[4, item2, 86.00, 980.00]";
		boolean actReportRow4List=ListComparisionWOOrder(report4thRowList,expReportRow4List);
		
		String expReportRow5List = "[5, item4, 67.41, 428.25]";
		boolean actReportRow5List=ListComparisionWOOrder(report5thRowList,expReportRow5List);
		
		String expReportRow6List = "[6, RMA1, 8.00, 47.00]";
		boolean actReportRow6List=ListComparisionWOOrder(report6thRowList,expReportRow6List);
		
		
		String expReportRow7List = "[7, RMA2, 8.00, 60.00]";
		boolean actReportRow7List=ListComparisionWOOrder(report7thRowList,expReportRow7List);
		
		String expReportRow8List = "[8, GrandTotal, 789.77, 5, 925.09]";
		boolean actReportRow8List=ListComparisionWOOrder(report8thRowList,expReportRow8List);
		
		getDriver().switchTo().window(openTabs.get(1)).close();
		
		Thread.sleep(1000);
		
		getDriver().switchTo().window(openTabs.get(0));
		Thread.sleep(1000);
		
		if(actRow1List && actRow2List && actRow3List && actRow4List && actReportRow1List && actReportRow2List && actReportRow3List
				&& actReportRow4List && actReportRow5List && actReportRow6List
				&& actReportRow7List && actReportRow8List)
		{
			return true;
		}
		else
		{
			return false;
		}
		
		
	}
	
	
	public boolean checkItemInfoItemQueryandItemReportofCrossRefinRDDetailReport() throws InterruptedException
	{
		click(reportCrossRef);
		Thread.sleep(2000);
		
		click(reportCrossRefItemInf);
		
		Thread.sleep(5000);
		
		System.out.println("**************************             Item Info Details                  **************************");
		
		String expRow1List = "[item1, i1, SU, 25/11/202111:12:59AM, SU, 27/11/20255:06:55PM, 1]";
		boolean actRow1List=ListComparisionWOOrder(reportCrossRefAccInfo1stRowList,expRow1List);
		
		String expRow2List = "[GrandTotal, 1]";
		boolean actRow2List=ListComparisionWOOrder(reportCrossRefAccInfo2ndRowList,expRow2List);
		
	
		click(reportCrossRefAccInfoCloseBtn);
		
		Thread.sleep(4000);
		click(reportCrossRef);
		Thread.sleep(2000);
		
		click(reportCrossRefItemQuery);
		
		Thread.sleep(5000);
		
		
		System.out.println("**************************             Item Query Details                  **************************");
		
		
		
		String expQryRow1List = "[OpeningStockQuantity, 25.00]";
		boolean actQryRow1List=ListComparisionWOOrder(reportCrossRefAccQury1stRowList,expQryRow1List);
		
		String expQryRow2List = "[Pendingpurchasesorders, 88.00]";
		boolean actQryRow2List=ListComparisionWOOrder(reportCrossRefAccQury2ndRowList,expQryRow2List);
		
		
		String expQryRow3List = "[PendingSalesOrders, 0.00]";
		boolean actQryRow3List=ListComparisionWOOrder(reportCrossRefAccQury3rdRowList,expQryRow3List);
		
		String expQryRow4List = "[QtytobeOrdered, 0.00]";
		boolean actQryRow4List=ListComparisionWOOrder(reportCrossRefAccQury4thRowList,expQryRow4List);
		
		String expQryRow5List = "[CurrentStock, 107.00]";
		boolean actQryRow5List=ListComparisionWOOrder(reportCrossRefAccQury5thRowList,expQryRow5List);
		
		String expQryRow6List = "[Avg.StockRate, 8.75]";
		boolean actQryRow6List=ListComparisionWOOrder(reportCrossRefAccQury6thRowList,expQryRow6List);
		
		
		String expQryRow7List = "[Value, 936.05]";
		boolean actQryRow7List=ListComparisionWOOrder(reportCrossRefAccQury7thRowList,expQryRow7List);
		
		String expQryRow8List = "[AlternateQuantity, 0.00]";
		boolean actQryRow8List=ListComparisionWOOrder(reportCrossRefAccQury8thRowList,expQryRow8List);
		
		click(reportCrossRefAccInfoCloseBtn);
	
		Thread.sleep(4000);
		click(reportCrossRef);
		Thread.sleep(2000);
		
		click(reportCrossRefItemReport);
		
		Thread.sleep(5000);
		
		System.out.println("**************************             Item Report  Details                  **************************");
		
		Select s=new Select(reportCrossItemTxt);
		String actSelectedAccTxt=s.getFirstSelectedOption().getText();
		String expSelectedAccTxt="Item";
		
		System.out.println("Actual Account Txt		"		+	actSelectedAccTxt		+	"Expected	"		+	expSelectedAccTxt);
		
		click(reportCrossReportTxt);
		reportCrossReportTxt.sendKeys("Multi-level stock movement");
		Thread.sleep(2000);
		reportCrossReportTxt.sendKeys(Keys.TAB);
		
		click(reportCrossReportOkBtn);
		Thread.sleep(2500);
		
		ArrayList<String> openTabs = new ArrayList<String>(getDriver().getWindowHandles());
				
		getDriver().switchTo().window(openTabs.get(1));
		Thread.sleep(3000);
		
		String expReportRow1List = "[1, item2, 331.50, 1808.00, 189.50, 909.03, 142.00, 741.18, 5.22]";
		boolean actReportRow1List=ListComparisionWOOrder(report1stRowList,expReportRow1List);
		
		String expReportRow2List = "[2, GrandTotal, 331.50, 1808.00, 189.50, 909.03, 142.00, 741.18, 5.22]";
		boolean actReportRow2List=ListComparisionWOOrder(report2ndRowList,expReportRow2List);
		
				
		
		getDriver().switchTo().window(openTabs.get(1)).close();
		
		Thread.sleep(1000);
		
		getDriver().switchTo().window(openTabs.get(0));
		Thread.sleep(1000);
		
		
		if(actRow1List && actRow2List && actQryRow1List &&actQryRow2List && actQryRow3List && actQryRow4List
				&& actQryRow5List && actQryRow6List && actQryRow7List && actQryRow8List && actReportRow1List&& actReportRow2List)
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
	 
	@FindBy(xpath="//*[@id='dvReportDetails']//tbody//tr//td[3]")
	public static List<WebElement> report_CreditPColList;
	
	@FindBy(xpath="//*[@id='dvReportDetails']//tbody//tr//td[4]")
	public static List<WebElement> report_CreditPLinkColList;

	public boolean checkRDCreditColumnFilterReport() throws InterruptedException
	{
		
		focusMainSearch("RD Credit column filter");
		Thread.sleep(4500);
		
		click(sl_OkBtn);
		Thread.sleep(4000);
		
		String expReportRow1List = "[100.00, 100.00, 200.00]";
		boolean actReportRow1List=ListComparisionWOOrder(report_CreditPColList,expReportRow1List);
		
		String expReportRow2List = "[20.00, 18.00, 38.00]";
		boolean actReportRow2List=ListComparisionWOOrder(report_CreditPLinkColList,expReportRow2List);
		
			if(actReportRow1List && actReportRow2List)	
			{
				return true;
			}
			else
			{
				return false;
			}
		
	}
	
	public ReportDesignerNew1Page(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
}
