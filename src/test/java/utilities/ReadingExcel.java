package utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.testng.Assert;

public class ReadingExcel {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
//		ReadingExcel excelReader = new ReadingExcel(System.getProperty("user.dir")+"/src/test/resources/excelTestData");
//		Object[][] myData = excelReader.readExcelData("LoanCalcTest");
//		
//		System.out.println(myData[1][0]);
//		System.out.println(myData[1][1]);
//		System.out.println(myData[1][2]);
//		System.out.println(myData[1][3]);
//		System.out.println(myData[1][4]);
//		System.out.println(myData[1][5]);
		
	}

	public String path;
	public static FileInputStream fis = null;
	private Workbook workbook;
	private Sheet sheet;

	public ReadingExcel(String dataFile) {
		this.path = dataFile;
		try {
			fis = new FileInputStream(path);
			workbook = WorkbookFactory.create(fis);

			fis.close();
		} catch (Exception e) {
			Assert.fail("Excel Data File Not Found! Invalid File Path: " + " path");
		}
	}

	public Object[][] readExcelData(String sheetName)
		throws EncryptedDocumentException, IOException{
		
		if(workbook.getSheet(sheetName) != null) {
			sheet = workbook.getSheet(sheetName);
		}else {
			sheet = null;
			Assert.fail("provided sheet name is invalid - "+sheetName);
			return null;
		}
		//get row count
		int rowCount = (sheet.getLastRowNum());
		
		//get col count --> looking at the 1st row which should be the header row to see how many columns there are
		int colCount = sheet.getRow(0).getLastCellNum();
		
		//creating a 2 dimensional array
		Object[][] data = new Object[rowCount][colCount];
		
		//iterating through each row in the sheet
		for (int row = 0; row < rowCount; row++) {
			
			//interating through each cell in the current row
			for (int col = 0; col < colCount; col++) {
				
				//accessing the cell
				Cell cell = sheet.getRow(row+1).getCell(col);
				
				//Determining the Cell type to retrieve the appropriate cell data
				//then storing the cell data into the 2-dimensional array
				switch (cell.getCellType()){
					case STRING:
						data[row][col] = cell.getStringCellValue();
						break;
					case NUMERIC:
						data[row][col] = NumberToTextConverter.toText(cell.getNumericCellValue());
						break;
					case BLANK:
						data[row][col] = "BLANK CELL!";
						break;
					default:
						data[row][col] = cell.getStringCellValue();
				}
			}
		}
		return data;
	}
}	
		