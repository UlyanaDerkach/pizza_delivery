package com.epam.pizza_delivery.service;

import com.epam.pizza_delivery.dao.impl.CategoryDAOImpl;
import com.epam.pizza_delivery.dao.impl.ProductDAOImpl;
import com.epam.pizza_delivery.entity.Category;
import com.epam.pizza_delivery.entity.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import static com.epam.pizza_delivery.util.constants.JspPageConstants.*;
import static com.epam.pizza_delivery.util.constants.ParameterConstants.*;
import static com.epam.pizza_delivery.util.constants.ParameterConstants.LANGUAGE;

public class ShowProductsByCategoryService implements Service {
    ProductDAOImpl productDAO = new ProductDAOImpl();
    CategoryDAOImpl categoryDAO = new CategoryDAOImpl();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        RequestDispatcher dispatcher;
        List<Product> products = productDAO.getAll();
        List<Category> categories = categoryDAO.getAll();
        session.setAttribute(CATEGORIES, categories);
        session.setAttribute(PRODUCTS, products);
        dispatcher = request.getRequestDispatcher(SHOW_MENU_JSP+ LANGUAGE_AS_PARAMETER + session.getAttribute(LANGUAGE));
        dispatcher.forward(request, response);

    }
}
