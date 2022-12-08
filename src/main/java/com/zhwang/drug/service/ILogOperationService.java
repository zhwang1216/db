package com.zhwang.drug.service;

import java.util.Map;

import com.zhwang.drug.entity.LogOperation;
import com.zhwang.drug.entity.domain.PaginationVO;

public interface ILogOperationService {
	
	/**
	 * 根据条件查询药品类别所有数据
	 */
	PaginationVO<LogOperation> getSelectLogOperation(Map<String,Object> map);
}
