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
public class DetailOrder {
    private int id;
    private int orderId;
    private String seri;
    private String pin;
    private Date createdAt;
    private int createdBy;
    private Date updateAt;
    private int updateBy;
    private Date deleteAt;
    private int deleteBy;
    private boolean isDelete;

    public DetailOrder(int id, int orderId, String seri, String pin, Date createdAt, int createdBy, Date updateAt, int updateBy, Date deleteAt, int deleteBy, boolean isDelete) {
        this.id = id;
        this.orderId = orderId;
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

    public DetailOrder(int id, int orderId, String seri, String pin, Date createdAt) {
        this.id = id;
        this.orderId = orderId;
        this.seri = seri;
        this.pin = pin;
        this.createdAt = createdAt;
    }
    
    

    public DetailOrder() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getSeri() {
        return seri;
    }

    public void setSeri(String sert) {
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
        return "DetailOrder{" + "id=" + id + ", orderId=" + orderId + ", seri=" + seri + ", pin=" + pin + ", createdAt=" + createdAt + ", createdBy=" + createdBy + ", updateAt=" + updateAt + ", updateBy=" + updateBy + ", deleteAt=" + deleteAt + ", deleteBy=" + deleteBy + ", isDelete=" + isDelete + '}';
    }
    
}
