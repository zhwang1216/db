package com.zhwang.drug.dao;

import java.util.List;

import com.zhwang.drug.entity.StockReturn;

/**
 * 退货持久层接口
 */
public interface StockReturnDao {

    /**
     * 添加退货信息
     *
     * @param stockReturn
     * @return 首影响的行数
     */
    Integer insertStockReturn(StockReturn stockReturn);

    /**
     * 查询所有的退货信息
     *
     * @param pageNoStr   分页
     * @param pageSizeStr 分页
     * @param documentNo  条件
     * @return 查询到的数据
     */
    List<StockReturn> findStockReturn(Integer pageNoStr, Integer pageSizeStr, String documentNo);
}