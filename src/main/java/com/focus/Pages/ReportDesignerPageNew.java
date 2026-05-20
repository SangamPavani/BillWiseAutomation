package com.focus.Pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
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
import com.focus.utilities.DriverUtility;
import com.focus.utilities.POJOUtility;
import com.testautomationguru.utility.PDFUtil;

public class ReportDesignerPageNew extends BaseEngine {
	
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

		checkRestoreOptionsCompanyAndLogin("Testing 22New", "Testing 22");

		

	}

	
	
	@FindBy(xpath="(//i[@class='icon-filter2 hiconright2'])[1]")
	public static WebElement homeCustomizationBtn;
	
	@FindBy(xpath="//*[@id='id_rd_header_button_group_3']")
	private static WebElement customizationTab;
	
	@FindBy(xpath="//*[@id='rd_customization_table_column_7']/div[text()='Gross']")
	private static WebElement customizationTab_GrossCol;
	
	@FindBy(xpath="(//*[@id='id_rd_customization_report_column_property']//span[@class='icon-new hiconright2 icon-arrow btn-img'])[2]")
	private static WebElement customizationTab_FilterExpBtn;
	
	
	@FindBy(xpath="(//*[@id='idFilterCustomizeIcon'])[2]")
	private static WebElement customizationTab_DefaultFilterBtn;
	
	
	@FindBy(xpath="(//*[@id='txtfiltersearch'])[1]")
	private static WebElement customizationTab_DefaultFilterSearchBtn;
	
	@FindBy(xpath="//label[text()='Voucher class']/input")
	private static WebElement customizationTab_filterVoucherClassChkBox;
	
	@FindBy(xpath="//label[text()='Voucher class']/span")
	private static WebElement customizationTab_filterVoucherClassChkBoxSelected;
	
	@FindBy(xpath="(//div[@class='modal-footer']//input[@class='FButton-Primary' and @value='Ok'])[1]")
	private static WebElement customizationTab_filterSearchOkBtn;
	
	@FindBy(xpath="//*[@id='FOption_0_1000_DefaultFilter_1']")
	private static WebElement customizationTab_VoucherClassDefaultfilterTxt;
	
	@FindBy(xpath="//*[@id='id_rd_customization_report_column_button_container']/input[2]")
	private static WebElement customizationTab_OkBtn;
	
	@FindBy(xpath="//*[@id='RDFinishbtn']")
	private static WebElement finishBtn;
	
	@FindBy(xpath="(//a[@title='Cancel']//i[@class='icon-close hiconright2'])[1]")
	private static WebElement RD_CloseBtn;
	
	@FindBy(xpath="//*[@id='dvReportDetails']//tbody//td[8]")
	private static List<WebElement> RDMRNDetailReportGrossColumn;
	
	public boolean checkColumnFilteronVoucherclassinMRNDetailReport() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		focusMainSearch("RD MRN Detail");
		Thread.sleep(4000);
		
		
		click(homeCustomizationBtn);
		Thread.sleep(2500);
		
		
		click(customizationTab);
		Thread.sleep(2500);
		
		click(customizationTab_GrossCol);
		Thread.sleep(1000);
		
		click(customizationTab_FilterExpBtn);
		Thread.sleep(2000);
		getAction().moveToElement(customizationTab_DefaultFilterBtn).build().perform();
		click(customizationTab_DefaultFilterBtn);
		Thread.sleep(2000);
		click(customizationTab_DefaultFilterSearchBtn);
		customizationTab_DefaultFilterSearchBtn.sendKeys("Voucher class");
		customizationTab_DefaultFilterSearchBtn.sendKeys(Keys.TAB);
		
		if(customizationTab_filterVoucherClassChkBox.isSelected()==false)
		{
			click(customizationTab_filterVoucherClassChkBoxSelected);
		}
		
		click(customizationTab_filterSearchOkBtn);
		Thread.sleep(4000);
		
		click(customizationTab_VoucherClassDefaultfilterTxt);
		customizationTab_VoucherClassDefaultfilterTxt.sendKeys("Sales Invoices");
		Thread.sleep(1500);
		customizationTab_VoucherClassDefaultfilterTxt.sendKeys(Keys.TAB);
		
		click(customizationTab_OkBtn);
		
		getAction().moveToElement(finishBtn).build().perform();
		Thread.sleep(1200);
		finishBtn.click();
		Thread.sleep(2000);
		
		String expMsg="Data saved successfully.";
		String actMsg=checkValidationMessage(expMsg);
		
		click(RD_CloseBtn);
		Thread.sleep(3000);
		
		click(sl_OkBtn);
		Thread.sleep(8000);
		
	getFluentWebDriverWait().until(ExpectedConditions.visibilityOf(sl_1stRow1stCol));
		
		ArrayList<String>MRNGross=new ArrayList<>();
		for(WebElement e:RDMRNDetailReportGrossColumn)
		{
			MRNGross.add(e.getText());
		}
		
		String actRow1List=MRNGross.toString();
		String expRow1List="[, , , , , , , , , , , , , , , , , , , ]";
		
		System.out.println(actRow1List);
		System.out.println(expRow1List);
		
		ArrayList<String>MRNGross1=new ArrayList<>();
		
			report_NextBtn.click();
			Thread.sleep(4000);
			for(WebElement e:RDMRNDetailReportGrossColumn)
			{
				MRNGross1.add(e.getText());
			}
		
		String actRow11List=MRNGross1.toString();
		String expRow11List="[, , , , , , , , , , , , , , , , , , , ]";
		
		System.out.println(actRow11List);
		System.out.println(expRow11List);
		
		
		ArrayList<String>MRNGross2=new ArrayList<>();
		
			report_NextBtn.click();
			Thread.sleep(4000);
			for(WebElement e:RDMRNDetailReportGrossColumn)
			{
				MRNGross2.add(e.getText());
			}
		
		String actRow12List=MRNGross2.toString();
		String expRow12List="[, , , , , , ]";
		
		
		System.out.println(actRow12List);
		System.out.println(expRow12List);
		
		if(actRow12List.equalsIgnoreCase(expRow12List) && actRow11List.equalsIgnoreCase(expRow11List)
				&& actRow11List.equalsIgnoreCase(expRow1List))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	@FindBy(xpath="//*[@id='cmbRowsperPage']")
	public static WebElement rowsPerPageSelect;
	
	@FindBy(xpath="//*[@id='dvReportDetails']//tbody//tr")
	public static List<WebElement> rowsPerPage;
	
	@FindBy(xpath="//a[@title='Refresh']")
	public static WebElement reportRefreshBtn;
	
	@FindBy(xpath="(//input[@value='Ok'])[1]")
	public static WebElement reportRefreshOkBtn;
	
	public boolean checkSelectRowsinRDMRNDetailReport() throws InterruptedException
	{
		
		Select s=new Select(rowsPerPageSelect);
		s.selectByValue("3");
		
		Thread.sleep(3000);
		int Page1=rowsPerPage.size();
		System.out.println(Page1);
		
		int Page2=0;
		int Page3=0;
		int Page4=0;
		if(report_NextBtn.isEnabled())
		{
			report_NextBtn.click();
			Thread.sleep(1500);
			 Page2=rowsPerPage.size();
		}
			
		Thread.sleep(1000);
		if(report_NextBtn.isEnabled())
		{
			report_NextBtn.click();
			Thread.sleep(1500);
			 Page3=rowsPerPage.size();
		}
		Thread.sleep(1000);
		if(report_NextBtn.isEnabled())
		{
			report_NextBtn.click();
			 Page4=rowsPerPage.size();
		}
		Thread.sleep(1000);
		System.out.println("Rows Count in each Page after selecting rows per page" + Page1 + Page2 + Page3 + Page4);
		
		Thread.sleep(4000);
		click(reportRefreshBtn);
		Thread.sleep(3000);
		
		click(reportRefreshOkBtn);
		Thread.sleep(4000);
		
		
		int Page1AfterRfsh=rowsPerPage.size();
		int Page2AfterRfsh=0;
		int Page3AfterRfsh=0;
		int Page4AfterRfsh=0;
	
		if(report_NextBtn.isEnabled())
		{
			report_NextBtn.click();
			Page2AfterRfsh=rowsPerPage.size();
		}
			
		
		if(report_NextBtn.isEnabled())
		{
			report_NextBtn.click();
			Page3AfterRfsh=rowsPerPage.size();
		}
		
		if(report_NextBtn.isEnabled())
		{
			report_NextBtn.click();
			Page4AfterRfsh=rowsPerPage.size();
		}
		
		System.out.println("Rows Count in each Page after selecting rows per page After refresh the report" + Page1AfterRfsh + Page2AfterRfsh + Page3AfterRfsh + Page4AfterRfsh);
		
		if(Page1==Page1AfterRfsh && Page2==Page2AfterRfsh && Page3==Page3AfterRfsh && Page4==Page4AfterRfsh)
		{
		return true;
		}
		else
		{
			return false;
		}
		
	}
	
	@FindBy(xpath="//*[@id='tblHeaderReportRender']/th[6]")
	public static WebElement RDMRNReport_QtyCol;
	
	@FindBy(xpath="//a[@title='Sorting']")
	public static WebElement reportSortingBtn;
	
	@FindBy(xpath="//a[@title='Sorting']/../ul/li[text()='Ascending']")
	public static WebElement reportSortingAscOrder;
	
	@FindBy(xpath="//a[@title='Sorting']/../ul/li[text()='Descending']")
	public static WebElement reportSortingDescOrder;
	
	
	@FindBy(xpath="//*[@id='dvReportDetails']//tbody//td[6]")
	public static List<WebElement> RDMRNReport_QtyColList;
	
	public boolean checkSortingOptioninRDMRNDetailReport() throws InterruptedException
	{
		//click(report_FirstBtn);
		Thread.sleep(1500);
		click(RDMRNReport_QtyCol);
		
		ArrayList<String>QtyCol=new ArrayList<String>();
		
		for(int i=1;i<RDMRNReport_QtyColList.size();i++)
		{
			QtyCol.add(RDMRNReport_QtyColList.get(i).getText());
		}
		
		String actQtyColBeforeSorting=QtyCol.toString();
		String expQtyColBeforeSorting="[5.00, 7.00, 10.00, 20.00, 12.00, 15.00, 13.00, 20.00, 10.00, 10.00, 5.00, 6.00, 10.00, 1.00, 1,000.00, 1,278.00]";
		
		System.out.println("Actual Qty Col Before Sort :"	+	actQtyColBeforeSorting);
		System.out.println("Expect Qty Col Before Sort :"	+	expQtyColBeforeSorting);
		
		click(RDMRNReport_QtyCol);
		
		click(reportSortingBtn);
		
		click(reportSortingAscOrder);
		
		Thread.sleep(4000);
		
		ArrayList<String>QtyCol1=new ArrayList<String>();
		
		for(int i=1;i<RDMRNReport_QtyColList.size();i++)
		{
			QtyCol1.add(RDMRNReport_QtyColList.get(i).getText());
		}
		
		String actQtyColAfterSortingAsc=QtyCol1.toString();
		String expQtyColAfterSortingAsc="[1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 2.50, 5.00, 5.00, 5.00, 5.00, 5.00, 5.00, 5.00, 5.00, 6.00]";
		
		System.out.println("Actual Qty Col After Sort Asc:"	+	actQtyColAfterSortingAsc);
		System.out.println("Expect Qty Col After Sort Asc:"	+	expQtyColAfterSortingAsc);
		
		click(RDMRNReport_QtyCol);
		
		click(reportSortingBtn);
		
		click(reportSortingDescOrder);
		
		Thread.sleep(4000);
		
		ArrayList<String>QtyCol2=new ArrayList<String>();
		
		for(int i=1;i<RDMRNReport_QtyColList.size();i++)
		{
			QtyCol2.add(RDMRNReport_QtyColList.get(i).getText());
		}
		
		String actQtyColAfterSortingDesc=QtyCol2.toString();
		String expQtyColAfterSortingDesc="[50.00, 20.00, 20.00, 15.00, 13.00, 12.00, 10.00, 10.00, 10.00, 10.00, 10.00, 9.00, 7.50, 7.00, 6.00, 6.00, 5.00, 5.00, 5.00, 5.00, 5.00, 5.00, 5.00, 5.00, 2.50, 1.00, 1.00, 1.00, 1.00]";
		
		System.out.println("Actual Qty Col After Sort Desc:"	+	actQtyColAfterSortingDesc);
		System.out.println("Expect Qty Col After Sort Desc:"	+	expQtyColAfterSortingDesc);
		
		
		if(actQtyColBeforeSorting.equalsIgnoreCase(expQtyColBeforeSorting) && actQtyColAfterSortingAsc.equalsIgnoreCase(expQtyColAfterSortingAsc)
				&& actQtyColAfterSortingDesc.equalsIgnoreCase(expQtyColAfterSortingDesc))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	@FindBy(xpath="//a[@title='Analyze']")
	public static WebElement reportAnalyzeBtn;
	
	
	@FindBy(xpath="//*[@id='tblAnalyze']/thead//th")
	public static List<WebElement> reportAnalyzeHeaderList;
	
	@FindBy(xpath="//*[@id='tblAnalyze']/tbody//td[2]")
	public static List<WebElement> reportAnalyzeParticularsColList;
	
	@FindBy(xpath="//*[@id='tblAnalyze']/tbody/tr[1]/td")
	public static List<WebElement> reportAnalyze1stRowList;
	
	@FindBy(xpath="//*[@id='tblAnalyze']/tbody/tr[2]/td")
	public static List<WebElement> reportAnalyze2ndRowList;
	
	@FindBy(xpath="//*[@id='tblAnalyze']/tbody/tr[3]/td")
	public static List<WebElement> reportAnalyze3rdRowList;
	
	@FindBy(xpath="//*[@id='tblAnalyze']/tbody/tr[4]/td")
	public static List<WebElement> reportAnalyze4thRowList;
	
	@FindBy(xpath="//*[@id='tblAnalyze']/tbody/tr[5]/td")
	public static List<WebElement> reportAnalyze5thRowList;
	
	
	public boolean checkAnalyzeinRDMRNDetailReport() throws InterruptedException, IOException
	{
		
		//click(reportRefreshBtn);
		Thread.sleep(3000);
		
		//click(reportRefreshOkBtn);
		//Thread.sleep(3000);
		
		click(reportAnalyzeBtn);
		Thread.sleep(12000);
		
		Runtime.getRuntime().exec(getBaseDir() + "\\autoIt\\scripts\\RDMRNAnalyze.exe");
		
		Thread.sleep(40000);
		
		String expAnalyzeHeaderList = "[Particulars, item1, item2, item3, item4, RMA, RMA2, RMA3, TotalQuantity, TotalGross, Quantity, Gross, Quantity, Gross, Quantity, Gross, Quantity, Gross, Quantity, Gross, Quantity, Gross, Quantity, Gross]";
		boolean actAnalyzeHeaderList=ListComparisionWOOrder(reportAnalyzeHeaderList,expAnalyzeHeaderList);
		
		String expAnalyzeParticularsList = "[CustomerB, VendorA, VendorB, VendorC, GrandTotal]";
		boolean actAnalyzeParticularsList=ListComparisionWOOrder(reportAnalyzeParticularsColList,expAnalyzeParticularsList);

		String expRow1List = "[1, CustomerB, 5.00, 5.00, 10.00]";
		boolean actRow1List=ListComparisionWOOrder(reportAnalyze1stRowList,expRow1List);

		String expRow2List = "[2, VendorA, 62.00, 11.50, 19.00, 92.50]";
		boolean actRow2List=ListComparisionWOOrder(reportAnalyze2ndRowList,expRow2List);
		
		String expRow3List = "[3, VendorB, 15.00, 2.50, 11.00, 26.00, 7.00, 1, 000.00, 10.00, 1, 071.50]";
		boolean actRow3List=ListComparisionWOOrder(reportAnalyze3rdRowList,expRow3List);
		
		String expRow4List = "[4, VendorC, 28.00, 34.00, 14.00, 27.00, 1.00, 104.00]";
		boolean actRow4List=ListComparisionWOOrder(reportAnalyze4thRowList,expRow4List);
		
		String expRow5List = "[5, GrandTotal, 110.00, 53.00, 44.00, 53.00, 7.00, 1, 001.00, 10.00, 1, 278.00]";
		boolean actRow5List=ListComparisionWOOrder(reportAnalyze5thRowList,expRow5List);
		
		if(actAnalyzeHeaderList && actAnalyzeParticularsList && actRow1List && actRow2List && actRow3List && actRow4List && actRow5List)
		{
			return true;
		}
		else
		{
			return false;
		}
		
		
	}
	
	@FindBy(xpath="(//a[@title='Export'])[3]")
	public static WebElement reportAnalyzeExportBtn;
	
	
	@FindBy(xpath="(//a[@title='Export'])[3]/../ul//a[@id='Excel']")
	public static WebElement reportAnalyzeExportExcelBtn;
	
	
	@FindBy(xpath="(//a[@title='Export'])[3]/../ul//a[@id='PDF']")
	public static WebElement reportAnalyzeExportPDFBtn;
	
	@FindBy(xpath="(//input[@value='Yes'])[1]")
	private static WebElement sl_ExportPDFYesBtn;
	
	public boolean checkExporttoExcelBeforeSavingAnalyzeReportofRDMRNDetailReport() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException
	{
		
		click(reportAnalyzeExportBtn);
		Thread.sleep(1500);
		click(reportAnalyzeExportExcelBtn);
		Thread.sleep(5000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_ExportPDFYesBtn));
		sl_ExportPDFYesBtn.click();
		Thread.sleep(8000);
		
		File Efile=new File(getBaseDir()+"\\autoIt\\ExportFiles\\RDMRNDetailAnalyzeExcel.xlsx");
			
			if(Efile.exists())
			{
				Efile.delete();
			}
			
			
			Robot robot = new Robot();
		
			
				
			Runtime.getRuntime().exec(getBaseDir() + "\\autoIt\\scripts\\RDMRNDetailAnalyzeExcel.exe");
			
			Thread.sleep(8000);
			
			
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
		     

		 	String actExcelfile = getBaseDir()+"\\autoIt\\ExportFiles\\RDMRNDetailAnalyzeExcel.xlsx";
		 	String expExcelfile = getBaseDir()+"\\autoIt\\ImportFiles\\RDMRNDetailAnalyzeExcel.xlsx";
		 	String sheet = "Sheet1";
		 	   
		 	
		 	
		 	FileInputStream fip1 = new FileInputStream(actExcelfile);
		 	Workbook workbook1  = WorkbookFactory.create(fip1);
		 	
		 	FileInputStream fip2 = new FileInputStream(expExcelfile);
		 	Workbook workbook2  = WorkbookFactory.create(fip2);
		 	
		 	boolean result = excelReader.checkExcelSheetsComparison(workbook1, workbook2,"10/11/2025");
		 	
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
	
	
	public boolean checkExporttoPDFBeforeSavingAnalyzeReportofRDMRNDetailReport() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException
	{
		
		click(reportAnalyzeExportBtn);
		Thread.sleep(1500);
		click(reportAnalyzeExportPDFBtn);
		Thread.sleep(5000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_ExportPDFYesBtn));
		sl_ExportPDFYesBtn.click();
		Thread.sleep(8000);
		
		File Efile1 = new File(getBaseDir() + "\\autoIt\\ExportFiles\\RDMRNDetailAnalyzePDF.pdf");

		if (Efile1.exists()) {
			Efile1.delete();
		}

		Thread.sleep(4000);
			
		
		
		Robot robot = new Robot();
	
		Runtime.getRuntime().exec(getBaseDir() + "\\autoIt\\scripts\\RDMRNDetailAnalyzePDF.exe");

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
		
		
	
		
		
		String actPDF = getBaseDir()+"\\autoIt\\ExportFiles\\RDMRNDetailAnalyzePDF.pdf";
		String expPDF = getBaseDir()+"\\autoIt\\ImportFiles\\RDMRNDetailAnalyzePDF.pdf";
		
		PDFUtil pdfutil = new PDFUtil();
		
		boolean result = pdfutil.compare(actPDF, expPDF);
		
		String data = pdfutil.getText(expPDF);
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		String date = df.format(cal.getTime());
		System.err.println(date);
		
		String oldDate = "10/11/2025";
		

		
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
	
	@FindBy(xpath="//*[@id='idsaveAnlyze']")
	public static WebElement reportAnalyzeSaveBtn;
	
	@FindBy(xpath="//*[@id='analyzeReportName']")
	public static WebElement reportAnalyzeRerportNameTxt;
	
	
	@FindBy(xpath="(//*[@id='id_menu_tree_135']//i[@class='icon-new'])[1]")
	public static WebElement reportAnalyzeInventoryExpBtn;
	
	@FindBy(xpath="//*[@id='id_menu_tree_200']/a/span")
	public static WebElement reportAnalyzeInventoryReportsBtn;
	
	
	@FindBy(xpath="//button[text()='Save']")
	public static WebElement reportAnalyze_SaveBtn;
	
	public boolean checkSavingAnalyzeofRDMRNDetailReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		click(reportAnalyzeSaveBtn);
		Thread.sleep(3000);
		
		reportAnalyzeRerportNameTxt.sendKeys("RD MRN Detail Analyze Report");
		
		
		click(reportAnalyzeInventoryExpBtn);
		click(reportAnalyzeInventoryReportsBtn);
		click(reportAnalyze_SaveBtn);
		
		String expMsg="Analyze Report Saved Successfully";
		String actMsg=checkValidationMessage(expMsg);
		
		
		focusMainSearch("RD MRN Detail Analyze Report");
		Thread.sleep(4000);
		
		click(sl_OkBtn);
		Thread.sleep(8000);
		
		String expReportHeaderList="[#, Particulars, item1, item2, item3, item4, RMA, RMA2, RMA3, TotalQuantity, TotalGross, Quantity, Gross, Quantity, Gross, Quantity, Gross, Quantity, Gross, Quantity, Gross, Quantity, Gross, Quantity, Gross]";
		boolean actRowHeaderList=ListComparisionWOOrder(reportHeaderList, expReportHeaderList);
		
		
		String expRow1List="[1, VendorA, 62.00, 11.50, 19.00, 92.50]";
		boolean actRow1List=ListComparisionWOOrder(reportRow1List, expRow1List);
		
		String expRow2List="[2, VendorC, 28.00, 34.00, 14.00, 27.00, 1.00, 104.00]";
		boolean actRow2List=ListComparisionWOOrder(reportRow2List, expRow2List);
		
		String expRow3List="[3, CustomerB, 5.00, 5.00, 10.00]";
		boolean actRow3List=ListComparisionWOOrder(reportRow3List, expRow3List);
		
		String expRow4List="[4, VendorB, 15.00, 2.50, 11.00, 26.00, 7.00, 1, 000.00, 10.00, 1, 071.50]";
		boolean actRow4List=ListComparisionWOOrder(reportRow4List, expRow4List);
		
		String expRow5List="[5, GrandTotal, 110.00, 53.00, 44.00, 53.00, 7.00, 1, 001.00, 10.00, 1, 278.00]";
		boolean actRow5List=ListComparisionWOOrder(reportRow5List, expRow5List);
		
		
		if(actMsg.equalsIgnoreCase(expMsg) && actRowHeaderList && actRow1List && actRow2List && actRow3List && actRow4List && actRow5List)
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	@FindBy(xpath="//*[@id='dvReportDetails']//thead//th")
	public static List<WebElement>reportHeaderList;
	
	public boolean checkRDMRNCubeReport() throws InterruptedException
	{
		focusMainSearch("RD MRN Cube");
		Thread.sleep(4000);
		
		click(sl_OkBtn);
		Thread.sleep(8000);
		
		String expReportHeaderList="[#, Particulars, November, December, April, May, July, August, September, TotalQuantity, AverageRate, TotalGross, Quantity, Rate, Gross, Quantity, Rate, Gross, Quantity, Rate, Gross, Quantity, Rate, Gross, Quantity, Rate, Gross, Quantity, Rate, Gross, Quantity, Rate, Gross]";
		boolean actRowHeaderList=ListComparisionWOOrder(reportHeaderList, expReportHeaderList);
		
		String expRow1List="[1, item1, 47, 10, 470, 2, 18, 35, 6, 10, 60, 5, 4, 20, 50, 10, 500, 110, 10, 1, 085]";
		boolean actRow1List=ListComparisionWOOrder(reportRow1List, expRow1List);
		
		String expRow2List="[2, item2, 40, 5, 157, 5, 5, 26, 1, 6, 6, 8, 15, 113, 53, 8, 302]";
		boolean actRow2List=ListComparisionWOOrder(reportRow2List, expRow2List);
		
		String expRow3List="[3, item3, 33, 4, 145, 2, 5, 10, 9, 15, 135, 44, 8, 290]";
		boolean actRow3List=ListComparisionWOOrder(reportRow3List, expRow3List);
		
		String expRow4List="[4, item4, 46, 5, 258, 7, 3, 19, 53, 4, 277]";
		boolean actRow4List=ListComparisionWOOrder(reportRow4List, expRow4List);
		
		String expRow5List="[5, RMA1, 7, 12, 98, 7, 12, 98]";
		boolean actRow5List=ListComparisionWOOrder(reportRow5List, expRow5List);
		
		String expRow6List="[6, RMA2, 1, 001, 8, 6, 009, 1, 001, 8, 6, 009]";
		boolean actRow6List=ListComparisionWOOrder(reportRow6List, expRow6List);
		
		String expRow7List="[7, RMA3, 5, 8, 40, 5, 9, 45, 10, 9, 85]";
		boolean actRow7List=ListComparisionWOOrder(reportRow7List, expRow7List);
		
		String expRow8List="[8, GrandTotal, 5, 8, 40, 1, 179, 52, 7, 182, 16, 30, 90, 7, 16, 66, 5, 4, 20, 58, 25, 613, 9, 15, 135, 1, 278, 57, 8, 146]";
		boolean actRow8List=ListComparisionWOOrder(reportRow8List, expRow8List);
		
		if(actRow1List && actRow2List && actRow3List && actRow4List && actRow5List && actRow6List && actRow7List && actRow8List)
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	@FindBy(xpath="(//a[@title='Export'])[2]")
	public static WebElement reportEntryExportBtn;
	
	@FindBy(xpath="(//a[@title='Export'])[2]/../ul/li/a[text()='PDF']")
	public static WebElement reportEntryExportPDFBtn;
	
	@FindBy(xpath="(//a[@title='Excel'])[2]")
	public static WebElement reportEntryExportExcelBtn;
	
	@FindBy(xpath="(//a[@title='Export'])[1]")
	public static WebElement reportHomeExportBtn;
	
	@FindBy(xpath="(//a[@title='Export'])[1]/../ul/li/a[text()='PDF']")
	public static WebElement reportHomeExportPDFBtn;
	
	@FindBy(xpath="(//a[@title='Excel'])[1]")
	public static WebElement reportHomeExportExcelBtn;
	
	public boolean checkExporttoPDFinFromEntryRDMRNCubeReport() throws InterruptedException, IOException, AWTException
	{
		click(reportEntryExportBtn);
		click(reportEntryExportPDFBtn);
		Thread.sleep(2000);
		click(sl_ExportPDFYesBtn);
		Thread.sleep(6000);
		
		File Efile1 = new File(getBaseDir() + "\\autoIt\\ExportFiles\\RDMRNCubeEntryPDF.pdf");

		if (Efile1.exists()) {
			Efile1.delete();
		}

		Thread.sleep(4000);
			
		
		
		Robot robot = new Robot();
	
		Runtime.getRuntime().exec(getBaseDir() + "\\autoIt\\scripts\\RDMRNCubeEntryPDF.exe");

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
		
		
	
		
		
		String actPDF = getBaseDir()+"\\autoIt\\ExportFiles\\RDMRNCubeEntryPDF.pdf";
		String expPDF = getBaseDir()+"\\autoIt\\ImportFiles\\RDMRNCubeEntryPDF.pdf";
		
		PDFUtil pdfutil = new PDFUtil();
		
		boolean result = pdfutil.compare(actPDF, expPDF);
		
		String data = pdfutil.getText(expPDF);
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		String date = df.format(cal.getTime());
		System.err.println(date);
		
		String oldDate = "11/11/2025";
		

		
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
	
	
	public boolean checkExporttoExcelFromEntryinRDMRNCubeReport() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		
		click(reportEntryExportExcelBtn);
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_ExportPDFYesBtn));
		sl_ExportPDFYesBtn.click();
		Thread.sleep(8000);
		
		File Efile=new File(getBaseDir()+"\\autoIt\\ExportFiles\\RDMRNCubeEntryExcel.xlsx");
			
			if(Efile.exists())
			{
				Efile.delete();
			}
			
			
			Robot robot = new Robot();
		
			
				
			Runtime.getRuntime().exec(getBaseDir() + "\\autoIt\\scripts\\RDMRNCubeEntryExcel.exe");
			
			Thread.sleep(8000);
			
			
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
		     

		 	String actExcelfile = getBaseDir()+"\\autoIt\\ExportFiles\\RDMRNCubeEntryExcel.xlsx";
		 	String expExcelfile = getBaseDir()+"\\autoIt\\ImportFiles\\RDMRNCubeEntryExcel.xlsx";
		 	String sheet = "Sheet1";
		 	   
		 	
		 	
		 	FileInputStream fip1 = new FileInputStream(actExcelfile);
		 	Workbook workbook1  = WorkbookFactory.create(fip1);
		 	
		 	FileInputStream fip2 = new FileInputStream(expExcelfile);
		 	Workbook workbook2  = WorkbookFactory.create(fip2);
		 	
		 	boolean result = excelReader.checkExcelSheetsComparison(workbook1, workbook2,"11/11/2025");
		 	
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
	
	
	
	
	public boolean checkExporttoPDFFromHomeinRDMRNCubeReport() throws InterruptedException, IOException, AWTException
	{
		
		click(report_CloseBtn);
		Thread.sleep(5000);
		
		click(reportHomeExportBtn);
		click(reportHomeExportPDFBtn);
		Thread.sleep(2000);
		click(sl_ExportPDFYesBtn);
		Thread.sleep(6000);
		
		File Efile1 = new File(getBaseDir() + "\\autoIt\\ExportFiles\\RDMRNCubeHomePDF.pdf");

		if (Efile1.exists()) {
			Efile1.delete();
		}

		Thread.sleep(4000);
			
		
		
		Robot robot = new Robot();
	
		Runtime.getRuntime().exec(getBaseDir() + "\\autoIt\\scripts\\RDMRNCubeHomePDF.exe");

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
		
		
	
		
		
		String actPDF = getBaseDir()+"\\autoIt\\ExportFiles\\RDMRNCubeHomePDF.pdf";
		String expPDF = getBaseDir()+"\\autoIt\\ImportFiles\\RDMRNCubeHomePDF.pdf";
		
		PDFUtil pdfutil = new PDFUtil();
		
		boolean result = pdfutil.compare(actPDF, expPDF);
		
		String data = pdfutil.getText(expPDF);
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		String date = df.format(cal.getTime());
		System.err.println(date);
		
		String oldDate = "11/11/2025";
		

		
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
	
	
	public boolean checkExporttoExcelFromHomeinRDMRNCubeReport() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		
		click(reportHomeExportExcelBtn);
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_ExportPDFYesBtn));
		sl_ExportPDFYesBtn.click();
		Thread.sleep(8000);
		
		File Efile=new File(getBaseDir()+"\\autoIt\\ExportFiles\\RDMRNCubeHomeExcel.xlsx");
			
			if(Efile.exists())
			{
				Efile.delete();
			}
			
			
			Robot robot = new Robot();
		
			
				
			Runtime.getRuntime().exec(getBaseDir() + "\\autoIt\\scripts\\RDMRNCubeHomeExcel.exe");
			
			Thread.sleep(8000);
			
			
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
		     

		 	String actExcelfile = getBaseDir()+"\\autoIt\\ExportFiles\\RDMRNCubeHomeExcel.xlsx";
		 	String expExcelfile = getBaseDir()+"\\autoIt\\ImportFiles\\RDMRNCubeHomeExcel.xlsx";
		 	String sheet = "Sheet1";
		 	   
		 	
		 	
		 	FileInputStream fip1 = new FileInputStream(actExcelfile);
		 	Workbook workbook1  = WorkbookFactory.create(fip1);
		 	
		 	FileInputStream fip2 = new FileInputStream(expExcelfile);
		 	Workbook workbook2  = WorkbookFactory.create(fip2);
		 	
		 	boolean result = excelReader.checkExcelSheetsComparison(workbook1, workbook2,"11/11/2025");
		 	
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
	
	public boolean checkWeekWiseReportforChangeLevel() throws InterruptedException
	{
		
		focusMainSearch("WEEK WISE REPORT");
		Thread.sleep(4000);
		
		click(sl_OkBtn);
		Thread.sleep(8000);
		
		String expRow1List="[1, 2020, 2, 257.00, 339.00, 48, 768.00]";
		boolean actRow1List=ListComparisionWOOrder(reportRow1List, expRow1List);
		
		String expRow2List="[2, November, 2, 257.00, 339.00, 48, 768.00]";
		boolean actRow2List=ListComparisionWOOrder(reportRow2List, expRow2List);
		
		String expRow3List="[3, 47, 2, 257.00, 339.00, 48, 768.00]";
		boolean actRow3List=ListComparisionWOOrder(reportRow3List, expRow3List);
		
		String expRow4List="[4, 20/11/2020, 2, 262.00, 325.00, 48, 791.00]";
		boolean actRow4List=ListComparisionWOOrder(reportRow4List, expRow4List);
		
		String expRow5List="[5, OpeStk:Focus_1, RMA1, 2.00, 3.00, 6.00]";
		boolean actRow5List=ListComparisionWOOrder(reportRow5List, expRow5List);
		
		click(reportChangeLevelBtn);
		click(reportChangeLevel1Btn);
		Thread.sleep(2500);
		
		String expRow1ListAfterL1="[1, 2020, 2, 257.00, 339.00, 48, 768.00]";
		boolean actRow1ListAfterL1=ListComparisionWOOrder(reportRow1List, expRow1ListAfterL1);
		
		String expRow2ListAfterL1="[2, 2022, 35.00, 9.00, 150.00]";
		boolean actRow2ListAfterL1=ListComparisionWOOrder(reportRow2List, expRow2ListAfterL1);
		
		String expRow3ListAfterL1="[3, 2023, 1, 224.73, 1, 995.38, 7, 118.91]";
		boolean actRow3ListAfterL1=ListComparisionWOOrder(reportRow3List, expRow3ListAfterL1);
		
		String expRow4ListAfterL1="[4, 2024, 40.00, 113.00, 46.00]";
		boolean actRow4ListAfterL1=ListComparisionWOOrder(reportRow4List, expRow4ListAfterL1);
		
		String expRow5ListAfterL1="[5, GrandTotal, 3, 476.73, 2, 456.38, 55, 990.91]";
		boolean actRow5ListAfterL1=ListComparisionWOOrder(reportRow5List, expRow5ListAfterL1);
		
		if(actRow1List && actRow2List && actRow3List && actRow4List && actRow5List &&
			actRow1ListAfterL1 && actRow2ListAfterL1 && actRow3ListAfterL1 && actRow4ListAfterL1 && actRow5ListAfterL1)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	@FindBy(xpath="//*[@id='reportChangeLevel']")
	public static WebElement reportChangeLevelBtn;
	
	@FindBy(xpath="//*[@id='reportChangeLevel']//a[@id='1']")
	public static WebElement reportChangeLevel1Btn;
	
	@FindBy(xpath="//*[@id='reportChangeLevel']//a[@id='2']")
	public static WebElement reportChangeLevel2Btn;
	
	@FindBy(xpath="//*[@id='reportChangeLevel']//a[@id='3']")
	public static WebElement reportChangeLevel3Btn;
	
	@FindBy(xpath="//*[@id='reportChangeLevel']//a[@id='4']")
	public static WebElement reportChangeLevel4Btn;
	
	@FindBy(xpath="//*[@id='reportChangeLevel']//a[@id='5']")
	public static WebElement reportChangeLevel5Btn;
	
	
	@FindBy(xpath="//*[@id='dvReportDetails']//tbody//td[2]")
	public static List<WebElement> report2ndColList;
	
	
	public boolean checkExporttoExcelinWeekWiseReportforLevel3() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException
	{
		click(reportChangeLevelBtn);
		click(reportChangeLevel3Btn);
		Thread.sleep(3000);
		
		String exp2ndColList="[2020, November, 47, 2022, October, 43, 2023, January, 1, February, 6, April, 15, 16, 17, May, 18, 21, 22, June, 22, 24, 25, July, 27, 29, 30, August, 32, 33]";
		boolean act2ndColList=ListComparisionWOOrder(report2ndColList, exp2ndColList);
		
		click(report_NextBtn);
		Thread.sleep(2000);
		
		String exp2ndColList1="[34, 35, September, 36, 37, 38, 39, October, 43, 44, November, 45, 46, 47, 48, December, 48, 49, 50, 51, 52, 2024, January, 1, 2, 3, 4, February, 6, GrandTotal]";
		boolean act2ndColList1=ListComparisionWOOrder(report2ndColList, exp2ndColList1);
		
		click(reportEntryExportExcelBtn);
		Thread.sleep(2000);
		
		click(sl_ExportPDFYesBtn);
		Thread.sleep(5000);
		
		File Efile=new File(getBaseDir()+"\\autoIt\\ExportFiles\\WeekWiseReportExcel.xlsx");
		
		if(Efile.exists())
		{
			Efile.delete();
		}
		
		
		Robot robot = new Robot();
	
		
			
		Runtime.getRuntime().exec(getBaseDir() + "\\autoIt\\scripts\\WeekWiseReportExcel.exe");
		
		Thread.sleep(8000);
		
		
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
	     

	 	String actExcelfile = getBaseDir()+"\\autoIt\\ExportFiles\\WeekWiseReportExcel.xlsx";
	 	String expExcelfile = getBaseDir()+"\\autoIt\\ImportFiles\\WeekWiseReportExcel.xlsx";
	 	String sheet = "Sheet1";
	 	   
	 	
	 	
	 	FileInputStream fip1 = new FileInputStream(actExcelfile);
	 	Workbook workbook1  = WorkbookFactory.create(fip1);
	 	
	 	FileInputStream fip2 = new FileInputStream(expExcelfile);
	 	Workbook workbook2  = WorkbookFactory.create(fip2);
	 	
	 	boolean result = excelReader.checkExcelSheetsComparison(workbook1, workbook2,"11/11/2025");
	 	
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
	
	
	@FindBy(xpath="(//a[@title='Print'])[2]")
	public static WebElement reportEntryPrintBtn;
	
	public boolean checkPrintinWeekWiseReportforLevel2() throws InterruptedException, IOException, AWTException
	{
		
		click(reportChangeLevelBtn);
		click(reportChangeLevel2Btn);
		Thread.sleep(4000);
		
		String exp2ndColList="[2020, November, 2022, October, 2023, January, February, April, May, June, July, August, September, October, November, December, 2024, January, February, GrandTotal]";
		boolean act2ndColList=ListComparisionWOOrder(report2ndColList, exp2ndColList);
		
		click(reportEntryPrintBtn);
		click(sl_ExportPDFYesBtn);
		Thread.sleep(5000);
	
		
		File Efile=new File(getBaseDir()+"\\autoIt\\ExportFiles\\WeekWiseReportLevel2Print.pdf");
		
		
		if(Efile.exists())
		{
			Efile.delete();
		}
		
		Thread.sleep(2000);
		
		
		Thread.sleep(4000);
		
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_S);
		robot.keyRelease(KeyEvent.VK_S);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		
		Thread.sleep(2000);
			
		Runtime.getRuntime().exec(getBaseDir() + "\\autoIt\\scripts\\WeekWiseReportLevel2Print.exe");
		
		Thread.sleep(15000);
		
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
	 	
	 	String actPDF = getBaseDir()+"\\autoIt\\ExportFiles\\WeekWiseReportLevel2Print.pdf";
		String expPDF = getBaseDir()+"\\autoIt\\ImportFiles\\WeekWiseReportLevel2Print.pdf";
		System.out.println(actPDF);
		System.out.println(expPDF);
		
		
		PDFUtil pdfutil = new PDFUtil();
		
		boolean result = pdfutil.compare(actPDF, expPDF);
		Calendar cal=Calendar.getInstance();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String currentDate = df.format(cal.getTime());
		
		String actData = pdfutil.getText(actPDF);
		
		
		String expData = pdfutil.getText(expPDF).replaceAll("11/11/2025", getCurrentDate());
		System.out.println(actData);
		System.out.println(expData);
		
		System.out.println("Compared Result  : "+result);
		if(actData.equalsIgnoreCase(expData) && act2ndColList)
		{
			
			return true;
		}
		else
		{
			
			return false;
		}
	  
	  
  
	}
	
	
	@FindBy(xpath="//*[@id='MasterSingle__1']")
	public static WebElement report_AccTxt;
	
	@FindBy(xpath="//*[@id='dvReportDetails']//table/tbody/tr/td[4]")
	public static List<WebElement> report4thColList; 
	
	public boolean checkExporttoExcelinItemParameterReport() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException
	{
		focusMainSearch("Item Parameter");
		Thread.sleep(4000);
		
		click(report_AccTxt);
		report_AccTxt.sendKeys("Item2");
		Thread.sleep(4000);
		report_AccTxt.sendKeys(Keys.TAB);
		Thread.sleep(2000);
		click(sl_OkBtn);
		Thread.sleep(8000);
		
		boolean flag=false;
		
		for(int i=0;i<report4thColList.size();i++)
		{
			flag=	report4thColList.get(i).getText().equals("Item2");
			
		}
		
		click(report_NextBtn);
		Thread.sleep(2000);
		for(int i=0;i<report4thColList.size();i++)
		{
			flag=	report4thColList.get(i).getText().equals("Item2");
		}
		
		click(reportEntryExportExcelBtn);
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_ExportPDFYesBtn));
		sl_ExportPDFYesBtn.click();
		Thread.sleep(8000);
		
		File Efile=new File(getBaseDir()+"\\autoIt\\ExportFiles\\ItemParameterExcel.xlsx");
			
			if(Efile.exists())
			{
				Efile.delete();
			}
			
			
			Robot robot = new Robot();
		
			
				
			Runtime.getRuntime().exec(getBaseDir() + "\\autoIt\\scripts\\ItemParameterExcel.exe");
			
			Thread.sleep(8000);
			
			
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
		     

		 	String actExcelfile = getBaseDir()+"\\autoIt\\ExportFiles\\ItemParameterExcel.xlsx";
		 	String expExcelfile = getBaseDir()+"\\autoIt\\ImportFiles\\ItemParameterExcel.xlsx";
		 	String sheet = "Sheet1";
		 	   
		 	
		 	
		 	FileInputStream fip1 = new FileInputStream(actExcelfile);
		 	Workbook workbook1  = WorkbookFactory.create(fip1);
		 	
		 	FileInputStream fip2 = new FileInputStream(expExcelfile);
		 	Workbook workbook2  = WorkbookFactory.create(fip2);
		 	
		 	boolean result = excelReader.checkExcelSheetsComparison(workbook1, workbook2,"17/11/2025");
		 	
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
	
	
	public boolean checkExporttoPDFinItmParameterReport() throws IOException, InterruptedException, AWTException
	{
		

		click(reportEntryExportBtn);
		click(reportEntryExportPDFBtn);
		Thread.sleep(2000);
		click(sl_ExportPDFYesBtn);
		Thread.sleep(6000);
		
		File Efile1 = new File(getBaseDir() + "\\autoIt\\ExportFiles\\ItemParameterPDF.pdf");

		if (Efile1.exists()) {
			Efile1.delete();
		}

		Thread.sleep(4000);
			
		
		
		Robot robot = new Robot();
	
		Runtime.getRuntime().exec(getBaseDir() + "\\autoIt\\scripts\\ItemParameterPDF.exe");

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
		
		
	
		
		
		String actPDF = getBaseDir()+"\\autoIt\\ExportFiles\\ItemParameterPDF.pdf";
		String expPDF = getBaseDir()+"\\autoIt\\ImportFiles\\ItemParameterPDF.pdf";
		
		PDFUtil pdfutil = new PDFUtil();
		
		boolean result = pdfutil.compare(actPDF, expPDF);
		
		String data = pdfutil.getText(expPDF);
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		String date = df.format(cal.getTime());
		System.err.println(date);
		
		String oldDate = "17/11/2025";
		

		
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
	
	
	
	@FindBy(xpath="(//*[@title='Email'])[1]")
	public static WebElement report_ExportEmailBtnfromHome;
	
	
	@FindBy(xpath="//*[contains(text(),'Yes, remove')]")
	private static WebElement yesRemoveBtn;
	
	
	@FindBy(xpath="//*[@id='tomailId']")
	public static WebElement ToAddressInMail;

	@FindBy(xpath="//*[@id='subjectMail']")
	public static WebElement subjectInMail;
	
	
	@FindBy(xpath="//*[@id='attachpdf']")
	public static WebElement attachPDFChkBox;

	@FindBy(xpath="//*[@id='attachpdf']//..//span")
	public static WebElement attachPDFChkBoxSelected;
	
	@FindBy(xpath="//input[@id='identifierId']")
	private static WebElement  userNameTxt;

	@FindBy(xpath="//span[contains(text(),'Next')]")
	private static WebElement NextBtn;

	@FindBy(xpath="//input[@name='Passwd']")
	private static WebElement  PasswordTxt;
	
	@FindBy(xpath="(//*[@id='gb']//iframe)[2]")
	private static WebElement SignOutFrame;


	@FindBy(xpath="//div[@id='yDmH0d']//*[text()='Sign out']")
	private static WebElement gmailSignOutBtn;
	
	@FindBy(xpath="//*[@id='emailSettings']//button[2]")
	public static WebElement okBtnInEmailOptions;
	
	@FindBy(xpath="//tbody/tr/td[5]/div[1]/div/div/span/span")
	private static List<WebElement> emailSubjectList;
	
	@FindBy(xpath="//*[@id='idGlobalError']/div/div[2]")
	public static WebElement validationConfirmationMessages;
	
	@FindBy(xpath="//*[@id=':28']")
	private static WebElement emailBody2;
	
	
	@FindBy(xpath = "(//div[@class='VYBDae-JX-ano'])[1]")
	private static WebElement mailAttachmentDownloadBtn1;
	
	@FindBy(xpath="//*[@id=':4']/div[2]/div[1]/div/div[2]/div[3]")
	private static WebElement mailDeleteButton;
	
	@FindBy(xpath="//*[contains(text(),'Remove an account')]")
	private static WebElement removeAccountBtn;

	//@FindBy(xpath="//body/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/form[1]/span[1]/section[1]/div[1]/div[1]/div[1]/div[1]/ul[1]/li[1]/div[1]/div[2]/*[1]")
	@FindBy(xpath="(//*[@jsname='MBVUVe']//div)[8]")
	private static WebElement removeDeleteBtn;
	
	@FindBy(xpath="//*[@id='gb']/div[2]/div[3]/div[1]/div[2]/div/a/img")
	private static WebElement gmailUserBtn;
	
	public boolean checkEmailinRDMRNDetilReportfromHomePageWithoutAttachment() throws InterruptedException, AWTException, IOException
	{
	
		report_CloseBtn.click();
		Thread.sleep(4000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(report_ExportEmailBtnfromHome));
		report_ExportEmailBtnfromHome.click();
		

			
			Thread.sleep(8000);
					
		
		//Email Report
		

				ToAddressInMail.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
				Thread.sleep(2000);
				ToAddressInMail.sendKeys("emailvalidationfour@gmail.com");
				
				subjectInMail.sendKeys("RD MRN Detail Report without Attachment");
				Thread.sleep(2000);
				
				
				//Thread.sleep(8000);
				
			//	getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(attachPDFChkBoxSelected));
			//	attachPDFChkBoxSelected.click();
			//	Thread.sleep(2000);
				
				okBtnInEmailOptions.click();
				Thread.sleep(8000);
				
				String novalidationConfirmationMessage =validationConfirmationMessages.getText();
				
				String actvalidationConfirmationMessage =novalidationConfirmationMessage;
				String expvalidationConfirmationMessage = "Mail sent successfully";
				
				System.out.println(actvalidationConfirmationMessage);
				
				System.out.println(expvalidationConfirmationMessage);
				
				

	File Efile1=new File(getBaseDir()+"\\autoIt\\ExportFiles\\SalesRegisterEmailFromHome.pdf");
		
		if(Efile1.exists())
		{
			Efile1.delete();
			System.out.println("File deleted");
		}
		
		Thread.sleep(4000);
		
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_T);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_T);
		
		Thread.sleep(4000);
		
		ArrayList<String> openTabs = new ArrayList<String>(getDriver().getWindowHandles());

	 	System.out.println("openTabs"+openTabs);

	 	getDriver().switchTo().window(openTabs.get(0));
	 	Thread.sleep(2000);
	 	getDriver().switchTo().window(openTabs.get(1));
		

	 	getDriver().get("https://accounts.google.com/ServiceLogin/identifier?service=mail&passive=true&rm=false&continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&ss=1&scc=1&ltmpl=default&ltmplcache=2&emr=1&osid=1&flowName=GlifWebSignIn&flowEntry=AddSession");
	 	
	 	Thread.sleep(2000);
	 	getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(userNameTxt));
	 	userNameTxt.click();
	 	
	 	userNameTxt.sendKeys("emailvalidationfour@gmail.com");
	 	
	 	Thread.sleep(2000);
	 	
	 	System.out.println("User enter text");
	 	
	 	getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(NextBtn));
	 	NextBtn.click();
	 	
	 	Thread.sleep(2000);
	 	
	 	getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(PasswordTxt));
	 	PasswordTxt.click();
	 	
	 	PasswordTxt.sendKeys("validationfour");
	 	
	 	Thread.sleep(2000);
	 	
	 	NextBtn.click();
	 	
	 	Thread.sleep(30000);
	 	
	 boolean result=false;
	 
	 		int count = emailSubjectList.size();
	 		System.err.println("No.of mails in the list"+count);
	 		System.out.println(emailBody2.getText().isEmpty());
		 	if(count==0)
		 	{
		 		
		 		System.err.println("No Mails in the List   :"+count);
		 		
		 		
		 	}
		 	else if (emailBody2.getText().isEmpty()==false)
		 	 	{
		 		Thread.sleep(3000);
	 		
		 	for (int i = 0; i < count; i++)
	 	{
	 		String mailFromTxt = emailSubjectList.get(i).getText();
	 		System.out.println(mailFromTxt);
	 	 	
	 		if (mailFromTxt.equalsIgnoreCase("RD MRN Detail Report without Attachment"))
	 		{
	 			emailSubjectList.get(i).click();
	 			break;
	 		}
	 	}
		 	
	 			 	
		 	Thread.sleep(8000);
		
	
		
		if(refreshBtn.isDisplayed())
		{
			
			System.out.println("Subject is not matching");
		 	
		}
		
	 		
	 	
		else
		{
			
			System.out.println("Subject is  matching");
			getAction().moveToElement(mailAttachmentDownloadBtn1).build().perform();
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(mailAttachmentDownloadBtn1));
		mailAttachmentDownloadBtn1.click();
		
		Thread.sleep(3000);
		
		 
		robot.keyPress(KeyEvent.VK_CONTROL);
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

	Thread.sleep(3000);

	robot.keyPress(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_S);
	robot.keyRelease(KeyEvent.VK_S);
	robot.keyRelease(KeyEvent.VK_CONTROL);
		
	Thread.sleep(4000);

			
			Runtime.getRuntime().exec(getBaseDir() + "\\autoIt\\scripts\\SalesRegisterEmailFromHome.exe");
			
			Thread.sleep(8000);
			
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_J);
			robot.keyRelease(KeyEvent.VK_J);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			
			Thread.sleep(4000);
			
			ArrayList<String> newTabs = new ArrayList<String>(getDriver().getWindowHandles());
				
			int actOpenWindowsCount = getDriver().getWindowHandles().size();
			int expOpenWindowsCount = 4;
			
			System.out.println("Number of Windows  : "+actOpenWindowsCount+"  Value Expected  "+expOpenWindowsCount);
			
		/*	getDriver().switchTo().window(newTabs.get(3)).close();
		 	Thread.sleep(1000);
		 	getDriver().switchTo().window(newTabs.get(2)).close();
		 	Thread.sleep(1000);
		 	getDriver().switchTo().window(newTabs.get(1));
		 	Thread.sleep(1000);*/
			
			
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
			
		 	getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(mailDeleteButton));
		 	mailDeleteButton.click();
		 	
		 	Thread.sleep(2000);
		 	

		 	Thread.sleep(2000);
		 	
		 	String actAccount1PDF = getBaseDir()+"\\autoIt\\ExportFiles\\SalesRegisterEmailFromHome.pdf";
			String expAccount1PDF = getBaseDir()+"\\autoIt\\ImportFiles\\SalesRegisterEmailFromHome.pdf";
			
		
		 
			PDFUtil pdfutil = new PDFUtil();
		
			boolean result1 = pdfutil.compare(actAccount1PDF, expAccount1PDF);
			Calendar cal=Calendar.getInstance();
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			String currentDate = df.format(cal.getTime());
			
			String  actAccount1Data = pdfutil.getText(actAccount1PDF);
			String  expAccount1Data = pdfutil.getText(expAccount1PDF).replaceAll("20/02/2025", currentDate).replaceAll("February", getCurrentMonth());
					
			System.err.println(actAccount1Data);
			System.err.println(expAccount1Data);
			
			if(actAccount1Data.equalsIgnoreCase(expAccount1Data))
			{
				result=true;
			}
		 	
		}
			
		 	 	}
			ArrayList<String> newTabs = new ArrayList<String>(getDriver().getWindowHandles());
			ClickUsingJs(gmailUserBtn);

			Thread.sleep(3000);
			getDriver().switchTo().frame(SignOutFrame);
			Thread.sleep(2000);
			
			getAction().moveToElement(gmailSignOutBtn).click().build().perform();
			
			getDriver().get("https://accounts.google.com/AccountChooser/signinchooser?service=mail&continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&flowName=GlifWebSignIn&flowEntry=AccountChooser");
			 Thread.sleep(2000);

			
		 	getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(removeAccountBtn));
		 	removeAccountBtn.click();
		 	
		 	Thread.sleep(1000);
		 	
		 	getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(removeDeleteBtn));
		 	removeDeleteBtn.click();
		 	
		 	Thread.sleep(2000);
		 	
		 	getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(yesRemoveBtn));
		 	yesRemoveBtn.click();
		 	
		 	Thread.sleep(1000);
		 	
		/*	getDriver().switchTo().window(newTabs.get(1)).close();
		 	Thread.sleep(2000);
		 	
		 	
		 	getDriver().switchTo().window(newTabs.get(0));
		 	Thread.sleep(2000);*/
		 	
		 	robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_W);
			robot.keyRelease(KeyEvent.VK_W);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			
	
				 	 		
		 	if (result && count!=0)
			{
			
				System.err.println("PDF file is as Expected");
				return true;
			}
			else
			{
									
				System.err.println("PDF file is not as Expected");
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
		 
	
	
	public ReportDesignerPageNew(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
}
