package com.focus.testcases;

import java.awt.AWTException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.focus.Pages.BillWisePage;
import com.focus.base.BaseEngine;

public class BillWiseTest extends BaseEngine 
{

	static BillWisePage bp;

	@Test(priority = 20)
	public void checkLogin()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException 
	{
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkLogin(), true);
	}

	
	@Test(priority = 21, enabled = false) // [Option is Unknown for Raise Cheque Concept]
	public void checkSavingVoucherInSalesInvoiceVATWithEnableRaiseChkbox()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkSavingVoucherInSalesInvoiceVATWithEnableRaiseChkbox(), true);
	}
	@Test(priority = 22, enabled = false)
	public void checkPostingDetailsInSalesInvoiceVAT()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkPostingDetailsInSalesInvoiceVAT(), true);
	}

	@Test(priority = 23, enabled = false)
	public void checkLedgerReportWIthRaiseRecepit()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkLedgerReportWIthRaiseRecepit(), true);
	}

	@Test(priority = 24, enabled = false)
	public void checkSavingSalesinvoiceVATWithOutRaiseRecepit()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkSavingSalesinvoiceVATWithOutRaiseRecepit(), true);
	}

	@Test(priority = 25, enabled = false)
	public void checkLedgerReportWIthOUTRaiseRecepit()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkLedgerReportWIthOUTRaiseRecepit(), true);
	}
	

	@Test(priority = 45)
	public void restoreCompany()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		bp = new BillWisePage(getDriver());
		bp.restoreCompany();
	}
	
	

	@Test(priority = 51) // Document INFO Section Difference Time in Seconds
	public void checkSavingOpeningBALVoucher()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkSavingOpeningBALVoucher(), true);
	}


	@Test(priority = 52)
	public void checkSuspendingOptionInEntryPageInOpeningBalance()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkSuspendingOptionInEntryPageInOpeningBalance(), true);
	}

	@Test(priority = 53)
	public void checkSuspendingOptionFromHomePage()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkSuspendingOptionFromHomePage(), true);
	}
	
	
	@Test(priority = 54)
	public void checkSavingPurchaseVoucherVAT()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkSavingPurchaseVoucherVAT(), true);
	}

	@Test(priority = 55, enabled = false)
	public void checkPendingBillsInPaymentsAfterSavingOpeningBal()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkPendingBillsInPaymentsAfterSavingOpeningBal(), true);
	}

	@Test(priority = 56)// Voucher Number to be updated. 
	public void checkPendingBillsinPaymentsOFAndPurchaseVoucherVAT() throws InterruptedException, EncryptedDocumentException, InvalidFormatException,
			IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkPendingBillsinPaymentsOFAndPurchaseVoucherVAT(), true);
	}

	@Test(priority = 57)
	public void checkCopyDocumnetScreenOption()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkCopyDocumnetScreenOption(), true);
	}

	@Test(priority = 58)
	public void checkEntryPageAFterClickOnOkButtonInCopyDocument()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkEntryPageAFterclickOkButtonInCopyDocument(), true);
	}

	@Test(priority = 59) // error Message 
	public void checkSavingVoucherWithCopyDocument() throws InterruptedException,
	  EncryptedDocumentException, InvalidFormatException, IOException 
	{
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkSavingVoucherWithCopyDocument(), true);
	}

	
	@Test(priority = 60)
	public void checkCopyToClipBoardOptioninOpeningBalance()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkCopyToClipBoardOptioninOpeningBalance(), true);
	}

	@Test(priority = 61)
	public void checkSavingVoucherAfterPasteFromClipBoard()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkSavingVoucherAfterPasteFromClipBoard(), true);
	}

	@Test(priority = 62)
	public void checkDeleteOptionFromEntryPage()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkDeleteOptionFromEntryPage(), true);
	}
	
	
	@Test(priority = 70)
	public void checkSavingBinAndBatchBinItemInOpeningStock()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkSavingBinAndBatchBinItemInOpeningStock(), true);
	}

	
	@Test(priority = 71)
	public void checkReservingInSalesOrder()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkReservingInSalesOrder(), true);
	}

	@Test(priority = 72)
	public void checkSavedSalesOrderWithbinItem()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkSavedSalesOrderWithbinItem(), true);
	}

	@Test(priority = 73, enabled = false)
	public void checkEnteringDataIntoSecoundRowAndThirdRowInSalesOrder()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkEnteringDataIntoSecoundRowAndThirdRowInSalesOrder(), true);
	}

	@Test(priority = 75, enabled = false)
	public void checkSavedSalesOrderWithThreeRows()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkSavedSalesOrderWithThreeRows(), true);
	}

	@Test(priority = 76, enabled = false)
	public void checkLoadingLinksInSalesInvoiceVATAndSavingVoucher()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkLoadingLinksInSalesInvoiceVATAndSavingVoucher(), true);
	}

	@Test(priority = 77, enabled = false)
	public void checkReservedVouchreAFTERRelase()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkReservedVouchreAFTERRelase(), true);
	}
	 
	
	

	
	
	
	@Test(priority = 85)
	public void checkEraseAllDATA()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkEraseAllDATA(), true);
	}

	
	// Restored back Up Continue run OPening Balance

	@Test(priority = 91) // INFO Side Panel Time Varies
	public void checkSavingOpeningBalanceWithCreditAmount()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkSavingOpeningBalanceWithCreditAmount(), true);
	}

	@Test(priority = 92)
	public void checkSavingOpeningBalanceWithDebitAmount()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkSavingOpeningBalanceWithDebitAmount(), true);
	}

	@Test(priority = 93)
	public void checkPreviousButtonInOpeningBalanceSavedVoucher()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkPreviousButtonInOpeningBalanceSavedVoucher(), true);
	}

	
	// Payments

	@Test(priority = 102)
	public void checkPaymentsVATPendingBills()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkPaymentsVATPendingBills(), true);
	}

	
	
	@Test(priority = 103)
	public void checkSavingVoucherPaymentsVATWithVendorNewRefrence()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkSavingVoucherPaymentsVATWithVendorNewRefrence(), true);
	}

	@Test(priority = 104)
	public void checkSavingVoucherWithVendorSemiAdjustment()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkSavingVoucherWithVendorSemiAdjustment(), true);
	}

	@Test(priority = 105)
	public void checkSavingVoucherInPaymentsVATWithVendorFullAdjustment()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkSavingVoucherInPaymentsVATWithVendorFullAdjustment(), true);
	}

	@Test(priority = 106)
	public void checkSavingVoucherInPaymentsVATWithCustomerSemiAdjustment()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkSavingVoucherInPaymentsVATWithCustomerSemiAdjustment(), true);
	}

	// Purchase Voucher VAT

	// @Test(priority = 110)
	public void checkPendingBillsInPurchaseVoucherVAT()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkPendingBillsInPurchaseVoucherVAT(), true);
	}

	@Test(priority = 111)
	public void checkSavingVoucherInPurchaseVouchersVatWithNewRefrence()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkSavingVoucherInPurchaseVouchersVatWithNewRefrence(), true);
	}

	
	@Test(priority = 112)
	public void checkSavingVoucherWithSemiVendorTypeInPurchaseVoucherVAT()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkSavingVoucherWithSemiVendorTypeInPurchaseVoucherVAT(), true);
	}

	@Test(priority = 113)
	public void checkSavingVoucherWithFullAdjustmentVendorTypeInPurchaseVoucherVAT()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkSavingVoucherWithFullAdjustmentVendorTypeInPurchaseVoucherVAT(), true);
	}

	
	
	// Sales Invoice VAT

	@Test(priority = 120,enabled = false)
	public void checkPendingBillsInSalesINvoiceVAT()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkPendingBillsInSalesINvoiceVAT(), true);
	}

	
	@Test(priority = 121)
	public void checkSavingSalesINvoiceVoucherWithCustomrNewRefrence()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkSavingSalesINvoiceVoucherWithCustomrNewRefrence(), true);
	}

	@Test(priority = 122)
	public void checkSavingSalesINvoiceVoucherWithCustomerSemiAdjustment()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkSavingSalesINvoiceVoucherWithCustomerSemiAdjustment(), true);
	}

	
	@Test(priority = 123)
	public void checkSavingSalesINvoiceVoucherWithCustomrFullAdjustment()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkSavingSalesINvoiceVoucherWithCustomrFullAdjustment(), true);
	}

	// Recepits

	@Test(priority = 129,enabled = false)
	public void checkSuspendOptionAndDeleteOptionInRecepitsVAT()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkSuspendOptionAndDeleteOptionInRecepitsVAT(), true);
	}
	
	@Test(priority = 130)
	public void checkSavingRecepitsVATVocherWithCustomerNewReference()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkSavingRecepitsVATVocherWithCustomerNewReference(), true);
	}
	

	@Test(priority = 131)
	public void checkSavingRecepitsVATVocherWithCustomerSemiAdjustment()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkSavingRecepitsVATVocherWithCustomerSemiAdjustment(), true);
	}

	@Test(priority = 132)
	public void checkSavingRecepitsVATVocherWithCustomerFullAdjustment()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkSavingRecepitsVATVocherWithCustomerFullAdjustment(), true);
	}

	@Test(priority = 133)
	public void checkBillWsieScreenAfterTotalConsumeInRecepitsVAT()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkBillWsieScreenAfterTotalConsumeInRecepitsVAT(), true);
	}

	@Test(priority = 134)
	public void checkSavedVoucherInRecepitsVAT()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkSavedVoucherInRecepitsVAT(), true);
	}

	@Test(priority = 136)
	public void checkSalesRetunsBillWiseScreenWithCovertingOption()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkSalesRetunsBillWiseScreenWithCovertingOption(), true);
	}

	// @Test(priority = 140)
	public void checkSusupendedOptionAndDeleteOptionInJVVAT()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkSusupendedOptionAndDeleteOptionInJVVAT(), true);
	}
	
	
	@Test(priority = 141)
	public void checkSavingVoucherInJVVATViewWithVendorNewReference()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkSavingVoucherInJVVATViewWithVendorNewReference(), true);
	}

	
	@Test(priority = 142)
	public void checkSavingVoucherInJVVATViewWithVendorSemiAdjustment()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkSavingVoucherInJVVATViewWithVendorSemiAdjustment(), true);
	}

	@Test(priority = 143)
	public void checkSavingVoucherInJVVATViewWithVendorFullAdjustment()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkSavingVoucherInJVVATViewWithVendorFullAdjustment(), true);
	}

	@Test(priority = 144)
	public void checkSavingJVVATViewVoucherWithCustomersInOneVoucher()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkSavingJVVATViewVoucherWithCustomersInOneVoucher(), true);
	}

	
	// Saving JV VOucher and Deleting after Validating Reports
	
	@Test(priority = 145)
	public void A_checkSavingJVVOucher()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkSavingJVVOucher(), true);
	}

	@Test(priority = 145)
	public void B_checkCustomerStatmentWithCustomerA()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkCustomerStatmentWithCustomerA(), true);
	}
	

	@Test(priority = 145)
	public void C_checkLedgerReportWothSingleRowInJV()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkLedgerReportWothSingleRowInJV(), true);
	}

	
	@Test(priority = 145)
	public void D_checkDuplicateRowInJV()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkDuplicateRowInJV(), true);
	}

	// @Test(priority=145)// Issue Exists After Duplicate Row report generation
	// issue
	public void E_checkCustomerStatmentWithCustomerAAfterDuplicateRow()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkCustomerStatmentWithCustomerAAfterDuplicateRow(), true);
	}

	// UPTO this TC Option is Completed
	@Test(priority = 145)
	public void F_checkLedgerReportWothSingleRowInJVAfterDuplicateRow()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkLedgerReportWothSingleRowInJVAfterDuplicateRow(), true);
	}

	
	
	@Test(priority = 146)
	public void checkSavingPaymentsAfterSavingJVVATView()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException 
	{
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkSavingPaymentsAfterSavingJVVATView(), true);
	}

	@Test(priority = 147)
	public void checkSavingPaymentsAfterSavingJVVATViewWithVendorFull()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException 
	{
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkSavingPaymentsAfterSavingJVVATViewWithVendorFull(), true);
	}

	@Test(priority = 148)
	public void checkSavingPaymentsAfterSavingJVVATViewWithCustomerSemi()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkSavingPaymentsAfterSavingJVVATViewWithCustomerSemi(), true);
	}

	
	@Test(priority = 149)
	public void checkSuspendingAndDeletingVoucherInCreditVAT()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkSuspendingAndDeletingVoucherInCreditVAT(), true);
	}

	@Test(priority = 150)
	public void checkSavingVoucherInCreditVATWithCustomerFullAdjustment()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkSavingVoucherInCreditVATWithCustomerFullAdjustment(), true);
	}

	@Test(priority = 151)
	public void checkSavingVoucherInCreditVATWithCustomerSemiAdjustment()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());

		Assert.assertEquals(bp.checkSavingVoucherInCreditVATWithCustomerSemiAdjustment(), true);
	}

	@Test(priority = 152)
	public void checkSavingVoucherInCreditVATWithCustomerNewRefernce()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkSavingVoucherInCreditVATWithCustomerNewRefernce(), true);
	}

	@Test(priority = 153)
	public void checkSuspendedAndDeleteOptionInDebitNotes()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkSuspendedAndDeleteOptionInDebitNotes(), true);
	}
	

	@Test(priority = 160)
	public void checkSavingVoucherInDebitNotesVAT()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkSavingVoucherInDebitNotesVAT(), true);
	}

	@Test(priority = 161)
	public void checkSavingVoucherInDebitNotesVATWithVendorSemiAdjustment()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkSavingVoucherInDebitNotesVATWithVendorSemiAdjustment(), true);
	}

	@Test(priority = 162)
	public void checkSavingVoucherInDebitNotesVATWithCustomerNewReference()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkSavingVoucherInDebitNotesVATWithCustomerNewReference(), true);
	}

	@Test(priority = 163)
	public void checkSavingPaymentsAfterSavingCreditNotes()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkSavingPaymentsAfterSavingCreditNotes(), true);
	}
	
	
	
	// PDC VOUCHERS

	@Test(priority = 210)
	public void checkPDCOptionsUnderSettings()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkPDCOptionsUnderSettings(), true);
	}

	@Test(priority = 211)
	public void checkConvertedMaturePDCScreenWithNoPDCExists()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkConvertedMaturePDCScreenWithNoPDCExists(), true);
	}

	
	
	@Test(priority = 212)
	public void AcheckSavingVoucherToPostDatedReceiptsWithCheckNO1()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkSavingVoucherToPostDatedReceiptsWithCheckNO1(), true);
	}

	
	
	@Test(priority = 212)
	public void BcheckSavingPDRVATWithChequeNumber2()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkSavingPDRVATWithChequeNumber2(), true);
	}

	@Test(priority = 212)
	public void CcheckSavingVoucherToPostDatedReceiptsWithNewRefrence()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkSavingVoucherToPostDatedReceiptsWithNewRefrence(), true);
	}

	@Test(priority = 213)//Start Date filter is not working 
	public void checkSavedVouchersInPDCVoucherScreenWithOutPostOnDate()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkSavedVouchersInPDCVoucherScreenWithOutPostOnDate(), true);
	}

	@Test(priority = 214)
	public void checkConvertingVouchersInPDCVoucherScreenWithOutPostOnDate()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkConvertingVouchersInPDCVoucherScreenWithOutPostOnDate(), true);
	}

	
	@Test(priority = 215)
	public void checkConvertedPDCVoucherInReceipts()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkConvertedPDCVoucherInReceipts(), true);
	}
	
	

	@Test(priority = 216)
	public void checkSavingVoucherInPDRWithTwoRowsByDuplicateRow()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkSavingVoucherInPDRWithTwoRowsByDuplicateRow(), true);
	}

	@Test(priority = 217)
	public void checkConvertingPDRVoucherWithFutureMaturityDate()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkConvertingPDRVoucherWithFutureMaturityDate(), true);
	}

	
	
	@Test(priority = 218)
	public void checkConvertedPDCVoucherInReceiptsWithFutureMaturityDate()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkConvertedPDCVoucherInReceiptsWithFutureMaturityDate(), true);
	}

	@Test(priority = 219)
	public void checkConverted3rdVoucherInRecepicts()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkConverted3rdVoucherInRecepicts(), true);
	}
	

	// reverse Posting In PDR

	@Test(priority = 221)
	public void checkUpdateinPDCUnderSettingMenuForEnableReversePostingInRecepits()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException 
			{
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkUpdateinPDCUnderSettingMenuForEnableReversePostingInRecepits(), true);
	}

	
	
	@Test(priority = 222)
	public void checkSavingVoucherInPostDatedReceiptsWithCashANdBankAccountHDFC()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkSavingVoucherInPostDatedReceiptsWithCashANdBankAccountHDFC(), true);
	}

	@Test(priority = 223)
	public void checkReversingPoistingInPDCWithPDRVoucher()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkReversingPoistingInPDCWithPDRVoucher(), true);
	}

	
	@Test(priority = 224)
	public void checkConvertedPDCVoucherInReceiptsReversePosting()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkConvertedPDCVoucherInReceiptsReversePosting(), true);
	}
	
	

	// PDP VAT
	@Test(priority = 230)
	public void checkUpdateinPDCUnderSettingMenuForDisableEnableReversePostingInRecepits()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkUpdateinPDCUnderSettingMenuForDisableEnableReversePostingInRecepits(), true);
	}
	
	

	@Test(priority = 232) // Issue Two To be displayed
	public void checkSavingVoucherINPDPVAT()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException 
	{
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkSavingVoucherINPDPVAT(), true);
	}

	 @Test(priority=233)
	//@Test(dependsOnMethods = { "checkSavingVoucherINPDPVAT" })
	public void DepedentTC_checkSavedVouchersInPDPVoucherScreenWithOutPostOnDate()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkSavedVouchersInPDPVoucherScreenWithOutPostOnDate(), true);
	}

	@Test(priority = 234)
	public void checkAddExtraFieldPDCInHeaderFieldOFPayments()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkAddExtraFieldPDCInHeaderFieldOFPayments(), true);
	}

	 @Test(priority=235)
//	@Test(dependsOnMethods = { "DepedentTC_checkSavedVouchersInPDPVoucherScreenWithOutPostOnDate" })
	public void depTC_checkCovertingVoucherInPDCAfetrCreatingExtraFieldInPayments()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkCovertingVoucherInPDCAfetrCreatingExtraField(), true);
	}

	 @Test(priority=236)
	//@Test(dependsOnMethods = { "depTC_checkCovertingVoucherInPDCAfetrCreatingExtraFieldInPayments" })
	public void dep_checkConvertedVoucherINPayments()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkConvertedVoucherINPayments(), true);
	}

	@Test(priority = 236)
	public void AcheckSavingPDPVATWithCutomerNewRef()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.AcheckSavingPDPVATWithCutomerNewRef(), true);
	}

	@Test(priority = 236)
	public void BcheckConvertingPDPInPDC()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.BcheckConvertingPDPInPDC(), true);
	}
	

	@Test(priority = 236) 
	public void CcheckConvertedVoucherInPaymentsWithADjustAndNewRefVouchers()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.CcheckConvertedVoucherInPaymentsWithADjustAndNewRefVouchers(), true);
	}
	
	

	@Test(priority = 237)
	public void AcheckUpdatingReservePosting()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.AcheckUpdatingReservePosting(), true);
	}

	
	@Test(priority = 237)
	public void BcheckSavingVoucherInPDPVatWithHDFCAccount()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.BcheckSavingVoucherInPDPVatWithHDFCAccount(), true);
	}

	@Test(priority = 237)
	public void CcheckAddingRow2InPDPVAT()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.CcheckAddingRow2InPDPVAT(), true);
	}

	@Test(priority = 238) // Amount getting round OFF
	public void checkReversingPositingInPDCWithPDPVoucher()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkReversingPositingInPDCWithPDPVoucher(), true);
	}

	@Test(priority = 239) // Amount getting round OFF
	public void checkPostedVoucherInPaymentsWithReversePosting()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkPostedVoucherInPaymentsWithReversePosting(), true);
	}

	
	
	@Test(priority = 14000)
	public void checkITEMWithF5Key()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkITEMWithF5Key(), true);
	}

	@Test(priority = 14001)
	public void checkLoadingFieldsIntoPopOnClickOnRefreshBtn()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkLoadingFieldsIntoPopOnclickRefreshBtn(), true);
	}

	@Test(priority = 14002)
	public void checkItemValuesintoVoucherLevelOnClickOnOkBtnInSearchPopUp()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkItemValuesintoVoucherLevelOnclickOkBtnInSearchPopUp(), true);
	}

	@Test(priority = 14003)
	public void checkAccountTxtWithClickOnF5()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkAccountTxtWithclickF5(), true);
	}

	@Test(priority = 14004)
	public void checkLoadingAccountValuesFromSearchPopUp()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkLoadingAccountValuesFromSearchPopUp(), true);
	}

	@Test(priority = 14005) // Issue, Should Display Hyderabad.in RMA Text Filed while we paste

	public void checkF5KeyWithRMAPopUpInOpeningStocks()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkF5KeyWithRMAPopUpInOpeningStocks(), true);
	}
	
	

	@Test(priority = 14006)
	public void checkCopyAndPasteWithControlOptionInLedgerReport()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkCopyAndPasteWithControlOptionInLedgerReport(), true);
	}

	@Test(priority = 14007)
	public void checkEnterF5keyInLedgerReport()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkEnterF5keyInLedgerReport(), true);
	}

	@Test(priority = 14007, enabled = false)
	public void checkF5KeyInFilterPopUpScreen()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkF5KeyInFilterPopUpScreen(), true);
	}

	
	@Test(priority = 14020)
	public void checkSavingPaymentsVATWithTwoRowsINEntryPage()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkSavingPaymentsVATWithTwoRowsINEntryPage(), true);
	}

	@Test(priority = 14021)
	public void checkSavedPaymentsVATVoucher()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkSavedPaymentsVATVoucher(), true);
	}

	@Test(priority = 14022)
	public void checkSavingPurchaseVoucherVATForBillwiseValidation()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkSavingPurchaseVoucherVATForBillwiseValidation(), true);
	}

	@Test(priority = 14023)
	public void checkBillwiseScreenOfSavedVoucher()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkBillwiseScreenOfSavedVoucher(), true);
	}

	
	// Added new

	// PDC Scenarios Added With Filter Conditions Added

	@Test(priority = 15023)
	public void checkSavingVouchersInPDRVAT()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkSavingVouchersInPDRVAT(), true);
	}

	@Test(priority = 15024)
	public void checkSavingVoucherWithAgainCopyToClipboardOption()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkSavingVoucherWithAgainCopyToClipboardOption(), true);
	}

	@Test(priority = 15025)
	public void checkConvertedPDCScreenApplyToBankToBank()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkConvertedPDCScreenApplyToBankToBank(), true);
	}

	@Test(priority = 15026)
	public void checkApplyingToHDFCInPDCScreen()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkApplyingToHDFCInPDCScreen(), true);
	}

	@Test(priority = 15027)
	public void checkConvertedVoucherInRecepitsWithAppliedScnerio()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkConvertedVoucherInRecepitsWithAppliedScnerio(), true);
	}

	@Test(priority = 15028)
	public void checkFilterAppledOnCustomerInPDCScreen()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkFilterAppledOnCustomerInPDCScreen(), true);
	}
	
	

	@Test(priority = 15029)
	public void checkEraseAllAndLogoutFromBillwiseTest()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkEraseAllAndLogoutFromBillwiseTest(), true);
	}

	@Test(priority = 15035)
	public void checkAutoCheckForMaturedPDCAndScheduledPostingsWhileInitializing()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException 
	{
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkAutoCheckForMaturedPDCAndScheduledPostingsWhileInitializing(), true);
	}

	
	@Test(priority = 15036)
	public void checkSavingPDRVATAfterOptionEnabled()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkSavingPDRVATAfterOptionEnabled(), true);
	}

	
	@Test(priority = 15037)
	public void checkValidationAutoCheckOption()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkValidationAutoCheckOption(), true);
	}

	
	@Test(priority = 15038)
	public void checkConvertedVoucherInRecepits()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkConvertedVoucherInRecepits(), true);
	}

	@Test(priority = 15039)
	public void checkEnableDisplayInLedgersAndTrialBalance()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkEnableDisplayInLedgersAndTrialBalance(), true);
	}

	@Test(priority = 15040)
	public void checkOptionEnabledInLedger()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkOptionEnabledInLedger(), true);
	}

	@Test(priority = 15041)
	public void checkOptionInTrailBalance()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkOptionInTrailBalance(), true);
	}

	@Test(priority = 15042)
	public void checkEnableDisplayBasedOnMaturityDateInLedgers()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkEnableDisplayBasedOnMaturityDateInLedgers(), true);
	}

	@Test(priority = 15043)
	public void checkSavingRecepitsVAT()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkSavingRecepitsVAT(), true);
	}

	@Test(priority = 15044)
	public void checkMatureDateOptionInLedger()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkMatureDateOptionInLedger(), true);
	}

	@Test(priority = 15045)
	public void checkMatureDateWithOutOptionInLedger()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkMatureDateWithOutOptionInLedger(), true);
	}

	// Reservation by Quantites

	@Test(priority = 15065)
	public void checkEnableReservationByQuantitesOptionInInventoryAndITEM()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkEnableReservationByQuantitesOptionInInventoryAndITEM(), true);
	}

	@Test(priority = 15066)
	public void checkSavingVoucherInMRNWithBRCogs()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkSavingVoucherInMRNWithBRCogs(), true);
	}

	@Test(priority = 15067)
	public void checkValidationInSalesOrder()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException {
		bp = new BillWisePage(getDriver());
		Assert.assertEquals(bp.checkValidationInSalesOrder(), true);
	}
}
