package testNGPracticePackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderPractice {
	FileInputStream fis=null;
	XSSFWorkbook workBook= null;
	XSSFSheet workSheet= null;
	ArrayList arrayList = null;
	/*
	@BeforeTest
	public void soruceFile() throws FileNotFoundException {
		System.out.println("Inside Before method");
		fis = new FileInputStream(new File ("./DataFile/TestNGDataFile.xlsx"));
	}
	 */

	@Test(dataProvider = "DataSource")
	public void getDatafromDataProvider() {
		System.out.println("Inside Test method");
	}

	@DataProvider(name= "DataSource")
	public void readData() throws IOException {
		fis = new FileInputStream(new File ("./DataFile/TestNGDataFile.xlsx"));
		workBook = new XSSFWorkbook(fis);
		workSheet = workBook.getSheetAt(0);

		Iterator<Row> rowItr = workSheet.iterator();
		while(rowItr.hasNext()) {
			Row row = (Row) rowItr.next();
			
			Iterator<Cell> cellItr = row.cellIterator();
			while(cellItr.hasNext()) {
				//Cell cellData = (Cell) cellItr.next();
				String data= cellItr.next().toString();
				arrayList= new ArrayList();
				arrayList.add(data);
			}
		}
		System.out.println("Data is ArrayList is: "+arrayList);
		/*
		//evaluating cell type   
		FormulaEvaluator formulaEvaluator=workBook.getCreationHelper().createFormulaEvaluator();  
		for(Row row: workSheet)     //iteration over row using for each loop  
		{  
		for(Cell cell: row)    //iteration over cell using for each loop  
		{  
		switch(formulaEvaluator.evaluateInCell(cell).getCellType())  
		{  
		case Cell.CELL_TYPE_NUMERIC:   //field that represents numeric cell type  
		//getting the value of the cell as a number  
		System.out.print(cell.getNumericCellValue()+ "\t\t");   
		break;  
		case Cell.CELL_TYPE_STRING:    //field that represents string cell type  
		//getting the value of the cell as a string  
		System.out.print(cell.getStringCellValue()+ "\t\t");  
		break;  
		}  
		}  
		System.out.println();  
		}
		 */  
	}
}