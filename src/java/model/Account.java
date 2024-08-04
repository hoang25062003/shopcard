package model;

import java.util.Date;

public class Account {

    private int id;
    private String userName;
    private String password;
    private String name;
    private String email;
    private String phone;  
    private int roleId;
    private String img;
    private int money;
    private int status;
    private String otp;
    private Date otpGender;
    private Date createdAt;
    private Integer createdBy;
    private Date updateAt;
    private Integer updateBy;
    private Date deleteAt;
    private Integer deleteBy;
    private boolean isDelete;

    public Account(){
    }
    
    public Account(int id) {
        this.id = id;
    }

    public Account(int id, String name, String email, String phone) {
        this.id = id;
       
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public Account(int id, String userName, String password, String name, String email, String phone, int roleId, String img, int money, int status, String otp, Date otpGender, Date createdAt, Integer createdBy, Date updateAt, Integer updateBy, Date deleteAt, Integer deleteBy, boolean isDelete) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.roleId = roleId;
        this.img = img;
        this.money = money;
        this.status = status;
        this.otp = otp;
        this.otpGender = otpGender;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.updateAt = updateAt;
        this.updateBy = updateBy;
        this.deleteAt = deleteAt;
        this.deleteBy = deleteBy;
        this.isDelete = isDelete;
    }

    public Account(String userName, String password, String name, String email, String phone, int roleId, String img, int money, int status, String otp, Date otpGender, Date createdAt, Integer createdBy, Date updateAt, Integer updateBy, Date deleteAt, Integer deleteBy, boolean isDelete) {
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.roleId = roleId;
        this.img = img;
        this.money = money;
        this.status = status;
        this.otp = otp;
        this.otpGender = otpGender;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.updateAt = updateAt;
        this.updateBy = updateBy;
        this.deleteAt = deleteAt;
        this.deleteBy = deleteBy;
        this.isDelete = isDelete;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public Date getOtpGender() {
        return otpGender;
    }

    public void setOtpGender(Date otpGender) {
        this.otpGender = otpGender;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public Date getDeleteAt() {
        return deleteAt;
    }

    public void setDeleteAt(Date deleteAt) {
        this.deleteAt = deleteAt;
    }

    public Integer getDeleteBy() {
        return deleteBy;
    }

    public void setDeleteBy(Integer deleteBy) {
        this.deleteBy = deleteBy;
    }

    public boolean isIsDelete() {
        return isDelete;
    }

    public void setIsDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }
  

@Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", roleId=" + roleId +
                ", img='" + img + '\'' +
                ", money=" + money +
                ", status=" + status +
                ", otp='" + otp + '\'' +
                ", otpGender=" + otpGender +
                ", createdAt=" + createdAt +
                ", createdBy=" + createdBy +
                ", updateAt=" + updateAt +
                ", updateBy=" + updateBy +
                ", deleteAt=" + deleteAt +
                ", deleteBy=" + deleteBy +
                ", isDelete=" + isDelete +
                '}';
    }

}
