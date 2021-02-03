package com.epam.pizza_delivery.service;

import com.epam.pizza_delivery.dao.impl.UserDAOImpl;
import com.epam.pizza_delivery.entity.User;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


import static com.epam.pizza_delivery.util.constants.ErrorConstants.*;
import static com.epam.pizza_delivery.util.constants.ErrorConstants.PASSWORD_ERROR;
import static com.epam.pizza_delivery.util.constants.JspPageConstants.*;
import static com.epam.pizza_delivery.util.constants.ParameterConstants.*;
import static com.epam.pizza_delivery.validation.UserDataValidation.validatePassword;

public class ChangePasswordService implements Service {

    private UserDAOImpl userDAO = new UserDAOImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER);
        RequestDispatcher dispatcher;

        if (user != null) {
            if (!confirmPassword(request.getParameter(PASSWORD), request.getParameter(CONFIRMED_PASSWORD))) {
                session.setAttribute(PASSWORD_NOT_CONFIRMED, PASSWORD_NOT_CONFIRMED_ERROR);
                dispatcher = request.getRequestDispatcher(PERSONAL_CABINET_JSP + LANGUAGE_AS_PARAMETER + session.getAttribute(LANGUAGE));
                dispatcher.forward(request, response);
            } else if (!validatePassword(request.getParameter(PASSWORD))) {
                session.setAttribute(WRONG_PASSWORD, PASSWORD_ERROR);
                dispatcher = request.getRequestDispatcher(PERSONAL_CABINET_JSP + LANGUAGE_AS_PARAMETER + session.getAttribute(LANGUAGE));
                dispatcher.forward(request, response);

            }else {
                String password = request.getParameter(PASSWORD);
                String securedPassword = DigestUtils.md5Hex(password);
                userDAO.changePassword(securedPassword, user.getId());
                session.setAttribute(USER, user);
            }
            response.sendRedirect(PERSONAL_CABINET_JSP+ LANGUAGE_AS_PARAMETER + session.getAttribute(LANGUAGE));
        } else{
            dispatcher = request.getRequestDispatcher(ACCESS_ERROR_JSP+ LANGUAGE_AS_PARAMETER + session.getAttribute(LANGUAGE));
            dispatcher.forward(request, response);
        }
    }

    private boolean confirmPassword(String password, String confirmPassword) {

        return password.equals(confirmPassword);

    }

}
