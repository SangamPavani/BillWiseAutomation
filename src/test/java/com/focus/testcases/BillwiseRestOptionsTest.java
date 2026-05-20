package com.focus.testcases;

import java.awt.AWTException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.focus.Pages.BillWisePage;
import com.focus.Pages.BillwiseRestOptions;
import com.focus.base.BaseEngine;

public class BillwiseRestOptionsTest extends BaseEngine 
{

	static BillwiseRestOptions BROP;
	static BillWisePage bp;

	
	@Test(priority = 1)
	public void AcheckLoginTOPaymentsTerms()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkLogin(), true);
	}
	
	
	
	@Test(priority = 1)
	public void BrestoreCompanyForPaymentTerms()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());

		BROP.restoreCompanyForRestOptions();
	}
	
	
	@Test(priority = 1)
	public void CcheckSavedRecepictsVATVoucherWithAdjustments()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavedRecepictsVATVoucherWithAdjustments(), true);
	}
	

	@Test(priority = 1)
	public void DcheckSavingSalesInvoiceVATAndSavingRecepcitsVAT()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingSalesInvoiceVATAndSavingRecepcitsVAT(), true);
	}

	@Test(priority = 1)
	public void EcheckSavingRecepictsVATWithNewReference()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingRecepictsVATWithNewReference(), true);
	}

	@Test(priority = 1)
	public void FcheckSavingReceipctsVATWithRemainingAmt()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingReceipctsVATWithRemainingAmt(), true);
	}

	
	@Test(priority = 1)
	public void GcheckSuspendingAndAddingSecoundRowIn1stVoucher()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSuspendingAndAddingSecoundRowIn1stVoucher(), true);
	}

	@Test(priority = 1)
	public void HcheckBillReferenceAfterConsumedIn1stVoucher()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkBillReferenceAfterConsumedIn1stVoucher(), true);
	}

	
	@Test(priority = 1)
	public void XcheckSavingPurchaseVoucherVATWithFutureDateAndVaryDueDate()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingPurchaseVoucherVATWithFutureDateAndVaryDueDate(), true);
	}

	@Test(priority = 1) // Date And due date Are Not UPdated ---Issue reported.
	public void YcheckCOpyTOCLipboardOptionsWithDueDateDifferent()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkCOpyTOCLipboardOptionsWithDueDateDifferent(), true);
	}

	@Test(priority = 2, enabled = false)
	public void BrestoreCompanyForPaymentTerms2()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());

		BROP.restoreCompanyForRestOptions();
	}

	
	
	@Test(priority = 2) // ITC
	public void CcheckSavinOpeningStockByInserRow()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavinOpeningStockByInserRow(), true);
	}

	@Test(priority = 2) // ITC

	public void DcheckSavingAfterInserRowDoneInVoucher2()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingAfterInserRowDoneInVoucher2(), true);
	}

	
	@Test(priority = 2) // ITC
	public void EcheckSavingPaymentsVATVoucherANDRaiseCheque()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException 
	{
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingPaymentsVATVoucherANDRaiseCheque(), true);
	}

	@Test(priority = 3) // ITC
	public void AcheckReverseChequeRecepitsVATVoucher()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkReverseChequeRecepitsVATVoucher(), true);
	}

	@Test(priority = 3) // ITC
	public void BcheckVoucherReveretedToPaymentsVAT()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkVoucherReveretedToPaymentsVAT(), true);
	}

	@Test(priority = 3) // ITC
	public void CcheckValidationInSalesInvoiceVATWithCustomerA()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkValidationInSalesInvoiceVATWithCustomerA(), true);
	}

	@Test(priority = 3) // ITC
	public void DcheckSalesInvoiceVATWithCustomerB()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSalesInvoiceVATWithCustomerB(), true);
	}
	
	
	
	// Payment Terms Starts From here ***********************************************************************************

	@Test(priority = 6)
	public void checkSavingAccountMaster()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingAccountMaster(), true);
	}

	@Test(priority = 7)
	public void checkPaymentsTermsScreen()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkPaymentsTermsScreen(), true);
	}

	@Test(priority = 8)
	public void checkSavingPTWIthoutInput()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingPTWIthoutInput(), true);
	}

	@Test(priority = 9)
	public void checkEntringInputInPaymentTerms()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkEntringInputInPaymentTerms(), true);
	}

	@Test(priority = 10)
	public void checkDeleteRowInPaymentTermsS()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkDeleteRowInPaymentTerms(), true);
	}

	@Test(priority = 11)
	public void checkResavingPaymentTermsAfterDeletion()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkResavingPaymentTermsAfterDeletion(), true);
	}

	@Test(priority = 12)
	public void checkSavedPayTermsAndEditing()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavedPayTermsAndEditing(), true);
	}

	@Test(priority = 13)
	public void checkAssgingAccTOPaymentTerms()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkAssgingAccTOPaymentTerms(), true);
	}
	
	

	@Test(priority = 14)
	public void checkSavingVoucherWithPaymentTermsInCashSales()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingVoucherWithPaymentTermsInCashSales(), true);
	}

	@Test(priority = 15) // Expected Fail as to get discount column in entry page Body display wrong 
	public void checkSavingRecepitsVoucher()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingRecepitsVoucher(), true);
	}
	

	@Test(priority = 16)
	public void checksavedRecepictsVAT()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checksavedRecepictsVAT(), true);
	}

	
	@Test(priority = 17)
	public void checkWith5DaysDueDateInRecepitsVAT()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkWith5DaysDueDateInRecepitsVAT(), true);
	}

	@Test(priority = 18)
	public void checkWith10DaysDueDateInRecepitsVAT()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkWith10DaysDueDateInRecepitsVAT(), true);
	}

	@Test(priority = 19)
	public void checkWith20DaysDueDateInRecepitsVAT()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkWith20DaysDueDateInRecepitsVAT(), true);
	}

	@Test(priority = 21)
	public void checkSavingPaymentsTerms2()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingPaymentsTerms2(), true);
	}

	@Test(priority = 22)
	public void checkAssginingPaymntTermsToVendorNewRefernceAccount()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkAssginingPaymntTermsToVendorNewRefernceAccount(), true);
	}

	@Test(priority = 23)
	public void checkEnablePaymentTermsOptionInPVVAT()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkEnablePaymentTermsOptionInPVVAT(), true);
	}

	@Test(priority = 24)
	public void checkSavingPVVATWithPaymentTerms()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingPVVATWithPaymentTerms(), true);
	}

	@Test(priority = 25)
	public void checkSavingPaymentsVATVou1WithPaymentTerms()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingPaymentsVATVou1WithPaymentTerms(), true);
	}

	
	@Test(priority = 26)
	public void checkSavedPaymentsVAT()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavedPaymentsVAT(), true);
	}

	@Test(priority = 27)
	public void AcheckWith10DaysDueDateInPAYMENTSVAT()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkWith10DaysDueDateInPAYMENTSVAT(), true);
	}

	
	@Test(priority = 27)
	public void BcheckWith5DaysDueDateInPAYMENTSVAT()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkWith5DaysDueDateInPAYMENTSVAT(), true);
	}

	
	
	@Test(priority = 28)
	public void checkSavingCashSalesVoucher()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingCashSalesVoucher(), true);
	}

	@Test(priority = 29)
	public void checkAddingExtraFiledPDCNOInRecepitsFIFO()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkAddingExtraFiledPDCNOInRecepitsFIFO(), true);
	}

	@Test(priority = 30)
	public void checkSavingVoucherPostDatedReceipts()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingVoucherPostDatedReceipts(), true);
	}

	@Test(priority = 31)
	public void checkConvertingVoucherINPDCScreen()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkConvertingVoucherINPDCScreen(), true);
	}

	@Test(priority = 32)
	public void checkCovertedRecepitsFIFOVoucher()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkCovertedRecepitsFIFOVoucher(), true);
	}
	
	
	@Test(priority = 35)
	public void checkSavingPurchaseVoucherWithPastDateAndEnablepaymentTerms()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingPurchaseVoucherWithPastDateAndEnablepaymentTerms(), true);
	}

	@Test(priority = 36)
	public void checkAddingExtraFiledPDCNOInPaymentsFIFO()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkAddingExtraFiledPDCNOInPaymentsFIFO(), true);
	}

	@Test(priority = 37)
	public void checkSavingVoucherPostDatedPayments()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingVoucherPostDatedPayments(), true);
	}

	@Test(priority = 38)
	public void checkConvertingVoucherPDPINPDCScreen()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkConvertingVoucherPDPINPDCScreen(), true);
	}

	@Test(priority = 39)
	public void checkCovertedPaymentsFIFOVoucher()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkCovertedPaymentsFIFOVoucher(), true);
	}

	@Test(priority = 40)
	public void checkPostingDetailsInPaymentsFIFO()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkPostingDetailsInPaymentsFIFO(), true);
	}

	
	
	
	// Newly Added
	
	@Test(priority = 41)
	public void checkEnableOptionPickcreditDaysFromPaymentTerms()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkEnableOptionPickcreditDaysFromPaymentTerms(), true);
	}

	@Test(priority = 42)
	public void checkEnablePaymentTermsiNSalesInvoiceVAT()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkEnablePaymentTermsiNSalesInvoiceVAT(), true);
	}

	@Test(priority = 43)
	public void checkPaymentTermsValidationAtVoucherLevel()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkPaymentTermsValidationAtVoucherLevel(), true);
	}

	
	@Test(priority = 44)
	public void checkEnableChangePaymentTermsInsalesInvoiceVAT()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkEnableChangePaymentTermsInsalesInvoiceVAT(), true);
	}

	@Test(priority = 45)
	public void checkChangePaymentTermOptionInSalesInvoiceVAT()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkChangePaymentTermOptionInSalesInvoiceVAT(), true);
	}

	@Test(priority = 46)
	public void checkSavingSalesInvoiceVATWithEnableOption()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingSalesInvoiceVATWithEnableOption(), true);
	}

	
	@Test(priority = 47)
	public void checkEnableOptionPickCreditDaysFromSalesAccount()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkEnableOptionPickCreditDaysFromSalesAccount(), true);
	}

	
	@Test(priority = 48)
	public void checkInputCreditDaysOFSalesAccInAccMaster()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkInputCreditDaysOFSalesAccInAccMaster(), true);
	}

	@Test(priority = 49)
	public void checkSavingSalesinvoiceWithOptionCreditDays()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingSalesinvoiceWithOptionCreditDays(), true);
	}
	
	// Payment term Ends here 
	
	
	
	// new Scanrios To be added here 
	

	@Test(priority = 51)
	public void checkEnablePaymentTermInSalesInvoiceVoucher()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkEnablePaymentTermInSalesInvoiceVoucher(), true);
	}
	
	
	
	@Test(priority = 52)
	public void checkSavingSalesInvoiceVoucherWithPaymentTerms()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingSalesInvoiceVoucherWithPaymentTerms(), true);
	}
	
	
	@Test(priority = 53)
	public void checkSavingSalesInvoiceVoucher3WithPaymentTerms()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingSalesInvoiceVoucher3WithPaymentTerms(), true);
	}
	
	
	@Test(priority = 54)
	public void checkSavingLedgerReportWithPaymentTermsFiledInReport()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingLedgerReportWithPaymentTermsFiledInReport(), true);
	}
	
	
	
	@Test(priority = 55)
	public void checkSavingPaymentTermsAndValidatingReportInSalesRegister()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingPaymentTermsAndValidatingReportInSalesRegister(), true);
	}
	
	
	@Test(priority = 56)
	public void checkSavingSalesInvoiceVoucher()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingSalesInvoiceVoucher(), true);
	}
	
	
	@Test(priority = 57)
	public void checkSavingSales2WithSameCustomerAndDep()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingSales2WithSameCustomerAndDep(), true);
	}
	

	@Test(priority = 58)
	public void checkEnablePostExchangeRateInReceictsVAT()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkEnablePostExchangeRateInReceictsVAT(), true);
	}
	

	@Test(priority = 59)//check it
	public void checkConvertingPendingBillsWithSameARAP()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkConvertingPendingBillsWithSameARAP(), true);
	}
	
	
	@Test(priority = 60)
	public void checkSavedVoucherWithConvertOptionsSameDepAndAcc()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavedVoucherWithConvertOptionsSameDepAndAcc(), true);
	}
	
	
	
	@Test(priority = 61)
	public void checkChangingDifferentAccountAndSameDep()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkChangingDifferentAccountAndSameDep(), true);
	}
	
	
	
	@Test(priority = 62)
	public void checkConvertingDifferentAccountAndSameDep()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkConvertingDifferentAccountAndSameDep(), true);
	}
	
	
	@Test(priority = 63)
	public void checkChangingSameAccountAndDiffDep()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkChangingSameAccountAndDiffDep(), true);
	}
	
	

	@Test(priority = 64)
	public void checkConvertingSameAccountAndDiffDep()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkConvertingSameAccountAndDiffDep(), true);
	}
	
	@Test(priority = 65)
	public void checkSavingSalesInvoiceWithBackdatedandChangeInCurrencyvalue()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingSalesInvoiceWithBackdatedandChangeInCurrencyvalue(), true);
	}
	
	
	@Test(priority = 66)
	public void checkConveringFromPendingBillsInrecepictsVAT()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkConveringFromPendingBillsInrecepictsVAT(), true);
	}
	
	
	@Test(priority = 67)
	public void checkSavedVoucherInReceipctsVAT()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavedVoucherInReceipctsVAT(), true);
	}
	
	
	
	@Test(priority = 69)
	public void checkEnableExchageRateDifferenceInPayments()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkEnableExchageRateDifferenceInPayments(), true);
	}
	
	
	
	@Test(priority = 70)
	public void checkSavingPurchaseVoucherWithLessExchageRate()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingPurchaseVoucherWithLessExchageRate(), true);
	}
	

	@Test(priority = 71)
	public void checkSavingPaymentsVOucherAjustingPurchaseVoucherWithCurrecntdate()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingPaymentsVOucherAjustingPurchaseVoucherWithCurrecntdate(), true);
	}
		
	
	
	@Test(priority = 72)
	public void checkSavedAndPostingDetaialsOInPayments()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavedAndPostingDetaialsOInPayments(), true);
	}
	
	
	
	
	
	@Test(priority = 73)
	public void checkSavingExchageRateWithINR()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingExchageRateWithINR(), true);
	}
	
	
	@Test(priority = 74)
	public void checkSavingSalesInvoiceWithPastDate()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingSalesInvoiceWithPastDate(), true);
	}
	
	
	@Test(priority = 75)
	public void checkSavingReceipctsVATWithConvertOption()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingReceipctsVATWithConvertOption(), true);
	}

	@Test(priority = 76)
	public void checkSavedReceipctsVATWithNativeCurrencyValidation()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavedReceipctsVATWithNativeCurrencyValidation(), true);
	}
	
	
	
	
	
	// Completed Payment Terms Validation Scenarios.



	@Test(priority = 91)
	public void checkLogin()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkLogin(), true);
	}
	

	
	@Test(priority = 92)
	public void restoreCompany()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		bp = new BillWisePage(getDriver());
		bp.restoreCompany();
	}
	

	@Test(priority = 93,enabled = false)
	public void checkEraseAllDATA()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkEraseAllDATA(), true);
	}

	
	@Test(priority = 94)
	public void checkSavingWithOpionBreakUpTagDetailsUnderSettingInPVVAT()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingWithOpionBreakUpByTagDetailsUnderSettingInPVVAT(), true);

	}

	@Test(priority = 95)
	public void AchechSavingVoucherInPVVAT()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.chechSavingVoucherInPVVAT(), true);

	}

	@Test(priority = 95)
	public void BcheckSavedPVVATTAGSInBody()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavedPVVATTAGSInBody(), true);

	}

	@Test(priority = 96)
	public void checkSavingDepartmentToBodyUnderSettingIOnPayments()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingDepartmentToBodyUnderSettingIOnPayments(), true);

	}
	

	@Test(priority = 97)
	public void checkSavingPaymentsByAdjustingPVVATWithRow1()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingPaymentsByAdjustingPVVATWithRow1(), true);
	}

	@Test(priority = 98)
	public void AcheckEnteringDataIntoSecondRowAndClickOnSaveInPayments()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkEnteringDataIntoSecondRowAndclickSaveInPayments(), true);
	}

	@Test(priority = 98) // Entry Page Row data CHECK
	public void BcheckSavedPaymentVATWithAccountTAGInBody()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavedPaymentVATWithAccountTAGInBody(), true);
	}
	

	@Test(priority = 100)
	public void checkSavingWithOpionBreakUpByTagDetailsUnderSettingInSalesInvoiceVAT()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingWithOpionBreakUpByTagDetailsUnderSettingInSalesInvoiceVAT(), true);

	}

	
	@Test(priority = 101)
	public void AchechSavingVoucherInSalesInvoiceVAT()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.chechSavingVoucherInSalesInvoiceVAT(), true);

	}

	@Test(priority = 101) // Depends on Previous TC
	public void BcheckSavedSalesinvoiceVATTagsInBody()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavedSalesinvoiceVATTagsInBody(), true);

	}

	@Test(priority = 102)
	public void checkSavingDepartmentToBodyUnderSettingIOnRecepits()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingDepartmentToBodyUnderSettingIOnRecepits(), true);

	}

	@Test(priority = 103)
	public void checkSavingRecepictsByAdjustingPVVATWithRow1()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingRecepictsByAdjustingPVVATWithRow1(), true);
	}

	@Test(priority = 104)
	public void AcheckEnteringDataIntoSecondRowAndClickOnSaveInRecepicts()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkEnteringDataIntoSecondRowAndclickSaveInRecepicts(), true);
	}

	@Test(priority = 104)
	public void BcheckSavedRecepitsWithTagsInBody()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavedRecepitsWithTagsInBody(), true);
	}
	
	@Test(priority = 105)
	public void checkDisableOptionsUnderARAPInSettings()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkDisableOptionsUnderARAPInSettings(), true);
	}

	@Test(priority = 106)
	public void checkARAPOptionsUnderTranactionWithTransactionExists()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkARAPOptionsUnderTranactionWithTransactionExists(), true);
	}

	
	@Test(priority = 107)
	public void checkARAPOptionWithEraseAllTranactions()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkARAPOptionWithEraseAllTranactions(), true);
	}
	

	@Test(priority = 108)
	public void checkEnableARAPOPtions()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkEnableARAPOPtions(), true);
	}

	@Test(priority = 115)
	public void checkSavingRecpitsVATVoucgerWithAccountInBillwise()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingRecpitsVATVoucherWithAccountInBillwise(), true);
	}

	@Test(priority = 116)
	public void chechSavedVouherInRecepitsVATWithOnAccountInBillwise()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.chechSavedVouherInRecepitsVATWithOnAccountInBillwise(), true);
	}

	
	@Test(priority = 117)
	public void checkAdjustingRecepitsVoucherInPaymentsVAT()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkAdjustingRecepitsVoucherInPaymentsVAT(), true);
	}

	@Test(priority = 118) // Reference is Not Getting reflected in VOucher Entry Page level
	public void checkEditingPaymentsVATVoucherByAdjustingFullAmount()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkEditingPaymentsVATVoucherByAdjustingFullAmount(), true);
	}
	

	@Test(priority = 119) //
	public void checkDeletingVoucherinPayments()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkDeletingVoucherinPaymentsWithARAPAdjustments(), true);
	}

	
	@Test(priority = 120)
	public void checkEnablingAPAPOptionAsOnAccountInRecepits()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkEnablingAPAPOptionAsOnAccountInRecepits(), true);
	}

	@Test(priority = 121)
	public void checkSavingRecepitsVoucherWithOnAccount()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingRecepitsVoucherWithOnAccount(), true);
	}

	@Test(priority = 122)
	public void chechSavedVouherInRecepitsWithOnAccountInBillwise()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.chechSavedVouherInRecepitsWithOnAccountInBillwise(), true);
	}

	@Test(priority = 123)
	public void checkAdjustingRecepitsVoucherInPayments()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkAdjustingRecepitsVoucherInPayments(), true);
	}

	@Test(priority = 124) // Reference Issue after saving adjusted voucher is not displaying---Reported
	public void checkSavedVoucherInPaymentsAndEditingByAdjustingAccount()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException 
	{
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavedVoucherInPaymentsAndEditingByAdjustingAccount(), true);
	}

	@Test(priority = 125)
	public void checkRecepitsScreenWithReferenceAllocatedInPayments()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkRecepitsScreenWithReferenceAllocatedInPayments(), true);
	}

	@Test(priority = 126)
	public void checkEnableInputNarriationInSalesInvoiceVAT()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkEnableInputNarriationInSalesInvoiceVAT(), true);
	}

	@Test(priority = 127)
	public void checkSavingVoucheInReceiptsVATWithAdjustmentNarrationTypeVoucher()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingVoucheInReceiptsVATWithAdjustmentNarrationTypeVoucher(), true);
	}

	@Test(priority = 128)
	public void checkSavedReceiptsVATWithNarration()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavedReceiptsVATWithNarration(), true);
	}

	@Test(priority = 129)
	public void checkEnableAlligmentAndValidateInVoucherLevel()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkEnableAlligmentAndValidateInVoucherLevel(), true);
	}

	
	@Test(priority = 130)
	public void restoreCompany2()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException 
	{
		bp = new BillWisePage(getDriver());
		bp.restoreCompany();// Method in Bill wise Page
	}

	
	@Test(priority = 131, enabled = false)

	public void checkLoginTOBillwiseCompany()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		bp = new BillWisePage(getDriver());
		BROP.checkLoginTOBillwiseCompany();
	}

	
	
	@Test(priority = 133)
	public void checkSavingPurchaseVoucherWithAllItems()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingPurchaseVoucherWithAllItems(), true);
	}

	@Test(priority = 134)
	public void checkEnablePostExhangeRateInPaymentsVAT()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkEnablePostExhangeRateInPaymentsVAT(), true);
	}

	@Test(priority = 135)
	public void checkAdjustingPurchaseVoucherInPayments()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkAdjustingPurchaseVoucherInPayments(), true);
	}

	@Test(priority = 136)
	public void checkSavedVoucherInPaymentsVAT()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavedVoucherInPaymentsVAT(), true);
	}
	

	@Test(priority = 137)
	public void checkSavingSalesInvoiceVAT()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingSalesInvoiceVAT(), true);
	}

	@Test(priority = 138)
	public void checEnableCurrencyAndPOstDiffernceInCreditNote()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checEnableCurrencyAndPOstDiffernceInCreditNote(), true);
	}

	@Test(priority = 139)
	public void checkSavingVoucherInCreditNoteAfterEnablingexchangeRATE()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingVoucherInCreditNoteAfterEnablingexchangeRATE(), true);
	}

	@Test(priority = 140)
	public void checkEditingVoucherinCreditNote()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkEditingVoucherinCreditNote(), true);
	}

	@Test(priority = 141)
	public void checkEnablePostDifferenceInJournelEntries()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkEnablePostDifferenceInJournelEntries(), true);
	}
	

	@Test(priority = 142)
	public void checkSavingVoucherInJournelEntries()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingVoucherInJournelEntries(), true);
	}

	@Test(priority = 143)
	public void checkSavedVoucherInJE()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavedVoucherInJE(), true);
	}
	
	
	@Test(priority = 144)
	public void checkEnableOnAccountOptionsINRecepistVAT()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkEnableOnAccountOptionsINRecepistVAT(), true);
	}

	@Test(priority = 145)
	public void checkSavingRecepictsVATVoucher()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingRecepictsVATVoucher(), true);
	}

	@Test(priority = 146)
	public void checkSavedVoucherInRecepitsVATONAccount()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavedVoucherInRecepitsVATONAccount(), true);
	}

	@Test(priority = 147) // error Message
	public void checkAdjustingRecepitsVATInSalesInvoiceVAT()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkAdjustingRecepitsVATInSalesInvoiceVAT(), true);
	}
	

	@Test(priority = 148)
	public void checkRecepitsVATAfterAdjsutedWithSalesInvoiceVAT()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkRecepitsVATAfterAdjsutedWithSalesInvoiceVAT(), true);
	}
	
	

	@Test(priority = 149)
	public void checkEnablingARAPOptionInSettings()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException 
	{
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkEnablingARAPOptionInSettings(), true);
	}

	@Test(priority = 150)
	public void checkEnablePostDiffExchaneRateInPV()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkEnablePostDiffExchaneRateInPV(), true);
	}


	
	
	@Test(priority = 151)
	public void checkSavingPV()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingPV(), true);
	}
	

	@Test(priority = 152)
	public void checkSavingPVWithAEDCurrency()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingPVWithAEDCurrency(), true);
	}

	
	@Test(priority = 153)
	public void checkSavingVoucherInPaymentsVATAEDTOINR()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingVoucherInPaymentsVATAEDTOINR(), true);
	}

	@Test(priority = 155)
	public void checkSavingVoucherInPaymentsVATUSDTOAED()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingVoucherInPaymentsVATUSDTOAED(), true);
	}

	@Test(priority = 156)
	public void checkSavingVoucherInPaymentsVATINRTOAED()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingVoucherInPaymentsVATINRTOAED(), true);
	}

	
	@Test(priority = 159)
	public void checkSavingPaymentsVATWithTwoRowsInNewRef()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException 
	{
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingPaymentsVATWithTwoRowsInNewRef(), true);
	}

	@Test(priority = 160)
	public void checkAdjustingPaymentsInPVWithVendorA()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkAdjustingPaymentsInPVWithVendorA(), true);
	}

	
	
	@Test(priority = 160)
	public void checkChangingVendorBAccountInPV()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkChangingVendorBAccountInPV(), true);
	}

	@Test(priority = 161)
	public void checkPostDifferenceAndLocalExchangedDiffInPV()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkPostDifferenceAndLocalExchangedDiffInPV(), true);
	}

	@Test(priority = 162)
	public void checkSavingPVWithAndNewRefernce()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingPVWithAndNewRefernce(), true);
	}

	@Test(priority = 163)
	public void checkSavingPVWithAdjustingPaymentsAndNewRefernce()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingPVWithAdjustingPaymentsAndNewRefernce(), true);
	}

	@Test(priority = 164)
	public void checkEnablePostDifferenceInDebitNoteVAT()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkEnablePostDifferenceInDebitNoteVAT(), true);
	}

	@Test(priority = 165)
	public void checkSavingDebitNoteVATWithAdjustingPV()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingDebitNoteVATWithAdjustingPV(), true);
	}

	@Test(priority = 166)
	public void checkSavedDebitNotesVAT()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavedDebitNotesVAT(), true);
	}

	@Test(priority = 168)
	public void checkEnablePostDifferenceInCreditNoteVAT()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkEnablePostDifferenceInCreditNoteVAT(), true);
	}

	@Test(priority = 169)
	public void checkSavingCreditNoteVATWithAdjustingPV()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingCreditNoteVATWithAdjustingPV(), true);
	}

	@Test(priority = 170)
	public void checkSavedCreditNotesVAT()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavedCreditNotesVAT(), true);
	}

	@Test(priority = 210)
	public void checkOpenExchangeRateDefWithAEDOnCurrentDATE()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkOpenExchangeRateDefWithAEDOnCurrentDATE(), true);
	}

	@Test(priority = 211)
	public void checkEditingExchangeRateDefWithPastDate()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkEditingExchangeRateDefWithPastDate(), true);
	}

	@Test(priority = 213)
	public void checkEditingExchangeRateDefWithFutureDate()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkEditingExchangeRateDefWithFutureDate(), true);
	}

	@Test(priority = 214)
	public void checkOpenExchangeRateDefWithAEDOnPastDATE()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkOpenExchangeRateDefWithAEDOnPastDATE(), true);
	}

	@Test(priority = 215)
	public void checkOpenExchangeRateDefWithAEDOnFutureDATE()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkOpenExchangeRateDefWithAEDOnFutureDATE(), true);
	}

	@Test(priority = 216)
	public void checkAddingExchangeRateWithUSDInPastDATE()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkAddingExchangeRateWithUSDInPastDATE(), true);
	}

	@Test(priority = 217)
	public void checkAddingExchangeRateWithUSDInFutureDATE()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkAddingExchangeRateWithUSDInFutureDATE(), true);
	}


	
	
	
	// Downloads Backup

	@Test(priority = 225)
	public void checkSavingPurchaseVocherWithPastDate()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingPurchaseVocherWithPastDate(), true);
	}

	
	@Test(priority = 226)
	public void checkSavingPurchaseVocherWithFutureDate()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingPurchaseVocherWithFutureDate(), true);
	}
	
	
	
	@Test(priority = 227)
	public void checkAdjustingPVInPaymentsVATOnCurrentDate()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkAdjustingPVInPaymentsVATOnCurrentDate(), true);
	}

	@Test(priority = 228)
	public void checkEditingPaymentsVATByAdjustingToFutureDateSavedPVVoucher()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkEditingPaymentsVATByAdjustingToFutureDateSavedPVVoucher(), true);
	}

	@Test(priority = 230)
	public void checkSavingPaymentsVATWithCurrentDATE()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingPaymentsVATWithCurrentDATE(), true);
	}

	@Test(priority = 231)
	public void checkAdjustingPaymentsInPurchaeWithFutureDATEUSD()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkAdjustingPaymentsInPurchaeWithFutureDATEUSD(), true);
	}
	
	

	@Test(priority = 232)
	public void checkAdjustingPaymentsInPurchaeWithCurrentDATEUSD()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkAdjustingPaymentsInPurchaeWithCurrentDATEUSD(), true);
	}

	@Test(priority = 233)
	public void checkSavingPaymentsVATWithCurrentDATEINR()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingPaymentsVATWithCurrentDATEINR(), true);
	}

	@Test(priority = 234)
	public void checkAdjustingPaymentsInPurchaeWithCurrentDATEINRTOUSD()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkAdjustingPaymentsInPurchaeWithCurrentDATEINRTOUSD(), true);
	}

	@Test(priority = 235)
	public void checkAdjustingPaymentsInPurchaeWithFutureDATEINRTOUSD()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkAdjustingPaymentsInPurchaeWithFutureDATEINRTOUSD(), true);
	}

	@Test(priority = 240)
	public void checkEnablePostExchageRateInFOREXJVToBodyGrid()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkEnablePostExchageRateInFOREXJVToBodyGrid(), true);
	}

	@Test(priority = 241)
	public void checkSavingVoucherFOREXJV()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingVoucherFOREXJV(), true);
	}

	@Test(priority = 242)
	public void checkSavedVocuerInForexJV()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavedVocuerInForexJV(), true);
	}

	
	@Test(priority = 243)
	public void checkSavingForexJVWithINRToAED()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingForexJVWithINRToAED(), true);
	}

	@Test(priority = 244)
	public void checkSavedForexJVWithINRTOAEDAndPostingDetaisl()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavedForexJVWithINRTOAEDAndPostingDetaisl(), true);
	}

	
	@Test(priority = 245)
	public void checkEnablePostExchangeRateInJournalEntries()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkEnablePostExchangeRateInJournalEntries(), true);
	}

	@Test(priority = 246)
	public void checkSavingVoucherJV()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingVoucherJV(), true);
	}

	@Test(priority = 247)
	public void checkSavedVocherInJV()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavedVocherInJV(), true);
	}
	

	@Test(priority = 250)
	public void checkSavingVoucherJVWithFutureDate()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingVoucherJVWithFutureDate(), true);
	}

	@Test(priority = 251)
	public void checkSavedVocherInJVWithFutureDate()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavedVocherInJVWithFutureDate(), true);
	}

	@Test(priority = 252)
	public void checkEnableInventoryAndAccountTagAsSame()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkEnableInventoryAndAccountTagAsSame(), true);
	}

	@Test(priority = 253)
	public void checkChangingDepToBodyinPostDatedPayments()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkChangingDepToBodyinPostDatedPayments(), true);
	}

	
	@Test(priority = 254)
	public void checkSavingPostDatedPaymentsWithDepBody()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingPostDatedPaymentsWithDepBody(), true);
	}

	@Test(priority = 255)
	public void checkChangingTAGToHeaderWithSame()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkChangingTAGToHeaderWithSame(), true);
	}

	@Test(priority = 256)
	public void checkSavingVoucherWithHeaderChanged()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingVoucherWithHeaderChanged(), true);
	}
	
	
	

	@Test(priority = 3000)
	public void checkEditingExchangeRate()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkEditingExchangeRate(), true);
	}

	@Test(priority = 3001)
	public void checkSavedExchangeRateWithINR()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavedExchangeRateWithINR(), true);
	}

	
	
	@Test(priority = 3002)
	public void checkSavingVoucherInPurchaseVoucherVAT()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingVoucherInPurchaseVoucherVAT(), true);
	}
	

	
	@Test(priority = 3003)
	public void checkEditingPurchaseVoucherVATExchangeRate()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkEditingPurchaseVoucherVATExchangeRate(), true);
	}

	@Test(priority = 3004)
	public void checkSavingPaymentsVATWithAdjsutingPurchase1()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingPaymentsVATWithAdjsutingPurchase1(), true);
	}

	
	@Test(priority = 3005)
	public void checkSavingPaymentsVat2()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingPaymentsVat2(), true);
	}

	@Test(priority = 3006)
	public void checkSavingPaymentVATOnEditingExchageRateInCurrentDATE()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingPaymentVATOnEditingExchageRateInCurrentDATE(), true);
	}

	@Test(priority = 3010)
	public void checkSavingVoucherInPDPVat()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingVoucherInPDPVat(), true);
	}

	@Test(priority = 3011)
	public void checkConvertingVoucherINPDCONBasisOFAmountFilter()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkConvertingVoucherINPDCONBasisOFAmountFilter(), true);
	}

	@Test(priority = 3012)
	public void checkConvertedVoucherOnPaymentsWithAmounts()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkConvertedVoucherOnPaymentsWithAmounts(), true);
	}

	@Test(priority = 3014)
	public void checkEnableReservePostingInPDCAndSavingVoucherINPDR()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkEnableReservePostingInPDCAndSavingVoucherINPDR(), true);
	}

	@Test(priority = 3015)
	public void checkSavingVOucher2InPDR()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingVOucher2InPDR(), true);
	}

	@Test(priority = 3016)
	public void checkConvertingINPDCWithPDR()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkConvertingINPDCWithPDR(), true);
	}

	@Test(priority = 3017)
	public void checkConvertedVoucherInReceipcts()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkConvertedVoucherInReceipcts(), true);
	}

	
	// Adding Exchange Rate Scenarios ---as Saving past date and Editing Current
	// Date Transaction

	@Test(priority = 3025)
	public void checkSavingSalesInvoiceVATWithPastDate()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingSalesInvoiceVATWithPastDate(), true);
	}

	@Test(priority = 3026)
	public void checkSavingReceiptsVATWithCurrentDate()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingReceiptsVATWithCurrentDate(), true);
	}

	@Test(priority = 3027)
	public void checkEditingReceipctsVATWithCurrentDate()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkEditingReceipctsVATWithCurrentDate(), true);
	}

	@Test(priority = 3028)
	public void checkValidationBillrefScreenAfterEditingToCurrentDate()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkValidationBillrefScreenAfterEditingToCurrentDate(), true);
	}

	@Test(priority = 3029)
	public void checkEnableCurrencyOptionInARAP()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkEnableCurrencyOptionInARAP(), true);
	}

	@Test(priority = 3030)
	public void checkSavingPaymentsVAT()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingPaymentsVAT(), true);
	}

	@Test(priority = 3031)
	public void checkEnableOptionsInMRNVoucher()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkEnableOptionsInMRNVoucher(), true);
	}

	@Test(priority = 3032)
	public void checkSavingVOucherInMRNVoucher()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingVOucherInMRNVoucher(), true);
	}

	@Test(priority = 3033)
	public void checkSavedVoucherInMRN()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavedVoucherInMRN(), true);
	}

	
	@Test(priority = 3034)
	public void checkEnableAndVOucherSavingInPurchaseVoucherVAT()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkEnableAndVOucherSavingInPurchaseVoucherVAT(), true);
	}

	@Test(priority = 3035)
	public void checkSavedPVVATWithAED()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavedPVVATWithAED(), true);
	}
	

}
