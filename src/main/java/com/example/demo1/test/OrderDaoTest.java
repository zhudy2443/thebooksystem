package com.example.demo1.test;

import com.example.demo1.dao.Impl.OrderDaoImpl;
import com.example.demo1.dao.OrderDao;
import com.example.demo1.pojo.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class OrderDaoTest {
    OrderDao orderDao = new OrderDaoImpl();
    @Test
    public void saveOrder() {
        orderDao.saveOrder(new Order("12345678912",new Date(),new BigDecimal(100),0,1));
    }
    @Test
    public void queryOrders() {
        for (Order o: orderDao.queryOrders()) {
            System.out.println(o);
        }
    }
    @Test
    public void queryMyOrders(){
        System.out.println(orderDao.queryMyOrders(1));
    }
    @Test
    public void queryOrderByOrderId(){
        Order order = orderDao.queryOrderByOrderId("123456");
        System.out.println(order);
    }
}
