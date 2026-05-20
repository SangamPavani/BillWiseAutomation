package com.focus.testcases;

import java.awt.AWTException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.focus.Pages.LoginPage;
import com.focus.Pages.ReportDesignerNew;
import com.focus.base.BaseEngine;

public class ReportDesignerNewTest extends BaseEngine{
	
	
	ReportDesignerNew RDN;
		
	@Test(priority=1000)
	public void checkLogin() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		RDN=new ReportDesignerNew(getDriver());
		Assert.assertEquals(RDN.checkLogin(), true);
	}
	
	
	@Test(priority=1001)
	public void checkRestoreOptionsCompanyAndLogin() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		RDN=new ReportDesignerNew(getDriver());
		RDN.checkRestoreOptionsCompanyAndLogin();
	}
	
	
	@Test(priority=1002)
	public void checkRDDetailReportCustomization() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		RDN=new ReportDesignerNew(getDriver());
		Assert.assertEquals(RDN.checkRDDetailReportCustomization(), true);
	}
	
	@Test(priority=1003)
	public void checkExporttoExcelinRDDetailReportofBrand() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		RDN=new ReportDesignerNew(getDriver());
		Assert.assertEquals(RDN.checkExporttoExcelinRDDetailReportofBrand(), true);
	}

	@Test(priority=1004)
	public void checkRowFormattinginRDDetailReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		RDN=new ReportDesignerNew(getDriver());
		Assert.assertEquals(RDN.checkRowFormattinginRDDetailReport(), true);
	}
	
	
	@Test(priority=1005)
	public void checkSortingOrderofGrossinRDDetilsReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		RDN=new ReportDesignerNew(getDriver());
		Assert.assertEquals(RDN.checkSortingOrderofGrossinRDDetilsReport(), true);
	}
	
	
	@Test(priority=1006)
	public void checkColumnHeadinginCenterinRDDetailReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		RDN=new ReportDesignerNew(getDriver());
		Assert.assertEquals(RDN.checkColumnHeadinginCenterinRDDetailReport(), true);
	}
	
	
	//cube report
	
	
	
	
	@Test(priority=1010)
	public void checkCreatingRDCubeReportforDepartmentParameter() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		RDN=new ReportDesignerNew(getDriver());
		Assert.assertEquals(RDN.checkCreatingRDCubeReportforDepartmentParameter(), true);
	}
	
	
	@Test(priority=1011)
	public void checkRDCubeReporttoDisplayDepartments() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		RDN=new ReportDesignerNew(getDriver());
		Assert.assertEquals(RDN.checkRDCubeReporttoDisplayDepartments(), true);
	}
	
	@Test(priority=1012)
	public void checkExporttoExcelinRDCubeReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		RDN=new ReportDesignerNew(getDriver());
		Assert.assertEquals(RDN.checkExporttoExcelinRDCubeReport(), true);
	}
	
		
	
	@Test(priority=1013)
	public void checkSavingRDDetailReportforMultipleParameter() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		RDN=new ReportDesignerNew(getDriver());
		Assert.assertEquals(RDN.checkSavingRDDetailReportforMultipleParameter(), true);
	}
	
	
	
	
	
	@Test(priority=1014)
	public void checkFilterinRDMultipleParameterReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		RDN=new ReportDesignerNew(getDriver());
		Assert.assertEquals(RDN.checkFilterinRDMultipleParameterReport(), true);
	}
	
	@Test(priority=1015)
	public void checkYearAndMonthWiseDatainRDCubeReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		RDN=new ReportDesignerNew(getDriver());
		Assert.assertEquals(RDN.checkYearAndMonthWiseDatainRDCubeReport(), true);
	}
	
	
	
	
	@Test(priority=1016)
	public void checkRDReportAfterChangingRatetoAverage() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		RDN=new ReportDesignerNew(getDriver());
		Assert.assertEquals(RDN.checkRDReportAfterChangingRatetoAverage(), true);
	}
	
	
	
	
	@Test(priority=1018)
	public void checkSavingVoucherwithTwoRowsinSalesOrder() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		RDN=new ReportDesignerNew(getDriver());
		Assert.assertEquals(RDN.checkSavingVoucherwithTwoRowsinSalesOrder(), true);
	}
		
	
	@Test(priority=1019)
	public void checkSavingVoucherConsumingoneRowinSalesInvoiceVAT() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		RDN=new ReportDesignerNew(getDriver());
		Assert.assertEquals(RDN.checkSavingVoucherConsumingoneRowinSalesInvoiceVAT(), true);
	}
	
	
	
	@Test(priority=1020)
	public void checkRDPendingLinksReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		RDN=new ReportDesignerNew(getDriver());
		Assert.assertEquals(RDN.checkRDPendingLinksReport(), true);
	}
	
	
	
	
	@Test(priority=1030)
	public void checkLogoutReportDesignerPage() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		RDN=new ReportDesignerNew(getDriver());
		Assert.assertEquals(RDN.checkLogoutReportDesignerPage(), true);
	}
	
}
