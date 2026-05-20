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

public class BillwiseRestOptionsARAPTest extends BaseEngine{
	
	
	static BillwiseRestOptions BROP;
	static BillWisePage bp;

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

	
	

}
