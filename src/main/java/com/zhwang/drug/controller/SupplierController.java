package com.zhwang.drug.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.zhwang.drug.entity.Supplier;
import com.zhwang.drug.service.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.zhwang.drug.entity.domain.PaginationVO;
import com.zhwang.drug.util.ResponseResult;

@RestController
@RequestMapping("/supplier")
public class SupplierController extends BaseController {

    @Autowired
    private ISupplierService supplierService;

    @RequestMapping("/addnew")
    public ResponseResult<Void> reg(Supplier supplier) {

        supplierService.addnew(supplier);

        return new ResponseResult<Void>(SUCCESS);

    }

    @RequestMapping("/change_info")
    public ResponseResult<Void> changeInfo(Supplier supplier, Integer uid) {
        supplier.setUid(uid);
        supplierService.changeInfo(supplier);

        return new ResponseResult<Void>(SUCCESS);
    }

    @GetMapping("/info")
    public ResponseResult<Supplier> getByUid(Integer uid) {
        Supplier data = supplierService.getByUid(uid);
        return new ResponseResult<Supplier>(SUCCESS, data);
    }

    @RequestMapping("/select_all")
    public ResponseResult<List<Supplier>> selectAll() {
        List<Supplier> data = supplierService.selectAll();
        return new ResponseResult<List<Supplier>>(SUCCESS, data);
    }

    @RequestMapping("/change_isDelete")
    public ResponseResult<Void> changeIsDelete(String uids, HttpSession session) {
        String[] uid = uids.split(",");
        String username = session.getAttribute("username").toString();
        supplierService.changeIsDelet(uid, username);
        return new ResponseResult<Void>(SUCCESS);
    }

    /**
     * 查询供应商数据，多条件查询
     *
     * @param Supplier
     * @return
     * @throws JsonProcessingException
     */
    @RequestMapping("/selectSupplier")
    public ResponseResult<PaginationVO<Supplier>> selectSupplier(String username, String pageNoStr, String pageSizeStr, String phone, String email) throws JsonProcessingException {
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
        map.put("username", username);
        map.put("phone", phone);
        map.put("email", email);
        map.put("pageSize", pageSize);
        PaginationVO<Supplier> vo = supplierService.getSelectSupplier(map);
        return new ResponseResult<PaginationVO<Supplier>>(SUCCESS, vo);
    }

    /**
     * 查询供货商的数量
     */
    @RequestMapping("/selectIdCount")
    public ResponseResult<Long> selectIdCount() {
        Long count = supplierService.getselectIdCount();
        return new ResponseResult<Long>(SUCCESS, count);
    }


}






