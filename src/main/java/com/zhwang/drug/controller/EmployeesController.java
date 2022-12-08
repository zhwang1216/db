package com.zhwang.drug.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.zhwang.drug.service.IEmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.zhwang.drug.controller.exception.FileContentTypeException;
import com.zhwang.drug.controller.exception.FileEmptyException;
import com.zhwang.drug.controller.exception.FileIOException;
import com.zhwang.drug.controller.exception.FileIllegalStateException;
import com.zhwang.drug.controller.exception.FileSizeException;
import com.zhwang.drug.entity.Employees;
import com.zhwang.drug.util.ResponseResult;

@RestController
@RequestMapping("/employees")
public class EmployeesController extends BaseController {
    @Autowired
    private IEmployeesService empService;

    /**
     * 添加员工信息
     *
     * @param emp
     * @param session
     * @return
     */
    @RequestMapping("/addEmp")
    public ResponseResult<Void> addEmp(Employees emp, HttpSession session) {
        String username = session.getAttribute("username").toString();
        empService.addEmp(emp, username);
        return new ResponseResult<Void>(SUCCESS);
    }

    /**
     * 员工手机号登录
     *
     * @param phone
     * @param password
     * @param permissions
     * @return
     */
    @RequestMapping("/login")
    public ResponseResult<Employees> loginEmp(String phone, String password, HttpSession session) {
        Employees emp = empService.loginEmp(phone, password);
        session.setAttribute("uid", emp.getUid());
        session.setAttribute("user", emp);
        session.setAttribute("username", emp.getUsername());
        return new ResponseResult<Employees>(SUCCESS, emp);
    }

    @RequestMapping("/changePassword")
    public ResponseResult<Void> changePassword(Integer uid, String oldPassword, String newPassword, HttpSession session) {
        empService.changePassword(oldPassword, newPassword, uid);
        return new ResponseResult<Void>(SUCCESS);
    }

    /**
     * 查询员工数据，后期改为多条件查询
     *
     * @param drugCategory
     * @return
     */
    @RequestMapping("/selectEmployees")
    public ResponseResult<List<Employees>> selectEmp() {
        List<Employees> list = empService.getSelectEmployees();
        return new ResponseResult<List<Employees>>(SUCCESS, list);
    }

    /**
     * 根据uid查询emp信息
     *
     * @param session
     * @return
     */
    @RequestMapping("/show_EmpInfo")
    public ResponseResult<Employees> showEmpInfo(Integer uid) {
        Employees emp = empService.findEmpInfo(uid);
        return new ResponseResult<Employees>(SUCCESS, emp);
    }


    //************************修改头像***********************************

    /**
     * 上传员工头像
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
        empService.changeAvatar(avatar, uid);
        return new ResponseResult<String>(SUCCESS, avatar);
    }

    @RequestMapping("/change_EmpInfo")
    public ResponseResult<Void> changeEmpInfo(Employees emp) {
        empService.changeEmpInfo(emp);
        return new ResponseResult<Void>(SUCCESS);
    }

    @RequestMapping("/delete_Employees")
    public ResponseResult<Void> deleteEmployess(Integer uid) {
        empService.deleteEmployeesByUid(uid);
        return new ResponseResult<Void>(SUCCESS);
    }

    @RequestMapping("/getOutEmp")
    public ResponseResult<Void> getOutEmp(Integer uid) {
        empService.getOutEmp(uid);
        return new ResponseResult<Void>(SUCCESS);
    }

    @RequestMapping("/getByUsername")
    public ResponseResult<List<Employees>> getByUsername(String username) {
        List<Employees> data = empService.getByUsername(username);
        return new ResponseResult<List<Employees>>(SUCCESS, data);
    }


}
