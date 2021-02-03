package com.epam.pizza_delivery.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.epam.pizza_delivery.util.constants.JspPageConstants.INDEX_JSP;


public class UserLogoutService implements Service {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws  IOException {

        request.getSession().invalidate();
        response.sendRedirect(INDEX_JSP);

    }
}
