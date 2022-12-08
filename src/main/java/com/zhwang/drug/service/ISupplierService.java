package com.zhwang.drug.service;

import java.util.List;
import java.util.Map;

import com.zhwang.drug.entity.Supplier;
import com.zhwang.drug.entity.domain.PaginationVO;
import com.zhwang.drug.service.exception.DeleteException;
import com.zhwang.drug.service.exception.EmailDuplicateException;
import com.zhwang.drug.service.exception.ForeignKeyReferenceException;
import com.zhwang.drug.service.exception.InsertException;
import com.zhwang.drug.service.exception.PhoneDuplicateException;
import com.zhwang.drug.service.exception.SupplierNotFoundException;
import com.zhwang.drug.service.exception.UpdateException;

public interface ISupplierService {
	/**
	 * 添加供应商
	 * @param supplier 共用时信息
	 * @throws InsertException
	 */
	void addnew(Supplier supplier) throws InsertException,PhoneDuplicateException,EmailDuplicateException;
	
	/**
	 * 修改供应商信息
	 */
	void changeInfo(Supplier supplier) throws SupplierNotFoundException,UpdateException;
	/**
	 * 查询供应商信息
	 * @param uid 供应商id
	 * @return 供应商
	 */
	Supplier getByUid(Integer uid);
	
	/**
	 * 查询所有供应商信息
	 * @return 供应商信息
	 */
	List<Supplier> selectAll();
	
	void changeIsDelet(String[] uid,String username)
			throws ForeignKeyReferenceException,DeleteException;
	
	/**
	 * 根据条件查询供应商所有数据
	 */
	PaginationVO<Supplier> getSelectSupplier(Map<String,Object> map);
	
	/**
	 * 查询供货商数量
	 * @return
	 */
	Long getselectIdCount();
	
	
	
	
	
	
	
	
	
}












