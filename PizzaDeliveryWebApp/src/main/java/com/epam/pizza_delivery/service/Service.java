package com.epam.pizza_delivery.service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public interface Service {
    void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,
            ParseException, SQLException;
}
