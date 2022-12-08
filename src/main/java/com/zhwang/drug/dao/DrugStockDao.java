package com.zhwang.drug.dao;

import java.util.Date;
import java.util.List;

import com.zhwang.drug.entity.DrugStock;
import com.zhwang.drug.entity.DrugStockFindAll;
import org.apache.ibatis.annotations.Param;

/**
 * 进货,持久层接口
 */
public interface DrugStockDao {

    /**
     * 修改进货数据
     *
     * @return 受影响的行数
     */
    Integer updateDrugStock(DrugStock drugstock);

    /**
     * 删除某条数据(设置isDelete为1)
     *
     * @param id           删除的id
     * @param modifiedUser 修改人
     * @param modifiedTime 修改时间
     * @return 受影响的行数
     */
    Integer updateIsDelete(@Param("id") Integer id, @Param("modifiedUser") String modifiedUser, @Param("modifiedTime") Date modifiedTime);

    /**
     * 添加进货清单
     *
     * @param drugstock
     * @return
     */
    Integer insertDrugStock(DrugStock drugstock);

    /**
     * 根据入库单号查询是否有重复数据
     *
     * @param documentNo 单号
     * @return 返回受影响的条数
     */
    Integer findBydocumentNo(String documentNo);

    /**
     * 查询所有进货数据
     *
     * @return
     */
    List<DrugStockFindAll> findAll(Integer pageNoStr, Integer pageSizeStr, String documentNo);
}
