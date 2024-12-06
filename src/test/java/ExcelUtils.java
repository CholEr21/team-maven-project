import org.apache.poi.ss.usermodel.*;
import java.io.FileInputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {
    public static List<String[]> readExcelData(String filePath, String sheetName) {
        List<String[]> data = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = WorkbookFactory.create(fis)) {
             
            Sheet sheet = workbook.getSheet(sheetName);
            // Start from row 1 (0-indexed), so row 2 in Excel
            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                if (row != null) {
                    String[] rowData = new String[row.getPhysicalNumberOfCells()];
                    for (int i = 0; i < row.getPhysicalNumberOfCells(); i++) {
                        Cell cell = row.getCell(i);
                        if (cell.getCellType() == CellType.NUMERIC) {
                            rowData[i] = String.valueOf((int) cell.getNumericCellValue());
                        } else {
                            rowData[i] = cell.toString();
                        }
                    }
                    data.add(rowData);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}
