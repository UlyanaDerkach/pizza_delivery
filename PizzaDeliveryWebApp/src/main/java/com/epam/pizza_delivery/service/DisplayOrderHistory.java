package com.epam.pizza_delivery.service;

import com.epam.pizza_delivery.dao.impl.*;
import com.epam.pizza_delivery.dao.interfaces.OrderDAO;
import com.epam.pizza_delivery.dao.interfaces.StatusDAO;
import com.epam.pizza_delivery.dao.interfaces.UserDAO;
import com.epam.pizza_delivery.entity.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static com.epam.pizza_delivery.util.constants.JspPageConstants.ACCESS_ERROR_JSP;
import static com.epam.pizza_delivery.util.constants.JspPageConstants.ORDER_HISTORY_JSP;
import static com.epam.pizza_delivery.util.constants.ParameterConstants.*;

public class DisplayOrderHistory implements Service {
   OrderHistoryDAOImpl orderHistoryDAO = new OrderHistoryDAOImpl();
   OrderDAOImpl orderDAO = new OrderDAOImpl();
   StatusDAOImpl statusDAO = new StatusDAOImpl();
   UserDAOImpl userDAO = new UserDAOImpl();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER);
        if (user != null) {
            RequestDispatcher dispatcher;
            List<OrderItem> orderItems;
            List<Order> orders;
            List<StatusDict> statusList = statusDAO.getAll();
            List<User> customers = new ArrayList<>();
            if (user.isAdmin()) {
                orderItems = orderHistoryDAO.getAll();
                orders = orderDAO.getAll();
                for (Order order : orders) {
                    User customer = userDAO.getByID(order.getUserId());
                    customers.add(customer);
                }
                session.setAttribute(CUSTOMERS, customers);
            } else {
                orderItems = orderHistoryDAO.getOrderByUserId(user.getId());
                orders = orderDAO.getOrderByUserId(user.getId());

            }
            session.setAttribute(ORDER_ITEMS, orderItems);
            session.setAttribute(ORDERS, orders);
            session.setAttribute(STATUSES, statusList);
            dispatcher = request.getRequestDispatcher(ORDER_HISTORY_JSP+ LANGUAGE_AS_PARAMETER + session.getAttribute(LANGUAGE));
            dispatcher.forward(request, response);
        } else{
            response.sendRedirect(ACCESS_ERROR_JSP+ LANGUAGE_AS_PARAMETER + session.getAttribute(LANGUAGE));
        }
    }
}
