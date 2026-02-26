package com.absolooplab.Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

    private FileInputStream fi;
    private FileOutputStream fo;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private XSSFRow row;
    private XSSFCell cell;
    private CellStyle style;
    private String path;

    // ✅ Open workbook ONLY ONCE
    public ExcelUtility(String path) throws IOException {
        this.path = path;
        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
    }

    public int getRowCount(String sheetName) {
        sheet = workbook.getSheet(sheetName);
        return sheet.getLastRowNum();
    }

    public int getCellCount(String sheetName, int rownum) {
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rownum);
        return row.getLastCellNum();
    }

    public String getCellData(String sheetName, int rownum, int colnum) {
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rownum);
        cell = row.getCell(colnum);

        DataFormatter formatter = new DataFormatter();
        try {
            return formatter.formatCellValue(cell);
        } catch (Exception e) {
            return "";
        }
    }

    public void setCellData(String sheetName, int rownum, int colnum, String data) throws IOException {
        File xlfile = new File(path);

        if (!xlfile.exists()) {
            workbook = new XSSFWorkbook();
            fo = new FileOutputStream(path);
            workbook.write(fo);
        }

        sheet = workbook.getSheet(sheetName);
        if (sheet == null)
            sheet = workbook.createSheet(sheetName);

        row = sheet.getRow(rownum);
        if (row == null)
            row = sheet.createRow(rownum);

        cell = row.createCell(colnum);
        cell.setCellValue(data);

        fo = new FileOutputStream(path);
        workbook.write(fo);
        fo.close();
    }

    public void fillGreenColor(String sheetName, int rownum, int colnum) {
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rownum);
        cell = row.getCell(colnum);

        style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(style);
    }

    public void fillRedColor(String sheetName, int rownum, int colnum) {
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rownum);
        cell = row.getCell(colnum);

        style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.RED.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(style);
    }

    // ✅ IMPORTANT — Close workbook once work is done
    public void closeWorkbook() throws IOException {
        workbook.close();
        fi.close();
    }
}