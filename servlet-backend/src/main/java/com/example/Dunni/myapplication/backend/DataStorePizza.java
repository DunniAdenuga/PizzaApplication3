package com.example.Dunni.myapplication.backend;

import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.appengine.api.datastore.QueryResultIterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 * Created by Dunni on 10/21/2017.
 */

public class DataStorePizza {

    private DatastoreService datastore;
    private static final String CUSTOMER_KIND = "CustomerInstance";
    private static final String ORDER_KIND = "OrderInstance";

    public DataStorePizza(){
        datastore = DatastoreServiceFactory.getDatastoreService();
    }

    public Customer entityToCustomer(Entity entity){
        return new Customer.Builder()
                .name((String)entity.getProperty(Customer.NAME))
                .email((String)entity.getProperty(Customer.EMAIL))
                .address((String)entity.getProperty(Customer.ADDRESS))
                .phone((String)entity.getProperty(Customer.PHONE))
                .id(entity.getKey().getId())
                .build();
    }

    public Orders entityToOrders(Entity entity){
        return new Orders.Builder()
                .customerId((String)entity.getProperty(Orders.CUSTOMERID))
                .cost((String)entity.getProperty(Orders.COST))
                .date((String)entity.getProperty(Orders.DATE))
                .size((String)entity.getProperty(Orders.SIZE))
                .crust((String)entity.getProperty(Orders.CRUST))
                .protein((String)entity.getProperty(Orders.PROTEIN))
                .sauce((String)entity.getProperty(Orders.SAUCE))
                .id(entity.getKey().getId())
                .build();
    }

    public Long createCustomer(Customer customer){
        Entity entity = new Entity(CUSTOMER_KIND);
        entity.setProperty(Customer.NAME, customer.getName());
        entity.setProperty(Customer.EMAIL, customer.getEmail());
        entity.setProperty(Customer.ADDRESS, customer.getAddress());
        entity.setProperty(Customer.PHONE, customer.getPhone());

        Key key = datastore.put(entity);
        customer.setId(key.getId());
        return key.getId();
    }

    public Long createOrder(Orders orders){
        Entity entity = new Entity(ORDER_KIND);
        entity.setProperty(Orders.CUSTOMERID, orders.getCustomerId());
        entity.setProperty(Orders.COST, orders.getCost());
        entity.setProperty(Orders.DATE, orders.getDate());
        entity.setProperty(Orders.SIZE, orders.getSize());
        entity.setProperty(Orders.CRUST, orders.getCrust());
        entity.setProperty(Orders.PROTEIN, orders.getProtein());
        entity.setProperty(Orders.SAUCE, orders.getSauce());

        Key key = datastore.put(entity);
        return key.getId();
    }

    public Customer getCustomer(Long customerId){
        try {
            Entity customerEntity = datastore.get(KeyFactory.createKey(CUSTOMER_KIND, customerId));
            return entityToCustomer(customerEntity);
        } catch (EntityNotFoundException e) {
            return null;
        }
    }

    public Orders getOrders(Long orderId){
        try {
            Entity orderEntity = datastore.get(KeyFactory.createKey(ORDER_KIND, orderId));
            return entityToOrders(orderEntity);
        } catch (EntityNotFoundException e) {
            return null;
        }
    }

    public void updateCustomer(Customer customer){
        Key key = KeyFactory.createKey(CUSTOMER_KIND, customer.getId());
        Entity entity = new Entity(key);
        entity.setProperty(Customer.NAME, customer.getName());
        entity.setProperty(Customer.EMAIL, customer.getEmail());
        entity.setProperty(Customer.ADDRESS, customer.getAddress());
        entity.setProperty(Customer.PHONE, customer.getPhone());

        datastore.put(entity);
    }

    public void updateOrders(Orders orders){
        Key key = KeyFactory.createKey(ORDER_KIND, orders.getId());
        Entity entity = new Entity(key);
        entity.setProperty(Orders.CUSTOMERID, orders.getCustomerId());
        entity.setProperty(Orders.COST, orders.getCost());
        entity.setProperty(Orders.DATE, orders.getDate());
        entity.setProperty(Orders.SIZE, orders.getSize());
        entity.setProperty(Orders.CRUST, orders.getCrust());
        entity.setProperty(Orders.PROTEIN, orders.getProtein());
        entity.setProperty(Orders.SAUCE, orders.getSauce());

        datastore.put(entity);
    }

    public void deleteCustomer(Long customerId){
        Key key = KeyFactory.createKey(CUSTOMER_KIND, customerId);
        datastore.delete(key);
    }

    public void deleteOrders(Long ordersId){
        Key key = KeyFactory.createKey(ORDER_KIND, ordersId);
        datastore.delete(key);
    }

    public List<Customer> entitiesToCustomers(Iterator<Entity> results) {
        List<Customer> resultCustomers = new ArrayList<>();
        while (results.hasNext()) {  // We still have data
            resultCustomers.add(entityToCustomer(results.next()));      // Add the Customer to the List
        }
        return resultCustomers;
    }

    public List<Orders> entitiesToOrders(Iterator<Entity> results) {
        List<Orders> resultOrders = new ArrayList<>();
        while (results.hasNext()) {  // We still have data
            resultOrders.add(entityToOrders(results.next()));// Add the Order to the List
        }
        return resultOrders;
    }

    public Result<Customer> listCustomers(String startCursorString){
        FetchOptions fetchOptions = FetchOptions.Builder.withLimit(10);// Only show 10 at a time
        if(startCursorString != null && !startCursorString.equals("")){
            fetchOptions.startCursor(Cursor.fromWebSafeString(startCursorString));
        }
        Query query = new Query(CUSTOMER_KIND).
                addSort(Customer.NAME, SortDirection.ASCENDING);
        PreparedQuery preparedQuery = datastore.prepare(query);
        QueryResultIterator<Entity> results = preparedQuery.asQueryResultIterator(fetchOptions);

        List<Customer> resultCustomers = entitiesToCustomers(results);
        Cursor cursor = results.getCursor();
        if(cursor != null && resultCustomers.size() == 10){
            String cursorString = cursor.toWebSafeString();
            return new Result<>(resultCustomers, cursorString);
        }else{
            return new Result<>(resultCustomers);
        }

    }

    public Result<Orders> listOrders(String startCursorString){
        FetchOptions fetchOptions = FetchOptions.Builder.withLimit(50);// Only show 10 at a time
        if(startCursorString != null && !startCursorString.equals("")){
            fetchOptions.startCursor(Cursor.fromWebSafeString(startCursorString));
        }
        Query query = new Query(ORDER_KIND).
                addSort(Orders.DATE, SortDirection.ASCENDING);
        PreparedQuery preparedQuery = datastore.prepare(query);
        QueryResultIterator<Entity> results = preparedQuery.asQueryResultIterator(fetchOptions);

        List<Orders> resultOrders = entitiesToOrders(results);
        Cursor cursor = results.getCursor();
        if(cursor != null && resultOrders.size() == 10){
            String cursorString = cursor.toWebSafeString();
            return new Result<>(resultOrders, cursorString);
        }else{
            return new Result<>(resultOrders);
        }

    }

    }
