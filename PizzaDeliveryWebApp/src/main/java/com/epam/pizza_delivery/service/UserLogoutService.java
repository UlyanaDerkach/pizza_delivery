package com.epam.pizza_delivery.service;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import static com.epam.pizza_delivery.util.constants.JspPageConstants.INDEX_JSP;
import static com.epam.pizza_delivery.util.constants.ParameterConstants.LANGUAGE;
import static com.epam.pizza_delivery.util.constants.ParameterConstants.LANGUAGE_AS_PARAMETER;

public class UserLogoutService implements Service {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        request.getSession().invalidate();
        response.sendRedirect(INDEX_JSP);

    }
}
