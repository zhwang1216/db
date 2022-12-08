package com.zhwang.drug.util;

import java.util.List;

/**
 * 表格导出的接口
 */
public interface ExcelFromInterface {
    public void excel(String title, String[] rowName, List<Object[]> list, String url);
}
