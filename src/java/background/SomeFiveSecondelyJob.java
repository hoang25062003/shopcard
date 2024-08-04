/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package background;

import DAO.AccountDao;
import DAO.OrderDao;
import DAO.OrderDetailDao;
import DAO.ProductDao;
import DAO.ProductDetailDao;
import java.util.List;
import model.Account;
import model.Order;
import model.ProductDetail;

/**
 *
 * @author Admin
 */
public class SomeFiveSecondelyJob implements Runnable {

   @Override
    public void run() {
        
       while (!OrderQueue.queue.isEmpty()) {
            int id =OrderQueue.queue.poll();
            ProductDao pDao = new ProductDao();
            OrderDetailDao oDDao = new OrderDetailDao();
            ProductDetailDao dDao = new ProductDetailDao();
            OrderDao oDao = new OrderDao();
            AccountDao aDao = new AccountDao();
            Order o = oDao.getOrderById(id);
            
            Account a = aDao.getAccountById(o.getAccountId());
            if(a.getMoney() < o.getTotal()){
                continue;
            }
            aDao.updateMoney(a, a.getMoney() - o.getTotal()); 
            List<ProductDetail> list = dDao.getProductDetail(o.getProductId(), o.getQuanlity());
            for (ProductDetail productDetail : list) {
                            dDao.delProductDetailById(productDetail.getId());
                            oDDao.addOrderDetail(o.getId(), productDetail.getSeri(), productDetail.getPin());
                        }
            pDao.updateQuantity(o.getProductId(), pDao.getProductById(o.getProductId()).getQuanlity()-o.getQuanlity());
            oDao.UpdateStatus(o.getId(), 1);
            
        }
    }
}
