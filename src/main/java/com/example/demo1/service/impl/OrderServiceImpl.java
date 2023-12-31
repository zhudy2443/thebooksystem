package com.example.demo1.service.impl;

import com.example.demo1.dao.BookDao;
import com.example.demo1.dao.Impl.BookDaoImpl;
import com.example.demo1.dao.Impl.OrderDaoImpl;
import com.example.demo1.dao.Impl.OrderItemDaoImpl;
import com.example.demo1.dao.OrderDao;
import com.example.demo1.dao.OrderItemDao;
import com.example.demo1.pojo.*;
import com.example.demo1.service.OrderService;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private BookDao bookDao = new BookDaoImpl();

    @Override
    public String createOrder(Cart cart, Integer userId) {
        // 订单号===唯一性
        String orderId = System.currentTimeMillis()+""+userId;
        // 创建一个订单对象
        Order order = new Order(orderId,new Date(),cart.getTotalPrice(), 0,userId);
        // 保存订单
        orderDao.saveOrder(order);

        // 遍历购物车中每一个商品项转换成为订单项保存到数据库
        for (Map.Entry<Integer, CartItem>entry : cart.getItems().entrySet()){
            // 获取每一个购物车中的商品项
            CartItem cartItem = entry.getValue();
            // 转换为每一个订单项
            OrderItem orderItem = new OrderItem(null,cartItem.getName(),cartItem.getCount(),cartItem.getPrice(),cartItem.getTotalPrice(), orderId);
            // 保存订单项到数据库
            orderItemDao.saveOrderItem(orderItem);

            // 更新库存和销量
            Book book = bookDao.queryBookById(cartItem.getId());
            book.setSales( book.getSales() + cartItem.getCount() );
            book.setStock( book.getStock() - cartItem.getCount() );
            bookDao.updateBook(book);
        }
        // 清空购物车
        cart.clear();

        return orderId;
    }

    @Override
    public List<Order> queryOrders() {
        return orderDao.queryOrders();
    }

    @Override
    public Order queryOrderByOrderId(String orderId) {
        return orderDao.queryOrderByOrderId(orderId);
    }

    @Override
    public void sendOrder(String orderId) {
        orderDao.sendOrder(orderId);
    }

    @Override
    public void receiveOrder(String orderId) {
        orderDao.receiveOrder(orderId);
    }

    @Override
    public List<Order> queryMyOrders(int userId) {
        return orderDao.queryMyOrders(userId);
    }

    @Override
    public List<OrderItem> showOrderDetail(String orderId) {
        return orderItemDao.queryOrderItemsByOrderId(orderId);
    }
}

