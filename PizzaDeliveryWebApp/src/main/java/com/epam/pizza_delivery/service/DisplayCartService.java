package com.epam.pizza_delivery.service;


import com.epam.pizza_delivery.entity.User;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;


import static com.epam.pizza_delivery.util.constants.JspPageConstants.*;
import static com.epam.pizza_delivery.util.constants.ParameterConstants.*;

public class DisplayCartService implements Service {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws  IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER);

        if (user != null && !user.isAdmin()) {
            response.sendRedirect(CART_JSP + LANGUAGE_AS_PARAMETER + session.getAttribute(LANGUAGE));
        } else {
            response.sendRedirect(ACCESS_ERROR_JSP+ LANGUAGE_AS_PARAMETER + session.getAttribute(LANGUAGE));
        }

    }
}
