package com.zhwang.drug.dao;

import java.util.List;
import java.util.Map;

import com.zhwang.drug.entity.LogOperation;

/**
 * 日志
 */
public interface LogOperationDao {

    /**
     * 根据条件，查询日志信息
     */
    List<LogOperation> selectLogOperationForPage(Map<String, Object> map);

    /**
     * 根据条件，查询日志信息数量
     */
    Long selectLogOperationCount(Map<String, Object> map);


}
