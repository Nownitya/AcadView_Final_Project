package com.example.nowni.taxcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class IncomeActivity extends AppCompatActivity
{
    public String editIncome;
    public EditText ed_Income;
    public TextView tx_textarea;
    public Double finalValue, totalIncome;
    public Double tax;
    public Button calAmount;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);

        ed_Income = findViewById(R.id.editIncome);
        tx_textarea = findViewById(R.id.textarea);
        calAmount =  findViewById(R.id.amount);

        calAmount.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                calculateIncome();

            }
        });
    }

    void calculateIncome()
    {
        intialise();
        check();

    }
    void intialise()
    {
        editIncome = ed_Income.getText().toString().trim();
        totalIncome = Double.valueOf(editIncome);
    }
    void check()
    {
        if ( totalIncome >= 0 && totalIncome<=250000 )
        {
            tax = 0.0;
            finalValue = tax + totalIncome;
            tx_textarea.setText("Tax on Income of " + editIncome + "=" + tax + "\n" + "Total income (Inclusion of Tax)" + " = " + finalValue);

        }

        else if ( totalIncome >= 250001.0 && totalIncome <= 500000.0){
            tax = ( 0.05 * ( totalIncome - 250000.0 ));
            finalValue = tax + totalIncome;
            tx_textarea.setText("Tax on Income of " + editIncome + " = " + tax + "\n\n" + "Total income (Inclusion of Tax)" + " = " + finalValue);
        }
        else if ( totalIncome >= 500001.0 && totalIncome <= 1000000.0 ){
            tax = 25000 + 0.20*( totalIncome - 500000);
            finalValue = tax + totalIncome;
            tx_textarea.setText("Tax on income of " + editIncome + " = " + tax + "\n\n" + "Total income (Inclusion of Tax)" + " = " + finalValue);
        }
        else if( totalIncome > 1000000.0 ) {
            tax = 112500.0 + 0.30 * ( totalIncome - 1000000 );
            finalValue = tax + totalIncome;
            tx_textarea.setText("Tax on income of " + editIncome + " = " + tax + "\n\n" + "Total income (Inclusion of Tax)" + " = " + finalValue);
        }

    }
}
