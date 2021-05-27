package commonLibrary;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class ExcelReader {

	//Method to interact with excel file
	@Test
	public void dataReader() {
		try {
			FileInputStream fis=new FileInputStream("./DataFile/TestNGDataFile.xlsx");
			XSSFWorkbook wb=new XSSFWorkbook(fis);
			XSSFSheet ws=wb.getSheetAt(0);
			int rowCount=ws.getLastRowNum(); System.out.println(rowCount);
			int colCount=ws.getRow(0).getLastCellNum();System.out.println(colCount);
			HashMap<String, String> hm=new HashMap<String, String>();
			for(int i=0; i<rowCount; i++) {
				for(int j=0; j<colCount; j++) {
					hm.put(ws.getRow(i).getCell(j).getStringCellValue(), ws.getRow(i+1).getCell(j).getStringCellValue());
				}
			}
			System.out.println(hm.entrySet());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
