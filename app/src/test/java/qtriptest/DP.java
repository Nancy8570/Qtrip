package qtriptest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class DP {
    @DataProvider (name = "data-provider")
    public Object[][] dpMethod (Method m) throws IOException{

        String currentDir = System.getProperty("user.dir");
        String testDataExcelPath = currentDir + "/src/test/resources/";
        String fileName = testDataExcelPath + "DatasetsforQTrip.xlsx";
        
        FileInputStream ExcelFile = new FileInputStream(fileName);
        XSSFWorkbook workBook = new XSSFWorkbook(ExcelFile);
        XSSFSheet sheet;

        switch(m.getName()) {
            case "TestCase01": 
                sheet = workBook.getSheet("TestCase01");
                return new Object[][] {
                    {sheet.getRow(1).getCell(1).toString(),sheet.getRow(1).getCell(2).toString()},
                    {sheet.getRow(2).getCell(1).toString(),sheet.getRow(2).getCell(2).toString()},
                    {sheet.getRow(3).getCell(1).toString(),sheet.getRow(3).getCell(2).toString()}
                };
            case "TestCase02":
                sheet = workBook.getSheet("TestCase02");
                return new Object[][] {
                    {sheet.getRow(1).getCell(1).toString(),sheet.getRow(1).getCell(2).toString(),sheet.getRow(1).getCell(3).toString(),sheet.getRow(1).getCell(4).toString(),sheet.getRow(1).getCell(5).toString()},
                    {sheet.getRow(2).getCell(1).toString(),sheet.getRow(2).getCell(2).toString(),sheet.getRow(2).getCell(3).toString(),sheet.getRow(2).getCell(4).toString(),sheet.getRow(2).getCell(5).toString()},
                    {sheet.getRow(3).getCell(1).toString(),sheet.getRow(3).getCell(2).toString(),sheet.getRow(3).getCell(3).toString(),sheet.getRow(3).getCell(4).toString(),sheet.getRow(3).getCell(5).toString()}
            };
            case "TestCase03":
                sheet = workBook.getSheet("TestCase03");
                return new Object[][] {
                    {sheet.getRow(1).getCell(1).toString(),sheet.getRow(1).getCell(2).toString(),sheet.getRow(1).getCell(3).toString(),sheet.getRow(1).getCell(4).toString(),sheet.getRow(1).getCell(5).toString(),sheet.getRow(1).getCell(6).toString(),sheet.getRow(1).getCell(7).toString()},
                    {sheet.getRow(2).getCell(1).toString(),sheet.getRow(2).getCell(2).toString(),sheet.getRow(2).getCell(3).toString(),sheet.getRow(2).getCell(4).toString(),sheet.getRow(2).getCell(5).toString(),sheet.getRow(2).getCell(6).toString(),sheet.getRow(2).getCell(7).toString()},
                    {sheet.getRow(3).getCell(1).toString(),sheet.getRow(3).getCell(2).toString(),sheet.getRow(3).getCell(3).toString(),sheet.getRow(3).getCell(4).toString(),sheet.getRow(3).getCell(5).toString(),sheet.getRow(3).getCell(6).toString(),sheet.getRow(3).getCell(7).toString()}
                };
            case "TestCase04":
                sheet = workBook.getSheet("TestCase04");
                return new Object[][] {
                    {sheet.getRow(1).getCell(1).toString(),sheet.getRow(1).getCell(2).toString(),sheet.getRow(1).getCell(3).toString(),sheet.getRow(1).getCell(4).toString(),sheet.getRow(1).getCell(5).toString()},
                    {sheet.getRow(2).getCell(1).toString(),sheet.getRow(2).getCell(2).toString(),sheet.getRow(2).getCell(3).toString(),sheet.getRow(2).getCell(4).toString(),sheet.getRow(1).getCell(5).toString()},
                    {sheet.getRow(3).getCell(1).toString(),sheet.getRow(3).getCell(2).toString(),sheet.getRow(3).getCell(3).toString(),sheet.getRow(3).getCell(4).toString(),sheet.getRow(1).getCell(5).toString()}
                };
        }
        return null;
}
}
