package com.example.nowni.taxcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class  MainActivity extends AppCompatActivity
{
    Button income, gst;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        income = findViewById(R.id.income);
        gst = findViewById(R.id.gst);

        applyIncome();
        applyGST();
    }

    void applyIncome()
    {
        income.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this, IncomeActivity.class);
                startActivity(intent);

            }
        });
    }

    void applyGST()
    {
        gst.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this, GSTActivity.class);
                startActivity(intent);

            }
        });

    }
}
