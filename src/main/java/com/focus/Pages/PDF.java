package com.focus.Pages;

import java.io.IOException;

import com.focus.base.BaseEngine;
import com.testautomationguru.utility.PDFUtil;

public class PDF extends BaseEngine{

	public static void main(String[] args) throws IOException {
	
		
		

		String actPDF = "F:\\FocusXBillWise1\\BillWise\\autoIt\\ExportFiles\\ItemParameterPDF.pdf";
		String expPDF = "F:\\FocusXBillWise1\\BillWise\\autoIt\\ImportFiles\\ItemImageLayoutPDF.pdf";
		System.out.println(actPDF);
		System.out.println(expPDF);
		
		
		PDFUtil pdfutil = new PDFUtil();
		
		boolean result = pdfutil.compare(actPDF, expPDF);
		System.out.println(result);
		
		String actData = pdfutil.getText(actPDF);
		String expData = pdfutil.getText(expPDF);
		System.out.println(actData);
		System.out.println(expData);
		
	}

}
