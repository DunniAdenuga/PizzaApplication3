package com.example.pizzaapplication3;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;

import backendConnections.ServletPostAsyncTask;

public class MainActivity extends AppCompatActivity {
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Servlet call
        //new ServletPostAsyncTask().execute(new Pair<Context, String>(this, "Dunni-A"));

        setContentView(R.layout.activity_main);



        //dbHelper = new DBHelper(this);
    }

    /**Called When the Pizza is Clicked*/
    public void startCustomerProfile(View view){
        Intent intent = new Intent(this, CustomerProfileActivity.class);
        startActivity(intent);
    }
}
