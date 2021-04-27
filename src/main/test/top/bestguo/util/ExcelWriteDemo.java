package top.bestguo.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.joda.time.DateTime;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelWriteDemo {

    @Test
    public void test1() throws IOException {
        // 创安静工作不
        Workbook workbook = new HSSFWorkbook();
        // 创建工作表
        Sheet sheet = workbook.createSheet("工作表1");
        // 创建一个行
        Row row = sheet.createRow(0);
        // 创建一个单元格
        Cell cell1 = row.createCell(0);
        cell1.setCellValue("今日新增观众");
        Cell cell2 = row.createCell(1);
        cell2.setCellValue(666);

        // 创建一个行
        Row row2 = sheet.createRow(1);
        // 创建一个单元格
        Cell cell21 = row2.createCell(0);
        cell21.setCellValue("统计时间");
        Cell cell22 = row2.createCell(1);
        cell22.setCellValue(new DateTime().toString("yyyy-MM-dd HH:mm:ss"));

        FileOutputStream fileOutputStream = new FileOutputStream("d:\\excel_demo\\demo.xls");
        workbook.write(fileOutputStream);
        fileOutputStream.close();

    }

    /**
     * 99 乘法表
     * @throws IOException
     */
    @Test
    public void printMultiplicationsTable() throws IOException {
        // 创建工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 创建工作表
        Sheet sheet = workbook.createSheet("99乘法表");
        for(int i = 1; i <= 9; i++) {
            // 创建行
            Row row = sheet.createRow(i - 1);
            for (int j = 1; j <= i; j++) {
                // 创建列（创建单元格）
                Cell cell = row.createCell(j - 1);
                cell.setCellValue(i + " × " + j + " = " + (i * j));
            }
        }
        // 打开输出流
        FileOutputStream fileOutputStream = new FileOutputStream("d:\\excel_demo\\demo2.xls");
        workbook.write(fileOutputStream);
        fileOutputStream.close();
    }

}
