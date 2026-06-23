package com.focus.testcases;

import java.awt.AWTException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.focus.Pages.ManualAdjustmentsPage;
import com.focus.base.BaseEngine;

public class manualAdjstmentTest extends BaseEngine 
{

	static ManualAdjustmentsPage map;

	@Test(priority = 01)
	public void AcheckLoginToManualAdjsuments()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException 
	{
		map = new ManualAdjustmentsPage(getDriver());
		//Assert.assertEquals(map.checkLogin(),true);
		map.checkLoginToManualAdjsuments();
	}
	
	@Test(priority = 01)
	public void BcheckRestoreOptionInManualAdjustment()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		map.checkRestoreOptionInManualAdjustment();
	}

	// AR Scenarios

	@Test(priority = 02)
	public void checkSavingVoucherInSalesInvoiceVATANDRecepicts()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkSavingVoucherInSalesInvoiceVATANDRecepicts(), true);
	}
	
	

	@Test(priority = 3)
	public void checkManualAdjustMentHomeScreen() 
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkManualAdjustMentHomeScreen(), true);
	}

	@Test(priority = 4)
	public void checkManualAdjutmentScreenWithCustomerTyepandAdjustmentWithAR()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkManualAdjutmentScreenWithCustomerTyepandAdjustmentWithAR(), true);
	}

	@Test(priority = 5)
	public void checkSavedVoucherInrecepictsVATAfterManulAdjustments()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkSavedVoucherInrecepictsVATAfterManulAdjustments(), true);
	}

	@Test(priority = 6) // Data not co-releate to Vouchers Input
	public void checkAuditTrailTransactionsreport()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkAuditTrailTransactionsreport(), true);
	}

	@Test(priority = 7, enabled = false)
	public void checkAuditTrailReportShowModification()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkAuditTrailReportShowModification(), true);
	}

	@Test(priority = 8)
	public void checkSavingVoucherInSalesInvoiceVATWIthMultipleAmount()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkSavingVoucherInSalesInvoiceVATWIthMultipleAmount(), true);
	}

	@Test(priority = 9)
	public void checkSavingReceiptsVATWithQty1500()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkSavingReceiptsVATWithQty1500(), true);
	}

	@Test(priority = 10)
	public void checkManualAdjustmentWithMultipleDebitAndSingelcreditWithAR()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkManualAdjustmentWithMultipleDebitAndSingelcreditWithAR(), true);
	}

	@Test(priority = 11)
	public void checkBalanceAmountOnCreditSideInManulaAdjustmentScreen()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkBalanceAmountOnCreditSideInManulaAdjustmentScreen(), true);
	}

	@Test(priority = 12, enabled = false)
	public void checkAuditTrailTransactionsreportWithMultipleDebitAndSingleCredit()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkAuditTrailTransactionsreportWithMultipleDebitAndSingleCredit(), true);
	}

	@Test(priority = 13, enabled = false)
	public void checkAuditTrailReportShowModificationWithMultipleDebitAndSingleCredit()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkAuditTrailReportShowModificationWithMultipleDebitAndSingleCredit(), true);
	}

	
	@Test(priority = 14)
	public void checkEnableNarrationDrpDownAsRemarksInSalesInvoiceVAT()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkEnableNarrationDrpDownAsRemarksInSalesInvoiceVAT(), true);
	}
	

	@Test(priority = 15)
	public void checkSavingSalesInvoiceVATWith2000()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkSavingSalesInvoiceVATWith2000(), true);
	}

	
	@Test(priority = 16)
	public void checkSavingRecepitsVATWithMultipleSaving()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkSavingRecepitsVATWithMultipleSaving(), true);
	}

	@Test(priority = 17)
	public void checkFIlterOptionInManualAdjustmentScreen()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkFIlterOptionInManualAdjustmentScreen(), true);
	}

	@Test(priority = 18)
	public void checkSavingSalesInvoiceVATWithCopyDOcument()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkSavingSalesInvoiceVATWithCopyDOcument(), true);
	}

	@Test(priority = 19)
	public void checkFilterOptionInManualEntryWithVoucherNumberANDBillNumber()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkFilterOptionInManualEntryWithVoucherNumberANDBillNumber(), true);
	}

	@Test(priority = 20)
	public void checkDeletingVoucherInsalesInvoiceVAT()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkDeletingVoucherInsalesInvoiceVAT(), true);
	}

	@Test(priority = 21)
	public void checkManualAdjustmentWithMultipleCreditAndSingelDebitWithAR()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkManualAdjustmentWithMultipleCreditAndSingelDebitWithAR(), true);
	}

	@Test(priority = 22)
	public void checkBalanceAmountOnDebitSideInManulaAdjustmentScreen()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkBalanceAmountOnDebitSideInManulaAdjustmentScreen(), true);
	}

	@Test(priority = 33)
	public void chekSavingSalesInvoiceVATWithMultiple()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.chekSavingSalesInvoiceVATWithMultiple(), true);
	}

	@Test(priority = 34)
	public void chekSavingreceiptsVATWithMultiple()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.chekSavingReceiptsVATWithMultiple(), true);
	}

	@Test(priority = 35)
	public void checkManualAdjustmentWithMultipleCreditAndMultipleDebitWithAR()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkManualAdjustmentWithMultipleCreditAndMultipleDebitWithAR(), true);
	}
	

	// AP Scenarios

	@Test(priority = 36)
	public void checkSavingVoucherInPurchaseVoucherVAT()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkSavingVoucherInPurchaseVoucherVAT(), true);
	}

	@Test(priority = 37)
	public void checkSavingVoucherInPaymentsVAT()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkSavingVoucherInPaymentsVAT(), true);
	}

	@Test(priority = 38)
	public void checkManualAdjustmentWithMultipleDebitAndSingelcreditWithAP()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkManualAdjustmentWithMultipleDebitAndSingelcreditWithAP(), true);
	}

	@Test(priority = 40)
	public void checkSavedVoucherInPaymentsVATAfterManulAdjustments()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkSavedVoucherInPaymentsVATAfterManulAdjustments(), true);
	}

	@Test(priority = 41)
	public void checkSavingVoucherInPVVATWIthMultipleAmount()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkSavingVoucherInPVVATWIthMultipleAmount(), true);
	}

	@Test(priority = 42)
	public void checkSavingPaymentsVATWithQty1055()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkSavingPaymentsVATWithQty1055(), true);
	}

	@Test(priority = 43)
	public void checkManualAdjustmentWithMultipleDebitAndSinglecreditWithAP()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkManualAdjustmentWithMultipleDebitAndSinglecreditWithAP(), true);
	}

	@Test(priority = 44)
	public void checkBalanceAmountOnCreditSideInManulaAdjustmentScreenWithAP()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkBalanceAmountOnCreditSideInManulaAdjustmentScreenWithAP(), true);
	}

	@Test(priority = 45)
	public void checkSavingPurchaseVoucherVATWith5000()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkSavingPurchaseVoucherVATWith5000(), true);
	}

	@Test(priority = 46)
	public void checkSavingPaymentsVATWithMultipleSaving()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkSavingPaymentsVATWithMultipleSaving(), true);
	}

	@Test(priority = 47)
	public void checkManualAdjustmentWithMultipleCreditAndSingelDebitWithAP()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkManualAdjustmentWithMultipleCreditAndSingelDebitWithAP(), true);
	}

	@Test(priority = 48)
	public void checkBalanceAmountOnDebitSideInManulaAdjustmentScreenAfterApAdjustment()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkBalanceAmountOnDebitSideInManulaAdjustmentScreenAfterApAdjustment(), true);
	}

	
	
	@Test(priority = 50)
	public void chekSavingPVVATWithMultiple()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.chekSavingPVVATWithMultiple(), true);
	}

	
	@Test(priority = 51)
	public void chekSavingPaymentsVATWithMultiple()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.chekSavingPaymentsVATWithMultiple(), true);
	}
	

	@Test(priority = 52)
	public void checkManualAdjustmentWithMultipleCreditAndMultipleDebitWithAP()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkManualAdjustmentWithMultipleCreditAndMultipleDebitWithAP(), true);
	}

	
	@Test(priority = 60)
	public void checkSavingSalesInvoiceVATAndRecepitsVAT()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkSavingSalesInvoiceVATAndRecepitsVAT(), true);
	}

	@Test(priority = 61)
	public void checkSavingPVVATAndPaymentsVAT()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkSavingPVVATAndPaymentsVAT(), true);
	}

	@Test(priority = 62)
	public void checkSavingVoucher2INRecepitsVAT()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkSavingVoucher2INRecepitsVAT(), true);
	}

	@Test(priority = 65)
	public void checkAutoAdjustScreenWithCustomerType()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkAutoAdjustScreenWithCustomerType(), true);
	}

	@Test(priority = 66)
	public void checkSavingAutoAdjustWithVendor()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkSavingAutoAdjustWithVendor(), true);
	}

	@Test(priority = 67)
	public void checkAdjustedVoucherInrecepitsVAT()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkAdjustedVoucherInrecepitsVAT(), true);
	}

	@Test(priority = 68)
	public void checkAdjustedVoucherINPaymentsVAT()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkAdjustedVoucherINPaymentsVAT(), true);
	}

	@Test(priority = 69)
	public void checkNonAdjsutedVoucherINReceiptsVAT()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkNonAdjsutedVoucherINReceiptsVAT(), true);
	}
	

	@Test(priority = 70)
	public void chekSavingSalesAndrecepitsVATWithHigherAnLowerAmount()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.chekSavingSalesAndrecepitsVATWithHigherAnLowerAmount(), true);
	}

	@Test(priority = 71)
	public void checkSavedVoucherInRecepictsVAT()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkSavedVoucherInRecepictsVAT(), true);
	}

	
	
	@Test(priority = 101)
	public void checkSavingSalesInvoiceVATWithCustomers()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkSavingSalesInvoiceVATWithCustomers(), true);
	}

	@Test(priority = 102)
	public void checkSavingRecepictsVAtWithCustomerA()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkSavingRecepictsVAtWithCustomerA(), true);
	}

	@Test(priority = 103)
	public void checkEditingVoucherInRecepictsVAT()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkEditingVoucherInRecepictsVAT(), true);
	}

	@Test(priority = 104)
	public void checkAdjustingCustomerBInRecepictsVAT()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkAdjustingCustomerBInRecepictsVAT(), true);
	}
	
	

	@Test(priority = 105)
	public void checkSavingVoucherInRecepictsVATWithCustomerCReference()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkSavingVoucherInRecepictsVATWithCustomerCReference(), true);
	}

	@Test(priority = 106)
	public void checkManaulAdjsutmentWithCustomerC()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkManaulAdjsutmentWithCustomerC(), true);
	}

	@Test(priority = 107)
	public void checkRececpictsVATVoucherAfterAdjsutmentInManualScreen()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkRececpictsVATVoucherAfterAdjsutmentInManualScreen(), true);
	}

	@Test(priority = 111)
	public void checkSavingRecipctsVATWithCustomerNewreference()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkSavingRecipctsVATWithCustomerNewreference(), true);
	}

	@Test(priority = 112)
	public void checkManualAdjsutmentWithCustomerNewreferenceAcc()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkManualAdjsutmentWithCustomerNewreferenceAcc(), true);
	}

	@Test(priority = 114)
	public void checkAdjustedVoucherInReceipctsVAT()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkAdjustedVoucherInReceipctsVAT(), true);
	}

	@Test(priority = 120)
	public void checkSavingRecepictsVATWithCustomerNewref()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkSavingRecepictsVATWithCustomerNewref(), true);
	}

	@Test(priority = 121)
	public void checkAutoAdjustScreenWithCustomerNewRef()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkAutoAdjustScreenWithCustomerNewRef(), true);
	}

	
	@Test(priority = 122)
	public void checkAdjsutedVoucherInReceiptsVAT()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkAdjsutedVoucherInReceiptsVAT(), true);
	}
	

	@Test(priority = 130)
	public void checkChangingTahsInARAP()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkChangingTahsInARAP(), true);
	}

	
	
	@Test(priority = 131)
	public void checkSavingVouchersInSalesInvoiceVAT()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkSavingVouchersInSalesInvoiceVAT(), true);
	}

	@Test(priority = 132)
	public void checkSavingReceiptsVATVOuchers()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkSavingReceiptsVATVOuchers(), true);
	}

	@Test(priority = 133)
	public void checkManulAdjsumentScreenWithCustomerA()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkManulAdjsumentScreenWithCustomerA(), true);
	}

	//@Test(priority = 134)
	public void checkWithFATAGDifferent()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkWithFATAGDifferent(), true);
	}

	
	@Test(priority = 135)
	public void checkAdjustingInAutoAdjustScreen()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkAdjustingInAutoAdjustScreenWithOutDep(), true);
	}

	@Test(priority = 140)
	public void checkAdjustmentVoucherInrecepictsVATFromAutoAdjustScreen()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkAdjustmentVoucherInrecepictsVATFromAutoAdjustScreen(), true);
	}

	@Test(priority = 141)
	public void checkResavingReceipctsVoucherWithNewReferenceAgain()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkResavingReceipctsVoucherWithNewReferenceAgain(), true);
	}

	@Test(priority = 142)
	public void checkAutoAdjustWithDepartment()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkAutoAdjustWithDepartment(), true);
	}

	@Test(priority = 143)
	public void checkAdjustmentVoucherInrecepictsVATFromAutoAdjustScreenWithDepFilter()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkAdjustmentVoucherInrecepictsVATFromAutoAdjustScreenWithDepFilter(), true);
	}
	
	
	// New Scnarios

	@Test(priority = 201)
	public void checkRestoreExchangeBackUp()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		map.checkRestoreExchangeBackUp();
	}

	
	@Test(priority = 205)
	public void checkSavingSalesInvoiceVATWithPastDate()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkSavingSalesInvoiceVATWithPastDate(), true);
	}
	

	@Test(priority = 206)
	public void checkSavingReceipcsVATAndAdjustingSalesInvoiceVAT()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkSavingReceipcsVATAndAdjustingSalesInvoiceVAT(), true);
	}

	@Test(priority = 208)
	public void checkPostingInAdjustForeignExchange()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkPostingInAdjustForeignExchange(), true);
	}

	@Test(priority = 209)
	public void checPostedVoucherINNonStandardJournales()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checPostedVoucherINNonStandardJournales(), true);
	}
	

	@Test(priority = 210)
	public void checkPostingThorughtManulAdjustmentScreen()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkPostingThorughtManulAdjustmentScreen(), true);
	}

	@Test(priority = 211)
	public void checkSavingWithAutoAdjustScreen()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkSavingWithAutoAdjustScreen(), true);
	}

	@Test(priority = 215)
	public void checkLedgerReport()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkLedgerReport(), true);
	}

	@Test(priority = 216)
	public void checkCustomerStatementsReport()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkCustomerStatementsReport(), true);
	}

	@Test(priority = 218)
	public void checkSavingPaymentsVAT()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkSavingPaymentsVAT(), true);
	}

	@Test(priority = 219)
	public void checkSavingMRNVOucherWithUpdateFA()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkSavingMRNVOucherWithUpdateFA(), true);
	}

	@Test(priority = 220)
	public void checkPostingDetailsInMRNVOucher()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkPostingDetailsInMRNVOucher(), true);
	}
	
	
	@Test(priority = 222)
	public void checkSavingVoucherInPurchaseVOucherVAT()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkSavingVoucherInPurchaseVOucherVAT(), true);
	}
	
	@Test(priority = 223)
	public void checkPostingDetailsInPurchaseVoucherVAT()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkPostingDetailsInPurchaseVoucherVAT(), true);
	}
	
	
	
	
	@Test(priority = 225)
	public void checkSavingMultipleVouchersofSalesInvoiceVATVoucher()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkSavingMultipleVouchersofSalesInvoiceVATVoucher(), true);
	}
	
	
	
	@Test(priority = 226)
	public void checkSavingReceiptsVATVoucher()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkSavingReceiptsVATVoucher(), true);
	}

	
	
	
	@Test(priority = 227)
	public void checkPostinginManualAdjustmentforMultipleDebitVocuhers()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkPostinginManualAdjustmentforMultipleDebitVocuhers(), true);
	}
	
	
	
	
	@Test(priority = 228)
	public void checkAdjustmentVoucherinReceiptsVATAfterManualAdjustment()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		map = new ManualAdjustmentsPage(getDriver());
		Assert.assertEquals(map.checkAdjustmentVoucherinReceiptsVATAfterManualAdjustment(), true);
	}
	
	
}
