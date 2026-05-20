package com.focus.testcases;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.focus.Pages.BroswerRefreshPage;
import com.focus.base.BaseEngine;

public class BroswerRefreshTest extends BaseEngine 
{

	BroswerRefreshPage brp;

	@Test(priority=50)
	public void checkLogoutOption() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		brp=new BroswerRefreshPage(getDriver());
		Assert.assertEquals(brp.checkLogoutOption(), true);	
	}


}
