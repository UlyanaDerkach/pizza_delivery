package com.epam.pizza_delivery.service;

import com.epam.pizza_delivery.dao.impl.OrderDAOImpl;
import com.epam.pizza_delivery.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import static com.epam.pizza_delivery.util.constants.JspPageConstants.ACCESS_ERROR_JSP;
import static com.epam.pizza_delivery.util.constants.JspPageConstants.ORDER_HISTORY_JSP;
import static com.epam.pizza_delivery.util.constants.ParameterConstants.*;
import static com.epam.pizza_delivery.util.constants.ServiceConstants.ORDER_HISTORY_SERVICE;

public class EditOrderStatusService implements Service{
    OrderDAOImpl orderDAO =  new OrderDAOImpl();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER);
        if (user.isAdmin()) {
            long orderIdToUpdateStatus = Long.parseLong(request.getParameter(ORDER_ID));
            long newStatusId = Long.parseLong(request.getParameter(STATUS_ID));
            orderDAO.updateOrderStatus(orderIdToUpdateStatus, newStatusId);
            response.sendRedirect(ORDER_HISTORY_SERVICE+ LANGUAGE_AS_PARAMETER + session.getAttribute(LANGUAGE));
        } else{
            response.sendRedirect(ACCESS_ERROR_JSP+ LANGUAGE_AS_PARAMETER + session.getAttribute(LANGUAGE));
        }
    }
}
