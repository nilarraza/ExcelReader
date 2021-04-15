package com.excel.demo.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Component;

import com.excel.demo.excelresponse.SheetResponse;
import com.excel.demo.excelresponse.TabResponse;
import com.excel.demo.model.TelecomAdSpend;

@Component
public class ExcelUtil {
	

	public void getSheetDetails(String excelPath)
			throws EncryptedDocumentException, InvalidFormatException, IOException {
		
		// Creating a Workbook from an Excel file (.xls or .xlsx)
		Workbook workbook = WorkbookFactory.create(new File(excelPath));

		// Retrieving the number of sheets in the Workbook
		System.out.println("Workbook has " + workbook.getNumberOfSheets() + " Sheets : ");

		System.out.println("Retrieving Sheets using for-each loop");
		
		for (Sheet sheet : workbook) {
			System.out.println("=> " + sheet.getSheetName());
		}
	}
	
	

	public SheetResponse readExcelSheet(String excelPath)
			throws EncryptedDocumentException, InvalidFormatException, IOException {

		List<String> list = new ArrayList<String>();

		// Creating a Workbook from an Excel file (.xls or .xlsx)
		Workbook workbook = WorkbookFactory.create(new File(excelPath));

		int x = workbook.getNumberOfSheets();

		SheetResponse response = new SheetResponse();
		List<TabResponse> listoftabs = new ArrayList<TabResponse>();

		// Getting the Sheet at index zero
		for (int i = 0; i < x; i++) {

			TabResponse tabresponse = new TabResponse();
			List<TelecomAdSpend> adSpendList = null;

			Sheet sheet1 = workbook.getSheetAt(i);

			int noOfColumns = sheet1.getRow(2).getLastCellNum();
			

			// Create a DataFormatter to format and get each cell's value as String
			DataFormatter dataFormatter = new DataFormatter();

			System.out.println("The sheet number is " + i + 1);
			// 2. Or you can use a for-each loop to iterate over the rows and columns
			System.out.println("\n\nIterating over Rows and Columns using for-each loop\n");

			for (Row row : sheet1) {
				System.out.println(row.getRowNum());
				if (row.getRowNum() == 0 || row.getRowNum() == 1) {
					continue; // just skip the rows if row number is 0 or 1
				}

				for (Cell cell : row) {
					cell.setCellType(CellType.STRING);

					String cellValue = dataFormatter.formatCellValue(cell);
					System.out.print(cellValue + "\t");
					list.add(cellValue);

				}
				System.out.println();
				
				
			} // loop
			adSpendList = createList(list, noOfColumns);System.out.println(list.get(0)+" "+list.get(1));
			tabresponse.setListAdSpend(adSpendList);	
			
			listoftabs.add(tabresponse);
		}

		response.setListSheetAdSpend(listoftabs);
		return response;
	}
	
	

	private List<TelecomAdSpend> createList(List<String> excelData, int noOfColumns) {

		ArrayList<TelecomAdSpend> telAdSpendList = new ArrayList<TelecomAdSpend>();
		
		int i = 0;
		do {
			TelecomAdSpend adSpend = new TelecomAdSpend();

			adSpend.setYear(excelData.get(i)); //System.out.println(excelData.get(i)+"testing");
			adSpend.setTotal(excelData.get(i + 1));
			adSpend.setNewspaper(excelData.get(i + 2));
			adSpend.setMagazine(excelData.get(i + 3));
			adSpend.setTv(excelData.get(i + 4));
			adSpend.setRadio(excelData.get(i + 5));
			adSpend.setCinema(excelData.get(i + 6));
			adSpend.setOoh(excelData.get(i + 7));
			adSpend.setDigital(excelData.get(i + 8));

			telAdSpendList.add(adSpend);
			i = i + (noOfColumns);

		} while (i < excelData.size());
		return telAdSpendList;
	}
}
