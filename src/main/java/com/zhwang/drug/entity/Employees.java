package com.zhwang.drug.entity;

/**
 * 员工实体类
 */
public class Employees extends BaseEntity {
    private Integer uid;//用户id
    private String username;//用户名
    private String password;//密码
    private String salt;//盐值
    private Integer gender;//性别,0-女性，1-男性
    private Integer age;//年龄
    private String phone;//电话
    private String email;//邮箱
    private String avatar;//头像
    private String cardBank;//开户行
    private String card;//银行账户
    private Integer isDelete;//是否删除，0-未删除，1-已删除
    private Integer permissions;//权限，0-老板，1-员工

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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

    public Integer getPermissions() {
        return permissions;
    }

    public void setPermissions(Integer permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return "Employees [uid=" + uid + ", username=" + username + ", password=" + password + ", salt=" + salt
                + ", gender=" + gender + ", age=" + age + ", phone=" + phone + ", email=" + email + ", avatar=" + avatar
                + ", cardBank=" + cardBank + ", card=" + card + ", isDelete=" + isDelete + ", permissions="
                + permissions + "]";
    }

}