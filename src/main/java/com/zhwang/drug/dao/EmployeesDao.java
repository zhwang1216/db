package com.zhwang.drug.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhwang.drug.entity.Employees;

/**
 * 员工持久层接口
 */
public interface EmployeesDao {
    /**
     * 查询员工表uid，username
     *
     * @return
     */
    List<Employees> findByUidUsername();

    /**
     * 插入emp员工信息
     *
     * @param emp
     * @return 返回受影响的行数
     */
    Integer insert(Employees emp);

    /**
     * 根据phone查询
     *
     * @param emp
     * @return 返回查询到的员工uid, 如果不存在则返回null.
     */
    Employees findByPhone(String phone);

    /**
     * 查询员工数据信息
     *
     * @return
     */
    List<Employees> selectEmployees();

    /**
     * 根据员工uid修改信息
     *
     * @return
     */
    Integer uploadInfo(Employees emp);

    /**
     * 根据uid查询员工
     *
     * @param
     * @return
     */
    Employees findByUid(Integer uid);

    /**
     * 根据uid修改头像
     *
     * @param uid
     * @return
     */
    Integer uploadAvata(String avatar, Integer uid);


    /**
     * 根据uid删除员工
     *
     * @param uid
     * @return
     */
    Integer deleteEmployees(Integer uid);

    /**
     * 根据uid修改新密码
     *
     * @param password
     * @param uid
     * @return
     */
    Integer updatePassword(@Param("password") String password, @Param("uid") Integer uid, @Param("username") String username, @Param("date") Date date);

    /**
     * 根据uid查询密码和颜值
     *
     * @param uid
     * @return
     */
    Employees findPassWordByUid(Integer uid);

    /**
     * 将id_delete改为1
     *
     * @param uid
     * @return
     */
    Integer deleteEmp(Integer uid);

    /**
     * 根据username模糊查询
     *
     * @param username
     * @return
     */
    List<Employees> findByUsername(Employees emp);


}
