package utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelReader {
    private Workbook workbook;
    private Sheet sheet;

    public ExcelReader(String filepath , String sheetName) throws IOException {
        FileInputStream fs = new FileInputStream(filepath);
        this.workbook = new XSSFWorkbook(fs);
        this.sheet = workbook.getSheet(sheetName);
    }

    public String GetCellData(int row , int column){
        Cell cell = this.sheet.getRow(row).getCell(column);
        return cell.toString();
    }

    public int GetNumberOfRows(){
        return sheet.getPhysicalNumberOfRows();
    }
}
