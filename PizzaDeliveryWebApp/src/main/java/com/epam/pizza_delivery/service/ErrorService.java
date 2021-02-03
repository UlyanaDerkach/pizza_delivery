package com.epam.pizza_delivery.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.epam.pizza_delivery.util.constants.JspPageConstants.NOT_FOUND_ERROR_JSP;
import static com.epam.pizza_delivery.util.constants.ParameterConstants.LANGUAGE;
import static com.epam.pizza_delivery.util.constants.ParameterConstants.LANGUAGE_AS_PARAMETER;

public class ErrorService implements Service {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws  IOException {

        HttpSession session = request.getSession();

        response.sendRedirect(NOT_FOUND_ERROR_JSP + LANGUAGE_AS_PARAMETER + session.getAttribute(LANGUAGE));
    }
}
