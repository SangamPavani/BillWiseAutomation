package com.focus.testcases;

import java.awt.AWTException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.focus.Pages.ReportDesignerNew1Page;
import com.focus.Pages.ReportDesignerPageNew;
import com.focus.base.BaseEngine;

public class ReportDesignerNew1Test extends BaseEngine{
	
	ReportDesignerNew1Page rd;
	
	@Test(priority=500)
	public void checkLogin() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		rd=new ReportDesignerNew1Page(getDriver());
		Assert.assertEquals(rd.checkLogin(), true);
	}

	
	@Test(priority=501)
	public void checkRestoreOptionsCompanyAndLogin() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		rd=new ReportDesignerNew1Page(getDriver());
		rd.checkRestoreOptionsCompanyAndLogin();
	}
	
	
	
	@Test(priority=502)
	public void checkLogoutandLoginWithUser() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		rd=new ReportDesignerNew1Page(getDriver());
		Assert.assertEquals(rd.checkLogoutandLoginWithUser(), true);
	}
	
		
	@Test(priority=503)
	public void checkRDwarehousewiseDataReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		rd=new ReportDesignerNew1Page(getDriver());
		Assert.assertEquals(rd.checkRDwarehousewiseDataReport(), true);
	}
	
		
	@Test(priority=504)
	public void checkLogoutandLoginWithSU() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		rd=new ReportDesignerNew1Page(getDriver());
		Assert.assertEquals(rd.checkLogoutandLoginWithSU(), true);
	}
	
	
	
	@Test(priority=505)
	public void checkRDItemWiseDataReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		rd=new ReportDesignerNew1Page(getDriver());
		Assert.assertEquals(rd.checkRDItemWiseDataReport(), true);
	}
		
	
	
	@Test(priority=506)
	public void checkCompareValuesinRDItemWiseReporttoStockMovementReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		rd=new ReportDesignerNew1Page(getDriver());
		Assert.assertEquals(rd.checkCompareValuesinRDItemWiseReporttoStockMovementReport(), true);
	}
	
	
	
	@Test(priority=507)
	public void checkRDAccountWiseDataReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		rd=new ReportDesignerNew1Page(getDriver());
		Assert.assertEquals(rd.checkRDAccountWiseDataReport(), true);
	}
	
	
	
	
	
	@Test(priority=508)
	public void checkGraphinCubeReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		rd=new ReportDesignerNew1Page(getDriver());
		Assert.assertEquals(rd.checkGraphinCubeReport(), true);
	}
	
		
	@Test(priority=509)
	public void checkDetailRDReportforGraph() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		rd=new ReportDesignerNew1Page(getDriver());
		Assert.assertEquals(rd.checkDetailRDReportforGraph(), true);
	}

	
	
	
	@Test(priority=510)
	public void checkCrossreferenceAccountInfoinRDDetailReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		rd=new ReportDesignerNew1Page(getDriver());
		Assert.assertEquals(rd.checkCrossreferenceAccountInfoinRDDetailReport(), true);
	}
	
	
	
	
	@Test(priority=511)
	public void checkAccountQueryandAccountReportofCrossRefinRDDetailReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		rd=new ReportDesignerNew1Page(getDriver());
		Assert.assertEquals(rd.checkAccountQueryandAccountReportofCrossRefinRDDetailReport(), true);
	}
	
	
	
	@Test(priority=512)
	public void checkItemInfoItemQueryandItemReportofCrossRefinRDDetailReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		rd=new ReportDesignerNew1Page(getDriver());
		Assert.assertEquals(rd.checkItemInfoItemQueryandItemReportofCrossRefinRDDetailReport(), true);
	}
	
	
	
	
	@Test(priority=513)
	public void checkRDCreditColumnFilterReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		rd=new ReportDesignerNew1Page(getDriver());
		Assert.assertEquals(rd.checkRDCreditColumnFilterReport(), true);
	}
	
	
	@Test(priority=520)
	public void checkLogoutReportDesignerPage() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		rd=new ReportDesignerNew1Page(getDriver());
		
		Assert.assertEquals(rd.checkLogoutReportDesignerPage(),true);
		
	}
}
