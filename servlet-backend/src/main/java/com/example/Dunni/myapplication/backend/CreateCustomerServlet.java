package com.example.Dunni.myapplication.backend;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;


/**
 * Created by Dunni on 10/22/2017.
 */
public class CreateCustomerServlet extends HttpServlet {
    Long id;
    DataStorePizza dao = new DataStorePizza();
    // [START setup]
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        //req.setAttribute("action", "Add");          // Part of the Header in form.jsp
        //req.setAttribute("destination", "customer");  // The urlPattern to invoke (this Servlet)
        //req.setAttribute("page", "form");           // Tells base.jsp to include form.jsp
        //req.getRequestDispatcher("/").forward(req, resp);
        Customer c = dao.getCustomer(id);
        resp.setContentType("text/plain");
        PrintWriter writer = resp.getWriter();
        writer.write("datastoreId: " +String.valueOf(id) + "\n");
        //writer.write("name: " + c.getName());
        writer.write(c.toString());
    }
    // [END setup]

    // [START formpost]
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        // [START bookBuilder]
        Customer customer = new Customer.Builder()
                .name(req.getParameter("name"))   // form parameter
                .email(req.getParameter("email"))
                .address(req.getParameter("address"))
                .phone(req.getParameter("phone"))
                .build();
        // [END bookBuilder]
        boolean found = false;
        Customer temp = null;
        resp.setContentType("text/plain");
        //DataStorePizza dao = new DataStorePizza();
        try {
            Result<Customer> customers = dao.listCustomers("");
            for(int i = 0; i < customers.result.size(); i++){
                 temp = customers.result.get(i);
                if((temp.getName().equalsIgnoreCase(customer.getName())) &&
                        (temp.getEmail().equalsIgnoreCase(customer.getEmail())) &&
                        (temp.getAddress().equalsIgnoreCase(customer.getAddress())) &&
                        (temp.getPhone().equalsIgnoreCase(customer.getPhone()))){
                        found = true;
                        break;
                }
            }
            if(found == false) {
                id = dao.createCustomer(customer);
            }else{
                id = temp.getId();
            }
           // resp.sendRedirect("/read?id=" + id.toString());   // read what we just wrote
            resp.sendRedirect("/customer");
            resp.getWriter().write(String.valueOf(id));

        } catch (Exception e) {
            throw new ServletException("Error creating customer", e);
        }
    }
    // [END formpost]

}
