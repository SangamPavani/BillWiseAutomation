package com.focus.supporters;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.testng.annotations.DataProvider;

import com.focus.base.BaseEngine;

public class ExcelReader extends BaseEngine
{
	private String filePath;
	private FileInputStream fip;
	private FileOutputStream fop;
	private Workbook workbook;
	private Sheet sheet;
	private Cell cell;
	private Cell cell2;
	private Cell cell3;
	private Cell cell4;
	private CellStyle style;
	private Row row;

	
	public ExcelReader(String filePath) throws EncryptedDocumentException, InvalidFormatException, IOException
	{
		this.filePath=filePath;
		fip=new FileInputStream(filePath);
		workbook=WorkbookFactory.create(fip);
	}
	
	
	
	
	public Sheet getSheet(String sheetName) 
	{
		sheet = workbook.getSheet(sheetName);
		return sheet;
	}
	
	public Sheet getSheet(int sheetIndex)
	{
		sheet=workbook.getSheetAt(sheetIndex);
		return sheet;
	}
	
	public Row getRow(String sheetName, int rowNum)
	{
		Row row=getSheet(sheetName).getRow(rowNum);
		return row;
	}
	
	public Row getRow(int sheetIndex, int rowNum)
	{
		row=getSheet(sheetIndex).getRow(rowNum);
		return row;
	}
	
	public Cell getCell(String sheetName,int rowNum, int cellNum)
	{
    
		cell =getSheet(sheetName).getRow(rowNum).getCell(cellNum);
		return cell;
		
	}
	
	public String getCellData(String sheetName, int rowNum,int cellNum)
	{
		String data=null;
		cell=getSheet(sheetName).getRow(rowNum).getCell(cellNum);
		if(cell.getCellType()==cell.CELL_TYPE_NUMERIC)
		{
			data=cell.getNumericCellValue()+"";
		}
		else if(cell.getCellType()==cell.CELL_TYPE_STRING)
		{
			data=cell.getStringCellValue();
		}
		return data;
	}
	
	
	public String get2CellData(String sheetName, int rowNum,int cellNum,String sheetName2, int rowNum2,int cellNum2)
	{
		String data=null;
		
		String data2=null;
		
		cell=getSheet(sheetName).getRow(rowNum).getCell(cellNum);
		
		cell2=getSheet(sheetName2).getRow(rowNum2).getCell(cellNum2);
		
		if(cell.getCellType()==cell.CELL_TYPE_NUMERIC)
		{
			data=cell.getNumericCellValue()+"";
		}
		else if(cell.getCellType()==cell.CELL_TYPE_STRING)
		{
			data=cell.getStringCellValue();
		}
		
		
		if(cell2.getCellType()==cell2.CELL_TYPE_NUMERIC)
		{
			data2=cell2.getNumericCellValue()+"";
		}
		else if(cell2.getCellType()==cell2.CELL_TYPE_STRING)
		{
			data2=cell2.getStringCellValue();
		}
		
		String dataSum=data+"	"+data2;
		
		return dataSum;
	}
	
	
	public String get4CellData(String sheetName, int rowNum,int cellNum,String sheetName2, int rowNum2,int cellNum2
			,String sheetName3, int rowNum3,int cellNum3,String sheetName4, int rowNum4,int cellNum4)
	{
		String data=null;
		
		String data2=null;
		
		String data3=null;
		
		String data4=null;
		
		cell=getSheet(sheetName).getRow(rowNum).getCell(cellNum);
		
		cell2=getSheet(sheetName2).getRow(rowNum2).getCell(cellNum2);
		
		cell3=getSheet(sheetName3).getRow(rowNum3).getCell(cellNum3);
		
		cell4=getSheet(sheetName4).getRow(rowNum4).getCell(cellNum4);
		
		if(cell.getCellType()==cell.CELL_TYPE_NUMERIC)
		{
			data=cell.getNumericCellValue()+"";
		}
		else if(cell.getCellType()==cell.CELL_TYPE_STRING)
		{
			data=cell.getStringCellValue();
		}
		
		
		if(cell2.getCellType()==cell2.CELL_TYPE_NUMERIC)
		{
			data2=cell2.getNumericCellValue()+"";
		}
		else if(cell2.getCellType()==cell2.CELL_TYPE_STRING)
		{
			data2=cell2.getStringCellValue();
		}
		
		
		if(cell3.getCellType()==cell3.CELL_TYPE_NUMERIC)
		{
			data3=cell3.getNumericCellValue()+"";
		}
		else if(cell3.getCellType()==cell3.CELL_TYPE_STRING)
		{
			data3=cell3.getStringCellValue();
		}
		
		if(cell4.getCellType()==cell4.CELL_TYPE_NUMERIC)
		{
			data4=cell4.getNumericCellValue()+"";
		}
		else if(cell4.getCellType()==cell4.CELL_TYPE_STRING)
		{
			data4=cell4.getStringCellValue();
		}
		
		String ddString=data+"\r\n"
				+ data2+"\r\n"
				+ data3+"\r\n"
				+ data4;
		
		
		return ddString;
	}
	
	
	
   public List<String> getTotalExcelData(String sheetName)
   {
	   String data=null;
	   sheet=getSheet(sheetName);
	   List<String> excelData=new ArrayList<String>();
	   
	   for(int i=0;i<sheet.getLastRowNum();i++)
	   {
		   row=sheet.getRow(i);
		   
		   for(int j=0;j<row.getLastCellNum();j++)
		   {
			   cell=row.getCell(j);
			   if(cell.getCellType()==cell.CELL_TYPE_NUMERIC)
			   {
				   data=cell.getNumericCellValue()+"";
				   excelData.add(data);
			   }
			   else if(cell.getCellType()==cell.CELL_TYPE_STRING)
			   {
				   data=cell.getStringCellValue();
				   excelData.add(data);
			   }
			   
		   }
	   }
	   
	   return excelData;
   }
   
   
   
   public List<String> getRowData(String sheetName, int rowNum)
   {
	   row=getSheet(sheetName).getRow(rowNum);
	   List<String> excelRowData=null;
	   String data=null;
	   
	   for(int i=0;i<row.getLastCellNum();i++)
	   {
		   cell=row.getCell(i);
		   
		   if(cell.getCellType()==cell.CELL_TYPE_NUMERIC)
		   {
			   data=cell.getNumericCellValue()+"";
			   excelRowData.add(data);
		   }
		   else if(cell.getCellType()==cell.CELL_TYPE_STRING)
		   {
			   data=cell.getStringCellValue();
			   excelRowData.add(data);
		   }
		   
	   }
	   return excelRowData;
   }
   
   //To Write the data in the excel sheet
  
   
   public void setCellData(String filePath,String sheetName,int rowNum,int cellNum,String data) throws IOException, EncryptedDocumentException, InvalidFormatException
   {
	   if(data.equalsIgnoreCase("pass") || data.equalsIgnoreCase("fail") )
	   {
		   if(data.equalsIgnoreCase("pass") )
		   {
			   this.filePath=filePath;
		
			   FileInputStream fip=new FileInputStream(filePath);
			   workbook =WorkbookFactory.create(fip);
			   
			   Sheet sheet=workbook.getSheet(sheetName);
			   
			   Row row=sheet.getRow(rowNum);
			   
			   Cell cell =row.createCell(cellNum);
			   cell.setCellValue(data);
			   
			   style = workbook.createCellStyle();
			   style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
			   style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			    
			   cell.setCellStyle(style);
		
			   FileOutputStream fop=new FileOutputStream(filePath);
		
			   workbook.write(fop);
			   
			   fip.close();
			   
			   fop.close();
		   }
		   else
		   {
			   this.filePath=filePath;

			   FileInputStream fip=new FileInputStream(filePath);
			   workbook =WorkbookFactory.create(fip);
			   
			   Sheet sheet=workbook.getSheet(sheetName);
			   
			   Row row=sheet.getRow(rowNum);
			   
			   Cell cell =row.createCell(cellNum);
			   
			   cell.setCellValue(data);
			   
			   style = workbook.createCellStyle();
			   style.setFillForegroundColor(IndexedColors.RED.getIndex());
			   style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			    
			   cell.setCellStyle(style);

			   FileOutputStream fop=new FileOutputStream(filePath);

			   workbook.write(fop);
			   
			   fip.close();
			   
			   fop.close();
		   }
	   }
	   else
	   {
		   this.filePath=filePath;

		   FileInputStream fip=new FileInputStream(filePath);
		   workbook =WorkbookFactory.create(fip);
		   
		   Sheet sheet=workbook.getSheet(sheetName);
		   
		   Row row=sheet.getRow(rowNum);
		   
		   Cell cell =row.getCell(cellNum);
		   
		   cell.setCellValue(data);
		   
		   FileOutputStream fop=new FileOutputStream(filePath);
	
		   workbook.write(fop);
		   
		   fip.close();
		   
		   fop.close();
	   }
   }
   
   
   
   
   public void copyExcelFile() throws IOException, EncryptedDocumentException, InvalidFormatException, InterruptedException
   {
	   //Provide the Path of excel file which we want to copy
	   File inputFile=new File(getBaseDir()+"\\src\\main\\resources\\testdata\\ExcelBackUp\\FocusTest.xlsx");
	   File outputFile=new File(getBaseDir()+"\\src\\main\\resources\\testdata\\FocusTestData.xlsx");
	   
	   if(outputFile.exists())
	   {
		   outputFile.delete();
	   }
	   
	   Thread.sleep(3000);
	   Files.copy(inputFile.toPath(), outputFile.toPath());
	   
   }	
   

   public void setExceptionInExcel(String filePath,String sheetName,int rowNum,int cellNum,String data) throws IOException, EncryptedDocumentException, InvalidFormatException
   {
	   this.filePath=filePath;

	   FileInputStream fip=new FileInputStream(filePath);
	   workbook =WorkbookFactory.create(fip);
	   
	   Sheet sheet=workbook.getSheet(sheetName);
	   
	   Row row=sheet.getRow(rowNum);

	   Cell cell =row.createCell(cellNum);
	   cell.setCellValue(data);
	   
	   style = workbook.createCellStyle();
	   style.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
	   style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	   
	   cell.setCellStyle(style);
			
	   FileOutputStream fop=new FileOutputStream(filePath);
			
	   workbook.write(fop);
	   
	   fip.close();
	   
	   fop.close();
   }

   
   public boolean checkExcelSheetsComparisonWithMonth(Workbook workbook1, Workbook workbook2,String date1)
   {
   	
   	boolean result;
   	 XSSFSheet sheet1 = (XSSFSheet) workbook1.getSheetAt(0);
   	    XSSFSheet sheet2 = (XSSFSheet) workbook2.getSheetAt(0);
   	    
   	    
   	    ArrayList<String> arrayList1 = new ArrayList<>();
   	    ArrayList<String> arrayList2 = new ArrayList<>();
   	    for (Row row1 : sheet1) {
   	        Iterator<Cell> cellIterator = row1.cellIterator();
   	        while (cellIterator.hasNext()) {
   	            Cell cell1 = cellIterator.next();
   	            DataFormatter dataFormatter = new DataFormatter();
   		          String cellValue = dataFormatter.formatCellValue(cell1);
   	            arrayList1.add(cellValue);
   	            }
   	        }
   	    
   	    System.out.println(arrayList1.toString());
   	    for (Row row2 : sheet2) {
   	        Iterator<Cell> cellIterator = row2.cellIterator();
   	        while (cellIterator.hasNext()) {
   	            Cell cell2 = cellIterator.next();
   	            
   	            DataFormatter dataFormatter = new DataFormatter();
   		          String cellValue = dataFormatter.formatCellValue(cell2);
   		          
   	            arrayList2.add(cellValue.replaceAll(date1, getCurrentDate()));
   	        }
   	    }
   	    
   	    System.out.println(arrayList2.toString());
   	if (arrayList1.equals(arrayList2)){
   		
   		result=true;
   	    System.out.println("the files are equal");
   	}
   	else{
   		result=false;
   	    System.out.println("NOT EQUAL");
   	}
   	return result;
   }
   
   
   
   public boolean verifyDataInCSVWithMonth(String file1,String file2,String date1) throws IOException
   {
       ArrayList al1=new ArrayList();
       ArrayList al2=new ArrayList();
      
       BufferedReader CSVFile1 = new BufferedReader(new FileReader(file1));
       String dataRow1 = CSVFile1.readLine();
       while (dataRow1 != null)
       {
           String[] dataArray1 = dataRow1.split(",");
           for (String item1:dataArray1)
           {
              al1.add(item1);
           }

           dataRow1 = CSVFile1.readLine(); // Read next line of data.
       }

        CSVFile1.close();

       BufferedReader CSVFile2 = new BufferedReader(new FileReader(file2));
       String dataRow2 = CSVFile2.readLine();
       while (dataRow2 != null)
       {
           String[] dataArray2 = dataRow2.split(",");
           for (String item2:dataArray2)
           {
              al2.add(item2.replaceAll(date1, getCurrentDate()));

           }
           dataRow2 = CSVFile2.readLine(); // Read next line of data.
       }
        CSVFile2.close();

        System.out.println("al1 : "+al1);
    	System.out.println("al2 : "+al2);
       
   	     
   if(al1.equals(al2))
   {
   	return true;
   }
   else
   {
   	return false;
   }
   }


   public boolean checkExcelSheetsComparisonWithMonth(Workbook workbook1, Workbook workbook2,String date1,String date2, String month)
   {
   	
   	boolean result;
   	 XSSFSheet sheet1 = (XSSFSheet) workbook1.getSheetAt(0);
   	    XSSFSheet sheet2 = (XSSFSheet) workbook2.getSheetAt(0);
   	    
   	    
   	    ArrayList<String> arrayList1 = new ArrayList<>();
   	    ArrayList<String> arrayList2 = new ArrayList<>();
   	    for (Row row1 : sheet1) {
   	        Iterator<Cell> cellIterator = row1.cellIterator();
   	        while (cellIterator.hasNext()) {
   	            Cell cell1 = cellIterator.next();
   	            DataFormatter dataFormatter = new DataFormatter();
   		          String cellValue = dataFormatter.formatCellValue(cell1);
   	            arrayList1.add(cellValue);
   	            }
   	        }
   	    
   	    System.out.println(arrayList1.toString());
   	    for (Row row2 : sheet2) {
   	        Iterator<Cell> cellIterator = row2.cellIterator();
   	        while (cellIterator.hasNext()) {
   	            Cell cell2 = cellIterator.next();
   	            
   	            DataFormatter dataFormatter = new DataFormatter();
   		          String cellValue = dataFormatter.formatCellValue(cell2);
   		          
   	            arrayList2.add(cellValue.replaceAll(date1, getCurrentDate()).replaceAll(date2, getCurrentMonthDate()).replaceAll(month, getCurrentMonth()));
   	        }
   	    }
   	    
   	    System.out.println(arrayList2.toString());
   	if (arrayList1.equals(arrayList2)){
   		
   		result=true;
   	    System.out.println("the files are equal");
   	}
   	else{
   		result=false;
   	    System.out.println("NOT EQUAL");
   	}
   	return result;
   }
   
   
   



public boolean checkExcelSheetsComparison(Workbook workbook1, Workbook workbook2,String date1)
{
	
	boolean result;
	 XSSFSheet sheet1 = (XSSFSheet) workbook1.getSheetAt(0);
	    XSSFSheet sheet2 = (XSSFSheet) workbook2.getSheetAt(0);
	    
	    
	    ArrayList<String> arrayList1 = new ArrayList<>();
	    ArrayList<String> arrayList2 = new ArrayList<>();
	    for (Row row1 : sheet1) {
	        Iterator<Cell> cellIterator = row1.cellIterator();
	        while (cellIterator.hasNext()) {
	            Cell cell1 = cellIterator.next();
	            DataFormatter dataFormatter = new DataFormatter();
		          String cellValue = dataFormatter.formatCellValue(cell1);
	            arrayList1.add(cellValue);
	            }
	        }
	    
	    System.out.println(arrayList1.toString());
	    for (Row row2 : sheet2) {
	        Iterator<Cell> cellIterator = row2.cellIterator();
	        while (cellIterator.hasNext()) {
	            Cell cell2 = cellIterator.next();
	            
	            DataFormatter dataFormatter = new DataFormatter();
		          String cellValue = dataFormatter.formatCellValue(cell2);
		          
	            arrayList2.add(cellValue.replaceAll(date1, getCurrentDate()));
	        }
	    }
	    
	    System.out.println(arrayList2.toString());
	if (arrayList1.equals(arrayList2)){
		
		result=true;
	    System.out.println("the files are equal");
	}
	else{
		result=false;
	    System.out.println("NOT EQUAL");
	}
	return result;
}


}

