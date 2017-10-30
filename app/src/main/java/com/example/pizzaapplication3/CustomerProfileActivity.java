package com.example.pizzaapplication3;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.Dunni.myapplication.backend.Customer;

import java.util.HashMap;
import java.util.Map;


public class CustomerProfileActivity extends AppCompatActivity {

    String backend = "http://pizzaapplication-183620.appspot.com/customer";

    public static final String NAME = "com.example.pizzaapplication.MESSAGE";
    public static final String ADDRESS = "com.example.pizzaappplication.MESSAGE";
    public static final String EMAIL = "com.example.pizzaappplicatioon.MESSAGE";
    public static final String PHONE = "com.example.piizzaappplication.MESSAGE";
    public static final String CUSTID = "com.example.piizzaappplicatiooon.MESSSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TextView textView = (TextView)findViewById(R.id.textView);
        //textView.setPaintFlags(textView.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);

        //new ServletPostAsyncTask().execute(new Pair<Context, String>(this, "Dunni-A"));
        setContentView(R.layout.activity_customer_profile);
        this.setTitle("Customer Information");
        //dbHelper = new DBHelper(this);

    }

    @TargetApi(9)
    /*Call this when button is pressed*/
    public void pickPizzaDetails(View view){
            final Intent intent = new Intent(this, TypeOfOrder.class);
             RequestQueue queue = Volley.newRequestQueue(this);
            String custId = "";
            String response;
            Context context = getApplicationContext();
            final TextView help = new TextView(context);
            int duration = Toast.LENGTH_SHORT;
            CharSequence text = "Please complete all fields.";
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            // Do something in response to button
            final EditText name = (EditText)findViewById(R.id.editText);
            final EditText email = (EditText)findViewById(R.id.editText2);
            final EditText address = (EditText)findViewById(R.id.editText3);
            final EditText phone = (EditText)findViewById(R.id.editText4);
            //value from creating customer in datastore
        //can I make datastore check if I'm creating the same customer ? not just from key
        //final String customerId = "";

            if((name.getText().toString().isEmpty()) || (email.getText().toString().isEmpty()) || (address.getText().toString().isEmpty())
                    || (phone.getText().toString().isEmpty())){
                toast.show();
            }
            else
            {

                StringRequest postReq = new StringRequest(Request.Method.POST, backend,
                        new Response.Listener<String>(){
                            public void onResponse(String response){
                                Log.i("volley ", "POST response received.");
                                Log.i("response ", response);
                                //custId = response;
                                String[] stringArr = response.split("\n");
                                help.setText(stringArr[0].split(" ")[1]);
                                intent.putExtra(CUSTID, help.getText().toString());
                                Log.i("help", help.getText().toString());
                                intent.putExtra(NAME, name.getText().toString());
                                intent.putExtra(ADDRESS, address.getText().toString());
                                intent.putExtra(EMAIL, email.getText().toString());
                                intent.putExtra(PHONE, phone.getText().toString());
                                intent.putExtra("caller", CustomerProfileActivity.class.toString());
                                startActivity(intent);
                            }
                        }, new Response.ErrorListener(){
                    public void onErrorResponse(VolleyError error){
                        Log.e("volley:", "error! " + error.toString());
                    }
                }) {
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put(Customer.NAME, name.getText().toString().trim());
                        params.put(Customer.ADDRESS, address.getText().toString().trim());
                        params.put(Customer.EMAIL, email.getText().toString().trim());
                        params.put(Customer.PHONE, phone.getText().toString().trim());
                        Log.i("paramsCA", params.toString());
                        return params;
                    }

                };
                queue.add(postReq);
                toast.cancel();

            }

    }

}
