package com.zhwang.drug.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.zhwang.drug.service.IEmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.zhwang.drug.dao.EmployeesDao;
import com.zhwang.drug.entity.Employees;
import com.zhwang.drug.service.exception.PhoneNotFoundException;
import com.zhwang.drug.service.exception.UpdateException;
import com.zhwang.drug.service.exception.UserNotFoundException;
import com.zhwang.drug.service.exception.DeleteException;
import com.zhwang.drug.service.exception.InsertException;
import com.zhwang.drug.service.exception.PasswordNotMatchException;
import com.zhwang.drug.service.exception.PhoneDuplicateException;
/**
 * 员工，业务层，实现类
 * @author PHP
 *
 */
@Service
public class EmployeesServiceImpl implements IEmployeesService {

	@Autowired
	EmployeesDao dao;
	/**
	 * 添加员工
	 */
	@Override
	public void addEmp(Employees emp,String username) throws PhoneDuplicateException{
		Employees result = findByPhone(emp.getPhone());
		if(result == null) {
			emp.setIsDelete(0);
			Date date = new Date();
			emp.setCreatedUser(username);
			emp.setCreatedTime(date);
			emp.setModifiedUser(username);
			emp.setModifiedTime(date);
			String salt = UUID.randomUUID().toString();
			emp.setSalt(salt);
			String md5Password = getMd5Password(emp.getPassword(),salt);
			emp.setPassword(md5Password);
			insert(emp);
		}else{
			throw new PhoneDuplicateException("手机号已经被注册,请直接登录!");
		}
	}
	
	/**
	 * 员工登录
	 */
	@Override
	public Employees loginEmp(String phone, String password)throws PhoneNotFoundException ,PasswordNotMatchException{
		
		Employees emp = findByPhone(phone);
		if(emp == null) {
			throw new PhoneNotFoundException("登录失败,手机号不存在!");
		}
		if(emp.getIsDelete().equals(1)) {
			throw new PhoneNotFoundException("登录失败,手机号不存在!");
		}
		String md5Password = getMd5Password(password, emp.getSalt());
		if(md5Password.equals(emp.getPassword().toString())) {
			emp.setPassword(null);
			emp.setSalt(null);
			emp.setIsDelete(null);
			return emp;
		}
		throw new PasswordNotMatchException("登录失败,密码输入有误!");
	}
	
	/**
	 * 查询员工数据信息
	 * @return
	 */
	public List<Employees> getSelectEmployees(){
		List<Employees> list = selectEmployees();
		return list;
	};
	/**
	 * 员工修改信息
	 * @param uid
	 * @param avatar
	 */
	public void changeEmpInfo(Employees emp) {
		emp.setModifiedUser(emp.getUsername());
		emp.setModifiedTime(new Date());
		uploadInfo(emp);
	}
	
	/**
	 * 根据员工uid查询员工信息
	 */
	public Employees findEmpInfo(Integer uid) {
		Employees emp = findByUid(uid);
		return emp;
	}
	
	/**
	 * 根据uid修改员工头像
	 */
	@Override
	public void changeAvatar(String avatar,Integer uid) {
		uploadAvatar(avatar,uid);
	}

	/**
	 * 根据员工uid删除员工
	 */
	@Override
	public void deleteEmployeesByUid(Integer uid) {
		deleteEmployees(uid);
	}
	
	/**
	 * 修改密码
	 * @param password
	 * @param uid
	 */
	public void changePassword(String oldPassword,String newPassword,Integer uid) {
		Employees emp = findPassWordByUid(uid);
		if(emp == null) {
			throw new UserNotFoundException("您尝试修改密码的用户未找到!");
		}
		if(emp.getIsDelete().equals("1")) {
			throw new UserNotFoundException("您尝试修改密码的用户未找到!");
		}
		String salt = emp.getSalt();
		if(!getMd5Password(oldPassword, salt).equals(emp.getPassword())) {
			throw new PasswordNotMatchException("原密码输入有误,请重新输入!");
		}
		String md5Password = getMd5Password(newPassword, salt);
		String username = findByUid(uid).getUsername();
		Date date = new Date();
		updatePassword(md5Password,uid,username,date);
	}
	
	/**
	 * 员工假删除
	 */
	@Override
	public void getOutEmp(Integer uid) {
		deleteEmp(uid);
	}
	
	@Override
	public List<Employees> getByUsername(String username) {
		List<Employees> result = findByUsername(username);
		return result;
	}
	
	//*****************以下为私有方法*********************************
	
	private List<Employees> findByUsername(String username){
		Employees emp = new Employees();
		emp.setUsername(username);
		List<Employees> result = dao.findByUsername(emp);
		return result;
	}
	
	/**
	 * 假删除
	 * @param uid
	 */
	private void deleteEmp(Integer uid) {
		Integer row = dao.deleteEmp(uid);
		if(row != 1) {
			throw new UserNotFoundException("用户已不存在!");
		}
	}
	
	private Employees findPassWordByUid(Integer uid) {
		Employees result = dao.findPassWordByUid(uid);
		return result;
	}
	
	private void updatePassword(String password,Integer uid,String username,Date date) {
		Integer row = dao.updatePassword(password,uid,username,date);
		if(row != 1) {
			throw new UpdateException("修改密码时出现未知异常!");
		}
	}
	/**
	 * 根据员工uid删除员工
	 * @param uid
	 */
	private void deleteEmployees(Integer uid) {
		Integer row = dao.deleteEmployees(uid);
		if(row != 1) {
			throw new DeleteException("删除员工数据时出现未知错误!");
		}
	}
	
	/**
	 * 根据员工uid修改员工头像
	 * @param avatar
	 * @param uid
	 */
	private void uploadAvatar(String avatar,Integer uid) {
		Integer row = dao.uploadAvata(avatar,uid);
		if(row != 1) {
			throw new UpdateException("修改头像时出现未知错误!");
		}
	}
	
	/**
	 * 插入头像数据
	 * @param uid
	 * @param avatar
	 * @param modifiedUser
	 * @param modifiedTime
	 */
	private void uploadInfo(Employees emp) {
		Integer row = dao.uploadInfo(emp);
		if(row != 1) {
			throw new UpdateException("修改信息时出现未知错误!");
		}
	}
	/**
	 * 根据uid查询用户信息
	 * @param uid
	 * @return
	 */
	private Employees findByUid(Integer uid) {
		Employees emp = dao.findByUid(uid);
		return emp;
	}
	
	/**
	 * 查询员工数据信息
	 * @return
	 */
	private List<Employees> selectEmployees(){
		return dao.selectEmployees();
	};
	
	/**
	 * 插入员工信息
	 * @param emp
	 */
	private void insert(Employees emp) {
		Integer row = dao.insert(emp);
		if(row != 1) {
			throw new InsertException("插入用户数据时出现未知错误!");
		}
	}
	/**
	 * 根据手机号查询用户信息
	 * @param phone
	 * @return
	 */
	private Employees findByPhone(String phone) {
		Employees result = dao.findByPhone(phone);
		return result;
	}
	/**
	 * 获取执行MD5加密后的密码
	 * @param password 原密码
	 * @param slat 盐值
	 * @return 加密后的密码
	 */
	private String getMd5Password(String password,String salt) {
		String result = salt+password+salt;
		for (int i = 0; i < 5; i++) {
			result = DigestUtils.md5DigestAsHex(result.getBytes()).toUpperCase();
		}
		return result;
	}

	

	

	

		
}
