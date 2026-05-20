package com.focus.testcases;

import java.awt.AWTException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.focus.Pages.CreditPage;
import com.focus.base.BaseEngine;

public class CreditTest extends BaseEngine {

	static CreditPage cp;

	@Test(priority = 95)
	public void checkLogin()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkLogin(), true);
	}
	

	
	@Test(priority = 96)
	public void checkRestoreCreditBackUp()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkRestoreCreditBackUp(), true);
	}

	
	@Test(priority = 97,enabled = false)
	public void checkErasingAllDataBefore()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkErasingAllData(), true);
	}

	@Test(priority = 98)
	public void chekARAPScreenUnderSettingsOption()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.chekARAPScreenUnderSettingsOption(), true);

	}

	@Test(priority = 99)
	public void checkTagsInARAP()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkTagsInARAP(), true);

	}

	@Test(priority = 100)
	public void checkCreditLimitOptionsAndAssigningCreditLimitToCustomer()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkCreditLimitOptionsAndAssigningCreditLimitToCustomer(), true);

	}
	

	@Test(priority = 101)
	public void checkStopOptionInCreditLimitUnderSettings()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkStopOptionInCreditLimitUnderSettings(), true);

	}

	@Test(priority = 102)
	public void checkSavingOpeningBalanceVoucherWithHalfCreditLimit()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkSavingOpeningBalanceVoucherWithHalfCreditLimit(), true);

	}

	@Test(priority = 103)
	public void checkEnteringDATAInsalesINvoiceAfterSavingOpeningBalance()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkEnteringDATAInsalesINvoiceAfterSavingOpeningBalance(), true);

	}

	@Test(priority = 104)
	public void checkEnableOverDueCheckUnderARAPinSettings()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkEnableOverDueCheckUnderARAPinSettings(), true);

	}

	@Test(priority = 105)
	public void checkAddingCreditDaysATAccountThroughShortCutAndCheckingChangesInDueDate()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkAddingCreditDaysATAccountThroughShortCutAndCheckingChangesInDueDate(), true);

	}
	
	
	@Test(priority = 106)//stock negative message
	public void checkRaisingSalesInvoiceVoucherMoreTahnCreditlimit()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkRaisingSalesInvoiceVoucherMoreTahnCreditlimit(), true);

	}
	
	

	@Test(priority = 107)
	public void checkAdjustingSalesinRecepitsVoucher()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkAdjustingSalesinRecepitsVoucher(), true);

	}

	@Test(priority = 108)//doubt
	public void checkResavingSalesInvoiceVoucherAfterConsuming()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkResavingSalesInvoiceVoucherAfterConsuming(), true);

	}

	@Test(priority = 109)
	public void checkResavingRecepitsVoucher()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkResavingRecepitsVoucher(), true);
	}

	@Test(priority = 110)
	public void checkResavingSalesInvoiceVoucherAfterConsumingLesserThanCreditLimit()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkResavingSalesInvoiceVoucherAfterConsumingLesserThanCreditLimit(), true);

	}

	@Test(priority = 111)
	public void checkEntringDateAfterDateOF1stVoucherInSalesInvoice()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException 
	{
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkEntringDateAfterDateOF1stVoucherInSalesInvoice(), true);

	}
	

	

	// credit limit Authorisation

	@Test(priority = 150)
	public void checkEnableCreditLimitOptionUnserPerferences()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkEnableCreditLimitOptionUnserPerferences(), true);

	}

// Back Up on desktop

	@Test(priority = 151)
	public void checkcreditlimitAuthorisationHomeScreen()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkcreditlimitAuthorisationHomeScreen(), true);

	}

	@Test(priority = 152)
	public void checkSavingCreditLimitAuthorisationWithOutInput()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkSavingCreditLimitAuthorisationWithOutInput(), true);

	}

	@Test(priority = 153)
	public void checkSavingCreditLimitAuthorisationWithNameInput()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkSavingCreditLimitAuthorisationWithNameInput(), true);
	}

	@Test(priority = 154)
	public void checkSavingCreditLimitAuthorisationWithVaildDataWithoutCreditDaysForApprovalInLevel1()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkSavingCreditLimitAuthorisationWithVaildDataWithoutCreditDaysForApprovalInLevel1(),
				true);
	}

	@Test(priority = 155)
	public void checkSavingWithEnteringCreditDays()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkSavingWithEnteringCreditDays(), true);
	}

	@Test(priority = 156)
	public void checkSavedCreditLimitAuthorisation()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkSavedCreditLimitAuthorisation(), true);
	}

	@Test(priority = 157)
	public void checkEditoptionInCreditLimit()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkEditoptionInCreditLimit(), true);
	}

	@Test(priority = 158)
	public void checkDeleteOptionInCreditLimitAuthorisation()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkDeleteOptionInCreditLimitAuthorisation(), true);
	}
	

	@Test(priority = 159)
	public void checkResavingCreditLimitAuthorisation()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkResavingCreditLimitAuthorisation(), true);
	}

	@Test(priority = 161)
	public void checkMappingDepartmentAndAccountInMapctreditLimitScreen()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkMappingDepartmentAndAccountInMapctreditLimitScreen(), true);
	}

	@Test(priority = 200, enabled = false)
	public void checkEnableOnlyARTagInPreferences()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkEnableOnlyARTagInPreferences(), true);
	}

	
	@Test(priority = 201)
	public void checkSaveCustomerOrVendorAccountForVouchers()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkSaveCustomerOrVendorAccountForVouchers(), true);
	}

	@Test(priority = 202)
	public void checkSavedAccountInMastersList()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkSavedAccountInMastersList(), true);
	}
	
	
	
	
	
	@Test(priority = 203)// Error Message 
	public void checkSavePurchaseReturnsVoucherWithSavedCustOrVenAccount()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkSavePurchaseReturnsVoucherWithSavedCustOrVenAccount(), true);
	}

	@Test(priority = 204)
	public void checkSaveCreditNoteVoucherWithSavedCustomerOrVendorAccount()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkSaveCreditNoteVoucherWithSavedCustomerOrVendorAccount(), true);
	}

	@Test(priority = 205)
	public void checkSaveDebitNotesVoucherWithSavedCustOrVenAccount()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkSaveDebitNotesVoucherWithSavedCustOrVenAccount(), true);
	}

	@Test(priority = 206)
	public void checkSaveSalesReturnsVoucher()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkSaveSalesReturnsVoucher(), true);
	}

	@Test(priority = 208)
	public void checkSavePurchaseVoucherWithCreatedCustOrVenAccount()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkSavePurchaseVoucherWithCreatedCustOrVenAccount(), true);
	}

	@Test(priority = 209)//error message
	public void checkSalesInvoiceVoucherWithCreatedCustOrVenAccount()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkSalesInvoiceVoucherWithCreatedCustOrVenAccount(), true);
	}

	@Test(priority = 210)
	public void checkSavePurchaseVoucherWithRMANumbersFromExcel()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkSavePurchaseVoucherWithRMANumbersFromExcel(), true);
	}

	@Test(priority = 211)
	public void checkCopyToClipandPasteBoardPreviouslySavedPurVoucherWithRmaAndSaveWithSameRma() throws Exception {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkCopyToClipandPasteBoardPreviouslySavedPurVoucherWithRmaAndSaveWithSameRmaUpdate(),
				true);
	}

	@Test(priority = 212) // Check Excel Once
	public void checkSaveSalesInvoicesVoucherWithRMAFromExcel()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkSaveSalesInvoicesVoucherWithRMAFromExcel(), true);
	}

	// @Test(priority=213)//no need
	public void checkCopyToClipandPasteBoardPreviouslySavedSalesVoucherWithRmaAndSaveWithSameRma()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkCopyToClipandPasteBoardPreviouslySavedSalesVoucherWithRmaAndSaveWithSameRma(),
				true);
	}

	
	@Test(priority = 214)
	public void checkErasingAllDataAfterExcel()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkErasingAllData(), true);
	}

	@Test(priority = 215)
	public void checkSavePurchaseVoucherWithRmaFromNotePad()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkSavePurchaseVoucherWithRmaFromNotePad(), true);
	}

	@Test(priority = 216) 
	public void AcheckCopyToClipBoardFromSavedPurchaseVoucherWithRmaFromNotePad()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkCopyToClipBoardFromSavedPurchaseVoucherWithRmaFromNotePad(), true);
	}
	

	@Test(priority = 217)
	public void BcheckCopyToClipboardWithNotePAD()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkCopyToClipboardWithNotePAD(), true);
	}

	@Test(priority = 218)//error message
	public void checkSaveSalesInvoiceVoucherWithRmaFromNotePad()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkSaveSalesInvoiceVoucherWithRmaFromNotePad(), true);
	}

	// @Test(priority=217)//no need
	public void checkCopyToClipandPasteBoardPreviouslySavedSalesVoucherWithRmaAndSaveWithSameRmaFromNotepad()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(
				cp.checkCopyToClipandPasteBoardPreviouslySavedSalesVoucherWithRmaAndSaveWithSameRmaFromNotepad(), true);
	}

	@Test(priority = 219)
	public void checkErasingAllData()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkErasingAllData(), true);
	}

	@Test(priority = 220)
	public void checkSavePurchaseVoucherWithRMANumbersFromExcelAfterEraseAll()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkSavePurchaseVoucherWithRMANumbersFromExcelAfterEraseAll(), true);
	}

	@Test(priority = 221)
	public void checkAddingQuantityAndNetFromTransactionFieldsInSalesOrderVoucher()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkAddingQuantityAndNetFromTransactionFieldsInSalesOrderVoucher(), true);
	}
	

	@Test(priority = 222)
	public void checkSaveSalesOrderVoucher()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkSaveSalesOrderVoucher(), true);
	}

	@Test(priority = 223)
	public void checkValidateQuantityAndNetValuesInTheSalesOrderVoucherHomePage()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkValidateQuantityAndNetValuesInTheSalesOrderVoucherHomePage(), true);
	}

	@Test(priority = 224)
	public void checkSaveSalesInvoiceVatVoucherByConsumingPartialQuantity()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkSaveSalesInvoiceVatVoucherByConsumingPartialQuantity(), true);
	}

	@Test(priority = 225)
	public void checkErasingAllTransactions()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkErasingAllData(), true);
	}

	@Test(priority = 226)
	public void checkSavePurchaseVoucher()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkSavePurchaseVoucherWithRMANumbersFromExcelAfterEraseAll(), true);
	}

	@Test(priority = 227)
	public void checkSaveSalesOrderWithPartialQntyConsumptionFromPVoucher()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkSaveSalesOrderWithPartialQntyConsumptionFromPVoucher(), true);
	}

	@Test(priority = 228)
	public void checkSalesOrderVoucherHomeandValidateQtyAndNetValues()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkSalesOrderVoucherHomeandValidateQtyAndNetValues(), true);
	}

	@Test(priority = 229)
	public void checkSaveSalesInvVatandConsumeQtyPartiallyFromPartiallyConsumedSalesOrder()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkSaveSalesInvVatandConsumeQtyPartiallyFromPartiallyConsumedSalesOrder(), true);
	}

	
	@Test(priority = 230)
	public void checkEnableARAPForCreditlimit()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkEnableARAPForCreditlimit(), true);
	}

	
	@Test(priority = 231)
	public void checkAssigingCreditlimitToCustomerAccountGroup()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkAssigingCreditlimitToCustomerAccountGroup(), true);
	}

	@Test(priority = 232)
	public void checkSavingSalesinvoiceVATWithCustomerUpdateGroupAccountWithHigherCreditValue()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkSavingSalesinvoiceVATWithCustomerUpdateGroupAccountWithHigherCreditValue(), true);
	}
	
	

	@Test(priority = 233)
	public void checkSavingSalesinvoiceVATWithCustomerUpdateGroupAccountWithLowerCreditValue()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkSavingSalesinvoiceVATWithCustomerUpdateGroupAccountWithLowerCreditValue(), true);
	}

	@Test(priority = 234)
	public void checkcreditAuthorizationreportBeforeApproval()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkcreditAuthorizationreportBeforeApproval(), true);
	}

	@Test(priority = 235)
	public void checkUserLevelAuthorizatioApprovalScreen()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkUserLevelAuthorizatioApprovalScreen(), true);
	}

	@Test(priority=236)
	public void checkReportAtUserLevel()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException 
	{
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkReportAtUserLevel(), true);
	}

	 @Test(priority=237)
	public void checkAuthorizationreportATSuperUserLevelAfterAuthorise()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkAuthorizationreportATSuperUserLevelAfterAuthorise(), true);
	}

	
	// New Scenarios added on Default Currency

	@Test(priority = 300)
	public void checkARandApOptionsUnderSetting()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkARandApOptionsUnderSetting(), true);
	}

	@Test(priority = 301)
	public void checkDefiningDefaultCurrencyForAccount()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkDefiningDefaultCurrencyForAccount(), true);
	}

	@Test(priority = 302)
	public void checkAddDefualtyCurrencyOfDepMaster()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkAddDefualtyCurrencyOfDepMaster(), true);
	}

	@Test(priority = 303)
	public void checkValidationOIfDefaultCurrenyAndSavingInSalesInvoice()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkValidationOIfDefaultCurrenyAndSavingInSalesInvoice(), true);
	}

	@Test(priority = 304)
	public void checkValidationAndSavingVoucherInPVVAT()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkValidationAndSavingVoucherInPVVAT(), true);
	}

	@Test(priority = 309)
	public void checkSavingDefaultcurrecnyAndSavingPaymentsVoucher()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkSavingDefaultcurrecnyAndSavingPaymentsVoucher(), true);
	}

	@Test(priority = 310)
	public void checkSavingDefaultcurrecnyAndSavingRecepictsVoucher()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkSavingDefaultcurrecnyAndSavingRecepictsVoucher(), true);
	}

	
	
	@Test(priority = 320)
	public void checkSavingSalesInvoiceVOucherWithCustomerVendorAccOfDepAmerica()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkSavingSalesInvoiceVOucherWithCustomerVendorAccOfDepAmerica(), true);
	}

	@Test(priority = 321)
	public void checkSavingSalesInvoiceVOucherWithCustomerVendorAccOfDepDubai()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkSavingSalesInvoiceVOucherWithCustomerVendorAccOfDepDubai(), true);
	}

	@Test(priority = 322)
	public void checkEnableSelectBillBeforeAmtInReceipcts()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkEnableSelectBillBeforeAmtInReceipcts(), true);
	}

	@Test(priority = 323)
	public void checkBillRefAndSaveVoucherInReceipcts()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkBillRefAndSaveVoucherInReceipcts(), true);
	}

	@Test(priority = 324)
	public void checkSavingReceipctsWithNewReference()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkSavingReceipctsWithNewReference(), true);
	}

//	@Test(priority = 325)
	public void checkEditingVoucherInReceipcts()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkEditingVoucherInReceipcts(), true);
	}
	
	
	
	@Test(priority = 350)
	public void checkSavingSalesReturnWithDUBAI()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkSavingSalesReturnWithDUBAI(), true);
	}
	
	@Test(priority = 351)
	public void checkSavingSalesreturnWithAmerica()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkSavingSalesreturnWithAmerica(), true);
	}
	
	

	@Test(priority = 356)
	public void checkDisableAROptionnderARAP()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkDisableAROptionnderARAP(), true);
	}
	
	
	
	@Test(priority = 357)
	public void checkSavingSalesReturnAfterDisableOption()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		cp = new CreditPage(getDriver());
		Assert.assertEquals(cp.checkSavingSalesReturnAfterDisableOption(), true);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
