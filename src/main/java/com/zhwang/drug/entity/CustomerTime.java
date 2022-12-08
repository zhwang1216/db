package com.zhwang.drug.entity;

/**
 * 统计客户根据年份，展示每年的12个月注册数据
 *
 */
public class CustomerTime {
    private Integer month;
    private Integer count;

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "CustomerTime [month=" + month + ", count=" + count + "]";
    }

}
