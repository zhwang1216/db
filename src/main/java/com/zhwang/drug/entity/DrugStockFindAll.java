package com.zhwang.drug.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 药品表,员工表,供货商表,进货表(主表)联查
 * 为了前端查询进货查询页面
 */
public class DrugStockFindAll {
    private Integer drugId;//进货表id
    private String documentNo;//入库单据号
    private String drugName;//药品名
    private String empName;//员工名
    private Double inventory;//入库金额
    private Integer inventoryQuantity;//入库数量
    private Double price;//入库单价
    private Date storageTime;//入库时间
    private String supName;//供货商名
    private Integer isDelete;//是否删除

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Double getInventory() {
        return inventory;
    }

    public void setInventory(Double inventory) {
        this.inventory = inventory;
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

    public Date getStorageTime() {
        return storageTime;
    }

    public void setStorageTime(String storageTime) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        this.storageTime = sdf.parse(storageTime);
    }

    public String getSupName() {
        return supName;
    }

    public void setSupName(String supName) {
        this.supName = supName;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getDrugId() {
        return drugId;
    }

    public void setDrugId(Integer drugId) {
        this.drugId = drugId;
    }

    public String getDocumentNo() {
        return documentNo;
    }

    public void setDocumentNo(String documentNo) {
        this.documentNo = documentNo;
    }

    @Override
    public String toString() {
        return "DrugStockFindAll [drugId=" + drugId + ", documentNo=" + documentNo + ", drugName=" + drugName
                + ", empName=" + empName + ", inventory=" + inventory + ", inventoryQuantity=" + inventoryQuantity
                + ", price=" + price + ", storageTime=" + storageTime + ", supName=" + supName + ", isDelete="
                + isDelete + "]";
    }
}