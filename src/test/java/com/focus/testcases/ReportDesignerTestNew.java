package com.focus.testcases;

import java.awt.AWTException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.focus.Pages.ReportDesignerPageNew;
import com.focus.base.BaseEngine;

public class ReportDesignerTestNew extends BaseEngine{
	
	ReportDesignerPageNew RDTN;
	
	
	@Test(priority=500)
	public void checkLogin() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		RDTN=new ReportDesignerPageNew(getDriver());
		
		Assert.assertEquals(RDTN.checkLogin(), true);
	}

	@Test(priority=501)
	public void checkRestoreOptionsCompanyAndLogin() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		RDTN=new ReportDesignerPageNew(getDriver());
		
		RDTN.checkRestoreOptionsCompanyAndLogin();
		
	}
		
	@Test(priority=502)
	public void checkColumnFilteronVoucherclassinMRNDetailReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		RDTN=new ReportDesignerPageNew(getDriver());
		
		Assert.assertEquals(RDTN.checkColumnFilteronVoucherclassinMRNDetailReport(),true);
		
	}
	
	
	
	@Test(priority=503)
	public void checkSelectRowsinRDMRNDetailReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		RDTN=new ReportDesignerPageNew(getDriver());
		
		Assert.assertEquals(RDTN.checkSelectRowsinRDMRNDetailReport(), true);
		
	}
	
	
	
	@Test(priority=504)
	public void checkSortingOptioninRDMRNDetailReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		RDTN=new ReportDesignerPageNew(getDriver());
		
		Assert.assertEquals(RDTN.checkSortingOptioninRDMRNDetailReport(), true);
		
		
	}
		
	
	@Test(priority=505)
	public void checkAnalyzeinRDMRNDetailReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		RDTN=new ReportDesignerPageNew(getDriver());
		
		Assert.assertEquals(RDTN.checkAnalyzeinRDMRNDetailReport(),true);
		
	}
	
	
	
	
	@Test(priority=506)
	public void checkExporttoExcelBeforeSavingAnalyzeReportofRDMRNDetailReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		RDTN=new ReportDesignerPageNew(getDriver());
		
		Assert.assertEquals(RDTN.checkExporttoExcelBeforeSavingAnalyzeReportofRDMRNDetailReport(),true);
		
	}
	
	
	
	@Test(priority=507)
	public void checkExporttoPDFBeforeSavingAnalyzeReportofRDMRNDetailReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		RDTN=new ReportDesignerPageNew(getDriver());
		
		Assert.assertEquals(RDTN.checkExporttoPDFBeforeSavingAnalyzeReportofRDMRNDetailReport(),true);
		
	}
	
	
	
	@Test(priority=508)
	public void checkSavingAnalyzeofRDMRNDetailReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		RDTN=new ReportDesignerPageNew(getDriver());
		
		Assert.assertEquals(RDTN.checkSavingAnalyzeofRDMRNDetailReport(),true);
		
	}
	
	
	@Test(priority=509)
	public void checkRDMRNCubeReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		RDTN=new ReportDesignerPageNew(getDriver());
		
		Assert.assertEquals(RDTN.checkRDMRNCubeReport(),true);
		
	}

	
	@Test(priority=510)
	public void checkExporttoPDFinFromEntryRDMRNCubeReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		RDTN=new ReportDesignerPageNew(getDriver());
		
		Assert.assertEquals(RDTN.checkExporttoPDFinFromEntryRDMRNCubeReport(),true);
		
	}

	
	@Test(priority=511)
	public void checkExporttoExcelFromEntryinRDMRNCubeReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		RDTN=new ReportDesignerPageNew(getDriver());
		
		Assert.assertEquals(RDTN.checkExporttoExcelFromEntryinRDMRNCubeReport(),true);
		
	}
	
	
	@Test(priority=512)
	public void checkExporttoPDFFromHomeinRDMRNCubeReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		RDTN=new ReportDesignerPageNew(getDriver());
		
		Assert.assertEquals(RDTN.checkExporttoPDFFromHomeinRDMRNCubeReport(),true);
		
	}
	
	@Test(priority=513)
	public void checkExporttoExcelFromHomeinRDMRNCubeReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		RDTN=new ReportDesignerPageNew(getDriver());
		
		Assert.assertEquals(RDTN.checkExporttoExcelFromHomeinRDMRNCubeReport(),true);
		
	}
	
	
	@Test(priority=514)
	public void checkWeekWiseReportforChangeLevel() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		RDTN=new ReportDesignerPageNew(getDriver());
		
		Assert.assertEquals(RDTN.checkWeekWiseReportforChangeLevel(),true);
	}

	@Test(priority=515)
	public void checkExporttoExcelinWeekWiseReportforLevel3() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		RDTN=new ReportDesignerPageNew(getDriver());
		
		Assert.assertEquals(RDTN.checkExporttoExcelinWeekWiseReportforLevel3(),true);
		
	}
	
	
	@Test(priority=516)
	public void checkPrintinWeekWiseReportforLevel2() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		RDTN=new ReportDesignerPageNew(getDriver());
		
		Assert.assertEquals(RDTN.checkPrintinWeekWiseReportforLevel2(),true);
		
	}
	
	
	
	@Test(priority=517)
	public void checkExporttoExcelinItemParameterReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		RDTN=new ReportDesignerPageNew(getDriver());
		
		Assert.assertEquals(RDTN.checkExporttoExcelinItemParameterReport(),true);
		
	}
	
	
	
	@Test(priority=518)
	public void checkExporttoPDFinItmParameterReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		RDTN=new ReportDesignerPageNew(getDriver());
		
		Assert.assertEquals(RDTN.checkExporttoPDFinItmParameterReport(),true);
		
	}
	
	
	
	
	@Test(priority=520)
	public void checkLogoutReportDesignerPage() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		RDTN=new ReportDesignerPageNew(getDriver());
		
		Assert.assertEquals(RDTN.checkLogoutReportDesignerPage(),true);
		
	}
}
