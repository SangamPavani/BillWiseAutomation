package com.focus.testcases;

import java.awt.AWTException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.text.ParseException;

import com.focus.Pages.BillWiseNewReferencePage;
import com.focus.base.BaseEngine;

public class BillWiseNewReferenceTest extends BaseEngine 
{


	BillWiseNewReferencePage bnrp;


	@Test(priority=72)
	public void checkLogin() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkLogin(), true);
	}

	
	
	@Test(priority=73)
	public void checkErasingAllData() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver()); 
		Assert.assertEquals(bnrp.checkErasingAllData(), true);
	}

	@Test(priority=74)
	public void checkSavingSalesINvoiceNewReference() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkSavingSalesINvoiceNewRef(), true);
	}


	@Test(priority=75)
	public void checkReceiptsVATForSalesNewReferencewithAdjustedAmount() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkReceiptsVATForSalesNewReferencewithAdjustedAmount(), true);
	}


	@Test(priority=76)
	public void checkSavedReceiptsVATVoucherWithSalesNewReference() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkSavedReceiptsVATVoucherWithSalesNewReference(), true);
	}



	@Test(priority=77)
	public void checkReceiptsVATForSalesNewReferencewithGridCheckbox() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkReceiptsVATForSalesNewReferencewithGridCheckbox(), true);
	}


	@Test(priority=78)
	public void checkSavedReceiptVatForSalesNewReferenceWithGridCheckbox() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkSavedReceiptVatForSalesNewReferenceWithGridCheckbox(), true);
	}


	@Test(priority=79)
	public void checkSalesReturnsVATForSalesNewReference() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkSalesReturnsVATForSalesNewReference(), true);
	}



	@Test(priority=80)
	public void checkSavingOpeningBalancesNewReference() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkSavingOpeningBalancesNewReference(), true);
	}


	@Test(priority=81)
	public void checkPreviousButtonInOpeningBalanceSavedVoucher() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkPreviousButtonInOpeningBalanceSavedVoucher(), true);
	}


	@Test(priority=82)
	public void checkSavingJournalEntriesVoucherAdjustingInOpeningBalancesNewRef() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkSavingJournalEntriesVoucherAdjustingInOpeningBalancesNewRef(), true);
	}


	@Test(priority=83)
	public void checkSavedJournalEntriesVoucher() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkSavedJournalEntriesVoucher(), true);
	}
	
	@Test(priority=84)
	public void checkSavingJVNewReferenceVoucher() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkSavingJVNewReferenceVoucher(), true);
	}


	@Test(priority=85)//
	public void checkSavedJVNewReferenceVoucher() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkSavedJVNewReferenceVoucher(), true);
	}
	

	@Test(priority=86)
	public void checkSavingPurchasesVoucherAdjustingInJVNewReference() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkSavingPurchasesVoucherAdjustingInJVNewReference(), true);
	}


	@Test(priority=87)
	public void checkSavingDebitNotesVoucher() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkSavingDebitNotesVoucher(), true);
	}

	@Test(priority=88)
	public void checkSavingPDPNewReferenceVoucher() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkSavingPDPNewReferenceVoucher(), true);
	}

	@Test(priority=89)
	public void checkSavedPDPNewReferenceVoucher() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkSavedPDPNewReferenceVoucher(), true);
	}

	@Test(priority=90)
	public void checkSavingPvVATAdjustingInPDPNewReference() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkSavingPvVATAdjustingInPDPNewReference(), true);
	}

	
	//PDR New Reference


	@Test(priority=91)
	public void checkSavingPDRNewReferenceVoucher() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkSavingPDRNewReferenceVoucher(), true);
	}


	@Test(priority=92)
	public void checkSavedPDRNewReferenceVoucher() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkSavedPDRNewReferenceVoucher(), true);
	}


	@Test(priority=93)
	public void checkSavingDebitNotesVoucherWithAdjustingInPDRNewReference() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkSavingDebitNotesVoucherWithAdjustingInPDRNewReference(), true);
	}

	@Test(priority=94)
	public void checkAmountAdjustingGridInBillReferenceScrren() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkAmountAdjustingGridInBillReferenceScrren(), true);
	}


	
	@Test(priority=95)
	public void checkSavingPettyCashNewReference() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkSavingPettyCashNewReference(), true);
	}


	@Test(priority=96)
	public void checkSavedPettyCashNewReferenceVoucher() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkSavedPettyCashNewReferenceVoucher(), true);

	}


	@Test(priority=97)
	public void checkSavingReceiptsVatWithAdjustingInPettyCashNewReference() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkSavingReceiptsVatWithAdjustingInPettyCashNewReference(), true);
	}


	@Test(priority=98)
	public void checkSavingNonJVNewReferenceVoucher() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkSavingNonJVNewReferenceVoucher(), true);

	}



	@Test(priority=99)//
	public void checkSavedNonJVNewReferenceVoucher() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkSavedNonJVNewReferenceVoucher(), true);

	}
	
	


	@Test(priority=100)
	public void checkSavingPaymentsVatAdjustingInNonJvNewReference() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkSavingPaymentsVatAdjustingInNonJvNewReference(), true);

	}

	@Test(priority=101)
	public void checkSavingReceiptsVatWithAdjsutingInNonJVNewReference() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkSavingReceiptsVatWithAdjsutingInNonJVNewReference(), true);

	}

	/* No need



	@Test(priority=102)//1
	public void checkFinanacialLedgerReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkFinanacialLedgerReport(), true);
	}

	@Test(priority=105)//2
	public void checkLedgerDetailsreport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkLedgerDetailsreport(), true);
	}

	@Test(priority=109)//3
	public void checkSubledgerReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkSubledgerReport(), true);
	}

	@Test(priority=113)//4
	public void checkBankBookReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkBankBookReport(), true);
	}

	@Test(priority=117)//5
	public void checkDayBookReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkDayBookReport(), true);
	}

	@Test(priority=118)//6
	public void checkBankReconciliationReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkBankReconciliationReport(), true);
	}

	@Test(priority=119)//
	public void checkBankReconciliationImportOptions() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkBankReconciliationImportOptions(), true);
	}

	@Test(priority=120)//7
	public void checkCustomerVendorReconciliationReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkCustomerVendorReconciliationReport(), true);
	}

	@Test(priority=121)//8
	public void checkBankReconciliationStatementReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkBankReconciliationStatementReport(), true);
	}

	@Test(priority=124)//9
	public void checkChequeDiscountingReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkChequeDiscountingReport(), true);
	}

	@Test(priority=125)//10
	public void checkSalesRegisterReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkSalesRegisterReport(), true);
	}

	@Test(priority=128)//11
	public void checkSalesReturnRegisterReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkSalesReturnRegisterReport(), true);
	}

	@Test(priority=131)//12
	public void checkSummarySalesBookReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkSummarySalesBookReport(), true);
	}

	@Test(priority=134)//13
	public void checkMonthlySalesBookReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkMonthlySalesBookReport(), true);
	}

	@Test(priority=135)//14
	public void checkTopCustomerListReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkTopCustomerListReport(), true);
	}

	@Test(priority=137)//15
	public void checkPurchaseRegisterReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkPurchaseRegisterReport(), true);
	}

	@Test(priority=140)//16
	public void checkPurchaseReturnRegisterReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkPurchaseReturnRegisterReport(), true);
	}

	@Test(priority=141)//17
	public void checkSummaryPurchaseBookReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkSummaryPurchaseBookReport(), true);
	}

	@Test(priority=144)//18
	public void checkVatPurchaseAccountReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkVatPurchaseAccountReport(), true);
	}

	@Test(priority=147)//19
	public void checkVatDetailedReportReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkVatDetailedReportReport(), true);
	}

	@Test(priority=149)//20
	public void checkSalesAccountReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkSalesAccountReport(), true);
	}

	@Test(priority=151)//21
	public void checkSalesByCustomerReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkSalesByCustomerReport(), true);
	}

	@Test(priority=152)//22
	public void checkVatSummaryReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkVatSummaryReport(), true);
	}

	@Test(priority=153)//23
	public void checkVatAuditFileReportOptions() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkVatAuditFileReportOptions(), true);
	}

	@Test(priority=154)//24
	public void checkVatReturnReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkVatReturnReport(), true);
	}

	@Test(priority=155)//25
	public void checkSalesAdvanceVatReportOptions() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkSalesAdvanceVatReportOptions(), true);
	}

	@Test(priority=157)//26
	public void checkOpeningBalanceRegisterReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkOpeningBalanceRegisterReport(), true);
	}

	@Test(priority=159)//27
	public void checkJournalEntriesRegisterOptions() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkJournalEntriesRegisterOptions(), true);
	}

	@Test(priority=163)//28
	public void checkDebitNoteRegisterReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkDebitNoteRegisterReport(), true);
	}

	@Test(priority=165)//29
	public void checkReceiptRegisterReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkReceiptRegisterReport(), true);
	}

	@Test(priority=167)//30
	public void checkPaymentRegisterOptions() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkPaymentRegisterOptions(), true);
	}

	@Test(priority=169)//31
	public void checkPdcReceiptsRegisterReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkPdcReceiptsRegisterReport(), true);
	}

	@Test(priority=171)//32
	public void checkPdcPaymentsRegisterReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkPdcPaymentsRegisterReport(), true);
	}

	@Test(priority=173)//33
	public void checkEntryJournalRegisterReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkEntryJournalRegisterReport(), true);
	}

	@Test(priority=175)//34
	public void checkEntryJournalDetailReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkEntryJournalDetailReport(), true);
	}

	@Test(priority=177)//35
	public void checksalesGroupedByCustomerReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checksalesGroupedByCustomerReport(), true);
	}

	@Test(priority=179)//36
	public void checkSalesGroupedByProductReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkSalesGroupedByProductReport(), true);
	}

	@Test(priority=181)//37
	public void checkSalesGroupedByDepartmentReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkSalesGroupedByDepartmentReport(), true);
	}

	@Test(priority=183)//38
	public void checkPurchasesGroupedByVendorOptions() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkPurchasesGroupedByVendorOptions(), true);
	}

	@Test(priority=185)//39
	public void checkPurchasesGroupedByProductReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkPurchasesGroupedByProductReport(), true);
	}


	@Test(priority=187)//40
	public void checkPurchasesGroupedByDepartmentReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkPurchasesGroupedByDepartmentReport(), true);
	}

	@Test(priority=213)
	public void checkTrailBalanceReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkTrailBalanceReport(), true);
	}

	@Test(priority=216)
	public void checkProfitAndLossReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkProfitAndLossReport(), true);
	}

	@Test(priority=219)
	public void checkTradingAccountOptions() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkTradingAccountOptions(), true);
	}

	@Test(priority=221)
	public void checkTradingAndProfitAndLossReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkTradingAndProfitAndLossReport(), true);
	}


	@Test(priority=224)
	public void checkBalanceSheetOptions() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkBalanceSheetOptions(), true);
	}

	@Test(priority=227)//Expected 
	public void checkFinalAccountsSchedulesReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkFinalAccountsSchedulesReport(), true);
	}

	@Test(priority=230)
	public void checkFundFlowReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkFundFlowReport(), true);
	}

	@Test(priority=242)
	public void checkBudgetPlanningReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkBudgetPlanningReport(), true);
	}

	@Test(priority=243)
	public void checkIncomeExpenseTrendReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkIncomeExpenseTrendReport(), true);
	}

	@Test(priority=245)
	public void checkReceavibleAndPayableReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkReceavibleAndPayableReport(), true);
	}

	// Receivable and Payable Analysis Reports Starts from Here

	@Test(priority=250)
	public void checkCustomerListingOfOutstandingBillsReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkCustomerListingOfOutstandingBillsReport(), true);
	}


	@Test(priority=253)
	public void checkCustomerStatementReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkCustomerStatementReport(), true);
	}

	@Test(priority=256)
	public void checkCustomerDueDateAnalysisReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkCustomerDueDateAnalysisReport(), true);
	}

	@Test(priority=259)
	public void checkCustomerAgeingDetailsAnalysisReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkCustomerAgeingDetailsAnalysisReport(), true);
	}

	@Test(priority=262)
	public void checkCustomerAgeingDetailsByDueDateReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkCustomerAgeingDetailsByDueDateReport(), true);
	}

	@Test(priority=265)
	public void checkCustomerOverDueAnalysisReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkCustomerOverDueAnalysisReport(), true);
	}

	@Test(priority=268)
	public void checkcustomerSummaryCustomerAgeingSummaryReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkcustomerSummaryCustomerAgeingSummaryReport(), true);
	}

	@Test(priority=271)
	public void checkcustomerSummaryAgeingByDueDateReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkcustomerSummaryAgeingByDueDateReport(), true);
	}

	@Test(priority=276)
	public void checkcustomerSummaryCustomerOverDueSummeryReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkcustomerSummaryCustomerOverDueSummeryReport(), true);
	}

	@Test(priority=279)
	public void checkcustomerSummaryCustomerBillWiseSummeryReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkcustomerSummaryCustomerBillWiseSummeryReport(), true);
	}


	//Vendor Details 

	@Test(priority=285)
	public void checkVendorListingOfOutstandingBillsReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkVendorListingOfOutstandingBillsReport(), true);
	}


	@Test(priority=290)
	public void checkvendorDetailsVendorStatementsReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, ParseException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkvendorDetailsVendorStatementsReport(), true);
	}

	@Test(priority=293)
	public void checkvendorDetailsVendorDueDateAnalysisReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, ParseException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkvendorDetailsVendorDueDateAnalysisReport(), true);
	}


	@Test(priority=296)
	public void checkVendorDetailsVendorAgeingDetailsReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, ParseException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkVendorDetailsVendorAgeingDetailsReport(), true);
	}

	@Test(priority=300)
	public void checkVendorDetailsVendorDetailsAgeingByDueDateReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, ParseException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkVendorDetailsVendorDetailsAgeingByDueDateReport(), true);
	}

	@Test(priority=303)
	public void checkVendorDetailsVendorOverdueAnalysisReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, ParseException, java.text.ParseException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkVendorDetailsVendorOverdueAnalysisReport(), true);
	}

	@Test(priority=306)
	public void checkVendorSummeryVendorAgeingSummaryReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkVendorSummeryVendorAgeingSummaryReport(), true);
	}

	@Test(priority=309)
	public void checkVendorSummeryVendorSummaryAgeingByDueDateReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkVendorSummeryVendorSummaryAgeingByDueDateReport(), true);
	}

	@Test(priority=312)
	public void checkVendorSummeryVendorOverdueSummaryReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkVendorSummeryVendorOverdueSummaryReport(), true);
	}

	@Test(priority=314)
	public void checkVendorSummeryVendorBillWiseSummaryReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkVendorSummeryVendorBillWiseSummaryReport(), true);
	}		   


	@Test(priority=401)
	public void checkLedgerReportSearchTextOption() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkLedgerReportSearchTextOption(), true);
	}	  

	@Test(priority=402)
	public void checkReportHomeSearchComboBox() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkReportHomeSearchComboBox(), true);
	}	  

	@Test(priority=403)
	public void checkCustomisationInStockLedgerReport() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkCustomisationInStockLedgerReport(), true);
	}	

	@Test(priority=420)
	public void checkAddingExtraFiledInsalesOrder() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkAddingExtraFiledInsalesOrder(), true);
	}	

	@Test(priority=421)//Issue 
	public void checkCreatedExtraFiledInLedgerReportCustomization() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkCreatedExtraFiledInLedgerReportCustomization(), true);
	}	


	@Test(priority=422)//Issue 
	public void checkCreatedExtraFiledInSUBLedgerDetailReportCustomization() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkCreatedExtraFiledInSUBLedgerDetailReportCustomization(), true);
	}	
	/*

  @Test(priority=423)//Issue 
  public void checkCreatedExtraFiledInCashBookReportCustomization() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
  {
  	bnrp=new BillWiseNewReferencePage(getDriver());
  	Assert.assertEquals(bnrp.checkCreatedExtraFiledInCashBookReportCustomization(), true);
  }	


  @Test(priority=425)//Issue 
  public void checkCreatedExtraFiledInBankBookReportCustomization() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
  {
  	bnrp=new BillWiseNewReferencePage(getDriver());
  	Assert.assertEquals(bnrp.checkCreatedExtraFiledInBankBookReportCustomization(), true);
  }	 

  @Test(priority=426)//Issue 
  public void checkCreatedExtraFiledInPettyCashReportCustomization() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
  {
  	bnrp=new BillWiseNewReferencePage(getDriver());
  	Assert.assertEquals(bnrp.checkCreatedExtraFiledInPettyCashReportCustomization(), true);
  }	


  @Test(priority=427)//Issue 
  public void checkCreatedExtraFiledInDAYBookReportCustomization() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
  {
  	bnrp=new BillWiseNewReferencePage(getDriver());
  	Assert.assertEquals(bnrp.checkCreatedExtraFiledInDAYBookReportCustomization(), true);
  }	



  @Test(priority=428)//Issue 
  public void checkCreatedExtraFiledInSalesRegisterReportCustomization() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
  {
  	bnrp=new BillWiseNewReferencePage(getDriver());
  	Assert.assertEquals(bnrp.checkCreatedExtraFiledInSalesRegisterReportCustomization(), true);
  }


  @Test(priority=429)//Issue 
  public void checkCreatedExtraFiledInSalesReturnRegisterReportCustomization() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
  {
  	bnrp=new BillWiseNewReferencePage(getDriver());
  	Assert.assertEquals(bnrp.checkCreatedExtraFiledInSalesReturnRegisterReportCustomization(), true);
  }


  @Test(priority=430)//Issue 
  public void checkCreatedExtraFiledInSummarySalesBookReportCustomization() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
  {
  	bnrp=new BillWiseNewReferencePage(getDriver());
  	Assert.assertEquals(bnrp.checkCreatedExtraFiledInSummarySalesBookReportCustomization(), true);
  }

  @Test(priority=431)//Issue 
  public void checkCreatedExtraFiledInMonthlySalesBookReportCustomization() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
  {
  	bnrp=new BillWiseNewReferencePage(getDriver());
  	Assert.assertEquals(bnrp.checkCreatedExtraFiledInMonthlySalesBookReportCustomization(), true);
  }

  @Test(priority=432)//Issue 
  public void checkCreatedExtraFiledInTopCustomersListReporttCustomization() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
  {
  	bnrp=new BillWiseNewReferencePage(getDriver());
  	Assert.assertEquals(bnrp.checkCreatedExtraFiledInTopCustomersListReporttCustomization(), true);
  }

  @Test(priority=433)//Issue 
  public void checkCreatedExtraFiledInSalesGroupedByCustomerReportCustomization() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
  {
  	bnrp=new BillWiseNewReferencePage(getDriver());
  	Assert.assertEquals(bnrp.checkCreatedExtraFiledInSalesGroupedByCustomerReportCustomization(), true);
  }

  @Test(priority=434)//Issue 
  public void checkCreatedExtraFiledInSalesGroupedByItemReportCustomization() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
  {
  	bnrp=new BillWiseNewReferencePage(getDriver());
  	Assert.assertEquals(bnrp.checkCreatedExtraFiledInSalesGroupedByItemReportCustomization(), true);
  }

  @Test(priority=435)//Issue 
  public void checkCreatedExtraFiledInSalesGroupedByDepartmentReportCustomization() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
  {
  	bnrp=new BillWiseNewReferencePage(getDriver());
  	Assert.assertEquals(bnrp.checkCreatedExtraFiledInSalesGroupedByDepartmentReportCustomization(), true);
  }


	 

	@Test(priority=600)
	public void checkSavingPaymentsVATWithTwoRowsINEntryPage() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkSavingPaymentsVATWithTwoRowsINEntryPage(), true);
	}


	@Test(priority=601)
	public void checkSavedPaymentsVATVoucher() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkSavedPaymentsVATVoucher(), true);
	}


	@Test(priority=602)
	public void checkSavingPurchaseVoucherVAT() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkSavingPurchaseVoucherVAT(), true);
	}


	@Test(priority=603)
	public void checkBillwiseScreenOfSavedVoucher() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		bnrp=new BillWiseNewReferencePage(getDriver());
		Assert.assertEquals(bnrp.checkBillwiseScreenOfSavedVoucher(), true);
	}


*/




















}