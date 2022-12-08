package com.zhwang.drug.entity;

/**
 * 供货商实体类
 */
public class Supplier extends BaseEntity {
    private Integer uid;//供货商id
    private String username;//厂名
    private String phone;//电话
    private String email;//邮箱
    private String address;//地址
    private String cardBank;//开户行
    private String card;//银行账户
    private Integer isDelete;//是否删除，0-未删除，1-已删除

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCardBank() {
        return cardBank;
    }

    public void setCardBank(String cardBank) {
        this.cardBank = cardBank;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        return "Supplier [uid=" + uid + ", username=" + username + ", phone=" + phone + ", email=" + email
                + ", address=" + address + ", cardBank=" + cardBank + ", card=" + card + ", isDelete=" + isDelete + "]";
    }

}
