package com.zhwang.drug.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.zhwang.drug.entity.DrugSales;
import com.zhwang.drug.entity.DrugSalesANDCustomer;
import com.zhwang.drug.entity.domain.PaginationVO;
import com.zhwang.drug.service.IDrugSalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.zhwang.drug.util.JsonDateValueProcessor;
import com.zhwang.drug.util.ResponseResult;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@RestController
@RequestMapping("/drugSales")
public class DrugSalesController extends BaseController {
    @Autowired    //自动装配
    private IDrugSalesService drugSalesService;

    /**
     * 添加销售数据
     *
     * @return
     */
    @RequestMapping("/insertDrugSales")
    public ResponseResult<Void> addDrugSales(String ids, String number, HttpSession session) {
        DrugSales drugSales = new DrugSales();
        drugSales.setInventoryQuantity(Integer.parseInt(number));    //添加销售数量
        drugSales.setCustomerId(getUidFromSession(session));        //添加客户id
        String[] id = ids.split(",");
        String username = session.getAttribute("username").toString();
        drugSalesService.addDrugSales(id, drugSales, username);

        return new ResponseResult<Void>(SUCCESS);
    }


    /**
     * 查询数据，多条件查询
     *
     * @param drugCategory
     * @return
     * @throws JsonProcessingException
     */
    @RequestMapping("/selectDrugSales")
    public ResponseResult<JSONObject> selectDrugSales
    (String drugName, String storageTime, String pageNoStr, String pageSizeStr, HttpSession session)
            throws JsonProcessingException {
        //获取参数
        long pageNo = 1;    //如果没有传数据,默认为第一页
        if (pageNoStr != null && pageNoStr.trim().length() > 0) {
            pageNo = Long.parseLong(pageNoStr);
        }
        int pageSize = 1;    //如果没有传数据,默认为10条数据
        if (pageSizeStr != null && pageSizeStr.trim().length() > 0) {
            pageSize = Integer.parseInt(pageSizeStr);
        }
        long beginNo = (pageNo - 1) * pageSize;

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("beginNo", beginNo);
        map.put("pageSize", pageSize);
        map.put("storageTime", storageTime);
        map.put("drugName", drugName);
        map.put("customerId", getUidFromSession(session));
        PaginationVO<DrugSales> vo = drugSalesService.getSelectDrugSales(map);
        //解决前后端json遍历的时间问题(把vo换成jsonObj)
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
        JSONObject jsonObj = JSONObject.fromObject(vo, jsonConfig);
        return new ResponseResult<JSONObject>(SUCCESS, jsonObj);
    }

    /**
     * 查询数据，多条件查询，销售账单
     *
     * @param drugCategory
     * @return
     * @throws JsonProcessingException
     */
    @RequestMapping("/getselectDrugSales")
    public ResponseResult<JSONObject> getselectDrugSales
    (String drugName, String storageTime, String username, String pageNoStr, String pageSizeStr)
            throws JsonProcessingException {
        //获取参数
        long pageNo = 1;    //如果没有传数据,默认为第一页
        if (pageNoStr != null && pageNoStr.trim().length() > 0) {
            pageNo = Long.parseLong(pageNoStr);
        }
        int pageSize = 1;    //如果没有传数据,默认为10条数据
        if (pageSizeStr != null && pageSizeStr.trim().length() > 0) {
            pageSize = Integer.parseInt(pageSizeStr);
        }
        long beginNo = (pageNo - 1) * pageSize;

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("beginNo", beginNo);
        map.put("pageSize", pageSize);
        map.put("storageTime", storageTime);
        map.put("drugName", drugName);
        map.put("username", username);
        PaginationVO<DrugSalesANDCustomer> vo = drugSalesService.getfindDrugSales(map);
        //解决前后端json遍历的时间问题(把vo换成jsonObj)
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
        JSONObject jsonObj = JSONObject.fromObject(vo, jsonConfig);
        return new ResponseResult<JSONObject>(SUCCESS, jsonObj);
    }

    /**
     * 查询供货商的数量
     */
    @RequestMapping("/selectIdCount")
    public ResponseResult<Long> selectIdCount() {
        Long count = drugSalesService.getselectIdCount();
        return new ResponseResult<Long>(SUCCESS, count);
    }


}
