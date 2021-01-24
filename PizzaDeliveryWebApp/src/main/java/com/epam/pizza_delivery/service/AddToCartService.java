package com.epam.pizza_delivery.service;

import com.epam.pizza_delivery.dao.impl.ProductDAOImpl;
import com.epam.pizza_delivery.entity.Product;
import com.epam.pizza_delivery.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import static com.epam.pizza_delivery.util.constants.JspPageConstants.ACCESS_ERROR_JSP;
import static com.epam.pizza_delivery.util.constants.JspPageConstants.SHOW_MENU_JSP;
import static com.epam.pizza_delivery.util.constants.ParameterConstants.*;

public class AddToCartService implements Service {
    private Integer cartItemsAmount = 0;
    ProductDAOImpl productDAO = new ProductDAOImpl();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws  IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER);
        if (!user.isAdmin() && user != null) {
            ArrayList<Product> cart = (ArrayList<Product>) session.getAttribute(CART);

            if (cart == null) {
                cart = new ArrayList<>();
            }
            long productInCartId = Long.parseLong(request.getParameter(PRODUCT_ID));
            Product product = productDAO.getByID(productInCartId);
            cart.add(product);
            cartItemsAmount++;


            session.setAttribute(CART, cart);
            session.setAttribute(CART_ITEMS_AMOUNT, cartItemsAmount);

            response.sendRedirect(SHOW_MENU_JSP + LANGUAGE_AS_PARAMETER + session.getAttribute(LANGUAGE));
        } else{
            response.sendRedirect(ACCESS_ERROR_JSP + LANGUAGE_AS_PARAMETER + session.getAttribute(LANGUAGE));
        }
    }

}
