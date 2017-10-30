package com.example.pizzaapplication3;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.Dunni.myapplication.backend.Customer;
import com.example.Dunni.myapplication.backend.Orders;
import com.example.Dunni.myapplication.backend.Result;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TypeOfOrder extends AppCompatActivity {
    //DBHelper dbHelper;

    String help = "";

    String backend = "http://pizzaapplication-183620.appspot.com/orderRead";
    private ListView mListView;
    public static final String NAME = "com.example.pizzaapplication.MESSAGE";
    public static final String ADDRESS = "com.example.pizzaappplication.MESSAGE";
    public static final String EMAIL = "com.example.pizzaappplicatioon.MESSAGE";
    public static final String PHONE = "com.example.piizzaappplication.MESSAGE";
    public static final String CUSTID = "com.exammmple.piizzaappplication.MESSAGE";

    public static final String SIZE = "com.exammmple.piizzaappplicationtion.MESSAGE";
    public static final String CRUST = "com.exammmple.piizzaappplication.MESSAGELOV";
    public static final String PROTEIN = "coooooom.exammmple.piizzaappplication.MESSAGE";
    public static final String SAUCE = "coooooom.exammmple.piizzaappplication.MEMESSSSAGE";
    public static final String COST = "comcom.exammmple.piizzaappplication.MESSAGE";

    public String copyChar(String string){
        char[] result = new char[string.length()];
    for(int i = 0; i < string.length(); i++){
         //result = new char[string.length()];
         result[i] = string.charAt(i);
    }
    return String.copyValueOf(result);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_of_order);
        this.setTitle("Previous Orders");
        //dbHelper = new DBHelper(this);

        final Intent intent = getIntent();
        final String name = intent.getStringExtra(CustomerProfileActivity.NAME);
        String email = intent.getStringExtra(CustomerProfileActivity.EMAIL);
        final String address = intent.getStringExtra(CustomerProfileActivity.ADDRESS);
        String phone = intent.getStringExtra(CustomerProfileActivity.PHONE);
        String custId = intent.getStringExtra(CustomerProfileActivity.CUSTID);

         help = copyChar(custId);
        final Context context = getApplicationContext();
        final TextView textView = new TextView(context);
        textView.setText(help);

        RequestQueue queue = Volley.newRequestQueue(this);

        final Intent orderPIntent = new Intent(this, OrderPage.class);
        final TextView help = new TextView(context);
        //final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);
        StringRequest getReq = new StringRequest(Request.Method.POST, backend,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("volleyTO: ", "POST response received.");
                        //see how orders are displayed in response
                        Log.i("responseTO: ", response);
                        //do something with response
                         final String[] listItems = response.split("==\n");

                         //help.setText(listItems[0]);
                        //Log.i("response[0]: ",help.getText().toString());
                        mListView = (ListView) findViewById(R.id.order_list_view);
                        //mListView.setLongClickable(true);
                        final ArrayAdapter adapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, listItems);
                        //adapter.add(help.getText().toString());
                       mListView.setAdapter(adapter);
                        //Add Listener to ListView
                        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position,
                                                    long id) {
                            String[] orderItems = listItems[position].split("\n");
                                orderPIntent.putExtra(SIZE, orderItems[1].split(": ")[1]);
                                orderPIntent.putExtra(CRUST, orderItems[2].split(": ")[1]);
                                orderPIntent.putExtra(PROTEIN, orderItems[3].split(": ")[1]);
                                orderPIntent.putExtra(SAUCE, orderItems[4].split(": ")[1]);
                                orderPIntent.putExtra(COST, orderItems[5].split(": ")[1]);
                                orderPIntent.putExtra(NAME, name);
                                orderPIntent.putExtra(ADDRESS, address);
                                orderPIntent.putExtra("caller", TypeOfOrder.class.toString());
                                startActivity(orderPIntent);
                            }
                        });
                       /* mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                            public boolean onItemLongClick(AdapterView<?> parent, View v, int position, long id) {
                                //Do some
                                ((ArrayList)Arrays.asList(listItems)).remove(position);
                                adapter.notifyDataSetChanged();
                                return true;
                            }});*/
                        ///////////////////////////////
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error){
                Log.e("volley:", "error! " + error.toString());
            }
        }){
            //Log.i("customerrrId", help.toString());
            protected Map<String, String> getParams() {
                Log.i("here", "here");
                Map<String, String> params = new HashMap<String, String>();
                params.put(Customer.ID, textView.getText().toString());

                return params;
            }
        };
        //Log.i("customerrrId", help.toString());
        queue.add(getReq);

    }

    /*Happens when button is clicked*/
    public void pickPizaDetails(View view) {
        Intent intent2 = getIntent();
        Intent intent = new Intent(this, PizzaDetailsActivity.class);
        intent.putExtra(NAME, intent2.getStringExtra(CustomerProfileActivity.NAME));
        intent.putExtra(ADDRESS, intent2.getStringExtra(CustomerProfileActivity.ADDRESS));
        intent.putExtra(EMAIL, intent2.getStringExtra(CustomerProfileActivity.EMAIL));
        intent.putExtra(PHONE, intent2.getStringExtra(CustomerProfileActivity.PHONE));
        intent.putExtra(CUSTID, intent2.getStringExtra(CustomerProfileActivity.CUSTID));
        intent.putExtra("caller", TypeOfOrder.class.toString());
        startActivity(intent);

    }

}
