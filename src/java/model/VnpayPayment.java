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
public class VnpayPayment {
    private String txnref;
    private int accountId;
    private int amount;
    private String orderInfor;
    private Date payDate; 

    @Override
    public String toString() {
        return "VnpayPayment{" + "txnref=" + txnref + ", accountId=" + accountId + ", amount=" + amount + ", orderInfor=" + orderInfor + ", payDate=" + payDate + '}';
    }

    public VnpayPayment(String txnref, int accountId, int amount, String orderInfor, Date payDate) {
        this.txnref = txnref;
        this.accountId = accountId;
        this.amount = amount;
        this.orderInfor = orderInfor;
        this.payDate = payDate;
    }

    

    public VnpayPayment() {
    }

    

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getTxnref() {
        return txnref;
    }

    public void setTxnref(String txnref) {
        this.txnref = txnref;
    }

    public String getOrderInfor() {
        return orderInfor;
    }

    public void setOrderInfor(String orderInfor) {
        this.orderInfor = orderInfor;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    
    
}
