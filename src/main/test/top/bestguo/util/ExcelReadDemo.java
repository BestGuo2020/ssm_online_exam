package top.bestguo.util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelReadDemo {

    @Test
    public void testRead01() throws IOException {

        // 获取文件流
        FileInputStream fis = new FileInputStream("d:\\excel_demo\\demo.xls");
        // 创建工作簿
        HSSFWorkbook workbook = new HSSFWorkbook(fis);
        // 创建工作表
        HSSFSheet sheet0 = workbook.getSheet("工作表1");
        // 获取第 0 行
        HSSFRow row = sheet0.getRow(0);
        // 获取第 0,1 个单元格
        HSSFCell cell0 = row.getCell(0);
        HSSFCell cell1 = row.getCell(1);
        // 获取单元格中的值
        String stringCellValue = cell0.getStringCellValue();
        double numericCellValue = cell1.getNumericCellValue();
        System.out.println(stringCellValue + ":" + numericCellValue);

        fis.close();
        workbook.close();

    }

    /**
     * 读取 99 乘法表
     */
    @Test
    public void testRead02() throws IOException {
        // 获取文件流
        FileInputStream fis = new FileInputStream("d:\\excel_demo\\demo2.xls");
        // 创建工作簿对象
        HSSFWorkbook workbook = new HSSFWorkbook(fis);
        // 得到工作表
        HSSFSheet sheet = workbook.getSheet("99乘法表");
        for (int i = 1; i < 10; i++) {
            // 得到行
            HSSFRow row = sheet.getRow(i - 1);
            for (int j = 1; j < i + 1; j++) {
                HSSFCell cell = row.getCell(j - 1);
                System.out.print(cell.getStringCellValue() + "\t");
            }
            System.out.println();
        }
        fis.close();
        workbook.close();
    }

}
