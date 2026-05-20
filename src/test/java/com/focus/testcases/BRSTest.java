package com.focus.testcases;

import com.focus.Pages.BRSPage;
import com.focus.base.BaseEngine;
import java.awt.AWTException;
import java.io.IOException;
import java.text.ParseException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BRSTest extends BaseEngine 
{

	static BRSPage BRSP;

	// Restore BRS BAck Up in BackUps

	@Test(priority = 89)
	public void checkLogin()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		BRSP = new BRSPage(getDriver());
		Assert.assertEquals(BRSP.checkLogin(), true);
	}
	
	
	
    @Test(priority = 90)
	public void restoreCompany()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		BRSP = new BRSPage(getDriver());
		BRSP.restoreCompany();
	}

	
	@Test(priority = 91)
	public void checkBankReconciliationReport()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException 
	{
		BRSP = new BRSPage(getDriver());
		Assert.assertEquals(BRSP.checkBankReconciliationReport(), true);
	}

	@Test(priority = 92)
	public void checkBankReportOnBasisOFSortOrderWithDateAndDocnumberCombination()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		BRSP = new BRSPage(getDriver());
		Assert.assertEquals(BRSP.checkBankReportOnBasisOFSortOrderWithDateAndDocnumberCombination(), true);
	}

	@Test(priority = 93)
	public void checkBRSReportWithSortByColoumnDebitAmount()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		BRSP = new BRSPage(getDriver());
		Assert.assertEquals(BRSP.checkBRSReportWithSortByColoumnDebitAmount(), true);
	}

	@Test(priority = 94)
	public void checkBRSReportWithSortByColoumnCreditAmount()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		BRSP = new BRSPage(getDriver());
		Assert.assertEquals(BRSP.checkBRSReportWithSortByColoumnCreditAmount(), true);
	}

	@Test(priority = 95)
	public void checkBRSReportWithSelectDropdownOnCredits()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		BRSP = new BRSPage(getDriver());
		Assert.assertEquals(BRSP.checkBRSReportWithSelectDropdownOnCredits(), true);
	}

	@Test(priority = 96)
	public void checkClearenaceVoucherInBRSReport()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		BRSP = new BRSPage(getDriver());
		Assert.assertEquals(BRSP.checkClearenaceVoucherOptionInBRSReport(), true);
	}

	@Test(priority = 97)
	public void checkBRSReportWithSelectStatusAsCleared()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		BRSP = new BRSPage(getDriver());
		Assert.assertEquals(BRSP.checkBRSReportWithSelectStatusAsCleared(), true);
	}

	@Test(priority = 98)
	public void checkSelctStatusWithPedingInBRSReport()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		BRSP = new BRSPage(getDriver());
		Assert.assertEquals(BRSP.checkSelctStatusWithPedingInBRSReport(), true);
	}

	@Test(priority = 99)
	public void checkCleareanceVoucherInBRSByUsingBatchMode()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		BRSP = new BRSPage(getDriver());
		Assert.assertEquals(BRSP.checkCleareanceVoucherInBRSByUsingBatchMode(), true);
	}

	@Test(priority = 100)
	public void checkbacktrackoptionInBRSReport()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		BRSP = new BRSPage(getDriver());
		Assert.assertEquals(BRSP.checkbacktrackoptionInBRSReport(), true);
	}

	@Test(priority = 101)//doubt
	public void checkChangingClearanceDateWithSaveOptionsAsbatchMode()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		BRSP = new BRSPage(getDriver());
		Assert.assertEquals(BRSP.checkChangingClearanceDateWithSaveOptionsAsbatchMode(), true);
	}

	@Test(priority = 102)
	public void checkSavingOpeningBalaceWithBank()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		BRSP = new BRSPage(getDriver());
		Assert.assertEquals(BRSP.checkSavingOpeningBalaceWithBank(), true);
	}

	@Test(priority = 103)
	public void checkChangingClearanceDateFromAccountingDateToCurrentdate()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		BRSP = new BRSPage(getDriver());
		Assert.assertEquals(BRSP.checkChangingClearanceDateFromAccountingDateToCurrentdate(), true);
	}

	@Test(priority = 104)
	public void checkSavingRecepitsVATVoucherFromBRS()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		BRSP = new BRSPage(getDriver());
		Assert.assertEquals(BRSP.checkSavingRecepitsVATVoucherFromBRS(), true);
	}

	@Test(priority = 106)
	public void checkBRSReportWithAdvanceFilterWithAccount()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		BRSP = new BRSPage(getDriver());
		Assert.assertEquals(BRSP.checkBRSReportWithAdvanceFilterWithAccount(), true);
	}

	@Test(priority = 107)
	public void checkBankReconciliationImportReport()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		BRSP = new BRSPage(getDriver());
		Assert.assertEquals(BRSP.checkBankReconciliationImportReport(), true);
	}

	
	@Test(priority = 109)
	public void checkBRSImportAfterEnableOptionInCustomization()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		BRSP = new BRSPage(getDriver());
		Assert.assertEquals(BRSP.checkBRSImportAfterEnableOptionInCustomization(), true);
	}

	@Test(priority = 110)
	public void checkSavingRaisingPaymentsFIFOVoucherFromBRSScreen()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		BRSP = new BRSPage(getDriver());
		Assert.assertEquals(BRSP.checkSavingRaisingPaymentsFIFOVoucherFromBRSScreen(), true);
	}
	

	@Test(priority = 111)
	public void checkRaisedVoucherPaymentsVATInBRS()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		BRSP = new BRSPage(getDriver());
		Assert.assertEquals(BRSP.checkRaisedVoucherPaymentsVATInBRS(), true);
	}

	@Test(priority = 124)
	public void checkSavingPDRVATWithBeforeAccountingDate()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		BRSP = new BRSPage(getDriver());
		Assert.assertEquals(BRSP.checkSavingPDRVATWithBeforeAccountingDate(), true);
	}

	@Test(priority = 125)
	public void checkValidationInPDRVATWithCurrencyWhichIsnotDefinedInRange()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		BRSP = new BRSPage(getDriver());
		Assert.assertEquals(BRSP.checkValidationInPDRVATWithCurrencyWhichIsnotDefinedInRange(), true);
	}
	
	
	
	@Test(priority=141)
	public void checkSavingVoucherInPostDatedRecepits() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException
	{
		BRSP=new BRSPage(getDriver());
		Assert.assertEquals(BRSP.checkSavingVoucherInPostDatedRecepits(), true);	
	}
	
	
	@Test(priority=142)
	public void checkAssignPDCDiscountLimitScreen() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException
	{
		BRSP=new BRSPage(getDriver());
		Assert.assertEquals(BRSP.checkAssignPDCDiscountLimitScreen(), true);	
	}
	

	@Test(priority=143)
	public void checkSavedAssignPDCScrrenWithHDFC() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException
	{
		BRSP=new BRSPage(getDriver());
		Assert.assertEquals(BRSP.checkSavedAssignPDCScrrenWithHDFC(), true);	
	}
	
	
	
	/*
	// Changes TO DONE
	
	@Test(priority=144)
	public void checkPostingChequeDiscountingScreen() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException
	{
		BRSP=new BRSPage(getDriver());
		Assert.assertEquals(BRSP.checkPostingChequeDiscountingScreen(), true);	
	}
	
	
	@Test(priority=145)
	public void checkPostedVoucherInrtecepitsVAT() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException
	{
		BRSP=new BRSPage(getDriver());
		Assert.assertEquals(BRSP.checkPostedVoucherInrtecepitsVAT(), true);	
	}
	
	
	@Test(priority=146)
	public void checkPostIngVoucherinCOnvertedMaturedPDCScreen() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException
	{
		BRSP=new BRSPage(getDriver());
		Assert.assertEquals(BRSP.checkPostIngVoucherinCOnvertedMaturedPDCScreen(), true);	
	}
	
	@Test(priority=147)
	public void checkRecepictsVATVoucherAfterPostedByPDCConverted() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException
	{
		BRSP=new BRSPage(getDriver());
		Assert.assertEquals(BRSP.checkRecepictsVATVoucherAfterPostedByPDCConverted(), true);	
	}
	
	
	@Test(priority=148)
	public void checkPostedVoucherInRecepitsPDCConverted() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException
	{
		BRSP=new BRSPage(getDriver());
		Assert.assertEquals(BRSP.checkPostedVoucherInRecepitsPDCConverted(), true);	
	}
	
	
	
	@Test(priority = 149)
	public void checkBankReconciliationStatementReport()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		BRSP = new BRSPage(getDriver());
		Assert.assertEquals(BRSP.checkBankReconciliationStatementReport(), true);
	}
	
	*/

}
