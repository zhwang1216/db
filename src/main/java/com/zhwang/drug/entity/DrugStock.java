package com.zhwang.drug.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 进货实体类
 */
public class DrugStock extends BaseEntity {
    private Integer id;//进货id
    private String documentNo;//入库单据号
    private Integer inventoryQuantity;//入库数量
    private Double price;//入库单价
    private Double inventory;//入库金额
    private Date storageTime;//入库时间
    private Integer drugId;//药品编号
    private Integer supplierId;//供货商id
    private Integer employeesId;//员工id---经办人
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

    public void setStorageTime(String storageTime) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        this.storageTime = sdf.parse(storageTime);
    }

    public Integer getDrugId() {
        return drugId;
    }

    public void setDrugId(Integer drugId) {
        this.drugId = drugId;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public Integer getEmployeesId() {
        return employeesId;
    }

    public void setEmployeesId(Integer employeesId) {
        this.employeesId = employeesId;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        return "DrugPurchase [id=" + id + ", documentNo=" + documentNo + ", inventoryQuantity=" + inventoryQuantity
                + ", price=" + price + ", inventory=" + inventory + ", storageTime=" + sdf.format(storageTime) + ", drugId="
                + drugId + ", supplierId=" + supplierId + ", employeesId=" + employeesId + ", isDelete=" + isDelete
                + "]";
    }

}