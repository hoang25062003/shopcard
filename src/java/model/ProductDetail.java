/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class ProductDetail {
    private int id;
    private int nameId;
    private String seri;
    private String pin;
    private Date createdAt;
    private int createdBy;
    private Date updateAt;
    private int updateBy;
    private Date deleteAt;
    private int deleteBy;
    private boolean isDelete;

    public ProductDetail(int id, int nameId, String seri, String pin, Date createdAt, int createdBy, Date updateAt, int updateBy, Date deleteAt, int deleteBy, boolean isDelete) {
        this.id = id;
        this.nameId = nameId;
        this.seri = seri;
        this.pin = pin;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.updateAt = updateAt;
        this.updateBy = updateBy;
        this.deleteAt = deleteAt;
        this.deleteBy = deleteBy;
        this.isDelete = isDelete;
    }

    public ProductDetail(int id, int nameId, String seri, String pin) {
        this.id = id;
        this.nameId = nameId;
        this.seri = seri;
        this.pin = pin;
    }
    

    public ProductDetail() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNameId() {
        return nameId;
    }

    public void setNameId(int nameId) {
        this.nameId = nameId;
    }

    public String getSeri() {
        return seri;
    }

    public void setSeri(String seri) {
        this.seri = seri;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public int getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(int updateBy) {
        this.updateBy = updateBy;
    }

    public Date getDeleteAt() {
        return deleteAt;
    }

    public void setDeleteAt(Date deleteAt) {
        this.deleteAt = deleteAt;
    }

    public int getDeleteBy() {
        return deleteBy;
    }

    public void setDeleteBy(int deleteBy) {
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
        return "ProductDetail{" + "id=" + id + ", nameId=" + nameId + ", seri=" + seri + ", pin=" + pin + ", createdAt=" + createdAt + ", createdBy=" + createdBy + ", updateAt=" + updateAt + ", updateBy=" + updateBy + ", deleteAt=" + deleteAt + ", deleteBy=" + deleteBy + ", isDelete=" + isDelete + '}';
    }
    
}
