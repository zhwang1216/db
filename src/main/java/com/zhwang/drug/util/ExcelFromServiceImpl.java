package com.zhwang.drug.util;

import java.io.FileOutputStream;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * 表格导出的实现类,表格导出，导入
 */
public class ExcelFromServiceImpl implements ExcelFromInterface {

    @Override
    public void excel(String title, String[] rowName, List<Object[]> list, String url) {
        String message = "";
        HSSFWorkbook wb = null;
        try {
            // 第一步，创建一个webbook，对应一个Excel文件
            wb = new HSSFWorkbook();
            // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
            HSSFSheet sheet = wb.createSheet(title);
            // 第三步，在sheet中添加表头第0行
            HSSFRow row = sheet.createRow((int) 0);
            // 第四步，创建单元格，并设置值表头
            HSSFCell cell = row.createCell((short) 0);
            for (int i = 0; i < rowName.length; i++) {
                cell.setCellValue(rowName[i]);
                cell = row.createCell((short) (i + 1));
            }
            // 第五步，写入实体数据实际应用中这些数据从数据库得到，
            for (int i = 0; i < list.size(); i++) {
                row = sheet.createRow((int) i + 1);
                Object[] obj = list.get(i);//遍历每个对象
                for (int j = 0; j < obj.length; j++) {
                    row.createCell((short) j).setCellValue(obj[j].toString());
                }
                try {
                    FileOutputStream fout = new FileOutputStream(url);
                    wb.write(fout);
                    fout.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            message = "已导出到" + url;
        } catch (Exception e) {
            message = "导出失败";
        }
        JOptionPane.showMessageDialog(null, message);
    }

}
