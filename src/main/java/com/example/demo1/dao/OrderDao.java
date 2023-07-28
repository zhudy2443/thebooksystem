package com.example.demo1.dao;

import com.example.demo1.pojo.Order;

import java.util.List;

public interface OrderDao {
    public int saveOrder(Order order);

    public List<Order> queryOrders();

    Order queryOrderByOrderId(String orderId);

    void sendOrder(String orderId);

    void receiveOrder(String orderId);

    List<Order> queryMyOrders(int userId);
}

