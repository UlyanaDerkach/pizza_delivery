package com.epam.pizza_delivery.service;

import com.epam.pizza_delivery.dao.impl.OrderDAOImpl;
import com.epam.pizza_delivery.dao.impl.OrderDetailsDAOImpl;
import com.epam.pizza_delivery.entity.Order;
import com.epam.pizza_delivery.entity.OrderDetails;
import com.epam.pizza_delivery.entity.Product;
import com.epam.pizza_delivery.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import java.time.*;
import java.util.ArrayList;
import java.util.List;

import static com.epam.pizza_delivery.util.constants.JspPageConstants.*;
import static com.epam.pizza_delivery.util.constants.ParameterConstants.*;

public class OrderService implements Service {
    private OrderDAOImpl orderDAO = new OrderDAOImpl();
    private OrderDetailsDAOImpl orderDetailsDAO = new OrderDetailsDAOImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER);
        RequestDispatcher dispatcher;

        if (user != null && !user.isAdmin()) {

            Order order = new Order();
            fillOrder(order, request);
            orderDAO.create(order);
            Order orderWithId = orderDAO.getOrderByData(order);
            List<Product> cart = (ArrayList<Product>) session.getAttribute(CART);
            for (Product product : cart) {
                OrderDetails orderDetails = new OrderDetails();
                fillOrderDetails(orderDetails, request, product, orderWithId);
                orderDetailsDAO.create(orderDetails);
            }
            session.setAttribute(CART, null);
            response.sendRedirect(CART_JSP + LANGUAGE_AS_PARAMETER + session.getAttribute(LANGUAGE));
        } else {
            dispatcher = request.getRequestDispatcher(ACCESS_ERROR_JSP+ LANGUAGE_AS_PARAMETER + session.getAttribute(LANGUAGE));
            dispatcher.forward(request, response);
        }
    }
    private static void fillOrder(Order order, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute(USER);
        order.setOrderDate(LocalDate.now());
        order.setOrderTime(LocalTime.now());
        order.setTotalSum(Integer.parseInt(request.getParameter(ORDER_TOTAL_SUM)));
        order.setDeliveryDate(LocalDate.parse(request.getParameter(DELIVERY_DATE)));
        order.setDeliveryTime(LocalTime.parse((request.getParameter(DELIVERY_TIME))+TIME_ADD_UP));
        order.setUserId(user.getId());
        order.setStatusId(INITIAL_STATUS_ID);
    }
    private static void fillOrderDetails(OrderDetails orderDetails, HttpServletRequest request, Product product, Order order){
        orderDetails.setOrderId(order.getId());
        orderDetails.setProductId(product.getId());
        orderDetails.setProductAmount(Integer.parseInt(request.getParameter(PRODUCT + product.getId() + PRODUCT_AMOUNT)));
        orderDetails.setTotalCost(product.getPrice()*orderDetails.getProductAmount());
    }
}
