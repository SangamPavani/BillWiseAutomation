package com.focus.testcases;

import static org.testng.Assert.fail;

import java.awt.AWTException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.Test;


import com.focus.Pages.AttributePage;
import com.focus.base.BaseEngine;

public class AttributeTest extends BaseEngine
{
	static AttributePage ap;	

	
	@Test(priority=10)
	public void checkLoginTOBRS() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		ap=new AttributePage(getDriver());
		Assert.assertEquals(ap.checkLoginTOBRS(), true);
	}
	
	
	
	
	@Test(priority=11)
	public void checkSavingVoucherInRecepictsVAT() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		ap=new AttributePage(getDriver());
		Assert.assertEquals(ap.checkSavingVoucherInRecepictsVAT(), true);
	}

	
	@Test(priority=12)
	public void checkSavingVocuherSalesInoiveVATAdjustingRecepictsVAT() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		ap=new AttributePage(getDriver());
		Assert.assertEquals(ap.checkSavingVocuherSalesInoiveVATAdjustingRecepictsVAT(), true);
	}
	
	
	@Test(priority=14)
	public void checkDecreaseQuantityInReciptsVATAfterConsumedInSalesInvoiceVAT() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		ap=new AttributePage(getDriver());
		Assert.assertEquals(ap.checkDecreaseQuantityInReciptsVATAfterConsumedInSalesInvoiceVAT(), true);
	}
	
	
	@Test(priority=15)
	public void checkInputAmountWithNegativeInReceiptsVAT() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		ap=new AttributePage(getDriver());
		Assert.assertEquals(ap.checkInputAmountWithNegativeInReceiptsVAT(), true);
	}
	
	
	@Test(priority=16)
	public void checkAdjustmentScreenInsalesInvoiceVATAfterChangingAmountInRecepictsVAT() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		ap=new AttributePage(getDriver());
		Assert.assertEquals(ap.checkAdjustmentScreenInsalesInvoiceVATAfterChangingAmountInRecepictsVAT(), true);
	}
	
	
	@Test(priority=17)
	public void checkSavingVoucherPaymentsVAT() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		ap=new AttributePage(getDriver());
		Assert.assertEquals(ap.checkSavingVoucherPaymentsVAT(), true);
	}
	
	
	@Test(priority=18)
	public void checkSavingpurchaseVoucherVATWithpaymentAdjustments() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		ap=new AttributePage(getDriver());
		Assert.assertEquals(ap.checkSavingpurchaseVoucherVATWithpaymentAdjustments(), true);
	}
	
	
	@Test(priority=19)
	public void checkDecreaseQuantityInPaymentsVATAfterConsumedInSalesInvoiceVAT() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		ap=new AttributePage(getDriver());
		Assert.assertEquals(ap.checkDecreaseQuantityInPaymentsVATAfterConsumedInSalesInvoiceVAT(), true);
	}
	
	
	@Test(priority=20)
	public void checkInputAmountWithNegativeInPaymentsVAT() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		ap=new AttributePage(getDriver());
		Assert.assertEquals(ap.checkInputAmountWithNegativeInPaymentsVAT(), true);
	}
	


	@Test(priority=21)
	public void checkAdjustmentScreenInPVVATVATAfterChangingAmountInPaymentsVAT() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		ap=new AttributePage(getDriver());
		Assert.assertEquals(ap.checkAdjustmentScreenInPVVATVATAfterChangingAmountInPaymentsVAT(), true);
	}
		
	@Test(priority=22)
	public void checkSavingRecepictsVATWithVendor() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		ap=new AttributePage(getDriver());
		Assert.assertEquals(ap.checkSavingRecepictsVATWithVendor(), true);
	}
	
	

	@Test(priority=23)
	public void checkSavingPaymentsAdjsutingRecepicts() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		ap=new AttributePage(getDriver());
		Assert.assertEquals(ap.checkSavingPaymentsAdjsutingRecepicts(), true);
	}
	
	

	@Test(priority=24)
	public void checkChangingAmountInReceiptsVAT() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		ap=new AttributePage(getDriver());
		Assert.assertEquals(ap.checkChangingAmountInReceiptsVAT(), true);
	}
	
	
	
	


	@Test(priority=102)
	public void checkConfigureTransactionMastersUnderSettings() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		ap=new AttributePage(getDriver());
		Assert.assertEquals(ap.checkConfigureTransactionMastersUnderSettings(), true);
	}

	@Test(priority=103)
	public static void checkItemAttributeValuesinMastersUnderSettingsConfigureTransactions() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		ap=new AttributePage(getDriver());
		Assert.assertEquals(ap.checkItemAttributeValuesinMastersUnderSettingsConfigureTransactions(), true);
	}

	@Test(priority=104)
	public void checkCloseOptionInMastersScreenUnderSettingsMenu() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		ap=new AttributePage(getDriver());
		Assert.assertEquals(ap.checkCloseOptionInMastersScreenUnderSettingsMenu(), true);	
	}

	@Test(priority=105)
	public void CheckingAttributestabinItem() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		ap=new AttributePage(getDriver());
		Assert.assertEquals(ap.CheckAttributesTabinItem(), true);
	}	


	@Test(priority=106)
	public void checkItemsAdddinginAttribute0Tab() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException{
		ap=new AttributePage(getDriver());
		Assert.assertEquals(ap.checkItemsAdddinginAttribute0Tab(), true);
	}


	@Test(priority=107)
	public void checkItemsAdddinginAttribute1Tab() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException{
		ap=new AttributePage(getDriver());
		Assert.assertEquals(ap.checkItemsAdddinginAttribute1Tab(), true);
	}

	@Test(priority=108)
	public void checkSubItemUnderFruitsItemMaster() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		ap=new AttributePage(getDriver());
		Assert.assertEquals(ap.checkSubItemUnderFruitsItemMaster(), true);
	}

	@Test(priority=109)
	public void checkItemsSavedinMasterItemforElectronicsandSubItems() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		ap=new AttributePage(getDriver());
		Assert.assertEquals(ap.checkItemsSavedinMasterItemforElectronicsandSubItems(), true);
	}

	@Test(priority=110,enabled = false)// Pronghorn Issue
	public void checktheNewBtninSubItemsofanItemFruits() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		ap=new AttributePage(getDriver());
		Assert.assertEquals(ap.checktheNewBtninSubItemsofanItemFruits(), true);
	}

	
	@Test(priority=111)
	public void checkFruits1ItemProperties() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException{
		ap=new AttributePage(getDriver());
		Assert.assertEquals(ap.checkFruits1ItemProperties(), true);
	}

	@Test(priority=112)
	public void checkElectronicsItemProperties() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException{
		ap=new AttributePage(getDriver());
		Assert.assertEquals(ap.checkElectronicsItemProperties(), true);
	}

	
	@Test(priority=113)
	public void checkLogoutAndLoginAgain() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException{
		ap=new AttributePage(getDriver());
		Assert.assertEquals(ap.checkLogoutAndLoginAgain(), true);
	}
	
	@Test(priority=121)
	public void checkPurchaseVocherVATtoAddAttributeItemFruitsasItem() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException{
		ap=new AttributePage(getDriver());
		Assert.assertEquals(ap.checkPurchaseVocherVATtoAddAttributeItemFruitsasItem(), true);
	}

	@Test(priority=122)
	public void checkPurchaseVocherVATtoAddAttributeItemElectronicsasItem() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		ap=new AttributePage(getDriver());
		Assert.assertEquals(ap.checkPurchaseVocherVATtoAddAttributeItemElectronicsasItem(), true);
	}


	
	@Test(priority=123)
	public void checkSettingsInventoryOptionsinPurchaseVATforInputItemByAttributeCheckBox() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException{
		ap=new AttributePage(getDriver());
		Assert.assertEquals(ap.checkSettingsInventoryOptionsinPurchaseVATforInputItemByAttributeCheckBox(), true);
	}
	

	@Test(priority=124)
	public void checkDisplayingofAttribute0WindowinItemsTabinPurchaseVocherVAT() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException{
		ap=new AttributePage(getDriver());
		Assert.assertEquals(ap.checkDisplayingofAttribute0WindowinItemsTabinPurchaseVocherVAT(), true);
	}
	
	

	@Test(priority=125)
	public void checkDisplayingofAttribute1WindowinItemsTabinPurchaseVocherVAT() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException{
		ap=new AttributePage(getDriver());
		Assert.assertEquals(ap.checkDisplayingofAttribute1WindowinItemsTabinPurchaseVocherVAT(), true);
	}



	@Test(priority=126)
	public void checkAddingItemSTDCOGSRateinItemWindowinPurchaseVocherVAT() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException{
		ap=new AttributePage(getDriver());
		Assert.assertEquals(ap.checkAddingItemSTDCOGSRateinItemWindowinPurchaseVocherVAT(), true);
	}


	@Test(priority=127)// Filter on Date Issue -Reported 
	public void checkDateFilterOptionInVoucherEntryPage() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException{
		ap=new AttributePage(getDriver());
		Assert.assertEquals(ap.checkDateFilterOptionInVoucherEntryPage(), true);
	}
	

	@Test(priority = 130)// Issue Not Loading
	public void checkLoadingSavedAttributesInMasterLevel()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {
		ap = new AttributePage(getDriver());
		Assert.assertEquals(ap.checkLoadingSavedAttributesInMasterLevel(), true);
	}

	
	
	// External Module
	
	@Test(priority=135)
	public void checkAddingExternalmoduleScreen() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		ap=new AttributePage(getDriver());
		Assert.assertEquals(ap.checkAddingExternalmoduleScreen(), true);
	}

	@Test(priority=136)
	public void checkEditingInExternalModules() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		ap=new AttributePage(getDriver());
		Assert.assertEquals(ap.checkEditingInExternalModules(), true);
	}


	@Test(priority=137)
	public void checkInsertingTwoRowsInExternalmodules() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		ap=new AttributePage(getDriver());
		Assert.assertEquals(ap.checkInsertingTwoRowsInExternalmodules(), true);
	}


	@Test(priority=138)
	public void checkSavedExternalModuleWithThreeRowsAndRemove() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException
	{
		ap=new AttributePage(getDriver());
		Assert.assertEquals(ap.checkSavedExternalModuleWithThreeRowsAndRemove(), true);
	}
	
	
	
	
	
	@Test(priority = 151)
	public void checkEnableOptionWithSalesinvoiceReorderWarnAndAllow()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException {
		ap = new AttributePage(getDriver());
		Assert.assertEquals(ap.checkEnableOptionWithSalesinvoiceReorderWarnAndAllow(), true);
	}

	
	@Test(priority = 152)
	public void checkEnableStopOptionInReorderUnderPerfernce()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException {
		ap = new AttributePage(getDriver());
		Assert.assertEquals(ap.checkEnableStopOptionInReorderUnderPerfernce(), true);
	}

	
	@Test(priority = 153)
	public void checkSavingSalesInvoiceVARWithEnableOption()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException, AWTException {
		ap = new AttributePage(getDriver());
		Assert.assertEquals(ap.checkSavingSalesInvoiceVARWithEnableOption(), true);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	



}
