package com.epam.pizza_delivery.service;

import com.epam.pizza_delivery.dao.impl.ProductDAOImpl;
import com.epam.pizza_delivery.entity.Product;
import com.epam.pizza_delivery.entity.User;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import static com.epam.pizza_delivery.util.constants.JspPageConstants.ACCESS_ERROR_JSP;
import static com.epam.pizza_delivery.util.constants.ParameterConstants.*;
import static com.epam.pizza_delivery.util.constants.ServiceConstants.SHOW_PRODUCTS_BY_CATEGORY_ID_SERVICE;

public class AddProductService implements Service {

    private final Logger LOGGER = Logger.getLogger(this.getClass().getName());
    private ProductDAOImpl productDAO = new ProductDAOImpl();
    private final String UPLOAD_DIRECTORY = ResourceBundle.getBundle("imgPath").getString("img.absolute.path.base");

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        User user = (User) request.getSession().getAttribute(USER);
        if(user.isAdmin()) {
            Map<String, String> parametersToValues = new HashMap<>();
            if (ServletFileUpload.isMultipartContent(request)) {
                try {
                    List<FileItem> multiParts = new ServletFileUpload(
                            new DiskFileItemFactory()).parseRequest(request);

                    for (FileItem item : multiParts) {
                        if (item.isFormField()) {
                            parametersToValues.put(item.getFieldName(), item.getString("UTF-8"));
                        } else {

                            String fileName = new File(item.getName()).getName();
                            item.write(new File(UPLOAD_DIRECTORY + File.separator + fileName));
                            parametersToValues.put(PICTURE_PATH, fileName);
                        }
                    }


                } catch (Exception ex) {
                    LOGGER.error(ex);
                }

            } else {
                LOGGER.error("Sorry, it only handles file upload request");
            }
            Product product = new Product();
            fillProduct(parametersToValues, product);
            productDAO.create(product);
            response.sendRedirect(SHOW_PRODUCTS_BY_CATEGORY_ID_SERVICE+ LANGUAGE_AS_PARAMETER + request.getSession().getAttribute(LANGUAGE));
        } else{
            response.sendRedirect(ACCESS_ERROR_JSP+ LANGUAGE_AS_PARAMETER + request.getSession().getAttribute(LANGUAGE));
        }

    }
    private void fillProduct(Map parametersToValues, Product product){
        product.setName((String) parametersToValues.get(PRODUCT_NAME));
        product.setDescription((String) parametersToValues.get(PRODUCT_DESCRIPTION));
        product.setPrice(Integer.parseInt((String) parametersToValues.get(PRODUCT_PRICE)));
        product.setCategoryId(Long.parseLong((String) parametersToValues.get(PRODUCT_CATEGORY_ID)));
        product.setPicturePath((String) parametersToValues.get(PICTURE_PATH));
    }


}
