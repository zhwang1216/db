package com.zhwang.drug.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.zhwang.drug.entity.Supplier;
import org.apache.ibatis.annotations.Param;

/**
 * 供货商持久层接口
 */
public interface SupplierDao {
    /**
     * 查询供货商表的uid,username
     *
     * @return
     */
    List<Supplier> findByUidUsername();

    /**
     * 插入供应商数据
     *
     * @param supplier 供应商
     * @return受影响行数
     */
    Integer insert(Supplier supplier);

    /**
     * 根据电话查询供应商
     *
     * @param phone 供应商电话
     * @return 供应商
     */
    Supplier findByPhone(String phone);

    /**
     * 根据邮箱查询供应商
     *
     * @param email 供应商邮箱
     * @return 供应商
     */
    Supplier findByEmail(String Email);

    /**
     * 根据供应商id查询供应商
     *
     * @param uid 供应商id
     * @return 供应商
     */
    Supplier findByUid(Integer uid);

    /**
     * 查询所有供应商信息
     *
     * @return 所有供应商
     */
    List<Supplier> findAll();

    /**
     * 根据供应商id判断，该条数据是否被引用
     *
     * @param categoryId
     * @return
     */
    Integer deleteSupplierId(Integer uid);

    /**
     * 修改供应商信息
     *
     * @param supplier
     * @return
     */
    Integer updateInfo(Supplier supplier);

    /**
     * 根据uid删除供应商
     *
     * @param uid          供应商id
     * @param modifiedUser 修改人
     * @param modifiedTime 修改时间
     * @return
     */
    Integer updateIsDelete(@Param("uid") Integer uid,
                           @Param("modifiedTime") String modifiedUser, @Param("modifiedTime") Date modifiedTime);

    /**
     * 根据条件查询供应商所有数据
     *
     * @return
     */
    List<Supplier> selectSupplierForPage(Map<String, Object> map);

    /**
     * 根据条件查询供应商总条数
     *
     * @param map
     * @return
     */
    Long selectCountSupplier(Map<String, Object> map);

    /**
     * 查询供货商数量
     *
     * @return
     */
    Long selectIdCount();
}










