package com.focus.testcases;

import java.awt.AWTException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.focus.Pages.BillWiseWithOptionsPage;
import com.focus.base.BaseEngine;

public class BillWiseWithOptionsTest1 extends BaseEngine{
	

		static BillWiseWithOptionsPage bwo;

		@Test(priority = 64)
		public void checkLogin()
				throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
			bwo = new BillWiseWithOptionsPage(getDriver());
			Assert.assertEquals(bwo.checkLogin(), true);
		}
		

		@Test(priority = 65)
		public void restoreCompany()
				throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
			bwo = new BillWiseWithOptionsPage(getDriver());
			bwo.restoreCompany();
		}
		
		@Test(priority = 66)
		public void checkEditingCompany()
				throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
			bwo = new BillWiseWithOptionsPage(getDriver());
			Assert.assertEquals(bwo.checkEditingCompany(), true);
		}

		@Test(priority = 67)
		public void checkEditScreenAfterLogin()
				throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
			bwo = new BillWiseWithOptionsPage(getDriver());
			Assert.assertEquals(bwo.checkEditScreenAfterLogin(), true);
		}

		@Test(priority = 67, enabled = false)
		public void checkLoginToCompanyAfterEditingCompanyAndImportingCurrency()
				throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
			bwo = new BillWiseWithOptionsPage(getDriver());
			Assert.assertEquals(bwo.checkLoginToCompanyAfterEditingCompanyAndImportingCurrency(), true);
		}
		
		

		@Test(priority = 67)
		public void checkSavingPurchaseVoucher()
				throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
			bwo = new BillWiseWithOptionsPage(getDriver());
			Assert.assertEquals(bwo.checkSavingPurchaseVoucher(), true);
		}

		
		@Test(priority = 68)
		public void checkPendingVouchersInPaymentVATAndSavingVoucher()
				throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
			bwo = new BillWiseWithOptionsPage(getDriver());
			Assert.assertEquals(bwo.checkPendingVouchersInPaymentVATAndSavingVoucher(), true);
		}

		@Test(priority = 69)
		public void checksavedVoucherInPaymnetsVTAWithPendingLinks()
				throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {
			bwo = new BillWiseWithOptionsPage(getDriver());
			Assert.assertEquals(bwo.checksavedVoucherInPaymnetsVTAWithPendingLinks(), true);
		}
		

		@Test(priority = 70)
		public void AcheckRevertoptionsInPaymentsVAT()
				throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {
			bwo = new BillWiseWithOptionsPage(getDriver());
			Assert.assertEquals(bwo.checkRevertoptionsInPaymentsVAT(), true);
		}
		

		@Test(priority = 71)
		public void chekSaveAndSuspnedOptionsInPaymentsVAT()
				throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {
			bwo = new BillWiseWithOptionsPage(getDriver());
			Assert.assertEquals(bwo.chekSaveAndSuspnedOptionsInPaymentsVAT(), true);
		}
		

		
		// Auto adjust on FIFO with Receipt

		@Test(priority = 72)
		public void checkSavingSalesInvoiceVat1stVoucher()
				throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
			bwo = new BillWiseWithOptionsPage(getDriver());
			Assert.assertEquals(bwo.checkSavingSalesInvoiceVat1stVoucher(), true);
		}
		

		@Test(priority = 73)
		public void checkSavingRecepitsFIFOWithAutoadjustFIFOOptionEnable()
				throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
			bwo = new BillWiseWithOptionsPage(getDriver());
			Assert.assertEquals(bwo.checkSavingRecepitsFIFOWithAutoadjustFIFOOptionEnable(), true);
		}

		@Test(priority = 74)
		public void checkSavedVoucherInRecepitsFIFO()
				throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
			bwo = new BillWiseWithOptionsPage(getDriver());
			Assert.assertEquals(bwo.checkSavedVoucherInRecepitsFIFO(), true);
		}
		

		@Test(priority = 75)
		public void checkSavingSalesInvoiceVatw2ndVoucher()
				throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
			bwo = new BillWiseWithOptionsPage(getDriver());
			Assert.assertEquals(bwo.checkSavingSalesInvoiceVatw2ndVoucher(), true);
		}

		
		@Test(priority = 76)
		public void checkSavingRecepitsFIFO2WithAutoadjustFIFOOptionEnable()
				throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
			bwo = new BillWiseWithOptionsPage(getDriver());
			Assert.assertEquals(bwo.checkSavingRecepitsFIFO2WithAutoadjustFIFOOptionEnable(), true);
		}

		@Test(priority = 77)
		public void checkSavedVoucherInRecepitsFIFOAdjustedWithLesserAmountThanInSalesInvoice()
				throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
			bwo = new BillWiseWithOptionsPage(getDriver());
			Assert.assertEquals(bwo.checkSavedVoucherInRecepitsFIFOAdjustedWithLesserAmountThanInSalesInvoice(), true);
		}

		
		
		
		@Test(priority = 78)
		public void checkSavingSalesInvoiceVatw3rdVoucher()
				throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
			bwo = new BillWiseWithOptionsPage(getDriver());
			Assert.assertEquals(bwo.checkSavingSalesInvoiceVatw3rdVoucher(), true);
		}

		
		@Test(priority = 79)
		public void checkSavingRecepitsFIFO3WithAutoadjustFIFOOptionEnable()
				throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
			bwo = new BillWiseWithOptionsPage(getDriver());
			Assert.assertEquals(bwo.checkSavingRecepitsFIFO3WithAutoadjustFIFOOptionEnable(), true);
		}

		@Test(priority = 80)
		public void checkSavedVoucherInRecepitsFIFOAdjustedWithGreaterThanSalesInvoice()
				throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
			bwo = new BillWiseWithOptionsPage(getDriver());
			Assert.assertEquals(bwo.checkSavedVoucherInRecepitsFIFOAdjustedWithGreaterThanSalesInvoice(), true);
		}
		
		

		@Test(priority = 81)
		public void checkSavingRecepitsVATWithCustomerDisplayCDForEachAccountThree()
				throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
			bwo = new BillWiseWithOptionsPage(getDriver());
			Assert.assertEquals(bwo.checkSavingRecepitsVATWithCustomerDisplayCDForEachAccountThree(), true);
		}

		
		@Test(priority = 82,enabled = false)
		public void checkCustomerAgeingSummaryAnalysisReportWithReceivablesOnlyOption()
				throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
			bwo = new BillWiseWithOptionsPage(getDriver());
			Assert.assertEquals(bwo.checkCustomerAgeingSummaryAnalysisReportWithReceivablesOnlyOption(), true);
		}

		@Test(priority = 83,enabled = false)
		public void checkCustomerAgeingSummaryAnalysisReportWithPayablesOnlyOption()
				throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
			bwo = new BillWiseWithOptionsPage(getDriver());
			Assert.assertEquals(bwo.checkCustomerAgeingSummaryAnalysisReportWithPayablesOnlyOption(), true);
		}

		@Test(priority = 84,enabled = false)
		public void checkCustomerAgeingSummaryAnalysisReportWithBothOption()
				throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
			bwo = new BillWiseWithOptionsPage(getDriver());
			Assert.assertEquals(bwo.checkCustomerAgeingSummaryAnalysisReportWithBothOption(), true);
		}

		@Test(priority = 89)
		public void checkDeletionOfRecepitsVAT()
				throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
			bwo = new BillWiseWithOptionsPage(getDriver());
			Assert.assertEquals(bwo.checkDeletionOfRecepitsVAT(), true);
		}

		@Test(priority = 110)
		public void checkSavingPurchaseVoucherVAT1()
				throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
			bwo = new BillWiseWithOptionsPage(getDriver());
			Assert.assertEquals(bwo.checkSavingPurchaseVoucherVAT1(), true);
		}

		
		@Test(priority = 111)
		public void checkSavingInPaymentsFIFOONAdjustingPurchaseVoucherVAT()
				throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
			bwo = new BillWiseWithOptionsPage(getDriver());
			Assert.assertEquals(bwo.checkSavingInPaymentsFIFOONAdjustingPurchaseVoucherVAT(), true);
		}

		@Test(priority = 112)
		public void checkSavedVoucherInPaymentsFIFOAdjustedWithLowerThanPVVATWithCurrencyDiffernce()
				throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
			bwo = new BillWiseWithOptionsPage(getDriver());
			Assert.assertEquals(bwo.checkSavedVoucherInPaymentsFIFOAdjustedWithLowerThanPVVATWithCurrencyDiffernce(), true);
		}

		@Test(priority = 113)
		public void checkSavingPurchaseVoucherVAT2()
				throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
			bwo = new BillWiseWithOptionsPage(getDriver());
			Assert.assertEquals(bwo.checkSavingPurchaseVoucherVAT2(), true);
		}

		@Test(priority = 114)
		public void checkSavingInPaymentsFIFOO2NAdjustingPurchaseVoucherVATWithHigher()
				throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
			bwo = new BillWiseWithOptionsPage(getDriver());
			Assert.assertEquals(bwo.checkSavingInPaymentsFIFOO2NAdjustingPurchaseVoucherVATWithHigher(), true);
		}

		@Test(priority = 115)
		public void checkSavedVoucherInPaymentsFIFOAdjustedWithHigherThanPVVATWithCurrencyDiffernce()
				throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
			bwo = new BillWiseWithOptionsPage(getDriver());
			Assert.assertEquals(bwo.checkSavedVoucherInPaymentsFIFOAdjustedWithHigherThanPVVATWithCurrencyDiffernce(),
					true);
		}


		@Test(priority = 116)
		public void checkSavingSalesReturnsByAdjustingSalesInvoice()
				throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
			bwo = new BillWiseWithOptionsPage(getDriver());
			Assert.assertEquals(bwo.checkSavingSalesReturnsByAdjustingSalesInvoice(), true);
		}
		
		@Test(priority = 117)
		public void checkSavingVoucherInOpeningBalanceWithAdjustFIFOOptionUsingSaleInvoiceVAT()
				throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
			bwo = new BillWiseWithOptionsPage(getDriver());
			Assert.assertEquals(bwo.checkSavingVoucherInOpeningBalanceWithAdjustFIFOOptionUsingSaleInvoiceVAT(), true);
		}

		@Test(priority = 118)
		public void checkSavedOpeningBALFIFOVoucherAdjustedWithRecepitsFIFO()
				throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
			bwo = new BillWiseWithOptionsPage(getDriver());
			Assert.assertEquals(bwo.checkSavedOpeningBALFIFOVoucherAdjustedWithRecepitsFIFO(), true);

		}
		
		@Test(priority = 119)
		public void checkSavingPurchaseVoucherVATWithVendorFullAdjustment()
				throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
			bwo = new BillWiseWithOptionsPage(getDriver());
			Assert.assertEquals(bwo.checkSavingPurchaseVoucherVATWithVendorFullAdjustment(), true);
		}

		@Test(priority = 120)
		public void checkSavingJVFIFOWithAdjustmentPurchaseVoucherVAT()
				throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
			bwo = new BillWiseWithOptionsPage(getDriver());
			Assert.assertEquals(bwo.checkSavingJVFIFOWithAdjustmentPurchaseVoucherVAT(), true);
		}

		@Test(priority = 121)
		public void checkSavedCoucherInJVFIFOWithLOwerThanAdjustedBill()
				throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
			bwo = new BillWiseWithOptionsPage(getDriver());
			Assert.assertEquals(bwo.checkSavedCoucherInJVFIFOWithLOwerThanAdjustedBill(), true);

		}

		@Test(priority = 122)
		public void checkSavingJVFIFOWithHigherAdjustAmount()
				throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
			bwo = new BillWiseWithOptionsPage(getDriver());
			Assert.assertEquals(bwo.checkSavingJVFIFOWithHigherAdjustAmount(), true);
		}

		@Test(priority = 123)
		public void checkSavedVoucherInJVFIFOWithHigherThanAdjustedBill()
				throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
			bwo = new BillWiseWithOptionsPage(getDriver());
			Assert.assertEquals(bwo.checkSavedVoucherInJVFIFOWithHigherThanAdjustedBill(), true);
		}

		@Test(priority = 124)
		public void checkPostingDetailsInJVFIFO()
				throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
			bwo = new BillWiseWithOptionsPage(getDriver());
			Assert.assertEquals(bwo.checkPostingDetailsInJVFIFO(), true);
		}

		
		
		@Test(priority = 125)
		public void checkSavingJVFIFOWithCreditAccountCustomer()
				throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
			bwo = new BillWiseWithOptionsPage(getDriver());
			Assert.assertEquals(bwo.checkSavingJVFIFOWithCreditAccountCustomer(), true);
		}

		@Test(priority = 126)
		public void checkSavedVoucherWithcreditAndPostingDetails()
				throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
			bwo = new BillWiseWithOptionsPage(getDriver());
			Assert.assertEquals(bwo.checkSavedVoucherWithcreditAndPostingDetails(), true);
		}

		
		
		@Test(priority = 127)
		public void checkSavingVoucherWithPDPFIFOWithAdjustmentOfPurchaseVoucherVAT()
				throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
			bwo = new BillWiseWithOptionsPage(getDriver());
			Assert.assertEquals(bwo.checkSavingVoucherWithPDPFIFOWithAdjustmentOfPurchaseVoucherVAT(), true);
		}

		@Test(priority = 128)
		public void checkSavedVoucherInPDPFIFO()
				throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
			bwo = new BillWiseWithOptionsPage(getDriver());
			Assert.assertEquals(bwo.checkSavedVoucherInPDPFIFO(), true);
		}

		@Test(priority = 129)
		public void checkSavingVoucherInDebitNote()
				throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
			bwo = new BillWiseWithOptionsPage(getDriver());
			Assert.assertEquals(bwo.checkSavingVoucherInDebitNote(), true);
		}

		@Test(priority = 130)
		public void checkSavingVoucherInPDRFIFO()
				throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
			bwo = new BillWiseWithOptionsPage(getDriver());
			Assert.assertEquals(bwo.checkSavingVoucherInPDRFIFO(), true);
		}

		@Test(priority = 131)
		public void checkSavedVoucherINPDRFIFOWithDebitNoteVAT()
				throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
			bwo = new BillWiseWithOptionsPage(getDriver());
			Assert.assertEquals(bwo.checkSavedVoucherINPDRFIFOWithDebitNoteVAT(), true);
		}

		@Test(priority = 132)
		public void checkSavingVoucherINPDRFIFOWithHigherAmt()
				throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
			bwo = new BillWiseWithOptionsPage(getDriver());
			Assert.assertEquals(bwo.checkSavingVoucherINPDRFIFOWithHigherAmt(), true);
		}

		
		@Test(priority = 135)
		public void checkSavingVoucherInDebitNoteToAdjustInPettyCashFIFO()
				throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
			bwo = new BillWiseWithOptionsPage(getDriver());
			Assert.assertEquals(bwo.checkSavingVoucherInDebitNoteToAdjustInPettyCashFIFO(), true);
		}

		@Test(priority = 136)
		public void checkSavingVouceherInPettyCashFIFO()
				throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
			bwo = new BillWiseWithOptionsPage(getDriver());
			Assert.assertEquals(bwo.checkSavingVouceherInPettyCashFIFO(), true);
		}

		@Test(priority = 137)
		public void checkSavedVoucherInPettyFifoAndEditOptions()
				throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
			bwo = new BillWiseWithOptionsPage(getDriver());
			Assert.assertEquals(bwo.checkSavedVoucherInPettyFifoAndEditOptions(), true);
		}

		@Test(priority = 138)
		public void checkSavedVoucherAfterEditingInPettyCashFifo()
				throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
			bwo = new BillWiseWithOptionsPage(getDriver());
			Assert.assertEquals(bwo.checkSavedVoucherAfterEditingInPettyCashFifo(), true);
		}

		@Test(priority = 139)
		public void checkDeletingBaseDocumentDebitNoteAfterAdjusting()
				throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
			bwo = new BillWiseWithOptionsPage(getDriver());
			Assert.assertEquals(bwo.checkDeletingBaseDocumentDebitNoteAfterAdjusting(), true);
		}

		//
		

		@Test(priority = 140)
		public void checkSavingVoucherInPurchaseVoucherVAT4()
				throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
			bwo = new BillWiseWithOptionsPage(getDriver());
			Assert.assertEquals(bwo.checkSavingVoucherInPurchaseVoucherVAT4(), true);
		}

		@Test(priority = 141)
		public void checkSavingVoucherInDepartmentalFIFOVoucher()
				throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
			bwo = new BillWiseWithOptionsPage(getDriver());
			Assert.assertEquals(bwo.checkSavingVoucherInDepartmentalFIFOVoucher(), true);
		}

		@Test(priority = 142)
		public void checkSavedVoucherInDepFIFOVoucher()
				throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
			bwo = new BillWiseWithOptionsPage(getDriver());
			Assert.assertEquals(bwo.checkSavedVoucherInDepFIFOVoucher(), true);
		}

		@Test(priority = 143)
		public void checkPurchaseVoucherVATBillwiseScreenAfterAdjustedInDepFifoVoucher()
				throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
			bwo = new BillWiseWithOptionsPage(getDriver());
			Assert.assertEquals(bwo.checkPurchaseVoucherVATBillwiseScreenAfterAdjustedInDepFifoVoucher(), true);
		}

		
		@Test(priority = 144)
		public void checkSalesInvoiceVATVoucherForNonStandardJVFifo()
				throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
			bwo = new BillWiseWithOptionsPage(getDriver());
			Assert.assertEquals(bwo.checkSalesInvoiceVATVoucherForNonStandardJVFifo(), true);
		}
		
		

		@Test(priority = 145)
		public void checkSavingVoucherInNonJVFIFOVoucher()
				throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
			bwo = new BillWiseWithOptionsPage(getDriver());
			Assert.assertEquals(bwo.checkSavingVoucherInNonJVFIFOVoucher(), true);
		}

		@Test(priority = 146)
		public void checkSavedVoucherInNonStandardFIFOVoucher()
				throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
			bwo = new BillWiseWithOptionsPage(getDriver());
			Assert.assertEquals(bwo.checkSavedVoucherInNonStandardFIFOVoucher(), true);
		}

		@Test(priority = 147) //
		public void checkSalesInvoiceVoucherVATBillwiseScreenAfterAdjustedInDepFifoVoucher()
				throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
			bwo = new BillWiseWithOptionsPage(getDriver());
			Assert.assertEquals(bwo.checkSalesInvoiceVoucherVATBillwiseScreenAfterAdjustedInDepFifoVoucher(), true);
		}

		@Test(priority = 150)
		public void checkSelctingOptionsSelectBillBeforeAmount()
				throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
			bwo = new BillWiseWithOptionsPage(getDriver());
			Assert.assertEquals(bwo.checkSelctingOptionsSelectBillBeforeAmount(), true);
		}

		@Test(priority = 151)
		public void checkSavingSalesInvoiceVoucher()
				throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
			bwo = new BillWiseWithOptionsPage(getDriver());
			Assert.assertEquals(bwo.checkSavingSalesInvoiceVoucher(), true);
		}

		@Test(priority = 152)
		public void checkSavingRecepitsWithAdjsutingSalesInvoiceVAT()
				throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
			bwo = new BillWiseWithOptionsPage(getDriver());
			Assert.assertEquals(bwo.checkSavingRecepitsWithAdjsutingSalesInvoiceVAT(), true);
		}

		@Test(priority = 153)
		public void checkEditOptionsInRecepits()
				throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
			bwo = new BillWiseWithOptionsPage(getDriver());
			Assert.assertEquals(bwo.checkEditOptionsInRecepits(), true);
		}

		// @Test(priority=154)--as Option Deleted
		public void checkEnableOptionInRecepitsUnderMiscelliniousTABAsAdjustmentBillsinLineWise()
				throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
			bwo = new BillWiseWithOptionsPage(getDriver());
			Assert.assertEquals(bwo.checkEnableOptionInRecepitsUnderMiscelliniousTABAsAdjustmentBillsinLineWise(), true);
		}

		// @Test(priority=155)
		public void checkSavingVoucherInRecepistAfterEnableOptionAdjusmentbillsInLineWise()
				throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
			bwo = new BillWiseWithOptionsPage(getDriver());
			Assert.assertEquals(bwo.checkSavingVoucherInRecepistAfterEnableOptionAdjusmentbillsInLineWise(), true);
		}
		
		

		@Test(priority = 156)
		public void checkSelctingOptionsSelectBillBeforeAmountInPayments()
				throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
			bwo = new BillWiseWithOptionsPage(getDriver());
			Assert.assertEquals(bwo.checkSelctingOptionsSelectBillBeforeAmountInPayments(), true);
		}

		@Test(priority = 159)
		public void checkSavingPaymentsWithAdjsutingPurchasevoucherVAT()
				throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
			bwo = new BillWiseWithOptionsPage(getDriver());
			Assert.assertEquals(bwo.checkSavingPaymentsWithAdjsutingPurchasevoucherVAT(), true);
		}


		@Test(priority = 160)
		public void checkEditOptionsInPayments()
				throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
			bwo = new BillWiseWithOptionsPage(getDriver());
			Assert.assertEquals(bwo.checkEditOptionsInPayments(), true);
			
		}


		 @Test(priority=161)
		public void checkEnableOptionInPaymentsUnderMiscelliniousTABAsAdjustmentBillsinLineWise()
				throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
			bwo = new BillWiseWithOptionsPage(getDriver());
			Assert.assertEquals(bwo.checkEnableOptionInPaymentsUnderMiscelliniousTABAsAdjustmentBillsinLineWise(), true);
		}
		
		

		 @Test(priority=162)
		public void checkSavingVoucherInPaymentsAfterEnableOptionAdjusmentbillsInLineWise()
				throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
			bwo = new BillWiseWithOptionsPage(getDriver());
			Assert.assertEquals(bwo.checkSavingVoucherInPaymentsAfterEnableOptionAdjusmentbillsInLineWise(), true);
		}

		@Test(priority = 163)
		public void checkAddingExtraFelidBillnoInPurchasevoucherVAT()
				throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
			bwo = new BillWiseWithOptionsPage(getDriver());
			Assert.assertEquals(bwo.checkAddingExtraFelidBillnoInPurchasevoucherVAT(), true);
		}

		
		@Test(priority = 164)
		public void checkSavingVoucherInPVVATAfterCreatingEditLayoutFiled()
				throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
			bwo = new BillWiseWithOptionsPage(getDriver());
			Assert.assertEquals(bwo.checkSavingVoucherInPVVATAfterCreatingEditLayoutFiled(), true);
		}
		

		 @Test(priority=165)
		public void checkSavingPaymentsVoucherWithNarration()
				throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
			bwo = new BillWiseWithOptionsPage(getDriver());
			Assert.assertEquals(bwo.checkSavingPaymentsVoucherWithNarration(), true);
		}

		

	}


