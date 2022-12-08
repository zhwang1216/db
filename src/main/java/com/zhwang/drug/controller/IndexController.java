package com.zhwang.drug.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zhwang.drug.util.ResponseResult;

@RestController
@RequestMapping("/index")
public class IndexController extends BaseController {

    /**
     * 查询日志数据，多条件查询
     *
     * @param drugCategory
     * @return
     * @throws JsonProcessingException
     */
    @RequestMapping("/exit")
    public ResponseResult<Void> selectDrugCategory(HttpSession session) {
        //销毁session
        session.invalidate();
        return new ResponseResult<Void>(SUCCESS);
    }
}
