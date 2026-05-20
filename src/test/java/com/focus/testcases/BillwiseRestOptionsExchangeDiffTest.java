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

public class BillwiseRestOptionsExchangeDiffTest extends BaseEngine
{
	
	
	static BillwiseRestOptions BROP;
	static BillWisePage bp;

	
	
	@Test(priority = 130)
	public void checkLogin()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		BROP = new BillwiseRestOptions(getDriver());
		Assert.assertEquals(BROP.checkLogin(), true);
	}
	
	
	@Test(priority = 131)
	public void restoreCompany2()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException 
	{
		bp = new BillWisePage(getDriver());
		bp.restoreCompany();// Method in Bill wise Page
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
