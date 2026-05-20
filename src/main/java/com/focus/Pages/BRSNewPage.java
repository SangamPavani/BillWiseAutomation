package com.focus.Pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.focus.base.BaseEngine;
import com.focus.supporters.ExcelReader;
import com.focus.utilities.POJOUtility;

public class BRSNewPage extends BaseEngine{
	
	
	@FindBy(xpath = "//*[@id='1' and @title='Home']")
	public static WebElement homeMenu;
	
	public boolean checkLogin()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		getDriver().navigate().refresh();

		
		Thread.sleep(1999);

		getDriver().navigate().refresh();

		Thread.sleep(1999);
		
		  
		  Thread.sleep(3500);
		  
		 // prongHornStartAtAdminLevel();
		  
		  Thread.sleep(8000);
		 
		LoginPage lp = new LoginPage(getDriver());

		Thread.sleep(3000);

		String unamelt = "su";

		String pawslt = "su";

		lp.enterUserName(unamelt);

		Thread.sleep(2000);

		lp.enterPassword(pawslt);

		String compname = "BRS";

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

		Thread.sleep(5000);
		
		

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(companyLogo));
		//companyLogo.click();

		if (homeMenu.isDisplayed()) {
			System.out.println("Test Pass :Logined to BRS Company");
			
			return true;

		} else {
			System.out.println("Test Fail :Logined to BRS Company");
			
			return false;

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
		
	}
	
	
	@FindBy(xpath="//*[@id='brsMainDiv']//ol//li//span")
	public static WebElement BRSLabel;
	
	public boolean checkBankReconciliationReport()throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException 
	{
		click(financialsMenu);
		click(financialsReportsMenu);
		click(cashAndBankBooksMenu);
		
		getAction().moveToElement(bankReconciliationReport).build().perform();
		click(bankReconciliationReport);
		
		Thread.sleep(9000);
		System.out.println(BRSLabel.getText());
		
		boolean novalidationConfirmationMessage = validationConfirmationMessage.getText().isEmpty();
		System.out.println(novalidationConfirmationMessage);
		click(LoadBtn);
		Thread.sleep(4000);
		
		String expvalidationConfirmationMessage = "Please select Bank Account";
		String actvalidationConfirmationMessage = checkValidationMessage(expvalidationConfirmationMessage);

		System.out.println("validationConfirmationMessage : " + actvalidationConfirmationMessage + " Value Expected : "
				+ expvalidationConfirmationMessage);

		if(novalidationConfirmationMessage&& actvalidationConfirmationMessage.equalsIgnoreCase(expvalidationConfirmationMessage))
		{
			return true;
		}
		else
		{
			return false;
		}

	}
	
	@FindBy(xpath="(//i[@class='icon-menudots btn-img'])[2]")
	public static WebElement BRS_BankCustBtn;
	
	@FindBy(xpath="//button[text()='Standard Fields']")
	public static WebElement BRS_StandardFieldsBtn;
	
	@FindBy(xpath="//*[@id='OptCtrlBank_customize_popup_standardfields_list']")
	public static WebElement BRS_ColAttFieldsSelect;
	
	@FindBy(xpath="(//input[@value='Ok'])[1]")
	public static WebElement BRS_ColAttOKBtn;
	
	@FindBy(xpath="//*[@id='OptCtrlBank_customize_popup_container']//span")
	public static  List<WebElement> BRS_CustDisPlayColList;
	
	@FindBy(xpath="//*[@id='OptCtrlBank_table_head']//th//div[1]")
	public static  List<WebElement> BRS_BankHeaderList;
	
	public boolean checkBankCustomizationforStandardFieldsinBRS() throws InterruptedException
	{
		click(BRS_BankCustBtn);
		click(BRS_StandardFieldsBtn);
		click(BRS_ColAttFieldsSelect);
		Thread.sleep(1500);
		Select s= new Select(BRS_ColAttFieldsSelect);
		s.selectByVisibleText("fCreditLimit");
		Thread.sleep(2000);
		//BRS_ColAttFieldsSelect.sendKeys(Keys.TAB);
		
		click(BRS_ColAttOKBtn);
		Thread.sleep(1000);
		
		
		
		ArrayList<String>CustColListArray=new ArrayList<String>();
		
		for(WebElement e: BRS_CustDisPlayColList)
		{
			CustColListArray.add(e.getText());
		}
		
		String actCustColList=CustColListArray.toString();
		String expCustColList="[sName, CreditLimit]";
		
		System.out.println("Actual Customize Column Display		"		+	actCustColList);
		System.out.println("Expect Customize Column Display		"		+	expCustColList);
		
		click(BRS_ColAttOKBtn);
		Thread.sleep(2000);
		click(reportbankTxt);
		reportbankTxt.sendKeys(Keys.SPACE);
		
		ArrayList<String>bankHeaderList=new ArrayList<String>();
		
		for(WebElement e: BRS_BankHeaderList)
		{
			bankHeaderList.add(e.getText());
		}
			
		String actBankHeaderList=bankHeaderList.toString();
		String expBankHeaderList="[, sName, CreditLimit]";
		
		System.out.println("Actual Bank Header List		"		+	actBankHeaderList);
		System.out.println("Expect Bank HeaderList		"		+	expBankHeaderList);
		
		reportbankTxt.sendKeys("BANK");
		Thread.sleep(1500);
		reportbankTxt.sendKeys(Keys.TAB);
		
		if(actCustColList.equalsIgnoreCase(expCustColList) && actBankHeaderList.equalsIgnoreCase(expBankHeaderList))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	@FindBy(xpath = "//Select[@id='sortOrder']")
	public static WebElement BRSortDrpdwn;
	
	@FindBy(xpath = "//label[@id='bookBal']")
	public static WebElement bankRecBookBal;

	@FindBy(xpath = "//*[@id='CancelCreateUser']/i")
	public static WebElement bankRecCloseBn;
	
	
	@FindBy(xpath = "//label[@id='outDebits']")
	public static WebElement bankRecOutDebits;

	@FindBy(xpath = "//label[@id='outCredits']")
	public static WebElement bankRecOutCredits;

	@FindBy(xpath = "//label[@id='clearedBal']")
	public static WebElement bankRecClearedBal;

	@FindBy(xpath = "//label[@id='OpeningBalance']")
	public static WebElement bankRecOpenBal;

	@FindBy(xpath = "//label[@id='debitCounts']")
	public static WebElement bankRecDebitCounts;

	@FindBy(xpath = "//label[@id='creditCounts']")
	public static WebElement bankRecCreditCounts;

	@FindBy(xpath = "//input[@id='bankBal']")
	public static WebElement bankRecBankBal;

	@FindBy(xpath = "//label[@id='diff']")
	public static WebElement bankRecDif;
	
	@FindBy(xpath = "//*[@id='BRTable_body']/tr[1]/td")
	public static List<WebElement> bankRecRow1List;

	@FindBy(xpath = "//*[@id='BRTable_body']/tr[2]/td")
	public static List<WebElement> bankRecRow2List;

	@FindBy(xpath = "//*[@id='BRTable_body']/tr[3]/td")
	public static List<WebElement> bankRecRow3List;

	@FindBy(xpath = "//*[@id='BRTable_body']/tr[4]/td")
	public static List<WebElement> bankRecRow4List;
	
	
	public boolean checkSortOrderOptionsinBRSReport() throws InterruptedException
	{
		click(BRSortDrpdwn);
		ArrayList<String>sortOrderListArray=new ArrayList<String>();
		Select s=new Select(BRSortDrpdwn);
		
		for(WebElement e: s.getOptions())
		{
			sortOrderListArray.add(e.getText());
		}
		
		String actSortOrderList=sortOrderListArray.toString();
		String expSortOrderList="[Please Select..., DocumentType + Clearance Date + Cheque No., Date + Document No., Document type + Cheque No.]";
		
		System.out.println("Actual Sort Order Lsit		"		+		actSortOrderList);
		System.out.println("Expect Sort Order Lsit		"		+		expSortOrderList);
		
		s.selectByValue("0");
		BRSortDrpdwn.sendKeys(Keys.TAB);
		
		//click(BRSSelectCRDRDrpdwn);
		Select s1 = new Select(BRSSelectCRDRDrpdwn);
		s1.selectByValue("2");
		Thread.sleep(2000);
		BRSSelectCRDRDrpdwn.sendKeys(Keys.TAB);
		
		click(LoadBtn);
		
		Thread.sleep(8000);
		
		ArrayList<String>brsRow1ArrayList=new ArrayList<String>();
		for(int i=0;i<bankRecRow1List.size();i++)
		{
			brsRow1ArrayList.add(bankRecRow1List.get(i).getText());
		}
		
		String actBRSRow1List=brsRow1ArrayList.toString();
		String expBRSRow1List="[1, Pending, , 08/04/2021, DebNts:1, 08/04/2021, 4.00, 0.00, Debit Notes, , , , ]";
		
		System.out.println("Actual Row1 List	"	+	actBRSRow1List);
		System.out.println("Expect Row1 List	"	+	expBRSRow1List);
		
		
		ArrayList<String>brsRow2ArrayList=new ArrayList<String>();
		for(int i=0;i<bankRecRow2List.size();i++)
		{
			brsRow2ArrayList.add(bankRecRow2List.get(i).getText());
		}
		
		String actBRSRow2List=brsRow2ArrayList.toString();
		String expBRSRow2List="[2, Pending, , 08/04/2021, NDT62:1, 08/04/2021, 0.00, 2.00, Debit Notes VAT, , , , ]";
		
		System.out.println("Actual Row2 List	"	+	actBRSRow2List);
		System.out.println("Expect Row2 List	"	+	expBRSRow2List);
		
		ArrayList<String>brsRow3ArrayList=new ArrayList<String>();
		for(int i=0;i<bankRecRow3List.size();i++)
		{
			brsRow3ArrayList.add(bankRecRow3List.get(i).getText());
		}
		
		String actBRSRow3List=brsRow3ArrayList.toString();
		String expBRSRow3List="[3, Pending, , 08/04/2021, Rct:1, 08/04/2021, 6.00, 0.00, Receipts, , , , ]";
		
		System.out.println("Actual Row3 List	"	+	actBRSRow3List);
		System.out.println("Expect Row3 List	"	+	expBRSRow3List);
		
		
		   String actBookBal=bankRecBookBal.getText();
		   String expBookBal="20.50 Cr";

			String actbankRecOutDebits=bankRecOutDebits.getText();
		    String expbankRecOutDebits="147.00 Dr";
		
			String actbankRecOutCredits=bankRecOutCredits.getText();
		    String expbankRecOutCredits="167.50 Cr";
		
			String actbankRecClearedBal=bankRecClearedBal.getText();
		    String expbankRecClearedBal="0.00";
		
			String actbankRecOpenBal=bankRecOpenBal.getText();
		    String expbankRecOpenBal="0.00";
			

			String actbankRecDebitCounts=bankRecDebitCounts.getText();
		    String expbankRecDebitCounts="9";
			
			String actbankRecCreditCounts=bankRecCreditCounts.getText();
		    String expbankRecCreditCounts="8";
		    
			String actbankRecBankBal=bankRecBankBal.getAttribute("value");
		    String expbankRecBankBal="0.00";
		    
		    String actbankRecDiff=bankRecDif.getText();
		    String expbankRecDiff="0.00";

			
			
		   System.out.println("**********************************checkBankReconciliationReport*****************************************");
		   System.out.println("BookBal             : "+actBookBal             +" Value Expected  : "+expBookBal);
	       System.out.println("bankRecOutDebits    : "+actbankRecOutDebits    +" Value Expected  : "+expbankRecOutDebits);
	       System.out.println("bankRecOutCredits   : "+actbankRecOutCredits   +" Value Expected  : "+expbankRecOutCredits);
	       System.out.println("bankRecClearedBal   : "+actbankRecClearedBal   +" Value Expected  : "+expbankRecClearedBal);
	       System.out.println("Opening Bal         : "+actbankRecOpenBal      +" Value Expected  : "+expbankRecOpenBal);
	       System.out.println("bankRecDebitCounts  : "+actbankRecDebitCounts  +" Value Expected  : "+expbankRecDebitCounts);
	       System.out.println("bankRecCreditCounts : "+actbankRecCreditCounts +" Value Expected  : "+expbankRecCreditCounts);
	       System.out.println("bankRecBankBal      : "+actbankRecBankBal      +" Value Expected  : "+expbankRecBankBal);
	       System.out.println("bankRecDifferr      : "+actbankRecDiff	      +" Value Expected  : "+expbankRecDiff);
 
		if(actBRSRow1List.equalsIgnoreCase(expBRSRow1List) && actBRSRow2List.equalsIgnoreCase(expBRSRow2List) && 
				actBRSRow3List.equalsIgnoreCase(expBRSRow3List) && actBookBal.equalsIgnoreCase(expBookBal) &&
				actbankRecOutDebits.equalsIgnoreCase(expbankRecOutDebits) && actbankRecOutCredits.equalsIgnoreCase(expbankRecOutCredits) &&
				actbankRecClearedBal.equalsIgnoreCase(expbankRecClearedBal) && actbankRecDebitCounts.equalsIgnoreCase(expbankRecDebitCounts) &&
				actbankRecCreditCounts.equalsIgnoreCase(expbankRecCreditCounts) && actbankRecBankBal.equalsIgnoreCase(expbankRecBankBal)
				&& actbankRecDiff.equalsIgnoreCase(expbankRecDiff))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	@FindBy(xpath="//span[@class='icon-pick']")
	public static WebElement pickBtn;

	@FindBy(xpath="//span[@class='icon-ok']")
	public static WebElement voucher_OkBtn;
	
	@FindBy(xpath="//*[@id='id_search_menu']//input")
	public static WebElement serachMenuTextHomePage;

	@FindBy(xpath="//li/span")
	public static WebElement searchMenuTextClick;
	
	public boolean checkSavingReceiptsVoucherwithBlankChequeNumber() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		getDriver().navigate().refresh();
		Thread.sleep(4000);
		
		Thread.sleep(2000);
		click(serachMenuTextHomePage);
		
		serachMenuTextHomePage.sendKeys("Receipts");
		Thread.sleep(2000);
		serachMenuTextHomePage.sendKeys(Keys.DOWN);
		serachMenuTextHomePage.sendKeys(Keys.ENTER);
		//click(searchMenuTextClick);
		 
		Thread.sleep(15000);
		new WebDriverWait(getDriver(), 300).until(ExpectedConditions.visibilityOf(newBtn));
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(newBtn));
		newBtn.click();
		Thread.sleep(4000);
		
		Thread.sleep(2000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNumberTxt));
		String docno=documentNumberTxt.getAttribute("value");
		
		click(caskBankAccountTxt);
		caskBankAccountTxt.sendKeys("HDFC");
		Thread.sleep(2000);
		caskBankAccountTxt.sendKeys(Keys.TAB);
		
		
		
		click(departmentTxt);
		departmentTxt.sendKeys("AMERICA");
		Thread.sleep(2000);
		departmentTxt.sendKeys(Keys.TAB);
		
		
		
		/*click(receipts_ChequeNoTxt);
		receipts_ChequeNoTxt.sendKeys("Rpt-1234");
		Thread.sleep(2000);
		receipts_ChequeNoTxt.sendKeys(Keys.TAB);*/
		
		
		
		click(select1stRow_1stColumn);
		
		click(enter_DebitACTxt);
		enter_DebitACTxt.sendKeys("Customer C");
		Thread.sleep(2000);
		enter_DebitACTxt.sendKeys(Keys.TAB);
		
		
		enter_Amount.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
		enter_Amount.sendKeys("255.69");
		Thread.sleep(2000);
		enter_Amount.sendKeys(Keys.TAB);
		
		
		
		click(billRefNewReferenceTxt);
		click(pickBtn);
		Thread.sleep(2000);
		
		click(voucher_OkBtn);
		Thread.sleep(4000);
		
		click(saveBtn);
		Thread.sleep(2000);
		
		String expMsg="Voucher saved successfully";
		String expMsg1=": 3";
		
		String actMsg=checkValidationMessage(expMsg);
		
		System.out.println("Actual Message		"	+	actMsg		+	"Expected Message	"	+	expMsg);
		
		if(actMsg.startsWith(expMsg) && actMsg.endsWith(expMsg1))
		{
			return true;
		}
		else
		{
			return false;
		}
		

	}
	
	public boolean checkSavingPaymentsVoucherwithBlankChequeNumber() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		

		
		Thread.sleep(2000);
		click(serachMenuTextHomePage);
		
		serachMenuTextHomePage.sendKeys("Payments");
		Thread.sleep(2000);
		serachMenuTextHomePage.sendKeys(Keys.DOWN);
		serachMenuTextHomePage.sendKeys(Keys.ENTER);
		//click(searchMenuTextClick);
		 
		Thread.sleep(8000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(newBtn));
		newBtn.click();
		Thread.sleep(4000);
		
		Thread.sleep(2000);
		//getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNumberTxt));
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNumberTxt));
				
		click(caskBankAccountTxt);
		caskBankAccountTxt.sendKeys("HDFC");
		Thread.sleep(2000);
		caskBankAccountTxt.sendKeys(Keys.TAB);
		
		
		
		click(departmentTxt);
		departmentTxt.sendKeys("AMERICA");
		Thread.sleep(2000);
		departmentTxt.sendKeys(Keys.TAB);
		
		/*
		
		click(payments_ChequeNoTxt);
		payments_ChequeNoTxt.sendKeys("pmt-1234");
		Thread.sleep(2000);
		payments_ChequeNoTxt.sendKeys(Keys.TAB);*/
		
		
		
		click(select1stRow_1stColumn);
		
		click(enter_DebitACTxt);
		enter_DebitACTxt.sendKeys("Vendor B");
		Thread.sleep(2000);
		enter_DebitACTxt.sendKeys(Keys.TAB);
		
		
		enter_Amount.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
		enter_Amount.sendKeys("1900.85");
		Thread.sleep(2000);
		enter_Amount.sendKeys(Keys.TAB);
		
		
		click(billRefNewReferenceTxt);
		click(pickBtn);
		Thread.sleep(2000);
		
		click(voucher_OkBtn);
		Thread.sleep(4000);
		
		click(saveBtn);
		Thread.sleep(2000);
		
		String expMsg="Voucher saved successfully";
		String expMsg1=": 4";
		
		String actMsg=checkValidationMessage(expMsg);
		
		System.out.println("Actual Message		"	+	actMsg		+	"Expected Message	"	+	expMsg);
		
		if(actMsg.startsWith(expMsg) && actMsg.endsWith(expMsg1))
		{
			return true;
		}
		else
		{
			return false;
		}
		

	}
	
	
	@FindBy(xpath = "//select[@id='SelectDebit']")
	public static WebElement BRSSelectCRDRDrpdwn;
	
	@FindBy(xpath = "//select[@id='selectStatus']")
	public static WebElement BRSSelectStatusDrpdwn;
	
	
	public boolean checkBankReconsillationReportforBlankChequesortingOrder() throws InterruptedException
	{
		
		click(serachMenuTextHomePage);
		
		serachMenuTextHomePage.sendKeys("Bank Reconciliation");
		Thread.sleep(2000);
		serachMenuTextHomePage.sendKeys(Keys.ENTER);
		
		Thread.sleep(3500);
		
		click(reportbankTxt);
		reportbankTxt.sendKeys("HDFC");
		Thread.sleep(1500);
		reportbankTxt.sendKeys(Keys.TAB);
		
		//click(BRSortDrpdwn);
		Select s=new Select(BRSortDrpdwn);
		s.selectByValue("1");
		Thread.sleep(2000);
		BRSortDrpdwn.sendKeys(Keys.TAB);
		
		//click(BRSSelectStatusDrpdwn);
		Select s1=new Select(BRSSelectStatusDrpdwn);
		s1.selectByValue("2");
		Thread.sleep(3000);
		BRSSelectStatusDrpdwn.sendKeys(Keys.TAB);
		
		//click(BRSSelectCRDRDrpdwn);
		Select s2=new Select(BRSSelectCRDRDrpdwn);
		s2.selectByValue("2");
		Thread.sleep(3000);
		BRSSelectCRDRDrpdwn.sendKeys(Keys.TAB);
		
		click(LoadBtn);
		Thread.sleep(8000);
		
		ArrayList<String>brsRow1ArrayList=new ArrayList<String>();
		for(int i=0;i<bankRecRow1List.size();i++)
		{
			brsRow1ArrayList.add(bankRecRow1List.get(i).getText());
		}
		
		String actBRSRow1List=brsRow1ArrayList.toString();
		String expBRSRow1List="[1, Pending, , "+getCurrentDate()+", Rct:3, "+getCurrentDate()+", 255.69, 0.00, Receipts, , , , ]";
		
		System.out.println("Actual Row1 List	"	+	actBRSRow1List);
		System.out.println("Expect Row1 List	"	+	expBRSRow1List);
		
		
		ArrayList<String>brsRow2ArrayList=new ArrayList<String>();
		for(int i=0;i<bankRecRow2List.size();i++)
		{
			brsRow2ArrayList.add(bankRecRow2List.get(i).getText());
		}
		
		String actBRSRow2List=brsRow2ArrayList.toString();
		String expBRSRow2List="[2, Pending, , "+getCurrentDate()+", Pmt:4, "+getCurrentDate()+", 0.00, 1,900.85, Payments, , , , ]";
		
		System.out.println("Actual Row2 List	"	+	actBRSRow2List);
		System.out.println("Expect Row2 List	"	+	expBRSRow2List);
		
		
		
		   String actBookBal=bankRecBookBal.getText();
		   String expBookBal="1,645.16 Cr";

			String actbankRecOutDebits=bankRecOutDebits.getText();
		    String expbankRecOutDebits="255.69 Dr";
		
			String actbankRecOutCredits=bankRecOutCredits.getText();
		    String expbankRecOutCredits="1,900.85 Cr";
		
			String actbankRecClearedBal=bankRecClearedBal.getText();
		    String expbankRecClearedBal="0.00";
		
			String actbankRecOpenBal=bankRecOpenBal.getText();
		    String expbankRecOpenBal="0.00";
			

			String actbankRecDebitCounts=bankRecDebitCounts.getText();
		    String expbankRecDebitCounts="1";
			
			String actbankRecCreditCounts=bankRecCreditCounts.getText();
		    String expbankRecCreditCounts="1";
		    
			String actbankRecBankBal=bankRecBankBal.getAttribute("value");
		    String expbankRecBankBal="0.00";
			
		    String actbankRecDiff=bankRecDif.getText();
		    String expbankRecDiff="0.00";


			
		   System.out.println("**********************************checkBankReconciliationReport*****************************************");
		   System.out.println("BookBal             : "+actBookBal             +" Value Expected  : "+expBookBal);
	       System.out.println("bankRecOutDebits    : "+actbankRecOutDebits    +" Value Expected  : "+expbankRecOutDebits);
	       System.out.println("bankRecOutCredits   : "+actbankRecOutCredits   +" Value Expected  : "+expbankRecOutCredits);
	       System.out.println("bankRecClearedBal   : "+actbankRecClearedBal   +" Value Expected  : "+expbankRecClearedBal);
	       System.out.println("Opening Bal         : "+actbankRecOpenBal      +" Value Expected  : "+expbankRecOpenBal);
	       System.out.println("bankRecDebitCounts  : "+actbankRecDebitCounts  +" Value Expected  : "+expbankRecDebitCounts);
	       System.out.println("bankRecCreditCounts : "+actbankRecCreditCounts +" Value Expected  : "+expbankRecCreditCounts);
	       System.out.println("bankRecBankBal      : "+actbankRecBankBal      +" Value Expected  : "+expbankRecBankBal);
	       System.out.println("bankRecDifferr      : "+actbankRecDiff	      +" Value Expected  : "+expbankRecDiff);

		if(actBRSRow1List.equalsIgnoreCase(expBRSRow1List) && actBRSRow2List.equalsIgnoreCase(expBRSRow2List) && 
				 actBookBal.equalsIgnoreCase(expBookBal) &&
				actbankRecOutDebits.equalsIgnoreCase(expbankRecOutDebits) && actbankRecOutCredits.equalsIgnoreCase(expbankRecOutCredits) &&
				actbankRecClearedBal.equalsIgnoreCase(expbankRecClearedBal) && actbankRecDebitCounts.equalsIgnoreCase(expbankRecDebitCounts) &&
				actbankRecOpenBal.equalsIgnoreCase(expbankRecBankBal)&&
				actbankRecCreditCounts.equalsIgnoreCase(expbankRecCreditCounts) && actbankRecBankBal.equalsIgnoreCase(expbankRecBankBal)
				&& actbankRecDiff.equalsIgnoreCase(expbankRecDiff))
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	
	
	public boolean checkSavingReceiptsVoucherwithSameCleranceDateDiffChequeNum() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		Thread.sleep(2000);
		click(serachMenuTextHomePage);
		
		serachMenuTextHomePage.sendKeys("Receipts");
		Thread.sleep(2000);
		serachMenuTextHomePage.sendKeys(Keys.DOWN);
		serachMenuTextHomePage.sendKeys(Keys.ENTER);
		//click(searchMenuTextClick);
		 
		Thread.sleep(6000);
		new WebDriverWait(getDriver(), 300).until(ExpectedConditions.visibilityOf(newBtn));
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(newBtn));
		newBtn.click();
		Thread.sleep(4000);
		
		Thread.sleep(2000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNumberTxt));
		click(dateTxt);
		dateTxt.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
		
		 Calendar cal=Calendar.getInstance();
		 cal.add(Calendar.MONTH, -1);
		 cal.add(Calendar.DATE, -5);
		 SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		dateTxt.sendKeys(f.format(cal.getTime()));
		dateTxt.sendKeys(Keys.TAB);
		
		click(caskBankAccountTxt);
		caskBankAccountTxt.sendKeys("HDFC");
		Thread.sleep(2000);
		caskBankAccountTxt.sendKeys(Keys.TAB);
		
		click(maturityDateTxt);
		maturityDateTxt.sendKeys(f.format(cal.getTime()));
		maturityDateTxt.sendKeys(Keys.TAB);
		
		click(departmentTxt);
		departmentTxt.sendKeys("AMERICA");
		Thread.sleep(2000);
		departmentTxt.sendKeys(Keys.TAB);
		
		
		
		click(receipts_ChequeNoTxt);
		receipts_ChequeNoTxt.sendKeys("Rpt-001");
		Thread.sleep(2000);
		receipts_ChequeNoTxt.sendKeys(Keys.TAB);
		
		
		
		click(select1stRow_1stColumn);
		
		click(enter_DebitACTxt);
		enter_DebitACTxt.sendKeys("Customer C");
		Thread.sleep(2000);
		enter_DebitACTxt.sendKeys(Keys.TAB);
		
		
		enter_Amount.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
		enter_Amount.sendKeys("1085.03");
		Thread.sleep(2000);
		enter_Amount.sendKeys(Keys.TAB);
		
		
		
		click(billRefNewReferenceTxt);
		click(pickBtn);
		Thread.sleep(2000);
		
		click(voucher_OkBtn);
		Thread.sleep(4000);
		
		click(saveBtn);
		Thread.sleep(2000);
		
		String expMsg="Voucher saved successfully";
		String expMsg1=": 4";
		
		String actMsg=checkValidationMessage(expMsg);
		
		System.out.println("Actual Message		"	+	actMsg		+	"Expected Message	"	+	expMsg);
		
		if(actMsg.startsWith(expMsg) && actMsg.endsWith(expMsg1))
		{
			return true;
		}
		else
		{
			return false;
		}
		

	}
	
	public boolean checkSavingPaymentsVoucherwithSameCleranceDateDiffChequeNum() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		

		
		Thread.sleep(2000);
		click(serachMenuTextHomePage);
		
		serachMenuTextHomePage.sendKeys("Payments");
		Thread.sleep(2000);
		serachMenuTextHomePage.sendKeys(Keys.DOWN);
		serachMenuTextHomePage.sendKeys(Keys.ENTER);
		//click(searchMenuTextClick);
		 
		Thread.sleep(6000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(newBtn));
		newBtn.click();
		Thread.sleep(4000);
		
		Thread.sleep(2000);
		//getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNumberTxt));
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNumberTxt));
		

		 Calendar cal=Calendar.getInstance();
		 cal.add(Calendar.MONTH, -1);
		 cal.add(Calendar.DATE, -5);
		 SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		click(dateTxt);
		
		dateTxt.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
		dateTxt.sendKeys(f.format(cal.getTime()));
		dateTxt.sendKeys(Keys.TAB);

		click(caskBankAccountTxt);
		caskBankAccountTxt.sendKeys("HDFC");
		Thread.sleep(2000);
		caskBankAccountTxt.sendKeys(Keys.TAB);
		
		click(maturityDateTxt);
		maturityDateTxt.sendKeys(f.format(cal.getTime()));
		maturityDateTxt.sendKeys(Keys.TAB);
		
				
		click(departmentTxt);
		departmentTxt.sendKeys("AMERICA");
		Thread.sleep(2000);
		departmentTxt.sendKeys(Keys.TAB);
		
		
		
		click(payments_ChequeNoTxt);
		payments_ChequeNoTxt.sendKeys("pmt-001");
		Thread.sleep(2000);
		payments_ChequeNoTxt.sendKeys(Keys.TAB);
		
		
		
		click(select1stRow_1stColumn);
		
		click(enter_DebitACTxt);
		enter_DebitACTxt.sendKeys("Vendor B");
		Thread.sleep(2000);
		enter_DebitACTxt.sendKeys(Keys.TAB);
		
		
		enter_Amount.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
		enter_Amount.sendKeys("1005.28");
		Thread.sleep(2000);
		enter_Amount.sendKeys(Keys.TAB);
		
		
		click(billRefNewReferenceTxt);
		click(pickBtn);
		Thread.sleep(2000);
		
		click(voucher_OkBtn);
		Thread.sleep(4000);
		
		click(saveBtn);
		Thread.sleep(2000);
		
		String expMsg="Voucher saved successfully";
		String expMsg1=": 5";
		
		String actMsg=checkValidationMessage(expMsg);
		
		System.out.println("Actual Message		"	+	actMsg		+	"Expected Message	"	+	expMsg);
		
		if(actMsg.startsWith(expMsg) && actMsg.endsWith(expMsg1))
		{
			return true;
		}
		else
		{
			return false;
		}
		

	}
	
	public boolean checkBankReconciliationReportforSameCleranceDateDiffChequeNum() throws InterruptedException
	{
		
		click(serachMenuTextHomePage);
		
		serachMenuTextHomePage.sendKeys("Bank Reconciliation");
		Thread.sleep(2000);
		serachMenuTextHomePage.sendKeys(Keys.ENTER);
		
		Thread.sleep(3500);
		
		click(reportbankTxt);
		reportbankTxt.sendKeys("HDFC");
		Thread.sleep(1500);
		reportbankTxt.sendKeys(Keys.TAB);
		
		//click(BRSortDrpdwn);
		Select s=new Select(BRSortDrpdwn);
		s.selectByValue("1");
		Thread.sleep(2000);
		BRSortDrpdwn.sendKeys(Keys.TAB);
		
		//click(BRSSelectStatusDrpdwn);
		Select s1=new Select(BRSSelectStatusDrpdwn);
		s1.selectByValue("2");
		Thread.sleep(3000);
		BRSSelectStatusDrpdwn.sendKeys(Keys.TAB);
		
		//click(BRSSelectCRDRDrpdwn);
		Select s2=new Select(BRSSelectCRDRDrpdwn);
		s2.selectByValue("2");
		Thread.sleep(3000);
		BRSSelectCRDRDrpdwn.sendKeys(Keys.TAB);
		
		click(LoadBtn);
		Thread.sleep(8000);
		
		 Calendar cal=Calendar.getInstance();
		 cal.add(Calendar.MONTH, -1);
		 cal.add(Calendar.DATE, -5);
		 SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		
		ArrayList<String>brsRow1ArrayList=new ArrayList<String>();
		for(int i=0;i<bankRecRow1List.size();i++)
		{
			brsRow1ArrayList.add(bankRecRow1List.get(i).getText());
		}
		
		String actBRSRow1List=brsRow1ArrayList.toString();
		String expBRSRow1List="[1, Pending, Rpt-001, "+f.format(cal.getTime())+", Rct:4, "+f.format(cal.getTime())+", 1,085.03, 0.00, Receipts, , , , ]";
		
		System.out.println("Actual Row1 List	"	+	actBRSRow1List);
		System.out.println("Expect Row1 List	"	+	expBRSRow1List);
		
		
		ArrayList<String>brsRow2ArrayList=new ArrayList<String>();
		for(int i=0;i<bankRecRow2List.size();i++)
		{
			brsRow2ArrayList.add(bankRecRow2List.get(i).getText());
		}
		
		String actBRSRow2List=brsRow2ArrayList.toString();
		String expBRSRow2List="[2, Pending, , "+getCurrentDate()+", Rct:3, "+getCurrentDate()+", 255.69, 0.00, Receipts, , , , ]";
		
		System.out.println("Actual Row2 List	"	+	actBRSRow2List);
		System.out.println("Expect Row2 List	"	+	expBRSRow2List);
		
		ArrayList<String>brsRow3ArrayList=new ArrayList<String>();
		for(int i=0;i<bankRecRow3List.size();i++)
		{
			brsRow3ArrayList.add(bankRecRow3List.get(i).getText());
		}
		
		String actBRSRow3List=brsRow3ArrayList.toString();
		String expBRSRow3List="[3, Pending, pmt-001, "+f.format(cal.getTime())+", Pmt:5, "+f.format(cal.getTime())+", 0.00, 1,005.28, Payments, , , , ]";
		
		System.out.println("Actual Row3 List	"	+	actBRSRow3List);
		System.out.println("Expect Row3 List	"	+	expBRSRow3List);
		
		
		ArrayList<String>brsRow4ArrayList=new ArrayList<String>();
		for(int i=0;i<bankRecRow4List.size();i++)
		{
			brsRow4ArrayList.add(bankRecRow4List.get(i).getText());
		}
		
		String actBRSRow4List=brsRow4ArrayList.toString();
		String expBRSRow4List="[4, Pending, , "+getCurrentDate()+", Pmt:4, "+getCurrentDate()+", 0.00, 1,900.85, Payments, , , , ]";
		
		System.out.println("Actual Row4 List	"	+	actBRSRow4List);
		System.out.println("Expect Row4 List	"	+	expBRSRow4List);
		
		
		   String actBookBal=bankRecBookBal.getText();
		   String expBookBal="1,565.41 Cr";

			String actbankRecOutDebits=bankRecOutDebits.getText();
		    String expbankRecOutDebits="1,340.72 Dr";
		
			String actbankRecOutCredits=bankRecOutCredits.getText();
		    String expbankRecOutCredits="2,906.13 Cr";
		
			String actbankRecClearedBal=bankRecClearedBal.getText();
		    String expbankRecClearedBal="0.00";
		
			String actbankRecOpenBal=bankRecOpenBal.getText();
		    String expbankRecOpenBal="0.00";
			

			String actbankRecDebitCounts=bankRecDebitCounts.getText();
		    String expbankRecDebitCounts="2";
			
			String actbankRecCreditCounts=bankRecCreditCounts.getText();
		    String expbankRecCreditCounts="2";
		    
			String actbankRecBankBal=bankRecBankBal.getAttribute("value");
		    String expbankRecBankBal="0.00";
		    
		    String actbankRecDiff=bankRecDif.getText();
		    String expbankRecDiff="0.00";


			
			
		   System.out.println("**********************************checkBankReconciliationReport*****************************************");
		   System.out.println("BookBal             : "+actBookBal             +" Value Expected  : "+expBookBal);
	       System.out.println("bankRecOutDebits    : "+actbankRecOutDebits    +" Value Expected  : "+expbankRecOutDebits);
	       System.out.println("bankRecOutCredits   : "+actbankRecOutCredits   +" Value Expected  : "+expbankRecOutCredits);
	       System.out.println("bankRecClearedBal   : "+actbankRecClearedBal   +" Value Expected  : "+expbankRecClearedBal);
	       System.out.println("Opening Bal         : "+actbankRecOpenBal      +" Value Expected  : "+expbankRecOpenBal);
	       System.out.println("bankRecDebitCounts  : "+actbankRecDebitCounts  +" Value Expected  : "+expbankRecDebitCounts);
	       System.out.println("bankRecCreditCounts : "+actbankRecCreditCounts +" Value Expected  : "+expbankRecCreditCounts);
	       System.out.println("bankRecBankBal      : "+actbankRecBankBal      +" Value Expected  : "+expbankRecBankBal);
	       System.out.println("bankRecDifferr      : "+actbankRecDiff	      +" Value Expected  : "+expbankRecDiff);

		if(actBRSRow1List.equalsIgnoreCase(expBRSRow1List) && actBRSRow2List.equalsIgnoreCase(expBRSRow2List) && 
				actBRSRow3List.equalsIgnoreCase(expBRSRow3List) && actBRSRow4List.equalsIgnoreCase(expBRSRow4List) &&
				 actBookBal.equalsIgnoreCase(expBookBal) &&
				actbankRecOutDebits.equalsIgnoreCase(expbankRecOutDebits) && actbankRecOutCredits.equalsIgnoreCase(expbankRecOutCredits) &&
				actbankRecClearedBal.equalsIgnoreCase(expbankRecClearedBal) && actbankRecDebitCounts.equalsIgnoreCase(expbankRecDebitCounts) &&
				actbankRecOpenBal.equalsIgnoreCase(expbankRecBankBal)&&
				actbankRecCreditCounts.equalsIgnoreCase(expbankRecCreditCounts) && actbankRecBankBal.equalsIgnoreCase(expbankRecBankBal)
				&& actbankRecDiff.equalsIgnoreCase(expbankRecDiff))
		{
			return true;
		}
		else
		{
			return false;
		}
		
	
		
	}
	
	
	public boolean checkSavingReceiptsVoucherWithDiffClearanceDateSameChequeNum() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		

		Thread.sleep(2000);
		click(serachMenuTextHomePage);
		
		serachMenuTextHomePage.sendKeys("Receipts");
		Thread.sleep(2000);
		serachMenuTextHomePage.sendKeys(Keys.DOWN);
		serachMenuTextHomePage.sendKeys(Keys.ENTER);
		//click(searchMenuTextClick);
		 
		Thread.sleep(6000);
		new WebDriverWait(getDriver(), 300).until(ExpectedConditions.visibilityOf(newBtn));
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(newBtn));
		newBtn.click();
		Thread.sleep(4000);
		
		Thread.sleep(2000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNumberTxt));
		click(dateTxt);
		dateTxt.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
		
		 Calendar cal=Calendar.getInstance();
		 cal.add(Calendar.MONTH, -2);
		 cal.add(Calendar.DATE, -5);
		 SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		dateTxt.sendKeys(f.format(cal.getTime()));
		dateTxt.sendKeys(Keys.TAB);
		
		click(caskBankAccountTxt);
		caskBankAccountTxt.sendKeys("HDFC");
		Thread.sleep(2000);
		caskBankAccountTxt.sendKeys(Keys.TAB);
		
		click(maturityDateTxt);
		maturityDateTxt.sendKeys(f.format(cal.getTime()));
		maturityDateTxt.sendKeys(Keys.TAB);
		
		click(departmentTxt);
		departmentTxt.sendKeys("AMERICA");
		Thread.sleep(2000);
		departmentTxt.sendKeys(Keys.TAB);
		
		
		
		click(receipts_ChequeNoTxt);
		receipts_ChequeNoTxt.sendKeys("RP001");
		Thread.sleep(2000);
		receipts_ChequeNoTxt.sendKeys(Keys.TAB);
		
		
		
		click(select1stRow_1stColumn);
		
		click(enter_DebitACTxt);
		enter_DebitACTxt.sendKeys("Customer C");
		Thread.sleep(2000);
		enter_DebitACTxt.sendKeys(Keys.TAB);
		
		
		enter_Amount.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
		enter_Amount.sendKeys("1250.47");
		Thread.sleep(2000);
		enter_Amount.sendKeys(Keys.TAB);
		
		
		
		click(billRefNewReferenceTxt);
		click(pickBtn);
		Thread.sleep(2000);
		
		click(voucher_OkBtn);
		Thread.sleep(4000);
		
		click(saveBtn);
		Thread.sleep(2000);
		
		String expMsg="Voucher saved successfully";
		String expMsg1=": 5";
		
		String actMsg=checkValidationMessage(expMsg);
		
		System.out.println("Actual Message		"	+	actMsg		+	"Expected Message	"	+	expMsg);
		
		if(actMsg.startsWith(expMsg) && actMsg.endsWith(expMsg1))
		{
			return true;
		}
		else
		{
			return false;
		}
		

	
	}
	
	
	public boolean checkSavingPaymentsVoucherWithDiffCleranceDAteSameChequeNum() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		Thread.sleep(2000);
		click(serachMenuTextHomePage);
		
		serachMenuTextHomePage.sendKeys("Payments");
		Thread.sleep(2000);
		serachMenuTextHomePage.sendKeys(Keys.DOWN);
		serachMenuTextHomePage.sendKeys(Keys.ENTER);
		//click(searchMenuTextClick);
		 
		Thread.sleep(9000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(newBtn));
		newBtn.click();
		Thread.sleep(4000);
		
		Thread.sleep(2000);
		//getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNumberTxt));
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNumberTxt));
		

		 Calendar cal=Calendar.getInstance();
		 cal.add(Calendar.MONTH, -3);
		 cal.add(Calendar.DATE, -5);
		 SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		click(dateTxt);
		
		dateTxt.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
		dateTxt.sendKeys(f.format(cal.getTime()));
		dateTxt.sendKeys(Keys.TAB);

		click(caskBankAccountTxt);
		caskBankAccountTxt.sendKeys("HDFC");
		Thread.sleep(2000);
		caskBankAccountTxt.sendKeys(Keys.TAB);
		
		click(maturityDateTxt);
		maturityDateTxt.sendKeys(f.format(cal.getTime()));
		maturityDateTxt.sendKeys(Keys.TAB);
		
				
		click(departmentTxt);
		departmentTxt.sendKeys("AMERICA");
		Thread.sleep(2000);
		departmentTxt.sendKeys(Keys.TAB);
		
		
		
		click(payments_ChequeNoTxt);
		payments_ChequeNoTxt.sendKeys("RP001");
		Thread.sleep(2000);
		payments_ChequeNoTxt.sendKeys(Keys.TAB);
		
		
		
		click(select1stRow_1stColumn);
		
		click(enter_DebitACTxt);
		enter_DebitACTxt.sendKeys("Vendor B");
		Thread.sleep(2000);
		enter_DebitACTxt.sendKeys(Keys.TAB);
		
		
		enter_Amount.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
		enter_Amount.sendKeys("3807.81");
		Thread.sleep(2000);
		enter_Amount.sendKeys(Keys.TAB);
		
		
		click(billRefNewReferenceTxt);
		click(pickBtn);
		Thread.sleep(2000);
		
		click(voucher_OkBtn);
		Thread.sleep(4000);
		
		click(saveBtn);
		Thread.sleep(2000);
		
		String expMsg="Voucher saved successfully";
		String expMsg1=": 6";
		
		String actMsg=checkValidationMessage(expMsg);
		
		System.out.println("Actual Message		"	+	actMsg		+	"Expected Message	"	+	expMsg);
		
		if(actMsg.startsWith(expMsg) && actMsg.endsWith(expMsg1))
		{
			return true;
		}
		else
		{
			return false;
		}
		

	
	}
	
	
	
	public boolean checkBankReconciliationReportforDiffCleranceDateSameChequeNum() throws InterruptedException
	{
		
		click(serachMenuTextHomePage);
		
		serachMenuTextHomePage.sendKeys("Bank Reconciliation");
		Thread.sleep(2000);
		serachMenuTextHomePage.sendKeys(Keys.ENTER);
		
		Thread.sleep(3500);
		
		click(reportbankTxt);
		reportbankTxt.sendKeys("HDFC");
		Thread.sleep(1500);
		reportbankTxt.sendKeys(Keys.TAB);
		
		//click(BRSortDrpdwn);
		Select s=new Select(BRSortDrpdwn);
		s.selectByValue("1");
		Thread.sleep(2000);
		BRSortDrpdwn.sendKeys(Keys.TAB);
		
		//click(BRSSelectStatusDrpdwn);
		Select s1=new Select(BRSSelectStatusDrpdwn);
		s1.selectByValue("2");
		Thread.sleep(3000);
		BRSSelectStatusDrpdwn.sendKeys(Keys.TAB);
		
		//click(BRSSelectCRDRDrpdwn);
		Select s2=new Select(BRSSelectCRDRDrpdwn);
		s2.selectByValue("2");
		Thread.sleep(3000);
		BRSSelectCRDRDrpdwn.sendKeys(Keys.TAB);
		
		click(LoadBtn);
		Thread.sleep(8000);
		
		 Calendar cal=Calendar.getInstance();
		 cal.add(Calendar.MONTH, -2);
		 cal.add(Calendar.DATE, -5);
		 SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		 
		ArrayList<String>brsRow1ArrayList=new ArrayList<String>();
		for(int i=0;i<bankRecRow1List.size();i++)
		{
			brsRow1ArrayList.add(bankRecRow1List.get(i).getText());
		}
		
		String actBRSRow1List=brsRow1ArrayList.toString();
		String expBRSRow1List="[1, Pending, RP001, "+f.format(cal.getTime())+", Rct:5, "+f.format(cal.getTime())+", 1,250.47, 0.00, Receipts, , , , ]";
		
		System.out.println("Actual Row1 List	"	+	actBRSRow1List);
		System.out.println("Expect Row1 List	"	+	expBRSRow1List);
		
		
		 Calendar cal1=Calendar.getInstance();
		 cal1.add(Calendar.MONTH, -1);
		 cal1.add(Calendar.DATE, -5);
		 SimpleDateFormat f1 = new SimpleDateFormat("dd/MM/yyyy");
		
		ArrayList<String>brsRow2ArrayList=new ArrayList<String>();
		for(int i=0;i<bankRecRow2List.size();i++)
		{
			brsRow2ArrayList.add(bankRecRow2List.get(i).getText());
		}
		
		String actBRSRow2List=brsRow2ArrayList.toString();
		String expBRSRow2List="[2, Pending, Rpt-001, "+f1.format(cal1.getTime())+", Rct:4, "+f1.format(cal1.getTime())+", 1,085.03, 0.00, Receipts, , , , ]";
		
		System.out.println("Actual Row2 List	"	+	actBRSRow2List);
		System.out.println("Expect Row2 List	"	+	expBRSRow2List);
		
		ArrayList<String>brsRow3ArrayList=new ArrayList<String>();
		for(int i=0;i<bankRecRow3List.size();i++)
		{
			brsRow3ArrayList.add(bankRecRow3List.get(i).getText());
		}
		
		String actBRSRow3List=brsRow3ArrayList.toString();
		String expBRSRow3List="[3, Pending, , "+getCurrentDate()+", Rct:3, "+getCurrentDate()+", 255.69, 0.00, Receipts, , , , ]";
		
		System.out.println("Actual Row3 List	"	+	actBRSRow3List);
		System.out.println("Expect Row3 List	"	+	expBRSRow3List);
		 Calendar cal2=Calendar.getInstance();
		 cal2.add(Calendar.MONTH, -3);
		 cal2.add(Calendar.DATE, -5);
		 SimpleDateFormat f2 = new SimpleDateFormat("dd/MM/yyyy");
		
		ArrayList<String>brsRow4ArrayList=new ArrayList<String>();
		for(int i=0;i<bankRecRow4List.size();i++)
		{
			brsRow4ArrayList.add(bankRecRow4List.get(i).getText());
		}
		
		String actBRSRow4List=brsRow4ArrayList.toString();
		String expBRSRow4List="[4, Pending, RP001, "+f2.format(cal2.getTime())+", Pmt:6, "+f2.format(cal2.getTime())+", 0.00, 3,807.81, Payments, , , , ]";
		
		System.out.println("Actual Row4 List	"	+	actBRSRow4List);
		System.out.println("Expect Row4 List	"	+	expBRSRow4List);
		
		ArrayList<String>brsRow5ArrayList=new ArrayList<String>();
		for(int i=0;i<bankRecRow5List.size();i++)
		{
			brsRow5ArrayList.add(bankRecRow5List.get(i).getText());
		}
		
		String actBRSRow5List=brsRow5ArrayList.toString();
		String expBRSRow5List="[5, Pending, pmt-001, "+f1.format(cal1.getTime())+", Pmt:5, "+f1.format(cal1.getTime())+", 0.00, 1,005.28, Payments, , , , ]";
		
		System.out.println("Actual Row5 List	"	+	actBRSRow5List);
		System.out.println("Expect Row5 List	"	+	expBRSRow5List);
		
		
		ArrayList<String>brsRow6ArrayList=new ArrayList<String>();
		for(int i=0;i<bankRecRow6List.size();i++)
		{
			brsRow6ArrayList.add(bankRecRow6List.get(i).getText());
		}
		
		String actBRSRow6List=brsRow6ArrayList.toString();
		String expBRSRow6List="[6, Pending, , "+getCurrentDate()+", Pmt:4, "+getCurrentDate()+", 0.00, 1,900.85, Payments, , , , ]";
		
		System.out.println("Actual Row6 List	"	+	actBRSRow6List);
		System.out.println("Expect Row6 List	"	+	expBRSRow6List);
		
		
		   String actBookBal=bankRecBookBal.getText();
		   String expBookBal="4,122.75 Cr";

			String actbankRecOutDebits=bankRecOutDebits.getText();
		    String expbankRecOutDebits="2,591.19 Dr";
		
			String actbankRecOutCredits=bankRecOutCredits.getText();
		    String expbankRecOutCredits="6,713.94 Cr";
		
			String actbankRecClearedBal=bankRecClearedBal.getText();
		    String expbankRecClearedBal="0.00";
		
			String actbankRecOpenBal=bankRecOpenBal.getText();
		    String expbankRecOpenBal="0.00";
			

			String actbankRecDebitCounts=bankRecDebitCounts.getText();
		    String expbankRecDebitCounts="3";
			
			String actbankRecCreditCounts=bankRecCreditCounts.getText();
		    String expbankRecCreditCounts="3";
		    
			String actbankRecBankBal=bankRecBankBal.getAttribute("value");
		    String expbankRecBankBal="0.00";
			
		    String actbankRecDiff=bankRecDif.getText();
		    String expbankRecDiff="0.00";


			
		   System.out.println("**********************************checkBankReconciliationReport*****************************************");
		   System.out.println("BookBal             : "+actBookBal             +" Value Expected  : "+expBookBal);
	       System.out.println("bankRecOutDebits    : "+actbankRecOutDebits    +" Value Expected  : "+expbankRecOutDebits);
	       System.out.println("bankRecOutCredits   : "+actbankRecOutCredits   +" Value Expected  : "+expbankRecOutCredits);
	       System.out.println("bankRecClearedBal   : "+actbankRecClearedBal   +" Value Expected  : "+expbankRecClearedBal);
	       System.out.println("Opening Bal         : "+actbankRecOpenBal      +" Value Expected  : "+expbankRecOpenBal);
	       System.out.println("bankRecDebitCounts  : "+actbankRecDebitCounts  +" Value Expected  : "+expbankRecDebitCounts);
	       System.out.println("bankRecCreditCounts : "+actbankRecCreditCounts +" Value Expected  : "+expbankRecCreditCounts);
	       System.out.println("bankRecBankBal      : "+actbankRecBankBal      +" Value Expected  : "+expbankRecBankBal);
	       System.out.println("bankRecDifferr      : "+actbankRecDiff	      +" Value Expected  : "+expbankRecDiff);
 
		if(actBRSRow1List.equalsIgnoreCase(expBRSRow1List) && actBRSRow2List.equalsIgnoreCase(expBRSRow2List) && 
				actBRSRow3List.equalsIgnoreCase(expBRSRow3List) && actBRSRow4List.equalsIgnoreCase(expBRSRow4List) &&
				actBRSRow5List.equalsIgnoreCase(expBRSRow5List) && actBRSRow6List.equalsIgnoreCase(expBRSRow6List) &&
				 actBookBal.equalsIgnoreCase(expBookBal) &&
				actbankRecOutDebits.equalsIgnoreCase(expbankRecOutDebits) && actbankRecOutCredits.equalsIgnoreCase(expbankRecOutCredits) &&
				actbankRecClearedBal.equalsIgnoreCase(expbankRecClearedBal) && actbankRecDebitCounts.equalsIgnoreCase(expbankRecDebitCounts) &&
				actbankRecOpenBal.equalsIgnoreCase(expbankRecBankBal)&&
				actbankRecCreditCounts.equalsIgnoreCase(expbankRecCreditCounts) && actbankRecBankBal.equalsIgnoreCase(expbankRecBankBal)
				&& actbankRecDiff.equalsIgnoreCase(expbankRecDiff))
		{
			return true;
		}
		else
		{
			return false;
		}
	
	}
	
	
	
	public boolean checkSavingReceiptsVoucherWithDiffClearanceDateDiffChequeNumSameDoc() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		

		Thread.sleep(2000);
		click(serachMenuTextHomePage);
		
		serachMenuTextHomePage.sendKeys("Receipts");
		Thread.sleep(2000);
		serachMenuTextHomePage.sendKeys(Keys.DOWN);
		serachMenuTextHomePage.sendKeys(Keys.ENTER);
		//click(searchMenuTextClick);
		 
		Thread.sleep(6000);
		new WebDriverWait(getDriver(), 300).until(ExpectedConditions.visibilityOf(newBtn));
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(newBtn));
		newBtn.click();
		Thread.sleep(4000);
		
		Thread.sleep(2000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNumberTxt));
		click(dateTxt);
		dateTxt.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
		
		 Calendar cal=Calendar.getInstance();
		 cal.add(Calendar.MONTH, -2);
		 cal.add(Calendar.DATE, -12);
		 SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		dateTxt.sendKeys(f.format(cal.getTime()));
		dateTxt.sendKeys(Keys.TAB);
		
		click(caskBankAccountTxt);
		caskBankAccountTxt.sendKeys("HDFC");
		Thread.sleep(2000);
		caskBankAccountTxt.sendKeys(Keys.TAB);
		
		click(maturityDateTxt);
		maturityDateTxt.sendKeys(f.format(cal.getTime()));
		maturityDateTxt.sendKeys(Keys.TAB);
		
		click(departmentTxt);
		departmentTxt.sendKeys("AMERICA");
		Thread.sleep(2000);
		departmentTxt.sendKeys(Keys.TAB);
		
		
		
		click(receipts_ChequeNoTxt);
		receipts_ChequeNoTxt.sendKeys("Rct002");
		Thread.sleep(2000);
		receipts_ChequeNoTxt.sendKeys(Keys.TAB);
		
		
		
		click(select1stRow_1stColumn);
		
		click(enter_DebitACTxt);
		enter_DebitACTxt.sendKeys("Customer C");
		Thread.sleep(2000);
		enter_DebitACTxt.sendKeys(Keys.TAB);
		
		
		enter_Amount.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
		enter_Amount.sendKeys("1250.47");
		Thread.sleep(2000);
		enter_Amount.sendKeys(Keys.TAB);
		
		
		
		click(billRefNewReferenceTxt);
		click(pickBtn);
		Thread.sleep(2000);
		
		click(voucher_OkBtn);
		Thread.sleep(4000);
		
		click(saveBtn);
		Thread.sleep(2000);
		
		String expMsg="Voucher saved successfully";
		String expMsg1=": 6";
		
		String actMsg=checkValidationMessage(expMsg);
		
		System.out.println("Actual Message		"	+	actMsg		+	"Expected Message	"	+	expMsg);
		
		//SAving Another voucher
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNumberTxt));
		click(dateTxt);
		dateTxt.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
		
		 Calendar cal1=Calendar.getInstance();
		 cal1.add(Calendar.MONTH, -2);
		 cal1.add(Calendar.DATE, -22);
		 SimpleDateFormat f1 = new SimpleDateFormat("dd/MM/yyyy");
		dateTxt.sendKeys(f1.format(cal1.getTime()));
		dateTxt.sendKeys(Keys.TAB);
		
		click(caskBankAccountTxt);
		caskBankAccountTxt.sendKeys("HDFC");
		Thread.sleep(2000);
		caskBankAccountTxt.sendKeys(Keys.TAB);
		
		click(maturityDateTxt);
		maturityDateTxt.sendKeys(f1.format(cal1.getTime()));
		maturityDateTxt.sendKeys(Keys.TAB);
		
		click(departmentTxt);
		departmentTxt.sendKeys("AMERICA");
		Thread.sleep(2000);
		departmentTxt.sendKeys(Keys.TAB);
		
		
		
		click(receipts_ChequeNoTxt);
		receipts_ChequeNoTxt.sendKeys("Rct003");
		Thread.sleep(2000);
		receipts_ChequeNoTxt.sendKeys(Keys.TAB);
		
		
		
		click(select1stRow_1stColumn);
		
		click(enter_DebitACTxt);
		enter_DebitACTxt.sendKeys("Customer B");
		Thread.sleep(2000);
		enter_DebitACTxt.sendKeys(Keys.TAB);
		
		
		enter_Amount.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
		enter_Amount.sendKeys("673.09");
		Thread.sleep(2000);
		enter_Amount.sendKeys(Keys.TAB);
		
		
		
		click(billRefNewReferenceTxt);
		click(pickBtn);
		Thread.sleep(2000);
		
		click(voucher_OkBtn);
		Thread.sleep(4000);
		
		click(saveBtn);
		Thread.sleep(2000);
		
		String expMsg2="Voucher saved successfully";
		String expMsg3=": 7";
		
		String actMsg1=checkValidationMessage(expMsg2);
		
		System.out.println("Actual Message		"	+	actMsg1		+	"Expected Message	"	+	expMsg2);
		
		
		if(actMsg.startsWith(expMsg) && actMsg.endsWith(expMsg1))
		{
			return true;
		}
		else
		{
			return false;
		}
		

	
	}
	
	
	public boolean checkSavingPaymentsVoucherWithDiffCleranceDateDiffChequeNumSameDoc() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		Thread.sleep(2000);
		click(serachMenuTextHomePage);
		
		serachMenuTextHomePage.sendKeys("Payments");
		Thread.sleep(2000);
		serachMenuTextHomePage.sendKeys(Keys.DOWN);
		serachMenuTextHomePage.sendKeys(Keys.ENTER);
		//click(searchMenuTextClick);
		 
		Thread.sleep(6000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(newBtn));
		newBtn.click();
		Thread.sleep(4000);
		
		Thread.sleep(2000);
		//getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNumberTxt));
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNumberTxt));
		

		 Calendar cal=Calendar.getInstance();
		 cal.add(Calendar.MONTH, -3);
		 cal.add(Calendar.DATE, -15);
		 SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		click(dateTxt);
		
		dateTxt.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
		dateTxt.sendKeys(f.format(cal.getTime()));
		dateTxt.sendKeys(Keys.TAB);

		click(caskBankAccountTxt);
		caskBankAccountTxt.sendKeys("HDFC");
		Thread.sleep(2000);
		caskBankAccountTxt.sendKeys(Keys.TAB);
		
		click(maturityDateTxt);
		maturityDateTxt.sendKeys(f.format(cal.getTime()));
		maturityDateTxt.sendKeys(Keys.TAB);
		
				
		click(departmentTxt);
		departmentTxt.sendKeys("AMERICA");
		Thread.sleep(2000);
		departmentTxt.sendKeys(Keys.TAB);
		
		
		
		click(payments_ChequeNoTxt);
		payments_ChequeNoTxt.sendKeys("Pmt002");
		Thread.sleep(2000);
		payments_ChequeNoTxt.sendKeys(Keys.TAB);
		
		
		
		click(select1stRow_1stColumn);
		
		click(enter_DebitACTxt);
		enter_DebitACTxt.sendKeys("Vendor B");
		Thread.sleep(2000);
		enter_DebitACTxt.sendKeys(Keys.TAB);
		
		
		enter_Amount.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
		enter_Amount.sendKeys("2514.03");
		Thread.sleep(2000);
		enter_Amount.sendKeys(Keys.TAB);
		
		
		click(billRefNewReferenceTxt);
		click(pickBtn);
		Thread.sleep(2000);
		
		click(voucher_OkBtn);
		Thread.sleep(4000);
		
		click(saveBtn);
		Thread.sleep(2000);
		
		String expMsg="Voucher saved successfully";
		String expMsg1=": 7";
		
		String actMsg=checkValidationMessage(expMsg);
		
		System.out.println("Actual Message		"	+	actMsg		+	"Expected Message	"	+	expMsg);
		
		///Saving another Vocuher
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNumberTxt));
		

		 Calendar cal1=Calendar.getInstance();
		 cal1.add(Calendar.MONTH, -3);
		 cal1.add(Calendar.DATE, -8);
		 SimpleDateFormat f1 = new SimpleDateFormat("dd/MM/yyyy");
		click(dateTxt);
		
		dateTxt.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
		dateTxt.sendKeys(f1.format(cal1.getTime()));
		dateTxt.sendKeys(Keys.TAB);

		click(caskBankAccountTxt);
		caskBankAccountTxt.sendKeys("HDFC");
		Thread.sleep(2000);
		caskBankAccountTxt.sendKeys(Keys.TAB);
		
		click(maturityDateTxt);
		maturityDateTxt.sendKeys(f1.format(cal1.getTime()));
		maturityDateTxt.sendKeys(Keys.TAB);
		
				
		click(departmentTxt);
		departmentTxt.sendKeys("AMERICA");
		Thread.sleep(2000);
		departmentTxt.sendKeys(Keys.TAB);
		
		
		
		click(payments_ChequeNoTxt);
		payments_ChequeNoTxt.sendKeys("Pmt003");
		Thread.sleep(2000);
		payments_ChequeNoTxt.sendKeys(Keys.TAB);
		
		
		
		click(select1stRow_1stColumn);
		
		click(enter_DebitACTxt);
		enter_DebitACTxt.sendKeys("Vendor A");
		Thread.sleep(2000);
		enter_DebitACTxt.sendKeys(Keys.TAB);
		
		
		enter_Amount.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
		enter_Amount.sendKeys("354.12");
		Thread.sleep(2000);
		enter_Amount.sendKeys(Keys.TAB);
		
		
		click(billRefNewReferenceTxt);
		click(pickBtn);
		Thread.sleep(2000);
		
		click(voucher_OkBtn);
		Thread.sleep(4000);
		
		click(saveBtn);
		Thread.sleep(2000);
		
		String expMsg2="Voucher saved successfully";
		String expMsg3=": 8";
		
		String actMsg1=checkValidationMessage(expMsg2);
		
		System.out.println("Actual Message		"	+	actMsg		+	"Expected Message	"	+	expMsg2);
		
		if(actMsg.startsWith(expMsg) && actMsg.endsWith(expMsg1))
		{
			return true;
		}
		else
		{
			return false;
		}
		

	
	}
	
	
	
	public boolean checkBankReconciliationReportforDiffCleranceDateDiffChequeNumSameDoc() throws InterruptedException
	{
		
		click(serachMenuTextHomePage);
		
		serachMenuTextHomePage.sendKeys("Bank Reconciliation");
		Thread.sleep(2000);
		serachMenuTextHomePage.sendKeys(Keys.ENTER);
		
		Thread.sleep(3500);
		
		click(reportbankTxt);
		reportbankTxt.sendKeys("HDFC");
		Thread.sleep(1500);
		reportbankTxt.sendKeys(Keys.TAB);
		
		//click(BRSortDrpdwn);
		Select s=new Select(BRSortDrpdwn);
		s.selectByValue("1");
		Thread.sleep(2000);
		BRSortDrpdwn.sendKeys(Keys.TAB);
		
		//click(BRSSelectStatusDrpdwn);
		Select s1=new Select(BRSSelectStatusDrpdwn);
		s1.selectByValue("2");
		Thread.sleep(3000);
		BRSSelectStatusDrpdwn.sendKeys(Keys.TAB);
		
		//click(BRSSelectCRDRDrpdwn);
		Select s2=new Select(BRSSelectCRDRDrpdwn);
		s2.selectByValue("2");
		Thread.sleep(3000);
		BRSSelectCRDRDrpdwn.sendKeys(Keys.TAB);
		
		click(LoadBtn);
		Thread.sleep(10000);
		
		
		Calendar cal1=Calendar.getInstance();
		 cal1.add(Calendar.MONTH, -2);
		 cal1.add(Calendar.DATE, -22);
		 SimpleDateFormat f1 = new SimpleDateFormat("dd/MM/yyyy");

		
		ArrayList<String>brsRow1ArrayList=new ArrayList<String>();
		for(int i=0;i<bankRecRow1List.size();i++)
		{
			brsRow1ArrayList.add(bankRecRow1List.get(i).getText());
		}
		
		String actBRSRow1List=brsRow1ArrayList.toString();
		String expBRSRow1List="[1, Pending, Rct003, "+f1.format(cal1.getTime())+", Rct:7, "+f1.format(cal1.getTime())+", 673.09, 0.00, Receipts, , , , ]";
		
		System.out.println("Actual Row1 List	"	+	actBRSRow1List);
		System.out.println("Expect Row1 List	"	+	expBRSRow1List);
		
		 Calendar cal=Calendar.getInstance();
		 cal.add(Calendar.MONTH, -2);
		 cal.add(Calendar.DATE, -12);
		 SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");


		
		ArrayList<String>brsRow2ArrayList=new ArrayList<String>();
		for(int i=0;i<bankRecRow2List.size();i++)
		{
			brsRow2ArrayList.add(bankRecRow2List.get(i).getText());
		}
		
		String actBRSRow2List=brsRow2ArrayList.toString();
		String expBRSRow2List="[2, Pending, Rct002, "+f.format(cal.getTime())+", Rct:6, "+f.format(cal.getTime())+", 1,250.47, 0.00, Receipts, , , , ]";
		
		System.out.println("Actual Row2 List	"	+	actBRSRow2List);
		System.out.println("Expect Row2 List	"	+	expBRSRow2List);
		
		Calendar cal2=Calendar.getInstance();
		 cal2.add(Calendar.MONTH, -2);
		 cal2.add(Calendar.DATE, -5);
		 SimpleDateFormat f2 = new SimpleDateFormat("dd/MM/yyyy");

		
		ArrayList<String>brsRow3ArrayList=new ArrayList<String>();
		for(int i=0;i<bankRecRow3List.size();i++)
		{
			brsRow3ArrayList.add(bankRecRow3List.get(i).getText());
		}
		
		String actBRSRow3List=brsRow3ArrayList.toString();
		String expBRSRow3List="[3, Pending, RP001, "+f2.format(cal2.getTime())+", Rct:5, "+f2.format(cal2.getTime())+", 1,250.47, 0.00, Receipts, , , , ]";
		
		System.out.println("Actual Row3 List	"	+	actBRSRow3List);
		System.out.println("Expect Row3 List	"	+	expBRSRow3List);
		
		
		Calendar cal3=Calendar.getInstance();
		cal3.add(Calendar.MONTH, -1);
		 cal3.add(Calendar.DAY_OF_MONTH, -5);
		 SimpleDateFormat f3= new SimpleDateFormat("dd/MM/yyyy");

		
		ArrayList<String>brsRow4ArrayList=new ArrayList<String>();
		for(int i=0;i<bankRecRow4List.size();i++)
		{
			brsRow4ArrayList.add(bankRecRow4List.get(i).getText());
		}
		
		String actBRSRow4List=brsRow4ArrayList.toString();
		String expBRSRow4List="[4, Pending, Rpt-001, "+f3.format(cal3.getTime())+", Rct:4, "+f3.format(cal3.getTime())+", 1,085.03, 0.00, Receipts, , , , ]";
		
		System.out.println("Actual Row4 List	"	+	actBRSRow4List);
		System.out.println("Expect Row4 List	"	+	expBRSRow4List);
		
		ArrayList<String>brsRow5ArrayList=new ArrayList<String>();
		for(int i=0;i<bankRecRow5List.size();i++)
		{
			brsRow5ArrayList.add(bankRecRow5List.get(i).getText());
		}
		
		String actBRSRow5List=brsRow5ArrayList.toString();
		String expBRSRow5List="[5, Pending, , "+getCurrentDate()+", Rct:3, "+getCurrentDate()+", 255.69, 0.00, Receipts, , , , ]";
		
		System.out.println("Actual Row5 List	"	+	actBRSRow5List);
		System.out.println("Expect Row5 List	"	+	expBRSRow5List);
		Calendar cal5=Calendar.getInstance();
		cal5.add(Calendar.MONTH, -3);
		 cal5.add(Calendar.DAY_OF_MONTH, -15);
		SimpleDateFormat f5= new SimpleDateFormat("dd/MM/yyyy");

		
		ArrayList<String>brsRow6ArrayList=new ArrayList<String>();
		for(int i=0;i<bankRecRow6List.size();i++)
		{
			brsRow6ArrayList.add(bankRecRow6List.get(i).getText());
		}
		
		String actBRSRow6List=brsRow6ArrayList.toString();
		String expBRSRow6List="[6, Pending, Pmt002, "+f5.format(cal5.getTime())+", Pmt:7, "+f5.format(cal5.getTime())+", 0.00, 2,514.03, Payments, , , , ]";
		
		System.out.println("Actual Row6 List	"	+	actBRSRow6List);
		System.out.println("Expect Row6 List	"	+	expBRSRow6List);
		
		
		Calendar cal6=Calendar.getInstance();
		cal6.add(Calendar.MONTH, -3);
		 cal6.add(Calendar.DAY_OF_MONTH, -8);
		SimpleDateFormat f6= new SimpleDateFormat("dd/MM/yyyy");
		
		
		ArrayList<String>brsRow7ArrayList=new ArrayList<String>();
		for(int i=0;i<bankRecRow7List.size();i++)
		{
			brsRow7ArrayList.add(bankRecRow7List.get(i).getText());
		}
		
		String actBRSRow7List=brsRow7ArrayList.toString();
		String expBRSRow7List="[7, Pending, Pmt003, "+f6.format(cal6.getTime())+", Pmt:8, "+f6.format(cal6.getTime())+", 0.00, 354.12, Payments, , , , ]";
		
		System.out.println("Actual Row7 List	"	+	actBRSRow7List);
		System.out.println("Expect Row7 List	"	+	expBRSRow7List);
		
		
		Calendar cal8=Calendar.getInstance();
		 cal8.add(Calendar.MONTH, -3);
		 cal8.add(Calendar.DATE, -5);
		 SimpleDateFormat f8= new SimpleDateFormat("dd/MM/yyyy");

		
		ArrayList<String>brsRow8ArrayList=new ArrayList<String>();
		for(int i=0;i<bankRecRow8List.size();i++)
		{
			brsRow8ArrayList.add(bankRecRow8List.get(i).getText());
		}
		
		String actBRSRow8List=brsRow8ArrayList.toString();
		String expBRSRow8List="[8, Pending, RP001, "+f8.format(cal8.getTime())+", Pmt:6, "+f8.format(cal8.getTime())+", 0.00, 3,807.81, Payments, , , , ]";
		
		System.out.println("Actual Row8 List	"	+	actBRSRow8List);
		System.out.println("Expect Row8 List	"	+	expBRSRow8List);
		
		ArrayList<String>brsRow9ArrayList=new ArrayList<String>();
		for(int i=0;i<bankRecRow9List.size();i++)
		{
			brsRow9ArrayList.add(bankRecRow9List.get(i).getText());
		}
		
		String actBRSRow9List=brsRow9ArrayList.toString();
		String expBRSRow9List="[9, Pending, pmt-001, "+f.format(cal3.getTime())+", Pmt:5, "+f.format(cal3.getTime())+", 0.00, 1,005.28, Payments, , , , ]";
		
		System.out.println("Actual Row9 List	"	+	actBRSRow9List);
		System.out.println("Expect Row9 List	"	+	expBRSRow9List);
		
		
		ArrayList<String>brsRow10ArrayList=new ArrayList<String>();
		for(int i=0;i<bankRecRow10List.size();i++)
		{
			brsRow10ArrayList.add(bankRecRow10List.get(i).getText());
		}
		
		String actBRSRow10List=brsRow10ArrayList.toString();
		String expBRSRow10List="[10, Pending, , "+getCurrentDate()+", Pmt:4, "+getCurrentDate()+", 0.00, 1,900.85, Payments, , , , ]";
		
		System.out.println("Actual Row10 List	"	+	actBRSRow10List);
		System.out.println("Expect Row10 List	"	+	expBRSRow10List);
		
		
		
		   String actBookBal=bankRecBookBal.getText();
		   String expBookBal="5,067.34 Cr";

			String actbankRecOutDebits=bankRecOutDebits.getText();
		    String expbankRecOutDebits="4,514.75 Dr";
		
			String actbankRecOutCredits=bankRecOutCredits.getText();
		    String expbankRecOutCredits="9,582.09 Cr";
		
			String actbankRecClearedBal=bankRecClearedBal.getText();
		    String expbankRecClearedBal="0.00";
		
			String actbankRecOpenBal=bankRecOpenBal.getText();
		    String expbankRecOpenBal="0.00";
			

			String actbankRecDebitCounts=bankRecDebitCounts.getText();
		    String expbankRecDebitCounts="5";
			
			String actbankRecCreditCounts=bankRecCreditCounts.getText();
		    String expbankRecCreditCounts="5";
		    
			String actbankRecBankBal=bankRecBankBal.getAttribute("value");
		    String expbankRecBankBal="0.00";
		    
		    String actbankRecDiff=bankRecDif.getText();
		    String expbankRecDiff="0.00";


			
			
		   System.out.println("**********************************checkBankReconciliationReport*****************************************");
		   System.out.println("BookBal             : "+actBookBal             +" Value Expected  : "+expBookBal);
	       System.out.println("bankRecOutDebits    : "+actbankRecOutDebits    +" Value Expected  : "+expbankRecOutDebits);
	       System.out.println("bankRecOutCredits   : "+actbankRecOutCredits   +" Value Expected  : "+expbankRecOutCredits);
	       System.out.println("bankRecClearedBal   : "+actbankRecClearedBal   +" Value Expected  : "+expbankRecClearedBal);
	       System.out.println("Opening Bal         : "+actbankRecOpenBal      +" Value Expected  : "+expbankRecOpenBal);
	       System.out.println("bankRecDebitCounts  : "+actbankRecDebitCounts  +" Value Expected  : "+expbankRecDebitCounts);
	       System.out.println("bankRecCreditCounts : "+actbankRecCreditCounts +" Value Expected  : "+expbankRecCreditCounts);
	       System.out.println("bankRecBankBal      : "+actbankRecBankBal      +" Value Expected  : "+expbankRecBankBal);
	       System.out.println("bankRecDifferr      : "+actbankRecDiff	      +" Value Expected  : "+expbankRecDiff);

		if(actBRSRow1List.equalsIgnoreCase(expBRSRow1List) && actBRSRow2List.equalsIgnoreCase(expBRSRow2List) && 
				actBRSRow3List.equalsIgnoreCase(expBRSRow3List) && actBRSRow4List.equalsIgnoreCase(expBRSRow4List) &&
				actBRSRow5List.equalsIgnoreCase(expBRSRow5List) && actBRSRow6List.equalsIgnoreCase(expBRSRow6List) &&
				 actBookBal.equalsIgnoreCase(expBookBal) &&
				actbankRecOutDebits.equalsIgnoreCase(expbankRecOutDebits) && actbankRecOutCredits.equalsIgnoreCase(expbankRecOutCredits) &&
				actbankRecClearedBal.equalsIgnoreCase(expbankRecClearedBal) && actbankRecDebitCounts.equalsIgnoreCase(expbankRecDebitCounts) &&
				actbankRecOpenBal.equalsIgnoreCase(expbankRecBankBal)&&
				actbankRecCreditCounts.equalsIgnoreCase(expbankRecCreditCounts) && actbankRecBankBal.equalsIgnoreCase(expbankRecBankBal) 
				&& actbankRecDiff.equalsIgnoreCase(expbankRecDiff))
		{
			return true;
		}
		else
		{
			return false;
		}
	
	}
	
	
	public boolean checkSavingReceiptsVocuherWithDiffChequeNumSameCleranceDateAndDoc() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{

		Thread.sleep(2000);
		click(serachMenuTextHomePage);
		
		serachMenuTextHomePage.sendKeys("Receipts");
		Thread.sleep(2000);
		serachMenuTextHomePage.sendKeys(Keys.DOWN);
		serachMenuTextHomePage.sendKeys(Keys.ENTER);
		//click(searchMenuTextClick);
		 
		Thread.sleep(6000);
		new WebDriverWait(getDriver(), 300).until(ExpectedConditions.visibilityOf(newBtn));
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(newBtn));
		newBtn.click();
		Thread.sleep(4000);
		
		Thread.sleep(2000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNumberTxt));
		click(dateTxt);
		dateTxt.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
		
		 Calendar cal=Calendar.getInstance();
		 cal.add(Calendar.MONTH, -1);
		 cal.add(Calendar.DATE, -12);
		 SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		dateTxt.sendKeys(f.format(cal.getTime()));
		dateTxt.sendKeys(Keys.TAB);
		
		click(caskBankAccountTxt);
		caskBankAccountTxt.sendKeys("HDFC");
		Thread.sleep(2000);
		caskBankAccountTxt.sendKeys(Keys.TAB);
		
		click(maturityDateTxt);
		maturityDateTxt.sendKeys(f.format(cal.getTime()));
		maturityDateTxt.sendKeys(Keys.TAB);
		
		click(departmentTxt);
		departmentTxt.sendKeys("AMERICA");
		Thread.sleep(2000);
		departmentTxt.sendKeys(Keys.TAB);
		
		
		
		click(receipts_ChequeNoTxt);
		receipts_ChequeNoTxt.sendKeys("Rct004");
		Thread.sleep(2000);
		receipts_ChequeNoTxt.sendKeys(Keys.TAB);
		
		
		
		click(select1stRow_1stColumn);
		
		click(enter_DebitACTxt);
		enter_DebitACTxt.sendKeys("Customer C");
		Thread.sleep(2000);
		enter_DebitACTxt.sendKeys(Keys.TAB);
		
		
		enter_Amount.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
		enter_Amount.sendKeys("865.24");
		Thread.sleep(2000);
		enter_Amount.sendKeys(Keys.TAB);
		
		
		
		click(billRefNewReferenceTxt);
		click(pickBtn);
		Thread.sleep(2000);
		
		click(voucher_OkBtn);
		Thread.sleep(4000);
		
		click(saveBtn);
		Thread.sleep(2000);
		
		String expMsg="Voucher saved successfully";
		String expMsg1=": 8";
		
		String actMsg=checkValidationMessage(expMsg);
		
		System.out.println("Actual Message		"	+	actMsg		+	"Expected Message	"	+	expMsg);
		
		//SAving Another voucher
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNumberTxt));
		click(dateTxt);
		dateTxt.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
		
		dateTxt.sendKeys(f.format(cal.getTime()));
		dateTxt.sendKeys(Keys.TAB);
		
		click(caskBankAccountTxt);
		caskBankAccountTxt.sendKeys("HDFC");
		Thread.sleep(2000);
		caskBankAccountTxt.sendKeys(Keys.TAB);
		
		click(maturityDateTxt);
		maturityDateTxt.sendKeys(f.format(cal.getTime()));
		maturityDateTxt.sendKeys(Keys.TAB);
		
		click(departmentTxt);
		departmentTxt.sendKeys("AMERICA");
		Thread.sleep(2000);
		departmentTxt.sendKeys(Keys.TAB);
		
		
		
		click(receipts_ChequeNoTxt);
		receipts_ChequeNoTxt.sendKeys("Rct006");
		Thread.sleep(2000);
		receipts_ChequeNoTxt.sendKeys(Keys.TAB);
		
		
		
		click(select1stRow_1stColumn);
		
		click(enter_DebitACTxt);
		enter_DebitACTxt.sendKeys("Customer B");
		Thread.sleep(2000);
		enter_DebitACTxt.sendKeys(Keys.TAB);
		
		
		enter_Amount.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
		enter_Amount.sendKeys("475.05");
		Thread.sleep(2000);
		enter_Amount.sendKeys(Keys.TAB);
		
		Thread.sleep(2000);
		
		click(billRefNewReferenceTxt);
		click(pickBtn);
		Thread.sleep(2000);
		
		click(voucher_OkBtn);
		Thread.sleep(4000);
		
		click(saveBtn);
		Thread.sleep(2000);
		
		String expMsg2="Voucher saved successfully";
		String expMsg3=": 9";
		
		String actMsg1=checkValidationMessage(expMsg2);
		
		System.out.println("Actual Message		"	+	actMsg1		+	"Expected Message	"	+	expMsg2);
		
		
		if(actMsg.startsWith(expMsg) && actMsg.endsWith(expMsg1))
		{
			return true;
		}
		else
		{
			return false;
		}

		
	}
	
	@FindBy(xpath="//*[@id='BRTable_body']/tr/td[2]")
	public static List<WebElement> BRS_BRSStatusList; 
	
	@FindBy(xpath="//*[@id='BRTable_body']/tr/td[3]")
	public static List<WebElement> BRS_ChequeNumList; 
	
	@FindBy(xpath="//*[@id='BRTable_body']/tr/td[4]")
	public static List<WebElement> BRS_CleranceDateColList; 
	
	@FindBy(xpath="//*[@id='BRTable_body']/tr/td[5]")
	public static List<WebElement> BRS_DocNoColList; 
	
	@FindBy(xpath="//*[@id='BRTable_body']/tr/td[7]")
	public static List<WebElement> BRS_DebitAmtColList; 
	
	@FindBy(xpath="//*[@id='BRTable_body']/tr/td[8]")
	public static List<WebElement> BRS_CreditAmtColList; 
	
	
	public boolean checkChequeNumColListinBankReconcilitionReport() throws InterruptedException
	{
		

		
		click(serachMenuTextHomePage);
		
		serachMenuTextHomePage.sendKeys("Bank Reconciliation");
		Thread.sleep(2000);
		serachMenuTextHomePage.sendKeys(Keys.ENTER);
		
		Thread.sleep(3500);
		
		click(reportbankTxt);
		reportbankTxt.sendKeys("HDFC");
		Thread.sleep(1500);
		reportbankTxt.sendKeys(Keys.TAB);
		
		//click(BRSortDrpdwn);
		Select s=new Select(BRSortDrpdwn);
		s.selectByValue("0");
		Thread.sleep(2000);
		BRSortDrpdwn.sendKeys(Keys.TAB);
		
		//click(BRSSelectStatusDrpdwn);
		Select s1=new Select(BRSSelectStatusDrpdwn);
		s1.selectByValue("2");
		Thread.sleep(3000);
		BRSSelectStatusDrpdwn.sendKeys(Keys.TAB);
		
		//click(BRSSelectCRDRDrpdwn);
		Select s2=new Select(BRSSelectCRDRDrpdwn);
		s2.selectByValue("2");
		Thread.sleep(3000);
		BRSSelectCRDRDrpdwn.sendKeys(Keys.TAB);
		
		click(LoadBtn);
		Thread.sleep(8000);
		
		ArrayList<String>brsRow1ArrayList=new ArrayList<String>();
		for(int i=0;i<BRS_ChequeNumList.size();i++)
		{
			brsRow1ArrayList.add(BRS_ChequeNumList.get(i).getText());
		}
		
		String actBeforeSortChequeList=brsRow1ArrayList.toString();
		String expBeforeSortChequeList="[, Rpt-001, RP001, Rct002, Rct003, Rct004, Rct006, , pmt-001, RP001, Pmt002, Pmt003, , , , , , , , ]";
		
		System.out.println("Actual Cheque Col List	"	+	actBeforeSortChequeList);
		System.out.println("Expect Cheque Col List	"	+	expBeforeSortChequeList);
		
		//click(BRSortDrpdwn);
		//Thread.sleep(1000);
		Select s5=new Select(BRSortDrpdwn);
		s5.selectByValue("1");
		Thread.sleep(3000);
		BRSortDrpdwn.sendKeys(Keys.TAB);
		
		click(LoadBtn);
		Thread.sleep(8000);
		
		ArrayList<String>brsRow2ArrayList=new ArrayList<String>();
		for(int i=0;i<BRS_ChequeNumList.size();i++)
		{
			brsRow2ArrayList.add(BRS_ChequeNumList.get(i).getText());
		}
		
		String actAfterSortChequeList=brsRow2ArrayList.toString();
		String expAfterSortChequeList="[Rct003, Rct002, RP001, Rct004, Rct006, Rpt-001, , Pmt002, Pmt003, RP001, pmt-001, , , , , , , , , ]";
		
		System.out.println("Actual Cheque Col List	"	+	actAfterSortChequeList);
		System.out.println("Expect Cheque Col List	"	+	expAfterSortChequeList);
		
		
		
		   String actBookBal=bankRecBookBal.getText();
		   String expBookBal="3,727.05 Cr";

			String actbankRecOutDebits=bankRecOutDebits.getText();
		    String expbankRecOutDebits="5,855.04 Dr";
		
			String actbankRecOutCredits=bankRecOutCredits.getText();
		    String expbankRecOutCredits="9,582.09 Cr";
		
			String actbankRecClearedBal=bankRecClearedBal.getText();
		    String expbankRecClearedBal="0.00";
		
			String actbankRecOpenBal=bankRecOpenBal.getText();
		    String expbankRecOpenBal="0.00";
			

			String actbankRecDebitCounts=bankRecDebitCounts.getText();
		    String expbankRecDebitCounts="7";
			
			String actbankRecCreditCounts=bankRecCreditCounts.getText();
		    String expbankRecCreditCounts="5";
		    
			String actbankRecBankBal=bankRecBankBal.getAttribute("value");
		    String expbankRecBankBal="0.00";
		    
		    String actbankRecDiff=bankRecDif.getText();
		    String expbankRecDiff="0.00";


			
			
		   System.out.println("**********************************checkBankReconciliationReport*****************************************");
		   System.out.println("BookBal             : "+actBookBal             +" Value Expected  : "+expBookBal);
	       System.out.println("bankRecOutDebits    : "+actbankRecOutDebits    +" Value Expected  : "+expbankRecOutDebits);
	       System.out.println("bankRecOutCredits   : "+actbankRecOutCredits   +" Value Expected  : "+expbankRecOutCredits);
	       System.out.println("bankRecClearedBal   : "+actbankRecClearedBal   +" Value Expected  : "+expbankRecClearedBal);
	       System.out.println("Opening Bal         : "+actbankRecOpenBal      +" Value Expected  : "+expbankRecOpenBal);
	       System.out.println("bankRecDebitCounts  : "+actbankRecDebitCounts  +" Value Expected  : "+expbankRecDebitCounts);
	       System.out.println("bankRecCreditCounts : "+actbankRecCreditCounts +" Value Expected  : "+expbankRecCreditCounts);
	       System.out.println("bankRecBankBal      : "+actbankRecBankBal      +" Value Expected  : "+expbankRecBankBal);
	       System.out.println("bankRecDifferr      : "+actbankRecDiff	      +" Value Expected  : "+expbankRecDiff);
 
		if(actBeforeSortChequeList.equalsIgnoreCase(expBeforeSortChequeList) && actAfterSortChequeList.equalsIgnoreCase(expAfterSortChequeList)&&
				 actBookBal.equalsIgnoreCase(expBookBal) &&
				actbankRecOutDebits.equalsIgnoreCase(expbankRecOutDebits) && actbankRecOutCredits.equalsIgnoreCase(expbankRecOutCredits) &&
				actbankRecClearedBal.equalsIgnoreCase(expbankRecClearedBal) && actbankRecDebitCounts.equalsIgnoreCase(expbankRecDebitCounts) &&
				actbankRecOpenBal.equalsIgnoreCase(expbankRecBankBal)&&
				actbankRecCreditCounts.equalsIgnoreCase(expbankRecCreditCounts) && actbankRecBankBal.equalsIgnoreCase(expbankRecBankBal)
				&& actbankRecDiff.equalsIgnoreCase(expbankRecDiff))
		{
			return true;
		}
		else
		{
			return false;
		}
	
	}
	
	
	@FindBy(xpath="//*[@id='id_transactionentry_new']//span")
	public static WebElement PaymentsNew_NewBtn;
	
	@FindBy(xpath="//*[@id='id_transactionentry_headertab_listelement1']")
	public static WebElement PaymentsNew_Main;
	
	public boolean checkSavingPaymentsVoucherWithDiffCleranceDateSameChequeNum() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		

		Thread.sleep(2000);
		click(serachMenuTextHomePage);
		
		serachMenuTextHomePage.sendKeys("Payments");
		Thread.sleep(2000);
		serachMenuTextHomePage.sendKeys(Keys.DOWN);
		serachMenuTextHomePage.sendKeys(Keys.ENTER);
		//click(searchMenuTextClick);
		 
		Thread.sleep(6000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(newBtn));
		newBtn.click();
		Thread.sleep(4000);
		
		Thread.sleep(2000);
		//getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNumberTxt));
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNumberTxt));
		

		 Calendar cal=Calendar.getInstance();
		 cal.add(Calendar.MONTH, -1);
		 cal.add(Calendar.DATE, -15);
		 SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		click(dateTxt);
		
		dateTxt.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
		dateTxt.sendKeys(f.format(cal.getTime()));
		dateTxt.sendKeys(Keys.TAB);

		click(caskBankAccountTxt);
		caskBankAccountTxt.sendKeys("HDFC");
		Thread.sleep(2000);
		caskBankAccountTxt.sendKeys(Keys.TAB);
		
		click(maturityDateTxt);
		maturityDateTxt.sendKeys(f.format(cal.getTime()));
		maturityDateTxt.sendKeys(Keys.TAB);
		
				
		click(departmentTxt);
		departmentTxt.sendKeys("AMERICA");
		Thread.sleep(2000);
		departmentTxt.sendKeys(Keys.TAB);
		
		
		
		click(payments_ChequeNoTxt);
		payments_ChequeNoTxt.sendKeys("Pmt010");
		Thread.sleep(2000);
		payments_ChequeNoTxt.sendKeys(Keys.TAB);
		
		
		
		click(select1stRow_1stColumn);
		
		click(enter_DebitACTxt);
		enter_DebitACTxt.sendKeys("Vendor B");
		Thread.sleep(2000);
		enter_DebitACTxt.sendKeys(Keys.TAB);
		
		
		enter_Amount.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
		enter_Amount.sendKeys("678.58");
		Thread.sleep(2000);
		enter_Amount.sendKeys(Keys.TAB);
		
		
		click(billRefNewReferenceTxt);
		click(pickBtn);
		Thread.sleep(2000);
		
		click(voucher_OkBtn);
		Thread.sleep(4000);
		
		
		click(select2ndRow_1stColumn);
		
		click(enter_DebitACTxt);
		enter_DebitACTxt.sendKeys("Vendor A");
		Thread.sleep(2000);
		enter_DebitACTxt.sendKeys(Keys.TAB);
		
		
		enter_Amount.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
		enter_Amount.sendKeys("788.33");
		Thread.sleep(2000);
		enter_Amount.sendKeys(Keys.TAB);
		
		
		click(billRefNewReferenceTxt);
		click(pickBtn);
		Thread.sleep(2000);
		
		click(voucher_OkBtn);
		Thread.sleep(4000);
		
		click(saveBtn);
		Thread.sleep(2000);
		
		String expMsg="Voucher saved successfully";
		String expMsg1=": 9";
		
		String actMsg=checkValidationMessage(expMsg);
		
		System.out.println("Actual Message		"	+	actMsg		+	"Expected Message	"	+	expMsg);
		
		///Saving another Vocuher
		
		Thread.sleep(4000);
		
		getAction().moveToElement(PaymentsNew_Main).build().perform();
		Thread.sleep(2000);
		click(PaymentsNew_NewBtn);
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNumberTxt));
		documentNumberTxt.click();
		documentNumberTxt.sendKeys(Keys.TAB);

		 Calendar cal1=Calendar.getInstance();
		 cal1.add(Calendar.MONTH, -1);
		 cal1.add(Calendar.DATE, -8);
		 SimpleDateFormat f1 = new SimpleDateFormat("dd/MM/yyyy");
		click(dateTxt);
		
		dateTxt.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
		dateTxt.sendKeys(f1.format(cal1.getTime()));
		dateTxt.sendKeys(Keys.TAB);

		click(caskBankAccountTxt);
		caskBankAccountTxt.sendKeys("HDFC");
		Thread.sleep(2000);
		caskBankAccountTxt.sendKeys(Keys.TAB);
		
		click(maturityDateTxt);
		maturityDateTxt.sendKeys(f1.format(cal1.getTime()));
		maturityDateTxt.sendKeys(Keys.TAB);
		
				
		click(departmentTxt);
		departmentTxt.sendKeys("AMERICA");
		Thread.sleep(2000);
		departmentTxt.sendKeys(Keys.TAB);
		
		
		
		click(payments_ChequeNoTxt);
		payments_ChequeNoTxt.sendKeys("Pmt010");
		Thread.sleep(2000);
		payments_ChequeNoTxt.sendKeys(Keys.TAB);
		
		
		
		click(select1stRow_1stColumn);
		
		click(enter_DebitACTxt);
		enter_DebitACTxt.sendKeys("Vendor A");
		Thread.sleep(2000);
		enter_DebitACTxt.sendKeys(Keys.TAB);
		
		
		enter_Amount.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
		enter_Amount.sendKeys("1100.25");
		Thread.sleep(2000);
		enter_Amount.sendKeys(Keys.TAB);
		
		
		click(billRefNewReferenceTxt);
		click(pickBtn);
		Thread.sleep(2000);
		
		click(voucher_OkBtn);
		Thread.sleep(4000);
		
		
		click(select2ndRow_1stColumn);
		
		click(enter_DebitACTxt);
		enter_DebitACTxt.sendKeys("Vendor B");
		Thread.sleep(2000);
		enter_DebitACTxt.sendKeys(Keys.TAB);
		
		
		enter_Amount.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
		enter_Amount.sendKeys("123.89");
		Thread.sleep(2000);
		enter_Amount.sendKeys(Keys.TAB);
		
		
		click(billRefNewReferenceTxt);
		click(pickBtn);
		Thread.sleep(2000);
		
		click(voucher_OkBtn);
		Thread.sleep(4000);
		
		
		click(saveBtn);
		Thread.sleep(2000);
		
		String expMsg2="Voucher saved successfully";
		String expMsg3=": 10";
		
		String actMsg1=checkValidationMessage(expMsg2);
		
		System.out.println("Actual Message		"	+	actMsg1		+	"Expected Message	"	+	expMsg2);
		
		if(actMsg.startsWith(expMsg) && actMsg.endsWith(expMsg1) && actMsg1.startsWith(expMsg2) && actMsg1.endsWith(expMsg3))
		{
			return true;
		}
		else
		{
			return false;
		}
	
	}
	
	
	public boolean checkCleranceDateColinBankReconcilationReportWithDiffCleranceDateSameChequeNumSmameDoc() throws InterruptedException
	{
		click(serachMenuTextHomePage);
		
		serachMenuTextHomePage.sendKeys("Bank Reconciliation");
		Thread.sleep(2000);
		serachMenuTextHomePage.sendKeys(Keys.ENTER);
		
		Thread.sleep(3500);
		
		click(reportbankTxt);
		reportbankTxt.sendKeys("HDFC");
		Thread.sleep(1500);
		reportbankTxt.sendKeys(Keys.TAB);
		
		//click(BRSortDrpdwn);
		Select s=new Select(BRSortDrpdwn);
		s.selectByValue("0");
		Thread.sleep(2000);
		BRSortDrpdwn.sendKeys(Keys.TAB);
		
		//click(BRSSelectStatusDrpdwn);
		Select s1=new Select(BRSSelectStatusDrpdwn);
		s1.selectByValue("2");
		Thread.sleep(3000);
		BRSSelectStatusDrpdwn.sendKeys(Keys.TAB);
		
		//click(BRSSelectCRDRDrpdwn);
		Select s2=new Select(BRSSelectCRDRDrpdwn);
		s2.selectByValue("2");
		Thread.sleep(3000);
		BRSSelectCRDRDrpdwn.sendKeys(Keys.TAB);
		
		click(LoadBtn);
		Thread.sleep(8000);
		
		Calendar cal=Calendar.getInstance();
		 cal.add(Calendar.MONTH, -1);
		 cal.add(Calendar.DATE, -5);
		 SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");

		 
		 Calendar cal1=Calendar.getInstance();
		 cal1.add(Calendar.MONTH, -2);
		 cal1.add(Calendar.DATE, -5);
		 SimpleDateFormat f1 = new SimpleDateFormat("dd/MM/yyyy");
		 
		 Calendar cal2=Calendar.getInstance();
		 cal2.add(Calendar.MONTH, -2);
		 cal2.add(Calendar.DATE, -12);
		 SimpleDateFormat f2 = new SimpleDateFormat("dd/MM/yyyy");
		 
		 Calendar cal3=Calendar.getInstance();
		 cal3.add(Calendar.MONTH, -2);
		 cal3.add(Calendar.DATE, -22);
		 SimpleDateFormat f3 = new SimpleDateFormat("dd/MM/yyyy");

		 Calendar cal4=Calendar.getInstance();
		 cal4.add(Calendar.MONTH, -1);
		 cal4.add(Calendar.DATE, -12);
		 SimpleDateFormat f4 = new SimpleDateFormat("dd/MM/yyyy");

		 Calendar cal5=Calendar.getInstance();
		 cal5.add(Calendar.MONTH, -1);
		 cal5.add(Calendar.DATE, -8);
		 SimpleDateFormat f5 = new SimpleDateFormat("dd/MM/yyyy");
		 
		 Calendar cal6=Calendar.getInstance();
		 cal6.add(Calendar.MONTH, -1);
		 cal6.add(Calendar.DATE, -15);
		 SimpleDateFormat f6 = new SimpleDateFormat("dd/MM/yyyy");


		 Calendar cal7=Calendar.getInstance();
		 cal7.add(Calendar.MONTH, -3);
		 cal7.add(Calendar.DATE, -8);
		 SimpleDateFormat f7 = new SimpleDateFormat("dd/MM/yyyy");
		 
		 Calendar cal71=Calendar.getInstance();
		 cal71.add(Calendar.MONTH, -3);
		 cal71.add(Calendar.DATE, -15);
		 SimpleDateFormat f71 = new SimpleDateFormat("dd/MM/yyyy");

		 Calendar cal8=Calendar.getInstance();
		 cal8.add(Calendar.MONTH, -3);
		 cal8.add(Calendar.DATE, -15);
		 SimpleDateFormat f8 = new SimpleDateFormat("dd/MM/yyyy");
		 
		 Calendar cal9=Calendar.getInstance();
		 cal9.add(Calendar.MONTH, -3);
		 cal9.add(Calendar.DATE, -5);
		 SimpleDateFormat f9 = new SimpleDateFormat("dd/MM/yyyy");
		
		ArrayList<String>brsRow1ArrayList=new ArrayList<String>();
		for(int i=0;i<BRS_CleranceDateColList.size();i++)
		{
			brsRow1ArrayList.add(BRS_CleranceDateColList.get(i).getText());
		}
		
		String actBeforeSortChequeList=brsRow1ArrayList.toString();
		String expBeforeSortChequeList="["+getCurrentDate()+", "+f.format(cal.getTime())+", "+f1.format(cal1.getTime())+", "+f2.format(cal2.getTime())+", "+f3.format(cal3.getTime())+", "+f4.format(cal4.getTime())+", "+f4.format(cal4.getTime())+", "+f5.format(cal5.getTime())+", "+f5.format(cal5.getTime())+", "+getCurrentDate()+", "+f.format(cal.getTime())+", "+f9.format(cal9.getTime())+", "+f8.format(cal8.getTime())+", "+f7.format(cal7.getTime())+", "+f6.format(cal6.getTime())+", "+f6.format(cal6.getTime())+", , , , ]";
		
		System.out.println("Actual Cheque Col List	"	+	actBeforeSortChequeList);
		System.out.println("Expect Cheque Col List	"	+	expBeforeSortChequeList);
		
		//click(BRSortDrpdwn);
		//Thread.sleep(1000);
		Select s5=new Select(BRSortDrpdwn);
		s5.selectByValue("1");
		Thread.sleep(3000);
		BRSortDrpdwn.sendKeys(Keys.TAB);
		
		click(LoadBtn);
		Thread.sleep(8000);
		
		ArrayList<String>brsRow2ArrayList=new ArrayList<String>();
		for(int i=0;i<BRS_CleranceDateColList.size();i++)
		{
			brsRow2ArrayList.add(BRS_CleranceDateColList.get(i).getText());
		}
		
		String actAfterSortChequeList=brsRow2ArrayList.toString();
		String expAfterSortChequeList="["+f3.format(cal3.getTime())+", "+f2.format(cal2.getTime())+", "+f1.format(cal1.getTime())+", "+f4.format(cal4.getTime())+", "+f4.format(cal4.getTime())+", "+f.format(cal.getTime())+", "+getCurrentDate()+", "+f71.format(cal71.getTime())+", "+f7.format(cal7.getTime())+", "+f9.format(cal9.getTime())+", "+f6.format(cal6.getTime())+", "+f6.format(cal6.getTime())+", "+f5.format(cal5.getTime())+", "+f5.format(cal5.getTime())+", "+f.format(cal.getTime())+", "+getCurrentDate()+", , , , ]";
		
		System.out.println("Actual Cheque Col List	"	+	actAfterSortChequeList);
		System.out.println("Expect Cheque Col List	"	+	expAfterSortChequeList);
		
		
		
		   String actBookBal=bankRecBookBal.getText();
		   String expBookBal="6,418.10 Cr";

			String actbankRecOutDebits=bankRecOutDebits.getText();
		    String expbankRecOutDebits="5,855.04 Dr";
		
			String actbankRecOutCredits=bankRecOutCredits.getText();
		    String expbankRecOutCredits="12,273.14 Cr";
		
			String actbankRecClearedBal=bankRecClearedBal.getText();
		    String expbankRecClearedBal="0.00";
		
			String actbankRecOpenBal=bankRecOpenBal.getText();
		    String expbankRecOpenBal="0.00";
			

			String actbankRecDebitCounts=bankRecDebitCounts.getText();
		    String expbankRecDebitCounts="7";
			
			String actbankRecCreditCounts=bankRecCreditCounts.getText();
		    String expbankRecCreditCounts="9";
		    
			String actbankRecBankBal=bankRecBankBal.getAttribute("value");
		    String expbankRecBankBal="0.00";
			
		    String actbankRecDiff=bankRecDif.getText();
		    String expbankRecDiff="0.00";


			
		   System.out.println("**********************************checkBankReconciliationReport*****************************************");
		   System.out.println("BookBal             : "+actBookBal             +" Value Expected  : "+expBookBal);
	       System.out.println("bankRecOutDebits    : "+actbankRecOutDebits    +" Value Expected  : "+expbankRecOutDebits);
	       System.out.println("bankRecOutCredits   : "+actbankRecOutCredits   +" Value Expected  : "+expbankRecOutCredits);
	       System.out.println("bankRecClearedBal   : "+actbankRecClearedBal   +" Value Expected  : "+expbankRecClearedBal);
	       System.out.println("Opening Bal         : "+actbankRecOpenBal      +" Value Expected  : "+expbankRecOpenBal);
	       System.out.println("bankRecDebitCounts  : "+actbankRecDebitCounts  +" Value Expected  : "+expbankRecDebitCounts);
	       System.out.println("bankRecCreditCounts : "+actbankRecCreditCounts +" Value Expected  : "+expbankRecCreditCounts);
	       System.out.println("bankRecBankBal      : "+actbankRecBankBal      +" Value Expected  : "+expbankRecBankBal);
	       System.out.println("bankRecDifferr      : "+actbankRecDiff	      +" Value Expected  : "+expbankRecDiff);
 
		if(actBeforeSortChequeList.equalsIgnoreCase(expBeforeSortChequeList) && actAfterSortChequeList.equalsIgnoreCase(expAfterSortChequeList)&&
				 actBookBal.equalsIgnoreCase(expBookBal) &&
				actbankRecOutDebits.equalsIgnoreCase(expbankRecOutDebits) && actbankRecOutCredits.equalsIgnoreCase(expbankRecOutCredits) &&
				actbankRecClearedBal.equalsIgnoreCase(expbankRecClearedBal) && actbankRecDebitCounts.equalsIgnoreCase(expbankRecDebitCounts) &&
				actbankRecOpenBal.equalsIgnoreCase(expbankRecBankBal)&&
				actbankRecCreditCounts.equalsIgnoreCase(expbankRecCreditCounts) && actbankRecBankBal.equalsIgnoreCase(expbankRecBankBal)
				&& actbankRecDiff.equalsIgnoreCase(expbankRecDiff))
		{
			return true;
		}
		else
		{
			return false;
		}
	
	
	}
	
	
	public boolean checkSavingPaymentVoucherWithSameCleranceDateChequeNumDoc() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		Thread.sleep(2000);
		
		getDriver().navigate().refresh();
		Thread.sleep(4000);
		
		click(serachMenuTextHomePage);
		
		serachMenuTextHomePage.sendKeys("Payments");
		Thread.sleep(2000);
		serachMenuTextHomePage.sendKeys(Keys.DOWN);
		serachMenuTextHomePage.sendKeys(Keys.ENTER);
		//click(searchMenuTextClick);
		 
		Thread.sleep(6000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(newBtn));
		newBtn.click();
		Thread.sleep(4000);
		
		Thread.sleep(2000);
		//getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNumberTxt));
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNumberTxt));
		

		 Calendar cal=Calendar.getInstance();
		 cal.add(Calendar.MONTH, -1);
		 cal.add(Calendar.DATE, -5);
		 SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		click(dateTxt);
		
		dateTxt.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
		dateTxt.sendKeys(f.format(cal.getTime()));
		dateTxt.sendKeys(Keys.TAB);

		click(caskBankAccountTxt);
		caskBankAccountTxt.sendKeys("HDFC");
		Thread.sleep(2000);
		caskBankAccountTxt.sendKeys(Keys.TAB);
		
		click(maturityDateTxt);
		maturityDateTxt.sendKeys(f.format(cal.getTime()));
		maturityDateTxt.sendKeys(Keys.TAB);
		
				
		click(departmentTxt);
		departmentTxt.sendKeys("AMERICA");
		Thread.sleep(2000);
		departmentTxt.sendKeys(Keys.TAB);
		
		
		
		click(payments_ChequeNoTxt);
		payments_ChequeNoTxt.sendKeys("Pmt050");
		Thread.sleep(2000);
		payments_ChequeNoTxt.sendKeys(Keys.TAB);
		
		
		
		click(select1stRow_1stColumn);
		
		click(enter_DebitACTxt);
		enter_DebitACTxt.sendKeys("Vendor B");
		Thread.sleep(2000);
		enter_DebitACTxt.sendKeys(Keys.TAB);
		
		
		enter_Amount.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
		enter_Amount.sendKeys("678.58");
		Thread.sleep(2000);
		enter_Amount.sendKeys(Keys.TAB);
		
		
		click(billRefNewReferenceTxt);
		click(pickBtn);
		Thread.sleep(2000);
		
		click(voucher_OkBtn);
		Thread.sleep(4000);
		
		
		click(select2ndRow_1stColumn);
		
		click(enter_DebitACTxt);
		enter_DebitACTxt.sendKeys("Vendor A");
		Thread.sleep(2000);
		enter_DebitACTxt.sendKeys(Keys.TAB);
		
		
		enter_Amount.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
		enter_Amount.sendKeys("788.33");
		Thread.sleep(2000);
		enter_Amount.sendKeys(Keys.TAB);
		
		
		click(billRefNewReferenceTxt);
		click(pickBtn);
		Thread.sleep(2000);
		
		click(voucher_OkBtn);
		Thread.sleep(4000);
		
		click(saveBtn);
		Thread.sleep(2000);
		
		String expMsg="Voucher saved successfully";
		String expMsg1=": 11";
		
		String actMsg=checkValidationMessage(expMsg);
		
		System.out.println("Actual Message		"	+	actMsg		+	"Expected Message	"	+	expMsg);
		
		///Saving another Vocuher
		
		Thread.sleep(4000);
		
		getAction().moveToElement(PaymentsNew_Main).build().perform();
		Thread.sleep(2000);
		click(PaymentsNew_NewBtn);
		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNumberTxt));
		documentNumberTxt.click();
		documentNumberTxt.sendKeys(Keys.TAB);

		 Calendar cal1=Calendar.getInstance();
		 cal1.add(Calendar.MONTH, -1);
		 cal1.add(Calendar.DATE, -5);
		 SimpleDateFormat f1 = new SimpleDateFormat("dd/MM/yyyy");
		click(dateTxt);
		
		dateTxt.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
		dateTxt.sendKeys(f1.format(cal1.getTime()));
		dateTxt.sendKeys(Keys.TAB);

		click(caskBankAccountTxt);
		caskBankAccountTxt.sendKeys("HDFC");
		Thread.sleep(2000);
		caskBankAccountTxt.sendKeys(Keys.TAB);
		
		click(maturityDateTxt);
		maturityDateTxt.sendKeys(f1.format(cal1.getTime()));
		maturityDateTxt.sendKeys(Keys.TAB);
		
				
		click(departmentTxt);
		departmentTxt.sendKeys("AMERICA");
		Thread.sleep(2000);
		departmentTxt.sendKeys(Keys.TAB);
		
		
		
		click(payments_ChequeNoTxt);
		payments_ChequeNoTxt.sendKeys("Pmt050");
		Thread.sleep(2000);
		payments_ChequeNoTxt.sendKeys(Keys.TAB);
		
		
		
		click(select1stRow_1stColumn);
		
		click(enter_DebitACTxt);
		enter_DebitACTxt.sendKeys("Vendor A");
		Thread.sleep(2000);
		enter_DebitACTxt.sendKeys(Keys.TAB);
		
		
		enter_Amount.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
		enter_Amount.sendKeys("1003.25");
		Thread.sleep(2000);
		enter_Amount.sendKeys(Keys.TAB);
		
		
		click(billRefNewReferenceTxt);
		click(pickBtn);
		Thread.sleep(2000);
		
		click(voucher_OkBtn);
		Thread.sleep(4000);
		
		
		click(select2ndRow_1stColumn);
		
		click(enter_DebitACTxt);
		enter_DebitACTxt.sendKeys("Vendor B");
		Thread.sleep(2000);
		enter_DebitACTxt.sendKeys(Keys.TAB);
		
		
		enter_Amount.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
		enter_Amount.sendKeys("596.05");
		Thread.sleep(2000);
		enter_Amount.sendKeys(Keys.TAB);
		
		
		click(billRefNewReferenceTxt);
		click(pickBtn);
		Thread.sleep(2000);
		
		click(voucher_OkBtn);
		Thread.sleep(4000);
		
		
		click(saveBtn);
		Thread.sleep(2000);
		
		String expMsg2="Voucher saved successfully";
		String expMsg3=": 12";
		
		String actMsg1=checkValidationMessage(expMsg2);
		
		System.out.println("Actual Message		"	+	actMsg1		+	"Expected Message	"	+	expMsg2);
		
		if(actMsg.startsWith(expMsg) && actMsg.endsWith(expMsg1) && actMsg1.startsWith(expMsg2) && actMsg1.endsWith(expMsg3))
		{
			return true;
		}
		else
		{
			return false;
		}
	
	}
	
	
	public boolean checkDocumentNoColinBankReconciliationReportWithSameCleranceDateChequeNumDoc() throws InterruptedException
	{
		

		click(serachMenuTextHomePage);
		
		serachMenuTextHomePage.sendKeys("Bank Reconciliation");
		Thread.sleep(2000);
		serachMenuTextHomePage.sendKeys(Keys.ENTER);
		
		Thread.sleep(3500);
		
		click(reportbankTxt);
		reportbankTxt.sendKeys("HDFC");
		Thread.sleep(1500);
		reportbankTxt.sendKeys(Keys.TAB);
		
		//click(BRSortDrpdwn);
		Select s=new Select(BRSortDrpdwn);
		s.selectByValue("0");
		Thread.sleep(2000);
		BRSortDrpdwn.sendKeys(Keys.TAB);
		
		//click(BRSSelectStatusDrpdwn);
		Select s1=new Select(BRSSelectStatusDrpdwn);
		s1.selectByValue("2");
		Thread.sleep(3000);
		BRSSelectStatusDrpdwn.sendKeys(Keys.TAB);
		
		//click(BRSSelectCRDRDrpdwn);
		Select s2=new Select(BRSSelectCRDRDrpdwn);
		s2.selectByValue("2");
		Thread.sleep(3000);
		BRSSelectCRDRDrpdwn.sendKeys(Keys.TAB);
		
		click(LoadBtn);
		Thread.sleep(8000);
		
		
		ArrayList<String>brsRow1ArrayList=new ArrayList<String>();
		for(int i=0;i<BRS_DocNoColList.size();i++)
		{
			brsRow1ArrayList.add(BRS_DocNoColList.get(i).getText());
		}
		
		String actBeforeSortChequeList=brsRow1ArrayList.toString();
		String expBeforeSortChequeList="[Rct:3, Rct:4, Rct:5, Rct:6, Rct:7, Rct:8, Rct:9, Pmt:10, Pmt:10, Pmt:11, Pmt:11, Pmt:12, Pmt:12, Pmt:4, Pmt:5, Pmt:6, Pmt:7, Pmt:8, Pmt:9, Pmt:9]";
		
		System.out.println("Actual Cheque Col List	"	+	actBeforeSortChequeList);
		System.out.println("Expect Cheque Col List	"	+	expBeforeSortChequeList);
		
		//click(BRSortDrpdwn);
		//Thread.sleep(1000);
		Select s5=new Select(BRSortDrpdwn);
		s5.selectByValue("1");
		Thread.sleep(3000);
		BRSortDrpdwn.sendKeys(Keys.TAB);
		
		click(LoadBtn);
		Thread.sleep(8000);
		
		ArrayList<String>brsRow2ArrayList=new ArrayList<String>();
		for(int i=0;i<BRS_DocNoColList.size();i++)
		{
			brsRow2ArrayList.add(BRS_DocNoColList.get(i).getText());
		}
		
		String actAfterSortChequeList=brsRow2ArrayList.toString();
		String expAfterSortChequeList="[Rct:7, Rct:6, Rct:5, Rct:8, Rct:9, Rct:4, Rct:3, Pmt:7, Pmt:8, Pmt:6, Pmt:9, Pmt:9, Pmt:10, Pmt:10, Pmt:5, Pmt:11, Pmt:11, Pmt:12, Pmt:12, Pmt:4]";
		
		System.out.println("Actual Cheque Col List	"	+	actAfterSortChequeList);
		System.out.println("Expect Cheque Col List	"	+	expAfterSortChequeList);
		
		
		
		   String actBookBal=bankRecBookBal.getText();
		   String expBookBal="9,484.31 Cr";

			String actbankRecOutDebits=bankRecOutDebits.getText();
		    String expbankRecOutDebits="5,855.04 Dr";
		
			String actbankRecOutCredits=bankRecOutCredits.getText();
		    String expbankRecOutCredits="15,339.35 Cr";
		
			String actbankRecClearedBal=bankRecClearedBal.getText();
		    String expbankRecClearedBal="0.00";
		
			String actbankRecOpenBal=bankRecOpenBal.getText();
		    String expbankRecOpenBal="0.00";
			

			String actbankRecDebitCounts=bankRecDebitCounts.getText();
		    String expbankRecDebitCounts="7";
			
			String actbankRecCreditCounts=bankRecCreditCounts.getText();
		    String expbankRecCreditCounts="13";
		    
			String actbankRecBankBal=bankRecBankBal.getAttribute("value");
		    String expbankRecBankBal="0.00";
			
		    String actbankRecDiff=bankRecDif.getText();
		    String expbankRecDiff="0.00";


			
		   System.out.println("**********************************checkBankReconciliationReport*****************************************");
		   System.out.println("BookBal             : "+actBookBal             +" Value Expected  : "+expBookBal);
	       System.out.println("bankRecOutDebits    : "+actbankRecOutDebits    +" Value Expected  : "+expbankRecOutDebits);
	       System.out.println("bankRecOutCredits   : "+actbankRecOutCredits   +" Value Expected  : "+expbankRecOutCredits);
	       System.out.println("bankRecClearedBal   : "+actbankRecClearedBal   +" Value Expected  : "+expbankRecClearedBal);
	       System.out.println("Opening Bal         : "+actbankRecOpenBal      +" Value Expected  : "+expbankRecOpenBal);
	       System.out.println("bankRecDebitCounts  : "+actbankRecDebitCounts  +" Value Expected  : "+expbankRecDebitCounts);
	       System.out.println("bankRecCreditCounts : "+actbankRecCreditCounts +" Value Expected  : "+expbankRecCreditCounts);
	       System.out.println("bankRecBankBal      : "+actbankRecBankBal      +" Value Expected  : "+expbankRecBankBal);
	       System.out.println("bankRecDifferr      : "+actbankRecDiff	      +" Value Expected  : "+expbankRecDiff);
 
		if(actBeforeSortChequeList.equalsIgnoreCase(expBeforeSortChequeList) && actAfterSortChequeList.equalsIgnoreCase(expAfterSortChequeList)&&
				 actBookBal.equalsIgnoreCase(expBookBal) &&
				actbankRecOutDebits.equalsIgnoreCase(expbankRecOutDebits) && actbankRecOutCredits.equalsIgnoreCase(expbankRecOutCredits) &&
				actbankRecClearedBal.equalsIgnoreCase(expbankRecClearedBal) && actbankRecDebitCounts.equalsIgnoreCase(expbankRecDebitCounts) &&
				actbankRecOpenBal.equalsIgnoreCase(expbankRecBankBal)&&
				actbankRecCreditCounts.equalsIgnoreCase(expbankRecCreditCounts) && actbankRecBankBal.equalsIgnoreCase(expbankRecBankBal)
				&& actbankRecDiff.equalsIgnoreCase(expbankRecDiff))
		{
			return true;
		}
		else
		{
			return false;
		}

		
	}
	
	
	public boolean checkSortOrderASDateandDocumentTypeinBankReconciliationReport() throws InterruptedException
	{

		
		click(serachMenuTextHomePage);
		
		serachMenuTextHomePage.sendKeys("Bank Reconciliation");
		Thread.sleep(2000);
		serachMenuTextHomePage.sendKeys(Keys.ENTER);
		
		Thread.sleep(3500);
		
		click(reportbankTxt);
		reportbankTxt.sendKeys("HDFC");
		Thread.sleep(1500);
		reportbankTxt.sendKeys(Keys.TAB);
		
		//click(BRSortDrpdwn);
		Select s=new Select(BRSortDrpdwn);
		s.selectByValue("0");
		Thread.sleep(2000);
		BRSortDrpdwn.sendKeys(Keys.TAB);
		
		//click(BRSSelectStatusDrpdwn);
		Select s1=new Select(BRSSelectStatusDrpdwn);
		s1.selectByValue("2");
		Thread.sleep(3000);
		BRSSelectStatusDrpdwn.sendKeys(Keys.TAB);
		
		//click(BRSSelectCRDRDrpdwn);
		Select s2=new Select(BRSSelectCRDRDrpdwn);
		s2.selectByValue("2");
		Thread.sleep(3000);
		BRSSelectCRDRDrpdwn.sendKeys(Keys.TAB);
		
		click(LoadBtn);
		Thread.sleep(8000);
		
		
		ArrayList<String>brsRow1ArrayList=new ArrayList<String>();
		for(int i=0;i<BRS_DocNoColList.size();i++)
		{
			brsRow1ArrayList.add(BRS_DocNoColList.get(i).getText());
		}
		
		String actBeforeSortChequeList=brsRow1ArrayList.toString();
		String expBeforeSortChequeList="[Rct:3, Rct:4, Rct:5, Rct:6, Rct:7, Rct:8, Rct:9, Pmt:10, Pmt:10, Pmt:11, Pmt:11, Pmt:12, Pmt:12, Pmt:4, Pmt:5, Pmt:6, Pmt:7, Pmt:8, Pmt:9, Pmt:9]";
		
		System.out.println("Actual Cheque Col List	"	+	actBeforeSortChequeList);
		System.out.println("Expect Cheque Col List	"	+	expBeforeSortChequeList);
		
		//click(BRSortDrpdwn);
		//Thread.sleep(1000);
		Select s5=new Select(BRSortDrpdwn);
		s5.selectByValue("2");
		Thread.sleep(3000);
		BRSortDrpdwn.sendKeys(Keys.TAB);
		
		click(LoadBtn);
		Thread.sleep(8000);
		
		ArrayList<String>brsRow2ArrayList=new ArrayList<String>();
		for(int i=0;i<BRS_DocNoColList.size();i++)
		{
			brsRow2ArrayList.add(BRS_DocNoColList.get(i).getText());
		}
		
		String actAfterSortChequeList=brsRow2ArrayList.toString();
		String expAfterSortChequeList="[Pmt:7, Pmt:8, Pmt:6, Rct:7, Rct:6, Rct:5, Pmt:9, Pmt:9, Rct:8, Rct:9, Pmt:10, Pmt:10, Pmt:11, Pmt:11, Pmt:12, Pmt:12, Pmt:5, Rct:4, Pmt:4, Rct:3]";
		
		System.out.println("Actual Cheque Col List	"	+	actAfterSortChequeList);
		System.out.println("Expect Cheque Col List	"	+	expAfterSortChequeList);
		
		
		
		   String actBookBal=bankRecBookBal.getText();
		   String expBookBal="9,484.31 Cr";

			String actbankRecOutDebits=bankRecOutDebits.getText();
		    String expbankRecOutDebits="5,855.04 Dr";
		
			String actbankRecOutCredits=bankRecOutCredits.getText();
		    String expbankRecOutCredits="15,339.35 Cr";
		
			String actbankRecClearedBal=bankRecClearedBal.getText();
		    String expbankRecClearedBal="0.00";
		
			String actbankRecOpenBal=bankRecOpenBal.getText();
		    String expbankRecOpenBal="0.00";
			

			String actbankRecDebitCounts=bankRecDebitCounts.getText();
		    String expbankRecDebitCounts="7";
			
			String actbankRecCreditCounts=bankRecCreditCounts.getText();
		    String expbankRecCreditCounts="13";
		    
			String actbankRecBankBal=bankRecBankBal.getAttribute("value");
		    String expbankRecBankBal="0.00";
			
		    String actbankRecDiff=bankRecDif.getText();
		    String expbankRecDiff="0.00";


		   System.out.println("**********************************checkBankReconciliationReport*****************************************");
		   System.out.println("BookBal             : "+actBookBal             +" Value Expected  : "+expBookBal);
	       System.out.println("bankRecOutDebits    : "+actbankRecOutDebits    +" Value Expected  : "+expbankRecOutDebits);
	       System.out.println("bankRecOutCredits   : "+actbankRecOutCredits   +" Value Expected  : "+expbankRecOutCredits);
	       System.out.println("bankRecClearedBal   : "+actbankRecClearedBal   +" Value Expected  : "+expbankRecClearedBal);
	       System.out.println("Opening Bal         : "+actbankRecOpenBal      +" Value Expected  : "+expbankRecOpenBal);
	       System.out.println("bankRecDebitCounts  : "+actbankRecDebitCounts  +" Value Expected  : "+expbankRecDebitCounts);
	       System.out.println("bankRecCreditCounts : "+actbankRecCreditCounts +" Value Expected  : "+expbankRecCreditCounts);
	       System.out.println("bankRecBankBal      : "+actbankRecBankBal      +" Value Expected  : "+expbankRecBankBal);
	       System.out.println("bankRecDifferr      : "+actbankRecDiff	      +" Value Expected  : "+expbankRecDiff);
 
		if(actBeforeSortChequeList.equalsIgnoreCase(expBeforeSortChequeList) && actAfterSortChequeList.equalsIgnoreCase(expAfterSortChequeList)&&
				 actBookBal.equalsIgnoreCase(expBookBal) &&
				actbankRecOutDebits.equalsIgnoreCase(expbankRecOutDebits) && actbankRecOutCredits.equalsIgnoreCase(expbankRecOutCredits) &&
				actbankRecClearedBal.equalsIgnoreCase(expbankRecClearedBal) && actbankRecDebitCounts.equalsIgnoreCase(expbankRecDebitCounts) &&
				actbankRecOpenBal.equalsIgnoreCase(expbankRecBankBal)&&
				actbankRecCreditCounts.equalsIgnoreCase(expbankRecCreditCounts) && actbankRecBankBal.equalsIgnoreCase(expbankRecBankBal)
				&& actbankRecDiff.equalsIgnoreCase(expbankRecDiff))
		{
			return true;
		}
		else
		{
			return false;
		}

		
	}
	
	
	public boolean checkSortOrderASDocumentTypeAndChequeNuminBankReconciliationReport() throws InterruptedException
	{
		
		click(serachMenuTextHomePage);
		
		serachMenuTextHomePage.sendKeys("Bank Reconciliation");
		Thread.sleep(2000);
		serachMenuTextHomePage.sendKeys(Keys.ENTER);
		
		Thread.sleep(3500);
		
		click(reportbankTxt);
		reportbankTxt.sendKeys("HDFC");
		Thread.sleep(1500);
		reportbankTxt.sendKeys(Keys.TAB);
		
		//click(BRSortDrpdwn);
		Select s=new Select(BRSortDrpdwn);
		s.selectByValue("0");
		Thread.sleep(2000);
		BRSortDrpdwn.sendKeys(Keys.TAB);
		
		//click(BRSSelectStatusDrpdwn);
		Select s1=new Select(BRSSelectStatusDrpdwn);
		s1.selectByValue("2");
		Thread.sleep(3000);
		BRSSelectStatusDrpdwn.sendKeys(Keys.TAB);
		
		//click(BRSSelectCRDRDrpdwn);
		Select s2=new Select(BRSSelectCRDRDrpdwn);
		s2.selectByValue("2");
		Thread.sleep(3000);
		BRSSelectCRDRDrpdwn.sendKeys(Keys.TAB);
		
		click(LoadBtn);
		Thread.sleep(12000);
		
		
		ArrayList<String>brsRow1ArrayList=new ArrayList<String>();
		for(int i=0;i<BRS_ChequeNumList.size();i++)
		{
			brsRow1ArrayList.add(BRS_ChequeNumList.get(i).getText());
		}
		
		String actBeforeSortChequeList=brsRow1ArrayList.toString();
		String expBeforeSortChequeList="[, Rpt-001, RP001, Rct002, Rct003, Rct004, Rct006, Pmt010, Pmt010, Pmt050, Pmt050, Pmt050, Pmt050, , pmt-001, RP001, Pmt002, Pmt003, Pmt010, Pmt010]";
		
		System.out.println("Actual Cheque Col List	"	+	actBeforeSortChequeList);
		System.out.println("Expect Cheque Col List	"	+	expBeforeSortChequeList);
		
		
		ArrayList<String>brsRow2ArrayList=new ArrayList<String>();
		for(int i=0;i<BRS_DocNoColList.size();i++)
		{
			brsRow2ArrayList.add(BRS_DocNoColList.get(i).getText());
		}
		
		String actBeforeSortDocList=brsRow2ArrayList.toString();
		String expBeforeSortDocList="[Rct:3, Rct:4, Rct:5, Rct:6, Rct:7, Rct:8, Rct:9, Pmt:10, Pmt:10, Pmt:11, Pmt:11, Pmt:12, Pmt:12, Pmt:4, Pmt:5, Pmt:6, Pmt:7, Pmt:8, Pmt:9, Pmt:9]";
		
		System.out.println("Actual Doc No Col List	"	+	actBeforeSortDocList);
		System.out.println("Expect Doc No Col List	"	+	expBeforeSortDocList);
		
		//click(BRSortDrpdwn);
		//Thread.sleep(1000);
		Select s5=new Select(BRSortDrpdwn);
		s5.selectByValue("3");
		Thread.sleep(3000);
		BRSortDrpdwn.sendKeys(Keys.TAB);
		
		click(LoadBtn);
		Thread.sleep(8000);
		
		ArrayList<String>brsRow3ArrayList=new ArrayList<String>();
		for(int i=0;i<BRS_ChequeNumList.size();i++)
		{
			brsRow3ArrayList.add(BRS_ChequeNumList.get(i).getText());
		}
		
		String actAfterSortChequeList=brsRow3ArrayList.toString();
		String expAfterSortChequeList="[, Rct002, Rct003, Rct004, Rct006, RP001, Rpt-001, , pmt-001, Pmt002, Pmt003, Pmt010, Pmt010, Pmt010, Pmt010, Pmt050, Pmt050, Pmt050, Pmt050, RP001]";
		
		System.out.println("Actual Cheque Col List	"	+	actAfterSortChequeList);
		System.out.println("Expect Cheque Col List	"	+	expAfterSortChequeList);
		
		
		
		ArrayList<String>brsRow4ArrayList=new ArrayList<String>();
		for(int i=0;i<BRS_DocNoColList.size();i++)
		{
			brsRow4ArrayList.add(BRS_DocNoColList.get(i).getText());
		}
		
		String actAfterSortDocList=brsRow4ArrayList.toString();
		String expAfterSortDocList="[Rct:3, Rct:6, Rct:7, Rct:8, Rct:9, Rct:5, Rct:4, Pmt:4, Pmt:5, Pmt:7, Pmt:8, Pmt:10, Pmt:10, Pmt:9, Pmt:9, Pmt:11, Pmt:11, Pmt:12, Pmt:12, Pmt:6]";
		
		System.out.println("Actual Doc No Col List	"	+	actAfterSortDocList);
		System.out.println("Expect Doc No Col List	"	+	expAfterSortDocList);
		
		
		
		   String actBookBal=bankRecBookBal.getText();
		   String expBookBal="9,484.31 Cr";

			String actbankRecOutDebits=bankRecOutDebits.getText();
		    String expbankRecOutDebits="5,855.04 Dr";
		
			String actbankRecOutCredits=bankRecOutCredits.getText();
		    String expbankRecOutCredits="15,339.35 Cr";
		
			String actbankRecClearedBal=bankRecClearedBal.getText();
		    String expbankRecClearedBal="0.00";
		
			String actbankRecOpenBal=bankRecOpenBal.getText();
		    String expbankRecOpenBal="0.00";
			

			String actbankRecDebitCounts=bankRecDebitCounts.getText();
		    String expbankRecDebitCounts="7";
			
			String actbankRecCreditCounts=bankRecCreditCounts.getText();
		    String expbankRecCreditCounts="13";
		    
			String actbankRecBankBal=bankRecBankBal.getAttribute("value");
		    String expbankRecBankBal="0.00";
			
		    String actbankRecDiff=bankRecDif.getText();
		    String expbankRecDiff="0.00";


			
		   System.out.println("**********************************checkBankReconciliationReport*****************************************");
		   System.out.println("BookBal             : "+actBookBal             +" Value Expected  : "+expBookBal);
	       System.out.println("bankRecOutDebits    : "+actbankRecOutDebits    +" Value Expected  : "+expbankRecOutDebits);
	       System.out.println("bankRecOutCredits   : "+actbankRecOutCredits   +" Value Expected  : "+expbankRecOutCredits);
	       System.out.println("bankRecClearedBal   : "+actbankRecClearedBal   +" Value Expected  : "+expbankRecClearedBal);
	       System.out.println("Opening Bal         : "+actbankRecOpenBal      +" Value Expected  : "+expbankRecOpenBal);
	       System.out.println("bankRecDebitCounts  : "+actbankRecDebitCounts  +" Value Expected  : "+expbankRecDebitCounts);
	       System.out.println("bankRecCreditCounts : "+actbankRecCreditCounts +" Value Expected  : "+expbankRecCreditCounts);
	       System.out.println("bankRecBankBal      : "+actbankRecBankBal      +" Value Expected  : "+expbankRecBankBal);
	       System.out.println("bankRecDifferr      : "+actbankRecDiff	      +" Value Expected  : "+expbankRecDiff);

		if(actBeforeSortChequeList.equalsIgnoreCase(expBeforeSortChequeList) && actAfterSortChequeList.equalsIgnoreCase(expAfterSortChequeList)&&
				actBeforeSortDocList.equalsIgnoreCase(expBeforeSortDocList)&& actAfterSortDocList.equalsIgnoreCase(expAfterSortDocList)&&
				 actBookBal.equalsIgnoreCase(expBookBal) &&
				actbankRecOutDebits.equalsIgnoreCase(expbankRecOutDebits) && actbankRecOutCredits.equalsIgnoreCase(expbankRecOutCredits) &&
				actbankRecClearedBal.equalsIgnoreCase(expbankRecClearedBal) && actbankRecDebitCounts.equalsIgnoreCase(expbankRecDebitCounts) &&
				actbankRecOpenBal.equalsIgnoreCase(expbankRecBankBal)&&
				actbankRecCreditCounts.equalsIgnoreCase(expbankRecCreditCounts) && actbankRecBankBal.equalsIgnoreCase(expbankRecBankBal)
				&& actbankRecDiff.equalsIgnoreCase(expbankRecDiff))
		{
			return true;
		}
		else
		{
			return false;
		}

		
	}
	
	public boolean checkSortOrderAsDocTypeCleranceDateChequeNumWithPendingDebitsinBankReconciliationReport() throws InterruptedException 
	{

		
		click(serachMenuTextHomePage);
		
		serachMenuTextHomePage.sendKeys("Bank Reconciliation");
		Thread.sleep(2000);
		serachMenuTextHomePage.sendKeys(Keys.ENTER);
		
		Thread.sleep(6000);
		
		click(reportbankTxt);
		reportbankTxt.sendKeys("HDFC");
		Thread.sleep(1500);
		reportbankTxt.sendKeys(Keys.TAB);
		
		//click(BRSortDrpdwn);
		Select s=new Select(BRSortDrpdwn);
		s.selectByValue("1");
		Thread.sleep(2000);
		BRSortDrpdwn.sendKeys(Keys.TAB);
		
		//click(BRSSelectStatusDrpdwn);
		Select s1=new Select(BRSSelectStatusDrpdwn);
		s1.selectByValue("0");
		Thread.sleep(3000);
		BRSSelectStatusDrpdwn.sendKeys(Keys.TAB);
		
		//click(BRSSelectCRDRDrpdwn);
		Select s2=new Select(BRSSelectCRDRDrpdwn);
		s2.selectByValue("0");
		Thread.sleep(3000);
		BRSSelectCRDRDrpdwn.sendKeys(Keys.TAB);
		
		click(LoadBtn);
		Thread.sleep(8000);
		
		
		ArrayList<String>brsRow1ArrayList=new ArrayList<String>();
		for(int i=0;i<BRS_DebitAmtColList.size();i++)
		{
			brsRow1ArrayList.add(BRS_DebitAmtColList.get(i).getText());
		}
		
		String actDebitAmtList=brsRow1ArrayList.toString();
		String expDebitAmtList="[673.09, 1,250.47, 1,250.47, 865.24, 475.05, 1,085.03, 255.69, , , , , , , , , , , , , ]";
		
		System.out.println("Actual Debit Amt Col List	"	+	actDebitAmtList);
		System.out.println("Expect Debit Amt Col List	"	+	expDebitAmtList);
		
		
		ArrayList<String>brsRow2ArrayList=new ArrayList<String>();
		for(int i=0;i<BRS_CreditAmtColList.size();i++)
		{
			brsRow2ArrayList.add(BRS_CreditAmtColList.get(i).getText());
		}
		
		String actCreditAmtList=brsRow2ArrayList.toString();
		String expCreditAmtList="[0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, , , , , , , , , , , , , ]";
		
		System.out.println("Actual Credit Amt Col List	"	+	actCreditAmtList);
		System.out.println("Expect Credit Amt Col List	"	+	expCreditAmtList);
		
				
		   String actBookBal=bankRecBookBal.getText();
		   String expBookBal="9,484.31 Cr";

			String actbankRecOutDebits=bankRecOutDebits.getText();
		    String expbankRecOutDebits="5,855.04 Dr";
		
			String actbankRecOutCredits=bankRecOutCredits.getText();
		    String expbankRecOutCredits="15,339.35 Cr";
		
			String actbankRecClearedBal=bankRecClearedBal.getText();
		    String expbankRecClearedBal="0.00";
		
			String actbankRecOpenBal=bankRecOpenBal.getText();
		    String expbankRecOpenBal="0.00";
			

			String actbankRecDebitCounts=bankRecDebitCounts.getText();
		    String expbankRecDebitCounts="7";
			
			String actbankRecCreditCounts=bankRecCreditCounts.getText();
		    String expbankRecCreditCounts="0";
		    
			String actbankRecBankBal=bankRecBankBal.getAttribute("value");
		    String expbankRecBankBal="0.00";
			
		    String actbankRecDiff=bankRecDif.getText();
		    String expbankRecDiff="0.00";


		   System.out.println("**********************************checkBankReconciliationReport*****************************************");
		   System.out.println("BookBal             : "+actBookBal             +" Value Expected  : "+expBookBal);
	       System.out.println("bankRecOutDebits    : "+actbankRecOutDebits    +" Value Expected  : "+expbankRecOutDebits);
	       System.out.println("bankRecOutCredits   : "+actbankRecOutCredits   +" Value Expected  : "+expbankRecOutCredits);
	       System.out.println("bankRecClearedBal   : "+actbankRecClearedBal   +" Value Expected  : "+expbankRecClearedBal);
	       System.out.println("Opening Bal         : "+actbankRecOpenBal      +" Value Expected  : "+expbankRecOpenBal);
	       System.out.println("bankRecDebitCounts  : "+actbankRecDebitCounts  +" Value Expected  : "+expbankRecDebitCounts);
	       System.out.println("bankRecCreditCounts : "+actbankRecCreditCounts +" Value Expected  : "+expbankRecCreditCounts);
	       System.out.println("bankRecBankBal      : "+actbankRecBankBal      +" Value Expected  : "+expbankRecBankBal);
	       System.out.println("bankRecDifferr      : "+actbankRecDiff	      +" Value Expected  : "+expbankRecDiff);

		if(actDebitAmtList.equalsIgnoreCase(expDebitAmtList)&&actCreditAmtList.equalsIgnoreCase(expCreditAmtList)&&
				 actBookBal.equalsIgnoreCase(expBookBal) &&
				actbankRecOutDebits.equalsIgnoreCase(expbankRecOutDebits) && actbankRecOutCredits.equalsIgnoreCase(expbankRecOutCredits) &&
				actbankRecClearedBal.equalsIgnoreCase(expbankRecClearedBal) && actbankRecDebitCounts.equalsIgnoreCase(expbankRecDebitCounts) &&
				actbankRecOpenBal.equalsIgnoreCase(expbankRecBankBal)&&
				actbankRecCreditCounts.equalsIgnoreCase(expbankRecCreditCounts) && actbankRecBankBal.equalsIgnoreCase(expbankRecBankBal)
				&& actbankRecDiff.equalsIgnoreCase(expbankRecDiff))
		{
			return true;
		}
		else
		{
			return false;
		}

		
	
	}
	
	
	
	public boolean checkSortOrderAsDocTypeCleranceDateChequeNumWithPendingCreditsinBankReconciliationReport() throws InterruptedException 
	{

		
		//click(BRSSelectCRDRDrpdwn);
		Select s2=new Select(BRSSelectCRDRDrpdwn);
		s2.selectByValue("1");
		Thread.sleep(3000);
		BRSSelectCRDRDrpdwn.sendKeys(Keys.TAB);
		
		click(LoadBtn);
		Thread.sleep(8000);
		
		
		ArrayList<String>brsRow1ArrayList=new ArrayList<String>();
		for(int i=0;i<BRS_DebitAmtColList.size();i++)
		{
			brsRow1ArrayList.add(BRS_DebitAmtColList.get(i).getText());
		}
		
		String actDebitAmtList=brsRow1ArrayList.toString();
		String expDebitAmtList="[0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, , , , , , , ]";
		
		System.out.println("Actual Debit Amt Col List	"	+	actDebitAmtList);
		System.out.println("Expect Debit Amt Col List	"	+	expDebitAmtList);
		
		
		ArrayList<String>brsRow2ArrayList=new ArrayList<String>();
		for(int i=0;i<BRS_CreditAmtColList.size();i++)
		{
			brsRow2ArrayList.add(BRS_CreditAmtColList.get(i).getText());
		}
		
		String actCreditAmtList=brsRow2ArrayList.toString();
		String expCreditAmtList="[2,514.03, 354.12, 3,807.81, 678.58, 788.33, 123.89, 1,100.25, 1,005.28, 788.33, 678.58, 596.05, 1,003.25, 1,900.85, , , , , , , ]";
		
		System.out.println("Actual Credit Amt Col List	"	+	actCreditAmtList);
		System.out.println("Expect Credit Amt Col List	"	+	expCreditAmtList);
		
				
		   String actBookBal=bankRecBookBal.getText();
		   String expBookBal="9,484.31 Cr";

			String actbankRecOutDebits=bankRecOutDebits.getText();
		    String expbankRecOutDebits="5,855.04 Dr";
		
			String actbankRecOutCredits=bankRecOutCredits.getText();
		    String expbankRecOutCredits="15,339.35 Cr";
		
			String actbankRecClearedBal=bankRecClearedBal.getText();
		    String expbankRecClearedBal="0.00";
		
			String actbankRecOpenBal=bankRecOpenBal.getText();
		    String expbankRecOpenBal="0.00";
			

			String actbankRecDebitCounts=bankRecDebitCounts.getText();
		    String expbankRecDebitCounts="0";
			
			String actbankRecCreditCounts=bankRecCreditCounts.getText();
		    String expbankRecCreditCounts="13";
		    
			String actbankRecBankBal=bankRecBankBal.getAttribute("value");
		    String expbankRecBankBal="0.00";
			
		    String actbankRecDiff=bankRecDif.getText();
		    String expbankRecDiff="0.00";


			
		   System.out.println("**********************************checkBankReconciliationReport*****************************************");
		   System.out.println("BookBal             : "+actBookBal             +" Value Expected  : "+expBookBal);
	       System.out.println("bankRecOutDebits    : "+actbankRecOutDebits    +" Value Expected  : "+expbankRecOutDebits);
	       System.out.println("bankRecOutCredits   : "+actbankRecOutCredits   +" Value Expected  : "+expbankRecOutCredits);
	       System.out.println("bankRecClearedBal   : "+actbankRecClearedBal   +" Value Expected  : "+expbankRecClearedBal);
	       System.out.println("Opening Bal         : "+actbankRecOpenBal      +" Value Expected  : "+expbankRecOpenBal);
	       System.out.println("bankRecDebitCounts  : "+actbankRecDebitCounts  +" Value Expected  : "+expbankRecDebitCounts);
	       System.out.println("bankRecCreditCounts : "+actbankRecCreditCounts +" Value Expected  : "+expbankRecCreditCounts);
	       System.out.println("bankRecBankBal      : "+actbankRecBankBal      +" Value Expected  : "+expbankRecBankBal);
	       System.out.println("bankRecDifferr      : "+actbankRecDiff	      +" Value Expected  : "+expbankRecDiff);

		if(actDebitAmtList.equalsIgnoreCase(expDebitAmtList)&&actCreditAmtList.equalsIgnoreCase(expCreditAmtList)&&
				 actBookBal.equalsIgnoreCase(expBookBal) &&
				actbankRecOutDebits.equalsIgnoreCase(expbankRecOutDebits) && actbankRecOutCredits.equalsIgnoreCase(expbankRecOutCredits) &&
				actbankRecClearedBal.equalsIgnoreCase(expbankRecClearedBal) && actbankRecDebitCounts.equalsIgnoreCase(expbankRecDebitCounts) &&
				actbankRecOpenBal.equalsIgnoreCase(expbankRecBankBal)&&
				actbankRecCreditCounts.equalsIgnoreCase(expbankRecCreditCounts) && actbankRecBankBal.equalsIgnoreCase(expbankRecBankBal)
				&& actbankRecDiff.equalsIgnoreCase(expbankRecDiff))
		{
			return true;
		}
		else
		{
			return false;
		}

	}
	
	@FindBy(xpath="//*[@id='BRTable_body']")
	public static WebElement BRSTable;
	
	public boolean checkSortOrderAsDocTypeCleranceDateChequeNumWithClearedDebitsinBankReconciliationReport() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
				
		//click(BRSSelectStatusDrpdwn);
		Select s1=new Select(BRSSelectStatusDrpdwn);
		s1.selectByValue("1");
		Thread.sleep(3000);
		BRSSelectStatusDrpdwn.sendKeys(Keys.TAB);
		
		//click(BRSSelectCRDRDrpdwn);
		Select s2=new Select(BRSSelectCRDRDrpdwn);
		s2.selectByValue("0");
		Thread.sleep(3000);
		BRSSelectCRDRDrpdwn.sendKeys(Keys.TAB);
		
		click(LoadBtn);
		Thread.sleep(4000);
		
		
		String expMsg="No records exists with this filter condition";
		String actMsg=checkValidationMessage(expMsg);
		
		if( actMsg.equalsIgnoreCase(expMsg))
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	
	
	public boolean checkSortOrderAsDocTypeCleranceDateChequeNumWithClearedCreditsinBankReconciliationReport() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
				
		//click(BRSSelectCRDRDrpdwn);
		Select s2=new Select(BRSSelectCRDRDrpdwn);
		s2.selectByValue("1");
		Thread.sleep(3000);
		BRSSelectCRDRDrpdwn.sendKeys(Keys.TAB);
		
		click(LoadBtn);
		Thread.sleep(4000);
		
		String expMsg="No records exists with this filter condition";
		String actMsg=checkValidationMessage(expMsg);
		
		if( actMsg.equalsIgnoreCase(expMsg))
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	
	public boolean checkSortOrderAsDateDocNumWithPendingDebitsinBankReconciliationReport() throws InterruptedException 
	{

		//click(BRSortDrpdwn);
		Select s=new Select(BRSortDrpdwn);
		s.selectByValue("2");
		Thread.sleep(2000);
		BRSortDrpdwn.sendKeys(Keys.TAB);
		
		//click(BRSSelectStatusDrpdwn);
		Select s1=new Select(BRSSelectStatusDrpdwn);
		s1.selectByValue("0");
		Thread.sleep(3000);
		BRSSelectStatusDrpdwn.sendKeys(Keys.TAB);
		
		//click(BRSSelectCRDRDrpdwn);
		Select s2=new Select(BRSSelectCRDRDrpdwn);
		s2.selectByValue("0");
		Thread.sleep(3000);
		BRSSelectCRDRDrpdwn.sendKeys(Keys.TAB);
		
		click(LoadBtn);
		Thread.sleep(8000);
		
		
		ArrayList<String>brsRow1ArrayList=new ArrayList<String>();
		for(int i=0;i<BRS_DebitAmtColList.size();i++)
		{
			brsRow1ArrayList.add(BRS_DebitAmtColList.get(i).getText());
		}
		
		String actDebitAmtList=brsRow1ArrayList.toString();
		String expDebitAmtList="[673.09, 1,250.47, 1,250.47, 865.24, 475.05, 1,085.03, 255.69, , , , , , , , , , , , , ]";
		
		System.out.println("Actual Debit Amt Col List	"	+	actDebitAmtList);
		System.out.println("Expect Debit Amt Col List	"	+	expDebitAmtList);
		
		
		ArrayList<String>brsRow2ArrayList=new ArrayList<String>();
		for(int i=0;i<BRS_CreditAmtColList.size();i++)
		{
			brsRow2ArrayList.add(BRS_CreditAmtColList.get(i).getText());
		}
		
		String actCreditAmtList=brsRow2ArrayList.toString();
		String expCreditAmtList="[0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, , , , , , , , , , , , , ]";
		
		System.out.println("Actual Credit Amt Col List	"	+	actCreditAmtList);
		System.out.println("Expect Credit Amt Col List	"	+	expCreditAmtList);
		
				
		   String actBookBal=bankRecBookBal.getText();
		   String expBookBal="9,484.31 Cr";

			String actbankRecOutDebits=bankRecOutDebits.getText();
		    String expbankRecOutDebits="5,855.04 Dr";
		
			String actbankRecOutCredits=bankRecOutCredits.getText();
		    String expbankRecOutCredits="15,339.35 Cr";
		
			String actbankRecClearedBal=bankRecClearedBal.getText();
		    String expbankRecClearedBal="0.00";
		
			String actbankRecOpenBal=bankRecOpenBal.getText();
		    String expbankRecOpenBal="0.00";
			

			String actbankRecDebitCounts=bankRecDebitCounts.getText();
		    String expbankRecDebitCounts="7";
			
			String actbankRecCreditCounts=bankRecCreditCounts.getText();
		    String expbankRecCreditCounts="0";
		    
			String actbankRecBankBal=bankRecBankBal.getAttribute("value");
		    String expbankRecBankBal="0.00";
			
		    String actbankRecDiff=bankRecDif.getText();
		    String expbankRecDiff="0.00";


		   System.out.println("**********************************checkBankReconciliationReport*****************************************");
		   System.out.println("BookBal             : "+actBookBal             +" Value Expected  : "+expBookBal);
	       System.out.println("bankRecOutDebits    : "+actbankRecOutDebits    +" Value Expected  : "+expbankRecOutDebits);
	       System.out.println("bankRecOutCredits   : "+actbankRecOutCredits   +" Value Expected  : "+expbankRecOutCredits);
	       System.out.println("bankRecClearedBal   : "+actbankRecClearedBal   +" Value Expected  : "+expbankRecClearedBal);
	       System.out.println("Opening Bal         : "+actbankRecOpenBal      +" Value Expected  : "+expbankRecOpenBal);
	       System.out.println("bankRecDebitCounts  : "+actbankRecDebitCounts  +" Value Expected  : "+expbankRecDebitCounts);
	       System.out.println("bankRecCreditCounts : "+actbankRecCreditCounts +" Value Expected  : "+expbankRecCreditCounts);
	       System.out.println("bankRecBankBal      : "+actbankRecBankBal      +" Value Expected  : "+expbankRecBankBal);
	       System.out.println("bankRecDifferr      : "+actbankRecDiff	      +" Value Expected  : "+expbankRecDiff);
 
		if(actDebitAmtList.equalsIgnoreCase(expDebitAmtList)&&actCreditAmtList.equalsIgnoreCase(expCreditAmtList)&&
				 actBookBal.equalsIgnoreCase(expBookBal) &&
				actbankRecOutDebits.equalsIgnoreCase(expbankRecOutDebits) && actbankRecOutCredits.equalsIgnoreCase(expbankRecOutCredits) &&
				actbankRecClearedBal.equalsIgnoreCase(expbankRecClearedBal) && actbankRecDebitCounts.equalsIgnoreCase(expbankRecDebitCounts) &&
				actbankRecOpenBal.equalsIgnoreCase(expbankRecBankBal)&&
				actbankRecCreditCounts.equalsIgnoreCase(expbankRecCreditCounts) && actbankRecBankBal.equalsIgnoreCase(expbankRecBankBal)
				&& actbankRecDiff.equalsIgnoreCase(expbankRecDiff))
		{
			return true;
		}
		else
		{
			return false;
		}

		
	
	}
	
	
	
	public boolean checkSortOrderAsDateDocNumWithPendingCreditsinBankReconciliationReport() throws InterruptedException 
	{

		
		//click(BRSSelectCRDRDrpdwn);
		Select s2=new Select(BRSSelectCRDRDrpdwn);
		s2.selectByValue("1");
		Thread.sleep(3000);
		BRSSelectCRDRDrpdwn.sendKeys(Keys.TAB);
		
		click(LoadBtn);
		Thread.sleep(8000);
		
		
		ArrayList<String>brsRow1ArrayList=new ArrayList<String>();
		for(int i=0;i<BRS_DebitAmtColList.size();i++)
		{
			brsRow1ArrayList.add(BRS_DebitAmtColList.get(i).getText());
		}
		
		String actDebitAmtList=brsRow1ArrayList.toString();
		String expDebitAmtList="[0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, , , , , , , ]";
		
		System.out.println("Actual Debit Amt Col List	"	+	actDebitAmtList);
		System.out.println("Expect Debit Amt Col List	"	+	expDebitAmtList);
		
		
		ArrayList<String>brsRow2ArrayList=new ArrayList<String>();
		for(int i=0;i<BRS_CreditAmtColList.size();i++)
		{
			brsRow2ArrayList.add(BRS_CreditAmtColList.get(i).getText());
		}
		
		String actCreditAmtList=brsRow2ArrayList.toString();
		String expCreditAmtList="[2,514.03, 354.12, 3,807.81, 678.58, 788.33, 123.89, 1,100.25, 788.33, 678.58, 596.05, 1,003.25, 1,005.28, 1,900.85, , , , , , , ]";
		
		System.out.println("Actual Credit Amt Col List	"	+	actCreditAmtList);
		System.out.println("Expect Credit Amt Col List	"	+	expCreditAmtList);
		
				
		   String actBookBal=bankRecBookBal.getText();
		   String expBookBal="9,484.31 Cr";

			String actbankRecOutDebits=bankRecOutDebits.getText();
		    String expbankRecOutDebits="5,855.04 Dr";
		
			String actbankRecOutCredits=bankRecOutCredits.getText();
		    String expbankRecOutCredits="15,339.35 Cr";
		
			String actbankRecClearedBal=bankRecClearedBal.getText();
		    String expbankRecClearedBal="0.00";
		
			String actbankRecOpenBal=bankRecOpenBal.getText();
		    String expbankRecOpenBal="0.00";
			

			String actbankRecDebitCounts=bankRecDebitCounts.getText();
		    String expbankRecDebitCounts="0";
			
			String actbankRecCreditCounts=bankRecCreditCounts.getText();
		    String expbankRecCreditCounts="13";
		    
			String actbankRecBankBal=bankRecBankBal.getAttribute("value");
		    String expbankRecBankBal="0.00";
			
		    String actbankRecDiff=bankRecDif.getText();
		    String expbankRecDiff="0.00";


			
		   System.out.println("**********************************checkBankReconciliationReport*****************************************");
		   System.out.println("BookBal             : "+actBookBal             +" Value Expected  : "+expBookBal);
	       System.out.println("bankRecOutDebits    : "+actbankRecOutDebits    +" Value Expected  : "+expbankRecOutDebits);
	       System.out.println("bankRecOutCredits   : "+actbankRecOutCredits   +" Value Expected  : "+expbankRecOutCredits);
	       System.out.println("bankRecClearedBal   : "+actbankRecClearedBal   +" Value Expected  : "+expbankRecClearedBal);
	       System.out.println("Opening Bal         : "+actbankRecOpenBal      +" Value Expected  : "+expbankRecOpenBal);
	       System.out.println("bankRecDebitCounts  : "+actbankRecDebitCounts  +" Value Expected  : "+expbankRecDebitCounts);
	       System.out.println("bankRecCreditCounts : "+actbankRecCreditCounts +" Value Expected  : "+expbankRecCreditCounts);
	       System.out.println("bankRecBankBal      : "+actbankRecBankBal      +" Value Expected  : "+expbankRecBankBal);
	       System.out.println("bankRecDifferr      : "+actbankRecDiff	      +" Value Expected  : "+expbankRecDiff);

		if(actDebitAmtList.equalsIgnoreCase(expDebitAmtList)&&actCreditAmtList.equalsIgnoreCase(expCreditAmtList)&&
				 actBookBal.equalsIgnoreCase(expBookBal) &&
				actbankRecOutDebits.equalsIgnoreCase(expbankRecOutDebits) && actbankRecOutCredits.equalsIgnoreCase(expbankRecOutCredits) &&
				actbankRecClearedBal.equalsIgnoreCase(expbankRecClearedBal) && actbankRecDebitCounts.equalsIgnoreCase(expbankRecDebitCounts) &&
				actbankRecOpenBal.equalsIgnoreCase(expbankRecBankBal)&&
				actbankRecCreditCounts.equalsIgnoreCase(expbankRecCreditCounts) && actbankRecBankBal.equalsIgnoreCase(expbankRecBankBal)
				&& actbankRecDiff.equalsIgnoreCase(expbankRecDiff))
		{
			return true;
		}
		else
		{
			return false;
		}

	}
	
	
	
	public boolean checkSortOrderAsDateDocNumWithClearedDebitsinBankReconciliationReport() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		
		//click(BRSSelectStatusDrpdwn);
		Select s1=new Select(BRSSelectStatusDrpdwn);
		s1.selectByValue("1");
		Thread.sleep(3000);
		BRSSelectStatusDrpdwn.sendKeys(Keys.TAB);
		
		//click(BRSSelectCRDRDrpdwn);
		Select s2=new Select(BRSSelectCRDRDrpdwn);
		s2.selectByValue("0");
		Thread.sleep(3000);
		BRSSelectCRDRDrpdwn.sendKeys(Keys.TAB);
		
		click(LoadBtn);
		Thread.sleep(5000);
		
		
		String expMsg="No records exists with this filter condition";
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
	
	
	
	public boolean checkSortOrderAsDateDocNumWithClearedCreditsinBankReconciliationReport() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		//click(BRSSelectCRDRDrpdwn);
		Select s2=new Select(BRSSelectCRDRDrpdwn);
		s2.selectByValue("1");
		Thread.sleep(3000);
		BRSSelectCRDRDrpdwn.sendKeys(Keys.TAB);
		
		click(LoadBtn);
		Thread.sleep(6000);
		
		
		String expMsg="No records exists with this filter condition";
		String actMsg=checkValidationMessage(expMsg);
		
		if( actMsg.equalsIgnoreCase(expMsg))
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	
	
	public boolean checkSortOrderAsDocTypeChequeNumWithPendingDebitsinBankReconciliationReport() throws InterruptedException 
	{

		//click(BRSortDrpdwn);
		Select s=new Select(BRSortDrpdwn);
		s.selectByValue("3");
		Thread.sleep(2000);
		BRSortDrpdwn.sendKeys(Keys.TAB);
		
		//click(BRSSelectStatusDrpdwn);
		Select s1=new Select(BRSSelectStatusDrpdwn);
		s1.selectByValue("0");
		Thread.sleep(3000);
		BRSSelectStatusDrpdwn.sendKeys(Keys.TAB);
		
		//click(BRSSelectCRDRDrpdwn);
		Select s2=new Select(BRSSelectCRDRDrpdwn);
		s2.selectByValue("0");
		Thread.sleep(3000);
		BRSSelectCRDRDrpdwn.sendKeys(Keys.TAB);
		
		click(LoadBtn);
		Thread.sleep(8000);
		
		
		ArrayList<String>brsRow1ArrayList=new ArrayList<String>();
		for(int i=0;i<BRS_DebitAmtColList.size();i++)
		{
			brsRow1ArrayList.add(BRS_DebitAmtColList.get(i).getText());
		}
		
		String actDebitAmtList=brsRow1ArrayList.toString();
		String expDebitAmtList="[255.69, 1,250.47, 673.09, 865.24, 475.05, 1,250.47, 1,085.03, , , , , , , , , , , , , ]";
		
		System.out.println("Actual Debit Amt Col List	"	+	actDebitAmtList);
		System.out.println("Expect Debit Amt Col List	"	+	expDebitAmtList);
		
		
		ArrayList<String>brsRow2ArrayList=new ArrayList<String>();
		for(int i=0;i<BRS_CreditAmtColList.size();i++)
		{
			brsRow2ArrayList.add(BRS_CreditAmtColList.get(i).getText());
		}
		
		String actCreditAmtList=brsRow2ArrayList.toString();
		String expCreditAmtList="[0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, , , , , , , , , , , , , ]";
		
		System.out.println("Actual Credit Amt Col List	"	+	actCreditAmtList);
		System.out.println("Expect Credit Amt Col List	"	+	expCreditAmtList);
		
				
		   String actBookBal=bankRecBookBal.getText();
		   String expBookBal="9,484.31 Cr";

			String actbankRecOutDebits=bankRecOutDebits.getText();
		    String expbankRecOutDebits="5,855.04 Dr";
		
			String actbankRecOutCredits=bankRecOutCredits.getText();
		    String expbankRecOutCredits="15,339.35 Cr";
		
			String actbankRecClearedBal=bankRecClearedBal.getText();
		    String expbankRecClearedBal="0.00";
		
			String actbankRecOpenBal=bankRecOpenBal.getText();
		    String expbankRecOpenBal="0.00";
			

			String actbankRecDebitCounts=bankRecDebitCounts.getText();
		    String expbankRecDebitCounts="7";
			
			String actbankRecCreditCounts=bankRecCreditCounts.getText();
		    String expbankRecCreditCounts="0";
		    
			String actbankRecBankBal=bankRecBankBal.getAttribute("value");
		    String expbankRecBankBal="0.00";
			
		    String actbankRecDiff=bankRecDif.getText();
		    String expbankRecDiff="0.00";


		   System.out.println("**********************************checkBankReconciliationReport*****************************************");
		   System.out.println("BookBal             : "+actBookBal             +" Value Expected  : "+expBookBal);
	       System.out.println("bankRecOutDebits    : "+actbankRecOutDebits    +" Value Expected  : "+expbankRecOutDebits);
	       System.out.println("bankRecOutCredits   : "+actbankRecOutCredits   +" Value Expected  : "+expbankRecOutCredits);
	       System.out.println("bankRecClearedBal   : "+actbankRecClearedBal   +" Value Expected  : "+expbankRecClearedBal);
	       System.out.println("Opening Bal         : "+actbankRecOpenBal      +" Value Expected  : "+expbankRecOpenBal);
	       System.out.println("bankRecDebitCounts  : "+actbankRecDebitCounts  +" Value Expected  : "+expbankRecDebitCounts);
	       System.out.println("bankRecCreditCounts : "+actbankRecCreditCounts +" Value Expected  : "+expbankRecCreditCounts);
	       System.out.println("bankRecBankBal      : "+actbankRecBankBal      +" Value Expected  : "+expbankRecBankBal);
	       System.out.println("bankRecDifferr      : "+actbankRecDiff	      +" Value Expected  : "+expbankRecDiff);

		if(actDebitAmtList.equalsIgnoreCase(expDebitAmtList)&&actCreditAmtList.equalsIgnoreCase(expCreditAmtList)&&
				 actBookBal.equalsIgnoreCase(expBookBal) &&
				actbankRecOutDebits.equalsIgnoreCase(expbankRecOutDebits) && actbankRecOutCredits.equalsIgnoreCase(expbankRecOutCredits) &&
				actbankRecClearedBal.equalsIgnoreCase(expbankRecClearedBal) && actbankRecDebitCounts.equalsIgnoreCase(expbankRecDebitCounts) &&
				actbankRecOpenBal.equalsIgnoreCase(expbankRecBankBal)&&
				actbankRecCreditCounts.equalsIgnoreCase(expbankRecCreditCounts) && actbankRecBankBal.equalsIgnoreCase(expbankRecBankBal) 
				&& actbankRecDiff.equalsIgnoreCase(expbankRecDiff))
		{
			return true;
		}
		else
		{
			return false;
		}

		
	
	}
	
	
	
	public boolean checkSortOrderAsDocTypeChequeNumWithPendingCreditsinBankReconciliationReport() throws InterruptedException 
	{

		
		//click(BRSSelectCRDRDrpdwn);
		Select s2=new Select(BRSSelectCRDRDrpdwn);
		s2.selectByValue("1");
		Thread.sleep(3000);
		BRSSelectCRDRDrpdwn.sendKeys(Keys.TAB);
		
		click(LoadBtn);
		Thread.sleep(8000);
		
		
		ArrayList<String>brsRow1ArrayList=new ArrayList<String>();
		for(int i=0;i<BRS_DebitAmtColList.size();i++)
		{
			brsRow1ArrayList.add(BRS_DebitAmtColList.get(i).getText());
		}
		
		String actDebitAmtList=brsRow1ArrayList.toString();
		String expDebitAmtList="[0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, , , , , , , ]";
		
		System.out.println("Actual Debit Amt Col List	"	+	actDebitAmtList);
		System.out.println("Expect Debit Amt Col List	"	+	expDebitAmtList);
		
		
		ArrayList<String>brsRow2ArrayList=new ArrayList<String>();
		for(int i=0;i<BRS_CreditAmtColList.size();i++)
		{
			brsRow2ArrayList.add(BRS_CreditAmtColList.get(i).getText());
		}
		
		String actCreditAmtList=brsRow2ArrayList.toString();
		String expCreditAmtList="[1,900.85, 1,005.28, 2,514.03, 354.12, 123.89, 1,100.25, 678.58, 788.33, 788.33, 678.58, 596.05, 1,003.25, 3,807.81, , , , , , , ]";
		
		System.out.println("Actual Credit Amt Col List	"	+	actCreditAmtList);
		System.out.println("Expect Credit Amt Col List	"	+	expCreditAmtList);
		
				
		   String actBookBal=bankRecBookBal.getText();
		   String expBookBal="9,484.31 Cr";

			String actbankRecOutDebits=bankRecOutDebits.getText();
		    String expbankRecOutDebits="5,855.04 Dr";
		
			String actbankRecOutCredits=bankRecOutCredits.getText();
		    String expbankRecOutCredits="15,339.35 Cr";
		
			String actbankRecClearedBal=bankRecClearedBal.getText();
		    String expbankRecClearedBal="0.00";
		
			String actbankRecOpenBal=bankRecOpenBal.getText();
		    String expbankRecOpenBal="0.00";
			

			String actbankRecDebitCounts=bankRecDebitCounts.getText();
		    String expbankRecDebitCounts="0";
			
			String actbankRecCreditCounts=bankRecCreditCounts.getText();
		    String expbankRecCreditCounts="13";
		    
			String actbankRecBankBal=bankRecBankBal.getAttribute("value");
		    String expbankRecBankBal="0.00";
			
		    String actbankRecDiff=bankRecDif.getText();
		    String expbankRecDiff="0.00";


		   System.out.println("**********************************checkBankReconciliationReport*****************************************");
		   System.out.println("BookBal             : "+actBookBal             +" Value Expected  : "+expBookBal);
	       System.out.println("bankRecOutDebits    : "+actbankRecOutDebits    +" Value Expected  : "+expbankRecOutDebits);
	       System.out.println("bankRecOutCredits   : "+actbankRecOutCredits   +" Value Expected  : "+expbankRecOutCredits);
	       System.out.println("bankRecClearedBal   : "+actbankRecClearedBal   +" Value Expected  : "+expbankRecClearedBal);
	       System.out.println("Opening Bal         : "+actbankRecOpenBal      +" Value Expected  : "+expbankRecOpenBal);
	       System.out.println("bankRecDebitCounts  : "+actbankRecDebitCounts  +" Value Expected  : "+expbankRecDebitCounts);
	       System.out.println("bankRecCreditCounts : "+actbankRecCreditCounts +" Value Expected  : "+expbankRecCreditCounts);
	       System.out.println("bankRecBankBal      : "+actbankRecBankBal      +" Value Expected  : "+expbankRecBankBal);
	       System.out.println("bankRecDifferr      : "+actbankRecDiff	      +" Value Expected  : "+expbankRecDiff);

		if(actDebitAmtList.equalsIgnoreCase(expDebitAmtList)&&actCreditAmtList.equalsIgnoreCase(expCreditAmtList)&&
				 actBookBal.equalsIgnoreCase(expBookBal) &&
				actbankRecOutDebits.equalsIgnoreCase(expbankRecOutDebits) && actbankRecOutCredits.equalsIgnoreCase(expbankRecOutCredits) &&
				actbankRecClearedBal.equalsIgnoreCase(expbankRecClearedBal) && actbankRecDebitCounts.equalsIgnoreCase(expbankRecDebitCounts) &&
				actbankRecOpenBal.equalsIgnoreCase(expbankRecBankBal)&&
				actbankRecCreditCounts.equalsIgnoreCase(expbankRecCreditCounts) && actbankRecBankBal.equalsIgnoreCase(expbankRecBankBal) 
				&& actbankRecDiff.equalsIgnoreCase(expbankRecDiff))
		{
			return true;
		}
		else
		{
			return false;
		}

	}
	
	
	
	public boolean checkSortOrderAsDocTypeChequeNumWithClearedDebitsinBankReconciliationReport() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		
		//click(BRSSelectStatusDrpdwn);
		Select s1=new Select(BRSSelectStatusDrpdwn);
		s1.selectByValue("1");
		Thread.sleep(3000);
		BRSSelectStatusDrpdwn.sendKeys(Keys.TAB);
		
		//click(BRSSelectCRDRDrpdwn);
		Select s2=new Select(BRSSelectCRDRDrpdwn);
		s2.selectByValue("0");
		Thread.sleep(3000);
		BRSSelectCRDRDrpdwn.sendKeys(Keys.TAB);
		
		click(LoadBtn);
		Thread.sleep(6000);
		
		
		String expMsg="No records exists with this filter condition";
		String actMsg=checkValidationMessage(expMsg);
		
		if( actMsg.equalsIgnoreCase(expMsg))
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	
	
	public boolean checkSortOrderAsDocTypeChequeNumWithClearedCreditsinBankReconciliationReport() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		//click(BRSSelectCRDRDrpdwn);
		Select s2=new Select(BRSSelectCRDRDrpdwn);
		s2.selectByValue("1");
		Thread.sleep(3000);
		BRSSelectCRDRDrpdwn.sendKeys(Keys.TAB);
		
		click(LoadBtn);
		Thread.sleep(6000);
		
	
		String expMsg="No records exists with this filter condition";
		String actMsg=checkValidationMessage(expMsg);
		
		if( actMsg.equalsIgnoreCase(expMsg))
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	public boolean checkSavingReceiptsVoucherWithUSD() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		Thread.sleep(2000);
		click(serachMenuTextHomePage);
		
		serachMenuTextHomePage.sendKeys("Receipts");
		Thread.sleep(2000);
		serachMenuTextHomePage.sendKeys(Keys.DOWN);
		serachMenuTextHomePage.sendKeys(Keys.ENTER);
		//click(searchMenuTextClick);
		 
		Thread.sleep(6000);
		new WebDriverWait(getDriver(), 300).until(ExpectedConditions.visibilityOf(newBtn));
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(newBtn));
		newBtn.click();
		Thread.sleep(4000);
		
		Thread.sleep(2000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNumberTxt));
		click(dateTxt);
		dateTxt.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
		
		 Calendar cal=Calendar.getInstance();
		 cal.add(Calendar.MONTH, -1);
		 cal.add(Calendar.DATE, -12);
		 SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		dateTxt.sendKeys(f.format(cal.getTime()));
		dateTxt.sendKeys(Keys.TAB);
		
		click(caskBankAccountTxt);
		caskBankAccountTxt.sendKeys("Cheque Discounting");
		Thread.sleep(2000);
		caskBankAccountTxt.sendKeys(Keys.TAB);
		
		click(maturityDateTxt);
		maturityDateTxt.sendKeys(f.format(cal.getTime()));
		maturityDateTxt.sendKeys(Keys.TAB);
		
		//click(voucherHeaderCurrency);
		voucherHeaderCurrency.sendKeys(Keys.SPACE);
		voucherHeaderCurrency.sendKeys("USD");
		Thread.sleep(2000);
		voucherHeaderCurrency.sendKeys(Keys.TAB);
		
		click(departmentTxt);
		departmentTxt.sendKeys("INDIA");
		Thread.sleep(2000);
		departmentTxt.sendKeys(Keys.TAB);
		
		
		
		click(receipts_ChequeNoTxt);
		receipts_ChequeNoTxt.sendKeys("Rct015");
		Thread.sleep(2000);
		receipts_ChequeNoTxt.sendKeys(Keys.TAB);
		
		
		
		click(select1stRow_1stColumn);
		
		click(enter_DebitACTxt);
		enter_DebitACTxt.sendKeys("Customer C");
		Thread.sleep(2000);
		enter_DebitACTxt.sendKeys(Keys.TAB);
		
		
		enter_Amount.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
		enter_Amount.sendKeys("865.24");
		Thread.sleep(2000);
		enter_Amount.sendKeys(Keys.TAB);
		
		
		
		click(billRefNewReferenceTxt);
		click(pickBtn);
		Thread.sleep(2000);
		
		click(voucher_OkBtn);
		Thread.sleep(4000);
		
		click(saveBtn);
		Thread.sleep(2000);
		
		String expMsg="Voucher saved successfully";
		String expMsg1=": 10";
		
		String actMsg=checkValidationMessage(expMsg);
		
		System.out.println("Actual Message		"	+	actMsg		+	"Expected Message	"	+	expMsg);
		
		if(actMsg.startsWith(expMsg) && actMsg.endsWith(expMsg1))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	@FindBy(xpath="//*[@id='BRTable_body']/tr[1]/td[7]")
	public static WebElement BRS_1stRowDebitAmt;
	
	
	public boolean checkTransactionCurrencyinBankReconcillationReport() throws InterruptedException
	{
		
		click(previousBtn);
		Thread.sleep(6000);
		
		double exchangeValue= Double.parseDouble(exchangeRateTxt.getAttribute("value"));
		click(select1stRow_2ndColumn);
		double amount= Double.parseDouble(enter_Amount.getAttribute("value"));
		
		System.out.println(exchangeValue + "   "+ amount);
		
		Thread.sleep(2500);
		click(serachMenuTextHomePage);
		
		serachMenuTextHomePage.sendKeys("Bank Reconciliation");
		Thread.sleep(2000);
		serachMenuTextHomePage.sendKeys(Keys.ENTER);
		
		Thread.sleep(6000);
		
		click(reportbankTxt);
		reportbankTxt.sendKeys("Cheque Discounting");
		Thread.sleep(1500);
		reportbankTxt.sendKeys(Keys.TAB);
		Thread.sleep(6000);
		
		Select s=new Select(BRSortDrpdwn);
		s.selectByValue("1");
		Thread.sleep(2000);
		BRSortDrpdwn.sendKeys(Keys.TAB);
		
		//click(BRSSelectStatusDrpdwn);
		Select s1=new Select(BRSSelectStatusDrpdwn);
		s1.selectByValue("2");
		Thread.sleep(3000);
		BRSSelectStatusDrpdwn.sendKeys(Keys.TAB);
		
		//click(BRSSelectCRDRDrpdwn);
		Select s2=new Select(BRSSelectCRDRDrpdwn);
		s2.selectByValue("0");
		Thread.sleep(3000);
		BRSSelectCRDRDrpdwn.sendKeys(Keys.TAB);

		
		
		click(LoadBtn);
		Thread.sleep(12000);
		getAction().moveToElement(BRS_1stRowDebitAmt).build().perform();
		System.out.println("*********"+BRS_1stRowDebitAmt.getText());
		String actDebitAmount=BRS_1stRowDebitAmt.getText().replace(",", "");
		//String expDebitAmount= String.valueOf(exchangeValue * amount);
		String expDebitAmount=String.format("%.2f", exchangeValue * amount);
		
		System.out.println("Actual Debit Amount		"	+	actDebitAmount);
		System.out.println("Expect Debit Amount		"	+	expDebitAmount);
		
		
		if(actDebitAmount.contentEquals(expDebitAmount))
		{
			return true;
		}
		else
		{
			return false;
		}
		
		
	}
	
	
	
	public boolean checkSavingPaymentsVoucherWithUSD() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		Thread.sleep(2000);
		click(serachMenuTextHomePage);
		
		serachMenuTextHomePage.sendKeys("Payments");
		Thread.sleep(2000);
		serachMenuTextHomePage.sendKeys(Keys.DOWN);
		serachMenuTextHomePage.sendKeys(Keys.ENTER);
		//click(searchMenuTextClick);
		 
		Thread.sleep(6000);
		new WebDriverWait(getDriver(), 300).until(ExpectedConditions.visibilityOf(newBtn));
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(newBtn));
		newBtn.click();
		Thread.sleep(4000);
		
		Thread.sleep(2000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNumberTxt));
		click(dateTxt);
		dateTxt.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
		
		 Calendar cal=Calendar.getInstance();
		 cal.add(Calendar.MONTH, -1);
		 cal.add(Calendar.DATE, -12);
		 SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		dateTxt.sendKeys(f.format(cal.getTime()));
		dateTxt.sendKeys(Keys.TAB);
		
		click(caskBankAccountTxt);
		caskBankAccountTxt.sendKeys("Cheque Discounting");
		Thread.sleep(2000);
		caskBankAccountTxt.sendKeys(Keys.TAB);
		
		click(maturityDateTxt);
		maturityDateTxt.sendKeys(f.format(cal.getTime()));
		maturityDateTxt.sendKeys(Keys.TAB);
		
		//click(voucherHeaderCurrency);
		voucherHeaderCurrency.sendKeys(Keys.SPACE);
		voucherHeaderCurrency.sendKeys("USD");
		Thread.sleep(2000);
		voucherHeaderCurrency.sendKeys(Keys.TAB);
		
		click(departmentTxt);
		departmentTxt.sendKeys("INDIA");
		Thread.sleep(2000);
		departmentTxt.sendKeys(Keys.TAB);
		
		
		
		click(payments_ChequeNoTxt);
		payments_ChequeNoTxt.sendKeys("Pmt019");
		Thread.sleep(2000);
		payments_ChequeNoTxt.sendKeys(Keys.TAB);
		
		
		
		click(select1stRow_1stColumn);
		
		click(enter_DebitACTxt);
		enter_DebitACTxt.sendKeys("Customer C");
		Thread.sleep(2000);
		enter_DebitACTxt.sendKeys(Keys.TAB);
		
		
		enter_Amount.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
		enter_Amount.sendKeys("1058.63");
		Thread.sleep(2000);
		enter_Amount.sendKeys(Keys.TAB);
		
		
		
		click(billRefNewReferenceTxt);
		click(pickBtn);
		Thread.sleep(2000);
		
		click(voucher_OkBtn);
		Thread.sleep(4000);
		
		click(saveBtn);
		Thread.sleep(2000);
		
		String expMsg="Voucher saved successfully";
		String expMsg1=": 13";
		
		String actMsg=checkValidationMessage(expMsg);
		
		System.out.println("Actual Message		"	+	actMsg		+	"Expected Message	"	+	expMsg);
		
		if(actMsg.startsWith(expMsg) && actMsg.endsWith(expMsg1))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	@FindBy(xpath="//*[@id='BRTable_body']/tr[1]/td[8]")
	public static WebElement BRS_1stRowCreditAmt;
	
	
	public boolean checkTransactionCurrencyforCreditinBankReconcillationReport() throws InterruptedException
	{
		
		click(previousBtn);
		Thread.sleep(4000);
		
		double exchangeValue= Double.parseDouble(exchangeRateTxt.getAttribute("value"));
		click(select1stRow_2ndColumn);
		double amount= Double.parseDouble(enter_Amount.getAttribute("value"));
		
		System.out.println(exchangeValue + "   "+ amount);
		
		click(serachMenuTextHomePage);
		
		serachMenuTextHomePage.sendKeys("Bank Reconciliation");
		Thread.sleep(2000);
		serachMenuTextHomePage.sendKeys(Keys.ENTER);
		
		Thread.sleep(6000);
		
		click(reportbankTxt);
		reportbankTxt.sendKeys("Cheque Discounting");
		Thread.sleep(1500);
		reportbankTxt.sendKeys(Keys.TAB);
		Thread.sleep(4000);
		
		
		Select s=new Select(BRSortDrpdwn);
		s.selectByValue("1");
		Thread.sleep(2000);
		BRSortDrpdwn.sendKeys(Keys.TAB);
		
		//click(BRSSelectStatusDrpdwn);
		Select s1=new Select(BRSSelectStatusDrpdwn);
		s1.selectByValue("2");
		Thread.sleep(3000);
		BRSSelectStatusDrpdwn.sendKeys(Keys.TAB);
		
	
		Select s2=new Select(BRSSelectCRDRDrpdwn);
		s2.selectByValue("1");
		Thread.sleep(3000);
		BRSSelectCRDRDrpdwn.sendKeys(Keys.TAB);
		
		click(LoadBtn);
		Thread.sleep(8000);
		
		System.out.println(BRS_1stRowCreditAmt.getAttribute("value"));
		
		String actCreditAmount=BRS_1stRowCreditAmt.getText().replace(",", "");
		
		String expCreditAmount=String.format("%.2f", exchangeValue * amount);
		
		System.out.println("Actual Credit Amount		"	+	actCreditAmount);
		System.out.println("Expect Credit Amount		"	+	expCreditAmount);
		
		
		if(actCreditAmount.contentEquals(expCreditAmount))
		{
			return true;
		}
		else
		{
			return false;
		}
		
		
	}
	
	@FindBy(xpath="//*[@id='BRTable_body']/tr[2]/td[4]")
	public static WebElement brs_2ndRowCleranceDate;
	
	@FindBy(xpath="//*[@id='BRTable_body']/tr[1]/td[2]")
	public static WebElement brs_1stRow2ndCol;
	
	@FindBy(xpath="//*[@id='BRTable_body']/tr[2]/td[2]")
	public static WebElement brs_2ndRow2ndCol;
	
	@FindBy(xpath="//*[@id='Clearancedate1']")
	public static WebElement brs_CleranceDateTxt;
	
	public boolean checkCleranceDateisNotAcceptingGreaterthanCurrentDateinBRS() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		
		click(clearBtn);
		Thread.sleep(1500);
		
	click(serachMenuTextHomePage);
		
		serachMenuTextHomePage.sendKeys("Bank Reconciliation");
		Thread.sleep(2000);
		serachMenuTextHomePage.sendKeys(Keys.ENTER);
		
		Thread.sleep(3500);
		
		click(reportbankTxt);
		reportbankTxt.sendKeys("Cheque Discounting");
		Thread.sleep(1500);
		reportbankTxt.sendKeys(Keys.TAB);
		
		
		Select s=new Select(BRSSelectStatusDrpdwn);
		s.selectByValue("2");
		Thread.sleep(1500);
		BRSSelectStatusDrpdwn.sendKeys(Keys.TAB);
		
		
		Select s1=new Select(BRSSelectCRDRDrpdwn);
		s1.selectByValue("2");
		Thread.sleep(1500);
		BRSSelectCRDRDrpdwn.sendKeys(Keys.TAB);
		
		click(LoadBtn);
		Thread.sleep(8000);
		
		 Calendar cal=Calendar.getInstance();
		
		 cal.add(Calendar.DATE, 7);
		 SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		
		click(brs_2ndRowCleranceDate);
		click(brs_CleranceDateTxt);
		brs_CleranceDateTxt.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
		brs_CleranceDateTxt.sendKeys(f.format(cal.getTime()));
		
		click(brs_2ndRow2ndCol);
		
		String expMsg="Clearance date cannot be future date.";
		String actMsg= checkValidationMessage(expMsg);
		
		click(brs_2ndRow2ndCol);
		
		if(actMsg.equalsIgnoreCase(expMsg))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	public boolean checkClearingDebitAmountinBRS() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		
		click(clearBtn);
		Thread.sleep(2000);
		
		click(reportbankTxt);
		reportbankTxt.sendKeys("Cheque Discounting");
		Thread.sleep(1500);
		reportbankTxt.sendKeys(Keys.TAB);
		
		Select s=new Select(BRSSelectStatusDrpdwn);
		s.selectByValue("2");
		Thread.sleep(1500);
		BRSSelectStatusDrpdwn.sendKeys(Keys.TAB);
		
		
		Select s1=new Select(BRSSelectCRDRDrpdwn);
		s1.selectByValue("2");
		Thread.sleep(1500);
		BRSSelectCRDRDrpdwn.sendKeys(Keys.TAB);
		
		click(LoadBtn);
		Thread.sleep(8000);
		
		String debitAmt=BRS_1stRowDebitAmt.getText();
		
		System.out.println(debitAmt);
		
		 	String actBookBal=bankRecBookBal.getText();
		 	String expBookBal="13,537.30 Cr";

			String actbankRecOutDebits=bankRecOutDebits.getText();
		    String expbankRecOutDebits="60,566.80 Dr";
		
			String actbankRecOutCredits=bankRecOutCredits.getText();
		    String expbankRecOutCredits="74,104.10 Cr";
		
			String actbankRecClearedBal=bankRecClearedBal.getText();
		    String expbankRecClearedBal="0.00";
		
			String actbankRecOpenBal=bankRecOpenBal.getText();
		    String expbankRecOpenBal="0.00";
			

			String actbankRecDebitCounts=bankRecDebitCounts.getText();
		    String expbankRecDebitCounts="1";
			
			String actbankRecCreditCounts=bankRecCreditCounts.getText();
		    String expbankRecCreditCounts="1";
		    
			String actbankRecBankBal=bankRecBankBal.getAttribute("value");
		    String expbankRecBankBal="0.00";
			
		    String actbankRecDiff=bankRecDif.getText();
		    String expbankRecDiff="0.00";


		   System.out.println("**********************************checkBankReconciliationReport*****************************************");
		   System.out.println("BookBal             : "+actBookBal             +" Value Expected  : "+expBookBal);
	       System.out.println("bankRecOutDebits    : "+actbankRecOutDebits    +" Value Expected  : "+expbankRecOutDebits);
	       System.out.println("bankRecOutCredits   : "+actbankRecOutCredits   +" Value Expected  : "+expbankRecOutCredits);
	       System.out.println("bankRecClearedBal   : "+actbankRecClearedBal   +" Value Expected  : "+expbankRecClearedBal);
	       System.out.println("Opening Bal         : "+actbankRecOpenBal      +" Value Expected  : "+expbankRecOpenBal);
	       System.out.println("bankRecDebitCounts  : "+actbankRecDebitCounts  +" Value Expected  : "+expbankRecDebitCounts);
	       System.out.println("bankRecCreditCounts : "+actbankRecCreditCounts +" Value Expected  : "+expbankRecCreditCounts);
	       System.out.println("bankRecBankBal      : "+actbankRecBankBal      +" Value Expected  : "+expbankRecBankBal);
	       System.out.println("bankRecDifferr      : "+actbankRecDiff	      +" Value Expected  : "+expbankRecDiff);

		getAction().moveToElement(brs_1stRow2ndCol).build().perform();
		Thread.sleep(2000);
		click(brs_1stRow2ndCol);
		Thread.sleep(2000);
		
		String expMsg="Selected Vouchers Reconsolidated Successfully";
		String actMsg=checkValidationMessage(expMsg);
		
		String actBRSStatusAfterClear=brs_1stRow2ndCol.getText();
		String expBRSStatusAfterClear="Cleared";
		
		System.out.println("Actual BRS Status	"	+	actBRSStatusAfterClear);
		System.out.println("Expect BRS Status	"	+	expBRSStatusAfterClear);
		
		
		String actBookBal1=bankRecBookBal.getText();
	 	String expBookBal1="13,537.30 Cr";

		String actbankRecOutDebits1=bankRecOutDebits.getText();
	    String expbankRecOutDebits1="0.00 Dr";
	
		String actbankRecOutCredits1=bankRecOutCredits.getText();
	    String expbankRecOutCredits1="74,104.10 Cr";
	
		String actbankRecClearedBal1=bankRecClearedBal.getText();
	    String expbankRecClearedBal1="60,566.80Dr";
	
		String actbankRecOpenBal1=bankRecOpenBal.getText();
	    String expbankRecOpenBal1="0.00";
		

		String actbankRecDebitCounts1=bankRecDebitCounts.getText();
	    String expbankRecDebitCounts1="1";
		
		String actbankRecCreditCounts1=bankRecCreditCounts.getText();
	    String expbankRecCreditCounts1="1";
	    
		String actbankRecBankBal1=bankRecBankBal.getAttribute("value");
	    String expbankRecBankBal1="0.00";
		
	    
	    String actbankRecDiff1=bankRecDif.getText();
	    String expbankRecDiff1="-60,566.80";


	    System.out.println("**********************************checkBankReconciliationReportAfterCleared*****************************************");
		   System.out.println("BookBal             : "+actBookBal1             +" Value Expected  : "+expBookBal1);
	       System.out.println("bankRecOutDebits    : "+actbankRecOutDebits1    +" Value Expected  : "+expbankRecOutDebits1);
	       System.out.println("bankRecOutCredits   : "+actbankRecOutCredits1   +" Value Expected  : "+expbankRecOutCredits1);
	       System.out.println("bankRecClearedBal   : "+actbankRecClearedBal1   +" Value Expected  : "+expbankRecClearedBal1);
	       System.out.println("Opening Bal         : "+actbankRecOpenBal1      +" Value Expected  : "+expbankRecOpenBal1);
	       System.out.println("bankRecDebitCounts  : "+actbankRecDebitCounts1  +" Value Expected  : "+expbankRecDebitCounts1);
	       System.out.println("bankRecCreditCounts : "+actbankRecCreditCounts1 +" Value Expected  : "+expbankRecCreditCounts1);
	       System.out.println("bankRecBankBal      : "+actbankRecBankBal1      +" Value Expected  : "+expbankRecBankBal1);
	       System.out.println("bankRecDifferr      : "+actbankRecDiff1 	      +" Value Expected  : "+expbankRecDiff1);

	       Thread.sleep(2000);
	       getAction().moveToElement(brs_1stRow2ndCol).build().perform();
			Thread.sleep(2000);
			click(brs_1stRow2ndCol);
			Thread.sleep(2000);
			
			String expMsg1="Selected Vouchers Reconsolidated Successfully";
			String actMsg1=checkValidationMessage(expMsg1);
			
			String actBRSStatusAfterCleared=brs_1stRow2ndCol.getText();
			String expBRSStatusAfterCleared="Pending";
			
			System.out.println("Actual BRS Status	"	+	actBRSStatusAfterCleared);
			System.out.println("Expect BRS Status	"	+	expBRSStatusAfterCleared);
			
	       
	       if(actMsg.equalsIgnoreCase(expMsg)&& actBRSStatusAfterClear.equalsIgnoreCase(expBRSStatusAfterClear) &&
	    		   actBRSStatusAfterCleared.equalsIgnoreCase(expBRSStatusAfterCleared)&&actBookBal.equalsIgnoreCase(expBookBal) &&
				actbankRecOutDebits.equalsIgnoreCase(expbankRecOutDebits) && actbankRecOutCredits.equalsIgnoreCase(expbankRecOutCredits) &&
				actbankRecClearedBal.equalsIgnoreCase(expbankRecClearedBal) && actbankRecDebitCounts.equalsIgnoreCase(expbankRecDebitCounts) &&
				actbankRecCreditCounts.equalsIgnoreCase(expbankRecCreditCounts) && actbankRecBankBal.equalsIgnoreCase(expbankRecBankBal) 
				&& actbankRecDiff.equalsIgnoreCase(expbankRecDiff) &&actBookBal1.equalsIgnoreCase(expBookBal1) &&
				actbankRecOutDebits1.equalsIgnoreCase(expbankRecOutDebits1) && actbankRecOutCredits1.equalsIgnoreCase(expbankRecOutCredits1) &&
				actbankRecClearedBal1.equalsIgnoreCase(expbankRecClearedBal1) && actbankRecDebitCounts1.equalsIgnoreCase(expbankRecDebitCounts1) &&
				actbankRecCreditCounts1.equalsIgnoreCase(expbankRecCreditCounts1) && actbankRecBankBal1.equalsIgnoreCase(expbankRecBankBal1)
				&& actbankRecDiff1.equalsIgnoreCase(expbankRecDiff1))
	       {
	    	   return true;
	       }
	       else
	       {
	    	   return false;
	       }
		    
	}
	
	
	public boolean checkClearingCreditAmountinBRS() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		
		click(clearBtn);
		Thread.sleep(2000);
		
		click(reportbankTxt);
		reportbankTxt.sendKeys("Cheque Discounting");
		Thread.sleep(1500);
		reportbankTxt.sendKeys(Keys.TAB);
		
		Select s=new Select(BRSSelectStatusDrpdwn);
		s.selectByValue("2");
		Thread.sleep(1500);
		BRSSelectStatusDrpdwn.sendKeys(Keys.TAB);
		
		
		Select s1=new Select(BRSSelectCRDRDrpdwn);
		s1.selectByValue("2");
		Thread.sleep(1500);
		BRSSelectCRDRDrpdwn.sendKeys(Keys.TAB);
		
		click(LoadBtn);
		Thread.sleep(10000);
		
				
		 	String actBookBal=bankRecBookBal.getText();
		 	String expBookBal="13,537.30 Cr";

			String actbankRecOutDebits=bankRecOutDebits.getText();
		    String expbankRecOutDebits="60,566.80 Dr";
		
			String actbankRecOutCredits=bankRecOutCredits.getText();
		    String expbankRecOutCredits="74,104.10 Cr";
		
			String actbankRecClearedBal=bankRecClearedBal.getText();
		    String expbankRecClearedBal="0.00";
		
			String actbankRecOpenBal=bankRecOpenBal.getText();
		    String expbankRecOpenBal="0.00";
			

			String actbankRecDebitCounts=bankRecDebitCounts.getText();
		    String expbankRecDebitCounts="1";
			
			String actbankRecCreditCounts=bankRecCreditCounts.getText();
		    String expbankRecCreditCounts="1";
		    
			String actbankRecBankBal=bankRecBankBal.getAttribute("value");
		    String expbankRecBankBal="0.00";
			
		    String actbankRecDiff=bankRecDif.getText();
		    String expbankRecDiff="0.00";


		   System.out.println("**********************************checkBankReconciliationReport*****************************************");
		   System.out.println("BookBal             : "+actBookBal             +" Value Expected  : "+expBookBal);
	       System.out.println("bankRecOutDebits    : "+actbankRecOutDebits    +" Value Expected  : "+expbankRecOutDebits);
	       System.out.println("bankRecOutCredits   : "+actbankRecOutCredits   +" Value Expected  : "+expbankRecOutCredits);
	       System.out.println("bankRecClearedBal   : "+actbankRecClearedBal   +" Value Expected  : "+expbankRecClearedBal);
	       System.out.println("Opening Bal         : "+actbankRecOpenBal      +" Value Expected  : "+expbankRecOpenBal);
	       System.out.println("bankRecDebitCounts  : "+actbankRecDebitCounts  +" Value Expected  : "+expbankRecDebitCounts);
	       System.out.println("bankRecCreditCounts : "+actbankRecCreditCounts +" Value Expected  : "+expbankRecCreditCounts);
	       System.out.println("bankRecBankBal      : "+actbankRecBankBal      +" Value Expected  : "+expbankRecBankBal);
	       System.out.println("bankRecDifferr      : "+actbankRecDiff	      +" Value Expected  : "+expbankRecDiff);

		getAction().moveToElement(brs_2ndRow2ndCol).build().perform();
		Thread.sleep(2000);
		click(brs_2ndRow2ndCol);
		Thread.sleep(2000);
		
		String expMsg="Selected Vouchers Reconsolidated Successfully";
		String actMsg=checkValidationMessage(expMsg);
		
		String actBRSStatusAfterClear=brs_2ndRow2ndCol.getText();
		String expBRSStatusAfterClear="Cleared";
		
		System.out.println("Actual BRS Status	"	+	actBRSStatusAfterClear);
		System.out.println("Expect BRS Status	"	+	expBRSStatusAfterClear);
		
		
		String actBookBal1=bankRecBookBal.getText();
	 	String expBookBal1="13,537.30 Cr";

		String actbankRecOutDebits1=bankRecOutDebits.getText();
	    String expbankRecOutDebits1="60,566.80 Dr";
	
		String actbankRecOutCredits1=bankRecOutCredits.getText();
	    String expbankRecOutCredits1="0.00 Cr";
	
		String actbankRecClearedBal1=bankRecClearedBal.getText();
	    String expbankRecClearedBal1="74,104.10Cr";
	
		String actbankRecOpenBal1=bankRecOpenBal.getText();
	    String expbankRecOpenBal1="0.00";
		

		String actbankRecDebitCounts1=bankRecDebitCounts.getText();
	    String expbankRecDebitCounts1="1";
		
		String actbankRecCreditCounts1=bankRecCreditCounts.getText();
	    String expbankRecCreditCounts1="1";
	    
		String actbankRecBankBal1=bankRecBankBal.getAttribute("value");
	    String expbankRecBankBal1="0.00";
		
	    String actbankRecDiff1=bankRecDif.getText();
	    String expbankRecDiff1="-74,104.10";


		
	    System.out.println("**********************************checkBankReconciliationReportAfterCleared*****************************************");
		   System.out.println("BookBal             : "+actBookBal1             +" Value Expected  : "+expBookBal1);
	       System.out.println("bankRecOutDebits    : "+actbankRecOutDebits1    +" Value Expected  : "+expbankRecOutDebits1);
	       System.out.println("bankRecOutCredits   : "+actbankRecOutCredits1   +" Value Expected  : "+expbankRecOutCredits1);
	       System.out.println("bankRecClearedBal   : "+actbankRecClearedBal1   +" Value Expected  : "+expbankRecClearedBal1);
	       System.out.println("Opening Bal         : "+actbankRecOpenBal1      +" Value Expected  : "+expbankRecOpenBal1);
	       System.out.println("bankRecDebitCounts  : "+actbankRecDebitCounts1  +" Value Expected  : "+expbankRecDebitCounts1);
	       System.out.println("bankRecCreditCounts : "+actbankRecCreditCounts1 +" Value Expected  : "+expbankRecCreditCounts1);
	       System.out.println("bankRecBankBal      : "+actbankRecBankBal1      +" Value Expected  : "+expbankRecBankBal1);
	       System.out.println("bankRecDifferr      : "+actbankRecDiff1	      +" Value Expected  : "+expbankRecDiff1);

	       
	       getAction().moveToElement(brs_2ndRow2ndCol).build().perform();
			Thread.sleep(2000);
			click(brs_2ndRow2ndCol);
			Thread.sleep(2000);
			
			String expMsg1="Selected Vouchers Reconsolidated Successfully";
			String actMsg1=checkValidationMessage(expMsg1);
			
			String actBRSStatusAfterCleared=brs_2ndRow2ndCol.getText();
			String expBRSStatusAfterCleared="Pending";
			
			System.out.println("Actual BRS Status	"	+	actBRSStatusAfterCleared);
			System.out.println("Expect BRS Status	"	+	expBRSStatusAfterCleared);
	       
	       if(actMsg.equalsIgnoreCase(expMsg)&& actBRSStatusAfterClear.equalsIgnoreCase(expBRSStatusAfterClear) 
	    	 && actBRSStatusAfterCleared.equalsIgnoreCase(expBRSStatusAfterCleared)&&actBookBal.equalsIgnoreCase(expBookBal) &&
				actbankRecOutDebits.equalsIgnoreCase(expbankRecOutDebits) && actbankRecOutCredits.equalsIgnoreCase(expbankRecOutCredits) &&
				actbankRecClearedBal.equalsIgnoreCase(expbankRecClearedBal) && actbankRecDebitCounts.equalsIgnoreCase(expbankRecDebitCounts) &&
				actbankRecCreditCounts.equalsIgnoreCase(expbankRecCreditCounts) && actbankRecBankBal.equalsIgnoreCase(expbankRecBankBal) 
				&& actbankRecDiff.equalsIgnoreCase(expbankRecDiff)&&actBookBal1.equalsIgnoreCase(expBookBal1) &&
				actbankRecOutDebits1.equalsIgnoreCase(expbankRecOutDebits1) && actbankRecOutCredits1.equalsIgnoreCase(expbankRecOutCredits1) &&
				actbankRecClearedBal1.equalsIgnoreCase(expbankRecClearedBal1) && actbankRecDebitCounts1.equalsIgnoreCase(expbankRecDebitCounts1) &&
				actbankRecCreditCounts1.equalsIgnoreCase(expbankRecCreditCounts1) && actbankRecBankBal1.equalsIgnoreCase(expbankRecBankBal1)
				&& actbankRecDiff1.equalsIgnoreCase(expbankRecDiff1))
	       {
	    	   return true;
	       }
	       else
	       {
	    	   return false;
	       }
		    
	}
	
	
	@FindBy(xpath="//*[@id='BRTable_body']/tr/td[5]")
	public static List<WebElement> brsDocNoList;
	
	@FindBy(xpath="//*[@id='BRTable_body']/tr/td[2]")
	public static List<WebElement> brsStatusList;
	
	public boolean checkBankReconciliationReportforDebitCreditClerance() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{

		
		click(clearBtn);
		Thread.sleep(2000);
		
		click(reportbankTxt);
		reportbankTxt.sendKeys("HDFC");
		Thread.sleep(1500);
		reportbankTxt.sendKeys(Keys.TAB);
		
		Select s=new Select(BRSSelectStatusDrpdwn);
		s.selectByValue("2");
		Thread.sleep(1500);
		BRSSelectStatusDrpdwn.sendKeys(Keys.TAB);
		
		
		Select s1=new Select(BRSSelectCRDRDrpdwn);
		s1.selectByValue("2");
		Thread.sleep(1500);
		BRSSelectCRDRDrpdwn.sendKeys(Keys.TAB);
		
		click(LoadBtn);
		Thread.sleep(10000);
		
				
		 	String actBookBal=bankRecBookBal.getText();
		 	String expBookBal="9,484.31 Cr";

			String actbankRecOutDebits=bankRecOutDebits.getText();
		    String expbankRecOutDebits="5,855.04 Dr";
		
			String actbankRecOutCredits=bankRecOutCredits.getText();
		    String expbankRecOutCredits="15,339.35 Cr";
		
			String actbankRecClearedBal=bankRecClearedBal.getText();
		    String expbankRecClearedBal="0.00";
		
			String actbankRecOpenBal=bankRecOpenBal.getText();
		    String expbankRecOpenBal="0.00";
			

			String actbankRecDebitCounts=bankRecDebitCounts.getText();
		    String expbankRecDebitCounts="7";
			
			String actbankRecCreditCounts=bankRecCreditCounts.getText();
		    String expbankRecCreditCounts="13";
		    
			String actbankRecBankBal=bankRecBankBal.getAttribute("value");
		    String expbankRecBankBal="0.00";
			
		    String actbankRecDiff=bankRecDif.getText();
		    String expbankRecDiff="0.00";


		   System.out.println("**********************************checkBankReconciliationReport*****************************************");
		   System.out.println("BookBal             : "+actBookBal             +" Value Expected  : "+expBookBal);
	       System.out.println("bankRecOutDebits    : "+actbankRecOutDebits    +" Value Expected  : "+expbankRecOutDebits);
	       System.out.println("bankRecOutCredits   : "+actbankRecOutCredits   +" Value Expected  : "+expbankRecOutCredits);
	       System.out.println("bankRecClearedBal   : "+actbankRecClearedBal   +" Value Expected  : "+expbankRecClearedBal);
	       System.out.println("Opening Bal         : "+actbankRecOpenBal      +" Value Expected  : "+expbankRecOpenBal);
	       System.out.println("bankRecDebitCounts  : "+actbankRecDebitCounts  +" Value Expected  : "+expbankRecDebitCounts);
	       System.out.println("bankRecCreditCounts : "+actbankRecCreditCounts +" Value Expected  : "+expbankRecCreditCounts);
	       System.out.println("bankRecBankBal      : "+actbankRecBankBal      +" Value Expected  : "+expbankRecBankBal);
	       System.out.println("bankRecDifferr      : "+actbankRecDiff	      +" Value Expected  : "+expbankRecDiff);
 
	     for(int i=0;i<brsDocNoList.size();i++)  
	     {
	    	 getAction().moveToElement(brsDocNoList.get(i)).build().perform();
	    	 if(brsDocNoList.get(i).getText().equalsIgnoreCase("Rct:7") || brsDocNoList.get(i).getText().equalsIgnoreCase("Pmt:6") )
	    	 {
	    		 brsStatusList.get(i).click();
	    	 }
	     }
	     
		
		
		String expMsg="Selected Vouchers Reconsolidated Successfully";
		String actMsg=checkValidationMessage(expMsg);
		
		ArrayList<String>clearedArray=new ArrayList<String>();
		
		 for(int i=0;i<brsDocNoList.size();i++)  
	     {
	    	 getAction().moveToElement(brsDocNoList.get(i)).build().perform();
	    	 if(brsDocNoList.get(i).getText().equalsIgnoreCase("Rct:7") || brsDocNoList.get(i).getText().equalsIgnoreCase("Pmt:6") )
	    	 {
	    		 clearedArray.add(brsStatusList.get(i).getText());
	    	 }
	     }
		
		String actBRSStatusAfterClear=clearedArray.toString();
		String expBRSStatusAfterClear="[Cleared, Cleared]";
		
		System.out.println("Actual BRS Status	"	+	actBRSStatusAfterClear);
		System.out.println("Expect BRS Status	"	+	expBRSStatusAfterClear);
		
		
		String actBookBal1=bankRecBookBal.getText();
	 	String expBookBal1="9,484.31 Cr";

		String actbankRecOutDebits1=bankRecOutDebits.getText();
	    String expbankRecOutDebits1="5,181.95 Dr";
	
		String actbankRecOutCredits1=bankRecOutCredits.getText();
	    String expbankRecOutCredits1="11,531.54 Cr";
	
		String actbankRecClearedBal1=bankRecClearedBal.getText();
	    String expbankRecClearedBal1="3,134.72Cr";
	
		String actbankRecOpenBal1=bankRecOpenBal.getText();
	    String expbankRecOpenBal1="0.00";
		

		String actbankRecDebitCounts1=bankRecDebitCounts.getText();
	    String expbankRecDebitCounts1="7";
		
		String actbankRecCreditCounts1=bankRecCreditCounts.getText();
	    String expbankRecCreditCounts1="13";
	    
		String actbankRecBankBal1=bankRecBankBal.getAttribute("value");
	    String expbankRecBankBal1="0.00";
		
	    String actbankRecDiff1=bankRecDif.getText();
	    String expbankRecDiff1="-3,134.72";


		
	    System.out.println("**********************************checkBankReconciliationReportAfterCleared*****************************************");
		   System.out.println("BookBal             : "+actBookBal1             +" Value Expected  : "+expBookBal1);
	       System.out.println("bankRecOutDebits    : "+actbankRecOutDebits1    +" Value Expected  : "+expbankRecOutDebits1);
	       System.out.println("bankRecOutCredits   : "+actbankRecOutCredits1   +" Value Expected  : "+expbankRecOutCredits1);
	       System.out.println("bankRecClearedBal   : "+actbankRecClearedBal1   +" Value Expected  : "+expbankRecClearedBal1);
	       System.out.println("Opening Bal         : "+actbankRecOpenBal1      +" Value Expected  : "+expbankRecOpenBal1);
	       System.out.println("bankRecDebitCounts  : "+actbankRecDebitCounts1  +" Value Expected  : "+expbankRecDebitCounts1);
	       System.out.println("bankRecCreditCounts : "+actbankRecCreditCounts1 +" Value Expected  : "+expbankRecCreditCounts1);
	       System.out.println("bankRecBankBal      : "+actbankRecBankBal1      +" Value Expected  : "+expbankRecBankBal1);
	       System.out.println("bankRecDifferr      : "+actbankRecDiff1	      +" Value Expected  : "+expbankRecDiff1);

	       
	      
	       
	       if(actMsg.equalsIgnoreCase(expMsg)&& actBRSStatusAfterClear.equalsIgnoreCase(expBRSStatusAfterClear) 
	    	 &&actBookBal.equalsIgnoreCase(expBookBal) &&
				actbankRecOutDebits.equalsIgnoreCase(expbankRecOutDebits) && actbankRecOutCredits.equalsIgnoreCase(expbankRecOutCredits) &&
				actbankRecClearedBal.equalsIgnoreCase(expbankRecClearedBal) && actbankRecDebitCounts.equalsIgnoreCase(expbankRecDebitCounts) &&
				actbankRecCreditCounts.equalsIgnoreCase(expbankRecCreditCounts) && actbankRecBankBal.equalsIgnoreCase(expbankRecBankBal) 
				&& actbankRecDiff.equalsIgnoreCase(expbankRecDiff)&&actBookBal1.equalsIgnoreCase(expBookBal1) &&
				actbankRecOutDebits1.equalsIgnoreCase(expbankRecOutDebits1) && actbankRecOutCredits1.equalsIgnoreCase(expbankRecOutCredits1) &&
				actbankRecClearedBal1.equalsIgnoreCase(expbankRecClearedBal1) && actbankRecDebitCounts1.equalsIgnoreCase(expbankRecDebitCounts1) &&
				actbankRecCreditCounts1.equalsIgnoreCase(expbankRecCreditCounts1) && actbankRecBankBal1.equalsIgnoreCase(expbankRecBankBal1)
				&& actbankRecDiff1.equalsIgnoreCase(expbankRecDiff1))
	       {
	    	   return true;
	       }
	       else
	       {
	    	   return false;
	       }
		    
	
		
	}
	
	@FindBy(xpath="//*[@id='sortByColumn']")
	public static WebElement brsSortByColSelect;
	
	@FindBy(xpath="//*[@id='btnAdvFilter']")
	public static WebElement brsAdvFilter;
	
	
	@FindBy(xpath="//*[@id='83_0_AdvanceFilter_']//tbody/tr/td[1]/select")
	public static WebElement brsAdvFilterConditionSelect;
	
	@FindBy(xpath="//*[@id='83_0_AdvanceFilter_']/table/tbody/tr/td[2]/input")
	public static WebElement brsAdvFilterSelectField;
	
	@FindBy(xpath="//a[text()='Cheque Number']")
	public static WebElement brsAdvFilterChequeNumField;
	
	@FindBy(xpath="//a[text()='Debit Amount']")
	public static WebElement brsAdvFilterDebitAmtField;
	
	@FindBy(xpath="//a[text()='Credit Amount']")
	public static WebElement brsAdvFilterCreditAmtField;
	
	@FindBy(xpath="//a[text()='Clearance Date']")
	public static WebElement brsAdvFilterCleranceDateField;
	
	@FindBy(xpath="//a[text()='Document Date']")
	public static WebElement brsAdvFilterDocDateField;
	
			@FindBy(xpath="(//a[text()='Department'])[1]")
			public static WebElement brsAdvFilterDepartField;
			
			
			@FindBy(xpath="//*[@id='83_0_AdvanceFilter_']//tbody/tr/td[3]/select")
			public static WebElement brsAdvFilterSelectOperator;
			
			@FindBy(xpath="//*[@id='83_0_AdvanceFilter_']//tbody/tr/td[4]/select")
			public static WebElement brsAdvFilterCompareWith;
			
			@FindBy(xpath="//*[@id='83_0_AdvanceFilter_']//tbody/tr/td[5]/input")
			public static WebElement brsAdvFilterValueTxt;
			
			@FindBy(xpath="//*[@id='advancefilter_date_83_0']")
			public static WebElement brsAdvFilterDateValueTxt;
			
			@FindBy(xpath="//*[@id='advancefilter_master_83_0_']")
			public static WebElement brsAdvFilterDeptValueTxt;
			
			@FindBy(xpath="//*[@id='83_0_AdvanceFilter_']//tbody/tr[2]/td[1]/select")
			public static WebElement brsAdvFilter2ndRowConditionSelect;
			
			@FindBy(xpath="//*[@id='83_0_AdvanceFilter_']/table/tbody/tr[2]/td[2]/input")
			public static WebElement brsAdvFilter2ndRowSelectField;
			
			@FindBy(xpath="//*[@id='83_0_AdvanceFilter_']//tbody/tr[2]/td[3]/select")
			public static WebElement brsAdvFilter2ndRowSelectOperator;
			
			@FindBy(xpath="//*[@id='83_0_AdvanceFilter_']//tbody/tr[2]/td[4]/select")
			public static WebElement brsAdvFilter2ndRowCompareWith;
			
			@FindBy(xpath="//*[@id='83_0_AdvanceFilter_']//tbody/tr[2]/td[5]/input")
			public static WebElement brsAdvFilter2ndRowValueTxt;
			
			@FindBy(xpath="//*[@id='btnOk']")
			public static WebElement brsAdvFilterOkBtn;
			
			
			@FindBy(xpath="//*[@id='83_0_AdvanceFilter_']/table/tbody/tr[1]/td[6]/span")
			public static WebElement brsAdvFilterRow1RemoveCondition;
			
			@FindBy(xpath="//*[@id='83_0_AdvanceFilter_']/table/tbody/tr[2]/td[6]/span")
			public static WebElement brsAdvFilterRow2RemoveCondition;
			
			@FindBy(xpath="//*[@id='83_0_AdvanceFilter_']/table/tbody/tr/td[7]/span")
			public static WebElement brsAdvFilterAddCondition;
	
			
			public boolean checkAdvancedFilteronCleranceDateandDocumentDateinBRSReport() throws InterruptedException
			{
				
				click(clearBtn);
				Thread.sleep(2000);
				
				click(reportbankTxt);
				reportbankTxt.sendKeys("HDFC");
				Thread.sleep(1500);
				reportbankTxt.sendKeys(Keys.TAB);
				
				Select s=new Select(BRSSelectStatusDrpdwn);
				s.selectByValue("2");
				Thread.sleep(1500);
				BRSSelectStatusDrpdwn.sendKeys(Keys.TAB);
				
				
				Select s1=new Select(BRSSelectCRDRDrpdwn);
				s1.selectByValue("2");
				Thread.sleep(1500);
				BRSSelectCRDRDrpdwn.sendKeys(Keys.TAB);
				
				click(LoadBtn);
				Thread.sleep(10000);
				
				click(brsAdvFilter);
				Thread.sleep(2000);
				
				click(brsAdvFilterConditionSelect);
				Select s3=new Select(brsAdvFilterConditionSelect);
				s3.selectByVisibleText("Where");
				Thread.sleep(1000);
				brsAdvFilterConditionSelect.sendKeys(Keys.TAB);
				
				click(brsAdvFilterSelectField);
				click(brsAdvFilterCleranceDateField);
				
				//click(brsAdvFilterSelectOperator);
				Thread.sleep(2000);
				Select s4=new Select(brsAdvFilterSelectOperator);
				s4.selectByVisibleText("Greater than");
				Thread.sleep(4000);
				//brsAdvFilterSelectOperator.sendKeys(Keys.TAB);
				
				click(brsAdvFilterCompareWith);
				Select s5=new Select(brsAdvFilterCompareWith);
				s5.selectByVisibleText("value");
				Thread.sleep(1000);
				brsAdvFilterCompareWith.sendKeys(Keys.TAB);
				
				
				Calendar cal= Calendar.getInstance();
				cal.add(Calendar.MONTH, -2);
				cal.add(Calendar.DATE, -4);
				SimpleDateFormat f= new SimpleDateFormat("dd/MM/yyyy");
				
			//click(brsAdvFilterValueTxt);
			Thread.sleep(1000);
			click(brsAdvFilterDateValueTxt);
			Thread.sleep(2000);
			brsAdvFilterDateValueTxt.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
			brsAdvFilterDateValueTxt.sendKeys(f.format(cal.getTime()));
			Thread.sleep(2000);
			brsAdvFilterDateValueTxt.sendKeys(Keys.TAB);
			
			click(brsAdvFilterAddCondition);
			Thread.sleep(1000);
			
			click(brsAdvFilter2ndRowConditionSelect);
			Select s6=new Select(brsAdvFilter2ndRowConditionSelect);
			s6.selectByVisibleText("And");
			Thread.sleep(1000);
			brsAdvFilter2ndRowConditionSelect.sendKeys(Keys.TAB);
			
			click(brsAdvFilter2ndRowSelectField);
			click(brsAdvFilterDocDateField);
			
			//click(brsAdvFilter2ndRowSelectOperator);
			Thread.sleep(2000);
			Select s7=new Select(brsAdvFilter2ndRowSelectOperator);
			s7.selectByVisibleText("Less than");
			Thread.sleep(2000);
			//brsAdvFilter2ndRowSelectOperator.sendKeys(Keys.TAB);
			
			click(brsAdvFilter2ndRowCompareWith);
			Select s8=new Select(brsAdvFilter2ndRowCompareWith);
			s8.selectByVisibleText("value");
			Thread.sleep(1000);
			brsAdvFilter2ndRowCompareWith.sendKeys(Keys.TAB);
			
			
			Calendar cal1= Calendar.getInstance();
			cal1.add(Calendar.MONTH, -1);
			cal1.add(Calendar.DATE, -5);
			
			
		//click(brsAdvFilter2ndRowValueTxt);
		brsAdvFilterDateValueTxt.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
		brsAdvFilterDateValueTxt.sendKeys(f.format(cal1.getTime()));
		Thread.sleep(2000);
		brsAdvFilterDateValueTxt.sendKeys(Keys.TAB);
		
			
		click(brsAdvFilterOkBtn);
		Thread.sleep(6000);
		
		Calendar cal2=Calendar.getInstance();
		 cal2.add(Calendar.MONTH, -1);
		 cal2.add(Calendar.DATE, -12);
		 
		
		ArrayList<String>brsRow1ArrayList=new ArrayList<String>();
		for(int i=0;i<bankRecRow1List.size();i++)
		{
			brsRow1ArrayList.add(bankRecRow1List.get(i).getText());
		}
		
		String actBRSRow1List=brsRow1ArrayList.toString();
		String expBRSRow1List="[1, Pending, Rct004, "+f.format(cal2.getTime())+", Rct:8, "+f.format(cal2.getTime())+", 865.24, 0.00, Receipts, , , , ]";
		
		System.out.println("Actual Row1 List	"	+	actBRSRow1List);
		System.out.println("Expect Row1 List	"	+	expBRSRow1List);
		
		
		ArrayList<String>brsRow2ArrayList=new ArrayList<String>();
		for(int i=0;i<bankRecRow2List.size();i++)
		{
			brsRow2ArrayList.add(bankRecRow2List.get(i).getText());
		}
		
		String actBRSRow2List=brsRow2ArrayList.toString();
		String expBRSRow2List="[2, Pending, Rct006, "+f.format(cal2.getTime())+", Rct:9, "+f.format(cal2.getTime())+", 475.05, 0.00, Receipts, , , , ]";
		
		System.out.println("Actual Row2 List	"	+	actBRSRow2List);
		System.out.println("Expect Row2 List	"	+	expBRSRow2List);
		
		
		 Calendar cal3=Calendar.getInstance();
			
		 cal3.add(Calendar.MONTH, -1);
		 cal3.add(Calendar.DATE, -8);
		
		ArrayList<String>brsRow3ArrayList=new ArrayList<String>();
		for(int i=0;i<bankRecRow3List.size();i++)
		{
			brsRow3ArrayList.add(bankRecRow3List.get(i).getText());
		}
		
		String actBRSRow3List=brsRow3ArrayList.toString();
		String expBRSRow3List="[3, Pending, Pmt010, "+f.format(cal3.getTime())+", Pmt:10, "+f.format(cal3.getTime())+", 0.00, 1,100.25, Payments, , , , ]";
		
		System.out.println("Actual Row3 List	"	+	actBRSRow3List);
		System.out.println("Expect Row3 List	"	+	expBRSRow3List);
		
		ArrayList<String>brsRow4ArrayList=new ArrayList<String>();
		for(int i=0;i<bankRecRow4List.size();i++)
		{
			brsRow4ArrayList.add(bankRecRow4List.get(i).getText());
		}
		
		String actBRSRow4List=brsRow4ArrayList.toString();
		String expBRSRow4List="[4, Pending, Pmt010, "+f.format(cal3.getTime())+", Pmt:10, "+f.format(cal3.getTime())+", 0.00, 123.89, Payments, , , , ]";
		
		System.out.println("Actual Row4 List	"	+	actBRSRow4List);
		System.out.println("Expect Row4 List	"	+	expBRSRow4List);
		
		 Calendar cal4=Calendar.getInstance();
		 cal4.add(Calendar.MONTH, -1);
		 cal4.add(Calendar.DATE, -15);
		
		ArrayList<String>brsRow5ArrayList=new ArrayList<String>();
		for(int i=0;i<bankRecRow5List.size();i++)
		{
			brsRow5ArrayList.add(bankRecRow5List.get(i).getText());
		}
		
		String actBRSRow5List=brsRow5ArrayList.toString();
		String expBRSRow5List="[5, Pending, Pmt010, "+f.format(cal4.getTime())+", Pmt:9, "+f.format(cal4.getTime())+", 0.00, 788.33, Payments, , , , ]";
		
		System.out.println("Actual Row5 List	"	+	actBRSRow5List);
		System.out.println("Expect Row5 List	"	+	expBRSRow5List);
		
		ArrayList<String>brsRow6ArrayList=new ArrayList<String>();
		for(int i=0;i<bankRecRow6List.size();i++)
		{
			brsRow6ArrayList.add(bankRecRow6List.get(i).getText());
		}
		
		String actBRSRow6List=brsRow6ArrayList.toString();
		String expBRSRow6List="[6, Pending, Pmt010, "+f.format(cal4.getTime())+", Pmt:9, "+f.format(cal4.getTime())+", 0.00, 678.58, Payments, , , , ]";
		
		System.out.println("Actual Row6 List	"	+	actBRSRow6List);
		System.out.println("Expect Row6 List	"	+	expBRSRow6List);
		
		
		   String actBookBal=bankRecBookBal.getText();
		   String expBookBal="9,484.31 Cr";

			String actbankRecOutDebits=bankRecOutDebits.getText();
		    String expbankRecOutDebits="5,181.95 Dr";
		
			String actbankRecOutCredits=bankRecOutCredits.getText();
		    String expbankRecOutCredits="11,531.54 Cr";
		
			String actbankRecClearedBal=bankRecClearedBal.getText();
		    String expbankRecClearedBal="3,134.72 Cr";
		
			String actbankRecOpenBal=bankRecOpenBal.getText();
		    String expbankRecOpenBal="0.00";
			

			String actbankRecDebitCounts=bankRecDebitCounts.getText();
		    String expbankRecDebitCounts="2";
			
			String actbankRecCreditCounts=bankRecCreditCounts.getText();
		    String expbankRecCreditCounts="4";
		    
			String actbankRecBankBal=bankRecBankBal.getAttribute("value");
		    String expbankRecBankBal="0.00";
			
		    String actbankRecDiff=bankRecDif.getText();
		    String expbankRecDiff="-3,134.72";


		   System.out.println("**********************************checkBankReconciliationReport*****************************************");
		   System.out.println("BookBal             : "+actBookBal             +" Value Expected  : "+expBookBal);
	       System.out.println("bankRecOutDebits    : "+actbankRecOutDebits    +" Value Expected  : "+expbankRecOutDebits);
	       System.out.println("bankRecOutCredits   : "+actbankRecOutCredits   +" Value Expected  : "+expbankRecOutCredits);
	       System.out.println("bankRecClearedBal   : "+actbankRecClearedBal   +" Value Expected  : "+expbankRecClearedBal);
	       System.out.println("Opening Bal         : "+actbankRecOpenBal      +" Value Expected  : "+expbankRecOpenBal);
	       System.out.println("bankRecDebitCounts  : "+actbankRecDebitCounts  +" Value Expected  : "+expbankRecDebitCounts);
	       System.out.println("bankRecCreditCounts : "+actbankRecCreditCounts +" Value Expected  : "+expbankRecCreditCounts);
	       System.out.println("bankRecBankBal      : "+actbankRecBankBal      +" Value Expected  : "+expbankRecBankBal);
	       System.out.println("bankRecDifferr      : "+actbankRecDiff	      +" Value Expected  : "+expbankRecDiff);

		if(actBRSRow1List.equalsIgnoreCase(expBRSRow1List) && actBRSRow2List.equalsIgnoreCase(expBRSRow2List) && 
				actBRSRow3List.equalsIgnoreCase(expBRSRow3List)&& actBRSRow4List.equalsIgnoreCase(expBRSRow4List) && actBRSRow5List.equalsIgnoreCase(expBRSRow5List) && 
				actBRSRow6List.equalsIgnoreCase(expBRSRow6List) && actBookBal.equalsIgnoreCase(expBookBal) &&
				actbankRecOutDebits.equalsIgnoreCase(expbankRecOutDebits) && actbankRecOutCredits.equalsIgnoreCase(expbankRecOutCredits) &&
				actbankRecClearedBal.equalsIgnoreCase(expbankRecClearedBal) && actbankRecDebitCounts.equalsIgnoreCase(expbankRecDebitCounts) &&
				actbankRecCreditCounts.equalsIgnoreCase(expbankRecCreditCounts) && actbankRecBankBal.equalsIgnoreCase(expbankRecBankBal) 
				&& actbankRecDiff.equalsIgnoreCase(expbankRecDiff))
		{
			return true;
		}
		else
		{
			return false;
		}

}
	
			
			
	public boolean checkAdvanceFilteronDebitAmountinBRSReport() throws InterruptedException
	{
		
		click(brsAdvFilter);
		Thread.sleep(4000);
		
		click(brsAdvFilterRow2RemoveCondition);
		click(brsAdvFilterRow1RemoveCondition);
		Thread.sleep(2000);
		
		click(brsAdvFilterConditionSelect);
		Select s3=new Select(brsAdvFilterConditionSelect);
		s3.selectByVisibleText("Where");
		Thread.sleep(1000);
		brsAdvFilterConditionSelect.sendKeys(Keys.TAB);
		
		click(brsAdvFilterSelectField);
		click(brsAdvFilterDebitAmtField);
		
		//click(brsAdvFilterSelectOperator);
		Thread.sleep(2000);
		Select s4=new Select(brsAdvFilterSelectOperator);
		s4.selectByVisibleText("Greater than");
		Thread.sleep(4000);
		//brsAdvFilterSelectOperator.sendKeys(Keys.TAB);
		
		click(brsAdvFilterCompareWith);
		Thread.sleep(2000);
		Select s5=new Select(brsAdvFilterCompareWith);
		s5.selectByVisibleText("value");
		Thread.sleep(2000);
		//brsAdvFilterCompareWith.sendKeys(Keys.TAB);
	
	//click(brsAdvFilterValueTxt);
	Thread.sleep(1000);
	click(brsAdvFilterValueTxt);
	Thread.sleep(2000);
	brsAdvFilterValueTxt.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
	brsAdvFilterValueTxt.sendKeys("1200");
	Thread.sleep(2000);
	brsAdvFilterValueTxt.sendKeys(Keys.TAB);
	
	click(brsAdvFilterOkBtn);
	Thread.sleep(4000);
		
	
	 Calendar cal3=Calendar.getInstance();
	 cal3.add(Calendar.MONTH, -1);
	 cal3.add(Calendar.DATE, -36);
	 SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
	ArrayList<String>brsRow1ArrayList=new ArrayList<String>();
	for(int i=0;i<bankRecRow1List.size();i++)
	{
		brsRow1ArrayList.add(bankRecRow1List.get(i).getText());
	}
	
	String actBRSRow1List=brsRow1ArrayList.toString();
	String expBRSRow1List="[1, Pending, RP001, "+f.format(cal3.getTime())+", Rct:5, "+f.format(cal3.getTime())+", 1,250.47, 0.00, Receipts, , , , ]";
	
	System.out.println("Actual Row1 List	"	+	actBRSRow1List);
	System.out.println("Expect Row1 List	"	+	expBRSRow1List);
	
	
	 Calendar cal4=Calendar.getInstance();
	 cal4.add(Calendar.MONTH, -2);
	 cal4.add(Calendar.DATE, -12);
	
	ArrayList<String>brsRow2ArrayList=new ArrayList<String>();
	for(int i=0;i<bankRecRow2List.size();i++)
	{
		brsRow2ArrayList.add(bankRecRow2List.get(i).getText());
	}
	
	String actBRSRow2List=brsRow2ArrayList.toString();
	String expBRSRow2List="[2, Pending, Rct002, "+f.format(cal4.getTime())+", Rct:6, "+f.format(cal4.getTime())+", 1,250.47, 0.00, Receipts, , , , ]";
	
	System.out.println("Actual Row2 List	"	+	actBRSRow2List);
	System.out.println("Expect Row2 List	"	+	expBRSRow2List);
	
	
	
	   String actBookBal=bankRecBookBal.getText();
	   String expBookBal="9,484.31 Cr";

		String actbankRecOutDebits=bankRecOutDebits.getText();
	    String expbankRecOutDebits="5,181.95 Dr";
	
		String actbankRecOutCredits=bankRecOutCredits.getText();
	    String expbankRecOutCredits="11,531.54 Cr";
	
		String actbankRecClearedBal=bankRecClearedBal.getText();
	    String expbankRecClearedBal="3,134.72 Cr";
	
		String actbankRecOpenBal=bankRecOpenBal.getText();
	    String expbankRecOpenBal="0.00";
		

		String actbankRecDebitCounts=bankRecDebitCounts.getText();
	    String expbankRecDebitCounts="2";
		
		String actbankRecCreditCounts=bankRecCreditCounts.getText();
	    String expbankRecCreditCounts="0";
	    
		String actbankRecBankBal=bankRecBankBal.getAttribute("value");
	    String expbankRecBankBal="0.00";
		
	    String actbankRecDiff=bankRecDif.getText();
	    String expbankRecDiff="-3,134.72";


	   System.out.println("**********************************checkBankReconciliationReport*****************************************");
	   System.out.println("BookBal             : "+actBookBal             +" Value Expected  : "+expBookBal);
       System.out.println("bankRecOutDebits    : "+actbankRecOutDebits    +" Value Expected  : "+expbankRecOutDebits);
       System.out.println("bankRecOutCredits   : "+actbankRecOutCredits   +" Value Expected  : "+expbankRecOutCredits);
       System.out.println("bankRecClearedBal   : "+actbankRecClearedBal   +" Value Expected  : "+expbankRecClearedBal);
       System.out.println("Opening Bal         : "+actbankRecOpenBal      +" Value Expected  : "+expbankRecOpenBal);
       System.out.println("bankRecDebitCounts  : "+actbankRecDebitCounts  +" Value Expected  : "+expbankRecDebitCounts);
       System.out.println("bankRecCreditCounts : "+actbankRecCreditCounts +" Value Expected  : "+expbankRecCreditCounts);
       System.out.println("bankRecBankBal      : "+actbankRecBankBal      +" Value Expected  : "+expbankRecBankBal);
       System.out.println("bankRecDifferr      : "+actbankRecDiff	      +" Value Expected  : "+expbankRecDiff);
 
	if(actBRSRow1List.equalsIgnoreCase(expBRSRow1List) && actBRSRow2List.equalsIgnoreCase(expBRSRow2List) && actBookBal.equalsIgnoreCase(expBookBal) &&
			actbankRecOutDebits.equalsIgnoreCase(expbankRecOutDebits) && actbankRecOutCredits.equalsIgnoreCase(expbankRecOutCredits) &&
			actbankRecClearedBal.equalsIgnoreCase(expbankRecClearedBal) && actbankRecDebitCounts.equalsIgnoreCase(expbankRecDebitCounts) &&
			actbankRecCreditCounts.equalsIgnoreCase(expbankRecCreditCounts) && actbankRecBankBal.equalsIgnoreCase(expbankRecBankBal)
			&& actbankRecDiff.equalsIgnoreCase(expbankRecDiff))
	{
		return true;
	}
	else
	{
		return false;
	}
	}
	
	
	public boolean checkAdvancedFilteronCreditAmountinBRSReport() throws InterruptedException
	{
		Thread.sleep(2000);
		click(brsAdvFilter);
		Thread.sleep(4000);
		
		click(brsAdvFilterRow1RemoveCondition);
		
		Thread.sleep(2000);
		
		click(brsAdvFilterConditionSelect);
		Select s3=new Select(brsAdvFilterConditionSelect);
		s3.selectByVisibleText("Where");
		Thread.sleep(1000);
		brsAdvFilterConditionSelect.sendKeys(Keys.TAB);
		
		click(brsAdvFilterSelectField);
		click(brsAdvFilterCreditAmtField);
		
		//click(brsAdvFilterSelectOperator);
		Thread.sleep(2000);
		Select s4=new Select(brsAdvFilterSelectOperator);
		s4.selectByVisibleText("Greater than or equal to");
		Thread.sleep(4000);
		//brsAdvFilterSelectOperator.sendKeys(Keys.TAB);
		
		click(brsAdvFilterCompareWith);
		Select s5=new Select(brsAdvFilterCompareWith);
		s5.selectByVisibleText("value");
		Thread.sleep(2000);
		//brsAdvFilterCompareWith.sendKeys(Keys.TAB);
	
	//click(brsAdvFilterValueTxt);
	Thread.sleep(1000);
	click(brsAdvFilterValueTxt);
	Thread.sleep(2000);
	brsAdvFilterValueTxt.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
	brsAdvFilterValueTxt.sendKeys("2000");
	Thread.sleep(2000);
	brsAdvFilterValueTxt.sendKeys(Keys.TAB);
	
	click(brsAdvFilterOkBtn);
	Thread.sleep(4000);
		
	
	 Calendar cal5=Calendar.getInstance();
	 cal5.add(Calendar.MONTH, -2);
	 cal5.add(Calendar.DATE, -35);
	 SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
	
	ArrayList<String>brsRow1ArrayList=new ArrayList<String>();
	for(int i=0;i<bankRecRow1List.size();i++)
	{
		brsRow1ArrayList.add(bankRecRow1List.get(i).getText());
	}
	
	String actBRSRow1List=brsRow1ArrayList.toString();
	String expBRSRow1List="[1, Cleared, RP001, "+f.format(cal5.getTime())+", Pmt:6, "+f.format(cal5.getTime())+", 0.00, 3,807.81, Payments, , , , ]";
	
	System.out.println("Actual Row1 List	"	+	actBRSRow1List);
	System.out.println("Expect Row1 List	"	+	expBRSRow1List);
	
	 Calendar cal6=Calendar.getInstance();
	 cal6.add(Calendar.MONTH, -3);
	 cal6.add(Calendar.DATE, -15);
	
	
	ArrayList<String>brsRow2ArrayList=new ArrayList<String>();
	for(int i=0;i<bankRecRow2List.size();i++)
	{
		brsRow2ArrayList.add(bankRecRow2List.get(i).getText());
	}
	
	String actBRSRow2List=brsRow2ArrayList.toString();
	String expBRSRow2List="[2, Pending, Pmt002, "+f.format(cal6.getTime())+", Pmt:7, "+f.format(cal6.getTime())+", 0.00, 2,514.03, Payments, , , , ]";
	
	System.out.println("Actual Row2 List	"	+	actBRSRow2List);
	System.out.println("Expect Row2 List	"	+	expBRSRow2List);
	
	
	
	   String actBookBal=bankRecBookBal.getText();
	   String expBookBal="9,484.31 Cr";

		String actbankRecOutDebits=bankRecOutDebits.getText();
	    String expbankRecOutDebits="5,181.95 Dr";
	
		String actbankRecOutCredits=bankRecOutCredits.getText();
	    String expbankRecOutCredits="11,531.54 Cr";
	
		String actbankRecClearedBal=bankRecClearedBal.getText();
	    String expbankRecClearedBal="3,134.72 Cr";
	
		String actbankRecOpenBal=bankRecOpenBal.getText();
	    String expbankRecOpenBal="0.00";
		

		String actbankRecDebitCounts=bankRecDebitCounts.getText();
	    String expbankRecDebitCounts="0";
		
		String actbankRecCreditCounts=bankRecCreditCounts.getText();
	    String expbankRecCreditCounts="2";
	    
		String actbankRecBankBal=bankRecBankBal.getAttribute("value");
	    String expbankRecBankBal="0.00";
		
	    String actbankRecDiff=bankRecDif.getText();
	    String expbankRecDiff="-3,134.72";


	   System.out.println("**********************************checkBankReconciliationReport*****************************************");
	   System.out.println("BookBal             : "+actBookBal             +" Value Expected  : "+expBookBal);
       System.out.println("bankRecOutDebits    : "+actbankRecOutDebits    +" Value Expected  : "+expbankRecOutDebits);
       System.out.println("bankRecOutCredits   : "+actbankRecOutCredits   +" Value Expected  : "+expbankRecOutCredits);
       System.out.println("bankRecClearedBal   : "+actbankRecClearedBal   +" Value Expected  : "+expbankRecClearedBal);
       System.out.println("Opening Bal         : "+actbankRecOpenBal      +" Value Expected  : "+expbankRecOpenBal);
       System.out.println("bankRecDebitCounts  : "+actbankRecDebitCounts  +" Value Expected  : "+expbankRecDebitCounts);
       System.out.println("bankRecCreditCounts : "+actbankRecCreditCounts +" Value Expected  : "+expbankRecCreditCounts);
       System.out.println("bankRecBankBal      : "+actbankRecBankBal      +" Value Expected  : "+expbankRecBankBal);
       System.out.println("bankRecDifferr      : "+actbankRecDiff	      +" Value Expected  : "+expbankRecDiff);

	if(actBRSRow1List.equalsIgnoreCase(expBRSRow1List) && actBRSRow2List.equalsIgnoreCase(expBRSRow2List) && actBookBal.equalsIgnoreCase(expBookBal) &&
			actbankRecOutDebits.equalsIgnoreCase(expbankRecOutDebits) && actbankRecOutCredits.equalsIgnoreCase(expbankRecOutCredits) &&
			actbankRecClearedBal.equalsIgnoreCase(expbankRecClearedBal) && actbankRecDebitCounts.equalsIgnoreCase(expbankRecDebitCounts) &&
			actbankRecCreditCounts.equalsIgnoreCase(expbankRecCreditCounts) && actbankRecBankBal.equalsIgnoreCase(expbankRecBankBal) 
			&& actbankRecDiff.equalsIgnoreCase(expbankRecDiff))
	{
		return true;
	}
	else
	{
		return false;
	}
	
		
	}
	
	
	public boolean checkAdvancedFilteronChequeNumberinBRSReport() throws InterruptedException
	{
		
		Thread.sleep(2000);
		click(brsAdvFilter);
		Thread.sleep(4000);
		
		click(brsAdvFilterRow1RemoveCondition);
		
		Thread.sleep(2000);
		
		click(brsAdvFilterConditionSelect);
		Select s3=new Select(brsAdvFilterConditionSelect);
		s3.selectByVisibleText("Where");
		Thread.sleep(1000);
		brsAdvFilterConditionSelect.sendKeys(Keys.TAB);
		
		click(brsAdvFilterSelectField);
		click(brsAdvFilterChequeNumField);
		
		//click(brsAdvFilterSelectOperator);
		Thread.sleep(2000);
		Select s4=new Select(brsAdvFilterSelectOperator);
		s4.selectByVisibleText("Equal to");
		Thread.sleep(4000);
		//brsAdvFilterSelectOperator.sendKeys(Keys.TAB);
		
		click(brsAdvFilterCompareWith);
		Select s5=new Select(brsAdvFilterCompareWith);
		s5.selectByVisibleText("value");
		Thread.sleep(1000);
		//brsAdvFilterCompareWith.sendKeys(Keys.TAB);
	
	//click(brsAdvFilterValueTxt);
	Thread.sleep(1000);
	click(brsAdvFilterValueTxt);
	Thread.sleep(2000);
	brsAdvFilterValueTxt.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
	brsAdvFilterValueTxt.sendKeys("RP001");
	Thread.sleep(2000);
	brsAdvFilterValueTxt.sendKeys(Keys.TAB);
	
	click(brsAdvFilterOkBtn);
	Thread.sleep(4000);
	 Calendar cal4=Calendar.getInstance();
	 cal4.add(Calendar.MONTH, -2);
	 cal4.add(Calendar.DATE, -5);
	 SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
	
	ArrayList<String>brsRow1ArrayList=new ArrayList<String>();
	for(int i=0;i<bankRecRow1List.size();i++)
	{
		brsRow1ArrayList.add(bankRecRow1List.get(i).getText());
	}
	
	String actBRSRow1List=brsRow1ArrayList.toString();
	String expBRSRow1List="[1, Pending, RP001, "+f.format(cal4.getTime())+", Rct:5, "+f.format(cal4.getTime())+", 1,250.47, 0.00, Receipts, , , , ]";
	
	System.out.println("Actual Row1 List	"	+	actBRSRow1List);
	System.out.println("Expect Row1 List	"	+	expBRSRow1List);
	
	 Calendar cal5=Calendar.getInstance();
	 cal5.add(Calendar.MONTH, -2);
	 cal5.add(Calendar.DATE, -35);
	 
	ArrayList<String>brsRow2ArrayList=new ArrayList<String>();
	for(int i=0;i<bankRecRow2List.size();i++)
	{
		brsRow2ArrayList.add(bankRecRow2List.get(i).getText());
	}
	
	String actBRSRow2List=brsRow2ArrayList.toString();
	String expBRSRow2List="[2, Cleared, RP001, "+f.format(cal5.getTime())+", Pmt:6, "+f.format(cal5.getTime())+", 0.00, 3,807.81, Payments, , , , ]";
	
	System.out.println("Actual Row2 List	"	+	actBRSRow2List);
	System.out.println("Expect Row2 List	"	+	expBRSRow2List);
	
	
	
	   String actBookBal=bankRecBookBal.getText();
	   String expBookBal="9,484.31 Cr";

		String actbankRecOutDebits=bankRecOutDebits.getText();
	    String expbankRecOutDebits="5,181.95 Dr";
	
		String actbankRecOutCredits=bankRecOutCredits.getText();
	    String expbankRecOutCredits="11,531.54 Cr";
	
		String actbankRecClearedBal=bankRecClearedBal.getText();
	    String expbankRecClearedBal="3,134.72 Cr";
	
		String actbankRecOpenBal=bankRecOpenBal.getText();
	    String expbankRecOpenBal="0.00";
		

		String actbankRecDebitCounts=bankRecDebitCounts.getText();
	    String expbankRecDebitCounts="1";
		
		String actbankRecCreditCounts=bankRecCreditCounts.getText();
	    String expbankRecCreditCounts="1";
	    
		String actbankRecBankBal=bankRecBankBal.getAttribute("value");
	    String expbankRecBankBal="0.00";
	    
	    
	    String actbankRecDiff=bankRecDif.getText();
	    String expbankRecDiff="-3,134.72";
		
	   System.out.println("**********************************checkBankReconciliationReport*****************************************");
	   System.out.println("BookBal             : "+actBookBal             +" Value Expected  : "+expBookBal);
       System.out.println("bankRecOutDebits    : "+actbankRecOutDebits    +" Value Expected  : "+expbankRecOutDebits);
       System.out.println("bankRecOutCredits   : "+actbankRecOutCredits   +" Value Expected  : "+expbankRecOutCredits);
       System.out.println("bankRecClearedBal   : "+actbankRecClearedBal   +" Value Expected  : "+expbankRecClearedBal);
       System.out.println("Opening Bal         : "+actbankRecOpenBal      +" Value Expected  : "+expbankRecOpenBal);
       System.out.println("bankRecDebitCounts  : "+actbankRecDebitCounts  +" Value Expected  : "+expbankRecDebitCounts);
       System.out.println("bankRecCreditCounts : "+actbankRecCreditCounts +" Value Expected  : "+expbankRecCreditCounts);
       System.out.println("bankRecBankBal      : "+actbankRecBankBal      +" Value Expected  : "+expbankRecBankBal);
       System.out.println("bankRecDifferr      : "+actbankRecDiff	      +" Value Expected  : "+expbankRecDiff);
	    
	if(actBRSRow1List.equalsIgnoreCase(expBRSRow1List) && actBRSRow2List.equalsIgnoreCase(expBRSRow2List) && actBookBal.equalsIgnoreCase(expBookBal) &&
			actbankRecOutDebits.equalsIgnoreCase(expbankRecOutDebits) && actbankRecOutCredits.equalsIgnoreCase(expbankRecOutCredits) &&
			actbankRecClearedBal.equalsIgnoreCase(expbankRecClearedBal) && actbankRecDebitCounts.equalsIgnoreCase(expbankRecDebitCounts) &&
			actbankRecCreditCounts.equalsIgnoreCase(expbankRecCreditCounts) && actbankRecBankBal.equalsIgnoreCase(expbankRecBankBal)
			&& actbankRecDiff.equalsIgnoreCase(expbankRecDiff))
	{
		return true;
	}
	else
	{
		return false;
	}
	
		
	
	}
	
	
	public boolean checkAdvancedFilteronDepartmentinBRSReport() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		
		Thread.sleep(2000);
		click(brsAdvFilter);
		Thread.sleep(5000);
		
		click(brsAdvFilterRow1RemoveCondition);
		
		Thread.sleep(2000);
		
		click(brsAdvFilterConditionSelect);
		Select s3=new Select(brsAdvFilterConditionSelect);
		s3.selectByVisibleText("Where");
		Thread.sleep(1000);
		brsAdvFilterConditionSelect.sendKeys(Keys.TAB);
		
		click(brsAdvFilterSelectField);
		click(brsAdvFilterDepartField);
		
		//click(brsAdvFilterSelectOperator);
		Thread.sleep(2000);
		Select s4=new Select(brsAdvFilterSelectOperator);
		s4.selectByVisibleText("Not equal to");
		Thread.sleep(4000);
		//brsAdvFilterSelectOperator.sendKeys(Keys.TAB);
		
		click(brsAdvFilterCompareWith);
		Select s5=new Select(brsAdvFilterCompareWith);
		s5.selectByVisibleText("value");
		Thread.sleep(1000);
		//brsAdvFilterCompareWith.sendKeys(Keys.TAB);
	
	//click(brsAdvFilterValueTxt);
	Thread.sleep(1000);
	//click(brsAdvFilterValueTxt);
	//Thread.sleep(2000);
	click(brsAdvFilterDeptValueTxt);
	brsAdvFilterDeptValueTxt.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
	brsAdvFilterDeptValueTxt.sendKeys("AMERICA");
	Thread.sleep(2000);
	brsAdvFilterDeptValueTxt.sendKeys(Keys.TAB);
	
	click(brsAdvFilterOkBtn);
	Thread.sleep(4000);
		
	String expMsg="No records exists with this filter condition";
	String actMsg= checkValidationMessage(expMsg);
	
	click(brsAdvFilter);
	Thread.sleep(5000);
	
	
	click(brsAdvFilterRow1RemoveCondition);
	Thread.sleep(2000);
	
	click(brsAdvFilterOkBtn);
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
	
	
	
	@FindBy(xpath="//*[@id='sortByColumn']")
	public static WebElement brs_sortColSelect;
	
	public boolean checkSortByColumnonBRSStatusinBRSReport() throws InterruptedException
	{
		Thread.sleep(2000);
		click(clearBtn);
		Thread.sleep(6000);
		
		click(reportbankTxt);
		reportbankTxt.sendKeys("HDFC");
		Thread.sleep(1500);
		reportbankTxt.sendKeys(Keys.TAB);
		
		Select s=new Select(BRSSelectStatusDrpdwn);
		s.selectByValue("2");
		Thread.sleep(1500);
		BRSSelectStatusDrpdwn.sendKeys(Keys.TAB);
		
		
		Select s1=new Select(BRSSelectCRDRDrpdwn);
		s1.selectByValue("2");
		Thread.sleep(1500);
		BRSSelectCRDRDrpdwn.sendKeys(Keys.TAB);
		
		click(LoadBtn);
		Thread.sleep(10000);
		
		ArrayList<String>BRSArrayList=new ArrayList<String>();
		for(int i=0;i<BRS_BRSStatusList.size();i++)
		{
			BRSArrayList.add(BRS_BRSStatusList.get(i).getText());
			
		}
		String actBRSStatusBeforeSort=BRSArrayList.toString();
		String expBRSStatusBeforeSort="[Pending, Pending, Pending, Pending, Cleared, Pending, Pending, Pending, Pending, Pending, Pending, Pending, Pending, Pending, Pending, Cleared, Pending, Pending, Pending, Pending]";
		
		System.out.println("Actual BRS Status Before Sort	"	+	actBRSStatusBeforeSort);
		System.out.println("Expect BRS Status Before Sort	"	+	expBRSStatusBeforeSort);
		
		
		//click(brs_sortColSelect);
		Select s4=new Select(brs_sortColSelect);
		s4.selectByVisibleText("BRS Status");
		Thread.sleep(2500);
		brs_sortColSelect.sendKeys(Keys.TAB);
		Thread.sleep(5000);
		
		ArrayList<String>BRSArrayListAfter=new ArrayList<String>();
		for(int i=0;i<BRS_BRSStatusList.size();i++)
		{
			BRSArrayListAfter.add(BRS_BRSStatusList.get(i).getText());
			
		}
		String actBRSStatusAfterSort=BRSArrayListAfter.toString();
		String expBRSStatusAfterSort="[Cleared, Cleared, Pending, Pending, Pending, Pending, Pending, Pending, Pending, Pending, Pending, Pending, Pending, Pending, Pending, Pending, Pending, Pending, Pending, Pending]";
		
		System.out.println("Actual BRS Status Before Sort	"	+	actBRSStatusAfterSort);
		System.out.println("Expect BRS Status Before Sort	"	+	expBRSStatusAfterSort);
		
		if(actBRSStatusBeforeSort.equalsIgnoreCase(expBRSStatusBeforeSort) && actBRSStatusAfterSort.equalsIgnoreCase(expBRSStatusAfterSort))
		{
			return true;
		}
		else
		{
			return false;
		}
	
	}
			
	public boolean checkSortByColumnonChequeNuminBRSReport() throws InterruptedException
	{
		click(clearBtn);
		Thread.sleep(4000);
		
		click(reportbankTxt);
		reportbankTxt.sendKeys("HDFC");
		Thread.sleep(1500);
		reportbankTxt.sendKeys(Keys.TAB);
		
		Select s=new Select(BRSSelectStatusDrpdwn);
		s.selectByValue("2");
		Thread.sleep(1500);
		BRSSelectStatusDrpdwn.sendKeys(Keys.TAB);
		
		
		Select s1=new Select(BRSSelectCRDRDrpdwn);
		s1.selectByValue("2");
		Thread.sleep(1500);
		BRSSelectCRDRDrpdwn.sendKeys(Keys.TAB);
		
		click(LoadBtn);
		Thread.sleep(10000);
		
		ArrayList<String>BRSArrayList=new ArrayList<String>();
		for(int i=0;i<BRS_ChequeNumList.size();i++)
		{
			BRSArrayList.add(BRS_ChequeNumList.get(i).getText());
			
		}
		String actChequeNumBeforeSort=BRSArrayList.toString();
		String expChequeNumBeforeSort="[, Rpt-001, RP001, Rct002, Rct003, Rct004, Rct006, Pmt010, Pmt010, Pmt050, Pmt050, Pmt050, Pmt050, , pmt-001, RP001, Pmt002, Pmt003, Pmt010, Pmt010]";
		
		System.out.println("Actual Cheque Num Before Sort	"	+	actChequeNumBeforeSort);
		System.out.println("Expect Cheque Num Before Sort	"	+	expChequeNumBeforeSort);
		
		
		//click(brs_sortColSelect);
		Select s4=new Select(brs_sortColSelect);
		s4.selectByVisibleText("ChequeNo");
		Thread.sleep(1500);
		brs_sortColSelect.sendKeys(Keys.TAB);
		Thread.sleep(5000);
		
		ArrayList<String>BRSArrayListAfter=new ArrayList<String>();
		for(int i=0;i<BRS_ChequeNumList.size();i++)
		{
			BRSArrayListAfter.add(BRS_ChequeNumList.get(i).getText());
			
		}
		String actChequeNumAfterSort=BRSArrayListAfter.toString();
		String expChequeNumAfterSort="[, , pmt-001, Pmt002, Pmt003, Pmt010, Pmt010, Pmt010, Pmt010, Pmt050, Pmt050, Pmt050, Pmt050, Rct002, Rct003, Rct004, Rct006, RP001, RP001, Rpt-001]";
		
		System.out.println("Actual Cheque Num After Sort	"	+	actChequeNumAfterSort);
		System.out.println("Expect Cheque Num After Sort	"	+	expChequeNumAfterSort);
		
		if(actChequeNumBeforeSort.equalsIgnoreCase(expChequeNumBeforeSort) && actChequeNumAfterSort.equalsIgnoreCase(expChequeNumAfterSort))
		{
			return true;
		}
		else
		{
			return false;
		}
	
	}
	
	

	
	
	public boolean checkSortByColumnOnDocumentNum() throws InterruptedException
	{

		click(clearBtn);
		Thread.sleep(4000);
		
		click(reportbankTxt);
		reportbankTxt.sendKeys("HDFC");
		Thread.sleep(1500);
		reportbankTxt.sendKeys(Keys.TAB);
		
		Select s=new Select(BRSSelectStatusDrpdwn);
		s.selectByValue("2");
		Thread.sleep(1500);
		BRSSelectStatusDrpdwn.sendKeys(Keys.TAB);
		
		
		Select s1=new Select(BRSSelectCRDRDrpdwn);
		s1.selectByValue("2");
		Thread.sleep(1500);
		BRSSelectCRDRDrpdwn.sendKeys(Keys.TAB);
		
		click(LoadBtn);
		Thread.sleep(10000);
		
		ArrayList<String>BRSArrayList=new ArrayList<String>();
		for(int i=0;i<BRS_DocNoColList.size();i++)
		{
			BRSArrayList.add(BRS_DocNoColList.get(i).getText());
			
		}
		String actDocumentNumBeforeSort=BRSArrayList.toString();
		String expDocumentNumBeforeSort="[Rct:3, Rct:4, Rct:5, Rct:6, Rct:7, Rct:8, Rct:9, Pmt:10, Pmt:10, Pmt:11, Pmt:11, Pmt:12, Pmt:12, Pmt:4, Pmt:5, Pmt:6, Pmt:7, Pmt:8, Pmt:9, Pmt:9]";
		
		System.out.println("Actual Cheque Num Before Sort	"	+	actDocumentNumBeforeSort);
		System.out.println("Expect Cheque Num Before Sort	"	+	expDocumentNumBeforeSort);
		
		
		//click(brs_sortColSelect);
		Select s4=new Select(brs_sortColSelect);
		s4.selectByVisibleText("Document No");
		Thread.sleep(1500);
		brs_sortColSelect.sendKeys(Keys.TAB);
		Thread.sleep(5000);
		
		ArrayList<String>BRSArrayListAfter=new ArrayList<String>();
		for(int i=0;i<BRS_DocNoColList.size();i++)
		{
			BRSArrayListAfter.add(BRS_DocNoColList.get(i).getText());
			
		}
		String actDocumentNumAfterSort=BRSArrayListAfter.toString();
		String expDocumentNumAfterSort="[Pmt:10, Pmt:10, Pmt:11, Pmt:11, Pmt:12, Pmt:12, Pmt:4, Pmt:5, Pmt:6, Pmt:7, Pmt:8, Pmt:9, Pmt:9, Rct:3, Rct:4, Rct:5, Rct:6, Rct:7, Rct:8, Rct:9]";
		
		System.out.println("Actual Cheque Num After Sort	"	+	actDocumentNumAfterSort);
		System.out.println("Expect Cheque Num After Sort	"	+	expDocumentNumAfterSort);
		
		if(actDocumentNumBeforeSort.equalsIgnoreCase(expDocumentNumBeforeSort) && actDocumentNumAfterSort.equalsIgnoreCase(expDocumentNumAfterSort))
		{
			return true;
		}
		else
		{
			return false;
		}
	
	
	}
	
	public boolean checkSortByColumnOnDebitAmount() throws InterruptedException
	{

		click(clearBtn);
		Thread.sleep(4000);
		
		click(reportbankTxt);
		reportbankTxt.sendKeys("HDFC");
		Thread.sleep(1500);
		reportbankTxt.sendKeys(Keys.TAB);
		
		Select s=new Select(BRSSelectStatusDrpdwn);
		s.selectByValue("2");
		Thread.sleep(1500);
		BRSSelectStatusDrpdwn.sendKeys(Keys.TAB);
		
		
		Select s1=new Select(BRSSelectCRDRDrpdwn);
		s1.selectByValue("2");
		Thread.sleep(1500);
		BRSSelectCRDRDrpdwn.sendKeys(Keys.TAB);
		
		click(LoadBtn);
		Thread.sleep(10000);
		
		ArrayList<String>BRSArrayList=new ArrayList<String>();
		for(int i=0;i<BRS_DebitAmtColList.size();i++)
		{
			BRSArrayList.add(BRS_DebitAmtColList.get(i).getText());
			
		}
		String actDebitAmtBeforeSort=BRSArrayList.toString();
		String expDebitAmtBeforeSort="[255.69, 1,085.03, 1,250.47, 1,250.47, 673.09, 865.24, 475.05, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00]";
		
		System.out.println("Actual Cheque Num Before Sort	"	+	actDebitAmtBeforeSort);
		System.out.println("Expect Cheque Num Before Sort	"	+	expDebitAmtBeforeSort);
		
		
		//click(brs_sortColSelect);
		Select s4=new Select(brs_sortColSelect);
		s4.selectByVisibleText("Debit Amount");
		Thread.sleep(1500);
		brs_sortColSelect.sendKeys(Keys.TAB);
		Thread.sleep(5000);
		
		ArrayList<String>BRSArrayListAfter=new ArrayList<String>();
		for(int i=0;i<BRS_DebitAmtColList.size();i++)
		{
			BRSArrayListAfter.add(BRS_DebitAmtColList.get(i).getText());
			
		}
		String actDebitAmtAfterSort=BRSArrayListAfter.toString();
		String expDebitAmtAfterSort="[0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 255.69, 475.05, 673.09, 865.24, 1,085.03, 1,250.47, 1,250.47]";
		
		System.out.println("Actual Cheque Num After Sort	"	+	actDebitAmtAfterSort);
		System.out.println("Expect Cheque Num After Sort	"	+	expDebitAmtAfterSort);
		
		if(actDebitAmtBeforeSort.equalsIgnoreCase(expDebitAmtBeforeSort) && actDebitAmtAfterSort.equalsIgnoreCase(expDebitAmtAfterSort))
		{
			return true;
		}
		else
		{
			return false;
		}
	
	
	}
	
	
	@FindBy(xpath="//*[@id='DatePeriod']")
	public static WebElement brs_DatePeriodSelect;
	
	@FindBy(xpath="//*[@id='brs_StartDate']")
	public static WebElement brs_StartDateTxt;
	
	@FindBy(xpath="//*[@id='brs_EndDate']")
	public static WebElement brs_EndDateTxt;
	
	public boolean checkDatePeriodasSelectDateinBRSReport() throws InterruptedException
	{
		Thread.sleep(2000);
			
		click(serachMenuTextHomePage);
		
		serachMenuTextHomePage.sendKeys("Bank Reconciliation");
		Thread.sleep(2000);
		//serachMenuTextHomePage.sendKeys(Keys.DOWN);
		serachMenuTextHomePage.sendKeys(Keys.ENTER);
		
		Thread.sleep(9000);
		
		click(reportbankTxt);
		reportbankTxt.sendKeys("HDFC");
		Thread.sleep(1500);
		reportbankTxt.sendKeys(Keys.TAB);
		Thread.sleep(4000);
		Select s=new Select(BRSSelectStatusDrpdwn);
		s.selectByValue("2");
		Thread.sleep(1500);
		BRSSelectStatusDrpdwn.sendKeys(Keys.TAB);
		
		
		Select s1=new Select(BRSSelectCRDRDrpdwn);
		s1.selectByValue("2");
		Thread.sleep(1500);
		BRSSelectCRDRDrpdwn.sendKeys(Keys.TAB);
		
		Select s2=new Select(brs_DatePeriodSelect);
		s2.selectByVisibleText("Select Date");
		Thread.sleep(1800);
		brs_DatePeriodSelect.sendKeys(Keys.TAB);
		Thread.sleep(1200);
		
		Calendar cal=Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -2);
		SimpleDateFormat f=new SimpleDateFormat("dd/MM/yyyy");
		click(brs_StartDateTxt);
		brs_StartDateTxt.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
		brs_StartDateTxt.sendKeys(f.format(cal.getTime()));
		Thread.sleep(1500);
		
		String actStartDateTxt=brs_StartDateTxt.getAttribute("value");
		String expStartDateTxt=f.format(cal.getTime());
		
		String actEndDateTxt=brs_EndDateTxt.getAttribute("value");
		String expEndDateTxt= getCurrentDate();
		
		System.out.println("Start Date		"		+	"Actual:	"	+	actStartDateTxt		+	"Expected	"	+	expStartDateTxt);
		System.out.println("End   Date		"		+	"Actual:	"	+	actEndDateTxt		+	"Expected	"	+	expEndDateTxt);
		
		click(LoadBtn);
		Thread.sleep(10000);
		
		String actBookBal=bankRecBookBal.getText();
	 	String expBookBal="9,484.31 Cr";

		String actbankRecOutDebits=bankRecOutDebits.getText();
	    String expbankRecOutDebits="255.69 Dr";
	
		String actbankRecOutCredits=bankRecOutCredits.getText();
	    String expbankRecOutCredits="1,900.85 Cr";
	
		String actbankRecClearedBal=bankRecClearedBal.getText();
	    String expbankRecClearedBal="3,134.72 Cr";
	
		String actbankRecOpenBal=bankRecOpenBal.getText();
	    String expbankRecOpenBal="7,839.15 Cr";
		

		String actbankRecDebitCounts=bankRecDebitCounts.getText();
	    String expbankRecDebitCounts="6";
		
		String actbankRecCreditCounts=bankRecCreditCounts.getText();
	    String expbankRecCreditCounts="12";
	    
		String actbankRecBankBal=bankRecBankBal.getAttribute("value");
	    String expbankRecBankBal="0.00";
		
	    String actbankRecDiff=bankRecDif.getText();
	    String expbankRecDiff="-3,134.72";


	   System.out.println("**********************************checkBankReconciliationReport*****************************************");
	   System.out.println("BookBal             : "+actBookBal             +" Value Expected  : "+expBookBal);
       System.out.println("bankRecOutDebits    : "+actbankRecOutDebits    +" Value Expected  : "+expbankRecOutDebits);
       System.out.println("bankRecOutCredits   : "+actbankRecOutCredits   +" Value Expected  : "+expbankRecOutCredits);
       System.out.println("bankRecClearedBal   : "+actbankRecClearedBal   +" Value Expected  : "+expbankRecClearedBal);
       System.out.println("Opening Bal         : "+actbankRecOpenBal      +" Value Expected  : "+expbankRecOpenBal);
       System.out.println("bankRecDebitCounts  : "+actbankRecDebitCounts  +" Value Expected  : "+expbankRecDebitCounts);
       System.out.println("bankRecCreditCounts : "+actbankRecCreditCounts +" Value Expected  : "+expbankRecCreditCounts);
       System.out.println("bankRecBankBal      : "+actbankRecBankBal      +" Value Expected  : "+expbankRecBankBal);
       System.out.println("bankRecDifferr      : "+actbankRecDiff	      +" Value Expected  : "+expbankRecDiff);

       if(actStartDateTxt.equalsIgnoreCase(expStartDateTxt)&& actEndDateTxt.equalsIgnoreCase(expEndDateTxt) 
  	    	 &&actBookBal.equalsIgnoreCase(expBookBal) &&
  				actbankRecOutDebits.equalsIgnoreCase(expbankRecOutDebits) && actbankRecOutCredits.equalsIgnoreCase(expbankRecOutCredits) &&
  				actbankRecClearedBal.equalsIgnoreCase(expbankRecClearedBal) && actbankRecDebitCounts.equalsIgnoreCase(expbankRecDebitCounts) &&
  				actbankRecCreditCounts.equalsIgnoreCase(expbankRecCreditCounts) && actbankRecBankBal.equalsIgnoreCase(expbankRecBankBal) 
  				&& actbankRecDiff.equalsIgnoreCase(expbankRecDiff))
       {
    	   return true;
       }
       else
       {
    	   return false;
       }
		
	}
	
	public boolean checkDatePeriodasASonDateinBRSReport() throws InterruptedException
	{

		
		Select s2=new Select(brs_DatePeriodSelect);
		s2.selectByVisibleText("As on date");
		Thread.sleep(2000);
		brs_DatePeriodSelect.sendKeys(Keys.TAB);
		Thread.sleep(2000);
		
		String actStartDateTxt=brs_StartDateTxt.getAttribute("value");
		String expStartDateTxt="29/03/2021";
		
		String actEndDateTxt=brs_EndDateTxt.getAttribute("value");
		String expEndDateTxt= getCurrentDate();
		
		System.out.println("Start Date		"		+	"Actual:	"	+	actStartDateTxt		+	"Expected	"	+	expStartDateTxt);
		System.out.println("End   Date		"		+	"Actual:	"	+	actEndDateTxt		+	"Expected	"	+	expEndDateTxt);
		
		click(LoadBtn);
		Thread.sleep(10000);
		
		String actBookBal=bankRecBookBal.getText();
	 	String expBookBal="9,484.31 Cr";

		String actbankRecOutDebits=bankRecOutDebits.getText();
	    String expbankRecOutDebits="5,181.95 Dr";
	
		String actbankRecOutCredits=bankRecOutCredits.getText();
	    String expbankRecOutCredits="11,531.54 Cr";
	
		String actbankRecClearedBal=bankRecClearedBal.getText();
	    String expbankRecClearedBal="3,134.72 Cr";
	
		String actbankRecOpenBal=bankRecOpenBal.getText();
	    String expbankRecOpenBal="0.00";
		

		String actbankRecDebitCounts=bankRecDebitCounts.getText();
	    String expbankRecDebitCounts="7";
		
		String actbankRecCreditCounts=bankRecCreditCounts.getText();
	    String expbankRecCreditCounts="13";
	    
		String actbankRecBankBal=bankRecBankBal.getAttribute("value");
	    String expbankRecBankBal="0.00";
		
	    String actbankRecDiff=bankRecDif.getText();
	    String expbankRecDiff="-3,134.72";


	   System.out.println("**********************************checkBankReconciliationReport*****************************************");
	   System.out.println("BookBal             : "+actBookBal             +" Value Expected  : "+expBookBal);
       System.out.println("bankRecOutDebits    : "+actbankRecOutDebits    +" Value Expected  : "+expbankRecOutDebits);
       System.out.println("bankRecOutCredits   : "+actbankRecOutCredits   +" Value Expected  : "+expbankRecOutCredits);
       System.out.println("bankRecClearedBal   : "+actbankRecClearedBal   +" Value Expected  : "+expbankRecClearedBal);
       System.out.println("Opening Bal         : "+actbankRecOpenBal      +" Value Expected  : "+expbankRecOpenBal);
       System.out.println("bankRecDebitCounts  : "+actbankRecDebitCounts  +" Value Expected  : "+expbankRecDebitCounts);
       System.out.println("bankRecCreditCounts : "+actbankRecCreditCounts +" Value Expected  : "+expbankRecCreditCounts);
       System.out.println("bankRecBankBal      : "+actbankRecBankBal      +" Value Expected  : "+expbankRecBankBal);
       System.out.println("bankRecDifferr      : "+actbankRecDiff	      +" Value Expected  : "+expbankRecDiff);

       if(actStartDateTxt.equalsIgnoreCase(expStartDateTxt)&& actEndDateTxt.equalsIgnoreCase(expEndDateTxt) 
  	    	 &&actBookBal.equalsIgnoreCase(expBookBal) &&
  				actbankRecOutDebits.equalsIgnoreCase(expbankRecOutDebits) && actbankRecOutCredits.equalsIgnoreCase(expbankRecOutCredits) &&
  				actbankRecClearedBal.equalsIgnoreCase(expbankRecClearedBal) && actbankRecDebitCounts.equalsIgnoreCase(expbankRecDebitCounts) &&
  				actbankRecCreditCounts.equalsIgnoreCase(expbankRecCreditCounts) && actbankRecBankBal.equalsIgnoreCase(expbankRecBankBal) 
  				&& actbankRecDiff.equalsIgnoreCase(expbankRecDiff))
       {
    	   return true;
       }
       else
       {
    	   return false;
       }
		
	
		
	}
	
	public boolean checkDatePeriodasCurrentMonthinBRSReport() throws InterruptedException
	{

		Select s2=new Select(brs_DatePeriodSelect);
		s2.selectByVisibleText("Current Month");
		Thread.sleep(2000);
		brs_DatePeriodSelect.sendKeys(Keys.TAB);
		Thread.sleep(2000);
		
		Calendar cal=Calendar.getInstance();
		cal.add(Calendar.MONTH, 0);
				
		cal.set(Calendar.DATE,cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		Date first=cal.getTime();
		cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		Date last=cal.getTime();
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		
		
		String actStartDateTxt=brs_StartDateTxt.getAttribute("value");
		String expStartDateTxt=f.format(first);
		
		String actEndDateTxt=brs_EndDateTxt.getAttribute("value");
		String expEndDateTxt= f.format(last);
		
		System.out.println("Start Date		"		+	"Actual:	"	+	actStartDateTxt		+	"Expected	"	+	expStartDateTxt);
		System.out.println("End   Date		"		+	"Actual:	"	+	actEndDateTxt		+	"Expected	"	+	expEndDateTxt);
		
		click(LoadBtn);
		Thread.sleep(10000);
		
		String actBookBal=bankRecBookBal.getText();
	 	String expBookBal="9,484.31 Cr";

		String actbankRecOutDebits=bankRecOutDebits.getText();
	    String expbankRecOutDebits="255.69 Dr";
	
		String actbankRecOutCredits=bankRecOutCredits.getText();
	    String expbankRecOutCredits="1,900.85 Cr";
	
		String actbankRecClearedBal=bankRecClearedBal.getText();
	    String expbankRecClearedBal="3,134.72 Cr";
	
		String actbankRecOpenBal=bankRecOpenBal.getText();
	    String expbankRecOpenBal="7,839.15 Cr";
		

		String actbankRecDebitCounts=bankRecDebitCounts.getText();
	    String expbankRecDebitCounts="6";
		
		String actbankRecCreditCounts=bankRecCreditCounts.getText();
	    String expbankRecCreditCounts="12";
	    
		String actbankRecBankBal=bankRecBankBal.getAttribute("value");
	    String expbankRecBankBal="0.00";
		
	    String actbankRecDiff=bankRecDif.getText();
	    String expbankRecDiff="-3,134.72";


	   System.out.println("**********************************checkBankReconciliationReport*****************************************");
	   System.out.println("BookBal             : "+actBookBal             +" Value Expected  : "+expBookBal);
       System.out.println("bankRecOutDebits    : "+actbankRecOutDebits    +" Value Expected  : "+expbankRecOutDebits);
       System.out.println("bankRecOutCredits   : "+actbankRecOutCredits   +" Value Expected  : "+expbankRecOutCredits);
       System.out.println("bankRecClearedBal   : "+actbankRecClearedBal   +" Value Expected  : "+expbankRecClearedBal);
       System.out.println("Opening Bal         : "+actbankRecOpenBal      +" Value Expected  : "+expbankRecOpenBal);
       System.out.println("bankRecDebitCounts  : "+actbankRecDebitCounts  +" Value Expected  : "+expbankRecDebitCounts);
       System.out.println("bankRecCreditCounts : "+actbankRecCreditCounts +" Value Expected  : "+expbankRecCreditCounts);
       System.out.println("bankRecBankBal      : "+actbankRecBankBal      +" Value Expected  : "+expbankRecBankBal);
       System.out.println("bankRecDifferr      : "+actbankRecDiff	      +" Value Expected  : "+expbankRecDiff);

       if(actStartDateTxt.equalsIgnoreCase(expStartDateTxt)&& actEndDateTxt.equalsIgnoreCase(expEndDateTxt) 
  	    	 &&actBookBal.equalsIgnoreCase(expBookBal) &&
  				actbankRecOutDebits.equalsIgnoreCase(expbankRecOutDebits) && actbankRecOutCredits.equalsIgnoreCase(expbankRecOutCredits) &&
  				actbankRecClearedBal.equalsIgnoreCase(expbankRecClearedBal) && actbankRecDebitCounts.equalsIgnoreCase(expbankRecDebitCounts) &&
  				actbankRecCreditCounts.equalsIgnoreCase(expbankRecCreditCounts) && actbankRecBankBal.equalsIgnoreCase(expbankRecBankBal) 
  				&& actbankRecDiff.equalsIgnoreCase(expbankRecDiff))
       {
    	   return true;
       }
       else
       {
    	   return false;
       }
	
		
	}
	
	
	public boolean checkDatePeriodasTodayinBRSReport() throws InterruptedException
	{

		
		Select s2=new Select(brs_DatePeriodSelect);
		s2.selectByVisibleText("Today");
		Thread.sleep(2000);
		brs_DatePeriodSelect.sendKeys(Keys.TAB);
		Thread.sleep(2000);
		
		String actStartDateTxt=brs_StartDateTxt.getAttribute("value");
		String expStartDateTxt=getCurrentDate();
		
		String actEndDateTxt=brs_EndDateTxt.getAttribute("value");
		String expEndDateTxt= getCurrentDate();
		
		System.out.println("Start Date		"		+	"Actual:	"	+	actStartDateTxt		+	"Expected	"	+	expStartDateTxt);
		System.out.println("End   Date		"		+	"Actual:	"	+	actEndDateTxt		+	"Expected	"	+	expEndDateTxt);
		
		click(LoadBtn);
		Thread.sleep(10000);
		
		String actBookBal=bankRecBookBal.getText();
	 	String expBookBal="9,484.31 Cr";

		String actbankRecOutDebits=bankRecOutDebits.getText();
	    String expbankRecOutDebits="255.69 Dr";
	
		String actbankRecOutCredits=bankRecOutCredits.getText();
	    String expbankRecOutCredits="1,900.85 Cr";
	
		String actbankRecClearedBal=bankRecClearedBal.getText();
	    String expbankRecClearedBal="3,134.72 Cr";
	
		String actbankRecOpenBal=bankRecOpenBal.getText();
	    String expbankRecOpenBal="7,839.15 Cr";
		

		String actbankRecDebitCounts=bankRecDebitCounts.getText();
	    String expbankRecDebitCounts="6";
		
		String actbankRecCreditCounts=bankRecCreditCounts.getText();
	    String expbankRecCreditCounts="12";
	    
		String actbankRecBankBal=bankRecBankBal.getAttribute("value");
	    String expbankRecBankBal="0.00";
		
	    String actbankRecDiff=bankRecDif.getText();
	    String expbankRecDiff="-3,134.72";


	   System.out.println("**********************************checkBankReconciliationReport*****************************************");
	   System.out.println("BookBal             : "+actBookBal             +" Value Expected  : "+expBookBal);
       System.out.println("bankRecOutDebits    : "+actbankRecOutDebits    +" Value Expected  : "+expbankRecOutDebits);
       System.out.println("bankRecOutCredits   : "+actbankRecOutCredits   +" Value Expected  : "+expbankRecOutCredits);
       System.out.println("bankRecClearedBal   : "+actbankRecClearedBal   +" Value Expected  : "+expbankRecClearedBal);
       System.out.println("Opening Bal         : "+actbankRecOpenBal      +" Value Expected  : "+expbankRecOpenBal);
       System.out.println("bankRecDebitCounts  : "+actbankRecDebitCounts  +" Value Expected  : "+expbankRecDebitCounts);
       System.out.println("bankRecCreditCounts : "+actbankRecCreditCounts +" Value Expected  : "+expbankRecCreditCounts);
       System.out.println("bankRecBankBal      : "+actbankRecBankBal      +" Value Expected  : "+expbankRecBankBal);
       System.out.println("bankRecDifferr      : "+actbankRecDiff	      +" Value Expected  : "+expbankRecDiff);

       if(actStartDateTxt.equalsIgnoreCase(expStartDateTxt)&& actEndDateTxt.equalsIgnoreCase(expEndDateTxt) 
  	    	 &&actBookBal.equalsIgnoreCase(expBookBal) &&
  				actbankRecOutDebits.equalsIgnoreCase(expbankRecOutDebits) && actbankRecOutCredits.equalsIgnoreCase(expbankRecOutCredits) &&
  				actbankRecClearedBal.equalsIgnoreCase(expbankRecClearedBal) && actbankRecDebitCounts.equalsIgnoreCase(expbankRecDebitCounts) &&
  				actbankRecCreditCounts.equalsIgnoreCase(expbankRecCreditCounts) && actbankRecBankBal.equalsIgnoreCase(expbankRecBankBal) 
  				&& actbankRecDiff.equalsIgnoreCase(expbankRecDiff))
       {
    	   return true;
       }
       else
       {
    	   return false;
       }
		
	
		
	}
	
	
	public boolean checkDatePeriodasThisWeekinBRSReport() throws InterruptedException
	{
		Thread.sleep(2500);
		Select s2=new Select(brs_DatePeriodSelect);
		s2.selectByVisibleText("This Week");
		Thread.sleep(2000);
		brs_DatePeriodSelect.sendKeys(Keys.TAB);
		Thread.sleep(4000);
		
		Calendar cal=Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		Date first=cal.getTime();
		cal.add(Calendar.DATE, 6);
		Date last=cal.getTime();
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		
		String actStartDateTxt=brs_StartDateTxt.getAttribute("value");
		String expStartDateTxt=f.format(first);
		
		String actEndDateTxt=brs_EndDateTxt.getAttribute("value");
		String expEndDateTxt= f.format(last);
		
		System.out.println("Start Date		"		+	"Actual:	"	+	actStartDateTxt		+	"Expected	"	+	expStartDateTxt);
		System.out.println("End   Date		"		+	"Actual:	"	+	actEndDateTxt		+	"Expected	"	+	expEndDateTxt);
		
		click(LoadBtn);
		Thread.sleep(10000);
		
		String actBookBal=bankRecBookBal.getText();
	 	String expBookBal="9,484.31 Cr";

		String actbankRecOutDebits=bankRecOutDebits.getText();
	    String expbankRecOutDebits="255.69 Dr";
	
		String actbankRecOutCredits=bankRecOutCredits.getText();
	    String expbankRecOutCredits="1,900.85 Cr";
	
		String actbankRecClearedBal=bankRecClearedBal.getText();
	    String expbankRecClearedBal="3,134.72 Cr";
	
		String actbankRecOpenBal=bankRecOpenBal.getText();
	    String expbankRecOpenBal="7,839.15 Cr";
		

		String actbankRecDebitCounts=bankRecDebitCounts.getText();
	    String expbankRecDebitCounts="6";
		
		String actbankRecCreditCounts=bankRecCreditCounts.getText();
	    String expbankRecCreditCounts="12";
	    
		String actbankRecBankBal=bankRecBankBal.getAttribute("value");
	    String expbankRecBankBal="0.00";
		
	    String actbankRecDiff=bankRecDif.getText();
	    String expbankRecDiff="-3,134.72";


	   System.out.println("**********************************checkBankReconciliationReport*****************************************");
	   System.out.println("BookBal             : "+actBookBal             +" Value Expected  : "+expBookBal);
       System.out.println("bankRecOutDebits    : "+actbankRecOutDebits    +" Value Expected  : "+expbankRecOutDebits);
       System.out.println("bankRecOutCredits   : "+actbankRecOutCredits   +" Value Expected  : "+expbankRecOutCredits);
       System.out.println("bankRecClearedBal   : "+actbankRecClearedBal   +" Value Expected  : "+expbankRecClearedBal);
       System.out.println("Opening Bal         : "+actbankRecOpenBal      +" Value Expected  : "+expbankRecOpenBal);
       System.out.println("bankRecDebitCounts  : "+actbankRecDebitCounts  +" Value Expected  : "+expbankRecDebitCounts);
       System.out.println("bankRecCreditCounts : "+actbankRecCreditCounts +" Value Expected  : "+expbankRecCreditCounts);
       System.out.println("bankRecBankBal      : "+actbankRecBankBal      +" Value Expected  : "+expbankRecBankBal);
       System.out.println("bankRecDifferr      : "+actbankRecDiff	      +" Value Expected  : "+expbankRecDiff);

       if(actStartDateTxt.equalsIgnoreCase(expStartDateTxt)&& actEndDateTxt.equalsIgnoreCase(expEndDateTxt) 
  	    	 &&actBookBal.equalsIgnoreCase(expBookBal) &&
  				actbankRecOutDebits.equalsIgnoreCase(expbankRecOutDebits) && actbankRecOutCredits.equalsIgnoreCase(expbankRecOutCredits) &&
  				actbankRecClearedBal.equalsIgnoreCase(expbankRecClearedBal) && actbankRecDebitCounts.equalsIgnoreCase(expbankRecDebitCounts) &&
  				actbankRecCreditCounts.equalsIgnoreCase(expbankRecCreditCounts) && actbankRecBankBal.equalsIgnoreCase(expbankRecBankBal) 
  				&& actbankRecDiff.equalsIgnoreCase(expbankRecDiff))
       {
    	   return true;
       }
       else
       {
    	   return false;
       }
		
	
		
	}
	
	
	public boolean checkDatePeriodasYearToDateinBRSReport() throws InterruptedException
	{
		Thread.sleep(2500);
		Select s2=new Select(brs_DatePeriodSelect);
		s2.selectByVisibleText("Year To Date");
		Thread.sleep(1200);
		brs_DatePeriodSelect.sendKeys(Keys.TAB);
		
		String actStartDateTxt=brs_StartDateTxt.getAttribute("value");
		String expStartDateTxt="29/03/2021";
		
		String actEndDateTxt=brs_EndDateTxt.getAttribute("value");
		String expEndDateTxt= getCurrentDate();
		
		System.out.println("Start Date		"		+	"Actual:	"	+	actStartDateTxt		+	"Expected	"	+	expStartDateTxt);
		System.out.println("End   Date		"		+	"Actual:	"	+	actEndDateTxt		+	"Expected	"	+	expEndDateTxt);
		
		click(LoadBtn);
		Thread.sleep(10000);
		
		String actBookBal=bankRecBookBal.getText();
	 	String expBookBal="9,484.31 Cr";

		String actbankRecOutDebits=bankRecOutDebits.getText();
	    String expbankRecOutDebits="5,181.95 Dr";
	
		String actbankRecOutCredits=bankRecOutCredits.getText();
	    String expbankRecOutCredits="11,531.54 Cr";
	
		String actbankRecClearedBal=bankRecClearedBal.getText();
	    String expbankRecClearedBal="3,134.72 Cr";
	
		String actbankRecOpenBal=bankRecOpenBal.getText();
	    String expbankRecOpenBal="0.00";
		

		String actbankRecDebitCounts=bankRecDebitCounts.getText();
	    String expbankRecDebitCounts="7";
		
		String actbankRecCreditCounts=bankRecCreditCounts.getText();
	    String expbankRecCreditCounts="13";
	    
		String actbankRecBankBal=bankRecBankBal.getAttribute("value");
	    String expbankRecBankBal="0.00";
		
	    String actbankRecDiff=bankRecDif.getText();
	    String expbankRecDiff="-3,134.72";


	   System.out.println("**********************************checkBankReconciliationReport*****************************************");
	   System.out.println("BookBal             : "+actBookBal             +" Value Expected  : "+expBookBal);
       System.out.println("bankRecOutDebits    : "+actbankRecOutDebits    +" Value Expected  : "+expbankRecOutDebits);
       System.out.println("bankRecOutCredits   : "+actbankRecOutCredits   +" Value Expected  : "+expbankRecOutCredits);
       System.out.println("bankRecClearedBal   : "+actbankRecClearedBal   +" Value Expected  : "+expbankRecClearedBal);
       System.out.println("Opening Bal         : "+actbankRecOpenBal      +" Value Expected  : "+expbankRecOpenBal);
       System.out.println("bankRecDebitCounts  : "+actbankRecDebitCounts  +" Value Expected  : "+expbankRecDebitCounts);
       System.out.println("bankRecCreditCounts : "+actbankRecCreditCounts +" Value Expected  : "+expbankRecCreditCounts);
       System.out.println("bankRecBankBal      : "+actbankRecBankBal      +" Value Expected  : "+expbankRecBankBal);
       System.out.println("bankRecDifferr      : "+actbankRecDiff	      +" Value Expected  : "+expbankRecDiff);

       if(actStartDateTxt.equalsIgnoreCase(expStartDateTxt)&& actEndDateTxt.equalsIgnoreCase(expEndDateTxt) 
  	    	 &&actBookBal.equalsIgnoreCase(expBookBal) &&
  				actbankRecOutDebits.equalsIgnoreCase(expbankRecOutDebits) && actbankRecOutCredits.equalsIgnoreCase(expbankRecOutCredits) &&
  				actbankRecClearedBal.equalsIgnoreCase(expbankRecClearedBal) && actbankRecDebitCounts.equalsIgnoreCase(expbankRecDebitCounts) &&
  				actbankRecCreditCounts.equalsIgnoreCase(expbankRecCreditCounts) && actbankRecBankBal.equalsIgnoreCase(expbankRecBankBal) 
  				&& actbankRecDiff.equalsIgnoreCase(expbankRecDiff))
       {
    	   return true;
       }
       else
       {
    	   return false;
       }
		
	
		
	}
	
	
	public boolean checkSavingPaymentsVoucher() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		

		Thread.sleep(2000);
		click(serachMenuTextHomePage);
		
		serachMenuTextHomePage.sendKeys("Payments");
		Thread.sleep(2000);
		serachMenuTextHomePage.sendKeys(Keys.DOWN);
		serachMenuTextHomePage.sendKeys(Keys.ENTER);
		//click(searchMenuTextClick);
		 
		Thread.sleep(6000);
		new WebDriverWait(getDriver(), 300).until(ExpectedConditions.visibilityOf(newBtn));
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(newBtn));
		newBtn.click();
		Thread.sleep(4000);
		
		Thread.sleep(2000);
		
		
		
		click(caskBankAccountTxt);
		caskBankAccountTxt.sendKeys("Cheque Discounting");
		Thread.sleep(2000);
		caskBankAccountTxt.sendKeys(Keys.TAB);
		
		
		click(departmentTxt);
		departmentTxt.sendKeys("INDIA");
		Thread.sleep(2000);
		departmentTxt.sendKeys(Keys.TAB);
		
		
		
		click(payments_ChequeNoTxt);
		payments_ChequeNoTxt.sendKeys("Pmt145");
		Thread.sleep(2000);
		payments_ChequeNoTxt.sendKeys(Keys.TAB);
		
		
		
		click(select1stRow_1stColumn);
		
		click(enter_DebitACTxt);
		enter_DebitACTxt.sendKeys("Customer C");
		Thread.sleep(2000);
		enter_DebitACTxt.sendKeys(Keys.TAB);
		
		
		enter_Amount.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
		enter_Amount.sendKeys("29583.25");
		Thread.sleep(2000);
		enter_Amount.sendKeys(Keys.TAB);
		
		
		
		click(billRefNewReferenceTxt);
		click(pickBtn);
		Thread.sleep(2000);
		
		click(voucher_OkBtn);
		Thread.sleep(4000);
		
		click(saveBtn);
		Thread.sleep(2000);
		
		String expMsg="Voucher saved successfully";
		String expMsg1=": 14";
		
		String actMsg=checkValidationMessage(expMsg);
		
		System.out.println("Actual Message		"	+	actMsg		+	"Expected Message	"	+	expMsg);
		
		if(actMsg.startsWith(expMsg) && actMsg.endsWith(expMsg1))
		{
			return true;
		}
		else
		{
			return false;
		}
	
	}
	
	
	public boolean checkSavingReceiptsVoucher() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		

		Thread.sleep(2000);
		click(serachMenuTextHomePage);
		
		serachMenuTextHomePage.sendKeys("Receipts");
		Thread.sleep(2000);
		serachMenuTextHomePage.sendKeys(Keys.DOWN);
		serachMenuTextHomePage.sendKeys(Keys.ENTER);
		//click(searchMenuTextClick);
		 
		Thread.sleep(6000);
		new WebDriverWait(getDriver(), 300).until(ExpectedConditions.visibilityOf(newBtn));
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(newBtn));
		newBtn.click();
		Thread.sleep(4000);
		
		Thread.sleep(2000);
		
		click(caskBankAccountTxt);
		caskBankAccountTxt.sendKeys("Cheque Discounting");
		Thread.sleep(2000);
		caskBankAccountTxt.sendKeys(Keys.TAB);
		
		click(departmentTxt);
		departmentTxt.sendKeys("INDIA");
		Thread.sleep(2000);
		departmentTxt.sendKeys(Keys.TAB);
		
		
		
		click(receipts_ChequeNoTxt);
		receipts_ChequeNoTxt.sendKeys("Rct145");
		Thread.sleep(2000);
		receipts_ChequeNoTxt.sendKeys(Keys.TAB);
		
		
		
		click(select1stRow_1stColumn);
		
		click(enter_DebitACTxt);
		enter_DebitACTxt.sendKeys("Customer C");
		Thread.sleep(2000);
		enter_DebitACTxt.sendKeys(Keys.TAB);
		
		
		enter_Amount.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
		enter_Amount.sendKeys("11037.28");
		Thread.sleep(2000);
		enter_Amount.sendKeys(Keys.TAB);
		
		
		
		click(billRefNewReferenceTxt);
		click(pickBtn);
		Thread.sleep(2000);
		
		click(voucher_OkBtn);
		Thread.sleep(4000);
		
		click(saveBtn);
		Thread.sleep(2000);
		
		String expMsg="Voucher saved successfully";
		String expMsg1=": 11";
		
		String actMsg=checkValidationMessage(expMsg);
		
		System.out.println("Actual Message		"	+	actMsg		+	"Expected Message	"	+	expMsg);
		
		if(actMsg.startsWith(expMsg) && actMsg.endsWith(expMsg1))
		{
			return true;
		}
		else
		{
			return false;
		}
	
	}
	
	
	@FindBy(xpath="//*[@id='selectSaveOption']")
	public static WebElement brs_SaveOptionSelect;
	
	public boolean checkSaveOptionasSaveImmediatelyinBRSReport() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		Thread.sleep(2000);
		click(serachMenuTextHomePage);
		
		serachMenuTextHomePage.sendKeys("Bank Reconciliation");
		Thread.sleep(2000);
		serachMenuTextHomePage.sendKeys(Keys.ENTER);
		
		Thread.sleep(6000);
		
		click(reportbankTxt);
		reportbankTxt.sendKeys("Cheque Discounting");
		Thread.sleep(1500);
		reportbankTxt.sendKeys(Keys.TAB);
		
		Select s=new Select(BRSSelectStatusDrpdwn);
		s.selectByValue("2");
		Thread.sleep(1500);
		BRSSelectStatusDrpdwn.sendKeys(Keys.TAB);
		
		
		Select s1=new Select(BRSSelectCRDRDrpdwn);
		s1.selectByValue("2");
		Thread.sleep(1500);
		BRSSelectCRDRDrpdwn.sendKeys(Keys.TAB);
		
		
		Select s2=new Select(brs_SaveOptionSelect);
		s2.selectByVisibleText("Save Immediately");
		Thread.sleep(2000);
		brs_SaveOptionSelect.sendKeys(Keys.TAB);
		Thread.sleep(1500);
		click(LoadBtn);
		Thread.sleep(10000);
		
		String actMsg=null;
		boolean flag=false;
		String expMsg="Selected Vouchers Reconsolidated Successfully";
		for(int i=0;i<BRS_BRSStatusList.size();i++)
		{
			String data=BRS_BRSStatusList.get(i).getText();
			if(i==0 || i==3)
			{
				BRS_BRSStatusList.get(i).click();
			
			actMsg=checkValidationMessage(expMsg);
			if(actMsg.equalsIgnoreCase(expMsg))
			{
				flag=true;
			}
			}
		}
		
		if(flag)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	@FindBy(xpath="//*[@id='liUpArrow']")
	public static WebElement brsUpArrow;
	
	
	public boolean checkSaveOptionasSaveBatchmodeinBRSReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{

		Thread.sleep(2000);
		click(serachMenuTextHomePage);
		
		serachMenuTextHomePage.sendKeys("Bank Reconciliation");
		Thread.sleep(2000);
		serachMenuTextHomePage.sendKeys(Keys.ENTER);
		
		Thread.sleep(8000);
		
		click(reportbankTxt);
		reportbankTxt.sendKeys("Cheque Discounting");
		Thread.sleep(1500);
		reportbankTxt.sendKeys(Keys.TAB);
		
		Select s=new Select(BRSSelectStatusDrpdwn);
		s.selectByValue("2");
		Thread.sleep(1500);
		BRSSelectStatusDrpdwn.sendKeys(Keys.TAB);
		
		
		Select s1=new Select(BRSSelectCRDRDrpdwn);
		s1.selectByValue("2");
		Thread.sleep(1500);
		BRSSelectCRDRDrpdwn.sendKeys(Keys.TAB);
		
		
		Select s2=new Select(brs_SaveOptionSelect);
		s2.selectByVisibleText("Save Batch mode");
		Thread.sleep(2000);
		brs_SaveOptionSelect.sendKeys(Keys.TAB);
		Thread.sleep(1500);
		click(LoadBtn);
		Thread.sleep(10000);
		
		
		String expMsg="Selected Vouchers Reconsolidated Successfully";
		for(int i=0;i<BRS_BRSStatusList.size();i++)
		{
			String data=BRS_BRSStatusList.get(i).getText();
			if(i==1 || i==2)
			{
				BRS_BRSStatusList.get(i).click();
			}
			
		}
		
		Thread.sleep(2500);
      
		//click(brsUpArrow);
		//Thread.sleep(1200);
		
		ClickUsingJs(BRsaveBtn);
		Thread.sleep(1200);
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
	
	@FindBy(xpath = "//button[@id='btnPendingBills']")
	public static WebElement BRSPendingBillsBtn;
	
	@FindBy(xpath = "//*[@id='btnRaiseReceipt']")
	public static WebElement BRSRaisingReceipts;
	
	@FindBy(xpath = "//*[@id='liRaiseReceipt1']")
	public static WebElement BRSRaisingReceiptsFIFO;

	@FindBy(xpath = "//*[@id='btnRaisePayment']")
	public static WebElement BRSRaisingPayments;
	
	@FindBy(xpath = "//*[@id='liRaisePayment2']")
	public static WebElement BRSRaisingPaymentsFIFO;

	@FindBy(xpath = "//li[@id='liPendingBills3']")
	public static WebElement BRSPendingBillsReceiptsVATBtn;
	
	@FindBy(xpath = "//li[@id='liPendingBills5']")
	public static WebElement BRSPendingBillsPaymentsVATBtn;

	@FindBy(xpath = "//*[@id='liRaisePayment1']")
	public static WebElement BRS_RaisePay_PayVAT;
	
	
	@FindBy(xpath = "//input[@id='id_body_16777330']")
	public static WebElement recVATTaxCode;
	
	@FindBy(xpath = "//input[@id='id_body_16777332']")
	public static WebElement payVATTaxCode;
	
	public boolean checkSavingRecepitsVATVoucherFromPendingBillsinBRSReport() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		Thread.sleep(1999);
		
		//click(brsUpArrow);
		Thread.sleep(1200);

		click(BRSPendingBillsBtn);

		Thread.sleep(1999);

		click(BRSPendingBillsReceiptsVATBtn);

		Thread.sleep(5000);

		ArrayList<String> openTabs = new ArrayList<String>(getDriver().getWindowHandles());

		int actOpenWindowsCount = getDriver().getWindowHandles().size();
		int expOpenWindowsCount = 2;

		System.out.println("Number of Windows  : " + actOpenWindowsCount + "  Value Expected  " + expOpenWindowsCount);

		Thread.sleep(1000);

		getDriver().switchTo().window(openTabs.get(1));

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(caskBankAccountTxt));
		String actCashBankAccTxt=caskBankAccountTxt.getAttribute("value");
		String expCashBankAccTxt="Cheque Discounting";
		System.out.println("Cash Bank Acc	"	+	"Actual		"	+	actCashBankAccTxt	+	"Expected	"	+	expCashBankAccTxt);
		
		click(departmentTxt);
		departmentTxt.sendKeys("AMERICA");
		Thread.sleep(2000);
		departmentTxt.sendKeys(Keys.TAB);

		
		Thread.sleep(2999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_1stColumn));
		select1stRow_1stColumn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_AccountTxt));
		enter_AccountTxt.click();
		enter_AccountTxt.sendKeys("Customer B");
		Thread.sleep(2999);
		
		enter_AccountTxt.sendKeys(Keys.TAB);
		Thread.sleep(2999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(recVATTaxCode));
		recVATTaxCode.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME);
		recVATTaxCode.sendKeys("STD Rate");
		Thread.sleep(1999);
		recVATTaxCode.sendKeys(Keys.TAB);
		

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_Amount));
		enter_Amount.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME);
		enter_Amount.sendKeys("1425.69");
		Thread.sleep(1999);
		enter_Amount.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		click(billRefPickIcon);

		Thread.sleep(1000);
		click(billRefOkBtn);

		Thread.sleep(1000);
		click(saveBtn);
		Thread.sleep(3500);
		
		String expMsg="Voucher saved successfully";
		String actMsg=checkValidationMessage(expMsg);
		String expMsg1=": 1";
		getDriver().switchTo().window(openTabs.get(1)).close();
		Thread.sleep(2000);

		getDriver().switchTo().window(openTabs.get(0));

		if (actCashBankAccTxt.equalsIgnoreCase(expCashBankAccTxt)&&actMsg.startsWith(expMsg) && actMsg.endsWith(expMsg1)) {
						
			return true;
		} else {
			
			return false;
		}
	}
	
	
	public boolean checkSavingPaymentsVATVoucherFromPendingBillsinBRSReport() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		Thread.sleep(1999);

		click(BRSPendingBillsBtn);

		Thread.sleep(1999);

		click(BRSPendingBillsPaymentsVATBtn);

		Thread.sleep(5000);

		ArrayList<String> openTabs = new ArrayList<String>(getDriver().getWindowHandles());

		int actOpenWindowsCount = getDriver().getWindowHandles().size();
		int expOpenWindowsCount = 2;

		System.out.println("Number of Windows  : " + actOpenWindowsCount + "  Value Expected  " + expOpenWindowsCount);

		Thread.sleep(1000);

		getDriver().switchTo().window(openTabs.get(1));

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(caskBankAccountTxt));
		String actCashBankAccTxt=caskBankAccountTxt.getAttribute("value");
		String expCashBankAccTxt="Cheque Discounting";
		System.out.println("Cash Bank Acc	"	+	"Actual		"	+	actCashBankAccTxt	+	"Expected	"	+	expCashBankAccTxt);
		click(departmentTxt);
		departmentTxt.sendKeys("AMERICA");
		Thread.sleep(2000);
		departmentTxt.sendKeys(Keys.TAB);

		
		Thread.sleep(2999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_1stColumn));
		select1stRow_1stColumn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_AccountTxt));
		enter_AccountTxt.click();
		enter_AccountTxt.sendKeys("Customer B");
		Thread.sleep(2999);
		
		enter_AccountTxt.sendKeys(Keys.TAB);
		Thread.sleep(2999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(payVATTaxCode));
		payVATTaxCode.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME);
		payVATTaxCode.sendKeys("STD Rate");
		Thread.sleep(1999);
		payVATTaxCode.sendKeys(Keys.TAB);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_Amount));
		enter_Amount.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME);
		enter_Amount.sendKeys("5209.41");
		Thread.sleep(1999);
		enter_Amount.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		click(billRefPickIcon);

		Thread.sleep(1000);
		click(billRefOkBtn);

		Thread.sleep(1000);
		click(saveBtn);
		Thread.sleep(3500);
		
		String expMsg="Voucher saved successfully";
		String actMsg=checkValidationMessage(expMsg);
		String expMsg1=": 1";
		getDriver().switchTo().window(openTabs.get(1)).close();
		Thread.sleep(2000);

		getDriver().switchTo().window(openTabs.get(0));

		if (actCashBankAccTxt.equalsIgnoreCase(expCashBankAccTxt)&&actMsg.startsWith(expMsg) && actMsg.endsWith(expMsg1)) {
						
			return true;
		} else {
			
			return false;
		}
	}
	
	
	public boolean checkSavingRecepitsFIFOVoucherFromRaiseReceiptsinBRSReport() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		Thread.sleep(2000);
		
		click(clearBtn);
		Thread.sleep(2000);
		click(reportbankTxt);
		reportbankTxt.sendKeys("HDFC");
		Thread.sleep(1999);
		reportbankTxt.sendKeys(Keys.TAB);
		
		click(LoadBtn);
		Thread.sleep(8000);
		click(BRSRaisingReceipts);

		Thread.sleep(1999);

		click(BRSRaisingReceiptsFIFO);

		Thread.sleep(5000);

		ArrayList<String> openTabs = new ArrayList<String>(getDriver().getWindowHandles());

		int actOpenWindowsCount = getDriver().getWindowHandles().size();
		int expOpenWindowsCount = 2;

		System.out.println("Number of Windows  : " + actOpenWindowsCount + "  Value Expected  " + expOpenWindowsCount);

		Thread.sleep(1000);

		getDriver().switchTo().window(openTabs.get(1));

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(caskBankAccountTxt));
		String actCashBankAccTxt=caskBankAccountTxt.getAttribute("value");
		String expCashBankAccTxt="HDFC";
		
		System.out.println("Cash Bank Acc	"	+	"Actual		"	+	actCashBankAccTxt	+	"Expected	"	+	expCashBankAccTxt);
		
		
		
		click(departmentTxt);
		departmentTxt.sendKeys("INDIA");
		Thread.sleep(2000);
		departmentTxt.sendKeys(Keys.TAB);

		
		Thread.sleep(2999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_1stColumn));
		select1stRow_1stColumn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_AccountTxt));
		enter_AccountTxt.click();
		enter_AccountTxt.sendKeys("Customer B");
		Thread.sleep(1999);
		
		enter_AccountTxt.sendKeys(Keys.TAB);
		Thread.sleep(1999);

		

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_Amount));
		enter_Amount.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME);
		enter_Amount.sendKeys("18321.45");
		Thread.sleep(1999);
		enter_Amount.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		/*click(billRefPickIcon);

		Thread.sleep(1000);
		click(billRefOkBtn);*/

		Thread.sleep(1000);
		click(saveBtn);
		Thread.sleep(3500);
		
		String expMsg="Voucher saved successfully";
		String actMsg=checkValidationMessage(expMsg);
		String expMsg1=": 4";
		getDriver().switchTo().window(openTabs.get(1)).close();
		Thread.sleep(2000);

		getDriver().switchTo().window(openTabs.get(0));

		if (actCashBankAccTxt.equalsIgnoreCase(expCashBankAccTxt)&&actMsg.startsWith(expMsg) && actMsg.endsWith(expMsg1)) {
						
			return true;
		} else {
			
			return false;
		}
	}
	
	
	
	
	public boolean checkSavingPaymentsFIFOVoucherFromRaisePaymentsinBRSReport() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		Thread.sleep(2000);
		
		click(clearBtn);
		Thread.sleep(2000);
		click(reportbankTxt);
		reportbankTxt.sendKeys("HDFC");
		Thread.sleep(1999);
		reportbankTxt.sendKeys(Keys.TAB);
		
		click(LoadBtn);
		Thread.sleep(8000);
		click(BRSRaisingPayments);

		Thread.sleep(1999);

		click(BRSRaisingPaymentsFIFO);

		Thread.sleep(5000);

		ArrayList<String> openTabs = new ArrayList<String>(getDriver().getWindowHandles());

		int actOpenWindowsCount = getDriver().getWindowHandles().size();
		int expOpenWindowsCount = 2;

		System.out.println("Number of Windows  : " + actOpenWindowsCount + "  Value Expected  " + expOpenWindowsCount);

		Thread.sleep(1000);

		getDriver().switchTo().window(openTabs.get(1));

		Thread.sleep(2000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(caskBankAccountTxt));
		String actCashBankAccTxt=caskBankAccountTxt.getAttribute("value");
		String expCashBankAccTxt="HDFC";
		
		System.out.println("Cash Bank Acc	"	+	"Actual		"	+	actCashBankAccTxt	+	"Expected	"	+	expCashBankAccTxt);
		
		
		
		click(departmentTxt);
		departmentTxt.sendKeys("INDIA");
		Thread.sleep(2000);
		departmentTxt.sendKeys(Keys.TAB);

		
		Thread.sleep(2999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_1stColumn));
		select1stRow_1stColumn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_AccountTxt));
		enter_AccountTxt.click();
		enter_AccountTxt.sendKeys("Customer B");
		Thread.sleep(1999);
		
		enter_AccountTxt.sendKeys(Keys.TAB);
		Thread.sleep(1999);

	
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_Amount));
		enter_Amount.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME);
		enter_Amount.sendKeys("999.99");
		Thread.sleep(1999);
		enter_Amount.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		/*click(billRefPickIcon);

		Thread.sleep(1000);
		click(billRefOkBtn);

		Thread.sleep(1000);*/
		click(saveBtn);
		Thread.sleep(3500);
		
		String expMsg="Voucher saved successfully";
		String actMsg=checkValidationMessage(expMsg);
		String expMsg1=": 3";
		getDriver().switchTo().window(openTabs.get(1)).close();
		Thread.sleep(2000);

		getDriver().switchTo().window(openTabs.get(0));

		if (actCashBankAccTxt.equalsIgnoreCase(expCashBankAccTxt)&&actMsg.startsWith(expMsg) && actMsg.endsWith(expMsg1)) {
						
			return true;
		} else {
			
			return false;
		}
	}
	
	@FindBy(xpath = "//*[@class='icon-backtrack hiconright2']")
	private static WebElement BRSBackTrackBtn;
	
	public boolean checkBacktrackinBRSReport() throws InterruptedException
	{
		

		Thread.sleep(2999);

	

		int count = brsDocNoList.size();

		for (int i = 0; i < count; i++) {
			String data = brsDocNoList.get(i).getText();

			System.out.println(" DATA : " + data);

			Thread.sleep(2000);
			if (data.equalsIgnoreCase("")) {
				Thread.sleep(2000);

				BRS_BRSStatusList.get(i).click();

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

		String expDocno = "1";

		String expDepartment = "";

		String expCurrency = "";

		String expCashAndBankAccount = "";

		String expDate = "";
		// AS BACK UP TAKEN ON THE DATE

		String actAccountR1 = select1stRow_1stColumn.getText();
		String actAmountR1 = select1stRow_2ndColumn.getText();
		String actrefR1 = select1stRow_3rdColumn.getText();

		String expAccountR1 = "";

		String expAmountR1 = "";

		String exprefR1 = "";

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
			
			return true;
		} else {
			
			return false;
		}
	
	}
	
	public boolean checkSavingReceiptsVoucherforConsolidationAmountinBRSReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		
		Thread.sleep(2000);
		click(serachMenuTextHomePage);
		
		serachMenuTextHomePage.sendKeys("Receipts");
		Thread.sleep(2000);
		serachMenuTextHomePage.sendKeys(Keys.DOWN);
		serachMenuTextHomePage.sendKeys(Keys.ENTER);
		//click(searchMenuTextClick);
		 
		Thread.sleep(6000);
		new WebDriverWait(getDriver(), 300).until(ExpectedConditions.visibilityOf(newBtn));
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(newBtn));
		newBtn.click();
		Thread.sleep(4000);
		
		Thread.sleep(2000);
		
		click(caskBankAccountTxt);
		caskBankAccountTxt.sendKeys("Cheque Discounting");
		Thread.sleep(2000);
		caskBankAccountTxt.sendKeys(Keys.TAB);
		
		click(departmentTxt);
		departmentTxt.sendKeys("INDIA");
		Thread.sleep(2000);
		departmentTxt.sendKeys(Keys.TAB);
		
		
		
		click(receipts_ChequeNoTxt);
		receipts_ChequeNoTxt.sendKeys("CH0001");
		Thread.sleep(2000);
		receipts_ChequeNoTxt.sendKeys(Keys.TAB);
		
		
		
		click(select1stRow_1stColumn);
		
		click(enter_DebitACTxt);
		enter_DebitACTxt.sendKeys("Customer A");
		Thread.sleep(2000);
		enter_DebitACTxt.sendKeys(Keys.TAB);
		
		
		enter_Amount.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
		enter_Amount.sendKeys("10.00");
		Thread.sleep(2000);
		enter_Amount.sendKeys(Keys.TAB);
		
		
		
		click(billRefNewReferenceTxt);
		click(pickBtn);
		Thread.sleep(2000);
		
		click(voucher_OkBtn);
		Thread.sleep(4000);
		
		
		click(select2ndRow_1stColumn);
		
		click(enter_DebitACTxt);
		enter_DebitACTxt.sendKeys("Customer B");
		Thread.sleep(2000);
		enter_DebitACTxt.sendKeys(Keys.TAB);
		
		
		enter_Amount.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
		enter_Amount.sendKeys("20.00");
		Thread.sleep(2000);
		enter_Amount.sendKeys(Keys.TAB);
		
		
		
		click(billRefNewReferenceTxt);
		click(pickBtn);
		Thread.sleep(2000);
		
		click(voucher_OkBtn);
		Thread.sleep(4000);
		
		click(saveBtn);
		Thread.sleep(2000);
		
		String expMsg="Voucher saved successfully";
		String expMsg1=": 12";
		
		String actMsg=checkValidationMessage(expMsg);
		
		System.out.println("Actual Message		"	+	actMsg		+	"Expected Message	"	+	expMsg);
		
		if(actMsg.startsWith(expMsg) && actMsg.endsWith(expMsg1))
		{
			return true;
		}
		else
		{
			return false;
		}
	
	
		
	}
	
	
	public boolean checkSavingPaymentsVoucherforConsolidationAmountinBRSReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		Thread.sleep(2000);
		click(serachMenuTextHomePage);
		
		serachMenuTextHomePage.sendKeys("Payments");
		Thread.sleep(2000);
		serachMenuTextHomePage.sendKeys(Keys.DOWN);
		serachMenuTextHomePage.sendKeys(Keys.ENTER);
		//click(searchMenuTextClick);
		 
		Thread.sleep(6000);
		new WebDriverWait(getDriver(), 300).until(ExpectedConditions.visibilityOf(newBtn));
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(newBtn));
		newBtn.click();
		Thread.sleep(4000);
		
		Thread.sleep(2000);
		
		
		
		click(caskBankAccountTxt);
		caskBankAccountTxt.sendKeys("Cheque Discounting");
		Thread.sleep(2000);
		caskBankAccountTxt.sendKeys(Keys.TAB);
		
		
		click(departmentTxt);
		departmentTxt.sendKeys("INDIA");
		Thread.sleep(2000);
		departmentTxt.sendKeys(Keys.TAB);
		
		
		
		click(payments_ChequeNoTxt);
		payments_ChequeNoTxt.sendKeys("CH0002");
		Thread.sleep(2000);
		payments_ChequeNoTxt.sendKeys(Keys.TAB);
		
		
		
		click(select1stRow_1stColumn);
		
		click(enter_DebitACTxt);
		enter_DebitACTxt.sendKeys("Customer C");
		Thread.sleep(2000);
		enter_DebitACTxt.sendKeys(Keys.TAB);
		
		
		enter_Amount.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
		enter_Amount.sendKeys("50.00");
		Thread.sleep(2000);
		enter_Amount.sendKeys(Keys.TAB);
		
		
		
		click(billRefNewReferenceTxt);
		click(pickBtn);
		Thread.sleep(2000);
		
		click(voucher_OkBtn);
		Thread.sleep(4000);
		
		
		click(select2ndRow_1stColumn);
		
		click(enter_DebitACTxt);
		enter_DebitACTxt.sendKeys("Customer B");
		Thread.sleep(2000);
		enter_DebitACTxt.sendKeys(Keys.TAB);
		
		
		enter_Amount.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
		enter_Amount.sendKeys("70.00");
		Thread.sleep(2000);
		enter_Amount.sendKeys(Keys.TAB);
		
		
		
		click(billRefNewReferenceTxt);
		click(pickBtn);
		Thread.sleep(2000);
		
		click(voucher_OkBtn);
		Thread.sleep(4000);
		
		click(saveBtn);
		Thread.sleep(2000);
		
		String expMsg="Voucher saved successfully";
		String expMsg1=": 15";
		
		String actMsg=checkValidationMessage(expMsg);
		
		System.out.println("Actual Message		"	+	actMsg		+	"Expected Message	"	+	expMsg);
		
		if(actMsg.startsWith(expMsg) && actMsg.endsWith(expMsg1))
		{
			return true;
		}
		else
		{
			return false;
		}
			
	}
	
	
	@FindBy(xpath="//*[@id='chkShow']")
	public static WebElement BRS_showConsolidatedChkBox;
	
	@FindBy(xpath="//*[@id='chkShow']/../span")
	public static WebElement BRS_showConsolidatedChkBoxSelected;
	
	
	
	public boolean checkConsolidationAmountinBRSReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{

		Thread.sleep(2000);
		click(serachMenuTextHomePage);
		
		serachMenuTextHomePage.sendKeys("Bank Reconciliation");
		Thread.sleep(2000);
		serachMenuTextHomePage.sendKeys(Keys.ENTER);
		
		Thread.sleep(6000);
		
		click(reportbankTxt);
		reportbankTxt.sendKeys("Cheque Discounting");
		Thread.sleep(1500);
		reportbankTxt.sendKeys(Keys.TAB);
		
		Select s=new Select(BRSSelectStatusDrpdwn);
		s.selectByValue("0");
		Thread.sleep(1500);
		BRSSelectStatusDrpdwn.sendKeys(Keys.TAB);
		
		
		Select s1=new Select(BRSSelectCRDRDrpdwn);
		s1.selectByValue("2");
		Thread.sleep(1500);
		BRSSelectCRDRDrpdwn.sendKeys(Keys.TAB);
		
		click(LoadBtn);
		Thread.sleep(12000);
		
		
		ArrayList<String>brsRow1ArrayList=new ArrayList<String>();
		for(int i=0;i<bankRecRow1List.size();i++)
		{
			brsRow1ArrayList.add(bankRecRow1List.get(i).getText());
		}
		
		String actBRSRow1List=brsRow1ArrayList.toString();
		String expBRSRow1List="[1, Pending, CH0001, "+getCurrentDate()+", Rct:12, "+getCurrentDate()+", 10.00, 0.00, Receipts, , , , ]";
		
		System.out.println("Actual Row1 List	"	+	actBRSRow1List);
		System.out.println("Expect Row1 List	"	+	expBRSRow1List);
		
		
		ArrayList<String>brsRow2ArrayList=new ArrayList<String>();
		for(int i=0;i<bankRecRow2List.size();i++)
		{
			brsRow2ArrayList.add(bankRecRow2List.get(i).getText());
		}
		
		String actBRSRow2List=brsRow2ArrayList.toString();
		String expBRSRow2List="[2, Pending, CH0001, "+getCurrentDate()+", Rct:12, "+getCurrentDate()+", 20.00, 0.00, Receipts, , , , ]";
		
		System.out.println("Actual Row2 List	"	+	actBRSRow2List);
		System.out.println("Expect Row2 List	"	+	expBRSRow2List);
		
		ArrayList<String>brsRow3ArrayList=new ArrayList<String>();
		for(int i=0;i<bankRecRow3List.size();i++)
		{
			brsRow3ArrayList.add(bankRecRow3List.get(i).getText());
		}
		
		String actBRSRow3List=brsRow3ArrayList.toString();
		String expBRSRow3List="[3, Pending, , "+getCurrentDate()+", NDT57:1, "+getCurrentDate()+", 1,425.69, 0.00, Receipts VAT, , , , ]";
		
		System.out.println("Actual Row3 List	"	+	actBRSRow3List);
		System.out.println("Expect Row3 List	"	+	expBRSRow3List);
		
		
		ArrayList<String>brsRow4ArrayList=new ArrayList<String>();
		for(int i=0;i<bankRecRow4List.size();i++)
		{
			brsRow4ArrayList.add(bankRecRow4List.get(i).getText());
		}
		
		String actBRSRow4List=brsRow4ArrayList.toString();
		String expBRSRow4List="[4, Pending, CH0002, "+getCurrentDate()+", Pmt:15, "+getCurrentDate()+", 0.00, 70.00, Payments, , , , ]";
		
		System.out.println("Actual Row4 List	"	+	actBRSRow4List);
		System.out.println("Expect Row4 List	"	+	expBRSRow4List);
		
		
		ArrayList<String>brsRow5ArrayList=new ArrayList<String>();
		for(int i=0;i<bankRecRow5List.size();i++)
		{
			brsRow5ArrayList.add(bankRecRow5List.get(i).getText());
		}
		
		String actBRSRow5List=brsRow5ArrayList.toString();
		String expBRSRow5List="[5, Pending, CH0002, "+getCurrentDate()+", Pmt:15, "+getCurrentDate()+", 0.00, 50.00, Payments, , , , ]";
		
		System.out.println("Actual Row5 List	"	+	actBRSRow5List);
		System.out.println("Expect Row5 List	"	+	expBRSRow5List);
		
		ArrayList<String>brsRow6ArrayList=new ArrayList<String>();
		for(int i=0;i<bankRecRow6List.size();i++)
		{
			brsRow6ArrayList.add(bankRecRow6List.get(i).getText());
		}
		
		String actBRSRow6List=brsRow6ArrayList.toString();
		String expBRSRow6List="[6, Pending, , "+getCurrentDate()+", NDT58:1, "+getCurrentDate()+", 0.00, 5,209.41, Payments VAT, , , , ]";
		
		System.out.println("Actual Row6 List	"	+	actBRSRow6List);
		System.out.println("Expect Row6 List	"	+	expBRSRow6List);
		
		
		   String actBookBal=bankRecBookBal.getText();
		   String expBookBal="35,956.99 Cr";

			String actbankRecOutDebits=bankRecOutDebits.getText();
		    String expbankRecOutDebits="1,455.69 Dr";
		
			String actbankRecOutCredits=bankRecOutCredits.getText();
		    String expbankRecOutCredits="5,329.41 Cr";
		
			String actbankRecClearedBal=bankRecClearedBal.getText();
		    String expbankRecClearedBal="32,083.27 Cr";
		
			String actbankRecOpenBal=bankRecOpenBal.getText();
		    String expbankRecOpenBal="0.00";
			

			String actbankRecDebitCounts=bankRecDebitCounts.getText();
		    String expbankRecDebitCounts="3";
			
			String actbankRecCreditCounts=bankRecCreditCounts.getText();
		    String expbankRecCreditCounts="3";
		    
			String actbankRecBankBal=bankRecBankBal.getAttribute("value");
		    String expbankRecBankBal="0.00";
		    
		    String actbankRecDiff=bankRecDif.getText();
		    String expbankRecDiff="-32,083.27";

			
			
		   System.out.println("**********************************checkBankReconciliationReportBeforeConsolodation*****************************************");
		   System.out.println("BookBal             : "+actBookBal             +" Value Expected  : "+expBookBal);
	       System.out.println("bankRecOutDebits    : "+actbankRecOutDebits    +" Value Expected  : "+expbankRecOutDebits);
	       System.out.println("bankRecOutCredits   : "+actbankRecOutCredits   +" Value Expected  : "+expbankRecOutCredits);
	       System.out.println("bankRecClearedBal   : "+actbankRecClearedBal   +" Value Expected  : "+expbankRecClearedBal);
	       System.out.println("Opening Bal         : "+actbankRecOpenBal      +" Value Expected  : "+expbankRecOpenBal);
	       System.out.println("bankRecDebitCounts  : "+actbankRecDebitCounts  +" Value Expected  : "+expbankRecDebitCounts);
	       System.out.println("bankRecCreditCounts : "+actbankRecCreditCounts +" Value Expected  : "+expbankRecCreditCounts);
	       System.out.println("bankRecBankBal      : "+actbankRecBankBal      +" Value Expected  : "+expbankRecBankBal);
	       System.out.println("bankRecDifferr      : "+actbankRecDiff	      +" Value Expected  : "+expbankRecDiff);
 
	       
	       
	       if(BRS_showConsolidatedChkBox.isSelected()==false)
	       {
	    	   BRS_showConsolidatedChkBoxSelected.click();
	       }
	       Thread.sleep(1500);
	       
	       click(LoadBtn);
	       Thread.sleep(12000);
	       
	       ArrayList<String>brsRow1AfterConArrayList=new ArrayList<String>();
			for(int i=0;i<bankRecRow1List.size();i++)
			{
				brsRow1AfterConArrayList.add(bankRecRow1List.get(i).getText());
			}
			
			String actBRSRow1AfterConList=brsRow1AfterConArrayList.toString();
			String expBRSRow1AfterConList="[1, Pending, CH0001, "+getCurrentDate()+", Rct:12, "+getCurrentDate()+", 30.00, 0.00, Receipts, , , , ]";
			
			System.out.println("Actual Row1 List	"	+	actBRSRow1AfterConList);
			System.out.println("Expect Row1 List	"	+	expBRSRow1AfterConList);
			
			
			ArrayList<String>brsRow2AfterConArrayList=new ArrayList<String>();
			for(int i=0;i<bankRecRow2List.size();i++)
			{
				brsRow2AfterConArrayList.add(bankRecRow2List.get(i).getText());
			}
			
			String actBRSRow2AfterConList=brsRow2AfterConArrayList.toString();
			String expBRSRow2AfterConList="[2, Pending, , "+getCurrentDate()+", NDT57:1, "+getCurrentDate()+", 1,425.69, 0.00, Receipts VAT, , , , ]";
			
			System.out.println("Actual Row2 List	"	+	actBRSRow2AfterConList);
			System.out.println("Expect Row2 List	"	+	expBRSRow2AfterConList);
			
			ArrayList<String>brsRow3AfterConArrayList=new ArrayList<String>();
			for(int i=0;i<bankRecRow3List.size();i++)
			{
				brsRow3AfterConArrayList.add(bankRecRow3List.get(i).getText());
			}
			
			String actBRSRow3AfterConList=brsRow3AfterConArrayList.toString();
			String expBRSRow3AfterConList="[3, Pending, CH0002, "+getCurrentDate()+", Pmt:15, "+getCurrentDate()+", 0.00, 120.00, Payments, , , , ]";
			
			System.out.println("Actual Row3 List	"	+	actBRSRow3AfterConList);
			System.out.println("Expect Row3 List	"	+	expBRSRow3AfterConList);
			
			
			ArrayList<String>brsRow4AfterConArrayList=new ArrayList<String>();
			for(int i=0;i<bankRecRow4List.size();i++)
			{
				brsRow4AfterConArrayList.add(bankRecRow4List.get(i).getText());
			}
			
			String actBRSRow4AfterConList=brsRow4AfterConArrayList.toString();
			String expBRSRow4AfterConList="[4, Pending, , "+getCurrentDate()+", NDT58:1, "+getCurrentDate()+", 0.00, 5,209.41, Payments VAT, , , , ]";
			
			System.out.println("Actual Row4 List	"	+	actBRSRow4AfterConList);
			System.out.println("Expect Row4 List	"	+	expBRSRow4AfterConList);
			
	       
			   String actBookBal1=bankRecBookBal.getText();
			   String expBookBal1="35,956.99 Cr";

				String actbankRecOutDebits1=bankRecOutDebits.getText();
			    String expbankRecOutDebits1="1,455.69 Dr";
			
				String actbankRecOutCredits1=bankRecOutCredits.getText();
			    String expbankRecOutCredits1="5,329.41 Cr";
			
				String actbankRecClearedBal1=bankRecClearedBal.getText();
			    String expbankRecClearedBal1="32,083.27 Cr";
			
				String actbankRecOpenBal1=bankRecOpenBal.getText();
			    String expbankRecOpenBal1="0.00";
				

				String actbankRecDebitCounts1=bankRecDebitCounts.getText();
			    String expbankRecDebitCounts1="2";
				
				String actbankRecCreditCounts1=bankRecCreditCounts.getText();
			    String expbankRecCreditCounts1="2";
			    
				String actbankRecBankBal1=bankRecBankBal.getAttribute("value");
			    String expbankRecBankBal1="0.00";
			    
			    String actbankRecDiff1=bankRecDif.getText();
			    String expbankRecDiff1="-32,083.27";

				
				
			   System.out.println("**********************************checkBankReconciliationReportAfterConsolodation*****************************************");
			   System.out.println("BookBal             : "+actBookBal1             +" Value Expected  : "+expBookBal1);
		       System.out.println("bankRecOutDebits    : "+actbankRecOutDebits1    +" Value Expected  : "+expbankRecOutDebits1);
		       System.out.println("bankRecOutCredits   : "+actbankRecOutCredits1   +" Value Expected  : "+expbankRecOutCredits1);
		       System.out.println("bankRecClearedBal   : "+actbankRecClearedBal1   +" Value Expected  : "+expbankRecClearedBal1);
		       System.out.println("Opening Bal         : "+actbankRecOpenBal1      +" Value Expected  : "+expbankRecOpenBal1);
		       System.out.println("bankRecDebitCounts  : "+actbankRecDebitCounts1  +" Value Expected  : "+expbankRecDebitCounts1);
		       System.out.println("bankRecCreditCounts : "+actbankRecCreditCounts1+" Value Expected  : "+expbankRecCreditCounts1);
		       System.out.println("bankRecBankBal      : "+actbankRecBankBal1      +" Value Expected  : "+expbankRecBankBal1);
		       System.out.println("bankRecDifferr      : "+actbankRecDiff1	      +" Value Expected  : "+expbankRecDiff1);
	 
			
			
			
	       
		if(actBRSRow1List.equalsIgnoreCase(expBRSRow1List) && actBRSRow2List.equalsIgnoreCase(expBRSRow2List) && 
				actBRSRow3List.equalsIgnoreCase(expBRSRow3List) && actBookBal.equalsIgnoreCase(expBookBal) &&
				actbankRecOutDebits.equalsIgnoreCase(expbankRecOutDebits) && actbankRecOutCredits.equalsIgnoreCase(expbankRecOutCredits) &&
				actbankRecClearedBal.equalsIgnoreCase(expbankRecClearedBal) && actbankRecDebitCounts.equalsIgnoreCase(expbankRecDebitCounts) &&
				actbankRecCreditCounts.equalsIgnoreCase(expbankRecCreditCounts) && actbankRecBankBal.equalsIgnoreCase(expbankRecBankBal)
				&& actbankRecDiff.equalsIgnoreCase(expbankRecDiff)
				&& actBRSRow1AfterConList.equalsIgnoreCase(expBRSRow1AfterConList) &&  actBRSRow2AfterConList.equalsIgnoreCase(expBRSRow2AfterConList)
				&& actBRSRow3AfterConList.equalsIgnoreCase(expBRSRow3AfterConList) && actBRSRow4AfterConList.equalsIgnoreCase(expBRSRow4AfterConList)
				&& actBookBal1.equalsIgnoreCase(expBookBal1) &&
				actbankRecOutDebits1.equalsIgnoreCase(expbankRecOutDebits1) && actbankRecOutCredits1.equalsIgnoreCase(expbankRecOutCredits1) &&
				actbankRecClearedBal1.equalsIgnoreCase(expbankRecClearedBal1) && actbankRecDebitCounts1.equalsIgnoreCase(expbankRecDebitCounts1) &&
				actbankRecCreditCounts1.equalsIgnoreCase(expbankRecCreditCounts1) && actbankRecBankBal1.equalsIgnoreCase(expbankRecBankBal1)
				&& actbankRecDiff1.equalsIgnoreCase(expbankRecDiff1)
				
				) 
		{
		return true;
	}
	else
	{
		return false;
	}
	
	}
	
	@FindBy(xpath="//*[@id='dvReportDetails']//table//tr[12]//td[13]")
	public static WebElement balanceTxtinLedger;
	
	public boolean checkBRSBalanceinLedgerReport() throws InterruptedException
	{
		
		focusMainSearch("Ledger");
		Thread.sleep(5000);
		
		sl_MasterTypeTxt.click();
		sl_MasterTypeTxt.sendKeys("Cheque Discounting");
		Thread.sleep(1500);
		sl_MasterTypeTxt.sendKeys(Keys.TAB);
		
		sl_DateOptionDropdown.click();
		Select s=new Select(sl_DateOptionDropdown);
		s.selectByValue("1");
		
		click(sl_OkBtn);
		Thread.sleep(8000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_1stRow1stCol));
		
		String actBalanceAmt=balanceTxtinLedger.getText();
		String expBalanceAmt="35,956.99";
		
		System.out.println("Balance in Ledger 		"		+		"Actual		"		+	actBalanceAmt		+		"Expected		"		+		expBalanceAmt);
		
		if(actBalanceAmt.equalsIgnoreCase(expBalanceAmt))
		{
			return true;
		}
		else
		{
			return false;
		}
		
		
	}
	
	
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
	
	@FindBy(xpath="(//tr[@id='trRender_7'])[1]/td")
	private static List<WebElement> reportsRow8List;
	
	@FindBy(xpath="(//tr[@id='trRender_8'])[1]/td")
	private static List<WebElement> reportsRow9List;
	
	@FindBy(xpath="(//tr[@id='trRender_9'])[1]/td")
	private static List<WebElement> reportsRow10List;
	
	@FindBy(xpath="(//tr[@id='trRender_10'])[1]/td")
	private static List<WebElement> reportsRow11List;
	
	@FindBy(xpath="(//tr[@id='trRender_11'])[1]/td")
	private static List<WebElement> reportsRow12List;
	
	@FindBy(xpath="(//tr[@id='trRender_12'])[1]/td")
	private static List<WebElement> reportsRow13List;
	
	@FindBy(xpath="(//tr[@id='trRender_13'])[1]/td")
	private static List<WebElement> reportsRow14List;
	
	@FindBy(xpath="(//tr[@id='trRender_14'])[1]/td")
	private static List<WebElement> reportsRow15List;
	
	@FindBy(xpath="(//tr[@id='trRender_15'])[1]/td")
	private static List<WebElement> reportsRow16List;
	
	
	public boolean checkBankReconciliationStatementReport() throws InterruptedException
	{
		focusMainSearch("Bank reconciliation statement");
		Thread.sleep(4000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_DateOptionDropdown));
		sl_DateOptionDropdown.click();
		Select s=new Select(sl_DateOptionDropdown);
		s.selectByValue("1");
		
		click(report_AccTxt);
		report_AccTxt.sendKeys("Cheque Discounting");
		Thread.sleep(2500);
		report_AccTxt.sendKeys(Keys.TAB);
		
		click(sl_OkBtn);
		Thread.sleep(8000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_1stRow1stCol));
		
		
		String expRow1List = "[1, BalanceasperBooks, 22, 613.08, 35, 956.99, 2, 397.09]";
		boolean actRow1List=ListComparisionWOOrder(reportsRow1List,expRow1List);

		
		
		String expRow2List = "[2, "+getCurrentDate()+", NDT57:1, CustomerB, 1, 425.69, 24, 038.77, 1, 425.69, 1, 425.69, 37, 382.68, 1, 425.69, 99.80, 2, 496.89, 99.80]";
		boolean actRow2List=ListComparisionWOOrder(reportsRow2List,expRow2List);

		
		
		String expRow3List = "[3, "+getCurrentDate()+", NDT58:1, CustomerB, 5, 209.41, 18, 829.36, 3, 783.72, 5, 209.41, 32, 173.27, 3, 783.72, 364.66, 2, 132.23, 264.86]";
		boolean actRow3List=ListComparisionWOOrder(reportsRow3List,expRow3List);

		
		String expRow4List = "[4, "+getCurrentDate()+", Rct:12, CustomerA, 10.00, 18, 839.36, 3, 773.72, 10.00, 32, 183.27, 3, 773.72, 0.70, 2, 132.93, 264.16]";
		boolean actRow4List=ListComparisionWOOrder(reportsRow4List,expRow4List);

		
		String expRow5List = "[5, "+getCurrentDate()+", Rct:12, CustomerB, 20.00, 18, 859.36, 3, 753.72, 20.00, 32, 203.27, 3, 753.72, 1.40, 2, 134.33, 262.76]";
		boolean actRow5List=ListComparisionWOOrder(reportsRow5List,expRow5List);

		
		
		String expRow6List = "[6, "+getCurrentDate()+", Pmt:15, CustomerC, 50.00, 18, 809.36, 3, 803.72, 50.00, 32, 153.27, 3, 803.72, 3.50, 2, 130.83, 266.26]";
		boolean actRow6List=ListComparisionWOOrder(reportsRow6List,expRow6List);

		
		String expRow7List = "[7, "+getCurrentDate()+", Pmt:15, CustomerB, 70.00, 18, 739.36, 3, 873.72, 70.00, 32, 083.27, 3, 873.72, 4.90, 2, 125.93, 271.16]";
		boolean actRow7List=ListComparisionWOOrder(reportsRow7List,expRow7List);

		
		String expRow8List = "[8, BalanceasperBank, 18, 739.36, 32, 083.27, 2, 125.93]";
		boolean actRow8List=ListComparisionWOOrder(reportsRow8List,expRow8List);

		
		String expRow9List = "[9, GrandTotal, 1, 455.69, 5, 329.41, 1, 18, 115.57, 17, 562.91, 1, 455.69, 5, 329.41, 1, 98, 179.03, 17, 562.91, 101.90, 373.06, 13, 153.12, 1, 229.40]";
		boolean actRow9List=ListComparisionWOOrder(reportsRow9List,expRow9List);

		
		if(actRow1List && actRow2List && actRow3List && actRow4List && actRow5List && actRow6List
				&& actRow7List && actRow8List && actRow9List)
				
		{
			
			return true;
		}
		else
		{
			
			return false;
		}
		
	}
	
	@FindBy(xpath = "//input[@id='RITCheckbox__1']")
	public static WebElement showConsolidatedChkBox;
	
	@FindBy(xpath = "//input[@id='RITCheckbox__1']/../span")
	public static WebElement showConsolidatedChkBoxSelected;
	
	
	
	public boolean checkBankReconciliationStatementByEnablingShowconsolidatedamountsChkBox() throws InterruptedException
	{
		
		click(report_CloseBtn);
		Thread.sleep(2500);
		
		if(showConsolidatedChkBox.isSelected()==false)
		{
			showConsolidatedChkBoxSelected.click();
		}
		
		click(sl_OkBtn);
		Thread.sleep(8000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_1stRow1stCol));
		
		
		String expRow1List = "[1, BalanceasperBooks, 22, 613.08, 35, 956.99, 2, 397.09]";
		boolean actRow1List=ListComparisionWOOrder(reportsRow1List,expRow1List);

		
		
		String expRow2List = "[2, "+getCurrentDate()+", NDT57:1, 1, 425.69, 24, 038.77, 1, 425.69, 1, 425.69, 37, 382.68, 1, 425.69, 99.80, 2, 496.89, 99.80]";
		boolean actRow2List=ListComparisionWOOrder(reportsRow2List,expRow2List);

		
		
		String expRow3List = "[3, "+getCurrentDate()+", NDT58:1, 5, 209.41, 18, 829.36, 3, 783.72, 5, 209.41, 32, 173.27, 3, 783.72, 364.66, 2, 132.23, 264.86]";
		boolean actRow3List=ListComparisionWOOrder(reportsRow3List,expRow3List);

		
		String expRow4List = "[4, "+getCurrentDate()+", Rct:12, 30.00, 18, 859.36, 3, 753.72, 30.00, 32, 203.27, 3, 753.72, 2.10, 2, 134.33, 262.76]";
		boolean actRow4List=ListComparisionWOOrder(reportsRow4List,expRow4List);

		
		String expRow5List = "[5, "+getCurrentDate()+", Pmt:15, 120.00, 18, 739.36, 3, 873.72, 120.00, 32, 083.27, 3, 873.72, 8.40, 2, 125.93, 271.16]";
		boolean actRow5List=ListComparisionWOOrder(reportsRow5List,expRow5List);

		
		
		String expRow6List = "[6, BalanceasperBank, 18, 739.36, 32, 083.27, 2, 125.93]";
		boolean actRow6List=ListComparisionWOOrder(reportsRow6List,expRow6List);

		
		String expRow7List = "[7, GrandTotal, 1, 455.69, 5, 329.41, 80, 466.85, 9, 985.47, 1, 455.69, 5, 329.41, 1, 33, 842.49, 9, 985.47, 101.90, 373.06, 8, 889.37, 698.98]";
		boolean actRow7List=ListComparisionWOOrder(reportsRow7List,expRow7List);

		if(actRow1List && actRow2List && actRow3List && actRow4List && actRow5List && actRow6List
				&& actRow7List )
				
		{
			
			return true;
		}
		else
		{
			
			return false;
		}
		
	}

	@FindBy(xpath="//*[@id='RITCombobox__4']")
	public static WebElement brs_ConsideDateBasedSelect;
	
	
	public boolean checkConsiderDateonTransationDateinBankReconciliationStatementReport() throws InterruptedException
	{
		click(report_CloseBtn);
		Thread.sleep(4000);
		
		
		if(showConsolidatedChkBox.isSelected()==true)
		{
			showConsolidatedChkBoxSelected.click();
		}
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(brs_ConsideDateBasedSelect));
		brs_ConsideDateBasedSelect.click();
		Select s=new Select(brs_ConsideDateBasedSelect);
		s.selectByValue("1");
		
		click(sl_OkBtn);
		Thread.sleep(8000);
		
	getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_1stRow1stCol));
		
		
		String expRow1List = "[1, BalanceasperBooks, 22, 613.08, 35, 956.99, 2, 397.09]";
		boolean actRow1List=ListComparisionWOOrder(reportsRow1List,expRow1List);

		
		
		String expRow2List = "[2, "+getCurrentDate()+", NDT57:1, CustomerB, 1, 425.69, 24, 038.77, 1, 425.69, 1, 425.69, 37, 382.68, 1, 425.69, 99.80, 2, 496.89, 99.80]";
		boolean actRow2List=ListComparisionWOOrder(reportsRow2List,expRow2List);

		
		
		String expRow3List = "[3, "+getCurrentDate()+", NDT58:1, CustomerB, 5, 209.41, 18, 829.36, 3, 783.72, 5, 209.41, 32, 173.27, 3, 783.72, 364.66, 2, 132.23, 264.86]";
		boolean actRow3List=ListComparisionWOOrder(reportsRow3List,expRow3List);

		
		String expRow4List = "[4, "+getCurrentDate()+", Rct:12, CustomerA, 10.00, 18, 839.36, 3, 773.72, 10.00, 32, 183.27, 3, 773.72, 0.70, 2, 132.93, 264.16]";
		boolean actRow4List=ListComparisionWOOrder(reportsRow4List,expRow4List);

		
		String expRow5List = "[5, "+getCurrentDate()+", Rct:12, CustomerB, 20.00, 18, 859.36, 3, 753.72, 20.00, 32, 203.27, 3, 753.72, 1.40, 2, 134.33, 262.76]";
		boolean actRow5List=ListComparisionWOOrder(reportsRow5List,expRow5List);

		
		
		String expRow6List = "[6, "+getCurrentDate()+", Pmt:15, CustomerC, 50.00, 18, 809.36, 3, 803.72, 50.00, 32, 153.27, 3, 803.72, 3.50, 2, 130.83, 266.26]";
		boolean actRow6List=ListComparisionWOOrder(reportsRow6List,expRow6List);

		
		String expRow7List = "[7, "+getCurrentDate()+", Pmt:15, CustomerB, 70.00, 18, 739.36, 3, 873.72, 70.00, 32, 083.27, 3, 873.72, 4.90, 2, 125.93, 271.16]";
		boolean actRow7List=ListComparisionWOOrder(reportsRow7List,expRow7List);

		
		String expRow8List = "[8, BalanceasperBank, 18, 739.36, 32, 083.27, 2, 125.93]";
		boolean actRow8List=ListComparisionWOOrder(reportsRow8List,expRow8List);

		
		String expRow9List = "[9, GrandTotal, 1, 455.69, 5, 329.41, 1, 18, 115.57, 17, 562.91, 1, 455.69, 5, 329.41, 1, 98, 179.03, 17, 562.91, 101.90, 373.06, 13, 153.12, 1, 229.40]";
		boolean actRow9List=ListComparisionWOOrder(reportsRow9List,expRow9List);

		
		if(actRow1List && actRow2List && actRow3List && actRow4List && actRow5List && actRow6List
				&& actRow7List && actRow8List && actRow9List)
				
		{
			
			return true;
		}
		else
		{
			
			return false;
		}
		
		
	}

	
	public boolean checkConsiderDateonCleranceDateinBankReconsilationStatementReport() throws InterruptedException
	{		

		click(report_CloseBtn);
		Thread.sleep(4000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(brs_ConsideDateBasedSelect));
		brs_ConsideDateBasedSelect.click();
		Select s=new Select(brs_ConsideDateBasedSelect);
		s.selectByValue("2");
		
		click(sl_OkBtn);
		Thread.sleep(8000);
		
	getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sl_1stRow1stCol));
		
		
		String expRow1List = "[1, BalanceasperBooks, 22, 613.08, 35, 956.99, 2, 397.09]";
		boolean actRow1List=ListComparisionWOOrder(reportsRow1List,expRow1List);

		
		
		String expRow2List = "[2, "+getCurrentDate()+", Pmt:14, CustomerC, 29, 583.25, 6, 970.17, 29, 583.25, 29, 583.25, 6, 373.74, 29, 583.25, 2, 070.83, 326.26, 2, 070.83]";
		boolean actRow2List=ListComparisionWOOrder(reportsRow2List,expRow2List);

		
		
		String expRow3List = "[3, "+getCurrentDate()+", Rct:11, CustomerC, 11, 037.28, 4, 067.11, 18, 545.97, 11, 037.28, 17, 411.02, 18, 545.97, 772.61, 1, 098.87, 1, 298.22]";
		boolean actRow3List=ListComparisionWOOrder(reportsRow3List,expRow3List);

		
		String expRow4List = "[4, "+getCurrentDate()+", NDT57:1, CustomerB, 1, 425.69, 5, 492.80, 17, 120.28, 1, 425.69, 18, 836.71, 17, 120.28, 99.80, 1, 198.67, 1, 198.42]";
		boolean actRow4List=ListComparisionWOOrder(reportsRow4List,expRow4List);

		
		String expRow5List = "[5, "+getCurrentDate()+", NDT58:1, CustomerB, 5, 209.41, 283.39, 22, 329.69, 5, 209.41, 13, 627.30, 22, 329.69, 364.66, 834.01, 1, 563.08]";
		boolean actRow5List=ListComparisionWOOrder(reportsRow5List,expRow5List);

		
		
		String expRow6List = "[6, "+getCurrentDate()+", Rct:12, CustomerA, 10.00, 293.39, 22, 319.69, 10.00, 13, 637.30, 22, 319.69, 0.70, 834.71, 1, 562.38]";
		boolean actRow6List=ListComparisionWOOrder(reportsRow6List,expRow6List);

		
		String expRow7List = "[7, "+getCurrentDate()+", Rct:12, CustomerB, 20.00, 313.39, 22, 299.69, 20.00, 13, 657.30, 22, 299.69, 1.40, 836.11, 1, 560.98]";
		boolean actRow7List=ListComparisionWOOrder(reportsRow7List,expRow7List);

		
		String expRow8List = "[8, "+getCurrentDate()+", Pmt:15, CustomerC, 50.00, 263.39, 22, 349.69, 50.00, 13, 607.30, 22, 349.69, 3.50, 832.61, 1, 564.48]";
		boolean actRow8List=ListComparisionWOOrder(reportsRow8List,expRow8List);

		
		String expRow9List = "[9, "+getCurrentDate()+", Pmt:15, CustomerB, 70.00, 193.39, 22, 419.69, 70.00, 13, 537.30, 22, 419.69, 4.90, 827.71, 1, 569.38]";
		boolean actRow9List=ListComparisionWOOrder(reportsRow9List,expRow9List);

		
		String expRow10List = "[10, BalanceasperBank, 193.39, 13, 537.30, 827.71]";
		boolean actRow10List=ListComparisionWOOrder(reportsRow10List,expRow10List);

		
		String expRow11List = "[11, GrandTotal, 12, 492.97, 34, 912.66, 3, 936.69, 1, 76, 967.95, 12, 492.97, 34, 912.66, 1, 10, 687.97, 1, 76, 967.95, 874.51, 2, 443.89, 6, 788.94, 12, 387.76]";
		boolean actRow11List=ListComparisionWOOrder(reportsRow11List,expRow11List);

		
		
		if(actRow1List && actRow2List && actRow3List && actRow4List && actRow5List && actRow6List
				&& actRow7List && actRow8List && actRow9List && actRow10List && actRow11List)
				
		{
			
			return true;
		}
		else
		{
			
			return false;
		}
		
		
	
	}
	
	
	@FindBy(xpath="//*[contains(text(),'Yes please')]")
	public static WebElement BRSUser_Yes;
	
	
	@FindBy(xpath="//a[@title='Customize']")
	public static WebElement BRS_CustomizationBtn;
	
	@FindBy(xpath="//*[@id='BRSCustomization_body']//td[2]")
	public static List<WebElement> BRS_CustomizationChkBoxList;
	
	@FindBy(xpath="//*[@id='BRSCustomization_body']//td[4]")
	public static List<WebElement> BRS_CustomizationFieldNameList;
	
	@FindBy(xpath="(//input[@value='Ok'])[3]")
	public static WebElement BRS_CustomizationOKBtn;
	
	public boolean checkSavingReceiptsVATVocuherForUser() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		focusMainSearch("Receipts VAT");
		Thread.sleep(8000);
		
		click(newBtn);
		Thread.sleep(2000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNumberTxt));
		
		click(caskBankAccountTxt);
		caskBankAccountTxt.sendKeys("BankB");
		Thread.sleep(2000);
		caskBankAccountTxt.sendKeys(Keys.TAB);
		
		click(departmentTxt);
		departmentTxt.sendKeys("AMERICA");
		Thread.sleep(2000);
		departmentTxt.sendKeys(Keys.TAB);

		
		Thread.sleep(2999);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select1stRow_1stColumn));
		select1stRow_1stColumn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_AccountTxt));
		enter_AccountTxt.click();
		enter_AccountTxt.sendKeys("Customer C");
		Thread.sleep(2999);
		
		enter_AccountTxt.sendKeys(Keys.TAB);
		Thread.sleep(2999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(recVATTaxCode));
		recVATTaxCode.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME);
		recVATTaxCode.sendKeys("STD Rate");
		Thread.sleep(1999);
		recVATTaxCode.sendKeys(Keys.TAB);
		

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_Amount));
		enter_Amount.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME);
		enter_Amount.sendKeys("147.05");
		Thread.sleep(1999);
		enter_Amount.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		click(billRefPickIcon);

		Thread.sleep(1000);
		click(billRefOkBtn);

		Thread.sleep(1000);
		
		//2nd Row
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(select2ndRow_1stColumn));
		select2ndRow_1stColumn.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_AccountTxt));
		enter_AccountTxt.click();
		enter_AccountTxt.sendKeys("CustomerD");
		Thread.sleep(2999);
		
		enter_AccountTxt.sendKeys(Keys.TAB);
		Thread.sleep(2999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(recVATTaxCode));
		recVATTaxCode.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME);
		recVATTaxCode.sendKeys("STD Rate");
		Thread.sleep(1999);
		recVATTaxCode.sendKeys(Keys.TAB);
		

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(enter_Amount));
		enter_Amount.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME);
		enter_Amount.sendKeys("453.25");
		Thread.sleep(1999);
		enter_Amount.sendKeys(Keys.TAB);

		Thread.sleep(2000);

		click(billRefPickIcon);

		Thread.sleep(1000);
		click(billRefOkBtn);

		Thread.sleep(1000);
		
		click(saveBtn);
		Thread.sleep(3500);
		
		String expMsg="Voucher saved successfully";
		String actMsg=checkValidationMessage(expMsg);
		String expMsg1=": 2";
		
		if(actMsg.startsWith(expMsg) && actMsg.endsWith(expMsg1))
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	//*[@id='BRTable']//thead//th/div
	@FindBy(xpath="//*[@id='BRTable']//th/div[@class='no-select']")
	public static List<WebElement> BRS_HeaderList;
	
	@FindBy(xpath = "//div[text()='sBRSUser']")
	public static WebElement move;
	
	
	
	public boolean checkAddingBRSUserColumninBRSReport() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException
	{
		
		focusMainSearch("Bank Reconciliation");
		Thread.sleep(8000);
		
		click(reportbankTxt);
		reportbankTxt.sendKeys("BankB");
		Thread.sleep(1500);
		reportbankTxt.sendKeys(Keys.TAB);
		
		Select s=new Select(BRSSelectStatusDrpdwn);
		s.selectByValue("2");
		Thread.sleep(1500);
		BRSSelectStatusDrpdwn.sendKeys(Keys.TAB);
		
		
		Select s1=new Select(BRSSelectCRDRDrpdwn);
		s1.selectByValue("2");
		Thread.sleep(1500);
		BRSSelectCRDRDrpdwn.sendKeys(Keys.TAB);
		
		click(LoadBtn);
		Thread.sleep(8000);
	
		click(BRSUser_Yes);
		Thread.sleep(6000);
		
		String expMsg="BRSUser field has been created successfully. Add the field from customization screen.";
		String actMsg=checkValidationMessage(expMsg);
		
		System.out.println("Actaul Message After User Added to Customization	"	+	actMsg);
		System.out.println("Expect Message After User Added to Customization	"	+	expMsg);
		
		click(BRS_CustomizationBtn);
		Thread.sleep(2500);
		
		for(int i=0;i<BRS_CustomizationFieldNameList.size();i++)
		{
			if(BRS_CustomizationFieldNameList.get(i).getText().equalsIgnoreCase("BRS User"))
			{
				BRS_CustomizationChkBoxList.get(i).click();
				break;
			}
		}
		Thread.sleep(1500);
		click(BRS_CustomizationOKBtn);
		Thread.sleep(10000);
		
		ArrayList<String>headerList=new ArrayList<String>();
		for(int i=0;i<BRS_HeaderList.size();i++)
		{
			ScrollToElement(BRS_HeaderList.get(i));
			headerList.add(BRS_HeaderList.get(i).getText());
			Thread.sleep(1200);
		}
		
		
		String actHeaderList=headerList.toString();
		String expHeaderList="[BRS Status, ChequeNo, Clearance Date, Document No, Document Date, Debit Amount, Credit Amount, Document Type, , sBRSUser, , , ]";
		
		System.out.println("Actual Header List	"	+	actHeaderList);
		System.out.println("Expect Header List	"	+	expHeaderList);
		
			
		if(actMsg.equalsIgnoreCase(expMsg) && actHeaderList.equalsIgnoreCase(expHeaderList))
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	
	public boolean checkBRSReportAfterAddingBRSUser() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException
	{
		Thread.sleep(4000);
		//getAction().moveToElement(brs_1stRow2ndCol).build().perform();
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
	     js.executeScript("window.scrollBy(0,-350)", "");
		Thread.sleep(2000);
		click(brs_1stRow2ndCol);
		Thread.sleep(2000);	
		
		checkValidationMessage("");
		
		
		logout();
		Thread.sleep(2500);
		
		checkLogin();
		Thread.sleep(8000);
		focusMainSearch("Bank Reconciliation");
		Thread.sleep(8000);
		
		click(reportbankTxt);
		reportbankTxt.sendKeys("BankB");
		Thread.sleep(1500);
		reportbankTxt.sendKeys(Keys.TAB);
		
		Select s2=new Select(BRSSelectStatusDrpdwn);
		s2.selectByValue("2");
		Thread.sleep(1500);
		BRSSelectStatusDrpdwn.sendKeys(Keys.TAB);
		
		
		Select s3=new Select(BRSSelectCRDRDrpdwn);
		s3.selectByValue("2");
		Thread.sleep(1500);
		BRSSelectCRDRDrpdwn.sendKeys(Keys.TAB);
		
		click(LoadBtn);
		Thread.sleep(8000);
		
		
		ArrayList<String>brsRow1ArrayList=new ArrayList<String>();
		for(int i=0;i<bankRecRow1List.size();i++)
		{
			brsRow1ArrayList.add(bankRecRow1List.get(i).getText());
		}
		
		String actBRSRow1List=brsRow1ArrayList.toString();
		String expBRSRow1List="[1, Cleared, , "+getCurrentDate()+", NDT57:2, "+getCurrentDate()+", 147.05, 0.00, Receipts VAT, , SU, , , ]";
		
		System.out.println("Actual Row1 List	"	+	actBRSRow1List);
		System.out.println("Expect Row1 List	"	+	expBRSRow1List);
		
		
		ArrayList<String>brsRow2ArrayList=new ArrayList<String>();
		for(int i=0;i<bankRecRow2List.size();i++)
		{
			brsRow2ArrayList.add(bankRecRow2List.get(i).getText());
		}
		
		String actBRSRow2List=brsRow2ArrayList.toString();
		String expBRSRow2List="[2, Pending, , "+getCurrentDate()+", NDT57:2, "+getCurrentDate()+", 453.25, 0.00, Receipts VAT, , 0, , , ]";
		
		System.out.println("Actual Row2 List	"	+	actBRSRow2List);
		System.out.println("Expect Row2 List	"	+	expBRSRow2List);
		
		
		if(actBRSRow1List.equalsIgnoreCase(expBRSRow1List) && actBRSRow2List.equalsIgnoreCase(expBRSRow2List))
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	
	public boolean checkBRSReportforBRSUseratUserLevel() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException
	{
		Thread.sleep(2500);
		
		logout();
		Thread.sleep(3500);
		
		click(username);
		username.sendKeys("useralloptions");
		username.sendKeys(Keys.TAB);
		
		click(password);
		password.sendKeys("12345");
		password.sendKeys(Keys.TAB);
		
		String compname = "BRS";

		Select oSelect = new Select(companyDropDownList);

		List<WebElement> elementCount = oSelect.getOptions();

		int cqSize = elementCount.size();

		for (int i = 0; i < elementCount.size(); i++) {

			elementCount.get(i).getText();

			String optionName = elementCount.get(i).getText();
			if (optionName.toUpperCase().startsWith(compname.toUpperCase())) {
				//System.out.println("Company List:" + elementCount.get(i).getText());
				elementCount.get(i).click();
			}

		}

		Thread.sleep(2000);

		
		click(signIn);
		Thread.sleep(8000);
		
		focusMainSearch("Bank Reconciliation");
		Thread.sleep(8000);
		
		click(reportbankTxt);
		reportbankTxt.sendKeys("BankB");
		Thread.sleep(1500);
		reportbankTxt.sendKeys(Keys.TAB);
		
		Select s2=new Select(BRSSelectStatusDrpdwn);
		s2.selectByValue("2");
		Thread.sleep(1500);
		BRSSelectStatusDrpdwn.sendKeys(Keys.TAB);
		
		
		Select s3=new Select(BRSSelectCRDRDrpdwn);
		s3.selectByValue("2");
		Thread.sleep(1500);
		BRSSelectCRDRDrpdwn.sendKeys(Keys.TAB);
		
		click(LoadBtn);
		Thread.sleep(8000);
		
		click(brs_2ndRow2ndCol);
		checkValidationMessage("");
		
		logout();
		Thread.sleep(3500);
		
		click(username);
		username.sendKeys("useralloptions");
		username.sendKeys(Keys.TAB);
		
		click(password);
		password.sendKeys("12345");
		password.sendKeys(Keys.TAB);
		
		Select oSelect1 = new Select(companyDropDownList);

		List<WebElement> elementCount1 = oSelect1.getOptions();

		for (int i = 0; i < elementCount1.size(); i++) {

			elementCount1.get(i).getText();

			String optionName = elementCount1.get(i).getText();
			if (optionName.toUpperCase().startsWith(compname.toUpperCase())) {
				//System.out.println("Company List:" + elementCount.get(i).getText());
				elementCount1.get(i).click();
			}

		}

		Thread.sleep(2000);
		
		click(signIn);
		Thread.sleep(8000);
		
		focusMainSearch("Bank Reconciliation");
		Thread.sleep(8000);
		
		click(reportbankTxt);
		reportbankTxt.sendKeys("BankB");
		Thread.sleep(1500);
		reportbankTxt.sendKeys(Keys.TAB);
		
		Select s6=new Select(BRSSelectStatusDrpdwn);
		s6.selectByValue("2");
		Thread.sleep(1500);
		BRSSelectStatusDrpdwn.sendKeys(Keys.TAB);
		
		
		Select s7=new Select(BRSSelectCRDRDrpdwn);
		s7.selectByValue("2");
		Thread.sleep(1500);
		BRSSelectCRDRDrpdwn.sendKeys(Keys.TAB);
		
		click(LoadBtn);
		Thread.sleep(8000);
		
		
		
		ArrayList<String>brsRow1ArrayList=new ArrayList<String>();
		for(int i=0;i<bankRecRow1List.size();i++)
		{
			brsRow1ArrayList.add(bankRecRow1List.get(i).getText());
		}
		
		String actBRSRow1List=brsRow1ArrayList.toString();
		String expBRSRow1List="[1, Cleared, , "+getCurrentDate()+", NDT57:2, "+getCurrentDate()+", 147.05, 0.00, Receipts VAT, , SU, , , ]";
		
		System.out.println("Actual Row1 List	"	+	actBRSRow1List);
		System.out.println("Expect Row1 List	"	+	expBRSRow1List);
		
		
		ArrayList<String>brsRow2ArrayList=new ArrayList<String>();
		for(int i=0;i<bankRecRow2List.size();i++)
		{
			brsRow2ArrayList.add(bankRecRow2List.get(i).getText());
		}
		
		String actBRSRow2List=brsRow2ArrayList.toString();
		String expBRSRow2List="[2, Cleared, , "+getCurrentDate()+", NDT57:2, "+getCurrentDate()+", 453.25, 0.00, Receipts VAT, , UserAllOptions, , , ]";
		
		System.out.println("Actual Row2 List	"	+	actBRSRow2List);
		System.out.println("Expect Row2 List	"	+	expBRSRow2List);
		
		logout();
		Thread.sleep(2500);
		
		checkLogin();
		Thread.sleep(4000);
		if(actBRSRow1List.equalsIgnoreCase(expBRSRow1List) && actBRSRow2List.equalsIgnoreCase(expBRSRow2List))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	@FindBy(xpath="//a[@title='Setting']//i")
	public static WebElement receiptsVAT_SettingsBtn;
	
	@FindBy(xpath="//*[@id='navigationtab3']")
	public static WebElement receiptsVAT_EditLayout;
	
	@FindBy(xpath="//*[@id='BodyTabMenu']")
	public static WebElement receiptsVAT_EditLayoutBodyTab;
	
	@FindBy(xpath="//*[@id='editLayout_bodyTable']//tr//td[3]")
	public static List<WebElement> receiptsVAT_EditLayoutBodyFieldNameList;
	
	@FindBy(xpath="//*[@id='updateButton']")
	public static WebElement receiptsVAT_UpdateBtn;
	
	@FindBy(xpath="//*[@id='btnCustomizeClose']")
	public static WebElement receiptsVAT_CloseBtn;
	
	public boolean checkBRSUserColumnisAddedinReceiptsVATVoucher() throws InterruptedException
	{
		
		focusMainSearch("Receipts VAT");
		Thread.sleep(5000);
		
		click(receiptsVAT_SettingsBtn);
		Thread.sleep(4500);
		
		click(receiptsVAT_EditLayout);
		Thread.sleep(1500);
		
		click(receiptsVAT_EditLayoutBodyTab);
		Thread.sleep(1500);
		
		boolean actBrsUser=false;
		
		for(int i=0;i<receiptsVAT_EditLayoutBodyFieldNameList.size();i++)
		{
			
			if(receiptsVAT_EditLayoutBodyFieldNameList.get(i).getText().equalsIgnoreCase("sBRSUser"))
			{
				actBrsUser=true;
			}
		}
		
		click(receiptsVAT_UpdateBtn);
		Thread.sleep(2000);
		
		click(receiptsVAT_CloseBtn);
		Thread.sleep(2500);
		
		if(actBrsUser)
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	@FindBy(xpath="//*[@id='txtUsername']")
	private static WebElement username;

	@FindBy(id="txtPassword")
	private static WebElement password;
	
    @FindBy(id="ddlCompany")
    private static WebElement companyDropDownList;

	@FindBy(id="btnSignin")
	private static WebElement signIn;
	
	
	
	
	////BRS Import
	
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
	
	
	public boolean checkSavingReceiptsVoucherforBRSImport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{

		Thread.sleep(2000);
		click(serachMenuTextHomePage);
		
		serachMenuTextHomePage.sendKeys("Receipts");
		Thread.sleep(2000);
		serachMenuTextHomePage.sendKeys(Keys.DOWN);
		serachMenuTextHomePage.sendKeys(Keys.ENTER);
		//click(searchMenuTextClick);
		 
		Thread.sleep(8000);
		new WebDriverWait(getDriver(), 300).until(ExpectedConditions.visibilityOf(newBtn));
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(newBtn));
		newBtn.click();
		Thread.sleep(4000);
		
		Thread.sleep(2000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNumberTxt));
		String docno=documentNumberTxt.getAttribute("value");
		
		click(caskBankAccountTxt);
		caskBankAccountTxt.sendKeys("BankA");
		Thread.sleep(2000);
		caskBankAccountTxt.sendKeys(Keys.TAB);
		
		
		
		click(departmentTxt);
		departmentTxt.sendKeys("AMERICA");
		Thread.sleep(2000);
		departmentTxt.sendKeys(Keys.TAB);
		
		
		
		click(receipts_ChequeNoTxt);
		receipts_ChequeNoTxt.sendKeys("160321");
		Thread.sleep(2000);
		receipts_ChequeNoTxt.sendKeys(Keys.TAB);
		
		
		
		click(select1stRow_1stColumn);
		
		click(enter_DebitACTxt);
		enter_DebitACTxt.sendKeys("Customer C");
		Thread.sleep(2000);
		enter_DebitACTxt.sendKeys(Keys.TAB);
		
		
		enter_Amount.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
		enter_Amount.sendKeys("255.69");
		Thread.sleep(2000);
		enter_Amount.sendKeys(Keys.TAB);
		
		
		
		click(billRefNewReferenceTxt);
		click(pickBtn);
		Thread.sleep(2000);
		
		click(voucher_OkBtn);
		Thread.sleep(4000);
		
		click(saveBtn);
		Thread.sleep(2000);
		
		String expMsg="Voucher saved successfully";
		String expMsg1=": 13";
		
		String actMsg=checkValidationMessage(expMsg);
		
		System.out.println("Actual Message		"	+	actMsg		+	"Expected Message	"	+	expMsg);
		
		if(actMsg.startsWith(expMsg) && actMsg.endsWith(expMsg1))
		{
			return true;
		}
		else
		{
			return false;
		}
	
	}
	
	
	public boolean checkBankReconciliationImportReportforSameChequeNumDiffAmount()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {

		Thread.sleep(2999);

		getDriver().navigate().refresh();

		Thread.sleep(2000);
		
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
		String expvalidationConfirmationMessage = "";

		System.out.println("validationConfirmationMessage : " + actvalidationConfirmationMessage + " Value Expected : "
				+ expvalidationConfirmationMessage);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(brsImport_ImportBtn));
		brsImport_ImportBtn.click();

		Thread.sleep(9500);

		Robot rb = new Robot();

		StringSelection str = new StringSelection(getBaseDir() + "\\autoIt\\ImportFiles\\BRS Import1.xlsx");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);

		rb.keyPress(KeyEvent.VK_CONTROL);
		rb.keyPress(KeyEvent.VK_V);

		rb.keyRelease(KeyEvent.VK_CONTROL);
		rb.keyRelease(KeyEvent.VK_V);

		rb.keyPress(KeyEvent.VK_ENTER);
		rb.keyRelease(KeyEvent.VK_ENTER);

		Thread.sleep(5000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(Sheet1));
		Sheet1.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sheetOkBtn));
		sheetOkBtn.click();
		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(brsImportBankDrpdwn));
		brsImportBankDrpdwn.click();
		brsImportBankDrpdwn.sendKeys("BankA");
		Thread.sleep(2000);

		brsImportBankDrpdwn.sendKeys(Keys.TAB);

		Thread.sleep(3000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(fieldMapRow1));
		fieldMapRow1.click();
		Thread.sleep(1999);
		FieldDpdwn.click();
		Select s1 = new Select(FieldDpdwn);
		s1.selectByVisibleText("chequeno");

		Thread.sleep(1999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(fieldMapRow2));
		fieldMapRow2.click();
		FieldDpdwn1.click();
		Select s2 = new Select(FieldDpdwn1);
		s2.selectByVisibleText("clearancedate");

		Thread.sleep(1999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(fieldMapRow3));
		fieldMapRow3.click();
		FieldDpdwn2.click();
		Select s3 = new Select(FieldDpdwn2);
		s3.selectByVisibleText("amount");

		Thread.sleep(1999);
		
		click(brsLoadBtn);
		Thread.sleep(15000);
		
		ArrayList<String>focusdata1stRowArray=new ArrayList<String>();
		
		for(int i=0;i<brsImportFocusData1stRowList.size();i++)
		{
			
			focusdata1stRowArray.add(brsImportFocusData1stRowList.get(i).getText());
		}
		
		String actFocusData1stRow=focusdata1stRowArray.toString();
		String expFocusData1stRow="[1, , Pending, 160321, 24/01/2025, Rct:13, "+getCurrentDate()+", 255.69, 0.00, Receipts]";
		
		System.out.println("Actual Focus Data 1st Row		"	+		actFocusData1stRow);
		System.out.println("Expect Focus Data 1st Row		"	+		expFocusData1stRow);
		
		
		ArrayList<String>Bankdata1stRowArray=new ArrayList<String>();
		
		for(int i=0;i<brsImportBankData1stRowList.size();i++)
		{
			
			Bankdata1stRowArray.add(brsImportBankData1stRowList.get(i).getText());
		}
		
		String actBankData1stRow=Bankdata1stRowArray.toString();
		String expBankData1stRow="[1, , 24/01/2025, 160321, 1500.00, , ]";
		
		System.out.println("Actual Bank Data 1st Row		"	+		actBankData1stRow);
		System.out.println("Expect Bank Data 1st Row		"	+		expBankData1stRow);
		
		click(brsImportMatchedEntries);
		Thread.sleep(1000);
		
		boolean actColChkBoxEnabled=brsImportColumn1ChkBox.isSelected();
		System.out.println("Column Chk Box is Enables After Matched Entries		"	+		actColChkBoxEnabled);
		
		click(brsImportPost);
		Thread.sleep(1500);
		
		String expMsg="Selected Vouchers Posted Succesfully";
		String actMsg=checkValidationMessage(expvalidationConfirmationMessage);
		
		if(actMsg.equalsIgnoreCase(expMsg) && actColChkBoxEnabled && actFocusData1stRow.equalsIgnoreCase(expFocusData1stRow)
				&& actBankData1stRow.equalsIgnoreCase(expBankData1stRow))
		{
		
		return true;
		}
		else
		{
			return false;
		}
	}
	
	@FindBy(xpath = "//*[@id='btnLoad']")
	private static WebElement brsLoadBtn;
	
	@FindBy(xpath="//*[@id='chkShowConsolidatedData']")
	public static WebElement brsImport_showConsolidatedChkBox;
	
	@FindBy(xpath="//*[@id='chkShowConsolidatedData']/../span")
	public static WebElement brsImport_showConsolidatedChkBoxSelected;
	
	@FindBy(xpath = "//*[@id='BRSFocusDataTable_body']//tr[1]//td")
	private static List<WebElement> brsImportFocusData1stRowList;
	
	@FindBy(xpath = "//*[@id='BRSFocusDataTable_body']//tr[2]//td")
	private static List<WebElement> brsImportFocusData2ndRowList;
	
	@FindBy(xpath = "//*[@id='BRSFocusDataTable_body']//tr[3]//td")
	private static List<WebElement> brsImportFocusData3rdRowList;
	
	@FindBy(xpath = "//*[@id='BRSFocusDataTable_body']//tr[4]//td")
	private static List<WebElement> brsImportFocusData4thRowList;
	
	@FindBy(xpath = "//*[@id='BRSFocusDataTable_body']//tr[5]//td")
	private static List<WebElement> brsImportFocusData5thRowList;
	
	@FindBy(xpath = "//*[@id='BRSFocusDataTable_body']//tr[6]//td")
	private static List<WebElement> brsImportFocusData6htRowList;
	
	@FindBy(xpath = "//*[@id='BRSBankDataTable_body']//tr[1]//td")
	private static List<WebElement> brsImportBankData1stRowList;
	
	@FindBy(xpath = "//*[@id='BRSBankDataTable_body']//tr[2]//td")
	private static List<WebElement> brsImportBankData2ndRowList;
	
	@FindBy(xpath = "//*[@id='BRSBankDataTable_body']//tr[3]//td")
	private static List<WebElement> brsImportBankData3rdRowList;
	
	@FindBy(xpath = "//*[@id='BRSBankDataTable_body']//tr[4]//td")
	private static List<WebElement> brsImportBankData4thRowList;
	
	@FindBy(xpath = "//*[@id='BRSBankDataTable_body']//tr[5]//td")
	private static List<WebElement> brsImportBankData5thRowList;
	
	@FindBy(xpath = "//*[@id='BRSBankDataTable_body']//tr[6]//td")
	private static List<WebElement> brsImportBankData6thRowList;
	
	
	@FindBy(xpath = "//*[@id='btnSelectedMatchedEntries']")
	public static WebElement brsImportMatchedEntries;
	
	@FindBy(xpath = "//*[@id='btnMisMatchedEntries']")
	public static WebElement brsImportMisMatchedEntries;
	
	@FindBy(xpath = "//*[@id='btnPost']")
	public static WebElement brsImportPost;
	
	@FindBy(xpath = "//*[@id='chkColumn']")
	public static WebElement brsImportColumnChkBox;
	
	@FindBy(xpath = "//*[@id='chkColumn_1']")
	public static WebElement brsImportColumn1ChkBox;
	
	@FindBy(xpath = "//*[@id='BRSMisMatchTable_body']")
	public static WebElement brsImportMismatchedTable;
	
	@FindBy(xpath = "(//input[@value='Cancel'])[2]")
	public static WebElement brsImportMismatchedCancelBtn;
	
	public boolean checkBankReconcillationReportAfterVoucherPostedinBRSImport() throws InterruptedException
	{

		click(financialsMenu);
		click(financialsReportsMenu);
		click(cashAndBankBooksMenu);
		
		getAction().moveToElement(bankReconciliationReport).build().perform();
		click(bankReconciliationReport);
		
		Thread.sleep(9000);
		
		reportbankTxt.sendKeys("BANKA");
		Thread.sleep(1500);
		reportbankTxt.sendKeys(Keys.TAB);
		
		Select s1=new Select(BRSSelectStatusDrpdwn);
		s1.selectByValue("2");
		Thread.sleep(3000);
		BRSSelectStatusDrpdwn.sendKeys(Keys.TAB);
		
		//click(BRSSelectCRDRDrpdwn);
		Select s2=new Select(BRSSelectCRDRDrpdwn);
		s2.selectByValue("2");
		Thread.sleep(3000);
		BRSSelectCRDRDrpdwn.sendKeys(Keys.TAB);
		
		click(LoadBtn);
		Thread.sleep(8000);
		
		ArrayList<String>brsRow1ArrayList=new ArrayList<String>();
		
				for(int i=0;i<bankRecRow1List.size();i++)
				{
					brsRow1ArrayList.add(bankRecRow1List.get(i).getText());
				}
		String actBRSRow1List=	brsRow1ArrayList.toString();
		String expBRSRow1List="[1, Cleared, 160321, 24/01/2025, Rct:13, "+getCurrentDate()+", 255.69, 0.00, Receipts, , 0, , , ]";
		
		System.out.println("Actual Row1 List		"		+	actBRSRow1List);
		System.out.println("Expect Row1 List		"		+	expBRSRow1List);
		
		
		   String actBookBal=bankRecBookBal.getText();
		   String expBookBal="255.69 Dr";

			String actbankRecOutDebits=bankRecOutDebits.getText();
		    String expbankRecOutDebits="0.00 Dr";
		
			String actbankRecOutCredits=bankRecOutCredits.getText();
		    String expbankRecOutCredits="0.00 Cr";
		
			String actbankRecClearedBal=bankRecClearedBal.getText();
		    String expbankRecClearedBal="255.69 Dr";
		
			String actbankRecOpenBal=bankRecOpenBal.getText();
		    String expbankRecOpenBal="0.00";
			

			String actbankRecDebitCounts=bankRecDebitCounts.getText();
		    String expbankRecDebitCounts="1";
			
			String actbankRecCreditCounts=bankRecCreditCounts.getText();
		    String expbankRecCreditCounts="0";
		    
			String actbankRecBankBal=bankRecBankBal.getAttribute("value");
		    String expbankRecBankBal="0.00";
		    
		    String actbankRecDiff=bankRecDif.getText();
		    String expbankRecDiff="-255.69";


			
			
		   System.out.println("**********************************checkBankReconciliationReport*****************************************");
		   System.out.println("BookBal             : "+actBookBal             +" Value Expected  : "+expBookBal);
	       System.out.println("bankRecOutDebits    : "+actbankRecOutDebits    +" Value Expected  : "+expbankRecOutDebits);
	       System.out.println("bankRecOutCredits   : "+actbankRecOutCredits   +" Value Expected  : "+expbankRecOutCredits);
	       System.out.println("bankRecClearedBal   : "+actbankRecClearedBal   +" Value Expected  : "+expbankRecClearedBal);
	       System.out.println("Opening Bal         : "+actbankRecOpenBal      +" Value Expected  : "+expbankRecOpenBal);
	       System.out.println("bankRecDebitCounts  : "+actbankRecDebitCounts  +" Value Expected  : "+expbankRecDebitCounts);
	       System.out.println("bankRecCreditCounts : "+actbankRecCreditCounts +" Value Expected  : "+expbankRecCreditCounts);
	       System.out.println("bankRecBankBal      : "+actbankRecBankBal      +" Value Expected  : "+expbankRecBankBal);
	       System.out.println("bankRecDifferr      : "+actbankRecDiff	      +" Value Expected  : "+expbankRecDiff);

		if(actBRSRow1List.equalsIgnoreCase(expBRSRow1List) &&
				 actBookBal.equalsIgnoreCase(expBookBal) &&
				actbankRecOutDebits.equalsIgnoreCase(expbankRecOutDebits) && actbankRecOutCredits.equalsIgnoreCase(expbankRecOutCredits) &&
				actbankRecClearedBal.equalsIgnoreCase(expbankRecClearedBal) && actbankRecDebitCounts.equalsIgnoreCase(expbankRecDebitCounts) &&
				actbankRecOpenBal.equalsIgnoreCase(expbankRecBankBal)&&
				actbankRecCreditCounts.equalsIgnoreCase(expbankRecCreditCounts) && actbankRecBankBal.equalsIgnoreCase(expbankRecBankBal)
				&& actbankRecDiff.equalsIgnoreCase(expbankRecDiff))
		{
			return true;
		}
		else
		{
			return false;
		}
		
		
	}
	protected String filePath;
	private FileInputStream fip;
	private FileOutputStream fop;
	protected Workbook workbook;
	protected Sheet sheet;
	private Cell cell;
	private CellStyle style;
	protected Row row;
	
	public boolean checkSavingReceiptsVoucherwithSameChequeNumberSameAmount() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
			Thread.sleep(2000);
			click(serachMenuTextHomePage);
			
			serachMenuTextHomePage.sendKeys("Receipts");
			Thread.sleep(2000);
			serachMenuTextHomePage.sendKeys(Keys.DOWN);
			serachMenuTextHomePage.sendKeys(Keys.ENTER);
			//click(searchMenuTextClick);
			 
			Thread.sleep(8000);
			new WebDriverWait(getDriver(), 300).until(ExpectedConditions.visibilityOf(newBtn));
			
			getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(newBtn));
			newBtn.click();
			Thread.sleep(4000);
			
			Thread.sleep(2000);
			
			getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNumberTxt));
			String docno=documentNumberTxt.getAttribute("value");
			
			click(caskBankAccountTxt);
			caskBankAccountTxt.sendKeys("BankA");
			Thread.sleep(2000);
			caskBankAccountTxt.sendKeys(Keys.TAB);
			
			
			
			click(departmentTxt);
			departmentTxt.sendKeys("INDIA");
			Thread.sleep(2000);
			departmentTxt.sendKeys(Keys.TAB);
			
			
			
			click(receipts_ChequeNoTxt);
			receipts_ChequeNoTxt.sendKeys("658921");
			Thread.sleep(2000);
			receipts_ChequeNoTxt.sendKeys(Keys.TAB);
			
			
			
			click(select1stRow_1stColumn);
			
			click(enter_DebitACTxt);
			enter_DebitACTxt.sendKeys("Customer C");
			Thread.sleep(2000);
			enter_DebitACTxt.sendKeys(Keys.TAB);
			
			
			enter_Amount.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
			enter_Amount.sendKeys("528.64");
			Thread.sleep(2000);
			enter_Amount.sendKeys(Keys.TAB);
			
			
			
			click(billRefNewReferenceTxt);
			click(pickBtn);
			Thread.sleep(2000);
			
			click(voucher_OkBtn);
			Thread.sleep(4000);
			
			click(saveBtn);
			Thread.sleep(2000);
			
			String expMsg="Voucher saved successfully";
			String expMsg1=": 14";
			
			String actMsg=checkValidationMessage(expMsg);
			
			System.out.println("Actual Message		"	+	actMsg		+	"Expected Message	"	+	expMsg);
			
			if(actMsg.startsWith(expMsg) && actMsg.endsWith(expMsg1))
			{
				return true;
			}
			else
			{
				return false;
			}
		
		
	}
	
	
	
	public boolean checkBankReconciliationImportReportforSameChequeNumSameAmount()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {

		Thread.sleep(2999);

		getDriver().navigate().refresh();

		Thread.sleep(2000);
		
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
		String expvalidationConfirmationMessage = "";

		System.out.println("validationConfirmationMessage : " + actvalidationConfirmationMessage + " Value Expected : "
				+ expvalidationConfirmationMessage);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(brsImport_ImportBtn));
		brsImport_ImportBtn.click();

		Thread.sleep(9500);

		Robot rb = new Robot();

		StringSelection str = new StringSelection(getBaseDir() + "\\autoIt\\ImportFiles\\BRS Import1.xlsx");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);

		rb.keyPress(KeyEvent.VK_CONTROL);
		rb.keyPress(KeyEvent.VK_V);

		rb.keyRelease(KeyEvent.VK_CONTROL);
		rb.keyRelease(KeyEvent.VK_V);

		rb.keyPress(KeyEvent.VK_ENTER);
		rb.keyRelease(KeyEvent.VK_ENTER);

		Thread.sleep(5000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(Sheet2));
		Sheet2.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sheetOkBtn));
		sheetOkBtn.click();
		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(brsImportBankDrpdwn));
		brsImportBankDrpdwn.click();
		brsImportBankDrpdwn.sendKeys("BankA");
		Thread.sleep(2000);

		brsImportBankDrpdwn.sendKeys(Keys.TAB);

		Thread.sleep(3000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(fieldMapRow1));
		fieldMapRow1.click();
		Thread.sleep(1999);
		FieldDpdwn.click();
		Select s1 = new Select(FieldDpdwn);
		s1.selectByVisibleText("chequeno");

		Thread.sleep(1999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(fieldMapRow2));
		fieldMapRow2.click();
		FieldDpdwn1.click();
		Select s2 = new Select(FieldDpdwn1);
		s2.selectByVisibleText("clearancedate");

		Thread.sleep(1999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(fieldMapRow3));
		fieldMapRow3.click();
		FieldDpdwn2.click();
		Select s3 = new Select(FieldDpdwn2);
		s3.selectByVisibleText("amount");

		Thread.sleep(1999);
		
		click(brsLoadBtn);
		Thread.sleep(8000);
		
		ArrayList<String>focusdata1stRowArray=new ArrayList<String>();
		
		for(int i=0;i<brsImportFocusData1stRowList.size();i++)
		{
			
			focusdata1stRowArray.add(brsImportFocusData1stRowList.get(i).getText());
		}
		
		String actFocusData1stRow=focusdata1stRowArray.toString();
		String expFocusData1stRow="[1, , Pending, 658921, 29/03/2025, Rct:14, "+getCurrentDate()+", 528.64, 0.00, Receipts]";
		
		System.out.println("Actual Focus Data 1st Row		"	+		actFocusData1stRow);
		System.out.println("Expect Focus Data 1st Row		"	+		expFocusData1stRow);
		
		
		ArrayList<String>Bankdata1stRowArray=new ArrayList<String>();
		
		for(int i=0;i<brsImportBankData1stRowList.size();i++)
		{
			
			Bankdata1stRowArray.add(brsImportBankData1stRowList.get(i).getText());
		}
		
		String actBankData1stRow=Bankdata1stRowArray.toString();
		String expBankData1stRow="[1, , 29/03/2025, 658921, 528.64, , ]";
		
		System.out.println("Actual Bank Data 1st Row		"	+		actBankData1stRow);
		System.out.println("Expect Bank Data 1st Row		"	+		expBankData1stRow);
		
		click(brsImportMatchedEntries);
		Thread.sleep(1000);
		
		boolean actColChkBoxEnabled=brsImportColumn1ChkBox.isSelected();
		System.out.println("Column Chk Box is Enables After Matched Entries		"	+		actColChkBoxEnabled);
		
		click(brsImportMisMatchedEntries);
		Thread.sleep(2000);
		
		boolean actMisMatchedEntriesTable=brsImportMismatchedTable.getText().isEmpty();
		System.out.println("MisMatched Table	"	+	actMisMatchedEntriesTable);
		
		click(brsImportMismatchedCancelBtn);
		Thread.sleep(2000);
		
		
		click(brsImportPost);
		Thread.sleep(1500);
		
		String expMsg="Selected Vouchers Posted Succesfully";
		String actMsg=checkValidationMessage(expvalidationConfirmationMessage);
		
		if(actMsg.equalsIgnoreCase(expMsg) && actColChkBoxEnabled && actFocusData1stRow.equalsIgnoreCase(expFocusData1stRow)
				&& actBankData1stRow.equalsIgnoreCase(expBankData1stRow))
		{
		
		return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean checkSavingReceiptsVoucherwithMultipleRows() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		

		Thread.sleep(2000);
		click(serachMenuTextHomePage);
		
		serachMenuTextHomePage.sendKeys("Receipts");
		Thread.sleep(2000);
		serachMenuTextHomePage.sendKeys(Keys.DOWN);
		serachMenuTextHomePage.sendKeys(Keys.ENTER);
		//click(searchMenuTextClick);
		 
		Thread.sleep(8000);
		new WebDriverWait(getDriver(), 300).until(ExpectedConditions.visibilityOf(newBtn));
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(newBtn));
		newBtn.click();
		Thread.sleep(4000);
		
		Thread.sleep(2000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNumberTxt));
		String docno=documentNumberTxt.getAttribute("value");
		
		click(caskBankAccountTxt);
		caskBankAccountTxt.sendKeys("BankB");
		Thread.sleep(2000);
		caskBankAccountTxt.sendKeys(Keys.TAB);
		
		
		
		click(departmentTxt);
		departmentTxt.sendKeys("INDIA");
		Thread.sleep(2000);
		departmentTxt.sendKeys(Keys.TAB);
		
		
		
		click(receipts_ChequeNoTxt);
		receipts_ChequeNoTxt.sendKeys("825963");
		Thread.sleep(2000);
		receipts_ChequeNoTxt.sendKeys(Keys.TAB);
		
		
		
		click(select1stRow_1stColumn);
		
		click(enter_DebitACTxt);
		enter_DebitACTxt.sendKeys("CustomerD");
		Thread.sleep(2000);
		enter_DebitACTxt.sendKeys(Keys.TAB);
		
		
		enter_Amount.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
		enter_Amount.sendKeys("19825.66");
		Thread.sleep(2000);
		enter_Amount.sendKeys(Keys.TAB);
		
		
		
		click(billRefNewReferenceTxt);
		click(pickBtn);
		Thread.sleep(2000);
		
		click(voucher_OkBtn);
		Thread.sleep(4000);
		
		
		click(select2ndRow_1stColumn);
		
		click(enter_DebitACTxt);
		enter_DebitACTxt.sendKeys("Customer C");
		Thread.sleep(2000);
		enter_DebitACTxt.sendKeys(Keys.TAB);
		
		
		enter_Amount.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
		enter_Amount.sendKeys("25896.35");
		Thread.sleep(2000);
		enter_Amount.sendKeys(Keys.TAB);
		
		
		
		click(billRefNewReferenceTxt);
		click(pickBtn);
		Thread.sleep(2000);
		
		click(voucher_OkBtn);
		Thread.sleep(4000);
		
		
		click(select3rdRow_1stColumn);
		
		click(enter_DebitACTxt);
		enter_DebitACTxt.sendKeys("Customer B");
		Thread.sleep(2000);
		enter_DebitACTxt.sendKeys(Keys.TAB);
		
		
		enter_Amount.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
		enter_Amount.sendKeys("8569.27");
		Thread.sleep(2000);
		enter_Amount.sendKeys(Keys.TAB);
		
				
		click(billRefNewReferenceTxt);
		click(pickBtn);
		Thread.sleep(2000);
		
		click(voucher_OkBtn);
		Thread.sleep(4000);
		
		
		click(select4thRow_1stColumn);
		
		click(enter_DebitACTxt);
		enter_DebitACTxt.sendKeys("Customer A");
		Thread.sleep(2000);
		enter_DebitACTxt.sendKeys(Keys.TAB);
		
		
		enter_Amount.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
		enter_Amount.sendKeys("58697.21");
		Thread.sleep(2000);
		enter_Amount.sendKeys(Keys.TAB);
		
				
		click(billRefNewReferenceTxt);
		click(pickBtn);
		Thread.sleep(2000);
		
		click(voucher_OkBtn);
		Thread.sleep(4000);
		
		
		click(select5thRow_1stColumn);
		
		click(enter_DebitACTxt);
		enter_DebitACTxt.sendKeys("CustomerD");
		Thread.sleep(2000);
		enter_DebitACTxt.sendKeys(Keys.TAB);
		
		
		enter_Amount.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
		enter_Amount.sendKeys("2458.63");
		Thread.sleep(2000);
		enter_Amount.sendKeys(Keys.TAB);
		
		
		
		click(billRefNewReferenceTxt);
		click(pickBtn);
		Thread.sleep(2000);
		
		click(voucher_OkBtn);
		Thread.sleep(4000);
		
		
		
		click(saveBtn);
		Thread.sleep(2000);
		
		String expMsg="Voucher saved successfully";
		String expMsg1=": 15";
		
		String actMsg=checkValidationMessage(expMsg);
		
		System.out.println("Actual Message		"	+	actMsg		+	"Expected Message	"	+	expMsg);
		
		if(actMsg.startsWith(expMsg) && actMsg.endsWith(expMsg1))
		{
			return true;
		}
		else
		{
			return false;
		}
	
	}

	
	
	public boolean checkBankReconciliationImportReportforMutipleRowsWithoutConsolidated()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {

		Thread.sleep(2999);

		getDriver().navigate().refresh();

		Thread.sleep(2000);
		
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
		String expvalidationConfirmationMessage = "";

		System.out.println("validationConfirmationMessage : " + actvalidationConfirmationMessage + " Value Expected : "
				+ expvalidationConfirmationMessage);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(brsImport_ImportBtn));
		brsImport_ImportBtn.click();

		Thread.sleep(9500);

		Robot rb = new Robot();

		StringSelection str = new StringSelection(getBaseDir() + "\\autoIt\\ImportFiles\\BRS Import1.xlsx");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);

		rb.keyPress(KeyEvent.VK_CONTROL);
		rb.keyPress(KeyEvent.VK_V);

		rb.keyRelease(KeyEvent.VK_CONTROL);
		rb.keyRelease(KeyEvent.VK_V);

		rb.keyPress(KeyEvent.VK_ENTER);
		rb.keyRelease(KeyEvent.VK_ENTER);

		Thread.sleep(5000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(Sheet3));
		Sheet3.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sheetOkBtn));
		sheetOkBtn.click();
		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(brsImportBankDrpdwn));
		brsImportBankDrpdwn.click();
		brsImportBankDrpdwn.sendKeys("BankB");
		Thread.sleep(2000);

		brsImportBankDrpdwn.sendKeys(Keys.TAB);

		Thread.sleep(3000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(fieldMapRow1));
		fieldMapRow1.click();
		Thread.sleep(1999);
		FieldDpdwn.click();
		Select s1 = new Select(FieldDpdwn);
		s1.selectByVisibleText("chequeno");

		Thread.sleep(1999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(fieldMapRow2));
		fieldMapRow2.click();
		FieldDpdwn1.click();
		Select s2 = new Select(FieldDpdwn1);
		s2.selectByVisibleText("clearancedate");

		Thread.sleep(1999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(fieldMapRow3));
		fieldMapRow3.click();
		FieldDpdwn2.click();
		Select s3 = new Select(FieldDpdwn2);
		s3.selectByVisibleText("amount");

		Thread.sleep(1999);
		
		click(brsLoadBtn);
		Thread.sleep(8000);
		
		ArrayList<String>focusdata1stRowArray=new ArrayList<String>();
		
		for(int i=0;i<brsImportFocusData1stRowList.size();i++)
		{
			
			focusdata1stRowArray.add(brsImportFocusData1stRowList.get(i).getText());
		}
		
		String actFocusData1stRow=focusdata1stRowArray.toString();
		String expFocusData1stRow="[1, , Pending, 825963, 30/07/2025, Rct:15, "+getCurrentDate()+", 25896.35, 0.00, Receipts]";
		
		System.out.println("Actual Focus Data 1st Row		"	+		actFocusData1stRow);
		System.out.println("Expect Focus Data 1st Row		"	+		expFocusData1stRow);
		
		ArrayList<String>focusdata2ndRowArray=new ArrayList<String>();
		
		for(int i=0;i<brsImportFocusData2ndRowList.size();i++)
		{
			
			focusdata2ndRowArray.add(brsImportFocusData2ndRowList.get(i).getText());
		}
		
		String actFocusData2ndRow=focusdata2ndRowArray.toString();
		String expFocusData2ndRow="[2, , Pending, 825963, 30/07/2025, Rct:15, "+getCurrentDate()+", 8569.27, 0.00, Receipts]";
		
		System.out.println("Actual Focus Data 2nd Row		"	+		actFocusData2ndRow);
		System.out.println("Expect Focus Data 2nd Row		"	+		expFocusData2ndRow);
		
		
		ArrayList<String>focusdata3rdRowArray=new ArrayList<String>();
		
		for(int i=0;i<brsImportFocusData3rdRowList.size();i++)
		{
			
			focusdata3rdRowArray.add(brsImportFocusData3rdRowList.get(i).getText());
		}
		
		String actFocusData3rdRow=focusdata3rdRowArray.toString();
		String expFocusData3rdRow="[3, , Pending, 825963, 30/07/2025, Rct:15, "+getCurrentDate()+", 58697.21, 0.00, Receipts]";
		
		System.out.println("Actual Focus Data 3rd Row		"	+		actFocusData3rdRow);
		System.out.println("Expect Focus Data 3rd Row		"	+		expFocusData3rdRow);
		
		
		ArrayList<String>focusdata4thRowArray=new ArrayList<String>();
		
		for(int i=0;i<brsImportFocusData4thRowList.size();i++)
		{
			
			focusdata4thRowArray.add(brsImportFocusData4thRowList.get(i).getText());
		}
		
		String actFocusData4thRow=focusdata4thRowArray.toString();
		String expFocusData4thRow="[4, , Pending, 825963, 30/07/2025, Rct:15, "+getCurrentDate()+", 19825.66, 0.00, Receipts]";
		
		System.out.println("Actual Focus Data 4th Row		"	+		actFocusData4thRow);
		System.out.println("Expect Focus Data 4th Row		"	+		expFocusData4thRow);
		
		
		ArrayList<String>focusdata5thRowArray=new ArrayList<String>();
		
		for(int i=0;i<brsImportFocusData5thRowList.size();i++)
		{
			
			focusdata5thRowArray.add(brsImportFocusData5thRowList.get(i).getText());
		}
		
		String actFocusData5thRow=focusdata5thRowArray.toString();
		String expFocusData5thRow="[5, , Pending, 825963, 30/07/2025, Rct:15, "+getCurrentDate()+", 2458.63, 0.00, Receipts]";
		
		System.out.println("Actual Focus Data 5th Row		"	+		actFocusData5thRow);
		System.out.println("Expect Focus Data 5th Row		"	+		expFocusData5thRow);
		
		
		ArrayList<String>Bankdata1stRowArray=new ArrayList<String>();
		
		for(int i=0;i<brsImportBankData1stRowList.size();i++)
		{
			
			Bankdata1stRowArray.add(brsImportBankData1stRowList.get(i).getText());
		}
		
		String actBankData1stRow=Bankdata1stRowArray.toString();
		String expBankData1stRow="[1, , 30/07/2025, 825963, 25896.35, , ]";
		
		System.out.println("Actual Bank Data 1st Row		"	+		actBankData1stRow);
		System.out.println("Expect Bank Data 1st Row		"	+		expBankData1stRow);
		
		ArrayList<String>Bankdata2ndRowArray=new ArrayList<String>();
		
		for(int i=0;i<brsImportBankData2ndRowList.size();i++)
		{
			
			Bankdata2ndRowArray.add(brsImportBankData2ndRowList.get(i).getText());
		}
		
		String actBankData2ndRow=Bankdata2ndRowArray.toString();
		String expBankData2ndRow="[2, , 30/07/2025, 825963, 8569.27, , ]";
		
		System.out.println("Actual Bank Data 2nd Row		"	+		actBankData2ndRow);
		System.out.println("Expect Bank Data 2nd Row		"	+		expBankData2ndRow);
		
		
		ArrayList<String>Bankdata3rdRowArray=new ArrayList<String>();
		
		for(int i=0;i<brsImportBankData3rdRowList.size();i++)
		{
			
			Bankdata3rdRowArray.add(brsImportBankData3rdRowList.get(i).getText());
		}
		
		String actBankData3rdRow=Bankdata3rdRowArray.toString();
		String expBankData3rdRow="[3, , 30/07/2025, 825963, 58697.21, , ]";
		
		System.out.println("Actual Bank Data 3rd Row		"	+		actBankData3rdRow);
		System.out.println("Expect Bank Data 3rd Row		"	+		expBankData3rdRow);
		
		
		ArrayList<String>Bankdata4thRowArray=new ArrayList<String>();
		
		for(int i=0;i<brsImportBankData4thRowList.size();i++)
		{
			
			Bankdata4thRowArray.add(brsImportBankData4thRowList.get(i).getText());
		}
		
		String actBankData4thRow=Bankdata4thRowArray.toString();
		String expBankData4thRow="[4, , 30/07/2025, 825963, 19825.66, , ]";
		
		System.out.println("Actual Bank Data 4th Row		"	+		actBankData4thRow);
		System.out.println("Expect Bank Data 4th Row		"	+		expBankData4thRow);
		
		
		ArrayList<String>Bankdata5thRowArray=new ArrayList<String>();
		
		for(int i=0;i<brsImportBankData5thRowList.size();i++)
		{
			
			Bankdata5thRowArray.add(brsImportBankData5thRowList.get(i).getText());
		}
		
		String actBankData5thRow=Bankdata5thRowArray.toString();
		String expBankData5thRow="[5, , 30/07/2025, 825963, 2458.63, , ]";
		
		System.out.println("Actual Bank Data 5th Row		"	+		actBankData5thRow);
		System.out.println("Expect Bank Data 5th Row		"	+		expBankData5thRow);
		
		boolean act1stRowBoth= focusdata1stRowArray.containsAll(Bankdata1stRowArray);
		boolean act2ndRowBoth= focusdata2ndRowArray.containsAll(Bankdata2ndRowArray);
		boolean act3rdRowBoth= focusdata3rdRowArray.containsAll(Bankdata3rdRowArray);
		boolean act4thRowBoth= focusdata4thRowArray.containsAll(Bankdata4thRowArray);
		boolean act5thRowBoth= focusdata5thRowArray.containsAll(Bankdata5thRowArray);
		
		System.out.println(act1stRowBoth);
		System.out.println(act2ndRowBoth);
		System.out.println(act3rdRowBoth);
		System.out.println(act4thRowBoth);
		System.out.println(act5thRowBoth);
		
		click(brsImportMatchedEntries);
		Thread.sleep(1000);
		
		boolean actColChkBoxEnabled=brsImportColumn1ChkBox.isSelected();
		System.out.println("Column Chk Box is Enables After Matched Entries		"	+		actColChkBoxEnabled);
		
		click(brsImportMisMatchedEntries);
		Thread.sleep(2000);
		
		boolean actMisMatchedEntriesTable=brsImportMismatchedTable.getText().isEmpty();
		System.out.println("MisMatched Table	"	+	actMisMatchedEntriesTable);
		
		click(brsImportMismatchedCancelBtn);
		Thread.sleep(2000);
		
		
		click(brsImportPost);
		Thread.sleep(1500);
		
		String expMsg="Selected Vouchers Posted Succesfully";
		String actMsg=checkValidationMessage(expvalidationConfirmationMessage);
		
		
		
		if(actMsg.equalsIgnoreCase(expMsg) && actColChkBoxEnabled && actFocusData1stRow.equalsIgnoreCase(expFocusData1stRow)
				&& actBankData1stRow.equalsIgnoreCase(expBankData1stRow) && actFocusData3rdRow.equalsIgnoreCase(expFocusData3rdRow)
				&& actFocusData2ndRow.equalsIgnoreCase(expFocusData2ndRow) && actFocusData4thRow.equalsIgnoreCase(expFocusData4thRow)
				&& actFocusData5thRow.equalsIgnoreCase(expFocusData5thRow) && actBankData2ndRow.equalsIgnoreCase(expBankData2ndRow)
				&& actBankData3rdRow.equalsIgnoreCase(expBankData3rdRow) && actBankData4thRow.equalsIgnoreCase(expBankData4thRow)
				&& actBankData5thRow.equalsIgnoreCase(expBankData5thRow) && act1stRowBoth  && act2ndRowBoth 
				&& act3rdRowBoth && act4thRowBoth && act5thRowBoth )
		{
		
		return true;
		}
		else
		{
			return false;
		}
	}
	
	
	public boolean checkSavingReceiptsVocuherforConsolidated() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		
		Thread.sleep(2000);
		click(serachMenuTextHomePage);
		
		serachMenuTextHomePage.sendKeys("Receipts");
		Thread.sleep(2000);
		serachMenuTextHomePage.sendKeys(Keys.DOWN);
		serachMenuTextHomePage.sendKeys(Keys.ENTER);
		//click(searchMenuTextClick);
		 
		Thread.sleep(8000);
		new WebDriverWait(getDriver(), 300).until(ExpectedConditions.visibilityOf(newBtn));
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(newBtn));
		newBtn.click();
		Thread.sleep(4000);
		
		Thread.sleep(2000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNumberTxt));
		String docno=documentNumberTxt.getAttribute("value");
		
		click(caskBankAccountTxt);
		caskBankAccountTxt.sendKeys("BankB");
		Thread.sleep(2000);
		caskBankAccountTxt.sendKeys(Keys.TAB);
		
		
		
		click(departmentTxt);
		departmentTxt.sendKeys("INDIA");
		Thread.sleep(2000);
		departmentTxt.sendKeys(Keys.TAB);
		
		
		
		click(receipts_ChequeNoTxt);
		receipts_ChequeNoTxt.sendKeys("78659");
		Thread.sleep(2000);
		receipts_ChequeNoTxt.sendKeys(Keys.TAB);
		
		
		
		click(select1stRow_1stColumn);
		
		click(enter_DebitACTxt);
		enter_DebitACTxt.sendKeys("CustomerD");
		Thread.sleep(2000);
		enter_DebitACTxt.sendKeys(Keys.TAB);
		
		
		enter_Amount.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
		enter_Amount.sendKeys("10000");
		Thread.sleep(2000);
		enter_Amount.sendKeys(Keys.TAB);
		
		
		
		click(billRefNewReferenceTxt);
		click(pickBtn);
		Thread.sleep(2000);
		
		click(voucher_OkBtn);
		Thread.sleep(4000);
		
		
		click(select2ndRow_1stColumn);
		
		click(enter_DebitACTxt);
		enter_DebitACTxt.sendKeys("Customer C");
		Thread.sleep(2000);
		enter_DebitACTxt.sendKeys(Keys.TAB);
		
		
		enter_Amount.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
		enter_Amount.sendKeys("15000");
		Thread.sleep(2000);
		enter_Amount.sendKeys(Keys.TAB);
		
		
		
		click(billRefNewReferenceTxt);
		click(pickBtn);
		Thread.sleep(2000);
		
		click(voucher_OkBtn);
		Thread.sleep(4000);
		
		
		click(select3rdRow_1stColumn);
		
		click(enter_DebitACTxt);
		enter_DebitACTxt.sendKeys("Customer B");
		Thread.sleep(2000);
		enter_DebitACTxt.sendKeys(Keys.TAB);
		
		
		enter_Amount.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
		enter_Amount.sendKeys("20000");
		Thread.sleep(2000);
		enter_Amount.sendKeys(Keys.TAB);
		
				
		click(billRefNewReferenceTxt);
		click(pickBtn);
		Thread.sleep(2000);
		
		click(voucher_OkBtn);
		Thread.sleep(4000);
		
		
		click(select4thRow_1stColumn);
		
		click(enter_DebitACTxt);
		enter_DebitACTxt.sendKeys("Customer A");
		Thread.sleep(2000);
		enter_DebitACTxt.sendKeys(Keys.TAB);
		
		
		enter_Amount.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
		enter_Amount.sendKeys("25000");
		Thread.sleep(2000);
		enter_Amount.sendKeys(Keys.TAB);
		
				
		click(billRefNewReferenceTxt);
		click(pickBtn);
		Thread.sleep(2000);
		
		click(voucher_OkBtn);
		Thread.sleep(4000);
		
		
		click(select5thRow_1stColumn);
		
		click(enter_DebitACTxt);
		enter_DebitACTxt.sendKeys("CustomerD");
		Thread.sleep(2000);
		enter_DebitACTxt.sendKeys(Keys.TAB);
		
		
		enter_Amount.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
		enter_Amount.sendKeys("30000");
		Thread.sleep(2000);
		enter_Amount.sendKeys(Keys.TAB);
		
		
		
		click(billRefNewReferenceTxt);
		click(pickBtn);
		Thread.sleep(2000);
		
		click(voucher_OkBtn);
		Thread.sleep(4000);
		
		
		
		click(saveBtn);
		Thread.sleep(2000);
		
		String expMsg="Voucher saved successfully";
		String expMsg1=": 16";
		
		String actMsg=checkValidationMessage(expMsg);
		
		System.out.println("Actual Message		"	+	actMsg		+	"Expected Message	"	+	expMsg);
		
		if(actMsg.startsWith(expMsg) && actMsg.endsWith(expMsg1))
		{
			return true;
		}
		else
		{
			return false;
		}
	
	}
	
	
	public boolean checkBankReconciliationImportReportforMutipleRowsWithConsolidated() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		Thread.sleep(2999);

		getDriver().navigate().refresh();

		Thread.sleep(2000);
		
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
		String expvalidationConfirmationMessage = "";

		System.out.println("validationConfirmationMessage : " + actvalidationConfirmationMessage + " Value Expected : "
				+ expvalidationConfirmationMessage);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(brsImport_ImportBtn));
		brsImport_ImportBtn.click();

		Thread.sleep(9500);

		Robot rb = new Robot();

		StringSelection str = new StringSelection(getBaseDir() + "\\autoIt\\ImportFiles\\BRS Import1.xlsx");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);

		rb.keyPress(KeyEvent.VK_CONTROL);
		rb.keyPress(KeyEvent.VK_V);

		rb.keyRelease(KeyEvent.VK_CONTROL);
		rb.keyRelease(KeyEvent.VK_V);

		rb.keyPress(KeyEvent.VK_ENTER);
		rb.keyRelease(KeyEvent.VK_ENTER);

		Thread.sleep(5000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(Sheet4));
		Sheet4.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sheetOkBtn));
		sheetOkBtn.click();
		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(brsImportBankDrpdwn));
		brsImportBankDrpdwn.click();
		brsImportBankDrpdwn.sendKeys("BankB");
		Thread.sleep(2000);

		brsImportBankDrpdwn.sendKeys(Keys.TAB);

		Thread.sleep(3000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(fieldMapRow1));
		fieldMapRow1.click();
		Thread.sleep(1999);
		FieldDpdwn.click();
		Select s1 = new Select(FieldDpdwn);
		s1.selectByVisibleText("chequeno");

		Thread.sleep(1999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(fieldMapRow2));
		fieldMapRow2.click();
		FieldDpdwn1.click();
		Select s2 = new Select(FieldDpdwn1);
		s2.selectByVisibleText("clearancedate");

		Thread.sleep(1999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(fieldMapRow3));
		fieldMapRow3.click();
		FieldDpdwn2.click();
		Select s3 = new Select(FieldDpdwn2);
		s3.selectByVisibleText("amount");

		Thread.sleep(1999);
		
		if(brsImport_showConsolidatedChkBox.isSelected()==false)
		{
			brsImport_showConsolidatedChkBoxSelected.click();
		}
		
		
		click(brsLoadBtn);
		Thread.sleep(8000);
		
		ArrayList<String>focusdata1stRowArray=new ArrayList<String>();
		
		for(int i=0;i<brsImportFocusData1stRowList.size();i++)
		{
			
			focusdata1stRowArray.add(brsImportFocusData1stRowList.get(i).getText());
		}
		
		String actFocusData1stRow=focusdata1stRowArray.toString();
		String expFocusData1stRow="[1, , Pending, 78659, 22/04/2025, Rct:16, "+getCurrentDate()+", 100000.00, 0.00, Receipts]";
		
		System.out.println("Actual Focus Data 1st Row		"	+		actFocusData1stRow);
		System.out.println("Expect Focus Data 1st Row		"	+		expFocusData1stRow);
		
		
		ArrayList<String>Bankdata1stRowArray=new ArrayList<String>();
		
		for(int i=0;i<brsImportBankData1stRowList.size();i++)
		{
			
			Bankdata1stRowArray.add(brsImportBankData1stRowList.get(i).getText());
		}
		
		String actBankData1stRow=Bankdata1stRowArray.toString();
		String expBankData1stRow="[1, , 22/04/2025, 78659, 100000.00, , ]";
		
		System.out.println("Actual Bank Data 1st Row		"	+		actBankData1stRow);
		System.out.println("Expect Bank Data 1st Row		"	+		expBankData1stRow);
		
		boolean act1stRowBoth= focusdata1stRowArray.containsAll(Bankdata1stRowArray);
		
		System.out.println(act1stRowBoth);
		
		
		click(brsImportMatchedEntries);
		Thread.sleep(1000);
		
		boolean actColChkBoxEnabled=brsImportColumn1ChkBox.isSelected();
		System.out.println("Column Chk Box is Enables After Matched Entries		"	+		actColChkBoxEnabled);
		
		click(brsImportMisMatchedEntries);
		Thread.sleep(2000);
		
		boolean actMisMatchedEntriesTable=brsImportMismatchedTable.getText().isEmpty();
		System.out.println("MisMatched Table	"	+	actMisMatchedEntriesTable);
		
		click(brsImportMismatchedCancelBtn);
		Thread.sleep(2000);
		
		
		click(brsImportPost);
		Thread.sleep(1500);
		
		String expMsg="Selected Vouchers Posted Succesfully";
		String actMsg=checkValidationMessage(expvalidationConfirmationMessage);
		
		
		
		if(actMsg.equalsIgnoreCase(expMsg) && actColChkBoxEnabled && actFocusData1stRow.equalsIgnoreCase(expFocusData1stRow)
				&& actBankData1stRow.equalsIgnoreCase(expBankData1stRow) && act1stRowBoth)
		{
		
		return true;
		}
		else
		{
			return false;
		}
	
	}
	
	
	@FindBy(xpath="//*[@id='BasisOn']")
	public static WebElement brsMisMatchVoucherType;
	
	@FindBy(xpath="//*[@id='misMatchVouchers']")
	public static WebElement brsMisMatchVoucherTxt;
	
	@FindBy(xpath="//*[@id='btnDocument1']")
	public static WebElement brsMisMatchRaiseVoucher1;
	
	@FindBy(xpath="//*[@id='btnDocument2']")
	public static WebElement brsMisMatchRaiseVoucher2;
	
	@FindBy(xpath="//input[@value='Export Data']")
	public static WebElement brsMisMatchExportData;
	
	@FindBy(xpath="//*[@id='BRSMisMatchTable_body']//tr[1]//td")
	public static List<WebElement> brsMisMatch1stRowList;
	
	@FindBy(xpath="//*[@id='BRSMisMatchTable_body']//tr[2]//td")
	public static List<WebElement> brsMisMatch2ndRowList;
	
	public boolean checkRaisingVoucherfromBankReconcilationImport() throws InterruptedException, AWTException
	{
		Thread.sleep(2999);

		getDriver().navigate().refresh();

		Thread.sleep(2000);
		
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
		String expvalidationConfirmationMessage = "";

		System.out.println("validationConfirmationMessage : " + actvalidationConfirmationMessage + " Value Expected : "
				+ expvalidationConfirmationMessage);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(brsImport_ImportBtn));
		brsImport_ImportBtn.click();

		Thread.sleep(9500);

		Robot rb = new Robot();

		StringSelection str = new StringSelection(getBaseDir() + "\\autoIt\\ImportFiles\\BRS Import1.xlsx");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);

		rb.keyPress(KeyEvent.VK_CONTROL);
		rb.keyPress(KeyEvent.VK_V);

		rb.keyRelease(KeyEvent.VK_CONTROL);
		rb.keyRelease(KeyEvent.VK_V);

		rb.keyPress(KeyEvent.VK_ENTER);
		rb.keyRelease(KeyEvent.VK_ENTER);

		Thread.sleep(5000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(SheetMisMatch));
		SheetMisMatch.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sheetOkBtn));
		sheetOkBtn.click();
		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(brsImportBankDrpdwn));
		brsImportBankDrpdwn.click();
		brsImportBankDrpdwn.sendKeys("BankB");
		Thread.sleep(2000);

		brsImportBankDrpdwn.sendKeys(Keys.TAB);

		Thread.sleep(3000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(fieldMapRow1));
		fieldMapRow1.click();
		Thread.sleep(1999);
		FieldDpdwn.click();
		Select s1 = new Select(FieldDpdwn);
		s1.selectByVisibleText("chequeno");

		Thread.sleep(1999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(fieldMapRow2));
		fieldMapRow2.click();
		FieldDpdwn1.click();
		Select s2 = new Select(FieldDpdwn1);
		s2.selectByVisibleText("clearancedate");

		Thread.sleep(1999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(fieldMapRow3));
		fieldMapRow3.click();
		FieldDpdwn2.click();
		Select s3 = new Select(FieldDpdwn2);
		s3.selectByVisibleText("amount");

		Thread.sleep(1999);
		
		if(brsImport_showConsolidatedChkBox.isSelected()==false)
		{
			brsImport_showConsolidatedChkBoxSelected.click();
		}
		
		
		click(brsLoadBtn);
		Thread.sleep(8000);
		
		click(brsImportMisMatchedEntries);
		Thread.sleep(3500);
		
		
		ArrayList<String>misMatch1stRowArray=new ArrayList<String>();
		for(int i=0;i<brsMisMatch1stRowList.size();i++)
		{
			misMatch1stRowArray.add(brsMisMatch1stRowList.get(i).getText());
		}
		String actMisMatch1stRowList=misMatch1stRowArray.toString();
		String expMisMatch1stRowList="[, , 16-Jun-2025, 987654, 148.58, , ]";
		
		System.out.println("Actual MisMatch 1stRow List	"		+		actMisMatch1stRowList);
		System.out.println("Expect MisMatch 1stRow List	"		+		expMisMatch1stRowList);
		
		
		ArrayList<String>misMatch2ndRowArray=new ArrayList<String>();
		for(int i=0;i<brsMisMatch2ndRowList.size();i++)
		{
			misMatch2ndRowArray.add(brsMisMatch2ndRowList.get(i).getText());
		}
		String actMisMatch2ndRowList=misMatch2ndRowArray.toString();
		String expMisMatch2ndRowList="[, , 16-Jun-2025, 987654, 625.21, , ]";
		
		System.out.println("Actual MisMatch 2ndRow List	"		+		actMisMatch2ndRowList);
		System.out.println("Expect MisMatch 2ndRow List	"		+		expMisMatch2ndRowList);
		
		
		click(brsMisMatchVoucherType);
		brsMisMatchVoucherType.sendKeys("Receipts");
		Thread.sleep(2000);
		brsMisMatchVoucherType.sendKeys(Keys.TAB);
		
		click(brsMisMatchVoucherTxt);
		brsMisMatchVoucherTxt.sendKeys("Receipts VAT");
		Thread.sleep(2000);
		brsMisMatchVoucherTxt.sendKeys(Keys.TAB);
		
		click(brsMisMatchRaiseVoucher1);
		Thread.sleep(2000);
		
		
		ArrayList<String> openTabs = new ArrayList<String>(getDriver().getWindowHandles());

		int actOpenWindowsCount = getDriver().getWindowHandles().size();
		int expOpenWindowsCount = 2;

		getDriver().switchTo().window(openTabs.get(1));
		Thread.sleep(4000);
		
		String actBankNameAfterRaise=caskBankAccountTxt.getAttribute("value");
		String expBankNameAfterRaise="BankB";
		
		System.out.println("Bank Name After Raise Vocuher from MisMatch		"	+	"Actual		"	+	actBankNameAfterRaise);
		System.out.println("Bank Name After Raise Vocuher from MisMatch		"	+	"Expect		"	+	expBankNameAfterRaise);
		
		
		getDriver().switchTo().window(openTabs.get(1)).close();

		Thread.sleep(2000);

		getDriver().switchTo().window(openTabs.get(0));
		Thread.sleep(2000);

		click(brsImportMismatchedCancelBtn);
		Thread.sleep(1000);
		
		if(actMisMatch1stRowList.equalsIgnoreCase(expMisMatch1stRowList) && actMisMatch2ndRowList.equalsIgnoreCase(expMisMatch2ndRowList)
				&& actBankNameAfterRaise.equalsIgnoreCase(expBankNameAfterRaise))
		{
			return true;
		}
		else
		{
			return false;
		}
		
		
	}
	
	public boolean checkSavingReceiptVoucherWithCurrencyUSD() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{

		
		Thread.sleep(2000);
		click(serachMenuTextHomePage);
		
		serachMenuTextHomePage.sendKeys("Receipts");
		Thread.sleep(2000);
		serachMenuTextHomePage.sendKeys(Keys.DOWN);
		serachMenuTextHomePage.sendKeys(Keys.ENTER);
		//click(searchMenuTextClick);
		 
		Thread.sleep(8000);
		new WebDriverWait(getDriver(), 300).until(ExpectedConditions.visibilityOf(newBtn));
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(newBtn));
		newBtn.click();
		Thread.sleep(4000);
		
		Thread.sleep(2000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(documentNumberTxt));
		String docno=documentNumberTxt.getAttribute("value");
		
		click(caskBankAccountTxt);
		caskBankAccountTxt.sendKeys("BankB");
		Thread.sleep(2000);
		caskBankAccountTxt.sendKeys(Keys.TAB);
		
		click(voucherHeaderCurrency);
		voucherHeaderCurrency.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
		voucherHeaderCurrency.sendKeys("USD");
		Thread.sleep(2000);
		voucherHeaderCurrency.sendKeys(Keys.TAB);
		
		
		click(departmentTxt);
		departmentTxt.sendKeys("INDIA");
		Thread.sleep(2000);
		departmentTxt.sendKeys(Keys.TAB);
		
		
		
		click(receipts_ChequeNoTxt);
		receipts_ChequeNoTxt.sendKeys("456123");
		Thread.sleep(2000);
		receipts_ChequeNoTxt.sendKeys(Keys.TAB);
		
		
		
		click(select1stRow_1stColumn);
		
		click(enter_DebitACTxt);
		enter_DebitACTxt.sendKeys("CustomerD");
		Thread.sleep(2000);
		enter_DebitACTxt.sendKeys(Keys.TAB);
		
		
		enter_Amount.sendKeys(Keys.END,Keys.SHIFT,Keys.HOME);
		enter_Amount.sendKeys("116");
		Thread.sleep(2000);
		enter_Amount.sendKeys(Keys.TAB);
		
		
		
		click(billRefNewReferenceTxt);
		click(pickBtn);
		Thread.sleep(2000);
		
		click(voucher_OkBtn);
		Thread.sleep(4000);
		
		
		click(saveBtn);
		Thread.sleep(2000);
		
		String expMsg="Voucher saved successfully";
		String expMsg1=": 17";
		
		String actMsg=checkValidationMessage(expMsg);
		
		System.out.println("Actual Message		"	+	actMsg		+	"Expected Message	"	+	expMsg);
		
		if(actMsg.startsWith(expMsg) && actMsg.endsWith(expMsg1))
		{
			return true;
		}
		else
		{
			return false;
		}
	
	
		
	}
	
	public boolean checkBankReconcillationImportReportforBaseCurrency() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		


		Thread.sleep(2999);

		getDriver().navigate().refresh();

		Thread.sleep(2000);
		
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
		String expvalidationConfirmationMessage = "";

		System.out.println("validationConfirmationMessage : " + actvalidationConfirmationMessage + " Value Expected : "
				+ expvalidationConfirmationMessage);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(brsImport_ImportBtn));
		brsImport_ImportBtn.click();

		Thread.sleep(9500);

		Robot rb = new Robot();

		StringSelection str = new StringSelection(getBaseDir() + "\\autoIt\\ImportFiles\\BRS Import1.xlsx");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);

		rb.keyPress(KeyEvent.VK_CONTROL);
		rb.keyPress(KeyEvent.VK_V);

		rb.keyRelease(KeyEvent.VK_CONTROL);
		rb.keyRelease(KeyEvent.VK_V);

		rb.keyPress(KeyEvent.VK_ENTER);
		rb.keyRelease(KeyEvent.VK_ENTER);

		Thread.sleep(5000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(SheetCurrency));
		SheetCurrency.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(sheetOkBtn));
		sheetOkBtn.click();
		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(brsImportBankDrpdwn));
		brsImportBankDrpdwn.click();
		brsImportBankDrpdwn.sendKeys("BankB");
		Thread.sleep(2000);

		brsImportBankDrpdwn.sendKeys(Keys.TAB);

		Thread.sleep(3000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(fieldMapRow1));
		fieldMapRow1.click();
		Thread.sleep(1999);
		FieldDpdwn.click();
		Select s1 = new Select(FieldDpdwn);
		s1.selectByVisibleText("chequeno");

		Thread.sleep(1999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(fieldMapRow2));
		fieldMapRow2.click();
		FieldDpdwn1.click();
		Select s2 = new Select(FieldDpdwn1);
		s2.selectByVisibleText("clearancedate");

		Thread.sleep(1999);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(fieldMapRow3));
		fieldMapRow3.click();
		FieldDpdwn2.click();
		Select s3 = new Select(FieldDpdwn2);
		s3.selectByVisibleText("amount");

		Thread.sleep(1999);
		
		click(brsLoadBtn);
		Thread.sleep(8000);
		
		ArrayList<String>focusdata1stRowArray=new ArrayList<String>();
		
		for(int i=0;i<brsImportFocusData1stRowList.size();i++)
		{
			
			focusdata1stRowArray.add(brsImportFocusData1stRowList.get(i).getText());
		}
		
		String actFocusData1stRow=focusdata1stRowArray.toString();
		String expFocusData1stRow="[1, , Pending, 456123, 03/02/2025, Rct:17, "+getCurrentDate()+", 8120.00, 0.00, Receipts]";
		
		System.out.println("Actual Focus Data 1st Row		"	+		actFocusData1stRow);
		System.out.println("Expect Focus Data 1st Row		"	+		expFocusData1stRow);
		
		
		ArrayList<String>Bankdata1stRowArray=new ArrayList<String>();
		
		for(int i=0;i<brsImportBankData1stRowList.size();i++)
		{
			
			Bankdata1stRowArray.add(brsImportBankData1stRowList.get(i).getText());
		}
		
		String actBankData1stRow=Bankdata1stRowArray.toString();
		String expBankData1stRow="[1, , 03/02/2025, 456123, 8120.00, , ]";
		
		System.out.println("Actual Bank Data 1st Row		"	+		actBankData1stRow);
		System.out.println("Expect Bank Data 1st Row		"	+		expBankData1stRow);
		
		click(brsImportMatchedEntries);
		Thread.sleep(1000);
		
		boolean actColChkBoxEnabled=brsImportColumn1ChkBox.isSelected();
		System.out.println("Column Chk Box is Enables After Matched Entries		"	+		actColChkBoxEnabled);
		
		click(brsImportPost);
		Thread.sleep(1500);
		
		String expMsg="Selected Vouchers Posted Succesfully";
		String actMsg=checkValidationMessage(expvalidationConfirmationMessage);
		
		if(actMsg.equalsIgnoreCase(expMsg) && actColChkBoxEnabled && actFocusData1stRow.equalsIgnoreCase(expFocusData1stRow)
				&& actBankData1stRow.equalsIgnoreCase(expBankData1stRow))
		{
		
		return true;
		}
		else
		{
			return false;
		}
	
		
	}
	
	
	
	public BRSNewPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
}
