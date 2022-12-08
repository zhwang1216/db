package com.zhwang.drug.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.zhwang.drug.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.zhwang.drug.controller.exception.FileContentTypeException;
import com.zhwang.drug.controller.exception.FileEmptyException;
import com.zhwang.drug.controller.exception.FileIOException;
import com.zhwang.drug.controller.exception.FileIllegalStateException;
import com.zhwang.drug.controller.exception.FileSizeException;
import com.zhwang.drug.entity.Customer;
import com.zhwang.drug.entity.CustomerTime;
import com.zhwang.drug.entity.domain.PaginationVO;
import com.zhwang.drug.util.ResponseResult;

@RestController
@RequestMapping("/customer")
public class CustomerController extends BaseController {
    @Autowired    //自动装配
    private ICustomerService customerService;

    /**
     * 注册用户
     *
     * @param user
     * @return 返回成功
     */
    @RequestMapping("/reg")
    public ResponseResult<Void> reg(Customer customer) {
        customerService.reg(customer);
        return new ResponseResult<Void>(SUCCESS);
    }

    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/login")
    public ResponseResult<Customer> login(String phone, String password, HttpSession session) {
        Customer customer = customerService.getloginCustomer(phone, password);
        session.setAttribute("user", customer);
        session.setAttribute("uid", customer.getUid());
        session.setAttribute("username", customer.getUsername());
        return new ResponseResult<Customer>(SUCCESS, customer);
    }

    /**
     * 查询客户数据，多条件查询
     *
     * @param drugCategory
     * @return
     * @throws JsonProcessingException
     */
    @RequestMapping("/selectCustomer")
    public ResponseResult<PaginationVO<Customer>> selectCustomer
    (String username, String gender, String address, String pageNoStr, String pageSizeStr) throws JsonProcessingException {
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
        map.put("username", username);
        map.put("gender", gender);
        map.put("address", address);
        PaginationVO<Customer> vo = customerService.getSelectCustomer(map);
        return new ResponseResult<PaginationVO<Customer>>(SUCCESS, vo);
    }

    /**
     * 删除客户数据
     *
     * @param uid
     * @param session
     * @return
     */
    @RequestMapping("/deleteCustomer")
    public ResponseResult<Void> deleteCustomer(Integer uid, HttpSession session) {
        String username = (String) session.getAttribute("username");
        customerService.getdeleteId(uid, username);
        return new ResponseResult<Void>(SUCCESS);
    }

    /**
     * 修改客户数据
     */
    @RequestMapping("/updateCustomer")
    public ResponseResult<Void> updateCustomer(Customer customer, HttpSession session) {
        String username = (String) session.getAttribute("username");
        customerService.getupdateCustomer(customer, username);
        return new ResponseResult<Void>(SUCCESS);
    }

    /**
     * 展示个人信息
     */
    @RequestMapping("/getfindByUid")
    public ResponseResult<Customer> getfindByUid(Integer uid) {
        Customer customer = customerService.getfindByUid(uid);
        return new ResponseResult<Customer>(SUCCESS, customer);
    }

    /**
     * 修改密码
     */
    @RequestMapping("/getfindByUidPassword")
    public ResponseResult<Void> getfindByUidPassword(Integer uid, HttpSession session, String oldPassword, String newPassword) {
        String username = (String) session.getAttribute("username");
        customerService.getfindByUidPassword(uid, username, oldPassword, newPassword);
        return new ResponseResult<Void>(SUCCESS);
    }

    /**
     * 上传头像
     *
     * @param request
     * @param file
     * @return MultipartFile file
     */
    @RequestMapping("/change_avatar")
    public ResponseResult<String> changeAvatar(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            throw new FileEmptyException("上传头像错误!上传文件不能为空!");
        }
        if (!UPLOAD_CONTENT_TYPE.contains(file.getContentType())) {
            throw new FileContentTypeException("上传头像错误!不支持所选的文件类型!");
        }
        if (file.getSize() > UPLOAD_MAX_SIZE) {
            throw new FileSizeException("上传文件过大!请选择小于" + UPLOAD_MAX_SIZE + "的文件!");
        }
        String parentPath = request.getServletContext().getRealPath(UPLOAD_DIR);
        File parent = new File(parentPath);
        if (!parent.exists()) {
            parent.mkdirs();
        }
        String originalFilename = file.getOriginalFilename();
        //使用系统纳秒值给头像命名
        //String prefic = System.nanoTime()+"";
        //使用uid+username为头像文件命名,新头像会将旧头像替换
        HttpSession session = request.getSession();
        String prefic = session.getAttribute("uid").toString() + session.getAttribute("username").toString();
        int beginIndex = originalFilename.lastIndexOf(".");
        String suffix = "";
        if (beginIndex > 0) {
            suffix = originalFilename.substring(beginIndex);
        }
        String filename = prefic + suffix;
        File dest = new File(parent, filename);
        try {
            file.transferTo(dest);
        } catch (IllegalStateException e) {
            e.printStackTrace();
            throw new FileIllegalStateException("上传头像错误!存储头像文件时状态异常!");
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileIOException("上传头像错误!读写文件时出现错误!");
        }
        Integer uid = getUidFromSession(session);
        String avatar = "/" + UPLOAD_DIR + "/" + filename;

        customerService.changeAvatar(avatar, uid);
        return new ResponseResult<String>(SUCCESS, avatar);
    }

    /**
     * 查询客户的数量
     */
    @RequestMapping("/selectIdCount")
    public ResponseResult<Long> selectIdCount() {
        Long count = customerService.getselectIdCount();
        return new ResponseResult<Long>(SUCCESS, count);
    }

    /**
     * 图表展示，客户流量，输入年份，展示该年每一个月的客户注册量
     *
     * @param createdTime
     * @return
     */
    @RequestMapping("/selectYearTime")
    public ResponseResult<List<CustomerTime>> selectYearTime(String createdTime) {
        String str = createdTime.substring(0, createdTime.indexOf("-"));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("createdTime", str);
        List<CustomerTime> customerTimeList = customerService.getselectYearMonth(map);
        return new ResponseResult<List<CustomerTime>>(SUCCESS, customerTimeList);
    }


}
