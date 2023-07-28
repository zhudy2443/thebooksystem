package com.example.demo1.service;

import com.example.demo1.pojo.Cart;
import com.example.demo1.pojo.Order;
import com.example.demo1.pojo.OrderItem;

import java.util.List;


public interface OrderService {
    String createOrder(Cart cart, Integer userId);

    List<Order> queryOrders();

    Order queryOrderByOrderId(String orderId);

    void sendOrder(String orderId);

    void receiveOrder(String orderId);

    List<Order> queryMyOrders(int userId);

    List<OrderItem> showOrderDetail(String orderId);
}
