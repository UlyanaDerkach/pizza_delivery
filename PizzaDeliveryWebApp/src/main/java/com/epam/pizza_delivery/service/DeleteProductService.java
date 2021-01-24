package com.epam.pizza_delivery.service;

import com.epam.pizza_delivery.dao.impl.ProductDAOImpl;
import com.epam.pizza_delivery.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;

import static com.epam.pizza_delivery.util.constants.JspPageConstants.ACCESS_ERROR_JSP;
import static com.epam.pizza_delivery.util.constants.JspPageConstants.SHOW_MENU_JSP;
import static com.epam.pizza_delivery.util.constants.ParameterConstants.*;
import static com.epam.pizza_delivery.util.constants.ParameterConstants.LANGUAGE;
import static com.epam.pizza_delivery.util.constants.ServiceConstants.SHOW_PRODUCTS_BY_CATEGORY_ID_SERVICE;

public class DeleteProductService implements Service {
    ProductDAOImpl productDAO = new ProductDAOImpl();
    private final String STORAGE_DIRECTORY = ResourceBundle.getBundle("imgPath").getString("img.absolute.path.base");
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER);
        if (user.isAdmin()) {
            long productId = Long.parseLong(request.getParameter(PRODUCT_ID));
            String fileName = productDAO.getByID(productId).getPicturePath();
            deleteImageFromDirectory(fileName);
            productDAO.delete(productId);
            response.sendRedirect(SHOW_PRODUCTS_BY_CATEGORY_ID_SERVICE+ LANGUAGE_AS_PARAMETER + session.getAttribute(LANGUAGE));

        } else{
            response.sendRedirect(ACCESS_ERROR_JSP+ LANGUAGE_AS_PARAMETER + session.getAttribute(LANGUAGE));
        }
    }
    private void deleteImageFromDirectory(String fileName){
        File deleteFile = new File(STORAGE_DIRECTORY + File.separator + fileName) ;
        if( deleteFile.exists() ){
            deleteFile.delete();
        }

    }
}
