package com.zhwang.drug.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zhwang.drug.entity.Customer;
import com.zhwang.drug.entity.CustomerTime;

/**
 * 客户dao层接口
 */
public interface CustomerDao {
    /**
     * 查询客户表的uid和客户名称
     *
     * @return
     */
    List<Customer> findByUidUsername();

    /**
     * 根据电话返回用户部分信息,主要用于保证插入数据时，电话的唯一性
     *
     * @param phone
     * @return
     */
    Customer findByPhone(String phone);

    /**
     * 根据邮箱返回用户部分信息,主要用于保证插入数据时，邮箱的唯一性
     *
     * @param email
     * @return
     */
    Customer findByEmail(String email);

    /**
     * 客户简单注册（用户名、密码、盐值、手机号、邮箱，性别）
     *
     * @param customer
     * @return
     */
    Integer insertCustomer(Customer customer);

    /**
     * 根据手机号进行登录判断
     *
     * @param phone
     * @return
     */
    Customer loginCustomer(String phone);

    /**
     * 根据条件查询客户所有数据
     *
     * @return
     */
    List<Customer> selectCustomerForPage(Map<String, Object> map);

    /**
     * 根据条件查询客户总条数
     *
     * @param map
     * @return
     */
    Long selectCustomerCount(Map<String, Object> map);

    /**
     * 根据uid删除客户信息
     *
     * @param uid
     * @return
     */
    Integer deleteCustomer(@Param("uid") Integer uid, @Param("isDelete") Integer isDelete,
                           @Param("modifiedUser") String modifiedUser, @Param("modifiedTime") Date modifiedTime);

    /**
     * 判断该条数据是否被引用
     *
     * @param uid
     * @return
     */
    Integer deleteCustomerId(Integer uid);

    /**
     * 修改客户数据
     *
     * @param customer
     * @return
     */
    Integer updateCustomer(Customer customer);

    /**
     * 根据uid出现所有个人数据
     *
     * @param uid
     * @return
     */
    Customer findByUid(Integer uid);

    /**
     * 根据uid查询密码，和颜值
     *
     * @param uid
     * @return
     */
    Customer findByuidPassword(Integer uid);

    /**
     * 根据uid修改密码
     *
     * @param uid
     * @param password
     * @return
     */
    Integer updateByUidPassword(@Param("uid") Integer uid, @Param("password") String password,
                                @Param("modifiedUser") String modifiedUser, @Param("modifiedTime") Date modifiedTime);

    /**
     * 根据uid修改头像
     *
     * @param uid
     * @return
     */
    Integer uploadAvata(@Param("avatar") String avatar, @Param("uid") Integer uid);

    /**
     * 查询客户数量
     *
     * @return
     */
    Long selectIdCount();

    /**
     * 根据年份，查询每年的12月注册数据
     *
     * @param year
     * @return
     */
    List<CustomerTime> selectYearMonth(Map<String, Object> map);
}
