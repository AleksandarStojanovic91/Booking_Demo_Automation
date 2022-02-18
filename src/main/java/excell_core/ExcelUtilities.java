package excell_core;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExcelUtilities {

    public Map<String, String> getRowData(String fileName, String sheetName, String row) throws IOException {
        FileInputStream fis = new FileInputStream(fileName);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet(sheetName);
        int rowNum = Integer.parseInt(row);
        int lastColumnNum = sheet.getRow(1).getLastCellNum();
        Map<String, String> data = new HashMap<>();

        for (int i = 0; i < lastColumnNum; i++) {
            String key;
            String value;

            key = sheet.getRow(1).getCell(i).getStringCellValue().trim();
            value = sheet.getRow(rowNum + 1).getCell(i).getStringCellValue().trim();

            data.put(key, value);
        }
        return data;
    }

}
