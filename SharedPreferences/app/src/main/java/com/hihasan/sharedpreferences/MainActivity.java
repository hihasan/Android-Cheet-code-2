package com.hihasan.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    private EditText e1,e2,e3;
    private AppCompatButton b1;

    private static final String MyPREFERENCES="HiHasan";
    private static final String NAME="Name";
    private static final String PHONE="Phone";
    private static final String EMAIL="Email";

    private SharedPreferences sharedpreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        e1=(EditText) findViewById (R.id.e1); //Name
        e2=(EditText) findViewById (R.id.e2); //phone
        e3=(EditText) findViewById (R.id.e3); //email

        b1= (AppCompatButton) findViewById (R.id.press);

        sharedpreferences=getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String na=e1.getText().toString();
                String ph=e2.getText().toString();
                String em=e3.getText().toString();

                SharedPreferences.Editor editor=sharedpreferences.edit();

                editor.putString(NAME, na);
                editor.putString(PHONE,ph);
                editor.putString(EMAIL,em);
                editor.commit();

                Toast.makeText(getApplicationContext(),"DATA SAVED",Toast.LENGTH_SHORT).show();

            }
        });

    }
}
