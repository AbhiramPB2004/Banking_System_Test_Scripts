package utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ExcelReader {
    private Workbook workbook;
    private Sheet sheet;

    public ExcelReader(String filepath , String sheetName) throws FileNotFoundException {
        FileInputStream fs = new FileInputStream(filepath);
        this.workbook = new XSSFWorkbook();
        sheet = workbook.getSheet(sheetName);
    }

    public String GetCellData(int row , int column){
        Cell cell = sheet.getRow(row).getCell(column);
        return cell.toString();
    }
}
