package com.example.demo1.dao.Impl;

import com.example.demo1.dao.BaseDao;
import com.example.demo1.dao.OrderItemDao;
import com.example.demo1.pojo.OrderItem;

import java.util.List;

public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item(`name`,`count`,`price`,`total_price`,`order_id`) values(?,?,?,?,?)";
        return update(sql,orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalPrice(), orderItem.getOrderId());
    }

    @Override
    public List<OrderItem> queryOrderItemsByOrderId(String orderId) {
        String sql = "select name,count,price,total_price as totalPrice from t_order_item where order_id=?";
        return queryForList(OrderItem.class,sql,orderId);
    }
}
