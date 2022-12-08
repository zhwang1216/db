package com.zhwang.drug.dao;

import java.util.List;
import java.util.Map;

import com.zhwang.drug.entity.DrugSales;
import com.zhwang.drug.entity.DrugSalesANDCustomer;

/**
 * 销售的持久层接口
 */
public interface DrugSalesDao {
    /**
     * 添加药品购买信息
     *
     * @param drugSalse
     * @return
     */
    Integer insertDrugSales(DrugSales drugSales);

    /**
     * 根据客户id查询以购买的药品信息
     *
     * @param customerId
     * @return
     */
    List<DrugSales> selectDrugSalesPage(Map<String, Object> map);

    /**
     * 根据条件，查询信息数量
     */
    Long selectDrugSalesCount(Map<String, Object> map);

    /**
     * 条件查询以购买的药品信息，账单查询
     *
     * @param customerId
     * @return
     */
    List<DrugSalesANDCustomer> getselectDrugSalesPage(Map<String, Object> map);

    /**
     * 根据条件，查询信息数量
     */
    Long getselectDrugSalesCount(Map<String, Object> map);

    /**
     * 查询销售数量
     *
     * @return
     */
    Long selectIdCount();
}
