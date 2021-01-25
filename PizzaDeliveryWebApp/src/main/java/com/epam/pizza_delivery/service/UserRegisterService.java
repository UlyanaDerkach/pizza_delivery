package com.epam.pizza_delivery.service;

import com.epam.pizza_delivery.dao.impl.UserDAOImpl;
import com.epam.pizza_delivery.dao.interfaces.UserDAO;
import com.epam.pizza_delivery.entity.User;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;

import static com.epam.pizza_delivery.util.constants.ErrorConstants.*;
import static com.epam.pizza_delivery.util.constants.JspPageConstants.*;
import static com.epam.pizza_delivery.util.constants.ParameterConstants.*;
import static com.epam.pizza_delivery.validation.UserDataValidation.*;

public class UserRegisterService implements Service {
    private UserDAO userDAO = new UserDAOImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        RequestDispatcher dispatcher;
        if (userDAO.isUserExist(request.getParameter(LOGIN))) {
            session.setAttribute(WRONG_LOGIN, LOGIN_EXISTS_ERROR);
            response.sendRedirect(REGISTER_JSP+ LANGUAGE_AS_PARAMETER + session.getAttribute(LANGUAGE));

        } else if (!validateEmail(request.getParameter(EMAIL))) {
            session.setAttribute(WRONG_EMAIL, EMAIL_ERROR);
            response.sendRedirect(REGISTER_JSP+ LANGUAGE_AS_PARAMETER + session.getAttribute(LANGUAGE));
        } else if (!validatePhone(request.getParameter(PHONE))) {
            session.setAttribute(WRONG_PHONE, PHONE_ERROR);
            response.sendRedirect(REGISTER_JSP+ LANGUAGE_AS_PARAMETER + session.getAttribute(LANGUAGE));
        } else if (!validatePassword(request.getParameter(PASSWORD))) {
            session.setAttribute(WRONG_PASSWORD, PASSWORD_ERROR);
            response.sendRedirect(REGISTER_JSP+ LANGUAGE_AS_PARAMETER + session.getAttribute(LANGUAGE));

        } else if(!confirmPassword(request.getParameter(PASSWORD), request.getParameter(CONFIRMED_PASSWORD))){
            session.setAttribute(PASSWORD_NOT_CONFIRMED, PASSWORD_NOT_CONFIRMED_ERROR);
            response.sendRedirect(REGISTER_JSP+ LANGUAGE_AS_PARAMETER + session.getAttribute(LANGUAGE));
        }else {
            User newUser = new User();
            newUser.setAdmin(false);
            newUser.setDeleted(false);
            newUser.setFirstName(request.getParameter(FIRST_NAME));
            newUser.setLastName(request.getParameter(LAST_NAME));
            newUser.setEmail(request.getParameter(EMAIL));
            newUser.setPhone(request.getParameter(PHONE));
            newUser.setBirthDate(Date.valueOf(request.getParameter(BIRTH_DATE)));
            newUser.setAddress(request.getParameter(ADDRESS));
            newUser.setLogin(request.getParameter(LOGIN));
            String password = request.getParameter(PASSWORD);
            String securedPassword = DigestUtils.md5Hex(password);
            newUser.setPasswordHash(securedPassword);

            userDAO.create(newUser);
            session.setAttribute(USER, newUser);
            response.sendRedirect(INDEX_JSP + LANGUAGE_AS_PARAMETER + session.getAttribute(LANGUAGE));

        }
    }
    private boolean confirmPassword(String password, String confirmPassword){

        return password.equals(confirmPassword);

    }
}
