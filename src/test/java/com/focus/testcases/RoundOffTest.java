package com.focus.testcases;

import java.awt.AWTException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.focus.Pages.BillwiseRestOptions;
import com.focus.base.BaseEngine;

public class RoundOffTest extends BaseEngine 
{

	static BillwiseRestOptions BROP;

	/// RoundOFF-FIRST BACK UP--Restore Back Up in Back up Folder under Project

	@Test(priority = 1)
	public void checkLoginRoundOFFCompany()
			throws EncryptedDocumentException, InvalidFormatException, IOException,  InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkLoginRoundOFFCompany(), true);
	}


	
	@Test(priority = 2)
	public void VerifyRestoreInRoundOff()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
	
		BROP.checkRestoreInRoundOff();
	}
	
	
	
	@Test(priority = 3) // ITC--Issue Gross is Not Updating on Paste From Excel
	public void checkCopyPasteOptionInVOucherEntryPageAndValidateGrosspreload()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkCopyPasteOptionInVOucherEntryPageAndValidateGrosspreload(), true);
	}

	@Test(priority = 4) // ITC
	public void checkAutoLoadOptionWithSingleRow()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkAutoLoadOptionWithSingleRow(), true);
	}

	
	@Test(priority = 5) // ITC
	public void FcheckCopyPasteFromExcelMultipleRows()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkCopyPasteFromExcelMultipleRows(), true);
	}

	@Test(priority = 6) // ITC---Auto Load Issue
	public void checkLoadingGrossValueAfterCopyFromEXCEL()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkLoadingGrossValueAfterCopyFromEXCEL(), true);
	}

	@Test(priority = 7) // ITC
	public void checkSavedVoucherinsalesQutations()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavedVoucherinsalesQutations(), true);
	}

	
	@Test(priority = 8) // ITC
	public void checkYesOptionINCopyFromExcelScenario()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkYesOptionINCopyFromExcelScenario(), true);
	}
	
	
	
	@Test(priority = 9) 
	public void checkGrossCalculatedValueINEntryPage()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkGrossCalculatedValueINEntryPage(), true);
	}
	

//	@Test(priority = 10) // Showing error message
	public void CheckSavingPurchaseOrderWithFutureDTAndTryTOConvert()
			throws EncryptedDocumentException, InvalidFormatException, IOException,  InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.CheckSavingPurchaseOrderWithFutureDTAndTryTOConvert(), true);
	}

//	@Test(priority=11)
	public void checkSavingPurchaseVoucherVATWithFutureDTConvert()
			throws EncryptedDocumentException, InvalidFormatException, IOException,  InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingPurchaseVoucherVATWithFutureDTConvert(), true);
	}
	
	
	
	
	
	@Test(priority = 12)
	public void checkSavingSalesOrderForRoundOFF()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingSalesOrderForRoundOFF(), true);
	}

	
	@Test(priority = 13)
	public void checkSavingSalesInvoiceVATWithSalesOrderLinkIncludedRoudOFF()
			throws EncryptedDocumentException, InvalidFormatException, IOException,  InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingSalesInvoiceVATWithSalesOrderLinkIncludedRoudOFF(), true);
	}

	@Test(priority = 14)
	public void checksavedVoucherInsalesInvoiceVATWithRounfOFF()
			throws EncryptedDocumentException, InvalidFormatException, IOException,  InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checksavedVoucherInsalesInvoiceVATWithRounfOFF(), true);
	}

	
	@Test(priority = 15)
	public void checkSavingSalesOrderForPostiveRounfOFFValue()
			throws EncryptedDocumentException, InvalidFormatException, IOException,  InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingSalesOrderForPostiveRounfOFFValue(), true);
	}

	@Test(priority = 16)
	public void checkConvertSalesOrderToSalesInVOiceVAT()
			throws EncryptedDocumentException, InvalidFormatException, IOException,  InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkConvertSalesOrderToSalesInVOiceVAT(), true);
	}

	@Test(priority = 17)
	public void checkSavedVoucherInsalesInvoiceVATWithPostiveRounfOFF()
			throws EncryptedDocumentException, InvalidFormatException, IOException,  InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavedVoucherInsalesInvoiceVATWithPostiveRounfOFF(), true);
	}

	
	@Test(priority = 18)
	public void checkSavingSalesInvoiceVATWithChangeInCurrencyINRT0USD()
			throws EncryptedDocumentException, InvalidFormatException, IOException,  InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingSalesInvoiceVATWithChangeInCurrencyINRT0USD(), true);
	}

	@Test(priority = 19)
	public void checkSavingWithChangeExchangeRATE()
			throws EncryptedDocumentException, InvalidFormatException, IOException,  InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingWithChangeExchangeRATE(), true);
	}

	@Test(priority = 20)
	public void checkPostingDetailsInSalesInvoiceVAT()
			throws EncryptedDocumentException, InvalidFormatException, IOException,  InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkPostingDetailsInSalesInvoiceVAT(), true);
	}

	
	@Test(priority = 21)
	public void checkTryToConvertVoucherInSalesOrderAfterFullConsumption()
			throws EncryptedDocumentException, InvalidFormatException, IOException,  InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkTryToConvertVoucherInSalesOrderAfterFullConsumption(), true);
	}

	
	@Test(priority = 22)
	public void checkSavingSalesOrderBYMultipleRowsEntry()
			throws EncryptedDocumentException, InvalidFormatException, IOException,  InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingSalesOrderBYMultipleRowsEntry(), true);
	}

	@Test(priority = 23)
	public void checkValidationGrossANDNetAmountInSalesInvoiceVATAndSavingVoucher()
			throws EncryptedDocumentException, InvalidFormatException, IOException,  InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkValidationGrossANDNetAmountInSalesInvoiceVATAndSavingVoucher(), true);
	}
	
	
	@Test(priority = 24)
	public void checkDataEntryWithDuplicaterowInSalesOrder()
			throws EncryptedDocumentException, InvalidFormatException, IOException,  InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkDataEntryWithDuplicaterowInSalesOrder(), true);
	}

	@Test(priority = 25)
	public void checkLoadingLinksInSalesInvoiceVATAfterDulplicateRowInBaseVoucher()
			throws EncryptedDocumentException, InvalidFormatException, IOException,  InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkLoadingLinksInSalesInvoiceVATAfterDulplicateRowInBaseVoucher(), true);
	}

	@Test(priority = 20, enabled = false)
	public void checkEnableCurrencyToBodyInRecepitsVoucher()
			throws EncryptedDocumentException, InvalidFormatException, IOException,  InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkEnableCurrencyToBodyInRecepitsVoucher(), true);
	}

	@Test(priority = 26)
	public void checkRecepitsEntryPageBodyHeaderGridListAfterEnableCurrencyInBody()
			throws EncryptedDocumentException, InvalidFormatException, IOException,  InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkRecepitsEntryPageBodyHeaderGridListAfterEnableCurrencyInBody(), true);
	}
	
	@Test(priority = 27) // enterBody_LocExgRate::0.0000000000 Value Exp: 4.2800000000---Issue Not
							// Loading Exchange Col in Body Grid
	public void checkSavingVoucherInrecepitsWithCurrencyInBody()
			throws EncryptedDocumentException, InvalidFormatException, IOException,  InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingVoucherInrecepitsWithCurrencyInBody(), true);
	}
	
	

	@Test(priority = 28)
	public void checkSavedVoucherinRecepitsVAT()
			throws EncryptedDocumentException, InvalidFormatException, IOException,  InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavedVoucherinRecepitsVAT(), true);
	}

	@Test(priority = 29)
	public void checkCopyAndPasteOptionsInBodyCurrency()
			throws EncryptedDocumentException, InvalidFormatException, IOException,  InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkCopyAndPasteOptionsInBodyCurrency(), true);
	}

	@Test(priority = 30)
	public void checkSavedVoucherinRecepitsVATWith4Rows()
			throws EncryptedDocumentException, InvalidFormatException, IOException,  InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavedVoucherinRecepitsVATWith4Rows(), true);
	}
	

	
	// VAT on Adjustment in the below Scenario.

	// 1. Raised sales invoice with 100 with New Reference
	// 2. Raised Receipt voucher with 100 with New Reference
	// 3. Now open the Receipt voucher and adjust the sales invoice voucher then VAT
	// value should become 0.

	
	@Test(priority = 31) // Error Message
	public void checksavingSalesInvoiceVATWithNewReference()
			throws EncryptedDocumentException, InvalidFormatException, IOException,  InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checksavingSalesInvoiceVATWithNewReference(), true);
	}

	@Test(priority = 32)
	public void checkSavingRecepitsVATWithNewReference()
			throws EncryptedDocumentException, InvalidFormatException, IOException,  InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingRecepitsVATWithNewReference(), true);
	}

	@Test(priority = 33)
	public void checkSavingRecepitsVATWithAdjustMentWithSalesInvoiceVATInBillrefrence()
			throws EncryptedDocumentException, InvalidFormatException, IOException,  InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingRecepitsVATWithAdjustMentWithSalesInvoiceVATInBillrefrence(), true);
	}

	@Test(priority = 34) // Vat Column in Updating with Value --TO be update as 0.00
	public void checkSavedRecepitsVoucherWithAdjustmentAfterBillWiseNewReference()
			throws EncryptedDocumentException, InvalidFormatException, IOException,  InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavedRecepitsVoucherWithAdjustmentAfterBillWiseNewReference(), true);
	}

	@Test(priority = 35)
	public void checkSavingRecepitsVATVOuchetWithDirectAdjustmentToSalesInvoiceVAT()
			throws EncryptedDocumentException, InvalidFormatException, IOException,  InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingRecepitsVATVOuchetWithDirectAdjustmentToSalesInvoiceVAT(), true);
	}
	
	
	
	
	// New Scenario insert row in sales invoice

	@Test(priority = 36)
	public void checkSavingOpeningStockWithSTDItem()
			throws EncryptedDocumentException, InvalidFormatException, IOException,  InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingOpeningStockWithSTDItem(), true);
	}

	
	@Test(priority = 37)
	public void checkSavingSalesinvoiceWithThreeRows()
			throws EncryptedDocumentException, InvalidFormatException, IOException,  InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingSalesinvoiceWithThreeRows(), true);
	}

	@Test(priority = 38)
	public void checkSavingSalesinvoiceWithInserRow()
			throws EncryptedDocumentException, InvalidFormatException, IOException,  InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingSalesinvoiceWithInserRow(), true);
	}
	
	

	// Scenario Added Update MRN

	@Test(priority = 50)
	public void checkSavingVoucherInMRN()
			throws EncryptedDocumentException, InvalidFormatException, IOException,  InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingVoucherInMRN(), true);
	}

	@Test(priority = 51)
	public void checkLinkLoadingInPVAndSaveChangeRate()
			throws EncryptedDocumentException, InvalidFormatException, IOException,  InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkLinkLoadingInPVAndSaveChangeRate(), true);
	}

	@Test(priority = 52)
	public void checkRateFiledInMRNAfterUpdationRateInPV()
			throws EncryptedDocumentException, InvalidFormatException, IOException,  InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkRateFiledInMRNAfterUpdationRateInPV(), true);
	}
	
	@Test(priority = 53)
	public void checkChangingExtraChargesFieldInPVAndValidationInMRN()
			throws EncryptedDocumentException, InvalidFormatException, IOException,  InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkChangingExtraChargesFieldInPVAndValidationInMRN(), true);
	}

	
	@Test(priority = 54)
	public void checkSavingMRNWithMultipleLineEntry()
			throws EncryptedDocumentException, InvalidFormatException, IOException,  InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingMRNWithMultipleLineEntry(), true);
	}

	@Test(priority = 55)
	public void checkConvertedVoucherInPVWithMupltiplelinesinMRN()
			throws EncryptedDocumentException, InvalidFormatException, IOException,  InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkConvertedVoucherInPVWithMupltiplelinesinMRN(), true);
	}

	@Test(priority = 56)
	public void checkRateUpdatedinMRNWithMultipleLines()
			throws EncryptedDocumentException, InvalidFormatException, IOException,  InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkRateUpdatedinMRNWithMultipleLines(), true);
	}
	
	
	@Test(priority = 57)
	public void checkInputFDPercentageInPVAndValidateInMRN()
			throws EncryptedDocumentException, InvalidFormatException, IOException,  InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkInputFDPercentageInPVAndValidateInMRN(), true);
	}

	
	@Test(priority = 57)
	public void checkSavingVouhcherInMRNandAddingDiscountInPV()
			throws EncryptedDocumentException, InvalidFormatException, IOException,  InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingVouhcherInMRNandAddingDiscountInPV(), true);
	}

	@Test(priority = 59)
	public void checkMRNVoucherAfterAddingDiscountColinPV()
			throws EncryptedDocumentException, InvalidFormatException, IOException,  InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkMRNVoucherAfterAddingDiscountColinPV(), true);
	}

	
	@Test(priority = 60)
	public void checkInputFooterDisocuntInPVAndValidateInPV()
			throws EncryptedDocumentException, InvalidFormatException, IOException,  InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkInputFooterDisocuntInPVAndValidateInPV(), true);
	}

	
	
	// Exchange Rate Added in MRN and PV---Roundoff-1 back up

	@Test(priority = 61)
	public void checkSavingVoucherInMRNWithExchangeRATE()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException 
	{
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingVoucherInMRNWithExchangeRATE(), true);
	}

	@Test(priority = 62)//getting error message Voucher amount not matching with pronghorn
	public void checkSavingPVWithAddExchageRateFiled()
			throws EncryptedDocumentException, InvalidFormatException, IOException,  InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingPVWithAddExchageRateFiled(), true);
	}

	@Test(priority = 63)//voucher amount not matching 
	public void checkVoucherInMRNAfterAddingExchagerateValueInPV()
			throws EncryptedDocumentException, InvalidFormatException, IOException,  InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkVoucherInMRNAfterAddingExchagerateValueInPV(), true);
	}

	
	
	@Test(priority = 64)
	public void checkSavingVoucherInMRNWithMultipleRowEntryForExchageRateInBASE()
			throws EncryptedDocumentException, InvalidFormatException, IOException,  InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingVoucherInMRNWithMultipleRowEntryForExchageRateInBASE(), true);
	}

	@Test(priority = 65)
	public void checkConvertedVoucherInPVWithMupltiplelinesFOrAddingExchageValueINBase()
			throws EncryptedDocumentException, InvalidFormatException, IOException,  InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkConvertedVoucherInPVWithMupltiplelinesFOrAddingExchageValueINBase(), true);
	}

	@Test(priority = 66)
	public void checkVoucherInMRNAfterAddingExchagerateValueInPVWithMultipleLine()
			throws EncryptedDocumentException, InvalidFormatException, IOException,  InterruptedException, AWTException 
	{
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkVoucherInMRNAfterAddingExchagerateValueInPVWithMultipleLine(), true);
	}
	
	
	
	//start
	@Test(priority = 67)
	public void checkSavingVoucherInMRNWithMultipleRowEntryForDebitRateInBASE()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingVoucherInMRNWithMultipleRowEntryForDebitRateInBASE(), true);
	}

	@Test(priority = 68)//getting error message Voucher amount not matching with pronghorn
	public void checkConvertedVoucherInPVWithMupltiplelinesFOrAddingDebitValueINBase()
			throws EncryptedDocumentException, InvalidFormatException, IOException,  InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkConvertedVoucherInPVWithMupltiplelinesFOrAddingDebitValueINBase(), true);
	}

	@Test(priority = 69)//need to chck
	public void checkVoucherInMRNAfterAddingDebitRateValueInPVWithMultipleLine()
			throws EncryptedDocumentException, InvalidFormatException, IOException,  InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkVoucherInMRNAfterAddingDebitRateValueInPVWithMultipleLine(), true);
	}
	
	
	
	// Repost Scenarios

	@Test(priority = 120) // Final Message

	public void checkSavingVoucherInSalesInvoice()
			throws EncryptedDocumentException, InvalidFormatException, IOException,  InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingVoucherInSalesInvoice(), true);
	}

	
	@Test(priority = 121)
	public void checkSavingSalesReturnVoucher()
			throws EncryptedDocumentException, InvalidFormatException, IOException,  InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingSalesReturnVoucher(), true);
	}
	
	
	
	@Test(priority = 122)
	public void checkSavingBuyerPriceBookWithSTDItem()
			throws EncryptedDocumentException, InvalidFormatException, IOException,  InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingBuyerPriceBookWithSTDItem(), true);
	}
	


	@Test(priority = 123)
	public void checkSavedSellerPriceBook()
			throws EncryptedDocumentException, InvalidFormatException, IOException,  InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavedSellerPriceBook(), true);
	}

	@Test(priority = 125)
	public void checkENABLEFormulaUnderEditLayoutTABInSalesInvoice()
			throws EncryptedDocumentException, InvalidFormatException, IOException,  InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkENABLEFormulaUnderEditLayoutTABInSalesInvoice(), true);
	}

	@Test(priority = 126)
	public void checkENABLEFormulaUnderEditLayoutTABInSalesReturn()
			throws EncryptedDocumentException, InvalidFormatException, IOException,  InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkENABLEFormulaUnderEditLayoutTABInSalesReturn(), true);
	}

	@Test(priority = 127)
	public void checkExcludeReportUnderMiscellenious()
			throws EncryptedDocumentException, InvalidFormatException, IOException,  InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkExcludeReportUnderMiscellenious(), true);
	}

	
	@Test(priority = 129)
	public void checkRepostAllVoucherFromRepostScreen()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkRepostAllVoucherFromRepostScreen(), true);
	}

	@Test(priority = 130)
	public void checkAfterRepostInSalesReturn()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkAfterRepostInSalesReturn(), true);
	}

	@Test(priority = 131) // Not Excluding Voucher Issue.
	public void checkSalesInvoiceAfterRepostWithExcludeVoucher()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSalesInvoiceAfterRepostWithExcludeVoucher(), true);
	}
	
	

	@Test(priority = 150)
	public void checkSavingVoucherInMRNWithUpdateFA()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingVoucherInMRNWithUpdateFA(), true);
	}

	
	@Test(priority = 151)
	public void checkConvertOptionsinMRNWithUpdateFA()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkConvertOptionsinMRNWithUpdateFA(), true);
	}

	@Test(priority = 152)
	public void checkValidatingMRNVoucherAfterChangesMadeInPV()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkValidatingMRNVoucherAfterChangesMadeInPV(), true);
	}

	

	// AR_AP --Accounting TAg Restricted Scenario

	@Test(priority = 201)
	public void checkDisabelARAPTagsAndEnableAccountTAGUnderSetting()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkDisabelARAPTagsAndEnableAccountTAGUnderSetting(), true);
	}

	
	@Test(priority = 202)
	public void checkAddUserRestrictUnderCreateUserScreen()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkAddUserRestrictUnderCreateUserScreen(), true);
	}

	
	@Test(priority = 203)
	public void checkSavingSalesInvoiceVATWithThreeCustomers()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException 
	{
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingSalesInvoiceVATWithThreeCustomers(), true);
	}
	

	@Test(priority = 204)
	public void checkLogoutAndLoginToUserLevel()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkLogoutAndLoginToUserLevel(), true);
	}

	
	
	@Test(priority = 205)
	public void checkReceiptsVATVoucherWithDepInput()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkReceiptsVATVoucherWithDepInput(), true);
	}

	@Test(priority = 206)
	public void checkRecepictsVATWithDepDUBAI()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkRecepictsVATWithDepDUBAI(), true);
	}

	@Test(priority = 207)
	public void checkRecepictsVATWithDepAmerica()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkRecepictsVATWithDepAmerica(), true);
	}

	@Test(priority = 210)
	public void checkBillRefenceScreenWithDepartmentFilter()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkBillRefenceScreenWithDepartmentFilter(), true);
	}

	@Test(priority = 211)
	public void checkEnableTagsAsWareHouseUnderreference()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkEnableTagsAsWareHouseUnderreference(), true);
	}

	
	@Test(priority = 212)
	public void checkSavingSalesInvoiceVATAfterEnableAPARTags()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException 
	{
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingSalesInvoiceVATAfterEnableAPARTags(), true);
	}

	
	@Test(priority = 214)
	public void checkBillwiseScreenAfterEnableAndSaving()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException 
	{
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkBillwiseScreenAfterEnableAndSaving(), true);
	}
	
	
	
	// Newly Added Scenarios in Due Bills in Bill wise screen
	
	
	@Test(priority = 300)
	public void checkSavingOpeningBalanceVOucherWithDebitOverDueOfCustomerA()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingOpeningBalanceVOucherWithDebitOverDueOfCustomerA(), true);
	}
	
	
	@Test(priority = 301)
	public void checkAdjustingInReceipctsVATFilterOnDate()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkAdjustingInReceipctsVATFilterOnDate(), true);
	}
	
	@Test(priority = 302)
	public void checkSavingVoucherInReceipctsWithBillwiseOverDueBills()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingVoucherInReceipctsWithBillwiseOverDueBills(), true);
	}
	
	
	@Test(priority = 303)
	public void checkSavingReceipctsVoucherWithOverDue()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingReceipctsVoucherWithOverDue(), true);
	}
	
	
	@Test(priority = 304)
	public void checkSavingReceipctsFIlterDueDateAndAutoAdjustOnFIFO()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkSavingReceipctsFIlterDueDateAndAutoAdjustOnFIFO(), true);
	}
	
	
	
	

}
