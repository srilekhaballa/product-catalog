package com.shoppingcart.productcatalog.utils;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.shoppingcart.productcatalog.model.Product;

public class ProductExcelExporter {
private XSSFWorkbook workbook;
private XSSFSheet sheet;
private List<Product> listProducts;
 
public ProductExcelExporter(List<Product> listProducts) {
    this.listProducts = listProducts;
    workbook = new XSSFWorkbook();
}


private void writeHeaderLine() {
    sheet = workbook.createSheet("Product");
     
    XSSFRow row = sheet.createRow(0);
     
    CellStyle style = workbook.createCellStyle();
    XSSFFont font = workbook.createFont();
    font.setBold(true);
    font.setFontHeight(16);
    style.setFont(font);
     
    createCell(row, 0, "product Id", style);      
    createCell(row, 1, "product Name",style);       
    createCell(row, 2, "description", style);    
    createCell(row, 3, "created By", style);
    createCell(row, 4, "created On", style);
    createCell(row, 5, "updated By", style);
    createCell(row, 6, "updated On", style);
    createCell(row, 7, "category Id", style);
}
 
private void createCell(XSSFRow row, int columnCount, Object value, CellStyle style) {
    sheet.autoSizeColumn(columnCount);
    XSSFCell cell = row.createCell(columnCount);
    if (value instanceof Integer) {
        cell.setCellValue((Integer) value);
    } else if (value instanceof Date) {
        cell.setCellValue((Date) value);
    }else {
        cell.setCellValue((String) value);
    }
    cell.setCellStyle(style);
}
 
private void writeDataLines() {
    int rowCount = 1;

    CellStyle style = workbook.createCellStyle();
    XSSFFont font = workbook.createFont();
    font.setFontHeight(14);
    style.setFont(font);
             
    for (Product product : listProducts) {
        XSSFRow row = sheet.createRow(rowCount++);
        int columnCount = 0;
         
        createCell(row, columnCount++, product.getProductId(), style);
        createCell(row, columnCount++, product.getProductName(), style);
        createCell(row, columnCount++, product.getDescription(), style);
        createCell(row, columnCount++, product.getCreatedOn(), style);
        createCell(row, columnCount++, product.getCreatedBy(), style);
        createCell(row, columnCount++, product.getUpdatedBy(), style);
        createCell(row, columnCount++, product.getUpdatedOn(), style);
        createCell(row, columnCount++, product.getCategory().getCategoryId(), style);
    }
}
 
public void export(HttpServletResponse response) throws IOException {
    writeHeaderLine();
    writeDataLines();
    ServletOutputStream outputStream = response.getOutputStream();
    workbook.write(outputStream);
    workbook.close();
    outputStream.close();
	}
}
