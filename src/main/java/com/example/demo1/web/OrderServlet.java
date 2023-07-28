package com.example.demo1.web;

import com.example.demo1.pojo.Cart;

import com.example.demo1.pojo.Order;
import com.example.demo1.pojo.OrderItem;
import com.example.demo1.pojo.User;
import com.example.demo1.service.OrderService;
import com.example.demo1.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrderServlet extends BaseServlet{

    private OrderService orderService = new OrderServiceImpl();

    //生成订单
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 先获取 Cart 购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        // 获取 Userid
        User loginUser = (User) req.getSession().getAttribute("user");

        if (loginUser == null) {
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }
        Integer userId = loginUser.getId();
        //调用 orderService.createOrder(Cart,Userid);生成订单
        String orderId = orderService.createOrder(cart, userId);

        req.getSession().setAttribute("orderId",orderId);
        resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");
    }

    //查看所有订单
    protected void showAllOrders(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
        // 获取所有订单列表
        List<Order> orderList = (List<Order>) orderService.queryOrders();

        // 将订单列表存储在request对象中，以便在JSP页面中显示
        req.setAttribute("orderList", orderList);
        orderList.forEach(System.out::println);
        // 转发到显示所有订单的JSP页面
        req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req, resp);
    }
    //发货
    protected void sendOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("orderId");
        req.getSession().setAttribute("orderId",orderId);
        Order order = orderService.queryOrderByOrderId(orderId);
        orderService.sendOrder(orderId);
        req.getRequestDispatcher("orderServlet?action=showAllOrders").forward(req,resp);
    }
    //查看订单详情
    protected void showOrderDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("orderId");
        List<OrderItem> orderItem = (List<OrderItem>) orderService.showOrderDetail(orderId);
//        System.out.println(orderItem);
        req.setAttribute("orderItem",orderItem);
        req.getRequestDispatcher("/pages/order/order_details.jsp").forward(req,resp);
    }
    //查看我的订单
    protected void showMyOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User loginUser = (User) req.getSession().getAttribute("user");
        if (loginUser==null){
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }
        int userId = loginUser.getId();
        List<Order> order = (List<Order>) orderService.queryMyOrders(userId);
        req.setAttribute("order",order);
        req.getRequestDispatcher("/pages/order/order.jsp").forward(req,resp);
    }
    //签收订单/确认收货
    protected void receiverOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("orderId");
        User loginUser = (User) req.getSession().getAttribute("user");
        if (loginUser==null){
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }
        int userId = loginUser.getId();
        Order order = orderService.queryOrderByOrderId(orderId);
        orderService.receiveOrder(orderId);
        req.getRequestDispatcher("orderServlet?action=showAllOrders&userId=userId").forward(req,resp);
    }
}

