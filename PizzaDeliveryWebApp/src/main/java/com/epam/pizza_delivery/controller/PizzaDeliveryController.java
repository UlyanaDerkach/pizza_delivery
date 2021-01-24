package com.epam.pizza_delivery.controller;

import com.epam.pizza_delivery.service.Service;
import com.epam.pizza_delivery.service.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import org.apache.log4j.Logger;


public class PizzaDeliveryController extends HttpServlet {
    private  final ServiceFactory SERVICE_FACTORY = ServiceFactory.getInstance();
    private final Logger LOGGER = Logger.getLogger(this.getClass().getName());
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)  {
        String requestString = req.getRequestURI().toLowerCase();
        Service service = SERVICE_FACTORY.getService(requestString);

        try {
            service.execute(req, resp);
        } catch (ServletException e) {
            LOGGER.error(e);
        } catch (IOException e) {
            LOGGER.error(e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        doGet(req, resp);
    }
}
