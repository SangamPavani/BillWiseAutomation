package com.focus.testcases;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.focus.Pages.ChequesPage;
import com.focus.base.BaseEngine;

public class ChequesTest extends BaseEngine
{

	static ChequesPage cp;

	//BRS BackUp 

	//@Test(priority=79)
	public static void checkLoginToDefineChequeSeries() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		cp=new ChequesPage(getDriver());
		Assert.assertEquals(cp.checkLoginToDefineChequeSeries(), true);	
	}
	
	
	@Test(priority=80)
	public void checkEraseAllDATA() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		cp=new ChequesPage(getDriver());
		Assert.assertEquals(cp.checkEraseAllDATA(), true);	
	}

	@Test(priority=81)
	public void checkDefineChequeSeriesScreen() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		cp=new ChequesPage(getDriver());
		Assert.assertEquals(cp.checkDefineChequeSeriesScreen(), true);	
	}

	@Test(priority=82)
	public void checksaveWithoutInputChequeInDefineCheqyeSeries() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		cp=new ChequesPage(getDriver());
		Assert.assertEquals(cp.checksaveWithoutInputChequeInDefineCheqyeSeries(), true);	
	}

	@Test(priority=83)
	public void checksaveWithAccountInputChequeInDefineCheqyeSeries() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		cp=new ChequesPage(getDriver());
		Assert.assertEquals(cp.checksaveWithAccountInputChequeInDefineCheqyeSeries(), true);	
	}

	@Test(priority=84)
	public void checkSavingDefineChqueSeries() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		cp=new ChequesPage(getDriver());
		Assert.assertEquals(cp.checkSavingDefineChqueSeries(), true);	
	}

	@Test(priority=85)
	public void checkSavedDefineCheckSeriesWithDepartmentDubai() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		cp=new ChequesPage(getDriver());
		Assert.assertEquals(cp.checkSavedDefineCheckSeriesWithDepartmentDubai(), true);	
	}

	@Test(priority=86)
	public void checkChequeDetailsOptionsInChequeDetailsSeries() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		cp=new ChequesPage(getDriver());
		Assert.assertEquals(cp.checkChequeDetailsOptionsInChequeDetailsSeries(), true);	
	}

	@Test(priority=87)
	public void checkSavingDCSWithOutCurrentlyUsed() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		cp=new ChequesPage(getDriver());
		Assert.assertEquals(cp.checkSavingDCSWithOutCurrentlyUsed(), true);	
	}

	@Test(priority=88)
	public void checkDeleteWithNoOptionInDCS() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		cp=new ChequesPage(getDriver());
		Assert.assertEquals(cp.checkDeleteWithNoOptionInDCS(), true);	
	}

	@Test(priority=89)
	public void checkDeleteWithYesOptionInDCS() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		cp=new ChequesPage(getDriver());
		Assert.assertEquals(cp.checkDeleteWithYesOptionInDCS(), true);	
	}

	@Test(priority=90)
	public void checkSavingAgainWithSameAccAfterDeletion() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		cp=new ChequesPage(getDriver());
		Assert.assertEquals(cp.checkSavingAgainWithSameAccAfterDeletion(), true);	
	}

	@Test(priority=92)
	public void checkEnableOptionMaintainChequeSeriesInPaymentsVAT() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException

	{
		cp=new ChequesPage(getDriver());
		Assert.assertEquals(cp.checkEnableOptionMaintainChequeSeriesInPaymentsVAT(), true);	
	}

	
	
	@Test(priority=93)
	public void checkSavingVoucherInPaymnetVatAfterEnableOptionMaintainChequeSeries() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		cp=new ChequesPage(getDriver());
		Assert.assertEquals(cp.checkSavingVoucherInPaymnetVatAfterEnableOptionMaintainChequeSeries(), true);	
	}
	

	@Test(priority=94)
	public void checkSavingPurchaseVoucherWithAdjustingPaymentsVoucher() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		cp=new ChequesPage(getDriver());
		Assert.assertEquals(cp.checkSavingPurchaseVoucherWithAdjustingPaymentsVoucher(), true);	
	}


	@Test(priority=96)
	public void checkChequeDetailsAfterChequeConsumedInPurchase() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		cp=new ChequesPage(getDriver());
		Assert.assertEquals(cp.checkChequeDetailsAfterChequeConsumedInPurchase(), true);	
	}

	@Test(priority=97)// Issue 
	public void check2ndCheueNumberInPaymentsAfterqstCheckConsumed() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		cp=new ChequesPage(getDriver());
		Assert.assertEquals(cp.check2ndCheueNumberInPaymentsAfterqstCheckConsumed(), true);	
	}
	

	@Test(priority=98)
	public void checkCancelChqueWithnotInRange() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		cp=new ChequesPage(getDriver());
		Assert.assertEquals(cp.checkCancelChqueWithnotInRange(), true);	
	}

	@Test(priority=99)// Issue 
	public void checkCancellingUsedChequeinPurchaseVat() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		cp=new ChequesPage(getDriver());
		Assert.assertEquals(cp.checkCancellingUsedChequeinPurchaseVat(), true);	
	}
	

	@Test(priority=100)
	public void checkCancelChequeUnused() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		cp=new ChequesPage(getDriver());
		Assert.assertEquals(cp.checkCancelChequeUnused(), true);	
	}

	@Test(priority=101)
	public void checkCancelChequeWithUnusedAccount() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		cp=new ChequesPage(getDriver());
		Assert.assertEquals(cp.checkCancelChequeWithUnusedAccount(), true);	
	}

	@Test(priority=102)
	public void checkCancellingChequeAgainAfterChqueCancel() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		cp=new ChequesPage(getDriver());
		Assert.assertEquals(cp.checkCancellingChequeAgainAfterChqueCancel(), true);	
	}


	@Test(priority=111)
	public void checkRaiseChequereturnInPaymentsVAT() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		cp=new ChequesPage(getDriver());
		Assert.assertEquals(cp.checkRaiseChequereturnInPaymentsVAT(), true);	
	}

	@Test(priority=112)
	public void checkEditoptionInPaymnetsVATWhereReturnChqueIsRaised() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		cp=new ChequesPage(getDriver());
		Assert.assertEquals(cp.checkEditoptionInPaymnetsVATWhereReturnChqueIsRaised(), true);	
	}

	
	@Test(priority=113)
	public void checkRaiseChequereturnOnVoucherWhichIsAlreadyRetuerned() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		cp=new ChequesPage(getDriver());
		Assert.assertEquals(cp.checkRaiseChequereturnOnVoucherWhichIsAlreadyRetuerned(), true);	
	}

	
	@Test(priority=114)
	public void checkChequebookRegisterReportWithUsedChequesEnable() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		cp=new ChequesPage(getDriver());
		Assert.assertEquals(cp.checkChequebookRegisterReportWithUsedChequesEnable(), true);	
	}

	@Test(priority=115)
	public void checkBackTrackInkChequebookRegisterReport() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		cp=new ChequesPage(getDriver());
		Assert.assertEquals(cp.checkBackTrackInkChequebookRegisterReport(), true);	
	}

	@Test(priority=121)
	public void checkChequebookRegisterReportWithUNUsedChequesEnable() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		cp=new ChequesPage(getDriver());
		Assert.assertEquals(cp.checkChequebookRegisterReportWithUNUsedChequesEnable(), true);	
	}

	
	@Test(priority=122)
	public void checkReturedChequeInRecepitsVATVoucher() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		cp=new ChequesPage(getDriver());
		Assert.assertEquals(cp.checkReturedChequeInRecepitsVATVoucher(), true);	
	}
	
	
	@Test(priority=123)
	public void checkCancelChequeValidationInDefineChequeSeries() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		cp=new ChequesPage(getDriver());
		Assert.assertEquals(cp.checkCancelChequeValidationInDefineChequeSeries(), true);	
	}

	






}
