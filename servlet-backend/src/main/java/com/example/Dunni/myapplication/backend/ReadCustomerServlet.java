package com.example.Dunni.myapplication.backend;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Dunni on 10/22/2017.
 */
public class ReadCustomerServlet extends HttpServlet{

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException,
            ServletException {
        Long id = Long.decode(req.getParameter("id"));
        DataStorePizza dao = new DataStorePizza();
        try {
            Customer book = dao.getCustomer(id);
            req.setAttribute("name", book.getName());
            req.setAttribute("email", book.getEmail());
            req.setAttribute("address", book.getAddress());
            req.setAttribute("phone", book.getPhone());
            req.setAttribute("customer", book);
            req.getRequestDispatcher("/customerRead").forward(req, resp);
            //req.getRequestDispatcher("/base.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new ServletException("Error reading customer", e);
        }
    }

}
