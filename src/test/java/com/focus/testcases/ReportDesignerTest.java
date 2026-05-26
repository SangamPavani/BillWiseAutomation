package com.focus.testcases;

import java.awt.AWTException;
import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.Test;


//import com.focus.Pages.BillWisePage;
import com.focus.Pages.ReportDesignerPage;
import com.focus.base.BaseEngine;

public class ReportDesignerTest extends BaseEngine 
{
	ReportDesignerPage RDP ;
/*
	@Test(priority=80)
	public void checkLogin() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkLogin(), true);	
	}

	@Test(priority=81)
	public void checkRestoreCompany() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkRestoreCompany(), true);
	}

	@Test(priority=82)
	public void checkNavigateToReportDesginer() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkNavigateToReportDesginer(), true);	
	}
	
	@Test(priority=83)
	public void checkDefaultReportDetails() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkDefaultReportDetails(), true);	
	}
	
	@Test(priority=84)
	public void checkEditAndAddFieldsInDefaultReport() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkEditAndAddFieldsInDefaultReport(), true);	
	}
	
	
	@Test(priority=91)
	public void checkSavingReportDesignerOfAllTransactionsOfDocumentClass() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkSavingReportDesignerOfAllTransactionsOfDocumentClass(), true);
	}

	@Test(priority=92)
	public void checkUpdatingTheSavedReportInreportDesinger() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkUpdatingTheSavedReportInreportDesinger(), true);
	}

	@Test(priority=93)
	public void checkPreviewTabInReportDesiging() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkPreviewTabInReportDesiging(), true);
	}

	@Test(priority=94)
	public void checkDeleteOptionInReportDesigner() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkDeleteOptionInReportDesigner(), true);
	}

	// Report Validation
	@Test(priority=95)
	public void checkReportAllTransactionsOfDocumentClassOfPurchaseType() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkReportAllTransactionsOfDocumentClassOfPurchaseType(), true);
	}

	@Test(priority=96)
	public void checkSavingAllTransactionsOfDocumentTypeOfPurchaseTypeToDetails() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkSavingAllTransactionsOfDocumentTypeOfRecepitsTypeToDetails(), true);
	}

	@Test(priority=97)
	public void checkReportAllTransactionsOfDocumentTypeOfRecepitsVATType() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkReportAllTransactionsOfDocumentTypeOfRecepitsVATType(), true);
	}                 


	@Test(priority=98)
	public void checkSavingAccountingTransactionsOfAnAccountDetailsWithConditionUnauthorisedRecords() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkSavingAccountingTransactionsOfAnAccountDetails(), true);
	} 


	@Test(priority=99)
	public void checkReportAccountingTransactionsOfAnAccountDetails() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkReportAccountingTransactionsOfAnAccountDetails(), true);
	} 
	
	

	@Test(priority=100)
	public void checkSavingAccountingTransactionsOfAccountingTagDetails() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkSavingAccountingTransactionsOfAccountingTagDetails(), true);
	} 

	@Test(priority=101)//col focus x issue with the no.of columns.
	public void checkReportAccountingTransactionsOfAnAccountingTagDetails() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkReportAccountingTransactionsOfAnAccountingTagDetails(), true);
	} 


	@Test(priority=102)//COL
	public void checkSavingAccountingTrasactionsOfInventoryTagDetails() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkSavingAccountingTrasactionsOfInventoryTagDetails(), true);
	} 


	@Test(priority=103)//COL
	public void checkReportAccountingTrasactionsofInventoryTagDetails() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkReportAccountingTrasactionsofInventoryTagDetails(), true);
	}

	@Test(priority=104)
	public void checkSavingAccountingTransactionsoFTagDetails() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkSavingAccountingTransactionsoFTagDetails(), true);
	} 

	@Test(priority=105)
	public void checkReportAccountingTransactionsofTagDetails() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkReportAccountingTransactionsofTagDetails(), true);
	} 


	@Test(priority=106)
	public void checkSavingAllAccountsByTagDetails() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkSavingAllAccountsByTagDetails(), true);
	} 


	@Test(priority=107)//col
	public void checkReportAllAccountsByTagDetails() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkReportAllAccountsByTagDetails(), true);
	} 

	@Test(priority=109)
	public void checkSavingRDWithAllProductsDetails() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkSavingRDWithAllProductsDetails(), true);
	}

	@Test(priority=110)
	public void checkReportAllProductsDetailsDetails() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkReportAllProductsDetailsDetails(), true);
	}

	@Test(priority=111)
	public void checkSavingRDWithAllProductsBTagDetails() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkSavingRDWithAllProductsBTagDetails(), true);
	}

	@Test(priority=112)//col
	public void checkReportAllProductsByTagDetails() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkReportAllProductsByTagDetails(), true);
	}


	@Test(priority=114)
	public void checkSavingRDWithInventoryTransactionsDetailsDetails() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkSavingRDWithInventoryTransactionsDetailsDetails(), true);
	}

	@Test(priority=115)//col
	public void checkReportInventoryTransactionsDetails() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkReportInventoryTransactionsDetails(), true);
	}

	@Test(priority=116)
	public void checkSavingRDWithInventoryTransactionsOfProductDetails() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkSavingRDWithInventoryTransactionsOfProductDetails(), true);
	}

	@Test(priority=117)//col
	public void checkReportInventoryTransactionsOfProductDetails() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkReportInventoryTransactionsOfProductDetails(), true);
	}

	@Test(priority=118)
	public void checkSavingRDWithInventoryTransactionsOfAccountingTagDetails() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkSavingRDWithInventoryTransactionsOfAccountingTagDetails(), true);
	}

	@Test(priority=119)
	public void checkReportInventoryTransactionsOFAccountingTagDetails() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkReportInventoryTransactionsOFAccountingTagDetails(), true);
	}

	@Test(priority=120)
	public void checkSavingRDWithInventoryTransactionsOfInventoryTagDetails() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkSavingRDWithInventoryTransactionsOfInventoryTagDetails(), true);
	}

	@Test(priority=121)
	public void checkReportInventoryTransactionsOFInventoryTagDetails() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkReportInventoryTransactionsOFInventoryTagDetails(), true);
	}
	

	@Test(priority=122)
	public void checkSavingRDWithInventoryTransactionsOfTagDetails() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkSavingRDWithInventoryTransactionsOfTagDetails(), true);
	}

	@Test(priority=123)// issue
	public void checkReportInventoryTransactionsOFTagDetails() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkReportInventoryTransactionsOFTagDetails(), true);
	}

	@Test(priority=124)
	public void checkSavingRDWithInventoryTransactionsOfSelectedAccountDetails() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkSavingRDWithInventoryTransactionsOfSelectedAccountDetails(), true);
	}

	@Test(priority=125)
	public void checkReportInventoryTransactionsSelectedAccountDetails() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkReportInventoryTransactionsSelectedAccountDetails(), true);
	}


	@Test(priority=126)
	public void checkSavingReportThroughAnalysisInLedgerReport() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkSavingReportThroughAnalysisInLedgerReport(), true);
	}

	@Test(priority=127)
	public void checkLedgerAnalysisreport() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkLedgerAnalysisreport(), true);
	}


	@Test(priority=128)
	public void checkLedgerDetailsExportPdf() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkLedgerDetailsExportPdf(), true);
	}

	//@Test(priority=129)
	public void checkImportExcelFileInReportDesign() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkImportExcelFileInReportDesign(), true);
	}

	// @Test(priority=130)  
	public void checkExportOptionreportDesging() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkExportOptionreportDesging(), true);
	}
	


	@Test(priority=132)
	public void checkSavingRDReportsWihParameter() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(
				getDriver());
		Assert.assertEquals(RDP.checkSavingRDReportsWihParameter(), true);
	}

	@Test(priority=133)
	public void checkAddingProgrammmingFiledInBillwiseRDReport() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkAddingProgrammmingFiledInBillwiseRDReport(), true);
	}


	@Test(priority=134)//col
	public void checkBillwiseDetailsreport() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkBillwiseDetailsreport(), true);
	}
	
	

	@Test(priority=135)
	public void checkParameterWithMultiplewayoptions() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkParameterWithMultiplewayoptions(), true);
	}


	@Test(priority=136)
	public void checkBillwiseReportAfterCheckingMultipleChkbox() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkBillwiseReportAfterCheckingMultipleChkbox(), true);
	}


	@Test(priority=140)
	public void checkSavingReportDesigningWithQuaeery() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkSavingReportDesigningWithQuaeery(), true);
	}

	@Test(priority=141)//
	public void checkReportWithQuaeryreport() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkReportWithQuaeryreport(), true);
	}

	
	
	@Test(priority=142)
	public void checkCustomizeDateFormatInLedgerReport() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkCustomizeDateFormatInLedgerReport(), true);
	}

	@Test(priority=143)
	public void checkValidateDateFormatInLedgerReport() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkValidateDateFormatInLedgerReport(), true);
	}
	

	@Test(priority=144)
	public void checkSaveAReportForValidationRowFormattingTab() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkSaveAReportForValidationRowFormattingTab(), true);
	}
	
	
	
	
	@Test(priority=145)//Not applying for Item name
	public void checkFormattingReportAfterRowFormating() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkFormattingReportAfterRowFormating(), true);
	}
	
	


	//Cubes

	@Test(priority=200)//with out pronghorn Display based on tree sequence is not appearing
	public void checkSavingAllTransactionsOfDocumentClassCube() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkSavingAllTransactionsOfDocumentClassCube(), true);
	}

	@Test(priority=201)
	public void checkReportAlltransactionsofdocumentclasscube() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkReportAlltransactionsofdocumentclasscube(), true);
	}

	@Test(priority=202)
	public void checkReportExportingThroughPDFAndValidatePDF() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkReportExportingThroughPDFAndValidatePDF(), true);
	}


	@Test(priority=203)
	public void checkCustomizeReportOFAlltransactionsofdocumentclasscube() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkCustomizeReportOFAlltransactionsofdocumentclasscube(), true);
	}
	

	@Test(priority=204)
	public void checkInventoryTransactionsOfATagCubeReportWithMutipleLevelsOfRowGrouping() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkInventoryTransactionsOfATagCubeReportWithMutipleLevelsOfRowGrouping(), true);
	}

	@Test(priority=205)
	public void checkReportForCreatedInventoryTransactionsOfATagCube() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkReportForCreatedInventoryTransactionsOfATagCube(), true);
	}
	
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


	
	@Test(priority=206)
	public void checkSaveInventoryTransactionsOfInventoryTagCubes() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkSaveInventoryTransactionsOfInventoryTagCubes(), true);
	}

	
	
	@Test(priority=207)
	public void checkReportForCreatedInventoryTransactionsOfInventoryTagCube() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkReportForCreatedInventoryTransactionsOfInventoryTagCube(), true);
	}



	@Test(priority=208)
	public void checkSavingAccountingTransactionsOfAnAccountCube() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkSavingAccountingTransactionsOfAnAccountCube(), true);
	} 


	@Test(priority=209)
	public void checkReportAccountingtransactionOfAnAccountCube() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkReportAccountingtransactionOfAnAccountCube(), true);
	} 

	@Test(priority=210)
	public void checkSavingAccountingTransactionsOfAnAccountingTagCube() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkSavingAccountingTransactionsOfAnAccountingTagCube(), true);
	}


	@Test(priority=211)
	public void checkReportAccountingTransactionsofAnAccountingTag_cube() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkReportAccountingTransactionsofAnAccountingTag_cube(), true);
	}
	
	
	
	


////////////////Filter and Prgramming Fields with Formula///////////////////////////////////////////////////////////////////////////////////////////



	@Test(priority=220)
	public void CheckAddingFilterInFiledsCreatedCustomizationTab() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.CheckAddingFilterInFiledsCreatedCustomizationTab(), true);
	}

	@Test(priority=221)//col
	public void checkReportMRWithFilter() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkReportMRWithFilter(), true);
	}

	@Test(priority=222)
	public void checkAddingProgrammableFiledWithColAttaributeAndEditingColName() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkAddingProgrammableFiledWithColAttaributeAndEditingColName(), true);
	}


	@Test(priority=223)//col
	public void checkReportMRAfterChangesInCustomizationtab() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkReportMRAfterChangesInCustomizationtab(), true);
	}

	@Test(priority=224)
	public void checkAlignmentInReportDesgining() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkAlignmentInReportDesgining(), true);
	}


	@Test(priority=225)//col
	public void checkReportMRAfterChangesAlignmentAndSignInColoumnFiels() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkReportMRAfterChangesAlignmentAndSignInColoumnFiels(), true);
	}


	@Test(priority=226)
	public void checkSortingOptionInReportDesgining() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkSortingOptionInReportDesgining(), true);
	}
	@Test(priority=227)//col
	public void checkReportMRAfterChangesAInSortingTAB() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkReportMRAfterChangesAInSortingTAB(), true);
	}

	
	

	@Test(priority=400)
	public void checklogout() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checklogout(), true);
	}
	

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	@Test(priority=420)//col
	public void checkLoginForRD2() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkLoginForRD2(), true);
	}


	@Test(priority=421)
	public void checkRestoreCompanyForRD2() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkRestoreCompanyForRD2(), true);
	}
	
	@Test(priority=422)//
	public void checkReportDesignerForQuantityFilter() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkReportDesignerForQuantityFilter(), true);
	}


	@Test(priority=423)
	public void checkQuantityFilteronWarehouse() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkQuantityFilteronWarehouse(), true);
	
	}
	
	@Test(priority=424)
	public void checkValidateQuantityFilterOnWarehouse() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkValidateQuantityFilterOnWarehouse(), true);
	
	}
	
	//1
	@Test(priority=425)
	public void checkCustomizeReportDesignerForRDItemParameter() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkCustomizeReportDesignerForRDItemParameter(), true);
	
	}
	
	
	@Test(priority=427)
	public void checklogoutRD2() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checklogout(), true);
	}

	*/

	@Test(priority=428)//col
	public void checkLoginForRD2Again() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkLoginForRD2(), true);
	}
	

	@Test(priority=429)
	public void checkApplyFilterInDataSetTabForItemWithParameterRD() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkApplyFilterInDataSetTabForItemWithParameterRD(), true);
	
	}
	
	@Test(priority=430)
	public void checkRDReportForItemFilterAsItemParameter() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkRDReportForItemFilterAsItemParameter(), true);
	
	}
	
	@Test(priority=431)
	public void checkPrintPDFForAnotherItemForItemParameterRD() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkPrintPDFForAnotherItemForItemParameterRD(), true);
	
	}
	
	
	
	@Test(priority=438)
	public void checkCreateReportForMonthWiseData() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkCreateReportForMonthWiseData(), true);
	
	}
	
	@Test(priority=439)
	public void checkCreatedMonthlyReportDetails() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkCreatedMonthlyReportDetails(), true);
	
	}
	
	
	@Test(priority=440)
	public void checkCreateReportDesignerForMultipleTransactionSets() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkCreateReportDesignerForMultipleTransactionSets(), true);
	
	}
	
	@Test(priority=441)
	public void checkChangeNetAmountForEachIndividualVouchers() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkChangeNetAmountForEachIndividualVouchers(), true);
	
	}
	
	@Test(priority=442)
	public void checkCreatedMultipleTransactionSetsReportDetails() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkCreatedMultipleTransactionSetsReportDetails(), true);
	
	}
	
	//@Test(priority=225) In complete 
	public void checkSavingReportDesginerOnBasisOFCustomization() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkSavingReportDesginerOnBasisOFCustomization(), true);
	}
	
	
	
	//////////////////////////////RD Reports Scenarios//////////////////////
	
	@Test(priority=450) 
	public void checkSavingRDReportforValidatingItemGroupFilter() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkSavingRDReportforValidatingItemGroupFilter(), true);
	}
	
	
	
	
	@Test(priority=451) 
	public void checkReportforItemGroupFilterBeforeandAfterFilteronGroupItem() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkReportforItemGroupFilterBeforeandAfterFilteronGroupItem(), true);
	}
	
	
	
	@Test(priority=452) 
	public void checkSavingRDReportforValidatingAnalyzeReport() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkSavingRDReportforValidatingAnalyzeReport(), true);
	}
	
	
	@Test(priority=453) 
	public void checkCreatedReportforAnalyzeReport() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkCreatedReportforAnalyzeReport(), true);
	}
	
	
	
	
	@Test(priority=454) 
	public void checkSavedRDAnalyzedReport() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkSavedRDAnalyzedReport(), true);
	}
	
	
	
	
	@Test(priority=455) 
	public void checkSavingDetailsReportforFilter() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkSavingDetailsReportforFilter(), true);
	}
	
	
	
	@Test(priority=456) 
	public void checkSavedReportforFilterforValidationoffilter() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkSavedReportforFilterforValidationoffilter(), true);
	}
	
	
	@Test(priority=457) 
	public void checkRemovingFilterinDatasetApplyFilterinReportScreen() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkRemovingFilterinDatasetApplyFilterinReportScreen(), true);
	}
	
	
	///Options in Detail and Cube Report
	
	
	
	@Test(priority=460) 
	public void checkOptionsinRDDetailsReport() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkOptionsinRDDetailsReport(), true);
	}
	
	
	
	@Test(priority=461) 
	public void checkOptionsinCubeRDReport() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkOptionsinCubeRDReport(), true);
	}
	
	
	//Backtrack in Detail and cube report
	
	
	@Test(priority=462) 
	public void checkBacktrackOptioninRDDetailReport() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkBacktrackOptioninRDDetailReport(), true);
	}
	
	
	@Test(priority=463) 
	public void checkBacktrackOptioninRDCubeReport() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkBacktrackOptioninRDCubeReport(), true);
	}
	
	
	//print,Excel in Home and Entry Page
	
	
	
	@Test(priority=464) 
	public void checkHomePagePrintOptioninRDDetailReport() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkHomePagePrintOptioninRDDetailReport(), true);
	}
	
	
	
	@Test(priority=465) 
	public void checkHomePageExcelOptioninRDDetailReport() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkHomePageExcelOptioninRDDetailReport(), true);
	}
	
	
	
	
	@Test(priority=466) 
	public void checkHomePageCSVOptioninRDDetailReport() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkHomePageCSVOptioninRDDetailReport(), true);
	}
	
	
	@Test(priority=467) 
	public void checkHomePagePDFOptioninRDDetailReport() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkHomePagePDFOptioninRDDetailReport(), true);
	}
	
	
	@Test(priority=468) 
	public void checkHomePageFileOptioninRDDetailReport() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkHomePageFileOptioninRDDetailReport(), true);
	}
	
	
	
	//Entry
	
	
	
	 
	 @Test(priority=469) 
	public void checkEntryPagePrintOptioninRDDetailReport() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkEntryPagePrintOptioninRDDetailReport(), true);
	}
	
	
	
	@Test(priority=470) 
	public void checkEntryPageExcelOptioninRDDetailReport() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkEntryPageExcelOptioninRDDetailReport(), true);
	}
	
	
	
	
	@Test(priority=471) 
	public void checkEntryPageCSVOptioninRDDetailReport() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkEntryPageCSVOptioninRDDetailReport(), true);
	}
	
	
	@Test(priority=472) 
	public void checkEntryPagePDFOptioninRDDetailReport() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkEntryPagePDFOptioninRDDetailReport(), true);
	}
	
	
	@Test(priority=473) 
	public void checkEntryPageFileOptioninRDDetailReport() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkEntryPageFileOptioninRDDetailReport(), true);
	}
	
	
	 
	///Print , Excel in Cube Report
	 
	 @Test(priority=474) 
	public void checkHomePagePrintOptioninRDCubeReport() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkHomePagePrintOptioninRDCubeReport(), true);
	}
	
	
	
	//@Test(priority=475) 
	public void checkHomePageExcelOptioninRDCubeReport() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkHomePageExcelOptioninRDCubeReport(), true);
	}
	
	
	
	
	@Test(priority=476) 
	public void checkHomePageCSVOptioninRDCubeReport() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkHomePageCSVOptioninRDCubeReport(), true);
	}
	
	
	@Test(priority=477) 
	public void checkHomePagePDFOptioninRDCubeReport() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkHomePagePDFOptioninRDCubeReport(), true);
	}
	
	
	@Test(priority=478) 
	public void checkHomePageFileOptioninRDCubeReport() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkHomePageFileOptioninRDCubeReport(), true);
	}
	
	
	
	//Entry
	
	
	
	 
	 @Test(priority=479) 
	public void checkEntryPagePrintOptioninRDCubeReport() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkEntryPagePrintOptioninRDCubeReport(), true);
	}
	
	
	
	@Test(priority=480) 
	public void checkEntryPageExcelOptioninRDCubeReport() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkEntryPageExcelOptioninRDCubeReport(), true);
	}
	
	
	
	
	@Test(priority=481) 
	public void checkEntryPageCSVOptioninRDCubeReport() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkEntryPageCSVOptioninRDCubeReport(), true);
	}
	
	
	@Test(priority=482) 
	public void checkEntryPagePDFOptioninRDCubeReport() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkEntryPagePDFOptioninRDCubeReport(), true);
	}
	
	
	@Test(priority=483) 
	public void checkEntryPageFileOptioninRDCubeReport() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkEntryPageFileOptioninRDCubeReport(), true);
	}
	 
	 
	
	 
	@Test(priority=484) 
	public void checkHeaderFooterinRDDetailReport() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkHeaderFooterinRDDetailReport(), true);
	}
	
	
	
	@Test(priority=485) 
	public void checkPrintOptioninRDDetailReportAfterHeaderFooter() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkPrintOptioninRDDetailReportAfterHeaderFooter(), true);
	}
	
	
	///Date Options
	
	
	
	
	@Test(priority=486) 
	public void checkRDDetailReportforDateRangeasAsonDate() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkRDDetailReportforDateRangeasAsonDate(), true);
	}
	
	
	
	@Test(priority=487) 
	public void checkRDDetailReportforDateRangeasCurrentMonth() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkRDDetailReportforDateRangeasCurrentMonth(), true);
	}
	
	
	
	@Test(priority=488) 
	public void checkRDDetailReportforDateRangeasPreviousMonth() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkRDDetailReportforDateRangeasPreviousMonth(), true);
	}
	
	
	
	
	@Test(priority=489) 
	public void checkRDDetailReportforDateRangeasCurrentWeek() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkRDDetailReportforDateRangeasCurrentWeek(), true);
	}
	
	
	
	
	@Test(priority=490) 
	public void checkRDDetailReportforDateRangeasPreviousWeek() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkRDDetailReportforDateRangeasPreviousWeek(), true);
	}
	
	
	
	
	@Test(priority=491) 
	public void checkRDDetailReportforDateRangeasToday() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkRDDetailReportforDateRangeasToday(), true);
	}
	
	
	
	
	@Test(priority=492) 
	public void checkRDDetailReportforDateRangeasCurrentyear() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkRDDetailReportforDateRangeasCurrentyear(), true);
	}
	
	
	
	
	@Test(priority=493) 
	public void checkRDDetailReportforDateRangeasPreviousyear() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkRDDetailReportforDateRangeasPreviousyear(), true);
	}
	
	
	
	
	
	@Test(priority=494) 
	public void checkRDDetailReportforDateRangeasCurrentFinancialyear() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkRDDetailReportforDateRangeasCurrentFinancialyear(), true);
	}
	
	
	
	@Test(priority=495) 
	public void checkRDDetailReportforDateRangeasPreviousFinancialyear() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkRDDetailReportforDateRangeasPreviousFinancialyear(), true);
	}
	
	
	
	@Test(priority=496) 
	public void checkRDDetailReportforDateRangeasYesterDay() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkRDDetailReportforDateRangeasYesterDay(), true);
	}
	
	
	
	
	@Test(priority=497) 
	public void checkRDDetailReportforDateRangeasCurrentQuarter() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkRDDetailReportforDateRangeasCurrentQuarter(), true);
	}
	
	
	
	@Test(priority=498) 
	public void checkRDDetailReportforDateRangeasPreviousQuarter() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkRDDetailReportforDateRangeasPreviousQuarter(), true);
	}
	
	//Cube Date Options
	
	
	
	 
	 @Test(priority=499) 
	public void checkCubeRDReportforDateRangeasAsonDate() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkCubeRDReportforDateRangeasAsonDate(), true);
	}
	
	
	
	@Test(priority=500) 
	public void checkCubeRDReportforDateRangeasCurrentMonth() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkCubeRDReportforDateRangeasCurrentMonth(), true);
	}
	
	
	
	@Test(priority=501) 
	public void checkCubeRDReportforDateRangeasPreviousMonth() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkCubeRDReportforDateRangeasPreviousMonth(), true);
	}
	
	
	
	
	@Test(priority=502) 
	public void checkCubeRDReportforDateRangeasCurrentWeek() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkCubeRDReportforDateRangeasCurrentWeek(), true);
	}
	
	
	
	
	@Test(priority=503) 
	public void checkCubeRDReportforDateRangeasPreviousWeek() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkCubeRDReportforDateRangeasPreviousWeek(), true);
	}
	
	
	
	
	@Test(priority=504) 
	public void checkCubeRDReportforDateRangeasToday() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkCubeRDReportforDateRangeasToday(), true);
	}
	
	
	
	
	@Test(priority=505) 
	public void checkCubeRDReportforDateRangeasCurrentyear() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkCubeRDReportforDateRangeasCurrentyear(), true);
	}
	
	
	
	
	@Test(priority=506) 
	public void checkCubeRDReportforDateRangeasPreviousyear() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkCubeRDReportforDateRangeasPreviousyear(), true);
	}
	
	
	
	
	
	@Test(priority=507) 
	public void checkCubeRDReportforDateRangeasCurrentFinancialyear() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkCubeRDReportforDateRangeasCurrentFinancialyear(), true);
	}
	
	
	
	@Test(priority=508) 
	public void checkCubeRDReportforDateRangeasPreviousFinancialyear() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkCubeRDReportforDateRangeasPreviousFinancialyear(), true);
	}
	
	
	
	@Test(priority=509) 
	public void checkCubeRDReportforDateRangeasYesterDay() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkCubeRDReportforDateRangeasYesterDay(), true);
	}
	
	
	
	
	@Test(priority=510) 
	public void checkCubeRDReportforDateRangeasCurrentQuarter() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkCubeRDReportforDateRangeasCurrentQuarter(), true);
	}
	
	
	
	@Test(priority=511) 
	public void checkCubeRDReportforDateRangeasPreviousQuarter() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkCubeRDReportforDateRangeasPreviousQuarter(), true);
	}
	 
	 
	//filter in RD Stock transfer Report 
	
	
	@Test(priority=512) 
	public void checkFilterinRDStockTransferReport() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkFilterinRDStockTransferReport(), true);
	}
	
	@Test(priority=513) 
	public void checkPrintinRDStockTransferReportAfterFilter() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkPrintinRDStockTransferReportAfterFilter(), true);
	}
	
	
	
	@Test(priority=514) 
	public void checkExcelinStockTransferReportAfterFilter() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkExcelinStockTransferReportAfterFilter(), true);
	}
	
	
	
	
	
	@Test(priority=515) 
	public void checkHomePrintinRDStockTransferReportAfterFilter() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkHomePrintinRDStockTransferReportAfterFilter(), true);
	}
	
	
	
	@Test(priority=516) 
	public void checkHomeExcelinStockTransferReportAfterFilter() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkHomeExcelinStockTransferReportAfterFilter(), true);
	}
	
	
	
	@Test(priority=517) 
	public void checkDefaultFilterinRDStockTransferReport() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkDefaultFilterinRDStockTransferReport(), true);
	}
	
	
	
	
	@Test(priority=518) 
	public void checkRDStockTransferReportafterDefaultFilter() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkRDStockTransferReportafterDefaultFilter(), true);
	}
	
	
	
	@Test(priority=519) 
	public void checkPrintinRDStockTransferReportAfterDefaultFilter() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkPrintinRDStockTransferReportAfterDefaultFilter(), true);
	}
	
	
	@Test(priority=520) 
	public void checkExcelinStockTransferReportAfterDefaultFilter() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkExcelinStockTransferReportAfterDefaultFilter(), true);
	}
	
	
	
	@Test(priority=521) 
	public void checkHomePrintinRDStockTransferReportAfterDefaultFilter() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkHomePrintinRDStockTransferReportAfterDefaultFilter(), true);
	}
	
	
	
	@Test(priority=522) 
	public void checkHomeExcelinStockTransferReportAfterdefaultFilter() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkHomeExcelinStockTransferReportAfterDefaultFilter(), true);
	}
	
	
	
	@Test(priority=523) 
	public void checkPreviousYearMonthDetailsinRDYearandMonthWiseReport() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
	{
		RDP=new ReportDesignerPage(getDriver());
		Assert.assertEquals(RDP.checkPreviousYearMonthDetailsinRDYearandMonthWiseReport(), true);
	}
	
	
	
	//DAte Range in Filter
	
	
	
		@Test(priority=525) 
		public void checkSavingRDDetailReportforDateOptions() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
		{
			RDP=new ReportDesignerPage(getDriver());
			Assert.assertEquals(RDP.checkSavingRDDetailReportforDateOptions(), true);
		}
		
		
		
		
		
		@Test(priority=526) 
		public void checkReportforDateRangeOptionsforPreviousFQ() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
		{
			RDP=new ReportDesignerPage(getDriver());
			Assert.assertEquals(RDP.checkReportforDateRangeOptionsforPreviousFQ(), true);
		}
		
		
		
		
		@Test(priority=527) 
		public void checkReportforDateRangeforPreviousMonth() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
		{
			RDP=new ReportDesignerPage(getDriver());
			Assert.assertEquals(RDP.checkReportforDateRangeforPreviousMonth(), true);
		}
		
				
		@Test(priority=528) 
		public void checkLogoutReportDesignerPage() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException
		{
			RDP=new ReportDesignerPage(getDriver());
			Assert.assertEquals(RDP.checkLogoutReportDesignerPage(), true);
		}
		
}

