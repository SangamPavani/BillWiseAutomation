package com.focus.testcases;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.focus.Pages.BillWisePage;
import com.focus.Pages.VoucherOptionsPage;
import com.focus.base.BaseEngine;

public class VoucherOptionsTest extends BaseEngine {

	static VoucherOptionsPage vop;

	static BillWisePage bp;

	@Test(priority = 99)

	public void checkLogin()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {
		vop = new VoucherOptionsPage(getDriver());
		Assert.assertEquals(vop.checkLogin(), true);
	}

	@Test(priority = 100)
	public void checkRestoreCompany()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		vop = new VoucherOptionsPage(getDriver());
		vop.checkRestoreCompany();
	}

	@Test(priority = 101)
	public void checkDocumentTABUnderVoucherWizard() throws InterruptedException, IOException, AWTException {
		vop = new VoucherOptionsPage(getDriver());
		Assert.assertEquals(vop.checkDocumentTABUnderVoucherWizard(), true);
	}

	@Test(priority = 103)
	public void checkDisplayStatusOFDocumentTABInVoucherWizard() throws InterruptedException {
		vop = new VoucherOptionsPage(getDriver());
		Assert.assertEquals(vop.checkDisplayStatusOFDocumentTABInVoucherWizard(), true);
	}

	@Test(priority = 104)
	public void checkAccountDependencyValues() throws InterruptedException {
		vop = new VoucherOptionsPage(getDriver());
		Assert.assertEquals(vop.checkAccountDependencyValues(), true);
	}

	@Test(priority = 105)
	public void checkAccountDependencyAndClickOnUpdate()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		vop = new VoucherOptionsPage(getDriver());
		Assert.assertEquals(vop.checkAccountDependencyAndClickOnUpdate(), true);
	}

	@Test(priority = 106)
	public void checkAssgingPalceOfSupplyInAccountMaster()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		vop = new VoucherOptionsPage(getDriver());
		Assert.assertEquals(vop.checkAssgingPalceOfSupplyInAccountMaster(), true);
	}

	@Test(priority = 107)
	public void checkAccountDependencyAtSalesInvoiceVATAfterAssigningInAxccountMaster()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		vop = new VoucherOptionsPage(getDriver());
		Assert.assertEquals(vop.checkAccountDependencyAtSalesInvoiceVATAfterAssigningInAxccountMaster(), true);
	}
	
	
	@Test(priority = 108)
	public void checkItemDependencyListUnderDocumentTAB()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		vop = new VoucherOptionsPage(getDriver());
		Assert.assertEquals(vop.checkItemDependencyListUnderDocumentTAB(), true);
	}

	@Test(priority = 109)
	public void checkSavingMastertypeFieldInItemMaster()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		vop = new VoucherOptionsPage(getDriver());
		Assert.assertEquals(vop.checkSavingMastertypeFieldInItemMaster(), true);
	}

	
	@Test(priority = 110)
	public void checkAssigingItemDependecyUnderSalesInvoiceVAT()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		vop = new VoucherOptionsPage(getDriver());
		Assert.assertEquals(vop.checkAssigingItemDependecyUnderSalesInvoiceVAT(), true);
	}

	@Test(priority = 111)
	public void checkViewOptionAndMovingItemForwatdUnderViewTAB()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		vop = new VoucherOptionsPage(getDriver());
		Assert.assertEquals(vop.checkViewOptionAndMovingItemForwatdUnderViewTAB(), true);
	}

	@Test(priority = 112)
	public void checkItemSavingWithDepartmentInItemMaster()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		vop = new VoucherOptionsPage(getDriver());
		Assert.assertEquals(vop.checkItemSavingWithDepartmentInItemMaster(), true);
	}

	@Test(priority = 113)
	public void checkAssigningDepToFIFOItem()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		vop = new VoucherOptionsPage(getDriver());
		Assert.assertEquals(vop.checkAssigningDepToFIFOItem(), true);
	}

	@Test(priority = 114)
	public void checkVouherLevelValidationAfterAddingItemDependency()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		vop = new VoucherOptionsPage(getDriver());
		Assert.assertEquals(vop.checkVouherLevelValidationAfterAddingItemDependency(), true);
	}

	@Test(priority = 115)
	public void checkSalesInvoiceVATValidationDepartmentAsStockItem()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		vop = new VoucherOptionsPage(getDriver());
		Assert.assertEquals(vop.checkSalesInvoiceVATValidationDepartmentAsStockItem(), true);
	}

	@Test(priority = 116)
	public void checkSalesInvoiceVATValidationDepartmentAsRMAItemWhichIsNotAddedDepency()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		vop = new VoucherOptionsPage(getDriver());
		Assert.assertEquals(vop.checkSalesInvoiceVATValidationDepartmentAsRMAItemWhichIsNotAddedDepency(), true);
	}

	@Test(priority = 120)
	public void checkSavingWareExtraFieldUnderItemGeneralHeaderDetailsTAB()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		vop = new VoucherOptionsPage(getDriver());
		Assert.assertEquals(vop.checkSavingWareExtraFieldUnderItemGeneralHeaderDetailsTAB(), true);
	}

	@Test(priority = 121)
	public void checkItemFilterDrpDwnInSalesInvoiceVATVoucherWizard()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		vop = new VoucherOptionsPage(getDriver());
		Assert.assertEquals(vop.checkItemFilterDrpDwnInSalesInvoiceVATVoucherWizard(), true);
	}

	@Test(priority = 122)
	public void checkAssgingWarehouseToItem()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		vop = new VoucherOptionsPage(getDriver());
		Assert.assertEquals(vop.checkAssgingWarehouseToItem(), true);
	}

	@Test(priority = 123)
	public void checkAlignmentOFWarehouseInViewTAbInSalesInvoiceVAT()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		vop = new VoucherOptionsPage(getDriver());
		Assert.assertEquals(vop.checkAlignmentOFWarehouseInViewTAbInSalesInvoiceVAT(), true);
	}

	@Test(priority = 123)
	public void checkValidationAtSalesInvoiceVATvoucher()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		vop = new VoucherOptionsPage(getDriver());
		Assert.assertEquals(vop.checkValidationAtSalesInvoiceVATvoucher(), true);
	}

	@Test(priority = 124)
	public void checkSavingMasterTypeFiledInAccountMaster()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		vop = new VoucherOptionsPage(getDriver());
		Assert.assertEquals(vop.checkSavingMasterTypeFiledInAccountMaster(), true);
	}

	@Test(priority = 125)
	public void checkAssigningAccountFilterUnderSalesinvoiceVAT()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		vop = new VoucherOptionsPage(getDriver());
		Assert.assertEquals(vop.checkAssigningAccountFilterUnderSalesinvoiceVAT(), true);
	}

	@Test(priority = 130)
	public void checkSavingAccountMasterWithJurdicationAndPlaceOFSupply()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		vop = new VoucherOptionsPage(getDriver());
		Assert.assertEquals(vop.checkSavingAccountMasterWithJurdicationAndPlaceOFSupply(), true);
	}

	@Test(priority = 131)
	public void checkValidationInSalesInvoiceVAT()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		vop = new VoucherOptionsPage(getDriver());
		Assert.assertEquals(vop.checkValidationInSalesInvoiceVAT(), true);
	}

	
	@Test(priority = 140)
	public void checkEnableShowCustomerAssignedItemsunderSalesinvoiceVAT()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		vop = new VoucherOptionsPage(getDriver());
		Assert.assertEquals(vop.checkEnableShowCustomerAssignedItemsunderSalesinvoiceVAT(), true);
	}
	

	@Test(priority = 142)
	public void checkAssigingCustomersToItemInItemmaster()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		vop = new VoucherOptionsPage(getDriver());
		Assert.assertEquals(vop.checkAssigingCustomersToItemInItemmaster(), true);
	}

	@Test(priority = 143)
	public void checkValidationItemsInSalesinvoiceVAT()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		vop = new VoucherOptionsPage(getDriver());
		Assert.assertEquals(vop.checkValidationItemsInSalesinvoiceVAT(), true);
	}

	
	
	
	@Test(priority = 144)
	public void checkLogoutVoucherOptionsPage()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		vop = new VoucherOptionsPage(getDriver());
		Assert.assertEquals(vop.checkLogoutVoucherOptionsPage(), true);
	}
}
