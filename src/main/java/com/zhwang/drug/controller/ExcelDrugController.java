package com.zhwang.drug.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zhwang.drug.service.IDrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhwang.drug.entity.DrugANDDrugCategory;
import com.zhwang.drug.util.ExcelFromServiceImpl;
import com.zhwang.drug.util.ExcelFromInterface;
import com.zhwang.drug.util.ResponseResult;

@RestController
@RequestMapping("/excel")
public class ExcelDrugController extends BaseController {

    @Autowired    //自动装配
    private IDrugService drugService;

    @RequestMapping("/excelDrug")
    public ResponseResult<Void> ExcelDrug(
            String drugName, String unit, String origin, Integer categoryId) {
        System.err.println(drugName + "," + unit + "," + origin + "," + categoryId);
        String title = "药品表";
        String[] rowName = new String[]{"药品名", "药品类别", "条形码", "简称", "规格", "单位", "产地", "批准文号", "进货价", "销售价", "库存", "销售总量", "药品备注"};

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("drugName", drugName);
        map.put("unit", unit);
        map.put("origin", origin);
        map.put("categoryId", categoryId);
        List<DrugANDDrugCategory> drugs = drugService.findselectIsdelete(map);
        List<Object[]> list = new ArrayList<Object[]>();
        String url = "D:/药品数据表.xls";
        Object[] objs = null;
        for (DrugANDDrugCategory drug : drugs) {
            System.err.println(drug);
            objs = new Object[rowName.length];
            objs[0] = drug.getDrugName();
            objs[1] = drug.getCategoryName();
            objs[2] = drug.getBarCode();
            objs[3] = drug.getReferred();
            objs[4] = drug.getSpecifications();
            objs[5] = drug.getUnit();
            objs[6] = drug.getOrigin();
            objs[7] = drug.getApprovalNumber();
            objs[8] = drug.getPleasedTo();
            objs[9] = drug.getSalesPrice();
            objs[10] = drug.getInventory();
            objs[11] = drug.getTotalSales();
            objs[12] = drug.getDrugNote();
            list.add(objs);
        }
        try {
            ExcelFromInterface excelInterface = new ExcelFromServiceImpl();
            excelInterface.excel(title, rowName, list, url);
        } catch (Exception e) {
        }
        return new ResponseResult<Void>(SUCCESS);
    }
}
