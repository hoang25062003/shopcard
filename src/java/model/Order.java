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
public class Order {
    private int id;
    private int accountId;
    private int productId;
    private int quanlity;
    private int total;
    private boolean status;
    private Date createdAt;
    private int createdBy;
    private Date updateAt;
    private int updateBy;
    private Date deleteAt;
    private int deleteBy;
    private boolean isDelete;

    public Order(int id, int accountId, int productId, int quanlity, int total, boolean status) {
        this.id = id;
        this.accountId = accountId;
        this.productId = productId;
        this.quanlity = quanlity;
        this.total = total;
        this.status = status;
    }

    public Order(int id, int accountId, int productId, int quanlity, int total, Date createdAt) {
        this.id = id;
        this.accountId = accountId;
        this.productId = productId;
        this.quanlity = quanlity;
        this.total = total;
        this.createdAt = createdAt;
    }

    public Order(int id, int accountId, int productId, int quanlity, int total, Date createdAt, boolean status) {
        this.id = id;
        this.accountId = accountId;
        this.productId = productId;
        this.quanlity = quanlity;
        this.total = total;
        this.status = status;
        this.createdAt = createdAt;
    }

    
   

    public Order(int id, int accountId, int productId, int quanlity, int total, boolean status, Date createdAt, int createdBy, Date updateAt, int updateBy, Date deleteAt, int deleteBy, boolean isDelete) {
        this.id = id;
        this.accountId = accountId;
        this.productId = productId;
        this.quanlity = quanlity;
        this.total = total;
        this.status = status;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.updateAt = updateAt;
        this.updateBy = updateBy;
        this.deleteAt = deleteAt;
        this.deleteBy = deleteBy;
        this.isDelete = isDelete;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuanlity() {
        return quanlity;
    }

    public void setQuanlity(int quanlity) {
        this.quanlity = quanlity;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
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
        return "Order{" + "id=" + id + ", accountId=" + accountId + ", productId=" + productId + ", quanlity=" + quanlity + ", total=" + total + ", createdAt=" + createdAt + ", createdBy=" + createdBy + ", updateAt=" + updateAt + ", updateBy=" + updateBy + ", deleteAt=" + deleteAt + ", deleteBy=" + deleteBy + ", isDelete=" + isDelete + '}';
    }
    
}
