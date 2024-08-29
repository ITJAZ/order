
package io.almonds.util;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import io.almonds.entity.Order;

public class ExcelExporter implements Exporter {

  @Override
  public void export(OutputStream os, Collection<Order> orders) {
    try (Workbook wb = createWorkbook(orders)) {
      wb.write(os);
      System.out.println("Excel exported");
    } catch (IOException e) {
      System.err.println("Excel export failed");
      System.err.println(e.getMessage());
    }
  }

  private Workbook createWorkbook(Collection<Order> orders) {
    Workbook wb = new SXSSFWorkbook(100);

    Sheet sheet = wb.createSheet("Order");

    int rowIndex = 0;
    Row header = sheet.createRow(rowIndex++);
    int cellIndex = 0;
    header.createCell(cellIndex++).setCellValue("Order No");
    header.createCell(cellIndex++).setCellValue("Item");
    header.createCell(cellIndex++).setCellValue("Sugar Level");
    header.createCell(cellIndex++).setCellValue("Ice Level");
    header.createCell(cellIndex++).setCellValue("Price");

    for (Order order : orders) {
      Row row = sheet.createRow(rowIndex++);
      cellIndex = 0;
      row.createCell(cellIndex++).setCellValue(order.getOrderNo());
      row.createCell(cellIndex++).setCellValue(order.getItem());
      row.createCell(cellIndex++).setCellValue(order.getSugarLevel());
      row.createCell(cellIndex++).setCellValue(order.getIceLevel());
      row.createCell(cellIndex++).setCellValue(order.getPrice().doubleValue());
    }

    return wb;
  }

  @Override
  public String getExtensionName() {
    return "xlsx";
  }

}