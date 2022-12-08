package com.zhwang.drug.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ExceptionHandler;

import com.zhwang.drug.service.exception.BarCodeDuplicateException;
import com.zhwang.drug.service.exception.CategoryNameDuplicateException;
import com.zhwang.drug.service.exception.DeleteException;
import com.zhwang.drug.service.exception.EmailDuplicateException;
import com.zhwang.drug.service.exception.ForeignKeyReferenceException;
import com.zhwang.drug.service.exception.InsertException;
import com.zhwang.drug.service.exception.InventoryException;
import com.zhwang.drug.service.exception.InventoryFoundException;
import com.zhwang.drug.service.exception.OnExistException;
import com.zhwang.drug.service.exception.PasswordNotMatchException;
import com.zhwang.drug.service.exception.PermissionsInsufficientException;
import com.zhwang.drug.service.exception.PhoneDuplicateException;
import com.zhwang.drug.service.exception.PhoneNotFoundException;
import com.zhwang.drug.service.exception.ServiceException;
import com.zhwang.drug.service.exception.SupplierNotFoundException;
import com.zhwang.drug.service.exception.UpdateException;
import com.zhwang.drug.service.exception.UserNotFoundException;
import com.zhwang.drug.service.exception.YearMonthException;
import com.zhwang.drug.util.ResponseResult;

/**
 * 控制器类的基类
 */
public abstract class BaseController {
    /**
     * 相应结果状态，成功
     */
    public static final Integer SUCCESS = 200;
    /**
     * 文件上传路径
     */
    public static final String UPLOAD_DIR = "upload";
    /**
     * 确定允许上传的文件的大小为一兆
     */
    public static final long UPLOAD_MAX_SIZE = 1 * 1024 * 1024;
    /**
     * 确定允许上传的文件类型列表
     */
    public static final List<String> UPLOAD_CONTENT_TYPE = new ArrayList<String>();

    static {
        UPLOAD_CONTENT_TYPE.add("image/png");
        UPLOAD_CONTENT_TYPE.add("image/jpeg");
        UPLOAD_CONTENT_TYPE.add("image/gif");
        UPLOAD_CONTENT_TYPE.add("image/bmp");
    }


    /**
     * 通过session获取uid
     *
     * @param session
     * @return
     */
    protected final Integer getUidFromSession(HttpSession session) {
        return Integer.valueOf(session.getAttribute("uid").toString());
    }

    /**
     * 处理异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ServiceException.class)
    public ResponseResult<Void> handleException(Throwable e) {
        ResponseResult<Void> rr = new ResponseResult<Void>();
        rr.setMessage(e.getMessage());
        if (e instanceof PhoneDuplicateException) {
            rr.setState(400);    //400-电话冲突
        } else if (e instanceof EmailDuplicateException) {
            rr.setState(401);    //401-邮箱冲突
        } else if (e instanceof OnExistException) {
            rr.setState(402);    //402-入库单号冲突
        } else if (e instanceof CategoryNameDuplicateException) {
            rr.setState(403);    //403-药品类别名称重复异常
        } else if (e instanceof UserNotFoundException) {
            rr.setState(404);    //404-用户不存在异常
        } else if (e instanceof BarCodeDuplicateException) {
            rr.setState(405);    //405-药品条形码重复异常
        } else if (e instanceof PhoneNotFoundException) {
            rr.setState(406);    //手机号不存在异常
        } else if (e instanceof PasswordNotMatchException) {
            rr.setState(407);    //密码不存在异常
        } else if (e instanceof YearMonthException) {
            rr.setState(406);    //客户时间异常
        } else if (e instanceof SupplierNotFoundException) {
            rr.setState(407);    //供货商不存在异常
        } else if (e instanceof PermissionsInsufficientException) {
            rr.setState(406);    //员工权限不足异常
        } else if (e instanceof InventoryFoundException) {
            rr.setState(407);    //库存不足异常
        } else if (e instanceof InventoryException) {
            rr.setState(406);    //修改库存异常
        } else if (e instanceof ForeignKeyReferenceException) {
            rr.setState(407);    //外键引用异常
        } else if (e instanceof InsertException) {
            rr.setState(500);    //500插入数据异常
        } else if (e instanceof UpdateException) {
            rr.setState(501);    //501修改数据异常
        } else if (e instanceof DeleteException) {
            rr.setState(502);    //501删除改数据异常
        }


        return rr;
    }
}
