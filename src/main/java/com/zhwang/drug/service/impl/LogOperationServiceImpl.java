package com.zhwang.drug.service.impl;

import java.util.List;
import java.util.Map;

import com.zhwang.drug.entity.LogOperation;
import com.zhwang.drug.entity.domain.PaginationVO;
import com.zhwang.drug.service.ILogOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zhwang.drug.dao.LogOperationDao;

/**
 * 日志信息业务层实现类
 *
 */
@Service
public class LogOperationServiceImpl implements ILogOperationService {
	
	@Autowired private LogOperationDao LogOperationDao;

	/**
	 * 根据条件查询药品类别所有数据
	 */
	public PaginationVO<LogOperation> getSelectLogOperation(Map<String,Object> map){
		//查询日志信息
		List<LogOperation> list = selectLogOperationForPage(map);
		//查询数量
		long count = selectLogOperationCount(map);
		PaginationVO<LogOperation> VO = new PaginationVO<LogOperation>();
		//把List和long封装成VO
		VO.setCount(count);
		VO.setDataList(list);
		return VO;
	}
	
	
	/*************************************************************************************************/
	
	
	
	/**
	 * 根据条件，查询日志信息
	 */
	private List<LogOperation> selectLogOperationForPage(Map<String,Object> map){
		return LogOperationDao.selectLogOperationForPage(map);
	};
	/**
	 * 根据条件，查询日志信息数量
	 */
	private Long selectLogOperationCount(Map<String,Object> map){
		return LogOperationDao.selectLogOperationCount(map);
	};
	
}











