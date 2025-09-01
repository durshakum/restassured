package util;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.lang.reflect.Method;
import java.security.PublicKey;

public class ExcelUtility {
	
public void excelData() throws IOException{
	
	String file = "C:\\Users\\admin\\eclipse-workspace\\selenium-java\\src\\test\\resources\\data\\testdata.xlsx";
	FileInputStream fileInputStream = new FileInputStream(file);
	XSSFWorkbook wb	= new XSSFWorkbook(fileInputStream);
	Sheet sheet = wb.getSheetAt(0);
	DataFormatter formatter = new DataFormatter();
	
	int rowsCount = sheet.getLastRowNum();
	int colCount = sheet.getRow(0).getLastCellNum();
	
	
	
}
	
	
	

}
