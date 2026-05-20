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

public class BillwiseRestOptionsPaymentTermsTest extends BaseEngine{
	
	
	static BillwiseRestOptions BROP;
	static BillWisePage bp;

	
	@Test(priority = 1)
	public void checkLoginTOPaymentsTerms()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkLogin(), true);
	}
	
	
	
	@Test(priority = 2)
	public void restoreCompanyForPaymentTerms()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());

		BROP.restoreCompanyForRestOptions();
	}
	
	
	@Test(priority = 3)
	public void checkSavedRecepictsVATVoucherWithAdjustments()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavedRecepictsVATVoucherWithAdjustments(), true);
	}
	

	@Test(priority = 4)
	public void checkSavingSalesInvoiceVATAndSavingRecepcitsVAT()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingSalesInvoiceVATAndSavingRecepcitsVAT(), true);
	}

	@Test(priority = 5)
	public void checkSavingRecepictsVATWithNewReference()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingRecepictsVATWithNewReference(), true);
	}

	@Test(priority = 6)
	public void checkSavingReceipctsVATWithRemainingAmt()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingReceipctsVATWithRemainingAmt(), true);
	}

	
	@Test(priority = 7)
	public void GcheckSuspendingAndAddingSecoundRowIn1stVoucher()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSuspendingAndAddingSecoundRowIn1stVoucher(), true);
	}

	@Test(priority = 8)
	public void checkBillReferenceAfterConsumedIn1stVoucher()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkBillReferenceAfterConsumedIn1stVoucher(), true);
	}

	
	@Test(priority = 9)
	public void checkSavingPurchaseVoucherVATWithFutureDateAndVaryDueDate()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingPurchaseVoucherVATWithFutureDateAndVaryDueDate(), true);
	}

	@Test(priority = 10) // Date And due date Are Not UPdated ---Issue reported.
	public void checkCOpyTOCLipboardOptionsWithDueDateDifferent()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkCOpyTOCLipboardOptionsWithDueDateDifferent(), true);
	}

	@Test(priority = 11) // ITC
	public void checkSavinOpeningStockByInserRow()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavinOpeningStockByInserRow(), true);
	}

	@Test(priority = 12) // ITC

	public void checkSavingAfterInserRowDoneInVoucher2()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingAfterInserRowDoneInVoucher2(), true);
	}

	
	@Test(priority = 13) // ITC
	public void checkSavingPaymentsVATVoucherANDRaiseCheque()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException 
	{
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingPaymentsVATVoucherANDRaiseCheque(), true);
	}

	@Test(priority = 14) // ITC
	public void checkReverseChequeRecepitsVATVoucher()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkReverseChequeRecepitsVATVoucher(), true);
	}

	@Test(priority = 15) // ITC
	public void checkVoucherReveretedToPaymentsVAT()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkVoucherReveretedToPaymentsVAT(), true);
	}

	@Test(priority = 16) // ITC
	public void checkValidationInSalesInvoiceVATWithCustomerA()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkValidationInSalesInvoiceVATWithCustomerA(), true);
	}

	@Test(priority = 17) // ITC
	public void checkSalesInvoiceVATWithCustomerB()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSalesInvoiceVATWithCustomerB(), true);
	}
	
	
	
	// Payment Terms Starts From here ***********************************************************************************

	@Test(priority = 18)
	public void checkSavingAccountMaster()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingAccountMaster(), true);
	}

	@Test(priority = 19)
	public void checkPaymentsTermsScreen()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkPaymentsTermsScreen(), true);
	}

	@Test(priority = 20)
	public void checkSavingPTWIthoutInput()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingPTWIthoutInput(), true);
	}

	@Test(priority = 21)
	public void checkEntringInputInPaymentTerms()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkEntringInputInPaymentTerms(), true);
	}

	@Test(priority = 22)
	public void checkDeleteRowInPaymentTermsS()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkDeleteRowInPaymentTerms(), true);
	}

	@Test(priority = 23)
	public void checkResavingPaymentTermsAfterDeletion()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkResavingPaymentTermsAfterDeletion(), true);
	}

	@Test(priority = 24)
	public void checkSavedPayTermsAndEditing()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavedPayTermsAndEditing(), true);
	}

	@Test(priority = 25)
	public void checkAssgingAccTOPaymentTerms()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkAssgingAccTOPaymentTerms(), true);
	}
	
	

	@Test(priority = 26)
	public void checkSavingVoucherWithPaymentTermsInCashSales()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingVoucherWithPaymentTermsInCashSales(), true);
	}

	@Test(priority = 27) // Expected Fail as to get discount column in entry page Body display wrong 
	public void checkSavingRecepitsVoucher()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingRecepitsVoucher(), true);
	}
	

	@Test(priority = 28)
	public void checksavedRecepictsVAT()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checksavedRecepictsVAT(), true);
	}

	
	@Test(priority = 29)
	public void checkWith5DaysDueDateInRecepitsVAT()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkWith5DaysDueDateInRecepitsVAT(), true);
	}

	@Test(priority = 30)
	public void checkWith10DaysDueDateInRecepitsVAT()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkWith10DaysDueDateInRecepitsVAT(), true);
	}

	@Test(priority = 31)
	public void checkWith20DaysDueDateInRecepitsVAT()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkWith20DaysDueDateInRecepitsVAT(), true);
	}

	@Test(priority = 32)
	public void checkSavingPaymentsTerms2()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingPaymentsTerms2(), true);
	}

	@Test(priority = 33)
	public void checkAssginingPaymntTermsToVendorNewRefernceAccount()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkAssginingPaymntTermsToVendorNewRefernceAccount(), true);
	}

	@Test(priority = 34)
	public void checkEnablePaymentTermsOptionInPVVAT()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkEnablePaymentTermsOptionInPVVAT(), true);
	}

	@Test(priority = 35)
	public void checkSavingPVVATWithPaymentTerms()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingPVVATWithPaymentTerms(), true);
	}

	@Test(priority = 36)
	public void checkSavingPaymentsVATVou1WithPaymentTerms()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingPaymentsVATVou1WithPaymentTerms(), true);
	}

	
	@Test(priority = 37)
	public void checkSavedPaymentsVAT()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavedPaymentsVAT(), true);
	}

	@Test(priority = 38)
	public void AcheckWith10DaysDueDateInPAYMENTSVAT()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkWith10DaysDueDateInPAYMENTSVAT(), true);
	}

	
	@Test(priority = 39)
	public void BcheckWith5DaysDueDateInPAYMENTSVAT()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkWith5DaysDueDateInPAYMENTSVAT(), true);
	}

	
	
	@Test(priority = 40)
	public void checkSavingCashSalesVoucher()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingCashSalesVoucher(), true);
	}

	@Test(priority = 41)
	public void checkAddingExtraFiledPDCNOInRecepitsFIFO()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkAddingExtraFiledPDCNOInRecepitsFIFO(), true);
	}

	@Test(priority = 42)
	public void checkSavingVoucherPostDatedReceipts()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingVoucherPostDatedReceipts(), true);
	}

	@Test(priority = 43)
	public void checkConvertingVoucherINPDCScreen()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkConvertingVoucherINPDCScreen(), true);
	}

	@Test(priority = 44)
	public void checkCovertedRecepitsFIFOVoucher()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkCovertedRecepitsFIFOVoucher(), true);
	}
	
	
	@Test(priority = 45)
	public void checkSavingPurchaseVoucherWithPastDateAndEnablepaymentTerms()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingPurchaseVoucherWithPastDateAndEnablepaymentTerms(), true);
	}

	@Test(priority = 46)
	public void checkAddingExtraFiledPDCNOInPaymentsFIFO()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkAddingExtraFiledPDCNOInPaymentsFIFO(), true);
	}

	@Test(priority = 47)
	public void checkSavingVoucherPostDatedPayments()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingVoucherPostDatedPayments(), true);
	}

	@Test(priority = 48)
	public void checkConvertingVoucherPDPINPDCScreen()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkConvertingVoucherPDPINPDCScreen(), true);
	}

	@Test(priority = 49)
	public void checkCovertedPaymentsFIFOVoucher()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkCovertedPaymentsFIFOVoucher(), true);
	}

	@Test(priority = 50)
	public void checkPostingDetailsInPaymentsFIFO()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkPostingDetailsInPaymentsFIFO(), true);
	}

	
	
	
	// Newly Added
	
	@Test(priority = 51)
	public void checkEnableOptionPickcreditDaysFromPaymentTerms()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkEnableOptionPickcreditDaysFromPaymentTerms(), true);
	}

	@Test(priority = 52)
	public void checkEnablePaymentTermsiNSalesInvoiceVAT()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkEnablePaymentTermsiNSalesInvoiceVAT(), true);
	}

	@Test(priority = 53)
	public void checkPaymentTermsValidationAtVoucherLevel()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkPaymentTermsValidationAtVoucherLevel(), true);
	}

	
	@Test(priority = 54)
	public void checkEnableChangePaymentTermsInsalesInvoiceVAT()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkEnableChangePaymentTermsInsalesInvoiceVAT(), true);
	}

	@Test(priority = 55)
	public void checkChangePaymentTermOptionInSalesInvoiceVAT()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkChangePaymentTermOptionInSalesInvoiceVAT(), true);
	}

	@Test(priority = 56)
	public void checkSavingSalesInvoiceVATWithEnableOption()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingSalesInvoiceVATWithEnableOption(), true);
	}

	
	@Test(priority = 57)
	public void checkEnableOptionPickCreditDaysFromSalesAccount()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkEnableOptionPickCreditDaysFromSalesAccount(), true);
	}

	
	@Test(priority = 58)
	public void checkInputCreditDaysOFSalesAccInAccMaster()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkInputCreditDaysOFSalesAccInAccMaster(), true);
	}

	@Test(priority = 59)
	public void checkSavingSalesinvoiceWithOptionCreditDays()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingSalesinvoiceWithOptionCreditDays(), true);
	}
	
	// Payment term Ends here 
	
	
	
	// new Scanrios To be added here 
	

	@Test(priority = 61)
	public void checkEnablePaymentTermInSalesInvoiceVoucher()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkEnablePaymentTermInSalesInvoiceVoucher(), true);
	}
	
	
	
	@Test(priority = 62)
	public void checkSavingSalesInvoiceVoucherWithPaymentTerms()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingSalesInvoiceVoucherWithPaymentTerms(), true);
	}
	
	
	@Test(priority = 63)
	public void checkSavingSalesInvoiceVoucher3WithPaymentTerms()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingSalesInvoiceVoucher3WithPaymentTerms(), true);
	}
	
	
	@Test(priority = 64)
	public void checkSavingLedgerReportWithPaymentTermsFiledInReport()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingLedgerReportWithPaymentTermsFiledInReport(), true);
	}
	
	
	
	@Test(priority = 65)
	public void checkSavingPaymentTermsAndValidatingReportInSalesRegister()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingPaymentTermsAndValidatingReportInSalesRegister(), true);
	}
	
	
	@Test(priority = 66)
	public void checkSavingSalesInvoiceVoucher()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingSalesInvoiceVoucher(), true);
	}
	
	
	@Test(priority = 67)
	public void checkSavingSales2WithSameCustomerAndDep()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingSales2WithSameCustomerAndDep(), true);
	}
	

	@Test(priority = 68)
	public void checkEnablePostExchangeRateInReceictsVAT()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkEnablePostExchangeRateInReceictsVAT(), true);
	}
	

	@Test(priority = 69)
	public void checkConvertingPendingBillsWithSameARAP()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkConvertingPendingBillsWithSameARAP(), true);
	}
	
	
	@Test(priority = 70)
	public void checkSavedVoucherWithConvertOptionsSameDepAndAcc()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavedVoucherWithConvertOptionsSameDepAndAcc(), true);
	}
	
	
	
	@Test(priority = 71)
	public void checkChangingDifferentAccountAndSameDep()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkChangingDifferentAccountAndSameDep(), true);
	}
	
	
	
	@Test(priority = 72)
	public void checkConvertingDifferentAccountAndSameDep()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkConvertingDifferentAccountAndSameDep(), true);
	}
	
	
	@Test(priority = 73)
	public void checkChangingSameAccountAndDiffDep()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkChangingSameAccountAndDiffDep(), true);
	}
	
	

	@Test(priority = 74)
	public void checkConvertingSameAccountAndDiffDep()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkConvertingSameAccountAndDiffDep(), true);
	}
	
	@Test(priority = 75)
	public void checkSavingSalesInvoiceWithBackdatedandChangeInCurrencyvalue()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingSalesInvoiceWithBackdatedandChangeInCurrencyvalue(), true);
	}
	
	
	@Test(priority = 76)
	public void checkConveringFromPendingBillsInrecepictsVAT()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkConveringFromPendingBillsInrecepictsVAT(), true);
	}
	
	
	@Test(priority = 77)
	public void checkSavedVoucherInReceipctsVAT()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavedVoucherInReceipctsVAT(), true);
	}
	
	
	
	@Test(priority = 78)
	public void checkEnableExchageRateDifferenceInPayments()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkEnableExchageRateDifferenceInPayments(), true);
	}
	
	
	
	@Test(priority = 80)
	public void checkSavingPurchaseVoucherWithLessExchageRate()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingPurchaseVoucherWithLessExchageRate(), true);
	}
	

	@Test(priority = 81)
	public void checkSavingPaymentsVOucherAjustingPurchaseVoucherWithCurrecntdate()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingPaymentsVOucherAjustingPurchaseVoucherWithCurrecntdate(), true);
	}
		
	
	
	@Test(priority = 82)
	public void checkSavedAndPostingDetaialsOInPayments()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavedAndPostingDetaialsOInPayments(), true);
	}
	
	
	
	
	
	@Test(priority = 83)
	public void checkSavingExchageRateWithINR()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingExchageRateWithINR(), true);
	}
	
	
	@Test(priority = 84)
	public void checkSavingSalesInvoiceWithPastDate()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingSalesInvoiceWithPastDate(), true);
	}
	
	
	@Test(priority = 85)
	public void checkSavingReceipctsVATWithConvertOption()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingReceipctsVATWithConvertOption(), true);
	}

	@Test(priority = 86)
	public void checkSavedReceipctsVATWithNativeCurrencyValidation()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavedReceipctsVATWithNativeCurrencyValidation(), true);
	}
	
	
	
	
	

}
