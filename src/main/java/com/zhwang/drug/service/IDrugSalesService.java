package com.zhwang.drug.service;

import java.util.Map;

import com.zhwang.drug.entity.DrugSales;
import com.zhwang.drug.entity.DrugSalesANDCustomer;
import com.zhwang.drug.entity.domain.PaginationVO;
import com.zhwang.drug.service.exception.InsertException;
import com.zhwang.drug.service.exception.InventoryException;
import com.zhwang.drug.service.exception.InventoryFoundException;

/**
 * 进货业务层接口
 * @author mmt
 *
 */
public interface IDrugSalesService {
	
	/**
	 * 添加药品购买信息
	 * @param operation 日志
	 */
	void addDrugSales(String[] ids, DrugSales drugSales, String username)
			throws InsertException,InventoryFoundException,InventoryException;
	
	/**
	 * 根据条件查询所有数据
	 */
	PaginationVO<DrugSales> getSelectDrugSales(Map<String,Object> map);
	
	/**
	 * 根据条件查询所有数据,账单查询
	 */
	PaginationVO<DrugSalesANDCustomer> getfindDrugSales(Map<String,Object> map);
	
	/**
	 * 查询销售数量
	 * @return
	 */
	Long getselectIdCount();
	
}