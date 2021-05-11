package testNGPracticePackage;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class TestNGDataProvider {

	public static void main(String[] args) {
		DataReader drObject = new DataReader();
		drObject.readData();
		drObject.iteratealObject();
	}			
}

class DataReader{

	FileInputStream fis=null;
	XSSFWorkbook wb= null;
	XSSFSheet ws= null;
	ArrayList<String> alObject= null;
	ArrayList<String> alObject2= null;
	public void readData() {
		try {
			File fileName = new File ("./DataFile/TestNGDataFile.xlsx");
			fis = new FileInputStream(fileName);

			wb = new XSSFWorkbook(fis);
			ws = wb.getSheetAt(0);

			int rowCount = ws.getLastRowNum();
			System.out.println("Row count is: "+rowCount);
			int colCount = ws.getRow(0).getLastCellNum();
			System.out.println("Column count is: "+colCount);

			Iterator<Row> rowItr = ws.iterator();
			alObject = new ArrayList<String>();
			while(rowItr.hasNext()) {
				Row row = rowItr.next();
				Iterator<Cell> celItr = row.cellIterator();
				String stringData = null;
				while(celItr.hasNext()) {
					Cell data = celItr.next();

					stringData =data.getStringCellValue(); 
					System.out.println("Data fetched in 'stringData' is: "+ stringData);
					alObject.add(stringData);
				}
				System.out.println("alObject contains: "+alObject);

			}
			System.out.println("Size of alObject is: "+alObject.size());
		}
		catch(Exception e) {
			e.printStackTrace();
		}		
	}
	public void iteratealObject() {
		int rowCount = ws.getLastRowNum();
		System.out.println("Row count is: "+rowCount);
		int colCount = ws.getRow(0).getLastCellNum();
		System.out.println("Column count is: "+colCount);
		alObject2 = new ArrayList<String>();
		alObject2= alObject;
	}
}