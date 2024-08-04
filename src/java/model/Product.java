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
public class Product {
    private int id;
    private String name;
    private int price;
    private int quanlity;
    private String img;
    private int cateID;
    private Date createdAt;
    private int createdBy;
    private Date updateAt;
    private int updateBy;
    private Date deleteAt;
    private int deleteBy;
    private boolean isDelete;
    
   

    public Product() {
    }

    public Product(int id, String name, int price, int quanlity, String img, int cateID, Date createdAt, int createdBy, Date updateAt, int updateBy, Date deleteAt, int deleteBy, boolean isDelete) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quanlity = quanlity;
        this.img = img;
        this.cateID = cateID;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.updateAt = updateAt;
        this.updateBy = updateBy;
        this.deleteAt = deleteAt;
        this.deleteBy = deleteBy;
        this.isDelete = isDelete;
    }

    public Product(int id, String name, int price, int quanlity, String img, int cateID) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quanlity = quanlity;
        this.img = img;
        this.cateID = cateID;
    }

    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuanlity() {
        return quanlity;
    }

    public void setQuanlity(int quanlity) {
        this.quanlity = quanlity;
    }

    public int getCateID() {
        return cateID;
    }

    public void setCateID(int cateID) {
        this.cateID = cateID;
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
        return "Product{" + "id=" + id + ", name=" + name + ", img=" + img + ", price=" + price + ", quanlity=" + quanlity + ", cateID=" + cateID 
                + ", createdAt=" + createdAt + ", createdBy=" + createdBy + ", updateAt=" + updateAt + ", updateBy=" + updateBy + ", deleteAt=" + deleteAt + ", deleteBy=" + deleteBy + ", isDelete=" + isDelete + '}';
    }

    
    
    

    
    

   
}
