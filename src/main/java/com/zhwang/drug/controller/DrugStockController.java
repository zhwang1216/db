package com.zhwang.drug.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;

import com.zhwang.drug.entity.DrugStock;
import com.zhwang.drug.entity.DrugStockFindAll;
import com.zhwang.drug.service.IDrugStockService;
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
 * drugStock控制器
 */
@RestController
@RequestMapping("/drugstock")
public class DrugStockController extends BaseController {

    @Autowired
    private IDrugStockService drugStockService;

    /**
     * 修改数据
     *
     * @return 返回成功与否
     */
    @PostMapping("/update")
    public ResponseResult<Void> update(DrugStock drugStock) {
        drugStockService.modificationDrugStock(drugStock);
        return new ResponseResult<Void>(SUCCESS);
    }

    /**
     * 插入进货数据
     *
     * @param drugStock 需要插入的数据
     * @return 返回执行信息错误与否
     */
    @PostMapping("/insert")
    public ResponseResult<Void> add(DrugStock drugStock, HttpSession session) {
        drugStockService.addDrugStock(drugStock, session);
        return new ResponseResult<Void>(SUCCESS);
    }

    /**
     * 查询员工与客户中所有已存在的id
     *
     * @return 返回list集合封装的id
     */
    @GetMapping("/findid")
    public ResponseResult<List<List<Map<Integer, String>>>> findEmpSupId() {
        List<List<Map<Integer, String>>> id = drugStockService.findDrgEmpSupId();
        return new ResponseResult<List<List<Map<Integer, String>>>>(SUCCESS, id);
    }

    /**
     * 查询所有的drugStock
     *
     * @return 返回结果
     */
    @GetMapping("/findall")
    public ResponseResult<JSONArray> findAllDrugStock(Integer pageNoStr, Integer pageSizeStr, String documentNo) {
        List<DrugStockFindAll> drugStocks = drugStockService.findDrugStock((pageNoStr - 1) * pageSizeStr, pageSizeStr, documentNo);
        //解决前后端json遍历的时间问题(把vo换成jsonObj)
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
        JSONArray jsonArr = JSONArray.fromObject(drugStocks, jsonConfig);
        return new ResponseResult<JSONArray>(SUCCESS, jsonArr);
    }

    /**
     * 删除数据
     *
     * @param Id      需要删除的id
     * @param session 获取绑定数据
     * @return 返回结果
     */
    @PostMapping("/deletebyid")
    public ResponseResult<Void> deleteDrugStock(String Id, HttpSession session) {
        drugStockService.deleteDrugStock(Id, session);
        return new ResponseResult<Void>(SUCCESS);
    }

}