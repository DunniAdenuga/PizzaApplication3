package com.example.pizzaapplication3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class OrderPage extends AppCompatActivity {
    //DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_page);
        this.setTitle("Order Page");
        //dbHelper = new DBHelper(this);

        Intent intent = getIntent();
        String caller = "";
        caller = intent.getStringExtra("caller");
        //add 30 mins to current time
        Calendar now = Calendar.getInstance();
        now.add(Calendar.MINUTE, 30);
        SimpleDateFormat df = new SimpleDateFormat("hh:mm aa");

        String name = "",address = "", cost = "";
        String size = "", crust = "", protein = "", sauce = "";

        if(caller.equalsIgnoreCase(PizzaDetailsActivity.class.toString())) {
            name = intent.getStringExtra(PizzaDetailsActivity.NAME);
            address = intent.getStringExtra(PizzaDetailsActivity.ADDRESS);
            cost = intent.getStringExtra(PizzaDetailsActivity.COST);
            ////
            size = intent.getStringExtra(PizzaDetailsActivity.SIZE);
            crust = intent.getStringExtra(PizzaDetailsActivity.CRUST);
            protein = intent.getStringExtra(PizzaDetailsActivity.PROTEIN);
            sauce = intent.getStringExtra(PizzaDetailsActivity.SAUCE);
            ///
        }else if(caller.equalsIgnoreCase(TypeOfOrder.class.toString())){
            name = intent.getStringExtra(TypeOfOrder.NAME);
            address = intent.getStringExtra(TypeOfOrder.ADDRESS);
            cost = intent.getStringExtra(TypeOfOrder.COST);
            ////
            size = intent.getStringExtra(TypeOfOrder.SIZE);
            crust = intent.getStringExtra(TypeOfOrder.CRUST);
            protein = intent.getStringExtra(TypeOfOrder.PROTEIN);
            sauce = intent.getStringExtra(TypeOfOrder.SAUCE);
        }
        TextView firstText = (TextView)findViewById(R.id.textView3);
        String message = name + ", your order would be delivered to " +
                  address + " by " + df.format(now.getTime());
        firstText.setText(message);

        TextView secondText = (TextView)findViewById(R.id.textView2);
        String details = "Your order details: \n\t\t\t\t\t" + size + "\n\t\t\t\t\t" + crust + "\n\t\t\t\t\t" +
                "" + protein + "\n\t\t\t\t\t" + sauce;
        secondText.setText(details);

        TextView costHere = (TextView)findViewById(R.id.textView5);
        String stringCost = String.format(Locale.US,"%15s: %19.2f", "Cost",Double.parseDouble(cost));
        costHere.setText(stringCost);

        TextView tax = (TextView)findViewById(R.id.textView6);
        String stringTax = String.format(Locale.US,"%15s: %21.2f", "Tax",0.18*Double.parseDouble(cost));
        tax.setText(stringTax);

        TextView delCharge = (TextView)findViewById(R.id.textView7);
        double two = 2;
        String charge = String.format(Locale.US,"Delivery Charge: %11.2f", two);
        delCharge.setText(charge);

        TextView total = (TextView)findViewById(R.id.textView8);
        double sum = (1.18 * Double.parseDouble(cost)) + two;
        String stringTotal = String.format(Locale.US,"%10s: %10.2f","Total ($)", sum);
        total.setText(stringTotal);
    }
}
