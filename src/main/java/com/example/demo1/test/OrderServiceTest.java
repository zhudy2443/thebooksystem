package com.example.demo1.test;

import com.example.demo1.pojo.Cart;
import com.example.demo1.pojo.CartItem;
import com.example.demo1.pojo.Order;
import com.example.demo1.service.OrderService;
import com.example.demo1.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

public class OrderServiceTest {
    OrderService orderService = new OrderServiceImpl();
    @Test
    public void createOrder() {

        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "java从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1, "java从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2, "数据结构与算法", 1, new BigDecimal(100),new BigDecimal(100)));
        OrderService orderService = new OrderServiceImpl();
        System.out.println( "订单号是：" + orderService.createOrder(cart, 1));
    }
    @Test
    public void queryOrders(){
        System.out.println(orderService.queryOrders());
    }
    @Test
    public void queryMyOrders(){
        System.out.println(orderService.queryMyOrders(1));
    }
    @Test
    public void queryOrderByOrderId(){
        Order order = orderService.queryOrderByOrderId("123456");
        System.out.println(order);
    }

    @Test
    public void queryAllOrderItems(){
        System.out.println(orderService.showOrderDetail("123456"));
    }
}
