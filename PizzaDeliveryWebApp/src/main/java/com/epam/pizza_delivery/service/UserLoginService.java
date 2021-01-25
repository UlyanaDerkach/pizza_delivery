package com.epam.pizza_delivery.service;

import com.epam.pizza_delivery.dao.impl.UserDAOImpl;
import com.epam.pizza_delivery.dao.interfaces.UserDAO;
import com.epam.pizza_delivery.entity.User;
import com.epam.pizza_delivery.util.ErrorIdentifier;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import static com.epam.pizza_delivery.util.constants.ErrorConstants.AUTH_ERROR;
import static com.epam.pizza_delivery.util.constants.ErrorConstants.LOGIN_ERROR;
import static com.epam.pizza_delivery.util.constants.JspPageConstants.*;
import static com.epam.pizza_delivery.util.constants.ParameterConstants.*;


public class UserLoginService implements Service {
    private UserDAO userDAO = new UserDAOImpl();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        HttpSession session = request.getSession();
        RequestDispatcher dispatcher;

        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        String securedPassword = DigestUtils.md5Hex(password);


        User user = userDAO.getUserByLoginPassword(login, securedPassword);

        if (user != null) {
            session.setAttribute(USER, user);
        } else {
            session.setAttribute(AUTH_ERROR, ErrorIdentifier.getErrorFromLanguageBundle(request, LOGIN_ERROR));

        }
        response.sendRedirect(INDEX_JSP + LANGUAGE_AS_PARAMETER + session.getAttribute(LANGUAGE));
    }
}
