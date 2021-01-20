package com.epam.pizza_delivery.service;

import com.epam.pizza_delivery.entity.Product;
import com.epam.pizza_delivery.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import static com.epam.pizza_delivery.util.constants.JspPageConstants.*;
import static com.epam.pizza_delivery.util.constants.ParameterConstants.*;

public class RemoveFromCart implements Service {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute(USER);
            if (user != null && !user.isAdmin()) {
                long cartItemID = Long.parseLong(request.getParameter(PRODUCT_ID));
                int cartItemsAmount = (int) session.getAttribute(CART_ITEMS_AMOUNT);
                ArrayList<Product> cart = (ArrayList<Product>) session.getAttribute(CART);

                cart.removeIf(cartItem -> (cartItem.getId() == cartItemID));
                cartItemsAmount--;

                session.setAttribute(CART, cart);
                session.setAttribute(CART_ITEMS_AMOUNT, cartItemsAmount);
                response.sendRedirect(CART_JSP + LANGUAGE_AS_PARAMETER + session.getAttribute(LANGUAGE));
            } else {
                response.sendRedirect(ACCESS_ERROR_JSP+ LANGUAGE_AS_PARAMETER + session.getAttribute(LANGUAGE));
            }
        }
    }

