package com.epam.pizza_delivery.service;

import com.epam.pizza_delivery.dao.impl.UserDAOImpl;
import com.epam.pizza_delivery.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;

import static com.epam.pizza_delivery.util.constants.JspPageConstants.ACCESS_ERROR_JSP;
import static com.epam.pizza_delivery.util.constants.JspPageConstants.PERSONAL_CABINET_JSP;
import static com.epam.pizza_delivery.util.constants.ParameterConstants.*;

public class EditUserInfoService implements Service {
    UserDAOImpl userDAO = new UserDAOImpl();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws  IOException{
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER);
        if (user != null) {
            user.setFirstName(request.getParameter(FIRST_NAME));
            user.setLastName(request.getParameter(LAST_NAME));
            user.setEmail(request.getParameter(EMAIL));
            user.setPhone(request.getParameter(PHONE));
            user.setBirthDate(Date.valueOf(request.getParameter(BIRTH_DATE)));
            user.setAddress(request.getParameter(ADDRESS));
            user.setLogin(request.getParameter(LOGIN));
            userDAO.update(user.getId(), user);
            session.setAttribute(USER, user);
            response.sendRedirect(PERSONAL_CABINET_JSP + LANGUAGE_AS_PARAMETER + session.getAttribute(LANGUAGE));
        } else{
            response.sendRedirect(ACCESS_ERROR_JSP+ LANGUAGE_AS_PARAMETER + session.getAttribute(LANGUAGE));
        }
    }
}
