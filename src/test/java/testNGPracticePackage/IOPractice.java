package testNGPracticePackage;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class IOPractice {

	public static void main(String[] args) {
		ReadExcel reader= new ReadExcel();
		ArrayList<String> recieverAl= reader.readData();
		for (int i=3; i<=recieverAl.size()-3; i++) {
			System.out.println();
			String username= recieverAl.get(i).toString();
			String password= recieverAl.get(i+1).toString();
			String team= recieverAl.get(i+2).toString();
	
			System.out.print(username+" "+password+" "+team);
			
		}
	}
}

class ReadExcel{

	FileInputStream fis=null;
	XSSFWorkbook wb= null;
	XSSFSheet ws= null;

	public ArrayList<String> readData() {
		ArrayList<String> al= new ArrayList<String>();
		try {
			File fileName = new File ("./DataFile/TestNGDataFile.xlsx");
			fis = new FileInputStream(fileName);

			wb = new XSSFWorkbook(fis);
			ws = wb.getSheetAt(0);
			int rowCount = ws.getLastRowNum(); // gives total row count
			int colCount = ws.getRow(0).getLastCellNum(); // gives total column count
			Iterator<Row> itrRow= ws.iterator();
			
			while (itrRow.hasNext()) {
				Row row = itrRow.next();
				Iterator<Cell> celItr= row.cellIterator();
				while (celItr.hasNext()) {
					String value= celItr.next().getStringCellValue();
					System.out.print("\t"+value);
					al.add(value);
				}
				System.out.println();
			}
			System.out.println("Size of al is: "+al.size());
			System.out.println("al contains: "+al);
			System.out.println("Below are the test data: ");
			
			/*
			for (int i=3; i<=al.size()-3; i++) {
				System.out.println();
				String username= al.get(i).toString();
				String password= al.get(i+1).toString();
				String team= al.get(i+2).toString();
		
				System.out.print(username+" "+password+" "+team);
				
			}
			*/
		}
		catch(Exception e) {
			e.printStackTrace();
		}	
		return al;
	}
}