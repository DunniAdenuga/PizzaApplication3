package com.example.Dunni.myapplication.backend;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import sun.rmi.runtime.Log;

/**
 * Created by Dunni on 10/22/2017.
 */
public class ReadOrdersServlet extends HttpServlet {
    Long id;
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException,
            ServletException {

        if (id != null) {
            //id = Long.decode(req.getParameter("id"));

            DataStorePizza dao = new DataStorePizza();
            try {
                //Orders book = dao.getOrders(id);
                Result<Orders> orders = dao.listOrders("");
                ArrayList<Orders> custOrders = new ArrayList<>();
            /*req.setAttribute("customerId", book.getCustomerId());
            req.setAttribute("cost", book.getCost());
            req.setAttribute("date", book.getDate());
            req.setAttribute("size", book.getSize());
            req.setAttribute("crust", book.getCrust());
            req.setAttribute("protein", book.getProtein());
            req.setAttribute("sauce", book.getSauce());
            req.setAttribute("orders", book);
            req.setAttribute("orderList", orders);*/
                for (int i = 0; i < orders.result.size(); i++) {
                    if (orders.result.get(i).getCustomerId().equalsIgnoreCase(String.valueOf(id))) {
                        custOrders.add(orders.result.get(i));
                    }
                }
                //req.getRequestDispatcher("/orderRead").forward(req, resp);
                //req.getRequestDispatcher("/base.jsp").forward(req, resp);
                resp.setContentType("text/plain");
                PrintWriter writer = resp.getWriter();
                //writer.write("datastoreId/CustomerId: " + String.valueOf(id) + "\n");

                if (custOrders.size() == 0) {
                    writer.write("No order for this customer!");
                } else {
                    for (int i = 0; i < custOrders.size(); i++) {
                        writer.write(custOrders.get(i).toString() );
                        writer.write("==\n");
                    }
                }

                //writer.write("name: " + c.getName());
                //writer.write(book.toString());
            } catch (Exception e) {
                throw new ServletException("Error reading Orders", e);
            }
        }else{
            PrintWriter writer = resp.getWriter();
            writer.write("No order for this customer because ID has issues!");
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        try{
        Customer customer =  new Customer.Builder()
                        .id(Long.parseLong(req.getParameter("id"))).build();
        id = customer.getId();

        resp.sendRedirect("/orderRead");
        resp.getWriter().write(String.valueOf(id));

    } catch (Exception e) {
        throw new ServletException("Error reading order", e);
    }
    }
}
