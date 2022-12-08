package com.zhwang.drug.service.impl;

import java.util.Date;
import java.util.List;

import com.zhwang.drug.entity.StockReturn;
import com.zhwang.drug.service.IStockReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhwang.drug.dao.StockReturnDao;
import com.zhwang.drug.service.exception.InsertException;

/**
 * 退货业务层实现类
 * @author mmt
 *
 */
@Service
public class StockReturnServiceImpl implements IStockReturnService {

	@Autowired
	StockReturnDao stockReturn;
	
	/**
	 * 添加退货信息
	 */
	@Override
	public void addStockReturn(StockReturn stockreturn, String username) throws InsertException {
		//创建人和创建时间由后台添加
		stockreturn.setCreatedUser(username);
		stockreturn.setCreatedTime(new Date());
		insertStockReturn(stockreturn);
	}

	/**
	 * 条件查询所有的退货信息
	 */
	@Override
	public List<StockReturn> findAll(Integer pageNoStr,Integer pageSizeStr,String documentNo) {
		List<StockReturn> allStockReturn = findStockReturn(pageNoStr,pageSizeStr,documentNo);
		return allStockReturn;
	}
	
	
	
	/**
	 * 添加退货信息
	 * @param stockReturn 需要添加的退货信息
	 */
	private void insertStockReturn(StockReturn stockreturn){
		Integer upd = stockReturn.insertStockReturn(stockreturn);
		if(upd!=1){
			throw new InsertException("添加数据出现未知错误!!!");
		}
	}
	
	/**
	 * 查询所有的退货信息
	 * @param pageNoStr 分页
	 * @param pageSizeStr 分页
	 * @param documentNo 条件
	 * @return 查询到的数据
	 */
	private List<StockReturn> findStockReturn(Integer pageNoStr,Integer pageSizeStr,String documentNo){
		List<StockReturn> all = stockReturn.findStockReturn(pageNoStr,pageSizeStr,documentNo);
		return all;
	}

}