package com.zhwang.drug.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.zhwang.drug.entity.Drug;
import com.zhwang.drug.entity.DrugCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.zhwang.drug.entity.DrugANDDrugCategory;
import com.zhwang.drug.entity.domain.PaginationVO;
import com.zhwang.drug.service.IDrugCategoryService;
import com.zhwang.drug.service.IDrugService;
import com.zhwang.drug.util.ResponseResult;

@RestController
@RequestMapping("/drug")
public class DrugController extends BaseController {
    @Autowired    //自动装配
    private IDrugService drugService;
    @Autowired //自动装配
    private IDrugCategoryService drugCategoryService;

    /**
     * 添加数据。药品类别信息
     *
     * @param user
     * @return 返回成功
     */
    @RequestMapping("/addDrug")
    public ResponseResult<Void> addDrug(Drug drug, HttpSession session) {
        String username = (String) session.getAttribute("username");
        drugService.addDrug(drug, username);
        return new ResponseResult<Void>(SUCCESS);
    }

    /**
     * 为添加药品时，药品类别选择所设计
     *
     * @return
     */
    @RequestMapping("/selectDrugCategory")
    public ResponseResult<List<DrugCategory>> selectDrugCategory() {
        List<DrugCategory> list = drugCategoryService.getfindByCategoryIdCategoryName();
        return new ResponseResult<List<DrugCategory>>(SUCCESS, list);
    }

    /**
     * 查询药品数据(关联查询)药品类别表，后期改为多条件查询
     *
     * @param drugCategory
     * @return
     * @throws JsonProcessingException
     */
    @RequestMapping("/selectDrug")
    public ResponseResult<PaginationVO<DrugANDDrugCategory>> selectDrug(String drugName, String unit, String origin, Integer categoryId, String pageNoStr, String pageSizeStr) throws JsonProcessingException {
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
        map.put("drugName", drugName);
        map.put("unit", unit);
        map.put("origin", origin);
        map.put("beginNo", beginNo);
        map.put("categoryId", categoryId);
        map.put("pageSize", pageSize);
        PaginationVO<DrugANDDrugCategory> vo = drugService.getselectDrug(map);
        return new ResponseResult<PaginationVO<DrugANDDrugCategory>>(SUCCESS, vo);
    }

    /**
     * 根据uid查询药品全部数据
     *
     * @param uid
     * @return
     */
    @RequestMapping("/findId")
    public ResponseResult<Drug> getfindId(Integer id) {
        Drug data = drugService.getfindId(id);
        return new ResponseResult<Drug>(SUCCESS, data);
    }

    ;

    /**
     * 修改药品数据
     *
     * @param drug
     * @param session
     * @return
     */
    @RequestMapping("/updateIdDrug")
    public ResponseResult<Void> updateIdDrug(Drug drug, HttpSession session) {
        String username = (String) session.getAttribute("username");
        drugService.getupdateIdDrug(drug, username);
        return new ResponseResult<Void>(SUCCESS);
    }

    /**
     * 根据id删除药品数据
     *
     * @param id
     * @param session
     * @return
     */
    @RequestMapping("/deleteIdDrug")
    public ResponseResult<Void> deleteIdDrug(String id, HttpSession session) {
        String[] ids = id.split(",");
        String username = (String) session.getAttribute("username");
        drugService.getdeleteIdDrug(ids, username);
        return new ResponseResult<Void>(SUCCESS);
    }


    /**
     * 查询药品的数量
     */
    @RequestMapping("/selectIdCount")
    public ResponseResult<Long> selectIdCount() {
        Long count = drugService.getselectIdCount();
        return new ResponseResult<Long>(SUCCESS, count);
    }


}
