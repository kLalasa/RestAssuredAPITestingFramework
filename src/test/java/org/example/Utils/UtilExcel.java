package org.example.Utils;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class UtilExcel {

    static Workbook book;
    static Sheet sheet;

    // Correct the file path with a proper separator
    public static String SHEET_PATH = System.getProperty("user.dir") + "/src/test/java/org/example/Resources/TestData.xlsx";

    public static Object[][] getTestDataFromExcel(String sheetName) throws IOException {
        FileInputStream file = null;
        try {
            file = new FileInputStream(SHEET_PATH);
            book = WorkbookFactory.create(file);
            sheet = book.getSheet(sheetName);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Excel file not found at path: " + SHEET_PATH, e);
        } catch (IOException e) {
            throw new RuntimeException("Error reading the Excel file at path: " + SHEET_PATH, e);
        } finally {
            if (file != null) {
                try {
                    file.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // Read the data from the sheet
        Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
        for (int i = 0; i < sheet.getLastRowNum(); i++) {
            for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
                data[i][j] = sheet.getRow(i + 1).getCell(j).toString();
            }
        }

        return data;
    }

    @DataProvider(name = "getData")
    public Object[][] getData() throws IOException {
        return getTestDataFromExcel("Sheet1");
    }
}