package com.zhwang.drug.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.zhwang.drug.entity.StockReturn;
import com.zhwang.drug.service.IStockReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhwang.drug.util.JsonDateValueProcessor;
import com.zhwang.drug.util.ResponseResult;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

/**
 * stockReturn(退货)控制器
 */
@RestController
@RequestMapping("/stockreturn")
public class StockReturnController extends BaseController {

    @Autowired
    IStockReturnService stockReturnService;

    /**
     * 查询所有退货信息
     *
     * @param pageNoStr   分页查询
     * @param pageSizeStr 分页查询
     * @param documentNo  查询条件
     * @return 返回查询结果(经过处理 : 处理里面的时间让用户看的更明白)
     */
    @GetMapping("/findstockreturn")
    public ResponseResult<JSONArray> findStockReturn(Integer pageNoStr, Integer pageSizeStr, String documentNo) {
        List<StockReturn> allStockReturn = stockReturnService.findAll((pageNoStr - 1) * pageSizeStr, pageSizeStr, documentNo);
        //解决前后端json遍历的时间问题(把vo换成jsonObj)
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
        JSONArray jsonArr = JSONArray.fromObject(allStockReturn, jsonConfig);
        return new ResponseResult<JSONArray>(SUCCESS, jsonArr);
    }

    /**
     * 添加退货信息
     *
     * @param stockReturn 退货信息
     * @param session     获取登录的用户名
     * @return 返回成功与否
     */
    @PostMapping("/addstockreturn")
    public ResponseResult<Void> addStockReturn(StockReturn stockReturn, HttpSession session) {
        System.err.println(stockReturn);
        stockReturnService.addStockReturn(stockReturn, session.getAttribute("username").toString());
        return new ResponseResult<>(SUCCESS);
    }
}