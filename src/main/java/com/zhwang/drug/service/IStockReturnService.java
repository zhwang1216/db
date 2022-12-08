package com.zhwang.drug.service;

import java.util.List;

import com.zhwang.drug.entity.StockReturn;
import com.zhwang.drug.service.exception.InsertException;

/**
 * 退货业务层接口
 * @author mmt
 *
 */
public interface IStockReturnService {
	
	/**
	 * 添加退货信息
	 * @throws InsertException 添加数据异常
	 */
	void addStockReturn(StockReturn stockreturn,String username);
	
	/**
	 * 根据给定条件查询数据
	 * @param pageNoStr 分页跳过的条数
	 * @param pageSizeStr 分页每页条数
	 * @param documentNo 查询条件
	 * @return 查询到的数据
	 */
	List<StockReturn> findAll(Integer pageNoStr,Integer pageSizeStr,String documentNo);
}