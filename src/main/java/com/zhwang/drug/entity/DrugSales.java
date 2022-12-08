package com.zhwang.drug.entity;

import java.util.Date;

/**
 * 销售实体类
 */
public class DrugSales extends BaseEntity {

    private Integer id;//销售id
    private String documentNo;//销售单据号
    private Integer inventoryQuantity;//销售数量
    private Double price;//入库单价
    private Double inventory;//销售金额
    private Date storageTime;//销售时间
    private String drugName;//药品编号
    private Integer customerId;//客户id
    private Integer isDelete;//是否删除，0-未删除，1-已删除

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDocumentNo() {
        return documentNo;
    }

    public void setDocumentNo(String documentNo) {
        this.documentNo = documentNo;
    }

    public Integer getInventoryQuantity() {
        return inventoryQuantity;
    }

    public void setInventoryQuantity(Integer inventoryQuantity) {
        this.inventoryQuantity = inventoryQuantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getInventory() {
        return inventory;
    }

    public void setInventory(Double inventory) {
        this.inventory = inventory;
    }

    public Date getStorageTime() {
        return storageTime;
    }

    public void setStorageTime(Date storageTime) {
        this.storageTime = storageTime;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        return "DrugSales [id=" + id + ", documentNo=" + documentNo + ", inventoryQuantity=" + inventoryQuantity
                + ", price=" + price + ", inventory=" + inventory + ", storageTime=" + storageTime + ", drugName="
                + drugName + ", customerId=" + customerId + ", isDelete=" + isDelete + "]";
    }


}