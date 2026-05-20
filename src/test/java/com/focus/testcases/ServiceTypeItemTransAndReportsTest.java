package com.focus.testcases;


import java.awt.AWTException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.focus.Pages.BillWisePage;
import com.focus.Pages.ServiceTypeItemTransactionsAndReportsPage;
import com.focus.base.BaseEngine;

public class ServiceTypeItemTransAndReportsTest extends BaseEngine
{
	static ServiceTypeItemTransactionsAndReportsPage STI;


	static BillWisePage bp;

	@Test(priority=50000)
	public static void checkLogin() throws NullPointerException, IOException, InterruptedException, EncryptedDocumentException, InvalidFormatException, AWTException 
	{
		bp=new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkLogin(), true);	
	}
	
	
	@Test(priority=53250)
	public static void checkEraseAllDATA() throws NullPointerException, IOException, InterruptedException, EncryptedDocumentException, InvalidFormatException, AWTException 
	{
		bp=new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkEraseAllDATA(), true);	
	}

	
	@Test(priority=53251)
	public static void checkCreatingServiceTypeItem() throws NullPointerException, IOException, InterruptedException, EncryptedDocumentException, InvalidFormatException, AWTException 
	{
		STI = new ServiceTypeItemTransactionsAndReportsPage(getDriver());
		Assert.assertEquals(STI.checkCreatingServiceTypeItem(),true);
	}

	
	@Test(priority=53252)
	public static void checkChangingServiceItemPropertiesToIgnoreBatchBinRMA() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		STI = new ServiceTypeItemTransactionsAndReportsPage(getDriver());
		Assert.assertEquals(STI.checkChangingServiceItemPropertiesToIgnoreBatchBinRMA(), true);
	}

	
	
	@Test(priority=53253)
	public static void checkSavingOpeningStockVoucher1WithService2370Item() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		STI = new ServiceTypeItemTransactionsAndReportsPage(getDriver());
		Assert.assertEquals(STI.checkSavingOpeningStockVoucher1WithService2370Item(), true);
	}

	
	
	@Test(priority=53254)
	public static void checkSavingDeliveryNotesVoucher1WithService2370Item() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		STI = new ServiceTypeItemTransactionsAndReportsPage(getDriver());
		Assert.assertEquals(STI.checkSavingDeliveryNotesVoucher1WithService2370Item(), true);
	}

	@Test(priority=53255)
	public static void checkSavingMaterialReceiptNoteVoucher1WithService2370Item() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		STI = new ServiceTypeItemTransactionsAndReportsPage(getDriver());
		Assert.assertEquals(STI.checkSavingMaterialReceiptNoteVoucher1WithService2370Item(), true);
	}

	@Test(priority=53256)
	public static void checkStockLedgerReportWithoutIncludeServiceTypeItem() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		STI = new ServiceTypeItemTransactionsAndReportsPage(getDriver());
		Assert.assertEquals(STI.checkStockLedgerReportWithoutIncludeServiceTypeItem(), true);
	}

	@Test(priority=53257)
	public static void checkStockLedgerReportWithIncludeServiceTypeItem() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		STI = new ServiceTypeItemTransactionsAndReportsPage(getDriver());
		Assert.assertEquals(STI.checkStockLedgerReportWithIncludeServiceTypeItem(), true);
	}

	@Test(priority=53258)
	public static void checkStockStatementReportWithoutIncludeServiceTypeItem() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		STI = new ServiceTypeItemTransactionsAndReportsPage(getDriver());
		Assert.assertEquals(STI.checkStockStatementReportWithoutIncludeServiceTypeItem(), true);
	}

	@Test(priority=53259)
	public static void checkStockStatementReportWithIncludeServiceTypeItem() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		STI = new ServiceTypeItemTransactionsAndReportsPage(getDriver());
		Assert.assertEquals(STI.checkStockStatementReportWithIncludeServiceTypeItem(), true);
	}

	@Test(priority=53260)
	public static void checkStockMovementReportWithoutIncludeServiceTypeItem() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		STI = new ServiceTypeItemTransactionsAndReportsPage(getDriver());
		Assert.assertEquals(STI.checkStockMovementReportWithoutIncludeServiceTypeItem(), true);
	}

	@Test(priority=53261)
	public static void checkStockMovementReportWithIncludeServiceTypeItem() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		STI = new ServiceTypeItemTransactionsAndReportsPage(getDriver());
		Assert.assertEquals(STI.checkStockMovementReportWithIncludeServiceTypeItem(), true);
	}

	@Test(priority=53262)
	public static void checkStockValuationReportWithoutIncludeServiceTypeItem() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		STI = new ServiceTypeItemTransactionsAndReportsPage(getDriver());
		Assert.assertEquals(STI.checkStockValuationReportWithoutIncludeServiceTypeItem(), true);
	}

	@Test(priority=53263)
	public static void checkStockValuationReportWithIncludeServiceTypeItem() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		STI = new ServiceTypeItemTransactionsAndReportsPage(getDriver());
		Assert.assertEquals(STI.checkStockValuationReportWithIncludeServiceTypeItem(), true);
	}

	
	@Test(priority=53264)
	public static void checkStockAgeingAnalysisReportWithoutIncludeServiceTypeItem() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		STI = new ServiceTypeItemTransactionsAndReportsPage(getDriver());
		Assert.assertEquals(STI.checkStockAgeingAnalysisReportWithoutIncludeServiceTypeItem(), true);
	}

	@Test(priority=53265)
	public static void checkStockAgeingAnalysisReportWithIncludeServiceTypeItem() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		STI = new ServiceTypeItemTransactionsAndReportsPage(getDriver());
		Assert.assertEquals(STI.checkStockAgeingAnalysisReportWithIncludeServiceTypeItem(), true);
	}

	@Test(priority=53266)
	public static void checkMultiLevelStockMovementReportWithoutIncludeServiceTypeItem() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		STI = new ServiceTypeItemTransactionsAndReportsPage(getDriver());
		Assert.assertEquals(STI.checkMultiLevelStockMovementReportWithoutIncludeServiceTypeItem(), true);
	}

	@Test(priority=53267)
	public static void checkMultiLevelStockMovementReportWithIncludeServiceTypeItem() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		STI = new ServiceTypeItemTransactionsAndReportsPage(getDriver());
		Assert.assertEquals(STI.checkMultiLevelStockMovementReportWithIncludeServiceTypeItem(), true);
	}













}
