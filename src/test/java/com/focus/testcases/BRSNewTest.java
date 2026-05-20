package com.focus.testcases;

import java.awt.AWTException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.focus.Pages.BRSNewPage;
import com.focus.base.BaseEngine;

public class BRSNewTest extends BaseEngine{
	
	BRSNewPage BNP;
	
	@Test(priority=1000)
	public void checkLogin() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkLogin(), true);
	}

	
	@Test(priority=1001)
	public void restoreCompany() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		BNP.restoreCompany();
	}

	
	  
	
	@Test(priority=1002)
	public void checkBankReconciliationReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkBankReconciliationReport(), true);
	}
	
	
	
	@Test(priority=1003)
	public void checkBankCustomizationforStandardFieldsinBRS() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkBankCustomizationforStandardFieldsinBRS(), true);
	}

	
	@Test(priority=1004)
	public void checkSortOrderOptionsinBRSReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkSortOrderOptionsinBRSReport(), true);
	}
	
	@Test(priority=1005)
	public void checkSavingReceiptsVoucherwithBlankChequeNumber() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkSavingReceiptsVoucherwithBlankChequeNumber(), true);
	}
	
		
	@Test(priority=1006)
	public void checkSavingPaymentsVoucherwithBlankChequeNumber() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkSavingPaymentsVoucherwithBlankChequeNumber(), true);
	}
	
	
	@Test(priority=1007)
	public void checkBankReconsillationReportforBlankChequesortingOrder() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkBankReconsillationReportforBlankChequesortingOrder(), true);
	}
	
	
	
	@Test(priority=1008)
	public void checkSavingReceiptsVoucherwithSameCleranceDateDiffChequeNum() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkSavingReceiptsVoucherwithSameCleranceDateDiffChequeNum(), true);
	}
	
	
	@Test(priority=1009)
	public void checkSavingPaymentsVoucherwithSameCleranceDateDiffChequeNum() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkSavingPaymentsVoucherwithSameCleranceDateDiffChequeNum(), true);
	}
	
		
	@Test(priority=1010)
	public void checkBankReconciliationReportforSameCleranceDateDiffChequeNum() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkBankReconciliationReportforSameCleranceDateDiffChequeNum(), true);
	}
	
	
	
	
	@Test(priority=1011)
	public void checkSavingReceiptsVoucherWithDiffClearanceDateSameChequeNum() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkSavingReceiptsVoucherWithDiffClearanceDateSameChequeNum(), true);
	}
	
		
	@Test(priority=1012)
	public void checkSavingPaymentsVoucherWithDiffCleranceDAteSameChequeNum() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkSavingPaymentsVoucherWithDiffCleranceDAteSameChequeNum(), true);
	}
	
	
	@Test(priority=1013)
	public void checkBankReconciliationReportforDiffCleranceDateSameChequeNum() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkBankReconciliationReportforDiffCleranceDateSameChequeNum(), true);
	}
	
	
	@Test(priority=1014)
	public void checkSavingReceiptsVoucherWithDiffClearanceDateDiffChequeNumSameDoc() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkSavingReceiptsVoucherWithDiffClearanceDateDiffChequeNumSameDoc(), true);
	}
		
	@Test(priority=1015)
	public void checkSavingPaymentsVoucherWithDiffCleranceDateDiffChequeNumSameDoc() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkSavingPaymentsVoucherWithDiffCleranceDateDiffChequeNumSameDoc(), true);
	}
	
		
	@Test(priority=1016)
	public void checkBankReconciliationReportforDiffCleranceDateDiffChequeNumSameDoc() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkBankReconciliationReportforDiffCleranceDateDiffChequeNumSameDoc(), true);
	}
	
	
	
	
	@Test(priority=1017)
	public void checkSavingReceiptsVocuherWithDiffChequeNumSameCleranceDateAndDoc() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkSavingReceiptsVocuherWithDiffChequeNumSameCleranceDateAndDoc(), true);
	}
	
	
	
	@Test(priority=1018)
	public void checkChequeNumColListinBankReconcilitionReportWithDiffChequeNumSameCleranceDateAndDoc() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkChequeNumColListinBankReconcilitionReport(), true);
	}
	
	
	
	
	@Test(priority=1019)
	public void checkSavingPaymentsVoucherWithDiffCleranceDateSameChequeNum() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkSavingPaymentsVoucherWithDiffCleranceDateSameChequeNum(), true);
	}
	
	
	
	
	@Test(priority=1020)
	public void checkCleranceDateColinBankReconcilationReportWithDiffCleranceDateSameChequeNumSmameDoc() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkCleranceDateColinBankReconcilationReportWithDiffCleranceDateSameChequeNumSmameDoc(), true);
	}
	
	
	@Test(priority=1021)
	public void checkSavingPaymentVoucherWithSameCleranceDateChequeNumDoc() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkSavingPaymentVoucherWithSameCleranceDateChequeNumDoc(), true);
	}
	
	
	@Test(priority=1022)
	public void checkDocumentNoColinBankReconciliationReportWithSameCleranceDateChequeNumDoc() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkDocumentNoColinBankReconciliationReportWithSameCleranceDateChequeNumDoc(), true);
	}
	
	@Test(priority=1023)//Based on Date, Date in ascending order
	public void checkSortOrderASDateandDocumentTypeinBankReconciliationReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkSortOrderASDateandDocumentTypeinBankReconciliationReport(), true);
	}
	
	
	@Test(priority=1024)//Based on cheque number, alphabet order in cheque number
	public void checkSortOrderASDocumentTypeAndChequeNuminBankReconciliationReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkSortOrderASDocumentTypeAndChequeNuminBankReconciliationReport(), true);
	}
	
	
	
	///Sortorder+Status+Cr/Dr
	
	@Test(priority=1025)
	public void checkSortOrderAsDocTypeCleranceDateChequeNumWithPendingDebitsinBankReconciliationReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkSortOrderAsDocTypeCleranceDateChequeNumWithPendingDebitsinBankReconciliationReport(), true);
	}
	
	
	
	@Test(priority=1026)
	public void checkSortOrderAsDocTypeCleranceDateChequeNumWithPendingCreditsinBankReconciliationReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkSortOrderAsDocTypeCleranceDateChequeNumWithPendingCreditsinBankReconciliationReport(), true);
	}
	
	
	
	
	@Test(priority=1027)
	public void checkSortOrderAsDocTypeCleranceDateChequeNumWithClearedDebitsinBankReconciliationReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkSortOrderAsDocTypeCleranceDateChequeNumWithClearedDebitsinBankReconciliationReport(), true);
	}
	
	
	
	
	
	@Test(priority=1028)
	public void checkSortOrderAsDocTypeCleranceDateChequeNumWithClearedCreditsinBankReconciliationReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkSortOrderAsDocTypeCleranceDateChequeNumWithClearedCreditsinBankReconciliationReport(), true);
	}
	
	///Date+Doc.No
	
	
	@Test(priority=1029)
	public void checkSortOrderAsDateDocNumWithPendingDebitsinBankReconciliationReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkSortOrderAsDateDocNumWithPendingDebitsinBankReconciliationReport(), true);
	}
	
	
	
	@Test(priority=1030)
	public void checkSortOrderAsDateDocNumWithPendingCreditsinBankReconciliationReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkSortOrderAsDateDocNumWithPendingCreditsinBankReconciliationReport(), true);
	}
	
	
	
	@Test(priority=1031)
	public void checkSortOrderAsDateDocNumWithClearedDebitsinBankReconciliationReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkSortOrderAsDateDocNumWithClearedDebitsinBankReconciliationReport(), true);
	}
	
	
	
	
	@Test(priority=1032)
	public void checkSortOrderAsDateDocNumWithClearedCreditsinBankReconciliationReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkSortOrderAsDateDocNumWithClearedCreditsinBankReconciliationReport(), true);
	}
	
	
	///Doctype+cheque No.
	
	
	@Test(priority=1033)
	public void checkSortOrderAsDocTypeChequeNumWithPendingDebitsinBankReconciliationReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkSortOrderAsDocTypeChequeNumWithPendingDebitsinBankReconciliationReport(), true);
	}
	
	
	
	@Test(priority=1034)
	public void checkSortOrderAsDocTypeChequeNumWithPendingCreditsinBankReconciliationReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkSortOrderAsDocTypeChequeNumWithPendingCreditsinBankReconciliationReport(), true);
	}
	
	
	
	@Test(priority=1035)
	public void checkSortOrderAsDocTypeChequeNumWithClearedDebitsinBankReconciliationReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkSortOrderAsDocTypeChequeNumWithClearedDebitsinBankReconciliationReport(), true);
	}
	
	
	
	
	@Test(priority=1036)
	public void checkSortOrderAsDocTypeChequeNumWithClearedCreditsinBankReconciliationReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkSortOrderAsDocTypeChequeNumWithClearedCreditsinBankReconciliationReport(), true);
	}
	
	
	
	
	
	@Test(priority=1037)
	public void checkSavingReceiptsVoucherWithUSD() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkSavingReceiptsVoucherWithUSD(), true);
	}
	
	
	
	@Test(priority=1038)
	public void checkTransactionCurrencyinBankReconcillationReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkTransactionCurrencyinBankReconcillationReport(), true);
	}
	
	
	
	@Test(priority=1039)
	public void checkSavingPaymentsVoucherWithUSD() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkSavingPaymentsVoucherWithUSD(), true);
	}
	
	
	
	@Test(priority=1040)
	public void checkTransactionCurrencyforCreditinBankReconcillationReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkTransactionCurrencyforCreditinBankReconcillationReport(), true);
	}
	
	
	
	
	@Test(priority=1041)
	public void checkClearingDebitAmountinBRS() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkClearingDebitAmountinBRS(), true);
	}
	
	
	@Test(priority=1042)
	public void checkClearingCreditAmountinBRS() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkClearingCreditAmountinBRS(), true);
	}
	
	
	
	
	@Test(priority=1043)
	public void checkBankReconciliationReportforDebitCreditClerance() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkBankReconciliationReportforDebitCreditClerance(), true);
	}
	

	@Test(priority=1044)
	public void checkAdvancedFilteronCleranceDateandDocumentDateinBRSReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkAdvancedFilteronCleranceDateandDocumentDateinBRSReport(), true);
	}
	
	
	
	@Test(priority=1045)
	public void checkAdvanceFilteronDebitAmountinBRSReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkAdvanceFilteronDebitAmountinBRSReport(), true);
	}
	
	@Test(priority=1046)
	public void checkAdvancedFilteronCreditAmountinBRSReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkAdvancedFilteronCreditAmountinBRSReport(), true);
	}

	@Test(priority=1047)
	public void checkAdvancedFilteronChequeNumberinBRSReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkAdvancedFilteronChequeNumberinBRSReport(), true);
	}
	
	
	
	@Test(priority=1048)
	public void checkAdvancedFilteronDepartmentinBRSReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkAdvancedFilteronDepartmentinBRSReport(), true);
	}
	
	
	//SortByColumn
	
	@Test(priority=1049)
	public void checkSortByColumnonBRSStatusinBRSReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkSortByColumnonBRSStatusinBRSReport(), true);
	}
	
	
	@Test(priority=1050)
	public void checkSortByColumnonChequeNuminBRSReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkSortByColumnonChequeNuminBRSReport(), true);
	}
	
	
	@Test(priority=1051)
	public void checkSortByColumnOnDocumentNum() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkSortByColumnOnDocumentNum(), true);
	}
	
	
	@Test(priority=1052)
	public void checkSortByColumnOnDebitAmount() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkSortByColumnOnDebitAmount(), true);
	}
	
	//Date Period
	
	
	
	@Test(priority=1053)
	public void checkDatePeriodasSelectDateinBRSReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkDatePeriodasSelectDateinBRSReport(), true);
	}
	
	
	@Test(priority=1054)
	public void checkDatePeriodasASonDateinBRSReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkDatePeriodasASonDateinBRSReport(), true);
	}
	
	
	
	@Test(priority=1055)
	public void checkDatePeriodasCurrentMonthinBRSReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkDatePeriodasCurrentMonthinBRSReport(), true);
	}
	
	
	@Test(priority=1056)
	public void checkDatePeriodasTodayinBRSReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkDatePeriodasTodayinBRSReport(), true);
	}
	
	
	@Test(priority=1057)
	public void checkDatePeriodasThisWeekinBRSReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkDatePeriodasThisWeekinBRSReport(), true);
	}
	
	
	@Test(priority=1058)
	public void checkDatePeriodasYearToDateinBRSReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkDatePeriodasYearToDateinBRSReport(), true);
	}
	

	//Save Option
	@Test(priority=1059)
	public void checkSavingPaymentsVoucher() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkSavingPaymentsVoucher(), true);
	}

	
	
	@Test(priority=1060)
	public void checkSavingReceiptsVoucher() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkSavingReceiptsVoucher(), true);
	}
		
	
	@Test(priority=1061)
	public void checkSaveOptionasSaveImmediatelyinBRSReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkSaveOptionasSaveImmediatelyinBRSReport(), true);
	}
	
	
	@Test(priority=1062)
	public void checkSaveOptionasSaveBatchmodeinBRSReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkSaveOptionasSaveBatchmodeinBRSReport(), true);
	}

	//Raise Vocuhers
	
	
	@Test(priority=1063)
	public void checkSavingRecepitsVATVoucherFromPendingBillsinBRSReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkSavingRecepitsVATVoucherFromPendingBillsinBRSReport(), true);
	}
	
	
	@Test(priority=1064)
	public void checkSavingPaymentsVATVoucherFromPendingBillsinBRSReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkSavingPaymentsVATVoucherFromPendingBillsinBRSReport(), true);
	}
	
	@Test(priority=1065)
	public void checkSavingRecepitsFIFOVoucherFromRaiseReceiptsinBRSReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkSavingRecepitsFIFOVoucherFromRaiseReceiptsinBRSReport(), true);
	}
	
	@Test(priority=1066)
	public void checkSavingPaymentsFIFOVoucherFromRaisePaymentsinBRSReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkSavingPaymentsFIFOVoucherFromRaisePaymentsinBRSReport(), true);
	}
	
	@Test(priority=1067)
	public void checkSavingReceiptsVoucherforConsolidationAmountinBRSReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkSavingReceiptsVoucherforConsolidationAmountinBRSReport(), true);
	}
	
	
	
	@Test(priority=1068)
	public void checkSavingPaymentsVoucherforConsolidationAmountinBRSReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkSavingPaymentsVoucherforConsolidationAmountinBRSReport(), true);
	}
	
	
	
	
	@Test(priority=1069)
	public void checkConsolidationAmountinBRSReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkConsolidationAmountinBRSReport(), true);
	}
	
	
	//Ledger
	
	
	@Test(priority=1071)
	public void checkBRSBalanceinLedgerReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkBRSBalanceinLedgerReport(), true);
	}
	
	
	//BankReconsilationStatement
	
	@Test(priority=1072)
	public void checkBankReconciliationStatementReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkBankReconciliationStatementReport(), true);
	}
	
	
	
	
	@Test(priority=1073)
	public void checkBankReconciliationStatementByEnablingShowconsolidatedamountsChkBox() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkBankReconciliationStatementByEnablingShowconsolidatedamountsChkBox(), true);
	}
	
	
	
	
	@Test(priority=1074)
	public void checkConsiderDateonTransationDateinBankReconciliationStatementReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkConsiderDateonTransationDateinBankReconciliationStatementReport(), true);
	}
	
	
	@Test(priority=1075)
	public void checkConsiderDateonCleranceDateinBankReconsilationStatementReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkConsiderDateonCleranceDateinBankReconsilationStatementReport(), true);
	}
	
		
	
	@Test(priority=1076)
	public void checkSavingReceiptsVATVocuherForUser() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkSavingReceiptsVATVocuherForUser(), true);
	}
	
	
	
	@Test(priority=1077)
	public void checkAddingBRSUserColumninBRSReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkAddingBRSUserColumninBRSReport(), true);
	}
	
	
	
	@Test(priority=1078)
	public void checkBRSReportAfterAddingBRSUser() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkBRSReportAfterAddingBRSUser(), true);
	}
	
	
	
	@Test(priority=1079)
	public void checkBRSReportforBRSUseratUserLevel() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkBRSReportforBRSUseratUserLevel(), true);
	}
	
		
	@Test(priority=1080)
	public void checkBRSUserColumnisAddedinReceiptsVATVoucher() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkBRSUserColumnisAddedinReceiptsVATVoucher(), true);
	}
	
	///BRS IMPORT
	
	
	
	
	@Test(priority=1090)
	public void checkSavingReceiptsVoucherforBRSImport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkSavingReceiptsVoucherforBRSImport(), true);
	}
	
	
	
	
	@Test(priority=1091)
	public void checkBankReconciliationImportReportforSameChequeNumDiffAmount() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkBankReconciliationImportReportforSameChequeNumDiffAmount(), true);
	}
	
	
	@Test(priority=1092)
	public void checkBankReconcillationReportAfterVoucherPostedinBRSImport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkBankReconcillationReportAfterVoucherPostedinBRSImport(), true);
	}
	
	
	
	
	@Test(priority=1093)
	public void checkSavingReceiptsVoucherwithSameChequeNumberSameAmount() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkSavingReceiptsVoucherwithSameChequeNumberSameAmount(), true);
	}
	
	
	
	@Test(priority=1094)
	public void checkBankReconciliationImportReportforSameChequeNumSameAmount() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkBankReconciliationImportReportforSameChequeNumSameAmount(), true);
	}
	
	@Test(priority=1095)
	public void checkSavingReceiptsVoucherwithMultipleRows() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkSavingReceiptsVoucherwithMultipleRows(), true);
	}

	
	@Test(priority=1096)
	public void checkBankReconciliationImportReportforMutipleRowsWithoutConsolidated() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkBankReconciliationImportReportforMutipleRowsWithoutConsolidated(), true);
	}
	

	@Test(priority=1097)
	public void checkSavingReceiptsVocuherforConsolidated() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkSavingReceiptsVocuherforConsolidated(), true);
	}
	
	@Test(priority=1098)
	public void checkBankReconciliationImportReportforMutipleRowsWithConsolidated() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkBankReconciliationImportReportforMutipleRowsWithConsolidated(), true);
	}
	
	
	
	@Test(priority=1099)//Not getting any values in the raised voucher except Bank name
	public void checkRaisingVoucherfromBankReconcilationImport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkRaisingVoucherfromBankReconcilationImport(), true);
	}
	
	
	
	@Test(priority=1099)
	public void checkSavingReceiptVoucherWithCurrencyUSD() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkSavingReceiptVoucherWithCurrencyUSD(), true);
	}
	
	
	@Test(priority=1100)
	public void checkBankReconcillationImportReportforBaseCurrency() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException
	{
		BNP=new BRSNewPage(getDriver());
		Assert.assertEquals(BNP.checkBankReconcillationImportReportforBaseCurrency(), true);
	}
	
}