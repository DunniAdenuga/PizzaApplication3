package com.example.Dunni.myapplication.backend;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Dunni on 10/22/2017.
 */
public class CreateOrdersServlet extends HttpServlet{
    Long id;
    DataStorePizza dao = new DataStorePizza();

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        Orders o =  dao.getOrders(id);
        resp.setContentType("text/plain");
        PrintWriter writer = resp.getWriter();
        //writer.write("datastoreId/OrderId: " +String.valueOf(id) + "\n");
        //writer.write("name: " + c.getName());
        writer.write(o.toString());
    }
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        // [START bookBuilder]
        Orders orders = new Orders.Builder()
                .customerId(req.getParameter("customerId"))
                .cost(req.getParameter("cost"))   // form parameter
                .date(req.getParameter("date"))
                .size(req.getParameter("size"))
                .protein(req.getParameter("protein"))
                .crust(req.getParameter("crust"))
                .sauce(req.getParameter("sauce"))
                .build();
        // [END bookBuilder]

        try {
            id = dao.createOrder(orders);
            resp.sendRedirect("/order");   // read what we just wrote
            resp.getWriter().write(String.valueOf(id));
        } catch (Exception e) {
            throw new ServletException("Error creating order", e);
        }
    }
    // [END formpost]
}
