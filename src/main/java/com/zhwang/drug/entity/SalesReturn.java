package com.zhwang.drug.entity;

import java.util.Date;

public class SalesReturn extends BaseEntity {
    private Integer id; //'id',
    private String customerName;// 客户名称
    private String drugName; // 药品名称
    private String salesOrder; // 销售单号
    private String returnOrder; // 退货单号
    private String drugAddress; // 药品产地
    private Date returnTime; // 退货时间
    private String employeesName; // 员工名称
    private Double drugPrice; // 购买药品单价
    private Integer number; // 退货数量
    private Double returnPrice; // 退货时金额
    private Double amount; // 退货总金额
    private String why; // 退货原因

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getSalesOrder() {
        return salesOrder;
    }

    public void setSalesOrder(String salesOrder) {
        this.salesOrder = salesOrder;
    }

    public String getReturnOrder() {
        return returnOrder;
    }

    public void setReturnOrder(String returnOrder) {
        this.returnOrder = returnOrder;
    }

    public String getDrugAddress() {
        return drugAddress;
    }

    public void setDrugAddress(String drugAddress) {
        this.drugAddress = drugAddress;
    }

    public Date getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(Date returnTime) {
        this.returnTime = returnTime;
    }

    public String getEmployeesName() {
        return employeesName;
    }

    public void setEmployeesName(String employeesName) {
        this.employeesName = employeesName;
    }

    public Double getDrugPrice() {
        return drugPrice;
    }

    public void setDrugPrice(Double drugPrice) {
        this.drugPrice = drugPrice;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Double getReturnPrice() {
        return returnPrice;
    }

    public void setReturnPrice(Double returnPrice) {
        this.returnPrice = returnPrice;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getWhy() {
        return why;
    }

    public void setWhy(String why) {
        this.why = why;
    }

    @Override
    public String toString() {
        return "SalesReturn [id=" + id + ", customerName=" + customerName + ", drugName=" + drugName + ", salesOrder="
                + salesOrder + ", returnOrder=" + returnOrder + ", drugAddress=" + drugAddress + ", returnTime="
                + returnTime + ", employeesName=" + employeesName + ", drugPrice=" + drugPrice + ", number=" + number
                + ", returnPrice=" + returnPrice + ", amount=" + amount + ", why=" + why + "]";
    }


}
