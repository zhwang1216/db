package com.zhwang.drug.service;

import java.util.List;

import com.zhwang.drug.entity.Employees;
import com.zhwang.drug.service.exception.PasswordNotMatchException;
import com.zhwang.drug.service.exception.PhoneDuplicateException;
import com.zhwang.drug.service.exception.PhoneNotFoundException;
import com.zhwang.drug.service.exception.UpdateException;
import com.zhwang.drug.service.exception.UserNotFoundException;

public interface IEmployeesService {
	/**
	 * 添加员工
	 * @param emp 员工
	 */
	public void addEmp(Employees emp,String username)throws PhoneDuplicateException ;
	
	/**
	 * 员工登录
	 * @param phone
	 * @param password
	 * @return
	 * @throws PhoneNotFoundException
	 * @throws PasswordNotMatchException
	 */
	public Employees loginEmp(String phone,String password) throws PhoneNotFoundException ,PasswordNotMatchException;
	
	/**
	 * 根据uid删除员工
	 * @param uid
	 */
	void deleteEmployeesByUid(Integer uid);
	
	/**
	 * 员工假删除
	 * @param uid
	 */
	void getOutEmp(Integer uid);
	
	/**
	 * 根据uid修改头像
	 * @param uid
	 */
	void changeAvatar(String avatar,Integer uid);
	
	/**
	 * 修改员工信息
	 * @param emp
	 */
	void changeEmpInfo(Employees emp);
	
	/**
	 * 查询员工数据信息
	 * @return
	 */
	List<Employees> getSelectEmployees();
	
	/**
	 * 查询员工信息
	 * @param uid
	 * @return
	 */
	Employees findEmpInfo(Integer uid);
	
	/**
	 * 修改密码
	 * @param password
	 * @param uid
	 */
	void changePassword(String oldPassword,String newPassword,Integer uid) throws UserNotFoundException,PasswordNotMatchException,UpdateException;
	
	List<Employees> getByUsername(String username);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
